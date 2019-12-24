import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UIPedirIp extends JFrame {
   JPanel pContenedor = new JPanel();
   JTextField txtDirecc1 = new JTextField();
   JTextField txtDirecc2 = new JTextField();
   JTextField txtDirecc3 = new JTextField();
   JTextField txtDirecc4 = new JTextField();
   JButton btnConectarMismo = new JButton();
   JButton btnConectar = new JButton();
   JLabel texto = new JLabel();
   String direcc = "";
   private JTextField jTextUser;
   private JLabel jLabel1;
   boolean valido = false;

   public UIPedirIp() {
      try {
         this.jbInit();
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   void jbInit() throws Exception {
      this.setBackground(Color.lightGray);
      this.setSize(new Dimension(400, 300));
      this.setTitle("Ventana Conexion");
      this.pContenedor.setLayout((LayoutManager)null);
      this.pContenedor.add(this.btnConectarMismo);
      this.btnConectarMismo.setText("Pulse aqui si el servidor en la misma maquina");
      this.btnConectarMismo.setBounds(new Rectangle(25, 220, 250, 40));
      this.btnConectarMismo.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            UIPedirIp.this.btnConectarMismo_actionPerformed(e);
         }
      });
      this.pContenedor.add(this.btnConectar);
      this.btnConectar.setText("Conectar");
      this.btnConectar.setBounds(49, 199, 150, 20);
      this.btnConectar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            UIPedirIp.this.btnConectar_actionPerformed(e);
         }
      });
      this.pContenedor.add(this.texto);
      this.texto.setText("Intoduzca una IP o pulse el boton inferior");
      this.texto.setBounds(91, 19, 250, 30);
      this.pContenedor.add(this.txtDirecc1);
      this.txtDirecc1.setBounds(91, 69, 42, 28);
      this.pContenedor.add(this.txtDirecc2);
      this.txtDirecc2.setBounds(151, 69, 42, 28);
      this.pContenedor.add(this.txtDirecc3);
      this.txtDirecc3.setBounds(211, 69, 42, 28);
      this.pContenedor.add(this.txtDirecc4);
      this.txtDirecc4.setBounds(281, 69, 42, 28);
      this.jTextUser = new JTextField();
      this.jTextUser.setText("");
      this.pContenedor.add(this.jTextUser);
      this.jTextUser.setBounds(91, 142, 102, 23);
      this.jLabel1 = new JLabel();
      this.pContenedor.add(this.jLabel1);
      this.jLabel1.setText("Introduzca nombre de usuario");
      this.jLabel1.setBounds(91, 109, 207, 14);
      this.pContenedor.add(this.btnConectarMismo);
      this.btnConectarMismo.setText("LOCALHOST");
      this.btnConectarMismo.setBounds(223, 195, 147, 28);
      this.pContenedor.setBorder(BorderFactory.createTitledBorder("Establecimiento de la conexion"));
      this.getContentPane().add(this.pContenedor);
      this.setResizable(false);
   }

   void btnConectar_actionPerformed(ActionEvent e) {
      if (this.okNumero(this.txtDirecc1.getText()) && this.okNumero(this.txtDirecc2.getText()) && this.okNumero(this.txtDirecc3.getText()) && this.okNumero(this.txtDirecc4.getText())) {
         this.direcc = this.validarIP(this.txtDirecc1.getText(), this.txtDirecc2.getText(), this.txtDirecc3.getText(), this.txtDirecc4.getText());
      }

      if (!this.direcc.equals("") && this.jTextUser.getText().compareTo("") != 0) {
         this.valido = true;
      } else {
         JOptionPane.showMessageDialog(this, "Por favor, introduzca una direccion IP valida", "Error", 0);
         JOptionPane.showMessageDialog(this, "Por favor, introduzca un nombre de usuario", "Error", 0);
      }

   }

   private String validarIP(String s1, String s2, String s3, String s4) {
      int p1 = Integer.parseInt(s1);
      int p2 = Integer.parseInt(s2);
      int p3 = Integer.parseInt(s3);
      int p4 = Integer.parseInt(s4);
      String esValido = "";
      if (p1 > 0 && p2 >= 0 && p3 >= 0 && p4 >= 0 && p1 < 256 && p2 < 256 && p3 < 256 && p4 < 256) {
         esValido = s1 + "." + s2 + "." + s3 + "." + s4;
      }

      return esValido;
   }

   public boolean okNumero(String cadena) {
      int len = cadena.length();
      boolean ok = true;
      if (cadena.equals("")) {
         return false;
      } else {
         for(int cont = 0; cont < len && ok; ++cont) {
            if (!Character.isDigit(cadena.charAt(cont))) {
               ok = false;
            }
         }

         return ok;
      }
   }

   void btnConectarMismo_actionPerformed(ActionEvent e) {
      this.direcc = "127.0.0.1";
      if (this.jTextUser.getText().compareTo("") != 0) {
         this.valido = true;
      } else {
         JOptionPane.showMessageDialog(this, "Por favor, introduzca un nombre de usuario", "Error", 0);
      }

   }

   protected void processWindowEvent(WindowEvent e) {
      if (e.getID() == 201) {
         this.cancel();
         System.exit(0);
      }

      super.processWindowEvent(e);
   }

   void cancel() {
      this.dispose();
   }

   public String getHost() {
      return this.direcc;
   }

   public String getName() {
      return this.jTextUser.getText();
   }

   public boolean esValido() {
      return this.valido;
   }
}
