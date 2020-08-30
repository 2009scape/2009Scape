package org.runite.jagex;

final class Class7 implements Interface4 {

   static CacheIndex aClass153_2160;
   static int anInt2161 = -1;
   static int anInt2162;
   static int anInt2166 = 0;
   static short[] aShortArray2167 = new short[]{(short)30, (short)6, (short)31, (short)29, (short)10, (short)44, (short)37, (short)57};
   static RSString aClass94_2168 = RSString.createRSString("<br>");


   static void method831(String var1) {
      System.out.println("Error: " + Class3_Sub28_Sub6.a("%0a", "\n", var1));
   }

   static RSInterface getRSInterface(byte var0, int interfaceHash) {
      try {
         int windowId = interfaceHash >> 16;
         if(var0 < 108) {
            getRSInterface((byte)87, 19);
         }

         int componentId = '\uffff' & interfaceHash;
         if (GameObject.aClass11ArrayArray1834.length <= windowId || windowId < 0) {
        	 return null;
         }
         if(GameObject.aClass11ArrayArray1834[windowId] == null || GameObject.aClass11ArrayArray1834[windowId].length <= componentId || null == GameObject.aClass11ArrayArray1834[windowId][componentId]) {
            boolean var4 = Canvas_Sub2.loadInterface(windowId);
            if(!var4) {
               return null;
            }
         }
         if (GameObject.aClass11ArrayArray1834[windowId].length <= componentId) {
        	 return null;
         }
         return GameObject.aClass11ArrayArray1834[windowId][componentId];
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "af.F(" + var0 + ',' + interfaceHash + ')');
      }
   }

   public static void method833(byte var0) {
      try {
         aShortArray2167 = null;
         aClass153_2160 = null;
         aClass94_2168 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "af.E(" + var0 + ')');
      }
   }

   public final RSString method20(int var1, int[] var2, int var3, long var4) {
      try {
         if(var1 == 0) {
            Class3_Sub28_Sub13 var6 = Class3_Sub13_Sub36.method342(var2[0]);
            return var6.method616((int)var4, (byte)120);
         } else if (var1 == 1 || var1 == 10) {
            ItemDefinition var8 = Class38.getItemDefinition((int) var4, (byte) 82);
            return var8.name;
         } else {
            return var1 != 6 && var1 != 7 && 11 != var1 ? (var3 != 4936 ? (RSString) null : null) : Class3_Sub13_Sub36.method342(var2[0]).method616((int) var4, (byte) -69);
         }
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "af.A(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ',' + var4 + ')');
      }
   }

   static void method834() {
      try {
         Class66.method1250(43, false);
         System.gc();
         Class117.method1719(25);

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "af.D(" + (byte) -86 + ')');
      }
   }

   static boolean method835(int var0, int var1, int var2, int var3, int var4, int var5, GameObject var6, long var8) {
      if(var6 == null) {
         return true;
      } else {
         int var10 = var1 * 128 + 64 * var4;
         int var11 = var2 * 128 + 64 * var5;
         return Class56.method1189(var0, var1, var2, var4, var5, var10, var11, var3, var6, 0, false, var8);
      }
   }

}
