package org.runite.jagex;
import java.util.Random;

abstract class Class164 {

   private final short[] aShortArray2047 = new short[512];
   static int[] anIntArray2048;
   private short[] aShortArray2049;
   static int anInt2050;
   static int anInt2051;
   static CacheIndex aClass153_2052;
   private final int anInt2054;
   static RSInterface aClass11_2055;
   private final int anInt2056;
   private final int anInt2057;
   static Model_Sub1[] aClass140_Sub5Array2058 = new Model_Sub1[4];
   private final int anInt2060;
   static RSString aClass94_2061 = RSString.createRSString("Forced tweening disabled)3");
   int anInt2062;


   final void method2230(int var1) {
      try {
         int[] var39 = new int[64];
         int[] var40 = new int[64];
         int[] var41 = new int[64];

         int var42;
         for(var42 = 0; var42 < 64; ++var42) {
            var39[var42] = (var42 << 12) / 64;
         }

         if(var1 > -68) {
            this.method2236(64);
         }

         for(var42 = 0; var42 < 64; ++var42) {
            var40[var42] = (var42 << 12) / 64;
         }

         for(var42 = 0; var42 < 64; ++var42) {
            var41[var42] = (var42 << 12) / 64;
         }

         this.method2233();

         for(int var37 = 0; var37 < 64; ++var37) {
            for(int var36 = 0; var36 < 64; ++var36) {
               for(int var35 = 0; var35 < 64; ++var35) {
                  for(int var38 = 0; var38 < this.anInt2062; ++var38) {
                     var42 = this.aShortArray2049[var38] << 12;
                     int var8 = this.anInt2057 * var42 >> 12;
                     int var7 = var42 * var41[var37] >> 12;
                     int var9 = var42 * this.anInt2056 >> 12;
                     var7 *= this.anInt2060;
                     int var10 = var42 * this.anInt2060 >> 12;
                     int var5 = var39[var35] * var42 >> 12;
                     var5 *= this.anInt2057;
                     int var11 = var5 >> 12;
                     var5 &= 4095;
                     int var15 = var7 >> 12;
                     int var20 = var5 + -4096;
                     int var12 = var11 + 1;
                     int var16 = var15 - -1;
                     int var6 = var40[var36] * var42 >> 12;
                     int var17 = Class1.anIntArray52[var5];
                     var6 *= this.anInt2056;
                     var15 &= 255;
                     var11 &= 255;
                     if(var10 <= var16) {
                        var16 = 0;
                     } else {
                        var16 &= 255;
                     }

                     int var13 = var6 >> 12;
                     short var23 = this.aShortArray2047[var15];
                     short var24 = this.aShortArray2047[var16];
                     int var14 = var13 + 1;
                     if(var9 > var14) {
                        var14 &= 255;
                     } else {
                        var14 = 0;
                     }

                     var6 &= 4095;
                     short var28 = this.aShortArray2047[var14 - -var24];
                     var7 &= 4095;
                     var13 &= 255;
                     int var19 = Class1.anIntArray52[var7];
                     short var27 = this.aShortArray2047[var13 + var24];
                     short var25 = this.aShortArray2047[var23 + var13];
                     if(var8 <= var12) {
                        var12 = 0;
                     } else {
                        var12 &= 255;
                     }

                     int var21 = -4096 + var6;
                     int var18 = Class1.anIntArray52[var6];
                     int var22 = var7 - 4096;
                     short var26 = this.aShortArray2047[var23 + var14];
                     int var29 = Class131.method1788(var5, var7, var6, this.aShortArray2047[var25 + var11], true);
                     int var30 = Class131.method1788(var20, var7, var6, this.aShortArray2047[var12 - -var25], true);
                     int var31 = var29 - -(var17 * (var30 - var29) >> 12);
                     var29 = Class131.method1788(var5, var7, var21, this.aShortArray2047[var26 + var11], true);
                     var30 = Class131.method1788(var20, var7, var21, this.aShortArray2047[var12 + var26], true);
                     int var32 = var29 + (var17 * (-var29 + var30) >> 12);
                     int var33 = ((-var31 + var32) * var18 >> 12) + var31;
                     var29 = Class131.method1788(var5, var22, var6, this.aShortArray2047[var11 + var27], true);
                     var30 = Class131.method1788(var20, var22, var6, this.aShortArray2047[var12 + var27], true);
                     var31 = (var17 * (var30 + -var29) >> 12) + var29;
                     var29 = Class131.method1788(var5, var22, var21, this.aShortArray2047[var11 - -var28], true);
                     var30 = Class131.method1788(var20, var22, var21, this.aShortArray2047[var12 + var28], true);
                     var32 = var29 + ((-var29 + var30) * var17 >> 12);
                     int var34 = var31 - -((var32 + -var31) * var18 >> 12);
                     this.method2237(((-var33 + var34) * var19 >> 12) + var33, var38);
                  }

                  this.method2231((byte)-92);
               }
            }
         }

      } catch (RuntimeException var43) {
         throw Class44.clientError(var43, "wf.M(" + var1 + ',' + 64 + ',' + 64 + ',' + 64 + ')');
      }
   }

   abstract void method2231(byte var1);

   private void method2232() {
      try {
         this.aShortArray2049 = new short[this.anInt2062];
         int var2 = 0;

         while(var2 < this.anInt2062) {
            this.aShortArray2049[var2] = (short)((int)Math.pow(2.0D, (double)var2));
            ++var2;
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "wf.L(" + (byte) -60 + ')');
      }
   }

   abstract void method2233();

   public static void method2235(int var0) {
      try {
         anIntArray2048 = null;
         aClass153_2052 = null;
         if(var0 == 4) {
            aClass140_Sub5Array2058 = null;
            aClass94_2061 = null;
            aClass11_2055 = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "wf.J(" + var0 + ')');
      }
   }

   private void method2236(int var1) {
      try {
         Random var2 = new Random((long)this.anInt2054);

         int var3;
         for(var3 = 0; var3 < 255; ++var3) {
            this.aShortArray2047[var3] = (short)var3;
         }

         if(var1 != -190126388) {
            anInt2051 = -58;
         }

         for(var3 = 0; var3 < 255; ++var3) {
            int var4 = -var3 + 255;
            int var5 = Class100.method1603((byte)-120, var4, var2);
            short var6 = this.aShortArray2047[var5];
            this.aShortArray2047[var5] = this.aShortArray2047[var4];
            this.aShortArray2047[var4] = this.aShortArray2047[256 + var4] = var6;
         }

      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "wf.N(" + var1 + ')');
      }
   }

   abstract void method2237(int var1, int var2);

   Class164(int var1, int var3, int var4) {
      try {
         this.anInt2056 = var4;
         this.anInt2062 = 5;
         this.anInt2054 = var1;
         this.anInt2060 = 2;
         this.anInt2057 = var3;
         this.method2232();
         this.method2236(-190126388);
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "wf.<init>(" + var1 + ',' + 5 + ',' + var3 + ',' + var4 + ',' + 2 + ')');
      }
   }

}
