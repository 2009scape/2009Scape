package org.runite.jagex;
import java.io.InputStream;
import java.util.Objects;

final class InputStream_Sub1 extends InputStream {

   static RSString aClass94_37 = RSString.createRSString("0(U");
   static RSString aClass94_38 = RSString.createRSString("tbrefresh");
   static int[] anIntArray39;
   static int anInt40 = 0;
   static IOHandler js5Connection;
   static int anInt42 = 0;

   public static void method61(int var0) {
      try {
         js5Connection = null;
         aClass94_38 = null;
         aClass94_37 = null;
         anIntArray39 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "qk.C(" + var0 + ')');
      }
   }

   static int[] method62() {
      try {

          int[] var8 = new int[2048];
         Class3_Sub13_Sub4 var9 = new Class3_Sub13_Sub4();
         var9.anInt3060 = 8;
         var9.anInt3058 = 4;
         var9.anInt3067 = 35;
         var9.anInt3056 = 8;
         var9.anInt3062 = (int)((float) 0.4 * 4096.0F);
         var9.aBoolean3065 = true;
         var9.method158(16251);
         Class3_Sub13_Sub3.method180(-106, 1, 2048);
         var9.method186(0, var8);
         return var8;
      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "qk.A(" + true + ',' + 14585 + ',' + 8 + ',' + 2048 + ',' + 4 + ',' + (float) 0.4 + ',' + 8 + ',' + 35 + ')');
      }
   }

   static Class3_Sub13 method63(RSByteBuffer var1) {
      try {
         var1.getByteB();
         int var2 = var1.getByteB();
         Class3_Sub13 var3 = Class130.method1777(var2, true);
         Objects.requireNonNull(var3).anInt2381 = var1.getByteB();
         int var4 = var1.getByteB();
         for(int var5 = 0; var5 < var4; ++var5) {
            int var6 = var1.getByteB();
            var3.method157(var6, var1, true);
         }

         var3.method158(16251);
         return var3;
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "qk.B(" + (byte) -67 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   public final int read() {
      try {
         Class3_Sub13_Sub34.method331(30000L, 64);
         return -1;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "qk.read()");
      }
   }

   static int method64(int var1) {
      try {
         return var1 >>> 8;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "qk.D(" + true + ',' + var1 + ')');
      }
   }

}
