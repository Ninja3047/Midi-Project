package cs3500.music.controller;

import java.util.List;

import cs3500.music.model.Note;

/**
 * Interface that represents a Controller that interacts with the model & view
 */
public interface Controller {

  /**
   * Starts the process
   * @param rd where to get information
   */
  void start(Readable rd);

  /**
   * Get the data relevant to console
   * @return the formatted list of notes
   */
  String getConsoleData();

  /**
   * Get notes from model
   *
   * @return the list of notes sorted by low to high pitch
   */
  List<Note> getNotes();

  /**
   * Gets the tempo from the model
   * @return the tempo
   */
  int getTempo();
}
