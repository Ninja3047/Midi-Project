package cs3500.music.model;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Interface representing MIDI editors
 */
public interface Model<K> extends ModelObserver {

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
  void overlayNotes(Note... n);

  /**
   * Adds the given notes to the end of the existing notes
   *
   * @param n the notes to append
   */
  void appendNotes(Note... n);

  /**
   * Deletes a note
   *
   * @param n the note to delete
   */
  void deleteNote(K n);

  void addNoteFromInt(int note, int start, int end);

  /**
   * Expands the note range to the given note
   *
   * @param note a note (0 - 127)
   * @throws IllegalArgumentException if the note is out of range
   */
  void expandNoteRange(int note) throws IllegalArgumentException;

  /**
   * Expand the beat range to the given beat
   *
   * @param beat a beat (any positive number)
   * @throws IllegalArgumentException if the note is not positive
   */
  void expandBeatRange(int beat) throws IllegalArgumentException;

  /**
   * Contracts the range to valid notes
   */
  void contractRange();
}