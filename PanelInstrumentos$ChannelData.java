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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 140 */     this.channel = channel;
/* 141 */     this.num = num;
/* 142 */     this.velocity = this.pressure = this.bend = this.reverb = 64;
/*     */   }
/*     */   
/*     */   public void setComponentStates() {
/* 146 */     PanelInstrumentos.this.table.setRowSelectionInterval(this.row, this.row);
/* 147 */     PanelInstrumentos.this.table.setColumnSelectionInterval(this.col, this.col);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 152 */     JSlider[] slider = { PanelInstrumentos.this.veloS, PanelInstrumentos.this.presS, PanelInstrumentos.this.bendS, PanelInstrumentos.this.revbS };
/* 153 */     int[] v = { this.velocity, this.pressure, this.bend, this.reverb };
/* 154 */     for (int i = 0; i < slider.length; i++) {
/* 155 */       TitledBorder tb = (TitledBorder)slider[i].getBorder();
/* 156 */       String s = tb.getTitle();
/* 157 */       tb.setTitle(String.valueOf(s.substring(0, s.indexOf('=') + 1)) + String.valueOf(v[i]));
/* 158 */       slider[i].repaint();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/PanelInstrumentos$ChannelData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */