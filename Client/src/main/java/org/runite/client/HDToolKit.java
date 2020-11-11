package org.runite.client;

import com.jogamp.nativewindow.awt.AWTGraphicsConfiguration;
import com.jogamp.nativewindow.awt.JAWTWindow;
import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.gl2es1.GLUgl2es1;
import jogamp.newt.awt.NewtFactoryAWT;
import org.rs09.client.config.GameConfig;

import java.awt.Canvas;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;

public final class HDToolKit {

   /**
    * GL4bc related
    */
   public static GL4bc gl;
   private static GLContext glContext;
   private static GLDrawable glDrawable;



   private static String vendor;
   private static String renderer;
   private static float aFloat1787;
   private static boolean aBoolean1788 = false;
   static int maxTextureUnits;
   static boolean aBoolean1790;
   static int anInt1791 = 0;
   private static int anInt1792 = 0;
   private static int anInt1793 = 0;
   private static float aFloat1794 = 0.0F;
   private static float aFloat1795;
   private static boolean aBoolean1796 = true;
   private static float aFloat1797 = 0.0F;
   static boolean aBoolean1798 = true;
   private static boolean viewportSetup = false;
   static boolean allows3DTextureMapping;
   private static int anInt1803 = -1;
   private static boolean aBoolean1805 = true;
   public static boolean highDetail = false;
   private static float[] aFloatArray1808 = new float[16];
   static boolean supportMultisample;
   static int anInt1810;
   public static int viewHeight;
   private static int anInt1812;
   static boolean supportVertexBufferObject;
   private static boolean aBoolean1816 = true;
   static boolean aBoolean1817;
   static boolean supportVertexProgram;
   private static RSString aClass94_1819 = RSString.parse("radeon");
   public static int viewWidth;
   static boolean supportTextureCubeMap;


   private static RSString method1820(String var0) {
      byte[] var1;
       var1 = var0.getBytes(StandardCharsets.ISO_8859_1);

       return Class3_Sub13_Sub3.method178(var1, var1.length, 0);
   }

   static void method1821(int offsetX, int offsetY, int ratioWidth, int ratioHeight) {
      viewport(0, 0, viewWidth, viewHeight, offsetX, offsetY, 0.0F, 0.0F, ratioWidth, ratioHeight);
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
      if(!viewportSetup) {
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

   static void bufferSwap() {
      try {
         glDrawable.swapBuffers();
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
      viewportSetup = false;
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
      gl.glCopyPixels(0, 0, viewWidth, viewHeight, 6144);
      gl.glPopAttrib();
      gl.glDrawBuffer(var0[0]);
      gl.glReadBuffer(var0[1]);
   }

   static void method1834(Canvas canvas) {
      try {
         if(!canvas.isDisplayable()) {
            return;
         }

         GLProfile.initSingleton();
         GLProfile profile = GLProfile.getDefault();
         AWTGraphicsConfiguration configuration = AWTGraphicsConfiguration.create(canvas.getGraphicsConfiguration(), null, null);
         JAWTWindow jawtWindow = NewtFactoryAWT.getNativeWindow(canvas, configuration);
         GLDrawableFactory glDrawableFactory = GLDrawableFactory.getFactory(profile);
         GLDrawable glDrawable = glDrawableFactory.createGLDrawable(jawtWindow);
         glDrawable.setRealized(true);
         GLContext glContext = glDrawable.createContext(null);
         glContext.makeCurrent();
         glContext.release();
         glContext.destroy();
         glDrawable.setRealized(false);
      } catch (Throwable var4) {
      }

   }

   public static void method1835() {
      Class3_Sub28_Sub4.method551(0, 0);
      method1836();
      bindTexture2D(-1);
      method1837(false);
      method1831(false);
      method1827(false);
      method1823();
   }

   private static void method1836() {
      if(!viewportSetup) {
         gl.glMatrixMode(5889);
         gl.glLoadIdentity();
         gl.glOrtho(0.0D, viewWidth, 0.0D, viewHeight, -1.0D, 1.0D);
         gl.glViewport(0, 0, viewWidth, viewHeight);
         gl.glMatrixMode(5888);
         gl.glLoadIdentity();
         viewportSetup = true;
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

   static float method1839() {
      return aFloat1794;
   }

   private static int method1840() {
      int var0 = 0;
      vendor = gl.glGetString(7936);
      renderer = gl.glGetString(7937);
      String var1 = vendor.toLowerCase();
      if(var1.contains("microsoft")) {
         var0 |= 1;
      }

      if(var1.contains("brian paul") || var1.contains("mesa")) {
         var0 |= 1;
      }

      String versionString = gl.glGetString(7938);
      String[] var3 = versionString.split("[. ]");
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
      maxTextureUnits = var12[0];
      gl.glGetIntegerv('\u8871', var12, 0);
      int anInt1814 = var12[0];
      gl.glGetIntegerv('\u8872', var12, 0);
      int anInt1806 = var12[0];
      if(maxTextureUnits < 2 || anInt1814 < 2 || anInt1806 < 2) {
         var0 |= 16;
      }

      if(var0 == 0) {
         aBoolean1790 = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
         supportVertexBufferObject = gl.isExtensionAvailable("GL_ARB_vertex_buffer_object");
         supportMultisample = gl.isExtensionAvailable("GL_ARB_multisample");
         supportTextureCubeMap = gl.isExtensionAvailable("GL_ARB_texture_cube_map");
         supportVertexProgram = gl.isExtensionAvailable("GL_ARB_vertex_program");
         allows3DTextureMapping = gl.isExtensionAvailable("GL_EXT_texture3D");
         RSString var13 = method1820(renderer).toLowercase();
         if(var13.indexOf(aClass94_1819, 57) != -1) {
            int version = 0;
            RSString[] var7 = var13.method1565().method1567(32, (byte)-98);

            for (RSString var9 : var7) {
               if (var9.length() >= 4 && var9.substring(0, 4, 0).isInteger()) {
                  version = var9.substring(0, 4, 0).parseInt();
                  break;
               }
            }

            if(version >= 7000 && version <= 7999) {
               supportVertexBufferObject = false;
            }

            if(version >= 7000 && version <= 9250) {
               allows3DTextureMapping = false;
            }

            aBoolean1817 = supportVertexBufferObject;
         }

         if(supportVertexBufferObject) {
            try {
               int[] var14 = new int[1];
               gl.glGenBuffers(1, var14, 0);
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

      if(glContext != null) {
         Class31.method988();

         try {
            if(GLContext.getCurrent() == glContext) {
               glContext.release();
            }
         } catch (Throwable var3) {
         }

         try {
            glContext.destroy();
         } catch (Throwable var2) {
         }

         glContext = null;
      }

      if(glDrawable != null) {
         try {
            glDrawable.setRealized(false);
         } catch (Throwable var1) {
         }

         glDrawable = null;
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

   static void viewport(int x, int y, int width, int height, int offsetX, int offsetY, float rotationX, float rotationY, int ratioWidth, int ratioHeight) {
      int left = (x - offsetX << 8) / ratioWidth;
      int right = (x + width - offsetX << 8) / ratioWidth;
      int top = (y - offsetY << 8) / ratioHeight;
      int bottom = (y + height - offsetY << 8) / ratioHeight;
      gl.glMatrixMode(5889);
      gl.glLoadIdentity();
      float constantFloat = 0.09765625F;
      method1848((float)left * constantFloat, (float)right * constantFloat, (float)(-bottom) * constantFloat, (float)(-top) * constantFloat, 50.0F, GameConfig.RENDER_DISTANCE_VALUE);
      gl.glViewport(x, viewHeight - y - height, width, height);
      gl.glMatrixMode(5888);
      gl.glLoadIdentity();
      gl.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
      if(rotationX != 0.0F) {
         gl.glRotatef(rotationX, 1.0F, 0.0F, 0.0F);
      }

      if(rotationY != 0.0F) {
         gl.glRotatef(rotationY, 0.0F, 1.0F, 0.0F);
      }

      viewportSetup = false;
      Class139.screenLowerX = left;
      Class145.screenUpperX = right;
      Class1.screenUpperY = top;
      AtmosphereParser.screenLowerY = bottom;
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
         //sets a texture environment parameter.
         //TEXTURE_ENV, COMBINE_ALPHA,
         if(var0 == 0) {
            gl.glTexEnvi(8960, '\u8572', 8448);//MODULATE
         }
         if(var0 == 1) {
            gl.glTexEnvi(8960, '\u8572', 7681);//REPLACE
         }
         if(var0 == 2) {
            gl.glTexEnvi(8960, '\u8572', 260);//ADD
         }
         anInt1792 = var0;
      }
   }

   private static void method1848(float left, float right, float bottom, float top, float constantFloat, float renderDistance) {
      float var6 = constantFloat * 2.0F;
      aFloatArray1808[0] = var6 / (right - left);
      aFloatArray1808[1] = 0.0F;
      aFloatArray1808[2] = 0.0F;
      aFloatArray1808[3] = 0.0F;
      aFloatArray1808[4] = 0.0F;
      aFloatArray1808[5] = var6 / (top - bottom);
      aFloatArray1808[6] = 0.0F;
      aFloatArray1808[7] = 0.0F;
      aFloatArray1808[8] = (right + left) / (right - left);
      aFloatArray1808[9] = (top + bottom) / (top - bottom);
      aFloatArray1808[10] = aFloat1787 = -(renderDistance + constantFloat) / (renderDistance - constantFloat);
      aFloatArray1808[11] = -1.0F;
      aFloatArray1808[12] = 0.0F;
      aFloatArray1808[13] = 0.0F;
      aFloatArray1808[14] = aFloat1795 = -(var6 * renderDistance) / (renderDistance - constantFloat);
      aFloatArray1808[15] = 0.0F;
      gl.glLoadMatrixf(aFloatArray1808, 0);
      aFloat1797 = 0.0F;
      aFloat1794 = 0.0F;
   }

   /**
    * clearScreen takes an int of color (can be replaced with whatever color you see fit)
    * @param color
    */
   static void clearScreen(int color) {
      gl.glClearColor((float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, 0.0F);
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

   static void depthBufferWritingDisabled() {
      gl.glDepthMask(false);
   }

   static float method1852() {
      return aFloat1797;
   }

   static void method1853(Canvas canvas, int SceneMSAASamples) {
      try {
         if(canvas.isDisplayable()) {
            GLProfile.initSingleton();
            GLProfile profile = GLProfile.getDefault();
            GLCapabilities glCapabilities = new GLCapabilities(profile);
            if(SceneMSAASamples > 0) {
               glCapabilities.setSampleBuffers(true);
               glCapabilities.setNumSamples(SceneMSAASamples);
            }

            AWTGraphicsConfiguration configuration = AWTGraphicsConfiguration.create(canvas.getGraphicsConfiguration(), glCapabilities, glCapabilities);
            JAWTWindow jawtWindow = NewtFactoryAWT.getNativeWindow(canvas, configuration);
            GLDrawableFactory glDrawableFactory = GLDrawableFactory.getFactory(profile);
            glDrawable = glDrawableFactory.createGLDrawable(jawtWindow);
            glDrawable.setRealized(true);
            int var4 = 0;
            int var5;
            while(true) {
               glContext = glDrawable.createContext(null);

               try {
                  var5 = glContext.makeCurrent();
                  if(var5 != 0) {
                     break;
                  }
               } catch (Exception var8) {
               }

               if(var4++ > 5) {
                  return;
               }

               TimeUtils.sleep(1000L);
            }

            gl = (GL4bc) glContext.getGL();
            new GLUgl2es1();
            highDetail = true;
            viewWidth = canvas.getSize().width;
            viewHeight = canvas.getSize().height;
            var5 = method1840();
            if(var5 == 0) {
               method1857();
               method1829();
               gl.glClear(16384);
               var4 = 0;

               while(true) {
                  try {
                     glDrawable.swapBuffers();
                     break;
                  } catch (Exception var7) {
                     if(var4++ > 5) {
                        method1842();
                        return;
                     }

                     TimeUtils.sleep(100L);
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
      viewWidth = var0;
      viewHeight = var1;
      viewportSetup = false;
   }

   static void method1855(int var0, int var1, int var2, int var3, int var4, int var5) {
      int var6 = -var0;
      int var7 = viewWidth - var0;
      int var8 = -var1;
      int var9 = viewHeight - var1;
      gl.glMatrixMode(5889);
      gl.glLoadIdentity();
      float var10 = (float)var2 / 512.0F;
      float var11 = var10 * (256.0F / (float)var4);
      float var12 = var10 * (256.0F / (float)var5);
      gl.glOrtho((float)var6 * var11, (float)var7 * var11, (float)(-var9) * var12, (float)(-var8) * var12, 50 - var3, 3584 - var3);
      gl.glViewport(0, 0, viewWidth, viewHeight);
      gl.glMatrixMode(5888);
      gl.glLoadIdentity();
      gl.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
      viewportSetup = false;
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
      Class3_Sub24_Sub3.method468();
   }

}
