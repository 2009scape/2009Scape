package org.runite.jagex;

final class Class3_Sub18 extends Class3 {

   int anInt2467;
   static Class61 aClass61_2468 = new Class61();
   static int[] anIntArray2469;
   static int[] anIntArray2470 = new int[]{0, 0, 2, 0, 0, 2, 1, 1, 0};
   static int anInt2471;


   public Class3_Sub18() {}

   public static void method382(int var0) {
      try {
         anIntArray2469 = null;
         aClass61_2468 = null;
         if(var0 == 1) {
            anIntArray2470 = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ka.B(" + var0 + ')');
      }
   }

   Class3_Sub18(int var1) {
      try {
         this.anInt2467 = var1;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ka.<init>(" + var1 + ')');
      }
   }

   static void method383(int var0, int var1) {
      try {
         if(var1 == Class23.anInt453 && var1 != 0) {
            Interface5 var2 = Class2.anInterface5Array70[var1];
            var2.method23(Class3_Sub13_Sub21.anInt3263);
         }

         if(var0 != -32584) {
            method383(60, 23);
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ka.A(" + var0 + ',' + var1 + ')');
      }
   }

}
