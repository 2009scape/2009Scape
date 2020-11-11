package org.runite.client;

import org.rs09.client.Linkable;
import org.rs09.client.util.ArrayUtils;

final class Class3_Sub25 extends Linkable {

   int[] anIntArray2547 = new int[]{-1};
   static short[] aShortArray2548;
   static int[] anIntArray2550 = new int[50];
   int[] anIntArray2551 = new int[]{0};
   static Class129 aClass129_2552;


   static void method509(int var1) {
      try {
         --Unsorted.menuOptionCount;
         if(Unsorted.menuOptionCount != var1) {
            ArrayUtils.arraycopy(Class140_Sub7.aClass94Array2935, var1 + 1, Class140_Sub7.aClass94Array2935, var1, -var1 + Unsorted.menuOptionCount);
            ArrayUtils.arraycopy(Class163_Sub2_Sub1.aClass94Array4016, 1 + var1, Class163_Sub2_Sub1.aClass94Array4016, var1, Unsorted.menuOptionCount - var1);
            ArrayUtils.arraycopy(Class114.anIntArray1578, 1 + var1, Class114.anIntArray1578, var1, -var1 + Unsorted.menuOptionCount);
            ArrayUtils.arraycopy(Class3_Sub13_Sub7.aShortArray3095, 1 + var1, Class3_Sub13_Sub7.aShortArray3095, var1, Unsorted.menuOptionCount + -var1);
            ArrayUtils.arraycopy(Class3_Sub13_Sub22.aLongArray3271, 1 + var1, Class3_Sub13_Sub22.aLongArray3271, var1, -var1 + Unsorted.menuOptionCount);
            ArrayUtils.arraycopy(Class117.anIntArray1613, var1 + 1, Class117.anIntArray1613, var1, -var1 + Unsorted.menuOptionCount);
            ArrayUtils.arraycopy(Class27.anIntArray512, 1 + var1, Class27.anIntArray512, var1, Unsorted.menuOptionCount + -var1);
         }
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "qe.A(" + 1 + ',' + var1 + ')');
      }
   }

}
