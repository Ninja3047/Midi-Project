package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cs3500.music.model.Note;

/**
 * Handles mouse events
 */
public class MouseHandler implements MouseListener {

  private int start;
  private int pitch;
  private Controller<Note> controller;

  MouseHandler(Controller<Note> c) {
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
    System.out.println(this.start);
    //System.out.println(mouseEvent.getY());
    System.out.println(this.pitch);
    //mouseEvent.getY();
  }

  @Override
  public void mouseReleased(MouseEvent mouseEvent) {
    this.controller.addNoteFromInt(this.pitch, this.start, (mouseEvent.getX() - 40) / 20);
    mouseEvent.getComponent().repaint();
    //System.out.println(this.controller.getNotesAtBeat(4));
  }

  @Override
  public void mouseEntered(MouseEvent mouseEvent) {

  }

  @Override
  public void mouseExited(MouseEvent mouseEvent) {

  }
}
