import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.NoSuchElementException;

import cs3500.music.model.MusicComposition;
import cs3500.music.model.Composition;
import cs3500.music.model.Note;
import cs3500.music.model.Note.Pitch;
import cs3500.music.util.MusicReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Class to test the model class
 */
public class CompositionModelTests {
  private Composition<Note> tester =
          MusicReader.parseFile(new StringReader(""), new MusicComposition.Builder());
  private Note n1 = new Note(Pitch.CSHARP, 1, 0, 2);
  private Note n2 = new Note(Pitch.B, 1, 0, 3);
  private Note n3 = new Note(Pitch.A, 1, 2, 4);
  private Note n4 = new Note(Pitch.C, 2, 1, 13);
  private Note n5 = new Note(Pitch.CSHARP, 1, 3, 3);

  @Test
  public void testBuilder() throws FileNotFoundException {
    Composition<Note> mary = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"),
            new MusicComposition.Builder());
    assertEquals(
            "    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   F4  F#4   G4 \n" +
                    " 0  X              X                                            X                 \n" +
                    " 1  |              |                                            |                 \n" +
                    " 2  |              |                                  X         |                 \n" +
                    " 3  |              |                                  |                           \n" +
                    " 4  |              |                        X         |                           \n" +
                    " 5  |              |                        |                                     \n" +
                    " 6  |              |                        |         X                           \n" +
                    " 7  |              |                                  |                           \n" +
                    " 8  |              X                                  |         X                 \n" +
                    " 9                 |                                            |                 \n" +
                    "10                 |                                            X                 \n" +
                    "11                 |                                            |                 \n" +
                    "12                 |                                            X                 \n" +
                    "13                 |                                            |                 \n" +
                    "14                 |                                            |                 \n" +
                    "15                 |                                            |                 \n" +
                    "16                 X                                  X                           \n" +
                    "17                 |                                  |                           \n" +
                    "18                 |                                  X                           \n" +
                    "19                 |                                  |                           \n" +
                    "20                 |                                  X                           \n" +
                    "21                 |                                  |                           \n" +
                    "22                 |                                  |                           \n" +
                    "23                 |                                  |                           \n" +
                    "24                 X                                  |         X                 \n" +
                    "25                 |                                            |                 \n" +
                    "26                 |                                            |              X  \n" +
                    "27                                                                             |  \n" +
                    "28                                                                             X  \n" +
                    "29                                                                             |  \n" +
                    "30                                                                             |  \n" +
                    "31                                                                             |  \n" +
                    "32                 X                                            X              |  \n" +
                    "33                 |                                            |                 \n" +
                    "34                 |                                  X         |                 \n" +
                    "35                 |                                  |                           \n" +
                    "36                 |                        X         |                           \n" +
                    "37                 |                        |                                     \n" +
                    "38                 |                        |         X                           \n" +
                    "39                 |                                  |                           \n" +
                    "40                 X                                  |         X                 \n" +
                    "41                 |                                            |                 \n" +
                    "42                 |                                            X                 \n" +
                    "43                 |                                            |                 \n" +
                    "44                 |                                            X                 \n" +
                    "45                 |                                            |                 \n" +
                    "46                 |                                            X                 \n" +
                    "47                 |                                            |                 \n" +
                    "48                 X                                  X         |                 \n" +
                    "49                 |                                  |                           \n" +
                    "50                 |                                  X                           \n" +
                    "51                 |                                  |                           \n" +
                    "52                 |                                  |         X                 \n" +
                    "53                 |                                            |                 \n" +
                    "54                 |                                  X         |                 \n" +
                    "55                 |                                  |                           \n" +
                    "56                 |                        X         |                           \n" +
                    "57                                          |                                     \n" +
                    "58                                          |                                     \n" +
                    "59                                          |                                     \n" +
                    "60                                          |                                     \n" +
                    "61                                          |                                     \n" +
                    "62                                          |                                     \n" +
                    "63                                          |                                     \n" +
                    "64                                          |                                     \n",
            mary.printNotes());
  }

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
