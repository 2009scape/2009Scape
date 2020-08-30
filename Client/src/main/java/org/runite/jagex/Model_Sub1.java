package org.runite.jagex;

final class Model_Sub1 extends GameObject {

   byte[] aByteArray2843;
   private short aShort2844;
   byte[] aByteArray2845;
   short[] aShortArray2846;
   int[] anIntArray2847;
   byte aByte2848 = 0;
   int anInt2849 = 0;
   private short aShort2850;
   short[] aShortArray2851;
   byte[] aByteArray2852;
   private boolean aBoolean2853 = false;
   private short aShort2854;
   short[] aShortArray2855;
   int[][] anIntArrayArray2856;
   byte[] aByteArray2857;
   short[] aShortArray2858;
   byte[] aByteArray2859;
   int[] anIntArray2860;
   private static int[] anIntArray2861 = new int[10000];
   int anInt2862;
   private static int[] anIntArray2863 = Class51.anIntArray851;
   int[] anIntArray2864;
   int[] anIntArray2865;
   byte[] aByteArray2866;
   byte[] aByteArray2867;
   private static int anInt2868 = 0;
   byte[] aByteArray2869;
   short[] aShortArray2870;
   private static int[] anIntArray2871 = Class51.anIntArray840;
   Class50[] aClass50Array2872;
   private short aShort2873;
   private short aShort2874;
   private static int[] anIntArray2875 = new int[10000];
   short aShort2876;
   byte[] aByteArray2877;
   int[] anIntArray2878;
   short aShort2879;
   private short aShort2880;
   int[] anIntArray2881;
   short[] aShortArray2882;
   Class50[] aClass50Array2883;
   short[] aShortArray2884;
   int[] anIntArray2885;
   Class120[] aClass120Array2886;
   int anInt2887 = 0;
   short[] aShortArray2888;
   byte[] aByteArray2889;
   int[][] anIntArrayArray2890;
   short[] aShortArray2891;
   int[] anIntArray2892;
   short[] aShortArray2893;


   final void method1989() {
      for(int var1 = 0; var1 < this.anInt2887; ++var1) {
         this.anIntArray2885[var1] = -this.anIntArray2885[var1];
         this.anIntArray2892[var1] = -this.anIntArray2892[var1];
      }

      this.method2007();
   }

   public static void method1990() {
      anIntArray2861 = null;
      anIntArray2875 = null;
      anIntArray2871 = null;
      anIntArray2863 = null;
   }

   final void method1991() {
      for(int var1 = 0; var1 < this.anInt2887; ++var1) {
         int var2 = this.anIntArray2885[var1];
         this.anIntArray2885[var1] = this.anIntArray2892[var1];
         this.anIntArray2892[var1] = -var2;
      }

      this.method2007();
   }

   private void method1992(int var1) {
      int var2 = anIntArray2871[var1];
      int var3 = anIntArray2863[var1];

      for(int var4 = 0; var4 < this.anInt2887; ++var4) {
         int var5 = this.anIntArray2881[var4] * var2 + this.anIntArray2885[var4] * var3 >> 16;
         this.anIntArray2881[var4] = this.anIntArray2881[var4] * var3 - this.anIntArray2885[var4] * var2 >> 16;
         this.anIntArray2885[var4] = var5;
      }

      this.method2007();
   }

   private void method1993() {
      if(!this.aBoolean2853) {
         this.aBoolean2853 = true;
         int var1 = 32767;
         int var2 = 32767;
         int var3 = 32767;
         int var4 = -32768;
         int var5 = -32768;
         int var6 = -32768;

         for(int var7 = 0; var7 < this.anInt2887; ++var7) {
            int var8 = this.anIntArray2885[var7];
            int var9 = this.anIntArray2881[var7];
            int var10 = this.anIntArray2892[var7];
            if(var8 < var1) {
               var1 = var8;
            }

            if(var8 > var4) {
               var4 = var8;
            }

            if(var9 < var2) {
               var2 = var9;
            }

            if(var9 > var5) {
               var5 = var9;
            }

            if(var10 < var3) {
               var3 = var10;
            }

            if(var10 > var6) {
               var6 = var10;
            }
         }

         this.aShort2850 = (short)var1;
         this.aShort2873 = (short)var4;
         this.aShort2854 = (short)var2;
         this.aShort2844 = (short)var5;
         this.aShort2880 = (short)var3;
         this.aShort2874 = (short)var6;
      }
   }

   final void method1994(int var1, int var2, int var3) {
      for(int var4 = 0; var4 < this.anInt2887; ++var4) {
         this.anIntArray2885[var4] = this.anIntArray2885[var4] * var1 / 128;
         this.anIntArray2881[var4] = this.anIntArray2881[var4] * var2 / 128;
         this.anIntArray2892[var4] = this.anIntArray2892[var4] * var3 / 128;
      }

      this.method2007();
   }

   final GameObject method1861() {
      return this.method2008(this.aShort2879, this.aShort2876, -50, -10, -50);
   }

   private int method1995(Model_Sub1 var1, int var2, short var3) {
      int var4 = var1.anIntArray2885[var2];
      int var5 = var1.anIntArray2881[var2];
      int var6 = var1.anIntArray2892[var2];

      for(int var7 = 0; var7 < this.anInt2887; ++var7) {
         if(var4 == this.anIntArray2885[var7] && var5 == this.anIntArray2881[var7] && var6 == this.anIntArray2892[var7]) {
            this.aShortArray2893[var7] |= var3;
            return var7;
         }
      }

      this.anIntArray2885[this.anInt2887] = var4;
      this.anIntArray2881[this.anInt2887] = var5;
      this.anIntArray2892[this.anInt2887] = var6;
      this.aShortArray2893[this.anInt2887] = var3;
      if(var1.anIntArray2860 != null) {
         this.anIntArray2860[this.anInt2887] = var1.anIntArray2860[var2];
      }

      return this.anInt2887++;
   }

   private void method1996(int[][] var1, int var2, int var3, int var4, int var5, int var6) {
      int var10 = -var5 / 2;
      int var11 = -var6 / 2;
      int var12 = method2009(var1, var2 + var10, var4 + var11);
      int var13 = var5 / 2;
      int var14 = -var6 / 2;
      int var15 = method2009(var1, var2 + var13, var4 + var14);
      int var16 = -var5 / 2;
      int var17 = var6 / 2;
      int var18 = method2009(var1, var2 + var16, var4 + var17);
      int var19 = var5 / 2;
      int var20 = var6 / 2;
      int var21 = method2009(var1, var2 + var19, var4 + var20);
      int var22 = var12 < var15?var12:var15;
      int var23 = var18 < var21?var18:var21;
      int var24 = var15 < var21?var15:var21;
      int var25 = var12 < var18?var12:var18;
      if(var6 != 0) {
         int var26 = (int)(Math.atan2((double)(var22 - var23), (double)var6) * 325.95D) & 2047;
         if(var26 != 0) {
            this.method2006(var26);
         }
      }

      if(var5 != 0) {
         int var27 = (int)(Math.atan2((double)(var25 - var24), (double)var5) * 325.95D) & 2047;
         if(var27 != 0) {
            this.method1992(var27);
         }
      }

      int var28 = var12 + var21;
      if(var15 + var18 < var28) {
         var28 = var15 + var18;
      }

      var28 = (var28 >> 1) - var3;
      if(var28 != 0) {
         this.method2001(0, var28, 0);
      }

   }

   final void animate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, long var9, int var11, Class127_Sub1 var12) {}

   final void method1997() {
      if(this.aClass50Array2872 == null) {
         this.aClass50Array2872 = new Class50[this.anInt2887];

         int var1;
         for(var1 = 0; var1 < this.anInt2887; ++var1) {
            this.aClass50Array2872[var1] = new Class50();
         }

         for(var1 = 0; var1 < this.anInt2849; ++var1) {
            int var2 = this.anIntArray2865[var1];
            int var3 = this.anIntArray2878[var1];
            int var4 = this.anIntArray2864[var1];
            int var5 = this.anIntArray2885[var3] - this.anIntArray2885[var2];
            int var6 = this.anIntArray2881[var3] - this.anIntArray2881[var2];
            int var7 = this.anIntArray2892[var3] - this.anIntArray2892[var2];
            int var8 = this.anIntArray2885[var4] - this.anIntArray2885[var2];
            int var9 = this.anIntArray2881[var4] - this.anIntArray2881[var2];
            int var10 = this.anIntArray2892[var4] - this.anIntArray2892[var2];
            int var11 = var6 * var10 - var9 * var7;
            int var12 = var7 * var8 - var10 * var5;

            int var13;
            for(var13 = var5 * var9 - var8 * var6; var11 > 8192 || var12 > 8192 || var13 > 8192 || var11 < -8192 || var12 < -8192 || var13 < -8192; var13 >>= 1) {
               var11 >>= 1;
               var12 >>= 1;
            }

            int var14 = (int)Math.sqrt((double)(var11 * var11 + var12 * var12 + var13 * var13));
            if(var14 <= 0) {
               var14 = 1;
            }

            var11 = var11 * 256 / var14;
            var12 = var12 * 256 / var14;
            var13 = var13 * 256 / var14;
            byte var15;
            if(this.aByteArray2859 == null) {
               var15 = 0;
            } else {
               var15 = this.aByteArray2859[var1];
            }

            if(var15 == 0) {
               Class50 var16 = this.aClass50Array2872[var2];
               var16.anInt831 += var11;
               var16.anInt821 += var12;
               var16.anInt830 += var13;
               ++var16.anInt823;
               var16 = this.aClass50Array2872[var3];
               var16.anInt831 += var11;
               var16.anInt821 += var12;
               var16.anInt830 += var13;
               ++var16.anInt823;
               var16 = this.aClass50Array2872[var4];
               var16.anInt831 += var11;
               var16.anInt821 += var12;
               var16.anInt830 += var13;
               ++var16.anInt823;
            } else if(var15 == 1) {
               if(this.aClass120Array2886 == null) {
                  this.aClass120Array2886 = new Class120[this.anInt2849];
               }

               Class120 var17 = this.aClass120Array2886[var1] = new Class120();
               var17.anInt1634 = var11;
               var17.anInt1635 = var12;
               var17.anInt1632 = var13;
            }
         }

      }
   }

   final void method1998(short var1, short var2) {
      if(this.aShortArray2858 != null) {
         for(int var3 = 0; var3 < this.anInt2849; ++var3) {
            if(this.aShortArray2858[var3] == var1) {
               this.aShortArray2858[var3] = var2;
            }
         }

      }
   }

   final Model_Sub1 method1999(int var1, int var2, int[][] var3, int[][] var4, int var5, int var6, int var7) {
      this.method1993();
      int var10 = var5 + this.aShort2850;
      int var11 = var5 + this.aShort2873;
      int var12 = var7 + this.aShort2880;
      int var13 = var7 + this.aShort2874;
      if((var1 == 1 || var1 == 2 || var1 == 3 || var1 == 5) && (var10 < 0 || var11 + 128 >> 7 >= var3.length || var12 < 0 || var13 + 128 >> 7 >= var3[0].length)) {
         return this;
      } else {
         if(var1 == 4 || var1 == 5) {
            if(var4 == null) {
               return this;
            }

            if(var10 < 0 || var11 + 128 >> 7 >= var4.length || var12 < 0 || var13 + 128 >> 7 >= var4[0].length) {
               return this;
            }
         } else {
            var10 >>= 7;
            var11 = var11 + 127 >> 7;
            var12 >>= 7;
            var13 = var13 + 127 >> 7;
            if(var3[var10][var12] == var6 && var3[var11][var12] == var6 && var3[var10][var13] == var6 && var3[var11][var13] == var6) {
               return this;
            }
         }

          Model_Sub1 var14;
         var14 = new Model_Sub1();
         var14.anInt2887 = this.anInt2887;
         var14.anInt2849 = this.anInt2849;
         var14.anInt2862 = this.anInt2862;
         var14.anIntArray2865 = this.anIntArray2865;
         var14.anIntArray2878 = this.anIntArray2878;
         var14.anIntArray2864 = this.anIntArray2864;
         var14.aByteArray2859 = this.aByteArray2859;
         var14.aByteArray2889 = this.aByteArray2889;
         var14.aByteArray2843 = this.aByteArray2843;
         var14.aByteArray2866 = this.aByteArray2866;
         var14.aShortArray2870 = this.aShortArray2870;
         var14.aShortArray2858 = this.aShortArray2858;
         var14.aByte2848 = this.aByte2848;
         var14.aByteArray2857 = this.aByteArray2857;
         var14.aShortArray2884 = this.aShortArray2884;
         var14.aShortArray2846 = this.aShortArray2846;
         var14.aShortArray2891 = this.aShortArray2891;
         var14.aShortArray2888 = this.aShortArray2888;
         var14.aShortArray2882 = this.aShortArray2882;
         var14.aShortArray2851 = this.aShortArray2851;
         var14.aByteArray2845 = this.aByteArray2845;
         var14.aByteArray2867 = this.aByteArray2867;
         var14.aByteArray2877 = this.aByteArray2877;
         var14.aByteArray2852 = this.aByteArray2852;
         var14.aByteArray2869 = this.aByteArray2869;
         var14.anIntArray2860 = this.anIntArray2860;
         var14.anIntArray2847 = this.anIntArray2847;
         var14.anIntArrayArray2890 = this.anIntArrayArray2890;
         var14.anIntArrayArray2856 = this.anIntArrayArray2856;
         var14.aShort2879 = this.aShort2879;
         var14.aShort2876 = this.aShort2876;
         var14.aClass50Array2872 = this.aClass50Array2872;
         var14.aClass120Array2886 = this.aClass120Array2886;
         var14.aClass50Array2883 = this.aClass50Array2883;
         if(var1 == 3) {
            var14.anIntArray2885 = Class65.method1233(this.anIntArray2885, 2);
            var14.anIntArray2881 = Class65.method1233(this.anIntArray2881, 2);
            var14.anIntArray2892 = Class65.method1233(this.anIntArray2892, 2);
         } else {
            var14.anIntArray2885 = this.anIntArray2885;
            var14.anIntArray2881 = new int[var14.anInt2887];
            var14.anIntArray2892 = this.anIntArray2892;
         }

         int var15;
         int var17;
         int var16;
         int var19;
         int var18;
         int var21;
         int var20;
         int var23;
         int var22;
         int var24;
         if(var1 == 1) {
            for(var15 = 0; var15 < var14.anInt2887; ++var15) {
               var16 = this.anIntArray2885[var15] + var5;
               var17 = this.anIntArray2892[var15] + var7;
               var18 = var16 & 127;
               var19 = var17 & 127;
               var20 = var16 >> 7;
               var21 = var17 >> 7;
               var22 = var3[var20][var21] * (128 - var18) + var3[var20 + 1][var21] * var18 >> 7;
               var23 = var3[var20][var21 + 1] * (128 - var18) + var3[var20 + 1][var21 + 1] * var18 >> 7;
               var24 = var22 * (128 - var19) + var23 * var19 >> 7;
               var14.anIntArray2881[var15] = this.anIntArray2881[var15] + var24 - var6;
            }
         } else {
            int var25;
            if(var1 == 2) {
               for(var15 = 0; var15 < var14.anInt2887; ++var15) {
                  var16 = (this.anIntArray2881[var15] << 16) / this.aShort2854;
                  if(var16 < var2) {
                     var17 = this.anIntArray2885[var15] + var5;
                     var18 = this.anIntArray2892[var15] + var7;
                     var19 = var17 & 127;
                     var20 = var18 & 127;
                     var21 = var17 >> 7;
                     var22 = var18 >> 7;
                     var23 = var3[var21][var22] * (128 - var19) + var3[var21 + 1][var22] * var19 >> 7;
                     var24 = var3[var21][var22 + 1] * (128 - var19) + var3[var21 + 1][var22 + 1] * var19 >> 7;
                     var25 = var23 * (128 - var20) + var24 * var20 >> 7;
                     var14.anIntArray2881[var15] = this.anIntArray2881[var15] + (var25 - var6) * (var2 - var16) / var2;
                  } else {
                     var14.anIntArray2881[var15] = this.anIntArray2881[var15];
                  }
               }
            } else if(var1 == 3) {
               var15 = (var2 & 255) * 4;
               var16 = (var2 >> 8 & 255) * 4;
               this.method1996(var3, var5, var6, var7, var15, var16);
            } else if(var1 == 4) {
               var15 = this.aShort2844 - this.aShort2854;

               for(var16 = 0; var16 < this.anInt2887; ++var16) {
                  var17 = this.anIntArray2885[var16] + var5;
                  var18 = this.anIntArray2892[var16] + var7;
                  var19 = var17 & 127;
                  var20 = var18 & 127;
                  var21 = var17 >> 7;
                  var22 = var18 >> 7;
                  var23 = var4[var21][var22] * (128 - var19) + var4[var21 + 1][var22] * var19 >> 7;
                  var24 = var4[var21][var22 + 1] * (128 - var19) + var4[var21 + 1][var22 + 1] * var19 >> 7;
                  var25 = var23 * (128 - var20) + var24 * var20 >> 7;
                  var14.anIntArray2881[var16] = this.anIntArray2881[var16] + (var25 - var6) + var15;
               }
            } else if(var1 == 5) {
               var15 = this.aShort2844 - this.aShort2854;

               for(var16 = 0; var16 < this.anInt2887; ++var16) {
                  var17 = this.anIntArray2885[var16] + var5;
                  var18 = this.anIntArray2892[var16] + var7;
                  var19 = var17 & 127;
                  var20 = var18 & 127;
                  var21 = var17 >> 7;
                  var22 = var18 >> 7;
                  var23 = var3[var21][var22] * (128 - var19) + var3[var21 + 1][var22] * var19 >> 7;
                  var24 = var3[var21][var22 + 1] * (128 - var19) + var3[var21 + 1][var22 + 1] * var19 >> 7;
                  var25 = var23 * (128 - var20) + var24 * var20 >> 7;
                  var23 = var4[var21][var22] * (128 - var19) + var4[var21 + 1][var22] * var19 >> 7;
                  var24 = var4[var21][var22 + 1] * (128 - var19) + var4[var21 + 1][var22 + 1] * var19 >> 7;
                  int var26 = var23 * (128 - var20) + var24 * var20 >> 7;
                  int var27 = var25 - var26;
                  var14.anIntArray2881[var16] = ((this.anIntArray2881[var16] << 8) / var15 * var27 >> 8) - (var6 - var25);
               }
            }
         }

         this.aBoolean2853 = false;

         return var14;
      }
   }

   final Class140_Sub1_Sub2 method2000(int var1, int var2) {
      return new Class140_Sub1_Sub2(this, var1, var2, -50, -10, -50);
   }

   final void method1867(int var1, int var2, int var3, int var4, int var5) {}

   final void method2001(int var1, int var2, int var3) {
      for(int var4 = 0; var4 < this.anInt2887; ++var4) {
         this.anIntArray2885[var4] += var1;
         this.anIntArray2881[var4] += var2;
         this.anIntArray2892[var4] += var3;
      }

      this.method2007();
   }

   final void method2002() {
      int var1;
      for(var1 = 0; var1 < this.anInt2887; ++var1) {
         this.anIntArray2892[var1] = -this.anIntArray2892[var1];
      }

      for(var1 = 0; var1 < this.anInt2849; ++var1) {
         int var2 = this.anIntArray2865[var1];
         this.anIntArray2865[var1] = this.anIntArray2864[var1];
         this.anIntArray2864[var1] = var2;
      }

      this.method2007();
   }

   final boolean method1865() {
      return true;
   }

   private void method2003(byte[] var1) {
      RSByteBuffer var2 = new RSByteBuffer(var1);
      RSByteBuffer var3 = new RSByteBuffer(var1);
      RSByteBuffer var4 = new RSByteBuffer(var1);
      RSByteBuffer var5 = new RSByteBuffer(var1);
      RSByteBuffer var6 = new RSByteBuffer(var1);
      RSByteBuffer var7 = new RSByteBuffer(var1);
      RSByteBuffer var8 = new RSByteBuffer(var1);
      var2.index = var1.length - 23;
      int var9 = var2.getShort();
      int var10 = var2.getShort();
      int var11 = var2.getByteB();
      int var12 = var2.getByteB();
      boolean var13 = (var12 & 1) == 1;
      boolean var14 = (var12 & 2) == 2;
      int var15 = var2.getByteB();
      int var16 = var2.getByteB();
      int var17 = var2.getByteB();
      int var18 = var2.getByteB();
      int var19 = var2.getByteB();
      int var20 = var2.getShort();
      int var21 = var2.getShort();
      int var22 = var2.getShort();
      int var23 = var2.getShort();
      int var24 = var2.getShort();
      int var25 = 0;
      int var26 = 0;
      int var27 = 0;
      int var28;
      if(var11 > 0) {
         this.aByteArray2857 = new byte[var11];
         var2.index = 0;

         for(var28 = 0; var28 < var11; ++var28) {
            byte var29 = this.aByteArray2857[var28] = var2.getByte();
            if(var29 == 0) {
               ++var25;
            }

            if(var29 >= 1 && var29 <= 3) {
               ++var26;
            }

            if(var29 == 2) {
               ++var27;
            }
         }
      }

      var28 = var11 + var9;
      int var30 = var28;
      if(var13) {
         var28 += var10;
      }

      int var31 = var28;
      var28 += var10;
      int var32 = var28;
      if(var15 == 255) {
         var28 += var10;
      }

      int var33 = var28;
      if(var17 == 1) {
         var28 += var10;
      }

      int var34 = var28;
      if(var19 == 1) {
         var28 += var9;
      }

      int var35 = var28;
      if(var16 == 1) {
         var28 += var10;
      }

      int var36 = var28;
      var28 += var23;
      int var37 = var28;
      if(var18 == 1) {
         var28 += var10 * 2;
      }

      int var38 = var28;
      var28 += var24;
      int var39 = var28;
      var28 += var10 * 2;
      int var40 = var28;
      var28 += var20;
      int var41 = var28;
      var28 += var21;
      int var42 = var28;
      var28 += var22;
      int var43 = var28;
      var28 += var25 * 6;
      int var44 = var28;
      var28 += var26 * 6;
      int var45 = var28;
      var28 += var26 * 6;
      int var46 = var28;
      var28 += var26;
      int var47 = var28;
      var28 += var26;
      int var48 = var28;
      var28 += var26 + var27 * 2;
      this.anInt2887 = var9;
      this.anInt2849 = var10;
      this.anInt2862 = var11;
      this.anIntArray2885 = new int[var9];
      this.anIntArray2881 = new int[var9];
      this.anIntArray2892 = new int[var9];
      this.anIntArray2865 = new int[var10];
      this.anIntArray2878 = new int[var10];
      this.anIntArray2864 = new int[var10];
      if(var19 == 1) {
         this.anIntArray2860 = new int[var9];
      }

      if(var13) {
         this.aByteArray2859 = new byte[var10];
      }

      if(var15 == 255) {
         this.aByteArray2889 = new byte[var10];
      } else {
         this.aByte2848 = (byte)var15;
      }

      if(var16 == 1) {
         this.aByteArray2843 = new byte[var10];
      }

      if(var17 == 1) {
         this.anIntArray2847 = new int[var10];
      }

      if(var18 == 1) {
         this.aShortArray2858 = new short[var10];
      }

      if(var18 == 1 && var11 > 0) {
         this.aByteArray2866 = new byte[var10];
      }

      this.aShortArray2870 = new short[var10];
      if(var11 > 0) {
         this.aShortArray2884 = new short[var11];
         this.aShortArray2846 = new short[var11];
         this.aShortArray2891 = new short[var11];
         if(var26 > 0) {
            this.aShortArray2888 = new short[var26];
            this.aShortArray2882 = new short[var26];
            this.aShortArray2851 = new short[var26];
            this.aByteArray2845 = new byte[var26];
            this.aByteArray2867 = new byte[var26];
            this.aByteArray2877 = new byte[var26];
         }

         if(var27 > 0) {
            this.aByteArray2852 = new byte[var27];
            this.aByteArray2869 = new byte[var27];
         }
      }

      var2.index = var11;
      var3.index = var40;
      var4.index = var41;
      var5.index = var42;
      var6.index = var34;
      int var50 = 0;
      int var51 = 0;
      int var52 = 0;

      int var55;
      int var54;
      int var53;
      int var57;
      int var56;
      for(var53 = 0; var53 < var9; ++var53) {
         var54 = var2.getByteB();
         var55 = 0;
         if((var54 & 1) != 0) {
            var55 = var3.getSmart(-21208);
         }

         var56 = 0;
         if((var54 & 2) != 0) {
            var56 = var4.getSmart(-21208);
         }

         var57 = 0;
         if((var54 & 4) != 0) {
            var57 = var5.getSmart(-21208);
         }

         this.anIntArray2885[var53] = var50 + var55;
         this.anIntArray2881[var53] = var51 + var56;
         this.anIntArray2892[var53] = var52 + var57;
         var50 = this.anIntArray2885[var53];
         var51 = this.anIntArray2881[var53];
         var52 = this.anIntArray2892[var53];
         if(var19 == 1) {
            this.anIntArray2860[var53] = var6.getByteB();
         }
      }

      var2.index = var39;
      var3.index = var30;
      var4.index = var32;
      var5.index = var35;
      var6.index = var33;
      var7.index = var37;
      var8.index = var38;

      for(var53 = 0; var53 < var10; ++var53) {
         this.aShortArray2870[var53] = (short)var2.getShort();
         if(var13) {
            this.aByteArray2859[var53] = var3.getByte();
         }

         if(var15 == 255) {
            this.aByteArray2889[var53] = var4.getByte();
         }

         if(var16 == 1) {
            this.aByteArray2843[var53] = var5.getByte();
         }

         if(var17 == 1) {
            this.anIntArray2847[var53] = var6.getByteB();
         }

         if(var18 == 1) {
            this.aShortArray2858[var53] = (short)(var7.getShort() - 1);
         }

         if(this.aByteArray2866 != null) {
            if(this.aShortArray2858[var53] == -1) {
               this.aByteArray2866[var53] = -1;
            } else {
               this.aByteArray2866[var53] = (byte)(var8.getByteB() - 1);
            }
         }
      }

      var2.index = var36;
      var3.index = var31;
      var53 = 0;
      var54 = 0;
      var55 = 0;
      var56 = 0;

      int var58;
      for(var57 = 0; var57 < var10; ++var57) {
         var58 = var3.getByteB();
         if(var58 == 1) {
            var53 = var2.getSmart(-21208) + var56;
            var54 = var2.getSmart(-21208) + var53;
            var55 = var2.getSmart(-21208) + var54;
            var56 = var55;
            this.anIntArray2865[var57] = var53;
            this.anIntArray2878[var57] = var54;
            this.anIntArray2864[var57] = var55;
         }

         if(var58 == 2) {
            var54 = var55;
            var55 = var2.getSmart(-21208) + var56;
            var56 = var55;
            this.anIntArray2865[var57] = var53;
            this.anIntArray2878[var57] = var54;
            this.anIntArray2864[var57] = var55;
         }

         if(var58 == 3) {
            var53 = var55;
            var55 = var2.getSmart(-21208) + var56;
            var56 = var55;
            this.anIntArray2865[var57] = var53;
            this.anIntArray2878[var57] = var54;
            this.anIntArray2864[var57] = var55;
         }

         if(var58 == 4) {
            int var59 = var53;
            var53 = var54;
            var54 = var59;
            var55 = var2.getSmart(-21208) + var56;
            var56 = var55;
            this.anIntArray2865[var57] = var53;
            this.anIntArray2878[var57] = var59;
            this.anIntArray2864[var57] = var55;
         }
      }

      var2.index = var43;
      var3.index = var44;
      var4.index = var45;
      var5.index = var46;
      var6.index = var47;
      var7.index = var48;

      for(var57 = 0; var57 < var11; ++var57) {
         var58 = this.aByteArray2857[var57] & 255;
         if(var58 == 0) {
            this.aShortArray2884[var57] = (short)var2.getShort();
            this.aShortArray2846[var57] = (short)var2.getShort();
            this.aShortArray2891[var57] = (short)var2.getShort();
         }

         if(var58 == 1) {
            this.aShortArray2884[var57] = (short)var3.getShort();
            this.aShortArray2846[var57] = (short)var3.getShort();
            this.aShortArray2891[var57] = (short)var3.getShort();
            this.aShortArray2888[var57] = (short)var4.getShort();
            this.aShortArray2882[var57] = (short)var4.getShort();
            this.aShortArray2851[var57] = (short)var4.getShort();
            this.aByteArray2845[var57] = var5.getByte();
            this.aByteArray2867[var57] = var6.getByte();
            this.aByteArray2877[var57] = var7.getByte();
         }

         if(var58 == 2) {
            this.aShortArray2884[var57] = (short)var3.getShort();
            this.aShortArray2846[var57] = (short)var3.getShort();
            this.aShortArray2891[var57] = (short)var3.getShort();
            this.aShortArray2888[var57] = (short)var4.getShort();
            this.aShortArray2882[var57] = (short)var4.getShort();
            this.aShortArray2851[var57] = (short)var4.getShort();
            this.aByteArray2845[var57] = var5.getByte();
            this.aByteArray2867[var57] = var6.getByte();
            this.aByteArray2877[var57] = var7.getByte();
            this.aByteArray2852[var57] = var7.getByte();
            this.aByteArray2869[var57] = var7.getByte();
         }

         if(var58 == 3) {
            this.aShortArray2884[var57] = (short)var3.getShort();
            this.aShortArray2846[var57] = (short)var3.getShort();
            this.aShortArray2891[var57] = (short)var3.getShort();
            this.aShortArray2888[var57] = (short)var4.getShort();
            this.aShortArray2882[var57] = (short)var4.getShort();
            this.aShortArray2851[var57] = (short)var4.getShort();
            this.aByteArray2845[var57] = var5.getByte();
            this.aByteArray2867[var57] = var6.getByte();
            this.aByteArray2877[var57] = var7.getByte();
         }
      }

      if(var14) {
         var2.index = var28;
         var57 = var2.getByteB();
         if(var57 > 0) {
            var2.index += 4 * var57;
         }

         var58 = var2.getByteB();
         if(var58 > 0) {
            var2.index += 4 * var58;
         }
      }

   }

   final Model_Sub1 method2004() {
      Model_Sub1 var1 = new Model_Sub1();
      if(this.aByteArray2859 != null) {
         var1.aByteArray2859 = new byte[this.anInt2849];

          if (this.anInt2849 >= 0) System.arraycopy(this.aByteArray2859, 0, var1.aByteArray2859, 0, this.anInt2849);
      }

      var1.anInt2887 = this.anInt2887;
      var1.anInt2849 = this.anInt2849;
      var1.anInt2862 = this.anInt2862;
      var1.anIntArray2885 = this.anIntArray2885;
      var1.anIntArray2881 = this.anIntArray2881;
      var1.anIntArray2892 = this.anIntArray2892;
      var1.anIntArray2865 = this.anIntArray2865;
      var1.anIntArray2878 = this.anIntArray2878;
      var1.anIntArray2864 = this.anIntArray2864;
      var1.aByteArray2889 = this.aByteArray2889;
      var1.aByteArray2843 = this.aByteArray2843;
      var1.aByteArray2866 = this.aByteArray2866;
      var1.aShortArray2870 = this.aShortArray2870;
      var1.aShortArray2858 = this.aShortArray2858;
      var1.aByte2848 = this.aByte2848;
      var1.aByteArray2857 = this.aByteArray2857;
      var1.aShortArray2884 = this.aShortArray2884;
      var1.aShortArray2846 = this.aShortArray2846;
      var1.aShortArray2891 = this.aShortArray2891;
      var1.aShortArray2888 = this.aShortArray2888;
      var1.aShortArray2882 = this.aShortArray2882;
      var1.aShortArray2851 = this.aShortArray2851;
      var1.aByteArray2845 = this.aByteArray2845;
      var1.aByteArray2867 = this.aByteArray2867;
      var1.aByteArray2877 = this.aByteArray2877;
      var1.aByteArray2852 = this.aByteArray2852;
      var1.aByteArray2869 = this.aByteArray2869;
      var1.anIntArray2860 = this.anIntArray2860;
      var1.anIntArray2847 = this.anIntArray2847;
      var1.anIntArrayArray2890 = this.anIntArrayArray2890;
      var1.anIntArrayArray2856 = this.anIntArrayArray2856;
      var1.aClass50Array2872 = this.aClass50Array2872;
      var1.aClass120Array2886 = this.aClass120Array2886;
      var1.aShort2879 = this.aShort2879;
      var1.aShort2876 = this.aShort2876;
      return var1;
   }

   final void method2005(int var1, int var2, int var3, short var5, byte var6) {
      this.anIntArray2865[this.anInt2849] = var1;
      this.anIntArray2878[this.anInt2849] = var2;
      this.anIntArray2864[this.anInt2849] = var3;
      this.aByteArray2859[this.anInt2849] = (byte) 1;
      this.aByteArray2866[this.anInt2849] = -1;
      this.aShortArray2870[this.anInt2849] = var5;
      this.aShortArray2858[this.anInt2849] = -1;
      this.aByteArray2843[this.anInt2849] = var6;
       this.anInt2849++;
   }

   private void method2006(int var1) {
      int var2 = anIntArray2871[var1];
      int var3 = anIntArray2863[var1];

      for(int var4 = 0; var4 < this.anInt2887; ++var4) {
         int var5 = this.anIntArray2881[var4] * var3 - this.anIntArray2892[var4] * var2 >> 16;
         this.anIntArray2892[var4] = this.anIntArray2881[var4] * var2 + this.anIntArray2892[var4] * var3 >> 16;
         this.anIntArray2881[var4] = var5;
      }

      this.method2007();
   }

   private void method2007() {
      this.aClass50Array2872 = null;
      this.aClass50Array2883 = null;
      this.aClass120Array2886 = null;
      this.aBoolean2853 = false;
   }

   final Model method2008(int var1, int var2, int var3, int var4, int var5) {
      if(HDToolKit.highDetail) {
         Class140_Sub1_Sub1 var6 = new Class140_Sub1_Sub1(this, var1, var2, true);
         var6.method1908();
         return var6;
      } else {
         return new Class140_Sub1_Sub2(this, var1, var2, var3, var4, var5);
      }
   }

   private static int method2009(int[][] var0, int var1, int var2) {
      int var3 = var1 >> 7;
      int var4 = var2 >> 7;
      if(var3 >= 0 && var4 >= 0 && var3 < var0.length && var4 < var0[0].length) {
         int var5 = var1 & 127;
         int var6 = var2 & 127;
         int var7 = var0[var3][var4] * (128 - var5) + var0[var3 + 1][var4] * var5 >> 7;
         int var8 = var0[var3][var4 + 1] * (128 - var5) + var0[var3 + 1][var4 + 1] * var5 >> 7;
         return var7 * (128 - var6) + var8 * var6 >> 7;
      } else {
         return 0;
      }
   }

   final int method1871() {
      if(!this.aBoolean2853) {
         this.method1993();
      }

      return this.aShort2854;
   }

   final void method2010() {
      this.anIntArray2860 = null;
      this.anIntArray2847 = null;
      this.anIntArrayArray2890 = (int[][])null;
      this.anIntArrayArray2856 = (int[][])null;
   }

   final void method1866(GameObject var1, int var2, int var3, int var4, boolean var5) {
      Model_Sub1 var6 = (Model_Sub1)var1;
      var6.method1993();
      var6.method1997();
      ++anInt2868;
      int var7 = 0;
      int[] var8 = var6.anIntArray2885;
      int var9 = var6.anInt2887;

      int var10;
      for(var10 = 0; var10 < this.anInt2887; ++var10) {
         Class50 var11 = this.aClass50Array2872[var10];
         if(var11.anInt823 != 0) {
            int var12 = this.anIntArray2881[var10] - var3;
            if(var12 >= var6.aShort2854 && var12 <= var6.aShort2844) {
               int var13 = this.anIntArray2885[var10] - var2;
               if(var13 >= var6.aShort2850 && var13 <= var6.aShort2873) {
                  int var14 = this.anIntArray2892[var10] - var4;
                  if(var14 >= var6.aShort2880 && var14 <= var6.aShort2874) {
                     for(int var15 = 0; var15 < var9; ++var15) {
                        Class50 var16 = var6.aClass50Array2872[var15];
                        if(var13 == var8[var15] && var14 == var6.anIntArray2892[var15] && var12 == var6.anIntArray2881[var15] && var16.anInt823 != 0) {
                           if(this.aClass50Array2883 == null) {
                              this.aClass50Array2883 = new Class50[this.anInt2887];
                           }

                           if(var6.aClass50Array2883 == null) {
                              var6.aClass50Array2883 = new Class50[var9];
                           }

                           Class50 var17 = this.aClass50Array2883[var10];
                           if(var17 == null) {
                              var17 = this.aClass50Array2883[var10] = new Class50(var11);
                           }

                           Class50 var18 = var6.aClass50Array2883[var15];
                           if(var18 == null) {
                              var18 = var6.aClass50Array2883[var15] = new Class50(var16);
                           }

                           var17.anInt831 += var16.anInt831;
                           var17.anInt821 += var16.anInt821;
                           var17.anInt830 += var16.anInt830;
                           var17.anInt823 += var16.anInt823;
                           var18.anInt831 += var11.anInt831;
                           var18.anInt821 += var11.anInt821;
                           var18.anInt830 += var11.anInt830;
                           var18.anInt823 += var11.anInt823;
                           ++var7;
                           anIntArray2861[var10] = anInt2868;
                           anIntArray2875[var15] = anInt2868;
                        }
                     }
                  }
               }
            }
         }
      }

      if(var7 >= 3 && var5) {
         for(var10 = 0; var10 < this.anInt2849; ++var10) {
            if(anIntArray2861[this.anIntArray2865[var10]] == anInt2868 && anIntArray2861[this.anIntArray2878[var10]] == anInt2868 && anIntArray2861[this.anIntArray2864[var10]] == anInt2868) {
               if(this.aByteArray2859 == null) {
                  this.aByteArray2859 = new byte[this.anInt2849];
               }

               this.aByteArray2859[var10] = 2;
            }
         }

         for(var10 = 0; var10 < var6.anInt2849; ++var10) {
            if(anIntArray2875[var6.anIntArray2865[var10]] == anInt2868 && anIntArray2875[var6.anIntArray2878[var10]] == anInt2868 && anIntArray2875[var6.anIntArray2864[var10]] == anInt2868) {
               if(var6.aByteArray2859 == null) {
                  var6.aByteArray2859 = new byte[var6.anInt2849];
               }

               var6.aByteArray2859[var10] = 2;
            }
         }

      }
   }

   final void method2011() {
      int var2 = anIntArray2871[256];
      int var3 = anIntArray2863[256];

      for(int var4 = 0; var4 < this.anInt2887; ++var4) {
         int var5 = this.anIntArray2892[var4] * var2 + this.anIntArray2885[var4] * var3 >> 16;
         this.anIntArray2892[var4] = this.anIntArray2892[var4] * var3 - this.anIntArray2885[var4] * var2 >> 16;
         this.anIntArray2885[var4] = var5;
      }

      this.method2007();
   }

   final void method2012() {
      int[] var1;
      int var2;
      int var3;
      int var4;
      if(this.anIntArray2860 != null) {
         var1 = new int[256];
         var2 = 0;

         for(var3 = 0; var3 < this.anInt2887; ++var3) {
            var4 = this.anIntArray2860[var3];
            ++var1[var4];
            if(var4 > var2) {
               var2 = var4;
            }
         }

         this.anIntArrayArray2890 = new int[var2 + 1][];

         for(var3 = 0; var3 <= var2; ++var3) {
            this.anIntArrayArray2890[var3] = new int[var1[var3]];
            var1[var3] = 0;
         }

         for(var3 = 0; var3 < this.anInt2887; this.anIntArrayArray2890[var4][var1[var4]++] = var3++) {
            var4 = this.anIntArray2860[var3];
         }

         this.anIntArray2860 = null;
      }

      if(this.anIntArray2847 != null) {
         var1 = new int[256];
         var2 = 0;

         for(var3 = 0; var3 < this.anInt2849; ++var3) {
            var4 = this.anIntArray2847[var3];
            ++var1[var4];
            if(var4 > var2) {
               var2 = var4;
            }
         }

         this.anIntArrayArray2856 = new int[var2 + 1][];

         for(var3 = 0; var3 <= var2; ++var3) {
            this.anIntArrayArray2856[var3] = new int[var1[var3]];
            var1[var3] = 0;
         }

         for(var3 = 0; var3 < this.anInt2849; this.anIntArrayArray2856[var4][var1[var4]++] = var3++) {
            var4 = this.anIntArray2847[var3];
         }

         this.anIntArray2847 = null;
      }

   }

   final void method2013(int var1, int var2, int var3) {
      int var4;
      int var5;
      int var6;
      int var7;
      if(var3 != 0) {
         var4 = anIntArray2871[var3];
         var5 = anIntArray2863[var3];

         for(var6 = 0; var6 < this.anInt2887; ++var6) {
            var7 = this.anIntArray2881[var6] * var4 + this.anIntArray2885[var6] * var5 >> 16;
            this.anIntArray2881[var6] = this.anIntArray2881[var6] * var5 - this.anIntArray2885[var6] * var4 >> 16;
            this.anIntArray2885[var6] = var7;
         }
      }

      if(var1 != 0) {
         var4 = anIntArray2871[var1];
         var5 = anIntArray2863[var1];

         for(var6 = 0; var6 < this.anInt2887; ++var6) {
            var7 = this.anIntArray2881[var6] * var5 - this.anIntArray2892[var6] * var4 >> 16;
            this.anIntArray2892[var6] = this.anIntArray2881[var6] * var4 + this.anIntArray2892[var6] * var5 >> 16;
            this.anIntArray2881[var6] = var7;
         }
      }

      if(var2 != 0) {
         var4 = anIntArray2871[var2];
         var5 = anIntArray2863[var2];

         for(var6 = 0; var6 < this.anInt2887; ++var6) {
            var7 = this.anIntArray2892[var6] * var4 + this.anIntArray2885[var6] * var5 >> 16;
            this.anIntArray2892[var6] = this.anIntArray2892[var6] * var5 - this.anIntArray2885[var6] * var4 >> 16;
            this.anIntArray2885[var6] = var7;
         }
      }

   }

   final int method2014(int var1, int var3) {
      for(int var4 = 0; var4 < this.anInt2887; ++var4) {
         if(this.anIntArray2885[var4] == var1 && this.anIntArray2881[var4] == 0 && this.anIntArray2892[var4] == var3) {
            return var4;
         }
      }

      this.anIntArray2885[this.anInt2887] = var1;
      this.anIntArray2881[this.anInt2887] = 0;
      this.anIntArray2892[this.anInt2887] = var3;
      return this.anInt2887++;
   }

   static Model_Sub1 method2015(CacheIndex var0, int var1) {
      byte[] var3 = var0.getFile(var1, 0);
      return var3 == null?null:new Model_Sub1(var3);
   }

   final void method2016(short var1, short var2) {
      for(int var3 = 0; var3 < this.anInt2849; ++var3) {
         if(this.aShortArray2870[var3] == var1) {
            this.aShortArray2870[var3] = var2;
         }
      }

   }

   private void method2017(byte[] var1) {
      boolean var2 = false;
      boolean var3 = false;
      RSByteBuffer var4 = new RSByteBuffer(var1);
      RSByteBuffer var5 = new RSByteBuffer(var1);
      RSByteBuffer var6 = new RSByteBuffer(var1);
      RSByteBuffer var7 = new RSByteBuffer(var1);
      RSByteBuffer var8 = new RSByteBuffer(var1);
      var4.index = var1.length - 18;
      int var9 = var4.getShort();
      int var10 = var4.getShort();
      int var11 = var4.getByteB();
      int var12 = var4.getByteB();
      int var13 = var4.getByteB();
      int var14 = var4.getByteB();
      int var15 = var4.getByteB();
      int var16 = var4.getByteB();
      int var17 = var4.getShort();
      int var18 = var4.getShort();
      var4.getShort();
      int var20 = var4.getShort();
      byte var21 = 0;
      int var45 = var21 + var9;
      int var23 = var45;
      var45 += var10;
      int var24 = var45;
      if(var13 == 255) {
         var45 += var10;
      }

      int var25 = var45;
      if(var15 == 1) {
         var45 += var10;
      }

      int var26 = var45;
      if(var12 == 1) {
         var45 += var10;
      }

      int var27 = var45;
      if(var16 == 1) {
         var45 += var9;
      }

      int var28 = var45;
      if(var14 == 1) {
         var45 += var10;
      }

      int var29 = var45;
      var45 += var20;
      int var30 = var45;
      var45 += var10 * 2;
      int var31 = var45;
      var45 += var11 * 6;
      int var32 = var45;
      var45 += var17;
      int var33 = var45;
      var45 += var18;
      this.anInt2887 = var9;
      this.anInt2849 = var10;
      this.anInt2862 = var11;
      this.anIntArray2885 = new int[var9];
      this.anIntArray2881 = new int[var9];
      this.anIntArray2892 = new int[var9];
      this.anIntArray2865 = new int[var10];
      this.anIntArray2878 = new int[var10];
      this.anIntArray2864 = new int[var10];
      if(var11 > 0) {
         this.aByteArray2857 = new byte[var11];
         this.aShortArray2884 = new short[var11];
         this.aShortArray2846 = new short[var11];
         this.aShortArray2891 = new short[var11];
      }

      if(var16 == 1) {
         this.anIntArray2860 = new int[var9];
      }

      if(var12 == 1) {
         this.aByteArray2859 = new byte[var10];
         this.aByteArray2866 = new byte[var10];
         this.aShortArray2858 = new short[var10];
      }

      if(var13 == 255) {
         this.aByteArray2889 = new byte[var10];
      } else {
         this.aByte2848 = (byte)var13;
      }

      if(var14 == 1) {
         this.aByteArray2843 = new byte[var10];
      }

      if(var15 == 1) {
         this.anIntArray2847 = new int[var10];
      }

      this.aShortArray2870 = new short[var10];
      var4.index = var21;
      var5.index = var32;
      var6.index = var33;
      var7.index = var45;
      var8.index = var27;
      int var35 = 0;
      int var36 = 0;
      int var37 = 0;

      int var38;
      int var39;
      int var42;
      int var40;
      int var41;
      for(var38 = 0; var38 < var9; ++var38) {
         var39 = var4.getByteB();
         var40 = 0;
         if((var39 & 1) != 0) {
            var40 = var5.getSmart(-21208);
         }

         var41 = 0;
         if((var39 & 2) != 0) {
            var41 = var6.getSmart(-21208);
         }

         var42 = 0;
         if((var39 & 4) != 0) {
            var42 = var7.getSmart(-21208);
         }

         this.anIntArray2885[var38] = var35 + var40;
         this.anIntArray2881[var38] = var36 + var41;
         this.anIntArray2892[var38] = var37 + var42;
         var35 = this.anIntArray2885[var38];
         var36 = this.anIntArray2881[var38];
         var37 = this.anIntArray2892[var38];
         if(var16 == 1) {
            this.anIntArray2860[var38] = var8.getByteB();
         }
      }

      var4.index = var30;
      var5.index = var26;
      var6.index = var24;
      var7.index = var28;
      var8.index = var25;

      for(var38 = 0; var38 < var10; ++var38) {
         this.aShortArray2870[var38] = (short)var4.getShort();
         if(var12 == 1) {
            var39 = var5.getByteB();
            if((var39 & 1) == 1) {
               this.aByteArray2859[var38] = 1;
               var2 = true;
            } else {
               this.aByteArray2859[var38] = 0;
            }

            if((var39 & 2) == 2) {
               this.aByteArray2866[var38] = (byte)(var39 >> 2);
               this.aShortArray2858[var38] = this.aShortArray2870[var38];
               this.aShortArray2870[var38] = 127;
               if(this.aShortArray2858[var38] != -1) {
                  var3 = true;
               }
            } else {
               this.aByteArray2866[var38] = -1;
               this.aShortArray2858[var38] = -1;
            }
         }

         if(var13 == 255) {
            this.aByteArray2889[var38] = var6.getByte();
         }

         if(var14 == 1) {
            this.aByteArray2843[var38] = var7.getByte();
         }

         if(var15 == 1) {
            this.anIntArray2847[var38] = var8.getByteB();
         }
      }

      var4.index = var29;
      var5.index = var23;
      var38 = 0;
      var39 = 0;
      var40 = 0;
      var41 = 0;

      int var43;
      int var44;
      for(var42 = 0; var42 < var10; ++var42) {
         var43 = var5.getByteB();
         if(var43 == 1) {
            var38 = var4.getSmart(-21208) + var41;
            var39 = var4.getSmart(-21208) + var38;
            var40 = var4.getSmart(-21208) + var39;
            var41 = var40;
            this.anIntArray2865[var42] = var38;
            this.anIntArray2878[var42] = var39;
            this.anIntArray2864[var42] = var40;
         }

         if(var43 == 2) {
            var39 = var40;
            var40 = var4.getSmart(-21208) + var41;
            var41 = var40;
            this.anIntArray2865[var42] = var38;
            this.anIntArray2878[var42] = var39;
            this.anIntArray2864[var42] = var40;
         }

         if(var43 == 3) {
            var38 = var40;
            var40 = var4.getSmart(-21208) + var41;
            var41 = var40;
            this.anIntArray2865[var42] = var38;
            this.anIntArray2878[var42] = var39;
            this.anIntArray2864[var42] = var40;
         }

         if(var43 == 4) {
            var44 = var38;
            var38 = var39;
            var39 = var44;
            var40 = var4.getSmart(-21208) + var41;
            var41 = var40;
            this.anIntArray2865[var42] = var38;
            this.anIntArray2878[var42] = var44;
            this.anIntArray2864[var42] = var40;
         }
      }

      var4.index = var31;

      for(var42 = 0; var42 < var11; ++var42) {
         this.aByteArray2857[var42] = 0;
         this.aShortArray2884[var42] = (short)var4.getShort();
         this.aShortArray2846[var42] = (short)var4.getShort();
         this.aShortArray2891[var42] = (short)var4.getShort();
      }

      if(this.aByteArray2866 != null) {
         boolean var46 = false;

         for(var43 = 0; var43 < var10; ++var43) {
            var44 = this.aByteArray2866[var43] & 255;
            if(var44 != 255) {
               if((this.aShortArray2884[var44] & '\uffff') == this.anIntArray2865[var43] && (this.aShortArray2846[var44] & '\uffff') == this.anIntArray2878[var43] && (this.aShortArray2891[var44] & '\uffff') == this.anIntArray2864[var43]) {
                  this.aByteArray2866[var43] = -1;
               } else {
                  var46 = true;
               }
            }
         }

         if(!var46) {
            this.aByteArray2866 = null;
         }
      }

      if(!var3) {
         this.aShortArray2858 = null;
      }

      if(!var2) {
         this.aByteArray2859 = null;
      }

   }

   final void method2018() {
      for(int var1 = 0; var1 < this.anInt2887; ++var1) {
         int var2 = this.anIntArray2892[var1];
         this.anIntArray2892[var1] = this.anIntArray2885[var1];
         this.anIntArray2885[var1] = -var2;
      }

      this.method2007();
   }

   private Model_Sub1() {}

   private Model_Sub1(byte[] var1) {
      if(var1[var1.length - 1] == -1 && var1[var1.length - 2] == -1) {
         this.method2003(var1);
      } else {
         this.method2017(var1);
      }

   }

   Model_Sub1(int var1, int var2) {
      this.anIntArray2885 = new int[var1];
      this.anIntArray2881 = new int[var1];
      this.anIntArray2892 = new int[var1];
      this.anIntArray2860 = new int[var1];
      this.anIntArray2865 = new int[var2];
      this.anIntArray2878 = new int[var2];
      this.anIntArray2864 = new int[var2];
      this.aByteArray2859 = new byte[var2];
      this.aByteArray2889 = new byte[var2];
      this.aByteArray2843 = new byte[var2];
      this.aShortArray2870 = new short[var2];
      this.aShortArray2858 = new short[var2];
      this.aByteArray2866 = new byte[var2];
      this.anIntArray2847 = new int[var2];

   }

   Model_Sub1(Model_Sub1[] var1, int var2) {
      boolean var3 = false;
      boolean var4 = false;
      boolean var5 = false;
      boolean var6 = false;
      boolean var7 = false;
      boolean var8 = false;
      this.anInt2887 = 0;
      this.anInt2849 = 0;
      this.anInt2862 = 0;
      this.aByte2848 = -1;

      int var11;
      for(var11 = 0; var11 < var2; ++var11) {
         Model_Sub1 var12 = var1[var11];
         if(var12 != null) {
            this.anInt2887 += var12.anInt2887;
            this.anInt2849 += var12.anInt2849;
            this.anInt2862 += var12.anInt2862;
            if(var12.aByteArray2889 == null) {
               if(this.aByte2848 == -1) {
                  this.aByte2848 = var12.aByte2848;
               }

               if(this.aByte2848 != var12.aByte2848) {
                  var4 = true;
               }
            } else {
               var4 = true;
            }

             var3 |= var12.aByteArray2859 != null;
            var5 |= var12.aByteArray2843 != null;
            var6 |= var12.anIntArray2847 != null;
            var7 |= var12.aShortArray2858 != null;
            var8 |= var12.aByteArray2866 != null;
         }
      }

      this.anIntArray2885 = new int[this.anInt2887];
      this.anIntArray2881 = new int[this.anInt2887];
      this.anIntArray2892 = new int[this.anInt2887];
      this.anIntArray2860 = new int[this.anInt2887];
      this.aShortArray2893 = new short[this.anInt2887];
      this.anIntArray2865 = new int[this.anInt2849];
      this.anIntArray2878 = new int[this.anInt2849];
      this.anIntArray2864 = new int[this.anInt2849];
      if(var3) {
         this.aByteArray2859 = new byte[this.anInt2849];
      }

      if(var4) {
         this.aByteArray2889 = new byte[this.anInt2849];
      }

      if(var5) {
         this.aByteArray2843 = new byte[this.anInt2849];
      }

      if(var6) {
         this.anIntArray2847 = new int[this.anInt2849];
      }

      if(var7) {
         this.aShortArray2858 = new short[this.anInt2849];
      }

      if(var8) {
         this.aByteArray2866 = new byte[this.anInt2849];
      }

      this.aShortArray2870 = new short[this.anInt2849];
      this.aShortArray2855 = new short[this.anInt2849];
      if(this.anInt2862 > 0) {
         this.aByteArray2857 = new byte[this.anInt2862];
         this.aShortArray2884 = new short[this.anInt2862];
         this.aShortArray2846 = new short[this.anInt2862];
         this.aShortArray2891 = new short[this.anInt2862];
         this.aShortArray2888 = new short[this.anInt2862];
         this.aShortArray2882 = new short[this.anInt2862];
         this.aShortArray2851 = new short[this.anInt2862];
         this.aByteArray2845 = new byte[this.anInt2862];
         this.aByteArray2867 = new byte[this.anInt2862];
         this.aByteArray2877 = new byte[this.anInt2862];
         this.aByteArray2852 = new byte[this.anInt2862];
         this.aByteArray2869 = new byte[this.anInt2862];
      }

      this.anInt2887 = 0;
      this.anInt2849 = 0;
      this.anInt2862 = 0;

      for(var11 = 0; var11 < var2; ++var11) {
         short var18 = (short)(1 << var11);
         Model_Sub1 var13 = var1[var11];
         if(var13 != null) {
            int var14;
            for(var14 = 0; var14 < var13.anInt2849; ++var14) {
               if(var3 && var13.aByteArray2859 != null) {
                  this.aByteArray2859[this.anInt2849] = var13.aByteArray2859[var14];
               }

               if(var4) {
                  if(var13.aByteArray2889 == null) {
                     this.aByteArray2889[this.anInt2849] = var13.aByte2848;
                  } else {
                     this.aByteArray2889[this.anInt2849] = var13.aByteArray2889[var14];
                  }
               }

               if(var5 && var13.aByteArray2843 != null) {
                  this.aByteArray2843[this.anInt2849] = var13.aByteArray2843[var14];
               }

               if(var6 && var13.anIntArray2847 != null) {
                  this.anIntArray2847[this.anInt2849] = var13.anIntArray2847[var14];
               }

               if(var7) {
                  if(var13.aShortArray2858 == null) {
                     this.aShortArray2858[this.anInt2849] = -1;
                  } else {
                     this.aShortArray2858[this.anInt2849] = var13.aShortArray2858[var14];
                  }
               }

               if(var8) {
                  if(var13.aByteArray2866 == null || var13.aByteArray2866[var14] == -1) {
                     this.aByteArray2866[this.anInt2849] = -1;
                  } else {
                     this.aByteArray2866[this.anInt2849] = (byte)(var13.aByteArray2866[var14] + this.anInt2862);
                  }
               }

               this.aShortArray2870[this.anInt2849] = var13.aShortArray2870[var14];
               this.aShortArray2855[this.anInt2849] = var18;
               this.anIntArray2865[this.anInt2849] = this.method1995(var13, var13.anIntArray2865[var14], var18);
               this.anIntArray2878[this.anInt2849] = this.method1995(var13, var13.anIntArray2878[var14], var18);
               this.anIntArray2864[this.anInt2849] = this.method1995(var13, var13.anIntArray2864[var14], var18);
               ++this.anInt2849;
            }

            for(var14 = 0; var14 < var13.anInt2862; ++var14) {
               byte var15 = this.aByteArray2857[this.anInt2862] = var13.aByteArray2857[var14];
               if(var15 == 0) {
                  this.aShortArray2884[this.anInt2862] = (short)this.method1995(var13, var13.aShortArray2884[var14], var18);
                  this.aShortArray2846[this.anInt2862] = (short)this.method1995(var13, var13.aShortArray2846[var14], var18);
                  this.aShortArray2891[this.anInt2862] = (short)this.method1995(var13, var13.aShortArray2891[var14], var18);
               }

               if(var15 >= 1 && var15 <= 3) {
                  this.aShortArray2884[this.anInt2862] = var13.aShortArray2884[var14];
                  this.aShortArray2846[this.anInt2862] = var13.aShortArray2846[var14];
                  this.aShortArray2891[this.anInt2862] = var13.aShortArray2891[var14];
                  this.aShortArray2888[this.anInt2862] = var13.aShortArray2888[var14];
                  this.aShortArray2882[this.anInt2862] = var13.aShortArray2882[var14];
                  this.aShortArray2851[this.anInt2862] = var13.aShortArray2851[var14];
                  this.aByteArray2845[this.anInt2862] = var13.aByteArray2845[var14];
                  this.aByteArray2867[this.anInt2862] = var13.aByteArray2867[var14];
                  this.aByteArray2877[this.anInt2862] = var13.aByteArray2877[var14];
               }

               if(var15 == 2) {
                  this.aByteArray2852[this.anInt2862] = var13.aByteArray2852[var14];
                  this.aByteArray2869[this.anInt2862] = var13.aByteArray2869[var14];
               }

               ++this.anInt2862;
            }
         }
      }

   }

   Model_Sub1(Model_Sub1 var1, boolean var2, boolean var3, boolean var4) {
      this.anInt2887 = var1.anInt2887;
      this.anInt2849 = var1.anInt2849;
      this.anInt2862 = var1.anInt2862;
      int var6;
      if(var2) {
         this.anIntArray2885 = var1.anIntArray2885;
         this.anIntArray2881 = var1.anIntArray2881;
         this.anIntArray2892 = var1.anIntArray2892;
      } else {
         this.anIntArray2885 = new int[this.anInt2887];
         this.anIntArray2881 = new int[this.anInt2887];
         this.anIntArray2892 = new int[this.anInt2887];

         for(var6 = 0; var6 < this.anInt2887; ++var6) {
            this.anIntArray2885[var6] = var1.anIntArray2885[var6];
            this.anIntArray2881[var6] = var1.anIntArray2881[var6];
            this.anIntArray2892[var6] = var1.anIntArray2892[var6];
         }
      }

      if(var3) {
         this.aShortArray2870 = var1.aShortArray2870;
      } else {
         this.aShortArray2870 = new short[this.anInt2849];

         for(var6 = 0; var6 < this.anInt2849; ++var6) {
            this.aShortArray2870[var6] = var1.aShortArray2870[var6];
         }
      }

      if(var4 || var1.aShortArray2858 == null) {
         this.aShortArray2858 = var1.aShortArray2858;
      } else {
         this.aShortArray2858 = new short[this.anInt2849];

         for(var6 = 0; var6 < this.anInt2849; ++var6) {
            this.aShortArray2858[var6] = var1.aShortArray2858[var6];
         }
      }

      this.aByteArray2843 = var1.aByteArray2843;

      this.anIntArray2865 = var1.anIntArray2865;
      this.anIntArray2878 = var1.anIntArray2878;
      this.anIntArray2864 = var1.anIntArray2864;
      this.aByteArray2859 = var1.aByteArray2859;
      this.aByteArray2889 = var1.aByteArray2889;
      this.aByteArray2866 = var1.aByteArray2866;
      this.aByte2848 = var1.aByte2848;
      this.aByteArray2857 = var1.aByteArray2857;
      this.aShortArray2884 = var1.aShortArray2884;
      this.aShortArray2846 = var1.aShortArray2846;
      this.aShortArray2891 = var1.aShortArray2891;
      this.aShortArray2888 = var1.aShortArray2888;
      this.aShortArray2882 = var1.aShortArray2882;
      this.aShortArray2851 = var1.aShortArray2851;
      this.aByteArray2845 = var1.aByteArray2845;
      this.aByteArray2867 = var1.aByteArray2867;
      this.aByteArray2877 = var1.aByteArray2877;
      this.aByteArray2852 = var1.aByteArray2852;
      this.aByteArray2869 = var1.aByteArray2869;
      this.anIntArray2860 = var1.anIntArray2860;
      this.anIntArray2847 = var1.anIntArray2847;
      this.anIntArrayArray2890 = var1.anIntArrayArray2890;
      this.anIntArrayArray2856 = var1.anIntArrayArray2856;
      this.aClass50Array2872 = var1.aClass50Array2872;
      this.aClass120Array2886 = var1.aClass120Array2886;
      this.aClass50Array2883 = var1.aClass50Array2883;
      this.aShort2879 = var1.aShort2879;
      this.aShort2876 = var1.aShort2876;
   }

}
