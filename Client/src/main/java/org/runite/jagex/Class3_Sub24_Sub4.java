package org.runite.jagex;

final class Class3_Sub24_Sub4 extends Class3_Sub24 {

   private int[] anIntArray3497 = new int[16];
   private int[] anIntArray3498 = new int[16];
   private int[] anIntArray3499 = new int[16];
   private int[] anIntArray3500 = new int[16];
   private int[] anIntArray3501 = new int[16];
   private int[] anIntArray3502 = new int[16];
   static boolean[] aBooleanArray3503;
   private int[] anIntArray3504 = new int[16];
   private Class78 aClass78_3505 = new Class78();
   private int[] anIntArray3506 = new int[16];
   static int anInt3507;
   private Class130 aClass130_3508;
   int[] anIntArray3509 = new int[16];
   private int[] anIntArray3510 = new int[16];
   private int anInt3511 = 1000000;
   private Class3_Sub22[][] aClass3_Sub22ArrayArray3512 = new Class3_Sub22[16][128];
   private Class3_Sub22[][] aClass3_Sub22ArrayArray3513 = new Class3_Sub22[16][128];
   private int[] anIntArray3514 = new int[16];
   private int[] anIntArray3515 = new int[16];
   private int[] anIntArray3516 = new int[16];
   static int anInt3517;
   int[] anIntArray3518 = new int[16];
   int[] anIntArray3519 = new int[16];
   private int[] anIntArray3520 = new int[16];
   private int anInt3521 = 256;
   private boolean aBoolean3522;
   private long aLong3523;
   private int anInt3524;
   private int anInt3525;
   private long aLong3526;
   private Class3_Sub24_Sub3 aClass3_Sub24_Sub3_3527 = new Class3_Sub24_Sub3(this);
   private Class3_Sub27 aClass3_Sub27_3528;
   private int anInt3529;
   private boolean aBoolean3530;


   final synchronized boolean method470(Class3_Sub27 var1, CacheIndex var3, Class83 var4) {
      try {
         var1.method516();
         boolean var6 = true;
         int[] var7 = null;
         if(22050 > 0) {
            var7 = new int[]{22050};
         }

         int var8 = 7 / ((-122 - -20) / 50);

         for(Class3_Sub6 var9 = (Class3_Sub6)var1.aClass130_2564.method1776(20); var9 != null; var9 = (Class3_Sub6)var1.aClass130_2564.method1778(-107)) {
            int var10 = (int)var9.aLong71;
            Class3_Sub15 var11 = (Class3_Sub15)this.aClass130_3508.method1780((long)var10, 0);
            if(null == var11) {
               var11 = Class66.method1245(var3, var10);
               if(null == var11) {
                  var6 = false;
                  continue;
               }

               this.aClass130_3508.method1779(1, var11, (long)var10);
            }

            if(!var11.method373(var7, var4, var9.aByteArray2289)) {
               var6 = false;
            }
         }

         if(var6) {
            var1.method515();
         }

         return var6;
      } catch (RuntimeException var12) {
         throw Class44.clientError(var12, "va.DB(" + (var1 != null?"{...}":"null") + ',' + -122 + ',' + (var3 != null?"{...}":"null") + ',' + (var4 != null?"{...}":"null") + ',' + 22050 + ')');
      }
   }

   final synchronized void method471() {
      try {
         if((byte) 53 == 53) {
            for(Class3_Sub15 var2 = (Class3_Sub15)this.aClass130_3508.method1776(75); var2 != null; var2 = (Class3_Sub15)this.aClass130_3508.method1778(-117)) {
               var2.method369();
            }

         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "va.AB(" + (byte) 53 + ')');
      }
   }

   private final void method472(int var1, int var2) {
      try {
         int var4 = -63 % ((85 - 4) / 59);
         this.anIntArray3501[var2] = var1;
         this.anIntArray3506[var2] = Class3_Sub28_Sub15.method633(var1, -128);
         this.method484(var1, var2);
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "va.SA(" + var1 + ',' + var2 + ',' + 85 + ')');
      }
   }

   final synchronized boolean method473(int var1) {
      try {
         if(var1 >= -121) {
            this.anIntArray3509 = (int[])null;
         }

         return this.aClass78_3505.method1373();
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "va.VA(" + var1 + ')');
      }
   }

   static final void method474() {
      try {
         Canvas_Sub1.aClass93_21.method1522(-125, 5);
         Class99.aClass93_1401.method1522(-126, 5);
         if(2 != 2) {
            aBooleanArray3503 = (boolean[])null;
         }

         Class3_Sub28_Sub7_Sub1.aClass93_4051.method1522(2 ^ -127, 5);
         Class154.aClass93_1965.method1522(-128, 5);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "va.S(" + 2 + ',' + 5 + ')');
      }
   }

   private final synchronized void method475(boolean var1, Class3_Sub27 var2, boolean var3) {
      try {
         this.method507(var3);
         this.aClass78_3505.method1380(var2.aByteArray2565);
         this.aBoolean3522 = var1;
         this.aLong3526 = 0L;
         int var5 = this.aClass78_3505.method1374();

         for(int var6 = 0; var6 < var5; ++var6) {
            this.aClass78_3505.method1376(var6);
            this.aClass78_3505.method1377(var6);
            this.aClass78_3505.method1381(var6);
         }

         if((byte) -52 != -52) {
            this.anIntArray3509 = (int[])null;
         }

         this.anInt3525 = this.aClass78_3505.method1382();
         this.anInt3524 = this.aClass78_3505.anIntArray1114[this.anInt3525];
         this.aLong3523 = this.aClass78_3505.method1370(this.anInt3524);
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "va.PA(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ',' + (byte) -52 + ')');
      }
   }

   private final void method476(int var1, int var2) {
      try {
         this.anIntArray3499[var1] = var2;
         if(0 != 0) {
            this.method478(109, true, 108);
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "va.P(" + var1 + ',' + var2 + ',' + 0 + ')');
      }
   }

   static final void method477(int var0, int var2, RSInterface var3) {
      try {
         if(!true) {
            method487(98, (byte)74);
         }

         if(var3.anInt318 == 1) {
            ++Class3_Sub17.anInt2459;
            Class54.method1177(-1, 0L, (byte)-78, Class3_Sub28_Sub14.aClass94_3672, 0, (short)8, var3.aClass94_289, var3.anInt279);
         }

         RSString var4;
         if(var3.anInt318 == 2 && !GameObject.aBoolean1837) {
            var4 = Class53.method1174(var3, (byte)-31);
            if(null != var4) {
               ++Class3_Sub13_Sub7.anInt3090;
               Class54.method1177(-1, 0L, (byte)-120, RenderAnimationDefinition.method903(new RSString[]{RSString.createRSString("<col=00ff00>"), var3.aClass94_243}, (byte)-94), -1, (short)32, var4, var3.anInt279);
            }
         }

         if(var3.anInt318 == 3) {
            ++Class96.anInt1361;
            Class54.method1177(-1, 0L, (byte)-59, Class3_Sub28_Sub14.aClass94_3672, 0, (short)28, TextCore.HasClose, var3.anInt279);
         }

         if(var3.anInt318 == 4) {
            ++Class15.anInt349;
            Class54.method1177(-1, 0L, (byte)-71, Class3_Sub28_Sub14.aClass94_3672, 0, (short)59, var3.aClass94_289, var3.anInt279);
         }

         if(var3.anInt318 == 5) {
            Class54.method1177(-1, 0L, (byte)-92, Class3_Sub28_Sub14.aClass94_3672, 0, (short)51, var3.aClass94_289, var3.anInt279);
            ++Class118.anInt1623;
         }

         if(var3.anInt318 == 6 && Class3_Sub13_Sub7.aClass11_3087 == null) {
            Class54.method1177(-1, 0L, (byte)-100, Class3_Sub28_Sub14.aClass94_3672, -1, (short)41, var3.aClass94_289, var3.anInt279);
            ++CS2Script.anInt2437;
         }

         int var5;
         int var15;
         if(var3.type == 2) {
            var15 = 0;

            for(var5 = 0; var3.height > var5; ++var5) {
               for(int var6 = 0; var6 < var3.width; ++var6) {
                  int var7 = (32 - -var3.anInt285) * var6;
                  int var8 = (32 + var3.anInt290) * var5;
                  if(var15 < 20) {
                     var8 += var3.anIntArray300[var15];
                     var7 += var3.anIntArray272[var15];
                  }

                  if(var7 <= var2 && var0 >= var8 && 32 + var7 > var2 && var0 < var8 + 32) {
                     Class99.aClass11_1402 = var3;
                     Class140_Sub2.anInt2701 = var15;
                     if(var3.itemAmounts[var15] > 0) {
                        Class3_Sub1 var9 = Client.method44(var3);
                        ItemDefinition var10 = Class38.getItemDefinition(var3.itemAmounts[var15] + -1, (byte)69);
                        if(1 == Class164_Sub1.anInt3012 && var9.method99()) {
                           if(var3.anInt279 != Class3_Sub28_Sub18.anInt3764 || var15 != Class110.anInt1473) {
                              ++Class15.anInt342;
                              Class54.method1177(-1, (long)var10.itemId, (byte)-91, RenderAnimationDefinition.method903(new RSString[]{RenderAnimationDefinition.aClass94_378, ColorCore.BankItemColor, var10.name}, (byte)-59), var15, (short)40, TextCore.HasUse, var3.anInt279);
                           }
                        } else if(GameObject.aBoolean1837 && var9.method99()) {
                           Class3_Sub28_Sub9 var18 = Class69.anInt1038 != -1 ?Class61.method1210(Class69.anInt1038):null;
                           if(0 != (16 & Class164.anInt2051) && (var18 == null || var18.anInt3614 != var10.method1115(var18.anInt3614, 103, Class69.anInt1038))) {
                              ++Class3_Sub28_Sub8.anInt3609;
                              Class54.method1177(Class144.anInt1887, (long)var10.itemId, (byte)-89, RenderAnimationDefinition.method903(new RSString[]{Class40.aClass94_676, ColorCore.BankItemColor, var10.name}, (byte)-122), var15, (short)3, Class3_Sub28_Sub9.aClass94_3621, var3.anInt279);
                           }
                        } else {
                           ++Class3_Sub23.anInt2540;
                           RSString[] inventoryOptions = var10.inventoryOptions;
//                           System.out.println("inventoryOptions[0] = " + inventoryOptions[4]);
                           RSString[] var11 = new RSString[var10.inventoryOptions.length];
                           System.arraycopy(var10.inventoryOptions, 0, var11, 0, var10.inventoryOptions.length);
						if (MouseWheel.shiftDown) {
							var11[0] = var11[4];
						}
                           if(Class123.aBoolean1656) {
                              inventoryOptions = Class3_Sub31.method822(inventoryOptions);
                           }
                           int var12;
                           byte var13;
                           final boolean bool_0 = MouseWheel.shiftDown;
                           if(var9.method99()) {
                              for(var12 = 4; var12 >= 3; --var12) {
                                 if(null != inventoryOptions && null != inventoryOptions[var12]) {
                                    ++Class3_Sub28_Sub14.anInt3670;
                                    if (bool_0) {
                                       var12 = var10.getShiftClickActionIndex();
                                       inventoryOptions[var12] = inventoryOptions[4];

                                    }
                                    if(var12 == 3) {
                                       var13 = 35;
                                    } else {
                                       var13 = 58;
                                    }
                                    Class54.method1177(-1, (long)var10.itemId, (byte)-65, RenderAnimationDefinition.method903(new RSString[]{ColorCore.ItemBackpackColor, var10.name}, (byte)-66), var15, var13, inventoryOptions[var12], var3.anInt279);
                                 }
                              }
                           }

                           if(var9.method96(-2063688673)) {
                              ++Class25.anInt494;
                              Class54.method1177(Class99.anInt1403, (long)var10.itemId, (byte)-96, RenderAnimationDefinition.method903(new RSString[]{ColorCore.ItemBackpackColor, var10.name}, (byte)-66), var15, (short)22, TextCore.HasUse, var3.anInt279);
                           }

                           if(var9.method99() && inventoryOptions != null) {
                              for(var12 = 2; 0 <= var12; --var12) {
                                 if(inventoryOptions[var12] != null) {
                                    ++Class81.anInt1141;
                                    var13 = 0;
                                    if(var12 == 0) {
                                       var13 = 47;
                                    }

                                    if(var12 == 1) {
                                       var13 = 5;
                                    }

                                    if(2 == var12) {
                                       var13 = 43;
                                    }

                                    Class54.method1177(-1, (long)var10.itemId, (byte)-82, RenderAnimationDefinition.method903(new RSString[]{ColorCore.ItemBackpackColor, var10.name}, (byte)-119), var15, var13, inventoryOptions[var12], var3.anInt279);
                                 }
                              }
                           }

                           inventoryOptions = var3.options;
                           if(Class123.aBoolean1656) {
                              inventoryOptions = Class3_Sub31.method822(inventoryOptions);
                           }

                           if(inventoryOptions != null) {
                              for(var12 = 4; var12 >= 0; --var12) {
                                 if(null != inventoryOptions[var12]) {
                                    ++Class3_Sub13_Sub35.anInt3420;
                                    var13 = 0;
                                    if(0 == var12) {
                                       var13 = 25;
                                    }

                                    if(var12 == 1) {
                                       var13 = 23;
                                    }

                                    if(var12 == 2) {
                                       var13 = 48;
                                    }

                                    if(3 == var12) {
                                       var13 = 7;
                                    }

                                    if(var12 == 4) {
                                       var13 = 13;
                                    }

                                    Class54.method1177(-1, (long)var10.itemId, (byte)-51, RenderAnimationDefinition.method903(new RSString[]{
                                            ColorCore.ItemBackpackColor, var10.name}, (byte)-77), var15, var13, inventoryOptions[var12], var3.anInt279);
                                 }
                              }
                           }

                           Class54.method1177(Class131.anInt1719, (long)var10.itemId, (byte)-98, RenderAnimationDefinition.method903(new RSString[]{ColorCore.ItemBackpackColor, var10.name}, (byte)-78), var15, (short)1006, TextCore.HasExamine, var3.anInt279);
                        }
                     }
                  }

                  ++var15;
               }
            }
         }

         if(var3.usingScripts) {
            if(GameObject.aBoolean1837) {
               if(Client.method44(var3).method97() && (32 & Class164.anInt2051) != 0) {
                  ++Class29.anInt562;
                  Class54.method1177(Class144.anInt1887, 0L, (byte)-113, RenderAnimationDefinition.method903(new RSString[]{
                          Class40.aClass94_676, Class3_Sub28_Sub16.aClass94_3703, var3.aClass94_277
                  }, (byte)-90), var3.anInt191, (short)12, Class3_Sub28_Sub9.aClass94_3621, var3.anInt279);
               }
            } else {
               for(var15 = 9; var15 >= 5; --var15) {
                  RSString var16 = Class120.method1732(var3, (byte)-71, var15);
                  if(null != var16) {
                     Class54.method1177(Class3_Sub13_Sub2.method173((byte)126, var15, var3), (long)(var15 + 1), (byte)-85, var3.aClass94_277, var3.anInt191, (short)1003, var16, var3.anInt279);
                     ++Class3_Sub13_Sub11.anInt3136;
                  }
               }

               var4 = Class53.method1174(var3, (byte)-101);
               if(var4 != null) {
                  ++Class3_Sub13_Sub7.anInt3090;
                  Class54.method1177(-1, 0L, (byte)-116, var3.aClass94_277, var3.anInt191, (short)32, var4, var3.anInt279);
               }

               for(var5 = 4; var5 >= 0; --var5) {
                  RSString var17 = Class120.method1732(var3, (byte)-65, var5);
                  if(var17 != null) {
                     ++Class3_Sub13_Sub11.anInt3136;
                     Class54.method1177(Class3_Sub13_Sub2.method173((byte)53, var5, var3), (long)(var5 - -1), (byte)-48, var3.aClass94_277, var3.anInt191, (short)9, var17, var3.anInt279);
                  }
               }

               if(Client.method44(var3).method95()) {
                  ++CS2Script.anInt2437;
                  Class54.method1177(-1, 0L, (byte)-74, Class3_Sub28_Sub14.aClass94_3672, var3.anInt191, (short)41, TextCore.HasContinue, var3.anInt279);
               }
            }
         }

      } catch (RuntimeException var14) {
         throw Class44.clientError(var14, "va.JA(" + var0 + ',' + true + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ')');
      }
   }

   final synchronized int method409() {
      try {
         return 0;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "va.D()");
      }
   }

   private final void method478(int var1, boolean var2, int var3) {
      try {
         if(var2) {
            this.aLong3526 = 101L;
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "va.QA(" + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   final synchronized void method479() {
      try {
         this.method472(128, 9);
         if((byte) 98 != 98) {
            aBooleanArray3503 = (boolean[])null;
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "va.HA(" + (byte) 98 + ',' + 9 + ',' + 128 + ')');
      }
   }

   private final void method480(int var1, int var2) {
      try {
         if(0 <= var2) {
            this.anIntArray3497[var2] = 12800;
            this.anIntArray3498[var2] = 8192;
            this.anIntArray3514[var2] = 16383;
            this.anIntArray3499[var2] = 8192;
            this.anIntArray3502[var2] = 0;
            this.anIntArray3510[var2] = 8192;
            this.method502(var2, var1 ^ -8388490);
            this.method497(var2, -128);
            this.anIntArray3518[var2] = 0;
            if(var1 == 8388489) {
               this.anIntArray3500[var2] = 32767;
               this.anIntArray3504[var2] = 256;
               this.anIntArray3519[var2] = 0;
               this.method482((byte)-125, var2, 8192);
            }
         } else {
            for(var2 = 0; var2 < 16; ++var2) {
               this.method480(8388489, var2);
            }

         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "va.V(" + var1 + ',' + var2 + ')');
      }
   }

   private final void method481(byte var1, int var2) {
      try {
         int var3 = -86 % ((11 - var1) / 41);

         for(Class3_Sub22 var4 = (Class3_Sub22)this.aClass3_Sub24_Sub3_3527.aClass61_3489.method1222(); null != var4; var4 = (Class3_Sub22)this.aClass3_Sub24_Sub3_3527.aClass61_3489.method1221()) {
            if(var2 < 0 || var4.anInt2514 == var2) {
               if(null != var4.aClass3_Sub24_Sub1_2507) {
                  var4.aClass3_Sub24_Sub1_2507.method417(Class21.anInt443 / 100);
                  if(var4.aClass3_Sub24_Sub1_2507.method445()) {
                     this.aClass3_Sub24_Sub3_3527.aClass3_Sub24_Sub2_3495.method457(var4.aClass3_Sub24_Sub1_2507);
                  }

                  var4.method401();
               }

               if(0 > var4.anInt2506) {
                  this.aClass3_Sub22ArrayArray3512[var4.anInt2514][var4.anInt2520] = null;
               }

               var4.method86(-1024);
            }
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "va.NA(" + var1 + ',' + var2 + ')');
      }
   }

   private final void method482(byte var1, int var2, int var3) {
      try {
         this.anIntArray3520[var2] = var3;
         this.anIntArray3509[var2] = (int)(0.5D + 2097152.0D * Math.pow(2.0D, 5.4931640625E-4D * (double)var3));
         if(var1 > -53) {
            this.method505((byte)114);
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "va.EA(" + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   private final synchronized void method483() {
      try {
         if(-1 < 0) {
            for(int var4 = 0; 16 > var4; ++var4) {
               this.anIntArray3516[var4] = 256;
            }
         } else {
            this.anIntArray3516[-1] = 256;
         }

         if(-48 > -14) {
            this.anIntArray3514 = (int[])null;
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "va.O(" + -1 + ',' + -48 + ',' + 256 + ')');
      }
   }

   private final void method484(int var2, int var3) {
      try {
         if(0 == 0) {
            if(this.anIntArray3515[var3] != var2) {
               this.anIntArray3515[var3] = var2;

               for(int var4 = 0; var4 < 128; ++var4) {
                  this.aClass3_Sub22ArrayArray3513[var3][var4] = null;
               }
            }

         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "va.FB(" + 0 + ',' + var2 + ',' + var3 + ')');
      }
   }

   final synchronized void method485() {
      try {
         for(Class3_Sub15 var2 = (Class3_Sub15)this.aClass130_3508.method1776(63); var2 != null; var2 = (Class3_Sub15)this.aClass130_3508.method1778(-106)) {
            var2.method86(-1024);
         }

         int var3 = 7 % ((-110 - 35) / 33);
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "va.DA(" + -110 + ')');
      }
   }

   private final void method486(int var1, int var2, int var4) {
      try {
         this.method493((byte)-123, var4, 64, var2);
         if((2 & this.anIntArray3518[var2]) != 0) {
            for(Class3_Sub22 var5 = (Class3_Sub22)this.aClass3_Sub24_Sub3_3527.aClass61_3489.method1212(); var5 != null; var5 = (Class3_Sub22)this.aClass3_Sub24_Sub3_3527.aClass61_3489.method1219(98)) {
               if(var5.anInt2514 == var2 && var5.anInt2506 < 0) {
                  this.aClass3_Sub22ArrayArray3512[var2][var5.anInt2520] = null;
                  this.aClass3_Sub22ArrayArray3512[var2][var4] = var5;
                  int var6 = var5.anInt2510 - -(var5.anInt2502 * var5.anInt2522 >> 12);
                  var5.anInt2502 = 4096;
                  var5.anInt2510 += -var5.anInt2520 + var4 << 8;
                  var5.anInt2522 = -var5.anInt2510 + var6;
                  var5.anInt2520 = var4;
                  return;
               }
            }
         }

         Class3_Sub15 var11 = (Class3_Sub15)this.aClass130_3508.method1780((long)this.anIntArray3515[var2], 0);
         if(var11 != null) {
            Class3_Sub12_Sub1 var12 = var11.aClass3_Sub12_Sub1Array2431[var4];
            if(var12 != null) {
               Class3_Sub22 var7 = new Class3_Sub22();
               var7.aClass3_Sub12_Sub1_2509 = var12;
               var7.aClass3_Sub15_2527 = var11;
               var7.anInt2514 = var2;
               var7.aClass166_2504 = var11.aClass166Array2435[var4];
               var7.anInt2517 = var11.aByteArray2425[var4];
               var7.anInt2520 = var4;
               var7.anInt2513 = var11.aByteArray2430[var4] * var1 * var1 * var11.anInt2424 - -1024 >> 11;
               var7.anInt2503 = var11.aByteArray2422[var4] & 255;
               var7.anInt2510 = -(32767 & var11.aShortArray2434[var4]) + (var4 << 8);
               var7.anInt2506 = -1;
               var7.anInt2511 = 0;
               int var8 = -43 / ((71 - -41) / 61);
               var7.anInt2519 = 0;
               var7.anInt2523 = 0;
               var7.anInt2501 = 0;
               if(this.anIntArray3519[var2] == 0) {
                  var7.aClass3_Sub24_Sub1_2507 = Class3_Sub24_Sub1.method432(var12, this.method498(var7), this.method508(var7), this.method496(var7));
               } else {
                  var7.aClass3_Sub24_Sub1_2507 = Class3_Sub24_Sub1.method432(var12, this.method498(var7), 0, this.method496(var7));
                  this.method501(var7, var11.aShortArray2434[var4] < 0, (byte)-114);
               }

               if(0 > var11.aShortArray2434[var4]) {
                  var7.aClass3_Sub24_Sub1_2507.method429(-1);
               }

               if(var7.anInt2517 >= 0) {
                  Class3_Sub22 var9 = this.aClass3_Sub22ArrayArray3513[var2][var7.anInt2517];
                  if(null != var9 && var9.anInt2506 < 0) {
                     this.aClass3_Sub22ArrayArray3512[var2][var9.anInt2520] = null;
                     var9.anInt2506 = 0;
                  }

                  this.aClass3_Sub22ArrayArray3513[var2][var7.anInt2517] = var7;
               }

               this.aClass3_Sub24_Sub3_3527.aClass61_3489.method1215(true, var7);
               this.aClass3_Sub22ArrayArray3512[var2][var4] = var7;
            }
         }
      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "va.U(" + var1 + ',' + var2 + ',' + 71 + ',' + var4 + ')');
      }
   }

   static final boolean method487(int var0, byte var1) {
      try {
         if(var1 != -85) {
            anInt3507 = 56;
         }

         return 97 <= var0 && var0 <= 122 || var0 >= 65 && var0 <= 90;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "va.EB(" + var0 + ',' + var1 + ')');
      }
   }

   private final void method488(int var2) {
      try {
         int var3 = 240 & var2;
         if((byte) 56 == 56) {
            int var4;
            int var5;
            int var6;
            if(var3 == 128) {
               var4 = 15 & var2;
               var6 = (8353073 & var2) >> 1531488560;
               var5 = (32634 & var2) >> 438178376;
               this.method493((byte)-95, var5, var6, var4);
            } else if(144 == var3) {
               var5 = 127 & var2 >> -173494616;
               var4 = var2 & 15;
               var6 = 127 & var2 >> 269474800;
               if(var6 > 0) {
                  this.method486(var6, var4, var5);
               } else {
                  this.method493((byte)-122, var5, 64, var4);
               }

            } else if(160 == var3) {
               var4 = var2 & 15;
               var5 = 127 & var2 >> -1419555256;
               var6 = 127 & var2 >> 1257903824;
               this.method495(var6, var5, (byte) 56 ^ 17363, var4);
            } else if(var3 == 176) {
               var5 = (32630 & var2) >> -966747416;
               var4 = var2 & 15;
               var6 = (8388489 & var2) >> -129860304;
               if(var5 == 0) {
                  this.anIntArray3506[var4] = (var6 << 613810062) + Class3_Sub28_Sub15.method633(-2080769, this.anIntArray3506[var4]);
               }

               if(var5 == 32) {
                  this.anIntArray3506[var4] = Class3_Sub28_Sub15.method633(-16257, this.anIntArray3506[var4]) + (var6 << 2038805095);
               }

               if(var5 == 1) {
                  this.anIntArray3502[var4] = (var6 << -958045273) + Class3_Sub28_Sub15.method633(this.anIntArray3502[var4], -16257);
               }

               if(33 == var5) {
                  this.anIntArray3502[var4] = Class3_Sub28_Sub15.method633(-128, this.anIntArray3502[var4]) - -var6;
               }

               if(var5 == 5) {
                  this.anIntArray3510[var4] = Class3_Sub28_Sub15.method633(this.anIntArray3510[var4], -16257) + (var6 << -219076089);
               }

               if(var5 == 37) {
                  this.anIntArray3510[var4] = var6 + Class3_Sub28_Sub15.method633(this.anIntArray3510[var4], -128);
               }

               if(var5 == 7) {
                  this.anIntArray3497[var4] = (var6 << -512659513) + Class3_Sub28_Sub15.method633(this.anIntArray3497[var4], -16257);
               }

               if(var5 == 39) {
                  this.anIntArray3497[var4] = Class3_Sub28_Sub15.method633(this.anIntArray3497[var4], -128) + var6;
               }

               if(var5 == 10) {
                  this.anIntArray3498[var4] = Class3_Sub28_Sub15.method633(-16257, this.anIntArray3498[var4]) - -(var6 << 1481099367);
               }

               if(var5 == 42) {
                  this.anIntArray3498[var4] = var6 + Class3_Sub28_Sub15.method633(-128, this.anIntArray3498[var4]);
               }

               if(var5 == 11) {
                  this.anIntArray3514[var4] = Class3_Sub28_Sub15.method633(this.anIntArray3514[var4], -16257) + (var6 << 1316353479);
               }

               if(var5 == 43) {
                  this.anIntArray3514[var4] = var6 + Class3_Sub28_Sub15.method633(-128, this.anIntArray3514[var4]);
               }

               if(var5 == 64) {
                  if(64 <= var6) {
                     this.anIntArray3518[var4] = Class3_Sub13_Sub29.bitwiseOr(this.anIntArray3518[var4], 1);
                  } else {
                     this.anIntArray3518[var4] = Class3_Sub28_Sub15.method633(this.anIntArray3518[var4], -2);
                  }
               }

               if(var5 == 65) {
                  if(var6 < 64) {
                     this.method502(var4, (byte) 56 ^ -57);
                     this.anIntArray3518[var4] = Class3_Sub28_Sub15.method633(this.anIntArray3518[var4], -3);
                  } else {
                     this.anIntArray3518[var4] = Class3_Sub13_Sub29.bitwiseOr(this.anIntArray3518[var4], 2);
                  }
               }

               if(var5 == 99) {
                  this.anIntArray3500[var4] = (var6 << -789066041) + Class3_Sub28_Sub15.method633(this.anIntArray3500[var4], 127);
               }

               if(var5 == 98) {
                  this.anIntArray3500[var4] = Class3_Sub28_Sub15.method633(this.anIntArray3500[var4], 16256) - -var6;
               }

               if(var5 == 101) {
                  this.anIntArray3500[var4] = (var6 << 783662759) + Class3_Sub28_Sub15.method633(127, this.anIntArray3500[var4]) + 16384;
               }

               if(var5 == 100) {
                  this.anIntArray3500[var4] = var6 + Class3_Sub28_Sub15.method633(16256, this.anIntArray3500[var4]) + 16384;
               }

               if(var5 == 120) {
                  this.method481((byte)-50, var4);
               }

               if(var5 == 121) {
                  this.method480(8388489, var4);
               }

               if(123 == var5) {
                  this.method489(-32323, var4);
               }

               int var7;
               if(6 == var5) {
                  var7 = this.anIntArray3500[var4];
                  if(var7 == 16384) {
                     this.anIntArray3504[var4] = Class3_Sub28_Sub15.method633(this.anIntArray3504[var4], -16257) + (var6 << -1848595929);
                  }
               }

               if(var5 == 38) {
                  var7 = this.anIntArray3500[var4];
                  if(var7 == 16384) {
                     this.anIntArray3504[var4] = Class3_Sub28_Sub15.method633(this.anIntArray3504[var4], -128) + var6;
                  }
               }

               if(var5 == 16) {
                  this.anIntArray3519[var4] = Class3_Sub28_Sub15.method633(this.anIntArray3519[var4], -16257) - -(var6 << 1767361671);
               }

               if(var5 == 48) {
                  this.anIntArray3519[var4] = Class3_Sub28_Sub15.method633(this.anIntArray3519[var4], -128) - -var6;
               }

               if(var5 == 81) {
                  if(var6 >= 64) {
                     this.anIntArray3518[var4] = Class3_Sub13_Sub29.bitwiseOr(this.anIntArray3518[var4], 4);
                  } else {
                     this.method497(var4, -102);
                     this.anIntArray3518[var4] = Class3_Sub28_Sub15.method633(this.anIntArray3518[var4], -5);
                  }
               }

               if(var5 == 17) {
                  this.method482((byte)-117, var4, (var6 << -919548985) + (this.anIntArray3520[var4] & -16257));
               }

               if(var5 == 49) {
                  this.method482((byte)-61, var4, (this.anIntArray3520[var4] & -128) + var6);
               }

            } else {
               if(var3 == 192) {
                  var5 = var2 >> -1987010456 & 127;
                  var4 = 15 & var2;
                  this.method484(this.anIntArray3506[var4] - -var5, var4);
               } else if(var3 == 208) {
                  var4 = 15 & var2;
                  var5 = (var2 & 32549) >> 42068712;
                  this.method478(var4, false, var5);
               } else if(var3 == 224) {
                  var4 = 15 & var2;
                  var5 = (var2 >> 2059852361 & 16256) + ((32702 & var2) >> 1459602440);
                  this.method476(var4, var5);
               } else {
                  var3 = var2 & 255;
                  if(255 == var3) {
                     this.method500(true, (byte)-40);
                  }
               }
            }
         }
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "va.W(" + (byte) 56 + ',' + var2 + ')');
      }
   }

   private final void method489(int var1, int var2) {
      try {
         for(Class3_Sub22 var3 = (Class3_Sub22)this.aClass3_Sub24_Sub3_3527.aClass61_3489.method1222(); var3 != null; var3 = (Class3_Sub22)this.aClass3_Sub24_Sub3_3527.aClass61_3489.method1221()) {
            if((var2 < 0 || var3.anInt2514 == var2) && var3.anInt2506 < 0) {
               this.aClass3_Sub22ArrayArray3512[var3.anInt2514][var3.anInt2520] = null;
               var3.anInt2506 = 0;
            }
         }

         if(var1 != -32323) {
            this.anInt3525 = -99;
         }

      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "va.AA(" + var1 + ',' + var2 + ')');
      }
   }

   final synchronized void method490(boolean var1, Class3_Sub27 var2) {
      try {
         this.method475(var1, var2, true);
         if(17774 != 17774) {
            this.method413((int[])null, -32, -26);
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "va.TA(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + 17774 + ')');
      }
   }

   public static void method491(byte var0) {
      try {
         aBooleanArray3503 = null;
         //int var1 = 21 / ((var0 - -51) / 62);
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "va.C(" + var0 + ')');
      }
   }

   final synchronized void method413(int[] var1, int var2, int var3) {
      try {
         if(this.aClass78_3505.method1373()) {
            int var4 = this.aClass78_3505.anInt1116 * this.anInt3511 / Class21.anInt443;

            while(true) {
               long var5 = this.aLong3526 - -((long)var3 * (long)var4);
               if(this.aLong3523 + -var5 < 0) {
                  int var7 = (int)((-1L + this.aLong3523 - this.aLong3526 + (long)var4) / (long)var4);
                  this.aLong3526 += (long)var4 * (long)var7;
                  this.aClass3_Sub24_Sub3_3527.method413(var1, var2, var7);
                  var3 -= var7;
                  var2 += var7;
                  this.method494(100);
                  if(this.aClass78_3505.method1373()) {
                     continue;
                  }
                  break;
               }

               this.aLong3526 = var5;
               break;
            }
         }

         this.aClass3_Sub24_Sub3_3527.method413(var1, var2, var3);
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "va.E(" + (var1 != null?"{...}":"null") + ',' + var2 + ',' + var3 + ')');
      }
   }

   final boolean method492(int var1, int var2, Class3_Sub22 var3, int[] var5) {
      try {
         var3.anInt2512 = Class21.anInt443 / 100;
         if(var3.anInt2506 >= 0 && (var3.aClass3_Sub24_Sub1_2507 == null || var3.aClass3_Sub24_Sub1_2507.method444())) {
            var3.method401();
            var3.method86(-1024);
            if(var3.anInt2517 > 0 && var3 == this.aClass3_Sub22ArrayArray3513[var3.anInt2514][var3.anInt2517]) {
               this.aClass3_Sub22ArrayArray3513[var3.anInt2514][var3.anInt2517] = null;
            }

            return true;
         } else {
            int var6 = var3.anInt2502;
            if((byte) 14 != 14) {
               this.anIntArray3497 = (int[])null;
            }

            if(var6 > 0) {
               var6 -= (int)(0.5D + Math.pow(2.0D, (double)this.anIntArray3510[var3.anInt2514] * 4.921259842519685E-4D) * 16.0D);
               if(var6 < 0) {
                  var6 = 0;
               }

               var3.anInt2502 = var6;
            }

            var3.aClass3_Sub24_Sub1_2507.method443(this.method498(var3));
            Class166 var7 = var3.aClass166_2504;
            var3.anInt2508 += var7.anInt2077;
            ++var3.anInt2515;
            double var9 = (double)((var3.anInt2520 - 60 << 1988745416) + (var3.anInt2502 * var3.anInt2522 >> -874059956)) * 5.086263020833333E-6D;
            boolean var8 = false;
            if(var7.anInt2078 > 0) {
               if(var7.anInt2063 > 0) {
                  var3.anInt2523 += (int)(Math.pow(2.0D, var9 * (double)var7.anInt2063) * 128.0D + 0.5D);
               } else {
                  var3.anInt2523 += 128;
               }

               if(var3.anInt2523 * var7.anInt2078 >= 819200) {
                  var8 = true;
               }
            }

            if(var7.aByteArray2064 != null) {
               if(var7.anInt2067 <= 0) {
                  var3.anInt2511 += 128;
               } else {
                  var3.anInt2511 += (int)(0.5D + Math.pow(2.0D, (double)var7.anInt2067 * var9) * 128.0D);
               }

               while(var7.aByteArray2064.length - 2 > var3.anInt2501 && var3.anInt2511 > ('\uff00' & var7.aByteArray2064[var3.anInt2501 - -2] << 1379060744)) {
                  var3.anInt2501 += 2;
               }

               if(var3.anInt2501 == -2 + var7.aByteArray2064.length && var7.aByteArray2064[1 + var3.anInt2501] == 0) {
                  var8 = true;
               }
            }

            if(var3.anInt2506 >= 0 && null != var7.aByteArray2076 && (1 & this.anIntArray3518[var3.anInt2514]) == 0 && (0 > var3.anInt2517 || this.aClass3_Sub22ArrayArray3513[var3.anInt2514][var3.anInt2517] != var3)) {
               if(var7.anInt2071 > 0) {
                  var3.anInt2506 += (int)(Math.pow(2.0D, (double)var7.anInt2071 * var9) * 128.0D + 0.5D);
               } else {
                  var3.anInt2506 += 128;
               }

               while(var3.anInt2519 < -2 + var7.aByteArray2076.length && var3.anInt2506 > (255 & var7.aByteArray2076[2 + var3.anInt2519]) << -1574552024) {
                  var3.anInt2519 += 2;
               }

               if(-2 + var7.aByteArray2076.length == var3.anInt2519) {
                  var8 = true;
               }
            }

            if(var8) {
               var3.aClass3_Sub24_Sub1_2507.method417(var3.anInt2512);
               if(null == var5) {
                  var3.aClass3_Sub24_Sub1_2507.method415(var1);
               } else {
                  var3.aClass3_Sub24_Sub1_2507.method413(var5, var2, var1);
               }

               if(var3.aClass3_Sub24_Sub1_2507.method445()) {
                  this.aClass3_Sub24_Sub3_3527.aClass3_Sub24_Sub2_3495.method457(var3.aClass3_Sub24_Sub1_2507);
               }

               var3.method401();
               if(var3.anInt2506 >= 0) {
                  var3.method86((byte) 14 + -1038);
                  if(var3.anInt2517 > 0 && this.aClass3_Sub22ArrayArray3513[var3.anInt2514][var3.anInt2517] == var3) {
                     this.aClass3_Sub22ArrayArray3513[var3.anInt2514][var3.anInt2517] = null;
                  }
               }

               return true;
            } else {
               var3.aClass3_Sub24_Sub1_2507.method450(var3.anInt2512, this.method508(var3), this.method496(var3));
               return false;
            }
         }
      } catch (RuntimeException var11) {
         throw Class44.clientError(var11, "va.BA(" + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + (byte) 14 + ',' + (var5 != null?"{...}":"null") + ')');
      }
   }

   final synchronized Class3_Sub24 method411() {
      try {
         return this.aClass3_Sub24_Sub3_3527;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "va.Q()");
      }
   }

   private final void method493(byte var1, int var2, int var3, int var4) {
      try {
         Class3_Sub22 var5 = this.aClass3_Sub22ArrayArray3512[var4][var2];
         if(null != var5) {
            if(var1 > -92) {
               this.aClass3_Sub24_Sub3_3527 = (Class3_Sub24_Sub3)null;
            }

            this.aClass3_Sub22ArrayArray3512[var4][var2] = null;
            if((2 & this.anIntArray3518[var4]) == 0) {
               var5.anInt2506 = 0;
            } else {
               for(Class3_Sub22 var6 = (Class3_Sub22)this.aClass3_Sub24_Sub3_3527.aClass61_3489.method1222(); null != var6; var6 = (Class3_Sub22)this.aClass3_Sub24_Sub3_3527.aClass61_3489.method1221()) {
                  if(var5.anInt2514 == var6.anInt2514 && var6.anInt2506 < 0 && var6 != var5) {
                     var5.anInt2506 = 0;
                     break;
                  }
               }
            }

         }
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "va.CB(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
      }
   }

   private final void method494(int var1) {
      try {
         int var2 = this.anInt3525;
         int var3 = this.anInt3524;
         if(var1 <= 54) {
            this.method505((byte)124);
         }

         long var4 = this.aLong3523;
         if(this.aClass3_Sub27_3528 != null && this.anInt3529 == var3) {
            this.method475(this.aBoolean3522, this.aClass3_Sub27_3528, this.aBoolean3530);
            this.method494(71);
         } else {
            while(this.anInt3524 == var3) {
               while(var3 == this.aClass78_3505.anIntArray1114[var2]) {
                  this.aClass78_3505.method1376(var2);
                  int var6 = this.aClass78_3505.method1375(var2);
                  if(1 == var6) {
                     this.aClass78_3505.method1384();
                     this.aClass78_3505.method1381(var2);
                     if(this.aClass78_3505.method1371()) {
                        if(this.aClass3_Sub27_3528 != null) {
                           this.method490(this.aBoolean3522, this.aClass3_Sub27_3528);
                           this.method494(126);
                           return;
                        }

                        if(!this.aBoolean3522 || var3 == 0) {
                           this.method500(true, (byte)-40);
                           this.aClass78_3505.method1383();
                           return;
                        }

                        this.aClass78_3505.method1372(var4);
                     }
                     break;
                  }

                  if((var6 & 128) != 0) {
                     this.method488(var6);
                  }

                  this.aClass78_3505.method1377(var2);
                  this.aClass78_3505.method1381(var2);
               }

               var2 = this.aClass78_3505.method1382();
               var3 = this.aClass78_3505.anIntArray1114[var2];
               var4 = this.aClass78_3505.method1370(var3);
            }

            this.anInt3525 = var2;
            this.aLong3523 = var4;
            this.anInt3524 = var3;
            if(this.aClass3_Sub27_3528 != null && var3 > this.anInt3529) {
               this.anInt3525 = -1;
               this.anInt3524 = this.anInt3529;
               this.aLong3523 = this.aClass78_3505.method1370(this.anInt3524);
            }

         }
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "va.GB(" + var1 + ')');
      }
   }

   private final void method495(int var1, int var2, int var3, int var4) {
      try {
         if(var3 != 17387) {
            this.aClass3_Sub24_Sub3_3527 = (Class3_Sub24_Sub3)null;
         }

      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "va.GA(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
      }
   }

   private final int method496(Class3_Sub22 var2) {
      try {
         int var3 = this.anIntArray3498[var2.anInt2514];
         if(0 != 0) {
            this.anIntArray3498 = (int[])null;
         }

         return var3 < 8192 ?32 + var2.anInt2503 * var3 >> 746377926:16384 - ((128 + -var2.anInt2503) * (16384 + -var3) + 32 >> -1544786522);
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "va.BB(" + 0 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   final synchronized void method415(int var1) {
      try {
         if(this.aClass78_3505.method1373()) {
            int var2 = this.aClass78_3505.anInt1116 * this.anInt3511 / Class21.anInt443;

            while(true) {
               long var3 = this.aLong3526 - -((long)var1 * (long)var2);
               if(this.aLong3523 + -var3 < 0) {
                  int var5 = (int)(((long)var2 + (-this.aLong3526 + this.aLong3523 - 1L)) / (long)var2);
                  var1 -= var5;
                  this.aLong3526 += (long)var5 * (long)var2;
                  this.aClass3_Sub24_Sub3_3527.method415(var5);
                  this.method494(64);
                  if(this.aClass78_3505.method1373()) {
                     continue;
                  }
                  break;
               }

               this.aLong3526 = var3;
               break;
            }
         }

         this.aClass3_Sub24_Sub3_3527.method415(var1);
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "va.R(" + var1 + ')');
      }
   }

   private final void method497(int var1, int var2) {
      try {
         int var3 = -75 % ((-44 - var2) / 45);
         if(0 != (4 & this.anIntArray3518[var1])) {
            for(Class3_Sub22 var4 = (Class3_Sub22)this.aClass3_Sub24_Sub3_3527.aClass61_3489.method1222(); null != var4; var4 = (Class3_Sub22)this.aClass3_Sub24_Sub3_3527.aClass61_3489.method1221()) {
               if(var1 == var4.anInt2514) {
                  var4.anInt2516 = 0;
               }
            }
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "va.A(" + var1 + ',' + var2 + ')');
      }
   }

   private final int method498(Class3_Sub22 var2) {
      try {
         Class166 var4 = var2.aClass166_2504;
         int var3 = (var2.anInt2522 * var2.anInt2502 >> -1563888596) + var2.anInt2510;
         var3 += this.anIntArray3504[var2.anInt2514] * (-8192 + this.anIntArray3499[var2.anInt2514]) >> 1103598476;
         int var5;
         if(var4.anInt2077 > 0 && (var4.anInt2066 > 0 || this.anIntArray3502[var2.anInt2514] > 0)) {
            var5 = var4.anInt2066 << 1313257762;
            int var6 = var4.anInt2069 << 749639265;
            if(var2.anInt2515 < var6) {
               var5 = var2.anInt2515 * var5 / var6;
            }

            var5 += this.anIntArray3502[var2.anInt2514] >> 519373607;
            double var7 = Math.sin(0.01227184630308513D * (double)(511 & var2.anInt2508));
            var3 += (int)((double)var5 * var7);
         }

         var5 = (int)(0.5D + (double)(256 * var2.aClass3_Sub12_Sub1_2509.anInt3034) * Math.pow(2.0D, (double)var3 * 3.255208333333333E-4D) / (double)Class21.anInt443);
         if((byte) 85 != 85) {
            this.method414();
         }

         return var5 >= 1?var5:1;
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "va.OA(" + (byte) 85 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   final int method499(boolean var1) {
      try {
         if(var1) {
            this.method500(true, (byte)91);
         }

         return this.anInt3521;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "va.IA(" + var1 + ')');
      }
   }

   private final void method500(boolean var1, byte var2) {
      try {
         if(var1) {
            this.method481((byte)91, -1);
         } else {
            this.method489(var2 + -32283, -1);
         }

         this.method480(8388489, -1);
         if(var2 == -40) {
            int var3;
            for(var3 = 0; 16 > var3; ++var3) {
               this.anIntArray3515[var3] = this.anIntArray3501[var3];
            }

            for(var3 = 0; var3 < 16; ++var3) {
               this.anIntArray3506[var3] = Class3_Sub28_Sub15.method633(-128, this.anIntArray3501[var3]);
            }

         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "va.F(" + var1 + ',' + var2 + ')');
      }
   }

   final void method501(Class3_Sub22 var1, boolean var2, byte var3) {
      try {
         int var4 = var1.aClass3_Sub12_Sub1_2509.aByteArray3030.length;
         int var5;
         if(var2 && var1.aClass3_Sub12_Sub1_2509.aBoolean3031) {
            int var6 = var4 + (var4 - var1.aClass3_Sub12_Sub1_2509.anInt3033);
            var4 <<= 8;
            var5 = (int)((long)var6 * (long)this.anIntArray3519[var1.anInt2514] >> -1659850106);
            if(var4 <= var5) {
               var1.aClass3_Sub24_Sub1_2507.method442();
               var5 = -1 + (var4 - -var4) + -var5;
            }
         } else {
            var5 = (int)((long)var4 * (long)this.anIntArray3519[var1.anInt2514] >> -1115007738);
         }

         var1.aClass3_Sub24_Sub1_2507.method434(var5);
         if(var3 >= -70) {
            this.aLong3523 = 47L;
         }

      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "va.CA(" + (var1 != null?"{...}":"null") + ',' + var2 + ',' + var3 + ')');
      }
   }

   private final void method502(int var1, int var2) {
      try {
         if(var2 != ~(this.anIntArray3518[var1] & 2)) {
            for(Class3_Sub22 var3 = (Class3_Sub22)this.aClass3_Sub24_Sub3_3527.aClass61_3489.method1222(); var3 != null; var3 = (Class3_Sub22)this.aClass3_Sub24_Sub3_3527.aClass61_3489.method1221()) {
               if(var3.anInt2514 == var1 && this.aClass3_Sub22ArrayArray3512[var1][var3.anInt2520] == null && var3.anInt2506 < 0) {
                  var3.anInt2506 = 0;
               }
            }
         }

      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "va.T(" + var1 + ',' + var2 + ')');
      }
   }

   static final void method503(int var1) {
      try {
         Class8.anInt101 = var1;
         if((byte) -53 == -53) {
            Class3_Sub28_Sub8.anInt3611 = 20;
            Class3_Sub28_Sub16.anInt3704 = 3;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "va.FA(" + (byte) -53 + ',' + var1 + ')');
      }
   }

   final boolean method504(Class3_Sub22 var1, int var2) {
      try {
         int var3 = -114 % ((76 - var2) / 45);
         if(var1.aClass3_Sub24_Sub1_2507 == null) {
            if(var1.anInt2506 >= 0) {
               var1.method86(-1024);
               if(var1.anInt2517 > 0 && this.aClass3_Sub22ArrayArray3513[var1.anInt2514][var1.anInt2517] == var1) {
                  this.aClass3_Sub22ArrayArray3513[var1.anInt2514][var1.anInt2517] = null;
               }
            }

            return true;
         } else {
            return false;
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "va.WA(" + (var1 != null?"{...}":"null") + ',' + var2 + ')');
      }
   }

   final synchronized void method505(byte var1) {
      try {
         this.method507(true);
         if(var1 > -125) {
            this.anIntArray3520 = (int[])null;
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "va.RA(" + var1 + ')');
      }
   }

   final synchronized void method506(int var2) {
      try {
         this.anInt3521 = var2;
         if(128 != 128) {
            this.method480(-80, 93);
         }

      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "va.LA(" + 128 + ',' + var2 + ')');
      }
   }

   private final synchronized void method507(boolean var1) {
      try {
         this.aClass78_3505.method1383();
         if((byte) -68 == -68) {
            this.aClass3_Sub27_3528 = null;
            this.method500(var1, (byte)-40);
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "va.MA(" + var1 + ',' + (byte) -68 + ')');
      }
   }

   private final int method508(Class3_Sub22 var2) {
      try {
         if((byte) 36 != 36) {
            this.anIntArray3501 = (int[])null;
         }

         if(this.anIntArray3516[var2.anInt2514] == 0) {
            return 0;
         } else {
            Class166 var3 = var2.aClass166_2504;
            int var4 = 4096 + this.anIntArray3497[var2.anInt2514] * this.anIntArray3514[var2.anInt2514] >> 435146989;
            var4 = 16384 + var4 * var4 >> 1690662127;
            var4 = 16384 + var2.anInt2513 * var4 >> -1161334033;
            var4 = 128 + var4 * this.anInt3521 >> -1571301048;
            var4 = this.anIntArray3516[var2.anInt2514] * var4 + 128 >> 798756328;
            if(0 < var3.anInt2078) {
               var4 = (int)(0.5D + Math.pow(0.5D, (double)var2.anInt2523 * 1.953125E-5D * (double)var3.anInt2078) * (double)var4);
            }

            int var5;
            int var6;
            int var7;
            int var8;
            if(null != var3.aByteArray2064) {
               var5 = var2.anInt2511;
               var6 = var3.aByteArray2064[1 + var2.anInt2501];
               if(var3.aByteArray2064.length - 2 > var2.anInt2501) {
                  var8 = (var3.aByteArray2064[2 + var2.anInt2501] & 255) << 292332552;
                  var7 = '\uff00' & var3.aByteArray2064[var2.anInt2501] << -166317176;
                  var6 += (var3.aByteArray2064[3 + var2.anInt2501] + -var6) * (var5 - var7) / (var8 + -var7);
               }

               var4 = 32 + var6 * var4 >> 967764454;
            }

            if(var2.anInt2506 > 0 && null != var3.aByteArray2076) {
               var5 = var2.anInt2506;
               var6 = var3.aByteArray2076[1 + var2.anInt2519];
               if(-2 + var3.aByteArray2076.length > var2.anInt2519) {
                  var7 = '\uff00' & var3.aByteArray2076[var2.anInt2519] << -191601976;
                  var8 = (var3.aByteArray2076[var2.anInt2519 + 2] & 255) << -371546200;
                  var6 += (var5 - var7) * (-var6 + var3.aByteArray2076[3 + var2.anInt2519]) / (-var7 + var8);
               }

               var4 = 32 + var4 * var6 >> -2036202458;
            }

            return var4;
         }
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "va.UA(" + (byte) 36 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   final synchronized Class3_Sub24 method414() {
      try {
         return null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "va.B()");
      }
   }

   public Class3_Sub24_Sub4() {
      try {
         this.aClass130_3508 = new Class130(128);
         this.method483();
         this.method500(true, (byte)-40);
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "va.<init>()");
      }
   }

}
