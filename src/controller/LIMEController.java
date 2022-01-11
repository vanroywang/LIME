package controller;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import model.IImage;
import model.IMultilayerModel;
import model.MyUtil;
import view.IImageProcessingView2;
import view.IViewListener;

/**
 * Controller for layer image manipulation and enhancement.
 */
public class LIMEController implements IViewListener, IImageProcessingController2 {

  private final IImageProcessingController2 delegate;
  private final IMultilayerModel model;
  private final IImageProcessingView2 view;

  /**
   * Constructs a LIME controller with the given model, delegate and view.
   *
   * @param model    model to feed to the controller
   * @param delegate ///
   * @param view     ///
   */
  public LIMEController(IMultilayerModel model, IImageProcessingController2 delegate,
      IImageProcessingView2 view) {
    this.delegate = delegate;
    this.model = model;
    this.view = view;
    this.view.registerViewEventListener(this);
  }

  @Override
  public void handleSaveSingleEvent(String path) {
    try {
      save(path);
    } catch (IOException e) {
      view.renderMessage("Invalid Path Name");
    }
  }

  @Override
  public void handleSaveMultipleEvent(String fileName) {
    try {
      saveMultiLayer(fileName.split("\\.")[0], fileName.split("\\.")[1]);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleLoadEvent(String path) {
    loadToModelFromFile(path);
    view.updateImage();
    view.askForFocus();
  }

  @Override
  public void handleSepia() {
    model.sepia();
    view.updateImage();
  }

  @Override
  public void handleGreyscale() {
    model.monochrome();
    view.updateImage();
  }

  @Override
  public void handleBlur() {
    model.blur();
    view.updateImage();
  }

  @Override
  public void handleSharpen() {
    model.sharpen();
    view.updateImage();
  }

  @Override
  public void handleSetCurrent(String name) {
    for (int i = 0; i < model.getLayers().size(); i++) {
      if (name.equals(model.getLayers().get(i).getName())) {
        model.setCurrentLayer(i);
      }
    }
  }

  @Override
  public void handleVisible(String name) {
    for (int i = 0; i < model.getLayers().size(); i++) {
      if (name.equals(model.getLayers().get(i).getName())) {
        model.setVisibility(i, true);
      }
    }
    view.updateImage();
  }

  @Override
  public void handleInvisible(String name) {
    for (int i = 0; i < model.getLayers().size(); i++) {
      if (name.equals(model.getLayers().get(i).getName())) {
        model.setVisibility(i, false);
      }
    }
    view.updateImage();
  }


  @Override
  public void handleRemoveLayer(String name) {
    for (int i = 0; i < model.getLayers().size(); i++) {
      if (name.equals(model.getLayers().get(i).getName())) {
        model.removeLayer(i);
      }
    }

    view.removeLayer(name);
    view.updateImage();
  }

  @Override
  public void handleDownscale(int width, int height) {
    try {
      model.downscale(width, height);
    } catch (IllegalArgumentException e) {
      view.renderMessage(e.getMessage());
    }
    view.updateImage();
  }

  @Override
  public void handleMosaic(int n) {
    model.mosaic(n);
    view.updateImage();
  }

  @Override
  public void loadToModelFromFile(String fileName) {
    File file = new File(fileName);
    if (file.isDirectory()) {
      for (File f : Objects.requireNonNull(file.listFiles())) {
        loadSingleFile(fileName + "\\" + f.getName());
      }
    } else {
      loadSingleFile(fileName);
    }
  }

  /**
   * Loads a single file to the model.
   *
   * @param fileName Name of the file to load to the model
   */
  private void loadSingleFile(String fileName) {
    loadSingle(fileName, model);
    if (!fileName.endsWith("txt")) {
      view.addLayer(fileName);
    }
  }

  /**
   * Helper to load a single various types of images to the model.
   *
   * @param fileName Name of the images to load
   * @param model    Model to load the images to
   */
  static void loadSingle(String fileName, IMultilayerModel model) {
    String layerName = fileName
        .substring(fileName.lastIndexOf("\\") + 1, fileName.lastIndexOf("."));
    if (fileName.endsWith("png") || fileName.endsWith("jpg")) {
      IImage img = MyUtil.imageFromPNGAndJPG(fileName);
      model.addLayer(img, layerName);
    } else if (fileName.endsWith("ppm")) {
      IImage img = MyUtil.imageFromPPM(fileName);
      model.addLayer(img, layerName);
    } else if (!fileName.endsWith("txt")) {
      throw new IllegalArgumentException("Unsupported file");
    }
  }


  @Override
  public void loadToModelFromImage(IImage image) {
    delegate.loadToModelFromImage(image);
  }

  @Override
  public void save(String fileName) throws IOException {
    delegate.save(fileName);
  }

  @Override
  public void saveMultiLayer(String fileName, String imageType) throws IOException {
    delegate.saveMultiLayer(fileName, imageType);
  }

  @Override
  public void runProcessor() {
    delegate.runProcessor();

  }

  @Override
  public void write(String message) throws IOException {
    delegate.write(message);

  }

  @Override
  public String getCommandList() {
    return delegate.getCommandList();
  }
}
