package org.runite.jagex;
import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.lang.reflect.Field;

public class Display {

   private GraphicsDevice aGraphicsDevice445;
   private DisplayMode aDisplayMode446;


   private void method917(Frame var1) {
      boolean var3 = false;

      Field var4;
      boolean var5;
      try {
         var4 = Class.forName("sun.awt.Win32GraphicsDevice").getDeclaredField("valid");
         var4.setAccessible(true);
         var5 = ((Boolean)var4.get(this.aGraphicsDevice445)).booleanValue();
         if(var5) {
            var4.set(this.aGraphicsDevice445, Boolean.FALSE);
            var3 = true;
         }
      } catch (Throwable var15) {
         ;
      }

      var5 = false;

      try {
         var5 = true;
         this.aGraphicsDevice445.setFullScreenWindow(var1);
         if((byte) -63 == -63) {
            var5 = false;
         } else {
            this.method918(90, -112, -67, 27, (Frame)null, -49);
            var5 = false;
         }
      } finally {
         if(var5 && var3) {
            try {
               Field var7 = Class.forName("sun.awt.Win32GraphicsDevice").getDeclaredField("valid");
               var7.set(this.aGraphicsDevice445, Boolean.TRUE);
            } catch (Throwable var13) {
               ;
            }
         }

      }

      if(var3) {
         try {
            var4 = Class.forName("sun.awt.Win32GraphicsDevice").getDeclaredField("valid");
            var4.set(this.aGraphicsDevice445, Boolean.TRUE);
         } catch (Throwable var14) {
            ;
         }
      }

   }

   public void method918(int var1, int var2, int var3, int var4, Frame var5, int var6) {
      this.aDisplayMode446 = this.aGraphicsDevice445.getDisplayMode();
      if(var1 > -6) {
         this.method919(false);
      }

      if(this.aDisplayMode446 == null) {
         throw new NullPointerException();
      } else {
         var5.setUndecorated(true);
         var5.enableInputMethods(false);
         this.method917(var5);
         if(var2 == 0) {
            int var7 = this.aDisplayMode446.getRefreshRate();
            DisplayMode[] var8 = this.aGraphicsDevice445.getDisplayModes();
            boolean var9 = false;

            for(int var10 = 0; var10 < var8.length; ++var10) {
               if(var8[var10].getWidth() == var6 && var8[var10].getHeight() == var4 && var3 == var8[var10].getBitDepth()) {
                  int var11 = var8[var10].getRefreshRate();
                  if(!var9 || Math.abs(var2 - var7) > Math.abs(var11 - var7)) {
                     var9 = true;
                     var2 = var11;
                  }
               }
            }

            if(!var9) {
               var2 = var7;
            }
         }

         this.aGraphicsDevice445.setDisplayMode(new DisplayMode(var6, var4, var3, var2));
      }
   }

   public int[] method919(boolean var1) {
      if(var1) {
         DisplayMode[] var2 = this.aGraphicsDevice445.getDisplayModes();
         int[] var3 = new int[var2.length << 2];

         for(int var4 = 0; var4 < var2.length; ++var4) {
            var3[var4 << 2] = var2[var4].getWidth();
            var3[1 + (var4 << 2)] = var2[var4].getHeight();
            var3[(var4 << 2) - -2] = var2[var4].getBitDepth();
            var3[3 + (var4 << 2)] = var2[var4].getRefreshRate();
         }

         return var3;
      } else {
         return null;
      }
   }

   public void method920(int var1) {
      if(this.aDisplayMode446 != null) {
         this.aGraphicsDevice445.setDisplayMode(this.aDisplayMode446);
         if(!this.aGraphicsDevice445.getDisplayMode().equals(this.aDisplayMode446)) {
            throw new RuntimeException("Did not return to correct resolution!");
         }

         this.aDisplayMode446 = null;
      }

      this.method917((Frame)null);
      int var2 = -121 / ((-64 - var1) / 47);
   }

   public Display() throws Exception {
      GraphicsEnvironment var1 = GraphicsEnvironment.getLocalGraphicsEnvironment();
      this.aGraphicsDevice445 = var1.getDefaultScreenDevice();
      if(!this.aGraphicsDevice445.isFullScreenSupported()) {
         GraphicsDevice[] var2 = var1.getScreenDevices();
         GraphicsDevice[] var3 = var2;

         for(int var4 = 0; var3.length > var4; ++var4) {
            GraphicsDevice var5 = var3[var4];
            if(var5 != null && var5.isFullScreenSupported()) {
               this.aGraphicsDevice445 = var5;
               return;
            }
         }

         throw new Exception();
      }
   }
}
