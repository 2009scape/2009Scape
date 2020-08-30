package org.runite.jagex;

final class Class65 {

   static Class61 aClass61_983 = new Class61();
   static int anInt984 = 0;
   static RSString aClass94_985 = RSString.createRSString("Fps:");
   static RSByteBuffer[] aClass3_Sub30Array986 = new RSByteBuffer[2048];
   static int anInt987 = 0;
   static int currentChunkX;
   static int anInt991 = -1;


   static int[] method1233(int[] var0, int var1) {
      try {
         if(null == var0) {
            return null;
         } else {
            if(var1 != 2) {
               anInt984 = 113;
            }

            int[] var2 = new int[var0.length];
            Class76.method1358(var0, 0, var2, 0, var0.length);
            return var2;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ja.H(" + "{...}" + ',' + var1 + ')');
      }
   }

   static int method1234(int var0, int var1, int var2) {
      try {
         int var4 = var1 / var0;
         int var6 = var2 / var0;
         int var7 = var2 & var0 - 1;
         int var5 = -1 + var0 & var1;
         int var8 = Class3_Sub28_Sub3.method543(var4, var6, (byte)-82);
         int var9 = Class3_Sub28_Sub3.method543(var4 + 1, var6, (byte)-104);
         int var10 = Class3_Sub28_Sub3.method543(var4, 1 + var6, (byte)-100);
          int var11 = Class3_Sub28_Sub3.method543(1 + var4, var6 + 1, (byte)-109);
          int var12 = Class3_Sub13_Sub23.method275(var8, var9, var5, 96, var0);
          int var13 = Class3_Sub13_Sub23.method275(var10, var11, var5, 16, var0);
          return Class3_Sub13_Sub23.method275(var12, var13, var7, 87, var0);
      } catch (RuntimeException var14) {
         throw Class44.clientError(var14, "ja.G(" + var0 + ',' + var1 + ',' + var2 + ',' + 512 + ')');
      }
   }

   static void method1235(int var0, int var1, int var2, int var3) {
      try {
         if(Class36.anInt638 == 1) {
            Class139.aClass3_Sub28_Sub16Array1825[Class151_Sub1.anInt2958 / 100].method643(-8 + Class70.anInt1053, -8 + Class3_Sub28_Sub10_Sub1.anInt4062);
         }

          if(Class36.anInt638 == 2) {
            Class139.aClass3_Sub28_Sub16Array1825[4 + Class151_Sub1.anInt2958 / 100].method643(Class70.anInt1053 + -8, -8 + Class3_Sub28_Sub10_Sub1.anInt4062);
         }

         Class3_Sub13_Sub37.method347();
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "ja.A(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + (byte) -121 + ')');
      }
   }

   static void method1236(CacheIndex var0, CacheIndex var1) {
      try {
         Class47.quickChatMessages = var1;
         NodeList.aClass153_332 = var0;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "ja.F(" + (var0 != null?"{...}":"null") + ',' + (var1 != null?"{...}":"null") + ',' + -117 + ')');
      }
   }

   static void method1237(int var0) {
      try {
         WorldListEntry.anInt2626 = 1000 / var0;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ja.D(" + var0 + ',' + 1000 + ')');
      }
   }

   public static void method1238(int var0) {
      try {
         if(var0 < -83) {
            aClass61_983 = null;
            aClass94_985 = null;
            aClass3_Sub30Array986 = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ja.B(" + var0 + ')');
      }
   }

   static void method1239(int var0, int var1, int var2, int var3, int var4, boolean var5) {
      try {
         if(var0 < 1) {
            var0 = 1;
         }

         if(1 > var3) {
            var3 = 1;
         }

         if(HDToolKit.highDetail) {
            int var6 = var3 + -334;
            if(0 <= var6) {
               if(var6 > 100) {
                  var6 = 100;
               }
            } else {
               var6 = 0;
            }

            int var7 = var6 * (Class3_Sub13_Sub3.aShort3052 + -Class106.aShort1444) / 100 + Class106.aShort1444;
            if(Class3_Sub13_Sub19.aShort3241 <= var7) {
               if(PacketParser.aShort83 < var7) {
                  var7 = PacketParser.aShort83;
               }
            } else {
               var7 = Class3_Sub13_Sub19.aShort3241;
            }

            int var8 = var7 * var3 * 512 / (var0 * 334);
            int var9;
            int var10;
            short var12;
            if(var8 >= ItemDefinition.aShort505) {
               if(var8 > Class3_Sub13_Sub23_Sub1.aShort4038) {
                  var12 = Class3_Sub13_Sub23_Sub1.aShort4038;
                  var7 = var12 * var0 * 334 / (var3 * 512);
                  if(var7 < Class3_Sub13_Sub19.aShort3241) {
                     var7 = Class3_Sub13_Sub19.aShort3241;
                     var9 = var12 * var0 * 334 / (512 * var7);
                     var10 = (-var9 + var3) / 2;
                     if(var5) {
                        Class22.method925();
                        Class22.method934(var4, var2, var0, var10, 0);
                        Class22.method934(var4, var2 + (var3 - var10), var0, var10, 0);
                     }

                     var3 -= var10 * 2;
                     var2 += var10;
                  }
               }
            } else {
               var12 = ItemDefinition.aShort505;
               var7 = 334 * var0 * var12 / (512 * var3);
               if(PacketParser.aShort83 < var7) {
                  var7 = PacketParser.aShort83;
                  var9 = 512 * var3 * var7 / (334 * var12);
                  var10 = (var0 - var9) / 2;
                  if(var5) {
                     Class22.method925();
                     Class22.method934(var4, var2, var10, var3, 0);
                     Class22.method934(var0 + (var4 - var10), var2, var10, var3, 0);
                  }

                  var4 += var10;
                  var0 -= 2 * var10;
               }
            }

            Class130.anInt1705 = var7 * var3 / 334;
         }

         Class96.anInt1358 = (short)var0;
         Canvas_Sub2.anInt31 = (short)var3;
         if(var1 < 11) {
            method1233((int[])null, 18);
         }

         Class3_Sub28_Sub3.anInt3564 = var2;
         Class163_Sub1.anInt2989 = var4;
      } catch (RuntimeException var11) {
         throw Class44.clientError(var11, "ja.C(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
      }
   }

   static void method1240() {
      try {
         Class126.aClass3_Sub28_Sub17_1669 = null;
         Class75_Sub3.aClass3_Sub28_Sub16Array2656 = null;
         Class139.aClass3_Sub28_Sub16Array1825 = null;
         Class3_Sub13_Sub22.aClass109Array3270 = null;
         Class157.aClass3_Sub28_Sub17_Sub1_2000 = null;
         Class102.aClass3_Sub28_Sub16_Sub2Array2140 = null;
         Class140_Sub4.aClass3_Sub28_Sub16Array2839 = null;
         Class129_Sub1.aClass3_Sub28_Sub16Array2690 = null;
         NPC.aClass3_Sub28_Sub16Array3977 = null;
         Class168.aClass3_Sub28_Sub17_2096 = null;
         Class57.aClass3_Sub28_Sub16_895 = null;
         Class80.aClass3_Sub28_Sub16Array1136 = null;
         Class3_Sub13.aClass3_Sub28_Sub17_2379 = null;
         Class45.aClass3_Sub28_Sub16_736 = null;
         Class66.aClass3_Sub28_Sub16Array996 = null;
         Class3_Sub13_Sub39.aClass3_Sub28_Sub16Array3458 = null;
         Class166.aClass3_Sub28_Sub16Array2072 = null;
         Class3_Sub13_Sub31.aClass3_Sub28_Sub16Array3373 = null;
         GameObject.aClass109Array1831 = null;

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ja.E(" + false + ')');
      }
   }

}
