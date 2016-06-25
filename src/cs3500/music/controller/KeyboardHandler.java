package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles keyboard input
 */
public class KeyboardHandler implements KeyListener {
  private Map<Integer, Runnable> keypressed;

  public KeyboardHandler() {
    keypressed = new HashMap<>();
  }

  /**
   * Sets the map for key pressed events
   *
   * @param map a map
   */
  public void setKeyPressedMap(Map<Integer, Runnable> map) {
    this.keypressed = map;
  }

  @Override
  public void keyTyped(KeyEvent keyEvent) {

  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    if (keypressed.containsKey(keyEvent.getKeyCode())) {
      keypressed.get(keyEvent.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent keyEvent) {

  }
}
