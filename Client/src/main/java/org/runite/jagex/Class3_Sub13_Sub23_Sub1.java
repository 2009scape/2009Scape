package org.runite.jagex;
import org.runite.Configurations;

import java.awt.*;

final class Class3_Sub13_Sub23_Sub1 extends Class3_Sub13_Sub23 {

   static Class3_Sub28_Sub5[] aClass3_Sub28_Sub5Array4031 = new Class3_Sub28_Sub5[14];
   static int anInt4032 = 0;
   static int anInt4033;
   static int anInt4034 = -8 + (int)(17.0D * Math.random());
   static int[] anIntArray4035 = new int[]{1, 1, 0, 0, 0, 8, 0, 0, 8};
   static int anInt4037;
   static short aShort4038 = 32767;
   static int anInt4039 = 0;
   static int anInt4041 = -1;
   static int hitMarkIndex;


   final int[][] method166(int var1, int var2) {
      try {
         int[][] var3 = this.aClass97_2376.method1594((byte)-119, var2);
         if(this.aClass97_2376.aBoolean1379 && this.method279(-128)) {
            int[] var4 = var3[0];
            int var7 = var2 % this.anInt3283 * this.anInt3283;
            int[] var5 = var3[1];
            int[] var6 = var3[2];

            for(int var8 = 0; Class113.anInt1559 > var8; ++var8) {
               int var9 = this.anIntArray3284[var7 + var8 % this.anInt3280];
               var6[var8] = Class69.bitwiseAnd(255, var9) << 4;
               var5[var8] = Class69.bitwiseAnd(var9 >> 4, 4080);
               var4[var8] = Class69.bitwiseAnd(16711680, var9) >> 12;
            }
         }

         if(var1 != -1) {
            anInt4039 = -119;
         }

         return var3;
      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "kd.T(" + var1 + ',' + var2 + ')');
      }
   }

   static void method282(int[] var0, int var1, int var2, int var3, int var4) {
      try {
         --var1;
         --var3;

         for(int var6 = -7 + var3; var6 > var1; var0[var1] = var4) {
            ++var1;
            var0[var1] = var4;
            ++var1;
            var0[var1] = var4;
            ++var1;
            var0[var1] = var4;
            ++var1;
            var0[var1] = var4;
            ++var1;
            var0[var1] = var4;
            ++var1;
            var0[var1] = var4;
            ++var1;
            var0[var1] = var4;
            ++var1;
         }

         while(var3 > var1) {
            ++var1;
            var0[var1] = var4;
         }

      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "kd.B(" + "null" + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
      }
   }

   static void method283(String var0) {
      try {

         System.out.println("Bad " + var0 + ", Usage: worldid, <live/rc/wip>, <english/german>, <game0/game1>");
         System.exit(1);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "kd.O(" + (var0 != null?"{...}":"null") + ',' + (byte) 38 + ')');
      }
   }

   static void method284(float[][] var0, int[][] var1, int var2, float[][] var3, int var4, int[] var5, Class37 var6, byte var7, Class43 var8, float[][] var9, int var10) {
      try {
         int[] var11 = new int[var5.length / 2];

         int var12;
         for(var12 = 0; var11.length > var12; ++var12) {
            int var13 = var5[var12 + var12];
            int var14 = var5[var12 + var12 + 1];
            int var15;
            if(var10 == 1) {
               var15 = var13;
               var13 = var14;
               var14 = -var15 + 128;
            } else if (var10 == 2) {
               var14 = -var14 + 128;
               var13 = -var13 + 128;
            } else if (var10 == 3) {
               var15 = var13;
               var13 = 128 - var14;
               var14 = var15;
            }

            float var17;
            float var16;
            float var24;
            if(var13 == 0 && var14 == 0) {
               var16 = var3[var2][var4];
               var24 = var9[var2][var4];
               var17 = var0[var2][var4];
            } else if(var13 == 128 && var14 == 0) {
               var17 = var0[var2 - -1][var4];
               var24 = var9[1 + var2][var4];
               var16 = var3[var2 - -1][var4];
            } else if(128 == var13 && var14 == 128) {
               var16 = var3[var2 + 1][var4 + 1];
               var24 = var9[var2 + 1][var4 + 1];
               var17 = var0[var2 - -1][var4 + 1];
            } else if(var13 == 0 && 128 == var14) {
               var17 = var0[var2][1 + var4];
               var16 = var3[var2][var4 + 1];
               var24 = var9[var2][1 + var4];
            } else {
               var24 = var9[var2][var4];
               var17 = var0[var2][var4];
               float var18 = (float)var13 / 128.0F;
               var16 = var3[var2][var4];
               var16 += (-var16 + var3[1 + var2][var4]) * var18;
               var17 += var18 * (var0[1 + var2][var4] - var17);
               var24 += (-var24 + var9[var2 - -1][var4]) * var18;
               float var20 = var9[var2][1 + var4];
               var20 += (var9[var2 - -1][var4 - -1] - var20) * var18;
               float var21 = var3[var2][1 + var4];
               float var19 = (float)var14 / 128.0F;
               var24 += (-var24 + var20) * var19;
               float var22 = var0[var2][1 + var4];
               var22 += (var0[1 + var2][var4 + 1] - var22) * var18;
               var21 += (-var21 + var3[var2 + 1][1 + var4]) * var18;
               var16 += (-var16 + var21) * var19;
               var17 += (var22 - var17) * var19;
            }

            int var26 = (var2 << 7) + var13;
            int var25 = (var4 << 7) - -var14;
            int var27 = Class3_Sub23.method408(var13, (byte)-53, var4, var1, var2, var14);
            var11[var12] = var6.method1018(var8, var26, var27, var25, var24, var16, var17);
         }

         var6.method1022(var11);
      } catch (RuntimeException var23) {
         throw Class44.clientError(var23, "kd.Q(" + (var0 != null?"{...}":"null") + ',' + (var1 != null?"{...}":"null") + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + var4 + ',' + (var5 != null?"{...}":"null") + ',' + (var6 != null?"{...}":"null") + ',' + var7 + ',' + (var8 != null?"{...}":"null") + ',' + (var9 != null?"{...}":"null") + ',' + var10 + ')');
      }
   }

   static Class158 method285(int var0, int var1, Component var3) {
      try {
         try {
            Class var4 = Class.forName(Configurations.PACKAGE_JAGEX + ".Class158_Sub2");
            Class158 var8 = (Class158)var4.newInstance();
            var8.method2185(var0, var1, var3);
            return var8;
         } catch (Throwable var6) {
            Class158_Sub1 var5 = new Class158_Sub1();
            var5.method2185(var0, var1, var3);
            return var5;
         }
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "kd.C(" + var0 + ',' + var1 + ',' + true + ',' + (var3 != null?"{...}":"null") + ')');
      }
   }

   static Class3_Sub28_Sub16[] method286(int var2, CacheIndex var3) {
      try {
         //System.out.println("Class3_Sub13_Sub_23_Sub1 " + var2);
         return !Class75_Sub4.method1351(var3, 0, var2, -30901)?null:Class15.method891(~4);
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "kd.F(" + -1 + ',' + 0 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ')');
      }
   }

   public static void method287(boolean var0) {
      try {
         anIntArray4035 = null;
         aClass3_Sub28_Sub5Array4031 = null;
         if(var0) {
            method282((int[])null, -113, 119, -92, 70);
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "kd.E(" + ')');
      }
   }

}
