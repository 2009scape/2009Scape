package org.runite.jagex;

import org.runite.Configurations;


final class ISAACCipher {

   private int anInt966;
   private int anInt967;
   private int anInt968;
   private int[] anIntArray970;
   private int[] anIntArray971;
   private int anInt972;


   private void method1227() {
      try {

         int var10 = -1640531527;
         int var9 = -1640531527;
         int var8 = -1640531527;
         int var7 = -1640531527;
         int var6 = -1640531527;
         int var5 = -1640531527;
         int var4 = -1640531527;
         int var3 = -1640531527;

         int var2;
         for(var2 = 0; 4 > var2; ++var2) {
            var3 ^= var4 << 11;
            var6 += var3;
            var4 += var5;
            var4 ^= var5 >>> 2;
            var5 += var6;
            var5 ^= var6 << 8;
            var8 += var5;
            var7 += var4;
            var6 += var7;
            var6 ^= var7 >>> 16;
            var7 += var8;
            var9 += var6;
            var7 ^= var8 << 10;
            var10 += var7;
            var8 += var9;
            var8 ^= var9 >>> 4;
            var9 += var10;
            var9 ^= var10 << 8;
            var4 += var9;
            var3 += var8;
            var10 += var3;
            var10 ^= var3 >>> 9;
            var5 += var10;
            var3 += var4;
         }

         for(var2 = 0; var2 < 256; var2 += 8) {
            var6 += this.anIntArray970[3 + var2];
            var7 += this.anIntArray970[var2 - -4];
            var9 += this.anIntArray970[var2 + 6];
            var3 += this.anIntArray970[var2];
            var5 += this.anIntArray970[2 + var2];
            var8 += this.anIntArray970[var2 - -5];
            var10 += this.anIntArray970[7 + var2];
            var4 += this.anIntArray970[var2 - -1];
            var3 ^= var4 << 11;
            var4 += var5;
            var4 ^= var5 >>> 2;
            var6 += var3;
            var5 += var6;
            var5 ^= var6 << 8;
            var7 += var4;
            var6 += var7;
            var6 ^= var7 >>> 16;
            var8 += var5;
            var7 += var8;
            var7 ^= var8 << 10;
            var10 += var7;
            var9 += var6;
            var8 += var9;
            var8 ^= var9 >>> 4;
            var9 += var10;
            var9 ^= var10 << 8;
            var3 += var8;
            var10 += var3;
            var4 += var9;
            var10 ^= var3 >>> 9;
            var3 += var4;
            this.anIntArray971[var2] = var3;
            this.anIntArray971[1 + var2] = var4;
            var5 += var10;
            this.anIntArray971[2 + var2] = var5;
            this.anIntArray971[var2 + 3] = var6;
            this.anIntArray971[4 + var2] = var7;
            this.anIntArray971[5 + var2] = var8;
            this.anIntArray971[var2 - -6] = var9;
            this.anIntArray971[7 + var2] = var10;
         }

         for(var2 = 0; var2 < 256; var2 += 8) {
            var9 += this.anIntArray971[6 + var2];
            var8 += this.anIntArray971[var2 + 5];
            var7 += this.anIntArray971[4 + var2];
            var4 += this.anIntArray971[var2 + 1];
            var5 += this.anIntArray971[2 + var2];
            var3 += this.anIntArray971[var2];
            var3 ^= var4 << 11;
            var6 += this.anIntArray971[var2 + 3];
            var10 += this.anIntArray971[var2 + 7];
            var6 += var3;
            var4 += var5;
            var4 ^= var5 >>> 2;
            var5 += var6;
            var7 += var4;
            var5 ^= var6 << 8;
            var8 += var5;
            var6 += var7;
            var6 ^= var7 >>> 16;
            var7 += var8;
            var7 ^= var8 << 10;
            var9 += var6;
            var8 += var9;
            var8 ^= var9 >>> 4;
            var10 += var7;
            var9 += var10;
            var3 += var8;
            var9 ^= var10 << 8;
            var4 += var9;
            var10 += var3;
            var10 ^= var3 >>> 9;
            var5 += var10;
            var3 += var4;
            this.anIntArray971[var2] = var3;
            this.anIntArray971[1 + var2] = var4;
            this.anIntArray971[var2 - -2] = var5;
            this.anIntArray971[3 + var2] = var6;
            this.anIntArray971[4 + var2] = var7;
            this.anIntArray971[5 + var2] = var8;
            this.anIntArray971[var2 + 6] = var9;
            this.anIntArray971[var2 - -7] = var10;
         }

         this.method1229(-1879);
         this.anInt968 = 256;
      } catch (RuntimeException var11) {
         throw Class44.clientError(var11, "ij.E(" + true + ')');
      }
   }

   final int nextOpcode(int var1) {
      try {
         if(this.anInt968-- == 0) {
            this.method1229(-1879);
            this.anInt968 = 255;
         }

         if(var1 != -9356) {
            this.nextOpcode(-128);
         }

         return Configurations.USEISAAC ? this.anIntArray970[this.anInt968] : 0;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ij.C(" + var1 + ')');
      }
   }

   private void method1229(int var1) {
      try {
         this.anInt967 += ++this.anInt966;
         int var2 = 0;
         if(var1 != -1879) {
            this.anInt968 = -98;
         }

         while(var2 < 256) {
            int var3 = this.anIntArray971[var2];
            if((2 & var2) != 0) {
               if((var2 & 1) == 0) {
                  this.anInt972 ^= this.anInt972 << 2;
               } else {
                  this.anInt972 ^= this.anInt972 >>> 16;
               }
            } else if((var2 & 1) == 0) {
               this.anInt972 ^= this.anInt972 << 13;
            } else {
               this.anInt972 ^= this.anInt972 >>> 6;
            }

            this.anInt972 += this.anIntArray971[128 + var2 & 255];
            int var4;
            this.anIntArray971[var2] = var4 = this.anInt967 + this.anInt972 + this.anIntArray971[Class69.bitwiseAnd(var3, 1020) >> 2];
            this.anIntArray970[var2] = this.anInt967 = var3 + this.anIntArray971[Class69.bitwiseAnd(261347, var4) >> 8 >> 2];
            ++var2;
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "ij.A(" + var1 + ')');
      }
   }

   static void method1230(byte var0) {
      try {
         int var1 = AbstractIndexedSprite.anInt1462;
         int var2 = Class3_Sub13_Sub33.anInt3395;
         int var4 = Class3_Sub28_Sub1.anInt3537;
         int var3 = Class3_Sub28_Sub3.anInt3552;
         if(Class3_Sub13_Sub7.aClass3_Sub28_Sub16_3099 == null || null == Class108.aClass3_Sub28_Sub16_1457) {
            if(Class140_Sub6.spritesCacheIndex.method2144(WorldListCountry.archiveID) && Class140_Sub6.spritesCacheIndex.method2144(Class99.anInt1400)) {
               Class3_Sub13_Sub7.aClass3_Sub28_Sub16_3099 = Class3_Sub28_Sub7.method562(Class140_Sub6.spritesCacheIndex, WorldListCountry.archiveID);
               Class108.aClass3_Sub28_Sub16_1457 = Class3_Sub28_Sub7.method562(Class140_Sub6.spritesCacheIndex, Class99.anInt1400);
               if(HDToolKit.highDetail) {
                  if(Class3_Sub13_Sub7.aClass3_Sub28_Sub16_3099 instanceof Class3_Sub28_Sub16_Sub2_Sub1) {
                     Class3_Sub13_Sub7.aClass3_Sub28_Sub16_3099 = new Class3_Sub28_Sub16_Sub1_Sub1((Class3_Sub28_Sub16_Sub2)Class3_Sub13_Sub7.aClass3_Sub28_Sub16_3099);
                  } else {
                     Class3_Sub13_Sub7.aClass3_Sub28_Sub16_3099 = new Class3_Sub28_Sub16_Sub1((Class3_Sub28_Sub16_Sub2)Class3_Sub13_Sub7.aClass3_Sub28_Sub16_3099);
                  }

                  if(Class108.aClass3_Sub28_Sub16_1457 instanceof Class3_Sub28_Sub16_Sub2_Sub1) {
                     Class108.aClass3_Sub28_Sub16_1457 = new Class3_Sub28_Sub16_Sub1_Sub1((Class3_Sub28_Sub16_Sub2)Class108.aClass3_Sub28_Sub16_1457);
                  } else {
                     Class108.aClass3_Sub28_Sub16_1457 = new Class3_Sub28_Sub16_Sub1((Class3_Sub28_Sub16_Sub2)Class108.aClass3_Sub28_Sub16_1457);
                  }
               }
            } else if(HDToolKit.highDetail) {
               Class22.method930(var1, var2, var3, 20, Class3_Sub28_Sub6.anInt3600, -Class62.anInt963 + 256);
            } else {
               Class74.method1312(var1, var2, var3, 20, Class3_Sub28_Sub6.anInt3600, -Class62.anInt963 + 256);
            }
         }

         int var5;
         int var6;
         if(Class3_Sub13_Sub7.aClass3_Sub28_Sub16_3099 != null && Class108.aClass3_Sub28_Sub16_1457 != null) {
            var5 = var3 / Class3_Sub13_Sub7.aClass3_Sub28_Sub16_3099.anInt3707;

            for(var6 = 0; var6 < var5; ++var6) {
               Class3_Sub13_Sub7.aClass3_Sub28_Sub16_3099.method643(var6 * Class3_Sub13_Sub7.aClass3_Sub28_Sub16_3099.anInt3707 + var1, var2);
            }

            Class108.aClass3_Sub28_Sub16_1457.method643(var1, var2);
            Class108.aClass3_Sub28_Sub16_1457.method641(-Class108.aClass3_Sub28_Sub16_1457.anInt3707 + (var1 - -var3), var2);
         }

         Class168.aClass3_Sub28_Sub17_2096.method681(TextCore.HasChooseOptions, var1 - -3, 14 + var2, Class3_Sub13_Sub28.anInt3351, -1);
         if(HDToolKit.highDetail) {
            Class22.method930(var1, 20 + var2, var3, var4 - 20, Class3_Sub28_Sub6.anInt3600, -Class62.anInt963 + 256);
         } else {
            Class74.method1312(var1, 20 + var2, var3, -20 + var4, Class3_Sub28_Sub6.anInt3600, -Class62.anInt963 + 256);
         }

         var6 = Class130.anInt1709;
         var5 = Class126.anInt1676;
         int var7;
         int var8;
         for(var7 = 0; Class3_Sub13_Sub34.anInt3415 > var7; ++var7) {
            var8 = (-var7 + Class3_Sub13_Sub34.anInt3415 - 1) * 15 + var2 + 35;
            if(var1 < var5 && var5 < var1 - -var3 && -13 + var8 < var6 && var8 + 3 > var6) {
               if(HDToolKit.highDetail) {
                  Class22.method930(var1, var8 + -13, var3, 16, MouseListeningClass.anInt1926, -Class136.anInt1771 + 256);
               } else {
                  Class74.method1312(var1, var8 + -13, var3, 16, MouseListeningClass.anInt1926, -Class136.anInt1771 + 256);
               }
            }
         }

         if((Class95.aClass3_Sub28_Sub16_1339 == null || Class50.aClass3_Sub28_Sub16_824 == null || null == Class3_Sub26.aClass3_Sub28_Sub16_2560) && Class140_Sub6.spritesCacheIndex.method2144(Class46.anInt739) && Class140_Sub6.spritesCacheIndex.method2144(Class79.anInt1126) && Class140_Sub6.spritesCacheIndex.method2144(Class140_Sub7.anInt2937)) {
            Class95.aClass3_Sub28_Sub16_1339 = Class3_Sub28_Sub7.method562(Class140_Sub6.spritesCacheIndex, Class46.anInt739);
            Class50.aClass3_Sub28_Sub16_824 = Class3_Sub28_Sub7.method562(Class140_Sub6.spritesCacheIndex, Class79.anInt1126);
            Class3_Sub26.aClass3_Sub28_Sub16_2560 = Class3_Sub28_Sub7.method562(Class140_Sub6.spritesCacheIndex, Class140_Sub7.anInt2937);
            if(HDToolKit.highDetail) {
               if(Class95.aClass3_Sub28_Sub16_1339 instanceof Class3_Sub28_Sub16_Sub2_Sub1) {
                  Class95.aClass3_Sub28_Sub16_1339 = new Class3_Sub28_Sub16_Sub1_Sub1((Class3_Sub28_Sub16_Sub2)Class95.aClass3_Sub28_Sub16_1339);
               } else {
                  Class95.aClass3_Sub28_Sub16_1339 = new Class3_Sub28_Sub16_Sub1((Class3_Sub28_Sub16_Sub2)Class95.aClass3_Sub28_Sub16_1339);
               }

               if(Class50.aClass3_Sub28_Sub16_824 instanceof Class3_Sub28_Sub16_Sub2_Sub1) {
                  Class50.aClass3_Sub28_Sub16_824 = new Class3_Sub28_Sub16_Sub1_Sub1((Class3_Sub28_Sub16_Sub2)Class50.aClass3_Sub28_Sub16_824);
               } else {
                  Class50.aClass3_Sub28_Sub16_824 = new Class3_Sub28_Sub16_Sub1((Class3_Sub28_Sub16_Sub2)Class50.aClass3_Sub28_Sub16_824);
               }

               if(Class3_Sub26.aClass3_Sub28_Sub16_2560 instanceof Class3_Sub28_Sub16_Sub2_Sub1) {
                  Class3_Sub26.aClass3_Sub28_Sub16_2560 = new Class3_Sub28_Sub16_Sub1_Sub1((Class3_Sub28_Sub16_Sub2)Class3_Sub26.aClass3_Sub28_Sub16_2560);
               } else {
                  Class3_Sub26.aClass3_Sub28_Sub16_2560 = new Class3_Sub28_Sub16_Sub1((Class3_Sub28_Sub16_Sub2)Class3_Sub26.aClass3_Sub28_Sub16_2560);
               }
            }
         }

         int var9;
         if(Class95.aClass3_Sub28_Sub16_1339 != null && null != Class50.aClass3_Sub28_Sub16_824 && null != Class3_Sub26.aClass3_Sub28_Sub16_2560) {
            var7 = var3 / Class95.aClass3_Sub28_Sub16_1339.anInt3707;

            for(var8 = 0; var7 > var8; ++var8) {
               Class95.aClass3_Sub28_Sub16_1339.method643(var1 + Class95.aClass3_Sub28_Sub16_1339.anInt3707 * var8, var4 + var2 + -Class95.aClass3_Sub28_Sub16_1339.anInt3696);
            }

            var8 = (-20 + var4) / Class50.aClass3_Sub28_Sub16_824.anInt3696;

            for(var9 = 0; var9 < var8; ++var9) {
               Class50.aClass3_Sub28_Sub16_824.method643(var1, var2 + 20 + var9 * Class50.aClass3_Sub28_Sub16_824.anInt3696);
               Class50.aClass3_Sub28_Sub16_824.method641(var1 - (-var3 - -Class50.aClass3_Sub28_Sub16_824.anInt3707), var2 + 20 + var9 * Class50.aClass3_Sub28_Sub16_824.anInt3696);
            }

            Class3_Sub26.aClass3_Sub28_Sub16_2560.method643(var1, var4 + (var2 - Class3_Sub26.aClass3_Sub28_Sub16_2560.anInt3696));
            Class3_Sub26.aClass3_Sub28_Sub16_2560.method641(var1 + var3 - Class3_Sub26.aClass3_Sub28_Sub16_2560.anInt3707, var2 - -var4 + -Class3_Sub26.aClass3_Sub28_Sub16_2560.anInt3696);
         }

         for(var7 = 0; var7 < Class3_Sub13_Sub34.anInt3415; ++var7) {
            var8 = 15 * (Class3_Sub13_Sub34.anInt3415 - 1 + -var7) + var2 + 35;
            var9 = Class3_Sub13_Sub28.anInt3351;
            if(var1 < var5 && var3 + var1 > var5 && var6 > var8 - 13 && var8 - -3 > var6) {
               var9 = Class154.anInt1957;
            }

            Class168.aClass3_Sub28_Sub17_2096.method681(RSByteBuffer.method802(var7), 3 + var1, var8, var9, 0);
         }

         Class69.method1282(AbstractIndexedSprite.anInt1462, (byte)122, Class3_Sub13_Sub33.anInt3395, Class3_Sub28_Sub1.anInt3537, Class3_Sub28_Sub3.anInt3552);
      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "ij.F(" + var0 + ')');
      }
   }

   public static void method1231(int var0) {
      try {
         if(var0 <= 116) {
            method1230((byte)-73);
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ij.D(" + var0 + ')');
      }
   }

   static int method1232(NPC var0) {
      try {
         NPCDefinition var2 = var0.definition;
         if(null != var2.childNPCs) {
            var2 = var2.method1471((byte)-108);
            if(var2 == null) {
               return -1;
            }
         }

         int var3 = var2.anInt1293;
         RenderAnimationDefinition var4 = var0.method1965();
         if(var0.anInt2764 == var4.anInt368) {
            var3 = var2.anInt1262;
         } else if(var4.anInt393 != var0.anInt2764 && var4.anInt386 != var0.anInt2764 && var0.anInt2764 != var4.anInt375 && var0.anInt2764 != var4.anInt373) {
            if(var0.anInt2764 == var4.anInt398 || var0.anInt2764 == var4.anInt372 || var4.anInt379 == var0.anInt2764 || var4.anInt406 == var0.anInt2764) {
               var3 = var2.anInt1290;
            }
         } else {
            var3 = var2.anInt1276;
         }

         return var3;
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "ij.B(" + (var0 != null?"{...}":"null") + ',' + -1 + ')');
      }
   }

   private ISAACCipher() {}

   ISAACCipher(int[] var1) {
      try {
         this.anIntArray970 = new int[256];
         this.anIntArray971 = new int[256];

          System.arraycopy(var1, 0, this.anIntArray970, 0, var1.length);

         this.method1227();
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ij.<init>(" + (var1 != null?"{...}":"null") + ')');
      }
   }

}
