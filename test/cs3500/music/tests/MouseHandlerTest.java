package cs3500.music.tests;

import org.junit.Test;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.controller.MouseHandler;

import static org.junit.Assert.assertEquals;

/**
 * Class to test the MouseHandler class
 */
public class MouseHandlerTest {
  private String key;

  @Test
  public void testKeyboard() {
    Map<String, Runnable> mouseActions = new HashMap<>();
    MouseHandler mouseHandler = new MouseHandler();

    //The lambda methods are mocks
    mouseActions.put("pressed", () -> {
      this.key = "mouse press";
    });

    mouseActions.put("released", () -> {
      this.key = "mouse release";
    });

    mouseHandler.setMouseAction(mouseActions);

    mouseHandler.mousePressed(new MouseEvent(new Label(), 0, 0, 0, 0, 0, 0, false));
    assertEquals("mouse press", this.key);

    mouseHandler.mouseEntered(new MouseEvent(new Label(), 0, 0, 0, 0, 0, 0, false));
    assertEquals("mouse press", this.key);

    mouseHandler.mouseReleased(new MouseEvent(new Label(), 0, 0, 0, 0, 0, 0, false));
    assertEquals("mouse release", this.key);

  }
}
