/*     */ import java.io.BufferedReader;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.InetAddress;
/*     */ import java.net.Socket;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JTextField;
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
/*     */ public class ClienteTCP
/*     */   extends Thread
/*     */ {
/*     */   private static ClienteTCP cli;
/*     */   SocketManager sm;
/*     */   Socket so;
/*     */   BufferedReader br;
/*     */   DataOutputStream dos;
/*     */   ListenerServidor act;
/*     */   JTextField textoHistorial;
/*     */   UIAplicacion ui;
/*     */   String name;
/*     */   boolean conectado;
/*     */   
/*     */   public void conectar(String ip, String name, int host) {
/*     */     try {
/*  51 */       this.sm = new SocketManager(ip, host);
/*  52 */       this.sm.InicializaStreams();
/*  53 */       this.name = name;
/*  54 */       this.conectado = true;
/*     */     
/*     */     }
/*  57 */     catch (IOException ioe) {
/*  58 */       System.err.println(ioe);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ClienteTCP getInstancia() {
/*  66 */     if (cli == null) {
/*  67 */       cli = new ClienteTCP();
/*     */     }
/*  69 */     return cli;
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
/*     */   public void cerrarSocket() {
/*     */     try {
/*  87 */       this.sm.CerrarSocket();
/*  88 */     } catch (IOException e) {
/*     */       
/*  90 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void desconectar() {
/*     */     try {
/*  96 */       InetAddress address = null;
/*     */       
/*  98 */       System.out.println("entraaafaswerfwerwerdc");
/*     */       try {
/* 100 */         address = InetAddress.getLocalHost();
/* 101 */       } catch (UnknownHostException e) {
/*     */         
/* 103 */         e.printStackTrace();
/*     */       } 
/* 105 */       String[] a = address.toString().split("/");
/*     */       
/* 107 */       this.sm.Escribir("SALIR " + a[1] + "-" + this.name + "\r\n");
/* 108 */       this.act.stop();
/* 109 */       this.sm.CerrarSocket();
/*     */       
/* 111 */       this.conectado = false;
/* 112 */     } catch (IOException ioe) {
/* 113 */       System.err.println(ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String EstadoServer() {
/* 118 */     String y = "";
/*     */     try {
/* 120 */       y = this.sm.Leer();
/*     */     }
/* 122 */     catch (IOException iOException) {}
/* 123 */     return y;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 128 */   public boolean estaConectado() { return this.conectado; }
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
/*     */   public Vector<String> registrar(UIAplicacion ui) {
/* 143 */     this.ui = ui;
/* 144 */     this.act = new ListenerServidor(this.sm, ui);
/* 145 */     this.act.start();
/* 146 */     Vector<String> vec = new Vector<String>();
/*     */     
/* 148 */     InetAddress address = null;
/*     */     
/* 150 */     System.out.println("entraaafasdc");
/*     */     try {
/* 152 */       address = InetAddress.getLocalHost();
/* 153 */     } catch (UnknownHostException e) {
/*     */       
/* 155 */       e.printStackTrace();
/*     */     } 
/* 157 */     String[] a = address.toString().split("/");
/*     */     try {
/* 159 */       this.sm.Escribir("REGISTRAR " + a[1] + "-" + this.name + "\r\n");
/* 160 */       System.out.println("pasa cliente no lee");
/*     */     }
/* 162 */     catch (IOException e) {
/*     */       
/* 164 */       e.printStackTrace();
/*     */     } 
/* 166 */     System.out.println(address + "asdf" + a[1]);
/* 167 */     return vec;
/*     */   }
/*     */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/ClienteTCP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */