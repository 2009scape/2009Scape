package org.runite.jagex;

final class Class3_Sub6 extends Class3 {


   static byte[][] aByteArrayArray2287;
   static int[] anIntArray2288 = new int[32];
   byte[] aByteArray2289;
   static int anInt2291;

   public static void method118(int var0) {
      try {
         anIntArray2288 = null;
         aByteArrayArray2287 = (byte[][])null;
         if(var0 != 2) {
            method119((float[])null);
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ea.A(" + var0 + ')');
      }
   }

   static void method119(float[] var0) {
      try {
         if(var0 == null) {
         } else {
            float[] var2 = new float[var0.length];
            Class76.method1360(var0, 91, var2, 0, var0.length);
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ea.B(" + "{...}" + ',' + 91 + ')');
      }
   }

   Class3_Sub6(byte[] var1) {
      try {
         this.aByteArray2289 = var1;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ea.<init>(" + (var1 != null?"{...}":"null") + ')');
      }
   }

   static {
      int var0 = 2;

      for(int var1 = 0; var1 < 32; ++var1) {
         anIntArray2288[var1] = -1 + var0;
         var0 += var0;
      }

      anInt2291 = 1;
   }
}
