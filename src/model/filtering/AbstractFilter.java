package model.filtering;

import model.IImage;
import model.IPixel;
import model.ImageImpl;
import model.MyUtil;
import model.Pixel;

/**
 * The abstract class of filters.
 */
public abstract class AbstractFilter implements IFilter {

  /**
   * This number depends on the size of the kernel. It is {@code (kernel.length - 1 )/ 2}.
   */
  protected int emptyLayerNeeded;
  protected double[][] kernel;

  @Override
  public IImage apply(IImage input) {
    if (input == null) {
      throw new IllegalArgumentException("Input image cannot be null.");
    }
    IPixel[][] outputPixels = new IPixel[input.getHeight()][input.getWidth()];
    IPixel[][] layered = getEmptyLayeredPixels(input, emptyLayerNeeded);
    for (int i = 0; i < input.getHeight(); i++) {
      for (int j = 0; j < input.getWidth(); j++) {
        outputPixels[i][j] = applyKernel(layered, i, j);
      }
    }
    return new ImageImpl(outputPixels);
  }

  /**
   * This helper method apply empty layers around the pixels of the given image in order to apply
   * filter to it.
   *
   * @param image       The image to be added empty layers to
   * @param numOfLayers the number of layers to be added
   * @return the pixels with empty layer(s)
   */
  protected IPixel[][] getEmptyLayeredPixels(IImage image, int numOfLayers) {
    IPixel[][] original = image.getPixels();
    IPixel emptyPixel = new Pixel(0, 0, 0);
    int width = image.getWidth();
    int height = image.getHeight();
    IPixel[][] output = new IPixel[height + 2 * numOfLayers][width + 2 * numOfLayers];

    //add n number of empty rows at size width + 2n above the pixels
    for (int i = 0; i < numOfLayers; i++) {
      for (int j = 0; j < width + 2 * numOfLayers; j++) {
        output[i][j] = emptyPixel;
      }
    }

    //add 2*n columns of empty pixels at the left
    for (int i = numOfLayers; i < height + numOfLayers; i++) {
      for (int j = 0; j < numOfLayers; j++) {
        output[i][j] = emptyPixel;
      }
    }

    //add 2*n columns of empty pixels at the right
    for (int i = numOfLayers; i < height + numOfLayers; i++) {
      for (int j = numOfLayers + width; j < 2 * numOfLayers + width; j++) {
        output[i][j] = emptyPixel;
      }
    }

    //add n number of empty row at size width + 2n in the end
    for (int i = height + numOfLayers; i < height + 2 * numOfLayers; i++) {
      for (int j = 0; j < width + 2 * numOfLayers; j++) {
        output[i][j] = emptyPixel;
      }
    }

    //fill original's values into the middle of the output (within the empty layers)
    for (int i = numOfLayers; i < height + numOfLayers; i++) {
      for (int j = numOfLayers; j < width + numOfLayers; j++) {
        output[i][j] = original[i - numOfLayers][j - numOfLayers];
      }
    }
    return output;
  }

  /**
   * This helper method returns the pixel at [i][j] after applying the kernel to it.
   *
   * @param layered the original pixels with empty layer
   * @param row     row number of the output pixel
   * @param column  column number of the output pixel
   * @return the pixel after applying the kernel
   */
  protected IPixel applyKernel(IPixel[][] layered, int row, int column) {
    double redVal = 0;
    double greenVal = 0;
    double blueVal = 0;
    for (int i = row; i < row + 2 * emptyLayerNeeded + 1; i++) {
      for (int j = column; j < column + 2 * emptyLayerNeeded + 1; j++) {
        redVal = redVal + kernel[i - row][j - column] * layered[i][j].getRed();
        greenVal = greenVal + kernel[i - row][j - column] * layered[i][j].getGreen();
        blueVal = blueVal + kernel[i - row][j - column] * layered[i][j].getBlue();
      }
    }
    redVal = MyUtil.clamp(redVal);
    greenVal = MyUtil.clamp(greenVal);
    blueVal = MyUtil.clamp(blueVal);
    return new Pixel((int) Math.round(redVal), (int) Math.round(greenVal),
        (int) Math.round(blueVal));
  }
}
