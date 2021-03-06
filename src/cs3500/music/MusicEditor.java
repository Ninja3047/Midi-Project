package cs3500.music;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.model.Model;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;
import cs3500.music.view.GuiView;
import cs3500.music.view.View;
import cs3500.music.view.ViewFactory;

/**
 * Class to start the program
 */
public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    try {
      Model<Note> model = MusicReader.parseFile(new FileReader(args[1]),
              new MusicModel.Builder());
      Controller con = new ControllerImpl(model);
      String editortype = args[0];
      View view = ViewFactory.createView(editortype, model);
      if (editortype.equals("composite")) {
        con.setView((GuiView) view);
      }
      view.display();
    } catch (FileNotFoundException e) {
      System.err.println("File not found. Try checking the path of the file. ");
    } catch (IllegalArgumentException e) {
      System.err.println("Wrong view type.\nMust be one of " +
              "\"midi\" \"console\" \"visual\" or \"composite\"");
    } catch (IndexOutOfBoundsException e) {
      System.err.println("Incorrect number of arguments. ");
      System.err.println("This jar takes in two arguments.");
      System.err.println("the first one being the editor type: \n" +
              "\"midi\" \"console\" \"visual\" or \"composite\"");
      System.err.println("and the second one being the file name you wish to play.");
    }
  }
}