package org.runite.jagex;

final class Class143 {

   static Class93 aClass93_1874 = new Class93(50);
   static int loadingStage = 0;
   static RSString aClass94_1880 = RSString.createRSString(")1");


   static void method2062(int var0, int var1, int var3, int var4, int var5, int var6, int var7) {
      try {
         int var12 = Class40.method1040(Class57.anInt902, var6, Class159.anInt2020);
         int var13 = Class40.method1040(Class57.anInt902, var3, Class159.anInt2020);
         int var14 = Class40.method1040(Class3_Sub28_Sub18.anInt3765, var7, Class101.anInt1425);
         int var15 = Class40.method1040(Class3_Sub28_Sub18.anInt3765, var0, Class101.anInt1425);
         int var8 = Class40.method1040(Class57.anInt902, var4 + var6, Class159.anInt2020);
         int var9 = Class40.method1040(Class57.anInt902, -var4 + var3, Class159.anInt2020);

         int var16;
         for(var16 = var12; var8 > var16; ++var16) {
            Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var16], var14, 97, var15, var5);
         }

         for(var16 = var13; var9 < var16; --var16) {
            Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var16], var14, 94, var15, var5);
         }

         int var10 = Class40.method1040(Class3_Sub28_Sub18.anInt3765, var4 + var7, Class101.anInt1425);
         int var11 = Class40.method1040(Class3_Sub28_Sub18.anInt3765, -var4 + var0, Class101.anInt1425);

         for(var16 = var8; var9 >= var16; ++var16) {
            int[] var17 = Class38.anIntArrayArray663[var16];
            Class3_Sub13_Sub23_Sub1.method282(var17, var14, 105, var10, var5);
            Class3_Sub13_Sub23_Sub1.method282(var17, var10, 111, var11, var1);
            Class3_Sub13_Sub23_Sub1.method282(var17, var11, 109, var15, var5);
         }

      } catch (RuntimeException var18) {
         throw Class44.clientError(var18, "tl.B(" + var0 + ',' + var1 + ',' + -89 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
      }
   }

   public static void method2063(int var0) {
      try {
         aClass93_1874 = null;
         aClass94_1880 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "tl.A(" + var0 + ')');
      }
   }

}
