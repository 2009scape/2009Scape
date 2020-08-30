package org.runite.jagex;

final class ScriptContainer {

   static int anInt869;
   int[] anIntArray870;
   static int anInt872;
   RSString[] aClass94Array873;
   Class3_Sub28_Sub15 aClass3_Sub28_Sub15_874;
   int anInt877 = -1;
   static CacheIndex aClass153_878;


   static void method1175(int var0) {
      try {
         Class3_Sub13_Sub30.anInt3362 = -1;
         Class82.anInt1150 = -1;

         Class3_Sub28_Sub1.anInt3536 = var0;
         Class3_Sub5.method117();
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "hj.D(" + var0 + ',' + 112 + ')');
      }
   }

   static boolean method1176(RSString var0) {
      try {
         if(var0 == null) {
            return false;
         } else {
            for(int var2 = 0; Class8.anInt104 > var2; ++var2) {
               if(var0.equals(-121, Class70.aClass94Array1046[var2])) {
                  return true;
               }
            }


            return var0.equals((byte) -82 + -46, Class102.player.displayName);
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "hj.A(" + "{...}" + ',' + (byte) -82 + ')');
      }
   }

   static void method1177(int var0, long var1, byte var3, RSString var4, int var5, short var6, RSString var7, int var8) {
      try {
         if(var3 > -23) {
            method1177(-45, 37L, (byte)-37, (RSString)null, -16, (short)110, (RSString)null, -75);
         }

         if(!Class38_Sub1.aBoolean2615) {
            if(Class3_Sub13_Sub34.anInt3415 < 500) {
               Class140_Sub7.aClass94Array2935[Class3_Sub13_Sub34.anInt3415] = var7;
               Class163_Sub2_Sub1.aClass94Array4016[Class3_Sub13_Sub34.anInt3415] = var4;
               Class114.anIntArray1578[Class3_Sub13_Sub34.anInt3415] = var0 == -1 ?Class3_Sub28_Sub5.anInt3590:var0;
               Class3_Sub13_Sub7.aShortArray3095[Class3_Sub13_Sub34.anInt3415] = var6;
               Class3_Sub13_Sub22.aLongArray3271[Class3_Sub13_Sub34.anInt3415] = var1;
               Class117.anIntArray1613[Class3_Sub13_Sub34.anInt3415] = var5;
               Class27.anIntArray512[Class3_Sub13_Sub34.anInt3415] = var8;
               ++Class3_Sub13_Sub34.anInt3415;
            }

         }
      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "hj.C(" + var0 + ',' + var1 + ',' + var3 + ',' + (var4 != null?"{...}":"null") + ',' + var5 + ',' + var6 + ',' + (var7 != null?"{...}":"null") + ',' + var8 + ')');
      }
   }

}
