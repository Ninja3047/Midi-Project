package cs3500.music.controller;

import java.util.List;

import cs3500.music.model.Note;
import cs3500.music.view.View;

/**
 * Interface that represents a Controller that interacts with the model & view
 */
public interface Controller<T> {

  /**
   * Starts the process
   */
  void start();

  /**
   * Set the view of the controller to the given view
   *
   * @param v a view
   */
  void setView(View v);

  /**
   * Get notes from model
   *
   * @return the list of notes sorted by low to high pitch
   */
  List<T> getNotes();

  /**
   * Get the list of notes at the given beat from the model
   *
   * @param beat a beat
   * @return the list of notes at the beat
   * @throws IllegalArgumentException when the beat number is less than zero
   */
  List<T> getNotesAtBeat(int beat) throws IllegalArgumentException;

  /**
   * Returns the lowest note from the model
   *
   * @return a note
   */
  T getLowestNote();

  /**
   * Return the highest note from the model
   *
   * @return a note
   */
  T getHighestNote();

  /**
   * Returns a list of strings representing the range of the notes of the model The order of the
   * string is lowest note to highest note
   *
   * @return a list of strings representing the range of notes used in the model
   */
  List<String> getNoteRange();

  /**
   * Gets the tempo from the model
   *
   * @return the tempo
   */
  int getTempo();

  /**
   * Gets number of beats in the current piece from the model
   *
   * @return the number of beats
   */
  int getSize();

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
  double getTime();

  /**
   * Gets the mode of the controller
   * @return the mode
   */
  String getMode();
}
