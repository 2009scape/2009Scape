package org.runite.jagex;

import java.util.Objects;

class Class164_Sub2 extends Class164 {

   private int anInt3018;
   static Class33 aClass33_3019;
   static int anInt3020 = 0;
   private final int anInt3021;
   private final int anInt3022;
   private int anInt3024;
   private byte[] aByteArray3025;
   private final int anInt3026;
   static byte[][] aByteArrayArray3027;
   private int anInt3028;
   private int anInt3029;


   void method2244(int var1, byte var2) {
      try {
         this.aByteArray3025[var1] = var2;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "we.G(" + var1 + ',' + var2 + ')');
      }
   }

   final void method2231(byte var1) {
      try {
         this.anInt3018 = this.anInt3021;
         this.anInt3029 >>= 4;
         if(0 > this.anInt3029) {
            this.anInt3029 = 0;
         } else if(255 < this.anInt3029) {
            this.anInt3029 = 255;
         }

         this.method2244(this.anInt3028++, (byte)this.anInt3029);
         if(var1 == -92) {
            this.anInt3029 = 0;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "we.A(" + var1 + ')');
      }
   }

   public static void method2245(byte var0) {
      try {
         aClass33_3019 = null;
         if(var0 != -74) {
            aByteArrayArray3027 = (byte[][])((byte[][])null);
         }

         aByteArrayArray3027 = (byte[][])null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "we.I(" + var0 + ')');
      }
   }

   Class164_Sub2() {
      super(8, 8, 8);

      try {
         this.anInt3022 = (int)(4096.0F * (float) 3.0);
         this.anInt3026 = (int)((float) 0.55 * 4096.0F);
         this.anInt3018 = this.anInt3021 = (int)(Math.pow(0.5D, (double)(-(float) 0.1)) * 4096.0D);
      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "we.<init>(" + 8 + ',' + 5 + ',' + 8 + ',' + 8 + ',' + 2 + ',' + (float) 0.1 + ',' + (float) 0.55 + ',' + (float) 3.0 + ')');
      }
   }

   static int method2246(int var1) {
      try {
         int var2 = var1 * (var1 * var1 >> 12) >> 12;

         int var3 = 6 * var1 - '\uf000';
         int var4 = (var1 * var3 >> 12) + '\ua000';
         return var2 * var4 >> 12;
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "we.E(" + (byte) 83 + ',' + var1 + ')');
      }
   }

   static int method2247(byte var0, int var1, RSInterface var2) {
      try {
         if(var2.childDataBuffers != null && var2.childDataBuffers.length > var1) {
            try {
               int[] var4 = var2.childDataBuffers[var1];
               byte var7 = 0;
               int var5 = 0;
               int var6 = 0;

               while(true) {
                  int var9 = 0;
                  int var8 = var4[var6++];
                  byte var10 = 0;
                  if(var8 == 0) {
                     return var5;
                  }

                  if(15 == var8) {
                     var10 = 1;
                  }

                  if(16 == var8) {
                     var10 = 2;
                  }

                  if(var8 == 1) {
                     var9 = Class3_Sub13_Sub15.anIntArray3185[var4[var6++]];
                  }

                  if(var8 == 17) {
                     var10 = 3;
                  }

                  if(var8 == 2) {
                     var9 = Class3_Sub20.anIntArray2480[var4[var6++]];
                  }

                  if(var8 == 3) {
                     var9 = Class133.anIntArray1743[var4[var6++]];
                  }

                  int var11;
                  RSInterface var12;
                  int var13;
                  int var14;
                  if(var8 == 4) {
                     var11 = var4[var6++] << 16;
                     var11 += var4[var6++];
                     var12 = Class7.getRSInterface((byte)124, var11);
                     var13 = var4[var6++];
                     if(-1 != var13 && (!Class38.getItemDefinition(var13, (byte)109).membersItem || Class2.isMember)) {
                        for(var14 = 0; var14 < Objects.requireNonNull(var12).itemAmounts.length; ++var14) {
                           if(1 + var13 == var12.itemAmounts[var14]) {
                              var9 += var12.itemIds[var14];
                           }
                        }
                     }
                  }

                  if(var8 == 5) {
                     var9 = ItemDefinition.ram[var4[var6++]];
                  }

                  if(6 == var8) {
                     var9 = ItemDefinition.anIntArray781[-1 + Class3_Sub20.anIntArray2480[var4[var6++]]];
                  }

                  if(var8 == 7) {
                     var9 = 100 * ItemDefinition.ram[var4[var6++]] / '\ub71b';
                  }

                  if(var8 == 8) {
                     var9 = Class102.player.COMBAT_LEVEL;
                  }

                  if(9 == var8) {
                     for(var11 = 0; var11 < 25; ++var11) {
                        if(Class3_Sub23.aBooleanArray2538[var11]) {
                           var9 += Class3_Sub20.anIntArray2480[var11];
                        }
                     }
                  }

                  if(var8 == 10) {
                     var11 = var4[var6++] << 16;
                     var11 += var4[var6++];
                     var12 = Class7.getRSInterface((byte)115, var11);
                     var13 = var4[var6++];
                     if(var13 != -1 && (!Class38.getItemDefinition(var13, (byte)88).membersItem || Class2.isMember)) {
                        for(var14 = 0; var14 < Objects.requireNonNull(var12).itemAmounts.length; ++var14) {
                           if(1 + var13 == var12.itemAmounts[var14]) {
                              var9 = 999999999;
                              break;
                           }
                        }
                     }
                  }

                  if(var8 == 11) {
                     var9 = Class9.anInt136;
                  }

                  if(12 == var8) {
                     var9 = MouseListeningClass.anInt1925;
                  }

                  if(var8 == 13) {
                     var11 = ItemDefinition.ram[var4[var6++]];
                     int var17 = var4[var6++];
                     var9 = (1 << var17 & var11) == 0 ?0:1;
                  }

                  if(var8 == 14) {
                     var11 = var4[var6++];
                     var9 = NPCDefinition.method1484(var11);
                  }

                  if(var8 == 18) {
                     var9 = (Class102.player.anInt2819 >> 7) - -Class131.anInt1716;
                  }

                  if(var8 == 19) {
                     var9 = (Class102.player.anInt2829 >> 7) - -Class82.anInt1152;
                  }

                  if(var8 == 20) {
                     var9 = var4[var6++];
                  }

                  if(0 == var10) {
                     if(0 == var7) {
                        var5 += var9;
                     }

                     if(var7 == 1) {
                        var5 -= var9;
                     }

                     if(2 == var7 && var9 != 0) {
                        var5 /= var9;
                     }

                     if(var7 == 3) {
                        var5 *= var9;
                     }

                     var7 = 0;
                  } else {
                     var7 = var10;
                  }
               }
            } catch (Exception var15) {
               return -1;
            }
         } else {
            return -2;
         }
      } catch (RuntimeException var16) {
         throw Class44.clientError(var16, "we.D(" + var0 + ',' + var1 + ',' + "null" + ')');
      }
   }

   static boolean method2248(int var1) {
      try {

         return 32 <= var1 && var1 <= 126 || (var1 >= 160 && 255 >= var1 || (var1 == 128 || var1 == 140 || var1 == 151 || var1 == 156 || var1 == 159));
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "we.B(" + -157 + ',' + var1 + ')');
      }
   }

   static void method2249(int var1) {
      try {
         Class3 var2 = Class124.aClass130_1659.method1776((byte) 83 + -6);

          for(; var2 != null; var2 = Class124.aClass130_1659.method1778(-97)) {
            if((long) var1 == (65535L & var2.aLong71 >> 48)) {
               var2.method86(-1024);
            }
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "we.F(" + (byte) 83 + ',' + var1 + ')');
      }
   }

   final void method2237(int var1, int var2) {
      try {
         int anInt3023;
         if(0 == var2) {
            this.anInt3024 = -(var1 >= 0 ?var1:-var1) + this.anInt3026;
            this.anInt3024 = this.anInt3024 * this.anInt3024 >> 12;
            this.anInt3029 = this.anInt3024;
         } else {
            anInt3023 = this.anInt3022 * this.anInt3024 >> 12;
            if(anInt3023 >= 0) {
               if(anInt3023 > 4096) {
                  anInt3023 = 4096;
               }
            } else {
               anInt3023 = 0;
            }

            this.anInt3024 = -(var1 >= 0 ?var1:-var1) + this.anInt3026;
            this.anInt3024 = this.anInt3024 * this.anInt3024 >> 12;
            this.anInt3024 = this.anInt3024 * anInt3023 >> 12;
            this.anInt3029 += this.anInt3018 * this.anInt3024 >> 12;
            this.anInt3018 = this.anInt3021 * this.anInt3018 >> 12;
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "we.H(" + var1 + ',' + var2 + ',' + -20975 + ')');
      }
   }

   final void method2233() {
      try {
         this.anInt3028 = 0;
         this.anInt3029 = 0;

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "we.C(" + -949697716 + ')');
      }
   }

}
