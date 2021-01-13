package org.runite.client;

import org.rs09.client.filestore.resources.configs.enums.EnumDefinition;
import org.rs09.client.filestore.resources.configs.enums.EnumDefinitionProvider;

public final class Class7 implements Interface4 {

   static CacheIndex aClass153_2160;
   static int anInt2161 = -1;
   static int anInt2162;
   public static int anInt2166 = 0;
   static short[] aShortArray2167 = new short[]{(short)30, (short)6, (short)31, (short)29, (short)10, (short)44, (short)37, (short)57};


   static void method831(String var1) {
      System.out.println("Error: " + InterfaceWidget.a("%0a", "\n", var1));
   }

   public static RSInterface getRSInterface(int interfaceHash) {
      try {
         int windowId = interfaceHash >> 16;

         int componentId = '\uffff' & interfaceHash;
         if (GameObject.aClass11ArrayArray1834.length <= windowId || windowId < 0) {
        	 return null;
         }
         if(GameObject.aClass11ArrayArray1834[windowId] == null || GameObject.aClass11ArrayArray1834[windowId].length <= componentId || null == GameObject.aClass11ArrayArray1834[windowId][componentId]) {
            boolean var4 = Unsorted.loadInterface(windowId);
            if(!var4) {
               return null;
            }
         }
         if (GameObject.aClass11ArrayArray1834[windowId].length <= componentId) {
        	 return null;
         }
         return GameObject.aClass11ArrayArray1834[windowId][componentId];
      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "af.F(" + interfaceHash + ')');
      }
   }

   public final RSString method20(int var1, int[] var2, int var3, long var4) {
      try {
         if(var1 == 0) {
            EnumDefinition var6 = EnumDefinitionProvider.provide(var2[0]);
            return var6.getString((int)var4);
         } else if (var1 == 1 || var1 == 10) {
            ItemDefinition var8 = Class38.getItemDefinition((int) var4);
            return var8.name;
         } else {
            return var1 != 6 && var1 != 7 && 11 != var1 ? (var3 != 4936 ? null : null) : EnumDefinitionProvider.provide(var2[0]).getString((int) var4);
         }
      } catch (RuntimeException var7) {
         throw ClientErrorException.clientError(var7, "af.A(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ',' + var4 + ')');
      }
   }

   static void method834() {
      try {
         Unsorted.method1250(43, false);
         System.gc();
         Class117.method1719(25);

      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "af.D(" + (byte) -86 + ')');
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
