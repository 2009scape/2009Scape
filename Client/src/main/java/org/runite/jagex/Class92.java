package org.runite.jagex;

import javax.media.opengl.GL;

final class Class92 {

   static float[] light0Position = new float[4];
   private static int screenColorRgb = -1;
   static int lightX;
   static int lightY;
   static int defaulFogColorRgb = 13156520;
   private static float light0Diffuse = -1.0F;
   private static float light1Diffuse = -1.0F;
   static float[] fogColor = new float[4];
   private static float lightModelAmbient;
   private static float[] light1Position = new float[4];
   static int defaultScreenColorRgb = 16777215;
   private static int fogOffset = -1;
   private static int fogColorRGB = -1;


   static void method1504() {
      GL gl = HDToolKit.gl;
      gl.glLightfv(16384, 4611, light0Position, 0);
      gl.glLightfv(16385, 4611, light1Position, 0);
   }

   static float getLightingModelAmbient() {
      return light0Diffuse;
   }

   static void setLightParams(int color, float ambientMod, float l0Diffuse, float l1Diffuse) {
         if (screenColorRgb != color || lightModelAmbient != ambientMod || light0Diffuse != l0Diffuse || light1Diffuse != l1Diffuse) {
            screenColorRgb = color;
            lightModelAmbient = ambientMod;
            light0Diffuse = l0Diffuse;
            light1Diffuse = l1Diffuse;
            final GL gl = HDToolKit.gl;
            final float red = (color >> 16 & 0xff) / 255.0F;
            final float green = (color >> 8 & 0xff) / 255.0F;
            final float blue = (color & 0xff) / 255.0F;
            final float[] lightModelAmbientParams = { ambientMod * red, ambientMod * green, ambientMod * blue, 1.0F };
            gl.glLightModelfv(2899, lightModelAmbientParams, 0);//LIGHT_MODEL_AMBIENT
            final float[] light0Params = { l0Diffuse * red, l0Diffuse * green, l0Diffuse * blue, 1.0F };
            gl.glLightfv(16384, 4609, light0Params, 0);//LIGHT0, DIFFUSE
            final float[] light1Params = { -l1Diffuse * red, -l1Diffuse * green, -l1Diffuse * blue, 1.0F };
            gl.glLightfv(16385, 4609, light1Params, 0);//LIGHT1, DIFFUSE
         }
   }

   public static void method1507() {
      light0Position = null;
      light1Position = null;
      fogColor = null;
   }

   static void setFogValues(int fogCol, int fogOff) {
      if(fogColorRGB != fogCol || fogOffset != fogOff) {
         fogColorRGB = fogCol;
         fogOffset = fogOff;
         final GL gl = HDToolKit.gl;
         byte lowestFogStart = 50;
         //short baseFogStart = 3584; This is unused because it was originally this but to avoid math jagex simplified it.
         fogColor[0] = (fogCol >> 16 & 0xff) / 255.0F;
         fogColor[1] = (fogCol >> 8 & 0xff) / 255.0F;
         fogColor[2] = (fogCol & 0xff) / 255.0F;
         //2917 FOG_MODE
         //9729 LINEAR
         gl.glFogi(2917, 9729);
         //FOG_DENSITY
         gl.glFogf(2914, 0.95F);
         //3156 = FOG_HINT
         //4353 = FASTEST, 4354 = NICEST, 4352 = DONT_CARE
         gl.glHint(3156, 4353);
         int fogStart = 3072 - fogOff;//baseFogStart - 512 - fogOff
         if (fogStart < lowestFogStart) {
            fogStart = lowestFogStart;
         }
         //FOG_START
         gl.glFogf(2915, fogStart);
         //FOG_END
         gl.glFogf(2916, 3328.0F);//baseFogStart - 256
         //FOG_COLOR
         gl.glFogfv(2918, fogColor, 0);
      }
   }

   static void setLightPosition(float x, float y, float z) {
      if(light0Position[0] != x || light0Position[1] != y || light0Position[2] != z) {
         light0Position[0] = x;
         light0Position[1] = y;
         light0Position[2] = z;
         light1Position[0] = -x;
         light1Position[1] = -y;
         light1Position[2] = -z;
         lightX = (int)(x * 256.0F / y);
         lightY = (int)(z * 256.0F / y);
      }
   }

   static int screenColorRgb() {
      return screenColorRgb;
   }

   static void method1511() {
      final GL gl = HDToolKit.gl;
      gl.glColorMaterial(1028, 5634);//FRONT, AMBIENT_AND_DIFFUSE
      gl.glEnable(2903);//COLOR_MATERIAL
      final float[] light0Params = { 0.0F, 0.0F, 0.0F, 1.0F };
      gl.glLightfv(16384, 4608, light0Params, 0);//LIGHT0, AMBIENT
      gl.glEnable(16384);//LIGHT0
      final float[] light1Params = { 0.0F, 0.0F, 0.0F, 1.0F };
      gl.glLightfv(16385, 4608, light1Params, 0);//LIGHT1, AMBIENT
      gl.glEnable(16385);//LIGHT1
      screenColorRgb = -1;
      fogColorRGB = -1;
      initDefaults();
   }

   static void method1512(float[] var0) {
      if(var0 == null) {
         var0 = fogColor;
      }

      GL gl = HDToolKit.gl;
      gl.glFogfv(2918, var0, 0);
   }

   private static void initDefaults() {
      setLightParams(defaultScreenColorRgb, 1.1523438F, 0.69921875F, 1.2F);
      setLightPosition(-50.0F, -60.0F, -50.0F);
      setFogValues(defaulFogColorRgb, 0);
   }

   static float method1514() {
      return lightModelAmbient;
   }

}
