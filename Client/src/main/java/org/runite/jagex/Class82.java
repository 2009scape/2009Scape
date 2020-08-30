package org.runite.jagex;

import java.util.Objects;

final class Class82 {

   private final int[] anIntArray1144;
   private final Class3_Sub13 aClass3_Sub13_1145;
   static Class93 aClass93_1146 = new Class93(64);
   private final Class3_Sub13[] aClass3_Sub13Array1147;
   private final Class3_Sub13 aClass3_Sub13_1148;
   private final int[] anIntArray1149;
   static int anInt1150 = -1;
   static RSString aClass94_1151 = RSString.createRSString("settings=");
   static int anInt1152;


   final int[] method1404(int var1, boolean var2, int var3, double var4, CacheIndex var7, Interface2 var8, boolean var9) {
      try {
         GameObject.method1859(var4);
         Class17.anInterface2_408 = var8;
         Class104.aClass153_2172 = var7;
         Class3_Sub13_Sub3.method180(-1, var1, var3);

         int var11;
         for(var11 = 0; var11 < this.aClass3_Sub13Array1147.length; ++var11) {
            this.aClass3_Sub13Array1147[var11].method160(var1, var3);
         }

         int[] var10 = new int[var1 * var3];
         int var12;
         byte var13;
         if(var9) {
            var13 = -1;
            var12 = -1;
            var11 = var3 - 1;
         } else {
            var13 = 1;
            var11 = 0;
            var12 = var3;
         }

         int var14 = 0;

         int var15;
         for(var15 = 0; var1 > var15; ++var15) {
            if(var2) {
               var14 = var15;
            }

            int[] var17;
            int[] var16;
            int[] var18;
            if(this.aClass3_Sub13_1145.aBoolean2375) {
               int[] var19 = this.aClass3_Sub13_1145.method154(var15, (byte)109);
               var16 = var19;
               var17 = var19;
               var18 = var19;
            } else {
               int[][] var24 = this.aClass3_Sub13_1145.method166(-1, var15);
               var16 = var24[0];
               var18 = var24[2];
               var17 = var24[1];
            }

            for(int var25 = var11; var25 != var12; var25 += var13) {
               int var20 = var16[var25] >> 4;
               if(var20 > 255) {
                  var20 = 255;
               }

               if(var20 < 0) {
                  var20 = 0;
               }

               var20 = Class3_Sub30_Sub1.anIntArray3804[var20];
               int var22 = var18[var25] >> 4;
               int var21 = var17[var25] >> 4;
               if(var21 > 255) {
                  var21 = 255;
               }

               if(0 > var21) {
                  var21 = 0;
               }

               if(var22 > 255) {
                  var22 = 255;
               }

               var21 = Class3_Sub30_Sub1.anIntArray3804[var21];
               if(var22 < 0) {
                  var22 = 0;
               }

               var22 = Class3_Sub30_Sub1.anIntArray3804[var22];
               var10[var14++] = (var20 << 16) - -(var21 << 8) + var22;
               if(var2) {
                  var14 += var3 + -1;
               }
            }
         }

         for(var15 = 0; var15 < this.aClass3_Sub13Array1147.length; ++var15) {
            this.aClass3_Sub13Array1147[var15].method161((byte)-45);
         }

         return var10;
      } catch (RuntimeException var23) {
         throw Class44.clientError(var23, "lc.C(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + 327680 + ',' + (var7 != null?"{...}":"null") + ',' + (var8 != null?"{...}":"null") + ',' + var9 + ')');
      }
   }

   static void method1405(int var0, int var1, int var2, int var3, int var4, int var5, int var6) {
      try {
         Class3_Sub13_Sub39.anInt3464 = 0;

         int var7;
         int var15;
         int var19;
         int var21;
         int var22;
         int var29;
         int var32;
         for(var7 = -1; var7 < Class159.localPlayerCount + Class163.localNPCCount; ++var7) {
            Object var8;
            if(var7 == -1) {
               var8 = Class102.player;
            } else if(var7 < Class159.localPlayerCount) {
               var8 = Class3_Sub13_Sub22.players[Class56.localPlayerIndexes[var7]];
            } else {
               var8 = Class3_Sub13_Sub24.npcs[Class15.localNPCIndexes[-Class159.localPlayerCount + var7]];
            }

            if(null != var8 && ((Class140_Sub4)var8).hasDefinitions()) {
               NPCDefinition var9;
               if(var8 instanceof NPC) {
                  var9 = ((NPC)var8).definition;
                  if(null != var9.childNPCs) {
                     var9 = var9.method1471((byte)-93);
                  }

                  if(var9 == null) {
                     continue;
                  }
               }

               int var12;
               if(var7 < Class159.localPlayerCount) {
                  var19 = 30;
                  Player var10 = (Player)var8;
                  if(var10.skullIcon != -1 || -1 != var10.headIcon) {
                     Class107.method1647(var4 >> 1, var3, (Class140_Sub4)var8, var5, ((Class140_Sub4)var8).method1975(var6 ^ -28716) - -15, var1 >> 1);
                     if(-1 < Class32.anInt590) {
                        if(var10.skullIcon != -1) {
                           Class3_Sub13_Sub31.aClass3_Sub28_Sub16Array3373[var10.skullIcon].method643(-12 + Class32.anInt590 + var2, -var19 + var0 + Class3_Sub1.anInt2208);
                           var19 += 25;
                        }

                        if(var10.headIcon != -1) {
                           NPC.aClass3_Sub28_Sub16Array3977[var10.headIcon].method643(-12 + var2 + Class32.anInt590, var0 - (-Class3_Sub1.anInt2208 + var19));
                           var19 += 25;
                        }
                     }
                  }

                  if(var7 >= 0) {
                     Class96[] var11 = ClientErrorException.aClass96Array2114;

                     for(var12 = 0; var12 < var11.length; ++var12) {
                        Class96 var13 = var11[var12];
                        if(null != var13 && var13.anInt1360 == 10 && Class56.localPlayerIndexes[var7] == var13.anInt1359) {
                           Class107.method1647(var4 >> 1, var3, (Class140_Sub4)var8, var5, ((Class140_Sub4)var8).method1975(var6 ^ -28716) - -15, var1 >> 1);
                           if(Class32.anInt590 > -1) {
                              Class166.aClass3_Sub28_Sub16Array2072[var13.anInt1351].method643(var2 - (-Class32.anInt590 + 12), var0 + (Class3_Sub1.anInt2208 - var19));
                           }
                        }
                     }
                  }
               } else {
                  var9 = ((NPC)var8).definition;
                  if(var9.childNPCs != null) {
                     var9 = var9.method1471((byte)102);
                  }

                  if(Objects.requireNonNull(var9).anInt1269 >= 0 && NPC.aClass3_Sub28_Sub16Array3977.length > var9.anInt1269) {
                     if(var9.anInt1265 == -1) {
                        var22 = 15 + ((Class140_Sub4)var8).method1975(27855);
                     } else {
                        var22 = 15 + var9.anInt1265;
                     }

                     Class107.method1647(var4 >> 1, var3, (Class140_Sub4)var8, var5, var22, var1 >> 1);
                     if(Class32.anInt590 > -1) {
                        NPC.aClass3_Sub28_Sub16Array3977[var9.anInt1269].method643(var2 - -Class32.anInt590 - 12, -30 + var0 - -Class3_Sub1.anInt2208);
                     }
                  }

                  Class96[] var20 = ClientErrorException.aClass96Array2114;

                  for(var21 = 0; var20.length > var21; ++var21) {
                     Class96 var24 = var20[var21];
                     if(null != var24 && var24.anInt1360 == 1 && Class15.localNPCIndexes[-Class159.localPlayerCount + var7] == var24.anInt1359 && Class44.anInt719 % 20 < 10) {
                        if(-1 == var9.anInt1265) {
                           var29 = 15 + ((Class140_Sub4)var8).method1975(var6 + '\u89b4');
                        } else {
                           var29 = 15 + var9.anInt1265;
                        }

                        Class107.method1647(var4 >> 1, var3, (Class140_Sub4)var8, var5, var29, var1 >> 1);
                        if(Class32.anInt590 > -1) {
                           Class166.aClass3_Sub28_Sub16Array2072[var24.anInt1351].method643(-12 + var2 + Class32.anInt590, -28 + Class3_Sub1.anInt2208 + var0);
                        }
                     }
                  }
               }

               if(((Class140_Sub4)var8).textSpoken != null && (var7 >= Class159.localPlayerCount || Class3_Sub13_Sub8.anInt3101 == 0 || 3 == Class3_Sub13_Sub8.anInt3101 || 1 == Class3_Sub13_Sub8.anInt3101 && ItemDefinition.method1176(((Player)var8).displayName))) {
                  Class107.method1647(var4 >> 1, var3, (Class140_Sub4)var8, var5, ((Class140_Sub4)var8).method1975(27855), var1 >> 1);
                  if(-1 < Class32.anInt590 && Class3_Sub13_Sub39.anInt3464 < Class3_Sub13_Sub26.anInt3332) {
                     Class3_Sub13_Sub26.anIntArray3329[Class3_Sub13_Sub39.anInt3464] = Class168.aClass3_Sub28_Sub17_2096.method682(((Class140_Sub4)var8).textSpoken) / 2;
                     Class3_Sub13_Sub26.anIntArray3327[Class3_Sub13_Sub39.anInt3464] = Class168.aClass3_Sub28_Sub17_2096.anInt3727;
                     Class3_Sub13_Sub26.anIntArray3319[Class3_Sub13_Sub39.anInt3464] = Class32.anInt590;
                     Class3_Sub13_Sub26.anIntArray3337[Class3_Sub13_Sub39.anInt3464] = Class3_Sub1.anInt2208;
                     Class3_Sub13_Sub26.anIntArray3331[Class3_Sub13_Sub39.anInt3464] = ((Class140_Sub4)var8).textColor;
                     Class3_Sub13_Sub26.anIntArray3336[Class3_Sub13_Sub39.anInt3464] = ((Class140_Sub4)var8).textEffect;
                     Class3_Sub13_Sub26.anIntArray3318[Class3_Sub13_Sub39.anInt3464] = ((Class140_Sub4)var8).textCycle;
                     Class3_Sub13_Sub26.aClass94Array3317[Class3_Sub13_Sub39.anInt3464] = ((Class140_Sub4)var8).textSpoken;
                     ++Class3_Sub13_Sub39.anInt3464;
                  }
               }

               if(Class44.anInt719 < ((Class140_Sub4) var8).anInt2781) {
                  Class3_Sub28_Sub16 var23 = Class66.aClass3_Sub28_Sub16Array996[0];
                  Class3_Sub28_Sub16 var25 = Class66.aClass3_Sub28_Sub16Array996[1];
                  if(var8 instanceof NPC) {
                     NPC var28 = (NPC)var8;
                     Class3_Sub28_Sub16[] var31 = (Class3_Sub28_Sub16[])((Class3_Sub28_Sub16[])Class3_Sub13_Sub11.aClass93_3130.get((long)var28.definition.anInt1279));
                     if(var31 == null) {
                        var31 = Class140_Sub6.getSprites(var28.definition.anInt1279, Class140_Sub6.spritesCacheIndex);
                        if(null != var31) {
                           Class3_Sub13_Sub11.aClass93_3130.put((byte)-98, var31, (long)var28.definition.anInt1279);
                        }
                     }

                     if(null != var31 && var31.length == 2) {
                        var25 = var31[1];
                        var23 = var31[0];
                     }

                     NPCDefinition var14 = var28.definition;
                     if(-1 == var14.anInt1265) {
                        var21 = ((Class140_Sub4)var8).method1975(27855);
                     } else {
                        var21 = var14.anInt1265;
                     }
                  } else {
                     var21 = ((Class140_Sub4)var8).method1975(27855);
                  }

                  Class107.method1647(var4 >> 1, var3, (Class140_Sub4)var8, var5, var23.anInt3696 + 10 + var21, var1 >> 1);
                  if(-1 < Class32.anInt590) {
                     var12 = -(var23.anInt3707 >> 1) + Class32.anInt590 + var2;
                     var29 = Class3_Sub1.anInt2208 + var0 + -3;
                     var23.method643(var12, var29);
                     var32 = var23.anInt3707 * ((Class140_Sub4)var8).anInt2775 / 255;
                     var15 = var23.anInt3696;
                     if(HDToolKit.highDetail) {
                        Class22.method931(var12, var29, var12 + var32, var29 + var15);
                     } else {
                        Class74.method1326(var12, var29, var12 + var32, var15 + var29);
                     }

                     var25.method643(var12, var29);
                     if(HDToolKit.highDetail) {
                        Class22.method935(var2, var0, var1 + var2, var0 - -var4);
                     } else {
                        Class74.method1324(var2, var0, var1 + var2, var4 + var0);
                     }
                  }
               }

               for(var19 = 0; var19 < 4; ++var19) {
                  if(Class44.anInt719 < ((Class140_Sub4) var8).anIntArray2768[var19]) {
                     if(var8 instanceof NPC) {
                        NPC var30 = (NPC)var8;
                        NPCDefinition var26 = var30.definition;
                        if(var26.anInt1265 == -1) {
                           var22 = ((Class140_Sub4)var8).method1975(27855) / 2;
                        } else {
                           var22 = var26.anInt1265 / 2;
                        }
                     } else {
                        var22 = ((Class140_Sub4)var8).method1975(var6 ^ -28716) / 2;
                     }

                     Class107.method1647(var4 >> 1, var3, (Class140_Sub4)var8, var5, var22, var1 >> 1);
                     if(-1 < Class32.anInt590) {
                        if(var19 == 1) {
                           Class3_Sub1.anInt2208 -= 20;
                        }

                        if(var19 == 2) {
                           Class3_Sub1.anInt2208 -= 10;
                           Class32.anInt590 -= 15;
                        }

                        if(3 == var19) {
                           Class3_Sub1.anInt2208 -= 10;
                           Class32.anInt590 += 15;
                        }

                        Class75_Sub3.aClass3_Sub28_Sub16Array2656[((Class140_Sub4)var8).anIntArray2815[var19]].method643(-12 + var2 + Class32.anInt590, var0 + Class3_Sub1.anInt2208 - 12);
                        Class3_Sub13.aClass3_Sub28_Sub17_2379.method699(Class72.method1298((byte)9, ((Class140_Sub4)var8).anIntArray2836[var19]), -1 + Class32.anInt590 + var2, 3 + Class3_Sub1.anInt2208 + var0, 16777215, 0);
                     }
                  }
               }
            }
         }

         var7 = 0;
         if(var6 != -7397) {
            method1409(true);
         }

         for(; Class3_Sub13_Sub39.anInt3464 > var7; ++var7) {
            var19 = Class3_Sub13_Sub26.anIntArray3337[var7];
            int var18 = Class3_Sub13_Sub26.anIntArray3319[var7];
            var21 = Class3_Sub13_Sub26.anIntArray3327[var7];
            var22 = Class3_Sub13_Sub26.anIntArray3329[var7];
            boolean var27 = true;

            while(var27) {
               var27 = false;

               for(var29 = 0; var7 > var29; ++var29) {
                  if(Class3_Sub13_Sub26.anIntArray3337[var29] - Class3_Sub13_Sub26.anIntArray3327[var29] < 2 + var19 && -var21 + var19 < Class3_Sub13_Sub26.anIntArray3337[var29] - -2 && -var22 + var18 < Class3_Sub13_Sub26.anIntArray3319[var29] + Class3_Sub13_Sub26.anIntArray3329[var29] && Class3_Sub13_Sub26.anIntArray3319[var29] - Class3_Sub13_Sub26.anIntArray3329[var29] < var22 + var18 && -Class3_Sub13_Sub26.anIntArray3327[var29] + Class3_Sub13_Sub26.anIntArray3337[var29] < var19) {
                     var19 = Class3_Sub13_Sub26.anIntArray3337[var29] - Class3_Sub13_Sub26.anIntArray3327[var29];
                     var27 = true;
                  }
               }
            }

            Class32.anInt590 = Class3_Sub13_Sub26.anIntArray3319[var7];
            Class3_Sub1.anInt2208 = Class3_Sub13_Sub26.anIntArray3337[var7] = var19;
            RSString var33 = Class3_Sub13_Sub26.aClass94Array3317[var7];
            if(Class41.anInt688 == 0) {
               var32 = 16776960;
               if(Class3_Sub13_Sub26.anIntArray3331[var7] < 6) {
                  var32 = Class3_Sub1.anIntArray2213[Class3_Sub13_Sub26.anIntArray3331[var7]];
               }

               if(6 == Class3_Sub13_Sub26.anIntArray3331[var7]) {
                  var32 = 10 <= Class79.anInt1127 % 20?16776960:16711680;
               }

               if(Class3_Sub13_Sub26.anIntArray3331[var7] == 7) {
                  var32 = Class79.anInt1127 % 20 < 10 ?255:'\uffff';
               }

               if(8 == Class3_Sub13_Sub26.anIntArray3331[var7]) {
                  var32 = Class79.anInt1127 % 20 >= 10 ?8454016:'\ub000';
               }

               if(9 == Class3_Sub13_Sub26.anIntArray3331[var7]) {
                  var15 = -Class3_Sub13_Sub26.anIntArray3318[var7] + 150;
                  if(var15 >= 50) {
                     if(var15 >= 100) {
                        if(150 > var15) {
                           var32 = -500 - (-(5 * var15) - '\uff00');
                        }
                     } else {
                        var32 = 16776960 + 16384000 + -(327680 * var15);
                     }
                  } else {
                     var32 = var15 * 1280 + 16711680;
                  }
               }

               if(10 == Class3_Sub13_Sub26.anIntArray3331[var7]) {
                  var15 = -Class3_Sub13_Sub26.anIntArray3318[var7] + 150;
                  if(50 <= var15) {
                     if(var15 < 100) {
                        var32 = -(327680 * (-50 + var15)) + 16711935;
                     } else if(150 > var15) {
                        var32 = 327680 * var15 - (32768000 - (255 + -(5 * var15) + 500));
                     }
                  } else {
                     var32 = 16711680 + var15 * 5;
                  }
               }

               if(Class3_Sub13_Sub26.anIntArray3331[var7] == 11) {
                  var15 = 150 + -Class3_Sub13_Sub26.anIntArray3318[var7];
                  if(var15 >= 50) {
                     if(var15 >= 100) {
                        if(var15 < 150) {
                           var32 = 16777215 - var15 * 327680 + 32768000;
                        }
                     } else {
                        var32 = '\uff00' - (-(327685 * var15) - -16384250);
                     }
                  } else {
                     var32 = 16777215 - 327685 * var15;
                  }
               }

               if(0 == Class3_Sub13_Sub26.anIntArray3336[var7]) {
                  Class168.aClass3_Sub28_Sub17_2096.method699(var33, Class32.anInt590 + var2, var0 + Class3_Sub1.anInt2208, var32, 0);
               }

               if(1 == Class3_Sub13_Sub26.anIntArray3336[var7]) {
                  Class168.aClass3_Sub28_Sub17_2096.method696(var33, var2 - -Class32.anInt590, Class3_Sub1.anInt2208 + var0, var32, Class79.anInt1127);
               }

               if(Class3_Sub13_Sub26.anIntArray3336[var7] == 2) {
                  Class168.aClass3_Sub28_Sub17_2096.method695(var33, var2 - -Class32.anInt590, var0 - -Class3_Sub1.anInt2208, var32, Class79.anInt1127);
               }

               if(Class3_Sub13_Sub26.anIntArray3336[var7] == 3) {
                  Class168.aClass3_Sub28_Sub17_2096.method692(var33, var2 + Class32.anInt590, Class3_Sub1.anInt2208 + var0, var32, Class79.anInt1127, 150 - Class3_Sub13_Sub26.anIntArray3318[var7]);
               }

               if(4 == Class3_Sub13_Sub26.anIntArray3336[var7]) {
                  var15 = (-Class3_Sub13_Sub26.anIntArray3318[var7] + 150) * (Class168.aClass3_Sub28_Sub17_2096.method682(var33) - -100) / 150;
                  if(HDToolKit.highDetail) {
                     Class22.method931(Class32.anInt590 + var2 + -50, var0, Class32.anInt590 + var2 - -50, var4 + var0);
                  } else {
                     Class74.method1326(-50 + (var2 - -Class32.anInt590), var0, 50 + Class32.anInt590 + var2, var4 + var0);
                  }

                  Class168.aClass3_Sub28_Sub17_2096.method681(var33, var2 - (-Class32.anInt590 + -50) + -var15, var0 + Class3_Sub1.anInt2208, var32, 0);
                  if(HDToolKit.highDetail) {
                     Class22.method935(var2, var0, var1 + var2, var4 + var0);
                  } else {
                     Class74.method1324(var2, var0, var2 - -var1, var0 + var4);
                  }
               }

               if(Class3_Sub13_Sub26.anIntArray3336[var7] == 5) {
                  int var16 = 0;
                  var15 = -Class3_Sub13_Sub26.anIntArray3318[var7] + 150;
                  if(HDToolKit.highDetail) {
                     Class22.method931(var2, -1 + -Class168.aClass3_Sub28_Sub17_2096.anInt3727 + Class3_Sub1.anInt2208 + var0, var1 + var2, 5 + var0 - -Class3_Sub1.anInt2208);
                  } else {
                     Class74.method1326(var2, -1 + -Class168.aClass3_Sub28_Sub17_2096.anInt3727 + Class3_Sub1.anInt2208 + var0, var2 + var1, 5 + Class3_Sub1.anInt2208 + var0);
                  }

                  if(25 > var15) {
                     var16 = var15 + -25;
                  } else if(var15 > 125) {
                     var16 = var15 - 125;
                  }

                  Class168.aClass3_Sub28_Sub17_2096.method699(var33, Class32.anInt590 + var2, var16 + var0 + Class3_Sub1.anInt2208, var32, 0);
                  if(HDToolKit.highDetail) {
                     Class22.method935(var2, var0, var2 - -var1, var0 + var4);
                  } else {
                     Class74.method1324(var2, var0, var2 + var1, var0 + var4);
                  }
               }
            } else {
               Class168.aClass3_Sub28_Sub17_2096.method699(var33, var2 - -Class32.anInt590, var0 + Class3_Sub1.anInt2208, 16776960, 0);
            }
         }

      } catch (RuntimeException var17) {
         throw Class44.clientError(var17, "lc.D(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ')');
      }
   }

   static int method1406() {
      try {
         return Class3_Sub28_Sub7_Sub1.anInt4045;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "lc.E(" + (byte) -43 + ')');
      }
   }

   final byte[] method1407(int var1, int var2, boolean var3, Interface2 var4, CacheIndex var8) {
      try {
         byte[] var9 = new byte[4 * var2 * var1];
         GameObject.method1859(0.7);
         Class104.aClass153_2172 = var8;
         Class17.anInterface2_408 = var4;
         Class3_Sub13_Sub3.method180(-32, var1, var2);

         int var10;
         for(var10 = 0; this.aClass3_Sub13Array1147.length > var10; ++var10) {
            this.aClass3_Sub13Array1147[var10].method160(var1, var2);
         }

         var10 = 0;

         int var11;
         for(var11 = 0; var11 < var1; ++var11) {
            if(var3) {
               var10 = var11 << 2;
            }

            int[] var12;
            int[] var13;
            int[] var14;
            int[] var15;
            if(this.aClass3_Sub13_1145.aBoolean2375) {
               var15 = this.aClass3_Sub13_1145.method154(var11, (byte)-98);
               var12 = var15;
               var13 = var15;
               var14 = var15;
            } else {
               int[][] var22 = this.aClass3_Sub13_1145.method166(-1, var11);
               var12 = var22[0];
               var13 = var22[1];
               var14 = var22[2];
            }

            if(this.aClass3_Sub13_1148.aBoolean2375) {
               var15 = this.aClass3_Sub13_1148.method154(var11, (byte)-103);
            } else {
               var15 = this.aClass3_Sub13_1148.method166(-1, var11)[0];
            }

            for(int var16 = var2 - 1; var16 >= 0; --var16) {
               int var17 = var12[var16] >> 4;
               if(var17 > 255) {
                  var17 = 255;
               }

               if(var17 < 0) {
                  var17 = 0;
               }

               int var18 = var13[var16] >> 4;
               if(var18 > 255) {
                  var18 = 255;
               }

               int var19 = var14[var16] >> 4;
               if(var19 > 255) {
                  var19 = 255;
               }

               var17 = Class3_Sub30_Sub1.anIntArray3804[var17];
               if(var19 < 0) {
                  var19 = 0;
               }

               if(var18 < 0) {
                  var18 = 0;
               }

               var18 = Class3_Sub30_Sub1.anIntArray3804[var18];
               var19 = Class3_Sub30_Sub1.anIntArray3804[var19];
               int var20;
               if(var17 == 0 && var18 == 0 && var19 == 0) {
                  var20 = 0;
               } else {
                  var20 = var15[var16] >> 4;
                  if(255 < var20) {
                     var20 = 255;
                  }

                  if(var20 < 0) {
                     var20 = 0;
                  }
               }

               var9[var10++] = (byte)var17;
               var9[var10++] = (byte)var18;
               var9[var10++] = (byte)var19;
               var9[var10++] = (byte)var20;
               if(var3) {
                  var10 += -4 + (var2 << 2);
               }
            }
         }

         for(var11 = 0; this.aClass3_Sub13Array1147.length > var11; ++var11) {
            this.aClass3_Sub13Array1147[var11].method161((byte)-45);
         }

         return var9;
      } catch (RuntimeException var21) {
         throw Class44.clientError(var21, "lc.F(" + var1 + ',' + var2 + ',' + var3 + ',' + (var4 != null?"{...}":"null") + ',' + 0.7 + ',' + 8839 + ',' + (var8 != null?"{...}":"null") + ')');
      }
   }

   final boolean method1408(Interface2 var2, CacheIndex var3) {
      try {
         int var4;
         if(0 < Class126.anInt1668) {
            for(var4 = 0; this.anIntArray1144.length > var4; ++var4) {
               if(!var3.method2129((byte)-78, this.anIntArray1144[var4], Class126.anInt1668)) {
                  return false;
               }
            }
         } else {
            for(var4 = 0; this.anIntArray1144.length > var4; ++var4) {
               if(!var3.method2144(this.anIntArray1144[var4])) {
                  return false;
               }
            }
         }

         for(var4 = 0; var4 < this.anIntArray1149.length; ++var4) {
            if(!var2.method11(21, this.anIntArray1149[var4])) {
               return false;
            }
         }

         return true;
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "lc.B(" + true + ',' + (var2 != null?"{...}":"null") + ',' + (var3 != null?"{...}":"null") + ')');
      }
   }

   public static void method1409(boolean var0) {
      try {
         aClass93_1146 = null;
         if(var0) {
            aClass94_1151 = (RSString)null;
         }

         aClass94_1151 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "lc.A(" + var0 + ')');
      }
   }

   public Class82() {
      try {
         this.anIntArray1149 = new int[0];
         this.anIntArray1144 = new int[0];
         this.aClass3_Sub13_1145 = new Class3_Sub13_Sub22();
         this.aClass3_Sub13_1145.anInt2381 = 1;
         this.aClass3_Sub13_1148 = new Class3_Sub13_Sub22();
         this.aClass3_Sub13Array1147 = new Class3_Sub13[]{this.aClass3_Sub13_1145, this.aClass3_Sub13_1148};
         this.aClass3_Sub13_1148.anInt2381 = 1;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "lc.<init>()");
      }
   }

   Class82(RSByteBuffer var1) {
      try {
         int var2 = var1.getByteB();
         this.aClass3_Sub13Array1147 = new Class3_Sub13[var2];
         int[][] var5 = new int[var2][];
         int var4 = 0;
         int var3 = 0;

         int var6;
         Class3_Sub13 var7;
         int var8;
         int var9;
         for(var6 = 0; var2 > var6; ++var6) {
            var7 = InputStream_Sub1.method63(var1);
            if(0 <= var7.method159(4)) {
               ++var3;
            }

            if(var7.method155((byte) 19) >= 0) {
               ++var4;
            }

            var8 = var7.aClass3_Sub13Array2377.length;
            var5[var6] = new int[var8];

            for(var9 = 0; var9 < var8; ++var9) {
               var5[var6][var9] = var1.getByteB();
            }

            this.aClass3_Sub13Array1147[var6] = var7;
         }

         this.anIntArray1144 = new int[var3];
         this.anIntArray1149 = new int[var4];
         var3 = 0;
         var4 = 0;

         for(var6 = 0; var6 < var2; ++var6) {
            var7 = this.aClass3_Sub13Array1147[var6];
            var8 = var7.aClass3_Sub13Array2377.length;

            for(var9 = 0; var8 > var9; ++var9) {
               var7.aClass3_Sub13Array2377[var9] = this.aClass3_Sub13Array1147[var5[var6][var9]];
            }

            var9 = var7.method159(4);
            int var10 = var7.method155((byte)19);
            if(var9 > 0) {
               this.anIntArray1144[var3++] = var9;
            }

            if(var10 > 0) {
               this.anIntArray1149[var4++] = var10;
            }

            var5[var6] = null;
         }

         this.aClass3_Sub13_1145 = this.aClass3_Sub13Array1147[var1.getByteB()];
          this.aClass3_Sub13_1148 = this.aClass3_Sub13Array1147[var1.getByteB()];
      } catch (RuntimeException var11) {
         throw Class44.clientError(var11, "lc.<init>(" + (var1 != null?"{...}":"null") + ')');
      }
   }

}
