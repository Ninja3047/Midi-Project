package cs3500.music.controller;

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

}
