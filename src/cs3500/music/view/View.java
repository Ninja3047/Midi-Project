package cs3500.music.view;

/**
 * Represents any view
 */
public interface View {
  /**
   * Starts the view
   */
  void display();

  /**
   * Get the time position
   *
   * @return the time ratio
   */
  double getTime();
}
