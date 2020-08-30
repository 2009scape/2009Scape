package org.runite.jagex;

final class Class154 {

   static Class93 aClass93_1955 = new Class93(64);
   static int anInt1957;
   static int[] anIntArray1960 = new int[14];
   static Class93 aClass93_1964 = new Class93(5);
   static Class93 aClass93_1965 = new Class93(50);
   static int anInt1966 = -1;
   static CacheIndex aClass153_1967;

   public static void method2145(byte var0) {
      try {
         aClass93_1955 = null;
         if(var0 >= -41) {
            method2147(-28, 103, -37);
         }

         anIntArray1960 = null;
         aClass153_1967 = null;
         aClass93_1965 = null;
         aClass93_1964 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "vf.A(" + var0 + ')');
      }
   }

   static void method2146(int var0, int var1, int var2, int var3, GameObject var4, GameObject var5, int var6, int var7, long var8) {
      if(var4 != null || var5 != null) {
         Class70 var10 = new Class70();
         var10.aLong1048 = var8;
         var10.anInt1054 = var1 * 128 + 64;
         var10.anInt1045 = var2 * 128 + 64;
         var10.anInt1057 = var3;
         var10.aClass140_1049 = var4;
         var10.aClass140_1052 = var5;
         var10.anInt1055 = var6;
         var10.anInt1059 = var7;

         for(int var11 = var0; var11 >= 0; --var11) {
            if(Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var11][var1][var2] == null) {
               Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var11][var1][var2] = new Class3_Sub2(var11, var1, var2);
            }
         }

         Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2].aClass70_2234 = var10;
      }
   }

   static Class70 method2147(int var0, int var1, int var2) {
      Class3_Sub2 var3 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2];
      return var3 == null?null:var3.aClass70_2234;
   }

   static RSString method2148(int var0) {
      try {
         if(999999999 <= var0) {

             return Class128.aClass94_1687;
         } else {
            return Class72.method1298((byte)9, var0);
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "vf.C(" + var0 + ',' + (byte) -78 + ')');
      }
   }

}
