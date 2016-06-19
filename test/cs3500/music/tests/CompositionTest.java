package cs3500.music.tests;

import org.junit.Test;

import java.util.NoSuchElementException;

import cs3500.music.model.Composition;
import cs3500.music.model.MusicComposition;
import cs3500.music.model.MusicNote;
import cs3500.music.model.MusicNote.Octave;
import cs3500.music.model.MusicNote.Pitch;
import cs3500.music.model.Note;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for Composition
 */
public class CompositionTest {
  private final Composition<Note> comp = new MusicComposition();
  private final Composition<Note> comp2 = new MusicComposition();
  private final Note n = new MusicNote(Pitch.C, Octave.ONE, 5, 4);
  private final Note n2 = new MusicNote(Pitch.C, Octave.TEN, 10, 4);

  @Test
  public void getNotes() {
    comp.addNote(n, 3);
    comp.addNote(n2, 5);
    assertEquals(1, comp.getNotes(3).size());
    assertEquals(1, comp.getNotes(5).size());
    assertEquals(0, comp.getNotes(7).size());
    assertEquals(n, comp.getNotes(3).get(0));
    assertEquals(n2, comp.getNotes(5).get(0));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testGetNoteInvalid() {
    comp.addNote(n, 3);
    comp.addNote(n2, 5);
    assertEquals(1, comp.getNotes(-1));
  }

  @Test
  public void addInvalidNote() {
    Note n = new MusicNote(Pitch.C, Octave.FOUR, 4, 3);
    try {
      comp.addNote(n, -1);
      fail();
    } catch (IndexOutOfBoundsException e) {
    }
    comp.addNote(n, 0);
    assertEquals(1, comp.size());
  }

  @Test
  public void testGetLowestNote() {
    comp.addNote(n, 4);
    comp.addNote(n, 2);
    comp.addNote(n2, 3);
    assertEquals(comp.getLowestNote(), n);
  }

  @Test
  public void testGetHighestNote() {
    comp.addNote(n, 4);
    comp.addNote(n2, 3);
    assertEquals(comp.getHighestNote(), n2);
  }

  @Test
  public void testSetNote() {
    Note n = new MusicNote(Pitch.ASHARP, Octave.TEN, 3, 1);
    Note n1 = new MusicNote(Pitch.DSHARP, Octave.TEN, 3, 1);
    comp.addNote(n, 3);
    assertEquals(1, comp.size());
    assertEquals(n, comp.getNotes(3).get(0));
    try {
      comp.setNote(100, n, n1);
      fail();
    } catch (IndexOutOfBoundsException e) {
    }
    assertEquals(1, comp.size());
    comp.setNote(3, n, n1);
    assertEquals(1, comp.size());
    assertEquals(n1, comp.getNotes(3).get(0));
    comp.setNote(3, n1, n);
    assertEquals(1, comp.size());
    assertEquals(n, comp.getNotes(3).get(0));
    try {
      comp.setNote(3, n1, n);
      fail();
    } catch (NoSuchElementException e) {
    }
    assertEquals(1, comp.size());
    assertEquals(n, comp.getNotes(3).get(0));
  }

  @Test
  public void testRemoveOverlappingNotes() {
    Note n = new MusicNote(Pitch.C, Octave.FOUR, 0, 3);
    assertEquals(0, comp.size());
    comp.addNote(n, 0);
    assertEquals(1, comp.size());
    assertEquals(n, comp.getNotes(0).get(0));
    Note n2 = new MusicNote(Pitch.C, Octave.FOUR, 3, 2);
    comp.addNote(n2, 3);
    assertEquals(2, comp.size());
    assertEquals(n, comp.getNotes(0).get(0));
    assertEquals(n2, comp.getNotes(3).get(0));
    comp.removeNote(n2, 3);
    assertEquals(1, comp.size());
    assertEquals(n, comp.getNotes(0).get(0));
    comp.removeNote(n, 0);
    assertEquals(0, comp.size());
  }

  @Test(expected = NoSuchElementException.class)
  public void removeEmpty() {
    Note n = new MusicNote(Pitch.C, Octave.FOUR, 0, 3);
    comp.removeNote(n, 0);
  }

  @Test
  public void removeNonExistent() {
    Note n = new MusicNote(Pitch.C, Octave.FOUR, 0, 3);
    assertEquals(0, comp.size());
    comp.addNote(n, 0);
    assertEquals(1, comp.size());
    Note n2 = new MusicNote(Pitch.C, Octave.FOUR, 3, 2);
    comp.addNote(n2, 3);
    assertEquals(2, comp.size());
    try {
      comp.removeNote(n2, 2);
      fail();
    } catch (NoSuchElementException e) {
    }
    assertEquals(2, comp.size());
  }

  @Test
  public void testEquality() {
    Note a = new MusicNote(Pitch.B, Octave.TWO, 0, 4);
    Note b = new MusicNote(Pitch.B, Octave.TWO, 0, 4);
    comp.addNote(n, 4);
    comp.addNote(n2, 3);
    comp2.addNote(n, 4);
    comp2.addNote(n2, 3);
    assertTrue(comp.equals(comp2) && comp2.equals(comp));
    assertTrue(comp.hashCode() == comp2.hashCode());
  }

  @Test
  public void testCombineNothing() {
    Composition<Note> comp = new MusicComposition();
    Note n = new MusicNote(Pitch.ASHARP, Octave.TEN, 1, 1);
    comp.addNote(n, 1);
    comp.combineComposition(comp2);
    assertEquals(n, comp.getNotes(1).get(0));
    assertEquals(1, comp.size());
  }

  @Test
  public void testCombineSame() {
    Composition<Note> comp = new MusicComposition();
    comp.addNote(n, 1);
    comp.addNote(n2, 0);
    comp2.addNote(n, 1);
    comp2.addNote(n2, 0);

    assertEquals(2, comp.size());
    comp.combineComposition(comp2);
    assertEquals(2, comp.size());
  }

  @Test
  public void testCombine() {
    Composition<Note> comp = new MusicComposition();
    Note n = new MusicNote(Pitch.ASHARP, Octave.TEN, 0, 1);
    Note n2 = new MusicNote(Pitch.B, Octave.TEN, 1, 2);
    comp.addNote(n2, 0);
    comp2.addNote(n, 1);

    assertEquals(1, comp.size());
    comp.combineComposition(comp2);
    assertEquals(2, comp.size());
    assertEquals(1, comp.getNotes(0).size());
    assertEquals(1, comp.getNotes(1).size());
  }

  @Test
  public void testAppendNothing() {
    Composition<Note> comp = new MusicComposition();
    Note n = new MusicNote(Pitch.ASHARP, Octave.TEN, 1, 1);
    comp.addNote(n, 1);
    assertEquals(1, comp.size());
    assertEquals(n, comp.getNotes(1).get(0));
    comp.appendComposition(comp2);
    assertEquals(1, comp.size());
    assertEquals(n, comp.getNotes(1).get(0));
  }

  @Test
  public void testAppendSame() {
    Composition<Note> comp = new MusicComposition();
    Note n = new MusicNote(Pitch.ASHARP, Octave.TEN, 0, 2);
    Note n2 = new MusicNote(Pitch.ASHARP, Octave.TEN, 1, 2);
    comp.addNote(n, 0);
    comp2.addNote(n2, 1);
    comp.appendComposition(comp2);
    assertEquals(2, comp.size());
    assertEquals(n.toInt(), comp.getNotes(0).get(0).toInt());
    assertEquals(n.toInt(), comp.getNotes(2).get(0).toInt());
  }

  @Test
  public void testAppend() {
    Composition<Note> comp = new MusicComposition();
    Note n = new MusicNote(Pitch.ASHARP, Octave.TEN, 0, 2);
    Note n2 = new MusicNote(Pitch.B, Octave.TEN, 1, 2);
    comp.addNote(n2, 0);
    comp2.addNote(n, 1);
    assertEquals(1, comp.size());
    comp.appendComposition(comp2);
    assertEquals(2, comp.size());
    assertEquals(n2.toInt(), comp.getNotes(0).get(0).toInt());
    assertEquals(n.toInt(), comp.getNotes(2).get(0).toInt());
  }
}