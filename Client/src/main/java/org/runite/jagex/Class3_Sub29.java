package org.runite.jagex;
import java.nio.charset.StandardCharsets;

final class Class3_Sub29 extends Class3 {

   static int anInt2579 = 1;
   static RSString[] aClass94Array2580 = new RSString[100];
   static CacheIndex aClass153_2581;
   static int anInt2582 = 0;
   static boolean isDynamicSceneGraph = false;
   static RSString aClass94_2584 = RSString.createRSString("<)4col>");
   RSString aClass94_2586;
   static int anInt2587;
   static GameShell anApplet_Sub1_2588 = null;
   static int anInt2589 = 0;


   static void method727() {
      try {
         KeyboardListener.aClass93_1911.method1524();
         Class80.aClass93_1131.method1524();
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "sj.O(" + 91 + ')');
      }
   }

   static void method728() {
      try {

         try {
            if(Class10.anInt154 == 1) {
               int var1 = Class101.aClass3_Sub24_Sub4_1421.method499();
               if(var1 > 0 && Class101.aClass3_Sub24_Sub4_1421.method473(-124)) {
                  var1 -= GraphicDefinition.anInt546;
                  if(var1 < 0) {
                     var1 = 0;
                  }

                  Class101.aClass3_Sub24_Sub4_1421.method506(var1);
                  return;
               }

               Class101.aClass3_Sub24_Sub4_1421.method505((byte)-128);
               Class101.aClass3_Sub24_Sub4_1421.method485();
               Class83.aClass3_Sub27_1154 = null;
               Class3_Sub28_Sub4.aClass83_3579 = null;
               if(Class101.aClass153_1423 == null) {
                  Class10.anInt154 = 0;
               } else {
                  Class10.anInt154 = 2;
               }
            }
         } catch (Exception var2) {
            var2.printStackTrace();
            Class101.aClass3_Sub24_Sub4_1421.method505((byte)-127);
            Class101.aClass153_1423 = null;
            Class83.aClass3_Sub27_1154 = null;
            Class10.anInt154 = 0;
            Class3_Sub28_Sub4.aClass83_3579 = null;
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "sj.A(" + false + ')');
      }
   }

   static int method729(byte var0, int var1, int var2) {
      try {
         if(var0 > -32) {
            return 88;
         } else if(var1 == -2) {
            return 12345678;
         } else if(var1 == -1) {
            if(2 > var2) {
               var2 = 2;
            } else if(var2 > 126) {
               var2 = 126;
            }

            return var2;
         } else {
            var2 = (127 & var1) * var2 >> 7;
            if(var2 < 2) {
               var2 = 2;
            } else if(var2 > 126) {
               var2 = 126;
            }

            return (var1 & '\uff80') - -var2;
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "sj.E(" + var0 + ',' + var1 + ',' + var2 + ')');
      }
   }

   static void method730(int var0, int var1, int var3, int var4, int var5) {
      try {
         if(Class101.anInt1425 <= var0 && var4 <= Class3_Sub28_Sub18.anInt3765 && Class159.anInt2020 <= var5 && Class57.anInt902 >= var3) {
            Class104.method1632(95, var3, var4, var5, var0, var1);
         } else {
            Class93.method1525(var1, var4, var5, var0, var3);
         }

      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "sj.R(" + var0 + ',' + var1 + ',' + (byte) 121 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
      }
   }

   static void method731(CacheIndex var0) {
      try {
         Class3_Sub13_Sub13.aClass153_3154 = var0;
         Class95.anInt1344 = Class3_Sub13_Sub13.aClass153_3154.getFileAmount(16, (byte)71);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "sj.B(" + (var0 != null?"{...}":"null") + ',' + (byte) -113 + ')');
      }
   }

   public Class3_Sub29() {}

   static RSString method732(String var0) {
      try {

         byte[] var2;
          var2 = var0.getBytes(StandardCharsets.ISO_8859_1);

          RSString var3 = new RSString();
         var3.byteArray = var2;
         var3.length = 0;

         for(int var4 = 0; var4 < var2.length; ++var4) {
            if(var2[var4] != 0) {
               var2[var3.length++] = var2[var4];
            }
         }

         return var3;
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "sj.D(" + (var0 != null?"{...}":"null") + ',' + 27307 + ')');
      }
   }

   static Class3_Sub28_Sub4 method733(int var1) {
      try {
         Class3_Sub28_Sub4 var2 = (Class3_Sub28_Sub4)Class3_Sub28_Sub19.aClass47_3776.method1092((long)var1);
         if(null == var2) {
            byte[] var3;
            if(var1 < 32768) {
               var3 = Class3_Sub24_Sub3.aClass153_3490.getFile(1, var1);
            } else {
               var3 = Class154.aClass153_1967.getFile(1, 32767 & var1);
            }

            var2 = new Class3_Sub28_Sub4();

            if(var3 != null) {
               var2.method546(new RSByteBuffer(var3));
            }

            if(var1 >= '\u8000') {
               var2.method548();
            }

            Class3_Sub28_Sub19.aClass47_3776.method1097(var2, (long)var1, (byte)-117);
         }
         return var2;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "sj.Q(" + 12345678 + ',' + var1 + ')');
      }
   }

   static void method734(RSString var1) {
      try {
         Class163_Sub2.aClass94_2996 = var1;
         if(null != Class38.aClass87_665.anApplet1219) {
            try {
               RSString var2 = Class163.aClass94_2044.method1573((byte)125, Class38.aClass87_665.anApplet1219);
               RSString var3 = Class144.aClass94_1885.method1573((byte)126, Class38.aClass87_665.anApplet1219);
               RSString var4 = RenderAnimationDefinition.method903(new RSString[]{var2, Class82.aClass94_1151, var1, Class166.aClass94_2074, var3}, (byte)-119);
               if(0 == var1.length(-84)) {
                  var4 = RenderAnimationDefinition.method903(new RSString[]{var4, TextCore.HasAgeExpire}, (byte)-60);
               } else {
                  var4 = RenderAnimationDefinition.method903(new RSString[]{var4, TextCore.HasExpires, Class15.method894(94608000000L + Class5.method830((byte)-55), (byte)52), TextCore.HasMaxAge, Class3_Sub28_Sub12.method612(94608000L, (byte)102)}, (byte)-80);
               }

               RenderAnimationDefinition.method903(new RSString[]{Class129.aClass94_1694, var4, Class130.aClass94_1698}, (byte)-84).method1554(Class38.aClass87_665.anApplet1219);
            } catch (Throwable var5) {
            }

         }
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "sj.F(" + 0 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   public static void method735(int var0) {
      try {
         aClass153_2581 = null;
         aClass94Array2580 = null;
         aClass94_2584 = null;
         if(var0 != -22749) {
            anInt2579 = 66;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "sj.C(" + var0 + ')');
      }
   }

   static void method736(int var0, int var1) {
      try {
         if(var1 <= 61) {
            method736(-60, -93);
         }

         if(Class10.anInt154 == 0) {
            Class101.aClass3_Sub24_Sub4_1421.method506(var0);
         } else {
            Class3_Sub13_Sub36.anInt3423 = var0;
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "sj.P(" + var0 + ',' + var1 + ')');
      }
   }

   Class3_Sub29(RSString var1) {
      try {
         this.aClass94_2586 = var1;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "sj.<init>(" + (var1 != null?"{...}":"null") + ')');
      }
   }

}
