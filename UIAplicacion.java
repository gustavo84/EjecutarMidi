/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.event.WindowListener;
/*     */ import java.io.File;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
/*     */ import javax.sound.midi.MidiUnavailableException;
/*     */ import javax.swing.ComboBoxModel;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UIAplicacion
/*     */   extends JFrame
/*     */   implements WindowListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  54 */   JPanel pContenedor = new JPanel();
/*  55 */   String direcc = "";
/*     */ 
/*     */   
/*     */   CalcularTRespuesta cal;
/*     */ 
/*     */   
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   
/*     */   private JLabel jLabel1;
/*     */ 
/*     */   
/*     */   private JLabel jLabel2;
/*     */ 
/*     */   
/*     */   private JComboBox jComboBoxClienteIn;
/*     */ 
/*     */   
/*     */   private JLabel jLabel3;
/*     */ 
/*     */   
/*     */   private JComboBox jComboBoxServer;
/*     */ 
/*     */   
/*     */   private JButton jButtonServerStart;
/*     */ 
/*     */   
/*     */   private ServidorUDP serUDP;
/*     */ 
/*     */   
/*     */   private JScrollPane JScollInstrumentos;
/*     */ 
/*     */   
/*     */   boolean valido = false;
/*     */ 
/*     */   
/*     */   private Vector<String> registrar2;
/*     */ 
/*     */   
/*     */   private WireOutput outputServer;
/*     */ 
/*     */   
/*     */   private PanelTeclado midiSynth;
/*     */ 
/*     */   
/*     */   private ClienteUDP cli;
/*     */ 
/*     */   
/*     */   DefaultTableModel dtm;
/*     */ 
/*     */   
/*     */   JScrollPane scrollPane;
/*     */ 
/*     */   
/*     */   JFrame f;
/*     */ 
/*     */   
/*     */   HashMap<String, Long> mapTiempo;
/*     */ 
/*     */   
/*     */   JTable table;
/*     */   
/*     */   private UIAplicacion ins;
/*     */   
/*     */   private ClienteTCP clienteTCP;
/*     */   
/*     */   private HashMap<String, WireOutput> mapRep;
/*     */   
/*     */   private ListenerServidor listenerAct;
/*     */ 
/*     */   
/*     */   public UIAplicacion(ClienteTCP z) {
/* 127 */     this.clienteTCP = z;
/* 128 */     this.ins = this;
/* 129 */     System.out.println("pasaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
/* 130 */     this.cli = new ClienteUDP();
/*     */ 
/*     */ 
/*     */     
/* 134 */     try { this.outputServer = new WireOutput(); } catch (Exception e) { System.out.println(e.toString()); }
/* 135 */      this.serUDP = new ServidorUDP(this.outputServer);
/* 136 */     this.serUDP.start();
/*     */     
/*     */     try {
/* 139 */       jbInit();
/*     */     }
/* 141 */     catch (Exception e) {
/* 142 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void jbInit() throws Exception {
/* 148 */     setBackground(Color.lightGray);
/* 149 */     setSize(new Dimension(800, 500));
/* 150 */     setTitle("Ventana Conexion");
/*     */ 
/*     */     
/* 153 */     this.pContenedor.setLayout(null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     getContentPane().add(this.pContenedor);
/* 162 */     this.pContenedor.setPreferredSize(new Dimension(743, 417));
/*     */ 
/*     */     
/* 165 */     this.jButtonServerStart = new JButton();
/* 166 */     this.pContenedor.add(this.jButtonServerStart);
/*     */     
/* 168 */     this.jButtonServerStart.setText("Parar");
/* 169 */     this.jButtonServerStart.setBounds(441, 168, 97, 21);
/* 170 */     this.jButtonServerStart.addActionListener(new ActionListener()
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 178 */             if (UIAplicacion.this.jButtonServerStart.getText().compareTo("Parar") == 0) {
/* 179 */               UIAplicacion.this.outputServer.close();
/*     */               
/* 181 */               UIAplicacion.this.jButtonServerStart.setText("Escuchar");
/* 182 */               System.out.println("entra en parar");
/*     */             }
/* 184 */             else if (UIAplicacion.this.jButtonServerStart.getText().compareTo("Escuchar") == 0) {
/*     */               try {
/* 186 */                 UIAplicacion.this.outputServer.open();
/* 187 */                 UIAplicacion.this.jButtonServerStart.setText("Parar");
/* 188 */                 System.out.println("entra en escuchar");
/* 189 */               } catch (MidiUnavailableException e1) {
/* 190 */                 e1.printStackTrace();
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 195 */             System.out.println("Started the output");
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 204 */     ComboBoxModel<String> jComboBoxServerModel = 
/* 205 */       new DefaultComboBoxModel<String>(
/* 206 */         new String[0]);
/* 207 */     this.jComboBoxServer = new JComboBox();
/* 208 */     this.pContenedor.add(this.jComboBoxServer);
/* 209 */     this.jComboBoxServer.setModel(jComboBoxServerModel);
/* 210 */     this.jComboBoxServer.setBounds(198, 168, 201, 21);
/* 211 */     this.jComboBoxServer.addItemListener(new ItemListener()
/*     */         {
/*     */           
/*     */           public void itemStateChanged(ItemEvent evt)
/*     */           {
/* 216 */             if (evt.getStateChange() != 1)
/* 217 */               return;  int i = ((JComboBox)evt.getSource()).getSelectedIndex();
/* 218 */             System.out.println("Setting MIDI output port to " + (i - 1)); try {
/* 219 */               UIAplicacion.this.outputServer.setport(i - 1);
/* 220 */               System.out.println("ennnnnnnnntraaaaaaaaaaaaaaaaaaaaaaaaaa" + i);
/* 221 */               if (i == 0) {
/*     */                 
/* 223 */                 UIAplicacion.this.jButtonServerStart.setEnabled(false);
/* 224 */                 UIAplicacion.this.outputServer.close();
/*     */               } else {
/* 226 */                 System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
/* 227 */                 UIAplicacion.this.jButtonServerStart.setEnabled(true);
/* 228 */                 UIAplicacion.this.outputServer.open();
/*     */               } 
/*     */             } catch (Exception e) {
/*     */               
/* 232 */               System.out.println(e.getMessage());
/*     */             }  }
/*     */         });
/* 235 */     this.jComboBoxServer.addItem("None");
/*     */ 
/*     */     
/* 238 */     try { System.out.println("asddfadsfasdfasdfa" + this.outputServer.getMaxReceivers());
/* 239 */       for (int i = 0; i < this.outputServer.getMaxReceivers(); i++)
/* 240 */         this.jComboBoxServer.addItem(this.outputServer.getDeviceName(i));  }
/* 241 */     catch (Exception e) { System.out.println(e.getMessage()); }
/*     */ 
/*     */ 
/*     */     
/* 245 */     this.jLabel3 = new JLabel();
/* 246 */     this.pContenedor.add(this.jLabel3);
/* 247 */     this.jLabel3.setText("Seleccione el dispositivo para reproducir la información");
/* 248 */     this.jLabel3.setBounds(198, 132, 363, 14);
/*     */ 
/*     */     
/* 251 */     ComboBoxModel<String> jComboBoxClienteInModel = 
/* 252 */       new DefaultComboBoxModel<String>(
/* 253 */         new String[0]);
/* 254 */     this.jComboBoxClienteIn = new JComboBox();
/* 255 */     this.pContenedor.add(this.jComboBoxClienteIn);
/* 256 */     this.jComboBoxClienteIn.setModel(jComboBoxClienteInModel);
/* 257 */     this.jComboBoxClienteIn.setBounds(198, 44, 201, 21);
/* 258 */     this.jComboBoxClienteIn.addItemListener(new ItemListener()
/*     */         {
/*     */           public void itemStateChanged(ItemEvent evt)
/*     */           {
/* 262 */             if (evt.getStateChange() != 1)
/*     */               return; 
/* 264 */             int i = ((JComboBox)evt.getSource()).getSelectedIndex();
/*     */ 
/*     */             
/* 267 */             if (i == 1) {
/*     */               
/* 269 */               UIAplicacion.this.midiSynth = new PanelTeclado(UIAplicacion.this.cli);
/* 270 */               UIAplicacion.this.midiSynth.setEnviar(true);
/* 271 */               UIAplicacion.this.midiSynth.open();
/*     */ 
/*     */               
/* 274 */               UIAplicacion.this.midiSynth.show();
/* 275 */               UIAplicacion.this.f = new JFrame("Sintetizador midi");
/* 276 */               UIAplicacion.this.f.addWindowListener(new WindowAdapter() {
/* 277 */                     public void windowClosing(WindowEvent e) { System.exit(0); }
/*     */                   });
/* 279 */               UIAplicacion.this.f.getContentPane().add("Center", UIAplicacion.this.midiSynth);
/* 280 */               UIAplicacion.this.f.pack();
/* 281 */               Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 282 */               int w = 760;
/* 283 */               int h = 170;
/* 284 */               UIAplicacion.this.f.setLocation(UIAplicacion.this.getX(), UIAplicacion.this.getY() + (UIAplicacion.this.getSize()).height);
/* 285 */               UIAplicacion.this.f.setDefaultCloseOperation(0);
/* 286 */               UIAplicacion.this.f.setSize(w, h);
/* 287 */               UIAplicacion.this.f.setVisible(true);
/*     */             } 
/*     */           }
/*     */         });
/* 291 */     this.jComboBoxClienteIn.addItem("None");
/*     */     
/*     */     try {
/* 294 */       this.jComboBoxClienteIn.addItem("Teclado simulado");
/*     */     } catch (Exception e) {
/* 296 */       System.out.println(e.getMessage());
/*     */     } 
/*     */ 
/*     */     
/* 300 */     this.jLabel2 = new JLabel();
/* 301 */     this.pContenedor.add(this.jLabel2);
/* 302 */     this.jLabel2.setText("Seleccione el dispositivo por el que quiere capturar la información");
/* 303 */     this.jLabel2.setBounds(198, 12, 384, 14);
/*     */ 
/*     */     
/* 306 */     this.jLabel1 = new JLabel();
/* 307 */     this.pContenedor.add(this.jLabel1);
/* 308 */     this.jLabel1.setText("Clientes conectados");
/* 309 */     this.jLabel1.setBounds(8, 12, 165, 14);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 314 */     this.jPanel1 = new PanelInstrumentos(this.cli, this.outputServer);
/* 315 */     this.pContenedor.add(this.jPanel1);
/*     */     
/* 317 */     this.jPanel1.setBounds(0, 300, 800, 170);
/*     */ 
/*     */ 
/*     */     
/* 321 */     addWindowListener(new WindowAdapter()
/*     */         {
/* 323 */           public void windowClosing(WindowEvent e) { UIAplicacion.this.desconectarServidorCliente(); }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void windowActivated(WindowEvent arg0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void windowClosed(WindowEvent arg0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 341 */   public void windowClosing(WindowEvent arg0) { System.exit(0); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void windowDeactivated(WindowEvent arg0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void windowDeiconified(WindowEvent arg0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void windowIconified(WindowEvent arg0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void windowOpened(WindowEvent arg0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void actualizar(HashMap<String, String> mapUser, HashMap<String, Long> mapTime) {
/* 367 */     System.out.println("pasa por actualizar");
/*     */ 
/*     */ 
/*     */     
/* 371 */     this.cli.actualizarUsuarios(mapUser);
/* 372 */     this.cal = new CalcularTRespuesta(this, mapUser, mapTime);
/*     */     
/* 374 */     this.cal.start();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void actualizar2(HashMap<String, Long> mapTime) {
/* 380 */     this.mapTiempo = mapTime;
/* 381 */     actualizar();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void actualizar() {
/* 387 */     System.out.println("entra en actualizar");
/* 388 */     this.dtm = new DefaultTableModel();
/* 389 */     Iterator<Map.Entry> it = this.mapTiempo.entrySet().iterator();
/* 390 */     this.dtm.addColumn("Usuario");
/* 391 */     this.dtm.addColumn("T. Respuesta");
/* 392 */     while (it.hasNext()) {
/* 393 */       Object[] data = new Object[2];
/* 394 */       System.out.println("entra en actualizar2");
/* 395 */       Map.Entry e = it.next();
/* 396 */       System.out.println("entra aqi" + e.getKey() + " " + e.getValue());
/* 397 */       data[0] = e.getKey();
/* 398 */       data[1] = e.getValue();
/* 399 */       this.dtm.addRow(data);
/*     */     } 
/*     */ 
/*     */     
/* 403 */     this.table = new JTable(this.dtm);
/*     */ 
/*     */     
/* 406 */     this.table.setPreferredScrollableViewportSize(new Dimension(134, 221));
/* 407 */     this.scrollPane = new JScrollPane(this.table);
/* 408 */     this.scrollPane.setBounds(12, 38, 161, 241);
/* 409 */     this.scrollPane.repaint();
/*     */ 
/*     */     
/* 412 */     this.pContenedor.add(this.scrollPane);
/*     */   }
/*     */ 
/*     */   
/*     */   public void desconectarCliente() {
/* 417 */     this.clienteTCP.cerrarSocket();
/* 418 */     String sFichero = "fichero.txt";
/* 419 */     File fichero = new File(sFichero);
/* 420 */     this.cli.desconectar();
/* 421 */     this.cal.desconectar();
/* 422 */     System.exit(0);
/*     */   }
/*     */   
/*     */   public void desconectarServidorCliente() {
/* 426 */     this.clienteTCP.desconectar();
/* 427 */     this.cli.desconectar();
/* 428 */     this.cal.desconectar();
/* 429 */     System.exit(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 434 */   public void pasarInstancia(ListenerServidor listenerAct) { this.listenerAct = listenerAct; }
/*     */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/UIAplicacion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */