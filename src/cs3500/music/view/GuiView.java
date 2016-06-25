package cs3500.music.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Represents a GUI View
 */
public interface GuiView extends View {
  /**
   * Adds the given keyboard listener to the view
   * @param listener a keyboard listener
   */
  void addKeyListener(KeyListener listener);

  void removeMouseListeners();

  void addMouseListener(MouseListener listener);

  //void setState(String c);

  void changeMode();
}
