package org.runite.jagex;

final class Class14 {

   static int anInt336;
   static boolean aBoolean337;
   static RSString aClass94_338 = RSString.createRSString("Ablegen");
   static RSString aClass94_339 = RSString.createRSString("1");
   static int anInt340 = 127;
   static RSString aClass94_341 = RSString.createRSString(")3");


   private static final LDIndexedSprite[] method885(boolean var0, int var1, CacheIndex var2, int var3) {
      try {
         if(!var0) {
            aClass94_338 = null;
         }
         return !Class75_Sub4.method1351(var2, var3, var1, -30901)?null:Class69.method1281(0);
      } catch (RuntimeException var5) {
         throw Class44.method1067(var5, "cg.C(" + var0 + ',' + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

   public static void method886(byte var0) {
      try {
         if(var0 < 26) {
            aClass94_339 = null;
         }

         aClass94_338 = null;
         aClass94_339 = null;
         aClass94_341 = null;
      } catch (RuntimeException var2) {
         throw Class44.method1067(var2, "cg.B(" + var0 + ')');
      }
   }

   static final void method887(int var0, CacheIndex var1) {
      try {
         Class102.aClass3_Sub28_Sub16_Sub2Array2140 = Class157.method2176(0, 32767, Class168.anInt2104, var1);
         Class75_Sub3.aClass3_Sub28_Sub16Array2656 = Class140_Sub6.getSprites(0, (byte)11, Class3_Sub13_Sub23_Sub1.hitMarkIndex, var1);
         Class66.aClass3_Sub28_Sub16Array996 = Class140_Sub6.getSprites(0, (byte)11, Client.anInt2195, var1);
         Class3_Sub13_Sub31.aClass3_Sub28_Sub16Array3373 = Class140_Sub6.getSprites(0, (byte)11, Node.anInt2575, var1);
         NPC.aClass3_Sub28_Sub16Array3977 = Class140_Sub6.getSprites(0, (byte)11, RenderAnimationDefinition.anInt380, var1);
         Class166.aClass3_Sub28_Sub16Array2072 = Class140_Sub6.getSprites(0, (byte)11, Class3_Sub13_Sub29.anInt3356, var1);
         Class129_Sub1.aClass3_Sub28_Sub16Array2690 = Class140_Sub6.getSprites(0, (byte)11, Class129_Sub1.anInt2689, var1);
         Class45.aClass3_Sub28_Sub16_736 = Class3_Sub28_Sub11.method602(0, Class3_Sub13_Sub4.anInt3061, (byte)-18, var1);
         Class139.aClass3_Sub28_Sub16Array1825 = Class3_Sub13_Sub23_Sub1.method286(var0 ^ -22, 0, Class75_Sub1.anInt2633, var1);
         Class80.aClass3_Sub28_Sub16Array1136 = Class3_Sub13_Sub23_Sub1.method286(-1, 0, Class40.anInt678, var1);
         GameObject.aClass109Array1831 = Class85.method1424(var1, (byte)-12, 0, Class3_Sub15.anInt2436);
         Class3_Sub13_Sub22.aClass109Array3270 = Class85.method1424(var1, (byte)-12, 0, Class3_Sub28_Sub18.anInt3757);
         Class3_Sub13.aClass3_Sub28_Sub17_2379.method697(Class3_Sub13_Sub22.aClass109Array3270, (int[])null);
         Class126.aClass3_Sub28_Sub17_1669.method697(Class3_Sub13_Sub22.aClass109Array3270, (int[])null);
         Class168.aClass3_Sub28_Sub17_2096.method697(Class3_Sub13_Sub22.aClass109Array3270, (int[])null);
         if(HDToolKit.highDetail) {
            Class141.aClass109_Sub1Array1843 = method885(true, Class45.anInt735, var1, 0);

            for(int var2 = 0; ~Class141.aClass109_Sub1Array1843.length < ~var2; ++var2) {
               Class141.aClass109_Sub1Array1843[var2].method1675();
            }
         }

         Class3_Sub28_Sub16_Sub2 var10 = Class40.method1043(0, var1, var0 + -3199, Class93.anInt1325);
         var10.method665();
         if(HDToolKit.highDetail) {
            Class57.aClass3_Sub28_Sub16_895 = new Class3_Sub28_Sub16_Sub1(var10);
         } else {
            Class57.aClass3_Sub28_Sub16_895 = var10;
         }

         Class3_Sub28_Sub16_Sub2[] var3 = Class157.method2176(0, 32767, Class3_Sub18.anInt2471, var1);

         int var4;
         for(var4 = 0; ~var4 > ~var3.length; ++var4) {
            var3[var4].method665();
         }

         if(!HDToolKit.highDetail) {
            Class3_Sub13_Sub39.aClass3_Sub28_Sub16Array3458 = var3;
         } else {
            Class3_Sub13_Sub39.aClass3_Sub28_Sub16Array3458 = new Class3_Sub28_Sub16[var3.length];

            for(var4 = 0; var4 < var3.length; ++var4) {
               Class3_Sub13_Sub39.aClass3_Sub28_Sub16Array3458[var4] = new Class3_Sub28_Sub16_Sub1(var3[var4]);
            }
         }

         int var5 = (int)((double)var0 * Math.random()) - 10;
         var4 = (int)(21.0D * Math.random()) - 10;
         int var6 = -10 + (int)(21.0D * Math.random());
         int var7 = -20 + (int)(Math.random() * 41.0D);

         int var8;
         for(var8 = 0; var8 < Class102.aClass3_Sub28_Sub16_Sub2Array2140.length; ++var8) {
            Class102.aClass3_Sub28_Sub16_Sub2Array2140[var8].method669(var4 + var7, var7 + var5, var7 + var6);
         }

         if(!HDToolKit.highDetail) {
            Class140_Sub4.aClass3_Sub28_Sub16Array2839 = Class102.aClass3_Sub28_Sub16_Sub2Array2140;
         } else {
            Class140_Sub4.aClass3_Sub28_Sub16Array2839 = new Class3_Sub28_Sub16[Class102.aClass3_Sub28_Sub16_Sub2Array2140.length];

            for(var8 = 0; ~Class102.aClass3_Sub28_Sub16_Sub2Array2140.length < ~var8; ++var8) {
               Class140_Sub4.aClass3_Sub28_Sub16Array2839[var8] = new Class3_Sub28_Sub16_Sub1(Class102.aClass3_Sub28_Sub16_Sub2Array2140[var8]);
            }
         }

      } catch (RuntimeException var9) {
         throw Class44.method1067(var9, "cg.A(" + var0 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

}
