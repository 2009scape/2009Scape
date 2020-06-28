package org.runite.jagex;

final class Class3_Sub28_Sub11 extends Node {

   private Class130 aClass130_3636;
   static RSString aClass94_3637 = RSString.createRSString(")4p=");
   private static RSString aClass94_3638 = RSString.createRSString("Loading fonts )2 ");
   private static RSString aClass94_3639 = RSString.createRSString(" is already on your friend list)3");
   static int anInt3640;
   static boolean aBoolean3641 = false;
   static int anInt3642 = 0;
   static RSString aClass94_3643 = aClass94_3638;
   static int anInt3644 = 0;
   static RSString aClass94_3645 = aClass94_3639;


   static final int method599(int var0, CacheIndex var1) {
      try {
         int var2 = 0;
         if(var0 != -20916) {
            return -88;
         } else {
            if(var1.method2144(0, Class154.anInt1966)) {
               ++var2;
            }

            if(var1.method2144(0, Class79.anInt1124)) {
               ++var2;
            }

            return var2;
         }
      } catch (RuntimeException var3) {
         throw Class44.method1067(var3, "lk.F(" + var0 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   final int method600(int var1, int var2, byte var3) {
      try {
         if(this.aClass130_3636 != null) {
            if(var3 != -29) {
               this.method604((RSString)null, (byte)56, 110);
            }

            Class3_Sub18 var4 = (Class3_Sub18)this.aClass130_3636.method1780((long)var1, var3 ^ -29);
            return null == var4?var2:var4.anInt2467;
         } else {
            return var2;
         }
      } catch (RuntimeException var5) {
         throw Class44.method1067(var5, "lk.Q(" + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   private final void method601(RSByteBuffer var1, int var2, byte var3) {
      try {
         if(var3 < -2) {
            if(249 == var2) {
               int var4 = var1.getByte((byte)-52);
               int var5;
               if(this.aClass130_3636 == null) {
                  var5 = Class95.method1585((byte)105, var4);
                  this.aClass130_3636 = new Class130(var5);
               }

               for(var5 = 0; var4 > var5; ++var5) {
                  boolean var6 = 1 == var1.getByte((byte)-94);
                  int var7 = var1.getTriByte((byte)95);
                  Object var8;
                  if(!var6) {
                     var8 = new Class3_Sub18(var1.getInt());
                  } else {
                     var8 = new Class3_Sub29(var1.getString());
                  }

                  this.aClass130_3636.method1779(1, (Class3)var8, (long)var7);
               }
            }

         }
      } catch (RuntimeException var9) {
         throw Class44.method1067(var9, "lk.P(" + (var1 != null?"{...}":"null") + ',' + var2 + ',' + var3 + ')');
      }
   }

   static final Class3_Sub28_Sub16 method602(int var0, int var1, byte var2, CacheIndex var3) {
      try {
    	//  System.out.println("Class3_sub28_Sub16 " + var1);
         if(Class75_Sub4.method1351(var3, var0, var1, var2 ^ 30885)) {
            if(var2 != -18) {
               method607(true);
            }

            return Class43.method1062(var2 + 103);
         } else {
            return null;
         }
      } catch (RuntimeException var5) {
         throw Class44.method1067(var5, "lk.R(" + var0 + ',' + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ')');
      }
   }

   static final void method603(int var0, int var1, int var2, RSInterface var3, boolean var4) {
      try {
         int var5 = var3.anInt168;
         int var6 = var3.anInt193;
         if(var1 != 13987) {
            method602(-115, 65, (byte)-119, (CacheIndex)null);
         }

         if(-1 != ~var3.aByte304) {
            if(~var3.aByte304 != -2) {
               if(~var3.aByte304 == -3) {
                  var3.anInt168 = var3.width * var2 >> 14;
               } else if(var3.aByte304 == 3) {
                  if(~var3.type != -3) {
                     if(var3.type == 7) {
                        var3.anInt168 = 115 * var3.width + var3.anInt285 * (-1 + var3.width);
                     }
                  } else {
                     var3.anInt168 = var3.width * 32 - -((var3.width - 1) * var3.anInt285);
                  }
               }
            } else {
               var3.anInt168 = var2 + -var3.width;
            }
         } else {
            var3.anInt168 = var3.width;
         }

         if(-1 == ~var3.aByte241) {
            var3.anInt193 = var3.height;
         } else if(var3.aByte241 == 1) {
            var3.anInt193 = -var3.height + var0;
         } else if(~var3.aByte241 == -3) {
            var3.anInt193 = var0 * var3.height >> 14;
         } else if(var3.aByte241 == 3) {
            if(~var3.type == -3) {
               var3.anInt193 = (var3.height + -1) * var3.anInt290 + var3.height * 32;
            } else if(~var3.type == -8) {
               var3.anInt193 = var3.height * 12 + (-1 + var3.height) * var3.anInt290;
            }
         }

         if(-5 == ~var3.aByte304) {
            var3.anInt168 = var3.anInt216 * var3.anInt193 / var3.anInt160;
         }

         if(var3.aByte241 == 4) {
            var3.anInt193 = var3.anInt160 * var3.anInt168 / var3.anInt216;
         }

         if(Class69.aBoolean1040 && (-1 != ~Client.method44(var3).anInt2205 || ~var3.type == -1)) {
            if(var3.anInt193 < 5 && 5 > var3.anInt168) {
               var3.anInt193 = 5;
               var3.anInt168 = 5;
            } else {
               if(~var3.anInt168 >= -1) {
                  var3.anInt168 = 5;
               }

               if(0 >= var3.anInt193) {
                  var3.anInt193 = 5;
               }
            }
         }

         if(1337 == var3.anInt189) {
            Class168.aClass11_2091 = var3;
         }

         if(var4 && null != var3.anObjectArray235 && (~var5 != ~var3.anInt168 || var3.anInt193 != var6)) {
            CS2Script var7 = new CS2Script();
            var7.arguments = var3.anObjectArray235;
            var7.aClass11_2449 = var3;
            Class110.aClass61_1471.method1215(true, var7);
         }

      } catch (RuntimeException var8) {
         throw Class44.method1067(var8, "lk.E(" + var0 + ',' + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + var4 + ')');
      }
   }

   final RSString method604(RSString var1, byte var2, int var3) {
      try {
         if(this.aClass130_3636 == null) {
            return var1;
         } else {
            Class3_Sub29 var4 = (Class3_Sub29)this.aClass130_3636.method1780((long)var3, 0);
            if(var2 != -44) {
               method607(false);
            }

            return null != var4?var4.aClass94_2586:var1;
         }
      } catch (RuntimeException var5) {
         throw Class44.method1067(var5, "lk.B(" + (var1 != null?"{...}":"null") + ',' + var2 + ',' + var3 + ')');
      }
   }

   public static void method605(int var0) {
      try {
         aClass94_3637 = null;
         aClass94_3638 = null;
         aClass94_3639 = null;
         aClass94_3643 = null;
         if(var0 != 221301966) {
            method603(-111, -64, -10, (RSInterface)null, false);
         }

         aClass94_3645 = null;
      } catch (RuntimeException var2) {
         throw Class44.method1067(var2, "lk.D(" + var0 + ')');
      }
   }

   static final void method606(int var0, Class3_Sub9 var1, int var2, int var3, int var4, int var5) {
      try {
         if(var5 > 44) {
            if(~var1.anInt2332 != 0 || var1.anIntArray2333 != null) {
               int var6 = 0;
               if(var1.anInt2321 < var0) {
                  var6 += -var1.anInt2321 + var0;
               } else if(var1.anInt2326 > var0) {
                  var6 += var1.anInt2326 - var0;
               }

               if(var1.anInt2307 >= var4) {
                  if(var4 < var1.anInt2308) {
                     var6 += -var4 + var1.anInt2308;
                  }
               } else {
                  var6 += -var1.anInt2307 + var4;
               }

               if(0 != var1.anInt2328 && ~var1.anInt2328 <= ~(var6 - 64) && 0 != Class14.anInt340 && var2 == var1.anInt2314) {
                  var6 -= 64;
                  if(var6 < 0) {
                     var6 = 0;
                  }

                  int var7 = (-var6 + var1.anInt2328) * Class14.anInt340 / var1.anInt2328;
                  if(var1.aClass3_Sub24_Sub1_2312 == null) {
                     if(-1 >= ~var1.anInt2332) {
                        Class135 var8 = Class135.method1811(Class146.aClass153_1902, var1.anInt2332, 0);
                        if(null != var8) {
                           Class3_Sub12_Sub1 var9 = var8.method1812().method151(Class27.aClass157_524);
                           Class3_Sub24_Sub1 var10 = Class3_Sub24_Sub1.method437(var9, 100, var7);
                           var10.method429(-1);
                           Class3_Sub26.aClass3_Sub24_Sub2_2563.method457(var10);
                           var1.aClass3_Sub24_Sub1_2312 = var10;
                        }
                     }
                  } else {
                     var1.aClass3_Sub24_Sub1_2312.method419(var7);
                  }

                  if(null != var1.aClass3_Sub24_Sub1_2315) {
                     var1.aClass3_Sub24_Sub1_2315.method419(var7);
                     if(!var1.aClass3_Sub24_Sub1_2315.method82(0)) {
                        var1.aClass3_Sub24_Sub1_2315 = null;
                     }
                  } else if(var1.anIntArray2333 != null && ~(var1.anInt2316 -= var3) >= -1) {
                     int var13 = (int)((double)var1.anIntArray2333.length * Math.random());
                     Class135 var14 = Class135.method1811(Class146.aClass153_1902, var1.anIntArray2333[var13], 0);
                     if(null != var14) {
                        Class3_Sub12_Sub1 var15 = var14.method1812().method151(Class27.aClass157_524);
                        Class3_Sub24_Sub1 var11 = Class3_Sub24_Sub1.method437(var15, 100, var7);
                        var11.method429(0);
                        Class3_Sub26.aClass3_Sub24_Sub2_2563.method457(var11);
                        var1.anInt2316 = (int)((double)(-var1.anInt2310 + var1.anInt2325) * Math.random()) + var1.anInt2310;
                        var1.aClass3_Sub24_Sub1_2315 = var11;
                     }
                  }

               } else {
                  if(null != var1.aClass3_Sub24_Sub1_2312) {
                     Class3_Sub26.aClass3_Sub24_Sub2_2563.method461(var1.aClass3_Sub24_Sub1_2312);
                     var1.aClass3_Sub24_Sub1_2312 = null;
                  }

                  if(var1.aClass3_Sub24_Sub1_2315 != null) {
                     Class3_Sub26.aClass3_Sub24_Sub2_2563.method461(var1.aClass3_Sub24_Sub1_2315);
                     var1.aClass3_Sub24_Sub1_2315 = null;
                  }

               }
            }
         }
      } catch (RuntimeException var12) {
         throw Class44.method1067(var12, "lk.O(" + var0 + ',' + (var1 != null?"{...}":"null") + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
      }
   }

   static final void method607(boolean var0) {
      try {
         Class140_Sub3.aClass94_2751 = Class113.aClass94_1546;
         Class3_Sub13_Sub36.aClass94_3426 = Class114.aClass94_1575;
         Class38.aClass94_662 = Class24.aClass94_463;
         Class3_Sub13_Sub12.aClass94_3142 = Class3_Sub2.aClass94_2215;
         Class143.aClass94_1879 = Class3_Sub13_Sub36.aClass94_3432;
         Class3_Sub28_Sub10_Sub2.aClass94_4071 = Class29.aClass94_560;
         Class3_Sub6.suffixWishesToTradeWithYou = Class29.aClass94_559;
         NPC.aClass94_3988 = Class38_Sub1.aClass94_2616;
         Class163_Sub2_Sub1.aClass94_4024 = Class39.aClass94_669;
         Class95.aClass94_1335 = Class3_Sub13_Sub27.aClass94_3344;
         Class12.aClass94_327 = Class3_Sub4.aClass94_2255;
         Class3_Sub1.aClass94_2210 = MouseListeningClass.aClass94_1922;
         Class140_Sub3.aClass94_2744 = Class129_Sub1.aClass94_2687;
         Class3_Sub28_Sub10_Sub1.aClass94_4058 = Class3_Sub13_Sub26.aClass94_3324;
         Class3_Sub22.aClass94_2526 = Class96.aClass94_1348;
         Class3_Sub28_Sub5.aClass94_3586 = Class3_Sub28_Sub13.aClass94_3661;
         Class123.aClass94_1653 = Class158.aClass94_2013;
         InputStream_Sub1.aClass94_43 = Class113.aClass94_1558;
         ItemDefinition.aClass94_809 = Class85.aClass94_1173;
         Class2.aClass94_62 = Class3_Sub13_Sub26.aClass94_3326;
         Class100.aClass94_1409 = Class131.aClass94_1728;
         Class3_Sub20.aClass94_2481 = Class3_Sub5.aClass94_2276;
         Class115.aClass94_1583 = Class3_Sub22.aClass94_2499;
         Class25.aClass94_485 = Class3_Sub28_Sub20.aClass94_3798;
         if(var0) {
            aBoolean3641 = false;
         }

         Class50.aClass94_825 = Class3_Sub5.aClass94_2269;
         Class3_Sub28_Sub10.aClass94_3629 = Class3_Sub24_Sub4.aClass94_3496;
         Class38.aClass94_666 = Class24.aClass94_463;
         aClass94_3643 = Class3_Sub13_Sub3.aClass94_3053;
         Class161.aClass94_2031 = Class97.aClass94_1377;
         Class128.aClass94_1688 = Class3_Sub13_Sub23.aClass94_3281;
         Class144.aClass94_1884 = Class57.aClass94_901;
         Class60.aClass94_935 = Class3_Sub13_Sub33.aClass94_3401;
         Class3_Sub13_Sub23.aClass94_3282 = Class60.aClass94_933;
         Class3_Sub13_Sub36.aClass94_3427 = Class3_Sub13_Sub28.aClass94_3355;
         Class130.aClass94_1707 = GraphicDefinition.aClass94_552;
         Class145.aClass94_1892 = NodeList.aClass94_334;
         Client.aClass94_2196 = Class43.aClass94_700;
         Class86.aClass94_1180 = Class3_Sub13_Sub34.aClass94_3408;
         Class136.aClass94_1773 = Class17.aClass94_414;
         Class27.aClass94_522 = Class104.aClass94_2170;
         Class3_Sub28_Sub15.aClass94_3691 = Class7.aClass94_2163;
         RenderAnimationDefinition.aClass94_374 = Class3_Sub28_Sub18.aClass94_3763;
         GameShell.aClass94_4 = ItemDefinition.aClass94_808;
         Class3_Sub2.aClass94_2216 = Class3_Sub10.aClass94_2340;
         Class50.aClass94_822 = Class107.aClass94_1455;
         Class3_Sub5.aClass94_2267 = Class3_Sub22.aClass94_2524;
         Class3_Sub28_Sub5.aClass94_3584 = Class3_Sub28_Sub13.aClass94_3661;
         Class131.aClass94_1722 = Class10.aClass94_150;
         Class154.aClass94_1962 = Class45.aClass94_728;
         Class3_Sub13_Sub33.aClass94_3397 = RSInterface.aClass94_297;
         Class43.aClass94_691 = Class163.aClass94_2042;
         Class24.aClass94_461 = AbstractIndexedSprite.aClass94_1466;
         Class157.aClass94_1995 = Class155.aClass94_1974;
         Class56.aClass94_891 = Class75_Sub3.aClass94_2653;
         Class3_Sub28_Sub18.aClass94_3762 = Class130.aClass94_1702;
         Class3_Sub13_Sub38.aClass94_3445 = Class3_Sub13_Sub14.aClass94_3169;
         Class3_Sub13_Sub10.aClass94_3124 = Class3_Sub10.aClass94_2336;
         Class75_Sub4.aClass94_2667 = Class3_Sub28_Sub16.aClass94_3705;
         aClass94_2576 = Class3_Sub17.aClass94_2461;
         Class3_Sub13_Sub32.aClass94_3388 = Class54.aClass94_875;
         Class3_Sub13_Sub23_Sub1.aClass94_4040 = Class3_Sub28_Sub2.aClass94_3547;
         Class154.aClass94_1959 = RSString.aClass94_2149;
         RSByteBuffer.aClass94_2597 = Class3_Sub28_Sub20.aClass94_3785;
         GraphicDefinition.aClass94_551 = Class121.aClass94_1647;
         WorldListEntry.aClass94_2624 = Class164.aClass94_2053;
         Class3_Sub28_Sub2.aClass94_3544 = Class9.aClass94_145;
         Canvas_Sub2.aClass94_36 = Class145.aClass94_1889;
         Class3_Sub13_Sub20.aClass94_3249 = Class163_Sub2.aClass94_2997;
         Class24.aClass94_462 = Class3_Sub13_Sub23.aClass94_3279;
         Class3_Sub13_Sub10.aClass94_3117 = Class3_Sub10.aClass94_2336;
         Class3_Sub28_Sub2.aClass94_3546 = RenderAnimationDefinition.aClass94_355;
         RSString.aClass94_2151 = Class75_Sub4.aClass94_2662;
         Class3_Sub28_Sub4.aClass94_3575 = Class69.aClass94_1044;
         Class163_Sub1.aClass94_2991 = Canvas_Sub1.aClass94_22;
         Class62.aClass94_957 = Class129.aClass94_1696;
         Class117.aClass94_1615 = Class3_Sub8.aClass94_2297;
         Class3_Sub13_Sub25.aClass94_3311 = Class81.aClass94_1140;
         Class106.aClass94_1445 = Class118.aClass94_1618;
         RenderAnimationDefinition.aClass94_361 = Class14.aClass94_338;
         Class3_Sub13_Sub3.aClass94_3051 = Class124.aClass94_1660;
         Class3_Sub13_Sub26.aClass94_3333 = Class3_Sub2.aClass94_2242;
         Class151_Sub1.aClass94_2961 = Class32.aClass94_591;
         aClass94_3645 = AnimationDefinition.aClass94_1863;
         Class23.aClass94_459 = Class102.aClass94_2132;
         Class3_Sub13_Sub7.aClass94_3097 = Class3_Sub13_Sub24.aClass94_3291;
         Class27.aClass94_523 = Class3_Sub21.aClass94_2496;
         Class3_Sub13_Sub14.aClass94_3167 = Class120.aClass94_1637;
         Class86.aClass94_1183 = Class3_Sub4.aClass94_2260;
         Class131.aClass94_1731 = Class3_Sub13_Sub34.aClass94_3409;
         Class27.aClass94_525 = WorldListEntry.aClass94_2628;
      } catch (RuntimeException var2) {
         throw Class44.method1067(var2, "lk.A(" + var0 + ')');
      }
   }

   final void method608(int var1, RSByteBuffer var2) {
      try {
         while(true) {
            int var3 = var2.getByte((byte)-76);
            if(0 == var3) {
               if(var1 != 5) {
                  method607(false);
               }

               return;
            }

            this.method601(var2, var3, (byte)-5);
         }
      } catch (RuntimeException var4) {
         throw Class44.method1067(var4, "lk.C(" + var1 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

}
