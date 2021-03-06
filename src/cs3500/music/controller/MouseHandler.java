package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Configurable MouseHandler for the controller
 */
public class MouseHandler implements MouseListener {

  private Map<String, Runnable> mouseAction;
  private int x;
  private int y;
  private int start;
  private int pitch;

  /**
   * Constructor
   */
  public MouseHandler() {
    this.mouseAction = new HashMap<>();
  }

  public int getPitch() {
    return this.pitch;
  }

  public void setPitch(int pitch) {
    this.pitch = pitch;
  }

  public int getStart() {
    return this.start;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  /**
   * Sets the x and y from mouseEvent
   */
  private void updateData(MouseEvent mouseEvent) {
    this.x = mouseEvent.getX();
    this.y = mouseEvent.getY();
  }

  public void setMouseAction(Map<String, Runnable> newMouseAction) {
    this.mouseAction = newMouseAction;
  }

  @Override
  public void mouseClicked(MouseEvent mouseEvent) {

  }

  @Override
  public void mousePressed(MouseEvent mouseEvent) {
    if (this.mouseAction.containsKey("pressed")) {
      this.updateData(mouseEvent);
      this.mouseAction.get("pressed").run();
      mouseEvent.getComponent().repaint();
    }
  }

  @Override
  public void mouseReleased(MouseEvent mouseEvent) {
    if (this.mouseAction.containsKey("released")) {
      this.updateData(mouseEvent);
      this.mouseAction.get("released").run();
      mouseEvent.getComponent().repaint();
    }
  }

  @Override
  public void mouseEntered(MouseEvent mouseEvent) {

  }

  @Override
  public void mouseExited(MouseEvent mouseEvent) {

  }
}
