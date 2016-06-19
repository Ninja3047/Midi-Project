import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.NoSuchElementException;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelComposition;
import cs3500.music.model.Note;
import cs3500.music.model.Note.Octave;
import cs3500.music.model.Note.Pitch;
import cs3500.music.util.MusicReader;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Class to test the model class
 */
public class MusicModelTest {
  private MusicModel<Note> tester =
          MusicReader.parseFile(new StringReader(""), new MusicModelComposition.Builder());
  private Note n1 = new Note(Pitch.CSHARP, Octave.ONE, 0, 2);

  private MusicModel<Note> advancedTester =
          MusicReader.parseFile(new StringReader("tempo 200000\n" +
                  "note 0 2 1 64 72\n" +
                  "note 0 7 1 55 70\n" +
                  "note 2 4 1 62 72\n" +
                  "note 4 6 1 60 71"), new MusicModelComposition.Builder());

  @Test
  public void testBuilder() throws FileNotFoundException {
    Note[] expected = {new Note(Pitch.E, Octave.FOUR, 0, 2),
            new Note(Pitch.G, Octave.THREE, 0, 7),
            new Note(Pitch.D, Octave.FOUR, 2, 2),
            new Note(Pitch.C, Octave.FOUR, 4, 2)};
    assertArrayEquals(expected, advancedTester.getAllNotes().toArray());
  }

  @Test
  public void testGetHighestNote() {
    assertEquals(new Note(Pitch.E, Octave.FOUR, 0, 2), advancedTester.getHighestNote());
  }

  @Test
  public void testGetLowestNote() {
    assertEquals(new Note(Pitch.G, Octave.THREE, 0, 7), advancedTester.getLowestNote());
  }

  @Test
  public void testGetSize() {
    assertEquals(6, advancedTester.getSize());
  }

  @Test
  public void testGetNotesAt() {
    Note[] expected = {new Note(Pitch.E, Octave.FOUR, 0, 2),
            new Note(Pitch.G, Octave.THREE, 0, 7)};
    assertArrayEquals(expected, advancedTester.getNotesAtBeat(0).toArray());
  }

  @Test
  public void testModel() {
    assertArrayEquals(new Note[0], tester.getAllNotes().toArray());

    Note[] expected = {new Note(Pitch.CSHARP, Octave.ONE, 0, 2)};
    tester.addNote(n1);
    assertArrayEquals(expected, tester.getAllNotes().toArray());

    tester.deleteNote(new Note(Pitch.CSHARP, Octave.ONE, 0, 2));
    assertArrayEquals(new Note[0], tester.getAllNotes().toArray());
  }

  @Test
  public void testDeleteNo() {
    try {
      tester.deleteNote(n1);
      fail();
    } catch (NoSuchElementException e) {
      assertEquals("Note does not exist", e.getMessage());
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
