package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.*;

import cs3500.music.model.ModelObserver;
import cs3500.music.model.MusicModel.Mode;
import cs3500.music.model.Note;

/**
 * Combined view that has both the midi view and the gui view
 */
public class MusicEditorView implements GuiView {
  private final ModelObserver<Note> observer;
  private final GuiViewFrame gui;
  private final MidiView midi;
  private final Timer timer;

  /**
   * Constructor
   *
   * @param gui  the graphical view
   * @param midi the sound view
   */
  public MusicEditorView(ModelObserver<Note> observer, GuiViewFrame gui, MidiView midi) {
    this.observer = observer;
    this.gui = gui;
    this.midi = midi;
    this.timer = new Timer(20, actionEvent -> this.gui.setTime(this.midi.getTime()));
  }

  @Override
  public void display() {
    gui.display();
  }

  @Override
  public void updateTrack() {
    this.midi.updateTrack();
  }

  @Override
  public void togglePlay() {
    midi.togglePlay();
    if (this.observer.getMode() == Mode.PLAY) {
      timer.start();
    } else {
      timer.stop();
    }
  }

  @Override
  public void moveToBeginning() {
    gui.moveToBeginning();
  }

  @Override
  public void moveToEnd() {
    gui.moveToEnd();
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    gui.addKeyListener(listener);
  }

  @Override
  public void addMouseListener(MouseListener listener) {
    this.gui.addMouseListener(listener);
  }

  @Override
  public void changeMode() {
    this.gui.changeMode();
  }

  @Override
  public void removeMouseListeners() {
    this.gui.removeMouseListeners();
  }
}
