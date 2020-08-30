package org.runite.jagex;
final class Class3_Sub13_Sub32 extends Class3_Sub13 {

   static int[] anIntArray3383 = new int[5];
   private int anInt3385 = 585;
   static boolean aBoolean3387 = true;
   static volatile int anInt3389 = 0;


   static int method319(int var0, int var1, int var2) {
      try {
         if(var1 >= -99) {
            aBoolean3387 = true;
         }

         int var3 = var0 >>> 31;
         return (var0 + var3) / var2 - var3;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "sa.E(" + var0 + ',' + var1 + ',' + var2 + ')');
      }
   }

   public Class3_Sub13_Sub32() {
      super(0, true);
   }

   static void method320(int var0, int var1, int var2, byte var3, int var4) {
      try {
         if(var2 >= var4) {
            Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var1], var4, -83, var2, var0);
         } else {
            Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var1], var2, -48, var4, var0);
         }

         if(var3 > -55) {
            method320(99, 100, 74, (byte)13, 92);
         }

      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "sa.C(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
      }
   }

   final int[] method154(int var1, byte var2) {
      try {
         int[] var3 = this.aClass114_2382.method1709(var1);
         if(this.aClass114_2382.aBoolean1580) {
            int var5 = Class163_Sub3.anIntArray2999[var1];

            for(int var6 = 0; var6 < Class113.anInt1559; ++var6) {
               int var7 = Class102.anIntArray2125[var6];
               int var8;
               if(var7 > this.anInt3385 && 4096 - this.anInt3385 > var7 && var5 > 2048 + -this.anInt3385 && this.anInt3385 + 2048 > var5) {
                  var8 = 2048 - var7;
                  var8 = var8 < 0?-var8:var8;
                  var8 <<= 12;
                  var8 /= -this.anInt3385 + 2048;
                  var3[var6] = -var8 + 4096;
               } else if(-this.anInt3385 + 2048 < var7 && var7 < this.anInt3385 + 2048) {
                  var8 = var5 + -2048;
                  var8 = var8 >= 0 ?var8:-var8;
                  var8 -= this.anInt3385;
                  var8 <<= 12;
                  var3[var6] = var8 / (-this.anInt3385 + 2048);
               } else if(this.anInt3385 <= var5 && var5 <= 4096 - this.anInt3385) {
                  if(this.anInt3385 <= var7 && var7 <= 4096 - this.anInt3385) {
                     var3[var6] = 0;
                  } else {
                     var8 = -var5 + 2048;
                     var8 = var8 < 0 ?-var8:var8;
                     var8 <<= 12;
                     var8 /= 2048 - this.anInt3385;
                     var3[var6] = -var8 + 4096;
                  }
               } else {
                  var8 = var7 + -2048;
                  var8 = 0 > var8?-var8:var8;
                  var8 -= this.anInt3385;
                  var8 <<= 12;
                  var3[var6] = var8 / (-this.anInt3385 + 2048);
               }
            }
         }

         return var3;
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "sa.D(" + var1 + ',' + var2 + ')');
      }
   }

   public static void method321(int var0) {
      try {
         anIntArray3383 = null;
         if(var0 != -21136) {
            method319(-38, -96, -102);
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "sa.B(" + var0 + ')');
      }
   }

   final void method157(int var1, RSByteBuffer var2, boolean var3) {
      try {
         if(!var3) {
            anInt3389 = 99;
         }

         if(var1 == 0) {
            this.anInt3385 = var2.getShort();
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "sa.A(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

}
