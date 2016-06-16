package cs3500.music;

import cs3500.music.view.View;
import cs3500.music.view.ViewFactory;

import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    View view = ViewFactory.createView("gui");
    // You probably need to connect these views to your model, too...
  }
}
