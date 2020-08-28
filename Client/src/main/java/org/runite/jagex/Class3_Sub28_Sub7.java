package org.runite.jagex;
import java.io.IOException;

abstract class Class3_Sub28_Sub7 extends Node {

   static RSString aClass94_3601 = RSString.createRSString(")3runescape)3com)4l=");
   static int anInt3602;
   static int anInt3603;
   static boolean aBoolean3604 = true;
   static int[][][] anIntArrayArrayArray3605;
   static int anInt3606;
   static int[] anIntArray3607 = new int[]{0, 2, 2, 2, 1, 1, 2, 2, 1, 3, 1, 1};
   static int updateStamp;


   static Class3_Sub28_Sub16_Sub2 method562(CacheIndex var0, int var2) {
      try {
    	//  System.out.println("Class 3_Sub28_Sub16_Sub2 " + var2);
         return Class75_Sub4.method1351(var0, 0, var2, -30901)? Class3_Sub28_Sub9.method578() :null;
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "gf.O(" + (var0 != null?"{...}":"null") + ',' + 0 + ',' + var2 + ',' + (byte) 39 + ')');
      }
   }

   public static void method563(int var0) {
      try {
         anIntArrayArrayArray3605 = (int[][][])null;
         aClass94_3601 = null;
         anIntArray3607 = null;
         if(var0 != 3) {
            anInt3603 = -108;
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "gf.Q(" + var0 + ')');
      }
   }

   static void method564(Signlink var0) {
      try {
         Class3_Sub28_Sub10.anInt3625 = 3;
         Class25.method957(true);
         aBoolean3604 = true;
         Class3_Sub13_Sub15.aBoolean3184 = true;
         Class128.aBoolean1685 = true;
         Class3_Sub28_Sub9.anInt3622 = 0;
         Class3_Sub13_Sub5.anInt3071 = 0;
         KeyboardListener.aBoolean1905 = true;
         WorldListEntry.aBoolean2623 = true;
         RSInterface.aBoolean236 = true;
         Class14.anInt340 = 127;
         Class38.aBoolean661 = true;
         Class140_Sub6.aBoolean2910 = true;
         Class3_Sub13.anInt2378 = 0;
         Class80.anInt1137 = 2;
         Class3_Sub13_Sub22.aBoolean3275 = true;
         Class106.aBoolean1441 = true;
         Class9.anInt120 = 255;
         Class25.aBoolean488 = true;
         Class3_Sub28_Sub14.anInt3671 = 0;
         Class122 var2 = null;
         CS2Script.anInt2453 = 127;
         if(Class3_Sub24_Sub3.anInt3492 >= 96) {
            Class127_Sub1.method1758(2);
         } else {
            Class127_Sub1.method1758(0);
         }

         RSString.anInt2148 = 0;
         Class3_Sub20.anInt2488 = 0;
         Class15.aBoolean346 = false;
         Class163_Sub3.aBoolean3004 = true;
         RSString.aBoolean2146 = false;
         Class73.aBoolean1080 = false;
         anInt2577 = 0;

         try {
            Class64 var3 = var0.method1433("runescape", 12);

            while(0 == var3.anInt978) {
               Class3_Sub13_Sub34.method331(1L, 64);
            }

            if(var3.anInt978 == 1) {
               var2 = (Class122)var3.anObject974;
               byte[] var4 = new byte[(int)var2.method1741(-1)];

               int var6;
               for(int var5 = 0; var4.length > var5; var5 += var6) {
                  var6 = var2.method1739(var5, 0, var4.length - var5, var4);
                  if(var6 == -1) {
                     throw new IOException("EOF");
                  }
               }

               Class79.method1390(new RSByteBuffer(var4));
            }
         } catch (Exception var8) {
         }

         try {
            if(var2 != null) {
               var2.close(1);
            }
         } catch (Exception var7) {
         }

      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "gf.F(" + (var0 != null?"{...}":"null") + ',' + 0 + ')');
      }
   }

   static void method565(int var1, int var2) {
      try {
         Class82.anInt1150 = -Class3_Sub13_Sub21.anInt3256 + var1;

         int var3 = -((int)((float)Class3_Sub28_Sub3.aClass11_3551.anInt168 / Class44.aFloat727)) + Class82.anInt1150;
         int var4 = Class82.anInt1150 + (int)((float)Class3_Sub28_Sub3.aClass11_3551.anInt168 / Class44.aFloat727);
         if(var3 < 0) {
            Class82.anInt1150 = (int)((float)Class3_Sub28_Sub3.aClass11_3551.anInt168 / Class44.aFloat727);
         }

         Class3_Sub13_Sub30.anInt3362 = Class108.anInt1460 + -1 + Class2.anInt65 + -var2;
         int var6 = (int)((float)Class3_Sub28_Sub3.aClass11_3551.anInt193 / Class44.aFloat727) + Class3_Sub13_Sub30.anInt3362;
         int var5 = Class3_Sub13_Sub30.anInt3362 - (int)((float)Class3_Sub28_Sub3.aClass11_3551.anInt193 / Class44.aFloat727);
         if(Class23.anInt455 < var4) {
            Class82.anInt1150 = Class23.anInt455 + -((int)((float)Class3_Sub28_Sub3.aClass11_3551.anInt168 / Class44.aFloat727));
         }

         if(var5 < 0) {
            Class3_Sub13_Sub30.anInt3362 = (int)((float)Class3_Sub28_Sub3.aClass11_3551.anInt193 / Class44.aFloat727);
         }

         if(var6 > Class108.anInt1460) {
            Class3_Sub13_Sub30.anInt3362 = -((int)((float)Class3_Sub28_Sub3.aClass11_3551.anInt193 / Class44.aFloat727)) + Class108.anInt1460;
         }

      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "gf.E(" + (byte) 86 + ',' + var1 + ',' + var2 + ')');
      }
   }

   static void method566(RSString var0, int var2) {
      try {
         Class3_Sub28_Sub6 var3 = Class3_Sub24_Sub3.method466(3, var2);
         var3.g();

         var3.aClass94_3599 = var0;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "gf.P(" + (var0 != null?"{...}":"null") + ',' + 0 + ',' + var2 + ')');
      }
   }

   abstract Object method567();

   abstract boolean method568();

}
