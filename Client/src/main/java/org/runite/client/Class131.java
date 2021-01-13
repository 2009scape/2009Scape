package org.runite.client;

import org.rs09.client.config.GameConfig;

final class Class131 {

   static int anInt1716;
    static RSString password = TextCore.aClass94_3672;
    static RSString username = TextCore.aClass94_3672;
    short[] aShortArray1718;
   static int anInt1719 = -1;
   int anInt1720;
   RSString[] aClass94Array1721;
   
   static CacheIndex aClass153_1723;
   int[] anIntArray1725;
   short[] aShortArray1727;
   byte[] aByteArray1730;

   static void addLocalPlayers() {
      try {
         while(true) {
            if(GraphicDefinition.incomingBuffer.method815(Unsorted.incomingPacketLength) >= 11) {
               int index = GraphicDefinition.incomingBuffer.getBits(11);
               if(index != 2047) {
                  boolean var2 = false;
                  if(null == Class3_Sub13_Sub22.players[index]) {
                     Class3_Sub13_Sub22.players[index] = new Player();
                     var2 = true;
                     if(null != Class65.aClass3_Sub30Array986[index]) {
                        Class3_Sub13_Sub22.players[index].parseAppearance(-54, Class65.aClass3_Sub30Array986[index]);
                     }
                  }

                  Class56.localPlayerIndexes[Class159.localPlayerCount++] = index;
                  Player var3 = Class3_Sub13_Sub22.players[index];
                  var3.anInt2838 = Class44.anInt719;
                  int var4 = GraphicDefinition.incomingBuffer.getBits(1);
                  if(var4 == 1) {
                     Class21.maskUpdateIndexes[Unsorted.maskUpdateCount++] = index;
                  }

                  int var5 = GraphicDefinition.incomingBuffer.getBits(5);
                  int var6 = Class27.anIntArray510[GraphicDefinition.incomingBuffer.getBits(3)];
                  if(var5 > 15) {
                     var5 -= 32;
                  }

                  if(var2) {
                     var3.anInt2806 = var3.anInt2785 = var6;
                  }

                  int var7 = GraphicDefinition.incomingBuffer.getBits(1);
                  int var8 = GraphicDefinition.incomingBuffer.getBits(5);
                  if(var8 > 15) {
                     var8 -= 32;
                  }

                  var3.method1981(var5 + Class102.player.anIntArray2767[0], var7 == 1, Class102.player.anIntArray2755[0] + var8);
                  continue;
               }
            }

            GraphicDefinition.incomingBuffer.method818();
            return;
         }
      } catch (RuntimeException var9) {
         throw ClientErrorException.clientError(var9, "se.D(" + -59 + ')');
      }
   }

   final boolean method1787(int var1) {
      try {

         return (this.aByteArray1730[var1] & 8) != 0;
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "se.G(" + var1 + ',' + (byte) -124 + ')');
      }
   }

   static int method1788(int var0, int var1, int var2, int var3, boolean var4) {
      try {
         if(var4) {
            int var5 = 15 & var3;
            int var7 = var5 >= 4 ?(var5 != 12 && var5 != 14 ?var1:var0):var2;
            int var6 = var5 < 8 ?var0:var2;
            return ((var5 & 1) != 0 ?-var6:var6) - -((2 & var5) != 0 ?-var7:var7);
         } else {
            return 127;
         }
      } catch (RuntimeException var8) {
         throw ClientErrorException.clientError(var8, "se.H(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
      }
   }

   final boolean method1789(int var1, int var2) {
      try {
         if(var2 != 530) {
            this.method1794(-111, 26);
         }

         return (4 & this.aByteArray1730[var1]) != 0;
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "se.A(" + var1 + ',' + var2 + ')');
      }
   }

   static void method1790(int var0, int var1) {
      try {
         InterfaceWidget var3 = Class3_Sub24_Sub3.getWidget(5, var0);
         var3.flagUpdate();
         var3.anInt3598 = var1;
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "se.B(" + var0 + ',' + var1 + ',' + 95 + ')');
      }
   }

   final int method1791(int var1, int var2) {
      try {
         return var2 != 8?35:this.aByteArray1730[var1] & 3;
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "se.I(" + var1 + ',' + var2 + ')');
      }
   }

   static void method1793(RSString var0, RSString var1, int var2) {
      try {
         password = var1;
         Class7.anInt2161 = var2;
         username = var0;
         if(username.equalsString(TextCore.aClass94_3672) || password.equalsString(TextCore.aClass94_3672)) {
            Class158.anInt2005 = 3;
         } else if (CS2Script.anInt2451 == -1) {
            Class163_Sub1_Sub1.anInt2246 = 0;
            Class163_Sub1_Sub1.anInt1616 = 0;
            Class158.anInt2005 = -3;
            Class163_Sub1_Sub1.anInt3375 = 1;
            DataBuffer var4 = new DataBuffer(128);
            var4.writeByte(10);
            var4.writeShort((int) (Math.random() * 99999.0D));
            var4.writeShort(GameConfig.CLIENT_BUILD);
            var4.writeLong(username.toLong());
            var4.writeInt((int) (Math.random() * 9.9999999E7D));
            var4.writeString(password);
            var4.writeInt((int) (Math.random() * 9.9999999E7D));
            var4.rsaEncrypt(Class3_Sub13_Sub14.aBigInteger3162, Class3_Sub13_Sub37.aBigInteger3441);
            Class3_Sub13_Sub1.outgoingBuffer.index = 0;
            Class3_Sub13_Sub1.outgoingBuffer.writeByte(210);
            Class3_Sub13_Sub1.outgoingBuffer.writeByte(var4.index);
            Class3_Sub13_Sub1.outgoingBuffer.putBytes(var4.buffer, var4.index);
         } else {
            Class24.method951();
         }
      } catch (RuntimeException var6) {
         throw ClientErrorException.clientError(var6, "se.C(" + (var0 != null?"{...}":"null") + ',' + (var1 != null?"{...}":"null") + ',' + var2 + ',' + (byte) -38 + ')');
      }
   }

   final boolean method1794(int var1, int var2) {
      try {
         if(var2 != -20138) {
            method1788(122, 38, -120, -29, false);
         }

         return 0 == (this.aByteArray1730[var1] & 16);
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "se.E(" + var1 + ',' + var2 + ')');
      }
   }

   Class131(int var1) {
      try {
         this.anInt1720 = var1;
         this.aClass94Array1721 = new RSString[this.anInt1720];
         this.aShortArray1718 = new short[this.anInt1720];
         this.anIntArray1725 = new int[this.anInt1720];
         this.aByteArray1730 = new byte[this.anInt1720];
         this.aShortArray1727 = new short[this.anInt1720];
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "se.<init>(" + var1 + ')');
      }
   }

}
