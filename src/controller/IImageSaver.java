package controller;

import java.io.IOException;
import model.IImage;

/**
 * Interface for saving images.
 */
public interface IImageSaver {

  /**
   * Saves an image.
   *
   * @param image    Image to be saved
   * @param fileName Name of the file
   * @throws IOException If file cannot be written or image cannot be saved
   */
  void save(IImage image, String fileName) throws IOException;
}
