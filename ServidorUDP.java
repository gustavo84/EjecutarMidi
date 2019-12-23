/*     */ import java.io.IOException;
/*     */ import java.net.DatagramPacket;
/*     */ import java.net.DatagramSocket;
/*     */ import java.net.SocketException;
/*     */ import java.util.HashMap;
/*     */ import javax.sound.midi.InvalidMidiDataException;
/*     */ import javax.sound.midi.Receiver;
/*     */ import javax.sound.midi.ShortMessage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServidorUDP
/*     */   extends Thread
/*     */ {
/*     */   private static Receiver rep;
/*     */   static WireOutput output;
/*     */   private HashMap<String, WireOutput> mapRep;
/*     */   
/*  42 */   public ServidorUDP(WireOutput output) { ServidorUDP.output = output; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*  47 */     System.out.println("entra");
/*     */     
/*     */     try {
/*  50 */       DatagramSocket socketUDP = new DatagramSocket(6789);
/*  51 */       byte[] bufer = new byte[45];
/*  52 */       String usuario = "am";
/*  53 */       int instrumento = 0;
/*  54 */       String[] se = (String[])null;
/*     */       
/*     */       while (true) {
/*  57 */         DatagramPacket peticion = 
/*  58 */           new DatagramPacket(bufer, bufer.length);
/*     */ 
/*     */         
/*  61 */         socketUDP.receive(peticion);
/*  62 */         System.out.println("uy la longggggiiiiiiiitud es" + peticion.getLength());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  70 */         String s = new String(peticion.getData());
/*  71 */         System.out.println("asdf" + s + "as" + s.compareTo("Test"));
/*  72 */         if (!s.contains("Test")) {
/*  73 */           se = s.split(":");
/*  74 */           System.out.println("seeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" + s);
/*  75 */           System.out.println("ver q " + se[1]);
/*  76 */           System.out.println(String.valueOf(Integer.parseInt(se[1])) + ":" + Integer.parseInt(se[2]) + Integer.parseInt(se[3]));
/*     */           
/*  78 */           System.out.println("var0" + se[0] + "var1" + se[1] + "var2:" + se[2] + "var3:" + se[3] + "var4:" + se[4]);
/*  79 */           ShortMessage message = new ShortMessage();
/*  80 */           if (se[0].compareTo("SI") == 0) {
/*     */ 
/*     */             
/*  83 */             if (instrumento != Integer.parseInt(se[4])) {
/*     */               try {
/*  85 */                 message.setMessage(192, Integer.parseInt(se[4]), 64);
/*  86 */               } catch (NumberFormatException e) {
/*     */                 
/*  88 */                 e.printStackTrace();
/*  89 */               } catch (InvalidMidiDataException e) {
/*     */                 
/*  91 */                 e.printStackTrace();
/*     */               } 
/*  93 */               output.send(message, Integer.parseInt(se[4]));
/*     */             } 
/*     */             
/*     */             try {
/*  97 */               message.setMessage(Integer.parseInt(se[1]), Integer.parseInt(se[2]), Integer.parseInt(se[3]));
/*  98 */               output.send(message, Integer.parseInt(se[2]));
/*     */             }
/* 100 */             catch (NumberFormatException e) {
/*     */               
/* 102 */               e.printStackTrace();
/* 103 */             } catch (InvalidMidiDataException e) {
/*     */               
/* 105 */               e.printStackTrace();
/*     */             } 
/*     */           } else {
/*     */             try {
/* 109 */               if (instrumento != Integer.parseInt(se[4])) {
/*     */                 try {
/* 111 */                   message.setMessage(192, Integer.parseInt(se[4]), 64);
/* 112 */                 } catch (NumberFormatException e) {
/*     */                   
/* 114 */                   e.printStackTrace();
/* 115 */                 } catch (InvalidMidiDataException e) {
/*     */                   
/* 117 */                   e.printStackTrace();
/*     */                 } 
/* 119 */                 output.send(message, Integer.parseInt(se[4]));
/*     */               } 
/* 121 */               message.setMessage(144, 0, Integer.parseInt(se[1]), Integer.parseInt(se[3]));
/* 122 */               output.send(message, Integer.parseInt(se[2]));
/*     */ 
/*     */ 
/*     */             
/*     */             }
/* 127 */             catch (NumberFormatException e) {
/*     */               
/* 129 */               e.printStackTrace();
/* 130 */             } catch (InvalidMidiDataException e) {
/*     */               
/* 132 */               e.printStackTrace();
/*     */             } 
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 143 */           System.out.println("Respuesta: " + peticion.getData().toString());
/*     */           
/* 145 */           System.out.print("Datagrama recibido del host: " + 
/* 146 */               peticion.getAddress());
/* 147 */           System.out.println(" desde el puerto remoto: " + 
/* 148 */               peticion.getPort());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 163 */           usuario = se[4];
/* 164 */           instrumento = Integer.parseInt(se[4]); continue;
/*     */         } 
/* 166 */         DatagramPacket respuesta = 
/* 167 */           new DatagramPacket(peticion.getData(), peticion.getLength(), 
/* 168 */             peticion.getAddress(), peticion.getPort());
/*     */ 
/*     */         
/* 171 */         socketUDP.send(respuesta);
/*     */       }
/*     */     
/*     */     }
/* 175 */     catch (SocketException e) {
/* 176 */       System.out.println("Socket: " + e.getMessage());
/* 177 */     } catch (IOException e) {
/* 178 */       System.out.println("IO: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 201 */     System.out.println("entra");
/*     */     
/*     */     try {
/* 204 */       DatagramSocket socketUDP = new DatagramSocket(6789);
/* 205 */       byte[] bufer = new byte[1000];
/*     */ 
/*     */       
/*     */       while (true) {
/* 209 */         DatagramPacket peticion = 
/* 210 */           new DatagramPacket(bufer, bufer.length);
/*     */ 
/*     */         
/* 213 */         socketUDP.receive(peticion);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 222 */         String s = new String(peticion.getData());
/* 223 */         System.out.println("asdf" + s);
/* 224 */         String[] se = s.split(":");
/*     */         
/* 226 */         System.out.println(String.valueOf(Integer.parseInt(se[0])) + ":" + Integer.parseInt(se[1]) + ":" + Integer.parseInt(se[2]));
/*     */         
/* 228 */         ShortMessage message = new ShortMessage();
/*     */         try {
/* 230 */           message.setMessage(144, 0, Integer.parseInt(se[0]), Integer.parseInt(se[2]));
/* 231 */         } catch (NumberFormatException e) {
/*     */           
/* 233 */           e.printStackTrace();
/* 234 */         } catch (InvalidMidiDataException e) {
/*     */           
/* 236 */           e.printStackTrace();
/*     */         } 
/* 238 */         output.send(message, (Integer.parseInt(se[1]) / 100000000));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 245 */         System.out.println("Respuesta: " + peticion.getData().toString());
/*     */         
/* 247 */         System.out.print("Datagrama recibido del host: " + 
/* 248 */             peticion.getAddress());
/* 249 */         System.out.println(" desde el puerto remoto: " + 
/* 250 */             peticion.getPort());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 267 */     catch (SocketException e) {
/* 268 */       System.out.println("Socket: " + e.getMessage());
/* 269 */     } catch (IOException e) {
/* 270 */       System.out.println("IO: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/ServidorUDP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */