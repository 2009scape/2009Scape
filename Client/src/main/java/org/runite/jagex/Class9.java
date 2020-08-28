package org.runite.jagex;

final class Class9 {

   int[][] anIntArrayArray108 = new int[6][258];
   byte[] aByteArray109 = new byte[4096];
   int[][] anIntArrayArray110 = new int[6][258];
   byte aByte111;
   boolean[] aBooleanArray112 = new boolean[256];
   static byte[][][] aByteArrayArrayArray113 = new byte[4][104][104];
   byte[] aByteArray114 = new byte[256];
   int[][] anIntArrayArray115 = new int[6][258];
   int anInt116 = 0;
   byte[] aByteArray117;
   int anInt118 = 0;
   static RSString aClass94_119 = RSString.createRSString("runes");
   static int anInt120 = 255;
   int anInt121;
   int[] anIntArray122 = new int[257];
   int[] anIntArray123 = new int[16];
   boolean[] aBooleanArray124 = new boolean[16];
   static byte[][] aByteArrayArray125 = new byte[250][];
   int anInt126;
   byte[] aByteArray127;
   int anInt128;
   int anInt129;
   byte[] aByteArray130 = new byte[18002];
   int anInt131;
   static RSString aClass94_132 = RSString.createRSString("::");
   int anInt133;
   int[] anIntArray134 = new int[256];
   int anInt135;
   static int anInt136 = 0;
   int anInt137;
   int[] anIntArray138 = new int[6];
   byte[] aByteArray139 = new byte[18002];
   int anInt140;
   int anInt141;
   int anInt142;
   int anInt143;
   static int anInt144;
   byte[][] aByteArrayArray146 = new byte[6][258];
   int anInt147;


   static void method848() {
      try {
         if(Class44.aFloat727 < NPC.aFloat3979) {
            Class44.aFloat727 = (float)((double)Class44.aFloat727 + (double)Class44.aFloat727 / 30.0D);
            if(NPC.aFloat3979 < Class44.aFloat727) {
               Class44.aFloat727 = NPC.aFloat3979;
            }

            Class3_Sub5.method117();
         } else if(NPC.aFloat3979 < Class44.aFloat727) {
            Class44.aFloat727 = (float)((double)Class44.aFloat727 - (double)Class44.aFloat727 / 30.0D);
            if(NPC.aFloat3979 > Class44.aFloat727) {
               Class44.aFloat727 = NPC.aFloat3979;
            }

            Class3_Sub5.method117();
         }

         if(Class82.anInt1150 != -1 && -1 != Class3_Sub13_Sub30.anInt3362) {
            int var1 = -Class3_Sub28_Sub1.anInt3536 + Class82.anInt1150;
            if(2 > var1 || var1 > 2) {
               var1 >>= 4;
            }

            int var2 = -Class3_Sub4.anInt2251 + Class3_Sub13_Sub30.anInt3362;
            if(var2 < 2 || var2 > 2) {
               var2 >>= 4;
            }

            Class3_Sub4.anInt2251 -= -var2;
            Class3_Sub28_Sub1.anInt3536 += var1;
            if(0 == var1 && 0 == var2) {
               Class82.anInt1150 = -1;
               Class3_Sub13_Sub30.anInt3362 = -1;
            }

            Class3_Sub5.method117();
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bb.A(" + 4 + ')');
      }
   }

   public static void method849(int var0) {
      try {
         if(var0 == 2) {
            aByteArrayArray125 = (byte[][])null;
            aClass94_119 = null;
            aClass94_132 = null;
            aByteArrayArrayArray113 = (byte[][][])null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "bb.B(" + var0 + ')');
      }
   }

}
