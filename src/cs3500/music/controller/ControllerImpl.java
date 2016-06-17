package cs3500.music.controller;

import cs3500.music.model.ICompositionModel;
import cs3500.music.model.Note;
import cs3500.music.util.CompBuilderImpl;
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
   * @param m model to communicate with
   * @param v view to communicate with
   */
  public ControllerImpl(ICompositionModel<Note> m, View v) {
    this.curModel = m;
    this.curView = v;
  }

  @Override
  public void start(Readable rd) {
    this.curModel = MusicReader.parseFile(rd, new CompBuilderImpl(this.curModel));
    this.curView.display(this);
  }

  @Override
  public String getConsoleData() {
    return this.curModel.printNotes();
  }
}
