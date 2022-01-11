package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.colortransformation.MonochromeTransformation;
import model.colortransformation.SepiaTransformation;
import model.filtering.BlurringFilter;
import model.filtering.SharpeningFilter;

/**
 * Model for multi-layer image representation of an image processing application.
 */
public class MultilayerModel implements IMultilayerModel, ILIMEViewModel {

  protected final ArrayList<Layer> layers;
  protected int current;
  protected int height;
  protected int width;

  /**
   * Constructs a multi-layer model with no layers and the current layer index set to -1.
   */
  public MultilayerModel() {
    this(new ArrayList<Layer>(), 0);
  }

  /**
   * Constructs a multi-layer model with the given layers and current index.
   *
   * @param layers  Layers to feed into the model
   * @param current Index of the current layer in the model
   */
  public MultilayerModel(ArrayList<Layer> layers, int current) {
    this.layers = Objects.requireNonNull(layers);
    this.current = current;
  }

  @Override
  public void loadImage(IImage image) {
    if (image == null) {
      throw new IllegalArgumentException("image cannot be null.");
    }
    if (layers.isEmpty()) {
      height = image.getHeight();
      width = image.getWidth();
      layers.add(new Layer(image));
      current = 0;
    } else if ((height != image.getHeight()) || (width != image.getWidth())) {
      throw new IllegalArgumentException("Dimensions in a multilayer must be the same.");
    } else {
      layers.add(new Layer(image));
    }
  }

  @Override
  public void blur() {
    layers.get(current).applyEffect(new BlurringFilter());
  }

  @Override
  public void sharpen() {
    layers.get(current).applyEffect(new SharpeningFilter());
  }

  @Override
  public void monochrome() {
    layers.get(current).applyEffect(new MonochromeTransformation());
  }

  @Override
  public void sepia() {
    layers.get(current).applyEffect(new SepiaTransformation());
  }

  @Override
  public IImage getImage() {
    for (Layer layer : layers) {
      if (layer.getVisibility()) {
        return layer.getImage();
      }
    }
    throw new IllegalArgumentException("There's no visible image.");
  }


  @Override
  public void setVisibility(int layerId, boolean visibility) {
    if (layerId < 0 || layerId >= layers.size()) {
      throw new IllegalArgumentException("Layer index out of bound.");
    }
    layers.get(layerId).setVisibility(visibility);
  }

  @Override
  public void setCurrentLayer(int layerId) {
    if (((layerId != -1) && layerId < 0) || layerId >= layers.size()) {
      throw new IllegalArgumentException("Layer index out of bound.");
    }
    current = layerId;
  }

  @Override
  public List<Layer> getLayers() {
    ArrayList<Layer> out = new ArrayList<>();
    for (Layer l : layers) {
      out.add(new Layer(l.getVisibility(), l.getImage(), l.getName()));
    }
    return out;
  }

  @Override
  public void nameLayer(int layerId, String name) {
    if (layerId < 0 || layerId >= layers.size()) {
      throw new IllegalArgumentException("Layer index out of bound.");
    }
    IImage img = layers.get(layerId).getImage();
    layers.set(layerId, new Layer(img, Objects.requireNonNull(name)));
  }

  @Override
  public void removeLayer(int layerId) {
    if (layerId < 0 || layerId >= layers.size()) {
      throw new IllegalArgumentException("Layer index out of bound.");
    }
    layers.remove(layerId);
    if (layerId < current) {
      current = current - 1;
    } else if (layerId == current) {
      current = 0;
    }
  }

  @Override
  public int getCurrentIndex() {
    return current;
  }

  @Override
  public void addLayer(IImage img, String name) {
    if (layers.isEmpty()) {
      this.height = img.getHeight();
      this.width = img.getWidth();
    }
    layers.add(new Layer(img, name));
  }

  @Override
  public void downscale(int targetWidth, int targetHeight) {
    if (targetWidth > width || targetHeight > height) {
      throw new IllegalArgumentException("Target width cannot be larger than current: "
          + width + "; Target height cannot be larger than current: "
          + height);
    }
    for (Layer lyr : layers) {
      lyr.layerDownscale(targetWidth, targetHeight);
    }
    width = targetWidth;
    height = targetHeight;
  }

  @Override
  public void mosaic(int numberOfSeed) {
    layers.get(current).layerMosaic(numberOfSeed);
  }
}
