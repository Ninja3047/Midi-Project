package cs3500.music.tests;

import org.junit.Test;

import java.io.StringReader;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.model.Model;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicNote;
import cs3500.music.model.MusicNote.Octave;
import cs3500.music.model.MusicNote.Pitch;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;

import static org.junit.Assert.*;

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
  public void testAddFromInt() {
    testCon.addNoteFromInt(48, 0, 3);
    MusicNote[] correct = {new MusicNote(Pitch.E, Octave.FOUR, 0, 2),
            new MusicNote(Pitch.G, Octave.THREE, 0, 7),
            new MusicNote(Pitch.C, Octave.THREE, 0, 3),
            new MusicNote(Pitch.D, Octave.FOUR, 2, 2),
            new MusicNote(Pitch.C, Octave.FOUR, 4, 2)};
    assertArrayEquals(correct, model.getAllNotes().toArray());
  }

  @Test
  public void deleteNoteTest() {
    testCon.deleteNote(new MusicNote(Pitch.G, Octave.THREE, 0, 7));
    MusicNote[] correct = {new MusicNote(Pitch.E, Octave.FOUR, 0, 2),
            new MusicNote(Pitch.D, Octave.FOUR, 2, 2),
            new MusicNote(Pitch.C, Octave.FOUR, 4, 2)};
    assertArrayEquals(correct, model.getAllNotes().toArray());
  }

  @Test
  public void testExpandNoteRange() {
    assertEquals(64, model.getHighestNote().toInt());
    assertEquals(55, model.getLowestNote().toInt());
    testCon.expandNoteRange(20);
    assertEquals(64, model.getHighestNote().toInt());
    assertEquals(20, model.getLowestNote().toInt());
    testCon.expandNoteRange(70);
    assertEquals(70, model.getHighestNote().toInt());
    assertEquals(20, model.getLowestNote().toInt());
  }

  @Test
  public void testExpandBeatRange() {
    assertEquals(6, model.getSize());
    testCon.expandBeatRange(20);
    assertEquals(20, model.getSize());
    testCon.expandBeatRange(50);
    assertEquals(50, model.getSize());
  }

  @Test
  public void testContractRange() {
    assertEquals(64, model.getHighestNote().toInt());
    assertEquals(55, model.getLowestNote().toInt());
    testCon.expandNoteRange(20);
    assertEquals(64, model.getHighestNote().toInt());
    assertEquals(20, model.getLowestNote().toInt());
    testCon.expandNoteRange(70);
    assertEquals(70, model.getHighestNote().toInt());
    assertEquals(20, model.getLowestNote().toInt());

    assertEquals(6, model.getSize());
    testCon.expandBeatRange(20);
    assertEquals(20, model.getSize());
    testCon.expandBeatRange(50);
    assertEquals(50, model.getSize());

    testCon.contractRange();
    assertEquals(6, model.getSize());
    assertEquals(64, model.getHighestNote().toInt());
    assertEquals(55, model.getLowestNote().toInt());
  }
}