package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.music.model.Model;
import cs3500.music.model.Note;
import cs3500.music.view.MusicEditorView;
import cs3500.music.view.View;

/**
 * Implementation of the controller interface
 */
public class ControllerImpl implements Controller<Note> {
  private final Model<Note> curModel;
  private View curView;

  /**
   * Constructor for the controller
   *
   * @param m model to communicate with
   */
  public ControllerImpl(Model<Note> m) {
    this.curModel = m;
  }

  private void configureKeyBoardListener() {
    Map<Character, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();
    MusicEditorView editorview = (MusicEditorView) this.curView;
    KeyboardHandler kbd = new KeyboardHandler();

    keyTypes.put('p', () -> {
      System.out.println("Now Playing/Pausing");
      editorview.togglePlay();
    });
    keyPresses.put(KeyEvent.VK_P, keyTypes.get('p'));

    keyTypes.put('a', () -> {
      editorview.setState("add");
      editorview.setupMouseListener(new MouseAddHandler(this));
    });
    keyPresses.put(KeyEvent.VK_A, keyTypes.get('a'));

    keyTypes.put('d', () -> {
      editorview.setState("delete");
      editorview.setupMouseListener(new MouseDelHandler(this));
    });
    keyPresses.put(KeyEvent.VK_D, keyTypes.get('d'));

    keyPresses.put(KeyEvent.VK_HOME, () -> {
      System.out.println("Moving to the beginning of the composition");
      editorview.moveToBeginning();
    });

    keyPresses.put(KeyEvent.VK_END, () -> {
      System.out.println("Moving to the end of the composition");
      editorview.moveToEnd();
    });

    kbd.setKeyTypedMap(keyTypes);
    kbd.setKeyPressedMap(keyPresses);
    kbd.setKeyReleasedMap(keyReleases);

    editorview.addKeyListener(kbd);
  }

  @Override
  public void setView(View v) {
    this.curView = v;
    if (this.curView instanceof MusicEditorView) {
      this.configureKeyBoardListener();
    }
  }

  @Override
  public void start() {
    this.curView.display();
  }

  @Override
  public List<Note> getNotes() {
    return this.curModel.getAllNotes();
  }

  @Override
  public List<Note> getNotesAtBeat(int beat) {
    return this.curModel.getNotesAtBeat(beat);
  }

  @Override
  public Note getHighestNote() {
    return this.curModel.getHighestNote();
  }

  @Override
  public Note getLowestNote() {
    return this.curModel.getLowestNote();
  }

  @Override
  public List<String> getNoteRange() {
    return this.curModel.getNoteRange();
  }

  @Override
  public int getTempo() {
    return this.curModel.getTempo();
  }

  @Override
  public int getSize() {
    return this.curModel.getSize();
  }

  @Override
  public void addNoteFromInt(int pitch, int start, int end) {
    this.curModel.addNoteFromInt(pitch, start, end);
  }

  @Override
  public void addToTrack(int pitch, int start, int stop) {
    this.curView.addToTrack(pitch, start, stop);
  }

  @Override
  public void deleteNote(Note n) {
    this.curModel.deleteNote(n);
  }

  @Override
  public void deleteFromTrack(int pitch, int start, int stop) {
    this.curView.deleteFromTrack(pitch, start, stop);
  }

  @Override
  public double getTime() {
    if (curView != null) {
      return this.curView.getTime();
    } else {
      return 0;
    }
  }
}
