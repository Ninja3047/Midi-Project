package cs3500.music.view;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerImpl;

/**
 * Console View implementation
 */
public class ConsoleViewImpl implements ConsoleView {

  @Override
  public void display(Controller con) {
    System.out.println(con.getString());
  }

}
