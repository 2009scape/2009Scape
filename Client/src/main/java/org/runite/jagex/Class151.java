package org.runite.jagex;

abstract class Class151 {

   static RSString aClass94_1932 = RSString.createRSString(")4a=");
   static RSInterface aClass11_1933;
   static float[] aFloatArray1934 = new float[]{0.073F, 0.169F, 0.24F, 1.0F};
   static RSString COMMAND_BREAK_CLIENT_CONNECTION = RSString.createRSString("::clientdrop");
   static Class8 aClass8_1936;


   public static void method2093(int var0) {
      try {
         aClass8_1936 = null;
         aClass94_1932 = null;
         aFloatArray1934 = null;
         COMMAND_BREAK_CLIENT_CONNECTION = null;
         aClass11_1933 = null;
         if(var0 != 1) {
            method2096(-83, 44, -77, 121L);
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "v.R(" + var0 + ')');
      }
   }

   abstract Class62 method2094();

   abstract void method2095(int var1);

   static boolean method2096(int var0, int var1, int var2, long var3) {
      Class3_Sub2 var5 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2];
      if(var5 == null) {
         return false;
      } else if(var5.aClass70_2234 != null && var5.aClass70_2234.aLong1048 == var3) {
         return true;
      } else if(var5.aClass19_2233 != null && var5.aClass19_2233.aLong428 == var3) {
         return true;
      } else if(var5.aClass12_2230 != null && var5.aClass12_2230.aLong328 == var3) {
         return true;
      } else {
         for(int var6 = 0; var6 < var5.anInt2223; ++var6) {
            if(var5.aClass25Array2221[var6].aLong498 == var3) {
               return true;
            }
         }

         return false;
      }
   }

   abstract int method2097(int var1, int var2);

   abstract byte[] method2098(int var1);

   static void method2099(int var1, CacheIndex var3, int var5) {
      try {
         Class101.aClass153_1423 = var3;
         Class132.anInt1741 = 0;
         Class3_Sub13_Sub39.anInt3463 = var1;
         Class3_Sub9.aBoolean2311 = false;
         Class10.anInt154 = 1;
         GraphicDefinition.anInt546 = 2;

         Class3_Sub13_Sub36.anInt3423 = var5;
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "v.Q(" + true + ',' + var1 + ',' + 0 + ',' + (var3 != null?"{...}":"null") + ',' + false + ',' + var5 + ',' + 2 + ')');
      }
   }

}
