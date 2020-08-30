package org.runite.jagex;

final class Class10 {

   static RSString aClass94_148 = RSString.createRSString("(U(Y");
   int anInt149;
   Class3_Sub28_Sub4 aClass3_Sub28_Sub4_151;
   static CacheIndex aClass153_152;
   int[] anIntArray153;
   static int anInt154 = 0;


   static Class3_Sub28_Sub16_Sub2[] method851() {
      try {
         Class3_Sub28_Sub16_Sub2[] var1 = new Class3_Sub28_Sub16_Sub2[Class95.anInt1338];

         for(int var2 = 0; Class95.anInt1338 > var2; ++var2) {
            int var3 = Class3_Sub13_Sub6.anIntArray3076[var2] * Class140_Sub7.anIntArray2931[var2];
            byte[] var4 = Class163_Sub1.aByteArrayArray2987[var2];
            int[] var5 = new int[var3];

            for(int var6 = 0; var3 > var6; ++var6) {
               var5[var6] = Class3_Sub13_Sub38.spritePalette[Class69.bitwiseAnd(255, var4[var6])];
            }

            var1[var2] = new Class3_Sub28_Sub16_Sub2(Class3_Sub15.anInt2426, Class133.anInt1748, Class164.anIntArray2048[var2], RSByteBuffer.anIntArray2591[var2], Class140_Sub7.anIntArray2931[var2], Class3_Sub13_Sub6.anIntArray3076[var2], var5);
         }

         Class39.method1035((byte)113);
         return var1;
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "bd.B(" + true + ')');
      }
   }

   static void method852(byte var0, int var1) {
      try {
         Class3_Sub25 var2 = (Class3_Sub25)Class3_Sub2.aClass130_2220.method1780((long)var1, 0);
         if(var2 != null) {
            if(var0 != 114) {
               aClass153_152 = (CacheIndex)null;
            }

            for(int var3 = 0; var2.anIntArray2547.length > var3; ++var3) {
               var2.anIntArray2547[var3] = -1;
               var2.anIntArray2551[var3] = 0;
            }

         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "bd.C(" + var0 + ',' + var1 + ')');
      }
   }

   public static void method853(int var0) {
      try {
         aClass94_148 = null;
         if(var0 != 0) {
         }

         aClass153_152 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "bd.A(" + var0 + ')');
      }
   }

}
