package org.runite.jagex;

abstract class Model extends GameObject {

   boolean aBoolean2699 = false;


   abstract int method1872();

   abstract int method1871();

   abstract boolean method1873();

   abstract void method1874();

   private void method1875(Class3_Sub17 var1, AnimationHeader var2, AnimationHeader var3, int var4, int var5, boolean[] var6, boolean var7, boolean var8, int var9, int[] var10) {
      int var11;
      if(var3 == null || var4 == 0) {
         for(var11 = 0; var11 < var2.anInt1383; ++var11) {
            short var12 = var2.aShortArray1385[var11];
            if(var6 == null || var6[var12] == var7 || var1.anIntArray2466[var12] == 0) {
               short var13 = var2.aShortArray1387[var11];
               int var14;
               if(var13 != -1) {
                  var14 = var9 & var1.anIntArray2455[var13];
                  if(var14 == '\uffff') {
                     this.method1891(0, var1.anIntArrayArray2460[var13], 0, 0, 0, var8);
                  } else {
                     this.method1899(0, var1.anIntArrayArray2460[var13], 0, 0, 0, var8, var14, var10);
                  }
               }

               var14 = var9 & var1.anIntArray2455[var12];
               if(var14 == '\uffff') {
                  this.method1891(var1.anIntArray2466[var12], var1.anIntArrayArray2460[var12], var2.aShortArray1388[var11], var2.aShortArray1396[var11], var2.aShortArray1395[var11], var8);
               } else {
                  this.method1899(var1.anIntArray2466[var12], var1.anIntArrayArray2460[var12], var2.aShortArray1388[var11], var2.aShortArray1396[var11], var2.aShortArray1395[var11], var8, var14, var10);
               }
            }
         }

      } else {
         var11 = 0;
         int var34 = 0;

         for(int var35 = 0; var35 < var1.anInt2462; ++var35) {
            boolean var36 = false;
            if(var11 < var2.anInt1383 && var2.aShortArray1385[var11] == var35) {
               var36 = true;
            }

            boolean var15 = false;
            if(var34 < var3.anInt1383 && var3.aShortArray1385[var34] == var35) {
               var15 = true;
            }

            if(var36 || var15) {
               if(var6 != null && var6[var35] != var7 && var1.anIntArray2466[var35] != 0) {
                  if(var36) {
                     ++var11;
                  }

                  if(var15) {
                     ++var34;
                  }
               } else {
                  short var16 = 0;
                  int var17 = var1.anIntArray2466[var35];
                  if(var17 == 3) {
                     var16 = 128;
                  }

                  short var19;
                  short var18;
                  short var21;
                  short var20;
                  byte var22;
                  if(var36) {
                     var18 = var2.aShortArray1388[var11];
                     var19 = var2.aShortArray1396[var11];
                     var20 = var2.aShortArray1395[var11];
                     var21 = var2.aShortArray1387[var11];
                     var22 = var2.aByteArray1393[var11];
                     ++var11;
                  } else {
                     var18 = var16;
                     var19 = var16;
                     var20 = var16;
                     var21 = -1;
                     var22 = 0;
                  }

                  short var23;
                  short var25;
                  short var24;
                  byte var27;
                  short var26;
                  if(var15) {
                     var23 = var3.aShortArray1388[var34];
                     var24 = var3.aShortArray1396[var34];
                     var25 = var3.aShortArray1395[var34];
                     var26 = var3.aShortArray1387[var34];
                     var27 = var3.aByteArray1393[var34];
                     ++var34;
                  } else {
                     var23 = var16;
                     var24 = var16;
                     var25 = var16;
                     var26 = -1;
                     var27 = 0;
                  }

                  int var29;
                  int var28;
                  int var31;
                  int var30;
                  if((var22 & 2) == 0 && (var27 & 1) == 0) {
                     if(var17 == 2) {
                        var31 = var23 - var18 & 2047;
                        int var32 = var24 - var19 & 2047;
                        int var33 = var25 - var20 & 2047;
                        if(var31 >= 1024) {
                           var31 -= 2048;
                        }

                        if(var32 >= 1024) {
                           var32 -= 2048;
                        }

                        if(var33 >= 1024) {
                           var33 -= 2048;
                        }

                        var28 = var18 + var31 * var4 / var5 & 2047;
                        var29 = var19 + var32 * var4 / var5 & 2047;
                        var30 = var20 + var33 * var4 / var5 & 2047;
                     } else if(var17 == 7) {
                        var31 = var23 - var18 & 63;
                        if(var31 >= 32) {
                           var31 -= 64;
                        }

                        var28 = var18 + var31 * var4 / var5 & 63;
                        var29 = var19 + (var24 - var19) * var4 / var5;
                        var30 = var20 + (var25 - var20) * var4 / var5;
                     } else {
                        var28 = var18 + (var23 - var18) * var4 / var5;
                        var29 = var19 + (var24 - var19) * var4 / var5;
                        var30 = var20 + (var25 - var20) * var4 / var5;
                     }
                  } else {
                     var28 = var18;
                     var29 = var19;
                     var30 = var20;
                  }

                  if(var21 != -1) {
                     var31 = var9 & var1.anIntArray2455[var21];
                     if(var31 == '\uffff') {
                        this.method1891(0, var1.anIntArrayArray2460[var21], 0, 0, 0, var8);
                     } else {
                        this.method1899(0, var1.anIntArrayArray2460[var21], 0, 0, 0, var8, var31, var10);
                     }
                  } else if(var26 != -1) {
                     var31 = var9 & var1.anIntArray2455[var26];
                     if(var31 == '\uffff') {
                        this.method1891(0, var1.anIntArrayArray2460[var26], 0, 0, 0, var8);
                     } else {
                        this.method1899(0, var1.anIntArrayArray2460[var26], 0, 0, 0, var8, var31, var10);
                     }
                  }

                  var31 = var9 & var1.anIntArray2455[var35];
                  if(var31 == '\uffff') {
                     this.method1891(var17, var1.anIntArrayArray2460[var35], var28, var29, var30, var8);
                  } else {
                     this.method1899(var17, var1.anIntArrayArray2460[var35], var28, var29, var30, var8, var31, var10);
                  }
               }
            }
         }

      }
   }

   abstract void method1876(int var1);

   final void method1877(Class3_Sub28_Sub5 var1, int var2) {
      if(var2 != -1) {
         if(this.method1873()) {
            AnimationHeader var3 = var1.animations[var2];
            Class3_Sub17 var4 = var3.skins;

            for(int var5 = 0; var5 < var3.anInt1383; ++var5) {
               short var6 = var3.aShortArray1385[var5];
               if(var4.aBooleanArray2463[var6]) {
                  if(var3.aShortArray1387[var5] != -1) {
                     this.method1889(0, 0, 0, 0);
                  }

                  this.method1889(var4.anIntArray2466[var6], var3.aShortArray1388[var5], var3.aShortArray1396[var5], var3.aShortArray1395[var5]);
               }
            }

            this.method1879();
         }
      }
   }

   private static int method1878(int[][] var0, int var1, int var2) {
      int var3 = var1 >> 7;
      int var4 = var2 >> 7;
      if(var3 >= 0 && var4 >= 0 && var3 < var0.length && var4 < var0[0].length) {
         int var5 = var1 & 127;
         int var6 = var2 & 127;
         int var7 = var0[var3][var4] * (128 - var5) + var0[var3 + 1][var4] * var5 >> 7;
         int var8 = var0[var3][var4 + 1] * (128 - var5) + var0[var3 + 1][var4 + 1] * var5 >> 7;
         return var7 * (128 - var6) + var8 * var6 >> 7;
      } else {
         return 0;
      }
   }

   abstract void method1879();

   final void method1880(Class3_Sub28_Sub5 var1, int var2, Class3_Sub28_Sub5 var3, int var4, int var5, int var6, boolean var7) {
      if(var2 != -1) {
         if(this.method1873()) {
            AnimationHeader var8 = var1.animations[var2];
            Class3_Sub17 var9 = var8.skins;
            AnimationHeader var10 = null;
            if(var3 != null) {
               var10 = var3.animations[var4];
               if(var10.skins != var9) {
                  var10 = null;
               }
            }

            this.method1875(var9, var8, var10, var5, var6, (boolean[])null, false, var7, '\uffff', (int[])null);
            this.method1879();
         }
      }
   }

   abstract void resize(int var1, int var2, int var3);

   abstract Model method1882(boolean var1, boolean var2, boolean var3);

   abstract int method1883();

   abstract int method1884();

   abstract void method1885();

   abstract void method1886(int var1);

   final void method1887(Class3_Sub28_Sub5 var1, int var2, Class3_Sub28_Sub5 var3, int var4, int var5, int var6, int var7, boolean var8, int[] var9) {
      if(var2 != -1) {
         if(this.method1873()) {
            AnimationHeader var10 = var1.animations[var2];
            Class3_Sub17 var11 = var10.skins;
            AnimationHeader var12 = null;
            if(var3 != null) {
               var12 = var3.animations[var4];
               if(var12.skins != var11) {
                  var12 = null;
               }
            }

            this.method1875(var11, var10, var12, var5, var6, (boolean[])null, false, var8, var7, var9);
            this.method1879();
         }
      }
   }

   abstract int method1888();

   abstract void method1889(int var1, int var2, int var3, int var4);

   abstract Model method1890(boolean var1, boolean var2, boolean var3);

   abstract void method1891(int var1, int[] var2, int var3, int var4, int var5, boolean var6);

   final void method1892(Class3_Sub28_Sub5 var1, int var2, Class3_Sub28_Sub5 var3, int var4, int var5, int var6, Class3_Sub28_Sub5 var7, int var8, Class3_Sub28_Sub5 var9, int var10, int var11, int var12, boolean[] debugArray530, boolean var14) {
      if(var2 != -1) {
         if(debugArray530 == null || var8 == -1) {
            this.method1880(var1, var2, var3, var4, var5, var6, var14);
         } else if (this.method1873()) {
            AnimationHeader var15 = var1.animations[var2];
            Class3_Sub17 var16 = var15.skins;
            AnimationHeader var17 = null;
            if (var3 != null) {
               var17 = var3.animations[var4];
               if (var17.skins != var16) {
                  var17 = null;
               }
            }

            AnimationHeader var18 = var7.animations[var8];
            AnimationHeader var19 = null;
            if (var9 != null) {
               var19 = var9.animations[var10];
               if (var19.skins != var16) {
                  var19 = null;
               }
            }

            this.method1875(var16, var15, var17, var5, var6, debugArray530, false, var14, '\uffff', (int[]) null);
            this.method1891(0, new int[0], 0, 0, 0, var14);
            this.method1875(var16, var18, var19, var11, var12, debugArray530, true, var14, '\uffff', (int[]) null);
            this.method1879();
         }
      }
   }

   abstract void method1893(int var2, int var3, int var4, int var5, int var6, int var7);

   abstract Model method1894(boolean var1, boolean var2, boolean var3);

   final void method1895(int[][] var1, int var2, int var3, int var4, int var5, int var6) {
      int var10 = -var5 / 2;
      int var11 = -var6 / 2;
      int var12 = method1878(var1, var2 + var10, var4 + var11);
      int var13 = var5 / 2;
      int var14 = -var6 / 2;
      int var15 = method1878(var1, var2 + var13, var4 + var14);
      int var16 = -var5 / 2;
      int var17 = var6 / 2;
      int var18 = method1878(var1, var2 + var16, var4 + var17);
      int var19 = var5 / 2;
      int var20 = var6 / 2;
      int var21 = method1878(var1, var2 + var19, var4 + var20);
      int var22 = var12 < var15?var12:var15;
      int var23 = var18 < var21?var18:var21;
      int var24 = var15 < var21?var15:var21;
      int var25 = var12 < var18?var12:var18;
      if(var6 != 0) {
         int var26 = (int)(Math.atan2((double)(var22 - var23), (double)var6) * 325.95D) & 2047;
         if(var26 != 0) {
            this.method1896(var26);
         }
      }

      if(var5 != 0) {
         int var27 = (int)(Math.atan2((double)(var25 - var24), (double)var5) * 325.95D) & 2047;
         if(var27 != 0) {
            this.method1886(var27);
         }
      }

      int var28 = var12 + var21;
      if(var15 + var18 < var28) {
         var28 = var15 + var18;
      }

      var28 = (var28 >> 1) - var3;
      if(var28 != 0) {
         this.method1897(0, var28, 0);
      }

   }

   abstract void method1896(int var1);

   final void method1867(int var1, int var2, int var3, int var4, int var5) {}

   abstract void method1897(int var1, int var2, int var3);

   abstract int method1898();

   abstract void animate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, long var9, int var11, Class127_Sub1 var12);

   abstract void method1899(int var1, int[] var2, int var3, int var4, int var5, boolean var6, int var7, int[] var8);

   abstract void method1900();

}
