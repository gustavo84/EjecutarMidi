/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import javax.sound.midi.Instrument;
/*     */ import javax.sound.midi.MidiChannel;
/*     */ import javax.sound.midi.MidiSystem;
/*     */ import javax.sound.midi.Receiver;
/*     */ import javax.sound.midi.Sequence;
/*     */ import javax.sound.midi.Sequencer;
/*     */ import javax.sound.midi.ShortMessage;
/*     */ import javax.sound.midi.Soundbank;
/*     */ import javax.sound.midi.Synthesizer;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSlider;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.ListSelectionModel;
/*     */ import javax.swing.border.TitledBorder;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PanelInstrumentos
/*     */   extends JPanel
/*     */ {
/*  45 */   final int PROGRAM = 192;
/*     */   
/*     */   Sequencer sequencer;
/*     */   
/*     */   Sequence sequence;
/*     */   
/*     */   String simular;
/*     */   
/*     */   Synthesizer synthesizer;
/*     */   
/*     */   Instrument[] instruments;
/*     */   
/*     */   ChannelData[] channels;
/*     */   
/*     */   ChannelData cc;
/*     */   
/*     */   JTable table;
/*     */   JSlider veloS;
/*     */   JSlider presS;
/*     */   JSlider bendS;
/*     */   JSlider revbS;
/*  66 */   private String[] names = new String[] { 
/*  67 */       "Piano", "Chromatic Perc.", "Organ", "Guitar", 
/*  68 */       "Bass", "Strings", "Ensemble", "Brass", 
/*  69 */       "Reed", "Pipe", "Synth Lead", "Synth Pad", 
/*  70 */       "Synth Effects", "Ethnic", "Percussive", "Sound Effects" };
/*  71 */   private int nRows = 8;
/*  72 */   private int nCols = this.names.length;
/*     */ 
/*     */ 
/*     */   
/*     */   private Receiver also;
/*     */ 
/*     */ 
/*     */   
/*     */   private ClienteUDP cli;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   public void setSimular(String simular) { this.simular = simular; }
/*     */ 
/*     */   
/*     */   public void open() {
/*     */     
/*  90 */     try { if (this.synthesizer == null && (
/*  91 */         this.synthesizer = MidiSystem.getSynthesizer()) == null) {
/*  92 */         System.out.println("getSynthesizer() failed!");
/*     */         
/*     */         return;
/*     */       } 
/*  96 */       this.synthesizer.open();
/*  97 */       this.sequencer = MidiSystem.getSequencer();
/*  98 */       this.sequence = new Sequence(0.0F, 10); }
/*  99 */     catch (Exception ex) { ex.printStackTrace(); return; }
/*     */     
/* 101 */     Soundbank sb = this.synthesizer.getDefaultSoundbank();
/*     */     
/* 103 */     if (sb != null) {
/* 104 */       this.instruments = this.synthesizer.getDefaultSoundbank().getInstruments();
/* 105 */       String ins = null;
/* 106 */       for (int i = 0; i < this.instruments.length; i++) {
/* 107 */         ins = String.valueOf(ins) + this.instruments[i].getName() + "-";
/*     */       }
/* 109 */       System.out.println("todos" + ins);
/* 110 */       this.synthesizer.loadInstrument(this.instruments[0]);
/*     */     } 
/* 112 */     MidiChannel[] midiChannels = this.synthesizer.getChannels();
/* 113 */     this.channels = new ChannelData[midiChannels.length];
/* 114 */     for (int i = 0; i < this.channels.length; i++) {
/* 115 */       this.channels[i] = new ChannelData(midiChannels[i], i);
/*     */     }
/* 117 */     this.cc = this.channels[0];
/*     */     
/* 119 */     ListSelectionModel lsm = this.table.getSelectionModel();
/* 120 */     lsm.setSelectionInterval(0, 0);
/* 121 */     lsm = this.table.getColumnModel().getSelectionModel();
/* 122 */     lsm.setSelectionInterval(0, 0);
/*     */   }
/*     */   
/*     */   class ChannelData {
/*     */     MidiChannel channel;
/*     */     boolean solo;
/*     */     boolean mono;
/*     */     boolean mute;
/*     */     boolean sustain;
/*     */     int velocity;
/*     */     int pressure;
/*     */     int bend;
/*     */     int reverb;
/*     */     int row;
/*     */     int col;
/*     */     int num;
/*     */     
/*     */     public ChannelData(MidiChannel channel, int num) {
/* 140 */       this.channel = channel;
/* 141 */       this.num = num;
/* 142 */       this.velocity = this.pressure = this.bend = this.reverb = 64;
/*     */     }
/*     */     
/*     */     public void setComponentStates() {
/* 146 */       PanelInstrumentos.this.table.setRowSelectionInterval(this.row, this.row);
/* 147 */       PanelInstrumentos.this.table.setColumnSelectionInterval(this.col, this.col);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 152 */       JSlider[] slider = { PanelInstrumentos.this.veloS, PanelInstrumentos.this.presS, PanelInstrumentos.this.bendS, PanelInstrumentos.this.revbS };
/* 153 */       int[] v = { this.velocity, this.pressure, this.bend, this.reverb };
/* 154 */       for (int i = 0; i < slider.length; i++) {
/* 155 */         TitledBorder tb = (TitledBorder)slider[i].getBorder();
/* 156 */         String s = tb.getTitle();
/* 157 */         tb.setTitle(String.valueOf(s.substring(0, s.indexOf('=') + 1)) + String.valueOf(v[i]));
/* 158 */         slider[i].repaint();
/*     */       } 
/*     */     } }
/*     */   
/*     */   public PanelInstrumentos(ClienteUDP cli, WireOutput output) {
/* 163 */     this.also = this.also;
/* 164 */     this.cli = cli;
/* 165 */     this.table = new JTable();
/* 166 */     open();
/* 167 */     setLayout(new BorderLayout());
/*     */     
/* 169 */     TableModel dataModel = new AbstractTableModel() {
/* 170 */         public int getColumnCount() { return PanelInstrumentos.this.nCols; }
/* 171 */         public int getRowCount() { return PanelInstrumentos.this.nRows; }
/*     */         public Object getValueAt(int r, int c) {
/* 173 */           if (PanelInstrumentos.this.instruments != null) {
/* 174 */             return PanelInstrumentos.this.instruments[c * PanelInstrumentos.this.nRows + r].getName();
/*     */           }
/* 176 */           return Integer.toString(c * PanelInstrumentos.this.nRows + r);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 181 */         public String getColumnName(int c) { return PanelInstrumentos.this.names[c]; }
/*     */ 
/*     */         
/* 184 */         public Class getColumnClass(int c) { return getValueAt(0, c).getClass(); }
/*     */         
/* 186 */         public boolean isCellEditable(int r, int c) { return false; }
/*     */         
/*     */         public void setValueAt(Object obj, int r, int c) {}
/*     */       };
/* 190 */     this.table.setModel(dataModel);
/* 191 */     this.table.setSelectionMode(0);
/*     */ 
/*     */     
/* 194 */     ListSelectionModel lsm = this.table.getSelectionModel();
/* 195 */     lsm.addListSelectionListener(new ListSelectionListener() {
/*     */           public void valueChanged(ListSelectionEvent e) {
/* 197 */             ListSelectionModel sm = (ListSelectionModel)e.getSource();
/* 198 */             if (!sm.isSelectionEmpty()) {
/* 199 */               PanelInstrumentos.this.cc.row = sm.getMinSelectionIndex();
/* 200 */               System.out.println("la seleccion es" + sm.getMinSelectionIndex());
/*     */             } 
/* 202 */             PanelInstrumentos.this.enviarEventoInstrumento(192, PanelInstrumentos.this.cc.col * PanelInstrumentos.this.nRows + PanelInstrumentos.this.cc.row);
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 208 */     lsm = this.table.getColumnModel().getSelectionModel();
/* 209 */     lsm.addListSelectionListener(new ListSelectionListener() {
/*     */           public void valueChanged(ListSelectionEvent e) {
/* 211 */             ListSelectionModel sm = (ListSelectionModel)e.getSource();
/* 212 */             if (!sm.isSelectionEmpty()) {
/* 213 */               PanelInstrumentos.this.cc.col = sm.getMinSelectionIndex();
/* 214 */               System.out.println("la seleccion es" + sm.getMinSelectionIndex());
/*     */             } 
/* 216 */             PanelInstrumentos.this.enviarEventoInstrumento(192, PanelInstrumentos.this.cc.col * PanelInstrumentos.this.nRows + PanelInstrumentos.this.cc.row);
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 221 */     this.table.setPreferredScrollableViewportSize(new Dimension(this.nCols * 110, 200));
/* 222 */     this.table.setCellSelectionEnabled(true);
/* 223 */     this.table.setColumnSelectionAllowed(true);
/* 224 */     for (int i = 0; i < this.names.length; i++) {
/* 225 */       TableColumn column = this.table.getColumn(this.names[i]);
/* 226 */       column.setPreferredWidth(110);
/*     */     } 
/* 228 */     this.table.setAutoResizeMode(0);
/*     */     
/* 230 */     JScrollPane sp = new JScrollPane(this.table);
/* 231 */     sp.setVerticalScrollBarPolicy(21);
/* 232 */     sp.setHorizontalScrollBarPolicy(32);
/* 233 */     add(sp);
/*     */   }
/*     */ 
/*     */   
/* 237 */   public Dimension getPreferredSize() { return new Dimension(800, 170); }
/*     */ 
/*     */   
/* 240 */   public Dimension getMaximumSize() { return new Dimension(800, 170); }
/*     */ 
/*     */   
/*     */   public void enviarEventoInstrumento(int type, int num) {
/* 244 */     ShortMessage message = new ShortMessage();
/* 245 */     System.out.println("envia");
/* 246 */     StringBuffer output = new StringBuffer();
/*     */ 
/*     */     
/*     */     try {
/* 250 */       System.out.println("tipo" + type + " num" + num + "ve" + this.cc.velocity);
/* 251 */       message.setMessage(type, num, this.cc.velocity);
/*     */       
/* 253 */       output.append("   Note On Key: ");
/* 254 */       System.out.println("envia" + type + this.cc.num + "nvia" + num + "cc.velocity" + this.cc.velocity + "cc.num" + this.cc.num);
/* 255 */       this.cli.cambiarInstrumento(num);
/*     */     }
/*     */     catch (Exception ex) {
/*     */       
/* 259 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/PanelInstrumentos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */