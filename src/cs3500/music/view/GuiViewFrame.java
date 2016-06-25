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

  private final JPanel notesPanel;

  /**
   * Creates new GuiView
   */
  public GuiViewFrame(Controller<Note> controller) {
    this.notesPanel = new ConcreteGuiViewPanel(controller);
    this.notesPanel.setFocusable(true);
    this.displayPanel = new JScrollPane(notesPanel);

    this.play = new JButton("▶");
    this.play.setFocusable(false);

    this.expandRight = new BasicArrowButton(SwingConstants.EAST);
    this.expandUp = new BasicArrowButton(SwingConstants.NORTH);
    this.expandDown = new BasicArrowButton(SwingConstants.SOUTH);

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

  @Override
  public void addToTrack(int pitch, int start, int end) {

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
  public void setupMouseListener(MouseListener listener) {
    MouseListener[] listeners = this.notesPanel.getMouseListeners();
    if (listeners.length > 0) {
      this.notesPanel.removeMouseListener(listeners[0]);
    } else {
      this.notesPanel.addMouseListener(listener);
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
  public void setState(String state) {
    ConcreteGuiViewPanel tmpPanel = (ConcreteGuiViewPanel) this.notesPanel;
    switch (state) {
      case "add":
        tmpPanel.setState(ConcreteGuiViewPanel.Mode.ADD);
        break;
      case "delete":
        tmpPanel.setState(ConcreteGuiViewPanel.Mode.DELETE);
        break;
      case "normal":
        tmpPanel.setState(ConcreteGuiViewPanel.Mode.NORMAL);
        break;
      default:
        throw new IllegalArgumentException("Illegal state");
    }
  }
}
