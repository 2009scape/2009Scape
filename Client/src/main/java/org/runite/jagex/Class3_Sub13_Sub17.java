package org.runite.jagex;

import java.util.Objects;

final class Class3_Sub13_Sub17 extends Class3_Sub13 {

   static boolean aBoolean3207 = false;
   static Class130 aClass130_3208 = new Class130(8);
   static RSString aClass94_3209 = RSString.createRSString("showingVideoAd");
   static CacheIndex aClass153_3210;
   static int[] anIntArray3212;
   static int anInt3213 = 1;


   public Class3_Sub13_Sub17() {
      super(1, false);
   }

   static void method246(int var0) {
      try {
         Class3_Sub28_Sub20.method724();
         Class3_Sub28_Sub18.method710();
         Class108.method1654(var0 ^ -15455);
         Canvas_Sub1.method55();
         Class163_Sub2_Sub1.method2222();
         Class3_Sub30_Sub1.method813();
         Class133.method1803();
         Class38.method1025((byte)-93);
         Class40.method1044(-3782);
         Class136.aClass93_1772.method1523((byte)-99);
         Class158_Sub1.method2192();
         Class159.method2196();
         Class50.method1132(103);
         Class3_Sub13_Sub6.method196(false);
         if(var0 != 8) {
            method246(-120);
         }

         Class115.method1714();
         Class3_Sub28_Sub6.h();
         Class107.method1650();
         MouseListeningClass.method2089();
         Class3_Sub13_Sub11.aClass93_3130.method1523((byte)-103);
         Class80.aClass93_1135.method1523((byte)-122);
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "je.O(" + var0 + ')');
      }
   }

   static void method247(byte var0) {
      try {
         if(Class140_Sub2.aBoolean2713) {
            AnimationDefinition.aClass109_1856 = null;
            Class140_Sub2.aBoolean2713 = false;
            Class40.aClass3_Sub28_Sub16_680 = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "je.F(" + var0 + ')');
      }
   }

   private void method248(int var1, byte var2, int var3) {
      try {
         if(var2 > 80) {
            int var4 = Class102.anIntArray2125[var3];
            int var5 = Class163_Sub3.anIntArray2999[var1];
            float var6 = (float)Math.atan2((double)(var4 - 2048), (double)(var5 - 2048));
            if((double)var6 >= -3.141592653589793D && -2.356194490192345D >= (double)var6) {
               Class50.anInt828 = var1;
               Class159.anInt2024 = var3;
            } else if((double)var6 <= -1.5707963267948966D && -2.356194490192345D <= (double)var6) {
               Class159.anInt2024 = var1;
               Class50.anInt828 = var3;
            } else if((double)var6 <= -0.7853981633974483D && (double)var6 >= -1.5707963267948966D) {
               Class159.anInt2024 = -var1 + Class113.anInt1559;
               Class50.anInt828 = var3;
            } else if(0.0F >= var6 && (double)var6 >= -0.7853981633974483D) {
               Class159.anInt2024 = var3;
               Class50.anInt828 = Class101.anInt1427 - var1;
            } else if(var6 >= 0.0F && (double)var6 <= 0.7853981633974483D) {
               Class159.anInt2024 = -var3 + Class113.anInt1559;
               Class50.anInt828 = -var1 + Class101.anInt1427;
            } else if((double)var6 >= 0.7853981633974483D && (double)var6 <= 1.5707963267948966D) {
               Class159.anInt2024 = -var1 + Class113.anInt1559;
               Class50.anInt828 = -var3 + Class101.anInt1427;
            } else if((double)var6 >= 1.5707963267948966D && 2.356194490192345D >= (double)var6) {
               Class50.anInt828 = -var3 + Class101.anInt1427;
               Class159.anInt2024 = var1;
            } else if(2.356194490192345D <= (double)var6 && (double)var6 <= 3.141592653589793D) {
               Class159.anInt2024 = -var3 + Class113.anInt1559;
               Class50.anInt828 = var1;
            }

            Class159.anInt2024 &= RenderAnimationDefinition.anInt396;
            Class50.anInt828 &= Class3_Sub20.anInt2487;
         }
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "je.Q(" + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   final void method157(int var1, RSByteBuffer var2, boolean var3) {
      try {
         if(var1 == 0) {
            this.aBoolean2375 = var2.getByteB() == 1;
         }

         if(!var3) {
            aClass153_3210 = (CacheIndex)null;
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "je.A(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

   public static void method249(int var0) {
      try {
         anIntArray3212 = null;
         aClass130_3208 = null;
         aClass153_3210 = null;
         aClass94_3209 = null;
         if(var0 >= -100) {
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "je.R(" + var0 + ')');
      }
   }

   static void method250(CacheIndex var1) {
      try {
         Class8.aClass153_105 = var1;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "je.C(" + 2048 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   static int method251() {
      try {
         if(Class119.aClass131_1624 == null) {
            return -1;
         } else {
            while(Class119.aClass131_1624.anInt1720 > Class136.anInt1780) {
               if(Class119.aClass131_1624.method1794(Class136.anInt1780, -20138)) {
                  return Class136.anInt1780++;
               }

               ++Class136.anInt1780;
            }

            return -1;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "je.B(" + -1 + ')');
      }
   }

   static void method252() {
      try {
         Class3_Sub9 var1;
         for(var1 = (Class3_Sub9) aClass61_78.method1222(); null != var1; var1 = (Class3_Sub9) aClass61_78.method1221()) {
            if(var1.aBoolean2329) {
               var1.method134();
            }
         }

         for(var1 = (Class3_Sub9)IOHandler.aClass61_1242.method1222(); null != var1; var1 = (Class3_Sub9)IOHandler.aClass61_1242.method1221()) {
            if(var1.aBoolean2329) {
               var1.method134();
            }
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "je.S(" + 8 + ')');
      }
   }

   final int[] method154(int var1, byte var2) {
      try {
         int[] var4 = this.aClass114_2382.method1709(var1);
         if(this.aClass114_2382.aBoolean1580) {
            for(int var5 = 0; var5 < Class113.anInt1559; ++var5) {
               this.method248(var1, (byte)105, var5);
               int[] var6 = this.method152(0, Class50.anInt828, 32755);
               var4[var5] = var6[Class159.anInt2024];
            }
         }

         return var4;
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "je.D(" + var1 + ',' + var2 + ')');
      }
   }

   static void method253(int var1, int var2, int var3, int var4) {
      try {

         Class3_Sub28_Sub1.anInt3536 = Class23.anInt455 * var3 / var1;
         Class3_Sub4.anInt2251 = Class108.anInt1460 * var2 / var4;
         Class82.anInt1150 = -1;
         Class3_Sub13_Sub30.anInt3362 = -1;
         Class3_Sub5.method117();
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "je.E(" + -22611 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
      }
   }

   final int[][] method166(int var1, int var2) {
      try {
         if(var1 != -1) {
            this.method166(-38, 67);
         }

         int[][] var3 = this.aClass97_2376.method1594((byte)-125, var2);
         if(this.aClass97_2376.aBoolean1379) {
            int[] var4 = var3[0];
            int[] var6 = var3[2];
            int[] var5 = var3[1];

            for(int var7 = 0; Class113.anInt1559 > var7; ++var7) {
               this.method248(var2, (byte)107, var7);
               int[][] var8 = this.method162(Class50.anInt828, 0, (byte)-49);
               var4[var7] = Objects.requireNonNull(var8)[0][Class159.anInt2024];
               var5[var7] = var8[1][Class159.anInt2024];
               var6[var7] = var8[2][Class159.anInt2024];
            }
         }

         return var3;
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "je.T(" + var1 + ',' + var2 + ')');
      }
   }

}
