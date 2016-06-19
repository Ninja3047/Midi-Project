package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.music.model.MusicNote.Octave;
import cs3500.music.model.MusicNote.Pitch;
import cs3500.music.util.CompositionBuilder;

/**
 * Class representing a MIDI editor using class Note
 */
public class MusicModel implements Model<Note> {
  private final Composition<Note> notes;
  private final int tempo;

  private MusicModel(Builder b) {
    this.notes = b.notes;
    this.tempo = b.tempo;
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  @Override
  public void addNote(Note n) {
    notes.addNote(n, n.getStart());
  }

  @Override
  public void overlayNotes(Note... n) {
    Composition<Note> c = new MusicComposition();
    for (Note t : n) {
      c.addNote(t, t.getStart());
    }

    this.notes.combineComposition(c);
  }

  @Override
  public void appendNotes(Note... n) {
    Composition<Note> c = new MusicComposition();
    for (Note t : n) {
      c.addNote(t, t.getStart());
    }

    this.notes.appendComposition(c);
  }

  @Override
  public Note getLowestNote() {
    return notes.getLowestNote();
  }

  @Override
  public Note getHighestNote() {
    return notes.getHighestNote();
  }

  @Override
  public List<String> getNoteRange() {
    List<String> range = new ArrayList<>();
    if (notes.size() > 0) {
      for (Octave o : Octave.values()) {
        for (Pitch p : Pitch.values()) {
          int note = MusicNote.Utils.toInt(p, o);
          if (getLowestNote().toInt() <= note && getHighestNote().toInt() >= note) {
            range.add(MusicNote.Utils.toString(p, o));
          }
        }
      }
    }
    return range;
  }

  @Override
  public void deleteNote(Note n) {
    notes.removeNote(n, n.getStart());
  }

  @Override
  public int getSize() {
    int lnb = notes.getLastNoteBeat();
    if (notes.getNotes(lnb).size() > 0) {
      List<Note> lastNotes = notes.getNotes(lnb);
      int longestDuration = 0;
      for (Note n : lastNotes) {
        if (longestDuration < n.getDuration()) {
          longestDuration = n.getDuration();
        }
      }
      return lnb + longestDuration;
    } else {
      return 0;
    }

  }

  @Override
  public List<Note> getNotesAtBeat(int beatNum) {
    return notes.getNotes(beatNum);
  }

  @Override
  public List<Note> getAllNotes() {
    List<Note> allnotes = new ArrayList<>();
    for (int i = 0; i < notes.getLastNoteBeat() + 1; i++) {
      allnotes.addAll(notes.getNotes(i));
    }
    return allnotes;
  }

  /**
   * Builds the Music Model
   */
  public static final class Builder implements CompositionBuilder<Model<Note>> {
    private final Composition<Note> notes;
    private int tempo;

    /**
     * Constructs a Builder for the Music Model
     */
    public Builder() {
      this.notes = new MusicComposition();
      this.tempo = 0;
    }

    @Override
    public CompositionBuilder<Model<Note>> addNote(int start, int end, int instrument,
                                                   int pitch, int volume) {
      int octaveInt = pitch / 12 - 1;
      int pitchInt = pitch % 12;
      Pitch curPitch = Pitch.C;
      for (Pitch p : Pitch.values()) {
        if (p.getValue() == pitchInt) {
          curPitch = p;
          break;
        }
      }
      Octave curOctave = Octave.ZERO;
      for (Octave o : Octave.values()) {
        if (o.getValue() == octaveInt) {
          curOctave = o;
          break;
        }
      }
      int curDuration = end - start;
      this.notes.addNote(new MusicNote(curPitch, curOctave, start, curDuration, instrument,
              pitch), start);
      return this;
    }

    @Override
    public CompositionBuilder<Model<Note>> setTempo(int tempo) {
      this.tempo = tempo;
      return this;
    }

    @Override
    public Model<Note> build() {
      return new MusicModel(this);
    }
  }
}