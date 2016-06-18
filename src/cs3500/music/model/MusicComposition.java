package cs3500.music.model;

import cs3500.music.model.Note.Pitch;
import cs3500.music.model.Note.Octave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import cs3500.music.util.CompositionBuilder;

/**
 * Class representing a MIDI editor using class Note
 */
public class MusicComposition implements Composition<Note> {
  private final List<Note> notes;
  private int tempo;

  private MusicComposition() {
    this.notes = new ArrayList<Note>();
    this.tempo = 0;
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
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
      newStart = this.getNumBeat() - 1;
    }
    for (Note n : newNotes) {
      n.setStart(n.getStart() + newStart);
      this.notes.add(n);
    }
    Collections.sort(this.notes);
  }

  @Override
  public Note getLowestNote() {
    return new Note(this.notes.get(0));
  }

  @Override
  public Note getHighestNote() {
    return new Note(this.notes.get(this.notes.size() - 1));
  }

  @Override
  public String printNotes() {
    if (this.notes.size() == 0) {
      return "No notes";
    }
    //Get the lowest and highest note
    Note high = getHighestNote();
    Note low = getLowestNote();
    low.setStart(0);

    //Info about dimensions
    int totalNotes = high.toInt() - low.toInt() + 1;
    int numBeat = this.getNumBeat();
    int numBeatDigits = Integer.toString(numBeat).length();

    //Init output StringBuilder
    char[] pad = new char[numBeatDigits];
    Arrays.fill(pad, ' ');
    StringBuilder output = new StringBuilder();
    output.append(pad);

    //Create the row of pitches
    for (Octave o : Octave.values()) {
      for (Pitch p : Pitch.values()) {
        //If note is within the low and high
        if (Note.Utils.toInt(p, o) >= low.toInt() &&
                Note.Utils.toInt(p, o)<= high.toInt()) {
          //Sets up spacing
          StringBuilder textNote = new StringBuilder(Note.Utils.toString(p, o));
          if (Note.Utils.toString(p, o).length() < 4) {
            textNote.append(" ");
          }
          output.append(String.format("%5s", textNote.toString()));
        }
      }
    }
    output.append("\n");

    //Sets up the table with spaces
    char[] spaces = new char[totalNotes * 5];
    Arrays.fill(spaces, ' ');
    for (int i = 0; i < numBeat; i += 1) {
      output.append(String.format("%" + numBeatDigits + "s", i));
      output.append(spaces);
      output.append("\n");
    }

    //Inserts the notes into location
    for (Note n : this.notes) {
      int totalLineChar = totalNotes * 5 + numBeatDigits + 1;
      int startRow = (n.getStart() + 1);
      int locInRow = (2 + numBeatDigits + (((n.toInt()) - low.toInt()) * 5));
      output.setCharAt(totalLineChar * startRow + locInRow, 'X');

      for (int i = 1; i < n.getDuration(); i += 1) {
        output.setCharAt(locInRow + (totalLineChar * (i + startRow)), '|');
      }
    }

    return output.toString();
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

  /**
   * Gets number of beats in the current piece
   *
   * @return the number of beats
   */
  private int getNumBeat() {
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
      if (n.getStart() == beatNum || (n.getStart() < beatNum &&
              n.getStart() + n.getDuration() > beatNum)) {
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
    private final Composition<Note> model = new MusicComposition();

    @Override
    public CompositionBuilder<Composition<Note>> addNote(int start, int end, int instrument,
                                                         int pitch, int volume) {
      int octaveInt = pitch / 12 - 1;
      int pitchInt = pitch % 12 + 1;
      Pitch curPitch = Pitch.C;
      for (Note.Pitch p : Note.Pitch.values()) {
        if (p.getValue() == pitchInt) {
          curPitch = p;
          break;
        }
      }
      Octave curOctave = Octave.ZERO;
      for (Octave o : Octave.values()) {
        if (o.getValue() == pitchInt) {
          curOctave = o;
          break;
        }
      }
      int curDuration = end - start + 1;
      this.model.overlayNotes(new Note(curPitch, curOctave, start, curDuration));
      return this;
    }

    @Override
    public CompositionBuilder<Composition<Note>> setTempo(int tempo) {
      this.model.setTempo(tempo);
      return this;
    }

    @Override
    public Composition<Note> build() {
      return this.model;
    }
  }
}