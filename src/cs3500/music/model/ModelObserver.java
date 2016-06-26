package cs3500.music.model;

import java.util.List;
import java.util.NoSuchElementException;

import cs3500.music.model.MusicModel.Mode;

/**
 * Read only access to the model
 */
public interface ModelObserver<K> {
  /**
   * Gets every note in model
   *
   * @return the list of all notes
   */
  List<K> getAllNotes();

  /**
   * Returns the lowest note in the model
   *
   * @return the lowest note
   * @throws NoSuchElementException if there are no notes in the model
   */
  K getLowestNote() throws NoSuchElementException;

  /**
   * Returns the highest note in the model
   *
   * @return the highest note
   * @throws NoSuchElementException if there are no notes in the model
   */
  K getHighestNote() throws NoSuchElementException;

  /**
   * Returns a list of strings representing the range of the notes of the model The order of the
   * string is lowest note to highest note
   *
   * @return a list of strings representing the range of notes used in the model
   */
  List<String> getNoteRange();

  /**
   * Gets number of beats in the current piece
   *
   * @return the number of beats
   */
  int getSize();

  /**
   * Returns the beats at the given number
   *
   * @param beatNum the number to return the notes at
   * @return the notes at that beat
   */
  List<K> getNotesAtBeat(int beatNum);

  /**
   * Gets the tempo of the composition in beats per minute
   *
   * @return a tempo
   */
  int getTempo();

  /**
   * Gets the mode of the model
   *
   * @return the mode
   */
  Mode getMode();
}
