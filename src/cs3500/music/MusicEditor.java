package cs3500.music;

import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.model.Model;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;
import cs3500.music.view.View;
import cs3500.music.view.ViewFactory;

/**
 * Class to start the program
 */
public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    Model<Note> model = MusicReader.parseFile(new FileReader(args[0]),
            new MusicModel.Builder());
    Controller<Note> con = new ControllerImpl(model);
    View view = ViewFactory.createView("player", con);
    con.setView(view);
    con.start();
  }
}