package org.runite.jagex;

final class Class133 {

   private byte aByte1742;
   static int[] anIntArray1743 = new int[25];
   static int anInt1744 = 0;
   static RSString aClass94_1745 = RSString.createRSString("settings");
   int anInt1746;
   int anInt1747;
   static int anInt1748;
   static RSInterface aClass11_1749;
   int anInt1750;
   static CacheIndex aClass153_1751;
   int anInt1752;
   static int anInt1753;
   static int anInt1754;
   static int[] inputTextCodeArray = new int[128];
   int anInt1757;


   public static void method1802(int var0) {
      try {
         aClass94_1745 = null;
         anIntArray1743 = null;
         inputTextCodeArray = null;
         aClass153_1751 = null;
         aClass11_1749 = null;
         if(var0 != 25) {
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "sg.A(" + var0 + ')');
      }
   }

   static void method1803() {
      try {
         Class82.aClass93_1146.method1523((byte)-119);
         Class159.aClass93_2016.method1523((byte)-103);
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "sg.D(" + (byte) 22 + ')');
      }
   }

   final int method1804() {
      try {

         return this.aByte1742 & 7;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "sg.B(" + false + ')');
      }
   }

   final int method1805() {
      try {
         return 8 != (this.aByte1742 & 8)?0:1;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "sg.C(" + (byte) -33 + ')');
      }
   }

   public Class133() {}

   Class133(RSByteBuffer var1) {
      try {
         this.aByte1742 = var1.getByte();
         this.anInt1752 = var1.getShort();
         this.anInt1757 = var1.getInt();
         this.anInt1747 = var1.getInt();
         this.anInt1746 = var1.getInt();
         this.anInt1750 = var1.getInt();
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "sg.<init>(" + (var1 != null?"{...}":"null") + ')');
      }
   }

}
