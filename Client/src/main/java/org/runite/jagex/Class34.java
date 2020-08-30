package org.runite.jagex;

final class Class34 {

   private int anInt600 = 2;
   int anInt601;
   private int[] anIntArray602 = new int[2];
   int anInt603;
   private int[] anIntArray604 = new int[2];
   int anInt605;
   private int anInt606;
   private int anInt607;
   private int anInt608;
   private int anInt609;
   private int anInt610;


   final int method1007(int var1) {
      if(this.anInt610 >= this.anInt609) {
         this.anInt607 = this.anIntArray602[this.anInt606++] << 15;
         if(this.anInt606 >= this.anInt600) {
            this.anInt606 = this.anInt600 - 1;
         }

         this.anInt609 = (int)((double)this.anIntArray604[this.anInt606] / 65536.0D * (double)var1);
         if(this.anInt609 > this.anInt610) {
            this.anInt608 = ((this.anIntArray602[this.anInt606] << 15) - this.anInt607) / (this.anInt609 - this.anInt610);
         }
      }

      this.anInt607 += this.anInt608;
      ++this.anInt610;
      return this.anInt607 - this.anInt608 >> 15;
   }

   final void method1008() {
      this.anInt609 = 0;
      this.anInt606 = 0;
      this.anInt608 = 0;
      this.anInt607 = 0;
      this.anInt610 = 0;
   }

   final void method1009(RSByteBuffer var1) {
      this.anInt600 = var1.getByteB();
      this.anIntArray604 = new int[this.anInt600];
      this.anIntArray602 = new int[this.anInt600];

      for(int var2 = 0; var2 < this.anInt600; ++var2) {
         this.anIntArray604[var2] = var1.getShort();
         this.anIntArray602[var2] = var1.getShort();
      }

   }

   final void method1010(RSByteBuffer var1) {
      this.anInt601 = var1.getByteB();
      this.anInt603 = var1.getInt();
      this.anInt605 = var1.getInt();
      this.method1009(var1);
   }

   public Class34() {
      this.anIntArray604[1] = '\uffff';
      this.anIntArray602[1] = '\uffff';
   }
}
