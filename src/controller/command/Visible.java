package controller.command;

import controller.IImageProcessingController2;
import java.io.IOException;
import model.IMultilayerModel;

/**
 * Class for the visible class operation that connects the controller to the model and sets a layer
 * in the model as visible.
 */
public class Visible implements IImageCommand {

  private final int layerIndex;

  /**
   * Constructs the visible class operation.
   *
   * @param layerIndex Index of the layer to set as visible.
   */
  public Visible(int layerIndex) {
    this.layerIndex = layerIndex;
  }

  @Override
  public void run(IImageProcessingController2 controller, IMultilayerModel model) {
    model.setVisibility(layerIndex, true);
    try {
      controller.write("Layer " + layerIndex + " is now visible.");
    } catch (IOException e) {
      try {
        controller.write(e.getMessage());
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }
}
