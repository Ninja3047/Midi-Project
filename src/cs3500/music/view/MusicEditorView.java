package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.Controller;
import cs3500.music.model.Note;

/**
 * Combined view that has both the midi view and the gui view
 */
public class MusicEditorView implements GuiView {
  private final GuiViewFrame gui;
  private final MidiView midi;
  private final Controller<Note> controller;

  public MusicEditorView(Controller<Note> controller, GuiViewFrame gui, MidiView midi) {
    this.gui = gui;
    this.midi = midi;
    this.controller = controller;

    this.gui.addActionListener(actionEvent -> midi.togglePlay());
  }

  @Override
  public void display() {
    gui.display();
  }

  @Override
  public double getTime() {
    return midi.getTime();
  }

  public void updateTrack() {
    this.midi.updateTrack();
  }

  /**
   * Toggles the play state of the midi
   */
  public void togglePlay() {
    midi.togglePlay();
  }

  /**
   * Moves the view to the beginning
   */
  public void moveToBeginning() {
    gui.moveToBeginning();
  }

  /**
   * Moves the view to the end
   */
  public void moveToEnd() {
    gui.moveToEnd();
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    gui.addKeyListener(listener);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    gui.addActionListener(listener);
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

  /*
  @Override
  public String getState() {
    return this.gui.getState();
  }
  */


}
