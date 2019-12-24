import java.io.Serializable;
import javax.sound.midi.MidiUnavailableException;

public final class WireNative implements Serializable {
   private int port = -1;
   private int Jthis;

   static {
      System.loadLibrary("WireNative");
   }

   public WireNative(int devicenumber) throws Exception {
      this.Jthis = this.newJavaCPPBridge(devicenumber);
      if (this.Jthis == -1) {
         throw new Exception("WireNative::WireNative(): Could not create new WireNative object.");
      }
   }

   private native int newJavaCPPBridge(int var1);

   public synchronized void finalize() {
      try {
         this.Jclose(this.Jthis);
      } catch (Exception var3) {
      }

      try {
         this.Jfinalize();
      } catch (Exception var2) {
      }

   }

   private native void Jfinalize();

   public synchronized String devicename(int port) throws Exception {
      return this.Jdevicename(this.Jthis, port);
   }

   private native String Jdevicename(int var1, int var2);

   public synchronized void close() throws Exception {
      this.Jclose(this.Jthis);
   }

   private native void Jclose(int var1);

   public synchronized void open() throws MidiUnavailableException {
      if (this.port != -1) {
         this.Jopen(this.Jthis);
      } else {
         throw new MidiUnavailableException("open(): first set the port!");
      }
   }

   private native void Jopen(int var1);

   public synchronized void setport(int port) throws Exception {
      if (port != -1) {
         this.Jsetport(this.Jthis, port);
      } else {
         this.close();
      }

      this.port = port;
   }

   private native void Jsetport(int var1, int var2);

   public synchronized int numberofdevices() {
      return this.Jnumberofdevices(this.Jthis);
   }

   private native int Jnumberofdevices(int var1);

   public synchronized int getport() {
      return this.port;
   }

   public synchronized int readLine(char[] inhere) throws Exception {
      return this.JreadLine(inhere, this.Jthis);
   }

   private native int JreadLine(char[] var1, int var2);

   public synchronized void writeLine(String ainString, int who) throws Exception {
      this.JwriteLine(this.Jthis, ainString, who);
   }

   private native void JwriteLine(int var1, String var2, int var3);
}
