package model;

import static org.junit.Assert.assertEquals;

import model.colortransformation.IColorTransformation;
import model.colortransformation.MonochromeTransformation;
import model.colortransformation.SepiaTransformation;
import model.filtering.BlurringFilter;
import model.filtering.IFilter;
import model.filtering.SharpeningFilter;
import org.junit.Test;

/**
 * Tests for methods in the layer class.
 */
public class LayerTest {

  IImage rainbow = MyUtil.imageFromPPM("rainbow.ppm");
  IImage rainbowBlur = MyUtil.imageFromPNGAndJPG("rainbowBlur.jpg");
  IImage rainbowSepia = MyUtil.imageFromPNGAndJPG("rainbowsepia.png");
  Layer rainbowLayer = new Layer(rainbow);
  Layer rainbowSepiaLayer = new Layer(rainbowSepia, "sepia");
  Layer rainbowBlurLayer = new Layer(false, rainbowBlur, "blur");

  @Test
  public void getVisibility() {
    assertEquals(true, rainbowLayer.getVisibility());
    rainbowLayer.setVisibility(false);
    assertEquals(false, rainbowLayer.getVisibility());
    assertEquals(false, rainbowBlurLayer.getVisibility());

  }

  @Test
  public void setVisibility() {
    assertEquals(true, rainbowSepiaLayer.getVisibility());
    rainbowSepiaLayer.setVisibility(false);
    assertEquals(false, rainbowSepiaLayer.getVisibility());
  }

  @Test
  public void getImage() {
    assertEquals(rainbow, rainbowLayer.getImage());
    assertEquals(rainbowBlur, rainbowBlurLayer.getImage());
    assertEquals(rainbowSepia, rainbowSepiaLayer.getImage());
  }

  @Test
  public void applyEffect() {
    IFilter sharpen = new SharpeningFilter();
    assertEquals(254, rainbowLayer.getImage().getPixels()[0][0].getRed());
    rainbowLayer.applyEffect(sharpen);
    assertEquals(255, rainbowLayer.getImage().getPixels()[0][0].getRed());
    IFilter blur = new BlurringFilter();
    rainbowLayer.applyEffect(blur);
    assertEquals(143, rainbowLayer.getImage().getPixels()[0][0].getRed());
    IColorTransformation sepia = new SepiaTransformation();
    rainbowLayer.applyEffect(sepia);
    assertEquals(56, rainbowLayer.getImage().getPixels()[0][0].getRed());
    IColorTransformation greyscale = new MonochromeTransformation();
    rainbowLayer.applyEffect(greyscale);
    assertEquals(50, rainbowLayer.getImage().getPixels()[0][0].getRed());
  }

  @Test
  public void getName() {
    assertEquals("", rainbowLayer.getName());
    assertEquals("sepia", rainbowSepiaLayer.getName());
    assertEquals("blur", rainbowBlurLayer.getName());
  }

  @Test
  public void setName() {
    assertEquals("", rainbowLayer.getName());
    rainbowLayer.setName("rainbow");
    assertEquals("rainbow", rainbowLayer.getName());
  }

  @Test
  public void downscale() {
    assertEquals(720, rainbowBlurLayer.getImage().getHeight());
    assertEquals(1280, rainbowBlurLayer.getImage().getWidth());
    rainbowBlurLayer.layerDownscale(100, 10);
    assertEquals(10, rainbowBlurLayer.getImage().getHeight());
    assertEquals(100, rainbowBlurLayer.getImage().getWidth());
  }

  @Test
  public void mosaic() {
    IImage coffeePic = MyUtil.imageFromPNGAndJPG("res/coffee.jpg");
    Layer coffee = new Layer(coffeePic);
    IPixel[][] pixels = coffee.getImage().getPixels();
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
    assertEquals(543, coffee.getImage().getWidth());
    assertEquals(803, coffee.getImage().getHeight());
    coffee.layerMosaic(1000);
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
    assertEquals(543, coffee.getImage().getWidth());
    assertEquals(803, coffee.getImage().getHeight());
  }
}