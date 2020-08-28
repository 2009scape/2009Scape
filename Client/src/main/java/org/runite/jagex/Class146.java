package org.runite.jagex;

import java.awt.Component;
import javax.media.opengl.GL;

abstract class Class146 {

   static int anInt1901 = 2;
   static CacheIndex aClass153_1902;
   static int[][][] anIntArrayArrayArray1903;
   static int anInt1904;


   abstract int method2078();

   static void updateInterfacePacketCounter(int packetCounter) {
      try {
         Class113.interfacePacketCounter = packetCounter + 1 & '\uffff';
         RenderAnimationDefinition.aBoolean402 = true;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "uc.D(" + packetCounter + ',' + (byte) -25 + ')');
      }
   }

   static int method2080(int var0) {
      try {
         var0 = (-715827883 & var0 >>> 1) + (1431655765 & var0);
         var0 = ((-858993460 & var0) >>> 2) + (var0 & 858993459);
         var0 = 252645135 & (var0 >>> 4) + var0;
         var0 += var0 >>> 8;
         var0 += var0 >>> 16;
         int var2 = -9 / ((-125 - -80) / 42);
         return 255 & var0;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "uc.E(" + var0 + ',' + -125 + ')');
      }
   }

   public static void method2081(int var0) {
      try {
         anIntArrayArrayArray1903 = (int[][][])null;
         if(var0 == 0) {
            aClass153_1902 = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "uc.A(" + var0 + ')');
      }
   }

   abstract void method2082(Component var2);

   static void method2083(int var0, int var1, int var2, byte[][][] var3, int var4, byte var5, int var6, int var7) {
      ++Class3_Sub28_Sub1.anInt3539;
      GameShell.anInt3 = 0;
      int var8 = var6 - 16;
      int var9 = var6 + 16;
      int var10 = var7 - 16;
      int var11 = var7 + 16;

      int var14;
      int var15;
      int var29;
      for(int var12 = Class3_Sub13_Sub35.anInt3419; var12 < Class3_Sub17.anInt2456; ++var12) {
         Class3_Sub2[][] var13 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var12];

         for(var14 = Class163_Sub1_Sub1.anInt4006; var14 < Class2.anInt67; ++var14) {
            for(var15 = Class3_Sub28_Sub7.anInt3603; var15 < Class126.anInt1665; ++var15) {
               Class3_Sub2 var16 = var13[var14][var15];
               if(var16 != null) {
                  if(Class23.aBooleanArrayArray457[var14 - Class97.anInt1375 + Class3_Sub13_Sub39.anInt3466][var15 - Class3_Sub13_Sub27.anInt3340 + Class3_Sub13_Sub39.anInt3466] && (var3 == null || var12 < var4 || var3[var12][var14][var15] != var5)) {
                     var16.aBoolean2222 = true;
                     var16.aBoolean2225 = true;
                     var16.aBoolean2236 = var16.anInt2223 > 0;

                     ++GameShell.anInt3;
                  } else {
                     var16.aBoolean2222 = false;
                     var16.aBoolean2225 = false;
                     var16.anInt2227 = 0;
                     if(var14 >= var8 && var14 <= var9 && var15 >= var10 && var15 <= var11) {
                        if(var16.aClass70_2234 != null) {
                           Class70 var17 = var16.aClass70_2234;
                           var17.aClass140_1049.method1867(0, var12, var17.anInt1057, var17.anInt1054, var17.anInt1045);
                           if(var17.aClass140_1052 != null) {
                              var17.aClass140_1052.method1867(0, var12, var17.anInt1057, var17.anInt1054, var17.anInt1045);
                           }
                        }

                        if(var16.aClass19_2233 != null) {
                           Class19 var31 = var16.aClass19_2233;
                           var31.aClass140_429.method1867(var31.anInt420, var12, var31.anInt425, var31.anInt424, var31.anInt427);
                           if(var31.aClass140_423 != null) {
                              var31.aClass140_423.method1867(var31.anInt420, var12, var31.anInt425, var31.anInt424, var31.anInt427);
                           }
                        }

                        if(var16.aClass12_2230 != null) {
                           Class12 var30 = var16.aClass12_2230;
                           var30.object.method1867(0, var12, var30.anInt326, var30.anInt324, var30.anInt330);
                        }

                        if(var16.aClass25Array2221 != null) {
                           for(var29 = 0; var29 < var16.anInt2223; ++var29) {
                              Class25 var18 = var16.aClass25Array2221[var29];
                              var18.aClass140_479.method1867(var18.anInt496, var12, var18.anInt489, var18.anInt482, var18.anInt484);
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      boolean var22 = Class44.anIntArrayArrayArray723 == Class3_Sub28_Sub7.anIntArrayArrayArray3605;
      if(HDToolKit.highDetail) {
         GL var24 = HDToolKit.gl;
         var24.glPushMatrix();
         var24.glTranslatef((float)(-var0), (float)(-var1), (float)(-var2));
         if(var22) {
            Class3_Sub22.method403();
            Class3_Sub28_Sub4.method551(-1, 3);
            Class3_Sub13_Sub17.aBoolean3207 = true;
            Class165.method2254();
            IOHandler.anInt1244 = -1;
            Class3_Sub13_Sub5.anInt3072 = -1;

            for(var14 = 0; var14 < Class3_Sub23.aClass3_Sub11ArrayArray2542[0].length; ++var14) {
               Class3_Sub11 var28 = Class3_Sub23.aClass3_Sub11ArrayArray2542[0][var14];
               float var26 = 251.5F - (var28.aBoolean2364?1.0F:0.5F);
               if(var28.anInt2355 != IOHandler.anInt1244) {
                  IOHandler.anInt1244 = var28.anInt2355;
                  Class3_Sub28_Sub2.method535((byte)56, var28.anInt2355);
                  Class92.method1512(Class72.method1297());
               }

               var28.method149(Class75_Sub2.aClass3_Sub2ArrayArrayArray2638, var26, false);
            }

            Class165.method2253();
         } else {
            for(var14 = Class3_Sub13_Sub35.anInt3419; var14 < Class3_Sub17.anInt2456; ++var14) {
               for(var15 = 0; var15 < Class3_Sub23.aClass3_Sub11ArrayArray2542[var14].length; ++var15) {
                  Class3_Sub11 var25 = Class3_Sub23.aClass3_Sub11ArrayArray2542[var14][var15];
                  float var33 = 201.5F - 50.0F * (float)var14 - (var25.aBoolean2364?1.0F:0.5F);
                  if(var25.anInt2351 != -1 && Class51.anInterface2_838.method18(var25.anInt2351, 255) == 4 && Class128.aBoolean1685) {
                     Class3_Sub28_Sub2.method535((byte)56, var25.anInt2355);
                  }

                  var25.method149(Class75_Sub2.aClass3_Sub2ArrayArrayArray2638, var33, false);
               }

               if(var14 == 0 && Class80.anInt1137 > 0) {
                  HDToolKit.method1832(101.5F);
                  Class141.method2038(Class97.anInt1375, Class3_Sub13_Sub27.anInt3340, Class3_Sub13_Sub39.anInt3466, Class23.aBooleanArrayArray457);
               }
            }

            Class68.method1277(Class75_Sub2.aClass3_Sub2ArrayArrayArray2638);
         }

         var24.glPopMatrix();
      }

      int var19;
      Class3_Sub2 var21;
      int var20;
      int var23;
      Class3_Sub2[][] var27;
      int var34;
      int var32;
      for(var23 = Class3_Sub13_Sub35.anInt3419; var23 < Class3_Sub17.anInt2456; ++var23) {
         var27 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var23];

         for(var15 = -Class3_Sub13_Sub39.anInt3466; var15 <= 0; ++var15) {
            var32 = Class97.anInt1375 + var15;
            var29 = Class97.anInt1375 - var15;
            if(var32 >= Class163_Sub1_Sub1.anInt4006 || var29 < Class2.anInt67) {
               for(var34 = -Class3_Sub13_Sub39.anInt3466; var34 <= 0; ++var34) {
                  var19 = Class3_Sub13_Sub27.anInt3340 + var34;
                  var20 = Class3_Sub13_Sub27.anInt3340 - var34;
                  if(var32 >= Class163_Sub1_Sub1.anInt4006) {
                     if(var19 >= Class3_Sub28_Sub7.anInt3603) {
                        var21 = var27[var32][var19];
                        if(var21 != null && var21.aBoolean2222) {
                           Class145.method2073(var21, true);
                        }
                     }

                     if(var20 < Class126.anInt1665) {
                        var21 = var27[var32][var20];
                        if(var21 != null && var21.aBoolean2222) {
                           Class145.method2073(var21, true);
                        }
                     }
                  }

                  if(var29 < Class2.anInt67) {
                     if(var19 >= Class3_Sub28_Sub7.anInt3603) {
                        var21 = var27[var29][var19];
                        if(var21 != null && var21.aBoolean2222) {
                           Class145.method2073(var21, true);
                        }
                     }

                     if(var20 < Class126.anInt1665) {
                        var21 = var27[var29][var20];
                        if(var21 != null && var21.aBoolean2222) {
                           Class145.method2073(var21, true);
                        }
                     }
                  }

                  if(GameShell.anInt3 == 0) {
                     if(!var22) {
                        Class3_Sub13_Sub21.aBoolean3261 = false;
                     }

                     return;
                  }
               }
            }
         }
      }

      for(var23 = Class3_Sub13_Sub35.anInt3419; var23 < Class3_Sub17.anInt2456; ++var23) {
         var27 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var23];

         for(var15 = -Class3_Sub13_Sub39.anInt3466; var15 <= 0; ++var15) {
            var32 = Class97.anInt1375 + var15;
            var29 = Class97.anInt1375 - var15;
            if(var32 >= Class163_Sub1_Sub1.anInt4006 || var29 < Class2.anInt67) {
               for(var34 = -Class3_Sub13_Sub39.anInt3466; var34 <= 0; ++var34) {
                  var19 = Class3_Sub13_Sub27.anInt3340 + var34;
                  var20 = Class3_Sub13_Sub27.anInt3340 - var34;
                  if(var32 >= Class163_Sub1_Sub1.anInt4006) {
                     if(var19 >= Class3_Sub28_Sub7.anInt3603) {
                        var21 = var27[var32][var19];
                        if(var21 != null && var21.aBoolean2222) {
                           Class145.method2073(var21, false);
                        }
                     }

                     if(var20 < Class126.anInt1665) {
                        var21 = var27[var32][var20];
                        if(var21 != null && var21.aBoolean2222) {
                           Class145.method2073(var21, false);
                        }
                     }
                  }

                  if(var29 < Class2.anInt67) {
                     if(var19 >= Class3_Sub28_Sub7.anInt3603) {
                        var21 = var27[var29][var19];
                        if(var21 != null && var21.aBoolean2222) {
                           Class145.method2073(var21, false);
                        }
                     }

                     if(var20 < Class126.anInt1665) {
                        var21 = var27[var29][var20];
                        if(var21 != null && var21.aBoolean2222) {
                           Class145.method2073(var21, false);
                        }
                     }
                  }

                  if(GameShell.anInt3 == 0) {
                     if(!var22) {
                        Class3_Sub13_Sub21.aBoolean3261 = false;
                     }

                     return;
                  }
               }
            }
         }
      }

      Class3_Sub13_Sub21.aBoolean3261 = false;
   }

   abstract void method2084(Component var1, int var2);

}
