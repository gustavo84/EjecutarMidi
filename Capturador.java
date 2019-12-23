/*    */ import javax.sound.midi.MidiMessage;
/*    */ import javax.sound.midi.Receiver;
/*    */ import javax.sound.midi.ShortMessage;
/*    */ import javax.sound.midi.SysexMessage;
/*    */ 
/*    */ class Capturador
/*    */   implements Receiver {
/*    */   private Receiver also;
/*    */   boolean onoff;
/*    */   private ClienteUDP cli;
/*    */   
/*    */   Capturador(ClienteUDP cli, Receiver also) {
/* 13 */     this.also = also;
/* 14 */     this.cli = cli;
/*    */   }
/*    */ 
/*    */   
/* 18 */   public void setEnabled(boolean onoff) { this.onoff = onoff; }
/*    */ 
/*    */   
/*    */   public void send(MidiMessage event, long time) {
/* 22 */     this.also.send(event, time);
/*    */     
/* 24 */     if (!this.onoff)
/*    */       return; 
/* 26 */     Integer i = new Integer(0);
/* 27 */     StringBuffer output = new StringBuffer();
/* 28 */     output.append("Receivederererer a MidiEvent: " + Integer.toHexString(event.getStatus()) + " Length: " + event.getLength() + " at " + time + "\n");
/*    */ 
/*    */ 
/*    */     
/* 32 */     if (event instanceof ShortMessage)
/*    */     
/* 34 */     { switch (event.getStatus() & 0xF0) {
/*    */ 
/*    */         
/*    */         case 144:
/* 38 */           output.append("   Note On Key: " + ((ShortMessage)event).getData1() + " Velocity: " + ((ShortMessage)event).getData2());
/* 39 */           this.cli.enviar(((ShortMessage)event).getData1(), (int)time, ((ShortMessage)event).getData2(), "NOSIMULAR");
/*    */           break;
/*    */ 
/*    */ 
/*    */ 
/*    */         
/*    */         case 128:
/* 46 */           output.append("   Note Offfff  Key: " + ((ShortMessage)event).getData1() + " Velocity: " + ((ShortMessage)event).getData2());
/*    */           break;
/*    */         case 176:
/* 49 */           if (((ShortMessage)event).getData1() < 120) {
/* 50 */             output.append("   Controller No.: " + ((ShortMessage)event).getData1() + " Value: " + ((ShortMessage)event).getData2()); break;
/*    */           } 
/* 52 */           output.append("   ChannelMode Message No.: " + ((ShortMessage)event).getData1() + " Value: " + ((ShortMessage)event).getData2()); break;
/*    */         case 224:
/* 54 */           output.append("   Pitch lsb: " + ((ShortMessage)event).getData1() + " msb: " + ((ShortMessage)event).getData2()); break;
/*    */         case 192:
/* 56 */           output.append("   Program Change No: " + ((ShortMessage)event).getData1() + " Just for Test: " + ((ShortMessage)event).getData2()); break;
/*    */         case 208:
/* 58 */           output.append("   Channel Aftertouch Pressure: " + ((ShortMessage)event).getData1() + " Just for Test: " + ((ShortMessage)event).getData2());
/*    */           break;
/*    */       } 
/*    */        }
/* 62 */     else if (event instanceof SysexMessage)
/*    */     
/* 64 */     { output.append("   SysexMessage: " + (event.getStatus() - 256));
/* 65 */       byte[] data = ((SysexMessage)event).getData();
/* 66 */       for (int x = 0; x < data.length; ) { output.append(" " + Integer.toHexString(data[x])); x++; }
/*    */        }
/* 68 */     else { output.append("   MetaEvent"); }
/*    */     
/* 70 */     System.out.println(output.toString());
/*    */   }
/*    */   
/*    */   public void close() {}
/*    */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarMidi.jar!/Capturador.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */