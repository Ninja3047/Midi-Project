package cs3500.music.controller;

import cs3500.music.model.ICompositionModel;
import cs3500.music.model.Note;
import cs3500.music.view.View;

/**
 * Interface that represents a Controller that interacts with the model & view
 */
public interface Controller {

  void setCurView(View v);

  void start(Readable rd);

  String getString();

}
