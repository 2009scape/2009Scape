package org.runite.jagex;

class Class3 {

   long aLong71;
   static int anInt72 = 0;
   static boolean[] aBooleanArray73 = new boolean[200];
   Class3 aClass3_74;
   Class3 aClass3_76;
   static Class61 aClass61_78 = new Class61();


   final boolean method82() {
      try {
         return null != this.aClass3_76;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ab.I(" + 0 + ')');
      }
   }

   public static void method83(byte var0) {
      try {
         ItemDefinition.stringsStack = null;
         if(var0 != 30) {
            method84((RSString)null, 89);
         }

         aClass61_78 = null;
         aBooleanArray73 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ab.H(" + var0 + ')');
      }
   }

   static void method84(RSString var0, int var1) {
      try {
         int var2 = Class100.method1602(var0);
         if(var2 != -1) {
            Class3_Sub28_Sub7.method565(Class119.aClass131_1624.aShortArray1727[var2], Class119.aClass131_1624.aShortArray1718[var2]);
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ab.N(" + (var0 != null?"{...}":"null") + ',' + var1 + ')');
      }
   }

   final void method86(int var1) {
      try {
         if(null != this.aClass3_76) {
            this.aClass3_76.aClass3_74 = this.aClass3_74;
            this.aClass3_74.aClass3_76 = this.aClass3_76;
            if(var1 != -1024) {
               this.method86(-35);
            }

            this.aClass3_76 = null;
            this.aClass3_74 = null;
         }
      } catch (RuntimeException var3) {
    	  var3.printStackTrace();
         throw Class44.clientError(var3, "ab.L(" + var1 + ')');
      }
   }

   static void method87(int var0, int var1) {
      try {
         if(var0 >= -20) {
            method83((byte)44);
         }

         if(0 != var1) {
            if(var1 == 1) {
               Translation.englishToGerman(false);
            } else {
               if(2 != var1) {
                  throw new RuntimeException();
               }

               Translation.englishToFrench();
            }

         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ab.K(" + var0 + ',' + var1 + ')');
      }
   }

   static Class106[] method88() {
      try {

         if(Class56.aClass106Array890 == null) {
            Class106[] var1 = Class3_Sub28_Sub10_Sub2.method596(Class38.aClass87_665);
            Class106[] var2 = new Class106[var1.length];
            int var3 = 0;

            label58:
            for(int var4 = 0; var4 < var1.length; ++var4) {
               Class106 var5 = var1[var4];
               if((0 >= var5.anInt1450 || var5.anInt1450 >= 24) && var5.anInt1447 >= 800 && 600 <= var5.anInt1449) {
                  for(int var6 = 0; var3 > var6; ++var6) {
                     Class106 var7 = var2[var6];
                     if(var5.anInt1447 == var7.anInt1447 && var5.anInt1449 == var7.anInt1449) {
                        if(var7.anInt1450 < var5.anInt1450) {
                           var2[var6] = var5;
                        }
                        continue label58;
                     }
                  }

                  var2[var3] = var5;
                  ++var3;
               }
            }

            Class56.aClass106Array890 = new Class106[var3];
            Class76.method1362(var2, 0, Class56.aClass106Array890, 0, var3);
            int[] var9 = new int[Class56.aClass106Array890.length];

            for(int var10 = 0; Class56.aClass106Array890.length > var10; ++var10) {
               Class106 var11 = Class56.aClass106Array890[var10];
               var9[var10] = var11.anInt1449 * var11.anInt1447;
            }

            Class108.method1658(var9, Class56.aClass106Array890);
         }

         return Class56.aClass106Array890;
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "ab.M(" + (byte) 28 + ')');
      }
   }

   static void method89(CacheIndex var1, CacheIndex var2, CacheIndex var3, CacheIndex var4) {
      try {
         Class12.aClass153_323 = var2;
         Class97.aClass153_1378 = var1;
         Class3_Sub13_Sub29.aClass153_3361 = var3;
         Class119.aClass153_1628 = var4;

         GameObject.aClass11ArrayArray1834 = new RSInterface[Class3_Sub13_Sub29.aClass153_3361.method2121()][];
         Class130.aBooleanArray1703 = new boolean[Class3_Sub13_Sub29.aClass153_3361.method2121()];
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "ab.J(" + true + ',' + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ',' + (var3 != null?"{...}":"null") + ',' + (var4 != null?"{...}":"null") + ')');
      }
   }

}
