package org.runite.client;
import org.rs09.client.Linkable;
import org.rs09.client.rendering.Toolkit;

import java.awt.Graphics;

abstract class Class3_Sub13 extends Linkable {

   boolean aBoolean2375;
   Class97 aClass97_2376;
   Class3_Sub13[] aClass3_Sub13Array2377;
   static int anInt2378 = 0;
   static Class3_Sub28_Sub17 aClass3_Sub28_Sub17_2379;
   int anInt2381;
   Class114 aClass114_2382;
   static int anInt2383 = 0;


   final int[] method152(int var1, int var2, int var3) {
      try {
         if(var3 != 32755) {
            anInt2383 = 121;
         }

         return this.aClass3_Sub13Array2377[var1].aBoolean2375?this.aClass3_Sub13Array2377[var1].method154(var2, (byte)-118):this.aClass3_Sub13Array2377[var1].method166(-1, var2)[0];
      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "j.RA(" + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   static void method153(int var0) {
      try {
         if(var0 >= 91) {
            Class3_Sub26.aClass61_2557 = new Class61();
         }
      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "j.QA(" + var0 + ')');
      }
   }

   int[] method154(int var1, byte var2) {
      try {
         throw new IllegalStateException("This operation does not have a monochrome output");
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "j.D(" + var1 + ',' + var2 + ')');
      }
   }

   int method155(byte var1) {
      try {
         if(var1 != 19) {
            this.method152(-80, 116, -73);
         }

         return -1;
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "j.HA(" + var1 + ')');
      }
   }

   void method157(int var1, DataBuffer var2, boolean var3) {
      try {
         if(!var3) {
            this.aClass3_Sub13Array2377 = (Class3_Sub13[])null;
         }

      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "j.A(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

   void method158(int var1) {
      try {
         if(var1 != 16251) {
            anInt2378 = 12;
         }

      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "j.P(" + var1 + ')');
      }
   }

   int method159(int var1) {
      try {
         if(var1 != 4) {
            method164((byte)-98, true, (RSString)null);
         }

         return -1;
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "j.GA(" + var1 + ')');
      }
   }

   final void method160(int var1, int var2) {
      try {

         int var4 = 255 == this.anInt2381?var1:this.anInt2381;
         if(this.aBoolean2375) {
            this.aClass114_2382 = new Class114(var4, var1, var2);
         } else {
            this.aClass97_2376 = new Class97(var4, var1, var2);
         }

      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "j.SA(" + var1 + ',' + var2 + ',' + 250 + ')');
      }
   }

   void method161(byte var1) {
      try {
         if(var1 != -45) {
            anInt2383 = 16;
         }

         if(this.aBoolean2375) {
            this.aClass114_2382.method1706();
            this.aClass114_2382 = null;
         } else {
            this.aClass97_2376.method1590();
            this.aClass97_2376 = null;
         }

      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "j.BA(" + var1 + ')');
      }
   }

   final int[][] method162(int var1, int var2, byte var3) {
      try {
         if(var3 > -45) {
            return (int[][])((int[][])null);
         } else if(this.aClass3_Sub13Array2377[var2].aBoolean2375) {
            int[] var4 = this.aClass3_Sub13Array2377[var2].method154(var1, (byte)-105);
            return new int[][]{var4, var4, var4};
         } else {
            return this.aClass3_Sub13Array2377[var2].method166(-1, var1);
         }
      } catch (RuntimeException var6) {
         throw ClientErrorException.clientError(var6, "j.UA(" + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   static Class3_Sub28_Sub17_Sub1 method163(byte[] var0) {
      try {
         if(var0 == null) {
            return null;
         } else {

            Class3_Sub28_Sub17_Sub1 var2 = new Class3_Sub28_Sub17_Sub1(var0, Class164.anIntArray2048, Unsorted.anIntArray2591, Class140_Sub7.anIntArray2931, Unsorted.anIntArray3076, Class163_Sub1.aByteArrayArray2987);
            Class39.method1035((byte)126);
            return var2;
         }
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "j.WA(" + "{...}" + ',' + 25208 + ')');
      }
   }

   static void method164(byte var0, boolean var1, RSString var2) {
      try {
         if(var0 <= -11) {
            byte var3 = 4;
            int var4 = var3 + 6;
            int var5 = var3 + 6;
            int var6 = Class126.aClass3_Sub28_Sub17_1669.method680(var2, 250);
            int var7 = Class126.aClass3_Sub28_Sub17_1669.method684(var2, 250) * 13;
            //Used for the top left (please wait...)
            Toolkit.getActiveToolkit().method934(var4 - var3, -var3 + var5, var3 + var6 - -var3, var3 + var3 + var7, 0);
            Toolkit.getActiveToolkit().drawRect(-var3 + var4, -var3 + var5, var6 + var3 - -var3, var3 + var7 + var3, 16777215, 255);

            Class126.aClass3_Sub28_Sub17_1669.method676(var2, var4, var5, var6, var7, 16777215, -1, 1, 1, 0);
            Class75.method1340(var4 + -var3, var6 + (var3 - -var3), -var3 + var5, var3 + var7 + var3);
            if(var1) {
               if(HDToolKit.highDetail) {
                  HDToolKit.bufferSwap();
               } else {
                  try {
                     Graphics var8 = GameShell.canvas.getGraphics();
                     Class164_Sub1.aClass158_3009.method2179(var8);
                  } catch (Exception var9) {
                     GameShell.canvas.repaint();
                  }
               }
            } else {
               Unsorted.method1282(var4, (byte)-97, var5, var7, var6);
            }

         }
      } catch (RuntimeException var10) {
         throw ClientErrorException.clientError(var10, "j.TA(" + var0 + ',' + var1 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   Class3_Sub13(int var1, boolean var2) {
      try {
         this.aClass3_Sub13Array2377 = new Class3_Sub13[var1];
         this.aBoolean2375 = var2;
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "j.<init>(" + var1 + ',' + var2 + ')');
      }
   }

   static void method165() {
      try {
         WorldListEntry.aAbstractSprite_1339 = null;
         WorldListEntry.aAbstractSprite_3099 = null;
         Class50.aAbstractSprite_824 = null;

         WorldListEntry.aAbstractSprite_1457 = null;
         Class3_Sub26.aAbstractSprite_2560 = null;
      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "j.VA(" + -7878 + ')');
      }
   }

   int[][] method166(int var1, int var2) {
      try {
         if(var1 == -1) {
            throw new IllegalStateException("This operation does not have a colour output");
         } else {
            return (int[][])((int[][])null);
         }
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "j.T(" + var1 + ',' + var2 + ')');
      }
   }

}
