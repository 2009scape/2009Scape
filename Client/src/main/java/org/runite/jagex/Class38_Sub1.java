package org.runite.jagex;

import org.runite.Configurations;

final class Class38_Sub1 extends Class38 {

   static int[][][] anIntArrayArrayArray2609;
   static RSString aClass94_2610 = RSString.createRSString(")1o");
   static String accRegistryIp;
   static int anInt2612;
   static Class15 aClass15_2613;
   static int anInt2614 = 0;
   static boolean aBoolean2615 = false;
   static int anInt2617 = 0;
   static int anInt2618;


   static void method1030(RSInterface var0, Class3_Sub28_Sub16 var1, int var2, int var3, int var4, int var6) {
      try {
         if(null != var1) {

             int var9 = var3 * var3 - -(var2 * var2);
            int var7 = 2047 & Class3_Sub13_Sub8.anInt3102 + GraphicDefinition.CAMERA_DIRECTION;
            int var8 = Math.max(var0.anInt168 / 2, var0.anInt193 / 2) - -10;
            if(var8 * var8 >= var9) {
               int var10 = Class51.anIntArray840[var7];
               var10 = var10 * 256 / (Class164_Sub2.anInt3020 - -256);
               int var11 = Class51.anIntArray851[var7];
               var11 = 256 * var11 / (256 + Class164_Sub2.anInt3020);
               int var12 = var10 * var2 - -(var3 * var11) >> 16;
               int var13 = var11 * var2 + -(var3 * var10) >> 16;
               if(HDToolKit.highDetail) {
                  ((Class3_Sub28_Sub16_Sub1)var1).method645(var0.anInt168 / 2 + var6 + var12 - var1.anInt3697 / 2, var0.anInt193 / 2 + var4 - (var13 + var1.anInt3706 / 2), (Class3_Sub28_Sub16_Sub1)var0.method866(false));
               } else {
                  ((Class3_Sub28_Sub16_Sub2)var1).method666(var0.anInt168 / 2 + var6 - -var12 + -(var1.anInt3697 / 2), -(var1.anInt3706 / 2) + var0.anInt193 / 2 + var4 + -var13, var0.anIntArray207, var0.anIntArray291);
               }

            }
         }
      } catch (RuntimeException var14) {
         throw Class44.clientError(var14, "em.B(" + (var0 != null ? "{...}" : "null") + ',' + "{...}" + ',' + var2 + ',' + var3 + ',' + var4 + ',' + (byte) 11 + ',' + var6 + ')');
      }
   }

   static int method1031(int var0) {
      try {

          return var0 >>> 7;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "em.C(" + var0 + ',' + 2 + ')');
      }
   }

   public static void method1032(boolean var0) {
      try {
         anIntArrayArrayArray2609 = (int[][][])null;
         accRegistryIp = Configurations.MS_IP;
         aClass94_2610 = null;
         aClass15_2613 = null;
         if(var0) {
            method1032(true);
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "em.A(" + ')');
      }
   }

}
