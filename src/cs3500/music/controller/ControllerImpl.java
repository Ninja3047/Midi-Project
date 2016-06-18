package cs3500.music.controller;

import java.util.List;

import cs3500.music.model.Composition;
import cs3500.music.model.Note;
import cs3500.music.view.View;

/**
 * Implementation of the controller interface
 */
public class ControllerImpl implements Controller<Note> {
  private final Composition<Note> curModel;
  private View curView;

  /**
   * Constructor
   * @param m model to communicate with
   */
  public ControllerImpl(Composition<Note> m) {
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
