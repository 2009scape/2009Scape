package org.runite.client;

import org.rs09.client.config.GameConfig;
import org.rs09.client.rendering.Toolkit;

public final class Class3_Sub13_Sub36 extends Class3_Sub13 {

   static int anInt3422;
   static int anInt3423;
   static float aFloat3424;
    static boolean aBoolean3094 = false;
    private int[] anIntArray3425;
   static byte[][][] aByteArrayArrayArray3430;
   private int anInt3431;
   private int anInt3433;
   private int anInt3434 = -1;
   static float aFloat3435;

   static void method338(int var1, boolean var2, int var3, int var4, int var5) {
      try {
         ++Class79.anInt1127;
         Class124.method1745();
         if(!var2) {
            Class3_Sub5.method116(true);
            Class102.method1611(71, true);
            Class3_Sub5.method116(false);
         }

         Class102.method1611(100, false);
         if(!var2) {
            Class3_Sub13_Sub28.method302(2);
         }

         Unsorted.method2067();
         if(HDToolKit.highDetail) {
            Class65.method1239(var4, 125, var5, var1, var3, true);
            var3 = Class163_Sub1.anInt2989;
            var5 = Class3_Sub28_Sub3.anInt3564;
            var4 = Class96.anInt1358;
            var1 = Unsorted.anInt31;
         }

         int var6;
         int var7;
         if(1 == Class133.anInt1753) {
            var7 = 2047 & LinkableRSString.anInt2589 + GraphicDefinition.CAMERA_DIRECTION;
            var6 = Unsorted.anInt2309;
            if(Class75_Sub4.anInt2670 / 256 > var6) {
               var6 = Class75_Sub4.anInt2670 / 256;
            }

            if(WaterfallShader.aBooleanArray2169[4] && Class166.anIntArray2073[4] + 128 > var6) {
               var6 = 128 + Class166.anIntArray2073[4];
            }

            Class140_Sub2.method1952(Unsorted.anInt3155, var1, Class121.method1736(WorldListCountry.localPlane, 1, Class102.player.anInt2819, Class102.player.anInt2829) + -50, Client.ZOOM - -(var6 * 3), var7, Unsorted.anInt942, var6);
         }

         var7 = Class7.anInt2162;
         var6 = NPC.anInt3995;
         int var8 = Class77.anInt1111;
         int var9 = Class139.anInt1823;
         int var10 = Class3_Sub13_Sub25.anInt3315;

         int var11;
         int var12;
         for(var11 = 0; 5 > var11; ++var11) {
            if(WaterfallShader.aBooleanArray2169[var11]) {
               var12 = (int)((double)(-Class3_Sub13_Sub32.anIntArray3383[var11]) + (double)(Class3_Sub13_Sub32.anIntArray3383[var11] * 2 + 1) * Math.random() + Math.sin((double)Class163_Sub1_Sub1.anIntArray4009[var11] * ((double)Class3_Sub13_Sub29.anIntArray3359[var11] / 100.0D)) * (double)Class166.anIntArray2073[var11]);
               if(var11 == 3) {
                  Class3_Sub13_Sub25.anInt3315 = var12 + Class3_Sub13_Sub25.anInt3315 & 2047;
               }

               if(var11 == 4) {
                  Class139.anInt1823 += var12;
                  if(128 > Class139.anInt1823) {
                     Class139.anInt1823 = 128;
                  }

                  if(Class139.anInt1823 > 383) {
                     Class139.anInt1823 = 383;
                  }
               }

               if(var11 == 2) {
                  Class77.anInt1111 += var12;
               }

               if(var11 == 1) {
                  Class7.anInt2162 += var12;
               }

               if(var11 == 0) {
                  NPC.anInt3995 += var12;
               }
            }
         }

         Class3_Sub28_Sub20.method725();
         if(HDToolKit.highDetail) {
            Class22.setClipping(var3, var5, var3 + var4, var5 - -var1);
            float var17 = (float)Class139.anInt1823 * 0.17578125F;
            float var16 = 0.17578125F * (float)Class3_Sub13_Sub25.anInt3315;
            if(Class133.anInt1753 == 3) {
               var17 = 360.0F * Class3_Sub28_Sub6.aFloat1169 / 6.2831855F;
               var16 = Class45.aFloat730 * 360.0F / 6.2831855F;
            }

            HDToolKit.viewport(var3, var5, var4, var1, var4 / 2 + var3, var5 - -(var1 / 2), var17, var16, Unsorted.anInt1705, Unsorted.anInt1705);
         } else {
            Class74.setClipping(var3, var5, var4 + var3, var1 + var5);
            Class51.method1134();
         }

         if(!Class38_Sub1.aBoolean2615 && var3 <= NPCDefinition.anInt1297 && var4 + var3 > NPCDefinition.anInt1297 && var5 <= Class38_Sub1.anInt2612 && Class38_Sub1.anInt2612 < var1 + var5) {
            aBoolean3094 = true;
            Unsorted.anInt59 = 0;
            var12 = Class145.screenUpperX;
            int var13 = Class1.screenUpperY;
            var11 = Class139.screenLowerX;
            Unsorted.anInt3642 = var11 + (var12 - var11) * (-var3 + NPCDefinition.anInt1297) / var4;
            int var14 = AtmosphereParser.screenLowerY;
            RenderAnimationDefinition.anInt384 = (-var13 + var14) * (Class38_Sub1.anInt2612 - var5) / var1 + var13;
         } else {
            aBoolean3094 = false;
            Unsorted.anInt59 = 0;
         }

         Class58.method1194();
         byte var19 = Class137.method1817() != 2 ?1:(byte)Class79.anInt1127;
         if(HDToolKit.highDetail) {
            HDToolKit.method1846();
            HDToolKit.method1831(true);
            HDToolKit.method1827(true);
            if(Class143.loadingStage == 10) {
               var12 = Class3_Sub30_Sub1.method809(Class106.anInt1446, Class77.anInt1111 >> 10, Unsorted.anInt3625, NPC.anInt3995 >> 10);
            } else {
               var12 = Class3_Sub30_Sub1.method809(Class106.anInt1446, Class102.player.anIntArray2755[0] >> 3, Unsorted.anInt3625, Class102.player.anIntArray2767[0] >> 3);
            }

            Class68.method1269(Class44.anInt719, !WorldListEntry.aBoolean2623);
            HDToolKit.clearScreen(var12);
            ClientErrorException.method2285(Class139.anInt1823, Class77.anInt1111, Class7.anInt2162, NPC.anInt3995, Class3_Sub13_Sub25.anInt3315);
            HDToolKit.anInt1791 = Class44.anInt719;
            Class3_Sub22.method398(NPC.anInt3995, Class7.anInt2162, Class77.anInt1111, Class139.anInt1823, Class3_Sub13_Sub25.anInt3315, Class158.aByteArrayArrayArray2008, Unsorted.anIntArray686, Class129_Sub1.anIntArray2696, Class159.anIntArray2021, Player.anIntArray3959, AnimationDefinition.anIntArray1871, WorldListCountry.localPlane + 1, var19, Class102.player.anInt2819 >> 7, Class102.player.anInt2829 >> 7);
            Unsorted.aBoolean47 = true;
            Class68.method1265();
            ClientErrorException.method2285(0, 0, 0, 0, 0);
            Class58.method1194();
            Unsorted.method1775();
            Class82.method1405(var5, var4, var3, Unsorted.anInt1705, var1, Unsorted.anInt1705, -7397);
            Class163_Sub2_Sub1.method2221(var4, var3, var1, Unsorted.anInt1705, Unsorted.anInt1705, var5);
         } else {
            Toolkit.JAVA_TOOLKIT.method934(var3, var5, var4, var1, 0);
            Class3_Sub22.method398(NPC.anInt3995, Class7.anInt2162, Class77.anInt1111, Class139.anInt1823, Class3_Sub13_Sub25.anInt3315, Class158.aByteArrayArrayArray2008, Unsorted.anIntArray686, Class129_Sub1.anIntArray2696, Class159.anIntArray2021, Player.anIntArray3959, AnimationDefinition.anIntArray1871, WorldListCountry.localPlane - -1, var19, Class102.player.anInt2819 >> 7, Class102.player.anInt2829 >> 7);
            Class58.method1194();
            Unsorted.method1775();
            Class82.method1405(var5, var4, var3, 256, var1, 256, -6403 + -994);
            Class163_Sub2_Sub1.method2221(var4, var3, var1, 256, 256, var5);
         }

         ((Class102)Class51.anInterface2_838).method1610(Class106.anInt1446);
         Class65.method1235(var4, var5, var1, var3);
         Class139.anInt1823 = var9;
         Class77.anInt1111 = var8;
         Class7.anInt2162 = var7;
         NPC.anInt3995 = var6;
         Class3_Sub13_Sub25.anInt3315 = var10;
         if(Class3_Sub13_Sub4.aBoolean3064 && Class58.aJs5Worker_917.countPriorityRequests() == 0) {
            Class3_Sub13_Sub4.aBoolean3064 = false;
         }

         if(Class3_Sub13_Sub4.aBoolean3064) {
            Toolkit.getActiveToolkit().method934(var3, var5, var4, var1, 0);
            Class3_Sub13.method164((byte)-52, false, TextCore.LoadingPleaseWait2);
         }

         if(!var2 && !Class3_Sub13_Sub4.aBoolean3064 && !Class38_Sub1.aBoolean2615 && var3 <= NPCDefinition.anInt1297 && var4 + var3 > NPCDefinition.anInt1297 && Class38_Sub1.anInt2612 >= var5 && var1 + var5 > Class38_Sub1.anInt2612) {
            method1628(var5, var4, var1, var3, Class38_Sub1.anInt2612, NPCDefinition.anInt1297);
         }

      } catch (RuntimeException var15) {
         throw ClientErrorException.clientError(var15, "ui.OA(" + -6403 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
      }
   }

   static void method1628(int var0, int var1, int var2, int var3, int var4, int var5) {
      try {
         int var9;
         int var12;
         if(Class164_Sub1.anInt3012 == 0) {
            int var10 = AtmosphereParser.screenLowerY;
            var9 = Class1.screenUpperY;
            int var8 = Class145.screenUpperX;
            int var7 = Class139.screenLowerX;
            int var11 = (var5 - var3) * (-var7 + var8) / var1 - -var7;
            var12 = var9 + (var10 + -var9) * (-var0 + var4) / var2;
            if(GameObject.aBoolean1837 && (64 & Class164.anInt2051) != 0) {
               RSInterface var13 = AbstractSprite.method638(Class3_Sub30_Sub1.anInt872, RSInterface.anInt278);
               if(var13 == null) {
                  Class25.method958((byte)-87);
               } else {
                  Class3_Sub24_Sub4.method1177(Unsorted.anInt1887, 0L, (byte)-53, TextCore.aClass94_1724, var11, (short)11, Class3_Sub28_Sub9.aClass94_3621, var12);
               }
            } else {
               if(Class158.anInt2014 == 1) {
                  Class3_Sub24_Sub4.method1177(-1, 0L, (byte)-62, TextCore.aClass94_3672, var11, (short)36, TextCore.HasFaceHere, var12);
               }

               Class3_Sub24_Sub4.method1177(-1, 0L, (byte)-75, TextCore.aClass94_3672, var11, (short)60, Class3_Sub13_Sub28.aClass94_3353, var12);
            }
         }

          long var25 = -1L;

          for(var9 = 0; Unsorted.anInt59 > var9; ++var9) {
             long var26 = Class3_Sub13_Sub38.aLongArray3448[var9];
             var12 = (int)var26 & 127;
             int var14 = ((int)var26 & 2009320690) >> 29;
             int var15 = (int)(var26 >>> 32) & Integer.MAX_VALUE;
             int var27 = 127 & (int)var26 >> 7;
             if(var25 != var26) {
                var25 = var26;
                int var18;
                if(var14 == 2 && Unsorted.method2096(WorldListCountry.localPlane, var12, var27, var26)) {
                   ObjectDefinition var16 = ObjectDefinition.getObjectDefinition(var15);
                   if(null != var16.ChildrenIds) {
                      var16 = var16.method1685(0);
                   }

                   if(null == var16) {
                      continue;
                   }

                   if(Class164_Sub1.anInt3012 == 1) {
                      Class3_Sub24_Sub4.method1177(Class99.anInt1403, var26, (byte)-58, RSString.stringCombiner(new RSString[]{RenderAnimationDefinition.aClass94_378, ColorCore.PMColor, var16.name}), var12, (short)14, TextCore.HasUse, var27);
                   } else if(GameObject.aBoolean1837) {
                      Class3_Sub28_Sub9 var17 = -1 == Unsorted.anInt1038?null:Class61.method1210(Unsorted.anInt1038);
                      if(0 != (Class164.anInt2051 & 4) && (var17 == null || var17.anInt3614 != var16.method1691(var17.anInt3614, Unsorted.anInt1038, (byte) 98))) {
                         Class3_Sub24_Sub4.method1177(Unsorted.anInt1887, var26, (byte)-77, RSString.stringCombiner(new RSString[]{TextCore.aClass94_676, ColorCore.PMColor, var16.name}), var12, (short)38, Class3_Sub28_Sub9.aClass94_3621, var27);
                      }
                   } else {
                      RSString[] var29 = var16.options;
                      if(Class123.aBoolean1656) {
                         var29 = Class3_Sub31.optionsArrayStringConstructor(var29);
                      }

                      if(var29 != null) {
                         for(var18 = 4; var18 >= 0; --var18) {
                            if(null != var29[var18]) {
                               short var19 = 0;
                               if(var18 == 0) {
                                  var19 = 42;
                               }

                               if(var18 == 1) {
                                  var19 = 50;
                               }

                               int var20 = -1;
                               if(2 == var18) {
                                  var19 = 49;
                               }

                               if(var16.anInt1493 == var18) {
                                  var20 = var16.anInt1517;
                               }

                               if(var18 == 3) {
                                  var19 = 46;
                               }

                               if(var18 == var16.anInt1520) {
                                  var20 = var16.anInt1522;
                               }

                               if(var18 == 4) {
                                  var19 = 1001;
                               }


                               if (GameConfig.OBJECT_DEBUG_ENABLED) {
                                  Class3_Sub24_Sub4.method1177(var20, var26, (byte) -91, RSString.stringCombiner(new RSString[]{ColorCore.ObjectNameColor, var16.name}), var12, var19, var29[var18], var27);
                               } else {
                                  Class3_Sub24_Sub4.method1177(var20, var26, (byte) -91, RSString.stringCombiner(new RSString[]{ColorCore.ObjectNameColor, var16.name}), var12, var19, var29[var18], var27);
                               }

                            }
                         }
                      }
                      if (GameConfig.OBJECT_DEBUG_ENABLED) {
                         Class3_Sub24_Sub4.method1177(Class131.anInt1719, (long) var16.objectId, (byte) -26, RSString.stringCombiner(new RSString[]{ColorCore.ObjectNameColor, var16.name}), var12, (short) 1004, RSString.parse("Examine" + "<br>" + " ID: (X" + var16.objectId + "(Y"), var27);
                      } else {
                         Class3_Sub24_Sub4.method1177(Class131.anInt1719, (long) var16.objectId, (byte) -26, RSString.stringCombiner(new RSString[]{ColorCore.ObjectNameColor, var16.name}), var12, (short) 1004, TextCore.HasExamine, var27);
                      }
                   }
                }

                int var21;
                int var22;
                int var33;
                Player var38;
                NPC var36;
                int var37;
                if(var14 == 1) {
                   NPC var31 = NPC.npcs[var15];
                   if((var31.definition.size & 1) == 0 && (127 & var31.anInt2819) == 0 && (var31.anInt2829 & 127) == 0 || 1 == (var31.definition.size & 1) && (127 & var31.anInt2819) == 64 && (var31.anInt2829 & 127) == 64) {
                      var33 = var31.anInt2819 - -64 - 64 * var31.definition.size;
                      var18 = -((-1 + var31.definition.size) * 64) + var31.anInt2829;

                      for(var37 = 0; var37 < Class163.localNPCCount; ++var37) {
                         var36 = NPC.npcs[Class15.localNPCIndexes[var37]];
                         var21 = -(var36.definition.size * 64) - -64 + var36.anInt2819;
                         var22 = var36.anInt2829 + -(var36.definition.size * 64) - -64;
                         if(var31 != var36 && var33 <= var21 && var31.definition.size - (-var33 + var21 >> 7) >= var36.definition.size && var18 <= var22 && var36.definition.size <= -(-var18 + var22 >> 7) + var31.definition.size) {
                            Unsorted.drawNpcRightClickOptions(var36.definition, var12, -126, Class15.localNPCIndexes[var37], var27);
                         }
                      }

                      for(var37 = 0; var37 < Class159.localPlayerCount; ++var37) {
                         var38 = Class3_Sub13_Sub22.players[Class56.localPlayerIndexes[var37]];
                         var21 = var38.anInt2819 + 64 + -(64 * var38.getSize());
                         var22 = var38.anInt2829 - (var38.getSize() * 64 + -64);
                         if(var21 >= var33 && var31.definition.size - (var21 - var33 >> 7) >= var38.getSize() && var18 <= var22 && var38.getSize() <= -(-var18 + var22 >> 7) + var31.definition.size) {
                            Class3_Sub13_Sub30.method312(Class56.localPlayerIndexes[var37], 5, var27, var38, var12);
                         }
                      }
                   }

                   Unsorted.drawNpcRightClickOptions(var31.definition, var12, -108, var15, var27);
                }

                if(var14 == 0) {
                   Player var30 = Class3_Sub13_Sub22.players[var15];
                   if((127 & var30.anInt2819) == 64 && 64 == (127 & var30.anInt2829)) {
                      var33 = var30.anInt2819 + -(64 * (-1 + var30.getSize()));
                      var18 = var30.anInt2829 + 64 + -(var30.getSize() * 64);

                      for(var37 = 0; var37 < Class163.localNPCCount; ++var37) {
                         var36 = NPC.npcs[Class15.localNPCIndexes[var37]];
                         var21 = var36.anInt2819 + -(var36.definition.size * 64) - -64;
                         var22 = var36.anInt2829 - 64 * var36.definition.size - -64;
                         if(var21 >= var33 && var36.definition.size <= -(var21 - var33 >> 7) + var30.getSize() && var18 <= var22 && -(-var18 + var22 >> 7) + var30.getSize() >= var36.definition.size) {
                            Unsorted.drawNpcRightClickOptions(var36.definition, var12, -121, Class15.localNPCIndexes[var37], var27);
                         }
                      }

                      for(var37 = 0; var37 < Class159.localPlayerCount; ++var37) {
                         var38 = Class3_Sub13_Sub22.players[Class56.localPlayerIndexes[var37]];
                         var21 = var38.anInt2819 - (var38.getSize() + -1) * 64;
                         var22 = var38.anInt2829 - (-64 + 64 * var38.getSize());
                         if(var38 != var30 && var21 >= var33 && var38.getSize() <= var30.getSize() - (var21 - var33 >> 7) && var18 <= var22 && -(var22 + -var18 >> 7) + var30.getSize() >= var38.getSize()) {
                            Class3_Sub13_Sub30.method312(Class56.localPlayerIndexes[var37], 9, var27, var38, var12);
                         }
                      }
                   }

                   Class3_Sub13_Sub30.method312(var15, 31, var27, var30, var12);
                }

                if(var14 == 3) {
                   Class61 var28 = Class3_Sub13_Sub22.aClass61ArrayArrayArray3273[WorldListCountry.localPlane][var12][var27];
                   if(null != var28) {
                      for(WorldMap var32 = (WorldMap)var28.method1212(); null != var32; var32 = (WorldMap)var28.method1219(41)) {
                         var18 = var32.aClass140_Sub7_3676.anInt2936;
                         ItemDefinition var40 = Class38.getItemDefinition(var18);
                         if(Class164_Sub1.anInt3012 == 1) {
                            Class3_Sub24_Sub4.method1177(Class99.anInt1403, (long)var18, (byte)-75, RSString.stringCombiner(new RSString[]{RenderAnimationDefinition.aClass94_378, ColorCore.BankItemColor, var40.name}), var12, (short)33, TextCore.HasUse, var27);
                         } else if(GameObject.aBoolean1837) {
                            Class3_Sub28_Sub9 var39 = Unsorted.anInt1038 == -1?null:Class61.method1210(Unsorted.anInt1038);
                            if((Class164.anInt2051 & 1) != 0 && (null == var39 || var39.anInt3614 != var40.method1115(var39.anInt3614, 100, Unsorted.anInt1038))) {
                               Class3_Sub24_Sub4.method1177(Unsorted.anInt1887, (long)var18, (byte)-70, RSString.stringCombiner(new RSString[]{TextCore.aClass94_676, ColorCore.BankItemColor, var40.name}), var12, (short)39, Class3_Sub28_Sub9.aClass94_3621, var27);
                            }
                         } else {
                            RSString[] var34 = var40.groundOptions;
                            if(Class123.aBoolean1656) {
                               var34 = Class3_Sub31.optionsArrayStringConstructor(var34);
                            }

                            for(var21 = 4; var21 >= 0; --var21) {
                               if(var34 != null && null != var34[var21]) {
                                  byte var35 = 0;
                                  if(var21 == 0) {
                                     var35 = 21;
                                  }

                                  if(1 == var21) {
                                     var35 = 34;
                                  }

                                  int var23 = -1;
                                  if(var40.anInt767 == var21) {
                                     var23 = var40.anInt758;
                                  }

                                  if(var21 == 2) {
                                     var35 = 18;
                                  }

                                  if(var40.anInt788 == var21) {
                                     var23 = var40.anInt756;
                                  }

                                  if(var21 == 3) {
                                     var35 = 20;
                                  }

                                  if(var21 == 4) {
                                     var35 = 24;
                                  }

                                  Class3_Sub24_Sub4.method1177(var23, (long)var18, (byte)-43, RSString.stringCombiner(new RSString[]{ColorCore.GroundItemColor, var40.name}), var12, var35, var34[var21], var27);
                               }
                            }
                            if (GameConfig.ITEM_DEBUG_ENABLED) {
                               Class3_Sub24_Sub4.method1177(Class131.anInt1719, (long) var18, (byte) -43, RSString.stringCombiner(new RSString[]{ColorCore.GroundItemColor, var40.name}), var12, (short) 1002, RSString.parse("Examine" + "<br>" + " ID: (X" + var40.itemId + "(Y"), var27);
                            } else {
                               Class3_Sub24_Sub4.method1177(Class131.anInt1719, (long) var18, (byte) -43, RSString.stringCombiner(new RSString[]{ColorCore.GroundItemColor, var40.name}), var12, (short) 1002, TextCore.HasExamine, var27);
                            }
                         }
                      }
                   }
                }
             }
          }

      } catch (RuntimeException var24) {
         throw ClientErrorException.clientError(var24, "ob.K(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + (byte) 97 + ')');
      }
   }

   final int[][] method166(int var1, int var2) {
      try {
         if(var1 != -1) {
            this.anInt3434 = 6;
         }

         int[][] var3 = this.aClass97_2376.method1594((byte)-120, var2);
         if(this.aClass97_2376.aBoolean1379 && this.method339()) {
            int var4 = (this.anInt3433 != Class101.anInt1427?this.anInt3433 * var2 / Class101.anInt1427:var2) * this.anInt3431;
            int[] var5 = var3[0];
            int[] var6 = var3[1];
            int[] var7 = var3[2];
            int var8;
            int var9;
            if(this.anInt3431 == Class113.anInt1559) {
               for(var8 = 0; var8 < Class113.anInt1559; ++var8) {
                  var9 = this.anIntArray3425[var4++];
                  var7[var8] = Unsorted.bitwiseAnd(var9 << 4, 4080);
                  var6[var8] = Unsorted.bitwiseAnd(var9, '\uff00') >> 4;
                  var5[var8] = Unsorted.bitwiseAnd(var9, 16711680) >> 12;
               }
            } else {
               for(var8 = 0; var8 < Class113.anInt1559; ++var8) {
                  var9 = this.anInt3431 * var8 / Class113.anInt1559;
                  int var10 = this.anIntArray3425[var4 - -var9];
                  var7[var8] = Unsorted.bitwiseAnd(var10 << 4, 4080);
                  var6[var8] = Unsorted.bitwiseAnd('\uff00', var10) >> 4;
                  var5[var8] = Unsorted.bitwiseAnd(var10 >> 12, 4080);
               }
            }
         }

         return var3;
      } catch (RuntimeException var11) {
         throw ClientErrorException.clientError(var11, "ui.T(" + var1 + ',' + var2 + ')');
      }
   }

   private boolean method339() {
      try {
         if(this.anIntArray3425 == null) {
            if(this.anInt3434 < 0) {
               return false;
            } else {
               int var2 = Class113.anInt1559;
               int var3 = Class101.anInt1427;
               int var4 = !Class17.anInterface2_408.method14((byte)-104, this.anInt3434)?128:64;
               this.anIntArray3425 = Class17.anInterface2_408.method16(64, this.anInt3434);
               this.anInt3433 = var4;
               this.anInt3431 = var4;
               Class3_Sub13_Sub3.method180(18, var3, var2);
               return this.anIntArray3425 != null;
            }
         } else {
            return true;
         }
      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "ui.LA(" + false + ')');
      }
   }

   static int method340(int var0) {
      try {
         return var0 >>> 8;
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "ui.NA(" + var0 + ',' + -51 + ')');
      }
   }

   final void method157(int var1, DataBuffer var2, boolean var3) {
      try {
         if(!var3) {
            CacheIndex.animationIndex = (CacheIndex)null;
         }

         if(0 == var1) {
            this.anInt3434 = var2.readUnsignedShort();
         }

      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "ui.A(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

   public Class3_Sub13_Sub36() {
      super(0, false);
   }

   final void method161(byte var1) {
      try {
         super.method161(var1);
         this.anIntArray3425 = null;
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "ui.BA(" + var1 + ')');
      }
   }

   static AbstractIndexedSprite[] method343() {
	      try {
	         AbstractIndexedSprite[] var1 = new AbstractIndexedSprite[Class95.anInt1338];

	         for(int var2 = 0; var2 < Class95.anInt1338; ++var2) {
	            if(HDToolKit.highDetail) {
	               var1[var2] = new HDIndexedSprite(Class3_Sub15.anInt2426, Class133.anInt1748, Class164.anIntArray2048[var2], Unsorted.anIntArray2591[var2], Class140_Sub7.anIntArray2931[var2], Unsorted.anIntArray3076[var2], Class163_Sub1.aByteArrayArray2987[var2], Class3_Sub13_Sub38.spritePalette);
	            } else {
	               var1[var2] = new LDIndexedSprite(Class3_Sub15.anInt2426, Class133.anInt1748, Class164.anIntArray2048[var2], Unsorted.anIntArray2591[var2], Class140_Sub7.anIntArray2931[var2], Unsorted.anIntArray3076[var2], Class163_Sub1.aByteArrayArray2987[var2], Class3_Sub13_Sub38.spritePalette);
	            }
             }
	         Class39.method1035((byte)113);
	         return var1;
	      } catch (RuntimeException var3) {
	         throw ClientErrorException.clientError(var3, "ui.JA(" + 1854847236 + ')');
	      }
	   }

   final int method155(byte var1) {
      try {
         if(var1 != 19) {
            this.method155((byte)-60);
         }

         return this.anInt3434;
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "ui.HA(" + var1 + ')');
      }
   }

   static void method344(int var0, int var1) {
      try {
         if(0 <= var0 && Class3_Sub24_Sub4.aBooleanArray3503.length > var0) {
            Class3_Sub24_Sub4.aBooleanArray3503[var0] = !Class3_Sub24_Sub4.aBooleanArray3503[var0];
            if(var1 != 4) {
               aByteArrayArrayArray3430 = (byte[][][])((byte[][][])null);
            }

         }
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "ui.KA(" + var0 + ',' + var1 + ')');
      }
   }

}
