package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.model.Model;
import cs3500.music.model.MusicModel.Mode;
import cs3500.music.model.Note;
import cs3500.music.view.GuiView;
import cs3500.music.view.MusicEditorView;
import cs3500.music.view.View;

/**
 * Implementation of the controller interface
 */
public class ControllerImpl implements Controller {
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

  private MouseHandler configureMouseDeleteListener() {
    MusicEditorView editorView = (MusicEditorView) this.curView;
    Map<String, Runnable> mouseActions = new HashMap<>();
    MouseHandler m = new MouseHandler();
    mouseActions.put("pressed", () -> {
      int truePitch = curModel.getHighestNote().toInt() - (m.getY() / 20 - 1);
      for (Note n : curModel.getNotesAtBeat((m.getX() - 40) / 20)) {
        if (n.toInt() == truePitch) {
          this.deleteNote(n);
        }
      }
      editorView.updateTrack();
    });
    m.setMouseAction(mouseActions);
    return m;
  }

  private MouseHandler configureMouseAddListener() {
    MusicEditorView editorView = (MusicEditorView) this.curView;
    Map<String, Runnable> mouseActions = new HashMap<>();
    MouseHandler m = new MouseHandler();
    mouseActions.put("pressed", () -> {
      int start = (m.getX() - 40) / 20;
      int pitch = (curModel.getHighestNote().toInt() - (m.getY() / 20 - 1));
      m.setStart(start);
      m.setPitch(pitch);
    });

    mouseActions.put("released", () -> {
          int stop = (m.getX() - 40) / 20 + 1;
      if (m.getPitch() <= curModel.getHighestNote().toInt() &&
              m.getPitch() >= curModel.getLowestNote().toInt() &&
              m.getStart() >= 0 && stop - m.getStart() > 0 && stop <= curModel.getSize()) {
      this.addNoteFromInt(m.getPitch(), m.getStart(), stop);
      editorView.updateTrack();
    }});

    m.setMouseAction(mouseActions);
    return m;
  }

  private void configureKeyBoardListener() {
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    MusicEditorView editorView = (MusicEditorView) this.curView;
    KeyboardHandler kbd = new KeyboardHandler();

    keyPresses.put(KeyEvent.VK_SPACE, () -> {
      if (curModel.getMode() == Mode.PLAY) {
        curModel.setMode(Mode.NORMAL);
      } else {
        curModel.setMode(Mode.PLAY);
      }
      editorView.togglePlay();
      editorView.removeMouseListeners();
      editorView.changeMode();
    });

    keyPresses.put(KeyEvent.VK_ESCAPE, () -> {
      curModel.setMode(Mode.NORMAL);
      editorView.changeMode();
      editorView.removeMouseListeners();
    });

    keyPresses.put(KeyEvent.VK_A, () -> {
      editorView.removeMouseListeners();
      if (curModel.getMode() == Mode.PLAY) {
        editorView.togglePlay();
      }
      if (curModel.getMode() == Mode.ADD) {
        this.contractRange();
        curModel.setMode(Mode.NORMAL);
      } else {
        curModel.setMode(Mode.ADD);

        editorView.addMouseListener(this.configureMouseAddListener());
      }
      editorView.changeMode();
    });

    keyPresses.put(KeyEvent.VK_J, () -> {
      if (curModel.getMode() == Mode.ADD) {
        this.expandNoteRange(curModel.getHighestNote().toInt() + 1);
      }
      editorView.changeMode();
    });

    keyPresses.put(KeyEvent.VK_K, () -> {
      if (curModel.getMode() == Mode.ADD) {
        this.expandNoteRange(curModel.getLowestNote().toInt() - 1);
      }
      editorView.changeMode();
    });

    keyPresses.put(KeyEvent.VK_L, () -> {
      if (curModel.getMode() == Mode.ADD) {
        this.expandBeatRange(curModel.getSize() + 1);
      }
      editorView.changeMode();
    });

    keyPresses.put(KeyEvent.VK_D, () -> {
      editorView.removeMouseListeners();
      if (curModel.getMode() == Mode.PLAY) {
        editorView.togglePlay();
      }
      if (curModel.getMode() == Mode.DELETE) {
        curModel.setMode(Mode.NORMAL);
      } else {
        curModel.setMode(Mode.DELETE);
        editorView.addMouseListener(this.configureMouseDeleteListener());
      }
      editorView.changeMode();
    });

    keyPresses.put(KeyEvent.VK_HOME, () -> {
      editorView.moveToBeginning();
    });

    keyPresses.put(KeyEvent.VK_END, () -> {
      editorView.moveToEnd();
    });

    kbd.setKeyPressedMap(keyPresses);
    editorView.addKeyListener(kbd);
  }

  @Override
  public void setView(GuiView v) {
    this.curView = v;
    this.configureKeyBoardListener();
  }

  /**
   * Method to manually set the command to a MusicEditorView
   * @param v the view
   * @param command the keycommands to run
   */
  public void setView(MusicEditorView v, HashMap<Integer, Runnable> command) {
    this.curView = v;
    KeyboardHandler kbd = new KeyboardHandler();
    kbd.setKeyPressedMap(command);
    v.addKeyListener(kbd);
  }

  @Override
  public void start() {
    this.curView.display();
  }

  @Override
  public void expandNoteRange(int note) {
    this.curModel.expandNoteRange(note);
  }

  @Override
  public void expandBeatRange(int beat) {
    this.curModel.expandBeatRange(beat);
  }

  @Override
  public void contractRange() {
    this.curModel.contractRange();
  }

  @Override
  public void addNoteFromInt(int pitch, int start, int end) {
    this.curModel.addNoteFromInt(pitch, start, end);
  }

  @Override
  public void deleteNote(Note n) {
    this.curModel.deleteNote(n);
  }
}
