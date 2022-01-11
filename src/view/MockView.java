package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Mock view created with emit methods that call an operation to the mock listener.
 */
public class MockView implements IImageProcessingView2 {

  private final List<IViewListener> viewListeners;
  private Appendable ap;

  /**
   * Constructs a mock view with the provided appendable.
   *
   * @param ap Appendable to append to
   */
  public MockView(Appendable ap) {
    this.viewListeners = new ArrayList<>();
    this.ap = ap;
  }

  @Override
  public void registerViewEventListener(IViewListener listener) {
    this.viewListeners.add(Objects.requireNonNull(listener));
  }

  @Override
  public void updateImage() {
    try {
      ap.append("imageUpdated");
    } catch (IOException e) {
      throw new IllegalStateException("imageNotUpdated");
    }
  }

  @Override
  public void askForFocus() {
    try {
      ap.append("askedForFocus");
    } catch (IOException e) {
      throw new IllegalStateException("focusNotHandled");
    }
  }

  @Override
  public void addLayer(String fileName) {
    try {
      ap.append("addedLayer");
    } catch (IOException e) {
      throw new IllegalStateException("layerNotAdded");
    }
  }

  @Override
  public void removeLayer(String fileName) {
    try {
      ap.append("removedLayer");
    } catch (IOException e) {
      throw new IllegalStateException("removeLayerNotHandled");
    }
  }

  /**
   * Calls the handleGreyscale method in the mock listener.
   */
  public void emitGreyscaleFromMenu() {
    for (IViewListener listener : this.viewListeners) {
      listener.handleGreyscale();
    }
  }

  /**
   * Calls the handleSharpen method in the mock listener.
   */
  public void emitSharpenFromMenu() {
    for (IViewListener listener : this.viewListeners) {
      listener.handleSharpen();
    }
  }

  /**
   * Calls the handleBlur method in the mock listener.
   */
  public void emitBlurFromMenu() {
    for (IViewListener listener : this.viewListeners) {
      listener.handleBlur();
    }
  }

  /**
   * Calls the handleSepia method in the mock listener.
   */
  public void emitSepiaFromMenu() {
    for (IViewListener listener : this.viewListeners) {
      listener.handleSepia();
    }
  }

  /**
   * Calls the handleSetCurrent method in the mock listener.
   */
  public void emitCurrentOperation() {
    String str = "current";
    for (IViewListener listener : this.viewListeners) {
      listener.handleSetCurrent(str);
    }
  }

  /**
   * Calls the handleRemove method in the mock listener.
   */
  public void emitRemoveOperation() {
    String str = "remove";
    for (IViewListener listener : this.viewListeners) {
      listener.handleRemoveLayer(str);
    }
  }

  /**
   * Calls the handleVisible method in the mock listener.
   */
  public void emitVisibleOperation() {
    String str = "visible";
    for (IViewListener listener : this.viewListeners) {
      listener.handleVisible(str);
    }
  }

  /**
   * Calls the handleInvisible method in the mock listener.
   */
  public void emitInvisibleOperation() {
    String str = "invisible";
    for (IViewListener listener : this.viewListeners) {
      listener.handleInvisible(str);
    }
  }

  /**
   * Calls on the controller to run a certain image operation on the current layer in the model.
   */
  public void emitRunOperation(String str) {
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
   * Calls the handleSaveMultiple method in the mock listener.
   */
  public void emitSaveMultipleEvent() {
    String path = "saveMultipleView";
    for (IViewListener listener : this.viewListeners) {
      listener.handleSaveMultipleEvent(path);
    }
  }

  /**
   * Calls the handleSaveSingleEvent method in the mock listener.
   */
  public void emitSaveSingleEvent() {
    String path = "saveSingleView";
    for (IViewListener listener : this.viewListeners) {
      listener.handleSaveSingleEvent(path);
    }
  }

  /**
   * Calls the handleLoadEvent method in the mock listener.
   */
  public void emitLoadEvent() {
    String path = "loadView";
    for (IViewListener listener : this.viewListeners) {
      listener.handleLoadEvent(path);
    }
  }

  /**
   * Calls the handleDownscale method in the mock listener.
   */
  public void emitDownScalePopup() {
    for (IViewListener listener : this.viewListeners) {
      listener.handleDownscale(10, 20);
    }
  }


  /**
   * Calls the handleMosaic method in the mock listener.
   */
  public void emitMosaicPopup() {
    for (IViewListener listener : this.viewListeners) {
      listener.handleMosaic(100);
    }
  }

  @Override
  public void renderMessage(String message) {
    try {
      ap.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("could not be appended");
    }
  }
}
