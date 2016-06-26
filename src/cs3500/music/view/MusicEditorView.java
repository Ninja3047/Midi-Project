package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Combined view that has both the midi view and the gui view
 */
public class MusicEditorView implements GuiView {
  private final GuiViewFrame gui;
  private final MidiView midi;

  public MusicEditorView(GuiViewFrame gui, MidiView midi) {
    this.gui = gui;
    this.midi = midi;
  }

  @Override
  public void display() {
    gui.display();
  }

  @Override
  public void updateTrack() {
    this.midi.updateTrack();
  }

  /**
   * Toggles the play state of the midi
   */
  @Override
  public void togglePlay() {
    midi.togglePlay();
  }

  /**
   * Moves the view to the beginning
   */
  @Override
  public void moveToBeginning() {
    gui.moveToBeginning();
  }

  /**
   * Moves the view to the end
   */
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
