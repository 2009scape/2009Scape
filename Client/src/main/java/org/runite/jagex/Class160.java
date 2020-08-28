package org.runite.jagex;

import java.nio.ByteBuffer;
import javax.media.opengl.GL;

final class Class160 implements Interface5 {

   private int anInt2187 = -1;
   private boolean aBoolean2188 = false;
   private int[] anIntArray2189 = null;


   private void method2198() {
      GL var1 = HDToolKit.gl;
      this.anInt2187 = var1.glGenLists(2);
      var1.glNewList(this.anInt2187, 4864);
      if(this.anIntArray2189 == null) {
         var1.glTexEnvi(8960, '\u8588', '\u8577');
      } else {
         var1.glActiveTexture('\u84c1');
         var1.glTexGeni(8192, 9472, '\u8511');
         var1.glTexGeni(8193, 9472, '\u8511');
         var1.glTexGeni(8194, 9472, '\u8511');
         var1.glEnable(3168);
         var1.glEnable(3169);
         var1.glEnable(3170);
         var1.glEnable('\u8513');
         var1.glMatrixMode(5890);
         var1.glLoadIdentity();
         var1.glRotatef(22.5F, 1.0F, 0.0F, 0.0F);
         var1.glMatrixMode(5888);
         if(this.aBoolean2188) {
            var1.glTexEnvi(8960, '\u8571', 260);
            var1.glTexEnvi(8960, '\u8590', 770);
            var1.glTexEnvi(8960, '\u8572', 7681);
            var1.glTexEnvi(8960, '\u8588', '\u8577');
         } else {
            var1.glTexEnvi(8960, '\u8571', 7681);
            var1.glTexEnvi(8960, '\u8580', '\u8578');
            var1.glTexEnvi(8960, '\u8572', 8448);
            var1.glActiveTexture('\u84c2');
            var1.glTexEnvi(8960, 8704, '\u8570');
            var1.glTexEnvi(8960, '\u8571', 260);
            var1.glTexEnvi(8960, '\u8580', '\u8578');
            var1.glTexEnvi(8960, '\u8581', '\u8578');
            var1.glTexEnvi(8960, '\u8591', 770);
            var1.glTexEnvi(8960, '\u8572', 7681);
            var1.glTexEnvi(8960, '\u8588', '\u8577');
            var1.glBindTexture(3553, HDToolKit.anInt1810);
            var1.glEnable(3553);
         }

          var1.glActiveTexture('\u84c0');
      }

       var1.glEndList();
      var1.glNewList(this.anInt2187 + 1, 4864);
      if(this.anIntArray2189 == null) {
         var1.glTexEnvi(8960, '\u8588', 5890);
      } else {
         var1.glActiveTexture('\u84c1');
         var1.glDisable(3168);
         var1.glDisable(3169);
         var1.glDisable(3170);
         var1.glDisable('\u8513');
         var1.glMatrixMode(5890);
         var1.glLoadIdentity();
         var1.glMatrixMode(5888);
         if(this.aBoolean2188) {
            var1.glTexEnvi(8960, '\u8571', 8448);
            var1.glTexEnvi(8960, '\u8590', 768);
            var1.glTexEnvi(8960, '\u8572', 8448);
            var1.glTexEnvi(8960, '\u8588', 5890);
         } else {
            var1.glTexEnvi(8960, '\u8571', 8448);
            var1.glTexEnvi(8960, '\u8580', 5890);
            var1.glActiveTexture('\u84c2');
            var1.glTexEnvi(8960, 8704, 8448);
            var1.glTexEnvi(8960, '\u8571', 8448);
            var1.glTexEnvi(8960, '\u8580', 5890);
            var1.glTexEnvi(8960, '\u8591', 768);
            var1.glTexEnvi(8960, '\u8572', 8448);
            var1.glTexEnvi(8960, '\u8588', 5890);
            var1.glDisable(3553);
         }

          var1.glActiveTexture('\u84c0');
      }

       var1.glEndList();
   }

   public final void method21() {
      javax.media.opengl.GL var1 = HDToolKit.gl;
      if(Class106.aBoolean1441) {
         var1.glCallList(this.anInt2187 + 1);
      } else {
         var1.glTexEnvi(8960, '\u8588', 5890);
      }

   }

   public final int method24() {
      return 4;
   }

   public final void method22() {
      javax.media.opengl.GL var1 = HDToolKit.gl;
      HDToolKit.method1847(1);
      if(Class106.aBoolean1441) {
         var1.glCallList(this.anInt2187);
      } else {
         var1.glTexEnvi(8960, '\u8588', '\u8577');
      }

   }

   public final void method23(int var1) {
      javax.media.opengl.GL var2 = HDToolKit.gl;
      if(Class106.aBoolean1441 && this.anIntArray2189 != null) {
         var2.glActiveTexture('\u84c1');
         var2.glBindTexture('\u8513', this.anIntArray2189[var1 - 1]);
         var2.glActiveTexture('\u84c0');
      }

   }

   private void method2199() {
      javax.media.opengl.GL var8 = HDToolKit.gl;
      if(this.anIntArray2189 == null) {
         this.anIntArray2189 = new int[3];
         var8.glGenTextures(3, this.anIntArray2189, 0);
      }

      short var9 = 4096;
      byte[] var10 = new byte[var9];
      byte[] var11 = new byte[var9];
      byte[] var12 = new byte[var9];

      for(int var13 = 0; var13 < 6; ++var13) {
         int var14 = 0;

         for(int var15 = 0; var15 < 64; ++var15) {
            for(int var16 = 0; var16 < 64; ++var16) {
               float var5 = 2.0F * (float)var16 / 64.0F - 1.0F;
               float var6 = 2.0F * (float)var15 / 64.0F - 1.0F;
               float var7 = (float)(1.0D / Math.sqrt((double)(var5 * var5 + 1.0F + var6 * var6)));
               var5 *= var7;
               var6 *= var7;
               float var4;
               if(var13 == 0) {
                  var4 = -var5;
               } else if(var13 == 1) {
                  var4 = var5;
               } else if(var13 == 2) {
                  var4 = var6;
               } else if(var13 == 3) {
                  var4 = -var6;
               } else if(var13 == 4) {
                  var4 = var7;
               } else {
                  var4 = -var7;
               }

               int var1;
               int var2;
               int var3;
               if(var4 > 0.0F) {
                  var1 = (int)(Math.pow((double)var4, 96.0D) * 255.0D);
                  var2 = (int)(Math.pow((double)var4, 36.0D) * 255.0D);
                  var3 = (int)(Math.pow((double)var4, 12.0D) * 255.0D);
               } else {
                  var3 = 0;
                  var2 = 0;
                  var1 = 0;
               }

               if(HDToolKit.anInt1789 < 3) {
                  var1 /= 5;
                  var2 /= 5;
                  var3 /= 5;
               } else {
                  var1 /= 2;
                  var2 /= 2;
                  var3 /= 2;
               }

               var11[var14] = (byte)var1;
               var12[var14] = (byte)var2;
               var10[var14] = (byte)var3;
               ++var14;
            }
         }

         var8.glBindTexture('\u8513', this.anIntArray2189[0]);
         var8.glTexImage2D('\u8515' + var13, 0, 6406, 64, 64, 0, 6406, 5121, ByteBuffer.wrap(var11));
         var8.glBindTexture('\u8513', this.anIntArray2189[1]);
         var8.glTexImage2D('\u8515' + var13, 0, 6406, 64, 64, 0, 6406, 5121, ByteBuffer.wrap(var12));
         var8.glBindTexture('\u8513', this.anIntArray2189[2]);
         var8.glTexImage2D('\u8515' + var13, 0, 6406, 64, 64, 0, 6406, 5121, ByteBuffer.wrap(var10));
         Class31.anInt580 += var9 * 3;
      }

   }

   public Class160() {
      if(HDToolKit.aBoolean1821 && HDToolKit.anInt1789 >= 2) {
         this.method2199();
         javax.media.opengl.GL var1 = HDToolKit.gl;
         var1.glBindTexture('\u8513', this.anIntArray2189[0]);
         var1.glTexParameteri('\u8513', 10241, 9729);
         var1.glTexParameteri('\u8513', 10240, 9729);
         var1.glTexParameteri('\u8513', '\u8072', '\u812f');
         var1.glTexParameteri('\u8513', 10242, '\u812f');
         var1.glTexParameteri('\u8513', 10243, '\u812f');
         var1.glBindTexture('\u8513', this.anIntArray2189[1]);
         var1.glTexParameteri('\u8513', 10241, 9729);
         var1.glTexParameteri('\u8513', 10240, 9729);
         var1.glTexParameteri('\u8513', '\u8072', '\u812f');
         var1.glTexParameteri('\u8513', 10242, '\u812f');
         var1.glTexParameteri('\u8513', 10243, '\u812f');
         var1.glBindTexture('\u8513', this.anIntArray2189[2]);
         var1.glTexParameteri('\u8513', 10241, 9729);
         var1.glTexParameteri('\u8513', 10240, 9729);
         var1.glTexParameteri('\u8513', '\u8072', '\u812f');
         var1.glTexParameteri('\u8513', 10242, '\u812f');
         var1.glTexParameteri('\u8513', 10243, '\u812f');
         this.aBoolean2188 = HDToolKit.anInt1789 < 3;
      }

      this.method2198();
   }
}
