package org.runite.jagex;

import javax.media.opengl.GL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final class Class169 {

   private Class156 aClass156_2105;
   boolean aBoolean2106 = true;
   private ByteBuffer aByteBuffer2107;
   private final int anInt2108;
   private ByteBuffer aByteBuffer2109;
   private Class156 aClass156_2110;
   private static byte[] aByteArray2111 = new byte[16384];
   private int anInt2112 = -1;


   final void method2281(int[][] var1, int var2, int var3) {
      RSByteBuffer var4 = new RSByteBuffer(1620);

      int var6;
      for(int var5 = 0; var5 <= 8; ++var5) {
         for(var6 = 0; var6 <= 8; ++var6) {
            if(HDToolKit.aBoolean1790) {
               var4.method801((float)var6 / 8.0F);
               var4.method801((float)var5 / 8.0F);
               var4.method801((float)(var6 * 128));
               var4.method801((float)var1[var6 + var2][var5 + var3]);
               var4.method801((float)(var5 * 128));
            } else {
               var4.method762((float)var6 / 8.0F, (byte)104);
               var4.method762((float)var5 / 8.0F, (byte)65);
               var4.method762((float)(var6 * 128), (byte)106);
               var4.method762((float)var1[var6 + var2][var5 + var3], (byte)79);
               var4.method762((float)(var5 * 128), (byte)124);
            }
         }
      }

      if(HDToolKit.aBoolean1813) {
         ByteBuffer var9 = ByteBuffer.wrap(var4.buffer, 0, var4.index);
         this.aClass156_2110 = new Class156();
         this.aClass156_2110.method2172(var9);
      } else {
         this.aByteBuffer2109 = ByteBuffer.allocateDirect(var4.index).order(ByteOrder.nativeOrder());
         this.aByteBuffer2109.put(var4.buffer, 0, var4.index);
         this.aByteBuffer2109.flip();
      }

      RSByteBuffer var8 = new RSByteBuffer(1536);

      for(var6 = 0; var6 < 8; ++var6) {
         for(int var7 = 0; var7 < 8; ++var7) {
            if(HDToolKit.aBoolean1790) {
               var8.putInt(-125, var7 + (var6 + 1) * 9);
               var8.putInt(-128, var7 + var6 * 9);
               var8.putInt(-128, var7 + 1 + var6 * 9);
               var8.putInt(-120, var7 + (var6 + 1) * 9);
               var8.putInt(-127, var7 + 1 + var6 * 9);
               var8.putInt(-122, var7 + 1 + (var6 + 1) * 9);
            } else {
               var8.method757(var7 + (var6 + 1) * 9, 109);
               var8.method757(var7 + var6 * 9, 122);
               var8.method757(var7 + 1 + var6 * 9, 67);
               var8.method757(var7 + (var6 + 1) * 9, 116);
               var8.method757(var7 + 1 + var6 * 9, 90);
               var8.method757(var7 + 1 + (var6 + 1) * 9, 93);
            }
         }
      }

      if(HDToolKit.aBoolean1813) {
         ByteBuffer var10 = ByteBuffer.wrap(var8.buffer, 0, var8.index);
         this.aClass156_2105 = new Class156();
         this.aClass156_2105.method2170(var10);
      } else {
         this.aByteBuffer2107 = ByteBuffer.allocateDirect(var8.index).order(ByteOrder.nativeOrder());
         this.aByteBuffer2107.put(var8.buffer, 0, var8.index);
         this.aByteBuffer2107.flip();
      }

   }

   final void method2282(LDIndexedSprite var1, int var2, int var3) {
      byte[] var4 = var1.aByteArray2674;
      int var5 = var1.anInt1461;
      int var6 = var2 * 128 + 1 + (var3 * 128 + 1) * var5;
      int var7 = 0;

      int var8;
      int var9;
      for(var8 = -128; var8 < 0; ++var8) {
         var7 = (var7 << 8) - var7;

         for(var9 = -128; var9 < 0; ++var9) {
            if(var4[var6++] != 0) {
               ++var7;
            }
         }

         var6 += var5 - 128;
      }

      if(var7 == this.anInt2112) {
      } else {
         this.anInt2112 = var7;
         var6 = var2 * 128 + 1 + (var3 * 128 + 1) * var5;
         var8 = 0;

         for(var9 = -128; var9 < 0; ++var9) {
            for(int var10 = -128; var10 < 0; ++var10) {
               if(var4[var6] == 0) {
                  int var11 = 0;
                  if(var4[var6 - 1] != 0) {
                     ++var11;
                  }

                  if(var4[var6 + 1] != 0) {
                     ++var11;
                  }

                  if(var4[var6 - var5] != 0) {
                     ++var11;
                  }

                  if(var4[var6 + var5] != 0) {
                     ++var11;
                  }

                  aByteArray2111[var8++] = (byte)(17 * var11);
               } else {
                  aByteArray2111[var8++] = 68;
               }

                ++var6;
            }

            var6 += var5 - 128;
         }

         GL var12 = HDToolKit.gl;
         ByteBuffer var13 = ByteBuffer.wrap(aByteArray2111);
         var13.limit(16384);
         HDToolKit.bindTexture2D(this.anInt2108);
         var12.glTexImage2D(3553, 0, 6406, 128, 128, 0, 6406, 5121, var13);
      }
   }

   public static void method2283() {
      aByteArray2111 = null;
   }

   final void method2284() {
      javax.media.opengl.GL var1 = HDToolKit.gl;
      HDToolKit.bindTexture2D(this.anInt2108);
      if(this.aClass156_2110 == null) {
         if(HDToolKit.aBoolean1813) {
            var1.glBindBufferARB('\u8892', 0);
         }

         var1.glInterleavedArrays(10791, 20, this.aByteBuffer2109);
      } else {
         this.aClass156_2110.method2169();
         var1.glInterleavedArrays(10791, 20, 0L);
      }
      HDToolKit.aBoolean1798 = false;

      if(this.aClass156_2105 == null) {
          if(HDToolKit.aBoolean1813) {
             var1.glBindBufferARB('\u8893', 0);
          }

          var1.glDrawElements(4, 384, 5125, this.aByteBuffer2107);
       } else {
          this.aClass156_2105.method2171();
          var1.glDrawElements(4, 384, 5125, 0L);
       }

   }

   public Class169() {
      javax.media.opengl.GL var1 = HDToolKit.gl;
      int[] var2 = new int[1];
      var1.glGenTextures(1, var2, 0);
      this.anInt2108 = var2[0];
      Class31.anInt580 += 16384;
      HDToolKit.bindTexture2D(this.anInt2108);
      var1.glTexParameteri(3553, 10241, 9729);
      var1.glTexParameteri(3553, 10240, 9729);
      var1.glTexParameteri(3553, 10242, '\u812f');
      var1.glTexParameteri(3553, 10243, '\u812f');
   }

}
