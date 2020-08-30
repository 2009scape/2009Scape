package org.runite.jagex;

final class RenderAnimationDefinition {

   static int[] anIntArray356 = new int[]{1, 0, -1, 0};
   int anInt357 = 0;
   int[][] anIntArrayArray359;
   int anInt360 = -1;
   static volatile int anInt362 = 0;
   int anInt364 = -1;
   int anInt367 = -1;
   int anInt368 = -1;
   int anInt369 = 0;
   int anInt370 = 0;
   int anInt371 = 0;
   int anInt372 = -1;
   int anInt373 = -1;
   int anInt375 = -1;
   static int anInt377 = 0;
   static RSString aClass94_378 = null;
   int anInt379 = -1;
   static int anInt380;
   int anInt381 = 0;
   int anInt382 = -1;
   static byte[][][] aByteArrayArrayArray383;
   static int anInt384 = 0;
   int anInt386 = -1;
   int anInt387 = 0;
   int anInt389 = -1;
   int anInt390 = -1;
   int anInt393 = -1;
   int anInt395 = 0;
   static int anInt396;
   int anInt398 = -1;
   int anInt399 = 0;
   int anInt400 = 0;
   static boolean aBoolean402 = false;
   int anInt403 = 0;
   int anInt406 = -1;
   int anInt407 = -1;

   public static void method896(boolean var0) {
      try {
         anIntArray356 = null;
         aClass94_378 = null;
         if(!var0) {
            getGraphicDefinition((byte)20, -109);
         }
         aByteArrayArrayArray383 = (byte[][][])null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ck.A(" + ')');
      }
   }

   static void method897(int var0, Class3_Sub24_Sub4 var1, CacheIndex var2, CacheIndex var3, CacheIndex var4) {
      try {
         Class124.aClass153_1661 = var2;
         Class40.aClass153_679 = var4;
         Class3_Sub28_Sub20.aClass153_3786 = var3;
         Class101.aClass3_Sub24_Sub4_1421 = var1;
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "ck.C(" + var0 + ',' + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ',' + (var3 != null?"{...}":"null") + ',' + (var4 != null?"{...}":"null") + ')');
      }
   }

   static GraphicDefinition getGraphicDefinition(byte var0, int graphicId) {
      try {
         GraphicDefinition def = (GraphicDefinition)Class3_Sub31.aClass93_2604.get((long)graphicId);
         if(def == null) {
            byte[] var3 = Class3_Sub13_Sub18.aClass153_3214.getFile(InputStream_Sub1.method64(graphicId), Class75.method1338(graphicId, var0 ^ 7));
            def = new GraphicDefinition();
            def.graphicId = graphicId;
            if(var0 != 42) {
               getGraphicDefinition((byte)-83, -12);
            }

            if(null != var3) {
               def.parse(new RSByteBuffer(var3));
            }

            Class3_Sub31.aClass93_2604.put((byte)-109, def, (long)graphicId);
         }
         return def;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "ck.D(" + var0 + ',' + graphicId + ')');
      }
   }

   final void method899() {
      try {

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ck.B(" + 96 + ')');
      }
   }

   static void method900(Class140_Sub4 var0, int var1) {
      try {
         var0.aBoolean2810 = false;
         AnimationDefinition var2;
         if(-1 != var0.anInt2764) {
            var2 = Client.getAnimationDefinition(var0.anInt2764);
            if(null == var2.frames) {
               var0.anInt2764 = -1;
            } else {
               ++var0.anInt2802;
               if(var0.anInt2813 < var2.frames.length && var0.anInt2802 > var2.duration[var0.anInt2813]) {
                  var0.anInt2802 = 1;
                  ++var0.anInt2813;
                  ++var0.anInt2793;
                  IOHandler.method1470(var0.anInt2829, var2, 183921384, var0.anInt2819, var0 == Class102.player, var0.anInt2813);
               }

               if(var2.frames.length <= var0.anInt2813) {
                  var0.anInt2813 = 0;
                  var0.anInt2802 = 0;
                  IOHandler.method1470(var0.anInt2829, var2, 183921384, var0.anInt2819, Class102.player == var0, var0.anInt2813);
               }

               var0.anInt2793 = var0.anInt2813 - -1;
               if(var2.frames.length <= var0.anInt2793) {
                  var0.anInt2793 = 0;
               }
            }
         }

         int var6;
         if(var0.anInt2842 != -1 && var0.anInt2759 <= Class44.anInt719) {
            var6 = getGraphicDefinition((byte)42, var0.anInt2842).anInt542;
            if(var6 == -1) {
               var0.anInt2842 = -1;
            } else {
               AnimationDefinition var3 = Client.getAnimationDefinition(var6);
               if(var3.frames == null) {
                  var0.anInt2842 = -1;
               } else {
                  if(0 > var0.anInt2805) {
                     var0.anInt2805 = 0;
                     IOHandler.method1470(var0.anInt2829, var3, 183921384, var0.anInt2819, Class102.player == var0, 0);
                  }

                  ++var0.anInt2761;
                  if(var0.anInt2805 < var3.frames.length && var0.anInt2761 > var3.duration[var0.anInt2805]) {
                     ++var0.anInt2805;
                     var0.anInt2761 = 1;
                     IOHandler.method1470(var0.anInt2829, var3, var1 ^ -183911469, var0.anInt2819, Class102.player == var0, var0.anInt2805);
                  }

                  if(var0.anInt2805 >= var3.frames.length) {
                     var0.anInt2842 = -1;
                  }

                  var0.anInt2826 = var0.anInt2805 - -1;
                  if(var0.anInt2826 >= var3.frames.length) {
                     var0.anInt2826 = -1;
                  }
               }
            }
         }

         if(var0.anInt2771 != -1 && var0.anInt2828 <= 1) {
            var2 = Client.getAnimationDefinition(var0.anInt2771);
            if(var2.anInt1866 == 1 && var0.anInt2811 > 0 && var0.anInt2800 <= Class44.anInt719 && Class44.anInt719 > var0.anInt2790) {
               var0.anInt2828 = 1;
               return;
            }
         }

         if(var1 == -11973) {
            if(var0.anInt2771 != -1 && var0.anInt2828 == 0) {
               var2 = Client.getAnimationDefinition(var0.anInt2771);
               if(var2.frames == null) {
                  var0.anInt2771 = -1;
               } else {
                  ++var0.anInt2760;
                  if(var2.frames.length > var0.anInt2832 && var0.anInt2760 > var2.duration[var0.anInt2832]) {
                     var0.anInt2760 = 1;
                     ++var0.anInt2832;
                     IOHandler.method1470(var0.anInt2829, var2, 183921384, var0.anInt2819, var0 == Class102.player, var0.anInt2832);
                  }

                  if(var2.frames.length <= var0.anInt2832) {
                     var0.anInt2832 -= var2.anInt1865;
                     ++var0.anInt2773;
                     if(var2.anInt1861 > var0.anInt2773) {
                        if(var0.anInt2832 >= 0 && var0.anInt2832 < var2.frames.length) {
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
               }
            }

            if(0 < var0.anInt2828) {
               --var0.anInt2828;
            }

            for(var6 = 0; var0.aClass145Array2809.length > var6; ++var6) {
               Class145 var7 = var0.aClass145Array2809[var6];
               if(null != var7) {
                  if(var7.anInt1900 <= 0) {
                     AnimationDefinition var4 = Client.getAnimationDefinition(var7.animationId);
                     if(var4.frames == null) {
                        var0.aClass145Array2809[var6] = null;
                     } else {
                        ++var7.anInt1897;
                        if(var7.anInt1893 < var4.frames.length && var7.anInt1897 > var4.duration[var7.anInt1893]) {
                           ++var7.anInt1893;
                           var7.anInt1897 = 1;
                           IOHandler.method1470(var0.anInt2829, var4, 183921384, var0.anInt2819, var0 == Class102.player, var7.anInt1893);
                        }

                        if(var7.anInt1893 >= var4.frames.length) {
                           ++var7.anInt1894;
                           var7.anInt1893 -= var4.anInt1865;
                           if(var4.anInt1861 > var7.anInt1894) {
                              if(var7.anInt1893 >= 0 && var4.frames.length > var7.anInt1893) {
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
                              if(var7.anInt1891 < 0 || var4.frames.length <= var7.anInt1891) {
                                 var7.anInt1891 = -1;
                              }
                           } else {
                              var7.anInt1891 = -1;
                           }
                        }
                     }
                  } else {
                     --var7.anInt1900;
                  }
               }
            }

         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "ck.F(" + (var0 != null?"{...}":"null") + ',' + var1 + ')');
      }
   }

   final void parse(RSByteBuffer var2) {
      try {

         while(true) {
            int opcode = var2.getByteB();
            if(opcode == 0) {
               return;
            }

            this.parseOpcode(opcode, var2);
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "ck.H(" + -1 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   private void parseOpcode(int var1, RSByteBuffer var3) {
      try {
         if(var1 == 1) {
            this.anInt368 = var3.getShort();
            this.anInt382 = var3.getShort();
            if(this.anInt382 == 65535) {
               this.anInt382 = -1;
            }

            if('\uffff' == this.anInt368) {
               this.anInt368 = -1;
            }
         } else if(var1 == 2) {
            this.anInt398 = var3.getShort();
         } else if(var1 == 3) {
            this.anInt372 = var3.getShort();
         } else if (4 == var1) {
            this.anInt406 = var3.getShort();
         } else if (var1 == 5) {
            this.anInt379 = var3.getShort();
         } else if (6 == var1) {
            this.anInt393 = var3.getShort();
         } else if (7 == var1) {
            this.anInt386 = var3.getShort();
         } else if (var1 == 8) {
            this.anInt373 = var3.getShort();
         } else if (var1 == 9) {
            this.anInt375 = var3.getShort();
         } else if (var1 == 26) {
            this.anInt395 = (short) (4 * var3.getByteB());
            this.anInt381 = (short) (4 * var3.getByteB());
         } else if (var1 == 27) {
            if (this.anIntArrayArray359 == null) {
               this.anIntArrayArray359 = new int[12][];
            }

            int var4 = var3.getByteB();
            this.anIntArrayArray359[var4] = new int[6];

            for (int var5 = 0; var5 < 6; ++var5) {
               this.anIntArrayArray359[var4][var5] = var3.getShort((byte) 85);
            }
         } else if (var1 == 29) {
            this.anInt369 = var3.getByteB();
         } else if (var1 == 30) {
            this.anInt357 = var3.getShort();
         } else if (var1 == 31) {
            this.anInt387 = var3.getByteB();
         } else if (32 == var1) {
            this.anInt370 = var3.getShort();
         } else if (33 == var1) {
            this.anInt400 = var3.getShort((byte) 41);
         } else if (34 == var1) {
            this.anInt403 = var3.getByteB();
         } else if (var1 == 35) {
            this.anInt399 = var3.getShort();
         } else if (var1 == 36) {
            this.anInt371 = var3.getShort((byte) 122);
         } else if (var1 == 37) {
            this.anInt360 = var3.getByteB();
         } else if (var1 == 38) {
            this.anInt367 = var3.getShort();
         } else if (39 == var1) {
            this.anInt407 = var3.getShort();
         } else if (var1 == 40) {
            this.anInt389 = var3.getShort();
         } else if (41 == var1) {
            this.anInt390 = var3.getShort();
         } else if (var1 == 42) {
            this.anInt364 = var3.getShort();
         } else if (var1 == 43) {
            var3.getShort();
         } else if (var1 == 44) {
            var3.getShort();
         } else if (var1 == 45) {
            var3.getShort();
         }

      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "ck.E(" + var1 + ',' + (byte) -106 + ',' + (var3 != null?"{...}":"null") + ')');
      }
   }

   static RSString method903(RSString[] var0, byte var1) {
      try {
         if(var0.length >= 2) {
            if(var1 >= -57) {
               method897(-13, (Class3_Sub24_Sub4)null, (CacheIndex)null, (CacheIndex)null, (CacheIndex)null);
            }
            return Class67.method1261(0, var0.length, var0);
         } else {
            throw new IllegalArgumentException();
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ck.G(" + (var0 != null?"{...}":"null") + ',' + var1 + ')');
      }
   }

}
