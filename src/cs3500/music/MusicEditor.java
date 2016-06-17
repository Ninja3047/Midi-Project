package cs3500.music;

import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.view.View;
import cs3500.music.view.ViewFactory;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    View view = ViewFactory.createView("midi");
    Controller con = new ControllerImpl(view);
    con.start(new FileReader("mystery-1.txt"));
  }
}
