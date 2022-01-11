package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.IImage;
import model.IPixel;
import model.MyUtil;

/**
 * Saves images of different formats.
 */
public class ImageSaverPpmPngJpg implements IImageSaver {

  @Override
  public void save(IImage image, String fileName) throws IOException {
    if (fileName.endsWith(".ppm")) {
      savePPM(image, fileName);
    } else if (fileName.endsWith(".jpg") || fileName.endsWith(".png")) {
      savePngJpg(image, fileName);
    } else {
      throw new IOException("unsupported file type.");
    }
  }

  /**
   * Saves an image with the png or jpg format.
   *
   * @param image    Image to be saved
   * @param fileName Name for the saved image.
   * @throws IOException If file cannot be written
   */
  private void savePngJpg(IImage image, String fileName) throws IOException {
    BufferedImage bufferedImage = MyUtil.getImageFromIImage(image);
    File file = new File(fileName);
    if (fileName.endsWith("jpg")) {
      ImageIO.write(bufferedImage, "jpg", file);
    } else {
      ImageIO.write(bufferedImage, "png", file);
    }
  }

  /**
   * Saves an image as a ppm.
   *
   * @param image    Image to be saved
   * @param fileName Name for the image file
   * @throws IOException If file cannot be written
   */
  protected void savePPM(IImage image, String fileName) throws IOException {
    String data = getDataFromImage(image);
    FileOutputStream writer = new FileOutputStream(fileName);
    writer.write(data.getBytes());
  }

  /**
   * Gets the data from an image and stores as a string.
   *
   * @param image Image to retrieve data from
   * @return String with the image data
   */
  protected String getDataFromImage(IImage image) {
    if (image == null) {
      throw new IllegalArgumentException("Source image cannot be null.");
    }
    StringBuilder builder = new StringBuilder();
    IPixel[][] pixels = image.getPixels();
    builder.append("P3\n" + image.getWidth() + " " + image.getHeight() + "\n" + 255 + "\n");
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        builder.append(pixels[i][j].getRed() + "\n"
            + pixels[i][j].getGreen() + "\n"
            + pixels[i][j].getBlue() + "\n");
      }
    }
    return builder.toString();
  }
}
