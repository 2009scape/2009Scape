package org.runite.jagex;

final class Class157 {

   
   static int anInt1996;
   private int anInt1997;
   private static RSString aClass94_1998 = RSString.createRSString("Connecting.. This takes a LONG time.");
   private int[][] anIntArrayArray1999;
   static Class3_Sub28_Sub17_Sub1 aClass3_Sub28_Sub17_Sub1_2000;
   private int anInt2001;
   static RSString aClass94_2002 = RSString.createRSString("Chargement des fichiers config )2 ");
   static RSString aClass94_2003 = RSString.createRSString("_labels");
static RSString aClass94_1995 = aClass94_1998;

   final byte[] method2173(byte[] var1, byte var2) {
      try {
         int var3 = 53 % ((var2 - -39) / 50);
         if(null != this.anIntArrayArray1999) {
            int var4 = 14 + (int)((long)var1.length * (long)this.anInt2001 / (long)this.anInt1997);
            int[] var5 = new int[var4];
            int var6 = 0;
            int var7 = 0;

            int var8;
            for(var8 = 0; ~var8 > ~var1.length; ++var8) {
               int[] var10 = this.anIntArrayArray1999[var7];
               byte var9 = var1[var8];

               int var11;
               for(var11 = 0; ~var11 > -15; ++var11) {
                  var5[var6 - -var11] += var10[var11] * var9;
               }

               var7 += this.anInt2001;
               var11 = var7 / this.anInt1997;
               var6 += var11;
               var7 -= var11 * this.anInt1997;
            }

            var1 = new byte[var4];

            for(var8 = 0; var4 > var8; ++var8) {
               int var13 = var5[var8] - -32768 >> 16;
               if(127 >= ~var13) {
                  if(127 >= var13) {
                     var1[var8] = (byte)var13;
                  } else {
                     var1[var8] = 127;
                  }
               } else {
                  var1[var8] = -128;
               }
            }
         }

         return var1;
      } catch (RuntimeException var12) {
         throw Class44.method1067(var12, "vj.E(" + (var1 != null?"{...}":"null") + ',' + var2 + ')');
      }
   }

   static final long method2174(int var0, int var1, int var2) {
      Class3_Sub2 var3 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2];
      return var3 != null && var3.aClass70_2234 != null?var3.aClass70_2234.aLong1048:0L;
   }

   public static void method2175(byte var0) {
      try {
         aClass94_2002 = null;
         aClass94_1995 = null;
         aClass3_Sub28_Sub17_Sub1_2000 = null;
         if(var0 == -110) {
            aClass94_2003 = null;
            aClass94_1998 = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.method1067(var2, "vj.F(" + var0 + ')');
      }
   }

   static final Class3_Sub28_Sub16_Sub2[] method2176(int var0, int var1, int var2, CacheIndex var3) {
      try {
    	  //System.out.println("Class 157 " + var2);
         if(Class75_Sub4.method1351(var3, var0, var2, -30901)) {
            if(var1 != 32767) {
               method2176(-111, 55, -18, (CacheIndex)null);
            }

            return Class10.method851(true);
         } else {
            return null;
         }
      } catch (RuntimeException var5) {
         throw Class44.method1067(var5, "vj.A(" + var0 + ',' + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ')');
      }
   }

   final int method2177(int var1, byte var2) {
      try {
         if(null != this.anIntArrayArray1999) {
            var1 = (int)((long)this.anInt2001 * (long)var1 / (long)this.anInt1997);
         }

         return var2 != 90?81:var1;
      } catch (RuntimeException var4) {
         throw Class44.method1067(var4, "vj.C(" + var1 + ',' + var2 + ')');
      }
   }

   final int method2178(boolean var1, int var2) {
      try {
         if(var1) {
            return -69;
         } else {
            if(null != this.anIntArrayArray1999) {
               var2 = (int)((long)this.anInt2001 * (long)var2 / (long)this.anInt1997) + 6;
            }

            return var2;
         }
      } catch (RuntimeException var4) {
         throw Class44.method1067(var4, "vj.D(" + var1 + ',' + var2 + ')');
      }
   }

   Class157(int var1, int var2) {
      try {
         if(var2 != var1) {
            int var3 = Class107.method1651(19067, var2, var1);
            var2 /= var3;
            this.anInt2001 = var2;
            var1 /= var3;
            this.anIntArrayArray1999 = new int[var1][14];
            this.anInt1997 = var1;

            for(int var4 = 0; var4 < var1; ++var4) {
               int[] var5 = this.anIntArrayArray1999[var4];
               double var6 = (double)var4 / (double)var1 + 6.0D;
               double var10 = (double)var2 / (double)var1;
               int var8 = (int)Math.floor(-7.0D + var6 + 1.0D);
               int var9 = (int)Math.ceil(7.0D + var6);
               if(var8 < 0) {
                  var8 = 0;
               }

               if(-15 > ~var9) {
                  var9 = 14;
               }

               while(var8 < var9) {
                  double var12 = ((double)var8 - var6) * 3.141592653589793D;
                  double var14 = var10;
                  if(-1.0E-4D > var12 || var12 > 1.0E-4D) {
                     var14 = var10 * (Math.sin(var12) / var12);
                  }

                  var14 *= 0.54D + 0.46D * Math.cos(((double)var8 - var6) * 0.2243994752564138D);
                  var5[var8] = (int)Math.floor(65536.0D * var14 + 0.5D);
                  ++var8;
               }
            }

         }
      } catch (RuntimeException var16) {
         throw Class44.method1067(var16, "vj.<init>(" + var1 + ',' + var2 + ')');
      }
   }

}
