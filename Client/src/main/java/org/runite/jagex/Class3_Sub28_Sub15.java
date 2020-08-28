package org.runite.jagex;

final class Class3_Sub28_Sub15 extends Node {

   int anInt3678;
   static Class130 aClass130_3679 = new Class130(16);
   int anInt3680;
   static int anInt3681;
   int anInt3682;
   int[] anIntArray3683;
   static int LoadingStageNumber = 10;
   Class130[] aClass130Array3685;
   RSString aClass94_3686;
   int anInt3687;
   RSString[] aClass94Array3688;
   static int anInt3689 = 0;
   int[] anIntArray3690;
   static int[] anIntArray3693 = new int[1000];
   static Class3_Sub19[] aClass3_Sub19Array3694;
   static int anInt3695;

   static Class100 method629(int var1) {
      try {
         Class100 var2 = (Class100)Class44.aClass93_725.get((long)var1);
         if(var2 == null) {
            if(true) {
               byte[] var3 = Class3_Sub23.aClass153_2536.getFile(1, var1);
               var2 = new Class100();
               if(null != var3) {
                  var2.method1601(var1, new RSByteBuffer(var3));
               }

               Class44.aClass93_725.put((byte)-104, var2, (long)var1);
               return var2;
            } else {
               return (Class100)null;
            }
         } else {
            return var2;
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "qc.B(" + true + ',' + var1 + ')');
      }
   }

   static int method630(int var1) {
      try {
         return 127 & var1 >> 11;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "qc.A(" + (byte) -34 + ',' + var1 + ')');
      }
   }

   static void method631(CacheIndex var1) {
      try {
         if(true) {
            Class3_Sub28_Sub5.aClass153_3580 = var1;
            Class54.anInt869 = Class3_Sub28_Sub5.aClass153_3580.getFileAmount(4, (byte)112);
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "qc.D(" + false + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   public static void method632(int var0) {
      try {
         aClass3_Sub19Array3694 = null;
         aClass130_3679 = null;
         anIntArray3693 = null;
         if(var0 != -30497) {
            aClass3_Sub19Array3694 = (Class3_Sub19[])null;
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "qc.E(" + var0 + ')');
      }
   }

   static int method633(int var0, int var1) {
      try {
         return var0 & var1;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "qc.C(" + var0 + ',' + var1 + ')');
      }
   }

}
