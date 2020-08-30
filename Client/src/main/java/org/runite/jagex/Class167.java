package org.runite.jagex;

final class Class167 {

   static RSString aClass94_2083 = null;
   static int anInt2087 = 0;


   static void addLocalNPCs() {
      try {
         while(true) {
            if(GraphicDefinition.incomingBuffer.method815(Class130.incomingPacketLength) >= 27) {
               int var1 = GraphicDefinition.incomingBuffer.getBits(15);
               if(32767 != var1) {
                  boolean var2 = false;
                  if(null == Class3_Sub13_Sub24.npcs[var1]) {
                     var2 = true;
                     Class3_Sub13_Sub24.npcs[var1] = new NPC();
                  }

                  NPC var3 = Class3_Sub13_Sub24.npcs[var1];
                  Class15.localNPCIndexes[Class163.localNPCCount++] = var1;
                  var3.anInt2838 = Class44.anInt719;
                  if(null != var3.definition && var3.definition.method1474()) {
                     Class3_Sub28_Sub8.method574(var3);
                  }

                  int var4 = GraphicDefinition.incomingBuffer.getBits(1);
                  int var5 = Class27.anIntArray510[GraphicDefinition.incomingBuffer.getBits(3)];
                  if(var2) {
                     var3.anInt2806 = var3.anInt2785 = var5;
                  }

                  int var6 = GraphicDefinition.incomingBuffer.getBits(1);
                  if(var6 == 1) {
                     Class21.maskUpdateIndexes[Class66.maskUpdateCount++] = var1;
                  }

                  int var7 = GraphicDefinition.incomingBuffer.getBits(5);
                  var3.setDefinitions(Node.method522(GraphicDefinition.incomingBuffer.getBits(14)));
                  if(15 < var7) {
                     var7 -= 32;
                  }

                  int var8 = GraphicDefinition.incomingBuffer.getBits(5);
                  if(15 < var8) {
                     var8 -= 32;
                  }

                  var3.setSize(var3.definition.size, 2);
                  var3.renderAnimationId = var3.definition.renderAnimationId;
                  var3.anInt2779 = var3.definition.anInt1274;
                  if(var3.anInt2779 == 0) {
                     var3.anInt2785 = 0;
                  }

                  var3.method1967(var3.getSize(), Class102.player.anIntArray2767[0] + var8, var7 + Class102.player.anIntArray2755[0], var4 == 1);
                  if(var3.definition.method1474()) {
                     Class70.method1286(var3.anIntArray2755[0], (ObjectDefinition)null, 0, var3, var3.anIntArray2767[0], WorldListCountry.localPlane, (Player)null);
                  }
                  continue;
               }
            }

            GraphicDefinition.incomingBuffer.method818();

            return;
         }
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "wj.E(" + 113 + ')');
      }
   }

   public static void method2262(byte var0) {
      try {
         aClass94_2083 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "wj.F(" + var0 + ')');
      }
   }

   static void method2263(int var0, int var1, int var2, int var3, int var4, int var5, int var6) {
      Class113 var7 = new Class113();
      var7.anInt1553 = var1 / 128;
      var7.anInt1547 = var2 / 128;
      var7.anInt1563 = var3 / 128;
      var7.anInt1566 = var4 / 128;
      var7.anInt1554 = var0;
      var7.anInt1562 = var1;
      var7.anInt1545 = var2;
      var7.anInt1560 = var3;
      var7.anInt1550 = var4;
      var7.anInt1544 = var5;
      var7.anInt1548 = var6;
      Class3_Sub28_Sub8.aClass113Array3610[Class3_Sub4.anInt2249++] = var7;
   }

   static void method2264(boolean var0) {
      if(var0) {
         Class75_Sub2.aClass3_Sub2ArrayArrayArray2638 = Class166.aClass3_Sub2ArrayArrayArray2065;
         Class44.anIntArrayArrayArray723 = Class3_Sub28_Sub7.anIntArrayArrayArray3605;
         Class3_Sub23.aClass3_Sub11ArrayArray2542 = Class3_Sub13_Sub28.aClass3_Sub11ArrayArray3346;
      } else {
         Class75_Sub2.aClass3_Sub2ArrayArrayArray2638 = Class3_Sub28_Sub10_Sub2.aClass3_Sub2ArrayArrayArray4070;
         Class44.anIntArrayArrayArray723 = Class58.anIntArrayArrayArray914;
         Class3_Sub23.aClass3_Sub11ArrayArray2542 = Client.aClass3_Sub11ArrayArray2199;
      }

      Class3_Sub17.anInt2456 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638.length;
   }

   static void method2265() {
      try {
         CS2Script.aClass93_2442.method1524();
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "wj.B(" + 0 + ')');
      }
   }

   static void method2266(int var0, int var1) {
      try {
         if(Class9.anInt120 != 0 && var1 != -1) {
            Class70.method1285(Node.aClass153_2573, var1, Class9.anInt120);
            Class83.aBoolean1158 = true;
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "wj.D(" + var0 + ',' + var1 + ',' + (byte) -1 + ')');
      }
   }

   static void method2267(int var0, int var1, boolean var2, RSByteBuffer var3, int var4, int var5, byte var6, int var7, int var8) {
      try {
         int var9;
         if(var5 >= 0 && var5 < 104 && var4 >= 0 && var4 < 104) {
            if(!var2) {
               Class9.aByteArrayArrayArray113[var8][var5][var4] = 0;
            }

            while(true) {
               var9 = var3.getByteB();
               if(var9 == 0) {
                  if(var2) {
                     Class44.anIntArrayArrayArray723[0][var5][var4] = Class58.anIntArrayArrayArray914[0][var5][var4];
                  } else if (var8 == 0) {
                     Class44.anIntArrayArrayArray723[0][var5][var4] = 8 * -Class32.method993(var4 + 556238 + var1, var0 + var5 + 932731);
                  } else {
                     Class44.anIntArrayArrayArray723[var8][var5][var4] = -240 + Class44.anIntArrayArrayArray723[var8 - 1][var5][var4];
                  }
                  break;
               }

               if(var9 == 1) {
                  int var10 = var3.getByteB();
                  if(var2) {
                     Class44.anIntArrayArrayArray723[0][var5][var4] = Class58.anIntArrayArrayArray914[0][var5][var4] - -(var10 * 8);
                  } else {
                     if(var10 == 1) {
                        var10 = 0;
                     }

                     if(var8 == 0) {
                        Class44.anIntArrayArrayArray723[0][var5][var4] = 8 * -var10;
                     } else {
                        Class44.anIntArrayArrayArray723[var8][var5][var4] = -(var10 * 8) + Class44.anIntArrayArrayArray723[-1 + var8][var5][var4];
                     }
                  }
                  break;
               }

               if(49 >= var9) {
                  Class139.aByteArrayArrayArray1828[var8][var5][var4] = var3.getByte();
                  Class93.aByteArrayArrayArray1328[var8][var5][var4] = (byte)((-2 + var9) / 4);
                  PacketParser.aByteArrayArrayArray81[var8][var5][var4] = (byte) Class69.bitwiseAnd(-2 + var9 + var7, 3);
               } else if(var9 > 81) {
                  Class3_Sub13_Sub36.aByteArrayArrayArray3430[var8][var5][var4] = (byte)(-81 + var9);
               } else if(!var2) {
                  Class9.aByteArrayArrayArray113[var8][var5][var4] = (byte)(var9 - 49);
               }
            }
         } else {
            while(true) {
               var9 = var3.getByteB();
               if(var9 == 0) {
                  break;
               }

               if(var9 == 1) {
                  var3.getByteB();
                  break;
               }

               if(var9 <= 49) {
                  var3.getByteB();
               }
            }
         }

         if(var6 < 58) {
            anInt2087 = 87;
         }

      } catch (RuntimeException var11) {
         throw Class44.clientError(var11, "wj.A(" + var0 + ',' + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ')');
      }
   }

   static int method2268(byte var0, int var1, int var2) {
      try {
         Class3_Sub25 var3 = (Class3_Sub25)Class3_Sub2.aClass130_2220.method1780((long)var1, 0);
         if(var3 == null) {
            return 0;
         } else if (var2 == -1) {
            return 0;
         } else {
            int var4 = 0;

            for (int var5 = 0; var3.anIntArray2551.length > var5; ++var5) {
               if (var3.anIntArray2547[var5] == var2) {
                  var4 += var3.anIntArray2551[var5];
               }
            }
            return var4;
         }
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "wj.H(" + var0 + ',' + var1 + ',' + var2 + ')');
      }
   }

   static void method2269(byte var0) {
      try {
         if(null != Class3_Sub15.aClass89_2429) {
            Class3_Sub15.aClass89_2429.close(14821);
            Class3_Sub15.aClass89_2429 = null;
         }

         Class3_Sub13_Sub30.method313((byte)110);
         Class32.method995();

         int var1;
         for(var1 = 0; var1 < 4; ++var1) {
            Class86.aClass91Array1182[var1].method1496();
         }

         Class66.method1250(62, false);
         System.gc();
         NodeList.method882();
         Class83.aBoolean1158 = false;
         Class129.anInt1691 = -1;
         Class164_Sub1.method2241((byte)-77, true);
         Class3_Sub29.isDynamicSceneGraph = false;
         Class82.anInt1152 = 0;
         Class3_Sub28_Sub7.anInt3606 = 0;
         Class3_Sub7.anInt2294 = 0;
         Class131.anInt1716 = 0;

         for(var1 = 0; ClientErrorException.aClass96Array2114.length > var1; ++var1) {
            ClientErrorException.aClass96Array2114[var1] = null;
         }

         Class159.localPlayerCount = 0;
         Class163.localNPCCount = 0;
         if(var0 != 46) {
            method2269((byte)43);
         }

         for(var1 = 0; var1 < 2048; ++var1) {
            Class3_Sub13_Sub22.players[var1] = null;
            Class65.aClass3_Sub30Array986[var1] = null;
         }

         for(var1 = 0; var1 < 32768; ++var1) {
            Class3_Sub13_Sub24.npcs[var1] = null;
         }

         for(var1 = 0; 4 > var1; ++var1) {
            for(int var2 = 0; var2 < 104; ++var2) {
               for(int var3 = 0; var3 < 104; ++var3) {
                  Class3_Sub13_Sub22.aClass61ArrayArrayArray3273[var1][var2][var3] = null;
               }
            }
         }

         Class3_Sub28_Sub5.method560();
         Class113.interfacePacketCounter = 0;
         Class3_Sub13_Sub2.method176(var0 + -161);
         Class3_Sub13_Sub11.method219(true);
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "wj.C(" + var0 + ')');
      }
   }

}
