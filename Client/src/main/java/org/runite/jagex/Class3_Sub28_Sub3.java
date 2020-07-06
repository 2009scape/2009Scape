package org.runite.jagex;

final class Class3_Sub28_Sub3 extends Node {

   int anInt3549 = 0;
   int anInt3550 = -1;
   static RSInterface aClass11_3551;
   static int anInt3552;
   boolean aBoolean3553 = true;
   RSString aClass94_3554;
   int anInt3555 = 12800;
   int anInt3556;
   static int anInt3557 = 0;
   int anInt3558;
   int anInt3559 = 0;
   Class61 aClass61_3560;
   RSString aClass94_3561;
   int anInt3562 = 12800;
   int anInt3563 = -1;
   static int anInt3564 = 0;


   final boolean method537(int var1, byte var2, int var3) {
      try {
         if(~this.anInt3555 >= ~var3 && var3 <= this.anInt3559 && var1 >= this.anInt3562 && var1 <= this.anInt3549) {
            for(Class3_Sub21 var4 = (Class3_Sub21)this.aClass61_3560.method1222(); var4 != null; var4 = (Class3_Sub21)this.aClass61_3560.method1221()) {
               if(var4.method393((byte)-45, var1, var3)) {
                  return true;
               }
            }

            if(var2 != 97) {
               method544(-51, 82);
            }

            return false;
         } else {
            return false;
         }
      } catch (RuntimeException var5) {
         throw Class44.method1067(var5, "bn.B(" + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   final void method538(byte var1) {
      try {
         this.anInt3562 = 12800;
         this.anInt3559 = 0;
         if(var1 != 103) {
            aClass11_3551 = (RSInterface)null;
         }

         this.anInt3549 = 0;
         this.anInt3555 = 12800;

         for(Class3_Sub21 var2 = (Class3_Sub21)this.aClass61_3560.method1222(); null != var2; var2 = (Class3_Sub21)this.aClass61_3560.method1221()) {
            if(~var2.anInt2494 > ~this.anInt3562) {
               this.anInt3562 = var2.anInt2494;
            }

            if(~var2.anInt2492 > ~this.anInt3555) {
               this.anInt3555 = var2.anInt2492;
            }

            if(var2.anInt2495 > this.anInt3559) {
               this.anInt3559 = var2.anInt2495;
            }

            if(this.anInt3549 < var2.anInt2497) {
               this.anInt3549 = var2.anInt2497;
            }
         }

      } catch (RuntimeException var3) {
         throw Class44.method1067(var3, "bn.F(" + var1 + ')');
      }
   }

   static final void method539(int var0, int var1) {
      try {
         if(var0 != 0) {
            method542((byte)73);
         }

         Class136.aClass93_1772.method1522(var0 ^ -126, var1);
      } catch (RuntimeException var3) {
         throw Class44.method1067(var3, "bn.O(" + var0 + ',' + var1 + ')');
      }
   }

   static final int method540(int var0, int var1, int var2) {
      try {
         if(var1 != -14314) {
            return 116;
         } else {
            int var3;
            for(var3 = 0; -1 > ~var0; --var0) {
               var3 = var3 << 1 | 1 & var2;
               var2 >>>= 1;
            }

            return var3;
         }
      } catch (RuntimeException var4) {
         throw Class44.method1067(var4, "bn.P(" + var0 + ',' + var1 + ',' + var2 + ')');
      }
   }

   static final void method541(byte var0, boolean var1, RSString var2) {
      try {
         var2 = var2.method1534(-98);
         int var4 = 0;
         int var5 = -26 / ((62 - var0) / 58);
         short[] var3 = new short[16];
         int var6 = !var1?0:'\u8000';
         int var7 = (!var1?Class83.anInt1156:RenderAnimationDefinition.anInt377) + var6;

         for(int var8 = var6; var8 < var7; ++var8) {
            Class3_Sub28_Sub4 var9 = Class3_Sub29.method733(12345678, var8);
            if(var9.aBoolean3568 && var9.method554(-1).method1534(-98).indexOf(var2, 112) != -1) {
               if(var4 >= 50) {
                  Class62.anInt952 = -1;
                  Class99.aShortArray1398 = null;
                  return;
               }

               if(var4 >= var3.length) {
                  short[] var10 = new short[2 * var3.length];

                  for(int var11 = 0; var4 > var11; ++var11) {
                     var10[var11] = var3[var11];
                  }

                  var3 = var10;
               }

               var3[var4++] = (short)var8;
            }
         }

         Class99.aShortArray1398 = var3;
         Class62.anInt952 = var4;
         Class140_Sub4.anInt2756 = 0;
         RSString[] var13 = new RSString[Class62.anInt952];

         for(int var14 = 0; Class62.anInt952 > var14; ++var14) {
            var13[var14] = Class3_Sub29.method733(12345678, var3[var14]).method554(-1);
         }

         Class3_Sub13_Sub29.method307(var13, Class99.aShortArray1398, 100);
      } catch (RuntimeException var12) {
         throw Class44.method1067(var12, "bn.C(" + var0 + ',' + var1 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   public static void method542(byte var0) {
      try {
         if(var0 != -46) {
            anInt3552 = 7;
         }

         aClass11_3551 = null;
      } catch (RuntimeException var2) {
         throw Class44.method1067(var2, "bn.D(" + var0 + ')');
      }
   }

   static final int method543(int var0, int var1, byte var2) {
      try {
         if(var2 > -71) {
            return -52;
         } else {
            int var3 = NPC.method1984(var0 + -1, 38, -1 + var1) - -NPC.method1984(1 + var0, 38, -1 + var1) - (-NPC.method1984(-1 + var0, 38, var1 - -1) + -NPC.method1984(var0 + 1, 38, var1 - -1));
            int var4 = NPC.method1984(var0 + -1, 38, var1) + NPC.method1984(var0 + 1, 38, var1) + (NPC.method1984(var0, 38, -1 + var1) - -NPC.method1984(var0, 38, 1 + var1));
            int var5 = NPC.method1984(var0, 38, var1);
            return var4 / 8 + var3 / 16 - -(var5 / 4);
         }
      } catch (RuntimeException var6) {
         throw Class44.method1067(var6, "bn.A(" + var0 + ',' + var1 + ',' + var2 + ')');
      }
   }

   static final boolean method544(int var0, int var1) {
      try {
         return var0 != -49?true:-49 >= ~var1 && 57 >= var1;
      } catch (RuntimeException var3) {
         throw Class44.method1067(var3, "bn.E(" + var0 + ',' + var1 + ')');
      }
   }

   Class3_Sub28_Sub3(RSString var1, RSString var2, int var3, int var4, int var5, boolean var6, int var7) {
      try {
         this.anInt3556 = var4;
         this.anInt3550 = var5;
         this.aBoolean3553 = var6;
         this.aClass94_3561 = var1;
         this.aClass94_3554 = var2;
         this.anInt3563 = var7;
         this.anInt3558 = var3;
         if(-256 == ~this.anInt3563) {
            this.anInt3563 = 0;
         }

         this.aClass61_3560 = new Class61();
      } catch (RuntimeException var9) {
         throw Class44.method1067(var9, "bn.<init>(" + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
      }
   }

}
