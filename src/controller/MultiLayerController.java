package controller;

import view.IImageProcessingView;
import view.ImageProcessingView;
import controller.command.Blur;
import controller.command.CommandList;
import controller.command.Current;
import controller.command.Greyscale;
import controller.command.IImageCommand;
import controller.command.Invisible;
import controller.command.Load;
import controller.command.NameLayer;
import controller.command.Remove;
import controller.command.Save;
import controller.command.SaveMultiple;
import controller.command.Sepia;
import controller.command.Sharpen;
import controller.command.Visible;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;
import model.IImage;
import model.IMultilayerModel;
import model.Layer;

/**
 * Controller for the multi-layer representation of an image processing application.
 */
public class MultiLayerController implements IImageProcessingController2 {

  private final IMultilayerModel model;
  private final IImageProcessingView view;
  private final Readable rd;
  private final Map<String, Function<Scanner, IImageCommand>> knownCommands;

  /**
   * Constructs a multi-layer controller with the given model, appendable and readable.
   *
   * @param model model to feed to the controller
   * @param ap    appendable to feed to the controller
   * @param rd    readable to feed to the controller
   */
  public MultiLayerController(IMultilayerModel model, Appendable ap,
      Readable rd) {
    this.model = model;
    this.view = new ImageProcessingView(ap);
    this.knownCommands = new HashMap<>();
    knownCommands.put("blur", s -> new Blur());
    knownCommands.put("current", s -> new Current(s.nextInt()));
    knownCommands.put("greyscale", s -> new Greyscale());
    knownCommands.put("invisible", s -> new Invisible(s.nextInt()));
    knownCommands.put("load", s -> new Load(s.next()));
    knownCommands.put("namelayer", s -> new NameLayer(s.nextInt(), s.next()));
    knownCommands.put("save", s -> new Save(s.next()));
    knownCommands.put("savemultiple", s -> new SaveMultiple(s.next(), s.next()));
    knownCommands.put("sepia", s -> new Sepia());
    knownCommands.put("sharpen", s -> new Sharpen());
    knownCommands.put("visible", s -> new Visible(s.nextInt()));
    knownCommands.put("remove", s -> new Remove(s.nextInt()));
    knownCommands.put("command", s -> new CommandList());
    if (rd == null) {
      throw new IllegalArgumentException("Readable cannot be null");
    } else {
      this.rd = rd;
    }
  }

  @Override
  public void loadToModelFromFile(String fileName) {
    Objects.requireNonNull(fileName);
    File file = new File(fileName);
    if (file.isDirectory()) {
      for (File f : Objects.requireNonNull(file.listFiles())) {
        loadSingleFile(fileName + "\\" + f.getName());
      }
    } else {
      loadSingleFile(fileName);
    }
  }

  /**
   * Loads in a single file.
   *
   * @param fileName Name of the file to laod in
   */
  private void loadSingleFile(String fileName) {
    LIMEController.loadSingle(fileName, model);

  }

  @Override
  public void loadToModelFromImage(IImage image) {
    model.loadImage(image);
  }

  @Override
  public void save(String fileName) throws IOException {
    IImageSaver saver = new ImageSaverPpmPngJpg();
    saver.save(model.getImage(), fileName);
  }

  @Override
  public void saveMultiLayer(String fileName, String imageType) throws IOException {
    File file = new File(fileName);
    IImageSaver saver = new ImageSaverPpmPngJpg();
    List<Layer> layers = model.getLayers();
    file.mkdir();
    FileOutputStream writer = new FileOutputStream(fileName + "\\" + "Path.txt");
    StringBuilder pathBuilder = new StringBuilder();
    for (Layer l : layers) {
      String name = fileName + "\\" + l.getName() + "." + imageType;
      saver.save(l.getImage(), name);
      pathBuilder.append(name + "\n");
    }
    writer.write(pathBuilder.toString().getBytes(StandardCharsets.UTF_8));
  }

  @Override
  public void runProcessor() {
    Scanner scan = new Scanner(this.rd);
    try {
      write("Please type in the path to script, or start by loading an image. "
          + "Type in command for list of commands");
    } catch (IOException e) {
      e.printStackTrace();
    }
    while (scan.hasNext()) {
      IImageCommand c;
      String inputLine = scan.nextLine();
      String inputWithoutComment = inputLine.split("#")[0];
      Scanner scan2 = new Scanner(inputWithoutComment);
      while (scan2.hasNext()) {
        String input = scan2.next();
        if (input.equals("quit")) {
          try {
            write("quitting.");
          } catch (IOException e) {
            e.printStackTrace();
          }
          return;
        }
        if (input.endsWith("txt")) {
          try {
            scan = new Scanner(new FileReader(input));
            continue;
          } catch (FileNotFoundException e) {
            try {
              write("Can't find file");
            } catch (IOException ioException) {
              ioException.printStackTrace();
            }
          }
        }
        Function<Scanner, IImageCommand> cmd =
            knownCommands.getOrDefault(input.toLowerCase(Locale.ROOT), null);
        if (cmd == null) {
          try {
            write("No such command.");
          } catch (IOException e) {
            e.printStackTrace();
          }
        } else {
          try {
            c = cmd.apply(scan2);
            c.run(this, model);
          } catch (Exception e) {
            if (e instanceof NoSuchElementException) {
              try {
                write("Please write a full command in one line.");
              } catch (IOException ioException) {
                ioException.printStackTrace();
              }
            } else {
              try {
                write(e.getMessage());
              } catch (IOException ioException) {
                ioException.printStackTrace();
              }
            }
          }
        }
      }
    }
  }

  @Override
  public void write(String message) throws IOException {
    view.renderMessage(message + "\n");
  }

  @Override
  public String getCommandList() {
    String out = "Commands are case insensitive. Possible Commands are: ";
    for (Map.Entry<String, Function<Scanner, IImageCommand>> entry : knownCommands.entrySet()) {
      out += entry.getKey() + ",  ";
    }
    out = out.substring(0, out.length() - 3);
    return out;
  }

}
