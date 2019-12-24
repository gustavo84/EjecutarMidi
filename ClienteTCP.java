import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;
import javax.swing.JTextField;

public class ClienteTCP extends Thread {
   private static ClienteTCP cli;
   SocketManager sm;
   Socket so;
   BufferedReader br;
   DataOutputStream dos;
   ListenerServidor act;
   JTextField textoHistorial;
   UIAplicacion ui;
   String name;
   boolean conectado;

   public void conectar(String ip, String name, int host) {
      try {
         this.sm = new SocketManager(ip, host);
         this.sm.InicializaStreams();
         this.name = name;
         this.conectado = true;
      } catch (IOException var5) {
         System.err.println(var5);
      }

   }

   public static ClienteTCP getInstancia() {
      if (cli == null) {
         cli = new ClienteTCP();
      }

      return cli;
   }

   public void cerrarSocket() {
      try {
         this.sm.CerrarSocket();
      } catch (IOException var2) {
         var2.printStackTrace();
      }

   }

   public void desconectar() {
      try {
         InetAddress address = null;
         System.out.println("entraaafaswerfwerwerdc");

         try {
            address = InetAddress.getLocalHost();
         } catch (UnknownHostException var3) {
            var3.printStackTrace();
         }

         String[] a = address.toString().split("/");
         this.sm.Escribir("SALIR " + a[1] + "-" + this.name + "\r\n");
         this.act.stop();
         this.sm.CerrarSocket();
         this.conectado = false;
      } catch (IOException var4) {
         System.err.println(var4);
      }

   }

   public String EstadoServer() {
      String y = "";

      try {
         y = this.sm.Leer();
      } catch (IOException var3) {
      }

      return y;
   }

   public boolean estaConectado() {
      return this.conectado;
   }

   public Vector registrar(UIAplicacion ui) {
      this.ui = ui;
      this.act = new ListenerServidor(this.sm, ui);
      this.act.start();
      Vector vec = new Vector();
      InetAddress address = null;
      System.out.println("entraaafasdc");

      try {
         address = InetAddress.getLocalHost();
      } catch (UnknownHostException var7) {
         var7.printStackTrace();
      }

      String[] a = address.toString().split("/");

      try {
         this.sm.Escribir("REGISTRAR " + a[1] + "-" + this.name + "\r\n");
         System.out.println("pasa cliente no lee");
      } catch (IOException var6) {
         var6.printStackTrace();
      }

      System.out.println(address + "asdf" + a[1]);
      return vec;
   }
}
