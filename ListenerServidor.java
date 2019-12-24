import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ListenerServidor extends Thread {
   SocketManager sm;
   UIAplicacion ui;
   HashMap hm = new HashMap();
   HashMap mapTime = new HashMap();
   WireOutput w;
   int valor;

   public ListenerServidor(SocketManager sm, UIAplicacion ui) {
      this.sm = sm;
      this.ui = ui;
      ui.pasarInstancia(this);
      System.out.println("a");
   }

   public void desconectar() {
      try {
         this.sm.CerrarSocket();
      } catch (IOException var2) {
         var2.printStackTrace();
      }

   }

   public void run() {
      while(true) {
         String comando = null;

         try {
            comando = this.sm.Leer();
         } catch (IOException var6) {
            var6.printStackTrace();
         }

         if (comando != null) {
            System.out.println("entrooooooooooooooooooooooooooooooooo");
            System.out.println("comando" + comando);
            StringTokenizer sTok = new StringTokenizer(comando, " ");
            comando = sTok.nextToken();
            String[] cadenas;
            String ip;
            if (comando.compareTo("PROPALTA") == 0) {
               this.hm.clear();

               while(sTok.hasMoreElements()) {
                  ip = sTok.nextToken();
                  cadenas = ip.split("-");
                  System.out.println("asdf" + cadenas[0]);
                  this.hm.put(cadenas[0], cadenas[1]);
               }

               this.ui.actualizar(this.hm, this.mapTime);
            }

            if (comando.compareTo("DESCONECTAR") == 0) {
               System.out.println("deberia pasar por aqi2222222");
               this.ui.desconectarCliente();
            }

            if (comando.compareTo("PROPBAJA") == 0) {
               System.out.println("deberia pasar por aqi");
               ip = sTok.nextToken();
               cadenas = ip.split("-");
               System.out.println("asdf" + cadenas[0]);
               this.hm.remove(cadenas[0]);
               this.mapTime.remove(this.hm.get(cadenas[0]));
               this.ui.actualizar(this.hm, this.mapTime);
            }
         }
      }
   }
}
