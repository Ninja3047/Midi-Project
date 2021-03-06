package cs3500.music.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import cs3500.music.model.ModelObserver;
import cs3500.music.model.Note;

/**
 * The Console View
 */
public class ConsoleView implements View {

  private ModelObserver<Note> observer;
  private Appendable output;

  /**
   * Constructor
   *
   * @param observer to read from
   */
  public ConsoleView(ModelObserver<Note> observer) {
    this.observer = observer;
    this.output = System.out;
  }

  /**
   * Explicit output constructor
   *
   * @param observer to read from
   * @param output   to append to
   */
  public ConsoleView(ModelObserver<Note> observer, Appendable output) {
    this.observer = observer;
    this.output = output;
  }

  @Override
  public void display() {
    String formatNotes = this.printNotes(this.observer.getAllNotes());
    try {
      output.append(formatNotes);
    } catch (IOException e) {
      e.printStackTrace();
    }
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
    Note low = this.observer.getLowestNote();
    Note high = this.observer.getHighestNote();

    //Info about dimensions
    int totalNotes = high.toInt() - low.toInt() + 1;
    int numBeat = observer.getSize();
    int numBeatDigits = Integer.toString(numBeat).length();

    //Init output StringBuilder
    char[] pad = new char[numBeatDigits];
    Arrays.fill(pad, ' ');
    StringBuilder output = new StringBuilder();
    output.append(pad);

    for (String n : observer.getNoteRange()) {
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
