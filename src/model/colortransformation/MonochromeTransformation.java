package model.colortransformation;

/**
 * This color transformation turns an image into a greyscale image, using the following color
 * transformation: r′=g′=b′=0.2126r+0.7152g+0.0722b.
 */
public class MonochromeTransformation extends AbstractColorTransformation {

  /**
   * Constructs a {@code monochromeTransformation}.
   */
  public MonochromeTransformation() {
    this.transformationMatrix = new double[][]{
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}};
  }
}
