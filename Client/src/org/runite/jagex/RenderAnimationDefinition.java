package org.runite.jagex;

final class RenderAnimationDefinition {

   private static RSString aClass94_354 = RSString.createRSString("Discard");
   static RSString aClass94_355 = RSString.createRSString("Lade Konfiguration )2 ");
   static int[] anIntArray356 = new int[]{1, 0, -1, 0};
   int anInt357 = 0;
   
   int[][] anIntArrayArray359;
   int anInt360 = -1;
   static RSString aClass94_361 = aClass94_354;
   static volatile int anInt362 = 0;
   private static RSString aClass94_363 = RSString.createRSString("Aug");
   int anInt364 = -1;
   private static RSString aClass94_365 = RSString.createRSString("May");
   private static RSString aClass94_366 = RSString.createRSString("Loaded world list data");
   int anInt367 = -1;
   int anInt368 = -1;
   int anInt369 = 0;
   int anInt370 = 0;
   int anInt371 = 0;
   int anInt372 = -1;
   int anInt373 = -1;
   static RSString aClass94_374 = aClass94_366;
   int anInt375 = -1;
   private static RSString aClass94_376 = RSString.createRSString("Jul");
   static int anInt377 = 0;
   static RSString aClass94_378 = null;
   int anInt379 = -1;
   static int anInt380;
   int anInt381 = 0;
   int anInt382 = -1;
   static byte[][][] aByteArrayArrayArray383;
   static int anInt384 = 0;
   private static RSString aClass94_385 = RSString.createRSString("Jun");
   int anInt386 = -1;
   int anInt387 = 0;
   private static RSString aClass94_388 = RSString.createRSString("Dec");
   int anInt389 = -1;
   int anInt390 = -1;
   private static RSString aClass94_391 = RSString.createRSString("Oct");
   private static RSString aClass94_392 = RSString.createRSString("Jan");
   int anInt393 = -1;
   private static RSString aClass94_394 = RSString.createRSString("Sep");
   int anInt395 = 0;
   static int anInt396;
   private static RSString aClass94_397 = RSString.createRSString("Nov");
   int anInt398 = -1;
   int anInt399 = 0;
   int anInt400 = 0;
   private static RSString aClass94_401 = RSString.createRSString("Apr");
   static boolean aBoolean402 = false;
   int anInt403 = 0;
   private static RSString aClass94_404 = RSString.createRSString("Mar");
   private static RSString aClass94_405 = RSString.createRSString("Feb");
   int anInt406 = -1;
   int anInt407 = -1;
   static RSString[] aClass94Array358 = new RSString[]{aClass94_392, aClass94_405, aClass94_404, aClass94_401, aClass94_365, aClass94_385, aClass94_376, aClass94_363, aClass94_394, aClass94_391, aClass94_397, aClass94_388};

   public static void method896(boolean var0) {
      try {
         aClass94_388 = null;
         aClass94_374 = null;
         aClass94_392 = null;
         aClass94_385 = null;
         aClass94_355 = null;
         aClass94_376 = null;
         aClass94_397 = null;
         anIntArray356 = null;
         aClass94_366 = null;
         aClass94Array358 = null;
         aClass94_378 = null;
         aClass94_354 = null;
         aClass94_365 = null;
         aClass94_394 = null;
         if(!var0) {
            getGraphicDefinition((byte)20, -109);
         }

         aClass94_361 = null;
         aClass94_391 = null;
         aClass94_401 = null;
         aClass94_405 = null;
         aClass94_363 = null;
         aByteArrayArrayArray383 = (byte[][][])null;
         aClass94_404 = null;
      } catch (RuntimeException var2) {
         throw Class44.method1067(var2, "ck.A(" + var0 + ')');
      }
   }

   static final boolean method897(int var0, Class3_Sub24_Sub4 var1, CacheIndex var2, CacheIndex var3, CacheIndex var4) {
      try {
         Class124.aClass153_1661 = var2;
         if(var0 != 17770) {
            aClass94_366 = (RSString)null;
         }

         Class40.aClass153_679 = var4;
         Class3_Sub28_Sub20.aClass153_3786 = var3;
         Class101.aClass3_Sub24_Sub4_1421 = var1;
         return true;
      } catch (RuntimeException var6) {
         throw Class44.method1067(var6, "ck.C(" + var0 + ',' + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ',' + (var3 != null?"{...}":"null") + ',' + (var4 != null?"{...}":"null") + ')');
      }
   }

   static final GraphicDefinition getGraphicDefinition(byte var0, int graphicId) {
      try {
         GraphicDefinition def = (GraphicDefinition)Class3_Sub31.aClass93_2604.get((long)graphicId, (byte)121);
         if(def != null) {
            return def;
         } else {
            byte[] var3 = Class3_Sub13_Sub18.aClass153_3214.getFile(InputStream_Sub1.method64(true, graphicId), (byte)-122, Class75.method1338(graphicId, var0 ^ 7));
            def = new GraphicDefinition();
            def.graphicId = graphicId;
            if(var0 != 42) {
               getGraphicDefinition((byte)-83, -12);
            }

            if(null != var3) {
               def.parse(new RSByteBuffer(var3), (byte)-113);
            }

            Class3_Sub31.aClass93_2604.put((byte)-109, def, (long)graphicId);
            return def;
         }
      } catch (RuntimeException var4) {
         throw Class44.method1067(var4, "ck.D(" + var0 + ',' + graphicId + ')');
      }
   }

   final void method899(int var1) {
      try {
         if(var1 <= 68) {
            method900((Class140_Sub4)null, -23);
         }

      } catch (RuntimeException var3) {
         throw Class44.method1067(var3, "ck.B(" + var1 + ')');
      }
   }

   static final void method900(Class140_Sub4 var0, int var1) {
      try {
         var0.aBoolean2810 = false;
         AnimationDefinition var2;
         if(-1 != var0.anInt2764) {
            var2 = Client.getAnimationDefinition(var0.anInt2764, (byte)-20);
            if(null != var2 && null != var2.frames) {
               ++var0.anInt2802;
               if(~var2.frames.length < ~var0.anInt2813 && var0.anInt2802 > var2.duration[var0.anInt2813]) {
                  var0.anInt2802 = 1;
                  ++var0.anInt2813;
                  ++var0.anInt2793;
                  IOHandler.method1470(var0.anInt2829, var2, 183921384, var0.anInt2819, var0 == Class102.player, var0.anInt2813);
               }

               if(~var0.anInt2813 <= ~var2.frames.length) {
                  var0.anInt2813 = 0;
                  var0.anInt2802 = 0;
                  IOHandler.method1470(var0.anInt2829, var2, 183921384, var0.anInt2819, Class102.player == var0, var0.anInt2813);
               }

               var0.anInt2793 = var0.anInt2813 - -1;
               if(~var0.anInt2793 <= ~var2.frames.length) {
                  var0.anInt2793 = 0;
               }
            } else {
               var0.anInt2764 = -1;
            }
         }

         int var6;
         if(~var0.anInt2842 != 0 && ~Class44.anInt719 <= ~var0.anInt2759) {
            var6 = getGraphicDefinition((byte)42, var0.anInt2842).anInt542;
            if(0 != ~var6) {
               AnimationDefinition var3 = Client.getAnimationDefinition(var6, (byte)-20);
               if(var3 != null && var3.frames != null) {
                  if(0 > var0.anInt2805) {
                     var0.anInt2805 = 0;
                     IOHandler.method1470(var0.anInt2829, var3, 183921384, var0.anInt2819, Class102.player == var0, 0);
                  }

                  ++var0.anInt2761;
                  if(var0.anInt2805 < var3.frames.length && ~var3.duration[var0.anInt2805] > ~var0.anInt2761) {
                     ++var0.anInt2805;
                     var0.anInt2761 = 1;
                     IOHandler.method1470(var0.anInt2829, var3, var1 ^ -183911469, var0.anInt2819, Class102.player == var0, var0.anInt2805);
                  }

                  if(var0.anInt2805 >= var3.frames.length) {
                     var0.anInt2842 = -1;
                  }

                  var0.anInt2826 = var0.anInt2805 - -1;
                  if(~var3.frames.length >= ~var0.anInt2826) {
                     var0.anInt2826 = -1;
                  }
               } else {
                  var0.anInt2842 = -1;
               }
            } else {
               var0.anInt2842 = -1;
            }
         }

         if(~var0.anInt2771 != 0 && -2 <= ~var0.anInt2828) {
            var2 = Client.getAnimationDefinition(var0.anInt2771, (byte)-20);
            if(~var2.anInt1866 == -2 && var0.anInt2811 > 0 && ~Class44.anInt719 <= ~var0.anInt2800 && Class44.anInt719 > var0.anInt2790) {
               var0.anInt2828 = 1;
               return;
            }
         }

         if(var1 == -11973) {
            if(~var0.anInt2771 != 0 && -1 == ~var0.anInt2828) {
               var2 = Client.getAnimationDefinition(var0.anInt2771, (byte)-20);
               if(var2 != null && var2.frames != null) {
                  ++var0.anInt2760;
                  if(~var0.anInt2832 > ~var2.frames.length && var0.anInt2760 > var2.duration[var0.anInt2832]) {
                     var0.anInt2760 = 1;
                     ++var0.anInt2832;
                     IOHandler.method1470(var0.anInt2829, var2, 183921384, var0.anInt2819, var0 == Class102.player, var0.anInt2832);
                  }

                  if(var2.frames.length <= var0.anInt2832) {
                     var0.anInt2832 -= var2.anInt1865;
                     ++var0.anInt2773;
                     if(~var0.anInt2773 > ~var2.anInt1861) {
                        if(var0.anInt2832 >= 0 && ~var2.frames.length < ~var0.anInt2832) {
                           IOHandler.method1470(var0.anInt2829, var2, var1 ^ -183911469, var0.anInt2819, Class102.player == var0, var0.anInt2832);
                        } else {
                           var0.anInt2771 = -1;
                        }
                     } else {
                        var0.anInt2771 = -1;
                     }
                  }

                  var0.anInt2776 = var0.anInt2832 + 1;
                  if(var0.anInt2776 >= var2.frames.length) {
                     var0.anInt2776 -= var2.anInt1865;
                     if(var2.anInt1861 > var0.anInt2773 + 1) {
                        if(0 > var0.anInt2776 || var0.anInt2776 >= var2.frames.length) {
                           var0.anInt2776 = -1;
                        }
                     } else {
                        var0.anInt2776 = -1;
                     }
                  }

                  var0.aBoolean2810 = var2.aBoolean1859;
               } else {
                  var0.anInt2771 = -1;
               }
            }

            if(0 < var0.anInt2828) {
               --var0.anInt2828;
            }

            for(var6 = 0; var0.aClass145Array2809.length > var6; ++var6) {
               Class145 var7 = var0.aClass145Array2809[var6];
               if(null != var7) {
                  if(~var7.anInt1900 >= -1) {
                     AnimationDefinition var4 = Client.getAnimationDefinition(var7.animationId, (byte)-20);
                     if(null != var4 && var4.frames != null) {
                        ++var7.anInt1897;
                        if(var7.anInt1893 < var4.frames.length && var7.anInt1897 > var4.duration[var7.anInt1893]) {
                           ++var7.anInt1893;
                           var7.anInt1897 = 1;
                           IOHandler.method1470(var0.anInt2829, var4, 183921384, var0.anInt2819, var0 == Class102.player, var7.anInt1893);
                        }

                        if(~var4.frames.length >= ~var7.anInt1893) {
                           ++var7.anInt1894;
                           var7.anInt1893 -= var4.anInt1865;
                           if(var4.anInt1861 > var7.anInt1894) {
                              if(-1 >= ~var7.anInt1893 && ~var7.anInt1893 > ~var4.frames.length) {
                                 IOHandler.method1470(var0.anInt2829, var4, 183921384, var0.anInt2819, Class102.player == var0, var7.anInt1893);
                              } else {
                                 var0.aClass145Array2809[var6] = null;
                              }
                           } else {
                              var0.aClass145Array2809[var6] = null;
                           }
                        }

                        var7.anInt1891 = 1 + var7.anInt1893;
                        if(var4.frames.length <= var7.anInt1891) {
                           var7.anInt1891 -= var4.anInt1865;
                           if(1 + var7.anInt1894 < var4.anInt1861) {
                              if(-1 < ~var7.anInt1891 || var4.frames.length <= var7.anInt1891) {
                                 var7.anInt1891 = -1;
                              }
                           } else {
                              var7.anInt1891 = -1;
                           }
                        }
                     } else {
                        var0.aClass145Array2809[var6] = null;
                     }
                  } else {
                     --var7.anInt1900;
                  }
               }
            }

         }
      } catch (RuntimeException var5) {
         throw Class44.method1067(var5, "ck.F(" + (var0 != null?"{...}":"null") + ',' + var1 + ')');
      }
   }

   final void parse(int var1, RSByteBuffer var2) {
      try {
         if(var1 != -1) {
            method900((Class140_Sub4)null, -18);
         }

         while(true) {
            int opcode = var2.getByte((byte)-34);
            if(-1 == ~opcode) {
               return;
            }

            this.parseOpcode(opcode, (byte)-106, var2);
         }
      } catch (RuntimeException var4) {
         throw Class44.method1067(var4, "ck.H(" + var1 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   private final void parseOpcode(int var1, byte var2, RSByteBuffer var3) {
      try {
         if(var1 == 1) {
            this.anInt368 = var3.getShort(1);
            this.anInt382 = var3.getShort(1);
            if(-65536 == ~this.anInt382) {
               this.anInt382 = -1;
            }

            if('\uffff' == this.anInt368) {
               this.anInt368 = -1;
            }
         } else if(-3 == ~var1) {
            this.anInt398 = var3.getShort(var2 ^ -105);
         } else if(-4 != ~var1) {
            if(4 == var1) {
               this.anInt406 = var3.getShort(1);
            } else if(var1 != 5) {
               if(6 != var1) {
                  if(7 == var1) {
                     this.anInt386 = var3.getShort(1);
                  } else if(var1 != 8) {
                     if(-10 != ~var1) {
                        if(var1 == 26) {
                           this.anInt395 = (short)(4 * var3.getByte((byte)-35));
                           this.anInt381 = (short)(4 * var3.getByte((byte)-110));
                        } else if(-28 != ~var1) {
                           if(-30 != ~var1) {
                              if(var1 != 30) {
                                 if(~var1 == -32) {
                                    this.anInt387 = var3.getByte((byte)-106);
                                 } else if(32 != var1) {
                                    if(33 == var1) {
                                       this.anInt400 = var3.getShort((byte)41);
                                    } else if(34 == var1) {
                                       this.anInt403 = var3.getByte((byte)-79);
                                    } else if(var1 != 35) {
                                       if(var1 != 36) {
                                          if(-38 != ~var1) {
                                             if(var1 == 38) {
                                                this.anInt367 = var3.getShort(1);
                                             } else if(39 == var1) {
                                                this.anInt407 = var3.getShort(1);
                                             } else if(var1 == 40) {
                                                this.anInt389 = var3.getShort(1);
                                             } else if(41 == var1) {
                                                this.anInt390 = var3.getShort(1);
                                             } else if(var1 == 42) {
                                                this.anInt364 = var3.getShort(1);
                                             } else if(-44 != ~var1) {
                                                if(~var1 != -45) {
                                                   if(~var1 == -46) {
                                                      var3.getShort(1);
                                                   }
                                                } else {
                                                   var3.getShort(1);
                                                }
                                             } else {
                                                var3.getShort(var2 ^ -105);
                                             }
                                          } else {
                                             this.anInt360 = var3.getByte((byte)-48);
                                          }
                                       } else {
                                          this.anInt371 = var3.getShort((byte)122);
                                       }
                                    } else {
                                       this.anInt399 = var3.getShort(1);
                                    }
                                 } else {
                                    this.anInt370 = var3.getShort(1);
                                 }
                              } else {
                                 this.anInt357 = var3.getShort(1);
                              }
                           } else {
                              this.anInt369 = var3.getByte((byte)-80);
                           }
                        } else {
                           if(this.anIntArrayArray359 == null) {
                              this.anIntArrayArray359 = new int[12][];
                           }

                           int var4 = var3.getByte((byte)-94);
                           this.anIntArrayArray359[var4] = new int[6];

                           for(int var5 = 0; var5 < 6; ++var5) {
                              this.anIntArrayArray359[var4][var5] = var3.getShort((byte)85);
                           }
                        }
                     } else {
                        this.anInt375 = var3.getShort(1);
                     }
                  } else {
                     this.anInt373 = var3.getShort(1);
                  }
               } else {
                  this.anInt393 = var3.getShort(1);
               }
            } else {
               this.anInt379 = var3.getShort(1);
            }
         } else {
            this.anInt372 = var3.getShort(var2 ^ -105);
         }

         if(var2 != -106) {
            this.parse(96, (RSByteBuffer)null);
         }

      } catch (RuntimeException var6) {
         throw Class44.method1067(var6, "ck.E(" + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ')');
      }
   }

   static final RSString method903(RSString[] var0, byte var1) {
      try {
         if(-3 >= ~var0.length) {
            if(var1 >= -57) {
               method897(-13, (Class3_Sub24_Sub4)null, (CacheIndex)null, (CacheIndex)null, (CacheIndex)null);
            }
            return Class67.method1261(0, var0.length, var0, 2774);
         } else {
            throw new IllegalArgumentException();
         }
      } catch (RuntimeException var3) {
         throw Class44.method1067(var3, "ck.G(" + (var0 != null?"{...}":"null") + ',' + var1 + ')');
      }
   }

}
