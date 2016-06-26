package cs3500.music.controller;

import cs3500.music.model.Note;
import cs3500.music.view.GuiView;

/**
 * Interface that represents a Controller that interacts with the model & view
 */
public interface Controller {

  /**
   * Set the view of the controller to the given view
   *
   * @param v a view
   */
  void setView(GuiView v);

  /**
   * Expands the range of the composition to the given note (0 - 127)
   * @param note a note as an integer (0 - 127)
   * @throws IllegalArgumentException when the note is out of range
   */
  void expandNoteRange(int note) throws IllegalArgumentException;

  /**
   * Expands the range of the composition to the given beat number (must be positive)
   * @param beat a positive number
   * @throws IllegalArgumentException when the beat is 0 or negative
   */
  void expandBeatRange(int beat) throws IllegalArgumentException;

  /**
   * Contracts the range of the composition to valid notes
   */
  void contractRange();

  /**
   * Adds a note to the model from an integer
   * @param pitch the pitch
   * @param start the start
   * @param end the end
   */
  void addNoteFromInt(int pitch, int start, int end);
  /**
   * Delete note from model
   * @param n note to delete
   */
  void deleteNote(Note n);

  /**
   * Gets the location relative to time of play
   * @return 0 - 100.  0 being the beginning 100 being the end
   */
  //double getTime();

  /**
   * Gets the mode of the controller
   * @return the mode
   */
  //String getMode();
}
