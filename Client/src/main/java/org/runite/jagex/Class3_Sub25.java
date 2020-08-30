package org.runite.jagex;

final class Class3_Sub25 extends Class3 {

   int[] anIntArray2547 = new int[]{-1};
   static short[] aShortArray2548;
   static int[] anIntArray2550 = new int[50];
   int[] anIntArray2551 = new int[]{0};
   static Class129 aClass129_2552;


   static void method509(int var1) {
      try {
         --Class3_Sub13_Sub34.anInt3415;
         if(Class3_Sub13_Sub34.anInt3415 != var1) {
            Class76.method1362(Class140_Sub7.aClass94Array2935, var1 + 1, Class140_Sub7.aClass94Array2935, var1, -var1 + Class3_Sub13_Sub34.anInt3415);
            Class76.method1362(Class163_Sub2_Sub1.aClass94Array4016, 1 + var1, Class163_Sub2_Sub1.aClass94Array4016, var1, Class3_Sub13_Sub34.anInt3415 - var1);
            Class76.method1358(Class114.anIntArray1578, 1 + var1, Class114.anIntArray1578, var1, -var1 + Class3_Sub13_Sub34.anInt3415);
            Class76.method1361(Class3_Sub13_Sub7.aShortArray3095, 1 + var1, Class3_Sub13_Sub7.aShortArray3095, var1, Class3_Sub13_Sub34.anInt3415 + -var1);
            Class76.method1356(Class3_Sub13_Sub22.aLongArray3271, 1 + var1, Class3_Sub13_Sub22.aLongArray3271, var1, -var1 + Class3_Sub13_Sub34.anInt3415);
            Class76.method1358(Class117.anIntArray1613, var1 + 1, Class117.anIntArray1613, var1, -var1 + Class3_Sub13_Sub34.anInt3415);
            Class76.method1358(Class27.anIntArray512, 1 + var1, Class27.anIntArray512, var1, Class3_Sub13_Sub34.anInt3415 + -var1);
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "qe.A(" + 1 + ',' + var1 + ')');
      }
   }

   public static void method510(int var0) {
      try {
         if(var0 > -73) {
            method510(-59);
         }

         aClass129_2552 = null;
         aShortArray2548 = null;
         anIntArray2550 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "qe.B(" + var0 + ')');
      }
   }

}
