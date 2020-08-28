package org.runite.jagex;

final class Class101 {

   static boolean aBoolean1419 = false;
   static CacheIndex aClass153_1420;
   static Class3_Sub24_Sub4 aClass3_Sub24_Sub4_1421;
   static Class30 aClass30_1422;
   static CacheIndex aClass153_1423;
   static Class61 aClass61_1424 = new Class61();
   static int anInt1425 = 0;
   static int[] anIntArray1426;
   static int anInt1427;
   static CacheIndex aClass153_1428;
   static short[][] aShortArrayArray1429;


   static void method1607(int var0, int var1, boolean var2, int[] var3, int[] var4) {
      try {
         if(var2) {
            method1608((byte)106);
         }

         if(var1 > var0) {
            int var5 = (var1 + var0) / 2;
            int var6 = var0;
            int var7 = var3[var5];
            var3[var5] = var3[var1];
            var3[var1] = var7;
            int var8 = var4[var5];
            var4[var5] = var4[var1];
            var4[var1] = var8;

            for(int var9 = var0; var9 < var1; ++var9) {
               if((var9 & 1) + var7 < var3[var9]) {
                  int var10 = var3[var9];
                  var3[var9] = var3[var6];
                  var3[var6] = var10;
                  int var11 = var4[var9];
                  var4[var9] = var4[var6];
                  var4[var6++] = var11;
               }
            }

            var3[var1] = var3[var6];
            var3[var6] = var7;
            var4[var1] = var4[var6];
            var4[var6] = var8;
            method1607(var0, var6 - 1, var2, var3, var4);
            method1607(var6 + 1, var1, var2, var3, var4);
         }

      } catch (RuntimeException var12) {
         throw Class44.clientError(var12, "nj.C(" + var0 + ',' + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + (var4 != null?"{...}":"null") + ')');
      }
   }

   public static void method1608(byte var0) {
      try {
         aClass153_1428 = null;
         aClass61_1424 = null;
         aShortArrayArray1429 = (short[][])null;
         anIntArray1426 = null;
         aClass3_Sub24_Sub4_1421 = null;
         aClass30_1422 = null;
         aClass153_1423 = null;
         if(var0 != 110) {
            method1607(46, 78, true, (int[])null, (int[])null);
         }

         aClass153_1420 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "nj.A(" + var0 + ')');
      }
   }

   static void method1609() {
      try {
         Class2.anInterface5Array70 = null;

          Class88.method1455();
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "nj.B(" + 90 + ')');
      }
   }

}
