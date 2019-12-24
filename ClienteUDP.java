import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.Map.Entry;

public class ClienteUDP {
   private Vector vUsuarios;
   private String ip;
   private Vector time;
   DatagramSocket socketUDP;
   private HashMap mapUser;
   int instrumento;

   public ClienteUDP() {
      try {
         InetAddress address = InetAddress.getLocalHost();
         String[] a = address.toString().split("/");
         this.ip = a[1];
      } catch (UnknownHostException var4) {
         var4.printStackTrace();
      }

      try {
         this.socketUDP = new DatagramSocket();
      } catch (SocketException var3) {
         var3.printStackTrace();
      }

   }

   public static void main(String[] args) {
      try {
         DatagramSocket socketUDP = new DatagramSocket();
         byte[] mensaje = args[0].getBytes();
         InetAddress hostServidor = InetAddress.getByName("192.168.1.2");
         int puertoServidor = 6789;
         DatagramPacket peticion = new DatagramPacket(mensaje, args[0].length(), hostServidor, puertoServidor);
         socketUDP.send(peticion);
         socketUDP.close();
      } catch (SocketException var6) {
         System.out.println("Socket: " + var6.getMessage());
      } catch (IOException var7) {
         System.out.println("IO: " + var7.getMessage());
      }

   }

   public void desconectar() {
      this.socketUDP.close();
   }

   public void enviar(int v, int time, int velocidad, String simulacion) {
      try {
         System.out.println("es" + String.valueOf(v) + time);
         Iterator it = this.mapUser.entrySet().iterator();
         System.out.println("ipadfadfsadfsdf" + this.ip);

         while(it.hasNext()) {
            Entry e = (Entry)it.next();
            byte[] mensaje = (simulacion + ":" + v + ":" + time + ":" + velocidad + ":" + this.instrumento + ":").getBytes();
            System.out.print(mensaje.length);
            InetAddress hostServidor = InetAddress.getByName((String)e.getKey());
            System.out.println("envia al usuario" + (String)e.getKey() + hostServidor);
            int puertoServidor = 6789;
            DatagramPacket peticion = new DatagramPacket(mensaje, (simulacion + ":" + v + ":" + time + ":" + velocidad + ":" + this.instrumento + ":").length(), hostServidor, puertoServidor);
            this.socketUDP.send(peticion);
         }
      } catch (SocketException var11) {
         System.out.println("Socket: " + var11.getMessage());
      } catch (IOException var12) {
         System.out.println("IO: " + var12.getMessage());
      }

   }

   public void actualizarUsuarios(HashMap mapUser) {
      this.mapUser = mapUser;
   }

   public void cambiarInstrumento(int num) {
      this.instrumento = num;
   }
}
