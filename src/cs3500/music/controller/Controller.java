package cs3500.music.controller;

import java.util.List;

/**
 * Interface that represents a Controller that interacts with the model & view
 */
public interface Controller<T> {

  /**
   * Starts the process
   * @param rd where to get information
   */
  void start(Readable rd);

  /**
   * Get notes from model
   *
   * @return the list of notes sorted by low to high pitch
   */
  List<T> getNotes();

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
}
