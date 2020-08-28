package org.runite.jagex;

final class Class134 {

   static Class61 aClass61_1758 = new Class61();
   static int anInt1759 = 0;
   static RSString aClass94_1760 = RSString.createRSString("");
   static int anInt1761 = -1;
   static int anInt1762 = 0;
   static int[][] anIntArrayArray1763 = new int[][]{{0, 128, 0, 0, 128, 0, 128, 128}, {0, 128, 0, 0, 128, 0}, {0, 0, 64, 128, 0, 128}, {128, 128, 64, 128, 128, 0}, {0, 0, 128, 0, 128, 128, 64, 128}, {0, 128, 0, 0, 128, 0, 64, 128}, {64, 128, 0, 128, 0, 0, 64, 0}, {0, 0, 64, 0, 0, 64}, {128, 0, 128, 128, 0, 128, 0, 64, 64, 0}, {0, 128, 0, 0, 32, 64, 64, 96, 128, 128}, {0, 0, 128, 0, 128, 128, 64, 96, 32, 64}, {0, 0, 128, 0, 96, 32, 32, 32}};
   static boolean aBoolean1765 = false;
   static long[] aLongArray1766 = new long[32];


   public static void method1806(int var0) {
      try {
         aClass61_1758 = null;
         if(var0 != 3846) {
            anInt1762 = 60;
         }

         anIntArrayArray1763 = (int[][])null;
         aLongArray1766 = null;
         aClass94_1760 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "sh.D(" + var0 + ')');
      }
   }

   static synchronized byte[] method1807(int var1) {
      try {
         byte[] var2;
         if(var1 == 100 && Class65.anInt984 > 0) {
            var2 = Class3_Sub13_Sub39.aByteArrayArray3461[--Class65.anInt984];
            Class3_Sub13_Sub39.aByteArrayArray3461[Class65.anInt984] = null;
            return var2;
         } else {

             if(var1 == 5000 && MouseListeningClass.anInt1927 > 0) {
               var2 = Class9.aByteArrayArray125[--MouseListeningClass.anInt1927];
               Class9.aByteArrayArray125[MouseListeningClass.anInt1927] = null;
               return var2;
            } else if(var1 == 30000 && 0 < Class3_Sub28_Sub18.anInt3766) {
               var2 = Class140_Sub3.aByteArrayArray2747[--Class3_Sub28_Sub18.anInt3766];
               Class140_Sub3.aByteArrayArray2747[Class3_Sub28_Sub18.anInt3766] = null;
               return var2;
            } else {
               return new byte[var1];
            }
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "sh.A(" + 66 + ',' + var1 + ')');
      }
   }

   static void method1808(int var0, boolean var1, int var3, boolean var4) {
      try {
         Class41.method1047(var0, var3, Class3_Sub13_Sub16.aClass44_Sub1Array3201.length - 1, var4, 0, var1);

      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "sh.C(" + var0 + ',' + var1 + ',' + (byte) 30 + ',' + var3 + ',' + var4 + ')');
      }
   }

   static void method1809(int var0, long[] var1, int var2, int var3, Object[] var4) {
      try {
         if(var2 < 85) {
            aClass61_1758 = (Class61)null;
         }

         if(var0 > var3) {
            int var6 = var3;
            int var5 = (var3 - -var0) / 2;
            long var7 = var1[var5];
            var1[var5] = var1[var0];
            var1[var0] = var7;
            Object var9 = var4[var5];
            var4[var5] = var4[var0];
            var4[var0] = var9;

            for(int var10 = var3; var0 > var10; ++var10) {
               if(var7 + (long)(1 & var10) > var1[var10]) {
                  long var11 = var1[var10];
                  var1[var10] = var1[var6];
                  var1[var6] = var11;
                  Object var13 = var4[var10];
                  var4[var10] = var4[var6];
                  var4[var6++] = var13;
               }
            }

            var1[var0] = var1[var6];
            var1[var6] = var7;
            var4[var0] = var4[var6];
            var4[var6] = var9;
            method1809(-1 + var6, var1, 107, var3, var4);
            method1809(var0, var1, 89, var6 - -1, var4);
         }

      } catch (RuntimeException var14) {
         throw Class44.clientError(var14, "sh.B(" + var0 + ',' + (var1 != null?"{...}":"null") + ',' + var2 + ',' + var3 + ',' + (var4 != null?"{...}":"null") + ')');
      }
   }

}
