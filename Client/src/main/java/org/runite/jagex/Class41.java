package org.runite.jagex;
import java.io.EOFException;
import java.io.IOException;

final class Class41 {

   private final Class30 aClass30_681;
   int cacheIndex;
   private final Class30 aClass30_683;
   static Class93 aClass93_684 = new Class93(64);
   static int[] anIntArray686 = new int[2];
   private final int anInt687;
   static int anInt688 = 0;
   static int anInt689;


   static void method1047(int var0, int var1, int var2, boolean var3, int var4, boolean var5) {
      try {
         if(var2 > var4) {
            int var7 = (var2 + var4) / 2;
            int var8 = var4;
            WorldListEntry var9 = Class3_Sub13_Sub16.aClass44_Sub1Array3201[var7];
            Class3_Sub13_Sub16.aClass44_Sub1Array3201[var7] = Class3_Sub13_Sub16.aClass44_Sub1Array3201[var2];
            Class3_Sub13_Sub16.aClass44_Sub1Array3201[var2] = var9;

            for(int var10 = var4; var10 < var2; ++var10) {
               if(RSString.method1535(var9, Class3_Sub13_Sub16.aClass44_Sub1Array3201[var10], 5730, var0, var1, var3, var5) <= 0) {
                  WorldListEntry var11 = Class3_Sub13_Sub16.aClass44_Sub1Array3201[var10];
                  Class3_Sub13_Sub16.aClass44_Sub1Array3201[var10] = Class3_Sub13_Sub16.aClass44_Sub1Array3201[var8];
                  Class3_Sub13_Sub16.aClass44_Sub1Array3201[var8++] = var11;
               }
            }

            Class3_Sub13_Sub16.aClass44_Sub1Array3201[var2] = Class3_Sub13_Sub16.aClass44_Sub1Array3201[var8];
            Class3_Sub13_Sub16.aClass44_Sub1Array3201[var8] = var9;
            method1047(var0, var1, -1 + var8, var3, var4, var5);
            method1047(var0, var1, var2, var3, var8 - -1, var5);
         }

      } catch (RuntimeException var12) {
         throw Class44.clientError(var12, "ge.A(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + false + ')');
      }
   }

   public final String toString() {
      try {
         return "Cache:" + this.cacheIndex;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ge.toString()");
      }
   }

   static void method1048(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      try {
         if(var5 > -15) {
            anInt688 = -64;
         }

         if(var1 >= 1 && var4 >= 1 && 102 >= var1 && var4 <= 102) {
            int var8;
            if(!NPC.method1986(41) && 0 == (2 & Class9.aByteArrayArrayArray113[0][var1][var4])) {
               var8 = var2;
               if((8 & Class9.aByteArrayArrayArray113[var2][var1][var4]) != 0) {
                  var8 = 0;
               }

               if(var8 != Class140_Sub3.anInt2745) {
                  return;
               }
            }

            var8 = var2;
            if(var2 < 3 && (2 & Class9.aByteArrayArrayArray113[1][var1][var4]) == 2) {
               var8 = var2 + 1;
            }

            Class20.method910(var4, var1, var2, var7, var8, Class86.aClass91Array1182[var2]);
            if(0 <= var0) {
               boolean var9 = KeyboardListener.aBoolean1905;
               KeyboardListener.aBoolean1905 = true;
               Class110.method1683(var8, false, var2, false, Class86.aClass91Array1182[var2], var0, var6, var1, var4, var3);
               KeyboardListener.aBoolean1905 = var9;
            }
         }

      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "ge.H(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
      }
   }

   public static void method1049(boolean var0) {
      try {
         anIntArray686 = null;
         aClass93_684 = null;
         if(!var0) {
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ge.G(" + var0 + ')');
      }
   }

   final void method1050(int var1, int var2, byte[] var3) {
      try {
         Class30 var5 = this.aClass30_681;
         synchronized(var5) {
            if(0 <= var2 && var2 <= this.anInt687) {
               boolean var6 = this.method1054((byte)87, var2, var1, var3, true);
               if(!var6) {
                  var6 = this.method1054((byte)87, var2, var1, var3, false);
               }

            } else {
               throw new IllegalArgumentException();
            }
         }
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "ge.D(" + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + (byte) -41 + ')');
      }
   }

   final byte[] method1051(int var1, byte var2) {
      try {
         Class30 var3 = this.aClass30_681;
         synchronized(var3) {
            Object var10000;
            try {
               if((long) (var1 * 6 + 6) > this.aClass30_683.method976(0)) {
                  var10000 = null;
                  return (byte[])var10000;
               }

               this.aClass30_683.method984(-35, (long)(6 * var1));
               this.aClass30_683.method978(0, Class162.aByteArray2040, 6);
               int var5 = ((255 & Class162.aByteArray2040[3]) << 16) - (-(Class162.aByteArray2040[4] << 8 & '\uff00') + -(255 & Class162.aByteArray2040[5]));
               int var4 = (Class162.aByteArray2040[2] & 255) + ('\uff00' & Class162.aByteArray2040[1] << 8) + (16711680 & Class162.aByteArray2040[0] << 16);
               if(this.anInt687 < var4) {
                  var10000 = null;
                  return (byte[])var10000;
               }

               if(0 < var5 && this.aClass30_681.method976(0) / 520L >= (long) var5) {
                  byte[] var7 = new byte[var4];
                  int var8 = 0;

                  int var13;
                  for(int var9 = 0; var4 > var8; var5 = var13) {
                     if(0 == var5) {
                        var10000 = null;
                        return (byte[])var10000;
                     }

                     int var10 = -var8 + var4;
                     this.aClass30_681.method984(-113, (long)(520 * var5));
                     if(var10 > 512) {
                        var10 = 512;
                     }

                     this.aClass30_681.method978(0, Class162.aByteArray2040, 8 + var10);
                     int var11 = (Class162.aByteArray2040[0] << 8 & '\uff00') - -(255 & Class162.aByteArray2040[1]);
                     int var12 = (Class162.aByteArray2040[3] & 255) + ('\uff00' & Class162.aByteArray2040[2] << 8);
                     int var14 = 255 & Class162.aByteArray2040[7];
                     var13 = (Class162.aByteArray2040[6] & 255) + ('\uff00' & Class162.aByteArray2040[5] << 8) + (Class162.aByteArray2040[4] << 16 & 16711680);
                     if(var1 != var11 || var9 != var12 || this.cacheIndex != var14) {
                        var10000 = null;
                        return (byte[])var10000;
                     }

                     if(var13 < 0 || (long)var13 > this.aClass30_681.method976(0) / 520L) {
                        var10000 = null;
                        return (byte[])var10000;
                     }

                     for(int var15 = 0; var10 > var15; ++var15) {
                        var7[var8++] = Class162.aByteArray2040[var15 + 8];
                     }

                     ++var9;
                  }

                  return var7;
               }

               var10000 = null;
            } catch (IOException var17) {
               return null;
            }

            return (byte[])var10000;
         }
      } catch (RuntimeException var19) {
         throw Class44.clientError(var19, "ge.C(" + var1 + ',' + var2 + ')');
      }
   }

   static RSString method1052(long var1) {
      try {
         if(var1 > 0 && var1 < 6582952005840035281L) {
            if(var1 % 37L == 0) {
               return null;
            } else {
               int var3 = 0;

               for(long var4 = var1; var4 != 0L; ++var3) {
                  var4 /= 37L;
               }

               byte[] var6 = new byte[var3];

               while(0L != var1) {
                  long var7 = var1;
                  var1 /= 37L;
                  --var3;
                  var6[var3] = Class163_Sub1_Sub1.aByteArray4005[(int)(-(var1 * 37L) + var7)];
               }

               RSString var10 = new RSString();
               var10.byteArray = var6;
               var10.length = var6.length;
               return var10;
            }
         } else {
            return null;
         }
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "ge.B(" + -29664 + ',' + var1 + ')');
      }
   }

   static void method1053(CacheIndex var1) {
      try {
         Class97.aClass153_1372 = var1;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ge.F(" + (byte) -117 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   Class41(int var1, Class30 var2, Class30 var3, int var4) {
      try {
         this.anInt687 = var4;
         this.aClass30_683 = var3;
         this.cacheIndex = var1;
         this.aClass30_681 = var2;
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "ge.<init>(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + (var3 != null?"{...}":"null") + ',' + var4 + ')');
      }
   }

   private boolean method1054(byte var1, int var2, int var3, byte[] var4, boolean var5) {
      try {
         Class30 var6 = this.aClass30_681;
         synchronized(var6) {
            try {
               int var7;
               if(var5) {
                  if(this.aClass30_683.method976(var1 ^ 87) < (long)(6 + var3 * 6)) {
                      return false;
                  }

                  this.aClass30_683.method984(-116, (long)(6 * var3));
                  this.aClass30_683.method978(0, Class162.aByteArray2040, 6);
                  var7 = (16711680 & Class162.aByteArray2040[3] << 16) + ('\uff00' & Class162.aByteArray2040[4] << 8) + (Class162.aByteArray2040[5] & 255);
                  if(var7 <= 0 || (long) var7 > this.aClass30_681.method976(0) / 520L) {
                      return false;
                  }
               } else {
                  var7 = (int)((this.aClass30_681.method976(var1 + -87) - -519L) / 520L);
                  if(var7 == 0) {
                     var7 = 1;
                  }
               }

               Class162.aByteArray2040[0] = (byte)(var2 >> 16);
               Class162.aByteArray2040[4] = (byte)(var7 >> 8);
               int var8 = 0;
               Class162.aByteArray2040[5] = (byte)var7;
               Class162.aByteArray2040[2] = (byte)var2;
               Class162.aByteArray2040[3] = (byte)(var7 >> 16);
               if(var1 != 87) {
                  this.method1054((byte)41, 108, -107, (byte[])null, true);
               }

               int var9 = 0;
               Class162.aByteArray2040[1] = (byte)(var2 >> 8);
               this.aClass30_683.method984(-14, (long)(var3 * 6));
               this.aClass30_683.method983(Class162.aByteArray2040, 0, var1 ^ -903171097, 6);

               while(true) {
                  if(var2 > var8) {
                     label146: {
                        int var10 = 0;
                        int var11;
                        if(var5) {
                           this.aClass30_681.method984(-116, (long)(520 * var7));

                           try {
                              this.aClass30_681.method978(0, Class162.aByteArray2040, 8);
                           } catch (EOFException var15) {
                              break label146;
                           }

                           var10 = ((Class162.aByteArray2040[4] & 255) << 16) + ('\uff00' & Class162.aByteArray2040[5] << 8) - -(Class162.aByteArray2040[6] & 255);
                           var11 = (255 & Class162.aByteArray2040[1]) + ((Class162.aByteArray2040[0] & 255) << 8);
                           int var13 = 255 & Class162.aByteArray2040[7];
                           int var12 = (Class162.aByteArray2040[3] & 255) + (Class162.aByteArray2040[2] << 8 & '\uff00');
                           if(var11 != var3 || var12 != var9 || var13 != this.cacheIndex) {
                               return false;
                           }

                           if(var10 < 0 || this.aClass30_681.method976(0) / 520L < (long) var10) {
                               return false;
                           }
                        }

                        var11 = -var8 + var2;
                        if(var10 == 0) {
                           var5 = false;
                           var10 = (int)((this.aClass30_681.method976(0) - -519L) / 520L);
                           if(var10 == 0) {
                              ++var10;
                           }

                           if(var7 == var10) {
                              ++var10;
                           }
                        }

                        Class162.aByteArray2040[7] = (byte)this.cacheIndex;
                        Class162.aByteArray2040[0] = (byte)(var3 >> 8);
                        if(-var8 + var2 <= 512) {
                           var10 = 0;
                        }

                        Class162.aByteArray2040[4] = (byte)(var10 >> 16);
                        if(var11 > 512) {
                           var11 = 512;
                        }

                        Class162.aByteArray2040[1] = (byte)var3;
                        Class162.aByteArray2040[6] = (byte)var10;
                        Class162.aByteArray2040[2] = (byte)(var9 >> 8);
                        Class162.aByteArray2040[3] = (byte)var9;
                        ++var9;
                        Class162.aByteArray2040[5] = (byte)(var10 >> 8);
                        this.aClass30_681.method984(var1 + -128, (long)(var7 * 520));
                        var7 = var10;
                        this.aClass30_681.method983(Class162.aByteArray2040, 0, -903171152, 8);
                        this.aClass30_681.method983(var4, var8, -903171152, var11);
                        var8 += var11;
                        continue;
                     }
                  }

                   return true;
               }
            } catch (IOException var16) {
               return false;
            }
         }
      } catch (RuntimeException var18) {
         throw Class44.clientError(var18, "ge.E(" + var1 + ',' + var2 + ',' + var3 + ',' + (var4 != null?"{...}":"null") + ',' + var5 + ')');
      }
   }

}
