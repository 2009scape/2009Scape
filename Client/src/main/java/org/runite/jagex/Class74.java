package org.runite.jagex;

final class Class74 {

   static int anInt1092;
   static int anInt1093 = 0;
   static int anInt1094;
   static int anInt1095 = 0;
   static int anInt1096 = 0;
   static int[] anIntArray1097;
   static int[] anIntArray1098;
   static int anInt1099 = 0;
   static int[] anIntArray1100;


   static void method1310() {
      anIntArray1097 = null;
      anIntArray1098 = null;
   }

   static void method1311(int var0, int var1, int var2, int var3, int var4) {
      method1317(var0, var1, var2, var4);
      method1317(var0, var1 + var3 - 1, var2, var4);
      method1318(var0, var1, var3, var4);
      method1318(var0 + var2 - 1, var1, var3, var4);
   }

   static void method1312(int var0, int var1, int var2, int var3, int var4, int var5) {
      if(var0 < anInt1093) {
         var2 -= anInt1093 - var0;
         var0 = anInt1093;
      }

      if(var1 < anInt1095) {
         var3 -= anInt1095 - var1;
         var1 = anInt1095;
      }

      if(var0 + var2 > anInt1096) {
         var2 = anInt1096 - var0;
      }

      if(var1 + var3 > anInt1099) {
         var3 = anInt1099 - var1;
      }

      var4 = ((var4 & 16711935) * var5 >> 8 & 16711935) + ((var4 & '\uff00') * var5 >> 8 & '\uff00');
      int var6 = 256 - var5;
      int var7 = anInt1092 - var2;
      int var8 = var0 + var1 * anInt1092;

      for(int var9 = 0; var9 < var3; ++var9) {
         for(int var10 = -var2; var10 < 0; ++var10) {
            int var11 = anIntArray1100[var8];
            var11 = ((var11 & 16711935) * var6 >> 8 & 16711935) + ((var11 & '\uff00') * var6 >> 8 & '\uff00');
            anIntArray1100[var8++] = var4 + var11;
         }

         var8 += var7;
      }

   }

   private static void method1313(int var0, int var1) {
      if(var0 >= anInt1093 && var1 >= anInt1095 && var0 < anInt1096 && var1 < anInt1099) {
         anIntArray1100[var0 + var1 * anInt1092] = 16776960;
      }
   }

   static void method1314(int[] var0, int[] var1) {
      if(var0.length == anInt1099 - anInt1095 && var1.length == anInt1099 - anInt1095) {
         anIntArray1097 = var0;
         anIntArray1098 = var1;
      } else {
         throw new IllegalArgumentException();
      }
   }

   static void method1315(int var0, int var1, int var2, int var3, int var4, int var5) {
      method1321(var0, var1, var2, var4, var5);
      method1321(var0, var1 + var3 - 1, var2, var4, var5);
      if(var3 >= 3) {
         method1327(var0, var1 + 1, var3 - 2, var4, var5);
         method1327(var0 + var2 - 1, var1 + 1, var3 - 2, var4, var5);
      }

   }

   static void method1316(int[] var0) {
      anInt1093 = var0[0];
      anInt1095 = var0[1];
      anInt1096 = var0[2];
      anInt1099 = var0[3];
      method1310();
   }

   static void method1317(int var0, int var1, int var2, int var3) {
      if(var1 >= anInt1095 && var1 < anInt1099) {
         if(var0 < anInt1093) {
            var2 -= anInt1093 - var0;
            var0 = anInt1093;
         }

         if(var0 + var2 > anInt1096) {
            var2 = anInt1096 - var0;
         }

         int var4 = var0 + var1 * anInt1092;

         for(int var5 = 0; var5 < var2; ++var5) {
            anIntArray1100[var4 + var5] = var3;
         }

      }
   }

   static void method1318(int var0, int var1, int var2, int var3) {
      if(var0 >= anInt1093 && var0 < anInt1096) {
         if(var1 < anInt1095) {
            var2 -= anInt1095 - var1;
            var1 = anInt1095;
         }

         if(var1 + var2 > anInt1099) {
            var2 = anInt1099 - var1;
         }

         int var4 = var0 + var1 * anInt1092;

         for(int var5 = 0; var5 < var2; ++var5) {
            anIntArray1100[var4 + var5 * anInt1092] = var3;
         }

      }
   }

   static void method1319(int[] var0, int var1, int var2) {
      anIntArray1100 = var0;
      anInt1092 = var1;
      anInt1094 = var2;
      method1324(0, 0, var1, var2);
   }

   static void method1320() {
      int var0 = 0;

      int var1;
      for(var1 = anInt1092 * anInt1094 - 7; var0 < var1; anIntArray1100[var0++] = 0) {
         anIntArray1100[var0++] = 0;
         anIntArray1100[var0++] = 0;
         anIntArray1100[var0++] = 0;
         anIntArray1100[var0++] = 0;
         anIntArray1100[var0++] = 0;
         anIntArray1100[var0++] = 0;
         anIntArray1100[var0++] = 0;
      }

      for(var1 += 7; var0 < var1; anIntArray1100[var0++] = 0) {
      }

   }

   private static void method1321(int var0, int var1, int var2, int var3, int var4) {
      if(var1 >= anInt1095 && var1 < anInt1099) {
         if(var0 < anInt1093) {
            var2 -= anInt1093 - var0;
            var0 = anInt1093;
         }

         if(var0 + var2 > anInt1096) {
            var2 = anInt1096 - var0;
         }

         int var5 = 256 - var4;
         int var6 = (var3 >> 16 & 255) * var4;
         int var7 = (var3 >> 8 & 255) * var4;
         int var8 = (var3 & 255) * var4;
         int var12 = var0 + var1 * anInt1092;

         for(int var13 = 0; var13 < var2; ++var13) {
            int var9 = (anIntArray1100[var12] >> 16 & 255) * var5;
            int var10 = (anIntArray1100[var12] >> 8 & 255) * var5;
            int var11 = (anIntArray1100[var12] & 255) * var5;
            int var14 = (var6 + var9 >> 8 << 16) + (var7 + var10 >> 8 << 8) + (var8 + var11 >> 8);
            anIntArray1100[var12++] = var14;
         }

      }
   }

   static void method1322(int var0, int var1, int var2, int var3, int var4, int var5) {
      int var6 = var2 - var0;
      int var7 = var3 - var1;
      int var8 = var6 >= 0?var6:-var6;
      int var9 = var7 >= 0?var7:-var7;
      int var10 = var8;
      if(var8 < var9) {
         var10 = var9;
      }

      if(var10 != 0) {
         int var11 = (var6 << 16) / var10;
         int var12 = (var7 << 16) / var10;
         if(var12 <= var11) {
            var11 = -var11;
         } else {
            var12 = -var12;
         }

         int var13 = var5 * var12 >> 17;
         int var14 = var5 * var12 + 1 >> 17;
         int var15 = var5 * var11 >> 17;
         int var16 = var5 * var11 + 1 >> 17;
         var0 -= Class51.method1139();
         var1 -= Class51.method1153();
         int var17 = var0 + var13;
         int var18 = var0 - var14;
         int var19 = var0 + var6 - var14;
         int var20 = var0 + var6 + var13;
         int var21 = var1 + var15;
         int var22 = var1 - var16;
         int var23 = var1 + var7 - var16;
         int var24 = var1 + var7 + var15;
         Class51.method1148(var17, var18, var19);
         Class51.method1144(var21, var22, var23, var17, var18, var19, var4);
         Class51.method1148(var17, var19, var20);
         Class51.method1144(var21, var23, var24, var17, var19, var20, var4);
      }
   }

   static void method1323(int var0, int var1, int var2, int var3, int var4) {
      if(var0 < anInt1093) {
         var2 -= anInt1093 - var0;
         var0 = anInt1093;
      }

      if(var1 < anInt1095) {
         var3 -= anInt1095 - var1;
         var1 = anInt1095;
      }

      if(var0 + var2 > anInt1096) {
         var2 = anInt1096 - var0;
      }

      if(var1 + var3 > anInt1099) {
         var3 = anInt1099 - var1;
      }

      int var5 = anInt1092 - var2;
      int var6 = var0 + var1 * anInt1092;

      for(int var7 = -var3; var7 < 0; ++var7) {
         for(int var8 = -var2; var8 < 0; ++var8) {
            anIntArray1100[var6++] = var4;
         }

         var6 += var5;
      }

   }

   static void method1324(int var0, int var1, int var2, int var3) {
      if(var0 < 0) {
         var0 = 0;
      }

      if(var1 < 0) {
         var1 = 0;
      }

      if(var2 > anInt1092) {
         var2 = anInt1092;
      }

      if(var3 > anInt1094) {
         var3 = anInt1094;
      }

      anInt1093 = var0;
      anInt1095 = var1;
      anInt1096 = var2;
      anInt1099 = var3;
      method1310();
   }

   static void method1325(int[] var0) {
      var0[0] = anInt1093;
      var0[1] = anInt1095;
      var0[2] = anInt1096;
      var0[3] = anInt1099;
   }

   static void method1326(int var0, int var1, int var2, int var3) {
      if(anInt1093 < var0) {
         anInt1093 = var0;
      }

      if(anInt1095 < var1) {
         anInt1095 = var1;
      }

      if(anInt1096 > var2) {
         anInt1096 = var2;
      }

      if(anInt1099 > var3) {
         anInt1099 = var3;
      }

      method1310();
   }

   private static void method1327(int var0, int var1, int var2, int var3, int var4) {
      if(var0 >= anInt1093 && var0 < anInt1096) {
         if(var1 < anInt1095) {
            var2 -= anInt1095 - var1;
            var1 = anInt1095;
         }

         if(var1 + var2 > anInt1099) {
            var2 = anInt1099 - var1;
         }

         int var5 = 256 - var4;
         int var6 = (var3 >> 16 & 255) * var4;
         int var7 = (var3 >> 8 & 255) * var4;
         int var8 = (var3 & 255) * var4;
         int var12 = var0 + var1 * anInt1092;

         for(int var13 = 0; var13 < var2; ++var13) {
            int var9 = (anIntArray1100[var12] >> 16 & 255) * var5;
            int var10 = (anIntArray1100[var12] >> 8 & 255) * var5;
            int var11 = (anIntArray1100[var12] & 255) * var5;
            int var14 = (var6 + var9 >> 8 << 16) + (var7 + var10 >> 8 << 8) + (var8 + var11 >> 8);
            anIntArray1100[var12] = var14;
            var12 += anInt1092;
         }

      }
   }

   static void method1328(int var0, int var1, int var2, int var3, int var4) {
      var2 -= var0;
      var3 -= var1;
      if(var3 == 0) {
         if(var2 >= 0) {
            method1317(var0, var1, var2 + 1, var4);
         } else {
            method1317(var0 + var2, var1, -var2 + 1, var4);
         }

      } else if(var2 == 0) {
         if(var3 >= 0) {
            method1318(var0, var1, var3 + 1, var4);
         } else {
            method1318(var0, var1 + var3, -var3 + 1, var4);
         }

      } else {
         if(var2 + var3 < 0) {
            var0 += var2;
            var2 = -var2;
            var1 += var3;
            var3 = -var3;
         }

         int var5;
         int var6;
         if(var2 > var3) {
            var1 <<= 16;
            var1 += '\u8000';
            var3 <<= 16;
            var5 = (int)Math.floor((double)var3 / (double)var2 + 0.5D);
            var2 += var0;
            if(var0 < anInt1093) {
               var1 += var5 * (anInt1093 - var0);
               var0 = anInt1093;
            }

            if(var2 >= anInt1096) {
               var2 = anInt1096 - 1;
            }

            while(var0 <= var2) {
               var6 = var1 >> 16;
               if(var6 >= anInt1095 && var6 < anInt1099) {
                  anIntArray1100[var0 + var6 * anInt1092] = var4;
               }

               var1 += var5;
               ++var0;
            }
         } else {
            var0 <<= 16;
            var0 += '\u8000';
            var2 <<= 16;
            var5 = (int)Math.floor((double)var2 / (double)var3 + 0.5D);
            var3 += var1;
            if(var1 < anInt1095) {
               var0 += var5 * (anInt1095 - var1);
               var1 = anInt1095;
            }

            if(var3 >= anInt1099) {
               var3 = anInt1099 - 1;
            }

            while(var1 <= var3) {
               var6 = var0 >> 16;
               if(var6 >= anInt1093 && var6 < anInt1096) {
                  anIntArray1100[var6 + var1 * anInt1092] = var4;
               }

               var0 += var5;
               ++var1;
            }
         }

      }
   }

   private static void method1329(int var0, int var1, int var2) {
      if(var2 == 0) {
         method1313(var0, var1);
      } else {
         if(var2 < 0) {
            var2 = -var2;
         }

         int var4 = var1 - var2;
         if(var4 < anInt1095) {
            var4 = anInt1095;
         }

         int var5 = var1 + var2 + 1;
         if(var5 > anInt1099) {
            var5 = anInt1099;
         }

         int var6 = var4;
         int var7 = var2 * var2;
         int var8 = 0;
         int var9 = var1 - var4;
         int var10 = var9 * var9;
         int var11 = var10 - var9;
         if(var1 > var5) {
            var1 = var5;
         }

         int var12;
         int var13;
         int var14;
         int var15;
         while(var6 < var1) {
            while(var11 <= var7 || var10 <= var7) {
               var10 += var8 + var8;
               var11 += var8++ + var8;
            }

            var12 = var0 - var8 + 1;
            if(var12 < anInt1093) {
               var12 = anInt1093;
            }

            var13 = var0 + var8;
            if(var13 > anInt1096) {
               var13 = anInt1096;
            }

            var14 = var12 + var6 * anInt1092;

            for(var15 = var12; var15 < var13; ++var15) {
               anIntArray1100[var14++] = 16776960;
            }

            ++var6;
            var10 -= var9-- + var9;
            var11 -= var9 + var9;
         }

         var8 = var2;
         var9 = var6 - var1;
         var11 = var9 * var9 + var7;
         var10 = var11 - var2;

         for(var11 -= var9; var6 < var5; var10 += var9++ + var9) {
            while(var11 > var7 && var10 > var7) {
               var11 -= var8-- + var8;
               var10 -= var8 + var8;
            }

            var12 = var0 - var8;
            if(var12 < anInt1093) {
               var12 = anInt1093;
            }

            var13 = var0 + var8;
            if(var13 > anInt1096 - 1) {
               var13 = anInt1096 - 1;
            }

            var14 = var12 + var6 * anInt1092;

            for(var15 = var12; var15 <= var13; ++var15) {
               anIntArray1100[var14++] = 16776960;
            }

            ++var6;
            var11 += var9 + var9;
         }

      }
   }

   static void method1330(int var0, int var1, int var2, int var4) {
      if(var4 != 0) {
         if(var4 == 256) {
            method1329(var0, var1, var2);
         } else {
            if(var2 < 0) {
               var2 = -var2;
            }

            int var5 = 256 - var4;
            int var6 = (16776960 >> 16 & 255) * var4;
            int var7 = (16776960 >> 8 & 255) * var4;
            int var8 = 0;
            int var12 = var1 - var2;
            if(var12 < anInt1095) {
               var12 = anInt1095;
            }

            int var13 = var1 + var2 + 1;
            if(var13 > anInt1099) {
               var13 = anInt1099;
            }

            int var14 = var12;
            int var15 = var2 * var2;
            int var16 = 0;
            int var17 = var1 - var12;
            int var18 = var17 * var17;
            int var19 = var18 - var17;
            if(var1 > var13) {
               var1 = var13;
            }

            int var9;
            int var10;
            int var11;
            int var21;
            int var20;
            int var23;
            int var22;
            int var24;
            while(var14 < var1) {
               while(var19 <= var15 || var18 <= var15) {
                  var18 += var16 + var16;
                  var19 += var16++ + var16;
               }

               var20 = var0 - var16 + 1;
               if(var20 < anInt1093) {
                  var20 = anInt1093;
               }

               var21 = var0 + var16;
               if(var21 > anInt1096) {
                  var21 = anInt1096;
               }

               var22 = var20 + var14 * anInt1092;

               for(var23 = var20; var23 < var21; ++var23) {
                  var9 = (anIntArray1100[var22] >> 16 & 255) * var5;
                  var10 = (anIntArray1100[var22] >> 8 & 255) * var5;
                  var11 = (anIntArray1100[var22] & 255) * var5;
                  var24 = (var6 + var9 >> 8 << 16) + (var7 + var10 >> 8 << 8) + (var8 + var11 >> 8);
                  anIntArray1100[var22++] = var24;
               }

               ++var14;
               var18 -= var17-- + var17;
               var19 -= var17 + var17;
            }

            var16 = var2;
            var17 = -var17;
            var19 = var17 * var17 + var15;
            var18 = var19 - var2;

            for(var19 -= var17; var14 < var13; var18 += var17++ + var17) {
               while(var19 > var15 && var18 > var15) {
                  var19 -= var16-- + var16;
                  var18 -= var16 + var16;
               }

               var20 = var0 - var16;
               if(var20 < anInt1093) {
                  var20 = anInt1093;
               }

               var21 = var0 + var16;
               if(var21 > anInt1096 - 1) {
                  var21 = anInt1096 - 1;
               }

               var22 = var20 + var14 * anInt1092;

               for(var23 = var20; var23 <= var21; ++var23) {
                  var9 = (anIntArray1100[var22] >> 16 & 255) * var5;
                  var10 = (anIntArray1100[var22] >> 8 & 255) * var5;
                  var11 = (anIntArray1100[var22] & 255) * var5;
                  var24 = (var6 + var9 >> 8 << 16) + (var7 + var10 >> 8 << 8) + (var8 + var11 >> 8);
                  anIntArray1100[var22++] = var24;
               }

               ++var14;
               var19 += var17 + var17;
            }

         }
      }
   }

   static void method1331() {
      anInt1093 = 0;
      anInt1095 = 0;
      anInt1096 = anInt1092;
      anInt1099 = anInt1094;
      method1310();
   }

   static void method1332(int var0, int var1, int[] var3, int[] var4) {
      int var5 = var0 + var1 * anInt1092;

      for(var1 = 0; var1 < var3.length; ++var1) {
         int var6 = var5 + var3[var1];

         for(var0 = -var4[var1]; var0 < 0; ++var0) {
            anIntArray1100[var6++] = 0;
         }

         var5 += anInt1092;
      }

   }

   public static void method1333() {
      anIntArray1100 = null;
      anIntArray1097 = null;
      anIntArray1098 = null;
   }

}
