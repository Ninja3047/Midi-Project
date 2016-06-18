package cs3500.music.view;

import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import cs3500.music.controller.Controller;
import cs3500.music.model.Note;

/**
 * A skeleton for MIDI playback
 */
public class MidiViewImpl implements MidiView {
  private final Sequencer sequencer;

  public MidiViewImpl() {
    Sequencer sequencer;
    try {
      sequencer = MidiSystem.getSequencer();
      sequencer.open();
    } catch (MidiUnavailableException e) {
      sequencer = null;
      e.printStackTrace();
    }
    this.sequencer = sequencer;
  }

  @Override
  public void display(Controller con) {
    List<Note> notes = con.getNotes();
    int tempo = con.getTempo();
    this.sequencer.setTempoInMPQ(tempo);
    try {
      Sequence forSequencer = new Sequence(Sequence.PPQ, 96);
      Track toPlay = forSequencer.createTrack();
      this.createMidiTrack(toPlay, notes);
      this.sequencer.setSequence(forSequencer);
    } catch (InvalidMidiDataException e) {
      System.out.println(e.getMessage());
    }
    this.sequencer.start();
  }

  private void createMidiTrack(Track toPlay, List<Note> toAdd) throws InvalidMidiDataException {
    for (Note n : toAdd) {
      int pitch = n.toInt();
      int noteEnd = n.getStart() + n.getDuration();
      MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, pitch, 64);
      MidiMessage end = new ShortMessage(ShortMessage.NOTE_OFF, pitch, 64);
      toPlay.add(new MidiEvent(start, n.getStart() * 96));
      toPlay.add(new MidiEvent(end, noteEnd * 96));
    }
  }
}
