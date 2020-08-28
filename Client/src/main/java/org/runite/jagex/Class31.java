package org.runite.jagex;

import javax.media.opengl.GL;

final class Class31 {

   static int anInt580 = 0;
   private static Class61 aClass61_581 = new Class61();
   static int anInt582 = 0;
   private static long aLong583 = 0L;
   static int memory2D = 0;
   static int anInt585 = 0;
   private static Class61 aClass61_586 = new Class61();
   private static Class61 aClass61_587 = new Class61();
   private static Class61 aClass61_588 = new Class61();
   private static int[] anIntArray589 = new int[1000];


   static synchronized void method985(int var0, int var1, int var2) {
      if(var2 == anInt582) {
         Class3_Sub18 var3 = new Class3_Sub18(var1);
         var3.aLong71 = (long)var0;
         aClass61_587.method1215(var3);
      }
   }

   static synchronized void method986(int var0, int var1) {
      if(var1 == anInt582) {
         Class3_Sub18 var2 = new Class3_Sub18();
         var2.aLong71 = (long)var0;
         aClass61_588.method1215(var2);
      }
   }

   public static void method987() {
      aClass61_581 = null;
      aClass61_586 = null;
      aClass61_587 = null;
      aClass61_588 = null;
      anIntArray589 = null;
   }

   static synchronized void method988() {
      ++anInt582;
      aClass61_581.method1211(-110);
      aClass61_586.method1211(-88);
      aClass61_587.method1211(-123);
      aClass61_588.method1211(-115);
      anInt585 = 0;
      memory2D = 0;
      anInt580 = 0;
   }

   static synchronized void method989(int var0, int var1, int var2) {
      if(var2 == anInt582) {
         Class3_Sub18 var3 = new Class3_Sub18(var1);
         var3.aLong71 = (long)var0;
         aClass61_581.method1215(var3);
      }
   }

   static synchronized void method990() {
      GL var0 = HDToolKit.gl;
      int var1 = 0;

      while(true) {
         Class3_Sub18 var2 = (Class3_Sub18)aClass61_581.method1220();
         if(var2 == null) {
            if(var1 > 0) {
               var0.glDeleteBuffersARB(var1, anIntArray589, 0);
               var1 = 0;
            }

            while(true) {
               var2 = (Class3_Sub18)aClass61_586.method1220();
               if(var2 == null) {
                  while(true) {
                     var2 = (Class3_Sub18)aClass61_587.method1220();
                     if(var2 == null) {
                        if(var1 > 0) {
                           var0.glDeleteTextures(var1, anIntArray589, 0);
                        }

                        while(true) {
                           var2 = (Class3_Sub18)aClass61_588.method1220();
                           if(var2 == null) {
                              if(anInt585 + memory2D + anInt580 > 100663296 && Class5.method830((byte)-55) > aLong583 + 60000L) {
                                 System.gc();
                                 aLong583 = Class5.method830((byte)-55);
                              }

                              return;
                           }

                           int var3 = (int)var2.aLong71;
                           var0.glDeleteLists(var3, 1);
                        }
                     }

                     anIntArray589[var1++] = (int)var2.aLong71;
                     anInt580 -= var2.anInt2467;
                     if(var1 == 1000) {
                        var0.glDeleteTextures(var1, anIntArray589, 0);
                        var1 = 0;
                     }
                  }
               }

               anIntArray589[var1++] = (int)var2.aLong71;
               memory2D -= var2.anInt2467;
               if(var1 == 1000) {
                  var0.glDeleteTextures(var1, anIntArray589, 0);
                  var1 = 0;
               }
            }
         }

         anIntArray589[var1++] = (int)var2.aLong71;
         anInt585 -= var2.anInt2467;
         if(var1 == 1000) {
            var0.glDeleteBuffersARB(var1, anIntArray589, 0);
            var1 = 0;
         }
      }
   }

   static synchronized void method991(int var0, int var1, int var2) {
      if(var2 == anInt582) {
         Class3_Sub18 var3 = new Class3_Sub18(var1);
         var3.aLong71 = (long)var0;
         aClass61_586.method1215(var3);
      }
   }

}
