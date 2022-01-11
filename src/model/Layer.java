package model;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a layer in a multi-layer image.
 */
public class Layer implements ILayer {

  private boolean visibility;
  private IImage image;
  private String name;


  /**
   * Constructs a layer with the given image and a blank name.
   *
   * @param image Image to store in the layer
   */
  public Layer(IImage image) {
    this(true, image, "");
  }


  /**
   * Constructs a layer with the given image and string name.
   *
   * @param image Image to store in the layer
   * @param name  Name of the layer
   */
  public Layer(IImage image, String name) {
    this(true, image, name);
  }

  /**
   * Constructs a layer with the given visibility, image, and name.
   *
   * @param visibility Visibility setting of the layer
   * @param image      Image to store in the layer
   * @param name       Name of the layer
   */
  public Layer(boolean visibility, IImage image, String name) {
    this.visibility = visibility;
    this.image = image;
    this.name = name;
  }

  @Override
  public boolean getVisibility() {
    return visibility;
  }

  @Override
  public void setVisibility(boolean visibility) {
    this.visibility = visibility;
  }

  @Override
  public IImage getImage() {
    return image;
  }

  @Override
  public void applyEffect(IEffect effect) {
    IImage newImage = effect.apply(image);
    image = newImage;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void layerDownscale(int targetWidth, int targetHeight) {
    IPixel[][] outPixels = new IPixel[targetHeight][targetWidth];
    IPixel[][] pixels = image.getPixels();
    int originalWidth = image.getWidth();
    int originalHeight = image.getHeight();
    for (int row = 0; row < targetHeight; row++) {
      for (int col = 0; col < targetWidth; col++) {
        double x = col * originalWidth / targetWidth;
        double y = row * originalHeight / targetHeight;
        int xRoundDown = col * originalWidth / targetWidth;
        int yRoundDown = row * originalHeight / targetHeight;
        int xRoundUp = xRoundDown + 1;
        int yRoundUp = yRoundDown + 1;
        IPixel pixelA = pixels[yRoundDown][xRoundDown];
        IPixel pixelB = pixels[yRoundDown][xRoundUp];
        IPixel pixelC = pixels[yRoundUp][xRoundDown];
        IPixel pixelD = pixels[yRoundUp][xRoundUp];
        double nRed = pixelD.getRed() * (x - xRoundDown) + pixelC.getRed() * (xRoundUp - x);
        double mRed = pixelB.getRed() * (x - xRoundDown) + pixelA.getRed() * (xRoundUp - x);
        double redValue = nRed * (y - yRoundDown) + mRed * (yRoundUp - y);
        int red = (int) Math.round(redValue);
        double nBlue = pixelD.getBlue() * (x - xRoundDown) + pixelC.getBlue() * (xRoundUp - x);
        double mBlue = pixelB.getBlue() * (x - xRoundDown) + pixelA.getBlue() * (xRoundUp - x);
        double blueValue = nBlue * (y - yRoundDown) + mBlue * (yRoundUp - y);
        int blue = (int) Math.round(blueValue);
        double nGreen = pixelD.getGreen() * (x - xRoundDown) + pixelC.getGreen() * (xRoundUp - x);
        double mGreen = pixelB.getGreen() * (x - xRoundDown) + pixelA.getGreen() * (xRoundUp - x);
        double greenValue = nGreen * (y - yRoundDown) + mGreen * (yRoundUp - y);
        int green = (int) Math.round(greenValue);
        outPixels[row][col] = new Pixel(red, green, blue);
      }
    }
    image = new ImageImpl(outPixels);

  }

  @Override
  public void layerMosaic(int numberOfSeed) {
    Random rand = new Random();
    IPixel[][] outPixels = new IPixel[image.getHeight()][image.getWidth()];
    Point2D[] seeds = new Point[numberOfSeed];
    ArrayList<ArrayList<Point2D>> clusters = new ArrayList<>(numberOfSeed);
    //each item in clusters is a cluster. Each contains a cluster of positions of pixels that
    // will be mixed
    for (int i = 0; i < numberOfSeed; i++) {
      seeds[i] = new Point(rand.nextInt(image.getWidth()), rand.nextInt(image.getHeight()));
      clusters.add(new ArrayList<>());
    }
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        clusters.get(getClosestSeed(row, col, seeds)).add(new Point(col, row));
      }
    }
    for (ArrayList<Point2D> cluster : clusters) {
      int[] rgb = getMixedRGB(cluster);
      //rgb[0] is red; rgb[1] is green; rgb[2] is blue.
      for (Point2D pt : cluster) {
        outPixels[(int) pt.getY()][(int) pt.getX()] = new Pixel(rgb[0], rgb[1], rgb[2]);
      }
    }
    image = new ImageImpl(outPixels);


  }

  /**
   * Averages out the RGB values of all the pixels in the provided cluster.
   *
   * @param cluster Cluster to average
   * @return Array of integers representing the averaged RGB values
   */
  private int[] getMixedRGB(ArrayList<Point2D> cluster) {
    IPixel[][] pixels = image.getPixels();
    int[] out = new int[3];
    double redSum = 0;
    double greenSum = 0;
    double blueSum = 0;
    for (Point2D pt : cluster) {
      redSum += pixels[(int) pt.getY()][(int) pt.getX()].getRed();
      greenSum += pixels[(int) pt.getY()][(int) pt.getX()].getGreen();
      blueSum += pixels[(int) pt.getY()][(int) pt.getX()].getBlue();
    }
    double redAvg = redSum / cluster.size();
    double greenAvg = greenSum / cluster.size();
    double blueAvg = blueSum / cluster.size();
    out[0] = (int) Math.round(redAvg);
    out[1] = (int) Math.round(greenAvg);
    out[2] = (int) Math.round(blueAvg);
    return out;
  }

  /**
   * Returns the index of the seed in seeds.
   *
   * @param row   Row of the seed to check
   * @param col   Column of the seed to check
   * @param seeds All of the seeds.
   * @return Integer representing the index of the closest seed
   */
  private int getClosestSeed(int row, int col, Point2D[] seeds) {
    int closestPointSoFar = 0;
    double closestDistSoFar = seeds[0].distance(col, row);
    for (int i = 1; i < seeds.length; i++) {
      double distance = seeds[i].distance(col, row);
      if (distance < closestDistSoFar) {
        closestPointSoFar = i;
        closestDistSoFar = distance;
      }
    }
    return closestPointSoFar;
  }
}
