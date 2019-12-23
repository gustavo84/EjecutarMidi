/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionAdapter;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Piano
/*     */   extends JPanel
/*     */   implements MouseListener, KeyListener
/*     */ {
/* 237 */   Vector blackKeys = new Vector();
/*     */ 
/*     */   
/*     */   PanelTeclado.Key prevKey;
/*     */ 
/*     */   
/* 243 */   final int kw = 16;
/* 244 */   final int kh = 80;
/*     */ 
/*     */   
/*     */   public Piano() {
/* 248 */     setLayout(new BorderLayout());
/* 249 */     setPreferredSize(new Dimension(672, 81));
/* 250 */     int transpose = 24;
/* 251 */     char[] vec = { 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'ñ', '´', 'ç' };
/* 252 */     char[] vec2 = { 'w', 'e', 't', 'y', 'u', 'o', 'p', '+' };
/* 253 */     int[] whiteIDs = { 0, 2, 4, 5, 7, 9, 11 };
/*     */     
/* 255 */     for (int i = 0, x = 0; i < 6; i++) {
/* 256 */       for (int j = 0; j < 7; j++, x += 16) {
/* 257 */         int keyNum = i * 12 + whiteIDs[j] + transpose;
/* 258 */         PanelTeclado.Key k = new PanelTeclado.Key(paramPanelTeclado, x, 0, 16, 80, keyNum);
/* 259 */         paramPanelTeclado.whiteKeys.add(k);
/* 260 */         if (i == 3)
/* 261 */           k.teclaCaracter(vec[j]); 
/*     */       } 
/*     */     } 
/* 264 */     for (int i = 0, x = 0; i < 6; i++, x += 16) {
/* 265 */       int keyNum = i * 12 + transpose;
/* 266 */       x += 16; PanelTeclado.Key k = new PanelTeclado.Key(paramPanelTeclado, x - 4, 0, 8, 40, keyNum + 1);
/* 267 */       if (i == 3)
/* 268 */         k.teclaCaracter(vec2[0]); 
/* 269 */       this.blackKeys.add(k);
/* 270 */       x += 16; k = new PanelTeclado.Key(paramPanelTeclado, x - 4, 0, 8, 40, keyNum + 3);
/* 271 */       if (i == 3)
/* 272 */         k.teclaCaracter(vec2[1]); 
/* 273 */       this.blackKeys.add(k);
/* 274 */       x += 16;
/* 275 */       x += 16; k = new PanelTeclado.Key(paramPanelTeclado, x - 4, 0, 8, 40, keyNum + 6);
/* 276 */       if (i == 3)
/* 277 */         k.teclaCaracter(vec2[2]); 
/* 278 */       this.blackKeys.add(k);
/* 279 */       x += 16; k = new PanelTeclado.Key(paramPanelTeclado, x - 4, 0, 8, 40, keyNum + 8);
/* 280 */       if (i == 3)
/* 281 */         k.teclaCaracter(vec2[3]); 
/* 282 */       this.blackKeys.add(k);
/* 283 */       x += 16; k = new PanelTeclado.Key(paramPanelTeclado, x - 4, 0, 8, 40, keyNum + 10);
/* 284 */       if (i == 3)
/* 285 */         k.teclaCaracter(vec2[4]); 
/* 286 */       this.blackKeys.add(k);
/*     */     } 
/*     */     
/* 289 */     paramPanelTeclado.keys.addAll(this.blackKeys);
/* 290 */     paramPanelTeclado.keys.addAll(paramPanelTeclado.whiteKeys);
/* 291 */     addKeyListener(this);
/* 292 */     requestFocusInWindow();
/* 293 */     setFocusable(true);
/*     */ 
/*     */     
/* 296 */     addMouseMotionListener(new MouseMotionAdapter() {
/*     */           public void mouseMoved(MouseEvent e) {
/* 298 */             if ((PanelTeclado.Piano.access$0(PanelTeclado.Piano.this)).mouseOverCB.isSelected()) {
/* 299 */               PanelTeclado.Key key = PanelTeclado.Piano.this.getKey(e.getPoint());
/* 300 */               if (PanelTeclado.Piano.this.prevKey != null && PanelTeclado.Piano.this.prevKey != key) {
/* 301 */                 PanelTeclado.Piano.this.prevKey.off();
/*     */               }
/* 303 */               if (key != null && PanelTeclado.Piano.this.prevKey != key) {
/* 304 */                 key.on();
/*     */               }
/* 306 */               PanelTeclado.Piano.this.prevKey = key;
/* 307 */               PanelTeclado.Piano.this.repaint();
/*     */             } 
/*     */           }
/*     */         });
/* 311 */     addMouseListener(this);
/*     */   }
/*     */   
/*     */   public void mousePressed(MouseEvent e) {
/* 315 */     this.prevKey = getKey(e.getPoint());
/* 316 */     if (this.prevKey != null) {
/* 317 */       this.prevKey.on();
/* 318 */       repaint();
/*     */     } 
/*     */   }
/*     */   public void mouseReleased(MouseEvent e) {
/* 322 */     if (this.prevKey != null) {
/* 323 */       this.prevKey.off();
/* 324 */       repaint();
/*     */     } 
/*     */   }
/*     */   public void mouseExited(MouseEvent e) {
/* 328 */     if (this.prevKey != null) {
/* 329 */       this.prevKey.off();
/* 330 */       repaint();
/* 331 */       this.prevKey = null;
/*     */     } 
/*     */   }
/*     */   public void mouseClicked(MouseEvent e) {}
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */   
/*     */   public PanelTeclado.Key getKey(Point point) {
/* 339 */     for (int i = 0; i < PanelTeclado.this.keys.size(); i++) {
/* 340 */       if (((PanelTeclado.Key)PanelTeclado.this.keys.get(i)).contains(point)) {
/* 341 */         return PanelTeclado.this.keys.get(i);
/*     */       }
/*     */     } 
/* 344 */     return null;
/*     */   }
/*     */   
/*     */   public PanelTeclado.Key getKeyTeclado(char point) {
/* 348 */     for (int i = 0; i < PanelTeclado.this.keys.size(); i++) {
/* 349 */       if (((PanelTeclado.Key)PanelTeclado.this.keys.get(i)).contains(point)) {
/* 350 */         return PanelTeclado.this.keys.get(i);
/*     */       }
/*     */     } 
/* 353 */     return null;
/*     */   }
/*     */   public void paint(Graphics g) {
/* 356 */     Graphics2D g2 = (Graphics2D)g;
/* 357 */     Dimension d = getSize();
/*     */     
/* 359 */     g2.setBackground(getBackground());
/* 360 */     g2.clearRect(0, 0, d.width, d.height);
/*     */     
/* 362 */     g2.setColor(Color.white);
/* 363 */     g2.fillRect(0, 0, 672, 80);
/*     */     
/* 365 */     for (int i = 0; i < PanelTeclado.this.whiteKeys.size(); i++) {
/* 366 */       PanelTeclado.Key key = PanelTeclado.this.whiteKeys.get(i);
/* 367 */       if (key.isNoteOn()) {
/* 368 */         g2.setColor(PanelTeclado.this.record ? PanelTeclado.this.pink : PanelTeclado.this.jfcBlue);
/* 369 */         g2.fill(key);
/*     */       } 
/* 371 */       g2.setColor(Color.black);
/* 372 */       g2.draw(key);
/*     */     } 
/* 374 */     for (int i = 0; i < this.blackKeys.size(); i++) {
/* 375 */       PanelTeclado.Key key = this.blackKeys.get(i);
/* 376 */       if (key.isNoteOn()) {
/* 377 */         g2.setColor(PanelTeclado.this.record ? PanelTeclado.this.pink : PanelTeclado.this.jfcBlue);
/* 378 */         g2.fill(key);
/* 379 */         g2.setColor(Color.black);
/* 380 */         g2.draw(key);
/*     */       } else {
/* 382 */         g2.setColor(Color.black);
/* 383 */         g2.fill(key);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void keyPressed(KeyEvent arg0) {
/* 390 */     System.out.println("aerewrqer" + arg0.getKeyChar());
/* 391 */     this.prevKey = getKeyTeclado(arg0.getKeyChar());
/* 392 */     if (this.prevKey != null) {
/* 393 */       this.prevKey.on();
/* 394 */       repaint();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyReleased(KeyEvent arg0) {
/* 401 */     System.out.println("aerewrqer" + arg0.getKeyChar());
/* 402 */     this.prevKey = getKeyTeclado(arg0.getKeyChar());
/* 403 */     if (this.prevKey != null) {
/* 404 */       this.prevKey.off();
/* 405 */       repaint();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyTyped(KeyEvent arg0) {
/* 412 */     System.out.println("aerewrqer" + arg0.getKeyChar());
/* 413 */     this.prevKey = getKeyTeclado(arg0.getKeyChar());
/* 414 */     if (this.prevKey != null) {
/* 415 */       this.prevKey.on();
/* 416 */       repaint();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/PanelTeclado$Piano.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */