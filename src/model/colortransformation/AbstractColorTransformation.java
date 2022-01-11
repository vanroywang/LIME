package model.colortransformation;

import model.IImage;
import model.IPixel;
import model.ImageImpl;
import model.MyUtil;
import model.Pixel;

/**
 * The abstract class for color transformations. It provides the utility helper to clamp values.
 */
public abstract class AbstractColorTransformation implements IColorTransformation {

  protected double[][] transformationMatrix;

  @Override
  public IImage apply(IImage input) {
    if (input == null) {
      throw new IllegalArgumentException("Input image cannot be null.");
    }
    IPixel[][] inputPixels = input.getPixels();
    IPixel[][] outputPixels = new IPixel[input.getHeight()][input.getWidth()];
    for (int i = 0; i < input.getHeight(); i++) {
      for (int j = 0; j < input.getWidth(); j++) {
        //transformation matrix [3 * 3] * rgb in a [3 * 1] matrix
        IPixel pixel = inputPixels[i][j];
        double[][] channelValues = matrixMultiplication(transformationMatrix,
            new double[][]{{pixel.getRed()}, {pixel.getGreen()}, {pixel.getBlue()}});
        double redVal = MyUtil.clamp(channelValues[0][0]);
        double greenVal = MyUtil.clamp(channelValues[1][0]);
        double blueVal = MyUtil.clamp(channelValues[2][0]);
        outputPixels[i][j] = new Pixel((int) Math.round(redVal), (int) Math.round(greenVal),
            (int) Math.round(blueVal));
      }
    }
    return new ImageImpl(outputPixels);
  }

  protected double[][] matrixMultiplication(double[][] matrix1, double[][] matrix2) {
    if (matrix1[0].length != matrix2.length) {
      throw new IllegalArgumentException("Invalid matrix multiplication.");
    }
    double[][] out = new double[matrix1.length][matrix2[0].length];
    for (int i = 0; i < out.length; i++) {
      for (int j = 0; j < out[0].length; j++) {
        double cell = 0;
        for (int k = 0; k < matrix2.length; k++) {
          cell += matrix1[i][k] * matrix2[k][j];
        }
        out[i][j] = cell;
      }
    }
    return out;
  }


}
