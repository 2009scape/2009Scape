package org.runite.jagex;
import java.awt.Canvas;

public final class Class3_Sub28_Sub12 extends Node {

   int anInt3647 = 0;
   public static Canvas aCanvas3648;
   static RSString aClass94_3651 = RSString.createRSString("::mm");
   static int anInt3652;
   static RSString aClass94_3653 = RSString.createRSString("Shift)2click disabled)3");
   static short[][] aShortArrayArray3654 = new short[][]{{(short)6798, (short)107, (short)10283, (short)16, (short)4797, (short)7744, (short)5799, (short)4634, (short)-31839, (short)22433, (short)2983, (short)-11343, (short)8, (short)5281, (short)10438, (short)3650, (short)-27322, (short)-21845, (short)200, (short)571, (short)908, (short)21830, (short)28946, (short)-15701, (short)-14010}, {(short)8741, (short)12, (short)-1506, (short)-22374, (short)7735, (short)8404, (short)1701, (short)-27106, (short)24094, (short)10153, (short)-8915, (short)4783, (short)1341, (short)16578, (short)-30533, (short)25239, (short)8, (short)5281, (short)10438, (short)3650, (short)-27322, (short)-21845, (short)200, (short)571, (short)908, (short)21830, (short)28946, (short)-15701, (short)-14010}, {(short)25238, (short)8742, (short)12, (short)-1506, (short)-22374, (short)7735, (short)8404, (short)1701, (short)-27106, (short)24094, (short)10153, (short)-8915, (short)4783, (short)1341, (short)16578, (short)-30533, (short)8, (short)5281, (short)10438, (short)3650, (short)-27322, (short)-21845, (short)200, (short)571, (short)908, (short)21830, (short)28946, (short)-15701, (short)-14010}, {(short)4626, (short)11146, (short)6439, (short)12, (short)4758, (short)10270}, {(short)4550, (short)4537, (short)5681, (short)5673, (short)5790, (short)6806, (short)8076, (short)4574}};
   static int anInt3655 = -1;


   static boolean method609(RSInterface var0, int var1) {
      try {
         if(null == var0.anIntArray275) {
            return false;
         } else {
            int var2 = 0;
            if(var1 <= 20) {
               aClass94_3651 = (RSString)null;
            }

            for(; var0.anIntArray275.length > var2; ++var2) {
               int var3 = Class164_Sub2.method2247((byte)119, var2, var0);
               int var4 = var0.anIntArray307[var2];
               if(var0.anIntArray275[var2] != 2) {
                  if(var0.anIntArray275[var2] != 3) {
                     if(4 == var0.anIntArray275[var2]) {
                        if(var4 == var3) {
                           return false;
                        }
                     } else if(var3 != var4) {
                        return false;
                     }
                  } else if(var3 <= var4) {
                     return false;
                  }
               } else if(var3 >= var4) {
                  return false;
               }
            }

            return true;
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "md.C(" + (var0 != null?"{...}":"null") + ',' + var1 + ')');
      }
   }

   final void method610(RSByteBuffer var1) {
      try {
         while(true) {
            int var3 = var1.getByteB();
            if(0 == var3) {
               return;
            }

            this.method614(var1, var3);
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "md.E(" + (var1 != null?"{...}":"null") + ',' + 0 + ')');
      }
   }

   static void sendGameMessage(int var0, int type, RSString message, RSString var3, RSString var5) {
      try {
         for(int i = 99; i > 0; --i) {
            Class3_Sub13_Sub6.anIntArray3082[i] = Class3_Sub13_Sub6.anIntArray3082[i - 1];
            Class3_Sub13_Sub19.aClass94Array3226[i] = Class3_Sub13_Sub19.aClass94Array3226[i - 1];
            Class3_Sub29.aClass94Array2580[i] = Class3_Sub29.aClass94Array2580[-1 + i];
            Class163_Sub3.aClass94Array3003[i] = Class163_Sub3.aClass94Array3003[i + -1];
            GameObject.anIntArray1835[i] = GameObject.anIntArray1835[i - 1];
         }

         ++Class3_Sub13_Sub9.anInt3114;
         Class3_Sub13_Sub6.anIntArray3082[0] = type;
         Class3_Sub13_Sub19.aClass94Array3226[0] = var5;
         Class24.anInt472 = Class3_Sub13_Sub17.anInt3213;
         GameObject.anIntArray1835[0] = var0;
         Class3_Sub29.aClass94Array2580[0] = message;
         Class163_Sub3.aClass94Array3003[0] = var3;
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "md.D(" + var0 + ',' + type + ',' + (message != null?"{...}":"null") + ',' + (var3 != null?"{...}":"null") + ',' + (byte) 50 + ',' + (var5 != null?"{...}":"null") + ')');
      }
   }

   static RSString method612(long var0, byte var2) {
      try {
         return var2 <= 85?(RSString)null:Class3_Sub13_Sub8.method207(10, false, 116, var0);
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "md.F(" + var0 + ',' + var2 + ')');
      }
   }

   public static void method613(int var0) {
      try {
         aCanvas3648 = null;
         aClass94_3653 = null;
         aShortArrayArray3654 = (short[][])null;
         if(var0 > 22) {
            aClass94_3651 = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "md.B(" + var0 + ')');
      }
   }

   private void method614(RSByteBuffer var1, int var2) {
      try {

          if(var2 == 2) {
            this.anInt3647 = var1.getShort();
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "md.A(" + (var1 != null?"{...}":"null") + ',' + var2 + ',' + false + ')');
      }
   }

}
