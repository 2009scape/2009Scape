package org.runite.jagex;

final class Class3_Sub7 extends Class3 {

   static int[] anIntArray2292 = new int[1000];
   static int anInt2293 = -1;
   static int anInt2294;
   long aLong2295;


   public static void method120(int var0) {
      try {
         anIntArray2292 = null;
         if(var0 != 1000) {
            anInt2293 = 55;
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "eb.B(" + var0 + ')');
      }
   }

   static int method121(int var0, int var1, int var2, int var3, int var4, int var6) {
      try {
         if((var2 & 1) == 1) {
            int var7 = var4;
            var4 = var3;
            var3 = var7;
         }

         var1 &= 3;
         return 0 != var1?(var1 != 1 ?(var1 != 2?-var0 + 7 + 1 + -var3:-var6 + (7 - (var4 + -1))):var0):var6;
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "eb.A(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + 1 + ',' + var6 + ')');
      }
   }

   static void method122(int var0) {
      try {
         GameObject.aClass11ArrayArray1834 = new RSInterface[Class3_Sub13_Sub29.aClass153_3361.method2121()][];
         Class130.aBooleanArray1703 = new boolean[Class3_Sub13_Sub29.aClass153_3361.method2121()];
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "eb.D(" + var0 + ')');
      }
   }

   static RSString itemStackColor(int var0, int var1) {
      try {
         if(100000 > var1) {
            return RenderAnimationDefinition.method903(new RSString[]{ColorCore.DefaultStackColor, Class72.method1298((byte)9, var1), Class72.aClass94_1076}, (byte)-65);
         } else {
            if(var0 != 1000) {
               itemStackColor(-54, 54);
            }

            return var1 >= 10000000?RenderAnimationDefinition.method903(new RSString[]{ColorCore.MillionStackColor, Class72.method1298((byte)9, var1 / 1000000), TextCore.MillionM, Class72.aClass94_1076}, (byte)-85):RenderAnimationDefinition.method903(new RSString[]{ColorCore.ThousandStackColor, Class72.method1298((byte)9, var1 / 1000), TextCore.ThousandK, Class72.aClass94_1076}, (byte)-124);
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "eb.C(" + var0 + ',' + var1 + ')');
      }
   }

   public Class3_Sub7() {}

   Class3_Sub7(long var1) {
      try {
         this.aLong2295 = var1;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "eb.<init>(" + var1 + ')');
      }
   }

}
