package model;

/**
 * This is an implementation of {@code IImage}. It represents an image.
 */
public class ImageImpl implements IImage {

  private final int height;
  private final int width;
  private final IPixel[][] pixels;

  /**
   * Creates a blank image with no pixel at the given {@code height} and {@code width}.
   *
   * @param height The height of the image
   * @param width  the width of the image
   */
  public ImageImpl(int height, int width) {
    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException("Height and Width of an image must be positive.");
    }
    this.height = height;
    this.width = width;
    this.pixels = new Pixel[height][width];
  }

  /**
   * Creates an image with the given pixels; Its {@code height} and {@code width} are sat
   * accordingly.
   *
   * @param pixels The 2-d array of pixels that makes up the image
   */
  public ImageImpl(IPixel[][] pixels) {
    if (pixels == null) {
      throw new IllegalArgumentException("Input pixel cannot be null");
    }
    this.height = pixels.length;
    this.width = pixels[0].length;
    this.pixels = pixels;
  }


  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public IPixel[][] getPixels() {
    return pixels;
  }

}
