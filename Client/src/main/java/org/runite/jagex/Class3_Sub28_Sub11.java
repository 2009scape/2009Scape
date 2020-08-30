package org.runite.jagex;

import java.util.Objects;

final class Class3_Sub28_Sub11 extends Node {

   private Class130 aClass130_3636;
   static RSString aClass94_3637 = RSString.createRSString(")4p=");
   static boolean aBoolean3641 = false;
   static int anInt3642 = 0;
   static int anInt3644 = 0;


   static int method599(CacheIndex var1) {
      try {
         int var2 = 0;
         if(var1.method2144(Class154.anInt1966)) {
            ++var2;
         }

         if(var1.method2144(Class79.anInt1124)) {
            ++var2;
         }

         return var2;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "lk.F(" + -20916 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   final int method600(int var1, int var2) {
      try {
         if(this.aClass130_3636 == null) {
            return var2;
         } else {

            Class3_Sub18 var4 = (Class3_Sub18)this.aClass130_3636.method1780((long)var1, (byte) -29 ^ -29);
            return null == var4?var2:var4.anInt2467;
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "lk.Q(" + var1 + ',' + var2 + ',' + (byte) -29 + ')');
      }
   }

   private void method601(RSByteBuffer var1, int var2) {
      try {
         if(249 == var2) {
            int var4 = var1.getByteB();
            int var5;
            if(this.aClass130_3636 == null) {
               var5 = Class95.method1585((byte)105, var4);
               this.aClass130_3636 = new Class130(var5);
            }

            for(var5 = 0; var4 > var5; ++var5) {
               boolean var6 = 1 == var1.getByteB();
               int var7 = var1.getTriByte((byte)95);
               Object var8;
               if(var6) {
                  var8 = new Class3_Sub29(var1.getString());
               } else {
                  var8 = new Class3_Sub18(var1.getInt());
               }

               this.aClass130_3636.method1779((Class3)var8, (long)var7);
            }
         }

      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "lk.P(" + (var1 != null?"{...}":"null") + ',' + var2 + ',' + (byte) -5 + ')');
      }
   }

   static Class3_Sub28_Sub16 method602(int var0, int var1, byte var2, CacheIndex var3) {
      try {
         //  System.out.println("Class3_sub28_Sub16 " + var1);
         if(Class75_Sub4.method1351(var3, var0, var1, var2 ^ 30885)) {
            if(var2 != -18) {
               Translation.englishToGerman(true);
            }

            return Class43.method1062(var2 + 103);
         } else {
            return null;
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "lk.R(" + var0 + ',' + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ')');
      }
   }

   static void method603(int var0, int var1, int var2, RSInterface var3, boolean var4) {
      try {
         int var5 = var3.anInt168;
         int var6 = var3.anInt193;
         if(var1 != 13987) {
            method602(-115, 65, (byte)-119, (CacheIndex)null);
         }

         if(var3.aByte304 == 0) {
            var3.anInt168 = var3.width;
         } else if (var3.aByte304 == 1) {
             var3.anInt168 = var2 + -var3.width;
         } else if (var3.aByte304 == 2) {
             var3.anInt168 = var3.width * var2 >> 14;
         } else if (var3.aByte304 == 3) {
             if (var3.type == 2) {
                 var3.anInt168 = var3.width * 32 - -((var3.width - 1) * var3.anInt285);
             } else if (var3.type == 7) {
                 var3.anInt168 = 115 * var3.width + var3.anInt285 * (-1 + var3.width);
             }
         }

         if(var3.aByte241 == 0) {
            var3.anInt193 = var3.height;
         } else if(var3.aByte241 == 1) {
            var3.anInt193 = -var3.height + var0;
         } else if(var3.aByte241 == 2) {
            var3.anInt193 = var0 * var3.height >> 14;
         } else if(var3.aByte241 == 3) {
            if(var3.type == 2) {
               var3.anInt193 = (var3.height + -1) * var3.anInt290 + var3.height * 32;
            } else if(var3.type == 7) {
               var3.anInt193 = var3.height * 12 + (-1 + var3.height) * var3.anInt290;
            }
         }

         if(var3.aByte304 == 4) {
            var3.anInt168 = var3.anInt216 * var3.anInt193 / var3.anInt160;
         }

         if(var3.aByte241 == 4) {
            var3.anInt193 = var3.anInt160 * var3.anInt168 / var3.anInt216;
         }

         if(Class69.aBoolean1040 && (Client.method44(var3).anInt2205 != 0 || var3.type == 0)) {
            if(var3.anInt193 < 5 && 5 > var3.anInt168) {
               var3.anInt193 = 5;
               var3.anInt168 = 5;
            } else {
               if(var3.anInt168 <= 0) {
                  var3.anInt168 = 5;
               }

               if(0 >= var3.anInt193) {
                  var3.anInt193 = 5;
               }
            }
         }

         if(1337 == var3.anInt189) {
            Class168.aClass11_2091 = var3;
         }

         if(var4 && null != var3.anObjectArray235 && (var3.anInt168 != var5 || var3.anInt193 != var6)) {
            CS2Script var7 = new CS2Script();
            var7.arguments = var3.anObjectArray235;
            var7.aClass11_2449 = var3;
            Class110.aClass61_1471.method1215(var7);
         }

      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "lk.E(" + var0 + ',' + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + var4 + ')');
      }
   }

   final RSString method604(RSString var1, int var3) {
      try {
         if(this.aClass130_3636 == null) {
            return var1;
         } else {
            Class3_Sub29 var4 = (Class3_Sub29)this.aClass130_3636.method1780((long)var3, 0);

            return null != var4?var4.aClass94_2586:var1;
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "lk.B(" + (var1 != null?"{...}":"null") + ',' + (byte) -44 + ',' + var3 + ')');
      }
   }

   public static void method605(int var0) {
      try {
         aClass94_3637 = null;
         if(var0 != 221301966) {
            method603(-111, -64, -10, (RSInterface)null, false);
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "lk.D(" + var0 + ')');
      }
   }

   static void method606(int var0, Class3_Sub9 var1, int var2, int var3, int var4, int var5) {
      try {
         if(var5 > 44) {
            if(var1.anInt2332 != -1 || var1.anIntArray2333 != null) {
               int var6 = 0;
               if(var1.anInt2321 < var0) {
                  var6 += -var1.anInt2321 + var0;
               } else if(var1.anInt2326 > var0) {
                  var6 += var1.anInt2326 - var0;
               }

               if(var1.anInt2307 >= var4) {
                  if(var4 < var1.anInt2308) {
                     var6 += -var4 + var1.anInt2308;
                  }
               } else {
                  var6 += -var1.anInt2307 + var4;
               }

               if(0 != var1.anInt2328 && var6 - 64 <= var1.anInt2328 && 0 != Class14.anInt340 && var2 == var1.anInt2314) {
                  var6 -= 64;
                  if(var6 < 0) {
                     var6 = 0;
                  }

                  int var7 = (-var6 + var1.anInt2328) * Class14.anInt340 / var1.anInt2328;
                  if(var1.aClass3_Sub24_Sub1_2312 == null) {
                     if(var1.anInt2332 >= 0) {
                        Class135 var8 = Class135.method1811(Class146.aClass153_1902, var1.anInt2332, 0);
                        if(null != var8) {
                           Class3_Sub12_Sub1 var9 = var8.method1812().method151(Class27.aClass157_524);
                           Class3_Sub24_Sub1 var10 = Class3_Sub24_Sub1.method437(var9, var7);
                           Objects.requireNonNull(var10).method429(-1);
                           Class3_Sub26.aClass3_Sub24_Sub2_2563.method457(var10);
                           var1.aClass3_Sub24_Sub1_2312 = var10;
                        }
                     }
                  } else {
                     var1.aClass3_Sub24_Sub1_2312.method419(var7);
                  }

                  if(null != var1.aClass3_Sub24_Sub1_2315) {
                     var1.aClass3_Sub24_Sub1_2315.method419(var7);
                     if(!var1.aClass3_Sub24_Sub1_2315.method82()) {
                        var1.aClass3_Sub24_Sub1_2315 = null;
                     }
                  } else if(var1.anIntArray2333 != null && ((var1.anInt2316 -= var3) <= 0)) {
                     int var13 = (int)((double)var1.anIntArray2333.length * Math.random());
                     Class135 var14 = Class135.method1811(Class146.aClass153_1902, var1.anIntArray2333[var13], 0);
                     if(null != var14) {
                        Class3_Sub12_Sub1 var15 = var14.method1812().method151(Class27.aClass157_524);
                        Class3_Sub24_Sub1 var11 = Class3_Sub24_Sub1.method437(var15, var7);
                        Objects.requireNonNull(var11).method429(0);
                        Class3_Sub26.aClass3_Sub24_Sub2_2563.method457(var11);
                        var1.anInt2316 = (int)((double)(-var1.anInt2310 + var1.anInt2325) * Math.random()) + var1.anInt2310;
                        var1.aClass3_Sub24_Sub1_2315 = var11;
                     }
                  }

               } else {
                  if(null != var1.aClass3_Sub24_Sub1_2312) {
                     Class3_Sub26.aClass3_Sub24_Sub2_2563.method461(var1.aClass3_Sub24_Sub1_2312);
                     var1.aClass3_Sub24_Sub1_2312 = null;
                  }

                  if(var1.aClass3_Sub24_Sub1_2315 != null) {
                     Class3_Sub26.aClass3_Sub24_Sub2_2563.method461(var1.aClass3_Sub24_Sub1_2315);
                     var1.aClass3_Sub24_Sub1_2315 = null;
                  }

               }
            }
         }
      } catch (RuntimeException var12) {
         throw Class44.clientError(var12, "lk.O(" + var0 + ',' + (var1 != null?"{...}":"null") + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
      }
   }



   final void method608(RSByteBuffer var2) {
      try {
         while(true) {
            int var3 = var2.getByteB();
            if(0 == var3) {

               return;
            }

            this.method601(var2, var3);
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "lk.C(" + 5 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

}
