package cs3500.music.tests;

import org.junit.Test;

import java.io.StringReader;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.model.Model;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;
import cs3500.music.view.MidiView;

import static org.junit.Assert.assertEquals;

/**
 * Class to test MidiView
 */
public class MidiViewTest {
  private Model<Note> model = MusicReader.parseFile(new StringReader("tempo 200000\n" +
                  "note 0 2 1 64 72\n" +
                  "note 0 7 1 55 70\n" +
                  "note 2 4 1 62 72\n" +
                  "note 4 6 1 60 71"),
          new MusicModel.Builder());
  private Controller<Note> con = new ControllerImpl(model);
  private MockSequencer mock = new MockSequencer();

  @Test
  public void testMidiData() throws InvalidMidiDataException {

    MidiView testView = new MidiView(con, mock);
    con.setView(testView);
    testView.display();
    assertEquals("Tempo: [3, 13, 64] (bytes)\n" +
            "0 PROGRAM_CHANGE 1\n" +
            "0 NOTE_ON 64\n" +
            "0 NOTE_ON 55\n" +
            "192 NOTE_OFF 64\n" +
            "192 NOTE_ON 62\n" +
            "384 NOTE_OFF 62\n" +
            "384 NOTE_ON 60\n" +
            "576 NOTE_OFF 60\n" +
            "672 NOTE_OFF 55\n", mock.getData());
  }
}