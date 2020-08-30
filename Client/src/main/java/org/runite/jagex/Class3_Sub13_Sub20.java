package org.runite.jagex;

import java.util.Objects;

final class Class3_Sub13_Sub20 extends Class3_Sub13 {

   static int anInt3244 = 0;
   private int anInt3245 = 4096;
   private int anInt3250 = 4096;
   private int anInt3252 = 4096;

   final void method157(int var1, RSByteBuffer var2, boolean var3) {
      try {
         if(var1 == 0) {
            this.anInt3252 = var2.getShort();
         } else if (var1 == 1) {
             this.anInt3245 = var2.getShort();
         } else if (2 == var1) {
             this.anInt3250 = var2.getShort();
         }

         if(!var3) {
            method266(12);
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "mg.A(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

   public static void method266(int var0) {//TODO: Misplaced Check Method
      try {
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "mg.U(" + var0 + ')');
      }
   }

   final int[][] method166(int var1, int var2) {
      try {
         if(var1 != -1) {
            anInt3244 = -40;
         }

         int[][] var3 = this.aClass97_2376.method1594((byte)-115, var2);
         if(this.aClass97_2376.aBoolean1379) {
            int[][] var4 = this.method162(var2, 0, (byte)-74);
            int[] var5 = Objects.requireNonNull(var4)[0];
            int[] var6 = var4[1];
            int[] var7 = var4[2];
            int[] var9 = var3[1];
            int[] var8 = var3[0];
            int[] var10 = var3[2];

            for(int var11 = 0; Class113.anInt1559 > var11; ++var11) {
               int var12 = var5[var11];
               int var14 = var6[var11];
               int var13 = var7[var11];
               if(var13 == var12 && var13 == var14) {
                  var8[var11] = this.anInt3252 * var12 >> 12;
                  var9[var11] = var13 * this.anInt3245 >> 12;
                  var10[var11] = var14 * this.anInt3250 >> 12;
               } else {
                  var8[var11] = this.anInt3252;
                  var9[var11] = this.anInt3245;
                  var10[var11] = this.anInt3250;
               }
            }
         }

         return var3;
      } catch (RuntimeException var15) {
         throw Class44.clientError(var15, "mg.T(" + var1 + ',' + var2 + ')');
      }
   }

   public Class3_Sub13_Sub20() {
      super(1, false);
   }

}
