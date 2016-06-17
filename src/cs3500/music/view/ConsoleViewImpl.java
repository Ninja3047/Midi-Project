package cs3500.music.view;

import cs3500.music.controller.Controller;

/**
 * The Console View
 */
public class ConsoleViewImpl implements View {
  @Override
  public void display(Controller con) {
    System.out.println(con.getConsoleData());
  }
}
