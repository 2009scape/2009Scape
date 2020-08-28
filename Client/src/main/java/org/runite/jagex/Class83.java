package org.runite.jagex;

final class Class83 {

   private final CacheIndex aClass153_1153;
   static Class3_Sub27 aClass3_Sub27_1154;
   private final Class130 aClass130_1155 = new Class130(256);
   static int anInt1156 = 0;
   private final CacheIndex aClass153_1157;
   static boolean aBoolean1158 = false;
   private final Class130 aClass130_1159 = new Class130(256);


   static void method1410(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10) {
      try {
         int var11 = var4 - var6;
         int var12 = -var3 + var2;
         if(Class23.anInt455 > var4) {
            ++var11;
         }

         if(Class108.anInt1460 > var2) {
            ++var12;
         }

         if(var10 != -12541) {
            method1411(-14);
         }

         int var13;
         int var14;
         int var15;
         int var17;
         int var16;
         int var19;
         int var21;
         int var20;
         int var22;
         int var25;
         int var24;
         int var26;
         int var28;
         int var31;
         int var30;
         int var32;
         int var33;
         int[][] var41;
         for(var13 = 0; var13 < var11; ++var13) {
            var14 = var13 * var8 + var1 >> 16;
            var15 = (var13 + 1) * var8 + var1 >> 16;
            var16 = -var14 + var15;
            if(var16 > 0) {
               var17 = var13 - -var6 >> 6;
               if(0 <= var17 && var17 <= Class146.anIntArrayArrayArray1903.length + -1) {
                  var14 += var5;
                  var41 = Class146.anIntArrayArrayArray1903[var17];
                  byte[][] var45 = RenderAnimationDefinition.aByteArrayArrayArray383[var17];
                  byte[][] var42 = Class3_Sub10.aByteArrayArrayArray2339[var17];
                  byte[][] var23 = Class36.aByteArrayArrayArray640[var17];
                  byte[][] var43 = CS2Script.aByteArrayArrayArray2452[var17];
                  var15 += var5;
                  byte[][] var46 = Class3_Sub13_Sub33.aByteArrayArrayArray3390[var17];

                  for(var24 = 0; var12 > var24; ++var24) {
                     var25 = var7 * var24 + var9 >> 16;
                     var26 = var9 - -((1 + var24) * var7) >> 16;
                     int var27 = -var25 + var26;
                     if(var27 > 0) {
                        var26 += var0;
                        var28 = var3 + var24 >> 6;
                        int var29 = 63 & var3 + var24;
                        var25 += var0;
                        var30 = var13 + var6 & 63;
                        var31 = (var29 << 6) + var30;
                        if(0 <= var28 && var41.length + -1 >= var28 && null != var41[var28]) {
                           var32 = var41[var28][var31];
                        } else {
                           if(RSByteBuffer.aClass3_Sub28_Sub3_2600.anInt3550 == -1) {
                              if((var6 + var13 & 4) == (4 & var24 + var3)) {
                                 var32 = Class84.anIntArray1161[1 + Class3_Sub13_Sub6.anInt3081];
                              } else {
                                 var32 = 4936552;
                              }
                           } else {
                              var32 = RSByteBuffer.aClass3_Sub28_Sub3_2600.anInt3550;
                           }

                           if(var28 < 0 || var28 > var41.length + -1) {
                              if(var32 == 0) {
                                 var32 = 1;
                              }

                              Class74.method1323(var14, var25, var16, var27, var32);
                              continue;
                           }
                        }

                        var33 = var45[var28] == null?0:Class84.anIntArray1161[var45[var28][var31] & 255];
                        if(var32 == 0) {
                           var32 = 1;
                        }

                        int var34 = var46[var28] != null?Class84.anIntArray1161[255 & var46[var28][var31]]:0;
                        int var36;
                        if(var33 == 0 && var34 == 0) {
                           Class74.method1323(var14, var25, var16, var27, var32);
                        } else {
                           byte var35;
                           if(0 != var33) {
                              if(var33 == -1) {
                                 var33 = 1;
                              }

                              var35 = var42[var28] != null?var42[var28][var31]:0;
                              var36 = var35 & 252;
                              if(var36 != 0 && var16 > 1 && var27 > 1) {
                                 Class168.method2272(Class74.anIntArray1100, var33, var14, var35 & 3, var32, var36 >> 2, var27, var16, var25, true);
                              } else {
                                 Class74.method1323(var14, var25, var16, var27, var33);
                              }
                           }

                           if(var34 != 0) {
                              if(var34 == -1) {
                                 var34 = var32;
                              }

                              var35 = var43[var28][var31];
                              var36 = 252 & var35;
                              if(var36 == 0 || var16 <= 1 || 1 >= var27) {
                                 Class74.method1323(var14, var25, var16, var27, var34);
                              }

                              Class168.method2272(Class74.anIntArray1100, var34, var14, var35 & 3, 0, var36 >> 2, var27, var16, var25, var33 == 0);
                           }
                        }

                        if(var23[var28] != null) {
                           int var49 = var23[var28][var31] & 255;
                           if(var49 != 0) {
                              if(1 == var16) {
                                 var36 = var14;
                              } else {
                                 var36 = var15 - 1;
                              }

                              int var37;
                              if(1 == var27) {
                                 var37 = var25;
                              } else {
                                 var37 = -1 + var26;
                              }

                              int var38 = 13421772;
                              if(5 <= var49 && 8 >= var49 || var49 >= 13 && 16 >= var49 || 21 <= var49 && var49 <= 24 || var49 == 27 || 28 == var49) {
                                 var38 = 13369344;
                                 var49 -= 4;
                              }

                              if(1 == var49) {
                                 Class74.method1318(var14, var25, var27, var38);
                              } else if(2 == var49) {
                                 Class74.method1317(var14, var25, var16, var38);
                              } else if(3 == var49) {
                                 Class74.method1318(var36, var25, var27, var38);
                              } else if (var49 == 4) {
                                 Class74.method1317(var14, var37, var16, var38);
                              } else if (var49 == 9) {
                                 Class74.method1318(var14, var25, var27, 16777215);
                                 Class74.method1317(var14, var25, var16, var38);
                              } else if (var49 == 10) {
                                 Class74.method1318(var36, var25, var27, 16777215);
                                 Class74.method1317(var14, var25, var16, var38);
                              } else if (var49 == 11) {
                                 Class74.method1318(var36, var25, var27, 16777215);
                                 Class74.method1317(var14, var37, var16, var38);
                              } else if (var49 == 12) {
                                 Class74.method1318(var14, var25, var27, 16777215);
                                 Class74.method1317(var14, var37, var16, var38);
                              } else if (var49 == 17) {
                                 Class74.method1317(var14, var25, 1, var38);
                              } else if (var49 == 18) {
                                 Class74.method1317(var36, var25, 1, var38);
                              } else if (var49 == 19) {
                                 Class74.method1317(var36, var37, 1, var38);
                              } else if (var49 == 20) {
                                 Class74.method1317(var14, var37, 1, var38);
                              } else {
                                 int var39;
                                 if (25 == var49) {
                                    for (var39 = 0; var27 > var39; ++var39) {
                                       Class74.method1317(var39 + var14, -var39 + var37, 1, var38);
                                    }
                                 } else if (26 == var49) {
                                    for (var39 = 0; var39 < var27; ++var39) {
                                       Class74.method1317(var39 + var14, var25 + var39, 1, var38);
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               } else {
                  var14 += var5;

                  for(int var18 = 0; var18 < var12; ++var18) {
                     if(-1 != RSByteBuffer.aClass3_Sub28_Sub3_2600.anInt3550) {
                        var19 = RSByteBuffer.aClass3_Sub28_Sub3_2600.anInt3550;
                     } else if((var13 - -var6 & 4) == (4 & var18 + var3)) {
                        var19 = Class84.anIntArray1161[1 + Class3_Sub13_Sub6.anInt3081];
                     } else {
                        var19 = 4936552;
                     }

                     if(var19 == 0) {
                        var19 = 1;
                     }

                     var20 = (var7 * var18 + var9 >> 16) + var0;
                     var21 = var0 + ((var18 + 1) * var7 + var9 >> 16);
                     var22 = var21 + -var20;
                     Class74.method1323(var14, var20, var16, var22, var19);
                  }

               }
            }
         }

         for(var13 = -2; 2 + var11 > var13; ++var13) {
            var14 = var1 - -(var13 * var8) >> 16;
            var15 = var8 * (var13 + 1) + var1 >> 16;
            var16 = -var14 + var15;
            if(var16 > 0) {
               var14 += var5;
               var17 = var6 + var13 >> 6;
               if(var17 >= 0 && Class29.anIntArrayArrayArray558.length + -1 >= var17) {
                  var41 = Class29.anIntArrayArrayArray558[var17];

                  for(var19 = -2; var12 - -2 > var19; ++var19) {
                     var20 = var9 - -(var19 * var7) >> 16;
                     var21 = var9 - -((var19 + 1) * var7) >> 16;
                     var22 = var21 + -var20;
                     if(var22 > 0) {
                        var20 += var0;
                        int var44 = var19 - -var3 >> 6;
                        if(var44 >= 0 && var44 <= -1 + var41.length) {
                           var24 = ((63 & var3 + var19) << 6) - -(var13 - -var6 & 63);
                           if(null != var41[var44]) {
                              var25 = var41[var44][var24];
                              var26 = 16383 & var25;
                              if(var26 != 0) {
                                 var28 = ('\ud228' & var25) >> 14;
                                 Class2 var47 = Class3_Sub28_Sub6.c(-1 + var26);
                                 LDIndexedSprite var48 = var47.method77(var28, (byte)-71);
                                 if(var48 != null) {
                                    var31 = var22 * var48.anInt1468 / 4;
                                    var30 = var16 * var48.anInt1461 / 4;
                                    if(var47.aBoolean69) {
                                       var32 = var25 >> 16 & 15;
                                       var33 = (16103184 & var25) >> 20;
                                       if((1 & var28) == 1) {
                                          var28 = var32;
                                          var32 = var33;
                                          var33 = var28;
                                       }

                                       var30 = var16 * var32;
                                       var31 = var22 * var33;
                                    }

                                    if(var30 != 0 && var31 != 0) {
                                       if(var47.anInt61 == 0) {
                                          var48.method1677(var14, -var31 + var20 - -var22, var30, var31);
                                       } else {
                                          var48.method1669(var14, var20 - (var31 - var22), var30, var31, var47.anInt61);
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

      } catch (RuntimeException var40) {
         throw Class44.clientError(var40, "le.C(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + var9 + ',' + var10 + ')');
      }
   }

   static int method1411(int var0) {
      try {
         if(Class3_Sub13_Sub10.aFrame3121 == null) {
            if(HDToolKit.highDetail && Class3_Sub15.aBoolean2427) {
               return 2;
            } else {
               if(var0 != 0) {
                  aBoolean1158 = false;
               }

               return HDToolKit.highDetail ?1:0;
            }
         } else {
            return 3;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "le.D(" + var0 + ')');
      }
   }

   private Class3_Sub12_Sub1 method1412(int[] var1, int var3, int var4) {
      try {
         int var5 = var4 ^ (var3 >>> 12 | var3 << 4 & '\ufff3');
         var5 |= var3 << 16;
         long var6 = (long)var5;
         Class3_Sub12_Sub1 var8 = (Class3_Sub12_Sub1)this.aClass130_1159.method1780(var6, 0);
         if(var8 != null) {
            return var8;
         } else if(null != var1 && var1[0] <= 0) {
            return null;
         } else {
            Class135 var9 = Class135.method1811(this.aClass153_1153, var3, var4);
            if(null == var9) {
               return null;
            } else {
               var8 = var9.method1812();
               this.aClass130_1159.method1779(var8, var6);
               if(var1 != null) {
                  var1[0] -= var8.aByteArray3030.length;
               }

               return var8;
            }
         }
      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "le.B(" + (var1 != null?"{...}":"null") + ',' + 31947 + ',' + var3 + ',' + var4 + ')');
      }
   }

   final Class3_Sub12_Sub1 method1413(int var1, int[] var3) {
      try {
         if(1 == this.aClass153_1153.method2121()) {
            return this.method1412(var3, 0, var1);
         } else if (this.aClass153_1153.getFileAmount(var1, (byte) 83) == 1) {
            return this.method1412(var3, var1, 0);
         } else {

             throw new RuntimeException();
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "le.A(" + var1 + ',' + 33 + ',' + (var3 != null?"{...}":"null") + ')');
      }
   }

   public static void method1414(int var0) {
      try {
         if(var0 < 86) {
            method1410(73, 59, 119, 60, 89, -42, -25, 37, 88, -62, 69);
         }

         aClass3_Sub27_1154 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "le.E(" + var0 + ')');
      }
   }

   private Class3_Sub12_Sub1 method1415(int var1, int[] var2, int var3) {
      try {
         int var5 = var3 ^ (var1 >>> 12 | '\ufff3' & var1 << 4);
         var5 |= var1 << 16;
         long var6 = (long)var5 ^ 4294967296L;
         Class3_Sub12_Sub1 var8 = (Class3_Sub12_Sub1)this.aClass130_1159.method1780(var6, 0);
         if(null != var8) {
            return var8;
         } else if(var2 != null && var2[0] <= 0) {
            return null;
         } else {
            Class3_Sub14 var9 = (Class3_Sub14)this.aClass130_1155.method1780(var6, 0);
            if(null == var9) {
               var9 = Class3_Sub14.method363(this.aClass153_1157, var1, var3);
               if(null == var9) {
                  return null;
               }

               this.aClass130_1155.method1779(var9, var6);
            }

            var8 = var9.method359(var2);
            if(null == var8) {
               return null;
            } else {
               var9.method86(-1024);
               this.aClass130_1159.method1779(var8, var6);

               return var8;
            }
         }
      } catch (RuntimeException var10) {
    	 // var10.printStackTrace();
    	  throw Class44.clientError(var10, "le.F(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ',' + (byte) 11 + ')');
      }
   }

   final Class3_Sub12_Sub1 method1416(int var2, int[] var3) {
      try {
         if(this.aClass153_1157.method2121() == 1) {
            return this.method1415(0, var3, var2);
         } else if(this.aClass153_1157.getFileAmount(var2, (byte) 99) == 1) {
            return this.method1415(var2, var3, 0);
         } else {
            throw new RuntimeException();
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "le.G(" + 10089 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ')');
      }
   }

   Class83(CacheIndex var1, CacheIndex var2) {
      try {
         this.aClass153_1153 = var1;
         this.aClass153_1157 = var2;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "le.<init>(" + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

}
