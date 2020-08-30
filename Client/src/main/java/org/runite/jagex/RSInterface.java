package org.runite.jagex;

final class RSInterface {

   boolean hidden = false;
   Object[] anObjectArray156;
   boolean aBoolean157;
   Object[] anObjectArray158;
   Object[] anObjectArray159;
   int anInt160 = 1;
   Object[] anObjectArray161;
   byte aByte162 = 0;
   boolean aBoolean163;
   int anInt164 = 100;
   Object[] anObjectArray165;
   int y;
   boolean aBoolean167;
   int anInt168;
   short aShort169 = 3000;
   Object[] anObjectArray170;
   RSString[] aClass94Array171;
   RSString aClass94_172;
   RSString[] options;
   Object[] anObjectArray174;
   int[] anIntArray175;
   Object[] anObjectArray176;
   int width;
   boolean aBoolean178;
   int anInt179 = 0;
   Object[] anObjectArray180;
   boolean aBoolean181 = false;
   int anInt182 = 0;
   Object[] anObjectArray183;
   int anInt184;
   int[] anIntArray185;
   boolean aBoolean186 = false;
   int type;
   boolean aBoolean188 = false;
   int anInt189;
   int parentId;
   int anInt191 = -1;
   int anInt192;
   int anInt193 = 0;
   int anInt194 = 0;
   boolean aBoolean195;
   private int secondModelId;
   int[] anIntArray197;
   int secondAnimationId = -1;
   boolean aBoolean199;
   boolean aBoolean200;
   int itemId;
   int modelType;
   Object[] anObjectArray203;
   int anInt204;
   int anInt205 = 0;
   Object[] anObjectArray206;
   int[] anIntArray207;
   int anInt208 = 0;
   static RSString aClass94_209 = RSString.createRSString("event_opbase");
   int anInt210 = 0;
   int[] anIntArray211;
   int anInt212;
   int anInt213;
   int anInt214 = 0;
   boolean aBoolean215;
   int anInt216;
   Object[] anObjectArray217;
   int anInt218;
   boolean aBoolean219;
   Object[] anObjectArray220;
   Object[] anObjectArray221;
   int anInt222;
   int anInt223;
   int spriteArchiveId = -1;
   int anInt225;
   boolean aBoolean226 = false;
   boolean aBoolean227;
   int anInt228;
   Object[] anObjectArray229;
   int anInt230 = 0;
   byte[] aByteArray231;
   RSString aClass94_232;
   boolean usingScripts;
   int anInt234;
   Object[] anObjectArray235;
   static boolean aBoolean236 = true;
   int anInt237;
   int anInt238 = -1;
   Object[] anObjectArray239;
   int anInt240;
   byte aByte241;
   int anInt242;
   RSString aClass94_243;
   int height;
   RSString aClass94_245;
   static float aFloat246;
   int anInt247;
   Object[] anObjectArray248;
   int[] anIntArray249;
   int anInt250 = 1;
   static RSString aClass94_251 = null;
   int anInt252;
   int anInt253;
   int[] itemAmounts;
   int anInt255;
   Object[] anObjectArray256;
   Class3_Sub1 aClass3_Sub1_257;
   int anInt258;
   int anInt259;
   int anInt260;
   static long aLong261 = 0L;
   RSInterface[] aClass11Array262;
   byte[] aByteArray263;
   int anInt264;
   int anInt265;
   int anInt266;
   int anInt267;
   Object[] anObjectArray268;
   Object[] anObjectArray269;
   int anInt270;
   int anInt271;
   int[] anIntArray272;
   byte aByte273;
   int[] anIntArray274;
   int[] anIntArray275;
   Object[] anObjectArray276;
   RSString aClass94_277;
   static int anInt278 = -1;
   int anInt279;
   int anInt280;
   Object[] anObjectArray281;
   Object[] anObjectArray282;
   int anInt283;
   int anInt284;
   int anInt285;
   int[] anIntArray286;
   int anInt287;
   int anInt288;
   RSString aClass94_289;
   int anInt290;
   int[] anIntArray291;
   int anInt292;
   short aShort293;
   private int secondModelType;
   Object[] anObjectArray295;
   int anInt296;
   int[][] childDataBuffers;
   int[] anIntArray299;
   int[] anIntArray300;
   int anInt301;
   RSInterface aClass11_302;
   Object[] anObjectArray303;
   byte aByte304;
   int animationId;
   int anInt306;
   int[] anIntArray307;
   int anInt308;
   boolean aBoolean309;
   int[] anIntArray310;
   int anInt311;
   int anInt312;
   Object[] anObjectArray313;
   Object[] anObjectArray314;
   Object[] anObjectArray315;
   int x;
   int[] itemIds;
   int anInt318;


   final void method854(int var1, int var2) {
      try {
         if(this.anIntArray249 == null || var1 >= this.anIntArray249.length) {
            int[] var4 = new int[1 + var1];
            if(this.anIntArray249 != null) {
               int var5;
               for(var5 = 0; this.anIntArray249.length > var5; ++var5) {
                  var4[var5] = this.anIntArray249[var5];
               }

               for(var5 = this.anIntArray249.length; var5 < var1; ++var5) {
                  var4[var5] = -1;
               }
            }

            this.anIntArray249 = var4;
         }

         this.anIntArray249[var1] = var2;

      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "be.P(" + var1 + ',' + var2 + ',' + (byte) 43 + ')');
      }
   }

   final boolean method855() {
      try {
         if(this.anIntArray207 == null) {
            LDIndexedSprite var2 = RSString.method1539(this.spriteArchiveId, Class12.aClass153_323);
            if(null == var2) {
               return false;
            } else {
               var2.method1675();
               this.anIntArray207 = new int[var2.anInt1468];
               this.anIntArray291 = new int[var2.anInt1468];
               int var3 = 0;

               while(var2.anInt1468 > var3) {
                  int var4 = 0;
                  int var5 = var2.anInt1461;
                  int var6 = 0;

                  while(true) {
                     if(var2.anInt1461 > var6) {
                        if(var2.aByteArray2674[var2.anInt1461 * var3 + var6] == 0) {
                           ++var6;
                           continue;
                        }

                        var4 = var6;
                     }

                     for(var6 = var4; var2.anInt1461 > var6; ++var6) {
                        if(0 == var2.aByteArray2674[var3 * var2.anInt1461 + var6]) {
                           var5 = var6;
                           break;
                        }
                     }

                     this.anIntArray207[var3] = var4;
                     this.anIntArray291[var3] = var5 - var4;
                     ++var3;
                     break;
                  }
               }

               return true;
            }
         } else {
            return true;
         }
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "be.G(" + -30721 + ')');
      }
   }

   static RSString method856() {
      try {

         RSString var1 = Class3_Sub28_Sub7_Sub1.aClass94_4052;
         RSString var2 = Class3_Sub28_Sub14.aClass94_3672;
         if(Class44.anInt718 != 0) {
            var1 = RSString.createRSString("www)2wtqa");
         }

         if(null != Class163_Sub2.aClass94_2996) {
            var2 = RenderAnimationDefinition.method903(new RSString[]{Class3_Sub28_Sub11.aClass94_3637, Class163_Sub2.aClass94_2996}, (byte)-64);
         }

         return RenderAnimationDefinition.method903(new RSString[]{Class30.aClass94_577, var1, Class3_Sub28_Sub7.aClass94_3601, Class72.method1298((byte)9, Class3_Sub20.language), Class151.aClass94_1932, Class72.method1298((byte)9, Class3_Sub26.anInt2554), var2, Class140_Sub3.aClass94_2735}, (byte)-61);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "be.N(" + true + ')');
      }
   }

   final void method857(RSString var2, int var3) {
      try {
         if(null == this.aClass94Array171 || var3 >= this.aClass94Array171.length) {
            RSString[] var4 = new RSString[1 + var3];
            if(null != this.aClass94Array171) {
               System.arraycopy(this.aClass94Array171, 0, var4, 0, this.aClass94Array171.length);
            }

            this.aClass94Array171 = var4;
         }

         this.aClass94Array171[var3] = var2;
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "be.B(" + (byte) 112 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

   final void decodeNoScripts(int var1, RSByteBuffer var2) {
      try {
         if(var1 >= -94) {
            this.anInt214 = -74;
         }

         this.usingScripts = false;
         this.type = var2.getByteB();
         this.anInt318 = var2.getByteB();
         this.anInt189 = var2.getShort();
         this.x = var2.getShort((byte)100);
         this.y = var2.getShort((byte)109);
         this.width = var2.getShort();
         this.height = var2.getShort();
         this.aByte304 = 0;
         this.aByte241 = 0;
         this.aByte273 = 0;
         this.aByte162 = 0;
         this.anInt223 = var2.getByteB();
         this.parentId = var2.getShort();
         if(this.parentId == 65535) {
            this.parentId = -1;
         } else {
            this.parentId += -65536 & this.anInt279;
         }

         this.anInt212 = var2.getShort();
         if(this.anInt212 == 65535) {
            this.anInt212 = -1;
         }

         int var3 = var2.getByteB();
         int var4;
         if(var3 > 0) {
            this.anIntArray307 = new int[var3];
            this.anIntArray275 = new int[var3];

            for(var4 = 0; var4 < var3; ++var4) {
               this.anIntArray275[var4] = var2.getByteB();
               this.anIntArray307[var4] = var2.getShort();
            }
         }

         var4 = var2.getByteB();
         int var5;
         int var6;
         int var7;
         if(var4 > 0) {
            this.childDataBuffers = new int[var4][];

            for(var5 = 0; var5 < var4; ++var5) {
               var6 = var2.getShort();
               this.childDataBuffers[var5] = new int[var6];

               for(var7 = 0; var6 > var7; ++var7) {
                  this.childDataBuffers[var5][var7] = var2.getShort();
                  if(this.childDataBuffers[var5][var7] == 65535) {
                     this.childDataBuffers[var5][var7] = -1;
                  }
               }
            }
         }

         if(this.type == 0) {
            this.anInt252 = var2.getShort();
            this.hidden = 1 == var2.getByteB();
         }

         if(this.type == 1) {
            var2.getShort();
            var2.getByteB();
         }

         var5 = 0;
         if(this.type == 2) {
            this.aByte241 = 3;
            this.itemIds = new int[this.width * this.height];
            this.itemAmounts = new int[this.height * this.width];
            this.aByte304 = 3;
            var6 = var2.getByteB();
            var7 = var2.getByteB();
            if(var6 == 1) {
               var5 |= 268435456;
            }

            int var8 = var2.getByteB();
            if(var7 == 1) {
               var5 |= 1073741824;
            }

            if(1 == var8) {
               var5 |= Integer.MIN_VALUE;
            }

            int var9 = var2.getByteB();
            if(var9 == 1) {
               var5 |= 536870912;
            }

            this.anInt285 = var2.getByteB();
            this.anInt290 = var2.getByteB();
            this.anIntArray300 = new int[20];
            this.anIntArray272 = new int[20];
            this.anIntArray197 = new int[20];

            int var10;
            for(var10 = 0; 20 > var10; ++var10) {
               int var11 = var2.getByteB();
               if(var11 == 1) {
                  this.anIntArray272[var10] = var2.getShort((byte)110);
                  this.anIntArray300[var10] = var2.getShort((byte)58);
                  this.anIntArray197[var10] = var2.getInt();
               } else {
                  this.anIntArray197[var10] = -1;
               }
            }

            this.options = new RSString[5];

            for(var10 = 0; var10 < 5; ++var10) {
               RSString var14 = var2.getString();
               if(var14.length(-28) > 0) {
                  this.options[var10] = var14;
                  var5 |= 1 << 23 - -var10;
               }
            }
         }

         if(3 == this.type) {
            this.aBoolean226 = 1 == var2.getByteB();
         }

         if(this.type == 4 || 1 == this.type) {
            this.anInt194 = var2.getByteB();
            this.anInt225 = var2.getByteB();
            this.anInt205 = var2.getByteB();
            this.anInt270 = var2.getShort();
            if(this.anInt270 == 65535) {
               this.anInt270 = -1;
            }

            this.aBoolean215 = 1 == var2.getByteB();
         }

         if(this.type == 4) {
            this.aClass94_232 = var2.getString();
            this.aClass94_172 = var2.getString();
         }

         if(this.type == 1 || this.type == 3 || 4 == this.type) {
            this.anInt218 = var2.getInt();
         }

         if(this.type == 3 || this.type == 4) {
            this.anInt253 = var2.getInt();
            this.anInt228 = var2.getInt();
            this.anInt222 = var2.getInt();
         }

         if(this.type == 5) {
            this.spriteArchiveId = var2.getInt();
            this.anInt296 = var2.getInt();
         }

         if(6 == this.type) {
            this.modelType = 1;
            this.itemId = var2.getShort();
            this.secondModelType = 1;
            if(this.itemId == '\uffff') {
               this.itemId = -1;
            }

            this.secondModelId = var2.getShort();
            if(this.secondModelId == '\uffff') {
               this.secondModelId = -1;
            }

            this.animationId = var2.getShort();
            if(this.animationId == 65535) {
               this.animationId = -1;
            }

            this.secondAnimationId = var2.getShort();
            if('\uffff' == this.secondAnimationId) {
               this.secondAnimationId = -1;
            }

            this.anInt164 = var2.getShort();
            this.anInt182 = var2.getShort();
            this.anInt308 = var2.getShort();
         }

         if(7 == this.type) {
            this.aByte241 = 3;
            this.aByte304 = 3;
            this.itemIds = new int[this.height * this.width];
            this.itemAmounts = new int[this.width * this.height];
            this.anInt194 = var2.getByteB();
            this.anInt270 = var2.getShort();
            if(this.anInt270 == 65535) {
               this.anInt270 = -1;
            }

            this.aBoolean215 = var2.getByteB() == 1;
            this.anInt218 = var2.getInt();
            this.anInt285 = var2.getShort((byte)31);
            this.anInt290 = var2.getShort((byte)83);
            var6 = var2.getByteB();
            if(var6 == 1) {
               var5 |= 1073741824;
            }

            this.options = new RSString[5];

            for(var7 = 0; var7 < 5; ++var7) {
               RSString var13 = var2.getString();
               if(var13.length(-121) > 0) {
                  this.options[var7] = var13;
                  var5 |= 1 << 23 - -var7;
               }
            }
         }

         if(8 == this.type) {
            this.aClass94_232 = var2.getString();
         }

         if(this.anInt318 == 2 || this.type == 2) {
            this.aClass94_245 = var2.getString();
            this.aClass94_243 = var2.getString();
            var6 = 63 & var2.getShort();
            var5 |= var6 << 11;
         }

         if(this.anInt318 == 1 || this.anInt318 == 4 || this.anInt318 == 5 || this.anInt318 == 6) {
            this.aClass94_289 = var2.getString();
            if(this.aClass94_289.length(-33) == 0) {
               if(this.anInt318 == 1) {
                  this.aClass94_289 = TextCore.HasOK;
               }

               if(this.anInt318 == 4) {
                  this.aClass94_289 = TextCore.HasSelect;
               }

               if(5 == this.anInt318) {
                  this.aClass94_289 = TextCore.HasSelect;
               }

               if(this.anInt318 == 6) {
                  this.aClass94_289 = TextCore.HasContinue;
               }
            }
         }

         if(this.anInt318 == 1 || this.anInt318 == 4 || this.anInt318 == 5) {
            var5 |= 4194304;
         }

         if(this.anInt318 == 6) {
            var5 |= 1;
         }

         this.aClass3_Sub1_257 = new Class3_Sub1(var5, -1);
      } catch (RuntimeException var12) {
         throw Class44.clientError(var12, "be.M(" + var1 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   final Class3_Sub28_Sub16 method859(int var2) {
      try {
         GameShell.aBoolean6 = false;
         if(var2 >= 0 && var2 < this.anIntArray197.length) {
            int var3 = this.anIntArray197[var2];
            if(var3 == -1) {
               return null;
            } else {
               Class3_Sub28_Sub16 var4 = (Class3_Sub28_Sub16)Class114.aClass93_1569.get((long)var3);
               if(var4 == null) {
                  var4 = Class3_Sub28_Sub11.method602(0, var3, (byte)-18, Class12.aClass153_323);
                  if(null == var4) {
                     GameShell.aBoolean6 = true;
                  } else {
                     Class114.aClass93_1569.put((byte)-126, var4, (long)var3);
                  }

               }
               return var4;
            }
         } else {
            return null;
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "be.I(" + true + ',' + var2 + ')');
      }
   }

   public static void method860(int var0) {
      try {
         aClass94_209 = null;
         if(var0 < 63) {
            method860(42);
         }

         aClass94_251 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "be.F(" + var0 + ')');
      }
   }

   static int method861(int var0, int var1, int var2) {
      try {
         Class3_Sub25 var3 = (Class3_Sub25)Class3_Sub2.aClass130_2220.method1780((long)var0, 0);
         return null == var3?-1:(0 <= var2 && var2 < var3.anIntArray2547.length?(var1 < 39?-69:var3.anIntArray2547[var2]):-1);
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "be.J(" + var0 + ',' + var1 + ',' + var2 + ')');
      }
   }

   private Object[] method862(RSByteBuffer var2) {
      try {
         int var3 = var2.getByteB();
         if(var3 == 0) {
            return null;
         } else {
            Object[] var4 = new Object[var3];

            for(int var5 = 0; var3 > var5; ++var5) {
               int var6 = var2.getByteB();
               if(0 == var6) {
                  var4[var5] = new Integer(var2.getInt());
               } else if (var6 == 1) {
                  var4[var5] = var2.getString();
               }
            }

            this.aBoolean195 = true;
            return var4;
         }
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "be.K(" + -65536 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   private int[] method863(RSByteBuffer var1) {
      try {
         int var3 = var1.getByteB();
         if(var3 == 0) {
            return null;
         } else {
            int[] var4 = new int[var3];

            for(int var5 = 0; var3 > var5; ++var5) {
               var4[var5] = var1.getInt();
            }

            return var4;
         }
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "be.H(" + (var1 != null?"{...}":"null") + ',' + false + ')');
      }
   }

   final void method864(int var1, int var2, int var3) {
      try {
         int var4 = this.itemAmounts[var2];
         this.itemAmounts[var2] = this.itemAmounts[var1];
         if(var3 > -66) {
            this.decodeNoScripts(36, (RSByteBuffer)null);
         }

         this.itemAmounts[var1] = var4;
         var4 = this.itemIds[var2];
         this.itemIds[var2] = this.itemIds[var1];
         this.itemIds[var1] = var4;
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "be.L(" + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   final Model method865(int var1, AnimationDefinition var2, int var3, int var4, int var5, boolean var6, Class52 var7) {
      try {
         GameShell.aBoolean6 = false;
         int var8;
         int var9;
         if(var6) {
            var8 = this.secondModelType;
            var9 = this.secondModelId;
         } else {
            var9 = this.itemId;
            var8 = this.modelType;
         }

         if(var4 < 125) {
            return (Model)null;
         } else if(var8 == 0) {
            return null;
         } else if (var8 == 1 && var9 == -1) {
            return null;
         } else {
            Model var10;
            if (1 == var8) {
               var10 = (Model) Class3_Sub15.aClass93_2428.get((long) ((var8 << 16) - -var9));
               if (var10 == null) {
                  Model_Sub1 var18 = Model_Sub1.method2015(Class119.aClass153_1628, var9);
                  if (var18 == null) {
                     GameShell.aBoolean6 = true;
                     return null;
                  }

                  var10 = var18.method2008(64, 768, -50, -10, -50);
                  Class3_Sub15.aClass93_2428.put((byte) -115, var10, (long) (var9 + (var8 << 16)));
               }

               if (var2 != null) {
                  var10 = var2.method2055(var10, (byte) 119, var1, var5, var3);
               }

               return var10;
            } else if (var8 == 2) {
               var10 = Node.method522(var9).getChatModel(var2, var5, var1, 27, var3);
               if (null == var10) {
                  GameShell.aBoolean6 = true;
                  return null;
               } else {
                  return var10;
               }
            } else if (3 != var8) {
               if (4 == var8) {
                  ItemDefinition var16 = Class38.getItemDefinition(var9, (byte) 94);
                  Model var17 = var16.method1110(110, var1, var5, var2, 10, var3);
                  if (var17 == null) {
                     GameShell.aBoolean6 = true;
                     return null;
                  } else {
                     return var17;
                  }
               } else if (var8 == 6) {
                  var10 = Node.method522(var9).method1476((Class145[]) null, 0, (byte) -120, 0, var1, var5, var3, (AnimationDefinition) null, 0, var2);
                  if (null == var10) {
                     GameShell.aBoolean6 = true;
                     return null;
                  } else {
                     return var10;
                  }
               } else if (var8 != 7) {
                  return null;
               } else if (var7 == null) {
                  return null;
               } else {
                  int var15 = this.itemId >>> 16;
                  int var11 = this.itemId & '\uffff';
                  int var12 = this.anInt265;
                  Model var13 = var7.method1157(var1, var12, var15, var5, var2, var3, var11);
                  if (var13 == null) {
                     GameShell.aBoolean6 = true;
                     return null;
                  } else {
                     return var13;
                  }
               }
            } else if (null == var7) {
               return null;
            } else {
               var10 = var7.method1167(var5, var2, var3, var1);
               if (null == var10) {
                  GameShell.aBoolean6 = true;
                  return null;
               } else {
                  return var10;
               }
            }
         }
      } catch (RuntimeException var14) {
         throw Class44.clientError(var14, "be.E(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + (var7 != null?"{...}":"null") + ')');
      }
   }

   final Class3_Sub28_Sub16 method866(boolean var2) {
      try {
         GameShell.aBoolean6 = false;
         int archiveId;
         if(var2) {
            archiveId = this.anInt296;
         } else {
            archiveId = this.spriteArchiveId;
         }
         if(archiveId == -1) {
            return null;
         } else {
            long var4 = ((this.aBoolean178?1L:0L) << 38) + ((!this.aBoolean157?0L:1L) << 35) + (long)archiveId + ((long)this.anInt288 << 36) + ((this.aBoolean199?1L:0L) << 39) + ((long)this.anInt287 << 40);
            Class3_Sub28_Sub16 var6 = (Class3_Sub28_Sub16)Class114.aClass93_1569.get(var4);
            if(var6 == null) {
               Class3_Sub28_Sub16_Sub2 var7;
               if(this.aBoolean157) {
                  var7 = Class3_Sub28_Sub7.method562(Class12.aClass153_323, archiveId);
               } else {
                  var7 = Class40.method1043(0, Class12.aClass153_323, archiveId);
               }

               if(null == var7) {
                  GameShell.aBoolean6 = true;
                  return null;
               } else {
                  if(this.aBoolean178) {
                     var7.method663();
                  }

                  if(this.aBoolean199) {
                     var7.method653();
                  }

                  if(this.anInt288 > 0) {
                     var7.method652(this.anInt288);
                  }

                  if(this.anInt288 >= 1) {
                     var7.method657(1);
                  }

                  if(2 <= this.anInt288) {
                     var7.method657(16777215);
                  }

                  if(this.anInt287 != 0) {
                     var7.method668(this.anInt287);
                  }

                  Object var9;
                  if(HDToolKit.highDetail) {
                     if(var7 instanceof Class3_Sub28_Sub16_Sub2_Sub1) {
                        var9 = new Class3_Sub28_Sub16_Sub1_Sub1(var7);
                     } else {
                        var9 = new Class3_Sub28_Sub16_Sub1(var7);
                     }
                  } else {
                     var9 = var7;
                  }

                  Class114.aClass93_1569.put((byte)-75, var9, var4);
                  return (Class3_Sub28_Sub16)var9;
               }
            } else {
               return var6;
            }
         }
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "be.O(" + (byte) -113 + ',' + var2 + ')');
      }
   }

   final void decodeScriptFormat(RSByteBuffer buffer) {
      try {
         this.usingScripts = true;
         ++buffer.index;
         this.type = buffer.getByteB();
         if((128 & this.type) != 0) {
            this.type &= 127;
            buffer.getString();
         }

         this.anInt189 = buffer.getShort();
         this.x = buffer.getShort((byte)66);
         this.y = buffer.getShort((byte)121);
         this.width = buffer.getShort();
         this.height = buffer.getShort();
         this.aByte304 = buffer.getByte();
         this.aByte241 = buffer.getByte();
         this.aByte273 = buffer.getByte();
         this.aByte162 = buffer.getByte();
         this.parentId = buffer.getShort();
         if(this.parentId == 65535) {
            this.parentId = -1;
         } else {
            this.parentId = (this.anInt279 & -65536) - -this.parentId;
         }

         this.hidden = buffer.getByteB() == 1;
         if(this.type == 0) {
            this.anInt240 = buffer.getShort();
            this.anInt252 = buffer.getShort();
            this.aBoolean219 = buffer.getByteB() == 1;
         }

         int var3;
         if(this.type == 5) {
            this.spriteArchiveId = buffer.getInt();
            this.anInt301 = buffer.getShort();
            var3 = buffer.getByteB();
            this.aBoolean157 = (2 & var3) != 0;
            this.aBoolean186 = (1 & var3) != 0;
            this.anInt223 = buffer.getByteB();
            this.anInt288 = buffer.getByteB();
            this.anInt287 = buffer.getInt();
            this.aBoolean178 = buffer.getByteB() == 1;
            this.aBoolean199 = 1 == buffer.getByteB();
         }

         if(this.type == 6) {
            this.modelType = 1;
            this.itemId = buffer.getShort();
            if(this.itemId == 65535) {
               this.itemId = -1;
            }

            this.anInt259 = buffer.getShort((byte)122);
            this.anInt230 = buffer.getShort((byte)32);
            this.anInt182 = buffer.getShort();
            this.anInt308 = buffer.getShort();
            this.anInt280 = buffer.getShort();
            this.anInt164 = buffer.getShort();
            this.animationId = buffer.getShort();
            if('\uffff' == this.animationId) {
               this.animationId = -1;
            }

            this.aBoolean181 = buffer.getByteB() == 1;
            this.aShort293 = (short)buffer.getShort();
            this.aShort169 = (short)buffer.getShort();
            this.aBoolean309 = 1 == buffer.getByteB();
            if(this.aByte304 != 0) {
               this.anInt184 = buffer.getShort();
            }

            if(this.aByte241 != 0) {
               this.anInt312 = buffer.getShort();
            }
         }

         if(this.type == 4) {
            this.anInt270 = buffer.getShort();
            if(this.anInt270 == 65535) {
               this.anInt270 = -1;
            }

            this.aClass94_232 = buffer.getString();
            this.anInt205 = buffer.getByteB();
            this.anInt194 = buffer.getByteB();
            this.anInt225 = buffer.getByteB();
            this.aBoolean215 = buffer.getByteB() == 1;
            this.anInt218 = buffer.getInt();
         }

         if(this.type == 3) {
            this.anInt218 = buffer.getInt();
            this.aBoolean226 = 1 == buffer.getByteB();
            this.anInt223 = buffer.getByteB();
         }

         if(this.type == 9) {
            this.anInt250 = buffer.getByteB();
            this.anInt218 = buffer.getInt();
            this.aBoolean167 = 1 == buffer.getByteB();
         }

         var3 = buffer.getTriByte((byte)87);
         int var4 = buffer.getByteB();
         int var5;
         if(var4 != 0) {
            this.anIntArray299 = new int[10];
            this.aByteArray263 = new byte[10];

            for(this.aByteArray231 = new byte[10]; var4 != 0; var4 = buffer.getByteB()) {
               var5 = (var4 >> 4) - 1;
               var4 = buffer.getByteB() | var4 << 8;
               var4 &= 4095;
               if(4095 == var4) {
                  this.anIntArray299[var5] = -1;
               } else {
                  this.anIntArray299[var5] = var4;
               }

               this.aByteArray263[var5] = buffer.getByte();
               this.aByteArray231[var5] = buffer.getByte();
            }
         }

         this.aClass94_277 = buffer.getString();
         var5 = buffer.getByteB();
         int var6 = var5 & 15;
         int var8;
         if(0 < var6) {
            this.aClass94Array171 = new RSString[var6];

            for(var8 = 0; var6 > var8; ++var8) {
               this.aClass94Array171[var8] = buffer.getString();
            }
         }

         
         
         int var7 = var5 >> 4;
         if(var7 > 0) {
            var8 = buffer.getByteB();
            this.anIntArray249 = new int[var8 + 1];

            for(int var9 = 0; var9 < this.anIntArray249.length; ++var9) {
               this.anIntArray249[var9] = -1;
            }

            this.anIntArray249[var8] = buffer.getShort();
         }

         if(1 < var7) {
            var8 = buffer.getByteB();
            this.anIntArray249[var8] = buffer.getShort();
         }

         this.anInt214 = buffer.getByteB();
         this.anInt179 = buffer.getByteB();
         this.aBoolean200 = buffer.getByteB() == 1;
         var8 = -1;
         this.aClass94_245 = buffer.getString();
         if(0 != (127 & var3 >> 11)) {
            var8 = buffer.getShort();
            this.anInt266 = buffer.getShort();
            if(var8 == 65535) {
               var8 = -1;
            }

            if('\uffff' == this.anInt266) {
               this.anInt266 = -1;
            }

            this.anInt238 = buffer.getShort();
            if(this.anInt238 == '\uffff') {
               this.anInt238 = -1;
            }
         }

         this.aClass3_Sub1_257 = new Class3_Sub1(var3, var8);
         this.anObjectArray159 = this.method862(buffer);
         this.anObjectArray248 = this.method862(buffer);
         this.anObjectArray281 = this.method862(buffer);
         this.anObjectArray303 = this.method862(buffer);
         this.anObjectArray203 = this.method862(buffer);
         this.anObjectArray282 = this.method862(buffer);
         this.anObjectArray174 = this.method862(buffer);
         this.anObjectArray158 = this.method862(buffer);//.?
         this.anObjectArray269 = this.method862(buffer);
         this.anObjectArray314 = this.method862(buffer);
         this.anObjectArray276 = this.method862(buffer);
         this.anObjectArray165 = this.method862(buffer);
         this.anObjectArray170 = this.method862(buffer);
         this.anObjectArray239 = this.method862(buffer);
         this.anObjectArray180 = this.method862(buffer);
         this.anObjectArray295 = this.method862(buffer);
         this.anObjectArray229 = this.method862(buffer);
         this.anObjectArray183 = this.method862(buffer);
         this.anObjectArray161 = this.method862(buffer);
         this.anObjectArray221 = this.method862(buffer);
         this.anIntArray286 = this.method863(buffer);
         this.anIntArray175 = this.method863(buffer);
         this.anIntArray274 = this.method863(buffer);
         this.anIntArray211 = this.method863(buffer);
         this.anIntArray185 = this.method863(buffer);
      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "be.C(" + -1 + ',' + (buffer != null?"{...}":"null") + ')');
      }
   }

   final Class3_Sub28_Sub17 method868(AbstractIndexedSprite[] var1) {
      try {
         GameShell.aBoolean6 = false;
         if(this.anInt270 == -1) {
            return null;
         } else {
            Class3_Sub28_Sub17 var3 = (Class3_Sub28_Sub17)Class47.aClass93_743.get((long)this.anInt270);
            if(null == var3) {
               var3 = Class73.method1300(this.anInt270, (byte)127, Class12.aClass153_323, Class97.aClass153_1378);
               if(null == var3) {
                  GameShell.aBoolean6 = true;
               } else {
                  var3.method697(var1, (int[])null);
                  Class47.aClass93_743.put((byte)-77, var3, (long)this.anInt270);
               }

            }
            return var3;
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "be.A(" + (var1 != null?"{...}":"null") + ',' + 0 + ')');
      }
   }

   static int method869(int var0, int var1) {
      try {
         return var1 != 16711935 ?(var0 < 97?-63:Class56.method1186(var1)):-1;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "be.D(" + var0 + ',' + var1 + ')');
      }
   }

   public RSInterface() {
      this.aClass94_243 = Class104.aClass94_2171;
      this.aBoolean163 = false;
      this.anInt225 = 0;
      this.anInt212 = -1;
      this.aBoolean167 = false;
      this.anInt266 = -1;
      this.aByte241 = 0;
      this.anInt252 = 0;
      this.aBoolean200 = false;
      this.aBoolean215 = false;
      this.anInt204 = -1;
      this.anInt260 = 1;
      this.anInt228 = 0;
      this.usingScripts = false;
      this.aClass3_Sub1_257 = Class158_Sub1.aClass3_Sub1_2980;
      this.anInt253 = 0;
      this.aClass94_232 = Class104.aClass94_2171;
      this.anInt168 = 0;
      this.anInt247 = 0;
      this.aBoolean219 = false;
      this.secondModelId = -1;
      this.parentId = -1;
      this.anInt216 = 1;
      this.anInt192 = -1;
      this.anInt222 = 0;
      this.anInt264 = 0;
      this.aClass94_277 = Class104.aClass94_2171;
      this.anInt284 = 0;
      this.width = 0;
      this.anInt285 = 0;
      this.anInt234 = -1;
      this.aBoolean157 = false;
      this.anInt184 = 0;
      this.anInt223 = 0;
      this.anInt258 = 0;
      this.aClass94_245 = Class104.aClass94_2171;
      this.anInt237 = 0;
      this.aClass94_172 = Class104.aClass94_2171;
      this.anInt288 = 0;
      this.anInt265 = -1;
      this.anInt242 = 0;
      this.anInt259 = 0;
      this.anInt290 = 0;
      this.height = 0;
      this.anInt279 = -1;
      this.anInt296 = -1;
      this.aByte273 = 0;
      this.anInt267 = 0;
      this.anInt270 = -1;
      this.anInt240 = 0;
      this.anInt255 = 0;
      this.aShort293 = 0;
      this.anInt301 = 0;
      this.animationId = -1;
      this.aClass94_289 = TextCore.HasOK;
      this.anInt280 = 0;
      this.anInt271 = 0;
      this.anInt292 = -1;
      this.anInt189 = 0;
      this.anInt287 = 0;
      this.aClass11_302 = null;
      this.anInt311 = 0;
      this.modelType = 1;
      this.aBoolean309 = false;
      this.aByte304 = 0;
      this.secondModelType = 1;
      this.anInt312 = 0;
      this.anInt308 = 0;
      this.aBoolean195 = false;
      this.x = 0;
      this.anInt306 = 0;
      this.y = 0;
      this.aBoolean227 = true;
      this.anInt283 = 0;
      this.anInt213 = 0;
      this.anInt218 = 0;
      this.anInt318 = 0;
   }

}
