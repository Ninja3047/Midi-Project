package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

import cs3500.music.controller.Controller;
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
  public GuiViewFrame(Controller<Note> controller) {
    this.notesPanel = new ConcreteGuiViewPanel(controller);
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

  public void moveToBeginning() {
    JScrollBar sb = this.displayPanel.getHorizontalScrollBar();
    sb.setValue(0);
  }

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
  public double getTime() {
    return 0;
  }

  @Override
  public void changeMode() {
    ConcreteGuiViewPanel tmpPanel = this.notesPanel;
    tmpPanel.changeMode();
  }
}
