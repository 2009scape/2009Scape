package org.runite.jagex;

import java.util.Objects;

final class Class8 {

   private RSByteBuffer aClass3_Sub30_99;
   private Class3_Sub28_Sub10_Sub2 aClass3_Sub28_Sub10_Sub2_100;
   static int anInt101;
   private final Class66 aClass66_102;
   private final Class73 aClass73_103;
   static int anInt104 = 0;
   static CacheIndex aClass153_105;
   static RSString aClass94_106 = RSString.createRSString("showVideoAd");
   private Class151_Sub1[] aClass151_Sub1Array107;


   public static void method836(int var0) {
      try {
         aClass153_105 = null;
         if(var0 > -10) {
            method843(-80, (RSByteBuffer)null);
         }

         aClass94_106 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "al.I(" + var0 + ')');
      }
   }

   final boolean method837() {
      try {
         if(null == this.aClass3_Sub30_99) {

            if(this.aClass3_Sub28_Sub10_Sub2_100 == null) {
               if(this.aClass66_102.method1251((byte)89)) {
                  return false;
               }

               this.aClass3_Sub28_Sub10_Sub2_100 = this.aClass66_102.addJS5Request(123, 255, (byte)0, 255, true);
            }

            if(this.aClass3_Sub28_Sub10_Sub2_100.aBoolean3632) {
               return false;
            } else {
               this.aClass3_Sub30_99 = new RSByteBuffer(this.aClass3_Sub28_Sub10_Sub2_100.method587());
               this.aClass151_Sub1Array107 = new Class151_Sub1[(this.aClass3_Sub30_99.buffer.length + -5) / 8];
               return true;
            }
         } else {
            return true;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "al.J(" + 255 + ')');
      }
   }

   final void method838() {
      try {
         if(null != this.aClass151_Sub1Array107) {
            int var2;
            for(var2 = 0; var2 < this.aClass151_Sub1Array107.length; ++var2) {
               if(this.aClass151_Sub1Array107[var2] != null) {
                  this.aClass151_Sub1Array107[var2].method2110();
               }
            }

            for(var2 = 0; this.aClass151_Sub1Array107.length > var2; ++var2) {
               if(this.aClass151_Sub1Array107[var2] != null) {
                  this.aClass151_Sub1Array107[var2].method2107();
               }
            }

         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "al.E(" + (byte) -70 + ')');
      }
   }

   private Class151_Sub1 method839(int var2, Class41 var3, Class41 var4) {
      try {

         return this.method847(var4, var2, var3);
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "al.L(" + -1824885439 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + (var4 != null?"{...}":"null") + ')');
      }
   }

   static void method840(ObjectDefinition var0, byte var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      try {
         int var9 = 3 & var3;
         if(var1 >= -1) {
            aClass94_106 = (RSString)null;
         }

         int var10;
         int var11;
         if(var9 == 1 || var9 == 3) {
            var10 = var0.SizeY;
            var11 = var0.SizeX;
         } else {
            var11 = var0.SizeY;
            var10 = var0.SizeX;
         }

         int var14;
         int var15;
         if(var7 - -var11 > 104) {
            var15 = 1 + var7;
            var14 = var7;
         } else {
            var14 = var7 - -(var11 >> 1);
            var15 = var7 - -(1 + var11 >> 1);
         }

         int var16 = (var6 << 7) - -(var10 << 6);
         int var17 = (var7 << 7) + (var11 << 6);
         int var12;
         int var13;
         if(104 < var6 - -var10) {
            var12 = var6;
            var13 = var6 + 1;
         } else {
            var12 = var6 + (var10 >> 1);
            var13 = (var10 - -1 >> 1) + var6;
         }

         int[][] var18 = Class44.anIntArrayArrayArray723[var8];
         int var20 = 0;
         int var19 = var18[var12][var15] + var18[var12][var14] + var18[var13][var14] + var18[var13][var15] >> 2;
         int[][] var21;
         if(var8 != 0) {
            var21 = Class44.anIntArrayArrayArray723[0];
            var20 = -(var21[var12][var15] + var21[var13][var14] + (var21[var12][var14] - -var21[var13][var15]) >> 2) + var19;
         }

         var21 = (int[][])null;
         if(3 > var8) {
            var21 = Class44.anIntArrayArrayArray723[1 + var8];
         }

         Class136 var22 = var0.method1696(var3, var16, var18, var5, var19, var21, false, (LDIndexedSprite)null, (byte)-69, true, var17);
         Class141.method2047(Objects.requireNonNull(var22).aClass109_Sub1_1770, -var4 + var16, var20, var17 + -var2);
      } catch (RuntimeException var23) {
         throw Class44.clientError(var23, "al.K(" + (var0 != null?"{...}":"null") + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ')');
      }
   }

   static void method841() {
      try {
         GameObject.aClass11Array1836 = null;
         Class3_Sub13_Sub1.method171(-101, Class3_Sub28_Sub12.anInt3655, 0, Class23.anInt454, 0, -1, Class140_Sub7.anInt2934, 0, 0);
         if(GameObject.aClass11Array1836 != null) {
            Class47.method1095(0, Class73.anInt1082, Class3_Sub28_Sub7.anInt3602, GameObject.aClass11Array1836, Class23.anInt454, -1412584499, 0, Class140_Sub7.anInt2934, (byte)73, PacketParser.aClass11_88.anInt292);
            GameObject.aClass11Array1836 = null;
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "al.G(" + true + ')');
      }
   }

   static CacheIndex getCacheIndex(boolean var0, boolean var1, boolean var2, int var3) {
      try {
         Class41 var5 = null;
         if(null != Class101.aClass30_1422) {
            var5 = new Class41(var3, Class101.aClass30_1422, Class163_Sub2.aClass30Array2998[var3], 1000000);
         }

         RSByteBuffer.aClass151_Sub1Array2601[var3] = Class151.aClass8_1936.method839(var3, Class86.aClass41_1186, var5);
         if(var1) {
            RSByteBuffer.aClass151_Sub1Array2601[var3].method2101();
         }
         return new CacheIndex(RSByteBuffer.aClass151_Sub1Array2601[var3], var0, var2);
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "al.B(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + true + ')');
      }
   }

   static Class75_Sub1 method843(int var0, RSByteBuffer var1) {
      try {
         return var0 != -5232?(Class75_Sub1)null:new Class75_Sub1(var1.getShort((byte)46), var1.getShort((byte)109), var1.getShort((byte)68), var1.getShort((byte)127), var1.getTriByte((byte)91), var1.getByteB());
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "al.D(" + var0 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   static void method844(byte var0) {
      try {
         if(var0 != -9) {
            aClass94_106 = (RSString)null;
         }

         if(null == Class3_Sub13_Sub17.anIntArray3212 || null == Class75_Sub2.anIntArray2639) {
            Class3_Sub13_Sub17.anIntArray3212 = new int[256];
            Class75_Sub2.anIntArray2639 = new int[256];

            for(int var1 = 0; 256 > var1; ++var1) {
               double var2 = (double)var1 / 255.0D * 6.283185307179586D;
               Class3_Sub13_Sub17.anIntArray3212[var1] = (int)(Math.sin(var2) * 4096.0D);
               Class75_Sub2.anIntArray2639[var1] = (int)(4096.0D * Math.cos(var2));
            }
         }

      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "al.C(" + var0 + ')');
      }
   }

   static void method845(boolean var0) {
      try {
         if(var0 == !Class139.aBoolean1827) {
            Class139.aBoolean1827 = var0;

            Class104.method1626((byte)-126);
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "al.H(" + var0 + ',' + 255 + ')');
      }
   }

   static boolean method846(int var0, int var1, int var2) {
      int var3 = Class81.anIntArrayArrayArray1142[var0][var1][var2];
      if(var3 == -Class3_Sub28_Sub1.anInt3539) {
         return false;
      } else if(var3 == Class3_Sub28_Sub1.anInt3539) {
         return true;
      } else {
         int var4 = var1 << 7;
         int var5 = var2 << 7;
         if(Class3_Sub13_Sub37.method349(var4 + 1, Class44.anIntArrayArrayArray723[var0][var1][var2], var5 + 1) && Class3_Sub13_Sub37.method349(var4 + 128 - 1, Class44.anIntArrayArrayArray723[var0][var1 + 1][var2], var5 + 1) && Class3_Sub13_Sub37.method349(var4 + 128 - 1, Class44.anIntArrayArrayArray723[var0][var1 + 1][var2 + 1], var5 + 128 - 1) && Class3_Sub13_Sub37.method349(var4 + 1, Class44.anIntArrayArrayArray723[var0][var1][var2 + 1], var5 + 128 - 1)) {
            Class81.anIntArrayArrayArray1142[var0][var1][var2] = Class3_Sub28_Sub1.anInt3539;
            return true;
         } else {
            Class81.anIntArrayArrayArray1142[var0][var1][var2] = -Class3_Sub28_Sub1.anInt3539;
            return false;
         }
      }
   }

   private Class151_Sub1 method847(Class41 var1, int var3, Class41 var5) {
      try {
         if(null == this.aClass3_Sub30_99) {
            throw new RuntimeException();
         } else {
            this.aClass3_Sub30_99.index = 5 + var3 * 8;
            if(this.aClass3_Sub30_99.buffer.length > this.aClass3_Sub30_99.index) {
               if(null == this.aClass151_Sub1Array107[var3]) {
                  int var6 = this.aClass3_Sub30_99.getInt();
                  int var7 = this.aClass3_Sub30_99.getInt();
                  Class151_Sub1 var8 = new Class151_Sub1(var3, var1, var5, this.aClass66_102, this.aClass73_103, var6, var7);
                  this.aClass151_Sub1Array107[var3] = var8;
                  return var8;
               } else {
                  return this.aClass151_Sub1Array107[var3];
               }
            } else {
               throw new RuntimeException();
            }
         }
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "al.A(" + (var1 != null?"{...}":"null") + ',' + -125 + ',' + var3 + ',' + true + ',' + (var5 != null?"{...}":"null") + ')');
      }
   }

   Class8(Class66 var1, Class73 var2) {
      try {
         this.aClass73_103 = var2;
         this.aClass66_102 = var1;
         if(!this.aClass66_102.method1251((byte)111)) {
            this.aClass3_Sub28_Sub10_Sub2_100 = this.aClass66_102.addJS5Request(110, 255, (byte)0, 255, true);
         }

      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "al.<init>(" + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

}
