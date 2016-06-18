import org.junit.Test;

import cs3500.music.model.Note;
import cs3500.music.model.Note.Pitch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Class to test the Note class
 */
public class NoteTest {
  private Note n1 = new Note(Pitch.A, 1, 0, 1);
  private Note n2 = new Note(Pitch.B, 1, 1, 3);
  private Note n3 = new Note(Pitch.A, 2, 2, 2);
  private Note n4 = new Note(Pitch.A, 1, 0, 1);
  private Note n5 = new Note(Pitch.A, 1, 1, 1);

  @Test
  public void testOctave() {
    try {
      new Note(Pitch.A, 0, 0, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Illegal octave", e.getMessage());
    }
  }

  @Test
  public void testStart() {
    try {
      new Note(Pitch.A, 1, -1, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Illegal start time", e.getMessage());
    }
  }

  @Test
  public void testDuration() {
    try {
      new Note(Pitch.A, 1, 1, -2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Illegal duration: -2", e.getMessage());
    }
  }

  @Test
  public void testEquals() {
    assertTrue(n1.equals(n4));
    assertTrue(n2.equals(n2));
    assertTrue(n4.equals(n1));
    assertFalse(n1.equals(n3));
  }

  @Test
  public void testHashCode() {
    assertEquals(n1.hashCode(), n4.hashCode());
    assertEquals(n2.hashCode(), n2.hashCode());
    assertEquals(n4.hashCode(), n1.hashCode());
    assertNotEquals(n1.hashCode(), n3.hashCode());
  }

  @Test
  public void setStartEx() {
    try {
      n1.setStart(-1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Illegal start time", e.getMessage());
    }
  }

  @Test
  public void testSetStart() {
    assertEquals(0, n1.getStart());
    n1.setStart(1);
    assertEquals(1, n1.getStart());
  }

  @Test
  public void testGetStart() {
    assertEquals(0, n1.getStart());
    assertEquals(1, n2.getStart());
    assertEquals(2, n3.getStart());
    assertEquals(0, n4.getStart());
  }

  @Test
  public void testGetDuration() {
    assertEquals(1, n1.getDuration());
    assertEquals(3, n2.getDuration());
    assertEquals(2, n3.getDuration());
    assertEquals(1, n4.getDuration());
  }

  @Test
  public void testGetCurOctave() {
    assertEquals(1, n1.getCurOctave());
    assertEquals(1, n2.getCurOctave());
    assertEquals(2, n3.getCurOctave());
    assertEquals(1, n4.getCurOctave());
  }

  @Test
  public void setDurationEx() {
    try {
      n1.setDuration(0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Illegal duration", e.getMessage());
    }
  }

  @Test
  public void testSetDuration() {
    assertEquals(1, n1.getDuration());
    n1.setDuration(5);
    assertEquals(5, n1.getDuration());
  }

  @Test
  public void testToString() {
    assertEquals("A1", n1.toString());
    assertEquals("B1", n2.toString());
    assertEquals("A2", n3.toString());
    assertEquals("A1", n4.toString());
  }

  @Test
  public void testToInt() {
    assertEquals(21, n1.toInt());
    assertEquals(23, n2.toInt());
    assertEquals(33, n3.toInt());
    assertEquals(21, n4.toInt());
  }

  @Test
  public void testCompare() {
    assertEquals(-2, n1.compareTo(n2));
    assertEquals(-1, n2.compareTo(n3));
    assertEquals(1, n3.compareTo(n5));
    assertEquals(-1, n1.compareTo(n5));
  }
}
