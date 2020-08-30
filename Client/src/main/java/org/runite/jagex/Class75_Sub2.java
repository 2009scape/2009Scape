package org.runite.jagex;

final class Class75_Sub2 extends Class75 {

   private final int anInt2636;
   static Class33 aClass33_2637;
   static Class3_Sub2[][][] aClass3_Sub2ArrayArrayArray2638;
   static int[] anIntArray2639;
   static int[] anIntArray2642 = new int[]{1, 1, 1, 1, 4, 1, 1, 5, 6, 1, 5, 0, 7, 0, 4, 1, 7, 2, 1, 1, 6, 1, 1, 3, 6, 1, 7, 0, 0, 6, 7, 0, 1, 7, 6, 1, 1, 1, 5, 4, 3, 2, 1, 1, 0, 4, 1, 5};
   static int anInt2643;
   private int anInt2644;
   static CacheIndex aClass153_2645;
   private final int anInt2646;
   private final int anInt2647;
   static Class33 aClass33_2648;


   final void method1341(int var2, int var3) {
      try {
         int var4 = this.anInt2646 * var2 >> 12;
         int var5 = var2 * this.anInt2636 >> 12;
         int var6 = this.anInt2644 * var3 >> 12;
         int var7 = this.anInt2647 * var3 >> 12;
          Class95.method1584(this.anInt1101, var7, var4, var6, var5);
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "kc.A(" + 2 + ',' + var2 + ',' + var3 + ')');
      }
   }

   static Class3_Sub28_Sub16 method1344(int var0, CacheIndex var1, int var2) {
      try {
         if(var0 < 29) {
            aClass3_Sub2ArrayArrayArray2638 = (Class3_Sub2[][][])((Class3_Sub2[][][])null);
         }

         return !Class140_Sub7.method2029((byte)-121, var1, var2)?null:Class43.method1062(99);
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "kc.C(" + var0 + ',' + (var1 != null?"{...}":"null") + ',' + var2 + ')');
      }
   }

   final void method1337(int var1, boolean var2, int var3) {
      try {
         if(!var2) {
            this.method1337(-7, false, 66);
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "kc.E(" + var1 + ',' + ',' + var3 + ')');
      }
   }

   public static void method1345(int var0) {
      try {
         if(var0 >= -38) {
            method1344(93, (CacheIndex)null, -70);
         }

         anIntArray2642 = null;
         aClass3_Sub2ArrayArrayArray2638 = (Class3_Sub2[][][])null;
         aClass33_2648 = null;
         aClass33_2637 = null;
         aClass153_2645 = null;
         anIntArray2639 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "kc.B(" + var0 + ')');
      }
   }

   final void method1335(int var1, int var2, int var3) {
      try {
         int var4 = var2 * this.anInt2646 >> 12;
         if(var3 != 4898) {
            this.anInt2644 = -39;
         }

         int var7 = this.anInt2647 * var1 >> 12;
         int var6 = this.anInt2644 * var1 >> 12;
         int var5 = this.anInt2636 * var2 >> 12;
         Class3_Sub13_Sub12.method223(this.anInt1106, var4, var6, var7, this.anInt1104, this.anInt1101, var5);
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "kc.D(" + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   Class75_Sub2(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      super(var5, var6, var7);

      try {
         this.anInt2647 = var4;
         this.anInt2646 = var1;
         this.anInt2644 = var2;
         this.anInt2636 = var3;
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "kc.<init>(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
      }
   }

}
