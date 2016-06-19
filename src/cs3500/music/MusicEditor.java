package cs3500.music;

import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelComposition;
import cs3500.music.model.MusicNote;
import cs3500.music.util.MusicReader;
import cs3500.music.view.View;
import cs3500.music.view.ViewFactory;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    MusicModel<MusicNote> model = MusicReader.parseFile(new FileReader(args[1]),
            new MusicModelComposition.Builder());
    Controller con = new ControllerImpl(model);
    View view = ViewFactory.createView(args[0], con);
    con.setView(view);
    con.start();
  }
}