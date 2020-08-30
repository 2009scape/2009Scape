package org.runite.jagex;

final class Class137 {

   static boolean aBoolean1784 = false;


   static int method1817() {
      try {

          return !Class73.aBoolean1084?(!NPC.method1986((byte) 70 ^ 28)?1:(Class3_Sub28_Sub7.aBoolean3604?2:1)):0;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "tc.B(" + (byte) 70 + ')');
      }
   }

   public static void method1818(boolean var0) { //TODO: Misplaced Check Method
      try {
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "tc.C(" + var0 + ')');
      }
   }

   static void method1819(int var0, int var1, RSInterface var2, int var4, int var5, int var6, int var7) {
      try {

          if(Class158_Sub1.aBoolean2981) {
            Class19.anInt433 = 32;
         } else {
            Class19.anInt433 = 0;
         }

         Class158_Sub1.aBoolean2981 = false;
         int var8;
         if(Class3_Sub13_Sub5.anInt3069 != 0) {
            if(var4 >= var5 && var4 < var5 + 16 && var0 >= var6 && var6 - -16 > var0) {
               var2.anInt208 -= 4;
               Class20.method909(113, var2);
            } else if(var4 >= var5 && 16 + var5 > var4 && var1 + (var6 - 16) <= var0 && var0 < var1 + var6) {
               var2.anInt208 += 4;
               Class20.method909(112, var2);
            } else if(var5 - Class19.anInt433 <= var4 && var4 < var5 + 16 + Class19.anInt433 && var0 >= 16 + var6 && var1 + var6 - 16 > var0) {
               var8 = var1 * (-32 + var1) / var7;
               if(8 > var8) {
                  var8 = 8;
               }

               int var10 = -32 + (var1 - var8);
               int var9 = -(var8 / 2) + -16 + -var6 + var0;
               var2.anInt208 = (-var1 + var7) * var9 / var10;
               Class20.method909(-48, var2);
               Class158_Sub1.aBoolean2981 = true;
            }
         }

         if(Class29.anInt561 != 0) {
            var8 = var2.anInt168;
            if(var4 >= -var8 + var5 && var0 >= var6 && var4 < 16 + var5 && var1 + var6 >= var0) {
               var2.anInt208 += 45 * Class29.anInt561;
               Class20.method909(-116, var2);
            }
         }

      } catch (RuntimeException var11) {
         throw Class44.clientError(var11, "tc.A(" + var0 + ',' + var1 + ',' + (var2 != null?"{...}":"null") + ',' + (byte) -101 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
      }
   }

}
