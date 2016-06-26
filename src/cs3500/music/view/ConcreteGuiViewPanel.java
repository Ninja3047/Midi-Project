package cs3500.music.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs3500.music.controller.Controller;
import cs3500.music.model.ModelObserver;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;

/**
 * Draws the composition
 */
public class ConcreteGuiViewPanel extends JPanel {
  private static final int CELL_SIZE = 20;
  private static final int LEFT_OFFSET = 40;
  private final Timer timer;
  private Color color;
  private MusicModel.Mode state;
  private double time;
  private final ModelObserver<Note> observer;

  public ConcreteGuiViewPanel(ModelObserver<Note> observer) {
    this.observer = observer;
    this.time = 0;
    this.timer = new Timer(10, actionEvent -> {
      drawTime(this.getGraphics());
      this.repaint();
      this.time = observer.getTime() * observer.getSize();
    });
    this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
    this.requestFocusInWindow();
    this.setFocusable(true);
    this.color = Color.MAGENTA;
    this.state = MusicModel.Mode.NORMAL;
  }

  public void changeMode() {
    switch (this.observer.getMode()) {
      case PLAY:
        this.state = MusicModel.Mode.PLAY;
        this.color = Color.MAGENTA;
        this.timer.start();
        break;
      case ADD:
        this.state = MusicModel.Mode.ADD;
        this.color = Color.GREEN;
        break;
      case DELETE:
        this.state = MusicModel.Mode.DELETE;
        this.color = Color.RED;
        break;
      case NORMAL:
        this.state = MusicModel.Mode.NORMAL;
        this.color = Color.MAGENTA;
        this.timer.stop();
        break;
      default:
        throw new IllegalArgumentException("Illegal state");
    }
    this.revalidate();
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
    int movement = (int) (time * CELL_SIZE);
    g2d.drawLine(LEFT_OFFSET + movement, CELL_SIZE, LEFT_OFFSET + movement,
            LEFT_OFFSET + CELL_SIZE * (observer.getNoteRange().size() - 1));
    if (this.state.equals("play")) {
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
