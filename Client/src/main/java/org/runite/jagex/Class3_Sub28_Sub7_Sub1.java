package org.runite.jagex;

final class Class3_Sub28_Sub7_Sub1 extends Class3_Sub28_Sub7 {

   static Class93 aClass93_4043 = new Class93(64);
   private final Object anObject4044;
   static volatile int anInt4045 = 0;
   static Class130 aClass130_4046 = new Class130(16);
   static MouseListeningClass aClass149_4047 = new MouseListeningClass();
   static CacheIndex aClass153_4048;
   static RSString aClass94_4049 = RSString.createRSString("");
   static int[] anIntArray4050 = new int[1000];
   static Class93 aClass93_4051 = new Class93(30);
   static RSString aClass94_4052 = RSString.createRSString("www");


   static void method569(int var1) {
      try {
         Class3_Sub28_Sub6 var2 = Class3_Sub24_Sub3.method466(7, var1);
         var2.a();
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "he.C(" + -82 + ',' + var1 + ')');
      }
   }

   final Object method567() {
      try {
         return this.anObject4044;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "he.B(" + true + ')');
      }
   }

   public static void method570(int var0) {
      try {
         aClass94_4049 = null;
         anIntArray4050 = null;
         aClass149_4047 = null;
         aClass130_4046 = null;
         aClass94_4052 = null;
         aClass93_4043 = null;
         aClass153_4048 = null;
         if(var0 > -101) {
            method570(-94);
         }

         aClass93_4051 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "he.D(" + var0 + ')');
      }
   }

   final boolean method568() {
      try {
         return false;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "he.A(" + -22358 + ')');
      }
   }

   Class3_Sub28_Sub7_Sub1(Object var1) {
      try {
         this.anObject4044 = var1;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "he.<init>(" + (var1 != null?"{...}":"null") + ')');
      }
   }

}
