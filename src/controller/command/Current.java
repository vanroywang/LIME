package controller.command;

import controller.IImageProcessingController2;
import java.io.IOException;
import model.IMultilayerModel;

/**
 * Class for the current class operation that connects the controller to the model and sets an layer
 * as the current layer in the model.
 */
public class Current implements IImageCommand {

  private final int layerIndex;

  /**
   * Constructs the current operation.
   *
   * @param layerIndex Index of the layer to set as the current layer
   */
  public Current(int layerIndex) {
    this.layerIndex = layerIndex;
  }

  @Override
  public void run(IImageProcessingController2 controller, IMultilayerModel model) {
    model.setCurrentLayer(layerIndex);
    try {
      controller.write("Current layer is set to " + layerIndex);
    } catch (IOException e) {
      try {
        controller.write(e.getMessage());
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }
}
