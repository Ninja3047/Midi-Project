package cs3500.music.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs3500.music.model.ModelObserver;
import cs3500.music.model.MusicModel.Mode;
import cs3500.music.model.Note;

/**
 * Draws the composition
 */
public class ConcreteGuiViewPanel extends JPanel {
  private static final int CELL_SIZE = 20;
  private static final int LEFT_OFFSET = 40;
  private final ModelObserver<Note> observer;
  private Color color;
  private double time;

  /**
   * Constructor
   *
   * @param observer observer to read from
   */
  public ConcreteGuiViewPanel(ModelObserver<Note> observer) {
    this.observer = observer;
    this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
    this.requestFocusInWindow();
    this.setFocusable(true);
    this.color = Color.MAGENTA;
  }

  /**
   * Change the mode of the model
   */
  public void changeMode() {
    switch (this.observer.getMode()) {
      case PLAY:
        this.color = Color.MAGENTA;
        break;
      case ADD:
        this.color = Color.GREEN;
        break;
      case DELETE:
        this.color = Color.RED;
        break;
      case NORMAL:
        this.color = Color.MAGENTA;
        break;
      default:
        throw new IllegalArgumentException("Illegal state");
    }
    this.revalidate();
    this.repaint();
  }

  /**
   * Sets the time in the view
   *
   * @param time used to set time
   */
  protected void setTime(double time) {
    this.time = time;
    drawTime(this.getGraphics());
    this.repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    // Handle the default painting
    super.paintComponent(g);
    for (int i = 0; i < observer.getSize() + 1; i++) {
      drawNotes(g, observer.getNotesAtBeat(i), observer.getHighestNote(), this.color);
    }
    drawMeasures(g, observer.getHighestNote(), observer.getLowestNote(), observer.getSize());
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

    List<String> range = observer.getNoteRange();

    for (int i = 0; i < range.size(); i++) {
      g2d.drawString(range.get(range.size() - i - 1), 0,
              CELL_SIZE * (i + 1) + (int) getFont().getSize2D());
    }

    // draw vertical measure lines and label every four at the top
    for (int i = 0; i < lastBeat; i++) {
      if (i % 16 == 0) {
        g2d.drawString(Integer.toString(i), LEFT_OFFSET + CELL_SIZE * i, CELL_SIZE - 4);
      }
      if (i % 4 == 0) {
        g2d.drawLine(LEFT_OFFSET + CELL_SIZE * i, CELL_SIZE,
                LEFT_OFFSET + CELL_SIZE * i, CELL_SIZE * (pitches + 1));
      }
    }
    g2d.drawLine(LEFT_OFFSET + CELL_SIZE * lastBeat, CELL_SIZE,
            LEFT_OFFSET + CELL_SIZE * lastBeat, CELL_SIZE * (pitches + 1));
  }

  /**
   * Draws the given list of notes
   *
   * @param g         graphics
   * @param notes     list of notes to draw
   * @param highPitch highest pitch note in the composition
   */
  private void drawNotes(Graphics g, List<Note> notes, Note highPitch, Color c) {
    for (Note n : notes) {
      drawNote(g, n, highPitch, c);
    }
  }

  /**
   * Draws the given note
   *
   * @param g         graphics
   * @param n         list of notes to draw
   * @param highPitch highest pitch note in the composition
   */
  private void drawNote(Graphics g, Note n, Note highPitch, Color c) {
    Graphics2D g2d = (Graphics2D) g;
    int pos = n.getStart() * CELL_SIZE;
    int pitch = highPitch.toInt() - n.toInt();
    g2d.setColor(Color.BLACK);
    g2d.fillRect(pos + LEFT_OFFSET, pitch * CELL_SIZE + CELL_SIZE,
            CELL_SIZE, CELL_SIZE); // draw head
    g2d.setColor(c);
    for (int i = 1; i < n.getDuration(); i++) { // draw tail
      g2d.fillRect(pos + i * CELL_SIZE + LEFT_OFFSET, pitch * CELL_SIZE
              + CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }
  }

  /**
   * Draws the line at the time specified in the controller
   *
   * @param g graphics
   */
  public void drawTime(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.RED);
    int movement = (int) (time * observer.getSize() * CELL_SIZE);
    g2d.drawLine(LEFT_OFFSET + movement, CELL_SIZE, LEFT_OFFSET + movement,
            LEFT_OFFSET + CELL_SIZE * (observer.getNoteRange().size() - 1));
    if (observer.getMode() == Mode.PLAY) {
      this.scrollRectToVisible(new Rectangle(movement, 1, movement, 1));
    }
  }

  @Override
  public Dimension getPreferredSize() {
    if (observer.getSize() > 0) {
      return new Dimension((observer.getSize() + 3) * CELL_SIZE,
              CELL_SIZE * (observer.getHighestNote().toInt()
                      - observer.getLowestNote().toInt() + 3));
    } else {
      return super.getPreferredSize();
    }
  }
}
