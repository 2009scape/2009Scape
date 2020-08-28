package org.runite.jagex;

import java.nio.ByteBuffer;
import javax.media.opengl.GL;

final class Class88 {

   static int[] anIntArray1223 = null;
   static int[] anIntArray1224 = null;
   private static ByteBuffer aByteBuffer1225;
   private static ByteBuffer aByteBuffer1226;
   static boolean aBoolean1227;
   static int anInt1228 = -1;
   static int anInt1229 = -1;


   static void method1454() {
      byte[] var1;
      if(aByteBuffer1226 == null) {
         Class164_Sub2_Sub1 var0 = new Class164_Sub2_Sub1();
         var1 = var0.method2250();
         aByteBuffer1226 = ByteBuffer.allocateDirect(var1.length);
         aByteBuffer1226.position(0);
         aByteBuffer1226.put(var1);
         aByteBuffer1226.flip();
      }

      if(aByteBuffer1225 == null) {
         Class164_Sub1_Sub1 var2 = new Class164_Sub1_Sub1();
         var1 = var2.method2243();
         aByteBuffer1225 = ByteBuffer.allocateDirect(var1.length);
         aByteBuffer1225.position(0);
         aByteBuffer1225.put(var1);
         aByteBuffer1225.flip();
      }

   }

   static void method1455() {
      GL var0;
      int[] var1;
      if(anInt1228 != -1) {
         var0 = HDToolKit.gl;
         var1 = new int[]{anInt1228};
         var0.glDeleteTextures(1, var1, 0);
         anInt1228 = -1;
         Class31.anInt580 -= aByteBuffer1226.limit() * 2;
      }

      if(anIntArray1224 != null) {
         var0 = HDToolKit.gl;
         var0.glDeleteTextures(64, anIntArray1224, 0);
         anIntArray1224 = null;
         Class31.anInt580 -= aByteBuffer1226.limit() * 2;
      }

      if(anInt1229 != -1) {
         var0 = HDToolKit.gl;
         var1 = new int[]{anInt1229};
         var0.glDeleteTextures(1, var1, 0);
         anInt1229 = -1;
         Class31.anInt580 -= aByteBuffer1225.limit() * 2;
      }

      if(anIntArray1223 != null) {
         var0 = HDToolKit.gl;
         var0.glDeleteTextures(64, anIntArray1223, 0);
         anIntArray1223 = null;
         Class31.anInt580 -= aByteBuffer1225.limit() * 2;
      }

   }

   static void method1456() {
      aBoolean1227 = HDToolKit.aBoolean1802;
      method1454();
      method1458();
      method1459();
   }

   public static void method1457() {
      anIntArray1224 = null;
      anIntArray1223 = null;
      aByteBuffer1226 = null;
      aByteBuffer1225 = null;
   }

   private static void method1458() {
      javax.media.opengl.GL var0 = HDToolKit.gl;
      if(aBoolean1227) {
         int[] var1 = new int[1];
         var0.glGenTextures(1, var1, 0);
         var0.glBindTexture('\u806f', var1[0]);
         aByteBuffer1226.position(0);
         var0.glTexImage3D('\u806f', 0, 6410, 64, 64, 64, 0, 6410, 5121, aByteBuffer1226);
         var0.glTexParameteri('\u806f', 10241, 9729);
         var0.glTexParameteri('\u806f', 10240, 9729);
         anInt1228 = var1[0];
      } else {
         anIntArray1224 = new int[64];
         var0.glGenTextures(64, anIntArray1224, 0);

         for(int var2 = 0; var2 < 64; ++var2) {
            HDToolKit.bindTexture2D(anIntArray1224[var2]);
            aByteBuffer1226.position(var2 * 64 * 64 * 2);
            var0.glTexImage2D(3553, 0, 6410, 64, 64, 0, 6410, 5121, aByteBuffer1226);
            var0.glTexParameteri(3553, 10241, 9729);
            var0.glTexParameteri(3553, 10240, 9729);
         }

      }
      Class31.anInt580 += aByteBuffer1226.limit() * 2;

   }

   private static void method1459() {
      javax.media.opengl.GL var0 = HDToolKit.gl;
      if(aBoolean1227) {
         int[] var1 = new int[1];
         var0.glGenTextures(1, var1, 0);
         var0.glBindTexture('\u806f', var1[0]);
         aByteBuffer1225.position(0);
         var0.glTexImage3D('\u806f', 0, 6410, 64, 64, 64, 0, 6410, 5121, aByteBuffer1225);
         var0.glTexParameteri('\u806f', 10241, 9729);
         var0.glTexParameteri('\u806f', 10240, 9729);
         anInt1229 = var1[0];
      } else {
         anIntArray1223 = new int[64];
         var0.glGenTextures(64, anIntArray1223, 0);

         for(int var2 = 0; var2 < 64; ++var2) {
            HDToolKit.bindTexture2D(anIntArray1223[var2]);
            aByteBuffer1225.position(var2 * 64 * 64 * 2);
            var0.glTexImage2D(3553, 0, 6410, 64, 64, 0, 6410, 5121, aByteBuffer1225);
            var0.glTexParameteri(3553, 10241, 9729);
            var0.glTexParameteri(3553, 10240, 9729);
         }

      }
      Class31.anInt580 += aByteBuffer1225.limit() * 2;

   }

}
