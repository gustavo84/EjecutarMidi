import java.io.Serializable;
import java.util.List;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.SysexMessage;
import javax.sound.midi.Transmitter;
import javax.sound.midi.MidiDevice.Info;

public final class WireOutput implements MidiDevice, Receiver, Serializable {
	
	
   private static final long serialVersionUID = 1L;
   WireNative myOutput = new WireNative(1);
   private StringBuffer array = new StringBuffer(6);

   public WireOutput() throws Exception {
      this.array.append("      ");
      this.array.setCharAt(0, '<');
      this.array.setCharAt(1, 'Կ');
      this.array.setCharAt(4, '\u0000');
      this.array.setCharAt(5, '\u0000');
   }

   public void close() {
      try {
         this.myOutput.close();
      } catch (Exception var2) {
      }

   }

   public Info getDeviceInfo() {
      return null;
   }

   public boolean isOpen() {
      return true;
   }

   public void open() throws MidiUnavailableException {
      if (this.myOutput.getport() != -1) {
         this.myOutput.open();
      }
   }

   public int getMaxReceivers() {
      return this.myOutput.numberofdevices();
   }

   public String getDeviceName(int port) throws Exception {
      return this.myOutput.devicename(port);
   }

   public void setport(int port) throws Exception {
      this.myOutput.setport(port);
   }

   public int getport() {
      return this.myOutput.getport();
   }

   public void sendThrow(MidiMessage event, long time) throws Exception {
      if (event instanceof ShortMessage) {
         ShortMessage e = (ShortMessage)event;
         this.array.setCharAt(2, (char)(event.getStatus() + (e.getData1() << 8)));
         this.array.setCharAt(3, (char)e.getData2());
         this.array.setCharAt(4, (char)((int)(time & 255L)));
         this.array.setCharAt(5, (char)((int)((time & 65280L) << 8)));
         this.myOutput.writeLine(this.array.toString(), 0);
         this.myOutput.writeLine(event,time);
      } else {
         if (!(event instanceof SysexMessage)) {
            throw new Exception("WireOut::send(): Sorry, only ShortEvent and SysexMessage types are supported!");
         }

         SysexMessage e = (SysexMessage)event;
         byte[] arree = e.getData();
         StringBuffer array2 = new StringBuffer(arree.length + 3);

         int q;
         for(q = 0; q < arree.length + 3; ++q) {
            array2.append(" ");
         }

         array2.setCharAt(0, '<');
         array2.setCharAt(1, (char)(64 + (arree.length + 2 << 8)));
         array2.setCharAt(2, (char)e.getStatus());

         for(q = 0; q < arree.length; ++q) {
            array2.setCharAt(q + 3, (char)arree[q]);
         }

         this.myOutput.writeLine(array2.toString(), 0);
      }

   }

   public void send(MidiMessage m, long time) {
      try {
         this.sendThrow(m, time);
      } catch (Exception var5) {
         System.out.println(var5.toString());
      }

   }

   public long getMicrosecondPosition() {
      return -1L;
   }

   public int getMaxTransmitters() {
      return 0;
   }

   public Receiver getReceiver() {
      return null;
   }

   public Transmitter getTransmitter() {
      return null;
   }

   public List getReceivers() {
      return null;
   }

   public List getTransmitters() {
      return null;
   }
}
