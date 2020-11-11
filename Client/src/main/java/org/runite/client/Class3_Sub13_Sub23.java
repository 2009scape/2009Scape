package org.runite.client;

import java.util.Objects;

class Class3_Sub13_Sub23 extends Class3_Sub13 {

   private int anInt3278 = -1;
   int anInt3280;
   int anInt3283;
   int[] anIntArray3284;
   static int itemDefinitionSize;

   static int method275(int var0, int var1, int var2, int var3) {
      try {
         int var5 = -Class51.anIntArray851[1024 * var2 / var3] + 65536 >> 1;
         return (var0 * (-var5 + 65536) >> 16) + (var1 * var5 >> 16);
      } catch (RuntimeException var6) {
         throw ClientErrorException.clientError(var6, "nh.CA(" + var0 + ',' + var1 + ',' + var2 + ',' + ',' + var3 + ')');
      }
   }

   static void method276(int var0, int var1, int var2, int var3, GameObject var4, long var5, boolean var7) {
      if(var4 != null) {
         Class12 var8 = new Class12();
         var8.object = var4;
         var8.anInt324 = var1 * 128 + 64;
         var8.anInt330 = var2 * 128 + 64;
         var8.anInt326 = var3;
         var8.aLong328 = var5;
         var8.aBoolean329 = var7;
         if(Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2] == null) {
            Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2] = new Class3_Sub2(var0, var1, var2);
         }

         Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2].aClass12_2230 = var8;
      }
   }

   static boolean handleWorldListUpdate(byte[] buf) {
      try {
         DataBuffer buffer = new DataBuffer(buf);
         int opcode = buffer.readUnsignedByte();
         //System.out.println(opcode);
         if(1 == opcode) {
            boolean updated = buffer.readUnsignedByte() == 1;
            if(updated) {
               WorldListEntry.parseWorldList(buffer);
            }

            Class3_Sub13_Sub10.method216(buffer);
            return true;
         } else {
            return false;
         }
      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "nh.AA(" + 4 + ',' + (buf != null?"{...}":"null") + ')');
      }
   }

   final boolean method279(int var1) {
      try {
         if(null == this.anIntArray3284) {
            if(this.anInt3278 < 0) {
               return false;
            } else {
               Class3_Sub28_Sub16_Sub2 var3 = Class82.anInt1668 < 0 ? Unsorted.method1537(WaterfallShader.aClass153_2172, this.anInt3278):Class40.method1043(this.anInt3278, WaterfallShader.aClass153_2172, Class82.anInt1668);
               Objects.requireNonNull(var3).method665();
               this.anInt3283 = var3.anInt3696;
               this.anInt3280 = var3.anInt3707;
               this.anIntArray3284 = var3.anIntArray4081;
               return true;
            }
         } else {
            return true;
         }
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "nh.FA(" + var1 + ')');
      }
   }

   final int method159(int var1) {
      try {
         return var1 != 4?40:this.anInt3278;
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "nh.GA(" + var1 + ')');
      }
   }

   public Class3_Sub13_Sub23() {
      super(0, false);
   }

   static void method280(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var11, int var12) {
      try {
         Class3_Sub5 var13 = new Class3_Sub5();
         var13.anInt2284 = var6;
         var13.anInt2283 = var3;
         var13.anInt2266 = var1;
         var13.anInt2279 = var5;
         var13.anInt2273 = var2;
         var13.anInt2271 = var8;
         var13.anInt2277 = var11;
         var13.anInt2282 = var4;
         var13.anInt2270 = var12;
         var13.anInt2268 = var7;
         var13.anInt2272 = var0;
         var13.anInt2278 = var9;
         Unsorted.aClass61_2468.method1215(var13);
      } catch (RuntimeException var14) {
         throw ClientErrorException.clientError(var14, "nh.V(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + var9 + ',' + -745213428 + ',' + var11 + ',' + var12 + ')');
      }
   }

   int[][] method166(int var1, int var2) {
      try {
         if(var1 != -1) {
            this.method159(32);
         }

         int[][] var3 = this.aClass97_2376.method1594((byte)65, var2);
         if(this.aClass97_2376.aBoolean1379 && this.method279(-113)) {
            int[] var4 = var3[0];
            int[] var5 = var3[1];
            int[] var6 = var3[2];
            int var7 = (Class101.anInt1427 == this.anInt3283 ?var2:this.anInt3283 * var2 / Class101.anInt1427) * this.anInt3280;
            int var8;
            int var9;
            if(Class113.anInt1559 == this.anInt3280) {
               for(var8 = 0; var8 < Class113.anInt1559; ++var8) {
                  var9 = this.anIntArray3284[var7++];
                  var6[var8] = Unsorted.bitwiseAnd(255, var9) << 4;
                  var5[var8] = Unsorted.bitwiseAnd('\uff00', var9) >> 4;
                  var4[var8] = Unsorted.bitwiseAnd(var9, 16711680) >> 12;
               }
            } else {
               for(var8 = 0; Class113.anInt1559 > var8; ++var8) {
                  var9 = this.anInt3280 * var8 / Class113.anInt1559;
                  int var10 = this.anIntArray3284[var7 - -var9];
                  var6[var8] = Unsorted.bitwiseAnd(var10 << 4, 4080);
                  var5[var8] = Unsorted.bitwiseAnd(var10, '\uff00') >> 4;
                  var4[var8] = Unsorted.bitwiseAnd(var10 >> 12, 4080);
               }
            }
         }

         return var3;
      } catch (RuntimeException var11) {
         throw ClientErrorException.clientError(var11, "nh.T(" + var1 + ',' + var2 + ')');
      }
   }

   final void method157(int var1, DataBuffer var2, boolean var3) {
      try {
         if(var1 == 0) {
            this.anInt3278 = var2.readUnsignedShort();
         }

         if(!var3) {
            method276(115, 107, 22, 20, (GameObject)null, 87L, false);
         }

      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "nh.A(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

   static void method281(int var1, int var2) {
      try {
         Class57.anIntArray898[var2] = var1;
         Class3_Sub7 var3 = (Class3_Sub7) AtmosphereParser.aHashTable_3679.get((long)var2);
         if(null == var3) {
            var3 = new Class3_Sub7(4611686018427387905L);
            AtmosphereParser.aHashTable_3679.put((long)var2, var3);
         } else if (var3.aLong2295 != 4611686018427387905L) {
            var3.aLong2295 = TimeUtils.time() + 500L | 4611686018427387904L;
         }

      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "nh.W(" + (byte) 99 + ',' + var1 + ',' + var2 + ')');
      }
   }

   final void method161(byte var1) {
      try {
         super.method161(var1);
         this.anIntArray3284 = null;
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "nh.BA(" + var1 + ')');
      }
   }

}
