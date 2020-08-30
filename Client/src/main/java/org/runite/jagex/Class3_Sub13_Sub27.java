package org.runite.jagex;

import java.util.Objects;

final class Class3_Sub13_Sub27 extends Class3_Sub13 {

   static long isaacServerKey = 0L;
   static RSString aClass94_3339 = RSString.createRSString("null");
   static int anInt3340;
   static RSString[] aClass94Array3341 = new RSString[100];
   static int anInt3342;
   private int anInt3343 = 6;


   static void method295(RSString var0, int var2) {
      try {
         Class3_Sub28_Sub6 var3 = Class3_Sub24_Sub3.method466(2, var2);

         var3.g();
         var3.aClass94_3599 = var0;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "pi.B(" + (var0 != null?"{...}":"null") + ',' + (byte) 40 + ',' + var2 + ')');
      }
   }

   final int[] method154(int var1, byte var2) {
      try {
         int[] var3 = this.aClass114_2382.method1709(var1);
         int var4;
         if(this.aClass114_2382.aBoolean1580) {
            int[] var6 = this.method152(0, var1, 32755);
            int[] var7 = this.method152(1, var1, 32755);
            int var8 = this.anInt3343;
            if(var8 == 1) {
               for(var8 = 0; var8 < Class113.anInt1559; ++var8) {
                  var3[var8] = var7[var8] + var6[var8];
               }
            } else if(var8 == 2) {
               for(var8 = 0; var8 < Class113.anInt1559; ++var8) {
                  var3[var8] = -var7[var8] + var6[var8];
               }
            } else if (var8 == 3) {
               for (var8 = 0; Class113.anInt1559 > var8; ++var8) {
                  var3[var8] = var7[var8] * var6[var8] >> 12;
               }
            } else {
               int var5;
               if (var8 == 4) {
                  for (var8 = 0; Class113.anInt1559 > var8; ++var8) {
                     var5 = var7[var8];
                     var3[var8] = var5 != 0 ? (var6[var8] << 12) / var5 : 4096;
                  }
               } else if (var8 == 5) {
                  for (var8 = 0; var8 < Class113.anInt1559; ++var8) {
                     var3[var8] = 4096 - ((-var6[var8] + 4096) * (-var7[var8] + 4096) >> 12);
                  }
               } else if (var8 == 6) {
                  for (var8 = 0; var8 < Class113.anInt1559; ++var8) {
                     var5 = var7[var8];
                     var3[var8] = var5 >= 2048 ? -((-var6[var8] + 4096) * (4096 + -var5) >> 11) + 4096 : var5 * var6[var8] >> 11;
                  }
               } else if (var8 == 7) {
                  for (var8 = 0; Class113.anInt1559 > var8; ++var8) {
                     var4 = var6[var8];
                     var3[var8] = var4 == 4096 ? 4096 : (var7[var8] << 12) / (4096 - var4);
                  }
               } else if (var8 == 8) {
                  for (var8 = 0; var8 < Class113.anInt1559; ++var8) {
                     var4 = var6[var8];
                     var3[var8] = var4 != 0 ? 4096 - (-var7[var8] + 4096 << 12) / var4 : 0;
                  }
               } else if (var8 == 9) {
                  for (var8 = 0; Class113.anInt1559 > var8; ++var8) {
                     var5 = var7[var8];
                     var4 = var6[var8];
                     var3[var8] = var5 > var4 ? var4 : var5;
                  }
               } else if (10 == var8) {
                  for (var8 = 0; var8 < Class113.anInt1559; ++var8) {
                     var5 = var7[var8];
                     var4 = var6[var8];
                     var3[var8] = var5 < var4 ? var4 : var5;
                  }
               } else if (var8 == 11) {
                  for (var8 = 0; var8 < Class113.anInt1559; ++var8) {
                     var4 = var6[var8];
                     var5 = var7[var8];
                     var3[var8] = var4 > var5 ? var4 + -var5 : var5 - var4;
                  }
               } else if (var8 == 12) {
                  for (var8 = 0; Class113.anInt1559 > var8; ++var8) {
                     var4 = var6[var8];
                     var5 = var7[var8];
                     var3[var8] = var5 + (var4 - (var4 * var5 >> 11));
                  }
               }
            }
         }

         return var3;
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "pi.D(" + var1 + ',' + var2 + ')');
      }
   }

   public static void method296(byte var0) {
      try {
         aClass94Array3341 = null;
         if(var0 >= -56) {
            anInt3340 = 30;
         }

         aClass94_3339 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "pi.F(" + var0 + ')');
      }
   }

   final int[][] method166(int var1, int var2) {
      try {
         if(var1 != -1) {
            this.method157(-8, (RSByteBuffer)null, false);
         }

         int[][] var3 = this.aClass97_2376.method1594((byte)-125, var2);
         if(this.aClass97_2376.aBoolean1379) {
            int[][] var10 = this.method162(var2, 0, (byte)-87);
            int[][] var11 = this.method162(var2, 1, (byte)-73);
            int[] var12 = var3[0];
            int[] var13 = var3[1];
            int[] var14 = var3[2];
            int[] var15 = Objects.requireNonNull(var10)[0];
            int[] var16 = var10[1];
            int[] var17 = var10[2];
            int[] var18 = Objects.requireNonNull(var11)[0];
            int[] var19 = var11[1];
            int[] var20 = var11[2];
            int var21 = this.anInt3343;
            if(var21 == 1) {
               for(var21 = 0; var21 < Class113.anInt1559; ++var21) {
                  var12[var21] = var18[var21] + var15[var21];
                  var13[var21] = var19[var21] + var16[var21];
                  var14[var21] = var17[var21] - -var20[var21];
               }
            } else if(var21 == 2) {
               for(var21 = 0; Class113.anInt1559 > var21; ++var21) {
                  var12[var21] = var15[var21] - var18[var21];
                  var13[var21] = -var19[var21] + var16[var21];
                  var14[var21] = -var20[var21] + var17[var21];
               }
            } else if(3 == var21) {
               for(var21 = 0; Class113.anInt1559 > var21; ++var21) {
                  var12[var21] = var18[var21] * var15[var21] >> 12;
                  var13[var21] = var16[var21] * var19[var21] >> 12;
                  var14[var21] = var20[var21] * var17[var21] >> 12;
               }
            } else {
               int var7;
               int var8;
               int var9;
               if(var21 == 4) {
                  for(var21 = 0; Class113.anInt1559 > var21; ++var21) {
                     var9 = var20[var21];
                     var8 = var19[var21];
                     var7 = var18[var21];
                     var12[var21] = var7 == 0 ?4096:(var15[var21] << 12) / var7;
                     var13[var21] = var8 != 0 ?(var16[var21] << 12) / var8:4096;
                     var14[var21] = var9 != 0?(var17[var21] << 12) / var9:4096;
                  }
               } else if(var21 == 5) {
                  for(var21 = 0; Class113.anInt1559 > var21; ++var21) {
                     var12[var21] = 4096 + -((4096 - var18[var21]) * (4096 - var15[var21]) >> 12);
                     var13[var21] = 4096 - ((-var19[var21] + 4096) * (-var16[var21] + 4096) >> 12);
                     var14[var21] = 4096 + -((-var20[var21] + 4096) * (4096 + -var17[var21]) >> 12);
                  }
               } else if(6 == var21) {
                  for(var21 = 0; var21 < Class113.anInt1559; ++var21) {
                     var9 = var20[var21];
                     var7 = var18[var21];
                     var8 = var19[var21];
                     var12[var21] = var7 >= 2048 ?-((-var7 + 4096) * (-var15[var21] + 4096) >> 11) + 4096:var7 * var15[var21] >> 11;
                     var13[var21] = 2048 > var8?var8 * var16[var21] >> 11:4096 - ((4096 + -var8) * (-var16[var21] + 4096) >> 11);
                     var14[var21] = 2048 <= var9?4096 + -((4096 + -var9) * (-var17[var21] + 4096) >> 11):var17[var21] * var9 >> 11;
                  }
               } else {
                  int var4;
                  int var5;
                  int var6;
                  if(var21 == 7) {
                     for(var21 = 0; Class113.anInt1559 > var21; ++var21) {
                        var6 = var17[var21];
                        var4 = var15[var21];
                        var5 = var16[var21];
                        var12[var21] = var4 == 4096 ?4096:(var18[var21] << 12) / (-var4 + 4096);
                        var13[var21] = var5 == 4096 ?4096:(var19[var21] << 12) / (4096 + -var5);
                        var14[var21] = var6 == 4096 ?4096:(var20[var21] << 12) / (4096 - var6);
                     }
                  } else if(var21 == 8) {
                     for(var21 = 0; Class113.anInt1559 > var21; ++var21) {
                        var4 = var15[var21];
                        var5 = var16[var21];
                        var6 = var17[var21];
                        var12[var21] = var4 == 0?0:-((-var18[var21] + 4096 << 12) / var4) + 4096;
                        var13[var21] = var5 == 0 ?0:-((-var19[var21] + 4096 << 12) / var5) + 4096;
                        var14[var21] = 0 == var6?0:4096 - (4096 - var20[var21] << 12) / var6;
                     }
                  } else if(var21 == 9) {
                     for(var21 = 0; Class113.anInt1559 > var21; ++var21) {
                        var6 = var17[var21];
                        var9 = var20[var21];
                        var8 = var19[var21];
                        var5 = var16[var21];
                        var7 = var18[var21];
                        var4 = var15[var21];
                        var12[var21] = var7 <= var4 ?var7:var4;
                        var13[var21] = var5 >= var8?var8:var5;
                        var14[var21] = var6 < var9?var6:var9;
                     }
                  } else if (10 == var21) {
                     for (var21 = 0; var21 < Class113.anInt1559; ++var21) {
                        var9 = var20[var21];
                        var6 = var17[var21];
                        var8 = var19[var21];
                        var4 = var15[var21];
                        var5 = var16[var21];
                        var7 = var18[var21];
                        var12[var21] = var7 < var4 ? var4 : var7;
                        var13[var21] = var5 > var8 ? var5 : var8;
                        var14[var21] = var6 > var9 ? var6 : var9;
                     }
                  } else if (var21 == 11) {
                     for (var21 = 0; Class113.anInt1559 > var21; ++var21) {
                        var8 = var19[var21];
                        var7 = var18[var21];
                        var5 = var16[var21];
                        var4 = var15[var21];
                        var6 = var17[var21];
                        var9 = var20[var21];
                        var12[var21] = var7 < var4 ? var4 + -var7 : -var4 + var7;
                        var13[var21] = var5 > var8 ? -var8 + var5 : -var5 + var8;
                        var14[var21] = var9 < var6 ? var6 + -var9 : -var6 + var9;
                     }
                  } else if (var21 == 12) {
                     for (var21 = 0; Class113.anInt1559 > var21; ++var21) {
                        var4 = var15[var21];
                        var9 = var20[var21];
                        var7 = var18[var21];
                        var8 = var19[var21];
                        var6 = var17[var21];
                        var5 = var16[var21];
                        var12[var21] = -(var7 * var4 >> 11) + var7 + var4;
                        var13[var21] = var8 + var5 + -(var5 * var8 >> 11);
                        var14[var21] = var9 + (var6 - (var6 * var9 >> 11));
                     }
                  }
               }
            }
         }

         return var3;
      } catch (RuntimeException var22) {
         throw Class44.clientError(var22, "pi.T(" + var1 + ',' + var2 + ')');
      }
   }

   static void method297(long var0, int var2) {
      try {
         if(var0 != 0L) {
            for(int var3 = 0; Class8.anInt104 > var3; ++var3) {
               if(var0 == Class50.aLongArray826[var3]) {
                  --Class8.anInt104;

                  for(int var4 = var3; var4 < Class8.anInt104; ++var4) {
                     Class70.aClass94Array1046[var4] = Class70.aClass94Array1046[var4 - -1];
                     Class55.anIntArray882[var4] = Class55.anIntArray882[var4 + 1];
                     Node.aClass94Array2566[var4] = Node.aClass94Array2566[1 + var4];
                     Class50.aLongArray826[var4] = Class50.aLongArray826[1 + var4];
                     Class57.anIntArray904[var4] = Class57.anIntArray904[var4 - -1];
                     aBooleanArray73[var4] = aBooleanArray73[1 + var4];
                  }

                  Class110.anInt1472 = Class3_Sub13_Sub17.anInt3213;
                  Class3_Sub13_Sub1.outgoingBuffer.putOpcode(57);
                  Class3_Sub13_Sub1.outgoingBuffer.putLong(var0, -2037491440);
                  break;
               }
            }

            if(var2 != 1) {
               aClass94Array3341 = (RSString[])null;
            }

         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "pi.C(" + var0 + ',' + var2 + ')');
      }
   }

   static Class3_Sub11[] method298(byte[][][] var0, byte[][] var1, byte[][] var2, int[][] var3, float[][] var4, int[][] var5, byte[][] var6, byte[][] var7, float[][] var8, int var9, float[][] var10, int[][] var11, int[][] var12, int[][] var13) {
      try {
         int[][] var15 = new int[105][105];

         int var17;
         for(int var16 = 1; var16 <= 103; ++var16) {
            for(var17 = 1; var17 <= 103; ++var17) {
               byte var18 = var2[var16][var17];
               if(var18 == 0) {
                  var18 = var2[var16 + -1][var17];
               }

               if(var18 == 0) {
                  var18 = var2[var16][-1 + var17];
               }

               if(var18 == 0) {
                  var18 = var2[-1 + var16][var17 - 1];
               }

               if(0 != var18) {
                  Class100 var19 = Class158_Sub1.method629(-1 + (255 & var18));
                  var15[var16][var17] = (1 + var19.anInt1412 << 16) - -var19.anInt1414;
               }
            }
         }

         Class130 var52 = new Class130(128);

         int var21;
         int var20;
         int var22;
         int var56;
         for(var17 = 1; var17 <= 102; ++var17) {
            for(var56 = 1; 102 >= var56; ++var56) {
               if(0 != var2[var17][var56]) {
                  int[] var53;
                  if(0 == var6[var17][var56]) {
                     var53 = Class134.anIntArrayArray1763[0];
                  } else {
                     var53 = Class25.anIntArrayArray499[var1[var17][var56]];
                     if(var53.length == 0) {
                        continue;
                     }
                  }

                  var20 = 0;
                  var21 = var15[var17][var56];
                  var22 = var15[var17 - -1][var56];
                  if(null != var5) {
                     var20 = var5[var17][var56] & 16777215;
                  }

                  long var27 = (long)var20 | (long)var22 << 32;
                  int var24 = var15[var17][var56 + 1];
                  int var23 = var15[var17 + 1][var56 - -1];
                  long var31 = (long)var24 << 32 | (long)var20;
                  int var33 = var53.length / 2;
                  long var25 = (long)var20 | (long)var21 << 32;
                  Class3_Sub11 var34 = (Class3_Sub11)var52.method1780(var25, 0);
                  if(null == var34) {
                     var34 = new Class3_Sub11(-1 + (var21 >> 16), (float)('\uffff' & var21), false, null != var13, var20);
                     var52.method1779(var34, var25);
                  }

                  ++var34.anInt2344;
                  var34.anInt2342 += var33;
                  if(var25 != var27) {
                     var34 = (Class3_Sub11)var52.method1780(var27, 0);
                     if(var34 == null) {
                        var34 = new Class3_Sub11((var22 >> 16) - 1, (float)('\uffff' & var22), false, null != var13, var20);
                        var52.method1779(var34, var27);
                     }

                     ++var34.anInt2344;
                     var34.anInt2342 += var33;
                  }

                  long var29 = (long)var23 << 32 | (long)var20;
                  if(var25 != var29 && var27 != var29) {
                     var34 = (Class3_Sub11)var52.method1780(var29, 0);
                     if(var34 == null) {
                        var34 = new Class3_Sub11((var23 >> 16) + -1, (float)('\uffff' & var23), false, null != var13, var20);
                        var52.method1779(var34, var29);
                     }

                     var34.anInt2342 += var33;
                     ++var34.anInt2344;
                  }

                  if(var25 != var31 && var31 != var27 && var31 != var29) {
                     var34 = (Class3_Sub11)var52.method1780(var31, 0);
                     if(null == var34) {
                        var34 = new Class3_Sub11((var24 >> 16) - 1, (float)(var24 & '\uffff'), false, null != var13, var20);
                        var52.method1779(var34, var31);
                     }

                     ++var34.anInt2344;
                     var34.anInt2342 += var33;
                  }
               }
            }
         }

         if(4096 != 4096) {
            method297(-10L, 48);
         }

         Class3_Sub11 var54;
         for(var54 = (Class3_Sub11)var52.method1776(92); var54 != null; var54 = (Class3_Sub11)var52.method1778(-102)) {
            var54.method145();
         }

         for(var17 = 1; var17 <= 102; ++var17) {
            for(var56 = 1; var56 <= 102; ++var56) {
               byte var57 = var2[var17][var56];
               if(0 != var57) {
                  if((8 & var0[var9][var17][var56]) == 0) {
                     if((2 & var0[1][var17][var56]) == 2 && 0 < var9) {
                        var20 = var9 - 1;
                     } else {
                        var20 = var9;
                     }
                  } else {
                     var20 = 0;
                  }

                  var21 = 0;
                  boolean[] var60 = null;
                  var22 = 128;
                  if(null != var5) {
                     var22 = var5[var17][var56] >>> 24 << 3;
                     var21 = 16777215 & var5[var17][var56];
                  }

                  int[] var58;
                  int var63;
                  int var62;
                  byte var61;
                  int var69;
                  int var64;
                  if(var6[var17][var56] == 0) {
                     byte var26 = 0;
                     var64 = var26 + (var2[var17 - 1][-1 + var56] != var57 ?-1:1);
                     byte var65 = 0;
                     var58 = Class134.anIntArrayArray1763[0];
                     var62 = var65 + (var57 == var2[1 + var17][var56 + -1]?1:-1);
                     if(var2[var17][var56 + -1] == var57) {
                        ++var62;
                        ++var64;
                     } else {
                        --var64;
                        --var62;
                     }

                     byte var28 = 0;
                     var63 = var28 + (var57 == var2[1 + var17][1 + var56]?1:-1);
                     byte var68 = 0;
                     if(var2[1 + var17][var56] == var57) {
                        ++var63;
                        ++var62;
                     } else {
                        --var62;
                        --var63;
                     }

                     var69 = var68 + (var2[var17 - 1][1 + var56] == var57 ?1:-1);
                     if(var2[var17][1 + var56] == var57) {
                        ++var69;
                        ++var63;
                     } else {
                        --var63;
                        --var69;
                     }

                     if(var2[-1 + var17][var56] == var57) {
                        ++var69;
                        ++var64;
                     } else {
                        --var69;
                        --var64;
                     }

                     int var30 = var64 + -var63;
                     int var66 = -var69 + var62;
                     if(var66 < 0) {
                        var66 = -var66;
                     }

                     if(var30 < 0) {
                        var30 = -var30;
                     }

                     var61 = (byte)(var30 >= var66 ?0:1);
                     var7[var17][var56] = var61;
                  } else {
                     var58 = Class25.anIntArrayArray499[var1[var17][var56]];
                     var60 = Class3_Sub13_Sub10.aBooleanArrayArray3118[var1[var17][var56]];
                     var61 = var7[var17][var56];
                     if(var58.length == 0) {
                        continue;
                     }
                  }

                  var64 = var15[var17][var56];
                  var62 = var15[var17 - -1][var56];
                  var63 = var15[var17 - -1][var56 - -1];
                  long var67 = (long)var64 << 32 | (long)var21;
                  long var32 = (long)var62 << 32 | (long)var21;
                  long var70 = (long)var63 << 32 | (long)var21;
                  int var38 = var11[var17][var56];
                  var69 = var15[var17][var56 - -1];
                  int var40 = var11[var17 - -1][var56 - -1];
                  int var39 = var11[1 + var17][var56];
                  long var36 = (long)var21 | (long)var69 << 32;
                  int var41 = var11[var17][var56 + 1];
                  int var42 = var3[var17][var56];
                  int var43 = var3[var17 + 1][var56];
                  int var44 = var3[var17 + 1][var56 - -1];
                  int var45 = var3[var17][1 + var56];
                  int var47 = -1 + (var62 >> 16);
                  int var46 = (var64 >> 16) - 1;
                  int var48 = (var63 >> 16) - 1;
                  Class3_Sub11 var50 = (Class3_Sub11)var52.method1780(var67, 0);
                  Class25.method955(var13, var64 <= var64, Class3_Sub13_Sub4.method190(var46, var38, (byte)-92, var42), var50, var58, var56, var20, var17, var64 <= var63, var8, var69 >= var64, 2, var4, var22, Class3_Sub13_Sub4.method190(var46, var41, (byte)-80, var45), Class3_Sub13_Sub4.method190(var46, var40, (byte)-103, var44), var62 >= var64, var12, var10, var61, Class3_Sub13_Sub4.method190(var46, var39, (byte)-118, var43), var60);
                  int var49 = (var69 >> 16) - 1;
                  if(var32 != var67) {
                     var50 = (Class3_Sub11)var52.method1780(var32, 0);
                     Class25.method955(var13, var62 <= var64, Class3_Sub13_Sub4.method190(var47, var38, (byte)88, var42), var50, var58, var56, var20, var17, var63 >= var62, var8, var62 <= var69, 2, var4, var22, Class3_Sub13_Sub4.method190(var47, var41, (byte)-82, var45), Class3_Sub13_Sub4.method190(var47, var40, (byte)-113, var44), var62 <= var62, var12, var10, var61, Class3_Sub13_Sub4.method190(var47, var39, (byte)113, var43), var60);
                  }

                  if(var70 != var67 && var32 != var70) {
                     var50 = (Class3_Sub11)var52.method1780(var70, 0);
                     Class25.method955(var13, var63 <= var64, Class3_Sub13_Sub4.method190(var48, var38, (byte)59, var42), var50, var58, var56, var20, var17, var63 <= var63, var8, var63 <= var69, 4096 ^ 4098, var4, var22, Class3_Sub13_Sub4.method190(var48, var41, (byte)54, var45), Class3_Sub13_Sub4.method190(var48, var40, (byte)-87, var44), var63 <= var62, var12, var10, var61, Class3_Sub13_Sub4.method190(var48, var39, (byte)-77, var43), var60);
                  }

                  if(var67 != var36 && var32 != var36 && var36 != var70) {
                     var50 = (Class3_Sub11)var52.method1780(var36, 0);
                     Class25.method955(var13, var64 >= var69, Class3_Sub13_Sub4.method190(var49, var38, (byte)-118, var42), var50, var58, var56, var20, var17, var69 <= var63, var8, var69 <= var69, 4096 ^ 4098, var4, var22, Class3_Sub13_Sub4.method190(var49, var41, (byte)-96, var45), Class3_Sub13_Sub4.method190(var49, var40, (byte)115, var44), var69 <= var62, var12, var10, var61, Class3_Sub13_Sub4.method190(var49, var39, (byte)58, var43), var60);
                  }
               }
            }
         }

         for(var54 = (Class3_Sub11)var52.method1776(54); var54 != null; var54 = (Class3_Sub11)var52.method1778(-62)) {
            if(var54.anInt2343 == 0) {
               var54.method86(-1024);
            } else {
               var54.method148();
            }
         }

         var17 = var52.method1781(83);
         Class3_Sub11[] var59 = new Class3_Sub11[var17];
         var52.method1782(var59, 4096 ^ 4182);
         long[] var55 = new long[var17];

         for(var20 = 0; var17 > var20; ++var20) {
            var55[var20] = var59[var20].aLong71;
         }

         PacketParser.method824(var55, var59, -86);
         return var59;
      } catch (RuntimeException var51) {
         throw Class44.clientError(var51, "pi.E(" + (var0 != null?"{...}":"null") + ',' + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ',' + (var3 != null?"{...}":"null") + ',' + (var4 != null?"{...}":"null") + ',' + (var5 != null?"{...}":"null") + ',' + (var6 != null?"{...}":"null") + ',' + (var7 != null?"{...}":"null") + ',' + (var8 != null?"{...}":"null") + ',' + var9 + ',' + (var10 != null?"{...}":"null") + ',' + (var11 != null?"{...}":"null") + ',' + (var12 != null?"{...}":"null") + ',' + (var13 != null?"{...}":"null") + ',' + 4096 + ')');
      }
   }

   public Class3_Sub13_Sub27() {
      super(2, false);
   }

   static void method299(int var0, int var1, int var2) {
      try {
         if(var0 <= 92) {
            method297(-6L, -85);
         }

         int var3 = var1;
         if(var1 > 25) {
            var3 = 25;
         }

         --var1;
         int var4 = Class3_Sub13_Sub38.anIntArray3456[var1];
         int var5 = Class45.anIntArray729[var1];
         if(0 == var2) {
            Class3_Sub13_Sub1.outgoingBuffer.putOpcode(215);
            Class3_Sub13_Sub1.outgoingBuffer.putByte((byte)-25, var3 - -var3 + 3);
         }

         if(var2 == 1) {
            Class3_Sub13_Sub1.outgoingBuffer.putOpcode(39);
            Class3_Sub13_Sub1.outgoingBuffer.putByte((byte)-111, 3 + var3 - (-var3 - 14));
         }

         if(var2 == 2) {
            Class3_Sub13_Sub1.outgoingBuffer.putOpcode(77);
            Class3_Sub13_Sub1.outgoingBuffer.putByte((byte)-70, var3 + var3 - -3);
         }

         Class3_Sub13_Sub1.outgoingBuffer.putByteA(ObjectDefinition.aBooleanArray1490[82]?1:0);
         Class3_Sub13_Sub1.outgoingBuffer.putShort(Class131.anInt1716 + var4);
         Class3_Sub13_Sub1.outgoingBuffer.putShortA(Class82.anInt1152 + var5);
         Class45.anInt733 = Class45.anIntArray729[0];
         Class65.anInt987 = Class3_Sub13_Sub38.anIntArray3456[0];

         for(int var6 = 1; var3 > var6; ++var6) {
            --var1;
            Class3_Sub13_Sub1.outgoingBuffer.putByteA(-var4 + Class3_Sub13_Sub38.anIntArray3456[var1]);
            Class3_Sub13_Sub1.outgoingBuffer.putByteS(Class45.anIntArray729[var1] + -var5);
         }

      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "pi.O(" + var0 + ',' + var1 + ',' + var2 + ')');
      }
   }

   final void method157(int var1, RSByteBuffer var2, boolean var3) {
      try {
         if(var1 == 0) {
            this.anInt3343 = var2.getByteB();
         } else if(var1 == 1) {
            this.aBoolean2375 = var2.getByteB() == 1;
         }

         if(!var3) {
            aClass94_3339 = (RSString)null;
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "pi.A(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

}
