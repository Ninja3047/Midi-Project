package cs3500.music.view;

/**
 * Interface to represent gui specific methods
 */
public interface GuiView extends View {
  /**
   * Jumps to position
   */
  void jumpTo();

  /**
   * Deletes a note
   */
  void deleteNote();

  /**
   * Adds a note
   */
  void addNote();

  /**
   * Scrolls with key event
   */
  void scrollKey();
}
