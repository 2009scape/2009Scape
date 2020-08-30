package org.runite.jagex;
import org.runite.Configurations;
import org.runite.GameLaunch;

import javax.media.opengl.GL;
import java.util.Objects;

final class Class104 implements Interface5 {

   static boolean[] aBooleanArray2169 = new boolean[5];
   static RSString aClass94_2171 = RSString.createRSString("");
   static CacheIndex aClass153_2172;
   private int anInt2173;
   private final float[] aFloatArray2174 = new float[4];
   private static RSString aClass94_2175 = RSString.createRSString(")4a=");


   static void method1626(byte var0) {
      try {
         Class3_Sub28_Sub4.aClass93_3572.method1524();
         Class143.aClass93_1874.method1524();
         if(var0 <= -124) {
            Class67.aClass93_1013.method1524();
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ob.F(" + var0 + ')');
      }
   }

   static boolean method1627(int var0, byte var1) {
      try {
         WorldListEntry var2 = Class3_Sub8.getWorld(97, var0);
         if(var2 == null) {
            return false;
         } else if (1 != Signlink.anInt1214 && Signlink.anInt1214 != 2 && Class44.anInt718 != 2) {
            RSString var9 = GameShell.aClass94_8;
            if (Class44.anInt718 != 0) {
               var9 = RenderAnimationDefinition.method903(new RSString[]{Class163_Sub1_Sub1.aClass94_4007, Class72.method1298((byte) 9, var2.worldId - -7000)}, (byte) -86);
            }

            if (var1 > -2) {
               return false;
            } else {
               RSString var4 = GameShell.aClass94_8;
               if (Class163_Sub2.aClass94_2996 != null) {
                  var4 = RenderAnimationDefinition.method903(new RSString[]{Class97.aClass94_1380, Class163_Sub2.aClass94_2996}, (byte) -100);
               }
               RSString var5 = RenderAnimationDefinition.method903(new RSString[]{RSString.createRSString("http:)4)4"), var2.address, var9, Class3_Sub31.aClass94_2608, Class72.method1298((byte) 9, Class3_Sub20.language), aClass94_2175, Class72.method1298((byte) 9, Class3_Sub26.anInt2554), var4, Class80.aClass94_1133, !Class3_Sub28_Sub11.aBoolean3641 ? Class164_Sub1.aClass94_3013 : Class14.aClass94_339, Class38_Sub1.aClass94_2610, !Class163_Sub2_Sub1.aBoolean4018 ? Class164_Sub1.aClass94_3013 : Class14.aClass94_339, Class118.aClass94_1617, Class3_Sub28_Sub19.aBoolean3779 ? Class14.aClass94_339 : Class164_Sub1.aClass94_3013}, (byte) -110);

               try {
                  Objects.requireNonNull(Class126.aClient1671.getAppletContext()).showDocument(var5.method1527(), "_self");
               } catch (Exception var7) {
                  return false;
               }

               return true;
            }
         } else {
            GameLaunch.SETTINGS.setIp(var2.address.toString());
            var2.address.method1568();
            Class38_Sub1.accRegistryIp = Configurations.MS_IP;
            CS2Script.anInt2451 = var2.worldId;
            if (Class44.anInt718 != 0) {
               Class162.anInt2036 = '\u9c40' + CS2Script.anInt2451;
               Class140_Sub6.accRegistryPort = Class162.anInt2036;
               Client.currentPort = CS2Script.anInt2451 + '\uc350';
            }

            return true;
         }
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "ob.E(" + var0 + ',' + var1 + ')');
      }
   }

   static void method1628(int var0, int var1, int var2, int var3, int var4, int var5) {
      try {
         int var9;
         int var12;
         if(Class164_Sub1.anInt3012 == 0) {
            int var10 = Class86.anInt1195;
            var9 = Class1.anInt55;
            int var8 = Class145.anInt1898;
            int var7 = Class139.anInt1824;
            int var11 = (var5 - var3) * (-var7 + var8) / var1 - -var7;
            var12 = var9 + (var10 + -var9) * (-var0 + var4) / var2;
            if(GameObject.aBoolean1837 && (64 & Class164.anInt2051) != 0) {
               RSInterface var13 = Class3_Sub28_Sub16.method638(Class3_Sub30_Sub1.anInt872, RSInterface.anInt278);
               if(var13 == null) {
                  Class25.method958((byte)-87);
               } else {
                  Class3_Sub24_Sub4.method1177(Class144.anInt1887, 0L, (byte)-53, Class131.aClass94_1724, var11, (short)11, Class3_Sub28_Sub9.aClass94_3621, var12);
               }
            } else {
               if(Class158.anInt2014 == 1) {
                  Class3_Sub24_Sub4.method1177(-1, 0L, (byte)-62, Class3_Sub28_Sub14.aClass94_3672, var11, (short)36, TextCore.HasFaceHere, var12);
               }

               Class3_Sub24_Sub4.method1177(-1, 0L, (byte)-75, Class3_Sub28_Sub14.aClass94_3672, var11, (short)60, Class3_Sub13_Sub28.aClass94_3353, var12);
            }
         }

          long var25 = -1L;

          for(var9 = 0; Class2.anInt59 > var9; ++var9) {
             long var26 = Class3_Sub13_Sub38.aLongArray3448[var9];
             var12 = (int)var26 & 127;
             int var14 = ((int)var26 & 2009320690) >> 29;
             int var15 = (int)(var26 >>> 32) & Integer.MAX_VALUE;
             int var27 = 127 & (int)var26 >> 7;
             if(var25 != var26) {
                var25 = var26;
                int var18;
                if(var14 == 2 && Class151.method2096(WorldListCountry.localPlane, var12, var27, var26)) {
                   ObjectDefinition var16 = Class162.getObjectDefinition(var15);
                   if(null != var16.ChildrenIds) {
                      var16 = var16.method1685(0);
                   }

                   if(null == var16) {
                      continue;
                   }

                   if(Class164_Sub1.anInt3012 == 1) {
                      Class3_Sub24_Sub4.method1177(Class99.anInt1403, var26, (byte)-58, RenderAnimationDefinition.method903(new RSString[]{RenderAnimationDefinition.aClass94_378, ColorCore.PMColor, var16.name}, (byte)-75), var12, (short)14, TextCore.HasUse, var27);
                   } else if(GameObject.aBoolean1837) {
                      Class3_Sub28_Sub9 var17 = -1 == Class69.anInt1038?null:Class61.method1210(Class69.anInt1038);
                      if(0 != (Class164.anInt2051 & 4) && (var17 == null || var17.anInt3614 != var16.method1691(var17.anInt3614, Class69.anInt1038, (byte) 98))) {
                         Class3_Sub24_Sub4.method1177(Class144.anInt1887, var26, (byte)-77, RenderAnimationDefinition.method903(new RSString[]{Class40.aClass94_676, ColorCore.PMColor, var16.name}, (byte)-122), var12, (short)38, Class3_Sub28_Sub9.aClass94_3621, var27);
                      }
                   } else {
                      RSString[] var29 = var16.options;
                      if(Class123.aBoolean1656) {
                         var29 = Class3_Sub31.method822(var29);
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

                               Class3_Sub24_Sub4.method1177(var20, var26, (byte)-91, RenderAnimationDefinition.method903(new RSString[]{ColorCore.ObjectNameColor, var16.name}, (byte)-95), var12, var19, var29[var18], var27);
                            }
                         }
                      }

                      Class3_Sub24_Sub4.method1177(Class131.anInt1719, (long)var16.objectId, (byte)-26, RenderAnimationDefinition.method903(new RSString[]{ColorCore.ObjectNameColor, var16.name}, (byte)-65), var12, (short)1004, TextCore.HasExamine, var27);
                   }
                }

                int var21;
                int var22;
                int var33;
                Player var38;
                NPC var36;
                int var37;
                if(var14 == 1) {
                   NPC var31 = Class3_Sub13_Sub24.npcs[var15];
                   if((var31.definition.size & 1) == 0 && (127 & var31.anInt2819) == 0 && (var31.anInt2829 & 127) == 0 || 1 == (var31.definition.size & 1) && (127 & var31.anInt2819) == 64 && (var31.anInt2829 & 127) == 64) {
                      var33 = var31.anInt2819 - -64 - 64 * var31.definition.size;
                      var18 = -((-1 + var31.definition.size) * 64) + var31.anInt2829;

                      for(var37 = 0; var37 < Class163.localNPCCount; ++var37) {
                         var36 = Class3_Sub13_Sub24.npcs[Class15.localNPCIndexes[var37]];
                         var21 = -(var36.definition.size * 64) - -64 + var36.anInt2819;
                         var22 = var36.anInt2829 + -(var36.definition.size * 64) - -64;
                         if(var31 != var36 && var33 <= var21 && var31.definition.size - (-var33 + var21 >> 7) >= var36.definition.size && var18 <= var22 && var36.definition.size <= -(-var18 + var22 >> 7) + var31.definition.size) {
                            Class144.drawNpcRightClickOptions(var36.definition, var12, -126, Class15.localNPCIndexes[var37], var27);
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

                   Class144.drawNpcRightClickOptions(var31.definition, var12, -108, var15, var27);
                }

                if(var14 == 0) {
                   Player var30 = Class3_Sub13_Sub22.players[var15];
                   if((127 & var30.anInt2819) == 64 && 64 == (127 & var30.anInt2829)) {
                      var33 = var30.anInt2819 + -(64 * (-1 + var30.getSize()));
                      var18 = var30.anInt2829 + 64 + -(var30.getSize() * 64);

                      for(var37 = 0; var37 < Class163.localNPCCount; ++var37) {
                         var36 = Class3_Sub13_Sub24.npcs[Class15.localNPCIndexes[var37]];
                         var21 = var36.anInt2819 + -(var36.definition.size * 64) - -64;
                         var22 = var36.anInt2829 - 64 * var36.definition.size - -64;
                         if(var21 >= var33 && var36.definition.size <= -(var21 - var33 >> 7) + var30.getSize() && var18 <= var22 && -(-var18 + var22 >> 7) + var30.getSize() >= var36.definition.size) {
                            Class144.drawNpcRightClickOptions(var36.definition, var12, -121, Class15.localNPCIndexes[var37], var27);
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
                      for(Class3_Sub28_Sub14 var32 = (Class3_Sub28_Sub14)var28.method1212(); null != var32; var32 = (Class3_Sub28_Sub14)var28.method1219(41)) {
                         var18 = var32.aClass140_Sub7_3676.anInt2936;
                         ItemDefinition var40 = Class38.getItemDefinition(var18, (byte)71);
                         if(Class164_Sub1.anInt3012 == 1) {
                            Class3_Sub24_Sub4.method1177(Class99.anInt1403, (long)var18, (byte)-75, RenderAnimationDefinition.method903(new RSString[]{RenderAnimationDefinition.aClass94_378, ColorCore.BankItemColor, var40.name}, (byte)-104), var12, (short)33, TextCore.HasUse, var27);
                         } else if(GameObject.aBoolean1837) {
                            Class3_Sub28_Sub9 var39 = Class69.anInt1038 == -1?null:Class61.method1210(Class69.anInt1038);
                            if((Class164.anInt2051 & 1) != 0 && (null == var39 || var39.anInt3614 != var40.method1115(var39.anInt3614, 100, Class69.anInt1038))) {
                               Class3_Sub24_Sub4.method1177(Class144.anInt1887, (long)var18, (byte)-70, RenderAnimationDefinition.method903(new RSString[]{Class40.aClass94_676, ColorCore.BankItemColor, var40.name}, (byte)-80), var12, (short)39, Class3_Sub28_Sub9.aClass94_3621, var27);
                            }
                         } else {
                            RSString[] var34 = var40.groundOptions;
                            if(Class123.aBoolean1656) {
                               var34 = Class3_Sub31.method822(var34);
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

                                  Class3_Sub24_Sub4.method1177(var23, (long)var18, (byte)-43, RenderAnimationDefinition.method903(new RSString[]{ColorCore.GroundItemColor, var40.name}, (byte)-66), var12, var35, var34[var21], var27);
                               }
                            }

                            Class3_Sub24_Sub4.method1177(Class131.anInt1719, (long)var18, (byte)-43, RenderAnimationDefinition.method903(new RSString[]{ColorCore.GroundItemColor, var40.name}, (byte)-70), var12, (short)1002, TextCore.HasExamine, var27);
                         }
                      }
                   }
                }
             }
          }

      } catch (RuntimeException var24) {
         throw Class44.clientError(var24, "ob.K(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + (byte) 97 + ')');
      }
   }

   static void method1629(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13, int var14, int var15, int var16, int var17, int var18, int var19) {
      int var21;
      Class126 var20;
      if(var3 == 0) {
         var20 = new Class126(var10, var11, var12, var13, -1, var18, false);

         for(var21 = var0; var21 >= 0; --var21) {
            if(Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var21][var1][var2] == null) {
               Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var21][var1][var2] = new Class3_Sub2(var21, var1, var2);
            }
         }

         Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2].aClass126_2240 = var20;
      } else if(var3 == 1) {
         var20 = new Class126(var14, var15, var16, var17, var5, var19, var6 == var7 && var6 == var8 && var6 == var9);

         for(var21 = var0; var21 >= 0; --var21) {
            if(Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var21][var1][var2] == null) {
               Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var21][var1][var2] = new Class3_Sub2(var21, var1, var2);
            }
         }

         Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2].aClass126_2240 = var20;
      } else {
         Class35 var22 = new Class35(var3, var4, var5, var1, var2, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19);

         for(var21 = var0; var21 >= 0; --var21) {
            if(Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var21][var1][var2] == null) {
               Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var21][var1][var2] = new Class3_Sub2(var21, var1, var2);
            }
         }

         Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2].aClass35_2226 = var22;
      }
   }

   public final int method24() {
      try {
         return 0;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ob.C()");
      }
   }

   public final void method23(int var1) {
      try {
         GL var2 = HDToolKit.gl;
         float var4 = (float)(1 + (var1 >> 3 & 3)) * 0.01F;
         float var3 = -0.01F * (float)(1 + (var1 & 3));
         float var5 = 0 == (var1 & 64)?4.8828125E-4F:9.765625E-4F;
         boolean var6 = (128 & var1) != 0;
         if(var6) {
            this.aFloatArray2174[0] = var5;
            this.aFloatArray2174[1] = 0.0F;
            this.aFloatArray2174[2] = 0.0F;
            this.aFloatArray2174[3] = 0.0F;
         } else {
            this.aFloatArray2174[2] = var5;
            this.aFloatArray2174[1] = 0.0F;
            this.aFloatArray2174[3] = 0.0F;
            this.aFloatArray2174[0] = 0.0F;
         }

         var2.glActiveTexture('\u84c1');
         var2.glMatrixMode(5888);
         var2.glPushMatrix();
         var2.glLoadIdentity();
         var2.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
         var2.glRotatef((float)Class140_Sub7.anInt2938 * 360.0F / 2048.0F, 1.0F, 0.0F, 0.0F);
         var2.glRotatef(360.0F * (float)Class3_Sub13_Sub8.anInt3103 / 2048.0F, 0.0F, 1.0F, 0.0F);
         var2.glTranslatef((float)(-Class9.anInt144), (float)(-Class69.anInt3695), (float)(-Class3_Sub29.anInt2587));
         var2.glTexGenfv(8192, 9474, this.aFloatArray2174, 0);
         this.aFloatArray2174[3] = var3 * (float)HDToolKit.anInt1791;
         this.aFloatArray2174[0] = 0.0F;
         this.aFloatArray2174[2] = 0.0F;
         this.aFloatArray2174[1] = var5;
         var2.glTexGenfv(8193, 9474, this.aFloatArray2174, 0);
         var2.glPopMatrix();
         if(Class88.aBoolean1227) {
            this.aFloatArray2174[3] = (float)HDToolKit.anInt1791 * var4;
            this.aFloatArray2174[1] = 0.0F;
            this.aFloatArray2174[0] = 0.0F;
            this.aFloatArray2174[2] = 0.0F;
            var2.glTexGenfv(8194, 9473, this.aFloatArray2174, 0);
         } else {
            int var7 = (int)((float)HDToolKit.anInt1791 * var4 * 64.0F);
            var2.glBindTexture(3553, Class88.anIntArray1223[var7 % 64]);
         }

         var2.glActiveTexture('\u84c0');
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "ob.B(" + var1 + ')');
      }
   }

   public final void method21() {
      try {
         GL var1 = HDToolKit.gl;
         var1.glCallList(1 + this.anInt2173);
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ob.A()");
      }
   }

   public final void method22() {
      try {
         GL var1 = HDToolKit.gl;
         var1.glCallList(this.anInt2173);
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ob.D()");
      }
   }

   public static void method1630(byte var0) {
      try {
         aClass94_2171 = null;
         aClass153_2172 = null;
         aBooleanArray2169 = null;
         if(var0 > -112) {
            method1632(-116, 108, 54, -120, 44, 6);
         }
         aClass94_2175 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ob.J(" + var0 + ')');
      }
   }

   private void method1631() {
      try {
         GL var2 = HDToolKit.gl;
         this.anInt2173 = var2.glGenLists(2);
         var2.glNewList(this.anInt2173, 4864);
         var2.glActiveTexture('\u84c1');
         if(Class88.aBoolean1227) {
            var2.glBindTexture('\u806f', Class88.anInt1229);
            var2.glTexGeni(8194, 9472, 9217);
            var2.glEnable(3170);
            var2.glEnable('\u806f');
         } else {
            var2.glEnable(3553);
         }

         var2.glTexGeni(8192, 9472, 9216);
         var2.glTexGeni(8193, 9472, 9216);
         var2.glEnable(3168);
         var2.glEnable(3169);
         var2.glActiveTexture('\u84c0');
         var2.glEndList();
         var2.glNewList(this.anInt2173 + 1, 4864);
         var2.glActiveTexture('\u84c1');
         if(Class88.aBoolean1227) {
            var2.glDisable('\u806f');
            var2.glDisable(3170);
         } else {
            var2.glDisable(3553);
         }

         var2.glDisable(3168);
         var2.glDisable(3169);
         var2.glActiveTexture('\u84c0');
         var2.glEndList();
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ob.I(" + 2 + ')');
      }
   }

   public Class104() {
      try {
         this.method1631();
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ob.<init>()");
      }
   }

   static void method1632(int var0, int var1, int var2, int var3, int var4, int var5) {
      try {
         if(var0 <= 66) {
            method1630((byte)-33);
         }

         for(int var6 = var3; var6 <= var1; ++var6) {
            Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var6], var4, 121, var2, var5);
         }

      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "ob.G(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
      }
   }

}
