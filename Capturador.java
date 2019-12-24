import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.SysexMessage;

class Capturador implements Receiver {
   private Receiver also;
   boolean onoff;
   private ClienteUDP cli;

   Capturador(ClienteUDP cli, Receiver also) {
      this.also = also;
      this.cli = cli;
   }

   public void setEnabled(boolean onoff) {
      this.onoff = onoff;
   }

   public void send(MidiMessage event, long time) {
      this.also.send(event, time);
      if (this.onoff) {
         new Integer(0);
         StringBuffer output = new StringBuffer();
         output.append("Receivederererer a MidiEvent: " + Integer.toHexString(event.getStatus()) + " Length: " + event.getLength() + " at " + time + "\n");
         if (event instanceof ShortMessage) {
            switch(event.getStatus() & 240) {
            case 128:
               output.append("   Note Offfff  Key: " + ((ShortMessage)event).getData1() + " Velocity: " + ((ShortMessage)event).getData2());
               break;
            case 144:
               output.append("   Note On Key: " + ((ShortMessage)event).getData1() + " Velocity: " + ((ShortMessage)event).getData2());
               this.cli.enviar(((ShortMessage)event).getData1(), (int)time, ((ShortMessage)event).getData2(), "NOSIMULAR");
               break;
            case 176:
               if (((ShortMessage)event).getData1() < 120) {
                  output.append("   Controller No.: " + ((ShortMessage)event).getData1() + " Value: " + ((ShortMessage)event).getData2());
               } else {
                  output.append("   ChannelMode Message No.: " + ((ShortMessage)event).getData1() + " Value: " + ((ShortMessage)event).getData2());
               }
               break;
            case 192:
               output.append("   Program Change No: " + ((ShortMessage)event).getData1() + " Just for Test: " + ((ShortMessage)event).getData2());
               break;
            case 208:
               output.append("   Channel Aftertouch Pressure: " + ((ShortMessage)event).getData1() + " Just for Test: " + ((ShortMessage)event).getData2());
               break;
            case 224:
               output.append("   Pitch lsb: " + ((ShortMessage)event).getData1() + " msb: " + ((ShortMessage)event).getData2());
            }
         } else if (event instanceof SysexMessage) {
            output.append("   SysexMessage: " + (event.getStatus() - 256));
            byte[] data = ((SysexMessage)event).getData();

            for(int x = 0; x < data.length; ++x) {
               output.append(" " + Integer.toHexString(data[x]));
            }
         } else {
            output.append("   MetaEvent");
         }

         System.out.println(output.toString());
      }
   }

   public void close() {
   }
}
