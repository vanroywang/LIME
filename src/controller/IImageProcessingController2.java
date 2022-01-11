package controller;

import java.io.IOException;

/**
 * Controller for the multi-layer representation of the image processing application.
 */
public interface IImageProcessingController2 extends IImageProcessingController {

  /**
   * Saves a multilayer image.
   *
   * @param fileName  Name of the file to save the images to
   * @param imageType Image type to save the images as
   * @throws IOException if file cannot be written or saved
   */
  void saveMultiLayer(String fileName, String imageType) throws IOException;

  /**
   * Runs the operations called from the readable.
   */
  void runProcessor();

  /**
   * Appends a message to the appendable in the view.
   *
   * @param message Message to append
   * @throws IOException if message cannot be appended
   */
  void write(String message) throws IOException;

  /**
   * To query the possible command from this controller.
   * @return the names of the commands in strings
   */
  String getCommandList();
}
