package controller;

import java.io.IOException;
import java.util.Objects;
import view.IViewListener;

/**
 * Mock listener class for testing that appends a certain string based on the method called from the
 * mock view.
 */
public class MockListener implements IViewListener {

  private final Appendable out;

  /**
   * Constructs a mock listener with the provided appendable.
   *
   * @param out Appendable to feed into the listener
   */
  public MockListener(Appendable out) {
    this.out = Objects.requireNonNull(out);
  }

  @Override
  public void handleSaveSingleEvent(String path) {
    try {
      out.append("handledSaveSingleEvent").append(path);
    } catch (IOException e) {
      throw new IllegalStateException("saveSingleNotHandled");
    }
  }

  @Override
  public void handleSaveMultipleEvent(String fileName) {
    try {
      out.append("handledSaveMultipleEvent").append(fileName);
    } catch (IOException e) {
      throw new IllegalStateException("saveMultipleNotHandled");
    }
  }

  @Override
  public void handleLoadEvent(String path) {
    try {
      out.append("handledLoadEvent").append(path);
    } catch (IOException e) {
      throw new IllegalStateException("loadNotHandled");
    }
  }

  @Override
  public void handleSepia() {
    try {
      out.append("handledSepia");
    } catch (IOException e) {
      throw new IllegalStateException("sepiaNotHandled");
    }
  }

  @Override
  public void handleGreyscale() {
    try {
      out.append("handledGreyScale");
    } catch (IOException e) {
      throw new IllegalStateException("greyScaleNotHandled");
    }
  }

  @Override
  public void handleBlur() {
    try {
      out.append("handledBlur");
    } catch (IOException e) {
      throw new IllegalStateException("blurNotHandled");
    }
  }

  @Override
  public void handleSharpen() {
    try {
      out.append("handledSharpen");
    } catch (IOException e) {
      throw new IllegalStateException("sharpenNotHandled");
    }
  }

  @Override
  public void handleSetCurrent(String name) {
    try {
      out.append("handledSetCurrent").append(name);
    } catch (IOException e) {
      throw new IllegalStateException("setCurrentNotHandled");
    }
  }

  @Override
  public void handleVisible(String name) {
    try {
      out.append("handledVisible").append(name);
    } catch (IOException e) {
      throw new IllegalStateException("visibleNotHandled");
    }
  }

  @Override
  public void handleInvisible(String name) {
    try {
      out.append("handledInvisible").append(name);
    } catch (IOException e) {
      throw new IllegalStateException("InvisibleNotHandled");
    }
  }

  @Override
  public void handleRemoveLayer(String name) {
    try {
      out.append("removeHandled").append(name);
    } catch (IOException e) {
      throw new IllegalStateException("removeNotHandled");
    }
  }

  @Override
  public void handleDownscale(int width, int height) {
    try {
      out.append("handledDownscale").append(String.valueOf(width)).append(String.valueOf(height));
    } catch (IOException e) {
      throw new IllegalStateException("downscaleNotHandled");
    }
  }

  @Override
  public void handleMosaic(int n) {
    try {
      out.append("handledMosaic").append(String.valueOf(n));
    } catch (IOException e) {
      throw new IllegalStateException("mosaicNotHandled");
    }
  }
}
