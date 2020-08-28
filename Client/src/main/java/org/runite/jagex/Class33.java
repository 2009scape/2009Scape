package org.runite.jagex;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.PixelGrabber;

final class Class33 {

   private byte[] aByteArray594 = new byte[100000];
   private boolean aBoolean595;
   private int anInt596;
   private static String aString597 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"??$%^&*()-_=+[{]};:\'@#~,<.>/?\\| " + String.valueOf('\u00c4') + '\u00cb' + '\u00cf' + '\u00d6' + '\u00dc' + '\u00e4' + '\u00eb' + '\u00ef' + '\u00f6' + '\u00fc' + '\u00ff' + '\u00df' + '\u00c1' + '\u00c0' + '\u00c9' + '\u00c8' + '\u00cd' + '\u00cc' + '\u00d3' + '\u00d2' + '\u00da' + '\u00d9' + '\u00e1' + '\u00e0' + '\u00e9' + '\u00e8' + '\u00ed' + '\u00ec' + '\u00f3' + '\u00f2' + '\u00fa' + '\u00f9' + '\u00c2' + '\u00ca' + '\u00ce' + '\u00d4' + '\u00db' + '\u00e2' + '\u00ea' + '\u00ee' + '\u00f4' + '\u00fb' + '\u00c6' + '\u00e6';
   private static final int anInt598 = aString597.length();
   private static int[] anIntArray599 = new int[256];


   private void method997(RSString var1, int var2, int var3, int var4, boolean var5) {
      if(this.aBoolean595 || var4 == 0) {
         var5 = false;
      }

      for(int var6 = 0; var6 < var1.length(-95); ++var6) {
         int var7 = anIntArray599[var1.charAt(var6, (byte)65)];
         if(var5) {
            this.method1001(var7, var2 + 1, var3, 1, this.aByteArray594);
            this.method1001(var7, var2, var3 + 1, 1, this.aByteArray594);
         }

         this.method1001(var7, var2, var3, var4, this.aByteArray594);
         var2 += this.aByteArray594[var7 + 7];
      }

   }

   final int method998() {
      return this.aByteArray594[8] - 1;
   }

   public static void method999() {
      aString597 = null;
      anIntArray599 = null;
   }

   private void method1000(int[] var1, byte[] var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
      for(int var10 = -var7; var10 < 0; ++var10) {
         for(int var11 = -var6; var11 < 0; ++var11) {
            int var12 = var2[var4++] & 255;
            if(var12 > 30) {
               if(var12 >= 230) {
                  var1[var5++] = var3;
               } else {
                  int var13 = var1[var5];
                  var1[var5++] = ((var3 & 16711935) * var12 + (var13 & 16711935) * (256 - var12) & -16711936) + ((var3 & '\uff00') * var12 + (var13 & '\uff00') * (256 - var12) & 16711680) >> 8;
               }
            } else {
               ++var5;
            }
         }

         var5 += var8;
         var4 += var9;
      }

   }

   private void method1001(int var1, int var2, int var3, int var4, byte[] var5) {
      int var6 = var2 + var5[var1 + 5];
      int var7 = var3 - var5[var1 + 6];
      int var8 = var5[var1 + 3];
      int var9 = var5[var1 + 4];
      int var10 = var5[var1] * 16384 + var5[var1 + 1] * 128 + var5[var1 + 2];
      int var11 = var6 + var7 * Class74.anInt1092;
      int var12 = Class74.anInt1092 - var8;
      int var13 = 0;
      int var14;
      if(var7 < Class74.anInt1095) {
         var14 = Class74.anInt1095 - var7;
         var9 -= var14;
         var7 = Class74.anInt1095;
         var10 += var14 * var8;
         var11 += var14 * Class74.anInt1092;
      }

      if(var7 + var9 >= Class74.anInt1099) {
         var9 -= var7 + var9 - Class74.anInt1099 + 1;
      }

      if(var6 < Class74.anInt1093) {
         var14 = Class74.anInt1093 - var6;
         var8 -= var14;
         var6 = Class74.anInt1093;
         var10 += var14;
         var11 += var14;
         var13 += var14;
         var12 += var14;
      }

      if(var6 + var8 >= Class74.anInt1096) {
         var14 = var6 + var8 - Class74.anInt1096 + 1;
         var8 -= var14;
         var13 += var14;
         var12 += var14;
      }

      if(var8 > 0 && var9 > 0) {
         if(this.aBoolean595) {
            this.method1000(Class74.anIntArray1100, var5, var4, var10, var11, var8, var9, var12, var13);
         } else {
            this.method1002(Class74.anIntArray1100, var5, var4, var10, var11, var8, var9, var12, var13);
         }
      }

   }

   private void method1002(int[] var1, byte[] var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
      int var10 = -(var6 >> 2);
      var6 = -(var6 & 3);

      for(int var11 = -var7; var11 < 0; ++var11) {
         int var12;
         for(var12 = var10; var12 < 0; ++var12) {
            if(var2[var4++] == 0) {
               ++var5;
            } else {
               var1[var5++] = var3;
            }

             if(var2[var4++] == 0) {
                ++var5;
             } else {
                var1[var5++] = var3;
             }

             if(var2[var4++] == 0) {
                ++var5;
             } else {
                var1[var5++] = var3;
             }

             if(var2[var4++] == 0) {
                ++var5;
             } else {
                var1[var5++] = var3;
             }
         }

         for(var12 = var6; var12 < 0; ++var12) {
            if(var2[var4++] == 0) {
               ++var5;
            } else {
               var1[var5++] = var3;
            }
         }

         var5 += var8;
         var4 += var9;
      }

   }

   final void method1003(RSString var1, int var2, int var3, int var4) {
      int var6 = this.method1005(var1) / 2;
      int var7 = this.method1006();
      if(var2 - var6 <= Class74.anInt1096) {
         if(var2 + var6 >= Class74.anInt1093) {
            if(var3 - var7 <= Class74.anInt1099) {
               if(var3 >= 0) {
                  this.method997(var1, var2 - var6, var3, var4, true);
               }
            }
         }
      }
   }

   private void method1004(Font var1, FontMetrics var2, char var3, int var4, boolean var5) {
      int var6 = var2.charWidth(var3);
      int var7 = var6;
      if(var5) {
         try {
            if(var3 == 47) {
               var5 = false;
            }

            if(var3 == 102 || var3 == 116 || var3 == 119 || var3 == 118 || var3 == 107 || var3 == 120 || var3 == 121 || var3 == 65 || var3 == 86 || var3 == 87) {
               ++var6;
            }
         } catch (Exception var23) {
         }
      }

      int var8 = var2.getMaxAscent();
      int var9 = var2.getMaxAscent() + var2.getMaxDescent();
      int var10 = var2.getHeight();
      Image var11 = Class3_Sub28_Sub12.aCanvas3648.createImage(var6, var9);
      Graphics var12 = var11.getGraphics();
      var12.setColor(Color.black);
      var12.fillRect(0, 0, var6, var9);
      var12.setColor(Color.white);
      var12.setFont(var1);
      var12.drawString(var3 + "", 0, var8);
      if(var5) {
         var12.drawString(var3 + "", 1, var8);
      }

      int[] var13 = new int[var6 * var9];
      PixelGrabber var14 = new PixelGrabber(var11, 0, 0, var6, var9, var13, 0, var6);

      try {
         var14.grabPixels();
      } catch (Exception var22) {
      }

      var11.flush();
       int var15 = 0;
      int var16 = 0;
      int var17 = var6;
      int var18 = var9;

      int var19;
      int var21;
      int var20;
      label134:
      for(var19 = 0; var19 < var9; ++var19) {
         for(var20 = 0; var20 < var6; ++var20) {
            var21 = var13[var20 + var19 * var6];
            if((var21 & 16777215) != 0) {
               var16 = var19;
               break label134;
            }
         }
      }

      label122:
      for(var19 = 0; var19 < var6; ++var19) {
         for(var20 = 0; var20 < var9; ++var20) {
            var21 = var13[var19 + var20 * var6];
            if((var21 & 16777215) != 0) {
               var15 = var19;
               break label122;
            }
         }
      }

      label110:
      for(var19 = var9 - 1; var19 >= 0; --var19) {
         for(var20 = 0; var20 < var6; ++var20) {
            var21 = var13[var20 + var19 * var6];
            if((var21 & 16777215) != 0) {
               var18 = var19 + 1;
               break label110;
            }
         }
      }

      label98:
      for(var19 = var6 - 1; var19 >= 0; --var19) {
         for(var20 = 0; var20 < var9; ++var20) {
            var21 = var13[var19 + var20 * var6];
            if((var21 & 16777215) != 0) {
               var17 = var19 + 1;
               break label98;
            }
         }
      }

      this.aByteArray594[var4 * 9] = (byte)(this.anInt596 / 16384);
      this.aByteArray594[var4 * 9 + 1] = (byte)(this.anInt596 / 128 & 127);
      this.aByteArray594[var4 * 9 + 2] = (byte)(this.anInt596 & 127);
      this.aByteArray594[var4 * 9 + 3] = (byte)(var17 - var15);
      this.aByteArray594[var4 * 9 + 4] = (byte)(var18 - var16);
      this.aByteArray594[var4 * 9 + 5] = (byte)var15;
      this.aByteArray594[var4 * 9 + 6] = (byte)(var8 - var16);
      this.aByteArray594[var4 * 9 + 7] = (byte)var7;
      this.aByteArray594[var4 * 9 + 8] = (byte)var10;

      for(var19 = var16; var19 < var18; ++var19) {
         for(var20 = var15; var20 < var17; ++var20) {
            var21 = var13[var20 + var19 * var6] & 255;
            if(var21 > 30 && var21 < 230) {
               this.aBoolean595 = true;
            }

            this.aByteArray594[this.anInt596++] = (byte)var21;
         }
      }

   }

   private int method1005(RSString var1) {
      int var2 = 0;

      for(int var3 = 0; var3 < var1.length(-97); ++var3) {
         if(var1.charAt(var3, (byte)-41) == 64 && var3 + 4 < var1.length(-93) && var1.charAt(var3 + 4, (byte)66) == 64) {
            var3 += 4;
         } else if(var1.charAt(var3, (byte)101) == 126 && var3 + 4 < var1.length(-60) && var1.charAt(var3 + 4, (byte)-51) == 126) {
            var3 += 4;
         } else {
            var2 += this.aByteArray594[anIntArray599[var1.charAt(var3, (byte)-64)] + 7];
         }
      }

      return var2;
   }

   final int method1006() {
      return this.aByteArray594[6];
   }

   Class33(int var1, Component var3) {
      this.anInt596 = anInt598 * 9;
      this.aBoolean595 = false;
      Font var4 = new Font("Helvetica", Font.BOLD, var1);
      FontMetrics var5 = var3.getFontMetrics(var4);

      int var6;
      for(var6 = 0; var6 < anInt598; ++var6) {
         this.method1004(var4, var5, aString597.charAt(var6), var6, false);
      }

      if(this.aBoolean595) {
         this.anInt596 = anInt598 * 9;
         this.aBoolean595 = false;
         var4 = new Font("Helvetica", Font.PLAIN, var1);
         var5 = var3.getFontMetrics(var4);

         for(var6 = 0; var6 < anInt598; ++var6) {
            this.method1004(var4, var5, aString597.charAt(var6), var6, false);
         }

         if(!this.aBoolean595) {
            this.anInt596 = anInt598 * 9;
            this.aBoolean595 = false;

            for(var6 = 0; var6 < anInt598; ++var6) {
               this.method1004(var4, var5, aString597.charAt(var6), var6, true);
            }
         }
      }

      byte[] var8 = new byte[this.anInt596];

      if (this.anInt596 >= 0) System.arraycopy(this.aByteArray594, 0, var8, 0, this.anInt596);

      this.aByteArray594 = var8;
   }

   static {
      for(int var0 = 0; var0 < 256; ++var0) {
         int var1 = aString597.indexOf(var0);
         if(var1 == -1) {
            var1 = 74;
         }

         anIntArray599[var0] = var1 * 9;
      }

   }
}
