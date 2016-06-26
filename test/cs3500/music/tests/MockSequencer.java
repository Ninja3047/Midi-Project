package cs3500.music.tests;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.sound.midi.Transmitter;

/**
 * Mock of a Sequencer
 */
public class MockSequencer implements Sequencer {
  private Sequence seq;

  @Override
  public void setSequence(Sequence seq) {
    this.seq = seq;
  }

  /**
   * Gets data that was in the track
   *
   * @return the data as a String
   */
  public String getData() {
    Track t = this.seq.getTracks()[0];
    MetaMessage tempoMessage = (MetaMessage) t.get(0).getMessage();
    String tempo = Arrays.toString(tempoMessage.getData());
    StringBuilder sb = new StringBuilder("Tempo: " + tempo + " (bytes)\n");
    for (int i = 1; i < t.size() - 1; i += 1) {
      MidiEvent me = t.get(i);
      long time = me.getTick();
      ShortMessage sm = (ShortMessage) me.getMessage();
      String command = "NOTE_OFF";
      if (sm.getCommand() == ShortMessage.NOTE_ON) {
        command = "NOTE_ON";
      } else if (sm.getCommand() == ShortMessage.PROGRAM_CHANGE) {
        command = "PROGRAM_CHANGE";
      }
      int pitch = sm.getData1();
      sb.append(time).append(" ").append(command).append(" ").append(pitch).append("\n");
    }
    return sb.toString();
  }

  @Override
  public Sequence getSequence() {
    return null;
  }

  @Override
  public void setSequence(InputStream inputStream) throws IOException, InvalidMidiDataException {

  }

  @Override
  public void start() {

  }

  @Override
  public void stop() {

  }

  @Override
  public boolean isRunning() {
    return false;
  }

  @Override
  public void startRecording() {

  }

  @Override
  public void stopRecording() {

  }

  @Override
  public boolean isRecording() {
    return false;
  }

  @Override
  public void recordEnable(Track track, int i) {

  }

  @Override
  public void recordDisable(Track track) {

  }

  @Override
  public float getTempoInBPM() {
    return 0;
  }

  @Override
  public void setTempoInBPM(float v) {

  }

  @Override
  public float getTempoInMPQ() {
    return 0;
  }

  @Override
  public void setTempoInMPQ(float v) {

  }

  @Override
  public float getTempoFactor() {
    return 0;
  }

  @Override
  public void setTempoFactor(float v) {

  }

  @Override
  public long getTickLength() {
    return 0;
  }

  @Override
  public long getTickPosition() {
    return 0;
  }

  @Override
  public void setTickPosition(long l) {

  }

  @Override
  public long getMicrosecondLength() {
    return 0;
  }

  @Override
  public long getMicrosecondPosition() {
    return 0;
  }

  @Override
  public void setMicrosecondPosition(long l) {

  }

  @Override
  public SyncMode getMasterSyncMode() {
    return null;
  }

  @Override
  public void setMasterSyncMode(SyncMode syncMode) {

  }

  @Override
  public SyncMode[] getMasterSyncModes() {
    return new SyncMode[0];
  }

  @Override
  public SyncMode getSlaveSyncMode() {
    return null;
  }

  @Override
  public void setSlaveSyncMode(SyncMode syncMode) {

  }

  @Override
  public SyncMode[] getSlaveSyncModes() {
    return new SyncMode[0];
  }

  @Override
  public void setTrackMute(int i, boolean b) {

  }

  @Override
  public boolean getTrackMute(int i) {
    return false;
  }

  @Override
  public void setTrackSolo(int i, boolean b) {

  }

  @Override
  public boolean getTrackSolo(int i) {
    return false;
  }

  @Override
  public boolean addMetaEventListener(MetaEventListener metaEventListener) {
    return false;
  }

  @Override
  public void removeMetaEventListener(MetaEventListener metaEventListener) {

  }

  @Override
  public int[] addControllerEventListener(ControllerEventListener controllerEventListener, int[]
          ints) {
    return new int[0];
  }

  @Override
  public int[] removeControllerEventListener(ControllerEventListener controllerEventListener,
                                             int[] ints) {
    return new int[0];
  }

  @Override
  public long getLoopStartPoint() {
    return 0;
  }

  @Override
  public void setLoopStartPoint(long l) {

  }

  @Override
  public long getLoopEndPoint() {
    return 0;
  }

  @Override
  public void setLoopEndPoint(long l) {

  }

  @Override
  public int getLoopCount() {
    return 0;
  }

  @Override
  public void setLoopCount(int i) {

  }

  @Override
  public Info getDeviceInfo() {
    return null;
  }

  @Override
  public void open() throws MidiUnavailableException {

  }

  @Override
  public void close() {

  }

  @Override
  public boolean isOpen() {
    return false;
  }

  @Override
  public int getMaxReceivers() {
    return 0;
  }

  @Override
  public int getMaxTransmitters() {
    return 0;
  }

  @Override
  public Receiver getReceiver() throws MidiUnavailableException {
    return null;
  }

  @Override
  public List<Receiver> getReceivers() {
    return null;
  }

  @Override
  public Transmitter getTransmitter() throws MidiUnavailableException {
    return null;
  }

  @Override
  public List<Transmitter> getTransmitters() {
    return null;
  }
}
