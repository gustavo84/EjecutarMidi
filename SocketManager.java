/*    */ import java.io.BufferedReader;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.InetAddress;
/*    */ import java.net.Socket;
/*    */ 
/*    */ public class SocketManager {
/*    */   public Socket mySocket;
/*    */   
/*    */   public SocketManager(Socket sock) throws IOException {
/* 12 */     this.mySocket = sock;
/* 13 */     InicializaStreams();
/*    */   }
/*    */   private DataOutputStream bufferEscritura; private BufferedReader bufferLectura;
/*    */   
/*    */   public SocketManager(InetAddress address, int port) throws IOException {
/* 18 */     this.mySocket = new Socket(address, port);
/* 19 */     InicializaStreams();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SocketManager(String host, int port) throws IOException {
/* 29 */     this.mySocket = new Socket(host, port);
/* 30 */     InicializaStreams();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void InicializaStreams() throws IOException {
/* 38 */     this.bufferEscritura = new DataOutputStream(this.mySocket.getOutputStream());
/* 39 */     this.bufferLectura = new BufferedReader(new InputStreamReader(
/* 40 */           this.mySocket.getInputStream()));
/*    */   }
/*    */   
/*    */   public void CerrarStreams() throws IOException {
/* 44 */     this.bufferEscritura.close();
/* 45 */     this.bufferLectura.close();
/*    */   }
/*    */ 
/*    */   
/* 49 */   public void CerrarSocket() throws IOException { this.mySocket.close(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 58 */   public String Leer() throws IOException { return this.bufferLectura.readLine(); }
/*    */ 
/*    */ 
/*    */   
/* 62 */   public void Escribir(String contenido) throws IOException { this.bufferEscritura.writeBytes(contenido); }
/*    */ 
/*    */ 
/*    */   
/* 66 */   public void Escribir(byte[] buffer, int bytes) throws IOException { this.bufferEscritura.write(buffer, 0, bytes); }
/*    */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/SocketManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */