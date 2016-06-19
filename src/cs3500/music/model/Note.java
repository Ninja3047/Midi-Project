package cs3500.music.model;

/**
 * Interface to represent notes
 */
public interface Note {

  /**
   * Sets the duration of the note
   *
   * @param newDuration the new duration
   */
  void setDuration(int newDuration);

  /**
   * Sets the start time of the note
   *
   * @param newStart the new start time
   */
  void setStart(int newStart);

  int getDuration();

  int getStart();

  int getInstrument();

  int getVolume();

  /**
   * Converts the note into a unique integer number for easy math
   *
   * @return the integer representation
   */
  int toInt();
}
