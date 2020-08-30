package org.runite.jagex;

final class Class3_Sub13_Sub28 extends Class3_Sub13 {

   static Class3_Sub11[][] aClass3_Sub11ArrayArray3346;
   private int anInt3347 = 3216;
   private final int[] anIntArray3348 = new int[3];
   static short[] aShortArray3349 = new short[]{(short)-10304, (short)9104, (short)-1, (short)-1, (short)-1};
   private int anInt3350 = 4096;
   static int anInt3351;
   static CacheIndex aClass153_3352;
   static RSString aClass94_3353;
   private int anInt3354 = 3216;


   public static void method300(int var0) {
      try {
         aClass94_3353 = null;
         aShortArray3349 = null;
         aClass3_Sub11ArrayArray3346 = (Class3_Sub11[][])null;
         aClass153_3352 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "pk.F(" + var0 + ')');
      }
   }

   final void method157(int var1, RSByteBuffer var2, boolean var3) {
      try {
         if(0 == var1) {
            this.anInt3350 = var2.getShort();
         } else if(1 == var1) {
            this.anInt3347 = var2.getShort();
         } else if (var1 == 2) {
             this.anInt3354 = var2.getShort();
         }

         if(!var3) {
            method302(-47);
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "pk.A(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

   static int method301(byte var0) {
      try {
         //int var1 = -47 / ((45 - var0) / 57);
         return Class3_Sub13_Sub33.anInt3398;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "pk.E(" + var0 + ')');
      }
   }

   public Class3_Sub13_Sub28() {
      super(1, true);
   }

   final void method158(int var1) {
      try {
         if(var1 == 16251) {
            this.method303();
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "pk.P(" + var1 + ')');
      }
   }

   final int[] method154(int var1, byte var2) {
      try {
         int[] var4 = this.aClass114_2382.method1709(var1);
         if(this.aClass114_2382.aBoolean1580) {
            int var8 = Class95.anInt1343 * this.anInt3350 >> 12;
            int[] var9 = this.method152(0, Class3_Sub20.anInt2487 & var1 + -1, 32755);
            int[] var10 = this.method152(0, var1, 32755);
            int[] var11 = this.method152(0, 1 + var1 & Class3_Sub20.anInt2487, 32755);

            for(int var12 = 0; Class113.anInt1559 > var12; ++var12) {
               int var14 = (var10[RenderAnimationDefinition.anInt396 & -1 + var12] - var10[1 + var12 & RenderAnimationDefinition.anInt396]) * var8 >> 12;
               int var13 = var8 * (-var9[var12] + var11[var12]) >> 12;
               int var15 = var14 >> 4;
               if(0 > var15) {
                  var15 = -var15;
               }

               if(var15 > 255) {
                  var15 = 255;
               }

               int var16 = var13 >> 4;
               if(var16 < 0) {
                  var16 = -var16;
               }

               if(255 < var16) {
                  var16 = 255;
               }

               int var17 = Class97.aByteArray1364[(var16 * (var16 - -1) >> 1) + var15] & 255;
               int var6 = var13 * var17 >> 8;
               int var5 = var17 * var14 >> 8;
               var6 = var6 * this.anIntArray3348[1] >> 12;
               var5 = this.anIntArray3348[0] * var5 >> 12;
               int var7 = 4096 * var17 >> 8;
               var7 = var7 * this.anIntArray3348[2] >> 12;
               var4[var12] = var7 + var6 + var5;
            }
         }

         return var4;
      } catch (RuntimeException var18) {
         throw Class44.clientError(var18, "pk.D(" + var1 + ',' + var2 + ')');
      }
   }

   static void method302(int var0) {
      try {
         if(var0 != 2) {
            method301((byte)56);
         }

         for(Class3_Sub28_Sub19 var1 = (Class3_Sub28_Sub19)Class3_Sub13_Sub30.aClass61_3364.method1222(); var1 != null; var1 = (Class3_Sub28_Sub19)Class3_Sub13_Sub30.aClass61_3364.method1221()) {
            Class140_Sub6 var2 = var1.aClass140_Sub6_3778;
            if(var2.anInt2907 == WorldListCountry.localPlane && Class44.anInt719 <= var2.anInt2899) {
               if(Class44.anInt719 >= var2.anInt2925) {
                  if(var2.anInt2919 > 0) {
                     NPC var3 = Class3_Sub13_Sub24.npcs[-1 + var2.anInt2919];
                     if(null != var3 && var3.anInt2819 >= 0 && 13312 > var3.anInt2819 && var3.anInt2829 >= 0 && var3.anInt2829 < 13312) {
                        var2.method2024(var3.anInt2829, Class44.anInt719, Class121.method1736(var2.anInt2907, 1, var3.anInt2819, var3.anInt2829) + -var2.anInt2903, var3.anInt2819);
                     }
                  }

                  if(var2.anInt2919 < 0) {
                     int var4 = -1 + -var2.anInt2919;
                     Player var6;
                     if(Class3_Sub1.localIndex == var4) {
                        var6 = Class102.player;
                     } else {
                        var6 = Class3_Sub13_Sub22.players[var4];
                     }

                     if(null != var6 && var6.anInt2819 >= 0 && var6.anInt2819 < 13312 && var6.anInt2829 >= 0 && var6.anInt2829 < 13312) {
                        var2.method2024(var6.anInt2829, Class44.anInt719, Class121.method1736(var2.anInt2907, 1, var6.anInt2819, var6.anInt2829) - var2.anInt2903, var6.anInt2819);
                     }
                  }

                  var2.method2023(Class106.anInt1446);
                  Class20.method907(WorldListCountry.localPlane, (int)var2.aDouble2920, (int)var2.aDouble2900, (int)var2.aDouble2914, 60, var2, var2.anInt2924, -1L, false);
               }
            } else {
               var1.method86(-1024);
            }
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "pk.C(" + var0 + ')');
      }
   }

   private void method303() {
      try {
         double var2 = Math.cos((double)((float)this.anInt3354 / 4096.0F));
         this.anIntArray3348[0] = (int)(4096.0D * var2 * Math.sin((double)((float)this.anInt3347 / 4096.0F)));
          this.anIntArray3348[1] = (int)(Math.cos((double)((float)this.anInt3347 / 4096.0F)) * var2 * 4096.0D);
          this.anIntArray3348[2] = (int)(4096.0D * Math.sin((double)((float)this.anInt3354 / 4096.0F)));
          int var6 = this.anIntArray3348[2] * this.anIntArray3348[2] >> 12;
          int var5 = this.anIntArray3348[1] * this.anIntArray3348[1] >> 12;
          int var4 = this.anIntArray3348[0] * this.anIntArray3348[0] >> 12;
          int var7 = (int)(4096.0D * Math.sqrt((double)(var4 - (-var5 - var6) >> 12)));
          if(var7 != 0) {
             this.anIntArray3348[2] = (this.anIntArray3348[2] << 12) / var7;
             this.anIntArray3348[0] = (this.anIntArray3348[0] << 12) / var7;
             this.anIntArray3348[1] = (this.anIntArray3348[1] << 12) / var7;
          }

      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "pk.B(" + (byte) 59 + ')');
      }
   }

}
