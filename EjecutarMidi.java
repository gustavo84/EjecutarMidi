/*    */ import java.util.Vector;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EjecutarMidi
/*    */ {
/*    */   public static void main(String[] args) {
/* 39 */     UIPedirIp ip = new UIPedirIp();
/* 40 */     ip.show(); do {  }
/* 41 */     while (!ip.esValido());
/* 42 */     ClienteTCP z = ClienteTCP.getInstancia();
/* 43 */     z.conectar(ip.getHost(), ip.getName(), 9999);
/* 44 */     String f = z.EstadoServer();
/*    */ 
/*    */     
/* 47 */     UIAplicacion ui = new UIAplicacion(z);
/*    */     
/* 49 */     Vector<String> registrar2 = z.registrar(ui);
/*    */     
/* 51 */     ui.show();
/* 52 */     ip.dispose();
/*    */   }
/*    */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/EjecutarMidi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */