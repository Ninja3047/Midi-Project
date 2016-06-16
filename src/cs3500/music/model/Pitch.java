package cs3500.music.model;

/**
 * Enum representing a Pitch
 */
public enum Pitch {
  C(1), Csharp(2), D(3), Dsharp(4), E(5), F(6), Fsharp(7), G(8), Gsharp(9), A(10), Asharp(11), B(12);

  int value;

  Pitch(int val) {
    this.value = val;
  }

  public int getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    switch (this) {
      case C:
        return "C";
      case Csharp:
        return "C#";
      case D:
        return "D";
      case Dsharp:
        return "D#";
      case E:
        return "E";
      case F:
        return "F";
      case Fsharp:
        return "F#";
      case G:
        return "G";
      case Gsharp:
        return "G#";
      case A:
        return "A";
      case Asharp:
        return "A#";
      case B:
        return "B";
      default:
        throw new IllegalArgumentException();
    }
  }
}
