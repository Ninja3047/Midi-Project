package cs3500.music.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class representing a MIDI editor using class Note
 */
public class CompositionModel implements ICompositionModel<Note> {
  private final List<Note> notes;

  public CompositionModel() {
    this.notes = new ArrayList<Note>();
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
  public String printNotes() {
    if (this.notes.size() == 0) {
      return "No notes";
    }
    //Get the lowest and highest note
    Note high = this.notes.get(this.notes.size() - 1);
    Note low = this.notes.get(0);
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
    for (int i = low.getCurOctave(); i <= high.getCurOctave(); i += 1) {
      for (Pitch p : Pitch.values()) {
        Note curNote = new Note(p, i, 0, 1);
        //If note is within the low and high
        if (curNote.compareTo(low) >= 0 &&
                curNote.compareTo(high) <= 0) {
          //Sets up spacing
          StringBuilder textNote = new StringBuilder(curNote.toString());
          if (curNote.toString().length() < 4) {
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
}