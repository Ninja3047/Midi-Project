import cs3500.music.controller.Controller;
import cs3500.music.model.Note;
import cs3500.music.view.View;

/**
 * Mock for Console View. Instead of System.out it saves it as a String
 */
class ConsoleViewMock implements View {

  private Controller<Note> controller;
  private String consoleString;

  ConsoleViewMock(Controller<Note> controller) {
    this.controller = controller;
  }

  @Override
  public void display() {
    //this.consoleString = this.controller.;
  }

  protected String getConsoleString() {
    return this.consoleString;
  }
}
