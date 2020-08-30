package org.runite.jagex;

import java.util.Objects;

final class Class47 {

   static boolean aBoolean742 = false;
   static Class93 aClass93_743 = new Class93(20);
   private Node aClass3_Sub28_744 = new Node();
   private final Class130 aClass130_745;
   private final int anInt746;
   private final NodeList aClass13_747 = new NodeList();
   static CacheIndex quickChatMessages;
   private int anInt749;
   static RSString aClass94_750 = RSString.createRSString("null");


   static boolean method1088(boolean var0) {
      try {
         if(Class3_Sub28_Sub11.aBoolean3641) {
            try {
               Class8.aClass94_106.method1577(Class38.aClass87_665.anApplet1219);
               return true;
            } catch (Throwable var2) {
            }
         }

         if(var0) {
            aClass93_743 = (Class93)null;
         }

         return false;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "gn.K(" + var0 + ')');
      }
   }

   static Class3_Sub28_Sub5 method1089(CacheIndex cacheIndex0, CacheIndex var2, int frameId) {
      try {
         boolean var5 = true;
         int[] var6 = cacheIndex0.getFileIds(frameId);

         for(int var7 = 0; var7 < Objects.requireNonNull(var6).length; ++var7) {
            byte[] var8 = cacheIndex0.method2140(var6[var7], frameId);
            if(var8 == null) {
               var5 = false;
            } else {
               int var9 = (255 & var8[0]) << 8 | var8[1] & 255;
               byte[] var10;
               var10 = var2.method2140(0, var9);

               if(null == var10) {
                  // System.out.println("Roar , " + var9);
                  var5 = false;
               }
            }
         }
         if(var5) {
            try {
               return new Class3_Sub28_Sub5(cacheIndex0, var2, frameId);
            } catch (Exception var11) {
               var11.printStackTrace();
               return null;
            }
         } else {
            return null;
         }
      } catch (RuntimeException var12) {
         throw Class44.clientError(var12, "gn.J(" + (cacheIndex0 != null?"{...}":"null") + ',' + false + ',' + (var2 != null?"{...}":"null") + ',' + (byte) 118 + ',' + frameId + ')');
      }
   }

   static RSString method1090(byte var0, int var1) {
      try {
         if(var0 > -86) {
            return (RSString)null;
         } else {
            RSString var2 = new RSString();
            var2.length = 0;
            var2.byteArray = new byte[var1];
            return var2;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "gn.M(" + var0 + ',' + var1 + ')');
      }
   }

   static void method1091(boolean var0, int var1) {
      try {
         byte var2;
         byte[][] var3;
         if(HDToolKit.highDetail && var0) {
            var2 = 1;
            var3 = Class3_Sub28_Sub14.aByteArrayArray3669;
         } else {
            var2 = 4;
            var3 = Class164_Sub2.aByteArrayArray3027;
         }

         int var4 = var3.length;

         int var5;
         int var6;
         int var7;
         byte[] var8;
         for(var5 = 0; var5 < var4; ++var5) {
            var6 = -Class131.anInt1716 + 64 * (Class3_Sub24_Sub3.anIntArray3494[var5] >> 8);
            var7 = -Class82.anInt1152 + 64 * (255 & Class3_Sub24_Sub3.anIntArray3494[var5]);
            var8 = var3[var5];
            if(null != var8) {
               Class58.method1194();
               RSByteBuffer.method777(Class86.aClass91Array1182, var0, -48 + 8 * Class3_Sub28_Sub7.anInt3606, var7, var6, (Class3_Sub7.anInt2294 + -6) * 8, var8);
            }
         }

         var5 = 0;
         if(var1 > -66) {
            method1088(true);
         }

         for(; var5 < var4; ++var5) {
            var6 = -Class131.anInt1716 + 64 * (Class3_Sub24_Sub3.anIntArray3494[var5] >> 8);
            var7 = -Class82.anInt1152 + 64 * (255 & Class3_Sub24_Sub3.anIntArray3494[var5]);
            var8 = var3[var5];
            if(var8 == null && Class3_Sub7.anInt2294 < 800) {
               Class58.method1194();

               for(int var9 = 0; var9 < var2; ++var9) {
                  Class12.method870(var9, (byte)102, var7, var6, 64, 64);
               }
            }
         }

      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "gn.I(" + var0 + ',' + var1 + ')');
      }
   }

   final Node method1092(long var1) {
      try {
         Node var4 = (Node)this.aClass130_745.method1780(var1, 0);
         if(null != var4) {
            this.aClass13_747.method879(var4, (byte)44);
         }

         return var4;
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "gn.N(" + var1 + ')');
      }
   }

   static void method1093(boolean var0) {
      try {
         if(var0) {
            method1093(true);
         }

         for(int var1 = 0; var1 < 100; ++var1) {
            Class3_Sub28_Sub14.aBooleanArray3674[var1] = true;
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "gn.H(" + var0 + ')');
      }
   }

   final Class3 method1094() {
      try {
         return this.aClass130_745.method1776(38);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "gn.A(" + 0 + ')');
      }
   }

   static void method1095(int var0, int var1, int var2, RSInterface[] var3, int var4, int var5, int var6, int var7, byte var8, int var9) {
      try {
         if(HDToolKit.highDetail) {
            Class22.method935(var0, var6, var4, var7);
         } else {
            Class74.method1324(var0, var6, var4, var7);
            Class51.method1134();
         }

         for (RSInterface var11 : var3) {
            if (var11 != null && (var5 == var11.parentId || var5 == -1412584499 && var11 == Class56.aClass11_886)) {
               int var12;
               if (var9 == -1) {
                  Class155.anIntArray1969[Class3_Sub28_Sub3.anInt3557] = var2 + var11.anInt306;
                  Player.anIntArray3954[Class3_Sub28_Sub3.anInt3557] = var11.anInt210 - -var1;
                  Class3_Sub28_Sub18.anIntArray3768[Class3_Sub28_Sub3.anInt3557] = var11.anInt168;
                  Class140_Sub4.anIntArray2794[Class3_Sub28_Sub3.anInt3557] = var11.anInt193;
                  var12 = Class3_Sub28_Sub3.anInt3557++;
               } else {
                  var12 = var9;
               }

               var11.anInt204 = Class44.anInt719;
               var11.anInt292 = var12;
               if (!var11.usingScripts || !Client.method51(var11)) {
                  if (0 < var11.anInt189) {
                     Class2.method81(var11);
                  }

                  int var14 = var1 + var11.anInt210;
                  int var15 = var11.anInt223;
                  int var13 = var11.anInt306 - -var2;
                  if (Class69.aBoolean1040 && (0 != Client.method44(var11).anInt2205 || var11.type == 0) && var15 > 127) {
                     var15 = 127;
                  }

                  int var17;
                  int var16;
                  if (var11 == Class56.aClass11_886) {
                     if (var5 != -1412584499 && !var11.aBoolean200) {
                        Class3_Sub28_Sub7.anInt3602 = var2;
                        Class73.anInt1082 = var1;
                        GameObject.aClass11Array1836 = var3;
                        continue;
                     }

                     if (NPC.aBoolean3975 && Class85.aBoolean1167) {
                        var17 = Class130.anInt1709;
                        var16 = Class126.anInt1676;
                        var17 -= Class95.anInt1336;
                        if (var17 < Class134.anInt1761) {
                           var17 = Class134.anInt1761;
                        }

                        if (PacketParser.aClass11_88.anInt193 + Class134.anInt1761 < var17 + var11.anInt193) {
                           var17 = -var11.anInt193 + PacketParser.aClass11_88.anInt193 + Class134.anInt1761;
                        }

                        var14 = var17;
                        var16 -= Class144.anInt1881;
                        if (Class3_Sub13_Sub13.anInt3156 > var16) {
                           var16 = Class3_Sub13_Sub13.anInt3156;
                        }

                        if (var11.anInt168 + var16 > PacketParser.aClass11_88.anInt168 + Class3_Sub13_Sub13.anInt3156) {
                           var16 = -var11.anInt168 + PacketParser.aClass11_88.anInt168 + Class3_Sub13_Sub13.anInt3156;
                        }

                        var13 = var16;
                     }

                     if (!var11.aBoolean200) {
                        var15 = 128;
                     }
                  }

                  int var19;
                  int var18;
                  int var21;
                  int var20;
                  if (2 == var11.type) {
                     var19 = var7;
                     var18 = var4;
                     var17 = var6;
                     var16 = var0;
                  } else {
                     var17 = var6 < var14 ? var14 : var6;
                     var16 = var13 > var0 ? var13 : var0;
                     var20 = var11.anInt168 + var13;
                     var21 = var14 - -var11.anInt193;
                     if (var11.type == 9) {
                        ++var21;
                        ++var20;
                     }

                     var19 = var7 <= var21 ? var7 : var21;
                     var18 = var4 <= var20 ? var4 : var20;
                  }

                  if (!var11.usingScripts || var16 < var18 && var19 > var17) {
                     int var23;
                     int var22;
                     int var25;
                     int var24;
                     int var26;
                     int var29;
                     int var28;
                     int var47;
                     if (var11.anInt189 != 0) {
                        if (var11.anInt189 == 1337 || var11.anInt189 == 1403 && HDToolKit.highDetail) {
                           Class168.aClass11_2091 = var11;
                           Node.anInt2567 = var14;
                           Class53.anInt865 = var13;
                           Class3_Sub13_Sub36.method338(var11.anInt193, var11.anInt189 == 1403, var13, var11.anInt168, var14);
                           if (HDToolKit.highDetail) {
                              Class22.method935(var0, var6, var4, var7);
                           } else {
                              Class74.method1324(var0, var6, var4, var7);
                           }
                           continue;
                        }

                        if (var11.anInt189 == 1338) {
                           if (!var11.method855()) {
                              continue;
                           }

                           Class3_Sub8.method125(var12, (byte) 59, var14, var13, var11);
                           if (HDToolKit.highDetail) {
                              Class22.method935(var0, var6, var4, var7);
                           } else {
                              Class74.method1324(var0, var6, var4, var7);
                           }

                           if (0 != Class161.anInt2028 && 3 != Class161.anInt2028 || Class38_Sub1.aBoolean2615 || var16 > NPCDefinition.anInt1297 || var17 > Class38_Sub1.anInt2612 || var18 <= NPCDefinition.anInt1297 || Class38_Sub1.anInt2612 >= var19) {
                              continue;
                           }

                           var20 = NPCDefinition.anInt1297 - var13;
                           var21 = -var14 + Class38_Sub1.anInt2612;
                           var22 = var11.anIntArray207[var21];
                           if (var22 > var20 || var22 + var11.anIntArray291[var21] < var20) {
                              continue;
                           }

                           var21 -= var11.anInt193 / 2;
                           var23 = 2047 & GraphicDefinition.CAMERA_DIRECTION - -Class3_Sub13_Sub8.anInt3102;
                           var20 -= var11.anInt168 / 2;
                           var24 = Class51.anIntArray840[var23];
                           var25 = Class51.anIntArray851[var23];
                           var24 = (Class164_Sub2.anInt3020 + 256) * var24 >> 8;
                           var25 = (Class164_Sub2.anInt3020 - -256) * var25 >> 8;
                           var47 = -(var24 * var20) + var25 * var21 >> 11;
                           var26 = var21 * var24 - -(var20 * var25) >> 11;
                           var28 = Class102.player.anInt2819 + var26 >> 7;
                           var29 = -var47 + Class102.player.anInt2829 >> 7;
                           if (GameObject.aBoolean1837 && 0 != (Class164.anInt2051 & 64)) {
                              RSInterface var53 = Class3_Sub28_Sub16.method638(Class3_Sub30_Sub1.anInt872, RSInterface.anInt278);
                              if (null == var53) {
                                 Class25.method958((byte) -91);
                              } else {
                                 Class3_Sub24_Sub4.method1177(Class144.anInt1887, 1L, (byte) -49, Class131.aClass94_1724, var28, (short) 11, Class3_Sub28_Sub9.aClass94_3621, var29);
                              }
                              continue;
                           }

                           if (Class158.anInt2014 == 1) {
                              Class3_Sub24_Sub4.method1177(-1, 1L, (byte) -41, Class3_Sub28_Sub14.aClass94_3672, var28, (short) 36, TextCore.HasFaceHere, var29);
                           }

                           Class3_Sub24_Sub4.method1177(-1, 1L, (byte) -125, Class3_Sub28_Sub14.aClass94_3672, var28, (short) 60, Class3_Sub13_Sub28.aClass94_3353, var29);
                           continue;
                        }

                        if (var11.anInt189 == 1339) {
                           if (var11.method855()) {
                              Class91.method1493(var13, var14, var11, var12);
                              if (HDToolKit.highDetail) {
                                 Class22.method935(var0, var6, var4, var7);
                              } else {
                                 Class74.method1324(var0, var6, var4, var7);
                              }
                           }
                           continue;
                        }

                        if (var11.anInt189 == 1400) {
                           RSByteBuffer.method799(var13, var14, var11.anInt193, var11.anInt168);
                           Class3_Sub28_Sub14.aBooleanArray3674[var12] = true;
                           Class163_Sub1_Sub1.aBooleanArray4008[var12] = true;
                           if (HDToolKit.highDetail) {
                              Class22.method935(var0, var6, var4, var7);
                           } else {
                              Class74.method1324(var0, var6, var4, var7);
                           }
                           continue;
                        }

                        if (var11.anInt189 == 1401) {
                           Class1.method72(var13, var11.anInt193, var11.anInt168, var14);
                           Class3_Sub28_Sub14.aBooleanArray3674[var12] = true;
                           Class163_Sub1_Sub1.aBooleanArray4008[var12] = true;
                           if (HDToolKit.highDetail) {
                              Class22.method935(var0, var6, var4, var7);
                           } else {
                              Class74.method1324(var0, var6, var4, var7);
                           }
                           continue;
                        }

                        if (1402 == var11.anInt189) {
                           if (!HDToolKit.highDetail) {
                              Class129.method1768(var13, var14);
                              Class3_Sub28_Sub14.aBooleanArray3674[var12] = true;
                              Class163_Sub1_Sub1.aBooleanArray4008[var12] = true;
                           }
                           continue;
                        }

                        if (var11.anInt189 == 1405) {
                           if (!Class20.aBoolean438) {
                              continue;
                           }

                           var20 = var11.anInt168 + var13;
                           var21 = 15 + var14;
                           Class126.aClass3_Sub28_Sub17_1669.method688(RenderAnimationDefinition.method903(new RSString[]{Class3_Sub13_Sub16.aClass94_3196, Class72.method1298((byte) 9, AnimationDefinition.anInt1862)}, (byte) -69), var20, var21, 16776960, -1);
                           var21 += 15;
                           Runtime var57 = Runtime.getRuntime();
                           var23 = (int) ((var57.totalMemory() + -var57.freeMemory()) / 1024L);
                           var24 = 16776960;
                           if (var23 > 65536) {
                              var24 = 16711680;
                           }

                           Class126.aClass3_Sub28_Sub17_1669.method688(RenderAnimationDefinition.method903(new RSString[]{Class3_Sub28_Sub10_Sub1.aClass94_4057, Class72.method1298((byte) 9, var23), TextCore.Memoryk}, (byte) -128), var20, var21, var24, -1);
                           var21 += 15;
                           if (HDToolKit.highDetail) {
                              var24 = 16776960;
                              var25 = (Class31.anInt580 + Class31.anInt585 + Class31.memory2D) / 1024;
                              if (65536 < var25) {
                                 var24 = 16711680;
                              }

                              Class126.aClass3_Sub28_Sub17_1669.method688(RenderAnimationDefinition.method903(new RSString[]{Class118.aClass94_1622, Class72.method1298((byte) 9, var25), TextCore.Memoryk}, (byte) -97), var20, var21, var24, -1);
                              var21 += 15;
                           }

                           var24 = 16776960;
                           var25 = 0;
                           var47 = 0;
                           var26 = 0;

                           for (var28 = 0; var28 < 29; ++var28) { //TODO:
                              var25 += RSByteBuffer.aClass151_Sub1Array2601[var28].method2108();
                              var26 += RSByteBuffer.aClass151_Sub1Array2601[var28].method2102();
                              var47 += RSByteBuffer.aClass151_Sub1Array2601[var28].method2106();
                           }

                           var29 = 10000 * var26 / var25;
                           var28 = var47 * 100 / var25;
                           RSString var55 = RenderAnimationDefinition.method903(new RSString[]{Class20.aClass94_436, Class3_Sub23.method407(0, true, 2, (long) var29), Class3_Sub21.aClass94_2498, Class72.method1298((byte) 9, var28), Class10.aClass94_148}, (byte) -113);
                           Class3_Sub13.aClass3_Sub28_Sub17_2379.method688(var55, var20, var21, var24, -1);
                           Class3_Sub28_Sub14.aBooleanArray3674[var12] = true;
                           Class163_Sub1_Sub1.aBooleanArray4008[var12] = true;
                           continue;
                        }

                        if (var11.anInt189 == 1406) {
                           ClientErrorException.anInt2115 = var14;
                           Class3_Sub28_Sub16.aClass11_3708 = var11;
                           Class3_Sub13_Sub23_Sub1.anInt4041 = var13;
                           continue;
                        }
                     }

                     if (!Class38_Sub1.aBoolean2615) {
                        if (var11.type == 0 && var11.aBoolean219 && NPCDefinition.anInt1297 >= var16 && var17 <= Class38_Sub1.anInt2612 && NPCDefinition.anInt1297 < var18 && Class38_Sub1.anInt2612 < var19 && !Class69.aBoolean1040) {
                           Class3_Sub13_Sub34.anInt3415 = 1;
                           Class114.anIntArray1578[0] = Class3_Sub28_Sub5.anInt3590;
                           Class140_Sub7.aClass94Array2935[0] = TextCore.HasCancel;
                           Class163_Sub2_Sub1.aClass94Array4016[0] = Class3_Sub28_Sub14.aClass94_3672;
                           Class3_Sub13_Sub7.aShortArray3095[0] = 1005;
                        }

                        if (var16 <= NPCDefinition.anInt1297 && Class38_Sub1.anInt2612 >= var17 && var18 > NPCDefinition.anInt1297 && Class38_Sub1.anInt2612 < var19) {
                           Class3_Sub24_Sub4.method477(Class38_Sub1.anInt2612 + -var14, -var13 + NPCDefinition.anInt1297, var11);
                        }
                     }

                     if (var11.type == 0) {
                        if (!var11.usingScripts && Client.method51(var11) && Class107.aClass11_1453 != var11) {
                           continue;
                        }

                        if (!var11.usingScripts) {
                           if (var11.anInt208 > -var11.anInt193 + var11.anInt252) {
                              var11.anInt208 = -var11.anInt193 + var11.anInt252;
                           }

                           if (0 > var11.anInt208) {
                              var11.anInt208 = 0;
                           }
                        }

                        method1095(var16, -var11.anInt208 + var14, -var11.anInt247 + var13, var3, var18, var11.anInt279, var17, var19, (byte) 87, var12);
                        if (null != var11.aClass11Array262) {
                           method1095(var16, -var11.anInt208 + var14, -var11.anInt247 + var13, var11.aClass11Array262, var18, var11.anInt279, var17, var19, (byte) 52, var12);
                        }

                        Class3_Sub31 var36 = (Class3_Sub31) Class3_Sub13_Sub17.aClass130_3208.method1780((long) var11.anInt279, 0);
                        if (var36 != null) {
                           if (var36.anInt2603 == 0 && !Class38_Sub1.aBoolean2615 && NPCDefinition.anInt1297 >= var16 && Class38_Sub1.anInt2612 >= var17 && NPCDefinition.anInt1297 < var18 && Class38_Sub1.anInt2612 < var19 && !Class69.aBoolean1040) {
                              Class140_Sub7.aClass94Array2935[0] = TextCore.HasCancel;
                              Class3_Sub13_Sub34.anInt3415 = 1;
                              Class114.anIntArray1578[0] = Class3_Sub28_Sub5.anInt3590;
                              Class3_Sub13_Sub7.aShortArray3095[0] = 1005;
                              Class163_Sub2_Sub1.aClass94Array4016[0] = Class3_Sub28_Sub14.aClass94_3672;
                           }

                           Class3_Sub13_Sub1.method171(-101, var36.anInt2602, var16, var18, var13, var12, var19, var17, var14);
                        }

                        if (HDToolKit.highDetail) {
                           Class22.method935(var0, var6, var4, var7);
                        } else {
                           Class74.method1324(var0, var6, var4, var7);
                           Class51.method1134();
                        }
                     }

                     if (Class130.aBooleanArray1712[var12] || Client.anInt3689 > 1) {
                        if (var11.type == 0 && !var11.usingScripts && var11.anInt252 > var11.anInt193) {
                           Class3_Sub13_Sub12.method224(var11.anInt208, var11.anInt252, var11.anInt168 + var13, var14, var11.anInt193);
                        }

                        if (var11.type != 1) {
                           boolean var39;
                           boolean var46;
                           if (var11.type == 2) {
                              var20 = 0;

                              for (var21 = 0; var11.height > var21; ++var21) {
                                 for (var22 = 0; var11.width > var22; ++var22) {
                                    var24 = var14 + var21 * (32 - -var11.anInt290);
                                    var23 = (var11.anInt285 + 32) * var22 + var13;
                                    if (var20 < 20) {
                                       var24 += var11.anIntArray300[var20];
                                       var23 += var11.anIntArray272[var20];
                                    }

                                    if (var11.itemAmounts[var20] <= 0) {
                                       if (null != var11.anIntArray197 && var20 < 20) {
                                          Class3_Sub28_Sub16 var58 = var11.method859(var20);
                                          if (null == var58) {
                                             if (GameShell.aBoolean6) {
                                                Class20.method909(125, var11);
                                             }
                                          } else {
                                             var58.method643(var23, var24);
                                          }
                                       }
                                    } else {
                                       var47 = var11.itemAmounts[var20] + -1;
                                       if (var0 < 32 + var23 && var4 > var23 && var24 - -32 > var6 && var7 > var24 || var11 == Class67.aClass11_1017 && var20 == PacketParser.anInt86) {
                                          Class3_Sub28_Sub16 var54;
                                          if (Class164_Sub1.anInt3012 == 1 && Class110.anInt1473 == var20 && Class3_Sub28_Sub18.anInt3764 == var11.anInt279) {
                                             var54 = Class114.method1707(2, var47, var11.aBoolean227, var11.itemIds[var20], 0);
                                          } else {
                                             var54 = Class114.method1707(1, var47, var11.aBoolean227, var11.itemIds[var20], 3153952);
                                          }

                                          if (Class51.aBoolean837) {
                                             Class3_Sub28_Sub14.aBooleanArray3674[var12] = true;
                                          }

                                          if (null == var54) {
                                             Class20.method909(-106, var11);
                                          } else if (Class67.aClass11_1017 == var11 && var20 == PacketParser.anInt86) {
                                             var25 = Class126.anInt1676 - Class129_Sub1.anInt2693;
                                             var26 = -InputStream_Sub1.anInt40 + Class130.anInt1709;
                                             if (var26 < 5 && var26 > -5) {
                                                var26 = 0;
                                             }

                                             if (var25 < 5 && var25 > -5) {
                                                var25 = 0;
                                             }

                                             if (5 > Class40.anInt677) {
                                                var25 = 0;
                                                var26 = 0;
                                             }

                                             var54.method637(var23 + var25, var24 - -var26, 128);
                                             if (var5 != -1) {
                                                RSInterface var51 = var3[var5 & '\uffff'];
                                                int var31;
                                                int var30;
                                                if (HDToolKit.highDetail) {
                                                   var31 = Class22.anInt451;
                                                   var30 = Class22.anInt448;
                                                } else {
                                                   var30 = Class74.anInt1095;
                                                   var31 = Class74.anInt1099;
                                                }

                                                int var32;
                                                if (var26 + var24 < var30 && var51.anInt208 > 0) {
                                                   var32 = Class106.anInt1446 * (-var26 + var30 + -var24) / 3;
                                                   if (Class106.anInt1446 * 10 < var32) {
                                                      var32 = 10 * Class106.anInt1446;
                                                   }

                                                   if (var32 > var51.anInt208) {
                                                      var32 = var51.anInt208;
                                                   }

                                                   var51.anInt208 -= var32;
                                                   InputStream_Sub1.anInt40 += var32;
                                                   Class20.method909(121, var51);
                                                }

                                                if (var31 < 32 + var26 + var24 && var51.anInt208 < -var51.anInt193 + var51.anInt252) {
                                                   var32 = (-var31 + 32 + (var24 - -var26)) * Class106.anInt1446 / 3;
                                                   if (var32 > Class106.anInt1446 * 10) {
                                                      var32 = 10 * Class106.anInt1446;
                                                   }

                                                   if (-var51.anInt208 + var51.anInt252 + -var51.anInt193 < var32) {
                                                      var32 = var51.anInt252 + -var51.anInt193 + -var51.anInt208;
                                                   }

                                                   var51.anInt208 += var32;
                                                   InputStream_Sub1.anInt40 -= var32;
                                                   Class20.method909(-81, var51);
                                                }
                                             }
                                          } else if (var11 == Class151.aClass11_1933 && var20 == KeyboardListener.anInt1918) {
                                             var54.method637(var23, var24, 128);
                                          } else {
                                             var54.method643(var23, var24);
                                          }
                                       }
                                    }

                                    ++var20;
                                 }
                              }
                           } else if (3 == var11.type) {
                              if (Class3_Sub28_Sub12.method609(var11, 26)) {
                                 var20 = var11.anInt253;
                                 if (Class107.aClass11_1453 == var11 && 0 != var11.anInt222) {
                                    var20 = var11.anInt222;
                                 }
                              } else {
                                 var20 = var11.anInt218;
                                 if (var11 == Class107.aClass11_1453 && 0 != var11.anInt228) {
                                    var20 = var11.anInt228;
                                 }
                              }

                              if (var15 != 0) {
                                 if (var11.aBoolean226) {
                                    if (HDToolKit.highDetail) {
                                       Class22.method930(var13, var14, var11.anInt168, var11.anInt193, var20, 256 - (var15 & 255));
                                    } else {
                                       Class74.method1312(var13, var14, var11.anInt168, var11.anInt193, var20, 256 + -(255 & var15));
                                    }
                                 } else if (HDToolKit.highDetail) {
                                    Class22.method928(var13, var14, var11.anInt168, var11.anInt193, var20, 256 + -(var15 & 255));
                                 } else {
                                    Class74.method1315(var13, var14, var11.anInt168, var11.anInt193, var20, -(var15 & 255) + 256);
                                 }
                              } else if (var11.aBoolean226) {
                                 if (HDToolKit.highDetail) {
                                    Class22.method934(var13, var14, var11.anInt168, var11.anInt193, var20);
                                 } else {
                                    Class74.method1323(var13, var14, var11.anInt168, var11.anInt193, var20);
                                 }
                              } else if (HDToolKit.highDetail) {
                                 Class22.method927(var13, var14, var11.anInt168, var11.anInt193, var20);
                              } else {
                                 Class74.method1311(var13, var14, var11.anInt168, var11.anInt193, var20);
                              }
                           } else {
                              Class3_Sub28_Sub17 var34;
                              if (var11.type == 4) {
                                 var34 = var11.method868(Class3_Sub13_Sub22.aClass109Array3270);
                                 if (var34 != null) {
                                    RSString var45 = var11.aClass94_232;
                                    if (Class3_Sub28_Sub12.method609(var11, 97)) {
                                       var21 = var11.anInt253;
                                       if (Class107.aClass11_1453 == var11 && var11.anInt222 != 0) {
                                          var21 = var11.anInt222;
                                       }

                                       if (var11.aClass94_172.length(-99) > 0) {
                                          var45 = var11.aClass94_172;
                                       }
                                    } else {
                                       var21 = var11.anInt218;
                                       if (Class107.aClass11_1453 == var11 && var11.anInt228 != 0) {
                                          var21 = var11.anInt228;
                                       }
                                    }

                                    if (var11.usingScripts && var11.anInt192 != -1) {
                                       ItemDefinition var50 = Class38.getItemDefinition(var11.anInt192, (byte) 113);
                                       var45 = var50.name;
                                       if (var45 == null) {
                                          var45 = TextCore.HasNull;
                                       }

                                       if ((var50.stackingType == 1 || var11.anInt271 != 1) && var11.anInt271 != -1) {
                                          var45 = RenderAnimationDefinition.method903(new RSString[]{ColorCore.ItemInterfaceColor, var45, Class3_Sub8.aClass94_2306, Class36.method1013((byte) -125, var11.anInt271)}, (byte) -89);
                                       }
                                    }

                                    if (Class3_Sub13_Sub7.aClass11_3087 == var11) {
                                       var21 = var11.anInt218;
                                       var45 = TextCore.LoadingPleaseWait;
                                    }

                                    if (!var11.usingScripts) {
                                       var45 = Class73.method1303(var11, var45);
                                    }

                                    var34.method676(var45, var13, var14, var11.anInt168, var11.anInt193, var21, !var11.aBoolean215 ? -1 : 0, var11.anInt194, var11.anInt225, var11.anInt205);
                                 } else if (GameShell.aBoolean6) {
                                    Class20.method909(-13, var11);
                                 }
                              } else if (5 == var11.type) {
                                 Class3_Sub28_Sub16 var37;
                                 if (var11.usingScripts) {
                                    if (var11.anInt192 == -1) {
                                       var37 = var11.method866(false);
                                       //TODO: this is where I get ma stuff
                                    } else {
                                       var37 = Class114.method1707(var11.anInt288, var11.anInt192, var11.aBoolean227, var11.anInt271, var11.anInt287);
                                    }

                                    if (var37 == null) {
                                       if (GameShell.aBoolean6) {
                                          Class20.method909(-40, var11);
                                       }
                                    } else {
                                       var21 = var37.anInt3697;
                                       var22 = var37.anInt3706;
                                       if (var11.aBoolean186) {
                                          var23 = (var21 + -1 + var11.anInt168) / var21;
                                          var24 = (var11.anInt193 - 1 - -var22) / var22;
                                          if (HDToolKit.highDetail) {
                                             Class22.method931(var13, var14, var11.anInt168 + var13, var11.anInt193 + var14);
                                             var39 = Class140_Sub6.method2021((byte) -94, var37.anInt3707);
                                             var46 = Class140_Sub6.method2021((byte) -113, var37.anInt3696);
                                             Class3_Sub28_Sub16_Sub1 var27 = (Class3_Sub28_Sub16_Sub1) var37;
                                             if (var39 && var46) {
                                                if (var15 == 0) {
                                                   var27.method649(var13, var14, var23, var24);
                                                } else {
                                                   var27.method646(var13, var14, -(255 & var15) + 256, var23, var24);
                                                }
                                             } else if (var39) {
                                                for (var28 = 0; var28 < var24; ++var28) {
                                                   if (var15 == 0) {
                                                      var27.method649(var13, var28 * var22 + var14, var23, 1);
                                                   } else {
                                                      var27.method646(var13, var14 + var28 * var22, 256 + -(var15 & 255), var23, 1);
                                                   }
                                                }
                                             } else if (var46) {
                                                for (var28 = 0; var28 < var23; ++var28) {
                                                   if (var15 == 0) {
                                                      var27.method649(var21 * var28 + var13, var14, 1, var24);
                                                   } else {
                                                      var27.method646(var21 * var28 + var13, var14, -(var15 & 255) + 256, 1, var24);
                                                   }
                                                }
                                             } else {
                                                for (var28 = 0; var23 > var28; ++var28) {
                                                   for (var29 = 0; var29 < var24; ++var29) {
                                                      if (var15 == 0) {
                                                         var37.method643(var13 - -(var21 * var28), var22 * var29 + var14);
                                                      } else {
                                                         var37.method637(var28 * var21 + var13, var22 * var29 + var14, -(255 & var15) + 256);
                                                      }
                                                   }
                                                }
                                             }

                                             Class22.method935(var0, var6, var4, var7);
                                          } else {
                                             Class74.method1326(var13, var14, var13 - -var11.anInt168, var14 - -var11.anInt193);

                                             for (var25 = 0; var25 < var23; ++var25) {
                                                for (var26 = 0; var24 > var26; ++var26) {
                                                   if (var11.anInt301 == 0) {
                                                      if (0 == var15) {
                                                         var37.method643(var25 * var21 + var13, var22 * var26 + var14);
                                                      } else {
                                                         var37.method637(var25 * var21 + var13, var14 + var26 * var22, 256 - (255 & var15));
                                                      }
                                                   } else {
                                                      var37.method640(var14 - -(var22 * var26) + var22 / 2, var11.anInt301, 4096, var25 * var21 + var13 + var21 / 2);
                                                   }
                                                }
                                             }

                                             Class74.method1324(var0, var6, var4, var7);
                                          }
                                       } else {
                                          var23 = var11.anInt168 * 4096 / var21;
                                          if (var11.anInt301 == 0) {
                                             if (0 != var15) {
                                                var37.method642(var13, var14, var11.anInt168, var11.anInt193, -(255 & var15) + 256);
                                             } else if (var11.anInt168 == var21 && var11.anInt193 == var22) {
                                                var37.method643(var13, var14);
                                             } else {
                                                var37.method639(var13, var14, var11.anInt168, var11.anInt193);
                                             }
                                          } else {
                                             var37.method640(var14 + var11.anInt193 / 2, var11.anInt301, var23, var13 + var11.anInt168 / 2);
                                          }
                                       }
                                    }
                                 } else {
                                    var37 = var11.method866(Class3_Sub28_Sub12.method609(var11, 69));
                                    if (null != var37) {
                                       var37.method643(var13, var14);
                                    } else if (GameShell.aBoolean6) {
                                       Class20.method909(118, var11);
                                    }
                                 }
                              } else {
                                 ItemDefinition var42;
                                 if (var11.type == 6) {
                                    boolean var41 = Class3_Sub28_Sub12.method609(var11, 110);
                                    Model var38 = null;
                                    if (var41) {
                                       var21 = var11.secondAnimationId;
                                    } else {
                                       var21 = var11.animationId;
                                    }

                                    var23 = 0;
                                    if (var11.anInt192 != -1) {
                                       var42 = Class38.getItemDefinition(var11.anInt192, (byte) 76);
                                       var42 = var42.method1106(var11.anInt271);
                                       AnimationDefinition var52 = var21 == -1 ? null : Client.getAnimationDefinition(var21);
                                       var38 = var42.method1110(100, var11.anInt260, var11.anInt267, var52, 1, var11.anInt283);
                                       if (var38 == null) {
                                          Class20.method909(116, var11);
                                       } else {
                                          var23 = -var38.method1871() / 2;
                                       }
                                    } else if (5 != var11.modelType) {
                                       if (var21 == -1) {
                                          var38 = var11.method865(-1, (AnimationDefinition) null, -1, 126, 0, var41, Class102.player.class52);
                                          if (null == var38 && GameShell.aBoolean6) {
                                             Class20.method909(122, var11);
                                          }
                                       } else {
                                          AnimationDefinition var48 = Client.getAnimationDefinition(var21);
                                          var38 = var11.method865(var11.anInt260, var48, var11.anInt283, 127, var11.anInt267, var41, Class102.player.class52);
                                          if (null == var38 && GameShell.aBoolean6) {
                                             Class20.method909(3, var11);
                                          }
                                       }
                                    } else if (-1 == var11.itemId) {
                                       var38 = Class77.aClass52_1112.method1165((Class145[]) null, -1, (AnimationDefinition) null, (AnimationDefinition) null, 0, -1, 100, 0, -1, -1);
                                    } else {
                                       var24 = 2047 & var11.itemId;
                                       if (Class3_Sub1.localIndex == var24) {
                                          var24 = 2047;
                                       }

                                       Player var49 = Class3_Sub13_Sub22.players[var24];
                                       AnimationDefinition var56 = var21 == -1 ? null : Client.getAnimationDefinition(var21);
                                       if (null != var49 && (-2048 & var11.itemId) == (int) var49.displayName.toLong(-128) << 11) {
                                          var38 = var49.class52.method1165((Class145[]) null, -1, (AnimationDefinition) null, var56, 0, -1, -126, 0, var11.anInt283, 0);
                                       }
                                    }

                                    if (var38 != null) {
                                       if (var11.anInt184 > 0) {
                                          var24 = (var11.anInt168 << 8) / var11.anInt184;
                                       } else {
                                          var24 = 256;
                                       }

                                       if (var11.anInt312 <= 0) {
                                          var25 = 256;
                                       } else {
                                          var25 = (var11.anInt193 << 8) / var11.anInt312;
                                       }

                                       var26 = var13 - -(var11.anInt168 / 2) - -(var24 * var11.anInt259 >> 8);
                                       var47 = var11.anInt193 / 2 + var14 + (var25 * var11.anInt230 >> 8);
                                       if (HDToolKit.highDetail) {
                                          if (var11.aBoolean181) {
                                             HDToolKit.method1855(var26, var47, var11.anInt164, var11.aShort293, var24, var25);
                                          } else {
                                             HDToolKit.method1821(var26, var47, var24, var25);
                                             HDToolKit.method1825((float) var11.aShort169, 1.5F * (float) var11.aShort293);
                                          }

                                          HDToolKit.method1846();
                                          HDToolKit.method1831(true);
                                          HDToolKit.method1827(false);
                                          Class3_Sub13_Sub33.method324(Class3_Sub28_Sub10.anInt3625);
                                          if (OutputStream_Sub1.aBoolean47) {
                                             Class22.method925();
                                             HDToolKit.method1841();
                                             Class22.method935(var0, var6, var4, var7);
                                             OutputStream_Sub1.aBoolean47 = false;
                                          }

                                          if (var11.aBoolean309) {
                                             HDToolKit.method1851();
                                          }

                                          var28 = Class51.anIntArray840[var11.anInt182] * var11.anInt164 >> 16;
                                          var29 = var11.anInt164 * Class51.anIntArray851[var11.anInt182] >> 16;
                                          if (var11.usingScripts) {
                                             var38.method1893(var11.anInt308, var11.anInt280, var11.anInt182, var11.anInt258, var11.anInt264 + var28 + var23, var11.anInt264 + var29);
                                          } else {
                                             var38.method1893(var11.anInt308, 0, var11.anInt182, 0, var28, var29);
                                          }

                                          if (var11.aBoolean309) {
                                             HDToolKit.method1830();
                                          }
                                       } else {
                                          Class51.method1145(var26, var47);
                                          var28 = Class51.anIntArray840[var11.anInt182] * var11.anInt164 >> 16;
                                          var29 = var11.anInt164 * Class51.anIntArray851[var11.anInt182] >> 16;
                                          if (!var11.usingScripts) {
                                             var38.method1893(var11.anInt308, 0, var11.anInt182, 0, var28, var29);
                                          } else if (var11.aBoolean181) {
                                             ((Class140_Sub1_Sub2) var38).method1946(var11.anInt308, var11.anInt280, var11.anInt182, var11.anInt258, var11.anInt264 + var23 + var28, var29 + var11.anInt264, var11.anInt164);
                                          } else {
                                             var38.method1893(var11.anInt308, var11.anInt280, var11.anInt182, var11.anInt258, var11.anInt264 + (var28 - -var23), var29 + var11.anInt264);
                                          }

                                          Class51.method1141();
                                       }
                                    }
                                 } else {
                                    if (var11.type == 7) {
                                       var34 = var11.method868(Class3_Sub13_Sub22.aClass109Array3270);
                                       if (var34 == null) {
                                          if (GameShell.aBoolean6) {
                                             Class20.method909(-115, var11);
                                          }
                                          continue;
                                       }

                                       var21 = 0;

                                       for (var22 = 0; var22 < var11.height; ++var22) {
                                          for (var23 = 0; var23 < var11.width; ++var23) {
                                             if (0 < var11.itemAmounts[var21]) {
                                                var42 = Class38.getItemDefinition(var11.itemAmounts[var21] + -1, (byte) 104);
                                                RSString var40;
                                                if (1 != var42.stackingType && 1 == var11.itemIds[var21]) {
                                                   var40 = RenderAnimationDefinition.method903(new RSString[]{
                                                           ColorCore.ItemInterfaceColor, var42.name, Class3_Sub29.aClass94_2584
                                                   }, (byte) -67);
                                                } else {
                                                   var40 = RenderAnimationDefinition.method903(new RSString[]{
                                                           ColorCore.ItemInterfaceColor, var42.name, Class3_Sub8.aClass94_2306, Class36.method1013((byte) -100, var11.itemIds[var21])
                                                   }, (byte) -73);
                                                }

                                                var26 = var13 + var23 * (var11.anInt285 + 115);
                                                var47 = (var11.anInt290 + 12) * var22 + var14;
                                                if (var11.anInt194 == 0) {
                                                   var34.method681(var40, var26, var47, var11.anInt218, var11.aBoolean215 ? 0 : -1);
                                                } else if (var11.anInt194 == 1) {
                                                   var34.method699(var40, 57 + var26, var47, var11.anInt218, !var11.aBoolean215 ? -1 : 0);
                                                } else {
                                                   var34.method688(var40, -1 + var26 + 115, var47, var11.anInt218, !var11.aBoolean215 ? -1 : 0);
                                                }
                                             }

                                             ++var21;
                                          }
                                       }
                                    }

                                    if (var11.type == 8 && Class20.aClass11_439 == var11 && Class3_Sub13_Sub26.anInt3323 == Class75.anInt1109) {
                                       var21 = 0;
                                       var20 = 0;
                                       RSString var43 = var11.aClass94_232;
                                       Class3_Sub28_Sub17 var35 = Class126.aClass3_Sub28_Sub17_1669;
                                       var43 = Class73.method1303(var11, var43);

                                       RSString var44;
                                       while (var43.length(-58) > 0) {
                                          var25 = var43.indexOf(RSByteBuffer.aClass94_2598, 62);
                                          if (var25 == -1) {
                                             var44 = var43;
                                             var43 = Class3_Sub28_Sub14.aClass94_3672;
                                          } else {
                                             var44 = var43.method1557(var25, 0, 0);
                                             var43 = var43.method1556(var25 + 4);
                                          }

                                          var26 = var35.method682(var44);
                                          var21 += var35.anInt3727 - -1;
                                          if (var26 > var20) {
                                             var20 = var26;
                                          }
                                       }

                                       var26 = var14 - -var11.anInt193 - -5;
                                       var20 += 6;
                                       var21 += 7;
                                       if (var7 < var26 - -var21) {
                                          var26 = -var21 + var7;
                                       }

                                       var25 = -var20 + -5 + var13 - -var11.anInt168;
                                       if (var25 < 5 + var13) {
                                          var25 = 5 + var13;
                                       }

                                       if (var4 < var20 + var25) {
                                          var25 = -var20 + var4;
                                       }

                                       if (HDToolKit.highDetail) {
                                          Class22.method934(var25, var26, var20, var21, 16777120);
                                          Class22.method927(var25, var26, var20, var21, 0);
                                       } else {
                                          Class74.method1323(var25, var26, var20, var21, 16777120);
                                          Class74.method1311(var25, var26, var20, var21, 0);
                                       }

                                       var43 = var11.aClass94_232;
                                       var47 = 2 + (var26 - -var35.anInt3727);

                                       for (var43 = Class73.method1303(var11, var43); var43.length(-102) > 0; var47 += var35.anInt3727 + 1) {
                                          var28 = var43.indexOf(RSByteBuffer.aClass94_2598, 86);
                                          if (var28 == -1) {
                                             var44 = var43;
                                             var43 = Class3_Sub28_Sub14.aClass94_3672;
                                          } else {
                                             var44 = var43.method1557(var28, 0, 0);
                                             var43 = var43.method1556(4 + var28);
                                          }

                                          var35.method681(var44, 3 + var25, var47, 0, -1);
                                       }
                                    }

                                    if (var11.type == 9) {
                                       if (var11.aBoolean167) {
                                          var20 = var13;
                                          var22 = var13 - -var11.anInt168;
                                          var21 = var14 - -var11.anInt193;
                                          var23 = var14;
                                       } else {
                                          var20 = var13;
                                          var21 = var14;
                                          var23 = var14 - -var11.anInt193;
                                          var22 = var13 + var11.anInt168;
                                       }

                                       if (var11.anInt250 == 1) {
                                          if (HDToolKit.highDetail) {
                                             Class22.method933(var20, var21, var22, var23, var11.anInt218);
                                          } else {
                                             Class74.method1328(var20, var21, var22, var23, var11.anInt218);
                                          }
                                       } else if (HDToolKit.highDetail) {
                                          Class22.method929(var20, var21, var22, var23, var11.anInt218, var11.anInt250);
                                       } else {
                                          Class74.method1322(var20, var21, var22, var23, var11.anInt218, var11.anInt250);
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

         if(var8 <= 31) {
            quickChatMessages = (CacheIndex)null;
         }

      } catch (RuntimeException var33) {
         throw Class44.clientError(var33, "gn.B(" + var0 + ',' + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + var9 + ')');
      }
   }

   public static void method1096(byte var0) {
      try {
         quickChatMessages = null;
         if(var0 < 84) {
            method1091(true, -127);
         }

         aClass94_750 = null;
         aClass93_743 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "gn.G(" + var0 + ')');
      }
   }

   final void method1097(Node var1, long var2, byte var4) {
      try {
         if(this.anInt749 == 0) {
            Node var5 = this.aClass13_747.method877();
            Objects.requireNonNull(var5).method86(-1024);
            var5.method524();
            if(this.aClass3_Sub28_744 == var5) {
               var5 = this.aClass13_747.method877();
               Objects.requireNonNull(var5).method86(-1024);
               var5.method524();
            }
         } else {
            --this.anInt749;
         }

         this.aClass130_745.method1779(var1, var2);
         this.aClass13_747.method879(var1, (byte)-126);
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "gn.L(" + (var1 != null?"{...}":"null") + ',' + var2 + ',' + var4 + ')');
      }
   }

   static void method1098(byte var0) {
      try {
         if(Class3_Sub9.anInt2309 < 128) {
            Class3_Sub9.anInt2309 = 128;
         }

         if(Class3_Sub9.anInt2309 > 383) {
            Class3_Sub9.anInt2309 = 383;
         }

         GraphicDefinition.CAMERA_DIRECTION &= 2047;
         if(var0 >= -31) {
            quickChatMessages = (CacheIndex)null;
         }

         int var1 = Class3_Sub13_Sub13.anInt3155 >> 7;
         int var2 = Class62.anInt942 >> 7;
         int var3 = Class121.method1736(WorldListCountry.localPlane, 1, Class3_Sub13_Sub13.anInt3155, Class62.anInt942);
         int var4 = 0;
         int var5;
         if(var1 > 3 && 3 < var2 && 100 > var1 && var2 < 100) {
            for(var5 = -4 + var1; var1 - -4 >= var5; ++var5) {
               for(int var6 = -4 + var2; var6 <= 4 + var2; ++var6) {
                  int var7 = WorldListCountry.localPlane;
                  if(3 > var7 && 2 == (2 & Class9.aByteArrayArrayArray113[1][var5][var6])) {
                     ++var7;
                  }

                  int var8 = (255 & Class136.aByteArrayArrayArray1774[var7][var5][var6]) * 8 - Class44.anIntArrayArrayArray723[var7][var5][var6] + var3;
                  if(var8 > var4) {
                     var4 = var8;
                  }
               }
            }
         }

         var5 = 192 * var4;
         if(var5 > 98048) {
            var5 = 98048;
         }

         if(var5 < 32768) {
            var5 = '\u8000';
         }

         if(var5 <= Class75_Sub4.anInt2670) {
            if(Class75_Sub4.anInt2670 > var5) {
               Class75_Sub4.anInt2670 += (var5 - Class75_Sub4.anInt2670) / 80;
            }
         } else {
            Class75_Sub4.anInt2670 += (-Class75_Sub4.anInt2670 + var5) / 24;
         }

      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "gn.D(" + var0 + ')');
      }
   }

   final Class3 method1099() {
      try {

         return this.aClass130_745.method1778(-119);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "gn.F(" + -1 + ')');
      }
   }

   static int method1100(int var0, int var2) {
      try {
         if(var2 == -1) {
            return 12345678;
         } else {

            var0 = var0 * (127 & var2) >> 7;
            if(2 <= var0) {
               if(126 < var0) {
                  var0 = 126;
               }
            } else {
               var0 = 2;
            }

            return var0 + ('\uff80' & var2);
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "gn.C(" + var0 + ',' + true + ',' + var2 + ')');
      }
   }

   final void method1101() {
      try {
         this.aClass13_747.method883();
         this.aClass130_745.method1773(-112);
         this.aClass3_Sub28_744 = new Node();

         this.anInt749 = this.anInt746;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "gn.E(" + 2 + ')');
      }
   }

   Class47(int var1) {
      try {
         int var2 = 1;

         for(this.anInt749 = var1; var2 - -var2 < var1; var2 += var2) {
         }

         this.anInt746 = var1;
         this.aClass130_745 = new Class130(var2);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "gn.<init>(" + var1 + ')');
      }
   }

}
