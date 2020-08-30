package org.runite.jagex;

final class Class72 {

   GameObject aClass140_1067;
   int anInt1068;
   GameObject aClass140_1069;
   static int anInt1071 = 0;
   static RSString LEFT_PARENTHESES = RSString.createRSString(" (X");
   GameObject aClass140_1073;
   static boolean aBoolean1074 = false;
   int anInt1075;
   static RSString aClass94_1076 = RSString.createRSString("<)4col>");
   int anInt1077;
   int anInt1078;
   long aLong1079;


   static Class3_Sub28_Sub11 method1292(byte var0, int var1) {
      try {
         Class3_Sub28_Sub11 var2 = (Class3_Sub28_Sub11)Class3_Sub13_Sub34.aClass47_3407.method1092((long)var1);
         if(var2 == null) {

            byte[] var3 = Class12.aClass153_322.getFile(26, var1);
            var2 = new Class3_Sub28_Sub11();
            if(var3 != null) {
               var2.method608(new RSByteBuffer(var3));
            }

            Class3_Sub13_Sub34.aClass47_3407.method1097(var2, (long)var1, (byte)59);
         }
         return var2;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "jj.D(" + var0 + ',' + var1 + ')');
      }
   }

   static void method1293() {
      try {
         if(!Class3_Sub28_Sub19.aBoolean3779 && Class44.anInt718 != 2) {
            try {
               InputStream_Sub1.aClass94_38.method1577(Class126.aClient1671);
            } catch (Throwable var2) {
            }

         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "jj.G(" + true + ')');
      }
   }

   static void method1294() {
      Class126.anInt1672 = 0;

      label188:
      for(int var0 = 0; var0 < Class3_Sub4.anInt2249; ++var0) {
         Class113 var1 = Class3_Sub28_Sub8.aClass113Array3610[var0];
         int var2;
         if(Class3_Sub13_Sub2.anIntArray3045 != null) {
            for(var2 = 0; var2 < Class3_Sub13_Sub2.anIntArray3045.length; ++var2) {
               if(Class3_Sub13_Sub2.anIntArray3045[var2] != -1000000 && (var1.anInt1544 <= Class3_Sub13_Sub2.anIntArray3045[var2] || var1.anInt1548 <= Class3_Sub13_Sub2.anIntArray3045[var2]) && (var1.anInt1562 <= Class52.anIntArray859[var2] || var1.anInt1545 <= Class52.anIntArray859[var2]) && (var1.anInt1562 >= Class73.anIntArray1083[var2] || var1.anInt1545 >= Class73.anIntArray1083[var2]) && (var1.anInt1560 <= Class75_Sub4.anIntArray2663[var2] || var1.anInt1550 <= Class75_Sub4.anIntArray2663[var2]) && (var1.anInt1560 >= InputStream_Sub1.anIntArray39[var2] || var1.anInt1550 >= InputStream_Sub1.anIntArray39[var2])) {
                  continue label188;
               }
            }
         }

         int var3;
         int var4;
         boolean var5;
         int var6;
         if(var1.anInt1554 == 1) {
            var2 = var1.anInt1553 - Class97.anInt1375 + Class3_Sub13_Sub39.anInt3466;
            if(var2 >= 0 && var2 <= Class3_Sub13_Sub39.anInt3466 + Class3_Sub13_Sub39.anInt3466) {
               var3 = var1.anInt1563 - Class3_Sub13_Sub27.anInt3340 + Class3_Sub13_Sub39.anInt3466;
               if(var3 < 0) {
                  var3 = 0;
               }

               var4 = var1.anInt1566 - Class3_Sub13_Sub27.anInt3340 + Class3_Sub13_Sub39.anInt3466;
               if(var4 > Class3_Sub13_Sub39.anInt3466 + Class3_Sub13_Sub39.anInt3466) {
                  var4 = Class3_Sub13_Sub39.anInt3466 + Class3_Sub13_Sub39.anInt3466;
               }

               var5 = false;

               while(var3 <= var4) {
                  if(Class23.aBooleanArrayArray457[var2][var3++]) {
                     var5 = true;
                     break;
                  }
               }

               if(var5) {
                  var6 = Class129_Sub1.anInt2697 - var1.anInt1562;
                  if(var6 > 32) {
                     var1.anInt1564 = 1;
                  } else {
                     if(var6 >= -32) {
                        continue;
                     }

                     var1.anInt1564 = 2;
                     var6 = -var6;
                  }

                  var1.anInt1555 = (var1.anInt1560 - Class3_Sub13_Sub30.anInt3363 << 8) / var6;
                  var1.anInt1551 = (var1.anInt1550 - Class3_Sub13_Sub30.anInt3363 << 8) / var6;
                  var1.anInt1561 = (var1.anInt1544 - Class3_Sub28_Sub13.anInt3657 << 8) / var6;
                  var1.anInt1565 = (var1.anInt1548 - Class3_Sub28_Sub13.anInt3657 << 8) / var6;
                  Class145.aClass113Array1895[Class126.anInt1672++] = var1;
               }
            }
         } else if(var1.anInt1554 == 2) {
            var2 = var1.anInt1563 - Class3_Sub13_Sub27.anInt3340 + Class3_Sub13_Sub39.anInt3466;
            if(var2 >= 0 && var2 <= Class3_Sub13_Sub39.anInt3466 + Class3_Sub13_Sub39.anInt3466) {
               var3 = var1.anInt1553 - Class97.anInt1375 + Class3_Sub13_Sub39.anInt3466;
               if(var3 < 0) {
                  var3 = 0;
               }

               var4 = var1.anInt1547 - Class97.anInt1375 + Class3_Sub13_Sub39.anInt3466;
               if(var4 > Class3_Sub13_Sub39.anInt3466 + Class3_Sub13_Sub39.anInt3466) {
                  var4 = Class3_Sub13_Sub39.anInt3466 + Class3_Sub13_Sub39.anInt3466;
               }

               var5 = false;

               while(var3 <= var4) {
                  if(Class23.aBooleanArrayArray457[var3++][var2]) {
                     var5 = true;
                     break;
                  }
               }

               if(var5) {
                  var6 = Class3_Sub13_Sub30.anInt3363 - var1.anInt1560;
                  if(var6 > 32) {
                     var1.anInt1564 = 3;
                  } else {
                     if(var6 >= -32) {
                        continue;
                     }

                     var1.anInt1564 = 4;
                     var6 = -var6;
                  }

                  var1.anInt1549 = (var1.anInt1562 - Class129_Sub1.anInt2697 << 8) / var6;
                  var1.anInt1557 = (var1.anInt1545 - Class129_Sub1.anInt2697 << 8) / var6;
                  var1.anInt1561 = (var1.anInt1544 - Class3_Sub28_Sub13.anInt3657 << 8) / var6;
                  var1.anInt1565 = (var1.anInt1548 - Class3_Sub28_Sub13.anInt3657 << 8) / var6;
                  Class145.aClass113Array1895[Class126.anInt1672++] = var1;
               }
            }
         } else if(var1.anInt1554 == 4) {
            var2 = var1.anInt1544 - Class3_Sub28_Sub13.anInt3657;
            if(var2 > 128) {
               var3 = var1.anInt1563 - Class3_Sub13_Sub27.anInt3340 + Class3_Sub13_Sub39.anInt3466;
               if(var3 < 0) {
                  var3 = 0;
               }

               var4 = var1.anInt1566 - Class3_Sub13_Sub27.anInt3340 + Class3_Sub13_Sub39.anInt3466;
               if(var4 > Class3_Sub13_Sub39.anInt3466 + Class3_Sub13_Sub39.anInt3466) {
                  var4 = Class3_Sub13_Sub39.anInt3466 + Class3_Sub13_Sub39.anInt3466;
               }

               if(var3 <= var4) {
                  int var10 = var1.anInt1553 - Class97.anInt1375 + Class3_Sub13_Sub39.anInt3466;
                  if(var10 < 0) {
                     var10 = 0;
                  }

                  var6 = var1.anInt1547 - Class97.anInt1375 + Class3_Sub13_Sub39.anInt3466;
                  if(var6 > Class3_Sub13_Sub39.anInt3466 + Class3_Sub13_Sub39.anInt3466) {
                     var6 = Class3_Sub13_Sub39.anInt3466 + Class3_Sub13_Sub39.anInt3466;
                  }

                  boolean var7 = false;

                  label114:
                  for(int var8 = var10; var8 <= var6; ++var8) {
                     for(int var9 = var3; var9 <= var4; ++var9) {
                        if(Class23.aBooleanArrayArray457[var8][var9]) {
                           var7 = true;
                           break label114;
                        }
                     }
                  }

                  if(var7) {
                     var1.anInt1564 = 5;
                     var1.anInt1549 = (var1.anInt1562 - Class129_Sub1.anInt2697 << 8) / var2;
                     var1.anInt1557 = (var1.anInt1545 - Class129_Sub1.anInt2697 << 8) / var2;
                     var1.anInt1555 = (var1.anInt1560 - Class3_Sub13_Sub30.anInt3363 << 8) / var2;
                     var1.anInt1551 = (var1.anInt1550 - Class3_Sub13_Sub30.anInt3363 << 8) / var2;
                     Class145.aClass113Array1895[Class126.anInt1672++] = var1;
                  }
               }
            }
         }
      }

   }

   static RSString combatLevelColor(int otherPlayer, byte levelByte, int yourPlayer) {
      try {
         int playerLevelDiff = -otherPlayer + yourPlayer;
         if (levelByte > -52)
            return (RSString)null;
         if (playerLevelDiff < -9)
            return ColorCore.LvlDiffN9;//Solid Red
         if (playerLevelDiff < -6)
            return ColorCore.LvlDiffN6;//Dark Orange
         if (playerLevelDiff < -3)
            return ColorCore.LvlDiffN3;//Orange
         if (playerLevelDiff < 0)
            return ColorCore.LvlDiffN0;//Yellow-Orange
         if (playerLevelDiff > 9)
            return ColorCore.LvlDiffP9;//Bright Green
         if (playerLevelDiff > 6)
            return ColorCore.LvlDiffP6;//Green
         if (playerLevelDiff > 3)
            return ColorCore.LvlDiffP3;//Yellow-Green
         if (playerLevelDiff > 0)
            return ColorCore.LvlDiffP0;//Yellow

         return ColorCore.LvlDiffDefault;//Yellow

      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "jj.E(" + otherPlayer + ',' + levelByte + ',' + yourPlayer + ')');
      }
   }

   public static void method1296(int var0) {
      try {
         aClass94_1076 = null;
         if(var0 == 1) {
        	LEFT_PARENTHESES = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "jj.F(" + var0 + ')');
      }
   }

   static float[] method1297() {
      try {
         float var1 = Class92.method1514() + Class92.getLightingModelAmbient();
         int var2 = Class92.screenColorRgb();
         float var3 = (float)(255 & var2 >> 16) / 255.0F;
         MouseListeningClass.aFloatArray1919[3] = 1.0F;
         float var4 = (float)(('\uff59' & var2) >> 8) / 255.0F;
         float var6 = 0.58823526F;
         float var5 = (float)(255 & var2) / 255.0F;
         MouseListeningClass.aFloatArray1919[2] = Class151.aFloatArray1934[2] * var5 * var6 * var1;
         MouseListeningClass.aFloatArray1919[0] = Class151.aFloatArray1934[0] * var3 * var6 * var1;
         MouseListeningClass.aFloatArray1919[1] = var1 * var6 * var4 * Class151.aFloatArray1934[1];
         return MouseListeningClass.aFloatArray1919;
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "jj.A(" + (byte) -50 + ')');
      }
   }

   static RSString method1298(byte var0, int var1) {
      try {
         return var0 != 9?(RSString)null:Class118.method1723((byte)-117, false, var1);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "jj.C(" + var0 + ',' + var1 + ')');
      }
   }

}
