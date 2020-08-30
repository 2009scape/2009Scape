package org.runite.jagex;

final class Class17 {

   static Interface2 anInterface2_408;
   static Thread aThread409;
   static int anInt410;
   static Class64 aClass64_413;


   public Class17() {
      new Class127();
   }

   static void method904(int var0, Class140_Sub4 var1) {
      try {
         if(var1.anInt2779 != 0) {
            RenderAnimationDefinition var2 = var1.method1965();
            int var4;
            int var5;
            if(var1.anInt2772 != -1 && '\u8000' > var1.anInt2772) {
               NPC var3 = Class3_Sub13_Sub24.npcs[var1.anInt2772];
               if(var3 != null) {
                  var5 = -var3.anInt2829 + var1.anInt2829;
                  var4 = -var3.anInt2819 + var1.anInt2819;
                  if(0 != var4 || 0 != var5) {
                     var1.anInt2806 = (int)(Math.atan2((double)var4, (double)var5) * 325.949D) & 2047;
                  }
               }
            }

            int var6;
            int var9;
            if(var1.anInt2772 >= '\u8000') {
               var9 = -32768 + var1.anInt2772;
               if(Class3_Sub1.localIndex == var9) {
                  var9 = 2047;
               }

               Player var10 = Class3_Sub13_Sub22.players[var9];
               if(null != var10) {
                  var6 = -var10.anInt2829 + var1.anInt2829;
                  var5 = -var10.anInt2819 + var1.anInt2819;
                  if(var5 != 0 || var6 != 0) {
                     var1.anInt2806 = (int)(Math.atan2((double)var5, (double)var6) * 325.949D) & 2047;
                  }
               }
            }

            if((0 != var1.anInt2786 || 0 != var1.anInt2762) && (var1.anInt2816 == 0 || var1.anInt2824 > 0)) {
               var9 = var1.anInt2819 + -((-Class131.anInt1716 + (var1.anInt2786 - Class131.anInt1716)) * 64);
               var4 = -((-Class82.anInt1152 + (var1.anInt2762 - Class82.anInt1152)) * 64) + var1.anInt2829;
               if(var9 != 0 || var4 != 0) {
                  var1.anInt2806 = (int)(Math.atan2((double)var9, (double)var4) * 325.949D) & 2047;
               }

               var1.anInt2762 = 0;
               var1.anInt2786 = 0;
            }

            var9 = var1.anInt2806 - var1.anInt2785 & 2047;
            if(var9 == 0) {
               var1.anInt2789 = 0;
               var1.anInt2821 = 0;
            } else if (var2.anInt369 == 0) {
               ++var1.anInt2789;
               boolean var11;
               if (var9 > 1024) {
                  var1.anInt2785 -= var1.anInt2779;
                  var11 = true;
                  if (var1.anInt2779 > var9 || var9 > -var1.anInt2779 + 2048) {
                     var1.anInt2785 = var1.anInt2806;
                     var11 = false;
                  }

                  if (var1.anInt2764 == var2.anInt368 && (25 < var1.anInt2789 || var11)) {
                     if (var2.anInt367 == -1) {
                        var1.anInt2764 = var2.anInt382;
                     } else {
                        var1.anInt2764 = var2.anInt367;
                     }
                  }
               } else {
                  var11 = true;
                  var1.anInt2785 += var1.anInt2779;
                  if (var1.anInt2779 > var9 || 2048 - var1.anInt2779 < var9) {
                     var11 = false;
                     var1.anInt2785 = var1.anInt2806;
                  }

                  if (var2.anInt368 == var1.anInt2764 && (25 < var1.anInt2789 || var11)) {
                     if (-1 == var2.anInt407) {
                        var1.anInt2764 = var2.anInt382;
                     } else {
                        var1.anInt2764 = var2.anInt407;
                     }
                  }
               }

               var1.anInt2785 &= 2047;
            } else {
               if (var1.anInt2764 == var2.anInt368 && 25 < var1.anInt2789) {
                  if (var2.anInt407 == -1) {
                     var1.anInt2764 = var2.anInt382;
                  } else {
                     var1.anInt2764 = var2.anInt407;
                  }
               }

               var4 = var1.anInt2806 << 5;
               if (var1.anInt2808 != var4) {
                  var1.anInt2791 = 0;
                  var1.anInt2808 = var4;
                  var5 = -var1.anInt2780 + var4 & '\uffff';
                  var6 = var1.anInt2821 * var1.anInt2821 / (var2.anInt369 * 2);
                  int var7;
                  if (var1.anInt2821 > 0 && var6 <= var5 && -var6 + var5 < '\u8000') {
                     var1.anInt2803 = var5 / 2;
                     var1.aBoolean2769 = true;
                     var7 = var2.anInt357 * var2.anInt357 / (var2.anInt369 * 2);
                     if (32767 < var7) {
                        var7 = 32767;
                     }

                     if (var7 < var1.anInt2803) {
                        var1.anInt2803 = -var7 + var5;
                     }
                  } else if (0 > var1.anInt2821 && var6 <= -var5 + 65536 && 65536 + -var5 + -var6 < '\u8000') {
                     var1.anInt2803 = (-var5 + 65536) / 2;
                     var1.aBoolean2769 = true;
                     var7 = var2.anInt357 * var2.anInt357 / (var2.anInt369 * 2);
                     if (var7 > 32767) {
                        var7 = 32767;
                     }

                     if (var7 < var1.anInt2803) {
                        var1.anInt2803 = 65536 - (var5 + var7);
                     }
                  } else {
                     var1.aBoolean2769 = false;
                  }
               }

               if (var1.anInt2821 == 0) {
                  var5 = -var1.anInt2780 + var1.anInt2808 & '\uffff';
                  if (var5 < var2.anInt369) {
                     var1.anInt2780 = var1.anInt2808;
                  } else {
                     var1.anInt2791 = 0;
                     var6 = var2.anInt357 * var2.anInt357 / (2 * var2.anInt369);
                     var1.aBoolean2769 = true;
                     if (32767 < var6) {
                        var6 = 32767;
                     }

                     if (var5 >= 32768) {
                        var1.anInt2821 = -var2.anInt369;
                        var1.anInt2803 = (65536 - var5) / 2;
                        if (var1.anInt2803 > var6) {
                           var1.anInt2803 = 65536 - (var5 + var6);
                        }
                     } else {
                        var1.anInt2821 = var2.anInt369;
                        var1.anInt2803 = var5 / 2;
                        if (var1.anInt2803 > var6) {
                           var1.anInt2803 = -var6 + var5;
                        }
                     }
                  }
               } else if (var1.anInt2821 <= 0) {
                  if (var1.anInt2803 <= var1.anInt2791) {
                     var1.aBoolean2769 = false;
                  }

                  if (!var1.aBoolean2769) {
                     var1.anInt2821 += var2.anInt369;
                     if (0 < var1.anInt2821) {
                        var1.anInt2821 = 0;
                     }
                  } else if (var1.anInt2821 > -var2.anInt357) {
                     var1.anInt2821 -= var2.anInt369;
                  }
               } else {
                  if (var1.anInt2791 >= var1.anInt2803) {
                     var1.aBoolean2769 = false;
                  }

                  if (!var1.aBoolean2769) {
                     var1.anInt2821 -= var2.anInt369;
                     if (var1.anInt2821 < 0) {
                        var1.anInt2821 = 0;
                     }
                  } else if (var1.anInt2821 < var2.anInt357) {
                     var1.anInt2821 += var2.anInt369;
                  }
               }

               var1.anInt2780 += var1.anInt2821;
               var1.anInt2780 &= '\uffff';
               if (0 >= var1.anInt2821) {
                  var1.anInt2791 -= var1.anInt2821;
               } else {
                  var1.anInt2791 += var1.anInt2821;
               }

               var1.anInt2785 = var1.anInt2780 >> 5;
            }

            if(var0 != 65536) {
               method904(-93, (Class140_Sub4)null);
            }

         }
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "cm.A(" + var0 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   public static void method905(int var0) {
      try {
         anInterface2_408 = null;
         aClass64_413 = null;
         if(var0 != -24912) {
         }

         aThread409 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "cm.B(" + var0 + ')');
      }
   }

}
