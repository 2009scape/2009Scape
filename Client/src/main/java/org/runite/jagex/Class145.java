package org.runite.jagex;

final class Class145 {

   int animationId;
   int anInt1891;
   int anInt1893;
   int anInt1894;
   static Class113[] aClass113Array1895;
   int anInt1897;
   static int anInt1898;
   int anInt1900;

   public static void method2071(byte var0) {
      try {
         aClass113Array1895 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ub.G(" + var0 + ')');
      }
   }

   static void method2072(int var0, int var1, int var2, int var3, int var4, int var5) {
      try {
         if(Class101.anInt1425 <= var1 && Class3_Sub28_Sub18.anInt3765 >= var3 && var2 >= Class159.anInt2020 && var4 <= Class57.anInt902) {
            if(var5 == 1) {
               Class77.method1367(var0, var3, var2, (byte)34, var4, var1);
            } else {
               AbstractIndexedSprite.method1665(-2 + -19617, var3, var2, var0, var4, var5, var1);
            }
         } else if(1 == var5) {
            Class102.method1617(var0, var1, var4, var3, var2);
         } else {
            Class3_Sub13_Sub3.method184(var4, var5, var3, var1, var0, var2);
         }

      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "ub.F(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + -2 + ')');
      }
   }

   static void method2073(Class3_Sub2 var0, boolean var1) {
      Class163_Sub1.aClass61_2990.method1215(var0);

      while(true) {
         Class3_Sub2 var2 = (Class3_Sub2)Class163_Sub1.aClass61_2990.method1220();
         if(var2 == null) {
            return;
         }

         if(var2.aBoolean2225) {
            int var3 = var2.anInt2239;
            int var4 = var2.anInt2231;
            int var5 = var2.anInt2244;
            int var6 = var2.anInt2238;
            Class3_Sub2[][] var7 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var5];
            float var8 = 0.0F;
            int var9;
            int var10;
            int var11;
            int var12;
            if(HDToolKit.highDetail) {
               if(Class3_Sub28_Sub7.anIntArrayArrayArray3605 == Class44.anIntArrayArrayArray723) {
                  var9 = Class3_Sub13_Sub9.anIntArrayArray3115[var3][var4];
                  var10 = var9 & 16777215;
                  if(var10 != IOHandler.anInt1244) {
                     IOHandler.anInt1244 = var10;
                     Class3_Sub28_Sub2.method535((byte)56, var10);
                     Class92.method1512(Class72.method1297());
                  }

                  var11 = var9 >>> 24 << 3;
                  if(var11 != Class3_Sub13_Sub5.anInt3072) {
                     Class3_Sub13_Sub5.anInt3072 = var11;
                     Class3_Sub21.method394(var11, 121);
                  }

                  var12 = Class58.anIntArrayArrayArray914[0][var3][var4] + Class58.anIntArrayArrayArray914[0][var3 + 1][var4] + Class58.anIntArrayArrayArray914[0][var3][var4 + 1] + Class58.anIntArrayArrayArray914[0][var3 + 1][var4 + 1] >> 2;
                  Class3_Sub28_Sub4.method551(-var12, 3);
                  var8 = 201.5F;
               } else {
                  var8 = 201.5F - 50.0F * (float)(var6 + 1);
               }
               HDToolKit.method1832(var8);
            }

            int var14;
            int var15;
            int var17;
            int var16;
            int var18;
            Class3_Sub2 var21;
            Class70 var23;
            boolean var22;
            Class25 var25;
            Class3_Sub2 var35;
            if(var2.aBoolean2222) {
               if(var1) {
                  if(var5 > 0) {
                     var21 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var5 - 1][var3][var4];
                     if(var21 != null && var21.aBoolean2225) {
                        continue;
                     }
                  }

                  if(var3 <= Class97.anInt1375 && var3 > Class163_Sub1_Sub1.anInt4006) {
                     var21 = var7[var3 - 1][var4];
                     if(var21 != null && var21.aBoolean2225 && (var21.aBoolean2222 || (var2.anInt2228 & 1) == 0)) {
                        continue;
                     }
                  }

                  if(var3 >= Class97.anInt1375 && var3 < Class2.anInt67 - 1) {
                     var21 = var7[var3 + 1][var4];
                     if(var21 != null && var21.aBoolean2225 && (var21.aBoolean2222 || (var2.anInt2228 & 4) == 0)) {
                        continue;
                     }
                  }

                  if(var4 <= Class3_Sub13_Sub27.anInt3340 && var4 > Class3_Sub28_Sub7.anInt3603) {
                     var21 = var7[var3][var4 - 1];
                     if(var21 != null && var21.aBoolean2225 && (var21.aBoolean2222 || (var2.anInt2228 & 8) == 0)) {
                        continue;
                     }
                  }

                  if(var4 >= Class3_Sub13_Sub27.anInt3340 && var4 < Class126.anInt1665 - 1) {
                     var21 = var7[var3][var4 + 1];
                     if(var21 != null && var21.aBoolean2225 && (var21.aBoolean2222 || (var2.anInt2228 & 2) == 0)) {
                        continue;
                     }
                  }
               } else {
                  var1 = true;
               }

               var2.aBoolean2222 = false;
               if(var2.aClass3_Sub2_2235 != null) {
                  var21 = var2.aClass3_Sub2_2235;
                  if(HDToolKit.highDetail) {
                     HDToolKit.method1832(201.5F - 50.0F * (float)(var21.anInt2238 + 1));
                  }

                  if(var21.aClass126_2240 != null) {
                     Class3_Sub13_Sub18.method259(var21.aClass126_2240, 0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var3, var4, Class8.method846(0, var3, var4));
                  } else if(var21.aClass35_2226 != null) {
                     Class3_Sub21.method395(var21.aClass35_2226, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var3, var4, Class8.method846(0, var3, var4));
                  }

                  var23 = var21.aClass70_2234;
                  if(var23 != null) {
                     if(HDToolKit.highDetail) {
                        if((var23.anInt1055 & var2.anInt2241) == 0) {
                           Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3, var4);
                        } else {
                           Class68.method1263(var23.anInt1055, Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var6, var3, var4);
                        }
                     }

                     var23.aClass140_1049.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var23.anInt1054 - Class129_Sub1.anInt2697, var23.anInt1057 - Class3_Sub28_Sub13.anInt3657, var23.anInt1045 - Class3_Sub13_Sub30.anInt3363, var23.aLong1048, var5, (Class127_Sub1)null);
                  }

                  for(var11 = 0; var11 < var21.anInt2223; ++var11) {
                     var25 = var21.aClass25Array2221[var11];
                     if(var25 != null) {
                        if(HDToolKit.highDetail) {
                           Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3, var4);
                        }

                        var25.aClass140_479.animate(var25.anInt496, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var25.anInt482 - Class129_Sub1.anInt2697, var25.anInt489 - Class3_Sub28_Sub13.anInt3657, var25.anInt484 - Class3_Sub13_Sub30.anInt3363, var25.aLong498, var5, (Class127_Sub1)null);
                     }
                  }

                  if(HDToolKit.highDetail) {
                     HDToolKit.method1832(var8);
                  }
               }

               var22 = false;
               if(var2.aClass126_2240 != null) {
                  if(Class8.method846(var6, var3, var4)) {
                     Class3_Sub13_Sub18.method259(var2.aClass126_2240, var6, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var3, var4, true);
                  } else {
                     var22 = true;
                     if(var2.aClass126_2240.anInt1664 != 12345678 || Class3_Sub13_Sub21.aBoolean3261 && var5 <= Class91.anInt1302) {
                        Class3_Sub13_Sub18.method259(var2.aClass126_2240, var6, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var3, var4, false);
                     }
                  }
               } else if(var2.aClass35_2226 != null) {
                  if(Class8.method846(var6, var3, var4)) {
                     Class3_Sub21.method395(var2.aClass35_2226, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var3, var4, true);
                  } else {
                     var22 = true;
                     Class3_Sub21.method395(var2.aClass35_2226, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var3, var4, false);
                  }
               }

               if(var22) {
                  Class12 var24 = var2.aClass12_2230;
                  if(var24 != null && (var24.aLong328 & 2147483648L) != 0L) {
                     if(HDToolKit.highDetail && var24.aBoolean329) {
                        HDToolKit.method1832(var8 + 50.0F - 1.5F);
                     }

                     if(HDToolKit.highDetail) {
                        Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3, var4);
                     }

                     var24.object.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var24.anInt324 - Class129_Sub1.anInt2697, var24.anInt326 - Class3_Sub28_Sub13.anInt3657, var24.anInt330 - Class3_Sub13_Sub30.anInt3363, var24.aLong328, var5, (Class127_Sub1)null);
                     if(HDToolKit.highDetail && var24.aBoolean329) {
                        HDToolKit.method1832(var8);
                     }
                  }
               }

               var10 = 0;
               var11 = 0;
               Class70 var26 = var2.aClass70_2234;
               Class19 var13 = var2.aClass19_2233;
               if(var26 != null || var13 != null) {
                  if(Class97.anInt1375 == var3) {
                     ++var10;
                  } else if(Class97.anInt1375 < var3) {
                     var10 += 2;
                  }

                  if(Class3_Sub13_Sub27.anInt3340 == var4) {
                     var10 += 3;
                  } else if(Class3_Sub13_Sub27.anInt3340 > var4) {
                     var10 += 6;
                  }

                  var11 = NPC.anIntArray3997[var10];
                  var2.anInt2241 = Class3_Sub13_Sub26.anIntArray3321[var10];
               }

               if(var26 != null) {
                  if((var26.anInt1055 & Class3_Sub10.anIntArray2338[var10]) == 0) {
                     var2.anInt2227 = 0;
                  } else if (var26.anInt1055 == 16) {
                     var2.anInt2227 = 3;
                     var2.anInt2229 = Class3_Sub18.anIntArray2470[var10];
                     var2.anInt2232 = 3 - var2.anInt2229;
                  } else if (var26.anInt1055 == 32) {
                     var2.anInt2227 = 6;
                     var2.anInt2229 = Class140_Sub7.anIntArray2933[var10];
                     var2.anInt2232 = 6 - var2.anInt2229;
                  } else if (var26.anInt1055 == 64) {
                     var2.anInt2227 = 12;
                     var2.anInt2229 = Class3_Sub13_Sub14.anIntArray3171[var10];
                     var2.anInt2232 = 12 - var2.anInt2229;
                  } else {
                     var2.anInt2227 = 9;
                     var2.anInt2229 = Class3_Sub13_Sub23_Sub1.anIntArray4035[var10];
                     var2.anInt2232 = 9 - var2.anInt2229;
                  }

                  if((var26.anInt1055 & var11) != 0 && Class164_Sub1.method2239(var6, var3, var4, var26.anInt1055)) {
                     if(HDToolKit.highDetail) {
                        Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3, var4);
                     }

                     var26.aClass140_1049.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var26.anInt1054 - Class129_Sub1.anInt2697, var26.anInt1057 - Class3_Sub28_Sub13.anInt3657, var26.anInt1045 - Class3_Sub13_Sub30.anInt3363, var26.aLong1048, var5, (Class127_Sub1)null);
                  }

                  if((var26.anInt1059 & var11) != 0 && Class164_Sub1.method2239(var6, var3, var4, var26.anInt1059)) {
                     if(HDToolKit.highDetail) {
                        Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3, var4);
                     }

                     var26.aClass140_1052.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var26.anInt1054 - Class129_Sub1.anInt2697, var26.anInt1057 - Class3_Sub28_Sub13.anInt3657, var26.anInt1045 - Class3_Sub13_Sub30.anInt3363, var26.aLong1048, var5, (Class127_Sub1)null);
                  }
               }

               if(var13 != null && Class166.method2256(var6, var3, var4, var13.aClass140_429.method1871())) {
                  if(HDToolKit.highDetail) {
                     HDToolKit.method1832(var8 - 0.5F);
                  }

                  if((var13.anInt432 & var11) != 0) {
                     if(HDToolKit.highDetail) {
                        Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3, var4);
                     }

                     var13.aClass140_429.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var13.anInt424 - Class129_Sub1.anInt2697 + var13.anInt430, var13.anInt425 - Class3_Sub28_Sub13.anInt3657, var13.anInt427 - Class3_Sub13_Sub30.anInt3363 + var13.anInt426, var13.aLong428, var5, (Class127_Sub1)null);
                  } else if(var13.anInt432 == 256) {
                     var14 = var13.anInt424 - Class129_Sub1.anInt2697;
                     var15 = var13.anInt425 - Class3_Sub28_Sub13.anInt3657;
                     var16 = var13.anInt427 - Class3_Sub13_Sub30.anInt3363;
                     var17 = var13.anInt420;
                     if(var17 == 1 || var17 == 2) {
                        var18 = -var14;
                     } else {
                        var18 = var14;
                     }

                     int var19;
                     if(var17 == 2 || var17 == 3) {
                        var19 = -var16;
                     } else {
                        var19 = var16;
                     }

                     if(var19 < var18) {
                        if(HDToolKit.highDetail) {
                           Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3, var4);
                        }

                        var13.aClass140_429.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var14 + var13.anInt430, var15, var16 + var13.anInt426, var13.aLong428, var5, (Class127_Sub1)null);
                     } else if(var13.aClass140_423 != null) {
                        if(HDToolKit.highDetail) {
                           Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3, var4);
                        }

                        var13.aClass140_423.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var14, var15, var16, var13.aLong428, var5, (Class127_Sub1)null);
                     }
                  }

                  if(HDToolKit.highDetail) {
                     HDToolKit.method1832(var8);
                  }
               }

               if(var22) {
                  Class12 var30 = var2.aClass12_2230;
                  if(var30 != null && (var30.aLong328 & 2147483648L) == 0L) {
                     if(HDToolKit.highDetail && var30.aBoolean329) {
                        HDToolKit.method1832(var8 + 50.0F - 1.5F);
                     }

                     if(HDToolKit.highDetail) {
                        Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3, var4);
                     }

                     var30.object.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var30.anInt324 - Class129_Sub1.anInt2697, var30.anInt326 - Class3_Sub28_Sub13.anInt3657, var30.anInt330 - Class3_Sub13_Sub30.anInt3363, var30.aLong328, var5, (Class127_Sub1)null);
                     if(HDToolKit.highDetail && var30.aBoolean329) {
                        HDToolKit.method1832(var8);
                     }
                  }

                  Class72 var28 = var2.aClass72_2245;
                  if(var28 != null && var28.anInt1077 == 0) {
                     if(HDToolKit.highDetail) {
                        Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3, var4);
                     }

                     if(var28.aClass140_1067 != null) {
                        var28.aClass140_1067.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var28.anInt1078 - Class129_Sub1.anInt2697, var28.anInt1068 - Class3_Sub28_Sub13.anInt3657, var28.anInt1075 - Class3_Sub13_Sub30.anInt3363, var28.aLong1079, var5, (Class127_Sub1)null);
                     }

                     if(var28.aClass140_1069 != null) {
                        var28.aClass140_1069.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var28.anInt1078 - Class129_Sub1.anInt2697, var28.anInt1068 - Class3_Sub28_Sub13.anInt3657, var28.anInt1075 - Class3_Sub13_Sub30.anInt3363, var28.aLong1079, var5, (Class127_Sub1)null);
                     }

                     if(var28.aClass140_1073 != null) {
                        var28.aClass140_1073.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var28.anInt1078 - Class129_Sub1.anInt2697, var28.anInt1068 - Class3_Sub28_Sub13.anInt3657, var28.anInt1075 - Class3_Sub13_Sub30.anInt3363, var28.aLong1079, var5, (Class127_Sub1)null);
                     }
                  }
               }

               var14 = var2.anInt2228;
               if(var14 != 0) {
                  if(var3 < Class97.anInt1375 && (var14 & 4) != 0) {
                     var35 = var7[var3 + 1][var4];
                     if(var35 != null && var35.aBoolean2225) {
                        Class163_Sub1.aClass61_2990.method1215(var35);
                     }
                  }

                  if(var4 < Class3_Sub13_Sub27.anInt3340 && (var14 & 2) != 0) {
                     var35 = var7[var3][var4 + 1];
                     if(var35 != null && var35.aBoolean2225) {
                        Class163_Sub1.aClass61_2990.method1215(var35);
                     }
                  }

                  if(var3 > Class97.anInt1375 && (var14 & 1) != 0) {
                     var35 = var7[var3 - 1][var4];
                     if(var35 != null && var35.aBoolean2225) {
                        Class163_Sub1.aClass61_2990.method1215(var35);
                     }
                  }

                  if(var4 > Class3_Sub13_Sub27.anInt3340 && (var14 & 8) != 0) {
                     var35 = var7[var3][var4 - 1];
                     if(var35 != null && var35.aBoolean2225) {
                        Class163_Sub1.aClass61_2990.method1215(var35);
                     }
                  }
               }
            }

            int var27;
            if(var2.anInt2227 != 0) {
               var22 = true;

               for(var10 = 0; var10 < var2.anInt2223; ++var10) {
                  if(var2.aClass25Array2221[var10].anInt490 != Class3_Sub28_Sub1.anInt3539 && (var2.anIntArray2237[var10] & var2.anInt2227) == var2.anInt2229) {
                     var22 = false;
                     break;
                  }
               }

               if(var22) {
                  var23 = var2.aClass70_2234;
                  if(Class164_Sub1.method2239(var6, var3, var4, var23.anInt1055)) {
                     if(HDToolKit.highDetail) {
                        label736: {
                           if((var23.aLong1048 & 1032192L) == 16384L) {
                              var11 = var23.anInt1054 - Class129_Sub1.anInt2697;
                              var12 = var23.anInt1045 - Class3_Sub13_Sub30.anInt3363;
                              var27 = (int)(var23.aLong1048 >> 20 & 3L);
                              if(var27 == 0) {
                                 var11 -= 64;
                                 var12 += 64;
                                 if(var12 < var11 && var3 > 0 && var4 < Class3_Sub13_Sub15.anInt3179 - 1) {
                                    Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3 - 1, var4 + 1);
                                    break label736;
                                 }
                              } else if(var27 == 1) {
                                 var11 += 64;
                                 var12 += 64;
                                 if(var12 < -var11 && var3 < IOHandler.anInt1234 - 1 && var4 < Class3_Sub13_Sub15.anInt3179 - 1) {
                                    Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3 + 1, var4 + 1);
                                    break label736;
                                 }
                              } else if(var27 == 2) {
                                 var11 += 64;
                                 var12 -= 64;
                                 if(var12 > var11 && var3 < IOHandler.anInt1234 - 1 && var4 > 0) {
                                    Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3 + 1, var4 - 1);
                                    break label736;
                                 }
                              } else {
                                 var11 -= 64;
                                 var12 -= 64;
                                 if(var12 > -var11 && var3 > 0 && var4 > 0) {
                                    Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3 - 1, var4 - 1);
                                    break label736;
                                 }
                              }
                           }

                           Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3, var4);
                        }
                     }

                     var23.aClass140_1049.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var23.anInt1054 - Class129_Sub1.anInt2697, var23.anInt1057 - Class3_Sub28_Sub13.anInt3657, var23.anInt1045 - Class3_Sub13_Sub30.anInt3363, var23.aLong1048, var5, (Class127_Sub1)null);
                  }

                  var2.anInt2227 = 0;
               }
            }

            if(var2.aBoolean2236) {
               try {
                  var9 = var2.anInt2223;
                  var2.aBoolean2236 = false;
                  var10 = 0;

                  label712:
                  for(var11 = 0; var11 < var9; ++var11) {
                     var25 = var2.aClass25Array2221[var11];
                     if(var25.anInt490 != Class3_Sub28_Sub1.anInt3539) {
                        for(var27 = var25.anInt483; var27 <= var25.anInt495; ++var27) {
                           for(var14 = var25.anInt478; var14 <= var25.anInt481; ++var14) {
                              var35 = var7[var27][var14];
                              if(var35.aBoolean2222) {
                                 var2.aBoolean2236 = true;
                                 continue label712;
                              }

                              if(var35.anInt2227 != 0) {
                                 var16 = 0;
                                 if(var27 > var25.anInt483) {
                                    ++var16;
                                 }

                                 if(var27 < var25.anInt495) {
                                    var16 += 4;
                                 }

                                 if(var14 > var25.anInt478) {
                                    var16 += 8;
                                 }

                                 if(var14 < var25.anInt481) {
                                    var16 += 2;
                                 }

                                 if((var16 & var35.anInt2227) == var2.anInt2232) {
                                    var2.aBoolean2236 = true;
                                    continue label712;
                                 }
                              }
                           }
                        }

                        Class3_Sub28_Sub10_Sub1.aClass25Array4060[var10++] = var25;
                        var27 = Class97.anInt1375 - var25.anInt483;
                        var14 = var25.anInt495 - Class97.anInt1375;
                        if(var14 > var27) {
                           var27 = var14;
                        }

                        var15 = Class3_Sub13_Sub27.anInt3340 - var25.anInt478;
                        var16 = var25.anInt481 - Class3_Sub13_Sub27.anInt3340;
                        if(var16 > var15) {
                           var25.anInt487 = var27 + var16;
                        } else {
                           var25.anInt487 = var27 + var15;
                        }
                     }
                  }

                  while(var10 > 0) {
                     var11 = -50;
                     var12 = -1;

                     for(var27 = 0; var27 < var10; ++var27) {
                        Class25 var34 = Class3_Sub28_Sub10_Sub1.aClass25Array4060[var27];
                        if(var34.anInt490 != Class3_Sub28_Sub1.anInt3539) {
                           if(var34.anInt487 > var11) {
                              var11 = var34.anInt487;
                              var12 = var27;
                           } else if(var34.anInt487 == var11) {
                              var15 = var34.anInt482 - Class129_Sub1.anInt2697;
                              var16 = var34.anInt484 - Class3_Sub13_Sub30.anInt3363;
                              var17 = Class3_Sub28_Sub10_Sub1.aClass25Array4060[var12].anInt482 - Class129_Sub1.anInt2697;
                              var18 = Class3_Sub28_Sub10_Sub1.aClass25Array4060[var12].anInt484 - Class3_Sub13_Sub30.anInt3363;
                              if(var15 * var15 + var16 * var16 > var17 * var17 + var18 * var18) {
                                 var12 = var27;
                              }
                           }
                        }
                     }

                     if(var12 == -1) {
                        break;
                     }

                     Class25 var32 = Class3_Sub28_Sub10_Sub1.aClass25Array4060[var12];
                     var32.anInt490 = Class3_Sub28_Sub1.anInt3539;
                     if(!Class3_Sub13_Sub11.method222(var6, var32.anInt483, var32.anInt495, var32.anInt478, var32.anInt481, var32.aClass140_479.method1871())) {
                        if(HDToolKit.highDetail) {
                           if((var32.aLong498 & 1032192L) == 147456L) {
                              Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3, var4);
                              var14 = var32.anInt482 - Class129_Sub1.anInt2697;
                              var15 = var32.anInt484 - Class3_Sub13_Sub30.anInt3363;
                              var16 = (int)(var32.aLong498 >> 20 & 3L);
                              if(var16 != 1 && var16 != 3) {
                                 if(var15 > var14) {
                                    Class68.method1272(var5, var3, var4 - 1, var3 + 1, var4);
                                 } else {
                                    Class68.method1272(var5, var3, var4 + 1, var3 - 1, var4);
                                 }
                              } else if(var15 > -var14) {
                                 Class68.method1272(var5, var3, var4 - 1, var3 - 1, var4);
                              } else {
                                 Class68.method1272(var5, var3, var4 + 1, var3 + 1, var4);
                              }
                           } else {
                              Class68.method1266(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var32.anInt483, var32.anInt478, var32.anInt495, var32.anInt481);
                           }
                        }

                        var32.aClass140_479.animate(var32.anInt496, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var32.anInt482 - Class129_Sub1.anInt2697, var32.anInt489 - Class3_Sub28_Sub13.anInt3657, var32.anInt484 - Class3_Sub13_Sub30.anInt3363, var32.aLong498, var5, (Class127_Sub1)null);
                     }

                     for(var14 = var32.anInt483; var14 <= var32.anInt495; ++var14) {
                        for(var15 = var32.anInt478; var15 <= var32.anInt481; ++var15) {
                           Class3_Sub2 var37 = var7[var14][var15];
                           if(var37.anInt2227 != 0) {
                              Class163_Sub1.aClass61_2990.method1215(var37);
                           } else if((var14 != var3 || var15 != var4) && var37.aBoolean2225) {
                              Class163_Sub1.aClass61_2990.method1215(var37);
                           }
                        }
                     }
                  }

                  if(var2.aBoolean2236) {
                     continue;
                  }
               } catch (Exception var20) {
                  var2.aBoolean2236 = false;
               }
            }

            if(var2.aBoolean2225 && var2.anInt2227 == 0) {
               if(var3 <= Class97.anInt1375 && var3 > Class163_Sub1_Sub1.anInt4006) {
                  var21 = var7[var3 - 1][var4];
                  if(var21 != null && var21.aBoolean2225) {
                     continue;
                  }
               }

               if(var3 >= Class97.anInt1375 && var3 < Class2.anInt67 - 1) {
                  var21 = var7[var3 + 1][var4];
                  if(var21 != null && var21.aBoolean2225) {
                     continue;
                  }
               }

               if(var4 <= Class3_Sub13_Sub27.anInt3340 && var4 > Class3_Sub28_Sub7.anInt3603) {
                  var21 = var7[var3][var4 - 1];
                  if(var21 != null && var21.aBoolean2225) {
                     continue;
                  }
               }

               if(var4 >= Class3_Sub13_Sub27.anInt3340 && var4 < Class126.anInt1665 - 1) {
                  var21 = var7[var3][var4 + 1];
                  if(var21 != null && var21.aBoolean2225) {
                     continue;
                  }
               }

               var2.aBoolean2225 = false;
               --GameShell.anInt3;
               Class72 var29 = var2.aClass72_2245;
               if(var29 != null && var29.anInt1077 != 0) {
                  if(HDToolKit.highDetail) {
                     Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3, var4);
                  }

                  if(var29.aClass140_1067 != null) {
                     var29.aClass140_1067.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var29.anInt1078 - Class129_Sub1.anInt2697, var29.anInt1068 - Class3_Sub28_Sub13.anInt3657 - var29.anInt1077, var29.anInt1075 - Class3_Sub13_Sub30.anInt3363, var29.aLong1079, var5, (Class127_Sub1)null);
                  }

                  if(var29.aClass140_1069 != null) {
                     var29.aClass140_1069.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var29.anInt1078 - Class129_Sub1.anInt2697, var29.anInt1068 - Class3_Sub28_Sub13.anInt3657 - var29.anInt1077, var29.anInt1075 - Class3_Sub13_Sub30.anInt3363, var29.aLong1079, var5, (Class127_Sub1)null);
                  }

                  if(var29.aClass140_1073 != null) {
                     var29.aClass140_1073.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var29.anInt1078 - Class129_Sub1.anInt2697, var29.anInt1068 - Class3_Sub28_Sub13.anInt3657 - var29.anInt1077, var29.anInt1075 - Class3_Sub13_Sub30.anInt3363, var29.aLong1079, var5, (Class127_Sub1)null);
                  }
               }

               if(var2.anInt2241 != 0) {
                  Class19 var36 = var2.aClass19_2233;
                  if(var36 != null && Class166.method2256(var6, var3, var4, var36.aClass140_429.method1871())) {
                     if((var36.anInt432 & var2.anInt2241) != 0) {
                        if(HDToolKit.highDetail) {
                           Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3, var4);
                        }

                        var36.aClass140_429.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var36.anInt424 - Class129_Sub1.anInt2697 + var36.anInt430, var36.anInt425 - Class3_Sub28_Sub13.anInt3657, var36.anInt427 - Class3_Sub13_Sub30.anInt3363 + var36.anInt426, var36.aLong428, var5, (Class127_Sub1)null);
                     } else if(var36.anInt432 == 256) {
                        var11 = var36.anInt424 - Class129_Sub1.anInt2697;
                        var12 = var36.anInt425 - Class3_Sub28_Sub13.anInt3657;
                        var27 = var36.anInt427 - Class3_Sub13_Sub30.anInt3363;
                        var14 = var36.anInt420;
                        if(var14 == 1 || var14 == 2) {
                           var15 = -var11;
                        } else {
                           var15 = var11;
                        }

                        if(var14 == 2 || var14 == 3) {
                           var16 = -var27;
                        } else {
                           var16 = var27;
                        }

                        if(var16 >= var15) {
                           if(HDToolKit.highDetail) {
                              Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3, var4);
                           }

                           var36.aClass140_429.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var11 + var36.anInt430, var12, var27 + var36.anInt426, var36.aLong428, var5, (Class127_Sub1)null);
                        } else if(var36.aClass140_423 != null) {
                           if(HDToolKit.highDetail) {
                              Class68.method1268(Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var5, var3, var4);
                           }

                           var36.aClass140_423.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var11, var12, var27, var36.aLong428, var5, (Class127_Sub1)null);
                        }
                     }
                  }

                  Class70 var31 = var2.aClass70_2234;
                  if(var31 != null) {
                     if((var31.anInt1059 & var2.anInt2241) != 0 && Class164_Sub1.method2239(var6, var3, var4, var31.anInt1059)) {
                        if(HDToolKit.highDetail) {
                           Class68.method1263(var31.anInt1059, Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var6, var3, var4);
                        }

                        var31.aClass140_1052.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var31.anInt1054 - Class129_Sub1.anInt2697, var31.anInt1057 - Class3_Sub28_Sub13.anInt3657, var31.anInt1045 - Class3_Sub13_Sub30.anInt3363, var31.aLong1048, var5, (Class127_Sub1)null);
                     }

                     if((var31.anInt1055 & var2.anInt2241) != 0 && Class164_Sub1.method2239(var6, var3, var4, var31.anInt1055)) {
                        if(HDToolKit.highDetail) {
                           Class68.method1263(var31.anInt1055, Class129_Sub1.anInt2697, Class3_Sub28_Sub13.anInt3657, Class3_Sub13_Sub30.anInt3363, var6, var3, var4);
                        }

                        var31.aClass140_1049.animate(0, Class60.anInt936, Class69.anInt1037, Class3_Sub13_Sub34.anInt3417, Class3_Sub13_Sub13.anInt3153, var31.anInt1054 - Class129_Sub1.anInt2697, var31.anInt1057 - Class3_Sub28_Sub13.anInt3657, var31.anInt1045 - Class3_Sub13_Sub30.anInt3363, var31.aLong1048, var5, (Class127_Sub1)null);
                     }
                  }
               }

               Class3_Sub2 var33;
               if(var5 < Class3_Sub17.anInt2456 - 1) {
                  var33 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var5 + 1][var3][var4];
                  if(var33 != null && var33.aBoolean2225) {
                     Class163_Sub1.aClass61_2990.method1215(var33);
                  }
               }

               if(var3 < Class97.anInt1375) {
                  var33 = var7[var3 + 1][var4];
                  if(var33 != null && var33.aBoolean2225) {
                     Class163_Sub1.aClass61_2990.method1215(var33);
                  }
               }

               if(var4 < Class3_Sub13_Sub27.anInt3340) {
                  var33 = var7[var3][var4 + 1];
                  if(var33 != null && var33.aBoolean2225) {
                     Class163_Sub1.aClass61_2990.method1215(var33);
                  }
               }

               if(var3 > Class97.anInt1375) {
                  var33 = var7[var3 - 1][var4];
                  if(var33 != null && var33.aBoolean2225) {
                     Class163_Sub1.aClass61_2990.method1215(var33);
                  }
               }

               if(var4 > Class3_Sub13_Sub27.anInt3340) {
                  var33 = var7[var3][var4 - 1];
                  if(var33 != null && var33.aBoolean2225) {
                     Class163_Sub1.aClass61_2990.method1215(var33);
                  }
               }
            }
         }
      }
   }

   static void method2074(int var0, int var1, int var2, int var3, int var4, int var6) {
      try {
         RSInterface var7 = Class3_Sub28_Sub16.method638(var0, var1);
         if(null != var7 && null != var7.anObjectArray203) {
            CS2Script var8 = new CS2Script();
            var8.aClass11_2449 = var7;
            var8.arguments = var7.anObjectArray203;
            Class43.method1065(var8);
         }

         RSInterface.anInt278 = var1;
         Class69.anInt1038 = var3;
         Class3_Sub30_Sub1.anInt872 = var0;
         Class164.anInt2051 = var2;
         GameObject.aBoolean1837 = true;
         Class144.anInt1887 = var4;
         Class3_Sub28_Sub5.anInt3590 = var6;
         Class20.method909(126, var7);
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "ub.C(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + -120 + ',' + var6 + ')');
      }
   }

   static Class29 method2076(int var0, int var1) {
      try {
         Class29 var2 = (Class29)Class136.aClass93_1772.get((long)var1);
         if(var2 == null) {
            byte[] var3 = Class3_Sub13_Sub13.aClass153_3154.getFile(16, var1);
            if(var0 > -4) {
               return (Class29)null;
            } else {
               var2 = new Class29();
               if(null != var3) {
                  var2.method970(new RSByteBuffer(var3));
               }

               Class136.aClass93_1772.put((byte)-108, var2, (long)var1);
               return var2;
            }
         } else {
            return var2;
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "ub.D(" + var0 + ',' + var1 + ')');
      }
   }

   static void method2077() {
      try {
         Class3_Sub31.aClass93_2604.method1524();
         Class27.aClass93_511.method1524();
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ub.B(" + true + ')');
      }
   }

}
