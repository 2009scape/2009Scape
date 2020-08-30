package org.runite.jagex;
import java.awt.Point;
import java.io.IOException;
import java.util.Objects;

final class Class3_Sub13_Sub13 extends Class3_Sub13 {

   private int anInt3147 = 4;
   static int anInt3148 = 0;
   private int anInt3149 = 4;
   static RSString aClass94_3152 = RSString.createRSString("::gc");
   static int anInt3153;
   static CacheIndex aClass153_3154;
   static int anInt3155;
   static int anInt3156 = -1;


   static void method229(int var0) {
      try {
         if(!Class163_Sub3.aBoolean3004) {
            var0 = -1;
         }

          if(var0 != Class65.anInt991) {
             if(var0 != -1) {
                Class55 var2 = Class3_Sub13_Sub29.method311(var0);
                Class3_Sub28_Sub16_Sub2 var3 = var2.method1179();
                if(null == var3) {
                   var0 = -1;
                } else {
                   Class38.aClass87_665.method1434(var3.method655(), 10000, var3.anInt3697, Class3_Sub28_Sub12.aCanvas3648, new Point(var2.anInt881, var2.anInt879), var3.anInt3706);
                   Class65.anInt991 = var0;
                }
             }

             if(var0 == -1 && Class65.anInt991 != -1) {
                Class38.aClass87_665.method1434((int[])null, 10000, -1, Class3_Sub28_Sub12.aCanvas3648, new Point(), -1);
                Class65.anInt991 = -1;
             }

          }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "gg.C(" + var0 + ',' + 20827 + ')');
      }
   }

   public Class3_Sub13_Sub13() {
      super(1, false);
   }

   final void method157(int var1, RSByteBuffer var2, boolean var3) {
      try {
         if(!var3) {
            anInt3148 = -117;
         }

         if(var1 == 0) {
            this.anInt3149 = var2.getByteB();
         } else if(1 == var1) {
            this.anInt3147 = var2.getByteB();
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "gg.A(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

   static void method230(int[][] var0) {
      try {
         Class38.anIntArrayArray663 = var0;

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "gg.Q(" + (var0 != null?"{...}":"null") + ',' + true + ')');
      }
   }

   static Class24 method231(int var0) {
      try {
         Class24 var2 = (Class24)Class140_Sub4.aClass93_2792.get((long)var0);
         if(var2 == null) {
            byte[] var3 = Class127.aClass153_1680.getFile(3, var0);
            var2 = new Class24();
            if(null != var3) {
               var2.method952(new RSByteBuffer(var3));
            }

            Class140_Sub4.aClass93_2792.put((byte)-89, var2, (long)var0);

         }
         return var2;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "gg.B(" + var0 + ',' + 0 + ')');
      }
   }

   static void method232(int var0) {
      try {
         if(Canvas_Sub2.loadInterface(var0)) {
            RSInterface[] var2 = GameObject.aClass11ArrayArray1834[var0];

            for(int var3 = 0; var3 < var2.length; ++var3) {
               RSInterface var4 = var2[var3];
               if(null != var4) {
                  var4.anInt260 = 1;
                  var4.anInt283 = 0;
                  var4.anInt267 = 0;
               }
            }

         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "gg.E(" + var0 + ',' + 16182 + ')');
      }
   }

   final int[] method154(int var1, byte var2) {
      try {
         int[] var10 = this.aClass114_2382.method1709(var1);
         if(this.aClass114_2382.aBoolean1580) {
            int var5 = Class113.anInt1559 / this.anInt3149;
            int var6 = Class101.anInt1427 / this.anInt3147;
            int[] var4;
            int var7;
            if(var6 <= 0) {
               var4 = this.method152(0, 0, 32755);
            } else {
               var7 = var1 % var6;
               var4 = this.method152(0, Class101.anInt1427 * var7 / var6, 32755);
            }

            for(var7 = 0; var7 < Class113.anInt1559; ++var7) {
               if(0 >= var5) {
                  var10[var7] = var4[0];
               } else {
                  int var8 = var7 % var5;
                  var10[var7] = var4[Class113.anInt1559 * var8 / var5];
               }
            }
         }

         return var10;
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "gg.D(" + var1 + ',' + var2 + ')');
      }
   }

   static void method233(int var0, CacheIndex var1) {
      try {
         if(var0 != 28280) {
            aClass153_3154 = (CacheIndex)null;
         }

         NPC.anInt4001 = var1.getArchiveForName(Class9.aClass94_119);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "gg.R(" + var0 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   public static void method234(int var0) {
      try {
         if(var0 != -3) {
            method233(-114, (CacheIndex)null);
         }

         aClass153_3154 = null;
         aClass94_3152 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "gg.O(" + var0 + ')');
      }
   }

   final int[][] method166(int var1, int var2) {
      try {
         if(var1 == -1) {
            int[][] var3 = this.aClass97_2376.method1594((byte)-123, var2);
            if(this.aClass97_2376.aBoolean1379) {
               int var5 = Class113.anInt1559 / this.anInt3149;
               int var6 = Class101.anInt1427 / this.anInt3147;
               int[][] var4;
               if(var6 > 0) {
                  int var7 = var2 % var6;
                  var4 = this.method162(var7 * Class101.anInt1427 / var6, 0, (byte)-109);
               } else {
                  var4 = this.method162(0, 0, (byte)-120);
               }

               int[] var17 = Objects.requireNonNull(var4)[0];
               int[] var9 = var4[2];
               int[] var10 = var3[0];
               int[] var8 = var4[1];
               int[] var11 = var3[1];
               int[] var12 = var3[2];

               for(int var13 = 0; Class113.anInt1559 > var13; ++var13) {
                  int var14;
                  if(var5 <= 0) {
                     var14 = 0;
                  } else {
                     int var15 = var13 % var5;
                     var14 = var15 * Class113.anInt1559 / var5;
                  }

                  var10[var13] = var17[var14];
                  var11[var13] = var8[var14];
                  var12[var13] = var9[var14];
               }
            }

            return var3;
         } else {
            return (int[][])((int[][])null);
         }
      } catch (RuntimeException var16) {
         throw Class44.clientError(var16, "gg.T(" + var1 + ',' + var2 + ')');
      }
   }

   static void method235() {
      try {
         if(Class159.anInt2023 > 0) {
            --Class159.anInt2023;
         }

         if(Class38_Sub1.anInt2617 > 1) {
            --Class38_Sub1.anInt2617;
            Class140_Sub6.anInt2905 = Class3_Sub13_Sub17.anInt3213;
         }

         if(Class3_Sub28_Sub18.aBoolean3769) {
            Class3_Sub28_Sub18.aBoolean3769 = false;
            Class3_Sub13_Sub24.method289();
         } else {
            int var1;
            for(var1 = 0; var1 < 100 && Class3_Sub13_Sub3.method181(); ++var1) {
            }

            if(Class143.loadingStage == 30) {
               Class163_Sub2_Sub1.method2226(Class3_Sub13_Sub1.outgoingBuffer, 163, -116);
               Object var14 = Class106.aClass67_1443.anObject1016;
               int var2;
               int var3;
               int var4;
               int var5;
               int var6;
               int var8;
               int var9;
               synchronized(var14) {
                  if(Canvas_Sub2.aBoolean29) {
                     if(Class3_Sub28_Sub11.anInt3644 != 0 || Class106.aClass67_1443.anInt1018 >= 40) {
                        Class3_Sub13_Sub1.outgoingBuffer.putOpcode(123);
                        var3 = 0;
                        Class3_Sub13_Sub1.outgoingBuffer.putByte((byte)-14, 0);
                        var2 = Class3_Sub13_Sub1.outgoingBuffer.index;

                        for(var4 = 0; Class106.aClass67_1443.anInt1018 > var4 && Class3_Sub13_Sub1.outgoingBuffer.index - var2 < 240; ++var4) {
                           ++var3;
                           var5 = Class106.aClass67_1443.anIntArray1019[var4];
                           var6 = Class106.aClass67_1443.anIntArray1020[var4];
                           if(var5 < 0) {
                              var5 = 0;
                           } else if(var5 > 65534) {
                              var5 = '\ufffe';
                           }

                           if(var6 >= 0) {
                              if('\ufffe' < var6) {
                                 var6 = '\ufffe';
                              }
                           } else {
                              var6 = 0;
                           }

                           boolean var7 = false;
                           if(Class106.aClass67_1443.anIntArray1019[var4] == -1 && Class106.aClass67_1443.anIntArray1020[var4] == -1) {
                              var7 = true;
                              var5 = -1;
                              var6 = -1;
                           }

                           if(Class155.anInt1977 == var6 && var5 == Canvas_Sub1.anInt14) {
                              if(2047 > Class3_Sub26.anInt2556) {
                                 ++Class3_Sub26.anInt2556;
                              }
                           } else {
                              var8 = -Class155.anInt1977 + var6;
                              Class155.anInt1977 = var6;
                              var9 = var5 + -Canvas_Sub1.anInt14;
                              Canvas_Sub1.anInt14 = var5;
                              if(Class3_Sub26.anInt2556 < 8 && var8 >= -32 && 31 >= var8 && -32 <= var9 && var9 <= 31) {
                                 var9 += 32;
                                 var8 += 32;
                                 Class3_Sub13_Sub1.outgoingBuffer.putShort(var9 + (Class3_Sub26.anInt2556 << 12) + (var8 << 6));
                                 Class3_Sub26.anInt2556 = 0;
                              } else if(Class3_Sub26.anInt2556 < 32 && var8 >= -128 && var8 <= 127 && var9 >= -128 && var9 <= 127) {
                                 Class3_Sub13_Sub1.outgoingBuffer.putByte((byte)-97, 128 - -Class3_Sub26.anInt2556);
                                 var9 += 128;
                                 var8 += 128;
                                 Class3_Sub13_Sub1.outgoingBuffer.putShort((var8 << 8) + var9);
                                 Class3_Sub26.anInt2556 = 0;
                              } else if(32 > Class3_Sub26.anInt2556) {
                                 Class3_Sub13_Sub1.outgoingBuffer.putByte((byte)-13, 192 - -Class3_Sub26.anInt2556);
                                 if(var7) {
                                    Class3_Sub13_Sub1.outgoingBuffer.putInt(-123, Integer.MIN_VALUE);
                                 } else {
                                    Class3_Sub13_Sub1.outgoingBuffer.putInt(-124, var6 | var5 << 16);
                                 }

                                 Class3_Sub26.anInt2556 = 0;
                              } else {
                                 Class3_Sub13_Sub1.outgoingBuffer.putShort(Class3_Sub26.anInt2556 + '\ue000');
                                 if(var7) {
                                    Class3_Sub13_Sub1.outgoingBuffer.putInt(-120, Integer.MIN_VALUE);
                                 } else {
                                    Class3_Sub13_Sub1.outgoingBuffer.putInt(-124, var6 | var5 << 16);
                                 }

                                 Class3_Sub26.anInt2556 = 0;
                              }
                           }
                        }

                        Class3_Sub13_Sub1.outgoingBuffer.method769((byte)-126, -var2 + Class3_Sub13_Sub1.outgoingBuffer.index);
                        if(var3 < Class106.aClass67_1443.anInt1018) {
                           Class106.aClass67_1443.anInt1018 -= var3;

                           for(var4 = 0; Class106.aClass67_1443.anInt1018 > var4; ++var4) {
                              Class106.aClass67_1443.anIntArray1020[var4] = Class106.aClass67_1443.anIntArray1020[var3 + var4];
                              Class106.aClass67_1443.anIntArray1019[var4] = Class106.aClass67_1443.anIntArray1019[var4 + var3];
                           }
                        } else {
                           Class106.aClass67_1443.anInt1018 = 0;
                        }
                     }
                  } else {
                     Class106.aClass67_1443.anInt1018 = 0;
                  }
               }

               if(Class3_Sub28_Sub11.anInt3644 != 0) {
                  long var15 = (-AbstractIndexedSprite.aLong1465 + Class75.aLong1102) / 50L;
                  var3 = Class38_Sub1.anInt2614;
                  if(var3 >= 0) {
                     if(var3 > '\uffff') {
                        var3 = '\uffff';
                     }
                  } else {
                     var3 = 0;
                  }

                  if(32767L < var15) {
                     var15 = 32767L;
                  }

                  var4 = Class163_Sub1.anInt2993;
                  AbstractIndexedSprite.aLong1465 = Class75.aLong1102;
                  byte var19 = 0;
                  if(var4 >= 0) {
                     if(var4 > '\uffff') {
                        var4 = '\uffff';
                     }
                  } else {
                     var4 = 0;
                  }

                  var6 = (int)var15;
                  if(Class3_Sub28_Sub11.anInt3644 == 2) {
                     var19 = 1;
                  }

                  Class3_Sub13_Sub1.outgoingBuffer.putOpcode(75);
                  Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var19 << 15 | var6);
                  Class3_Sub13_Sub1.outgoingBuffer.putIntB(-105, var4 | var3 << 16);
               }

               if(0 < Class3_Sub1.anInt2212) {
                  --Class3_Sub1.anInt2212;
               }

               if(Class15.aBoolean346) {
                  for(var1 = 0; Class3_Sub23.anInt2537 > var1; ++var1) {
                     var2 = Class133.inputTextCodeArray[var1];
                     if(98 == var2 || var2 == 99 || var2 == 96 || var2 == 97) {
                        Class3_Sub28_Sub10_Sub2.aBoolean4068 = true;
                        break;
                     }
                  }
               } else if(ObjectDefinition.aBooleanArray1490[96] || ObjectDefinition.aBooleanArray1490[97] || ObjectDefinition.aBooleanArray1490[98] || ObjectDefinition.aBooleanArray1490[99]) {
                  Class3_Sub28_Sub10_Sub2.aBoolean4068 = true;
               }

               if(Class3_Sub28_Sub10_Sub2.aBoolean4068 && 0 >= Class3_Sub1.anInt2212) {
                  Class3_Sub1.anInt2212 = 20;
                  Class3_Sub28_Sub10_Sub2.aBoolean4068 = false;
                  Class3_Sub13_Sub1.outgoingBuffer.putOpcode(21);
                  Class3_Sub13_Sub1.outgoingBuffer.putShortA(Class3_Sub9.anInt2309);
                  Class3_Sub13_Sub1.outgoingBuffer.putLEShort(GraphicDefinition.CAMERA_DIRECTION);
               }

               if(Class3_Sub13_Sub6.aBoolean3078 && !Class140_Sub4.aBoolean2774) {
                  Class140_Sub4.aBoolean2774 = true;
                  Class3_Sub13_Sub1.outgoingBuffer.putOpcode(22);
                  Class3_Sub13_Sub1.outgoingBuffer.putByte((byte)-41, 1);
               }

               if(!Class3_Sub13_Sub6.aBoolean3078 && Class140_Sub4.aBoolean2774) {
                  Class140_Sub4.aBoolean2774 = false;
                  Class3_Sub13_Sub1.outgoingBuffer.putOpcode(22);
                  Class3_Sub13_Sub1.outgoingBuffer.putByte((byte)-43, 0);
               }

               if(!Class140_Sub2.aBoolean2705) {
                  Class3_Sub13_Sub1.outgoingBuffer.putOpcode(98);
                  Class3_Sub13_Sub1.outgoingBuffer.putInt(-126, Class84.method1421());
                  Class140_Sub2.aBoolean2705 = true;
               }

               Class163_Sub1_Sub1.method2214();
               if(Class143.loadingStage == 30) {
                  MouseListeningClass.method2087();
                  Class115.method1713();
                  Class3_Sub8.method132((byte)-92);
                  ++Class3_Sub28_Sub16.anInt3699;
                  if(Class3_Sub28_Sub16.anInt3699 > 750) {
                     Class3_Sub13_Sub24.method289();
                  } else {
                     Class38.method1028();
                     Class60.method1207();
                     Class3_Sub13_Sub4.method189();
                     if(Class3_Sub28_Sub3.aClass11_3551 != null) {
                        Class9.method848();
                     }

                     for(var1 = Class3_Sub5.method115(true); var1 != -1; var1 = Class3_Sub5.method115(false)) {
                        Class46.method1087(40, var1);
                        Class44.anIntArray726[Class69.bitwiseAnd(Class36.anInt641++, 31)] = var1;
                     }

                     int nodeModelID;
                     for(Class3_Sub28_Sub6 var16 = Class73.method1302(); var16 != null; var16 = Class73.method1302()) {
                        var3 = var16.e(2063817568);
                        var4 = var16.f();
                        if(1 == var3) {
                           NPCDefinition.anIntArray1277[var4] = var16.anInt3598;
                           NPC.anIntArray3986[Class69.bitwiseAnd(31, PacketParser.anInt87++)] = var4;
                        } else if(var3 == 2) {
                           Class132.aClass94Array1739[var4] = var16.aClass94_3599;
                           Class163_Sub2_Sub1.anIntArray4025[Class69.bitwiseAnd(31, Class3_Sub9.anInt2317++)] = var4;
                        } else {
                           RSInterface var20;
                           if(var3 == 3) {
                              var20 = Class7.getRSInterface((byte)118, var4);
                              if(!var16.aClass94_3599.method1528(Objects.requireNonNull(var20).aClass94_232)) {
                                 var20.aClass94_232 = var16.aClass94_3599;
                                 Class20.method909(107, var20);
                              }
                           } else if (var3 == 4) {
                              var20 = Class7.getRSInterface((byte) 109, var4);
                              var6 = var16.anInt3598;
                              var8 = var16.anInt3596;
                              nodeModelID = var16.anInt3597;
                              if (Objects.requireNonNull(var20).modelType != var6 || nodeModelID != var20.itemId || var8 != var20.anInt265) {
                                 var20.itemId = nodeModelID;
                                 var20.anInt265 = var8;
                                 var20.modelType = var6;
                                 Class20.method909(120, var20);
                              }
                           } else if (var3 == 5) {
                              var20 = Class7.getRSInterface((byte) 117, var4);
                              if (var16.anInt3598 != Objects.requireNonNull(var20).animationId || var16.anInt3598 == -1) {
                                 var20.anInt260 = 1;
                                 var20.anInt267 = 0;
                                 var20.animationId = var16.anInt3598;
                                 var20.anInt283 = 0;
                                 Class20.method909(-117, var20);
                              }
                           } else if (var3 == 6) {
                              var5 = var16.anInt3598;
                              var6 = (32195 & var5) >> 10;
                              var8 = var5 & 31;
                              nodeModelID = (var5 & 1000) >> 5;
                              RSInterface var10 = Class7.getRSInterface((byte) 120, var4);
                              var9 = (var8 << 3) + (nodeModelID << 11) + (var6 << 19);
                              if (Objects.requireNonNull(var10).anInt218 != var9) {
                                 var10.anInt218 = var9;
                                 Class20.method909(123, var10);
                              }
                           } else if (var3 == 7) {
                              var20 = Class7.getRSInterface((byte) 124, var4);
                              boolean var24 = var16.anInt3598 == 1;
                              if (var20 != null && var24 == !var20.hidden) {
                                 var20.hidden = var24;
                                 Class20.method909(119, var20);
                              }
                           } else if (var3 == 8) {
                              var20 = Class7.getRSInterface((byte) 122, var4);
                              if (var16.anInt3598 != Objects.requireNonNull(var20).anInt182 || var20.anInt308 != var16.anInt3597 || var20.anInt164 != var16.anInt3596) {
                                 var20.anInt182 = var16.anInt3598;
                                 var20.anInt164 = var16.anInt3596;
                                 var20.anInt308 = var16.anInt3597;
                                 if (-1 != var20.anInt192) {
                                    if (var20.anInt184 <= 0) {
                                       if (var20.width > 0) {
                                          var20.anInt164 = 32 * var20.anInt164 / var20.width;
                                       }
                                    } else {
                                       var20.anInt164 = var20.anInt164 * 32 / var20.anInt184;
                                    }
                                 }

                                 Class20.method909(112, var20);
                              }
                           } else if (var3 == 9) {
                              var20 = Class7.getRSInterface((byte) 119, var4);
                              if (Objects.requireNonNull(var20).anInt192 != var16.anInt3598 || var20.anInt271 != var16.anInt3597) {
                                 var20.anInt192 = var16.anInt3598;
                                 var20.anInt271 = var16.anInt3597;
                                 Class20.method909(127, var20);
                              }
                           } else if (var3 == 10) {
                              var20 = Class7.getRSInterface((byte) 121, var4);
                              if (var16.anInt3598 != Objects.requireNonNull(var20).anInt258 || var20.anInt264 != var16.anInt3597 || var20.anInt280 != var16.anInt3596) {
                                 var20.anInt264 = var16.anInt3597;
                                 var20.anInt280 = var16.anInt3596;
                                 var20.anInt258 = var16.anInt3598;
                                 Class20.method909(-69, var20);
                              }
                           } else if (var3 == 11) {
                              var20 = Class7.getRSInterface((byte) 124, var4);
                              Objects.requireNonNull(var20).anInt306 = var20.x = var16.anInt3598;
                              var20.aByte273 = 0;
                              var20.aByte162 = 0;
                              var20.anInt210 = var20.y = var16.anInt3597;
                              Class20.method909(110, var20);
                           } else if (var3 == 12) {
                              var20 = Class7.getRSInterface((byte) 116, var4);
                              var6 = var16.anInt3598;
                              if (null != var20 && 0 == var20.type) {
                                 if (var6 > var20.anInt252 + -var20.anInt193) {
                                    var6 = var20.anInt252 + -var20.anInt193;
                                 }

                                 if (0 > var6) {
                                    var6 = 0;
                                 }

                                 if (var6 != var20.anInt208) {
                                    var20.anInt208 = var6;
                                    Class20.method909(-71, var20);
                                 }
                              }
                           } else if (var3 == 13) {
                              var20 = Class7.getRSInterface((byte) 124, var4);
                              Objects.requireNonNull(var20).anInt237 = var16.anInt3598;
                           }
                        }
                     }

                     if(Class36.anInt638 != 0) {
                        Class151_Sub1.anInt2958 += 20;
                        if(400 <= Class151_Sub1.anInt2958) {
                           Class36.anInt638 = 0;
                        }
                     }

                     ++Class106.anInt1446;
                     if(Class151.aClass11_1933 != null) {
                        ++Class3_Sub9.anInt2330;
                        if(15 <= Class3_Sub9.anInt2330) {
                           Class20.method909(-30, Class151.aClass11_1933);
                           Class151.aClass11_1933 = null;
                        }
                     }

                     RSInterface var17;
                     if(Class67.aClass11_1017 != null) {
                        Class20.method909(117, Class67.aClass11_1017);
                        if(Class126.anInt1676 > 5 + Class129_Sub1.anInt2693 || Class126.anInt1676 < -5 + Class129_Sub1.anInt2693 || Class130.anInt1709 > InputStream_Sub1.anInt40 + 5 || -5 + InputStream_Sub1.anInt40 > Class130.anInt1709) {
                           Class72.aBoolean1074 = true;
                        }

                        ++Class40.anInt677;
                        if(0 == Class3_Sub13_Sub5.anInt3069) {
                           if(Class72.aBoolean1074 && 5 <= Class40.anInt677) {
                              if(Class67.aClass11_1017 == Class99.aClass11_1402 && PacketParser.anInt86 != Class140_Sub2.anInt2701) {
                                 var17 = Class67.aClass11_1017;
                                 byte var18 = 0;
                                 if(1 == Canvas_Sub1.anInt15 && 206 == var17.anInt189) {
                                    var18 = 1;
                                 }

                                 if(var17.itemAmounts[Class140_Sub2.anInt2701] <= 0) {
                                    var18 = 0;
                                 }

                                 if(Client.method44(var17).method93()) {
                                    var5 = PacketParser.anInt86;
                                    var6 = Class140_Sub2.anInt2701;
                                    var17.itemAmounts[var6] = var17.itemAmounts[var5];
                                    var17.itemIds[var6] = var17.itemIds[var5];
                                    var17.itemAmounts[var5] = -1;
                                    var17.itemIds[var5] = 0;
                                 } else if (var18 == 1) {
                                    var6 = Class140_Sub2.anInt2701;
                                    var5 = PacketParser.anInt86;

                                    while (var6 != var5) {
                                       if (var5 > var6) {
                                          var17.method864(-1 + var5, var5, -71);
                                          --var5;
                                       } else {
                                          var17.method864(1 + var5, var5, -95);
                                          ++var5;
                                       }
                                    }
                                 } else {
                                    var17.method864(Class140_Sub2.anInt2701, PacketParser.anInt86, -93);
                                 }

                                 Class3_Sub13_Sub1.outgoingBuffer.putOpcode(231);
                                 Class3_Sub13_Sub1.outgoingBuffer.putShort(PacketParser.anInt86);
                                 Class3_Sub13_Sub1.outgoingBuffer.putLEInt(Class67.aClass11_1017.anInt279, (byte)-125);
                                 Class3_Sub13_Sub1.outgoingBuffer.putShortA(Class140_Sub2.anInt2701);
                                 Class3_Sub13_Sub1.outgoingBuffer.putByteS(var18);
                              }
                           } else if((Class66.anInt998 == 1 || Class3_Sub13_Sub39.method353(-1 + Class3_Sub13_Sub34.anInt3415, 0)) && Class3_Sub13_Sub34.anInt3415 > 2) {
                              Class132.method1801((byte)-116);
                           } else if(Class3_Sub13_Sub34.anInt3415 > 0) {
                              Class3_Sub13_Sub8.method203(56);
                           }

                           Class3_Sub28_Sub11.anInt3644 = 0;
                           Class3_Sub9.anInt2330 = 10;
                           Class67.aClass11_1017 = null;
                        }
                     }

                     Class85.aBoolean1167 = false;
                     Class27.aClass11_526 = null;
                     Class21.aBoolean440 = false;
                     Class3_Sub23.anInt2537 = 0;
                     var17 = Class107.aClass11_1453;
                     Class107.aClass11_1453 = null;
                     RSInterface var21 = Class20.aClass11_439;

                     for(Class20.aClass11_439 = null; Class3_Sub28_Sub10_Sub1.method591(72) && 128 > Class3_Sub23.anInt2537; ++Class3_Sub23.anInt2537) {
                        Class133.inputTextCodeArray[Class3_Sub23.anInt2537] = Class3_Sub28_Sub9.anInt3624;
                        Class120.anIntArray1638[Class3_Sub23.anInt2537] = Class3_Sub13_Sub27.anInt3342;
                     }

                     Class3_Sub28_Sub3.aClass11_3551 = null;
                     if(Class3_Sub28_Sub12.anInt3655 != -1) {
                        GraphicDefinition.method967(0, 0, 0, Class23.anInt454, Class3_Sub28_Sub12.anInt3655, 0, Class140_Sub7.anInt2934);
                     }

                     ++Class3_Sub13_Sub17.anInt3213;

                     while(true) {
                        CS2Script var26 = (CS2Script)PacketParser.aClass61_82.method1220();
                        RSInterface var23;
                        RSInterface var25;
                        if(var26 == null) {
                           while(true) {
                              var26 = (CS2Script)Class65.aClass61_983.method1220();
                              if(var26 == null) {
                                 while(true) {
                                    var26 = (CS2Script)Class110.aClass61_1471.method1220();
                                    if(var26 == null) {
                                       if(Class3_Sub28_Sub3.aClass11_3551 == null) {
                                          Class3_Sub19.anInt2475 = 0;
                                       }

                                       if(Class56.aClass11_886 != null) {
                                          PacketParser.method829();
                                       }

                                       if(Class3_Sub13_Sub26.rights > 0 && ObjectDefinition.aBooleanArray1490[82] && ObjectDefinition.aBooleanArray1490[81] && 0 != Class29.anInt561) {
                                          var5 = WorldListCountry.localPlane - Class29.anInt561;
                                          if(0 > var5) {
                                             var5 = 0;
                                          } else if(var5 > 3) {
                                             var5 = 3;
                                          }

                                          Class30.method979(Class102.player.anIntArray2767[0] + Class131.anInt1716, Class102.player.anIntArray2755[0] + Class82.anInt1152, var5);
                                       }

                                       if(Class3_Sub13_Sub26.rights > 0 && ObjectDefinition.aBooleanArray1490[82] && ObjectDefinition.aBooleanArray1490[81]) {
                                          if(-1 != Class27.anInt515) {
                                             Class30.method979(Class131.anInt1716 + Class27.anInt515, Class82.anInt1152 - -Class66.anInt999, WorldListCountry.localPlane);
                                          }

                                          ObjectDefinition.anInt1521 = 0;
                                          CS2Script.anInt2440 = 0;
                                       } else if(CS2Script.anInt2440 == 2) {
                                          if(Class27.anInt515 != -1) {
                                             Class3_Sub13_Sub1.outgoingBuffer.putOpcode(131);
                                             Class3_Sub13_Sub1.outgoingBuffer.putIntB(-123, Class3_Sub30_Sub1.anInt872);
                                             Class3_Sub13_Sub1.outgoingBuffer.putShortA(Class131.anInt1716 + Class27.anInt515);
                                             Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(RSInterface.anInt278);
                                             Class3_Sub13_Sub1.outgoingBuffer.putShortA(Class66.anInt999 + Class82.anInt1152);
                                             Class36.anInt638 = 1;
                                             Class151_Sub1.anInt2958 = 0;
                                             Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
                                             Class70.anInt1053 = Class163_Sub1.anInt2993;
                                          }

                                          CS2Script.anInt2440 = 0;
                                       } else if(2 == ObjectDefinition.anInt1521) {
                                          if(-1 != Class27.anInt515) {
                                             Class3_Sub13_Sub1.outgoingBuffer.putOpcode(179);
                                             Class3_Sub13_Sub1.outgoingBuffer.putShort(Class82.anInt1152 + Class66.anInt999);
                                             Class3_Sub13_Sub1.outgoingBuffer.putShort(Class27.anInt515 + Class131.anInt1716);
                                             Class151_Sub1.anInt2958 = 0;
                                             Class36.anInt638 = 1;
                                             Class70.anInt1053 = Class163_Sub1.anInt2993;
                                             Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
                                          }

                                          ObjectDefinition.anInt1521 = 0;
                                       } else if(-1 != Class27.anInt515 && 0 == CS2Script.anInt2440 && ObjectDefinition.anInt1521 == 0) {
                                          boolean var27 = Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 0, true, 0, 2, Class27.anInt515, 0, 0, 0, Class66.anInt999, Class102.player.anIntArray2767[0]);
                                          if(var27) {
                                             Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
                                             Class151_Sub1.anInt2958 = 0;
                                             Class70.anInt1053 = Class163_Sub1.anInt2993;
                                             Class36.anInt638 = 1;
                                          }
                                       }

                                       Class27.anInt515 = -1;
                                       Class163_Sub1.method2211(-48);
                                       if(Class107.aClass11_1453 != var17) {
                                          if(var17 != null) {
                                             Class20.method909(-52, var17);
                                          }

                                          if(null != Class107.aClass11_1453) {
                                             Class20.method909(119, Class107.aClass11_1453);
                                          }
                                       }

                                       if(var21 != Class20.aClass11_439 && Class75.anInt1109 == Class3_Sub13_Sub26.anInt3323) {
                                          if(null != var21) {
                                             Class20.method909(112, var21);
                                          }

                                          if(null != Class20.aClass11_439) {
                                             Class20.method909(-22, Class20.aClass11_439);
                                          }
                                       }

                                       if(Class20.aClass11_439 == null) {
                                          if(Class75.anInt1109 > 0) {
                                             --Class75.anInt1109;
                                          }
                                       } else if(Class75.anInt1109 < Class3_Sub13_Sub26.anInt3323) {
                                          ++Class75.anInt1109;
                                          if(Class3_Sub13_Sub26.anInt3323 == Class75.anInt1109) {
                                             Class20.method909(-48, Class20.aClass11_439);
                                          }
                                       }

                                       if(Class133.anInt1753 == 1) {
                                          KeyboardListener.method2086();
                                       } else if(Class133.anInt1753 == 2) {
                                          CS2Script.method379();
                                       } else {
                                          Class3_Sub28_Sub6.d('\uffff');
                                       }

                                       for(var5 = 0; var5 < 5; ++var5) {
                                          ++Class163_Sub1_Sub1.anIntArray4009[var5];
                                       }

                                       var5 = Class82.method1406();
                                       var6 = Class3_Sub13_Sub28.method301((byte)-119);
                                       if(var5 > 15000 && var6 > 15000) {
                                          Class159.anInt2023 = 250;
                                          Class23.method940(112, 14500);
                                          Class3_Sub13_Sub1.outgoingBuffer.putOpcode(245);
                                       }

                                       if(Class15.aClass64_351 != null && Class15.aClass64_351.anInt978 == 1) {
                                          if(null != Class15.aClass64_351.anObject974) {
                                             Class99.method1596(Class3_Sub13_Sub24.aClass94_3295, (byte)126, RSString.aBoolean2154);
                                          }

                                          Class3_Sub13_Sub24.aClass94_3295 = null;
                                          Class15.aClass64_351 = null;
                                          RSString.aBoolean2154 = false;
                                       }

                                       ++Class3_Sub13_Sub23_Sub1.anInt4032;
                                       ++Class43.anInt716;
                                       ++ClientErrorException.anInt2120;
                                       if(ClientErrorException.anInt2120 > 500) {
                                          ClientErrorException.anInt2120 = 0;
                                          nodeModelID = (int)(8.0D * Math.random());
                                          if((nodeModelID & 4) == 4) {
                                             Class3_Sub29.anInt2589 += Class128.anInt1682;
                                          }

                                          if((nodeModelID & 2) == 2) {
                                             InputStream_Sub1.anInt42 += Class3_Sub2.anInt2217;
                                          }

                                          if((nodeModelID & 1) == 1) {
                                             Class3_Sub13_Sub18.anInt3216 += Class146.anInt1901;
                                          }
                                       }

                                       if(Class43.anInt716 > 500) {
                                          Class43.anInt716 = 0;
                                          nodeModelID = (int)(8.0D * Math.random());
                                          if((1 & nodeModelID) == 1) {
                                             Class3_Sub13_Sub8.anInt3102 += OutputStream_Sub1.anInt48;
                                          }

                                          if((2 & nodeModelID) == 2) {
                                             Class164_Sub2.anInt3020 += Canvas_Sub1.anInt25;
                                          }
                                       }

                                       if(Class3_Sub13_Sub18.anInt3216 < -50) {
                                          Class146.anInt1901 = 2;
                                       }

                                       if(Class3_Sub13_Sub8.anInt3102 < -60) {
                                          OutputStream_Sub1.anInt48 = 2;
                                       }

                                       if(Class164_Sub2.anInt3020 < -20) {
                                          Canvas_Sub1.anInt25 = 1;
                                       }

                                       if(-55 > InputStream_Sub1.anInt42) {
                                          Class3_Sub2.anInt2217 = 2;
                                       }

                                       if(InputStream_Sub1.anInt42 > 55) {
                                          Class3_Sub2.anInt2217 = -2;
                                       }

                                       if(-40 > Class3_Sub29.anInt2589) {
                                          Class128.anInt1682 = 1;
                                       }

                                       if(Class3_Sub13_Sub18.anInt3216 > 50) {
                                          Class146.anInt1901 = -2;
                                       }

                                       if(Class3_Sub29.anInt2589 > 40) {
                                          Class128.anInt1682 = -1;
                                       }

                                       if(10 < Class164_Sub2.anInt3020) {
                                          Canvas_Sub1.anInt25 = -1;
                                       }

                                       if(60 < Class3_Sub13_Sub8.anInt3102) {
                                          OutputStream_Sub1.anInt48 = -2;
                                       }

                                       if(Class3_Sub13_Sub23_Sub1.anInt4032 > 50) {
                                          Class3_Sub13_Sub1.outgoingBuffer.putOpcode(93);
                                       }

                                       if(RenderAnimationDefinition.aBoolean402) {
                                          Class38.method1029(0);
                                          RenderAnimationDefinition.aBoolean402 = false;
                                       }

                                       try {
                                          if(Class3_Sub15.aClass89_2429 != null && Class3_Sub13_Sub1.outgoingBuffer.index > 0) {
                                             Class3_Sub15.aClass89_2429.sendBytes(Class3_Sub13_Sub1.outgoingBuffer.buffer, Class3_Sub13_Sub1.outgoingBuffer.index);
                                             Class3_Sub13_Sub23_Sub1.anInt4032 = 0;
                                             Class3_Sub13_Sub1.outgoingBuffer.index = 0;
                                          }
                                       } catch (IOException var11) {
                                          Class3_Sub13_Sub24.method289();
                                       }

                                       return;
                                    }

                                    var25 = var26.aClass11_2449;
                                    if(var25.anInt191 >= 0) {
                                       var23 = Class7.getRSInterface((byte)118, var25.parentId);
                                       if(null == var23 || var23.aClass11Array262 == null || var23.aClass11Array262.length <= var25.anInt191 || var25 != var23.aClass11Array262[var25.anInt191]) {
                                          continue;
                                       }
                                    }

                                    Class43.method1065(var26);
                                 }
                              }

                              var25 = var26.aClass11_2449;
                              if(var25.anInt191 >= 0) {
                                 var23 = Class7.getRSInterface((byte)120, var25.parentId);
                                 if(var23 == null || null == var23.aClass11Array262 || var23.aClass11Array262.length <= var25.anInt191 || var23.aClass11Array262[var25.anInt191] != var25) {
                                    continue;
                                 }
                              }

                              Class43.method1065(var26);
                           }
                        }

                        var25 = var26.aClass11_2449;
                        if(var25.anInt191 >= 0) {
                           var23 = Class7.getRSInterface((byte)127, var25.parentId);
                           if(var23 == null || var23.aClass11Array262 == null || var25.anInt191 >= var23.aClass11Array262.length || var23.aClass11Array262[var25.anInt191] != var25) {
                              continue;
                           }
                        }

                        Class43.method1065(var26);
                     }
                  }
               }
            }
         }
      } catch (RuntimeException var13) {
         throw Class44.clientError(var13, "gg.F(" + true + ')');
      }
   }

}
