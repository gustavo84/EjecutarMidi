import java.io.Serializable;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public final class WireNative implements Serializable {
   private int port = -1;
//   private int Jthis;
private MidiDevice device;

   static {
    //  System.loadLibrary("WireNative");
   }
	Receiver rcvr = null;
   public WireNative(int devicenumber) throws Exception {

     // this.Jthis = this.newJavaCPPBridge(devicenumber);
   //   if (this.Jthis == -1) {
     //    throw new Exception("WireNative::WireNative(): Could not create new WireNative object.");
      //}
   }

   private native int newJavaCPPBridge(int var1);

   public synchronized void finalize() {


   }

   private native void Jfinalize();

   public synchronized String devicename(int port) throws Exception {
	   

		try {
			rcvr = MidiSystem.getReceiver();
		} catch (MidiUnavailableException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} /* or device.getReceiver() */

	   device=MidiSystem.getMidiDevice(MidiSystem.getMidiDeviceInfo()[port]);
      return    MidiSystem.getMidiDevice(MidiSystem.getMidiDeviceInfo()[port]).getDeviceInfo().getName();
   }

   private native String Jdevicename(int var1, int var2);

   public synchronized void close() throws Exception {
	   if (device.isOpen()) {
		    device.close();
	   }
   }

   private native void Jclose(int var1);

   public synchronized void open() throws MidiUnavailableException {
	   if (!(device.isOpen())) {
		    try {
		      device.open();
		  } catch (MidiUnavailableException e) {
		       System.out.println(e);
		  }
	   }
   }

   private native void Jopen(int var1);

   public synchronized void setport(int port) throws Exception {


      this.port = port;
      

   }

   private native void Jsetport(int var1, int var2);

   public synchronized int numberofdevices() {
      return MidiSystem.getMidiDeviceInfo().length;
   }

   private native int Jnumberofdevices(int var1);

   public synchronized int getport() {
      return this.port;
   }

   public synchronized int readLine(char[] inhere) throws Exception {
	return port;
 //     return this.JreadLine(inhere, this.Jthis);
   }

   private native int JreadLine(char[] var1, int var2);

   public synchronized void writeLine(String ainString, int who) throws Exception {
	 //  MidiSystem.write(arg0, arg1, arg2);
   }

   private native void JwriteLine(int var1, String var2, int var3);

	public void writeLine(MidiMessage event, long time) {


		rcvr.send(event, time);

}
}
