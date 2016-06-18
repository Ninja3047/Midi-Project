package cs3500.music.view;

import java.awt.*;

import javax.swing.*;

/**
 * A dummy view that simply draws a string 
 */
public class ConcreteGuiViewPanel extends JPanel {
  private static final int CELL_SIZE = 20;
  private static final int FONT_SIZE = 12;

  public ConcreteGuiViewPanel() {
    super();
    this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, FONT_SIZE));
  }

  @Override
  public void paintComponent(Graphics g){
    // Handle the default painting
    super.paintComponent(g);
    // Look for more documentation about the Graphics class,
    // and methods on it that may be useful
    drawPitches(g, 40, 50);
  }

  private void drawPitches(Graphics g, int lowNote, int highNote) {
    Graphics2D g2d = (Graphics2D) g;
  }

  private void drawMeasures(Graphics g) {

  }

}
