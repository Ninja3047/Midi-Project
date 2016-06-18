import org.junit.Test;

import java.io.StringReader;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.model.Composition;
import cs3500.music.model.MusicComposition;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


/**
 * Class to test MidiView
 */
public class MidiViewTest {
  Composition<Note> model = MusicReader.parseFile(new StringReader("tempo 200000\n" +
            "note 0 2 1 64 72\n" +
            "note 0 7 1 55 70\n" +
            "note 2 4 1 62 72\n" +
          "note 4 6 1 60 71"), new MusicComposition.Builder());
  private Controller con = new ControllerImpl(model);
  private MidiViewMock v = new MidiViewMock(con);

  @Test
  public void testMidiInputs() {
    con.setView(v);
    con.start();
    Integer[] expectedPitches = {55, 60, 62, 64};
    Integer[] expectedStarts = {0, 384, 192, 0};
    Integer[] expectedEnds = {768, 672, 480, 288};
    assertEquals(200000, v.getTempo());
    assertArrayEquals(expectedPitches, v.getPitches().toArray());
    assertArrayEquals(expectedStarts, v.getStarts().toArray());
    assertArrayEquals(expectedEnds, v.getEnds().toArray());
  }
}
