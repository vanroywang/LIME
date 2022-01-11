package controller.command;

import controller.IImageProcessingController2;
import java.io.IOException;
import model.IMultilayerModel;


/**
 * Class for the blur class operation that connects the controller to the model and blurs an image.
 */
public class Blur implements IImageCommand {

  @Override
  public void run(IImageProcessingController2 controller, IMultilayerModel model) {
    model.blur();
    try {
      controller.write("Current image is blurred");
    } catch (IOException e) {
      try {
        controller.write(e.getMessage());
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }
}
