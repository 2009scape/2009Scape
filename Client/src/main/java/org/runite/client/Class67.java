package org.runite.client;

import org.rs09.client.util.ArrayUtils;
import org.rs09.client.data.ReferenceCache;

final class Class67 implements Runnable {

   static ReferenceCache aReferenceCache_1013 = new ReferenceCache(100);
   static byte[][][] aByteArrayArrayArray1014;
   boolean aBoolean1015 = true;
   Object anObject1016 = new Object();
   static RSInterface aClass11_1017;
   int anInt1018 = 0;
   int[] anIntArray1019 = new int[500];
   int[] anIntArray1020 = new int[500];

   public final void run() {
      try {
         for(; this.aBoolean1015; TimeUtils.sleep(50L)) {
            Object var1 = this.anObject1016;
            synchronized(var1) {
               if(this.anInt1018 < 500) {
                  this.anIntArray1020[this.anInt1018] = Class126.anInt1676;
                  this.anIntArray1019[this.anInt1018] = Unsorted.anInt1709;
                  ++this.anInt1018;
               }
            }
         }

      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "jd.run()");
      }
   }

   static int method1258(byte var0) {
      try {
         Class3_Sub13_Sub17.anInt1780 = 0;
         if(var0 != -53) {
            method1258((byte)-35);
         }

         return Class3_Sub13_Sub17.method251();
      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "jd.D(" + var0 + ')');
      }
   }

   static void method1259(int var0) {
      try {
         InterfaceWidget var2 = Class3_Sub24_Sub3.getWidget(12, var0);
         var2.a();
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "jd.A(" + var0 + ',' + (byte) 109 + ')');
      }
   }

   static void method1260(int var0, int var1, RSInterface[] var2) {
      try {
         for(int var3 = 0; var3 < var2.length; ++var3) {
            RSInterface var4 = var2[var3];
            if(null != var4 && var1 == var4.parentId && (!var4.usingScripts || !Client.method51(var4))) {
               if(var4.type == 0) {
                  if(!var4.usingScripts && Client.method51(var4) && var4 != Class107.aClass11_1453) {
                     continue;
                  }

                  method1260(var0, var4.componentHash, var2);
                  if(var4.aClass11Array262 != null) {
                     method1260(23206, var4.componentHash, var4.aClass11Array262);
                  }

                  Class3_Sub31 var5 = (Class3_Sub31)Class3_Sub13_Sub17.aHashTable_3208.get((long)var4.componentHash);
                  if(var5 != null) {
                     Class52.method1160(-111, var5.anInt2602);
                  }
               }

               if(var4.type == 6) {
                  int var6;
                  if(var4.animationId != -1 || var4.secondAnimationId != -1) {
                     boolean var9 = Class3_Sub28_Sub12.method609(var4, var0 + -23173);
                     if(var9) {
                        var6 = var4.secondAnimationId;
                     } else {
                        var6 = var4.animationId;
                     }

                     if(var6 != -1) {
                        SequenceDefinition var7 = SequenceDefinition.getAnimationDefinition(var6);
                        for(var4.anInt267 += Class106.anInt1446; var7.duration[var4.anInt283] < var4.anInt267; Class20.method909(var4)) {
                           var4.anInt267 -= var7.duration[var4.anInt283];
                           ++var4.anInt283;
                           if(var7.frames.length <= var4.anInt283) {
                              var4.anInt283 -= var7.anInt1865;
                              if(var4.anInt283 < 0 || var7.frames.length <= var4.anInt283) {
                                 var4.anInt283 = 0;
                              }
                           }

                           var4.anInt260 = var4.anInt283 + 1;
                           if(var7.frames.length <= var4.anInt260) {
                              var4.anInt260 -= var7.anInt1865;
                              if(var4.anInt260 < 0 || var7.frames.length <= var4.anInt260) {
                                 var4.anInt260 = -1;
                              }
                           }
                        }
                     }
                  }

                  if(0 != var4.anInt237 && !var4.usingScripts) {
                     int var10 = var4.anInt237 >> 16;
                     var10 *= Class106.anInt1446;
                     var6 = var4.anInt237 << 16 >> 16;
                     var4.anInt182 = 2047 & var10 + var4.anInt182;
                     var6 *= Class106.anInt1446;
                     var4.anInt308 = var4.anInt308 - -var6 & 2047;
                     Class20.method909(var4);
                  }
               }
            }
         }
      } catch (RuntimeException var8) {
         throw ClientErrorException.clientError(var8, "jd.E(" + var0 + ',' + var1 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   static RSString method1261(int var0, int var1, RSString[] var2) {
      try {
         int var4 = 0;

         for(int var5 = 0; var1 > var5; ++var5) {
            if(null == var2[var0 - -var5]) {
               var2[var5 + var0] = TextCore.aClass94_3339;
            }

            var4 += var2[var5 + var0].length;
         }

         byte[] var10 = new byte[var4];
         int var6 = 0;

         for(int var7 = 0; var1 > var7; ++var7) {
            RSString var8 = var2[var7 + var0];
            ArrayUtils.arraycopy(var8.buffer, 0, var10, var6, var8.length);
            var6 += var8.length;
         }

         RSString var11 = new RSString();
         var11.length = var4;

         var11.buffer = var10;
         return var11;
      } catch (RuntimeException var9) {
         throw ClientErrorException.clientError(var9, "jd.C(" + var0 + ',' + var1 + ',' + (var2 != null?"{...}":"null") + ',' + 2774 + ')');
      }
   }

   static int method1262(int var0, int var1) {
      try {
         if(var0 < 20) {
            method1262(15, 87);
         }

         return var1 & 127;
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "jd.F(" + var0 + ',' + var1 + ')');
      }
   }

}
