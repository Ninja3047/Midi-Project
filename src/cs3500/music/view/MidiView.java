package cs3500.music.view;

import java.util.HashMap;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import cs3500.music.controller.Controller;
import cs3500.music.model.Note;

/**
 * A skeleton for MIDI playback
 */
public class MidiView implements View {
  private final Sequencer sequencer;
  private final Controller<Note> controller;

  /**
   * Default constructor
   *
   * @param controller controller to use
   */
  public MidiView(Controller<Note> controller) {
    Sequencer sequencer;
    try {
      //Init sequencer and helper data
      sequencer = MidiSystem.getSequencer();
      sequencer.open();
    } catch (Exception e) {
      sequencer = null;
      e.printStackTrace();
    }
    this.sequencer = sequencer;
    this.controller = controller;
    this.initSequencerData();
  }

  private void initSequencerData() {
    try {
      List<Note> notes = controller.getNotes();
      int tempo = controller.getTempo();
      Sequence forSequencer = new Sequence(Sequence.PPQ, 96);
      Track toPlay = forSequencer.createTrack();

      //tempo as a byte array
      byte[] data = new byte[3];
      data[0] = (byte) ((tempo >> 16) & 0xFF);
      data[1] = (byte) ((tempo >> 8) & 0xFF);
      data[2] = (byte) (tempo & 0xFF);

      //Set tempo
      MetaMessage mm = new MetaMessage();
      mm.setMessage(81, data, 3);
      MidiEvent pls = new MidiEvent(mm, -1);
      toPlay.add(pls);

      //Create rest of track
      this.createMidiTrack(toPlay, notes);
      sequencer.setSequence(forSequencer);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  /**
   * Constructor for explicit sequencer
   *
   * @param controller the controller to use
   * @param seq        the sequencer to use
   */
  public MidiView(Controller<Note> controller, Sequencer seq) throws InvalidMidiDataException {
    this.sequencer = seq;
    this.controller = controller;
    this.initSequencerData();
  }

  @Override
  public void display() {
    this.sequencer.start();
  }

  /**
   * Toggles the play state of the sequencer
   */
  public void togglePlay() {
    if (this.sequencer.isRunning()) {
      this.sequencer.stop();
    } else {
      this.sequencer.start();
    }
  }

  /**
   * Turns a list of notes into a MidiSequence
   *
   * @param toPlay the track to play
   * @param toAdd  the list of notes to add to the track
   */
  private void createMidiTrack(Track toPlay, List<Note> toAdd) throws InvalidMidiDataException {
    HashMap<Integer, Integer> instruments = new HashMap<>();
    int instChannel = 0;
    for (Note n : toAdd) {
      int curInstrument = n.getInstrument();
      if (!(instruments.containsKey(curInstrument))) {
        if (instChannel != 10) {
          instruments.put(curInstrument, instChannel);
        } else {
          instruments.put(curInstrument, ++instChannel);
        }
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