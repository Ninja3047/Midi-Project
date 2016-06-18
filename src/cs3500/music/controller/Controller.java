package cs3500.music.controller;

import java.util.List;

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
   * Gets the tempo from the model
   * @return the tempo
   */
  int getTempo();

  /**
   * Gets number of beats in the current piece from the model
   *
   * @return the number of beats
   */
  int getSize();

  void setView(View v);
}
