package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * This class contains utility methods.
 */
public class MyUtil {

  /**
   * Read an image file in the PPM format and return it as an {@code IImage}.
   *
   * @param filename the path of the file.
   * @return the {@code IImage} format of the given file.
   */
  public static IImage imageFromPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("This is not a PPM file.");
    }
    int width = sc.nextInt();
    // System.out.println("Width of image: "+width);
    int height = sc.nextInt();
    //System.out.println("Height of image: "+height);
    int maxValue = sc.nextInt();
    //    System.out.println("Maximum value of a color in this file (usually 256): "+maxValue);
    IPixel[][] pixels = new IPixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[i][j] = new Pixel(r, g, b);

      }
    }
    return new ImageImpl(pixels);
  }

  /**
   * A method to create a square checker board image with the given row number, size, and two
   * colors.
   *
   * @param row  number of rows and columns in the checker board
   * @param size the number of pixels that make up one side of a unit (1 * 1) board
   * @param c1   the first color of the checker board
   * @param c2   the second color of the checker board
   * @return The image with specified configuration
   * @throws IllegalArgumentException if the parameters are invalid
   */

  public static IImage creatCheckerboard(int row, int size, Color c1, Color c2)
      throws IllegalArgumentException {
    if (row <= 0 || size <= 0 || c1 == null || c2 == null) {
      throw new IllegalArgumentException("Size and row must be positive, "
          + "and colors cannot be null");
    }
    IPixel[][] pixels = new Pixel[row * size][row * size];
    for (int i = 0; i < row * size; i++) {
      for (int j = 0; j < row * size; j++) {
        if ((i / size + j / size) % 2 == 0) {
          pixels[i][j] = new Pixel(c1.getRed(), c1.getGreen(), c1.getBlue());
        } else {
          pixels[i][j] = new Pixel(c2.getRed(), c2.getGreen(), c2.getBlue());
        }
      }
    }
    return new ImageImpl(pixels);
  }


  /**
   * Clamp the given value: if it's greater than 255, return 255; if it's less than 0, return 0;
   * other wise, return itself.
   *
   * @param value The value to be clamped
   * @return clamped value
   */
  public static double clamp(double value) {
    if (value < 0) {
      return 0;
    }
    if (value > 255) {
      return 255;
    } else {
      return value;
    }
  }

  /**
   * Read an image file in the PNG or jpg format and return it as an {@code IImage}.
   *
   * @param filename the path of the file.
   * @return the {@code IImage} format of the given file.
   */

  public static IImage imageFromPNGAndJPG(String filename) {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new FileInputStream(filename));
    } catch (IOException e) {
      e.printStackTrace();
    }
    IPixel[][] pixels = new Pixel[img.getHeight()][img.getWidth()];
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        Color c = new Color(img.getRGB(j, i));
        pixels[i][j] = new Pixel(c.getRed(), c.getGreen(), c.getBlue());
      }
    }
    return new ImageImpl(pixels);
  }

  /**
   * Converts an IImage to a buffered image.
   *
   * @param img IImage to convert
   * @return IImage converted to an image
   */
  public static BufferedImage getImageFromIImage(IImage img) {
    IPixel[][] pixels = img.getPixels();
    BufferedImage bufferedImage = new BufferedImage(img.getWidth(), img.getHeight(),
        BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        IPixel pixel = pixels[i][j];
        bufferedImage.setRGB(j, i,
            new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue()).getRGB());
      }
    }
    return bufferedImage;
  }

  /**
   * Returns a blank image for when there are no layers displayed in the view.
   *
   * @return Blank image with text displayed on it.
   */
  public static Image getImageForBlank() {
    Image img = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
    Graphics graphic = img.getGraphics();
    Font font = new Font("TimesNewRoman", Font.TRUETYPE_FONT, 18);

    graphic.setFont(font);
    graphic.drawString("No Visible Layer. Please upload a layer or make an existing layer visible.",
        10, 300);
    return img;
  }

}


