package org.runite.jagex;

import java.util.Objects;

final class Class3_Sub23 extends Class3 {

   static int anInt2530 = 2301979;
   int anInt2531;
   int anInt2532;
   static int[] anIntArray2533;
   static int anInt2535 = -2;
   static CacheIndex aClass153_2536;
   static int anInt2537 = 0;
   static boolean[] aBooleanArray2538 = new boolean[]{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false};
   int anInt2539;
   static Class3_Sub11[][] aClass3_Sub11ArrayArray2542;


   public static void method405(boolean var0) {
      try {
         aClass153_2536 = null;
         anIntArray2533 = null;
         aClass3_Sub11ArrayArray2542 = (Class3_Sub11[][])null;
         aBooleanArray2538 = null;
         if(!var0) {
            method408(-65, (byte)-3, 110, (int[][])((int[][])null), -123, 122);
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "oj.B(" + ')');
      }
   }

   static void method406(byte var0, boolean var1, Class3_Sub28_Sub17 var2) {
      try {
         int var3;
         if(HDToolKit.highDetail || var1) {
            var3 = Class140_Sub7.anInt2934;
            int var4 = var3 * 956 / 503;
            Class40.aClass3_Sub28_Sub16_680.method639((Class23.anInt454 + -var4) / 2, 0, var4, var3);
            AnimationDefinition.aClass109_1856.method1667(-(AnimationDefinition.aClass109_1856.anInt1461 / 2) + Class23.anInt454 / 2, 18);
         }

         var2.method699(TextCore.RSLoadingPleaseWait, Class23.anInt454 / 2, Class140_Sub7.anInt2934 / 2 - 26, 16777215, -1);
         var3 = Class140_Sub7.anInt2934 / 2 + -18;
         if(HDToolKit.highDetail) {
            Class22.method927(Class23.anInt454 / 2 - 152, var3, 304, 34, 9179409);
            Class22.method927(-151 + Class23.anInt454 / 2, var3 - -1, 302, 32, 0);
            Class22.method934(Class23.anInt454 / 2 - 150, var3 + 2, Client.LoadingStageNumber * 3, 30, 9179409);
            Class22.method934(Class23.anInt454 / 2 + -150 - -(3 * Client.LoadingStageNumber), 2 + var3, 300 + -(3 * Client.LoadingStageNumber), 30, 0);
         } else {
            Class74.method1311(Class23.anInt454 / 2 + -152, var3, 304, 34, 9179409);
            Class74.method1311(-151 + Class23.anInt454 / 2, var3 + 1, 302, 32, 0);
            Class74.method1323(Class23.anInt454 / 2 + -150, var3 + 2, Client.LoadingStageNumber * 3, 30, 9179409);
            Class74.method1323(3 * Client.LoadingStageNumber + -150 + Class23.anInt454 / 2, 2 + var3, -(3 * Client.LoadingStageNumber) + 300, 30, 0);
         }
         var2.method699(Class3_Sub17.aClass94_2464, Class23.anInt454 / 2, 4 + Class140_Sub7.anInt2934 / 2, 16777215, -1);
         if(var0 < 50) {
            anIntArray2533 = (int[])null;
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "oj.D(" + var0 + ',' + var1 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   static RSString method407(int var0, boolean var1, int var2, long var3) {
      try {
         RSString var6 = Class47.method1090((byte)-99, 0);
         if(var3 < 0) {
            var3 = -var3;
            Objects.requireNonNull(var6).method1533(Class3_Sub13_Sub11.aClass94_3133);
         }

         RSString var8 = Class143.aClass94_1880;
         RSString var7 = Class14.aClass94_341;
         if(var0 == 1) {
            var8 = Class14.aClass94_341;
            var7 = Class143.aClass94_1880;
         }

         if(var0 == 2) {
            var7 = Class143.aClass94_1880;
            var8 = Class3_Sub13_Sub6.aClass94_3080;
         }

         if(var0 == 3) {
            var8 = Class14.aClass94_341;
            var7 = Class143.aClass94_1880;
         }

         RSString var10 = Class47.method1090((byte)-99, 0);

         int var11;
         for(var11 = 0; var2 > var11; ++var11) {
            Objects.requireNonNull(var10).method1533(Class72.method1298((byte)9, (int)(var3 % 10L)));
            var3 /= 10L;
         }

         var11 = 0;
         RSString var9;
         if(var3 == 0L) {
            var9 = Class3_Sub13_Sub1.aClass94_3039;
         } else {
            RSString var12;
            for(var12 = Class47.method1090((byte)-102, 0); var3 > 0L; var3 /= 10L) {
               if(var1 && var11 != 0 && var11 % 3 == 0) {
                  var12.method1533(var8);
               }

               Objects.requireNonNull(var12).method1533(Class72.method1298((byte)9, (int)(var3 % 10L)));
               ++var11;
            }

            var9 = var12;
         }

          if(Objects.requireNonNull(var10).length(2 ^ -47) > 0) {
             var10.method1533(var7);
          }

          return RenderAnimationDefinition.method903(new RSString[]{var6, Objects.requireNonNull(var9).method1544(true), var10.method1544(true)}, (byte)-66);
      } catch (RuntimeException var13) {
         throw Class44.clientError(var13, "oj.C(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + 2 + ')');
      }
   }

   static int method408(int var0, byte var1, int var2, int[][] var3, int var4, int var5) {
      try {
         int var6 = var0 * var3[1 + var4][var2] + (128 - var0) * var3[var4][var2] >> 7;
         int var7 = var3[var4][1 + var2] * (-var0 + 128) + var3[var4 - -1][var2 - -1] * var0 >> 7;
         return var6 * (128 + -var5) - -(var5 * var7) >> 7;
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "oj.A(" + var0 + ',' + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + var4 + ',' + var5 + ')');
      }
   }

}
