package org.runite.jagex;
import java.io.InputStream;

final class InputStream_Sub1 extends InputStream {

   static RSString aClass94_37 = RSString.createRSString("0(U");
   static RSString aClass94_38 = RSString.createRSString("tbrefresh");
   static int[] anIntArray39;
   static int anInt40 = 0;
   static IOHandler js5Connection;
   static int anInt42 = 0;

   public static void method61(int var0) {
      try {
         int var1 = 10 / ((-37 - var0) / 33);
         js5Connection = null;
         aClass94_38 = null;
         aClass94_37 = null;
         anIntArray39 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "qk.C(" + var0 + ')');
      }
   }

   static final int[] method62(boolean var0, int var1, int var2, int var3, int var4, float var5, int var6, int var7) {
      try {
         if(var1 != 14585) {
            anInt40 = 43;
         }

         int[] var8 = new int[var3];
         Class3_Sub13_Sub4 var9 = new Class3_Sub13_Sub4();
         var9.anInt3060 = var6;
         var9.anInt3058 = var4;
         var9.anInt3067 = var7;
         var9.anInt3056 = var2;
         var9.anInt3062 = (int)(var5 * 4096.0F);
         var9.aBoolean3065 = var0;
         var9.method158(16251);
         Class3_Sub13_Sub3.method180(-106, 1, var3);
         var9.method186(true, 0, var8);
         return var8;
      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "qk.A(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
      }
   }

   static final Class3_Sub13 method63(byte var0, RSByteBuffer var1) {
      try {
         var1.getByte((byte)-114);
         int var2 = var1.getByte((byte)-51);
         Class3_Sub13 var3 = Class130.method1777(var2, true);
         var3.anInt2381 = var1.getByte((byte)-66);
         int var4 = var1.getByte((byte)-33);
         for(int var5 = 0; var5 < var4; ++var5) {
            int var6 = var1.getByte((byte)-92);
            var3.method157(var6, var1, true);
         }

         var3.method158(16251);
         return var3;
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "qk.B(" + var0 + ',' + (var1 != null?"{...}":"null") + ')');
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

   static final int method64(boolean var0, int var1) {
      try {
         return var1 >>> 8;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "qk.D(" + var0 + ',' + var1 + ')');
      }
   }

}
