package org.runite.jagex;
import java.nio.ByteBuffer;
import javax.media.opengl.GL;

final class Class156 {

   private int anInt1991;
   private final int anInt1992;
   private int anInt1993;
   private final boolean aBoolean1994;


   final void method2168(ByteBuffer var1) {
      if(var1.limit() <= this.anInt1993) {
         GL var2 = HDToolKit.gl;
         var2.glBindBufferARB('\u8892', this.anInt1991);
         var2.glBufferSubDataARB('\u8892', 0, var1.limit(), var1);
      } else {
         this.method2172(var1);
      }

   }

   protected final void finalize() throws Throwable {
      if(this.anInt1991 != -1) {
         Class31.method989(this.anInt1991, this.anInt1993, this.anInt1992);
         this.anInt1991 = -1;
         this.anInt1993 = 0;
      }

      super.finalize();
   }

   final void method2169() {
      GL var1 = HDToolKit.gl;
      var1.glBindBufferARB('\u8892', this.anInt1991);
   }

   public Class156() {
      this(false);
   }

   final void method2170(ByteBuffer var1) {
      GL var2 = HDToolKit.gl;
      var2.glBindBufferARB('\u8893', this.anInt1991);
      var2.glBufferDataARB('\u8893', var1.limit(), var1, this.aBoolean1994?'\u88e0':'\u88e4');
      Class31.anInt585 += var1.limit() - this.anInt1993;
      this.anInt1993 = var1.limit();
   }

   final void method2171() {
      GL var1 = HDToolKit.gl;
      var1.glBindBufferARB('\u8893', this.anInt1991);
   }

   final void method2172(ByteBuffer var1) {
      GL var2 = HDToolKit.gl;
      var2.glBindBufferARB('\u8892', this.anInt1991);
      var2.glBufferDataARB('\u8892', var1.limit(), var1, this.aBoolean1994?'\u88e0':'\u88e4');
      Class31.anInt585 += var1.limit() - this.anInt1993;
      this.anInt1993 = var1.limit();
   }

   Class156(boolean var1) {
      this.anInt1991 = -1;
      this.anInt1993 = 0;
      GL var2 = HDToolKit.gl;
      int[] var3 = new int[1];
      var2.glGenBuffersARB(1, var3, 0);
      this.aBoolean1994 = var1;
      this.anInt1991 = var3[0];
      this.anInt1992 = Class31.anInt582;
   }
}
