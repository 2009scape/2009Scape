package org.runite.client;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL4bc;

import java.nio.IntBuffer;

public final class Class22 {

   static HDSprite aHDSprite_447 = null;
   static int anInt448 = 0;
   static int anInt449 = 0;
   private static int anInt450 = 0;
   static int anInt451 = 0;


   static void method921() {
      aHDSprite_447 = null;
   }

   public static void resetClipping() {
      anInt449 = 0;
      anInt448 = 0;
      anInt450 = HDToolKit.viewWidth;
      anInt451 = HDToolKit.viewHeight;
      GL4bc var0 = HDToolKit.gl;
      var0.glDisable(3089);
      method921();
   }

   static void method926(int[] var0, int var1, int var2, int var3, int var4) {
      HDToolKit.method1835();
      GL4bc var5 = HDToolKit.gl;
      var5.glRasterPos2i(var1, HDToolKit.viewHeight - var2);
      var5.glPixelZoom(1.0F, -1.0F);
      var5.glDisable(3042);
      var5.glDisable(3008);
      var5.glDrawPixels(var3, var4, '\u80e1', HDToolKit.aBoolean1790?'\u8367':5121, IntBuffer.wrap(var0));
      var5.glEnable(3008);
      var5.glEnable(3042);
      var5.glPixelZoom(1.0F, 1.0F);
   }

   static void method928(int var0, int var1, int var2, int var3, int var4, int var5) {
      HDToolKit.method1835();
      float var6 = (float)var0 + 0.3F;
      float var7 = var6 + (float)(var2 - 1);
      float var8 = (float)HDToolKit.viewHeight - ((float)var1 + 0.3F);
      float var9 = var8 - (float)(var3 - 1);
      GL4bc var10 = HDToolKit.gl;
      var10.glBegin(GL.GL_LINE_LOOP);
      var10.glColor4ub((byte)(var4 >> 16), (byte)(var4 >> 8), (byte)var4, var5 > 255?-1:(byte)var5);
      var10.glVertex2f(var6, var8);
      var10.glVertex2f(var6, var9);
      var10.glVertex2f(var7, var9);
      var10.glVertex2f(var7, var8);
      var10.glEnd();
   }

   static void method929(int var0, int var1, int var2, int var3, int var4, int var5) {
      int var6 = var2 - var0;
      int var7 = var3 - var1;
      int var8 = var6 >= 0?var6:-var6;
      int var9 = var7 >= 0?var7:-var7;
      int var10 = var8;
      if(var8 < var9) {
         var10 = var9;
      }

      if(var10 != 0) {
         int var11 = (var6 << 16) / var10;
         int var12 = (var7 << 16) / var10;
         if(var12 <= var11) {
            var11 = -var11;
         } else {
            var12 = -var12;
         }

         int var13 = var5 * var12 >> 17;
         int var14 = var5 * var12 + 1 >> 17;
         int var15 = var5 * var11 >> 17;
         int var16 = var5 * var11 + 1 >> 17;
         int var17 = var0 + var13;
         int var18 = var0 - var14;
         int var19 = var0 + var6 - var14;
         int var20 = var0 + var6 + var13;
         int var21 = var1 + var15;
         int var22 = var1 - var16;
         int var23 = var1 + var7 - var16;
         int var24 = var1 + var7 + var15;
         HDToolKit.method1835();
         GL4bc var25 = HDToolKit.gl;
         var25.glColor3ub((byte)(var4 >> 16), (byte)(var4 >> 8), (byte)var4);
         var25.glBegin(GL.GL_TRIANGLE_FAN);
         if(var12 <= var11) {
            var25.glVertex2f((float)var20, (float)(HDToolKit.viewHeight - var24));
            var25.glVertex2f((float)var19, (float)(HDToolKit.viewHeight - var23));
            var25.glVertex2f((float)var18, (float)(HDToolKit.viewHeight - var22));
            var25.glVertex2f((float)var17, (float)(HDToolKit.viewHeight - var21));
         } else {
            var25.glVertex2f((float)var17, (float)(HDToolKit.viewHeight - var21));
            var25.glVertex2f((float)var18, (float)(HDToolKit.viewHeight - var22));
            var25.glVertex2f((float)var19, (float)(HDToolKit.viewHeight - var23));
            var25.glVertex2f((float)var20, (float)(HDToolKit.viewHeight - var24));
         }

         var25.glEnd();
      }
   }

   static void method931(int var0, int var1, int var2, int var3) {
      if(anInt449 < var0) {
         anInt449 = var0;
      }

      if(anInt448 < var1) {
         anInt448 = var1;
      }

      if(anInt450 > var2) {
         anInt450 = var2;
      }

      if(anInt451 > var3) {
         anInt451 = var3;
      }

      GL4bc var4 = HDToolKit.gl;
      var4.glEnable(3089);
      if(anInt449 <= anInt450 && anInt448 <= anInt451) {
         var4.glScissor(anInt449, HDToolKit.viewHeight - anInt451, anInt450 - anInt449, anInt451 - anInt448);
      } else {
         var4.glScissor(0, 0, 0, 0);
      }

      method921();
   }

   static void method932() {
      HDToolKit.gl.glClear(16640);
   }

   static void method933(int var0, int var1, int var2, int var3, int var4) {
      HDToolKit.method1835();
      float var5 = (float)var0 + 0.3F;
      float var6 = (float)var2 + 0.3F;
      float var7 = (float)HDToolKit.viewHeight - ((float)var1 + 0.3F);
      float var8 = (float)HDToolKit.viewHeight - ((float)var3 + 0.3F);
      GL4bc var9 = HDToolKit.gl;
      var9.glBegin(GL.GL_LINE_LOOP);
      var9.glColor3ub((byte)(var4 >> 16), (byte)(var4 >> 8), (byte)var4);
      var9.glVertex2f(var5, var7);
      var9.glVertex2f(var6, var8);
      var9.glEnd();
   }

   public static void setClipping(int left, int top, int right, int bottom) {
      if(left < 0) {
         left = 0;
      }

      if(top < 0) {
         top = 0;
      }

      if(right > HDToolKit.viewWidth) {
         right = HDToolKit.viewWidth;
      }

      if(bottom > HDToolKit.viewHeight) {
         bottom = HDToolKit.viewHeight;
      }

      anInt449 = left;
      anInt448 = top;
      anInt450 = right;
      anInt451 = bottom;
      GL4bc var4 = HDToolKit.gl;
      var4.glEnable(3089);
      if(anInt449 <= anInt450 && anInt448 <= anInt451) {
         var4.glScissor(anInt449, HDToolKit.viewHeight - anInt451, anInt450 - anInt449, anInt451 - anInt448);
      } else {
         var4.glScissor(0, 0, 0, 0);
      }

      method921();
   }

   static void method936(HDSprite var0) {
      if(var0.anInt3696 == anInt451 - anInt448) {
         aHDSprite_447 = var0;
      } else {
         throw new IllegalArgumentException();
      }
   }

}
