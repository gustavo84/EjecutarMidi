/*    */ import javax.sound.midi.InvalidMidiDataException;
/*    */ import javax.sound.midi.MidiEvent;
/*    */ import javax.sound.midi.MidiSystem;
/*    */ import javax.sound.midi.MidiUnavailableException;
/*    */ import javax.sound.midi.Sequence;
/*    */ import javax.sound.midi.Sequencer;
/*    */ import javax.sound.midi.ShortMessage;
/*    */ import javax.sound.midi.Track;
/*    */ 
/*    */ public class SequencerSound {
/*    */   private Track track;
/*    */   private Sequencer sequencer;
/*    */   private Sequence sequence;
/*    */   
/*    */   public SequencerSound() {
/*    */     try {
/* 17 */       this.sequencer = MidiSystem.getSequencer();
/* 18 */       this.sequencer.open();
/* 19 */     } catch (MidiUnavailableException e) {
/* 20 */       e.printStackTrace();
/*    */     } 
/* 22 */     createTrack();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startSequencer() {
/*    */     try {
/* 29 */       this.sequencer.setSequence(this.sequence);
/* 30 */     } catch (InvalidMidiDataException e) {
/* 31 */       e.printStackTrace();
/*    */     } 
/* 33 */     this.sequencer.start();
/* 34 */     this.sequencer.setTempoInBPM(60.0F);
/*    */   }
/*    */   
/*    */   private void createTrack() {
/*    */     try {
/* 39 */       this.sequence = new Sequence(0.0F, 4);
/* 40 */     } catch (InvalidMidiDataException e) {
/* 41 */       e.printStackTrace();
/*    */     } 
/* 43 */     this.track = this.sequence.createTrack();
/*    */   }
/*    */   
/*    */   public void startNote(int note, int tick) {
/* 47 */     System.out.println("la nota" + note + "t" + tick);
/* 48 */     setShortMessage(
/*    */         
/* 50 */         144, note, tick);
/*    */   }
/*    */   
/*    */   public void stopNote(int note, int tick) {
/* 54 */     System.out.println("la nota para" + note + "t" + tick);
/* 55 */     setShortMessage(
/*    */         
/* 57 */         128, note, tick);
/*    */   }
/*    */ 
/*    */   
/*    */   private void setShortMessage(int onOrOff, int note, int tick) {
/* 62 */     ShortMessage message = new ShortMessage();
/*    */     try {
/* 64 */       message.setMessage(onOrOff, 0, note, 90);
/* 65 */       MidiEvent event = new MidiEvent(
/* 66 */           message, tick);
/* 67 */       this.track.add(event);
/*    */     }
/* 69 */     catch (InvalidMidiDataException e) {
/* 70 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void makeScale(int baseNote) {
/* 75 */     for (int i = 0; i < 13; i++) {
/* 76 */       startNote(baseNote + i, i);
/* 77 */       stopNote(baseNote + i, i + 1);
/* 78 */       startNote(baseNote + i, 25 - i);
/* 79 */       stopNote(baseNote + i, 26 - i);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {}
/*    */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/SequencerSound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */