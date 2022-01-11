package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.StringReader;

import model.IMultilayerModel;

import model.MultilayerModel;
import view.IImageProcessingView2;
import view.MyWindow;

/**
 * The main class of the multi layer image processor.
 */
public class Main {

  /**
   * The main function that runs a MultiLayer image processor.
   *
   * @param args The command input
   */
  public static void main(String[] args) {

    IMultilayerModel model = new MultilayerModel();

    switch (args[0]) {
      case "-text":
        IImageProcessingController2 cmdController = new MultiLayerController(model, System.out,
            new InputStreamReader(System.in));
        cmdController.runProcessor();
        break;
      case "-interactive":
        IImageProcessingView2 myWindow = new MyWindow(model);
        IImageProcessingController2 delegate = new MultiLayerController(model, new StringBuilder(),
            new StringReader(""));
        IImageProcessingController2 controller = new LIMEController(model, delegate
            , myWindow);
        break;
      case "-script":
        IImageProcessingController2 cmdControllerScript = null;
        try {
          cmdControllerScript = new MultiLayerController(model, System.out,
              new InputStreamReader(new FileInputStream(args[1])));
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
        cmdControllerScript.runProcessor();
        break;
      default:
        System.out.println("invalid command");
        break;
    }
  }
}

