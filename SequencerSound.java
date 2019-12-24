import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class SequencerSound {
   private Track track;
   private Sequencer sequencer;
   private Sequence sequence;

   public SequencerSound() {
      try {
         this.sequencer = MidiSystem.getSequencer();
         this.sequencer.open();
      } catch (MidiUnavailableException var2) {
         var2.printStackTrace();
      }

      this.createTrack();
   }

   public void startSequencer() {
      try {
         this.sequencer.setSequence(this.sequence);
      } catch (InvalidMidiDataException var2) {
         var2.printStackTrace();
      }

      this.sequencer.start();
      this.sequencer.setTempoInBPM(60.0F);
   }

   private void createTrack() {
      try {
         this.sequence = new Sequence(0.0F, 4);
      } catch (InvalidMidiDataException var2) {
         var2.printStackTrace();
      }

      this.track = this.sequence.createTrack();
   }

   public void startNote(int note, int tick) {
      System.out.println("la nota" + note + "t" + tick);
      this.setShortMessage(144, note, tick);
   }

   public void stopNote(int note, int tick) {
      System.out.println("la nota para" + note + "t" + tick);
      this.setShortMessage(128, note, tick);
   }

   private void setShortMessage(int onOrOff, int note, int tick) {
      ShortMessage message = new ShortMessage();

      try {
         message.setMessage(onOrOff, 0, note, 90);
         MidiEvent event = new MidiEvent(message, (long)tick);
         this.track.add(event);
      } catch (InvalidMidiDataException var6) {
         var6.printStackTrace();
      }

   }

   public void makeScale(int baseNote) {
      for(int i = 0; i < 13; ++i) {
         this.startNote(baseNote + i, i);
         this.stopNote(baseNote + i, i + 1);
         this.startNote(baseNote + i, 25 - i);
         this.stopNote(baseNote + i, 26 - i);
      }

   }

   public static void main(String[] args) {
      new SequencerSound();
   }
}
