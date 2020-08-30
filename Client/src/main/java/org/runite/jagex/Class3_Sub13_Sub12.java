package org.runite.jagex;
import java.util.Objects;
import java.util.zip.CRC32;

final class Class3_Sub13_Sub12 extends Class3_Sub13 {

   static CRC32 aCRC32_3143 = new CRC32();
   static RSString aClass94_3145 = RSString.createRSString(")1 ");


   static void method223(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      try {
         if(var7 == var4) {
            IOHandler.method1460(var1, var3, var6, var7, var2, var5);
         } else {

            if(Class101.anInt1425 <= var2 - var7 && var7 + var2 <= Class3_Sub28_Sub18.anInt3765 && var3 - var4 >= Class159.anInt2020 && Class57.anInt902 >= var3 + var4) {
               Class161.method2200(var6, var2, var3, var5, var7, 95, var4, var1);
            } else {
               Class3_Sub13_Sub34.method329(var7, var6, var5, var1, var3, var2, var4);
            }

         }
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "fn.C(" + true + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
      }
   }

   public Class3_Sub13_Sub12() {
      super(1, true);
   }

   static void method224(int var1, int var2, int var3, int var4, int var5) {
      try {
         GameObject.aClass109Array1831[0].method1667(var3, var4);
         GameObject.aClass109Array1831[1].method1667(var3, -16 + var5 + var4);
         int var6 = var5 * (var5 + -32) / var2;
         if(var6 < 8) {
            var6 = 8;
         }

         int var7 = var1 * (-var6 + -32 + var5) / (var2 + -var5);
         if(HDToolKit.highDetail) {
            Class22.method934(var3, 16 + var4, 16, -32 + var5, Class3_Sub23.anInt2530);
            Class22.method934(var3, 16 + var4 + var7, 16, var6, Class25.anInt486);
            Class22.method924(var3, var7 + (var4 - -16), var6, Class3_Sub13_Sub31.anInt3377);
            Class22.method924(var3 + 1, var7 + 16 + var4, var6, Class3_Sub13_Sub31.anInt3377);
            Class22.method922(var3, var7 + 16 + var4, 16, Class3_Sub13_Sub31.anInt3377);
            Class22.method922(var3, var7 + var4 + 17, 16, Class3_Sub13_Sub31.anInt3377);
            Class22.method924(15 + var3, var4 + (16 - -var7), var6, Class3_Sub2.anInt2243);
            Class22.method924(14 + var3, 17 + (var4 - -var7), -1 + var6, Class3_Sub2.anInt2243);
            Class22.method922(var3, var6 + 15 + var4 + var7, 16, Class3_Sub2.anInt2243);
            Class22.method922(var3 + 1, var4 + 14 - -var7 + var6, 15, Class3_Sub2.anInt2243);
         } else {
            Class74.method1323(var3, 16 + var4, 16, -32 + var5, Class3_Sub23.anInt2530);
            Class74.method1323(var3, var7 + (var4 - -16), 16, var6, Class25.anInt486);
            Class74.method1318(var3, var7 + var4 + 16, var6, Class3_Sub13_Sub31.anInt3377);
            Class74.method1318(var3 + 1, var7 + 16 + var4, var6, Class3_Sub13_Sub31.anInt3377);
            Class74.method1317(var3, var4 + (16 - -var7), 16, Class3_Sub13_Sub31.anInt3377);
            Class74.method1317(var3, 17 + var4 + var7, 16, Class3_Sub13_Sub31.anInt3377);
            Class74.method1318(var3 - -15, var7 + 16 + var4, var6, Class3_Sub2.anInt2243);
            Class74.method1318(14 + var3, var4 - -17 - -var7, -1 + var6, Class3_Sub2.anInt2243);
            Class74.method1317(var3, var6 + 15 + var4 + var7, 16, Class3_Sub2.anInt2243);
            Class74.method1317(1 + var3, var6 + var4 - (-14 + -var7), 15, Class3_Sub2.anInt2243);
         }

      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "fn.E(" + (byte) 120 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
      }
   }

   static void method225(RSInterface var1) {
      try {
         RSInterface var2 = Class168.method2273(var1, 123);

         int var3;
         int var4;
         if(null == var2) {
            var4 = Class140_Sub7.anInt2934;
            var3 = Class23.anInt454;
         } else {
            var4 = var2.anInt193;
            var3 = var2.anInt168;
         }

         Class3_Sub28_Sub11.method603(var4, 13987, var3, var1, false);
         Class62.method1224(var1, var4, var3);
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "fn.Q(" + 14 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   static void method226(int var0, int var1) {
      try {
         if(var0 != -1) {
            if(var1 <= 27) {
               method228((CacheIndex)null, (CacheIndex)null, true);
            }

            if(Canvas_Sub2.loadInterface(var0)) {
               RSInterface[] var2 = GameObject.aClass11ArrayArray1834[var0];

               for(int var3 = 0; var3 < var2.length; ++var3) {
                  RSInterface var4 = var2[var3];
                  if(null != var4.anObjectArray159) {
                     CS2Script var5 = new CS2Script();
                     var5.arguments = var4.anObjectArray159;
                     var5.aClass11_2449 = var4;
                     ItemDefinition.runAssembledScript(2000000, var5);
                  }
               }

            }
         }
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "fn.F(" + var0 + ',' + var1 + ')');
      }
   }

   public static void method227(boolean var0) {
      try {
         aClass94_3145 = null;
         aCRC32_3143 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "fn.O(" + var0 + ')');
      }
   }

   final int[] method154(int var1, byte var2) {
      try {
         int[] var10 = this.aClass114_2382.method1709(var1);
         if(this.aClass114_2382.aBoolean1580) {
            int[][] var4 = this.method162(var1, 0, (byte)-126);
            int[] var5 = Objects.requireNonNull(var4)[0];
            int[] var7 = var4[2];
            int[] var6 = var4[1];

            for(int var8 = 0; Class113.anInt1559 > var8; ++var8) {
               var10[var8] = (var7[var8] + var5[var8] + var6[var8]) / 3;
            }
         }

         return var10;
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "fn.D(" + var1 + ',' + var2 + ')');
      }
   }

   static int method228(CacheIndex var0, CacheIndex var1, boolean var2) {
      try {
         int var3 = 0;
         if(var0.method2144(Class96.anInt1352)) {
            ++var3;
         }

         if(var0.method2144(Class75_Sub2.anInt2643)) {
            ++var3;
         }

         if(var0.method2144(Class3_Sub13_Sub11.anInt3132)) {
            ++var3;
         }

         if(var1.method2144(Class96.anInt1352)) {
            ++var3;
         }


         if(var1.method2144(Class75_Sub2.anInt2643)) {
            ++var3;
         }

         if(var1.method2144(Class3_Sub13_Sub11.anInt3132)) {
            ++var3;
         }

         return var3;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "fn.B(" + (var0 != null?"{...}":"null") + ',' + (var1 != null?"{...}":"null") + ',' + var2 + ')');
      }
   }

}
