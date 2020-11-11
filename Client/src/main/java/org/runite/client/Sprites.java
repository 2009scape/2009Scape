package org.runite.client;

import java.util.Objects;

final class Sprites {

   static boolean aBoolean337;
   static int anInt340 = 127;
   static int p11FullSpriteArchive;
   static int p12FullSpriteArchive;
   static int b12FullSpriteArchive;
   static int mapFunctionSpriteArchive;
   static int hitmarkSpriteArchive;
   static int hitbarDefaultSpriteArchive;
   static int headiconsPkSpriteArchive;
   static int headiconsPrayerSpriteArchive;
   static int headiconsHintSpriteArchive;
   static int hintMapMarkersSpriteArchive;
   static int mapFlagSpriteArchive;
   static int crossSpriteArchive;
   static int mapDotsSpriteArchive;
   static int scrollbarSpriteArchive;
   static int nameIconsSpriteArchive;
   static int floorShadowSpriteArchive;
   public static int compassSpriteArchive;
   public static int hintMapEdgeSpriteArchive;
    static Class3_Sub28_Sub16_Sub2[] aClass3_Sub28_Sub16_Sub2Array2140;


    private static LDIndexedSprite[] method885(int var1, CacheIndex var2) {
      try {
       //  System.out.println("Class 14 " + var1);
         return !Class75_Sub4.method1351(var2, 0, var1, -30901)?null: Unsorted.method1281();
      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "cg.C(" + true + ',' + var1 + ',' + (var2 != null?"{...}":"null") + ',' + 0 + ')');
      }
   }

   static void method887(CacheIndex index8) {
      try {
         aClass3_Sub28_Sub16_Sub2Array2140 = Class157.method2176(mapFunctionSpriteArchive, index8);
         Class75_Sub3.aAbstractSpriteArray2656 = Class140_Sub6.getSprites(hitmarkSpriteArchive, index8);
         Unsorted.aAbstractSpriteArray996 = Class140_Sub6.getSprites(hitbarDefaultSpriteArchive, index8);
         Class3_Sub13_Sub31.aAbstractSpriteArray3373 = Class140_Sub6.getSprites(headiconsPkSpriteArchive, index8);
         NPC.aAbstractSpriteArray3977 = Class140_Sub6.getSprites(headiconsPrayerSpriteArchive, index8);
         Class166.aAbstractSpriteArray2072 = Class140_Sub6.getSprites(headiconsHintSpriteArchive, index8);
         Class129_Sub1.aAbstractSpriteArray2690 = Class140_Sub6.getSprites(hintMapMarkersSpriteArchive, index8);
         Class45.aAbstractSprite_736 = Unsorted.method602(mapFlagSpriteArchive, index8);
         Class65.aAbstractSpriteArray1825 = Class3_Sub13_Sub23_Sub1.method286(crossSpriteArchive, index8);
         Unsorted.aAbstractSpriteArray1136 = Class3_Sub13_Sub23_Sub1.method286(mapDotsSpriteArchive, index8);
         GameObject.aClass109Array1831 = Class85.method1424(index8, scrollbarSpriteArchive);
         Class3_Sub13_Sub22.aClass109Array3270 = Class85.method1424(index8, nameIconsSpriteArchive);
         Class3_Sub13.aClass3_Sub28_Sub17_2379.method697(Class3_Sub13_Sub22.aClass109Array3270, (int[])null);
         Class126.aClass3_Sub28_Sub17_1669.method697(Class3_Sub13_Sub22.aClass109Array3270, (int[])null);
         Class168.aClass3_Sub28_Sub17_2096.method697(Class3_Sub13_Sub22.aClass109Array3270, (int[])null);
         if(HDToolKit.highDetail) {
            Class141.aClass109_Sub1Array1843 = method885(floorShadowSpriteArchive, index8);

            for(int var2 = 0; var2 < Objects.requireNonNull(Class141.aClass109_Sub1Array1843).length; ++var2) {
               Class141.aClass109_Sub1Array1843[var2].method1675();
            }
         }

         Class3_Sub28_Sub16_Sub2 var10 = Class40.method1043(0, index8, compassSpriteArchive);
         Objects.requireNonNull(var10).method665();
         if(HDToolKit.highDetail) {
            Class57.aAbstractSprite_895 = new HDSprite(var10);
         } else {
            Class57.aAbstractSprite_895 = var10;
         }

         Class3_Sub28_Sub16_Sub2[] var3 = Class157.method2176(hintMapEdgeSpriteArchive, index8);

         int var4;
         for(var4 = 0; Objects.requireNonNull(var3).length > var4; ++var4) {
            var3[var4].method665();
         }

         if(HDToolKit.highDetail) {
            Class3_Sub13_Sub39.aAbstractSpriteArray3458 = new AbstractSprite[var3.length];

            for(var4 = 0; var4 < var3.length; ++var4) {
               Class3_Sub13_Sub39.aAbstractSpriteArray3458[var4] = new HDSprite(var3[var4]);
            }
         } else {
            Class3_Sub13_Sub39.aAbstractSpriteArray3458 = var3;
         }

         int var5 = (int)((double) 21 * Math.random()) - 10;
         var4 = (int)(21.0D * Math.random()) - 10;
         int var6 = -10 + (int)(21.0D * Math.random());
         int var7 = -20 + (int)(Math.random() * 41.0D);

         int var8;
         for(var8 = 0; var8 < aClass3_Sub28_Sub16_Sub2Array2140.length; ++var8) {
            aClass3_Sub28_Sub16_Sub2Array2140[var8].method669(var4 + var7, var7 + var5, var7 + var6);
         }

         if(HDToolKit.highDetail) {
            Class140_Sub4.aAbstractSpriteArray2839 = new AbstractSprite[aClass3_Sub28_Sub16_Sub2Array2140.length];

            for(var8 = 0; var8 < aClass3_Sub28_Sub16_Sub2Array2140.length; ++var8) {
               Class140_Sub4.aAbstractSpriteArray2839[var8] = new HDSprite(aClass3_Sub28_Sub16_Sub2Array2140[var8]);
            }
         } else {
            Class140_Sub4.aAbstractSpriteArray2839 = aClass3_Sub28_Sub16_Sub2Array2140;
         }

      } catch (RuntimeException var9) {
         throw ClientErrorException.clientError(var9, "cg.A(" + 21 + ',' + (index8 != null?"{...}":"null") + ')');
      }
   }

   static void getSpriteFromArchive(CacheIndex index8) {
      try {
            p11FullSpriteArchive = index8.getArchiveForName(RSString.parse("p11_full"));
            p12FullSpriteArchive = index8.getArchiveForName(RSString.parse("p12_full"));
            b12FullSpriteArchive = index8.getArchiveForName(RSString.parse("b12_full"));
            mapFunctionSpriteArchive = index8.getArchiveForName(RSString.parse("mapfunction"));
            hitmarkSpriteArchive = index8.getArchiveForName(RSString.parse("hitmarks"));
            hitbarDefaultSpriteArchive = index8.getArchiveForName(RSString.parse("hitbar_default"));
            headiconsPkSpriteArchive = index8.getArchiveForName(RSString.parse("headicons_pk"));

            headiconsPrayerSpriteArchive = index8.getArchiveForName(RSString.parse("headicons_prayer"));
            headiconsHintSpriteArchive = index8.getArchiveForName(RSString.parse("hint_headicons"));
            hintMapMarkersSpriteArchive = index8.getArchiveForName(RSString.parse("hint_mapmarkers"));
            mapFlagSpriteArchive = index8.getArchiveForName(RSString.parse("mapflag"));
            crossSpriteArchive = index8.getArchiveForName(RSString.parse("cross"));
            mapDotsSpriteArchive = index8.getArchiveForName(RSString.parse("mapdots"));
            scrollbarSpriteArchive = index8.getArchiveForName(RSString.parse("scrollbar"));
            nameIconsSpriteArchive = index8.getArchiveForName(RSString.parse("name_icons"));
            floorShadowSpriteArchive = index8.getArchiveForName(RSString.parse("floorshadows"));
            compassSpriteArchive = index8.getArchiveForName(RSString.parse("compass"));
            hintMapEdgeSpriteArchive = index8.getArchiveForName(RSString.parse("hint_mapedge"));
      } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "g.C(" + 208 + ',' + (index8 != null?"{...}":"null") + ')');
      }
   }

   static int method228(CacheIndex var0, CacheIndex var1) {
       try {
           int var3 = 0;
           if (var0.retrieveSpriteFile(p11FullSpriteArchive)) {
               ++var3;
           }

           if (var0.retrieveSpriteFile(p12FullSpriteArchive)) {
               ++var3;
           }

           if (var0.retrieveSpriteFile(b12FullSpriteArchive)) {
               ++var3;
           }

           if (var1.retrieveSpriteFile(p11FullSpriteArchive)) {
               ++var3;
           }


           if (var1.retrieveSpriteFile(p12FullSpriteArchive)) {
               ++var3;
           }

           if (var1.retrieveSpriteFile(b12FullSpriteArchive)) {
               ++var3;
           }

           return var3;
       } catch (RuntimeException var4) {
           throw ClientErrorException.clientError(var4, "fn.B(" + (var0 != null ? "{...}" : "null") + ',' + (var1 != null ? "{...}" : "null") + ')');
       }
   }

   static int method107(CacheIndex index) {
      try {
            int var2 = 0;
            if(index.retrieveSpriteFile(mapFunctionSpriteArchive)) {
               ++var2;
            }

            if(index.retrieveSpriteFile(hitmarkSpriteArchive)) {
               ++var2;
            }

            if(index.retrieveSpriteFile(hitbarDefaultSpriteArchive)) {
               ++var2;
            }

            if(index.retrieveSpriteFile(headiconsPkSpriteArchive)) {
               ++var2;
            }

            if(index.retrieveSpriteFile(headiconsPrayerSpriteArchive)) {
               ++var2;
            }

            if(index.retrieveSpriteFile(headiconsHintSpriteArchive)) {
               ++var2;
            }

            if(index.retrieveSpriteFile(hintMapMarkersSpriteArchive)) {
               ++var2;
            }

            if(index.retrieveSpriteFile(mapFlagSpriteArchive)) {
               ++var2;
            }

            if(index.retrieveSpriteFile(crossSpriteArchive)) {
               ++var2;
            }

            if(index.retrieveSpriteFile(mapDotsSpriteArchive)) {
               ++var2;
            }

            if(index.retrieveSpriteFile(scrollbarSpriteArchive)) {
               ++var2;
            }

            if(index.retrieveSpriteFile(nameIconsSpriteArchive)) {
               ++var2;
            }

            if(index.retrieveSpriteFile(floorShadowSpriteArchive)) {
               ++var2;
            }

            if(index.retrieveSpriteFile(compassSpriteArchive)) {
               ++var2;
            }

            if(index.retrieveSpriteFile(hintMapEdgeSpriteArchive)) {
               ++var2;
            }

            return var2;
      } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "cd.B(" + (index != null?"{...}":"null") + ',' + (byte) -125 + ')');
      }
   }
}
