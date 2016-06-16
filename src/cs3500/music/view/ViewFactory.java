package cs3500.music.view;

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
  public static View createView(String view) throws IllegalArgumentException {
    switch (view) {
      case "console":
        return new ConsoleViewImpl();
      case "visual":
        return new GuiViewFrame();
      case "midi":
        return new MidiViewImpl();
      default:
        throw new IllegalArgumentException();
    }
  }
}
