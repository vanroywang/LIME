package model;


/**
 * This is the interface for image processing models.
 */
public interface IImageProcessingModel {

  /**
   * Loads the given image to be processed.
   *
   * @param image the image to be processed
   */
  void loadImage(IImage image);


  /**
   * Blurs the current image. It can be applied to an image that has already been blurred to further
   * blur it.
   */
  void blur();

  /**
   * Sharpens the current image. It can be applied to an image that has already been sharpened to
   * further sharpen it.
   */
  void sharpen();

  /**
   * Turns the current image into a greyscale image.
   */
  void monochrome();

  /**
   * Turns the current image into a sepia-toned image.
   */
  void sepia();

  /**
   * Gets the image in the model.
   *
   * @return The image the model is holding
   */
  IImage getImage();
}
