package view;

import static org.junit.Assert.assertEquals;

import controller.MockListener;
import org.junit.Test;

/**
 * Tests for the myWindow class and the methods inside it.
 */
public class MyWindowTest {

  @Test
  public void testBlurClicked() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    Appendable apMock = new StringBuilder();
    IViewListener mockController = new MockListener(apMock);
    mockView.registerViewEventListener(mockController);
    mockView.emitBlurFromMenu();
    assertEquals("handledBlur", apMock.toString());
  }

  @Test
  public void testSharpenClicked() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    Appendable apMock = new StringBuilder();
    IViewListener mockController = new MockListener(apMock);
    mockView.registerViewEventListener(mockController);
    mockView.emitSharpenFromMenu();
    assertEquals("handledSharpen", apMock.toString());
  }

  @Test
  public void testSaveSingleClicked() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    Appendable apMock = new StringBuilder();
    IViewListener mockController = new MockListener(apMock);
    mockView.registerViewEventListener(mockController);
    mockView.emitSaveSingleEvent();
    assertEquals("handledSaveSingleEventsaveSingleView", apMock.toString());
  }

  @Test
  public void testSaveMultipleClicked() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    Appendable apMock = new StringBuilder();
    IViewListener mockController = new MockListener(apMock);
    mockView.registerViewEventListener(mockController);
    mockView.emitSaveMultipleEvent();
    assertEquals("handledSaveMultipleEventsaveMultipleView", apMock.toString());
  }

  @Test
  public void testLoadClicked() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    Appendable apMock = new StringBuilder();
    IViewListener mockController = new MockListener(apMock);
    mockView.registerViewEventListener(mockController);
    mockView.emitLoadEvent();
    assertEquals("handledLoadEventloadView", apMock.toString());
  }

  @Test
  public void testSepiaClicked() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    Appendable apMock = new StringBuilder();
    IViewListener mockController = new MockListener(apMock);
    mockView.registerViewEventListener(mockController);
    mockView.emitSepiaFromMenu();
    assertEquals("handledSepia", apMock.toString());
  }

  @Test
  public void testGreyscaleClicked() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    Appendable apMock = new StringBuilder();
    IViewListener mockController = new MockListener(apMock);
    mockView.registerViewEventListener(mockController);
    mockView.emitGreyscaleFromMenu();
    assertEquals("handledGreyScale", apMock.toString());
  }

  @Test
  public void testCurrentClicked() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    Appendable apMock = new StringBuilder();
    IViewListener mockController = new MockListener(apMock);
    mockView.registerViewEventListener(mockController);
    mockView.emitCurrentOperation();
    assertEquals("handledSetCurrentcurrent", apMock.toString());
  }

  @Test
  public void testVisibleClicked() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    Appendable apMock = new StringBuilder();
    IViewListener mockController = new MockListener(apMock);
    mockView.registerViewEventListener(mockController);
    mockView.emitVisibleOperation();
    assertEquals("handledVisiblevisible", apMock.toString());
  }

  @Test
  public void testInvisibleClicked() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    Appendable apMock = new StringBuilder();
    IViewListener mockController = new MockListener(apMock);
    mockView.registerViewEventListener(mockController);
    mockView.emitInvisibleOperation();
    assertEquals("handledInvisibleinvisible", apMock.toString());
  }

  @Test
  public void testRemoveClicked() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    Appendable apMock = new StringBuilder();
    IViewListener mockController = new MockListener(apMock);
    mockView.registerViewEventListener(mockController);
    mockView.emitRemoveOperation();
    assertEquals("removeHandledremove", apMock.toString());
  }

  @Test
  public void testDownscaleClicked() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    Appendable apMock = new StringBuilder();
    IViewListener mockController = new MockListener(apMock);
    mockView.registerViewEventListener(mockController);
    mockView.emitDownScalePopup();
    assertEquals("handledDownscale1020", apMock.toString());
  }

  @Test
  public void testMosaicClicked() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    Appendable apMock = new StringBuilder();
    IViewListener mockController = new MockListener(apMock);
    mockView.registerViewEventListener(mockController);
    mockView.emitMosaicPopup();
    assertEquals("handledMosaic100", apMock.toString());
  }

  @Test
  public void testOperationSepia() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    Appendable apMock = new StringBuilder();
    IViewListener mockController = new MockListener(apMock);
    mockView.registerViewEventListener(mockController);
    mockView.emitRunOperation("Sepia");
    assertEquals("handledSepia", apMock.toString());
  }

  @Test
  public void testOperationGreyscale() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    Appendable apMock = new StringBuilder();
    IViewListener mockController = new MockListener(apMock);
    mockView.registerViewEventListener(mockController);
    mockView.emitRunOperation("Greyscale");
    assertEquals("handledGreyScale", apMock.toString());
  }

  @Test
  public void testOperationSharpen() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    Appendable apMock = new StringBuilder();
    IViewListener mockController = new MockListener(apMock);
    mockView.registerViewEventListener(mockController);
    mockView.emitRunOperation("Sharpen");
    assertEquals("handledSharpen", apMock.toString());
  }

  @Test
  public void testOperationBlur() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    Appendable apMock = new StringBuilder();
    IViewListener mockController = new MockListener(apMock);
    mockView.registerViewEventListener(mockController);
    mockView.emitRunOperation("Blur");
    assertEquals("handledBlur", apMock.toString());
  }

  @Test
  public void updateImage() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    mockView.updateImage();
    assertEquals("imageUpdated", viewAp.toString());
  }

  @Test
  public void askForFocus() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    mockView.askForFocus();
    assertEquals("askedForFocus", viewAp.toString());
  }

  @Test
  public void addLayer() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    mockView.addLayer("www");
    assertEquals("addedLayer", viewAp.toString());
  }

  @Test
  public void removeLayer() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    mockView.removeLayer("www");
    assertEquals("removedLayer", viewAp.toString());
  }

  @Test
  public void renderMessage() {
    Appendable viewAp = new StringBuilder();
    MockView mockView = new MockView(viewAp);
    mockView.renderMessage("www");
    assertEquals("www", viewAp.toString());
  }
}