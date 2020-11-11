package org.runite.client;

final class Class57 {

   static AbstractSprite aAbstractSprite_895;
   int anInt896 = 0;
   static int[] anIntArray898 = new int[2500];
   int anInt899 = 2048;
   static int anInt902 = 100;
   static int[] anIntArray904 = new int[200];
   static int activeWorldListSize;
   int anInt907 = 0;
   int anInt908 = 2048;


   final void method1190(DataBuffer var2, int var3) {
      try {
         while(true) {
            int var4 = var2.readUnsignedByte();
            if(var4 == 0) {
               return;
            }

            this.method1191(var4, var2, var3);
         }
      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "ic.A(" + 2 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

   private void method1191(int var1, DataBuffer var2, int var3) {
      try {
         if(1 == var1) {
            this.anInt896 = var2.readUnsignedByte();
         } else if (var1 == 2) {
            this.anInt908 = var2.readUnsignedShort();
         } else if (var1 == 3) {
            this.anInt899 = var2.readUnsignedShort();
         } else if (4 == var1) {
            this.anInt907 = var2.readSignedShort();
         }

      } catch (RuntimeException var6) {
         throw ClientErrorException.clientError(var6, "ic.C(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ',' + true + ')');
      }
   }

}
