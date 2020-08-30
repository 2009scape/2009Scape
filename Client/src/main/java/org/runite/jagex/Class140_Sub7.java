package org.runite.jagex;

final class Class140_Sub7 extends GameObject {

   static RSString aClass94_2928 = RSString.createRSString("null");
   int anInt2930;
   static int[] anIntArray2931;
   private int anInt2932 = -32768;
   static int[] anIntArray2933 = new int[]{2, 0, 0, 2, 0, 0, 0, 4, 4};
   static int anInt2934;
   static RSString[] aClass94Array2935 = new RSString[500];
   int anInt2936;
   static int anInt2937;
   static int anInt2938;
   static CacheIndex aClass153_2939;


   static boolean method2029(byte var0, CacheIndex var1, int var2) {
      try {
         if(var0 > -116) {
            return false;
         } else {
            byte[] var3 = var1.method2138(var2);
            if(null == var3) {
               return false;
            } else {
               Class45.method1082(var3, 19);
               return true;
            }
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "uj.B(" + var0 + ',' + (var1 != null?"{...}":"null") + ',' + var2 + ')');
      }
   }

   public static void method2030(byte var0) {
      try {
         ItemDefinition.intsStack = null;
         if(var0 < 82) {
            method2031((byte)-61, false, -20, 24, (Class3_Sub2[][][])((Class3_Sub2[][][])null), 124);
         }

         anIntArray2931 = null;
         anIntArray2933 = null;
         aClass94Array2935 = null;
         aClass94_2928 = null;
         aClass153_2939 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "uj.C(" + var0 + ')');
      }
   }

   static boolean method2031(byte var0, boolean var1, int var2, int var3, Class3_Sub2[][][] var4, int var5) {
      try {
         byte var6 = !var1?(byte)(255 & Class79.anInt1127):1;
         if(Class158.aByteArrayArrayArray2008[WorldListCountry.localPlane][var2][var3] == var6) {
            return false;
         } else if((Class9.aByteArrayArrayArray113[WorldListCountry.localPlane][var2][var3] & 4) == 0) {
            return false;
         } else {
            int var8 = 0;
            byte var7 = 0;
            Class3_Sub13_Sub38.anIntArray3456[var7] = var2;
            if(var0 >= -9) {
               method2031((byte)-29, false, 59, 52, (Class3_Sub2[][][])((Class3_Sub2[][][])null), 75);
            }

            int var23 = var7 + 1;
            Class45.anIntArray729[var7] = var3;
            Class158.aByteArrayArrayArray2008[WorldListCountry.localPlane][var2][var3] = var6;

            while(var23 != var8) {
               int var10 = (16740943 & Class3_Sub13_Sub38.anIntArray3456[var8]) >> 16;
               int var11 = 255 & Class3_Sub13_Sub38.anIntArray3456[var8] >> 24;
               int var9 = Class3_Sub13_Sub38.anIntArray3456[var8] & '\uffff';
               int var13 = (Class45.anIntArray729[var8] & 16721603) >> 16;
               int var12 = Class45.anIntArray729[var8] & '\uffff';
               var8 = 4095 & 1 + var8;
               boolean var14 = false;
               boolean var15 = false;
               if(0 == (Class9.aByteArrayArrayArray113[WorldListCountry.localPlane][var9][var12] & 4)) {
                  var14 = true;
               }

               int var17;
               int var16;
               label257:
               for(var16 = 1 + WorldListCountry.localPlane; 3 >= var16; ++var16) {
                  if((Class9.aByteArrayArrayArray113[var16][var9][var12] & 8) == 0) {
                     int var18;
                     int var20;
                     if(var14 && var4[var16][var9][var12] != null) {
                        if(null != var4[var16][var9][var12].aClass70_2234) {
                           var17 = Class55.method1184(var10, (byte)82);
                           if(var17 == var4[var16][var9][var12].aClass70_2234.anInt1055 || var4[var16][var9][var12].aClass70_2234.anInt1059 == var17) {
                              continue;
                           }

                           if(0 != var11) {
                              var18 = Class55.method1184(var11, (byte)-93);
                              if(var18 == var4[var16][var9][var12].aClass70_2234.anInt1055 || var4[var16][var9][var12].aClass70_2234.anInt1059 == var18) {
                                 continue;
                              }
                           }

                           if(var13 != 0) {
                              var18 = Class55.method1184(var13, (byte)99);
                              if(var18 == var4[var16][var9][var12].aClass70_2234.anInt1055 || var4[var16][var9][var12].aClass70_2234.anInt1059 == var18) {
                                 continue;
                              }
                           }
                        }

                        if(var4[var16][var9][var12].aClass25Array2221 != null) {
                           for(var17 = 0; var17 < var4[var16][var9][var12].anInt2223; ++var17) {
                              var18 = (int)(63L & var4[var16][var9][var12].aClass25Array2221[var17].aLong498 >> 14);
                              if(var18 == 21) {
                                 var18 = 19;
                              }

                              int var19 = (int)(var4[var16][var9][var12].aClass25Array2221[var17].aLong498 >> 20 & 3L);
                              var20 = var18 | var19 << 6;
                              if(var10 == var20 || var11 != 0 && var20 == var11 || var13 != 0 && var13 == var20) {
                                 continue label257;
                              }
                           }
                        }
                     }

                     var15 = true;
                     Class3_Sub2 var24 = var4[var16][var9][var12];
                     if(var24 != null && var24.anInt2223 > 0) {
                        for(var18 = 0; var18 < var24.anInt2223; ++var18) {
                           Class25 var25 = var24.aClass25Array2221[var18];
                           if(var25.anInt483 != var25.anInt495 || var25.anInt478 != var25.anInt481) {
                              for(var20 = var25.anInt483; var20 <= var25.anInt495; ++var20) {
                                 for(int var21 = var25.anInt478; var25.anInt481 >= var21; ++var21) {
                                    Class158.aByteArrayArrayArray2008[var16][var20][var21] = var6;
                                 }
                              }
                           }
                        }
                     }

                     Class158.aByteArrayArrayArray2008[var16][var9][var12] = var6;
                  }
               }

               if(var15) {
                  if(Class44.anIntArrayArrayArray723[WorldListCountry.localPlane - -1][var9][var12] > Class41.anIntArray686[var5]) {
                     Class41.anIntArray686[var5] = Class44.anIntArrayArrayArray723[WorldListCountry.localPlane + 1][var9][var12];
                  }

                  var16 = var9 << 7;
                  if(var16 >= Class129_Sub1.anIntArray2696[var5]) {
                     if(var16 > Class159.anIntArray2021[var5]) {
                        Class159.anIntArray2021[var5] = var16;
                     }
                  } else {
                     Class129_Sub1.anIntArray2696[var5] = var16;
                  }

                  var17 = var12 << 7;
                  if(var17 < AnimationDefinition.anIntArray1871[var5]) {
                     AnimationDefinition.anIntArray1871[var5] = var17;
                  } else if(Player.anIntArray3959[var5] < var17) {
                     Player.anIntArray3959[var5] = var17;
                  }
               }

               if(!var14) {
                  if(var9 >= 1 && Class158.aByteArrayArrayArray2008[WorldListCountry.localPlane][-1 + var9][var12] != var6) {
                     Class3_Sub13_Sub38.anIntArray3456[var23] = Class3_Sub13_Sub29.bitwiseOr(Class3_Sub13_Sub29.bitwiseOr(var9 - 1, 1179648), -754974720);
                     Class45.anIntArray729[var23] = Class3_Sub13_Sub29.bitwiseOr(var12, 1245184);
                     var23 = 1 + var23 & 4095;
                     Class158.aByteArrayArrayArray2008[WorldListCountry.localPlane][var9 - 1][var12] = var6;
                  }

                  ++var12;
                  if(104 > var12) {
                     if(var9 + -1 >= 0 && Class158.aByteArrayArrayArray2008[WorldListCountry.localPlane][-1 + var9][var12] != var6 && (Class9.aByteArrayArrayArray113[WorldListCountry.localPlane][var9][var12] & 4) == 0 && (Class9.aByteArrayArrayArray113[WorldListCountry.localPlane][-1 + var9][var12 + -1] & 4) == 0) {
                        Class3_Sub13_Sub38.anIntArray3456[var23] = Class3_Sub13_Sub29.bitwiseOr(1375731712, Class3_Sub13_Sub29.bitwiseOr(1179648, -1 + var9));
                        Class45.anIntArray729[var23] = Class3_Sub13_Sub29.bitwiseOr(var12, 1245184);
                        Class158.aByteArrayArrayArray2008[WorldListCountry.localPlane][-1 + var9][var12] = var6;
                        var23 = 1 + var23 & 4095;
                     }

                     if(var6 != Class158.aByteArrayArrayArray2008[WorldListCountry.localPlane][var9][var12]) {
                        Class3_Sub13_Sub38.anIntArray3456[var23] = Class3_Sub13_Sub29.bitwiseOr(318767104, Class3_Sub13_Sub29.bitwiseOr(var9, 5373952));
                        Class45.anIntArray729[var23] = Class3_Sub13_Sub29.bitwiseOr(5439488, var12);
                        var23 = 4095 & 1 + var23;
                        Class158.aByteArrayArrayArray2008[WorldListCountry.localPlane][var9][var12] = var6;
                     }

                     if(1 + var9 < 104 && Class158.aByteArrayArrayArray2008[WorldListCountry.localPlane][var9 + 1][var12] != var6 && 0 == (Class9.aByteArrayArrayArray113[WorldListCountry.localPlane][var9][var12] & 4) && (Class9.aByteArrayArrayArray113[WorldListCountry.localPlane][1 + var9][var12 - 1] & 4) == 0) {
                        Class3_Sub13_Sub38.anIntArray3456[var23] = Class3_Sub13_Sub29.bitwiseOr(-1845493760, Class3_Sub13_Sub29.bitwiseOr(5373952, var9 + 1));
                        Class45.anIntArray729[var23] = Class3_Sub13_Sub29.bitwiseOr(5439488, var12);
                        Class158.aByteArrayArrayArray2008[WorldListCountry.localPlane][var9 - -1][var12] = var6;
                        var23 = 4095 & var23 - -1;
                     }
                  }

                  --var12;
                  if(104 > 1 + var9 && var6 != Class158.aByteArrayArrayArray2008[WorldListCountry.localPlane][var9 - -1][var12]) {
                     Class3_Sub13_Sub38.anIntArray3456[var23] = Class3_Sub13_Sub29.bitwiseOr(Class3_Sub13_Sub29.bitwiseOr(1 + var9, 9568256), 1392508928);
                     Class45.anIntArray729[var23] = Class3_Sub13_Sub29.bitwiseOr(var12, 9633792);
                     Class158.aByteArrayArrayArray2008[WorldListCountry.localPlane][1 + var9][var12] = var6;
                     var23 = var23 + 1 & 4095;
                  }

                  --var12;
                  if(var12 >= 0) {
                     if(0 <= var9 + -1 && Class158.aByteArrayArrayArray2008[WorldListCountry.localPlane][-1 + var9][var12] != var6 && (Class9.aByteArrayArrayArray113[WorldListCountry.localPlane][var9][var12] & 4) == 0 && (Class9.aByteArrayArrayArray113[WorldListCountry.localPlane][var9 + -1][1 + var12] & 4) == 0) {
                        Class3_Sub13_Sub38.anIntArray3456[var23] = Class3_Sub13_Sub29.bitwiseOr(Class3_Sub13_Sub29.bitwiseOr(-1 + var9, 13762560), 301989888);
                        Class45.anIntArray729[var23] = Class3_Sub13_Sub29.bitwiseOr(var12, 13828096);
                        Class158.aByteArrayArrayArray2008[WorldListCountry.localPlane][-1 + var9][var12] = var6;
                        var23 = 4095 & var23 - -1;
                     }

                     if(var6 != Class158.aByteArrayArrayArray2008[WorldListCountry.localPlane][var9][var12]) {
                        Class3_Sub13_Sub38.anIntArray3456[var23] = Class3_Sub13_Sub29.bitwiseOr(Class3_Sub13_Sub29.bitwiseOr(var9, 13762560), -1828716544);
                        Class45.anIntArray729[var23] = Class3_Sub13_Sub29.bitwiseOr(13828096, var12);
                        var23 = var23 - -1 & 4095;
                        Class158.aByteArrayArrayArray2008[WorldListCountry.localPlane][var9][var12] = var6;
                     }

                     if(var9 - -1 < 104 && Class158.aByteArrayArrayArray2008[WorldListCountry.localPlane][var9 + 1][var12] != var6 && (4 & Class9.aByteArrayArrayArray113[WorldListCountry.localPlane][var9][var12]) == 0 && (Class9.aByteArrayArrayArray113[WorldListCountry.localPlane][1 + var9][1 + var12] & 4) == 0) {
                        Class3_Sub13_Sub38.anIntArray3456[var23] = Class3_Sub13_Sub29.bitwiseOr(-771751936, Class3_Sub13_Sub29.bitwiseOr(var9 - -1, 9568256));
                        Class45.anIntArray729[var23] = Class3_Sub13_Sub29.bitwiseOr(9633792, var12);
                        Class158.aByteArrayArrayArray2008[WorldListCountry.localPlane][var9 + 1][var12] = var6;
                        var23 = 4095 & 1 + var23;
                     }
                  }
               }
            }

            if(-1000000 != Class41.anIntArray686[var5]) {
               Class41.anIntArray686[var5] += 10;
               Class129_Sub1.anIntArray2696[var5] -= 50;
               Class159.anIntArray2021[var5] += 50;
               Player.anIntArray3959[var5] += 50;
               AnimationDefinition.anIntArray1871[var5] -= 50;
            }

            return true;
         }
      } catch (RuntimeException var22) {
         throw Class44.clientError(var22, "uj.A(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + (var4 != null?"{...}":"null") + ',' + var5 + ')');
      }
   }

   final void method1867(int var1, int var2, int var3, int var4, int var5) {}

   static int method2032(int var0) {
      try {

         return var0 >>> 10;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "uj.E(" + var0 + ',' + 5439488 + ')');
      }
   }

   static RSString method2033(RSByteBuffer var0) {
      try {
         try {
            int var3 = var0.getSmart();
            if(32767 < var3) {
               var3 = 32767;
            }

            byte[] var4 = new byte[var3];
            var0.index += Class3_Sub13_Sub9.aClass36_3112.method1017(0, var3, var4, var0.buffer, var0.index);
            return Class3_Sub13_Sub3.method178(var4, var3, 0);
         } catch (Exception var6) {
            return Class159.aClass94_2018;
         }
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "uj.D(" + (var0 != null?"{...}":"null") + ',' + 1 + ',' + 32767 + ')');
      }
   }

   final void animate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, long var9, int var11, Class127_Sub1 var12) {
      try {
         Model var13 = Class38.getItemDefinition(this.anInt2936, (byte)84).method1110(124, -1, 0, (AnimationDefinition)null, this.anInt2930, 0);
         if(null != var13) {
            var13.animate(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11, var12);
            this.anInt2932 = var13.method1871();
         }
      } catch (RuntimeException var14) {
         throw Class44.clientError(var14, "uj.IA(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + var9 + ',' + var11 + ',' + (var12 != null?"{...}":"null") + ')');
      }
   }

   final int method1871() {
      try {
         return this.anInt2932;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "uj.MA()");
      }
   }

}
