package org.runite.jagex;

import java.nio.ByteBuffer;
import javax.media.opengl.GL;

final class HDIndexedSprite extends AbstractIndexedSprite {

   private int anInt2675 = -1;
   private int anInt2676 = -1;
   private int anInt2677 = 0;
   private int anInt2678 = 0;
   private int anInt2679;
   private int anInt2680;
   private int anInt2681;


   private void method1678(byte[] var1, int[] var2) {
      this.anInt2681 = Class95.method1585((byte)62, this.anInt1461);
      this.anInt2680 = Class95.method1585((byte)99, this.anInt1468);
      byte[] var3 = new byte[this.anInt2681 * this.anInt2680 * 4];
      int var4 = 0;
      int var5 = 0;

      for(int var6 = 0; var6 < this.anInt1468; ++var6) {
         for(int var7 = 0; var7 < this.anInt1461; ++var7) {
             // Hd Fix
        	if (var1[var5]  < 0) {
        		return;
        	}
            byte var8 = var1[var5++];
            if(var8 == 0) {
               var4 += 4;
            } else {
               int var9 = var2[var8];
               var3[var4++] = (byte)(var9 >> 16);
               var3[var4++] = (byte)(var9 >> 8);
               var3[var4++] = (byte)var9;
               var3[var4++] = -1;
            }
         }
         var4 += (this.anInt2681 - this.anInt1461) * 4;
      }
      ByteBuffer byteBuffer = ByteBuffer.wrap(var3);
      GL gl = HDToolKit.gl;
      if(this.anInt2675 == -1) {
         int[] var12 = new int[1];
         gl.glGenTextures(1, var12, 0);
         this.anInt2675 = var12[0];
         this.anInt2679 = Class31.anInt582;
      }

      HDToolKit.bindTexture2D(this.anInt2675);
      gl.glTexImage2D(3553, 0, 6408, this.anInt2681, this.anInt2680, 0, 6408, 5121, byteBuffer);
      Class31.memory2D += byteBuffer.limit() - this.anInt2678;
      this.anInt2678 = byteBuffer.limit();
   }

   final void method1666(int var1, int var2, int var3) {
      HDToolKit.method1828();
      var1 += this.anInt1470;
      var2 += this.anInt1464;
      javax.media.opengl.GL var4 = HDToolKit.gl;
      HDToolKit.bindTexture2D(this.anInt2675);
      this.method1679();
      var4.glColor4f(1.0F, 1.0F, 1.0F, (float)var3 / 256.0F);
      var4.glTranslatef((float)var1, (float)(HDToolKit.anInt1811 - var2), 0.0F);
      var4.glCallList(this.anInt2676);
      var4.glLoadIdentity();
   }

   private void method1679() {
      if(this.anInt2677 != 1) {
         this.anInt2677 = 1;
         javax.media.opengl.GL var2 = HDToolKit.gl;
          var2.glTexParameteri(3553, 10241, 9728);
          var2.glTexParameteri(3553, 10240, 9728);

      }
   }

   final void method1667(int var1, int var2) {
      HDToolKit.method1822();
      var1 += this.anInt1470;
      var2 += this.anInt1464;
      javax.media.opengl.GL var3 = HDToolKit.gl;
      HDToolKit.bindTexture2D(this.anInt2675);
      this.method1679();
      var3.glTranslatef((float)var1, (float)(HDToolKit.anInt1811 - var2), 0.0F);
      var3.glCallList(this.anInt2676);
      var3.glLoadIdentity();
   }

   protected final void finalize() throws Throwable {
      if(this.anInt2675 != -1) {
         Class31.method991(this.anInt2675, this.anInt2678, this.anInt2679);
         this.anInt2675 = -1;
         this.anInt2678 = 0;
      }

      if(this.anInt2676 != -1) {
         Class31.method986(this.anInt2676, this.anInt2679);
         this.anInt2676 = -1;
      }

      super.finalize();
   }

   private void method1680() {
      float var1 = (float)this.anInt1461 / (float)this.anInt2681;
      float var2 = (float)this.anInt1468 / (float)this.anInt2680;
      javax.media.opengl.GL var3 = HDToolKit.gl;
      if(this.anInt2676 == -1) {
         this.anInt2676 = var3.glGenLists(1);
         this.anInt2679 = Class31.anInt582;
      }

      var3.glNewList(this.anInt2676, 4864);
      var3.glBegin(6);
      var3.glTexCoord2f(var1, 0.0F);
      var3.glVertex2f((float)this.anInt1461, 0.0F);
      var3.glTexCoord2f(0.0F, 0.0F);
      var3.glVertex2f(0.0F, 0.0F);
      var3.glTexCoord2f(0.0F, var2);
      var3.glVertex2f(0.0F, (float)(-this.anInt1468));
      var3.glTexCoord2f(var1, var2);
      var3.glVertex2f((float)this.anInt1461, (float)(-this.anInt1468));
      var3.glEnd();
      var3.glEndList();
   }

   HDIndexedSprite(int var1, int var2, int var3, int var4, int var5, int var6, byte[] var7, int[] var8) {
      this.anInt1469 = var1;
      this.anInt1467 = var2;
      this.anInt1470 = var3;
      this.anInt1464 = var4;
      this.anInt1461 = var5;
      this.anInt1468 = var6;
      this.method1678(var7, var8);
      this.method1680();
   }
}
