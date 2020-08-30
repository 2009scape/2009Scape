package org.runite.jagex;

final class Class86 {

    static Class130 aClass130_3679 = new Class130(16);
    int anInt1175;
   int anInt1177;
   int anInt1178;
   int anInt1181;
   static Class91[] aClass91Array1182 = new Class91[4];
   int anInt1184;
   int anInt1185;
   static Class41 aClass41_1186;
   float aFloat1187;
   static RSString COMMAND_REBUILD = RSString.createRSString("::rebuild");
   float aFloat1189;
   float aFloat1190;
   static int anInt1191;
   static Class3_Sub24_Sub4 aClass3_Sub24_Sub4_1193;
   static Class130 aClass130_1194;
   static int anInt1195;

   static void method1427(int var1) {
      try {
         if(-1 == var1 && !Class83.aBoolean1158) {
            GameObject.method1870();
         } else if(var1 != -1 && (Class129.anInt1691 != var1 || Class79.method1391(-1)) && Class9.anInt120 != 0 && !Class83.aBoolean1158) {
            Class151.method2099(var1, Class75_Sub2.aClass153_2645, Class9.anInt120);
         }
         Class129.anInt1691 = var1;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "li.B(" + true + ',' + var1 + ')');
      }
   }

   static void method1428(int var0, int var2) {
      try {
         ItemDefinition.ram[var0] = var2;
         Class3_Sub7 var3 = (Class3_Sub7) aClass130_3679.method1780((long)var0, 0);
         if(var3 == null) {
            var3 = new Class3_Sub7(Class5.method830((byte)-55) - -500L);
            aClass130_3679.method1779(var3, (long)var0);
         } else {
            var3.aLong2295 = 500L + Class5.method830((byte)-55);
         }

      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "li.D(" + var0 + ',' + var2 + ')');
      }
   }

   public static void method1429(byte var0) {
      try {
         aClass3_Sub24_Sub4_1193 = null;
         COMMAND_REBUILD = null;
         aClass91Array1182 = null;
         aClass41_1186 = null;
         aClass130_1194 = null;
         if(var0 != 53) {
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "li.C(" + var0 + ')');
      }
   }

   static Class3_Sub28_Sub17_Sub1 method1430(int var0, int var1) {
      try {
         if(var0 == -28922) {
            Class3_Sub28_Sub17_Sub1 var2 = (Class3_Sub28_Sub17_Sub1)Class80.aClass93_1135.get((long)var1);
            if(var2 == null) {
               byte[] var3 = CacheIndex.aClass153_1948.getFile(var1, 0);
               var2 = new Class3_Sub28_Sub17_Sub1(var3);
               var2.method697(Class3_Sub13_Sub22.aClass109Array3270, (int[])null);
               Class80.aClass93_1135.put((byte)-96, var2, (long)var1);
            }
            return var2;
         } else {
            return (Class3_Sub28_Sub17_Sub1)null;
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "li.A(" + var0 + ',' + var1 + ')');
      }
   }

   public Class86() {
      try {
         this.anInt1177 = Class92.defaultScreenColorRgb;
         this.aFloat1189 = 1.2F;
         this.anInt1178 = -50;
         this.aFloat1187 = 1.1523438F;
         this.anInt1175 = Class92.defaulFogColorRgb;
         this.anInt1181 = -60;
         this.aFloat1190 = 0.69921875F;
         this.anInt1184 = 0;
         this.anInt1185 = -50;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "li.<init>()");
      }
   }

   Class86(RSByteBuffer var1) {
      try {
         int var2 = var1.getByteB();
         if((var2 & 1) == 0) {
            this.anInt1177 = Class92.defaultScreenColorRgb;
         } else {
            this.anInt1177 = var1.getInt();
         }

         if((2 & var2) == 0) {
            this.aFloat1187 = 1.1523438F;
         } else {
            this.aFloat1187 = (float)var1.getShort() / 256.0F;
         }

         if((var2 & 4) == 0) {
            this.aFloat1190 = 0.69921875F;
         } else {
            this.aFloat1190 = (float)var1.getShort() / 256.0F;
         }

         if((var2 & 8) == 0) {
            this.aFloat1189 = 1.2F;
         } else {
            this.aFloat1189 = (float)var1.getShort() / 256.0F;
         }

         if((16 & var2) == 0) {
            this.anInt1178 = -50;
            this.anInt1185 = -50;
            this.anInt1181 = -60;
         } else {
            this.anInt1185 = var1.getShort((byte)53);
            this.anInt1181 = var1.getShort((byte)15);
            this.anInt1178 = var1.getShort((byte)50);
         }

         if((32 & var2) == 0) {
            this.anInt1175 = Class92.defaulFogColorRgb;
         } else {
            this.anInt1175 = var1.getInt();
         }

         if((64 & var2) == 0) {
            this.anInt1184 = 0;
         } else {
            this.anInt1184 = var1.getShort();
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "li.<init>(" + (var1 != null?"{...}":"null") + ')');
      }
   }

}
