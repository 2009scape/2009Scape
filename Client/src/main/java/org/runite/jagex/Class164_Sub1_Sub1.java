package org.runite.jagex;

final class Class164_Sub1_Sub1 extends Class164_Sub1 {

   private byte[] aByteArray4028;


   final void method2242(int var1, byte var2) {
      int var3 = var1 * 2;
      var2 = (byte)(127 + ((var2 & 255) >> 1));
      this.aByteArray4028[var3++] = var2;
      this.aByteArray4028[var3] = var2;
   }

   public Class164_Sub1_Sub1() {
      super();
   }

   final byte[] method2243() {
      this.aByteArray4028 = new byte[64 * 64 * 64 * 2];
      this.method2230(-95);
      return this.aByteArray4028;
   }
}
