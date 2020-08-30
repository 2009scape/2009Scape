package org.runite.jagex;

import java.util.Objects;

final class Class163_Sub3 extends Class163 {

   static int[] anIntArray2999;
   static RSString[] aClass94Array3003 = new RSString[100];
   static boolean aBoolean3004 = true;
   static byte[][] aByteArrayArray3005;
   static int[] anIntArray3007 = new int[]{-1, -1, 1, 1};


   public static void method2227(byte var0) {
      try {
         anIntArray3007 = null;
         aClass94Array3003 = null;
         if(var0 == 37) {
            aByteArrayArray3005 = (byte[][])null;
            anIntArray2999 = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "fb.A(" + var0 + ')');
      }
   }

   static final int[] PLAYER_RENDER_LOG = new int[4];
   
   static void renderPlayers() {
      try {
         Class66.maskUpdateCount = 0;
         Class139.anInt1829 = 0;
         Class151_Sub1.updateLocalPosition();
         PLAYER_RENDER_LOG[0] = GraphicDefinition.incomingBuffer.index;
         Class140_Sub3.renderLocalPlayers();
         PLAYER_RENDER_LOG[1] = GraphicDefinition.incomingBuffer.index;
         Class131.addLocalPlayers();
         PLAYER_RENDER_LOG[2] = GraphicDefinition.incomingBuffer.index;
         Canvas_Sub2.parsePlayerMasks();
         PLAYER_RENDER_LOG[3] = GraphicDefinition.incomingBuffer.index;
         int var1;
         for(var1 = 0; Class139.anInt1829 > var1; ++var1) {
            int var2 = Class3_Sub7.anIntArray2292[var1];
            if(Class44.anInt719 != Class3_Sub13_Sub22.players[var2].anInt2838) {
               if(0 < Class3_Sub13_Sub22.players[var2].anInt3969) {
                  Class162.method2203(Class3_Sub13_Sub22.players[var2]);
               }

               Class3_Sub13_Sub22.players[var2] = null;
            }
         }

         if(GraphicDefinition.incomingBuffer.index == Class130.incomingPacketLength) {
            for(var1 = 0; var1 < Class159.localPlayerCount; ++var1) {
               if(null == Class3_Sub13_Sub22.players[Class56.localPlayerIndexes[var1]]) {
//                     throw new RuntimeException("gpp2 pos:" + var1 + " size:" + Class159.anInt2022);
//                     System.err.println("gpp2 pos:" + var1 + " size:" + Class159.anInt2022);
                  System.err.println("Local player was null - index: " + Class56.localPlayerIndexes[var1] + ", list index: " + var1 + ", list size: " + Class159.localPlayerCount);
               }
            }

         } else {
            System.err.println("Player rendering packet size mismatch - size log: self=" + PLAYER_RENDER_LOG[0] + ", local=" + PLAYER_RENDER_LOG[1] + ", add global=" + PLAYER_RENDER_LOG[2] + ", masks=" + PLAYER_RENDER_LOG[3] + ".");
//               System.err.println("gpp1 pos:" + GraphicDefinition.incomingBuffer.index + " psize:" + Class130.incomingPacketLength);
//                throw new RuntimeException("gpp1 pos:" + Class28.incomingBuffer.index + " psize:" + Class130.incomingPacketLength);
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "fb.B(" + (byte) -122 + ')');
      }
   }

   static void method2229(long var0) {
      try {
         if(var0 != 0) {
            if((100 > Class8.anInt104 || Class3_Sub13_Sub29.disableGEBoxes) && Class8.anInt104 < 200) {
               RSString var3 = Objects.requireNonNull(Class41.method1052(var0)).method1545();

               int var4;
               for(var4 = 0; Class8.anInt104 > var4; ++var4) {
                  if(var0 == Class50.aLongArray826[var4]) {
                     Class3_Sub30_Sub1.addChatMessage(Class3_Sub28_Sub14.aClass94_3672, 0, RenderAnimationDefinition.method903(new RSString[]{var3, TextCore.HasFriendsAlready}, (byte)-93), -1);
                     return;
                  }
               }

               for(var4 = 0; var4 < Class3_Sub28_Sub5.anInt3591; ++var4) {
                  if(Class114.ignores[var4] == var0) {
                     Class3_Sub30_Sub1.addChatMessage(Class3_Sub28_Sub14.aClass94_3672, 0, RenderAnimationDefinition.method903(new RSString[]{TextCore.HasPleaseRemove, var3, TextCore.HasIgnoreToFriends}, (byte)-66), -1);
                     return;
                  }
               }

               if(var3.method1528(Class102.player.displayName)) {
                  Class3_Sub30_Sub1.addChatMessage(Class3_Sub28_Sub14.aClass94_3672, 0, TextCore.HasOnOwnFriendsList, -1);
               } else {
                  Class70.aClass94Array1046[Class8.anInt104] = var3;
                  Class50.aLongArray826[Class8.anInt104] = var0;
                  Class55.anIntArray882[Class8.anInt104] = 0;
                  Node.aClass94Array2566[Class8.anInt104] = Class3_Sub28_Sub14.aClass94_3672;
                  Class57.anIntArray904[Class8.anInt104] = 0;
                  Class3.aBooleanArray73[Class8.anInt104] = false;
                  ++Class8.anInt104;
                  Class110.anInt1472 = Class3_Sub13_Sub17.anInt3213;
                  Class3_Sub13_Sub1.outgoingBuffer.putOpcode(120);
                  Class3_Sub13_Sub1.outgoingBuffer.putLong(var0, (byte) -91 ^ 2037491381);
               }
            } else {
               Class3_Sub30_Sub1.addChatMessage(Class3_Sub28_Sub14.aClass94_3672, 0, TextCore.HasFriendsListFull, -1);
            }
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "fb.C(" + var0 + ',' + (byte) -91 + ')');
      }
   }

}
