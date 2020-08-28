package org.runite.jagex;

final class Class164_Sub2_Sub1 extends Class164_Sub2 {

   private byte[] aByteArray4029;


   public Class164_Sub2_Sub1() {
      super();
   }

   final byte[] method2250() {
      this.aByteArray4029 = new byte[64 * 64 * 64 * 2];
      this.method2230(-98);
      return this.aByteArray4029;
   }

   final void method2244(int var1, byte var2) {
      int var3 = var1 * 2;
      int var4 = var2 & 255;
      this.aByteArray4029[var3++] = (byte)(3 * var4 >> 5);
      this.aByteArray4029[var3] = (byte)(var4 >> 2);
   }
}
