package model.filtering;

/**
 *                    ┌ 1/16 1/8 1/16 ┐
 * This filter uses a │ 1/8  1/4  1/8 │ kernel to make the image looks blurred.
 *                    └ 1/16 1/8 1/16 ┘
 */
public class BlurringFilter extends AbstractFilter {

  /**
   * Constructs a blurring filter object.
   */
  public BlurringFilter() {
    this.kernel = new double[][]{
        {0.0625, 0.125, 0.0625},
        {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}};
    this.emptyLayerNeeded = 1;
  }
}




