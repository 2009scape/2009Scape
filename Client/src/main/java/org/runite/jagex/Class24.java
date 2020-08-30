package org.runite.jagex;

import java.util.Objects;

final class Class24 {

   private short[] aShortArray460;
   private short[] aShortArray464;
   static RSString aClass94_465 = RSString.createRSString(" ");
   int anInt466 = -1;
   static int anInt467 = 0;
   static RSString aClass94_468 = RSString.createRSString("(U");
   static int anInt469 = 0;
   private short[] aShortArray470;
   private short[] aShortArray471;
   static int anInt472 = 0;
   private int[] anIntArray474;
   private final int[] anIntArray475 = new int[]{-1, -1, -1, -1, -1};
   boolean aBoolean476 = false;

   final Model_Sub1 method941() {
      try {
          int var3 = 0;
          Model_Sub1[] var2 = new Model_Sub1[5];

          for(int var4 = 0; var4 < 5; ++var4) {
             if(this.anIntArray475[var4] != -1) {
                var2[var3++] = Model_Sub1.method2015(Class10.aClass153_152, this.anIntArray475[var4]);
             }
          }

          Model_Sub1 var7 = new Model_Sub1(var2, var3);
          int var5;
          if(this.aShortArray464 != null) {
             for(var5 = 0; this.aShortArray464.length > var5; ++var5) {
                var7.method2016(this.aShortArray464[var5], this.aShortArray460[var5]);
             }
          }

          if(null != this.aShortArray471) {
             for(var5 = 0; var5 < this.aShortArray471.length; ++var5) {
                var7.method1998(this.aShortArray471[var5], this.aShortArray470[var5]);
             }
          }

          return var7;
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "dm.F(" + true + ')');
      }
   }

   final boolean method942() {
      try {
         if(null == this.anIntArray474) {
            return true;
         } else {
            boolean var2 = true;

             for(int var3 = 0; this.anIntArray474.length > var3; ++var3) {
               if(!Class10.aClass153_152.method2129((byte)-90, 0, this.anIntArray474[var3])) {
                  var2 = false;
               }
            }

            return var2;
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "dm.J(" + 101 + ')');
      }
   }

   public static void method943(int var0) {
      try {
         aClass94_468 = null;
         if(var0 == -9893) {
            aClass94_465 = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "dm.C(" + var0 + ')');
      }
   }

   static boolean isValidObjectMapping(byte var0, int var1, int var2, byte[] var3) {
      try {
         boolean var5 = true;
         int var7 = -1;
         RSByteBuffer buffer = new RSByteBuffer(var3);

    	 if (buffer.buffer.length == 0) {
//    		 System.out.println("No object mapping found!");
    		 return true;
    	 }
         while(true) {
      	   if (buffer.index == buffer.buffer.length) {
    		   return true;
    	   }
            int var8 = buffer.method773((byte)-121);
            if(0 == var8) {
               return var5;
            }

            int var9 = 0;
            var7 += var8;
            boolean var10 = false;

            while(true) {
               int var11;
               if(var10) {
                  var11 = buffer.getSmart();
                  if(var11 == 0) {
                     break;
                  }

                  buffer.getByteB();
               } else {
            	   if (buffer.index == buffer.buffer.length) {
            		   break;
            	   }
                  var11 = buffer.getSmart();
                  if(0 == var11) {
                     break;
                  }

                  var9 += var11 + -1;
                  int var12 = 63 & var9;
                  int var13 = (4088 & var9) >> 6;
                  int var16 = var2 + var12;
                  int var15 = var1 + var13;
                  int var14 = buffer.getByteB() >> 2;
                  if(var15 > 0 && var16 > 0 && 103 > var15 && 103 > var16) {
                     ObjectDefinition var17 = Class162.getObjectDefinition(var7);
                     if(var14 != 22 || KeyboardListener.aBoolean1905 || 0 != var17.SecondInt || var17.ClipType == 1 || var17.aBoolean1483) {
                        var10 = true;
                        if(!var17.hasModels()) {
                           var5 = false;
                           ++Class162.anInt2038;
                        }
                     }
                  }
               }
            }
         }
      } catch (RuntimeException var18) {
         throw Class44.clientError(var18, "dm.A(" + var0 + ',' + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ')');
      }
   }

   static void renderLocalNPCs() {
      try {
         GraphicDefinition.incomingBuffer.setBitAccess((byte)-98);
         int var1 = GraphicDefinition.incomingBuffer.getBits(8);
         int var2;
         if(var1 < Class163.localNPCCount) {
            for(var2 = var1; var2 < Class163.localNPCCount; ++var2) {
               Class3_Sub7.anIntArray2292[Class139.anInt1829++] = Class15.localNPCIndexes[var2];
            }
         }

         if(Class163.localNPCCount < var1) {
            throw new RuntimeException("gnpov1");
         } else {
            Class163.localNPCCount = 0;

            for(var2 = 0; var1 > var2; ++var2) {
               int var3 = Class15.localNPCIndexes[var2];
               NPC var4 = Class3_Sub13_Sub24.npcs[var3];
               int var5 = GraphicDefinition.incomingBuffer.getBits(1);
               if(0 == var5) {
                  Class15.localNPCIndexes[Class163.localNPCCount++] = var3;
                  var4.anInt2838 = Class44.anInt719;
               } else {
                  int var6 = GraphicDefinition.incomingBuffer.getBits(2);
                  if(var6 == 0) {
                     Class15.localNPCIndexes[Class163.localNPCCount++] = var3;
                     var4.anInt2838 = Class44.anInt719;
                     Class21.maskUpdateIndexes[Class66.maskUpdateCount++] = var3;
                  } else {
                     int var7;
                     int var8;
                     if(1 == var6) {
                        Class15.localNPCIndexes[Class163.localNPCCount++] = var3;
                        var4.anInt2838 = Class44.anInt719;
                        var7 = GraphicDefinition.incomingBuffer.getBits(3);
                        var4.walkStep(1, (byte)32, var7);
                        var8 = GraphicDefinition.incomingBuffer.getBits(1);
                        if(1 == var8) {
                           Class21.maskUpdateIndexes[Class66.maskUpdateCount++] = var3;
                        }
                     } else if (var6 == 2) {
                        Class15.localNPCIndexes[Class163.localNPCCount++] = var3;
                        var4.anInt2838 = Class44.anInt719;
                        if (GraphicDefinition.incomingBuffer.getBits(1) == 1) {
                           var7 = GraphicDefinition.incomingBuffer.getBits(3);
                           var4.walkStep(2, (byte) -122, var7);
                           var8 = GraphicDefinition.incomingBuffer.getBits(3);
                           var4.walkStep(2, (byte) 85, var8);
                        } else {
                           var7 = GraphicDefinition.incomingBuffer.getBits(3);
                           var4.walkStep(0, (byte) -80, var7);
                        }

                        var7 = GraphicDefinition.incomingBuffer.getBits(1);
                        if (var7 == 1) {
                           Class21.maskUpdateIndexes[Class66.maskUpdateCount++] = var3;
                        }
                     } else if (var6 == 3) {
                        Class3_Sub7.anIntArray2292[Class139.anInt1829++] = var3;
                     }
                  }
               }
            }

         }
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "dm.E(" + (byte) -11 + ')');
      }
   }

   private void method946(RSByteBuffer var2, int var3) {
      try {

          if(var3 == 1) {
            this.anInt466 = var2.getByteB();
         } else {
            int var4;
            int var5;
            if(var3 == 2) {
               var4 = var2.getByteB();
               this.anIntArray474 = new int[var4];

               for(var5 = 0; var4 > var5; ++var5) {
                  this.anIntArray474[var5] = var2.getShort();
               }
            } else if(var3 == 3) {
               this.aBoolean476 = true;
            } else if(var3 == 40) {
               var4 = var2.getByteB();
               this.aShortArray460 = new short[var4];
               this.aShortArray464 = new short[var4];

               for(var5 = 0; var5 < var4; ++var5) {
                  this.aShortArray464[var5] = (short)var2.getShort();
                  this.aShortArray460[var5] = (short)var2.getShort();
               }
            } else if (var3 == 41) {
               var4 = var2.getByteB();
               this.aShortArray471 = new short[var4];
               this.aShortArray470 = new short[var4];

               for (var5 = 0; var5 < var4; ++var5) {
                  this.aShortArray471[var5] = (short) var2.getShort();
                  this.aShortArray470[var5] = (short) var2.getShort();
               }
            } else if (var3 >= 60 && var3 < 70) {
               this.anIntArray475[-60 + var3] = var2.getShort();
            }
         }

      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "dm.K(" + (byte) -84 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

   final Model_Sub1 method947() {
      try {
         if(this.anIntArray474 == null) {
            return null;
         } else {
            Model_Sub1[] var2 = new Model_Sub1[this.anIntArray474.length];

            for(int var3 = 0; this.anIntArray474.length > var3; ++var3) {
               var2[var3] = Model_Sub1.method2015(Class10.aClass153_152, this.anIntArray474[var3]);
            }
            Model_Sub1 var7;
            if(var2.length == 1) {
               var7 = var2[0];
            } else {
               var7 = new Model_Sub1(var2, var2.length);
            }

            int var5;
            if(null != this.aShortArray464) {
               for(var5 = 0; var5 < this.aShortArray464.length; ++var5) {
                  Objects.requireNonNull(var7).method2016(this.aShortArray464[var5], this.aShortArray460[var5]);
               }
            }

            if(this.aShortArray471 != null) {
               for(var5 = 0; this.aShortArray471.length > var5; ++var5) {
                  Objects.requireNonNull(var7).method1998(this.aShortArray471[var5], this.aShortArray470[var5]);
               }
            }

            return var7;
         }
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "dm.H(" + (byte) -26 + ')');
      }
   }

   final boolean method948() {
      try {
         boolean var2 = true;

          for(int var3 = 0; var3 < 5; ++var3) {
            if(-1 != this.anIntArray475[var3] && !Class10.aClass153_152.method2129((byte)95, 0, this.anIntArray475[var3])) {
               var2 = false;
            }
         }

         return !var2;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "dm.B(" + 18991 + ')');
      }
   }

   static void method949(int var0, int var2, int var3, int var4) {
      try {
         int var6 = 0;
         Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var4], -var2 + var0, 100, var0 - -var2, var3);
         int var8 = -var2;
         int var7 = var2;
         int var9 = -1;

         while(var7 > var6) {
            ++var6;
            var9 += 2;
            var8 += var9;
            if(var8 >= 0) {
               --var7;
               var8 -= var7 << 1;
               int[] var10 = Class38.anIntArrayArray663[var4 - -var7];
               int[] var11 = Class38.anIntArrayArray663[var4 - var7];
               int var12 = var0 - -var6;
               int var13 = -var6 + var0;
               Class3_Sub13_Sub23_Sub1.method282(var10, var13, 115, var12, var3);
               Class3_Sub13_Sub23_Sub1.method282(var11, var13, 114, var12, var3);
            }

            int var16 = var7 + var0;
            int var15 = -var7 + var0;
            int[] var17 = Class38.anIntArrayArray663[var4 - -var6];
            int[] var18 = Class38.anIntArrayArray663[-var6 + var4];
            Class3_Sub13_Sub23_Sub1.method282(var17, var15, -61, var16, var3);
            Class3_Sub13_Sub23_Sub1.method282(var18, var15, -93, var16, var3);
         }

      } catch (RuntimeException var14) {
         throw Class44.clientError(var14, "dm.I(" + var0 + ',' + (byte) 118 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
      }
   }

   static void method950(RSInterface var0, int var1, int var2, int var3) {
      try {
         if(2 <= Class3_Sub13_Sub34.anInt3415 || Class164_Sub1.anInt3012 != 0 || GameObject.aBoolean1837) {
            RSString var4 = Class3_Sub28_Sub1.method531();
            if(var0 == null) {
               int var5 = Class168.aClass3_Sub28_Sub17_2096.method683(var4, 4 + var3, var2 - -15, Class3_Sub13_Sub7.aRandom3088, Class38_Sub1.anInt2618);
               Class75.method1340(4 + var3, Class168.aClass3_Sub28_Sub17_2096.method682(var4) + var5, var2, 15);
            } else {
               Class3_Sub28_Sub17 var7 = var0.method868(Class3_Sub13_Sub22.aClass109Array3270);
               if(null == var7) {
                  var7 = Class168.aClass3_Sub28_Sub17_2096;
               }

               var7.method702(var4, var3, var2, var0.anInt168, var0.anInt193, var0.anInt218, var0.anInt287, var0.anInt194, var0.anInt225, Class3_Sub13_Sub7.aRandom3088, Class38_Sub1.anInt2618, Player.anIntArray3951);
               Class75.method1340(Player.anIntArray3951[0], Player.anIntArray3951[2], Player.anIntArray3951[1], Player.anIntArray3951[3]);
            }

         }
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "dm.D(" + (var0 != null?"{...}":"null") + ',' + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   static void method951() {
      try {
         Class3_Sub28_Sub18.aBoolean3769 = false;
         Class3_Sub13_Sub34.anInt3413 = 0;
         Class158.anInt2005 = -3;
         Class50.anInt820 = 0;
         Class3_Sub13_Sub25.loginStage = 1;
         Class166.anInt2079 = 0;
         Class3_Sub26.anInt2561 = -1;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "dm.G(" + 0 + ')');
      }
   }

   final void method952(RSByteBuffer var2) {
      try {
          while(true) {
             int var3 = var2.getByteB();
             if(0 == var3) {
                return;
             }

             this.method946(var2, var3);
          }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "dm.L(" + -31957 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

}
