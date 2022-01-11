package controller.command;

import controller.IImageProcessingController2;
import java.io.IOException;
import model.IMultilayerModel;

/**
 * Class for the load class operation that connects the controller to the model and loads a file.
 */
public class Load implements IImageCommand {

  private final String fileName;

  /**
   * Constructs the load operation.
   *
   * @param fileName Name of the file to load
   */
  public Load(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public void run(IImageProcessingController2 controller, IMultilayerModel model) {

    try {
      controller.loadToModelFromFile(fileName);
      controller.write("Successfully loaded " + fileName
          + " at layer number [ " + (model.getLayers().size() - 1) + " ].");
    } catch (IOException e) {
      try {
        controller.write(e.getMessage());
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }
}
