package cs3500.music.tests;

import java.util.ArrayList;
import java.util.List;

import cs3500.music.controller.Controller;
import cs3500.music.model.MusicNote;
import cs3500.music.view.View;

/**
 * Mock to test the MidiView
 */
class MidiViewMock implements View {
  private List<Integer> starts;
  private List<Integer> ends;
  private List<Integer> pitches;
  private int tempo;
  private Controller<MusicNote> controller;

  MidiViewMock(Controller<MusicNote> controller) {
    this.controller = controller;
    this.starts = new ArrayList<>();
    this.ends = new ArrayList<>();
    this.pitches = new ArrayList<>();
    this.tempo = 0;
  }

  @Override
  public void display() {
    List<MusicNote> notes = this.controller.getNotes();
    this.tempo = this.controller.getTempo();
    this.createMidiTrack(notes);
  }

  private void createMidiTrack(List<MusicNote> toAdd) {
    for (MusicNote n : toAdd) {
      int pitch = n.toInt() + 12;
      this.pitches.add(pitch);
      int noteEnd = n.getStart() + n.getDuration();
      this.ends.add(noteEnd * 96);
      this.starts.add(n.getStart() * 96);
    }
  }

  List<Integer> getStarts() {
    return this.starts;
  }

  List<Integer> getEnds() {
    return this.ends;
  }

  List<Integer> getPitches() {
    return this.pitches;
  }

  int getTempo() {
    return this.tempo;
  }
}
