/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowEvent;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UIPedirIp
/*     */   extends JFrame
/*     */ {
/*  24 */   JPanel pContenedor = new JPanel();
/*  25 */   JTextField txtDirecc1 = new JTextField();
/*  26 */   JTextField txtDirecc2 = new JTextField();
/*  27 */   JTextField txtDirecc3 = new JTextField();
/*  28 */   JTextField txtDirecc4 = new JTextField();
/*  29 */   JButton btnConectarMismo = new JButton();
/*  30 */   JButton btnConectar = new JButton();
/*  31 */   JLabel texto = new JLabel();
/*  32 */   String direcc = "";
/*     */   
/*     */   private JTextField jTextUser;
/*     */   private JLabel jLabel1;
/*     */   boolean valido = false;
/*     */   
/*     */   public UIPedirIp() {
/*     */     try {
/*  40 */       jbInit();
/*     */     }
/*  42 */     catch (Exception e) {
/*  43 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   void jbInit() throws Exception {
/*  47 */     setBackground(Color.lightGray);
/*  48 */     setSize(new Dimension(400, 300));
/*  49 */     setTitle("Ventana Conexion");
/*     */ 
/*     */     
/*  52 */     this.pContenedor.setLayout(null);
/*     */     
/*  54 */     this.pContenedor.add(this.btnConectarMismo);
/*  55 */     this.btnConectarMismo.setText("Pulse aqui si el servidor en la misma maquina");
/*  56 */     this.btnConectarMismo.setBounds(new Rectangle(25, 220, 250, 40));
/*  57 */     this.btnConectarMismo.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent e) {
/*  59 */             UIPedirIp.this.btnConectarMismo_actionPerformed(e);
/*     */           }
/*     */         });
/*     */     
/*  63 */     this.pContenedor.add(this.btnConectar);
/*  64 */     this.btnConectar.setText("Conectar");
/*  65 */     this.btnConectar.setBounds(49, 199, 150, 20);
/*  66 */     this.btnConectar.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent e) {
/*  68 */             UIPedirIp.this.btnConectar_actionPerformed(e);
/*     */           }
/*     */         });
/*     */     
/*  72 */     this.pContenedor.add(this.texto);
/*  73 */     this.texto.setText("Intoduzca una IP o pulse el boton inferior");
/*  74 */     this.texto.setBounds(91, 19, 250, 30);
/*     */     
/*  76 */     this.pContenedor.add(this.txtDirecc1);
/*  77 */     this.txtDirecc1.setBounds(91, 69, 42, 28);
/*  78 */     this.pContenedor.add(this.txtDirecc2);
/*  79 */     this.txtDirecc2.setBounds(151, 69, 42, 28);
/*  80 */     this.pContenedor.add(this.txtDirecc3);
/*  81 */     this.txtDirecc3.setBounds(211, 69, 42, 28);
/*  82 */     this.pContenedor.add(this.txtDirecc4);
/*  83 */     this.txtDirecc4.setBounds(281, 69, 42, 28);
/*     */     
/*  85 */     this.jTextUser = new JTextField();
/*  86 */     this.jTextUser.setText("");
/*  87 */     this.pContenedor.add(this.jTextUser);
/*  88 */     this.jTextUser.setBounds(91, 142, 102, 23);
/*     */ 
/*     */     
/*  91 */     this.jLabel1 = new JLabel();
/*  92 */     this.pContenedor.add(this.jLabel1);
/*  93 */     this.jLabel1.setText("Introduzca nombre de usuario");
/*  94 */     this.jLabel1.setBounds(91, 109, 207, 14);
/*     */ 
/*     */     
/*  97 */     this.pContenedor.add(this.btnConectarMismo);
/*  98 */     this.btnConectarMismo.setText("LOCALHOST");
/*  99 */     this.btnConectarMismo.setBounds(223, 195, 147, 28);
/*     */     
/* 101 */     this.pContenedor.setBorder(BorderFactory.createTitledBorder("Establecimiento de la conexion"));
/*     */     
/* 103 */     getContentPane().add(this.pContenedor);
/* 104 */     setResizable(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void btnConectar_actionPerformed(ActionEvent e) {
/* 110 */     if (okNumero(this.txtDirecc1.getText()) && okNumero(this.txtDirecc2.getText()) && okNumero(this.txtDirecc3.getText()) && okNumero(this.txtDirecc4.getText()))
/* 111 */       this.direcc = validarIP(this.txtDirecc1.getText(), this.txtDirecc2.getText(), this.txtDirecc3.getText(), this.txtDirecc4.getText()); 
/* 112 */     if (!this.direcc.equals("") && this.jTextUser.getText().compareTo("") != 0) {
/*     */       
/* 114 */       this.valido = true;
/*     */     }
/*     */     else {
/*     */       
/* 118 */       JOptionPane.showMessageDialog(this, "Por favor, introduzca una direccion IP valida", "Error", 0);
/* 119 */       JOptionPane.showMessageDialog(this, "Por favor, introduzca un nombre de usuario", "Error", 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String validarIP(String s1, String s2, String s3, String s4) {
/* 128 */     int p1 = Integer.parseInt(s1);
/* 129 */     int p2 = Integer.parseInt(s2);
/* 130 */     int p3 = Integer.parseInt(s3);
/* 131 */     int p4 = Integer.parseInt(s4);
/*     */     
/* 133 */     String esValido = "";
/*     */     
/* 135 */     if (p1 > 0 && p2 >= 0 && p3 >= 0 && p4 >= 0 && p1 < 256 && p2 < 256 && p3 < 256 && p4 < 256) {
/* 136 */       esValido = String.valueOf(s1) + "." + s2 + "." + s3 + "." + s4;
/*     */     }
/* 138 */     return esValido;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean okNumero(String cadena) {
/* 143 */     int len = cadena.length();
/* 144 */     boolean ok = true;
/* 145 */     if (cadena.equals("")) {
/* 146 */       return false;
/*     */     }
/* 148 */     for (int cont = 0; cont < len && ok; cont++) {
/* 149 */       if (!Character.isDigit(cadena.charAt(cont))) {
/* 150 */         ok = false;
/*     */       }
/*     */     } 
/* 153 */     return ok;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void btnConectarMismo_actionPerformed(ActionEvent e) {
/* 159 */     this.direcc = "127.0.0.1";
/*     */     
/* 161 */     if (this.jTextUser.getText().compareTo("") != 0) {
/*     */       
/* 163 */       this.valido = true;
/*     */     } else {
/* 165 */       JOptionPane.showMessageDialog(this, "Por favor, introduzca un nombre de usuario", "Error", 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processWindowEvent(WindowEvent e) {
/* 171 */     if (e.getID() == 201) {
/* 172 */       cancel();
/* 173 */       System.exit(0);
/*     */     } 
/* 175 */     super.processWindowEvent(e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 180 */   void cancel() { dispose(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 185 */   public String getHost() { return this.direcc; }
/*     */ 
/*     */ 
/*     */   
/* 189 */   public String getName() { return this.jTextUser.getText(); }
/*     */ 
/*     */ 
/*     */   
/* 193 */   public boolean esValido() { return this.valido; }
/*     */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/UIPedirIp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */