package cs3500.music.model;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Interface representing MIDI editors
 */
public interface Model<K> {

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

  void addNoteFromInt(int note, int start, int end);

  void deleteNoteFromInt(int note, int start, int end);
}