package cs3500.music.view;

import cs3500.music.controller.Controller;

/**
 * Represents any view
 */
public interface View {
  /**
   * Starts the view
   * @param con the controller to communicate with
   */
  void display(Controller con);
}
