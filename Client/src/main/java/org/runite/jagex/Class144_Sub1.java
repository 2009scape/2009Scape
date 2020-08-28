package org.runite.jagex;
import java.nio.ByteBuffer;

final class Class144_Sub1 extends Class144 {

   private ByteBuffer aByteBuffer2940;


   final void method2066(int var1, byte[] var2) {
      this.aByteBuffer2940 = ByteBuffer.allocateDirect(var2.length);
      this.aByteBuffer2940.position(0);
      if(var1 != 400) {
         this.method2066(44, (byte[])null);
      }

      this.aByteBuffer2940.put(var2);
   }

   final byte[] method2064() {
      byte[] var2 = new byte[this.aByteBuffer2940.capacity()];
      this.aByteBuffer2940.position(0);
       this.aByteBuffer2940.get(var2);
       return var2;
   }

}
