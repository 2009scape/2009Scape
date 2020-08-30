package org.runite.jagex;
import java.awt.Component;
import java.lang.reflect.Method;

final class Class3_Sub13_Sub4 extends Class3_Sub13 {

   static int worldListOffset;
   static RSString aClass94_3055 = RSString.createRSString("k");
   int anInt3056 = 4;
   static byte[][] aByteArrayArray3057;
   int anInt3058 = 4;
   private byte[] aByteArray3059 = new byte[512];
   int anInt3060 = 4;
   static int anInt3061;
   int anInt3062 = 1638;
   private short[] aShortArray3063;
   static boolean aBoolean3064 = true;
   boolean aBoolean3065 = true;
   private short[] aShortArray3066;
   int anInt3067 = 0;


   static boolean method185(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      if(var1 < var2 && var1 < var3 && var1 < var4) {
         return false;
      } else if(var1 > var2 && var1 > var3 && var1 > var4) {
         return false;
      } else if(var0 < var5 && var0 < var6 && var0 < var7) {
         return false;
      } else if(var0 > var5 && var0 > var6 && var0 > var7) {
         return false;
      } else {
         int var8 = (var1 - var2) * (var6 - var5) - (var0 - var5) * (var3 - var2);
         int var9 = (var1 - var4) * (var5 - var7) - (var0 - var7) * (var2 - var4);
         int var10 = (var1 - var3) * (var7 - var6) - (var0 - var6) * (var4 - var3);
         return var8 * var10 > 0 && var10 * var9 > 0;
      }
   }

   final void method186(int var2, int[] var3) {
      try {
         int var5 = this.anInt3056 * Class163_Sub3.anIntArray2999[var2];
         int var4;
         int var6;
         int var8;
         short var9;
         int var10;
         int var11;
         int var12;
         int var13;
         int var14;
         int var15;
         int var17;
         int var16;
         int var18;
         if(this.anInt3058 == 1) {
            var9 = this.aShortArray3066[0];
            var8 = this.aShortArray3063[0] << 12;
            var11 = var5 * var8 >> 12;
            var12 = this.anInt3060 * var8 >> 12;
            var13 = var8 * this.anInt3056 >> 12;
            var15 = var11 >> 12;
            var17 = this.aByteArray3059[255 & var15] & 255;
            var11 &= 4095;
            var14 = Class1.anIntArray52[var11];
            var16 = var15 - -1;
            if(var16 >= var13) {
               var16 = 0;
            }

            var18 = 255 & this.aByteArray3059[255 & var16];
            if(this.aBoolean3065) {
               for(var10 = 0; Class113.anInt1559 > var10; ++var10) {
                  var4 = this.anInt3060 * Class102.anIntArray2125[var10];
                  var6 = this.method192(var8 * var4 >> 12, var18, var17, var12, 121, var11, var14);
                  var6 = var9 * var6 >> 12;
                  var3[var10] = 2048 - -(var6 >> 1);
               }
            } else {
               for(var10 = 0; var10 < Class113.anInt1559; ++var10) {
                  var4 = this.anInt3060 * Class102.anIntArray2125[var10];
                  var6 = this.method192(var8 * var4 >> 12, var18, var17, var12, 124, var11, var14);
                  var3[var10] = var9 * var6 >> 12;
               }
            }
         } else {
            var9 = this.aShortArray3066[0];
            if(var9 > 8 || -8 > var9) {
               var8 = this.aShortArray3063[0] << 12;
               var13 = var8 * this.anInt3056 >> 12;
               var12 = this.anInt3060 * var8 >> 12;
               var11 = var5 * var8 >> 12;
               var15 = var11 >> 12;
               var16 = 1 + var15;
               var17 = this.aByteArray3059[var15 & 255] & 255;
               var11 &= 4095;
               var14 = Class1.anIntArray52[var11];
               if(var13 <= var16) {
                  var16 = 0;
               }

               var18 = 255 & this.aByteArray3059[255 & var16];

               for(var10 = 0; Class113.anInt1559 > var10; ++var10) {
                  var4 = Class102.anIntArray2125[var10] * this.anInt3060;
                  var6 = this.method192(var4 * var8 >> 12, var18, var17, var12, 122, var11, var14);
                  var3[var10] = var6 * var9 >> 12;
               }
            }

            for(int var7 = 1; this.anInt3058 > var7; ++var7) {
               var9 = this.aShortArray3066[var7];
               if(8 < var9 || var9 < -8) {
                  var8 = this.aShortArray3063[var7] << 12;
                  var11 = var8 * var5 >> 12;
                  var15 = var11 >> 12;
                  var17 = this.aByteArray3059[255 & var15] & 255;
                  var12 = this.anInt3060 * var8 >> 12;
                  var16 = var15 + 1;
                  var11 &= 4095;
                  var14 = Class1.anIntArray52[var11];
                  var13 = this.anInt3056 * var8 >> 12;
                  if(var13 <= var16) {
                     var16 = 0;
                  }

                  var18 = 255 & this.aByteArray3059[var16 & 255];
                  if(this.aBoolean3065 && this.anInt3058 + -1 == var7) {
                     for(var10 = 0; var10 < Class113.anInt1559; ++var10) {
                        var4 = Class102.anIntArray2125[var10] * this.anInt3060;
                        var6 = this.method192(var8 * var4 >> 12, var18, var17, var12, 120, var11, var14);
                        var6 = (var9 * var6 >> 12) + var3[var10];
                        var3[var10] = (var6 >> 1) + 2048;
                     }
                  } else {
                     for(var10 = 0; Class113.anInt1559 > var10; ++var10) {
                        var4 = Class102.anIntArray2125[var10] * this.anInt3060;
                        var6 = this.method192(var4 * var8 >> 12, var18, var17, var12, 126, var11, var14);
                        var3[var10] += var6 * var9 >> 12;
                     }
                  }
               }
            }
         }

      } catch (RuntimeException var19) {
         throw Class44.clientError(var19, "bi.C(" + true + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ')');
      }
   }

   public static void method187(boolean var0) {
      try {
         aByteArrayArray3057 = (byte[][])null;
         if(var0) {
            aClass94_3055 = (RSString)null;
         }

         aClass94_3055 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "bi.S(" + var0 + ')');
      }
   }

   final void method158(int var1) {
      try {
         this.aByteArray3059 = Class49.method1123(16711935, this.anInt3067);
         this.method191();

         for(int var2 = -1 + this.anInt3058; var2 >= 1; --var2) {
            short var3 = this.aShortArray3066[var2];
            if(8 < var3 || var3 < -8) {
               break;
            }

            --this.anInt3058;
         }

         if(var1 != 16251) {
            this.anInt3062 = 101;
         }

      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "bi.P(" + var1 + ')');
      }
   }

   static void method188(int var0, int var1) {
      try {
         if(var1 != 0) {
            method188(38, 29);
         }

         Class154.aClass93_1955.method1522(-125, var0);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bi.U(" + var0 + ',' + var1 + ')');
      }
   }

   static void method189() {
      try {
         int var2;

         int var1;
         for(var1 = -1; Class159.localPlayerCount > var1; ++var1) {
            int var3;
            if(var1 == -1) {
               var3 = 2047;
            } else {
               var3 = Class56.localPlayerIndexes[var1];
            }

            Player var4 = Class3_Sub13_Sub22.players[var3];
            if(var4 != null && 0 < var4.textCycle) {
               --var4.textCycle;
               if(var4.textCycle == 0) {
                  var4.textSpoken = null;
               }
            }
         }

         for(var1 = 0; var1 < Class163.localNPCCount; ++var1) {
            var2 = Class15.localNPCIndexes[var1];
            NPC var6 = Class3_Sub13_Sub24.npcs[var2];
            if(null != var6 && var6.textCycle > 0) {
               --var6.textCycle;
               if(var6.textCycle == 0) {
                  var6.textSpoken = null;
               }
            }
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "bi.B(" + (byte) -62 + ')');
      }
   }

   final void method157(int var1, RSByteBuffer var2, boolean var3) {
      try {
         if(!var3) {
            this.method192(49, -110, -55, -117, 117, 10, -51);
         }

         if(var1 == 0) {
            this.aBoolean3065 = var2.getByteB() == 1;
         } else if(var1 == 1) {
            this.anInt3058 = var2.getByteB();
         } else if(var1 == 2) {
            this.anInt3062 = var2.getShort((byte)30);
            if(0 > this.anInt3062) {
               this.aShortArray3066 = new short[this.anInt3058];

               for(int var4 = 0; this.anInt3058 > var4; ++var4) {
                  this.aShortArray3066[var4] = (short)var2.getShort((byte)19);
               }
            }
         } else if(var1 == 3) {
            this.anInt3060 = this.anInt3056 = var2.getByteB();
         } else if(var1 == 4) {
            this.anInt3067 = var2.getByteB();
         } else if(var1 == 5) {
            this.anInt3060 = var2.getByteB();
         } else if(var1 == 6) {
            this.anInt3056 = var2.getByteB();
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "bi.A(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

   static int method190(int var0, int var1, byte var2, int var3) {
      try {
         int var5 = Class51.anIntArray834[Class140_Sub1_Sub2.method1940(var1, var3)];
         if(var0 > 0) {
            int var6 = Class51.anInterface2_838.method19(111, var0 & '\uffff');
            int var7;
            int var9;
            if(var6 != 0) {
               if(var3 >= 0) {
                  if(var3 > 127) {
                     var7 = 16777215;
                  } else {
                     var7 = 131586 * var3;
                  }
               } else {
                  var7 = 0;
               }

               if(var6 == 256) {
                  var5 = var7;
               } else {
                  var9 = -var6 + 256;
                  var5 = (16711680 & (var7 & '\uff00') * var6 + var9 * (var5 & '\uff00')) + (var6 * (var7 & 16711935) - -((16711935 & var5) * var9) & -16711936) >> 8;
               }
            }

            var7 = Class51.anInterface2_838.method10(106, '\uffff' & var0);
            if(var7 != 0) {
               var7 += 256;
               int var8 = ((16711680 & var5) >> 16) * var7;
               if('\uffff' < var8) {
                  var8 = '\uffff';
               }

               var9 = ((var5 & '\uff00') >> 8) * var7;
               if(var9 > '\uffff') {
                  var9 = '\uffff';
               }

               int var10 = var7 * (var5 & 255);
               if(var10 > '\uffff') {
                  var10 = '\uffff';
               }

               var5 = (var10 >> 8) + ('\uff00' & var9) + (16711711 & var8 << 8);
            }
         }

         return var5;
      } catch (RuntimeException var11) {
         throw Class44.clientError(var11, "bi.E(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   private void method191() {
      try {
         int var2;
         if(this.anInt3062 <= 0) {
            if(this.aShortArray3066 != null && this.aShortArray3066.length == this.anInt3058) {
               this.aShortArray3063 = new short[this.anInt3058];

               for(var2 = 0; var2 < this.anInt3058; ++var2) {
                  this.aShortArray3063[var2] = (short)((int)Math.pow(2.0D, (double)var2));
               }
            }
         } else {
            this.aShortArray3066 = new short[this.anInt3058];
            this.aShortArray3063 = new short[this.anInt3058];

            for(var2 = 0; var2 < this.anInt3058; ++var2) {
               this.aShortArray3066[var2] = (short)((int)(Math.pow((double)((float)this.anInt3062 / 4096.0F), (double)var2) * 4096.0D));
               this.aShortArray3063[var2] = (short)((int)Math.pow(2.0D, (double)var2));
            }
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bi.F(" + true + ')');
      }
   }

   private int method192(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      try {
         if(var5 < 117) {
            this.method158(89);
         }

         int var10 = -4096 + var6;
         int var13 = var1 >> 12;
         int var12 = 1 + var13;
         var13 &= 255;
         if(var4 <= var12) {
            var12 = 0;
         }

         var1 &= 4095;
         int var14 = this.aByteArray3059[var13 - -var3] & 3;
         int var15 = Class1.anIntArray52[var1];
         int var8;
         if(var14 > 1) {
            var8 = 2 != var14?-var1 + -var6:var1 - var6;
         } else {
            var8 = 0 == var14?var6 + var1:-var1 + var6;
         }

         var12 &= 255;
         int var11 = -4096 + var1;
         var14 = this.aByteArray3059[var3 + var12] & 3;
         int var9;
         if(var14 <= 1) {
            var9 = 0 == var14?var6 + var11:-var11 + var6;
         } else {
            var9 = 2 == var14?-var6 + var11:-var11 + -var6;
         }

         var14 = this.aByteArray3059[var13 - -var2] & 3;
         int var16 = var8 + ((var9 + -var8) * var15 >> 12);
         if(1 < var14) {
            var8 = 2 != var14?-var1 - var10:var1 + -var10;
         } else {
            var8 = 0 != var14?var10 + -var1:var1 - -var10;
         }

         var14 = 3 & this.aByteArray3059[var2 + var12];
         if(1 < var14) {
            var9 = var14 != 2 ?-var10 + -var11:var11 - var10;
         } else {
            var9 = var14 == 0?var11 + var10:var10 + -var11;
         }

         int var17 = var8 + ((-var8 + var9) * var15 >> 12);
         return var16 - -(var7 * (var17 + -var16) >> 12);
      } catch (RuntimeException var18) {
         throw Class44.clientError(var18, "bi.R(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
      }
   }

   static void method193(byte var0, Component var1) {
      try {
         if(var0 < 49) {
            worldListOffset = 85;
         }

         Method var2 = Signlink.aMethod1222;
         if(null != var2) {
            try {
               var2.invoke(var1, new Object[]{Boolean.FALSE});
            } catch (Throwable var4) {
            }
         }

         var1.addKeyListener(Class3_Sub13_Sub3.aClass148_3049);
         var1.addFocusListener(Class3_Sub13_Sub3.aClass148_3049);
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "bi.Q(" + var0 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   public Class3_Sub13_Sub4() {
      super(0, true);
   }

   final int[] method154(int var1, byte var2) {
      try {
         int[] var3 = this.aClass114_2382.method1709(var1);
         if(this.aClass114_2382.aBoolean1580) {
            this.method186(var1, var3);
         }

         return var3;
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "bi.D(" + var1 + ',' + var2 + ')');
      }
   }

}
