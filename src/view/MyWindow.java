package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ILIMEViewModel;
import model.Layer;
import model.MyUtil;

/**
 * View for Layered Image Manipulation and Enhancement GUI.
 */
public class MyWindow extends JFrame implements IImageProcessingView2, ActionListener {

  private JPanel operation;
  private JPanel setting;
  private final JLabel image;
  private final List<IViewListener> viewListeners;
  private JComboBox<String> comboBox;
  private JComboBox<String> settingLayers;
  private final ILIMEViewModel viewModel;

  /**
   * Constructs the myWindow view with the given LIME view model.
   *
   * @param viewModel Immutable model to feed the view
   */
  public MyWindow(ILIMEViewModel viewModel) {
    super();
    this.viewModel = viewModel;

    viewListeners = new ArrayList<>();
    setSize(600, 600);
    setTitle("Image Processing Application");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    setMinimumSize(new Dimension(600, 600));

    initializeMenuBar();
    initializeMenu();

    initializeOperation();

    image = new JLabel(getImageIcon());
    JScrollPane scrollPane = new JScrollPane(image);
    add(scrollPane, BorderLayout.CENTER);

    setVisible(true);
    setFocusable(true);
    requestFocus();
    pack();
  }

  /**
   * Initializes the menu bar in this view.
   */
  private void initializeMenuBar() {
    JMenuBar menuBar = new JMenuBar();

    JMenu sl = new JMenu("Save/Load");

    JMenuItem save = new JMenuItem("Save single");
    save.setActionCommand("save single");
    save.addActionListener(this);
    sl.add(save);

    JMenuItem saveAll = new JMenuItem("Save multilayer");
    saveAll.setActionCommand("save multiple");
    saveAll.addActionListener(this);
    sl.add(saveAll);

    JMenuItem load = new JMenuItem("Load");
    load.setActionCommand("load");
    load.addActionListener(this);
    sl.add(load);

    menuBar.add(sl);

    JMenu settingMenu = new JMenu("Setting");

    JMenuItem current = new JMenuItem("Choose Layer to Operate on");
    current.setActionCommand("current from menu");
    current.addActionListener(this);
    settingMenu.add(current);

    JMenuItem remove = new JMenuItem("Remove Layer");
    remove.setActionCommand("remove from menu");
    remove.addActionListener(this);
    settingMenu.add(remove);

    JMenuItem visibility = new JMenuItem("Make a Layer Visible");
    visibility.setActionCommand("visible from menu");
    visibility.addActionListener(this);
    settingMenu.add(visibility);

    JMenuItem invisibility = new JMenuItem("Make a Layer Invisible");
    invisibility.setActionCommand("invisible from menu");
    invisibility.addActionListener(this);
    settingMenu.add(invisibility);

    menuBar.add(settingMenu);

    JMenu operation = new JMenu("Operation");
    JMenuItem sharpen = new JMenuItem("Sharpen");
    sharpen.addActionListener(this);
    sharpen.setActionCommand("sharpen from menu");
    operation.add(sharpen);

    JMenuItem blur = new JMenuItem("Blur");
    blur.addActionListener(this);
    blur.setActionCommand("blur from menu");
    operation.add(blur);

    JMenuItem greyscale = new JMenuItem("Greyscale");
    greyscale.addActionListener(this);
    greyscale.setActionCommand("greyscale from menu");
    operation.add(greyscale);

    JMenuItem sepia = new JMenuItem("Sepia");
    sepia.addActionListener(this);
    sepia.setActionCommand("sepia from menu");
    operation.add(sepia);

    JMenuItem downscale = new JMenuItem("Downscale");
    downscale.addActionListener(this);
    downscale.setActionCommand("downscale from menu");
    operation.add(downscale);

    JMenuItem mosaic = new JMenuItem("Mosaic");
    mosaic.addActionListener(this);
    mosaic.setActionCommand("mosaic");
    operation.add(mosaic);

    menuBar.add(operation);
    this.setJMenuBar(menuBar);
  }

  /**
   * Gets the top visible layer from the model and sets it as the image displayed in the view.
   *
   * @return Image icon with the top most visible layer in the model
   */
  private ImageIcon getImageIcon() {
    ImageIcon icon;
    try {
      icon = new ImageIcon(MyUtil.getImageFromIImage(viewModel.getImage()));
    } catch (IllegalArgumentException e) {
      icon = new ImageIcon(MyUtil.getImageForBlank());
    }
    return icon;
  }

  /**
   * Initializes the operations features in the view.
   */
  private void initializeOperation() {
    JPanel bottomSettings = new JPanel();
    bottomSettings.setLayout(new BoxLayout(bottomSettings, BoxLayout.PAGE_AXIS));
    operation = new JPanel();
    operation.setLayout(new FlowLayout());
    bottomSettings.add(operation);
    setting = new JPanel();
    setting.setLayout(new FlowLayout());
    bottomSettings.add(setting);
    initializeOperationComboBox();
    initializeRemoveVisibilityCurrentComboBox();
    add(bottomSettings, BorderLayout.SOUTH);
  }

  /**
   * Initializes the menu features in the view.
   */
  private void initializeMenu() {
    JPanel menu = new JPanel();
    menu.setLayout(new FlowLayout());

    JButton fileLoad = new JButton("Load a file");
    fileLoad.setActionCommand("load");
    fileLoad.addActionListener(this);
    menu.add(fileLoad);

    JButton saveSingleButton = new JButton("Save single file");
    saveSingleButton.setActionCommand("save single");
    saveSingleButton.addActionListener(this);
    menu.add(saveSingleButton);

    JButton saveMultipleButton = new JButton("Save multiple files");
    saveMultipleButton.setActionCommand("save multiple");
    saveMultipleButton.addActionListener(this);
    menu.add(saveMultipleButton);

    add(menu, BorderLayout.NORTH);
  }

  /**
   * Initializes the combobox for image layer operations in this view.
   */
  private void initializeOperationComboBox() {
    //combo boxes for choosing operation

    JPanel comboboxPanel = new JPanel();
    comboboxPanel.setLayout(new FlowLayout());

    JLabel chooseOperationBox = new JLabel("Which operation would you like to run on the current "
        + "image?");
    comboboxPanel.add(chooseOperationBox);
    String[] options = {"Blur", "Sharpen", "Sepia", "Greyscale"};
    comboBox = new JComboBox<>();
    for (String option : options) {
      comboBox.addItem(option);
    }
    comboboxPanel.add(comboBox);

    JButton runOperationButton = new JButton("Run");
    runOperationButton.setActionCommand("run operation");
    runOperationButton.addActionListener(this);

    comboboxPanel.add(runOperationButton);
    operation.add(comboboxPanel);
  }

  /**
   * Initializes the combobox for the layers in the model.
   */
  private void initializeRemoveVisibilityCurrentComboBox() {
    //combo boxes for removing layer

    JPanel settingOperationsPanel = new JPanel();
    settingOperationsPanel.setLayout(new FlowLayout());

    JLabel chooseLayer = new JLabel("Which layer would you like to choose?");
    settingOperationsPanel.add(chooseLayer);
    ArrayList<String> layers = new ArrayList<>();
    for (int i = 0; i < viewModel.getLayers().size(); i++) {
      layers.add(viewModel.getLayers().get(i).getName());
    }
    settingLayers = new JComboBox<>();
    for (String layer : layers) {
      settingLayers.addItem(layer);
    }
    settingOperationsPanel.add(settingLayers);

    JButton removeButton = new JButton("Remove layer");
    removeButton.setActionCommand("remove");
    removeButton.addActionListener(this);
    JButton currentButton = new JButton("Set as current");
    currentButton.setActionCommand("current");
    currentButton.addActionListener(this);
    JButton invisibleButton = new JButton("Set to invisible");
    invisibleButton.setActionCommand("invisible");
    invisibleButton.addActionListener(this);
    JButton visibleButton = new JButton("Set to visible");
    visibleButton.setActionCommand("visible");
    visibleButton.addActionListener(this);
    settingOperationsPanel.add(removeButton);
    settingOperationsPanel.add(currentButton);
    settingOperationsPanel.add(invisibleButton);
    settingOperationsPanel.add(visibleButton);
    setting.add(settingOperationsPanel);
  }

  @Override
  public void actionPerformed(ActionEvent eventCalled) {
    switch (eventCalled.getActionCommand()) {
      case "load":
        emitLoadEvent();
        break;
      case "save single":
        emitSaveSingleEvent();
        break;
      case "save multiple":
        emitSaveMultipleEvent();
        break;
      case "run operation":
        emitRunOperation();
        break;
      case "remove":
        emitRemoveOperation();
        break;
      case "current":
        emitCurrentOperation();
        break;
      case "invisible":
        emitInvisibleOperation();
        break;
      case "visible":
        emitVisibleOperation();
        break;
      case "sepia from menu":
        emitSepiaFromMenu();
        break;
      case "greyscale from menu":
        emitGreyscaleFromMenu();
        break;
      case "sharpen from menu":
        emitSharpenFromMenu();
        break;
      case "blur from menu":
        emitBlurFromMenu();
        break;
      case "current from menu":
        emitCurrentPopup();
        break;
      case "remove from menu":
        emitRemovePopup();
        break;
      case "visible from menu":
        emitVisiblePopup();
        break;
      case "invisible from menu":
        emitInvisiblePopup();
        break;
      case "downscale from menu":
        emitDownScalePopup();
        break;
      case "mosaic":
        emitMosaicPopup();
        break;
      default:
        break;
    }
  }

  /**
   * Displays a pop-up for adding a mosaic filter to a layer in the model feature.
   */
  private void emitMosaicPopup() {
    JTextField numberOfSeeds = new JTextField(10);

    JPanel myPanel = new JPanel();
    myPanel.add(new JLabel("Number Of Seeds: "));
    myPanel.add(numberOfSeeds);

    int result = JOptionPane.showConfirmDialog(MyWindow.this, myPanel,
        "Please Enter Desired Number of Seeds", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      try {
        int n = Integer.parseInt(numberOfSeeds.getText());
        for (IViewListener listener : this.viewListeners) {
          listener.handleMosaic(n);
        }
      } catch (Exception e) {
        e.printStackTrace();
        //renderMessage(e.getMessage());
      }
    }
  }

  /**
   * Displays a pop-up for downscaling the layers in the model feature.
   */
  private void emitDownScalePopup() {

    JTextField width = new JTextField(5);
    JTextField height = new JTextField(5);

    JPanel myPanel = new JPanel();
    myPanel.add(new JLabel("Width: "));
    myPanel.add(width);
    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
    myPanel.add(new JLabel("Height: "));
    myPanel.add(height);
    int result = JOptionPane.showConfirmDialog(MyWindow.this, myPanel,
        "Please Enter Desired Width and Height", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      try {
        int w = Integer.parseInt(width.getText());
        int h = Integer.parseInt(height.getText());
        for (IViewListener listener : this.viewListeners) {
          listener.handleDownscale(w, h);
        }
      } catch (Exception e) {
        renderMessage(e.getMessage());
      }
    }
  }

  /**
   * Displays a pop-up for setting a layer as invisible feature.
   */
  private void emitInvisiblePopup() {
    ArrayList<Layer> visibleLayers = new ArrayList<>();
    for (int i = 0; i < viewModel.getLayers().size(); i++) {
      Layer l = viewModel.getLayers().get(i);
      if (l.getVisibility()) {
        visibleLayers.add(l);
      }
    }
    String[] options = new String[visibleLayers.size()];
    for (int i = 0; i < visibleLayers.size(); i++) {
      options[i] = visibleLayers.get(i).getName();
    }
    int retValue = JOptionPane.showOptionDialog(MyWindow.this,
        "Choose the Layer You Want to Make Invisible: ", "Option", JOptionPane.YES_NO_OPTION,
        JOptionPane.INFORMATION_MESSAGE, null, options, null);
    if (retValue >= 0) {
      for (IViewListener listener : this.viewListeners) {
        listener.handleInvisible(options[retValue]);
      }
    }
  }

  /**
   * Displays a pop-up for setting a layer as visible feature.
   */
  private void emitVisiblePopup() {
    ArrayList<Layer> invisibleLayers = new ArrayList<>();
    for (int i = 0; i < viewModel.getLayers().size(); i++) {
      Layer l = viewModel.getLayers().get(i);
      if (!l.getVisibility()) {
        invisibleLayers.add(l);
      }
    }
    String[] options = new String[invisibleLayers.size()];
    for (int i = 0; i < invisibleLayers.size(); i++) {
      options[i] = invisibleLayers.get(i).getName();
    }
    int retValue = JOptionPane.showOptionDialog(MyWindow.this,
        "Choose the Layer You Want to Make Visible: ", "Option", JOptionPane.YES_NO_OPTION,
        JOptionPane.INFORMATION_MESSAGE, null, options, null);
    if (retValue >= 0) {
      for (IViewListener listener : this.viewListeners) {
        listener.handleVisible(options[retValue]);
      }
    }
  }

  /**
   * Displays a pop-up for removing a layer feature.
   */
  private void emitRemovePopup() {
    String[] options = new String[viewModel.getLayers().size()];
    for (int i = 0; i < viewModel.getLayers().size(); i++) {
      options[i] = viewModel.getLayers().get(i).getName();
    }
    int retValue = JOptionPane.showOptionDialog(MyWindow.this,
        "Choose the Layer You Want to Remove: ", "Option", JOptionPane.YES_NO_OPTION,
        JOptionPane.INFORMATION_MESSAGE, null, options, null);
    if (retValue >= 0) {
      for (IViewListener listener : this.viewListeners) {
        listener.handleRemoveLayer(options[retValue]);
      }
    }
  }

  /**
   * Displays a pop-up for setting a layer as the current layer feature.
   */
  private void emitCurrentPopup() {
    String[] options = new String[viewModel.getLayers().size()];
    for (int i = 0; i < viewModel.getLayers().size(); i++) {
      options[i] = viewModel.getLayers().get(i).getName();
    }
    int retValue = JOptionPane.showOptionDialog(MyWindow.this,
        "Choose the Layer You Want to Operate on: ", "Option", JOptionPane.YES_NO_OPTION,
        JOptionPane.INFORMATION_MESSAGE, null, options, null);
    if (retValue >= 0) {
      for (IViewListener listener : this.viewListeners) {
        listener.handleSetCurrent(options[retValue]);
      }
    }
  }

  /**
   * Calls on the controller to greyscale the current image in the model.
   */
  private void emitGreyscaleFromMenu() {
    for (IViewListener listener : this.viewListeners) {
      listener.handleGreyscale();
    }
  }

  /**
   * Calls on the controller to sharpen the current image in the model.
   */
  private void emitSharpenFromMenu() {
    for (IViewListener listener : this.viewListeners) {
      listener.handleSharpen();
    }
  }

  /**
   * Calls on the controller to blur the current image in the model.
   */
  private void emitBlurFromMenu() {
    for (IViewListener listener : this.viewListeners) {
      listener.handleBlur();
    }
  }

  /**
   * Calls on the controller to sepia the current image in the model.
   */
  private void emitSepiaFromMenu() {
    for (IViewListener listener : this.viewListeners) {
      listener.handleSepia();
    }
  }

  /**
   * Calls on the controller to set a layer as the current layer in the model.
   */
  private void emitCurrentOperation() {
    String str = (String) settingLayers.getSelectedItem();
    for (IViewListener listener : this.viewListeners) {
      listener.handleSetCurrent(str);
    }
  }

  /**
   * Calls on the controller to remove a layer from the model.
   */
  private void emitRemoveOperation() {
    String str = (String) settingLayers.getSelectedItem();
    for (IViewListener listener : this.viewListeners) {
      listener.handleRemoveLayer(str);
    }
  }

  /**
   * Calls on the controller to set the current image in the model to visible.
   */
  private void emitVisibleOperation() {
    String str = (String) settingLayers.getSelectedItem();
    for (IViewListener listener : this.viewListeners) {
      listener.handleVisible(str);
    }
  }

  /**
   * Calls on the controller to set the current image in the model to invisible.
   */
  private void emitInvisibleOperation() {
    String str = (String) settingLayers.getSelectedItem();
    for (IViewListener listener : this.viewListeners) {
      listener.handleInvisible(str);
    }
  }

  /**
   * Calls on the controller to run a certain image operation on the current layer in the model.
   */
  private void emitRunOperation() {
    String str = (String) comboBox.getSelectedItem();
    switch (Objects.requireNonNull(str)) {
      case "Sepia":
        for (IViewListener listener : this.viewListeners) {
          listener.handleSepia();
        }
        break;
      case "Greyscale":
        for (IViewListener listener : this.viewListeners) {
          listener.handleGreyscale();
        }
        break;
      case "Sharpen":
        for (IViewListener listener : this.viewListeners) {
          listener.handleSharpen();
        }
        break;
      case "Blur":
        for (IViewListener listener : this.viewListeners) {
          listener.handleBlur();
        }
        break;
      default:
        break;
    }
  }

  /**
   * Calls on the controller to save multiple images from the model.
   */
  private void emitSaveMultipleEvent() {

    final JFileChooser fChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG, PNG, and PPM Images", "jpg", "ppm", "png");
    fChooser.setFileFilter(filter);

    int retValue = fChooser.showSaveDialog(MyWindow.this);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fChooser.getSelectedFile();
      String path = f.getAbsolutePath();
      for (IViewListener listener : this.viewListeners) {
        listener.handleSaveMultipleEvent(path);
      }
    }
  }

  /**
   * Calls on the controller to save a single image from the model.
   */
  private void emitSaveSingleEvent() {
    String path;

    final JFileChooser fChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG, PNG, and PPM Images", "jpg", "ppm", "png");
    fChooser.setFileFilter(filter);
    int retValue = fChooser.showSaveDialog(MyWindow.this);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fChooser.getSelectedFile();
      path = f.getAbsolutePath();

      for (IViewListener listener : this.viewListeners) {
        listener.handleSaveSingleEvent(path);
      }
    }
  }

  @Override
  public void askForFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void addLayer(String fileName) {
    String name = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.lastIndexOf("."));
    settingLayers.addItem(name);
  }

  @Override
  public void removeLayer(String name) {
    settingLayers.removeItem(name);
  }

  /**
   * Calls on the controller to load a certain file to the model.
   */
  private void emitLoadEvent() {
    String path;

    final JFileChooser fChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG, PNG, and PPM Images", "jpg", "ppm", "png", "directory");
    fChooser.setFileFilter(filter);
    fChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    int retValue = fChooser.showOpenDialog(MyWindow.this);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fChooser.getSelectedFile();
      path = f.getAbsolutePath();

      for (IViewListener listener : this.viewListeners) {
        listener.handleLoadEvent(path);
      }
    }
  }

  @Override
  public void renderMessage(String message) {
    JOptionPane.showConfirmDialog(MyWindow.this,
        message, "Warning", -1, 2);
  }

  @Override
  public void registerViewEventListener(IViewListener listener) {
    this.viewListeners.add(Objects.requireNonNull(listener));
  }

  @Override
  public void updateImage() {
    image.setIcon(getImageIcon());
  }
}
