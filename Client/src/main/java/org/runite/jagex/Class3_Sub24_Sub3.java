package org.runite.jagex;

import java.util.Objects;

final class Class3_Sub24_Sub3 extends Class3_Sub24 {

   Class61 aClass61_3489 = new Class61();
   static CacheIndex aClass153_3490;
   static int[] anIntArray3491 = new int[]{0, -1, 0, 1};
   static int anInt3492 = 64;
   private final Class3_Sub24_Sub4 aClass3_Sub24_Sub4_3493;
   static int[] anIntArray3494;
   Class3_Sub24_Sub2 aClass3_Sub24_Sub2_3495 = new Class3_Sub24_Sub2();


   final Class3_Sub24 method414() {
      try {
         Class3_Sub22 var1;
         do {
            var1 = (Class3_Sub22)this.aClass61_3489.method1221();
            if(null == var1) {
               return null;
            }
         } while(var1.aClass3_Sub24_Sub1_2507 == null);

         return var1.aClass3_Sub24_Sub1_2507;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "te.B()");
      }
   }

   public static void method463(int var0) {
      try {
         aClass153_3490 = null;
         anIntArray3491 = null;
         anIntArray3494 = null;
         if(var0 != -28918) {
            method468(-39);
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "te.C(" + var0 + ')');
      }
   }

   final Class3_Sub24 method411() {
      try {
         Class3_Sub22 var1 = (Class3_Sub22)this.aClass61_3489.method1222();
         return (Class3_Sub24)(null != var1?(null != var1.aClass3_Sub24_Sub1_2507?var1.aClass3_Sub24_Sub1_2507:this.method414()):null);
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "te.Q()");
      }
   }

   private void method464(Class3_Sub22 var1, int var3) {
      try {
         if((this.aClass3_Sub24_Sub4_3493.anIntArray3518[var1.anInt2514] & 4) != 0 && var1.anInt2506 < 0) {
            int var4 = this.aClass3_Sub24_Sub4_3493.anIntArray3509[var1.anInt2514] / Class21.anInt443;
            int var5 = (var4 + 1048575 + -var1.anInt2516) / var4;
            var1.anInt2516 = 1048575 & var4 * var3 + var1.anInt2516;
            if(var5 <= var3) {
               if(0 == this.aClass3_Sub24_Sub4_3493.anIntArray3519[var1.anInt2514]) {
                  var1.aClass3_Sub24_Sub1_2507 = Class3_Sub24_Sub1.method432(var1.aClass3_Sub12_Sub1_2509, var1.aClass3_Sub24_Sub1_2507.method438(), var1.aClass3_Sub24_Sub1_2507.method425(), var1.aClass3_Sub24_Sub1_2507.method451());
               } else {
                  var1.aClass3_Sub24_Sub1_2507 = Class3_Sub24_Sub1.method432(var1.aClass3_Sub12_Sub1_2509, var1.aClass3_Sub24_Sub1_2507.method438(), 0, var1.aClass3_Sub24_Sub1_2507.method451());
                  this.aClass3_Sub24_Sub4_3493.method501(var1, var1.aClass3_Sub15_2527.aShortArray2434[var1.anInt2520] < 0, (byte)-101);
               }

               if(var1.aClass3_Sub15_2527.aShortArray2434[var1.anInt2520] < 0) {
                  Objects.requireNonNull(var1.aClass3_Sub24_Sub1_2507).method429(-1);
               }

               var3 = var1.anInt2516 / var4;
            }
         }

         Objects.requireNonNull(var1.aClass3_Sub24_Sub1_2507).method415(var3);

      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "te.P(" + (var1 != null?"{...}":"null") + ',' + 7 + ',' + var3 + ')');
      }
   }

   static void method465() {
      try {

         Class140_Sub4.aClass93_2792.method1522(-128, 5);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "te.O(" + 5 + ',' + true + ')');
      }
   }

   final int method409() {
      try {
         return 0;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "te.D()");
      }
   }

   static Class3_Sub28_Sub6 method466(int var1, int var2) {
      try {
         Class3_Sub28_Sub6 var3 = (Class3_Sub28_Sub6)Client.aClass130_2194.method1780((long)var2 | (long)var1 << 32, 0);
         if(null == var3) {
            var3 = new Class3_Sub28_Sub6(var1, var2);
            Client.aClass130_2194.method1779(var3, var3.aLong71);
         }

         return var3;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "te.F(" + 4 + ',' + var1 + ',' + var2 + ')');
      }
   }

   static boolean method467(RSString var0) {
      try {
          if (var0 != null) {
              for (int var2 = 0; var2 < Class3_Sub28_Sub5.anInt3591; ++var2) {
                  if (var0.equals(63, Class3_Sub13_Sub27.aClass94Array3341[var2])) {
                      return true;
                  }
              }

          }
          return false;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "te.A(" + "{...}" + ',' + 0 + ')');
      }
   }

   static void method468(int var0) {
      try {
         Class88.method1456();
         Class2.anInterface5Array70 = new Interface5[7];
         Class2.anInterface5Array70[1] = new Class160();
         Class2.anInterface5Array70[2] = new Class125();
         Class2.anInterface5Array70[3] = new Class165();
         Class2.anInterface5Array70[4] = new Class112();
         Class2.anInterface5Array70[5] = new Class104();
         Class2.anInterface5Array70[var0] = new Class147();
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "te.T(" + var0 + ')');
      }
   }

   final void method415(int var1) {
      try {
         this.aClass3_Sub24_Sub2_3495.method415(var1);

         for(Class3_Sub22 var3 = (Class3_Sub22)this.aClass61_3489.method1222(); var3 != null; var3 = (Class3_Sub22)this.aClass61_3489.method1221()) {
            if(this.aClass3_Sub24_Sub4_3493.method504(var3, 121)) {
               int var2 = var1;

               while(true) {
                  if(var2 > var3.anInt2512) {
                     this.method464(var3, var3.anInt2512);
                     var2 -= var3.anInt2512;
                     if(this.aClass3_Sub24_Sub4_3493.method492(var2, 0, var3, (int[]) null)) {
                        continue;
                     }
                     break;
                  }

                  this.method464(var3, var2);
                  var3.anInt2512 -= var2;
                  break;
               }
            }
         }

      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "te.R(" + var1 + ')');
      }
   }

   final void method413(int[] var1, int var2, int var3) {
      try {
         this.aClass3_Sub24_Sub2_3495.method413(var1, var2, var3);

         for(Class3_Sub22 var6 = (Class3_Sub22)this.aClass61_3489.method1222(); var6 != null; var6 = (Class3_Sub22)this.aClass61_3489.method1221()) {
            if(this.aClass3_Sub24_Sub4_3493.method504(var6, 126)) {
               int var5 = var3;
               int var4 = var2;

               while(true) {
                  if(var5 > var6.anInt2512) {
                     this.method469(var1, var6, var4, var6.anInt2512, var5 + var4);
                     var5 -= var6.anInt2512;
                     var4 += var6.anInt2512;
                     if(this.aClass3_Sub24_Sub4_3493.method492(var5, var4, var6, var1)) {
                        continue;
                     }
                     break;
                  }

                  this.method469(var1, var6, var4, var5, var4 + var5);
                  var6.anInt2512 -= var5;
                  break;
               }
            }
         }

      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "te.E(" + (var1 != null?"{...}":"null") + ',' + var2 + ',' + var3 + ')');
      }
   }

   private void method469(int[] var1, Class3_Sub22 var2, int var3, int var4, int var5) {
      try {
         if((4 & this.aClass3_Sub24_Sub4_3493.anIntArray3518[var2.anInt2514]) != 0 && var2.anInt2506 < 0) {
            int var7 = this.aClass3_Sub24_Sub4_3493.anIntArray3509[var2.anInt2514] / Class21.anInt443;

            while(true) {
               int var8 = (-var2.anInt2516 + 1048575 + var7) / var7;
               if(var4 < var8) {
                  var2.anInt2516 += var4 * var7;
                  break;
               }

               var4 -= var8;
               var2.aClass3_Sub24_Sub1_2507.method413(var1, var3, var8);
               int var9 = Class21.anInt443 / 100;
               Class3_Sub24_Sub1 var11 = var2.aClass3_Sub24_Sub1_2507;
               int var10 = 262144 / var7;
               if(var10 < var9) {
                  var9 = var10;
               }

               var2.anInt2516 += var7 * var8 + -1048576;
               if(this.aClass3_Sub24_Sub4_3493.anIntArray3519[var2.anInt2514] == 0) {
                  var2.aClass3_Sub24_Sub1_2507 = Class3_Sub24_Sub1.method432(var2.aClass3_Sub12_Sub1_2509, var11.method438(), var11.method425(), var11.method451());
               } else {
                  var2.aClass3_Sub24_Sub1_2507 = Class3_Sub24_Sub1.method432(var2.aClass3_Sub12_Sub1_2509, var11.method438(), 0, var11.method451());
                  this.aClass3_Sub24_Sub4_3493.method501(var2, var2.aClass3_Sub15_2527.aShortArray2434[var2.anInt2520] < 0, (byte)-88);
                  var2.aClass3_Sub24_Sub1_2507.method431(var9, var11.method425());
               }

               if(var2.aClass3_Sub15_2527.aShortArray2434[var2.anInt2520] < 0) {
                  Objects.requireNonNull(var2.aClass3_Sub24_Sub1_2507).method429(-1);
               }

               var3 += var8;
               var11.method417(var9);
               var11.method413(var1, var3, var5 + -var3);
               if(var11.method445()) {
                  this.aClass3_Sub24_Sub2_3495.method457(var11);
               }
            }
         }

         var2.aClass3_Sub24_Sub1_2507.method413(var1, var3, var4);
      } catch (RuntimeException var12) {
         throw Class44.clientError(var12, "te.S(" + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ',' + var4 + ',' + var5 + ',' + (byte) 4 + ')');
      }
   }

   Class3_Sub24_Sub3(Class3_Sub24_Sub4 var1) {
      try {
         this.aClass3_Sub24_Sub4_3493 = var1;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "te.<init>(" + (var1 != null?"{...}":"null") + ')');
      }
   }

}
