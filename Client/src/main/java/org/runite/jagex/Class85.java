package org.runite.jagex;

final class Class85 {

   static int anInt1166;
   static boolean aBoolean1167 = false;
   static AnimationDefinition[] aClass142Array1168 = new AnimationDefinition[14];
   static float aFloat1169;
   static int anInt1170;
   static CacheIndex aClass153_1171;
   static int anInt1172;
   static int anInt1174 = 99;


   static final int method1423(boolean var0, RSByteBuffer var1, RSString var2) {
      try {
         if(var0) {
            method1426(17);
         }

         int var3 = var1.index;
         byte[] var4 = var2.method1568(0);
         var1.method768(-32769, var4.length);
         var1.index += Class3_Sub13_Sub9.aClass36_3112.method1015(var4.length, -81, var1.buffer, var4, 0, var1.index);
         return var1.index + -var3;
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "lg.A(" + var0 + ',' + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   static final AbstractIndexedSprite[] method1424(CacheIndex var0, byte var1, int var2, int var3) {
	      try {
	         if(var1 != -12) {
	            anInt1174 = 37;
	         }

	         return Class75_Sub4.method1351(var0, var2, var3, -30901)?Class3_Sub13_Sub36.method343(1854847236):null;
	      } catch (RuntimeException var5) {
	         throw Class44.clientError(var5, "lg.C(" + (var0 != null?"{...}":"null") + ',' + var1 + ',' + var2 + ',' + var3 + ')');
	      }
	   }

   static final void method1425(int var0) {
      Class3_Sub13_Sub35.anInt3419 = var0;

      for(int var1 = 0; var1 < IOHandler.anInt1234; ++var1) {
         for(int var2 = 0; var2 < Class3_Sub13_Sub15.anInt3179; ++var2) {
            if(Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2] == null) {
               Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2] = new Class3_Sub2(var0, var1, var2);
            }
         }
      }

   }

   public static void method1426(int var0) {
      try {
         aClass153_1171 = null;
         if(var0 != -25247) {
            aClass142Array1168 = (AnimationDefinition[])null;
         }
         aClass142Array1168 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "lg.B(" + var0 + ')');
      }
   }

}
