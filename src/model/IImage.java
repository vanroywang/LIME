package model;

/**
 * This is the interface for image.
 */
public interface IImage {

  /**
   * Gets the width of the image in number of pixels.
   *
   * @return the width of the image
   */
  int getWidth();

  /**
   * Gets the height of the image in number of pixels.
   *
   * @return the height of the image
   */
  int getHeight();

  /**
   * Gets the pixels in this image.
   *
   * @return The pixels of the image
   */
  IPixel[][] getPixels();


}
