package org.runite.jagex;

final class NodeList {

   static CacheIndex aClass153_332;
   private final Node aClass3_Sub28_333 = new Node();
   private Node aClass3_Sub28_335;


   final int method874(int var1) {
      try {
         int var2 = 0;

         for(Node var3 = this.aClass3_Sub28_333.aClass3_Sub28_2578; var3 != this.aClass3_Sub28_333; ++var2) {
            var3 = var3.aClass3_Sub28_2578;
         }

         return var1 > -41?-56:var2;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "ce.H(" + var1 + ')');
      }
   }

   public static void method875(byte var0) {
      try {
         aClass153_332 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ce.C(" + var0 + ')');
      }
   }

   final Node method876(byte var1) {
      try {
         Node var2 = this.aClass3_Sub28_333.aClass3_Sub28_2578;
         if(this.aClass3_Sub28_333 == var2) {
            this.aClass3_Sub28_335 = null;
            return null;
         } else {
            this.aClass3_Sub28_335 = var2.aClass3_Sub28_2578;
            if(var1 < 14) {
               this.aClass3_Sub28_335 = (Node)null;
            }

            return var2;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ce.K(" + var1 + ')');
      }
   }

   final Node method877() {
      try {
         Node var2 = this.aClass3_Sub28_333.aClass3_Sub28_2578;
         if(var2 == this.aClass3_Sub28_333) {
            return null;
         } else {
            var2.method524();
            return var2;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ce.B(" + -1 + ')');
      }
   }

   final Node method878(int var1) {
      try {
         Node var2 = this.aClass3_Sub28_335;
         if(var2 == this.aClass3_Sub28_333) {
            this.aClass3_Sub28_335 = null;
            return null;
         } else {
            this.aClass3_Sub28_335 = var2.aClass3_Sub28_2578;
            return var2;
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "ce.A(" + var1 + ')');
      }
   }

   final void method879(Node var1, byte var2) {
      try {
         if(var1.aClass3_Sub28_2570 != null) {
            var1.method524();
         }

         var1.aClass3_Sub28_2570 = this.aClass3_Sub28_333.aClass3_Sub28_2570;
         var1.aClass3_Sub28_2578 = this.aClass3_Sub28_333;
         var1.aClass3_Sub28_2570.aClass3_Sub28_2578 = var1;
         var1.aClass3_Sub28_2578.aClass3_Sub28_2570 = var1;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "ce.E(" + (var1 != null?"{...}":"null") + ',' + var2 + ')');
      }
   }

   static Class3_Sub28_Sub17 method880(byte[] var1) {
      try {
         if(null == var1) {
            return null;
         } else {
            Object var2;
            if (HDToolKit.highDetail) {
               var2 = new Class3_Sub28_Sub17_Sub2(var1, Class164.anIntArray2048, RSByteBuffer.anIntArray2591, Class140_Sub7.anIntArray2931, Class3_Sub13_Sub6.anIntArray3076, Class163_Sub1.aByteArrayArray2987);
            } else {
               var2 = new Class3_Sub28_Sub17_Sub1(var1, Class164.anIntArray2048, RSByteBuffer.anIntArray2591, Class140_Sub7.anIntArray2931, Class3_Sub13_Sub6.anIntArray3076, Class163_Sub1.aByteArrayArray2987);
            }

            Class39.method1035((byte) 106);
            return (Class3_Sub28_Sub17) var2;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ce.G(" + -22376 + ',' + "{...}" + ')');
      }
   }

   static void method881(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
      try {
         if(var2 <= -65) {
            Class3_Sub4 var10 = null;

            for(Class3_Sub4 var11 = (Class3_Sub4)Class3_Sub13_Sub6.aClass61_3075.method1222(); var11 != null; var11 = (Class3_Sub4)Class3_Sub13_Sub6.aClass61_3075.method1221()) {
               if(var11.anInt2250 == var0 && var11.anInt2264 == var4 && var11.anInt2248 == var1 && var11.anInt2263 == var7) {
                  var10 = var11;
                  break;
               }
            }

            if(null == var10) {
               var10 = new Class3_Sub4();
               var10.anInt2264 = var4;
               var10.anInt2248 = var1;
               var10.anInt2250 = var0;
               var10.anInt2263 = var7;
               Class132.method1798(72, var10);
               Class3_Sub13_Sub6.aClass61_3075.method1215(var10);
            }

            var10.anInt2262 = var8;
            var10.anInt2261 = var9;
            var10.anInt2259 = var5;
            var10.anInt2265 = var6;
            var10.anInt2256 = var3;
         }
      } catch (RuntimeException var12) {
         throw Class44.clientError(var12, "ce.J(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + var9 + ')');
      }
   }

   static void method882() {
      try {
         Class3_Sub13_Sub36.anInt3423 = 0;
         Class132.anInt1741 = -1;
         Class10.anInt154 = 1;
         GraphicDefinition.anInt546 = 2;
         Class3_Sub9.aBoolean2311 = false;
         Class101.aClass153_1423 = null;
         Class3_Sub13_Sub39.anInt3463 = -1;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ce.F(" + -1 + ',' + 2 + ')');
      }
   }

   final void method883() {
      try {

         while(true) {
            Node var2 = this.aClass3_Sub28_333.aClass3_Sub28_2578;
            if(this.aClass3_Sub28_333 == var2) {
               this.aClass3_Sub28_335 = null;
               return;
            }

            var2.method524();
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ce.I(" + 17126 + ')');
      }
   }

   static Class3_Sub28_Sub3 method884(int var0, byte var1, int var2) {
      try {
         Class3_Sub28_Sub3 var3 = (Class3_Sub28_Sub3)Class134.aClass61_1758.method1222();

         for(; var3 != null; var3 = (Class3_Sub28_Sub3)Class134.aClass61_1758.method1221()) {
            if(var3.aBoolean3553 && var3.method537(var2, var0)) {
               return var3;
            }
         }

         return null;
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "ce.D(" + var0 + ',' + var1 + ',' + var2 + ')');
      }
   }

   public NodeList() {
      try {
         this.aClass3_Sub28_333.aClass3_Sub28_2578 = this.aClass3_Sub28_333;
         this.aClass3_Sub28_333.aClass3_Sub28_2570 = this.aClass3_Sub28_333;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ce.<init>()");
      }
   }

}
