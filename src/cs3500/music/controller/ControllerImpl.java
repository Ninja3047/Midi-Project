package cs3500.music.controller;

import cs3500.music.model.ICompositionModel;
import cs3500.music.model.Note;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.CompositionBuilderImpl;
import cs3500.music.util.MusicReader;
import cs3500.music.view.View;

/**
 * Implementation of the controller interface
 */
public class ControllerImpl implements Controller {

  private ICompositionModel<Note> curModel;
  private View curView;

  public ControllerImpl(ICompositionModel<Note> m) {
    this.curModel = m;
  }

  @Override
  public void setCurView(View v) {
    this.curView = v;
  }

  @Override
  public void start(Readable rd) {
    if (this.curView == null) {
      throw new IllegalArgumentException("Did not set a view");
    }
    //MusicReader kek = new MusicReader(rd, new CompositionBuilderImpl(this.curModel));
    this.curModel = MusicReader.parseFile(rd, new CompositionBuilderImpl(this.curModel));
    this.curView.display(this);
  }

  public String getString() {
    return this.curModel.printNotes();
  }

}
