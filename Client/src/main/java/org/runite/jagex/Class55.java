package org.runite.jagex;

final class Class55 {

   int anInt879;
   int anInt881;
   static int[] anIntArray882 = new int[200];
   private int anInt883;


   final Class3_Sub28_Sub16_Sub2 method1179() {
      try {
         Class3_Sub28_Sub16_Sub2 var2 = (Class3_Sub28_Sub16_Sub2)Class163_Sub1.aClass93_2984.get((long)this.anInt883);
         if(null == var2) {
            var2 = Class3_Sub28_Sub7.method562(AnimationDefinition.aClass153_1852, this.anInt883);
            if(var2 != null) {
               Class163_Sub1.aClass93_2984.put((byte)-87, var2, (long)this.anInt883);
            }

            return var2;
         } else {
            return var2;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ia.F(" + (byte) 95 + ')');
      }
   }

   static void method1180(byte var0, Class140_Sub4 var1) {
      try {
         RenderAnimationDefinition var2 = var1.method1965();
         var1.anInt2764 = var2.anInt368;
         if(var0 >= -5) {
            anIntArray882 = (int[])null;
         }

         if(var1.anInt2816 == 0) {
            var1.anInt2824 = 0;
         } else {
            if(var1.anInt2771 != -1 && 0 == var1.anInt2828) {
               AnimationDefinition var3 = Client.getAnimationDefinition(var1.anInt2771);
               if(var1.anInt2811 > 0 && var3.anInt1866 == 0) {
                  ++var1.anInt2824;
                  return;
               }

               if(var1.anInt2811 <= 0 && var3.anInt1850 == 0) {
                  ++var1.anInt2824;
                  return;
               }
            }

            int var18 = var1.anInt2819;
            int var4 = var1.anInt2829;
            int var5 = var1.anIntArray2767[-1 + var1.anInt2816] * 128 - -(var1.getSize() * 64);
            int var6 = 128 * var1.anIntArray2755[-1 + var1.anInt2816] - -(var1.getSize() * 64);
            if(var5 + -var18 > 256 || -var18 + var5 < -256 || var6 - var4 > 256 || -256 > var6 - var4) {
               var1.anInt2819 = var5;
               var1.anInt2829 = var6;
               return;
            }

            if(var5 <= var18) {
               if(var18 <= var5) {
                  if(var6 <= var4) {
                     if(var6 < var4) {
                        var1.anInt2806 = 0;
                     }
                  } else {
                     var1.anInt2806 = 1024;
                  }
               } else if(var6 > var4) {
                  var1.anInt2806 = 768;
               } else if(var6 < var4) {
                  var1.anInt2806 = 256;
               } else {
                  var1.anInt2806 = 512;
               }
            } else if(var6 > var4) {
               var1.anInt2806 = 1280;
            } else if(var4 > var6) {
               var1.anInt2806 = 1792;
            } else {
               var1.anInt2806 = 1536;
            }

            int var7 = 2047 & -var1.anInt2785 + var1.anInt2806;
            int var8 = var2.anInt389;
            if(1024 < var7) {
               var7 -= 2048;
            }

            boolean var10 = true;
            byte var11 = 1;
            if(var7 >= -256 && var7 <= 256) {
               var8 = var2.anInt382;
            } else if(var7 >= 256 && 768 > var7) {
               var8 = var2.anInt364;
            } else if(var7 >= -768 && -256 >= var7) {
               var8 = var2.anInt390;
            }

            int var9 = 4;
            if(var8 == -1) {
               var8 = var2.anInt382;
            }

            var1.anInt2764 = var8;
            if(var1 instanceof NPC) {
               var10 = ((NPC)var1).definition.aBoolean1255;
            }

            if(var10) {
               if(var1.anInt2806 != var1.anInt2785 && var1.anInt2772 == -1 && var1.anInt2779 != 0) {
                  var9 = 2;
               }

               if(2 < var1.anInt2816) {
                  var9 = 6;
               }

               if(3 < var1.anInt2816) {
                  var9 = 8;
               }

               if(var1.anInt2824 > 0 && var1.anInt2816 > 1) {
                  var9 = 8;
                  --var1.anInt2824;
               }
            } else {
               if(1 < var1.anInt2816) {
                  var9 = 6;
               }

               if(var1.anInt2816 > 2) {
                  var9 = 8;
               }

               if(var1.anInt2824 > 0 && var1.anInt2816 > 1) {
                  --var1.anInt2824;
                  var9 = 8;
               }
            }

            if(2 == var1.aByteArray2795[var1.anInt2816 - 1]) {
               var9 <<= 1;
               var11 = 2;
            } else if (var1.aByteArray2795[-1 + var1.anInt2816] == 0) {
               var11 = 0;
               var9 >>= 1;
            }

            if(var9 >= 8 && -1 != var2.anInt393) {
               if(var1.anInt2764 == var2.anInt389 && -1 != var2.anInt386) {
                  var1.anInt2764 = var2.anInt386;
               } else if(var1.anInt2764 == var2.anInt390 && var2.anInt373 != -1) {
                  var1.anInt2764 = var2.anInt373;
               } else if(var2.anInt364 == var1.anInt2764 && var2.anInt375 != -1) {
                  var1.anInt2764 = var2.anInt375;
               } else {
                  var1.anInt2764 = var2.anInt393;
               }
            } else if(var2.anInt398 != -1 && var11 == 0) {
               if(var1.anInt2764 == var2.anInt389 && var2.anInt372 != -1) {
                  var1.anInt2764 = var2.anInt372;
               } else if(var1.anInt2764 == var2.anInt390 && -1 != var2.anInt406) {
                  var1.anInt2764 = var2.anInt406;
               } else if(var2.anInt364 == var1.anInt2764 && -1 != var2.anInt379) {
                  var1.anInt2764 = var2.anInt379;
               } else {
                  var1.anInt2764 = var2.anInt398;
               }
            }

            if(var2.anInt360 != -1) {
               var9 <<= 7;
               if(var1.anInt2816 == 1) {
                  int var13 = (var1.anInt2819 <= var5 ?var5 - var1.anInt2819:-var5 + var1.anInt2819) << 7;
                  int var12 = var1.anInt2758 * var1.anInt2758;
                  int var14 = (var1.anInt2829 > var6 ?-var6 + var1.anInt2829:-var1.anInt2829 + var6) << 7;
                  int var15 = var13 > var14?var13:var14;
                  int var16 = var2.anInt360 * 2 * var15;
                  if(var12 <= var16) {
                     if(var12 / 2 > var15) {
                        var1.anInt2758 -= var2.anInt360;
                        if(var1.anInt2758 < 0) {
                           var1.anInt2758 = 0;
                        }
                     } else if(var1.anInt2758 < var9) {
                        var1.anInt2758 += var2.anInt360;
                        if(var1.anInt2758 > var9) {
                           var1.anInt2758 = var9;
                        }
                     }
                  } else {
                     var1.anInt2758 /= 2;
                  }
               } else if(var1.anInt2758 < var9) {
                  var1.anInt2758 += var2.anInt360;
                  if(var1.anInt2758 > var9) {
                     var1.anInt2758 = var9;
                  }
               } else {
                  var1.anInt2758 -= var2.anInt360;
                  if(0 > var1.anInt2758) {
                     var1.anInt2758 = 0;
                  }
               }

               var9 = var1.anInt2758 >> 7;
               if(var9 < 1) {
                  var9 = 1;
               }
            }

            if(var5 > var18) {
               var1.anInt2819 += var9;
               if(var1.anInt2819 > var5) {
                  var1.anInt2819 = var5;
               }
            } else if(var18 > var5) {
               var1.anInt2819 -= var9;
               if(var1.anInt2819 < var5) {
                  var1.anInt2819 = var5;
               }
            }

            if(var4 >= var6) {
               if(var6 < var4) {
                  var1.anInt2829 -= var9;
                  if(var6 > var1.anInt2829) {
                     var1.anInt2829 = var6;
                  }
               }
            } else {
               var1.anInt2829 += var9;
               if(var6 < var1.anInt2829) {
                  var1.anInt2829 = var6;
               }
            }

            if(var5 == var1.anInt2819 && var6 == var1.anInt2829) {
               --var1.anInt2816;
               if(0 < var1.anInt2811) {
                  --var1.anInt2811;
               }
            }
         }

      } catch (RuntimeException var17) {
         throw Class44.clientError(var17, "ia.C(" + var0 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   public static void method1181(byte var0) {
      try {
         anIntArray882 = null;
         if(var0 != -118) {
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ia.E(" + var0 + ')');
      }
   }

   final void method1182(RSByteBuffer var1, int var2, byte var3) {
      try {
         if(var3 < 75) {
            this.method1182((RSByteBuffer)null, -111, (byte)47);
         }

         while(true) {
            int var4 = var1.getByteB();
            if(var4 == 0) {
               return;
            }

            this.method1183(var2, var4, var1);
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "ia.G(" + (var1 != null?"{...}":"null") + ',' + var2 + ',' + var3 + ')');
      }
   }

   private void method1183(int var2, int var3, RSByteBuffer var4) {
      try {

          if(var3 == 1) {
            this.anInt883 = var4.getShort();
         } else if (2 == var3) {
            this.anInt881 = var4.getByteB();
            this.anInt879 = var4.getByteB();
         }

      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "ia.D(" + -18426 + ',' + var2 + ',' + var3 + ',' + (var4 != null?"{...}":"null") + ')');
      }
   }

   static int method1184(int var0, byte var1) {
      try {
         int var3 = var0 & 63;
         int var4 = (var0 & 217) >> 6;
         if(var3 == 18) {
            if(0 == var4) {
               return 1;
            }

            if(var4 == 1) {
               return 2;
            }

            if(var4 == 2) {
               return 4;
            }

             return 8;
         } else if (var3 == 19 || var3 == 21) {
            if (var4 == 0) {
               return 16;
            }

            if (1 == var4) {
               return 32;
            }

            if (var4 == 2) {
               return 64;
            }

             return 128;
         }

         return 0;
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "ia.A(" + var0 + ',' + var1 + ')');
      }
   }

}
