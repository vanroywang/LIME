package model;

/**
 * Interface for the operation apply for class operations.
 */
public interface IEffect {

  /**
   * Applies a certain opertaion on the given image.
   *
   * @param input Image to apply the operation to
   * @return The image post-apply
   */
  IImage apply(IImage input);
}
