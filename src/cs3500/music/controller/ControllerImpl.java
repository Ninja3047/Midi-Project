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
    MusicEditorView editorview = (MusicEditorView) this.curView;
    KeyboardHandler kbd = new KeyboardHandler();

    keyTypes.put('p', () -> {
      System.out.println("Now Playing/Pausing");
      editorview.togglePlay();
    });
    keyPresses.put(KeyEvent.VK_P, keyTypes.get('p'));

    keyTypes.put('a', () -> {
      System.out.println("Adding a note");
      // TODO when a is pressed, add a mouse handler to the view to add a note
      // and then remove it after
    });
    keyPresses.put(KeyEvent.VK_A, keyTypes.get('a'));

    keyTypes.put('d', () -> {
      System.out.println("Deleting a note");
      // TODO when d is pressed, add a mouse handler to the view to delete a note
      // and then remove it after
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

    editorview.addMouseListener(new MouseHandler());
    editorview.addKeyListener(kbd);
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
