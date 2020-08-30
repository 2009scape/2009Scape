package org.runite.jagex;

abstract class Class140_Sub4 extends GameObject {

   int textEffect = 0;
   private int anInt2754 = 0;
   int[] anIntArray2755 = new int[10];
   static int anInt2756;
   private int anInt2757 = 0;
   int anInt2758 = 0;
   int anInt2759;
   int anInt2760 = 0;
   int anInt2761 = 0;
   int anInt2762 = 0;
   int renderAnimationId = -1;
   int anInt2764 = -1;
   static RSString aClass94_2765 = RSString.createRSString(" ");
   private int anInt2766 = 0;
   int[] anIntArray2767 = new int[10];
   int[] anIntArray2768 = new int[4];
   boolean aBoolean2769 = false;
   int anInt2771 = -1;
   int anInt2772 = -1;
   int anInt2773 = 0;
   static boolean aBoolean2774 = true;
   int anInt2775;
   int anInt2776 = -1;
   int anInt2777;
   int anInt2778 = 0;
   int anInt2779 = 32;
   int anInt2780 = 0;
   int anInt2781 = -1000;
   int anInt2782;
   private boolean aBoolean2783 = false;
   int anInt2784;
   int anInt2785;
   int anInt2786 = 0;
   private int anInt2787 = 0;
   int anInt2788;
   int anInt2789 = 0;
   int anInt2790;
   int anInt2791 = 0;
   static Class93 aClass93_2792 = new Class93(64);
   int anInt2793 = -1;
   static int[] anIntArray2794 = new int[100];
   byte[] aByteArray2795 = new byte[10];
   Object anObject2796;
   int anInt2797 = 0;
   int anInt2798;
   int anInt2799;
   int anInt2800;
   Class127_Sub1 aClass127_Sub1_2801;
   int anInt2802 = 0;
   int anInt2803 = 0;
   private int anInt2804 = 0;
   int anInt2805 = 0;
   int anInt2806;
   private boolean aBoolean2807 = false;
   int anInt2808 = 0;
   Class145[] aClass145Array2809 = new Class145[12];
   boolean aBoolean2810 = false;
   int anInt2811 = 0;
   int anInt2812;
   int anInt2813 = 0;
   int textCycle = 100;
   int[] anIntArray2815 = new int[4];
   int anInt2816 = 0;
   int anInt2817;
   int anInt2818;
   int anInt2819;
   int anInt2820 = -32768;
   int anInt2821 = 0;
   private int size = 1;
   int anInt2823;
   int anInt2824 = 0;
   RSString textSpoken = null;
   int anInt2826 = -1;
   private int anInt2827 = 0;
   int anInt2828 = 0;
   int anInt2829;
   private int anInt2830 = 0;
   int anInt2831;
   int anInt2832 = 0;
   int anInt2833;
   private int anInt2834 = 0;
   int anInt2835;
   int[] anIntArray2836 = new int[4];
   int textColor = 0;
   int anInt2838 = 0;
   static Class3_Sub28_Sub16[] aClass3_Sub28_Sub16Array2839;
   int anInt2840;
   int anInt2842 = -1;


   final RenderAnimationDefinition method1965() {
      try {
         int var2 = this.getRenderAnimationId();
         return (var2 == -1 ?PacketParser.aClass16_84:Class3_Sub10.getRenderAnimationDefinition(var2));
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "fe.D(" + false + ')');
      }
   }

   boolean hasDefinitions() {
      try {

          return false;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "fe.L(" + (byte) 17 + ')');
      }
   }

   final void method1967(int var2, int var3, int var4, boolean var5) {
      try {
         if(this.anInt2771 != -1 && Client.getAnimationDefinition(this.anInt2771).anInt1850 == 1) {
            this.anInt2771 = -1;
         }

         if(!var5) {
            int var6 = var3 + -this.anIntArray2767[0];
            int var7 = var4 + -this.anIntArray2755[0];
            if(var6 >= -8 && 8 >= var6 && var7 >= -8 && var7 <= 8) {
               if(this.anInt2816 < 9) {
                  ++this.anInt2816;
               }

               for(int var8 = this.anInt2816; var8 > 0; --var8) {
                  this.anIntArray2767[var8] = this.anIntArray2767[-1 + var8];
                  this.anIntArray2755[var8] = this.anIntArray2755[var8 + -1];
                  this.aByteArray2795[var8] = this.aByteArray2795[var8 - 1];
               }

               this.aByteArray2795[0] = 1;
               this.anIntArray2767[0] = var3;
               this.anIntArray2755[0] = var4;
               return;
            }
         }

         this.anInt2824 = 0;
         this.anIntArray2767[0] = var3;
         this.anIntArray2755[0] = var4;
         this.anInt2816 = 0;
         this.anInt2811 = 0;
         this.anInt2829 = 64 * var2 + this.anIntArray2755[0] * 128;
         this.anInt2819 = var2 * 64 + 128 * this.anIntArray2767[0];

         if(HDToolKit.highDetail && Class102.player == this) {
            Class3_Sub13_Sub14.method236();
         }

      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "fe.J(" + -2 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
      }
   }

   final void walkStep(int var1, byte var2, int var3) {
      try {
         int var4 = this.anIntArray2767[0];
         int var5 = this.anIntArray2755[0];
         if(0 == var3) {
            --var4;
            ++var5;
         }

         if(this.anInt2771 != -1 && 1 == Client.getAnimationDefinition(this.anInt2771).anInt1850) {
            this.anInt2771 = -1;
         }

         if(this.anInt2816 < 9) {
            ++this.anInt2816;
         }

         int var6;
         for(var6 = this.anInt2816; var6 > 0; --var6) {
            this.anIntArray2767[var6] = this.anIntArray2767[-1 + var6];
            this.anIntArray2755[var6] = this.anIntArray2755[-1 + var6];
            this.aByteArray2795[var6] = this.aByteArray2795[-1 + var6];
         }

         if(var3 == 1) {
            ++var5;
         }

         this.aByteArray2795[0] = (byte)var1;
         if(2 == var3) {
            ++var5;
            ++var4;
         }

         if(var3 == 3) {
            --var4;
         }

         if(4 == var3) {
            ++var4;
         }

         if(5 == var3) {
            --var5;
            --var4;
         }

         if(var3 == 6) {
            --var5;
         }

         if(var3 == 7) {
            --var5;
            ++var4;
         }

         this.anIntArray2767[0] = var4;
         this.anIntArray2755[0] = var5;
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "fe.E(" + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   final void method1969(byte var1, Model var2, int var3) {
      try {
         if(var1 <= 33) {
            this.method1971((Model)null, (byte)-26);
         }

         Class3_Sub28_Sub9.anInt3623 = 0;
         Class3_Sub13_Sub16.anInt3198 = 0;
         Class3_Sub13_Sub9.anInt3111 = 0;
         RenderAnimationDefinition var4 = this.method1965();
         int var5 = var4.anInt395;
         int var6 = var4.anInt381;
         if(var5 != 0 && var6 != 0) {
            int var7 = Class51.anIntArray840[var3];
            int var8 = Class51.anIntArray851[var3];
            int var9 = -var5 / 2;
            int var10 = -var6 / 2;
            int var12 = -(var9 * var7) + var10 * var8 >> 16;
            int var11 = var7 * var10 - -(var8 * var9) >> 16;
            int var13 = Class121.method1736(WorldListCountry.localPlane, 1, var11 + this.anInt2819, this.anInt2829 + var12);
            int var14 = var5 / 2;
            int var15 = -var6 / 2;
            int var16 = var14 * var8 + var15 * var7 >> 16;
            int var20 = var6 / 2;
            int var17 = var15 * var8 + -(var14 * var7) >> 16;
            int var18 = Class121.method1736(WorldListCountry.localPlane, 1, var16 + this.anInt2819, this.anInt2829 - -var17);
            int var19 = -var5 / 2;
            int var22 = -(var7 * var19) + var20 * var8 >> 16;
            int var25 = var6 / 2;
            int var24 = var5 / 2;
            int var21 = var7 * var20 - -(var8 * var19) >> 16;
            int var27 = var25 * var8 - var7 * var24 >> 16;
            int var26 = var7 * var25 + var8 * var24 >> 16;
            int var23 = Class121.method1736(WorldListCountry.localPlane, 1, this.anInt2819 + var21, var22 + this.anInt2829);
            int var29 = var13 < var18 ?var13:var18;
            int var28 = Class121.method1736(WorldListCountry.localPlane, 1, var26 + this.anInt2819, var27 + this.anInt2829);
            int var30 = var28 > var23?var23:var28;
            int var31 = var28 > var18?var18:var28;
            int var32 = var23 <= var13?var23:var13;
            Class3_Sub13_Sub16.anInt3198 = 2047 & (int)(325.95D * Math.atan2((double)(var29 - var30), (double)var6));
            if(Class3_Sub13_Sub16.anInt3198 != 0) {
               var2.method1896(Class3_Sub13_Sub16.anInt3198);
            }

            Class3_Sub28_Sub9.anInt3623 = 2047 & (int)(325.95D * Math.atan2((double)(-var31 + var32), (double)var5));
            if(Class3_Sub28_Sub9.anInt3623 != 0) {
               var2.method1886(Class3_Sub28_Sub9.anInt3623);
            }

            Class3_Sub13_Sub9.anInt3111 = var28 + var13;
            if(Class3_Sub13_Sub9.anInt3111 > var23 + var18) {
               Class3_Sub13_Sub9.anInt3111 = var23 + var18;
            }

            Class3_Sub13_Sub9.anInt3111 = (Class3_Sub13_Sub9.anInt3111 >> 1) - this.anInt2831;
            if(Class3_Sub13_Sub9.anInt3111 != 0) {
               var2.method1897(0, Class3_Sub13_Sub9.anInt3111, 0);
            }
         }

      } catch (RuntimeException var33) {
         throw Class44.clientError(var33, "fe.M(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

   final void method1970(int var1, int var3, int var4) {
      try {
         for(int var5 = 0; var5 < 4; ++var5) {
            if(var3 >= this.anIntArray2768[var5]) {
               this.anIntArray2836[var5] = var4;
               this.anIntArray2815[var5] = var1;
               this.anIntArray2768[var5] = 70 + var3;
               return;
            }
         }

      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "fe.G(" + var1 + ',' + -8 + ',' + var3 + ',' + var4 + ')');
      }
   }

   final void method1971(Model var1, byte var2) {
      try {
         if(var2 > -102) {
            this.setSize(1, -96);
         }

         RenderAnimationDefinition var3 = this.method1965();
         if(0 != var3.anInt400 || 0 != var3.anInt371) {
            int var4 = 0;
            int var5 = 0;
            if(this.aBoolean2769 && this.anInt2821 != 0) {
               var5 = var3.anInt371;
               if(this.anInt2821 >= 0) {
                  var4 = var3.anInt400;
               } else {
                  var4 = -var3.anInt400;
               }
            }

            int var6;
            int var7;
            int var8;
            int var9;
            if(this.anInt2827 != var4) {
               this.anInt2827 = var4;
               if(this.anInt2754 > 0 && this.anInt2787 < var4) {
                  var6 = this.anInt2754 * this.anInt2754 / (2 * var3.anInt387);
                  var7 = -this.anInt2787 + var4;
                  if(var6 <= var7) {
                     this.aBoolean2807 = true;
                     this.anInt2804 = (var4 + this.anInt2787 - var6) / 2;
                     var8 = var3.anInt370 * var3.anInt370 / (var3.anInt387 * 2);
                     var9 = -var8 + var4;
                     if(this.anInt2804 < var9) {
                        this.anInt2804 = var9;
                     }
                  } else {
                     this.aBoolean2807 = false;
                  }
               } else if(this.anInt2754 < 0 && var4 < this.anInt2787) {
                  var6 = this.anInt2754 * this.anInt2754 / (var3.anInt387 * 2);
                  var7 = var4 - this.anInt2787;
                  if(var7 >= var6) {
                     this.aBoolean2807 = true;
                     this.anInt2804 = (this.anInt2787 + var6 - -var4) / 2;
                     var8 = var3.anInt370 * var3.anInt370 / (2 * var3.anInt387);
                     var9 = var8 + var4;
                     if(this.anInt2804 > var9) {
                        this.anInt2804 = var9;
                     }
                  } else {
                     this.aBoolean2807 = false;
                  }
               } else {
                  this.aBoolean2807 = false;
               }
            }

            if(this.anInt2754 == 0) {
               var6 = -this.anInt2787 + this.anInt2827;
               if(-var3.anInt387 < var6 && var6 < var3.anInt387) {
                  this.anInt2787 = this.anInt2827;
               } else {
                  this.aBoolean2807 = true;
                  var7 = var3.anInt370 * var3.anInt370 / (var3.anInt387 * 2);
                  this.anInt2804 = (this.anInt2827 + this.anInt2787) / 2;
                  if(var6 >= 0) {
                     var8 = -var7 + this.anInt2827;
                     this.anInt2754 = var3.anInt387;
                     if(this.anInt2804 < var8) {
                        this.anInt2804 = var8;
                     }
                  } else {
                     this.anInt2754 = -var3.anInt387;
                     var8 = var7 + this.anInt2827;
                     if(var8 < this.anInt2804) {
                        this.anInt2804 = var8;
                     }
                  }
               }
            } else if (this.anInt2754 > 0) {
               if (this.anInt2804 <= this.anInt2787) {
                  this.aBoolean2807 = false;
               }

               if (!this.aBoolean2807) {
                  this.anInt2754 -= var3.anInt387;
                  if (this.anInt2754 < 0) {
                     this.anInt2754 = 0;
                  }
               } else if (var3.anInt370 > this.anInt2754) {
                  this.anInt2754 += var3.anInt387;
               }
            } else {
               if (this.anInt2787 <= this.anInt2804) {
                  this.aBoolean2807 = false;
               }

               if (this.aBoolean2807) {
                  if (-var3.anInt370 < this.anInt2754) {
                     this.anInt2754 -= var3.anInt387;
                  }
               } else {
                  this.anInt2754 += var3.anInt387;
                  if (this.anInt2754 > 0) {
                     this.anInt2754 = 0;
                  }
               }
            }

            this.anInt2787 += this.anInt2754;
            if(this.anInt2787 != 0) {
               var6 = (this.anInt2787 & '\ufff1') >> 5;
               var7 = var1.method1871() / 2;
               var1.method1897(0, -var7, 0);
               var1.method1886(var6);
               var1.method1897(0, var7, 0);
            }

            if(var5 != this.anInt2766) {
               this.anInt2766 = var5;
               if(this.anInt2830 > 0 && this.anInt2834 < var5) {
                  var6 = this.anInt2830 * this.anInt2830 / (2 * var3.anInt403);
                  var7 = -this.anInt2834 + var5;
                  if(var6 > var7) {
                     this.aBoolean2783 = false;
                  } else {
                     this.anInt2757 = (-var6 + this.anInt2834 + var5) / 2;
                     this.aBoolean2783 = true;
                     var8 = var3.anInt399 * var3.anInt399 / (2 * var3.anInt403);
                     var9 = var5 + -var8;
                     if(var9 > this.anInt2757) {
                        this.anInt2757 = var9;
                     }
                  }
               } else if(this.anInt2830 < 0 && this.anInt2834 > var5) {
                  var7 = -this.anInt2834 + var5;
                  var6 = this.anInt2830 * this.anInt2830 / (2 * var3.anInt403);
                  if(var7 >= var6) {
                     this.anInt2757 = (var6 + this.anInt2834 - -var5) / 2;
                     this.aBoolean2783 = true;
                     var8 = var3.anInt399 * var3.anInt399 / (2 * var3.anInt403);
                     var9 = var8 + var5;
                     if(var9 < this.anInt2757) {
                        this.anInt2757 = var9;
                     }
                  } else {
                     this.aBoolean2783 = false;
                  }
               } else {
                  this.aBoolean2783 = false;
               }
            }

            if(this.anInt2830 == 0) {
               var6 = -this.anInt2834 + this.anInt2766;
               if(-var3.anInt403 < var6 && var3.anInt403 > var6) {
                  this.anInt2834 = this.anInt2766;
               } else {
                  this.anInt2757 = (this.anInt2766 + this.anInt2834) / 2;
                  this.aBoolean2783 = true;
                  var7 = var3.anInt399 * var3.anInt399 / (2 * var3.anInt403);
                  if(var6 < 0) {
                     this.anInt2830 = -var3.anInt403;
                     var8 = var7 + this.anInt2766;
                     if(this.anInt2757 > var8) {
                        this.anInt2757 = var8;
                     }
                  } else {
                     this.anInt2830 = var3.anInt403;
                     var8 = -var7 + this.anInt2766;
                     if(var8 > this.anInt2757) {
                        this.anInt2757 = var8;
                     }
                  }
               }
            } else if (this.anInt2830 > 0) {
               if (this.anInt2757 <= this.anInt2834) {
                  this.aBoolean2783 = false;
               }

               if (this.aBoolean2783) {
                  if (var3.anInt399 > this.anInt2830) {
                     this.anInt2830 += var3.anInt403;
                  }
               } else {
                  this.anInt2830 -= var3.anInt403;
                  if (0 > this.anInt2830) {
                     this.anInt2830 = 0;
                  }
               }
            } else {
               if (this.anInt2757 >= this.anInt2834) {
                  this.aBoolean2783 = false;
               }

               if (this.aBoolean2783) {
                  if (this.anInt2830 > -var3.anInt399) {
                     this.anInt2830 -= var3.anInt403;
                  }
               } else {
                  this.anInt2830 += var3.anInt403;
                  if (0 < this.anInt2830) {
                     this.anInt2830 = 0;
                  }
               }
            }

            this.anInt2834 += this.anInt2830;
            if(this.anInt2834 != 0) {
               var6 = (this.anInt2834 & '\uffe6') >> 5;
               var7 = var1.method1871() / 2;
               var1.method1897(0, -var7, 0);
               var1.method1896(var6);
               var1.method1897(0, var7, 0);
            }
         }

      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "fe.A(" + (var1 != null?"{...}":"null") + ',' + var2 + ')');
      }
   }

   abstract int getRenderAnimationId();

   final void method1973(int var1) {
      try {
         if(var1 < -75) {
            this.anInt2816 = 0;
            this.anInt2811 = 0;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "fe.I(" + var1 + ')');
      }
   }

   public static void method1974(byte var0) {
      try {
         if(var0 > -101) {
            aClass3_Sub28_Sub16Array2839 = (Class3_Sub28_Sub16[])null;
         }

         ItemDefinition.methodStack = null;
         aClass94_2765 = null;
         aClass93_2792 = null;
         aClass3_Sub28_Sub16Array2839 = null;
         anIntArray2794 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "fe.K(" + var0 + ')');
      }
   }

   final int method1975(int var1) {
      try {
         return var1 != 27855?107:(this.anInt2820 != -32768?-this.anInt2820:200);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "fe.F(" + var1 + ')');
      }
   }

   final void setSize(int var1, int var2) {
      try {
         this.size = var1;
         if(var2 != 2) {
            this.aBoolean2807 = false;
         }

      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "fe.C(" + var1 + ',' + var2 + ')');
      }
   }

   int getSize() {
      try {
         return this.size;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "fe.H(" + (byte) 114 + ')');
      }
   }

}
