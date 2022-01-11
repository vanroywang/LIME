package model;


import model.colortransformation.MonochromeTransformation;
import model.colortransformation.SepiaTransformation;
import model.filtering.BlurringFilter;
import model.filtering.SharpeningFilter;

/**
 * The first implementation of the {@code IImageProcessingModel}.
 */
public class ImageProcessingModelV1 implements IImageProcessingModel {

  private IImage image;

  @Override
  public void loadImage(IImage image) {
    if (image == null) {
      throw new IllegalArgumentException("Input image cannot be null.");
    }
    this.image = image;
  }

  @Override
  public void blur() {
    if (image == null) {
      throw new IllegalArgumentException("Input image cannot be null.");
    }
    image = new BlurringFilter().apply(image);
  }

  @Override
  public void sharpen() {
    if (image == null) {
      throw new IllegalArgumentException("Input image cannot be null.");
    }
    image = new SharpeningFilter().apply(image);
  }

  @Override
  public void monochrome() {
    if (image == null) {
      throw new IllegalArgumentException("Input image cannot be null.");
    }
    image = new MonochromeTransformation().apply(image);
  }

  @Override
  public void sepia() {
    if (image == null) {
      throw new IllegalArgumentException("Input image cannot be null.");
    }
    image = new SepiaTransformation().apply(image);
  }

  @Override
  public IImage getImage() {
    if (image == null) {
      throw new IllegalArgumentException("Input image cannot be null.");
    }
    return image;
  }


}
