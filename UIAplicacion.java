import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.Map.Entry;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UIAplicacion extends JFrame implements WindowListener {
   private static final long serialVersionUID = 1L;
   JPanel pContenedor = new JPanel();
   String direcc = "";
   CalcularTRespuesta cal;
   private JPanel jPanel1;
   private JLabel jLabel1;
   private JLabel jLabel2;
   private JComboBox jComboBoxClienteIn;
   private JLabel jLabel3;
   private JComboBox jComboBoxServer;
   private JButton jButtonServerStart;
   private ServidorUDP serUDP;
   private JScrollPane JScollInstrumentos;
   boolean valido = false;
   private Vector registrar2;
   private WireOutput outputServer;
   private PanelTeclado midiSynth;
   private ClienteUDP cli;
   DefaultTableModel dtm;
   JScrollPane scrollPane;
   JFrame f;
   HashMap mapTiempo;
   JTable table;
   private UIAplicacion ins;
   private ClienteTCP clienteTCP;
   private HashMap mapRep;
   private ListenerServidor listenerAct;

   public UIAplicacion(ClienteTCP z) throws Exception {
      this.clienteTCP = z;
      this.ins = this;
      System.out.println("pasaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
      this.cli = new ClienteUDP();

      try {
         this.outputServer = new WireOutput();
      } catch (RuntimeException var4) {
         System.out.println(var4.toString());
      }

      this.serUDP = new ServidorUDP(this.outputServer);
      this.serUDP.start();

      try {
         this.jbInit();
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   private void jbInit() throws Exception {
      this.setBackground(Color.lightGray);
      this.setSize(new Dimension(800, 500));
      this.setTitle("Ventana Conexion");
      this.pContenedor.setLayout((LayoutManager)null);
      this.getContentPane().add(this.pContenedor);
      this.pContenedor.setPreferredSize(new Dimension(743, 417));
      this.jButtonServerStart = new JButton();
      this.pContenedor.add(this.jButtonServerStart);
      this.jButtonServerStart.setText("Parar");
      this.jButtonServerStart.setBounds(441, 168, 97, 21);
      this.jButtonServerStart.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (UIAplicacion.this.jButtonServerStart.getText().compareTo("Parar") == 0) {
               UIAplicacion.this.outputServer.close();
               UIAplicacion.this.jButtonServerStart.setText("Escuchar");
               System.out.println("entra en parar");
            } else if (UIAplicacion.this.jButtonServerStart.getText().compareTo("Escuchar") == 0) {
               try {
                  UIAplicacion.this.outputServer.open();
                  UIAplicacion.this.jButtonServerStart.setText("Parar");
                  System.out.println("entra en escuchar");
               } catch (MidiUnavailableException var3) {
                  var3.printStackTrace();
               }
            }

            System.out.println("Started the output");
         }
      });
      ComboBoxModel jComboBoxClienteInModel = new DefaultComboBoxModel(new String[0]);
      this.jComboBoxServer = new JComboBox();
      this.pContenedor.add(this.jComboBoxServer);
      this.jComboBoxServer.setModel(jComboBoxClienteInModel);
      this.jComboBoxServer.setBounds(198, 168, 201, 21);
      this.jComboBoxServer.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent evt) {
            if (evt.getStateChange() == 1) {
               int i = ((JComboBox)evt.getSource()).getSelectedIndex();
               System.out.println("Setting MIDI output port to " + (i - 1));

               try {
                  UIAplicacion.this.outputServer.setport(i - 1);
                  System.out.println("ennnnnnnnntraaaaaaaaaaaaaaaaaaaaaaaaaa" + i);
                  if (i == 0) {
                     UIAplicacion.this.jButtonServerStart.setEnabled(false);
                     UIAplicacion.this.outputServer.close();
                  } else {
                     System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
                     UIAplicacion.this.jButtonServerStart.setEnabled(true);
                     UIAplicacion.this.outputServer.open();
                  }
               } catch (Exception var4) {
                  System.out.println(var4.getMessage());
               }

            }
         }
      });
      this.jComboBoxServer.addItem("None");

      try {
         System.out.println("asddfadsfasdfasdfa" + this.outputServer.getMaxReceivers());

         for(int i = 0; i < this.outputServer.getMaxReceivers(); ++i) {
            this.jComboBoxServer.addItem(this.outputServer.getDeviceName(i));
         }
      } catch (Exception var4) {
         System.out.println(var4.getMessage());
      }

      this.jLabel3 = new JLabel();
      this.pContenedor.add(this.jLabel3);
      this.jLabel3.setText("Seleccione el dispositivo para reproducir la información");
      this.jLabel3.setBounds(198, 132, 363, 14);
      jComboBoxClienteInModel = new DefaultComboBoxModel(new String[0]);
      this.jComboBoxClienteIn = new JComboBox();
      this.pContenedor.add(this.jComboBoxClienteIn);
      this.jComboBoxClienteIn.setModel(jComboBoxClienteInModel);
      this.jComboBoxClienteIn.setBounds(198, 44, 201, 21);
      this.jComboBoxClienteIn.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent evt) {
            if (evt.getStateChange() == 1) {
               int i = ((JComboBox)evt.getSource()).getSelectedIndex();
               if (i == 1) {
                  UIAplicacion.this.midiSynth = new PanelTeclado(UIAplicacion.this.cli);
                  UIAplicacion.this.midiSynth.setEnviar(true);
                  UIAplicacion.this.midiSynth.open();
                  UIAplicacion.this.midiSynth.show();
                  UIAplicacion.this.f = new JFrame("Sintetizador midi");
                  UIAplicacion.this.f.addWindowListener(new WindowAdapter() {
                     public void windowClosing(WindowEvent e) {
                        System.exit(0);
                     }
                  });
                  UIAplicacion.this.f.getContentPane().add("Center", UIAplicacion.this.midiSynth);
                  UIAplicacion.this.f.pack();
                  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                  int w = 760;
                  int h = 170;
                  UIAplicacion.this.f.setLocation(UIAplicacion.this.getX(), UIAplicacion.this.getY() + UIAplicacion.this.getSize().height);
                  UIAplicacion.this.f.setDefaultCloseOperation(0);
                  UIAplicacion.this.f.setSize(w, h);
                  UIAplicacion.this.f.setVisible(true);
               }

            }
         }
      });
      this.jComboBoxClienteIn.addItem("None");

      try {
         this.jComboBoxClienteIn.addItem("Teclado simulado");
      } catch (Exception var3) {
         System.out.println(var3.getMessage());
      }

      this.jLabel2 = new JLabel();
      this.pContenedor.add(this.jLabel2);
      this.jLabel2.setText("Seleccione el dispositivo por el que quiere capturar la información");
      this.jLabel2.setBounds(198, 12, 384, 14);
      this.jLabel1 = new JLabel();
      this.pContenedor.add(this.jLabel1);
      this.jLabel1.setText("Clientes conectados");
      this.jLabel1.setBounds(8, 12, 165, 14);
      this.jPanel1 = new PanelInstrumentos(this.cli, this.outputServer);
      this.pContenedor.add(this.jPanel1);
      this.jPanel1.setBounds(0, 300, 800, 170);
      this.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            UIAplicacion.this.desconectarServidorCliente();
         }
      });
   }

   public void windowActivated(WindowEvent arg0) {
   }

   public void windowClosed(WindowEvent arg0) {
   }

   public void windowClosing(WindowEvent arg0) {
      System.exit(0);
   }

   public void windowDeactivated(WindowEvent arg0) {
   }

   public void windowDeiconified(WindowEvent arg0) {
   }

   public void windowIconified(WindowEvent arg0) {
   }

   public void windowOpened(WindowEvent arg0) {
   }

   public void actualizar(HashMap mapUser, HashMap mapTime) {
      System.out.println("pasa por actualizar");
      this.cli.actualizarUsuarios(mapUser);
      this.cal = new CalcularTRespuesta(this, mapUser, mapTime);
      this.cal.start();
   }

   public void actualizar2(HashMap mapTime) {
      this.mapTiempo = mapTime;
      this.actualizar();
   }

   public void actualizar() {
      System.out.println("entra en actualizar");
      this.dtm = new DefaultTableModel();
      Iterator it = this.mapTiempo.entrySet().iterator();
      this.dtm.addColumn("Usuario");
      this.dtm.addColumn("T. Respuesta");

      while(it.hasNext()) {
         Object[] data = new Object[2];
         System.out.println("entra en actualizar2");
         Entry e = (Entry)it.next();
         System.out.println("entra aqi" + e.getKey() + " " + e.getValue());
         data[0] = e.getKey();
         data[1] = e.getValue();
         this.dtm.addRow(data);
      }

      this.table = new JTable(this.dtm);
      this.table.setPreferredScrollableViewportSize(new Dimension(134, 221));
      this.scrollPane = new JScrollPane(this.table);
      this.scrollPane.setBounds(12, 38, 161, 241);
      this.scrollPane.repaint();
      this.pContenedor.add(this.scrollPane);
   }

   public void desconectarCliente() {
      this.clienteTCP.cerrarSocket();
      String sFichero = "fichero.txt";
      new File(sFichero);
      this.cli.desconectar();
      this.cal.desconectar();
      System.exit(0);
   }

   public void desconectarServidorCliente() {
      this.clienteTCP.desconectar();
      this.cli.desconectar();
      this.cal.desconectar();
      System.exit(0);
   }

   public void pasarInstancia(ListenerServidor listenerAct) {
      this.listenerAct = listenerAct;
   }
}
