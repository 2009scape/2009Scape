package org.runite.client;
import java.awt.event.KeyEvent;

final class Class79 {

   int anInt1123;
   static int anInt1124 = -1;
   int anInt1125;
   static int anInt1127 = 0;
   int anInt1128;


   static void method1385(int var0, int var1) {
      try {
         InterfaceWidget var3 = Class3_Sub24_Sub3.getWidget(6, var1);
         var3.flagUpdate();
         var3.anInt3598 = var0;
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "kk.E(" + var0 + ',' + var1 + ',' + (byte) -127 + ')');
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
         throw ClientErrorException.clientError(var3, "kk.C(" + true + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   final void method1387(DataBuffer var1) {
      try {
         while(true) {
            int var3 = var1.readUnsignedByte();
            if(var3 == 0) {
               return;
            }

            this.method1389(var1, var3);
         }
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "kk.G(" + (var1 != null?"{...}":"null") + ',' + -111 + ')');
      }
   }

   private void method1389(DataBuffer var1, int var3) {
      try {
         if(1 == var3) {
            this.anInt1128 = var1.readUnsignedShort();
            this.anInt1123 = var1.readUnsignedByte();
            this.anInt1125 = var1.readUnsignedByte();
         }

      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "kk.B(" + (var1 != null?"{...}":"null") + ',' + 1 + ',' + var3 + ')');
      }
   }

   static void method1390(DataBuffer var0) {
      try {
         if(-var0.index + var0.buffer.length >= 1) {
            int var2 = var0.readUnsignedByte();
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
                  Unsorted.anInt3625 = var0.readUnsignedByte();
                  if(Unsorted.anInt3625 >= 1) {
                     if(Unsorted.anInt3625 > 4) {
                        Unsorted.anInt3625 = 4;
                     }
                  } else {
                     Unsorted.anInt3625 = 1;
                  }

                  Class25.method957(1 == var0.readUnsignedByte());
                  Unsorted.aBoolean3604 = var0.readUnsignedByte() == 1;
                  KeyboardListener.aBoolean1905 = 1 == var0.readUnsignedByte();
                  Class25.aBoolean488 = 1 == var0.readUnsignedByte();
                  RSInterface.aBoolean236 = var0.readUnsignedByte() == 1;
                  WorldListEntry.aBoolean2623 = var0.readUnsignedByte() == 1;
                  Class3_Sub13_Sub22.aBoolean3275 = var0.readUnsignedByte() == 1;
                  Class140_Sub6.aBoolean2910 = 1 == var0.readUnsignedByte();
                  Unsorted.anInt1137 = var0.readUnsignedByte();
                  if(2 < Unsorted.anInt1137) {
                     Unsorted.anInt1137 = 2;
                  }

                  if(var2 < 2) {
                     Class106.aBoolean1441 = var0.readUnsignedByte() == 1;
                     var0.readUnsignedByte();
                  } else {
                     Class106.aBoolean1441 = var0.readUnsignedByte() == 1;
                  }

                  Class128.aBoolean1685 = 1 == var0.readUnsignedByte();
                  Class38.aBoolean661 = var0.readUnsignedByte() == 1;
                  Class3_Sub28_Sub9.anInt3622 = var0.readUnsignedByte();
                  if(Class3_Sub28_Sub9.anInt3622 > 2) {
                     Class3_Sub28_Sub9.anInt3622 = 2;
                  }

                  Unsorted.anInt3671 = Class3_Sub28_Sub9.anInt3622;
                  Class3_Sub13_Sub15.aBoolean3184 = var0.readUnsignedByte() == 1;
                  CS2Script.anInt2453 = var0.readUnsignedByte();
                  if(CS2Script.anInt2453 > 127) {
                     CS2Script.anInt2453 = 127;
                  }

                  Unsorted.anInt120 = var0.readUnsignedByte();
                  Sprites.anInt340 = var0.readUnsignedByte();
                  if(Sprites.anInt340 > 127) {
                     Sprites.anInt340 = 127;
                  }

                  if(var2 >= 1) {
                     Class3_Sub13.anInt2378 = var0.readUnsignedShort();
                     Unsorted.anInt3071 = var0.readUnsignedShort();
                  }

                  if(var2 >= 3 && var2 < 6) {
                     var0.readUnsignedByte();
                  }

                  if(var2 >= 4) {
                     int var4 = var0.readUnsignedByte();
                     if(Class3_Sub24_Sub3.anInt3492 < 96) {
                        var4 = 0;
                     }

                     Class127_Sub1.method1758(var4);
                  }

                  if(var2 >= 5) {
                     Unsorted.anInt2148 = var0.readInt();
                  }

                  if(6 <= var2) {
                     Unsorted.anInt2577 = var0.readUnsignedByte();
                  }

                  if(var2 >= 7) {
                     Unsorted.aBoolean2146 = 1 == var0.readUnsignedByte();
                  }

                  if(8 <= var2) {
                     Class15.aBoolean346 = var0.readUnsignedByte() == 1;
                  }

                  if(9 <= var2) {
                     Class3_Sub20.anInt2488 = var0.readUnsignedByte();
                  }

                  if(10 <= var2) {
                     Unsorted.aBoolean1080 = 0 != var0.readUnsignedByte();
                  }

                  if(var2 >= 11) {
                     Class163_Sub3.aBoolean3004 = var0.readUnsignedByte() != 0;
                  }

               }
            }
         }
      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "kk.F(" + (var0 != null?"{...}":"null") + ',' + -1 + ')');
      }
   }

   static boolean method1391(int var0) {
      try {
         return var0 == ~Class10.anInt154 && !Class101.aClass3_Sub24_Sub4_1421.method473(-128);
      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "kk.A(" + var0 + ')');
      }
   }

}
