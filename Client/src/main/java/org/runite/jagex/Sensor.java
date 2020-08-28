package org.runite.jagex;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Robot;
import java.awt.image.BufferedImage;

class Sensor {

   private final Robot aRobot1732 = new Robot();
   private Component aComponent1733;


   void method1795(Point var2, int var3, Component var4, int var5, int[] var6) {
       if(var6 == null) {
          var4.setCursor((Cursor)null);
       } else {
          BufferedImage var7 = new BufferedImage(var3, var5, 2);
          var7.setRGB(0, 0, var3, var5, var6, 0, var3);
          var4.setCursor(var4.getToolkit().createCustomCursor(var7, var2, (String)null));
       }

   }

   Sensor() throws Exception {}

   void method1796(int var1, int var3) {
      this.aRobot1732.mouseMove(var1, var3);
   }

   void method1797(Component var1, boolean var3) {
      if(var3) {
         var1 = null;
      } else if (var1 == null) {
         throw new NullPointerException();
      }

       if(var1 != this.aComponent1733) {

           if(this.aComponent1733 != null) {
            this.aComponent1733.setCursor((Cursor)null);
            this.aComponent1733 = null;
         }

         if(var1 != null) {
            var1.setCursor(var1.getToolkit().createCustomCursor(new BufferedImage(1, 1, 2), new Point(0, 0), (String)null));
            this.aComponent1733 = var1;
         }
      }

   }
}
