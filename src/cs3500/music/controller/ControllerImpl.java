package cs3500.music.controller;

import java.util.List;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicNote;
import cs3500.music.view.View;

/**
 * Implementation of the controller interface
 */
public class ControllerImpl implements Controller<MusicNote> {
  private final MusicModel<MusicNote> curModel;
  private View curView;

  /**
   * Constructor
   * @param m model to communicate with
   */
  public ControllerImpl(MusicModel<MusicNote> m) {
    this.curModel = m;
  }

  public void setView(View v) {
    this.curView = v;
  }

  @Override
  public void start() {
    this.curView.display();
  }

  @Override
  public List<MusicNote> getNotes() {
    return this.curModel.getAllNotes();
  }

  @Override
  public List<MusicNote> getNotesAtBeat(int beat) {
    return this.curModel.getNotesAtBeat(beat);
  }

  @Override
  public MusicNote getHighestNote() {
    return this.curModel.getHighestNote();
  }

  @Override
  public MusicNote getLowestNote() {
    return this.curModel.getLowestNote();
  }

  @Override
  public int getTempo() {
    return this.curModel.getTempo();
  }

  @Override
  public int getSize() {
    return this.curModel.getSize();
  }
}
