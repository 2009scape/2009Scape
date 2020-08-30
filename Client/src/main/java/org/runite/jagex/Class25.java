package org.runite.jagex;

final class Class25 {

   int anInt478;
   GameObject aClass140_479;
   static Class47 aClass47_480 = new Class47(16);
   int anInt481;
   int anInt482;
   int anInt483;
   int anInt484;
   
   static int anInt486 = 5063219;
   int anInt487;
   static boolean aBoolean488 = true;
   int anInt489;
   int anInt490;
   static int anInt491 = 0;
   int anInt493;
   int anInt495;
   int anInt496;
   static int anInt497;
   long aLong498 = 0L;
   static int[][] anIntArrayArray499 = new int[][]{new int[0], {128, 0, 128, 128, 0, 128}, {0, 0, 128, 0, 128, 128, 64, 128}, {0, 128, 0, 0, 128, 0, 64, 128}, {0, 0, 64, 128, 0, 128}, {128, 128, 64, 128, 128, 0}, {64, 0, 128, 0, 128, 128, 64, 128}, {128, 0, 128, 128, 0, 128, 0, 64, 64, 0}, {0, 0, 64, 0, 0, 64}, {0, 0, 128, 0, 128, 128, 64, 96, 32, 64}, {0, 128, 0, 0, 32, 64, 64, 96, 128, 128}, {0, 128, 0, 0, 32, 32, 96, 32, 128, 0, 128, 128}};

   static void method953() {
      try {
         Class114.aClass93_1569.method1522(-13508 ^ 13501, 50);
         Class3_Sub15.aClass93_2428.method1522(-126, 50);
         Class47.aClass93_743.method1522(-126, 50);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ec.D(" + -13508 + ',' + 50 + ')');
      }
   }

   public static void method954(int var0) {
      try {
         aClass47_480 = null;
         anIntArrayArray499 = (int[][])null;
         if(var0 != 128) {
            method954(113);
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ec.A(" + var0 + ')');
      }
   }

   static void method955(int[][] var0, boolean var1, int var2, Class3_Sub11 var3, int[] var4, int var5, int var6, int var7, boolean var8, float[][] var9, boolean var10, int var11, float[][] var12, int var13, int var14, int var15, boolean var16, int[][] var17, float[][] var18, byte var19, int var20, boolean[] var21) {
      try {
         int var22 = (var2 << 8) + (var1?255:0);
         int var24 = (!var8?0:255) + (var15 << 8);
         int[] var26 = new int[var4.length / var11];
         int var25 = (var10?255:0) + (var14 << 8);
         int var23 = (var20 << 8) + (!var16?0:255);

         for(int var27 = 0; var26.length > var27; ++var27) {
            int var28 = var4[var27 + var27];
            int[][] var30 = null != var0 && var21 != null && var21[var27]?var0:var17;
            int var29 = var4[var27 + var27 - -1];
            var26[var27] = Class121.method1734(var25, (float)var13, var22, var23, var0, var30, var7, var18, var24, (byte)-116, var19, false, var3, var9, var5, var28, var12, var29);
         }

         var3.method150(var6, var7, var5, var26, (int[])null, false);
      } catch (RuntimeException var31) {
         throw Class44.clientError(var31, "ec.F(" + (var0 != null?"{...}":"null") + ',' + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + (var4 != null?"{...}":"null") + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + (var9 != null?"{...}":"null") + ',' + var10 + ',' + var11 + ',' + (var12 != null?"{...}":"null") + ',' + var13 + ',' + var14 + ',' + var15 + ',' + var16 + ',' + (var17 != null?"{...}":"null") + ',' + (var18 != null?"{...}":"null") + ',' + var19 + ',' + var20 + ',' + (var21 != null?"{...}":"null") + ')');
      }
   }

   static void method956(Object[] var0, int var1, int[] var2, int var3, int var4) {
      try {
         if(var4 < var1) {
            int var5 = (var4 + var1) / 2;
            int var7 = var2[var5];
            int var6 = var4;
            var2[var5] = var2[var1];
            var2[var1] = var7;
            Object var8 = var0[var5];
            var0[var5] = var0[var1];
            var0[var1] = var8;

            for(int var9 = var4; var1 > var9; ++var9) {
               if(var2[var9] < (var9 & 1) + var7) {
                  int var10 = var2[var9];
                  var2[var9] = var2[var6];
                  var2[var6] = var10;
                  Object var11 = var0[var9];
                  var0[var9] = var0[var6];
                  var0[var6++] = var11;
               }
            }

            var2[var1] = var2[var6];
            var2[var6] = var7;
            var0[var1] = var0[var6];
            var0[var6] = var8;
            method956(var0, var6 - 1, var2, 47, var4);
            method956(var0, var1, var2, 100, 1 + var6);
         }

         if(var3 < 16) {
            method955((int[][])((int[][])null), true, 94, (Class3_Sub11)null, (int[])null, -50, -107, -51, false, (float[][])((float[][])null), true, 73, (float[][])((float[][])null), -92, -7, -23, true, (int[][])((int[][])null), (float[][])((float[][])null), (byte)52, 41, (boolean[])null);
         }

      } catch (RuntimeException var12) {
         throw Class44.clientError(var12, "ec.G(" + (var0 != null?"{...}":"null") + ',' + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ',' + var4 + ')');
      }
   }

   static void method957(boolean var1) {
      try {
          Class3_Sub28_Sub13.aBoolean3665 = var1;
          Class47.aBoolean742 = !NPC.method1986(42);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ec.C(" + 96 + ',' + var1 + ')');
      }
   }

   static void method958(byte var0) {
      try {
         if(GameObject.aBoolean1837) {
            RSInterface var1 = Class3_Sub28_Sub16.method638(Class3_Sub30_Sub1.anInt872, RSInterface.anInt278);
            if(null != var1 && var1.anObjectArray303 != null) {
               CS2Script var3 = new CS2Script();
               var3.arguments = var1.anObjectArray303;
               var3.aClass11_2449 = var1;
               Class43.method1065(var3);
            }

            GameObject.aBoolean1837 = false;
            Class3_Sub28_Sub5.anInt3590 = -1;
            Class20.method909(-107, var1);
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "ec.E(" + var0 + ')');
      }
   }

   static void method959() {
      try {
         CS2Script.aClass93_2450.method1524();

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ec.B(" + 0 + ')');
      }
   }

}
