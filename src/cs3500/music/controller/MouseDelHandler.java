package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cs3500.music.model.Note;

/**
 * Handles mouse events
 */
public class MouseDelHandler implements MouseListener {

  private int start;
  private int pitch;
  private Controller<Note> controller;

  MouseDelHandler(Controller<Note> c) {
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
    for (Note n : this.controller.getNotesAtBeat(start)) {
      if (n.toInt() == this.pitch) {
        this.controller.deleteFromTrack(this.pitch, this.start, 0);
        this.controller.deleteNote(n);
        mouseEvent.getComponent().repaint();
      }
    }
  }

  @Override
  public void mouseReleased(MouseEvent mouseEvent) {
    /*
    this.controller.deleteNoteFromInt(this.pitch, this.start, stop);
      mouseEvent.getComponent().repaint();
      this.controller.deleteFromTrack(this.pitch, this.start, stop);
      */
  }

  @Override
  public void mouseEntered(MouseEvent mouseEvent) {

  }

  @Override
  public void mouseExited(MouseEvent mouseEvent) {

  }
}
