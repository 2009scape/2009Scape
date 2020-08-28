package org.runite.jagex;

final class Class3_Sub24_Sub1 extends Class3_Sub24 {

   private int anInt3470;
   private int anInt3471;
   private int anInt3472;
   private final int anInt3473;
   private int anInt3474;
   private int anInt3475;
   private int anInt3476;
   private final boolean aBoolean3477;
   private int anInt3478;
   private int anInt3479;
   private int anInt3480;
   private int anInt3481;
   private final int anInt3482;
   private int anInt3483;
   private int anInt3484;


   final synchronized void method413(int[] var1, int var2, int var3) {
      if(this.anInt3471 == 0 && this.anInt3480 == 0) {
         this.method415(var3);
      } else {
         Class3_Sub12_Sub1 var4 = (Class3_Sub12_Sub1)this.aClass3_Sub12_2544;
         int var5 = this.anInt3473 << 8;
         int var6 = this.anInt3482 << 8;
         int var7 = var4.aByteArray3030.length << 8;
         int var8 = var6 - var5;
         if(var8 <= 0) {
            this.anInt3479 = 0;
         }

         int var9 = var2;
         var3 += var2;
         if(this.anInt3474 < 0) {
            if(this.anInt3470 <= 0) {
               this.method439();
               this.method86(-1024);
               return;
            }

            this.anInt3474 = 0;
         }

         if(this.anInt3474 >= var7) {
            if(this.anInt3470 >= 0) {
               this.method439();
               this.method86(-1024);
               return;
            }

            this.anInt3474 = var7 - 1;
         }

         if(this.anInt3479 < 0) {
            if(this.aBoolean3477) {
               if(this.anInt3470 < 0) {
                  var9 = this.method436(var1, var2, var5, var3, var4.aByteArray3030[this.anInt3473]);
                  if(this.anInt3474 >= var5) {
                     return;
                  }

                  this.anInt3474 = var5 + var5 - 1 - this.anInt3474;
                  this.anInt3470 = -this.anInt3470;
               }

               while(true) {
                  var9 = this.method418(var1, var9, var6, var3, var4.aByteArray3030[this.anInt3482 - 1]);
                  if(this.anInt3474 < var6) {
                     return;
                  }

                  this.anInt3474 = var6 + var6 - 1 - this.anInt3474;
                  this.anInt3470 = -this.anInt3470;
                  var9 = this.method436(var1, var9, var5, var3, var4.aByteArray3030[this.anInt3473]);
                  if(this.anInt3474 >= var5) {
                     return;
                  }

                  this.anInt3474 = var5 + var5 - 1 - this.anInt3474;
                  this.anInt3470 = -this.anInt3470;
               }
            } else if(this.anInt3470 < 0) {
               while(true) {
                  var9 = this.method436(var1, var9, var5, var3, var4.aByteArray3030[this.anInt3482 - 1]);
                  if(this.anInt3474 >= var5) {
                     return;
                  }

                  this.anInt3474 = var6 - 1 - (var6 - 1 - this.anInt3474) % var8;
               }
            } else {
               while(true) {
                  var9 = this.method418(var1, var9, var6, var3, var4.aByteArray3030[this.anInt3473]);
                  if(this.anInt3474 < var6) {
                     return;
                  }

                  this.anInt3474 = var5 + (this.anInt3474 - var5) % var8;
               }
            }
         } else {
            if(this.anInt3479 > 0) {
               if(this.aBoolean3477) {
                  label134: {
                     if(this.anInt3470 < 0) {
                        var9 = this.method436(var1, var2, var5, var3, var4.aByteArray3030[this.anInt3473]);
                        if(this.anInt3474 >= var5) {
                           return;
                        }

                        this.anInt3474 = var5 + var5 - 1 - this.anInt3474;
                        this.anInt3470 = -this.anInt3470;
                        if(--this.anInt3479 == 0) {
                           break label134;
                        }
                     }

                     do {
                        var9 = this.method418(var1, var9, var6, var3, var4.aByteArray3030[this.anInt3482 - 1]);
                        if(this.anInt3474 < var6) {
                           return;
                        }

                        this.anInt3474 = var6 + var6 - 1 - this.anInt3474;
                        this.anInt3470 = -this.anInt3470;
                        if(--this.anInt3479 == 0) {
                           break;
                        }

                        var9 = this.method436(var1, var9, var5, var3, var4.aByteArray3030[this.anInt3473]);
                        if(this.anInt3474 >= var5) {
                           return;
                        }

                        this.anInt3474 = var5 + var5 - 1 - this.anInt3474;
                        this.anInt3470 = -this.anInt3470;
                     } while(--this.anInt3479 != 0);
                  }
               } else {
                  int var10;
                  if(this.anInt3470 < 0) {
                     while(true) {
                        var9 = this.method436(var1, var9, var5, var3, var4.aByteArray3030[this.anInt3482 - 1]);
                        if(this.anInt3474 >= var5) {
                           return;
                        }

                        var10 = (var6 - 1 - this.anInt3474) / var8;
                        if(var10 >= this.anInt3479) {
                           this.anInt3474 += var8 * this.anInt3479;
                           this.anInt3479 = 0;
                           break;
                        }

                        this.anInt3474 += var8 * var10;
                        this.anInt3479 -= var10;
                     }
                  } else {
                     while(true) {
                        var9 = this.method418(var1, var9, var6, var3, var4.aByteArray3030[this.anInt3473]);
                        if(this.anInt3474 < var6) {
                           return;
                        }

                        var10 = (this.anInt3474 - var5) / var8;
                        if(var10 >= this.anInt3479) {
                           this.anInt3474 -= var8 * this.anInt3479;
                           this.anInt3479 = 0;
                           break;
                        }

                        this.anInt3474 -= var8 * var10;
                        this.anInt3479 -= var10;
                     }
                  }
               }
            }

            if(this.anInt3470 < 0) {
               this.method436(var1, var9, 0, var3, 0);
               if(this.anInt3474 < 0) {
                  this.anInt3474 = -1;
                  this.method439();
                  this.method86(-1024);
               }
            } else {
               this.method418(var1, var9, var7, var3, 0);
               if(this.anInt3474 >= var7) {
                  this.anInt3474 = var7;
                  this.method439();
                  this.method86(-1024);
               }
            }

         }
      }
   }

   private boolean method416() {
      int var1 = this.anInt3471;
      int var2;
      int var3;
      if(var1 == Integer.MIN_VALUE) {
         var3 = 0;
         var2 = 0;
         var1 = 0;
      } else {
         var2 = method452(var1, this.anInt3478);
         var3 = method454(var1, this.anInt3478);
      }

      if(this.anInt3476 == var1 && this.anInt3484 == var2 && this.anInt3481 == var3) {
         if(this.anInt3471 == Integer.MIN_VALUE) {
            this.anInt3471 = 0;
            this.anInt3476 = this.anInt3484 = this.anInt3481 = 0;
            this.method86(-1024);
            return false;
         } else {
            this.method449();
            return true;
         }
      } else {
         if(this.anInt3476 < var1) {
            this.anInt3472 = 1;
            this.anInt3480 = var1 - this.anInt3476;
         } else if(this.anInt3476 > var1) {
            this.anInt3472 = -1;
            this.anInt3480 = this.anInt3476 - var1;
         } else {
            this.anInt3472 = 0;
         }

         if(this.anInt3484 < var2) {
            this.anInt3475 = 1;
            if(this.anInt3480 == 0 || this.anInt3480 > var2 - this.anInt3484) {
               this.anInt3480 = var2 - this.anInt3484;
            }
         } else if(this.anInt3484 > var2) {
            this.anInt3475 = -1;
            if(this.anInt3480 == 0 || this.anInt3480 > this.anInt3484 - var2) {
               this.anInt3480 = this.anInt3484 - var2;
            }
         } else {
            this.anInt3475 = 0;
         }

         if(this.anInt3481 < var3) {
            this.anInt3483 = 1;
            if(this.anInt3480 == 0 || this.anInt3480 > var3 - this.anInt3481) {
               this.anInt3480 = var3 - this.anInt3481;
            }
         } else if(this.anInt3481 > var3) {
            this.anInt3483 = -1;
            if(this.anInt3480 == 0 || this.anInt3480 > this.anInt3481 - var3) {
               this.anInt3480 = this.anInt3481 - var3;
            }
         } else {
            this.anInt3483 = 0;
         }

         return true;
      }
   }

   final synchronized void method417(int var1) {
      if(var1 == 0) {
         this.method430();
         this.method86(-1024);
      } else if(this.anInt3484 == 0 && this.anInt3481 == 0) {
         this.anInt3480 = 0;
         this.anInt3471 = 0;
         this.anInt3476 = 0;
         this.method86(-1024);
      } else {
         int var2 = -this.anInt3476;
         if(this.anInt3476 > var2) {
            var2 = this.anInt3476;
         }

         if(-this.anInt3484 > var2) {
            var2 = -this.anInt3484;
         }

         if(this.anInt3484 > var2) {
            var2 = this.anInt3484;
         }

         if(-this.anInt3481 > var2) {
            var2 = -this.anInt3481;
         }

         if(this.anInt3481 > var2) {
            var2 = this.anInt3481;
         }

         if(var1 > var2) {
            var1 = var2;
         }

         this.anInt3480 = var1;
         this.anInt3471 = Integer.MIN_VALUE;
         this.anInt3472 = -this.anInt3476 / var1;
         this.anInt3475 = -this.anInt3484 / var1;
         this.anInt3483 = -this.anInt3481 / var1;
      }
   }

   private int method418(int[] var1, int var2, int var3, int var4, int var5) {
      while(true) {
         if(this.anInt3480 > 0) {
            int var6 = var2 + this.anInt3480;
            if(var6 > var4) {
               var6 = var4;
            }

            this.anInt3480 += var2;
            if(this.anInt3470 == 256 && (this.anInt3474 & 255) == 0) {
               if(RSString.aBoolean2150) {
                  var2 = method426(((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030, var1, this.anInt3474, var2, this.anInt3484, this.anInt3481, this.anInt3475, this.anInt3483, var6, var3, this);
               } else {
                  var2 = method428(((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030, var1, this.anInt3474, var2, this.anInt3476, this.anInt3472, var6, var3, this);
               }
            } else if(RSString.aBoolean2150) {
               var2 = method421(((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030, var1, this.anInt3474, var2, this.anInt3484, this.anInt3481, this.anInt3475, this.anInt3483, var6, var3, this, this.anInt3470, var5);
            } else {
               var2 = method422(((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030, var1, this.anInt3474, var2, this.anInt3476, this.anInt3472, var6, var3, this, this.anInt3470, var5);
            }

            this.anInt3480 -= var2;
            if(this.anInt3480 != 0) {
               return var2;
            }

            if(this.method416()) {
               continue;
            }

            return var4;
         }

         if(this.anInt3470 == 256 && (this.anInt3474 & 255) == 0) {
            if(RSString.aBoolean2150) {
               return method420(((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030, var1, this.anInt3474, var2, this.anInt3484, this.anInt3481, var4, var3, this);
            }

            return method424(((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030, var1, this.anInt3474, var2, this.anInt3476, var4, var3, this);
         }

         if(RSString.aBoolean2150) {
            return method433(((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030, var1, this.anInt3474, var2, this.anInt3484, this.anInt3481, var4, var3, this, this.anInt3470, var5);
         }

         return method455(((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030, var1, this.anInt3474, var2, this.anInt3476, var4, var3, this, this.anInt3470, var5);
      }
   }

   final synchronized void method419(int var1) {
      this.method441(var1 << 6, this.method451());
   }

   final int method412() {
      int var1 = this.anInt3476 * 3 >> 6;
      var1 = (var1 ^ var1 >> 31) + (var1 >>> 31);
      if(this.anInt3479 == 0) {
         var1 -= var1 * this.anInt3474 / (((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030.length << 8);
      } else if(this.anInt3479 >= 0) {
         var1 -= var1 * this.anInt3473 / ((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030.length;
      }

      return var1 > 255?255:var1;
   }

   private static int method420(byte[] var1, int[] var2, int var3, int var4, int var5, int var6, int var8, int var9, Class3_Sub24_Sub1 var10) {
      var3 >>= 8;
      var9 >>= 8;
      var5 <<= 2;
      var6 <<= 2;
      int var7;
      if((var7 = var4 + var9 - var3) > var8) {
         var7 = var8;
      }

      var4 <<= 1;
      var7 <<= 1;

      byte var11;
      int var10001;
      for(var7 -= 6; var4 < var7; var2[var10001] += var11 * var6) {
         var11 = var1[var3++];
         var10001 = var4++;
         var2[var10001] += var11 * var5;
         var10001 = var4++;
         var2[var10001] += var11 * var6;
         var11 = var1[var3++];
         var10001 = var4++;
         var2[var10001] += var11 * var5;
         var10001 = var4++;
         var2[var10001] += var11 * var6;
         var11 = var1[var3++];
         var10001 = var4++;
         var2[var10001] += var11 * var5;
         var10001 = var4++;
         var2[var10001] += var11 * var6;
         var11 = var1[var3++];
         var10001 = var4++;
         var2[var10001] += var11 * var5;
         var10001 = var4++;
      }

      for(var7 += 6; var4 < var7; var2[var10001] += var11 * var6) {
         var11 = var1[var3++];
         var10001 = var4++;
         var2[var10001] += var11 * var5;
         var10001 = var4++;
      }

      var10.anInt3474 = var3 << 8;
      return var4 >> 1;
   }

   private static int method421(byte[] var2, int[] var3, int var4, int var5, int var6, int var7, int var8, int var9, int var11, int var12, Class3_Sub24_Sub1 var13, int var14, int var15) {
      var13.anInt3476 -= var13.anInt3472 * var5;
      int var10;
      if(var14 == 0 || (var10 = var5 + (var12 - var4 + var14 - 257) / var14) > var11) {
         var10 = var11;
      }

      var5 <<= 1;

      int var10001;
      byte var16;
      int var1;
      int var0;
      for(var10 <<= 1; var5 < var10; var4 += var14) {
         var1 = var4 >> 8;
         var16 = var2[var1];
         var0 = (var16 << 8) + (var2[var1 + 1] - var16) * (var4 & 255);
         var10001 = var5++;
         var3[var10001] += var0 * var6 >> 6;
         var6 += var8;
         var10001 = var5++;
         var3[var10001] += var0 * var7 >> 6;
         var7 += var9;
      }

      if(var14 == 0 || (var10 = (var5 >> 1) + (var12 - var4 + var14 - 1) / var14) > var11) {
         var10 = var11;
      }

      var10 <<= 1;

      for(var1 = var15; var5 < var10; var4 += var14) {
         var16 = var2[var4 >> 8];
         var0 = (var16 << 8) + (var1 - var16) * (var4 & 255);
         var10001 = var5++;
         var3[var10001] += var0 * var6 >> 6;
         var6 += var8;
         var10001 = var5++;
         var3[var10001] += var0 * var7 >> 6;
         var7 += var9;
      }

      var5 >>= 1;
      var13.anInt3476 += var13.anInt3472 * var5;
      var13.anInt3484 = var6;
      var13.anInt3481 = var7;
      var13.anInt3474 = var4;
      return var5;
   }

   final int method409() {
      return this.anInt3471 == 0 && this.anInt3480 == 0?0:1;
   }

   private static int method422(byte[] var2, int[] var3, int var4, int var5, int var6, int var7, int var9, int var10, Class3_Sub24_Sub1 var11, int var12, int var13) {
      var11.anInt3484 -= var11.anInt3475 * var5;
      var11.anInt3481 -= var11.anInt3483 * var5;
      int var8;
      if(var12 == 0 || (var8 = var5 + (var10 - var4 + var12 - 257) / var12) > var9) {
         var8 = var9;
      }

      int var10001;
      byte var14;
      int var1;
      while(var5 < var8) {
         var1 = var4 >> 8;
         var14 = var2[var1];
         var10001 = var5++;
         var3[var10001] += ((var14 << 8) + (var2[var1 + 1] - var14) * (var4 & 255)) * var6 >> 6;
         var6 += var7;
         var4 += var12;
      }

      if(var12 == 0 || (var8 = var5 + (var10 - var4 + var12 - 1) / var12) > var9) {
         var8 = var9;
      }

      for(var1 = var13; var5 < var8; var4 += var12) {
         var14 = var2[var4 >> 8];
         var10001 = var5++;
         var3[var10001] += ((var14 << 8) + (var1 - var14) * (var4 & 255)) * var6 >> 6;
         var6 += var7;
      }

      var11.anInt3484 += var11.anInt3475 * var5;
      var11.anInt3481 += var11.anInt3483 * var5;
      var11.anInt3476 = var6;
      var11.anInt3474 = var4;
      return var5;
   }

   private static int method423(byte[] var2, int[] var3, int var4, int var5, int var6, int var8, int var9, Class3_Sub24_Sub1 var10, int var11, int var12) {
      int var7;
      if(var11 == 0 || (var7 = var5 + (var9 + 256 - var4 + var11) / var11) > var8) {
         var7 = var8;
      }

      int var10001;
      int var1;
      while(var5 < var7) {
         var1 = var4 >> 8;
         byte var13 = var2[var1 - 1];
         var10001 = var5++;
         var3[var10001] += ((var13 << 8) + (var2[var1] - var13) * (var4 & 255)) * var6 >> 6;
         var4 += var11;
      }

      if(var11 == 0 || (var7 = var5 + (var9 - var4 + var11) / var11) > var8) {
         var7 = var8;
      }

      for(var1 = var11; var5 < var7; var4 += var1) {
         var10001 = var5++;
         var3[var10001] += ((var12 << 8) + (var2[var4 >> 8] - var12) * (var4 & 255)) * var6 >> 6;
      }

      var10.anInt3474 = var4;
      return var5;
   }

   private static int method424(byte[] var0, int[] var1, int var2, int var3, int var4, int var6, int var7, Class3_Sub24_Sub1 var8) {
      var2 >>= 8;
      var7 >>= 8;
      var4 <<= 2;
      int var5;
      if((var5 = var3 + var7 - var2) > var6) {
         var5 = var6;
      }

      int var10001;
      for(var5 -= 3; var3 < var5; var1[var10001] += var0[var2++] * var4) {
         var10001 = var3++;
         var1[var10001] += var0[var2++] * var4;
         var10001 = var3++;
         var1[var10001] += var0[var2++] * var4;
         var10001 = var3++;
         var1[var10001] += var0[var2++] * var4;
         var10001 = var3++;
      }

      for(var5 += 3; var3 < var5; var1[var10001] += var0[var2++] * var4) {
         var10001 = var3++;
      }

      var8.anInt3474 = var2 << 8;
      return var3;
   }

   final synchronized int method425() {
      return this.anInt3471 == Integer.MIN_VALUE?0:this.anInt3471;
   }

   private static int method426(byte[] var1, int[] var2, int var3, int var4, int var5, int var6, int var7, int var8, int var10, int var11, Class3_Sub24_Sub1 var12) {
      var3 >>= 8;
      var11 >>= 8;
      var5 <<= 2;
      var6 <<= 2;
      var7 <<= 2;
      var8 <<= 2;
      int var9;
      if((var9 = var4 + var11 - var3) > var10) {
         var9 = var10;
      }

      var12.anInt3476 += var12.anInt3472 * (var9 - var4);
      var4 <<= 1;
      var9 <<= 1;

      int var10001;
      byte var13;
      for(var9 -= 6; var4 < var9; var6 += var8) {
         var13 = var1[var3++];
         var10001 = var4++;
         var2[var10001] += var13 * var5;
         var5 += var7;
         var10001 = var4++;
         var2[var10001] += var13 * var6;
         var6 += var8;
         var13 = var1[var3++];
         var10001 = var4++;
         var2[var10001] += var13 * var5;
         var5 += var7;
         var10001 = var4++;
         var2[var10001] += var13 * var6;
         var6 += var8;
         var13 = var1[var3++];
         var10001 = var4++;
         var2[var10001] += var13 * var5;
         var5 += var7;
         var10001 = var4++;
         var2[var10001] += var13 * var6;
         var6 += var8;
         var13 = var1[var3++];
         var10001 = var4++;
         var2[var10001] += var13 * var5;
         var5 += var7;
         var10001 = var4++;
         var2[var10001] += var13 * var6;
      }

      for(var9 += 6; var4 < var9; var6 += var8) {
         var13 = var1[var3++];
         var10001 = var4++;
         var2[var10001] += var13 * var5;
         var5 += var7;
         var10001 = var4++;
         var2[var10001] += var13 * var6;
      }

      var12.anInt3484 = var5 >> 2;
      var12.anInt3481 = var6 >> 2;
      var12.anInt3474 = var3 << 8;
      return var4 >> 1;
   }

   private static int method427(byte[] var0, int[] var1, int var2, int var3, int var4, int var5, int var7, int var8, Class3_Sub24_Sub1 var9) {
      var2 >>= 8;
      var8 >>= 8;
      var4 <<= 2;
      var5 <<= 2;
      int var6;
      if((var6 = var3 + var2 - (var8 - 1)) > var7) {
         var6 = var7;
      }

      var9.anInt3484 += var9.anInt3475 * (var6 - var3);
      var9.anInt3481 += var9.anInt3483 * (var6 - var3);

      int var10001;
      for(var6 -= 3; var3 < var6; var4 += var5) {
         var10001 = var3++;
         var1[var10001] += var0[var2--] * var4;
         var4 += var5;
         var10001 = var3++;
         var1[var10001] += var0[var2--] * var4;
         var4 += var5;
         var10001 = var3++;
         var1[var10001] += var0[var2--] * var4;
         var4 += var5;
         var10001 = var3++;
         var1[var10001] += var0[var2--] * var4;
      }

      for(var6 += 3; var3 < var6; var4 += var5) {
         var10001 = var3++;
         var1[var10001] += var0[var2--] * var4;
      }

      var9.anInt3476 = var4 >> 2;
      var9.anInt3474 = var2 << 8;
      return var3;
   }

   private static int method428(byte[] var0, int[] var1, int var2, int var3, int var4, int var5, int var7, int var8, Class3_Sub24_Sub1 var9) {
      var2 >>= 8;
      var8 >>= 8;
      var4 <<= 2;
      var5 <<= 2;
      int var6;
      if((var6 = var3 + var8 - var2) > var7) {
         var6 = var7;
      }

      var9.anInt3484 += var9.anInt3475 * (var6 - var3);
      var9.anInt3481 += var9.anInt3483 * (var6 - var3);

      int var10001;
      for(var6 -= 3; var3 < var6; var4 += var5) {
         var10001 = var3++;
         var1[var10001] += var0[var2++] * var4;
         var4 += var5;
         var10001 = var3++;
         var1[var10001] += var0[var2++] * var4;
         var4 += var5;
         var10001 = var3++;
         var1[var10001] += var0[var2++] * var4;
         var4 += var5;
         var10001 = var3++;
         var1[var10001] += var0[var2++] * var4;
      }

      for(var6 += 3; var3 < var6; var4 += var5) {
         var10001 = var3++;
         var1[var10001] += var0[var2++] * var4;
      }

      var9.anInt3476 = var4 >> 2;
      var9.anInt3474 = var2 << 8;
      return var3;
   }

   final synchronized void method415(int var1) {
      if(this.anInt3480 > 0) {
         if(var1 >= this.anInt3480) {
            if(this.anInt3471 == Integer.MIN_VALUE) {
               this.anInt3471 = 0;
               this.anInt3476 = this.anInt3484 = this.anInt3481 = 0;
               this.method86(-1024);
               var1 = this.anInt3480;
            }

            this.anInt3480 = 0;
            this.method449();
         } else {
            this.anInt3476 += this.anInt3472 * var1;
            this.anInt3484 += this.anInt3475 * var1;
            this.anInt3481 += this.anInt3483 * var1;
            this.anInt3480 -= var1;
         }
      }

      Class3_Sub12_Sub1 var2 = (Class3_Sub12_Sub1)this.aClass3_Sub12_2544;
      int var3 = this.anInt3473 << 8;
      int var4 = this.anInt3482 << 8;
      int var5 = var2.aByteArray3030.length << 8;
      int var6 = var4 - var3;
      if(var6 <= 0) {
         this.anInt3479 = 0;
      }

      if(this.anInt3474 < 0) {
         if(this.anInt3470 <= 0) {
            this.method439();
            this.method86(-1024);
            return;
         }

         this.anInt3474 = 0;
      }

      if(this.anInt3474 >= var5) {
         if(this.anInt3470 >= 0) {
            this.method439();
            this.method86(-1024);
            return;
         }

         this.anInt3474 = var5 - 1;
      }

      this.anInt3474 += this.anInt3470 * var1;
      if(this.anInt3479 < 0) {
         if(this.aBoolean3477) {
            if(this.anInt3470 < 0) {
               if(this.anInt3474 >= var3) {
                  return;
               }

               this.anInt3474 = var3 + var3 - 1 - this.anInt3474;
               this.anInt3470 = -this.anInt3470;
            }

            while(this.anInt3474 >= var4) {
               this.anInt3474 = var4 + var4 - 1 - this.anInt3474;
               this.anInt3470 = -this.anInt3470;
               if(this.anInt3474 >= var3) {
                  return;
               }

               this.anInt3474 = var3 + var3 - 1 - this.anInt3474;
               this.anInt3470 = -this.anInt3470;
            }

         } else if (this.anInt3470 < 0) {
             if (this.anInt3474 >= var3) {
                 return;
             }

             this.anInt3474 = var4 - 1 - (var4 - 1 - this.anInt3474) % var6;
         } else {
             if (this.anInt3474 < var4) {
                 return;
             }

             this.anInt3474 = var3 + (this.anInt3474 - var3) % var6;
         }
      } else {
         if(this.anInt3479 > 0) {
            if(this.aBoolean3477) {
               label122: {
                  if(this.anInt3470 < 0) {
                     if(this.anInt3474 >= var3) {
                        return;
                     }

                     this.anInt3474 = var3 + var3 - 1 - this.anInt3474;
                     this.anInt3470 = -this.anInt3470;
                     if(--this.anInt3479 == 0) {
                        break label122;
                     }
                  }

                  do {
                     if(this.anInt3474 < var4) {
                        return;
                     }

                     this.anInt3474 = var4 + var4 - 1 - this.anInt3474;
                     this.anInt3470 = -this.anInt3470;
                     if(--this.anInt3479 == 0) {
                        break;
                     }

                     if(this.anInt3474 >= var3) {
                        return;
                     }

                     this.anInt3474 = var3 + var3 - 1 - this.anInt3474;
                     this.anInt3470 = -this.anInt3470;
                  } while(--this.anInt3479 != 0);
               }
            } else {
               label132: {
                  int var7;
                  if(this.anInt3470 < 0) {
                     if(this.anInt3474 >= var3) {
                        return;
                     }

                     var7 = (var4 - 1 - this.anInt3474) / var6;
                     if(var7 >= this.anInt3479) {
                        this.anInt3474 += var6 * this.anInt3479;
                        this.anInt3479 = 0;
                        break label132;
                     }

                     this.anInt3474 += var6 * var7;
                  } else {
                     if(this.anInt3474 < var4) {
                        return;
                     }

                     var7 = (this.anInt3474 - var3) / var6;
                     if(var7 >= this.anInt3479) {
                        this.anInt3474 -= var6 * this.anInt3479;
                        this.anInt3479 = 0;
                        break label132;
                     }

                     this.anInt3474 -= var6 * var7;
                  }
                  this.anInt3479 -= var7;

                  return;
               }
            }
         }

         if(this.anInt3470 < 0) {
            if(this.anInt3474 < 0) {
               this.anInt3474 = -1;
               this.method439();
               this.method86(-1024);
            }
         } else if(this.anInt3474 >= var5) {
            this.anInt3474 = var5;
            this.method439();
            this.method86(-1024);
         }

      }
   }

   final synchronized void method429(int var1) {
      this.anInt3479 = var1;
   }

   private synchronized void method430() {
      this.method441(0, this.method451());
   }

   final synchronized void method431(int var1, int var2) {
      this.method450(var1, var2, this.method451());
   }

   static Class3_Sub24_Sub1 method432(Class3_Sub12_Sub1 var0, int var1, int var2, int var3) {
      return var0.aByteArray3030 != null && var0.aByteArray3030.length != 0?new Class3_Sub24_Sub1(var0, var1, var2, var3):null;
   }

   final Class3_Sub24 method411() {
      return null;
   }

   private static int method433(byte[] var2, int[] var3, int var4, int var5, int var6, int var7, int var9, int var10, Class3_Sub24_Sub1 var11, int var12, int var13) {
      int var8;
      if(var12 == 0 || (var8 = var5 + (var10 - var4 + var12 - 257) / var12) > var9) {
         var8 = var9;
      }

      var5 <<= 1;

      int var10001;
      byte var14;
      int var1;
      int var0;
      for(var8 <<= 1; var5 < var8; var4 += var12) {
         var1 = var4 >> 8;
         var14 = var2[var1];
         var0 = (var14 << 8) + (var2[var1 + 1] - var14) * (var4 & 255);
         var10001 = var5++;
         var3[var10001] += var0 * var6 >> 6;
         var10001 = var5++;
         var3[var10001] += var0 * var7 >> 6;
      }

      if(var12 == 0 || (var8 = (var5 >> 1) + (var10 - var4 + var12 - 1) / var12) > var9) {
         var8 = var9;
      }

      var8 <<= 1;

      for(var1 = var13; var5 < var8; var4 += var12) {
         var14 = var2[var4 >> 8];
         var0 = (var14 << 8) + (var1 - var14) * (var4 & 255);
         var10001 = var5++;
         var3[var10001] += var0 * var6 >> 6;
         var10001 = var5++;
         var3[var10001] += var0 * var7 >> 6;
      }

      var11.anInt3474 = var4;
      return var5 >> 1;
   }

   final synchronized void method434(int var1) {
      int var2 = ((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030.length << 8;
      if(var1 < -1) {
         var1 = -1;
      }

      if(var1 > var2) {
         var1 = var2;
      }

      this.anInt3474 = var1;
   }

   private static int method435(byte[] var1, int[] var2, int var3, int var4, int var5, int var6, int var7, int var8, int var10, int var11, Class3_Sub24_Sub1 var12) {
      var3 >>= 8;
      var11 >>= 8;
      var5 <<= 2;
      var6 <<= 2;
      var7 <<= 2;
      var8 <<= 2;
      int var9;
      if((var9 = var4 + var3 - (var11 - 1)) > var10) {
         var9 = var10;
      }

      var12.anInt3476 += var12.anInt3472 * (var9 - var4);
      var4 <<= 1;
      var9 <<= 1;

      int var10001;
      byte var13;
      for(var9 -= 6; var4 < var9; var6 += var8) {
         var13 = var1[var3--];
         var10001 = var4++;
         var2[var10001] += var13 * var5;
         var5 += var7;
         var10001 = var4++;
         var2[var10001] += var13 * var6;
         var6 += var8;
         var13 = var1[var3--];
         var10001 = var4++;
         var2[var10001] += var13 * var5;
         var5 += var7;
         var10001 = var4++;
         var2[var10001] += var13 * var6;
         var6 += var8;
         var13 = var1[var3--];
         var10001 = var4++;
         var2[var10001] += var13 * var5;
         var5 += var7;
         var10001 = var4++;
         var2[var10001] += var13 * var6;
         var6 += var8;
         var13 = var1[var3--];
         var10001 = var4++;
         var2[var10001] += var13 * var5;
         var5 += var7;
         var10001 = var4++;
         var2[var10001] += var13 * var6;
      }

      for(var9 += 6; var4 < var9; var6 += var8) {
         var13 = var1[var3--];
         var10001 = var4++;
         var2[var10001] += var13 * var5;
         var5 += var7;
         var10001 = var4++;
         var2[var10001] += var13 * var6;
      }

      var12.anInt3484 = var5 >> 2;
      var12.anInt3481 = var6 >> 2;
      var12.anInt3474 = var3 << 8;
      return var4 >> 1;
   }

   private int method436(int[] var1, int var2, int var3, int var4, int var5) {
      while(true) {
         if(this.anInt3480 > 0) {
            int var6 = var2 + this.anInt3480;
            if(var6 > var4) {
               var6 = var4;
            }

            this.anInt3480 += var2;
            if(this.anInt3470 == -256 && (this.anInt3474 & 255) == 0) {
               if(RSString.aBoolean2150) {
                  var2 = method435(((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030, var1, this.anInt3474, var2, this.anInt3484, this.anInt3481, this.anInt3475, this.anInt3483, var6, var3, this);
               } else {
                  var2 = method427(((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030, var1, this.anInt3474, var2, this.anInt3476, this.anInt3472, var6, var3, this);
               }
            } else if(RSString.aBoolean2150) {
               var2 = method440(((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030, var1, this.anInt3474, var2, this.anInt3484, this.anInt3481, this.anInt3475, this.anInt3483, var6, var3, this, this.anInt3470, var5);
            } else {
               var2 = method448(((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030, var1, this.anInt3474, var2, this.anInt3476, this.anInt3472, var6, var3, this, this.anInt3470, var5);
            }

            this.anInt3480 -= var2;
            if(this.anInt3480 != 0) {
               return var2;
            }

            if(this.method416()) {
               continue;
            }

            return var4;
         }

         if(this.anInt3470 == -256 && (this.anInt3474 & 255) == 0) {
            if(RSString.aBoolean2150) {
               return method447(((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030, var1, this.anInt3474, var2, this.anInt3484, this.anInt3481, var4, var3, this);
            }

            return method446(((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030, var1, this.anInt3474, var2, this.anInt3476, var4, var3, this);
         }

         if(RSString.aBoolean2150) {
            return method453(((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030, var1, this.anInt3474, var2, this.anInt3484, this.anInt3481, var4, var3, this, this.anInt3470, var5);
         }

         return method423(((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030, var1, this.anInt3474, var2, this.anInt3476, var4, var3, this, this.anInt3470, var5);
      }
   }

   static Class3_Sub24_Sub1 method437(Class3_Sub12_Sub1 var0, int var2) {
      return var0.aByteArray3030 != null && var0.aByteArray3030.length != 0?new Class3_Sub24_Sub1(var0, (int)((long)var0.anInt3034 * 256L * (long) 100 / (long)(100 * Class21.anInt443)), var2 << 6):null;
   }

   final synchronized int method438() {
      return this.anInt3470 < 0?-this.anInt3470:this.anInt3470;
   }

   private void method439() {
      if(this.anInt3480 != 0) {
         if(this.anInt3471 == Integer.MIN_VALUE) {
            this.anInt3471 = 0;
         }

         this.anInt3480 = 0;
         this.method449();
      }

   }

   private static int method440(byte[] var2, int[] var3, int var4, int var5, int var6, int var7, int var8, int var9, int var11, int var12, Class3_Sub24_Sub1 var13, int var14, int var15) {
      var13.anInt3476 -= var13.anInt3472 * var5;
      int var10;
      if(var14 == 0 || (var10 = var5 + (var12 + 256 - var4 + var14) / var14) > var11) {
         var10 = var11;
      }

      var5 <<= 1;

      int var10001;
      int var1;
      int var0;
      for(var10 <<= 1; var5 < var10; var4 += var14) {
         var1 = var4 >> 8;
         byte var16 = var2[var1 - 1];
         var0 = (var16 << 8) + (var2[var1] - var16) * (var4 & 255);
         var10001 = var5++;
         var3[var10001] += var0 * var6 >> 6;
         var6 += var8;
         var10001 = var5++;
         var3[var10001] += var0 * var7 >> 6;
         var7 += var9;
      }

      if(var14 == 0 || (var10 = (var5 >> 1) + (var12 - var4 + var14) / var14) > var11) {
         var10 = var11;
      }

      var10 <<= 1;

      for(var1 = var15; var5 < var10; var4 += var14) {
         var0 = (var1 << 8) + (var2[var4 >> 8] - var1) * (var4 & 255);
         var10001 = var5++;
         var3[var10001] += var0 * var6 >> 6;
         var6 += var8;
         var10001 = var5++;
         var3[var10001] += var0 * var7 >> 6;
         var7 += var9;
      }

      var5 >>= 1;
      var13.anInt3476 += var13.anInt3472 * var5;
      var13.anInt3484 = var6;
      var13.anInt3481 = var7;
      var13.anInt3474 = var4;
      return var5;
   }

   private synchronized void method441(int var1, int var2) {
      this.anInt3471 = var1;
      this.anInt3478 = var2;
      this.anInt3480 = 0;
      this.method449();
   }

   final synchronized void method442() {
      this.anInt3470 = (this.anInt3470 ^ this.anInt3470 >> 31) + (this.anInt3470 >>> 31);
       this.anInt3470 = -this.anInt3470;

   }

   final synchronized void method443(int var1) {
      if(this.anInt3470 < 0) {
         this.anInt3470 = -var1;
      } else {
         this.anInt3470 = var1;
      }

   }

   final boolean method444() {
      return this.anInt3474 < 0 || this.anInt3474 >= ((Class3_Sub12_Sub1)this.aClass3_Sub12_2544).aByteArray3030.length << 8;
   }

   final boolean method445() {
      return this.anInt3480 != 0;
   }

   private static int method446(byte[] var0, int[] var1, int var2, int var3, int var4, int var6, int var7, Class3_Sub24_Sub1 var8) {
      var2 >>= 8;
      var7 >>= 8;
      var4 <<= 2;
      int var5;
      if((var5 = var3 + var2 - (var7 - 1)) > var6) {
         var5 = var6;
      }

      int var10001;
      for(var5 -= 3; var3 < var5; var1[var10001] += var0[var2--] * var4) {
         var10001 = var3++;
         var1[var10001] += var0[var2--] * var4;
         var10001 = var3++;
         var1[var10001] += var0[var2--] * var4;
         var10001 = var3++;
         var1[var10001] += var0[var2--] * var4;
         var10001 = var3++;
      }

      for(var5 += 3; var3 < var5; var1[var10001] += var0[var2--] * var4) {
         var10001 = var3++;
      }

      var8.anInt3474 = var2 << 8;
      return var3;
   }

   private static int method447(byte[] var1, int[] var2, int var3, int var4, int var5, int var6, int var8, int var9, Class3_Sub24_Sub1 var10) {
      var3 >>= 8;
      var9 >>= 8;
      var5 <<= 2;
      var6 <<= 2;
      int var7;
      if((var7 = var4 + var3 - (var9 - 1)) > var8) {
         var7 = var8;
      }

      var4 <<= 1;
      var7 <<= 1;

      byte var11;
      int var10001;
      for(var7 -= 6; var4 < var7; var2[var10001] += var11 * var6) {
         var11 = var1[var3--];
         var10001 = var4++;
         var2[var10001] += var11 * var5;
         var10001 = var4++;
         var2[var10001] += var11 * var6;
         var11 = var1[var3--];
         var10001 = var4++;
         var2[var10001] += var11 * var5;
         var10001 = var4++;
         var2[var10001] += var11 * var6;
         var11 = var1[var3--];
         var10001 = var4++;
         var2[var10001] += var11 * var5;
         var10001 = var4++;
         var2[var10001] += var11 * var6;
         var11 = var1[var3--];
         var10001 = var4++;
         var2[var10001] += var11 * var5;
         var10001 = var4++;
      }

      for(var7 += 6; var4 < var7; var2[var10001] += var11 * var6) {
         var11 = var1[var3--];
         var10001 = var4++;
         var2[var10001] += var11 * var5;
         var10001 = var4++;
      }

      var10.anInt3474 = var3 << 8;
      return var4 >> 1;
   }

   private static int method448(byte[] var2, int[] var3, int var4, int var5, int var6, int var7, int var9, int var10, Class3_Sub24_Sub1 var11, int var12, int var13) {
      var11.anInt3484 -= var11.anInt3475 * var5;
      var11.anInt3481 -= var11.anInt3483 * var5;
      int var8;
      if(var12 == 0 || (var8 = var5 + (var10 + 256 - var4 + var12) / var12) > var9) {
         var8 = var9;
      }

      int var10001;
      int var1;
      while(var5 < var8) {
         var1 = var4 >> 8;
         byte var14 = var2[var1 - 1];
         var10001 = var5++;
         var3[var10001] += ((var14 << 8) + (var2[var1] - var14) * (var4 & 255)) * var6 >> 6;
         var6 += var7;
         var4 += var12;
      }

      if(var12 == 0 || (var8 = var5 + (var10 - var4 + var12) / var12) > var9) {
         var8 = var9;
      }

      for(var1 = var12; var5 < var8; var4 += var1) {
         var10001 = var5++;
         var3[var10001] += ((var13 << 8) + (var2[var4 >> 8] - var13) * (var4 & 255)) * var6 >> 6;
         var6 += var7;
      }

      var11.anInt3484 += var11.anInt3475 * var5;
      var11.anInt3481 += var11.anInt3483 * var5;
      var11.anInt3476 = var6;
      var11.anInt3474 = var4;
      return var5;
   }

   private void method449() {
      this.anInt3476 = this.anInt3471;
      this.anInt3484 = method452(this.anInt3471, this.anInt3478);
      this.anInt3481 = method454(this.anInt3471, this.anInt3478);
   }

   final synchronized void method450(int var1, int var2, int var3) {
      if(var1 == 0) {
         this.method441(var2, var3);
      } else {
         int var4 = method452(var2, var3);
         int var5 = method454(var2, var3);
         if(this.anInt3484 == var4 && this.anInt3481 == var5) {
            this.anInt3480 = 0;
         } else {
            int var6 = var2 - this.anInt3476;
            if(this.anInt3476 - var2 > var6) {
               var6 = this.anInt3476 - var2;
            }

            if(var4 - this.anInt3484 > var6) {
               var6 = var4 - this.anInt3484;
            }

            if(this.anInt3484 - var4 > var6) {
               var6 = this.anInt3484 - var4;
            }

            if(var5 - this.anInt3481 > var6) {
               var6 = var5 - this.anInt3481;
            }

            if(this.anInt3481 - var5 > var6) {
               var6 = this.anInt3481 - var5;
            }

            if(var1 > var6) {
               var1 = var6;
            }

            this.anInt3480 = var1;
            this.anInt3471 = var2;
            this.anInt3478 = var3;
            this.anInt3472 = (var2 - this.anInt3476) / var1;
            this.anInt3475 = (var4 - this.anInt3484) / var1;
            this.anInt3483 = (var5 - this.anInt3481) / var1;
         }
      }
   }

   final synchronized int method451() {
      return this.anInt3478 < 0?-1:this.anInt3478;
   }

   private static int method452(int var0, int var1) {
      return var1 < 0?var0:(int)((double)var0 * Math.sqrt((double)(16384 - var1) * 1.220703125E-4D) + 0.5D);
   }

   private static int method453(byte[] var2, int[] var3, int var4, int var5, int var6, int var7, int var9, int var10, Class3_Sub24_Sub1 var11, int var12, int var13) {
      int var8;
      if(var12 == 0 || (var8 = var5 + (var10 + 256 - var4 + var12) / var12) > var9) {
         var8 = var9;
      }

      var5 <<= 1;

      int var10001;
      int var1;
      int var0;
      for(var8 <<= 1; var5 < var8; var4 += var12) {
         var1 = var4 >> 8;
         byte var14 = var2[var1 - 1];
         var0 = (var14 << 8) + (var2[var1] - var14) * (var4 & 255);
         var10001 = var5++;
         var3[var10001] += var0 * var6 >> 6;
         var10001 = var5++;
         var3[var10001] += var0 * var7 >> 6;
      }

      if(var12 == 0 || (var8 = (var5 >> 1) + (var10 - var4 + var12) / var12) > var9) {
         var8 = var9;
      }

      var8 <<= 1;

      for(var1 = var13; var5 < var8; var4 += var12) {
         var0 = (var1 << 8) + (var2[var4 >> 8] - var1) * (var4 & 255);
         var10001 = var5++;
         var3[var10001] += var0 * var6 >> 6;
         var10001 = var5++;
         var3[var10001] += var0 * var7 >> 6;
      }

      var11.anInt3474 = var4;
      return var5 >> 1;
   }

   private Class3_Sub24_Sub1(Class3_Sub12_Sub1 var1, int var2, int var3) {
      this.aClass3_Sub12_2544 = var1;
      this.anInt3473 = var1.anInt3033;
      this.anInt3482 = var1.anInt3032;
      this.aBoolean3477 = var1.aBoolean3031;
      this.anInt3470 = var2;
      this.anInt3471 = var3;
      this.anInt3478 = 8192;
      this.anInt3474 = 0;
      this.method449();
   }

   final Class3_Sub24 method414() {
      return null;
   }

   private static int method454(int var0, int var1) {
      return var1 < 0?-var0:(int)((double)var0 * Math.sqrt((double)var1 * 1.220703125E-4D) + 0.5D);
   }

   private Class3_Sub24_Sub1(Class3_Sub12_Sub1 var1, int var2, int var3, int var4) {
      this.aClass3_Sub12_2544 = var1;
      this.anInt3473 = var1.anInt3033;
      this.anInt3482 = var1.anInt3032;
      this.aBoolean3477 = var1.aBoolean3031;
      this.anInt3470 = var2;
      this.anInt3471 = var3;
      this.anInt3478 = var4;
      this.anInt3474 = 0;
      this.method449();
   }

   private static int method455(byte[] var2, int[] var3, int var4, int var5, int var6, int var8, int var9, Class3_Sub24_Sub1 var10, int var11, int var12) {
      int var7;
      if(var11 == 0 || (var7 = var5 + (var9 - var4 + var11 - 257) / var11) > var8) {
         var7 = var8;
      }

      int var10001;
      byte var13;
      int var1;
      while(var5 < var7) {
         var1 = var4 >> 8;
         var13 = var2[var1];
         var10001 = var5++;
         var3[var10001] += ((var13 << 8) + (var2[var1 + 1] - var13) * (var4 & 255)) * var6 >> 6;
         var4 += var11;
      }

      if(var11 == 0 || (var7 = var5 + (var9 - var4 + var11 - 1) / var11) > var8) {
         var7 = var8;
      }

      for(var1 = var12; var5 < var7; var4 += var11) {
         var13 = var2[var4 >> 8];
         var10001 = var5++;
         var3[var10001] += ((var13 << 8) + (var1 - var13) * (var4 & 255)) * var6 >> 6;
      }

      var10.anInt3474 = var4;
      return var5;
   }
}
