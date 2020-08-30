package org.runite.jagex;


final class Class140_Sub3 extends GameObject {

   private int anInt2720 = 0;
   private boolean aBoolean2721 = false;
   private AnimationDefinition aClass142_2722;
   private final int anInt2724;
   private int anInt2725 = 0;
   private int anInt2726;
   static CacheIndex aClass153_2727;
   private boolean aBoolean2728 = true;
   private final int objectId;
   private final int anInt2730;
   private final int anInt2732;
   private int anInt2733;
   private final int type;
   static RSString aClass94_2735 = RSString.createRSString(")4");
   private final int anInt2736;
   static int anInt2737 = 0;
   private LDIndexedSprite aClass109_Sub1_2738 = null;
   private Class127_Sub1 aClass127_Sub1_2742;
   static volatile int anInt2743 = 0;
   static int anInt2745 = 0;
   private int anInt2746;
   static byte[][] aByteArrayArray2747 = new byte[50][];
   private int anInt2748 = 0;
   private int anInt2749;
   private int anInt2750 = -1;
   private int anInt2752 = -1;


   static Model method1957(int var0, boolean var1, AnimationDefinition var2, int var3, int var4, int var5, int var6, int var7, Model var8, int var9, int var10, int var11, int var12) {
      try {
         long var14 = ((long)var4 << 48) + (long)(var7 + (var0 << 16) - -(var12 << 24)) + ((long)var6 << 32);
         Model var16 = (Model)Class158_Sub1.aClass93_2982.get(var14);
         int var21;
         int var23;
         int var25;
         int var24;
         int var28;
         if(var16 == null) {
            byte var17;
            if(1 == var7) {
               var17 = 9;
            } else if(var7 == 2) {
               var17 = 12;
            } else if(var7 == 3) {
               var17 = 15;
            } else if (4 == var7) {
               var17 = 18;
            } else {
               var17 = 21;
            }

            int[] var19 = new int[]{64, 96, 128};
            byte var18 = 3;
            Model_Sub1 var20 = new Model_Sub1(1 + var18 * var17, -var17 + var17 * var18 * 2);
            var21 = var20.method2014(0, 0);
            int[][] var22 = new int[var18][var17];

            for(var23 = 0; var18 > var23; ++var23) {
               var24 = var19[var23];
               var25 = var19[var23];

               for(int var26 = 0; var26 < var17; ++var26) {
                  int var27 = (var26 << 11) / var17;
                  int var29 = var5 - -(Class51.anIntArray851[var27] * var25) >> 16;
                  var28 = var3 + Class51.anIntArray840[var27] * var24 >> 16;
                  var22[var23][var26] = var20.method2014(var28, var29);
               }
            }

            for(var23 = 0; var23 < var18; ++var23) {
               var24 = (256 * var23 - -128) / var18;
               var25 = 256 + -var24;
               byte var38 = (byte)(var12 * var24 + var0 * var25 >> 8);
               short var39 = (short)(((var6 & 127) * var25 + (127 & var4) * var24 & 32512) + (var25 * (var6 & 896) + var24 * (var4 & 896) & 229376) + (var24 * (var4 & '\ufc00') + ('\ufc00' & var6) * var25 & 16515072) >> 8);

               for(var28 = 0; var28 < var17; ++var28) {
                  if(var23 == 0) {
                     var20.method2005(var21, var22[0][(1 + var28) % var17], var22[0][var28], var39, var38);
                  } else {
                     var20.method2005(var22[var23 - 1][var28], var22[var23 + -1][(var28 + 1) % var17], var22[var23][(var28 - -1) % var17], var39, var38);
                     var20.method2005(var22[-1 + var23][var28], var22[var23][(1 + var28) % var17], var22[var23][var28], var39, var38);
                  }
               }
            }

            var16 = var20.method2008(64, 768, -50, -10, -50);
            Class158_Sub1.aClass93_2982.put((byte)-125, var16, var14);
         }

         int var32 = var7 * 64 + -1;
         int var33 = -var32;
         int var31 = -var32;
         int var34 = var32;
         int var35 = var8.method1884();
         Class3_Sub28_Sub5 var40 = null;
         var23 = var8.method1883();
         var24 = var8.method1898();
         var25 = var8.method1872();
         if(var2 != null) {
            var10 = var2.frames[var10];
            int frame = var10 >> 16;
            //              	 System.out.println(var2.animId  + " Roar " + (var10 >> 16) + ", " + (var10 & '\uffff') + ", " + Arrays.toString(var2.frames));
            var40 = Class3_Sub9.method133(frame); //NPC render animating
            var10 &= '\uffff';
         }

         var21 = var32;
         if(var1) {
            if(1664 < var9 || 384 > var9) {
               var31 -= 128;
            }

            if(var9 > 1152 && var9 < 1920) {
               var34 = var32 + 128;
            }

            if(640 < var9 && var9 < 1408) {
               var21 = var32 + 128;
            }

            if(var9 > 128 && var9 < 896) {
               var33 -= 128;
            }
         }

         if(var21 < var25) {
            var25 = var21;
         }

         if(var33 > var35) {
            var35 = var33;
         }

         if(var24 < var31) {
            var24 = var31;
         }

         if(var34 < var23) {
            var23 = var34;
         }

         if(null == var40) {
            var16 = var16.method1882(true, true, true);
            var16.resize((var23 + -var35) / 2, 128, (var25 - var24) / 2);
            var16.method1897((var35 + var23) / 2, 0, (var24 - -var25) / 2);
         } else {
            var16 = var16.method1882(!var40.method559(var10), !var40.method561(var10, (byte)115), true);
            var16.resize((var23 + -var35) / 2, 128, (var25 + -var24) / 2);
            var16.method1897((var35 + var23) / 2, 0, (var24 + var25) / 2);
            var16.method1877(var40, var10);
         }

         if(var9 != 0) {
            var16.method1876(var9);
         }

         if(HDToolKit.highDetail) {
            Class140_Sub1_Sub1 var36 = (Class140_Sub1_Sub1)var16;
            if(var11 != Class121.method1736(WorldListCountry.localPlane, (byte) -49 ^ -50, var3 - -var35, var24 + var5) || var11 != Class121.method1736(WorldListCountry.localPlane, 1, var23 + var3, var5 - -var25)) {
               for(var28 = 0; var28 < var36.anInt3823; ++var28) {
                  var36.anIntArray3845[var28] += -var11 + Class121.method1736(WorldListCountry.localPlane, Class93.bitwiseXOR((byte) -49, -50), var36.anIntArray3822[var28] - -var3, var5 + var36.anIntArray3848[var28]);
               }

               var36.aClass6_3835.aBoolean98 = false;
               var36.aClass121_3839.aBoolean1640 = false;
            }
         } else {
            Class140_Sub1_Sub2 var37 = (Class140_Sub1_Sub2)var16;
            if(var11 != Class121.method1736(WorldListCountry.localPlane, 1, var3 - -var35, var5 - -var24) || var11 != Class121.method1736(WorldListCountry.localPlane, 1, var3 + var23, var5 - -var25)) {
               for(var28 = 0; var28 < var37.anInt3891; ++var28) {
                  var37.anIntArray3883[var28] += -var11 + Class121.method1736(WorldListCountry.localPlane, 1, var3 + var37.anIntArray3885[var28], var5 + var37.anIntArray3895[var28]);
               }

               var37.aBoolean3897 = false;
            }
         }

         return var16;
      } catch (RuntimeException var30) {
         throw Class44.clientError(var30, "dc.E(" + var0 + ',' + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + (var8 != null?"{...}":"null") + ',' + var9 + ',' + var10 + ',' + var11 + ',' + var12 + ',' + (byte) -49 + ')');
      }
   }

   public static void method1958(int var0) {
      try {
         aClass153_2727 = null;
         if(var0 != 2) {
            method1958(64);
         }

         aByteArrayArray2747 = (byte[][])null;
         aClass94_2735 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "dc.G(" + var0 + ')');
      }
   }

   final void method1867(int var1, int var2, int var3, int var4, int var5) {
      try {
         if(HDToolKit.highDetail) {
            this.method1962(true);
         } else {
            this.method1961(var5, var4);
         }

      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "dc.IB(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
      }
   }

   static void method1959(boolean var3) {
      try {
         Class3_Sub24_Sub4.anInt3507 = 2;
         RSString.aBoolean2150 = var3;
         Class21.anInt443 = 22050;
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "dc.D(" + 256 + ',' + 2 + ',' + 22050 + ',' + var3 + ')');
      }
   }

   final void animate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, long var9, int var11, Class127_Sub1 var12) {
      try {
         GameObject var13 = this.method1963();
         if(null != var13) {
            var13.animate(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11, this.aClass127_Sub1_2742);
         }
      } catch (RuntimeException var14) {
         throw Class44.clientError(var14, "dc.IA(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + var9 + ',' + var11 + ',' + (var12 != null?"{...}":"null") + ')');
      }
   }

   final void method1960() {
      try {
         if(this.aClass109_Sub1_2738 != null) {
            Class141.method2047(this.aClass109_Sub1_2738, this.anInt2725, this.anInt2720, this.anInt2748);
         }

         this.anInt2750 = -1;
         this.anInt2752 = -1;
         this.aClass109_Sub1_2738 = null;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "dc.F(" + -1 + ')');
      }
   }

   protected final void finalize() {}

   private void method1961(int var1, int var2) {
      try {
         if(this.aClass142_2722 != null) {
            int var4 = Class44.anInt719 - this.anInt2749;
            if(var4 > 100 && this.aClass142_2722.anInt1865 > 0) {
               int var5;
               for(var5 = this.aClass142_2722.frames.length - this.aClass142_2722.anInt1865; var5 > this.anInt2726 && var4 > this.aClass142_2722.duration[this.anInt2726]; ++this.anInt2726) {
                  var4 -= this.aClass142_2722.duration[this.anInt2726];
               }

               if(var5 <= this.anInt2726) {
                  int var6 = 0;

                  for(int var7 = var5; var7 < this.aClass142_2722.frames.length; ++var7) {
                     var6 += this.aClass142_2722.duration[var7];
                  }

                  var4 %= var6;
               }

               this.anInt2733 = 1 + this.anInt2726;
               if(this.anInt2733 >= this.aClass142_2722.frames.length) {
                  this.anInt2733 -= this.aClass142_2722.anInt1865;
                  if(this.anInt2733 < 0 || this.aClass142_2722.frames.length <= this.anInt2733) {
                     this.anInt2733 = -1;
                  }
               }
            }

            while(this.aClass142_2722.duration[this.anInt2726] < var4) {
               IOHandler.method1470(var1, this.aClass142_2722, 183921384, var2, false, this.anInt2726);
               var4 -= this.aClass142_2722.duration[this.anInt2726];
               ++this.anInt2726;
               if(this.anInt2726 >= this.aClass142_2722.frames.length) {
                  this.anInt2726 -= this.aClass142_2722.anInt1865;
                  if(0 > this.anInt2726 || this.anInt2726 >= this.aClass142_2722.frames.length) {
                     this.aClass142_2722 = null;
                     break;
                  }
               }

               this.anInt2733 = this.anInt2726 - -1;
               if(this.anInt2733 >= this.aClass142_2722.frames.length) {
                  this.anInt2733 -= this.aClass142_2722.anInt1865;
                  if(this.anInt2733 < 0 || this.aClass142_2722.frames.length <= this.anInt2733) {
                     this.anInt2733 = -1;
                  }
               }
            }

            this.anInt2746 = var4;
            this.anInt2749 = -var4 + Class44.anInt719;
         }

      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "dc.A(" + var1 + ',' + var2 + ',' + -101 + ')');
      }
   }

   private GameObject method1962(boolean var1) {
      try {
         boolean var3 = Class58.anIntArrayArrayArray914 != Class44.anIntArrayArrayArray723;
         ObjectDefinition var4 = Class162.getObjectDefinition(this.objectId);
         int var5 = var4.animationId;
         if(null != var4.ChildrenIds) {
            var4 = var4.method1685(0);
         }

         if(null == var4) {
            if(HDToolKit.highDetail && !var3) {
               this.method1960();
            }

            return null;
         } else {
            int var6;
            if(Class158.anInt2014 != 0 && this.aBoolean2721 && (null == this.aClass142_2722 || var4.animationId != this.aClass142_2722.animId)) {
               var6 = var4.animationId;
               if(var4.animationId == -1) {
                  var6 = var5;
               }

               if(var6 == -1) {
                  this.aClass142_2722 = null;
               } else {
                  this.aClass142_2722 = Client.getAnimationDefinition(var6);
               }

               if(null != this.aClass142_2722) {
                  if(var4.aBoolean1492 && -1 != this.aClass142_2722.anInt1865) {
                     this.anInt2726 = (int)(Math.random() * (double)this.aClass142_2722.frames.length);
                     this.anInt2749 -= (int)(Math.random() * (double)this.aClass142_2722.duration[this.anInt2726]);
                  } else {
                     this.anInt2726 = 0;
                     this.anInt2749 = Class44.anInt719 + -1;
                  }
               }
            }

            var6 = this.anInt2724 & 3;
            int var7;
            int var8;
            if(var6 == 1 || var6 == 3) {
               var8 = var4.SizeX;
               var7 = var4.SizeY;
            } else {
               var7 = var4.SizeX;
               var8 = var4.SizeY;
            }

            int var10 = this.anInt2736 - -(1 + var7 >> 1);
            int var9 = (var7 >> 1) + this.anInt2736;
            int var11 = (var8 >> 1) + this.anInt2730;
            int var12 = (var8 - -1 >> 1) + this.anInt2730;
            this.method1961(128 * var11, var9 * 128);
            boolean var13 = !var3 && var4.aBoolean1503 && (var4.objectId != this.anInt2750 || (this.anInt2752 != this.anInt2726 || this.aClass142_2722 != null && (this.aClass142_2722.aBoolean1872 || Class3_Sub26.aBoolean2558) && this.anInt2733 != this.anInt2726) && Class80.anInt1137 >= 2);
            if(var1 && !var13) {
               return null;
            } else {
               int[][] var14 = Class44.anIntArrayArrayArray723[this.anInt2732];
               int var15 = var14[var10][var12] + var14[var9][var12] + var14[var9][var11] + var14[var10][var11] >> 2;
               int var16 = (var7 << 6) + (this.anInt2736 << 7);
               int var17 = (var8 << 6) + (this.anInt2730 << 7);
               int[][] var18 = (int[][])null;
               if(var3) {
                  var18 = Class58.anIntArrayArrayArray914[0];
               } else if (this.anInt2732 < 3) {
                  var18 = Class44.anIntArrayArrayArray723[1 + this.anInt2732];
               }

               if(HDToolKit.highDetail && var13) {
                  Class141.method2047(this.aClass109_Sub1_2738, this.anInt2725, this.anInt2720, this.anInt2748);
               }

               boolean var19 = null == this.aClass109_Sub1_2738;
               Class136 var20;
               if(this.aClass142_2722 == null) {
                  var20 = var4.method1696(this.anInt2724, var16, var14, this.type, var15, var18, false, var19?Class75_Sub1.aClass109_Sub1_2631:this.aClass109_Sub1_2738, (byte)-128, var13, var17);
               } else {
                  var20 = var4.method1697(var17, var16, !var19?this.aClass109_Sub1_2738:Class75_Sub1.aClass109_Sub1_2631, var15, this.aClass142_2722, this.anInt2724, var14, var13, this.anInt2726, -2 ^ -8310, var18, this.anInt2733, this.type, this.anInt2746);
               }

               if(null == var20) {
                  return null;
               } else {
                  if(HDToolKit.highDetail && var13) {
                     if(var19) {
                        Class75_Sub1.aClass109_Sub1_2631 = var20.aClass109_Sub1_1770;
                     }

                     int var21 = 0;
                     if(this.anInt2732 != 0) {
                        int[][] var22 = Class44.anIntArrayArrayArray723[0];
                        var21 = var15 - (var22[var10][var11] + var22[var9][var11] - (-var22[var9][var12] - var22[var10][var12]) >> 2);
                     }

                     LDIndexedSprite var24 = var20.aClass109_Sub1_1770;
                     if(this.aBoolean2728 && Class141.method2049(var24, var16, var21, var17)) {
                        this.aBoolean2728 = false;
                     }

                     if(!this.aBoolean2728) {
                        Class141.method2051(var24, var16, var21, var17);
                        this.aClass109_Sub1_2738 = var24;
                        this.anInt2748 = var17;
                        if(var19) {
                           Class75_Sub1.aClass109_Sub1_2631 = null;
                        }

                        this.anInt2720 = var21;
                        this.anInt2725 = var16;
                     }

                     this.anInt2750 = var4.objectId;
                     this.anInt2752 = this.anInt2726;
                  }

                  return var20.aClass140_1777;
               }
            }
         }
      } catch (RuntimeException var23) {
         throw Class44.clientError(var23, "dc.H(" + var1 + ',' + -2 + ')');
      }
   }

   final GameObject method1963() {
      try {
         return this.method1962(false);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "dc.C(" + 3 + ')');
      }
   }

   final int method1871() {
      return -32768;
   }

   static void renderLocalPlayers() {
      try {
         int localPlayerAmount = GraphicDefinition.incomingBuffer.getBits(8);
         int var2;
         if(localPlayerAmount < Class159.localPlayerCount) {
            for(var2 = localPlayerAmount; Class159.localPlayerCount > var2; ++var2) {
               Class3_Sub7.anIntArray2292[Class139.anInt1829++] = Class56.localPlayerIndexes[var2];
            }
         }

         if(Class159.localPlayerCount >= localPlayerAmount) {
            Class159.localPlayerCount = 0;
            var2 = 0;

            for(; localPlayerAmount > var2; ++var2) {
               int var3 = Class56.localPlayerIndexes[var2];
               Player var4 = Class3_Sub13_Sub22.players[var3];
               int update = GraphicDefinition.incomingBuffer.getBits(1);
               if(update == 0) {
                  Class56.localPlayerIndexes[Class159.localPlayerCount++] = var3;
                  var4.anInt2838 = Class44.anInt719;
               } else {
                  int type = GraphicDefinition.incomingBuffer.getBits(2);
                  if(type == 0) {
                     Class56.localPlayerIndexes[Class159.localPlayerCount++] = var3;
                     var4.anInt2838 = Class44.anInt719;
                     Class21.maskUpdateIndexes[Class66.maskUpdateCount++] = var3;
                  } else {
                     int var7;
                     int var8;
                     if(type == 1) {
                        Class56.localPlayerIndexes[Class159.localPlayerCount++] = var3;
                        var4.anInt2838 = Class44.anInt719;
                        var7 = GraphicDefinition.incomingBuffer.getBits(3);
                        var4.walkStep(1, (byte)46, var7);
                        var8 = GraphicDefinition.incomingBuffer.getBits(1);
                        if(var8 == 1) {
                           Class21.maskUpdateIndexes[Class66.maskUpdateCount++] = var3;
                        }
                     } else if (type == 2) {
                        Class56.localPlayerIndexes[Class159.localPlayerCount++] = var3;
                        var4.anInt2838 = Class44.anInt719;
                        if (GraphicDefinition.incomingBuffer.getBits(1) == 1) {
                           var7 = GraphicDefinition.incomingBuffer.getBits(3);
                           var4.walkStep(2, (byte) -92, var7);
                           var8 = GraphicDefinition.incomingBuffer.getBits(3);
                           var4.walkStep(2, (byte) 88, var8);
                        } else {
                           var7 = GraphicDefinition.incomingBuffer.getBits(3);
                           var4.walkStep(0, (byte) 113, var7);
                        }

                        var7 = GraphicDefinition.incomingBuffer.getBits(1);
                        if (1 == var7) {
                           Class21.maskUpdateIndexes[Class66.maskUpdateCount++] = var3;
                        }
                     } else if (type == 3) {
                        Class3_Sub7.anIntArray2292[Class139.anInt1829++] = var3;
                     }
                  }
               }
            }

         } else {
            throw new RuntimeException("gppov1");
         }
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "dc.B(" + false + ')');
      }
   }

   Class140_Sub3(int objectId, int type, int var3, int var4, int var5, int var6, int animationId, boolean var8, GameObject var9) {
      try {
         this.anInt2732 = var4;
         this.anInt2724 = var3;
         this.anInt2736 = var5;
         this.type = type;
         this.objectId = objectId;
         this.anInt2730 = var6;
         ObjectDefinition var10;
         if(HDToolKit.highDetail && null != var9) {
            if(var9 instanceof Class140_Sub3) {
               ((Class140_Sub3)var9).method1960();
            } else {
               var10 = Class162.getObjectDefinition(this.objectId);
               if(var10.ChildrenIds != null) {
                  var10 = var10.method1685(0);
               }

               if(null != var10) {
                  Class8.method840(var10, (byte)-76, 0, this.anInt2724, 0, this.type, this.anInt2736, this.anInt2730, this.anInt2732);
               }
            }
         }

         if(-1 != animationId) {
            this.aClass142_2722 = Client.getAnimationDefinition(animationId);
            this.anInt2726 = 0;
            if(1 >= this.aClass142_2722.frames.length) {
               this.anInt2733 = 0;
            } else {
               this.anInt2733 = 1;
            }

            this.anInt2746 = 1;
            this.anInt2749 = -1 + Class44.anInt719;
            if(this.aClass142_2722.anInt1845 == 0 && null != var9 && var9 instanceof Class140_Sub3) {
               Class140_Sub3 var12 = (Class140_Sub3)var9;
               if(this.aClass142_2722 == var12.aClass142_2722) {
                  this.anInt2726 = var12.anInt2726;
                  this.anInt2749 = var12.anInt2749;
                  this.anInt2746 = var12.anInt2746;
                  this.anInt2733 = var12.anInt2733;
                  return;
               }
            }

            if(var8 && this.aClass142_2722.anInt1865 != -1) {
               this.anInt2726 = (int)(Math.random() * (double)this.aClass142_2722.frames.length);
               this.anInt2733 = this.anInt2726 - -1;
               if(this.aClass142_2722.frames.length <= this.anInt2733) {
                  this.anInt2733 -= this.aClass142_2722.anInt1865;
                  if(this.anInt2733 < 0 || this.aClass142_2722.frames.length <= this.anInt2733) {
                     this.anInt2733 = -1;
                  }
               }

               this.anInt2746 = 1 - -((int)(Math.random() * (double)this.aClass142_2722.duration[this.anInt2726]));
               this.anInt2749 = -this.anInt2746 + Class44.anInt719;
            }
         }

         if(HDToolKit.highDetail && var9 != null) {
            this.method1962(true);
         }

         if(var9 == null) {
            var10 = Class162.getObjectDefinition(this.objectId);
            if(null != var10.ChildrenIds) {
               this.aBoolean2721 = true;
            }
         }

      } catch (RuntimeException var11) {
         throw Class44.clientError(var11, "dc.<init>(" + objectId + ',' + type + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + animationId + ',' + var8 + ',' + (var9 != null?"{...}":"null") + ')');
      }
   }

}
