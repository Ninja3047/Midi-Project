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

  private final JButton play;

  private final JButton expandRight;
  private final JButton expandUp;
  private final JButton expandDown;

  private final ConcreteGuiViewPanel notesPanel;

  /**
   * Creates new GuiView
   */
  public GuiViewFrame(Controller<Note> controller) {
    this.notesPanel = new ConcreteGuiViewPanel(controller);
    this.notesPanel.setFocusable(true);
    this.displayPanel = new JScrollPane(notesPanel);

    this.play = new JButton("▶");
    this.play.setFocusable(false);

    this.expandRight = new JButton(">");
    this.expandRight.addActionListener(actionEvent -> {
      controller.expandBeatRange(controller.getSize() + 1);
      this.repaint();
    });
    this.expandRight.setFocusable(false);
    this.expandUp = new JButton("^");
    this.expandUp.addActionListener(actionEvent -> {
      controller.expandNoteRange(controller.getHighestNote().toInt() + 1);
      this.repaint();
    });
    this.expandUp.setFocusable(false);
    this.expandDown = new JButton("v");
    this.expandDown.addActionListener(actionEvent -> {
      controller.expandNoteRange(controller.getLowestNote().toInt() - 1);
      this.repaint();
    });
    this.expandDown.setFocusable(false);

    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.setTitle("Music Player");

    GroupLayout layout = new GroupLayout(this.getContentPane());
    this.setLayout(layout);

    layout.setHorizontalGroup(layout.createParallelGroup()
            .addComponent(displayPanel)
            .addGroup(layout.createSequentialGroup()
                    .addComponent(play)
                    .addComponent(expandUp)
                    .addComponent(expandDown)
                    .addComponent(expandRight)));

    layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(displayPanel)
            .addGroup(layout.createParallelGroup()
                    .addComponent(play)
                    .addComponent(expandUp)
                    .addComponent(expandDown)
                    .addComponent(expandRight)));

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
  public void addActionListener(ActionListener listener) {
    play.addActionListener(listener);
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
