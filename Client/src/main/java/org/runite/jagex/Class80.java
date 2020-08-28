package org.runite.jagex;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Insets;

final class Class80 {

   private Class3 aClass3_1130;
   static Class93 aClass93_1131 = new Class93(5);
   private int anInt1132 = 0;
   static RSString aClass94_1133 = RSString.createRSString(")4j");
   private final Class130 aClass130_1134;
   static Class93 aClass93_1135 = new Class93(4);
   static Class3_Sub28_Sub16[] aClass3_Sub28_Sub16Array1136;
   static int anInt1137 = 2;
   static int[] anIntArray1138;


   final Class3 method1392() {
      try {

          Class3 var2;
         if(this.anInt1132 > 0 && this.aClass130_1134.aClass3Array1697[this.anInt1132 + -1] != this.aClass3_1130) {
            var2 = this.aClass3_1130;
         } else {
            do {
               if(this.anInt1132 >= this.aClass130_1134.anInt1700) {
                  return null;
               }

               var2 = this.aClass130_1134.aClass3Array1697[this.anInt1132++].aClass3_74;
            } while(var2 == this.aClass130_1134.aClass3Array1697[-1 + this.anInt1132]);

         }
         this.aClass3_1130 = var2.aClass3_74;
         return var2;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "l.D(" + 0 + ')');
      }
   }

   final Class3 method1393() {
      try {

          this.anInt1132 = 0;
         return this.method1392();
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "l.C(" + (byte) 125 + ')');
      }
   }

   public static void method1394(byte var0) {
      try {
         anIntArray1138 = null;
         aClass93_1131 = null;
         aClass94_1133 = null;
         aClass93_1135 = null;
         aClass3_Sub28_Sub16Array1136 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "l.E(" + var0 + ')');
      }
   }

   static long method1395(int var0, int var1, int var2) {
      Class3_Sub2 var3 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2];
      return var3 != null && var3.aClass19_2233 != null?var3.aClass19_2233.aLong428:0L;
   }

   static void method1396(int var0) {
      try {
         int var2 = Class106.anInt1442;
         int var1 = Class84.anInt1164;
         int var4 = -Class140_Sub7.anInt2934 + (Class70.anInt1047 - var2);
         int var3 = -var1 + Class3_Sub9.anInt2334 - Class23.anInt454;
         if(~var1 < var0 || var3 > 0 || var2 > 0 || var4 > 0) {
            try {
               Object var5;
               if(null != Class3_Sub13_Sub10.aFrame3121) {
                  var5 = Class3_Sub13_Sub10.aFrame3121;
               } else if(GameShell.frame == null) {
                  var5 = Class38.aClass87_665.anApplet1219;
               } else {
                  var5 = GameShell.frame;
               }

               int var7 = 0;
               int var6 = 0;
               if(GameShell.frame == var5) {
                  Insets var8 = GameShell.frame.getInsets();
                  var6 = var8.left;
                  var7 = var8.top;
               }

               Graphics var11 = ((Container)var5).getGraphics();
               var11.setColor(Color.black);
               if(var1 > 0) {
                  var11.fillRect(var6, var7, var1, Class70.anInt1047);
               }

               if(0 < var2) {
                  var11.fillRect(var6, var7, Class3_Sub9.anInt2334, var2);
               }

               if(var3 > 0) {
                  var11.fillRect(-var3 + var6 + Class3_Sub9.anInt2334, var7, var3, Class70.anInt1047);
               }

               if(var4 > 0) {
                  var11.fillRect(var6, -var4 + var7 + Class70.anInt1047, Class3_Sub9.anInt2334, var4);
               }
            } catch (Exception var9) {
            }
         }

      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "l.A(" + var0 + ')');
      }
   }

   Class80(Class130 var1) {
      try {
         this.aClass130_1134 = var1;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "l.<init>(" + (var1 != null?"{...}":"null") + ')');
      }
   }

}
