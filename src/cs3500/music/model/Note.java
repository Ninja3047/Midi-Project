package cs3500.music.model;

import java.util.Objects;

/**
 * Class representing a note
 */
public class Note implements Comparable<Note> {
  private final Pitch curPitch;
  private final int curOctave;
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
  public Note(Pitch curPitch, int curOctave, int start, int duration) {
    this.curPitch = curPitch;
    if (curOctave < 1) {
      throw new IllegalArgumentException("Illegal octave");
    } else {
      this.curOctave = curOctave;
    }
    if (start < 0) {
      throw new IllegalArgumentException("Illegal start time");
    } else {
      this.start = start;
    }
    if (duration < 1) {
      throw new IllegalArgumentException("Illegal duration: " + duration);
    } else {
      this.duration = duration;
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

  public int getCurOctave() {
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
    return this.curOctave * 12 + this.curPitch.ordinal();
  }

  @Override
  public int compareTo(Note n) {
    if (this.curOctave == n.curOctave && this.curPitch == n.curPitch) {
      return this.start - n.start;
    } else if (this.curOctave == n.curOctave) {
      return this.curPitch.ordinal() - n.curPitch.ordinal();
    } else {
      return this.curOctave - n.curOctave;
    }
  }
}
