package cs3500.music.controller;

import java.util.List;

import cs3500.music.model.Composition;
import cs3500.music.model.MusicComposition;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;
import cs3500.music.view.View;

/**
 * Implementation of the controller interface
 */
public class ControllerImpl implements Controller<Note> {
  private Composition<Note> curModel;
  private final View curView;

  /**
   * Constructor
   * @param v view to communicate with
   */
  public ControllerImpl(View v) {
    this.curView = v;
  }

  @Override
  public void start(Readable rd) {
    this.curModel = MusicReader.parseFile(rd, new MusicComposition.Builder());
    this.curView.display(this);
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
