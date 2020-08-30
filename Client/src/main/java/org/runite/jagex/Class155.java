package org.runite.jagex;
import java.awt.Component;

class Class155 {

   static int[] anIntArray1969 = new int[100];
   static RSString char_colon = RSString.createRSString(":");
   static int anInt1971;
   private long aLong1972 = Class5.method830((byte)-55);
   private Class3_Sub24 aClass3_Sub24_1973;
   int[] anIntArray1975;
   static int[] anIntArray1976 = new int[]{1, 0, 0, 0, 1, 0, 2, 1, 1, 1, 0, 2, 0, 0, 1, 0};
   static int anInt1977 = 0;
   static int[] anIntArray1978 = new int[128];
   private long aLong1979 = 0L;
   private final Class3_Sub24[] aClass3_Sub24Array1980 = new Class3_Sub24[8];
   private int anInt1981 = 0;
   private long aLong1982 = 0L;
   private final Class3_Sub24[] aClass3_Sub24Array1983 = new Class3_Sub24[8];
   private boolean aBoolean1984 = true;
   private int anInt1985 = 0;
   private int anInt1986;
   private int anInt1987 = 0;
   private int anInt1988 = 0;
   int anInt1989;
   int anInt1990;


   void method2149() {}

   void method2150(int var1) throws Exception {}

   void method2151() throws Exception {}

   private void method2152(int[] var1) {
      int var3 = 256;
      if(RSString.aBoolean2150) {
         var3 = 256 << 1;
      }

      Class76.method1363(var1, 0, var3);
      this.anInt1987 -= 256;
      if(this.aClass3_Sub24_1973 != null && this.anInt1987 <= 0) {
         this.anInt1987 += Class21.anInt443 >> 4;
         Class97.method1591(this.aClass3_Sub24_1973);
         this.method2155(this.aClass3_Sub24_1973, this.aClass3_Sub24_1973.method412(), (byte)-24);
         int var4 = 0;
         int var5 = 255;

         int var6;
         label101:
         for(var6 = 7; var5 != 0; --var6) {
            int var7;
            int var8;
            if(var6 < 0) {
               var7 = var6 & 3;
               var8 = -(var6 >> 2);
            } else {
               var7 = var6;
               var8 = 0;
            }

            for(int var9 = var5 >>> var7 & 286331153; var9 != 0; var9 >>>= 4) {
               if((var9 & 1) != 0) {
                  var5 &= ~(1 << var7);
                  Class3_Sub24 var10 = null;
                  Class3_Sub24 var11 = this.aClass3_Sub24Array1980[var7];

                  while(var11 != null) {
                     Class3_Sub12 var12 = var11.aClass3_Sub12_2544;
                     if(var12 != null && var12.anInt2374 > var8) {
                        var5 |= 1 << var7;
                        var10 = var11;
                        var11 = var11.aClass3_Sub24_2546;
                     } else {
                        var11.aBoolean2545 = true;
                        int var13 = var11.method409();
                        var4 += var13;
                        if(var12 != null) {
                           var12.anInt2374 += var13;
                        }

                        int anInt1968 = 32;
                        if(var4 >= anInt1968) {
                           break label101;
                        }

                        Class3_Sub24 var14 = var11.method411();
                        if(var14 != null) {
                           for(int var15 = var11.anInt2543; var14 != null; var14 = var11.method414()) {
                              this.method2155(var14, var15 * var14.method412() >> 8, (byte)-24);
                           }
                        }

                        Class3_Sub24 var18 = var11.aClass3_Sub24_2546;
                        var11.aClass3_Sub24_2546 = null;
                        if(var10 == null) {
                           this.aClass3_Sub24Array1980[var7] = var18;
                        } else {
                           var10.aClass3_Sub24_2546 = var18;
                        }

                        if(var18 == null) {
                           this.aClass3_Sub24Array1983[var7] = var10;
                        }

                        var11 = var18;
                     }
                  }
               }

               var7 += 4;
               ++var8;
            }
         }

         for(var6 = 0; var6 < 8; ++var6) {
            Class3_Sub24 var16 = this.aClass3_Sub24Array1980[var6];

            Class3_Sub24 var17;
            for(this.aClass3_Sub24Array1980[var6] = this.aClass3_Sub24Array1983[var6] = null; var16 != null; var16 = var17) {
               var17 = var16.aClass3_Sub24_2546;
               var16.aClass3_Sub24_2546 = null;
            }
         }
      }

      if(this.anInt1987 < 0) {
         this.anInt1987 = 0;
      }

      if(this.aClass3_Sub24_1973 != null) {
         this.aClass3_Sub24_1973.method413(var1, 0, 256);
      }

      this.aLong1972 = Class5.method830((byte)-55);
   }

   final synchronized void method2153() {
      try {
         if(null != this.anIntArray1975) {
            long var2 = Class5.method830((byte)-55);

            try {
               if(0L != this.aLong1982) {
                  if(var2 < this.aLong1982) {
                     return;
                  }

                  this.method2150(this.anInt1990);
                  this.aBoolean1984 = true;
                  this.aLong1982 = 0L;
               }

               int var4 = this.method2157();
               if(-var4 + this.anInt1985 > this.anInt1981) {
                  this.anInt1981 = -var4 + this.anInt1985;
               }

               int var5 = this.anInt1989 - -this.anInt1986;
               if(256 + var5 > 16384) {
                  var5 = 16128;
               }

               if(this.anInt1990 < var5 + 256) {
                  this.anInt1990 += 1024;
                  if(this.anInt1990 > 16384) {
                     this.anInt1990 = 16384;
                  }

                  this.method2160();
                  var4 = 0;
                  this.method2150(this.anInt1990);
                  if(this.anInt1990 < 256 + var5) {
                     var5 = -256 + this.anInt1990;
                     this.anInt1986 = -this.anInt1989 + var5;
                  }

                  this.aBoolean1984 = true;
               }

               while(var4 < var5) {
                  var4 += 256;
                  this.method2152(this.anIntArray1975);
                  this.method2149();
               }

               if(this.aLong1979 < var2) {
                  if(this.aBoolean1984) {
                     this.aBoolean1984 = false;
                  } else {
                     if(this.anInt1981 == 0 && this.anInt1988 == 0) {
                        this.method2160();
                        this.aLong1982 = var2 - -2000L;
                        return;
                     }

                     this.anInt1986 = Math.min(this.anInt1988, this.anInt1981);
                     this.anInt1988 = this.anInt1981;
                  }

                  this.aLong1979 = 2000L + var2;
                  this.anInt1981 = 0;
               }

               this.anInt1985 = var4;
            } catch (Exception var7) {
               this.method2160();
               this.aLong1982 = var2 + 2000L;
            }

            try {
               if(var2 > 500000L + this.aLong1972) {
                  var2 = this.aLong1972;
               }

               while(var2 > this.aLong1972 + 5000L) {
                  this.method2161();
                  this.aLong1972 += (long)(256000 / Class21.anInt443);
               }
            } catch (Exception var6) {
               this.aLong1972 = var2;
            }

         }
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "vh.Q(" + (byte) -34 + ')');
      }
   }

   final synchronized void method2154(int var1, Class3_Sub24 var2) {
      try {
         this.aClass3_Sub24_1973 = var2;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "vh.I(" + var1 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   private void method2155(Class3_Sub24 var1, int var2, byte var3) {
      try {
         if(var3 != -24) {
            this.method2155((Class3_Sub24)null, -105, (byte)87);
         }

         int var4 = var2 >> 5;
         Class3_Sub24 var5 = this.aClass3_Sub24Array1983[var4];
         if(null == var5) {
            this.aClass3_Sub24Array1980[var4] = var1;
         } else {
            var5.aClass3_Sub24_2546 = var1;
         }

         this.aClass3_Sub24Array1983[var4] = var1;
         var1.anInt2543 = var2;
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "vh.H(" + (var1 != null?"{...}":"null") + ',' + var2 + ',' + var3 + ')');
      }
   }

   static Class10 method2156(RSByteBuffer var1) {
      try {
         Class10 var2 = new Class10();
         var2.anInt149 = var1.getShort();

         var2.aClass3_Sub28_Sub4_151 = Class3_Sub29.method733(var2.anInt149);
         return var2;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "vh.M(" + 1024 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   int method2157() {
      try {
         return this.anInt1990;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "vh.B()");
      }
   }

   final synchronized void method2158() {
      try {
         this.aBoolean1984 = true;

         try {
            this.method2151();
         } catch (Exception var3) {
            this.method2160();
            this.aLong1982 = Class5.method830((byte)-55) + 2000L;
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "vh.L(" + (byte) -78 + ')');
      }
   }

   final void method2159(int var1) {
      try {
         this.aBoolean1984 = true;
         if(var1 <= 54) {
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "vh.J(" + var1 + ')');
      }
   }

   void method2160() {}

   private void method2161() {
      try {
         this.anInt1987 -= 256;
         if(0 > this.anInt1987) {
            this.anInt1987 = 0;
         }

         if(null != this.aClass3_Sub24_1973) {
            this.aClass3_Sub24_1973.method415(256);
         }

      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "vh.K(" + 256 + ',' + 1 + ')');
      }
   }

   static void method2162(GameObject var0, int var1, int var2, int var3) {
      Class3_Sub2 var4;
      if(var2 < IOHandler.anInt1234) {
         var4 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var1][var2 + 1][var3];
         if(var4 != null && var4.aClass12_2230 != null && var4.aClass12_2230.object.method1865()) {
            var0.method1866(var4.aClass12_2230.object, 128, 0, 0, true);
         }
      }

      if(var3 < IOHandler.anInt1234) {
         var4 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var1][var2][var3 + 1];
         if(var4 != null && var4.aClass12_2230 != null && var4.aClass12_2230.object.method1865()) {
            var0.method1866(var4.aClass12_2230.object, 0, 0, 128, true);
         }
      }

      if(var2 < IOHandler.anInt1234 && var3 < Class3_Sub13_Sub15.anInt3179) {
         var4 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var1][var2 + 1][var3 + 1];
         if(var4 != null && var4.aClass12_2230 != null && var4.aClass12_2230.object.method1865()) {
            var0.method1866(var4.aClass12_2230.object, 128, 0, 128, true);
         }
      }

      if(var2 < IOHandler.anInt1234 && var3 > 0) {
         var4 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var1][var2 + 1][var3 - 1];
         if(var4 != null && var4.aClass12_2230 != null && var4.aClass12_2230.object.method1865()) {
            var0.method1866(var4.aClass12_2230.object, 128, 0, -128, true);
         }
      }

   }

   final synchronized void method2163() {
      try {
         if(null != Class38_Sub1.aClass15_2613) {
            boolean var2 = true;

            for(int var3 = 0; var3 < 2; ++var3) {
               if(this == Class38_Sub1.aClass15_2613.aClass155Array352[var3]) {
                  Class38_Sub1.aClass15_2613.aClass155Array352[var3] = null;
               }

               if(null != Class38_Sub1.aClass15_2613.aClass155Array352[var3]) {
                  var2 = false;
               }
            }

            if(var2) {
               Class38_Sub1.aClass15_2613.aBoolean345 = true;

               while(Class38_Sub1.aClass15_2613.aBoolean353) {
                  Class3_Sub13_Sub34.method331(50L, 64);
               }

               Class38_Sub1.aClass15_2613 = null;
            }
         }

         this.method2160();
         this.anIntArray1975 = null;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "vh.P(" + false + ')');
      }
   }

   void method2164(Component var1) throws Exception {}

   public static void method2165(int var0) {
      try {
         char_colon = null;
         anIntArray1976 = null;
         if(var0 != 0) {
            method2165(-20);
         }

         anIntArray1969 = null;
         anIntArray1978 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "vh.G(" + var0 + ')');
      }
   }

}
