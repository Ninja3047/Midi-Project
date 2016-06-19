package cs3500.music.tests;

import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import cs3500.music.model.Model;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicNote;
import cs3500.music.model.MusicNote.Octave;
import cs3500.music.model.MusicNote.Pitch;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Class to test the model class
 */
public class ModelTest {
  private Model<Note> tester =
          MusicReader.parseFile(new StringReader(""), new MusicModel.Builder());
  private Note n1 = new MusicNote(Pitch.CSHARP, Octave.ONE, 0, 2);
  private Note n2 = new MusicNote(Pitch.B, Octave.ONE, 0, 3);
  private Note n3 = new MusicNote(Pitch.A, Octave.ONE, 2, 4);

  private Model<Note> advancedTester =
          MusicReader.parseFile(new StringReader("tempo 200000\n" +
                  "note 0 2 1 64 72\n" +
                  "note 0 7 1 55 70\n" +
                  "note 2 4 1 62 72\n" +
                  "note 4 6 1 60 71"), new MusicModel.Builder());

  @Test
  public void testBuilder() {
    Note[] expected = {new MusicNote(Pitch.E, Octave.FOUR, 0, 2),
            new MusicNote(Pitch.G, Octave.THREE, 0, 7),
            new MusicNote(Pitch.D, Octave.FOUR, 2, 2),
            new MusicNote(Pitch.C, Octave.FOUR, 4, 2)};
    assertArrayEquals(expected, advancedTester.getAllNotes().toArray());
  }

  @Test
  public void testGetHighestNote() {
    assertEquals(new MusicNote(Pitch.E, Octave.FOUR, 0, 2), advancedTester.getHighestNote());
  }

  @Test
  public void testGetLowestNote() {
    assertEquals(new MusicNote(Pitch.G, Octave.THREE, 0, 7), advancedTester.getLowestNote());
  }

  @Test
  public void testGetNoteRange() {
    List<String> expected = Arrays.asList("G3", "G#3", "A3", "A#3", "B3",
            "C4", "C#4", "D4", "D#4", "E4");
    assertEquals(expected, advancedTester.getNoteRange());
  }

  @Test
  public void testGetEmptyNoteRange() {
    List<String> empty = new ArrayList<>();
    assertEquals(empty, tester.getNoteRange());
  }

  @Test
  public void testGetSize() {
    assertEquals(6, advancedTester.getSize());
  }

  @Test
  public void testGetNotesAt() {
    MusicNote[] expected = {new MusicNote(Pitch.E, Octave.FOUR, 0, 2),
            new MusicNote(Pitch.G, Octave.THREE, 0, 7)};
    assertArrayEquals(expected, advancedTester.getNotesAtBeat(0).toArray());
  }

  @Test
  public void testModel() {
    assertArrayEquals(new MusicNote[0], tester.getAllNotes().toArray());

    MusicNote[] expected1 = {new MusicNote(Pitch.CSHARP, Octave.ONE, 0, 2)};
    tester.addNote(n1);
    assertArrayEquals(expected1, tester.getAllNotes().toArray());

    tester.overlayNotes(n2);
    MusicNote[] expected2 = {new MusicNote(Pitch.CSHARP, Octave.ONE, 0, 2),
            new MusicNote(Pitch.B, Octave.ONE, 0, 3)};
    assertArrayEquals(expected2, tester.getAllNotes().toArray());

    tester.deleteNote(new MusicNote(Pitch.CSHARP, Octave.ONE, 0, 2));
    assertArrayEquals(new MusicNote[0], tester.getAllNotes().toArray());
  }

  @Test
  public void testDeleteNo() {
    try {
      tester.deleteNote(n1);
      fail();
    } catch (NoSuchElementException e) {
      assertEquals("MusicNote does not exist", e.getMessage());
    }
  }

  @Test
  public void testNotesAtEx() {
    try {
      tester.getNotesAtBeat(-1);
      fail();
    } catch (IndexOutOfBoundsException e) {
      assertEquals("Illegal beat number", e.getMessage());
    }
  }
}
