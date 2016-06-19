import org.junit.Test;

import java.io.StringReader;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelComposition;
import cs3500.music.model.Note;
import cs3500.music.model.Note.Octave;
import cs3500.music.model.Note.Pitch;
import cs3500.music.util.MusicReader;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Class to test the Controller Interface
 */
public class ControllerTest {

  MusicModel<Note> model = MusicReader.parseFile(new StringReader("tempo 200000\n" +
          "note 0 2 1 64 72\n" +
          "note 0 7 1 55 70\n" +
          "note 2 4 1 62 72\n" +
          "note 4 6 1 60 71"), new MusicModelComposition.Builder());
  private Controller testCon = new ControllerImpl(model);

  @Test
  public void testGetHighestNote() {
    assertEquals(new Note(Pitch.E, Octave.FOUR, 0, 2), testCon.getHighestNote());
  }

  @Test
  public void testGetLowestNote() {
    assertEquals(new Note(Pitch.G, Octave.THREE, 0, 7), testCon.getLowestNote());
  }

  @Test
  public void testGetSize() {
    assertEquals(6, testCon.getSize());
  }

  @Test
  public void testGetNotesAt() {
    Note[] expected = {new Note(Pitch.E, Octave.FOUR, 0, 2),
            new Note(Pitch.G, Octave.THREE, 0, 7)};
    assertArrayEquals(expected, testCon.getNotesAtBeat(0).toArray());
  }

  @Test
  public void testGetNotes() {
    Note[] correct = {new Note(Pitch.E, Octave.FOUR, 0, 2),
            new Note(Pitch.G, Octave.THREE, 0, 7),
            new Note(Pitch.D, Octave.FOUR, 2, 2),
            new Note(Pitch.C, Octave.FOUR, 4, 2)};
    assertArrayEquals(correct, this.testCon.getNotes().toArray());
  }

  @Test
  public void testGetTempo() {
    assertEquals(200000, this.testCon.getTempo());
  }
}