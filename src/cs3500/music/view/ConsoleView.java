package cs3500.music.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import cs3500.music.controller.Controller;
import cs3500.music.model.MusicNote;
import cs3500.music.model.MusicNote.Octave;
import cs3500.music.model.MusicNote.Pitch;

/**
 * The Console View
 */
public class ConsoleView implements View {

  private final Controller<MusicNote> con;
  private Appendable output;

  public ConsoleView(Controller<MusicNote> con) {
    this.con = con;
    this.output = System.out;
  }

  public ConsoleView(Controller<MusicNote> con, Appendable output) {
    this.con = con;
    this.output = output;
  }

  @Override
  public void display() {
    String formatNotes = this.printNotes(this.con.getNotes());
    try {
      output.append(formatNotes);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets the notes printed out
   *
   * @return the notes represented as a string
   */
  public String printNotes(List<MusicNote> toPrint) {
    if (toPrint.size() == 0) {
      return "No notes";
    }
    //Get the lowest and highest note
    MusicNote low = this.con.getLowestNote();
    MusicNote high = this.con.getHighestNote();

    //Info about dimensions
    int totalNotes = high.toInt() - low.toInt() + 1;
    int numBeat = con.getSize();
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
        if (MusicNote.Utils.toInt(p, o) >= low.toInt() &&
                MusicNote.Utils.toInt(p, o) <= high.toInt()) {
          //Sets up spacing
          StringBuilder textNote = new StringBuilder(MusicNote.Utils.toString(p, o));
          if (MusicNote.Utils.toString(p, o).length() < 4) {
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
    for (MusicNote n : toPrint) {
      int totalLineChar = totalNotes * 5 + numBeatDigits + 1;
      int startRow = (n.getStart() + 1);
      int locInRow = (2 + numBeatDigits + (((n.toInt()) - low.toInt()) * 5));
      output.setCharAt(totalLineChar * startRow + locInRow, 'X');

      for (int i = 1; i < n.getDuration(); i += 1) {
        int curInRow = locInRow + (totalLineChar * (startRow + i));
        if (output.charAt(curInRow) != 'X') {
          output.setCharAt(curInRow, '|');
        }
      }
    }

    return output.toString();
  }
}
