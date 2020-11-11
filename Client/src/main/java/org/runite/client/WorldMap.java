package org.runite.client;

import org.rs09.client.Node;
import org.rs09.client.config.GameConfig;
import org.rs09.client.rendering.Toolkit;

final class WorldMap extends Node {

    static int anInt2737 = 0;
    static CacheIndex aClass153_3210;
    static Class3_Sub28_Sub16_Sub2 aClass3_Sub28_Sub16_Sub2_3221;
    Class140_Sub7 aClass140_Sub7_3676;



   static void worldMapZoomFontSize() {
      try {
         if(null != Unsorted.aClass3_Sub28_Sub3_2600) {
            if(anInt2737 < 10) {
               if(!aClass153_3210.method2127(Unsorted.aClass3_Sub28_Sub3_2600.aClass94_3561)) {
                  anInt2737 = CacheIndex.worldmapIndex.method2116(Unsorted.aClass3_Sub28_Sub3_2600.aClass94_3561) / 10;
                  return;
               }

               Class3_Sub13_Sub1.method169();
               anInt2737 = 10;
            }

            /**
             * This block is dealing with world map zoom
             */
            if(anInt2737 == 10) {
               Class3_Sub13_Sub21.anInt3256 = Unsorted.aClass3_Sub28_Sub3_2600.anInt3555 >> 6 << 6;
               Unsorted.anInt65 = Unsorted.aClass3_Sub28_Sub3_2600.anInt3562 >> 6 << 6;
               Class108.anInt1460 = (Unsorted.aClass3_Sub28_Sub3_2600.anInt3549 >> 6 << 6) - Unsorted.anInt65 + 64;
               Class23.anInt455 = 64 + (Unsorted.aClass3_Sub28_Sub3_2600.anInt3559 >> 6 << 6) + -Class3_Sub13_Sub21.anInt3256;
               if (GameConfig.WORLD_MAP_DEBUG) System.out.println("World Map Debug: Setting initial zoom level to: " + Unsorted.aClass3_Sub28_Sub3_2600.anInt3563);
               if(Unsorted.aClass3_Sub28_Sub3_2600.anInt3563 == 37) { //Furthest zoom 37% on interface
                  Class44.aFloat727 = 3.0F;
                  NPC.aFloat3979 = 3.0F;
               } else if (Unsorted.aClass3_Sub28_Sub3_2600.anInt3563 == 50) { //Far zoom 50% on interface
                  Class44.aFloat727 = 4.0F;
                  NPC.aFloat3979 = 4.0F;
               } else if (Unsorted.aClass3_Sub28_Sub3_2600.anInt3563 == 75) { //Default zoom 75% on interface
                  Class44.aFloat727 = 6.0F;
                  NPC.aFloat3979 = 6.0F;
               } else if (Unsorted.aClass3_Sub28_Sub3_2600.anInt3563 == 100) { //Close zoom 100% on interface
                  Class44.aFloat727 = 8.0F;
                  NPC.aFloat3979 = 8.0F;
               } else if (Unsorted.aClass3_Sub28_Sub3_2600.anInt3563 == 200) { //Closest zoom 200% on interface
                  Class44.aFloat727 = 16.0F;
                  NPC.aFloat3979 = 16.0F;
               } else {
                  Class44.aFloat727 = 8.0F; //Default value if one is not set = to 100% on interface
                  NPC.aFloat3979 = 8.0F;
               }
               /* End Minimap Zoom */


               int var1 = -Class3_Sub13_Sub21.anInt3256 + (Class102.player.anInt2819 >> 7) + Class131.anInt1716;
               var1 += -5 + (int)(Math.random() * 10.0D);
               int var2 = -Class82.anInt1152 + -(Class102.player.anInt2829 >> 7) + Unsorted.anInt65 + -1 + Class108.anInt1460;
               var2 += -5 + (int)(Math.random() * 10.0D);
               if(var1 >= 0 && var1 < Class23.anInt455 && 0 <= var2 && Class108.anInt1460 > var2) {
                  Class3_Sub28_Sub1.anInt3536 = var1;
                  Class3_Sub4.anInt2251 = var2;
               } else {
                  Class3_Sub4.anInt2251 = Unsorted.anInt65 - Unsorted.aClass3_Sub28_Sub3_2600.anInt3556 * 64 + Class108.anInt1460 + -1;
                  Class3_Sub28_Sub1.anInt3536 = Unsorted.aClass3_Sub28_Sub3_2600.anInt3558 * 64 + -Class3_Sub13_Sub21.anInt3256;
               }

               Class3_Sub5.method117();
               Class83.anIntArray1161 = new int[1 + Client.anInt869];
               int var4 = Class108.anInt1460 >> 6;
               int var3 = Class23.anInt455 >> 6;
               Class3_Sub10.aByteArrayArrayArray2339 = new byte[var3][var4][];
               int var5 = Class158_Sub1.anInt3158 >> 2 << 10;
               Class36.aByteArrayArrayArray640 = new byte[var3][var4][];
               Class44.anIntArrayArrayArray720 = new int[var3][var4][];
               RenderAnimationDefinition.aByteArrayArrayArray383 = new byte[var3][var4][];
               Class146.anIntArrayArrayArray1903 = new int[var3][var4][];
               Class3_Sub13_Sub33.aByteArrayArrayArray3390 = new byte[var3][var4][];
               int var6 = Class158_Sub1.anInt1463 >> 1;
               CS2Script.aByteArrayArrayArray2452 = new byte[var3][var4][];
               Class29.anIntArrayArrayArray558 = new int[var3][var4][];
               Class36.method1014(var6, var5);
               anInt2737 = 20;


            } else if(anInt2737 == 20) {
               if (GameConfig.WORLD_MAP_DEBUG) System.out.println("World Map Debug: World Map Stage 20: String given: " + Unsorted.aClass3_Sub28_Sub3_2600.aClass94_3561.properlyCapitalize().toString());
               Class15.method889(new DataBuffer(aClass153_3210.method2123(TextCore.worldmapUnderlay, Unsorted.aClass3_Sub28_Sub3_2600.aClass94_3561)));//This controls the world map underlay
               anInt2737 = 30;
               Class163_Sub1.method2210(true);
               Class75_Sub4.method1355();
            } else {
               if(anInt2737 == 30) {
                  if (GameConfig.WORLD_MAP_DEBUG) System.out.println("World Map Debug: World Map Stage 30: String given: " + Unsorted.aClass3_Sub28_Sub3_2600.aClass94_3561.properlyCapitalize().toString());
                  Class163_Sub2.method2219(new DataBuffer(aClass153_3210.method2123(TextCore.worldmapOverlay, Unsorted.aClass3_Sub28_Sub3_2600.aClass94_3561)));//This controls the world map overlay (water)
                  anInt2737 = 40;
                  Class75_Sub4.method1355();
               } else if(anInt2737 == 40) {
                  if (GameConfig.WORLD_MAP_DEBUG) System.out.println("World Map Debug: World Map Stage 40: String given: " + Unsorted.aClass3_Sub28_Sub3_2600.aClass94_3561.properlyCapitalize().toString());
                  Class3_Sub13_Sub34.method328(new DataBuffer(aClass153_3210.method2123(TextCore.worldmapOverlay2, Unsorted.aClass3_Sub28_Sub3_2600.aClass94_3561)));//unsure
                  anInt2737 = 50;
                  Class75_Sub4.method1355();
               } else if (anInt2737 == 50) {
                  if (GameConfig.WORLD_MAP_DEBUG) System.out.println("World Map Debug: World Map Stage 50: String given: " + Unsorted.aClass3_Sub28_Sub3_2600.aClass94_3561.properlyCapitalize().toString());
                  Class96.method1587(new DataBuffer(aClass153_3210.method2123(TextCore.aClass94_422, Unsorted.aClass3_Sub28_Sub3_2600.aClass94_3561)));//This controls the world map object drawing (buildings etc)
                  anInt2737 = 60;
                  Class163_Sub1.method2210(true);
                  Class75_Sub4.method1355();
               } else if (anInt2737 == 60) {
                  if (GameConfig.WORLD_MAP_DEBUG) System.out.println("World Map Debug: World Map Stage 60: String given: " + Unsorted.aClass3_Sub28_Sub3_2600.aClass94_3561.properlyCapitalize().toString());
                  if (aClass153_3210.method2135(RSString.stringCombiner(new RSString[]{Unsorted.aClass3_Sub28_Sub3_2600.aClass94_3561, TextCore.HasLabels}))) {
                     if (!aClass153_3210.method2127(RSString.stringCombiner(new RSString[]{Unsorted.aClass3_Sub28_Sub3_2600.aClass94_3561, TextCore.HasLabels}))) {
                        return;
                     }

                     if (GameConfig.WORLD_MAP_DEBUG) System.out.println("World Map Debug: World Map Class119.aClass131_1624: " + Unsorted.aClass3_Sub28_Sub3_2600.aClass94_3561.properlyCapitalize().toString() + ", " + TextCore.HasLabels.properlyCapitalize().toString());
                     Class119.aClass131_1624 = Class81.getWorldMapArchive(RSString.stringCombiner(new RSString[]{Unsorted.aClass3_Sub28_Sub3_2600.aClass94_3561, TextCore.HasLabels}), aClass153_3210);
                  } else {
                     Class119.aClass131_1624 = new Class131(0);
                  }

                  anInt2737 = 70;
                  Class75_Sub4.method1355();
               } else if (anInt2737 == 70) {
                  Class164_Sub2.aClass33_3019 = new Class33(11, GameShell.canvas);//Sets the font size of *some* locations on the world map interface
                  anInt2737 = 73;
                  Class163_Sub1.method2210(true);
                  Class75_Sub4.method1355();
               } else if (anInt2737 == 73) {
                  Unsorted.aClass33_1238 = new Class33(12, GameShell.canvas);//Unsure
                  anInt2737 = 76;
                  Class163_Sub1.method2210(true);
                  Class75_Sub4.method1355();
               } else if (anInt2737 == 76) {
                  Class99.aClass33_1399 = new Class33(14, GameShell.canvas);//Sets the font size of *some* cities on the world map interface
                  anInt2737 = 79;
                  Class163_Sub1.method2210(true);
                  Class75_Sub4.method1355();
               } else if (anInt2737 == 79) {
                  Class75_Sub2.aClass33_2637 = new Class33(17, GameShell.canvas);//unsure
                  anInt2737 = 82;
                  Class163_Sub1.method2210(true);
                  Class75_Sub4.method1355();
               } else if (anInt2737 == 82) {
                  Class119.aClass33_1626 = new Class33(19, GameShell.canvas);//Sets the font size of the orange region names on the world map interface
                  anInt2737 = 85;
                  Class163_Sub1.method2210(true);
                  Class75_Sub4.method1355();
               } else if (85 == anInt2737) {
                  Class75_Sub2.aClass33_2648 = new Class33(22, GameShell.canvas);//unsure
                  anInt2737 = 88;
                  Class163_Sub1.method2210(true);
                  Class75_Sub4.method1355();
               } else if (88 == anInt2737) {
                  Class161.aClass33_2034 = new Class33(26, GameShell.canvas);//unsure
                  anInt2737 = 91;
                  Class163_Sub1.method2210(true);
                  Class75_Sub4.method1355();
               } else {
                  Class91.aClass33_1305 = new Class33(30, GameShell.canvas);//unsure
                  anInt2737 = 100;
                  Class163_Sub1.method2210(true);
                  Class75_Sub4.method1355();
                  System.gc();
               }
            }
         }
      } catch (RuntimeException var7) {
         throw ClientErrorException.clientError(var7, "pa.B(" + 64 + ')');
      }
   }

   WorldMap(Class140_Sub7 var1) {
      try {
         this.aClass140_Sub7_3676 = var1;
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "pa.<init>(" + (var1 != null?"{...}":"null") + ')');
      }
   }

   static void method628(int var1, int var2, Player var3) {
      try {

         if(var3.anInt2771 == var2 && var2 != -1) {
            AnimationDefinition var4 = Client.getAnimationDefinition(var2);
            int var5 = var4.anInt1845;
            if(1 == var5) {
               var3.anInt2828 = var1;
               var3.anInt2760 = 0;
               var3.anInt2776 = 1;
               var3.anInt2832 = 0;
               var3.anInt2773 = 0;
               Unsorted.method1470(var3.anInt2829, var4, 183921384, var3.anInt2819, Class102.player == var3, var3.anInt2832);
            }

            if(var5 == 2) {
               var3.anInt2773 = 0;
            }
         } else if(-1 == var2 || var3.anInt2771 == -1 || Client.getAnimationDefinition(var2).anInt1857 >= Client.getAnimationDefinition(var3.anInt2771).anInt1857) {
            var3.anInt2776 = 1;
            var3.anInt2832 = 0;
            var3.anInt2828 = var1;
            var3.anInt2811 = var3.anInt2816;
            var3.anInt2773 = 0;
            var3.anInt2760 = 0;
            var3.anInt2771 = var2;
            if(var3.anInt2771 != -1) {
               Unsorted.method1470(var3.anInt2829, Client.getAnimationDefinition(var3.anInt2771), 183921384, var3.anInt2819, var3 == Class102.player, var3.anInt2832);
            }
         }

      } catch (RuntimeException var6) {
         throw ClientErrorException.clientError(var6, "pa.C(" + 0 + ',' + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ')');
      }
   }

    public static void drawWorldMap(int var0, int var2, int var3, int var4) {
        try {
            if (anInt2737 < 100) {
                worldMapZoomFontSize();
            }

            if (HDToolKit.highDetail) {
                Class22.setClipping(var0, var2, var0 + var4, var3 + var2);
            } else {
                Class74.setClipping(var0, var2, var0 + var4, var3 + var2);
            }

            int var6;
            int var7;
            if (anInt2737 >= 100) {
                Class17.anInt410 = (int) ((float) (var3 * 2) / Class44.aFloat727);
                Class60.anInt930 = Class3_Sub28_Sub1.anInt3536 + -((int) ((float) var4 / Class44.aFloat727));
                int var15 = -((int) ((float) var4 / Class44.aFloat727)) + Class3_Sub28_Sub1.anInt3536;
                var6 = Class3_Sub4.anInt2251 - (int) ((float) var3 / Class44.aFloat727);
                Class60.anInt934 = Class3_Sub4.anInt2251 + -((int) ((float) var3 / Class44.aFloat727));
                int var8 = Class3_Sub4.anInt2251 + (int) ((float) var3 / Class44.aFloat727);
                var7 = (int) ((float) var4 / Class44.aFloat727) + Class3_Sub28_Sub1.anInt3536;
                Class49.anInt817 = (int) ((float) (var4 * 2) / Class44.aFloat727);

                if (HDToolKit.highDetail) {
                    if (aClass3_Sub28_Sub16_Sub2_3221 == null || var4 != aClass3_Sub28_Sub16_Sub2_3221.anInt3707 || var3 != aClass3_Sub28_Sub16_Sub2_3221.anInt3696) {
                        aClass3_Sub28_Sub16_Sub2_3221 = null;
                        aClass3_Sub28_Sub16_Sub2_3221 = new Class3_Sub28_Sub16_Sub2(var4, var3);
                    }

                    Class74.setBuffer(aClass3_Sub28_Sub16_Sub2_3221.anIntArray4081, var4, var3);
                    Unsorted.method523(var4, 0, var7, var6, 0, var8, var3, var15);
                    Class23.method938(var4, 0, var7, var8, var3, 0, var15, var6);
                    Class3_Sub5.method111((byte) -54, 0, 0, var15, var4, var8, var6, var7, var3);
                    Class22.method926(aClass3_Sub28_Sub16_Sub2_3221.anIntArray4081, var0, var2, var4, var3);
                    Toolkit.JAVA_TOOLKIT.resetBuffer();
                } else {
                    Unsorted.method523(var4 + var0, var2, var7, var6, var0, var8, var2 - -var3, var15);
                    Class23.method938(var0 + var4, var0, var7, var8, var3 + var2, var2, var15, var6);
                    Class3_Sub5.method111((byte) -100, var0, var2, var15, var0 - -var4, var8, var6, var7, var3 + var2);
                }

                if (0 < AbstractSprite.anInt3704) {
                    --Class3_Sub28_Sub8.anInt3611;
                    if (Class3_Sub28_Sub8.anInt3611 == 0) {
                        Class3_Sub28_Sub8.anInt3611 = 20;
                        --AbstractSprite.anInt3704;
                    }
                }

                /*
                 * This handles the ::fpson command to overlay when a user opens up the world map
                 */
                if (ClientCommands.fpsOverlayEnabled) {
                    int var10 = -8 + var2 - -var3;
                    int var9 = -5 + (var0 - -var4);
                    Class126.aClass3_Sub28_Sub17_1669.method688(RSString.stringCombiner(new RSString[]{TextCore.aClass94_985, RSString.stringAnimator(AnimationDefinition.anInt1862)}), var9, var10, 16776960, -1);
                    Runtime var11 = Runtime.getRuntime();
                    int var12 = (int) ((var11.totalMemory() - var11.freeMemory()) / 1024L);
                    int var13 = 16776960;
                    var10 -= 15;
                    if (var12 > 65536) {
                        var13 = 16711680;
                    }

                    Class126.aClass3_Sub28_Sub17_1669.method688(RSString.stringCombiner(new RSString[]{TextCore.aClass94_1630, RSString.stringAnimator(var12), TextCore.aClass94_3055}), var9, var10, var13, -1);
                }
                /* * * * * * * */

            } else {
                byte var5 = 20;
                var6 = var0 - -(var4 / 2);
                var7 = var3 / 2 + (var2 - 18) + -var5;
                //World map pre loading brown screen
                Toolkit.getActiveToolkit().method934(var0, var2, var4, var3, 0);
                Toolkit.getActiveToolkit().drawRect(var6 - 152, var7, 304, 34, 9179409, 255);
                Toolkit.getActiveToolkit().drawRect(var6 + -151, var7 + 1, 302, 32, 0, 255);
                Toolkit.getActiveToolkit().method934(-150 + var6, var7 + 2, 3 * anInt2737, 30, 9179409);
                Toolkit.getActiveToolkit().method934(-150 + var6 + anInt2737 * 3, var7 - -2, 300 + -(3 * anInt2737), 30, 0);

                Class168.aClass3_Sub28_Sub17_2096.method699(TextCore.LoadingGeneral, var6, var5 + var7, 16777215, -1);
            }
        } catch (RuntimeException var14) {
            throw ClientErrorException.clientError(var14, "wa.FA(" + var0 + ',' + 64 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
        }
    }
}
