package view;

import java.io.IOException;

/**
 * View for an image processing application.
 */
public class ImageProcessingView implements IImageProcessingView {

  private final Appendable ap;

  /**
   * Constructs a view with the given appendable.
   *
   * @param ap Appendable to feed into the view
   */
  public ImageProcessingView(Appendable ap) {
    this.ap = ap;
  }

  @Override
  public void renderMessage(String message) {
    try {
      ap.append(message);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
