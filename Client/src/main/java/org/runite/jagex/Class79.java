package org.runite.jagex;
import java.awt.event.KeyEvent;

final class Class79 {

   int anInt1123;
   static int anInt1124 = -1;
   int anInt1125;
   static int anInt1126;
   static int anInt1127 = 0;
   int anInt1128;


   static void method1385(int var0, int var1) {
      try {
         Class3_Sub28_Sub6 var3 = Class3_Sub24_Sub3.method466(6, var1);
         var3.g();
         var3.anInt3598 = var0;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "kk.E(" + var0 + ',' + var1 + ',' + (byte) -127 + ')');
      }
   }

   static int method1386(KeyEvent var1) {
      try {
         int var2 = var1.getKeyChar();
         if(8364 == var2) {
            return 128;
         } else {
            if(var2 <= 0 || 256 <= var2) {
               var2 = -1;
            }

            return var2;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "kk.C(" + true + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   final void method1387(RSByteBuffer var1) {
      try {
         while(true) {
            int var3 = var1.getByteB();
            if(var3 == 0) {
               return;
            }

            this.method1389(var1, var3);
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "kk.G(" + (var1 != null?"{...}":"null") + ',' + -111 + ')');
      }
   }

   public static void method1388(boolean var0) {
      try {
         if(!var0) {
            anInt1126 = 8;
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "kk.D(" + var0 + ')');
      }
   }

   private void method1389(RSByteBuffer var1, int var3) {
      try {
         if(1 == var3) {
            this.anInt1128 = var1.getShort();
            this.anInt1123 = var1.getByteB();
            this.anInt1125 = var1.getByteB();
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "kk.B(" + (var1 != null?"{...}":"null") + ',' + 1 + ',' + var3 + ')');
      }
   }

   static void method1390(RSByteBuffer var0) {
      try {
         if(-var0.index + var0.buffer.length >= 1) {
            int var2 = var0.getByteB();
            if(var2 >= 0 && var2 <= 11) {
               byte var3;
               if(var2 == 11) {
                  var3 = 33;
               } else if(var2 == 10) {
                  var3 = 32;
               } else if (var2 == 9) {
                  var3 = 31;
               } else if (var2 == 8) {
                  var3 = 30;
               } else if (var2 == 7) {
                  var3 = 29;
               } else if (var2 == 6) {
                  var3 = 28;
               } else if (var2 == 5) {
                  var3 = 28;
               } else if (var2 == 4) {
                  var3 = 24;
               } else if (var2 == 3) {
                  var3 = 23;
               } else if (var2 == 2) {
                  var3 = 22;
               } else if (1 == var2) {
                  var3 = 23;
               } else {
                  var3 = 19;
               }

               if(var3 <= var0.buffer.length - var0.index) {
                  Class3_Sub28_Sub10.anInt3625 = var0.getByteB();
                  if(Class3_Sub28_Sub10.anInt3625 >= 1) {
                     if(Class3_Sub28_Sub10.anInt3625 > 4) {
                        Class3_Sub28_Sub10.anInt3625 = 4;
                     }
                  } else {
                     Class3_Sub28_Sub10.anInt3625 = 1;
                  }

                  Class25.method957(1 == var0.getByteB());
                  Class3_Sub28_Sub7.aBoolean3604 = var0.getByteB() == 1;
                  KeyboardListener.aBoolean1905 = 1 == var0.getByteB();
                  Class25.aBoolean488 = 1 == var0.getByteB();
                  RSInterface.aBoolean236 = var0.getByteB() == 1;
                  WorldListEntry.aBoolean2623 = var0.getByteB() == 1;
                  Class3_Sub13_Sub22.aBoolean3275 = var0.getByteB() == 1;
                  Class140_Sub6.aBoolean2910 = 1 == var0.getByteB();
                  Class80.anInt1137 = var0.getByteB();
                  if(2 < Class80.anInt1137) {
                     Class80.anInt1137 = 2;
                  }

                  if(var2 < 2) {
                     Class106.aBoolean1441 = var0.getByteB() == 1;
                     var0.getByteB();
                  } else {
                     Class106.aBoolean1441 = var0.getByteB() == 1;
                  }

                  Class128.aBoolean1685 = 1 == var0.getByteB();
                  Class38.aBoolean661 = var0.getByteB() == 1;
                  Class3_Sub28_Sub9.anInt3622 = var0.getByteB();
                  if(Class3_Sub28_Sub9.anInt3622 > 2) {
                     Class3_Sub28_Sub9.anInt3622 = 2;
                  }

                  Class3_Sub28_Sub14.anInt3671 = Class3_Sub28_Sub9.anInt3622;
                  Class3_Sub13_Sub15.aBoolean3184 = var0.getByteB() == 1;
                  CS2Script.anInt2453 = var0.getByteB();
                  if(CS2Script.anInt2453 > 127) {
                     CS2Script.anInt2453 = 127;
                  }

                  Class9.anInt120 = var0.getByteB();
                  Class14.anInt340 = var0.getByteB();
                  if(Class14.anInt340 > 127) {
                     Class14.anInt340 = 127;
                  }

                  if(var2 >= 1) {
                     Class3_Sub13.anInt2378 = var0.getShort();
                     Class3_Sub13_Sub5.anInt3071 = var0.getShort();
                  }

                  if(var2 >= 3 && var2 < 6) {
                     var0.getByteB();
                  }

                  if(var2 >= 4) {
                     int var4 = var0.getByteB();
                     if(Class3_Sub24_Sub3.anInt3492 < 96) {
                        var4 = 0;
                     }

                     Class127_Sub1.method1758(var4);
                  }

                  if(var2 >= 5) {
                     RSString.anInt2148 = var0.getInt();
                  }

                  if(6 <= var2) {
                     Node.anInt2577 = var0.getByteB();
                  }

                  if(var2 >= 7) {
                     RSString.aBoolean2146 = 1 == var0.getByteB();
                  }

                  if(8 <= var2) {
                     Class15.aBoolean346 = var0.getByteB() == 1;
                  }

                  if(9 <= var2) {
                     Class3_Sub20.anInt2488 = var0.getByteB();
                  }

                  if(10 <= var2) {
                     Class73.aBoolean1080 = 0 != var0.getByteB();
                  }

                  if(var2 >= 11) {
                     Class163_Sub3.aBoolean3004 = var0.getByteB() != 0;
                  }

               }
            }
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "kk.F(" + (var0 != null?"{...}":"null") + ',' + -1 + ')');
      }
   }

   static boolean method1391(int var0) {
      try {
         return var0 == ~Class10.anInt154 && !Class101.aClass3_Sub24_Sub4_1421.method473(-128);
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "kk.A(" + var0 + ')');
      }
   }

}
