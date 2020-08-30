package org.runite.jagex;

import java.util.Objects;

final class Player extends Class140_Sub4 {

   static int[] anIntArray3951 = new int[4];
   int anInt3952 = -1;
   static byte aByte3953;
   static int[] anIntArray3954 = new int[100];
   int headIcon = -1;
   int teamId = 0;
   private int anInt3958 = 0;
   static int[] anIntArray3959 = new int[2];
   int COMBAT_LEVEL = 0;
   static RSString aClass94_3961 = RSString.createRSString("Forced tweening / animation smoothing ENABLED(Q");
   Class52 class52;
   int anInt3963 = -1;
   int combatLevel = 0;
   int anInt3966 = -1;
   RSString displayName;
   boolean aBoolean3968 = false;
   int anInt3969 = 0;
   int anInt3970 = -1;
   int skullIcon = -1;
   int anInt3973 = -1;
   int anInt3974 = 0;


   final int getSize() {
      try {
         if(null == this.class52 || this.class52.pnpcId == -1) {

            return super.getSize();
         } else {
            return Node.method522(this.class52.pnpcId).size;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "e.H(" + (byte) 114 + ')');
      }
   }

   final int getRenderAnimationId() {
      try {

         return this.renderAnimationId;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "e.B(" + -1 + ')');
      }
   }

   final void parseAppearance(int var1, RSByteBuffer buffer) {
      try {
         buffer.index = 0;
         int var3 = buffer.getByteB();
         int npcId = -1;
         int var4 = 1 & var3;
         boolean var6 = (var3 & 4) != 0;
         int var7 = super.getSize();
         int[] look = new int[12];
         this.setSize(1 + (var3 >> 3 & 7), 2);
         this.anInt3958 = 3 & var3 >> 6;
         this.anInt2819 += (-var7 + this.getSize()) * 64;
         this.anInt2829 += 64 * (this.getSize() + -var7);
         this.skullIcon = buffer.getByte();
         this.headIcon = buffer.getByte();
         this.teamId = 0;

         int var11;
         int var12;
         int outfit;
         int var14;
         for(int var10 = 0; var10 < 12; ++var10) {
            var11 = buffer.getByteB();
            if(var11 == 0) {
               look[var10] = 0;
            } else {
               var12 = buffer.getByteB();
               outfit = (var11 << 8) + var12;
               if(var10 == 0 && outfit == 65535) {
                  npcId = buffer.getShort();
                  this.teamId = buffer.getByteB();
                  break;
               }

               if('\u8000' <= outfit) {
            	   int equipId = outfit - '\u8000';
            	   if (equipId > Class75_Sub4.anIntArray2664.length) {
            		   System.err.println("Player->parseAppearance()-> Array length = " + Class75_Sub4.anIntArray2664.length + ", equipId=" + equipId + ", item def size=" + Class3_Sub13_Sub23.itemDefinitionSize);
            		  continue;
            	   }
                  outfit = Class75_Sub4.anIntArray2664[equipId];
                  look[var10] = Class3_Sub13_Sub29.bitwiseOr(1073741824, outfit);
                  var14 = Class38.getItemDefinition(outfit, (byte)119).teamId;
                  if(var14 != 0) {
                     this.teamId = var14;
                  }
               } else {
                  look[var10] = Class3_Sub13_Sub29.bitwiseOr(-256 + outfit, Integer.MIN_VALUE);
               }
            }
         }

         int[] colors = new int[5];

         for(var11 = 0; var11 < 5; ++var11) {
            var12 = buffer.getByteB();
            if(var12 < 0 || var12 >= Class15.aShortArrayArray344[var11].length) {
               var12 = 0;
            }

            colors[var11] = var12;
         }

         this.renderAnimationId = buffer.getShort();
         long var20 = buffer.getLong(-99);
         this.displayName = Objects.requireNonNull(Class41.method1052(var20)).method1545();
         this.COMBAT_LEVEL = buffer.getByteB();
         if(var6) {
            this.anInt3974 = buffer.getShort();
            this.combatLevel = this.COMBAT_LEVEL;
            this.anInt3970 = -1;
         } else {
            this.anInt3974 = 0;
            this.combatLevel = buffer.getByteB();
            this.anInt3970 = buffer.getByteB();
            if(this.anInt3970 == 255) {
               this.anInt3970 = -1;
            }
         }

         outfit = this.anInt3969;
         this.anInt3969 = buffer.getByteB();
         if(this.anInt3969 == 0) {
            Class162.method2203(this);
         } else {
            int var15 = this.anInt3966;
            int var16 = this.anInt3963;
            int var17 = this.anInt3973;
            var14 = this.anInt3952;
            this.anInt3952 = buffer.getShort();
            this.anInt3966 = buffer.getShort();
            this.anInt3963 = buffer.getShort();
            this.anInt3973 = buffer.getShort();
            if(this.anInt3969 != outfit || var14 != this.anInt3952 || var15 != this.anInt3966 || var16 != this.anInt3963 || var17 != this.anInt3973) {
               Node.method518(this);
            }
         }

         if(null == this.class52) {
            this.class52 = new Class52();
         }

         var14 = this.class52.pnpcId;
         this.class52.method1161(colors, npcId, var4 == 1, look, this.renderAnimationId);
         if(npcId != var14) {
            this.anInt2819 = 128 * this.anIntArray2767[0] + this.getSize() * 64;
            this.anInt2829 = 128 * this.anIntArray2755[0] - -(64 * this.getSize());
         }
      } catch (RuntimeException var18) {
         throw Class44.clientError(var18, "e.P(" + var1 + ',' + (buffer != null?"{...}":"null") + ')');
      }
   }

   final void animate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, long var9, int var11, Class127_Sub1 var12) {
      try {
         if(this.class52 != null) {
            AnimationDefinition var13 = this.anInt2771 != -1 && 0 == this.anInt2828?Client.getAnimationDefinition(this.anInt2771):null;
            AnimationDefinition var14 = this.anInt2764 != -1 && !this.aBoolean3968 && (this.method1965().anInt368 != this.anInt2764 || var13 == null)?Client.getAnimationDefinition(this.anInt2764):null;
            Model var15 = this.class52.method1165(this.aClass145Array2809, this.anInt2776, var14, var13, this.anInt2802, this.anInt2793, -120, this.anInt2760, this.anInt2832, this.anInt2813);
            int var16 = Class118.method1727((byte)123);
            if(HDToolKit.highDetail && Class3_Sub24_Sub3.anInt3492 < 96 && var16 > 50) {
               Class3_Sub1.method90(1);
            }

            int var17;
            if(0 != Class3_Sub13_Sub13.anInt3148 && var16 < 50) {
               for(var17 = 50 - var16; Class56.anInt893 < var17; ++Class56.anInt893) {
                  Class3_Sub6.aByteArrayArray2287[Class56.anInt893] = new byte[102400];
               }

               while(Class56.anInt893 > var17) {
                  --Class56.anInt893;
                  Class3_Sub6.aByteArrayArray2287[Class56.anInt893] = null;
               }
            }

            if(var15 != null) {
               this.anInt2820 = var15.method1871();
               Model var23;
               if(Class140_Sub6.aBoolean2910 && (-1 == this.class52.pnpcId || Node.method522(this.class52.pnpcId).aBoolean1249)) {
                  var23 = Class140_Sub3.method1957(160, this.aBoolean2810, var14 == null?var13:var14, this.anInt2819, 0, this.anInt2829, 0, 1, var15, var1, null != var14?this.anInt2813:this.anInt2832, this.anInt2831, 240);
                  if(HDToolKit.highDetail) {
                     float var18 = HDToolKit.method1852();
                     float var19 = HDToolKit.method1839();
                     HDToolKit.method1851();
                     HDToolKit.method1825(var18, -150.0F + var19);
                     var23.animate(0, var2, var3, var4, var5, var6, var7, var8, -1L, var11, (Class127_Sub1)null);
                     HDToolKit.method1830();
                     HDToolKit.method1825(var18, var19);
                  } else {
                     var23.animate(0, var2, var3, var4, var5, var6, var7, var8, -1L, var11, (Class127_Sub1)null);
                  }
               }

               if(Class102.player == this) {
                  for(var17 = ClientErrorException.aClass96Array2114.length + -1; var17 >= 0; --var17) {
                     Class96 var27 = ClientErrorException.aClass96Array2114[var17];
                     if(var27 != null && var27.anInt1355 != -1) {
                        int var21;
                        int var20;
                        if(var27.anInt1360 == 1 && 0 <= var27.anInt1359 && var27.anInt1359 < Class3_Sub13_Sub24.npcs.length) {
                           NPC var24 = Class3_Sub13_Sub24.npcs[var27.anInt1359];
                           if(null != var24) {
                              var20 = var24.anInt2819 / 32 - Class102.player.anInt2819 / 32;
                              var21 = -(Class102.player.anInt2829 / 32) + var24.anInt2829 / 32;
                              this.method1979((Class127_Sub1)null, var21, var15, var20, var6, var11, var1, var8, var5, var4, var2, var27.anInt1355, var3, var7);
                           }
                        }

                        if(var27.anInt1360 == 2) {
                           int var29 = 4 * (-Class131.anInt1716 + var27.anInt1356) + 2 + -(Class102.player.anInt2819 / 32);
                           var20 = 2 + (4 * (var27.anInt1347 - Class82.anInt1152) - Class102.player.anInt2829 / 32);
                           this.method1979((Class127_Sub1)null, var20, var15, var29, var6, var11, var1, var8, var5, var4, var2, var27.anInt1355, var3, var7);
                        }

                        if(var27.anInt1360 == 10 && var27.anInt1359 >= 0 && var27.anInt1359 < Class3_Sub13_Sub22.players.length) {
                           Player var28 = Class3_Sub13_Sub22.players[var27.anInt1359];
                           if(null != var28) {
                              var20 = -(Class102.player.anInt2819 / 32) + var28.anInt2819 / 32;
                              var21 = var28.anInt2829 / 32 + -(Class102.player.anInt2829 / 32);
                              this.method1979((Class127_Sub1)null, var21, var15, var20, var6, var11, var1, var8, var5, var4, var2, var27.anInt1355, var3, var7);
                           }
                        }
                     }
                  }
               }

               this.method1971(var15, (byte)-103);
               this.method1969((byte)110, var15, var1);
               var23 = null;
               if(!this.aBoolean3968 && this.anInt2842 != -1 && this.anInt2805 != -1) {
                  GraphicDefinition var26 = RenderAnimationDefinition.getGraphicDefinition((byte)42, this.anInt2842);
                  var23 = var26.method966(this.anInt2826, this.anInt2805, this.anInt2761);
                  if(var23 != null) {
                     var23.method1897(0, -this.anInt2799, 0);
                     if(var26.aBoolean536) {
                        if(Class3_Sub13_Sub16.anInt3198 != 0) {
                           var23.method1896(Class3_Sub13_Sub16.anInt3198);
                        }

                        if(0 != Class3_Sub28_Sub9.anInt3623) {
                           var23.method1886(Class3_Sub28_Sub9.anInt3623);
                        }

                        if(Class3_Sub13_Sub9.anInt3111 != 0) {
                           var23.method1897(0, Class3_Sub13_Sub9.anInt3111, 0);
                        }
                     }
                  }
               }

               Model var25 = null;
               if(!this.aBoolean3968 && this.anObject2796 != null) {
                  if(Class44.anInt719 >= this.anInt2778) {
                     this.anObject2796 = null;
                  }

                  if(Class44.anInt719 >= this.anInt2797 && this.anInt2778 > Class44.anInt719) {
                     if(this.anObject2796 instanceof Class140_Sub3) {
                        var25 = (Model)((Class140_Sub3)this.anObject2796).method1963();
                     } else {
                        var25 = (Model)this.anObject2796;
                     }

                     Objects.requireNonNull(var25).method1897(this.anInt2782 + -this.anInt2819, this.anInt2812 + -this.anInt2831, this.anInt2833 + -this.anInt2829);
                     if(this.anInt2806 == 512) {
                        var25.method1900();
                     } else if (this.anInt2806 == 1024) {
                        var25.method1874();
                     } else if (this.anInt2806 == 1536) {
                        var25.method1885();
                     }
                  }
               }

               if(HDToolKit.highDetail) {
                  var15.aBoolean2699 = true;
                  var15.animate(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11, this.aClass127_Sub1_2801);
                  if(var23 != null) {
                     var23.aBoolean2699 = true;
                     var23.animate(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11, this.aClass127_Sub1_2801);
                  }
               } else {
                  if(null != var23) {
                     var15 = ((Class140_Sub1_Sub2)var15).method1943(var23);
                  }

                  if(var25 != null) {
                     var15 = ((Class140_Sub1_Sub2)var15).method1943(var25);
                  }

                  var15.aBoolean2699 = true;
                  var15.animate(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11, this.aClass127_Sub1_2801);
               }

               if(null != var25) {
                  if(this.anInt2806 == 512) {
                     var25.method1885();
                  } else if(1024 == this.anInt2806) {
                     var25.method1874();
                  } else if(1536 == this.anInt2806) {
                     var25.method1900();
                  }

                  var25.method1897(-this.anInt2782 + this.anInt2819, -this.anInt2812 + this.anInt2831, -this.anInt2833 + this.anInt2829);
               }

            }
         }
      } catch (RuntimeException var22) {
         throw Class44.clientError(var22, "e.IA(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + var9 + ',' + var11 + ',' + (var12 != null?"{...}":"null") + ')');
      }
   }

   private void method1979(Class127_Sub1 var1, int var2, Model var3, int var4, int var5, int var6, int var8, int var9, int var10, int var11, int var12, int var13, int var14, int var15) {
      try {
         int var16 = var4 * var4 - -(var2 * var2);
         if(var16 >= 16 && var16 <= 360000) {
            int var17 = (int)(325.949D * Math.atan2((double)var4, (double)var2)) & 2047;
            Model var18 = Class128.method1763(var17, this.anInt2829, var13, this.anInt2819, var3, this.anInt2831);
            if(var18 != null) {
               if(HDToolKit.highDetail) {
                  float var19 = HDToolKit.method1852();
                  float var20 = HDToolKit.method1839();
                  HDToolKit.method1851();
                  HDToolKit.method1825(var19, var20 - 150.0F);
                  var18.animate(0, var12, var14, var11, var10, var5, var15, var9, -1L, var6, var1);
                  HDToolKit.method1830();
                  HDToolKit.method1825(var19, var20);
               } else {
                  var18.animate(0, var12, var14, var11, var10, var5, var15, var9, -1L, var6, var1);
               }
            }

         }
      } catch (RuntimeException var21) {
         throw Class44.clientError(var21, "e.N(" + (var1 != null?"{...}":"null") + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + var4 + ',' + var5 + ',' + var6 + ',' + 2047 + ',' + var8 + ',' + var9 + ',' + var10 + ',' + var11 + ',' + var12 + ',' + var13 + ',' + var14 + ',' + var15 + ')');
      }
   }

   final boolean hasDefinitions() {
      try {

          return this.class52 != null;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "e.L(" + (byte) 17 + ')');
      }
   }

   final RSString getName() {
      try {
         RSString var2 = this.displayName;

         if(Class3_Sub30_Sub1.aClass94Array3802 != null) {
            var2 = RenderAnimationDefinition.method903(new RSString[]{Class3_Sub30_Sub1.aClass94Array3802[this.anInt3958], var2}, (byte)-92);
         }

         if(null != OutputStream_Sub1.aClass94Array45) {
            var2 = RenderAnimationDefinition.method903(new RSString[]{var2, OutputStream_Sub1.aClass94Array45[this.anInt3958]}, (byte)-128);
         }

         return var2;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "e.Q(" + 0 + ')');
      }
   }

   final void method1867(int var1, int var2, int var3, int var4, int var5) {}

   final void method1981(int var2, boolean var3, int var4) {
      try {
         super.method1967(this.getSize(), var2, var4, var3);

      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "e.O(" + (byte) 126 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
      }
   }

   protected final void finalize() {}

   public static void method1982(byte var0) {
      try {
         anIntArray3951 = null;
         aClass94_3961 = null;
         anIntArray3959 = null;
         if(var0 <= 116) {
            method1982((byte)-48);
         }

         anIntArray3954 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "e.R(" + var0 + ')');
      }
   }

   final int method1871() {
      try {
         return this.anInt2820;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "e.MA()");
      }
   }

}
