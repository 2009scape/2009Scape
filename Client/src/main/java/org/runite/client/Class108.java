package org.runite.client;

import java.util.Objects;

final class Class108 {

   static int anInt1460;


   static void method1652(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      try {

         if(Class101.anInt1425 <= var5 && Class3_Sub28_Sub18.anInt3765 >= var5 && var0 >= Class101.anInt1425 && Class3_Sub28_Sub18.anInt3765 >= var0 && Class101.anInt1425 <= var6 && var6 <= Class3_Sub28_Sub18.anInt3765 && var1 >= Class101.anInt1425 && Class3_Sub28_Sub18.anInt3765 >= var1 && var4 >= Class159.anInt2020 && Class57.anInt902 >= var4 && Class159.anInt2020 <= var7 && Class57.anInt902 >= var7 && var2 >= Class159.anInt2020 && var2 <= Class57.anInt902 && Class159.anInt2020 <= var3 && Class57.anInt902 >= var3) {
            Class3_Sub5.method114(var2, var8, var7, var6, var1, var3, var4, var0, var5);
         } else {
            Class95.method1583(var5, var0, var7, var8, var3, var2, var1, var6, var4);
         }

      } catch (RuntimeException var11) {
         throw ClientErrorException.clientError(var11, "oi.C(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + 0 + ')');
      }
   }

   static RSString method1653(int var0) {
      try {

         return RSString.stringCombiner(new RSString[]{RSString.stringAnimator(255 & var0 >> 24), TextCore.aClass94_4023, RSString.stringAnimator((var0 & 16712751) >> 16), TextCore.aClass94_4023, RSString.stringAnimator(255 & var0 >> 8), TextCore.aClass94_4023, RSString.stringAnimator(var0 & 255)});
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "oi.F(" + var0 + ',' + 0 + ')');
      }
   }

//   static void method1654(int var0) { //Separated into method1656 + clearClientCacheMemory
//      try {
//         Class140_Sub4.aReferenceCache_2792.clearSoftReferences();
//      } catch (RuntimeException var2) {
//         throw ClientErrorException.clientError(var2, "oi.E(" + var0 + ')');
//      }
//   }

   static void method1656(CacheIndex var0, byte var1) {
      try {
         if(!Class140_Sub2.aBoolean2713) {
            if(HDToolKit.highDetail) {
               Class22.method932();
            } else {
               Class74.method1320();
            }

            Class40.aAbstractSprite_680 = Class75_Sub2.method1344(var0, Class154.anInt1966);
            int var2 = Class140_Sub7.anInt2934;
            int var3 = var2 * 956 / 503;
            Objects.requireNonNull(Class40.aAbstractSprite_680).method639((Class23.anInt454 + -var3) / 2, 0, var3, var2);
            AnimationDefinition.aClass109_1856 = Class3_Sub28_Sub6.a(Class79.anInt1124, var0);
            Objects.requireNonNull(AnimationDefinition.aClass109_1856).method1667(Class23.anInt454 / 2 + -(AnimationDefinition.aClass109_1856.width / 2), 18);
            Class140_Sub2.aBoolean2713 = true;
            if(var1 > -50) {
               Class140_Sub4.aReferenceCache_2792.clearSoftReferences();
            }

         }
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "oi.B(" + (var0 != null?"{...}":"null") + ',' + var1 + ')');
      }
   }

   static void method1657(int var0) {
      try {
         Class3_Sub28_Sub6 var2 = Class3_Sub24_Sub3.method466(5, var0);
         var2.a();

      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "oi.J(" + var0 + ',' + -903 + ')');
      }
   }

   static void method1658(int[] var1, Object[] var2) {
      try {
         Class25.method956(var2, var1.length - 1, var1, 74, 0);
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "oi.I(" + 21 + ',' + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   static void method1659() {
      try {
         Class61.aReferenceCache_939.clear();
      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "oi.G(" + 16712751 + ')');
      }
   }

   static void method1661(CacheIndex var1, CacheIndex var2) {
      try {
         Sprites.aBoolean337 = true;
         Unsorted.aClass153_1043 = var2;

         Class85.aClass153_1171 = var1;
      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "oi.D(" + 2 + ',' + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ',' + true + ')');
      }
   }

}
