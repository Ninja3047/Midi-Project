package cs3500.music.model;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Interface representing MIDI editors
 */
public interface MusicModel<K> {

  /**
   * Adds the given note into the existing notes
   *
   * @param n a note
   */
  void addNote(K n);

  /**
   * Overlays the given notes onto the existing notes
   *
   * @param n the notes to overlay
   */
  void overlayNotes(Composition<K> n);

  /**
   * Adds the given notes to the end of the existing notes
   *
   * @param n the notes to append
   */
  void appendNotes(Composition<K> n);

  /**
   * Deletes a note
   *
   * @param n the note to delete
   */
  void deleteNote(K n);

  /**
   * Gets the tempo of the composition in beats per minute
   *
   * @return a tempo
   */
  int getTempo();

  /**
   * Returns the beats at the given number
   *
   * @param beatNum the number to return the notes at
   * @return the notes at that beat
   */
  List<K> getNotesAtBeat(int beatNum);

  /**
   * Gets every note in model
   *
   * @return the list of all notes
   */
  List<K> getAllNotes();

  /**
   * Returns the lowest note in the composition
   *
   * @return the lowest note
   * @throws NoSuchElementException if there are no notes in the composition
   */
  K getLowestNote() throws NoSuchElementException;

  /**
   * Returns the highest note in the composition
   *
   * @return the highest note
   * @throws NoSuchElementException if there are no notes in the composition
   */
  K getHighestNote() throws NoSuchElementException;

  /**
   * Gets number of beats in the current piece
   *
   * @return the number of beats
   */
  int getSize();
}