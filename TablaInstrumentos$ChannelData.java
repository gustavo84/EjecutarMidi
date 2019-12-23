/*     */ import javax.sound.midi.MidiChannel;
/*     */ import javax.swing.JSlider;
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
/*     */ class ChannelData
/*     */ {
/*     */   MidiChannel channel;
/*     */   boolean solo;
/*     */   boolean mono;
/*     */   boolean mute;
/*     */   boolean sustain;
/*     */   int velocity;
/*     */   int pressure;
/*     */   int bend;
/*     */   int reverb;
/*     */   int row;
/*     */   int col;
/*     */   int num;
/*     */   
/*     */   public ChannelData(MidiChannel channel, int num) {
/*  95 */     this.channel = channel;
/*  96 */     this.num = num;
/*  97 */     this.velocity = this.pressure = this.bend = this.reverb = 64;
/*     */   }
/*     */   
/*     */   public void setComponentStates() {
/* 101 */     TablaInstrumentos.this.table.setRowSelectionInterval(this.row, this.row);
/* 102 */     TablaInstrumentos.this.table.setColumnSelectionInterval(this.col, this.col);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     JSlider[] slider = { TablaInstrumentos.this.veloS, TablaInstrumentos.this.presS, TablaInstrumentos.this.bendS, TablaInstrumentos.this.revbS };
/* 108 */     int[] v = { this.velocity, this.pressure, this.bend, this.reverb };
/* 109 */     for (int i = 0; i < slider.length; i++) {
/* 110 */       TitledBorder tb = (TitledBorder)slider[i].getBorder();
/* 111 */       String s = tb.getTitle();
/* 112 */       tb.setTitle(String.valueOf(s.substring(0, s.indexOf('=') + 1)) + String.valueOf(v[i]));
/* 113 */       slider[i].repaint();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/TablaInstrumentos$ChannelData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */