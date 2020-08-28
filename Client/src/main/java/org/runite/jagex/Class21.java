package org.runite.jagex;

import org.runite.Configurations;

final class Class21 {

   static boolean aBoolean440 = false;
   static int[] maskUpdateIndexes = new int[2048];
   static RSString aClass94_442 = RSString.createRSString("Number of player models in cache:");
   static int anInt443;
   static RSString aClass94_444 = RSString.createRSString("<img=1>");


   public static void method911(int var0) {
      try {
         if(var0 != 26) {
            maskUpdateIndexes = (int[])null;
         }

         maskUpdateIndexes = null;
         aClass94_444 = null;
         aClass94_442 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "dh.B(" + var0 + ')');
      }
   }

   static void method912() {
      try {
         Class3_Sub13_Sub1.outgoingBuffer.index = 0;
         Class7.anInt2166 = -1;
         Class38_Sub1.aBoolean2615 = false;
         Class130.incomingPacketLength = 0;
         Class65.anInt987 = 0;
         Class3_Sub13_Sub34.anInt3415 = 0;
         Class3_Sub29.anInt2582 = -1;
         Class161.anInt2028 = 0;
         Class38_Sub1.anInt2617 = 0;
         Class24.anInt469 = -1;
         GraphicDefinition.incomingBuffer.index = 0;
         Class3_Sub28_Sub16.anInt3699 = 0;
         RSString.incomingOpcode = -1;

         int var1;
         for(var1 = 0; Class3_Sub13_Sub22.players.length > var1; ++var1) {
            if(null != Class3_Sub13_Sub22.players[var1]) {
               Class3_Sub13_Sub22.players[var1].anInt2772 = -1;
            }
         }

         for(var1 = 0; Class3_Sub13_Sub24.npcs.length > var1; ++var1) {
            if(Class3_Sub13_Sub24.npcs[var1] != null) {
               Class3_Sub13_Sub24.npcs[var1].anInt2772 = -1;
            }
         }

         Class3_Sub28_Sub9.method580((byte)80);
         Class133.anInt1753 = 1;
         Class117.method1719(30);

         for(var1 = 0; var1 < 100; ++var1) {
            Class3_Sub28_Sub14.aBooleanArray3674[var1] = true;
         }

         Class3_Sub13_Sub8.method204(-3);
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "dh.F(" + false + ')');
      }
   }

   static Class118 method913() {
      try {
         try {
            return (Class118)Class.forName(Configurations.PACKAGE_JAGEX + ".Class118_Sub1").newInstance();
         } catch (Throwable var2) {
            return null;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "dh.C(" + ')');
      }
   }

   static void method914(int interfaceId, int interfaceHash, int walkable) {
      try {
         Class3_Sub31 var4 = new Class3_Sub31();
         var4.anInt2603 = walkable;
         var4.anInt2602 = interfaceId;
         Class3_Sub13_Sub17.aClass130_3208.method1779(var4, (long)interfaceHash);
         Class3_Sub13_Sub13.method232(interfaceId);
         RSInterface var5 = Class7.getRSInterface((byte)122, interfaceHash);
         if(var5 == null) {
        	 System.out.println("Invalid interface opened - [window=" + (interfaceHash >> 16) + ", child=" + (interfaceHash & 0xFF) + ", id=" + interfaceId + "]");
         } else {
            Class20.method909(120, var5);
         }

         if(null != Class3_Sub13_Sub7.aClass11_3087) {
            Class20.method909(117, Class3_Sub13_Sub7.aClass11_3087);
            Class3_Sub13_Sub7.aClass11_3087 = null;
         }

         int var6 = Class3_Sub13_Sub34.anInt3415;

         int var7;
         for(var7 = 0; var6 > var7; ++var7) {
            if(Class2.method73(Class3_Sub13_Sub7.aShortArray3095[var7])) {
               Class3_Sub25.method509(var7);
            }
         }

         if(1 == Class3_Sub13_Sub34.anInt3415) {
            Class38_Sub1.aBoolean2615 = false;
            Class75.method1340(AbstractIndexedSprite.anInt1462, Class3_Sub28_Sub3.anInt3552, Class3_Sub13_Sub33.anInt3395, Class3_Sub28_Sub1.anInt3537);
         } else {
            Class75.method1340(AbstractIndexedSprite.anInt1462, Class3_Sub28_Sub3.anInt3552, Class3_Sub13_Sub33.anInt3395, Class3_Sub28_Sub1.anInt3537);
            var7 = Class168.aClass3_Sub28_Sub17_2096.method682(TextCore.HasChooseOptions);

            for(int var8 = 0; Class3_Sub13_Sub34.anInt3415 > var8; ++var8) {
               int var9 = Class168.aClass3_Sub28_Sub17_2096.method682(RSByteBuffer.method802(var8));
               if(var7 < var9) {
                  var7 = var9;
               }
            }

            Class3_Sub28_Sub3.anInt3552 = 8 + var7;
            Class3_Sub28_Sub1.anInt3537 = 15 * Class3_Sub13_Sub34.anInt3415 + (!CacheIndex.aBoolean1951?22:26);
         }

         if(var5 != null) {
            Class151_Sub1.method2104(var5, false, 55);
         }

         Class3_Sub13_Sub12.method226(interfaceId, 58);
         if(Class3_Sub28_Sub12.anInt3655 != -1) {
            Class3_Sub8.method124(6422 ^ 6509, 1, Class3_Sub28_Sub12.anInt3655);
         }

      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "dh.D(" + 6422 + ',' + interfaceId + ',' + interfaceHash + ',' + walkable + ')');
      }
   }

   static void method915(RSString var0) {
      try {
         int var2 = Class3_Sub28_Sub8.method576(var0);
         if(-1 != var2) {
            Class3_Sub28_Sub7.method565(Class119.aClass131_1624.aShortArray1727[var2], Class119.aClass131_1624.aShortArray1718[var2]);
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "dh.A(" + (var0 != null?"{...}":"null") + ',' + -1 + ')');
      }
   }

   static Class146 method916() {
      try {

         try {
            return (Class146)Class.forName(Configurations.PACKAGE_JAGEX + ".MouseWheel").newInstance();
         } catch (Throwable var2) {
            return null;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "dh.E(" + (byte) 15 + ')');
      }
   }

}
