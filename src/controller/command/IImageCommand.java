package controller.command;

import controller.IImageProcessingController2;
import model.IMultilayerModel;


/**
 * Interface representing the command pattern which calls a certain operation from the controller on
 * the model.
 */
public interface IImageCommand {

  /**
   * Calls a operation fed from the given controller on the given model.
   *
   * @param controller Controller
   * @param model model
   */
  void run(IImageProcessingController2 controller, IMultilayerModel model);
}

