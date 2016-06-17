package cs3500.music.util;

import cs3500.music.model.ICompositionModel;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;

/**
 * This might be an adapter, rename?
 */
public class CompositionBuilderImpl implements CompositionBuilder<ICompositionModel<Note>> {
  private final ICompositionModel<Note> model;

  public CompositionBuilderImpl(ICompositionModel<Note> m) {
    this.model = m;
  }

  @Override
  public CompositionBuilder<ICompositionModel<Note>> addNote(int start, int end, int instrument, int pitch, int volume) {
    int curOctave = pitch / 12 - 1;
    int pitchInt = pitch % 12 + 1;
    Pitch curPitch = Pitch.C;
    for (Pitch p : Pitch.values()) {
      if (p.getValue() == pitchInt) {
        curPitch = p;
        //break;
      }
    }
    int curDuration = end - start;
    this.model.overlayNotes(new Note(curPitch, curOctave, start, curDuration));
    return this;
  }

  @Override
  public CompositionBuilder<ICompositionModel<Note>> setTempo(int tempo) {
    return this;
  }

  @Override
  public ICompositionModel<Note> build() {
    return this.model;
  }

}