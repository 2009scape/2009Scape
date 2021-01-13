package org.runite.client;
import org.rs09.client.util.ArrayUtils;

import java.util.Random;

final class Class3_Sub13_Sub19 extends Class3_Sub13 {

   private int anInt3219 = 204;
   private int anInt3223;
   private int anInt3224 = 81;
   private int[][] anIntArrayArray3225;
   static CacheIndex aClass153_3227;
   private int anInt3229 = 1024;
   private int[] anIntArray3230;
   private int anInt3231 = 0;
   private static RSString aClass94_3232 = RSString.parse("pt");
   private int anInt3233 = 8;
   private int anInt3234 = 1024;
   private int anInt3235;
   private int anInt3236 = 409;
   private static RSString aClass94_3237 = RSString.parse("en");
   private static RSString aClass94_3239 = RSString.parse("fr");
   private int[][] anIntArrayArray3240;
   private int anInt3242 = 4;
   private static RSString aClass94_3243 = RSString.parse("de");
   static RSString[] aClass94Array3238 = new RSString[]{aClass94_3237, aClass94_3243, aClass94_3239, aClass94_3232};
   

   final void method158(int var1) {
      try {
         if(var1 != 16251) {
            this.method158(-93);
         }

         this.method263();
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "mc.P(" + var1 + ')');
      }
   }

   static void method260(int var1, int var2) {
      try {

         InterfaceWidget var3 = Class3_Sub24_Sub3.getWidget(7, var1);
         var3.flagUpdate();
         var3.anInt3598 = var2;
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "mc.O(" + -16207 + ',' + var1 + ',' + var2 + ')');
      }
   }

   final void method157(int var1, DataBuffer var2, boolean var3) {
      try {
         if(0 == var1) {
            this.anInt3242 = var2.readUnsignedByte();
         } else if (var1 == 1) {
            this.anInt3233 = var2.readUnsignedByte();
         } else if (var1 == 2) {
            this.anInt3236 = var2.readUnsignedShort();
         } else if (var1 == 3) {
            this.anInt3219 = var2.readUnsignedShort();
         } else if (4 == var1) {
            this.anInt3234 = var2.readUnsignedShort();
         } else if (var1 == 5) {
            this.anInt3231 = var2.readUnsignedShort();
         } else if (var1 == 6) {
            this.anInt3224 = var2.readUnsignedShort();
         } else if (var1 == 7) {
            this.anInt3229 = var2.readUnsignedShort();
         }

         if(!var3) {
            aClass94_3239 = (RSString)null;
         }

      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "mc.A(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

   static void method262(int var0, int var2, int var3, int var4, int var5, int var6, int var7) {
      try {
         int var8 = var5 + var2;
         int var10 = var5 + var7;

         int var12;
         for(var12 = var2; var12 < var8; ++var12) {
            Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var12], var7, 125, var6, var0);
         }

         int var9 = -var5 + var3;
         int var11 = -var5 + var6;

         for(var12 = var3; var12 > var9; --var12) {
            Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var12], var7, 103, var6, var0);
         }

         for(var12 = var8; var12 <= var9; ++var12) {
            int[] var13 = Class38.anIntArrayArray663[var12];
            Class3_Sub13_Sub23_Sub1.method282(var13, var7, 117, var10, var0);
            Class3_Sub13_Sub23_Sub1.method282(var13, var10, 111, var11, var4);
            Class3_Sub13_Sub23_Sub1.method282(var13, var11, -75, var6, var0);
         }

      } catch (RuntimeException var14) {
         throw ClientErrorException.clientError(var14, "mc.Q(" + var0 + ',' + 119 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
      }
   }

   private void method263() {
      try {
         Random var2 = new Random((long)this.anInt3233);
         int anInt3222 = 4096 / this.anInt3233;
         this.anInt3223 = this.anInt3224 / 2;
         this.anIntArrayArray3225 = new int[this.anInt3233][1 + this.anInt3242];
         int var4 = anInt3222 / 2;
         this.anIntArray3230 = new int[this.anInt3233 - -1];
         this.anIntArrayArray3240 = new int[this.anInt3233][this.anInt3242];
         this.anInt3235 = 4096 / this.anInt3242;
         this.anIntArray3230[0] = 0;
         int var3 = this.anInt3235 / 2;

         for(int var5 = 0; this.anInt3233 > var5; ++var5) {
            int var6;
            int var7;
            if(var5 > 0) {
               var6 = anInt3222;
               var7 = (Class3_Sub13.method1603((byte)59, 4096, var2) + -2048) * this.anInt3219 >> 12;
               var6 += var7 * var4 >> 12;
               this.anIntArray3230[var5] = this.anIntArray3230[var5 - 1] - -var6;
            }

            this.anIntArrayArray3225[var5][0] = 0;

            for(var6 = 0; this.anInt3242 > var6; ++var6) {
               if(0 < var6) {
                  var7 = this.anInt3235;
                  int var8 = (-2048 + Class3_Sub13.method1603((byte)-1, 4096, var2)) * this.anInt3236 >> 12;
                  var7 += var3 * var8 >> 12;
                  this.anIntArrayArray3225[var5][var6] = this.anIntArrayArray3225[var5][var6 + -1] - -var7;
               }

               this.anIntArrayArray3240[var5][var6] = this.anInt3229 <= 0 ?4096:4096 + -Class3_Sub13.method1603((byte)33, this.anInt3229, var2);
            }

            this.anIntArrayArray3225[var5][this.anInt3242] = 4096;
         }

         this.anIntArray3230[this.anInt3233] = 4096;
      } catch (RuntimeException var9) {
         throw ClientErrorException.clientError(var9, "mc.E(" + 0 + ')');
      }
   }

   static void method264(byte var0) {
      try {
         Class3_Sub13_Sub1.outgoingBuffer.putOpcode(184);

         for(Class3_Sub31 var1 = (Class3_Sub31)Class3_Sub13_Sub17.aHashTable_3208.first(); null != var1; var1 = (Class3_Sub31)Class3_Sub13_Sub17.aHashTable_3208.next()) {
            if(var1.anInt2603 == 0) {
               Class3_Sub13_Sub18.method254(true, var1);
            }
         }

         if(var0 < 83) {
            aClass94_3232 = (RSString)null;
         }

         if(null != Class3_Sub13_Sub7.aClass11_3087) {
            Class20.method909(Class3_Sub13_Sub7.aClass11_3087);
            Class3_Sub13_Sub7.aClass11_3087 = null;
         }

      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "mc.C(" + var0 + ')');
      }
   }

   final int[] method154(int var1, byte var2) {
      try {
         int[] var3 = this.aClass114_2382.method1709(var1);
         int var4;
         if(this.aClass114_2382.aBoolean1580) {
            int var7 = 0;

            int var9;
            for(var9 = Class163_Sub3.anIntArray2999[var1] + this.anInt3231; var9 < 0; var9 += 4096) {
            }

            while(4096 < var9) {
               var9 -= 4096;
            }

            while(this.anInt3233 > var7 && this.anIntArray3230[var7] <= var9) {
               ++var7;
            }

            int var11 = var7 + -1;
            int var15 = this.anIntArray3230[var7];
            boolean var12 = 0 == (var7 & 1);
            int var16 = this.anIntArray3230[var7 - 1];
            if(var16 - -this.anInt3223 < var9 && var9 < var15 - this.anInt3223) {
               for(var4 = 0; var4 < Class113.anInt1559; ++var4) {
                  int var6 = 0;
                  int var5 = !var12?-this.anInt3234:this.anInt3234;

                  int var8;
                  for(var8 = Class102.anIntArray2125[var4] - -(this.anInt3235 * var5 >> 12); var8 < 0; var8 += 4096) {
                  }

                  while(var8 > 4096) {
                     var8 -= 4096;
                  }

                  while(var6 < this.anInt3242 && this.anIntArrayArray3225[var11][var6] <= var8) {
                     ++var6;
                  }

                  int var14 = this.anIntArrayArray3225[var11][var6];
                  int var10 = var6 - 1;
                  int var13 = this.anIntArrayArray3225[var11][var10];
                  if(var8 > var13 - -this.anInt3223 && var8 < -this.anInt3223 + var14) {
                     var3[var4] = this.anIntArrayArray3240[var11][var10];
                  } else {
                     var3[var4] = 0;
                  }
               }
            } else {
               ArrayUtils.fill(var3, 0, Class113.anInt1559, 0);
            }
         }

          return var3;
      } catch (RuntimeException var17) {
         throw ClientErrorException.clientError(var17, "mc.D(" + var1 + ',' + var2 + ')');
      }
   }

   public Class3_Sub13_Sub19() {
      super(0, true);
   }

   static void method265(int var1) {
      try {
         InterfaceWidget var2 = Class3_Sub24_Sub3.getWidget(8, var1);
         var2.a();

      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "mc.B(" + (byte) -42 + ',' + var1 + ')');
      }
   }

}
