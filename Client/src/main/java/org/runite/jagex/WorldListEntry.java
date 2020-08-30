package org.runite.jagex;

import java.util.Objects;

final class WorldListEntry extends Class44 {

   RSString activity;
   int worldId;
   static boolean aBoolean2623 = true;
   RSString address;
   static int anInt2626 = 20;
   static Class155 aClass155_2627;


   static void method1076() {
      try {
         Class154.aClass93_1964.method1524();
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ba.C(" + 88 + ')');
      }
   }

   public static void method1077(int var0) {
      try {
         aClass155_2627 = null;
         if(var0 != 0) {
            anInt2626 = 89;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ba.A(" + var0 + ')');
      }
   }

   final WorldListCountry method1078(int var1) {
      try {
         return Class119.countries[this.countryIndex];
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "ba.B(" + var1 + ')');
      }
   }

   static int method1079(int var0) {
      try {
         if(0 > var0) {
            return 0;
         } else {
            Class3_Sub25 var2 = (Class3_Sub25)Class3_Sub2.aClass130_2220.method1780((long)var0, 0);
            if(var2 == null) {
               return Objects.requireNonNull(Class144.method2069(var0, -126)).anInt3647;
            } else {
               int var3 = 0;

               for(int var4 = 0; var4 < var2.anIntArray2547.length; ++var4) {
                  if(var2.anIntArray2547[var4] == -1) {
                     ++var3;
                  }
               }

               var3 += Objects.requireNonNull(Class144.method2069(var0, -100)).anInt3647 + -var2.anIntArray2547.length;
               return var3;
            }
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "ba.D(" + var0 + ',' + (byte) -80 + ')');
      }
   }

}
