package org.runite.jagex;

final class Class75_Sub3 extends Class75 {

   private final int anInt2649;
   private final int anInt2650;
   private final int anInt2652;
   private final int anInt2654;
   private int anInt2655;
   static Class3_Sub28_Sub16[] aClass3_Sub28_Sub16Array2656;
   private final int anInt2657;
   static int anInt2658;
   private final int anInt2659;
   static CacheIndex aClass153_2660;
   private final int anInt2661;


   final void method1337(int var1, boolean var2, int var3) {
      try {
         int var4 = var3 * this.anInt2654 >> 12;
         int var6 = var3 * this.anInt2661 >> 12;
         int var7 = this.anInt2649 * var1 >> 12;
         int var5 = this.anInt2657 * var1 >> 12;
         int var8 = this.anInt2655 * var3 >> 12;
         int var9 = var1 * this.anInt2652 >> 12;
         int var10 = this.anInt2659 * var3 >> 12;
         int var11 = var1 * this.anInt2650 >> 12;
         Class108.method1652(var6, var10, var9, var11, var5, var4, var8, var7, this.anInt1104);
      } catch (RuntimeException var12) {
         throw Class44.clientError(var12, "re.E(" + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   static void method1346() {
      try {
         Class3_Sub13_Sub2.method174();
         Class38.method1027(5, (byte)69);
         Class3_Sub24_Sub3.method465();
         Class3_Sub24_Sub4.method474();
         Class140_Sub6.method2025();
         Class114.method1711(5, 26211 + -25956);
         Class3_Sub9.method137(5, (byte)-118);
         Class3_Sub19.method386();
         RSByteBuffer.method795();
         Class3_Sub28_Sub3.method539();
         Class3_Sub13_Sub4.method188(5, 0);
         Class166.method2260();
         Class3_Sub28_Sub10_Sub1.method594(26211 + -26090, 5);
         Class3_Sub28_Sub10_Sub1.method595();
         Class3_Sub31.method820(5, 64);
         Class25.method953();
         Class3_Sub13_Sub21.method269(-5, 5);
         Class77.method1366();
         Class3_Sub13_Sub11.aClass93_3130.method1522(-125, 5);
         Class80.aClass93_1135.method1522(26211 ^ -26142, 5);
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "re.K(" + 26211 + ')');
      }
   }

   final void method1341(int var2, int var3) {
      try {

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "re.A(" + 2 + ',' + var2 + ',' + var3 + ')');
      }
   }

   final void method1335(int var1, int var2, int var3) {
      try {
         if(var3 != 4898) {
            this.anInt2655 = -64;
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "re.D(" + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   Class75_Sub3(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10) {
      super(-1, var9, var10);

      try {
         this.anInt2657 = var2;
         this.anInt2659 = var7;
         this.anInt2655 = var5;
         this.anInt2654 = var1;
         this.anInt2650 = var8;
         this.anInt2652 = var6;
         this.anInt2649 = var4;
         this.anInt2661 = var3;
      } catch (RuntimeException var12) {
         throw Class44.clientError(var12, "re.<init>(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + var9 + ',' + var10 + ')');
      }
   }

   static Class3_Sub28_Sub16[] method1347(int var0) {
      try {
         if(var0 == -26802) {
            Class3_Sub28_Sub16[] var1 = new Class3_Sub28_Sub16[Class95.anInt1338];

            for(int var2 = 0; Class95.anInt1338 > var2; ++var2) {
               byte[] var4 = Class163_Sub1.aByteArrayArray2987[var2];
               int var3 = Class3_Sub13_Sub6.anIntArray3076[var2] * Class140_Sub7.anIntArray2931[var2];
               if(Class3_Sub13_Sub22.aBooleanArray3272[var2]) {
                  int[] var6 = new int[var3];
                  byte[] var5 = Class163_Sub3.aByteArrayArray3005[var2];

                  for(int var7 = 0; var3 > var7; ++var7) {
                     var6[var7] = Class3_Sub13_Sub29.bitwiseOr(Class3_Sub13_Sub38.spritePalette[Class69.bitwiseAnd(var4[var7], 255)], Class69.bitwiseAnd(-16777216, var5[var7] << 24));
                  }

                  if(HDToolKit.highDetail) {
                     var1[var2] = new Class3_Sub28_Sub16_Sub1_Sub1(Class3_Sub15.anInt2426, Class133.anInt1748, Class164.anIntArray2048[var2], RSByteBuffer.anIntArray2591[var2], Class140_Sub7.anIntArray2931[var2], Class3_Sub13_Sub6.anIntArray3076[var2], var6);
                  } else {
                     var1[var2] = new Class3_Sub28_Sub16_Sub2_Sub1(Class3_Sub15.anInt2426, Class133.anInt1748, Class164.anIntArray2048[var2], RSByteBuffer.anIntArray2591[var2], Class140_Sub7.anIntArray2931[var2], Class3_Sub13_Sub6.anIntArray3076[var2], var6);
                  }
               } else {
                  int[] var9 = new int[var3];

                  for(int var10 = 0; var3 > var10; ++var10) {
                     var9[var10] = Class3_Sub13_Sub38.spritePalette[Class69.bitwiseAnd(var4[var10], 255)];
                  }

                  if(HDToolKit.highDetail) {
                     var1[var2] = new Class3_Sub28_Sub16_Sub1(Class3_Sub15.anInt2426, Class133.anInt1748, Class164.anIntArray2048[var2], RSByteBuffer.anIntArray2591[var2], Class140_Sub7.anIntArray2931[var2], Class3_Sub13_Sub6.anIntArray3076[var2], var9);
                  } else {
                     var1[var2] = new Class3_Sub28_Sub16_Sub2(Class3_Sub15.anInt2426, Class133.anInt1748, Class164.anIntArray2048[var2], RSByteBuffer.anIntArray2591[var2], Class140_Sub7.anIntArray2931[var2], Class3_Sub13_Sub6.anIntArray3076[var2], var9);
                  }
               }
            }

            Class39.method1035((byte)106);
            return var1;
         } else {
            return (Class3_Sub28_Sub16[])null;
         }
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "re.B(" + var0 + ')');
      }
   }

   public static void method1348(byte var0) {
      try {
         aClass153_2660 = null;
         if(var0 != 100) {
            method1347(-79);
         }
         aClass3_Sub28_Sub16Array2656 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "re.C(" + var0 + ')');
      }
   }

}
