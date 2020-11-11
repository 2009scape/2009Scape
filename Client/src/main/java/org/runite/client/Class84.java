package org.runite.client;

import org.rs09.client.config.GameConfig;
import org.rs09.client.rendering.Toolkit;

final class Class84 {

   static int[][] anIntArrayArray1160 = new int[104][104];
   static Class61 aClass61_1162 = new Class61();
   static int[] anIntArray1163 = new int[1000];
   static int anInt1164 = 0;
    static Class3_Sub28_Sub16_Sub2 aClass3_Sub28_Sub16_Sub2_1381;
    static int[] anIntArray1729 = new int[]{12543016, 15504954, 15914854, 16773818};

    static void method1417() {
      try {
         if(Class143.loadingStage == 10 && HDToolKit.highDetail) {
            Class117.method1719(28);
         }

         if(Class143.loadingStage == 30) {
            Class117.method1719(25);
         }

      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "lf.D(" + ')');
      }
   }

   static void method1418(int var0, CacheIndex var1) {
      try {
         Class163_Sub2_Sub1.aClass109_Sub1Array4027 = Unsorted.method619((byte)65, NPC.anInt4001, var1);
         Class52.anIntArray861 = new int[256];

         int var2;
         for(var2 = 0; var2 < 3; ++var2) {
            int var4 = (anIntArray1729[1 + var2] & 16711680) >> 16;
            float var3 = (float)((anIntArray1729[var2] & 16711680) >> 16);
            float var6 = (float)(anIntArray1729[var2] >> 8 & 255);
            float var9 = (float)(anIntArray1729[var2] & 255);
            float var5 = ((float)var4 - var3) / 64.0F;
            int var7 = (anIntArray1729[var2 + 1] & '\uff00') >> 8;
            float var8 = (-var6 + (float)var7) / 64.0F;
            int var10 = anIntArray1729[var2 + 1] & 255;
            float var11 = ((float)var10 - var9) / 64.0F;

            for(int var12 = 0; var12 < 64; ++var12) {
               Class52.anIntArray861[var12 + 64 * var2] = Class3_Sub13_Sub29.bitwiseOr((int)var9, Class3_Sub13_Sub29.bitwiseOr((int)var6 << 8, (int)var3 << 16));
               var6 += var8;
               var9 += var11;
               var3 += var5;
            }
         }

         for(var2 = 192; var2 < 255; ++var2) {
            Class52.anIntArray861[var2] = anIntArray1729[3];
         }

          Class161.anIntArray2026 = new int['\u8000'];
         Unsorted.anIntArray49 = new int['\u8000'];
         Class3_Sub13_Sub10.method215((byte)-89, null);
         Class3_Sub30_Sub1.anIntArray3805 = new int['\u8000'];
         Class159.anIntArray1681 = new int['\u8000'];
         aClass3_Sub28_Sub16_Sub2_1381 = new Class3_Sub28_Sub16_Sub2(128, 254);
      } catch (RuntimeException var13) {
         throw ClientErrorException.clientError(var13, "lf.E(" + var0 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   static void rightClickContextMenuDraw() {
      try {
         int x = AbstractIndexedSprite.anInt1462;
         int y = Class3_Sub13_Sub33.anInt3395;
         int width = Class3_Sub28_Sub3.anInt3552;
         int height = Class3_Sub28_Sub1.anInt3537;
         int contextMenuColor = 6116423; //Context Menu RGB || 6116423 Classic || Old var5 || ColorCore.getHexColors()
         Toolkit.getActiveToolkit().fillRect(1 + x, y + 18, width + -2, -19 + height, GameConfig.RCM_BG_COLOR, GameConfig.RCM_BG_OPACITY);
         if (GameConfig.RS3_CONTEXT_STYLE) {
            Toolkit.getActiveToolkit().fillRect(1 + x, 2 + y, width + -2, 16, GameConfig.RCM_TITLE_COLOR, GameConfig.RCM_TITLE_OPACITY);
            Toolkit.getActiveToolkit().drawRect(1 + x, 1 + y, width + -2, height, GameConfig.RCM_BORDER_COLOR, GameConfig.RCM_BORDER_OPACITY);
         } else {
            Toolkit.getActiveToolkit().fillRect(1 + x, 2 + y, width + -2, 16, GameConfig.RCM_TITLE_COLOR, GameConfig.RCM_TITLE_OPACITY);
            Toolkit.getActiveToolkit().drawRect(1 + x, y + 18, width + -2, -19 + height, GameConfig.RCM_BORDER_COLOR, GameConfig.RCM_BORDER_OPACITY);
         }

         /**
          * Old toolkit stuff, updated with new and improved toolkit (thanks Techdaan <3)
          *
         if(HDToolKit.highDetail) {
            OpenGlToolkit.OPENGL_TOOLKIT.fillRect(var1, var2, var3, var4, var5, 45);
            OpenGlToolkit.OPENGL_TOOLKIT.fillRect(1 + var1, 1 + var2, var3 + -2, 16, 0, 255);
            OpenGlToolkit.OPENGL_TOOLKIT.drawRect(1 + var1, var2 + 18, var3 + -2, -19 + var4, 11119017, 120);
            //            Class22.method934(var1, var2, var3, var4, var5); Old "toolkit"
            //System.out.println("Class84: rightClickContextMenuDraw debug: Context (choose option) menu: Passing values method934(" + var1 + ", " + var2 + ", " + var3 + ", " + var4 + ", " + var5 + ")");
            //            Class22.method934(1 + var1, 1 + var2, var3 + -2, 16, 0);
            //System.out.println("Class84: rightClickContextMenuDraw debug: Context (choose option) menu: Passing values method934(" + (1 + var1) +"," + (1 + var2) + ", " + (var3 + -2) + ", " + "16, " + "0)");
            //            Class22.drawRect(1 + var1, var2 + 18, var3 + -2, -19 + var4, 0);//Outer Rectangle choose option Box (contains options)
            //System.out.println("Class84: rightClickContextMenuDraw debug: Context (choose option) menu: Passing values drawRect(" + (1 + var1) + ", " + (var2 + 18) + ", " + (var3 + -2) + ", " + (-19 + var4) + ", " + "0)");
         } else {
            Class74.method934(var1, var2, var3, var4, var5);
            Class74.method934(1 + var1, 1 + var2, var3 + -2, 16, 0);
            Class74.drawRect(1 + var1, var2 + 18, -2 + var3, -19 + var4, 0);
         }
          */
         Class168.aClass3_Sub28_Sub17_2096.method681(RSString.parse(GameConfig.RCM_TITLE), x - -3, y + 14, contextMenuColor, -1);
         int var7 = Unsorted.anInt1709;
         int var6 = Class126.anInt1676;

          for(int var8 = 0; var8 < Unsorted.menuOptionCount; ++var8) {
            int var9 = (-var8 + -1 + Unsorted.menuOptionCount) * 15 + y - -31;
            int var10 = 16777215;
            if(var6 > x && x - -width > var6 && -13 + var9 < var7 && 3 + var9 > var7) {
               var10 = 16776960;
            }

            Class168.aClass3_Sub28_Sub17_2096.method681(Unsorted.method802(var8), x - -3, var9, var10, 0);
         }

         Unsorted.method1282(AbstractIndexedSprite.anInt1462, (byte)107, Class3_Sub13_Sub33.anInt3395, Class3_Sub28_Sub1.anInt3537, Class3_Sub28_Sub3.anInt3552);
      } catch (RuntimeException var11) {
         throw ClientErrorException.clientError(var11, "lf.A(" + -117 + ')');
      }
   }

   static void method1420(int var0, int var1, int var2, int var3, byte var4) {
      try {
         Class3_Sub28_Sub6 var5 = Class3_Sub24_Sub3.method466(10, var0);
         var5.g();
         var5.anInt3597 = var2;
         var5.anInt3598 = var3;
         var5.anInt3596 = var1;
         if(var4 >= -35) {
            Unsorted.anInt1165 = 86;
         }

      } catch (RuntimeException var6) {
         throw ClientErrorException.clientError(var6, "lf.C(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
      }
   }

   static int method1421() {
      try {

          return ((Class3_Sub13_Sub15.aBoolean3184?1:0) << 19) + (((Class38.aBoolean661?1:0) << 16) + ((!Class128.aBoolean1685?0:1) << 15) + ((!Class106.aBoolean1441?0:1) << 13) + ((Class140_Sub6.aBoolean2910?1:0) << 10) + ((Class3_Sub13_Sub22.aBoolean3275?1:0) << 9) + ((RSInterface.aBoolean236?1:0) << 7) + ((!Class25.aBoolean488?0:1) << 6) + ((KeyboardListener.aBoolean1905?1:0) << 5) + (((!Unsorted.aBoolean3665?0:1) << 3) + (Unsorted.anInt3625 & 7) - (-((!Unsorted.aBoolean3604?0:1) << 4) + -((WorldListEntry.aBoolean2623?1:0) << 8)) - (-(Unsorted.anInt1137 << 11 & 6144) + -((CS2Script.anInt2453 == 0 ?0:1) << 20) - (((Unsorted.anInt120 != 0 ?1:0) << 21) + ((Sprites.anInt340 == 0 ?0:1) << 22)))) - -(Class127_Sub1.method1757() << 23));
      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "lf.F(" + -2 + ')');
      }
   }

}
