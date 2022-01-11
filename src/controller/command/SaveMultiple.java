package controller.command;

import controller.IImageProcessingController2;
import java.io.IOException;
import model.IMultilayerModel;

/**
 * Class for the save multiple class operation that connects the controller to the model and saves
 * the multilayer image.
 */
public class SaveMultiple implements IImageCommand {

  private final String fileName;
  private final String fileType;

  /**
   * Constructs the save multiple class operation.
   *
   * @param fileName Name the file should be saved as
   * @param fileType Type of the images
   */
  public SaveMultiple(String fileName, String fileType) {
    this.fileName = fileName;
    this.fileType = fileType;
  }

  @Override
  public void run(IImageProcessingController2 controller, IMultilayerModel model) {
    try {
      controller.saveMultiLayer(fileName, fileType);
      controller.write("Images from all layers are created at " + fileName);
    } catch (IOException e) {
      try {
        controller.write(e.getMessage());
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }
}
