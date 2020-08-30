package org.runite.jagex;

final class Class107 {

   static int currentChunkY;
   static RSInterface aClass11_1453;
   static CacheIndex aClass153_878;


   static void method1645(CacheIndex var0, CacheIndex var1) {
      try {
         KeyboardListener.aClass153_1916 = var1;
         int var4 = (int)(21.0D * Math.random()) - 10;
         aClass153_878 = var0;
         int var5 = (int)(21.0D * Math.random()) - 10;
         aClass153_878.getFileAmount(34, (byte)103);
         int var3 = (int)(Math.random() * 21.0D) + -10;
         int var6 = -20 + (int)(41.0D * Math.random());
         Class158.anInt2015 = var6 + var5;
         Class46.anInt740 = var4 + var6;
         Class102.anInt2136 = var6 + var3;
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "og.F(" + (var0 != null?"{...}":"null") + ',' + (var1 != null?"{...}":"null") + ',' + (byte) -67 + ')');
      }
   }

   public static void method1646(boolean var0) {
      try {
         ItemDefinition.stringArguments = null;
         aClass11_1453 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "og.E(" + var0 + ')');
      }
   }

   static void method1647(int var1, int var2, Class140_Sub4 var3, int var4, int var5, int var6) {
      try {

         Class118.method1724(var6, var2, var3.anInt2829, var5, var1, var3.anInt2819, (byte)-85, var4);
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "og.A(" + (byte) 122 + ',' + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + var4 + ',' + var5 + ',' + var6 + ')');
      }
   }

   static void method1648(CacheIndex var0) {
      try {
         Class101.aClass153_1420 = var0;

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "og.B(" + (var0 != null?"{...}":"null") + ',' + 255 + ')');
      }
   }

   static void method1649(int var0, int var1) {
      try {
         if(var1 <= -65) {
            Class3_Sub28_Sub6 var2 = Class3_Sub24_Sub3.method466(10, var0);
            var2.a();
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "og.C(" + var0 + ',' + var1 + ')');
      }
   }

   static void method1650() {
      try {
         Class61.aClass93_939.method1523((byte)-113);

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "og.G(" + 21 + ')');
      }
   }

   static int method1651(int var1, int var2) {
      try {
         int var3;
         if(var1 > var2) {
            var3 = var2;
            var2 = var1;
            var1 = var3;
         }

         while(var1 != 0) {
            var3 = var2 % var1;
            var2 = var1;
            var1 = var3;
         }

         return var2;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "og.D(" + 19067 + ',' + var1 + ',' + var2 + ')');
      }
   }

}
