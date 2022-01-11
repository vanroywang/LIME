package model.filtering;

/**
 *                    ┌ -1/8 -1/8 -1/8 -1/8 -1/8 ┐
 * This filter uses a │ -1/8  1/4  1/4  1/4 -1/8 │ kernel to accentuate the edges between high
 *  contrast regions. │ -1/8  1/4   1   1/4 -1/8 │
 *                    │ -1/8  1/4  1/4  1/4 -1/8 │
 *                    └ -1/8 -1/8 -1/8 -1/8 -1/8 ┘
 */
public class SharpeningFilter extends AbstractFilter {

  /**
   * Constructs a blurring filter object.
   */
  public SharpeningFilter() {
    this.kernel = new double[][]{
        {- 0.125, - 0.125, - 0.125, - 0.125, - 0.125},
        {- 0.125, 0.25, 0.25, 0.25, - 0.125},
        {- 0.125, 0.25, 1, 0.25, - 0.125},
        {- 0.125, 0.25, 0.25, 0.25, - 0.125},
        {- 0.125, - 0.125, - 0.125, - 0.125, - 0.125}};
    this.emptyLayerNeeded = 2;
  }

}
