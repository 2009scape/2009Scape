package org.runite.jagex;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Hashtable;

final class Class158_Sub1 extends Class158 implements ImageProducer, ImageObserver {

   static RSString[] aClass94Array2977 = new RSString[5];
   private ImageConsumer anImageConsumer2978;
   private ColorModel aColorModel2979;
   static Class3_Sub1 aClass3_Sub1_2980 = new Class3_Sub1(0, -1);
   static boolean aBoolean2981 = false;
   static Class93 aClass93_2982 = new Class93(32);

    static Class100 method629(int var1) {
       try {
          Class100 var2 = (Class100)Class44.aClass93_725.get((long)var1);
          if(var2 == null) {
             byte[] var3 = Class3_Sub23.aClass153_2536.getFile(1, var1);
             var2 = new Class100();
             if(null != var3) {
                var2.method1601(var1, new RSByteBuffer(var3));
             }

             Class44.aClass93_725.put((byte)-104, var2, (long)var1);
             return var2;
          } else {
             return var2;
          }
       } catch (RuntimeException var4) {
          throw Class44.clientError(var4, "qc.B(" + true + ',' + var1 + ')');
       }
    }


    public final synchronized void addConsumer(ImageConsumer var1) {
      try {
         this.anImageConsumer2978 = var1;
         var1.setDimensions(this.anInt2012, this.anInt2011);
         var1.setProperties((Hashtable)null);
         var1.setColorModel(this.aColorModel2979);
         var1.setHints(14);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "di.addConsumer(" + (var1 != null?"{...}":"null") + ')');
      }
   }

   public static void method2187(int var0) {
      try {
         aClass94Array2977 = null;
         aClass93_2982 = null;
         aClass3_Sub1_2980 = null;
         if(var0 != 27316) {
            aBoolean2981 = true;
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "di.I(" + var0 + ')');
      }
   }

   private synchronized void method2188(int var1, int var2, int var3, int var5) {
      try {
         if(null != this.anImageConsumer2978) {
            this.anImageConsumer2978.setPixels(var3, var5, var1, var2, this.aColorModel2979, this.anIntArray2007, var5 * this.anInt2012 + var3, this.anInt2012);
            this.anImageConsumer2978.imageComplete(2);

         }
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "di.N(" + var1 + ',' + var2 + ',' + var3 + ',' + (byte) -124 + ',' + var5 + ')');
      }
   }

   public final synchronized void removeConsumer(ImageConsumer var1) {
      try {
         if(this.anImageConsumer2978 == var1) {
            this.anImageConsumer2978 = null;
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "di.removeConsumer(" + (var1 != null?"{...}":"null") + ')');
      }
   }

   final void drawGraphics(int var1, int var2, int var4, Graphics var5, int var6) {
      try {
         this.method2188(var1, var4, var2, var6);
         Shape var7 = var5.getClip();
         var5.clipRect(var2, var6, var1, var4);
         var5.drawImage(this.anImage2009, 0, 0, this);
         var5.setClip(var7);
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "di.E(" + var1 + ',' + var2 + ',' + 6260 + ',' + var4 + ',' + (var5 != null?"{...}":"null") + ',' + var6 + ')');
      }
   }

   public final void startProduction(ImageConsumer var1) {
      try {
         this.addConsumer(var1);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "di.startProduction(" + (var1 != null?"{...}":"null") + ')');
      }
   }

   static void method2189(Class91[] var0, boolean var1, int var2) {
      try {
         int var4;
         int var5;
         if(!var1) {
            for(var4 = 0; var4 < 4; ++var4) {
               for(var5 = 0; var5 < 104; ++var5) {
                  for(int var6 = 0; var6 < 104; ++var6) {
                     if((1 & Class9.aByteArrayArrayArray113[var4][var5][var6]) == 1) {
                        int var7 = var4;
                        if((2 & Class9.aByteArrayArrayArray113[1][var5][var6]) == 2) {
                           var7 = var4 - 1;
                        }

                        if(var7 >= 0) {
                           var0[var7].method1497(var6, var5);
                        }
                     }
                  }
               }
            }

            AbstractIndexedSprite.anInt1463 += (int)(Math.random() * 5.0D) - 2;
            if(AbstractIndexedSprite.anInt1463 < -16) {
               AbstractIndexedSprite.anInt1463 = -16;
            }

            if(AbstractIndexedSprite.anInt1463 > 16) {
               AbstractIndexedSprite.anInt1463 = 16;
            }

            Class3_Sub13_Sub14.anInt3158 += (int)(Math.random() * 5.0D) - 2;
            if(-8 > Class3_Sub13_Sub14.anInt3158) {
               Class3_Sub13_Sub14.anInt3158 = -8;
            }

            if(Class3_Sub13_Sub14.anInt3158 > 8) {
               Class3_Sub13_Sub14.anInt3158 = 8;
            }
         }

         byte var3;
         if(var1) {
            var3 = 1;
         } else {
            var3 = 4;
         }

         var4 = Class3_Sub13_Sub14.anInt3158 >> 2 << 10;
         int[][] var34 = new int[104][104];
         int[][] var35 = new int[104][104];
         var5 = AbstractIndexedSprite.anInt1463 >> 1;

         int var8;
         int var10;
         int var11;
         int var13;
         int var14;
         int var15;
         int var16;
         int var19;
         int var18;
         int var20;
         int var37;
         int var44;
         for(var8 = 0; var3 > var8; ++var8) {
            byte[][] var9 = Class67.aByteArrayArrayArray1014[var8];
            int var21;
            int var23;
            int var22;
            int var24;
            if(HDToolKit.highDetail) {
               if(Class106.aBoolean1441) {
                  for(var10 = 1; var10 < 103; ++var10) {
                     for(var11 = 1; var11 < 103; ++var11) {
                        var13 = (var9[1 + var11][var10] >> 3) + (var9[-1 + var11][var10] >> 2) - -(var9[var11][-1 + var10] >> 2) - -(var9[var11][1 + var10] >> 3) - -(var9[var11][var10] >> 1);
                        byte var12 = 74;
                        var35[var11][var10] = -var13 + var12;
                     }
                  }
               } else {
                  var10 = (int)Class92.light0Position[0];
                  var11 = (int)Class92.light0Position[1];
                  var37 = (int)Class92.light0Position[2];
                  var13 = (int)Math.sqrt((double)(var11 * var11 + (var10 * var10 - -(var37 * var37))));
                  var14 = 1024 * var13 >> 8;

                  for(var15 = 1; var15 < 103; ++var15) {
                     for(var16 = 1; var16 < 103; ++var16) {
                        byte var17 = 96;
                        var18 = Class44.anIntArrayArrayArray723[var8][var16 - -1][var15] - Class44.anIntArrayArrayArray723[var8][-1 + var16][var15];
                        var19 = Class44.anIntArrayArrayArray723[var8][var16][var15 + 1] - Class44.anIntArrayArrayArray723[var8][var16][-1 + var15];
                        var20 = (int)Math.sqrt((double)(var18 * var18 + 65536 + var19 * var19));
                        var21 = (var18 << 8) / var20;
                        var24 = (var9[var16][1 + var15] >> 3) + (var9[var16][var15 - 1] >> 2) + ((var9[var16 - 1][var15] >> 2) + (var9[var16 + 1][var15] >> 3) - -(var9[var16][var15] >> 1));
                        var22 = -65536 / var20;
                        var23 = (var19 << 8) / var20;
                        var44 = var17 + (var37 * var23 + (var10 * var21 - -(var22 * var11))) / var14;
                        var35[var16][var15] = var44 + -((int)((float)var24 * 1.7F));
                     }
                  }
               }
            } else {
               var10 = (int)Math.sqrt(5100.0D);
               var11 = 768 * var10 >> 8;

               for(var37 = 1; var37 < 103; ++var37) {
                  for(var13 = 1; 103 > var13; ++var13) {
                     var16 = -Class44.anIntArrayArrayArray723[var8][var13][-1 + var37] + Class44.anIntArrayArrayArray723[var8][var13][var37 + 1];
                     byte var41 = 74;
                     var15 = -Class44.anIntArrayArrayArray723[var8][var13 + -1][var37] + Class44.anIntArrayArrayArray723[var8][var13 - -1][var37];
                     var44 = (int)Math.sqrt((double)(var15 * var15 - -65536 - -(var16 * var16)));
                     var20 = (var16 << 8) / var44;
                     var19 = -65536 / var44;
                     var18 = (var15 << 8) / var44;
                     var21 = (var9[var13][var37] >> 1) + (var9[var13][-1 + var37] >> 2) + (var9[var13 - -1][var37] >> 3) + ((var9[var13 - 1][var37] >> 2) - -(var9[var13][var37 + 1] >> 3));
                     var14 = var41 + (var20 * -50 + var18 * -50 - -(var19 * -10)) / var11;
                     var35[var13][var37] = var14 - var21;
                  }
               }
            }

            for(var10 = 0; 104 > var10; ++var10) {
               Class129.anIntArray1695[var10] = 0;
               Class80.anIntArray1138[var10] = 0;
               Class3_Sub31.anIntArray2606[var10] = 0;
               MouseListeningClass.anIntArray1920[var10] = 0;
               Class3_Sub18.anIntArray2469[var10] = 0;
            }

            for(var10 = -5; var10 < 104; ++var10) {
               for(var11 = 0; 104 > var11; ++var11) {
                  var37 = var10 - -5;
                  if(var37 < 104) {
                     var13 = 255 & Class3_Sub13_Sub36.aByteArrayArrayArray3430[var8][var37][var11];
                     if(var13 > 0) {
                        Class100 var39 = method629(-1 + var13);
                        Class129.anIntArray1695[var11] += var39.anInt1408;
                        Class80.anIntArray1138[var11] += var39.anInt1406;
                        Class3_Sub31.anIntArray2606[var11] += var39.anInt1417;
                        MouseListeningClass.anIntArray1920[var11] += var39.anInt1418;
                        ++Class3_Sub18.anIntArray2469[var11];
                     }
                  }

                  var13 = -5 + var10;
                  if(0 <= var13) {
                     var14 = 255 & Class3_Sub13_Sub36.aByteArrayArrayArray3430[var8][var13][var11];
                     if(var14 > 0) {
                        Class100 var42 = method629(-1 + var14);
                        Class129.anIntArray1695[var11] -= var42.anInt1408;
                        Class80.anIntArray1138[var11] -= var42.anInt1406;
                        Class3_Sub31.anIntArray2606[var11] -= var42.anInt1417;
                        MouseListeningClass.anIntArray1920[var11] -= var42.anInt1418;
                        --Class3_Sub18.anIntArray2469[var11];
                     }
                  }
               }

               if(var10 >= 0) {
                  var11 = 0;
                  var13 = 0;
                  var37 = 0;
                  var14 = 0;
                  var15 = 0;

                  for(var16 = -5; var16 < 104; ++var16) {
                     var44 = var16 - -5;
                     if(104 > var44) {
                        var37 += Class80.anIntArray1138[var44];
                        var15 += Class3_Sub18.anIntArray2469[var44];
                        var11 += Class129.anIntArray1695[var44];
                        var14 += MouseListeningClass.anIntArray1920[var44];
                        var13 += Class3_Sub31.anIntArray2606[var44];
                     }

                     var18 = var16 + -5;
                     if(var18 >= 0) {
                        var37 -= Class80.anIntArray1138[var18];
                        var14 -= MouseListeningClass.anIntArray1920[var18];
                        var11 -= Class129.anIntArray1695[var18];
                        var15 -= Class3_Sub18.anIntArray2469[var18];
                        var13 -= Class3_Sub31.anIntArray2606[var18];
                     }

                     if(0 <= var16 && var15 > 0 && var14 != 0) {
                        var34[var10][var16] = Class3_Sub8.method129(var13 / var15, var37 / var15, 256 * var11 / var14);
                     }
                  }
               }
            }

            for(var10 = 1; var10 < 103; ++var10) {
               label754:
               for(var11 = 1; var11 < 103; ++var11) {
                  if(var1 || NPC.method1986(66) || (2 & Class9.aByteArrayArrayArray113[0][var10][var11]) != 0 || (16 & Class9.aByteArrayArrayArray113[var8][var10][var11]) == 0 && PacketParser.method823(var11, var10, -87, var8) == Class140_Sub3.anInt2745) {
                     if(var8 < Class85.anInt1174) {
                        Class85.anInt1174 = var8;
                     }

                     var37 = 255 & Class3_Sub13_Sub36.aByteArrayArrayArray3430[var8][var10][var11];
                     var13 = Class139.aByteArrayArrayArray1828[var8][var10][var11] & 255;
                     if(0 < var37 || var13 > 0) {
                        var15 = Class44.anIntArrayArrayArray723[var8][var10 + 1][var11];
                        var14 = Class44.anIntArrayArrayArray723[var8][var10][var11];
                        var44 = Class44.anIntArrayArrayArray723[var8][var10][1 + var11];
                        var16 = Class44.anIntArrayArrayArray723[var8][1 + var10][var11 + 1];
                        if(0 < var8) {
                           boolean var47 = true;
                           if(var37 == 0 && Class93.aByteArrayArrayArray1328[var8][var10][var11] != 0) {
                              var47 = false;
                           }

                           if(var13 > 0 && !Class3_Sub13_Sub37.method350((byte)-73, var13 + -1).aBoolean2102) {
                              var47 = false;
                           }

                           if(var47 && var14 == var15 && var16 == var14 && var14 == var44) {
                              Class38_Sub1.anIntArrayArrayArray2609[var8][var10][var11] = Class3_Sub13_Sub29.bitwiseOr(Class38_Sub1.anIntArrayArrayArray2609[var8][var10][var11], 4);
                           }
                        }

                        if(var37 <= 0) {
                           var18 = -1;
                           var19 = 0;
                        } else {
                           var18 = var34[var10][var11];
                           var20 = (var18 & 127) + var5;
                           if(var20 >= 0) {
                              if(var20 > 127) {
                                 var20 = 127;
                              }
                           } else {
                              var20 = 0;
                           }

                           var21 = (896 & var18) + (var18 + var4 & '\ufc00') + var20;
                           var19 = Class51.anIntArray834[Class47.method1100(96, var21)];
                        }

                        var20 = var35[var10][var11];
                        var23 = var35[var10][var11 + 1];
                        var21 = var35[1 + var10][var11];
                        var22 = var35[var10 - -1][var11 - -1];
                        if(var13 == 0) {
                           Class104.method1629(var8, var10, var11, 0, 0, -1, var14, var15, var16, var44, Class47.method1100(var20, var18), Class47.method1100(var21, var18), Class47.method1100(var22, var18), Class47.method1100(var23, var18), 0, 0, 0, 0, var19, 0);
                           if(HDToolKit.highDetail && var8 > 0 && var18 != -1 && method629(-1 + var37).aBoolean1411) {
                              Class141.method2037(0, 0, true, false, var10, var11, var14 - Class44.anIntArrayArrayArray723[0][var10][var11], -Class44.anIntArrayArrayArray723[0][1 + var10][var11] + var15, var16 - Class44.anIntArrayArrayArray723[0][1 + var10][1 + var11], var44 - Class44.anIntArrayArrayArray723[0][var10][1 + var11]);
                           }

                           if(HDToolKit.highDetail && !var1 && Class3_Sub13_Sub9.anIntArrayArray3115 != null && 0 == var8) {
                              for(var24 = var10 + -1; var10 - -1 >= var24; ++var24) {
                                 for(int var52 = -1 + var11; var52 <= 1 + var11; ++var52) {
                                    if((var24 != var10 || var11 != var52) && var24 >= 0 && var24 < 104 && 0 <= var52 && var52 < 104) {
                                       int var54 = Class139.aByteArrayArrayArray1828[var8][var24][var52] & 255;
                                       if(var54 != 0) {
                                          Class168 var53 = Class3_Sub13_Sub37.method350((byte)-25, -1 + var54);
                                          if(var53.anInt2095 != -1 && 4 == Class51.anInterface2_838.method18(var53.anInt2095, 255)) {
                                             Class3_Sub13_Sub9.anIntArrayArray3115[var10][var11] = var53.anInt2094 + (var53.anInt2101 << 24);
                                             continue label754;
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        } else {
                           var24 = 1 + Class93.aByteArrayArrayArray1328[var8][var10][var11];
                           byte var25 = PacketParser.aByteArrayArrayArray81[var8][var10][var11];
                           Class168 var26 = Class3_Sub13_Sub37.method350((byte)-105, var13 + -1);
                           int var27;
                           int var29;
                           int var28;
                           if(HDToolKit.highDetail && !var1 && null != Class3_Sub13_Sub9.anIntArrayArray3115 && 0 == var8) {
                              if(-1 != var26.anInt2095 && Class51.anInterface2_838.method18(var26.anInt2095, 255) == 4) {
                                 Class3_Sub13_Sub9.anIntArrayArray3115[var10][var11] = (var26.anInt2101 << 24) + var26.anInt2094;
                              } else {
                                 label722:
                                 for(var27 = var10 + -1; 1 + var10 >= var27; ++var27) {
                                    for(var28 = var11 + -1; 1 + var11 >= var28; ++var28) {
                                       if((var27 != var10 || var11 != var28) && var27 >= 0 && var27 < 104 && var28 >= 0 && var28 < 104) {
                                          var29 = Class139.aByteArrayArrayArray1828[var8][var27][var28] & 255;
                                          if(var29 != 0) {
                                             Class168 var30 = Class3_Sub13_Sub37.method350((byte)-14, -1 + var29);
                                             if(var30.anInt2095 != -1 && Class51.anInterface2_838.method18(var30.anInt2095, 255) == 4) {
                                                Class3_Sub13_Sub9.anIntArrayArray3115[var10][var11] = var30.anInt2094 + (var30.anInt2101 << 24);
                                                break label722;
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }

                           var27 = var26.anInt2095;
                           if(0 <= var27 && !Class51.anInterface2_838.method17(var27, 101)) {
                              var27 = -1;
                           }

                           int var31;
                           int var55;
                           if(var27 < 0) {
                              if(var26.anInt2103 == -1) {
                                 var28 = -2;
                                 var29 = 0;
                              } else {
                                 var28 = var26.anInt2103;
                                 var55 = var5 + (var28 & 127);
                                 if(var55 >= 0) {
                                    if(var55 > 127) {
                                       var55 = 127;
                                    }
                                 } else {
                                    var55 = 0;
                                 }

                                 var31 = (var28 & 896) + (('\ufc00' & var28 + var4) - -var55);
                                 var29 = Class51.anIntArray834[Class3_Sub29.method729((byte)-85, var31, 96)];
                              }
                           } else {
                              var28 = -1;
                              var29 = Class51.anIntArray834[Class3_Sub29.method729((byte)-126, Class51.anInterface2_838.method15(var27, '\uffff'), 96)];
                           }

                           if(var26.anInt2098 >= 0) {
                              var55 = var26.anInt2098;
                              var31 = var5 + (var55 & 127);
                              if(var31 >= 0) {
                                 if(127 < var31) {
                                    var31 = 127;
                                 }
                              } else {
                                 var31 = 0;
                              }

                              int var32 = (896 & var55) + (('\ufc00' & var55 + var4) - -var31);
                              var29 = Class51.anIntArray834[Class3_Sub29.method729((byte)-101, var32, 96)];
                           }

                           Class104.method1629(var8, var10, var11, var24, var25, var27, var14, var15, var16, var44, Class47.method1100(var20, var18), Class47.method1100(var21, var18), Class47.method1100(var22, var18), Class47.method1100(var23, var18), Class3_Sub29.method729((byte)-72, var28, var20), Class3_Sub29.method729((byte)-107, var28, var21), Class3_Sub29.method729((byte)-82, var28, var22), Class3_Sub29.method729((byte)-93, var28, var23), var19, var29);
                           if(HDToolKit.highDetail && var8 > 0) {
                              Class141.method2037(var24, var25, var28 == -2 || !var26.aBoolean2093, -1 == var18 || !method629(-1 + var37).aBoolean1411, var10, var11, -Class44.anIntArrayArrayArray723[0][var10][var11] + var14, var15 - Class44.anIntArrayArrayArray723[0][1 + var10][var11], -Class44.anIntArrayArrayArray723[0][1 + var10][var11 + 1] + var16, -Class44.anIntArrayArrayArray723[0][var10][1 + var11] + var44);
                           }
                        }
                     }
                  }
               }
            }

            if(HDToolKit.highDetail) {
               float[][] var38 = new float[105][105];
               int[][] var45 = Class44.anIntArrayArrayArray723[var8];
               float[][] var40 = new float[105][105];
               float[][] var43 = new float[105][105];

               for(var14 = 1; var14 <= 103; ++var14) {
                  for(var15 = 1; var15 <= 103; ++var15) {
                     var44 = var45[var15][var14 - -1] + -var45[var15][-1 + var14];
                     var16 = -var45[var15 - 1][var14] + var45[var15 + 1][var14];
                     float var51 = (float)Math.sqrt((double)(var16 * var16 - -65536 - -(var44 * var44)));
                     var38[var15][var14] = (float)var16 / var51;
                     var40[var15][var14] = -256.0F / var51;
                     var43[var15][var14] = (float)var44 / var51;
                  }
               }

               Class3_Sub11[] var50;
               if(var1) {
                  var50 = Class3_Sub13_Sub27.method298(Class9.aByteArrayArrayArray113, Class93.aByteArrayArrayArray1328[var8], Class3_Sub13_Sub36.aByteArrayArrayArray3430[var8], var35, var40, Class3_Sub13_Sub9.anIntArrayArray3115, Class139.aByteArrayArrayArray1828[var8], PacketParser.aByteArrayArrayArray81[var8], var38, var8, var43, var34, Class44.anIntArrayArrayArray723[var8], Class58.anIntArrayArrayArray914[0]);
                  Class61.method1213(var8, var50);
               } else {
                  var50 = Class3_Sub13_Sub27.method298(Class9.aByteArrayArrayArray113, Class93.aByteArrayArrayArray1328[var8], Class3_Sub13_Sub36.aByteArrayArrayArray3430[var8], var35, var40, (int[][])null, Class139.aByteArrayArrayArray1828[var8], PacketParser.aByteArrayArrayArray81[var8], var38, var8, var43, var34, Class44.anIntArrayArrayArray723[var8], (int[][])null);
                  Class3_Sub11[] var46 = Class1.method70(var40, var38, Class44.anIntArrayArrayArray723[var8], var8, var43, PacketParser.aByteArrayArrayArray81[var8], var35, Class93.aByteArrayArrayArray1328[var8], Class3_Sub13_Sub36.aByteArrayArrayArray3430[var8], Class139.aByteArrayArrayArray1828[var8], Class9.aByteArrayArrayArray113);
                  Class3_Sub11[] var49 = new Class3_Sub11[var50.length - -var46.length];

                  for(var44 = 0; var44 < var50.length; ++var44) {
                     var49[var44] = var50[var44];
                  }

                  for(var44 = 0; var44 < var46.length; ++var44) {
                     var49[var50.length + var44] = var46[var44];
                  }

                  Class61.method1213(var8, var49);
                  Class129.method1769(var43, Class3_Sub13_Sub36.aByteArrayArrayArray3430[var8], PacketParser.aByteArrayArrayArray81[var8], Class68.aClass43Array1021, var8, Class68.anInt1032, var40, Class93.aByteArrayArrayArray1328[var8], Class139.aByteArrayArrayArray1828[var8], Class44.anIntArrayArrayArray723[var8], var38);
               }
            }

            Class3_Sub13_Sub36.aByteArrayArrayArray3430[var8] = (byte[][])null;
            Class139.aByteArrayArrayArray1828[var8] = (byte[][])null;
            Class93.aByteArrayArrayArray1328[var8] = (byte[][])null;
            PacketParser.aByteArrayArrayArray81[var8] = (byte[][])null;
            Class67.aByteArrayArrayArray1014[var8] = (byte[][])null;
         }

         if(var2 <= 26) {
            method2187(86);
         }

         Class128.method1764();
         if(!var1) {
            int var36;
            for(var8 = 0; 104 > var8; ++var8) {
               for(var36 = 0; var36 < 104; ++var36) {
                  if((Class9.aByteArrayArrayArray113[1][var8][var36] & 2) == 2) {
                     Class3_Sub28_Sub18.method709(var8, var36);
                  }
               }
            }

            for(var8 = 0; 4 > var8; ++var8) {
               for(var36 = 0; var36 <= 104; ++var36) {
                  for(var10 = 0; var10 <= 104; ++var10) {
                     short var48;
                     if((Class38_Sub1.anIntArrayArrayArray2609[var8][var10][var36] & 1) != 0) {
                        var14 = var8;

                        for(var11 = var36; var11 > 0 && (1 & Class38_Sub1.anIntArrayArrayArray2609[var8][var10][-1 + var11]) != 0; --var11) {
                        }

                        var13 = var8;

                        for(var37 = var36; var37 < 104 && (1 & Class38_Sub1.anIntArrayArrayArray2609[var8][var10][var37 - -1]) != 0; ++var37) {
                        }

                        label453:
                        while(var13 > 0) {
                           for(var15 = var11; var15 <= var37; ++var15) {
                              if((Class38_Sub1.anIntArrayArrayArray2609[var13 + -1][var10][var15] & 1) == 0) {
                                 break label453;
                              }
                           }

                           --var13;
                        }

                        label464:
                        while(var14 < 3) {
                           for(var15 = var11; var15 <= var37; ++var15) {
                              if((1 & Class38_Sub1.anIntArrayArrayArray2609[var14 + 1][var10][var15]) == 0) {
                                 break label464;
                              }
                           }

                           ++var14;
                        }

                        var15 = (var14 - (-1 + var13)) * (-var11 + (var37 - -1));
                        if(var15 >= 8) {
                           var48 = 240;
                           var44 = -var48 + Class44.anIntArrayArrayArray723[var14][var10][var11];
                           var18 = Class44.anIntArrayArrayArray723[var13][var10][var11];
                           Class167.method2263(1, 128 * var10, 128 * var10, 128 * var11, var37 * 128 + 128, var44, var18);

                           for(var19 = var13; var19 <= var14; ++var19) {
                              for(var20 = var11; var37 >= var20; ++var20) {
                                 Class38_Sub1.anIntArrayArrayArray2609[var19][var10][var20] = Class69.bitwiseAnd(Class38_Sub1.anIntArrayArrayArray2609[var19][var10][var20], -2);
                              }
                           }
                        }
                     }

                     if((2 & Class38_Sub1.anIntArrayArrayArray2609[var8][var10][var36]) != 0) {
                        for(var11 = var10; 0 < var11 && (Class38_Sub1.anIntArrayArrayArray2609[var8][-1 + var11][var36] & 2) != 0; --var11) {
                        }

                        var14 = var8;
                        var13 = var8;

                        for(var37 = var10; 104 > var37 && (2 & Class38_Sub1.anIntArrayArrayArray2609[var8][var37 - -1][var36]) != 0; ++var37) {
                        }

                        label503:
                        while(var13 > 0) {
                           for(var15 = var11; var15 <= var37; ++var15) {
                              if(0 == (2 & Class38_Sub1.anIntArrayArrayArray2609[-1 + var13][var15][var36])) {
                                 break label503;
                              }
                           }

                           --var13;
                        }

                        label514:
                        while(var14 < 3) {
                           for(var15 = var11; var15 <= var37; ++var15) {
                              if((2 & Class38_Sub1.anIntArrayArrayArray2609[var14 + 1][var15][var36]) == 0) {
                                 break label514;
                              }
                           }

                           ++var14;
                        }

                        var15 = (-var11 + var37 - -1) * (-var13 + var14 - -1);
                        if(8 <= var15) {
                           var48 = 240;
                           var44 = Class44.anIntArrayArrayArray723[var14][var11][var36] - var48;
                           var18 = Class44.anIntArrayArrayArray723[var13][var11][var36];
                           Class167.method2263(2, var11 * 128, 128 * var37 + 128, 128 * var36, var36 * 128, var44, var18);

                           for(var19 = var13; var14 >= var19; ++var19) {
                              for(var20 = var11; var20 <= var37; ++var20) {
                                 Class38_Sub1.anIntArrayArrayArray2609[var19][var20][var36] = Class69.bitwiseAnd(Class38_Sub1.anIntArrayArrayArray2609[var19][var20][var36], -3);
                              }
                           }
                        }
                     }

                     if((4 & Class38_Sub1.anIntArrayArrayArray2609[var8][var10][var36]) != 0) {
                        var11 = var10;
                        var37 = var10;

                        for(var13 = var36; 0 < var13 && 0 != (4 & Class38_Sub1.anIntArrayArrayArray2609[var8][var10][-1 + var13]); --var13) {
                        }

                        for(var14 = var36; var14 < 104 && (Class38_Sub1.anIntArrayArrayArray2609[var8][var10][var14 + 1] & 4) != 0; ++var14) {
                        }

                        label554:
                        while(var11 > 0) {
                           for(var15 = var13; var14 >= var15; ++var15) {
                              if(0 == (Class38_Sub1.anIntArrayArrayArray2609[var8][var11 + -1][var15] & 4)) {
                                 break label554;
                              }
                           }

                           --var11;
                        }

                        label565:
                        while(var37 < 104) {
                           for(var15 = var13; var14 >= var15; ++var15) {
                              if(0 == (4 & Class38_Sub1.anIntArrayArrayArray2609[var8][1 + var37][var15])) {
                                 break label565;
                              }
                           }

                           ++var37;
                        }

                        if(4 <= (1 + -var11 + var37) * (var14 - (var13 - 1))) {
                           var15 = Class44.anIntArrayArrayArray723[var8][var11][var13];
                           Class167.method2263(4, var11 * 128, 128 * var37 - -128, var13 * 128, 128 + 128 * var14, var15, var15);

                           for(var16 = var11; var37 >= var16; ++var16) {
                              for(var44 = var13; var14 >= var44; ++var44) {
                                 Class38_Sub1.anIntArrayArrayArray2609[var8][var16][var44] = Class69.bitwiseAnd(Class38_Sub1.anIntArrayArrayArray2609[var8][var16][var44], -5);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

      } catch (RuntimeException var33) {
         throw Class44.clientError(var33, "di.K(" + (var0 != null?"{...}":"null") + ',' + var1 + ',' + var2 + ')');
      }
   }

   private synchronized void method2190() {
      try {
         if(this.anImageConsumer2978 != null) {
            this.anImageConsumer2978.setPixels(0, 0, this.anInt2012, this.anInt2011, this.aColorModel2979, this.anIntArray2007, 0, this.anInt2012);
            this.anImageConsumer2978.imageComplete(2);
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "di.L(" + 19661184 + ')');
      }
   }

   public final synchronized boolean isConsumer(ImageConsumer var1) {
      try {
         return this.anImageConsumer2978 == var1;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "di.isConsumer(" + (var1 != null?"{...}":"null") + ')');
      }
   }

   static boolean method2191(int var0, int var1, int var2, int var4, int var5, int var6, int var7, boolean var8, int var9, int var10, int var11) {
      try {
         int var12;
         int var13;
         for(var12 = 0; var12 < 104; ++var12) {
            for(var13 = 0; var13 < 104; ++var13) {
               Class84.anIntArrayArray1160[var12][var13] = 0;
               Class97.anIntArrayArray1373[var12][var13] = 99999999;
            }
         }

         var12 = var2;
         Class84.anIntArrayArray1160[var2][var10] = 99;
         var13 = var10;
         Class97.anIntArrayArray1373[var2][var10] = 0;
         byte var14 = 0;
         boolean var16 = false;
          int var15 = 0;
          Class3_Sub13_Sub38.anIntArray3456[var14] = var2;
          int var27 = var14 + 1;
          Class45.anIntArray729[var14] = var10;
          int[][] var17 = Class86.aClass91Array1182[WorldListCountry.localPlane].anIntArrayArray1304;

          int var18;
          while(var15 != var27) {
             var13 = Class45.anIntArray729[var15];
             var12 = Class3_Sub13_Sub38.anIntArray3456[var15];
             var15 = 4095 & var15 + 1;
             if(var12 == var0 && var13 == var4) {
                var16 = true;
                break;
             }

             if(var9 != 0) {
                if((var9 < 5 || 10 == var9) && Class86.aClass91Array1182[WorldListCountry.localPlane].method1488(var4, var12, var13, var0, var9 + -1, 1, var7)) {
                   var16 = true;
                   break;
                }

                if(var9 < 10 && Class86.aClass91Array1182[WorldListCountry.localPlane].method1492(var4, -1 + var9, var0, var13, 1, var7, var12, 95)) {
                   var16 = true;
                   break;
                }
             }

             if(var11 != 0 && 0 != var6 && Class86.aClass91Array1182[WorldListCountry.localPlane].method1498(var0, var13, var12, 1, var11, var1, var4, var6)) {
                var16 = true;
                break;
             }

             var18 = 1 + Class97.anIntArrayArray1373[var12][var13];
             if(0 < var12 && Class84.anIntArrayArray1160[var12 + -1][var13] == 0 && (19661064 & var17[var12 + -1][var13]) == 0) {
                Class3_Sub13_Sub38.anIntArray3456[var27] = -1 + var12;
                Class45.anIntArray729[var27] = var13;
                var27 = var27 - -1 & 4095;
                Class84.anIntArrayArray1160[-1 + var12][var13] = 2;
                Class97.anIntArrayArray1373[-1 + var12][var13] = var18;
             }

             if(103 > var12 && Class84.anIntArrayArray1160[var12 + 1][var13] == 0 && (var17[var12 + 1][var13] & 19661184) == 0) {
                Class3_Sub13_Sub38.anIntArray3456[var27] = var12 - -1;
                Class45.anIntArray729[var27] = var13;
                var27 = 1 + var27 & 4095;
                Class84.anIntArrayArray1160[var12 - -1][var13] = 8;
                Class97.anIntArrayArray1373[1 + var12][var13] = var18;
             }

             if(var13 > 0 && Class84.anIntArrayArray1160[var12][var13 - 1] == 0 && (19661058 & var17[var12][-1 + var13]) == 0) {
                Class3_Sub13_Sub38.anIntArray3456[var27] = var12;
                Class45.anIntArray729[var27] = -1 + var13;
                Class84.anIntArrayArray1160[var12][var13 - 1] = 1;
                var27 = var27 + 1 & 4095;
                Class97.anIntArrayArray1373[var12][-1 + var13] = var18;
             }

             if(103 > var13 && Class84.anIntArrayArray1160[var12][1 + var13] == 0 && (19661088 & var17[var12][var13 + 1]) == 0) {
                Class3_Sub13_Sub38.anIntArray3456[var27] = var12;
                Class45.anIntArray729[var27] = var13 - -1;
                var27 = 1 + var27 & 4095;
                Class84.anIntArrayArray1160[var12][1 + var13] = 4;
                Class97.anIntArrayArray1373[var12][var13 - -1] = var18;
             }

             if(var12 > 0 && var13 > 0 && Class84.anIntArrayArray1160[-1 + var12][var13 - 1] == 0 && (var17[var12 - 1][-1 + var13] & 19661070) == 0 && (19661064 & var17[var12 - 1][var13]) == 0 && (19661058 & var17[var12][-1 + var13]) == 0) {
                Class3_Sub13_Sub38.anIntArray3456[var27] = -1 + var12;
                Class45.anIntArray729[var27] = var13 + -1;
                var27 = 1 + var27 & 4095;
                Class84.anIntArrayArray1160[-1 + var12][-1 + var13] = 3;
                Class97.anIntArrayArray1373[var12 - 1][var13 + -1] = var18;
             }

             if(var12 < 103 && 0 < var13 && Class84.anIntArrayArray1160[var12 - -1][var13 - 1] == 0 && 0 == (19661187 & var17[var12 - -1][-1 + var13]) && (19661184 & var17[var12 - -1][var13]) == 0 && (19661058 & var17[var12][-1 + var13]) == 0) {
                Class3_Sub13_Sub38.anIntArray3456[var27] = var12 + 1;
                Class45.anIntArray729[var27] = -1 + var13;
                var27 = 4095 & var27 + 1;
                Class84.anIntArrayArray1160[1 + var12][var13 + -1] = 9;
                Class97.anIntArrayArray1373[var12 - -1][-1 + var13] = var18;
             }

             if(0 < var12 && var13 < 103 && 0 == Class84.anIntArrayArray1160[var12 + -1][var13 + 1] && 0 == (19661112 & var17[var12 + -1][1 + var13]) && 0 == (var17[var12 + -1][var13] & 19661064) && (19661088 & var17[var12][1 + var13]) == 0) {
                Class3_Sub13_Sub38.anIntArray3456[var27] = var12 - 1;
                Class45.anIntArray729[var27] = 1 + var13;
                Class84.anIntArrayArray1160[-1 + var12][var13 - -1] = 6;
                var27 = 4095 & 1 + var27;
                Class97.anIntArrayArray1373[-1 + var12][1 + var13] = var18;
             }

             if(var12 < 103 && var13 < 103 && Class84.anIntArrayArray1160[var12 - -1][1 + var13] == 0 && (19661280 & var17[1 + var12][var13 + 1]) == 0 && (var17[1 + var12][var13] & 19661184) == 0 && (19661088 & var17[var12][var13 - -1]) == 0) {
                Class3_Sub13_Sub38.anIntArray3456[var27] = var12 + 1;
                Class45.anIntArray729[var27] = var13 - -1;
                Class84.anIntArrayArray1160[var12 + 1][1 + var13] = 12;
                var27 = var27 - -1 & 4095;
                Class97.anIntArrayArray1373[1 + var12][var13 - -1] = var18;
             }
          }

          Class129.anInt1692 = 0;
          int var19;
          if(!var16) {
             if(!var8) {
                return false;
             }

             var18 = 1000;
             var19 = 100;
             byte var20 = 10;

             for(int var21 = var0 + -var20; var20 + var0 >= var21; ++var21) {
                for(int var22 = var4 + -var20; var4 - -var20 >= var22; ++var22) {
                   if(var21 >= 0 && var22 >= 0 && 104 > var21 && var22 < 104 && 100 > Class97.anIntArrayArray1373[var21][var22]) {
                      int var24 = 0;
                      if(var4 > var22) {
                         var24 = var4 + -var22;
                      } else if(var6 + var4 - 1 < var22) {
                         var24 = 1 + (-var4 - var6) + var22;
                      }

                      int var23 = 0;
                      if(var0 <= var21) {
                         if(-1 + var11 + var0 < var21) {
                            var23 = 1 - var11 - (var0 - var21);
                         }
                      } else {
                         var23 = var0 + -var21;
                      }

                      int var25 = var24 * var24 + var23 * var23;
                      if(var18 > var25 || var18 == var25 && Class97.anIntArrayArray1373[var21][var22] < var19) {
                         var13 = var22;
                         var18 = var25;
                         var12 = var21;
                         var19 = Class97.anIntArrayArray1373[var21][var22];
                      }
                   }
                }
             }

             if(var18 == 1000) {
                return false;
             }

             if(var2 == var12 && var10 == var13) {
                return false;
             }

             Class129.anInt1692 = 1;
          }

          byte var28 = 0;
          Class3_Sub13_Sub38.anIntArray3456[var28] = var12;
          var15 = var28 + 1;
          Class45.anIntArray729[var28] = var13;

          for(var18 = var19 = Class84.anIntArrayArray1160[var12][var13]; var2 != var12 || var13 != var10; var18 = Class84.anIntArrayArray1160[var12][var13]) {
             if(var19 != var18) {
                var19 = var18;
                Class3_Sub13_Sub38.anIntArray3456[var15] = var12;
                Class45.anIntArray729[var15++] = var13;
             }

             if((var18 & 2) == 0) {
                if(0 != (8 & var18)) {
                   --var12;
                }
             } else {
                ++var12;
             }

             if((1 & var18) == 0) {
                if(0 != (4 & var18)) {
                   --var13;
                }
             } else {
                ++var13;
             }
          }

          if(var15 > 0) {
             Class3_Sub13_Sub27.method299(100, var15, var5);
             return true;
          } else return var5 != 1;
      } catch (RuntimeException var26) {
         throw Class44.clientError(var26, "di.J(" + var0 + ',' + var1 + ',' + var2 + ',' + -1001 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + var9 + ',' + var10 + ',' + var11 + ')');
      }
   }

   final void method2179(Graphics var3) {
      try {

         this.method2190();
         var3.drawImage(this.anImage2009, 0, 0, this);
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "di.C(" + 0 + ',' + 0 + ',' + (var3 != null?"{...}":"null") + ',' + 0 + ')');
      }
   }

   final void method2185(int var1, int var3, Component var4) {
      try {
         this.anInt2011 = var1;
         this.anIntArray2007 = new int[var3 * var1 + 1];
         this.anInt2012 = var3;
         this.aColorModel2979 = new DirectColorModel(32, 16711680, '\uff00', 255);
         this.anImage2009 = var4.createImage(this);
         this.method2190();
         var4.prepareImage(this.anImage2009, this);
         this.method2190();
         var4.prepareImage(this.anImage2009, this);
         this.method2190();
         var4.prepareImage(this.anImage2009, this);
         this.method2182();

      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "di.F(" + var1 + ',' + false + ',' + var3 + ',' + (var4 != null?"{...}":"null") + ')');
      }
   }

   public final boolean imageUpdate(Image var1, int var2, int var3, int var4, int var5, int var6) {
      try {
         return true;
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "di.imageUpdate(" + (var1 != null?"{...}":"null") + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ')');
      }
   }

   static void method2192() {
      try {

         Class154.aClass93_1955.method1523((byte)-110);
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "di.M(" + -68 + ')');
      }
   }

   public final void requestTopDownLeftRightResend(ImageConsumer var1) {}

}
