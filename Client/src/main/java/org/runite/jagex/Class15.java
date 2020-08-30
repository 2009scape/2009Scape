package org.runite.jagex;
import org.runite.Configurations;

import java.util.Calendar;
import java.util.Date;

final class Class15 implements Runnable {

   static short[][] aShortArrayArray344;
    static RSString timeZone = RSString.createRSString(" GMT");
    volatile boolean aBoolean345 = false;
   static boolean aBoolean346;
   static int[] localNPCIndexes = new int['\u8000'];
   Signlink aClass87_350;
   static Class64 aClass64_351;
   volatile Class155[] aClass155Array352 = new Class155[2];
   volatile boolean aBoolean353 = false;


   static boolean method888(int var0, ObjectDefinition var1, int var3, int var4, int var5, int var6) {
      try {
         Class2 var7 = Class3_Sub28_Sub6.c(var1.anInt1516);
         if(var7.anInt64 == -1) {
            return false;
         } else {
            if(var1.aBoolean1537) {
               var6 += var1.anInt1478;
               var6 &= 3;
            } else {
               var6 = 0;
            }

            LDIndexedSprite var8 = var7.method77(var6, (byte)-111);
            if(var8 == null) {
               return true;
            } else {
               int var9 = var1.SizeX;
               int var10 = var1.SizeY;
               if(1 == (1 & var6)) {
                  var9 = var1.SizeY;
                  var10 = var1.SizeX;
               }

               int var11 = var8.anInt1469;
               int var12 = var8.anInt1467;
               if(var7.aBoolean69) {
                  var12 = 4 * var10;
                  var11 = 4 * var9;
               }

               if(var7.anInt61 == 0) {
                  var8.method1677(var0 * 4 + 48, 48 + 4 * (-var10 + -var5 + 104), var11, var12);
               } else {
                  var8.method1669(48 + 4 * var0, 4 * (-var10 + -var5 + 104) + 48, var11, var12, var7.anInt61);
               }

               return false;
            }
         }
      } catch (RuntimeException var13) {
         throw Class44.clientError(var13, "cj.D(" + var0 + ',' + (var1 != null?"{...}":"null") + ',' + false + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ')');
      }
   }

   static void method889(byte var0, RSByteBuffer var1) {
      try {
         if(var0 != 105) {
            localNPCIndexes = (int[])null;
         }

         int var3 = Class158.anInt2010 >> 1;
         int var2 = Class3_Sub13_Sub23_Sub1.anInt4034 >> 2 << 10;
         byte[][] var4 = new byte[Class23.anInt455][Class108.anInt1460];

         int var6;
         int var12;
         int var14;
         while(var1.index < var1.buffer.length) {
            int var7 = 0;
            var6 = 0;
            boolean var5 = false;
            if(var1.getByteB() == 1) {
               var6 = var1.getByteB();
               var7 = var1.getByteB();
               var5 = true;
            }

            int var8 = var1.getByteB();
            int var9 = var1.getByteB();
            int var10 = -Class3_Sub13_Sub21.anInt3256 + var8 * 64;
            int var11 = -1 + Class108.anInt1460 - var9 * 64 + Class2.anInt65;
            if(var10 >= 0 && 0 <= -63 + var11 && Class23.anInt455 > var10 - -63 && var11 < Class108.anInt1460) {
               for(var12 = 0; var12 < 64; ++var12) {
                  byte[] var13 = var4[var10 - -var12];

                  for(var14 = 0; 64 > var14; ++var14) {
                     if(!var5 || var12 >= 8 * var6 && 8 + 8 * var6 > var12 && var14 >= var7 * 8 && var14 < 8 + 8 * var7) {
                        var13[var11 - var14] = var1.getByte();
                     }
                  }
               }
            } else if(var5) {
               var1.index += 64;
            } else {
               var1.index += 4096;
            }
         }

         int var27 = Class23.anInt455;
         var6 = Class108.anInt1460;
         int[] var29 = new int[var6];
         int[] var28 = new int[var6];
         int[] var30 = new int[var6];
         int[] var32 = new int[var6];
         int[] var31 = new int[var6];

         for(var12 = -5; var27 > var12; ++var12) {
            int var15;
            int var35;
            for(int var34 = 0; var6 > var34; ++var34) {
               var14 = var12 + 5;
               if(var27 > var14) {
                  var15 = 255 & var4[var14][var34];
                  if(var15 > 0) {
                     Class100 var16 = Class158_Sub1.method629(var15 - 1);
                     var28[var34] += var16.anInt1408;
                     var29[var34] += var16.anInt1406;
                     var30[var34] += var16.anInt1417;
                     var32[var34] += var16.anInt1418;
                     ++var31[var34];
                  }
               }

               var15 = var12 + -5;
               if(var15 >= 0) {
                  var35 = var4[var15][var34] & 255;
                  if(0 < var35) {
                     Class100 var17 = Class158_Sub1.method629(-1 + var35);
                     var28[var34] -= var17.anInt1408;
                     var29[var34] -= var17.anInt1406;
                     var30[var34] -= var17.anInt1417;
                     var32[var34] -= var17.anInt1418;
                     --var31[var34];
                  }
               }
            }

            if(var12 >= 0) {
               int[][] var33 = Class146.anIntArrayArrayArray1903[var12 >> 6];
               var14 = 0;
               var15 = 0;
               int var36 = 0;
               int var18 = 0;
               var35 = 0;

               for(int var19 = -5; var6 > var19; ++var19) {
                  int var20 = var19 - -5;
                  if(var6 > var20) {
                     var18 += var31[var20];
                     var15 += var29[var20];
                     var35 += var30[var20];
                     var14 += var28[var20];
                     var36 += var32[var20];
                  }

                  int var21 = -5 + var19;
                  if(var21 >= 0) {
                     var35 -= var30[var21];
                     var36 -= var32[var21];
                     var14 -= var28[var21];
                     var18 -= var31[var21];
                     var15 -= var29[var21];
                  }

                  if(var19 >= 0 && 0 < var18) {
                     int[] var22 = var33[var19 >> 6];
                     int var23 = var36 != 0?Class3_Sub8.method129(var35 / var18, var15 / var18, var14 * 256 / var36):0;
                     if(var4[var12][var19] == 0) {
                        if(var22 != null) {
                           var22[Class69.bitwiseAnd(4032, var19 << 6) - -Class69.bitwiseAnd(var12, 63)] = 0;
                        }
                     } else {
                        if(var22 == null) {
                           var22 = var33[var19 >> 6] = new int[4096];
                        }

                        int var24 = var3 + (var23 & 127);
                        if(var24 < 0) {
                           var24 = 0;
                        } else if(var24 > 127) {
                           var24 = 127;
                        }

                        int var25 = var24 + (896 & var23) + (var23 + var2 & '\ufc00');
                        var22[Class69.bitwiseAnd(4032, var19 << 6) + Class69.bitwiseAnd(63, var12)] = Class51.anIntArray834[Class47.method1100(96, var25)];
                     }
                  }
               }
            }
         }

      } catch (RuntimeException var26) {
         throw Class44.clientError(var26, "cj.H(" + var0 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   static Object method890(int var1, byte[] var2) {
      try {
         if(var2 == null) {
            return null;
         } else {
            if(var1 >= -67) {
               method891(19);
            }

            if(var2.length > 136 && !Class45.aBoolean732) {
               try {
                  Class144 var3 = (Class144)Class.forName(Configurations.PACKAGE_JAGEX + ".Class144_Sub1").newInstance();
                  var3.method2066(400, var2);
                  return var3;
               } catch (Throwable var4) {
                  Class45.aBoolean732 = true;
               }
            }

            return var2;
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "cj.E(" + ',' + var1 + ',' + "{...}" + ')');
      }
   }

   public final void run() {
      try {
         this.aBoolean353 = true;

         try {
            while(!this.aBoolean345) {
               for(int var1 = 0; var1 < 2; ++var1) {
                  Class155 var2 = this.aClass155Array352[var1];
                  if(var2 != null) {
                     var2.method2153();
                  }
               }

               Class3_Sub13_Sub34.method331(10L, 64);
               Class81.method1400(this.aClass87_350, (Object)null, -71);
            }
         } catch (Exception var7) {
            Class49.method1125((String)null, var7, (byte)111);
         } finally {
            this.aBoolean353 = false;
         }

      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "cj.run()");
      }
   }

   static Class3_Sub28_Sub16[] method891(int var0) {
      try {
         Class3_Sub28_Sub16[] var1 = new Class3_Sub28_Sub16[Class95.anInt1338];
         if(var0 != -5) {
            method894(113L, (byte)48);
         }

         for(int var2 = 0; var2 < Class95.anInt1338; ++var2) {
            int var3 = Class140_Sub7.anIntArray2931[var2] * Class3_Sub13_Sub6.anIntArray3076[var2];
            byte[] var4 = Class163_Sub1.aByteArrayArray2987[var2];
            int[] var5 = new int[var3];

            for(int var6 = 0; var6 < var3; ++var6) {
               var5[var6] = Class3_Sub13_Sub38.spritePalette[Class69.bitwiseAnd(255, var4[var6])];
            }

            if(HDToolKit.highDetail) {
               var1[var2] = new Class3_Sub28_Sub16_Sub1(Class3_Sub15.anInt2426, Class133.anInt1748, Class164.anIntArray2048[var2], RSByteBuffer.anIntArray2591[var2], Class140_Sub7.anIntArray2931[var2], Class3_Sub13_Sub6.anIntArray3076[var2], var5);
            } else {
               var1[var2] = new Class3_Sub28_Sub16_Sub2(Class3_Sub15.anInt2426, Class133.anInt1748, Class164.anIntArray2048[var2], RSByteBuffer.anIntArray2591[var2], Class140_Sub7.anIntArray2931[var2], Class3_Sub13_Sub6.anIntArray3076[var2], var5);
            }
         }

         Class39.method1035((byte)116);
         return var1;
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "cj.C(" + var0 + ')');
      }
   }

   public static void method892(int var0) {
      try {
         aShortArrayArray344 = (short[][])null;
         aClass64_351 = null;
         if(var0 <= 75) {
            aBoolean346 = false;
         }

         localNPCIndexes = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "cj.G(" + var0 + ')');
      }
   }

   static RSString method894(long var0, byte var2) {
      try {
         Class3_Sub28_Sub5.aCalendar3581.setTime(new Date(var0));
         int var3 = Class3_Sub28_Sub5.aCalendar3581.get(Calendar.DAY_OF_WEEK);//Day of the week
         int var4 = Class3_Sub28_Sub5.aCalendar3581.get(Calendar.DATE);
         int var5 = Class3_Sub28_Sub5.aCalendar3581.get(Calendar.MONTH);
         if(var2 < 9) {
            method889((byte)7, (RSByteBuffer)null);
         }

         int var6 = Class3_Sub28_Sub5.aCalendar3581.get(Calendar.YEAR);
         int var7 = Class3_Sub28_Sub5.aCalendar3581.get(Calendar.HOUR_OF_DAY);
         int var8 = Class3_Sub28_Sub5.aCalendar3581.get(Calendar.MINUTE);
         int var9 = Class3_Sub28_Sub5.aCalendar3581.get(Calendar.SECOND);
         return RenderAnimationDefinition.method903(new RSString[]{TextCore.DaysOfTheWeek[var3 + -1], Class3_Sub13_Sub12.aClass94_3145, Class72.method1298((byte)9, var4 / 10), Class72.method1298((byte)9, var4 % 10), Class161.aClass94_2025, TextCore.MonthsOfTheYear[var5], Class161.aClass94_2025, Class72.method1298((byte)9, var6), Class24.aClass94_465, Class72.method1298((byte)9, var7 / 10), Class72.method1298((byte)9, var7 % 10), Class155.char_colon, Class72.method1298((byte)9, var8 / 10), Class72.method1298((byte)9, var8 % 10), Class155.char_colon, Class72.method1298((byte)9, var9 / 10), Class72.method1298((byte)9, var9 % 10), timeZone}, (byte)-96);
      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "cj.F(" + var0 + ',' + var2 + ')');
      }
   }

   static int[][] method895(int var4, byte var8) {
      try {
         if(var8 >= -52) {
            method891(115);
         }

         int[][] var9 = new int[256][64];
         Class3_Sub13_Sub4 var10 = new Class3_Sub13_Sub4();
         var10.anInt3062 = (int)((float) 0.4 * 4096.0F);
         var10.anInt3058 = 3;
         var10.anInt3056 = 4;
         var10.aBoolean3065 = false;
         var10.anInt3060 = 8;
         var10.method158(16251);
         Class3_Sub13_Sub3.method180(122, 256, 64);

         for(int var11 = 0; var11 < 256; ++var11) {
            var10.method186(var11, var9[var11]);
         }

         return var9;
      } catch (RuntimeException var12) {
         throw Class44.clientError(var12, "cj.B(" + false + ',' + 3 + ',' + 64 + ',' + 256 + ',' + var4 + ',' + 4 + ',' + 8 + ',' + (float) 0.4 + ',' + var8 + ')');
      }
   }

}
