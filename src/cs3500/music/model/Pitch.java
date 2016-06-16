package cs3500.music.model;

/**
 * Enum representing a Pitch
 */
public enum Pitch {
  C, Csharp, D, Dsharp, E, F, Fsharp, G, Gsharp, A, Asharp, B;

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
