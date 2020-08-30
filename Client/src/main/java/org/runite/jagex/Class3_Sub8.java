package org.runite.jagex;

import org.runite.GameLaunch;

import java.util.Objects;

final class Class3_Sub8 extends Class3 {

   int anInt2296;
   Class64[] aClass64Array2298;
   int[] anIntArray2299;
   int[] anIntArray2300;
   int[] anIntArray2301;
   byte[][][] aByteArrayArrayArray2302;
   Class64[] aClass64Array2303;
   static RSString aClass94_2304 = RSString.createRSString("details");
   int anInt2305;
   static RSString aClass94_2306 = RSString.createRSString("<)4col> x");


   static void method124(int var0, int var1, int var2) {
      try {
         if(var0 <= 23) {
            aClass94_2306 = (RSString)null;
         }

         if(Canvas_Sub2.loadInterface(var2)) {
            Class2.method75(GameObject.aClass11ArrayArray1834[var2], var1);
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "ed.A(" + var0 + ',' + var1 + ',' + var2 + ')');
      }
   }

   static void method125(int var0, byte var1, int var2, int var3, RSInterface var4) {
      try {
         Class58.method1194();
         if(HDToolKit.highDetail) {
            Class22.method935(var3, var2, var3 + var4.anInt168, var2 + var4.anInt193);
         } else {
            Class74.method1324(var3, var2, var3 - -var4.anInt168, var2 + var4.anInt193);
         }

         if(2 != Class161.anInt2028 && 5 != Class161.anInt2028 && Class49.aClass3_Sub28_Sub16_812 != null) {
            int var19 = Class3_Sub13_Sub8.anInt3102 + GraphicDefinition.CAMERA_DIRECTION & 2047;
            int var6 = Class102.player.anInt2819 / 32 + 48;
            int var7 = -(Class102.player.anInt2829 / 32) + 464;
            if(HDToolKit.highDetail) {
               ((Class3_Sub28_Sub16_Sub1)Class49.aClass3_Sub28_Sub16_812).method647(var3, var2, var4.anInt168, var4.anInt193, var6, var7, var19, Class164_Sub2.anInt3020 + 256, (Class3_Sub28_Sub16_Sub1)var4.method866(false));
            } else {
               ((Class3_Sub28_Sub16_Sub2)Class49.aClass3_Sub28_Sub16_812).method664(var3, var2, var4.anInt168, var4.anInt193, var6, var7, var19, 256 - -Class164_Sub2.anInt3020, var4.anIntArray207, var4.anIntArray291);
            }

            int var9;
            int var10;
            int var11;
            int var12;
            int var13;
            int var14;
            int var17;
            int var16;
            if(null != Class3_Sub13_Sub35.aClass131_3421) {
               for(int var8 = 0; var8 < Class3_Sub13_Sub35.aClass131_3421.anInt1720; ++var8) {
                  if(Class3_Sub13_Sub35.aClass131_3421.method1789(var8, var1 ^ 553)) {
                     var9 = 2 + 4 * (Class3_Sub13_Sub35.aClass131_3421.aShortArray1727[var8] + -Class131.anInt1716) + -(Class102.player.anInt2819 / 32);
                     var11 = Class51.anIntArray840[var19];
                     var12 = Class51.anIntArray851[var19];
                     Class3_Sub28_Sub17 var15 = Class3_Sub13.aClass3_Sub28_Sub17_2379;
                     var11 = var11 * 256 / (256 + Class164_Sub2.anInt3020);
                     var10 = 2 + 4 * (-Class82.anInt1152 + Class3_Sub13_Sub35.aClass131_3421.aShortArray1718[var8]) - Class102.player.anInt2829 / 32;
                     var12 = var12 * 256 / (256 + Class164_Sub2.anInt3020);
                     var14 = -(var9 * var11) + var10 * var12 >> 16;
                     if(Class3_Sub13_Sub35.aClass131_3421.method1791(var8, var1 + -51) == 1) {
                        var15 = Class126.aClass3_Sub28_Sub17_1669;
                     }

                     if(2 == Class3_Sub13_Sub35.aClass131_3421.method1791(var8, 8)) {
                        var15 = Class168.aClass3_Sub28_Sub17_2096;
                     }

                     var13 = var11 * var10 - -(var12 * var9) >> 16;
                     var16 = var15.method680(Class3_Sub13_Sub35.aClass131_3421.aClass94Array1721[var8], 100);
                     var13 -= var16 / 2;
                     if(-var4.anInt168 <= var13 && var13 <= var4.anInt168 && var14 >= -var4.anInt193 && var14 <= var4.anInt193) {
                        var17 = 16777215;
                        if(Class3_Sub13_Sub35.aClass131_3421.anIntArray1725[var8] != -1) {
                           var17 = Class3_Sub13_Sub35.aClass131_3421.anIntArray1725[var8];
                        }

                        if(HDToolKit.highDetail) {
                           Class22.method936((Class3_Sub28_Sub16_Sub1) Objects.requireNonNull(var4.method866(false)));
                        } else {
                           Class74.method1314(var4.anIntArray207, var4.anIntArray291);
                        }

                        var15.method693(Class3_Sub13_Sub35.aClass131_3421.aClass94Array1721[var8], var3 + var13 + var4.anInt168 / 2, var2 + var4.anInt193 / 2 + -var14, var16, 50, var17, 0, 1, 0, 0);
                        if(HDToolKit.highDetail) {
                           Class22.method921();
                        } else {
                           Class74.method1310();
                        }
                     }
                  }
               }
            }

            for(var9 = 0; MouseListeningClass.anInt1924 > var9; ++var9) {
               var10 = -(Class102.player.anInt2819 / 32) + 2 + 4 * Class84.anIntArray1163[var9];
               var11 = -(Class102.player.anInt2829 / 32) + 2 + (Class3_Sub28_Sub7_Sub1.anIntArray4050[var9] * 4);
               ObjectDefinition var20 = Class162.getObjectDefinition(Class3_Sub19.anIntArray3693[var9]);
               if(null != var20.ChildrenIds) {
                  var20 = var20.method1685(var1 + -59);
                  if(null == var20 || var20.MapIcon == -1) {
                     continue;
                  }
               }

               Class38_Sub1.method1030(var4, Class140_Sub4.aClass3_Sub28_Sub16Array2839[var20.MapIcon], var11, var10, var2, var3);
            }

            for(var9 = 0; 104 > var9; ++var9) {
               for(var10 = 0; var10 < 104; ++var10) {
                  Class61 var25 = Class3_Sub13_Sub22.aClass61ArrayArrayArray3273[WorldListCountry.localPlane][var9][var10];
                  if(null != var25) {
                     var12 = 2 + var9 * 4 + -(Class102.player.anInt2819 / 32);
                     var13 = -(Class102.player.anInt2829 / 32) + 2 + 4 * var10;
                     Class38_Sub1.method1030(var4, Class80.aClass3_Sub28_Sub16Array1136[0], var13, var12, var2, var3);
                  }
               }
            }

            for(var9 = 0; var9 < Class163.localNPCCount; ++var9) {
               NPC var21 = Class3_Sub13_Sub24.npcs[Class15.localNPCIndexes[var9]];
               if(var21 != null && var21.hasDefinitions()) {
                  NPCDefinition var22 = var21.definition;
                  if(null != var22 && null != var22.childNPCs) {
                     var22 = var22.method1471((byte)-3);
                  }

                  if(var22 != null && var22.aBoolean1285 && var22.aBoolean1270) {
                     var12 = var21.anInt2819 / 32 - Class102.player.anInt2819 / 32;
                     var13 = var21.anInt2829 / 32 + -(Class102.player.anInt2829 / 32);
                     if(var22.anInt1283 == -1) {
                        Class38_Sub1.method1030(var4, Class80.aClass3_Sub28_Sub16Array1136[1], var13, var12, var2, var3);
                     } else {
                        Class38_Sub1.method1030(var4, Class140_Sub4.aClass3_Sub28_Sub16Array2839[var22.anInt1283], var13, var12, var2, var3);
                     }
                  }
               }
            }

            for(var9 = 0; var9 < Class159.localPlayerCount; ++var9) {
               Player var23 = Class3_Sub13_Sub22.players[Class56.localPlayerIndexes[var9]];
               if(null != var23 && var23.hasDefinitions()) {
                  var12 = var23.anInt2829 / 32 - Class102.player.anInt2829 / 32;
                  var11 = -(Class102.player.anInt2819 / 32) + var23.anInt2819 / 32;
                  long var29 = var23.displayName.toLong(-128);
                  boolean var28 = false;

                  for(var16 = 0; var16 < Class8.anInt104; ++var16) {
                     if(Class50.aLongArray826[var16] == var29 && 0 != Class55.anIntArray882[var16]) {
                        var28 = true;
                        break;
                     }
                  }

                  boolean var31 = false;

                  for(var17 = 0; Node.clanSize > var17; ++var17) {
                     if(var29 == PacketParser.aClass3_Sub19Array3694[var17].aLong71) {
                        var31 = true;
                        break;
                     }
                  }

                  boolean var32 = false;
                  if(Class102.player.teamId != 0 && 0 != var23.teamId && var23.teamId == Class102.player.teamId) {
                     var32 = true;
                  }

                  if(var28) {
                     Class38_Sub1.method1030(var4, Class80.aClass3_Sub28_Sub16Array1136[3], var12, var11, var2, var3);
                  } else if(var31) {
                     Class38_Sub1.method1030(var4, Class80.aClass3_Sub28_Sub16Array1136[5], var12, var11, var2, var3);
                  } else if (var32) {
                     Class38_Sub1.method1030(var4, Class80.aClass3_Sub28_Sub16Array1136[4], var12, var11, var2, var3);
                  } else {
                     Class38_Sub1.method1030(var4, Class80.aClass3_Sub28_Sub16Array1136[2], var12, var11, var2, var3);
                  }
               }
            }

            Class96[] var24 = ClientErrorException.aClass96Array2114;

            for(var10 = 0; var24.length > var10; ++var10) {
               Class96 var26 = var24[var10];
               if(null != var26 && var26.anInt1360 != 0 && Class44.anInt719 % 20 < 10) {
                  if(var26.anInt1360 == 1 && var26.anInt1359 >= 0 && var26.anInt1359 < Class3_Sub13_Sub24.npcs.length) {
                     NPC var27 = Class3_Sub13_Sub24.npcs[var26.anInt1359];
                     if(null != var27) {
                        var13 = -(Class102.player.anInt2819 / 32) + var27.anInt2819 / 32;
                        var14 = var27.anInt2829 / 32 + -(Class102.player.anInt2829 / 32);
                        Class53.method1171(var26.anInt1351, var2, var3, var13, var14, var4);
                     }
                  }

                  if(var26.anInt1360 == 2) {
                     var12 = (-Class131.anInt1716 + var26.anInt1356) * 4 + 2 - Class102.player.anInt2819 / 32;
                     var13 = -(Class102.player.anInt2829 / 32) + 2 + (-Class82.anInt1152 + var26.anInt1347) * 4;
                     Class53.method1171(var26.anInt1351, var2, var3, var12, var13, var4);
                  }

                  if(var26.anInt1360 == 10 && var26.anInt1359 >= 0 && Class3_Sub13_Sub22.players.length > var26.anInt1359) {
                     Player var30 = Class3_Sub13_Sub22.players[var26.anInt1359];
                     if(null != var30) {
                        var14 = var30.anInt2829 / 32 + -(Class102.player.anInt2829 / 32);
                        var13 = var30.anInt2819 / 32 + -(Class102.player.anInt2819 / 32);
                        Class53.method1171(var26.anInt1351, var2, var3, var13, var14, var4);
                     }
                  }
               }
            }

            if(Class65.anInt987 != 0) {
               var9 = 4 * Class65.anInt987 + (2 - Class102.player.anInt2819 / 32);
               var10 = 2 + 4 * Class45.anInt733 - Class102.player.anInt2829 / 32;
               Class38_Sub1.method1030(var4, Class45.aClass3_Sub28_Sub16_736, var10, var9, var2, var3);
            }

            if(HDToolKit.highDetail) {
               Class22.method934(-1 + (var3 - -(var4.anInt168 / 2)), -1 + var2 - -(var4.anInt193 / 2), 3, 3, 16777215);
            } else {
               Class74.method1323(-1 + var4.anInt168 / 2 + var3, -1 + var4.anInt193 / 2 + var2, 3, 3, 16777215);
            }
         } else if(HDToolKit.highDetail) {
            Class3_Sub28_Sub16 var5 = var4.method866(false);
            if(null != var5) {
               var5.method643(var3, var2);
            }
         } else {
            Class74.method1332(var3, var2, var4.anIntArray207, var4.anIntArray291);
         }

         if(var1 == 59) {
            Class163_Sub1_Sub1.aBooleanArray4008[var0] = true;
         }
      } catch (RuntimeException var18) {
         throw Class44.clientError(var18, "ed.B(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + (var4 != null?"{...}":"null") + ')');
      }
   }

   static void method126(boolean var0, int var1, int var2, int var3, int var4) {
      try {
         if(!var0) {
            if(Class101.anInt1425 <= var4 && var4 <= Class3_Sub28_Sub18.anInt3765) {
               var2 = Class40.method1040(Class57.anInt902, var2, Class159.anInt2020);
               var1 = Class40.method1040(Class57.anInt902, var1, Class159.anInt2020);
               Class3_Sub13_Sub16.method244(var2, var4, var1, var3);
            }

         }
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "ed.F(" + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
      }
   }

   static void method127(short[] var0, int var1, RSString[] var2, int var4) {
      try {
         if(var4 < var1) {
            int var6 = var4;
            int var5 = (var4 - -var1) / 2;
            RSString var7 = var2[var5];
            var2[var5] = var2[var1];
            var2[var1] = var7;
            short var8 = var0[var5];
            var0[var5] = var0[var1];
            var0[var1] = var8;

            for(int var9 = var4; var9 < var1; ++var9) {
               if(var7 == null || null != var2[var9] && var2[var9].method1559(var7) < (var9 & 1)) {
                  RSString var10 = var2[var9];
                  var2[var9] = var2[var6];
                  var2[var6] = var10;
                  short var11 = var0[var9];
                  var0[var9] = var0[var6];
                  var0[var6++] = var11;
               }
            }

            var2[var1] = var2[var6];
            var2[var6] = var7;
            var0[var1] = var0[var6];
            var0[var6] = var8;
            method127(var0, -1 + var6, var2, var4);
            method127(var0, var1, var2, var6 - -1);
         }

      } catch (RuntimeException var12) {
         throw Class44.clientError(var12, "ed.E(" + (var0 != null?"{...}":"null") + ',' + var1 + ',' + (var2 != null?"{...}":"null") + ',' + -909 + ',' + var4 + ')');
      }
   }

   static void method128() {
      try {
         Class44.aClass93_725.method1524();

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ed.O(" + 2 + ')');
      }
   }

   static int method129(int var0, int var2, int var3) {
      try {

         if(var0 <= 243) {
            if(var0 > 217) {
               var2 >>= 3;
            } else if(var0 <= 192) {
               if(179 < var0) {
                  var2 >>= 1;
               }
            } else {
               var2 >>= 2;
            }
         } else {
            var2 >>= 4;
         }

         return (var0 >> 1) + (var2 >> 5 << 7) + (var3 >> 2 << 10);
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "ed.D(" + var0 + ',' + 2 + ',' + var2 + ',' + var3 + ')');
      }
   }

   static WorldListEntry getWorld(int var0, int index) {
      try {
         if(Class30.loadedWorldList && Class3_Sub13_Sub4.worldListOffset <= index && Class100.worldListArraySize >= index) {
            GameLaunch.SETTINGS.setWorld(index);
            return Class117.worldList[index - Class3_Sub13_Sub4.worldListOffset];
         } else {
            return null;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ed.P(" + var0 + ',' + index + ')');
      }
   }

   public static void method131(int var0) {
      try {
         aClass94_2304 = null;
         aClass94_2306 = null;
         if(var0 >= -104) {
            method132((byte)-28);
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ed.Q(" + var0 + ')');
      }
   }

   static void method132(byte var0) {
      try {
         for(int var1 = 0; var1 < Class113.anInt1552; ++var1) {
            --RSString.anIntArray2157[var1];
            if(RSString.anIntArray2157[var1] < -10) {
               --Class113.anInt1552;

               for(int var2 = var1; var2 < Class113.anInt1552; ++var2) {
                  Class3_Sub25.anIntArray2550[var2] = Class3_Sub25.anIntArray2550[var2 - -1];
                  Class102.aClass135Array2131[var2] = Class102.aClass135Array2131[var2 + 1];
                  Class166.anIntArray2068[var2] = Class166.anIntArray2068[1 + var2];
                  RSString.anIntArray2157[var2] = RSString.anIntArray2157[1 + var2];
                  Class3_Sub13_Sub6.anIntArray3083[var2] = Class3_Sub13_Sub6.anIntArray3083[var2 - -1];
               }

               --var1;
            } else {
               Class135 var11 = Class102.aClass135Array2131[var1];
               if(null == var11) {
                  var11 = Class135.method1811(Class146.aClass153_1902, Class3_Sub25.anIntArray2550[var1], 0);
                  if(null == var11) {
                     continue;
                  }

                  RSString.anIntArray2157[var1] += var11.method1813();
                  Class102.aClass135Array2131[var1] = var11;
               }

               if(0 > RSString.anIntArray2157[var1]) {
                  int var3;
                  if(Class3_Sub13_Sub6.anIntArray3083[var1] == 0) {
                     var3 = CS2Script.anInt2453;
                  } else {
                     int var4 = 128 * (255 & Class3_Sub13_Sub6.anIntArray3083[var1]);
                     int var7 = Class3_Sub13_Sub6.anIntArray3083[var1] >> 8 & 255;
                     int var5 = 255 & Class3_Sub13_Sub6.anIntArray3083[var1] >> 16;
                     int var8 = -Class102.player.anInt2829 + 64 + 128 * var7;
                     if(var8 < 0) {
                        var8 = -var8;
                     }

                     int var6 = -Class102.player.anInt2819 + 64 + var5 * 128;
                     if(0 > var6) {
                        var6 = -var6;
                     }

                     int var9 = -128 + var6 + var8;
                     if(var9 > var4) {
                        RSString.anIntArray2157[var1] = -100;
                        continue;
                     }

                     if(var9 < 0) {
                        var9 = 0;
                     }

                     var3 = Class14.anInt340 * (var4 + -var9) / var4;
                  }

                  if(var3 > 0) {
                     Class3_Sub12_Sub1 var12 = var11.method1812().method151(Class27.aClass157_524);
                     Class3_Sub24_Sub1 var13 = Class3_Sub24_Sub1.method437(var12, var3);
                     Objects.requireNonNull(var13).method429(Class166.anIntArray2068[var1] + -1);
                     Class3_Sub26.aClass3_Sub24_Sub2_2563.method457(var13);
                  }

                  RSString.anIntArray2157[var1] = -100;
               }
            }
         }

         if(var0 != -92) {
            method126(true, 36, 42, 14, 51);
         }

         if(Class83.aBoolean1158 && Class79.method1391(-1)) {
            if(0 != Class9.anInt120 && Class129.anInt1691 != -1) {
               Class70.method1285(Class75_Sub2.aClass153_2645, Class129.anInt1691, Class9.anInt120);
            }

            Class83.aBoolean1158 = false;
         } else if(Class9.anInt120 != 0 && Class129.anInt1691 != -1 && Class79.method1391(var0 + 91)) {
            Class3_Sub13_Sub1.outgoingBuffer.putOpcode(137);
            Class3_Sub13_Sub1.outgoingBuffer.putInt(-127, Class129.anInt1691);
            Class129.anInt1691 = -1;
         }

      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "ed.C(" + var0 + ')');
      }
   }

}
