package org.runite.jagex;

final class Class29 {

   static boolean aBoolean554 = false;
   int anInt556 = 0;
   static CacheIndex aClass153_557;
   static int[][][] anIntArrayArrayArray558;
   static int anInt561 = 0;


   static void method968(int var0) {
      try {
         int[] var1 = new int[Class3_Sub13_Sub23.itemDefinitionSize];
         int var2 = 0;

         int var3;
         for(var3 = 0; Class3_Sub13_Sub23.itemDefinitionSize > var3; ++var3) {
            ItemDefinition var4 = Class38.getItemDefinition(var3, (byte)119);
            if(var4.anInt793 >= 0 || var4.anInt761 >= 0) {
               var1[var2++] = var3;
            }
         }

         Class75_Sub4.anIntArray2664 = new int[var2];
         

         for(var3 = 0; var3 < var2; ++var3) {
            Class75_Sub4.anIntArray2664[var3] = var1[var3];
         }

         if(var0 != 128) {
            method968(19);
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "eh.A(" + var0 + ')');
      }
   }

   static void method969(CacheIndex var0, int var1) {
      try {
         Class12.aClass153_322 = var0;
         if(var1 < 39) {
            anInt561 = -82;
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "eh.D(" + (var0 != null?"{...}":"null") + ',' + var1 + ')');
      }
   }

   final void method970(RSByteBuffer var2) {
      try {
         while(true) {
            int var3 = var2.getByteB();
            if(0 == var3) {

                return;
            }

            this.method972(var2, var3);
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "eh.B(" + -20638 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   static void method971(int var0, int[][] var1, float[][] var2, int var3, float[][] var4, int var5, byte var6, int var7, int var8, boolean var10, byte var11, int var12, float[][] var13, int var14, Class3_Sub11 var15) {
      try {
         int var16 = 255 + (var0 << 8);
         int var17 = (var5 << 8) - -255;
         int var18 = (var8 << 8) - -255;
         int var19 = (var14 << 8) - -255;
         int[] var20 = Class134.anIntArrayArray1763[var11];
         int[] var22 = new int[var20.length >> 1];

         int var23;
         for(var23 = 0; var23 < var22.length; ++var23) {
            var22[var23] = Class121.method1734(var19, 0.0F, var16, var17, (int[][])null, var1, var3, var13, var18, (byte)-125, var6, false, var15, var2, var12, var20[var23 + var23], var4, var20[var23 + var23 + 1]);
         }

          int[] var21 = null;
          if(var10) {
             int var24;
             if(1 == var11) {
                var23 = Class121.method1734(var19, 0.0F, var16, var17, (int[][])null, var1, var3, var13, var18, (byte)-122, var6, true, var15, var2, var12, 64, var4, 128);
                var24 = Class121.method1734(var19, 0.0F, var16, var17, (int[][])null, var1, var3, var13, var18, (byte)-114, var6, true, var15, var2, var12, 128, var4, 64);
                var21 = new int[]{var24, var23, var22[2], var23, var22[0], var22[2]};
             } else if(2 == var11) {
                var21 = new int[6];
                var23 = Class121.method1734(var19, 0.0F, var16, var17, (int[][])null, var1, var3, var13, var18, (byte)-121, var6, true, var15, var2, var12, 128, var4, 128);
                var24 = Class121.method1734(var19, 0.0F, var16, var17, (int[][])null, var1, var3, var13, var18, (byte)-128, var6, true, var15, var2, var12, 64, var4, 0);
                var21[2] = var23;
                var21[0] = var22[0];
                var21[5] = var22[0];
                var21[3] = var23;
                var21[1] = var24;
                var21[4] = var22[1];
             } else if(var11 == 3) {
                var21 = new int[6];
                var23 = Class121.method1734(var19, 0.0F, var16, var17, (int[][])null, var1, var3, var13, var18, (byte)-114, var6, true, var15, var2, var12, 0, var4, 128);
                var24 = Class121.method1734(var19, 0.0F, var16, var17, (int[][])null, var1, var3, var13, var18, (byte)-115, var6, true, var15, var2, var12, 64, var4, 0);
                var21[4] = var24;
                var21[1] = var22[1];
                var21[0] = var22[2];
                var21[3] = var23;
                var21[2] = var23;
                var21[5] = var22[2];
             } else if (var11 == 4) {
                var21 = new int[3];
                var23 = Class121.method1734(var19, 0.0F, var16, var17, (int[][]) null, var1, var3, var13, var18, (byte) -115, var6, true, var15, var2, var12, 0, var4, 128);
                var21[0] = var22[3];
                var21[2] = var22[0];
                var21[1] = var23;
             } else if (var11 == 5) {
                var21 = new int[3];
                var23 = Class121.method1734(var19, 0.0F, var16, var17, (int[][]) null, var1, var3, var13, var18, (byte) -112, var6, true, var15, var2, var12, 128, var4, 128);
                var21[1] = var23;
                var21[0] = var22[2];
                var21[2] = var22[3];
             } else if (var11 == 6) {
                var21 = new int[6];
                var23 = Class121.method1734(var19, 0.0F, var16, var17, (int[][]) null, var1, var3, var13, var18, (byte) -123, var6, true, var15, var2, var12, 128, var4, 0);
                var24 = Class121.method1734(var19, 0.0F, var16, var17, (int[][]) null, var1, var3, var13, var18, (byte) -115, var6, true, var15, var2, var12, 128, var4, 128);
                var21[1] = var23;
                var21[0] = var22[3];
                var21[2] = var24;
                var21[4] = var22[0];
                var21[3] = var24;
                var21[5] = var22[3];
             } else if (var11 == 7) {
                var21 = new int[6];
                var23 = Class121.method1734(var19, 0.0F, var16, var17, (int[][]) null, var1, var3, var13, var18, (byte) -126, var6, true, var15, var2, var12, 0, var4, 128);
                var24 = Class121.method1734(var19, 0.0F, var16, var17, (int[][]) null, var1, var3, var13, var18, (byte) -117, var6, true, var15, var2, var12, 128, var4, 0);
                var21[3] = var23;
                var21[2] = var23;
                var21[0] = var22[1];
                var21[4] = var22[2];
                var21[1] = var24;
                var21[5] = var22[1];
             } else if (var11 == 8) {
                var21 = new int[3];
                var23 = Class121.method1734(var19, 0.0F, var16, var17, (int[][]) null, var1, var3, var13, var18, (byte) -117, var6, true, var15, var2, var12, 0, var4, 0);
                var21[2] = var22[4];
                var21[0] = var22[3];
                var21[1] = var23;
             } else if (var11 == 9) {
                var23 = Class121.method1734(var19, 0.0F, var16, var17, (int[][]) null, var1, var3, var13, var18, (byte) -118, var6, true, var15, var2, var12, 128, var4, 64);
                var24 = Class121.method1734(var19, 0.0F, var16, var17, (int[][]) null, var1, var3, var13, var18, (byte) -121, var6, true, var15, var2, var12, 96, var4, 32);
                int var25 = Class121.method1734(var19, 0.0F, var16, var17, (int[][]) null, var1, var3, var13, var18, (byte) -120, var6, true, var15, var2, var12, 64, var4, 0);
                var21 = new int[]{var24, var23, var22[4], var24, var22[4], var22[3], var24, var22[3], var22[2], var24, var22[2], var22[1], var24, var22[1], var25};
             } else if (10 == var11) {
                var21 = new int[9];
                var23 = Class121.method1734(var19, 0.0F, var16, var17, (int[][]) null, var1, var3, var13, var18, (byte) -120, var6, true, var15, var2, var12, 0, var4, 128);
                var21[0] = var22[2];
                var21[8] = var22[0];
                var21[1] = var23;
                var21[4] = var23;
                var21[2] = var22[3];
                var21[7] = var23;
                var21[3] = var22[3];
                var21[5] = var22[4];
                var21[6] = var22[4];
             } else if (var11 == 11) {
                var21 = new int[12];
                var23 = Class121.method1734(var19, 0.0F, var16, var17, (int[][]) null, var1, var3, var13, var18, (byte) -119, var6, true, var15, var2, var12, 0, var4, 64);
                var24 = Class121.method1734(var19, 0.0F, var16, var17, (int[][]) null, var1, var3, var13, var18, (byte) -121, var6, true, var15, var2, var12, 128, var4, 64);
                var21[5] = var23;
                var21[1] = var23;
                var21[8] = var23;
                var21[0] = var22[3];
                var21[2] = var22[0];
                var21[11] = var24;
                var21[6] = var22[2];
                var21[7] = var24;
                var21[10] = var22[1];
                var21[3] = var22[3];
                var21[4] = var22[2];
                var21[9] = var22[2];
             }
          }

          var15.method150(var7, var3, var12, var22, var21, false);
      } catch (RuntimeException var26) {
         throw Class44.clientError(var26, "eh.C(" + var0 + ',' + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ',' + (var4 != null?"{...}":"null") + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + (byte) 88 + ',' + var10 + ',' + var11 + ',' + var12 + ',' + (var13 != null?"{...}":"null") + ',' + var14 + ',' + (var15 != null?"{...}":"null") + ')');
      }
   }

   private void method972(RSByteBuffer var1, int var3) {
      try {

          if(var3 == 5) {
            this.anInt556 = var1.getShort();
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "eh.F(" + (var1 != null?"{...}":"null") + ',' + (byte) -117 + ',' + var3 + ')');
      }
   }

   public static void method973(byte var0) {
      try {
         aClass153_557 = null;
         anIntArrayArrayArray558 = (int[][][])null;
         if(var0 != 62) {
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "eh.E(" + var0 + ')');
      }
   }

}