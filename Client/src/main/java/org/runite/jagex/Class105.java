package org.runite.jagex;

final class Class105 {

   private static Class9 aClass9_1438 = new Class9();


   private static int method1633(int var0, Class9 var1) {
      while(var1.anInt128 < var0) {
         var1.anInt140 = var1.anInt140 << 8 | var1.aByteArray127[var1.anInt116] & 255;
         var1.anInt128 += 8;
         ++var1.anInt116;
         ++var1.anInt147;
      }

      int var3 = var1.anInt140 >> var1.anInt128 - var0 & (1 << var0) - 1;
      var1.anInt128 -= var0;
      return var3;
   }

   private static void method1634(Class9 var0) {
      byte var2 = var0.aByte111;
      int var3 = var0.anInt143;
      int var4 = var0.anInt131;
      int var5 = var0.anInt129;
      int[] var6 = Class129.anIntArray1690;
      int var7 = var0.anInt133;
      byte[] var8 = var0.aByteArray117;
      int var9 = var0.anInt118;
      int var10 = var0.anInt126;
      int var12 = var0.anInt121 + 1;

      label67:
      while(true) {
         if(var3 > 0) {
            while(true) {
               if(var10 == 0) {
                  break label67;
               }

               if(var3 == 1) {

                  var8[var9] = var2;
                  ++var9;
                  --var10;
                  break;
               }

               var8[var9] = var2;
               --var3;
               ++var9;
               --var10;
            }
         }

         boolean var14 = true;

         byte var1;
         while(var14) {
            var14 = false;
            if(var4 == var12) {
               var3 = 0;
               break label67;
            }

            var2 = (byte)var5;
            var7 = var6[var7];
            var1 = (byte)(var7 & 255);
            var7 >>= 8;
            ++var4;
            if(var1 != var5) {
               var5 = var1;
               if(var10 == 0) {
                  var3 = 1;
                  break label67;
               }

               var8[var9] = var2;
               ++var9;
               --var10;
               var14 = true;
            } else if(var4 == var12) {
               if(var10 == 0) {
                  var3 = 1;
                  break label67;
               }

               var8[var9] = var2;
               ++var9;
               --var10;
               var14 = true;
            }
         }

         var3 = 2;
         var7 = var6[var7];
         var1 = (byte)(var7 & 255);
         var7 >>= 8;
         ++var4;
         if(var4 != var12) {
            if(var1 == var5) {
               var3 = 3;
               var7 = var6[var7];
               var1 = (byte)(var7 & 255);
               var7 >>= 8;
               ++var4;
               if(var4 != var12) {
                  if(var1 == var5) {
                     var7 = var6[var7];
                     var1 = (byte)(var7 & 255);
                     var7 >>= 8;
                     ++var4;
                     var3 = (var1 & 255) + 4;
                     var7 = var6[var7];
                     var5 = (byte)(var7 & 255);
                     var7 >>= 8;
                     ++var4;
                  } else {
                     var5 = var1;
                  }
               }
            } else {
               var5 = var1;
            }
         }
      }

      var0.aByte111 = var2;
      var0.anInt143 = var3;
      var0.anInt131 = var4;
      var0.anInt129 = var5;
      Class129.anIntArray1690 = var6;
      var0.anInt133 = var7;
      var0.aByteArray117 = var8;
      var0.anInt118 = var9;
      var0.anInt126 = var10;
   }

   private static void method1635(int[] var0, int[] var1, int[] var2, byte[] var3, int var4, int var5, int var6) {
      int var7 = 0;

      int var8;
      for(var8 = var4; var8 <= var5; ++var8) {
         for(int var9 = 0; var9 < var6; ++var9) {
            if(var3[var9] == var8) {
               var2[var7] = var9;
               ++var7;
            }
         }
      }

      for(var8 = 0; var8 < 23; ++var8) {
         var1[var8] = 0;
      }

      for(var8 = 0; var8 < var6; ++var8) {
         ++var1[var3[var8] + 1];
      }

      for(var8 = 1; var8 < 23; ++var8) {
         var1[var8] += var1[var8 - 1];
      }

      for(var8 = 0; var8 < 23; ++var8) {
         var0[var8] = 0;
      }

      int var10 = 0;

      for(var8 = var4; var8 <= var5; ++var8) {
         var10 += var1[var8 + 1] - var1[var8];
         var0[var8] = var10 - 1;
         var10 <<= 1;
      }

      for(var8 = var4 + 1; var8 <= var5; ++var8) {
         var1[var8] = (var0[var8 - 1] + 1 << 1) - var1[var8];
      }

   }

   private static void method1636(Class9 var0) {
      var0.anInt137 = 0;

      for(int var1 = 0; var1 < 256; ++var1) {
         if(var0.aBooleanArray112[var1]) {
            var0.aByteArray114[var0.anInt137] = (byte)var1;
            ++var0.anInt137;
         }
      }

   }

   private static byte method1637(Class9 var0) {
      return (byte)method1633(8, var0);
   }

   private static void method1638(Class9 var0) {
      int var22;
      int[] var23;
      int[] var24;
      int[] var25;
      var0.anInt142 = 1;
      if(Class129.anIntArray1690 == null) {
         Class129.anIntArray1690 = new int[var0.anInt142 * 100000];
      }

      boolean var26 = true;

      while(var26) {
         byte var1 = method1637(var0);
         if(var1 == 23) {
            return;
         }

         var1 = method1637(var0);
         var1 = method1637(var0);
         var1 = method1637(var0);
         var1 = method1637(var0);
         var1 = method1637(var0);
         var1 = method1637(var0);
         var1 = method1637(var0);
         var1 = method1637(var0);
         var1 = method1637(var0);
         var1 = method1639(var0);

         var0.anInt135 = 0;
         var1 = method1637(var0);
         var0.anInt135 = var0.anInt135 << 8 | var1 & 255;
         var1 = method1637(var0);
         var0.anInt135 = var0.anInt135 << 8 | var1 & 255;
         var1 = method1637(var0);
         var0.anInt135 = var0.anInt135 << 8 | var1 & 255;

         int var35;
         for(var35 = 0; var35 < 16; ++var35) {
            var1 = method1639(var0);
            var0.aBooleanArray124[var35] = var1 == 1;
         }

         for(var35 = 0; var35 < 256; ++var35) {
            var0.aBooleanArray112[var35] = false;
         }

         int var36;
         for(var35 = 0; var35 < 16; ++var35) {
            if(var0.aBooleanArray124[var35]) {
               for(var36 = 0; var36 < 16; ++var36) {
                  var1 = method1639(var0);
                  if(var1 == 1) {
                     var0.aBooleanArray112[var35 * 16 + var36] = true;
                  }
               }
            }
         }

         method1636(var0);
         int var38 = var0.anInt137 + 2;
         int var39 = method1633(3, var0);
         int var40 = method1633(15, var0);
         var35 = 0;

         while(var35 < var40) {
            var36 = 0;

            while(true) {
               var1 = method1639(var0);
               if(var1 == 0) {
                  var0.aByteArray139[var35] = (byte)var36;
                  ++var35;
                  break;
               }

               ++var36;
            }
         }

         byte[] var27 = new byte[6];

         byte var29;
         for(var29 = 0; var29 < var39; var27[var29] = var29++) {
         }

         for(var35 = 0; var35 < var40; ++var35) {
            var29 = var0.aByteArray139[var35];

            byte var28;
            for(var28 = var27[var29]; var29 > 0; --var29) {
               var27[var29] = var27[var29 - 1];
            }

            var27[0] = var28;
            var0.aByteArray130[var35] = var28;
         }

         int var37;
         for(var37 = 0; var37 < var39; ++var37) {
            int var49 = method1633(5, var0);
            var35 = 0;

            while(var35 < var38) {
               while(true) {
                  var1 = method1639(var0);
                  if(var1 == 0) {
                     var0.aByteArrayArray146[var37][var35] = (byte)var49;
                     ++var35;
                     break;
                  }

                  var1 = method1639(var0);
                  if(var1 == 0) {
                     ++var49;
                  } else {
                     --var49;
                  }
               }
            }
         }

         for(var37 = 0; var37 < var39; ++var37) {
            byte var2 = 32;
            byte var3 = 0;

            for(var35 = 0; var35 < var38; ++var35) {
               if(var0.aByteArrayArray146[var37][var35] > var3) {
                  var3 = var0.aByteArrayArray146[var37][var35];
               }

               if(var0.aByteArrayArray146[var37][var35] < var2) {
                  var2 = var0.aByteArrayArray146[var37][var35];
               }
            }

            method1635(var0.anIntArrayArray115[var37], var0.anIntArrayArray108[var37], var0.anIntArrayArray110[var37], var0.aByteArrayArray146[var37], var2, var3, var38);
            var0.anIntArray138[var37] = var2;
         }

         int var42 = var0.anInt137 + 1;
         int var41 = -1;
         byte var43;

         for(var35 = 0; var35 <= 255; ++var35) {
            var0.anIntArray134[var35] = 0;
         }

         int var56 = 4095;

         int var55;
         int var54;
         for(var55 = 15; var55 >= 0; --var55) {
            for(var54 = 15; var54 >= 0; --var54) {
               var0.aByteArray109[var56] = (byte)(var55 * 16 + var54);
               --var56;
            }

            var0.anIntArray123[var55] = var56 + 1;
         }

         int var47 = 0;
         byte var53;
         ++var41;
         var43 = 50;
         var53 = var0.aByteArray130[var41];
         var22 = var0.anIntArray138[var53];
         var23 = var0.anIntArrayArray115[var53];
         var25 = var0.anIntArrayArray110[var53];
         var24 = var0.anIntArrayArray108[var53];

         int var45 = var43 - 1;
         int var51 = var22;

         int var50;
         byte var52;
         for(var50 = method1633(var22, var0); var50 > var23[var51]; var50 = var50 << 1 | var52) {
            ++var51;
            var52 = method1639(var0);
         }

         int var44 = var25[var50 - var24[var51]];

         while(var44 != var42) {
            if(var44 == 0 || var44 == 1) {
               int var46 = -1;
               int var48 = 1;

               do {
                  if(var44 == 0) {
                     var46 += var48;
                  } else {
                     var46 += 2 * var48;
                  }

                  var48 *= 2;
                  if(var45 == 0) {
                     ++var41;
                     var45 = 50;
                     var53 = var0.aByteArray130[var41];
                     var22 = var0.anIntArray138[var53];
                     var23 = var0.anIntArrayArray115[var53];
                     var25 = var0.anIntArrayArray110[var53];
                     var24 = var0.anIntArrayArray108[var53];
                  }

                  --var45;
                  var51 = var22;

                  for(var50 = method1633(var22, var0); var50 > var23[var51]; var50 = var50 << 1 | var52) {
                     ++var51;
                     var52 = method1639(var0);
                  }

                  var44 = var25[var50 - var24[var51]];
               } while(var44 == 0 || var44 == 1);

               ++var46;
               var1 = var0.aByteArray114[var0.aByteArray109[var0.anIntArray123[0]] & 255];

               for(var0.anIntArray134[var1 & 255] += var46; var46 > 0; --var46) {
                  Class129.anIntArray1690[var47] = var1 & 255;
                  ++var47;
               }
            } else {
               int var33 = var44 - 1;
               int var30;
               if(var33 < 16) {
                  var30 = var0.anIntArray123[0];

                  for(var1 = var0.aByteArray109[var30 + var33]; var33 > 3; var33 -= 4) {
                     int var34 = var30 + var33;
                     var0.aByteArray109[var34] = var0.aByteArray109[var34 - 1];
                     var0.aByteArray109[var34 - 1] = var0.aByteArray109[var34 - 2];
                     var0.aByteArray109[var34 - 2] = var0.aByteArray109[var34 - 3];
                     var0.aByteArray109[var34 - 3] = var0.aByteArray109[var34 - 4];
                  }

                  while(var33 > 0) {
                     var0.aByteArray109[var30 + var33] = var0.aByteArray109[var30 + var33 - 1];
                     --var33;
                  }

                  var0.aByteArray109[var30] = var1;
               } else {
                  int var31 = var33 / 16;
                  int var32 = var33 % 16;
                  var30 = var0.anIntArray123[var31] + var32;

                  for(var1 = var0.aByteArray109[var30]; var30 > var0.anIntArray123[var31]; --var30) {
                     var0.aByteArray109[var30] = var0.aByteArray109[var30 - 1];
                  }

                  ++var0.anIntArray123[var31];

                  while(var31 > 0) {
                     --var0.anIntArray123[var31];
                     var0.aByteArray109[var0.anIntArray123[var31]] = var0.aByteArray109[var0.anIntArray123[var31 - 1] + 16 - 1];
                     --var31;
                  }

                  --var0.anIntArray123[0];
                  var0.aByteArray109[var0.anIntArray123[0]] = var1;
                  if(var0.anIntArray123[0] == 0) {
                     var56 = 4095;

                     for(var55 = 15; var55 >= 0; --var55) {
                        for(var54 = 15; var54 >= 0; --var54) {
                           var0.aByteArray109[var56] = var0.aByteArray109[var0.anIntArray123[var55] + var54];
                           --var56;
                        }

                        var0.anIntArray123[var55] = var56 + 1;
                     }
                  }
               }

               ++var0.anIntArray134[var0.aByteArray114[var1 & 255] & 255];
               Class129.anIntArray1690[var47] = var0.aByteArray114[var1 & 255] & 255;
               ++var47;
               if(var45 == 0) {
                  ++var41;
                  var45 = 50;
                  var53 = var0.aByteArray130[var41];
                  var22 = var0.anIntArray138[var53];
                  var23 = var0.anIntArrayArray115[var53];
                  var25 = var0.anIntArrayArray110[var53];
                  var24 = var0.anIntArrayArray108[var53];
               }

               --var45;
               var51 = var22;

               for(var50 = method1633(var22, var0); var50 > var23[var51]; var50 = var50 << 1 | var52) {
                  ++var51;
                  var52 = method1639(var0);
               }

               var44 = var25[var50 - var24[var51]];
            }
         }

         var0.anInt143 = 0;
         var0.aByte111 = 0;
         var0.anIntArray122[0] = 0;

         for(var35 = 1; var35 <= 256; ++var35) {
            var0.anIntArray122[var35] = var0.anIntArray134[var35 - 1];
         }

         for(var35 = 1; var35 <= 256; ++var35) {
            var0.anIntArray122[var35] += var0.anIntArray122[var35 - 1];
         }

         for(var35 = 0; var35 < var47; ++var35) {
            var1 = (byte)(Class129.anIntArray1690[var35] & 255);
            Class129.anIntArray1690[var0.anIntArray122[var1 & 255]] |= var35 << 8;
            ++var0.anIntArray122[var1 & 255];
         }

         var0.anInt133 = Class129.anIntArray1690[var0.anInt135] >> 8;
         var0.anInt131 = 0;
         var0.anInt133 = Class129.anIntArray1690[var0.anInt133];
         var0.anInt129 = (byte)(var0.anInt133 & 255);
         var0.anInt133 >>= 8;
         ++var0.anInt131;
         var0.anInt121 = var47;
         method1634(var0);
         var26 = var0.anInt131 == var0.anInt121 + 1 && var0.anInt143 == 0;
      }

   }

   private static byte method1639(Class9 var0) {
      return (byte)method1633(1, var0);
   }

   static void method1640(byte[] var0, int var1, byte[] var2) {
      Class9 var5 = aClass9_1438;
      synchronized(var5) {
         aClass9_1438.aByteArray127 = var2;
         aClass9_1438.anInt116 = 9;
         aClass9_1438.aByteArray117 = var0;
         aClass9_1438.anInt118 = 0;
         aClass9_1438.anInt126 = var1;
         aClass9_1438.anInt128 = 0;
         aClass9_1438.anInt140 = 0;
         aClass9_1438.anInt147 = 0;
         aClass9_1438.anInt141 = 0;
         method1638(aClass9_1438);
         var1 -= aClass9_1438.anInt126;
         aClass9_1438.aByteArray127 = null;
         aClass9_1438.aByteArray117 = null;
      }
   }

   public static void method1641() {
      aClass9_1438 = null;
   }

}
