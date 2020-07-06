package org.runite.jagex;
import org.runite.GameLaunch;

import java.applet.Applet;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

final class RSString implements Interface3 {

   static int anInt2145;
   static boolean aBoolean2146 = false;
   static int incomingOpcode = 0;
   static int anInt2148 = 0;
   static RSString aClass94_2149 = RSString.createRSString("Bitte warten Sie )2 es wird versucht)1 die Verbindung wiederherzustellen)3");
   static boolean aBoolean2150;
   private boolean aBoolean2152 = true;
   byte[] byteArray;
   static boolean aBoolean2154;
   static RSString aClass94_2155 = RSString.createRSString(":allyreq:");
   int length;
   static int[] anIntArray2157 = new int[50];
   private static RSString aClass94_2158 = RSString.createRSString("Allocated memory");
   static RSString aClass94_2151 = aClass94_2158;

   final URL method1527(boolean var1) throws MalformedURLException {
      try {
         if(var1) {
            this.method1548(true, 22);
         }

         return new URL(new String(this.byteArray, 0, this.length));
      } catch (RuntimeException var3) {
         throw Class44.method1067(var3, "na.W(" + var1 + ')');
      }
   }

   final boolean method1528(byte var1, RSString var2) {
      try {
         if(var2 == null) {
            return false;
         } else if(this == var2) {
            return true;
         } else if(this.length != var2.length) {
            return false;
         } else {
            if(var1 != -42) {
               this.method1568(-127);
            }

            byte[] var4 = var2.byteArray;
            byte[] var3 = this.byteArray;

            for(int var5 = 0; ~this.length < ~var5; ++var5) {
               if(var3[var5] != var4[var5]) {
                  return false;
               }
            }

            return true;
         }
      } catch (RuntimeException var6) {
         throw Class44.method1067(var6, "na.F(" + var1 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   static final boolean method1529(int var0, int var1, int var2, int var3, int var4, int var5, int var6, boolean var7) {
      try {
         long var8 = Class157.method2174(var6, var1 + var5, var3 + var0);
         int var10;
         int var11;
         int var12;
         ObjectDefinition var13;
         int var14;
         int[] var15;
         int var16;
         if(~var8 != -1L) {
            var10 = 3 & (int)var8 >> 20;
            var11 = (508650 & (int)var8) >> 14;
            var12 = Integer.MAX_VALUE & (int)(var8 >>> 32);
            var13 = Class162.getObjectDefinition(4, var12);
            if(0 != ~var13.anInt1516) {
               if(!Class15.method888(var1, var13, false, var0, var5, var3, var10)) {
                  return false;
               }
            } else {
               var14 = var2;
               if(~var8 < -1L) {
                  var14 = var4;
               }

               var15 = Class74.anIntArray1100;
               var16 = 4 * (-(var3 * 512) + '\uce00') + var1 * 4 + 24624;
               if(var11 == 0 || var11 == 2) {
                  if(~var10 == -1) {
                     var15[var16] = var14;
                     var15[512 + var16] = var14;
                     var15[var16 - -1024] = var14;
                     var15[1536 + var16] = var14;
                  } else if(~var10 != -2) {
                     if(~var10 != -3) {
                        if(3 == var10) {
                           var15[var16 + 1536] = var14;
                           var15[1536 + var16 - -1] = var14;
                           var15[var16 + 1538] = var14;
                           var15[3 + var16 + 1536] = var14;
                        }
                     } else {
                        var15[var16 - -3] = var14;
                        var15[var16 - -3 - -512] = var14;
                        var15[var16 - -3 + 1024] = var14;
                        var15[var16 + 3 + 1536] = var14;
                     }
                  } else {
                     var15[var16] = var14;
                     var15[1 + var16] = var14;
                     var15[var16 - -2] = var14;
                     var15[var16 - -3] = var14;
                  }
               }

               if(var11 == 3) {
                  if(var10 == 0) {
                     var15[var16] = var14;
                  } else if(1 == var10) {
                     var15[var16 - -3] = var14;
                  } else if(-3 == ~var10) {
                     var15[var16 - -3 + 1536] = var14;
                  } else if(-4 == ~var10) {
                     var15[var16 - -1536] = var14;
                  }
               }

               if(~var11 == -3) {
                  if(-4 != ~var10) {
                     if(-1 != ~var10) {
                        if(-2 != ~var10) {
                           if(2 == var10) {
                              var15[1536 + var16] = var14;
                              var15[var16 - -1536 + 1] = var14;
                              var15[1536 + var16 + 2] = var14;
                              var15[var16 + 1539] = var14;
                           }
                        } else {
                           var15[var16 - -3] = var14;
                           var15[512 + 3 + var16] = var14;
                           var15[3 + (var16 - -1024)] = var14;
                           var15[1536 + var16 + 3] = var14;
                        }
                     } else {
                        var15[var16] = var14;
                        var15[1 + var16] = var14;
                        var15[2 + var16] = var14;
                        var15[3 + var16] = var14;
                     }
                  } else {
                     var15[var16] = var14;
                     var15[var16 - -512] = var14;
                     var15[var16 + 1024] = var14;
                     var15[1536 + var16] = var14;
                  }
               }
            }
         }

         var8 = Class3_Sub28_Sub5.method557(var6, var1 - -var5, var0 + var3);
         if(var8 != 0L) {
            var10 = (int)var8 >> 20 & 3;
            var11 = ((int)var8 & 520964) >> 14;
            var12 = (int)(var8 >>> 32) & Integer.MAX_VALUE;
            var13 = Class162.getObjectDefinition(4, var12);
            if(~var13.anInt1516 != 0) {
               if(!Class15.method888(var1, var13, false, var0, var5, var3, var10)) {
                  return false;
               }
            } else if(var11 == 9) {
               var14 = 15658734;
               if(~var8 < -1L) {
                  var14 = 15597568;
               }

               var16 = var1 * 4 + (24624 - -(2048 * (103 - var3)));
               var15 = Class74.anIntArray1100;
               if(~var10 != -1 && ~var10 != -3) {
                  var15[var16] = var14;
                  var15[var16 - -512 - -1] = var14;
                  var15[var16 - -1024 - -2] = var14;
                  var15[1536 + var16 - -3] = var14;
               } else {
                  var15[1536 + var16] = var14;
                  var15[var16 - -1025] = var14;
                  var15[var16 + 512 + 2] = var14;
                  var15[var16 - -3] = var14;
               }
            }
         }

         var8 = Class3_Sub2.method104(var6, var1 + var5, var3 + var0);
         if(var8 != 0L) {
            var10 = (int)var8 >> 20 & 3;
            var11 = (int)(var8 >>> 32) & Integer.MAX_VALUE;
            ObjectDefinition var18 = Class162.getObjectDefinition(4, var11);
            if(0 != ~var18.anInt1516 && !Class15.method888(var1, var18, !var7, var0, var5, var3, var10)) {
               return false;
            }
         }

         if(!var7) {
            aBoolean2150 = true;
         }

         return true;
      } catch (RuntimeException var17) {
         throw Class44.method1067(var17, "na.N(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
      }
   }

   final int method1530(byte var1, int var2) {
      try {
         if(var2 < 1 || -37 > ~var2) {
            var2 = 10;
         }

         boolean var3 = false;
         boolean var4 = false;
         int var5 = 0;
         int var6 = 0;

         for(int var7 = 29 / ((-47 - var1) / 37); this.length > var6; ++var6) {
            int var8 = 255 & this.byteArray[var6];
            if(~var6 == -1) {
               if(45 == var8) {
                  var3 = true;
                  continue;
               }

               if(var8 == 43) {
                  continue;
               }
            }

            if(var8 >= 48 && 57 >= var8) {
               var8 -= 48;
            } else if(65 <= var8 && -91 <= ~var8) {
               var8 -= 55;
            } else {
               if(-98 < ~var8 || ~var8 < -123) {
                  throw new NumberFormatException();
               }

               var8 -= 87;
            }

            if(~var2 >= ~var8) {
               throw new NumberFormatException();
            }

            if(var3) {
               var8 = -var8;
            }

            int var9 = var8 + var5 * var2;
            if(var9 / var2 != var5) {
               throw new NumberFormatException();
            }

            var5 = var9;
            var4 = true;
         }

         if(!var4) {
            throw new NumberFormatException();
         } else {
            return var5;
         }
      } catch (RuntimeException var10) {
         throw Class44.method1067(var10, "na.AB(" + var1 + ',' + var2 + ')');
      }
   }

   final boolean equals(int var1, RSString var2) {
      try {
         if(var2 == null) {
            return false;
         } else if(this.length != var2.length) {
            return false;
         } else {
            int var4 = -54 / ((var1 - -43) / 61);

            for(int var3 = 0; ~this.length < ~var3; ++var3) {
               byte var5 = this.byteArray[var3];
               if(-66 >= ~var5 && ~var5 >= -91 || ~var5 <= 63 && var5 <= -34 && ~var5 != 40) {
                  var5 = (byte)(var5 + 32);
               }

               byte var6 = var2.byteArray[var3];
               if(65 <= var6 && -91 <= ~var6 || -64 <= var6 && var6 <= -34 && 40 != ~var6) {
                  var6 = (byte)(var6 + 32);
               }

               if(~var5 != ~var6) {
                  return false;
               }
            }

            return true;
         }
      } catch (RuntimeException var7) {
         throw Class44.method1067(var7, "na.EA(" + var1 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   final void drawString(int x, int y, Graphics var3, byte var4) {
      try {
         if(var4 < -85) {
            String string;
            try {
               string = new String(this.byteArray, 0, this.length, "ISO-8859-1");
            } catch (UnsupportedEncodingException var7) {
               string = new String(this.byteArray, 0, this.length);
            }
            var3.drawString(string, x, y);
         }
      } catch (RuntimeException var8) {
         throw Class44.method1067(var8, "na.B(" + y + ',' + x + ',' + (var3 != null?"{...}":"null") + ',' + var4 + ')');
      }
   }

   final RSString method1533(RSString var1, boolean var2) {
      try {
         if(this.aBoolean2152) {
            if(!var2) {
               return (RSString)null;
            } else {
               if(var1.length + this.length > this.byteArray.length) {
                  int var3;
                  for(var3 = 1; ~(var1.length + this.length) < ~var3; var3 += var3) {
                     ;
                  }

                  byte[] var4 = new byte[var3];
                  Class76.method1357(this.byteArray, 0, var4, 0, this.length);
                  this.byteArray = var4;
               }

               Class76.method1357(var1.byteArray, 0, this.byteArray, this.length, var1.length);
               this.length += var1.length;
               return this;
            }
         } else {
            throw new IllegalArgumentException();
         }
      } catch (RuntimeException var5) {
         throw Class44.method1067(var5, "na.E(" + (var1 != null?"{...}":"null") + ',' + var2 + ')');
      }
   }

   final RSString method1534(int var1) {
      try {
         if(var1 != -98) {
            this.method1562((byte)-53, (RSString)null);
         }

         RSString var2 = new RSString();
         var2.length = this.length;
         var2.byteArray = new byte[this.length];

         for(int var3 = 0; ~this.length < ~var3; ++var3) {
            byte var4 = this.byteArray[var3];
            if(65 <= var4 && var4 <= 90 || var4 >= -64 && ~var4 >= 33 && var4 != -41) {
               var4 = (byte)(var4 + 32);
            }

            var2.byteArray[var3] = var4;
         }

         return var2;
      } catch (RuntimeException var5) {
         throw Class44.method1067(var5, "na.VA(" + var1 + ')');
      }
   }

   static final int method1535(WorldListEntry var0, WorldListEntry var1, int var2, int var3, int var4, boolean var5, boolean var6) {
      try {
         int var7 = Class161.method2201(var1, var4, var2 + -5638, var0, var6);
         if(var7 == 0) {
            if(var2 != 5730) {
               return -76;
            } else if(var3 != -1) {
               int var8 = Class161.method2201(var1, var3, var2 ^ 5651, var0, var5);
               return !var5?var8:-var8;
            } else {
               return 0;
            }
         } else {
            return !var6?var7:-var7;
         }
      } catch (RuntimeException var9) {
         throw Class44.method1067(var9, "na.D(" + (var0 != null?"{...}":"null") + ',' + (var1 != null?"{...}":"null") + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ')');
      }
   }

   final RSString method1536(int var1) {
      try {
         byte var3 = 2;
         RSString var2 = new RSString();
         var2.length = this.length;
         if(var1 < 67) {
            return (RSString)null;
         } else {
            var2.byteArray = new byte[this.length];

            for(int var4 = 0; ~this.length < ~var4; ++var4) {
               byte var5 = this.byteArray[var4];
               if((-98 < ~var5 || 122 < var5) && (31 < ~var5 || ~var5 < 1 || var5 == -9)) {
                  if((var5 < 65 || ~var5 < -91) && (var5 < -64 || var5 > -34 || ~var5 == 40)) {
                     if(~var5 != -47 && 33 != var5 && var5 != 63) {
                        if(32 == var5) {
                           if(2 != var3) {
                              var3 = 1;
                           }
                        } else {
                           var3 = 1;
                        }
                     } else {
                        var3 = 2;
                     }
                  } else {
                     if(0 == var3) {
                        var5 = (byte)(var5 + 32);
                     }

                     var3 = 0;
                  }
               } else {
                  if(2 == var3) {
                     var5 = (byte)(var5 - 32);
                  }

                  var3 = 0;
               }

               var2.byteArray[var4] = var5;
            }

            return var2;
         }
      } catch (RuntimeException var6) {
         throw Class44.method1067(var6, "na.DA(" + var1 + ')');
      }
   }

   static final Class3_Sub28_Sub16_Sub2 method1537(CacheIndex var0, int var1, boolean var2) {
      try {
         if(!Class140_Sub7.method2029((byte)-118, var0, var1)) {
            return null;
         } else {
            if(var2) {
               method1539(-39, true, -93, (CacheIndex)null);
            }

            return Class117.method1722(-93);
         }
      } catch (RuntimeException var4) {
         throw Class44.method1067(var4, "na.GB(" + (var0 != null?"{...}":"null") + ',' + var1 + ',' + var2 + ')');
      }
   }

   final long method1538(int var1) {
      try {
         long var2 = 0L;
         if(var1 < 4) {
            aClass94_2158 = (RSString)null;
         }

         for(int var4 = 0; var4 < this.length; ++var4) {
            var2 = (long)(this.byteArray[var4] & 255) + (var2 << 5) + -var2;
         }

         return var2;
      } catch (RuntimeException var5) {
         throw Class44.method1067(var5, "na.C(" + var1 + ')');
      }
   }

   static final LDIndexedSprite method1539(int var0, boolean var1, int var2, CacheIndex var3) {
      try {
         if(!var1) {
            method1535((WorldListEntry)null, (WorldListEntry)null, -64, -40, 23, false, false);
         }
       //  System.out.println("RSString " + var2);
         return Class75_Sub4.method1351(var3, var0, var2, -30901)?Class77.method1364((byte)82):null;
      } catch (RuntimeException var5) {
         throw Class44.method1067(var5, "na.MA(" + var0 + ',' + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ')');
      }
   }

   final int length(int var1) {
      try {
         if(var1 >= -16) {
            this.method1544(false);
         }

         return this.length;
      } catch (RuntimeException var3) {
         throw Class44.method1067(var3, "na.M(" + var1 + ')');
      }
   }

   public static void method1541(int var0) {
      try {
         aClass94_2151 = null;
         if(var0 != -8635) {
            aClass94_2151 = (RSString)null;
         }

         anIntArray2157 = null;
         aClass94_2155 = null;
         aClass94_2149 = null;
         aClass94_2158 = null;
      } catch (RuntimeException var2) {
         throw Class44.method1067(var2, "na.R(" + var0 + ')');
      }
   }

   final RSString method1542(int var1, RSString var2, int var3, int var4) {
      try {
         if(!this.aBoolean2152) {
            throw new IllegalArgumentException();
         } else if(0 <= var3 && var3 <= var4 && ~var4 >= ~var2.length) {
            if(this.length + (var4 - var3) > this.byteArray.length) {
               int var5;
               for(var5 = 1; ~(this.length + var2.length) < ~var5; var5 += var5) {
                  ;
               }

               byte[] var6 = new byte[var5];
               Class76.method1357(this.byteArray, 0, var6, 0, this.length);
               this.byteArray = var6;
            }

            Class76.method1357(var2.byteArray, var3, this.byteArray, this.length, -var3 + var4);
            if(var1 != 1) {
               aClass94_2155 = (RSString)null;
            }

            this.length += var4 + -var3;
            return this;
         } else {
            throw new IllegalArgumentException();
         }
      } catch (RuntimeException var7) {
         throw Class44.method1067(var7, "na.O(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ',' + var4 + ')');
      }
   }

   final boolean method1543(int var1) {
      try {
         if(var1 < 79) {
            this.method1552((byte)114);
         }

         return this.method1561(10, true);
      } catch (RuntimeException var3) {
         throw Class44.method1067(var3, "na.I(" + var1 + ')');
      }
   }

   final RSString method1544(boolean var1) {
      try {
         RSString var2 = new RSString();
         var2.length = this.length;
         var2.byteArray = new byte[var2.length];
         if(!var1) {
            return (RSString)null;
         } else {
            for(int var3 = 0; this.length > var3; ++var3) {
               var2.byteArray[this.length - var3 + -1] = this.byteArray[var3];
            }

            return var2;
         }
      } catch (RuntimeException var4) {
         throw Class44.method1067(var4, "na.FB(" + var1 + ')');
      }
   }

   final RSString method1545(byte var1) {
      try {
         RSString var2 = new RSString();
         var2.length = this.length;
         var2.byteArray = new byte[this.length];
         boolean var3 = true;
         int var4 = 0;
         if(var1 != -50) {
            this.toString();
         }

         for(; ~this.length < ~var4; ++var4) {
            byte var5 = this.byteArray[var4];
            if(~var5 != -96) {
               if(97 <= var5 && var5 <= 122 && var3) {
                  var3 = false;
                  var2.byteArray[var4] = (byte)(-32 + var5);
               } else {
                  var2.byteArray[var4] = var5;
                  var3 = false;
               }
            } else {
               var3 = true;
               var2.byteArray[var4] = 32;
            }
         }

         return var2;
      } catch (RuntimeException var6) {
         throw Class44.method1067(var6, "na.G(" + var1 + ')');
      }
   }

   final int method1546(byte var1, RSString var2) {
      try {
         if(var1 >= -44) {
            aBoolean2150 = true;
         }

         int var3 = 0;
         int var4 = 0;
         int var6 = var2.length;
         int var5 = this.length;
         int var7 = this.length;
         int var8 = var2.length;
         int var9 = 0;
         int var10 = 0;

         while(-1 != ~var5 && var6 != 0) {
            if(~var3 != -157 && var3 != 230) {
               if(140 != var3 && -199 != ~var3) {
                  if(var3 != 223) {
                     var3 = this.byteArray[var9] & 255;
                     ++var9;
                  } else {
                     var3 = 115;
                  }
               } else {
                  var3 = 69;
               }
            } else {
               var3 = 101;
            }

            if(!Class151_Sub1.method2103(var3, -116)) {
               --var5;
            } else {
               ++var7;
            }

            if(var4 != 156 && 230 != var4) {
               if(~var4 != -141 && ~var4 != -199) {
                  if(223 == var4) {
                     var4 = 115;
                  } else {
                     var4 = 255 & var2.byteArray[var10];
                     ++var10;
                  }
               } else {
                  var4 = 69;
               }
            } else {
               var4 = 101;
            }

            if(Class151_Sub1.method2103(var4, -86)) {
               ++var8;
            } else {
               --var6;
            }

            if(~Class158.anIntArray2004[var3] > ~Class158.anIntArray2004[var4]) {
               return -1;
            }

            if(~Class158.anIntArray2004[var4] > ~Class158.anIntArray2004[var3]) {
               return 1;
            }
         }

         return var8 <= var7?(var7 > var8?1:0):-1;
      } catch (RuntimeException var11) {
         throw Class44.method1067(var11, "na.FA(" + var1 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   final URL method1547(URL var1, boolean var2) throws MalformedURLException {
      try {
         if(!var2) {
            this.byteArray = (byte[])null;
         }

         return new URL(var1, new String(this.byteArray, 0, this.length));
      } catch (RuntimeException var4) {
         throw Class44.method1067(var4, "na.EB(" + (var1 != null?"{...}":"null") + ',' + var2 + ')');
      }
   }

   final RSString method1548(boolean var1, int var2) {
      try {
         if(~var2 < -1 && ~var2 >= -256) {
            RSString var3 = new RSString();
            var3.byteArray = new byte[1 + this.length];
            var3.length = this.length + 1;
            if(var1) {
               aClass94_2155 = (RSString)null;
            }

            Class76.method1357(this.byteArray, 0, var3.byteArray, 0, this.length);
            var3.byteArray[this.length] = (byte)var2;
            return var3;
         } else {
            throw new IllegalArgumentException("invalid char");
         }
      } catch (RuntimeException var4) {
         throw Class44.method1067(var4, "na.OA(" + var1 + ',' + var2 + ')');
      }
   }

   final void method1549(boolean var1) {
      try {
         String var2;
         try {
            if(var1) {
               return;
            }

            var2 = new String(this.byteArray, 0, this.length, "ISO-8859-1");
         } catch (UnsupportedEncodingException var4) {
            var2 = new String(this.byteArray, 0, this.length);
         }

        // System.out.println(var2);
      } catch (RuntimeException var5) {
         throw Class44.method1067(var5, "na.BA(" + var1 + ')');
      }
   }

   final boolean endsWith(byte var1, RSString var2) {
      try {
         if(var2.length > this.length) {
            return false;
         } else {
            int var3 = -var2.length + this.length;
            if(var1 >= -25) {
               anIntArray2157 = (int[])null;
            }

            for(int var4 = 0; var4 < var2.length; ++var4) {
               if(this.byteArray[var3 + var4] != var2.byteArray[var4]) {
                  return false;
               }
            }

            return true;
         }
      } catch (RuntimeException var5) {
         throw Class44.method1067(var5, "na.TA(" + var1 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   final int indexOf(RSString var1, int var2) {
      try {
         return var2 <= 49?-20:this.method1566(var1, 0, -1);
      } catch (RuntimeException var4) {
         throw Class44.method1067(var4, "na.A(" + (var1 != null?"{...}":"null") + ',' + var2 + ')');
      }
   }

   final int method1552(byte var1) {
      try {
         if(var1 > -89) {
            this.method1557(33, 31, -79);
         }

         return this.method1530((byte)-114, 10);
      } catch (RuntimeException var3) {
         throw Class44.method1067(var3, "na.T(" + var1 + ')');
      }
   }

   final void method1553(int var1, boolean var2) {
      try {
         if(!this.aBoolean2152) {
            throw new IllegalArgumentException();
         } else if(-1 < ~var1) {
            throw new IllegalArgumentException();
         } else {
            int var3;
            if(~var1 < ~this.byteArray.length) {
               for(var3 = 1; ~var3 > ~var1; var3 += var3) {
                  ;
               }

               byte[] var4 = new byte[var3];
               Class76.method1357(this.byteArray, 0, var4, 0, this.length);
               this.byteArray = var4;
            }

            for(var3 = this.length; ~var3 > ~var1; ++var3) {
               this.byteArray[var3] = 32;
            }

            if(var2) {
               anIntArray2157 = (int[])null;
            }

            this.length = var1;
         }
      } catch (RuntimeException var5) {
         throw Class44.method1067(var5, "na.RA(" + var1 + ',' + var2 + ')');
      }
   }

   public final String toString() {
		if (byteArray == null) {
			throw new RuntimeException();
		}
		return new String(byteArray);
	}

   final void method1554(boolean var1, Applet var2) throws Throwable {
      try {
         if(var1) {
            String var3 = new String(this.byteArray, 0, this.length);
            Class42.method1057(var2, !var1, var3);
         }
      } catch (RuntimeException var4) {
         throw Class44.method1067(var4, "na.AA(" + var1 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   final int method1555(int var1, int var2, int var3) {
      try {
         byte var4 = (byte)var1;
         if(var3 != 1536) {
            return 123;
         } else {
            for(int var5 = var2; ~var5 > ~this.length; ++var5) {
               if(this.byteArray[var5] == var4) {
                  return var5;
               }
            }

            return -1;
         }
      } catch (RuntimeException var6) {
         throw Class44.method1067(var6, "na.NA(" + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   final RSString method1556(int var1, byte var2) {
      try {
         if(var2 != -74) {
            this.toString();
         }

         return this.method1557(this.length, var2 ^ -74, var1);
      } catch (RuntimeException var4) {
         throw Class44.method1067(var4, "na.CA(" + var1 + ',' + var2 + ')');
      }
   }

   final RSString method1557(int var1, int var2, int var3) {
      try {
         RSString var4 = new RSString();
         var4.length = -var3 + var1;
         var4.byteArray = new byte[-var3 + var1];
         Class76.method1357(this.byteArray, var3, var4.byteArray, var2, var4.length);
         return var4;
      } catch (RuntimeException var5) {
         throw Class44.method1067(var5, "na.U(" + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   final boolean method1558(RSString var1, int var2) {
      try {
         if(~this.length <= ~var1.length) {
            for(int var3 = var2; var3 < var1.length; ++var3) {
               if(~this.byteArray[var3] != ~var1.byteArray[var3]) {
                  return false;
               }
            }

            return true;
         } else {
            return false;
         }
      } catch (RuntimeException var4) {
         throw Class44.method1067(var4, "na.UA(" + (var1 != null?"{...}":"null") + ',' + var2 + ')');
      }
   }

   public final boolean equals(Object var1) {
      try {
         if(!(var1 instanceof RSString)) {
            throw new IllegalArgumentException();
         } else {
            return this.method1528((byte)-42, (RSString)var1);
         }
      } catch (RuntimeException var3) {
         throw Class44.method1067(var3, "na.equals(" + (var1 != null?"{...}":"null") + ')');
      }
   }

   final int method1559(RSString var1, int var2) {
      try {
         if(var2 != -1) {
            this.method1544(true);
         }

         int var3;
         if(var1.length < this.length) {
            var3 = var1.length;
         } else {
            var3 = this.length;
         }

         for(int var4 = 0; var3 > var4; ++var4) {
            if((255 & this.byteArray[var4]) < (var1.byteArray[var4] & 255)) {
               return -1;
            }

            if(~(var1.byteArray[var4] & 255) > ~(this.byteArray[var4] & 255)) {
               return 1;
            }
         }

         if(var1.length > this.length) {
            return -1;
         } else if(~var1.length <= ~this.length) {
            return 0;
         } else {
            return 1;
         }
      } catch (RuntimeException var5) {
         throw Class44.method1067(var5, "na.QA(" + (var1 != null?"{...}":"null") + ',' + var2 + ')');
      }
   }

   final RSString method1560(RSString var1, boolean var2, RSString var3) {
      try {
         int var4 = this.length;
         int var5 = var1.length - var3.length;
         int var6 = 0;

         while(true) {
            int var7 = this.method1566(var3, var6, -1);
            if(0 > var7) {
               var6 = 0;
               RSString var10 = Class47.method1090((byte)-104, var4);

               while(true) {
                  int var8 = this.method1566(var3, var6, -1);
                  if(0 > var8) {
                     while(~var6 > ~this.length) {
                        var10.method1572(255 & this.byteArray[var6++], (byte)117);
                     }

                     if(!var2) {
                        this.method1567(-5, (byte)-91);
                     }

                     return var10;
                  }

                  while(var6 < var8) {
                     var10.method1572(this.byteArray[var6++] & 255, (byte)125);
                  }

                  var10.method1533(var1, var2);
                  var6 += var3.length;
               }
            }

            var6 = var7 - -var3.length;
            var4 += var5;
         }
      } catch (RuntimeException var9) {
         throw Class44.method1067(var9, "na.IA(" + (var1 != null?"{...}":"null") + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ')');
      }
   }

   public final int hashCode() {
      try {
         return this.method1574(false);
      } catch (RuntimeException var2) {
         throw Class44.method1067(var2, "na.hashCode()");
      }
   }

   private final boolean method1561(int var1, boolean var2) {
      try {
         if(~var1 > -2 || var1 > 36) {
            var1 = 10;
         }

         if(!var2) {
            aBoolean2146 = false;
         }

         boolean var4 = false;
         boolean var3 = false;
         int var5 = 0;

         for(int var6 = 0; var6 < this.length; ++var6) {
            int var7 = this.byteArray[var6] & 255;
            if(0 == var6) {
               if(var7 == 45) {
                  var3 = true;
                  continue;
               }

               if(var7 == 43) {
                  continue;
               }
            }

            if(var7 >= 48 && ~var7 >= -58) {
               var7 -= 48;
            } else if(-66 >= ~var7 && -91 <= ~var7) {
               var7 -= 55;
            } else {
               if(97 > var7 || ~var7 < -123) {
                  return false;
               }

               var7 -= 87;
            }

            if(~var7 <= ~var1) {
               return false;
            }

            if(var3) {
               var7 = -var7;
            }

            int var8 = var7 + var1 * var5;
            if(~var5 != ~(var8 / var1)) {
               return false;
            }

            var5 = var8;
            var4 = true;
         }

         return var4;
      } catch (RuntimeException var9) {
         throw Class44.method1067(var9, "na.P(" + var1 + ',' + var2 + ')');
      }
   }

   final boolean method1562(byte var1, RSString var2) {
      try {
         if(this.length < var2.length) {
            return false;
         } else {
            if(var1 != -32) {
               this.length = 13;
            }

            for(int var3 = 0; var2.length > var3; ++var3) {
               byte var4 = this.byteArray[var3];
               byte var5 = var2.byteArray[var3];
               if(var5 >= 65 && var5 <= 90 || -64 <= var5 && -34 >= var5 && -41 != var5) {
                  var5 = (byte)(var5 + 32);
               }

               if(65 <= var4 && ~var4 >= -91 || var4 >= -64 && -34 >= var4 && var4 != -41) {
                  var4 = (byte)(var4 + 32);
               }

               if(~var4 != ~var5) {
                  return false;
               }
            }

            return true;
         }
      } catch (RuntimeException var6) {
         throw Class44.method1067(var6, "na.HB(" + var1 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   final RSString method1563(int var1) {
      try {
         if(var1 <= 86) {
            this.trim(117);
         }

         return this;
      } catch (RuntimeException var3) {
         throw Class44.method1067(var3, "na.K(" + var1 + ')');
      }
   }

   final RSString trim(int var1) {
      try {
         if(var1 != 1) {
            method1535((WorldListEntry)null, (WorldListEntry)null, 23, 68, 126, false, false);
         }

         int var2;
         for(var2 = 0; var2 < this.length && (0 <= this.byteArray[var2] && 32 >= this.byteArray[var2] || -161 == ~(255 & this.byteArray[var2])); ++var2) {
            ;
         }

         int var3;
         for(var3 = this.length; var3 > var2 && (~this.byteArray[var3 - 1] <= -1 && -33 <= ~this.byteArray[var3 - 1] || -161 == ~(255 & this.byteArray[var3 + -1])); --var3) {
            ;
         }

         if(~var2 == -1 && ~this.length == ~var3) {
            return this;
         } else {
            RSString var4 = new RSString();
            var4.length = var3 + -var2;
            var4.byteArray = new byte[var4.length];

            for(int var5 = 0; var5 < var4.length; ++var5) {
               var4.byteArray[var5] = this.byteArray[var2 + var5];
            }

            return var4;
         }
      } catch (RuntimeException var6) {
         throw Class44.method1067(var6, "na.KA(" + var1 + ')');
      }
   }

   final RSString method1565(int var1, int var2, int var3) {
      try {
         byte var4 = (byte)var3;
         RSString var6 = new RSString();
         byte var5 = (byte)var1;
         var6.length = this.length;
         var6.byteArray = new byte[this.length];
         if(var2 < 3) {
            aBoolean2146 = true;
         }

         for(int var7 = 0; ~this.length < ~var7; ++var7) {
            byte var8 = this.byteArray[var7];
            if(~var8 != ~var4) {
               var6.byteArray[var7] = var8;
            } else {
               var6.byteArray[var7] = var5;
            }
         }

         return var6;
      } catch (RuntimeException var9) {
         throw Class44.method1067(var9, "na.HA(" + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   final int method1566(RSString var1, int var2, int var3) {
      try {
         int var4 = var1.length;
         if(var2 >= this.length) {
            return ~var4 == -1?this.length:-1;
         } else {
            if(~var2 > -1) {
               var2 = 0;
            }

            if(var3 == ~var4) {
               return var2;
            } else {
               int var7 = this.length - var4;
               byte[] var5 = var1.byteArray;
               byte var6 = var5[0];
               int var8 = var2;

               while(~var8 >= ~var7) {
                  if(~var6 != ~this.byteArray[var8]) {
                     do {
                        ++var8;
                        if(var8 > var7) {
                           return -1;
                        }
                     } while(~var6 != ~this.byteArray[var8]);
                  }

                  boolean var9 = true;
                  int var10 = 1 + var8;
                  int var11 = 1;

                  while(true) {
                     if(var11 < var4) {
                        if(~var5[var11] == ~this.byteArray[var10]) {
                           ++var10;
                           ++var11;
                           continue;
                        }

                        var9 = false;
                     }

                     if(var9) {
                        return var8;
                     }

                     ++var8;
                     break;
                  }
               }

               return -1;
            }
         }
      } catch (RuntimeException var12) {
         throw Class44.method1067(var12, "na.CB(" + (var1 != null?"{...}":"null") + ',' + var2 + ',' + var3 + ')');
      }
   }

   final RSString[] method1567(int var1, byte var2) {
      try {
         int var3 = 0;

         for(int var4 = 0; ~this.length < ~var4; ++var4) {
            if(~var1 == ~this.byteArray[var4]) {
               ++var3;
            }
         }

         RSString[] var11 = new RSString[1 + var3];
         if(var3 == 0) {
            var11[0] = this;
            return var11;
         } else {
            int var5 = 0;
            int var6 = 0;
            int var7 = 0;

            for(int var8 = 88 / ((var2 - -12) / 33); ~var7 > ~var3; ++var7) {
               int var9;
               for(var9 = 0; ~var1 != ~this.byteArray[var9 + var6]; ++var9) {
                  ;
               }

               var11[var5++] = this.method1557(var6 - -var9, 0, var6);
               var6 += 1 + var9;
            }

            var11[var3] = this.method1557(this.length, 0, var6);
            return var11;
         }
      } catch (RuntimeException var10) {
         throw Class44.method1067(var10, "na.GA(" + var1 + ',' + var2 + ')');
      }
   }

   final byte[] method1568(int var1) {
      try {
         byte[] var2 = new byte[this.length];
         Class76.method1357(this.byteArray, 0, var2, var1, this.length);
         return var2;
      } catch (RuntimeException var3) {
         throw Class44.method1067(var3, "na.H(" + var1 + ')');
      }
   }

   final int charAt(int var1, byte var2) {
      try {
         int var3 = 53 / ((var2 - 9) / 32);
         return this.byteArray[var1] & 255;
      } catch (RuntimeException var4) {
         throw Class44.method1067(var4, "na.SA(" + var1 + ',' + var2 + ')');
      }
   }

   static final Class3_Sub28_Sub16 method1570(int var0, byte var1, boolean var2, int var3, boolean var4, int var5, int var6, boolean var7) {
      try {
         ItemDefinition var8 = Class38.getItemDefinition(var3, (byte)106);
         if(~var6 < -2 && var8.anIntArray804 != null) {
            int var9 = -1;

            for(int var10 = 0; -11 < ~var10; ++var10) {
               if(~var6 <= ~var8.anIntArray766[var10] && var8.anIntArray766[var10] != 0) {
                  var9 = var8.anIntArray804[var10];
               }
            }

            if(0 != ~var9) {
               var8 = Class38.getItemDefinition(var9, (byte)84);
            }
         }

         Class140_Sub1_Sub2 var21 = var8.method1120(18206);
         if(null == var21) {
            return null;
         } else {
            Class3_Sub28_Sub16_Sub2 var22 = null;
            if(0 == ~var8.anInt791) {
               if(var8.anInt762 != -1) {
                  var22 = (Class3_Sub28_Sub16_Sub2)method1570(var0, (byte)-107, true, var8.anInt795, false, var5, var6, false);
                  if(null == var22) {
                     return null;
                  }
               }
            } else {
               var22 = (Class3_Sub28_Sub16_Sub2)method1570(0, (byte)116, true, var8.anInt789, false, 1, 10, true);
               if(null == var22) {
                  return null;
               }
            }

            int[] var11 = Class74.anIntArray1100;
            int var12 = Class74.anInt1092;
            int var13 = Class74.anInt1094;
            int[] var14 = new int[4];
            Class74.method1325(var14);
            Class3_Sub28_Sub16_Sub2 var15 = new Class3_Sub28_Sub16_Sub2(36, 32);
            Class74.method1319(var15.anIntArray4081, 36, 32);
            Class51.method1134();
            Class51.method1145(16, 16);
            int var16 = var8.anInt810;
            Class51.aBoolean843 = false;
            if(var7) {
               var16 = (int)((double)var16 * 1.5D);
            } else if(var5 == 2) {
               var16 = (int)(1.04D * (double)var16);
            }

            int var18 = Class51.anIntArray851[var8.anInt786] * var16 >> 16;
            int var17 = Class51.anIntArray840[var8.anInt786] * var16 >> 16;
            var21.method1893(0, var8.anInt799, var8.anInt768, var8.anInt786, var8.anInt792, var17 - (var21.method1871() / 2 + -var8.anInt754), var8.anInt754 + var18, -1L);
            if(var5 >= 1) {
               var15.method657(1);
               if(-3 >= ~var5) {
                  var15.method657(16777215);
               }

               Class74.method1319(var15.anIntArray4081, 36, 32);
            }

            if(~var0 != -1) {
               var15.method668(var0);
            }

            int var19 = 73 / ((-56 - var1) / 47);
            if(0 != ~var8.anInt791) {
               var22.method643(0, 0);
            } else if(-1 != var8.anInt762) {
               Class74.method1319(var22.anIntArray4081, 36, 32);
               var15.method643(0, 0);
               var15 = var22;
            }

            if(var4 && (~var8.stackingType == -2 || var6 != 1) && var6 != -1) {
               Class3_Sub13_Sub37.aClass3_Sub28_Sub17_Sub1_3440.method681(Class3_Sub7.method123(1000, var6), 0, 9, 16776960, 1);
            }

            Class74.method1319(var11, var12, var13);
            Class74.method1316(var14);
            Class51.method1134();
            Class51.aBoolean843 = true;
            return (Class3_Sub28_Sub16)(HDToolKit.highDetail && !var2?new Class3_Sub28_Sub16_Sub1(var15):var15);
         }
      } catch (RuntimeException var20) {
         throw Class44.method1067(var20, "na.WA(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
      }
   }

   final RSString method1571(byte var1) {
      try {
         long var2 = this.method1538(var1 + 90);
         Class var4 = RSString.class;
         synchronized(var4) {
            Class3_Sub29 var5;
            if(Class86.aClass130_1194 != null) {
               for(var5 = (Class3_Sub29)Class86.aClass130_1194.method1780(var2, var1 ^ 32); null != var5; var5 = (Class3_Sub29)Class86.aClass130_1194.method1784(0)) {
                  if(this.method1528((byte)-42, var5.aClass94_2586)) {
                     return var5.aClass94_2586;
                  }
               }
            } else {
               Class86.aClass130_1194 = new Class130(4096);
            }

            var5 = new Class3_Sub29();
            if(var1 != 32) {
               return (RSString)null;
            }

            var5.aClass94_2586 = this;
            this.aBoolean2152 = false;
            Class86.aClass130_1194.method1779(1, var5, var2);
         }

         return this;
      } catch (RuntimeException var8) {
         throw Class44.method1067(var8, "na.BB(" + var1 + ')');
      }
   }

   final RSString method1572(int var1, byte var2) {
      try {
         if(var2 <= 110) {
            aBoolean2150 = true;
         }

         if(0 < var1 && var1 <= 255) {
            if(!this.aBoolean2152) {
               throw new IllegalArgumentException();
            } else {
               if(this.length == this.byteArray.length) {
                  int var3;
                  for(var3 = 1; ~var3 >= ~this.length; var3 += var3) {
                     ;
                  }

                  byte[] var4 = new byte[var3];
                  Class76.method1357(this.byteArray, 0, var4, 0, this.length);
                  this.byteArray = var4;
               }

               this.byteArray[this.length++] = (byte)var1;
               return this;
            }
         } else {
            throw new IllegalArgumentException("invalid char:" + var1);
         }
      } catch (RuntimeException var5) {
         throw Class44.method1067(var5, "na.L(" + var1 + ',' + var2 + ')');
      }
   }

   final RSString method1573(byte var1, Applet var2) {
      try {
         if(var1 < 124) {
            this.method1552((byte)-82);
         }

         String var3 = new String(this.byteArray, 0, this.length);
         String var4 = var2.getParameter(var3);
         return null == var4?null:Class3_Sub29.method732(var4, 27307);
      } catch (RuntimeException var5) {
         throw Class44.method1067(var5, "na.DB(" + var1 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   final int method1574(boolean var1) {
      try {
         int var2 = 0;
         if(var1) {
            anIntArray2157 = (int[])null;
         }

         for(int var3 = 0; var3 < this.length; ++var3) {
            var2 = (255 & this.byteArray[var3]) + -var2 + (var2 << 998234309);
         }

         return var2;
      } catch (RuntimeException var4) {
         throw Class44.method1067(var4, "na.J(" + var1 + ')');
      }
   }

   final int method1575(int var1, FontMetrics var2) {
      try {
         if(var1 != -21018) {
            return 63;
         } else {
            String var3;
            try {
               var3 = new String(this.byteArray, 0, this.length, "ISO-8859-1");
            } catch (UnsupportedEncodingException var5) {
               var3 = new String(this.byteArray, 0, this.length);
            }

            return var2.stringWidth(var3);
         }
      } catch (RuntimeException var6) {
         throw Class44.method1067(var6, "na.V(" + var1 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   final RSString method1576(byte var1) {
      try {
         if(!this.aBoolean2152) {
            throw new IllegalArgumentException();
         } else {
            if(var1 != 90) {
               incomingOpcode = -84;
            }

            if(this.byteArray.length != this.length) {
               byte[] var2 = new byte[this.length];
               Class76.method1357(this.byteArray, 0, var2, 0, this.length);
               this.byteArray = var2;
            }

            return this;
         }
      } catch (RuntimeException var3) {
         throw Class44.method1067(var3, "na.PA(" + var1 + ')');
      }
   }

   final Object method1577(int var1, Applet var2) throws Throwable {
      try {
         String var3 = new String(this.byteArray, 0, this.length);
         Object var4 = Class42.method1055(var3, (byte)-111, var2);
         if(var4 instanceof String) {
            byte[] var5 = ((String)var4).getBytes();
            var4 = Class3_Sub13_Sub3.method178(var5, -4114, var5.length, 0);
         }

         if(var1 != -1857) {
            this.method1553(116, false);
         }

         return var4;
      } catch (RuntimeException var6) {
         throw Class44.method1067(var6, "na.JA(" + var1 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   final long toLong(int var1) {
      try {
         long var2 = 0L;
         if(var1 >= -105) {
            aBoolean2154 = true;
         }
         for(int var4 = 0; ~this.length < ~var4 && -13 < ~var4; ++var4) {
            byte var5 = this.byteArray[var4];
            var2 *= 37L;
            if(65 <= var5 && 90 >= var5) {
               var2 += (long)(-65 + 1 + var5);
            } else if(~var5 <= -98 && 122 >= var5) {
               var2 += (long)(-97 + var5 + 1);
            } else if(var5 >= 48 && var5 <= 57) {
               var2 += (long)(-48 + var5 + 27);
            }
         }

         while(~(var2 % 37L) == -1L && var2 != 0L) {
            var2 /= 37L;
         }

         return var2;
      } catch (RuntimeException var6) {
         throw Class44.method1067(var6, "na.S(" + var1 + ')');
      }
   }

   final RSString method1579(int var1) {
      try {
         RSString var2 = Class41.method1052(-29664, this.toLong(-112));
         return var1 >= -4?(RSString)null:(null == var2?Class134.aClass94_1760:var2);
      } catch (RuntimeException var3) {
         throw Class44.method1067(var3, "na.Q(" + var1 + ')');
      }
   }

   final int method1580(boolean var1, byte[] var2, int var3, int var4, int var5) {
      try {
         Class76.method1357(this.byteArray, var4, var2, var3, -var4 + var5);
         if(!var1) {
            method1570(42, (byte)-117, true, -47, false, 3, -26, true);
         }

         return -var4 + var5;
      } catch (RuntimeException var7) {
         throw Class44.method1067(var7, "na.LA(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ',' + var4 + ',' + var5 + ')');
      }
   }

static final RSString createRSString(String string) {
	if (string != null) {
		string = string.replace("RuneScape", GameLaunch.SETTINGS.getName());
	}
	try {
		byte[] var2 = string.getBytes(); 
		int var3 = var2.length;
		RSString var4 = new RSString();
		int var5 = 0;
		var4.byteArray = new byte[var3];

		while(var3 > var5) {
			int var6 = var2[var5++] & 255;
			if(45 >= var6 && ~var6 <= -41) {
				if(~var5 <= ~var3) {
					break;
				}

				int var7 = 255 & var2[var5++];
				var4.byteArray[var4.length++] = (byte)(-48 + var7 + 43 * (-40 + var6));
			} else if(~var6 != -1) {
				var4.byteArray[var4.length++] = (byte)var6;
			}
		}
		var4.method1576((byte)90);
		return var4.method1571((byte)32);
	} catch (RuntimeException var8) {
		throw Class44.method1067(var8, "cd.D(" + (string != null?"{...}":"null") + ',' + -1 + ')');
	}
}

}
