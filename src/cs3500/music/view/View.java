package cs3500.music.view;

import java.awt.*;

import cs3500.music.model.Note;

/**
 * Represents any view
 */
public interface View {
  /**
   * Starts the view
   */
  void display();

  /**
   * Get the time position
   *
   * @return the time ratio
   */
  double getTime();
}
