package controller.command;

import controller.IImageProcessingController2;
import java.io.IOException;
import model.IMultilayerModel;

/**
 * Class for the save class operation that connects the controller to the model and saves the
 * current layer.
 */
public class Save implements IImageCommand {

  private final String fileName;

  /**
   * Constructs the save class operation.
   *
   * @param fileName Name of the file
   */
  public Save(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public void run(IImageProcessingController2 controller, IMultilayerModel model) {
    try {
      controller.save(fileName);
      controller.write("Image saved at " + fileName);
    } catch (IOException e) {
      try {
        controller.write(e.getMessage());
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }
}
