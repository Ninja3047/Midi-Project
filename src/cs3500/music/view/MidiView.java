package cs3500.music.view;

import java.util.HashMap;
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
import cs3500.music.model.MusicNote;

/**
 * A skeleton for MIDI playback
 */
public class MidiView implements View {
  private final Sequencer sequencer;
  private final Controller<MusicNote> controller;

  public MidiView(Controller<MusicNote> controller) {
    this.controller = controller;
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

  /**
   * Constructor for explicit sequencer
   *
   * @param controller controller to interact with
   * @param seq        the sequencer to use
   */
  public MidiView(Controller<MusicNote> controller, Sequencer seq) {
    this.controller = controller;
    this.sequencer = seq;
  }

  @Override
  public void display() {
    List<MusicNote> notes = controller.getNotes();
    int tempo = controller.getTempo();
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

  private void createMidiTrack(Track toPlay, List<MusicNote> toAdd) throws InvalidMidiDataException {
    HashMap<Integer, Integer> instruments = new HashMap<>();
    int instChannel = 0;
    for (MusicNote n : toAdd) {
      int curInstrument = n.getInstrument();
      if (!(instruments.containsKey(curInstrument))) {
        instruments.put(curInstrument, instChannel);
        MidiMessage iMessage = new ShortMessage(ShortMessage.PROGRAM_CHANGE,
                instChannel, curInstrument, 0);
        toPlay.add(new MidiEvent(iMessage, 0));
        instChannel += 1;
      }
      int pitch = n.toInt();
      int noteEnd = n.getStart() + n.getDuration();
      int channel = instruments.get(curInstrument);
      int volume = n.getVolume();
      MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, channel, pitch, volume);
      MidiMessage end = new ShortMessage(ShortMessage.NOTE_OFF, channel, pitch, volume);
      toPlay.add(new MidiEvent(start, 96 * n.getStart()));
      toPlay.add(new MidiEvent(end, 96 * noteEnd));
    }
  }
}