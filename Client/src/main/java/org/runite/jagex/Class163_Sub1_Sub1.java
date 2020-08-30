package org.runite.jagex;
import java.awt.Component;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

final class Class163_Sub1_Sub1 extends Class163_Sub1 {

   static byte[] aByteArray4005 = new byte[]{(byte)95, (byte)97, (byte)98, (byte)99, (byte)100, (byte)101, (byte)102, (byte)103, (byte)104, (byte)105, (byte)106, (byte)107, (byte)108, (byte)109, (byte)110, (byte)111, (byte)112, (byte)113, (byte)114, (byte)115, (byte)116, (byte)117, (byte)118, (byte)119, (byte)120, (byte)121, (byte)122, (byte)48, (byte)49, (byte)50, (byte)51, (byte)52, (byte)53, (byte)54, (byte)55, (byte)56, (byte)57};
   static int anInt4006;
   static RSString aClass94_4007 = RSString.createRSString(":");
   static boolean[] aBooleanArray4008 = new boolean[100];
   static int[] anIntArray4009 = new int[5];
   static int[][] anIntArrayArray4010 = new int[104][104];

   public static void method2213(byte var0) {
      try {
         anIntArray4009 = null;
         aByteArray4005 = null;
         aBooleanArray4008 = null;
         if(var0 != 104) {
            aClass94_4007 = (RSString)null;
         }

         aClass94_4007 = null;
         anIntArrayArray4010 = (int[][])null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ch.F(" + var0 + ')');
      }
   }

   static void method2214() {
      try {

         if(NPC.method1986(90) || WorldListCountry.localPlane == Class140_Sub3.anInt2745) {
            if(Class58.anInt909 != WorldListCountry.localPlane && Class3_Sub19.method385(WorldListCountry.localPlane)) {
               Class58.anInt909 = WorldListCountry.localPlane;
               RSByteBuffer.method792();
            }

         } else {
            Class73.method1301(WorldListCountry.localPlane, Class3_Sub7.anInt2294, Class3_Sub28_Sub7.anInt3606, Class102.player.anIntArray2755[0], false, Class102.player.anIntArray2767[0]);
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ch.E(" + 0 + ')');
      }
   }

   static void method2215(Component var0) {
      try {
         var0.removeKeyListener(Class3_Sub13_Sub3.aClass148_3049);
         var0.removeFocusListener(Class3_Sub13_Sub3.aClass148_3049);
         Class3_Sub13.anInt2384 = -1;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ch.G(" + (var0 != null?"{...}":"null") + ',' + -9320 + ')');
      }
   }

   static void method2216() {
      try {

         if(Class3_Sub13_Sub31.anInt3375 != 0) {
            try {
               if(1500 < ++Class3_Sub2.anInt2246) {
                  if(null != Class3_Sub15.aClass89_2429) {
                     Class3_Sub15.aClass89_2429.close(14821);
                     Class3_Sub15.aClass89_2429 = null;
                  }

                  if(1 <= Class117.anInt1616) {
                     Class158.anInt2005 = -5;
                     Class3_Sub13_Sub31.anInt3375 = 0;
                     return;
                  }

                  Class3_Sub2.anInt2246 = 0;
                  ++Class117.anInt1616;
                  Class3_Sub13_Sub31.anInt3375 = 1;
                  if(Class123.anInt1658 == Class3_Sub28_Sub19.anInt3773) {
                     Class123.anInt1658 = Class53.anInt867;
                  } else {
                     Class123.anInt1658 = Class3_Sub28_Sub19.anInt3773;
                  }
               }

               if(Class3_Sub13_Sub31.anInt3375 == 1) {
                  Class3_Sub9.aClass64_2318 = Class38.aClass87_665.method1441((byte)8, ClientErrorException.worldListHost, Class123.anInt1658);
                  Class3_Sub13_Sub31.anInt3375 = 2;
               }

               int var1;
               if(Class3_Sub13_Sub31.anInt3375 == 2) {
                  if(Objects.requireNonNull(Class3_Sub9.aClass64_2318).anInt978 == 2) {
                     throw new IOException();
                  }

                  if(1 != Class3_Sub9.aClass64_2318.anInt978) {
                     return;
                  }

                  Class3_Sub15.aClass89_2429 = new IOHandler((Socket)Class3_Sub9.aClass64_2318.anObject974, Class38.aClass87_665);
                  Class3_Sub9.aClass64_2318 = null;
                  Class3_Sub15.aClass89_2429.sendBytes(Class3_Sub13_Sub1.outgoingBuffer.buffer, Class3_Sub13_Sub1.outgoingBuffer.index);
                  if(WorldListEntry.aClass155_2627 != null) {
                     WorldListEntry.aClass155_2627.method2159(83);
                  }
                  if(null != Class3_Sub21.aClass155_2491) {
                     Class3_Sub21.aClass155_2491.method2159(120);
                  }

                  var1 = Class3_Sub15.aClass89_2429.readByte(0);
                  if(WorldListEntry.aClass155_2627 != null) {
                     WorldListEntry.aClass155_2627.method2159(59);
                  }

                  if(Class3_Sub21.aClass155_2491 != null) {
                     Class3_Sub21.aClass155_2491.method2159(113);
                  }

                  if(var1 != 101) {
                     Class158.anInt2005 = var1;
                     Class3_Sub13_Sub31.anInt3375 = 0;
                     Class3_Sub15.aClass89_2429.close(14821);
                     Class3_Sub15.aClass89_2429 = null;
                     return;
                  }

                  Class3_Sub13_Sub31.anInt3375 = 3;
               }

               if(Class3_Sub13_Sub31.anInt3375 == 3) {
                  if(Class3_Sub15.aClass89_2429.availableBytes(-18358) < 2) {
                     return;
                  }

                  var1 = Class3_Sub15.aClass89_2429.readByte(0) << 8 | Class3_Sub15.aClass89_2429.readByte(0);
                  Class104.method1627(var1, (byte)-16);
                  if(CS2Script.anInt2451 == -1) {
                     Class3_Sub13_Sub31.anInt3375 = 0;
                     Class158.anInt2005 = 6;
                     Class3_Sub15.aClass89_2429.close(14821);
                     Class3_Sub15.aClass89_2429 = null;
                     return;
                  }

                  Class3_Sub13_Sub31.anInt3375 = 0;
                  Class3_Sub15.aClass89_2429.close((byte) 81 + 14740);
                  Class3_Sub15.aClass89_2429 = null;
                  Class24.method951();
                  return;
               }
            } catch (IOException var2) {
               if(null != Class3_Sub15.aClass89_2429) {
                  Class3_Sub15.aClass89_2429.close(14821);
                  Class3_Sub15.aClass89_2429 = null;
               }

               if(Class117.anInt1616 < 1) {
                  if(Class123.anInt1658 == Class3_Sub28_Sub19.anInt3773) {
                     Class123.anInt1658 = Class53.anInt867;
                  } else {
                     Class123.anInt1658 = Class3_Sub28_Sub19.anInt3773;
                  }

                  Class3_Sub13_Sub31.anInt3375 = 1;
                  Class3_Sub2.anInt2246 = 0;
                  ++Class117.anInt1616;
               } else {
                  Class158.anInt2005 = -4;
                  Class3_Sub13_Sub31.anInt3375 = 0;
               }
            }

         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ch.D(" + (byte) 81 + ')');
      }
   }

}
