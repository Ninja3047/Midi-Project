package cs3500.music.controller;

import java.util.List;

import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.view.View;

/**
 * Implementation of the controller interface
 */
public class ControllerImpl implements Controller<Note> {
  private final MusicModel<Note> curModel;
  private View curView;

  /**
   * Constructor
   * @param m model to communicate with
   */
  public ControllerImpl(MusicModel<Note> m) {
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
  public List<Note> getNotes() {
    return this.curModel.getAllNotes();
  }

  @Override
  public List<Note> getNotesAtBeat(int beat) {
    return this.curModel.getNotesAtBeat(beat);
  }

  @Override
  public Note getHighestNote() {
    return this.curModel.getHighestNote();
  }

  @Override
  public Note getLowestNote() {
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
