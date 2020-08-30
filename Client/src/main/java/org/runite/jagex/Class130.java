package org.runite.jagex;
import java.awt.Component;
import java.util.Objects;

final class Class130 {

   Class3[] aClass3Array1697;
   static RSString aClass94_1698 = RSString.createRSString("(R");
   int anInt1700;
   static boolean[] aBooleanArray1703;
   static int incomingPacketLength = 0;
   static int anInt1705 = 0;
   private Class3 aClass3_1706;
   
   private long aLong1708;
   static int anInt1709 = 0;
   static int anInt1711 = -2;
   static boolean[] aBooleanArray1712 = new boolean[100];
   private Class3 aClass3_1713;
   private int anInt1715 = 0;


   static void method1772(int plane, int animId, int dummy, NPC var3) {
      try {
         if(var3.anInt2771 == animId && -1 != animId) {
            AnimationDefinition var4 = Client.getAnimationDefinition(animId);
            int var5 = var4.anInt1845;
            if(var5 == 1) {
               var3.anInt2776 = 1;
               var3.anInt2832 = 0;
               var3.anInt2760 = 0;
               var3.anInt2773 = 0;
               var3.anInt2828 = plane;
               IOHandler.method1470(var3.anInt2829, var4, 183921384, var3.anInt2819, false, var3.anInt2832);
            }

            if(var5 == 2) {
               var3.anInt2773 = 0;
            }
         } else if(animId == -1 || -1 == var3.anInt2771 || Client.getAnimationDefinition(animId).anInt1857 >= Client.getAnimationDefinition(var3.anInt2771).anInt1857) {
            var3.anInt2760 = 0;
            var3.anInt2771 = animId;
            var3.anInt2776 = 1;
            var3.anInt2773 = 0;
            var3.anInt2828 = plane;
            var3.anInt2811 = var3.anInt2816;
            var3.anInt2832 = 0;
            if(var3.anInt2771 != -1) {
               IOHandler.method1470(var3.anInt2829, Client.getAnimationDefinition(var3.anInt2771), dummy + 183921345, var3.anInt2819, false, var3.anInt2832);
            }
         }

         if(dummy != 39) {
            anInt1711 = 41;
         }

      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "sc.J(" + plane + ',' + animId + ',' + dummy + ',' + (var3 != null?"{...}":"null") + ')');
      }
   }

   final void method1773(int var1) {
      try {
         int var2 = 0;

         while(var2 < this.anInt1700) {
            Class3 var3 = this.aClass3Array1697[var2];

            while(true) {
               Class3 var4 = var3.aClass3_74;
               if(var3 == var4) {
                  ++var2;
                  break;
               }

               var4.method86(-1024);
            }
         }

         this.aClass3_1713 = null;
          this.aClass3_1706 = null;
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "sc.G(" + var1 + ')');
      }
   }

   public static void method1774(int var0) {
      try {
         aClass94_1698 = null;
         aBooleanArray1712 = null;
         aBooleanArray1703 = null;
         if(var0 <= 96) {
            method1783(-63, (Component)null);
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "sc.H(" + var0 + ')');
      }
   }

   static void method1775() {
      for(int var0 = 0; var0 < Class3_Sub13_Sub5.anInt3070; ++var0) {
         Class25 var1 = AnimationDefinition.aClass25Array1868[var0];
         Class158.method2186(var1);
         AnimationDefinition.aClass25Array1868[var0] = null;
      }

      Class3_Sub13_Sub5.anInt3070 = 0;
   }

   final Class3 method1776(int var1) {
      try {
         this.anInt1715 = 0;
         return var1 < 11?(Class3)null:this.method1778(-66);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "sc.B(" + var1 + ')');
      }
   }

   static Class3_Sub13 method1777(int var0, boolean var1) {
      try {
         if(var0 == 0) {
            return new Class3_Sub13_Sub22();
         } else if(var0 == 1) {
            return new Class3_Sub13_Sub11();
         } else if (2 == var0) {
            return new Class3_Sub13_Sub31();
         } else if (var0 == 3) {
            return new Class3_Sub13_Sub29();
         } else if (var0 == 4) {
            return new Class3_Sub13_Sub19();
         } else if (var0 == 5) {
            return new Class3_Sub13_Sub24();
         } else if (var0 == 6) {
            return new Class3_Sub13_Sub2();
         } else if (var0 == 7) {
            return new Class3_Sub13_Sub27();
         } else if (var0 == 8) {
            return new Class3_Sub13_Sub39();
         } else if (9 == var0) {
            return new Class3_Sub13_Sub8();
         } else if (10 == var0) {
            return new Class3_Sub13_Sub37();
         } else if (var0 == 11) {
            return new Class3_Sub13_Sub20();
         } else if (var0 == 12) {
            return new Class3_Sub13_Sub1();
         } else if (var0 == 13) {
            return new Class3_Sub13_Sub30();
         } else if (14 == var0) {
            return new Class3_Sub13_Sub32();
         } else if (var0 == 15) {
            return new Class3_Sub13_Sub16();
         } else if (var0 == 16) {
            return new Class3_Sub13_Sub9();
         } else if (17 == var0) {
            return new Class3_Sub13_Sub15();
         } else if (var0 == 18) {
            return new Class3_Sub13_Sub23_Sub1();
         } else if (var0 == 19) {
            return new Class3_Sub13_Sub18();
         } else if (var0 == 20) {
            return new Class3_Sub13_Sub13();
         } else if (21 == var0) {
            return new Class3_Sub13_Sub5();
         } else if (22 == var0) {
            return new Class3_Sub13_Sub35();
         } else if (var0 == 23) {
            return new Class3_Sub13_Sub17();
         } else if (24 == var0) {
            return new Class3_Sub13_Sub12();
         } else if (var0 == 25) {
            return new Class3_Sub13_Sub34();
         } else if (var0 == 26) {
            return new Class3_Sub13_Sub6();
         } else if (27 == var0) {
            return new Class3_Sub13_Sub7();
         } else if (var0 == 28) {
            return new Class3_Sub13_Sub25();
         } else if (var0 == 29) {
            return new Class3_Sub13_Sub33();
         } else if (var0 == 30) {
            return new Class3_Sub13_Sub10();
         } else if (31 == var0) {
            return new Class3_Sub13_Sub14();
         } else if (32 == var0) {
            return new Class3_Sub13_Sub28();
         } else if (33 == var0) {
            return new Class3_Sub13_Sub3();
         } else if (var0 == 34) {
            return new Class3_Sub13_Sub4();
         } else if (var0 == 35) {
            return new Class3_Sub13_Sub26();
         } else if (var0 == 36) {
            return new Class3_Sub13_Sub36();
         } else if (var0 == 37) {
            return new Class3_Sub13_Sub21();
         } else if (38 == var0) {
            return new Class3_Sub13_Sub38();
         } else if (39 == var0) {
            return new Class3_Sub13_Sub23();
         } else {
            if (!var1) {
               method1772(8, 6, 81, (NPC) null);
            }

            return null;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "sc.I(" + var0 + ',' + var1 + ')');
      }
   }

   final Class3 method1778(int var1) {
      try {
         Class3 var2;
         if(this.anInt1715 > 0 && this.aClass3_1713 != this.aClass3Array1697[this.anInt1715 - 1]) {
            var2 = this.aClass3_1713;
         } else {
            do {
               if(this.anInt1700 <= this.anInt1715) {
                  if(var1 > -61) {
                     this.method1773(119);
                  }

                  return null;
               }

               var2 = this.aClass3Array1697[this.anInt1715++].aClass3_74;
            } while(this.aClass3Array1697[this.anInt1715 + -1] == var2);

         }
         this.aClass3_1713 = var2.aClass3_74;
         return var2;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "sc.K(" + var1 + ')');
      }
   }

   final void method1779(Class3 var2, long var3) {
      try {
         if(null != var2.aClass3_76) {
            var2.method86(1 + -1025);
         }

         Class3 var5 = this.aClass3Array1697[(int)(var3 & (long)(this.anInt1700 - 1))];
         var2.aClass3_74 = var5;
         var2.aLong71 = var3;
         var2.aClass3_76 = var5.aClass3_76;
         var2.aClass3_76.aClass3_74 = var2;
         var2.aClass3_74.aClass3_76 = var2;
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "sc.E(" + 1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
      }
   }

   Class130(int var1) {
      try {
         this.aClass3Array1697 = new Class3[var1];
         this.anInt1700 = var1;

         for(int var2 = 0; var2 < var1; ++var2) {
            Class3 var3 = this.aClass3Array1697[var2] = new Class3();
            var3.aClass3_76 = var3;
            var3.aClass3_74 = var3;
         }

      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "sc.<init>(" + var1 + ')');
      }
   }

   final Class3 method1780(long var1, int var3) {
      try {
         if(var3 != 0) {
            this.aClass3Array1697 = (Class3[])null;
         }

         this.aLong1708 = var1;
         Class3 var4 = Objects.requireNonNull(this.aClass3Array1697)[(int)(var1 & (long)(-1 + this.anInt1700))];

         for(this.aClass3_1706 = var4.aClass3_74; var4 != this.aClass3_1706; this.aClass3_1706 = this.aClass3_1706.aClass3_74) {
            if(this.aClass3_1706.aLong71 == var1) {
               Class3 var5 = this.aClass3_1706;
               this.aClass3_1706 = this.aClass3_1706.aClass3_74;
               return var5;
            }
         }

         this.aClass3_1706 = null;
         return null;
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "sc.C(" + var1 + ',' + var3 + ')');
      }
   }

   final int method1781(int var1) {
      try {
         int var3 = 0;

         for(int var4 = 0; var4 < this.anInt1700; ++var4) {
            Class3 var5 = this.aClass3Array1697[var4];

            for(Class3 var6 = var5.aClass3_74; var6 != var5; ++var3) {
               var6 = var6.aClass3_74;
            }
         }

         return var3;
      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "sc.N(" + var1 + ')');
      }
   }

   final void method1782(Class3[] var1, int var2) {
      try {
         int var3 = 0;

         for(int var4 = 0; var4 < this.anInt1700; ++var4) {
            Class3 var5 = this.aClass3Array1697[var4];

            for(Class3 var6 = var5.aClass3_74; var6 != var5; var6 = var6.aClass3_74) {
               var1[var3++] = var6;
            }
         }

      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "sc.D(" + (var1 != null?"{...}":"null") + ',' + var2 + ')');
      }
   }

   static void method1783(int var0, Component var1) {
      try {
         if(var0 != 4) {
            method1777(-104, false);
         }

         var1.removeMouseListener(Class3_Sub28_Sub7_Sub1.aClass149_4047);
         var1.removeMouseMotionListener(Class3_Sub28_Sub7_Sub1.aClass149_4047);
         var1.removeFocusListener(Class3_Sub28_Sub7_Sub1.aClass149_4047);
         GraphicDefinition.anInt549 = 0;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "sc.M(" + var0 + ',' + (var1 != null?"{...}":"null") + ')');
      }
   }

   final Class3 method1784() {
      try {
         if (null != this.aClass3_1706) {
            Class3 var2 = this.aClass3Array1697[(int) (this.aLong1708 & (long) (-1 + this.anInt1700))];

            while (var2 != this.aClass3_1706) {
               if (this.aClass3_1706.aLong71 == this.aLong1708) {
                  Class3 var3 = this.aClass3_1706;
                  this.aClass3_1706 = this.aClass3_1706.aClass3_74;
                  return var3;
               }

               this.aClass3_1706 = this.aClass3_1706.aClass3_74;
            }

            this.aClass3_1706 = null;
         }
         return null;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "sc.A(" + 0 + ')');
      }
   }

   final int method1785(int var1) {
      try {
         if(var1 < 66) {
            method1772(-91, -119, -117, (NPC)null);
         }

         return this.anInt1700;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "sc.F(" + var1 + ')');
      }
   }

}
