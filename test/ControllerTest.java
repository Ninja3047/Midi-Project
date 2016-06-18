import org.junit.Test;

import java.io.StringReader;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.view.View;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Class to test the Controller Interface
 */
public class ControllerTest {

  private View testView = new ConsoleViewMock();
  private Controller testCon = new ControllerImpl(testView);
  private String input = "tempo 200000\n" +
          "note 0 2 1 64 72\n" +
          "note 0 7 1 55 70\n" +
          "note 2 4 1 62 72\n" +
          "note 4 6 1 60 71";

  @Test
  public void testGetNotes() {
    testCon.start(new StringReader(input));
    Note[] correct = {new Note(Pitch.G, 3, 0, 8),
            new Note(Pitch.C, 4, 4, 3),
            new Note(Pitch.D, 4, 2, 3),
            new Note(Pitch.E, 4, 0, 3)};
    assertArrayEquals(correct, this.testCon.getNotes().toArray());
  }

  @Test
  public void testGetTempo() {
    testCon.start(new StringReader(input));
    assertEquals(200000, this.testCon.getTempo());
  }
}
