package org.runite.jagex;

final class Class140_Sub1_Sub2 extends Model {

   private short[] aShortArray3869;
   private int[][] anIntArrayArray3870;
   private short[] aShortArray3871;
   private int[][] anIntArrayArray3872;
   private short aShort3873;
   private int[] anIntArray3874;
   private byte aByte3875 = 0;
   private int[] anIntArray3876;
   private boolean aBoolean3877 = false;
   private short aShort3878;
   private short aShort3879;
   private byte[] aByteArray3880;
   private int[] anIntArray3881;
   private int[] anIntArray3882;
   int[] anIntArray3883;
   private short aShort3884;
   int[] anIntArray3885;
   private short aShort3886;
   private int[] anIntArray3887;
   private short aShort3888;
   private int anInt3889 = 0;
   private int[] anIntArray3890;
   int anInt3891 = 0;
   private short aShort3892;
   private short[] aShortArray3893;
   private short aShort3894;
   int[] anIntArray3895;
   private int[] anIntArray3896;
   boolean aBoolean3897 = false;
   private int[] anIntArray3898;
   private byte[] aByteArray3899;
   private int anInt3900 = 0;
   private int[] anIntArray3901;
   private static Class140_Sub1_Sub2 aClass140_Sub1_Sub2_3902 = new Class140_Sub1_Sub2();
   private byte[] aByteArray3903;
   private static int[] anIntArray3904 = new int[1];
   private static int[] anIntArray3905 = new int[1];
   private static Class140_Sub1_Sub2 aClass140_Sub1_Sub2_3906 = new Class140_Sub1_Sub2();
   private static byte[] aByteArray3907 = new byte[1];
   private short[] aShortArray3908;
   private static int[] anIntArray3909 = new int[1];
   private static short[] aShortArray3910 = new short[1];
   private static int[] anIntArray3911 = new int[1];
   private static short[] aShortArray3912 = new short[1];
   private static int[] anIntArray3913 = new int[1];
   private static byte[] aByteArray3914 = new byte[1];
   private static Class140_Sub1_Sub2 aClass140_Sub1_Sub2_3915 = new Class140_Sub1_Sub2();
   private static int[] anIntArray3916 = new int[1];
   private static boolean[] aBooleanArray3917 = new boolean[4096];
   private static int[] anIntArray3918 = new int[12];
   private static int[] anIntArray3919 = new int[10];
   private static int[] anIntArray3920 = new int[4096];
   private static int[] anIntArray3921;
   private static int[] anIntArray3922 = new int[4096];
   private static int[][] anIntArrayArray3923;
   private static int[] anIntArray3924;
   private static int[] anIntArray3925 = new int[10];
   private static int[][] anIntArrayArray3926;
   private static boolean aBoolean3927 = false;
   private static int[] anIntArray3928;
   private static int[] anIntArray3929;
   private static int anInt3930 = 0;
   private static int[] anIntArray3931;
   private static int[] anIntArray3932 = new int[4096];
   private static byte[] aByteArray3933 = new byte[1];
   private static int anInt3934;
   private static int[] anIntArray3935;
   private static int[] anIntArray3936 = new int[10];
   private static boolean[] aBooleanArray3937 = new boolean[4096];
   private static int[][] anIntArrayArray3938;
   private static int[] anIntArray3939;
   private static int anInt3940;
   private static int[] anIntArray3941 = new int[4096];
   private static boolean aBoolean3942 = false;
   private static int[] anIntArray3943;
   private static int[] anIntArray3944 = new int[12];
   private static int anInt3945;
   private static int[] anIntArray3946;
   private static int[] anIntArray3947 = new int[1];
   private static int[] anIntArray3948 = new int[4096];
   private static short[] aShortArray3949;
   private static int[] anIntArray3950;


   private void method1934(int var1) {
      if(aBooleanArray3917[var1]) {
         this.method1942(var1);
      } else {
         int var2 = this.anIntArray3901[var1];
         int var3 = this.anIntArray3876[var1];
         int var4 = this.anIntArray3887[var1];
         Class51.aBoolean849 = aBooleanArray3937[var1];
         if(this.aByteArray3903 == null) {
            Class51.anInt850 = 0;
         } else {
            Class51.anInt850 = this.aByteArray3903[var1] & 255;
         }

         if(this.aShortArray3908 != null && this.aShortArray3908[var1] != -1) {
            int var5;
            int var6;
            int var7;
            if(this.aByteArray3899 == null || this.aByteArray3899[var1] == -1) {
               var5 = var2;
               var6 = var3;
               var7 = var4;
            } else {
               int var8 = this.aByteArray3899[var1] & 255;
               var5 = this.anIntArray3882[var8];
               var6 = this.anIntArray3890[var8];
               var7 = this.anIntArray3881[var8];
            }

             if(this.anIntArray3896[var1] == -1) {
               Class51.method1138(anIntArray3932[var2], anIntArray3932[var3], anIntArray3932[var4], anIntArray3943[var2], anIntArray3943[var3], anIntArray3943[var4], this.anIntArray3898[var1], this.anIntArray3898[var1], this.anIntArray3898[var1], anIntArray3948[var5], anIntArray3948[var6], anIntArray3948[var7], anIntArray3928[var5], anIntArray3928[var6], anIntArray3928[var7], anIntArray3921[var5], anIntArray3921[var6], anIntArray3921[var7], this.aShortArray3908[var1]);
            } else {
               Class51.method1138(anIntArray3932[var2], anIntArray3932[var3], anIntArray3932[var4], anIntArray3943[var2], anIntArray3943[var3], anIntArray3943[var4], this.anIntArray3898[var1], this.anIntArray3874[var1], this.anIntArray3896[var1], anIntArray3948[var5], anIntArray3948[var6], anIntArray3948[var7], anIntArray3928[var5], anIntArray3928[var6], anIntArray3928[var7], anIntArray3921[var5], anIntArray3921[var6], anIntArray3921[var7], this.aShortArray3908[var1]);
            }
         } else if(this.anIntArray3896[var1] == -1) {
            Class51.method1144(anIntArray3932[var2], anIntArray3932[var3], anIntArray3932[var4], anIntArray3943[var2], anIntArray3943[var3], anIntArray3943[var4], Class51.anIntArray834[this.anIntArray3898[var1] & '\uffff']);
         } else {
            Class51.method1154(anIntArray3932[var2], anIntArray3932[var3], anIntArray3932[var4], anIntArray3943[var2], anIntArray3943[var3], anIntArray3943[var4], this.anIntArray3898[var1] & '\uffff', this.anIntArray3874[var1] & '\uffff', this.anIntArray3896[var1] & '\uffff');
         }

      }
   }

   final void method1874() {
      for(int var1 = 0; var1 < this.anInt3891; ++var1) {
         this.anIntArray3885[var1] = -this.anIntArray3885[var1];
         this.anIntArray3895[var1] = -this.anIntArray3895[var1];
      }

      this.aBoolean3897 = false;
   }

   static void method1935() {
      aBoolean3942 = true;
      anIntArray3931 = new int[4096];
      anIntArray3929 = new int[4096];
      anIntArray3946 = null;
      anIntArrayArray3926 = (int[][])null;
      anIntArray3935 = null;
      anIntArrayArray3923 = (int[][])null;
   }

   private void method1936() {
      for(int var4 = 0; var4 < this.anInt3889; ++var4) {
         short var3 = this.aShortArray3908 != null?this.aShortArray3908[var4]:-1;
         if(var3 == -1) {
            int var1 = this.aShortArray3869[var4] & '\uffff';
            int var2;
            if(this.anIntArray3896[var4] == -1) {
               var2 = this.anIntArray3898[var4] & -131072;
               this.anIntArray3898[var4] = var2 | method1940(var1, var2 >> 17);
            } else if(this.anIntArray3896[var4] != -2) {
               var2 = this.anIntArray3898[var4] & -131072;
               this.anIntArray3898[var4] = var2 | method1940(var1, var2 >> 17);
               var2 = this.anIntArray3874[var4] & -131072;
               this.anIntArray3874[var4] = var2 | method1940(var1, var2 >> 17);
               var2 = this.anIntArray3896[var4] & -131072;
               this.anIntArray3896[var4] = var2 | method1940(var1, var2 >> 17);
            }
         }
      }

   }

   private static int method1937(int var0) {
      if(var0 < 2) {
         var0 = 2;
      } else if(var0 > 126) {
         var0 = 126;
      }

      return var0;
   }

   static void method1938() {
      aBoolean3942 = false;
      anIntArray3931 = null;
      anIntArray3929 = null;
      anIntArray3946 = new int[1600];
      anIntArrayArray3926 = new int[1600][64];
      anIntArray3935 = new int[32];
      anIntArrayArray3923 = new int[32][512];
   }

   final int method1898() {
      if(!this.aBoolean3897) {
         this.method1947();
      }

      return this.aShort3879;
   }

   final int method1871() {
      if(!this.aBoolean3897) {
         this.method1947();
      }

      return this.aShort3894;
   }

   private Model method1939(boolean var1, boolean var2, Class140_Sub1_Sub2 var3, byte[] var4, short[] var5, int[] var6, int[] var7, int[] var8) {
      var3.anInt3891 = this.anInt3891;
      var3.anInt3889 = this.anInt3889;
      var3.anInt3900 = this.anInt3900;
      if(var3.anIntArray3885 == null || var3.anIntArray3885.length < this.anInt3891) {
         var3.anIntArray3885 = new int[this.anInt3891 + 100];
         var3.anIntArray3883 = new int[this.anInt3891 + 100];
         var3.anIntArray3895 = new int[this.anInt3891 + 100];
      }

      int var9;
      for(var9 = 0; var9 < this.anInt3891; ++var9) {
         var3.anIntArray3885[var9] = this.anIntArray3885[var9];
         var3.anIntArray3883[var9] = this.anIntArray3883[var9];
         var3.anIntArray3895[var9] = this.anIntArray3895[var9];
      }

      if(var1) {
         var3.aByteArray3903 = this.aByteArray3903;
      } else {
         var3.aByteArray3903 = var4;
         if(this.aByteArray3903 == null) {
            for(var9 = 0; var9 < this.anInt3889; ++var9) {
               var3.aByteArray3903[var9] = 0;
            }
         } else {
            for(var9 = 0; var9 < this.anInt3889; ++var9) {
               var3.aByteArray3903[var9] = this.aByteArray3903[var9];
            }
         }
      }

      if(var2) {
         var3.aShortArray3869 = this.aShortArray3869;
         var3.anIntArray3898 = this.anIntArray3898;
         var3.anIntArray3874 = this.anIntArray3874;
         var3.anIntArray3896 = this.anIntArray3896;
      } else {
         var3.aShortArray3869 = var5;
         var3.anIntArray3898 = var6;
         var3.anIntArray3874 = var7;
         var3.anIntArray3896 = var8;

         for(var9 = 0; var9 < this.anInt3889; ++var9) {
            var3.aShortArray3869[var9] = this.aShortArray3869[var9];
            var3.anIntArray3898[var9] = this.anIntArray3898[var9];
            var3.anIntArray3874[var9] = this.anIntArray3874[var9];
            var3.anIntArray3896[var9] = this.anIntArray3896[var9];
         }
      }

      var3.anIntArray3901 = this.anIntArray3901;
      var3.anIntArray3876 = this.anIntArray3876;
      var3.anIntArray3887 = this.anIntArray3887;
      var3.aByteArray3880 = this.aByteArray3880;
      var3.aByteArray3899 = this.aByteArray3899;
      var3.aShortArray3908 = this.aShortArray3908;
      var3.aByte3875 = this.aByte3875;
      var3.anIntArray3882 = this.anIntArray3882;
      var3.anIntArray3890 = this.anIntArray3890;
      var3.anIntArray3881 = this.anIntArray3881;
      var3.anIntArrayArray3870 = this.anIntArrayArray3870;
      var3.anIntArrayArray3872 = this.anIntArrayArray3872;
      var3.aShortArray3893 = this.aShortArray3893;
      var3.aShortArray3871 = this.aShortArray3871;
      var3.aBoolean2699 = this.aBoolean2699;
      var3.aBoolean3897 = false;
      return var3;
   }

   final void method1896(int var1) {
      int var2 = Class51.anIntArray840[var1];
      int var3 = Class51.anIntArray851[var1];

      for(int var4 = 0; var4 < this.anInt3891; ++var4) {
         int var5 = this.anIntArray3883[var4] * var3 - this.anIntArray3895[var4] * var2 >> 16;
         this.anIntArray3895[var4] = this.anIntArray3883[var4] * var2 + this.anIntArray3895[var4] * var3 >> 16;
         this.anIntArray3883[var4] = var5;
      }

      this.aBoolean3897 = false;
   }

   final boolean method1873() {
      if(this.anIntArrayArray3870 == null) {
         return false;
      } else {
         anInt3945 = 0;
         anInt3934 = 0;
         anInt3940 = 0;
         return true;
      }
   }

   static int method1940(int var0, int var1) {
      var1 = var1 * (var0 & 127) >> 7;
      if(var1 < 2) {
         var1 = 2;
      } else if(var1 > 126) {
         var1 = 126;
      }

      return (var0 & '\uff80') + var1;
   }

   final void method1886(int var1) {
      int var2 = Class51.anIntArray840[var1];
      int var3 = Class51.anIntArray851[var1];

      for(int var4 = 0; var4 < this.anInt3891; ++var4) {
         int var5 = this.anIntArray3883[var4] * var2 + this.anIntArray3885[var4] * var3 >> 16;
         this.anIntArray3883[var4] = this.anIntArray3883[var4] * var3 - this.anIntArray3885[var4] * var2 >> 16;
         this.anIntArray3885[var4] = var5;
      }

      this.aBoolean3897 = false;
   }

   final void animate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, long var9, int var11, Class127_Sub1 var12) {
      if(!this.aBoolean3897) {
         this.method1947();
      }

      int var15 = var8 * var5 - var6 * var4 >> 16;
      int var16 = var7 * var2 + var15 * var3 >> 16;
      int var13 = var16 + (this.aShort3884 * var3 + this.aShort3892 * var2 >> 16);
      int var14 = var16 + (-this.aShort3884 * var3 + this.aShort3894 * var2 >> 16);
      if(var13 > 50) {
         if(var14 < 3500) {
            int var17 = var8 * var4 + var6 * var5 >> 16;
            int var18 = var17 + this.aShort3884 << 9;
            if(var18 / var13 > Class139.anInt1824) {
               int var19 = var17 - this.aShort3884 << 9;
               if(var19 / var13 < Class145.anInt1898) {
                  int var20 = var7 * var3 - var15 * var2 >> 16;
                  int var21 = var20 + (this.aShort3884 * var2 + this.aShort3892 * var3 >> 16) << 9;
                  if(var21 / var13 > Class1.anInt55) {
                     int var22 = var20 + (-this.aShort3884 * var2 + this.aShort3894 * var3 >> 16) << 9;
                     if(var22 / var13 < Class86.anInt1195) {
                        boolean var23 = false;
                        boolean var24 = var14 <= 50;
                        boolean var25 = var24 || this.anInt3900 > 0;
                        int var26 = Class51.anInt846;
                        int var27 = Class51.anInt835;
                        int var28 = 0;
                        int var29 = 0;
                        if(var1 != 0) {
                           var28 = Class51.anIntArray840[var1];
                           var29 = Class51.anIntArray851[var1];
                        }

                        boolean var30 = false;
                        int var31;
                        int var34;
                        int var32;
                        int var33;
                        if(var9 > 0L && Class3_Sub13_Sub7.aBoolean3094 && var14 > 0) {
                           if(var17 > 0) {
                              var31 = var19 / var13;
                              var33 = var18 / var14;
                           } else {
                              var31 = var19 / var14;
                              var33 = var18 / var13;
                           }

                           if(var20 > 0) {
                              var32 = var22 / var13;
                              var34 = var21 / var14;
                           } else {
                              var32 = var22 / var14;
                              var34 = var21 / var13;
                           }

                           if(Class3_Sub28_Sub11.anInt3642 >= var31 && Class3_Sub28_Sub11.anInt3642 <= var33 && RenderAnimationDefinition.anInt384 >= var32 && RenderAnimationDefinition.anInt384 <= var34) {
                              var31 = 999999;
                              var33 = -999999;
                              var32 = 999999;
                              var34 = -999999;
                              int[] var35 = new int[]{this.aShort3873, this.aShort3878, this.aShort3873, this.aShort3878, this.aShort3873, this.aShort3878, this.aShort3873, this.aShort3878};
                              int[] var36 = new int[]{this.aShort3879, this.aShort3879, this.aShort3888, this.aShort3888, this.aShort3879, this.aShort3879, this.aShort3888, this.aShort3888};
                              int[] var37 = new int[]{this.aShort3894, this.aShort3894, this.aShort3894, this.aShort3894, this.aShort3892, this.aShort3892, this.aShort3892, this.aShort3892};

                              for(int var38 = 0; var38 < 8; ++var38) {
                                 int var39 = var35[var38];
                                 int var40 = var37[var38];
                                 int var41 = var36[var38];
                                 int var42;
                                 if(var1 != 0) {
                                    var42 = var41 * var28 + var39 * var29 >> 16;
                                    var41 = var41 * var29 - var39 * var28 >> 16;
                                    var39 = var42;
                                 }

                                 var39 += var6;
                                 var40 += var7;
                                 var41 += var8;
                                 var42 = var41 * var4 + var39 * var5 >> 16;
                                 var41 = var41 * var5 - var39 * var4 >> 16;
                                 var39 = var42;
                                 var42 = var40 * var3 - var41 * var2 >> 16;
                                 var41 = var40 * var2 + var41 * var3 >> 16;
                                 if(var41 > 0) {
                                    int var43 = (var39 << 9) / var41;
                                    int var44 = (var42 << 9) / var41;
                                    if(var43 < var31) {
                                       var31 = var43;
                                    }

                                    if(var43 > var33) {
                                       var33 = var43;
                                    }

                                    if(var44 < var32) {
                                       var32 = var44;
                                    }

                                    if(var44 > var34) {
                                       var34 = var44;
                                    }
                                 }
                              }

                              if(Class3_Sub28_Sub11.anInt3642 >= var31 && Class3_Sub28_Sub11.anInt3642 <= var33 && RenderAnimationDefinition.anInt384 >= var32 && RenderAnimationDefinition.anInt384 <= var34) {
                                 if(this.aBoolean2699) {
                                    Class3_Sub13_Sub38.aLongArray3448[Class2.anInt59++] = var9;
                                 } else {
                                    var30 = true;
                                 }
                              }
                           }
                        }

                        for(var31 = 0; var31 < this.anInt3891; ++var31) {
                           var32 = this.anIntArray3885[var31];
                           var33 = this.anIntArray3883[var31];
                           var34 = this.anIntArray3895[var31];
                           int var46;
                           if(var1 != 0) {
                              var46 = var34 * var28 + var32 * var29 >> 16;
                              var34 = var34 * var29 - var32 * var28 >> 16;
                              var32 = var46;
                           }

                           var32 += var6;
                           var33 += var7;
                           var34 += var8;
                           var46 = var34 * var4 + var32 * var5 >> 16;
                           var34 = var34 * var5 - var32 * var4 >> 16;
                           var32 = var46;
                           var46 = var33 * var3 - var34 * var2 >> 16;
                           var34 = var33 * var2 + var34 * var3 >> 16;
                           anIntArray3920[var31] = var34 - var16;
                           if(var34 >= 50) {
                              anIntArray3943[var31] = var26 + (var32 << 9) / var34;
                              anIntArray3932[var31] = var27 + (var46 << 9) / var34;
                           } else {
                              anIntArray3943[var31] = -5000;
                              var23 = true;
                           }

                           if(var25) {
                              anIntArray3948[var31] = var32;
                              anIntArray3928[var31] = var46;
                              anIntArray3921[var31] = var34;
                           }
                        }

                        try {
                           this.method1945(var23, var30, var9, var16 - var14, var13 - var14 + 2);
                        } catch (Exception var45) {
                        }

                     }
                  }
               }
            }
         }
      }
   }

   final Class140_Sub1_Sub2 method1941(int var1, int var2, int[][] var3, int[][] var4, int var5, int var6, int var7, boolean var8) {
      if(!this.aBoolean3897) {
         this.method1947();
      }

      int var9 = var5 + this.aShort3873;
      int var10 = var5 + this.aShort3878;
      int var11 = var7 + this.aShort3879;
      int var12 = var7 + this.aShort3888;
      if((var1 == 1 || var1 == 2 || var1 == 3 || var1 == 5) && (var9 < 0 || var10 + 128 >> 7 >= var3.length || var11 < 0 || var12 + 128 >> 7 >= var3[0].length)) {
         return this;
      } else {
         if(var1 == 4 || var1 == 5) {
            if(var4 == null) {
               return this;
            }

            if(var9 < 0 || var10 + 128 >> 7 >= var4.length || var11 < 0 || var12 + 128 >> 7 >= var4[0].length) {
               return this;
            }
         } else {
            var9 >>= 7;
            var10 = var10 + 127 >> 7;
            var11 >>= 7;
            var12 = var12 + 127 >> 7;
            if(var3[var9][var11] == var6 && var3[var10][var11] == var6 && var3[var9][var12] == var6 && var3[var10][var12] == var6) {
               return this;
            }
         }

          Class140_Sub1_Sub2 var13;
         if(var8) {
            var13 = new Class140_Sub1_Sub2();
            var13.anInt3891 = this.anInt3891;
            var13.anInt3889 = this.anInt3889;
            var13.anInt3900 = this.anInt3900;
            var13.anIntArray3901 = this.anIntArray3901;
            var13.anIntArray3876 = this.anIntArray3876;
            var13.anIntArray3887 = this.anIntArray3887;
            var13.anIntArray3898 = this.anIntArray3898;
            var13.anIntArray3874 = this.anIntArray3874;
            var13.anIntArray3896 = this.anIntArray3896;
            var13.aByteArray3880 = this.aByteArray3880;
            var13.aByteArray3899 = this.aByteArray3899;
            var13.aShortArray3908 = this.aShortArray3908;
            var13.aShortArray3869 = this.aShortArray3869;
            var13.aByteArray3903 = this.aByteArray3903;
            var13.aByte3875 = this.aByte3875;
            var13.anIntArray3882 = this.anIntArray3882;
            var13.anIntArray3890 = this.anIntArray3890;
            var13.anIntArray3881 = this.anIntArray3881;
            var13.anIntArrayArray3870 = this.anIntArrayArray3870;
            var13.anIntArrayArray3872 = this.anIntArrayArray3872;
            var13.aShortArray3893 = this.aShortArray3893;
            var13.aShortArray3871 = this.aShortArray3871;
            var13.aBoolean2699 = this.aBoolean2699;
            if(var1 == 3) {
               var13.anIntArray3885 = Class65.method1233(this.anIntArray3885, 2);
               var13.anIntArray3883 = Class65.method1233(this.anIntArray3883, 2);
               var13.anIntArray3895 = Class65.method1233(this.anIntArray3895, 2);
            } else {
               var13.anIntArray3885 = this.anIntArray3885;
               var13.anIntArray3883 = new int[var13.anInt3891];
               var13.anIntArray3895 = this.anIntArray3895;
            }
         } else {
            var13 = this;
         }

         int var14;
         int var15;
         int var17;
         int var16;
         int var19;
         int var18;
         int var21;
         int var20;
         int var23;
         int var22;
         if(var1 == 1) {
            for(var14 = 0; var14 < var13.anInt3891; ++var14) {
               var15 = this.anIntArray3885[var14] + var5;
               var16 = this.anIntArray3895[var14] + var7;
               var17 = var15 & 127;
               var18 = var16 & 127;
               var19 = var15 >> 7;
               var20 = var16 >> 7;
               var21 = var3[var19][var20] * (128 - var17) + var3[var19 + 1][var20] * var17 >> 7;
               var22 = var3[var19][var20 + 1] * (128 - var17) + var3[var19 + 1][var20 + 1] * var17 >> 7;
               var23 = var21 * (128 - var18) + var22 * var18 >> 7;
               var13.anIntArray3883[var14] = this.anIntArray3883[var14] + var23 - var6;
            }
         } else {
            int var24;
            if(var1 == 2) {
               for(var14 = 0; var14 < var13.anInt3891; ++var14) {
                  var15 = (this.anIntArray3883[var14] << 16) / this.aShort3894;
                  if(var15 < var2) {
                     var16 = this.anIntArray3885[var14] + var5;
                     var17 = this.anIntArray3895[var14] + var7;
                     var18 = var16 & 127;
                     var19 = var17 & 127;
                     var20 = var16 >> 7;
                     var21 = var17 >> 7;
                     var22 = var3[var20][var21] * (128 - var18) + var3[var20 + 1][var21] * var18 >> 7;
                     var23 = var3[var20][var21 + 1] * (128 - var18) + var3[var20 + 1][var21 + 1] * var18 >> 7;
                     var24 = var22 * (128 - var19) + var23 * var19 >> 7;
                     var13.anIntArray3883[var14] = this.anIntArray3883[var14] + (var24 - var6) * (var2 - var15) / var2;
                  } else {
                     var13.anIntArray3883[var14] = this.anIntArray3883[var14];
                  }
               }
            } else if(var1 == 3) {
               var14 = (var2 & 255) * 4;
               var15 = (var2 >> 8 & 255) * 4;
               var13.method1895(var3, var5, var6, var7, var14, var15);
            } else if(var1 == 4) {
               var14 = this.aShort3892 - this.aShort3894;

               for(var15 = 0; var15 < this.anInt3891; ++var15) {
                  var16 = this.anIntArray3885[var15] + var5;
                  var17 = this.anIntArray3895[var15] + var7;
                  var18 = var16 & 127;
                  var19 = var17 & 127;
                  var20 = var16 >> 7;
                  var21 = var17 >> 7;
                  var22 = var4[var20][var21] * (128 - var18) + var4[var20 + 1][var21] * var18 >> 7;
                  var23 = var4[var20][var21 + 1] * (128 - var18) + var4[var20 + 1][var21 + 1] * var18 >> 7;
                  var24 = var22 * (128 - var19) + var23 * var19 >> 7;
                  var13.anIntArray3883[var15] = this.anIntArray3883[var15] + (var24 - var6) + var14;
               }
            } else if(var1 == 5) {
               var14 = this.aShort3892 - this.aShort3894;

               for(var15 = 0; var15 < this.anInt3891; ++var15) {
                  var16 = this.anIntArray3885[var15] + var5;
                  var17 = this.anIntArray3895[var15] + var7;
                  var18 = var16 & 127;
                  var19 = var17 & 127;
                  var20 = var16 >> 7;
                  var21 = var17 >> 7;
                  var22 = var3[var20][var21] * (128 - var18) + var3[var20 + 1][var21] * var18 >> 7;
                  var23 = var3[var20][var21 + 1] * (128 - var18) + var3[var20 + 1][var21 + 1] * var18 >> 7;
                  var24 = var22 * (128 - var19) + var23 * var19 >> 7;
                  var22 = var4[var20][var21] * (128 - var18) + var4[var20 + 1][var21] * var18 >> 7;
                  var23 = var4[var20][var21 + 1] * (128 - var18) + var4[var20 + 1][var21 + 1] * var18 >> 7;
                  int var25 = var22 * (128 - var19) + var23 * var19 >> 7;
                  int var26 = var24 - var25;
                  var13.anIntArray3883[var15] = ((this.anIntArray3883[var15] << 8) / var14 * var26 >> 8) - (var6 - var24);
               }
            }
         }

         var13.aBoolean3897 = false;
         return var13;
      }
   }

   final void method1885() {
      for(int var1 = 0; var1 < this.anInt3891; ++var1) {
         int var2 = this.anIntArray3885[var1];
         this.anIntArray3885[var1] = this.anIntArray3895[var1];
         this.anIntArray3895[var1] = -var2;
      }

      this.aBoolean3897 = false;
   }

   final void method1893(int var2, int var3, int var4, int var5, int var6, int var7) {
      try {
         if(!this.aBoolean3897) {
            this.method1947();
         }

         int var10 = Class51.anInt846;
         int var11 = Class51.anInt835;
         int var14 = Class51.anIntArray840[var2];
         int var15 = Class51.anIntArray851[var2];
         int var16 = Class51.anIntArray840[var3];
         int var17 = Class51.anIntArray851[var3];
         int var18 = Class51.anIntArray840[var4];
         int var19 = Class51.anIntArray851[var4];
         int var20 = var6 * var18 + var7 * var19 >> 16;

         for(int var21 = 0; var21 < this.anInt3891; ++var21) {
            int var22 = this.anIntArray3885[var21];
            int var23 = this.anIntArray3883[var21];
            int var24 = this.anIntArray3895[var21];
            int var25;
            if(var3 != 0) {
               var25 = var23 * var16 + var22 * var17 >> 16;
               var23 = var23 * var17 - var22 * var16 >> 16;
               var22 = var25;
            }

             if(var2 != 0) {
               var25 = var24 * var14 + var22 * var15 >> 16;
               var24 = var24 * var15 - var22 * var14 >> 16;
               var22 = var25;
            }

            var22 += var5;
            var23 += var6;
            var24 += var7;
            var25 = var23 * var19 - var24 * var18 >> 16;
            var24 = var23 * var18 + var24 * var19 >> 16;
            anIntArray3920[var21] = var24 - var20;
            anIntArray3943[var21] = var10 + (var22 << 9) / var24;
            anIntArray3932[var21] = var11 + (var25 << 9) / var24;
            if(this.anInt3900 > 0) {
               anIntArray3948[var21] = var22;
               anIntArray3928[var21] = var25;
               anIntArray3921[var21] = var24;
            }
         }

         this.method1945(false, false, (long) -1, this.aShort3886, this.aShort3886 << 1);
      } catch (RuntimeException var26) {
      }

   }

   private void method1942(int var1) {
      int var2 = Class51.anInt846;
      int var3 = Class51.anInt835;
      int var4 = 0;
      int var5 = this.anIntArray3901[var1];
      int var6 = this.anIntArray3876[var1];
      int var7 = this.anIntArray3887[var1];
      int var8 = anIntArray3921[var5];
      int var9 = anIntArray3921[var6];
      int var10 = anIntArray3921[var7];
      if(this.aByteArray3903 == null) {
         Class51.anInt850 = 0;
      } else {
         Class51.anInt850 = this.aByteArray3903[var1] & 255;
      }

      int var11;
      int var12;
      int var13;
      int var14;
      if(var8 >= 50) {
         anIntArray3919[var4] = anIntArray3943[var5];
         anIntArray3925[var4] = anIntArray3932[var5];
         anIntArray3936[var4++] = this.anIntArray3898[var1] & '\uffff';
      } else {
         var11 = anIntArray3948[var5];
         var12 = anIntArray3928[var5];
         var13 = this.anIntArray3898[var1] & '\uffff';
         if(var10 >= 50) {
            var14 = (50 - var8) * Class51.anIntArray841[var10 - var8];
            anIntArray3919[var4] = var2 + (var11 + ((anIntArray3948[var7] - var11) * var14 >> 16) << 9) / 50;
            anIntArray3925[var4] = var3 + (var12 + ((anIntArray3928[var7] - var12) * var14 >> 16) << 9) / 50;
            anIntArray3936[var4++] = var13 + (((this.anIntArray3896[var1] & '\uffff') - var13) * var14 >> 16);
         }

         if(var9 >= 50) {
            var14 = (50 - var8) * Class51.anIntArray841[var9 - var8];
            anIntArray3919[var4] = var2 + (var11 + ((anIntArray3948[var6] - var11) * var14 >> 16) << 9) / 50;
            anIntArray3925[var4] = var3 + (var12 + ((anIntArray3928[var6] - var12) * var14 >> 16) << 9) / 50;
            anIntArray3936[var4++] = var13 + (((this.anIntArray3874[var1] & '\uffff') - var13) * var14 >> 16);
         }
      }

      if(var9 >= 50) {
         anIntArray3919[var4] = anIntArray3943[var6];
         anIntArray3925[var4] = anIntArray3932[var6];
         anIntArray3936[var4++] = this.anIntArray3874[var1] & '\uffff';
      } else {
         var11 = anIntArray3948[var6];
         var12 = anIntArray3928[var6];
         var13 = this.anIntArray3874[var1] & '\uffff';
         if(var8 >= 50) {
            var14 = (50 - var9) * Class51.anIntArray841[var8 - var9];
            anIntArray3919[var4] = var2 + (var11 + ((anIntArray3948[var5] - var11) * var14 >> 16) << 9) / 50;
            anIntArray3925[var4] = var3 + (var12 + ((anIntArray3928[var5] - var12) * var14 >> 16) << 9) / 50;
            anIntArray3936[var4++] = var13 + (((this.anIntArray3898[var1] & '\uffff') - var13) * var14 >> 16);
         }

         if(var10 >= 50) {
            var14 = (50 - var9) * Class51.anIntArray841[var10 - var9];
            anIntArray3919[var4] = var2 + (var11 + ((anIntArray3948[var7] - var11) * var14 >> 16) << 9) / 50;
            anIntArray3925[var4] = var3 + (var12 + ((anIntArray3928[var7] - var12) * var14 >> 16) << 9) / 50;
            anIntArray3936[var4++] = var13 + (((this.anIntArray3896[var1] & '\uffff') - var13) * var14 >> 16);
         }
      }

      if(var10 >= 50) {
         anIntArray3919[var4] = anIntArray3943[var7];
         anIntArray3925[var4] = anIntArray3932[var7];
         anIntArray3936[var4++] = this.anIntArray3896[var1] & '\uffff';
      } else {
         var11 = anIntArray3948[var7];
         var12 = anIntArray3928[var7];
         var13 = this.anIntArray3896[var1] & '\uffff';
         if(var9 >= 50) {
            var14 = (50 - var10) * Class51.anIntArray841[var9 - var10];
            anIntArray3919[var4] = var2 + (var11 + ((anIntArray3948[var6] - var11) * var14 >> 16) << 9) / 50;
            anIntArray3925[var4] = var3 + (var12 + ((anIntArray3928[var6] - var12) * var14 >> 16) << 9) / 50;
            anIntArray3936[var4++] = var13 + (((this.anIntArray3874[var1] & '\uffff') - var13) * var14 >> 16);
         }

         if(var8 >= 50) {
            var14 = (50 - var10) * Class51.anIntArray841[var8 - var10];
            anIntArray3919[var4] = var2 + (var11 + ((anIntArray3948[var5] - var11) * var14 >> 16) << 9) / 50;
            anIntArray3925[var4] = var3 + (var12 + ((anIntArray3928[var5] - var12) * var14 >> 16) << 9) / 50;
            anIntArray3936[var4++] = var13 + (((this.anIntArray3898[var1] & '\uffff') - var13) * var14 >> 16);
         }
      }

      var11 = anIntArray3919[0];
      var12 = anIntArray3919[1];
      var13 = anIntArray3919[2];
      var14 = anIntArray3925[0];
      int var15 = anIntArray3925[1];
      int var16 = anIntArray3925[2];
      Class51.aBoolean849 = false;
      int var17;
      int var19;
      int var18;
      int var20;
      if(var4 == 3) {
         if(var11 < 0 || var12 < 0 || var13 < 0 || var11 > Class51.anInt847 || var12 > Class51.anInt847 || var13 > Class51.anInt847) {
            Class51.aBoolean849 = true;
         }

         if(this.aShortArray3908 != null && this.aShortArray3908[var1] != -1) {
            if(this.aByteArray3899 == null || this.aByteArray3899[var1] == -1) {
               var17 = var5;
               var18 = var6;
               var19 = var7;
            } else {
               var20 = this.aByteArray3899[var1] & 255;
               var17 = this.anIntArray3882[var20];
               var18 = this.anIntArray3890[var20];
               var19 = this.anIntArray3881[var20];
            }

             if(this.anIntArray3896[var1] == -1) {
               Class51.method1138(var14, var15, var16, var11, var12, var13, this.anIntArray3898[var1], this.anIntArray3898[var1], this.anIntArray3898[var1], anIntArray3948[var17], anIntArray3948[var18], anIntArray3948[var19], anIntArray3928[var17], anIntArray3928[var18], anIntArray3928[var19], anIntArray3921[var17], anIntArray3921[var18], anIntArray3921[var19], this.aShortArray3908[var1]);
            } else {
               Class51.method1138(var14, var15, var16, var11, var12, var13, anIntArray3936[0], anIntArray3936[1], anIntArray3936[2], anIntArray3948[var17], anIntArray3948[var18], anIntArray3948[var19], anIntArray3928[var17], anIntArray3928[var18], anIntArray3928[var19], anIntArray3921[var17], anIntArray3921[var18], anIntArray3921[var19], this.aShortArray3908[var1]);
            }
         } else if(this.anIntArray3896[var1] == -1) {
            Class51.method1144(var14, var15, var16, var11, var12, var13, Class51.anIntArray834[this.anIntArray3898[var1] & '\uffff']);
         } else {
            Class51.method1154(var14, var15, var16, var11, var12, var13, anIntArray3936[0], anIntArray3936[1], anIntArray3936[2]);
         }
      }

      if(var4 == 4) {
         if(var11 < 0 || var12 < 0 || var13 < 0 || var11 > Class51.anInt847 || var12 > Class51.anInt847 || var13 > Class51.anInt847 || anIntArray3919[3] < 0 || anIntArray3919[3] > Class51.anInt847) {
            Class51.aBoolean849 = true;
         }

         if(this.aShortArray3908 != null && this.aShortArray3908[var1] != -1) {
            if(this.aByteArray3899 == null || this.aByteArray3899[var1] == -1) {
               var17 = var5;
               var18 = var6;
               var19 = var7;
            } else {
               var20 = this.aByteArray3899[var1] & 255;
               var17 = this.anIntArray3882[var20];
               var18 = this.anIntArray3890[var20];
               var19 = this.anIntArray3881[var20];
            }

             short var21 = this.aShortArray3908[var1];
            if(this.anIntArray3896[var1] == -1) {
               Class51.method1138(var14, var15, var16, var11, var12, var13, this.anIntArray3898[var1], this.anIntArray3898[var1], this.anIntArray3898[var1], anIntArray3948[var17], anIntArray3948[var18], anIntArray3948[var19], anIntArray3928[var17], anIntArray3928[var18], anIntArray3928[var19], anIntArray3921[var17], anIntArray3921[var18], anIntArray3921[var19], var21);
               Class51.method1138(var14, var16, anIntArray3925[3], var11, var13, anIntArray3919[3], this.anIntArray3898[var1], this.anIntArray3898[var1], this.anIntArray3898[var1], anIntArray3948[var17], anIntArray3948[var18], anIntArray3948[var19], anIntArray3928[var17], anIntArray3928[var18], anIntArray3928[var19], anIntArray3921[var17], anIntArray3921[var18], anIntArray3921[var19], var21);
            } else {
               Class51.method1138(var14, var15, var16, var11, var12, var13, anIntArray3936[0], anIntArray3936[1], anIntArray3936[2], anIntArray3948[var17], anIntArray3948[var18], anIntArray3948[var19], anIntArray3928[var17], anIntArray3928[var18], anIntArray3928[var19], anIntArray3921[var17], anIntArray3921[var18], anIntArray3921[var19], var21);
               Class51.method1138(var14, var16, anIntArray3925[3], var11, var13, anIntArray3919[3], anIntArray3936[0], anIntArray3936[2], anIntArray3936[3], anIntArray3948[var17], anIntArray3948[var18], anIntArray3948[var19], anIntArray3928[var17], anIntArray3928[var18], anIntArray3928[var19], anIntArray3921[var17], anIntArray3921[var18], anIntArray3921[var19], var21);
            }
         } else if(this.anIntArray3896[var1] == -1) {
            var17 = Class51.anIntArray834[this.anIntArray3898[var1] & '\uffff'];
            Class51.method1144(var14, var15, var16, var11, var12, var13, var17);
            Class51.method1144(var14, var16, anIntArray3925[3], var11, var13, anIntArray3919[3], var17);
         } else {
            Class51.method1154(var14, var15, var16, var11, var12, var13, anIntArray3936[0], anIntArray3936[1], anIntArray3936[2]);
            Class51.method1154(var14, var16, anIntArray3925[3], var11, var13, anIntArray3919[3], anIntArray3936[0], anIntArray3936[2], anIntArray3936[3]);
         }
      }

   }

   final int method1872() {
      if(!this.aBoolean3897) {
         this.method1947();
      }

      return this.aShort3888;
   }

   final void resize(int var1, int var2, int var3) {
      for(int var4 = 0; var4 < this.anInt3891; ++var4) {
         this.anIntArray3885[var4] = this.anIntArray3885[var4] * var1 / 128;
         this.anIntArray3883[var4] = this.anIntArray3883[var4] * var2 / 128;
         this.anIntArray3895[var4] = this.anIntArray3895[var4] * var3 / 128;
      }

      this.aBoolean3897 = false;
   }

   final Model method1882(boolean var1, boolean var2, boolean var3) {
      if(!var1 && aByteArray3914.length < this.anInt3889) {
         aByteArray3914 = new byte[this.anInt3889 + 100];
      }

      if(!var2 && aShortArray3912.length < this.anInt3889) {
         anIntArray3913 = new int[this.anInt3889 + 100];
         anIntArray3916 = new int[this.anInt3889 + 100];
         anIntArray3911 = new int[this.anInt3889 + 100];
         aShortArray3912 = new short[this.anInt3889 + 100];
      }

      return this.method1939(var1, var2, aClass140_Sub1_Sub2_3906, aByteArray3914, aShortArray3912, anIntArray3913, anIntArray3916, anIntArray3911);
   }

   final int method1888() {
      if(!this.aBoolean3897) {
         this.method1947();
      }

      return this.aShort3884;
   }

   final Model method1943(Model var1) {
      return new Class140_Sub1_Sub2(new Class140_Sub1_Sub2[]{this, (Class140_Sub1_Sub2)var1});
   }

   final int method1883() {
      if(!this.aBoolean3897) {
         this.method1947();
      }

      return this.aShort3878;
   }

   final void method1879() {
      if(this.aBoolean3877) {
         this.method1936();
         this.aBoolean3877 = false;
      }

      this.aBoolean3897 = false;
   }

   final void method1876(int var1) {
      int var2 = Class51.anIntArray840[var1];
      int var3 = Class51.anIntArray851[var1];

      for(int var4 = 0; var4 < this.anInt3891; ++var4) {
         int var5 = this.anIntArray3895[var4] * var2 + this.anIntArray3885[var4] * var3 >> 16;
         this.anIntArray3895[var4] = this.anIntArray3895[var4] * var3 - this.anIntArray3885[var4] * var2 >> 16;
         this.anIntArray3885[var4] = var5;
      }

      this.aBoolean3897 = false;
   }

   final void method1891(int var1, int[] var2, int var3, int var4, int var5, boolean var6) {
      int var7 = var2.length;
      int var8;
      int var9;
      int var12;
      int var13;
      if(var1 == 0) {
         var8 = 0;
         anInt3945 = 0;
         anInt3934 = 0;
         anInt3940 = 0;

         for(var9 = 0; var9 < var7; ++var9) {
            int var17 = var2[var9];
            if(var17 < this.anIntArrayArray3870.length) {
               int[] var18 = this.anIntArrayArray3870[var17];

               for(var12 = 0; var12 < var18.length; ++var12) {
                  var13 = var18[var12];
                  anInt3945 += this.anIntArray3885[var13];
                  anInt3934 += this.anIntArray3883[var13];
                  anInt3940 += this.anIntArray3895[var13];
                  ++var8;
               }
            }
         }

         if(var8 > 0) {
            anInt3945 = anInt3945 / var8 + var3;
            anInt3934 = anInt3934 / var8 + var4;
            anInt3940 = anInt3940 / var8 + var5;
         } else {
            anInt3945 = var3;
            anInt3934 = var4;
            anInt3940 = var5;
         }

      } else {
         int[] var10;
         int var11;
         if(var1 == 1) {
            for(var8 = 0; var8 < var7; ++var8) {
               var9 = var2[var8];
               if(var9 < this.anIntArrayArray3870.length) {
                  var10 = this.anIntArrayArray3870[var9];

                  for(var11 = 0; var11 < var10.length; ++var11) {
                     var12 = var10[var11];
                     this.anIntArray3885[var12] += var3;
                     this.anIntArray3883[var12] += var4;
                     this.anIntArray3895[var12] += var5;
                  }
               }
            }

         } else {
            int var14;
            int var15;
            if(var1 == 2) {
               for(var8 = 0; var8 < var7; ++var8) {
                  var9 = var2[var8];
                  if(var9 < this.anIntArrayArray3870.length) {
                     var10 = this.anIntArrayArray3870[var9];

                     for(var11 = 0; var11 < var10.length; ++var11) {
                        var12 = var10[var11];
                        this.anIntArray3885[var12] -= anInt3945;
                        this.anIntArray3883[var12] -= anInt3934;
                        this.anIntArray3895[var12] -= anInt3940;
                        if(var5 != 0) {
                           var13 = Class51.anIntArray840[var5];
                           var14 = Class51.anIntArray851[var5];
                           var15 = this.anIntArray3883[var12] * var13 + this.anIntArray3885[var12] * var14 + 32767 >> 16;
                           this.anIntArray3883[var12] = this.anIntArray3883[var12] * var14 - this.anIntArray3885[var12] * var13 + 32767 >> 16;
                           this.anIntArray3885[var12] = var15;
                        }

                        if(var3 != 0) {
                           var13 = Class51.anIntArray840[var3];
                           var14 = Class51.anIntArray851[var3];
                           var15 = this.anIntArray3883[var12] * var14 - this.anIntArray3895[var12] * var13 + 32767 >> 16;
                           this.anIntArray3895[var12] = this.anIntArray3883[var12] * var13 + this.anIntArray3895[var12] * var14 + 32767 >> 16;
                           this.anIntArray3883[var12] = var15;
                        }

                        if(var4 != 0) {
                           var13 = Class51.anIntArray840[var4];
                           var14 = Class51.anIntArray851[var4];
                           var15 = this.anIntArray3895[var12] * var13 + this.anIntArray3885[var12] * var14 + 32767 >> 16;
                           this.anIntArray3895[var12] = this.anIntArray3895[var12] * var14 - this.anIntArray3885[var12] * var13 + 32767 >> 16;
                           this.anIntArray3885[var12] = var15;
                        }

                        this.anIntArray3885[var12] += anInt3945;
                        this.anIntArray3883[var12] += anInt3934;
                        this.anIntArray3895[var12] += anInt3940;
                     }
                  }
               }

            } else if(var1 == 3) {
               for(var8 = 0; var8 < var7; ++var8) {
                  var9 = var2[var8];
                  if(var9 < this.anIntArrayArray3870.length) {
                     var10 = this.anIntArrayArray3870[var9];

                     for(var11 = 0; var11 < var10.length; ++var11) {
                        var12 = var10[var11];
                        this.anIntArray3885[var12] -= anInt3945;
                        this.anIntArray3883[var12] -= anInt3934;
                        this.anIntArray3895[var12] -= anInt3940;
                        this.anIntArray3885[var12] = this.anIntArray3885[var12] * var3 / 128;
                        this.anIntArray3883[var12] = this.anIntArray3883[var12] * var4 / 128;
                        this.anIntArray3895[var12] = this.anIntArray3895[var12] * var5 / 128;
                        this.anIntArray3885[var12] += anInt3945;
                        this.anIntArray3883[var12] += anInt3934;
                        this.anIntArray3895[var12] += anInt3940;
                     }
                  }
               }

            } else if(var1 == 5) {
               if(this.anIntArrayArray3872 != null && this.aByteArray3903 != null) {
                  for(var8 = 0; var8 < var7; ++var8) {
                     var9 = var2[var8];
                     if(var9 < this.anIntArrayArray3872.length) {
                        var10 = this.anIntArrayArray3872[var9];

                        for(var11 = 0; var11 < var10.length; ++var11) {
                           var12 = var10[var11];
                           var13 = (this.aByteArray3903[var12] & 255) + var3 * 8;
                           if(var13 < 0) {
                              var13 = 0;
                           } else if(var13 > 255) {
                              var13 = 255;
                           }

                           this.aByteArray3903[var12] = (byte)var13;
                        }
                     }
                  }
               }

            } else if(var1 == 7) {
               if(this.anIntArrayArray3872 != null) {
                  for(var8 = 0; var8 < var7; ++var8) {
                     var9 = var2[var8];
                     if(var9 < this.anIntArrayArray3872.length) {
                        var10 = this.anIntArrayArray3872[var9];

                        for(var11 = 0; var11 < var10.length; ++var11) {
                           var12 = var10[var11];
                           var13 = this.aShortArray3869[var12] & '\uffff';
                           var14 = var13 >> 10 & 63;
                           var15 = var13 >> 7 & 7;
                           int var16 = var13 & 127;
                           var14 = var14 + var3 & 63;
                           var15 += var4;
                           if(var15 < 0) {
                              var15 = 0;
                           } else if(var15 > 7) {
                              var15 = 7;
                           }

                           var16 += var5;
                           if(var16 < 0) {
                              var16 = 0;
                           } else if(var16 > 127) {
                              var16 = 127;
                           }

                           this.aShortArray3869[var12] = (short)(var14 << 10 | var15 << 7 | var16);
                        }

                        this.aBoolean3877 = true;
                     }
                  }
               }

            }
         }
      }
   }

   private boolean method1944(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      return (var2 >= var3 || var2 >= var4 || var2 >= var5) && ((var2 <= var3 || var2 <= var4 || var2 <= var5) && ((var1 >= var6 || var1 >= var7 || var1 >= var8) && (var1 <= var6 || var1 <= var7 || var1 <= var8)));
   }

   final Model method1894(boolean var1, boolean var2, boolean var3) {
      if(!var1 && aByteArray3907.length < this.anInt3889) {
         aByteArray3907 = new byte[this.anInt3889 + 100];
      }

      if(!var2 && aShortArray3910.length < this.anInt3889) {
         anIntArray3905 = new int[this.anInt3889 + 100];
         anIntArray3909 = new int[this.anInt3889 + 100];
         anIntArray3904 = new int[this.anInt3889 + 100];
         aShortArray3910 = new short[this.anInt3889 + 100];
      }

      return this.method1939(var1, var2, aClass140_Sub1_Sub2_3902, aByteArray3907, aShortArray3910, anIntArray3905, anIntArray3909, anIntArray3904);
   }

   private void method1945(boolean var1, boolean var2, long var3, int var5, int var6) {
      if(var6 < 1600) {
         int var8 = 0;
         int var9 = 0;
         int var10;
         if(!aBoolean3942) {
            for(var10 = 0; var10 < 1600; ++var10) {
               anIntArray3946[var10] = 0;
            }

            for(var10 = 0; var10 < 32; ++var10) {
               anIntArray3935[var10] = 0;
            }

            anInt3930 = 0;
         }

         int var11;
         int var12;
         int var13;
         int var14;
         int var15;
         int var17;
         int var19;
         int var18;
         int var21;
         for(var10 = 0; var10 < this.anInt3889; ++var10) {
            if(this.anIntArray3896[var10] != -2) {
               var11 = this.anIntArray3901[var10];
               var12 = this.anIntArray3876[var10];
               var13 = this.anIntArray3887[var10];
               var14 = anIntArray3943[var11];
               var15 = anIntArray3943[var12];
               int var16 = anIntArray3943[var13];
               if(var1 && (var14 == -5000 || var15 == -5000 || var16 == -5000)) {
                  var17 = anIntArray3948[var11];
                  var18 = anIntArray3948[var12];
                  var19 = anIntArray3948[var13];
                  int var20 = anIntArray3928[var11];
                  var21 = anIntArray3928[var12];
                  int var22 = anIntArray3928[var13];
                  int var23 = anIntArray3921[var11];
                  int var24 = anIntArray3921[var12];
                  int var25 = anIntArray3921[var13];
                  var17 -= var18;
                  var19 -= var18;
                  var20 -= var21;
                  var22 -= var21;
                  var23 -= var24;
                  var25 -= var24;
                  int var26 = var20 * var25 - var23 * var22;
                  int var27 = var23 * var19 - var17 * var25;
                  int var28 = var17 * var22 - var20 * var19;
                  if(var18 * var26 + var21 * var27 + var24 * var28 > 0) {
                     aBooleanArray3917[var10] = true;
                     if(aBoolean3942) {
                        anIntArray3931[var8] = (anIntArray3920[var11] + anIntArray3920[var12] + anIntArray3920[var13]) / 3;
                        anIntArray3929[var8++] = var10;
                     } else {
                        int var29 = (anIntArray3920[var11] + anIntArray3920[var12] + anIntArray3920[var13]) / 3 + var5;
                        if(anIntArray3946[var29] < 64) {
                           anIntArrayArray3926[var29][anIntArray3946[var29]++] = var10;
                        } else {
                           int var30 = anIntArray3946[var29];
                           if(var30 == 64) {
                              if(anInt3930 == 512) {
                                 continue;
                              }

                              anIntArray3946[var29] = var30 = 65 + anInt3930++;
                           }

                           var30 -= 65;
                           anIntArrayArray3923[var30][anIntArray3935[var30]++] = var10;
                        }
                     }
                  }
               } else {
                  if(var2 && this.method1944(Class3_Sub28_Sub11.anInt3642 + Class51.anInt846, RenderAnimationDefinition.anInt384 + Class51.anInt835, anIntArray3932[var11], anIntArray3932[var12], anIntArray3932[var13], var14, var15, var16)) {
                     Class3_Sub13_Sub38.aLongArray3448[Class2.anInt59++] = var3;
                     var2 = false;
                  }

                  if((var14 - var15) * (anIntArray3932[var13] - anIntArray3932[var12]) - (anIntArray3932[var11] - anIntArray3932[var12]) * (var16 - var15) > 0) {
                     aBooleanArray3917[var10] = false;
                     aBooleanArray3937[var10] = var14 < 0 || var15 < 0 || var16 < 0 || var14 > Class51.anInt847 || var15 > Class51.anInt847 || var16 > Class51.anInt847;

                     if(aBoolean3942) {
                        anIntArray3931[var8] = (anIntArray3920[var11] + anIntArray3920[var12] + anIntArray3920[var13]) / 3;
                        anIntArray3929[var8++] = var10;
                     } else {
                        var17 = (anIntArray3920[var11] + anIntArray3920[var12] + anIntArray3920[var13]) / 3 + var5;
                        if(anIntArray3946[var17] < 64) {
                           anIntArrayArray3926[var17][anIntArray3946[var17]++] = var10;
                        } else {
                           var18 = anIntArray3946[var17];
                           if(var18 == 64) {
                              if(anInt3930 == 512) {
                                 continue;
                              }

                              anIntArray3946[var17] = var18 = 65 + anInt3930++;
                           }

                           var18 -= 65;
                           anIntArrayArray3923[var18][anIntArray3935[var18]++] = var10;
                        }
                     }
                  }
               }
            }
         }

         if(aBoolean3942) {
            Class101.method1607(0, var8 - 1, false, anIntArray3931, anIntArray3929);
            if(this.aByteArray3880 == null) {
               for(var10 = 0; var10 < var8; ++var10) {
                  this.method1934(anIntArray3929[var10]);
               }

               return;
            }

            for(var10 = 0; var10 < 12; ++var10) {
               anIntArray3918[var10] = 0;
               anIntArray3944[var10] = 0;
            }

            for(var10 = 0; var10 < var8; ++var10) {
               var11 = anIntArray3929[var10];
               var12 = anIntArray3931[var10];
               byte var32 = this.aByteArray3880[var11];
               var14 = anIntArray3918[var32]++;
               anIntArrayArray3938[var32][var14] = var11;
               if(var32 < 10) {
                  anIntArray3944[var32] += var12;
               } else if(var32 == 10) {
                  anIntArray3922[var14] = var12;
               } else {
                  anIntArray3941[var14] = var12;
               }
            }
         } else {
            int[] var31;
            if(this.aByteArray3880 == null) {
               for(var10 = var6 - 1; var10 >= 0; --var10) {
                  var11 = anIntArray3946[var10];
                  if(var11 > 0) {
                     var12 = var11 > 64?64:var11;
                     var31 = anIntArrayArray3926[var10];

                     for(var14 = 0; var14 < var12; ++var14) {
                        var15 = var31[var14];
                        if(var15 < 65536) {
                           this.method1934(var31[var14]);
                        }
                     }
                  }

                  if(var11 > 64) {
                     var12 = anIntArray3946[var10] - 64 - 1;
                     var31 = anIntArrayArray3923[var12];

                     for(var14 = 0; var14 < anIntArray3935[var12]; ++var14) {
                        var15 = var31[var14];
                        if(var15 < 65536) {
                           this.method1934(var31[var14]);
                        }
                     }
                  }
               }

               return;
            }

            for(var10 = 0; var10 < 12; ++var10) {
               anIntArray3918[var10] = 0;
               anIntArray3944[var10] = 0;
            }

            for(var10 = var6 - 1; var10 >= 0; --var10) {
               var11 = anIntArray3946[var10];
               byte var33;
               if(var11 > 0) {
                  if(var11 > 64) {
                     var12 = 64;
                  } else {
                     var12 = var11;
                  }

                  var31 = anIntArrayArray3926[var10];

                  for(var14 = 0; var14 < var12; ++var14) {
                     var15 = var31[var14];
                     if(var15 < 65536) {
                        var33 = this.aByteArray3880[var15];
                        var17 = anIntArray3918[var33]++;
                        anIntArrayArray3938[var33][var17] = var15;
                        if(var33 < 10) {
                           anIntArray3944[var33] += var10;
                        } else if(var33 == 10) {
                           anIntArray3922[var17] = var10;
                        } else {
                           anIntArray3941[var17] = var10;
                        }
                     } else {
                        anIntArray3950[var9++] = (var15 >> 16) - 1;
                     }
                  }
               }

               if(var11 > 64) {
                  var12 = anIntArray3946[var10] - 64 - 1;
                  var31 = anIntArrayArray3923[var12];

                  for(var14 = 0; var14 < anIntArray3935[var12]; ++var14) {
                     var15 = var31[var14];
                     if(var15 < 65536) {
                        var33 = this.aByteArray3880[var15];
                        var17 = anIntArray3918[var33]++;
                        anIntArrayArray3938[var33][var17] = var15;
                        if(var33 < 10) {
                           anIntArray3944[var33] += var10;
                        } else if(var33 == 10) {
                           anIntArray3922[var17] = var10;
                        } else {
                           anIntArray3941[var17] = var10;
                        }
                     } else {
                        anIntArray3950[var9++] = (var15 >> 16) - 1;
                     }
                  }
               }
            }
         }

         var10 = 0;
         if(anIntArray3918[1] > 0 || anIntArray3918[2] > 0) {
            var10 = (anIntArray3944[1] + anIntArray3944[2]) / (anIntArray3918[1] + anIntArray3918[2]);
         }

         var11 = 0;
         if(anIntArray3918[3] > 0 || anIntArray3918[4] > 0) {
            var11 = (anIntArray3944[3] + anIntArray3944[4]) / (anIntArray3918[3] + anIntArray3918[4]);
         }

         var12 = 0;
         if(anIntArray3918[6] > 0 || anIntArray3918[8] > 0) {
            var12 = (anIntArray3944[6] + anIntArray3944[8]) / (anIntArray3918[6] + anIntArray3918[8]);
         }

         var14 = 0;
         var15 = anIntArray3918[10];
         int[] var34 = anIntArrayArray3938[10];
         int[] var35 = anIntArray3922;
         if(var14 == var15) {
            var15 = anIntArray3918[11];
            var34 = anIntArrayArray3938[11];
            var35 = anIntArray3941;
         }

         if(var14 < var15) {
            var13 = var35[var14];
         } else {
            var13 = -1000;
         }

         for(var18 = 0; var18 < 10; ++var18) {
            while(var18 == 0 && var13 > var10) {
               this.method1934(var34[var14++]);
               if(var14 == var15 && var34 != anIntArrayArray3938[11]) {
                  var14 = 0;
                  var15 = anIntArray3918[11];
                  var34 = anIntArrayArray3938[11];
                  var35 = anIntArray3941;
               }

               if(var14 < var15) {
                  var13 = var35[var14];
               } else {
                  var13 = -1000;
               }
            }

            while(var18 == 3 && var13 > var11) {
               this.method1934(var34[var14++]);
               if(var14 == var15 && var34 != anIntArrayArray3938[11]) {
                  var14 = 0;
                  var15 = anIntArray3918[11];
                  var34 = anIntArrayArray3938[11];
                  var35 = anIntArray3941;
               }

               if(var14 < var15) {
                  var13 = var35[var14];
               } else {
                  var13 = -1000;
               }
            }

            while(var18 == 5 && var13 > var12) {
               this.method1934(var34[var14++]);
               if(var14 == var15 && var34 != anIntArrayArray3938[11]) {
                  var14 = 0;
                  var15 = anIntArray3918[11];
                  var34 = anIntArrayArray3938[11];
                  var35 = anIntArray3941;
               }

               if(var14 < var15) {
                  var13 = var35[var14];
               } else {
                  var13 = -1000;
               }
            }

            var19 = anIntArray3918[var18];
            int[] var36 = anIntArrayArray3938[var18];

            for(var21 = 0; var21 < var19; ++var21) {
               this.method1934(var36[var21]);
            }
         }

         while(var13 != -1000) {
            this.method1934(var34[var14++]);
            if(var14 == var15 && var34 != anIntArrayArray3938[11]) {
               var14 = 0;
               var34 = anIntArrayArray3938[11];
               var15 = anIntArray3918[11];
               var35 = anIntArray3941;
            }

            if(var14 < var15) {
               var13 = var35[var14];
            } else {
               var13 = -1000;
            }
         }

      }
   }

   final void method1900() {
      for(int var1 = 0; var1 < this.anInt3891; ++var1) {
         int var2 = this.anIntArray3895[var1];
         this.anIntArray3895[var1] = this.anIntArray3885[var1];
         this.anIntArray3885[var1] = -var2;
      }

      this.aBoolean3897 = false;
   }

   final void method1946(int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      try {
         if(!this.aBoolean3897) {
            this.method1947();
         }

         int var9 = Class51.anInt846;
         int var10 = Class51.anInt835;
         int var13 = Class51.anIntArray840[var2];
         int var14 = Class51.anIntArray851[var2];
         int var15 = Class51.anIntArray840[var3];
         int var16 = Class51.anIntArray851[var3];
         int var17 = Class51.anIntArray840[var4];
         int var18 = Class51.anIntArray851[var4];
         int var19 = var6 * var17 + var7 * var18 >> 16;

         for(int var20 = 0; var20 < this.anInt3891; ++var20) {
            int var21 = this.anIntArray3885[var20];
            int var22 = this.anIntArray3883[var20];
            int var23 = this.anIntArray3895[var20];
            int var24;
            if(var3 != 0) {
               var24 = var22 * var15 + var21 * var16 >> 16;
               var22 = var22 * var16 - var21 * var15 >> 16;
               var21 = var24;
            }

             if(var2 != 0) {
               var24 = var23 * var13 + var21 * var14 >> 16;
               var23 = var23 * var14 - var21 * var13 >> 16;
               var21 = var24;
            }

            var21 += var5;
            var22 += var6;
            var23 += var7;
            var24 = var22 * var18 - var23 * var17 >> 16;
            var23 = var22 * var17 + var23 * var18 >> 16;
            anIntArray3920[var20] = var23 - var19;
            anIntArray3943[var20] = var9 + (var21 << 9) / var8;
            anIntArray3932[var20] = var10 + (var24 << 9) / var8;
            if(this.anInt3900 > 0) {
               anIntArray3948[var20] = var21;
               anIntArray3928[var20] = var24;
               anIntArray3921[var20] = var23;
            }
         }

         this.method1945(false, false, 0L, this.aShort3886, this.aShort3886 << 1);
      } catch (RuntimeException var25) {
      }

   }

   private void method1947() {
      int var1 = 32767;
      int var2 = 32767;
      int var3 = 32767;
      int var4 = -32768;
      int var5 = -32768;
      int var6 = -32768;
      int var7 = 0;
      int var8 = 0;

      for(int var9 = 0; var9 < this.anInt3891; ++var9) {
         int var10 = this.anIntArray3885[var9];
         int var11 = this.anIntArray3883[var9];
         int var12 = this.anIntArray3895[var9];
         if(var10 < var1) {
            var1 = var10;
         }

         if(var10 > var4) {
            var4 = var10;
         }

         if(var11 < var2) {
            var2 = var11;
         }

         if(var11 > var5) {
            var5 = var11;
         }

         if(var12 < var3) {
            var3 = var12;
         }

         if(var12 > var6) {
            var6 = var12;
         }

         int var13 = var10 * var10 + var12 * var12;
         if(var13 > var7) {
            var7 = var13;
         }

         var13 += var11 * var11;
         if(var13 > var8) {
            var8 = var13;
         }
      }

      this.aShort3873 = (short)var1;
      this.aShort3878 = (short)var4;
      this.aShort3894 = (short)var2;
      this.aShort3892 = (short)var5;
      this.aShort3879 = (short)var3;
      this.aShort3888 = (short)var6;
      this.aShort3884 = (short)((int)(Math.sqrt((double)var7) + 0.99D));
      this.aShort3886 = (short)((int)(Math.sqrt((double)var8) + 0.99D));
      this.aBoolean3897 = true;
   }

   public static void method1948() {
      aClass140_Sub1_Sub2_3902 = null;
      aByteArray3907 = null;
      aShortArray3910 = null;
      anIntArray3905 = null;
      anIntArray3909 = null;
      anIntArray3904 = null;
      aClass140_Sub1_Sub2_3906 = null;
      aByteArray3914 = null;
      aShortArray3912 = null;
      anIntArray3913 = null;
      anIntArray3916 = null;
      anIntArray3911 = null;
      aClass140_Sub1_Sub2_3915 = null;
      aByteArray3933 = null;
      aShortArray3949 = null;
      anIntArray3939 = null;
      anIntArray3947 = null;
      anIntArray3924 = null;
      aBooleanArray3937 = null;
      aBooleanArray3917 = null;
      anIntArray3943 = null;
      anIntArray3932 = null;
      anIntArray3920 = null;
      anIntArray3950 = null;
      anIntArray3948 = null;
      anIntArray3928 = null;
      anIntArray3921 = null;
      anIntArray3946 = null;
      anIntArrayArray3926 = (int[][])null;
      anIntArray3935 = null;
      anIntArrayArray3923 = (int[][])null;
      anIntArray3931 = null;
      anIntArray3929 = null;
      anIntArray3918 = null;
      anIntArrayArray3938 = (int[][])null;
      anIntArray3922 = null;
      anIntArray3941 = null;
      anIntArray3944 = null;
      anIntArray3919 = null;
      anIntArray3925 = null;
      anIntArray3936 = null;
   }

   final int method1884() {
      if(!this.aBoolean3897) {
         this.method1947();
      }

      return this.aShort3873;
   }

   final void method1899(int var1, int[] var2, int var3, int var4, int var5, boolean var6, int var7, int[] var8) {
      int var9 = var2.length;
      int var10;
      int var11;
      int var14;
      int var15;
      int var47;
      if(var1 == 0) {
         var10 = 0;
         anInt3945 = 0;
         anInt3934 = 0;
         anInt3940 = 0;

         for(var11 = 0; var11 < var9; ++var11) {
            var47 = var2[var11];
            if(var47 < this.anIntArrayArray3870.length) {
               int[] var48 = this.anIntArrayArray3870[var47];

               for(var14 = 0; var14 < var48.length; ++var14) {
                  var15 = var48[var14];
                  if(this.aShortArray3893 == null || (var7 & this.aShortArray3893[var15]) != 0) {
                     anInt3945 += this.anIntArray3885[var15];
                     anInt3934 += this.anIntArray3883[var15];
                     anInt3940 += this.anIntArray3895[var15];
                     ++var10;
                  }
               }
            }
         }

         if(var10 > 0) {
            anInt3945 = anInt3945 / var10 + var3;
            anInt3934 = anInt3934 / var10 + var4;
            anInt3940 = anInt3940 / var10 + var5;
            aBoolean3927 = true;
         } else {
            anInt3945 = var3;
            anInt3934 = var4;
            anInt3940 = var5;
         }

      } else {
         int[] var12;
         int var13;
         if(var1 == 1) {
            if(var8 != null) {
               var10 = var8[0] * var3 + var8[1] * var4 + var8[2] * var5 + 16384 >> 15;
               var11 = var8[3] * var3 + var8[4] * var4 + var8[5] * var5 + 16384 >> 15;
               var47 = var8[6] * var3 + var8[7] * var4 + var8[8] * var5 + 16384 >> 15;
               var3 = var10;
               var4 = var11;
               var5 = var47;
            }

            for(var10 = 0; var10 < var9; ++var10) {
               var11 = var2[var10];
               if(var11 < this.anIntArrayArray3870.length) {
                  var12 = this.anIntArrayArray3870[var11];

                  for(var13 = 0; var13 < var12.length; ++var13) {
                     var14 = var12[var13];
                     if(this.aShortArray3893 == null || (var7 & this.aShortArray3893[var14]) != 0) {
                        this.anIntArray3885[var14] += var3;
                        this.anIntArray3883[var14] += var4;
                        this.anIntArray3895[var14] += var5;
                     }
                  }
               }
            }

         } else {
            int var17;
            int var16;
            int var19;
            int var18;
            int var21;
            int var20;
            int var23;
            int var22;
            int var24;
            int var27;
            int var26;
            int var28;
            int var30;
            int var34;
            int[] var35;
            int var32;
            int var33;
            int var38;
            int var39;
            int var36;
            int var37;
            int var40;
            if(var1 == 2) {
               if(var8 == null) {
                  for(var10 = 0; var10 < var9; ++var10) {
                     var11 = var2[var10];
                     if(var11 < this.anIntArrayArray3870.length) {
                        var12 = this.anIntArrayArray3870[var11];

                        for(var13 = 0; var13 < var12.length; ++var13) {
                           var14 = var12[var13];
                           if(this.aShortArray3893 == null || (var7 & this.aShortArray3893[var14]) != 0) {
                              this.anIntArray3885[var14] -= anInt3945;
                              this.anIntArray3883[var14] -= anInt3934;
                              this.anIntArray3895[var14] -= anInt3940;
                              if(var5 != 0) {
                                 var15 = Class51.anIntArray840[var5];
                                 var16 = Class51.anIntArray851[var5];
                                 var17 = this.anIntArray3883[var14] * var15 + this.anIntArray3885[var14] * var16 + 32767 >> 16;
                                 this.anIntArray3883[var14] = this.anIntArray3883[var14] * var16 - this.anIntArray3885[var14] * var15 + 32767 >> 16;
                                 this.anIntArray3885[var14] = var17;
                              }

                              if(var3 != 0) {
                                 var15 = Class51.anIntArray840[var3];
                                 var16 = Class51.anIntArray851[var3];
                                 var17 = this.anIntArray3883[var14] * var16 - this.anIntArray3895[var14] * var15 + 32767 >> 16;
                                 this.anIntArray3895[var14] = this.anIntArray3883[var14] * var15 + this.anIntArray3895[var14] * var16 + 32767 >> 16;
                                 this.anIntArray3883[var14] = var17;
                              }

                              if(var4 != 0) {
                                 var15 = Class51.anIntArray840[var4];
                                 var16 = Class51.anIntArray851[var4];
                                 var17 = this.anIntArray3895[var14] * var15 + this.anIntArray3885[var14] * var16 + 32767 >> 16;
                                 this.anIntArray3895[var14] = this.anIntArray3895[var14] * var16 - this.anIntArray3885[var14] * var15 + 32767 >> 16;
                                 this.anIntArray3885[var14] = var17;
                              }

                              this.anIntArray3885[var14] += anInt3945;
                              this.anIntArray3883[var14] += anInt3934;
                              this.anIntArray3895[var14] += anInt3940;
                           }
                        }
                     }
                  }
               } else {
                  var10 = var8[9];
                  var11 = var8[10];
                  var47 = var8[11];
                  var13 = var8[12];
                  var14 = var8[13];
                  var15 = var8[14];
                  if(aBoolean3927) {
                     var16 = var8[0] * anInt3945 + var8[3] * anInt3934 + var8[6] * anInt3940 + 16384 >> 15;
                     var17 = var8[1] * anInt3945 + var8[4] * anInt3934 + var8[7] * anInt3940 + 16384 >> 15;
                     var18 = var8[2] * anInt3945 + var8[5] * anInt3934 + var8[8] * anInt3940 + 16384 >> 15;
                     var16 += var13;
                     var17 += var14;
                     var18 += var15;
                     anInt3945 = var16;
                     anInt3934 = var17;
                     anInt3940 = var18;
                     aBoolean3927 = false;
                  }

                  int[] var49 = new int[9];
                  var17 = Class51.anIntArray851[var3] >> 1;
                  var18 = Class51.anIntArray840[var3] >> 1;
                  var19 = Class51.anIntArray851[var4] >> 1;
                  var20 = Class51.anIntArray840[var4] >> 1;
                  var21 = Class51.anIntArray851[var5] >> 1;
                  var22 = Class51.anIntArray840[var5] >> 1;
                  var23 = var18 * var21 + 16384 >> 15;
                  var24 = var18 * var22 + 16384 >> 15;
                  var49[0] = var19 * var21 + var20 * var24 + 16384 >> 15;
                  var49[1] = -var19 * var22 + var20 * var23 + 16384 >> 15;
                  var49[2] = var20 * var17 + 16384 >> 15;
                  var49[3] = var17 * var22 + 16384 >> 15;
                  var49[4] = var17 * var21 + 16384 >> 15;
                  var49[5] = -var18;
                  var49[6] = -var20 * var21 + var19 * var24 + 16384 >> 15;
                  var49[7] = var20 * var22 + var19 * var23 + 16384 >> 15;
                  var49[8] = var19 * var17 + 16384 >> 15;
                  int var50 = var49[0] * -anInt3945 + var49[1] * -anInt3934 + var49[2] * -anInt3940 + 16384 >> 15;
                  var26 = var49[3] * -anInt3945 + var49[4] * -anInt3934 + var49[5] * -anInt3940 + 16384 >> 15;
                  var27 = var49[6] * -anInt3945 + var49[7] * -anInt3934 + var49[8] * -anInt3940 + 16384 >> 15;
                  var28 = var50 + anInt3945;
                  int var51 = var26 + anInt3934;
                  var30 = var27 + anInt3940;
                  int[] var52 = new int[9];

                  for(var32 = 0; var32 < 3; ++var32) {
                     for(var33 = 0; var33 < 3; ++var33) {
                        var34 = 0;

                        for(int var53 = 0; var53 < 3; ++var53) {
                           var34 += var49[var32 * 3 + var53] * var8[var33 * 3 + var53];
                        }

                        var52[var32 * 3 + var33] = var34 + 16384 >> 15;
                     }
                  }

                  var32 = var49[0] * var13 + var49[1] * var14 + var49[2] * var15 + 16384 >> 15;
                  var33 = var49[3] * var13 + var49[4] * var14 + var49[5] * var15 + 16384 >> 15;
                  var34 = var49[6] * var13 + var49[7] * var14 + var49[8] * var15 + 16384 >> 15;
                  var32 += var28;
                  var33 += var51;
                  var34 += var30;
                  var35 = new int[9];

                  for(var36 = 0; var36 < 3; ++var36) {
                     for(var37 = 0; var37 < 3; ++var37) {
                        var38 = 0;

                        for(var39 = 0; var39 < 3; ++var39) {
                           var38 += var8[var36 * 3 + var39] * var52[var37 + var39 * 3];
                        }

                        var35[var36 * 3 + var37] = var38 + 16384 >> 15;
                     }
                  }

                  var36 = var8[0] * var32 + var8[1] * var33 + var8[2] * var34 + 16384 >> 15;
                  var37 = var8[3] * var32 + var8[4] * var33 + var8[5] * var34 + 16384 >> 15;
                  var38 = var8[6] * var32 + var8[7] * var33 + var8[8] * var34 + 16384 >> 15;
                  var36 += var10;
                  var37 += var11;
                  var38 += var47;

                  for(var39 = 0; var39 < var9; ++var39) {
                     var40 = var2[var39];
                     if(var40 < this.anIntArrayArray3870.length) {
                        int[] var41 = this.anIntArrayArray3870[var40];

                        for(int var42 = 0; var42 < var41.length; ++var42) {
                           int var43 = var41[var42];
                           if(this.aShortArray3893 == null || (var7 & this.aShortArray3893[var43]) != 0) {
                              int var44 = var35[0] * this.anIntArray3885[var43] + var35[1] * this.anIntArray3883[var43] + var35[2] * this.anIntArray3895[var43] + 16384 >> 15;
                              int var45 = var35[3] * this.anIntArray3885[var43] + var35[4] * this.anIntArray3883[var43] + var35[5] * this.anIntArray3895[var43] + 16384 >> 15;
                              int var46 = var35[6] * this.anIntArray3885[var43] + var35[7] * this.anIntArray3883[var43] + var35[8] * this.anIntArray3895[var43] + 16384 >> 15;
                              var44 += var36;
                              var45 += var37;
                              var46 += var38;
                              this.anIntArray3885[var43] = var44;
                              this.anIntArray3883[var43] = var45;
                              this.anIntArray3895[var43] = var46;
                           }
                        }
                     }
                  }
               }

            } else if(var1 == 3) {
               if(var8 == null) {
                  for(var10 = 0; var10 < var9; ++var10) {
                     var11 = var2[var10];
                     if(var11 < this.anIntArrayArray3870.length) {
                        var12 = this.anIntArrayArray3870[var11];

                        for(var13 = 0; var13 < var12.length; ++var13) {
                           var14 = var12[var13];
                           if(this.aShortArray3893 == null || (var7 & this.aShortArray3893[var14]) != 0) {
                              this.anIntArray3885[var14] -= anInt3945;
                              this.anIntArray3883[var14] -= anInt3934;
                              this.anIntArray3895[var14] -= anInt3940;
                              this.anIntArray3885[var14] = this.anIntArray3885[var14] * var3 / 128;
                              this.anIntArray3883[var14] = this.anIntArray3883[var14] * var4 / 128;
                              this.anIntArray3895[var14] = this.anIntArray3895[var14] * var5 / 128;
                              this.anIntArray3885[var14] += anInt3945;
                              this.anIntArray3883[var14] += anInt3934;
                              this.anIntArray3895[var14] += anInt3940;
                           }
                        }
                     }
                  }
               } else {
                  var10 = var8[9];
                  var11 = var8[10];
                  var47 = var8[11];
                  var13 = var8[12];
                  var14 = var8[13];
                  var15 = var8[14];
                  if(aBoolean3927) {
                     var16 = var8[0] * anInt3945 + var8[3] * anInt3934 + var8[6] * anInt3940 + 16384 >> 15;
                     var17 = var8[1] * anInt3945 + var8[4] * anInt3934 + var8[7] * anInt3940 + 16384 >> 15;
                     var18 = var8[2] * anInt3945 + var8[5] * anInt3934 + var8[8] * anInt3940 + 16384 >> 15;
                     var16 += var13;
                     var17 += var14;
                     var18 += var15;
                     anInt3945 = var16;
                     anInt3934 = var17;
                     anInt3940 = var18;
                     aBoolean3927 = false;
                  }

                  var16 = var3 << 15 >> 7;
                  var17 = var4 << 15 >> 7;
                  var18 = var5 << 15 >> 7;
                  var19 = var16 * -anInt3945 + 16384 >> 15;
                  var20 = var17 * -anInt3934 + 16384 >> 15;
                  var21 = var18 * -anInt3940 + 16384 >> 15;
                  var22 = var19 + anInt3945;
                  var23 = var20 + anInt3934;
                  var24 = var21 + anInt3940;
                  int[] var25 = new int[]{var16 * var8[0] + 16384 >> 15, var16 * var8[3] + 16384 >> 15, var16 * var8[6] + 16384 >> 15, var17 * var8[1] + 16384 >> 15, var17 * var8[4] + 16384 >> 15, var17 * var8[7] + 16384 >> 15, var18 * var8[2] + 16384 >> 15, var18 * var8[5] + 16384 >> 15, var18 * var8[8] + 16384 >> 15};
                  var26 = var16 * var13 + 16384 >> 15;
                  var27 = var17 * var14 + 16384 >> 15;
                  var28 = var18 * var15 + 16384 >> 15;
                  var26 += var22;
                  var27 += var23;
                  var28 += var24;
                  int[] var29 = new int[9];

                  int var31;
                  for(var30 = 0; var30 < 3; ++var30) {
                     for(var31 = 0; var31 < 3; ++var31) {
                        var32 = 0;

                        for(var33 = 0; var33 < 3; ++var33) {
                           var32 += var8[var30 * 3 + var33] * var25[var31 + var33 * 3];
                        }

                        var29[var30 * 3 + var31] = var32 + 16384 >> 15;
                     }
                  }

                  var30 = var8[0] * var26 + var8[1] * var27 + var8[2] * var28 + 16384 >> 15;
                  var31 = var8[3] * var26 + var8[4] * var27 + var8[5] * var28 + 16384 >> 15;
                  var32 = var8[6] * var26 + var8[7] * var27 + var8[8] * var28 + 16384 >> 15;
                  var30 += var10;
                  var31 += var11;
                  var32 += var47;

                  for(var33 = 0; var33 < var9; ++var33) {
                     var34 = var2[var33];
                     if(var34 < this.anIntArrayArray3870.length) {
                        var35 = this.anIntArrayArray3870[var34];

                        for(var36 = 0; var36 < var35.length; ++var36) {
                           var37 = var35[var36];
                           if(this.aShortArray3893 == null || (var7 & this.aShortArray3893[var37]) != 0) {
                              var38 = var29[0] * this.anIntArray3885[var37] + var29[1] * this.anIntArray3883[var37] + var29[2] * this.anIntArray3895[var37] + 16384 >> 15;
                              var39 = var29[3] * this.anIntArray3885[var37] + var29[4] * this.anIntArray3883[var37] + var29[5] * this.anIntArray3895[var37] + 16384 >> 15;
                              var40 = var29[6] * this.anIntArray3885[var37] + var29[7] * this.anIntArray3883[var37] + var29[8] * this.anIntArray3895[var37] + 16384 >> 15;
                              var38 += var30;
                              var39 += var31;
                              var40 += var32;
                              this.anIntArray3885[var37] = var38;
                              this.anIntArray3883[var37] = var39;
                              this.anIntArray3895[var37] = var40;
                           }
                        }
                     }
                  }
               }

            } else if(var1 == 5) {
               if(this.anIntArrayArray3872 != null && this.aByteArray3903 != null) {
                  for(var10 = 0; var10 < var9; ++var10) {
                     var11 = var2[var10];
                     if(var11 < this.anIntArrayArray3872.length) {
                        var12 = this.anIntArrayArray3872[var11];

                        for(var13 = 0; var13 < var12.length; ++var13) {
                           var14 = var12[var13];
                           if(this.aShortArray3871 == null || (var7 & this.aShortArray3871[var14]) != 0) {
                              var15 = (this.aByteArray3903[var14] & 255) + var3 * 8;
                              if(var15 < 0) {
                                 var15 = 0;
                              } else if(var15 > 255) {
                                 var15 = 255;
                              }

                              this.aByteArray3903[var14] = (byte)var15;
                           }
                        }
                     }
                  }
               }

            } else if(var1 == 7) {
               if(this.anIntArrayArray3872 != null) {
                  for(var10 = 0; var10 < var9; ++var10) {
                     var11 = var2[var10];
                     if(var11 < this.anIntArrayArray3872.length) {
                        var12 = this.anIntArrayArray3872[var11];

                        for(var13 = 0; var13 < var12.length; ++var13) {
                           var14 = var12[var13];
                           if(this.aShortArray3871 == null || (var7 & this.aShortArray3871[var14]) != 0) {
                              var15 = this.aShortArray3869[var14] & '\uffff';
                              var16 = var15 >> 10 & 63;
                              var17 = var15 >> 7 & 7;
                              var18 = var15 & 127;
                              var16 = var16 + var3 & 63;
                              var17 += var4;
                              if(var17 < 0) {
                                 var17 = 0;
                              } else if(var17 > 7) {
                                 var17 = 7;
                              }

                              var18 += var5;
                              if(var18 < 0) {
                                 var18 = 0;
                              } else if(var18 > 127) {
                                 var18 = 127;
                              }

                              this.aShortArray3869[var14] = (short)(var16 << 10 | var17 << 7 | var18);
                           }
                        }

                        this.aBoolean3877 = true;
                     }
                  }
               }

            }
         }
      }
   }

   final void method1889(int var1, int var2, int var3, int var4) {
      int var5;
      int var6;
      if(var1 == 0) {
         var5 = 0;
         anInt3945 = 0;
         anInt3934 = 0;
         anInt3940 = 0;

         for(var6 = 0; var6 < this.anInt3891; ++var6) {
            anInt3945 += this.anIntArray3885[var6];
            anInt3934 += this.anIntArray3883[var6];
            anInt3940 += this.anIntArray3895[var6];
            ++var5;
         }

         if(var5 > 0) {
            anInt3945 = anInt3945 / var5 + var2;
            anInt3934 = anInt3934 / var5 + var3;
            anInt3940 = anInt3940 / var5 + var4;
         } else {
            anInt3945 = var2;
            anInt3934 = var3;
            anInt3940 = var4;
         }

      } else if(var1 == 1) {
         for(var5 = 0; var5 < this.anInt3891; ++var5) {
            this.anIntArray3885[var5] += var2;
            this.anIntArray3883[var5] += var3;
            this.anIntArray3895[var5] += var4;
         }

      } else {
         int var7;
         int var8;
         if(var1 == 2) {
            for(var5 = 0; var5 < this.anInt3891; ++var5) {
               this.anIntArray3885[var5] -= anInt3945;
               this.anIntArray3883[var5] -= anInt3934;
               this.anIntArray3895[var5] -= anInt3940;
               if(var4 != 0) {
                  var6 = Class51.anIntArray840[var4];
                  var7 = Class51.anIntArray851[var4];
                  var8 = this.anIntArray3883[var5] * var6 + this.anIntArray3885[var5] * var7 + 32767 >> 16;
                  this.anIntArray3883[var5] = this.anIntArray3883[var5] * var7 - this.anIntArray3885[var5] * var6 + 32767 >> 16;
                  this.anIntArray3885[var5] = var8;
               }

               if(var2 != 0) {
                  var6 = Class51.anIntArray840[var2];
                  var7 = Class51.anIntArray851[var2];
                  var8 = this.anIntArray3883[var5] * var7 - this.anIntArray3895[var5] * var6 + 32767 >> 16;
                  this.anIntArray3895[var5] = this.anIntArray3883[var5] * var6 + this.anIntArray3895[var5] * var7 + 32767 >> 16;
                  this.anIntArray3883[var5] = var8;
               }

               if(var3 != 0) {
                  var6 = Class51.anIntArray840[var3];
                  var7 = Class51.anIntArray851[var3];
                  var8 = this.anIntArray3895[var5] * var6 + this.anIntArray3885[var5] * var7 + 32767 >> 16;
                  this.anIntArray3895[var5] = this.anIntArray3895[var5] * var7 - this.anIntArray3885[var5] * var6 + 32767 >> 16;
                  this.anIntArray3885[var5] = var8;
               }

               this.anIntArray3885[var5] += anInt3945;
               this.anIntArray3883[var5] += anInt3934;
               this.anIntArray3895[var5] += anInt3940;
            }

         } else if(var1 == 3) {
            for(var5 = 0; var5 < this.anInt3891; ++var5) {
               this.anIntArray3885[var5] -= anInt3945;
               this.anIntArray3883[var5] -= anInt3934;
               this.anIntArray3895[var5] -= anInt3940;
               this.anIntArray3885[var5] = this.anIntArray3885[var5] * var2 / 128;
               this.anIntArray3883[var5] = this.anIntArray3883[var5] * var3 / 128;
               this.anIntArray3895[var5] = this.anIntArray3895[var5] * var4 / 128;
               this.anIntArray3885[var5] += anInt3945;
               this.anIntArray3883[var5] += anInt3934;
               this.anIntArray3895[var5] += anInt3940;
            }

         } else if (var1 == 5) {
            for (var5 = 0; var5 < this.anInt3889; ++var5) {
               var6 = (this.aByteArray3903[var5] & 255) + var2 * 8;
               if (var6 < 0) {
                  var6 = 0;
               } else if (var6 > 255) {
                  var6 = 255;
               }

               this.aByteArray3903[var5] = (byte) var6;
            }

         } else if (var1 == 7) {
            for (var5 = 0; var5 < this.anInt3889; ++var5) {
               var6 = this.aShortArray3869[var5] & '\uffff';
               var7 = var6 >> 10 & 63;
               var8 = var6 >> 7 & 7;
               int var9 = var6 & 127;
               var7 = var7 + var2 & 63;
               var8 += var3;
               if (var8 < 0) {
                  var8 = 0;
               } else if (var8 > 7) {
                  var8 = 7;
               }

               var9 += var4;
               if (var9 < 0) {
                  var9 = 0;
               } else if (var9 > 127) {
                  var9 = 127;
               }

               this.aShortArray3869[var5] = (short) (var7 << 10 | var8 << 7 | var9);
            }

            this.aBoolean3877 = true;
         }
      }
   }

   final void method1897(int var1, int var2, int var3) {
      for(int var4 = 0; var4 < this.anInt3891; ++var4) {
         this.anIntArray3885[var4] += var1;
         this.anIntArray3883[var4] += var2;
         this.anIntArray3895[var4] += var3;
      }

      this.aBoolean3897 = false;
   }

   final Model method1890(boolean var1, boolean var2, boolean var3) {
      if(!var1 && aByteArray3933.length < this.anInt3889) {
         aByteArray3933 = new byte[this.anInt3889 + 100];
      }

      if(!var2 && aShortArray3949.length < this.anInt3889) {
         anIntArray3939 = new int[this.anInt3889 + 100];
         anIntArray3947 = new int[this.anInt3889 + 100];
         anIntArray3924 = new int[this.anInt3889 + 100];
         aShortArray3949 = new short[this.anInt3889 + 100];
      }

      return this.method1939(var1, var2, aClass140_Sub1_Sub2_3915, aByteArray3933, aShortArray3949, anIntArray3939, anIntArray3947, anIntArray3924);
   }

   public Class140_Sub1_Sub2() {}

   Class140_Sub1_Sub2(Model_Sub1 var1, int var2, int var3, int var4, int var5, int var6) {
      var1.method1997();
      var1.method2012();
      this.anInt3891 = var1.anInt2887;
      this.anIntArray3885 = var1.anIntArray2885;
      this.anIntArray3883 = var1.anIntArray2881;
      this.anIntArray3895 = var1.anIntArray2892;
      this.anInt3889 = var1.anInt2849;
      this.anIntArray3901 = var1.anIntArray2865;
      this.anIntArray3876 = var1.anIntArray2878;
      this.anIntArray3887 = var1.anIntArray2864;
      this.aByteArray3880 = var1.aByteArray2889;
      this.aByteArray3903 = var1.aByteArray2843;
      this.aByte3875 = var1.aByte2848;
      this.aShortArray3869 = var1.aShortArray2870;
      this.anIntArrayArray3870 = var1.anIntArrayArray2890;
      this.anIntArrayArray3872 = var1.anIntArrayArray2856;
      this.aShortArray3871 = var1.aShortArray2855;
      this.aShortArray3893 = var1.aShortArray2893;
      int var7 = (int)Math.sqrt((double)(var4 * var4 + var5 * var5 + var6 * var6));
      int var8 = var3 * var7 >> 8;
      this.anIntArray3898 = new int[this.anInt3889];
      this.anIntArray3874 = new int[this.anInt3889];
      this.anIntArray3896 = new int[this.anInt3889];
      int var9;
      if(var1.aShortArray2858 == null) {
         this.aShortArray3908 = null;
      } else {
         this.aShortArray3908 = new short[this.anInt3889];

         for(var9 = 0; var9 < this.anInt3889; ++var9) {
            short var10 = var1.aShortArray2858[var9];
            if(var10 != -1 && Class51.anInterface2_838.method17(var10, 77)) {
               this.aShortArray3908[var9] = var10;
            } else {
               this.aShortArray3908[var9] = -1;
            }
         }
      }

       if(var1.anInt2862 > 0 && var1.aByteArray2866 != null) {
         int[] var16 = new int[var1.anInt2862];

         int var17;
         for(var17 = 0; var17 < this.anInt3889; ++var17) {
            if(var1.aByteArray2866[var17] != -1) {
               ++var16[var1.aByteArray2866[var17] & 255];
            }
         }

         this.anInt3900 = 0;

         for(var17 = 0; var17 < var1.anInt2862; ++var17) {
            if(var16[var17] > 0 && var1.aByteArray2857[var17] == 0) {
               ++this.anInt3900;
            }
         }

         this.anIntArray3882 = new int[this.anInt3900];
         this.anIntArray3890 = new int[this.anInt3900];
         this.anIntArray3881 = new int[this.anInt3900];
         var17 = 0;

         int var11;
         for(var11 = 0; var11 < var1.anInt2862; ++var11) {
            if(var16[var11] > 0 && var1.aByteArray2857[var11] == 0) {
               this.anIntArray3882[var17] = var1.aShortArray2884[var11] & '\uffff';
               this.anIntArray3890[var17] = var1.aShortArray2846[var11] & '\uffff';
               this.anIntArray3881[var17] = var1.aShortArray2891[var11] & '\uffff';
               var16[var11] = var17++;
            } else {
               var16[var11] = -1;
            }
         }

         this.aByteArray3899 = new byte[this.anInt3889];

         for(var11 = 0; var11 < this.anInt3889; ++var11) {
            if(var1.aByteArray2866[var11] == -1) {
               this.aByteArray3899[var11] = -1;
            } else {
               this.aByteArray3899[var11] = (byte)var16[var1.aByteArray2866[var11] & 255];
               if(this.aByteArray3899[var11] == -1 && this.aShortArray3908 != null) {
                  this.aShortArray3908[var11] = -1;
               }
            }
         }
      }

      for(var9 = 0; var9 < this.anInt3889; ++var9) {
         byte var18;
         if(var1.aByteArray2859 == null) {
            var18 = 0;
         } else {
            var18 = var1.aByteArray2859[var9];
         }

         byte var19;
         if(var1.aByteArray2843 == null) {
            var19 = 0;
         } else {
            var19 = var1.aByteArray2843[var9];
         }

         short var12;
         if(this.aShortArray3908 == null) {
            var12 = -1;
         } else {
            var12 = this.aShortArray3908[var9];
         }

         if(var19 == -2) {
            var18 = 3;
         }

         if(var19 == -1) {
            var18 = 2;
         }

         Class50 var13;
         int var14;
         Class120 var20;
         if(var12 == -1) {
            if(var18 == 0) {
               int var15 = var1.aShortArray2870[var9] & '\uffff';
               if(var1.aClass50Array2883 == null || var1.aClass50Array2883[this.anIntArray3901[var9]] == null) {
                  var13 = var1.aClass50Array2872[this.anIntArray3901[var9]];
               } else {
                  var13 = var1.aClass50Array2883[this.anIntArray3901[var9]];
               }

                var14 = var2 + (var4 * var13.anInt831 + var5 * var13.anInt821 + var6 * var13.anInt830) / (var8 * var13.anInt823) << 17;
               this.anIntArray3898[var9] = var14 | method1940(var15, var14 >> 17);
               if(var1.aClass50Array2883 == null || var1.aClass50Array2883[this.anIntArray3876[var9]] == null) {
                  var13 = var1.aClass50Array2872[this.anIntArray3876[var9]];
               } else {
                  var13 = var1.aClass50Array2883[this.anIntArray3876[var9]];
               }

                var14 = var2 + (var4 * var13.anInt831 + var5 * var13.anInt821 + var6 * var13.anInt830) / (var8 * var13.anInt823) << 17;
               this.anIntArray3874[var9] = var14 | method1940(var15, var14 >> 17);
               if(var1.aClass50Array2883 == null || var1.aClass50Array2883[this.anIntArray3887[var9]] == null) {
                  var13 = var1.aClass50Array2872[this.anIntArray3887[var9]];
               } else {
                  var13 = var1.aClass50Array2883[this.anIntArray3887[var9]];
               }

                var14 = var2 + (var4 * var13.anInt831 + var5 * var13.anInt821 + var6 * var13.anInt830) / (var8 * var13.anInt823) << 17;
               this.anIntArray3896[var9] = var14 | method1940(var15, var14 >> 17);
            } else if(var18 == 1) {
               var20 = var1.aClass120Array2886[var9];
               var14 = var2 + (var4 * var20.anInt1634 + var5 * var20.anInt1635 + var6 * var20.anInt1632) / (var8 + var8 / 2) << 17;
               this.anIntArray3898[var9] = var14 | method1940(var1.aShortArray2870[var9] & '\uffff', var14 >> 17);
               this.anIntArray3896[var9] = -1;
            } else if(var18 == 3) {
               this.anIntArray3898[var9] = 128;
               this.anIntArray3896[var9] = -1;
            } else {
               this.anIntArray3896[var9] = -2;
            }
         } else if(var18 == 0) {
            if(var1.aClass50Array2883 == null || var1.aClass50Array2883[this.anIntArray3901[var9]] == null) {
               var13 = var1.aClass50Array2872[this.anIntArray3901[var9]];
            } else {
               var13 = var1.aClass50Array2883[this.anIntArray3901[var9]];
            }

             var14 = var2 + (var4 * var13.anInt831 + var5 * var13.anInt821 + var6 * var13.anInt830) / (var8 * var13.anInt823);
            this.anIntArray3898[var9] = method1937(var14);
            if(var1.aClass50Array2883 == null || var1.aClass50Array2883[this.anIntArray3876[var9]] == null) {
               var13 = var1.aClass50Array2872[this.anIntArray3876[var9]];
            } else {
               var13 = var1.aClass50Array2883[this.anIntArray3876[var9]];
            }

             var14 = var2 + (var4 * var13.anInt831 + var5 * var13.anInt821 + var6 * var13.anInt830) / (var8 * var13.anInt823);
            this.anIntArray3874[var9] = method1937(var14);
            if(var1.aClass50Array2883 == null || var1.aClass50Array2883[this.anIntArray3887[var9]] == null) {
               var13 = var1.aClass50Array2872[this.anIntArray3887[var9]];
            } else {
               var13 = var1.aClass50Array2883[this.anIntArray3887[var9]];
            }

             var14 = var2 + (var4 * var13.anInt831 + var5 * var13.anInt821 + var6 * var13.anInt830) / (var8 * var13.anInt823);
            this.anIntArray3896[var9] = method1937(var14);
         } else if(var18 == 1) {
            var20 = var1.aClass120Array2886[var9];
            var14 = var2 + (var4 * var20.anInt1634 + var5 * var20.anInt1635 + var6 * var20.anInt1632) / (var8 + var8 / 2);
            this.anIntArray3898[var9] = method1937(var14);
            this.anIntArray3896[var9] = -1;
         } else {
            this.anIntArray3896[var9] = -2;
         }
      }

   }

   private Class140_Sub1_Sub2(Class140_Sub1_Sub2[] var1) {
      boolean var3 = false;
      boolean var4 = false;
      boolean var5 = false;
      boolean var6 = false;
      this.anInt3891 = 0;
      this.anInt3889 = 0;
      this.anInt3900 = 0;
      this.aByte3875 = -1;

      int var9;
      Class140_Sub1_Sub2 var10;
      for(var9 = 0; var9 < 2; ++var9) {
         var10 = var1[var9];
         if(var10 != null) {
            this.anInt3891 += var10.anInt3891;
            this.anInt3889 += var10.anInt3889;
            this.anInt3900 += var10.anInt3900;
            if(var10.aByteArray3880 == null) {
               if(this.aByte3875 == -1) {
                  this.aByte3875 = var10.aByte3875;
               }

               if(this.aByte3875 != var10.aByte3875) {
                  var3 = true;
               }
            } else {
               var3 = true;
            }

             var4 |= var10.aByteArray3903 != null;
            var5 |= var10.aShortArray3908 != null;
            var6 |= var10.aByteArray3899 != null;
         }
      }

      this.anIntArray3885 = new int[this.anInt3891];
      this.anIntArray3883 = new int[this.anInt3891];
      this.anIntArray3895 = new int[this.anInt3891];
      this.anIntArray3901 = new int[this.anInt3889];
      this.anIntArray3876 = new int[this.anInt3889];
      this.anIntArray3887 = new int[this.anInt3889];
      this.anIntArray3898 = new int[this.anInt3889];
      this.anIntArray3874 = new int[this.anInt3889];
      this.anIntArray3896 = new int[this.anInt3889];
      if(var3) {
         this.aByteArray3880 = new byte[this.anInt3889];
      }

      if(var4) {
         this.aByteArray3903 = new byte[this.anInt3889];
      }

      if(var5) {
         this.aShortArray3908 = new short[this.anInt3889];
      }

      if(var6) {
         this.aByteArray3899 = new byte[this.anInt3889];
      }

      if(this.anInt3900 > 0) {
         this.anIntArray3882 = new int[this.anInt3900];
         this.anIntArray3890 = new int[this.anInt3900];
         this.anIntArray3881 = new int[this.anInt3900];
      }

       this.aShortArray3869 = new short[this.anInt3889];
      this.anInt3891 = 0;
      this.anInt3889 = 0;
      this.anInt3900 = 0;

      for(var9 = 0; var9 < 2; ++var9) {
         var10 = var1[var9];
         if(var10 != null) {
            int var11;
            for(var11 = 0; var11 < var10.anInt3889; ++var11) {
               this.anIntArray3901[this.anInt3889] = var10.anIntArray3901[var11] + this.anInt3891;
               this.anIntArray3876[this.anInt3889] = var10.anIntArray3876[var11] + this.anInt3891;
               this.anIntArray3887[this.anInt3889] = var10.anIntArray3887[var11] + this.anInt3891;
               this.anIntArray3898[this.anInt3889] = var10.anIntArray3898[var11];
               this.anIntArray3874[this.anInt3889] = var10.anIntArray3874[var11];
               this.anIntArray3896[this.anInt3889] = var10.anIntArray3896[var11];
               this.aShortArray3869[this.anInt3889] = var10.aShortArray3869[var11];
               if(var3) {
                  if(var10.aByteArray3880 == null) {
                     this.aByteArray3880[this.anInt3889] = var10.aByte3875;
                  } else {
                     this.aByteArray3880[this.anInt3889] = var10.aByteArray3880[var11];
                  }
               }

               if(var4 && var10.aByteArray3903 != null) {
                  this.aByteArray3903[this.anInt3889] = var10.aByteArray3903[var11];
               }

               if(var5) {
                  if(var10.aShortArray3908 == null) {
                     this.aShortArray3908[this.anInt3889] = -1;
                  } else {
                     this.aShortArray3908[this.anInt3889] = var10.aShortArray3908[var11];
                  }
               }

               if(var6) {
                  if(var10.aByteArray3899 == null || var10.aByteArray3899[var11] == -1) {
                     this.aByteArray3899[this.anInt3889] = -1;
                  } else {
                     this.aByteArray3899[this.anInt3889] = (byte)(var10.aByteArray3899[var11] + this.anInt3900);
                  }
               }

               ++this.anInt3889;
            }

            for(var11 = 0; var11 < var10.anInt3900; ++var11) {
               this.anIntArray3882[this.anInt3900] = var10.anIntArray3882[var11] + this.anInt3891;
               this.anIntArray3890[this.anInt3900] = var10.anIntArray3890[var11] + this.anInt3891;
               this.anIntArray3881[this.anInt3900] = var10.anIntArray3881[var11] + this.anInt3891;
               ++this.anInt3900;
            }

            for(var11 = 0; var11 < var10.anInt3891; ++var11) {
               this.anIntArray3885[this.anInt3891] = var10.anIntArray3885[var11];
               this.anIntArray3883[this.anInt3891] = var10.anIntArray3883[var11];
               this.anIntArray3895[this.anInt3891] = var10.anIntArray3895[var11];
               ++this.anInt3891;
            }
         }
      }

   }

   static {
       anIntArray3946 = new int[1600];
       anIntArrayArray3926 = new int[1600][64];
       anIntArray3935 = new int[32];
       anIntArrayArray3923 = new int[32][512];

       anIntArray3924 = new int[1];
      anIntArray3939 = new int[1];
      anIntArray3928 = new int[4096];
      aShortArray3949 = new short[1];
      anIntArray3943 = new int[4096];
      anIntArrayArray3938 = new int[12][4096];
      anIntArray3921 = new int[4096];
      anIntArray3950 = new int[8192];
   }
}
