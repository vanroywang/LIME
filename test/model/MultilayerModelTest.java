package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

/**
 * Tests for the methods in the multilayer model class.
 */
public class MultilayerModelTest {

  IImage rainbow = MyUtil.imageFromPPM("rainbow.ppm");
  IImage rainbowBlur = MyUtil.imageFromPNGAndJPG("rainbowblur.jpg");
  IImage rainbowSepia = MyUtil.imageFromPNGAndJPG("rainbowsepia.png");
  IImage coffee = MyUtil.imageFromPNGAndJPG("res/coffee.jpg");
  ArrayList<Layer> layers = new ArrayList<>(Arrays.asList(new Layer(rainbow, "Rainbow"),
      new Layer(rainbowBlur, "blur"), new Layer(rainbowSepia, "sepia")));
  IMultilayerModel model = new MultilayerModel(layers, 0);
  IMultilayerModel model2 = new MultilayerModel();

  @Test
  public void loadImage() {
    model2.loadImage(rainbow);
    assertEquals(1, model2.getLayers().size());
    model2.loadImage(rainbowBlur);
    assertEquals(2, model2.getLayers().size());
  }

  @Test
  public void blur() {
    IPixel[][] pixels = model.getImage().getPixels();
    IPixel pixel = pixels[0][0];
    assertEquals(254, pixel.getRed());
    model.blur();
    IPixel[][] pixels2 = model.getImage().getPixels();
    IPixel pixel2 = pixels2[0][0];
    assertEquals(143, pixel2.getRed());
  }

  @Test
  public void sharpen() {
    IPixel[][] pixels = model.getImage().getPixels();
    IPixel pixel = pixels[0][0];
    assertEquals(254, pixel.getRed());
    model.sharpen();
    IPixel[][] pixels2 = model.getImage().getPixels();
    IPixel pixel2 = pixels2[0][0];
    assertEquals(255, pixel2.getRed());
  }

  @Test
  public void monochrome() {
    IPixel[][] pixels = model.getImage().getPixels();
    IPixel pixel = pixels[0][0];
    assertEquals(254, pixel.getRed());
    model.monochrome();
    IPixel[][] pixels2 = model.getImage().getPixels();
    IPixel pixel2 = pixels2[0][0];
    assertEquals(54, pixel2.getRed());
  }

  @Test
  public void sepia() {
    IPixel[][] pixels = model.getImage().getPixels();
    IPixel pixel = pixels[0][0];
    assertEquals(254, pixel.getRed());
    model.sepia();
    IPixel[][] pixels2 = model.getImage().getPixels();
    IPixel pixel2 = pixels2[0][0];
    assertEquals(100, pixel2.getRed());
  }

  @Test
  public void downScale() {
    model2.loadImage(rainbow);
    model2.loadImage(rainbowBlur);
    model2.loadImage(rainbowSepia);
    assertEquals(720, model2.getLayers().get(0).getImage().getHeight());
    assertEquals(1280, model2.getLayers().get(0).getImage().getWidth());
    assertEquals(720, model2.getLayers().get(1).getImage().getHeight());
    assertEquals(1280, model2.getLayers().get(1).getImage().getWidth());
    assertEquals(720, model2.getLayers().get(2).getImage().getHeight());
    assertEquals(1280, model2.getLayers().get(2).getImage().getWidth());
    model2.setCurrentLayer(0);
    model2.downscale(50, 100);
    assertEquals(100, model2.getLayers().get(0).getImage().getHeight());
    assertEquals(50, model2.getLayers().get(0).getImage().getWidth());
    assertEquals(100, model2.getLayers().get(1).getImage().getHeight());
    assertEquals(50, model2.getLayers().get(1).getImage().getWidth());
    assertEquals(100, model2.getLayers().get(2).getImage().getHeight());
    assertEquals(50, model2.getLayers().get(2).getImage().getWidth());
  }

  @Test
  public void mosaic() {
    IImage coffee = MyUtil.imageFromPNGAndJPG("res/coffee.jpg");
    IMultilayerModel mosaicModel = new MultilayerModel();
    mosaicModel.loadImage(coffee);
    IPixel[][] pixels = mosaicModel.getImage().getPixels();
    assertEquals(4, pixels[0][0].getRed());
    assertEquals(6, pixels[0][0].getGreen());
    assertEquals(5, pixels[0][0].getBlue());
    assertEquals(4, pixels[0][1].getRed());
    assertEquals(6, pixels[0][1].getGreen());
    assertEquals(5, pixels[0][1].getBlue());
    assertEquals(4, pixels[1][0].getRed());
    assertEquals(6, pixels[1][0].getGreen());
    assertEquals(5, pixels[1][0].getBlue());
    assertEquals(4, pixels[1][1].getRed());
    assertEquals(6, pixels[1][1].getGreen());
    assertEquals(5, pixels[1][1].getBlue());
    assertEquals(543, mosaicModel.getImage().getWidth());
    assertEquals(803, mosaicModel.getImage().getHeight());
    mosaicModel.mosaic(1000);
    assertEquals(4, pixels[0][0].getRed());
    assertEquals(6, pixels[0][0].getGreen());
    assertEquals(5, pixels[0][0].getBlue());
    assertEquals(4, pixels[0][1].getRed());
    assertEquals(6, pixels[0][1].getGreen());
    assertEquals(5, pixels[0][1].getBlue());
    assertEquals(4, pixels[1][0].getRed());
    assertEquals(6, pixels[1][0].getGreen());
    assertEquals(5, pixels[1][0].getBlue());
    assertEquals(4, pixels[1][1].getRed());
    assertEquals(6, pixels[1][1].getGreen());
    assertEquals(5, pixels[1][1].getBlue());
    assertEquals(543, mosaicModel.getImage().getWidth());
    assertEquals(803, mosaicModel.getImage().getHeight());

  }

  @Test
  public void getImage() {
    model.addLayer(rainbow, "Rainbow");
    assertEquals(rainbow, model.getImage());
    model.addLayer(rainbowBlur, "Rainbow");
    assertEquals(rainbow, model.getImage());
  }

  @Test
  public void setVisibility() {
    assertEquals(true, model.getLayers().get(0).getVisibility());
    model.setVisibility(0, false);
    assertEquals(false, model.getLayers().get(0).getVisibility());
    model.setVisibility(0, true);
    assertEquals(true, model.getLayers().get(0).getVisibility());
  }

  @Test
  public void setCurrentLayer() {
    assertEquals(0, model.getCurrentIndex());
    model.setCurrentLayer(1);
    assertEquals(1, model.getCurrentIndex());
  }

  @Test
  public void getLayers() {
    assertEquals(3, model.getLayers().size());
    assertEquals(0, model2.getLayers().size());
  }

  @Test
  public void nameLayer() {
    assertEquals("Rainbow", model.getLayers().get(0).getName());
    model.nameLayer(0, "hello");
    assertEquals("hello", model.getLayers().get(0).getName());
    assertEquals("blur", model.getLayers().get(1).getName());
    model.nameLayer(1, "bye");
    assertEquals("bye", model.getLayers().get(1).getName());
  }

  @Test
  public void removeLayer() {
    assertEquals(3, model.getLayers().size());
    assertEquals("Rainbow", model.getLayers().get(0).getName());
    assertEquals("blur", model.getLayers().get(1).getName());
    model.removeLayer(1);
    assertEquals(2, model.getLayers().size());
    assertEquals("Rainbow", model.getLayers().get(0).getName());
    model.removeLayer(0);
    assertEquals(1, model.getLayers().size());
  }

  @Test
  public void getCurrentIndex() {
    model.setCurrentLayer(1);
    assertEquals(1, model.getCurrentIndex());
    model.setCurrentLayer(0);
    assertEquals(0, model.getCurrentIndex());
  }

  @Test
  public void addLayer() {
    assertEquals(3, model.getLayers().size());
    model.addLayer(rainbowSepia, "Sepia");
    assertEquals(4, model.getLayers().size());
    model.getLayers().contains(rainbowSepia);
  }
}