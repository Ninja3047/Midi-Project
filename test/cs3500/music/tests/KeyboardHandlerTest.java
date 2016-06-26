package cs3500.music.tests;

import org.junit.Test;

import java.awt.event.KeyEvent;
import java.io.StringReader;
import java.util.HashMap;

import cs3500.music.model.ModelObserver;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;
import cs3500.music.view.MusicEditorView;
import cs3500.music.view.ViewFactory;

/**
 * Created by chris on 6/25/16.
 */
public class KeyboardHandlerTest {
  ModelObserver<Note> testModel = MusicReader.parseFile(new StringReader(""), new MusicModel
          .Builder());
  MusicEditorView mev = (MusicEditorView) ViewFactory.createView("player", testModel);
  HashMap<Integer, Runnable> commands = new HashMap<>();
  Runnable testCommand = new MockRunnable();

  @Test
  public void testKeyboard() {
    commands.put(KeyEvent.VK_D, testCommand);
    //testController.setView(mev, commands);


  }
}
