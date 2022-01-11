package controller.command;

import controller.IImageProcessingController2;
import java.io.IOException;
import model.IMultilayerModel;

/**
 * Class for the sepia class operation that connects the controller to the model and turns an image
 * to sepia.
 */
public class Sepia implements IImageCommand {

  @Override
  public void run(IImageProcessingController2 controller, IMultilayerModel model) {
    model.sepia();
    try {
      controller.write("The current layer has been turned into sepia-toned");
    } catch (IOException e) {
      try {
        controller.write(e.getMessage());
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }
}
