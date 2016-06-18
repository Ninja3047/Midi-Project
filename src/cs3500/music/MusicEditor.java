package cs3500.music;

import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.model.Composition;
import cs3500.music.model.MusicComposition;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;
import cs3500.music.view.View;
import cs3500.music.view.ViewFactory;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    Composition<Note> model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"),
            new MusicComposition.Builder());
    Controller con = new ControllerImpl(model);
    View view = ViewFactory.createView("console", con);
    con.setView(view);
    con.start();
  }
}