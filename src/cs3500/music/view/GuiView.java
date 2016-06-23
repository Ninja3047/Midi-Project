package cs3500.music.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * Represents a GUI View
 */
public interface GuiView extends View {
  /**
   * Adds the given keyboard listener to the view
   * @param listener a keyboard listener
   */
  void addKeyListener(KeyListener listener);

  /**
   * Adds the given action listener to the view
   * @param listener an action listener
   */
  void addActionListener(ActionListener listener);
}
