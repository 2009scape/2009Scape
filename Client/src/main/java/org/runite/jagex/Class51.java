package org.runite.jagex;

final class Class51 {

   static int[] anIntArray834 = new int[65536];
   static int anInt835;
   private static int[] anIntArray836 = new int[1024];
   static boolean aBoolean837 = false;
   static Interface2 anInterface2_838;
   private static int[] anIntArray839 = new int[512];
   static int[] anIntArray840 = new int[2048];
   static int[] anIntArray841 = new int[2048];
   private static boolean aBoolean842 = false;
   static boolean aBoolean843 = true;
   private static int anInt844;
   private static boolean aBoolean845 = false;
   static int anInt846;
   static int anInt847;
   private static float aFloat848 = 1.0F;
   static boolean aBoolean849 = false;
   static int anInt850 = 0;
   static int[] anIntArray851 = new int[2048];


   static void method1134() {
      method1151(Class74.anInt1093, Class74.anInt1095, Class74.anInt1096, Class74.anInt1099);
   }

   static void method1135(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13, int var14, int var15, int var16, int var17, int var18) {
      int[] var19 = anInterface2_838.method13(var18, true, aFloat848);
      int var20;
      if(var19 == null) {
         var20 = anInterface2_838.method15(var18, '\uffff');
         method1154(var0, var1, var2, var3, var4, var5, method1136(var20, var6), method1136(var20, var7), method1136(var20, var8));
      } else {
         aBoolean845 = anInterface2_838.method14((byte)-106, var18);
         aBoolean842 = anInterface2_838.method7((byte)88, var18);
         var20 = var4 - var3;
         int var21 = var1 - var0;
         int var22 = var5 - var3;
         int var23 = var2 - var0;
         int var24 = var7 - var6;
         int var25 = var8 - var6;
         int var26 = 0;
         if(var1 != var0) {
            var26 = (var4 - var3 << 16) / (var1 - var0);
         }

         int var27 = 0;
         if(var2 != var1) {
            var27 = (var5 - var4 << 16) / (var2 - var1);
         }

         int var28 = 0;
         if(var2 != var0) {
            var28 = (var3 - var5 << 16) / (var0 - var2);
         }

         int var29 = var20 * var23 - var22 * var21;
         if(var29 != 0) {
            int var30 = (var24 * var23 - var25 * var21 << 9) / var29;
            int var31 = (var25 * var20 - var24 * var22 << 9) / var29;
            var10 = var9 - var10;
            var13 = var12 - var13;
            var16 = var15 - var16;
            var11 -= var9;
            var14 -= var12;
            var17 -= var15;
            int var32 = var11 * var12 - var14 * var9 << 14;
            int var33 = var14 * var15 - var17 * var12 << 5;
            int var34 = var17 * var9 - var11 * var15 << 5;
            int var35 = var10 * var12 - var13 * var9 << 14;
            int var36 = var13 * var15 - var16 * var12 << 5;
            int var37 = var16 * var9 - var10 * var15 << 5;
            int var38 = var13 * var11 - var10 * var14 << 14;
            int var39 = var16 * var14 - var13 * var17 << 5;
            int var40 = var10 * var17 - var16 * var11 << 5;
            int var41;
            if(var0 <= var1 && var0 <= var2) {
               if(var0 < anInt844) {
                  if(var1 > anInt844) {
                     var1 = anInt844;
                  }

                  if(var2 > anInt844) {
                     var2 = anInt844;
                  }

                  var6 = (var6 << 9) - var30 * var3 + var30;
                  if(var1 < var2) {
                     var5 = var3 <<= 16;
                     if(var0 < 0) {
                        var5 -= var28 * var0;
                        var3 -= var26 * var0;
                        var6 -= var31 * var0;
                        var0 = 0;
                     }

                     var4 <<= 16;
                     if(var1 < 0) {
                        var4 -= var27 * var1;
                        var1 = 0;
                     }

                     var41 = var0 - anInt835;
                     var32 += var34 * var41;
                     var35 += var37 * var41;
                     var38 += var40 * var41;
                     if((var0 == var1 || var28 >= var26) && (var0 != var1 || var28 <= var27)) {
                        var2 -= var1;
                        var1 -= var0;
                        var0 = anIntArray836[var0];

                        while(true) {
                           --var1;
                           if(var1 < 0) {
                              while(true) {
                                 --var2;
                                 if(var2 < 0) {
                                    return;
                                 }

                                 method1143(Class74.anIntArray1100, var19, var0, var4 >> 16, var5 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
                                 var5 += var28;
                                 var4 += var27;
                                 var6 += var31;
                                 var0 += Class74.anInt1092;
                                 var32 += var34;
                                 var35 += var37;
                                 var38 += var40;
                              }
                           }

                           method1143(Class74.anIntArray1100, var19, var0, var3 >> 16, var5 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
                           var5 += var28;
                           var3 += var26;
                           var6 += var31;
                           var0 += Class74.anInt1092;
                           var32 += var34;
                           var35 += var37;
                           var38 += var40;
                        }
                     } else {
                        var2 -= var1;
                        var1 -= var0;
                        var0 = anIntArray836[var0];

                        while(true) {
                           --var1;
                           if(var1 < 0) {
                              while(true) {
                                 --var2;
                                 if(var2 < 0) {
                                    return;
                                 }

                                 method1143(Class74.anIntArray1100, var19, var0, var5 >> 16, var4 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
                                 var5 += var28;
                                 var4 += var27;
                                 var6 += var31;
                                 var0 += Class74.anInt1092;
                                 var32 += var34;
                                 var35 += var37;
                                 var38 += var40;
                              }
                           }

                           method1143(Class74.anIntArray1100, var19, var0, var5 >> 16, var3 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
                           var5 += var28;
                           var3 += var26;
                           var6 += var31;
                           var0 += Class74.anInt1092;
                           var32 += var34;
                           var35 += var37;
                           var38 += var40;
                        }
                     }
                  } else {
                     var4 = var3 <<= 16;
                     if(var0 < 0) {
                        var4 -= var28 * var0;
                        var3 -= var26 * var0;
                        var6 -= var31 * var0;
                        var0 = 0;
                     }

                     var5 <<= 16;
                     if(var2 < 0) {
                        var5 -= var27 * var2;
                        var2 = 0;
                     }

                     var41 = var0 - anInt835;
                     var32 += var34 * var41;
                     var35 += var37 * var41;
                     var38 += var40 * var41;
                     if((var0 == var2 || var28 >= var26) && (var0 != var2 || var27 <= var26)) {
                        var1 -= var2;
                        var2 -= var0;
                        var0 = anIntArray836[var0];

                        while(true) {
                           --var2;
                           if(var2 < 0) {
                              while(true) {
                                 --var1;
                                 if(var1 < 0) {
                                    return;
                                 }

                                 method1143(Class74.anIntArray1100, var19, var0, var3 >> 16, var5 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
                                 var5 += var27;
                                 var3 += var26;
                                 var6 += var31;
                                 var0 += Class74.anInt1092;
                                 var32 += var34;
                                 var35 += var37;
                                 var38 += var40;
                              }
                           }

                           method1143(Class74.anIntArray1100, var19, var0, var3 >> 16, var4 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
                           var4 += var28;
                           var3 += var26;
                           var6 += var31;
                           var0 += Class74.anInt1092;
                           var32 += var34;
                           var35 += var37;
                           var38 += var40;
                        }
                     } else {
                        var1 -= var2;
                        var2 -= var0;
                        var0 = anIntArray836[var0];

                        while(true) {
                           --var2;
                           if(var2 < 0) {
                              while(true) {
                                 --var1;
                                 if(var1 < 0) {
                                    return;
                                 }

                                 method1143(Class74.anIntArray1100, var19, var0, var5 >> 16, var3 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
                                 var5 += var27;
                                 var3 += var26;
                                 var6 += var31;
                                 var0 += Class74.anInt1092;
                                 var32 += var34;
                                 var35 += var37;
                                 var38 += var40;
                              }
                           }

                           method1143(Class74.anIntArray1100, var19, var0, var4 >> 16, var3 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
                           var4 += var28;
                           var3 += var26;
                           var6 += var31;
                           var0 += Class74.anInt1092;
                           var32 += var34;
                           var35 += var37;
                           var38 += var40;
                        }
                     }
                  }
               }
            } else if(var1 <= var2) {
               if(var1 < anInt844) {
                  if(var2 > anInt844) {
                     var2 = anInt844;
                  }

                  if(var0 > anInt844) {
                     var0 = anInt844;
                  }

                  var7 = (var7 << 9) - var30 * var4 + var30;
                  if(var2 < var0) {
                     var3 = var4 <<= 16;
                     if(var1 < 0) {
                        var3 -= var26 * var1;
                        var4 -= var27 * var1;
                        var7 -= var31 * var1;
                        var1 = 0;
                     }

                     var5 <<= 16;
                     if(var2 < 0) {
                        var5 -= var28 * var2;
                        var2 = 0;
                     }

                     var41 = var1 - anInt835;
                     var32 += var34 * var41;
                     var35 += var37 * var41;
                     var38 += var40 * var41;
                     if((var1 == var2 || var26 >= var27) && (var1 != var2 || var26 <= var28)) {
                        var0 -= var2;
                        var2 -= var1;
                        var1 = anIntArray836[var1];

                        while(true) {
                           --var2;
                           if(var2 < 0) {
                              while(true) {
                                 --var0;
                                 if(var0 < 0) {
                                    return;
                                 }

                                 method1143(Class74.anIntArray1100, var19, var1, var5 >> 16, var3 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
                                 var3 += var26;
                                 var5 += var28;
                                 var7 += var31;
                                 var1 += Class74.anInt1092;
                                 var32 += var34;
                                 var35 += var37;
                                 var38 += var40;
                              }
                           }

                           method1143(Class74.anIntArray1100, var19, var1, var4 >> 16, var3 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
                           var3 += var26;
                           var4 += var27;
                           var7 += var31;
                           var1 += Class74.anInt1092;
                           var32 += var34;
                           var35 += var37;
                           var38 += var40;
                        }
                     } else {
                        var0 -= var2;
                        var2 -= var1;
                        var1 = anIntArray836[var1];

                        while(true) {
                           --var2;
                           if(var2 < 0) {
                              while(true) {
                                 --var0;
                                 if(var0 < 0) {
                                    return;
                                 }

                                 method1143(Class74.anIntArray1100, var19, var1, var3 >> 16, var5 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
                                 var3 += var26;
                                 var5 += var28;
                                 var7 += var31;
                                 var1 += Class74.anInt1092;
                                 var32 += var34;
                                 var35 += var37;
                                 var38 += var40;
                              }
                           }

                           method1143(Class74.anIntArray1100, var19, var1, var3 >> 16, var4 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
                           var3 += var26;
                           var4 += var27;
                           var7 += var31;
                           var1 += Class74.anInt1092;
                           var32 += var34;
                           var35 += var37;
                           var38 += var40;
                        }
                     }
                  } else {
                     var5 = var4 <<= 16;
                     if(var1 < 0) {
                        var5 -= var26 * var1;
                        var4 -= var27 * var1;
                        var7 -= var31 * var1;
                        var1 = 0;
                     }

                     var3 <<= 16;
                     if(var0 < 0) {
                        var3 -= var28 * var0;
                        var0 = 0;
                     }

                     var41 = var1 - anInt835;
                     var32 += var34 * var41;
                     var35 += var37 * var41;
                     var38 += var40 * var41;
                     if(var26 < var27) {
                        var2 -= var0;
                        var0 -= var1;
                        var1 = anIntArray836[var1];

                        while(true) {
                           --var0;
                           if(var0 < 0) {
                              while(true) {
                                 --var2;
                                 if(var2 < 0) {
                                    return;
                                 }

                                 method1143(Class74.anIntArray1100, var19, var1, var3 >> 16, var4 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
                                 var3 += var28;
                                 var4 += var27;
                                 var7 += var31;
                                 var1 += Class74.anInt1092;
                                 var32 += var34;
                                 var35 += var37;
                                 var38 += var40;
                              }
                           }

                           method1143(Class74.anIntArray1100, var19, var1, var5 >> 16, var4 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
                           var5 += var26;
                           var4 += var27;
                           var7 += var31;
                           var1 += Class74.anInt1092;
                           var32 += var34;
                           var35 += var37;
                           var38 += var40;
                        }
                     } else {
                        var2 -= var0;
                        var0 -= var1;
                        var1 = anIntArray836[var1];

                        while(true) {
                           --var0;
                           if(var0 < 0) {
                              while(true) {
                                 --var2;
                                 if(var2 < 0) {
                                    return;
                                 }

                                 method1143(Class74.anIntArray1100, var19, var1, var4 >> 16, var3 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
                                 var3 += var28;
                                 var4 += var27;
                                 var7 += var31;
                                 var1 += Class74.anInt1092;
                                 var32 += var34;
                                 var35 += var37;
                                 var38 += var40;
                              }
                           }

                           method1143(Class74.anIntArray1100, var19, var1, var4 >> 16, var5 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
                           var5 += var26;
                           var4 += var27;
                           var7 += var31;
                           var1 += Class74.anInt1092;
                           var32 += var34;
                           var35 += var37;
                           var38 += var40;
                        }
                     }
                  }
               }
            } else if(var2 < anInt844) {
               if(var0 > anInt844) {
                  var0 = anInt844;
               }

               if(var1 > anInt844) {
                  var1 = anInt844;
               }

               var8 = (var8 << 9) - var30 * var5 + var30;
               if(var0 < var1) {
                  var4 = var5 <<= 16;
                  if(var2 < 0) {
                     var4 -= var27 * var2;
                     var5 -= var28 * var2;
                     var8 -= var31 * var2;
                     var2 = 0;
                  }

                  var3 <<= 16;
                  if(var0 < 0) {
                     var3 -= var26 * var0;
                     var0 = 0;
                  }

                  var41 = var2 - anInt835;
                  var32 += var34 * var41;
                  var35 += var37 * var41;
                  var38 += var40 * var41;
                  if(var27 < var28) {
                     var1 -= var0;
                     var0 -= var2;
                     var2 = anIntArray836[var2];

                     while(true) {
                        --var0;
                        if(var0 < 0) {
                           while(true) {
                              --var1;
                              if(var1 < 0) {
                                 return;
                              }

                              method1143(Class74.anIntArray1100, var19, var2, var4 >> 16, var3 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
                              var4 += var27;
                              var3 += var26;
                              var8 += var31;
                              var2 += Class74.anInt1092;
                              var32 += var34;
                              var35 += var37;
                              var38 += var40;
                           }
                        }

                        method1143(Class74.anIntArray1100, var19, var2, var4 >> 16, var5 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
                        var4 += var27;
                        var5 += var28;
                        var8 += var31;
                        var2 += Class74.anInt1092;
                        var32 += var34;
                        var35 += var37;
                        var38 += var40;
                     }
                  } else {
                     var1 -= var0;
                     var0 -= var2;
                     var2 = anIntArray836[var2];

                     while(true) {
                        --var0;
                        if(var0 < 0) {
                           while(true) {
                              --var1;
                              if(var1 < 0) {
                                 return;
                              }

                              method1143(Class74.anIntArray1100, var19, var2, var3 >> 16, var4 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
                              var4 += var27;
                              var3 += var26;
                              var8 += var31;
                              var2 += Class74.anInt1092;
                              var32 += var34;
                              var35 += var37;
                              var38 += var40;
                           }
                        }

                        method1143(Class74.anIntArray1100, var19, var2, var5 >> 16, var4 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
                        var4 += var27;
                        var5 += var28;
                        var8 += var31;
                        var2 += Class74.anInt1092;
                        var32 += var34;
                        var35 += var37;
                        var38 += var40;
                     }
                  }
               } else {
                  var3 = var5 <<= 16;
                  if(var2 < 0) {
                     var3 -= var27 * var2;
                     var5 -= var28 * var2;
                     var8 -= var31 * var2;
                     var2 = 0;
                  }

                  var4 <<= 16;
                  if(var1 < 0) {
                     var4 -= var26 * var1;
                     var1 = 0;
                  }

                  var41 = var2 - anInt835;
                  var32 += var34 * var41;
                  var35 += var37 * var41;
                  var38 += var40 * var41;
                  if(var27 < var28) {
                     var0 -= var1;
                     var1 -= var2;
                     var2 = anIntArray836[var2];

                     while(true) {
                        --var1;
                        if(var1 < 0) {
                           while(true) {
                              --var0;
                              if(var0 < 0) {
                                 return;
                              }

                              method1143(Class74.anIntArray1100, var19, var2, var4 >> 16, var5 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
                              var4 += var26;
                              var5 += var28;
                              var8 += var31;
                              var2 += Class74.anInt1092;
                              var32 += var34;
                              var35 += var37;
                              var38 += var40;
                           }
                        }

                        method1143(Class74.anIntArray1100, var19, var2, var3 >> 16, var5 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
                        var3 += var27;
                        var5 += var28;
                        var8 += var31;
                        var2 += Class74.anInt1092;
                        var32 += var34;
                        var35 += var37;
                        var38 += var40;
                     }
                  } else {
                     var0 -= var1;
                     var1 -= var2;
                     var2 = anIntArray836[var2];

                     while(true) {
                        --var1;
                        if(var1 < 0) {
                           while(true) {
                              --var0;
                              if(var0 < 0) {
                                 return;
                              }

                              method1143(Class74.anIntArray1100, var19, var2, var5 >> 16, var4 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
                              var4 += var26;
                              var5 += var28;
                              var8 += var31;
                              var2 += Class74.anInt1092;
                              var32 += var34;
                              var35 += var37;
                              var38 += var40;
                           }
                        }

                        method1143(Class74.anIntArray1100, var19, var2, var5 >> 16, var3 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
                        var3 += var27;
                        var5 += var28;
                        var8 += var31;
                        var2 += Class74.anInt1092;
                        var32 += var34;
                        var35 += var37;
                        var38 += var40;
                     }
                  }
               }
            }
         }
      }
   }

   private static int method1136(int var0, int var1) {
      var1 = var1 * (var0 & 127) >> 7;
      if(var1 < 2) {
         var1 = 2;
      } else if(var1 > 126) {
         var1 = 126;
      }

      return (var0 & '\uff80') + var1;
   }

   static void method1137(float var0) {
      method1152(var0);
      method1150();
   }

   static void method1138(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13, int var14, int var15, int var16, int var17, int var18) {
      int[] var19 = anInterface2_838.method13(var18, true, aFloat848);
      int var20;
      if(var19 != null && anInt850 <= 10) {
         aBoolean845 = anInterface2_838.method14((byte)-115, var18);
         aBoolean842 = anInterface2_838.method7((byte)88, var18);
         var20 = var4 - var3;
         int var21 = var1 - var0;
         int var22 = var5 - var3;
         int var23 = var2 - var0;
         int var24 = var7 - var6;
         int var25 = var8 - var6;
         int var26 = 0;
         if(var1 != var0) {
            var26 = (var4 - var3 << 16) / (var1 - var0);
         }

         int var27 = 0;
         if(var2 != var1) {
            var27 = (var5 - var4 << 16) / (var2 - var1);
         }

         int var28 = 0;
         if(var2 != var0) {
            var28 = (var3 - var5 << 16) / (var0 - var2);
         }

         int var29 = var20 * var23 - var22 * var21;
         if(var29 != 0) {
            int var30 = (var24 * var23 - var25 * var21 << 9) / var29;
            int var31 = (var25 * var20 - var24 * var22 << 9) / var29;
            var10 = var9 - var10;
            var13 = var12 - var13;
            var16 = var15 - var16;
            var11 -= var9;
            var14 -= var12;
            var17 -= var15;
            int var32 = var11 * var12 - var14 * var9 << 14;
            int var33 = var14 * var15 - var17 * var12 << 8;
            int var34 = var17 * var9 - var11 * var15 << 5;
            int var35 = var10 * var12 - var13 * var9 << 14;
            int var36 = var13 * var15 - var16 * var12 << 8;
            int var37 = var16 * var9 - var10 * var15 << 5;
            int var38 = var13 * var11 - var10 * var14 << 14;
            int var39 = var16 * var14 - var13 * var17 << 8;
            int var40 = var10 * var17 - var16 * var11 << 5;
            int var41;
            if(var0 <= var1 && var0 <= var2) {
               if(var0 < anInt844) {
                  if(var1 > anInt844) {
                     var1 = anInt844;
                  }

                  if(var2 > anInt844) {
                     var2 = anInt844;
                  }

                  var6 = (var6 << 9) - var30 * var3 + var30;
                  if(var1 < var2) {
                     var5 = var3 <<= 16;
                     if(var0 < 0) {
                        var5 -= var28 * var0;
                        var3 -= var26 * var0;
                        var6 -= var31 * var0;
                        var0 = 0;
                     }

                     var4 <<= 16;
                     if(var1 < 0) {
                        var4 -= var27 * var1;
                        var1 = 0;
                     }

                     var41 = var0 - anInt835;
                     var32 += var34 * var41;
                     var35 += var37 * var41;
                     var38 += var40 * var41;
                     if((var0 == var1 || var28 >= var26) && (var0 != var1 || var28 <= var27)) {
                        var2 -= var1;
                        var1 -= var0;
                        var0 = anIntArray836[var0];

                        while(true) {
                           --var1;
                           if(var1 < 0) {
                              while(true) {
                                 --var2;
                                 if(var2 < 0) {
                                    return;
                                 }

                                 method1142(Class74.anIntArray1100, var19, var0, var4 >> 16, var5 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
                                 var5 += var28;
                                 var4 += var27;
                                 var6 += var31;
                                 var0 += Class74.anInt1092;
                                 var32 += var34;
                                 var35 += var37;
                                 var38 += var40;
                              }
                           }

                           method1142(Class74.anIntArray1100, var19, var0, var3 >> 16, var5 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
                           var5 += var28;
                           var3 += var26;
                           var6 += var31;
                           var0 += Class74.anInt1092;
                           var32 += var34;
                           var35 += var37;
                           var38 += var40;
                        }
                     } else {
                        var2 -= var1;
                        var1 -= var0;
                        var0 = anIntArray836[var0];

                        while(true) {
                           --var1;
                           if(var1 < 0) {
                              while(true) {
                                 --var2;
                                 if(var2 < 0) {
                                    return;
                                 }

                                 method1142(Class74.anIntArray1100, var19, var0, var5 >> 16, var4 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
                                 var5 += var28;
                                 var4 += var27;
                                 var6 += var31;
                                 var0 += Class74.anInt1092;
                                 var32 += var34;
                                 var35 += var37;
                                 var38 += var40;
                              }
                           }

                           method1142(Class74.anIntArray1100, var19, var0, var5 >> 16, var3 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
                           var5 += var28;
                           var3 += var26;
                           var6 += var31;
                           var0 += Class74.anInt1092;
                           var32 += var34;
                           var35 += var37;
                           var38 += var40;
                        }
                     }
                  } else {
                     var4 = var3 <<= 16;
                     if(var0 < 0) {
                        var4 -= var28 * var0;
                        var3 -= var26 * var0;
                        var6 -= var31 * var0;
                        var0 = 0;
                     }

                     var5 <<= 16;
                     if(var2 < 0) {
                        var5 -= var27 * var2;
                        var2 = 0;
                     }

                     var41 = var0 - anInt835;
                     var32 += var34 * var41;
                     var35 += var37 * var41;
                     var38 += var40 * var41;
                     if((var0 == var2 || var28 >= var26) && (var0 != var2 || var27 <= var26)) {
                        var1 -= var2;
                        var2 -= var0;
                        var0 = anIntArray836[var0];

                        while(true) {
                           --var2;
                           if(var2 < 0) {
                              while(true) {
                                 --var1;
                                 if(var1 < 0) {
                                    return;
                                 }

                                 method1142(Class74.anIntArray1100, var19, var0, var3 >> 16, var5 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
                                 var5 += var27;
                                 var3 += var26;
                                 var6 += var31;
                                 var0 += Class74.anInt1092;
                                 var32 += var34;
                                 var35 += var37;
                                 var38 += var40;
                              }
                           }

                           method1142(Class74.anIntArray1100, var19, var0, var3 >> 16, var4 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
                           var4 += var28;
                           var3 += var26;
                           var6 += var31;
                           var0 += Class74.anInt1092;
                           var32 += var34;
                           var35 += var37;
                           var38 += var40;
                        }
                     } else {
                        var1 -= var2;
                        var2 -= var0;
                        var0 = anIntArray836[var0];

                        while(true) {
                           --var2;
                           if(var2 < 0) {
                              while(true) {
                                 --var1;
                                 if(var1 < 0) {
                                    return;
                                 }

                                 method1142(Class74.anIntArray1100, var19, var0, var5 >> 16, var3 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
                                 var5 += var27;
                                 var3 += var26;
                                 var6 += var31;
                                 var0 += Class74.anInt1092;
                                 var32 += var34;
                                 var35 += var37;
                                 var38 += var40;
                              }
                           }

                           method1142(Class74.anIntArray1100, var19, var0, var4 >> 16, var3 >> 16, var6, var30, var32, var35, var38, var33, var36, var39);
                           var4 += var28;
                           var3 += var26;
                           var6 += var31;
                           var0 += Class74.anInt1092;
                           var32 += var34;
                           var35 += var37;
                           var38 += var40;
                        }
                     }
                  }
               }
            } else if(var1 <= var2) {
               if(var1 < anInt844) {
                  if(var2 > anInt844) {
                     var2 = anInt844;
                  }

                  if(var0 > anInt844) {
                     var0 = anInt844;
                  }

                  var7 = (var7 << 9) - var30 * var4 + var30;
                  if(var2 < var0) {
                     var3 = var4 <<= 16;
                     if(var1 < 0) {
                        var3 -= var26 * var1;
                        var4 -= var27 * var1;
                        var7 -= var31 * var1;
                        var1 = 0;
                     }

                     var5 <<= 16;
                     if(var2 < 0) {
                        var5 -= var28 * var2;
                        var2 = 0;
                     }

                     var41 = var1 - anInt835;
                     var32 += var34 * var41;
                     var35 += var37 * var41;
                     var38 += var40 * var41;
                     if((var1 == var2 || var26 >= var27) && (var1 != var2 || var26 <= var28)) {
                        var0 -= var2;
                        var2 -= var1;
                        var1 = anIntArray836[var1];

                        while(true) {
                           --var2;
                           if(var2 < 0) {
                              while(true) {
                                 --var0;
                                 if(var0 < 0) {
                                    return;
                                 }

                                 method1142(Class74.anIntArray1100, var19, var1, var5 >> 16, var3 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
                                 var3 += var26;
                                 var5 += var28;
                                 var7 += var31;
                                 var1 += Class74.anInt1092;
                                 var32 += var34;
                                 var35 += var37;
                                 var38 += var40;
                              }
                           }

                           method1142(Class74.anIntArray1100, var19, var1, var4 >> 16, var3 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
                           var3 += var26;
                           var4 += var27;
                           var7 += var31;
                           var1 += Class74.anInt1092;
                           var32 += var34;
                           var35 += var37;
                           var38 += var40;
                        }
                     } else {
                        var0 -= var2;
                        var2 -= var1;
                        var1 = anIntArray836[var1];

                        while(true) {
                           --var2;
                           if(var2 < 0) {
                              while(true) {
                                 --var0;
                                 if(var0 < 0) {
                                    return;
                                 }

                                 method1142(Class74.anIntArray1100, var19, var1, var3 >> 16, var5 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
                                 var3 += var26;
                                 var5 += var28;
                                 var7 += var31;
                                 var1 += Class74.anInt1092;
                                 var32 += var34;
                                 var35 += var37;
                                 var38 += var40;
                              }
                           }

                           method1142(Class74.anIntArray1100, var19, var1, var3 >> 16, var4 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
                           var3 += var26;
                           var4 += var27;
                           var7 += var31;
                           var1 += Class74.anInt1092;
                           var32 += var34;
                           var35 += var37;
                           var38 += var40;
                        }
                     }
                  } else {
                     var5 = var4 <<= 16;
                     if(var1 < 0) {
                        var5 -= var26 * var1;
                        var4 -= var27 * var1;
                        var7 -= var31 * var1;
                        var1 = 0;
                     }

                     var3 <<= 16;
                     if(var0 < 0) {
                        var3 -= var28 * var0;
                        var0 = 0;
                     }

                     var41 = var1 - anInt835;
                     var32 += var34 * var41;
                     var35 += var37 * var41;
                     var38 += var40 * var41;
                     if(var26 < var27) {
                        var2 -= var0;
                        var0 -= var1;
                        var1 = anIntArray836[var1];

                        while(true) {
                           --var0;
                           if(var0 < 0) {
                              while(true) {
                                 --var2;
                                 if(var2 < 0) {
                                    return;
                                 }

                                 method1142(Class74.anIntArray1100, var19, var1, var3 >> 16, var4 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
                                 var3 += var28;
                                 var4 += var27;
                                 var7 += var31;
                                 var1 += Class74.anInt1092;
                                 var32 += var34;
                                 var35 += var37;
                                 var38 += var40;
                              }
                           }

                           method1142(Class74.anIntArray1100, var19, var1, var5 >> 16, var4 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
                           var5 += var26;
                           var4 += var27;
                           var7 += var31;
                           var1 += Class74.anInt1092;
                           var32 += var34;
                           var35 += var37;
                           var38 += var40;
                        }
                     } else {
                        var2 -= var0;
                        var0 -= var1;
                        var1 = anIntArray836[var1];

                        while(true) {
                           --var0;
                           if(var0 < 0) {
                              while(true) {
                                 --var2;
                                 if(var2 < 0) {
                                    return;
                                 }

                                 method1142(Class74.anIntArray1100, var19, var1, var4 >> 16, var3 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
                                 var3 += var28;
                                 var4 += var27;
                                 var7 += var31;
                                 var1 += Class74.anInt1092;
                                 var32 += var34;
                                 var35 += var37;
                                 var38 += var40;
                              }
                           }

                           method1142(Class74.anIntArray1100, var19, var1, var4 >> 16, var5 >> 16, var7, var30, var32, var35, var38, var33, var36, var39);
                           var5 += var26;
                           var4 += var27;
                           var7 += var31;
                           var1 += Class74.anInt1092;
                           var32 += var34;
                           var35 += var37;
                           var38 += var40;
                        }
                     }
                  }
               }
            } else if(var2 < anInt844) {
               if(var0 > anInt844) {
                  var0 = anInt844;
               }

               if(var1 > anInt844) {
                  var1 = anInt844;
               }

               var8 = (var8 << 9) - var30 * var5 + var30;
               if(var0 < var1) {
                  var4 = var5 <<= 16;
                  if(var2 < 0) {
                     var4 -= var27 * var2;
                     var5 -= var28 * var2;
                     var8 -= var31 * var2;
                     var2 = 0;
                  }

                  var3 <<= 16;
                  if(var0 < 0) {
                     var3 -= var26 * var0;
                     var0 = 0;
                  }

                  var41 = var2 - anInt835;
                  var32 += var34 * var41;
                  var35 += var37 * var41;
                  var38 += var40 * var41;
                  if(var27 < var28) {
                     var1 -= var0;
                     var0 -= var2;
                     var2 = anIntArray836[var2];

                     while(true) {
                        --var0;
                        if(var0 < 0) {
                           while(true) {
                              --var1;
                              if(var1 < 0) {
                                 return;
                              }

                              method1142(Class74.anIntArray1100, var19, var2, var4 >> 16, var3 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
                              var4 += var27;
                              var3 += var26;
                              var8 += var31;
                              var2 += Class74.anInt1092;
                              var32 += var34;
                              var35 += var37;
                              var38 += var40;
                           }
                        }

                        method1142(Class74.anIntArray1100, var19, var2, var4 >> 16, var5 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
                        var4 += var27;
                        var5 += var28;
                        var8 += var31;
                        var2 += Class74.anInt1092;
                        var32 += var34;
                        var35 += var37;
                        var38 += var40;
                     }
                  } else {
                     var1 -= var0;
                     var0 -= var2;
                     var2 = anIntArray836[var2];

                     while(true) {
                        --var0;
                        if(var0 < 0) {
                           while(true) {
                              --var1;
                              if(var1 < 0) {
                                 return;
                              }

                              method1142(Class74.anIntArray1100, var19, var2, var3 >> 16, var4 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
                              var4 += var27;
                              var3 += var26;
                              var8 += var31;
                              var2 += Class74.anInt1092;
                              var32 += var34;
                              var35 += var37;
                              var38 += var40;
                           }
                        }

                        method1142(Class74.anIntArray1100, var19, var2, var5 >> 16, var4 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
                        var4 += var27;
                        var5 += var28;
                        var8 += var31;
                        var2 += Class74.anInt1092;
                        var32 += var34;
                        var35 += var37;
                        var38 += var40;
                     }
                  }
               } else {
                  var3 = var5 <<= 16;
                  if(var2 < 0) {
                     var3 -= var27 * var2;
                     var5 -= var28 * var2;
                     var8 -= var31 * var2;
                     var2 = 0;
                  }

                  var4 <<= 16;
                  if(var1 < 0) {
                     var4 -= var26 * var1;
                     var1 = 0;
                  }

                  var41 = var2 - anInt835;
                  var32 += var34 * var41;
                  var35 += var37 * var41;
                  var38 += var40 * var41;
                  if(var27 < var28) {
                     var0 -= var1;
                     var1 -= var2;
                     var2 = anIntArray836[var2];

                     while(true) {
                        --var1;
                        if(var1 < 0) {
                           while(true) {
                              --var0;
                              if(var0 < 0) {
                                 return;
                              }

                              method1142(Class74.anIntArray1100, var19, var2, var4 >> 16, var5 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
                              var4 += var26;
                              var5 += var28;
                              var8 += var31;
                              var2 += Class74.anInt1092;
                              var32 += var34;
                              var35 += var37;
                              var38 += var40;
                           }
                        }

                        method1142(Class74.anIntArray1100, var19, var2, var3 >> 16, var5 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
                        var3 += var27;
                        var5 += var28;
                        var8 += var31;
                        var2 += Class74.anInt1092;
                        var32 += var34;
                        var35 += var37;
                        var38 += var40;
                     }
                  } else {
                     var0 -= var1;
                     var1 -= var2;
                     var2 = anIntArray836[var2];

                     while(true) {
                        --var1;
                        if(var1 < 0) {
                           while(true) {
                              --var0;
                              if(var0 < 0) {
                                 return;
                              }

                              method1142(Class74.anIntArray1100, var19, var2, var5 >> 16, var4 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
                              var4 += var26;
                              var5 += var28;
                              var8 += var31;
                              var2 += Class74.anInt1092;
                              var32 += var34;
                              var35 += var37;
                              var38 += var40;
                           }
                        }

                        method1142(Class74.anIntArray1100, var19, var2, var5 >> 16, var3 >> 16, var8, var30, var32, var35, var38, var33, var36, var39);
                        var3 += var27;
                        var5 += var28;
                        var8 += var31;
                        var2 += Class74.anInt1092;
                        var32 += var34;
                        var35 += var37;
                        var38 += var40;
                     }
                  }
               }
            }
         }
      } else {
         var20 = anInterface2_838.method15(var18, '\uffff');
         aBoolean837 = true;
         method1154(var0, var1, var2, var3, var4, var5, method1136(var20, var6), method1136(var20, var7), method1136(var20, var8));
      }
   }

   static int method1139() {
      return anIntArray836[0] % Class74.anInt1092;
   }

   static void method1140(Interface2 var0) {
      anInterface2_838 = var0;
   }

   static void method1141() {
      anInt846 = anInt847 / 2;
      anInt835 = anInt844 / 2;
      Class139.anInt1824 = -anInt846;
      Class145.anInt1898 = anInt847 - anInt846;
      Class1.anInt55 = -anInt835;
      Class86.anInt1195 = anInt844 - anInt835;
   }

   private static void method1142(int[] var0, int[] var1, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13, int var14) {
      if(aBoolean849) {
         if(var6 > anInt847) {
            var6 = anInt847;
         }

         if(var5 < 0) {
            var5 = 0;
         }
      }

      if(var5 < var6) {
         var4 += var5;
         var7 += var8 * var5;
         int var17 = var6 - var5;
         int var15;
         int var16;
         int var19;
         int var18;
         int var21;
         int var20;
         int var23;
         int var22;
         int var3;
         int var2;
         if(aBoolean845) {
            var23 = var5 - anInt846;
            var9 += (var12 >> 3) * var23;
            var10 += (var13 >> 3) * var23;
            var11 += (var14 >> 3) * var23;
            var22 = var11 >> 12;
            if(var22 == 0) {
               var18 = 0;
               var19 = 0;
            } else {
               var18 = var9 / var22;
               var19 = var10 / var22;
            }

             var9 += var12;
            var10 += var13;
            var11 += var14;
            var22 = var11 >> 12;
            if(var22 == 0) {
               var20 = 0;
               var21 = 0;
            } else {
               var20 = var9 / var22;
               var21 = var10 / var22;
            }

             var2 = (var18 << 20) + var19;
            var16 = (var20 - var18 >> 3 << 20) + (var21 - var19 >> 3);
            var17 >>= 3;
            var8 <<= 3;
            var15 = var7 >> 8;
            if(aBoolean842) {
               if(var17 > 0) {
                  do {
                     var3 = var1[(var2 & 4032) + (var2 >>> 26)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 4032) + (var2 >>> 26)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 4032) + (var2 >>> 26)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 4032) + (var2 >>> 26)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 4032) + (var2 >>> 26)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 4032) + (var2 >>> 26)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 4032) + (var2 >>> 26)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 4032) + (var2 >>> 26)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                      var18 = var20;
                     var19 = var21;
                     var9 += var12;
                     var10 += var13;
                     var11 += var14;
                     var22 = var11 >> 12;
                     if(var22 == 0) {
                        var20 = 0;
                        var21 = 0;
                     } else {
                        var20 = var9 / var22;
                        var21 = var10 / var22;
                     }

                      var2 = (var18 << 20) + var19;
                     var16 = (var20 - var18 >> 3 << 20) + (var21 - var19 >> 3);
                     var7 += var8;
                     var15 = var7 >> 8;
                     --var17;
                  } while(var17 > 0);
               }

               var17 = var6 - var5 & 7;
               if(var17 > 0) {
                  do {
                     var3 = var1[(var2 & 4032) + (var2 >>> 26)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     --var17;
                  } while(var17 > 0);
               }
            } else {
               if(var17 > 0) {
                  do {
                     if((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                      var18 = var20;
                     var19 = var21;
                     var9 += var12;
                     var10 += var13;
                     var11 += var14;
                     var22 = var11 >> 12;
                     if(var22 == 0) {
                        var20 = 0;
                        var21 = 0;
                     } else {
                        var20 = var9 / var22;
                        var21 = var10 / var22;
                     }

                      var2 = (var18 << 20) + var19;
                     var16 = (var20 - var18 >> 3 << 20) + (var21 - var19 >> 3);
                     var7 += var8;
                     var15 = var7 >> 8;
                     --var17;
                  } while(var17 > 0);
               }

               var17 = var6 - var5 & 7;
               if(var17 > 0) {
                  do {
                     if((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     --var17;
                  } while(var17 > 0);
               }
            }
         } else {
            var23 = var5 - anInt846;
            var9 += (var12 >> 3) * var23;
            var10 += (var13 >> 3) * var23;
            var11 += (var14 >> 3) * var23;
            var22 = var11 >> 14;
            if(var22 == 0) {
               var18 = 0;
               var19 = 0;
            } else {
               var18 = var9 / var22;
               var19 = var10 / var22;
            }

             var9 += var12;
            var10 += var13;
            var11 += var14;
            var22 = var11 >> 14;
            if(var22 == 0) {
               var20 = 0;
               var21 = 0;
            } else {
               var20 = var9 / var22;
               var21 = var10 / var22;
            }

             var2 = (var18 << 18) + var19;
            var16 = (var20 - var18 >> 3 << 18) + (var21 - var19 >> 3);
            var17 >>= 3;
            var8 <<= 3;
            var15 = var7 >> 8;
            if(aBoolean842) {
               if(var17 > 0) {
                  do {
                     var3 = var1[(var2 & 16256) + (var2 >>> 25)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 16256) + (var2 >>> 25)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 16256) + (var2 >>> 25)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 16256) + (var2 >>> 25)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 16256) + (var2 >>> 25)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 16256) + (var2 >>> 25)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 16256) + (var2 >>> 25)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 16256) + (var2 >>> 25)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                      var18 = var20;
                     var19 = var21;
                     var9 += var12;
                     var10 += var13;
                     var11 += var14;
                     var22 = var11 >> 14;
                     if(var22 == 0) {
                        var20 = 0;
                        var21 = 0;
                     } else {
                        var20 = var9 / var22;
                        var21 = var10 / var22;
                     }

                      var2 = (var18 << 18) + var19;
                     var16 = (var20 - var18 >> 3 << 18) + (var21 - var19 >> 3);
                     var7 += var8;
                     var15 = var7 >> 8;
                     --var17;
                  } while(var17 > 0);
               }

               var17 = var6 - var5 & 7;
               if(var17 > 0) {
                  do {
                     var3 = var1[(var2 & 16256) + (var2 >>> 25)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     --var17;
                  } while(var17 > 0);
               }
            } else {
               if(var17 > 0) {
                  do {
                     if((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                      var18 = var20;
                     var19 = var21;
                     var9 += var12;
                     var10 += var13;
                     var11 += var14;
                     var22 = var11 >> 14;
                     if(var22 == 0) {
                        var20 = 0;
                        var21 = 0;
                     } else {
                        var20 = var9 / var22;
                        var21 = var10 / var22;
                     }

                      var2 = (var18 << 18) + var19;
                     var16 = (var20 - var18 >> 3 << 18) + (var21 - var19 >> 3);
                     var7 += var8;
                     var15 = var7 >> 8;
                     --var17;
                  } while(var17 > 0);
               }

               var17 = var6 - var5 & 7;
               if(var17 > 0) {
                  do {
                     if((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     --var17;
                  } while(var17 > 0);
               }
            }
         }

      }
   }

   private static void method1143(int[] var0, int[] var1, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13, int var14) {
      if(aBoolean849) {
         if(var6 > anInt847) {
            var6 = anInt847;
         }

         if(var5 < 0) {
            var5 = 0;
         }
      }

      if(var5 < var6) {
         var4 += var5;
         var7 += var8 * var5;
         int var17 = var6 - var5;
         int var15;
         int var16;
         int var19;
         int var18;
         int var21;
         int var20;
         int var23;
         int var22;
         int var3;
         int var2;
         if(aBoolean845) {
            var23 = var5 - anInt846;
            var9 += var12 * var23;
            var10 += var13 * var23;
            var11 += var14 * var23;
            var22 = var11 >> 12;
            if(var22 == 0) {
               var18 = 0;
               var19 = 0;
            } else {
               var18 = var9 / var22;
               var19 = var10 / var22;
            }

             var9 += var12 * var17;
            var10 += var13 * var17;
            var11 += var14 * var17;
            var22 = var11 >> 12;
            if(var22 == 0) {
               var20 = 0;
               var21 = 0;
            } else {
               var20 = var9 / var22;
               var21 = var10 / var22;
            }

             var2 = (var18 << 20) + var19;
            var16 = ((var20 - var18) / var17 << 20) + (var21 - var19) / var17;
            var17 >>= 3;
            var8 <<= 3;
            var15 = var7 >> 8;
            if(aBoolean842) {
               if(var17 > 0) {
                  do {
                     var3 = var1[(var2 & 4032) + (var2 >>> 26)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 4032) + (var2 >>> 26)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 4032) + (var2 >>> 26)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 4032) + (var2 >>> 26)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 4032) + (var2 >>> 26)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 4032) + (var2 >>> 26)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 4032) + (var2 >>> 26)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 4032) + (var2 >>> 26)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var7 += var8;
                     var15 = var7 >> 8;
                     --var17;
                  } while(var17 > 0);
               }

               var17 = var6 - var5 & 7;
               if(var17 > 0) {
                  do {
                     var3 = var1[(var2 & 4032) + (var2 >>> 26)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     --var17;
                  } while(var17 > 0);
               }
            } else {
               if(var17 > 0) {
                  do {
                     if((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     var7 += var8;
                     var15 = var7 >> 8;
                     --var17;
                  } while(var17 > 0);
               }

               var17 = var6 - var5 & 7;
               if(var17 > 0) {
                  do {
                     if((var3 = var1[(var2 & 4032) + (var2 >>> 26)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     --var17;
                  } while(var17 > 0);
               }
            }
         } else {
            var23 = var5 - anInt846;
            var9 += var12 * var23;
            var10 += var13 * var23;
            var11 += var14 * var23;
            var22 = var11 >> 14;
            if(var22 == 0) {
               var18 = 0;
               var19 = 0;
            } else {
               var18 = var9 / var22;
               var19 = var10 / var22;
            }

             var9 += var12 * var17;
            var10 += var13 * var17;
            var11 += var14 * var17;
            var22 = var11 >> 14;
            if(var22 == 0) {
               var20 = 0;
               var21 = 0;
            } else {
               var20 = var9 / var22;
               var21 = var10 / var22;
            }

             var2 = (var18 << 18) + var19;
            var16 = ((var20 - var18) / var17 << 18) + (var21 - var19) / var17;
            var17 >>= 3;
            var8 <<= 3;
            var15 = var7 >> 8;
            if(aBoolean842) {
               if(var17 > 0) {
                  do {
                     var3 = var1[(var2 & 16256) + (var2 >>> 25)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 16256) + (var2 >>> 25)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 16256) + (var2 >>> 25)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 16256) + (var2 >>> 25)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 16256) + (var2 >>> 25)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 16256) + (var2 >>> 25)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 16256) + (var2 >>> 25)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var3 = var1[(var2 & 16256) + (var2 >>> 25)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     var7 += var8;
                     var15 = var7 >> 8;
                     --var17;
                  } while(var17 > 0);
               }

               var17 = var6 - var5 & 7;
               if(var17 > 0) {
                  do {
                     var3 = var1[(var2 & 16256) + (var2 >>> 25)];
                     var0[var4++] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     var2 += var16;
                     --var17;
                  } while(var17 > 0);
               }
            } else {
               if(var17 > 0) {
                  do {
                     if((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     if((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     var7 += var8;
                     var15 = var7 >> 8;
                     --var17;
                  } while(var17 > 0);
               }

               var17 = var6 - var5 & 7;
               if(var17 > 0) {
                  do {
                     if((var3 = var1[(var2 & 16256) + (var2 >>> 25)]) != 0) {
                        var0[var4] = ((var3 & 16711935) * var15 & -16711936) + ((var3 & '\uff00') * var15 & 16711680) >> 8;
                     }

                     ++var4;
                     var2 += var16;
                     --var17;
                  } while(var17 > 0);
               }
            }
         }

      }
   }

   static void method1144(int var0, int var1, int var2, int var3, int var4, int var5, int var6) {
      int var7 = 0;
      if(var1 != var0) {
         var7 = (var4 - var3 << 16) / (var1 - var0);
      }

      int var8 = 0;
      if(var2 != var1) {
         var8 = (var5 - var4 << 16) / (var2 - var1);
      }

      int var9 = 0;
      if(var2 != var0) {
         var9 = (var3 - var5 << 16) / (var0 - var2);
      }

      if(var0 <= var1 && var0 <= var2) {
         if(var0 < anInt844) {
            if(var1 > anInt844) {
               var1 = anInt844;
            }

            if(var2 > anInt844) {
               var2 = anInt844;
            }

            if(var1 < var2) {
               var5 = var3 <<= 16;
               if(var0 < 0) {
                  var5 -= var9 * var0;
                  var3 -= var7 * var0;
                  var0 = 0;
               }

               var4 <<= 16;
               if(var1 < 0) {
                  var4 -= var8 * var1;
                  var1 = 0;
               }

               if((var0 == var1 || var9 >= var7) && (var0 != var1 || var9 <= var8)) {
                  var2 -= var1;
                  var1 -= var0;
                  var0 = anIntArray836[var0];

                  while(true) {
                     --var1;
                     if(var1 < 0) {
                        while(true) {
                           --var2;
                           if(var2 < 0) {
                              return;
                           }

                           method1149(Class74.anIntArray1100, var0, var6, var4 >> 16, var5 >> 16);
                           var5 += var9;
                           var4 += var8;
                           var0 += Class74.anInt1092;
                        }
                     }

                     method1149(Class74.anIntArray1100, var0, var6, var3 >> 16, var5 >> 16);
                     var5 += var9;
                     var3 += var7;
                     var0 += Class74.anInt1092;
                  }
               } else {
                  var2 -= var1;
                  var1 -= var0;
                  var0 = anIntArray836[var0];

                  while(true) {
                     --var1;
                     if(var1 < 0) {
                        while(true) {
                           --var2;
                           if(var2 < 0) {
                              return;
                           }

                           method1149(Class74.anIntArray1100, var0, var6, var5 >> 16, var4 >> 16);
                           var5 += var9;
                           var4 += var8;
                           var0 += Class74.anInt1092;
                        }
                     }

                     method1149(Class74.anIntArray1100, var0, var6, var5 >> 16, var3 >> 16);
                     var5 += var9;
                     var3 += var7;
                     var0 += Class74.anInt1092;
                  }
               }
            } else {
               var4 = var3 <<= 16;
               if(var0 < 0) {
                  var4 -= var9 * var0;
                  var3 -= var7 * var0;
                  var0 = 0;
               }

               var5 <<= 16;
               if(var2 < 0) {
                  var5 -= var8 * var2;
                  var2 = 0;
               }

               if((var0 == var2 || var9 >= var7) && (var0 != var2 || var8 <= var7)) {
                  var1 -= var2;
                  var2 -= var0;
                  var0 = anIntArray836[var0];

                  while(true) {
                     --var2;
                     if(var2 < 0) {
                        while(true) {
                           --var1;
                           if(var1 < 0) {
                              return;
                           }

                           method1149(Class74.anIntArray1100, var0, var6, var3 >> 16, var5 >> 16);
                           var5 += var8;
                           var3 += var7;
                           var0 += Class74.anInt1092;
                        }
                     }

                     method1149(Class74.anIntArray1100, var0, var6, var3 >> 16, var4 >> 16);
                     var4 += var9;
                     var3 += var7;
                     var0 += Class74.anInt1092;
                  }
               } else {
                  var1 -= var2;
                  var2 -= var0;
                  var0 = anIntArray836[var0];

                  while(true) {
                     --var2;
                     if(var2 < 0) {
                        while(true) {
                           --var1;
                           if(var1 < 0) {
                              return;
                           }

                           method1149(Class74.anIntArray1100, var0, var6, var5 >> 16, var3 >> 16);
                           var5 += var8;
                           var3 += var7;
                           var0 += Class74.anInt1092;
                        }
                     }

                     method1149(Class74.anIntArray1100, var0, var6, var4 >> 16, var3 >> 16);
                     var4 += var9;
                     var3 += var7;
                     var0 += Class74.anInt1092;
                  }
               }
            }
         }
      } else if(var1 <= var2) {
         if(var1 < anInt844) {
            if(var2 > anInt844) {
               var2 = anInt844;
            }

            if(var0 > anInt844) {
               var0 = anInt844;
            }

            if(var2 < var0) {
               var3 = var4 <<= 16;
               if(var1 < 0) {
                  var3 -= var7 * var1;
                  var4 -= var8 * var1;
                  var1 = 0;
               }

               var5 <<= 16;
               if(var2 < 0) {
                  var5 -= var9 * var2;
                  var2 = 0;
               }

               if((var1 == var2 || var7 >= var8) && (var1 != var2 || var7 <= var9)) {
                  var0 -= var2;
                  var2 -= var1;
                  var1 = anIntArray836[var1];

                  while(true) {
                     --var2;
                     if(var2 < 0) {
                        while(true) {
                           --var0;
                           if(var0 < 0) {
                              return;
                           }

                           method1149(Class74.anIntArray1100, var1, var6, var5 >> 16, var3 >> 16);
                           var3 += var7;
                           var5 += var9;
                           var1 += Class74.anInt1092;
                        }
                     }

                     method1149(Class74.anIntArray1100, var1, var6, var4 >> 16, var3 >> 16);
                     var3 += var7;
                     var4 += var8;
                     var1 += Class74.anInt1092;
                  }
               } else {
                  var0 -= var2;
                  var2 -= var1;
                  var1 = anIntArray836[var1];

                  while(true) {
                     --var2;
                     if(var2 < 0) {
                        while(true) {
                           --var0;
                           if(var0 < 0) {
                              return;
                           }

                           method1149(Class74.anIntArray1100, var1, var6, var3 >> 16, var5 >> 16);
                           var3 += var7;
                           var5 += var9;
                           var1 += Class74.anInt1092;
                        }
                     }

                     method1149(Class74.anIntArray1100, var1, var6, var3 >> 16, var4 >> 16);
                     var3 += var7;
                     var4 += var8;
                     var1 += Class74.anInt1092;
                  }
               }
            } else {
               var5 = var4 <<= 16;
               if(var1 < 0) {
                  var5 -= var7 * var1;
                  var4 -= var8 * var1;
                  var1 = 0;
               }

               var3 <<= 16;
               if(var0 < 0) {
                  var3 -= var9 * var0;
                  var0 = 0;
               }

               if(var7 < var8) {
                  var2 -= var0;
                  var0 -= var1;
                  var1 = anIntArray836[var1];

                  while(true) {
                     --var0;
                     if(var0 < 0) {
                        while(true) {
                           --var2;
                           if(var2 < 0) {
                              return;
                           }

                           method1149(Class74.anIntArray1100, var1, var6, var3 >> 16, var4 >> 16);
                           var3 += var9;
                           var4 += var8;
                           var1 += Class74.anInt1092;
                        }
                     }

                     method1149(Class74.anIntArray1100, var1, var6, var5 >> 16, var4 >> 16);
                     var5 += var7;
                     var4 += var8;
                     var1 += Class74.anInt1092;
                  }
               } else {
                  var2 -= var0;
                  var0 -= var1;
                  var1 = anIntArray836[var1];

                  while(true) {
                     --var0;
                     if(var0 < 0) {
                        while(true) {
                           --var2;
                           if(var2 < 0) {
                              return;
                           }

                           method1149(Class74.anIntArray1100, var1, var6, var4 >> 16, var3 >> 16);
                           var3 += var9;
                           var4 += var8;
                           var1 += Class74.anInt1092;
                        }
                     }

                     method1149(Class74.anIntArray1100, var1, var6, var4 >> 16, var5 >> 16);
                     var5 += var7;
                     var4 += var8;
                     var1 += Class74.anInt1092;
                  }
               }
            }
         }
      } else if(var2 < anInt844) {
         if(var0 > anInt844) {
            var0 = anInt844;
         }

         if(var1 > anInt844) {
            var1 = anInt844;
         }

         if(var0 < var1) {
            var4 = var5 <<= 16;
            if(var2 < 0) {
               var4 -= var8 * var2;
               var5 -= var9 * var2;
               var2 = 0;
            }

            var3 <<= 16;
            if(var0 < 0) {
               var3 -= var7 * var0;
               var0 = 0;
            }

            if(var8 < var9) {
               var1 -= var0;
               var0 -= var2;
               var2 = anIntArray836[var2];

               while(true) {
                  --var0;
                  if(var0 < 0) {
                     while(true) {
                        --var1;
                        if(var1 < 0) {
                           return;
                        }

                        method1149(Class74.anIntArray1100, var2, var6, var4 >> 16, var3 >> 16);
                        var4 += var8;
                        var3 += var7;
                        var2 += Class74.anInt1092;
                     }
                  }

                  method1149(Class74.anIntArray1100, var2, var6, var4 >> 16, var5 >> 16);
                  var4 += var8;
                  var5 += var9;
                  var2 += Class74.anInt1092;
               }
            } else {
               var1 -= var0;
               var0 -= var2;
               var2 = anIntArray836[var2];

               while(true) {
                  --var0;
                  if(var0 < 0) {
                     while(true) {
                        --var1;
                        if(var1 < 0) {
                           return;
                        }

                        method1149(Class74.anIntArray1100, var2, var6, var3 >> 16, var4 >> 16);
                        var4 += var8;
                        var3 += var7;
                        var2 += Class74.anInt1092;
                     }
                  }

                  method1149(Class74.anIntArray1100, var2, var6, var5 >> 16, var4 >> 16);
                  var4 += var8;
                  var5 += var9;
                  var2 += Class74.anInt1092;
               }
            }
         } else {
            var3 = var5 <<= 16;
            if(var2 < 0) {
               var3 -= var8 * var2;
               var5 -= var9 * var2;
               var2 = 0;
            }

            var4 <<= 16;
            if(var1 < 0) {
               var4 -= var7 * var1;
               var1 = 0;
            }

            if(var8 < var9) {
               var0 -= var1;
               var1 -= var2;
               var2 = anIntArray836[var2];

               while(true) {
                  --var1;
                  if(var1 < 0) {
                     while(true) {
                        --var0;
                        if(var0 < 0) {
                           return;
                        }

                        method1149(Class74.anIntArray1100, var2, var6, var4 >> 16, var5 >> 16);
                        var4 += var7;
                        var5 += var9;
                        var2 += Class74.anInt1092;
                     }
                  }

                  method1149(Class74.anIntArray1100, var2, var6, var3 >> 16, var5 >> 16);
                  var3 += var8;
                  var5 += var9;
                  var2 += Class74.anInt1092;
               }
            } else {
               var0 -= var1;
               var1 -= var2;
               var2 = anIntArray836[var2];

               while(true) {
                  --var1;
                  if(var1 < 0) {
                     while(true) {
                        --var0;
                        if(var0 < 0) {
                           return;
                        }

                        method1149(Class74.anIntArray1100, var2, var6, var5 >> 16, var4 >> 16);
                        var4 += var7;
                        var5 += var9;
                        var2 += Class74.anInt1092;
                     }
                  }

                  method1149(Class74.anIntArray1100, var2, var6, var5 >> 16, var3 >> 16);
                  var3 += var8;
                  var5 += var9;
                  var2 += Class74.anInt1092;
               }
            }
         }
      }
   }

   static void method1145(int var0, int var1) {
      int var2 = anIntArray836[0];
      int var3 = var2 / Class74.anInt1092;
      int var4 = var2 - var3 * Class74.anInt1092;
      anInt846 = var0 - var4;
      anInt835 = var1 - var3;
      Class139.anInt1824 = -anInt846;
      Class145.anInt1898 = anInt847 - anInt846;
      Class1.anInt55 = -anInt835;
      Class86.anInt1195 = anInt844 - anInt835;
   }

   private static void method1146(int[] var0, int var1, int var4, int var5, int var6, int var7) {
      if(aBoolean849) {
         if(var5 > anInt847) {
            var5 = anInt847;
         }

         if(var4 < 0) {
            var4 = 0;
         }
      }

      if(var4 < var5) {
         var1 += var4;
         var6 += var7 * var4;
         int var8;
         int var9;
         int var10;
         int var3;
         int var2;
         if(aBoolean843) {
            var3 = var5 - var4 >> 2;
            var7 <<= 2;
            if(anInt850 == 0) {
               if(var3 > 0) {
                  do {
                     var2 = anIntArray834[var6 >> 8];
                     var6 += var7;
                     var0[var1++] = var2;
                     var0[var1++] = var2;
                     var0[var1++] = var2;
                     var0[var1++] = var2;
                     --var3;
                  } while(var3 > 0);
               }

               var3 = var5 - var4 & 3;
               if(var3 > 0) {
                  var2 = anIntArray834[var6 >> 8];

                  do {
                     var0[var1++] = var2;
                     --var3;
                  } while(var3 > 0);
               }
            } else {
               var8 = anInt850;
               var9 = 256 - anInt850;
               if(var3 > 0) {
                  do {
                     var2 = anIntArray834[var6 >> 8];
                     var6 += var7;
                     var2 = ((var2 & 16711935) * var9 >> 8 & 16711935) + ((var2 & '\uff00') * var9 >> 8 & '\uff00');
                     var10 = var0[var1];
                     var0[var1++] = var2 + ((var10 & 16711935) * var8 >> 8 & 16711935) + ((var10 & '\uff00') * var8 >> 8 & '\uff00');
                     var10 = var0[var1];
                     var0[var1++] = var2 + ((var10 & 16711935) * var8 >> 8 & 16711935) + ((var10 & '\uff00') * var8 >> 8 & '\uff00');
                     var10 = var0[var1];
                     var0[var1++] = var2 + ((var10 & 16711935) * var8 >> 8 & 16711935) + ((var10 & '\uff00') * var8 >> 8 & '\uff00');
                     var10 = var0[var1];
                     var0[var1++] = var2 + ((var10 & 16711935) * var8 >> 8 & 16711935) + ((var10 & '\uff00') * var8 >> 8 & '\uff00');
                     --var3;
                  } while(var3 > 0);
               }

               var3 = var5 - var4 & 3;
               if(var3 > 0) {
                  var2 = anIntArray834[var6 >> 8];
                  var2 = ((var2 & 16711935) * var9 >> 8 & 16711935) + ((var2 & '\uff00') * var9 >> 8 & '\uff00');

                  do {
                     var10 = var0[var1];
                     var0[var1++] = var2 + ((var10 & 16711935) * var8 >> 8 & 16711935) + ((var10 & '\uff00') * var8 >> 8 & '\uff00');
                     --var3;
                  } while(var3 > 0);
               }
            }

         } else {
            var3 = var5 - var4;
            if(anInt850 == 0) {
               do {
                  var0[var1++] = anIntArray834[var6 >> 8];
                  var6 += var7;
                  --var3;
               } while(var3 > 0);
            } else {
               var8 = anInt850;
               var9 = 256 - anInt850;

               do {
                  var2 = anIntArray834[var6 >> 8];
                  var6 += var7;
                  var2 = ((var2 & 16711935) * var9 >> 8 & 16711935) + ((var2 & '\uff00') * var9 >> 8 & '\uff00');
                  var10 = var0[var1];
                  var0[var1++] = var2 + ((var10 & 16711935) * var8 >> 8 & 16711935) + ((var10 & '\uff00') * var8 >> 8 & '\uff00');
                  --var3;
               } while(var3 > 0);
            }

         }
      }
   }

   static void method1147(byte[] var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      int var8 = 0;
      if(var2 != var1) {
         var8 = (var5 - var4 << 16) / (var2 - var1);
      }

      int var9 = 0;
      if(var3 != var2) {
         var9 = (var6 - var5 << 16) / (var3 - var2);
      }

      int var10 = 0;
      if(var3 != var1) {
         var10 = (var4 - var6 << 16) / (var1 - var3);
      }

      if(var1 <= var2 && var1 <= var3) {
         if(var2 < var3) {
            var6 = var4 <<= 16;
            if(var1 < 0) {
               var6 -= var10 * var1;
               var4 -= var8 * var1;
               var1 = 0;
            }

            var5 <<= 16;
            if(var2 < 0) {
               var5 -= var9 * var2;
               var2 = 0;
            }

            if((var1 == var2 || var10 >= var8) && (var1 != var2 || var10 <= var9)) {
               var3 -= var2;
               var2 -= var1;
               var1 *= var7;

               while(true) {
                  --var2;
                  if(var2 < 0) {
                     while(true) {
                        --var3;
                        if(var3 < 0) {
                           return;
                        }

                        method1156(var0, var1, var5 >> 16, var6 >> 16);
                        var6 += var10;
                        var5 += var9;
                        var1 += var7;
                     }
                  }

                  method1156(var0, var1, var4 >> 16, var6 >> 16);
                  var6 += var10;
                  var4 += var8;
                  var1 += var7;
               }
            } else {
               var3 -= var2;
               var2 -= var1;
               var1 *= var7;

               while(true) {
                  --var2;
                  if(var2 < 0) {
                     while(true) {
                        --var3;
                        if(var3 < 0) {
                           return;
                        }

                        method1156(var0, var1, var6 >> 16, var5 >> 16);
                        var6 += var10;
                        var5 += var9;
                        var1 += var7;
                     }
                  }

                  method1156(var0, var1, var6 >> 16, var4 >> 16);
                  var6 += var10;
                  var4 += var8;
                  var1 += var7;
               }
            }
         } else {
            var5 = var4 <<= 16;
            if(var1 < 0) {
               var5 -= var10 * var1;
               var4 -= var8 * var1;
               var1 = 0;
            }

            var6 <<= 16;
            if(var3 < 0) {
               var6 -= var9 * var3;
               var3 = 0;
            }

            if((var1 == var3 || var10 >= var8) && (var1 != var3 || var9 <= var8)) {
               var2 -= var3;
               var3 -= var1;
               var1 *= var7;

               while(true) {
                  --var3;
                  if(var3 < 0) {
                     while(true) {
                        --var2;
                        if(var2 < 0) {
                           return;
                        }

                        method1156(var0, var1, var4 >> 16, var6 >> 16);
                        var6 += var9;
                        var4 += var8;
                        var1 += var7;
                     }
                  }

                  method1156(var0, var1, var4 >> 16, var5 >> 16);
                  var5 += var10;
                  var4 += var8;
                  var1 += var7;
               }
            } else {
               var2 -= var3;
               var3 -= var1;
               var1 *= var7;

               while(true) {
                  --var3;
                  if(var3 < 0) {
                     while(true) {
                        --var2;
                        if(var2 < 0) {
                           return;
                        }

                        method1156(var0, var1, var6 >> 16, var4 >> 16);
                        var6 += var9;
                        var4 += var8;
                        var1 += var7;
                     }
                  }

                  method1156(var0, var1, var5 >> 16, var4 >> 16);
                  var5 += var10;
                  var4 += var8;
                  var1 += var7;
               }
            }
         }
      } else if(var2 <= var3) {
         if(var3 < var1) {
            var4 = var5 <<= 16;
            if(var2 < 0) {
               var4 -= var8 * var2;
               var5 -= var9 * var2;
               var2 = 0;
            }

            var6 <<= 16;
            if(var3 < 0) {
               var6 -= var10 * var3;
               var3 = 0;
            }

            if((var2 == var3 || var8 >= var9) && (var2 != var3 || var8 <= var10)) {
               var1 -= var3;
               var3 -= var2;
               var2 *= var7;

               while(true) {
                  --var3;
                  if(var3 < 0) {
                     while(true) {
                        --var1;
                        if(var1 < 0) {
                           return;
                        }

                        method1156(var0, var2, var6 >> 16, var4 >> 16);
                        var4 += var8;
                        var6 += var10;
                        var2 += var7;
                     }
                  }

                  method1156(var0, var2, var5 >> 16, var4 >> 16);
                  var4 += var8;
                  var5 += var9;
                  var2 += var7;
               }
            } else {
               var1 -= var3;
               var3 -= var2;
               var2 *= var7;

               while(true) {
                  --var3;
                  if(var3 < 0) {
                     while(true) {
                        --var1;
                        if(var1 < 0) {
                           return;
                        }

                        method1156(var0, var2, var4 >> 16, var6 >> 16);
                        var4 += var8;
                        var6 += var10;
                        var2 += var7;
                     }
                  }

                  method1156(var0, var2, var4 >> 16, var5 >> 16);
                  var4 += var8;
                  var5 += var9;
                  var2 += var7;
               }
            }
         } else {
            var6 = var5 <<= 16;
            if(var2 < 0) {
               var6 -= var8 * var2;
               var5 -= var9 * var2;
               var2 = 0;
            }

            var4 <<= 16;
            if(var1 < 0) {
               var4 -= var10 * var1;
               var1 = 0;
            }

            if(var8 < var9) {
               var3 -= var1;
               var1 -= var2;
               var2 *= var7;

               while(true) {
                  --var1;
                  if(var1 < 0) {
                     while(true) {
                        --var3;
                        if(var3 < 0) {
                           return;
                        }

                        method1156(var0, var2, var4 >> 16, var5 >> 16);
                        var4 += var10;
                        var5 += var9;
                        var2 += var7;
                     }
                  }

                  method1156(var0, var2, var6 >> 16, var5 >> 16);
                  var6 += var8;
                  var5 += var9;
                  var2 += var7;
               }
            } else {
               var3 -= var1;
               var1 -= var2;
               var2 *= var7;

               while(true) {
                  --var1;
                  if(var1 < 0) {
                     while(true) {
                        --var3;
                        if(var3 < 0) {
                           return;
                        }

                        method1156(var0, var2, var5 >> 16, var4 >> 16);
                        var4 += var10;
                        var5 += var9;
                        var2 += var7;
                     }
                  }

                  method1156(var0, var2, var5 >> 16, var6 >> 16);
                  var6 += var8;
                  var5 += var9;
                  var2 += var7;
               }
            }
         }
      } else if(var1 < var2) {
         var5 = var6 <<= 16;
         if(var3 < 0) {
            var5 -= var9 * var3;
            var6 -= var10 * var3;
            var3 = 0;
         }

         var4 <<= 16;
         if(var1 < 0) {
            var4 -= var8 * var1;
            var1 = 0;
         }

         if(var9 < var10) {
            var2 -= var1;
            var1 -= var3;
            var3 *= var7;

            while(true) {
               --var1;
               if(var1 < 0) {
                  while(true) {
                     --var2;
                     if(var2 < 0) {
                        return;
                     }

                     method1156(var0, var3, var5 >> 16, var4 >> 16);
                     var5 += var9;
                     var4 += var8;
                     var3 += var7;
                  }
               }

               method1156(var0, var3, var5 >> 16, var6 >> 16);
               var5 += var9;
               var6 += var10;
               var3 += var7;
            }
         } else {
            var2 -= var1;
            var1 -= var3;
            var3 *= var7;

            while(true) {
               --var1;
               if(var1 < 0) {
                  while(true) {
                     --var2;
                     if(var2 < 0) {
                        return;
                     }

                     method1156(var0, var3, var4 >> 16, var5 >> 16);
                     var5 += var9;
                     var4 += var8;
                     var3 += var7;
                  }
               }

               method1156(var0, var3, var6 >> 16, var5 >> 16);
               var5 += var9;
               var6 += var10;
               var3 += var7;
            }
         }
      } else {
         var4 = var6 <<= 16;
         if(var3 < 0) {
            var4 -= var9 * var3;
            var6 -= var10 * var3;
            var3 = 0;
         }

         var5 <<= 16;
         if(var2 < 0) {
            var5 -= var8 * var2;
            var2 = 0;
         }

         if(var9 < var10) {
            var1 -= var2;
            var2 -= var3;
            var3 *= var7;

            while(true) {
               --var2;
               if(var2 < 0) {
                  while(true) {
                     --var1;
                     if(var1 < 0) {
                        return;
                     }

                     method1156(var0, var3, var5 >> 16, var6 >> 16);
                     var5 += var8;
                     var6 += var10;
                     var3 += var7;
                  }
               }

               method1156(var0, var3, var4 >> 16, var6 >> 16);
               var4 += var9;
               var6 += var10;
               var3 += var7;
            }
         } else {
            var1 -= var2;
            var2 -= var3;
            var3 *= var7;

            while(true) {
               --var2;
               if(var2 < 0) {
                  while(true) {
                     --var1;
                     if(var1 < 0) {
                        return;
                     }

                     method1156(var0, var3, var6 >> 16, var5 >> 16);
                     var5 += var8;
                     var6 += var10;
                     var3 += var7;
                  }
               }

               method1156(var0, var3, var6 >> 16, var4 >> 16);
               var4 += var9;
               var6 += var10;
               var3 += var7;
            }
         }
      }
   }

   static void method1148(int var0, int var1, int var2) {
      aBoolean849 = var0 < 0 || var0 > anInt847 || var1 < 0 || var1 > anInt847 || var2 < 0 || var2 > anInt847;
   }

   private static void method1149(int[] var0, int var1, int var2, int var4, int var5) {
      if(aBoolean849) {
         if(var5 > anInt847) {
            var5 = anInt847;
         }

         if(var4 < 0) {
            var4 = 0;
         }
      }

      if(var4 < var5) {
         var1 += var4;
         int var3 = var5 - var4 >> 2;
         if(anInt850 == 0) {
            while(true) {
               --var3;
               if(var3 < 0) {
                  var3 = var5 - var4 & 3;

                  while(true) {
                     --var3;
                     if(var3 < 0) {
                        return;
                     }

                     var0[var1++] = var2;
                  }
               }

               var0[var1++] = var2;
               var0[var1++] = var2;
               var0[var1++] = var2;
               var0[var1++] = var2;
            }
         } else if(anInt850 == 254) {
            while(true) {
               --var3;
               if(var3 < 0) {
                  var3 = var5 - var4 & 3;

                  while(true) {
                     --var3;
                     if(var3 < 0) {
                        return;
                     }

                     var0[var1++] = var0[var1];
                  }
               }

               var0[var1++] = var0[var1];
               var0[var1++] = var0[var1];
               var0[var1++] = var0[var1];
               var0[var1++] = var0[var1];
            }
         } else {
            int var6 = anInt850;
            int var7 = 256 - anInt850;
            var2 = ((var2 & 16711935) * var7 >> 8 & 16711935) + ((var2 & '\uff00') * var7 >> 8 & '\uff00');

            while(true) {
               --var3;
               int var8;
               if(var3 < 0) {
                  var3 = var5 - var4 & 3;

                  while(true) {
                     --var3;
                     if(var3 < 0) {
                        return;
                     }

                     var8 = var0[var1];
                     var0[var1++] = var2 + ((var8 & 16711935) * var6 >> 8 & 16711935) + ((var8 & '\uff00') * var6 >> 8 & '\uff00');
                  }
               }

               var8 = var0[var1];
               var0[var1++] = var2 + ((var8 & 16711935) * var6 >> 8 & 16711935) + ((var8 & '\uff00') * var6 >> 8 & '\uff00');
               var8 = var0[var1];
               var0[var1++] = var2 + ((var8 & 16711935) * var6 >> 8 & 16711935) + ((var8 & '\uff00') * var6 >> 8 & '\uff00');
               var8 = var0[var1];
               var0[var1++] = var2 + ((var8 & 16711935) * var6 >> 8 & 16711935) + ((var8 & '\uff00') * var6 >> 8 & '\uff00');
               var8 = var0[var1];
               var0[var1++] = var2 + ((var8 & 16711935) * var6 >> 8 & 16711935) + ((var8 & '\uff00') * var6 >> 8 & '\uff00');
            }
         }
      }
   }

   private static void method1150() {
      int var2 = 0;

      for(int var3 = 0; var3 < 512; ++var3) {
         double var4 = (double)(var3 >> 3) / 64.0D + 0.0078125D;
         double var6 = (double)(var3 & 7) / 8.0D + 0.0625D;

         for(int var8 = 0; var8 < 128; ++var8) {
            double var9 = (double)var8 / 128.0D;
            double var11 = var9;
            double var13 = var9;
            double var15 = var9;
            if(var6 != 0.0D) {
               double var17;
               if(var9 < 0.5D) {
                  var17 = var9 * (1.0D + var6);
               } else {
                  var17 = var9 + var6 - var9 * var6;
               }

               double var19 = 2.0D * var9 - var17;
               double var21 = var4 + 0.3333333333333333D;
               if(var21 > 1.0D) {
                  --var21;
               }

               double var25 = var4 - 0.3333333333333333D;
               if(var25 < 0.0D) {
                  ++var25;
               }

               if(6.0D * var21 < 1.0D) {
                  var11 = var19 + (var17 - var19) * 6.0D * var21;
               } else if(2.0D * var21 < 1.0D) {
                  var11 = var17;
               } else if(3.0D * var21 < 2.0D) {
                  var11 = var19 + (var17 - var19) * (0.6666666666666666D - var21) * 6.0D;
               } else {
                  var11 = var19;
               }

               if(6.0D * var4 < 1.0D) {
                  var13 = var19 + (var17 - var19) * 6.0D * var4;
               } else if(2.0D * var4 < 1.0D) {
                  var13 = var17;
               } else if(3.0D * var4 < 2.0D) {
                  var13 = var19 + (var17 - var19) * (0.6666666666666666D - var4) * 6.0D;
               } else {
                  var13 = var19;
               }

               if(6.0D * var25 < 1.0D) {
                  var15 = var19 + (var17 - var19) * 6.0D * var25;
               } else if(2.0D * var25 < 1.0D) {
                  var15 = var17;
               } else if(3.0D * var25 < 2.0D) {
                  var15 = var19 + (var17 - var19) * (0.6666666666666666D - var25) * 6.0D;
               } else {
                  var15 = var19;
               }
            }

            var11 = Math.pow(var11, (double)aFloat848);
            var13 = Math.pow(var13, (double)aFloat848);
            var15 = Math.pow(var15, (double)aFloat848);
            int var27 = (int)(var11 * 256.0D);
            int var18 = (int)(var13 * 256.0D);
            int var28 = (int)(var15 * 256.0D);
            int var20 = (var27 << 16) + (var18 << 8) + var28;
            if(var20 == 0) {
               var20 = 1;
            }

            anIntArray834[var2++] = var20;
         }
      }

   }

   private static void method1151(int var0, int var1, int var2, int var3) {
      anInt847 = var2 - var0;
      anInt844 = var3 - var1;
      method1141();
      if(anIntArray836.length < anInt844) {
         anIntArray836 = new int[Class95.method1585((byte)95, anInt844)];
      }

      int var4 = var1 * Class74.anInt1092 + var0;

      for(int var5 = 0; var5 < anInt844; ++var5) {
         anIntArray836[var5] = var4;
         var4 += Class74.anInt1092;
      }

   }

   private static void method1152(float var0) {
      aFloat848 = var0;
      aFloat848 = (float)((double)aFloat848 + (Math.random() * 0.03D - 0.015D));
   }

   static int method1153() {
      return anIntArray836[0] / Class74.anInt1092;
   }

   static void method1154(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      int var9 = var4 - var3;
      int var10 = var1 - var0;
      int var11 = var5 - var3;
      int var12 = var2 - var0;
      int var13 = var7 - var6;
      int var14 = var8 - var6;
      int var15;
      if(var2 == var1) {
         var15 = 0;
      } else {
         var15 = (var5 - var4 << 16) / (var2 - var1);
      }

       int var16;
      if(var1 == var0) {
         var16 = 0;
      } else {
         var16 = (var9 << 16) / var10;
      }

       int var17;
      if(var2 == var0) {
         var17 = 0;
      } else {
         var17 = (var11 << 16) / var12;
      }

       int var18 = var9 * var12 - var11 * var10;
      if(var18 != 0) {
         int var19 = (var13 * var12 - var14 * var10 << 8) / var18;
         int var20 = (var14 * var9 - var13 * var11 << 8) / var18;
         if(var0 <= var1 && var0 <= var2) {
            if(var0 < anInt844) {
               if(var1 > anInt844) {
                  var1 = anInt844;
               }

               if(var2 > anInt844) {
                  var2 = anInt844;
               }

               var6 = (var6 << 8) - var19 * var3 + var19;
               if(var1 < var2) {
                  var5 = var3 <<= 16;
                  if(var0 < 0) {
                     var5 -= var17 * var0;
                     var3 -= var16 * var0;
                     var6 -= var20 * var0;
                     var0 = 0;
                  }

                  var4 <<= 16;
                  if(var1 < 0) {
                     var4 -= var15 * var1;
                     var1 = 0;
                  }

                  if((var0 == var1 || var17 >= var16) && (var0 != var1 || var17 <= var15)) {
                     var2 -= var1;
                     var1 -= var0;
                     var0 = anIntArray836[var0];

                     while(true) {
                        --var1;
                        if(var1 < 0) {
                           while(true) {
                              --var2;
                              if(var2 < 0) {
                                 return;
                              }

                              method1146(Class74.anIntArray1100, var0, var4 >> 16, var5 >> 16, var6, var19);
                              var5 += var17;
                              var4 += var15;
                              var6 += var20;
                              var0 += Class74.anInt1092;
                           }
                        }

                        method1146(Class74.anIntArray1100, var0, var3 >> 16, var5 >> 16, var6, var19);
                        var5 += var17;
                        var3 += var16;
                        var6 += var20;
                        var0 += Class74.anInt1092;
                     }
                  } else {
                     var2 -= var1;
                     var1 -= var0;
                     var0 = anIntArray836[var0];

                     while(true) {
                        --var1;
                        if(var1 < 0) {
                           while(true) {
                              --var2;
                              if(var2 < 0) {
                                 return;
                              }

                              method1146(Class74.anIntArray1100, var0, var5 >> 16, var4 >> 16, var6, var19);
                              var5 += var17;
                              var4 += var15;
                              var6 += var20;
                              var0 += Class74.anInt1092;
                           }
                        }

                        method1146(Class74.anIntArray1100, var0, var5 >> 16, var3 >> 16, var6, var19);
                        var5 += var17;
                        var3 += var16;
                        var6 += var20;
                        var0 += Class74.anInt1092;
                     }
                  }
               } else {
                  var4 = var3 <<= 16;
                  if(var0 < 0) {
                     var4 -= var17 * var0;
                     var3 -= var16 * var0;
                     var6 -= var20 * var0;
                     var0 = 0;
                  }

                  var5 <<= 16;
                  if(var2 < 0) {
                     var5 -= var15 * var2;
                     var2 = 0;
                  }

                  if((var0 == var2 || var17 >= var16) && (var0 != var2 || var15 <= var16)) {
                     var1 -= var2;
                     var2 -= var0;
                     var0 = anIntArray836[var0];

                     while(true) {
                        --var2;
                        if(var2 < 0) {
                           while(true) {
                              --var1;
                              if(var1 < 0) {
                                 return;
                              }

                              method1146(Class74.anIntArray1100, var0, var3 >> 16, var5 >> 16, var6, var19);
                              var5 += var15;
                              var3 += var16;
                              var6 += var20;
                              var0 += Class74.anInt1092;
                           }
                        }

                        method1146(Class74.anIntArray1100, var0, var3 >> 16, var4 >> 16, var6, var19);
                        var4 += var17;
                        var3 += var16;
                        var6 += var20;
                        var0 += Class74.anInt1092;
                     }
                  } else {
                     var1 -= var2;
                     var2 -= var0;
                     var0 = anIntArray836[var0];

                     while(true) {
                        --var2;
                        if(var2 < 0) {
                           while(true) {
                              --var1;
                              if(var1 < 0) {
                                 return;
                              }

                              method1146(Class74.anIntArray1100, var0, var5 >> 16, var3 >> 16, var6, var19);
                              var5 += var15;
                              var3 += var16;
                              var6 += var20;
                              var0 += Class74.anInt1092;
                           }
                        }

                        method1146(Class74.anIntArray1100, var0, var4 >> 16, var3 >> 16, var6, var19);
                        var4 += var17;
                        var3 += var16;
                        var6 += var20;
                        var0 += Class74.anInt1092;
                     }
                  }
               }
            }
         } else if(var1 <= var2) {
            if(var1 < anInt844) {
               if(var2 > anInt844) {
                  var2 = anInt844;
               }

               if(var0 > anInt844) {
                  var0 = anInt844;
               }

               var7 = (var7 << 8) - var19 * var4 + var19;
               if(var2 < var0) {
                  var3 = var4 <<= 16;
                  if(var1 < 0) {
                     var3 -= var16 * var1;
                     var4 -= var15 * var1;
                     var7 -= var20 * var1;
                     var1 = 0;
                  }

                  var5 <<= 16;
                  if(var2 < 0) {
                     var5 -= var17 * var2;
                     var2 = 0;
                  }

                  if((var1 == var2 || var16 >= var15) && (var1 != var2 || var16 <= var17)) {
                     var0 -= var2;
                     var2 -= var1;
                     var1 = anIntArray836[var1];

                     while(true) {
                        --var2;
                        if(var2 < 0) {
                           while(true) {
                              --var0;
                              if(var0 < 0) {
                                 return;
                              }

                              method1146(Class74.anIntArray1100, var1, var5 >> 16, var3 >> 16, var7, var19);
                              var3 += var16;
                              var5 += var17;
                              var7 += var20;
                              var1 += Class74.anInt1092;
                           }
                        }

                        method1146(Class74.anIntArray1100, var1, var4 >> 16, var3 >> 16, var7, var19);
                        var3 += var16;
                        var4 += var15;
                        var7 += var20;
                        var1 += Class74.anInt1092;
                     }
                  } else {
                     var0 -= var2;
                     var2 -= var1;
                     var1 = anIntArray836[var1];

                     while(true) {
                        --var2;
                        if(var2 < 0) {
                           while(true) {
                              --var0;
                              if(var0 < 0) {
                                 return;
                              }

                              method1146(Class74.anIntArray1100, var1, var3 >> 16, var5 >> 16, var7, var19);
                              var3 += var16;
                              var5 += var17;
                              var7 += var20;
                              var1 += Class74.anInt1092;
                           }
                        }

                        method1146(Class74.anIntArray1100, var1, var3 >> 16, var4 >> 16, var7, var19);
                        var3 += var16;
                        var4 += var15;
                        var7 += var20;
                        var1 += Class74.anInt1092;
                     }
                  }
               } else {
                  var5 = var4 <<= 16;
                  if(var1 < 0) {
                     var5 -= var16 * var1;
                     var4 -= var15 * var1;
                     var7 -= var20 * var1;
                     var1 = 0;
                  }

                  var3 <<= 16;
                  if(var0 < 0) {
                     var3 -= var17 * var0;
                     var0 = 0;
                  }

                  if(var16 < var15) {
                     var2 -= var0;
                     var0 -= var1;
                     var1 = anIntArray836[var1];

                     while(true) {
                        --var0;
                        if(var0 < 0) {
                           while(true) {
                              --var2;
                              if(var2 < 0) {
                                 return;
                              }

                              method1146(Class74.anIntArray1100, var1, var3 >> 16, var4 >> 16, var7, var19);
                              var3 += var17;
                              var4 += var15;
                              var7 += var20;
                              var1 += Class74.anInt1092;
                           }
                        }

                        method1146(Class74.anIntArray1100, var1, var5 >> 16, var4 >> 16, var7, var19);
                        var5 += var16;
                        var4 += var15;
                        var7 += var20;
                        var1 += Class74.anInt1092;
                     }
                  } else {
                     var2 -= var0;
                     var0 -= var1;
                     var1 = anIntArray836[var1];

                     while(true) {
                        --var0;
                        if(var0 < 0) {
                           while(true) {
                              --var2;
                              if(var2 < 0) {
                                 return;
                              }

                              method1146(Class74.anIntArray1100, var1, var4 >> 16, var3 >> 16, var7, var19);
                              var3 += var17;
                              var4 += var15;
                              var7 += var20;
                              var1 += Class74.anInt1092;
                           }
                        }

                        method1146(Class74.anIntArray1100, var1, var4 >> 16, var5 >> 16, var7, var19);
                        var5 += var16;
                        var4 += var15;
                        var7 += var20;
                        var1 += Class74.anInt1092;
                     }
                  }
               }
            }
         } else if(var2 < anInt844) {
            if(var0 > anInt844) {
               var0 = anInt844;
            }

            if(var1 > anInt844) {
               var1 = anInt844;
            }

            var8 = (var8 << 8) - var19 * var5 + var19;
            if(var0 < var1) {
               var4 = var5 <<= 16;
               if(var2 < 0) {
                  var4 -= var15 * var2;
                  var5 -= var17 * var2;
                  var8 -= var20 * var2;
                  var2 = 0;
               }

               var3 <<= 16;
               if(var0 < 0) {
                  var3 -= var16 * var0;
                  var0 = 0;
               }

               if(var15 < var17) {
                  var1 -= var0;
                  var0 -= var2;
                  var2 = anIntArray836[var2];

                  while(true) {
                     --var0;
                     if(var0 < 0) {
                        while(true) {
                           --var1;
                           if(var1 < 0) {
                              return;
                           }

                           method1146(Class74.anIntArray1100, var2, var4 >> 16, var3 >> 16, var8, var19);
                           var4 += var15;
                           var3 += var16;
                           var8 += var20;
                           var2 += Class74.anInt1092;
                        }
                     }

                     method1146(Class74.anIntArray1100, var2, var4 >> 16, var5 >> 16, var8, var19);
                     var4 += var15;
                     var5 += var17;
                     var8 += var20;
                     var2 += Class74.anInt1092;
                  }
               } else {
                  var1 -= var0;
                  var0 -= var2;
                  var2 = anIntArray836[var2];

                  while(true) {
                     --var0;
                     if(var0 < 0) {
                        while(true) {
                           --var1;
                           if(var1 < 0) {
                              return;
                           }

                           method1146(Class74.anIntArray1100, var2, var3 >> 16, var4 >> 16, var8, var19);
                           var4 += var15;
                           var3 += var16;
                           var8 += var20;
                           var2 += Class74.anInt1092;
                        }
                     }

                     method1146(Class74.anIntArray1100, var2, var5 >> 16, var4 >> 16, var8, var19);
                     var4 += var15;
                     var5 += var17;
                     var8 += var20;
                     var2 += Class74.anInt1092;
                  }
               }
            } else {
               var3 = var5 <<= 16;
               if(var2 < 0) {
                  var3 -= var15 * var2;
                  var5 -= var17 * var2;
                  var8 -= var20 * var2;
                  var2 = 0;
               }

               var4 <<= 16;
               if(var1 < 0) {
                  var4 -= var16 * var1;
                  var1 = 0;
               }

               if(var15 < var17) {
                  var0 -= var1;
                  var1 -= var2;
                  var2 = anIntArray836[var2];

                  while(true) {
                     --var1;
                     if(var1 < 0) {
                        while(true) {
                           --var0;
                           if(var0 < 0) {
                              return;
                           }

                           method1146(Class74.anIntArray1100, var2, var4 >> 16, var5 >> 16, var8, var19);
                           var4 += var16;
                           var5 += var17;
                           var8 += var20;
                           var2 += Class74.anInt1092;
                        }
                     }

                     method1146(Class74.anIntArray1100, var2, var3 >> 16, var5 >> 16, var8, var19);
                     var3 += var15;
                     var5 += var17;
                     var8 += var20;
                     var2 += Class74.anInt1092;
                  }
               } else {
                  var0 -= var1;
                  var1 -= var2;
                  var2 = anIntArray836[var2];

                  while(true) {
                     --var1;
                     if(var1 < 0) {
                        while(true) {
                           --var0;
                           if(var0 < 0) {
                              return;
                           }

                           method1146(Class74.anIntArray1100, var2, var5 >> 16, var4 >> 16, var8, var19);
                           var4 += var16;
                           var5 += var17;
                           var8 += var20;
                           var2 += Class74.anInt1092;
                        }
                     }

                     method1146(Class74.anIntArray1100, var2, var5 >> 16, var3 >> 16, var8, var19);
                     var3 += var15;
                     var5 += var17;
                     var8 += var20;
                     var2 += Class74.anInt1092;
                  }
               }
            }
         }
      }
   }

   public static void method1155() {
      anIntArray836 = null;
      anIntArray834 = null;
      anInterface2_838 = null;
      anIntArray839 = null;
      anIntArray841 = null;
      anIntArray840 = null;
      anIntArray851 = null;
   }

   private static void method1156(byte[] var0, int var1, int var3, int var4) {
      if(var3 < var4) {
         var1 += var3;
         int var2 = var4 - var3 >> 2;

         while(true) {
            --var2;
            if(var2 < 0) {
               var2 = var4 - var3 & 3;

               while(true) {
                  --var2;
                  if(var2 < 0) {
                     return;
                  }

                  var0[var1++] = 1;
               }
            }

            var0[var1++] = 1;
            var0[var1++] = 1;
            var0[var1++] = 1;
            var0[var1++] = 1;
         }
      }
   }

   static {
      int var0;
      for(var0 = 1; var0 < 512; ++var0) {
         anIntArray839[var0] = '\u8000' / var0;
      }

      for(var0 = 1; var0 < 2048; ++var0) {
         anIntArray841[var0] = 65536 / var0;
      }

      for(var0 = 0; var0 < 2048; ++var0) {
         anIntArray840[var0] = (int)(65536.0D * Math.sin((double)var0 * 0.0030679615D));
         anIntArray851[var0] = (int)(65536.0D * Math.cos((double)var0 * 0.0030679615D));
      }

   }
}
