package org.runite.jagex;

final class Class3_Sub28_Sub19 extends Node {

   static CacheIndex aClass153_3772;
   static int anInt3773;
   static int anInt3775 = 0;
   static Class47 aClass47_3776 = new Class47(64);
   static RSString aClass94_3777 = RSString.createRSString(" x ");
   Class140_Sub6 aClass140_Sub6_3778;
   static boolean aBoolean3779 = false;
   static int[] anIntArray3780 = new int[32];


   static boolean method715(RSInterface var1) {
      try {
         if(205 == var1.anInt189) {
            Class159.anInt2023 = 250;
            return true;
         } else {
            return false;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ud.B(" + 205 + ',' + "null" + ')');
      }
   }

   static void method716() {
      try {
         Class136.aClass93_1772.method1524();

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ud.A(" + 14073 + ')');
      }
   }

   public static void method717(int var0) {
      try {
         aClass94_3777 = null;
         anIntArray3780 = null;
         aClass153_3772 = null;
         aClass47_3776 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ud.C(" + var0 + ')');
      }
   }

   Class3_Sub28_Sub19(Class140_Sub6 var1) {
      try {
         this.aClass140_Sub6_3778 = var1;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ud.<init>(" + (var1 != null?"{...}":"null") + ')');
      }
   }

}
