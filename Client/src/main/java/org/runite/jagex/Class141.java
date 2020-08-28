package org.runite.jagex;

import javax.media.opengl.GL;

final class Class141 {

   static LDIndexedSprite aClass109_Sub1_1840;
   static Class169[][] aClass169ArrayArray1841;
   private static int anInt1842;
   static LDIndexedSprite[] aClass109_Sub1Array1843;
   private static int anInt1844;


   private static void method2034(LDIndexedSprite var0, LDIndexedSprite var1, int var2, int var3) {
      var2 += var0.anInt1470;
      var3 += var0.anInt1464;
      int var4 = var2 + var3 * var1.anInt1461;
      int var5 = 0;
      int var6 = var0.anInt1468;
      int var7 = var0.anInt1461;
      int var8 = var1.anInt1461 - var7;
      int var9 = 0;
      int var10;
      if(var3 <= 0) {
         var10 = 1 - var3;
         var6 -= var10;
         var5 += var10 * var7;
         var4 += var10 * var1.anInt1461;
         var3 = 1;
      }

      if(var3 + var6 >= var1.anInt1468) {
         var10 = var3 + var6 + 1 - var1.anInt1468;
         var6 -= var10;
      }

      if(var2 <= 0) {
         var10 = 1 - var2;
         var7 -= var10;
         var5 += var10;
         var4 += var10;
         var9 += var10;
         var8 += var10;
         var2 = 1;
      }

      if(var2 + var7 >= var1.anInt1461) {
         var10 = var2 + var7 + 1 - var1.anInt1461;
         var7 -= var10;
         var9 += var10;
         var8 += var10;
      }

      if(var7 > 0 && var6 > 0) {
         method2044(var1.aByteArray2674, var0.aByteArray2674, var5, var4, var7, var6, var8, var9);
         method2036(var2, var3, var7, var6);
      }
   }

   private static void method2035(byte[] var0, byte[] var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      int var8 = -(var4 >> 2);
      var4 = -(var4 & 3);

      for(int var9 = -var5; var9 < 0; ++var9) {
         int var10;
         int var10001;
         for(var10 = var8; var10 < 0; ++var10) {
            var10001 = var3++;
            var0[var10001] -= var1[var2++];
            var10001 = var3++;
            var0[var10001] -= var1[var2++];
            var10001 = var3++;
            var0[var10001] -= var1[var2++];
            var10001 = var3++;
            var0[var10001] -= var1[var2++];
         }

         for(var10 = var4; var10 < 0; ++var10) {
            var10001 = var3++;
            var0[var10001] -= var1[var2++];
         }

         var3 += var6;
         var2 += var7;
      }

   }

   private static void method2036(int var0, int var1, int var2, int var3) {
      int var4 = var0 - 1 >> 7;
      int var5 = var0 - 1 + var2 - 1 >> 7;
      int var6 = var1 - 1 >> 7;
      int var7 = var1 - 1 + var3 - 1 >> 7;

      for(int var8 = var4; var8 <= var5; ++var8) {
         for(int var9 = var6; var9 <= var7; ++var9) {
            aClass169ArrayArray1841[var8][var9].aBoolean2106 = true;
         }
      }

   }

   static void method2037(int var0, int var1, boolean var2, boolean var3, int var4, int var5, int var6, int var7, int var8, int var9) {
      if(!var2 || !var3) {
         if(!var2 || var0 != 1) {
            if(!var3 || var0 != 0) {
               int var10 = var4 << 7;
               int var11 = var6 + var7 + var8 + var9 >> 2;
               int var12 = var5 << 7;
               int var13 = var10 - (var11 * Class92.lightX >> 8) >> 3;
               int var14 = var12 - (var11 * Class92.lightY >> 8) >> 3;
               if(var0 != 0 && var0 != 1 && (var2 || var3)) {
                  method2050(aClass109_Sub1Array1843[var0], aClass109_Sub1_1840, var13 + 1, var14 + 1, var1, var2);
               } else {
                  method2034(aClass109_Sub1Array1843[1], aClass109_Sub1_1840, var13 + 1, var14 + 1);
               }
            }
         }
      }
   }

   static void method2038(int var0, int var1, int var2, boolean[][] var4) {
      GL var6 = HDToolKit.gl;
      HDToolKit.method1856(1);
      HDToolKit.method1847(1);
      HDToolKit.method1823();
      HDToolKit.method1837(false);
      Class3_Sub28_Sub4.method551(0, 0);
      var6.glDepthMask(false);

      for(int var7 = 0; var7 < anInt1844; ++var7) {
         int var8 = 0;

         while(var8 < anInt1842) {
            int var9 = var7 * 8;

            while(true) {
               if(var9 < var7 * 8 + 8) {
                  label42: {
                     if(var9 - var0 >= -var2 && var9 - var0 <= var2) {
                        for(int var10 = var8 * 8; var10 < var8 * 8 + 8; ++var10) {
                           if(var10 - var1 >= -var2 && var10 - var1 <= var2 && var4[var9 - var0 + var2][var10 - var1 + var2]) {
                              Class169 var11 = aClass169ArrayArray1841[var7][var8];
                              if(var11.aBoolean2106) {
                                 var11.method2282(aClass109_Sub1_1840, var7, var8);
                                 var11.aBoolean2106 = false;
                              }

                              var6.glPushMatrix();
                              var6.glTranslatef((float)(var7 * 1024), 0.0F, (float)(var8 * 1024));
                              var11.method2284();
                              var6.glPopMatrix();
                              break label42;
                           }
                        }
                     }

                     ++var9;
                     continue;
                  }
               }

               ++var8;
               break;
            }
         }
      }

      var6.glEnableClientState('\u8076');
      var6.glDepthMask(true);
      HDToolKit.method1846();
   }

   private static boolean method2039(byte[] var0, int var1, int var2, int var3, int var4, int var5) {
      int var6 = var2 % var5;
      int var7;
      if(var6 == 0) {
         var7 = 0;
      } else {
         var7 = var5 - var6;
      }

       int var8 = -((var3 + var5 - 1) / var5);
      int var9 = -((var2 + var5 - 1) / var5);

      for(int var10 = var8; var10 < 0; ++var10) {
         for(int var11 = var9; var11 < 0; ++var11) {
            if(var0[var1] == 0) {
               return true;
            }

            var1 += var5;
         }

         var1 -= var7;
         if(var0[var1 - 1] == 0) {
            return true;
         }

         var1 += var4;
      }

      return false;
   }

   private static boolean method2040(LDIndexedSprite var0, LDIndexedSprite var1, int var2, int var3) {
      var2 += var0.anInt1470;
      var3 += var0.anInt1464;
      int var4 = var2 + var3 * var1.anInt1461;
      int var5 = var0.anInt1468;
      int var6 = var0.anInt1461;
      int var7 = var1.anInt1461 - var6;
      int var8;
      if(var3 <= 0) {
         var8 = 1 - var3;
         var5 -= var8;
         var4 += var8 * var1.anInt1461;
         var3 = 1;
      }

      if(var3 + var5 >= var1.anInt1468) {
         var8 = var3 + var5 + 1 - var1.anInt1468;
         var5 -= var8;
      }

      if(var2 <= 0) {
         var8 = 1 - var2;
         var6 -= var8;
         var4 += var8;
         var7 += var8;
         var2 = 1;
      }

      if(var2 + var6 >= var1.anInt1461) {
         var8 = var2 + var6 + 1 - var1.anInt1461;
         var6 -= var8;
         var7 += var8;
      }

      if(var6 > 0 && var5 > 0) {
         byte var9 = 8;
         var7 += (var9 - 1) * var1.anInt1461;
         method2036(var2, var3, var6, var5);
         return method2039(var1.aByteArray2674, var4, var6, var5, var7, var9);
      } else {
         return false;
      }
   }

   static void method2041() {
      anInt1844 = 104 + 7 >> 3;
      anInt1842 = 104 + 7 >> 3;
      aClass109_Sub1_1840 = new LDIndexedSprite(anInt1844 * 128 + 2, anInt1842 * 128 + 2);
      aClass169ArrayArray1841 = new Class169[anInt1844][anInt1842];

      for(int var2 = 0; var2 < anInt1844; ++var2) {
         for(int var3 = 0; var3 < anInt1842; ++var3) {
            aClass169ArrayArray1841[var2][var3] = new Class169();
         }
      }

   }

   private static void method2042(LDIndexedSprite var0, LDIndexedSprite var1, int var2, int var3) {
      var2 += var0.anInt1470;
      var3 += var0.anInt1464;
      int var4 = var2 + var3 * var1.anInt1461;
      int var5 = 0;
      int var6 = var0.anInt1468;
      int var7 = var0.anInt1461;
      int var8 = var1.anInt1461 - var7;
      int var9 = 0;
      int var10;
      if(var3 <= 0) {
         var10 = 1 - var3;
         var6 -= var10;
         var5 += var10 * var7;
         var4 += var10 * var1.anInt1461;
         var3 = 1;
      }

      if(var3 + var6 >= var1.anInt1468) {
         var10 = var3 + var6 + 1 - var1.anInt1468;
         var6 -= var10;
      }

      if(var2 <= 0) {
         var10 = 1 - var2;
         var7 -= var10;
         var5 += var10;
         var4 += var10;
         var9 += var10;
         var8 += var10;
         var2 = 1;
      }

      if(var2 + var7 >= var1.anInt1461) {
         var10 = var2 + var7 + 1 - var1.anInt1461;
         var7 -= var10;
         var9 += var10;
         var8 += var10;
      }

      if(var7 > 0 && var6 > 0) {
         method2035(var1.aByteArray2674, var0.aByteArray2674, var5, var4, var7, var6, var8, var9);
         method2036(var2, var3, var7, var6);
      }
   }

   static void method2043() {
      aClass109_Sub1_1840 = null;
      aClass109_Sub1Array1843 = null;
      aClass169ArrayArray1841 = (Class169[][])null;
   }

   private static void method2044(byte[] var0, byte[] var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      int var8 = -(var4 >> 2);
      var4 = -(var4 & 3);

      for(int var9 = -var5; var9 < 0; ++var9) {
         int var10;
         int var10001;
         for(var10 = var8; var10 < 0; ++var10) {
            var10001 = var3++;
            var0[var10001] += var1[var2++];
            var10001 = var3++;
            var0[var10001] += var1[var2++];
            var10001 = var3++;
            var0[var10001] += var1[var2++];
            var10001 = var3++;
            var0[var10001] += var1[var2++];
         }

         for(var10 = var4; var10 < 0; ++var10) {
            var10001 = var3++;
            var0[var10001] += var1[var2++];
         }

         var3 += var6;
         var2 += var7;
      }

   }

   public static void method2045() {
      aClass109_Sub1_1840 = null;
      aClass109_Sub1Array1843 = null;
      aClass169ArrayArray1841 = (Class169[][])null;
   }

   private static void method2046(byte[] var0, byte[] var1, int var2, int var3, int var4, int var5, int var6) {
      for(int var7 = -16; var7 < 0; ++var7) {
         for(int var8 = -4; var8 < 0; ++var8) {
            int var10001 = var3++;
            var0[var10001] = (byte)(var0[var10001] + (1 - var1[var2]));
            var2 += var5;
            var10001 = var3++;
            var0[var10001] = (byte)(var0[var10001] + (1 - var1[var2]));
            var2 += var5;
            var10001 = var3++;
            var0[var10001] = (byte)(var0[var10001] + (1 - var1[var2]));
            var2 += var5;
            var10001 = var3++;
            var0[var10001] = (byte)(var0[var10001] + (1 - var1[var2]));
            var2 += var5;
         }

         var3 += var4;
         var2 += var6;
      }

   }

   static void method2047(LDIndexedSprite var0, int var1, int var2, int var3) {
      if(var0 != null) {
         int var4 = var1 - (var2 * Class92.lightX >> 8) >> 3;
         int var5 = var3 - (var2 * Class92.lightY >> 8) >> 3;
         method2042(var0, aClass109_Sub1_1840, var4 + 1, var5 + 1);
      }
   }

   private static void method2048(byte[] var0, byte[] var1, int var2, int var3, int var4, int var5, int var6) {
      for(int var7 = -16; var7 < 0; ++var7) {
         for(int var8 = -4; var8 < 0; ++var8) {
            int var10001 = var3++;
            var0[var10001] += var1[var2];
            var2 += var5;
            var10001 = var3++;
            var0[var10001] += var1[var2];
            var2 += var5;
            var10001 = var3++;
            var0[var10001] += var1[var2];
            var2 += var5;
            var10001 = var3++;
            var0[var10001] += var1[var2];
            var2 += var5;
         }

         var3 += var4;
         var2 += var6;
      }

   }

   static boolean method2049(LDIndexedSprite var0, int var1, int var2, int var3) {
      if(var0 == null) {
         return false;
      } else {
         int var4 = var1 - (var2 * Class92.lightX >> 8) >> 3;
         int var5 = var3 - (var2 * Class92.lightY >> 8) >> 3;
         return method2040(var0, aClass109_Sub1_1840, var4 + 1, var5 + 1);
      }
   }

   private static void method2050(LDIndexedSprite var0, LDIndexedSprite var1, int var2, int var3, int var4, boolean var5) {
      if(var2 > 0 && var3 > 0 && var2 + 16 < var1.anInt1461 && var3 + 16 < var1.anInt1468) {
         int var6 = var2 + var3 * var1.anInt1461;
         int var7 = var1.anInt1461 - 16;
         short var8;
         byte var9;
         int var10;
         if(var4 == 0) {
            var8 = 240;
            var9 = 1;
            var10 = -var9 * 16 - 16;
         } else if(var4 == 1) {
            var8 = 255;
            var9 = -16;
            var10 = -var9 * 16 - 1;
         } else if(var4 == 2) {
            var8 = 15;
            var9 = -1;
            var10 = -var9 * 16 + 16;
         } else {
            var8 = 0;
            var9 = 16;
            var10 = -var9 * 16 + 1;
         }

         if(var5) {
            method2046(var1.aByteArray2674, var0.aByteArray2674, var8, var6, var7, var9, var10);
         } else {
            method2048(var1.aByteArray2674, var0.aByteArray2674, var8, var6, var7, var9, var10);
         }

         method2036(var2, var3, 16, 16);
      }
   }

   static void method2051(LDIndexedSprite var0, int var1, int var2, int var3) {
      if(var0 != null) {
         int var4 = var1 - (var2 * Class92.lightX >> 8) >> 3;
         int var5 = var3 - (var2 * Class92.lightY >> 8) >> 3;
         method2034(var0, aClass109_Sub1_1840, var4 + 1, var5 + 1);
      }
   }
}
