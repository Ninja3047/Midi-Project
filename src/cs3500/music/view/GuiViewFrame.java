package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;

import cs3500.music.controller.Controller;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.Note;

/**
 * A skeleton Frame for the visual View
 */
public class GuiViewFrame extends JFrame implements GuiView {

  private final JScrollPane displayPanel;
  private final JButton play;

  /**
   * Creates new GuiView
   */
  public GuiViewFrame(Controller<Note> controller) {
    this.displayPanel = new JScrollPane(new ConcreteGuiViewPanel(controller));
    this.displayPanel.setFocusable(true);
    this.displayPanel.addMouseListener(new MouseHandler());
    this.play = new JButton("▶");
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.setTitle("Music Player");
    this.setLayout(new BorderLayout());
    this.getContentPane().add(displayPanel, BorderLayout.CENTER);
    this.getContentPane().add(play, BorderLayout.SOUTH);
    this.pack();
  }

  @Override
  public void display() {
    this.setVisible(true);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(1333, 390);
  }

  /*
  @Override
  public void addKeyListener(KeyListener listen) {
    System.out.println("hello?");
    //ConcreteGuiViewPanel tmp = (ConcreteGuiViewPanel) this.displayPanel;
    this.displayPanel.addKeyListener(listen);
  }
  */

  @Override
  public void addKeyListener(KeyListener listen) {
    this.getContentPane().;
  }

  @Override
  public void addActionListener(ActionListener listener) {
    play.addActionListener(listener);
  }
}
