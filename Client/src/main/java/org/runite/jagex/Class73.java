package org.runite.jagex;
import org.runite.Properties;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

final class Class73 implements Runnable {

   private static final RSString TOGGLE_ATK = RSString.createRSString("::toggleatk");
   private static final RSString TOGGLE_FK = RSString.createRSString("::togglefk");
static boolean aBoolean1080 = false;
   static int anInt1081 = 0;
   static int anInt1082;
   static int[] anIntArray1083;
   static boolean aBoolean1084 = false;
   private final NodeList aClass13_1086 = new NodeList();
   int anInt1087 = 0;
   static int anInt1088 = 0;
   private Thread aThread1090;
   private boolean aBoolean1091 = false;


   private void method1299(Class3_Sub28_Sub10_Sub1 var1) {
      try {
         NodeList var3 = this.aClass13_1086;
         synchronized(var3) {

            this.aClass13_1086.method879(var1, (byte)-127);
            ++this.anInt1087;
            this.aClass13_1086.notifyAll();
         }
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "k.G(" + (var1 != null?"{...}":"null") + ',' + 104 + ')');
      }
   }

   static Class3_Sub28_Sub17 method1300(int var1, byte var2, CacheIndex var3, CacheIndex var4) {
      try {
         if(var2 < 123) {
            aBoolean1080 = false;
         }
         //System.out.println("Class 73 " + var1);
         return !Class75_Sub4.method1351(var3, 0, var1, -30901)?null:NodeList.method880(var4.getFile(var1, 0));
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "k.C(" + 0 + ',' + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + (var4 != null?"{...}":"null") + ')');
      }
   }

   static void method1301(int var0, int var1, int var2, int var3, boolean var4, int var5) {
      try {
         if(Class3_Sub28_Sub7.anInt3606 != var2 || Class3_Sub7.anInt2294 != var1 || var0 != Class140_Sub3.anInt2745 && !NPC.method1986(45)) {
            Class3_Sub28_Sub7.anInt3606 = var2;
            Class3_Sub7.anInt2294 = var1;
            Class140_Sub3.anInt2745 = var0;
            if(NPC.method1986(105)) {
               Class140_Sub3.anInt2745 = 0;
            }

            if(var4) {
               Class117.method1719(28);
            } else {
               Class117.method1719(25);
            }

            Class3_Sub13.method164((byte)-125, true, TextCore.LoadingPleaseWait2);
            int var8 = Class82.anInt1152;
            int var7 = Class131.anInt1716;
            Class82.anInt1152 = var1 * 8 - 48;
            Class131.anInt1716 = 8 * (-6 + var2);
            Class3_Sub13_Sub21.aClass3_Sub28_Sub3_3264 = NodeList.method884(8 * Class3_Sub28_Sub7.anInt3606, (byte)88, 8 * Class3_Sub7.anInt2294);
            int var10 = -var8 + Class82.anInt1152;
            int var9 = Class131.anInt1716 + -var7;
            var7 = Class131.anInt1716;
            var8 = Class82.anInt1152;
            Class3_Sub13_Sub35.aClass131_3421 = null;
            int var11;
            NPC var12;
            int var13;
            if(var4) {
               Class163.localNPCCount = 0;

               for(var11 = 0; var11 < 32768; ++var11) {
                  var12 = Class3_Sub13_Sub24.npcs[var11];
                  if(null != var12) {
                     var12.anInt2819 -= 128 * var9;
                     var12.anInt2829 -= 128 * var10;
                     if(var12.anInt2819 >= 0 && var12.anInt2819 <= 13184 && var12.anInt2829 >= 0 && var12.anInt2829 <= 13184) {
                        for(var13 = 0; 10 > var13; ++var13) {
                           var12.anIntArray2767[var13] -= var9;
                           var12.anIntArray2755[var13] -= var10;
                        }

                        Class15.localNPCIndexes[Class163.localNPCCount++] = var11;
                     } else {
                        Class3_Sub13_Sub24.npcs[var11].setDefinitions((NPCDefinition)null);
                        Class3_Sub13_Sub24.npcs[var11] = null;
                     }
                  }
               }
            } else {
               for(var11 = 0; var11 < '\u8000'; ++var11) {
                  var12 = Class3_Sub13_Sub24.npcs[var11];
                  if(null != var12) {
                     for(var13 = 0; var13 < 10; ++var13) {
                        var12.anIntArray2767[var13] -= var9;
                        var12.anIntArray2755[var13] -= var10;
                     }

                     var12.anInt2819 -= 128 * var9;
                     var12.anInt2829 -= var10 * 128;
                  }
               }
            }

            for(var11 = 0; var11 < 2048; ++var11) {
               Player var23 = Class3_Sub13_Sub22.players[var11];
               if(null != var23) {
                  for(var13 = 0; 10 > var13; ++var13) {
                     var23.anIntArray2767[var13] -= var9;
                     var23.anIntArray2755[var13] -= var10;
                  }

                  var23.anInt2819 -= 128 * var9;
                  var23.anInt2829 -= 128 * var10;
               }
            }

            WorldListCountry.localPlane = var0;
            Class102.player.method1981(var5, false, var3);
            byte var25 = 104;
            byte var24 = 0;
            byte var14 = 0;
            byte var16 = 1;
            byte var15 = 104;
            byte var26 = 1;
            if(var10 < 0) {
               var16 = -1;
               var15 = -1;
               var14 = 103;
            }

            if(var9 < 0) {
               var26 = -1;
               var24 = 103;
               var25 = -1;
            }

            for(int var17 = var24; var25 != var17; var17 += var26) {
               for(int var18 = var14; var18 != var15; var18 += var16) {
                  int var19 = var9 + var17;
                  int var20 = var18 + var10;

                  for(int var21 = 0; 4 > var21; ++var21) {
                     if(var19 >= 0 && var20 >= 0 && var19 < 104 && var20 < 104) {
                        Class3_Sub13_Sub22.aClass61ArrayArrayArray3273[var21][var17][var18] = Class3_Sub13_Sub22.aClass61ArrayArrayArray3273[var21][var19][var20];
                     } else {
                        Class3_Sub13_Sub22.aClass61ArrayArrayArray3273[var21][var17][var18] = null;
                     }
                  }
               }
            }

            for(Class3_Sub4 var27 = (Class3_Sub4)Class3_Sub13_Sub6.aClass61_3075.method1222(); var27 != null; var27 = (Class3_Sub4)Class3_Sub13_Sub6.aClass61_3075.method1221()) {
               var27.anInt2248 -= var10;
               var27.anInt2264 -= var9;
               if(0 > var27.anInt2264 || var27.anInt2248 < 0 || var27.anInt2264 >= 104 || var27.anInt2248 >= 104) {
                  var27.method86(-1024);
               }
            }

            if(var4) {
               NPC.anInt3995 -= 128 * var9;
               Class77.anInt1111 -= var10 * 128;
               Class146.anInt1904 -= var10;
               MouseListeningClass.anInt1923 -= var9;
               Class157.anInt1996 -= var10;
               Canvas_Sub2.anInt30 -= var9;
            } else {
               Class133.anInt1753 = 1;
            }

            Class113.anInt1552 = 0;
            if(Class65.anInt987 != 0) {
               Class45.anInt733 -= var10;
               Class65.anInt987 -= var9;
            }

            if(HDToolKit.highDetail && var4 && (Math.abs(var9) > 104 || 104 < Math.abs(var10))) {
               Class3_Sub13_Sub14.method236();
            }

            Class58.anInt909 = -1;
            Class3_Sub13_Sub15.aClass61_3177.method1211(-122);
            Class3_Sub13_Sub30.aClass61_3364.method1211(-87);
         }
      } catch (RuntimeException var22) {
         throw Class44.clientError(var22, "k.D(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + true + ')');
      }
   }

   static Class3_Sub28_Sub6 method1302() {
      try {
         Class3_Sub28_Sub6 var1 = (Class3_Sub28_Sub6)Class126.aClass13_1666.method876((byte)100);
         if(var1 != null) {
            var1.method86(-1024);
            var1.method524();
            return var1;
         } else {
            do {
               var1 = (Class3_Sub28_Sub6)Class81.aClass13_1139.method876((byte)63);
               if(var1 == null) {
                  return null;
               }

               if(var1.b() > Class5.method830((byte)-55)) {
                  return null;
               }

               var1.method86(-1024);
               var1.method524();
            } while((Long.MIN_VALUE & var1.aLong2569) == 0L);

            return var1;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "k.J(" + (byte) -36 + ')');
      }
   }

   static RSString method1303(RSInterface var0, RSString var1) {
      try {
         if(~var1.indexOf(Class24.aClass94_468, 102) == 0) {
            return var1;
         } else {
            while(true) {
               int var3 = var1.indexOf(Class12.aClass94_331, 55);
               if(var3 == -1) {
                  while(true) {
                     var3 = var1.indexOf(Class166.aClass94_2080, 106);
                     if(var3 == -1) {
                        while(true) {
                           var3 = var1.indexOf(Class91.aClass94_1301, 95);
                           if(var3 == -1) {
                              while(true) {
                                 var3 = var1.indexOf(Class52.aClass94_852, 57);
                                 if(var3 == -1) {
                                    while(true) {
                                       var3 = var1.indexOf(Class3_Sub13_Sub35.aClass94_3418, 113);
                                       if(var3 == -1) {
                                          while(true) {
                                             var3 = var1.indexOf(Class70.aClass94_1051, 50);
                                             if(var3 == -1) {
                                                return var1;
                                             }

                                             RSString var4 = Class3_Sub28_Sub14.aClass94_3672;
                                             if(null != Class136.aClass64_1778) {
                                                var4 = Class108.method1653(Class136.aClass64_1778.anInt979);

                                                try {
                                                   if(null != Class136.aClass64_1778.anObject974) {
                                                      byte[] var5 = ((String)Class136.aClass64_1778.anObject974).getBytes("ISO-8859-1");
                                                      var4 = Class3_Sub13_Sub3.method178(var5, var5.length, 0);
                                                   }
                                                } catch (UnsupportedEncodingException var6) {
                                                }
                                             }

                                             var1 = RenderAnimationDefinition.method903(new RSString[]{var1.method1557(var3, 0, 0), var4, var1.method1556(4 + var3)}, (byte)-94);
                                          }
                                       }

                                       var1 = RenderAnimationDefinition.method903(new RSString[]{var1.method1557(var3, 0, 0), Class154.method2148(Class164_Sub2.method2247((byte)-4, 4, var0)), var1.method1556(var3 - -2)}, (byte)-107);
                                    }
                                 }

                                 var1 = RenderAnimationDefinition.method903(new RSString[]{var1.method1557(var3, 0, 0), Class154.method2148(Class164_Sub2.method2247((byte)-109, 3, var0)), var1.method1556(2 + var3)}, (byte)-70);
                              }
                           }

                           var1 = RenderAnimationDefinition.method903(new RSString[]{var1.method1557(var3, 0, 0), Class154.method2148(Class164_Sub2.method2247((byte)111, 2, var0)), var1.method1556(2 + var3)}, (byte)-74);
                        }
                     }

                     var1 = RenderAnimationDefinition.method903(new RSString[]{var1.method1557(var3, 0, 0), Class154.method2148(Class164_Sub2.method2247((byte)23, 1, var0)), var1.method1556(var3 + 2)}, (byte)-80);
                  }
               }

               var1 = RenderAnimationDefinition.method903(new RSString[]{var1.method1557(var3, 0, 0), Class154.method2148(Class164_Sub2.method2247((byte)107, 0, var0)), var1.method1556(2 + var3)}, (byte)-89);
            }
         }
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "k.K(" + (var0 != null?"{...}":"null") + ',' + (var1 != null?"{...}":"null") + ',' + 0 + ')');
      }
   }

   final void method1304() {
      try {
         this.aBoolean1091 = true;
         NodeList var2 = this.aClass13_1086;
         synchronized(var2) {
            this.aClass13_1086.notifyAll();
         }

         try {
            this.aThread1090.join();
         } catch (InterruptedException var4) {
         }

         this.aThread1090 = null;
         if(3208 != 3208) {
         }

      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "k.B(" + 3208 + ')');
      }
   }

   final Class3_Sub28_Sub10_Sub1 method1305(Class41 var1, byte[] var3, int var4) {
      try {
         Class3_Sub28_Sub10_Sub1 var5 = new Class3_Sub28_Sub10_Sub1();
         var5.aByteArray4059 = var3;
         var5.aBoolean3628 = false;
         var5.aLong2569 = (long)var4;
         var5.aClass41_4056 = var1;
         var5.anInt4061 = 2;
         this.method1299(var5);
         return var5;
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "k.A(" + (var1 != null?"{...}":"null") + ',' + 2 + ',' + (var3 != null?"{...}":"null") + ',' + var4 + ')');
      }
   }

   public static void method1306(int var0) {
      try {
         if(var0 == -16222) {
            anIntArray1083 = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "k.I(" + var0 + ')');
      }
   }

   final Class3_Sub28_Sub10_Sub1 method1307(int var1, Class41 var3) {
      try {
         Class3_Sub28_Sub10_Sub1 var4 = new Class3_Sub28_Sub10_Sub1();
         var4.aClass41_4056 = var3;
         var4.anInt4061 = 3;
         var4.aBoolean3628 = false;

         var4.aLong2569 = (long)var1;
         this.method1299(var4);
         return var4;
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "k.E(" + var1 + ',' + -27447 + ',' + (var3 != null?"{...}":"null") + ')');
      }
   }

   static void ClientCommands(RSString command) {
      try {
            int var2;
            int var3;
            Runtime var6;
            if(command.equals(-128, Class3_Sub13_Sub13.aClass94_3152)) {
               Class3_Sub13_Sub17.method246(8);

               for(var2 = 0; var2 < 10; ++var2) {
                  System.gc();
               }

               var6 = Runtime.getRuntime();
               var3 = (int)((var6.totalMemory() - var6.freeMemory()) / 1024L);
               Class3_Sub30_Sub1.addChatMessage((RSString)null, 0, RenderAnimationDefinition.method903(new RSString[]{Class3_Sub13_Sub29.aClass94_3360, Class72.method1298((byte)9, var3), TextCore.Memoryk}, (byte)-95), -1);
            }

            int var4;
            if(command.equals(-120, Class3_Sub28_Sub12.aClass94_3651)) {
               Class3_Sub13_Sub17.method246(8);

               for(var2 = 0; var2 < 10; ++var2) {
                  System.gc();
               }

               var6 = Runtime.getRuntime();
               var3 = (int)((var6.totalMemory() + -var6.freeMemory()) / 1024L);
               Class3_Sub30_Sub1.addChatMessage((RSString)null, 0, RenderAnimationDefinition.method903(new RSString[]{Class119.aClass94_1625, Class72.method1298((byte)9, var3), TextCore.Memoryk}, (byte)-94), -1);
               Class3_Sub1.method90(1);
               Class3_Sub13_Sub17.method246(8);

               for(var4 = 0; var4 < 10; ++var4) {
                  System.gc();
               }

               var3 = (int)((var6.totalMemory() + -var6.freeMemory()) / 1024L);
               Class3_Sub30_Sub1.addChatMessage((RSString)null, 0, RenderAnimationDefinition.method903(new RSString[]{Class161.aClass94_2033, Class72.method1298((byte)9, var3), TextCore.Memoryk}, (byte)-104), -1);
            }

            if(command.equals(-113, Class139.aClass94_1830)) {
               Class3_Sub30_Sub1.addChatMessage((RSString)null, 0, RenderAnimationDefinition.method903(new RSString[]{Class21.aClass94_442, Class72.method1298((byte)9, Class118.method1727((byte)123))}, (byte)-94), -1);
            }

            if(HDToolKit.highDetail && command.equals(-113, Class3_Sub28_Sub16.COMMAND_GRAPHICS_CARD_MEMORY)) {
               System.out.println("oncard_geometry:" + Class31.anInt585);
               System.out.println("oncard_2d:" + Class31.memory2D);
               System.out.println("oncard_texture:" + Class31.anInt580);
            }

            if(command.equals(60, Class151.COMMAND_BREAK_CLIENT_CONNECTION)) {
               Class3_Sub13_Sub24.method289();
            }

            if(command.equals(-118, Class3_Sub31.COMMAND_BREAK_JS5_CLIENT_CONNECTION)) {
               Class58.aClass66_917.method1254();
            }

            if(command.equals(33, Class110.COMMAND_BREAK_JS5_SERVER_CONNECTION)) {
               Class58.aClass66_917.method1244();
            }

            if(command.equals(-110, Class95.COMMAND_BREAK_CONNECTION)) {
               Class38.aClass87_665.method1431(0);
               Class3_Sub15.aClass89_2429.method1467();
               Class58.aClass66_917.method1248();
            }

            if(command.equals(-115, Class3_Sub13.COMMAND_REPLACE_CANVAS)) {
               Class3_Sub28_Sub5.aBoolean3593 = true;
            }

            if(command.equals(21, Class86.COMMAND_REBUILD)) {
               Class117.method1719(25);
            }

            if(command.equals(-120, Class58.COMMAND_TOGGLE_FPSON)) {
               Class20.aBoolean438 = true;
            }

            if(command.equals(-109, Class30.COMMAND_TOGGLE_FPSOFF)) {
               Class20.aBoolean438 = false;
            }
            if (command.equals(-120, TOGGLE_ATK)) {
            	boolean on = !Properties.get().isInstantAttack();
            	Properties.get().setInstantAttack(on);
				Properties.get().save();
                Class3_Sub30_Sub1.addChatMessage((RSString)null, 0, RSString.createRSString("Left click attack option mode toggled " + (on ? "on." : "off.")), -1);
            }
            if (command.equals(-120, TOGGLE_FK)) {
            	boolean on = !Properties.get().isModernHotkeys();
            	Properties.get().setModernHotkeys(on);
				Properties.get().save();
                Class3_Sub30_Sub1.addChatMessage((RSString)null, 0, RSString.createRSString("Modern hotkeys mode toggled " + (on ? "on." : "off.")), -1);
            }

            if(command.equals(-120, Class52.COMMAND_LOWRES_GRAPHICS)) {
               GameObject.graphicsSettings(false, 0, -1, -1);
            }

            if(command.equals(-122, Class108.COMMAND_HIGHRES_GRAPHICS_WINDOW)) {
               GameObject.graphicsSettings(false, 1, -1, -1);
            }

            if(command.equals(-106, Class121.COMMAND_HIGHRES_GRAPHICS_RESIZE)) {
               GameObject.graphicsSettings(false, 2, -1, -1);
            }

            if(command.equals(-121, Class3_Sub13_Sub15.COMMAND_HIGHRES_GRAPHICS_FULLSCREEN)) {
               GameObject.graphicsSettings(false, 3, 1024, 768);
            }

            if(command.equals(69, Class3_Sub13_Sub10.COMMAND_NOCLIP)) {
               for(var2 = 0; var2 < 4; ++var2) {
                  for(var3 = 1; var3 < 103; ++var3) {
                     for(var4 = 1; var4 < 103; ++var4) {
                        Class86.aClass91Array1182[var2].anIntArrayArray1304[var3][var4] = 0;
                     }
                  }
               }
            }

            if(command.method1558(GameObject.COMMAND_SET_PARTICLES)) {
               Class127_Sub1.method1758(command.method1556(15).method1552((byte)-124));
               Class119.method1730(Class38.aClass87_665);
               Class140_Sub2.aBoolean2705 = false;
            }

            if(command.method1558(Class3_Sub13_Sub23.aClass94_3289) && Class44.anInt718 != 0) {
               Class65.method1237(command.method1556(6).method1552((byte)-106));
            }

            if(command.equals(34, Class163.COMMAND_ERROR_TEST)) {
               throw new RuntimeException();
            }

            if(command.method1558(GameShell.aClass94_10)) {
               Class3_Sub28_Sub15.anInt3689 = command.method1556(12).trim(1).method1552((byte)-120);
               Class3_Sub30_Sub1.addChatMessage((RSString)null, 0, RenderAnimationDefinition.method903(new RSString[]{Class166.aClass94_2075, Class72.method1298((byte)9, Class3_Sub28_Sub15.anInt3689)}, (byte)-75), -1);
            }

            if(command.equals(104, Class108.COMMAND_QA_OP_TEST)) {
               Class69.aBoolean1040 = true;
            }
            /* Client tweening */
            if(command.equals(104, Class3_Sub15.COMMAND_TWEENING)) {
               if(Class3_Sub26.aBoolean2558) {
                  Class3_Sub26.aBoolean2558 = false;
                  Class3_Sub30_Sub1.addChatMessage((RSString)null, 0, Class164.aClass94_2061, -1);
               } else {
                  Class3_Sub26.aBoolean2558 = true;
                  Class3_Sub30_Sub1.addChatMessage((RSString)null, 0, Player.aClass94_3961, -1);
               }
            }

            if(command.equals(47, Class3_Sub13.COMMAND_SHIFT_DROP_CLICK)) {
               if(Class101.aBoolean1419) {
                  Class3_Sub28_Sub12.aClass94_3653.method1549();
                  Class101.aBoolean1419 = false;
               } else {
                  Class20.aClass94_434.method1549();
                  Class101.aBoolean1419 = true;
               }
            }

         Class3_Sub13_Sub1.outgoingBuffer.putOpcode(44);
         Class3_Sub13_Sub1.outgoingBuffer.putByte((byte)-38, command.length(-83) + -1);
         ++Class120.anInt1633;
         Class3_Sub13_Sub1.outgoingBuffer.putString(command.method1556(2));

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "k.H(" + (command != null?"{...}":"null") + ',' + false + ')');
      }
   }

   final Class3_Sub28_Sub10_Sub1 method1309(Class41 var1, byte var2, int var3) {
      try {
         Class3_Sub28_Sub10_Sub1 var4 = new Class3_Sub28_Sub10_Sub1();
         var4.anInt4061 = 1;
         NodeList var5 = this.aClass13_1086;
         synchronized(var5) {
            if(var2 < 39) {
               return (Class3_Sub28_Sub10_Sub1)null;
            }

            Class3_Sub28_Sub10_Sub1 var6 = (Class3_Sub28_Sub10_Sub1)this.aClass13_1086.method876((byte)100);

            while (var6 != null) {

               if ((long) var3 == var6.aLong2569 && var6.aClass41_4056 == var1 && var6.anInt4061 == 2) {
                  var4.aByteArray4059 = var6.aByteArray4059;
                  var4.aBoolean3632 = false;
                  return var4;
               }

               var6 = (Class3_Sub28_Sub10_Sub1) this.aClass13_1086.method878(29);
            }
         }

         var4.aByteArray4059 = var1.method1051(var3, (byte)85);
         var4.aBoolean3632 = false;
         var4.aBoolean3628 = true;
         return var4;
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "k.F(" + (var1 != null?"{...}":"null") + ',' + var2 + ',' + var3 + ')');
      }
   }

   public final void run() {
      try {
         while(!this.aBoolean1091) {
            NodeList var2 = this.aClass13_1086;
            Class3_Sub28_Sub10_Sub1 var1;
            synchronized(var2) {
               var1 = (Class3_Sub28_Sub10_Sub1)this.aClass13_1086.method877();
               if(null == var1) {
                  try {
                     this.aClass13_1086.wait();
                  } catch (InterruptedException var6) {
                  }
                  continue;
               }

               --this.anInt1087;
            }

            try {
               if(var1.anInt4061 == 2) {
                  var1.aClass41_4056.method1050((int)var1.aLong2569, var1.aByteArray4059.length, var1.aByteArray4059);
               } else if (var1.anInt4061 == 3) {
                  var1.aByteArray4059 = var1.aClass41_4056.method1051((int) var1.aLong2569, (byte) -77);
               }
            } catch (Exception var5) {
               Class49.method1125((String)null, var5, (byte)111);
            }

            var1.aBoolean3632 = false;
         }

      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "k.run()");
      }
   }

   public Class73() {
      try {
         Class64 var1 = Class38.aClass87_665.method1451(0, 5, this);

         while(Objects.requireNonNull(var1).anInt978 == 0) {
            Class3_Sub13_Sub34.method331(10L, 64);
         }

         if(2 == var1.anInt978) {
            throw new RuntimeException();
         } else {
            this.aThread1090 = (Thread)var1.anObject974;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "k.<init>()");
      }
   }

}
