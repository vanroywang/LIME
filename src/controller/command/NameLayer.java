package controller.command;

import controller.IImageProcessingController2;
import java.io.IOException;
import model.IMultilayerModel;

/**
 * Class for the nameLayer class operation that connects the controller to the model and renames a
 * layer.
 */
public class NameLayer implements IImageCommand {

  private final int layerId;
  private final String name;

  /**
   * Constructs the name layer class operation.
   *
   * @param layerId Layer to rename
   * @param name    New name for the layer
   */
  public NameLayer(int layerId, String name) {
    this.layerId = layerId;
    this.name = name;
  }

  @Override
  public void run(IImageProcessingController2 controller, IMultilayerModel model) {
    model.nameLayer(layerId, name);
    try {
      controller.write("Layer " + layerId + " is named as " + name);
    } catch (IOException e) {
      try {
        controller.write(e.getMessage());
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }
}
