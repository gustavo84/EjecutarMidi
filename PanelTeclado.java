/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionAdapter;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.util.Vector;
/*     */ import javax.sound.midi.Instrument;
/*     */ import javax.sound.midi.MidiChannel;
/*     */ import javax.sound.midi.MidiEvent;
/*     */ import javax.sound.midi.MidiSystem;
/*     */ import javax.sound.midi.Receiver;
/*     */ import javax.sound.midi.Sequence;
/*     */ import javax.sound.midi.Sequencer;
/*     */ import javax.sound.midi.ShortMessage;
/*     */ import javax.sound.midi.Soundbank;
/*     */ import javax.sound.midi.Synthesizer;
/*     */ import javax.sound.midi.Track;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSlider;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.border.BevelBorder;
/*     */ import javax.swing.border.CompoundBorder;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.border.TitledBorder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PanelTeclado
/*     */   extends JPanel
/*     */   implements ControlContext
/*     */ {
/*     */   class Piano
/*     */     extends JPanel
/*     */     implements MouseListener, KeyListener
/*     */   {
/* 237 */     Vector blackKeys = new Vector();
/*     */ 
/*     */     
/*     */     PanelTeclado.Key prevKey;
/*     */ 
/*     */     
/* 243 */     final int kw = 16;
/* 244 */     final int kh = 80;
/*     */ 
/*     */     
/*     */     public Piano() {
/* 248 */       setLayout(new BorderLayout());
/* 249 */       setPreferredSize(new Dimension(672, 81));
/* 250 */       int transpose = 24;
/* 251 */       char[] vec = { 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'ñ', '´', 'ç' };
/* 252 */       char[] vec2 = { 'w', 'e', 't', 'y', 'u', 'o', 'p', '+' };
/* 253 */       int[] whiteIDs = { 0, 2, 4, 5, 7, 9, 11 };
/*     */       
/* 255 */       for (int i = 0, x = 0; i < 6; i++) {
/* 256 */         for (int j = 0; j < 7; j++, x += 16) {
/* 257 */           int keyNum = i * 12 + whiteIDs[j] + transpose;
/* 258 */           PanelTeclado.Key k = new PanelTeclado.Key(x, 0, 16, 80, keyNum);
/* 259 */           PanelTeclado.this.whiteKeys.add(k);
/* 260 */           if (i == 3)
/* 261 */             k.teclaCaracter(vec[j]); 
/*     */         } 
/*     */       } 
/* 264 */       for (int i = 0, x = 0; i < 6; i++, x += 16) {
/* 265 */         int keyNum = i * 12 + transpose;
/* 266 */         x += 16; PanelTeclado.Key k = new PanelTeclado.Key(x - 4, 0, 8, 40, keyNum + 1);
/* 267 */         if (i == 3)
/* 268 */           k.teclaCaracter(vec2[0]); 
/* 269 */         this.blackKeys.add(k);
/* 270 */         x += 16; k = new PanelTeclado.Key(x - 4, 0, 8, 40, keyNum + 3);
/* 271 */         if (i == 3)
/* 272 */           k.teclaCaracter(vec2[1]); 
/* 273 */         this.blackKeys.add(k);
/* 274 */         x += 16;
/* 275 */         x += 16; k = new PanelTeclado.Key(x - 4, 0, 8, 40, keyNum + 6);
/* 276 */         if (i == 3)
/* 277 */           k.teclaCaracter(vec2[2]); 
/* 278 */         this.blackKeys.add(k);
/* 279 */         x += 16; k = new PanelTeclado.Key(x - 4, 0, 8, 40, keyNum + 8);
/* 280 */         if (i == 3)
/* 281 */           k.teclaCaracter(vec2[3]); 
/* 282 */         this.blackKeys.add(k);
/* 283 */         x += 16; k = new PanelTeclado.Key(x - 4, 0, 8, 40, keyNum + 10);
/* 284 */         if (i == 3)
/* 285 */           k.teclaCaracter(vec2[4]); 
/* 286 */         this.blackKeys.add(k);
/*     */       } 
/*     */       
/* 289 */       PanelTeclado.this.keys.addAll(this.blackKeys);
/* 290 */       PanelTeclado.this.keys.addAll(PanelTeclado.this.whiteKeys);
/* 291 */       addKeyListener(this);
/* 292 */       requestFocusInWindow();
/* 293 */       setFocusable(true);
/*     */ 
/*     */       
/* 296 */       addMouseMotionListener(new MouseMotionAdapter() {
/*     */             public void mouseMoved(MouseEvent e) {
/* 298 */               if ((PanelTeclado.Piano.access$0(PanelTeclado.Piano.this)).mouseOverCB.isSelected()) {
/* 299 */                 PanelTeclado.Key key = PanelTeclado.Piano.this.getKey(e.getPoint());
/* 300 */                 if (PanelTeclado.Piano.this.prevKey != null && PanelTeclado.Piano.this.prevKey != key) {
/* 301 */                   PanelTeclado.Piano.this.prevKey.off();
/*     */                 }
/* 303 */                 if (key != null && PanelTeclado.Piano.this.prevKey != key) {
/* 304 */                   key.on();
/*     */                 }
/* 306 */                 PanelTeclado.Piano.this.prevKey = key;
/* 307 */                 PanelTeclado.Piano.this.repaint();
/*     */               } 
/*     */             }
/*     */           });
/* 311 */       addMouseListener(this);
/*     */     }
/*     */     
/*     */     public void mousePressed(MouseEvent e) {
/* 315 */       this.prevKey = getKey(e.getPoint());
/* 316 */       if (this.prevKey != null) {
/* 317 */         this.prevKey.on();
/* 318 */         repaint();
/*     */       } 
/*     */     }
/*     */     public void mouseReleased(MouseEvent e) {
/* 322 */       if (this.prevKey != null) {
/* 323 */         this.prevKey.off();
/* 324 */         repaint();
/*     */       } 
/*     */     }
/*     */     public void mouseExited(MouseEvent e) {
/* 328 */       if (this.prevKey != null) {
/* 329 */         this.prevKey.off();
/* 330 */         repaint();
/* 331 */         this.prevKey = null;
/*     */       } 
/*     */     }
/*     */     public void mouseClicked(MouseEvent e) {}
/*     */     
/*     */     public void mouseEntered(MouseEvent e) {}
/*     */     
/*     */     public PanelTeclado.Key getKey(Point point) {
/* 339 */       for (int i = 0; i < PanelTeclado.this.keys.size(); i++) {
/* 340 */         if (((PanelTeclado.Key)PanelTeclado.this.keys.get(i)).contains(point)) {
/* 341 */           return PanelTeclado.this.keys.get(i);
/*     */         }
/*     */       } 
/* 344 */       return null;
/*     */     }
/*     */     
/*     */     public PanelTeclado.Key getKeyTeclado(char point) {
/* 348 */       for (int i = 0; i < PanelTeclado.this.keys.size(); i++) {
/* 349 */         if (((PanelTeclado.Key)PanelTeclado.this.keys.get(i)).contains(point)) {
/* 350 */           return PanelTeclado.this.keys.get(i);
/*     */         }
/*     */       } 
/* 353 */       return null;
/*     */     }
/*     */     public void paint(Graphics g) {
/* 356 */       Graphics2D g2 = (Graphics2D)g;
/* 357 */       Dimension d = getSize();
/*     */       
/* 359 */       g2.setBackground(getBackground());
/* 360 */       g2.clearRect(0, 0, d.width, d.height);
/*     */       
/* 362 */       g2.setColor(Color.white);
/* 363 */       g2.fillRect(0, 0, 672, 80);
/*     */       
/* 365 */       for (int i = 0; i < PanelTeclado.this.whiteKeys.size(); i++) {
/* 366 */         PanelTeclado.Key key = PanelTeclado.this.whiteKeys.get(i);
/* 367 */         if (key.isNoteOn()) {
/* 368 */           g2.setColor(PanelTeclado.this.record ? PanelTeclado.this.pink : PanelTeclado.this.jfcBlue);
/* 369 */           g2.fill(key);
/*     */         } 
/* 371 */         g2.setColor(Color.black);
/* 372 */         g2.draw(key);
/*     */       } 
/* 374 */       for (int i = 0; i < this.blackKeys.size(); i++) {
/* 375 */         PanelTeclado.Key key = this.blackKeys.get(i);
/* 376 */         if (key.isNoteOn()) {
/* 377 */           g2.setColor(PanelTeclado.this.record ? PanelTeclado.this.pink : PanelTeclado.this.jfcBlue);
/* 378 */           g2.fill(key);
/* 379 */           g2.setColor(Color.black);
/* 380 */           g2.draw(key);
/*     */         } else {
/* 382 */           g2.setColor(Color.black);
/* 383 */           g2.fill(key);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void keyPressed(KeyEvent arg0) {
/* 390 */       System.out.println("aerewrqer" + arg0.getKeyChar());
/* 391 */       this.prevKey = getKeyTeclado(arg0.getKeyChar());
/* 392 */       if (this.prevKey != null) {
/* 393 */         this.prevKey.on();
/* 394 */         repaint();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void keyReleased(KeyEvent arg0) {
/* 401 */       System.out.println("aerewrqer" + arg0.getKeyChar());
/* 402 */       this.prevKey = getKeyTeclado(arg0.getKeyChar());
/* 403 */       if (this.prevKey != null) {
/* 404 */         this.prevKey.off();
/* 405 */         repaint();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void keyTyped(KeyEvent arg0) {
/* 412 */       System.out.println("aerewrqer" + arg0.getKeyChar());
/* 413 */       this.prevKey = getKeyTeclado(arg0.getKeyChar());
/* 414 */       if (this.prevKey != null) {
/* 415 */         this.prevKey.on();
/* 416 */         repaint();
/*     */       } 
/*     */     }
/*     */   }
/*     */   final int PROGRAM = 192;
/*     */   final int NOTEON = 144;
/*     */   final int NOTEOFF = 128;
/*     */   final int SUSTAIN = 64; final int REVERB = 91; final int ON = 0; final int OFF = 1; final Color jfcBlue = new Color(204, 204, 255); final Color pink = new Color(255, 175, 175); Sequencer sequencer; Sequence sequence; Synthesizer synthesizer; Instrument[] instruments; ChannelData[] channels; ChannelData cc; JCheckBox mouseOverCB = new JCheckBox("mouseOver", true); JSlider veloS; JSlider presS; JSlider bendS; JSlider revbS; JCheckBox soloCB; JCheckBox monoCB; JCheckBox muteCB; JCheckBox sustCB; Vector keys = new Vector(); Vector whiteKeys = new Vector(); JTable table; Piano piano; boolean record; Track track; long startTime; private Receiver also; private ClienteUDP cli; private boolean enviar; public PanelTeclado(ClienteUDP cli) { this.cli = cli; setLayout(new BorderLayout()); this.record = true; JPanel p = new JPanel(); p.setLayout(new BoxLayout(p, 1)); EmptyBorder eb = new EmptyBorder(5, 5, 5, 5); BevelBorder bb = new BevelBorder(1); CompoundBorder cb = new CompoundBorder(eb, bb); p.setBorder(new CompoundBorder(cb, eb)); JPanel pp = new JPanel(new BorderLayout()); pp.setBorder(new EmptyBorder(10, 20, 10, 5)); pp.add(this.piano = new Piano()); p.add(pp); add(p); } public void open() { try { if (this.synthesizer == null && (this.synthesizer = MidiSystem.getSynthesizer()) == null) { System.out.println("getSynthesizer() failed!"); return; }
/*     */        this.synthesizer.open(); this.sequencer = MidiSystem.getSequencer(); this.sequence = new Sequence(0.0F, 10); }
/*     */     catch (Exception ex)
/*     */     { ex.printStackTrace(); return; }
/*     */      Soundbank sb = this.synthesizer.getDefaultSoundbank(); if (sb != null) {
/*     */       this.instruments = this.synthesizer.getDefaultSoundbank().getInstruments(); this.synthesizer.loadInstrument(this.instruments[0]);
/*     */     }  MidiChannel[] midiChannels = this.synthesizer.getChannels(); this.channels = new ChannelData[midiChannels.length]; for (int i = 0; i < this.channels.length; i++)
/*     */       this.channels[i] = new ChannelData(midiChannels[i], i);  this.cc = this.channels[0]; } public void close() { if (this.synthesizer != null)
/*     */       this.synthesizer.close();  if (this.sequencer != null)
/*     */       this.sequencer.close();  this.sequencer = null; this.synthesizer = null; this.instruments = null; this.channels = null; } public void createShortEvent(int type, int num) { ShortMessage message = new ShortMessage(); System.out.println("envia"); StringBuffer output = new StringBuffer(); try {
/*     */       long millis = System.currentTimeMillis() - this.startTime; long tick = millis * this.sequence.getResolution() / 500L; System.out.println("tipo" + type + " num" + num + "ve" + this.cc.velocity); message.setMessage(type, num, this.cc.velocity); MidiEvent event = new MidiEvent(message, tick); output.append("   Note On Key: "); System.out.println("envia" + type + this.cc.num + "nvia" + num + "cc.velocity" + this.cc.velocity + "cc.num" + this.cc.num); if (this.enviar)
/*     */         this.cli.enviar(type, num, this.cc.velocity, "SI"); 
/*     */     } catch (Exception ex) {
/*     */       ex.printStackTrace();
/*     */     }  } class Key extends Rectangle {
/*     */     int noteState = 1; int kNum; char c; public Key(int x, int y, int width, int height, int num) { super(x, y, width, height); this.kNum = num; } public void teclaCaracter(char c) { this.c = c; } public boolean isNoteOn() { return (this.noteState == 0); } public void on() { setNoteState(0); if (PanelTeclado.this.record)
/*     */         PanelTeclado.this.createShortEvent(144, this.kNum);  } public void off() { setNoteState(1); if (PanelTeclado.this.record)
/*     */         PanelTeclado.this.createShortEvent(128, this.kNum);  } public void setNoteState(int state) { this.noteState = state; } public boolean contains(char point) { if (this.c == point)
/*     */         return true;  return false; }
/*     */   } class ChannelData {
/* 443 */     MidiChannel channel; boolean solo; boolean mono; boolean mute; boolean sustain; int velocity; public ChannelData(MidiChannel channel, int num) { this.channel = channel;
/* 444 */       this.num = num;
/* 445 */       this.velocity = this.pressure = this.bend = this.reverb = 64; }
/*     */     
/*     */     int pressure; int bend; int reverb; int row; int col; int num;
/*     */     public void setComponentStates() {
/* 449 */       PanelTeclado.this.table.setRowSelectionInterval(this.row, this.row);
/* 450 */       PanelTeclado.this.table.setColumnSelectionInterval(this.col, this.col);
/*     */       
/* 452 */       PanelTeclado.this.soloCB.setSelected(this.solo);
/* 453 */       PanelTeclado.this.monoCB.setSelected(this.mono);
/* 454 */       PanelTeclado.this.muteCB.setSelected(this.mute);
/*     */ 
/*     */       
/* 457 */       JSlider[] slider = { PanelTeclado.this.veloS, PanelTeclado.this.presS, PanelTeclado.this.bendS, PanelTeclado.this.revbS };
/* 458 */       int[] v = { this.velocity, this.pressure, this.bend, this.reverb };
/* 459 */       for (int i = 0; i < slider.length; i++) {
/* 460 */         TitledBorder tb = (TitledBorder)slider[i].getBorder();
/* 461 */         String s = tb.getTitle();
/* 462 */         tb.setTitle(String.valueOf(s.substring(0, s.indexOf('=') + 1)) + String.valueOf(v[i]));
/* 463 */         slider[i].repaint();
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 583 */     PanelTeclado midiSynth = new PanelTeclado(null);
/* 584 */     System.out.println("entra");
/* 585 */     midiSynth.open();
/* 586 */     JFrame f = new JFrame("Midi Synthesizer");
/* 587 */     f.addWindowListener(new WindowAdapter() {
/* 588 */           public void windowClosing(WindowEvent e) { System.exit(0); }
/*     */         });
/* 590 */     f.getContentPane().add("Center", midiSynth);
/* 591 */     f.pack();
/* 592 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 593 */     int w = 760;
/* 594 */     int h = 470;
/* 595 */     f.setLocation(screenSize.width / 2 - w / 2, screenSize.height / 2 - h / 2);
/* 596 */     f.setSize(w, h);
/* 597 */     f.setVisible(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 605 */   public void setEnviar(boolean enviar) { this.enviar = enviar; }
/*     */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/PanelTeclado.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */