package org.runite.jagex;
import java.awt.*;
import java.awt.image.ImageObserver;

final class Class3_Sub28_Sub1 extends Node {

   static boolean aBoolean3531 = false;
   static Class3_Sub20 aClass3_Sub20_3532 = new Class3_Sub20(0, 0);
   int[] anIntArray3533;
   int[] anIntArray3534;
   int[] anIntArray3535;
   static int anInt3536;
   static int anInt3537;
   RSString quickChatMenu;
   static int anInt3539;
   int[] anIntArray3540;


   final void method525() {
      try {
         int var2;
         if(null != this.anIntArray3540) {
            for(var2 = 0; var2 < this.anIntArray3540.length; ++var2) {
               this.anIntArray3540[var2] = Class3_Sub13_Sub29.bitwiseOr(this.anIntArray3540[var2], '\u8000');
            }
         }

         if(null != this.anIntArray3534) {
            for(var2 = 0; this.anIntArray3534.length > var2; ++var2) {
               this.anIntArray3534[var2] = Class3_Sub13_Sub29.bitwiseOr(this.anIntArray3534[var2], '\u8000');
            }
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bc.O(" + -85 + ')');
      }
   }

   final int method526(int var1) {
      try {
         if (this.anIntArray3540 != null) {
            for (int var3 = 0; this.anIntArray3540.length > var3; ++var3) {
               if (var1 == this.anIntArray3533[var3]) {
                  return this.anIntArray3540[var3];
               }
            }

         }
         return -1;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "bc.Q(" + var1 + ',' + 0 + ')');
      }
   }

   private void method527(RSByteBuffer var1, int var3) {
      try {

         if(var3 == 1) {
            this.quickChatMenu = var1.getString();
         } else {
            int var4;
            int var5;
            if(var3 == 2) {
               var4 = var1.getByteB();
               this.anIntArray3534 = new int[var4];
               this.anIntArray3535 = new int[var4];

               for(var5 = 0; var5 < var4; ++var5) {
                  this.anIntArray3534[var5] = var1.getShort();
                  this.anIntArray3535[var5] = Class3_Sub13_Sub33.method322(var1.getByte());
               }
            } else if (var3 == 3) {
               var4 = var1.getByteB();
               this.anIntArray3540 = new int[var4];
               this.anIntArray3533 = new int[var4];

               for (var5 = 0; var5 < var4; ++var5) {
                  this.anIntArray3540[var5] = var1.getShort();
                  this.anIntArray3533[var5] = Class3_Sub13_Sub33.method322(var1.getByte());
               }
            }
         }

      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "bc.E(" + (var1 != null?"{...}":"null") + ',' + 0 + ',' + var3 + ')');
      }
   }

   public static void method528(int var0) {
      try {
         aClass3_Sub20_3532 = null;
         if(var0 != -1667) {
            anInt3539 = 101;
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "bc.B(" + var0 + ')');
      }
   }

   final int method529(int var2) {
      try {
         if (null != this.anIntArray3534) {
            for (int var4 = 0; this.anIntArray3534.length > var4; ++var4) {
               if (var2 == this.anIntArray3535[var4]) {
                  return this.anIntArray3534[var4];
               }
            }

         }
         return -1;
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "bc.P(" + (byte) 50 + ',' + var2 + ')');
      }
   }

   final void method530(RSByteBuffer var1) {
      try {

         while(true) {
            int var3 = var1.getByteB();
            if(var3 == 0) {
               return;
            }

            this.method527(var1, var3);
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "bc.D(" + (var1 != null?"{...}":"null") + ',' + (byte) 116 + ')');
      }
   }

   static RSString method531() {
      try {
         RSString var1;
         if(Class164_Sub1.anInt3012 == 1 && Class3_Sub13_Sub34.anInt3415 < 2) {
            var1 = RenderAnimationDefinition.method903(new RSString[]{TextCore.HasUse, TextCore.Spacer, RenderAnimationDefinition.aClass94_378, Class131.aClass94_1724}, (byte)-105);
         } else if(GameObject.aBoolean1837 && 2 > Class3_Sub13_Sub34.anInt3415) {
            var1 = RenderAnimationDefinition.method903(new RSString[]{Class3_Sub28_Sub9.aClass94_3621, TextCore.Spacer, Class40.aClass94_676, Class131.aClass94_1724}, (byte)-95);
         } else if(Class101.aBoolean1419 && ObjectDefinition.aBooleanArray1490[81] && Class3_Sub13_Sub34.anInt3415 > 2) {
            var1 = RSByteBuffer.method802(Class3_Sub13_Sub34.anInt3415 + -2);
         } else {
            var1 = RSByteBuffer.method802(Class3_Sub13_Sub34.anInt3415 - 1);
         }

         if(Class3_Sub13_Sub34.anInt3415 > 2) {
            var1 = RenderAnimationDefinition.method903(new RSString[] {
                    var1, Class1.aClass94_58, Class72.method1298((byte) 9, Class3_Sub13_Sub34.anInt3415 - 2), TextCore.HasMoreOptions
            }, (byte)-62);
//            System.out.println(var1.toString());
         }

         return var1;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bc.F(" + (byte) 94 + ')');
      }
   }

   static void method532(int var0) {
      try {
         Class3_Sub25 var2 = (Class3_Sub25)Class3_Sub2.aClass130_2220.method1780((long)var0, 0);
         if(null != var2) {
            var2.method86(-1024);
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bc.A(" + var0 + ',' + -28236 + ')');
      }
   }

   static void updateLoadingBar(Color var0, boolean var2, RSString var3, int var4) {
      try {
         try {
            Graphics var5 = Class3_Sub28_Sub12.aCanvas3648.getGraphics();
            Class139.aFontMetrics1822 = Class3_Sub28_Sub12.aCanvas3648.getFontMetrics(TextCore.Helvetica);
            if(var2) {
               var5.setColor(Color.black);
               var5.fillRect(0, 0, Class23.anInt454, Class140_Sub7.anInt2934);
            }

            if(null == var0) {
               var0 = ColorCore.loadingbarcolor;
            }

            try {
               if(null == Class129_Sub1.anImage2695) {
                  Class129_Sub1.anImage2695 = Class3_Sub28_Sub12.aCanvas3648.createImage(304, 34);
               }

               Graphics var6 = Class129_Sub1.anImage2695.getGraphics();
               var6.setColor(var0);
               var6.drawRect(0, 0, 303, 33);
               var6.fillRect(2, 2, var4 * 3, 30);
               var6.setColor(Color.black);
               var6.drawRect(1, 1, 301, 31);
               var6.fillRect(3 * var4 + 2, 2, -(3 * var4) + 300, 30);
               var6.setFont(TextCore.Helvetica);
               var6.setColor(Color.white);
               var3.drawString((-var3.method1575(Class139.aFontMetrics1822) + 304) / 2, 22, var6, (byte)-90);
               var5.drawImage(Class129_Sub1.anImage2695, Class23.anInt454 / 2 - 152, -18 + Class140_Sub7.anInt2934 / 2, (ImageObserver)null);
            } catch (Exception var9) {
               int var7 = -152 + Class23.anInt454 / 2;
               int var8 = -18 + Class140_Sub7.anInt2934 / 2;
               var5.setColor(var0);
               var5.drawRect(var7, var8, 303, 33);
               var5.fillRect(var7 + 2, 2 + var8, 3 * var4, 30);
               var5.setColor(Color.black);
               var5.drawRect(1 + var7, var8 - -1, 301, 31);
               var5.fillRect(3 * var4 + (var7 - -2), 2 + var8, 300 - var4 * 3, 30);
               var5.setFont(TextCore.Helvetica);
               var5.setColor(Color.white);
               var3.drawString(var7 + (-var3.method1575(Class139.aFontMetrics1822) + 304) / 2, 22 + var8, var5, (byte)-125);
            }

            if(Class167.aClass94_2083 != null) {
               var5.setFont(TextCore.Helvetica);
               var5.setColor(Color.white);
               Class167.aClass94_2083.drawString(Class23.anInt454 / 2 - Class167.aClass94_2083.method1575(Class139.aFontMetrics1822) / 2, Class140_Sub7.anInt2934 / 2 - 26, var5, (byte)-116);
            }
         } catch (Exception var10) {
            Class3_Sub28_Sub12.aCanvas3648.repaint();
         }

      } catch (RuntimeException var11) {
         throw Class44.clientError(var11, "bc.C(" + (var0 != null?"{...}":"null") + ',' + false + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + var4 + ')');
      }
   }

}
