package org.runite.jagex;
import java.awt.Component;

final class Class155_Sub1 extends Class155 {

   private final int anInt2969;
   private static Interface1 anInterface1_2970;


   final int method2157() {
      return anInterface1_2970.method2((byte)118, this.anInt2969);
   }

   final void method2149() {
      anInterface1_2970.method6(this.anInt2969, this.anIntArray1975);
   }

   public static void method2166() {
      anInterface1_2970 = null;
   }

   final void method2164(Component var1) throws Exception {
      anInterface1_2970.method5(Class21.anInt443, (byte)-39, var1, RSString.aBoolean2150);
   }

   final void method2151() {
      anInterface1_2970.method1(this.anInt2969, 28544);
   }

   Class155_Sub1(Signlink var1, int var2) {
      anInterface1_2970 = var1.method1446((byte)99);
      this.anInt2969 = var2;
   }

   final void method2150(int var1) throws Exception {
      if(var1 > '\u8000') {
         throw new IllegalArgumentException();
      } else {
         anInterface1_2970.method3(this.anInt2969, 25349, var1);
      }
   }

   final void method2160() {
      anInterface1_2970.method4((byte)20, this.anInt2969);
   }
}
