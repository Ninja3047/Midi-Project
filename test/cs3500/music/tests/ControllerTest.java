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
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiView;
import cs3500.music.view.MusicEditorView;

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

  @Test
  public void testAddFromInt() {
    testCon.addNoteFromInt(48, 0, 3);
    testCon.getNotes();
    MusicNote[] correct = {new MusicNote(Pitch.E, Octave.FOUR, 0, 2),
            new MusicNote(Pitch.G, Octave.THREE, 0, 7),
            new MusicNote(Pitch.C, Octave.THREE, 0, 3),
            new MusicNote(Pitch.D, Octave.FOUR, 2, 2),
            new MusicNote(Pitch.C, Octave.FOUR, 4, 2)};
    assertArrayEquals(correct, this.testCon.getNotes().toArray());
  }

  /*
  @Test
  public void testAddToTrack() throws InvalidMidiDataException {
    MockSequencer mock = new MockSequencer();
    MidiView v = new MidiView(testCon, mock);
    testCon.setView(v);
    testCon.addToTrack(60, 0, 1);
    assertEquals("Tempo: [3, 13, 64] (bytes)\n" +
            "0 PROGRAM_CHANGE 1\n" +
            "0 NOTE_ON 64\n" +
            "0 NOTE_ON 55\n" +
            "0 NOTE_ON 60\n" +
            "192 NOTE_OFF 64\n" +
            "192 NOTE_ON 62\n" +
            "384 NOTE_OFF 62\n" +
            "384 NOTE_OFF 60\n" +
            "384 NOTE_ON 60\n" +
            "576 NOTE_OFF 60\n" +
            "672 NOTE_OFF 55\n", mock.getData());
  }
  */

  @Test
  public void deleteNoteTest() {
    testCon.deleteNote(new MusicNote(Pitch.G, Octave.THREE, 0, 7));
    MusicNote[] correct = {new MusicNote(Pitch.E, Octave.FOUR, 0, 2),
            new MusicNote(Pitch.D, Octave.FOUR, 2, 2),
            new MusicNote(Pitch.C, Octave.FOUR, 4, 2)};
    assertArrayEquals(correct, this.testCon.getNotes().toArray());
  }

  /*
  @Test
  public void deleteNoteTrack() throws InvalidMidiDataException {
    testCon.deleteFromTrack(60, 0, 7);
    MockSequencer mock = new MockSequencer();
    MidiView v = new MidiView(testCon, mock);
    testCon.setView(v);
    testCon.addToTrack(60, 0, 1);
    assertEquals("Tempo: [3, 13, 64] (bytes)\n" +
            "0 PROGRAM_CHANGE 1\n" +
            "0 NOTE_ON 64\n" +
            "0 NOTE_ON 55\n" +
            "0 NOTE_ON 60\n" +
            "192 NOTE_OFF 64\n" +
            "192 NOTE_ON 62\n" +
            "384 NOTE_OFF 62\n" +
            "384 NOTE_OFF 60\n" +
            "384 NOTE_ON 60\n" +
            "576 NOTE_OFF 60\n" +
            "672 NOTE_OFF 55\n", mock.getData());
  }
  */

  @Test
  public void testGetTime() {
    GuiViewFrame gv = new GuiViewFrame(testCon);
    MidiView mv = new MidiView(testCon);
    MusicEditorView v = new MusicEditorView(testCon, gv, mv);
    testCon.setView(mv);
    assertEquals(0, testCon.getTime(), 0.001);
  }

  @Test
  public void testGetMode() {
    assertEquals("normal", testCon.getMode());
  }
}