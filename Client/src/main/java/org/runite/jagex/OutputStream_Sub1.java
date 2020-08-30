package org.runite.jagex;
import java.io.IOException;
import java.io.OutputStream;

final class OutputStream_Sub1 extends OutputStream {

   static RSString[] aClass94Array45;
   static short aShort46 = 256;
   static boolean aBoolean47 = false;
   static int anInt48 = 2;
   static int[] anIntArray49;


   static short[] method65(short[] var1) {
      try {
         if(null == var1) {
            return null;
         } else {
            short[] var2 = new short[var1.length];
            Class76.method1361(var1, 0, var2, 0, var1.length);
            return var2;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "vg.A(" + 23032 + ',' + "{...}" + ')');
      }
   }

   static void method66(RSString var0, int var1, int var2, byte var3, int var4) {
      try {
         RSInterface var5 = Class3_Sub28_Sub16.method638(var4, var1);
         if(null != var5) {
            if(var5.anObjectArray314 != null) {
               CS2Script var6 = new CS2Script();
               var6.arguments = var5.anObjectArray314;
               var6.aClass11_2449 = var5;
               var6.aClass94_2439 = var0;
               var6.interfaceButtons = var2; // Set to 1 for jukebox/friends/all/game chat. set to non 1 for all other chat settings
               Class43.method1065(var6);
            }

            boolean var8 = true;
            if(0 < var5.anInt189) {
               var8 = Class3_Sub28_Sub19.method715(var5);
            }

            if(var8) {
               if(Client.method44(var5).method92(var2 - 1, (byte)-108)) {
                  if(1 == var2) {
                     Class3_Sub13_Sub1.outgoingBuffer.putOpcode(155);
                     Class3_Sub13_Sub1.outgoingBuffer.putInt(-120, var4);
                     Class3_Sub13_Sub1.outgoingBuffer.putShort(var1);
                  }

                  if(var3 < -7) {
                     if(var2 == 2) {
                        Class3_Sub13_Sub1.outgoingBuffer.putOpcode(196);
                        Class3_Sub13_Sub1.outgoingBuffer.putInt(-122, var4);
                        Class3_Sub13_Sub1.outgoingBuffer.putShort(var1);
                     }

                     if(var2 == 3) {
                        Class3_Sub13_Sub1.outgoingBuffer.putOpcode(124);
                        Class3_Sub13_Sub1.outgoingBuffer.putInt(-122, var4);
                        Class3_Sub13_Sub1.outgoingBuffer.putShort(var1);
                     }

                     if(var2 == 4) {
                        Class3_Sub13_Sub1.outgoingBuffer.putOpcode(199);
                        Class3_Sub13_Sub1.outgoingBuffer.putInt(-126, var4);
                        Class3_Sub13_Sub1.outgoingBuffer.putShort(var1);
                     }

                     if(var2 == 5) {
                        Class3_Sub13_Sub1.outgoingBuffer.putOpcode(234);
                        Class3_Sub13_Sub1.outgoingBuffer.putInt(-123, var4);
                        Class3_Sub13_Sub1.outgoingBuffer.putShort(var1);
                     }

                     if(6 == var2) {
                        Class3_Sub13_Sub1.outgoingBuffer.putOpcode(168);
                        Class3_Sub13_Sub1.outgoingBuffer.putInt(-120, var4);
                        Class3_Sub13_Sub1.outgoingBuffer.putShort(var1);
                     }

                     if(var2 == 7) {
                        Class3_Sub13_Sub1.outgoingBuffer.putOpcode(166);
                        Class3_Sub13_Sub1.outgoingBuffer.putInt(-123, var4);
                        Class3_Sub13_Sub1.outgoingBuffer.putShort(var1);
                     }

                     if(var2 == 8) {
                        Class3_Sub13_Sub1.outgoingBuffer.putOpcode(64);
                        Class3_Sub13_Sub1.outgoingBuffer.putInt(-127, var4);
                        Class3_Sub13_Sub1.outgoingBuffer.putShort(var1);
                     }

                     if(var2 == 9) {
                        Class3_Sub13_Sub1.outgoingBuffer.putOpcode(53);
                        Class3_Sub13_Sub1.outgoingBuffer.putInt(-123, var4);
                        Class3_Sub13_Sub1.outgoingBuffer.putShort(var1);
                     }

                     if(var2 == 10) {
                        Class3_Sub13_Sub1.outgoingBuffer.putOpcode(9);
                        Class3_Sub13_Sub1.outgoingBuffer.putInt(-125, var4);
                        Class3_Sub13_Sub1.outgoingBuffer.putShort(var1);
                     }

                  }
               }
            }
         }
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "vg.D(" + (var0 != null?"{...}":"null") + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
      }
   }

   public static void method67(boolean var0) {
      try {
         aClass94Array45 = null;
         if(!var0) {
            aBoolean47 = true;
         }

         anIntArray49 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "vg.B(" + var0 + ')');
      }
   }

   static void method68(int var0, Class140_Sub4 var2) {
      try {
         if(var2.anInt2800 <= Class44.anInt719) {
            if(var2.anInt2790 >= Class44.anInt719) {
               Class168.method2270(var2);
            } else {
               Class55.method1180((byte)-22, var2);
            }
         } else {
            Class140_Sub2.method1950(var2);
         }

         if(var2.anInt2819 < 128 || var2.anInt2829 < 128 || var2.anInt2819 >= 13184 || var2.anInt2829 >= 13184) {
            var2.anInt2771 = -1;
            var2.anInt2842 = -1;
            var2.anInt2800 = 0;
            var2.anInt2790 = 0;
            var2.anInt2819 = 128 * var2.anIntArray2767[0] - -(64 * var2.getSize());
            var2.anInt2829 = var2.anIntArray2755[0] * 128 + var2.getSize() * 64;
            var2.method1973(2279 + -2395);
         }

         if(var2 == Class102.player && (var2.anInt2819 < 1536 || var2.anInt2829 < 1536 || var2.anInt2819 >= 11776 || var2.anInt2829 >= 11776)) {
            var2.anInt2842 = -1;
            var2.anInt2800 = 0;
            var2.anInt2790 = 0;
            var2.anInt2771 = -1;
            var2.anInt2819 = var2.anIntArray2767[0] * 128 + var2.getSize() * 64;
            var2.anInt2829 = 128 * var2.anIntArray2755[0] + 64 * var2.getSize();
            var2.method1973(-98);
         }

         Class17.method904(65536, var2);
         RenderAnimationDefinition.method900(var2, -11973);
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "vg.C(" + var0 + ',' + 2279 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   public final void write(int var1) throws IOException {
      try {
         throw new IOException();
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "vg.write(" + var1 + ')');
      }
   }

}
