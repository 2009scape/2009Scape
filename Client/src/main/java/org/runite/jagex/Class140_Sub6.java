package org.runite.jagex;

final class Class140_Sub6 extends GameObject {

   static int accRegistryPort;
   private double aDouble2895;
   private int anInt2896 = 0;
   private final int anInt2897;
   private int anInt2898 = -1;
   int anInt2899;
   double aDouble2900;
   private final int anInt2902;
   int anInt2903;
   private boolean aBoolean2904;
   static int anInt2905 = 0;
   static CacheIndex spritesCacheIndex;
   int anInt2907;
   private double aDouble2908;
   private Class127_Sub1 aClass127_Sub1_2909;
   static boolean aBoolean2910 = true;
   private final int anInt2911;
   private int anInt2912 = -32768;
   private double aDouble2913;
   double aDouble2914;
   private final int anInt2915;
   private final int anInt2916;
   private int anInt2917;
   private double aDouble2918;
   int anInt2919;
   double aDouble2920;
   private double aDouble2921;
   private int anInt2922 = 0;
   private final AnimationDefinition aClass142_2923;
   int anInt2924;
   int anInt2925;
   static volatile long aLong2926 = 0L;
   private final int anInt2927;


   public static void method2019(boolean var0) {
      try {
         if(var0) {
            spritesCacheIndex = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ra.C(" + var0 + ')');
      }
   }

   static void method2020(int var0, ObjectDefinition var1, int var3, int var4) {
      try {
         Class3_Sub9 var5 = (Class3_Sub9)Class3.aClass61_78.method1222();

          while(null != var5) {
            if(var4 == var5.anInt2314 && 128 * var0 == var5.anInt2326 && var5.anInt2308 == 128 * var3 && var5.aClass111_2320.objectId == var1.objectId) {
               if(null != var5.aClass3_Sub24_Sub1_2312) {
                  Class3_Sub26.aClass3_Sub24_Sub2_2563.method461(var5.aClass3_Sub24_Sub1_2312);
                  var5.aClass3_Sub24_Sub1_2312 = null;
               }

               if(var5.aClass3_Sub24_Sub1_2315 != null) {
                  Class3_Sub26.aClass3_Sub24_Sub2_2563.method461(var5.aClass3_Sub24_Sub1_2315);
                  var5.aClass3_Sub24_Sub1_2315 = null;
               }

               var5.method86(-1024);
               return;
            }

            var5 = (Class3_Sub9)Class3.aClass61_78.method1221();
         }

      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "ra.E(" + var0 + ',' + (var1 != null?"{...}":"null") + ',' + (byte) -73 + ',' + var3 + ',' + var4 + ')');
      }
   }

   static boolean method2021(byte var0, int var1) {
      try {
         if(var0 > -63) {
            anInt2905 = 66;
         }

         return var1 == (-var1 & var1);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ra.H(" + var0 + ',' + var1 + ')');
      }
   }

   final void method1867(int var1, int var2, int var3, int var4, int var5) {}

   private Model method2022() {
      try {
          GraphicDefinition var2 = RenderAnimationDefinition.getGraphicDefinition((byte)42, this.anInt2915);
          Model var3 = var2.method966(this.anInt2898, this.anInt2922, this.anInt2896);
          if(null == var3) {
             return null;
          } else {
             var3.method1896(this.anInt2917);
             return var3;
          }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "ra.G(" + -126 + ')');
      }
   }

   final void method2023(int var2) {
      try {
         this.aDouble2920 += this.aDouble2895 * (double)var2;
         this.aDouble2900 += this.aDouble2918 * (double)var2;
         this.aBoolean2904 = true;
         if(this.anInt2927 == -1) {
            this.aDouble2914 += this.aDouble2913 * (double)var2;
         } else {
            this.aDouble2914 += (double)var2 * this.aDouble2908 * 0.5D * (double)var2 + (double)var2 * this.aDouble2913;
            this.aDouble2913 += this.aDouble2908 * (double)var2;
         }

         this.anInt2924 = 1024 + (int)(325.949D * Math.atan2(this.aDouble2895, this.aDouble2918)) & 2047;
         this.anInt2917 = 2047 & (int)(325.949D * Math.atan2(this.aDouble2913, this.aDouble2921));

          if(this.aClass142_2923 != null) {
            this.anInt2896 += var2;

            while(this.anInt2896 > this.aClass142_2923.duration[this.anInt2922]) {
               this.anInt2896 -= this.aClass142_2923.duration[this.anInt2922];
               ++this.anInt2922;
               if(this.aClass142_2923.frames.length <= this.anInt2922) {
                  this.anInt2922 -= this.aClass142_2923.anInt1865;
                  if(this.anInt2922 < 0 || this.aClass142_2923.frames.length <= this.anInt2922) {
                     this.anInt2922 = 0;
                  }
               }

               this.anInt2898 = this.anInt2922 + 1;
               if(this.aClass142_2923.frames.length <= this.anInt2898) {
                  this.anInt2898 -= this.aClass142_2923.anInt1865;
                  if(this.anInt2898 < 0 || this.aClass142_2923.frames.length <= this.anInt2898) {
                     this.anInt2898 = -1;
                  }
               }
            }
         }

      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "ra.D(" + (byte) -59 + ',' + var2 + ')');
      }
   }

   final void method2024(int var1, int var3, int var4, int var5) {
      try {
         double var6;
         if(!this.aBoolean2904) {
            double var8 = (double)(-this.anInt2916 + var1);
            var6 = (double)(-this.anInt2902 + var5);
            double var10 = Math.sqrt(var6 * var6 + var8 * var8);
            this.aDouble2914 = (double)this.anInt2911;
            this.aDouble2900 = var8 * (double)this.anInt2897 / var10 + (double)this.anInt2916;
            this.aDouble2920 = (double)this.anInt2897 * var6 / var10 + (double)this.anInt2902;
         }

         var6 = (double)(-var3 + 1 + this.anInt2899);
         this.aDouble2918 = ((double)var1 - this.aDouble2900) / var6;
         this.aDouble2895 = (-this.aDouble2920 + (double)var5) / var6;
         this.aDouble2921 = Math.sqrt(this.aDouble2918 * this.aDouble2918 + this.aDouble2895 * this.aDouble2895);
         if(this.anInt2927 == -1) {
            this.aDouble2913 = (-this.aDouble2914 + (double)var4) / var6;
         } else {
            if(!this.aBoolean2904) {
               this.aDouble2913 = -this.aDouble2921 * Math.tan((double)this.anInt2927 * 0.02454369D);
            }

            this.aDouble2908 = 2.0D * ((double)var4 - this.aDouble2914 - this.aDouble2913 * var6) / (var6 * var6);
         }

      } catch (RuntimeException var12) {
         throw Class44.clientError(var12, "ra.J(" + var1 + ',' + 1 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
      }
   }

   final void animate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, long var9, int var11, Class127_Sub1 var12) {
      try {
         Model var13 = this.method2022();
         if(null != var13) {
            var13.animate(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11, this.aClass127_Sub1_2909);
            this.anInt2912 = var13.method1871();
         }
      } catch (RuntimeException var14) {
         throw Class44.clientError(var14, "ra.IA(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + var9 + ',' + var11 + ',' + (var12 != null?"{...}":"null") + ')');
      }
   }

   static void method2025() {
      try {
          Class3_Sub28_Sub7_Sub1.aClass93_4043.method1522(-128, 5);
          CS2Script.aClass93_2442.method1522(-126, 5);
          Class154.aClass93_1964.method1522(-127, 5);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ra.F(" + (byte) -62 + ',' + 5 + ')');
      }
   }

   final int method1871() {
      try {
         return this.anInt2912;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ra.MA()");
      }
   }

   static void method2026(int interfaceHash, int amount, int itemId) {
      try {
          Class3_Sub28_Sub6 var4 = Class3_Sub24_Sub3.method466(9, interfaceHash);
          var4.g();
          var4.anInt3598 = itemId;
          var4.anInt3597 = amount;
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "ra.I(" + (byte) 122 + ',' + interfaceHash + ',' + amount + ',' + itemId + ')');
      }
   }

   static Class3_Sub28_Sub16[] getSprites(int archiveId, CacheIndex var3) {
      try {
    	 // System.out.println(archiveId);
         return !Class75_Sub4.method1351(var3, 0, archiveId, -30901)?null: Class75_Sub3.method1347(-26802);
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "ra.B(" + 0 + ',' + (byte) 11 + ',' + archiveId + ',' + (var3 != null?"{...}":"null") + ')');
      }
   }

   static int method2028(int var0, int var1) {
      try {
         int var4;
         for(var4 = 1; 1 < var0; var0 >>= 1) {
            if(0 != (1 & var0)) {
               var4 *= var1;
            }

            var1 *= var1;
         }

         if(var0 == 1) {
            return var4 * var1;
         } else {
            return var4;
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "ra.A(" + var0 + ',' + var1 + ',' + -122 + ')');
      }
   }

   Class140_Sub6(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11) {
      try {
         this.anInt2899 = var7;
         this.anInt2902 = var3;
         this.anInt2919 = var10;
         this.anInt2915 = var1;
         this.anInt2907 = var2;
         this.anInt2927 = var8;
         this.anInt2916 = var4;
         this.aBoolean2904 = false;
         this.anInt2903 = var11;
         this.anInt2897 = var9;
         this.anInt2925 = var6;
         this.anInt2911 = var5;
         int var12 = RenderAnimationDefinition.getGraphicDefinition((byte)42, this.anInt2915).anInt542;
         if(var12 == -1) {
            this.aClass142_2923 = null;
         } else {
            this.aClass142_2923 = Client.getAnimationDefinition(var12);
         }

      } catch (RuntimeException var13) {
         throw Class44.clientError(var13, "ra.<init>(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + var9 + ',' + var10 + ',' + var11 + ')');
      }
   }

}
