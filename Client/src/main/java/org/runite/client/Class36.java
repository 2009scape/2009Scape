package org.runite.client;



import org.rs09.client.config.GameConfig;

import java.util.Objects;

final class Class36 {

   private int[] anIntArray633;
   private final byte[] aByteArray635;
   private final int[] anIntArray636;
   static AbstractSprite aAbstractSprite_637;
   static int anInt638;
   static int anInt639;
   static byte[][][] aByteArrayArrayArray640;
   static int anInt641;


   static Class129 method1012() {
      try {
         try {

            return (Class129)Class.forName(GameConfig.PACKAGE_NAME + ".Class129_Sub2").newInstance();
         } catch (Throwable var2) {
            return new Class129_Sub1();
         }
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "fi.F(" + (byte) -31 + ')');
      }
   }

   static RSString method1013(byte var0, int var1) {
      try {
         RSString var2 = RSString.stringAnimator(var1);
         if(var0 >= -87) {
            return (RSString)null;
         } else {
            for(int var3 = Objects.requireNonNull(var2).length() + -3; var3 > 0; var3 -= 3) {
               var2 = RSString.stringCombiner(new RSString[]{var2.substring(0, var3, 0), TextCore.aClass94_3268, var2.substring(var3)});
            }

            return var2.length() > 9? RSString.stringCombiner(new RSString[]{ColorCore.MillionStackColor, var2.substring(0, -8 + var2.length(), 0), TextCore.MillionM, TextCore.LEFT_PARENTHESES, var2, TextCore.aClass94_995}):(6 < var2.length()? RSString.stringCombiner(new RSString[]{ColorCore.ThousandStackColor, var2.substring(0, -4 + var2.length(), 0), TextCore.ThousandK, TextCore.LEFT_PARENTHESES, var2, TextCore.aClass94_995}): RSString.stringCombiner(new RSString[]{ColorCore.DefaultStackColor, var2, TextCore.aClass94_2584}));
         }
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "fi.D(" + var0 + ',' + var1 + ')');
      }
   }

   static void method1014(int var1, int var2) {
      try {

         for(int var4 = 0; var4 < Client.anInt869; ++var4) {
            Class168 var5 = Class3_Sub13_Sub37.method350((byte)120, var4);
            if(null != var5) {
               int var6 = var5.anInt2095;
               if(0 <= var6 && !Class51.anInterface2_838.method17(var6, 126)) {
                  var6 = -1;
               }

               int var7;
               int var8;
               int var9;
               int var10;
               if(var5.anInt2098 < 0) {
                  if(var6 >= 0) {
                     var7 = Class51.anIntArray834[LinkableRSString.method729((byte)-74, Class51.anInterface2_838.method15(var6, '\uffff'), 96)];
                  } else if(-1 == var5.anInt2103) {
                     var7 = -1;
                  } else {
                     var8 = var5.anInt2103;
                     var9 = var1 + (var8 & 127);
                     if(var9 < 0) {
                        var9 = 0;
                     } else if(var9 > 127) {
                        var9 = 127;
                     }

                     var10 = var9 + (896 & var8) + ('\ufc00' & var8 + var2);
                     var7 = Class51.anIntArray834[LinkableRSString.method729((byte)-127, var10, 96)];
                  }
               } else {
                  var8 = var5.anInt2098;
                  var9 = (127 & var8) + var1;
                  if(var9 < 0) {
                     var9 = 0;
                  } else if(var9 > 127) {
                     var9 = 127;
                  }

                  var10 = (896 & var8) + ('\ufc00' & var2 + var8) + var9;
                  var7 = Class51.anIntArray834[LinkableRSString.method729((byte)-63, var10, 96)];
               }

               Class83.anIntArray1161[1 + var4] = var7;
            }
         }

      } catch (RuntimeException var11) {
         throw ClientErrorException.clientError(var11, "fi.B(" + -120 + ',' + var1 + ',' + var2 + ')');
      }
   }

   final int method1015(int var1, byte[] var3, byte[] var4, int var5, int var6) {
      try {
         var1 += var5;
         int var7 = 0;

         int var8;
         for(var8 = var6 << 3; var1 > var5; ++var5) {
            int var9 = var4[var5] & 255;
            int var10 = this.anIntArray636[var9];
            byte var11 = this.aByteArray635[var9];
            if(0 == var11) {
               throw new RuntimeException("No codeword for data value " + var9);
            }

            int var12 = var8 >> 3;
            int var13 = var8 & 7;
            var8 += var11;
            int var14 = var12 + (var13 + var11 - 1 >> 3);
            var7 &= -var13 >> 31;
            var13 += 24;
            var3[var12] = (byte)(var7 = Class3_Sub13_Sub29.bitwiseOr(var7, var10 >>> var13));
            if(var14 > var12) {
               ++var12;
               var13 -= 8;
               var3[var12] = (byte)(var7 = var10 >>> var13);
               if(var12 < var14) {
                  var13 -= 8;
                  ++var12;
                  var3[var12] = (byte)(var7 = var10 >>> var13);
                  if(var12 < var14) {
                     var13 -= 8;
                     ++var12;
                     var3[var12] = (byte)(var7 = var10 >>> var13);
                     if(var14 > var12) {
                        ++var12;
                        var13 -= 8;
                        var3[var12] = (byte)(var7 = var10 << -var13);
                     }
                  }
               }
            }
         }

         return -var6 + (var8 + 7 >> 3);
      } catch (RuntimeException var15) {
         throw ClientErrorException.clientError(var15, "fi.A(" + var1 + ',' + -81 + ',' + (var3 != null?"{...}":"null") + ',' + (var4 != null?"{...}":"null") + ',' + var5 + ',' + var6 + ')');
      }
   }

   final int method1017(int var1, int var2, byte[] var3, byte[] var5, int var6) {
      try {
         if(var2 == 0) {
            return 0;
         } else {
            int var7 = 0;
            var2 += var1;
            int var8 = var6;

            while(true) {
               byte var9 = var5[var8];
               if(var9 < 0) {
                  var7 = this.anIntArray633[var7];
               } else {
                  ++var7;
               }

               int var10;
               if((var10 = this.anIntArray633[var7]) < 0) {
                  var3[var1++] = (byte)(~var10);
                  if(var1 >= var2) {
                     break;
                  }

                  var7 = 0;
               }

               if((64 & var9) == 0) {
                  ++var7;
               } else {
                  var7 = this.anIntArray633[var7];
               }

               if((var10 = this.anIntArray633[var7]) < 0) {
                  var3[var1++] = (byte)(~var10);
                  if(var2 <= var1) {
                     break;
                  }

                  var7 = 0;
               }

               if((32 & var9) == 0) {
                  ++var7;
               } else {
                  var7 = this.anIntArray633[var7];
               }

               if((var10 = this.anIntArray633[var7]) < 0) {
                  var3[var1++] = (byte)(~var10);
                  if(var1 >= var2) {
                     break;
                  }

                  var7 = 0;
               }

               if((var9 & 16) == 0) {
                  ++var7;
               } else {
                  var7 = this.anIntArray633[var7];
               }

               if((var10 = this.anIntArray633[var7]) < 0) {
                  var3[var1++] = (byte)(~var10);
                  if(var1 >= var2) {
                     break;
                  }

                  var7 = 0;
               }

               if((var9 & 8) == 0) {
                  ++var7;
               } else {
                  var7 = this.anIntArray633[var7];
               }

               if((var10 = this.anIntArray633[var7]) < 0) {
                  var3[var1++] = (byte)(~var10);
                  if(var2 <= var1) {
                     break;
                  }

                  var7 = 0;
               }

               if((var9 & 4) == 0) {
                  ++var7;
               } else {
                  var7 = this.anIntArray633[var7];
               }

               if((var10 = this.anIntArray633[var7]) < 0) {
                  var3[var1++] = (byte)(~var10);
                  if(var2 <= var1) {
                     break;
                  }

                  var7 = 0;
               }

               if((var9 & 2) == 0) {
                  ++var7;
               } else {
                  var7 = this.anIntArray633[var7];
               }
               var10 = this.anIntArray633[var7];

               if(var10 < 0) {
                  var3[var1++] = (byte)(~var10);
                  if(var2 <= var1) {
                     break;
                  }

                  var7 = 0;
               }

               if((1 & var9) == 0) {
                  ++var7;
               } else {
                  var7 = this.anIntArray633[var7];
               }

               if(0 > (var10 = this.anIntArray633[var7])) {
                  var3[var1++] = (byte)(~var10);
                  if(var1 >= var2) {
                     break;
                  }

                  var7 = 0;
               }

               ++var8;
            }

            return -var6 + 1 + var8;
         }
      } catch (RuntimeException var11) {
         throw ClientErrorException.clientError(var11, "fi.E(" + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + -1248 + ',' + (var5 != null?"{...}":"null") + ',' + var6 + ')');
      }
   }

   Class36(byte[] var1) {
      try {
         int[] var3 = new int[33];
         int var2 = var1.length;
         this.anIntArray633 = new int[8];
         this.anIntArray636 = new int[var2];
         this.aByteArray635 = var1;
         int var4 = 0;

         for(int var5 = 0; var5 < var2; ++var5) {
            byte var6 = var1[var5];
            if(var6 != 0) {
               int var7 = 1 << 32 + -var6;
               int var8 = var3[var6];
               this.anIntArray636[var5] = var8;
               int var9;
               int var10;
               int var11;
               int var12;
               if(0 == (var8 & var7)) {
                  for(var10 = -1 + var6; var10 >= 1; --var10) {
                     var11 = var3[var10];
                     if(var11 != var8) {
                        break;
                     }

                     var12 = 1 << -var10 + 32;
                     if((var11 & var12) != 0) {
                        var3[var10] = var3[-1 + var10];
                        break;
                     }

                     var3[var10] = Class3_Sub13_Sub29.bitwiseOr(var12, var11);
                  }

                  var9 = var8 | var7;
               } else {
                  var9 = var3[-1 + var6];
               }

               var3[var6] = var9;

               for(var10 = var6 + 1; var10 <= 32; ++var10) {
                  if(var3[var10] == var8) {
                     var3[var10] = var9;
                  }
               }

               var10 = 0;

               for(var11 = 0; var11 < var6; ++var11) {
                  var12 = Integer.MIN_VALUE >>> var11;
                  if(0 == (var8 & var12)) {
                     ++var10;
                  } else {
                     if(0 == this.anIntArray633[var10]) {
                        this.anIntArray633[var10] = var4;
                     }

                     var10 = this.anIntArray633[var10];
                  }

                  if(this.anIntArray633.length <= var10) {
                     int[] var13 = new int[this.anIntArray633.length * 2];

                     System.arraycopy(this.anIntArray633, 0, var13, 0, this.anIntArray633.length);

                     this.anIntArray633 = var13;
                  }

               }

               this.anIntArray633[var10] = ~var5;
               if(var4 <= var10) {
                  var4 = var10 + 1;
               }
            }
         }

      } catch (RuntimeException var15) {
         throw ClientErrorException.clientError(var15, "fi.<init>(" + (var1 != null?"{...}":"null") + ')');
      }
   }

   static {
      anInt639 = 0;
      anInt638 = 0;
      anInt641 = 0;
   }
}
