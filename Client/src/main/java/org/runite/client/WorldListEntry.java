package org.runite.client;

import org.rs09.client.config.GameConfig;
import org.rs09.client.rendering.Toolkit;

import java.util.Objects;

final class WorldListEntry extends Class44 {

    public static int anInt2937;
    static WorldListCountry[] countries;
    static AbstractSprite aAbstractSprite_3099;
    static int anInt3351;
    static AbstractSprite aAbstractSprite_1339;
    static AbstractSprite aAbstractSprite_1457;
    static int anInt1400;
    static int anInt739;
    static int anInt1126;
    static int archiveID;
    static int worldListArraySize;
    RSString activity;
   int worldId;
   static boolean aBoolean2623 = true;
   RSString address;
   static int anInt2626 = 20;
   static Class155 aClass155_2627;


   static void method1076() {
      try {
         Class154.aReferenceCache_1964.clear();
      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "ba.C(" + 88 + ')');
      }
   }

   static void parseWorldList(DataBuffer buffer) {
      try {
         int var2 = buffer.getSmart();
         countries = new WorldListCountry[var2];

         int var3;
         for(var3 = 0; var3 < var2; ++var3) {
            countries[var3] = new WorldListCountry();
            countries[var3].flagId = buffer.getSmart();
            countries[var3].name = buffer.getGJString2(105);
         }

         Class53.worldListOffset = buffer.getSmart();
         worldListArraySize = buffer.getSmart();
         Class57.activeWorldListSize = buffer.getSmart();
         Class117.worldList = new WorldListEntry[-Class53.worldListOffset + worldListArraySize + 1];

         for(var3 = 0; var3 < Class57.activeWorldListSize; ++var3) {
            int worldId = buffer.getSmart();
            WorldListEntry var5 = Class117.worldList[worldId] = new WorldListEntry();
            var5.countryIndex = buffer.readUnsignedByte();
            var5.settings = buffer.readInt();
            var5.worldId = worldId - -Class53.worldListOffset;
            var5.activity = buffer.getGJString2(98);
            var5.address = buffer.getGJString2(79);
            GameConfig.WORLD = worldId;
//            GameLaunch.SETTINGS.setWorld(worldId);
            System.out.println("Registering to world: " + GameConfig.WORLD);
         }
         Unsorted.updateStamp = buffer.readInt();
         Class30.loadedWorldList = true;
      } catch (RuntimeException var6) {
         throw ClientErrorException.clientError(var6, "hi.B(" + (buffer != null?"{...}":"null") + ',' + -88 + ')');
      }
   }

    static void buildWorldListInterface() {
        try {
            int var1 = AbstractIndexedSprite.anInt1462;
            int var2 = Class3_Sub13_Sub33.anInt3395;
            int var4 = Class3_Sub28_Sub1.anInt3537;
            int var3 = Class3_Sub28_Sub3.anInt3552;
            if (aAbstractSprite_3099 == null || null == aAbstractSprite_1457) {
                if (CacheIndex.spritesIndex.retrieveSpriteFile(archiveID) && CacheIndex.spritesIndex.retrieveSpriteFile(anInt1400)) {
                    aAbstractSprite_3099 = Unsorted.method562(CacheIndex.spritesIndex, archiveID);
                    aAbstractSprite_1457 = Unsorted.method562(CacheIndex.spritesIndex, anInt1400);
                    if (HDToolKit.highDetail) {
                        if (aAbstractSprite_3099 instanceof Class3_Sub28_Sub16_Sub2_Sub1) {
                            aAbstractSprite_3099 = new Class3_Sub28_Sub16_Sub1_Sub1((Class3_Sub28_Sub16_Sub2) aAbstractSprite_3099);
                        } else {
                            aAbstractSprite_3099 = new HDSprite((Class3_Sub28_Sub16_Sub2) aAbstractSprite_3099);
                        }

                        if (aAbstractSprite_1457 instanceof Class3_Sub28_Sub16_Sub2_Sub1) {
                            aAbstractSprite_1457 = new Class3_Sub28_Sub16_Sub1_Sub1((Class3_Sub28_Sub16_Sub2) aAbstractSprite_1457);
                        } else {
                            aAbstractSprite_1457 = new HDSprite((Class3_Sub28_Sub16_Sub2) aAbstractSprite_1457);
                        }
                    }
                } else {
                    Toolkit.getActiveToolkit().fillRect(var1, var2, var3, 20, InterfaceWidget.anInt3600, -Unsorted.anInt963 + 256);
                }
            }

            int var5;
            int var6;
            if (aAbstractSprite_3099 != null && aAbstractSprite_1457 != null) {
                var5 = var3 / aAbstractSprite_3099.anInt3707;

                for (var6 = 0; var6 < var5; ++var6) {
                    aAbstractSprite_3099.method643(var6 * aAbstractSprite_3099.anInt3707 + var1, var2);
                }

                aAbstractSprite_1457.method643(var1, var2);
                aAbstractSprite_1457.method641(-aAbstractSprite_1457.anInt3707 + (var1 - -var3), var2);
            }

            Class168.aClass3_Sub28_Sub17_2096.method681(RSString.parse(GameConfig.RCM_TITLE), var1 - -3, 14 + var2, anInt3351, -1);
            Toolkit.getActiveToolkit().fillRect(var1, 20 + var2, var3, var4 - 20, InterfaceWidget.anInt3600, -Unsorted.anInt963 + 256);

            var6 = Unsorted.anInt1709;
            var5 = Class126.anInt1676;
            int var7;
            int var8;
            for (var7 = 0; Unsorted.menuOptionCount > var7; ++var7) {
                var8 = (-var7 + Unsorted.menuOptionCount - 1) * 15 + var2 + 35;
                if (var1 < var5 && var5 < var1 - -var3 && -13 + var8 < var6 && var8 + 3 > var6) {
                    Toolkit.getActiveToolkit().fillRect(var1, var8 - 13, var3, 16, MouseListeningClass.anInt1926, -Class136.anInt1771 + 256);
                }
            }

            if ((aAbstractSprite_1339 == null || Class50.aAbstractSprite_824 == null || null == Class3_Sub26.aAbstractSprite_2560) && CacheIndex.spritesIndex.retrieveSpriteFile(anInt739) && CacheIndex.spritesIndex.retrieveSpriteFile(anInt1126) && CacheIndex.spritesIndex.retrieveSpriteFile(anInt2937)) {
                aAbstractSprite_1339 = Unsorted.method562(CacheIndex.spritesIndex, anInt739);
                Class50.aAbstractSprite_824 = Unsorted.method562(CacheIndex.spritesIndex, anInt1126);
                Class3_Sub26.aAbstractSprite_2560 = Unsorted.method562(CacheIndex.spritesIndex, anInt2937);
                if (HDToolKit.highDetail) {
                    if (aAbstractSprite_1339 instanceof Class3_Sub28_Sub16_Sub2_Sub1) {
                        aAbstractSprite_1339 = new Class3_Sub28_Sub16_Sub1_Sub1((Class3_Sub28_Sub16_Sub2) aAbstractSprite_1339);
                    } else {
                        aAbstractSprite_1339 = new HDSprite((Class3_Sub28_Sub16_Sub2) aAbstractSprite_1339);
                    }

                    if (Class50.aAbstractSprite_824 instanceof Class3_Sub28_Sub16_Sub2_Sub1) {
                        Class50.aAbstractSprite_824 = new Class3_Sub28_Sub16_Sub1_Sub1((Class3_Sub28_Sub16_Sub2) Class50.aAbstractSprite_824);
                    } else {
                        Class50.aAbstractSprite_824 = new HDSprite((Class3_Sub28_Sub16_Sub2) Class50.aAbstractSprite_824);
                    }

                    if (Class3_Sub26.aAbstractSprite_2560 instanceof Class3_Sub28_Sub16_Sub2_Sub1) {
                        Class3_Sub26.aAbstractSprite_2560 = new Class3_Sub28_Sub16_Sub1_Sub1((Class3_Sub28_Sub16_Sub2) Class3_Sub26.aAbstractSprite_2560);
                    } else {
                        Class3_Sub26.aAbstractSprite_2560 = new HDSprite((Class3_Sub28_Sub16_Sub2) Class3_Sub26.aAbstractSprite_2560);
                    }
                }
            }

            int var9;
            if (aAbstractSprite_1339 != null && null != Class50.aAbstractSprite_824 && null != Class3_Sub26.aAbstractSprite_2560) {
                var7 = var3 / aAbstractSprite_1339.anInt3707;

                for (var8 = 0; var7 > var8; ++var8) {
                    aAbstractSprite_1339.method643(var1 + aAbstractSprite_1339.anInt3707 * var8, var4 + var2 + -aAbstractSprite_1339.anInt3696);
                }

                var8 = (-20 + var4) / Class50.aAbstractSprite_824.anInt3696;

                for (var9 = 0; var9 < var8; ++var9) {
                    Class50.aAbstractSprite_824.method643(var1, var2 + 20 + var9 * Class50.aAbstractSprite_824.anInt3696);
                    Class50.aAbstractSprite_824.method641(var1 - (-var3 - -Class50.aAbstractSprite_824.anInt3707), var2 + 20 + var9 * Class50.aAbstractSprite_824.anInt3696);
                }

                Class3_Sub26.aAbstractSprite_2560.method643(var1, var4 + (var2 - Class3_Sub26.aAbstractSprite_2560.anInt3696));
                Class3_Sub26.aAbstractSprite_2560.method641(var1 + var3 - Class3_Sub26.aAbstractSprite_2560.anInt3707, var2 - -var4 + -Class3_Sub26.aAbstractSprite_2560.anInt3696);
            }

            for (var7 = 0; var7 < Unsorted.menuOptionCount; ++var7) {
                var8 = 15 * (Unsorted.menuOptionCount - 1 + -var7) + var2 + 35;
                var9 = anInt3351;
                if (var1 < var5 && var3 + var1 > var5 && var6 > var8 - 13 && var8 - -3 > var6) {
                    var9 = Class154.anInt1957;
                }

                Class168.aClass3_Sub28_Sub17_2096.method681(Unsorted.method802(var7), 3 + var1, var8, var9, 0);
            }

            Unsorted.method1282(AbstractIndexedSprite.anInt1462, (byte) 122, Class3_Sub13_Sub33.anInt3395, Class3_Sub28_Sub1.anInt3537, Class3_Sub28_Sub3.anInt3552);
        } catch (RuntimeException var10) {
            throw ClientErrorException.clientError(var10, "ij.F("+')');
        }
    }

    final WorldListCountry method1078(int var1) {
      try {
         return countries[this.countryIndex];
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "ba.B(" + var1 + ')');
      }
   }

   static int method1079(int var0) {
      try {
         if(0 > var0) {
            return 0;
         } else {
            Class3_Sub25 var2 = (Class3_Sub25)Class3_Sub2.aHashTable_2220.get((long)var0);
            if(var2 == null) {
               return Objects.requireNonNull(Unsorted.method2069(var0)).size;
            } else {
               int var3 = 0;

               for(int var4 = 0; var4 < var2.anIntArray2547.length; ++var4) {
                  if(var2.anIntArray2547[var4] == -1) {
                     ++var3;
                  }
               }

               var3 += Objects.requireNonNull(Unsorted.method2069(var0)).size + -var2.anIntArray2547.length;
               return var3;
            }
         }
      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "ba.D(" + var0 + ',' + (byte) -80 + ')');
      }
   }

}
