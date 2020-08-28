package org.runite.jagex;

final class Class60 {

   static int anInt930;
   static int anInt932;
   static int anInt934;
   static int anInt936;


   public static void method1206(byte var0) {
      try {
         int var1 = 50 % ((-41 - var0) / 57);
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ig.C(" + var0 + ')');
      }
   }

   static final void method1207(int var0) {
      try {
         for(int var1 = 0; Class163.localNPCCount > var1; ++var1) {
            int var2 = Class15.localNPCIndexes[var1];
            NPC var3 = Class3_Sub13_Sub24.npcs[var2];
            if(null != var3) {
               OutputStream_Sub1.method68(var3.definition.size, 2279, var3);
            }
         }

         if(var0 > -4) {
            method1208((byte)25, 108);
         }

      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "ig.A(" + var0 + ')');
      }
   }

   static final void method1208(byte var0, int var1) {
      try {
         if(-1 != var1) {
            if(Class130.aBooleanArray1703[var1]) {
               Class3_Sub13_Sub29.aClass153_3361.method2128(7561, var1);
               if(null != GameObject.aClass11ArrayArray1834[var1]) {
                  boolean var2 = true;

                  for(int var3 = 0; GameObject.aClass11ArrayArray1834[var1].length > var3; ++var3) {
                     if(GameObject.aClass11ArrayArray1834[var1][var3] != null) {
                        if(GameObject.aClass11ArrayArray1834[var1][var3].type == 2) {
                           var2 = false;
                        } else {
                           GameObject.aClass11ArrayArray1834[var1][var3] = null;
                        }
                     }
                  }

                  int var4 = 43 / ((-70 - var0) / 47);
                  if(var2) {
                     GameObject.aClass11ArrayArray1834[var1] = null;
                  }

                  Class130.aBooleanArray1703[var1] = false;
               }
            }
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "ig.B(" + var0 + ',' + var1 + ')');
      }
   }

}
