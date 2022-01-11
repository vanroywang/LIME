package model.colortransformation;

/**
 * This color transformation turns an image into a sepia-toned one, using the following equation:
 * ┌ r'┐   ┌ 0.393 0.769 0.189 ┐  ┌ r ┐
 * │ g'│ = │ 0.349 0.686 0.168 │* │ g │
 * └ b'┘   └ 0.272 0.534 0.131 ┘  └ b ┘.
 */
public class SepiaTransformation extends AbstractColorTransformation {

  /**
   * Constructs a {@code sepiaTransformation}.
   */
  public SepiaTransformation() {
    this.transformationMatrix = new double[][]{
        {0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}};
  }


}
