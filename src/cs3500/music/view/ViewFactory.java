package cs3500.music.view;

import cs3500.music.model.ModelObserver;
import cs3500.music.model.Note;

/**
 * Class for creating views
 */
public class ViewFactory {
  /**
   * Creates a view based on the given parameter
   *
   * @param view     the view type to create, either "console", "visual", or "midi"
   * @param observer the observer to read from
   * @return returns the correct view object based on the given parameter
   * @throws IllegalArgumentException when the view is not one of "console", "visual" or "midi"
   */
  public static View createView(String view, ModelObserver<Note> observer)
          throws IllegalArgumentException {
    switch (view) {
      case "console":
        return new ConsoleView(observer);
      case "visual":
        return new GuiViewFrame(observer);
      case "midi":
        return new MidiView(observer);
      case "composite":
        return new MusicEditorView(observer, new GuiViewFrame(observer), new MidiView(observer));
      default:
        throw new IllegalArgumentException();
    }
  }
}
