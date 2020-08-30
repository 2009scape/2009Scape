package org.runite.jagex;

final class Class3_Sub14 extends Class3 {

   private static float[] aFloatArray2387;
   private static float[] aFloatArray2388;
   private int anInt2389;
   private int anInt2390;
   private static int[] anIntArray2391;
   private static Class152[] aClass152Array2392;
   private static boolean[] aBooleanArray2393;
   private float[] aFloatArray2394;
   private boolean aBoolean2395;
   private static int anInt2396;
   private int anInt2397;
   private static Class150[] aClass150Array2398;
   private static float[] aFloatArray2399;
   private static float[] aFloatArray2400;
   private int anInt2401;
   private static int anInt2402;
   private static float[] aFloatArray2403;
   private static int anInt2404;
   private static boolean aBoolean2405 = false;
   static Class71[] aClass71Array2406;
   private static Class59[] aClass59Array2407;
   private int anInt2408;
   private static int[] anIntArray2409;
   private byte[][] aByteArrayArray2410;
   private boolean aBoolean2411;
   private int anInt2412;
   private static float[] aFloatArray2413;
   private static int anInt2414;
   private byte[] aByteArray2415;
   private static float[] aFloatArray2416;
   private static byte[] aByteArray2417;
   private int anInt2418;
   private int anInt2419;
   private static int[] anIntArray2420;


   static float method358(int var0) {
      int var1 = var0 & 2097151;
      int var2 = var0 & Integer.MIN_VALUE;
      int var3 = (var0 & 2145386496) >> 21;
      if(var2 != 0) {
         var1 = -var1;
      }

      return (float)((double)var1 * Math.pow(2.0D, (double)(var3 - 788)));
   }

   final Class3_Sub12_Sub1 method359(int[] var1) {
      if(var1 != null && var1[0] <= 0) {
         return null;
      } else {
         if(this.aByteArray2415 == null) {
            this.anInt2389 = 0;
            this.aFloatArray2394 = new float[anInt2396];
            this.aByteArray2415 = new byte[this.anInt2390];
            this.anInt2419 = 0;
            this.anInt2418 = 0;
         }

         for(; this.anInt2418 < this.aByteArrayArray2410.length; ++this.anInt2418) {
            if(var1 != null && var1[0] <= 0) {
               return null;
            }

            float[] var2 = this.method366(this.anInt2418);
            if(var2 != null) {
               int var3 = this.anInt2419;
               int var4 = var2.length;
               if(var4 > this.anInt2390 - var3) {
                  var4 = this.anInt2390 - var3;
               }

               for(int var5 = 0; var5 < var4; ++var5) {
                  int var6 = (int)(128.0F + var2[var5] * 128.0F);
                  if((var6 & -256) != 0) {
                     var6 = ~var6 >> 31;
                  }

                  this.aByteArray2415[var3++] = (byte)(var6 - 128);
               }

               if(var1 != null) {
                  var1[0] -= var3 - this.anInt2419;
               }

               this.anInt2419 = var3;
            }
         }

         this.aFloatArray2394 = null;
         byte[] var7 = this.aByteArray2415;
         this.aByteArray2415 = null;
         return new Class3_Sub12_Sub1(this.anInt2408, var7, this.anInt2397, this.anInt2401, this.aBoolean2395);
      }
   }

   private void method360(byte[] var1) {
      RSByteBuffer var2 = new RSByteBuffer(var1);
      this.anInt2408 = var2.getInt();
      this.anInt2390 = var2.getInt();
      this.anInt2397 = var2.getInt();
      this.anInt2401 = var2.getInt();
      if(this.anInt2401 < 0) {
         this.anInt2401 = ~this.anInt2401;
         this.aBoolean2395 = true;
      }

      int var3 = var2.getInt();
      this.aByteArrayArray2410 = new byte[var3][];

      for(int var4 = 0; var4 < var3; ++var4) {
         int var5 = 0;

         int var6;
         do {
            var6 = var2.getByteB();
            var5 += var6;
         } while(var6 >= 255);

         byte[] var7 = new byte[var5];
         var2.method764(var5, var7);
         this.aByteArrayArray2410[var4] = var7;
      }

   }

   public static void method361() {
      aByteArray2417 = null;
      aClass71Array2406 = null;
      aClass59Array2407 = null;
      aClass152Array2392 = null;
      aClass150Array2398 = null;
      aBooleanArray2393 = null;
      anIntArray2420 = null;
      aFloatArray2403 = null;
      aFloatArray2400 = null;
      aFloatArray2413 = null;
      aFloatArray2416 = null;
      aFloatArray2399 = null;
      aFloatArray2387 = null;
      aFloatArray2388 = null;
      anIntArray2391 = null;
      anIntArray2409 = null;
   }

   private static boolean method362(CacheIndex var0) {
      if(!aBoolean2405) {
         byte[] var1 = var0.getFile(0, 0);
         if(var1 == null) {
            return false;
         }

         method367(var1);
         aBoolean2405 = true;
      }

      return true;
   }

   static Class3_Sub14 method363(CacheIndex var0, int var1, int var2) {
      if(method362(var0)) {
         byte[] var3 = var0.getFile(var1, var2);
         return var3 == null?null:new Class3_Sub14(var3);
      } else {
         var0.method2129((byte)95, var2, var1);
         return null;
      }
   }

   static int method364() {
      int var0 = aByteArray2417[anInt2404] >> anInt2414 & 1;
      ++anInt2414;
      anInt2404 += anInt2414 >> 3;
      anInt2414 &= 7;
      return var0;
   }

   private static void method365(byte[] var0) {
      aByteArray2417 = var0;
      anInt2404 = 0;
      anInt2414 = 0;
   }

   private float[] method366(int var1) {
      method365(this.aByteArrayArray2410[var1]);
      method364();
      int var2 = method368(Class3_Sub26.method513(anIntArray2420.length - 1));
      boolean var3 = aBooleanArray2393[var2];
      int var4 = var3?anInt2396:anInt2402;
      boolean var5 = false;
      boolean var6 = false;
      if(var3) {
         var5 = method364() != 0;
         var6 = method364() != 0;
      }

      int var7 = var4 >> 1;
      int var8;
      int var9;
      int var10;
      if(var3 && !var5) {
         var8 = (var4 >> 2) - (anInt2402 >> 2);
         var9 = (var4 >> 2) + (anInt2402 >> 2);
         var10 = anInt2402 >> 1;
      } else {
         var8 = 0;
         var9 = var7;
         var10 = var4 >> 1;
      }

      int var11;
      int var12;
      int var13;
      if(var3 && !var6) {
         var11 = var4 - (var4 >> 2) - (anInt2402 >> 2);
         var12 = var4 - (var4 >> 2) + (anInt2402 >> 2);
         var13 = anInt2402 >> 1;
      } else {
         var11 = var7;
         var12 = var4;
         var13 = var4 >> 1;
      }

      Class150 var14 = aClass150Array2398[anIntArray2420[var2]];
      int var16 = var14.anInt1928;
      int var17 = var14.anIntArray1929[var16];
      boolean var15 = !aClass59Array2407[var17].method1205();

      for(var17 = 0; var17 < var14.anInt1931; ++var17) {
         Class152 var18 = aClass152Array2392[var14.anIntArray1930[var17]];
         float[] var19 = aFloatArray2403;
         var18.method2112(var19, var4 >> 1, var15);
      }

      int var44;
      if(!var15) {
         var17 = var14.anInt1928;
         var44 = var14.anIntArray1929[var17];
         aClass59Array2407[var44].method1202(aFloatArray2403, var4 >> 1);
      }

      int var41;
      if(var15) {
         for(var17 = var4 >> 1; var17 < var4; ++var17) {
            aFloatArray2403[var17] = 0.0F;
         }
      } else {
         var17 = var4 >> 1;
         var44 = var4 >> 2;
         var41 = var4 >> 3;
         float[] var20 = aFloatArray2403;

         int var21;
         for(var21 = 0; var21 < var17; ++var21) {
            var20[var21] *= 0.5F;
         }

         for(var21 = var17; var21 < var4; ++var21) {
            var20[var21] = -var20[var4 - var21 - 1];
         }

         float[] var46 = var3?aFloatArray2399:aFloatArray2400;
         float[] var22 = var3?aFloatArray2387:aFloatArray2413;
         float[] var23 = var3?aFloatArray2388:aFloatArray2416;
         int[] var24 = var3?anIntArray2409:anIntArray2391;

         int var25;
         float var27;
         float var26;
         float var29;
         float var28;
         for(var25 = 0; var25 < var44; ++var25) {
            var26 = var20[4 * var25] - var20[var4 - 4 * var25 - 1];
            var27 = var20[4 * var25 + 2] - var20[var4 - 4 * var25 - 3];
            var28 = var46[2 * var25];
            var29 = var46[2 * var25 + 1];
            var20[var4 - 4 * var25 - 1] = var26 * var28 - var27 * var29;
            var20[var4 - 4 * var25 - 3] = var26 * var29 + var27 * var28;
         }

         float var31;
         float var30;
         for(var25 = 0; var25 < var41; ++var25) {
            var26 = var20[var17 + 3 + 4 * var25];
            var27 = var20[var17 + 1 + 4 * var25];
            var28 = var20[4 * var25 + 3];
            var29 = var20[4 * var25 + 1];
            var20[var17 + 3 + 4 * var25] = var26 + var28;
            var20[var17 + 1 + 4 * var25] = var27 + var29;
            var30 = var46[var17 - 4 - 4 * var25];
            var31 = var46[var17 - 3 - 4 * var25];
            var20[4 * var25 + 3] = (var26 - var28) * var30 - (var27 - var29) * var31;
            var20[4 * var25 + 1] = (var27 - var29) * var30 + (var26 - var28) * var31;
         }

         var25 = Class3_Sub26.method513(var4 - 1);

         int var47;
         int var49;
         int var48;
         int var52;
         for(var49 = 0; var49 < var25 - 3; ++var49) {
            var47 = var4 >> var49 + 2;
            var48 = 8 << var49;

            for(var52 = 0; var52 < 2 << var49; ++var52) {
               int var50 = var4 - var47 * 2 * var52;
               int var51 = var4 - var47 * (2 * var52 + 1);

               for(int var32 = 0; var32 < var4 >> var49 + 4; ++var32) {
                  int var33 = 4 * var32;
                  float var34 = var20[var50 - 1 - var33];
                  float var35 = var20[var50 - 3 - var33];
                  float var36 = var20[var51 - 1 - var33];
                  float var37 = var20[var51 - 3 - var33];
                  var20[var50 - 1 - var33] = var34 + var36;
                  var20[var50 - 3 - var33] = var35 + var37;
                  float var38 = var46[var32 * var48];
                  float var39 = var46[var32 * var48 + 1];
                  var20[var51 - 1 - var33] = (var34 - var36) * var38 - (var35 - var37) * var39;
                  var20[var51 - 3 - var33] = (var35 - var37) * var38 + (var34 - var36) * var39;
               }
            }
         }

         for(var49 = 1; var49 < var41 - 1; ++var49) {
            var47 = var24[var49];
            if(var49 < var47) {
               var48 = 8 * var49;
               var52 = 8 * var47;
               var30 = var20[var48 + 1];
               var20[var48 + 1] = var20[var52 + 1];
               var20[var52 + 1] = var30;
               var30 = var20[var48 + 3];
               var20[var48 + 3] = var20[var52 + 3];
               var20[var52 + 3] = var30;
               var30 = var20[var48 + 5];
               var20[var48 + 5] = var20[var52 + 5];
               var20[var52 + 5] = var30;
               var30 = var20[var48 + 7];
               var20[var48 + 7] = var20[var52 + 7];
               var20[var52 + 7] = var30;
            }
         }

         for(var49 = 0; var49 < var17; ++var49) {
            var20[var49] = var20[2 * var49 + 1];
         }

         for(var49 = 0; var49 < var41; ++var49) {
            var20[var4 - 1 - 2 * var49] = var20[4 * var49];
            var20[var4 - 2 - 2 * var49] = var20[4 * var49 + 1];
            var20[var4 - var44 - 1 - 2 * var49] = var20[4 * var49 + 2];
            var20[var4 - var44 - 2 - 2 * var49] = var20[4 * var49 + 3];
         }

         for(var49 = 0; var49 < var41; ++var49) {
            var27 = var23[2 * var49];
            var28 = var23[2 * var49 + 1];
            var29 = var20[var17 + 2 * var49];
            var30 = var20[var17 + 2 * var49 + 1];
            var31 = var20[var4 - 2 - 2 * var49];
            float var54 = var20[var4 - 1 - 2 * var49];
            float var53 = var28 * (var29 - var31) + var27 * (var30 + var54);
            var20[var17 + 2 * var49] = (var29 + var31 + var53) * 0.5F;
            var20[var4 - 2 - 2 * var49] = (var29 + var31 - var53) * 0.5F;
            var53 = var28 * (var30 + var54) - var27 * (var29 - var31);
            var20[var17 + 2 * var49 + 1] = (var30 - var54 + var53) * 0.5F;
            var20[var4 - 1 - 2 * var49] = (-var30 + var54 + var53) * 0.5F;
         }

         for(var49 = 0; var49 < var44; ++var49) {
            var20[var49] = var20[2 * var49 + var17] * var22[2 * var49] + var20[2 * var49 + 1 + var17] * var22[2 * var49 + 1];
            var20[var17 - 1 - var49] = var20[2 * var49 + var17] * var22[2 * var49 + 1] - var20[2 * var49 + 1 + var17] * var22[2 * var49];
         }

         for(var49 = 0; var49 < var44; ++var49) {
            var20[var4 - var44 + var49] = -var20[var49];
         }

         for(var49 = 0; var49 < var44; ++var49) {
            var20[var49] = var20[var44 + var49];
         }

         for(var49 = 0; var49 < var44; ++var49) {
            var20[var44 + var49] = -var20[var44 - var49 - 1];
         }

         for(var49 = 0; var49 < var44; ++var49) {
            var20[var17 + var49] = var20[var4 - var49 - 1];
         }

         for(var49 = var8; var49 < var9; ++var49) {
            var27 = (float)Math.sin(((double)(var49 - var8) + 0.5D) / (double)var10 * 0.5D * 3.141592653589793D);
            aFloatArray2403[var49] *= (float)Math.sin(1.5707963267948966D * (double)var27 * (double)var27);
         }

         for(var49 = var11; var49 < var12; ++var49) {
            var27 = (float)Math.sin(((double)(var49 - var11) + 0.5D) / (double)var13 * 0.5D * 3.141592653589793D + 1.5707963267948966D);
            aFloatArray2403[var49] *= (float)Math.sin(1.5707963267948966D * (double)var27 * (double)var27);
         }
      }

      float[] var42 = null;
      if(this.anInt2389 > 0) {
         var44 = this.anInt2389 + var4 >> 2;
         var42 = new float[var44];
         int var45;
         if(!this.aBoolean2411) {
            for(var41 = 0; var41 < this.anInt2412; ++var41) {
               var45 = (this.anInt2389 >> 1) + var41;
               var42[var41] += this.aFloatArray2394[var45];
            }
         }

         if(!var15) {
            for(var41 = var8; var41 < var4 >> 1; ++var41) {
               var45 = var42.length - (var4 >> 1) + var41;
               var42[var45] += aFloatArray2403[var41];
            }
         }
      }

      float[] var43 = this.aFloatArray2394;
      this.aFloatArray2394 = aFloatArray2403;
      aFloatArray2403 = var43;
      this.anInt2389 = var4;
      this.anInt2412 = var12 - (var4 >> 1);
      this.aBoolean2411 = var15;
      return var42;
   }

   private static void method367(byte[] var0) {
      method365(var0);
      anInt2402 = 1 << method368(4);
      anInt2396 = 1 << method368(4);
      aFloatArray2403 = new float[anInt2396];

      int var1;
      int var2;
      int var3;
      int var4;
      int var5;
      for(var1 = 0; var1 < 2; ++var1) {
         var2 = var1 != 0?anInt2396:anInt2402;
         var3 = var2 >> 1;
         var4 = var2 >> 2;
         var5 = var2 >> 3;
         float[] var6 = new float[var3];

         for(int var7 = 0; var7 < var4; ++var7) {
            var6[2 * var7] = (float)Math.cos((double)(4 * var7) * 3.141592653589793D / (double)var2);
            var6[2 * var7 + 1] = -((float)Math.sin((double)(4 * var7) * 3.141592653589793D / (double)var2));
         }

         float[] var13 = new float[var3];

         for(int var8 = 0; var8 < var4; ++var8) {
            var13[2 * var8] = (float)Math.cos((double)(2 * var8 + 1) * 3.141592653589793D / (double)(2 * var2));
            var13[2 * var8 + 1] = (float)Math.sin((double)(2 * var8 + 1) * 3.141592653589793D / (double)(2 * var2));
         }

         float[] var14 = new float[var4];

         for(int var9 = 0; var9 < var5; ++var9) {
            var14[2 * var9] = (float)Math.cos((double)(4 * var9 + 2) * 3.141592653589793D / (double)var2);
            var14[2 * var9 + 1] = -((float)Math.sin((double)(4 * var9 + 2) * 3.141592653589793D / (double)var2));
         }

         int[] var15 = new int[var5];
         int var10 = Class3_Sub26.method513(var5 - 1);

         for(int var11 = 0; var11 < var5; ++var11) {
            var15[var11] = Class3_Sub28_Sub3.method540(var10, var11);
         }

         if(var1 == 0) {
            aFloatArray2400 = var6;
            aFloatArray2413 = var13;
            aFloatArray2416 = var14;
            anIntArray2391 = var15;
         } else {
            aFloatArray2399 = var6;
            aFloatArray2387 = var13;
            aFloatArray2388 = var14;
            anIntArray2409 = var15;
         }
      }

      var1 = method368(8) + 1;
      aClass71Array2406 = new Class71[var1];

      for(var2 = 0; var2 < var1; ++var2) {
         aClass71Array2406[var2] = new Class71();
      }

      var2 = method368(6) + 1;

      for(var3 = 0; var3 < var2; ++var3) {
         method368(16);
      }

      var2 = method368(6) + 1;
      aClass59Array2407 = new Class59[var2];

      for(var3 = 0; var3 < var2; ++var3) {
         aClass59Array2407[var3] = new Class59();
      }

      var3 = method368(6) + 1;
      aClass152Array2392 = new Class152[var3];

      for(var4 = 0; var4 < var3; ++var4) {
         aClass152Array2392[var4] = new Class152();
      }

      var4 = method368(6) + 1;
      aClass150Array2398 = new Class150[var4];

      for(var5 = 0; var5 < var4; ++var5) {
         aClass150Array2398[var5] = new Class150();
      }

      var5 = method368(6) + 1;
      aBooleanArray2393 = new boolean[var5];
      anIntArray2420 = new int[var5];

      for(int var12 = 0; var12 < var5; ++var12) {
         aBooleanArray2393[var12] = method364() != 0;
         method368(16);
         method368(16);
         anIntArray2420[var12] = method368(8);
      }

   }

   static int method368(int var0) {
      int var1 = 0;

      int var2;
      int var3;
      for(var2 = 0; var0 >= 8 - anInt2414; var0 -= var3) {
         var3 = 8 - anInt2414;
         int var4 = (1 << var3) - 1;
         var1 += (aByteArray2417[anInt2404] >> anInt2414 & var4) << var2;
         anInt2414 = 0;
         ++anInt2404;
         var2 += var3;
      }

      if(var0 > 0) {
         var3 = (1 << var0) - 1;
         var1 += (aByteArray2417[anInt2404] >> anInt2414 & var3) << var2;
         anInt2414 += var0;
      }

      return var1;
   }

   private Class3_Sub14(byte[] var1) {
      this.method360(var1);
   }

}
