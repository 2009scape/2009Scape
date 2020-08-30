package org.runite.jagex;

import java.util.Objects;

final class Class46 {

   static CacheIndex aClass153_737;
   static int anInt739;
   static int anInt740;
   static int anInt741;


   public static void method1085(int var0) {
      try {
         if(var0 == -1) {
            aClass153_737 = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "gl.B(" + var0 + ')');
      }
   }

   static void method1086(CacheIndex var0) {
      try {
         Class45.aClass153_731 = var0;

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "gl.C(" + (var0 != null?"{...}":"null") + ',' + -6 + ')');
      }
   }

   static void method1087(int var0, int var1) {
      try {
         Class3_Sub30_Sub1.method819();
         Class3_Sub13_Sub17.method252();
         if(var0 < 38) {
            anInt741 = 118;
         }

         int var2 = Objects.requireNonNull(Class145.method2076(-37, var1)).anInt556;
         if(var2 != 0) {
            int var3 = ItemDefinition.ram[var1];
            if(6 == var2) {
               Class41.anInt688 = var3;
            }

            if(var2 == 5) {
               Class66.anInt998 = var3;
            }

            if(var2 == 9) {
               Canvas_Sub1.anInt15 = var3;
            }

         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "gl.A(" + var0 + ',' + var1 + ')');
      }
   }

}
