package cs3500.music.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

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

    this.gui.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        midi.togglePlay();
      }
    });
  }

  @Override
  public void display() {
    gui.display();
    midi.display();
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    System.out.println("kek3");
    gui.addKeyListener(listener);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    gui.addActionListener(listener);
  }
}
