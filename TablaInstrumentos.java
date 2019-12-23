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
/*     */ public class TablaInstrumentos
/*     */   extends JPanel
/*     */ {
/*  33 */   final int PROGRAM = 192; Sequencer sequencer; Sequence sequence; String simular;
/*     */   Synthesizer synthesizer;
/*     */   Instrument[] instruments;
/*     */   ChannelData[] channels;
/*     */   ChannelData cc;
/*     */   JTable table;
/*     */   JSlider veloS;
/*     */   JSlider presS;
/*     */   JSlider bendS;
/*     */   JSlider revbS;
/*  43 */   private String[] names = new String[] { 
/*  44 */       "Piano", "Chromatic Perc.", "Organ", "Guitar", 
/*  45 */       "Bass", "Strings", "Ensemble", "Brass", 
/*  46 */       "Reed", "Pipe", "Synth Lead", "Synth Pad", 
/*  47 */       "Synth Effects", "Ethnic", "Percussive", "Sound Effects" };
/*  48 */   private int nRows = 8;
/*  49 */   private int nCols = this.names.length;
/*     */   
/*     */   private Receiver also;
/*     */   private ClienteUDP cli;
/*     */   
/*  54 */   public void setSimular(String simular) { this.simular = simular; }
/*     */ 
/*     */   
/*     */   public void open() {
/*     */     
/*  59 */     try { if (this.synthesizer == null && (
/*  60 */         this.synthesizer = MidiSystem.getSynthesizer()) == null) {
/*  61 */         System.out.println("getSynthesizer() failed!");
/*     */         
/*     */         return;
/*     */       } 
/*  65 */       this.synthesizer.open();
/*  66 */       this.sequencer = MidiSystem.getSequencer();
/*  67 */       this.sequence = new Sequence(0.0F, 10); }
/*  68 */     catch (Exception ex) { ex.printStackTrace(); return; }
/*     */     
/*  70 */     Soundbank sb = this.synthesizer.getDefaultSoundbank();
/*  71 */     if (sb != null) {
/*  72 */       this.instruments = this.synthesizer.getDefaultSoundbank().getInstruments();
/*  73 */       this.synthesizer.loadInstrument(this.instruments[0]);
/*     */     } 
/*  75 */     MidiChannel[] midiChannels = this.synthesizer.getChannels();
/*  76 */     this.channels = new ChannelData[midiChannels.length];
/*  77 */     for (int i = 0; i < this.channels.length; i++) {
/*  78 */       this.channels[i] = new ChannelData(midiChannels[i], i);
/*     */     }
/*  80 */     this.cc = this.channels[0];
/*     */     
/*  82 */     ListSelectionModel lsm = this.table.getSelectionModel();
/*  83 */     lsm.setSelectionInterval(0, 0);
/*  84 */     lsm = this.table.getColumnModel().getSelectionModel();
/*  85 */     lsm.setSelectionInterval(0, 0);
/*     */   }
/*     */   class ChannelData { MidiChannel channel;
/*     */     boolean solo;
/*     */     boolean mono;
/*     */     boolean mute;
/*     */     boolean sustain;
/*     */     int velocity;
/*     */     
/*     */     public ChannelData(MidiChannel channel, int num) {
/*  95 */       this.channel = channel;
/*  96 */       this.num = num;
/*  97 */       this.velocity = this.pressure = this.bend = this.reverb = 64;
/*     */     }
/*     */     int pressure; int bend; int reverb; int row; int col; int num;
/*     */     public void setComponentStates() {
/* 101 */       TablaInstrumentos.this.table.setRowSelectionInterval(this.row, this.row);
/* 102 */       TablaInstrumentos.this.table.setColumnSelectionInterval(this.col, this.col);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 107 */       JSlider[] slider = { TablaInstrumentos.this.veloS, TablaInstrumentos.this.presS, TablaInstrumentos.this.bendS, TablaInstrumentos.this.revbS };
/* 108 */       int[] v = { this.velocity, this.pressure, this.bend, this.reverb };
/* 109 */       for (int i = 0; i < slider.length; i++) {
/* 110 */         TitledBorder tb = (TitledBorder)slider[i].getBorder();
/* 111 */         String s = tb.getTitle();
/* 112 */         tb.setTitle(String.valueOf(s.substring(0, s.indexOf('=') + 1)) + String.valueOf(v[i]));
/* 113 */         slider[i].repaint();
/*     */       } 
/*     */     } }
/*     */   
/*     */   public TablaInstrumentos(ClienteUDP cli, WireOutput output) {
/* 118 */     this.also = this.also;
/* 119 */     this.cli = cli;
/* 120 */     this.table = new JTable();
/* 121 */     open();
/* 122 */     setLayout(new BorderLayout());
/*     */     
/* 124 */     TableModel dataModel = new AbstractTableModel() {
/* 125 */         public int getColumnCount() { return TablaInstrumentos.this.nCols; }
/* 126 */         public int getRowCount() { return TablaInstrumentos.this.nRows; }
/*     */         public Object getValueAt(int r, int c) {
/* 128 */           if (TablaInstrumentos.this.instruments != null) {
/* 129 */             return TablaInstrumentos.this.instruments[c * TablaInstrumentos.this.nRows + r].getName();
/*     */           }
/* 131 */           return Integer.toString(c * TablaInstrumentos.this.nRows + r);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 136 */         public String getColumnName(int c) { return TablaInstrumentos.this.names[c]; }
/*     */ 
/*     */         
/* 139 */         public Class getColumnClass(int c) { return getValueAt(0, c).getClass(); }
/*     */         
/* 141 */         public boolean isCellEditable(int r, int c) { return false; }
/*     */         
/*     */         public void setValueAt(Object obj, int r, int c) {}
/*     */       };
/* 145 */     this.table.setModel(dataModel);
/* 146 */     this.table.setSelectionMode(0);
/*     */ 
/*     */     
/* 149 */     ListSelectionModel lsm = this.table.getSelectionModel();
/* 150 */     lsm.addListSelectionListener(new ListSelectionListener() {
/*     */           public void valueChanged(ListSelectionEvent e) {
/* 152 */             ListSelectionModel sm = (ListSelectionModel)e.getSource();
/* 153 */             if (!sm.isSelectionEmpty()) {
/* 154 */               TablaInstrumentos.this.cc.row = sm.getMinSelectionIndex();
/* 155 */               System.out.println("la seleccion es" + sm.getMinSelectionIndex());
/*     */             } 
/* 157 */             TablaInstrumentos.this.enviarEventoInstrumento(192, TablaInstrumentos.this.cc.col * TablaInstrumentos.this.nRows + TablaInstrumentos.this.cc.row);
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 163 */     lsm = this.table.getColumnModel().getSelectionModel();
/* 164 */     lsm.addListSelectionListener(new ListSelectionListener() {
/*     */           public void valueChanged(ListSelectionEvent e) {
/* 166 */             ListSelectionModel sm = (ListSelectionModel)e.getSource();
/* 167 */             if (!sm.isSelectionEmpty()) {
/* 168 */               TablaInstrumentos.this.cc.col = sm.getMinSelectionIndex();
/* 169 */               System.out.println("la seleccion es" + sm.getMinSelectionIndex());
/*     */             } 
/* 171 */             TablaInstrumentos.this.enviarEventoInstrumento(192, TablaInstrumentos.this.cc.col * TablaInstrumentos.this.nRows + TablaInstrumentos.this.cc.row);
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 176 */     this.table.setPreferredScrollableViewportSize(new Dimension(this.nCols * 110, 200));
/* 177 */     this.table.setCellSelectionEnabled(true);
/* 178 */     this.table.setColumnSelectionAllowed(true);
/* 179 */     for (int i = 0; i < this.names.length; i++) {
/* 180 */       TableColumn column = this.table.getColumn(this.names[i]);
/* 181 */       column.setPreferredWidth(110);
/*     */     } 
/* 183 */     this.table.setAutoResizeMode(0);
/*     */     
/* 185 */     JScrollPane sp = new JScrollPane(this.table);
/* 186 */     sp.setVerticalScrollBarPolicy(21);
/* 187 */     sp.setHorizontalScrollBarPolicy(32);
/* 188 */     add(sp);
/*     */   }
/*     */ 
/*     */   
/* 192 */   public Dimension getPreferredSize() { return new Dimension(800, 170); }
/*     */ 
/*     */   
/* 195 */   public Dimension getMaximumSize() { return new Dimension(800, 170); }
/*     */ 
/*     */   
/*     */   public void enviarEventoInstrumento(int type, int num) {
/* 199 */     ShortMessage message = new ShortMessage();
/* 200 */     System.out.println("envia");
/* 201 */     StringBuffer output = new StringBuffer();
/*     */ 
/*     */     
/*     */     try {
/* 205 */       System.out.println("tipo" + type + " num" + num + "ve" + this.cc.velocity);
/* 206 */       message.setMessage(type, num, this.cc.velocity);
/*     */       
/* 208 */       output.append("   Note On Key: ");
/* 209 */       System.out.println("envia" + type + this.cc.num + "nvia" + num + "cc.velocity" + this.cc.velocity + "cc.num" + this.cc.num);
/*     */       
/* 211 */       this.cli.enviar(type, num, this.cc.velocity, "SIMULAR");
/*     */     } catch (Exception ex) {
/*     */       
/* 214 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/TablaInstrumentos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */