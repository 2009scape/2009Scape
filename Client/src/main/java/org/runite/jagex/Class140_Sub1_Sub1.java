package org.runite.jagex;

import javax.media.opengl.GL;
import java.nio.ByteBuffer;
import java.util.Objects;

final class Class140_Sub1_Sub1 extends Model {

   private short[] aShortArray3808;
   boolean aBoolean3809 = false;
   private short[] aShortArray3810;
   private short[] aShortArray3811;
   private Class156 aClass156_3812;
   private short[] aShortArray3813;
   private Class121 aClass121_3814;
   private Class121 aClass121_3815;
   private byte[] aByteArray3816;
   private Class121 aClass121_3817;
   private static RSByteBuffer aClass3_Sub30_3818 = new RSByteBuffer(10000);
   private short aShort3819;
   private byte[] aByteArray3820;
   private static long[] aLongArray3821;
   int[] anIntArray3822;
   int anInt3823 = 0;
   private float[] aFloatArray3824;
   private int[][] anIntArrayArray3825;
   private short[] aShortArray3826;
   private short[] aShortArray3827;
   private short[] aShortArray3828;
   private int[][] anIntArrayArray3829;
   private short[] aShortArray3830;
   private short[] aShortArray3831;
   private short[] aShortArray3832;
   private int anInt3833 = 0;
   private static ByteBuffer aByteBuffer3834;
   Class6 aClass6_3835;
   private byte aByte3836 = 0;
   private short[] aShortArray3837;
   private int[] anIntArray3838;
   Class121 aClass121_3839;
   private int[] anIntArray3840;
   private short[] aShortArray3841;
   private static Class140_Sub1_Sub1 aClass140_Sub1_Sub1_3842 = new Class140_Sub1_Sub1();
   private Class18 aClass18_3843;
   private int[] anIntArray3844;
   int[] anIntArray3845;
   private Class121 aClass121_3846;
   private float[] aFloatArray3847;
   int[] anIntArray3848;
   private short aShort3849;
   private static Class140_Sub1_Sub1 aClass140_Sub1_Sub1_3850 = new Class140_Sub1_Sub1();
   private byte aByte3851 = 0;
   private int anInt3852 = 0;
   private static Class140_Sub1_Sub1 aClass140_Sub1_Sub1_3853 = new Class140_Sub1_Sub1();
   private static Class140_Sub1_Sub1 aClass140_Sub1_Sub1_3854 = new Class140_Sub1_Sub1();
   private static Class140_Sub1_Sub1 aClass140_Sub1_Sub1_3855 = new Class140_Sub1_Sub1();
   private static Class140_Sub1_Sub1 aClass140_Sub1_Sub1_3856 = new Class140_Sub1_Sub1();
   private static int[] anIntArray3857 = new int[1];
   private static float aFloat3858;
   private static int anInt3859;
   private static float aFloat3860;
   private static int[] anIntArray3861 = new int[1];
   private static int anInt3862;
   private static float aFloat3863;
   private static float aFloat3864;
   private static int anInt3865;
   private static float aFloat3866;
   private static float aFloat3867;
   private static boolean aBoolean3868 = false;


   private static int method1901(float var0, float var1, float var2) {
      float var3 = var0 < 0.0F?-var0:var0;
      float var4 = var1 < 0.0F?-var1:var1;
      float var5 = var2 < 0.0F?-var2:var2;
      return var4 > var3 && var4 > var5?(var1 > 0.0F?0:1):(var5 > var3 && var5 > var4?(var2 > 0.0F?2:3):(var0 > 0.0F?4:5));
   }

   final Model method1882(boolean var1, boolean var2, boolean var3) {
      return this.method1923(var1, var2, var3, aClass140_Sub1_Sub1_3854, aClass140_Sub1_Sub1_3853);
   }

   final void animate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, long var9, int var11, Class127_Sub1 var12) {
      if(this.anInt3833 != 0) {
         if(!this.aClass6_3835.aBoolean98) {
            this.method1917();
         }

         short var13 = this.aClass6_3835.aShort93;
         short var14 = this.aClass6_3835.aShort91;
         short var15 = this.aClass6_3835.aShort92;
         int var16 = var8 * var5 - var6 * var4 >> 16;
         int var17 = var7 * var2 + var16 * var3 >> 16;
         int var18 = var17 + (var13 * var3 + var15 * var2 >> 16);
         if(var18 > 50) {
            int var19 = var17 + (-var13 * var3 + var14 * var2 >> 16);
            if(var19 < 3584) {
               int var20 = var8 * var4 + var6 * var5 >> 16;
               int var21 = var20 + var13 << 9;
               if(var21 / var18 > Class139.anInt1824) {
                  int var22 = var20 - var13 << 9;
                  if(var22 / var18 < Class145.anInt1898) {
                     int var23 = var7 * var3 - var16 * var2 >> 16;
                     int var24 = var23 + (var13 * var2 + var15 * var3 >> 16) << 9;
                     if(var24 / var18 > Class1.anInt55) {
                        int var25 = var23 + (-var13 * var2 + var14 * var3 >> 16) << 9;
                        if(var25 / var18 < Class86.anInt1195) {
                           int var26 = 0;
                           int var27 = 0;
                           if(var1 != 0) {
                              var26 = Class51.anIntArray840[var1];
                              var27 = Class51.anIntArray851[var1];
                           }

                           if(var9 > 0L && Class3_Sub13_Sub7.aBoolean3094 && var19 > 0) {
                              int var28;
                              int var30;
                              if(var20 > 0) {
                                 var28 = var22 / var18;
                                 var30 = var21 / var19;
                              } else {
                                 var28 = var22 / var19;
                                 var30 = var21 / var18;
                              }

                              int var29;
                              int var31;
                              if(var23 > 0) {
                                 var29 = var25 / var18;
                                 var31 = var24 / var19;
                              } else {
                                 var29 = var25 / var19;
                                 var31 = var24 / var18;
                              }

                              if(Class3_Sub28_Sub11.anInt3642 >= var28 && Class3_Sub28_Sub11.anInt3642 <= var30 && RenderAnimationDefinition.anInt384 >= var29 && RenderAnimationDefinition.anInt384 <= var31) {
                                 var28 = 999999;
                                 var30 = -999999;
                                 var29 = 999999;
                                 var31 = -999999;
                                 short var32 = this.aClass6_3835.aShort95;
                                 short var33 = this.aClass6_3835.aShort94;
                                 short var34 = this.aClass6_3835.aShort97;
                                 short var35 = this.aClass6_3835.aShort96;
                                 int[] var36 = new int[]{var32, var33, var32, var33, var32, var33, var32, var33};
                                 int[] var37 = new int[]{var34, var34, var35, var35, var34, var34, var35, var35};
                                 int[] var38 = new int[]{var14, var14, var14, var14, var15, var15, var15, var15};

                                 int var39;
                                 int var42;
                                 int var43;
                                 int var40;
                                 int var41;
                                 int var44;
                                 int var45;
                                 for(var39 = 0; var39 < 8; ++var39) {
                                    var40 = var36[var39];
                                    var41 = var38[var39];
                                    var42 = var37[var39];
                                    if(var1 != 0) {
                                       var43 = var42 * var26 + var40 * var27 >> 16;
                                       var42 = var42 * var27 - var40 * var26 >> 16;
                                       var40 = var43;
                                    }

                                    var40 += var6;
                                    var41 += var7;
                                    var42 += var8;
                                    var43 = var42 * var4 + var40 * var5 >> 16;
                                    var42 = var42 * var5 - var40 * var4 >> 16;
                                    var40 = var43;
                                    var43 = var41 * var3 - var42 * var2 >> 16;
                                    var42 = var41 * var2 + var42 * var3 >> 16;
                                    if(var42 > 0) {
                                       var44 = (var40 << 9) / var42;
                                       var45 = (var43 << 9) / var42;
                                       if(var44 < var28) {
                                          var28 = var44;
                                       }

                                       if(var44 > var30) {
                                          var30 = var44;
                                       }

                                       if(var45 < var29) {
                                          var29 = var45;
                                       }

                                       if(var45 > var31) {
                                          var31 = var45;
                                       }
                                    }
                                 }

                                 if(Class3_Sub28_Sub11.anInt3642 >= var28 && Class3_Sub28_Sub11.anInt3642 <= var30 && RenderAnimationDefinition.anInt384 >= var29 && RenderAnimationDefinition.anInt384 <= var31) {
                                    if(this.aBoolean2699) {
                                       Class3_Sub13_Sub38.aLongArray3448[Class2.anInt59++] = var9;
                                    } else {
                                       if(anIntArray3861.length < this.anInt3833) {
                                          anIntArray3861 = new int[this.anInt3833];
                                          anIntArray3857 = new int[this.anInt3833];
                                       }

                                       var39 = 0;

                                       label118:
                                       while(true) {
                                          if(var39 >= this.anInt3823) {
                                             var39 = 0;

                                             while(true) {
                                                if(var39 >= this.anInt3852) {
                                                   break label118;
                                                }

                                                short var53 = this.aShortArray3811[var39];
                                                short var52 = this.aShortArray3830[var39];
                                                short var51 = this.aShortArray3831[var39];
                                                if(this.method1927(Class3_Sub28_Sub11.anInt3642, RenderAnimationDefinition.anInt384, anIntArray3857[var53], anIntArray3857[var52], anIntArray3857[var51], anIntArray3861[var53], anIntArray3861[var52], anIntArray3861[var51])) {
                                                   Class3_Sub13_Sub38.aLongArray3448[Class2.anInt59++] = var9;
                                                   break label118;
                                                }

                                                ++var39;
                                             }
                                          }

                                          var40 = this.anIntArray3822[var39];
                                          var41 = this.anIntArray3845[var39];
                                          var42 = this.anIntArray3848[var39];
                                          if(var1 != 0) {
                                             var43 = var42 * var26 + var40 * var27 >> 16;
                                             var42 = var42 * var27 - var40 * var26 >> 16;
                                             var40 = var43;
                                          }

                                          var40 += var6;
                                          var41 += var7;
                                          var42 += var8;
                                          var43 = var42 * var4 + var40 * var5 >> 16;
                                          var42 = var42 * var5 - var40 * var4 >> 16;
                                          var40 = var43;
                                          var43 = var41 * var3 - var42 * var2 >> 16;
                                          var42 = var41 * var2 + var42 * var3 >> 16;
                                          if(var42 < 50) {
                                             break;
                                          }

                                          var44 = (var40 << 9) / var42;
                                          var45 = (var43 << 9) / var42;
                                          int var46 = this.anIntArray3838[var39];
                                          int var47 = this.anIntArray3838[var39 + 1];

                                          for(int var48 = var46; var48 < var47; ++var48) {
                                             int var49 = this.aShortArray3828[var48] - 1;
                                             if(var49 == -1) {
                                                break;
                                             }

                                             anIntArray3861[var49] = var44;
                                             anIntArray3857[var49] = var45;
                                          }

                                          ++var39;
                                       }
                                    }
                                 }
                              }
                           }

                           GL var50 = HDToolKit.gl;
                           var50.glPushMatrix();
                           var50.glTranslatef((float)var6, (float)var7, (float)var8);
                           var50.glRotatef((float)var1 * 0.17578125F, 0.0F, 1.0F, 0.0F);
                           this.method1930();
                           var50.glRotatef((float)(-var1) * 0.17578125F, 0.0F, 1.0F, 0.0F);
                           var50.glTranslatef((float)(-var6), (float)(-var7), (float)(-var8));
                           var50.glPopMatrix();
                        }
                     }
                  }
               }
            }
         }
      }
   }

   final void method1902() {
      if(this.aShortArray3810 == null) {
         this.method1900();
      } else {
         int var1;
         for(var1 = 0; var1 < this.anInt3823; ++var1) {
            int var2 = this.anIntArray3848[var1];
            this.anIntArray3848[var1] = this.anIntArray3822[var1];
            this.anIntArray3822[var1] = -var2;
         }

         for(var1 = 0; var1 < this.anInt3833; ++var1) {
            short var3 = this.aShortArray3837[var1];
            this.aShortArray3837[var1] = this.aShortArray3810[var1];
            this.aShortArray3810[var1] = (short)(-var3);
         }

         this.aClass6_3835.aBoolean98 = false;
         this.aClass121_3839.aBoolean1640 = false;
         if(this.aClass121_3815 != null) {
            this.aClass121_3815.aBoolean1640 = false;
         }

      }
   }

   final int method1903() {
      return this.aShort3849;
   }

   final void method1886(int var1) {
      int var2 = Class51.anIntArray840[var1];
      int var3 = Class51.anIntArray851[var1];

      for(int var4 = 0; var4 < this.anInt3823; ++var4) {
         int var5 = this.anIntArray3845[var4] * var2 + this.anIntArray3822[var4] * var3 >> 16;
         this.anIntArray3845[var4] = this.anIntArray3845[var4] * var3 - this.anIntArray3822[var4] * var2 >> 16;
         this.anIntArray3822[var4] = var5;
      }

      this.aClass6_3835.aBoolean98 = false;
      this.aClass121_3839.aBoolean1640 = false;
   }

   final int method1883() {
      if(!this.aClass6_3835.aBoolean98) {
         this.method1917();
      }

      return this.aClass6_3835.aShort94;
   }

   final int method1872() {
      if(!this.aClass6_3835.aBoolean98) {
         this.method1917();
      }

      return this.aClass6_3835.aShort96;
   }

   final boolean method1865() {
      return this.aBoolean3809 && this.anIntArray3822 != null && this.aShortArray3810 != null;
   }

   final int method1898() {
      if(!this.aClass6_3835.aBoolean98) {
         this.method1917();
      }

      return this.aClass6_3835.aShort97;
   }

   final void method1897(int var1, int var2, int var3) {
      for(int var4 = 0; var4 < this.anInt3823; ++var4) {
         this.anIntArray3822[var4] += var1;
         this.anIntArray3845[var4] += var2;
         this.anIntArray3848[var4] += var3;
      }

      this.aClass6_3835.aBoolean98 = false;
      this.aClass121_3839.aBoolean1640 = false;
   }

   final Model method1890(boolean var1, boolean var2, boolean var3) {
      return this.method1923(var1, var2, var3, aClass140_Sub1_Sub1_3856, aClass140_Sub1_Sub1_3855);
   }

   private static void method1904(int var0, int var1, int var2, int var3, int var4, int var5, float[] var6, float var7, int var8, float var9) {
      var0 -= var3;
      var1 -= var4;
      var2 -= var5;
      float var10 = (float)var0 * var6[0] + (float)var1 * var6[1] + (float)var2 * var6[2];
      float var11 = (float)var0 * var6[3] + (float)var1 * var6[4] + (float)var2 * var6[5];
      float var12 = (float)var0 * var6[6] + (float)var1 * var6[7] + (float)var2 * var6[8];
      float var13 = (float)Math.atan2((double)var10, (double)var12) / 6.2831855F + 0.5F;
      if(var7 != 1.0F) {
         var13 *= var7;
      }

      float var14 = var11 + 0.5F + var9;
      float var15;
      if(var8 == 1) {
         var15 = var13;
         var13 = -var14;
         var14 = var15;
      } else if(var8 == 2) {
         var13 = -var13;
         var14 = -var14;
      } else if(var8 == 3) {
         var15 = var13;
         var13 = var14;
         var14 = -var15;
      }

      aFloat3863 = var13;
      aFloat3866 = var14;
   }

   final void method1866(GameObject var1, int var2, int var3, int var4, boolean var5) {
      Class140_Sub1_Sub1 var6 = (Class140_Sub1_Sub1)var1;
      if(this.anInt3852 != 0 && var6.anInt3852 != 0) {
         int var7 = var6.anInt3823;
         int[] var8 = var6.anIntArray3822;
         int[] var9 = var6.anIntArray3845;
         int[] var10 = var6.anIntArray3848;
         short[] var11 = var6.aShortArray3810;
         short[] var12 = var6.aShortArray3826;
         short[] var13 = var6.aShortArray3837;
         short[] var14 = var6.aShortArray3841;
         short[] var15;
         short[] var17;
         short[] var16;
         short[] var18;
         if(this.aClass18_3843 == null) {
            var15 = null;
            var16 = null;
            var17 = null;
            var18 = null;
         } else {
            var15 = this.aClass18_3843.aShortArray417;
            var16 = this.aClass18_3843.aShortArray419;
            var17 = this.aClass18_3843.aShortArray418;
            var18 = this.aClass18_3843.aShortArray416;
         }

          short[] var19;
         short[] var21;
         short[] var20;
         short[] var22;
         if(var6.aClass18_3843 == null) {
            var19 = null;
            var20 = null;
            var21 = null;
            var22 = null;
         } else {
            var19 = var6.aClass18_3843.aShortArray417;
            var20 = var6.aClass18_3843.aShortArray419;
            var21 = var6.aClass18_3843.aShortArray418;
            var22 = var6.aClass18_3843.aShortArray416;
         }

          int[] var23 = var6.anIntArray3838;
         short[] var24 = var6.aShortArray3828;
         if(!var6.aClass6_3835.aBoolean98) {
            var6.method1917();
         }

         short var25 = var6.aClass6_3835.aShort91;
         short var26 = var6.aClass6_3835.aShort92;
         short var27 = var6.aClass6_3835.aShort95;
         short var28 = var6.aClass6_3835.aShort94;
         short var29 = var6.aClass6_3835.aShort97;
         short var30 = var6.aClass6_3835.aShort96;

         for(int var31 = 0; var31 < this.anInt3823; ++var31) {
            int var32 = this.anIntArray3845[var31] - var3;
            if(var32 >= var25 && var32 <= var26) {
               int var33 = this.anIntArray3822[var31] - var2;
               if(var33 >= var27 && var33 <= var28) {
                  int var34 = this.anIntArray3848[var31] - var4;
                  if(var34 >= var29 && var34 <= var30) {
                     int var35 = -1;
                     int var36 = this.anIntArray3838[var31];
                     int var37 = this.anIntArray3838[var31 + 1];

                     int var38;
                     for(var38 = var36; var38 < var37; ++var38) {
                        var35 = this.aShortArray3828[var38] - 1;
                        if(var35 == -1 || this.aShortArray3841[var35] != 0) {
                           break;
                        }
                     }

                     if(var35 != -1) {
                        for(var38 = 0; var38 < var7; ++var38) {
                           if(var33 == var8[var38] && var34 == var10[var38] && var32 == var9[var38]) {
                              int var39 = -1;
                              var36 = var23[var38];
                              var37 = var23[var38 + 1];

                              for(int var40 = var36; var40 < var37; ++var40) {
                                 var39 = var24[var40] - 1;
                                 if(var39 == -1 || var14[var39] != 0) {
                                    break;
                                 }
                              }

                              if(var39 != -1) {
                                 if(var15 == null) {
                                    this.aClass18_3843 = new Class18();
                                    var15 = this.aClass18_3843.aShortArray417 = OutputStream_Sub1.method65(this.aShortArray3810);
                                    var16 = this.aClass18_3843.aShortArray419 = OutputStream_Sub1.method65(this.aShortArray3826);
                                    var17 = this.aClass18_3843.aShortArray418 = OutputStream_Sub1.method65(this.aShortArray3837);
                                    var18 = this.aClass18_3843.aShortArray416 = OutputStream_Sub1.method65(this.aShortArray3841);
                                 }

                                 if(var19 == null) {
                                    Class18 var47 = var6.aClass18_3843 = new Class18();
                                    var19 = var47.aShortArray417 = OutputStream_Sub1.method65(var11);
                                    var20 = var47.aShortArray419 = OutputStream_Sub1.method65(var12);
                                    var21 = var47.aShortArray418 = OutputStream_Sub1.method65(var13);
                                    var22 = var47.aShortArray416 = OutputStream_Sub1.method65(var14);
                                 }

                                 short var46 = this.aShortArray3810[var35];
                                 short var41 = this.aShortArray3826[var35];
                                 short var42 = this.aShortArray3837[var35];
                                 short var43 = this.aShortArray3841[var35];
                                 var36 = var23[var38];
                                 var37 = var23[var38 + 1];

                                 int var44;
                                 int var45;
                                 for(var44 = var36; var44 < var37; ++var44) {
                                    var45 = var24[var44] - 1;
                                    if(var45 == -1) {
                                       break;
                                    }

                                    if(Objects.requireNonNull(var22)[var45] != 0) {
                                       Objects.requireNonNull(var19)[var45] += var46;
                                       Objects.requireNonNull(var20)[var45] += var41;
                                       Objects.requireNonNull(var21)[var45] += var42;
                                       var22[var45] += var43;
                                    }
                                 }

                                 var46 = var11[var39];
                                 var41 = var12[var39];
                                 var42 = var13[var39];
                                 var43 = var14[var39];
                                 var36 = this.anIntArray3838[var31];
                                 var37 = this.anIntArray3838[var31 + 1];

                                 for(var44 = var36; var44 < var37; ++var44) {
                                    var45 = this.aShortArray3828[var44] - 1;
                                    if(var45 == -1) {
                                       break;
                                    }

                                    if(Objects.requireNonNull(var18)[var45] != 0) {
                                       Objects.requireNonNull(var15)[var45] += var46;
                                       Objects.requireNonNull(var16)[var45] += var41;
                                       Objects.requireNonNull(var17)[var45] += var42;
                                       var18[var45] += var43;
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

      }
   }

   final void method1893(int var2, int var3, int var4, int var5, int var6, int var7) {
      if(this.anInt3833 != 0) {
         javax.media.opengl.GL var10 = HDToolKit.gl;
         var10.glPushMatrix();
         if(var4 != 0) {
            var10.glRotatef((float)var4 * 0.17578125F, 1.0F, 0.0F, 0.0F);
         }

         var10.glTranslatef((float)var5, (float)var6, (float)var7);
         if(var2 != 0) {
            var10.glRotatef((float)var2 * 0.17578125F, 0.0F, 1.0F, 0.0F);
         }

         if(var3 != 0) {
            var10.glRotatef((float)(-var3) * 0.17578125F, 0.0F, 0.0F, 1.0F);
         }

         this.method1930();
         var10.glPopMatrix();
      }
   }

   private static int method1905(int var0, short var1, int var2, byte var3) {
      int var4 = Class51.anIntArray834[Class140_Sub1_Sub2.method1940(var0, var2)];
      if(var1 != -1) {
         int var5 = Class51.anInterface2_838.method19(93, var1 & '\uffff');
         int var6;
         int var8;
         if(var5 != 0) {
            if(var2 < 0) {
               var6 = 0;
            } else if(var2 > 127) {
               var6 = 16777215;
            } else {
               var6 = 131586 * var2;
            }

            if(var5 == 256) {
               var4 = var6;
            } else {
               var8 = 256 - var5;
               var4 = ((var6 & 16711935) * var5 + (var4 & 16711935) * var8 & -16711936) + ((var6 & '\uff00') * var5 + (var4 & '\uff00') * var8 & 16711680) >> 8;
            }
         }

         var6 = Class51.anInterface2_838.method10(90, var1 & '\uffff');
         if(var6 != 0) {
            var6 += 256;
            int var7 = ((var4 & 16711680) >> 16) * var6;
            if(var7 > '\uffff') {
               var7 = '\uffff';
            }

            var8 = ((var4 & '\uff00') >> 8) * var6;
            if(var8 > '\uffff') {
               var8 = '\uffff';
            }

            int var9 = (var4 & 255) * var6;
            if(var9 > '\uffff') {
               var9 = '\uffff';
            }

            var4 = (var7 << 8 & 16711680) + (var8 & '\uff00') + (var9 >> 8);
         }
      }

      return (var4 << 8) + (255 - (var3 & 255));
   }

   private static float[] method1906(int var0, int var1, int var2, int var3, float var4, float var5, float var6) {
      float[] var7 = new float[9];
      float[] var8 = new float[9];
      float var9 = (float)Math.cos((double)((float)var3 * 0.024543693F));
      float var10 = (float)Math.sin((double)((float)var3 * 0.024543693F));
      float var11;
      var7[0] = var9;
      var7[1] = 0.0F;
      var7[2] = var10;
      var7[3] = 0.0F;
      var7[4] = 1.0F;
      var7[5] = 0.0F;
      var7[6] = -var10;
      var7[7] = 0.0F;
      var7[8] = var9;
      float[] var12 = new float[9];
      float var13 = 1.0F;
      float var14 = 0.0F;
      var9 = (float)var1 / 32767.0F;
      var10 = -((float)Math.sqrt((double)(1.0F - var9 * var9)));
      var11 = 1.0F - var9;
      float var15 = (float)Math.sqrt((double)(var0 * var0 + var2 * var2));
      if(var15 == 0.0F && var9 == 0.0F) {
         var8 = var7;
      } else {
         if(var15 != 0.0F) {
            var13 = (float)(-var2) / var15;
            var14 = (float)var0 / var15;
         }

         var12[0] = var9 + var13 * var13 * var11;
         var12[1] = var14 * var10;
         var12[2] = var14 * var13 * var11;
         var12[3] = -var14 * var10;
         var12[4] = var9;
         var12[5] = var13 * var10;
         var12[6] = var13 * var14 * var11;
         var12[7] = -var13 * var10;
         var12[8] = var9 + var14 * var14 * var11;
         var8[0] = var7[0] * var12[0] + var7[1] * var12[3] + var7[2] * var12[6];
         var8[1] = var7[0] * var12[1] + var7[1] * var12[4] + var7[2] * var12[7];
         var8[2] = var7[0] * var12[2] + var7[1] * var12[5] + var7[2] * var12[8];
         var8[3] = var7[3] * var12[0] + var7[4] * var12[3] + var7[5] * var12[6];
         var8[4] = var7[3] * var12[1] + var7[4] * var12[4] + var7[5] * var12[7];
         var8[5] = var7[3] * var12[2] + var7[4] * var12[5] + var7[5] * var12[8];
         var8[6] = var7[6] * var12[0] + var7[7] * var12[3] + var7[8] * var12[6];
         var8[7] = var7[6] * var12[1] + var7[7] * var12[4] + var7[8] * var12[7];
         var8[8] = var7[6] * var12[2] + var7[7] * var12[5] + var7[8] * var12[8];
      }

      var8[0] *= var4;
      var8[1] *= var4;
      var8[2] *= var4;
      var8[3] *= var5;
      var8[4] *= var5;
      var8[5] *= var5;
      var8[6] *= var6;
      var8[7] *= var6;
      var8[8] *= var6;
      return var8;
   }

   private short method1907(int var2, long var3, int var5, int var6, int var7, int var8, float var9, float var10) {
      int var11 = this.anIntArray3838[var2];
      int var12 = this.anIntArray3838[var2 + 1];
      int var13 = 0;

      for(int var14 = var11; var14 < var12; ++var14) {
         short var15 = this.aShortArray3828[var14];
         if(var15 == 0) {
            var13 = var14;
            break;
         }

         if(aLongArray3821[var14] == var3) {
            return (short)(var15 - 1);
         }
      }

      this.aShortArray3828[var13] = (short)(this.anInt3833 + 1);
      aLongArray3821[var13] = var3;
      this.aShortArray3810[this.anInt3833] = (short)var5;
      this.aShortArray3826[this.anInt3833] = (short)var6;
      this.aShortArray3837[this.anInt3833] = (short)var7;
      this.aShortArray3841[this.anInt3833] = (short)var8;
      this.aFloatArray3824[this.anInt3833] = var9;
      this.aFloatArray3847[this.anInt3833] = var10;
      return (short)(this.anInt3833++);
   }

   final void method1876(int var1) {
      int var2 = Class51.anIntArray840[var1];
      int var3 = Class51.anIntArray851[var1];

      for(int var4 = 0; var4 < this.anInt3823; ++var4) {
         int var5 = this.anIntArray3848[var4] * var2 + this.anIntArray3822[var4] * var3 >> 16;
         this.anIntArray3848[var4] = this.anIntArray3848[var4] * var3 - this.anIntArray3822[var4] * var2 >> 16;
         this.anIntArray3822[var4] = var5;
      }

      this.aClass6_3835.aBoolean98 = false;
      this.aClass121_3839.aBoolean1640 = false;
   }

   final void method1899(int var1, int[] var2, int var3, int var4, int var5, boolean var6, int var7, int[] var8) {
      int var9 = var2.length;
      int var10;
      int var11;
      int var14;
      int var15;
      int var47;
      if(var1 == 0) {
         var3 <<= 4;
         var4 <<= 4;
         var5 <<= 4;
         var10 = 0;
         anInt3859 = 0;
         anInt3865 = 0;
         anInt3862 = 0;

         for(var11 = 0; var11 < var9; ++var11) {
            var47 = var2[var11];
            if(var47 < this.anIntArrayArray3825.length) {
               int[] var48 = this.anIntArrayArray3825[var47];

               for(var14 = 0; var14 < var48.length; ++var14) {
                  var15 = var48[var14];
                  if(this.aShortArray3832 == null || (var7 & this.aShortArray3832[var15]) != 0) {
                     anInt3859 += this.anIntArray3822[var15];
                     anInt3865 += this.anIntArray3845[var15];
                     anInt3862 += this.anIntArray3848[var15];
                     ++var10;
                  }
               }
            }
         }

         if(var10 > 0) {
            anInt3859 = anInt3859 / var10 + var3;
            anInt3865 = anInt3865 / var10 + var4;
            anInt3862 = anInt3862 / var10 + var5;
            aBoolean3868 = true;
         } else {
            anInt3859 = var3;
            anInt3865 = var4;
            anInt3862 = var5;
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

            var3 <<= 4;
            var4 <<= 4;
            var5 <<= 4;

            for(var10 = 0; var10 < var9; ++var10) {
               var11 = var2[var10];
               if(var11 < this.anIntArrayArray3825.length) {
                  var12 = this.anIntArrayArray3825[var11];

                  for(var13 = 0; var13 < var12.length; ++var13) {
                     var14 = var12[var13];
                     if(this.aShortArray3832 == null || (var7 & this.aShortArray3832[var14]) != 0) {
                        this.anIntArray3822[var14] += var3;
                        this.anIntArray3845[var14] += var4;
                        this.anIntArray3848[var14] += var5;
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
                     if(var11 < this.anIntArrayArray3825.length) {
                        var12 = this.anIntArrayArray3825[var11];

                        for(var13 = 0; var13 < var12.length; ++var13) {
                           var14 = var12[var13];
                           if(this.aShortArray3832 == null || (var7 & this.aShortArray3832[var14]) != 0) {
                              this.anIntArray3822[var14] -= anInt3859;
                              this.anIntArray3845[var14] -= anInt3865;
                              this.anIntArray3848[var14] -= anInt3862;
                              if(var5 != 0) {
                                 var15 = Class51.anIntArray840[var5];
                                 var16 = Class51.anIntArray851[var5];
                                 var17 = this.anIntArray3845[var14] * var15 + this.anIntArray3822[var14] * var16 + 32767 >> 16;
                                 this.anIntArray3845[var14] = this.anIntArray3845[var14] * var16 - this.anIntArray3822[var14] * var15 + 32767 >> 16;
                                 this.anIntArray3822[var14] = var17;
                              }

                              if(var3 != 0) {
                                 var15 = Class51.anIntArray840[var3];
                                 var16 = Class51.anIntArray851[var3];
                                 var17 = this.anIntArray3845[var14] * var16 - this.anIntArray3848[var14] * var15 + 32767 >> 16;
                                 this.anIntArray3848[var14] = this.anIntArray3845[var14] * var15 + this.anIntArray3848[var14] * var16 + 32767 >> 16;
                                 this.anIntArray3845[var14] = var17;
                              }

                              if(var4 != 0) {
                                 var15 = Class51.anIntArray840[var4];
                                 var16 = Class51.anIntArray851[var4];
                                 var17 = this.anIntArray3848[var14] * var15 + this.anIntArray3822[var14] * var16 + 32767 >> 16;
                                 this.anIntArray3848[var14] = this.anIntArray3848[var14] * var16 - this.anIntArray3822[var14] * var15 + 32767 >> 16;
                                 this.anIntArray3822[var14] = var17;
                              }

                              this.anIntArray3822[var14] += anInt3859;
                              this.anIntArray3845[var14] += anInt3865;
                              this.anIntArray3848[var14] += anInt3862;
                           }
                        }
                     }
                  }

                  if(var6 && this.aShortArray3810 != null) {
                     for(var10 = 0; var10 < var9; ++var10) {
                        var11 = var2[var10];
                        if(var11 < this.anIntArrayArray3825.length) {
                           var12 = this.anIntArrayArray3825[var11];

                           for(var13 = 0; var13 < var12.length; ++var13) {
                              var14 = var12[var13];
                              if(this.aShortArray3832 == null || (var7 & this.aShortArray3832[var14]) != 0) {
                                 var15 = this.anIntArray3838[var14];
                                 var16 = this.anIntArray3838[var14 + 1];

                                 for(var17 = var15; var17 < var16; ++var17) {
                                    var18 = this.aShortArray3828[var17] - 1;
                                    if(var18 == -1) {
                                       break;
                                    }

                                    if(var5 != 0) {
                                       var19 = Class51.anIntArray840[var5];
                                       var20 = Class51.anIntArray851[var5];
                                       var21 = this.aShortArray3826[var18] * var19 + this.aShortArray3810[var18] * var20 + 32767 >> 16;
                                       this.aShortArray3826[var18] = (short)(this.aShortArray3826[var18] * var20 - this.aShortArray3810[var18] * var19 + 32767 >> 16);
                                       this.aShortArray3810[var18] = (short)var21;
                                    }

                                    if(var3 != 0) {
                                       var19 = Class51.anIntArray840[var3];
                                       var20 = Class51.anIntArray851[var3];
                                       var21 = this.aShortArray3826[var18] * var20 - this.aShortArray3837[var18] * var19 + 32767 >> 16;
                                       this.aShortArray3837[var18] = (short)(this.aShortArray3826[var18] * var19 + this.aShortArray3837[var18] * var20 + 32767 >> 16);
                                       this.aShortArray3826[var18] = (short)var21;
                                    }

                                    if(var4 != 0) {
                                       var19 = Class51.anIntArray840[var4];
                                       var20 = Class51.anIntArray851[var4];
                                       var21 = this.aShortArray3837[var18] * var19 + this.aShortArray3810[var18] * var20 + 32767 >> 16;
                                       this.aShortArray3837[var18] = (short)(this.aShortArray3837[var18] * var20 - this.aShortArray3810[var18] * var19 + 32767 >> 16);
                                       this.aShortArray3810[var18] = (short)var21;
                                    }
                                 }
                              }
                           }
                        }
                     }

                     if(this.aClass121_3815 != null) {
                        this.aClass121_3815.aBoolean1640 = false;
                     }
                  }
               } else {
                  var10 = var8[9] << 4;
                  var11 = var8[10] << 4;
                  var47 = var8[11] << 4;
                  var13 = var8[12] << 4;
                  var14 = var8[13] << 4;
                  var15 = var8[14] << 4;
                  if(aBoolean3868) {
                     var16 = var8[0] * anInt3859 + var8[3] * anInt3865 + var8[6] * anInt3862 + 16384 >> 15;
                     var17 = var8[1] * anInt3859 + var8[4] * anInt3865 + var8[7] * anInt3862 + 16384 >> 15;
                     var18 = var8[2] * anInt3859 + var8[5] * anInt3865 + var8[8] * anInt3862 + 16384 >> 15;
                     var16 += var13;
                     var17 += var14;
                     var18 += var15;
                     anInt3859 = var16;
                     anInt3865 = var17;
                     anInt3862 = var18;
                     aBoolean3868 = false;
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
                  int var50 = var49[0] * -anInt3859 + var49[1] * -anInt3865 + var49[2] * -anInt3862 + 16384 >> 15;
                  var26 = var49[3] * -anInt3859 + var49[4] * -anInt3865 + var49[5] * -anInt3862 + 16384 >> 15;
                  var27 = var49[6] * -anInt3859 + var49[7] * -anInt3865 + var49[8] * -anInt3862 + 16384 >> 15;
                  var28 = var50 + anInt3859;
                  int var51 = var26 + anInt3865;
                  var30 = var27 + anInt3862;
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
                     if(var40 < this.anIntArrayArray3825.length) {
                        int[] var41 = this.anIntArrayArray3825[var40];

                        for(int var42 = 0; var42 < var41.length; ++var42) {
                           int var43 = var41[var42];
                           if(this.aShortArray3832 == null || (var7 & this.aShortArray3832[var43]) != 0) {
                              int var44 = var35[0] * this.anIntArray3822[var43] + var35[1] * this.anIntArray3845[var43] + var35[2] * this.anIntArray3848[var43] + 16384 >> 15;
                              int var45 = var35[3] * this.anIntArray3822[var43] + var35[4] * this.anIntArray3845[var43] + var35[5] * this.anIntArray3848[var43] + 16384 >> 15;
                              int var46 = var35[6] * this.anIntArray3822[var43] + var35[7] * this.anIntArray3845[var43] + var35[8] * this.anIntArray3848[var43] + 16384 >> 15;
                              var44 += var36;
                              var45 += var37;
                              var46 += var38;
                              this.anIntArray3822[var43] = var44;
                              this.anIntArray3845[var43] = var45;
                              this.anIntArray3848[var43] = var46;
                           }
                        }
                     }
                  }
               }

            } else if(var1 == 3) {
               if(var8 == null) {
                  for(var10 = 0; var10 < var9; ++var10) {
                     var11 = var2[var10];
                     if(var11 < this.anIntArrayArray3825.length) {
                        var12 = this.anIntArrayArray3825[var11];

                        for(var13 = 0; var13 < var12.length; ++var13) {
                           var14 = var12[var13];
                           if(this.aShortArray3832 == null || (var7 & this.aShortArray3832[var14]) != 0) {
                              this.anIntArray3822[var14] -= anInt3859;
                              this.anIntArray3845[var14] -= anInt3865;
                              this.anIntArray3848[var14] -= anInt3862;
                              this.anIntArray3822[var14] = this.anIntArray3822[var14] * var3 >> 7;
                              this.anIntArray3845[var14] = this.anIntArray3845[var14] * var4 >> 7;
                              this.anIntArray3848[var14] = this.anIntArray3848[var14] * var5 >> 7;
                              this.anIntArray3822[var14] += anInt3859;
                              this.anIntArray3845[var14] += anInt3865;
                              this.anIntArray3848[var14] += anInt3862;
                           }
                        }
                     }
                  }
               } else {
                  var10 = var8[9] << 4;
                  var11 = var8[10] << 4;
                  var47 = var8[11] << 4;
                  var13 = var8[12] << 4;
                  var14 = var8[13] << 4;
                  var15 = var8[14] << 4;
                  if(aBoolean3868) {
                     var16 = var8[0] * anInt3859 + var8[3] * anInt3865 + var8[6] * anInt3862 + 16384 >> 15;
                     var17 = var8[1] * anInt3859 + var8[4] * anInt3865 + var8[7] * anInt3862 + 16384 >> 15;
                     var18 = var8[2] * anInt3859 + var8[5] * anInt3865 + var8[8] * anInt3862 + 16384 >> 15;
                     var16 += var13;
                     var17 += var14;
                     var18 += var15;
                     anInt3859 = var16;
                     anInt3865 = var17;
                     anInt3862 = var18;
                     aBoolean3868 = false;
                  }

                  var16 = var3 << 15 >> 7;
                  var17 = var4 << 15 >> 7;
                  var18 = var5 << 15 >> 7;
                  var19 = var16 * -anInt3859 + 16384 >> 15;
                  var20 = var17 * -anInt3865 + 16384 >> 15;
                  var21 = var18 * -anInt3862 + 16384 >> 15;
                  var22 = var19 + anInt3859;
                  var23 = var20 + anInt3865;
                  var24 = var21 + anInt3862;
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
                     if(var34 < this.anIntArrayArray3825.length) {
                        var35 = this.anIntArrayArray3825[var34];

                        for(var36 = 0; var36 < var35.length; ++var36) {
                           var37 = var35[var36];
                           if(this.aShortArray3832 == null || (var7 & this.aShortArray3832[var37]) != 0) {
                              var38 = var29[0] * this.anIntArray3822[var37] + var29[1] * this.anIntArray3845[var37] + var29[2] * this.anIntArray3848[var37] + 16384 >> 15;
                              var39 = var29[3] * this.anIntArray3822[var37] + var29[4] * this.anIntArray3845[var37] + var29[5] * this.anIntArray3848[var37] + 16384 >> 15;
                              var40 = var29[6] * this.anIntArray3822[var37] + var29[7] * this.anIntArray3845[var37] + var29[8] * this.anIntArray3848[var37] + 16384 >> 15;
                              var38 += var30;
                              var39 += var31;
                              var40 += var32;
                              this.anIntArray3822[var37] = var38;
                              this.anIntArray3845[var37] = var39;
                              this.anIntArray3848[var37] = var40;
                           }
                        }
                     }
                  }
               }

            } else if(var1 == 5) {
               if(this.anIntArrayArray3829 != null && this.aByteArray3816 != null) {
                  for(var10 = 0; var10 < var9; ++var10) {
                     var11 = var2[var10];
                     if(var11 < this.anIntArrayArray3829.length) {
                        var12 = this.anIntArrayArray3829[var11];

                        for(var13 = 0; var13 < var12.length; ++var13) {
                           var14 = var12[var13];
                           if(this.aShortArray3813 == null || (var7 & this.aShortArray3813[var14]) != 0) {
                              var15 = (this.aByteArray3816[var14] & 255) + var3 * 8;
                              if(var15 < 0) {
                                 var15 = 0;
                              } else if(var15 > 255) {
                                 var15 = 255;
                              }

                              this.aByteArray3816[var14] = (byte)var15;
                           }
                        }

                        if(var12.length > 0) {
                           this.aClass121_3814.aBoolean1640 = false;
                        }
                     }
                  }
               }

            } else if(var1 == 7) {
               if(this.anIntArrayArray3829 != null) {
                  for(var10 = 0; var10 < var9; ++var10) {
                     var11 = var2[var10];
                     if(var11 < this.anIntArrayArray3829.length) {
                        var12 = this.anIntArrayArray3829[var11];

                        for(var13 = 0; var13 < var12.length; ++var13) {
                           var14 = var12[var13];
                           if(this.aShortArray3813 == null || (var7 & this.aShortArray3813[var14]) != 0) {
                              var15 = this.aShortArray3808[var14] & '\uffff';
                              var16 = var15 >> 10 & 63;
                              var17 = var15 >> 7 & 7;
                              var18 = var15 & 127;
                              var16 = var16 + var3 & 63;
                              var17 += var4 / 4;
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

                              this.aShortArray3808[var14] = (short)(var16 << 10 | var17 << 7 | var18);
                           }
                        }

                        if(var12.length > 0) {
                           this.aClass121_3814.aBoolean1640 = false;
                        }
                     }
                  }
               }

            }
         }
      }
   }

   final int method1871() {
      if(!this.aClass6_3835.aBoolean98) {
         this.method1917();
      }

      return this.aClass6_3835.aShort91;
   }

   final void method1908() {
      int[] var1;
      int var2;
      int var3;
      int var4;
      if(this.anIntArray3844 != null) {
         var1 = new int[256];
         var2 = 0;

         for(var3 = 0; var3 < this.anInt3823; ++var3) {
            var4 = this.anIntArray3844[var3] & 255;
            ++var1[var4];
            if(var4 > var2) {
               var2 = var4;
            }
         }

         this.anIntArrayArray3825 = new int[var2 + 1][];

         for(var3 = 0; var3 <= var2; ++var3) {
            this.anIntArrayArray3825[var3] = new int[var1[var3]];
            var1[var3] = 0;
         }

         for(var3 = 0; var3 < this.anInt3823; this.anIntArrayArray3825[var4][var1[var4]++] = var3++) {
            var4 = this.anIntArray3844[var3] & 255;
         }

         this.anIntArray3844 = null;
      }

      if(this.aByteArray3820 != null) {
         var1 = new int[256];
         var2 = 0;

         for(var3 = 0; var3 < this.anInt3852; ++var3) {
            var4 = this.aByteArray3820[var3] & 255;
            ++var1[var4];
            if(var4 > var2) {
               var2 = var4;
            }
         }

         this.anIntArrayArray3829 = new int[var2 + 1][];

         for(var3 = 0; var3 <= var2; ++var3) {
            this.anIntArrayArray3829[var3] = new int[var1[var3]];
            var1[var3] = 0;
         }

         for(var3 = 0; var3 < this.anInt3852; this.anIntArrayArray3829[var4][var1[var4]++] = var3++) {
            var4 = this.aByteArray3820[var3] & 255;
         }

         this.aByteArray3820 = null;
      }

   }

   final void method1909(int var1) {
      this.aShort3819 = (short)var1;
      if(this.aClass121_3815 != null) {
         this.aClass121_3815.aBoolean1640 = false;
      }

   }

   private static void method1910(int var0, int var1, int var2, int var3, int var4, int var5, float[] var6, int var7, float var8) {
      var0 -= var3;
      var1 -= var4;
      var2 -= var5;
      float var9 = (float)var0 * var6[0] + (float)var1 * var6[1] + (float)var2 * var6[2];
      float var10 = (float)var0 * var6[3] + (float)var1 * var6[4] + (float)var2 * var6[5];
      float var11 = (float)var0 * var6[6] + (float)var1 * var6[7] + (float)var2 * var6[8];
      float var12 = (float)Math.sqrt((double)(var9 * var9 + var10 * var10 + var11 * var11));
      float var13 = (float)Math.atan2((double)var9, (double)var11) / 6.2831855F + 0.5F;
      float var14 = (float)Math.asin((double)(var10 / var12)) / 3.1415927F + 0.5F + var8;
      float var15;
      if(var7 == 1) {
         var15 = var13;
         var13 = -var14;
         var14 = var15;
      } else if(var7 == 2) {
         var13 = -var13;
         var14 = -var14;
      } else if(var7 == 3) {
         var15 = var13;
         var13 = var14;
         var14 = -var15;
      }

      aFloat3867 = var13;
      aFloat3860 = var14;
   }

   final void method1911() {
      if(this.aShortArray3810 == null) {
         this.method1874();
      } else {
         int var1;
         for(var1 = 0; var1 < this.anInt3823; ++var1) {
            this.anIntArray3822[var1] = -this.anIntArray3822[var1];
            this.anIntArray3848[var1] = -this.anIntArray3848[var1];
         }

         for(var1 = 0; var1 < this.anInt3833; ++var1) {
            this.aShortArray3810[var1] = (short)(-this.aShortArray3810[var1]);
            this.aShortArray3837[var1] = (short)(-this.aShortArray3837[var1]);
         }

         this.aClass6_3835.aBoolean98 = false;
         this.aClass121_3839.aBoolean1640 = false;
         if(this.aClass121_3815 != null) {
            this.aClass121_3815.aBoolean1640 = false;
         }

      }
   }

   final void resize(int var1, int var2, int var3) {
      for(int var4 = 0; var4 < this.anInt3823; ++var4) {
         this.anIntArray3822[var4] = this.anIntArray3822[var4] * var1 >> 7;
         this.anIntArray3845[var4] = this.anIntArray3845[var4] * var2 >> 7;
         this.anIntArray3848[var4] = this.anIntArray3848[var4] * var3 >> 7;
      }

      this.aClass6_3835.aBoolean98 = false;
      this.aClass121_3839.aBoolean1640 = false;
   }

   final Model method1894(boolean var1, boolean var2, boolean var3) {
      return this.method1923(var1, var2, var3, aClass140_Sub1_Sub1_3850, aClass140_Sub1_Sub1_3842);
   }

   final void method1874() {
      for(int var1 = 0; var1 < this.anInt3823; ++var1) {
         this.anIntArray3822[var1] = -this.anIntArray3822[var1];
         this.anIntArray3848[var1] = -this.anIntArray3848[var1];
      }

      this.aClass6_3835.aBoolean98 = false;
      this.aClass121_3839.aBoolean1640 = false;
   }

   private void method1912() {
      if(aClass3_Sub30_3818.buffer.length < this.anInt3833 * 12) {
         aClass3_Sub30_3818 = new RSByteBuffer((this.anInt3833 + 100) * 12);
      } else {
         aClass3_Sub30_3818.index = 0;
      }

      int var1;
      if(HDToolKit.aBoolean1790) {
         for(var1 = 0; var1 < this.anInt3852; ++var1) {
            aClass3_Sub30_3818.putInt(-122, this.aShortArray3811[var1]);
            aClass3_Sub30_3818.putInt(-126, this.aShortArray3830[var1]);
            aClass3_Sub30_3818.putInt(-120, this.aShortArray3831[var1]);
         }
      } else {
         for(var1 = 0; var1 < this.anInt3852; ++var1) {
            aClass3_Sub30_3818.method757(this.aShortArray3811[var1], 98);
            aClass3_Sub30_3818.method757(this.aShortArray3830[var1], 68);
            aClass3_Sub30_3818.method757(this.aShortArray3831[var1], 77);
         }
      }

      if(HDToolKit.aBoolean1813) {
         Class156 var3 = new Class156();
         ByteBuffer var2 = ByteBuffer.wrap(aClass3_Sub30_3818.buffer, 0, aClass3_Sub30_3818.index);
         var3.method2172(var2);
         this.aClass121_3846.aBoolean1640 = true;
         this.aClass121_3846.aByteBuffer1644 = null;
         this.aClass121_3846.aClass156_1643 = var3;
      } else {
         ByteBuffer var4 = ByteBuffer.allocateDirect(aClass3_Sub30_3818.index);
         var4.put(aClass3_Sub30_3818.buffer, 0, aClass3_Sub30_3818.index);
         var4.flip();
         this.aClass121_3846.aBoolean1640 = true;
         this.aClass121_3846.aByteBuffer1644 = var4;
         this.aClass121_3846.aClass156_1643 = null;
      }

   }

   private static void method1913(int var0, int var1, int var2, int var3, int var4, int var5, int var6, float[] var7, int var8, float var9, float var10, float var11) {
      var0 -= var3;
      var1 -= var4;
      var2 -= var5;
      float var12 = (float)var0 * var7[0] + (float)var1 * var7[1] + (float)var2 * var7[2];
      float var13 = (float)var0 * var7[3] + (float)var1 * var7[4] + (float)var2 * var7[5];
      float var14 = (float)var0 * var7[6] + (float)var1 * var7[7] + (float)var2 * var7[8];
      float var15;
      float var16;
      if(var6 == 0) {
         var15 = var12 + var9 + 0.5F;
         var16 = -var14 + var11 + 0.5F;
      } else if(var6 == 1) {
         var15 = var12 + var9 + 0.5F;
         var16 = var14 + var11 + 0.5F;
      } else if(var6 == 2) {
         var15 = -var12 + var9 + 0.5F;
         var16 = -var13 + var10 + 0.5F;
      } else if(var6 == 3) {
         var15 = var12 + var9 + 0.5F;
         var16 = -var13 + var10 + 0.5F;
      } else if(var6 == 4) {
         var15 = var14 + var11 + 0.5F;
         var16 = -var13 + var10 + 0.5F;
      } else {
         var15 = -var14 + var11 + 0.5F;
         var16 = -var13 + var10 + 0.5F;
      }

      float var17;
      if(var8 == 1) {
         var17 = var15;
         var15 = -var16;
         var16 = var17;
      } else if(var8 == 2) {
         var15 = -var15;
         var16 = -var16;
      } else if(var8 == 3) {
         var17 = var15;
         var15 = var16;
         var16 = -var17;
      }

      aFloat3864 = var15;
      aFloat3858 = var16;
   }

   final void method1914(int var1) {
      this.aShort3849 = (short)var1;
      this.aClass121_3814.aBoolean1640 = false;
   }

   public static void method1915() {
      aLongArray3821 = null;
      aClass3_Sub30_3818 = null;
      aByteBuffer3834 = null;
      aClass140_Sub1_Sub1_3842 = null;
      aClass140_Sub1_Sub1_3850 = null;
      aClass140_Sub1_Sub1_3853 = null;
      aClass140_Sub1_Sub1_3854 = null;
      aClass140_Sub1_Sub1_3855 = null;
      aClass140_Sub1_Sub1_3856 = null;
      anIntArray3861 = null;
      anIntArray3857 = null;
   }

   final boolean method1873() {
      if(this.anIntArrayArray3825 == null) {
         return false;
      } else {
         for(int var1 = 0; var1 < this.anInt3823; ++var1) {
            this.anIntArray3822[var1] <<= 4;
            this.anIntArray3845[var1] <<= 4;
            this.anIntArray3848[var1] <<= 4;
         }

         anInt3859 = 0;
         anInt3865 = 0;
         anInt3862 = 0;
         return true;
      }
   }

   final void method1916(short var1, short var2) {
      int var3;
      for(var3 = 0; var3 < this.anInt3852; ++var3) {
         if(this.aShortArray3827[var3] == var1) {
            this.aShortArray3827[var3] = var2;
         }
      }

      var3 = 0;
      int var4 = 0;
      if(var1 != -1) {
         var3 = Class51.anInterface2_838.method19(-125, var1 & '\uffff');
         var4 = Class51.anInterface2_838.method10(-98, var1 & '\uffff');
      }

      int var5 = 0;
      int var6 = 0;
      if(var2 != -1) {
         var5 = Class51.anInterface2_838.method19(57, var2 & '\uffff');
         var6 = Class51.anInterface2_838.method10(-114, var2 & '\uffff');
      }

      if(var3 != var5 || var4 != var6) {
         this.aClass121_3814.aBoolean1640 = false;
      }

   }

   private void method1917() {
      int var1 = 32767;
      int var2 = 32767;
      int var3 = 32767;
      int var4 = -32768;
      int var5 = -32768;
      int var6 = -32768;
      int var7 = 0;
      int var8 = 0;

      for(int var9 = 0; var9 < this.anInt3823; ++var9) {
         int var10 = this.anIntArray3822[var9];
         int var11 = this.anIntArray3845[var9];
         int var12 = this.anIntArray3848[var9];
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

         var13 = var10 * var10 + var12 * var12 + var11 * var11;
         if(var13 > var8) {
            var8 = var13;
         }
      }

      this.aClass6_3835.aShort95 = (short)var1;
      this.aClass6_3835.aShort94 = (short)var4;
      this.aClass6_3835.aShort91 = (short)var2;
      this.aClass6_3835.aShort92 = (short)var5;
      this.aClass6_3835.aShort97 = (short)var3;
      this.aClass6_3835.aShort96 = (short)var6;
      this.aClass6_3835.aShort93 = (short)((int)(Math.sqrt((double)var7) + 0.99D));
      Math.sqrt((double)var8);
      this.aClass6_3835.aBoolean98 = true;
   }

   final void method1918(short var1, short var2) {
      for(int var3 = 0; var3 < this.anInt3852; ++var3) {
         if(this.aShortArray3808[var3] == var1) {
            this.aShortArray3808[var3] = var2;
         }
      }

      this.aClass121_3814.aBoolean1640 = false;
   }

   final void method1919(int var1, int var2, Class140_Sub1_Sub1 var3, int[][] var4, int[][] var5, int var6, int var7, int var8) {
      if(!var3.aClass6_3835.aBoolean98) {
         var3.method1917();
      }

      int var9 = var6 + var3.aClass6_3835.aShort95;
      int var10 = var6 + var3.aClass6_3835.aShort94;
      int var11 = var8 + var3.aClass6_3835.aShort97;
      int var12 = var8 + var3.aClass6_3835.aShort96;
      if(var1 != 1 && var1 != 2 && var1 != 3 && var1 != 5 || var9 >= 0 && var10 + 128 >> 7 < var4.length && var11 >= 0 && var12 + 128 >> 7 < var4[0].length) {
         if(var1 == 4 || var1 == 5) {
            if(var5 == null) {
               return;
            }

            if(var9 < 0 || var10 + 128 >> 7 >= var5.length || var11 < 0 || var12 + 128 >> 7 >= var5[0].length) {
               return;
            }
         } else {
            var9 >>= 7;
            var10 = var10 + 127 >> 7;
            var11 >>= 7;
            var12 = var12 + 127 >> 7;
            if(var4[var9][var11] == var7 && var4[var10][var11] == var7 && var4[var9][var12] == var7 && var4[var10][var12] == var7) {
               return;
            }
         }

          int var13;
         int var14;
         int var15;
         int var17;
         int var16;
         int var19;
         int var18;
         int var21;
         int var20;
         int var22;
         if(var1 == 1) {
            for(var13 = 0; var13 < this.anInt3823; ++var13) {
               var14 = this.anIntArray3822[var13] + var6;
               var15 = this.anIntArray3848[var13] + var8;
               var16 = var14 & 127;
               var17 = var15 & 127;
               var18 = var14 >> 7;
               var19 = var15 >> 7;
               var20 = var4[var18][var19] * (128 - var16) + var4[var18 + 1][var19] * var16 >> 7;
               var21 = var4[var18][var19 + 1] * (128 - var16) + var4[var18 + 1][var19 + 1] * var16 >> 7;
               var22 = var20 * (128 - var17) + var21 * var17 >> 7;
               this.anIntArray3845[var13] = this.anIntArray3845[var13] + var22 - var7;
            }
         } else {
            int var23;
            int var24;
            if(var1 == 2) {
               short var26 = var3.aClass6_3835.aShort91;

               for(var14 = 0; var14 < this.anInt3823; ++var14) {
                  var15 = (this.anIntArray3845[var14] << 16) / var26;
                  if(var15 < var2) {
                     var16 = this.anIntArray3822[var14] + var6;
                     var17 = this.anIntArray3848[var14] + var8;
                     var18 = var16 & 127;
                     var19 = var17 & 127;
                     var20 = var16 >> 7;
                     var21 = var17 >> 7;
                     var22 = var4[var20][var21] * (128 - var18) + var4[var20 + 1][var21] * var18 >> 7;
                     var23 = var4[var20][var21 + 1] * (128 - var18) + var4[var20 + 1][var21 + 1] * var18 >> 7;
                     var24 = var22 * (128 - var19) + var23 * var19 >> 7;
                     this.anIntArray3845[var14] += (var24 - var7) * (var2 - var15) / var2;
                  }
               }
            } else if(var1 == 3) {
               var13 = (var2 & 255) * 4;
               var14 = (var2 >> 8 & 255) * 4;
               this.method1895(var4, var6, var7, var8, var13, var14);
            } else if(var1 == 4) {
               var13 = var3.aClass6_3835.aShort92 - var3.aClass6_3835.aShort91;

               for(var14 = 0; var14 < this.anInt3823; ++var14) {
                  var15 = this.anIntArray3822[var14] + var6;
                  var16 = this.anIntArray3848[var14] + var8;
                  var17 = var15 & 127;
                  var18 = var16 & 127;
                  var19 = var15 >> 7;
                  var20 = var16 >> 7;
                  var21 = var5[var19][var20] * (128 - var17) + var5[var19 + 1][var20] * var17 >> 7;
                  var22 = var5[var19][var20 + 1] * (128 - var17) + var5[var19 + 1][var20 + 1] * var17 >> 7;
                  var23 = var21 * (128 - var18) + var22 * var18 >> 7;
                  this.anIntArray3845[var14] = this.anIntArray3845[var14] + (var23 - var7) + var13;
               }
            } else if(var1 == 5) {
               var13 = var3.aClass6_3835.aShort92 - var3.aClass6_3835.aShort91;

               for(var14 = 0; var14 < this.anInt3823; ++var14) {
                  var15 = this.anIntArray3822[var14] + var6;
                  var16 = this.anIntArray3848[var14] + var8;
                  var17 = var15 & 127;
                  var18 = var16 & 127;
                  var19 = var15 >> 7;
                  var20 = var16 >> 7;
                  var21 = var4[var19][var20] * (128 - var17) + var4[var19 + 1][var20] * var17 >> 7;
                  var22 = var4[var19][var20 + 1] * (128 - var17) + var4[var19 + 1][var20 + 1] * var17 >> 7;
                  var23 = var21 * (128 - var18) + var22 * var18 >> 7;
                  var21 = var5[var19][var20] * (128 - var17) + var5[var19 + 1][var20] * var17 >> 7;
                  var22 = var5[var19][var20 + 1] * (128 - var17) + var5[var19 + 1][var20 + 1] * var17 >> 7;
                  var24 = var21 * (128 - var18) + var22 * var18 >> 7;
                  int var25 = var23 - var24;
                  this.anIntArray3845[var14] = ((this.anIntArray3845[var14] << 8) / var13 * var25 >> 8) - (var7 - var23);
               }
            }
         }

         this.aClass121_3839.aBoolean1640 = false;
         this.aClass6_3835.aBoolean98 = false;
      }
   }

   final void method1920(boolean var1, boolean var2, boolean var3, boolean var5, boolean var6, boolean var7) {
      if(this.aByte3836 != 0) {
         throw new IllegalArgumentException();
      } else if(this.anInt3833 != 0) {
         if(var7) {
            boolean var8 = !this.aClass121_3814.aBoolean1640 && (var2 || var3 && !Class106.aBoolean1441);
            this.method1922(false, !this.aClass121_3839.aBoolean1640 && var1, var8, this.aClass121_3815 != null && !this.aClass121_3815.aBoolean1640 && var3, !this.aClass121_3817.aBoolean1640);
            if(!this.aClass121_3846.aBoolean1640 && var5 && var2) {
               this.method1912();
            }
         }

         if(var1) {
            if(this.aClass121_3839.aBoolean1640) {
               if(!this.aClass6_3835.aBoolean98) {
                  this.method1917();
               }

               this.anIntArray3822 = null;
               this.anIntArray3845 = null;
               this.anIntArray3848 = null;
               this.aShortArray3828 = null;
               this.anIntArray3838 = null;
            } else {
               this.aByte3851 = (byte)(this.aByte3851 | 1);
            }
         }

         if(var2) {
            if(this.aClass121_3814.aBoolean1640) {
               this.aShortArray3808 = null;
               this.aByteArray3816 = null;
            } else {
               this.aByte3851 = (byte)(this.aByte3851 | 2);
            }
         }

         if(var3 && Class106.aBoolean1441) {
            if(this.aClass121_3815.aBoolean1640) {
               this.aShortArray3810 = null;
               this.aShortArray3826 = null;
               this.aShortArray3837 = null;
               this.aShortArray3841 = null;
            } else {
               this.aByte3851 = (byte)(this.aByte3851 | 4);
            }
         }

         if(this.aClass121_3817.aBoolean1640) {
            this.aFloatArray3824 = null;
            this.aFloatArray3847 = null;
         } else {
            this.aByte3851 = (byte)(this.aByte3851 | 8);
         }

         if(var5 && var2) {
            if(this.aClass121_3846.aBoolean1640 && this.aClass121_3814.aBoolean1640) {
               this.aShortArray3811 = null;
               this.aShortArray3830 = null;
               this.aShortArray3831 = null;
            } else {
               this.aByte3851 = (byte)(this.aByte3851 | 16);
            }
         }

         if(var6) {
            this.anIntArray3844 = null;
            this.aByteArray3820 = null;
            this.anIntArrayArray3825 = (int[][])null;
            this.anIntArrayArray3829 = (int[][])null;
         }

      }
   }

   private static float[] method1921(float[] var0, int var1) {
      float[] var2 = new float[var1];
      Class76.method1360(var0, 0, var2, 0, var1);
      return var2;
   }

   private void method1922(boolean var1, boolean var2, boolean var3, boolean var4, boolean var5) {
      int var6 = 0;
      if(var2) {
         this.aClass121_3839.anInt1639 = var6;
         var6 += 12;
      }

      if(var3) {
         this.aClass121_3814.anInt1639 = var6;
         var6 += 4;
      }

      if(var4) {
         this.aClass121_3815.anInt1639 = var6;
         var6 += 12;
      }

      if(var5) {
         this.aClass121_3817.anInt1639 = var6;
         var6 += 8;
      }

      if(var6 != 0) {
         if(aClass3_Sub30_3818.buffer.length < this.anInt3833 * var6) {
            aClass3_Sub30_3818 = new RSByteBuffer((this.anInt3833 + 100) * var6);
         } else {
            aClass3_Sub30_3818.index = 0;
         }

         int var7;
         int var8;
         int var9;
         int var10;
         int var11;
         int var12;
         int var13;
         if(var2) {
            int var14;
            if(HDToolKit.aBoolean1790) {
               for(var7 = 0; var7 < this.anInt3823; ++var7) {
                  var8 = Float.floatToRawIntBits((float)this.anIntArray3822[var7]);
                  var9 = Float.floatToRawIntBits((float)this.anIntArray3845[var7]);
                  var10 = Float.floatToRawIntBits((float)this.anIntArray3848[var7]);
                  var11 = this.anIntArray3838[var7];
                  var12 = this.anIntArray3838[var7 + 1];

                  for(var13 = var11; var13 < var12; ++var13) {
                     var14 = this.aShortArray3828[var13] - 1;
                     if(var14 == -1) {
                        break;
                     }

                     aClass3_Sub30_3818.index = var14 * var6;
                     aClass3_Sub30_3818.putInt(-123, var8);
                     aClass3_Sub30_3818.putInt(-127, var9);
                     aClass3_Sub30_3818.putInt(-123, var10);
                  }
               }
            } else {
               for(var7 = 0; var7 < this.anInt3823; ++var7) {
                  var8 = Float.floatToRawIntBits((float)this.anIntArray3822[var7]);
                  var9 = Float.floatToRawIntBits((float)this.anIntArray3845[var7]);
                  var10 = Float.floatToRawIntBits((float)this.anIntArray3848[var7]);
                  var11 = this.anIntArray3838[var7];
                  var12 = this.anIntArray3838[var7 + 1];

                  for(var13 = var11; var13 < var12; ++var13) {
                     var14 = this.aShortArray3828[var13] - 1;
                     if(var14 == -1) {
                        break;
                     }

                     aClass3_Sub30_3818.index = var14 * var6;
                     aClass3_Sub30_3818.method757(var8, 105);
                     aClass3_Sub30_3818.method757(var9, 121);
                     aClass3_Sub30_3818.method757(var10, 84);
                  }
               }
            }
         }

         if(var3) {
            if(Class106.aBoolean1441) {
               for(var7 = 0; var7 < this.anInt3852; ++var7) {
                  var8 = method1905(this.aShortArray3808[var7], this.aShortArray3827[var7], this.aShort3849, this.aByteArray3816[var7]);
                  aClass3_Sub30_3818.index = this.aClass121_3814.anInt1639 + this.aShortArray3811[var7] * var6;
                  aClass3_Sub30_3818.putInt(-123, var8);
                  aClass3_Sub30_3818.index = this.aClass121_3814.anInt1639 + this.aShortArray3830[var7] * var6;
                  aClass3_Sub30_3818.putInt(-121, var8);
                  aClass3_Sub30_3818.index = this.aClass121_3814.anInt1639 + this.aShortArray3831[var7] * var6;
                  aClass3_Sub30_3818.putInt(-127, var8);
               }
            } else {
               var7 = (int)Class92.light0Position[0];
               var8 = (int)Class92.light0Position[1];
               var9 = (int)Class92.light0Position[2];
               var10 = (int)Math.sqrt((double)(var7 * var7 + var8 * var8 + var9 * var9));
               var11 = (int)((float)this.aShort3849 * 1.3F);
               var12 = this.aShort3819 * var10 >> 8;

               for(var13 = 0; var13 < this.anInt3852; ++var13) {
                  short var33 = this.aShortArray3811[var13];
                  short var15 = this.aShortArray3841[var33];
                  int var16;
                  if(var15 < 0) {
                     var16 = -1 - var15;
                  } else {
                     if(var15 == 0) {
                        var16 = var11 + (var7 * this.aShortArray3810[var33] + var8 * this.aShortArray3826[var33] + var9 * this.aShortArray3837[var33]) / (var12 + var12 / 2);
                     } else {
                        var16 = var11 + (var7 * this.aShortArray3810[var33] + var8 * this.aShortArray3826[var33] + var9 * this.aShortArray3837[var33]) / (var12 * var15);
                     }

                      if(var16 < 0) {
                        var16 = 0;
                     } else if(var16 > 16384) {
                        var16 = 16384;
                     }

                     this.aShortArray3841[var33] = (short)(-1 - var16);
                  }

                  short var17 = this.aShortArray3830[var13];
                  short var18 = this.aShortArray3841[var17];
                  int var19;
                  if(var18 < 0) {
                     var19 = -1 - var18;
                  } else {
                     if(var18 == 0) {
                        var19 = var11 + (var7 * this.aShortArray3810[var17] + var8 * this.aShortArray3826[var17] + var9 * this.aShortArray3837[var17]) / (var12 + var12 / 2);
                     } else {
                        var19 = var11 + (var7 * this.aShortArray3810[var17] + var8 * this.aShortArray3826[var17] + var9 * this.aShortArray3837[var17]) / (var12 * var18);
                     }

                      if(var19 < 0) {
                        var19 = 0;
                     } else if(var19 > 16384) {
                        var19 = 16384;
                     }

                     this.aShortArray3841[var17] = (short)(-1 - var19);
                  }

                  short var20 = this.aShortArray3831[var13];
                  short var21 = this.aShortArray3841[var20];
                  int var22;
                  if(var21 < 0) {
                     var22 = -1 - var21;
                  } else {
                     if(var21 == 0) {
                        var22 = var11 + (var7 * this.aShortArray3810[var20] + var8 * this.aShortArray3826[var20] + var9 * this.aShortArray3837[var20]) / (var12 + var12 / 2);
                     } else {
                        var22 = var11 + (var7 * this.aShortArray3810[var20] + var8 * this.aShortArray3826[var20] + var9 * this.aShortArray3837[var20]) / (var12 * var21);
                     }

                      if(var22 < 0) {
                        var22 = 0;
                     } else if(var22 > 16384) {
                        var22 = 16384;
                     }

                     this.aShortArray3841[var20] = (short)(-1 - var22);
                  }

                  int var23 = method1905(this.aShortArray3808[var13], this.aShortArray3827[var13], var16, this.aByteArray3816[var13]);
                  int var24 = method1905(this.aShortArray3808[var13], this.aShortArray3827[var13], var19, this.aByteArray3816[var13]);
                  int var25 = method1905(this.aShortArray3808[var13], this.aShortArray3827[var13], var22, this.aByteArray3816[var13]);
                  aClass3_Sub30_3818.index = this.aClass121_3814.anInt1639 + var33 * var6;
                  aClass3_Sub30_3818.putInt(-121, var23);
                  aClass3_Sub30_3818.index = this.aClass121_3814.anInt1639 + var17 * var6;
                  aClass3_Sub30_3818.putInt(-120, var24);
                  aClass3_Sub30_3818.index = this.aClass121_3814.anInt1639 + var20 * var6;
                  aClass3_Sub30_3818.putInt(-124, var25);
               }

               this.aShortArray3810 = null;
               this.aShortArray3826 = null;
               this.aShortArray3837 = null;
            }
         }

         if(var4) {
            float var27 = 3.0F / (float)this.aShort3819;
            float var26 = 3.0F / (float)(this.aShort3819 + this.aShort3819 / 2);
            aClass3_Sub30_3818.index = this.aClass121_3815.anInt1639;
            short var30;
            float var32;
            if(HDToolKit.aBoolean1790) {
               for(var9 = 0; var9 < this.anInt3833; ++var9) {
                  var30 = this.aShortArray3841[var9];
                  if(var30 == 0) {
                     aClass3_Sub30_3818.method801((float) Objects.requireNonNull(this.aShortArray3810)[var9] * var26);
                     aClass3_Sub30_3818.method801((float)this.aShortArray3826[var9] * var26);
                     aClass3_Sub30_3818.method801((float)this.aShortArray3837[var9] * var26);
                  } else {
                     var32 = var27 / (float)var30;
                     aClass3_Sub30_3818.method801((float) Objects.requireNonNull(this.aShortArray3810)[var9] * var32);
                     aClass3_Sub30_3818.method801((float)this.aShortArray3826[var9] * var32);
                     aClass3_Sub30_3818.method801((float)this.aShortArray3837[var9] * var32);
                  }

                  aClass3_Sub30_3818.index += var6 - 12;
               }
            } else {
               for(var9 = 0; var9 < this.anInt3833; ++var9) {
                  var30 = this.aShortArray3841[var9];
                  if(var30 == 0) {
                     aClass3_Sub30_3818.method762((float) Objects.requireNonNull(this.aShortArray3810)[var9] * var26, (byte)124);
                     aClass3_Sub30_3818.method762((float)this.aShortArray3826[var9] * var26, (byte)64);
                     aClass3_Sub30_3818.method762((float)this.aShortArray3837[var9] * var26, (byte)122);
                  } else {
                     var32 = var27 / (float)var30;
                     aClass3_Sub30_3818.method762((float) Objects.requireNonNull(this.aShortArray3810)[var9] * var32, (byte)91);
                     aClass3_Sub30_3818.method762((float)this.aShortArray3826[var9] * var32, (byte)86);
                     aClass3_Sub30_3818.method762((float)this.aShortArray3837[var9] * var32, (byte)111);
                  }

                  aClass3_Sub30_3818.index += var6 - 12;
               }
            }
         }

         if(var5) {
            aClass3_Sub30_3818.index = this.aClass121_3817.anInt1639;
            if(HDToolKit.aBoolean1790) {
               for(var7 = 0; var7 < this.anInt3833; ++var7) {
                  aClass3_Sub30_3818.method801(this.aFloatArray3824[var7]);
                  aClass3_Sub30_3818.method801(this.aFloatArray3847[var7]);
                  aClass3_Sub30_3818.index += var6 - 8;
               }
            } else {
               for(var7 = 0; var7 < this.anInt3833; ++var7) {
                  aClass3_Sub30_3818.method762(this.aFloatArray3824[var7], (byte)90);
                  aClass3_Sub30_3818.method762(this.aFloatArray3847[var7], (byte)76);
                  aClass3_Sub30_3818.index += var6 - 8;
               }
            }
         }

         aClass3_Sub30_3818.index = var6 * this.anInt3833;
         ByteBuffer var29;
         if(var1) {
            if(HDToolKit.aBoolean1817) {
               var29 = ByteBuffer.wrap(aClass3_Sub30_3818.buffer, 0, aClass3_Sub30_3818.index);
               if(this.aClass156_3812 == null) {
                  this.aClass156_3812 = new Class156(true);
                  this.aClass156_3812.method2172(var29);
               } else {
                  this.aClass156_3812.method2168(var29);
               }

               if(var2) {
                  this.aClass121_3839.aBoolean1640 = true;
                  this.aClass121_3839.aByteBuffer1644 = null;
                  this.aClass121_3839.aClass156_1643 = this.aClass156_3812;
                  this.aClass121_3839.anInt1648 = var6;
               }

               if(var3) {
                  this.aClass121_3814.aBoolean1640 = true;
                  this.aClass121_3814.aByteBuffer1644 = null;
                  this.aClass121_3814.aClass156_1643 = this.aClass156_3812;
                  this.aClass121_3814.anInt1648 = var6;
               }

               if(var4) {
                  this.aClass121_3815.aBoolean1640 = true;
                  this.aClass121_3815.aByteBuffer1644 = null;
                  this.aClass121_3815.aClass156_1643 = this.aClass156_3812;
                  this.aClass121_3815.anInt1648 = var6;
               }

               if(var5) {
                  this.aClass121_3817.aBoolean1640 = true;
                  this.aClass121_3817.aByteBuffer1644 = null;
                  this.aClass121_3817.aClass156_1643 = this.aClass156_3812;
                  this.aClass121_3817.anInt1648 = var6;
               }
            } else {
               if(aByteBuffer3834 != null && aByteBuffer3834.capacity() >= aClass3_Sub30_3818.index) {
                  aByteBuffer3834.clear();
               } else {
                  aByteBuffer3834 = ByteBuffer.allocateDirect(aClass3_Sub30_3818.index + 100 * var6);
               }

               aByteBuffer3834.put(aClass3_Sub30_3818.buffer, 0, aClass3_Sub30_3818.index);
               aByteBuffer3834.flip();
               if(var2) {
                  this.aClass121_3839.aBoolean1640 = true;
                  this.aClass121_3839.aByteBuffer1644 = aByteBuffer3834;
                  this.aClass121_3839.aClass156_1643 = null;
                  this.aClass121_3839.anInt1648 = var6;
               }

               if(var3) {
                  this.aClass121_3814.aBoolean1640 = true;
                  this.aClass121_3814.aByteBuffer1644 = aByteBuffer3834;
                  this.aClass121_3839.aClass156_1643 = null;
                  this.aClass121_3814.anInt1648 = var6;
               }

               if(var4) {
                  this.aClass121_3815.aBoolean1640 = true;
                  this.aClass121_3815.aByteBuffer1644 = aByteBuffer3834;
                  this.aClass121_3815.aClass156_1643 = null;
                  this.aClass121_3815.anInt1648 = var6;
               }

               if(var5) {
                  this.aClass121_3817.aBoolean1640 = true;
                  this.aClass121_3817.aByteBuffer1644 = aByteBuffer3834;
                  this.aClass121_3817.aClass156_1643 = null;
                  this.aClass121_3817.anInt1648 = var6;
               }
            }
         } else if(HDToolKit.aBoolean1813) {
            Class156 var31 = new Class156();
            ByteBuffer var28 = ByteBuffer.wrap(aClass3_Sub30_3818.buffer, 0, aClass3_Sub30_3818.index);
            var31.method2172(var28);
            if(var2) {
               this.aClass121_3839.aBoolean1640 = true;
               this.aClass121_3839.aByteBuffer1644 = null;
               this.aClass121_3839.aClass156_1643 = var31;
               this.aClass121_3839.anInt1648 = var6;
            }

            if(var3) {
               this.aClass121_3814.aBoolean1640 = true;
               this.aClass121_3814.aByteBuffer1644 = null;
               this.aClass121_3814.aClass156_1643 = var31;
               this.aClass121_3814.anInt1648 = var6;
            }

            if(var4) {
               this.aClass121_3815.aBoolean1640 = true;
               this.aClass121_3815.aByteBuffer1644 = null;
               this.aClass121_3815.aClass156_1643 = var31;
               this.aClass121_3815.anInt1648 = var6;
            }

            if(var5) {
               this.aClass121_3817.aBoolean1640 = true;
               this.aClass121_3817.aByteBuffer1644 = null;
               this.aClass121_3817.aClass156_1643 = var31;
               this.aClass121_3817.anInt1648 = var6;
            }
         } else {
            var29 = ByteBuffer.allocateDirect(aClass3_Sub30_3818.index);
            var29.put(aClass3_Sub30_3818.buffer, 0, aClass3_Sub30_3818.index);
            var29.flip();
            if(var2) {
               this.aClass121_3839.aBoolean1640 = true;
               this.aClass121_3839.aByteBuffer1644 = var29;
               this.aClass121_3839.aClass156_1643 = null;
               this.aClass121_3839.anInt1648 = var6;
            }

            if(var3) {
               this.aClass121_3814.aBoolean1640 = true;
               this.aClass121_3814.aByteBuffer1644 = var29;
               this.aClass121_3839.aClass156_1643 = null;
               this.aClass121_3814.anInt1648 = var6;
            }

            if(var4) {
               this.aClass121_3815.aBoolean1640 = true;
               this.aClass121_3815.aByteBuffer1644 = var29;
               this.aClass121_3815.aClass156_1643 = null;
               this.aClass121_3815.anInt1648 = var6;
            }

            if(var5) {
               this.aClass121_3817.aBoolean1640 = true;
               this.aClass121_3817.aByteBuffer1644 = var29;
               this.aClass121_3817.aClass156_1643 = null;
               this.aClass121_3817.anInt1648 = var6;
            }
         }

      }
   }

   private Model method1923(boolean var1, boolean var2, boolean var3, Class140_Sub1_Sub1 var4, Class140_Sub1_Sub1 var5) {
      var4.anInt3823 = this.anInt3823;
      var4.anInt3833 = this.anInt3833;
      var4.anInt3852 = this.anInt3852;
      var4.aShort3849 = this.aShort3849;
      var4.aShort3819 = this.aShort3819;
      var4.aByte3836 = (byte)(1 | (var1 && var2?0:2) | (var3?0:4));
      if(var4.anIntArray3822 == null || var4.anIntArray3822.length < this.anInt3823) {
         var4.anIntArray3822 = new int[this.anInt3823 + 100];
         var4.anIntArray3845 = new int[this.anInt3823 + 100];
         var4.anIntArray3848 = new int[this.anInt3823 + 100];
      }

      int var6;
      for(var6 = 0; var6 < this.anInt3823; ++var6) {
         var4.anIntArray3822[var6] = this.anIntArray3822[var6];
         var4.anIntArray3845[var6] = this.anIntArray3845[var6];
         var4.anIntArray3848[var6] = this.anIntArray3848[var6];
      }

      if(var4.aClass121_3839 == null) {
         var4.aClass121_3839 = new Class121();
      }

      var4.aClass121_3839.aBoolean1640 = false;
      if(var4.aClass6_3835 == null) {
         var4.aClass6_3835 = new Class6();
      }

      var4.aClass6_3835.aBoolean98 = false;
      if(var1) {
         var4.aByteArray3816 = this.aByteArray3816;
      } else {
         if(var5.aByteArray3816 == null || var5.aByteArray3816.length < this.anInt3852) {
            var5.aByteArray3816 = new byte[this.anInt3852 + 100];
         }

         var4.aByteArray3816 = var5.aByteArray3816;

         for(var6 = 0; var6 < this.anInt3852; ++var6) {
            var4.aByteArray3816[var6] = this.aByteArray3816[var6];
         }
      }

      if(var2) {
         var4.aShortArray3808 = this.aShortArray3808;
      } else {
         if(var5.aShortArray3808 == null || var5.aShortArray3808.length < this.anInt3852) {
            var5.aShortArray3808 = new short[this.anInt3852 + 100];
         }

         var4.aShortArray3808 = var5.aShortArray3808;

         for(var6 = 0; var6 < this.anInt3852; ++var6) {
            var4.aShortArray3808[var6] = this.aShortArray3808[var6];
         }
      }

      if(var1 && var2) {
         var4.aClass121_3814 = this.aClass121_3814;
      } else {
         if(var5.aClass121_3814 == null) {
            var5.aClass121_3814 = new Class121();
         }

         var4.aClass121_3814 = var5.aClass121_3814;
         var4.aClass121_3814.aBoolean1640 = false;
      }

      if(var3 || this.aShortArray3810 == null) {
         var4.aShortArray3810 = this.aShortArray3810;
         var4.aShortArray3826 = this.aShortArray3826;
         var4.aShortArray3837 = this.aShortArray3837;
         var4.aShortArray3841 = this.aShortArray3841;
         var4.aClass121_3815 = this.aClass121_3815;
      } else {
         if(var5.aShortArray3810 == null || var5.aShortArray3810.length < this.anInt3833) {
            var5.aShortArray3810 = new short[this.anInt3833 + 100];
            var5.aShortArray3826 = new short[this.anInt3833 + 100];
            var5.aShortArray3837 = new short[this.anInt3833 + 100];
            var5.aShortArray3841 = new short[this.anInt3833 + 100];
         }

         var4.aShortArray3810 = var5.aShortArray3810;
         var4.aShortArray3826 = var5.aShortArray3826;
         var4.aShortArray3837 = var5.aShortArray3837;
         var4.aShortArray3841 = var5.aShortArray3841;

         for(var6 = 0; var6 < this.anInt3833; ++var6) {
            var4.aShortArray3810[var6] = this.aShortArray3810[var6];
            var4.aShortArray3826[var6] = this.aShortArray3826[var6];
            var4.aShortArray3837[var6] = this.aShortArray3837[var6];
            var4.aShortArray3841[var6] = this.aShortArray3841[var6];
         }

         if(Class106.aBoolean1441) {
            if(var5.aClass121_3815 == null) {
               var5.aClass121_3815 = new Class121();
            }

            var4.aClass121_3815 = var5.aClass121_3815;
            var4.aClass121_3815.aBoolean1640 = false;
         } else {
            var4.aClass121_3815 = null;
         }
      }

       var4.aFloatArray3824 = this.aFloatArray3824;
      var4.aFloatArray3847 = this.aFloatArray3847;
      var4.anIntArray3844 = this.anIntArray3844;
      var4.anIntArrayArray3825 = this.anIntArrayArray3825;
      var4.aShortArray3811 = this.aShortArray3811;
      var4.aShortArray3830 = this.aShortArray3830;
      var4.aShortArray3831 = this.aShortArray3831;
      var4.aShortArray3827 = this.aShortArray3827;
      var4.aByteArray3820 = this.aByteArray3820;
      var4.anIntArrayArray3829 = this.anIntArrayArray3829;
      var4.aClass121_3817 = this.aClass121_3817;
      var4.aClass121_3846 = this.aClass121_3846;
      var4.anIntArray3840 = this.anIntArray3840;
      var4.aShortArray3828 = this.aShortArray3828;
      var4.anIntArray3838 = this.anIntArray3838;
      var4.aBoolean2699 = this.aBoolean2699;
      var4.aShortArray3832 = this.aShortArray3832;
      var4.aShortArray3813 = this.aShortArray3813;
      return var4;
   }

   final GameObject method1861() {
      this.aBoolean3809 = false;
      if(this.aClass18_3843 != null) {
         this.aShortArray3810 = this.aClass18_3843.aShortArray417;
         this.aShortArray3826 = this.aClass18_3843.aShortArray419;
         this.aShortArray3837 = this.aClass18_3843.aShortArray418;
         this.aShortArray3841 = this.aClass18_3843.aShortArray416;
         this.aClass18_3843 = null;
      }

      return this;
   }

   final int method1924() {
      return this.aShort3819;
   }

   final void method1925() {
      if(this.aShortArray3810 == null) {
         this.method1885();
      } else {
         int var1;
         for(var1 = 0; var1 < this.anInt3823; ++var1) {
            int var2 = this.anIntArray3822[var1];
            this.anIntArray3822[var1] = this.anIntArray3848[var1];
            this.anIntArray3848[var1] = -var2;
         }

         for(var1 = 0; var1 < this.anInt3833; ++var1) {
            short var3 = this.aShortArray3810[var1];
            this.aShortArray3810[var1] = this.aShortArray3837[var1];
            this.aShortArray3837[var1] = (short)(-var3);
         }

         this.aClass6_3835.aBoolean98 = false;
         this.aClass121_3839.aBoolean1640 = false;
         if(this.aClass121_3815 != null) {
            this.aClass121_3815.aBoolean1640 = false;
         }

      }
   }

   final Class140_Sub1_Sub1 method1926(boolean var1, boolean var2, boolean var3, boolean var5, boolean var6, boolean var7, boolean var8, boolean var10, boolean var11) {
      Class140_Sub1_Sub1 var12 = new Class140_Sub1_Sub1();
      var12.anInt3823 = this.anInt3823;
      var12.anInt3833 = this.anInt3833;
      var12.anInt3852 = this.anInt3852;
      if(var1) {
         var12.anIntArray3822 = this.anIntArray3822;
         var12.anIntArray3848 = this.anIntArray3848;
      } else {
         var12.anIntArray3822 = Class65.method1233(this.anIntArray3822, 2);
         var12.anIntArray3848 = Class65.method1233(this.anIntArray3848, 2);
      }

      if(var2) {
         var12.anIntArray3845 = this.anIntArray3845;
      } else {
         var12.anIntArray3845 = Class65.method1233(this.anIntArray3845, 2);
      }

      if(var1 && var2) {
         var12.aClass121_3839 = this.aClass121_3839;
         var12.aClass6_3835 = this.aClass6_3835;
      } else {
         var12.aClass121_3839 = new Class121();
         var12.aClass6_3835 = new Class6();
      }

      if(var3) {
         var12.aShortArray3808 = this.aShortArray3808;
      } else {
         var12.aShortArray3808 = OutputStream_Sub1.method65(this.aShortArray3808);
      }

      var12.aByteArray3816 = this.aByteArray3816;

      if(var3 && var5 && (var8 && var6 || Class106.aBoolean1441)) {
         var12.aClass121_3814 = this.aClass121_3814;
      } else {
         var12.aClass121_3814 = new Class121();
      }

      if(var6) {
         var12.aShortArray3810 = this.aShortArray3810;
         var12.aShortArray3826 = this.aShortArray3826;
         var12.aShortArray3837 = this.aShortArray3837;
         var12.aShortArray3841 = this.aShortArray3841;
      } else {
         var12.aShortArray3810 = OutputStream_Sub1.method65(this.aShortArray3810);
         var12.aShortArray3826 = OutputStream_Sub1.method65(this.aShortArray3826);
         var12.aShortArray3837 = OutputStream_Sub1.method65(this.aShortArray3837);
         var12.aShortArray3841 = OutputStream_Sub1.method65(this.aShortArray3841);
      }

      if(Class106.aBoolean1441) {
         if(var6 && var7 && var8) {
            var12.aClass121_3815 = this.aClass121_3815;
         } else {
            var12.aClass121_3815 = new Class121();
         }
      } else {
         var12.aClass121_3815 = null;
      }

      var12.aFloatArray3824 = this.aFloatArray3824;
      var12.aFloatArray3847 = this.aFloatArray3847;
      var12.aClass121_3817 = this.aClass121_3817;

      if(var10) {
         var12.aShortArray3811 = this.aShortArray3811;
         var12.aShortArray3830 = this.aShortArray3830;
         var12.aShortArray3831 = this.aShortArray3831;
         var12.aClass121_3846 = this.aClass121_3846;
      } else {
         var12.aShortArray3811 = OutputStream_Sub1.method65(this.aShortArray3811);
         var12.aShortArray3830 = OutputStream_Sub1.method65(this.aShortArray3830);
         var12.aShortArray3831 = OutputStream_Sub1.method65(this.aShortArray3831);
         var12.aClass121_3846 = new Class121();
      }

      if(var11) {
         var12.aShortArray3827 = this.aShortArray3827;
      } else {
         var12.aShortArray3827 = OutputStream_Sub1.method65(this.aShortArray3827);
      }

      var12.anIntArray3844 = this.anIntArray3844;
      var12.anIntArrayArray3825 = this.anIntArrayArray3825;
      var12.aByteArray3820 = this.aByteArray3820;
      var12.anIntArrayArray3829 = this.anIntArrayArray3829;
      var12.anIntArray3840 = this.anIntArray3840;
      var12.aShortArray3828 = this.aShortArray3828;
      var12.anIntArray3838 = this.anIntArray3838;
      var12.aShort3849 = this.aShort3849;
      var12.aShort3819 = this.aShort3819;
      var12.aShortArray3832 = this.aShortArray3832;
      var12.aShortArray3813 = this.aShortArray3813;
      return var12;
   }

   final void method1885() {
      for(int var1 = 0; var1 < this.anInt3823; ++var1) {
         int var2 = this.anIntArray3822[var1];
         this.anIntArray3822[var1] = this.anIntArray3848[var1];
         this.anIntArray3848[var1] = -var2;
      }

      this.aClass6_3835.aBoolean98 = false;
      this.aClass121_3839.aBoolean1640 = false;
   }

   private boolean method1927(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      return (var2 >= var3 || var2 >= var4 || var2 >= var5) && ((var2 <= var3 || var2 <= var4 || var2 <= var5) && ((var1 >= var6 || var1 >= var7 || var1 >= var8) && (var1 <= var6 || var1 <= var7 || var1 <= var8)));
   }

   private static short[] method1928(short[] var0, int var1) {
      short[] var2 = new short[var1];
      Class76.method1361(var0, 0, var2, 0, var1);
      return var2;
   }

   final int method1888() {
      if(!this.aClass6_3835.aBoolean98) {
         this.method1917();
      }

      return this.aClass6_3835.aShort93;
   }

   final void method1891(int var1, int[] var2, int var3, int var4, int var5, boolean var6) {
      int var7 = var2.length;
      int var8;
      int var9;
      int var12;
      int var13;
      if(var1 == 0) {
         var3 <<= 4;
         var4 <<= 4;
         var5 <<= 4;
         var8 = 0;
         anInt3859 = 0;
         anInt3865 = 0;
         anInt3862 = 0;

         for(var9 = 0; var9 < var7; ++var9) {
            int var20 = var2[var9];
            if(var20 < this.anIntArrayArray3825.length) {
               int[] var21 = this.anIntArrayArray3825[var20];

               for(var12 = 0; var12 < var21.length; ++var12) {
                  var13 = var21[var12];
                  anInt3859 += this.anIntArray3822[var13];
                  anInt3865 += this.anIntArray3845[var13];
                  anInt3862 += this.anIntArray3848[var13];
                  ++var8;
               }
            }
         }

         if(var8 > 0) {
            anInt3859 = anInt3859 / var8 + var3;
            anInt3865 = anInt3865 / var8 + var4;
            anInt3862 = anInt3862 / var8 + var5;
         } else {
            anInt3859 = var3;
            anInt3865 = var4;
            anInt3862 = var5;
         }

      } else {
         int[] var10;
         int var11;
         if(var1 == 1) {
            var3 <<= 4;
            var4 <<= 4;
            var5 <<= 4;

            for(var8 = 0; var8 < var7; ++var8) {
               var9 = var2[var8];
               if(var9 < this.anIntArrayArray3825.length) {
                  var10 = this.anIntArrayArray3825[var9];

                  for(var11 = 0; var11 < var10.length; ++var11) {
                     var12 = var10[var11];
                     this.anIntArray3822[var12] += var3;
                     this.anIntArray3845[var12] += var4;
                     this.anIntArray3848[var12] += var5;
                  }
               }
            }

         } else {
            int var14;
            int var15;
            int var16;
            if(var1 == 2) {
               for(var8 = 0; var8 < var7; ++var8) {
                  var9 = var2[var8];
                  if(var9 < this.anIntArrayArray3825.length) {
                     var10 = this.anIntArrayArray3825[var9];

                     for(var11 = 0; var11 < var10.length; ++var11) {
                        var12 = var10[var11];
                        this.anIntArray3822[var12] -= anInt3859;
                        this.anIntArray3845[var12] -= anInt3865;
                        this.anIntArray3848[var12] -= anInt3862;
                        if(var5 != 0) {
                           var13 = Class51.anIntArray840[var5];
                           var14 = Class51.anIntArray851[var5];
                           var15 = this.anIntArray3845[var12] * var13 + this.anIntArray3822[var12] * var14 + 32767 >> 16;
                           this.anIntArray3845[var12] = this.anIntArray3845[var12] * var14 - this.anIntArray3822[var12] * var13 + 32767 >> 16;
                           this.anIntArray3822[var12] = var15;
                        }

                        if(var3 != 0) {
                           var13 = Class51.anIntArray840[var3];
                           var14 = Class51.anIntArray851[var3];
                           var15 = this.anIntArray3845[var12] * var14 - this.anIntArray3848[var12] * var13 + 32767 >> 16;
                           this.anIntArray3848[var12] = this.anIntArray3845[var12] * var13 + this.anIntArray3848[var12] * var14 + 32767 >> 16;
                           this.anIntArray3845[var12] = var15;
                        }

                        if(var4 != 0) {
                           var13 = Class51.anIntArray840[var4];
                           var14 = Class51.anIntArray851[var4];
                           var15 = this.anIntArray3848[var12] * var13 + this.anIntArray3822[var12] * var14 + 32767 >> 16;
                           this.anIntArray3848[var12] = this.anIntArray3848[var12] * var14 - this.anIntArray3822[var12] * var13 + 32767 >> 16;
                           this.anIntArray3822[var12] = var15;
                        }

                        this.anIntArray3822[var12] += anInt3859;
                        this.anIntArray3845[var12] += anInt3865;
                        this.anIntArray3848[var12] += anInt3862;
                     }
                  }
               }

               if(var6 && this.aShortArray3810 != null) {
                  for(var8 = 0; var8 < var7; ++var8) {
                     var9 = var2[var8];
                     if(var9 < this.anIntArrayArray3825.length) {
                        var10 = this.anIntArrayArray3825[var9];

                        for(var11 = 0; var11 < var10.length; ++var11) {
                           var12 = var10[var11];
                           var13 = this.anIntArray3838[var12];
                           var14 = this.anIntArray3838[var12 + 1];

                           for(var15 = var13; var15 < var14; ++var15) {
                              var16 = this.aShortArray3828[var15] - 1;
                              if(var16 == -1) {
                                 break;
                              }

                              int var17;
                              int var19;
                              int var18;
                              if(var5 != 0) {
                                 var17 = Class51.anIntArray840[var5];
                                 var18 = Class51.anIntArray851[var5];
                                 var19 = this.aShortArray3826[var16] * var17 + this.aShortArray3810[var16] * var18 + 32767 >> 16;
                                 this.aShortArray3826[var16] = (short)(this.aShortArray3826[var16] * var18 - this.aShortArray3810[var16] * var17 + 32767 >> 16);
                                 this.aShortArray3810[var16] = (short)var19;
                              }

                              if(var3 != 0) {
                                 var17 = Class51.anIntArray840[var3];
                                 var18 = Class51.anIntArray851[var3];
                                 var19 = this.aShortArray3826[var16] * var18 - this.aShortArray3837[var16] * var17 + 32767 >> 16;
                                 this.aShortArray3837[var16] = (short)(this.aShortArray3826[var16] * var17 + this.aShortArray3837[var16] * var18 + 32767 >> 16);
                                 this.aShortArray3826[var16] = (short)var19;
                              }

                              if(var4 != 0) {
                                 var17 = Class51.anIntArray840[var4];
                                 var18 = Class51.anIntArray851[var4];
                                 var19 = this.aShortArray3837[var16] * var17 + this.aShortArray3810[var16] * var18 + 32767 >> 16;
                                 this.aShortArray3837[var16] = (short)(this.aShortArray3837[var16] * var18 - this.aShortArray3810[var16] * var17 + 32767 >> 16);
                                 this.aShortArray3810[var16] = (short)var19;
                              }
                           }
                        }
                     }
                  }

                  if(this.aClass121_3815 != null) {
                     this.aClass121_3815.aBoolean1640 = false;
                  }
               }

            } else if(var1 == 3) {
               for(var8 = 0; var8 < var7; ++var8) {
                  var9 = var2[var8];
                  if(var9 < this.anIntArrayArray3825.length) {
                     var10 = this.anIntArrayArray3825[var9];

                     for(var11 = 0; var11 < var10.length; ++var11) {
                        var12 = var10[var11];
                        this.anIntArray3822[var12] -= anInt3859;
                        this.anIntArray3845[var12] -= anInt3865;
                        this.anIntArray3848[var12] -= anInt3862;
                        this.anIntArray3822[var12] = this.anIntArray3822[var12] * var3 >> 7;
                        this.anIntArray3845[var12] = this.anIntArray3845[var12] * var4 >> 7;
                        this.anIntArray3848[var12] = this.anIntArray3848[var12] * var5 >> 7;
                        this.anIntArray3822[var12] += anInt3859;
                        this.anIntArray3845[var12] += anInt3865;
                        this.anIntArray3848[var12] += anInt3862;
                     }
                  }
               }

            } else if(var1 == 5) {
               if(this.anIntArrayArray3829 != null && this.aByteArray3816 != null) {
                  for(var8 = 0; var8 < var7; ++var8) {
                     var9 = var2[var8];
                     if(var9 < this.anIntArrayArray3829.length) {
                        var10 = this.anIntArrayArray3829[var9];

                        for(var11 = 0; var11 < var10.length; ++var11) {
                           var12 = var10[var11];
                           var13 = (this.aByteArray3816[var12] & 255) + var3 * 8;
                           if(var13 < 0) {
                              var13 = 0;
                           } else if(var13 > 255) {
                              var13 = 255;
                           }

                           this.aByteArray3816[var12] = (byte)var13;
                        }

                        if(var10.length > 0) {
                           this.aClass121_3814.aBoolean1640 = false;
                        }
                     }
                  }
               }

            } else if(var1 == 7) {
               if(this.anIntArrayArray3829 != null) {
                  for(var8 = 0; var8 < var7; ++var8) {
                     var9 = var2[var8];
                     if(var9 < this.anIntArrayArray3829.length) {
                        var10 = this.anIntArrayArray3829[var9];

                        for(var11 = 0; var11 < var10.length; ++var11) {
                           var12 = var10[var11];
                           var13 = this.aShortArray3808[var12] & '\uffff';
                           var14 = var13 >> 10 & 63;
                           var15 = var13 >> 7 & 7;
                           var16 = var13 & 127;
                           var14 = var14 + var3 & 63;
                           var15 += var4 / 4;
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

                           this.aShortArray3808[var12] = (short)(var14 << 10 | var15 << 7 | var16);
                        }

                        if(var10.length > 0) {
                           this.aClass121_3814.aBoolean1640 = false;
                        }
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
         anInt3859 = 0;
         anInt3865 = 0;
         anInt3862 = 0;

         for(var6 = 0; var6 < this.anInt3823; ++var6) {
            anInt3859 += this.anIntArray3822[var6];
            anInt3865 += this.anIntArray3845[var6];
            anInt3862 += this.anIntArray3848[var6];
            ++var5;
         }

         if(var5 > 0) {
            anInt3859 = anInt3859 / var5 + var2;
            anInt3865 = anInt3865 / var5 + var3;
            anInt3862 = anInt3862 / var5 + var4;
         } else {
            anInt3859 = var2;
            anInt3865 = var3;
            anInt3862 = var4;
         }

      } else if(var1 == 1) {
         for(var5 = 0; var5 < this.anInt3823; ++var5) {
            this.anIntArray3822[var5] += var2;
            this.anIntArray3845[var5] += var3;
            this.anIntArray3848[var5] += var4;
         }

      } else {
         int var7;
         int var8;
         if(var1 == 2) {
            for(var5 = 0; var5 < this.anInt3823; ++var5) {
               this.anIntArray3822[var5] -= anInt3859;
               this.anIntArray3845[var5] -= anInt3865;
               this.anIntArray3848[var5] -= anInt3862;
               if(var4 != 0) {
                  var6 = Class51.anIntArray840[var4];
                  var7 = Class51.anIntArray851[var4];
                  var8 = this.anIntArray3845[var5] * var6 + this.anIntArray3822[var5] * var7 + 32767 >> 16;
                  this.anIntArray3845[var5] = this.anIntArray3845[var5] * var7 - this.anIntArray3822[var5] * var6 + 32767 >> 16;
                  this.anIntArray3822[var5] = var8;
               }

               if(var2 != 0) {
                  var6 = Class51.anIntArray840[var2];
                  var7 = Class51.anIntArray851[var2];
                  var8 = this.anIntArray3845[var5] * var7 - this.anIntArray3848[var5] * var6 + 32767 >> 16;
                  this.anIntArray3848[var5] = this.anIntArray3845[var5] * var6 + this.anIntArray3848[var5] * var7 + 32767 >> 16;
                  this.anIntArray3845[var5] = var8;
               }

               if(var3 != 0) {
                  var6 = Class51.anIntArray840[var3];
                  var7 = Class51.anIntArray851[var3];
                  var8 = this.anIntArray3848[var5] * var6 + this.anIntArray3822[var5] * var7 + 32767 >> 16;
                  this.anIntArray3848[var5] = this.anIntArray3848[var5] * var7 - this.anIntArray3822[var5] * var6 + 32767 >> 16;
                  this.anIntArray3822[var5] = var8;
               }

               this.anIntArray3822[var5] += anInt3859;
               this.anIntArray3845[var5] += anInt3865;
               this.anIntArray3848[var5] += anInt3862;
            }

         } else if(var1 == 3) {
            for(var5 = 0; var5 < this.anInt3823; ++var5) {
               this.anIntArray3822[var5] -= anInt3859;
               this.anIntArray3845[var5] -= anInt3865;
               this.anIntArray3848[var5] -= anInt3862;
               this.anIntArray3822[var5] = this.anIntArray3822[var5] * var2 / 128;
               this.anIntArray3845[var5] = this.anIntArray3845[var5] * var3 / 128;
               this.anIntArray3848[var5] = this.anIntArray3848[var5] * var4 / 128;
               this.anIntArray3822[var5] += anInt3859;
               this.anIntArray3845[var5] += anInt3865;
               this.anIntArray3848[var5] += anInt3862;
            }

         } else if (var1 == 5) {
            for (var5 = 0; var5 < this.anInt3852; ++var5) {
               var6 = (this.aByteArray3816[var5] & 255) + var2 * 8;
               if (var6 < 0) {
                  var6 = 0;
               } else if (var6 > 255) {
                  var6 = 255;
               }

               this.aByteArray3816[var5] = (byte) var6;
            }

            this.aClass121_3814.aBoolean1640 = false;
         } else if (var1 == 7) {
            for (var5 = 0; var5 < this.anInt3852; ++var5) {
               var6 = this.aShortArray3808[var5] & '\uffff';
               var7 = var6 >> 10 & 63;
               var8 = var6 >> 7 & 7;
               int var9 = var6 & 127;
               var7 = var7 + var2 & 63;
               var8 += var3 / 4;
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

               this.aShortArray3808[var5] = (short) (var7 << 10 | var8 << 7 | var9);
            }

            this.aClass121_3814.aBoolean1640 = false;
         }
      }
   }

   static void method1929() {
      aClass140_Sub1_Sub1_3842 = new Class140_Sub1_Sub1();
      aClass140_Sub1_Sub1_3850 = new Class140_Sub1_Sub1();
      aClass140_Sub1_Sub1_3853 = new Class140_Sub1_Sub1();
      aClass140_Sub1_Sub1_3854 = new Class140_Sub1_Sub1();
      aClass140_Sub1_Sub1_3855 = new Class140_Sub1_Sub1();
      aClass140_Sub1_Sub1_3856 = new Class140_Sub1_Sub1();
   }

   final int method1884() {
      if(!this.aClass6_3835.aBoolean98) {
         this.method1917();
      }

      return this.aClass6_3835.aShort95;
   }

   private void method1930() {
      javax.media.opengl.GL var1 = HDToolKit.gl;
      if(this.anInt3852 != 0) {
         if(this.aByte3836 != 0) {
            this.method1922(true, !this.aClass121_3839.aBoolean1640 && (this.aByte3836 & 1) != 0, !this.aClass121_3814.aBoolean1640 && (this.aByte3836 & 2) != 0, this.aClass121_3815 != null && !this.aClass121_3815.aBoolean1640 && (this.aByte3836 & 4) != 0, false);
         }

         this.method1922(false, !this.aClass121_3839.aBoolean1640, !this.aClass121_3814.aBoolean1640, this.aClass121_3815 != null && !this.aClass121_3815.aBoolean1640, !this.aClass121_3817.aBoolean1640);
         if(!this.aClass121_3846.aBoolean1640) {
            this.method1912();
         }

         if(this.aByte3851 != 0) {
            if((this.aByte3851 & 1) != 0) {
               this.anIntArray3822 = null;
               this.anIntArray3845 = null;
               this.anIntArray3848 = null;
               this.aShortArray3828 = null;
               this.anIntArray3838 = null;
            }

            if((this.aByte3851 & 2) != 0) {
               this.aShortArray3808 = null;
               this.aByteArray3816 = null;
            }

            if((this.aByte3851 & 4) != 0) {
               this.aShortArray3810 = null;
               this.aShortArray3826 = null;
               this.aShortArray3837 = null;
               this.aShortArray3841 = null;
            }

            if((this.aByte3851 & 8) != 0) {
               this.aFloatArray3824 = null;
               this.aFloatArray3847 = null;
            }

            if((this.aByte3851 & 16) != 0) {
               this.aShortArray3811 = null;
               this.aShortArray3830 = null;
               this.aShortArray3831 = null;
            }

            this.aByte3851 = 0;
         }

         Class156 var2 = null;
         if(this.aClass121_3839.aClass156_1643 != null) {
            this.aClass121_3839.aClass156_1643.method2169();
            var2 = this.aClass121_3839.aClass156_1643;
            var1.glVertexPointer(3, 5126, this.aClass121_3839.anInt1648, (long)this.aClass121_3839.anInt1639);
         }

         if(this.aClass121_3814.aClass156_1643 != null) {
            if(var2 != this.aClass121_3814.aClass156_1643) {
               this.aClass121_3814.aClass156_1643.method2169();
               var2 = this.aClass121_3814.aClass156_1643;
            }

            var1.glColorPointer(4, 5121, this.aClass121_3814.anInt1648, (long)this.aClass121_3814.anInt1639);
         }

         if(Class106.aBoolean1441 && this.aClass121_3815.aClass156_1643 != null) {
            if(var2 != this.aClass121_3815.aClass156_1643) {
               this.aClass121_3815.aClass156_1643.method2169();
               var2 = this.aClass121_3815.aClass156_1643;
            }

            var1.glNormalPointer(5126, this.aClass121_3815.anInt1648, (long)this.aClass121_3815.anInt1639);
         }

         if(this.aClass121_3817.aClass156_1643 != null) {
            if(var2 != this.aClass121_3817.aClass156_1643) {
               this.aClass121_3817.aClass156_1643.method2169();
            }

            var1.glTexCoordPointer(2, 5126, this.aClass121_3817.anInt1648, (long)this.aClass121_3817.anInt1639);
         }

         if(this.aClass121_3846.aClass156_1643 != null) {
            this.aClass121_3846.aClass156_1643.method2171();
         }

         if(this.aClass121_3839.aClass156_1643 == null || this.aClass121_3814.aClass156_1643 == null || Class106.aBoolean1441 && this.aClass121_3815.aClass156_1643 == null || this.aClass121_3817.aClass156_1643 == null) {
            if(HDToolKit.aBoolean1813) {
               var1.glBindBufferARB('\u8892', 0);
            }

            if(this.aClass121_3839.aClass156_1643 == null) {
               this.aClass121_3839.aByteBuffer1644.position(this.aClass121_3839.anInt1639);
               var1.glVertexPointer(3, 5126, this.aClass121_3839.anInt1648, this.aClass121_3839.aByteBuffer1644);
            }

            if(this.aClass121_3814.aClass156_1643 == null) {
               this.aClass121_3814.aByteBuffer1644.position(this.aClass121_3814.anInt1639);
               var1.glColorPointer(4, 5121, this.aClass121_3814.anInt1648, this.aClass121_3814.aByteBuffer1644);
            }

            if(Class106.aBoolean1441 && this.aClass121_3815.aClass156_1643 == null) {
               this.aClass121_3815.aByteBuffer1644.position(this.aClass121_3815.anInt1639);
               var1.glNormalPointer(5126, this.aClass121_3815.anInt1648, this.aClass121_3815.aByteBuffer1644);
            }

            if(this.aClass121_3817.aClass156_1643 == null) {
               this.aClass121_3817.aByteBuffer1644.position(this.aClass121_3817.anInt1639);
               var1.glTexCoordPointer(2, 5126, this.aClass121_3817.anInt1648, this.aClass121_3817.aByteBuffer1644);
            }
         }

         if(this.aClass121_3846.aClass156_1643 == null && HDToolKit.aBoolean1813) {
            var1.glBindBufferARB('\u8893', 0);
         }

         int var3 = this.anIntArray3840.length - 1;

         for(int var4 = 0; var4 < var3; ++var4) {
            int var5 = this.anIntArray3840[var4];
            int var6 = this.anIntArray3840[var4 + 1];
            short var7 = this.aShortArray3827[var5];
            if(var7 == -1) {
               HDToolKit.bindTexture2D(-1);
               Class3_Sub28_Sub4.method551(0, 0);
            } else {
               Class51.anInterface2_838.method8(var7 & '\uffff', true);
            }

            if(this.aClass121_3846.aClass156_1643 == null) {
               this.aClass121_3846.aByteBuffer1644.position(var5 * 12);
               var1.glDrawElements(4, (var6 - var5) * 3, 5125, this.aClass121_3846.aByteBuffer1644);
            } else {
               var1.glDrawElements(4, (var6 - var5) * 3, 5125, (long)(var5 * 12));
            }
         }

      }
   }

   final void method1879() {
      for(int var1 = 0; var1 < this.anInt3823; ++var1) {
         this.anIntArray3822[var1] = this.anIntArray3822[var1] + 7 >> 4;
         this.anIntArray3845[var1] = this.anIntArray3845[var1] + 7 >> 4;
         this.anIntArray3848[var1] = this.anIntArray3848[var1] + 7 >> 4;
      }

      this.aClass6_3835.aBoolean98 = false;
      this.aClass121_3839.aBoolean1640 = false;
   }

   final void method1931() {
      int var1;
      for(var1 = 0; var1 < this.anInt3823; ++var1) {
         this.anIntArray3848[var1] = -this.anIntArray3848[var1];
      }

      if(this.aShortArray3837 != null) {
         for(var1 = 0; var1 < this.anInt3833; ++var1) {
            this.aShortArray3837[var1] = (short)(-this.aShortArray3837[var1]);
         }
      }

      for(var1 = 0; var1 < this.anInt3852; ++var1) {
         short var2 = this.aShortArray3811[var1];
         this.aShortArray3811[var1] = this.aShortArray3831[var1];
         this.aShortArray3831[var1] = var2;
      }

      this.aClass6_3835.aBoolean98 = false;
      this.aClass121_3839.aBoolean1640 = false;
      if(this.aClass121_3815 != null) {
         this.aClass121_3815.aBoolean1640 = false;
      }

      this.aClass121_3846.aBoolean1640 = false;
   }

   final void method1896(int var1) {
      int var2 = Class51.anIntArray840[var1];
      int var3 = Class51.anIntArray851[var1];

      for(int var4 = 0; var4 < this.anInt3823; ++var4) {
         int var5 = this.anIntArray3845[var4] * var3 - this.anIntArray3848[var4] * var2 >> 16;
         this.anIntArray3848[var4] = this.anIntArray3845[var4] * var2 + this.anIntArray3848[var4] * var3 >> 16;
         this.anIntArray3845[var4] = var5;
      }

      this.aClass6_3835.aBoolean98 = false;
      this.aClass121_3839.aBoolean1640 = false;
   }

   final void method1932() {
      if(this.aShortArray3810 == null) {
         this.method1876(256);
      } else {
         int var2 = Class51.anIntArray840[256];
         int var3 = Class51.anIntArray851[256];

         int var4;
         int var5;
         for(var4 = 0; var4 < this.anInt3823; ++var4) {
            var5 = this.anIntArray3848[var4] * var2 + this.anIntArray3822[var4] * var3 >> 16;
            this.anIntArray3848[var4] = this.anIntArray3848[var4] * var3 - this.anIntArray3822[var4] * var2 >> 16;
            this.anIntArray3822[var4] = var5;
         }

         for(var4 = 0; var4 < this.anInt3833; ++var4) {
            var5 = this.aShortArray3837[var4] * var2 + this.aShortArray3810[var4] * var3 >> 16;
            this.aShortArray3837[var4] = (short)(this.aShortArray3837[var4] * var3 - this.aShortArray3810[var4] * var2 >> 16);
            this.aShortArray3810[var4] = (short)var5;
         }

         this.aClass6_3835.aBoolean98 = false;
         this.aClass121_3839.aBoolean1640 = false;
         if(this.aClass121_3815 != null) {
            this.aClass121_3815.aBoolean1640 = false;
         }

      }
   }

   final LDIndexedSprite method1933(LDIndexedSprite var1) {
      if(this.anInt3833 == 0) {
         return null;
      } else {
         if(!this.aClass6_3835.aBoolean98) {
            this.method1917();
         }

         int var2;
         int var3;
         if(Class92.lightX > 0) {
            var2 = this.aClass6_3835.aShort95 - (this.aClass6_3835.aShort92 * Class92.lightX >> 8) >> 3;
            var3 = this.aClass6_3835.aShort94 - (this.aClass6_3835.aShort91 * Class92.lightX >> 8) >> 3;
         } else {
            var2 = this.aClass6_3835.aShort95 - (this.aClass6_3835.aShort91 * Class92.lightX >> 8) >> 3;
            var3 = this.aClass6_3835.aShort94 - (this.aClass6_3835.aShort92 * Class92.lightX >> 8) >> 3;
         }

         int var4;
         int var5;
         if(Class92.lightY > 0) {
            var4 = this.aClass6_3835.aShort97 - (this.aClass6_3835.aShort92 * Class92.lightY >> 8) >> 3;
            var5 = this.aClass6_3835.aShort96 - (this.aClass6_3835.aShort91 * Class92.lightY >> 8) >> 3;
         } else {
            var4 = this.aClass6_3835.aShort97 - (this.aClass6_3835.aShort91 * Class92.lightY >> 8) >> 3;
            var5 = this.aClass6_3835.aShort96 - (this.aClass6_3835.aShort92 * Class92.lightY >> 8) >> 3;
         }

         int var6 = var3 - var2 + 1;
         int var7 = var5 - var4 + 1;
         LDIndexedSprite var8;
         if(var1 != null && var1.aByteArray2674.length >= var6 * var7) {
            var8 = var1;
            var1.anInt1469 = var1.anInt1461 = var6;
            var1.anInt1467 = var1.anInt1468 = var7;
            var1.method1671();
         } else {
            var8 = new LDIndexedSprite(var6, var7);
         }

         var8.anInt1470 = var2;
         var8.anInt1464 = var4;
         if(anIntArray3861.length < this.anInt3833) {
            anIntArray3861 = new int[this.anInt3833];
            anIntArray3857 = new int[this.anInt3833];
         }

         int var9 = 0;

         int var13;
         int var14;
         int var15;
         while(var9 < this.anInt3823) {
            int var10 = (this.anIntArray3822[var9] - (this.anIntArray3845[var9] * Class92.lightX >> 8) >> 3) - var2;
            int var11 = (this.anIntArray3848[var9] - (this.anIntArray3845[var9] * Class92.lightY >> 8) >> 3) - var4;
            int var12 = this.anIntArray3838[var9];
            var13 = this.anIntArray3838[var9 + 1];
            var14 = var12;

            while(true) {
               if(var14 < var13) {
                  var15 = this.aShortArray3828[var14] - 1;
                  if(var15 != -1) {
                     anIntArray3861[var15] = var10;
                     anIntArray3857[var15] = var11;
                     ++var14;
                     continue;
                  }
               }

               ++var9;
               break;
            }
         }

         for(var9 = 0; var9 < this.anInt3852; ++var9) {
            short var20 = this.aShortArray3811[var9];
            short var19 = this.aShortArray3830[var9];
            short var21 = this.aShortArray3831[var9];
            var13 = anIntArray3861[var20];
            var14 = anIntArray3861[var19];
            var15 = anIntArray3861[var21];
            int var16 = anIntArray3857[var20];
            int var17 = anIntArray3857[var19];
            int var18 = anIntArray3857[var21];
            if((var13 - var14) * (var17 - var18) - (var17 - var16) * (var15 - var14) > 0) {
               Class51.method1147(var8.aByteArray2674, var16, var17, var18, var13, var14, var15, var6);
            }
         }

         return var8;
      }
   }

   final void method1900() {
      for(int var1 = 0; var1 < this.anInt3823; ++var1) {
         int var2 = this.anIntArray3848[var1];
         this.anIntArray3848[var1] = this.anIntArray3822[var1];
         this.anIntArray3822[var1] = -var2;
      }

      this.aClass6_3835.aBoolean98 = false;
      this.aClass121_3839.aBoolean1640 = false;
   }

   private Class140_Sub1_Sub1() {}

   Class140_Sub1_Sub1(Model_Sub1 var1, int var2, int var3, boolean var4) {
      int[] var5 = new int[var1.anInt2849];
      this.anIntArray3838 = new int[var1.anInt2887 + 1];

      for(int var6 = 0; var6 < var1.anInt2849; ++var6) {
         if((var1.aByteArray2859 == null || var1.aByteArray2859[var6] != 2) && (var1.aShortArray2858 == null || var1.aShortArray2858[var6] == -1 || !Class51.anInterface2_838.method12(var1.aShortArray2858[var6] & '\uffff', -65))) {
            var5[this.anInt3852++] = var6;
            ++this.anIntArray3838[var1.anIntArray2865[var6]];
            ++this.anIntArray3838[var1.anIntArray2878[var6]];
            ++this.anIntArray3838[var1.anIntArray2864[var6]];
         }
      }

      long[] var60 = new long[this.anInt3852];

      int var7;
      int var8;
      int var9;
      int var62;
      for(var7 = 0; var7 < this.anInt3852; ++var7) {
         var8 = var5[var7];
         var9 = 0;
         byte var10 = 0;
         int var11 = 0;
         int var12 = 0;
         short var13 = -1;
         if(var1.aShortArray2858 != null) {
            var13 = var1.aShortArray2858[var8];
            if(var13 != -1) {
               var11 = Class51.anInterface2_838.method18(var13 & '\uffff', 255);
               var12 = Class51.anInterface2_838.method9(var13 & '\uffff', false);
            }
         }

         boolean var14 = var1.aByteArray2843 != null && var1.aByteArray2843[var8] != 0 || var13 != -1 && !Class51.anInterface2_838.method7((byte)88, var13 & '\uffff');
         if((var4 || var14) && var1.aByteArray2889 != null) {
            var9 += var1.aByteArray2889[var8] << 17;
         }

         if(var14) {
            var9 += 65536;
         }

         var9 += (var11 & 255) << 8;
         var9 += var12 & 255;
         var62 = var10 + ((var13 & '\uffff') << 16);
         var62 += var7 & '\uffff';
         var60[var7] = ((long)var9 << 32) + (long)var62;
      }

      Class102.method1614(true, var60, var5);
      this.anInt3823 = var1.anInt2887;
      this.anIntArray3822 = var1.anIntArray2885;
      this.anIntArray3845 = var1.anIntArray2881;
      this.anIntArray3848 = var1.anIntArray2892;
      this.anIntArray3844 = var1.anIntArray2860;
      this.aShortArray3832 = var1.aShortArray2893;
      var7 = this.anInt3852 * 3;
      this.aShortArray3810 = new short[var7];
      this.aShortArray3826 = new short[var7];
      this.aShortArray3837 = new short[var7];
      this.aShortArray3841 = new short[var7];
      this.aFloatArray3824 = new float[var7];
      this.aFloatArray3847 = new float[var7];
      this.aShortArray3808 = new short[this.anInt3852];
      this.aByteArray3816 = new byte[this.anInt3852];
      this.aShortArray3811 = new short[this.anInt3852];
      this.aShortArray3830 = new short[this.anInt3852];
      this.aShortArray3831 = new short[this.anInt3852];
      this.aShortArray3827 = new short[this.anInt3852];
      if(var1.anIntArray2847 != null) {
         this.aByteArray3820 = new byte[this.anInt3852];
      }

      if(var1.aShortArray2855 != null) {
         this.aShortArray3813 = new short[this.anInt3852];
      }

      this.aClass6_3835 = new Class6();
      this.aClass121_3839 = new Class121();
      this.aClass121_3814 = new Class121();
      if(Class106.aBoolean1441) {
         this.aClass121_3815 = new Class121();
      }

      this.aClass121_3817 = new Class121();
      this.aClass121_3846 = new Class121();
      this.aShort3849 = (short)var2;
      this.aShort3819 = (short)var3;
      this.aShortArray3828 = new short[var7];
      aLongArray3821 = new long[var7];
      var8 = 0;

      for(var9 = 0; var9 < var1.anInt2887; ++var9) {
         var62 = this.anIntArray3838[var9];
         this.anIntArray3838[var9] = var8;
         var8 += var62;
      }

      this.anIntArray3838[var1.anInt2887] = var8;
      int[] var63 = null;
      int[] var61 = null;
      int[] var64 = null;
      float[][] var65 = (float[][])null;
      int var27;
      int var68;
      float var79;
      float var75;
      float var82;
      if(var1.aByteArray2866 != null) {
         var68 = var1.anInt2862;
         int[] var69 = new int[var68];
         int[] var15 = new int[var68];
         int[] var16 = new int[var68];
         int[] var17 = new int[var68];
         int[] var18 = new int[var68];
         int[] var19 = new int[var68];

         int var20;
         for(var20 = 0; var20 < var68; ++var20) {
            var69[var20] = Integer.MAX_VALUE;
            var15[var20] = -2147483647;
            var16[var20] = Integer.MAX_VALUE;
            var17[var20] = -2147483647;
            var18[var20] = Integer.MAX_VALUE;
            var19[var20] = -2147483647;
         }

         for(var20 = 0; var20 < this.anInt3852; ++var20) {
            int var21 = var5[var20];
            if(var1.aByteArray2866[var21] != -1) {
               int var22 = var1.aByteArray2866[var21] & 255;

               for(int var23 = 0; var23 < 3; ++var23) {
                  int var24;
                  if(var23 == 0) {
                     var24 = var1.anIntArray2865[var21];
                  } else if(var23 == 1) {
                     var24 = var1.anIntArray2878[var21];
                  } else {
                     var24 = var1.anIntArray2864[var21];
                  }

                  int var25 = var1.anIntArray2885[var24];
                  int var26 = var1.anIntArray2881[var24];
                  var27 = var1.anIntArray2892[var24];
                  if(var25 < var69[var22]) {
                     var69[var22] = var25;
                  }

                  if(var25 > var15[var22]) {
                     var15[var22] = var25;
                  }

                  if(var26 < var16[var22]) {
                     var16[var22] = var26;
                  }

                  if(var26 > var17[var22]) {
                     var17[var22] = var26;
                  }

                  if(var27 < var18[var22]) {
                     var18[var22] = var27;
                  }

                  if(var27 > var19[var22]) {
                     var19[var22] = var27;
                  }
               }
            }
         }

         var63 = new int[var68];
         var61 = new int[var68];
         var64 = new int[var68];
         var65 = new float[var68][];

         for(var20 = 0; var20 < var68; ++var20) {
            byte var76 = var1.aByteArray2857[var20];
            if(var76 > 0) {
               var63[var20] = (var69[var20] + var15[var20]) / 2;
               var61[var20] = (var16[var20] + var17[var20]) / 2;
               var64[var20] = (var18[var20] + var19[var20]) / 2;
               if(var76 == 1) {
                  short var80 = var1.aShortArray2888[var20];
                  if(var80 == 0) {
                     var75 = 1.0F;
                     var79 = 1.0F;
                  } else if(var80 > 0) {
                     var75 = 1.0F;
                     var79 = (float)var80 / 1024.0F;
                  } else {
                     var79 = 1.0F;
                     var75 = (float)(-var80) / 1024.0F;
                  }

                  var82 = 64.0F / (float)(var1.aShortArray2882[var20] & '\uffff');
               } else if(var76 == 2) {
                  var75 = 64.0F / (float)(var1.aShortArray2888[var20] & '\uffff');
                  var82 = 64.0F / (float)(var1.aShortArray2882[var20] & '\uffff');
                  var79 = 64.0F / (float)(var1.aShortArray2851[var20] & '\uffff');
               } else {
                  var75 = (float)var1.aShortArray2888[var20] / 1024.0F;
                  var82 = (float)var1.aShortArray2882[var20] / 1024.0F;
                  var79 = (float)var1.aShortArray2851[var20] / 1024.0F;
               }

               var65[var20] = method1906(var1.aShortArray2884[var20], var1.aShortArray2846[var20], var1.aShortArray2891[var20], var1.aByteArray2845[var20] & 255, var75, var82, var79);
            }
         }
      }

      short var71;
      int var67;
      for(var68 = 0; var68 < this.anInt3852; ++var68) {
         int var70 = var5[var68];
         var67 = var1.aShortArray2870[var70] & '\uffff';
         if(var1.aShortArray2858 == null) {
            var71 = -1;
         } else {
            var71 = var1.aShortArray2858[var70];
         }

         int var72;
         if(var1.aByteArray2866 == null) {
            var72 = -1;
         } else {
            var72 = var1.aByteArray2866[var70];
         }

         int var74;
         if(var1.aByteArray2843 == null) {
            var74 = 0;
         } else {
            var74 = var1.aByteArray2843[var70] & 255;
         }

         float var73 = 0.0F;
         float var78 = 0.0F;
         float var77 = 0.0F;
         var75 = 0.0F;
         var82 = 0.0F;
         var79 = 0.0F;
         byte var81 = 0;
         byte var83 = 0;
         var27 = 0;
         byte var28;
         int var31;
         int var87;
         if(var71 != -1) {
            if(var72 == -1) {
               var73 = 0.0F;
               var78 = 1.0F;
               var77 = 1.0F;
               var75 = 1.0F;
               var81 = 1;
               var82 = 0.0F;
               var79 = 0.0F;
               var83 = 2;
            } else {
               var72 &= 255;
               var28 = var1.aByteArray2857[var72];
               int var29;
               int var30;
               float var38;
               float var39;
               float var37;
               float var51;
               float var50;
               float var49;
               float var54;
               float var53;
               float var52;
               if(var28 == 0) {
                  var29 = var1.anIntArray2865[var70];
                  var30 = var1.anIntArray2878[var70];
                  var31 = var1.anIntArray2864[var70];
                  short var32 = var1.aShortArray2884[var72];
                  short var33 = var1.aShortArray2846[var72];
                  short var34 = var1.aShortArray2891[var72];
                  float var35 = (float)var1.anIntArray2885[var32];
                  float var36 = (float)var1.anIntArray2881[var32];
                  var37 = (float)var1.anIntArray2892[var32];
                  var38 = (float)var1.anIntArray2885[var33] - var35;
                  var39 = (float)var1.anIntArray2881[var33] - var36;
                  float var40 = (float)var1.anIntArray2892[var33] - var37;
                  float var41 = (float)var1.anIntArray2885[var34] - var35;
                  float var42 = (float)var1.anIntArray2881[var34] - var36;
                  float var43 = (float)var1.anIntArray2892[var34] - var37;
                  float var44 = (float)var1.anIntArray2885[var29] - var35;
                  float var45 = (float)var1.anIntArray2881[var29] - var36;
                  float var46 = (float)var1.anIntArray2892[var29] - var37;
                  float var47 = (float)var1.anIntArray2885[var30] - var35;
                  float var48 = (float)var1.anIntArray2881[var30] - var36;
                  var49 = (float)var1.anIntArray2892[var30] - var37;
                  var50 = (float)var1.anIntArray2885[var31] - var35;
                  var51 = (float)var1.anIntArray2881[var31] - var36;
                  var52 = (float)var1.anIntArray2892[var31] - var37;
                  var53 = var39 * var43 - var40 * var42;
                  var54 = var40 * var41 - var38 * var43;
                  float var55 = var38 * var42 - var39 * var41;
                  float var56 = var42 * var55 - var43 * var54;
                  float var57 = var43 * var53 - var41 * var55;
                  float var58 = var41 * var54 - var42 * var53;
                  float var59 = 1.0F / (var56 * var38 + var57 * var39 + var58 * var40);
                  var73 = (var56 * var44 + var57 * var45 + var58 * var46) * var59;
                  var77 = (var56 * var47 + var57 * var48 + var58 * var49) * var59;
                  var82 = (var56 * var50 + var57 * var51 + var58 * var52) * var59;
                  var56 = var39 * var55 - var40 * var54;
                  var57 = var40 * var53 - var38 * var55;
                  var58 = var38 * var54 - var39 * var53;
                  var59 = 1.0F / (var56 * var41 + var57 * var42 + var58 * var43);
                  var78 = (var56 * var44 + var57 * var45 + var58 * var46) * var59;
                  var75 = (var56 * var47 + var57 * var48 + var58 * var49) * var59;
                  var79 = (var56 * var50 + var57 * var51 + var58 * var52) * var59;
               } else {
                  var29 = var1.anIntArray2865[var70];
                  var30 = var1.anIntArray2878[var70];
                  var31 = var1.anIntArray2864[var70];
                  int var88 = var63[var72];
                  var87 = var61[var72];
                  int var94 = var64[var72];
                  float[] var93 = var65[var72];
                  byte var90 = var1.aByteArray2867[var72];
                  var37 = (float)var1.aByteArray2877[var72] / 256.0F;
                  if(var28 == 1) {
                     var38 = (float)(var1.aShortArray2851[var72] & '\uffff') / 1024.0F;
                     method1904(var1.anIntArray2885[var29], var1.anIntArray2881[var29], var1.anIntArray2892[var29], var88, var87, var94, var93, var38, var90, var37);
                     var73 = aFloat3863;
                     var78 = aFloat3866;
                     method1904(var1.anIntArray2885[var30], var1.anIntArray2881[var30], var1.anIntArray2892[var30], var88, var87, var94, var93, var38, var90, var37);
                     var77 = aFloat3863;
                     var75 = aFloat3866;
                     method1904(var1.anIntArray2885[var31], var1.anIntArray2881[var31], var1.anIntArray2892[var31], var88, var87, var94, var93, var38, var90, var37);
                     var82 = aFloat3863;
                     var79 = aFloat3866;
                     var39 = var38 / 2.0F;
                     if((var90 & 1) == 0) {
                        if(var77 - var73 > var39) {
                           var77 -= var38;
                           var81 = 1;
                        } else if(var73 - var77 > var39) {
                           var77 += var38;
                           var81 = 2;
                        }

                        if(var82 - var73 > var39) {
                           var82 -= var38;
                           var83 = 1;
                        } else if(var73 - var82 > var39) {
                           var82 += var38;
                           var83 = 2;
                        }
                     } else {
                        if(var75 - var78 > var39) {
                           var75 -= var38;
                           var81 = 1;
                        } else if(var78 - var75 > var39) {
                           var75 += var38;
                           var81 = 2;
                        }

                        if(var79 - var78 > var39) {
                           var79 -= var38;
                           var83 = 1;
                        } else if(var78 - var79 > var39) {
                           var79 += var38;
                           var83 = 2;
                        }
                     }
                  } else if(var28 == 2) {
                     var38 = (float)var1.aByteArray2852[var72] / 256.0F;
                     var39 = (float)var1.aByteArray2869[var72] / 256.0F;
                     int var97 = var1.anIntArray2885[var30] - var1.anIntArray2885[var29];
                     int var96 = var1.anIntArray2881[var30] - var1.anIntArray2881[var29];
                     int var101 = var1.anIntArray2892[var30] - var1.anIntArray2892[var29];
                     int var102 = var1.anIntArray2885[var31] - var1.anIntArray2885[var29];
                     int var103 = var1.anIntArray2881[var31] - var1.anIntArray2881[var29];
                     int var98 = var1.anIntArray2892[var31] - var1.anIntArray2892[var29];
                     int var99 = var96 * var98 - var103 * var101;
                     int var100 = var101 * var102 - var98 * var97;
                     int var104 = var97 * var103 - var102 * var96;
                     var49 = 64.0F / (float)(var1.aShortArray2888[var72] & '\uffff');
                     var50 = 64.0F / (float)(var1.aShortArray2882[var72] & '\uffff');
                     var51 = 64.0F / (float)(var1.aShortArray2851[var72] & '\uffff');
                     var52 = ((float)var99 * var93[0] + (float)var100 * var93[1] + (float)var104 * var93[2]) / var49;
                     var53 = ((float)var99 * var93[3] + (float)var100 * var93[4] + (float)var104 * var93[5]) / var50;
                     var54 = ((float)var99 * var93[6] + (float)var100 * var93[7] + (float)var104 * var93[8]) / var51;
                     var27 = method1901(var52, var53, var54);
                     method1913(var1.anIntArray2885[var29], var1.anIntArray2881[var29], var1.anIntArray2892[var29], var88, var87, var94, var27, var93, var90, var37, var38, var39);
                     var73 = aFloat3864;
                     var78 = aFloat3858;
                     method1913(var1.anIntArray2885[var30], var1.anIntArray2881[var30], var1.anIntArray2892[var30], var88, var87, var94, var27, var93, var90, var37, var38, var39);
                     var77 = aFloat3864;
                     var75 = aFloat3858;
                     method1913(var1.anIntArray2885[var31], var1.anIntArray2881[var31], var1.anIntArray2892[var31], var88, var87, var94, var27, var93, var90, var37, var38, var39);
                     var82 = aFloat3864;
                     var79 = aFloat3858;
                  } else if(var28 == 3) {
                     method1910(var1.anIntArray2885[var29], var1.anIntArray2881[var29], var1.anIntArray2892[var29], var88, var87, var94, var93, var90, var37);
                     var73 = aFloat3867;
                     var78 = aFloat3860;
                     method1910(var1.anIntArray2885[var30], var1.anIntArray2881[var30], var1.anIntArray2892[var30], var88, var87, var94, var93, var90, var37);
                     var77 = aFloat3867;
                     var75 = aFloat3860;
                     method1910(var1.anIntArray2885[var31], var1.anIntArray2881[var31], var1.anIntArray2892[var31], var88, var87, var94, var93, var90, var37);
                     var82 = aFloat3867;
                     var79 = aFloat3860;
                     if((var90 & 1) == 0) {
                        if(var77 - var73 > 0.5F) {
                           --var77;
                           var81 = 1;
                        } else if(var73 - var77 > 0.5F) {
                           ++var77;
                           var81 = 2;
                        }

                        if(var82 - var73 > 0.5F) {
                           --var82;
                           var83 = 1;
                        } else if(var73 - var82 > 0.5F) {
                           ++var82;
                           var83 = 2;
                        }
                     } else {
                        if(var75 - var78 > 0.5F) {
                           --var75;
                           var81 = 1;
                        } else if(var78 - var75 > 0.5F) {
                           ++var75;
                           var81 = 2;
                        }

                        if(var79 - var78 > 0.5F) {
                           --var79;
                           var83 = 1;
                        } else if(var78 - var79 > 0.5F) {
                           ++var79;
                           var83 = 2;
                        }
                     }
                  }
               }
            }
         }

         var1.method1997();
         if(var1.aByteArray2859 == null) {
            var28 = 0;
         } else {
            var28 = var1.aByteArray2859[var70];
         }

         if(var28 == 0) {
            long var86 = (long)(var72 << 2) + ((long)(var27 << 24) + (long)(var67 << 8) + (long)var74 << 32);
            var31 = var1.anIntArray2865[var70];
            Class50 var89 = var1.aClass50Array2872[var31];
            this.aShortArray3811[var68] = this.method1907(var31, var86, var89.anInt831, var89.anInt821, var89.anInt830, var89.anInt823, var73, var78);
            var87 = var1.anIntArray2878[var70];
            Class50 var95 = var1.aClass50Array2872[var87];
            this.aShortArray3830[var68] = this.method1907(var87, var86 + (long)var81, var95.anInt831, var95.anInt821, var95.anInt830, var95.anInt823, var77, var75);
            int var92 = var1.anIntArray2864[var70];
            Class50 var91 = var1.aClass50Array2872[var92];
            this.aShortArray3831[var68] = this.method1907(var92, var86 + (long)var83, var91.anInt831, var91.anInt821, var91.anInt830, var91.anInt823, var82, var79);
         } else if(var28 == 1) {
            Class120 var85 = var1.aClass120Array2886[var70];
            long var84 = (long)((var72 << 2) + (var85.anInt1634 > 0?1024:2048) + (var85.anInt1635 + 256 << 12) + (var85.anInt1632 + 256 << 22)) + ((long)(var27 << 24) + (long)(var67 << 8) + (long)var74 << 32);
            this.aShortArray3811[var68] = this.method1907(var1.anIntArray2865[var70], var84, var85.anInt1634, var85.anInt1635, var85.anInt1632, 0, var73, var78);
            this.aShortArray3830[var68] = this.method1907(var1.anIntArray2878[var70], var84 + (long)var81, var85.anInt1634, var85.anInt1635, var85.anInt1632, 0, var77, var75);
            this.aShortArray3831[var68] = this.method1907(var1.anIntArray2864[var70], var84 + (long)var83, var85.anInt1634, var85.anInt1635, var85.anInt1632, 0, var82, var79);
         }

         if(var1.aShortArray2858 == null) {
            this.aShortArray3827[var68] = -1;
         } else {
            this.aShortArray3827[var68] = var1.aShortArray2858[var70];
         }

          if(this.aByteArray3820 != null) {
            this.aByteArray3820[var68] = (byte)var1.anIntArray2847[var70];
         }

         this.aShortArray3808[var68] = var1.aShortArray2870[var70];
         if(var1.aByteArray2843 != null) {
            this.aByteArray3816[var68] = var1.aByteArray2843[var70];
         }

         if(var1.aShortArray2855 != null) {
            this.aShortArray3813[var68] = var1.aShortArray2855[var70];
         }
      }

      var68 = 0;
      short var66 = -10000;

      for(var67 = 0; var67 < this.anInt3852; ++var67) {
         var71 = this.aShortArray3827[var67];
         if(var71 != var66) {
            ++var68;
            var66 = var71;
         }
      }

      this.anIntArray3840 = new int[var68 + 1];
      var68 = 0;
      var66 = -10000;

      for(var67 = 0; var67 < this.anInt3852; ++var67) {
         var71 = this.aShortArray3827[var67];
         if(var71 != var66) {
            this.anIntArray3840[var68++] = var67;
            var66 = var71;
         }
      }

      this.anIntArray3840[var68] = this.anInt3852;
      aLongArray3821 = null;
      this.aShortArray3810 = method1928(this.aShortArray3810, this.anInt3833);
      this.aShortArray3826 = method1928(this.aShortArray3826, this.anInt3833);
      this.aShortArray3837 = method1928(this.aShortArray3837, this.anInt3833);
      this.aShortArray3841 = method1928(this.aShortArray3841, this.anInt3833);
      this.aFloatArray3824 = method1921(this.aFloatArray3824, this.anInt3833);
      this.aFloatArray3847 = method1921(this.aFloatArray3847, this.anInt3833);
   }

}
