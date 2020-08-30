package org.runite.jagex;
import java.util.Objects;
import java.util.Random;

final class Class3_Sub13_Sub38 extends Class3_Sub13 {

   private int anInt3444 = 0;
   
   static int[] spritePalette;
   private int anInt3447 = 2000;
   static long[] aLongArray3448 = new long[1000];
   private int anInt3450 = 4096;
   private int anInt3451 = 16;
   static short[] aShortArray3453 = new short[256];
   private int anInt3454 = 0;
   static short[] aShortArray3455;
   static int[] anIntArray3456 = new int[4096];

   public static void method351(int var0) {
      try {
         aShortArray3455 = null;
         spritePalette = null;
         if(var0 == -1) {
            aShortArray3453 = null;
            aLongArray3448 = null;
            anIntArray3456 = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "vc.B(" + var0 + ')');
      }
   }

   final void method158(int var1) {
      try {
         Class8.method844((byte)-9);
         if(var1 != 16251) {
            this.anInt3454 = 107;
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "vc.P(" + var1 + ')');
      }
   }

   final void method157(int var1, RSByteBuffer var2, boolean var3) {
      try {
         if(var1 == 0) {
            this.anInt3454 = var2.getByteB();
         } else if(var1 == 1) {
            this.anInt3447 = var2.getShort();
         } else if(var1 == 2) {
            this.anInt3451 = var2.getByteB();
         } else if (3 == var1) {
             this.anInt3444 = var2.getShort();
         } else if (var1 == 4) {
             this.anInt3450 = var2.getShort();
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "vc.A(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

   public Class3_Sub13_Sub38() {
      super(0, true);
   }

   final int[] method154(int var1, byte var2) {
      try {
         int[] var3 = this.aClass114_2382.method1709(var1);
         if(this.aClass114_2382.aBoolean1580) {
            int var5 = this.anInt3450 >> 1;
            int[][] var6 = this.aClass114_2382.method1710((byte)93);
            Random var7 = new Random((long)this.anInt3454);

            for(int var8 = 0; this.anInt3447 > var8; ++var8) {
               int var9 = this.anInt3450 > 0?this.anInt3444 + -var5 + Class100.method1603((byte)-99, this.anInt3450, var7):this.anInt3444;
               int var10 = Class100.method1603((byte)-96, Class113.anInt1559, var7);
               var9 = (var9 & 4088) >> 4;
               int var11 = Class100.method1603((byte)62, Class101.anInt1427, var7);
               int var12 = var10 - -(this.anInt3451 * Class75_Sub2.anIntArray2639[var9] >> 12);
               int var13 = var11 + (Class3_Sub13_Sub17.anIntArray3212[var9] * this.anInt3451 >> 12);
               int var15 = var12 - var10;
               int var14 = -var11 + var13;
               if(var15 != 0 || var14 != 0) {
                  if(var15 < 0) {
                     var15 = -var15;
                  }

                  if(var14 < 0) {
                     var14 = -var14;
                  }

                  boolean var16 = var14 > var15;
                  int var17;
                  int var18;
                  if(var16) {
                     var17 = var10;
                     var18 = var12;
                     var12 = var13;
                     var13 = var18;
                     var10 = var11;
                     var11 = var17;
                  }

                  if(var12 < var10) {
                     var17 = var10;
                     var18 = var11;
                     var10 = var12;
                     var11 = var13;
                     var13 = var18;
                     var12 = var17;
                  }

                  var18 = -var10 + var12;
                  int var19 = var13 + -var11;
                  var17 = var11;
                  if(var19 < 0) {
                     var19 = -var19;
                  }

                  int var20 = -var18 / 2;
                  int var22 = -(Class100.method1603((byte)-18, 4096, var7) >> 2) + 1024;
                  int var23 = var11 >= var13 ?-1:1;
                  int var21 = 2048 / var18;

                  for(int var24 = var10; var12 > var24; ++var24) {
                     var20 += var19;
                     int var25 = var21 * (-var10 + var24) + var22 + 1024;
                     int var27 = var17 & Class3_Sub20.anInt2487;
                     if(0 < var20) {
                        var20 += -var18;
                        var17 += var23;
                     }

                     int var26 = RenderAnimationDefinition.anInt396 & var24;
                     if(var16) {
                        Objects.requireNonNull(var6)[var27][var26] = var25;
                     } else {
                        Objects.requireNonNull(var6)[var26][var27] = var25;
                     }
                  }
               }
            }
         }

         return var3;
      } catch (RuntimeException var28) {
         throw Class44.clientError(var28, "vc.D(" + var1 + ',' + var2 + ')');
      }
   }

}
