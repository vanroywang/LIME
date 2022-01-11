package model;


import static org.junit.Assert.assertEquals;

import java.awt.Color;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for ImageImplProcessingModelV1.
 */
public class ImageImplProcessingModelV1Test {

  IImageProcessingModel model;
  IImage image1;
  IImage image2;

  @Before
  public void init() {
    model = new ImageProcessingModelV1();
    image1 = MyUtil.creatCheckerboard(2, 10, Color.BLACK, Color.WHITE);
    //                                                   (0, 0, 255) (255, 0, 0)
    image2 = MyUtil.creatCheckerboard(2, 1, Color.BLUE, Color.RED);
  }

  @Test
  public void testLoadImage() {

    model.loadImage(image1);
    assertEquals(image1, model.getImage());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadNullImage() {
    assertEquals(null, model.getImage());
    model.loadImage(null);
  }

  @Test
  public void testGetImage() {
    model.loadImage(image1);
    IImage image = model.getImage();
    assertEquals(image, image1);
  }


  @Test
  public void testCheckerBoard() {
    IPixel[][] pixels = image1.getPixels();
    //corners
    IPixel topLeft = pixels[0][0];
    IPixel topRight = pixels[0][19];
    IPixel bottomLeft = pixels[19][0];
    IPixel bottomRight = pixels[19][19];
    //centers
    IPixel midTopLeft = pixels[9][9];
    IPixel midTopRight = pixels[9][10];
    IPixel midBottomLeft = pixels[10][9];
    IPixel midBottomRight = pixels[10][10];

    assertEquals(20, image1.getHeight());
    assertEquals(20, image1.getWidth());

    //topLeft
    assertEquals(0, topLeft.getRed());
    assertEquals(0, topLeft.getGreen());
    assertEquals(0, topLeft.getBlue());

    //topRight
    assertEquals(255, topRight.getRed());
    assertEquals(255, topRight.getGreen());
    assertEquals(255, topRight.getBlue());

    //bottomLeft
    assertEquals(255, bottomLeft.getRed());
    assertEquals(255, bottomLeft.getGreen());
    assertEquals(255, bottomLeft.getBlue());

    //bottomRight
    assertEquals(0, bottomRight.getRed());
    assertEquals(0, bottomRight.getGreen());
    assertEquals(0, bottomRight.getBlue());

    //midTopLeft
    assertEquals(0, midTopLeft.getRed());
    assertEquals(0, midTopLeft.getGreen());
    assertEquals(0, midTopLeft.getBlue());

    //midTopRight
    assertEquals(255, midTopRight.getRed());
    assertEquals(255, midTopRight.getGreen());
    assertEquals(255, midTopRight.getBlue());

    //midTopRight
    assertEquals(255, midBottomLeft.getRed());
    assertEquals(255, midBottomLeft.getGreen());
    assertEquals(255, midBottomLeft.getBlue());

    //midBottomRight
    assertEquals(0, midBottomRight.getRed());
    assertEquals(0, midBottomRight.getGreen());
    assertEquals(0, midBottomRight.getBlue());
  }

  @org.junit.Test
  public void testSharpen() {
    model.loadImage(image2);
    model.sharpen();
    IImage output = model.getImage();
    IPixel[][] pixels = output.getPixels();
    assertEquals(2, pixels.length);
    assertEquals(2, pixels[0].length);

    IPixel pixel1 = pixels[0][0];
    IPixel pixel2 = pixels[0][1];
    IPixel pixel3 = pixels[1][0];
    IPixel pixel4 = pixels[1][1];

    assertEquals(128, pixel1.getRed());
    assertEquals(0, pixel1.getGreen());
    assertEquals(255, pixel1.getBlue());

    assertEquals(255, pixel2.getRed());
    assertEquals(0, pixel2.getGreen());
    assertEquals(128, pixel2.getBlue());

    assertEquals(255, pixel3.getRed());
    assertEquals(0, pixel3.getGreen());
    assertEquals(128, pixel3.getBlue());

    assertEquals(128, pixel4.getRed());
    assertEquals(0, pixel4.getGreen());
    assertEquals(255, pixel4.getBlue());
  }

  @org.junit.Test
  public void testDoubleSharpen() {
    model.loadImage(image2);
    model.sharpen();
    model.sharpen();
    IImage output = model.getImage();
    IPixel[][] pixels = output.getPixels();
    assertEquals(2, pixels.length);
    assertEquals(2, pixels[0].length);

    IPixel pixel1 = pixels[0][0];
    IPixel pixel2 = pixels[0][1];
    IPixel pixel3 = pixels[1][0];
    IPixel pixel4 = pixels[1][1];

    assertEquals(255, pixel1.getRed());
    assertEquals(0, pixel1.getGreen());
    assertEquals(255, pixel1.getBlue());

    assertEquals(255, pixel2.getRed());
    assertEquals(0, pixel2.getGreen());
    assertEquals(255, pixel2.getBlue());

    assertEquals(255, pixel3.getRed());
    assertEquals(0, pixel3.getGreen());
    assertEquals(255, pixel3.getBlue());

    assertEquals(255, pixel4.getRed());
    assertEquals(0, pixel4.getGreen());
    assertEquals(255, pixel4.getBlue());
  }



  @org.junit.Test
  public void testBlur() {
    model.loadImage(image2);
    model.blur();
    IImage output = model.getImage();
    IPixel[][] pixels = output.getPixels();
    assertEquals(2, pixels.length);
    assertEquals(2, pixels[0].length);

    IPixel pixel1 = pixels[0][0];
    IPixel pixel2 = pixels[0][1];
    IPixel pixel3 = pixels[1][0];
    IPixel pixel4 = pixels[1][1];

    assertEquals(64, pixel1.getRed());
    assertEquals(0, pixel1.getGreen());
    assertEquals(80, pixel1.getBlue());

    assertEquals(80, pixel2.getRed());
    assertEquals(0, pixel2.getGreen());
    assertEquals(64, pixel2.getBlue());

    assertEquals(80, pixel3.getRed());
    assertEquals(0, pixel3.getGreen());
    assertEquals(64, pixel3.getBlue());

    assertEquals(64, pixel4.getRed());
    assertEquals(0, pixel4.getGreen());
    assertEquals(80, pixel4.getBlue());
  }

  @org.junit.Test
  public void testDoubleBlur() {
    model.loadImage(image2);
    model.blur();
    model.blur();
    IImage output = model.getImage();
    IPixel[][] pixels = output.getPixels();
    assertEquals(2, pixels.length);
    assertEquals(2, pixels[0].length);

    IPixel pixel1 = pixels[0][0];
    IPixel pixel2 = pixels[0][1];
    IPixel pixel3 = pixels[1][0];
    IPixel pixel4 = pixels[1][1];

    assertEquals(40, pixel1.getRed());
    assertEquals(0, pixel1.getGreen());
    assertEquals(41, pixel1.getBlue());

    assertEquals(41, pixel2.getRed());
    assertEquals(0, pixel2.getGreen());
    assertEquals(40, pixel2.getBlue());

    assertEquals(41, pixel3.getRed());
    assertEquals(0, pixel3.getGreen());
    assertEquals(40, pixel3.getBlue());

    assertEquals(40, pixel4.getRed());
    assertEquals(0, pixel4.getGreen());
    assertEquals(41, pixel4.getBlue());
  }

  @org.junit.Test
  public void testMonochrome() {
    model.loadImage(image2);
    model.monochrome();
    IImage output = model.getImage();
    IPixel[][] pixels = output.getPixels();

    IPixel pixel1 = pixels[0][0];
    IPixel pixel2 = pixels[0][1];
    IPixel pixel3 = pixels[1][0];
    IPixel pixel4 = pixels[1][1];

    assertEquals(18, pixel1.getRed());
    assertEquals(18, pixel1.getGreen());
    assertEquals(18, pixel1.getBlue());

    assertEquals(54, pixel2.getRed());
    assertEquals(54, pixel2.getGreen());
    assertEquals(54, pixel2.getBlue());

    assertEquals(54, pixel3.getRed());
    assertEquals(54, pixel3.getGreen());
    assertEquals(54, pixel3.getBlue());

    assertEquals(18, pixel4.getRed());
    assertEquals(18, pixel4.getGreen());
    assertEquals(18, pixel4.getBlue());
  }

  @org.junit.Test
  public void testDoubleMonochrome() {
    model.loadImage(image2);
    model.monochrome();
    model.monochrome();
    IImage output = model.getImage();
    IPixel[][] pixels = output.getPixels();

    IPixel pixel1 = pixels[0][0];
    IPixel pixel2 = pixels[0][1];
    IPixel pixel3 = pixels[1][0];
    IPixel pixel4 = pixels[1][1];

    assertEquals(18, pixel1.getRed());
    assertEquals(18, pixel1.getGreen());
    assertEquals(18, pixel1.getBlue());

    assertEquals(54, pixel2.getRed());
    assertEquals(54, pixel2.getGreen());
    assertEquals(54, pixel2.getBlue());

    assertEquals(54, pixel3.getRed());
    assertEquals(54, pixel3.getGreen());
    assertEquals(54, pixel3.getBlue());

    assertEquals(18, pixel4.getRed());
    assertEquals(18, pixel4.getGreen());
    assertEquals(18, pixel4.getBlue());

  }

  @org.junit.Test
  public void testSepia() {
    model.loadImage(image2);
    model.sepia();
    IImage output = model.getImage();
    IPixel[][] pixels = output.getPixels();
    IPixel pixel1 = pixels[0][0];
    IPixel pixel2 = pixels[0][1];
    IPixel pixel3 = pixels[1][0];
    IPixel pixel4 = pixels[1][1];

    assertEquals(48, pixel1.getRed());
    assertEquals(43, pixel1.getGreen());
    assertEquals(33, pixel1.getBlue());

    assertEquals(100, pixel2.getRed());
    assertEquals(89, pixel2.getGreen());
    assertEquals(69, pixel2.getBlue());

    assertEquals(100, pixel3.getRed());
    assertEquals(89, pixel3.getGreen());
    assertEquals(69, pixel3.getBlue());

    assertEquals(48, pixel4.getRed());
    assertEquals(43, pixel4.getGreen());
    assertEquals(33, pixel4.getBlue());
  }

  @org.junit.Test
  public void testDoubleSepia() {
    model.loadImage(image2);
    model.sepia();
    model.sepia();
    IImage output = model.getImage();
    IPixel[][] pixels = output.getPixels();
    IPixel pixel1 = pixels[0][0];
    IPixel pixel2 = pixels[0][1];
    IPixel pixel3 = pixels[1][0];
    IPixel pixel4 = pixels[1][1];

    assertEquals(58, pixel1.getRed());
    assertEquals(52, pixel1.getGreen());
    assertEquals(40, pixel1.getBlue());

    assertEquals(121, pixel2.getRed());
    assertEquals(108, pixel2.getGreen());
    assertEquals(84, pixel2.getBlue());

    assertEquals(121, pixel3.getRed());
    assertEquals(108, pixel3.getGreen());
    assertEquals(84, pixel3.getBlue());

    assertEquals(58, pixel4.getRed());
    assertEquals(52, pixel4.getGreen());
    assertEquals(40, pixel4.getBlue());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNonImageBlur() {
    model.blur();
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNonImageSharpen() {
    model.sharpen();
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNonImageMonochrome() {
    model.monochrome();
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNonImageSepia() {
    model.sepia();
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNonImageGet() {
    model.getImage();
  }
}