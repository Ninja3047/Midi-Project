package cs3500.music.tests;

import org.junit.Test;

import java.io.StringReader;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.model.Model;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiView;
import cs3500.music.view.View;
import cs3500.music.view.ViewFactory;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Tests for the View Factory
 */
public class ViewFactoryTest {
  private final Model<Note> m = MusicReader.parseFile(new StringReader(""),
          new MusicModel.Builder());
  private final Controller<Note> c = new ControllerImpl(m);

  @Test
  public void testVisual() {
    View v = ViewFactory.createView("visual", c);
    assertThat(v, instanceOf(GuiViewFrame.class));
  }

  @Test
  public void testConsole() {
    View v = ViewFactory.createView("console", c);
    assertThat(v, instanceOf(ConsoleView.class));
  }

  @Test
  public void testMidi() {
    View v = ViewFactory.createView("midi", c);
    assertThat(v, instanceOf(MidiView.class));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalid() {
    View v = ViewFactory.createView("Hello", c);
  }
}