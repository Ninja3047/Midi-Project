package cs3500.music.model;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Interface representing MIDI editors
 */
public interface Composition<K> {


  /**
   * Overlays the given notes onto the existing notes
   *
   * @param n the notes to overlay
   */
  void overlayNotes(K... n);

  /**
   * Adds the given notes to the end of the existing notes
   *
   * @param n the notes to append
   */
  void appendNotes(K... n);

  /**
   * Deletes a note
   *
   * @param n the note to delete
   */
  void deleteNote(K n);

  /**
   * Gets the tempo of the composition in beats per minute
   * @return a tempo
   */
  int getTempo();

  /**
   * Sets the tempo of the composition in beats per minute
   *
   * @param tempo a tempo
   * @throws IllegalArgumentException if the tempo is less than one
   */
  void setTempo(int tempo) throws IllegalArgumentException;

  /**
   * Changes the duration of a note
   *
   * @param n           the note to change
   * @param newDuration the new duration of the note
   */
  void resizeNote(K n, int newDuration);

  /**
   * Moves the given note
   *
   * @param n        the note to move
   * @param newStart the new start point of the note
   */
  void moveNote(K n, int newStart);

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
   * Gets the notes printed out
   *
   * @return the notes represented as a string
   */
  String printNotes();
}