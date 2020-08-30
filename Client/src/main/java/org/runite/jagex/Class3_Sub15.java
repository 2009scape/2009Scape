package org.runite.jagex;

final class Class3_Sub15 extends Class3 {

   static int anInt2421 = -1;
   byte[] aByteArray2422;
   private int[] anIntArray2423;
   int anInt2424;
   byte[] aByteArray2425;
   static int anInt2426;
   static boolean aBoolean2427 = false;
   static Class93 aClass93_2428 = new Class93(50);
   static IOHandler aClass89_2429;
   byte[] aByteArray2430;
   Class3_Sub12_Sub1[] aClass3_Sub12_Sub1Array2431;
   static RSString COMMAND_TWEENING = RSString.createRSString("::tween");
   static boolean aBoolean2433 = false;
   short[] aShortArray2434;
   Class166[] aClass166Array2435;
   static int anInt2436;


   final void method369() {
      try {
         this.anIntArray2423 = null;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "jk.A(" + (byte) -124 + ')');
      }
   }

   static void method370() {
      try {
         //int var1 = -125 / ((0 - var0) / 59);
         Class154.aClass93_1955.method1524();
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "jk.B(" + (byte) -73 + ')');
      }
   }

   static Class3_Sub28_Sub3 method371(RSString var1) {
      try {

         for(Class3_Sub28_Sub3 var2 = (Class3_Sub28_Sub3)Class134.aClass61_1758.method1222(); var2 != null; var2 = (Class3_Sub28_Sub3)Class134.aClass61_1758.method1221()) {
            if(var2.aClass94_3561.method1528(var1)) {
               return var2;
            }
         }

         return null;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "jk.C(" + 2 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   public static void method372(boolean var0) {
      try {
         COMMAND_TWEENING = null;
         if(var0) {
            aClass93_2428 = null;
            aClass89_2429 = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "jk.D(" + var0 + ')');
      }
   }

   final boolean method373(int[] var2, Class83 var3, byte[] var4) {
      try {
         int var6 = 0;
         Class3_Sub12_Sub1 var7 = null;
         boolean var5 = true;

         for(int var8 = 0; var8 < 128; ++var8) {
            if(null == var4 || var4[var8] != 0) {
               int var9 = this.anIntArray2423[var8];
               if(var9 != 0) {
                  if(var6 != var9) {
                     var6 = var9--;
                     if((var9 & 1) == 0) {
                        var7 = var3.method1413(var9 >> 2, var2);
                     } else {
                        var7 = var3.method1416(var9 >> 2, var2);
                     }

                     if(var7 == null) {
                        var5 = false;
                     }
                  }

                  if(null != var7) {
                     this.aClass3_Sub12_Sub1Array2431[var8] = var7;
                     this.anIntArray2423[var8] = 0;
                  }
               }
            }
         }

         return var5;
      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "jk.E(" + 17904 + ',' + (var2 != null?"{...}":"null") + ',' + (var3 != null?"{...}":"null") + ',' + (var4 != null?"{...}":"null") + ')');
      }
   }

   static void method374(int var0, boolean var1, byte[] var2, int var3, Class91[] var5) {
      try {
         RSByteBuffer var6 = new RSByteBuffer(var2);
         int objectId = -1;

         while(var6.buffer.length > 0) {
            int var8 = var6.method773((byte)-126);
            if(0 == var8) {

               return;
            }

            objectId += var8;
            int data = 0;

            while(true) {
               int var10 = var6.getSmart();
               if(var10 == 0) {
                  break;
               }

               data += var10 - 1;
               int var11 = data & 63;
               int var13 = data >> 12;
               int var12 = data >> 6 & 63;
               int var14 = var6.getByteB();
               int var15 = var14 >> 2;
               int var16 = var14 & 3;
               int var17 = var0 + var12;
               int var18 = var11 + var3;
               if(var17 > 0 && var18 > 0 && var17 < 103 && var18 < 103) {
                  Class91 var19 = null;
                  if(!var1) {
                     int var20 = var13;
                     if((2 & Class9.aByteArrayArrayArray113[1][var17][var18]) == 2) {
                        var20 = var13 - 1;
                     }

                     if(0 <= (var20 %= 4)) {
                        var19 = var5[var20];
                     }
                  }

                  Class110.method1683(var13 % 4, !var1, var13, var1, var19, objectId, var15, var17, var18, var16);
               }
            }
         }
      } catch (RuntimeException var21) {
         throw Class44.clientError(var21, "jk.F(" + var0 + ',' + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ',' + 0 + ',' + (var5 != null?"{...}":"null") + ')');
      }
   }

   public Class3_Sub15() {}

   Class3_Sub15(byte[] var1) {
      try {
         this.aShortArray2434 = new short[128];
         this.aByteArray2430 = new byte[128];
         this.aClass3_Sub12_Sub1Array2431 = new Class3_Sub12_Sub1[128];
         this.aByteArray2422 = new byte[128];
         this.aClass166Array2435 = new Class166[128];
         this.anIntArray2423 = new int[128];
         int var3 = 0;
         this.aByteArray2425 = new byte[128];

         RSByteBuffer var2;
         for(var2 = new RSByteBuffer(var1); var2.buffer[var3 + var2.index] != 0; ++var3) {
         }

         byte[] var4 = new byte[var3];

         int var5;
         for(var5 = 0; var3 > var5; ++var5) {
            var4[var5] = var2.getByte();
         }

         ++var2.index;
         ++var3;
         var5 = var2.index;
         var2.index += var3;

         int var6;
         for(var6 = 0; 0 != var2.buffer[var2.index + var6]; ++var6) {
         }

         byte[] var7 = new byte[var6];

         int var8;
         for(var8 = 0; var6 > var8; ++var8) {
            var7[var8] = var2.getByte();
         }

         ++var2.index;
         ++var6;
         int var9 = 0;
         var8 = var2.index;

         for(var2.index += var6; var2.buffer[var9 + var2.index] != 0; ++var9) {
         }

         byte[] var10 = new byte[var9];

         for(int var11 = 0; var9 > var11; ++var11) {
            var10[var11] = var2.getByte();
         }

         ++var2.index;
         ++var9;
         byte[] var37 = new byte[var9];
         int var12;
         int var14;
         if(var9 <= 1) {
            var12 = var9;
         } else {
            var12 = 2;
            var37[1] = 1;
            int var13 = 1;

            for(var14 = 2; var14 < var9; ++var14) {
               int var15 = var2.getByteB();
               if(0 == var15) {
                  var13 = var12++;
               } else {
                  if(var15 <= var13) {
                     --var15;
                  }

                  var13 = var15;
               }

               var37[var14] = (byte)var13;
            }
         }

         Class166[] var38 = new Class166[var12];

         Class166 var41;
         for(var14 = 0; var14 < var38.length; ++var14) {
            var41 = var38[var14] = new Class166();
            int var16 = var2.getByteB();
            if(0 < var16) {
               var41.aByteArray2064 = new byte[2 * var16];
            }

            var16 = var2.getByteB();
            if(var16 > 0) {
               var41.aByteArray2076 = new byte[var16 * 2 + 2];
               var41.aByteArray2076[1] = 64;
            }
         }

         var14 = var2.getByteB();
         byte[] var40 = var14 > 0 ?new byte[var14 * 2]:null;
         var14 = var2.getByteB();
         byte[] var39 = var14 > 0 ?new byte[var14 * 2]:null;

         int var17;
         for(var17 = 0; var2.buffer[var17 + var2.index] != 0; ++var17) {
         }

         byte[] var18 = new byte[var17];

         int var19;
         for(var19 = 0; var19 < var17; ++var19) {
            var18[var19] = var2.getByte();
         }

         ++var2.index;
         ++var17;
         var19 = 0;

         int var20;
         for(var20 = 0; var20 < 128; ++var20) {
            var19 += var2.getByteB();
            this.aShortArray2434[var20] = (short)var19;
         }

         var19 = 0;

         for(var20 = 0; var20 < 128; ++var20) {
            var19 += var2.getByteB();
            this.aShortArray2434[var20] = (short)(this.aShortArray2434[var20] + (var19 << 8));
         }

         var20 = 0;
         int var21 = 0;
         int var22 = 0;

         int var23;
         for(var23 = 0; var23 < 128; ++var23) {
            if(var20 == 0) {
               if(var21 < var18.length) {
                  var20 = var18[var21++];
               } else {
                  var20 = -1;
               }

               var22 = var2.method741((byte)122);
            }

            this.aShortArray2434[var23] = (short)(this.aShortArray2434[var23] + Class69.bitwiseAnd('\u8000', -1 + var22 << 14));
            this.anIntArray2423[var23] = var22;
            --var20;
         }

         var20 = 0;
         var23 = 0;
         var21 = 0;

         int var24;
         for(var24 = 0; var24 < 128; ++var24) {
            if(this.anIntArray2423[var24] != 0) {
               if(var20 == 0) {
                  var23 = var2.buffer[var5++] + -1;
                  if(var4.length > var21) {
                     var20 = var4[var21++];
                  } else {
                     var20 = -1;
                  }
               }

               --var20;
               this.aByteArray2425[var24] = (byte)var23;
            }
         }

         var20 = 0;
         var21 = 0;
         var24 = 0;

         for(int var25 = 0; var25 < 128; ++var25) {
            if(this.anIntArray2423[var25] != 0) {
               if(0 == var20) {
                  var24 = var2.buffer[var8++] - -16 << 2;
                  if(var7.length > var21) {
                     var20 = var7[var21++];
                  } else {
                     var20 = -1;
                  }
               }

               --var20;
               this.aByteArray2422[var25] = (byte)var24;
            }
         }

         var21 = 0;
         var20 = 0;
         Class166 var43 = null;

         int var26;
         for(var26 = 0; var26 < 128; ++var26) {
            if(this.anIntArray2423[var26] != 0) {
               if(var20 == 0) {
                  var43 = var38[var37[var21]];
                  if(var21 >= var10.length) {
                     var20 = -1;
                  } else {
                     var20 = var10[var21++];
                  }
               }

               this.aClass166Array2435[var26] = var43;
               --var20;
            }
         }

         var20 = 0;
         var21 = 0;
         var26 = 0;

         int var27;
         for(var27 = 0; var27 < 128; ++var27) {
            if(var20 == 0) {
               if(var18.length > var21) {
                  var20 = var18[var21++];
               } else {
                  var20 = -1;
               }

               if(this.anIntArray2423[var27] > 0) {
                  var26 = var2.getByteB() + 1;
               }
            }

            --var20;
            this.aByteArray2430[var27] = (byte)var26;
         }

         this.anInt2424 = var2.getByteB() + 1;

         int var29;
         Class166 var28;
         for(var27 = 0; var27 < var12; ++var27) {
            var28 = var38[var27];
            if(var28.aByteArray2064 != null) {
               for(var29 = 1; var29 < var28.aByteArray2064.length; var29 += 2) {
                  var28.aByteArray2064[var29] = var2.getByte();
               }
            }

            if(var28.aByteArray2076 != null) {
               for(var29 = 3; var29 < var28.aByteArray2076.length + -2; var29 += 2) {
                  var28.aByteArray2076[var29] = var2.getByte();
               }
            }
         }

         if(null != var40) {
            for(var27 = 1; var40.length > var27; var27 += 2) {
               var40[var27] = var2.getByte();
            }
         }

         if(null != var39) {
            for(var27 = 1; var27 < var39.length; var27 += 2) {
               var39[var27] = var2.getByte();
            }
         }

         for(var27 = 0; var27 < var12; ++var27) {
            var28 = var38[var27];
            if(null != var28.aByteArray2076) {
               var19 = 0;

               for(var29 = 2; var28.aByteArray2076.length > var29; var29 += 2) {
                  var19 -= -1 + -var2.getByteB();
                  var28.aByteArray2076[var29] = (byte)var19;
               }
            }
         }

         for(var27 = 0; var12 > var27; ++var27) {
            var28 = var38[var27];
            if(null != var28.aByteArray2064) {
               var19 = 0;

               for(var29 = 2; var28.aByteArray2064.length > var29; var29 += 2) {
                  var19 = var19 - -1 - -var2.getByteB();
                  var28.aByteArray2064[var29] = (byte)var19;
               }
            }
         }

         byte var30;
         int var34;
         int var32;
         int var33;
         int var44;
         byte var48;
         if(null != var40) {
            var19 = var2.getByteB();
            var40[0] = (byte)var19;

            for(var27 = 2; var40.length > var27; var27 += 2) {
               var19 = 1 + (var19 - -var2.getByteB());
               var40[var27] = (byte)var19;
            }

            var48 = var40[0];
            byte var46 = var40[1];

            for(var29 = 0; var29 < var48; ++var29) {
               this.aByteArray2430[var29] = (byte)(32 + var46 * this.aByteArray2430[var29] >> 6);
            }

            for(var29 = 2; var29 < var40.length; var48 = var30) {
               var30 = var40[var29];
               byte var31 = var40[1 + var29];
               var29 += 2;
               var32 = (var30 - var48) * var46 + (var30 - var48) / 2;

               for(var33 = var48; var30 > var33; ++var33) {
                  var34 = Class3_Sub13_Sub32.method319(var32, -125, -var48 + var30);
                  var32 += var31 + -var46;
                  this.aByteArray2430[var33] = (byte)(var34 * this.aByteArray2430[var33] - -32 >> 6);
               }

               var46 = var31;
            }

             for(var44 = var48; var44 < 128; ++var44) {
               this.aByteArray2430[var44] = (byte)(32 + this.aByteArray2430[var44] * var46 >> 6);
            }
         }

         if(null != var39) {
            var19 = var2.getByteB();
            var39[0] = (byte)var19;

            for(var27 = 2; var27 < var39.length; var27 += 2) {
               var19 = 1 + (var19 - -var2.getByteB());
               var39[var27] = (byte)var19;
            }

            var48 = var39[0];
            int var47 = var39[1] << 1;

            for(var29 = 0; var29 < var48; ++var29) {
               var44 = var47 + (255 & this.aByteArray2422[var29]);
               if(var44 < 0) {
                  var44 = 0;
               }

               if(128 < var44) {
                  var44 = 128;
               }

               this.aByteArray2422[var29] = (byte)var44;
            }

            int var45;
            for(var29 = 2; var39.length > var29; var47 = var45) {
               var30 = var39[var29];
               var32 = (-var48 + var30) * var47 - -((var30 - var48) / 2);
               var45 = var39[1 + var29] << 1;
               var29 += 2;

               for(var33 = var48; var30 > var33; ++var33) {
                  var34 = Class3_Sub13_Sub32.method319(var32, -116, -var48 + var30);
                  var32 += -var47 + var45;
                  int var35 = var34 + (this.aByteArray2422[var33] & 255);
                  if(var35 < 0) {
                     var35 = 0;
                  }

                  if(var35 > 128) {
                     var35 = 128;
                  }

                  this.aByteArray2422[var33] = (byte)var35;
               }

               var48 = var30;
            }

            for(var44 = var48; var44 < 128; ++var44) {
               var45 = (255 & this.aByteArray2422[var44]) + var47;
               if(var45 < 0) {
                  var45 = 0;
               }

               if(128 < var45) {
                  var45 = 128;
               }

               this.aByteArray2422[var44] = (byte)var45;
            }
         }

         for(var27 = 0; var12 > var27; ++var27) {
            var38[var27].anInt2078 = var2.getByteB();
         }

         for(var27 = 0; var27 < var12; ++var27) {
            var28 = var38[var27];
            if(null != var28.aByteArray2064) {
               var28.anInt2067 = var2.getByteB();
            }

            if(null != var28.aByteArray2076) {
               var28.anInt2071 = var2.getByteB();
            }

            if(var28.anInt2078 > 0) {
               var28.anInt2063 = var2.getByteB();
            }
         }

         for(var27 = 0; var12 > var27; ++var27) {
            var38[var27].anInt2077 = var2.getByteB();
         }

         for(var27 = 0; var12 > var27; ++var27) {
            var28 = var38[var27];
            if(var28.anInt2077 > 0) {
               var28.anInt2066 = var2.getByteB();
            }
         }

         for(var27 = 0; var27 < var12; ++var27) {
            var28 = var38[var27];
            if(var28.anInt2066 > 0) {
               var28.anInt2069 = var2.getByteB();
            }
         }

      } catch (RuntimeException var36) {
         throw Class44.clientError(var36, "jk.<init>(" + (var1 != null?"{...}":"null") + ')');
      }
   }

}
