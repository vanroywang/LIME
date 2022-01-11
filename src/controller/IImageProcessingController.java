package controller;

import java.io.IOException;
import model.IImage;

/**
 * The interface for the controller module of the image processing application.
 */
public interface IImageProcessingController {

  /**
   * read the ppm file from the given path and load the {@code IImage} to the model from the file.
   *
   * @param fileName the path of the file
   */
  void loadToModelFromFile(String fileName);

  /**
   * Load the given {@code IImage} to the model.
   *
   * @param image the image to be loaded to model
   */
  void loadToModelFromImage(IImage image);

  /**
   * Saves the image from the model as the given file name.
   *
   * @param fileName the name of the file
   */
  void save(String fileName) throws IOException;//path to save?

}
