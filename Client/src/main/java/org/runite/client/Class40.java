package org.runite.client;

import org.rs09.client.config.GameConfig;

final class Class40 {

   static RSString aClass94_672 = RSString.parse("null");
    static int[] anIntArray675 = new int[]{16, 32, 64, 128};
    static int anInt677 = 0;
    static CacheIndex aClass153_679;
   static AbstractSprite aAbstractSprite_680;
    static byte[][] aByteArrayArray3669;
    static byte[][] aByteArrayArray3057;
    static int anInt3293 = 0;


    static int method1040(int var0, int var1, int var3) {
      try {
         return var1 < var3 ?var3: var1 > var0 ?var0:var1;
      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "gd.C(" + var0 + ',' + var1 + ',' + (byte) 0 + ',' + var3 + ')');
      }
   }

   static void method1041(long var0, RSString name) {
      try {
    	 // System.out.println("Class 40 " + var0 + ", " + var2 + ", " + name.toString());
         Class3_Sub13_Sub1.outgoingBuffer.index = 0;
         Class3_Sub13_Sub1.outgoingBuffer.writeByte(186);
         Class3_Sub13_Sub1.outgoingBuffer.writeString(name);
        // Class3_Sub13_Sub1.outgoingBuffer.putLong(var0, var2 + -2037463204);
         Unsorted.registryStage = 1;
         Class132.anInt1734 = 0;
         GraphicDefinition.anInt548 = 0;
         Unsorted.anInt1711 = -3;
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "gd.D(" + var0 + ',' + -28236 + ')');
      }
   }

    static Class3_Sub28_Sub16_Sub2 method1043(int var0, CacheIndex var1, int archiveId) {
      try {
         // System.out.println("Class 40 " + archiveId);
         return Class75_Sub4.method1351(var1, var0, archiveId, -30901)?Class117.method1722(-53):null;
      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "gd.G(" + var0 + ',' + (var1 != null?"{...}":"null") + ',' + -3178 + ',' + archiveId + ')');
      }
   }

//   static void method1044() {
//      try {
//         CS2Script.aReferenceCache_2450.clearSoftReferences();
//      } catch (RuntimeException var2) {
//         throw ClientErrorException.clientError(var2, "gd.E(" + ')');
//      }
//   }

   static void method1045() {
      try {

         Class128.aReferenceCache_1683.clear();
      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "gd.B(" + -19761 + ')');
      }
   }

   static void method1046() {
      try {
         Class163_Sub1.method2210(false);
         anInt3293 = 0;
         boolean var1 = true;

         int var2;
         for(var2 = 0; Class164_Sub2.aByteArrayArray3027.length > var2; ++var2) {
            if(Client.anIntArray2200[var2] != -1 && null == Class164_Sub2.aByteArrayArray3027[var2]) {
               Class164_Sub2.aByteArrayArray3027[var2] = CacheIndex.landscapesIndex.getFile(Client.anIntArray2200[var2], 0);
               if(Class164_Sub2.aByteArrayArray3027[var2] == null) {
                  ++anInt3293;
                  var1 = false;
               }
            }

            if(-1 != Class101.anIntArray1426[var2] && null == Class3_Sub22.aByteArrayArray2521[var2]) {
               Class3_Sub22.aByteArrayArray2521[var2] = CacheIndex.landscapesIndex.getFile(Class101.anIntArray1426[var2], 0, Class39.regionXteaKeys[var2]);
               if(null == Class3_Sub22.aByteArrayArray2521[var2]) {
                  var1 = false;
                  ++anInt3293;
               }
            }

            if(HDToolKit.highDetail) {
               if(Class3_Sub13_Sub15.anIntArray3181[var2] != -1 && aByteArrayArray3669[var2] == null) {
                  aByteArrayArray3669[var2] = CacheIndex.landscapesIndex.getFile(Class3_Sub13_Sub15.anIntArray3181[var2], 0);
                  if(null == aByteArrayArray3669[var2]) {
                     var1 = false;
                     ++anInt3293;
                  }
               }

               if(Class3_Sub28_Sub5.anIntArray3587[var2] != -1 && null == aByteArrayArray3057[var2]) {
                  aByteArrayArray3057[var2] = CacheIndex.landscapesIndex.getFile(Class3_Sub28_Sub5.anIntArray3587[var2], 0);
                  if(null == aByteArrayArray3057[var2]) {
                     ++anInt3293;
                     var1 = false;
                  }
               }
            }

            if(null != NPC.npcSpawnCacheIndices && null == Class3_Sub13_Sub26.aByteArrayArray3335[var2] && NPC.npcSpawnCacheIndices[var2] != -1) {
               Class3_Sub13_Sub26.aByteArrayArray3335[var2] = CacheIndex.landscapesIndex.getFile(NPC.npcSpawnCacheIndices[var2], 0, Class39.regionXteaKeys[var2]);
               if(Class3_Sub13_Sub26.aByteArrayArray3335[var2] == null) {
                  ++anInt3293;
                  var1 = false;
               }
            }
         }

         if(Class3_Sub13_Sub35.aClass131_3421 == null) {
            if(null != Class3_Sub13_Sub21.aClass3_Sub28_Sub3_3264 && CacheIndex.worldmapIndex.method2135(RSString.stringCombiner(new RSString[]{Class3_Sub13_Sub21.aClass3_Sub28_Sub3_3264.aClass94_3561, TextCore.HasPlayerLabels}))) {
               if(CacheIndex.worldmapIndex.method2127(RSString.stringCombiner(new RSString[]{Class3_Sub13_Sub21.aClass3_Sub28_Sub3_3264.aClass94_3561, TextCore.HasPlayerLabels}))) {
                  Class3_Sub13_Sub35.aClass131_3421 = Class81.getWorldMapArchive(RSString.stringCombiner(new RSString[]{Class3_Sub13_Sub21.aClass3_Sub28_Sub3_3264.aClass94_3561, TextCore.HasPlayerLabels}), CacheIndex.worldmapIndex);
               } else {
                  var1 = false;
                  ++anInt3293;
               }
            } else {
               Class3_Sub13_Sub35.aClass131_3421 = new Class131(0);
            }
         }

         if(var1) {
            Class162.anInt2038 = 0;
            var1 = true;

            int var4;
            int var5;
            for(var2 = 0; var2 < Class164_Sub2.aByteArrayArray3027.length; ++var2) {
               byte[] var3 = Class3_Sub22.aByteArrayArray2521[var2];
               if(null != var3) {
                  var5 = -Class82.anInt1152 + (Class3_Sub24_Sub3.anIntArray3494[var2] & 255) * 64;
                  var4 = -Class131.anInt1716 + (Class3_Sub24_Sub3.anIntArray3494[var2] >> 8) * 64;
                  if(LinkableRSString.isDynamicSceneGraph) {
                     var5 = 10;
                     var4 = 10;
                  }

                  var1 &= Class24.isValidObjectMapping((byte)-97, var4, var5, var3);
               }

               if(HDToolKit.highDetail) {
                  var3 = aByteArrayArray3057[var2];
                  if(null != var3) {
                     var4 = -Class131.anInt1716 + 64 * (Class3_Sub24_Sub3.anIntArray3494[var2] >> 8);
                     var5 = -Class82.anInt1152 + 64 * (Class3_Sub24_Sub3.anIntArray3494[var2] & 255);
                     if(LinkableRSString.isDynamicSceneGraph) {
                        var5 = 10;
                        var4 = 10;
                     }

                     var1 &= Class24.isValidObjectMapping((byte)-74, var4, var5, var3);
                  }
               }
            }

             if(var1) {
               if(Class163_Sub2_Sub1.anInt4019 != 0) {
                  Class3_Sub13.method164((byte)-24, true, RSString.stringCombiner(new RSString[]{TextCore.LoadingPleaseWait2, TextCore.aClass94_2707}));
               }

               Class58.method1194();
               Class3_Sub13_Sub30.method313((byte)58);
               boolean var11 = false;
               int var12;
               if(HDToolKit.highDetail && Class128.aBoolean1685) {
                  for(var12 = 0; var12 < Class164_Sub2.aByteArrayArray3027.length; ++var12) {
                     if(null != aByteArrayArray3057[var12] || aByteArrayArray3669[var12] != null) {
                        var11 = true;
                        break;
                     }
                  }
               }

               Class3_Sub4.initializeScene(HDToolKit.highDetail? GameConfig.RENDER_DISTANCE_TILE_VALUE : 25, var11);

               for(var12 = 0; 4 > var12; ++var12) {
                  AtmosphereParser.aClass91Array1182[var12].method1496();
               }

               for(var12 = 0; var12 < 4; ++var12) {
                  for(var4 = 0; var4 < 104; ++var4) {
                     for(var5 = 0; var5 < 104; ++var5) {
                        Unsorted.aByteArrayArrayArray113[var12][var4][var5] = 0;
                     }
                  }
               }

               Class164_Sub1.method2241((byte)-115, false);
               if(HDToolKit.highDetail) {
                  Class141.aClass109_Sub1_1840.method1671();

                  for(var12 = 0; var12 < 13; ++var12) {
                     for(var4 = 0; var4 < 13; ++var4) {
                        Class141.aClass169ArrayArray1841[var12][var4].aBoolean2106 = true;
                     }
                  }
               }

               if(HDToolKit.highDetail) {
                  Class68.method1279();
               }

               if(HDToolKit.highDetail) {
                  Class39.method1036();
               }

               Class58.method1194();
               System.gc();
               Class163_Sub1.method2210(true);
               Class117.method1720(false, 105);
               if(!LinkableRSString.isDynamicSceneGraph) {
                  Unsorted.method1091(false, -93);
                  Class163_Sub1.method2210(true);
                  if(HDToolKit.highDetail) {
                     var12 = Class102.player.anIntArray2767[0] >> 3;
                     var4 = Class102.player.anIntArray2755[0] >> 3;
                     Class3_Sub13_Sub11.method220(var4, var12);
                  }

                  Class3_Sub13_Sub6.method198(false);
                  if(null != Class3_Sub13_Sub26.aByteArrayArray3335) {
                     Class3_Sub13_Sub21.method272((byte)-124);
                  }
               }

               if(LinkableRSString.isDynamicSceneGraph) {
                  Class49.method1121(false, (byte)98);
                  Class163_Sub1.method2210(true);
                  if(HDToolKit.highDetail) {
                     var12 = Class102.player.anIntArray2767[0] >> 3;
                     var4 = Class102.player.anIntArray2755[0] >> 3;
                     Class3_Sub13_Sub11.method220(var4, var12);
                  }

                  Class163_Sub2_Sub1.method2223(false, (byte)-121);
               }

               Class3_Sub13_Sub30.method313((byte)90);
               Class163_Sub1.method2210(true);
               Class158_Sub1.method2189(AtmosphereParser.aClass91Array1182, false, 66);
               if(HDToolKit.highDetail) {
                  Class68.method1270();
               }

               Class163_Sub1.method2210(true);
               var12 = Class85.anInt1174;
               if(var12 > WorldListCountry.localPlane) {
                  var12 = WorldListCountry.localPlane;
               }

               if(WorldListCountry.localPlane + -1 > var12) {
               }

               if(NPC.method1986(39)) {
                  Class85.method1425(0);
               } else {
                  Class85.method1425(Class85.anInt1174);
               }

               Class56.method1188();
               if(HDToolKit.highDetail && var11) {
                  Class167.method2264(true);
                  Class117.method1720(true, 105);
                  if(!LinkableRSString.isDynamicSceneGraph) {
                     Unsorted.method1091(true, -121);
                     Class163_Sub1.method2210(true);
                     Class3_Sub13_Sub6.method198(true);
                  }

                  if(LinkableRSString.isDynamicSceneGraph) {
                     Class49.method1121(true, (byte)56);
                     Class163_Sub1.method2210(true);
                     Class163_Sub2_Sub1.method2223(true, (byte)-105);
                  }

                  Class3_Sub13_Sub30.method313((byte)102);
                  Class163_Sub1.method2210(true);
                  Class158_Sub1.method2189(AtmosphereParser.aClass91Array1182, true, 112);
                  Class163_Sub1.method2210(true);
                  Class56.method1188();
                  Class167.method2264(false);
               }

               if(HDToolKit.highDetail) {
                  for(var4 = 0; var4 < 13; ++var4) {
                     for(var5 = 0; var5 < 13; ++var5) {
                        Class141.aClass169ArrayArray1841[var4][var5].method2281(Class44.anIntArrayArrayArray723[0], var4 * 8, var5 * 8);
                     }
                  }
               }

               for(var4 = 0; var4 < 104; ++var4) {
                  for(var5 = 0; 104 > var5; ++var5) {
                     Class128.method1760(var5, var4);
                  }
               }

               Unsorted.method792();
               Class58.method1194();
               method318();
               Class3_Sub13_Sub30.method313((byte)100);
               Class3_Sub13_Sub34.aBoolean3416 = false;
               if(GameShell.frame != null && null != Class3_Sub15.activeConnection && 25 == Class143.loadingStage) {
                  Class3_Sub13_Sub1.outgoingBuffer.putOpcode(20);
                  Class3_Sub13_Sub1.outgoingBuffer.writeInt(1057001181);
               }

               if(!LinkableRSString.isDynamicSceneGraph) {
                  int var7 = (Class3_Sub7.anInt2294 + 6) / 8;
                  int var6 = (Class3_Sub7.anInt2294 - 6) / 8;
                  var4 = (Unsorted.anInt3606 - 6) / 8;
                  var5 = (Unsorted.anInt3606 - -6) / 8;

                  for(int var8 = var4 - 1; var5 - -1 >= var8; ++var8) {
                     for(int var9 = -1 + var6; var7 - -1 >= var9; ++var9) {
                        if(var4 > var8 || var8 > var5 || var6 > var9 || var9 > var7) {
                           CacheIndex.landscapesIndex.method2124(-124, RSString.stringCombiner(new RSString[]{TextCore.aClass94_3807, RSString.stringAnimator(var8), TextCore.aClass94_3161, RSString.stringAnimator(var9)}));
                           CacheIndex.landscapesIndex.method2124(-123, RSString.stringCombiner(new RSString[]{TextCore.aClass94_2029, RSString.stringAnimator(var8), TextCore.aClass94_3161, RSString.stringAnimator(var9)}));
                        }
                     }
                  }
               }

               if(Class143.loadingStage == 28) {
                  Class117.method1719(10);
               } else {
                  Class117.method1719(30);
                  if(null != Class3_Sub15.activeConnection) {
                     Class3_Sub13_Sub1.outgoingBuffer.putOpcode(110);
                  }
               }

               Class3_Sub20.method388((byte)116);
               Class58.method1194();
               Class75_Sub4.method1355();
            } else {
               Class163_Sub2_Sub1.anInt4019 = 2;
            }
         } else {
            Class163_Sub2_Sub1.anInt4019 = 1;
         }
      } catch (RuntimeException var10) {
         throw ClientErrorException.clientError(var10, "gd.F(" + -117 + ')');
      }
   }

    static void method318() {
       try {
          Class3_Sub4 var1 = (Class3_Sub4)Class3_Sub13_Sub6.aClass61_3075.method1222();

          for(; null != var1; var1 = (Class3_Sub4)Class3_Sub13_Sub6.aClass61_3075.method1221()) {
             if(var1.anInt2259 == -1) {
                var1.anInt2261 = 0;
                Class132.method1798(56, var1);
             } else {
                var1.unlink();
             }
          }

       } catch (RuntimeException var2) {
          throw ClientErrorException.clientError(var2, "rl.B(" + 7759444 + ')');
       }
    }
}
