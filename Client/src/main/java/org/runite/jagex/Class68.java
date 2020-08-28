package org.runite.jagex;

import javax.media.opengl.GL;

final class Class68 {

   static Class43[] aClass43Array1021;
   private static float[] aFloatArray1022 = new float[]{0.0F, 0.0F, 0.0F, 1.0F};
   private static int[] anIntArray1023;
   private static int anInt1024;
   private static int anInt1025;
   private static boolean[] aBooleanArray1026;
   private static int[][][] anIntArrayArrayArray1027;
   private static int[] anIntArray1028;
   private static int anInt1029;
   private static int anInt1030;
   private static int anInt1031;
   static int anInt1032 = 0;
   private static boolean[] aBooleanArray1033;
   private static int anInt1034;
   private static int anInt1035;
   private static int anInt1036;


   static void method1263(int var0, int var1, int var2, int var3, int var4, int var5, int var6) {
      if(Class106.aBoolean1441) {
         if(var0 == 1 && var5 > 0) {
            method1268(var1, var2, var3, var4, var5 - 1, var6);
         } else if(var0 == 4 && var5 < anInt1036 - 1) {
            method1268(var1, var2, var3, var4, var5 + 1, var6);
         } else if(var0 == 8 && var6 > 0) {
            method1268(var1, var2, var3, var4, var5, var6 - 1);
         } else if(var0 == 2 && var6 < anInt1035 - 1) {
            method1268(var1, var2, var3, var4, var5, var6 + 1);
         } else if(var0 == 16 && var5 > 0 && var6 < anInt1035 - 1) {
            method1268(var1, var2, var3, var4, var5 - 1, var6 + 1);
         } else if(var0 == 32 && var5 < anInt1036 - 1 && var6 < anInt1035 - 1) {
            method1268(var1, var2, var3, var4, var5 + 1, var6 + 1);
         } else if(var0 == 128 && var5 > 0 && var6 > 0) {
            method1268(var1, var2, var3, var4, var5 - 1, var6 - 1);
         } else if(var0 == 64 && var5 < anInt1036 - 1 && var6 > 0) {
            method1268(var1, var2, var3, var4, var5 + 1, var6 - 1);
         }
      }
   }

   static void method1264(Class43 var0) {
      if(anInt1032 >= 255) {
         System.out.println("Number of lights added exceeds maximum!");
      } else {
         aClass43Array1021[anInt1032++] = var0;
      }
   }

   static void method1265() {
      for(int var0 = 0; var0 < 4; ++var0) {
         anIntArray1028[var0] = -1;
         method1271(var0);
      }

   }

   static void method1266(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      if(Class106.aBoolean1441) {
         if(anInt1029 != var3 || anInt1031 != var4 || anInt1024 != var5 || anInt1034 != var6 || anInt1025 != var7) {
            int var8;
            for(var8 = 0; var8 < 4; ++var8) {
               aBooleanArray1033[var8] = false;
            }

            var8 = 0;
            int var9 = 0;

            int var10;
            int var11;
            label105:
            for(var10 = var4; var10 <= var6; ++var10) {
               for(var11 = var5; var11 <= var7; ++var11) {
                  int var12 = anIntArrayArrayArray1027[var3][var10][var11];

                  label101:
                  while(var12 != 0) {
                     int var13 = (var12 & 255) - 1;
                     var12 >>>= 8;

                     int var14;
                     for(var14 = 0; var14 < var9; ++var14) {
                        if(var13 == anIntArray1023[var14]) {
                           continue label101;
                        }
                     }

                     for(var14 = 0; var14 < 4; ++var14) {
                        if(var13 == anIntArray1028[var14]) {
                           if(!aBooleanArray1033[var14]) {
                              aBooleanArray1033[var14] = true;
                              ++var8;
                              if(var8 == 4) {
                                 break label105;
                              }
                           }
                           continue label101;
                        }
                     }

                     anIntArray1023[var9++] = var13;
                     ++var8;
                     if(var8 == 4) {
                        break label105;
                     }
                  }
               }
            }

            var10 = 0;

            while(var10 < var9) {
               var11 = 0;

               while(true) {
                  if(var11 < 4) {
                     if(aBooleanArray1033[var11]) {
                        ++var11;
                        continue;
                     }

                     anIntArray1028[var11] = anIntArray1023[var10];
                     aBooleanArray1033[var11] = true;
                     method1278(var11, aClass43Array1021[anIntArray1023[var10]], var0, var1, var2);
                  }

                  ++var10;
                  break;
               }
            }

            for(var10 = 0; var10 < 4; ++var10) {
               if(!aBooleanArray1033[var10]) {
                  anIntArray1028[var10] = -1;
                  method1271(var10);
               }
            }

            anInt1029 = var3;
            anInt1031 = var4;
            anInt1024 = var5;
            anInt1034 = var6;
            anInt1025 = var7;
         }
      }
   }

   static void method1267() {
      anInt1030 = 4;
      anInt1036 = 104;
      anInt1035 = 104;
      anIntArrayArrayArray1027 = new int[anInt1030][anInt1036][anInt1035];
   }

   static void method1268(int var0, int var1, int var2, int var3, int var4, int var5) {
      if(Class106.aBoolean1441) {
         if(anInt1029 != var3 || anInt1031 != var4 || anInt1024 != var5 || anInt1034 != var4 || anInt1025 != var5) {
            int var6;
            for(var6 = 0; var6 < 4; ++var6) {
               aBooleanArray1033[var6] = false;
            }

            var6 = 0;
            int var7 = anIntArrayArrayArray1027[var3][var4][var5];

            int var8;
            int var9;
            label71:
            while(var7 != 0) {
               var8 = (var7 & 255) - 1;
               var7 >>>= 8;

               for(var9 = 0; var9 < 4; ++var9) {
                  if(var8 == anIntArray1028[var9]) {
                     aBooleanArray1033[var9] = true;
                     continue label71;
                  }
               }

               anIntArray1023[var6++] = var8;
            }

            var8 = 0;

            while(var8 < var6) {
               var9 = 0;

               while(true) {
                  if(var9 < 4) {
                     if(aBooleanArray1033[var9]) {
                        ++var9;
                        continue;
                     }

                     anIntArray1028[var9] = anIntArray1023[var8];
                     aBooleanArray1033[var9] = true;
                     method1278(var9, aClass43Array1021[anIntArray1023[var8]], var0, var1, var2);
                  }

                  ++var8;
                  break;
               }
            }

            for(var8 = 0; var8 < 4; ++var8) {
               if(!aBooleanArray1033[var8]) {
                  anIntArray1028[var8] = -1;
                  method1271(var8);
               }
            }

            anInt1029 = var3;
            anInt1031 = var4;
            anInt1024 = var5;
            anInt1034 = var4;
            anInt1025 = var5;
         }
      }
   }

   static void method1269(int var0, boolean var1) {
      for(int var2 = 0; var2 < anInt1032; ++var2) {
         aClass43Array1021[var2].method1063(var1, var0);
      }

      anInt1029 = -1;
      anInt1031 = -1;
      anInt1024 = -1;
      anInt1034 = -1;
      anInt1025 = -1;
   }

   static void method1270() {
      for(int var0 = 0; var0 < anInt1032; ++var0) {
         Class43 var1 = aClass43Array1021[var0];
         int var2 = var1.anInt704;
         if(var1.aBoolean690) {
            var2 = 0;
         }

         int var3 = var1.anInt704;
         if(var1.aBoolean711) {
            var3 = 3;
         }

         for(int var4 = var2; var4 <= var3; ++var4) {
            int var5 = 0;
            int var6 = (var1.anInt708 >> 7) - var1.anInt698;
            if(var6 < 0) {
               var5 -= var6;
               var6 = 0;
            }

            int var7 = (var1.anInt708 >> 7) + var1.anInt698;
            if(var7 > anInt1035 - 1) {
               var7 = anInt1035 - 1;
            }

            for(int var8 = var6; var8 <= var7; ++var8) {
               short var9 = var1.aShortArray706[var5++];
               int var10 = (var1.anInt703 >> 7) - var1.anInt698 + (var9 >> 8);
               int var11 = var10 + (var9 & 255) - 1;
               if(var10 < 0) {
                  var10 = 0;
               }

               if(var11 > anInt1036 - 1) {
                  var11 = anInt1036 - 1;
               }

               for(int var12 = var10; var12 <= var11; ++var12) {
                  int var13 = anIntArrayArrayArray1027[var4][var12][var8];
                  if((var13 & 255) == 0) {
                     anIntArrayArrayArray1027[var4][var12][var8] = var13 | var0 + 1;
                  } else if((var13 & '\uff00') == 0) {
                     anIntArrayArrayArray1027[var4][var12][var8] = var13 | var0 + 1 << 8;
                  } else if((var13 & 16711680) == 0) {
                     anIntArrayArrayArray1027[var4][var12][var8] = var13 | var0 + 1 << 16;
                  } else if((var13 & -16777216) == 0) {
                     anIntArrayArrayArray1027[var4][var12][var8] = var13 | var0 + 1 << 24;
                  }
               }
            }
         }
      }

   }

   private static void method1271(int var0) {
      if(aBooleanArray1026[var0]) {
         aBooleanArray1026[var0] = false;
         int var1 = var0 + 16384 + 4;
         GL var2 = HDToolKit.gl;
         var2.glDisable(var1);
      }
   }

   static void method1272(int var0, int var1, int var2, int var3, int var4) {
      if(Class106.aBoolean1441) {
         label44:
         for(int var5 = 0; var5 < 4; ++var5) {
            if(anIntArray1028[var5] != -1) {
               int var6 = anIntArrayArrayArray1027[var0][var1][var2];

               int var7;
               while(var6 != 0) {
                  var7 = (var6 & 255) - 1;
                  var6 >>>= 8;
                  if(var7 == anIntArray1028[var5]) {
                     continue label44;
                  }
               }

               var6 = anIntArrayArrayArray1027[var0][var3][var4];

               while(var6 != 0) {
                  var7 = (var6 & 255) - 1;
                  var6 >>>= 8;
                  if(var7 == anIntArray1028[var5]) {
                     continue label44;
                  }
               }
            }

            anIntArray1028[var5] = -1;
            method1271(var5);
         }

      }
   }

   static void method1273() {
      aClass43Array1021 = null;
      anIntArray1028 = null;
      aBooleanArray1026 = null;
      anIntArray1023 = null;
      aBooleanArray1033 = null;
      anIntArrayArrayArray1027 = (int[][][])null;
   }

   public static void method1274() {
      aClass43Array1021 = null;
      anIntArrayArrayArray1027 = (int[][][])null;
      anIntArray1028 = null;
      aBooleanArray1026 = null;
      aFloatArray1022 = null;
      anIntArray1023 = null;
      aBooleanArray1033 = null;
   }

   static void method1275() {
      javax.media.opengl.GL var0 = HDToolKit.gl;

      int var1;
      for(var1 = 0; var1 < 4; ++var1) {
         int var2 = 16388 + var1;
         var0.glLightfv(var2, 4608, new float[]{0.0F, 0.0F, 0.0F, 1.0F}, 0);
         var0.glLightf(var2, 4616, 0.0F);
         var0.glLightf(var2, 4615, 0.0F);
      }

      for(var1 = 0; var1 < 4; ++var1) {
         anIntArray1028[var1] = -1;
         method1271(var1);
      }

   }

   static void method1276() {
      aClass43Array1021 = new Class43[255];
      anIntArray1028 = new int[4];
      aBooleanArray1026 = new boolean[4];
      anIntArray1023 = new int[4];
      aBooleanArray1033 = new boolean[4];
      anIntArrayArrayArray1027 = new int[anInt1030][anInt1036][anInt1035];
   }

   static void method1277(Class3_Sub2[][][] var2) {
      if(Class106.aBoolean1441) {
         javax.media.opengl.GL var3 = HDToolKit.gl;
         Class3_Sub28_Sub4.method551(0, 0);
         HDToolKit.method1856(0);
         HDToolKit.method1823();
         HDToolKit.bindTexture2D(HDToolKit.anInt1810);
         var3.glDepthMask(false);
         HDToolKit.method1837(false);
         var3.glBlendFunc(774, 1);
         var3.glFogfv(2918, new float[]{0.0F, 0.0F, 0.0F, 0.0F}, 0);
         var3.glTexEnvi(8960, '\u8580', '\u8576');
         var3.glTexEnvi(8960, '\u8590', 770);

         label69:
         for(int var4 = 0; var4 < anInt1032; ++var4) {
            Class43 var5 = aClass43Array1021[var4];
            int var6 = var5.anInt704;
            if(var5.aBoolean696) {
               --var6;
            }

            if(var5.aClass37_712 != null) {
               int var7 = 0;
               int var8 = (var5.anInt708 >> 7) - var5.anInt698;
               int var9 = (var5.anInt708 >> 7) + var5.anInt698;
               if(var9 >= Class126.anInt1665) {
                  var9 = Class126.anInt1665 - 1;
               }

               if(var8 < Class3_Sub28_Sub7.anInt3603) {
                  var7 += Class3_Sub28_Sub7.anInt3603 - var8;
                  var8 = Class3_Sub28_Sub7.anInt3603;
               }

               int var10 = var8;

               while(var10 <= var9) {
                  short var11 = var5.aShortArray706[var7++];
                  int var12 = (var5.anInt703 >> 7) - var5.anInt698 + (var11 >> 8);
                  int var13 = var12 + (var11 & 255) - 1;
                  if(var12 < Class163_Sub1_Sub1.anInt4006) {
                     var12 = Class163_Sub1_Sub1.anInt4006;
                  }

                  if(var13 >= Class2.anInt67) {
                     var13 = Class2.anInt67 - 1;
                  }

                  int var14 = var12;

                  while(true) {
                     if(var14 <= var13) {
                        Class3_Sub2 var15 = null;
                        if(var6 >= 0) {
                           var15 = var2[var6][var14][var10];
                        }

                        if(var6 >= 0 && (var15 == null || !var15.aBoolean2222)) {
                           ++var14;
                           continue;
                        }

                        HDToolKit.method1832(201.5F - (float)var5.anInt704 * 50.0F - 1.5F);
                        var3.glTexEnvfv(8960, 8705, new float[]{0.0F, 0.0F, 0.0F, var5.aFloat707}, 0);
                        var5.aClass37_712.method1021();
                        continue label69;
                     }

                     ++var10;
                     break;
                  }
               }
            }
         }

         var3.glTexEnvi(8960, '\u8580', 5890);
         var3.glTexEnvi(8960, '\u8590', 768);
         var3.glBlendFunc(770, 771);
         var3.glDepthMask(true);
         var3.glFogfv(2918, Class92.fogColor, 0);
         var3.glEnableClientState('\u8078');
         HDToolKit.method1846();
      }
   }

   private static void method1278(int var0, Class43 var1, int var2, int var3, int var4) {
      int var5 = var0 + 16384 + 4;
      javax.media.opengl.GL var6 = HDToolKit.gl;
      if(!aBooleanArray1026[var0]) {
         var6.glEnable(var5);
         aBooleanArray1026[var0] = true;
      }

      var6.glLightf(var5, 4617, var1.aFloat710);
      var6.glLightfv(var5, 4609, var1.aFloatArray717, 0);
      aFloatArray1022[0] = (float)(var1.anInt703 - var2);
      aFloatArray1022[1] = (float)(var1.anInt697 - var3);
      aFloatArray1022[2] = (float)(var1.anInt708 - var4);
      var6.glLightfv(var5, 4611, aFloatArray1022, 0);
   }

   static void method1279() {
      anInt1032 = 0;

      for(int var0 = 0; var0 < anInt1030; ++var0) {
         for(int var1 = 0; var1 < anInt1036; ++var1) {
            for(int var2 = 0; var2 < anInt1035; ++var2) {
               anIntArrayArrayArray1027[var0][var1][var2] = 0;
            }
         }
      }

   }

}
