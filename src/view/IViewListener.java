package view;

/**
 * Interface for the view listener which listens for a call from the view and calls a method onto
 * the model.
 */
public interface IViewListener {

  /**
   * Handles saving a single image from the model, called from myWindow view.
   *
   * @param path path to save the image to
   */
  void handleSaveSingleEvent(String path);

  /**
   * Handles saving multiple images from the model, called from myWindow view.
   *
   * @param fileName Name of the file to save images as
   */
  void handleSaveMultipleEvent(String fileName);

  /**
   * Handles loading an image to the model, called from the myWindow view.
   *
   * @param path Path of the image to be loadded
   */
  void handleLoadEvent(String path);

  /**
   * Handles adding the sepia color transformation to an image in the model, called from the
   * myWindow view.
   */
  void handleSepia();

  /**
   * Handles adding the greyscale color transformation to an image in the model, called from the
   * myWindow view.
   */
  void handleGreyscale();

  /**
   * Handles adding the blur filter to an image in the model, called from the myWindow view.
   */
  void handleBlur();

  /**
   * Handles adding the sharpen filter to an image in the model, called from the myWindow view.
   */
  void handleSharpen();

  /**
   * Handles setting a layer as the current layer in the model, called from the myWindow view.
   *
   * @param name Name of the layer to set as the current layer
   */
  void handleSetCurrent(String name);

  /**
   * Handles setting a layer to visible in the model, called from the myWindow view.
   *
   * @param name Name of the layer to set as visible
   */
  void handleVisible(String name);

  /**
   * Handles setting a layer to invisible in the model, called from the myWindow view.
   *
   * @param name Name of the layer to set as invisible
   */
  void handleInvisible(String name);

  /**
   * Handles removing a layer from the model, called from the myWindow view.
   *
   * @param name Name of the layer to remove
   */
  void handleRemoveLayer(String name);

  /**
   * Handles downscaling the all the layers from the model, called from the myWindow view.
   *
   * @param width  Width to downscale to
   * @param height Height to downscale to
   */
  void handleDownscale(int width, int height);

  /**
   * Handles adding the mosaic filter to an image in the model, called from the myWindow view.
   *
   * @param n Number of seeds
   */
  void handleMosaic(int n);
}
