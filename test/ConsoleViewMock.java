import cs3500.music.controller.Controller;
import cs3500.music.view.View;

/**
 * Mock for Console View. Instead of System.out it saves it as a String
 */
class ConsoleViewMock implements View {

  private String consoleString;

  @Override
  public void display(Controller con) {
    this.consoleString = con.getConsoleData();
  }

  public String getConsoleString() {
    return this.consoleString;
  }
}
