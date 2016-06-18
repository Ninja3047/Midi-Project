import org.junit.Test;

import java.util.NoSuchElementException;

import cs3500.music.model.CompositionModel;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Class to test the model class
 */
public class CompositionModelTests {
  private CompositionModel tester = new CompositionModel();
  private Note n1 = new Note(Pitch.Csharp, 1, 0, 2);
  private Note n2 = new Note(Pitch.B, 1, 0, 3);
  private Note n3 = new Note(Pitch.A, 1, 2, 4);
  private Note n4 = new Note(Pitch.C, 2, 1, 13);
  private Note n5 = new Note(Pitch.Csharp, 1, 3, 3);

  @Test
  public void testModel() {
    assertEquals("No notes", tester.printNotes());
    tester.appendNotes(n1);
    assertEquals("  C#1 \n" +
            "0  X  \n" +
            "1  |  \n", tester.printNotes());
    tester.overlayNotes(n2, n3);
    assertEquals("  C#1   D1  D#1   E1   F1  F#1   G1  G#1   A1  A#1   B1 \n" +
                    "0  X                                                 X  \n" +
                    "1  |                                                 |  \n" +
                    "2                                          X         |  \n" +
                    "3                                          |            \n" +
                    "4                                          |            \n" +
                    "5                                          |            \n",
            tester.printNotes());
    tester.appendNotes(n4);
    assertEquals("   C#1   D1  D#1   E1   F1  F#1   G1  G#1   A1  A#1   B1   C2 \n" +
                    " 0  X                                                 X       \n" +
                    " 1  |                                                 |       \n" +
                    " 2                                          X         |       \n" +
                    " 3                                          |                 \n" +
                    " 4                                          |                 \n" +
                    " 5                                          |                 \n" +
                    " 6                                                         X  \n" +
                    " 7                                                         |  \n" +
                    " 8                                                         |  \n" +
                    " 9                                                         |  \n" +
                    "10                                                         |  \n" +
                    "11                                                         |  \n" +
                    "12                                                         |  \n" +
                    "13                                                         |  \n" +
                    "14                                                         |  \n" +
                    "15                                                         |  \n" +
                    "16                                                         |  \n" +
                    "17                                                         |  \n" +
                    "18                                                         |  \n",
            tester.printNotes());
    tester.moveNote(n2, 2);
    assertEquals("   C#1   D1  D#1   E1   F1  F#1   G1  G#1   A1  A#1   B1   C2 \n" +
                    " 0  X                                                         \n" +
                    " 1  |                                                         \n" +
                    " 2                                          X         X       \n" +
                    " 3                                          |         |       \n" +
                    " 4                                          |         |       \n" +
                    " 5                                          |                 \n" +
                    " 6                                                         X  \n" +
                    " 7                                                         |  \n" +
                    " 8                                                         |  \n" +
                    " 9                                                         |  \n" +
                    "10                                                         |  \n" +
                    "11                                                         |  \n" +
                    "12                                                         |  \n" +
                    "13                                                         |  \n" +
                    "14                                                         |  \n" +
                    "15                                                         |  \n" +
                    "16                                                         |  \n" +
                    "17                                                         |  \n" +
                    "18                                                         |  \n",
            tester.printNotes());
    tester.deleteNote(new Note(Pitch.C, 2, 6, 13));
    assertEquals("  C#1   D1  D#1   E1   F1  F#1   G1  G#1   A1  A#1   B1 \n" +
                    "0  X                                                    \n" +
                    "1  |                                                    \n" +
                    "2                                          X         X  \n" +
                    "3                                          |         |  \n" +
                    "4                                          |         |  \n" +
                    "5                                          |            \n",
            tester.printNotes());
    tester.resizeNote(n1, 6);
    assertEquals("  C#1   D1  D#1   E1   F1  F#1   G1  G#1   A1  A#1   B1 \n" +
                    "0  X                                                    \n" +
                    "1  |                                                    \n" +
                    "2  |                                       X         X  \n" +
                    "3  |                                       |         |  \n" +
                    "4  |                                       |         |  \n" +
                    "5  |                                       |            \n",
            tester.printNotes());
    tester.overlayNotes(n5);
    assertEquals("  C#1   D1  D#1   E1   F1  F#1   G1  G#1   A1  A#1   B1 \n" +
                    "0  X                                                    \n" +
                    "1  |                                                    \n" +
                    "2  |                                       X         X  \n" +
                    "3  X                                       |         |  \n" +
                    "4  |                                       |         |  \n" +
                    "5  |                                       |            \n",
            tester.printNotes());
    assertEquals("[C#1, C#1, A1, B1]", tester.getAllNotes().toString());
    assertEquals("[C#1, A1, B1]", tester.getNotesAtBeat(2).toString());
  }

  @Test
  public void testMoveNo() {
    try {
      tester.moveNote(n1, 0);
      fail();
    } catch (NoSuchElementException e) {
      assertEquals("Note does not exist", e.getMessage());
    }
  }

  @Test
  public void testResizeNo() {
    try {
      tester.resizeNote(n1, 4);
      fail();
    } catch (NoSuchElementException e) {
      assertEquals("Note does not exist", e.getMessage());
    }
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
  public void invalidArgs() {
    tester.appendNotes(n1);
    assertEquals("  C#1 \n" +
            "0  X  \n" +
            "1  |  \n", tester.printNotes());
    try {
      tester.resizeNote(n1, -1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Illegal duration", e.getMessage());
    }
    try {
      tester.moveNote(n1, -2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Illegal start", e.getMessage());
    }
    assertEquals("  C#1 \n" +
            "0  X  \n" +
            "1  |  \n", tester.printNotes());
  }

  @Test
  public void testNotesAtEx() {
    try {
      tester.getNotesAtBeat(-1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Illegal beat number", e.getMessage());
    }
  }
}
