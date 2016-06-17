package cs3500.music.controller;

import java.util.List;

import cs3500.music.model.CompositionModel;
import cs3500.music.model.ICompositionModel;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;
import cs3500.music.view.View;

/**
 * Implementation of the controller interface
 */
public class ControllerImpl implements Controller {
  private ICompositionModel<Note> curModel;
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
    this.curModel = MusicReader.parseFile(rd, new CompositionModel.Builder());
    this.curView.display(this);
  }

  @Override
  public String getConsoleData() {
    return this.curModel.printNotes();
  }

  @Override
  public List<Note> getNotes() {
    return this.curModel.getAllNotes();
  }

  @Override
  public int getTempo() {
    return this.curModel.getTempo();
  }
}
