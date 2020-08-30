package org.runite.jagex;

final class Class77 {

   static int anInt1111;
   static Class52 aClass52_1112 = new Class52();


   static LDIndexedSprite method1364() {
      try {
         LDIndexedSprite var1 = new LDIndexedSprite(Class3_Sub15.anInt2426, Class133.anInt1748, Class164.anIntArray2048[0], RSByteBuffer.anIntArray2591[0], Class140_Sub7.anIntArray2931[0], Class3_Sub13_Sub6.anIntArray3076[0], Class163_Sub1.aByteArrayArray2987[0], Class3_Sub13_Sub38.spritePalette);

         Class39.method1035((byte)127);
         return var1;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "kh.B(" + (byte) 82 + ')');
      }
   }

   public static void method1365(int var0) {
      try {
         aClass52_1112 = null;
         if(var0 <= 96) {
            anInt1111 = 55;
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "kh.E(" + var0 + ')');
      }
   }

   static void method1366() {
      try {

          Class158_Sub1.aClass93_2982.method1522(104 ^ -22, 5);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "kh.C(" + 104 + ',' + 5 + ')');
      }
   }

   static void method1367(int var0, int var1, int var2, byte var3, int var4, int var5) {
      try {
         Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var2++], var5, 92, var1, var0);
         Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var4--], var5, 97, var1, var0);
         if(var3 >= 23) {
            for(int var6 = var2; var4 >= var6; ++var6) {
               int[] var7 = Class38.anIntArrayArray663[var6];
               var7[var5] = var7[var1] = var0;
            }

         }
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "kh.A(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
      }
   }

   static void method1368() {
      try {
         Class3_Sub13_Sub1.outgoingBuffer.putOpcode(104);
         Class3_Sub13_Sub1.outgoingBuffer.putLong(0L, -2037491440);
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "kh.D(" + -90 + ')');
      }
   }

}
