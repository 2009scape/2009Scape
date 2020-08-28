package org.runite.jagex;
import java.nio.FloatBuffer;
import javax.media.opengl.GL;

final class Class127_Sub1 extends Class127 {

   private static int anInt2682;


   Class127_Sub1() {
      new Class17();
      new Class61();
   }

   static void method1755() {
      GL var0 = HDToolKit.gl;
      if(var0.isExtensionAvailable("GL_ARB_point_parameters")) {
         float[] var1 = new float[]{1.0F, 0.0F, 5.0E-4F};
         var0.glPointParameterfvARB('\u8129', var1, 0);
         FloatBuffer var2 = FloatBuffer.allocate(1);
         var0.glGetFloatv('\u8127', var2);
         float var3 = var2.get(0);
         if(var3 > 1024.0F) {
            var3 = 1024.0F;
         }

         var0.glPointParameterfARB('\u8126', 1.0F);
         var0.glPointParameterfARB('\u8127', var3);
      }

      if(var0.isExtensionAvailable("GL_ARB_point_sprite")) {
      }

   }

   static int method1757() {
      return anInt2682;
   }

   static void method1758(int var0) {
      anInt2682 = var0;
   }

   static {
      new Class128();
      anInt2682 = 2;
      new RSByteBuffer(131056);
   }
}
