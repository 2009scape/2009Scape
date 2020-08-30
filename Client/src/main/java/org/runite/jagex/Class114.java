package org.runite.jagex;

import java.util.Objects;

final class Class114 {

   private final int anInt1568;
   static Class93 aClass93_1569 = new Class93(200);
   private int anInt1570 = 0;
   private Class3_Sub26[] aClass3_Sub26Array1571;
   static Class30 aClass30_1572;
   private int[][] anIntArrayArray1573;
   static long[] ignores = new long[100];
   private int anInt1576 = -1;
   private Class61 aClass61_1577 = new Class61();
   static int[] anIntArray1578 = new int[500];
   private final int anInt1579;
   boolean aBoolean1580 = false;


   public static void method1704(int var0) {
      try {
         aClass93_1569 = null;
         if(var0 != 65536) {
            method1705(-109, -68);
         }

         anIntArray1578 = null;
         ignores = null;
         aClass30_1572 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "pf.G(" + var0 + ')');
      }
   }

   static float[] method1705(int var0, int var1) {
      try {
         float var2 = Class92.method1514() + Class92.getLightingModelAmbient();
         int var3 = Class92.screenColorRgb();
         float var7 = 0.58823526F;
         MouseListeningClass.aFloatArray1919[3] = 1.0F;
         float var4 = (float)(var3 >> 16 & 255) / 255.0F;
         float var5 = (float)(('\uff1e' & var3) >> 8) / 255.0F;
         MouseListeningClass.aFloatArray1919[1] = var2 * (float) Class69.bitwiseAnd(var0 >> 8, 255) / 255.0F * var5 * var7;
         MouseListeningClass.aFloatArray1919[var1] = var2 * var7 * var4 * ((float)(Class69.bitwiseAnd(16754958, var0) >> 16) / 255.0F);
         float var6 = (float)(var3 & 255) / 255.0F;
         MouseListeningClass.aFloatArray1919[2] = (float) Class69.bitwiseAnd(255, var0) / 255.0F * var6 * var7 * var2;
         return MouseListeningClass.aFloatArray1919;
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "pf.F(" + var0 + ',' + var1 + ')');
      }
   }

   final void method1706() {
      try {
         for(int var2 = 0; var2 < this.anInt1579; ++var2) {
            this.anIntArrayArray1573[var2] = null;
         }

         this.anIntArrayArray1573 = (int[][])null;
         this.aClass3_Sub26Array1571 = null;
         this.aClass61_1577.method1211(-85);
         this.aClass61_1577 = null;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "pf.A(" + 1127165736 + ')');
      }
   }

   static Class3_Sub28_Sub16 method1707(int var0, int var1, boolean var2, int var3, int var4) {
      try {
         int var7 = (!var2?0:65536) + var1 + (var0 << 17) + (var4 << 19);
         long var8 = (long)var7 * 3849834839L + 3147483667L * (long)var3;
         Class3_Sub28_Sub16 var10 = (Class3_Sub28_Sub16)Class67.aClass93_1013.get(var8);
         if(var10 == null) {
            Class51.aBoolean837 = false;
            var10 = RSString.method1570(var4, (byte)13, false, var1, var2, var0, var3, false);
            if(var10 != null && !Class51.aBoolean837) {
               Class67.aClass93_1013.put((byte)-79, var10, var8);
            }

         }
         return var10;
      } catch (RuntimeException var11) {
         throw Class44.clientError(var11, "pf.B(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + 65536 + ')');
      }
   }

   static void method1708(int var0, int var1) {
      try {
         Class3_Sub28_Sub6 var3 = Class3_Sub24_Sub3.method466(13, var1);
         var3.g();
         var3.anInt3598 = var0;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "pf.E(" + var0 + ',' + var1 + ',' + -2553 + ')');
      }
   }

   final int[] method1709(int var2) {
      try {

         if(this.anInt1579 == this.anInt1568) {
            this.aBoolean1580 = this.aClass3_Sub26Array1571[var2] == null;
            this.aClass3_Sub26Array1571[var2] = Class56.aClass3_Sub26_884;
            return this.anIntArrayArray1573[var2];
         } else if (this.anInt1579 == 1) {
            this.aBoolean1580 = this.anInt1576 != var2;
            this.anInt1576 = var2;
            return this.anIntArrayArray1573[0];
         } else {
            Class3_Sub26 var3 = this.aClass3_Sub26Array1571[var2];
            if (null == var3) {
               this.aBoolean1580 = true;
               if (this.anInt1570 < this.anInt1579) {
                  var3 = new Class3_Sub26(var2, this.anInt1570);
                  ++this.anInt1570;
               } else {
                  Class3_Sub26 var4 = (Class3_Sub26) this.aClass61_1577.method1212();
                  var3 = new Class3_Sub26(var2, Objects.requireNonNull(var4).anInt2555);
                  this.aClass3_Sub26Array1571[var4.anInt2553] = null;
                  var4.method86(-1024);
               }

               this.aClass3_Sub26Array1571[var2] = var3;
            } else {
               this.aBoolean1580 = false;
            }

            this.aClass61_1577.method1216(var3);
            return this.anIntArrayArray1573[var3.anInt2555];
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "pf.C(" + -16409 + ',' + var2 + ')');
      }
   }

   final int[][] method1710(byte var1) {
      try {
         if(var1 <= 67) {
            return (int[][])((int[][])null);
         } else if(this.anInt1579 == this.anInt1568) {
            for(int var2 = 0; this.anInt1579 > var2; ++var2) {
               this.aClass3_Sub26Array1571[var2] = Class56.aClass3_Sub26_884;
            }

            return this.anIntArrayArray1573;
         } else {
            throw new RuntimeException("Can only retrieve a full image cache");
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "pf.H(" + var1 + ')');
      }
   }

   static void method1711(int var0, int var1) {
      try {
         Class3_Sub28_Sub4.aClass93_3572.method1522(-127, var0);
         Class143.aClass93_1874.method1522(var1 + -383, var0);
         Class67.aClass93_1013.method1522(var1 ^ -132, var0);
         if(var1 != 255) {
            method1711(20, 87);
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "pf.D(" + var0 + ',' + var1 + ')');
      }
   }

   Class114(int var1, int var2, int var3) {
      try {
         this.anInt1568 = var2;
         this.aClass3_Sub26Array1571 = new Class3_Sub26[this.anInt1568];
         this.anInt1579 = var1;
         this.anIntArrayArray1573 = new int[this.anInt1579][var3];
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "pf.<init>(" + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

}
