package controller;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.io.IOException;
import model.IImage;
import model.IImageProcessingModel;
import model.IPixel;
import model.ImageProcessingModelV1;
import model.MyUtil;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for ImageProcessingController.
 */
public class ImageProcessingControllerV1Test {

  IImageProcessingModel model;
  IImageProcessingController controller;
  IImage image1;

  @Before
  public void init() {
    model = new ImageProcessingModelV1();
    controller = new ImageProcessingControllerV1(model);
    //Please be advised that this color combination is very spicy for your eyes.
    //magenta: (255, 0, 255); pink: (255, 175, 175)
    image1 = MyUtil.creatCheckerboard(8, 100, Color.magenta, Color.PINK);
  }

  /**
   * Uses a predefined ppm object to see if the controller feed file to model correctly.
   * This is the file's content:
   * P3
   * 4 4
   * 255
   * 0  0  0   100 0  0       0  0  0    255   0 255
   * 0  0  0    0 255 175     0  0  0     0    0  0
   * 0  0  0    0  0  0       0 15 175    0    0  0
   * 255 0 255  0  0  0       0  0  0    255  255 255.
   */
  @Test
  public void testLoadToModelFromFile() {
    controller.loadToModelFromFile("sample2.ppm");
    IImage image = model.getImage();
    assertEquals(4, image.getHeight());
    assertEquals(4, image.getWidth());

    IPixel[][] pixels = image.getPixels();

    assertEquals(0, pixels[0][0].getRed());
    assertEquals(0, pixels[0][0].getGreen());
    assertEquals(0, pixels[0][0].getBlue());

    assertEquals(255, pixels[3][3].getRed());
    assertEquals(255, pixels[3][3].getGreen());
    assertEquals(255, pixels[3][3].getBlue());

    assertEquals(0, pixels[1][1].getRed());
    assertEquals(255, pixels[1][1].getGreen());
    assertEquals(175, pixels[1][1].getBlue());
  }


  @Test (expected =  IllegalArgumentException.class)
  public void testNullModelInitialize() {
    IImageProcessingController c = new ImageProcessingControllerV1(null);
  }

  @Test (expected =  IllegalArgumentException.class)
  public void testLoadToModelFromFileNotFound() {
    controller.loadToModelFromFile("sample3.ppm");//there's no sample3
  }

  @Test (expected =  IllegalArgumentException.class)
  public void testLoadToModelFromFileNull() {
    controller.loadToModelFromFile(null);
  }

  @Test (expected =  IllegalArgumentException.class)
  public void testLoadToModelFromFileEmpty() {
    controller.loadToModelFromFile("");
  }

  @Test
  public void testLoadToModelFromImage() {
    controller.loadToModelFromImage(image1);
    IImage im = model.getImage();
    IPixel[][] pixels = im.getPixels();
    assertEquals(800, im.getHeight());
    assertEquals(800, im.getHeight());
    //top left
    assertEquals(255, pixels[0][0].getRed());
    assertEquals(0, pixels[0][0].getGreen());
    assertEquals(255, pixels[0][0].getBlue());
    //bottom right
    assertEquals(255, pixels[799][799].getRed());
    assertEquals(0, pixels[799][799].getGreen());
    assertEquals(255, pixels[799][799].getBlue());
    //top right
    assertEquals(255, pixels[0][799].getRed());
    assertEquals(175, pixels[0][799].getGreen());
    assertEquals(175, pixels[0][799].getBlue());
    //bottom left
    assertEquals(255, pixels[799][0].getRed());
    assertEquals(175, pixels[799][0].getGreen());
    assertEquals(175, pixels[799][0].getBlue());
  }

  @Test (expected =  NullPointerException.class)
  public void testLoadToModelFromImageNull() {
    controller.loadToModelFromImage(null);
  }

  @Test(expected =  IOException.class)
  public void saveAtEmpty() throws IOException {
    controller.loadToModelFromImage(image1);
    controller.save("");
  }

  @Test(expected =  IOException.class)
  public void saveAtNonPPM() throws IOException {
    controller.loadToModelFromImage(image1);
    controller.save("image.abc");
  }

}