package org.runite.client;
import org.rs09.client.Node;

import java.awt.*;
import java.awt.image.ImageObserver;

final class Class3_Sub28_Sub1 extends Node {

   static boolean aBoolean3531 = false;
   static Class3_Sub20 aClass3_Sub20_3532 = new Class3_Sub20(0, 0);
    static Image anImage2695;
    static FontMetrics aFontMetrics1822;
    int[] anIntArray3533;
   int[] anIntArray3534;
   int[] anIntArray3535;
   static int anInt3536;
   static int anInt3537;
   RSString quickChatMenu;
   static int anInt3539;
   static int dropAction;
   static int counter;
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
         throw ClientErrorException.clientError(var3, "bc.O(" + -85 + ')');
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
         throw ClientErrorException.clientError(var4, "bc.Q(" + var1 + ',' + 0 + ')');
      }
   }

   private void method527(DataBuffer var1, int var3) {
      try {

         if(var3 == 1) {
            this.quickChatMenu = var1.readString();
         } else {
            int var4;
            int var5;
            if(var3 == 2) {
               var4 = var1.readUnsignedByte();
               this.anIntArray3534 = new int[var4];
               this.anIntArray3535 = new int[var4];

               for(var5 = 0; var5 < var4; ++var5) {
                  this.anIntArray3534[var5] = var1.readUnsignedShort();
                  this.anIntArray3535[var5] = Class3_Sub13_Sub33.method322(var1.readSignedByte());
               }
            } else if (var3 == 3) {
               var4 = var1.readUnsignedByte();
               this.anIntArray3540 = new int[var4];
               this.anIntArray3533 = new int[var4];

               for (var5 = 0; var5 < var4; ++var5) {
                  this.anIntArray3540[var5] = var1.readUnsignedShort();
                  this.anIntArray3533[var5] = Class3_Sub13_Sub33.method322(var1.readSignedByte());
               }
            }
         }

      } catch (RuntimeException var6) {
         throw ClientErrorException.clientError(var6, "bc.E(" + (var1 != null?"{...}":"null") + ',' + 0 + ',' + var3 + ')');
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
         throw ClientErrorException.clientError(var5, "bc.P(" + (byte) 50 + ',' + var2 + ')');
      }
   }

   final void method530(DataBuffer var1) {
      try {

         while(true) {
            int var3 = var1.readUnsignedByte();
            if(var3 == 0) {
               return;
            }

            this.method527(var1, var3);
         }
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "bc.D(" + (var1 != null?"{...}":"null") + ',' + (byte) 116 + ')');
      }
   }

   static RSString method531() {
         RSString var1;
         if(Class164_Sub1.anInt3012 == 1 && Unsorted.menuOptionCount < 2) {
            var1 = RSString.stringCombiner(new RSString[]{TextCore.HasUse, TextCore.Spacer, RenderAnimationDefinition.aClass94_378, TextCore.aClass94_1724});
         } else if(GameObject.aBoolean1837 && 2 > Unsorted.menuOptionCount) {
            var1 = RSString.stringCombiner(new RSString[]{Class3_Sub28_Sub9.aClass94_3621, TextCore.Spacer, TextCore.aClass94_676, TextCore.aClass94_1724});

         } else if(ClientCommands.shiftClickEnabled && ObjectDefinition.aBooleanArray1490[81] && Unsorted.menuOptionCount > 2 && !ObjectDefinition.aBooleanArray1490[82]) {
            for (counter = 2; counter < Unsorted.menuOptionCount; counter++) {
               RSString option = (Unsorted.method802(Unsorted.menuOptionCount - counter));
               if (option.toString().contains("Drop") || option.toString().contains("Release")) {
                  ClientCommands.canDrop = true;
                  dropAction = counter;
                  break;
               } else {
                  ClientCommands.canDrop = false;
               }
            }
            if (ClientCommands.canDrop) {
               var1 = Unsorted.method802(Unsorted.menuOptionCount - dropAction);
            } else {
               var1 = Unsorted.method802(Unsorted.menuOptionCount - 1);
            }
         } else {
            var1 = Unsorted.method802(Unsorted.menuOptionCount - 1);
         }

         if(Unsorted.menuOptionCount > 2) {
            var1 = RSString.stringCombiner(new RSString[] {var1, Class1.aClass94_58, RSString.stringAnimator(Unsorted.menuOptionCount - 2), TextCore.HasMoreOptions});
         }
         return var1;
   }

   static void method532(int var0) {
      try {
         Class3_Sub25 var2 = (Class3_Sub25)Class3_Sub2.aHashTable_2220.get((long)var0);
         if(null != var2) {
            var2.unlink();
         }
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "bc.A(" + var0 + ',' + -28236 + ')');
      }
   }

   static void updateLoadingBar(Color var0, boolean var2, RSString var3, int var4) {
      try {
         try {
            Graphics var5 = GameShell.canvas.getGraphics();
            aFontMetrics1822 = GameShell.canvas.getFontMetrics(TextCore.Helvetica);
            if(var2) {
               var5.setColor(Color.black);
               var5.fillRect(0, 0, Class23.anInt454, Class140_Sub7.anInt2934);
            }

            if(null == var0) {
               var0 = ColorCore.loadingbarcolor;
            }

            try {
               if(null == anImage2695) {
                  anImage2695 = GameShell.canvas.createImage(304, 34);
               }

               Graphics var6 = anImage2695.getGraphics();
               var6.setColor(var0);
               var6.drawRect(0, 0, 303, 33);
               var6.fillRect(2, 2, var4 * 3, 30);
               var6.setColor(Color.black);
               var6.drawRect(1, 1, 301, 31);
               var6.fillRect(3 * var4 + 2, 2, -(3 * var4) + 300, 30);
               var6.setFont(TextCore.Helvetica);
               var6.setColor(Color.white);
               var3.drawString(var6, 22, (-var3.method1575(aFontMetrics1822) + 304) / 2);
               var5.drawImage(anImage2695, Class23.anInt454 / 2 - 152, -18 + Class140_Sub7.anInt2934 / 2, (ImageObserver)null);
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
               var3.drawString(var5, 22 + var8, var7 + (-var3.method1575(aFontMetrics1822) + 304) / 2);
            }

            if(Class167.aClass94_2083 != null) {
               var5.setFont(TextCore.Helvetica);
               var5.setColor(Color.white);
               Class167.aClass94_2083.drawString(var5, Class140_Sub7.anInt2934 / 2 - 26, Class23.anInt454 / 2 - Class167.aClass94_2083.method1575(aFontMetrics1822) / 2);
            }
         } catch (Exception var10) {
            GameShell.canvas.repaint();
         }

      } catch (RuntimeException var11) {
         throw ClientErrorException.clientError(var11, "bc.C(" + (var0 != null?"{...}":"null") + ',' + false + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + var4 + ')');
      }
   }

}
