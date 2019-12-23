/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import javax.sound.midi.MidiDevice;
/*     */ import javax.sound.midi.MidiMessage;
/*     */ import javax.sound.midi.MidiUnavailableException;
/*     */ import javax.sound.midi.Receiver;
/*     */ import javax.sound.midi.ShortMessage;
/*     */ import javax.sound.midi.SysexMessage;
/*     */ import javax.sound.midi.Transmitter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class WireOutput
/*     */   implements MidiDevice, Receiver, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   WireNative myOutput;
/*     */   private StringBuffer array;
/*     */   
/*     */   public WireOutput() throws Exception {
/*  35 */     this.myOutput = new WireNative(1);
/*     */     
/*  37 */     this.array = new StringBuffer(6);
/*  38 */     this.array.append("      ");
/*  39 */     this.array.setCharAt(0, '<');
/*  40 */     this.array.setCharAt(1, 'Կ');
/*  41 */     this.array.setCharAt(4, false);
/*  42 */     this.array.setCharAt(5, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     
/*  53 */     try { this.myOutput.close(); } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public MidiDevice.Info getDeviceInfo() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public boolean isOpen() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void open() throws MidiUnavailableException {
/*  95 */     if (this.myOutput.getport() == -1)
/*  96 */       return;  this.myOutput.open();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public int getMaxReceivers() { return this.myOutput.numberofdevices(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public String getDeviceName(int port) throws Exception { return this.myOutput.devicename(port); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   public void setport(int port) throws Exception { this.myOutput.setport(port); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public int getport() { return this.myOutput.getport(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendThrow(MidiMessage event, long time) throws Exception {
/* 135 */     if (event instanceof ShortMessage) {
/*     */       
/* 137 */       ShortMessage e = (ShortMessage)event;
/* 138 */       this.array.setCharAt(2, (char)(event.getStatus() + (e.getData1() << 8)));
/* 139 */       this.array.setCharAt(3, (char)e.getData2());
/* 140 */       this.array.setCharAt(4, (char)(int)(time & 0xFFL));
/* 141 */       this.array.setCharAt(5, (char)(int)((time & 0xFF00L) << 8L));
/* 142 */       this.myOutput.writeLine(this.array.toString(), 0);
/*     */     }
/* 144 */     else if (event instanceof SysexMessage) {
/*     */       
/* 146 */       SysexMessage e = (SysexMessage)event;
/* 147 */       byte[] arree = e.getData();
/* 148 */       StringBuffer array2 = new StringBuffer(arree.length + 3);
/* 149 */       for (int q = 0; q < arree.length + 3; ) { array2.append(" "); q++; }
/* 150 */        array2.setCharAt(0, '<');
/* 151 */       array2.setCharAt(1, (char)(64 + (arree.length + 2 << 8)));
/* 152 */       array2.setCharAt(2, (char)e.getStatus());
/* 153 */       for (int q = 0; q < arree.length; ) { array2.setCharAt(q + 3, (char)arree[q]); q++; }
/* 154 */        this.myOutput.writeLine(array2.toString(), 0);
/*     */     } else {
/* 156 */       throw new Exception("WireOut::send(): Sorry, only ShortEvent and SysexMessage types are supported!");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void send(MidiMessage m, long time) { 
/* 166 */     try { sendThrow(m, time); } catch (Exception e2) { System.out.println(e2.toString()); }
/*     */      }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 175 */   public long getMicrosecondPosition() { return -1L; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   public int getMaxTransmitters() { return 0; }
/* 181 */   public Receiver getReceiver() { return null; }
/* 182 */   public Transmitter getTransmitter() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 187 */   public List<Receiver> getReceivers() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 193 */   public List<Transmitter> getTransmitters() { return null; }
/*     */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/WireOutput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */