package org.runite.client;

import org.rs09.client.config.GameConfig;

final class Class132 {

   static int anInt1734 = 0;
   static RSString[] aClass94Array1739 = new RSString[1000];
   static int anInt1741;


   static void method1798(int var0, Class3_Sub4 var1) {
      try {
         long var2 = 0L;
         int var4 = -1;
         if(var0 <= 17) {
            Class159.anInt1740 = -43;
         }

         int var5 = 0;
         if(var1.anInt2263 == 0) {
            var2 = Class157.method2174(var1.anInt2250, var1.anInt2264, var1.anInt2248);
         }

         int var6 = 0;
         if(var1.anInt2263 == 1) {
            var2 = Unsorted.method1395(var1.anInt2250, var1.anInt2264, var1.anInt2248);
         }

         if(var1.anInt2263 == 2) {
            var2 = Class3_Sub28_Sub5.method557(var1.anInt2250, var1.anInt2264, var1.anInt2248);
         }

         if(var1.anInt2263 == 3) {
            var2 = Class3_Sub2.method104(var1.anInt2250, var1.anInt2264, var1.anInt2248);
         }

         if(var2 != 0L) {
            var4 = Integer.MAX_VALUE & (int)(var2 >>> 32);
            var6 = (int)var2 >> 20 & 3;
            var5 = ((int)var2 & 516214) >> 14;
         }

         var1.anInt2254 = var4;
         var1.anInt2253 = var5;
         var1.anInt2257 = var6;
      } catch (RuntimeException var7) {
         throw ClientErrorException.clientError(var7, "sf.B(" + var0 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   static void method1799(byte var0, CacheIndex var1) {
      try {
         Class3_Sub13_Sub7.aClass153_3098 = var1;
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "sf.C(" + var0 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   static void method1801() {
      try {
         int var1 = Class168.aClass3_Sub28_Sub17_2096.method682(RSString.parse(GameConfig.RCM_TITLE));
         int var2;
         int var3;
         for(var2 = 0; Unsorted.menuOptionCount > var2; ++var2) {
            var3 = Class168.aClass3_Sub28_Sub17_2096.method682(Unsorted.method802(var2));
            if(var3 > var1) {
               var1 = var3;
            }
         }
         var2 = 15 * Unsorted.menuOptionCount + 21;
         int var4 = Class38_Sub1.anInt2612;
         var1 += 8;
         var3 = NPCDefinition.anInt1297 + -(var1 / 2);
         if(Class140_Sub7.anInt2934 < var4 + var2) {
            var4 = Class140_Sub7.anInt2934 + -var2;
         }

         if(Class23.anInt454 < var3 + var1) {
            var3 = -var1 + Class23.anInt454;
         }

         if(var3 < 0) {
            var3 = 0;
         }

         if(var4 < 0) {
            var4 = 0;
         }

         if(Unsorted.anInt3660 == 1) {
            if(Class3_Sub13_Sub39.anInt3460 == NPCDefinition.anInt1297 && Class38_Sub1.anInt2612 == Class168.anInt2099) {
               Class3_Sub28_Sub1.anInt3537 = Unsorted.menuOptionCount * 15 - -(!Unsorted.aBoolean1951?22:26);
               Unsorted.anInt3660 = 0;
               Class3_Sub13_Sub33.anInt3395 = var4;
               AbstractIndexedSprite.anInt1462 = var3;
               Class38_Sub1.aBoolean2615 = true;
               Class3_Sub28_Sub3.anInt3552 = var1;
            }
         } else if(Class163_Sub1.anInt2993 == NPCDefinition.anInt1297 && Class38_Sub1.anInt2614 == Class38_Sub1.anInt2612) {
            AbstractIndexedSprite.anInt1462 = var3;
            Unsorted.anInt3660 = 0;
            Class3_Sub28_Sub3.anInt3552 = var1;
            Class3_Sub13_Sub33.anInt3395 = var4;
            Class3_Sub28_Sub1.anInt3537 = (Unsorted.aBoolean1951?26:22) + Unsorted.menuOptionCount * 15;
            Class38_Sub1.aBoolean2615 = true;
         } else {
            Class168.anInt2099 = Class38_Sub1.anInt2614;
            Class3_Sub13_Sub39.anInt3460 = Class163_Sub1.anInt2993;
            Unsorted.anInt3660 = 1;
         }

      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "sf.D(" + ')');
      }
   }

}
