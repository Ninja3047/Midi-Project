package cs3500.music.view;

import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;

import cs3500.music.controller.Controller;
import cs3500.music.model.Note;

/**
 * A skeleton for MIDI playback
 */
public class MidiViewImpl implements MidiView {
  private final Synthesizer synth;
  private final Receiver receiver;
  private final Sequencer sequencer;

  public MidiViewImpl() {
    Synthesizer synth;
    Receiver receiver;
    Sequencer sequencer;
    try {
      synth = MidiSystem.getSynthesizer();
      sequencer = MidiSystem.getSequencer();
      receiver = synth.getReceiver();
      sequencer.open();
      synth.open();
    } catch (MidiUnavailableException e) {
      synth = null;
      receiver = null;
      sequencer = null;
      e.printStackTrace();
    }
    this.synth = synth;
    this.receiver = receiver;
    this.sequencer = sequencer;
  }
/*
  public void playNote() throws InvalidMidiDataException {
    //Timer test = new Timer();
    //test.schedule();

    //this.sequencer.get
    Sequence forSequencer = new Sequence(Sequence.PPQ, 96);
    this.sequencer.setTempoInMPQ(15700);
    Track test = forSequencer.createTrack();
    this.sequencer.setSequence(forSequencer);
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 60, 64);
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 60, 64);
    MidiMessage startkek = new ShortMessage(ShortMessage.NOTE_ON, 100, 64);
    MidiMessage wewlad = new ShortMessage(ShortMessage.NOTE_OFF, 100, 64);
    //this.receiver.send(start, -1);
    test.add(new MidiEvent(start, -1));
    test.add(new MidiEvent(stop, 100000));
    test.add(new MidiEvent(startkek, 10));
    test.add(new MidiEvent(wewlad, 200000));
    //this.receiver.send(stop, this.synth.getMicrosecondPosition() + 200000);
    this.sequencer.start();
    //this.sequencer.stop();
    //this.sequencer.close();
  }

  public static void main(String[] args) throws InvalidMidiDataException {
    new MidiViewImpl().playNote();
  }
  */

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
      int pitch = n.toInt() + 12;
      int noteEnd = n.getStart() + n.getDuration();
      MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, pitch, 64);
      MidiMessage end = new ShortMessage(ShortMessage.NOTE_OFF, pitch, 64);
      toPlay.add(new MidiEvent(start, n.getStart() * 96));
      toPlay.add(new MidiEvent(end, noteEnd * 96));
    }
  }
}
