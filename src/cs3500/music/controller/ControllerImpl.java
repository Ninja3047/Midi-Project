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
  private Mode mode;

  /**
   * Constructor for the controller
   *
   * @param m model to communicate with
   */
  public ControllerImpl(Model<Note> m) {
    this.curModel = m;
    this.mode = Mode.NORMAL;
  }

  private MouseHandler configureMouseDeleteListener() {
    MusicEditorView editorView = (MusicEditorView) this.curView;
    Map<String, Runnable> mouseActions = new HashMap<>();
    MouseHandler m = new MouseHandler();
    mouseActions.put("pressed", () -> {
      int truePitch = this.getHighestNote().toInt() - (m.getY() / 20 - 1);
      for (Note n : this.getNotesAtBeat((m.getX() - 40) / 20)) {
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
      int pitch = (this.getHighestNote().toInt() - (m.getY() / 20 - 1));
      m.setStart(start);
      m.setPitch(pitch);
    });

    mouseActions.put("released", () -> {
          int stop = (m.getX() - 40) / 20 + 1;
    if (m.getPitch() <= this.getHighestNote().toInt() &&
            m.getPitch() >= this.getLowestNote().toInt() &&
            m.getStart() >= 0 && stop - m.getStart() > 0 && stop <= this.getSize()) {
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
    MouseHandler mh = new MouseHandler();

    keyPresses.put(KeyEvent.VK_SPACE, () -> {
      //System.out.println("Now Playing/Pausing");
      if (this.mode == Mode.PLAY) {
        this.mode = Mode.NORMAL;
      } else {
        this.mode = Mode.PLAY;
      }
      editorView.togglePlay();
      editorView.removeMouseListeners();
      editorView.changeMode();
    });

    keyPresses.put(KeyEvent.VK_ESCAPE, () -> {
      this.mode = Mode.NORMAL;
      editorView.changeMode();
      editorView.removeMouseListeners();
    });

    keyPresses.put(KeyEvent.VK_A, () -> {
      editorView.removeMouseListeners();
      if (this.mode == Mode.PLAY) {
        editorView.togglePlay();
      }
      if (this.mode == Mode.ADD) {
        this.mode = Mode.NORMAL;
      } else {
        this.mode = Mode.ADD;

        editorView.addMouseListener(this.configureMouseAddListener());
      }
      editorView.changeMode();
    });

    keyPresses.put(KeyEvent.VK_D, () -> {
      editorView.removeMouseListeners();
      if (this.mode == Mode.PLAY) {
        editorView.togglePlay();
      }
      if (this.mode == Mode.DELETE) {
        this.mode = Mode.NORMAL;
      } else {
        this.mode = Mode.DELETE;
        editorView.addMouseListener(this.configureMouseDeleteListener());
      }
      editorView.changeMode();
    });

    keyPresses.put(KeyEvent.VK_HOME, () -> {
      System.out.println("Moving to the beginning of the composition");
      editorView.moveToBeginning();
    });

    keyPresses.put(KeyEvent.VK_END, () -> {
      System.out.println("Moving to the end of the composition");
      editorView.moveToEnd();
    });

    kbd.setKeyPressedMap(keyPresses);
    editorView.addKeyListener(kbd);
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
  public void expandNoteRange(int note) {
    this.curModel.expandNoteRange(note);
  }

  @Override
  public void expandBeatRange(int beat) {
    this.curModel.expandBeatRange(beat);
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
  public void deleteNote(Note n) {
    this.curModel.deleteNote(n);
  }

  @Override
  public double getTime() {
    if (curView != null) {
      return this.curView.getTime();
    } else {
      return 0;
    }
  }

  @Override
  public String getMode() {
    return this.mode.getValue();
  }

  /**
   * Enum representing the mode of the controller
   */
  protected enum Mode {
    NORMAL("normal"), DELETE("delete"), ADD("add"), PLAY("play");

    String value;

    Mode(String value) {
      this.value = value;
    }

    String getValue() {
      return this.value;
    }
  }
}
