package org.runite.jagex;

import javax.media.opengl.GL;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

final class Class3_Sub28_Sub18 extends Node {

   static int anInt3757;
   private int anInt3759;
   private final int anInt3760;
   static int anInt3764;
   static int anInt3765 = 100;
   static int anInt3766 = 0;
   private int anInt3767 = 0;
   static int[] anIntArray3768 = new int[100];
   static boolean aBoolean3769 = false;


   protected final void finalize() throws Throwable {
      try {
         if(this.anInt3759 != -1) {
            Class31.method985(this.anInt3759, this.anInt3767, this.anInt3760);
            this.anInt3759 = -1;
            this.anInt3767 = 0;
         }

         super.finalize();
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "sd.finalize()");
      }
   }

   static void method709(int var0, int var1) {
      Class3_Sub2 var2 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[0][var0][var1];

      for(int var3 = 0; var3 < 3; ++var3) {
         Class3_Sub2 var4 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var3][var0][var1] = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var3 + 1][var0][var1];
         if(var4 != null) {
            --var4.anInt2244;

            for(int var5 = 0; var5 < var4.anInt2223; ++var5) {
               Class25 var6 = var4.aClass25Array2221[var5];
               if((var6.aLong498 >> 29 & 3L) == 2L && var6.anInt483 == var0 && var6.anInt478 == var1) {
                  --var6.anInt493;
               }
            }
         }
      }

      if(Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[0][var0][var1] == null) {
         Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[0][var0][var1] = new Class3_Sub2(0, var0, var1);
      }

      Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[0][var0][var1].aClass3_Sub2_2235 = var2;
      Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[3][var0][var1] = null;
   }

   static void method710() {
      try {
         Class44.aClass93_725.method1523((byte)-104);
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "sd.C(" + (byte) 126 + ')');
      }
   }

   public static void method711(int var0) {
      try {
         anIntArray3768 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "sd.B(" + var0 + ')');
      }
   }

   final void method712() {
      try {
         int var2 = Class27.method961();
         if((1 & var2) == 0) {
            HDToolKit.bindTexture2D(this.anInt3759);
         }

         if(0 == (var2 & 2)) {
            HDToolKit.method1856(0);
         }

         if((var2 & 4) == 0) {
            HDToolKit.method1847(0);
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "sd.E(" + (byte) 6 + ')');
      }
   }

   static void method713() {
      try {
         try {
            Method var1 = Runtime.class.getMethod("maxMemory", new Class[0]);
            try {
               Runtime var2 = Runtime.getRuntime();
               Long var3 = (Long)var1.invoke(var2, (Object[])null);
               Class3_Sub24_Sub3.anInt3492 = (int)(var3.longValue() / 1048576L) - -1;
            } catch (Throwable var4) {
            }
         } catch (Exception var5) {
         }

      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "sd.A(" + 0 + ')');
      }
   }

   Class3_Sub28_Sub18(int var1) {
      try {
         GL var2 = HDToolKit.gl;
         int[] var3 = new int[1];
         var2.glGenTextures(1, var3, 0);
         this.anInt3759 = var3[0];
         this.anInt3760 = Class31.anInt582;
         HDToolKit.bindTexture2D(this.anInt3759);
         int var4 = Class51.anIntArray834[var1];
         byte[] var5 = new byte[]{(byte)(var4 >> 16), (byte)(var4 >> 8), (byte)var4, (byte)-1};
         ByteBuffer var6 = ByteBuffer.wrap(var5);
         var2.glTexImage2D(3553, 0, 6408, 1, 1, 0, 6408, 5121, var6);
         var2.glTexParameteri(3553, 10241, 9729);
         var2.glTexParameteri(3553, 10240, 9729);
         Class31.anInt580 += var6.limit() - this.anInt3767;
         this.anInt3767 = var6.limit();
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "sd.<init>(" + var1 + ')');
      }
   }

}
