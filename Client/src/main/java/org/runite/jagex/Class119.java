package org.runite.jagex;
import java.awt.Container;
import java.awt.Insets;

final class Class119 {

   static Class131 aClass131_1624;
   static RSString aClass94_1625 = RSString.createRSString("Memory before cleanup=");
   static Class33 aClass33_1626;
   static WorldListCountry[] countries;
   static CacheIndex aClass153_1628;
   static RSString aClass94_1630 = RSString.createRSString("Mem:");


   public static void method1728(int var0) {
      try {
         aClass33_1626 = null;
         aClass131_1624 = null;
         countries = null;
         aClass94_1630 = null;
         aClass153_1628 = null;
         aClass94_1625 = null;
         if(var0 != -14256) {
            method1728(46);
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "qh.B(" + var0 + ')');
      }
   }

   static void method1729() {
      try {
         Object var1;
         if(null == Class3_Sub13_Sub10.aFrame3121) {
            if(GameShell.frame == null) {
               var1 = Class38.aClass87_665.anApplet1219;
            } else {
               var1 = GameShell.frame;
            }
         } else {
            var1 = Class3_Sub13_Sub10.aFrame3121;
         }

         Class3_Sub9.anInt2334 = ((Container)var1).getSize().width;
         Class70.anInt1047 = ((Container)var1).getSize().height;
         Insets var2;
         if(var1 == GameShell.frame) {
            var2 = GameShell.frame.getInsets();
            Class70.anInt1047 -= var2.bottom + var2.top;
            Class3_Sub9.anInt2334 -= var2.right + var2.left;
         }

         if(Class83.method1411(0) >= 2) {
            Class23.anInt454 = Class3_Sub9.anInt2334;
            Class84.anInt1164 = 0;
            Class106.anInt1442 = 0;
            Class140_Sub7.anInt2934 = Class70.anInt1047;
         } else {
            Class106.anInt1442 = 0;
            Class84.anInt1164 = (-765 + Class3_Sub9.anInt2334) / 2;
            Class140_Sub7.anInt2934 = 503;
            Class23.anInt454 = 765;
         }

         if(HDToolKit.highDetail) {
            HDToolKit.method1854(Class23.anInt454, Class140_Sub7.anInt2934);
         }

         Class3_Sub28_Sub12.aCanvas3648.setSize(Class23.anInt454, Class140_Sub7.anInt2934);
         if(var1 == GameShell.frame) {
            var2 = GameShell.frame.getInsets();
            Class3_Sub28_Sub12.aCanvas3648.setLocation(var2.left + Class84.anInt1164, Class106.anInt1442 + var2.top);
         } else {
            Class3_Sub28_Sub12.aCanvas3648.setLocation(Class84.anInt1164, Class106.anInt1442);
         }

         if(Class3_Sub28_Sub12.anInt3655 != -1) {
            Class124.method1746(true, (byte)-125);
         }

         Class80.method1396(-1);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "qh.C(" + true + ')');
      }
   }

   static void method1730(Signlink var0) {
      try {
         Class122 var2 = null;

         try {
            Class64 var3 = var0.method1433("runescape", 12);

            while(0 == var3.anInt978) {
               Class3_Sub13_Sub34.method331(1L, 64);
            }

            if(var3.anInt978 == 1) {
               var2 = (Class122)var3.anObject974;
               RSByteBuffer var4 = Class23.method939();
               var2.method1738(116, var4.buffer, var4.index, 0);
            }
         } catch (Exception var6) {
         }

         try {
            if(var2 != null) {
               var2.close(1);
            }
         } catch (Exception var5) {
         }

      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "qh.A(" + (var0 != null?"{...}":"null") + ',' + (byte) 14 + ')');
      }
   }

}
