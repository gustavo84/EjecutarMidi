/*     */ import java.awt.Dimension;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFrame;
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
/*     */ 
/*     */ class null
/*     */   implements ItemListener
/*     */ {
/*     */   public void itemStateChanged(ItemEvent evt) {
/* 262 */     if (evt.getStateChange() != 1)
/*     */       return; 
/* 264 */     int i = ((JComboBox)evt.getSource()).getSelectedIndex();
/*     */ 
/*     */     
/* 267 */     if (i == 1) {
/*     */       
/* 269 */       UIAplicacion.access$3(UIAplicacion.this, new PanelTeclado(UIAplicacion.access$2(UIAplicacion.this)));
/* 270 */       UIAplicacion.access$4(UIAplicacion.this).setEnviar(true);
/* 271 */       UIAplicacion.access$4(UIAplicacion.this).open();
/*     */ 
/*     */       
/* 274 */       UIAplicacion.access$4(UIAplicacion.this).show();
/* 275 */       UIAplicacion.this.f = new JFrame("Sintetizador midi");
/* 276 */       UIAplicacion.this.f.addWindowListener(new WindowAdapter() {
/* 277 */             public void windowClosing(WindowEvent e) { System.exit(0); }
/*     */           });
/* 279 */       UIAplicacion.this.f.getContentPane().add("Center", UIAplicacion.access$4(UIAplicacion.this));
/* 280 */       UIAplicacion.this.f.pack();
/* 281 */       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 282 */       int w = 760;
/* 283 */       int h = 170;
/* 284 */       UIAplicacion.this.f.setLocation(UIAplicacion.this.getX(), UIAplicacion.this.getY() + (UIAplicacion.this.getSize()).height);
/* 285 */       UIAplicacion.this.f.setDefaultCloseOperation(0);
/* 286 */       UIAplicacion.this.f.setSize(w, h);
/* 287 */       UIAplicacion.this.f.setVisible(true);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/UIAplicacion$3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */