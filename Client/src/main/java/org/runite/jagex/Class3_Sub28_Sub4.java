package org.runite.jagex;

import java.util.Objects;

final class Class3_Sub28_Sub4 extends Node {

   static int[] anIntArray3565 = new int[32];
   private int[] anIntArray3566;
   int[] anIntArray3567;
   boolean aBoolean3568 = true;
   private int[][] anIntArrayArray3570;
   private RSString[] aClass94Array3571;
   static Class93 aClass93_3572 = new Class93(64);
   static RSString aClass94_3574 = RSString.createRSString("titlebg");
   static RSString aClass94_3577 = RSString.createRSString(": ");
   static Class83 aClass83_3579;

   final void method545(RSByteBuffer var1, int[] var2) {
      try {
         if(this.anIntArray3566 != null) {
            for(int var4 = 0; var4 < this.anIntArray3566.length && var2.length > var4; ++var4) {
               int var5 = ClientErrorException.anIntArray2113[this.method550(89, var4)];
               if(var5 > 0) {
                  var1.method739(var5, (long)var2[var4]);
               }
            }

         }
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "cb.O(" + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ',' + false + ')');
      }
   }

   final void method546(RSByteBuffer var1) {
      try {
         while(true) {
            int var3 = var1.getByteB();
            if(0 == var3) {

               return;
            }

            this.method553(var1, var3);
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "cb.D(" + (var1 != null?"{...}":"null") + ',' + -1 + ')');
      }
   }

   public static void method547(int var0) {
      try {
         aClass93_3572 = null;
         aClass94_3574 = null;
         aClass94_3577 = null;
         aClass83_3579 = null;
         anIntArray3565 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "cb.F(" + var0 + ')');
      }
   }

   final void method548() {
      try {
         if(null != this.anIntArray3567) {
            for(int var2 = 0; var2 < this.anIntArray3567.length; ++var2) {
               this.anIntArray3567[var2] = Class3_Sub13_Sub29.bitwiseOr(this.anIntArray3567[var2], '\u8000');
            }
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "cb.Q(" + 60 + ')');
      }
   }

   final int method549(int var2, int var3) {
      try {

         return null != this.anIntArray3566 && var3 >= 0 && this.anIntArray3566.length >= var3 ?(this.anIntArrayArray3570[var3] != null && var2 >= 0 && this.anIntArrayArray3570[var3].length >= var2 ?this.anIntArrayArray3570[var3][var2]:-1):-1;
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "cb.P(" + -117 + ',' + var2 + ',' + var3 + ')');
      }
   }

   final int method550(int var1, int var2) {
      try {
         if(null != this.anIntArray3566 && var2 >= 0 && var2 <= this.anIntArray3566.length) {
            return this.anIntArray3566[var2];
         } else {
            return -1;
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "cb.S(" + var1 + ',' + var2 + ')');
      }
   }

   static void method551(int var1, int var2) {
      try {
         if(4 == var2 && !Class128.aBoolean1685) {
            var2 = 2;
            var1 = 2;
         }

         if(var2 == Class23.anInt453) {
            if(0 != var2 && var1 != Class3_Sub13_Sub21.anInt3263) {
               Class2.anInterface5Array70[var2].method23(var1);
               Class3_Sub13_Sub21.anInt3263 = var1;
            }
         } else {
            if(Class3_Sub13_Sub17.aBoolean3207) {
               return;
            }

            if(Class23.anInt453 != 0) {
               Class2.anInterface5Array70[Class23.anInt453].method21();
            }

            if(var2 != 0) {
               Interface5 var3 = Class2.anInterface5Array70[var2];
               var3.method22();
               var3.method23(var1);
            }

            Class23.anInt453 = var2;
            Class3_Sub13_Sub21.anInt3263 = var1;
         }

      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "cb.A(" + 0 + ',' + var1 + ',' + var2 + ')');
      }
   }

   final int method552() {
      try {
         return (this.anIntArray3566 != null?this.anIntArray3566.length:0);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "cb.E(" + true + ')');
      }
   }

   private void method553(RSByteBuffer var1, int var2) {
      try {

         if(var2 == 1) {
            this.aClass94Array3571 = var1.getString().method1567(60, (byte)118);
         } else {
            int var4;
            int var5;
            if(var2 == 2) {
               var4 = var1.getByteB();
               this.anIntArray3567 = new int[var4];

               for(var5 = 0; var5 < var4; ++var5) {
                  this.anIntArray3567[var5] = var1.getShort();
               }
            } else if (var2 == 3) {
               var4 = var1.getByteB();
               this.anIntArray3566 = new int[var4];
               this.anIntArrayArray3570 = new int[var4][];

               for (var5 = 0; var4 > var5; ++var5) {
                  int var6 = var1.getShort();
                  this.anIntArray3566[var5] = var6;
                  this.anIntArrayArray3570[var5] = new int[Class155.anIntArray1976[var6]];

                  for (int var7 = 0; Class155.anIntArray1976[var6] > var7; ++var7) {
                     this.anIntArrayArray3570[var5][var7] = var1.getShort();
                  }
               }
            } else if (var2 == 4) {
               this.aBoolean3568 = false;
            }
         }

      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "cb.R(" + (var1 != null?"{...}":"null") + ',' + var2 + ',' + -14637 + ')');
      }
   }

   final RSString method554() {
      try {
         RSString var2 = Class47.method1090((byte)-118, 80);
         if(null == this.aClass94Array3571) {
            return Class3_Sub13_Sub29.aClass94_3357;
         } else {
            Objects.requireNonNull(var2).method1533(this.aClass94Array3571[0]);

            for(int var3 = 1; var3 < this.aClass94Array3571.length; ++var3) {
               var2.method1533(GameShell.aClass94_9);
               var2.method1533(this.aClass94Array3571[var3]);
            }

            return var2.method1576();
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "cb.C(" + -1 + ')');
      }
   }

   final RSString method555(RSByteBuffer var2) {
      try {

         RSString var3 = Class47.method1090((byte)-125, 80);
         if(this.anIntArray3566 != null) {
            for(int var4 = 0; var4 < this.anIntArray3566.length; ++var4) {
               Objects.requireNonNull(var3).method1533(this.aClass94Array3571[var4]);
               var3.method1533(Class49.method1124(this.anIntArrayArray3570[var4], var2.method772(Class3_Sub1.anIntArray2209[this.anIntArray3566[var4]]), this.anIntArray3566[var4]));
            }
         }

         Objects.requireNonNull(var3).method1533(this.aClass94Array3571[-1 + this.aClass94Array3571.length]);
         return var3.method1576();
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "cb.B(" + 28021 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

}
