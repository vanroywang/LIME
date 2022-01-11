package model;

/**
 * This is an implementation of {@code IPixel} interface. It represents a pixel on an image.
 */
public class Pixel implements IPixel {

  private final int red;
  private final int green;
  private final int blue;

  /**
   * Creates a {@code Pixel} with the given red, green, and blue value.
   *
   * @param red   the value of this pixel's red channel
   * @param green the value of this pixel's green channel
   * @param blue  the value of this pixel's blue channel
   */
  public Pixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  @Override
  public int getRed() {
    return red;
  }

  @Override
  public int getGreen() {
    return green;
  }

  @Override
  public int getBlue() {
    return blue;
  }
}
