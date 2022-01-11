package controller.command;

import controller.IImageProcessingController2;
import java.io.IOException;
import model.IMultilayerModel;

/**
 * Class for the greyscale class operation that connects the controller to the model and turns an
 * image to greyscale.
 */
public class Greyscale implements IImageCommand {

  @Override
  public void run(IImageProcessingController2 controller, IMultilayerModel model) {
    model.monochrome();
    try {
      controller.write("Current image is turned into a greyscale one.");
    } catch (IOException e) {
      try {
        controller.write(e.getMessage());
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }
}
