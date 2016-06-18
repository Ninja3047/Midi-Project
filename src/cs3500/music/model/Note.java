package cs3500.music.model;

import java.util.Objects;

/**
 * Class representing a note
 */
public class Note implements Comparable<Note> {
  private final Pitch curPitch;
  private final Octave curOctave;
  private int start;
  private int duration;

  /**
   * Note constructor
   *
   * @param curPitch  the pitch of the note
   * @param curOctave the octave of the note
   * @param start     the starting beat
   * @param duration  the duration (in beats) of the note
   */
  public Note(Pitch curPitch, Octave curOctave, int start, int duration) {
    this.curPitch = curPitch;
    this.curOctave = curOctave;
    if (start < 0) {
      throw new IllegalArgumentException("Illegal start time");
    } else {
      this.start = start;
    }
    if (duration < 0) {
      throw new IllegalArgumentException("Illegal duration: " + duration);
    } else {
      this.duration = duration;
    }
  }

  /**
   * Creates a copy of a note given the note
   * @param n a note
   */
  public Note(Note n) {
    this.curPitch = n.curPitch;
    this.curOctave = n.curOctave;
    this.start = n.start;
    this.duration = n.duration;
  }

  public enum Pitch {
    C(0), CSHARP(1), D(2), DSHARP(3), E(4), F(5),
    FSHARP(6), G(7), GSHARP(8), A(9), ASHARP(10), B(11);

    private final int value;

    Pitch(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }

    @Override
    public String toString() {
      switch (this) {
        case C:
          return "C";
        case D:
          return "D";
        case DSHARP:
          return "D#";
        case E:
          return "E";
        case F:
          return "F";
        case FSHARP:
          return "F#";
        case G:
          return "G";
        case GSHARP:
          return "G#";
        case A:
          return "A";
        case ASHARP:
          return "A#";
        case B:
          return "B";
        default:
          throw new IllegalArgumentException();
      }
    }
  }

  public enum Octave {
    ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10);

    private final int octave;

    Octave(int octave) {
      this.octave = octave;
    }

    public int getValue() {
      return octave;
    }

    @Override
    public String toString() {
      return Integer.toString(octave);
    }

  }

  /**
   * Note utilities class
   */
  public static class Utils {
    /**
     * Converts the given octave and pitch
     * @param p a pitch
     * @param o an octave
     * @return the integer representation
     */
    public static int toInt(Pitch p, Octave o) {
      return o.getValue() * 12 + p.getValue();
    }

    public static String toString(Pitch p, Octave o) {
      return p.toString() + o.toString();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Note)) {
      return false;
    } else {
      Note n = (Note) o;
      return this.curPitch == n.curPitch &&
              this.start == n.start &&
              this.duration == n.duration &&
              this.curOctave == n.curOctave;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.curPitch, this.start, this.duration, this.curOctave);
  }

  /**
   * Sets the duration of the note
   *
   * @param newDuration the new duration
   */
  public void setDuration(int newDuration) {
    if (newDuration < 1) {
      throw new IllegalArgumentException("Illegal duration");
    } else {
      this.duration = newDuration;
    }
  }

  /**
   * Sets the start time of the note
   *
   * @param newStart the new start time
   */
  public void setStart(int newStart) {
    if (newStart < 0) {
      throw new IllegalArgumentException("Illegal start time");
    } else {
      this.start = newStart;
    }
  }

  public int getDuration() {
    return this.duration;
  }

  public int getStart() {
    return this.start;
  }

  public Octave getCurOctave() {
    return this.curOctave;
  }

  @Override
  public String toString() {
    return this.curPitch.toString() + this.curOctave;
  }

  /**
   * Converts the note into a unique integer number for easy math
   *
   * @return the integer representation
   */
  public int toInt() {
    return this.curOctave.getValue() * 12 + this.curPitch.ordinal();
  }

  @Override
  public int compareTo(Note n) {
    if (this.curOctave == n.curOctave && this.curPitch == n.curPitch) {
      return this.start - n.start;
    } else if (this.curOctave == n.curOctave) {
      return this.curPitch.ordinal() - n.curPitch.ordinal();
    } else {
      return this.curOctave.getValue() - n.curOctave.getValue();
    }
  }
}
