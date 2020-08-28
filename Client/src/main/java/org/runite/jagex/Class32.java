package org.runite.jagex;

final class Class32 {

   static int anInt590 = -1;
   static RSString aClass94_592 = RSString.createRSString("<img=0>");


   static RSString method992(RSByteBuffer var0) {
      try {

         return Class140_Sub7.method2033(var0);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "fc.C(" + (var0 != null?"{...}":"null") + ',' + 29488 + ')');
      }
   }

   static int method993(int var0, int var2) {
      try {
         int var3 = -128 + (Class65.method1234(4, var2 + '\ub135', var0 - -91923) + (Class65.method1234(2, var2 + 10294, '\u93bd' + var0) + -128 >> 1) - -(Class65.method1234(1, var2, var0) + -128 >> 2));
         var3 = 35 + (int)(0.3D * (double)var3);
          if(var3 >= 10) {
             if(var3 > 60) {
                var3 = 60;
             }
          } else {
             var3 = 10;
          }

          return var3;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "fc.B(" + var0 + ',' + 125 + ',' + var2 + ')');
      }
   }

   public static void method994(int var0) {
      try {
         if(var0 == '\u93bd') {
            aClass94_592 = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "fc.A(" + var0 + ')');
      }
   }

   static void method995() {
      int var0;
      int var1;
      int var2;
      if(Class3_Sub28_Sub10_Sub2.aClass3_Sub2ArrayArrayArray4070 != null) {
         for(var0 = 0; var0 < Class3_Sub28_Sub10_Sub2.aClass3_Sub2ArrayArrayArray4070.length; ++var0) {
            for(var1 = 0; var1 < IOHandler.anInt1234; ++var1) {
               for(var2 = 0; var2 < Class3_Sub13_Sub15.anInt3179; ++var2) {
                  Class3_Sub28_Sub10_Sub2.aClass3_Sub2ArrayArrayArray4070[var0][var1][var2] = null;
               }
            }
         }
      }

      Client.aClass3_Sub11ArrayArray2199 = (Class3_Sub11[][])null;
      if(Class166.aClass3_Sub2ArrayArrayArray2065 != null) {
         for(var0 = 0; var0 < Class166.aClass3_Sub2ArrayArrayArray2065.length; ++var0) {
            for(var1 = 0; var1 < IOHandler.anInt1234; ++var1) {
               for(var2 = 0; var2 < Class3_Sub13_Sub15.anInt3179; ++var2) {
                  Class166.aClass3_Sub2ArrayArrayArray2065[var0][var1][var2] = null;
               }
            }
         }
      }

      Class3_Sub13_Sub28.aClass3_Sub11ArrayArray3346 = (Class3_Sub11[][])null;
      Class3_Sub4.anInt2249 = 0;
      if(Class3_Sub28_Sub8.aClass113Array3610 != null) {
      }

      if(AnimationDefinition.aClass25Array1868 != null) {
         for(var0 = 0; var0 < Class3_Sub13_Sub5.anInt3070; ++var0) {
            AnimationDefinition.aClass25Array1868[var0] = null;
         }

         Class3_Sub13_Sub5.anInt3070 = 0;
      }

      if(Class3_Sub28_Sub10_Sub1.aClass25Array4060 != null) {
         for(var0 = 0; var0 < Class3_Sub28_Sub10_Sub1.aClass25Array4060.length; ++var0) {
            Class3_Sub28_Sub10_Sub1.aClass25Array4060[var0] = null;
         }
      }

   }

   static void method996() {
      try {

         KeyboardListener var1 = Class3_Sub13_Sub3.aClass148_3049;
         synchronized(var1) {
            Class3_Sub28_Sub9.anInt3620 = Class134.anInt1762;
            ++Class3_Sub13_Sub33.anInt3398;
            int var2;
            if(Class3_Sub13.anInt2384 < 0) {
               for(var2 = 0; var2 < 112; ++var2) {
                  ObjectDefinition.aBooleanArray1490[var2] = false;
               }

               Class3_Sub13.anInt2384 = Class133.anInt1744;
            } else {
               while(Class3_Sub13.anInt2384 != Class133.anInt1744) {
                  var2 = Class151_Sub1.anIntArray2952[Class133.anInt1744];
                  Class133.anInt1744 = 127 & 1 + Class133.anInt1744;
                  if(0 <= var2) {
                     ObjectDefinition.aBooleanArray1490[var2] = true;
                  } else {
                     ObjectDefinition.aBooleanArray1490[~var2] = false;
                  }
               }
            }

            Class134.anInt1762 = Class25.anInt491;
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "fc.E(" + -43 + ')');
      }
   }

}
