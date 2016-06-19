package cs3500.music.model;

/**
 * Represents a composition of notes
 */
public final class MusicComposition extends AbstractComposition<Note> {

  @Override
  public void appendComposition(Composition<Note> c) {
    for (int i = 0; i < c.getLastNoteBeat() + getLastNoteBeat() + 1; i++) {
      for (Note n : c.getNotes(i)) {
        n.setStart(i + n.getDuration() - 1);
        addNote(n, i + n.getDuration() - 1);
      }
    }
  }
}
