package org.runite.client;

final class Class75_Sub2 extends Class75 {

   private final int anInt2636;
   static Class33 aClass33_2637;
   static Class3_Sub2[][][] aClass3_Sub2ArrayArrayArray2638;
   static int[] anIntArray2639;
   private int anInt2644;
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
         throw ClientErrorException.clientError(var8, "kc.A(" + 2 + ',' + var2 + ',' + var3 + ')');
      }
   }

   static AbstractSprite method1344(CacheIndex var0, int var1) {
      try {
         return !Class140_Sub7.method2029((byte)-121, var0, var1)?null:Class43.method1062(99);
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "kc.C(" + (var0 != null?"{...}":"null") + ',' + var1 + ')');
      }
   }

   final void method1337(int var1, boolean var2, int var3) {
      try {
         if (!var2) {
            this.method1337(-7, false, 66);
         }

      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "kc.E(" + var1 + ',' + ',' + var3 + ')');
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
         throw ClientErrorException.clientError(var8, "kc.D(" + var1 + ',' + var2 + ',' + var3 + ')');
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
         throw ClientErrorException.clientError(var9, "kc.<init>(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
      }
   }

}
