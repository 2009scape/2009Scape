package org.runite.jagex;
import java.util.Random;

final class Class116 {

   private Class34 aClass34_1584;
   private Class34 aClass34_1585;
   int anInt1586 = 500;
   private Class34 aClass34_1587;
   private Class34 aClass34_1588;
   private Class34 aClass34_1589;
   private int anInt1590 = 0;
   private static int[] anIntArray1591;
   private Class34 aClass34_1592;
   int anInt1593 = 0;
   private static int[] anIntArray1594 = new int['\u8000'];
   private Class34 aClass34_1595;
   private final int[] anIntArray1596 = new int[]{0, 0, 0, 0, 0};
   private final int[] anIntArray1597 = new int[]{0, 0, 0, 0, 0};
   private static int[] anIntArray1598;
   private static int[] anIntArray1599;
   private static int[] anIntArray1600;
   private static int[] anIntArray1601;
   private static int[] anIntArray1602;
   private static int[] anIntArray1603;
   private int anInt1604 = 100;
   private final int[] anIntArray1605 = new int[]{0, 0, 0, 0, 0};
   private Class103 aClass103_1606;
   private Class34 aClass34_1607;
   private Class34 aClass34_1608;


   public static void method1715() {
      anIntArray1598 = null;
      anIntArray1594 = null;
      anIntArray1591 = null;
      anIntArray1601 = null;
      anIntArray1602 = null;
      anIntArray1603 = null;
      anIntArray1599 = null;
      anIntArray1600 = null;
   }

   private int method1716(int var1, int var2, int var3) {
      return var3 == 1?((var1 & 32767) < 16384?var2:-var2):(var3 == 2?anIntArray1591[var1 & 32767] * var2 >> 14:(var3 == 3?((var1 & 32767) * var2 >> 14) - var2:(var3 == 4?anIntArray1594[var1 / 2607 & 32767] * var2:0)));
   }

   final int[] method1717(int var1, int var2) {
      Class76.method1363(anIntArray1598, 0, var1);
      if (var2 >= 10) {
         double var3 = (double) var1 / ((double) var2 + 0.0D);
         this.aClass34_1589.method1008();
         this.aClass34_1587.method1008();
         int var5 = 0;
         int var6 = 0;
         int var7 = 0;
         if (this.aClass34_1608 != null) {
            this.aClass34_1608.method1008();
            this.aClass34_1607.method1008();
            var5 = (int) ((double) (this.aClass34_1608.anInt605 - this.aClass34_1608.anInt603) * 32.768D / var3);
            var6 = (int) ((double) this.aClass34_1608.anInt603 * 32.768D / var3);
         }

         int var8 = 0;
         int var9 = 0;
         int var10 = 0;
         if (this.aClass34_1595 != null) {
            this.aClass34_1595.method1008();
            this.aClass34_1584.method1008();
            var8 = (int) ((double) (this.aClass34_1595.anInt605 - this.aClass34_1595.anInt603) * 32.768D / var3);
            var9 = (int) ((double) this.aClass34_1595.anInt603 * 32.768D / var3);
         }

         int var11;
         for (var11 = 0; var11 < 5; ++var11) {
            if (this.anIntArray1596[var11] != 0) {
               anIntArray1601[var11] = 0;
               anIntArray1602[var11] = (int) ((double) this.anIntArray1597[var11] * var3);
               anIntArray1603[var11] = (this.anIntArray1596[var11] << 14) / 100;
               anIntArray1599[var11] = (int) ((double) (this.aClass34_1589.anInt605 - this.aClass34_1589.anInt603) * 32.768D * Math.pow(1.0057929410678534D, (double) this.anIntArray1605[var11]) / var3);
               anIntArray1600[var11] = (int) ((double) this.aClass34_1589.anInt603 * 32.768D / var3);
            }
         }

         int var12;
         int var13;
         int var14;
         int var15;
         for (var11 = 0; var11 < var1; ++var11) {
            var12 = this.aClass34_1589.method1007(var1);
            var13 = this.aClass34_1587.method1007(var1);
            if (this.aClass34_1608 != null) {
               var14 = this.aClass34_1608.method1007(var1);
               var15 = this.aClass34_1607.method1007(var1);
               var12 += this.method1716(var7, var15, this.aClass34_1608.anInt601) >> 1;
               var7 += (var14 * var5 >> 16) + var6;
            }

            if (this.aClass34_1595 != null) {
               var14 = this.aClass34_1595.method1007(var1);
               var15 = this.aClass34_1584.method1007(var1);
               var13 = var13 * ((this.method1716(var10, var15, this.aClass34_1595.anInt601) >> 1) + '\u8000') >> 15;
               var10 += (var14 * var8 >> 16) + var9;
            }

            for (var14 = 0; var14 < 5; ++var14) {
               if (this.anIntArray1596[var14] != 0) {
                  var15 = var11 + anIntArray1602[var14];
                  if (var15 < var1) {
                     anIntArray1598[var15] += this.method1716(anIntArray1601[var14], var13 * anIntArray1603[var14] >> 15, this.aClass34_1589.anInt601);
                     anIntArray1601[var14] += (var12 * anIntArray1599[var14] >> 16) + anIntArray1600[var14];
                  }
               }
            }
         }

         int var16;
         if (this.aClass34_1585 != null) {
            this.aClass34_1585.method1008();
            this.aClass34_1588.method1008();
            var11 = 0;

            for (var14 = 0; var14 < var1; ++var14) {
               var15 = this.aClass34_1585.method1007(var1);
               var16 = this.aClass34_1588.method1007(var1);
               var12 = this.aClass34_1585.anInt603 + ((this.aClass34_1585.anInt605 - this.aClass34_1585.anInt603) * var15 >> 8);
               var11 += 256;
               if (var11 >= var12) {
                  var11 = 0;
               } else {
                  anIntArray1598[var14] = 0;
               }
            }
         }

         if (this.anInt1590 > 0 && this.anInt1604 > 0) {
            var11 = (int) ((double) this.anInt1590 * var3);

            for (var12 = var11; var12 < var1; ++var12) {
               anIntArray1598[var12] += anIntArray1598[var12 - var11] * this.anInt1604 / 100;
            }
         }

         if (this.aClass103_1606.anIntArray1434[0] > 0 || this.aClass103_1606.anIntArray1434[1] > 0) {
            this.aClass34_1592.method1008();
            var11 = this.aClass34_1592.method1007(var1 + 1);
            var12 = this.aClass103_1606.method1622(0, (float) var11 / 65536.0F);
            var13 = this.aClass103_1606.method1622(1, (float) var11 / 65536.0F);
            if (var1 >= var12 + var13) {
               var14 = 0;
               var15 = var13;
               if (var13 > var1 - var12) {
                  var15 = var1 - var12;
               }

               int var17;
               while (var14 < var15) {
                  var16 = (int) ((long) anIntArray1598[var14 + var12] * (long) Class103.anInt1436 >> 16);

                  for (var17 = 0; var17 < var12; ++var17) {
                     var16 += (int) ((long) anIntArray1598[var14 + var12 - 1 - var17] * (long) Class103.anIntArrayArray1435[0][var17] >> 16);
                  }

                  for (var17 = 0; var17 < var14; ++var17) {
                     var16 -= (int) ((long) anIntArray1598[var14 - 1 - var17] * (long) Class103.anIntArrayArray1435[1][var17] >> 16);
                  }

                  anIntArray1598[var14] = var16;
                  var11 = this.aClass34_1592.method1007(var1 + 1);
                  ++var14;
               }

               var15 = 128;

               while (true) {
                  if (var15 > var1 - var12) {
                     var15 = var1 - var12;
                  }

                  while (var14 < var15) {
                     var16 = (int) ((long) anIntArray1598[var14 + var12] * (long) Class103.anInt1436 >> 16);

                     for (var17 = 0; var17 < var12; ++var17) {
                        var16 += (int) ((long) anIntArray1598[var14 + var12 - 1 - var17] * (long) Class103.anIntArrayArray1435[0][var17] >> 16);
                     }

                     for (var17 = 0; var17 < var13; ++var17) {
                        var16 -= (int) ((long) anIntArray1598[var14 - 1 - var17] * (long) Class103.anIntArrayArray1435[1][var17] >> 16);
                     }

                     anIntArray1598[var14] = var16;
                     var11 = this.aClass34_1592.method1007(var1 + 1);
                     ++var14;
                  }

                  if (var14 >= var1 - var12) {
                     while (var14 < var1) {
                        var16 = 0;

                        for (var17 = var14 + var12 - var1; var17 < var12; ++var17) {
                           var16 += (int) ((long) anIntArray1598[var14 + var12 - 1 - var17] * (long) Class103.anIntArrayArray1435[0][var17] >> 16);
                        }

                        for (var17 = 0; var17 < var13; ++var17) {
                           var16 -= (int) ((long) anIntArray1598[var14 - 1 - var17] * (long) Class103.anIntArrayArray1435[1][var17] >> 16);
                        }

                        anIntArray1598[var14] = var16;
                        this.aClass34_1592.method1007(var1 + 1);
                        ++var14;
                     }
                     break;
                  }

                  var12 = this.aClass103_1606.method1622(0, (float) var11 / 65536.0F);
                  var13 = this.aClass103_1606.method1622(1, (float) var11 / 65536.0F);
                  var15 += 128;
               }
            }
         }

         for (var11 = 0; var11 < var1; ++var11) {
            if (anIntArray1598[var11] < -32768) {
               anIntArray1598[var11] = -32768;
            }

            if (anIntArray1598[var11] > 32767) {
               anIntArray1598[var11] = 32767;
            }
         }

      }
      return anIntArray1598;
   }

   final void method1718(RSByteBuffer var1) {
      this.aClass34_1589 = new Class34();
      this.aClass34_1589.method1010(var1);
      this.aClass34_1587 = new Class34();
      this.aClass34_1587.method1010(var1);
      int var2 = var1.getByteB();
      if(var2 != 0) {
         --var1.index;
         this.aClass34_1608 = new Class34();
         this.aClass34_1608.method1010(var1);
         this.aClass34_1607 = new Class34();
         this.aClass34_1607.method1010(var1);
      }

      var2 = var1.getByteB();
      if(var2 != 0) {
         --var1.index;
         this.aClass34_1595 = new Class34();
         this.aClass34_1595.method1010(var1);
         this.aClass34_1584 = new Class34();
         this.aClass34_1584.method1010(var1);
      }

      var2 = var1.getByteB();
      if(var2 != 0) {
         --var1.index;
         this.aClass34_1585 = new Class34();
         this.aClass34_1585.method1010(var1);
         this.aClass34_1588 = new Class34();
         this.aClass34_1588.method1010(var1);
      }

      for(int var3 = 0; var3 < 10; ++var3) {
         int var4 = var1.getSmart();
         if(var4 == 0) {
            break;
         }

         this.anIntArray1596[var3] = var4;
         this.anIntArray1605[var3] = var1.getSmart(-21208);
         this.anIntArray1597[var3] = var1.getSmart();
      }

      this.anInt1590 = var1.getSmart();
      this.anInt1604 = var1.getSmart();
      this.anInt1586 = var1.getShort();
      this.anInt1593 = var1.getShort();
      this.aClass103_1606 = new Class103();
      this.aClass34_1592 = new Class34();
      this.aClass103_1606.method1620(var1, this.aClass34_1592);
   }

   static {
      Random var0 = new Random(0L);

      int var1;
      for(var1 = 0; var1 < '\u8000'; ++var1) {
         anIntArray1594[var1] = (var0.nextInt() & 2) - 1;
      }

      anIntArray1591 = new int['\u8000'];

      for(var1 = 0; var1 < '\u8000'; ++var1) {
         anIntArray1591[var1] = (int)(Math.sin((double)var1 / 5215.1903D) * 16384.0D);
      }

      anIntArray1598 = new int[220500];
      anIntArray1599 = new int[5];
      anIntArray1600 = new int[5];
      anIntArray1601 = new int[5];
      anIntArray1603 = new int[5];
      anIntArray1602 = new int[5];
   }
}
