package org.runite.jagex;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Insets;

final class Class3_Sub28_Sub10_Sub2 extends Class3_Sub28_Sub10 {

   byte aByte4064;
   static RSString aClass94_4066 = RSString.createRSString("<br>");
   int anInt4067;
   static boolean aBoolean4068 = true;
   RSByteBuffer aClass3_Sub30_4069;
   static Class3_Sub2[][][] aClass3_Sub2ArrayArrayArray4070;
   static int anInt4073;

   final int method586() {
      try {
         return (this.aClass3_Sub30_4069 == null?0:this.aClass3_Sub30_4069.index * 100 / (-this.aByte4064 + this.aClass3_Sub30_4069.buffer.length));
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "pm.A(" + false + ')');
      }
   }

   static Class106[] method596(Signlink var1) {
      try {
         if(var1.method1432(false)) {
            Class64 var2 = var1.method1453((byte)8);

            while(0 == var2.anInt978) {
               Class3_Sub13_Sub34.method331(10L, 64);
            }

            if(2 == var2.anInt978) {
               return new Class106[0];
            } else {
               int[] var3 = (int[])((int[])var2.anObject974);
               Class106[] var4 = new Class106[var3.length >> 2];

               for(int var5 = 0; var5 < var4.length; ++var5) {
                  Class106 var6 = new Class106();
                  var4[var5] = var6;
                  var6.anInt1447 = var3[var5 << 2];
                  var6.anInt1449 = var3[(var5 << 2) + 1];
                  var6.anInt1450 = var3[(var5 << 2) - -2];
			   }

				return var4;
            }
         } else {
            return new Class106[0];
         }
	  } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "pm.P(" + 10 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   public static void method597(byte var0) {
      try {
         aClass3_Sub2ArrayArrayArray4070 = (Class3_Sub2[][][])null;
         aClass94_4066 = null;
         if(var0 < 91) {
		 }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "pm.O(" + var0 + ')');
      }
   }
   
   static void method598(boolean var0, int var1, boolean var2, int var3, boolean var4, int var5, int var6) {
	      try {
	         if(var2) {
	            HDToolKit.method1842();
	         }

	         if(null != Class3_Sub13_Sub10.aFrame3121 && (3 != var1 || Class3_Sub13.anInt2378 != var5 || Class3_Sub13_Sub5.anInt3071 != var6)) {
	            Class3_Sub28_Sub10_Sub1.method593(Class3_Sub13_Sub10.aFrame3121, Class38.aClass87_665);
	            Class3_Sub13_Sub10.aFrame3121 = null;
	         }

	         if(3 == var1 && null == Class3_Sub13_Sub10.aFrame3121) {
	            Class3_Sub13_Sub10.aFrame3121 = Class99.method1597(0, var6, var5, Class38.aClass87_665);
	            if(null != Class3_Sub13_Sub10.aFrame3121) {
	               Class3_Sub13_Sub5.anInt3071 = var6;
	               Class3_Sub13.anInt2378 = var5;
	               Class119.method1730(Class38.aClass87_665);
	            }
	         }

	         if(var1 == 3 && Class3_Sub13_Sub10.aFrame3121 == null) {
	            method598(true, anInt2577, true, var3, var4, -1, -1);
	         } else {
	            Object var7;
	            if(null == Class3_Sub13_Sub10.aFrame3121) {
	               if(null == GameShell.frame) {
	                  var7 = Class38.aClass87_665.anApplet1219;
	               } else {
	                  var7 = GameShell.frame;
	               }
				} else {
	               var7 = Class3_Sub13_Sub10.aFrame3121;
	            }

	            Class3_Sub9.anInt2334 = ((Container)var7).getSize().width;
	            Class70.anInt1047 = ((Container)var7).getSize().height;
	            Insets var8;
	            if(GameShell.frame == var7) {
	               var8 = GameShell.frame.getInsets();
	               Class3_Sub9.anInt2334 -= var8.right + var8.left;
	               Class70.anInt1047 -= var8.bottom + var8.top;
	            }

	            if(var1 >= 2) {
	               Class23.anInt454 = Class3_Sub9.anInt2334;
	               Class140_Sub7.anInt2934 = Class70.anInt1047;
	               Class84.anInt1164 = 0;
	               Class106.anInt1442 = 0;
	            } else {
	               Class106.anInt1442 = 0;
	               Class84.anInt1164 = (Class3_Sub9.anInt2334 + -765) / 2;
	               Class23.anInt454 = 765;
	               Class140_Sub7.anInt2934 = 503;
	            }

	            if(var0) {
	               Class163_Sub1_Sub1.method2215(Class3_Sub28_Sub12.aCanvas3648);
	               Class130.method1783(4, Class3_Sub28_Sub12.aCanvas3648);
	               if(null != Class38.aClass146_668) {
	                  Class38.aClass146_668.method2082(Class3_Sub28_Sub12.aCanvas3648);
	               }

	               Class126.aClient1671.method30((byte)97);
	               Class3_Sub13_Sub4.method193((byte)97, Class3_Sub28_Sub12.aCanvas3648);
	               ItemDefinition.method1119(Class3_Sub28_Sub12.aCanvas3648, var4);
	               if(Class38.aClass146_668 != null) {
	                  Class38.aClass146_668.method2084(Class3_Sub28_Sub12.aCanvas3648, -103);
	               }
	            } else {
	               if(HDToolKit.highDetail) {
	                  HDToolKit.method1854(Class23.anInt454, Class140_Sub7.anInt2934);
	               }

	               Class3_Sub28_Sub12.aCanvas3648.setSize(Class23.anInt454, Class140_Sub7.anInt2934);
	               if(GameShell.frame == var7) {
	                  var8 = GameShell.frame.getInsets();
	                  Class3_Sub28_Sub12.aCanvas3648.setLocation(var8.left - -Class84.anInt1164, var8.top + Class106.anInt1442);
	               } else {
	                  Class3_Sub28_Sub12.aCanvas3648.setLocation(Class84.anInt1164, Class106.anInt1442);
	               }
	            }

				 if(0 == var1 && var3 > 0) {
	               HDToolKit.method1834(Class3_Sub28_Sub12.aCanvas3648);
	            }

	            if(var2 && var1 > 0) {
	               Class3_Sub28_Sub12.aCanvas3648.setIgnoreRepaint(true);
	               if(!GameShell.aBoolean11) {
	                  Class32.method995();
	                  Class164_Sub1.aClass158_3009 = null;
	                  Class164_Sub1.aClass158_3009 = Class3_Sub13_Sub23_Sub1.method285(Class140_Sub7.anInt2934, Class23.anInt454, Class3_Sub28_Sub12.aCanvas3648);
	                  Class74.method1320();
	                  if(5 == Class143.loadingStage) {
	                     Class3_Sub23.method406((byte)122, true, Class168.aClass3_Sub28_Sub17_2096);
	                  } else {
	                     Class3_Sub13.method164((byte)-20, false, TextCore.LoadingPleaseWait2);
	                  }

					   try {
	                     Graphics var11 = Class3_Sub28_Sub12.aCanvas3648.getGraphics();
	                     Class164_Sub1.aClass158_3009.method2179(var11);
	                  } catch (Exception var9) {
					   }

	                  Class80.method1396(-1);
	                  if(var3 == 0) {
	                     Class164_Sub1.aClass158_3009 = Class3_Sub13_Sub23_Sub1.method285(503, 765, Class3_Sub28_Sub12.aCanvas3648);
	                  } else {
	                     Class164_Sub1.aClass158_3009 = null;
	                  }

					   Class64 var13 = Class38.aClass87_665.method1444(-43, Class126.aClient1671.getClass());

	                  while(var13.anInt978 == 0) {
	                     Class3_Sub13_Sub34.method331(100L, 64);
	                  }

	                  if(1 == var13.anInt978) {
	                     GameShell.aBoolean11 = true;
	                  }
	               }

	               if(GameShell.aBoolean11) {
	                  HDToolKit.method1853(Class3_Sub28_Sub12.aCanvas3648, 2 * Class3_Sub28_Sub14.anInt3671);
	               }
	            }

	            if(!HDToolKit.highDetail && 0 < var1) {
	               method598(true, 0, true, var3, false, -1, -1);
	            } else {
	               if(var1 > 0 && var3 == 0) {
	                  Class17.aThread409.setPriority(5);
	                  Class164_Sub1.aClass158_3009 = null;
	                  Class140_Sub1_Sub2.method1935();
	                  ((Class102)Class51.anInterface2_838).method1619(200);
	                  if(Class106.aBoolean1441) {
	                     Class51.method1137(0.7F);
	                  }

	                  Class3_Sub13.method165();
	               } else if(0 == var1 && var3 > 0) {
	                  Class17.aThread409.setPriority(1);
	                  Class164_Sub1.aClass158_3009 = Class3_Sub13_Sub23_Sub1.method285(503, 765, Class3_Sub28_Sub12.aCanvas3648);
	                  Class140_Sub1_Sub2.method1938();
	                  ((Class102)Class51.anInterface2_838).method1619(20);
	                  if(Class106.aBoolean1441) {
	                     if(1 == Class3_Sub28_Sub10.anInt3625) {
	                        Class51.method1137(0.9F);
	                     }

	                     if(Class3_Sub28_Sub10.anInt3625 == 2) {
	                        Class51.method1137(0.8F);
	                     }

	                     if(3 == Class3_Sub28_Sub10.anInt3625) {
	                        Class51.method1137(0.7F);
	                     }

	                     if(Class3_Sub28_Sub10.anInt3625 == 4) {
	                        Class51.method1137(0.6F);
	                     }
	                  }

	                  Class3_Sub11.method144();
	                  Class3_Sub13.method165();
	               }

	               Class47.aBoolean742 = !NPC.method1986(89);
	               if(var2) {
	                  Class3_Sub20.method389();
	               }

					Class3_Sub15.aBoolean2427 = var1 >= 2;

	               if(-1 != Class3_Sub28_Sub12.anInt3655) {
	                  Class124.method1746(true, (byte)-107);
	               }

	               if(null != Class3_Sub15.aClass89_2429 && (Class143.loadingStage == 30 || Class143.loadingStage == 25)) {
	                  Class3_Sub13_Sub8.method204(-3);
	               }

	               for(int var12 = 0; var12 < 100; ++var12) {
	                  Class3_Sub28_Sub14.aBooleanArray3674[var12] = true;
	               }

	               Class3_Sub13_Sub10.aBoolean3116 = true;
	            }
	         }
	      } catch (RuntimeException var10) {
	         throw Class44.clientError(var10, "pm.F(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ')');
	      }
	   }

   final byte[] method587() {
      try {
         if(!this.aBoolean3632 && -this.aByte4064 + this.aClass3_Sub30_4069.buffer.length <= this.aClass3_Sub30_4069.index) {

			 return this.aClass3_Sub30_4069.buffer;
         } else {
            throw new RuntimeException();
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "pm.E(" + false + ')');
      }
   }

}
