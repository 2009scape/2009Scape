package org.runite.jagex;

final class Class3_Sub21 extends Class3 {

   static Class155 aClass155_2491;
   int anInt2492;
   static volatile int anInt2493 = -1;
   int anInt2494;
   int anInt2495;
   int anInt2497;
   static RSString aClass94_2498 = RSString.createRSString("(U (X");


   final boolean method393(int var2, int var3) {
      try {

          return var3 >= this.anInt2492 && var3 <= this.anInt2495 && this.anInt2494 <= var2 && var2 <= this.anInt2497;
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "lh.D(" + (byte) -45 + ',' + var2 + ',' + var3 + ')');
      }
   }

   static void method394(int var0, int var1) {
      try {
         Class3_Sub13_Sub23.anInt3285 = var0;
         Class3_Sub18.method383(-32584, 3);
         Class3_Sub18.method383(-32584, 4);
         if(var1 <= 83) {
            method395((Class35)null, -43, -61, -51, 101, -106, -58, true);
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "lh.E(" + var0 + ',' + var1 + ')');
      }
   }

   static void method395(Class35 var0, int var1, int var2, int var3, int var4, int var5, int var6, boolean var7) {
      int var8 = var0.anIntArray627.length;

      int var9;
      int var10;
      int var11;
      int var12;
      int var13;
      for(var9 = 0; var9 < var8; ++var9) {
         var10 = var0.anIntArray627[var9] - Class129_Sub1.anInt2697;
         var11 = var0.anIntArray615[var9] - Class3_Sub28_Sub13.anInt3657;
         var12 = var0.anIntArray618[var9] - Class3_Sub13_Sub30.anInt3363;
         var13 = var12 * var3 + var10 * var4 >> 16;
         var12 = var12 * var4 - var10 * var3 >> 16;
         var10 = var13;
         var13 = var11 * var2 - var12 * var1 >> 16;
         var12 = var11 * var1 + var12 * var2 >> 16;
         if(var12 < 50) {
            return;
         }

         if(var0.anIntArray616 != null) {
            Class35.anIntArray614[var9] = var10;
            Class35.anIntArray630[var9] = var13;
            Class35.anIntArray628[var9] = var12;
         }

         Class35.anIntArray623[var9] = Class51.anInt846 + (var10 << 9) / var12;
         Class35.anIntArray622[var9] = Class51.anInt835 + (var13 << 9) / var12;
      }

      Class51.anInt850 = 0;
      var8 = var0.anIntArray624.length;

      for(var9 = 0; var9 < var8; ++var9) {
         var10 = var0.anIntArray624[var9];
         var11 = var0.anIntArray617[var9];
         var12 = var0.anIntArray613[var9];
         var13 = Class35.anIntArray623[var10];
         int var14 = Class35.anIntArray623[var11];
         int var15 = Class35.anIntArray623[var12];
         int var16 = Class35.anIntArray622[var10];
         int var17 = Class35.anIntArray622[var11];
         int var18 = Class35.anIntArray622[var12];
         if((var13 - var14) * (var18 - var17) - (var16 - var17) * (var15 - var14) > 0) {
            if(Class3_Sub13_Sub21.aBoolean3261 && Class3_Sub13_Sub4.method185(Class49.anInt819 + Class51.anInt846, Class3_Sub13_Sub23_Sub1.anInt4039 + Class51.anInt835, var16, var17, var18, var13, var14, var15)) {
               Class27.anInt515 = var5;
               Class66.anInt999 = var6;
            }

            if(!HDToolKit.highDetail && !var7) {
               Class51.aBoolean849 = var13 < 0 || var14 < 0 || var15 < 0 || var13 > Class51.anInt847 || var14 > Class51.anInt847 || var15 > Class51.anInt847;

               if(var0.anIntArray616 != null && var0.anIntArray616[var9] != -1) {
                  if(Class3_Sub13_Sub22.aBoolean3275) {
                     if(var0.aBoolean629) {
                        Class51.method1135(var16, var17, var18, var13, var14, var15, var0.anIntArray625[var9], var0.anIntArray632[var9], var0.anIntArray631[var9], Class35.anIntArray614[0], Class35.anIntArray614[1], Class35.anIntArray614[3], Class35.anIntArray630[0], Class35.anIntArray630[1], Class35.anIntArray630[3], Class35.anIntArray628[0], Class35.anIntArray628[1], Class35.anIntArray628[3], var0.anIntArray616[var9]);
                     } else {
                        Class51.method1135(var16, var17, var18, var13, var14, var15, var0.anIntArray625[var9], var0.anIntArray632[var9], var0.anIntArray631[var9], Class35.anIntArray614[var10], Class35.anIntArray614[var11], Class35.anIntArray614[var12], Class35.anIntArray630[var10], Class35.anIntArray630[var11], Class35.anIntArray630[var12], Class35.anIntArray628[var10], Class35.anIntArray628[var11], Class35.anIntArray628[var12], var0.anIntArray616[var9]);
                     }
                  } else {
                     int var19 = Class51.anInterface2_838.method15(var0.anIntArray616[var9], '\uffff');
                     Class51.method1154(var16, var17, var18, var13, var14, var15, Class127.method1753(var19, var0.anIntArray625[var9]), Class127.method1753(var19, var0.anIntArray632[var9]), Class127.method1753(var19, var0.anIntArray631[var9]));
                  }
               } else if(var0.anIntArray625[var9] != 12345678) {
                  Class51.method1154(var16, var17, var18, var13, var14, var15, var0.anIntArray625[var9], var0.anIntArray632[var9], var0.anIntArray631[var9]);
               }
            }
         }
      }

   }

   public static void method396(int var0) {
      try {
         if(var0 != 0) {
            method397((byte)35);
         }

         aClass155_2491 = null;
         aClass94_2498 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "lh.B(" + var0 + ')');
      }
   }

   static void method397(byte var0) {
      try {
         Class114.aClass93_1569.method1524();
         Class3_Sub15.aClass93_2428.method1524();
         if(var0 != -41) {
            method394(14, 52);
         }

         Class47.aClass93_743.method1524();
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "lh.C(" + var0 + ')');
      }
   }

   Class3_Sub21(int var1, int var2, int var3, int var4) {
      try {
         this.anInt2497 = var4;
         this.anInt2494 = var2;
         this.anInt2495 = var3;
         this.anInt2492 = var1;
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "lh.<init>(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
      }
   }

}
