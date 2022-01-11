package view;

/**
 * Interface for the view of an image processing application.
 */
public interface IImageProcessingView {

  /**
   * Appends the given message to the appendable and displays it.
   *
   * @param message Message to append
   */
  void renderMessage(String message);
}
