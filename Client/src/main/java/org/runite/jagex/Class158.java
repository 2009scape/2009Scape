package org.runite.jagex;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

abstract class Class158 {

   static int[] anIntArray2004 = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 73, 74, 76, 78, 83, 84, 85, 86, 91, 92, 93, 94, 95, 97, 103, 104, 105, 106, 107, 108, 113, 114, 115, 116, 118, 119, 120, 121, 122, 123, 124, 125, 133, 134, 136, 138, 143, 144, 145, 146, 151, 152, 153, 154, 155, 157, 163, 164, 165, 166, 168, 169, 174, 175, 176, 177, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 97, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 157, 215, 216, 117, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 66, 66, 66, 66, 66, 66, 65, 75, 79, 79, 79, 79, 87, 87, 87, 87, 77, 96, 98, 98, 98, 98, 98, 250, 251, 109, 109, 109, 109, 117, 252, 167, 126, 126, 126, 126, 126, 126, 125, 135, 139, 139, 139, 139, 147, 147, 147, 147, 137, 156, 158, 158, 158, 158, 158, 253, 254, 170, 170, 170, 170, 178, 255, 178};
   static int anInt2005 = -2;
   static RSString aClass94_2006 = RSString.createRSString("null");
   int[] anIntArray2007;
   static byte[][][] aByteArrayArrayArray2008;
   Image anImage2009;
   static int anInt2010 = -16 + (int)(33.0D * Math.random());
   int anInt2011;
   int anInt2012;
   static int anInt2014 = 0;
   static int anInt2015;


   abstract void method2179(Graphics var3);

   static void method2180(CacheIndex var0, CacheIndex var1) {
      try {
         Class3_Sub28_Sub7_Sub1.aClass153_4048 = var0;
         Class3_Sub13_Sub18.aClass153_3214 = var1;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "vk.D(" + (var0 != null?"{...}":"null") + ',' + (var1 != null?"{...}":"null") + ',' + 11504 + ')');
      }
   }

   public static void method2181(boolean var0) {
      try {
         aByteArrayArrayArray2008 = (byte[][][])null;
         anIntArray2004 = null;
         if(var0) {
            method2181(false);
         }

         aClass94_2006 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "vk.H(" + ')');
      }
   }

   final void method2182() {
      try {
         Class74.method1319(this.anIntArray2007, this.anInt2012, this.anInt2011);

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "vk.G(" + 0 + ')');
      }
   }

   static void method2183(int var0, boolean var1, int var2, int var4, RSInterface[] var5) {
      try {
         int var6 = 0;
         for(; var5.length > var6; ++var6) {
            RSInterface var7 = var5[var6];
            if(null != var7 && var7.parentId == var0) {
               Class3_Sub28_Sub11.method603(var4, 13987, var2, var7, var1);
               Class62.method1224(var7, var4, var2);
               if(var7.anInt247 > var7.anInt240 - var7.anInt168) {
                  var7.anInt247 = var7.anInt240 - var7.anInt168;
               }

               if(var7.anInt208 > -var7.anInt193 + var7.anInt252) {
                  var7.anInt208 = var7.anInt252 - var7.anInt193;
               }

               if(var7.anInt208 < 0) {
                  var7.anInt208 = 0;
               }

               if(var7.anInt247 < 0) {
                  var7.anInt247 = 0;
               }

               if(var7.type == 0) {
                  Class151_Sub1.method2104(var7, var1, 235 + -343);
               }
            }
         }

      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "vk.A(" + var0 + ',' + var1 + ',' + var2 + ',' + 235 + ',' + var4 + ',' + (var5 != null?"{...}":"null") + ')');
      }
   }

   abstract void drawGraphics(int var1, int var2, int var4, Graphics var5, int var6);

   abstract void method2185(int var1, int var3, Component var4);

   static void method2186(Class25 var0) {
      for(int var1 = var0.anInt483; var1 <= var0.anInt495; ++var1) {
         for(int var2 = var0.anInt478; var2 <= var0.anInt481; ++var2) {
            Class3_Sub2 var3 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0.anInt493][var1][var2];
            if(var3 != null) {
               int var4;
               for(var4 = 0; var4 < var3.anInt2223; ++var4) {
                  if(var3.aClass25Array2221[var4] == var0) {
                     --var3.anInt2223;

                     for(int var5 = var4; var5 < var3.anInt2223; ++var5) {
                        var3.aClass25Array2221[var5] = var3.aClass25Array2221[var5 + 1];
                        var3.anIntArray2237[var5] = var3.anIntArray2237[var5 + 1];
                     }

                     var3.aClass25Array2221[var3.anInt2223] = null;
                     break;
                  }
               }

               var3.anInt2228 = 0;

               for(var4 = 0; var4 < var3.anInt2223; ++var4) {
                  var3.anInt2228 |= var3.anIntArray2237[var4];
               }
            }
         }
      }

   }

}
