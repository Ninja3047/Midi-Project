package cs3500.music.model;

import java.util.List;

/**
 * Interface representing MIDI editors
 */
public interface ICompositionModel<K> {
  /**
   * Deletes a note
   *
   * @param n the note to delete
   */
  void deleteNote(K n);

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
   * Gets the notes printed out
   *
   * @return the notes represented as a string
   */
  String printNotes();
}