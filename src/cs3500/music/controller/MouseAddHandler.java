package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cs3500.music.model.Note;

/**
 * Handles mouse events
 */
public class MouseAddHandler implements MouseListener {

  private int start;
  private int pitch;
  private Controller<Note> controller;

  MouseAddHandler(Controller<Note> c) {
    this.controller = c;
  }

  @Override
  public void mouseClicked(MouseEvent mouseEvent) {
    //System.out.println("test");
  }

  @Override
  public void mousePressed(MouseEvent mouseEvent) {
    this.start = (mouseEvent.getX() - 40) / 20;
    this.pitch = (this.controller.getHighestNote().toInt() - (mouseEvent.getY() / 20 - 1));
  }

  @Override
  public void mouseReleased(MouseEvent mouseEvent) {
    int stop = (mouseEvent.getX() - 40) / 20 + 1;
    if (this.pitch <= this.controller.getHighestNote().toInt() &&
            this.pitch >= this.controller.getLowestNote().toInt() &&
            this.start >= 0 && stop - this.start > 0 && stop <= this.controller.getSize()) {
      this.controller.addNoteFromInt(this.pitch, this.start, stop);
      mouseEvent.getComponent().repaint();
      this.controller.addToTrack(this.pitch, this.start, stop);
    }
  }

  @Override
  public void mouseEntered(MouseEvent mouseEvent) {

  }

  @Override
  public void mouseExited(MouseEvent mouseEvent) {

  }
}
