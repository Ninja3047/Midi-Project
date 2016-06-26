package cs3500.music.tests;

import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.controller.KeyboardHandler;

import static org.junit.Assert.assertEquals;

/**
 * Class to test the KeyboardHandler class
 */
public class KeyboardHandlerTest {
  private int key;

  @Test
  public void testKeyboard() {
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    KeyboardHandler kbd = new KeyboardHandler();

    keyPresses.put(KeyEvent.VK_SPACE, () -> {
      this.key = KeyEvent.VK_SPACE;
    });

    keyPresses.put(KeyEvent.VK_A, () -> {
      this.key = KeyEvent.VK_A;
    });

    kbd.setKeyPressedMap(keyPresses);

    kbd.keyPressed(new KeyEvent(new Label(), 0, 0, 0, KeyEvent.VK_SPACE, ' '));
    assertEquals(KeyEvent.VK_SPACE, this.key);

    kbd.keyPressed(new KeyEvent(new Label(), 0, 0, 0, KeyEvent.VK_B, 'b'));
    assertEquals(KeyEvent.VK_SPACE, this.key);

    kbd.keyPressed(new KeyEvent(new Label(), 0, 0, 0, KeyEvent.VK_A, 'a'));
    assertEquals(KeyEvent.VK_A, this.key);

  }
}
