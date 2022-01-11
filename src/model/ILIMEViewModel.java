package model;

import java.util.List;

/**
 * Model for the layered image manipulation and enhancement view.
 */
public interface ILIMEViewModel {
  /**
   * Gets the image in the model.
   *
   * @return The image the model is holding
   */
  IImage getImage();

  /**
   * Returns all the layers in this model.
   *
   * @return the layers of this model
   */
  List<Layer> getLayers();

  /**
   * Returns the index of the current layer.
   *
   * @return Index of the current layer
   */
  int getCurrentIndex();
}
