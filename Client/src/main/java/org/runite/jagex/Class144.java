package org.runite.jagex;

import org.runite.Properties;

abstract class Class144 {

   static int anInt1881 = 0;
   static RSString aClass94_1885 = RSString.createRSString("cookiehost");
   static int anInt1887;

   abstract byte[] method2064();

   static void method2065(CacheIndex var1, CacheIndex var2) {
      try {
         Class3_Sub13_Sub19.aClass153_3227 = var1;
         AnimationDefinition.aClass153_1852 = var2;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "u.D(" + (byte) -125 + ',' + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   abstract void method2066(int var1, byte[] var2);

   static void method2067() {
      try {
         for(Class3_Sub28_Sub2 var1 = (Class3_Sub28_Sub2)Class3_Sub13_Sub15.aClass61_3177.method1222(); null != var1; var1 = (Class3_Sub28_Sub2)Class3_Sub13_Sub15.aClass61_3177.method1221()) {
            Class140_Sub2 var2 = var1.aClass140_Sub2_3545;
            if(WorldListCountry.localPlane == var2.anInt2717 && !var2.aBoolean2718) {
               if(Class44.anInt719 >= var2.anInt2703) {
                  var2.method1955(Class106.anInt1446);
                  if(var2.aBoolean2718) {
                     var1.method86(-1024);
                  } else {
                     Class20.method907(var2.anInt2717, var2.anInt2716, var2.anInt2710, var2.anInt2712, 60, var2, 0, -1L, false);
                  }
               }
            } else {
               var1.method86(-1024);
            }
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "u.G(" + false + ')');
      }
   }
   // static final void method2068(NPCDefinition var0, int var1, int var2, int var3, int var4) {
   // @Splinter
   static void drawNpcRightClickOptions(NPCDefinition var0, int var1, int var2, int var3, int var4) {
      try {
         if(Class3_Sub13_Sub34.anInt3415 < 400) {
            if(var0.childNPCs != null) {
               var0 = var0.method1471((byte)66);
            }

            if(null != var0) {
               if(var0.aBoolean1270) {
                  RSString var5 = var0.aClass94_1273;
                  if(0 != var0.anInt1260) {
                     RSString var6 = Class158.anInt2014 != 1?TextCore.HasLevel:TextCore.HasRating;
                     var5 = RenderAnimationDefinition.method903(new RSString[]{var5, Class72.combatLevelColor(var0.anInt1260, (byte)-122, Class102.player.COMBAT_LEVEL), Class72.LEFT_PARENTHESES, var6, Class72.method1298((byte)9, var0.anInt1260), Class3_Sub9.RIGHT_PARENTHESES}, (byte)-73);
                  }

                  if(Class164_Sub1.anInt3012 == 1) {
                     Class3_Sub24_Sub4.method1177(Class99.anInt1403, (long)var3, (byte)-125, RenderAnimationDefinition.method903(new RSString[]{RenderAnimationDefinition.aClass94_378, ColorCore.TextColor, var5}, (byte)-125), var1, (short)26, TextCore.HasUse, var4);
                  } else if(GameObject.aBoolean1837) {
                     Class3_Sub28_Sub9 var12 = -1 == Class69.anInt1038?null:Class61.method1210(Class69.anInt1038);
                     if((2 & Class164.anInt2051) != 0 && (var12 == null || var0.method1475(Class69.anInt1038, var12.anInt3614) != var12.anInt3614)) {
                        Class3_Sub24_Sub4.method1177(anInt1887, (long)var3, (byte)-93, RenderAnimationDefinition.method903(new RSString[]{Class40.aClass94_676, ColorCore.TextColor, var5}, (byte)-65), var1, (short)45, Class3_Sub28_Sub9.aClass94_3621, var4);
                     }
                  } else {
                     RSString[] var11 = var0.options;
                     if(Class123.aBoolean1656) {
                        var11 = Class3_Sub31.method822(var11);
                     }

                     int var7;
                     if(var11 != null) {
                        for(var7 = 4; var7 >= 0; --var7) {
                           if(var11[var7] != null && (Class158.anInt2014 != 0 || !var11[var7].equals(-113, TextCore.HasAttack))) {
                              byte var8 = 0;
                              if(var7 == 0) {
                                 var8 = 17;
                              }

                              if(var7 == 1) {
                                 var8 = 16;
                              }

                              int var9 = -1;
                              if(var7 == 2) {
                                 var8 = 4;
                              }

                              if(var7 == 3) {
                                 var8 = 19;
                              }

                              if(var0.anInt1296 == var7) {
                                 var9 = var0.anInt1253;
                              }

                              if(var0.anInt1289 == var7) {
                                 var9 = var0.anInt1278;
                              }

                              if(var7 == 4) {
                                 var8 = 2;
                              }

                              Class3_Sub24_Sub4.method1177(var9, (long)var3, (byte)-103, RenderAnimationDefinition.method903(new RSString[]{ColorCore.NPCRightClickColor, var5}, (byte)-99), var1, var8, var11[var7], var4);
                           }
                        }
                     }

                     if(0 == Class158.anInt2014 && var11 != null) {
                        for(var7 = 4; var7 >= 0; --var7) {
                           if(null != var11[var7] && var11[var7].equals(-112, TextCore.HasAttack)) {
                              short var14 = 0;
                              if(var0.anInt1260 > Class102.player.COMBAT_LEVEL && !Properties.get().isInstantAttack()) {
                                 var14 = 2000;
                                 //This var sets "attack" as a right click attack option for higher level npcs, let's make it a single click!
                              }

                              short var13 = 0;
                              if(var7 == 0) {
                                 var13 = 17;
                              }

                              if(var7 == 1) {
                                 var13 = 16;
                              }

                              if(2 == var7) {
                                 var13 = 4;
                              }

                              if(3 == var7) {
                                 var13 = 19;
                              }

                              if(var7 == 4) {
                                 var13 = 2;
                              }

                              if(0 != var13) {
                                 var13 += var14;
                              }

                              Class3_Sub24_Sub4.method1177(var0.anInt1298, (long)var3, (byte)-128, RenderAnimationDefinition.method903(new RSString[]{ColorCore.NPCRightClickColor, var5}, (byte)-124), var1, var13, var11[var7], var4);
                           }
                        }
                     }

                     Class3_Sub24_Sub4.method1177(Class131.anInt1719, (long)var3, (byte)-73, RenderAnimationDefinition.method903(new RSString[]{ColorCore.NPCRightClickColor, var5}, (byte)-69), var1, (short)1007, TextCore.HasExamine, var4);
                  }

               }
            }
         }
      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "u.A(" + (var0 != null?"{...}":"null") + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
      }
   }

   static Class3_Sub28_Sub12 method2069(int var0, int var1) {
      try {
         if(var1 >= -99) {
            return (Class3_Sub28_Sub12)null;
         } else {
            Class3_Sub28_Sub12 var2 = (Class3_Sub28_Sub12)Class49.aClass47_818.method1092((long)var0);
            if(null == var2) {
               byte[] var3 = Class8.aClass153_105.getFile(5, var0);
               var2 = new Class3_Sub28_Sub12();
               if(var3 != null) {
                  var2.method610(new RSByteBuffer(var3));
               }

               Class49.aClass47_818.method1097(var2, (long)var0, (byte)58);
            }
            return var2;
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "u.C(" + var0 + ',' + var1 + ')');
      }
   }

   public static void method2070(byte var0) {
      try {
         if(var0 < 0) {
            anInt1881 = -60;
         }
         aClass94_1885 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "u.F(" + var0 + ')');
      }
   }

}
