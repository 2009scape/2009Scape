package org.runite.jagex;

class Class38 {

   static int anInt660;
   static boolean aBoolean661 = true;
   static int[][] anIntArrayArray663;
   static int[] anIntArray664 = new int[14];
   static Signlink aClass87_665;
   static Class146 aClass146_668;


   static ItemDefinition getItemDefinition(int itemId, byte var1) {
      try {
         ItemDefinition var2 = (ItemDefinition)Class3_Sub28_Sub4.aClass93_3572.get((long)itemId);
         if(var2 == null) {
            byte[] var3 = Class97.aClass153_1370.getFile(Class140_Sub2.method1951(itemId), 255 & itemId);
            if(var1 <= 68) {
               method1027(-113, (byte)110);
            }

            var2 = new ItemDefinition();
            var2.itemId = itemId;
            if(var3 != null) {
               var2.parseDefinitions(new RSByteBuffer(var3));
            }

            var2.method1112();
            if(var2.anInt791 != -1) {
               var2.method1118(getItemDefinition(var2.anInt789, (byte)70), getItemDefinition(var2.anInt791, (byte)73));
            }

            if(var2.anInt762 != -1) {
               var2.method1109(getItemDefinition(var2.anInt795, (byte)111), getItemDefinition(var2.anInt762, (byte)86));
            }

            if(!Class139.aBoolean1827 && var2.membersItem) {
               var2.name = TextCore.MembersObject;
               var2.teamId = 0;
               var2.inventoryOptions = ClientErrorException.aClass94Array2119;
               var2.aBoolean807 = false;
               var2.groundOptions = RSByteBuffer.aClass94Array2596;
            }

            Class3_Sub28_Sub4.aClass93_3572.put((byte)-107, var2, (long)itemId);
         }
         return var2;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "fk.F(" + itemId + ',' + var1 + ')');
      }
   }

   public static void method1024(int var0) {
      try {
         if(var0 != 21474) {
            method1029(-65);
         }

         anIntArray664 = null;
         anIntArrayArray663 = (int[][])null;
         aClass146_668 = null;
         aClass87_665 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "fk.E(" + var0 + ')');
      }
   }

   static void method1025(byte var0) {
      try {
         Class3_Sub31.aClass93_2604.method1523((byte)-121);
         if(var0 > -51) {
            method1025((byte)86);
         }

         Class27.aClass93_511.method1523((byte)-120);
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "fk.I(" + var0 + ')');
      }
   }

   static int method1026(byte[] var0, int var1) {
      try {
         return Class99.method1599(0, var1, var0, (byte)-35);
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "fk.H(" + (var0 != null?"{...}":"null") + ',' + var1 + ',' + false + ')');
      }
   }

   static void method1027(int var0, byte var1) {
      try {
         Class44.aClass93_725.method1522(-128, var0);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "fk.J(" + var0 + ',' + var1 + ')');
      }
   }

   static void method1028() {
      try {
         for(int var1 = -1; Class159.localPlayerCount > var1; ++var1) {
            int var2;
            if(var1 == -1) {
               var2 = 2047;
            } else {
               var2 = Class56.localPlayerIndexes[var1];
            }

            Player var3 = Class3_Sub13_Sub22.players[var2];
            if(var3 != null) {
               OutputStream_Sub1.method68(var3.getSize(), var3);
            }
         }

      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "fk.G(" + -102 + ')');
      }
   }

   static void method1029(int var0) {
      try {
         Class3_Sub13_Sub1.outgoingBuffer.putOpcode(177);
         Class3_Sub13_Sub1.outgoingBuffer.putShort(Class113.interfacePacketCounter);
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "fk.D(" + var0 + ')');
      }
   }

}
