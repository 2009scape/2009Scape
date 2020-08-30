package org.runite.jagex;

final class Class3_Sub19 extends Class3 {

    static int[] anIntArray3693 = new int[1000];
    byte aByte2472;
   RSString aClass94_2473;
   static CacheIndex aClass153_2474;
   static int anInt2475 = 0;
   RSString aClass94_2476;
   int anInt2478;


   static Class75_Sub2 method384(RSByteBuffer var0) {
      try {

         return new Class75_Sub2(var0.getShort((byte)38), var0.getShort((byte)51), var0.getShort((byte)66), var0.getShort((byte)68), var0.getTriByte((byte)86), var0.getTriByte((byte)86), var0.getByteB());
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "kl.C(" + (var0 != null?"{...}":"null") + ',' + (byte) 80 + ')');
      }
   }

   static boolean method385(int var1) {
      try {
         byte var3 = 0;
         int var5;
         int var6;
         int var7;
         int var8;
         if(null == Class27.aClass3_Sub28_Sub16_Sub2_518) {
            if(HDToolKit.highDetail || null == Class49.aClass3_Sub28_Sub16_812) {
               Class27.aClass3_Sub28_Sub16_Sub2_518 = new Class3_Sub28_Sub16_Sub2(512, 512);
            } else {
               Class27.aClass3_Sub28_Sub16_Sub2_518 = (Class3_Sub28_Sub16_Sub2)Class49.aClass3_Sub28_Sub16_812;
            }

            int[] var4 = Class27.aClass3_Sub28_Sub16_Sub2_518.anIntArray4081;
            var5 = var4.length;

            for(var6 = 0; var5 > var6; ++var6) {
               var4[var6] = 1;
            }

            for(var6 = var3 + 1; -1 + -var3 + 104 > var6; ++var6) {
               var7 = 4 * 512 * (var3 + 103 + -var6) + 24628;

               for(var8 = 1 + 0; -0 + 104 + -1 > var8; ++var8) {
                  if((Class9.aByteArrayArrayArray113[var1][var8][var6] & 24) == 0) {
                     IOHandler.method1469(var4, var7, 512, var1, var8, var6);
                  }

                  if(var1 < 3 && 0 != (Class9.aByteArrayArrayArray113[1 + var1][var8][var6] & 8)) {
                     IOHandler.method1469(var4, var7, 512, var1 + 1, var8, var6);
                  }

                  var7 += 4;
               }
            }

            MouseListeningClass.anInt1924 = 0;

            for(var6 = 0; 104 > var6; ++var6) {
               for(var7 = 0; 104 > var7; ++var7) {
                  long var20 = Class3_Sub2.method104(WorldListCountry.localPlane, var6 + 0, var7 - -var3);
                  if(var20 != 0) {
                     ObjectDefinition var10 = Class162.getObjectDefinition((int)(var20 >>> 32) & Integer.MAX_VALUE);
                     int var11 = var10.MapIcon;
                     int var12;
                     if(null != var10.ChildrenIds) {
                        for(var12 = 0; var12 < var10.ChildrenIds.length; ++var12) {
                           if(-1 != var10.ChildrenIds[var12]) {
                              ObjectDefinition var13 = Class162.getObjectDefinition(var10.ChildrenIds[var12]);
                              if(0 <= var13.MapIcon) {
                                 var11 = var13.MapIcon;
                                 break;
                              }
                           }
                        }
                     }

                     if(var11 >= 0) {
                        int var21 = var3 + var7;
                        var12 = 0 + var6;
                        if(22 != var11 && var11 != 29 && 34 != var11 && var11 != 36 && 46 != var11 && var11 != 47 && var11 != 48) {
                           int[][] var14 = Class86.aClass91Array1182[WorldListCountry.localPlane].anIntArrayArray1304;

                           for(int var15 = 0; var15 < 10; ++var15) {
                              int var16 = (int)(Math.random() * 4.0D);
                              if(var16 == 0 && var12 > 0 && var12 > var6 - 3 && (var14[var12 - 1][var21] & 19661064) == 0) {
                                 --var12;
                              }

                              if(var16 == 1 && var12 < 103 && var12 < 3 + var6 && (var14[var12 - -1][var21] & 19661184) == 0) {
                                 ++var12;
                              }

                              if(2 == var16 && var21 > 0 && var21 > -3 + var7 && 0 == (var14[var12][-1 + var21] & 19661058)) {
                                 --var21;
                              }

                              if(3 == var16 && var21 < 103 && var7 - -3 > var21 && 0 == (19661088 & var14[var12][1 + var21])) {
                                 ++var21;
                              }
                           }
                        }

                        anIntArray3693[MouseListeningClass.anInt1924] = var10.objectId;
                        Class84.anIntArray1163[MouseListeningClass.anInt1924] = -0 + var12;
                        Class3_Sub28_Sub7_Sub1.anIntArray4050[MouseListeningClass.anInt1924] = var21 + -var3;
                        ++MouseListeningClass.anInt1924;
                     }
                  }
               }
            }
         }

         Class27.aClass3_Sub28_Sub16_Sub2_518.method658();
         int var18 = (238 + (int)(20.0D * Math.random()) - 10 << 8) + (-10 + (int)(Math.random() * 20.0D) + 238 << 16) + (228 - -((int)(20.0D * Math.random())));
         var5 = (int)(Math.random() * 20.0D) + 238 + -10 << 16;

         for(var6 = 1; var6 < 103; ++var6) {
            for(var7 = 1; 103 > var7; ++var7) {
               if(0 == (Class9.aByteArrayArrayArray113[var1][var7 + 0][var6 - -var3] & 24) && !RSString.method1529(var3, var7, var18, var6, var5, var1)) {
                  if(HDToolKit.highDetail) {
                     Class74.anIntArray1100 = null;
                  } else {
                     Class164_Sub1.aClass158_3009.method2182();
                  }

                  return false;
               }

               if(var1 < 3 && (Class9.aByteArrayArrayArray113[1 + var1][var7 + 0][var6 + var3] & 8) != 0 && !RSString.method1529(var3, var7, var18, var6, var5, 1 + var1)) {
                  if(HDToolKit.highDetail) {
                     Class74.anIntArray1100 = null;
                  } else {
                     Class164_Sub1.aClass158_3009.method2182();
                  }

                  return false;
               }
            }
         }

         if(HDToolKit.highDetail) {
            int[] var19 = Class27.aClass3_Sub28_Sub16_Sub2_518.anIntArray4081;
            var7 = var19.length;

            for(var8 = 0; var7 > var8; ++var8) {
               if(var19[var8] == 0) {
                  var19[var8] = 1;
               }
            }

            Class49.aClass3_Sub28_Sub16_812 = new Class3_Sub28_Sub16_Sub1(Class27.aClass3_Sub28_Sub16_Sub2_518);
         } else {
            Class49.aClass3_Sub28_Sub16_812 = Class27.aClass3_Sub28_Sub16_Sub2_518;
         }

         if(HDToolKit.highDetail) {
            Class74.anIntArray1100 = null;
         } else {
            Class164_Sub1.aClass158_3009.method2182();
         }

         Class27.aClass3_Sub28_Sub16_Sub2_518 = null;
         return true;
      } catch (RuntimeException var17) {
         throw Class44.clientError(var17, "kl.B(" + 0 + ',' + var1 + ')');
      }
   }

   static void method386() {
      try {
         Class3_Sub31.aClass93_2604.method1522(-128, 5);
         Class27.aClass93_511.method1522(-126, 5);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "kl.D(" + 5 + ',' + -123 + ')');
      }
   }

   public static void method387(int var0) {
      try {
         //int var1 = 41 % ((var0 - -70) / 32);
         aClass153_2474 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "kl.A(" + var0 + ')');
      }
   }

}
