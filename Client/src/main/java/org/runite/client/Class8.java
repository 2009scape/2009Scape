package org.runite.client;

import java.util.Objects;

final class Class8 {

    static RSInterface[] aClass11Array1836;
    private DataBuffer aClass3_Sub30_99;
   private Js5ResourceRequest aJs5ResourceRequest_100;
   static int anInt101;
   private final Js5Worker aJs5Worker_102;
   private final CacheResourceWorker aCacheResourceWorker_103;
   static int anInt104 = 0;
   static CacheIndex aClass153_105;
   private Class151_Sub1[] aClass151_Sub1Array107;


   final boolean method837() {
      try {
         if(null == this.aClass3_Sub30_99) {

            if(this.aJs5ResourceRequest_100 == null) {
               if(this.aJs5Worker_102.priorityRequestsFull()) {
                  return false;
               }

               this.aJs5ResourceRequest_100 = this.aJs5Worker_102.request(255, 255, (byte)0, true);
            }

            if(this.aJs5ResourceRequest_100.waiting) {
               return false;
            } else {
               this.aClass3_Sub30_99 = new DataBuffer(this.aJs5ResourceRequest_100.getData());
               this.aClass151_Sub1Array107 = new Class151_Sub1[(this.aClass3_Sub30_99.buffer.length + -5) / 8];
               return true;
            }
         } else {
            return true;
         }
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "al.J(" + 255 + ')');
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
         throw ClientErrorException.clientError(var3, "al.E(" + (byte) -70 + ')');
      }
   }

   private Class151_Sub1 method839(int var2, Class41 var3, Class41 var4) {
      try {

         return this.method847(var4, var2, var3);
      } catch (RuntimeException var6) {
         throw ClientErrorException.clientError(var6, "al.L(" + -1824885439 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + (var4 != null?"{...}":"null") + ')');
      }
   }

   static void method840(ObjectDefinition var0, byte var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      try {
         int var9 = 3 & var3;
         if(var1 >= -1) {
            TextCore.aClass94_106 = null;
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

         Class136 var22 = var0.method1696(var3, var16, var18, var5, var19, var21, false, null, (byte)-69, true, var17);
         Class141.method2047(Objects.requireNonNull(var22).aClass109_Sub1_1770, -var4 + var16, var20, var17 + -var2);
      } catch (RuntimeException var23) {
         throw ClientErrorException.clientError(var23, "al.K(" + (var0 != null?"{...}":"null") + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ')');
      }
   }

   static void method841() {
      try {
         aClass11Array1836 = null;
         Class3_Sub13_Sub1.method171(-101, Class3_Sub28_Sub12.anInt3655, 0, Class23.anInt454, 0, -1, Class140_Sub7.anInt2934, 0, 0);
         if(aClass11Array1836 != null) {
            Unsorted.method1095(0, Unsorted.anInt1082, Unsorted.anInt3602, aClass11Array1836, Class23.anInt454, -1412584499, 0, Class140_Sub7.anInt2934, (byte)73, PacketParser.aClass11_88.anInt292);
            aClass11Array1836 = null;
         }

      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "al.G(" + true + ')');
      }
   }

   static CacheIndex getCacheIndex(boolean var0, boolean var1, boolean var2, int var3) {
      try {
         Class41 var5 = null;
         if(null != Class101.aClass30_1422) {
            var5 = new Class41(var3, Class101.aClass30_1422, Class163_Sub2.aClass30Array2998[var3], 1000000);
         }

         Unsorted.aClass151_Sub1Array2601[var3] = Unsorted.aClass8_1936.method839(var3, AtmosphereParser.aClass41_1186, var5);
         if(var1) {
            Unsorted.aClass151_Sub1Array2601[var3].method2101();
         }
         return new CacheIndex(Unsorted.aClass151_Sub1Array2601[var3], var0, var2);
      } catch (RuntimeException var6) {
         throw ClientErrorException.clientError(var6, "al.B(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + true + ')');
      }
   }

   static Class75_Sub1 method843(int var0, DataBuffer var1) {
      try {
         return var0 != -5232?(Class75_Sub1)null:new Class75_Sub1(var1.readSignedShort(), var1.readSignedShort(), var1.readSignedShort(), var1.readSignedShort(), var1.readMedium(), var1.readUnsignedByte());
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "al.D(" + var0 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   static void method844(byte var0) {
      try {
         if(var0 != -9) {
            TextCore.aClass94_106 = (RSString)null;
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
         throw ClientErrorException.clientError(var4, "al.C(" + var0 + ')');
      }
   }

   static void method845(boolean var0) {
      try {
         if(var0 == !Class139.aBoolean1827) {
            Class139.aBoolean1827 = var0;

            WaterfallShader.method1626((byte)-126);
         }
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "al.H(" + var0 + ',' + 255 + ')');
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
                  int var6 = this.aClass3_Sub30_99.readInt();
                  int var7 = this.aClass3_Sub30_99.readInt();
                  Class151_Sub1 var8 = new Class151_Sub1(var3, var1, var5, this.aJs5Worker_102, this.aCacheResourceWorker_103, var6, var7);
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
         throw ClientErrorException.clientError(var9, "al.A(" + (var1 != null?"{...}":"null") + ',' + -125 + ',' + var3 + ',' + true + ',' + (var5 != null?"{...}":"null") + ')');
      }
   }

   Class8(Js5Worker var1, CacheResourceWorker var2) {
      try {
         this.aCacheResourceWorker_103 = var2;
         this.aJs5Worker_102 = var1;
         if(!this.aJs5Worker_102.priorityRequestsFull()) {
            this.aJs5ResourceRequest_100 = this.aJs5Worker_102.request(255, 255, (byte)0, true);
         }

      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "al.<init>(" + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

}
