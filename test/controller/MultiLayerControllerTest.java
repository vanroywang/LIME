package controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import model.IImage;
import model.IMultilayerModel;
import model.ImageImpl;
import model.MultilayerMock;
import model.MultilayerModel;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class for multilayer controller.
 */
public class MultiLayerControllerTest {
  IMultilayerModel fakeModel;
  IMultilayerModel model;
  IImageProcessingController2 controller;
  Appendable ap;
  Readable rd;
  IImage image;

  @Before
  public void init() {
    fakeModel = new MultilayerMock();
    model = new MultilayerModel();
    ap = new StringBuilder();
    image = new ImageImpl(4,5);
  }

  @Test
  public void loadToModelFromFile() {
    rd = new StringReader( "load rainbow.ppm" );
    controller = new MultiLayerController(model, ap, rd);
    controller.runProcessor();
    assertEquals("Please type in the path to script, or start by loading an image. "
        + "Type in command for list of commands\n"
        + "Successfully loaded rainbow.ppm at layer number [ 0 ].\n", ap.toString());
  }


  @Test
  public void loadToModelFromImage() {
    rd = new StringReader( "" );
    controller = new MultiLayerController(fakeModel, ap, rd);
    controller.loadToModelFromImage(image);
    assertEquals(String.format("Image [%s] with width 5 and height 4 is loaded.\n",
        image.toString()), fakeModel.toString());
  }


  @Test
  public void testSendingSepia() {
    rd = new StringReader( "sepia" );
    controller = new MultiLayerController(fakeModel, ap, rd);
    controller.runProcessor();
    assertEquals("sepia effect.\n", fakeModel.toString());
  }

  @Test
  public void testSendingGreyscale() {
    rd = new StringReader( "greyscale" );
    controller = new MultiLayerController(fakeModel, ap, rd);
    controller.runProcessor();
    assertEquals("monochrome effect.\n", fakeModel.toString());
  }

  @Test
  public void testSendingBlur() {
    rd = new StringReader( "blur" );
    controller = new MultiLayerController(fakeModel, ap, rd);
    controller.runProcessor();
    assertEquals("blurred.\n", fakeModel.toString());
  }

  @Test
  public void testSendingSharpen() {
    rd = new StringReader( "sharpen" );
    controller = new MultiLayerController(fakeModel, ap, rd);
    controller.runProcessor();
    assertEquals("sharpened.\n", fakeModel.toString());
  }

  @Test
  public void testSettingCurrentLayer() {
    rd = new StringReader( "current 0" );
    controller = new MultiLayerController(fakeModel, ap, rd);
    controller.runProcessor();
    assertEquals("Layer number 0 is sat as current layer.\n", fakeModel.toString());
  }

  @Test
  public void testSettingVisible() {
    rd = new StringReader( "visible 0" );
    controller = new MultiLayerController(fakeModel, ap, rd);
    controller.runProcessor();
    assertEquals("The visibility of layer number 0 is sat as true.\n", fakeModel.toString());
  }

  @Test
  public void testSettingInvisible() {
    rd = new StringReader( "invisible 1" );
    controller = new MultiLayerController(fakeModel, ap, rd);
    controller.runProcessor();
    assertEquals("The visibility of layer number 1 is sat as false.\n", fakeModel.toString());
  }

  @Test
  public void testChangingName() {
    rd = new StringReader( "nameLayer 0 haha" );
    controller = new MultiLayerController(fakeModel, ap, rd);
    controller.runProcessor();
    assertEquals("Layer number 0 is named as haha.\n", fakeModel.toString());
  }

  @Test
  public void testRemovingLayer() {
    rd = new StringReader( "remove 1" );
    controller = new MultiLayerController(fakeModel, ap, rd);
    controller.runProcessor();
    assertEquals("Layer number 1 is removed.\n", fakeModel.toString());
  }

  @Test
  public void write() {
    rd = new StringReader( "" );
    controller = new MultiLayerController(fakeModel, ap, rd);
    try {
      controller.write("Hello there.");
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("Hello there.\n", ap.toString());
  }
}