package org.runite.jagex;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.media.opengl.GL;

final class Class3_Sub11 extends Class3 {

   int anInt2342 = 0;
   int anInt2343 = 0;
   int anInt2344 = 0;
   private ByteBuffer aByteBuffer2345;
   private float[] aFloatArray2346;
   private final boolean aBoolean2347;
   private int[] anIntArray2348;
   private int[] anIntArray2349;
   private int[] anIntArray2350;
   int anInt2351;
   private int[] anIntArray2352;
   private Class130 aClass130_2353;
   private float[] aFloatArray2354;
   int anInt2355;
   private int anInt2356 = 0;
   private int[][] anIntArrayArray2357;
   private int[] anIntArray2358;
   private int anInt2359 = 0;
   private int[][] anIntArrayArray2360;
   private static ByteBuffer aByteBuffer2361;
   private static RSByteBuffer aClass3_Sub30_2362;
   private Class156 aClass156_2363;
   boolean aBoolean2364;
   private float[] aFloatArray2365;
   private int anInt2366 = 0;
   private int[] anIntArray2367;
   private static ByteBuffer aByteBuffer2368;
   private float[] aFloatArray2369;
   private boolean[] aBooleanArray2370;
   private int[] anIntArray2371;
   private static RSByteBuffer aClass3_Sub30_2372;
   private final float aFloat2373;


   static void method144() {
      aClass3_Sub30_2372 = null;
      aClass3_Sub30_2362 = null;
      aByteBuffer2368 = null;
      aByteBuffer2361 = null;
   }

   final void method145() {
      this.anIntArray2371 = new int[this.anInt2342];
      this.anIntArray2358 = new int[this.anInt2342];
      this.anIntArray2352 = new int[this.anInt2342];
      if(this.aBoolean2347) {
         this.aFloatArray2346 = new float[this.anInt2342];
      }

      this.anIntArray2348 = new int[this.anInt2342];
      this.aFloatArray2369 = new float[this.anInt2342];
      this.aFloatArray2365 = new float[this.anInt2342];
      this.aFloatArray2354 = new float[this.anInt2342];
      this.anIntArray2350 = new int[this.anInt2344];
      this.anIntArray2349 = new int[this.anInt2344];
      this.anIntArray2367 = new int[this.anInt2344];
      this.anIntArrayArray2357 = new int[this.anInt2344][];
      this.aClass130_2353 = new Class130(Class95.method1585((byte)123, this.anInt2342));
      if(this.aBoolean2364) {
         this.anIntArrayArray2360 = new int[this.anInt2344][];
         this.aBooleanArray2370 = new boolean[this.anInt2344];
      }

   }

   final int method146(int var1, int var2, int var3, float var4, float var5, float var6, int var7, float var8) {
      long var9 = 0L;
      if((var1 & 127) == 0 || (var3 & 127) == 0) {
         var9 = (long)(var1 + (var3 << 16)) + ((long)var7 << 32);
         Class3_Sub18 var11 = (Class3_Sub18)this.aClass130_2353.method1780(var9, 0);
         if(var11 != null) {
            if(var2 < this.anIntArray2358[var11.anInt2467]) {
               this.anIntArray2358[var11.anInt2467] = var2;
            }

            return var11.anInt2467;
         }
      }

      this.anIntArray2371[this.anInt2343] = var1;
      this.anIntArray2358[this.anInt2343] = var2;
      this.anIntArray2352[this.anInt2343] = var3;
      if(this.aBoolean2347) {
         this.aFloatArray2346[this.anInt2343] = var8;
      }

      this.aFloatArray2369[this.anInt2343] = var4;
      this.aFloatArray2365[this.anInt2343] = var5;
      this.aFloatArray2354[this.anInt2343] = var6;
      this.anIntArray2348[this.anInt2343] = var7;
      if(var9 != 0L) {
         this.aClass130_2353.method1779(new Class3_Sub18(this.anInt2343), var9);
      }

      return this.anInt2343++;
   }

   public static void method147() {
      aClass3_Sub30_2372 = null;
      aClass3_Sub30_2362 = null;
      aByteBuffer2368 = null;
      aByteBuffer2361 = null;
   }

   final void method148() {
      RSByteBuffer var1 = new RSByteBuffer((this.aBoolean2347?40:36) * this.anInt2343);

      for(int var2 = 0; var2 < this.anInt2343; ++var2) {
         if(HDToolKit.aBoolean1790) {
            var1.method801((float)this.anIntArray2371[var2]);
            var1.method801((float)this.anIntArray2358[var2]);
            var1.method801((float)this.anIntArray2352[var2]);
            var1.putInt(-123, this.anIntArray2348[var2]);
            var1.method801(this.aFloatArray2369[var2]);
            var1.method801(this.aFloatArray2365[var2]);
            var1.method801(this.aFloatArray2354[var2]);
            var1.method801((float)this.anIntArray2371[var2] / this.aFloat2373);
            var1.method801((float)this.anIntArray2352[var2] / this.aFloat2373);
            if(this.aBoolean2347) {
               var1.method801(this.aFloatArray2346[var2]);
            }
         } else {
            var1.method762((float)this.anIntArray2371[var2], (byte)94);
            var1.method762((float)this.anIntArray2358[var2], (byte)121);
            var1.method762((float)this.anIntArray2352[var2], (byte)101);
            var1.putInt(-124, this.anIntArray2348[var2]);
            var1.method762(this.aFloatArray2369[var2], (byte)121);
            var1.method762(this.aFloatArray2365[var2], (byte)117);
            var1.method762(this.aFloatArray2354[var2], (byte)77);
            var1.method762((float)this.anIntArray2371[var2] / this.aFloat2373, (byte)97);
            var1.method762((float)this.anIntArray2352[var2] / this.aFloat2373, (byte)101);
            if(this.aBoolean2347) {
               var1.method762(this.aFloatArray2346[var2], (byte)126);
            }
         }
      }

      if(HDToolKit.aBoolean1813) {
         ByteBuffer var3 = ByteBuffer.wrap(var1.buffer, 0, var1.index);
         this.aClass156_2363 = new Class156();
         this.aClass156_2363.method2172(var3);
      } else {
         this.aByteBuffer2345 = ByteBuffer.allocateDirect(var1.index).order(ByteOrder.nativeOrder());
         this.aByteBuffer2345.put(var1.buffer, 0, var1.index);
         this.aByteBuffer2345.flip();
      }

      this.anIntArray2371 = null;
      this.anIntArray2358 = null;
      this.anIntArray2352 = null;
      this.anIntArray2348 = null;
      this.aFloatArray2369 = null;
      this.aFloatArray2365 = null;
      this.aFloatArray2354 = null;
      this.aClass130_2353 = null;
      this.aFloatArray2346 = null;
   }

   final void method149(Class3_Sub2[][][] var1, float var2, boolean var3) {
      if(aClass3_Sub30_2372 != null && aClass3_Sub30_2372.buffer.length >= this.anInt2359 * 4) {
         aClass3_Sub30_2372.index = 0;
      } else {
         aClass3_Sub30_2372 = new RSByteBuffer(this.anInt2359 * 4);
      }

      if(aClass3_Sub30_2362 != null && aClass3_Sub30_2362.buffer.length >= this.anInt2356 * 4) {
         aClass3_Sub30_2362.index = 0;
      } else {
         aClass3_Sub30_2362 = new RSByteBuffer(this.anInt2356 * 4);
      }

      int var4;
      Class3_Sub2 var5;
      RSByteBuffer var6;
      int[] var7;
      int[] var8;
      int var9;
      int var12;
      if(HDToolKit.aBoolean1790) {
         for(var4 = 0; var4 < this.anInt2366; ++var4) {
            var5 = var1[this.anIntArray2367[var4]][this.anIntArray2350[var4]][this.anIntArray2349[var4]];
            if(var5 != null && var5.aBoolean2222) {
               var7 = this.anIntArrayArray2357[var4];
               if(this.aBoolean2364) {
                  var8 = this.anIntArrayArray2360[var4];
                  if(var8 != null) {
                     for(var9 = 0; var9 < var8.length; ++var9) {
                        aClass3_Sub30_2362.putInt(-124, var8[var9]);
                     }
                  }

                  var6 = this.aBooleanArray2370[var4]?aClass3_Sub30_2362:aClass3_Sub30_2372;
               } else {
                  var6 = aClass3_Sub30_2372;
               }

               for(var12 = 1; var12 < var7.length - 1; ++var12) {
                  var6.putInt(-128, var7[0]);
                  var6.putInt(-123, var7[var12]);
                  var6.putInt(-123, var7[var12 + 1]);
               }
            }
         }
      } else {
         for(var4 = 0; var4 < this.anInt2366; ++var4) {
            var5 = var1[this.anIntArray2367[var4]][this.anIntArray2350[var4]][this.anIntArray2349[var4]];
            if(var5 != null && var5.aBoolean2222) {
               var7 = this.anIntArrayArray2357[var4];
               if(this.aBoolean2364) {
                  var8 = this.anIntArrayArray2360[var4];
                  if(var8 != null) {
                     for(var9 = 0; var9 < var8.length; ++var9) {
                        aClass3_Sub30_2362.method757(var8[var9], 79);
                     }
                  }

                  var6 = this.aBooleanArray2370[var4]?aClass3_Sub30_2362:aClass3_Sub30_2372;
               } else {
                  var6 = aClass3_Sub30_2372;
               }

               for(var12 = 1; var12 < var7.length - 1; ++var12) {
                  var6.method757(var7[0], 84);
                  var6.method757(var7[var12], 103);
                  var6.method757(var7[var12 + 1], 82);
               }
            }
         }
      }

      if(aClass3_Sub30_2372.index != 0 || aClass3_Sub30_2362.index != 0) {
         GL var10 = HDToolKit.gl;
         if(this.anInt2351 == -1 || var3) {
            HDToolKit.bindTexture2D(-1);
            Class3_Sub28_Sub4.method551(0, 0);
         } else {
            Class51.anInterface2_838.method8(this.anInt2351, true);
         }

          int var11 = this.aBoolean2347?40:36;
         if(this.aClass156_2363 == null) {
            if(HDToolKit.aBoolean1813) {
               var10.glBindBufferARB('\u8892', 0);
            }

            this.aByteBuffer2345.position(0);
            var10.glVertexPointer(3, 5126, var11, this.aByteBuffer2345);
            this.aByteBuffer2345.position(12);
            var10.glColorPointer(4, 5121, var11, this.aByteBuffer2345);
            if(Class106.aBoolean1441) {
               this.aByteBuffer2345.position(16);
               var10.glNormalPointer(5126, var11, this.aByteBuffer2345);
            }

            this.aByteBuffer2345.position(28);
            var10.glTexCoordPointer(2, 5126, var11, this.aByteBuffer2345);
            if(this.aBoolean2347) {
               var10.glClientActiveTexture(Class165.method2252());
               this.aByteBuffer2345.position(36);
               var10.glTexCoordPointer(1, 5126, var11, this.aByteBuffer2345);
               var10.glClientActiveTexture('\u84c0');
            }
         } else {
            this.aClass156_2363.method2169();
            var10.glVertexPointer(3, 5126, var11, 0L);
            var10.glColorPointer(4, 5121, var11, 12L);
            if(Class106.aBoolean1441) {
               var10.glNormalPointer(5126, var11, 16L);
            }

            var10.glTexCoordPointer(2, 5126, var11, 28L);
            if(this.aBoolean2347) {
               var10.glClientActiveTexture(Class165.method2252());
               var10.glTexCoordPointer(1, 5126, var11, 36L);
               var10.glClientActiveTexture('\u84c0');
            }
         }

          if(HDToolKit.aBoolean1813) {
            var10.glBindBufferARB('\u8893', 0);
         }

         if(aClass3_Sub30_2372.index != 0) {
            if(aByteBuffer2368 != null && aByteBuffer2368.capacity() >= aClass3_Sub30_2372.index) {
               aByteBuffer2368.clear();
            } else {
               aByteBuffer2368 = ByteBuffer.allocateDirect(aClass3_Sub30_2372.index).order(ByteOrder.nativeOrder());
            }

            aByteBuffer2368.put(aClass3_Sub30_2372.buffer, 0, aClass3_Sub30_2372.index);
            aByteBuffer2368.flip();
            HDToolKit.method1832(var2);
            var10.glDrawElements(4, aClass3_Sub30_2372.index / 4, 5125, aByteBuffer2368);
         }

         if(aClass3_Sub30_2362.index != 0) {
            if(aByteBuffer2361 != null && aByteBuffer2361.capacity() >= aClass3_Sub30_2362.index) {
               aByteBuffer2361.clear();
            } else {
               aByteBuffer2361 = ByteBuffer.allocateDirect(aClass3_Sub30_2362.index).order(ByteOrder.nativeOrder());
            }

            aByteBuffer2361.put(aClass3_Sub30_2362.buffer, 0, aClass3_Sub30_2362.index);
            aByteBuffer2361.flip();
            HDToolKit.method1832(var2 - 100.0F);
            HDToolKit.method1851();
            var10.glDrawElements(4, aClass3_Sub30_2362.index / 4, 5125, aByteBuffer2361);
            HDToolKit.method1830();
         }

      }
   }

   final void method150(int var1, int var2, int var3, int[] var4, int[] var5, boolean var6) {
      if(this.aBoolean2364) {
         this.anIntArrayArray2360[this.anInt2366] = var5;
         this.aBooleanArray2370[this.anInt2366] = var6;
         if(var5 != null) {
            this.anInt2356 += var5.length;
         }

         if(var6) {
            this.anInt2356 += 3 * (var4.length - 2);
         } else {
            this.anInt2359 += 3 * (var4.length - 2);
         }
      } else {
         this.anInt2359 += 3 * (var4.length - 2);
      }

      this.anIntArray2367[this.anInt2366] = var1;
      this.anIntArray2350[this.anInt2366] = var2;
      this.anIntArray2349[this.anInt2366] = var3;
      this.anIntArrayArray2357[this.anInt2366] = var4;
       this.anInt2366++;
   }

   Class3_Sub11(int var1, float var2, boolean var3, boolean var4, int var5) {
      this.anInt2351 = var1;
      this.aFloat2373 = var2;
      this.aBoolean2364 = var3;
      this.aBoolean2347 = var4;
      this.anInt2355 = var5;
   }
}
