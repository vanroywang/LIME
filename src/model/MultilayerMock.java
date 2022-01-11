package model;

import java.util.List;

/**
 * This mock model is used to test that controller passes instruction to its model correctly.
 */
public class MultilayerMock implements IMultilayerModel {
  private StringBuilder log;

  public MultilayerMock() {
    log = new StringBuilder();
  }

  @Override
  public void loadImage(IImage image) {
    log.append(String.format("Image [%s] with width %d and height %d is loaded.\n",
        image.toString(), image.getWidth(), image.getHeight()));
  }

  @Override
  public void blur() {
    log.append("blurred.\n");
  }

  @Override
  public void sharpen() {
    log.append("sharpened.\n");
  }

  @Override
  public String toString() {
    return log.toString();
  }

  @Override
  public void monochrome() {
    log.append("monochrome effect.\n");
  }

  @Override
  public void sepia() {
    log.append("sepia effect.\n");
  }

  @Override
  public IImage getImage() {
    log.append("Queried for image.\n");
    return null;
  }

  @Override
  public void setVisibility(int layerId, boolean visibility) {
    log.append(String.format("The visibility of layer number %d is sat as %b.\n",
        layerId, visibility));
  }

  @Override
  public void setCurrentLayer(int layerId) {
    log.append(String.format("Layer number %d is sat as current layer.\n",
        layerId));
  }


  @Override
  public List<Layer> getLayers() {
    log.append("The layers of the model is queried.\n");
    return null;
  }

  @Override
  public void nameLayer(int layerId, String name) {
    log.append(String.format("Layer number %d is named as %s.\n",
        layerId, name));
  }

  @Override
  public void removeLayer(int layerId) {
    log.append(String.format("Layer number %d is removed.\n",
        layerId));
  }

  @Override
  public int getCurrentIndex() {
    return 0;
  }

  @Override
  public void addLayer(IImage img, String name) {
    log.append(String.format("Added a layer with Image [%s] called %s.\n",
        img.toString(), name));
  }

  @Override
  public void downscale(int targetWidth, int targetHeight) {
    // not needed because controller scripting doesn't require downscale
  }

  @Override
  public void mosaic(int numberSeed) {
    // not needed because controller scripting doesn't require mosaic
  }

}
