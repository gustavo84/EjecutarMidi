import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class SocketManager {
   public Socket mySocket;
   private DataOutputStream bufferEscritura;
   private BufferedReader bufferLectura;

   public SocketManager(Socket sock) throws IOException {
      this.mySocket = sock;
      this.InicializaStreams();
   }

   public SocketManager(InetAddress address, int port) throws IOException {
      this.mySocket = new Socket(address, port);
      this.InicializaStreams();
   }

   public SocketManager(String host, int port) throws IOException {
      this.mySocket = new Socket(host, port);
      this.InicializaStreams();
   }

   public void InicializaStreams() throws IOException {
      this.bufferEscritura = new DataOutputStream(this.mySocket.getOutputStream());
      this.bufferLectura = new BufferedReader(new InputStreamReader(this.mySocket.getInputStream()));
   }

   public void CerrarStreams() throws IOException {
      this.bufferEscritura.close();
      this.bufferLectura.close();
   }

   public void CerrarSocket() throws IOException {
      this.mySocket.close();
   }

   public String Leer() throws IOException {
      return this.bufferLectura.readLine();
   }

   public void Escribir(String contenido) throws IOException {
      this.bufferEscritura.writeBytes(contenido);
   }

   public void Escribir(byte[] buffer, int bytes) throws IOException {
      this.bufferEscritura.write(buffer, 0, bytes);
   }
}
