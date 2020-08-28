package org.runite.jagex;
import java.awt.Component;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.Mixer.Info;

final class Class155_Sub2 extends Class155 {

   private int anInt2971;
   private SourceDataLine aSourceDataLine2972;
   private boolean aBoolean2973 = false;
   private AudioFormat anAudioFormat2974;
   private byte[] aByteArray2975;


   final void method2160() {
      if(null != this.aSourceDataLine2972) {
         this.aSourceDataLine2972.close();
         this.aSourceDataLine2972 = null;
      }

   }

   final void method2164(Component var1) {
      Info[] var2 = AudioSystem.getMixerInfo();
      if(null != var2) {

         for(int var4 = 0; var2.length > var4; ++var4) {
            Info var5 = var2[var4];
            if(null != var5) {
               String var6 = var5.getName();
               if(null != var6 && var6.toLowerCase().indexOf("soundmax") >= 0) {
                  this.aBoolean2973 = true;
               }
            }
         }
      }

      this.anAudioFormat2974 = new AudioFormat((float)Class21.anInt443, 16, !RSString.aBoolean2150?1:2, true, false);
      this.aByteArray2975 = new byte[256 << (RSString.aBoolean2150?2:1)];
   }

   final void method2150(int var1) throws LineUnavailableException {
      try {
         javax.sound.sampled.DataLine.Info var2 = new javax.sound.sampled.DataLine.Info(SourceDataLine.class, this.anAudioFormat2974, var1 << (!RSString.aBoolean2150?1:2));
         this.aSourceDataLine2972 = (SourceDataLine)AudioSystem.getLine(var2);
         this.aSourceDataLine2972.open();
         this.aSourceDataLine2972.start();
         this.anInt2971 = var1;
      } catch (LineUnavailableException var3) {
         if(Class146.method2080(var1) == 1) {
            this.aSourceDataLine2972 = null;
            throw var3;
         } else {
            this.method2150(Class95.method1585((byte)76, var1));
         }
      }
   }

   final void method2151() throws LineUnavailableException {
      this.aSourceDataLine2972.flush();
      if(this.aBoolean2973) {
         this.aSourceDataLine2972.close();
         this.aSourceDataLine2972 = null;
         javax.sound.sampled.DataLine.Info var1 = new javax.sound.sampled.DataLine.Info(SourceDataLine.class, this.anAudioFormat2974, this.anInt2971 << (!RSString.aBoolean2150?1:2));
         this.aSourceDataLine2972 = (SourceDataLine)AudioSystem.getLine(var1);
         this.aSourceDataLine2972.open();
         this.aSourceDataLine2972.start();
      }

   }

   final int method2157() {
      return this.anInt2971 - (this.aSourceDataLine2972.available() >> (!RSString.aBoolean2150?1:2));
   }

   final void method2149() {
      int var1 = 256;
      if(RSString.aBoolean2150) {
         var1 <<= 1;
      }

      for(int var2 = 0; var2 < var1; ++var2) {
         int var3 = this.anIntArray1975[var2];
         if((var3 + 8388608 & -16777216) != 0) {
            var3 = 8388607 ^ var3 >> 31;
         }

         this.aByteArray2975[var2 * 2] = (byte)(var3 >> 8);
         this.aByteArray2975[var2 * 2 + 1] = (byte)(var3 >> 16);
      }

      this.aSourceDataLine2972.write(this.aByteArray2975, 0, var1 << 1);
   }
}
