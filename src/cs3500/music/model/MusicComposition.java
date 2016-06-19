package cs3500.music.model;

/**
 * Music Composition representation
 */
public final class MusicComposition extends AbstractComposition<MusicNote> {

  @Override
  public void appendComposition(Composition<MusicNote> c) {
    for (int i = 0; i < c.getLastNoteBeat() + getLastNoteBeat() + 1; i++) {
      for (MusicNote n : c.getNotes(i)) {
        addNote(n, i + n.getDuration() - 1);
      }
    }
  }
}
