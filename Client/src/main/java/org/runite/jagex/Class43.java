package org.runite.jagex;

final class Class43 {

   boolean aBoolean690;
   
   static int anInt692 = 0;
   private int anInt693;
   private int anInt694;
   boolean aBoolean696 = false;
   int anInt697;
   int anInt698;
   private int anInt702;
   int anInt703;
   int anInt704;
   int anInt705;
   short[] aShortArray706;
   float aFloat707;
   int anInt708;
   private int anInt709;
   float aFloat710;
   boolean aBoolean711;
   Class37 aClass37_712;
   int anInt713;
   private int anInt714;
   static int anInt716 = 0;
   float[] aFloatArray717 = new float[4];

   static void method1058(int var0, int var1, int var2, int var3) {
      try {
         if(Class101.anInt1425 <= var1 + -var0 && Class3_Sub28_Sub18.anInt3765 >= var0 + var1 && var3 + -var0 >= Class159.anInt2020 && Class57.anInt902 >= var0 + var3) {
            Class24.method949(var1, var0, var2, var3);
         } else {
            Class49.method1129(var2, var3, var0, var1);
         }

      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "gi.B(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + (byte) -47 + ')');
      }
   }

   public static void method1059(byte var0) {//TODO: Empty check method
      try {
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "gi.E(" + var0 + ')');
      }
   }

   final void method1060(byte var1, int var2, int var3, int var4, int var5) {
      try {
         this.anInt694 = var2;
         this.anInt702 = var4;
         this.anInt693 = var5;
         this.anInt709 = var3;
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "gi.F(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
      }
   }

   private void method1061() {
      try {
         int var2 = (this.anInt698 << 7) - -64;
         this.aFloat710 = 1.0F / (float)(var2 * var2);
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "gi.C(" + 66 + ')');
      }
   }

   static Class3_Sub28_Sub16 method1062(int var0) {
      try {
         byte[] var2 = Class163_Sub1.aByteArrayArray2987[0];
         int var1 = Class140_Sub7.anIntArray2931[0] * Class3_Sub13_Sub6.anIntArray3076[0];
         int[] var3 = new int[var1];
         if(var0 < 70) {
            method1062(67);
         }

         for(int var4 = 0; var4 < var1; ++var4) {
            var3[var4] = Class3_Sub13_Sub38.spritePalette[Class69.bitwiseAnd(var2[var4], 255)];
         }

         Object var6;
         if(HDToolKit.highDetail) {
            var6 = new Class3_Sub28_Sub16_Sub1(Class3_Sub15.anInt2426, Class133.anInt1748, Class164.anIntArray2048[0], RSByteBuffer.anIntArray2591[0], Class140_Sub7.anIntArray2931[0], Class3_Sub13_Sub6.anIntArray3076[0], var3);
         } else {
            var6 = new Class3_Sub28_Sub16_Sub2(Class3_Sub15.anInt2426, Class133.anInt1748, Class164.anIntArray2048[0], RSByteBuffer.anIntArray2591[0], Class140_Sub7.anIntArray2931[0], Class3_Sub13_Sub6.anIntArray3076[0], var3);
         }

         Class39.method1035((byte)111);
         return (Class3_Sub28_Sub16)var6;
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "gi.D(" + var0 + ')');
      }
   }

   final void method1063(boolean var1, int var2) {
      try {

         int var5 = this.anInt714 + var2 * this.anInt709 / 50 & 2047;
         int var6 = this.anInt694;
         int var4;
         if(var6 == 1) {
            var4 = 1024 - -(Class51.anIntArray840[var5] >> 6);
         } else if(var6 == 3) {
            var4 = Class3_Sub13_Sub9.anIntArray3107[var5] >> 1;
         } else if (var6 == 4) {
            var4 = var5 >> 10 << 11;
         } else if (var6 == 2) {
            var4 = var5;
         } else if (var6 == 5) {
            var4 = (var5 < 1024 ? var5 : 2048 - var5) << 1;
         } else {
            var4 = 2048;
         }

         if(var1) {
            var4 = 2048;
         }

         this.aFloat707 = (float)(this.anInt693 + (var4 * this.anInt702 >> 11)) / 2048.0F;
         float var8 = this.aFloat707 / 255.0F;
         this.aFloatArray717[0] = (float)(Class69.bitwiseAnd(this.anInt713, 16771365) >> 16) * var8;
         this.aFloatArray717[2] = var8 * (float) Class69.bitwiseAnd(255, this.anInt713);
         this.aFloatArray717[1] = (float)(Class69.bitwiseAnd(this.anInt713, '\uffe7') >> 8) * var8;
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "gi.A(" + var1 + ',' + var2 + ',' + -3696 + ')');
      }
   }

   private void method1064() {
      try {
         int var2 = this.anInt705;
         if(var2 == 2) {
            this.anInt702 = 2048;
            this.anInt693 = 0;
            this.anInt694 = 1;
            this.anInt709 = 2048;
         } else if (var2 == 3) {
            this.anInt693 = 0;
            this.anInt709 = 4096;
            this.anInt694 = 1;
            this.anInt702 = 2048;
         } else if (var2 == 4) {
            this.anInt693 = 0;
            this.anInt702 = 2048;
            this.anInt694 = 4;
            this.anInt709 = 2048;
         } else if (var2 == 5) {
            this.anInt694 = 4;
            this.anInt702 = 2048;
            this.anInt709 = 8192;
            this.anInt693 = 0;
         } else if (var2 == 12) {
            this.anInt702 = 2048;
            this.anInt694 = 2;
            this.anInt709 = 2048;
            this.anInt693 = 0;
         } else if (var2 == 13) {
            this.anInt709 = 8192;
            this.anInt702 = 2048;
            this.anInt694 = 2;
            this.anInt693 = 0;
         } else if (var2 == 10) {
            this.anInt702 = 512;
            this.anInt694 = 3;
            this.anInt693 = 1536;
            this.anInt709 = 2048;
         } else if (var2 == 11) {
            this.anInt694 = 3;
            this.anInt709 = 4096;
            this.anInt702 = 512;
            this.anInt693 = 1536;
         } else if (var2 == 6) {
            this.anInt702 = 768;
            this.anInt693 = 1280;
            this.anInt694 = 3;
            this.anInt709 = 2048;
         } else if (var2 == 7) {
            this.anInt702 = 768;
            this.anInt693 = 1280;
            this.anInt709 = 4096;
            this.anInt694 = 3;
         } else if (var2 == 8) {
            this.anInt709 = 2048;
            this.anInt694 = 3;
            this.anInt702 = 1024;
            this.anInt693 = 1024;
         } else if (var2 == 9) {
            this.anInt709 = 4096;
            this.anInt693 = 1024;
            this.anInt702 = 1024;
            this.anInt694 = 3;
         } else if (var2 == 14) {
            this.anInt709 = 2048;
            this.anInt693 = 1280;
            this.anInt694 = 1;
            this.anInt702 = 768;
         } else if (var2 == 15) {
            this.anInt702 = 512;
            this.anInt709 = 4096;
            this.anInt693 = 1536;
            this.anInt694 = 1;
         } else if (var2 == 16) {
            this.anInt709 = 8192;
            this.anInt693 = 1792;
            this.anInt694 = 1;
            this.anInt702 = 256;
         } else {
            this.anInt709 = 2048;
            this.anInt693 = 0;
            this.anInt702 = 2048;
            this.anInt694 = 0;
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "gi.G(" + 3 + ')');
      }
   }

   static void method1065(CS2Script var1) {
      try {
         ItemDefinition.runAssembledScript(200000, var1);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "gi.H(" + 1073376993 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   protected Class43() {
      try {
         if(Class3_Sub13_Sub9.anIntArray3107 == null) {
            Class45.method1083((byte)-90);
         }

         this.method1064();
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "gi.<init>()");
      }
   }

   Class43(RSByteBuffer var1) {
      try {
         if(null == Class3_Sub13_Sub9.anIntArray3107) {
            Class45.method1083((byte)-94);
         }

         this.anInt704 = var1.getByteB();
         this.aBoolean690 = (this.anInt704 & 16) != 0;
         this.aBoolean711 = (this.anInt704 & 8) != 0;
         this.anInt704 &= 7;
         this.anInt703 = var1.getShort();
         this.anInt708 = var1.getShort();
         this.anInt697 = var1.getShort();
         this.anInt698 = var1.getByteB();
         this.method1061();
         this.aShortArray706 = new short[this.anInt698 * 2 + 1];

         int var2;
         for(var2 = 0; var2 < this.aShortArray706.length; ++var2) {
            this.aShortArray706[var2] = (short)var1.getShort();
         }

         this.anInt713 = Class51.anIntArray834[var1.getShort()];
         var2 = var1.getByteB();
         this.anInt714 = 1792 & var2 << 3;
         this.anInt705 = var2 & 31;
         if(31 != this.anInt705) {
            this.method1064();
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "gi.<init>(" + (var1 != null?"{...}":"null") + ')');
      }
   }

}
