package org.runite.jagex;

final class Class56 {

   static Class3_Sub26 aClass3_Sub26_884 = new Class3_Sub26(0, 0);
   static Class47 aClass47_885 = new Class47(128);
   static RSInterface aClass11_886 = null;
   static int[] localPlayerIndexes = new int[2048];
   static Class106[] aClass106Array890;
   
   static RSString aClass94_892 = RSString.createRSString(" )2> <col=ffffff>");
   static int anInt893 = 0;

   static int method1186(int var1) {
      try {
         double var2 = (double)(255 & var1 >> 16) / 256.0D;
         double var4 = (double)(255 & var1 >> 8) / 256.0D;
         double var12 = 0.0D;
         double var6 = (double)(255 & var1) / 256.0D;
         double var8 = var2;
         double var14 = (double) 0;
         double var10 = var2;
         if(var2 > var4) {
            var8 = var4;
         }

         if(var6 < var8) {
            var8 = var6;
         }

         if(var4 > var2) {
            var10 = var4;
         }

         if(var6 > var10) {
            var10 = var6;
         }

         double var16 = (var8 + var10) / 2.0D;
         if(var8 != var10) {
            if(0.5D > var16) {
               var14 = (-var8 + var10) / (var8 + var10);
            }

            if(var16 >= 0.5D) {
               var14 = (-var8 + var10) / (2.0D - var10 - var8);
            }

            if(var10 == var2) {
               var12 = (var4 - var6) / (-var8 + var10);
            } else if(var4 == var10) {
               var12 = 2.0D + (-var2 + var6) / (var10 - var8);
            } else if(var10 == var6) {
               var12 = 4.0D + (-var4 + var2) / (-var8 + var10);
            }
         }

         int var19 = (int)(var14 * 256.0D);
         int var20 = (int)(256.0D * var16);
         var12 /= 6.0D;
         if(0 > var20) {
            var20 = 0;
         } else if(var20 > 255) {
            var20 = 255;
         }

         int var18 = (int)(var12 * 256.0D);
         if(var19 >= 0) {
            if(var19 > 255) {
               var19 = 255;
            }
         } else {
            var19 = 0;
         }

         if(var20 <= 243) {
            if(var20 <= 217) {
               if(var20 > 192) {
                  var19 >>= 2;
               } else if(var20 > 179) {
                  var19 >>= 1;
               }
            } else {
               var19 >>= 3;
            }
         } else {
            var19 >>= 4;
         }

         return (var18 >> 2 << 10) + (var19 >> 5 << 7) + (var20 >> 1);
      } catch (RuntimeException var21) {
         throw Class44.clientError(var21, "ib.A(" + 0 + ',' + var1 + ')');
      }
   }

   public static void method1187(int var0) {
      try {
         if(var0 != 30351) {
            method1189(-73, -127, -26, 43, 67, 125, 38, 80, (GameObject)null, -92, true, 27L);
         }
         aClass94_892 = null;
         aClass11_886 = null;
         localPlayerIndexes = null;
         aClass47_885 = null;
         aClass106Array890 = null;
         aClass3_Sub26_884 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ib.B(" + var0 + ')');
      }
   }

   static void method1188() {
      try {
         MouseListeningClass.anIntArray1920 = null;
         Class38_Sub1.anIntArrayArrayArray2609 = (int[][][])null;
         Class3_Sub18.anIntArray2469 = null;
         Class93.aByteArrayArrayArray1328 = (byte[][][])null;
         PacketParser.aByteArrayArrayArray81 = (byte[][][])null;
         Class67.aByteArrayArrayArray1014 = (byte[][][])null;
         Class139.aByteArrayArrayArray1828 = (byte[][][])null;
         Class3_Sub13_Sub36.aByteArrayArrayArray3430 = (byte[][][])null;
         Class80.anIntArray1138 = null;
         Class129.anIntArray1695 = null;
         Class3_Sub31.anIntArray2606 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ib.D(" + -113 + ')');
      }
   }

   static boolean method1189(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, GameObject var8, int var9, boolean var10, long var11) {
      boolean var13 = Class44.anIntArrayArrayArray723 == Class3_Sub28_Sub7.anIntArrayArrayArray3605;
      int var14 = 0;

      int var16;
      for(int var15 = var1; var15 < var1 + var3; ++var15) {
         for(var16 = var2; var16 < var2 + var4; ++var16) {
            if(var15 < 0 || var16 < 0 || var15 >= IOHandler.anInt1234 || var16 >= Class3_Sub13_Sub15.anInt3179) {
               return false;
            }

            Class3_Sub2 var17 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var15][var16];
            if(var17 != null && var17.anInt2223 >= 5) {//@splinter
               return false;
            }
         }
      }

      Class25 var20 = new Class25();
      var20.aLong498 = var11;
      var20.anInt493 = var0;
      var20.anInt482 = var5;
      var20.anInt484 = var6;
      var20.anInt489 = var7;
      var20.aClass140_479 = var8;
      var20.anInt496 = var9;
      var20.anInt483 = var1;
      var20.anInt478 = var2;
      var20.anInt495 = var1 + var3 - 1;
      var20.anInt481 = var2 + var4 - 1;

      int var21;
      for(var16 = var1; var16 < var1 + var3; ++var16) {
         for(var21 = var2; var21 < var2 + var4; ++var21) {
            int var18 = 0;
            if(var16 > var1) {
               ++var18;
            }

            if(var16 < var1 + var3 - 1) {
               var18 += 4;
            }

            if(var21 > var2) {
               var18 += 8;
            }

            if(var21 < var2 + var4 - 1) {
               var18 += 2;
            }

            for(int var19 = var0; var19 >= 0; --var19) {
               if(Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var19][var16][var21] == null) {
                  Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var19][var16][var21] = new Class3_Sub2(var19, var16, var21);
               }
            }

            Class3_Sub2 var22 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var16][var21];
            var22.aClass25Array2221[var22.anInt2223] = var20;
            var22.anIntArray2237[var22.anInt2223] = var18;
            var22.anInt2228 |= var18;
            ++var22.anInt2223;
            if(var13 && Class3_Sub13_Sub9.anIntArrayArray3115[var16][var21] != 0) {
               var14 = Class3_Sub13_Sub9.anIntArrayArray3115[var16][var21];
            }
         }
      }

      if(var13 && var14 != 0) {
         for(var16 = var1; var16 < var1 + var3; ++var16) {
            for(var21 = var2; var21 < var2 + var4; ++var21) {
               if(Class3_Sub13_Sub9.anIntArrayArray3115[var16][var21] == 0) {
                  Class3_Sub13_Sub9.anIntArrayArray3115[var16][var21] = var14;
               }
            }
         }
      }

      if(var10) {
         AnimationDefinition.aClass25Array1868[Class3_Sub13_Sub5.anInt3070++] = var20;
      }

      return true;
   }

}
