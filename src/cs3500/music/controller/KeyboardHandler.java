package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles keyboard input
 */
public class KeyboardHandler implements KeyListener {
  private Map<Character, Runnable> keytyped;
  private Map<Integer, Runnable> keypressed, keyreleased;

  public KeyboardHandler() {
    keytyped = new HashMap<>();
    keypressed = new HashMap<>();
    keyreleased = new HashMap<>();
  }

  /**
   * Sets the map for key typed events
   *
   * @param map a map
   */
  public void setKeyTypedMap(Map<Character, Runnable> map) {
    this.keytyped = map;
  }

  /**
   * Sets the map for key pressed events
   *
   * @param map a map
   */
  public void setKeyPressedMap(Map<Integer, Runnable> map) {
    this.keypressed = map;
  }

  /**
   * Sets the map for key released events
   *
   * @param map a map
   */
  public void setKeyReleasedMap(Map<Integer, Runnable> map) {
    this.keyreleased = map;
  }

  @Override
  public void keyTyped(KeyEvent keyEvent) {
    if (keytyped.containsKey(keyEvent.getKeyCode())) {
      keytyped.get(keyEvent.getKeyCode()).run();
    }
  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    if (keypressed.containsKey(keyEvent.getKeyCode())) {
      keypressed.get(keyEvent.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent keyEvent) {
    if (keyreleased.containsKey(keyEvent.getKeyCode())) {
      keyreleased.get(keyEvent.getKeyCode()).run();
    }
  }
}
