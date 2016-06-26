package cs3500.music.tests;

import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.StringReader;
import java.util.HashMap;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.model.Model;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;
import cs3500.music.view.MusicEditorView;
import cs3500.music.view.View;
import cs3500.music.view.ViewFactory;

/**
 * Created by chris on 6/25/16.
 */
public class KeyboardHandlerTest {
  Model<Note> testModel = MusicReader.parseFile(new StringReader(""), new MusicModel.Builder());
  ControllerImpl testController = new ControllerImpl(testModel);
  MusicEditorView mev = (MusicEditorView) ViewFactory.createView("player", testController);
  HashMap<Integer, Runnable> commands = new HashMap<>();
  Runnable testCommand = new MockRunnable();

  @Test
  public void testKeyboard() {
    commands.put(KeyEvent.VK_D, testCommand);
    testController.setView(mev, commands);


  }
}
