package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.model.Model;
import cs3500.music.model.MusicModel.Mode;
import cs3500.music.model.Note;
import cs3500.music.view.GuiView;

/**
 * Implementation of the controller interface
 */
public class ControllerImpl implements Controller {
  private final Model<Note> curModel;
  private GuiView curView;

  /**
   * Constructor for the controller
   *
   * @param m model to communicate with
   */
  public ControllerImpl(Model<Note> m) {
    this.curModel = m;
  }

  /**
   * Creates a MouseListener that is used for deleting notes
   *
   * @return the listener
   */
  private MouseHandler configureMouseDeleteListener() {
    Map<String, Runnable> mouseActions = new HashMap<>();
    MouseHandler m = new MouseHandler();
    mouseActions.put("pressed", () -> {
      int truePitch = curModel.getHighestNote().toInt() - (m.getY() / 20 - 1);
      for (Note n : curModel.getNotesAtBeat((m.getX() - 40) / 20)) {
        if (n.toInt() == truePitch) {
          this.deleteNote(n);
        }
      }
      curView.updateTrack();
    });
    m.setMouseAction(mouseActions);
    return m;
  }

  /**
   * Creates a MouseListener that is used for adding notes
   * @return the listener
   */
  private MouseHandler configureMouseAddListener() {
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
        curView.updateTrack();
      }
    });

    m.setMouseAction(mouseActions);
    return m;
  }

  /**
   * Creates a KeyBoardListener that is used to control the view
   */
  private void configureKeyBoardListener() {
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    KeyboardHandler kbd = new KeyboardHandler();

    keyPresses.put(KeyEvent.VK_SPACE, () -> {
      if (curModel.getMode() == Mode.PLAY) {
        this.contractRange();
        curModel.setMode(Mode.NORMAL);
      } else {
        curModel.setMode(Mode.PLAY);
      }
      curView.togglePlay();
      curView.removeMouseListeners();
      curView.changeMode();
    });

    keyPresses.put(KeyEvent.VK_ESCAPE, () -> {
      this.contractRange();
      curModel.setMode(Mode.NORMAL);
      curView.changeMode();
      curView.removeMouseListeners();
    });

    keyPresses.put(KeyEvent.VK_A, () -> {
      curView.removeMouseListeners();
      if (curModel.getMode() == Mode.PLAY) {
        curView.togglePlay();
      }
      if (curModel.getMode() == Mode.ADD) {
        this.contractRange();
        curModel.setMode(Mode.NORMAL);
      } else {
        curModel.setMode(Mode.ADD);

        curView.addMouseListener(this.configureMouseAddListener());
      }
      curView.changeMode();
    });

    keyPresses.put(KeyEvent.VK_J, () -> {
      if (curModel.getMode() == Mode.ADD) {
        this.expandNoteRange(curModel.getHighestNote().toInt() + 1);
      }
      curView.changeMode();
    });

    keyPresses.put(KeyEvent.VK_K, () -> {
      if (curModel.getMode() == Mode.ADD) {
        this.expandNoteRange(curModel.getLowestNote().toInt() - 1);
      }
      curView.changeMode();
    });

    keyPresses.put(KeyEvent.VK_L, () -> {
      if (curModel.getMode() == Mode.ADD) {
        this.expandBeatRange(curModel.getSize() + 1);
      }
      curView.changeMode();
    });

    keyPresses.put(KeyEvent.VK_D, () -> {
      curView.removeMouseListeners();
      if (curModel.getMode() == Mode.PLAY) {
        curView.togglePlay();
      }
      if (curModel.getMode() == Mode.DELETE) {
        curModel.setMode(Mode.NORMAL);
      } else {
        curModel.setMode(Mode.DELETE);
        curView.addMouseListener(this.configureMouseDeleteListener());
      }
      curView.changeMode();
    });

    keyPresses.put(KeyEvent.VK_HOME, () -> {
      curView.moveToBeginning();
    });

    keyPresses.put(KeyEvent.VK_END, () -> {
      curView.moveToEnd();
    });

    kbd.setKeyPressedMap(keyPresses);
    curView.addKeyListener(kbd);
  }

  @Override
  public void setView(GuiView v) {
    this.curView = v;
    this.configureKeyBoardListener();
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
