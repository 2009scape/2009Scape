package org.runite.jagex;

final class Class120 {

   static long[] aLongArray1631 = new long[256];
   int anInt1632;
   int anInt1634;
   int anInt1635;
   static int[] anIntArray1638;


   public static void method1731(int var0) {
      try {
         anIntArray1638 = null;
         aLongArray1631 = null;
         if(var0 != 12881) {
            method1732((RSInterface)null, (byte)-74, -125);
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "qj.A(" + var0 + ')');
      }
   }

   static RSString method1732(RSInterface var0, byte var1, int var2) {
      try {
         if(var1 >= -8) {
            anIntArray1638 = (int[])null;
         }

         return !Client.method44(var0).method92(var2, (byte)-110) && var0.anObjectArray314 == null?null:(null != var0.aClass94Array171 && var0.aClass94Array171.length > var2 && var0.aClass94Array171[var2] != null && var0.aClass94Array171[var2].trim(1).length(-58) != 0 ?var0.aClass94Array171[var2]:(!Class69.aBoolean1040?null:RenderAnimationDefinition.method903(new RSString[]{Class121.aClass94_1645, Class72.method1298((byte)9, var2)}, (byte)-101)));
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "qj.B(" + (var0 != null?"{...}":"null") + ',' + var1 + ',' + var2 + ')');
      }
   }

   static {
      for(int var2 = 0; 256 > var2; ++var2) {
         long var0 = (long)var2;

         for(int var3 = 0; 8 > var3; ++var3) {
            if((1L & var0) == 1) {
               var0 = var0 >>> 1 ^ -3932672073523589310L;
            } else {
               var0 >>>= 1;
            }
         }

         aLongArray1631[var2] = var0;
      }

      anIntArray1638 = new int[128];
   }
}
