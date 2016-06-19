/**
 * Class to test MidiView
 */
public class MidiViewTest {
  /*
  MusicModel<Note> model = MusicReader.parseFile(new StringReader("tempo 200000\n" +
            "note 0 2 1 64 72\n" +
            "note 0 7 1 55 70\n" +
            "note 2 4 1 62 72\n" +
          "note 4 6 1 60 71"), new MusicModelComposition.Builder());
  private Controller con = new ControllerImpl(model);
  private MidiViewMock v = new MidiViewMock(con);

  @Test
  public void testMidiInputs() {
    con.setView(v);
    con.start();
    Integer[] expectedPitches = {55, 60, 62, 64};
    Integer[] expectedStarts = {0, 384, 192, 0};
    Integer[] expectedEnds = {768, 672, 480, 288};
    assertEquals(200000, v.getTempo());
    assertArrayEquals(expectedPitches, v.getPitches().toArray());
    assertArrayEquals(expectedStarts, v.getStarts().toArray());
    assertArrayEquals(expectedEnds, v.getEnds().toArray());
  }
  */
}
