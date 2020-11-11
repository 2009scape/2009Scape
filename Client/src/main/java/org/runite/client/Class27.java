package org.runite.client;

import org.rs09.client.data.ReferenceCache;

final class Class27 {

   static int[] anIntArray510 = new int[]{768, 1024, 1280, 512, 1536, 256, 0, 1792};
   static ReferenceCache aReferenceCache_511 = new ReferenceCache(30);
   static int[] anIntArray512 = new int[500];
   static int anInt515 = -1;
   static int anInt517 = 0;
   static Class3_Sub28_Sub16_Sub2 aClass3_Sub28_Sub16_Sub2_518;
   static Class157 aClass157_524;
  
   static RSInterface aClass11_526 = null;

   static int method961() {
      try {
         return Class23.anInt453 == 0 ?0: Unsorted.anShaderInterfaceArray70[Class23.anInt453].method24();
      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "ef.B(" + 1536 + ')');
      }
   }

}
