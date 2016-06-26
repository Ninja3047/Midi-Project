package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Represents a GUI View
 */
public interface GuiView extends View {

  /**
   * Updates the midi track
   */
  void updateTrack();

  /**
   * Toggles the midi sequencer
   */
  void togglePlay();

  /**
   * Scrolls to the beginning of the composition
   */
  void moveToBeginning();

  /**
   * Scrolls to end of the composition
   */
  void moveToEnd();

  /**
   * Adds the given keyboard listener to the view
   *
   * @param listener a keyboard listener
   */
  void addKeyListener(KeyListener listener);

  /**
   * Removes all KeyListeners
   */
  void removeMouseListeners();

  /**
   * Adds a MouseListener
   *
   * @param listener to add
   */
  void addMouseListener(MouseListener listener);

  /**
   * Change the mode of the view
   */
  void changeMode();
}
