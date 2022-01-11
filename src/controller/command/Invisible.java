package controller.command;

import controller.IImageProcessingController2;
import java.io.IOException;
import model.IMultilayerModel;

/**
 * Class for the invisible class operation that connects the controller to the model and sets a
 * layer in the model as invisible.
 */
public class Invisible implements IImageCommand {

  private final int layerIndex;

  /**
   * Constructs the invisible operation.
   *
   * @param layerIndex Index of the layer to set as invisible.
   */
  public Invisible(int layerIndex) {
    this.layerIndex = layerIndex;
  }

  @Override
  public void run(IImageProcessingController2 controller, IMultilayerModel model) {
    model.setVisibility(layerIndex, false);
    try {
      controller.write("Layer " + layerIndex + " is now invisible.");
    } catch (IOException e) {
      try {
        controller.write(e.getMessage());
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }
}
