package org.runite.jagex;

final class Class27 {

   static int[] anIntArray510 = new int[]{768, 1024, 1280, 512, 1536, 256, 0, 1792};
   static Class93 aClass93_511 = new Class93(30);
   static int[] anIntArray512 = new int[500];
   static int anInt515 = -1;
   static RSString aClass94_516 = RSString.createRSString("unzap");
   static int anInt517 = 0;
   static Class3_Sub28_Sub16_Sub2 aClass3_Sub28_Sub16_Sub2_518;
   static Class157 aClass157_524;
  
   static RSInterface aClass11_526 = null;

   static int method961() {
      try {
         return Class23.anInt453 == 0 ?0:Class2.anInterface5Array70[Class23.anInt453].method24();
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ef.B(" + 1536 + ')');
      }
   }

   public static void method962(byte var0) {
      try {
         anIntArray512 = null;
         aClass3_Sub28_Sub16_Sub2_518 = null;
         aClass11_526 = null;
         anIntArray510 = null;
         aClass94_516 = null;
         aClass157_524 = null;
         aClass93_511 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ef.A(" + var0 + ')');
      }
   }

}
