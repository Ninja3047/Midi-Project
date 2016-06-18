package cs3500.music.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import cs3500.music.model.Note.Octave;
import cs3500.music.model.Note.Pitch;
import cs3500.music.util.CompositionBuilder;

/**
 * Class representing a MIDI editor using class Note
 */
public class MusicComposition implements Composition<Note> {
  private final List<Note> notes;
  private final int tempo;

  private MusicComposition(Builder b) {
    this.notes = b.notes;
    this.tempo = b.tempo;
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  @Override
  public void overlayNotes(Note... newNotes) {
    Collections.addAll(this.notes, newNotes);
    Collections.sort(this.notes);
  }

  @Override
  public void appendNotes(Note... newNotes) {
    int newStart = 0;
    if (this.notes.size() != 0) {
      newStart = this.getSize() - 1;
    }
    for (Note n : newNotes) {
      n.setStart(n.getStart() + newStart);
      this.notes.add(n);
    }
    Collections.sort(this.notes);
  }

  @Override
  public Note getLowestNote() {
    Collections.sort(notes);
    return new Note(this.notes.get(this.notes.size() - 1));
  }

  @Override
  public Note getHighestNote() {
    Collections.sort(notes);
    return new Note(this.notes.get(0));
  }

  @Override
  public void deleteNote(Note n) {
    if (!(this.notes.contains(n))) {
      throw new NoSuchElementException("Note does not exist");
    }
    this.notes.remove(n);
  }

  @Override
  public void resizeNote(Note n, int newDuration) {
    if (newDuration < 1) {
      throw new IllegalArgumentException("Illegal duration");
    }
    if (!(this.notes.contains(n))) {
      throw new NoSuchElementException("Note does not exist");
    }
    Note mod = this.notes.get(this.notes.indexOf(n));
    mod.setDuration(newDuration);
  }

  @Override
  public void moveNote(Note n, int newStart) {
    if (newStart < 0) {
      throw new IllegalArgumentException("Illegal start");
    }
    if (!(this.notes.contains(n))) {
      throw new NoSuchElementException("Note does not exist");
    }
    Note mod = this.notes.get(this.notes.indexOf(n));
    mod.setStart(newStart);
    Collections.sort(this.notes);
  }

  @Override
  public int getSize() {
    int endBeat = 0;
    for (Note n : this.notes) {
      int curEndBeat = n.getStart() + n.getDuration();
      if (curEndBeat > endBeat) {
        endBeat = curEndBeat;
      }
    }
    return endBeat;
  }

  @Override
  public List<Note> getNotesAtBeat(int beatNum) {
    if (beatNum < 0) {
      throw new IllegalArgumentException("Illegal beat number");
    }
    ArrayList<Note> curNotes = new ArrayList<Note>();
    for (Note n : this.notes) {
      if (n.getStart() == beatNum) {
        curNotes.add(n);
      }
    }
    return curNotes;
  }

  @Override
  public List<Note> getAllNotes() {
    return new ArrayList<Note>(this.notes);
  }

  public static final class Builder implements CompositionBuilder<Composition<Note>> {
    private final List<Note> notes;
    private int tempo;

    public Builder() {
      this.notes = new ArrayList<>();
    }

    @Override
    public CompositionBuilder<Composition<Note>> addNote(int start, int end, int instrument,
                                                         int pitch, int volume) {
      int octaveInt = pitch / 12 - 1;
      int pitchInt = pitch % 12;
      Pitch curPitch = Pitch.C;
      for (Note.Pitch p : Note.Pitch.values()) {
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
      this.notes.add(new Note(curPitch, curOctave, start, curDuration));
      return this;
    }

    @Override
    public CompositionBuilder<Composition<Note>> setTempo(int tempo) {
      this.tempo = tempo;
      return this;
    }

    @Override
    public Composition<Note> build() {
      return new MusicComposition(this);
    }
  }
}