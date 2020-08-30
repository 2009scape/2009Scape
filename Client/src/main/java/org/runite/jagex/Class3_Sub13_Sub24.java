package org.runite.jagex;

import java.util.Objects;

final class Class3_Sub13_Sub24 extends Class3_Sub13 {

   static int[] npcSpawnCacheIndices;
   static NPC[] npcs = new NPC['\u8000'];
   static int anInt3293 = 0;
   private int anInt3294 = 1;
   static RSString aClass94_3295;
   static long aLong3296 = 0L;
   private int anInt3297 = 1;


   final int[] method154(int var1, byte var2) {
      try {
         int[] var4 = this.aClass114_2382.method1709(var1);
         if(this.aClass114_2382.aBoolean1580) {
            int var5 = 1 + this.anInt3297 + this.anInt3297;
            int var6 = 65536 / var5;
            int var7 = 1 + this.anInt3294 + this.anInt3294;
            int var8 = 65536 / var7;
            int[][] var9 = new int[var5][];

            int var10;
            for(var10 = -this.anInt3297 + var1; var1 - -this.anInt3297 >= var10; ++var10) {
               int[] var11 = this.method152(0, var10 & Class3_Sub20.anInt2487, 32755);
               int[] var12 = new int[Class113.anInt1559];
               int var13 = 0;

               int var14;
               for(var14 = -this.anInt3294; var14 <= this.anInt3294; ++var14) {
                  var13 += var11[var14 & RenderAnimationDefinition.anInt396];
               }

               for(var14 = 0; var14 < Class113.anInt1559; var13 += var11[RenderAnimationDefinition.anInt396 & this.anInt3294 + var14]) {
                  var12[var14] = var8 * var13 >> 16;
                  var13 -= var11[RenderAnimationDefinition.anInt396 & var14 - this.anInt3294];
                  ++var14;
               }

               var9[this.anInt3297 + var10 + -var1] = var12;
            }

            for(var10 = 0; var10 < Class113.anInt1559; ++var10) {
               int var16 = 0;

               for(int var17 = 0; var5 > var17; ++var17) {
                  var16 += var9[var17][var10];
               }

               var4[var10] = var6 * var16 >> 16;
            }
         }

         return var4;
      } catch (RuntimeException var15) {
         throw Class44.clientError(var15, "nm.D(" + var1 + ',' + var2 + ')');
      }
   }

   public static void method288(byte var0) {
      try {
         if(var0 < 31) {
            method289();
         }

         npcSpawnCacheIndices = null;
         npcs = null;
         aClass94_3295 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "nm.C(" + var0 + ')');
      }
   }

   public Class3_Sub13_Sub24() {
      super(1, false);
   }

   static void method289() {
      try {

          if(Class159.anInt2023 > 0) {
            Class167.method2269((byte)46);
         } else {
            Class163_Sub2_Sub1.aClass89_4012 = Class3_Sub15.aClass89_2429;
            Class3_Sub15.aClass89_2429 = null;
            Class117.method1719(40);
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "nm.B(" + false + ')');
      }
   }

   final int[][] method166(int var1, int var2) {
      try {

         int[][] var3 = this.aClass97_2376.method1594((byte)90, var2);
         if(this.aClass97_2376.aBoolean1379) {
            int var6 = this.anInt3294 - -this.anInt3294 - -1;
            int var7 = 65536 / var6;
            int var4 = this.anInt3297 + (this.anInt3297 - -1);
            int var5 = 65536 / var4;
            int[][][] var8 = new int[var4][][];

            int var12;
            int var13;
            int var14;
            for(int var9 = var2 - this.anInt3297; this.anInt3297 + var2 >= var9; ++var9) {
               int[][] var10 = this.method162(Class3_Sub20.anInt2487 & var9, 0, (byte)-59);
               var12 = 0;
               var13 = 0;
               int[][] var11 = new int[3][Class113.anInt1559];
               var14 = 0;
               int[] var15 = Objects.requireNonNull(var10)[0];
               int[] var16 = var10[1];
               int[] var17 = var10[2];

               for(int var18 = -this.anInt3294; var18 <= this.anInt3294; ++var18) {
                  int var19 = var18 & RenderAnimationDefinition.anInt396;
                  var13 += var16[var19];
                  var12 += var15[var19];
                  var14 += var17[var19];
               }

               int[] var20 = var11[2];
               int[] var31 = var11[0];
               int[] var30 = var11[1];

               int var22;
               for(int var21 = 0; Class113.anInt1559 > var21; var12 += var15[var22]) {
                  var31[var21] = var12 * var7 >> 16;
                  var30[var21] = var13 * var7 >> 16;
                  var20[var21] = var7 * var14 >> 16;
                  var22 = RenderAnimationDefinition.anInt396 & var21 + -this.anInt3294;
                  var14 -= var17[var22];
                  ++var21;
                  var12 -= var15[var22];
                  var13 -= var16[var22];
                  var22 = this.anInt3294 + var21 & RenderAnimationDefinition.anInt396;
                  var14 += var17[var22];
                  var13 += var16[var22];
               }

               var8[-var2 + this.anInt3297 + var9] = var11;
            }

            int[] var24 = var3[0];
            int[] var26 = var3[1];
            int[] var25 = var3[2];

            for(var12 = 0; var12 < Class113.anInt1559; ++var12) {
               var14 = 0;
               var13 = 0;
               int var27 = 0;

               for(int var28 = 0; var28 < var4; ++var28) {
                  int[][] var29 = var8[var28];
                  var27 += var29[2][var12];
                  var14 += var29[1][var12];
                  var13 += var29[0][var12];
               }

               var24[var12] = var5 * var13 >> 16;
               var26[var12] = var5 * var14 >> 16;
               var25[var12] = var27 * var5 >> 16;
            }
         }

         return var3;
      } catch (RuntimeException var23) {
         throw Class44.clientError(var23, "nm.T(" + var1 + ',' + var2 + ')');
      }
   }

   final void method157(int var1, RSByteBuffer var2, boolean var3) {
      try {
         if(var1 == 0) {
            this.anInt3294 = var2.getByteB();
         } else if(var1 == 1) {
            this.anInt3297 = var2.getByteB();
         } else if (2 == var1) {
            this.aBoolean2375 = var2.getByteB() == 1;
         }

         if(!var3) {
            this.anInt3294 = 60;
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "nm.A(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

}
