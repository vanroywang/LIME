package controller.command;

import controller.IImageProcessingController2;
import java.io.IOException;
import model.IMultilayerModel;

/**
 * Class for the remove class operation that connects the controller to the model and removes a
 * layer.
 */
public class Remove implements IImageCommand {

  private final int layerIndex;

  /**
   * Constructs the remove class operation.
   *
   * @param layerIndex Index of the layer to remove
   */
  public Remove(int layerIndex) {
    this.layerIndex = layerIndex;
  }

  @Override
  public void run(IImageProcessingController2 controller, IMultilayerModel model) {
    model.removeLayer(layerIndex);
    if (layerIndex < model.getCurrentIndex()) {
      model.setCurrentLayer(model.getCurrentIndex());
    } else if (layerIndex == model.getCurrentIndex()) {
      model.setCurrentLayer(-1);
    }
    try {
      controller.write("Layer " + layerIndex + " is removed.");
    } catch (IOException e) {
      try {
        controller.write(e.getMessage());
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }
}
