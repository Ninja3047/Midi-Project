package cs3500.music.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs3500.music.controller.Controller;
import cs3500.music.model.Note;

/**
 * Draws the composition
 */
public class ConcreteGuiViewPanel extends JPanel {
  private static final int CELL_SIZE = 20;
  private static final int LEFT_OFFSET = 40;
  private final Controller<Note> controller;

  public ConcreteGuiViewPanel(Controller<Note> controller) {
    this.controller = controller;
    this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
    this.requestFocusInWindow();
    this.setFocusable(true);
  }

  @Override
  public void paintComponent(Graphics g) {
    // Handle the default painting
    super.paintComponent(g);
    for (int i = 0; i < controller.getSize() + 1; i++) {
      drawNotes(g, controller.getNotesAtBeat(i), controller.getHighestNote());
    }
    drawMeasures(g, controller.getHighestNote(), controller.getLowestNote(), controller.getSize());
    drawTime(g);
  }

  /**
   * Draws the measures
   *
   * @param g         graphics
   * @param highPitch highest pitch to draw
   * @param lowPitch  lowest pitch to draw
   * @param lastBeat  last beat to draw
   */
  private void drawMeasures(Graphics g, Note highPitch, Note lowPitch, int lastBeat) {
    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.BLACK);
    Stroke thin = new BasicStroke(2);
    Stroke thick = new BasicStroke(3);
    g2d.setStroke(thin);

    int pitches = highPitch.toInt() - lowPitch.toInt() + 1;

    // draw horizontal note lines and pitch labels

    for (int i = 0; i <= pitches; i++) {
      if ((highPitch.toInt() - i + 1) % 12 == 0) {
        g2d.setStroke(thick);
      } else {
        g2d.setStroke(thin);
      }
      g2d.drawLine(LEFT_OFFSET, CELL_SIZE * (i + 1),
              LEFT_OFFSET + CELL_SIZE * lastBeat, CELL_SIZE * (i + 1));
    }

    List<String> range = controller.getNoteRange();

    for (int i = 0; i < range.size(); i++) {
      g2d.drawString(range.get(range.size() - i - 1), 0,
              CELL_SIZE * (i + 1) + (int) getFont().getSize2D());
    }

    int measures = (int) Math.ceil(lastBeat / 4.0);

    // draw vertical measure lines and label every four at the top
    for (int i = 0; i <= measures; i++) {
      if (i % 4 == 0) {
        g2d.drawString(Integer.toString(i * 4), LEFT_OFFSET + 4 * CELL_SIZE * i, CELL_SIZE - 4);
      }
      g2d.drawLine(LEFT_OFFSET + 4 * CELL_SIZE * i, CELL_SIZE,
              LEFT_OFFSET + 4 * CELL_SIZE * i, CELL_SIZE * (pitches + 1));
    }
  }

  /**
   * Draws the given list of notes
   *
   * @param g         graphics
   * @param notes     list of notes to draw
   * @param highPitch highest pitch note in the composition
   */
  private void drawNotes(Graphics g, List<Note> notes, Note highPitch) {
    for (Note n : notes) {
      drawNote(g, n, highPitch);
    }
  }

  /**
   * Draws the given note
   *
   * @param g         graphics
   * @param n         list of notes to draw
   * @param highPitch highest pitch note in the composition
   */
  private void drawNote(Graphics g, Note n, Note highPitch) {
    Graphics2D g2d = (Graphics2D) g;
    int pos = n.getStart() * CELL_SIZE;
    int pitch = highPitch.toInt() - n.toInt();
    g2d.setColor(Color.BLACK);
    g2d.fillRect(pos + LEFT_OFFSET, pitch * CELL_SIZE + CELL_SIZE,
            CELL_SIZE, CELL_SIZE); // draw head
    g2d.setColor(Color.MAGENTA);
    for (int i = 1; i < n.getDuration(); i++) { // draw tail
      g2d.fillRect(pos + i * CELL_SIZE + LEFT_OFFSET, pitch * CELL_SIZE
              + CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }
  }

  public void drawTime(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.RED);
    int movement = (int) (controller.getSize() * CELL_SIZE * controller.getTime());
    g2d.drawLine(LEFT_OFFSET + movement, CELL_SIZE, LEFT_OFFSET + movement,
            LEFT_OFFSET + CELL_SIZE * (controller.getNoteRange().size() - 1));
    this.repaint();
    this.revalidate();
    this.scrollRectToVisible(new Rectangle(movement, 1, 15 * CELL_SIZE, 1));
  }

  @Override
  public Dimension getPreferredSize() {
    if (controller.getSize() > 0) {
      return new Dimension((controller.getSize() + 3) * CELL_SIZE,
              CELL_SIZE * (controller.getHighestNote().toInt()
                      - controller.getLowestNote().toInt() + 3));
    } else {
      return super.getPreferredSize();
    }
  }
}
