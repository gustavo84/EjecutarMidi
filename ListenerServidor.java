/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.StringTokenizer;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ListenerServidor
/*     */   extends Thread
/*     */ {
/*     */   SocketManager sm;
/*     */   UIAplicacion ui;
/*  40 */   HashMap<String, String> hm = new HashMap<String, String>();
/*     */   
/*  42 */   HashMap<String, Long> mapTime = new HashMap<String, Long>();
/*     */   
/*     */   WireOutput w;
/*     */   
/*     */   int valor;
/*     */ 
/*     */   
/*     */   public ListenerServidor(SocketManager sm, UIAplicacion ui) {
/*  50 */     this.sm = sm;
/*  51 */     this.ui = ui;
/*  52 */     ui.pasarInstancia(this);
/*  53 */     System.out.println("a");
/*     */   }
/*     */   
/*     */   public void desconectar() {
/*     */     try {
/*  58 */       this.sm.CerrarSocket();
/*  59 */     } catch (IOException e) {
/*  60 */       e.printStackTrace();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     while (true) {
/*  88 */       String comando = null;
/*     */       try {
/*  90 */         comando = this.sm.Leer();
/*  91 */       } catch (IOException e) {
/*     */         
/*  93 */         e.printStackTrace();
/*     */       } 
/*  95 */       if (comando != null) {
/*  96 */         System.out.println("entrooooooooooooooooooooooooooooooooo");
/*     */ 
/*     */         
/*  99 */         System.out.println("comando" + comando);
/*     */         
/* 101 */         String y1 = comando;
/* 102 */         StringTokenizer sTok = new StringTokenizer(y1, " ");
/* 103 */         comando = sTok.nextToken();
/* 104 */         if (comando.compareTo("PROPALTA") == 0) {
/* 105 */           this.hm.clear();
/* 106 */           while (sTok.hasMoreElements()) {
/* 107 */             String ip = sTok.nextToken();
/* 108 */             String[] cadenas = ip.split("-");
/* 109 */             System.out.println("asdf" + cadenas[0]);
/* 110 */             this.hm.put(cadenas[0], cadenas[1]);
/*     */           } 
/* 112 */           this.ui.actualizar(this.hm, this.mapTime);
/*     */         } 
/* 114 */         if (comando.compareTo("DESCONECTAR") == 0) {
/* 115 */           System.out.println("deberia pasar por aqi2222222");
/* 116 */           this.ui.desconectarCliente();
/*     */         } 
/* 118 */         if (comando.compareTo("PROPBAJA") == 0) {
/*     */           
/* 120 */           System.out.println("deberia pasar por aqi");
/*     */           
/* 122 */           String ip = sTok.nextToken();
/* 123 */           String[] cadenas = ip.split("-");
/* 124 */           System.out.println("asdf" + cadenas[0]);
/* 125 */           this.hm.remove(cadenas[0]);
/*     */           
/* 127 */           this.mapTime.remove(this.hm.get(cadenas[0]));
/* 128 */           this.ui.actualizar(this.hm, this.mapTime);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/ListenerServidor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */