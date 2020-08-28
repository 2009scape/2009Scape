package org.runite.jagex;

import java.util.Objects;

final class Class151_Sub1 extends Class151 {

   static Class3_Sub30_Sub1 aClass3_Sub30_Sub1_2942 = new Class3_Sub30_Sub1();
   private final Class41 aClass41_2943;
   private Class62 aClass62_2944;
   private final Class130 aClass130_2946 = new Class130(16);
   private final int anInt2947;
   private int anInt2948 = 0;
   private byte[] aByteArray2949;
   private Class3_Sub28_Sub10 aClass3_Sub28_Sub10_2950;
   static int[] anIntArray2952 = new int[128];
   private final Class66 aClass66_2953;
    Class41 aClass41_2954;
   private final int anInt2955;
   private final Class73 aClass73_2956;
   private final int anInt2957;
   static int anInt2958 = 0;
   private boolean aBoolean2962;
   private final Class61 aClass61_2963 = new Class61();
   private int anInt2964 = 0;
   private boolean aBoolean2965;
   private Class61 aClass61_2966;
   private long aLong2967 = 0L;
   private final boolean aBoolean2968;


   static void updateLocalPosition() {
      try {
         GraphicDefinition.incomingBuffer.setBitAccess((byte)118);
         int opcode = GraphicDefinition.incomingBuffer.getBits(1);
         if(opcode != 0) {
            int type = GraphicDefinition.incomingBuffer.getBits(2);
            if(0 == type) {
               Class21.maskUpdateIndexes[Class66.maskUpdateCount++] = 2047;
            } else {
               int var4;
               int var5;
               if(type == 1) { //Walk
                  var4 = GraphicDefinition.incomingBuffer.getBits(3);
                  Class102.player.walkStep(1, (byte)-128, var4);
                  var5 = GraphicDefinition.incomingBuffer.getBits(1);
                  if(var5 == 1) {
                     Class21.maskUpdateIndexes[Class66.maskUpdateCount++] = 2047;
                  }

               } else if(2 == type) {
                  if(GraphicDefinition.incomingBuffer.getBits(1) == 1) {
                     var4 = GraphicDefinition.incomingBuffer.getBits(3);
                     Class102.player.walkStep(2, (byte)-104, var4);
                     var5 = GraphicDefinition.incomingBuffer.getBits(3);
                     Class102.player.walkStep(2, (byte)-126, var5);
                  } else {
                     var4 = GraphicDefinition.incomingBuffer.getBits(3);
                     Class102.player.walkStep(0, (byte)-109, var4);
                  }

                  var4 = GraphicDefinition.incomingBuffer.getBits(1);
                  if(var4 == 1) {
                     Class21.maskUpdateIndexes[Class66.maskUpdateCount++] = 2047;
                  }

               } else if (type == 3) {
                  var4 = GraphicDefinition.incomingBuffer.getBits(7);
                  var5 = GraphicDefinition.incomingBuffer.getBits(1);
                  WorldListCountry.localPlane = GraphicDefinition.incomingBuffer.getBits(2);
                  int var6 = GraphicDefinition.incomingBuffer.getBits(1);
                  if (var6 == 1) {
                     Class21.maskUpdateIndexes[Class66.maskUpdateCount++] = 2047;
                  }

                  int var7 = GraphicDefinition.incomingBuffer.getBits(7);
                  Class102.player.method1981(var7, var5 == 1, var4);
               }
            }
         }
      } catch (RuntimeException var8) {
         throw Class44.clientError(var8, "bg.G(" + (byte) 81 + ')');
      }
   }

   final void method2095(int var1) {
      try {
         if(null != this.aClass41_2954) {
            Class3 var3;
            for(var3 = this.aClass61_2963.method1222(); null != var3; var3 = this.aClass61_2963.method1221()) {
               if((long) var1 == var3.aLong71) {
                  return;
               }
            }
            var3 = new Class3();
            var3.aLong71 = (long)var1;
            this.aClass61_2963.method1215(var3);
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "bg.H(" + var1 + ',' + 127 + ')');
      }
   }

   final Class62 method2094() {
      try {
         if(this.aClass62_2944 == null) {
            if(null == this.aClass3_Sub28_Sub10_2950) {
               if(this.aClass66_2953.method1251((byte)73)) {
                  return null;
               }

               this.aClass3_Sub28_Sub10_2950 = this.aClass66_2953.addJS5Request(115, 255, (byte)0, this.anInt2957, true);
            }

            if(this.aClass3_Sub28_Sub10_2950.aBoolean3632) {
               return null;
            } else {
               byte[] var2 = this.aClass3_Sub28_Sub10_2950.method587();
               if(this.aClass3_Sub28_Sub10_2950 instanceof Class3_Sub28_Sub10_Sub1) {
                  try {
                     if(var2 == null) {
                        throw new RuntimeException();
                     }

                     this.aClass62_2944 = new Class62(var2, this.anInt2955);
                     if(this.aClass62_2944.revision != this.anInt2947) {
                        throw new RuntimeException();
                     }
                  } catch (RuntimeException var4) {
                     this.aClass62_2944 = null;
                     if(this.aClass66_2953.method1251((byte)124)) {
                        this.aClass3_Sub28_Sub10_2950 = null;
                     } else {
                        this.aClass3_Sub28_Sub10_2950 = this.aClass66_2953.addJS5Request(-81, 255, (byte)0, this.anInt2957, true);
                     }

                     return null;
                  }
               } else {
                  try {
                     if(var2 == null) {
                        throw new RuntimeException();
                     }

                     this.aClass62_2944 = new Class62(var2, this.anInt2955);
                  } catch (RuntimeException var5) {
                     this.aClass66_2953.method1252((byte)-107);
                     this.aClass62_2944 = null;
                     if(this.aClass66_2953.method1251((byte) -71)) {
                        this.aClass3_Sub28_Sub10_2950 = null;
                     } else {
                        this.aClass3_Sub28_Sub10_2950 = this.aClass66_2953.addJS5Request(0 + 120, 255, (byte)0, this.anInt2957, true);
                     }

                     return null;
                  }

                  if(this.aClass41_2943 != null) {
                     this.aClass73_2956.method1305(this.aClass41_2943, var2, this.anInt2957);
                  }
               }

               if(null != this.aClass41_2954) {
                  this.aByteArray2949 = new byte[this.aClass62_2944.archiveAmount];
                  this.anInt2948 = 0;
               }

               this.aClass3_Sub28_Sub10_2950 = null;
               return this.aClass62_2944;
            }
         } else {
            return this.aClass62_2944;
         }
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "bg.B(" + 0 + ')');
      }
   }

   final void method2101() {
      try {
         if(this.aClass41_2954 != null) {
            this.aBoolean2965 = true;
            if(this.aClass61_2966 == null) {
               this.aClass61_2966 = new Class61();
            }

         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bg.A(" + true + ')');
      }
   }

   final int method2102() {
      try {

         return this.anInt2948;
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bg.I(" + 0 + ')');
      }
   }

   static boolean method2103(int var0, int var1) {
      try {
         return var1 >= -78 || (var0 == 198 || 230 == var0 || var0 == 156 || var0 == 140 || 223 == var0);
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bg.P(" + var0 + ',' + var1 + ')');
      }
   }

   static void method2104(RSInterface var0, boolean var1, int var2) {
      try {
         int var4 = var0.anInt240 != 0?var0.anInt240:var0.anInt168;
         int var5 = var0.anInt252 != 0 ?var0.anInt252:var0.anInt193;
         Class158.method2183(var0.anInt279, var1, var4, var5, GameObject.aClass11ArrayArray1834[var0.anInt279 >> 16]);
         if(var0.aClass11Array262 != null) {
            Class158.method2183(var0.anInt279, var1, var4, var5, var0.aClass11Array262);
         }

         Class3_Sub31 var6 = (Class3_Sub31)Class3_Sub13_Sub17.aClass130_3208.method1780((long)var0.anInt279, 0);
         if(var6 != null) {
            Class75_Sub4.method1352(var5, var1, var6.anInt2602, var4);
         }

      } catch (RuntimeException var7) {
         throw Class44.clientError(var7, "bg.N(" + (var0 != null?"{...}":"null") + ',' + var1 + ',' + var2 + ')');
      }
   }

   public static void method2105(boolean var0) {
      try {
         if(!var0) {
            anIntArray2952 = null;
            aClass3_Sub30_Sub1_2942 = null;
         }
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "bg.F(" + var0 + ')');
      }
   }

   final int method2106() {
      try {
         if(null == this.aClass62_2944) {
            return 0;
         } else if (this.aBoolean2962) {
            Class3 var2 = this.aClass61_2966.method1222();
            if (null == var2) {
               return 0;
            } else {

               return (int) var2.aLong71;
            }
         } else {
            return this.aClass62_2944.validArchiveAmount;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bg.O(" + 1 + ')');
      }
   }

   final void method2107() {
      try {
         if(null != this.aClass61_2966) {
            if(this.method2094() == null) {
               return;
            }

            boolean var2;
            Class3 var3;
            int var4;
            if(this.aBoolean2962) {
               var2 = true;

               for(var3 = this.aClass61_2966.method1222(); null != var3; var3 = this.aClass61_2966.method1221()) {
                  var4 = (int)var3.aLong71;
                  if(this.aByteArray2949[var4] == 0) {
                     this.method2109(1, var4, 51);
                  }

                  if(this.aByteArray2949[var4] == 0) {
                     var2 = false;
                  } else {
                     var3.method86(-1024);
                  }
               }

               while(this.aClass62_2944.archiveFileLengths.length > this.anInt2964) {
                  if (this.aClass62_2944.archiveFileLengths[this.anInt2964] != 0) {
                     if (this.aClass73_2956.anInt1087 >= 250) {
                        var2 = false;
                        break;
                     }

                     if (0 == this.aByteArray2949[this.anInt2964]) {
                        this.method2109(1, this.anInt2964, 99);
                     }

                     if (this.aByteArray2949[this.anInt2964] == 0) {
                        var2 = false;
                        var3 = new Class3();
                        var3.aLong71 = (long) this.anInt2964;
                        this.aClass61_2966.method1215(var3);
                     }

                  }
                  ++this.anInt2964;
               }

               if(var2) {
                  this.aBoolean2962 = false;
                  this.anInt2964 = 0;
               }
            } else if (this.aBoolean2965) {
               var2 = true;

               for (var3 = this.aClass61_2966.method1222(); var3 != null; var3 = this.aClass61_2966.method1221()) {
                  var4 = (int) var3.aLong71;
                  if (this.aByteArray2949[var4] != 1) {
                     this.method2109(2, var4, 96);
                  }

                  if (this.aByteArray2949[var4] == 1) {
                     var3.method86(-1024);
                  } else {
                     var2 = false;
                  }
               }

               while (this.anInt2964 < this.aClass62_2944.archiveFileLengths.length) {
                  if (this.aClass62_2944.archiveFileLengths[this.anInt2964] == 0) {
                     ++this.anInt2964;
                  } else {
                     if (this.aClass66_2953.method1241()) {
                        var2 = false;
                        break;
                     }

                     if (1 != this.aByteArray2949[this.anInt2964]) {
                        this.method2109(2, this.anInt2964, 47);
                     }

                     if (this.aByteArray2949[this.anInt2964] != 1) {
                        var3 = new Class3();
                        var3.aLong71 = (long) this.anInt2964;
                        this.aClass61_2966.method1215(var3);
                        var2 = false;
                     }

                     ++this.anInt2964;
                  }
               }

               if (var2) {
                  this.anInt2964 = 0;
                  this.aBoolean2965 = false;
               }
            } else {
               this.aClass61_2966 = null;
            }
         }

         if(this.aBoolean2968 && this.aLong2967 <= Class5.method830((byte)-55)) {
            for(Class3_Sub28_Sub10 var6 = (Class3_Sub28_Sub10)this.aClass130_2946.method1776(71); var6 != null; var6 = (Class3_Sub28_Sub10)this.aClass130_2946.method1778(-115)) {
               if(!var6.aBoolean3632) {
                  if(var6.aBoolean3635) {
                     if(!var6.aBoolean3628) {
                        throw new RuntimeException();
                     }

                     var6.method86(-1024);
                  } else {
                     var6.aBoolean3635 = true;
                  }
               }
            }

            this.aLong2967 = 1000L + Class5.method830((byte)-55);
         }

      } catch (RuntimeException var5) {
    	  var5.printStackTrace();
         throw Class44.clientError(var5, "bg.J(" + true + ')');
      }
   }

   final int method2097(int var1, int var2) {
      try {
         if(var2 != '\uffff') {
            this.anInt2964 = 25;
         }

         Class3_Sub28_Sub10 var3 = (Class3_Sub28_Sub10)this.aClass130_2946.method1780((long)var1, var2 + -65535);
         return null != var3?var3.method586():0;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "bg.L(" + var1 + ',' + var2 + ')');
      }
   }

   final int method2108() {
      try {
         if(this.aClass62_2944 == null) {
            return 0;
         } else {

            return this.aClass62_2944.validArchiveAmount;
         }
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bg.M(" + (byte) 1 + ')');
      }
   }

   private Class3_Sub28_Sub10 method2109(int var1, int archiveIndex, int var3) {
      try {
         Object var4 = (Class3_Sub28_Sub10)this.aClass130_2946.method1780((long)archiveIndex, 0);
         if(null != var4 && var1 == 0 && !((Class3_Sub28_Sub10)var4).aBoolean3628 && ((Class3_Sub28_Sub10)var4).aBoolean3632) {
            ((Class3_Sub28_Sub10)var4).method86(-1024);
            var4 = null;
         }

         if(null == var4) {
            if(0 == var1) {
               if(null == this.aClass41_2954 || this.aByteArray2949[archiveIndex] == -1) {
                  if(this.aClass66_2953.method1251((byte)-83)) {
                     return null;
                  }

                  var4 = this.aClass66_2953.addJS5Request(-51, this.anInt2957, (byte)2, archiveIndex, true);
               } else {
                  var4 = this.aClass73_2956.method1309(this.aClass41_2954, (byte)106, archiveIndex);
               }
            } else if(1 == var1) {
               if(this.aClass41_2954 == null) {
                  throw new RuntimeException();
               }

               var4 = this.aClass73_2956.method1307(archiveIndex, this.aClass41_2954);
            } else {
               if(var1 != 2) {
                  throw new RuntimeException();
               }

               if(this.aClass41_2954 == null) {
                  throw new RuntimeException();
               }

               if(this.aByteArray2949[archiveIndex] != -1) {
                  throw new RuntimeException();
               }

               if(this.aClass66_2953.method1241()) {
                  return null;
               }

               var4 = this.aClass66_2953.addJS5Request(-37, this.anInt2957, (byte)2, archiveIndex, false);
            }

            this.aClass130_2946.method1779((Class3)var4, (long)archiveIndex);
         }

         if(((Class3_Sub28_Sub10) Objects.requireNonNull(var4)).aBoolean3632) {
            return null;
         } else {
            byte[] var5 = ((Class3_Sub28_Sub10)var4).method587();
            int var7;
            Class3_Sub28_Sub10_Sub2 var12;
            if(var4 instanceof Class3_Sub28_Sub10_Sub1) {
               try {
                  if(var5 != null && var5.length > 2) {
                     Class3_Sub13_Sub12.aCRC32_3143.reset();
                     Class3_Sub13_Sub12.aCRC32_3143.update(var5, 0, -2 + var5.length);
                     var7 = (int)Class3_Sub13_Sub12.aCRC32_3143.getValue();
                     if(this.aClass62_2944.archiveCRCs[archiveIndex] == var7) {
                        int var8 = (var5[-2 + var5.length] << 8 & '\uff00') - -(255 & var5[-1 + var5.length]);
                        if(('\uffff' & this.aClass62_2944.archiveRevisions[archiveIndex]) == var8) {
                           if(1 != this.aByteArray2949[archiveIndex]) {

                              ++this.anInt2948;
                              this.aByteArray2949[archiveIndex] = 1;
                           }

                           if(!((Class3_Sub28_Sub10)var4).aBoolean3628) {
                              ((Class3_Sub28_Sub10)var4).method86(-1024);
                           }

                           return (Class3_Sub28_Sub10)var4;
                        } else {
                          	 System.err.println("CRC mismatch - [entry=" + this.aClass62_2944.archiveRevisions[archiveIndex] + ", pass=" + var8 + "]!");
                           throw new RuntimeException();
                        }
                     } else {
                       	 System.err.println("CRC mismatch - [entry=" + this.aClass62_2944.archiveCRCs[archiveIndex] + ", pass=" + var7 + "]!");
                         throw new RuntimeException();
                     }
                  } else {
//                      if(1 != this.aByteArray2949[var2]) {
//                          ++this.anInt2948;
//                          this.aByteArray2949[var2] = 1;
//                       }
//                       if(!((Class3_Sub28_Sub10)var4).aBoolean3628) {
//                          ((Class3_Sub28_Sub10)var4).method86(-1024);
//                       }

//                       return null;
                     throw new RuntimeException("Missing CRC for request " + ((archiveIndex >> 16) & 0xFF) + ", " + (archiveIndex & 0xFFFF));
                  }
               } catch (Exception var9) {
//            	   var9.printStackTrace();
                  this.aByteArray2949[archiveIndex] = -1;
                  ((Class3_Sub28_Sub10)var4).method86(-1024);
                  if(((Class3_Sub28_Sub10)var4).aBoolean3628 && !this.aClass66_2953.method1251((byte)-78)) {
                     var12 = this.aClass66_2953.addJS5Request(-13, this.anInt2957, (byte)2, archiveIndex, true);
                     this.aClass130_2946.method1779(var12, (long)archiveIndex);
                  }

                  return null;
               }
            } else {
               try {
                  if(null == var5 || var5.length <= 2) {
                	 System.err.println("Invalid CRC?");
                     throw new RuntimeException();
                  }

                  Class3_Sub13_Sub12.aCRC32_3143.reset();
                  Class3_Sub13_Sub12.aCRC32_3143.update(var5, 0, var5.length - 2);
                  var7 = (int)Class3_Sub13_Sub12.aCRC32_3143.getValue();
                  if(var7 != this.aClass62_2944.archiveCRCs[archiveIndex]) {
                	 System.err.println("CRC mismatch - [found=" + this.aClass62_2944.archiveCRCs[archiveIndex] + ", expected=" + var7 + "]!");
                     throw new RuntimeException();
                  }

                  this.aClass66_2953.anInt1011 = 0;
                  this.aClass66_2953.anInt1010 = 0;
               } catch (RuntimeException var10) {
            	  var10.printStackTrace();
                  this.aClass66_2953.method1252((byte)-67);
                  ((Class3_Sub28_Sub10)var4).method86(-1024);
                  if(((Class3_Sub28_Sub10)var4).aBoolean3628 && !this.aClass66_2953.method1251((byte)90)) {
                     var12 = this.aClass66_2953.addJS5Request(112, this.anInt2957, (byte)2, archiveIndex, true);
                     this.aClass130_2946.method1779(var12, (long)archiveIndex);
                  }

                  return null;
               }

               var5[var5.length + -2] = (byte)(this.aClass62_2944.archiveRevisions[archiveIndex] >>> 8);
               var5[var5.length - 1] = (byte)this.aClass62_2944.archiveRevisions[archiveIndex];
               if(null != this.aClass41_2954) {
                  this.aClass73_2956.method1305(this.aClass41_2954, var5, archiveIndex);
                  if(1 != this.aByteArray2949[archiveIndex]) {
                     ++this.anInt2948;
                     this.aByteArray2949[archiveIndex] = 1;
                  }
               }

               if(!((Class3_Sub28_Sub10)var4).aBoolean3628) {
                  ((Class3_Sub28_Sub10)var4).method86(-1024);
               }

               return (Class3_Sub28_Sub10)var4;
            }
         }
      } catch (RuntimeException var11) {
//    	  var11.printStackTrace();
         throw Class44.clientError(var11, "bg.C(" + var1 + ',' + archiveIndex + ',' + var3 + ')');
      }
   }

   final void method2110() {
      try {
         if(this.aClass61_2966 != null) {
            if(null != this.method2094()) {
               for(Class3 var2 = this.aClass61_2963.method1222(); null != var2; var2 = this.aClass61_2963.method1221()) {
                  int var3 = (int)var2.aLong71;
                  if(0 <= var3 && this.aClass62_2944.archiveAmount > var3 && this.aClass62_2944.archiveFileLengths[var3] != 0) {
                     if(this.aByteArray2949[var3] == 0) {
                        this.method2109(1, var3, 80);
                     }

                     if(-1 == this.aByteArray2949[var3]) {
                        this.method2109(2, var3, 78);
                     }

                     if(this.aByteArray2949[var3] == 1) {
                        var2.method86(-1024);
                     }
                  } else {
                     var2.method86(-1024);
                  }
               }

            }
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "bg.D(" + 0 + ')');
      }
   }

   final int method2111() {
      try {
         return null != this.method2094()?100:(null == this.aClass3_Sub28_Sub10_2950?0:this.aClass3_Sub28_Sub10_2950.method586());
      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "bg.E(" + -61 + ')');
      }
   }

   final byte[] method2098(int var1) {
      try {
         Class3_Sub28_Sub10 var3 = this.method2109(0, var1, 103);
         if(var3 == null) {
            return null;
         } else {
            byte[] var4 = var3.method587();
            var3.method86(-1024);
            return var4;
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "bg.K(" + var1 + ',' + 0 + ')');
      }
   }

   Class151_Sub1(int var1, Class41 var2, Class41 var3, Class66 var4, Class73 var5, int var6, int var7) {
      try {
         this.anInt2957 = var1;
         this.aClass41_2954 = var2;
         if(this.aClass41_2954 == null) {
            this.aBoolean2962 = false;
         } else {
            this.aBoolean2962 = true;
            this.aClass61_2966 = new Class61();
         }

         this.aClass73_2956 = var5;
         this.anInt2955 = var6;
         this.aBoolean2968 = true;
         this.aClass41_2943 = var3;
         this.aClass66_2953 = var4;
         this.anInt2947 = var7;
         if(null != this.aClass41_2943) {
            this.aClass3_Sub28_Sub10_2950 = this.aClass73_2956.method1309(this.aClass41_2943, (byte)113, this.anInt2957);
         }

      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "bg.<init>(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + (var3 != null?"{...}":"null") + ',' + (var4 != null?"{...}":"null") + ',' + (var5 != null?"{...}":"null") + ',' + var6 + ',' + var7 + ',' + true + ')');
      }
   }

}
