package org.runite.jagex;

final class Class3_Sub1 extends Class3 {

   int anInt2202;
   static CacheIndex interfaceScriptsIndex;
   int anInt2205;
   static int anInt2208 = -1;
   static int[] anIntArray2209 = new int[]{2, 2, 4, 2, 1, 8, 4, 1, 4, 4, 2, 1, 1, 1, 4, 1};
   static int localIndex = -1;
   static int anInt2212 = 0;
   static int[] anIntArray2213 = new int[]{16776960, 16711680, '\uff00', '\uffff', 16711935, 16777215};


   static void method90(int var0) {
      try {
         if(HDToolKit.highDetail) {
            if(!Class3_Sub13_Sub34.aBoolean3416) {
               Class3_Sub2[][][] var1 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638;
               for (Class3_Sub2[][] var3 : var1) {
                  for (Class3_Sub2[] class3_sub2s : var3) {
                     for (Class3_Sub2 var6 : class3_sub2s) {
                        if (var6 != null) {
                           Class140_Sub1_Sub1 var7;
                           if (var6.aClass12_2230 != null && var6.aClass12_2230.object instanceof Class140_Sub1_Sub1) {
                              var7 = (Class140_Sub1_Sub1) var6.aClass12_2230.object;
                              if ((var6.aClass12_2230.aLong328 & Long.MIN_VALUE) == 0) {
                                 var7.method1920(false, true, true, false, true, true);
                              } else {
                                 var7.method1920(true, true, true, true, true, true);
                              }
                           }

                           if (null != var6.aClass19_2233) {
                              if (var6.aClass19_2233.aClass140_429 instanceof Class140_Sub1_Sub1) {
                                 var7 = (Class140_Sub1_Sub1) var6.aClass19_2233.aClass140_429;
                                 if (0L == (var6.aClass19_2233.aLong428 & Long.MIN_VALUE)) {
                                    var7.method1920(false, true, true, false, true, true);
                                 } else {
                                    var7.method1920(true, true, true, true, true, true);
                                 }
                              }

                              if (var6.aClass19_2233.aClass140_423 instanceof Class140_Sub1_Sub1) {
                                 var7 = (Class140_Sub1_Sub1) var6.aClass19_2233.aClass140_423;
                                 if ((Long.MIN_VALUE & var6.aClass19_2233.aLong428) == 0) {
                                    var7.method1920(false, true, true, false, true, true);
                                 } else {
                                    var7.method1920(true, true, true, true, true, true);
                                 }
                              }
                           }

                           if (var6.aClass70_2234 != null) {
                              if (var6.aClass70_2234.aClass140_1049 instanceof Class140_Sub1_Sub1) {
                                 var7 = (Class140_Sub1_Sub1) var6.aClass70_2234.aClass140_1049;
                                 if ((var6.aClass70_2234.aLong1048 & Long.MIN_VALUE) == 0) {
                                    var7.method1920(false, true, true, false, true, true);
                                 } else {
                                    var7.method1920(true, true, true, true, true, true);
                                 }
                              }

                              if (var6.aClass70_2234.aClass140_1052 instanceof Class140_Sub1_Sub1) {
                                 var7 = (Class140_Sub1_Sub1) var6.aClass70_2234.aClass140_1052;
                                 if ((Long.MIN_VALUE & var6.aClass70_2234.aLong1048) == 0) {
                                    var7.method1920(false, true, true, false, true, true);
                                 } else {
                                    var7.method1920(true, true, true, true, true, true);
                                 }
                              }
                           }

                           for (int var10 = 0; var6.anInt2223 > var10; ++var10) {
                              if (var6.aClass25Array2221[var10].aClass140_479 instanceof Class140_Sub1_Sub1) {
                                 Class140_Sub1_Sub1 var8 = (Class140_Sub1_Sub1) var6.aClass25Array2221[var10].aClass140_479;
                                 if ((Long.MIN_VALUE & var6.aClass25Array2221[var10].aLong498) == 0) {
                                    var8.method1920(false, true, true, false, true, true);
                                 } else {
                                    var8.method1920(true, true, true, true, true, true);
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               Class3_Sub13_Sub34.aBoolean3416 = true;
            }
         }
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "bf.D(" + var0 + ')');
      }
   }

   public static void method91(byte var0) {
      try {
         anIntArray2213 = null;
         if(var0 <= 110) {
            method90(-74);
         }

         interfaceScriptsIndex = null;
         anIntArray2209 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "bf.P(" + var0 + ')');
      }
   }

   final boolean method92(int var1, byte var2) {
      try {
         return 0 != (this.anInt2205 >> 1 + var1 & 1);
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "bf.C(" + var1 + ',' + var2 + ')');
      }
   }

   final boolean method93() {
      try {
         return 0 != (572878952 & this.anInt2205) >> 29;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bf.S(" + 572878952 + ')');
      }
   }

   final int method94() {
      try {

         return this.anInt2205 >> 18 & 7;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bf.A(" + (byte) -74 + ')');
      }
   }

   final boolean method95() {
      try {

         return (1 & this.anInt2205) != 0;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bf.Q(" + -13081 + ')');
      }
   }

   final boolean method96() {
      try {
         return (this.anInt2205 >> 31 & 1) != 0;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bf.T(" + -2063688673 + ')');
      }
   }

   final boolean method97() {
      try {
         return 0 != (1 & this.anInt2205 >> 22);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bf.R(" + -20710 + ')');
      }
   }

   Class3_Sub1(int var1, int var2) {
      try {
         this.anInt2202 = var2;
         this.anInt2205 = var1;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "bf.<init>(" + var1 + ',' + var2 + ')');
      }
   }

   final boolean method98() {
      try {

         return (this.anInt2205 >> 21 & 1) != 0;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bf.E(" + false + ')');
      }
   }

   final boolean method99() {
      try {

         return (1738913629 & this.anInt2205) >> 30 != 0;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bf.F(" + 31595 + ')');
      }
   }

   final boolean method100() {
      try {
         return (this.anInt2205 & 455226656) >> 28 != 0;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bf.O(" + (byte) -9 + ')');
      }
   }

   final int method101() {
      try {
         return (127 & this.anInt2205 >> 11);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bf.B(" + ')');
      }
   }

}
