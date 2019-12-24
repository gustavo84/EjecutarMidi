import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class ServidorUDP extends Thread {
   private static Receiver rep;
   static WireOutput output;
   private HashMap mapRep;

   public ServidorUDP(WireOutput output) {
      ServidorUDP.output = output;
   }

   public void run() {
      System.out.println("entra");

      try {
         DatagramSocket socketUDP = new DatagramSocket(6789);
         byte[] bufer = new byte[45];
         String usuario = "am";
         int instrumento = 0;
         String[] se = (String[])null;

         while(true) {
            while(true) {
               DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);
               socketUDP.receive(peticion);
               System.out.println("uy la longggggiiiiiiiitud es" + peticion.getLength());
               String s = new String(peticion.getData());
               System.out.println("asdf" + s + "as" + s.compareTo("Test"));
               if (!s.contains("Test")) {
                  se = s.split(":");
                  System.out.println("seeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" + s);
                  System.out.println("ver q " + se[1]);
                  System.out.println(Integer.parseInt(se[1]) + ":" + Integer.parseInt(se[2]) + Integer.parseInt(se[3]));
                  System.out.println("var0" + se[0] + "var1" + se[1] + "var2:" + se[2] + "var3:" + se[3] + "var4:" + se[4]);
                  ShortMessage message = new ShortMessage();
                  if (se[0].compareTo("SI") == 0) {
                     if (instrumento != Integer.parseInt(se[4])) {
                        try {
                           message.setMessage(192, Integer.parseInt(se[4]), 64);
                        } catch (NumberFormatException var10) {
                           var10.printStackTrace();
                        } catch (InvalidMidiDataException var11) {
                           var11.printStackTrace();
                        }

                        output.send(message, (long)Integer.parseInt(se[4]));
                     }

                     try {
                        message.setMessage(Integer.parseInt(se[1]), Integer.parseInt(se[2]), Integer.parseInt(se[3]));
                        output.send(message, (long)Integer.parseInt(se[2]));
                     } catch (NumberFormatException var16) {
                        var16.printStackTrace();
                     } catch (InvalidMidiDataException var17) {
                        var17.printStackTrace();
                     }
                  } else {
                     try {
                        if (instrumento != Integer.parseInt(se[4])) {
                           try {
                              message.setMessage(192, Integer.parseInt(se[4]), 64);
                           } catch (NumberFormatException var12) {
                              var12.printStackTrace();
                           } catch (InvalidMidiDataException var13) {
                              var13.printStackTrace();
                           }

                           output.send(message, (long)Integer.parseInt(se[4]));
                        }

                        message.setMessage(144, 0, Integer.parseInt(se[1]), Integer.parseInt(se[3]));
                        output.send(message, (long)Integer.parseInt(se[2]));
                     } catch (NumberFormatException var14) {
                        var14.printStackTrace();
                     } catch (InvalidMidiDataException var15) {
                        var15.printStackTrace();
                     }
                  }

                  System.out.println("Respuesta: " + peticion.getData().toString());
                  System.out.print("Datagrama recibido del host: " + peticion.getAddress());
                  System.out.println(" desde el puerto remoto: " + peticion.getPort());
                  usuario = se[4];
                  instrumento = Integer.parseInt(se[4]);
               } else {
                  DatagramPacket respuesta = new DatagramPacket(peticion.getData(), peticion.getLength(), peticion.getAddress(), peticion.getPort());
                  socketUDP.send(respuesta);
               }
            }
         }
      } catch (SocketException var18) {
         System.out.println("Socket: " + var18.getMessage());
      } catch (IOException var19) {
         System.out.println("IO: " + var19.getMessage());
      }

   }

   public static void main(String[] args) {
      System.out.println("entra");

      try {
         DatagramSocket socketUDP = new DatagramSocket(6789);
         byte[] bufer = new byte[1000];

         while(true) {
            DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);
            socketUDP.receive(peticion);
            String s = new String(peticion.getData());
            System.out.println("asdf" + s);
            String[] se = s.split(":");
            System.out.println(Integer.parseInt(se[0]) + ":" + Integer.parseInt(se[1]) + ":" + Integer.parseInt(se[2]));
            ShortMessage message = new ShortMessage();

            try {
               message.setMessage(144, 0, Integer.parseInt(se[0]), Integer.parseInt(se[2]));
            } catch (NumberFormatException var8) {
               var8.printStackTrace();
            } catch (InvalidMidiDataException var9) {
               var9.printStackTrace();
            }

            output.send(message, (long)(Integer.parseInt(se[1]) / 100000000));
            System.out.println("Respuesta: " + peticion.getData().toString());
            System.out.print("Datagrama recibido del host: " + peticion.getAddress());
            System.out.println(" desde el puerto remoto: " + peticion.getPort());
         }
      } catch (SocketException var10) {
         System.out.println("Socket: " + var10.getMessage());
      } catch (IOException var11) {
         System.out.println("IO: " + var11.getMessage());
      }

   }
}
