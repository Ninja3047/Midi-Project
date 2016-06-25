package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Represents an abstract general composition
 */
public abstract class AbstractComposition<T> implements Composition<T> {
  private final SortedMap<T, List<Integer>> composition;
  private final SortedMap<Integer, List<T>> beats;

  /**
   * Constructs an abstract composition
   */
  public AbstractComposition() {
    this.composition = new TreeMap<>();
    this.beats = new TreeMap<>();
  }

  @Override
  public void addNote(T t, int beat) throws IndexOutOfBoundsException {
    if (beat < 0) {
      throw new IndexOutOfBoundsException();
    }

    List<Integer> b;
    if (composition.get(t) == null) {
      b = new ArrayList<>(); // initialize list if not there
      composition.put(t, b);
    } else {
      b = composition.get(t);
    }
    b.add(beat);

    List<T> n;
    if (beats.get(beat) == null) {
      n = new ArrayList<>();
      beats.put(beat, n);
    } else {
      n = beats.get(beat);
    }
    n.add(t);
  }

  @Override
  public void setNote(int beat, T t, T t1)
          throws IndexOutOfBoundsException, NoSuchElementException {
    if (beat < 0 || beat > getLastNoteBeat()) {
      throw new IndexOutOfBoundsException();
    }
    List<Integer> c = composition.get(t);
    if (c == null) {
      throw new NoSuchElementException();
    }
    for (Integer i : c) {
      if (i.equals(beat)) {
        c.remove(i);
        if (c.size() == 0) {
          composition.remove(t);
        }
        if (composition.get(t1) != null) {
          composition.get(t1).add(i);
        } else {
          List<Integer> a = new ArrayList<>();
          a.add(i);
          composition.put(t1, a);
        }
        break;
      }
    }

    List<T> b = beats.get(beat);
    if (b == null) {
      throw new NoSuchElementException();
    }
    for (T n : b) {
      if (n.equals(t)) {
        b.set(b.indexOf(n), t1);
        break;
      }
    }
  }

  @Override
  public T removeNote(T t, int beat) throws IndexOutOfBoundsException, NoSuchElementException {
    if (beat < 0 || beat > getLastNoteBeat()) {
      throw new IndexOutOfBoundsException();
    }
    List<Integer> c = composition.get(t);
    if (c == null) {
      throw new NoSuchElementException("MusicNote does not exist");
    }
    for (Integer i : c) {
      if (i.equals(beat)) {
        c.remove(i);
        if (c.size() == 0) {
          composition.remove(t);
        }
        break;
      }
    }

    List<T> b = beats.get(beat);
    if (b == null) {
      throw new NoSuchElementException();
    }
    for (T n : b) {
      if (n.equals(t)) {
        b.remove(n);
        if (b.size() == 0) {
          beats.remove(beat);
        }
        break;
      }
    }

    return t;
  }

  @Override
  public int getLastNoteBeat() {
    if (beats.size() > 0) {
      return beats.lastKey();
    } else {
      return 0;
    }
  }

  @Override
  public List<T> getNotes(Integer beat) throws IndexOutOfBoundsException {
    if (beat < 0) {
      throw new IndexOutOfBoundsException("Illegal beat number");
    }

    List<T> notes = new ArrayList<>();
    if (beats.get(beat) != null) {
      notes.addAll(beats.get(beat));
    }

    return notes;
  }

  @Override
  public int size() {
    return beats.size();
  }

  @Override
  public T getLowestNote() {
    return composition.lastKey();
  }

  @Override
  public T getHighestNote() {
    return composition.firstKey();
  }

  @Override
  public void combineComposition(Composition<T> c) {
    for (int i = 0; i < c.getLastNoteBeat() + 1; i++) {
      for (T n : c.getNotes(i)) {
        addNote(n, i);
      }
    }
  }

  @Override
  public abstract void appendComposition(Composition<T> c);

  @Override
  public void expandNoteRange(T note) throws IllegalArgumentException {
    if (!composition.containsKey(note)) {
      List<Integer> list = new ArrayList<>();
      System.out.println("Putting " + note.toString());
      composition.put(note, list);
    }
  }

  @Override
  public void expandBeatRange(int beat) throws IllegalArgumentException {
    if (!beats.containsKey(beat)) {
      List<T> list = new ArrayList<>();
      System.out.println("Expanding " + beat);
      beats.put(beat, list);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof AbstractComposition)) {
      return false;
    }

    AbstractComposition that = (AbstractComposition) o;
    return this.composition.equals(that.composition) &&
            this.beats.equals(that.beats);
  }

  @Override
  public int hashCode() {
    return Objects.hash(composition, beats);
  }
}
