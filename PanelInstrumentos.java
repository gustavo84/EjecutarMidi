import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class PanelInstrumentos extends JPanel {
   final int PROGRAM = 192;
   Sequencer sequencer;
   Sequence sequence;
   String simular;
   Synthesizer synthesizer;
   Instrument[] instruments;
   PanelInstrumentos.ChannelData[] channels;
   PanelInstrumentos.ChannelData cc;
   JTable table;
   JSlider veloS;
   JSlider presS;
   JSlider bendS;
   JSlider revbS;
   private String[] names = new String[]{"Piano", "Chromatic Perc.", "Organ", "Guitar", "Bass", "Strings", "Ensemble", "Brass", "Reed", "Pipe", "Synth Lead", "Synth Pad", "Synth Effects", "Ethnic", "Percussive", "Sound Effects"};
   private int nRows = 8;
   private int nCols;
   private Receiver also;
   private ClienteUDP cli;

   public void setSimular(String simular) {
      this.simular = simular;
   }

   public void open() {
      try {
         if (this.synthesizer == null && (this.synthesizer = MidiSystem.getSynthesizer()) == null) {
            System.out.println("getSynthesizer() failed!");
            return;
         }

         this.synthesizer.open();
         this.sequencer = MidiSystem.getSequencer();
         this.sequence = new Sequence(0.0F, 10);
      } catch (Exception var4) {
         var4.printStackTrace();
         return;
      }

      Soundbank sb = this.synthesizer.getDefaultSoundbank();
      int i;
      if (sb != null) {
         this.instruments = this.synthesizer.getDefaultSoundbank().getInstruments();
         String ins = null;

         for(i = 0; i < this.instruments.length; ++i) {
            ins = ins + this.instruments[i].getName() + "-";
         }

         System.out.println("todos" + ins);
         this.synthesizer.loadInstrument(this.instruments[0]);
      }

      MidiChannel[] midiChannels = this.synthesizer.getChannels();
      this.channels = new PanelInstrumentos.ChannelData[midiChannels.length];

      for(i = 0; i < this.channels.length; ++i) {
         this.channels[i] = new PanelInstrumentos.ChannelData(midiChannels[i], i);
      }

      this.cc = this.channels[0];
      ListSelectionModel lsm = this.table.getSelectionModel();
      lsm.setSelectionInterval(0, 0);
      lsm = this.table.getColumnModel().getSelectionModel();
      lsm.setSelectionInterval(0, 0);
   }

   public PanelInstrumentos(ClienteUDP cli, WireOutput output) {
      this.nCols = this.names.length;
      this.also = this.also;
      this.cli = cli;
      this.table = new JTable();
      this.open();
      this.setLayout(new BorderLayout());
      TableModel dataModel = new AbstractTableModel() {
         public int getColumnCount() {
            return PanelInstrumentos.this.nCols;
         }

         public int getRowCount() {
            return PanelInstrumentos.this.nRows;
         }

         public Object getValueAt(int r, int c) {
            return PanelInstrumentos.this.instruments != null ? PanelInstrumentos.this.instruments[c * PanelInstrumentos.this.nRows + r].getName() : Integer.toString(c * PanelInstrumentos.this.nRows + r);
         }

         public String getColumnName(int c) {
            return PanelInstrumentos.this.names[c];
         }

         public Class getColumnClass(int c) {
            return this.getValueAt(0, c).getClass();
         }

         public boolean isCellEditable(int r, int c) {
            return false;
         }

         public void setValueAt(Object obj, int r, int c) {
         }
      };
      this.table.setModel(dataModel);
      this.table.setSelectionMode(0);
      ListSelectionModel lsm = this.table.getSelectionModel();
      lsm.addListSelectionListener(new ListSelectionListener() {
         public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel sm = (ListSelectionModel)e.getSource();
            if (!sm.isSelectionEmpty()) {
               PanelInstrumentos.this.cc.row = sm.getMinSelectionIndex();
               System.out.println("la seleccion es" + sm.getMinSelectionIndex());
            }

            PanelInstrumentos.this.enviarEventoInstrumento(192, PanelInstrumentos.this.cc.col * PanelInstrumentos.this.nRows + PanelInstrumentos.this.cc.row);
         }
      });
      lsm = this.table.getColumnModel().getSelectionModel();
      lsm.addListSelectionListener(new ListSelectionListener() {
         public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel sm = (ListSelectionModel)e.getSource();
            if (!sm.isSelectionEmpty()) {
               PanelInstrumentos.this.cc.col = sm.getMinSelectionIndex();
               System.out.println("la seleccion es" + sm.getMinSelectionIndex());
            }

            PanelInstrumentos.this.enviarEventoInstrumento(192, PanelInstrumentos.this.cc.col * PanelInstrumentos.this.nRows + PanelInstrumentos.this.cc.row);
         }
      });
      this.table.setPreferredScrollableViewportSize(new Dimension(this.nCols * 110, 200));
      this.table.setCellSelectionEnabled(true);
      this.table.setColumnSelectionAllowed(true);

      for(int i = 0; i < this.names.length; ++i) {
         TableColumn column = this.table.getColumn(this.names[i]);
         column.setPreferredWidth(110);
      }

      this.table.setAutoResizeMode(0);
      JScrollPane sp = new JScrollPane(this.table);
      sp.setVerticalScrollBarPolicy(21);
      sp.setHorizontalScrollBarPolicy(32);
      this.add(sp);
   }

   public Dimension getPreferredSize() {
      return new Dimension(800, 170);
   }

   public Dimension getMaximumSize() {
      return new Dimension(800, 170);
   }

   public void enviarEventoInstrumento(int type, int num) {
      ShortMessage message = new ShortMessage();
      System.out.println("envia");
      StringBuffer output = new StringBuffer();

      try {
         System.out.println("tipo" + type + " num" + num + "ve" + this.cc.velocity);
         message.setMessage(type, num, this.cc.velocity);
         output.append("   Note On Key: ");
         System.out.println("envia" + type + this.cc.num + "nvia" + num + "cc.velocity" + this.cc.velocity + "cc.num" + this.cc.num);
         this.cli.cambiarInstrumento(num);
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }

   class ChannelData {
      MidiChannel channel;
      boolean solo;
      boolean mono;
      boolean mute;
      boolean sustain;
      int velocity;
      int pressure;
      int bend;
      int reverb;
      int row;
      int col;
      int num;

      public ChannelData(MidiChannel channel, int num) {
         this.channel = channel;
         this.num = num;
         this.velocity = this.pressure = this.bend = this.reverb = 64;
      }

      public void setComponentStates() {
         PanelInstrumentos.this.table.setRowSelectionInterval(this.row, this.row);
         PanelInstrumentos.this.table.setColumnSelectionInterval(this.col, this.col);
         JSlider[] slider = new JSlider[]{PanelInstrumentos.this.veloS, PanelInstrumentos.this.presS, PanelInstrumentos.this.bendS, PanelInstrumentos.this.revbS};
         int[] v = new int[]{this.velocity, this.pressure, this.bend, this.reverb};

         for(int i = 0; i < slider.length; ++i) {
            TitledBorder tb = (TitledBorder)slider[i].getBorder();
            String s = tb.getTitle();
            tb.setTitle(s.substring(0, s.indexOf(61) + 1) + String.valueOf(v[i]));
            slider[i].repaint();
         }

      }
   }
}
