package cs3500.music.view;

import cs3500.music.controller.Controller;
import cs3500.music.model.MusicNote;

/**
 * Class for creating views
 */
public class ViewFactory {
  /**
   * Creates a view based on the given parameter
   * @param view the view type to create, either "console", "visual", or "midi"
   * @return returns the correct view object based on the given parameter
   * @throws IllegalArgumentException when the view is not one of "console", "visual" or "midi"
   */
  public static View createView(String view, Controller<MusicNote> con) throws IllegalArgumentException {
    switch (view) {
      case "console":
        return new ConsoleView(con);
      case "visual":
        return new GuiViewFrame(con);
      case "midi":
        return new MidiView(con);
      default:
        throw new IllegalArgumentException();
    }
  }
}
