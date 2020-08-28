package org.runite.jagex;

import javax.media.opengl.GL;
import java.nio.ByteBuffer;

final class Class3_Sub28_Sub17_Sub2 extends Class3_Sub28_Sub17 {

   private int anInt4083 = 0;
   private int anInt4084 = -1;
   private int[] anIntArray4085;
   private int anInt4086;
   private int anInt4087;


   protected final void finalize() throws Throwable {
      if(this.anInt4084 != -1) {
         Class31.method991(this.anInt4084, this.anInt4083, this.anInt4086);
         this.anInt4084 = -1;
         this.anInt4083 = 0;
      }

      if(this.anIntArray4085 != null) {
         for(int var1 = 0; var1 < this.anIntArray4085.length; ++var1) {
            Class31.method986(this.anIntArray4085[var1], this.anInt4086);
         }

         this.anIntArray4085 = null;
      }

      super.finalize();
   }

   final void method678(int var1, int var2, int var3, int var4, int var5, int var6) {
      GL var8;
      if(Class22.aClass3_Sub28_Sub16_Sub1_447 == null) {
         HDToolKit.method1824();
         var8 = HDToolKit.gl;
         HDToolKit.bindTexture2D(this.anInt4084);
         var8.glColor3ub((byte)(var6 >> 16), (byte)(var6 >> 8), (byte)var6);
         var8.glTranslatef((float)var2, (float)(HDToolKit.anInt1811 - var3), 0.0F);
         var8.glCallList(this.anIntArray4085[var1]);
      } else {
         HDToolKit.method1824();
         var8 = HDToolKit.gl;
         var8.glColor3ub((byte)(var6 >> 16), (byte)(var6 >> 8), (byte)var6);
         var8.glTranslatef((float)var2, (float)(HDToolKit.anInt1811 - var3), 0.0F);
         float var9 = (float)(var1 % 16) / 16.0F;
         float var10 = (float)(var1 / 16) / 16.0F;
         float var11 = var9 + (float)this.anIntArray3709[var1] / (float)this.anInt4087;
         float var12 = var10 + (float)this.anIntArray3721[var1] / (float)this.anInt4087;
         HDToolKit.bindTexture2D(this.anInt4084);
         Class3_Sub28_Sub16_Sub1 var13 = Class22.aClass3_Sub28_Sub16_Sub1_447;
         var8.glActiveTexture('\u84c1');
         var8.glEnable(3553);
         var8.glBindTexture(3553, var13.anInt4077);
         var8.glTexEnvi(8960, '\u8571', 7681);
         var8.glTexEnvi(8960, '\u8580', '\u8578');
         float var14 = (float)(var2 - Class22.anInt449) / (float)var13.anInt4075;
         float var15 = (float)(var3 - Class22.anInt448) / (float)var13.anInt4079;
         float var16 = (float)(var2 + var4 - Class22.anInt449) / (float)var13.anInt4075;
         float var17 = (float)(var3 + var5 - Class22.anInt448) / (float)var13.anInt4079;
         var8.glBegin(6);
         var8.glMultiTexCoord2f('\u84c1', var16, var15);
         var8.glTexCoord2f(var11, var10);
         var8.glVertex2f((float)this.anIntArray3709[var1], 0.0F);
         var8.glMultiTexCoord2f('\u84c1', var14, var15);
         var8.glTexCoord2f(var9, var10);
         var8.glVertex2f(0.0F, 0.0F);
         var8.glMultiTexCoord2f('\u84c1', var14, var17);
         var8.glTexCoord2f(var9, var12);
         var8.glVertex2f(0.0F, (float)(-this.anIntArray3721[var1]));
         var8.glMultiTexCoord2f('\u84c1', var16, var17);
         var8.glTexCoord2f(var11, var12);
         var8.glVertex2f((float)this.anIntArray3709[var1], (float)(-this.anIntArray3721[var1]));
         var8.glEnd();
         var8.glTexEnvi(8960, '\u8571', 8448);
         var8.glTexEnvi(8960, '\u8580', 5890);
         var8.glDisable(3553);
         var8.glActiveTexture('\u84c0');
      }
       var8.glLoadIdentity();

   }

   final void method679(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      HDToolKit.method1824();
      javax.media.opengl.GL var9 = HDToolKit.gl;
      HDToolKit.bindTexture2D(this.anInt4084);
      var9.glColor4ub((byte)(var6 >> 16), (byte)(var6 >> 8), (byte)var6, var7 > 255?-1:(byte)var7);
      var9.glTranslatef((float)var2, (float)(HDToolKit.anInt1811 - var3), 0.0F);
      var9.glCallList(this.anIntArray4085[var1]);
      var9.glLoadIdentity();
   }

   private void method707() {
      if(this.anIntArray4085 == null) {
         this.anIntArray4085 = new int[256];
         javax.media.opengl.GL var1 = HDToolKit.gl;

         for(int var2 = 0; var2 < 256; ++var2) {
            float var3 = (float)(var2 % 16) / 16.0F;
            float var4 = (float)(var2 / 16) / 16.0F;
            float var5 = var3 + (float)this.anIntArray3709[var2] / (float)this.anInt4087;
            float var6 = var4 + (float)this.anIntArray3721[var2] / (float)this.anInt4087;
            this.anIntArray4085[var2] = var1.glGenLists(1);
            var1.glNewList(this.anIntArray4085[var2], 4864);
            var1.glBegin(6);
            var1.glTexCoord2f(var5, var4);
            var1.glVertex2f((float)this.anIntArray3709[var2], 0.0F);
            var1.glTexCoord2f(var3, var4);
            var1.glVertex2f(0.0F, 0.0F);
            var1.glTexCoord2f(var3, var6);
            var1.glVertex2f(0.0F, (float)(-this.anIntArray3721[var2]));
            var1.glTexCoord2f(var5, var6);
            var1.glVertex2f((float)this.anIntArray3709[var2], (float)(-this.anIntArray3721[var2]));
            var1.glEnd();
            var1.glEndList();
         }

         this.anInt4086 = Class31.anInt582;
      }
   }

   Class3_Sub28_Sub17_Sub2(byte[] var1, int[] var2, int[] var3, int[] var4, int[] var5, byte[][] var6) {
      super(var1, var2, var3, var4, var5);
      this.method708(var6);
      this.method707();
   }

   private void method708(byte[][] var1) {
      if(this.anInt4084 == -1) {
         this.anInt4087 = 0;

         int var2;
         for(var2 = 0; var2 < 256; ++var2) {
            if(this.anIntArray3721[var2] > this.anInt4087) {
               this.anInt4087 = this.anIntArray3721[var2];
            }

            if(this.anIntArray3709[var2] > this.anInt4087) {
               this.anInt4087 = this.anIntArray3709[var2];
            }
         }

         this.anInt4087 *= 16;
         this.anInt4087 = Class95.method1585((byte)104, this.anInt4087);
         var2 = this.anInt4087 / 16;
         byte[] var3 = new byte[this.anInt4087 * this.anInt4087 * 2];

         for(int var4 = 0; var4 < 256; ++var4) {
            int var5 = var4 % 16 * var2;
            int var6 = var4 / 16 * var2;
            int var7 = (var6 * this.anInt4087 + var5) * 2;
            int var8 = 0;
            int var9 = this.anIntArray3721[var4];
            int var10 = this.anIntArray3709[var4];
            byte[] var11 = var1[var4];

            for(int var12 = 0; var12 < var9; ++var12) {
               for(int var13 = 0; var13 < var10; ++var13) {
                  if(var11[var8++] == 0) {
                     var7 += 2;
                  } else {
                     var3[var7++] = -1;
                     var3[var7++] = -1;
                  }
               }

               var7 += (this.anInt4087 - var10) * 2;
            }
         }

         ByteBuffer var14 = ByteBuffer.wrap(var3);
         javax.media.opengl.GL var15 = HDToolKit.gl;
         if(this.anInt4084 == -1) {
            int[] var16 = new int[1];
            var15.glGenTextures(1, var16, 0);
            this.anInt4084 = var16[0];
            this.anInt4086 = Class31.anInt582;
         }

         HDToolKit.bindTexture2D(this.anInt4084);
         var15.glTexImage2D(3553, 0, 6410, this.anInt4087, this.anInt4087, 0, 6410, 5121, var14);
         Class31.memory2D += var14.limit() - this.anInt4083;
         this.anInt4083 = var14.limit();
         var15.glTexParameteri(3553, 10241, 9728);
         var15.glTexParameteri(3553, 10240, 9728);
      }
   }
}
