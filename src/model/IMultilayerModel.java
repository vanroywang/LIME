package model;

import java.util.List;

/**
 * Model for a multi-layer image representation of a image processing application.
 */
public interface IMultilayerModel extends IImageProcessingModel, ILIMEViewModel {
  //void addLayer(Layer layer);
  //void loadMultiLayer(IImage[] layersOfImage);

  /**
   * Sets the visiblity of the layer at the given index to the given boolean.
   *
   * @param layerId    Index of the layer
   * @param visibility true for visible, false for invisible
   */
  void setVisibility(int layerId, boolean visibility);

  /**
   * Sets the layer at the given index to the current layer.
   *
   * @param layerId Index of the layer
   */
  void setCurrentLayer(int layerId);

  /**
   * Returns all the layers in this model.
   *
   * @return the layers of this model
   */
  List<Layer> getLayers();

  /**
   * Renames the layer at the given index to the given name.
   *
   * @param layerId Index of the layer
   * @param name    New name for the layer
   */
  void nameLayer(int layerId, String name);

  /**
   * Removes a layer at the given index.
   *
   * @param layerId Index of the layer
   */
  void removeLayer(int layerId);

  /**
   * Returns the index of the current layer.
   *
   * @return Index of the current layer
   */
  int getCurrentIndex();

  /**
   * Adds a new layer with the given image and the given string as the name.
   *
   * @param img       Image of the new layer
   * @param substring Name of the new layer
   */
  void addLayer(IImage img, String substring);

  /**
   * Downscales all the images in the layers of the model.
   *
   * @param targetWidth Width to downscale to
   * @param targetHeight Height to downscale to
   */
  void downscale(int targetWidth, int targetHeight);

  /**
   * Turns an image to a mosaic version with a certain amount of seeds.
   */
  void mosaic(int numberOfSeed);
}
