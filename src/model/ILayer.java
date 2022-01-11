package model;

/**
 * Interface for the representation of a layer in a multi-layer image.
 */
public interface ILayer {

  /**
   * Gets the visiblity of this layer.
   *
   * @return true for visible, false for invisible
   */
  boolean getVisibility();

  /**
   * Sets the visibility of this layer to the given boolean.
   *
   * @param visibility visiblity of the layer
   */
  void setVisibility(boolean visibility);

  /**
   * Gets the image stored in this layer.
   *
   * @return Image stored in the layer
   */
  IImage getImage();

  /**
   * Applies the given effect to the image in the layer.
   *
   * @param effect effect to apply
   */
  void applyEffect(IEffect effect);

  /**
   * Gets the name of this layer.
   *
   * @return name of this layer
   */
  String getName();

  /**
   * Sets the name of this layer to the given name.
   *
   * @param name New name for this layer
   */
  void setName(String name);

  /**
   * Downscales a layer to a certain width and height.
   *
   * @param targetWidth  Width to downscale the image to
   * @param targetHeight Height to downscale the image to
   */
  void layerDownscale(int targetWidth, int targetHeight);

  /**
   * Turns the image in this layer into a mosaic version with the given number of seeds.
   *
   * @param numberOfSeed Number of seeds for the mosaic
   */
  void layerMosaic(int numberOfSeed);
}
