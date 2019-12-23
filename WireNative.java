/*    */ import java.io.Serializable;
/*    */ import javax.sound.midi.MidiUnavailableException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class WireNative
/*    */   implements Serializable
/*    */ {
/*    */   private int port;
/*    */   private int Jthis;
/*    */   
/*    */   static  {
/* 24 */     System.loadLibrary("WireNative");
/*    */   }
/*    */   
/*    */   public WireNative(int devicenumber) throws Exception {
/* 28 */     this.port = -1;
/* 29 */     this.Jthis = newJavaCPPBridge(devicenumber);
/* 30 */     if (this.Jthis == -1) throw new Exception("WireNative::WireNative(): Could not create new WireNative object."); 
/*    */   }
/*    */   private native int newJavaCPPBridge(int paramInt);
/*    */   
/*    */   public synchronized void finalize() {
/*    */     
/* 36 */     try { Jclose(this.Jthis); } catch (Exception exception) {} 
/* 37 */     try { Jfinalize(); } catch (Exception exception) {}
/*    */   }
/*    */   
/*    */   private native void Jfinalize();
/*    */   
/* 42 */   public synchronized String devicename(int port) throws Exception { return Jdevicename(this.Jthis, port); }
/*    */   
/*    */   private native String Jdevicename(int paramInt1, int paramInt2);
/*    */   
/* 46 */   public synchronized void close() throws Exception { Jclose(this.Jthis); }
/*    */ 
/*    */   
/*    */   private native void Jclose(int paramInt);
/*    */   
/* 51 */   public synchronized void open() throws MidiUnavailableException { if (this.port != -1) { Jopen(this.Jthis); } else { throw new MidiUnavailableException("open(): first set the port!"); }
/*    */      }
/*    */   
/*    */   private native void Jopen(int paramInt);
/*    */   
/*    */   public synchronized void setport(int port) throws Exception {
/* 57 */     if (port != -1) { Jsetport(this.Jthis, port); }
/* 58 */     else { close(); }
/* 59 */      this.port = port;
/*    */   }
/*    */ 
/*    */   
/*    */   private native void Jsetport(int paramInt1, int paramInt2);
/*    */   
/* 65 */   public synchronized int numberofdevices() { return Jnumberofdevices(this.Jthis); }
/*    */   
/*    */   private native int Jnumberofdevices(int paramInt);
/*    */   
/* 69 */   public synchronized int getport() { return this.port; }
/*    */ 
/*    */   
/* 72 */   public synchronized int readLine(char[] inhere) throws Exception { return JreadLine(inhere, this.Jthis); }
/*    */   
/*    */   private native int JreadLine(char[] paramArrayOfchar, int paramInt);
/*    */   
/* 76 */   public synchronized void writeLine(String ainString, int who) throws Exception { JwriteLine(this.Jthis, ainString, who); }
/*    */   
/*    */   private native void JwriteLine(int paramInt1, String paramString, int paramInt2);
/*    */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/WireNative.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */