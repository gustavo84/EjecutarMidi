import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class CalcularTRespuesta extends Thread {
   UIAplicacion cliente22;
   HashMap mapUser;
   HashMap mapTime;
   DatagramSocket socketUDP;

   public CalcularTRespuesta(UIAplicacion cliente22, HashMap mapUser, HashMap mapTime2) {
      this.mapTime = mapTime2;
      this.cliente22 = cliente22;
      this.mapUser = mapUser;

      try {
         this.socketUDP = new DatagramSocket();
      } catch (SocketException var5) {
         var5.printStackTrace();
      }

   }

   public void run() {
      try {
         while(true) {
            System.out.println("mando mensaje desde calculartrespeusta0");
            this.test();
            System.out.println("mando mensaje desde calculartrespeusta1");
            this.cliente22.actualizar2(this.mapTime);
            System.out.println("mando mensaje desde calculartrespeusta");
            Thread.sleep(60000L);
         }
      } catch (InterruptedException var2) {
         var2.printStackTrace();
      }
   }

   public void desconectar() {
      this.stop();
      this.socketUDP.close();
   }

   public void test() {
      long inicio = System.currentTimeMillis();
      System.out.println("deberia pasar en cliente1");
      Iterator it = this.mapUser.entrySet().iterator();
      this.mapTime.clear();

      try {
         String usuario = null;

         while(it.hasNext()) {
            Entry e = (Entry)it.next();
            System.out.println("essafadf" + e.getKey() + " " + e.getValue());
            usuario = (String)e.getKey();
            byte[] mensaje = "Test".getBytes();
            InetAddress hostServidor = InetAddress.getByName(usuario);
            System.out.println("envia al usuario" + usuario);
            int puertoServidor = 6789;
            DatagramPacket peticion = new DatagramPacket(mensaje, String.valueOf("Test").length(), hostServidor, puertoServidor);
            this.socketUDP.send(peticion);
            byte[] bufer = new byte[1000];
            DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
            System.out.println("todo bene");
            this.socketUDP.receive(respuesta);
            System.out.println("todo bene2");
            long fin = System.currentTimeMillis() - inicio;
            System.out.println("tiempo es:" + fin);
            this.mapTime.put((String)e.getValue(), fin / 2L);
         }
      } catch (SocketException var14) {
         System.out.println("Socket: " + var14.getMessage());
      } catch (IOException var15) {
         System.out.println("IO: " + var15.getMessage());
      }

   }
}
