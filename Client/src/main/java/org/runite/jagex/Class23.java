package org.runite.jagex;

final class Class23 {

   static int anInt453 = 0;
   static int anInt454;
   static int anInt455;
   static boolean[][] aBooleanArrayArray457;


   public static void method937(int var0) {
      try {
         aBooleanArrayArray457 = (boolean[][])null;
         if(var0 != 0) {
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "dl.A(" + var0 + ')');
      }
   }

   static void method938(int var0, int var1, int var2, int var3, int var4, int var5, int var7, int var8) {
      try {
         int var9 = var2 - var7;
         int var10 = var3 - var8;
         int var11 = (-var1 + var0 << 16) / var9;
         int var12 = (-var5 + var4 << 16) / var10;
         Class136.method1814(var1, var3, var2, var12, var7, var11, var8, var5);
      } catch (RuntimeException var13) {
         throw Class44.clientError(var13, "dl.B(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + 1 + ',' + var7 + ',' + var8 + ')');
      }
   }

   static RSByteBuffer method939() {
      try {
         RSByteBuffer var1 = new RSByteBuffer(34);
         var1.putByte((byte)-40, 11);
         var1.putByte((byte)-42, Class3_Sub28_Sub10.anInt3625);
         var1.putByte((byte)-81, !Class3_Sub28_Sub13.aBoolean3665?0:1);
         var1.putByte((byte)-57, Class3_Sub28_Sub7.aBoolean3604?1:0);
         var1.putByte((byte)-12, KeyboardListener.aBoolean1905?1:0);
         var1.putByte((byte)-29, Class25.aBoolean488?1:0);
         var1.putByte((byte)-60, !RSInterface.aBoolean236?0:1);
         var1.putByte((byte)-78, !WorldListEntry.aBoolean2623?0:1);
         var1.putByte((byte)-30, Class3_Sub13_Sub22.aBoolean3275?1:0);
         var1.putByte((byte)-6, !Class140_Sub6.aBoolean2910?0:1);
         var1.putByte((byte)-83, Class80.anInt1137);
         var1.putByte((byte)-29, !Class106.aBoolean1441?0:1);
         var1.putByte((byte) -116, Class128.aBoolean1685?1:0);
         var1.putByte((byte)-45, Class38.aBoolean661?1:0);
         var1.putByte((byte)-78, Class3_Sub28_Sub9.anInt3622);
         var1.putByte((byte)-118, !Class3_Sub13_Sub15.aBoolean3184?0:1);
         var1.putByte((byte)-119, CS2Script.anInt2453);
         var1.putByte((byte)-70, Class9.anInt120);
         var1.putByte((byte)-9, Class14.anInt340);
         var1.putShort(Class3_Sub13.anInt2378);
         var1.putShort(Class3_Sub13_Sub5.anInt3071);
         var1.putByte((byte)-53, Class127_Sub1.method1757());
         var1.putInt(-123, RSString.anInt2148);
         var1.putByte((byte)-19, Node.anInt2577);
         var1.putByte((byte)-117, RSString.aBoolean2146?1:0);
         var1.putByte((byte)-9, !Class15.aBoolean346?0:1);
         var1.putByte((byte)-39, Class3_Sub20.anInt2488);
         var1.putByte((byte)-78, Class73.aBoolean1080?1:0);
         var1.putByte((byte)-79, Class163_Sub3.aBoolean3004?1:0);
         return var1;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "dl.C(" + (byte) -116 + ')');
      }
   }

   static void method940(int var0, int var1) {
      try {
         if(var0 >= 101) {
            MouseListeningClass var2 = Class3_Sub28_Sub7_Sub1.aClass149_4047;
            synchronized(var2) {
               Class3_Sub28_Sub7_Sub1.anInt4045 = var1;
            }
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "dl.D(" + var0 + ',' + var1 + ')');
      }
   }

}
