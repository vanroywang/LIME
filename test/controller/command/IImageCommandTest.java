package controller.command;

import static org.junit.Assert.assertEquals;

import view.IImageProcessingView;
import view.ImageProcessingView;
import controller.IImageProcessingController2;
import controller.MultiLayerController;
import java.io.StringReader;
import java.util.List;
import model.IImage;
import model.IMultilayerModel;
import model.IPixel;
import model.Layer;
import model.MultilayerModel;
import model.MyUtil;
import org.junit.Test;

/**
 * Tests for the run method of various class operations.
 */
public class IImageCommandTest {

  IMultilayerModel model = new MultilayerModel();
  IImage rainbow = MyUtil.imageFromPPM("rainbow.ppm");
  IImage rainbowBlur = MyUtil.imageFromPNGAndJPG("rainbowBlur.jpg");
  Appendable ap = new StringBuilder();
  Readable rd = new StringReader("");
  IImageProcessingController2 controller = new MultiLayerController(model, ap, rd);
  IImageProcessingView view = new ImageProcessingView(ap);

  @Test
  public void runBlur() {
    model.addLayer(rainbow, "Rainbow");
    model.setCurrentLayer(0);
    IPixel[][] pixels = model.getImage().getPixels();
    IPixel pixel = pixels[0][0];
    assertEquals(254, pixel.getRed());
    IImageCommand blur = new Blur();
    blur.run(controller, model);
    IPixel[][] pixels2 = model.getImage().getPixels();
    IPixel pixel2 = pixels2[0][0];
    assertEquals(143, pixel2.getRed());
    assertEquals("Current image is blurred\n" , ap.toString());
  }

  @Test
  public void runCurrent() {
    model.addLayer(rainbow, "Rainbow");
    model.addLayer(rainbowBlur, "Rainbow Blur");
    IImageCommand current = new Current(0);
    current.run(controller, model);
    assertEquals(0, model.getCurrentIndex());
    IImageCommand newCurrent = new Current(1);
    newCurrent.run(controller, model);
    assertEquals(1, model.getCurrentIndex());
    assertEquals("Current layer is set to 0\nCurrent layer is set to 1\n" , ap.toString());
  }

  @Test
  public void runGreyScale() {
    model.addLayer(rainbow, "Rainbow");
    model.setCurrentLayer(0);
    IPixel[][] pixels = model.getImage().getPixels();
    IPixel pixel = pixels[0][0];
    assertEquals(254, pixel.getRed());
    IImageCommand greyscale = new Greyscale();
    greyscale.run(controller, model);
    IPixel[][] pixels2 = model.getImage().getPixels();
    IPixel pixel2 = pixels2[0][0];
    assertEquals(54, pixel2.getRed());
    assertEquals("Current image is turned into a greyscale one.\n" , ap.toString());
  }

  @Test
  public void runInvisible() {
    model.addLayer(rainbow, "Rainbow");
    model.addLayer(rainbowBlur, "Rainbow Blur");
    assertEquals(rainbow, model.getImage());
    IImageCommand invisible = new Invisible(0);
    invisible.run(controller, model);
    assertEquals(rainbowBlur, model.getImage());
    assertEquals("Layer 0 is now invisible.\n" , ap.toString());
  }

  @Test
  public void runLoad() {
    IImageCommand load = new Load("rainbow.ppm");
    load.run(controller, model);
    assertEquals(1, model.getLayers().size());
    assertEquals("Successfully loaded rainbow.ppm at layer number [ 0 ].\n" , ap.toString());
  }

  @Test
  public void runNameLayer() {
    model.addLayer(rainbow, "rainbow");
    IImageCommand rename = new NameLayer(0, "bow");
    rename.run(controller, model);
    List<Layer> layers = model.getLayers();
    assertEquals("bow", layers.get(0).getName());
    assertEquals("Layer 0 is named as bow\n" , ap.toString());
  }

  @Test
  public void runRemove() {
    model.addLayer(rainbow, "rainbow");
    model.addLayer(rainbowBlur, "rainbowBlur");
    assertEquals(2, model.getLayers().size());
    IImageCommand remove = new Remove(0);
    remove.run(controller, model);
    assertEquals(1, model.getLayers().size());
    assertEquals("Layer 0 is removed.\n" , ap.toString());
  }

  @Test
  public void runSave() {
    model.addLayer(rainbow, "rainbow");
    model.addLayer(rainbowBlur, "rainbowBlur");
    IImageCommand save = new Save("newRainbow.ppm");
    save.run(controller, model);
    // file created called "newRainbow.ppm"
    assertEquals("Image saved at newRainbow.ppm\n" , ap.toString());
  }

  @Test
  public void runSaveMultiple() {
    model.addLayer(rainbow, "rainbow");
    model.addLayer(rainbowBlur, "rainbowBlur");
    IImageCommand save = new SaveMultiple("newRainbow", "jpg");
    save.run(controller, model);
    // new directory created called "newRainbow" and new text file inside directory created
    // called "newRainbowPath.txt", rainbow.jpg and rainbowBlur.jpg inside directory
    assertEquals("Images from all layers are created at newRainbow\n" , ap.toString());
  }

  @Test
  public void runSepia() {
    model.addLayer(rainbow, "Rainbow");
    model.setCurrentLayer(0);
    IPixel[][] pixels = model.getImage().getPixels();
    IPixel pixel = pixels[0][0];
    assertEquals(254, pixel.getRed());
    IImageCommand sepia = new Sepia();
    sepia.run(controller, model);
    IPixel[][] pixels2 = model.getImage().getPixels();
    IPixel pixel2 = pixels2[0][0];
    assertEquals(100, pixel2.getRed());
    assertEquals("The current layer has been turned into sepia-toned\n" , ap.toString());
  }

  @Test
  public void runSharpen() {
    model.addLayer(rainbow, "Rainbow");
    model.setCurrentLayer(0);
    IPixel[][] pixels = model.getImage().getPixels();
    IPixel pixel = pixels[0][0];
    assertEquals(254, pixel.getRed());
    IImageCommand sharpen = new Sharpen();
    sharpen.run(controller, model);
    IPixel[][] pixels2 = model.getImage().getPixels();
    IPixel pixel2 = pixels2[0][0];
    assertEquals(255, pixel2.getRed());
    assertEquals("The current layer has been sharpened\n" , ap.toString());
  }

  @Test
  public void runVisible() {
    model.addLayer(rainbow, "Rainbow");
    model.addLayer(rainbowBlur, "Rainbow Blur");
    assertEquals(rainbow, model.getImage());
    model.setVisibility(0, false);
    assertEquals(rainbowBlur, model.getImage());
    IImageCommand visible = new Visible(0);
    visible.run(controller, model);
    assertEquals(rainbow, model.getImage());
    assertEquals("Layer 0 is now visible.\n" , ap.toString());
  }
}