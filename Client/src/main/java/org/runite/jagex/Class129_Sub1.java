package org.runite.jagex;
import java.awt.Image;

final class Class129_Sub1 extends Class129 {

   private long aLong2683;
   private int anInt2685;
   static Class47 aClass47_2686 = new Class47(128);
   private int anInt2688;
   static int anInt2689;
   static Class3_Sub28_Sub16[] aClass3_Sub28_Sub16Array2690;
   private int anInt2691;
   private int anInt2692;
   static int anInt2693 = 0;
   private final long[] aLongArray2694 = new long[10];
   static Image anImage2695;
   static int[] anIntArray2696 = new int[2];
   static int anInt2697;


   public static void method1771(int var0) {
      try {
         anIntArray2696 = null;
         anImage2695 = null;
         if(var0 != 14635) {
            aClass47_2686 = (Class47)null;
         }

         aClass3_Sub28_Sub16Array2690 = null;
         aClass47_2686 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "lj.G(" + var0 + ')');
      }
   }

   final void method1770() {
      try {
         int var2;
         for(var2 = 0; var2 < 10; ++var2) {
            this.aLongArray2694[var2] = 0L;
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "lj.A(" + -124 + ')');
      }
   }

   final int method1767(int var1, int var2, int var3) {
      try {
         if(var1 != -1) {
            this.method1767(-14, 83, 10);
         }

         int var5 = this.anInt2691;
         int var4 = this.anInt2688;
         this.anInt2688 = 300;
         this.anInt2691 = 1;
         this.aLong2683 = Class5.method830((byte)-55);
         if(this.aLongArray2694[this.anInt2685] == 0L) {
            this.anInt2688 = var4;
            this.anInt2691 = var5;
         } else if(this.aLongArray2694[this.anInt2685] < this.aLong2683) {
            this.anInt2688 = (int)((long)(var3 * 2560) / (this.aLong2683 + -this.aLongArray2694[this.anInt2685]));
         }

         if(this.anInt2688 < 25) {
            this.anInt2688 = 25;
         }

         if(256 < this.anInt2688) {
            this.anInt2688 = 256;
            this.anInt2691 = (int)(-((this.aLong2683 - this.aLongArray2694[this.anInt2685]) / 10L) + (long)var3);
         }

         if(this.anInt2691 > var3) {
            this.anInt2691 = var3;
         }

         this.aLongArray2694[this.anInt2685] = this.aLong2683;
         this.anInt2685 = (1 + this.anInt2685) % 10;
         int var6;
         if(this.anInt2691 > 1) {
            for(var6 = 0; var6 < 10; ++var6) {
               if(this.aLongArray2694[var6] != 0L) {
                  this.aLongArray2694[var6] += (long)this.anInt2691;
               }
            }
         }

         if(var2 > this.anInt2691) {
            this.anInt2691 = var2;
         }

         Class3_Sub13_Sub34.method331((long)this.anInt2691, var1 ^ -65);

         for(var6 = 0; 256 > this.anInt2692; ++var6) {
            this.anInt2692 += this.anInt2688;
         }

         this.anInt2692 &= 255;
         return var6;
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "lj.B(" + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   Class129_Sub1() {
      try {
         this.anInt2688 = 256;
         this.anInt2691 = 1;
         this.anInt2692 = 0;
         this.aLong2683 = Class5.method830((byte)-55);

         for(int var1 = 0; var1 < 10; ++var1) {
            this.aLongArray2694[var1] = this.aLong2683;
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "lj.<init>()");
      }
   }

}
