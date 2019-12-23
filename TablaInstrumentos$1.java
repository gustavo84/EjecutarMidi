/*     */ import javax.swing.table.AbstractTableModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   extends AbstractTableModel
/*     */ {
/* 125 */   public int getColumnCount() { return TablaInstrumentos.access$0(TablaInstrumentos.this); }
/* 126 */   public int getRowCount() { return TablaInstrumentos.access$1(TablaInstrumentos.this); }
/*     */   public Object getValueAt(int r, int c) {
/* 128 */     if (TablaInstrumentos.this.instruments != null) {
/* 129 */       return TablaInstrumentos.this.instruments[c * TablaInstrumentos.access$1(TablaInstrumentos.this) + r].getName();
/*     */     }
/* 131 */     return Integer.toString(c * TablaInstrumentos.access$1(TablaInstrumentos.this) + r);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 136 */   public String getColumnName(int c) { return TablaInstrumentos.access$2(TablaInstrumentos.this)[c]; }
/*     */ 
/*     */   
/* 139 */   public Class getColumnClass(int c) { return getValueAt(0, c).getClass(); }
/*     */   
/* 141 */   public boolean isCellEditable(int r, int c) { return false; }
/*     */   
/*     */   public void setValueAt(Object obj, int r, int c) {}
/*     */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/TablaInstrumentos$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */