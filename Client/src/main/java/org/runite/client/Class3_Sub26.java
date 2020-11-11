package org.runite.client;

import org.rs09.client.Linkable;

final class Class3_Sub26 extends Linkable {

   int anInt2553;
   static int anInt2554 = 0;
   int anInt2555;
   static int anInt2556 = 0;
   static Class61 aClass61_2557 = new Class61();
   static int[] anIntArray2559 = new int[]{0, 1, 2, 3, 4, 5, 6, 14};
   static AbstractSprite aAbstractSprite_2560;
   static int anInt2561 = -1;
   static Class3_Sub24_Sub2 aClass3_Sub24_Sub2_2563;


   static void method512() {
      try {
         Unsorted.aReferenceCache_4043.clear();
         CS2Script.aReferenceCache_2442.clear();
         Class154.aReferenceCache_1964.clear();

      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "qi.D(" + (byte) -108 + ')');
      }
   }

   static int method513(int var0) {
      try {
         int var2 = 0;
         if(var0 < 0 || var0 >= 65536) {
            var2 += 16;
            var0 >>>= 16;
         }

         if(var0 >= 256) {
            var2 += 8;
            var0 >>>= 8;
         }

         if(var0 >= 16) {
            var2 += 4;
            var0 >>>= 4;
         }

         if(4 <= var0) {
            var0 >>>= 2;
            var2 += 2;
         }

         if(var0 >= 1) {
            var0 >>>= 1;
            ++var2;
         }

         return var0 + var2;
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "qi.B(" + var0 + ',' + 4 + ')');
      }
   }

   Class3_Sub26(int var1, int var2) {
      try {
         this.anInt2555 = var2;
         this.anInt2553 = var1;
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "qi.<init>(" + var1 + ',' + var2 + ')');
      }
   }

   static int method514(int var0, int var1, int var3) {
      try {
         var0 &= 3;

          return var0 == 0 ?var1:(1 == var0?var3:(2 != var0?-var3 + 1023:1023 + -var1));
      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "qi.C(" + var0 + ',' + var1 + ',' + (byte) -83 + ',' + var3 + ')');
      }
   }

}
