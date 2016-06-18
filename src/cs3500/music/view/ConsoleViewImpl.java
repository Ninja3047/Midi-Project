package cs3500.music.view;

import java.util.Arrays;
import java.util.List;

import cs3500.music.controller.Controller;
import cs3500.music.model.Note;
import cs3500.music.model.Note.Octave;
import cs3500.music.model.Note.Pitch;

/**
 * The Console View
 */
public class ConsoleViewImpl implements View {

  private final Controller<Note> con;

  public ConsoleViewImpl(Controller<Note> con) {
    this.con = con;
  }

  @Override
  public void display() {
    String formatNotes = this.printNotes(this.con.getNotes());
    System.out.println(formatNotes);
  }

  /**
   * Gets the notes printed out
   *
   * @return the notes represented as a string
   */
  public String printNotes(List<Note> toPrint) {
    if (toPrint.size() == 0) {
      return "No notes";
    }
    //Get the lowest and highest note
    Note low = toPrint.get(0);
    Note high = toPrint.get(toPrint.size() - 1);

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
        if (Note.Utils.toInt(p, o) >= low.toInt() &&
                Note.Utils.toInt(p, o) <= high.toInt()) {
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
    for (Note n : toPrint) {
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
