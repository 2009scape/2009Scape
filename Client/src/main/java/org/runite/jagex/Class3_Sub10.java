package org.runite.jagex;

final class Class3_Sub10 extends Class3 {

   static int anInt2337;
   static int[] anIntArray2338 = new int[]{160, 192, 80, 96, 0, 144, 80, 48, 160};
   static byte[][][] aByteArrayArrayArray2339;
   RSString aClass94_2341;


   static final void method138(RSString var0, int var1) {
      try {
         Class66.method1250(var1 ^ 93, false);
         Class75.method1339(var0, var1 + var1);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "hb.F(" + (var0 != null?"{...}":"null") + ',' + var1 + ')');
      }
   }

   static final void method139(int var0) {
      try {
         if(var0 >= 63) {
            Canvas_Sub1.aClass93_21.method1524(3);
            Class99.aClass93_1401.method1524(3);
            Class3_Sub28_Sub7_Sub1.aClass93_4051.method1524(3);
            Class154.aClass93_1965.method1524(3);
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "hb.D(" + var0 + ')');
      }
   }

   static final boolean method140(int var0, int var1) {
      try {
         int var2 = -11 / ((-29 - var1) / 49);
         return var0 >= 0 && Class3_Sub24_Sub4.aBooleanArray3503.length > var0?Class3_Sub24_Sub4.aBooleanArray3503[var0]:false;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "hb.A(" + var0 + ',' + var1 + ')');
      }
   }

   static final RenderAnimationDefinition getRenderAnimationDefinition(boolean var0, int renderAnimationId) {
      try {
         RenderAnimationDefinition def = (RenderAnimationDefinition)Class154.aClass93_1955.get((long)renderAnimationId, (byte)121);
         if(def == null) {
            byte[] var3 = Class97.aClass153_1372.getFile(32, (byte)-122, renderAnimationId);
            if(var0) {
               aByteArrayArrayArray2339 = (byte[][][])((byte[][][])null);
            }

            def = new RenderAnimationDefinition();
            if(var3 != null) {
               def.parse(-1, new RSByteBuffer(var3));
            }

            def.method899(96);
            Class154.aClass93_1955.put((byte)-96, def, (long)renderAnimationId);
            return def;
         } else {
            return def;
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "hb.E(" + var0 + ',' + renderAnimationId + ')');
      }
   }

   static final void method142(boolean var0) {
      try {
         if(!var0) {
            aByteArrayArrayArray2339 = (byte[][][])((byte[][][])null);
         }

         Class158_Sub1.aClass93_2982.method1524(3);
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "hb.C(" + var0 + ')');
      }
   }

   public static void method143(int var0) {
      try {
         anIntArray2338 = null;
         if(var0 <= -16) {
            aByteArrayArrayArray2339 = (byte[][][])null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "hb.B(" + var0 + ')');
      }
   }

   public Class3_Sub10() {}

   Class3_Sub10(RSString var1, int var2) {
      try {
         this.aClass94_2341 = var1;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "hb.<init>(" + (var1 != null?"{...}":"null") + ',' + var2 + ')');
      }
   }

}
