package cs3500.music.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import cs3500.music.controller.Controller;
import cs3500.music.model.Note;

/**
 * The Console View
 */
public class ConsoleView implements View {

  private final Controller<Note> con;
  private Appendable output;

  /**
   * Default constructor
   *
   * @param con the controller to use
   */
  public ConsoleView(Controller<Note> con) {
    this.con = con;
    this.output = System.out;
  }

  /**
   * Constructor to explicitly state output
   *
   * @param con    the controller to use
   * @param output the output to print to
   */
  public ConsoleView(Controller<Note> con, Appendable output) {
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

  @Override
  public void addToTrack(int pitch, int start, int end) {

  }

  /**
   * Prints out the notes given
   *
   * @param toPrint the notes to print
   * @return the formatted printed notes
   */
  private String printNotes(List<Note> toPrint) {
    if (toPrint.size() == 0) {
      return "No notes";
    }
    //Get the lowest and highest note
    Note low = this.con.getLowestNote();
    Note high = this.con.getHighestNote();

    //Info about dimensions
    int totalNotes = high.toInt() - low.toInt() + 1;
    int numBeat = con.getSize();
    int numBeatDigits = Integer.toString(numBeat).length();

    //Init output StringBuilder
    char[] pad = new char[numBeatDigits];
    Arrays.fill(pad, ' ');
    StringBuilder output = new StringBuilder();
    output.append(pad);

    for (String n : con.getNoteRange()) {
      StringBuilder textNote = new StringBuilder();
      textNote.append(n);
      if (n.length() < 4) {
        textNote.append(" ");
      }
      output.append(String.format("%5s", textNote.toString()));
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
