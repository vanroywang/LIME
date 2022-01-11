package controller.command;

import controller.IImageProcessingController2;
import java.io.IOException;
import model.IMultilayerModel;

/**
 * Class for the sharpen class operation that connects the controller to the model and sharpens an
 * image.
 */
public class Sharpen implements IImageCommand {

  @Override
  public void run(IImageProcessingController2 controller, IMultilayerModel model) {
    model.sharpen();
    try {
      controller.write("The current layer has been sharpened");
    } catch (IOException e) {
      try {
        controller.write(e.getMessage());
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }
}
