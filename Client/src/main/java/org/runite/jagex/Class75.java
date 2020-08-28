package org.runite.jagex;

abstract class Class75 {

   int anInt1101;
   static long aLong1102 = 0L;
   static Class3_Sub28_Sub5[] aClass3_Sub28_Sub5Array1103 = new Class3_Sub28_Sub5[14];
   int anInt1104;
   static int anInt1105;
   int anInt1106;
   static int[] anIntArray1107 = new int[]{0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3};
   static int anInt1108 = 0;
   static int anInt1109 = 0;


   public static void method1334(byte var0) {
      try {
         anIntArray1107 = null;
         aClass3_Sub28_Sub5Array1103 = null;
         if(var0 != -115) {
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "kf.J(" + var0 + ')');
      }
   }

   abstract void method1335(int var1, int var2, int var3);

   static Class25 method1336(int var0, int var1, int var2) {
      Class3_Sub2 var3 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2];
      if (var3 != null) {
         for (int var4 = 0; var4 < var3.anInt2223; ++var4) {
            Class25 var5 = var3.aClass25Array2221[var4];
            if ((var5.aLong498 >> 29 & 3L) == 2L && var5.anInt483 == var1 && var5.anInt478 == var2) {
               return var5;
            }
         }

      }
      return null;
   }

   Class75(int var1, int var2, int var3) {
      try {
         this.anInt1104 = var2;
         this.anInt1106 = var3;
         this.anInt1101 = var1;
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "kf.<init>(" + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   abstract void method1337(int var1, boolean var2, int var3);

   static int method1338(int var0, int var1) {
      try {
         if(var1 <= 13) {
            anIntArray1107 = (int[])null;
         }

         return 255 & var0;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "kf.H(" + var0 + ',' + var1 + ')');
      }
   }

   static void method1339(RSString var0) {
      try {
         for(Class3_Sub28_Sub3 var2 = (Class3_Sub28_Sub3)Class134.aClass61_1758.method1222(); var2 != null; var2 = (Class3_Sub28_Sub3)Class134.aClass61_1758.method1221()) {
            if(var2.aClass94_3561.method1528(var0)) {
               RSByteBuffer.aClass3_Sub28_Sub3_2600 = var2;
               return;
            }
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "kf.G(" + (var0 != null?"{...}":"null") + ',' + 0 + ')');
      }
   }

   static void method1340(int var0, int var1, int var3, int var4) {
      try {

         for(int var5 = 0; var5 < Class3_Sub28_Sub3.anInt3557; ++var5) {
            if(var0 < Class3_Sub28_Sub18.anIntArray3768[var5] + Class155.anIntArray1969[var5] && Class155.anIntArray1969[var5] < var1 + var0 && Class140_Sub4.anIntArray2794[var5] + Player.anIntArray3954[var5] > var3 && Player.anIntArray3954[var5] < var3 - -var4) {
               Class3_Sub28_Sub14.aBooleanArray3674[var5] = true;
            }
         }

      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "kf.I(" + var0 + ',' + var1 + ',' + (byte) -40 + ',' + var3 + ',' + var4 + ')');
      }
   }

   abstract void method1341(int var2, int var3);

}
