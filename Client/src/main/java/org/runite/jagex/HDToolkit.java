package org.runite.jagex;

import java.awt.Canvas;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import javax.media.opengl.GL;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLCapabilitiesChooser;
import javax.media.opengl.GLContext;
import javax.media.opengl.GLDrawable;
import javax.media.opengl.GLDrawableFactory;
import javax.media.opengl.glu.GLU;

final class HDToolKit {

   private static String aString1785;
   private static String aString1786;
   private static float aFloat1787;
   private static boolean aBoolean1788 = false;
   static int anInt1789;
   static boolean aBoolean1790;
   static int anInt1791 = 0;
   private static int anInt1792 = 0;
   private static int anInt1793 = 0;
   private static float aFloat1794 = 0.0F;
   private static float aFloat1795;
   private static boolean aBoolean1796 = true;
   private static float aFloat1797 = 0.0F;
   static boolean aBoolean1798 = true;
   private static boolean aBoolean1799 = false;
   private static GLContext aGLContext1800;
   static boolean aBoolean1802;
   private static int anInt1803 = -1;
   static GL gl;
   private static boolean aBoolean1805 = true;
   static boolean highDetail = false;
   private static float[] aFloatArray1808 = new float[16];
   static boolean aBoolean1809;
   static int anInt1810;
   static int anInt1811;
   private static int anInt1812;
   static boolean aBoolean1813;
   private static GLDrawable aGLDrawable1815;
   private static boolean aBoolean1816 = true;
   static boolean aBoolean1817;
   static boolean aBoolean1818;
   private static RSString aClass94_1819 = RSString.createRSString("radeon");
   static int anInt1820;
   static boolean aBoolean1821;


   private static RSString method1820(String var0) {
      byte[] var1;
       var1 = var0.getBytes(StandardCharsets.ISO_8859_1);

       return Class3_Sub13_Sub3.method178(var1, var1.length, 0);
   }

   static void method1821(int var0, int var1, int var2, int var3) {
      method1844(0, 0, anInt1820, anInt1811, var0, var1, 0.0F, 0.0F, var2, var3);
   }

   static void method1822() {
      Class3_Sub28_Sub4.method551(0, 0);
      method1836();
      method1856(1);
      method1847(1);
      method1837(false);
      method1831(false);
      method1827(false);
      method1823();
   }

   static void method1823() {
      if(aBoolean1788) {
         gl.glMatrixMode(5890);
         gl.glLoadIdentity();
         gl.glMatrixMode(5888);
         aBoolean1788 = false;
      }

   }

   static void method1824() {
      Class3_Sub28_Sub4.method551(0, 0);
      method1836();
      method1856(0);
      method1847(0);
      method1837(false);
      method1831(false);
      method1827(false);
      method1823();
   }

   static void method1825(float var0, float var1) {
      if(!aBoolean1799) {
         if(var0 != aFloat1797 || var1 != aFloat1794) {
            aFloat1797 = var0;
            aFloat1794 = var1;
            if(var1 == 0.0F) {
               aFloatArray1808[10] = aFloat1787;
               aFloatArray1808[14] = aFloat1795;
            } else {
               float var2 = var0 / (var1 + var0);
               float var3 = var2 * var2;
               float var4 = -aFloat1795 * (1.0F - var2) * (1.0F - var2) / var1;
               aFloatArray1808[10] = aFloat1787 + var4;
               aFloatArray1808[14] = aFloat1795 * var3;
            }

             gl.glMatrixMode(5889);
            gl.glLoadMatrixf(aFloatArray1808, 0);
            gl.glMatrixMode(5888);
         }
      }
   }

   static void method1826() {
      try {
         aGLDrawable1815.swapBuffers();
      } catch (Exception var1) {
      }

   }

   static void method1827(boolean var0) {
      if(var0 != aBoolean1816) {
         if(var0) {
            gl.glEnable(2912);
         } else {
            gl.glDisable(2912);
         }

         aBoolean1816 = var0;
      }
   }

   static void method1828() {
      Class3_Sub28_Sub4.method551(0, 0);
      method1836();
      method1856(0);
      method1847(0);
      method1837(false);
      method1831(false);
      method1827(false);
      method1823();
   }

   private static void method1829() {
      aBoolean1799 = false;
      gl.glDisable(3553);
      anInt1803 = -1;
      gl.glTexEnvi(8960, 8704, '\u8570');
      gl.glTexEnvi(8960, '\u8571', 8448);
      anInt1793 = 0;
      gl.glTexEnvi(8960, '\u8572', 8448);
      anInt1792 = 0;
      gl.glEnable(2896);
      gl.glEnable(2912);
      gl.glEnable(2929);
      aBoolean1796 = true;
      aBoolean1805 = true;
      aBoolean1816 = true;
      Class44.method1073();
      gl.glActiveTexture('\u84c1');
      gl.glTexEnvi(8960, 8704, '\u8570');
      gl.glTexEnvi(8960, '\u8571', 8448);
      gl.glTexEnvi(8960, '\u8572', 8448);
      gl.glActiveTexture('\u84c0');
      gl.setSwapInterval(0);
      gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
      gl.glShadeModel(7425);
      gl.glClearDepth(1.0D);
      gl.glDepthFunc(515);
      method1830();
      gl.glMatrixMode(5890);
      gl.glLoadIdentity();
      gl.glPolygonMode(1028, 6914);
      gl.glEnable(2884);
      gl.glCullFace(1029);
      gl.glEnable(3042);
      gl.glBlendFunc(770, 771);
      gl.glEnable(3008);
      gl.glAlphaFunc(516, 0.0F);
      gl.glEnableClientState('\u8074');
      gl.glEnableClientState('\u8075');
      aBoolean1798 = true;
      gl.glEnableClientState('\u8076');
      gl.glEnableClientState('\u8078');
      gl.glMatrixMode(5888);
      gl.glLoadIdentity();
      Class92.method1511();
      Class68.method1275();
   }

   static void method1830() {
      gl.glDepthMask(true);
   }

   static void method1831(boolean var0) {
      if(var0 != aBoolean1805) {
         if(var0) {
            gl.glEnable(2929);
         } else {
            gl.glDisable(2929);
         }

         aBoolean1805 = var0;
      }
   }

   static void method1832(float var0) {
      method1825(3000.0F, var0 * 1.5F);
   }

   static void method1833() {
      int[] var0 = new int[2];
      gl.glGetIntegerv(3073, var0, 0);
      gl.glGetIntegerv(3074, var0, 1);
      gl.glDrawBuffer(1026);
      gl.glReadBuffer(1024);
      bindTexture2D(-1);
      gl.glPushAttrib(8192);
      gl.glDisable(2912);
      gl.glDisable(3042);
      gl.glDisable(2929);
      gl.glDisable(3008);
      gl.glRasterPos2i(0, 0);
      gl.glCopyPixels(0, 0, anInt1820, anInt1811, 6144);
      gl.glPopAttrib();
      gl.glDrawBuffer(var0[0]);
      gl.glReadBuffer(var0[1]);
   }

   static void method1834(Canvas var0) {
      try {
         if(!var0.isDisplayable()) {
            return;
         }

         GLDrawableFactory var1 = GLDrawableFactory.getFactory();
         GLDrawable var2 = var1.getGLDrawable(var0, (GLCapabilities)null, (GLCapabilitiesChooser)null);
         var2.setRealized(true);
         GLContext var3 = var2.createContext((GLContext)null);
         var3.makeCurrent();
         var3.release();
         var3.destroy();
         var2.setRealized(false);
      } catch (Throwable var4) {
      }

   }

   static void method1835() {
      Class3_Sub28_Sub4.method551(0, 0);
      method1836();
      bindTexture2D(-1);
      method1837(false);
      method1831(false);
      method1827(false);
      method1823();
   }

   private static void method1836() {
      if(!aBoolean1799) {
         gl.glMatrixMode(5889);
         gl.glLoadIdentity();
         gl.glOrtho(0.0D, (double)anInt1820, 0.0D, (double)anInt1811, -1.0D, 1.0D);
         gl.glViewport(0, 0, anInt1820, anInt1811);
         gl.glMatrixMode(5888);
         gl.glLoadIdentity();
         aBoolean1799 = true;
      }
   }

   static void method1837(boolean var0) {
      if(var0 != aBoolean1796) {
         if(var0) {
            gl.glEnable(2896);
         } else {
            gl.glDisable(2896);
         }

         aBoolean1796 = var0;
      }
   }

   public static void method1838() {
      aClass94_1819 = null;
      aString1786 = null;
      aString1785 = null;
      gl = null;
      aGLDrawable1815 = null;
      aGLContext1800 = null;
      aFloatArray1808 = null;
   }

   static float method1839() {
      return aFloat1794;
   }

   private static int method1840() {
      int var0 = 0;
      aString1785 = gl.glGetString(7936);
      aString1786 = gl.glGetString(7937);
      String var1 = aString1785.toLowerCase();
      if(var1.indexOf("microsoft") != -1) {
         var0 |= 1;
      }

      if(var1.indexOf("brian paul") != -1 || var1.indexOf("mesa") != -1) {
         var0 |= 1;
      }

      String var2 = gl.glGetString(7938);
      String[] var3 = var2.split("[. ]");
      if(var3.length >= 2) {
         try {
            int var4 = Integer.parseInt(var3[0]);
            int var5 = Integer.parseInt(var3[1]);
            anInt1812 = var4 * 10 + var5;
         } catch (NumberFormatException var11) {
            var0 |= 4;
         }
      } else {
         var0 |= 4;
      }

      if(anInt1812 < 12) {
         var0 |= 2;
      }

      if(!gl.isExtensionAvailable("GL_ARB_multitexture")) {
         var0 |= 8;
      }

      if(!gl.isExtensionAvailable("GL_ARB_texture_env_combine")) {
         var0 |= 32;
      }

      int[] var12 = new int[1];
      gl.glGetIntegerv('\u84e2', var12, 0);
      anInt1789 = var12[0];
      gl.glGetIntegerv('\u8871', var12, 0);
      int anInt1814 = var12[0];
      gl.glGetIntegerv('\u8872', var12, 0);
      int anInt1806 = var12[0];
      if(anInt1789 < 2 || anInt1814 < 2 || anInt1806 < 2) {
         var0 |= 16;
      }

      if(var0 == 0) {
         aBoolean1790 = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
         aBoolean1813 = gl.isExtensionAvailable("GL_ARB_vertex_buffer_object");
         aBoolean1809 = gl.isExtensionAvailable("GL_ARB_multisample");
         aBoolean1821 = gl.isExtensionAvailable("GL_ARB_texture_cube_map");
         aBoolean1818 = gl.isExtensionAvailable("GL_ARB_vertex_program");
         aBoolean1802 = gl.isExtensionAvailable("GL_EXT_texture3D");
         RSString var13 = method1820(aString1786).method1534();
         if(var13.indexOf(aClass94_1819, 57) != -1) {
            int var6 = 0;
            RSString[] var7 = var13.method1565().method1567(32, (byte)-98);

            for(int var8 = 0; var8 < var7.length; ++var8) {
               RSString var9 = var7[var8];
               if(var9.length(-125) >= 4 && var9.method1557(4, 0, 0).method1543(106)) {
                  var6 = var9.method1557(4, 0, 0).method1552((byte)-113);
                  break;
               }
            }

            if(var6 >= 7000 && var6 <= 7999) {
               aBoolean1813 = false;
            }

            if(var6 >= 7000 && var6 <= 9250) {
               aBoolean1802 = false;
            }

            aBoolean1817 = aBoolean1813;
         }

         if(aBoolean1813) {
            try {
               int[] var14 = new int[1];
               gl.glGenBuffersARB(1, var14, 0);
            } catch (Throwable var10) {
               return -4;
            }
         }

         return 0;
      } else {
         return var0;
      }
   }

   static void method1841() {
      gl.glClear(256);
   }

   static void method1842() {
      if(gl != null) {
         try {
            Class101.method1609();
         } catch (Throwable var4) {
         }

         gl = null;
      }

      if(aGLContext1800 != null) {
         Class31.method988();

         try {
            if(GLContext.getCurrent() == aGLContext1800) {
               aGLContext1800.release();
            }
         } catch (Throwable var3) {
         }

         try {
            aGLContext1800.destroy();
         } catch (Throwable var2) {
         }

         aGLContext1800 = null;
      }

      if(aGLDrawable1815 != null) {
         try {
            aGLDrawable1815.setRealized(false);
         } catch (Throwable var1) {
         }

         aGLDrawable1815 = null;
      }

      Class68.method1273();
      highDetail = false;
   }

   static void method1843(float var0, float var1) {
      gl.glMatrixMode(5890);
      if(aBoolean1788) {
         gl.glLoadIdentity();
      }

      gl.glTranslatef(var0, var1, (float) 0.0);
      gl.glMatrixMode(5888);
      aBoolean1788 = true;
   }

   static void method1844(int var0, int var1, int var2, int var3, int var4, int var5, float var6, float var7, int var8, int var9) {
      int var10 = (var0 - var4 << 8) / var8;
      int var11 = (var0 + var2 - var4 << 8) / var8;
      int var12 = (var1 - var5 << 8) / var9;
      int var13 = (var1 + var3 - var5 << 8) / var9;
      gl.glMatrixMode(5889);
      gl.glLoadIdentity();
      float aFloat1801 = 0.09765625F;
      method1848((float)var10 * aFloat1801, (float)var11 * aFloat1801, (float)(-var13) * aFloat1801, (float)(-var12) * aFloat1801);
      gl.glViewport(var0, anInt1811 - var1 - var3, var2, var3);
      gl.glMatrixMode(5888);
      gl.glLoadIdentity();
      gl.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
      if(var6 != 0.0F) {
         gl.glRotatef(var6, 1.0F, 0.0F, 0.0F);
      }

      if(var7 != 0.0F) {
         gl.glRotatef(var7, 0.0F, 1.0F, 0.0F);
      }

      aBoolean1799 = false;
      Class139.anInt1824 = var10;
      Class145.anInt1898 = var11;
      Class1.anInt55 = var12;
      Class86.anInt1195 = var13;
   }

   private static void method1845(boolean var0) {
      if(var0 != aBoolean1798) {
         if(var0) {
            gl.glEnableClientState('\u8075');
         } else {
            gl.glDisableClientState('\u8075');
         }

         aBoolean1798 = var0;
      }
   }

   static void method1846() {
      if(Class106.aBoolean1441) {
         method1837(true);
         method1845(true);
      } else {
         method1837(false);
         method1845(false);
      }

   }

   static void method1847(int var0) {
      if(var0 != anInt1792) {
         if(var0 == 0) {
            gl.glTexEnvi(8960, '\u8572', 8448);
         }

         if(var0 == 1) {
            gl.glTexEnvi(8960, '\u8572', 7681);
         }

         if(var0 == 2) {
            gl.glTexEnvi(8960, '\u8572', 260);
         }

         anInt1792 = var0;
      }
   }

   private static void method1848(float var0, float var1, float var2, float var3) {
      float var6 = (float) 50.0 * 2.0F;
      aFloatArray1808[0] = var6 / (var1 - var0);
      aFloatArray1808[1] = 0.0F;
      aFloatArray1808[2] = 0.0F;
      aFloatArray1808[3] = 0.0F;
      aFloatArray1808[4] = 0.0F;
      aFloatArray1808[5] = var6 / (var3 - var2);
      aFloatArray1808[6] = 0.0F;
      aFloatArray1808[7] = 0.0F;
      aFloatArray1808[8] = (var1 + var0) / (var1 - var0);
      aFloatArray1808[9] = (var3 + var2) / (var3 - var2);
      aFloatArray1808[10] = aFloat1787 = -((float) 3584.0 + (float) 50.0) / ((float) 3584.0 - (float) 50.0);
      aFloatArray1808[11] = -1.0F;
      aFloatArray1808[12] = 0.0F;
      aFloatArray1808[13] = 0.0F;
      aFloatArray1808[14] = aFloat1795 = -(var6 * (float) 3584.0) / ((float) 3584.0 - (float) 50.0);
      aFloatArray1808[15] = 0.0F;
      gl.glLoadMatrixf(aFloatArray1808, 0);
      aFloat1797 = 0.0F;
      aFloat1794 = 0.0F;
   }

   static void method1849(int var0) {
      gl.glClearColor((float)(var0 >> 16 & 255) / 255.0F, (float)(var0 >> 8 & 255) / 255.0F, (float)(var0 & 255) / 255.0F, 0.0F);
      gl.glClear(16640);
      gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
   }

   static void bindTexture2D(int var0) {
      if(var0 != anInt1803) {
         if(var0 == -1) {
            gl.glDisable(3553);
         } else {
            if(anInt1803 == -1) {
               gl.glEnable(3553);
            }

            gl.glBindTexture(3553, var0);
         }

          anInt1803 = var0;
      }
   }

   static void method1851() {
      gl.glDepthMask(false);
   }

   static float method1852() {
      return aFloat1797;
   }

   static void method1853(Canvas var0, int var1) {
      try {
         if(var0.isDisplayable()) {
            GLCapabilities var2 = new GLCapabilities();
            if(var1 > 0) {
               var2.setSampleBuffers(true);
               var2.setNumSamples(var1);
            }

            GLDrawableFactory var3 = GLDrawableFactory.getFactory();
            aGLDrawable1815 = var3.getGLDrawable(var0, var2, (GLCapabilitiesChooser)null);
            aGLDrawable1815.setRealized(true);
            int var4 = 0;

            int var5;
            while(true) {
               aGLContext1800 = aGLDrawable1815.createContext((GLContext)null);

               try {
                  var5 = aGLContext1800.makeCurrent();
                  if(var5 != 0) {
                     break;
                  }
               } catch (Exception var8) {
               }

               if(var4++ > 5) {
                  return;
               }

               Class3_Sub13_Sub34.method331(1000L, 64);
            }

            gl = aGLContext1800.getGL();
            new GLU();
            highDetail = true;
            anInt1820 = var0.getSize().width;
            anInt1811 = var0.getSize().height;
            var5 = method1840();
            if(var5 == 0) {
               method1857();
               method1829();
               gl.glClear(16384);
               var4 = 0;

               while(true) {
                  try {
                     aGLDrawable1815.swapBuffers();
                     break;
                  } catch (Exception var7) {
                     if(var4++ > 5) {
                        method1842();
                        return;
                     }

                     Class3_Sub13_Sub34.method331(100L, 64);
                  }
               }

               gl.glClear(16384);
            } else {
               method1842();
            }
         } else {
         }
      } catch (Throwable var9) {
         method1842();
      }
   }

   static void method1854(int var0, int var1) {
      anInt1820 = var0;
      anInt1811 = var1;
      aBoolean1799 = false;
   }

   static void method1855(int var0, int var1, int var2, int var3, int var4, int var5) {
      int var6 = -var0;
      int var7 = anInt1820 - var0;
      int var8 = -var1;
      int var9 = anInt1811 - var1;
      gl.glMatrixMode(5889);
      gl.glLoadIdentity();
      float var10 = (float)var2 / 512.0F;
      float var11 = var10 * (256.0F / (float)var4);
      float var12 = var10 * (256.0F / (float)var5);
      gl.glOrtho((double)((float)var6 * var11), (double)((float)var7 * var11), (double)((float)(-var9) * var12), (double)((float)(-var8) * var12), (double)(50 - var3), (double)(3584 - var3));
      gl.glViewport(0, 0, anInt1820, anInt1811);
      gl.glMatrixMode(5888);
      gl.glLoadIdentity();
      gl.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
      aBoolean1799 = false;
   }

   static void method1856(int var0) {
      if(var0 != anInt1793) {
         if(var0 == 0) {
            gl.glTexEnvi(8960, '\u8571', 8448);
         }

         if(var0 == 1) {
            gl.glTexEnvi(8960, '\u8571', 7681);
         }

         if(var0 == 2) {
            gl.glTexEnvi(8960, '\u8571', 260);
         }

         if(var0 == 3) {
            gl.glTexEnvi(8960, '\u8571', '\u84e7');
         }

         if(var0 == 4) {
            gl.glTexEnvi(8960, '\u8571', '\u8574');
         }

         if(var0 == 5) {
            gl.glTexEnvi(8960, '\u8571', '\u8575');
         }

         anInt1793 = var0;
      }
   }

   private static void method1857() {
      int[] var0 = new int[1];
      gl.glGenTextures(1, var0, 0);
      anInt1810 = var0[0];
      gl.glBindTexture(3553, anInt1810);
      gl.glTexImage2D(3553, 0, 4, 1, 1, 0, 6408, 5121, IntBuffer.wrap(new int[]{-1}));
      Class68.method1276();
      Class3_Sub24_Sub3.method468(6);
   }

}
