package org.runite.jagex;

final class Class135 {

   private int anInt1767;
   private final Class116[] aClass116Array1768 = new Class116[10];
   private int anInt1769;


   private byte[] method1810() {
      int var1 = 0;

      int var2;
      for(var2 = 0; var2 < 10; ++var2) {
         if(this.aClass116Array1768[var2] != null && this.aClass116Array1768[var2].anInt1586 + this.aClass116Array1768[var2].anInt1593 > var1) {
            var1 = this.aClass116Array1768[var2].anInt1586 + this.aClass116Array1768[var2].anInt1593;
         }
      }

      if(var1 == 0) {
         return new byte[0];
      } else {
         var2 = 22050 * var1 / 1000;
         byte[] var3 = new byte[var2];

         for(int var4 = 0; var4 < 10; ++var4) {
            if(this.aClass116Array1768[var4] != null) {
               int var5 = this.aClass116Array1768[var4].anInt1586 * 22050 / 1000;
               int var6 = this.aClass116Array1768[var4].anInt1593 * 22050 / 1000;
               int[] var7 = this.aClass116Array1768[var4].method1717(var5, this.aClass116Array1768[var4].anInt1586);

               for(int var8 = 0; var8 < var5; ++var8) {
                  int var9 = var3[var8 + var6] + (var7[var8] >> 8);
                  if((var9 + 128 & -256) != 0) {
                     var9 = var9 >> 31 ^ 127;
                  }

                  var3[var8 + var6] = (byte)var9;
               }
            }
         }

         return var3;
      }
   }

   static Class135 method1811(CacheIndex var0, int var1, int var2) {
      byte[] var3 = var0.getFile(var1, var2);
      return var3 == null?null:new Class135(new RSByteBuffer(var3));
   }

   final Class3_Sub12_Sub1 method1812() {
      byte[] var1 = this.method1810();
      return new Class3_Sub12_Sub1(var1, 22050 * this.anInt1769 / 1000, 22050 * this.anInt1767 / 1000);
   }

   final int method1813() {
      int var1 = 9999999;

      int var2;
      for(var2 = 0; var2 < 10; ++var2) {
         if(this.aClass116Array1768[var2] != null && this.aClass116Array1768[var2].anInt1593 / 20 < var1) {
            var1 = this.aClass116Array1768[var2].anInt1593 / 20;
         }
      }

      if(this.anInt1769 < this.anInt1767 && this.anInt1769 / 20 < var1) {
         var1 = this.anInt1769 / 20;
      }

      if(var1 == 9999999 || var1 == 0) {
         return 0;
      } else {
         for(var2 = 0; var2 < 10; ++var2) {
            if(this.aClass116Array1768[var2] != null) {
               this.aClass116Array1768[var2].anInt1593 -= var1 * 20;
            }
         }

         if(this.anInt1769 < this.anInt1767) {
            this.anInt1769 -= var1 * 20;
            this.anInt1767 -= var1 * 20;
         }

         return var1;
      }
   }

   private Class135(RSByteBuffer var1) {
      for(int var2 = 0; var2 < 10; ++var2) {
         int var3 = var1.getByteB();
         if(var3 != 0) {
            --var1.index;
            this.aClass116Array1768[var2] = new Class116();
            this.aClass116Array1768[var2].method1718(var1);
         }
      }

      this.anInt1769 = var1.getShort();
      this.anInt1767 = var1.getShort();
   }

   private Class135() {}
}
