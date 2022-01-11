package view;

/**
 * Interface for the GUI view for an image processing application.
 */
public interface IImageProcessingView2 extends IImageProcessingView {

  /**
   * Registers a view listener to the list of listeners.
   *
   * @param listener Listener to register to the list of listeners
   */
  void registerViewEventListener(IViewListener listener);

  /**
   * Updates the image currently displayed in the GUI.
   */
  void updateImage();

  /**
   * Asks for the focus in this view.
   */
  void askForFocus();

  /**
   * Adds a layer to the model this view is displaying.
   *
   * @param fileName File to load into the layer being added.
   */
  void addLayer(String fileName);

  /**
   * Removes a layer from the model this view is displaying.
   *
   * @param fileName Removes a layer with the given name from the model
   */
  void removeLayer(String fileName);
}
