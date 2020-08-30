package org.runite.jagex;

final class Class57 {

   static Class3_Sub28_Sub16 aClass3_Sub28_Sub16_895;
   int anInt896 = 0;
   static int[] anIntArray898 = new int[2500];
   int anInt899 = 2048;
   static int anInt902 = 100;
   static int[] anIntArray904 = new int[200];
   static int activeWorldListSize;
   int anInt907 = 0;
   int anInt908 = 2048;


   final void method1190(RSByteBuffer var2, int var3) {
      try {
         while(true) {
            int var4 = var2.getByteB();
            if(var4 == 0) {
               return;
            }

            this.method1191(var4, var2, var3);
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "ic.A(" + 2 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

   private void method1191(int var1, RSByteBuffer var2, int var3) {
      try {
         if(1 == var1) {
            this.anInt896 = var2.getByteB();
         } else if (var1 == 2) {
            this.anInt908 = var2.getShort();
         } else if (var1 == 3) {
            this.anInt899 = var2.getShort();
         } else if (4 == var1) {
            this.anInt907 = var2.getShort((byte) 46);
         }

      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "ic.C(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ',' + true + ')');
      }
   }

   public static void method1192(byte var0) {
      try {
         anIntArray898 = null;
         anIntArray904 = null;
         if(var0 == -86) {
            aClass3_Sub28_Sub16_895 = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ic.B(" + var0 + ')');
      }
   }

}
