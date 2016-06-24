package cs3500.music.view;

import cs3500.music.model.Note;

/**
 * Represents any view
 */
public interface View {
  /**
   * Starts the view
   */
  void display();

  void addToTrack(int pitch, int start, int stop);
}
