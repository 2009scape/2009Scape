package org.runite.jagex;

final class Class62 {

   static int anInt942;
   int[][] fileNameHashes;
   static int anInt944 = 0;
   int[] archiveCRCs;
   static RSString aClass94_946 = RSString.createRSString(")2");
   int validArchiveAmount;
   Class69 aClass69_949;
   static int anInt950;
   static int anInt952;
   int[] validArchiveIds;
   int[] archiveLengths;
   int[] archiveNameHash;
   int[] archiveFileLengths;
   int[] archiveRevisions;
   int[][] validFileIds;
   int archiveAmount;
   int revision;
   Class69[] aClass69Array962;
   static int anInt963;
   int anInt964;


   public static void method1223(int var0) {
      try {
         aClass94_946 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "ii.A(" + var0 + ')');
      }
   }

   static void method1224(RSInterface var0, int var2, int var3) {
      try {
         if(0 == var0.aByte162) {
            var0.anInt210 = var0.y;
         } else if (var0.aByte162 == 1) {
            var0.anInt210 = (var2 - var0.anInt193) / 2 + var0.y;
         } else if (2 == var0.aByte162) {
            var0.anInt210 = var2 - var0.anInt193 - var0.y;
         } else if (var0.aByte162 == 3) {
            var0.anInt210 = var0.y * var2 >> 14;
         } else if (4 == var0.aByte162) {
            var0.anInt210 = (var2 * var0.y >> 14) + (-var0.anInt193 + var2) / 2;
         } else {
            var0.anInt210 = -(var2 * var0.y >> 14) + -var0.anInt193 + var2;
         }

         if(0 == var0.aByte273) {
            var0.anInt306 = var0.x;
         } else if(var0.aByte273 == 1) {
            var0.anInt306 = var0.x + (var3 - var0.anInt168) / 2;
         } else if (var0.aByte273 == 2) {
            var0.anInt306 = -var0.x + -var0.anInt168 + var3;
         } else if (3 == var0.aByte273) {
            var0.anInt306 = var0.x * var3 >> 14;
         } else if (4 == var0.aByte273) {
            var0.anInt306 = (var0.x * var3 >> 14) + (var3 - var0.anInt168) / 2;
         } else {
            var0.anInt306 = -(var3 * var0.x >> 14) + var3 + -var0.anInt168;
         }

         if(Class69.aBoolean1040 && (Client.method44(var0).anInt2205 != 0 || var0.type == 0)) {
            if(var0.anInt210 < 0) {
               var0.anInt210 = 0;
            } else if(var0.anInt193 + var0.anInt210 > var2) {
               var0.anInt210 = var2 + -var0.anInt193;
            }

            if(0 > var0.anInt306) {
               var0.anInt306 = 0;
            } else if(var3 < var0.anInt306 - -var0.anInt168) {
               var0.anInt306 = var3 + -var0.anInt168;
            }
         }

      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "ii.B(" + (var0 != null?"{...}":"null") + ',' + 23730 + ',' + var2 + ',' + var3 + ')');
      }
   }

   static void method1225() {
      try {
         MouseListeningClass var1 = Class3_Sub28_Sub7_Sub1.aClass149_4047;
         synchronized(var1) {

            Class3_Sub13_Sub5.anInt3069 = GraphicDefinition.anInt549;
            Class126.anInt1676 = Class3_Sub21.anInt2493;
            Class130.anInt1709 = Class95.anInt1340;
            Class3_Sub28_Sub11.anInt3644 = Class140_Sub3.anInt2743;
            Class163_Sub1.anInt2993 = RenderAnimationDefinition.anInt362;
            ++Class3_Sub28_Sub7_Sub1.anInt4045;
            Class38_Sub1.anInt2614 = Class3_Sub13_Sub32.anInt3389;
            Class75.aLong1102 = Class140_Sub6.aLong2926;
            Class140_Sub3.anInt2743 = 0;
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "ii.D(" + 18074 + ')');
      }
   }

   private void method1226(byte[] var2) {
      try {
         RSByteBuffer buffer = new RSByteBuffer(Class3_Sub28_Sub13.method623((byte)-114, var2));
         int var4 = buffer.getByteB();
         if(var4 == 5 || var4 == 6) {
            if(var4 >= 6) {
               this.revision = buffer.getInt();
            } else {
               this.revision = 0;
            }

            int info = buffer.getByteB();
            int var6 = 0;
            this.validArchiveAmount = buffer.getShort();
            int var7 = -1;
            this.validArchiveIds = new int[this.validArchiveAmount];

            int var8;
            for(var8 = 0; this.validArchiveAmount > var8; ++var8) {
               this.validArchiveIds[var8] = var6 += buffer.getShort();
               if(this.validArchiveIds[var8] > var7) {
                  var7 = this.validArchiveIds[var8];
               }
            }

            this.archiveAmount = var7 - -1;
            this.archiveRevisions = new int[this.archiveAmount];
            this.validFileIds = new int[this.archiveAmount][];
            this.archiveCRCs = new int[this.archiveAmount];
            this.archiveLengths = new int[this.archiveAmount];
            this.archiveFileLengths = new int[this.archiveAmount];
            if(info != 0) {
               this.archiveNameHash = new int[this.archiveAmount];

               for(var8 = 0; this.archiveAmount > var8; ++var8) {
                  this.archiveNameHash[var8] = -1;
               }

               for(var8 = 0; this.validArchiveAmount > var8; ++var8) {
                  this.archiveNameHash[this.validArchiveIds[var8]] = buffer.getInt();
               }

               this.aClass69_949 = new Class69(this.archiveNameHash);
            }

            for(var8 = 0; var8 < this.validArchiveAmount; ++var8) {
               this.archiveCRCs[this.validArchiveIds[var8]] = buffer.getInt();
            }

            for(var8 = 0; this.validArchiveAmount > var8; ++var8) {
               this.archiveRevisions[this.validArchiveIds[var8]] = buffer.getInt();
            }

            var8 = 0;
            while(this.validArchiveAmount > var8) {
               this.archiveFileLengths[this.validArchiveIds[var8]] = buffer.getShort();
               ++var8;
            }

            int var9;
            int var10;
            int var11;
            int var12;
            for(var8 = 0; this.validArchiveAmount > var8; ++var8) {
               var6 = 0;
               var9 = this.validArchiveIds[var8];
               var10 = this.archiveFileLengths[var9];
               var11 = -1;
               this.validFileIds[var9] = new int[var10];

               for(var12 = 0; var10 > var12; ++var12) {
                  int var13 = this.validFileIds[var9][var12] = var6 += buffer.getShort();
                  if(var13 > var11) {
                     var11 = var13;
                  }
               }

               this.archiveLengths[var9] = var11 + 1;
               if(var10 == 1 + var11) {
                  this.validFileIds[var9] = null;
               }
            }

            if(info != 0) {
               this.aClass69Array962 = new Class69[var7 - -1];
               this.fileNameHashes = new int[1 + var7][];

               for(var8 = 0; var8 < this.validArchiveAmount; ++var8) {
                  var9 = this.validArchiveIds[var8];
                  var10 = this.archiveFileLengths[var9];
                  this.fileNameHashes[var9] = new int[this.archiveLengths[var9]];

                  for(var11 = 0; var11 < this.archiveLengths[var9]; ++var11) {
                     this.fileNameHashes[var9][var11] = -1;
                  }

                  for(var11 = 0; var10 > var11; ++var11) {
                     if(null == this.validFileIds[var9]) {
                        var12 = var11;
                     } else {
                        var12 = this.validFileIds[var9][var11];
                     }

                     this.fileNameHashes[var9][var12] = buffer.getInt();
                  }

                  this.aClass69Array962[var9] = new Class69(this.fileNameHashes[var9]);
               }
            }

         } else {
            throw new RuntimeException();
         }
      } catch (RuntimeException var14) {
         throw Class44.clientError(var14, "ii.C(" + 2 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   Class62(byte[] var1, int var2) {
      try {
         this.anInt964 = Class38.method1026(var1, var1.length);
         if(var2 == this.anInt964) {
            this.method1226(var1);
         } else {
            throw new RuntimeException();
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "ii.<init>(" + (var1 != null?"{...}":"null") + ',' + var2 + ')');
      }
   }

}
