package org.runite.client;

final class Class162 {

   static int anInt2036;
   static int anInt2038 = 0;


   static void method2203(Player var0) {
      try {
         Class3_Sub9 var2 = (Class3_Sub9) Unsorted.aHashTable_4046.get(var0.displayName.toLong());

         if(null != var2) {
            if(var2.aClass3_Sub24_Sub1_2312 != null) {
               Class3_Sub26.aClass3_Sub24_Sub2_2563.method461(var2.aClass3_Sub24_Sub1_2312);
               var2.aClass3_Sub24_Sub1_2312 = null;
            }

            var2.unlink();
         }

      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "wc.B(" + (var0 != null?"{...}":"null") + ',' + 8 + ')');
      }
   }

   static void method2204(DataBuffer var0) {
      try {
         if(null != Unsorted.aClass30_1039) {
            try {
               Unsorted.aClass30_1039.method984(-117, 0L);
               Unsorted.aClass30_1039.method983(var0.buffer, var0.index, -903171152, 24);
            } catch (Exception var3) {
            }
         }

          var0.index += 24;
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "wc.E(" + "null" + ',' + 120 + ')');
      }
   }

   static void method2206(int var1) {
      try {
         InterfaceWidget var2 = Class3_Sub24_Sub3.getWidget(4, var1);
         var2.a();
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "wc.A(" + true + ',' + var1 + ')');
      }
   }

}
