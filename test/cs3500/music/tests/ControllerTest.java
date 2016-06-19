package cs3500.music.tests;

import org.junit.Test;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.model.Model;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicNote;
import cs3500.music.model.MusicNote.Octave;
import cs3500.music.model.MusicNote.Pitch;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Class to test the Controller Interface
 */
public class ControllerTest {

  Model<Note> model = MusicReader.parseFile(new StringReader("tempo 200000\n" +
          "note 0 2 1 64 72\n" +
          "note 0 7 1 55 70\n" +
          "note 2 4 1 62 72\n" +
          "note 4 6 1 60 71"), new MusicModel.Builder());
  private Controller testCon = new ControllerImpl(model);

  @Test
  public void testGetHighestNote() {
    assertEquals(new MusicNote(Pitch.E, Octave.FOUR, 0, 2), testCon.getHighestNote());
  }

  @Test
  public void testGetLowestNote() {
    assertEquals(new MusicNote(Pitch.G, Octave.THREE, 0, 7), testCon.getLowestNote());
  }

  @Test
  public void testGetNoteRange() {
    List<String> expected = Arrays.asList("G3", "G#3", "A3", "A#3", "B3",
            "C4", "C#4", "D4", "D#4", "E4");
    assertEquals(expected, testCon.getNoteRange());
  }

  @Test
  public void testGetSize() {
    assertEquals(6, testCon.getSize());
  }

  @Test
  public void testGetNotesAt() {
    MusicNote[] expected = {new MusicNote(Pitch.E, Octave.FOUR, 0, 2),
            new MusicNote(Pitch.G, Octave.THREE, 0, 7)};
    assertArrayEquals(expected, testCon.getNotesAtBeat(0).toArray());
  }

  @Test
  public void testGetNotes() {
    MusicNote[] correct = {new MusicNote(Pitch.E, Octave.FOUR, 0, 2),
            new MusicNote(Pitch.G, Octave.THREE, 0, 7),
            new MusicNote(Pitch.D, Octave.FOUR, 2, 2),
            new MusicNote(Pitch.C, Octave.FOUR, 4, 2)};
    assertArrayEquals(correct, this.testCon.getNotes().toArray());
  }

  @Test
  public void testGetTempo() {
    assertEquals(200000, this.testCon.getTempo());
  }
}