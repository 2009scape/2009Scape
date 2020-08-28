package org.runite.jagex;

final class WorldListCountry {

   static RSString aClass94_500 = RSString.createRSString(" GMT");
   static int localPlane;
   static int anInt502;
   static int anInt503;
   static short aShort505 = 1;
   static int anInt506;
   int flagId;
   RSString name;
   static int[] anIntArray509 = new int[5];


   public static void method960(int var0) {
      try {
         anIntArray509 = null;
         aClass94_500 = null;
         int var1 = 32 % ((-78 - var0) / 34);
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ee.A(" + var0 + ')');
      }
   }

}
