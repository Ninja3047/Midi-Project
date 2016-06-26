package cs3500.music.view;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.*;

import cs3500.music.model.ModelObserver;
import cs3500.music.model.Note;

/**
 * A skeleton Frame for the visual View
 */
public class GuiViewFrame extends JFrame implements GuiView {

  private final JScrollPane displayPanel;
  private final ConcreteGuiViewPanel notesPanel;

  /**
   * Creates new GuiView
   */
  public GuiViewFrame(ModelObserver<Note> observer) {
    this.notesPanel = new ConcreteGuiViewPanel(observer);
    this.notesPanel.setFocusable(true);
    this.displayPanel = new JScrollPane(notesPanel);

    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.setTitle("Music Player");

    this.getContentPane().add(displayPanel, BorderLayout.CENTER);

    this.pack();
  }

  @Override
  public void display() {
    this.setVisible(true);
  }

  @Override
  public void moveToBeginning() {
    JScrollBar sb = this.displayPanel.getHorizontalScrollBar();
    sb.setValue(0);
  }

  @Override
  public void moveToEnd() {
    JScrollBar sb = this.displayPanel.getHorizontalScrollBar();
    sb.setValue(sb.getMaximum());
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(1333, 390);
  }

  @Override
  public void addKeyListener(KeyListener listen) {
    this.notesPanel.addKeyListener(listen);
  }

  @Override
  public void addMouseListener(MouseListener listener) {
    this.notesPanel.addMouseListener(listener);
  }

  @Override
  public void removeMouseListeners() {
    MouseListener[] listeners = this.notesPanel.getMouseListeners();
    for (MouseListener listener : listeners) {
      this.notesPanel.removeMouseListener(listener);
    }
  }

  @Override
  public void changeMode() {
    ConcreteGuiViewPanel tmpPanel = this.notesPanel;
    tmpPanel.changeMode();
  }

  @Override
  public void togglePlay() {

  }

  @Override
  public void updateTrack() {

  }

  public void setTime(double time) {
    notesPanel.setTime(time);
  }
}
