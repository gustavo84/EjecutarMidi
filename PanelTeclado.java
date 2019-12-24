import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PanelTeclado extends JPanel implements ControlContext {
   final int PROGRAM = 192;
   final int NOTEON = 144;
   final int NOTEOFF = 128;
   final int SUSTAIN = 64;
   final int REVERB = 91;
   final int ON = 0;
   final int OFF = 1;
   final Color jfcBlue = new Color(204, 204, 255);
   final Color pink = new Color(255, 175, 175);
   Sequencer sequencer;
   Sequence sequence;
   Synthesizer synthesizer;
   Instrument[] instruments;
   PanelTeclado.ChannelData[] channels;
   PanelTeclado.ChannelData cc;
   JCheckBox mouseOverCB = new JCheckBox("mouseOver", true);
   JSlider veloS;
   JSlider presS;
   JSlider bendS;
   JSlider revbS;
   JCheckBox soloCB;
   JCheckBox monoCB;
   JCheckBox muteCB;
   JCheckBox sustCB;
   Vector keys = new Vector();
   Vector whiteKeys = new Vector();
   JTable table;
   PanelTeclado.Piano piano;
   boolean record;
   Track track;
   long startTime;
   private Receiver also;
   private ClienteUDP cli;
   private boolean enviar;

   public PanelTeclado(ClienteUDP cli) {
      this.cli = cli;
      this.setLayout(new BorderLayout());
      this.record = true;
      JPanel p = new JPanel();
      p.setLayout(new BoxLayout(p, 1));
      EmptyBorder eb = new EmptyBorder(5, 5, 5, 5);
      BevelBorder bb = new BevelBorder(1);
      CompoundBorder cb = new CompoundBorder(eb, bb);
      p.setBorder(new CompoundBorder(cb, eb));
      JPanel pp = new JPanel(new BorderLayout());
      pp.setBorder(new EmptyBorder(10, 20, 10, 5));
      pp.add(this.piano = new PanelTeclado.Piano());
      p.add(pp);
      this.add(p);
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
      if (sb != null) {
         this.instruments = this.synthesizer.getDefaultSoundbank().getInstruments();
         this.synthesizer.loadInstrument(this.instruments[0]);
      }

      MidiChannel[] midiChannels = this.synthesizer.getChannels();
      this.channels = new PanelTeclado.ChannelData[midiChannels.length];

      for(int i = 0; i < this.channels.length; ++i) {
         this.channels[i] = new PanelTeclado.ChannelData(midiChannels[i], i);
      }

      this.cc = this.channels[0];
   }

   public void close() {
      if (this.synthesizer != null) {
         this.synthesizer.close();
      }

      if (this.sequencer != null) {
         this.sequencer.close();
      }

      this.sequencer = null;
      this.synthesizer = null;
      this.instruments = null;
      this.channels = null;
   }

   public void createShortEvent(int type, int num) {
      ShortMessage message = new ShortMessage();
      System.out.println("envia");
      StringBuffer output = new StringBuffer();

      try {
         long millis = System.currentTimeMillis() - this.startTime;
         long tick = millis * (long)this.sequence.getResolution() / 500L;
         System.out.println("tipo" + type + " num" + num + "ve" + this.cc.velocity);
         message.setMessage(type, num, this.cc.velocity);
         new MidiEvent(message, tick);
         output.append("   Note On Key: ");
         System.out.println("envia" + type + this.cc.num + "nvia" + num + "cc.velocity" + this.cc.velocity + "cc.num" + this.cc.num);
         if (this.enviar) {
            this.cli.enviar(type, num, this.cc.velocity, "SI");
         }
      } catch (Exception var10) {
         var10.printStackTrace();
      }

   }

   public static void main(String[] args) {
      PanelTeclado midiSynth = new PanelTeclado((ClienteUDP)null);
      System.out.println("entra");
      midiSynth.open();
      JFrame f = new JFrame("Midi Synthesizer");
      f.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            System.exit(0);
         }
      });
      f.getContentPane().add("Center", midiSynth);
      f.pack();
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int w = 760;
      int h = 470;
      f.setLocation(screenSize.width / 2 - w / 2, screenSize.height / 2 - h / 2);
      f.setSize(w, h);
      f.setVisible(true);
   }

   public void setEnviar(boolean enviar) {
      this.enviar = enviar;
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
         PanelTeclado.this.table.setRowSelectionInterval(this.row, this.row);
         PanelTeclado.this.table.setColumnSelectionInterval(this.col, this.col);
         PanelTeclado.this.soloCB.setSelected(this.solo);
         PanelTeclado.this.monoCB.setSelected(this.mono);
         PanelTeclado.this.muteCB.setSelected(this.mute);
         JSlider[] slider = new JSlider[]{PanelTeclado.this.veloS, PanelTeclado.this.presS, PanelTeclado.this.bendS, PanelTeclado.this.revbS};
         int[] v = new int[]{this.velocity, this.pressure, this.bend, this.reverb};

         for(int i = 0; i < slider.length; ++i) {
            TitledBorder tb = (TitledBorder)slider[i].getBorder();
            String s = tb.getTitle();
            tb.setTitle(s.substring(0, s.indexOf(61) + 1) + String.valueOf(v[i]));
            slider[i].repaint();
         }

      }
   }

   class Key extends Rectangle {
      int noteState = 1;
      int kNum;
      char c;

      public Key(int x, int y, int width, int height, int num) {
         super(x, y, width, height);
         this.kNum = num;
      }

      public void teclaCaracter(char c) {
         this.c = c;
      }

      public boolean isNoteOn() {
         return this.noteState == 0;
      }

      public void on() {
         this.setNoteState(0);
         if (PanelTeclado.this.record) {
            PanelTeclado.this.createShortEvent(144, this.kNum);
         }

      }

      public void off() {
         this.setNoteState(1);
         if (PanelTeclado.this.record) {
            PanelTeclado.this.createShortEvent(128, this.kNum);
         }

      }

      public void setNoteState(int state) {
         this.noteState = state;
      }

      public boolean contains(char point) {
         return this.c == point;
      }
   }

   class Piano extends JPanel implements MouseListener, KeyListener {
      Vector blackKeys = new Vector();
      PanelTeclado.Key prevKey;
      final int kw = 16;
      final int kh = 80;

      public Piano() {
         this.setLayout(new BorderLayout());
         this.setPreferredSize(new Dimension(672, 81));
         int transpose = 24;
         char[] vec = new char[]{'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'ñ', '´', 'ç'};
         char[] vec2 = new char[]{'w', 'e', 't', 'y', 'u', 'o', 'p', '+'};
         int[] whiteIDs = new int[]{0, 2, 4, 5, 7, 9, 11};
         int i = 0;

         int x;
         int keyNum;
         for(x = 0; i < 6; ++i) {
            for(keyNum = 0; keyNum < 7; x += 16) {
               int keyNumx = i * 12 + whiteIDs[keyNum] + transpose;
               PanelTeclado.Key kx = PanelTeclado.this.new Key(x, 0, 16, 80, keyNumx);
               PanelTeclado.this.whiteKeys.add(kx);
               if (i == 3) {
                  kx.teclaCaracter(vec[keyNum]);
               }

               ++keyNum;
            }
         }

         i = 0;

         for(x = 0; i < 6; x += 16) {
            keyNum = i * 12 + transpose;
            x += 16;
            PanelTeclado.Key k = PanelTeclado.this.new Key(x - 4, 0, 8, 40, keyNum + 1);
            if (i == 3) {
               k.teclaCaracter(vec2[0]);
            }

            this.blackKeys.add(k);
            x += 16;
            k = PanelTeclado.this.new Key(x - 4, 0, 8, 40, keyNum + 3);
            if (i == 3) {
               k.teclaCaracter(vec2[1]);
            }

            this.blackKeys.add(k);
            x += 16;
            x += 16;
            k = PanelTeclado.this.new Key(x - 4, 0, 8, 40, keyNum + 6);
            if (i == 3) {
               k.teclaCaracter(vec2[2]);
            }

            this.blackKeys.add(k);
            x += 16;
            k = PanelTeclado.this.new Key(x - 4, 0, 8, 40, keyNum + 8);
            if (i == 3) {
               k.teclaCaracter(vec2[3]);
            }

            this.blackKeys.add(k);
            x += 16;
            k = PanelTeclado.this.new Key(x - 4, 0, 8, 40, keyNum + 10);
            if (i == 3) {
               k.teclaCaracter(vec2[4]);
            }

            this.blackKeys.add(k);
            ++i;
         }

         PanelTeclado.this.keys.addAll(this.blackKeys);
         PanelTeclado.this.keys.addAll(PanelTeclado.this.whiteKeys);
         this.addKeyListener(this);
         this.requestFocusInWindow();
         this.setFocusable(true);
         this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
               if (PanelTeclado.this.mouseOverCB.isSelected()) {
                  PanelTeclado.Key key = Piano.this.getKey(e.getPoint());
                  if (Piano.this.prevKey != null && Piano.this.prevKey != key) {
                     Piano.this.prevKey.off();
                  }

                  if (key != null && Piano.this.prevKey != key) {
                     key.on();
                  }

                  Piano.this.prevKey = key;
                  Piano.this.repaint();
               }

            }
         });
         this.addMouseListener(this);
      }

      public void mousePressed(MouseEvent e) {
         this.prevKey = this.getKey(e.getPoint());
         if (this.prevKey != null) {
            this.prevKey.on();
            this.repaint();
         }

      }

      public void mouseReleased(MouseEvent e) {
         if (this.prevKey != null) {
            this.prevKey.off();
            this.repaint();
         }

      }

      public void mouseExited(MouseEvent e) {
         if (this.prevKey != null) {
            this.prevKey.off();
            this.repaint();
            this.prevKey = null;
         }

      }

      public void mouseClicked(MouseEvent e) {
      }

      public void mouseEntered(MouseEvent e) {
      }

      public PanelTeclado.Key getKey(Point point) {
         for(int i = 0; i < PanelTeclado.this.keys.size(); ++i) {
            if (((PanelTeclado.Key)PanelTeclado.this.keys.get(i)).contains(point)) {
               return (PanelTeclado.Key)PanelTeclado.this.keys.get(i);
            }
         }

         return null;
      }

      public PanelTeclado.Key getKeyTeclado(char point) {
         for(int i = 0; i < PanelTeclado.this.keys.size(); ++i) {
            if (((PanelTeclado.Key)PanelTeclado.this.keys.get(i)).contains(point)) {
               return (PanelTeclado.Key)PanelTeclado.this.keys.get(i);
            }
         }

         return null;
      }

      public void paint(Graphics g) {
         Graphics2D g2 = (Graphics2D)g;
         Dimension d = this.getSize();
         g2.setBackground(this.getBackground());
         g2.clearRect(0, 0, d.width, d.height);
         g2.setColor(Color.white);
         g2.fillRect(0, 0, 672, 80);

         int i;
         PanelTeclado.Key key;
         for(i = 0; i < PanelTeclado.this.whiteKeys.size(); ++i) {
            key = (PanelTeclado.Key)PanelTeclado.this.whiteKeys.get(i);
            if (key.isNoteOn()) {
               g2.setColor(PanelTeclado.this.record ? PanelTeclado.this.pink : PanelTeclado.this.jfcBlue);
               g2.fill(key);
            }

            g2.setColor(Color.black);
            g2.draw(key);
         }

         for(i = 0; i < this.blackKeys.size(); ++i) {
            key = (PanelTeclado.Key)this.blackKeys.get(i);
            if (key.isNoteOn()) {
               g2.setColor(PanelTeclado.this.record ? PanelTeclado.this.pink : PanelTeclado.this.jfcBlue);
               g2.fill(key);
               g2.setColor(Color.black);
               g2.draw(key);
            } else {
               g2.setColor(Color.black);
               g2.fill(key);
            }
         }

      }

      public void keyPressed(KeyEvent arg0) {
         System.out.println("aerewrqer" + arg0.getKeyChar());
         this.prevKey = this.getKeyTeclado(arg0.getKeyChar());
         if (this.prevKey != null) {
            this.prevKey.on();
            this.repaint();
         }

      }

      public void keyReleased(KeyEvent arg0) {
         System.out.println("aerewrqer" + arg0.getKeyChar());
         this.prevKey = this.getKeyTeclado(arg0.getKeyChar());
         if (this.prevKey != null) {
            this.prevKey.off();
            this.repaint();
         }

      }

      public void keyTyped(KeyEvent arg0) {
         System.out.println("aerewrqer" + arg0.getKeyChar());
         this.prevKey = this.getKeyTeclado(arg0.getKeyChar());
         if (this.prevKey != null) {
            this.prevKey.on();
            this.repaint();
         }

      }
   }
}
