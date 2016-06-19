package cs3500.music.model;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Represents a general composition
 */
public interface Composition<T> {
  /**
   * Adds the given note to the given beat to the composition
   *
   * @param t    a note
   * @param beat a positive beat number
   * @throws IndexOutOfBoundsException when the beat number is invalid
   */
  void addNote(T t, int beat) throws IndexOutOfBoundsException;

  /**
   * Changes a given note at the given beat
   *
   * @param beat a beat
   * @param t    a note to change
   * @param t1   the note to change to
   * @throws IndexOutOfBoundsException if the beat is out of range (either less than 0 or greater
   *                                   than the size of the composition)
   * @throws NoSuchElementException    if the note to change was not found
   */
  void setNote(int beat, T t, T t1)
          throws IndexOutOfBoundsException, NoSuchElementException;

  /**
   * Removes the given note at the given beat
   *
   * @param t    a note
   * @param beat a beat
   * @return the note removed
   * @throws IndexOutOfBoundsException if the beat number is out of range (either less than 0 or
   *                                   greater than the size of the composition)
   * @throws NoSuchElementException    if given note at the given beat was not found
   */
  T removeNote(T t, int beat) throws IndexOutOfBoundsException, NoSuchElementException;

  /**
   * Returns the last note location
   *
   * @return the last note location
   */
  int getLastNoteBeat();

  /**
   * Returns a list of notes that start at the given beat
   *
   * @param beat a beat
   * @return the list of notes at the given beat
   * @throws IndexOutOfBoundsException when beat is out of range (either less than 0 or greater
   *                                   than the size of the composition)
   */
  List<T> getNotes(Integer beat) throws IndexOutOfBoundsException;

  /**
   * Returns the size of the composition
   *
   * @return composition size
   */
  int size();

  /**
   * Returns the lowest note of this composition
   *
   * @return the lowest note
   */
  T getLowestNote();

  /**
   * Returns the highest note of this composition
   *
   * @return the highest note
   */
  T getHighestNote();

  /**
   * Combines the given composition with this one
   *
   * @param c a composition
   */
  void combineComposition(Composition<T> c);

  /**
   * Appends the given composition to the end of this one
   *
   * @param c a composition
   */
  void appendComposition(Composition<T> c);
}
