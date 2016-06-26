package cs3500.music.tests;

/**
 * Created by chris on 6/25/16.
 */
public class MockRunnable implements Runnable {

  private String testString;

  MockRunnable() {
    this.testString = "";
  }

  @Override
  public void run() {
    this.testString = "Key press works";
  }

  public String getTestString() {
    return this.testString;
  }
}
