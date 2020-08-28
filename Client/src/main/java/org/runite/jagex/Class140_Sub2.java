package org.runite.jagex;

final class Class140_Sub2 extends GameObject {

   static int anInt2701 = 0;
   int anInt2703;
   private Class127_Sub1 aClass127_Sub1_2704;
   static boolean aBoolean2705 = true;
   private int anInt2706 = 0;
   static RSString aClass94_2707 = RSString.createRSString("<br>(X100(U(Y");
   private final int anInt2708;
   static int[] anIntArray2709 = new int[]{0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, '\uffff', 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, Integer.MAX_VALUE, -1};
   int anInt2710;
   private AnimationDefinition aClass142_2711;
   int anInt2712;
   static boolean aBoolean2713;
   private int anInt2715 = -32768;
   int anInt2716;
   int anInt2717;
   boolean aBoolean2718;
   private int anInt2719 = 0;


   private Model method1949() {
      try {
         GraphicDefinition var2 = RenderAnimationDefinition.getGraphicDefinition((byte)42, this.anInt2708);

         Model var3;
         if(this.aBoolean2718) {
            var3 = var2.method966(-1, -1, 0);
         } else {
            int anInt2714 = -1;
            var3 = var2.method966(anInt2714, this.anInt2706, this.anInt2719);
         }

         return var3;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "bh.E(" + false + ')');
      }
   }

   static void method1950(Class140_Sub4 var0) {
      try {
         int var2 = var0.anInt2800 - Class44.anInt719;
         int var3 = 128 * var0.anInt2784 - -(64 * var0.getSize());
          int var4 = 128 * var0.anInt2835 - -(var0.getSize() * 64);
          if(0 == var0.anInt2840) {
             var0.anInt2806 = 1024;
          }

          var0.anInt2819 += (-var0.anInt2819 + var3) / var2;
          var0.anInt2829 += (var4 + -var0.anInt2829) / var2;
          if(var0.anInt2840 == 1) {
             var0.anInt2806 = 1536;
          }

          var0.anInt2824 = 0;
          if(var0.anInt2840 == 2) {
             var0.anInt2806 = 0;
          }

          if(var0.anInt2840 == 3) {
             var0.anInt2806 = 512;
          }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "bh.F(" + (var0 != null?"{...}":"null") + ',' + true + ')');
      }
   }

   static int method1951(int var0) {
      try {

         return var0 >>> 8;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bh.D(" + var0 + ',' + (byte) -115 + ')');
      }
   }

   static void method1952(int var0, int var2, int var3, int var4, int var5, int var6, int var7) {
      try {
         int var8;
         int var9;
         if(HDToolKit.highDetail) {
            var8 = -334 + var2;
            if(var8 >= 0) {
               if(var8 > 100) {
                  var8 = 100;
               }
            } else {
               var8 = 0;
            }

            var9 = var8 * (-OutputStream_Sub1.aShort46 + ObjectDefinition.aShort1535) / 100 + OutputStream_Sub1.aShort46;
            var4 = var9 * var4 >> 8;
         }

         var8 = -var7 + 2048 & 2047;
         var9 = 2047 & -var5 + 2048;
         int var10 = 0;
         int var12 = var4;
         int var11 = 0;
         int var13;
         int var14;
         if(var8 != 0) {
            var14 = Class51.anIntArray851[var8];
            var13 = Class51.anIntArray840[var8];
            var11 = var13 * -var4 >> 16;
            var12 = var14 * var4 >> 16;
         }

         if(0 != var9) {
            var13 = Class51.anIntArray840[var9];
            var14 = Class51.anIntArray851[var9];
            var10 = var13 * var12 >> 16;
            var12 = var12 * var14 >> 16;
         }

         Class139.anInt1823 = var7;
         Class3_Sub13_Sub25.anInt3315 = var5;
         Class77.anInt1111 = var6 + -var12;
         NPC.anInt3995 = var0 + -var10;
         Class7.anInt2162 = -var11 + var3;
      } catch (RuntimeException var15) {
         throw Class44.clientError(var15, "bh.G(" + var0 + ',' + -1907397104 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
      }
   }

   static WorldListEntry method1953() {
      try {

         Class3_Sub6.anInt2291 = 0;
         return ItemDefinition.method1107(5422);
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "bh.H(" + (byte) 124 + ')');
      }
   }

   final int method1871() {
      try {
         return this.anInt2715;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "bh.MA()");
      }
   }

   public static void method1954(int var0) {
      try {
         if(var0 == 0) {
            anIntArray2709 = null;
            aClass94_2707 = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "bh.B(" + var0 + ')');
      }
   }

   final void animate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, long var9, int var11, Class127_Sub1 var12) {
      try {
         Model var13 = this.method1949();
         if(var13 != null) {
            var13.animate(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11, this.aClass127_Sub1_2704);
            this.anInt2715 = var13.method1871();
         }
      } catch (RuntimeException var14) {
         throw Class44.clientError(var14, "bh.IA(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + var9 + ',' + var11 + ',' + (var12 != null?"{...}":"null") + ')');
      }
   }

   final void method1955(int var2) {
      try {
         if(!this.aBoolean2718) {

            this.anInt2719 += var2;

            while(this.aClass142_2711.duration[this.anInt2706] < this.anInt2719) {
               this.anInt2719 -= this.aClass142_2711.duration[this.anInt2706];
               ++this.anInt2706;
               if(this.anInt2706 >= this.aClass142_2711.frames.length) {
                  this.aBoolean2718 = true;
                  break;
               }
            }

         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "bh.A(" + true + ',' + var2 + ')');
      }
   }

   static void method1956(int var0, int var1, int var2, int var3) {
      Class3_Sub2 var4 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2];
      if(var4 != null) {
         Class19 var5 = var4.aClass19_2233;
         if(var5 != null) {
            var5.anInt430 = var5.anInt430 * var3 / 16;
            var5.anInt426 = var5.anInt426 * var3 / 16;
         }
      }
   }

   final void method1867(int var1, int var2, int var3, int var4, int var5) {}

   Class140_Sub2(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      try {
         this.anInt2710 = var4;
         this.anInt2716 = var3;
         this.anInt2717 = var2;
         this.anInt2703 = var7 + var6;
         this.anInt2708 = var1;
         this.anInt2712 = var5;
         int var8 = RenderAnimationDefinition.getGraphicDefinition((byte)42, this.anInt2708).anInt542;
         if(-1 == var8) {
            this.aBoolean2718 = true;
         } else {
            this.aBoolean2718 = false;
            this.aClass142_2711 = Client.getAnimationDefinition(var8);
         }

      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "bh.<init>(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
      }
   }

}
