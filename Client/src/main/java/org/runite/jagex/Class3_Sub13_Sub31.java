package org.runite.jagex;

final class Class3_Sub13_Sub31 extends Class3_Sub13 {

   static Class93 aClass93_3369 = new Class93(64);
   static Class3_Sub28_Sub16[] aClass3_Sub28_Sub16Array3373;
   static int anInt3375 = 0;
   static int anInt3377 = 7759444;

   public static void method317(int var0) {
      try {
         if(var0 != 7759444) {
            method317(72);
         }

         aClass93_3369 = null;
         aClass3_Sub28_Sub16Array3373 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "rl.C(" + var0 + ')');
      }
   }

   final int[] method154(int var1, byte var2) {
      try {
         return Class102.anIntArray2125;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "rl.D(" + var1 + ',' + var2 + ')');
      }
   }

   public Class3_Sub13_Sub31() {
      super(0, true);
   }

   static void method318() {
      try {
         Class3_Sub4 var1 = (Class3_Sub4)Class3_Sub13_Sub6.aClass61_3075.method1222();

         for(; null != var1; var1 = (Class3_Sub4)Class3_Sub13_Sub6.aClass61_3075.method1221()) {
            if(var1.anInt2259 == -1) {
               var1.anInt2261 = 0;
               Class132.method1798(56, var1);
            } else {
               var1.method86(-1024);
            }
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "rl.B(" + 7759444 + ')');
      }
   }

}
