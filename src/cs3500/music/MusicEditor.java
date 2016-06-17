package cs3500.music;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.model.CompositionModel;
import cs3500.music.view.View;
import cs3500.music.view.ViewFactory;

import java.io.FileReader;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    View view = ViewFactory.createView("console");
    Controller con = new ControllerImpl(new CompositionModel(), view);
    con.start(new FileReader("mary-little-lamb.txt"));
  }
}
