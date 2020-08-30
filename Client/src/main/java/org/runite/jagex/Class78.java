package org.runite.jagex;

final class Class78 {

   private final RSByteBuffer aClass3_Sub30_1113 = new RSByteBuffer((byte[])null);
   int[] anIntArray1114;
   private int[] anIntArray1115;
   int anInt1116;
   private static byte[] aByteArray1117 = new byte[]{(byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)0, (byte)1, (byte)2, (byte)1, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0};
   private int[] anIntArray1118;
   private long aLong1119;
   private int[] anIntArray1120;
   private int anInt1121;


   public static void method1369() {
      aByteArray1117 = null;
   }

   final long method1370(int var1) {
      return this.aLong1119 + (long)var1 * (long)this.anInt1121;
   }

   final boolean method1371() {
      int var1 = this.anIntArray1115.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         if(this.anIntArray1115[var2] >= 0) {
            return false;
         }
      }

      return true;
   }

   final void method1372(long var1) {
      this.aLong1119 = var1;
      int var3 = this.anIntArray1115.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         this.anIntArray1114[var4] = 0;
         this.anIntArray1120[var4] = 0;
         this.aClass3_Sub30_1113.index = this.anIntArray1118[var4];
         this.method1377(var4);
         this.anIntArray1115[var4] = this.aClass3_Sub30_1113.index;
      }

   }

   final boolean method1373() {
      return this.aClass3_Sub30_1113.buffer != null;
   }

   final int method1374() {
      return this.anIntArray1115.length;
   }

   final int method1375(int var1) {
       return this.method1379(var1);
   }

   final void method1376(int var1) {
      this.aClass3_Sub30_1113.index = this.anIntArray1115[var1];
   }

   final void method1377(int var1) {
      int var2 = this.aClass3_Sub30_1113.method741((byte)119);
      this.anIntArray1114[var1] += var2;
   }

   private int method1378(int var1, int var2) {
      int var4;
      if(var2 == 255) {
         int var7 = this.aClass3_Sub30_1113.getByteB();
         var4 = this.aClass3_Sub30_1113.method741((byte)123);
         if(var7 == 47) {
            this.aClass3_Sub30_1113.index += var4;
            return 1;
         } else if(var7 == 81) {
            int var5 = this.aClass3_Sub30_1113.getTriByte((byte)87);
            var4 -= 3;
            int var6 = this.anIntArray1114[var1];
            this.aLong1119 += (long)var6 * (long)(this.anInt1121 - var5);
            this.anInt1121 = var5;
            this.aClass3_Sub30_1113.index += var4;
            return 2;
         } else {
            this.aClass3_Sub30_1113.index += var4;
            return 3;
         }
      } else {
         byte var3 = aByteArray1117[var2 - 128];
         var4 = var2;
         if(var3 >= 1) {
            var4 = var2 | this.aClass3_Sub30_1113.getByteB() << 8;
         }

         if(var3 >= 2) {
            var4 |= this.aClass3_Sub30_1113.getByteB() << 16;
         }

         return var4;
      }
   }

   private int method1379(int var1) {
      byte var2 = this.aClass3_Sub30_1113.buffer[this.aClass3_Sub30_1113.index];
      int var5;
      if(var2 < 0) {
         var5 = var2 & 255;
         this.anIntArray1120[var1] = var5;
         ++this.aClass3_Sub30_1113.index;
      } else {
         var5 = this.anIntArray1120[var1];
      }

      if(var5 == 240 || var5 == 247) {
         int var3 = this.aClass3_Sub30_1113.method741((byte)126);
         if(var5 == 247 && var3 > 0) {
            int var4 = this.aClass3_Sub30_1113.buffer[this.aClass3_Sub30_1113.index] & 255;
            if(var4 >= 241 && var4 <= 243 || var4 == 246 || var4 == 248 || var4 >= 250 && var4 <= 252 || var4 == 254) {
               ++this.aClass3_Sub30_1113.index;
               this.anIntArray1120[var1] = var4;
               return this.method1378(var1, var4);
            }
         }

         this.aClass3_Sub30_1113.index += var3;
         return 0;
      } else {
         return this.method1378(var1, var5);
      }
   }

   final void method1380(byte[] var1) {
      this.aClass3_Sub30_1113.buffer = var1;
      this.aClass3_Sub30_1113.index = 10;
      int var2 = this.aClass3_Sub30_1113.getShort();
      this.anInt1116 = this.aClass3_Sub30_1113.getShort();
      this.anInt1121 = 500000;
      this.anIntArray1118 = new int[var2];

      int var3;
      int var5;
      for(var3 = 0; var3 < var2; this.aClass3_Sub30_1113.index += var5) {
         int var4 = this.aClass3_Sub30_1113.getInt();
         var5 = this.aClass3_Sub30_1113.getInt();
         if(var4 == 1297379947) {
            this.anIntArray1118[var3] = this.aClass3_Sub30_1113.index;
            ++var3;
         }
      }

      this.aLong1119 = 0L;
      this.anIntArray1115 = new int[var2];

      for(var3 = 0; var3 < var2; ++var3) {
         this.anIntArray1115[var3] = this.anIntArray1118[var3];
      }

      this.anIntArray1114 = new int[var2];
      this.anIntArray1120 = new int[var2];
   }

   final void method1381(int var1) {
      this.anIntArray1115[var1] = this.aClass3_Sub30_1113.index;
   }

   final int method1382() {
      int var1 = this.anIntArray1115.length;
      int var2 = -1;
      int var3 = Integer.MAX_VALUE;

      for(int var4 = 0; var4 < var1; ++var4) {
         if(this.anIntArray1115[var4] >= 0 && this.anIntArray1114[var4] < var3) {
            var2 = var4;
            var3 = this.anIntArray1114[var4];
         }
      }

      return var2;
   }

   final void method1383() {
      this.aClass3_Sub30_1113.buffer = null;
      this.anIntArray1118 = null;
      this.anIntArray1115 = null;
      this.anIntArray1114 = null;
      this.anIntArray1120 = null;
   }

   public Class78() {}

   final void method1384() {
      this.aClass3_Sub30_1113.index = -1;
   }

   Class78(byte[] var1) {
      this.method1380(var1);
   }

}
