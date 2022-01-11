package controller.command;

import controller.IImageProcessingController2;
import java.io.IOException;
import model.IMultilayerModel;

/**
 *   Class for the operation command that connects the controller to the model and
 *   queries the possible commands.
 */
public class CommandList implements IImageCommand {

  @Override
  public void run(IImageProcessingController2 controller, IMultilayerModel model) {
    try {
      controller.write(controller.getCommandList());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
