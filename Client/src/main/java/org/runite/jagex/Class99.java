package org.runite.jagex;
import java.awt.Frame;
import java.util.Objects;

final class Class99 {

   static short[] aShortArray1398;
   static Class33 aClass33_1399;
   static int anInt1400;
   static Class93 aClass93_1401 = new Class93(500);
   static RSInterface aClass11_1402;
   static int anInt1403 = -1;


   static void method1596(RSString var0, byte var1, boolean var2) {
      try {
         if(var1 < 124) {
            aClass93_1401 = (Class93)null;
         }

         if(var2) {
            if(HDToolKit.highDetail && Class3_Sub28_Sub6.aBoolean3594) {
               try {
                  Class42.method1056(Class38.aClass87_665.anApplet1219, new Object[]{var0.method1547(Class3_Sub29.anApplet_Sub1_2588.getCodeBase()).toString()});
                  return;
               } catch (Throwable var6) {
               }
            }

            try {
               Objects.requireNonNull(Class3_Sub29.anApplet_Sub1_2588.getAppletContext()).showDocument(var0.method1547(Class3_Sub29.anApplet_Sub1_2588.getCodeBase()), "_blank");
            } catch (Exception var4) {
            }
         } else {
            try {
               Objects.requireNonNull(Class3_Sub29.anApplet_Sub1_2588.getAppletContext()).showDocument(var0.method1547(Class3_Sub29.anApplet_Sub1_2588.getCodeBase()), "_top");
            } catch (Exception var5) {
            }
         }

      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "nf.C(" + (var0 != null?"{...}":"null") + ',' + var1 + ',' + var2 + ')');
      }
   }

   static Frame method1597(int var2, int var3, int var4, Signlink var5) {
      try {
         if(var5.method1432(false)) {
            if(0 == var2) {
               Class106[] var6 = Class3_Sub28_Sub10_Sub2.method596(var5);

               boolean var7 = false;

               for(int var8 = 0; var6.length > var8; ++var8) {
                  if(var4 == var6[var8].anInt1447 && var3 == var6[var8].anInt1449 && (!var7 || var6[var8].anInt1450 > var2)) {
                     var2 = var6[var8].anInt1450;
                     var7 = true;
                  }
               }

               if(!var7) {
                  return null;
               }
            }

            Class64 var10 = var5.method1450(0, var2, var3, var4);

            while(0 == var10.anInt978) {
               Class3_Sub13_Sub34.method331(10L, 64);
            }

            Frame var11 = (Frame)var10.anObject974;
            if(null == var11) {
               return null;
            } else if (2 == var10.anInt978) {
               Class3_Sub28_Sub10_Sub1.method593(var11, var5);
               return null;
            } else {
               return var11;
            }
         } else {
            return null;
         }
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "nf.D(" + 2 + ',' + 0 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + (var5 != null?"{...}":"null") + ')');
      }
   }

   public static void method1598(int var0) {
      try {
         if(var0 <= -106) {
            aShortArray1398 = null;
            aClass11_1402 = null;
            aClass93_1401 = null;
            aClass33_1399 = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "nf.B(" + var0 + ')');
      }
   }

   static int method1599(int var0, int var1, byte[] var2, byte var3) {
      try {
         int var4 = -1;

         for(int var6 = var0; var1 > var6; ++var6) {
            var4 = var4 >>> 8 ^ Class36.anIntArray634[255 & (var4 ^ var2[var6])];
         }

         var4 = ~var4;
         return var4;
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "nf.A(" + var0 + ',' + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

}
