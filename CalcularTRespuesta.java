/*     */ import java.io.IOException;
/*     */ import java.net.DatagramPacket;
/*     */ import java.net.DatagramSocket;
/*     */ import java.net.InetAddress;
/*     */ import java.net.SocketException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
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
/*     */ public class CalcularTRespuesta
/*     */   extends Thread
/*     */ {
/*     */   UIAplicacion cliente22;
/*     */   HashMap<String, String> mapUser;
/*     */   HashMap<String, Long> mapTime;
/*     */   DatagramSocket socketUDP;
/*     */   
/*     */   public CalcularTRespuesta(UIAplicacion cliente22, HashMap<String, String> mapUser, HashMap<String, Long> mapTime2) {
/*  32 */     this.mapTime = mapTime2;
/*  33 */     this.cliente22 = cliente22;
/*  34 */     this.mapUser = mapUser;
/*     */     try {
/*  36 */       this.socketUDP = new DatagramSocket();
/*  37 */     } catch (SocketException e) {
/*  38 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     try {
/*     */       while (true) {
/*  46 */         System.out.println("mando mensaje desde calculartrespeusta0");
/*  47 */         test();
/*  48 */         System.out.println("mando mensaje desde calculartrespeusta1");
/*  49 */         this.cliente22.actualizar2(this.mapTime);
/*  50 */         System.out.println("mando mensaje desde calculartrespeusta");
/*  51 */         Thread.sleep(60000L);
/*     */       }
/*     */     
/*     */     }
/*  55 */     catch (InterruptedException e) {
/*  56 */       e.printStackTrace();
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void desconectar() {
/*  62 */     stop();
/*  63 */     this.socketUDP.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void test() {
/*  70 */     long inicio = System.currentTimeMillis();
/*  71 */     System.out.println("deberia pasar en cliente1");
/*  72 */     Iterator<Map.Entry> it = this.mapUser.entrySet().iterator();
/*  73 */     this.mapTime.clear();
/*     */     
/*     */     try {
/*  76 */       String usuario = null;
/*  77 */       while (it.hasNext()) {
/*  78 */         Map.Entry e = it.next();
/*  79 */         System.out.println("essafadf" + e.getKey() + " " + e.getValue());
/*  80 */         usuario = (String)e.getKey();
/*     */         
/*  82 */         byte[] mensaje = "Test".getBytes();
/*  83 */         InetAddress hostServidor = InetAddress.getByName(usuario);
/*  84 */         System.out.println("envia al usuario" + usuario);
/*  85 */         int puertoServidor = 6789;
/*  86 */         DatagramPacket peticion = 
/*  87 */           new DatagramPacket(mensaje, String.valueOf("Test").length(), hostServidor, 
/*  88 */             puertoServidor);
/*     */ 
/*     */         
/*  91 */         this.socketUDP.send(peticion);
/*  92 */         byte[] bufer = new byte[1000];
/*  93 */         DatagramPacket respuesta = 
/*  94 */           new DatagramPacket(bufer, bufer.length);
/*  95 */         System.out.println("todo bene");
/*  96 */         this.socketUDP.receive(respuesta);
/*  97 */         System.out.println("todo bene2");
/*  98 */         long fin = System.currentTimeMillis() - inicio;
/*  99 */         System.out.println("tiempo es:" + fin);
/* 100 */         this.mapTime.put((String)e.getValue(), Long.valueOf(fin / 2L));
/*     */       }
/*     */     
/* 103 */     } catch (SocketException e) {
/* 104 */       System.out.println("Socket: " + e.getMessage());
/* 105 */     } catch (IOException e) {
/* 106 */       System.out.println("IO: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/CalcularTRespuesta.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */