package cs3500.music.controller;

import java.awt.event.ActionListener;

import java.awt.event.KeyListener;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.music.model.Model;
import cs3500.music.model.Note;
import cs3500.music.view.GuiView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiView;
import cs3500.music.view.MusicEditorView;
import cs3500.music.view.View;
import cs3500.music.view.ViewFactory;

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

    keyTypes.put('x', () -> {
      System.out.println("Tilliam Wan is shit (its just a prank bro)");
    });
    keyPresses.put(KeyEvent.VK_X, keyTypes.get('x'));

    KeyboardHandler kbd = new KeyboardHandler();
    kbd.setKeyTypedMap(keyTypes);
    kbd.setKeyPressedMap(keyPresses);

    MusicEditorView tmpView = (MusicEditorView) this.curView;
    System.out.println("kek2");
    tmpView.addKeyListener(kbd);
  }

  @Override
  public void setView(View v) {
    this.curView = v;
    if (this.curView instanceof MusicEditorView) {
      System.out.println("kek");
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
  public float getTime() {
    // TODO
    return 0.0F;
  }

  /**
   * Plays the midi
   */
  class PlayMusic implements Runnable {

    @Override
    public void run() {
      start();
    }
  }

  /**
   * Adds a note to the model
   */
  class AddNote implements Runnable {

    @Override
    public void run() {

    }
  }

  /**
   * Removes a note from the model
   */
  class DeleteNote implements Runnable {

    @Override
    public void run() {

    }
  }

  /**
   * Moves a note in the model
   */
  class MoveNote implements Runnable {

    @Override
    public void run() {

    }
  }

  /**
   * Scroll composition
   */
  class Scroll implements Runnable {

    @Override
    public void run() {

    }
  }
}
