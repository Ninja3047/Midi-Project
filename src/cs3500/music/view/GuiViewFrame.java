package cs3500.music.view;

import java.awt.*;

import javax.swing.*;

import cs3500.music.controller.Controller;
import cs3500.music.model.MusicNote;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends javax.swing.JFrame implements View {

  private final JScrollPane displayPanel; // You may want to refine this to a subtype of JPanel
  private final Controller<MusicNote> controller;

  /**
   * Creates new GuiView
   */
  public GuiViewFrame(Controller<MusicNote> controller) {
    this.controller = controller;
    this.displayPanel = new JScrollPane(new ConcreteGuiViewPanel(controller));
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
  public Dimension getPreferredSize(){
    return new Dimension(1333, 400);
  }
}
