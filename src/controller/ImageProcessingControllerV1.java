package controller;


import java.io.IOException;
import java.util.Objects;
import model.IImage;
import model.IImageProcessingModel;
import model.ImageProcessingModelV1;
import model.MyUtil;

/**
 * This is the first implementation of {@code IImageProcessingController}.
 */
public class ImageProcessingControllerV1 implements IImageProcessingController {

  private final IImageProcessingModel model;

  /**
   * Constructs a controller with a given model.
   *
   * @param model the model to feed to the controller
   */
  public ImageProcessingControllerV1(IImageProcessingModel model) {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    this.model = model;
  }

  /**
   * Construct a controller with {@code ImageProcessingModelV1} as its default model.
   */
  public ImageProcessingControllerV1() {
    this.model = new ImageProcessingModelV1();
  }

  @Override
  public void loadToModelFromFile(String fileName) {
    if (fileName == null) {
      throw new IllegalArgumentException("file path cannot be null.");
    }
    if (!fileName.endsWith(".ppm")) {
      throw new IllegalArgumentException("Must load a ppm file.");
    }
    model.loadImage(MyUtil.imageFromPPM(fileName));
  }

  @Override
  public void loadToModelFromImage(IImage image) {
    model.loadImage(Objects.requireNonNull(image));
  }

  @Override
  public void save(String fileName) throws IOException {
    IImageSaver saver = new ImageSaverPpmPngJpg();
    saver.save(model.getImage(), fileName);
  }


}
