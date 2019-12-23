/*     */ import java.io.IOException;
/*     */ import java.net.DatagramPacket;
/*     */ import java.net.DatagramSocket;
/*     */ import java.net.InetAddress;
/*     */ import java.net.SocketException;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClienteUDP
/*     */ {
/*     */   private Vector<String> vUsuarios;
/*     */   private String ip;
/*     */   private Vector<String> time;
/*     */   DatagramSocket socketUDP;
/*     */   private HashMap<String, String> mapUser;
/*     */   int instrumento;
/*     */   
/*     */   public ClienteUDP() {
/*     */     try {
/*  25 */       InetAddress address = InetAddress.getLocalHost();
/*  26 */       String[] a = address.toString().split("/");
/*  27 */       this.ip = a[1];
/*  28 */     } catch (UnknownHostException e1) {
/*     */       
/*  30 */       e1.printStackTrace();
/*     */     } 
/*     */     
/*     */     try {
/*  34 */       this.socketUDP = new DatagramSocket();
/*  35 */     } catch (SocketException e) {
/*     */       
/*  37 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/*  45 */       DatagramSocket socketUDP = new DatagramSocket();
/*     */ 
/*     */       
/*  48 */       byte[] mensaje = args[0].getBytes();
/*  49 */       InetAddress hostServidor = InetAddress.getByName("192.168.1.2");
/*  50 */       int puertoServidor = 6789;
/*     */ 
/*     */       
/*  53 */       DatagramPacket peticion = 
/*  54 */         new DatagramPacket(mensaje, args[0].length(), hostServidor, 
/*  55 */           puertoServidor);
/*     */ 
/*     */       
/*  58 */       socketUDP.send(peticion);
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
/*  70 */       socketUDP.close();
/*     */     }
/*  72 */     catch (SocketException e) {
/*  73 */       System.out.println("Socket: " + e.getMessage());
/*  74 */     } catch (IOException e) {
/*  75 */       System.out.println("IO: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  81 */   public void desconectar() { this.socketUDP.close(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void enviar(int v, int time, int velocidad, String simulacion) {
/*     */     try {
/*  91 */       System.out.println("es" + String.valueOf(v) + String.valueOf(time));
/*  92 */       Iterator<Map.Entry> it = this.mapUser.entrySet().iterator();
/*  93 */       System.out.println("ipadfadfsadfsdf" + this.ip);
/*  94 */       while (it.hasNext()) {
/*  95 */         Map.Entry e = it.next();
/*     */         
/*  97 */         byte[] mensaje = (String.valueOf(simulacion) + ":" + String.valueOf(v) + ":" + String.valueOf(time) + ":" + String.valueOf(velocidad) + ":" + String.valueOf(this.instrumento) + ":").getBytes();
/*     */ 
/*     */         
/* 100 */         System.out.print(mensaje.length);
/* 101 */         InetAddress hostServidor = InetAddress.getByName((String)e.getKey());
/* 102 */         System.out.println("envia al usuario" + (String)e.getKey() + hostServidor);
/* 103 */         int puertoServidor = 6789;
/* 104 */         DatagramPacket peticion = 
/* 105 */           new DatagramPacket(mensaje, (String.valueOf(simulacion) + ":" + String.valueOf(v) + ":" + String.valueOf(time) + ":" + String.valueOf(velocidad) + ":" + String.valueOf(this.instrumento) + ":").length(), hostServidor, 
/* 106 */             puertoServidor);
/*     */ 
/*     */         
/* 109 */         this.socketUDP.send(peticion);
/*     */       } 
/* 111 */     } catch (SocketException e) {
/* 112 */       System.out.println("Socket: " + e.getMessage());
/* 113 */     } catch (IOException e) {
/* 114 */       System.out.println("IO: " + e.getMessage());
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
/* 129 */   public void actualizarUsuarios(HashMap<String, String> mapUser) { this.mapUser = mapUser; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   public void cambiarInstrumento(int num) { this.instrumento = num; }
/*     */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/ClienteUDP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */