package org.runite.jagex;

import java.util.Objects;

final class Class3_Sub13_Sub18 extends Class3_Sub13 {

   static CacheIndex aClass153_3214;
   static int[][] anIntArrayArray3215 = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1}, {1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1}, {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1}, {1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1}};
   static int anInt3216 = 0;
   private int anInt3217 = '\u8000';
   static int[] anIntArray3218 = new int[]{1, 4};


   static void method254(boolean var0, Class3_Sub31 var1) {
      try {
         int var4 = (int)var1.aLong71;

         int var3 = var1.anInt2602;
         var1.method86(-1024);
         if(var0) {
            Class60.method1208((byte)79, var3);
         }

         Class164_Sub2.method2249(var3);
         RSInterface var5 = Class7.getRSInterface((byte)109, var4);
         if(null != var5) {
            Class20.method909(107, var5);
         }

         int var6 = Class3_Sub13_Sub34.anInt3415;

         int var7;
         for(var7 = 0; var6 > var7; ++var7) {
            if(Class2.method73(Class3_Sub13_Sub7.aShortArray3095[var7])) {
               Class3_Sub25.method509(var7);
            }
         }

         if(Class3_Sub13_Sub34.anInt3415 == 1) {
            Class38_Sub1.aBoolean2615 = false;
            Class75.method1340(AbstractIndexedSprite.anInt1462, Class3_Sub28_Sub3.anInt3552, Class3_Sub13_Sub33.anInt3395, Class3_Sub28_Sub1.anInt3537);
         } else {
            Class75.method1340(AbstractIndexedSprite.anInt1462, Class3_Sub28_Sub3.anInt3552, Class3_Sub13_Sub33.anInt3395, Class3_Sub28_Sub1.anInt3537);
            var7 = Class168.aClass3_Sub28_Sub17_2096.method682(TextCore.HasChooseOptions);

            for(int var8 = 0; Class3_Sub13_Sub34.anInt3415 > var8; ++var8) {
               int var9 = Class168.aClass3_Sub28_Sub17_2096.method682(RSByteBuffer.method802(var8));
               if(var7 < var9) {
                  var7 = var9;
               }
            }

            Class3_Sub28_Sub1.anInt3537 = Class3_Sub13_Sub34.anInt3415 * 15 + (CacheIndex.aBoolean1951?26:22);
            Class3_Sub28_Sub3.anInt3552 = var7 + 8;
         }

         if(-1 != Class3_Sub28_Sub12.anInt3655) {
            Class3_Sub8.method124(115, 1, Class3_Sub28_Sub12.anInt3655);
         }

      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "ke.O(" + var0 + ',' + (var1 != null?"{...}":"null") + ',' + false + ')');
      }
   }

   static void method255(int var0, int var1, int var2) {
      try {
         Class3_Sub28_Sub6 var3 = Class3_Sub24_Sub3.method466(var2, var0);
         var3.g();
         var3.anInt3598 = var1;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "ke.Q(" + var0 + ',' + var1 + ',' + var2 + ')');
      }
   }

   public Class3_Sub13_Sub18() {
      super(3, false);
   }

   final int[][] method166(int var1, int var2) {
      try {
         if(var1 != -1) {
            aClass153_3214 = (CacheIndex)null;
         }

         int[][] var3 = this.aClass97_2376.method1594((byte)4, var2);
         if(this.aClass97_2376.aBoolean1379) {
            int[] var4 = this.method152(1, var2, 32755);
            int[] var5 = this.method152(2, var2, 32755);
            int[] var8 = var3[2];
            int[] var7 = var3[1];
            int[] var6 = var3[0];

            for(int var9 = 0; Class113.anInt1559 > var9; ++var9) {
               int var10 = (var4[var9] * 255 & 1046259) >> 12;
               int var11 = var5[var9] * this.anInt3217 >> 12;
               int var12 = var11 * Class75_Sub2.anIntArray2639[var10] >> 12;
               int var13 = Class3_Sub13_Sub17.anIntArray3212[var10] * var11 >> 12;
               int var14 = (var12 >> 12) + var9 & RenderAnimationDefinition.anInt396;
               int var15 = Class3_Sub20.anInt2487 & var2 - -(var13 >> 12);
               int[][] var16 = this.method162(var15, 0, (byte)-117);
               var6[var9] = Objects.requireNonNull(var16)[0][var14];
               var7[var9] = var16[1][var14];
               var8[var9] = var16[2][var14];
            }
         }

         return var3;
      } catch (RuntimeException var17) {
         throw Class44.clientError(var17, "ke.T(" + var1 + ',' + var2 + ')');
      }
   }

   final int[] method154(int var1, byte var2) {
      try {
         int[] var4 = this.aClass114_2382.method1709(var1);
         if(this.aClass114_2382.aBoolean1580) {
            int[] var5 = this.method152(1, var1, 32755);
            int[] var6 = this.method152(2, var1, 32755);

            for(int var7 = 0; var7 < Class113.anInt1559; ++var7) {
               int var9 = this.anInt3217 * var6[var7] >> 12;
               int var8 = (var5[var7] & 4087) >> 4;
               int var10 = Class75_Sub2.anIntArray2639[var8] * var9 >> 12;
               int var11 = Class3_Sub13_Sub17.anIntArray3212[var8] * var9 >> 12;
               int var12 = RenderAnimationDefinition.anInt396 & (var10 >> 12) + var7;
               int var13 = Class3_Sub20.anInt2487 & (var11 >> 12) + var1;
               int[] var14 = this.method152(0, var13, 32755);
               var4[var7] = var14[var12];
            }
         }

         return var4;
      } catch (RuntimeException var15) {
         throw Class44.clientError(var15, "ke.D(" + var1 + ',' + var2 + ')');
      }
   }

   final void method157(int var1, RSByteBuffer var2, boolean var3) {
      try {
         if(var1 == 0) {
            this.anInt3217 = var2.getShort() << 4;
         } else if (var1 == 1) {
            this.aBoolean2375 = var2.getByteB() == 1;
         }

         if(!var3) {
            anInt3216 = -7;
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "ke.A(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

   static void method256(int var0, int var1, int var2, byte var3, int var4) {
      try {
         Class3_Sub28_Sub6 var5 = Class3_Sub24_Sub3.method466(4, var2);
         var5.g();
         var5.anInt3597 = var4;
         var5.anInt3596 = var0;
         if(var3 >= -108) {
            method258(-75);
         }

         var5.anInt3598 = var1;
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "ke.E(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
      }
   }

   static void method257() {
      try {
         int var1 = 0;

         for(int var2 = 0; var2 < 104; ++var2) {
            for(int var3 = 0; var3 < 104; ++var3) {
               if(Class140_Sub7.method2031((byte)-106, true, var2, var3, Class75_Sub2.aClass3_Sub2ArrayArrayArray2638, var1)) {
                  ++var1;
               }

               if(var1 >= 512) {
                  return;
               }
            }
         }

      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "ke.C(" + (byte) 125 + ')');
      }
   }

   public static void method258(int var0) {
      try {
         anIntArray3218 = null;
         anIntArrayArray3215 = (int[][])null;
         aClass153_3214 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ke.B(" + var0 + ')');
      }
   }

   static void method259(Class126 var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
      int var9;
      int var10 = var9 = (var6 << 7) - Class129_Sub1.anInt2697;
      int var11;
      int var12 = var11 = (var7 << 7) - Class3_Sub13_Sub30.anInt3363;
      int var13;
      int var14 = var13 = var10 + 128;
      int var15;
      int var16 = var15 = var12 + 128;
      int var17 = Class44.anIntArrayArrayArray723[var1][var6][var7] - Class3_Sub28_Sub13.anInt3657;
      int var18 = Class44.anIntArrayArrayArray723[var1][var6 + 1][var7] - Class3_Sub28_Sub13.anInt3657;
      int var19 = Class44.anIntArrayArrayArray723[var1][var6 + 1][var7 + 1] - Class3_Sub28_Sub13.anInt3657;
      int var20 = Class44.anIntArrayArrayArray723[var1][var6][var7 + 1] - Class3_Sub28_Sub13.anInt3657;
      int var21 = var12 * var4 + var10 * var5 >> 16;
      var12 = var12 * var5 - var10 * var4 >> 16;
      var10 = var21;
      var21 = var17 * var3 - var12 * var2 >> 16;
      var12 = var17 * var2 + var12 * var3 >> 16;
      var17 = var21;
      if(var12 >= 50) {
         var21 = var11 * var4 + var14 * var5 >> 16;
         var11 = var11 * var5 - var14 * var4 >> 16;
         var14 = var21;
         var21 = var18 * var3 - var11 * var2 >> 16;
         var11 = var18 * var2 + var11 * var3 >> 16;
         var18 = var21;
         if(var11 >= 50) {
            var21 = var16 * var4 + var13 * var5 >> 16;
            var16 = var16 * var5 - var13 * var4 >> 16;
            var13 = var21;
            var21 = var19 * var3 - var16 * var2 >> 16;
            var16 = var19 * var2 + var16 * var3 >> 16;
            var19 = var21;
            if(var16 >= 50) {
               var21 = var15 * var4 + var9 * var5 >> 16;
               var15 = var15 * var5 - var9 * var4 >> 16;
               var9 = var21;
               var21 = var20 * var3 - var15 * var2 >> 16;
               var15 = var20 * var2 + var15 * var3 >> 16;
               if(var15 >= 50) {
                  int var22 = Class51.anInt846 + (var10 << 9) / var12;
                  int var23 = Class51.anInt835 + (var17 << 9) / var12;
                  int var24 = Class51.anInt846 + (var14 << 9) / var11;
                  int var25 = Class51.anInt835 + (var18 << 9) / var11;
                  int var26 = Class51.anInt846 + (var13 << 9) / var16;
                  int var27 = Class51.anInt835 + (var19 << 9) / var16;
                  int var28 = Class51.anInt846 + (var9 << 9) / var15;
                  int var29 = Class51.anInt835 + (var21 << 9) / var15;
                  Class51.anInt850 = 0;
                  int var30;
                  if((var26 - var28) * (var25 - var29) - (var27 - var29) * (var24 - var28) > 0) {
                     if(Class3_Sub13_Sub21.aBoolean3261 && Class3_Sub13_Sub4.method185(Class49.anInt819 + Class51.anInt846, Class3_Sub13_Sub23_Sub1.anInt4039 + Class51.anInt835, var27, var29, var25, var26, var28, var24)) {
                        Class27.anInt515 = var6;
                        Class66.anInt999 = var7;
                     }

                     if(!HDToolKit.highDetail && !var8) {
                        Class51.aBoolean849 = var26 < 0 || var28 < 0 || var24 < 0 || var26 > Class51.anInt847 || var28 > Class51.anInt847 || var24 > Class51.anInt847;

                        if(var0.anInt1670 == -1) {
                           if(var0.anInt1664 != 12345678) {
                              Class51.method1154(var27, var29, var25, var26, var28, var24, var0.anInt1664, var0.anInt1663, var0.anInt1667);
                           }
                        } else if(Class3_Sub13_Sub22.aBoolean3275) {
                           if(var0.aBoolean1674) {
                              Class51.method1135(var27, var29, var25, var26, var28, var24, var0.anInt1664, var0.anInt1663, var0.anInt1667, var10, var14, var9, var17, var18, var21, var12, var11, var15, var0.anInt1670);
                           } else {
                              Class51.method1135(var27, var29, var25, var26, var28, var24, var0.anInt1664, var0.anInt1663, var0.anInt1667, var13, var9, var14, var19, var21, var18, var16, var15, var11, var0.anInt1670);
                           }
                        } else {
                           var30 = Class51.anInterface2_838.method15(var0.anInt1670, '\uffff');
                           Class51.method1154(var27, var29, var25, var26, var28, var24, Class127.method1753(var30, var0.anInt1664), Class127.method1753(var30, var0.anInt1663), Class127.method1753(var30, var0.anInt1667));
                        }
                     }
                  }

                  if((var22 - var24) * (var29 - var25) - (var23 - var25) * (var28 - var24) > 0) {
                     if(Class3_Sub13_Sub21.aBoolean3261 && Class3_Sub13_Sub4.method185(Class49.anInt819 + Class51.anInt846, Class3_Sub13_Sub23_Sub1.anInt4039 + Class51.anInt835, var23, var25, var29, var22, var24, var28)) {
                        Class27.anInt515 = var6;
                        Class66.anInt999 = var7;
                     }

                     if(!HDToolKit.highDetail && !var8) {
                        Class51.aBoolean849 = var22 < 0 || var24 < 0 || var28 < 0 || var22 > Class51.anInt847 || var24 > Class51.anInt847 || var28 > Class51.anInt847;

                        if(var0.anInt1670 == -1) {
                           if(var0.anInt1675 != 12345678) {
                              Class51.method1154(var23, var25, var29, var22, var24, var28, var0.anInt1675, var0.anInt1667, var0.anInt1663);
                           }
                        } else if(Class3_Sub13_Sub22.aBoolean3275) {
                           Class51.method1135(var23, var25, var29, var22, var24, var28, var0.anInt1675, var0.anInt1667, var0.anInt1663, var10, var14, var9, var17, var18, var21, var12, var11, var15, var0.anInt1670);
                        } else {
                           var30 = Class51.anInterface2_838.method15(var0.anInt1670, '\uffff');
                           Class51.method1154(var23, var25, var29, var22, var24, var28, Class127.method1753(var30, var0.anInt1675), Class127.method1753(var30, var0.anInt1667), Class127.method1753(var30, var0.anInt1663));
                        }
                     }
                  }

               }
            }
         }
      }
   }

   final void method158(int var1) {
      try {
         if(var1 != 16251) {
            method255(33, 78, 124);
         }

         Class8.method844((byte)-9);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ke.P(" + var1 + ')');
      }
   }

}
