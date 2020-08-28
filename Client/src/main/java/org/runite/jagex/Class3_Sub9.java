package org.runite.jagex;

final class Class3_Sub9 extends Class3 {

   int anInt2307;
   int anInt2308;
   static int anInt2309 = 128;
   int anInt2310;
   static boolean aBoolean2311;
   Class3_Sub24_Sub1 aClass3_Sub24_Sub1_2312;
   static long aLong2313 = 0L;
   int anInt2314;
   Class3_Sub24_Sub1 aClass3_Sub24_Sub1_2315;
   int anInt2316;
   static int anInt2317 = 0;
   static Class64 aClass64_2318;
   static int[][] regionXteaKeys;
   ObjectDefinition aClass111_2320;
   int anInt2321;
   int anInt2322 = 0;
   static RSString aClass94_2323 = RSString.createRSString("<img=0>");
   NPC aClass140_Sub4_Sub2_2324;
   int anInt2325;
   int anInt2326;
   Player aClass140_Sub4_Sub1_2327;
   int anInt2328;
   boolean aBoolean2329;
   static int anInt2330 = 0;
   static RSString aClass94_2331 = RSString.createRSString("");
   int anInt2332;
   int[] anIntArray2333;
   static int anInt2334;
   static RSString RIGHT_PARENTHESES = RSString.createRSString("(Y");


   static Class3_Sub28_Sub5 method133(int var0) {
      try {
         Class3_Sub28_Sub5 var2 = (Class3_Sub28_Sub5)Class159.aClass93_2016.get((long)var0);
         if(var2 == null) {
            var2 = Class47.method1089(Class131.aClass153_1723, Class7.aClass153_2160, var0);
            if(null != var2) {
               Class159.aClass93_2016.put((byte)-75, var2, (long)var0);
            }

         }
         return var2;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "fl.B(" + var0 + ',' + 0 + ')');
      }
   }

   final void method134() {
      try {
         int var2 = this.anInt2332;
         if(null != this.aClass111_2320) {
            ObjectDefinition var3 = this.aClass111_2320.method1685(0);
            if(var3 == null) {
               this.anInt2332 = -1;
               this.anIntArray2333 = null;
               this.anInt2325 = 0;
               this.anInt2328 = 0;
               this.anInt2310 = 0;
            } else {
               this.anInt2325 = var3.anInt1515;
               this.anInt2332 = var3.anInt1512;
               this.anInt2310 = var3.anInt1518;
               this.anInt2328 = var3.anInt1484 * 128;
               this.anIntArray2333 = var3.anIntArray1539;
            }
         } else if(this.aClass140_Sub4_Sub2_2324 == null) {
            if(null != this.aClass140_Sub4_Sub1_2327) {
               this.anInt2332 = Class81.method1398(this.aClass140_Sub4_Sub1_2327);
               this.anInt2328 = 128 * this.aClass140_Sub4_Sub1_2327.anInt3969;
            }
         } else {
            int var6 = ISAACCipher.method1232(this.aClass140_Sub4_Sub2_2324);
            if(var2 != var6) {
               NPCDefinition var4 = this.aClass140_Sub4_Sub2_2324.definition;
               this.anInt2332 = var6;
               if(var4.childNPCs != null) {
                  var4 = var4.method1471((byte)-87);
               }

               if(var4 == null) {
                  this.anInt2328 = 0;
               } else {
                  this.anInt2328 = var4.anInt1291 * 128;
               }
            }
         }

         if(this.anInt2332 != var2 && this.aClass3_Sub24_Sub1_2312 != null) {
            Class3_Sub26.aClass3_Sub24_Sub2_2563.method461(this.aClass3_Sub24_Sub1_2312);
            this.aClass3_Sub24_Sub1_2312 = null;
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "fl.A(" + 1 + ')');
      }
   }

   static void method135(int var0, int var1, int var2, int var3, int var4, int var5) {
      try {
         Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var0], var1 - var2, -76, var2 + var1, var5);
         int var6 = 0;
         int var8 = var2 * var2;
         int var9 = var4 * var4;
         int var7 = var4;
         int var10 = var9 << 1;
         int var11 = var8 << 1;
         int var12 = var4 << 1;
         int var13 = var10 + (1 + -var12) * var8;
         int var14 = var9 - var11 * (var12 - 1);
         int var15 = var8 << 2;
         if(var3 > -110) {
            method137(-83, (byte)-91);
         }

         int var16 = var9 << 2;
         int var17 = var10 * (3 + (var6 << 1));
         int var18 = var11 * ((var4 << 1) + -3);
         int var19 = (1 + var6) * var16;
         int var20 = var15 * (var4 - 1);

         while(var7 > 0) {
            --var7;
            int var22 = var7 + var0;
            int var21 = var0 - var7;
            if(var13 < 0) {
               while(var13 < 0) {
                  ++var6;
                  var13 += var17;
                  var14 += var19;
                  var19 += var16;
                  var17 += var16;
               }
            }

            if(var14 < 0) {
               var13 += var17;
               var17 += var16;
               var14 += var19;
               ++var6;
               var19 += var16;
            }

            int var23 = var6 + var1;
            var14 += -var18;
            var18 -= var15;
            var13 += -var20;
            int var24 = var1 + -var6;
            var20 -= var15;
            Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var21], var24, -110, var23, var5);
            Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var22], var24, 112, var23, var5);
         }

      } catch (RuntimeException var25) {
         throw Class44.clientError(var25, "fl.C(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
      }
   }

   public static void method136(int var0) {
      try {
         aClass94_2323 = null;
         RIGHT_PARENTHESES = null;
         if(var0 != -3) {
            anInt2317 = 98;
         }

         aClass94_2331 = null;
         aClass64_2318 = null;
         regionXteaKeys = (int[][])null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "fl.D(" + var0 + ')');
      }
   }

   static void method137(int var0, byte var1) {
      try {
         if(var1 >= -111) {
            RIGHT_PARENTHESES = (RSString)null;
         }

         Class82.aClass93_1146.method1522(-127, var0);
         Class159.aClass93_2016.method1522(-126, var0);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "fl.E(" + var0 + ',' + var1 + ')');
      }
   }

}
