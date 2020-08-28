package org.runite.jagex;
import org.runite.Configurations;

import java.awt.*;

final class Class58 {

   static int anInt909 = -1;
   static RSString COMMAND_TOGGLE_FPSON = RSString.createRSString("::fpson");
   static int[][][] anIntArrayArrayArray911 = new int[2][][];
   static int[] anIntArray912 = new int[14];
   static boolean aBoolean913 = false;
   static int[][][] anIntArrayArrayArray914;
   static Interface4 anInterface4_915 = null;
   static int anInt916;
   static Class66 aClass66_917;


   public static void method1193(int var0) {
      try {
         anInterface4_915 = null;
         anIntArrayArrayArray911 = (int[][][])null;
         anIntArrayArrayArray914 = (int[][][])null;
         aClass66_917 = null;
         anIntArray912 = null;
         if(var0 != -26723) {
         }

         COMMAND_TOGGLE_FPSON = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "id.C(" + var0 + ')');
      }
   }

   static void method1194() {
      try {
         if(null != Class3_Sub21.aClass155_2491) {
            Class3_Sub21.aClass155_2491.method2153();
         }

         if(null != WorldListEntry.aClass155_2627) {
            WorldListEntry.aClass155_2627.method2153();
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "id.A(" + -16385 + ')');
      }
   }

   static Class155 method1195(int var0, Signlink var1, Component var2, int var3) {
      try {
         if(Class21.anInt443 == 0) {
            throw new IllegalStateException();
         } else if(0 <= var3 && 2 > var3) {
            if(var0 < 256) {
               var0 = 256;
            }

            try {
               Class155 var9 = (Class155)Class.forName(Configurations.PACKAGE_JAGEX + ".Class155_Sub2").newInstance();
               var9.anInt1989 = var0;
               var9.anIntArray1975 = new int[(!RSString.aBoolean2150?1:2) * 256];
               var9.method2164(var2);
               var9.anInt1990 = (var0 & -1024) - -1024;
               if(var9.anInt1990 > 16384) {
                  var9.anInt1990 = 16384;
               }

               var9.method2150(var9.anInt1990);
               if(Class3_Sub24_Sub4.anInt3507 > 0 && null == Class38_Sub1.aClass15_2613) {
                  Class38_Sub1.aClass15_2613 = new Class15();
                  Class38_Sub1.aClass15_2613.aClass87_350 = var1;
                  var1.method1451(0, Class3_Sub24_Sub4.anInt3507, Class38_Sub1.aClass15_2613);
               }

               if(Class38_Sub1.aClass15_2613 != null) {
                  if(null != Class38_Sub1.aClass15_2613.aClass155Array352[var3]) {
                     throw new IllegalArgumentException();
                  }

                  Class38_Sub1.aClass15_2613.aClass155Array352[var3] = var9;
               }

               return var9;
            } catch (Throwable var7) {

               try {
                  Class155_Sub1 var5 = new Class155_Sub1(var1, var3);
                  var5.anIntArray1975 = new int[256 * (RSString.aBoolean2150?2:1)];
                  var5.anInt1989 = var0;
                  var5.method2164(var2);
                  var5.anInt1990 = 16384;
                  var5.method2150(var5.anInt1990);
                  if(Class3_Sub24_Sub4.anInt3507 > 0 && null == Class38_Sub1.aClass15_2613) {
                     Class38_Sub1.aClass15_2613 = new Class15();
                     Class38_Sub1.aClass15_2613.aClass87_350 = var1;
                     var1.method1451(0, Class3_Sub24_Sub4.anInt3507, Class38_Sub1.aClass15_2613);
                  }

                  if(Class38_Sub1.aClass15_2613 != null) {
                     if(Class38_Sub1.aClass15_2613.aClass155Array352[var3] != null) {
                        throw new IllegalArgumentException();
                     }

                     Class38_Sub1.aClass15_2613.aClass155Array352[var3] = var5;
                  }

                  return var5;
               } catch (Throwable var6) {
                  return new Class155();
               }
            }
         } else {
            throw new IllegalArgumentException();
         }
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "id.D(" + var0 + ',' + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ',' + 14 + ')');
      }
   }

   static void method1196(int var3, int var4) {
      try {
         Class3_Sub28_Sub18.anInt3765 = var4;

         Class101.anInt1425 = 0;
         Class159.anInt2020 = 0;
         Class57.anInt902 = var3;
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "id.B(" + 0 + ',' + 0 + ',' + (byte) 111 + ',' + var3 + ',' + var4 + ')');
      }
   }

   static void method1197(CacheIndex var0) {
      try {

         Class46.aClass153_737 = var0;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "id.E(" + (var0 != null?"{...}":"null") + ',' + (byte) 69 + ')');
      }
   }

}
