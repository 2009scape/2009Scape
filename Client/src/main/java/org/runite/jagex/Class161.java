package org.runite.jagex;

final class Class161 {

   static RSString aClass94_2025 = RSString.createRSString(")2");
   static int[] anIntArray2026;
   static int anInt2027 = -1;
   static int anInt2028 = 0;
   static RSString aClass94_2029 = RSString.createRSString("l");
   static RSByteBuffer aClass3_Sub30_2030 = new RSByteBuffer(new byte[5000]);
   static RSString aClass94_2033 = RSString.createRSString("Memory after cleanup=");
   static Class33 aClass33_2034;
   static RSString aClass94_2035 = null;

   static void method2200(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      try {
         int var8 = 0;
         int var9 = var6;
         int var10 = 0;
         int var11 = var4 + -var7;
         int var13 = var4 * var4;
         int var12 = var6 + -var7;
         int var14 = var6 * var6;
         int var16 = var12 * var12;
         int var17 = var14 << 1;
         int var18 = var13 << 1;
         int var19 = var16 << 1;
         int var15 = var11 * var11;
         int var20 = var15 << 1;
         int var21 = var6 << 1;
         int var22 = var12 << 1;
         int var24 = -((-1 + var21) * var18) + var14;
         int var25 = var15 * (-var22 + 1) - -var19;
         int var23 = (-var21 + 1) * var13 + var17;
         int var27 = var13 << 2;
         int var26 = -(var20 * (var22 - 1)) + var16;
         int var29 = var15 << 2;
         int var28 = var14 << 2;
         int var30 = var16 << 2;
         int var32 = (-3 + var21) * var18;
         int var31 = var17 * 3;
         int var34 = (-3 + var22) * var20;
         int var33 = var19 * 3;
         int var35 = var28;
         int var37 = var30;
         int var38 = (-1 + var12) * var29;
         if(var5 < 73) {
            method2200(108, -88, 76, -36, -95, -64, -84, 85);
         }

         int var36 = (-1 + var6) * var27;
         int[] var39 = Class38.anIntArrayArray663[var2];
         Class3_Sub13_Sub23_Sub1.method282(var39, -var4 + var1, -87, -var11 + var1, var3);
         Class3_Sub13_Sub23_Sub1.method282(var39, -var11 + var1, -66, var1 - -var11, var0);
         Class3_Sub13_Sub23_Sub1.method282(var39, var1 - -var11, -68, var4 + var1, var3);

         while(var9 > 0) {
            if(var23 < 0) {
               while(0 > var23) {
                  var24 += var35;
                  var23 += var31;
                  ++var8;
                  var35 += var28;
                  var31 += var28;
               }
            }

            if(var24 < 0) {
               var23 += var31;
               ++var8;
               var31 += var28;
               var24 += var35;
               var35 += var28;
            }

            boolean var40 = var9 <= var12;
            if(var40) {
               if(var25 < 0) {
                  while(var25 < 0) {
                     var25 += var33;
                     var33 += var30;
                     ++var10;
                     var26 += var37;
                     var37 += var30;
                  }
               }

               if(0 > var26) {
                  var25 += var33;
                  ++var10;
                  var33 += var30;
                  var26 += var37;
                  var37 += var30;
               }

               var26 += -var34;
               var34 -= var29;
               var25 += -var38;
               var38 -= var29;
            }

            var23 += -var36;
            var24 += -var32;
            var32 -= var27;
            --var9;
            int var41 = var2 - var9;
            int var43 = var8 + var1;
            var36 -= var27;
            int var42 = var9 + var2;
            int var44 = -var8 + var1;
            if(var40) {
               int var45 = var1 + var10;
               int var46 = -var10 + var1;
               Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var41], var44, -93, var46, var3);
               Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var41], var46, 121, var45, var0);
               Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var41], var45, -47, var43, var3);
               Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var42], var44, 124, var46, var3);
               Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var42], var46, -73, var45, var0);
               Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var42], var45, 111, var43, var3);
            } else {
               Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var41], var44, 96, var43, var3);
               Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var42], var44, 124, var43, var3);
            }
         }

      } catch (RuntimeException var47) {
         throw Class44.clientError(var47, "wb.A(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
      }
   }

   static int method2201(WorldListEntry var0, int var1, int var2, WorldListEntry var3, boolean var4) {
      try {
         if(var1 == 1) {
            int var5 = var0.anInt722;
            int var6 = var3.anInt722;
            if(!var4) {
               if(-1 == var6) {
                  var6 = 2001;
               }

               if(var5 == -1) {
                  var5 = 2001;
               }
            }

            return -var6 + var5;
         } else {
            return 2 == var1?var0.method1078(102).name.method1546((byte)-67, var3.method1078(-119).name):(var1 == 3 ?(var0.activity.method1528(Class62.aClass94_946)?(var3.activity.method1528(Class62.aClass94_946)?0:(var4?-1:1)):(var3.activity.method1528(Class62.aClass94_946)?(var4?1:-1):var0.activity.method1546((byte)-100, var3.activity))):(var2 <= 67?36:(var1 == 4?(!var0.method1074(-114)?(!var3.method1074(-123)?0:-1):(var3.method1074(-108)?0:1)):(var1 == 5?(var0.method1075()?(!var3.method1075()?1:0):(var3.method1075()?-1:0)):(var1 != 6 ?(7 == var1?(var0.method1070(8)?(var3.method1070(8)?0:1):(var3.method1070(8)?-1:0)):-var3.worldId + var0.worldId):(var0.method1072()?(!var3.method1072()?1:0):(var3.method1072()?-1:0)))))));
         }
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "wb.C(" + (var0 != null?"{...}":"null") + ',' + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + var4 + ')');
      }
   }

   public static void method2202(int var0) {
      try {
         aClass94_2025 = null;
         aClass94_2033 = null;
         aClass3_Sub30_2030 = null;
         anIntArray2026 = null;
         aClass33_2034 = null;
         aClass94_2029 = null;
         aClass94_2035 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "wb.B(" + var0 + ')');
      }
   }

}
