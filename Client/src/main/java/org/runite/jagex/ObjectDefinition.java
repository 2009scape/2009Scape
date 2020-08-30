package org.runite.jagex;

import java.util.Objects;

final class ObjectDefinition {

   private short[] aShortArray1476;
   private short[] OriginalColors;
   int anInt1478;
   private int anInt1479;
   int SizeX = 1;
   private int anInt1481;
   int MapIcon;
   boolean aBoolean1483 = false;
   int anInt1484;
   int SizeY = 1;
   boolean ProjectileClipped;
   private int[] configuration;
   private int anInt1488;
   private int anInt1489;
   static boolean[] aBooleanArray1490 = new boolean[112];
   boolean aBoolean1491;
   boolean aBoolean1492;
   int anInt1493;
   private int anInt1494 = 0;
   private short[] aShortArray1495;
   private int anInt1496;
   static int[][][] anIntArrayArrayArray1497 = new int[4][13][13];
   boolean NotClipped;
   RSString[] options;
   private short aShort1500;
   private Class130 aClass130_1501;
   boolean aBoolean1502 = false;
   boolean aBoolean1503;
   RSString name;
   private byte aByte1505;
   private short[] ModifiedColors;
   boolean aBoolean1507;
   boolean aBoolean1510;
   private int anInt1511;
   int anInt1512;
   private byte[] aByteArray1513;
   static int worldId = 1;
   int anInt1515;
   int anInt1516;
   int anInt1517;
   int anInt1518;
   private int[] models;
   int anInt1520;
   static int anInt1521 = 0;
   int anInt1522;
   int[] ChildrenIds;
   boolean aBoolean1525;
   private int ConfigFileId;
   int objectId;
   int anInt1528;
   int SecondInt;
   boolean aBoolean1530;
   int animationId;
   private int ConfigId;
   int WalkingFlag;
   private int SecondBool;
   static short aShort1535 = 320;
   private boolean aBoolean1536;
   boolean aBoolean1537;
   int ClipType;
   int[] anIntArray1539;
   int anInt1540;
   private boolean aBoolean1541;
   boolean aBoolean1542;


   final boolean method1684(int var2) {
      try {
         if(this.configuration != null) {
            for(int var7 = 0; var7 < this.configuration.length; ++var7) {
               if(this.configuration[var7] == var2) {
                  return Class69.aClass153_1043.method2129((byte)72, 0, this.models[var7] & '\uffff');
               }
            }

            return true;
         } else if(null == this.models) {
            return true;
         } else if(var2 == 10) {
            boolean var4 = true;

            for(int var5 = 0; this.models.length > var5; ++var5) {
               var4 &= Class69.aClass153_1043.method2129((byte)71, 0, '\uffff' & this.models[var5]);
            }

            return var4;
         } else {
            return true;
         }
      } catch (RuntimeException var6) {
         throw Class44.clientError(var6, "pb.H(" + 115 + ',' + var2 + ')');
      }
   }

   final ObjectDefinition method1685(int var1) {
      try {
         if(var1 != 0) {
            this.method1697(-92, 83, (LDIndexedSprite)null, -13, (AnimationDefinition)null, 18, (int[][])((int[][])null), true, 114, 123, (int[][])((int[][])null), 118, 85, -116);
         }

         int var2 = -1;
         if(this.ConfigFileId != -1) {
            var2 = NPCDefinition.method1484(this.ConfigFileId);
         } else if(this.ConfigId != -1) {
            var2 = ItemDefinition.ram[this.ConfigId];
         }

         if(var2 >= 0 && this.ChildrenIds.length - 1 > var2 && this.ChildrenIds[var2] != -1) {
            return Class162.getObjectDefinition(this.ChildrenIds[var2]);
         } else {
            int var3 = this.ChildrenIds[-1 + this.ChildrenIds.length];
            return var3 == -1 ?null:Class162.getObjectDefinition(var3);
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "pb.C(" + var1 + ')');
      }
   }

   private Model_Sub1 method1686(int var1, int var2) {
      try {
         Model_Sub1 var4 = null;
         boolean var5 = this.aBoolean1536;
         if(var2 == 2 && 3 < var1) {
            var5 = !var5;
         }

         int var6;
         int var7;
         if(null == this.configuration) {
            if(var2 != 10) {
               return null;
            }

            if(this.models == null) {
               return null;
            }

            var6 = this.models.length;

            for(var7 = 0; var6 > var7; ++var7) {
               int var8 = this.models[var7];
               if(var5) {
                  var8 += 65536;
               }

               var4 = (Model_Sub1)Class99.aClass93_1401.get((long)var8);
               if(var4 == null) {
                  var4 = Model_Sub1.method2015(Class69.aClass153_1043, var8 & '\uffff');
                  if(var4 == null) {
                     return null;
                  }

                  if(var5) {
                     var4.method2002();
                  }

                  Class99.aClass93_1401.put((byte)-91, var4, (long)var8);
               }

               if(1 < var6) {
                  Class164.aClass140_Sub5Array2058[var7] = var4;
               }
            }

            if(var6 > 1) {
               var4 = new Model_Sub1(Class164.aClass140_Sub5Array2058, var6);
            }
         } else {
            var6 = -1;

            for(var7 = 0; this.configuration.length > var7; ++var7) {
               if(var2 == this.configuration[var7]) {
                  var6 = var7;
                  break;
               }
            }

            if(var6 == -1) {
               return null;
            }

            var7 = this.models[var6];
            if(var5) {
               var7 += 65536;
            }

            var4 = (Model_Sub1)Class99.aClass93_1401.get((long)var7);
            if(null == var4) {
               var4 = Model_Sub1.method2015(Class69.aClass153_1043, var7 & '\uffff');
               if(null == var4) {
                  return null;
               }

               if(var5) {
                  var4.method2002();
               }

               Class99.aClass93_1401.put((byte)-122, var4, (long)var7);
            }
         }

         boolean var11;
         var11 = 128 != this.anInt1479 || this.anInt1488 != 128 || 128 != this.anInt1481;

         boolean var12;
         var12 = this.anInt1496 != 0 || this.anInt1511 != 0 || 0 != this.SecondBool;

         Model_Sub1 var13 = new Model_Sub1(Objects.requireNonNull(var4), var1 == 0 && !var11 && !var12, this.OriginalColors == null, null == this.aShortArray1476);
         if(var2 == 4 && var1 > 3) {
            var13.method2011();
            var13.method2001(45, 0, -45);
         }

         var1 &= 3;
         if(var1 == 1) {
            var13.method1991();
         } else if (var1 == 2) {
            var13.method1989();
         } else if (3 == var1) {
            var13.method2018();
         }

         int var9;
         if(null != this.OriginalColors) {
            for(var9 = 0; this.OriginalColors.length > var9; ++var9) {
               if(null != this.aByteArray1513 && this.aByteArray1513.length > var9) {
                  var13.method2016(this.OriginalColors[var9], Class3_Sub13_Sub9.aShortArray3110[255 & this.aByteArray1513[var9]]);
               } else {
                  var13.method2016(this.OriginalColors[var9], this.ModifiedColors[var9]);
               }
            }
         }

         if(this.aShortArray1476 != null) {
            for(var9 = 0; this.aShortArray1476.length > var9; ++var9) {
               var13.method1998(this.aShortArray1476[var9], this.aShortArray1495[var9]);
            }
         }

         if(var11) {
            var13.method1994(this.anInt1479, this.anInt1488, this.anInt1481);
         }

         if(var12) {
            var13.method2001(this.anInt1496, this.anInt1511, this.SecondBool);
         }

         return var13;
      } catch (RuntimeException var10) {
         throw Class44.clientError(var10, "pb.O(" + var1 + ',' + var2 + ',' + -1 + ')');
      }
   }

   public static void method1687(int var0) {
      try {
         anIntArrayArrayArray1497 = (int[][][])null;
         aBooleanArray1490 = null;
         if(var0 != -11) {
            anInt1521 = -96;
         }

      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "pb.B(" + var0 + ')');
      }
   }

   static void method1688(int var0, int var1, int var2) {
      Class3_Sub2 var3 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2];
      if(var3 == null) {
      } else {
         Class72 var4 = var3.aClass72_2245;
         var3.aClass72_2245 = null;
      }
   }

   final void method1689(int var1) {
      try {
         if(this.SecondInt == -1) {
            this.SecondInt = 0;
            if(null != this.models && (null == this.configuration || this.configuration[0] == 10)) {
               this.SecondInt = 1;
            }

            for(int var2 = 0; var2 < 5; ++var2) {
               if(this.options[var2] != null) {
                  this.SecondInt = 1;
                  break;
               }
            }
         }

         if(var1 != -2116) {
            this.method1692(67, (RSByteBuffer)null);
         }

         if(-1 == this.anInt1540) {
            this.anInt1540 = this.ClipType != 0 ?1:0;
         }

      } catch (RuntimeException var3) {
         throw Class44.clientError(var3, "pb.D(" + var1 + ')');
      }
   }

   final boolean method1690() {
      try {
         if(this.ChildrenIds == null) {
            return this.anInt1512 != -1 || this.anIntArray1539 != null;
         } else {

            for(int var2 = 0; this.ChildrenIds.length > var2; ++var2) {
               if(this.ChildrenIds[var2] != -1) {
                  ObjectDefinition var3 = Class162.getObjectDefinition(this.ChildrenIds[var2]);
                  if(var3.anInt1512 != -1 || var3.anIntArray1539 != null) {
                     return true;
                  }
               }
            }

            return false;
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "pb.F(" + 28933 + ')');
      }
   }

   final int method1691(int var1, int var2, byte var3) {
      try {
         if(var3 <= 76) {
            return -40;
         } else if(this.aClass130_1501 == null) {
            return var1;
         } else {
            Class3_Sub18 var4 = (Class3_Sub18)this.aClass130_1501.method1780((long)var2, 0);
            return var4 != null?var4.anInt2467:var1;
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "pb.N(" + var1 + ',' + var2 + ',' + var3 + ')');
      }
   }

   final void method1692(int var1, RSByteBuffer var2) {
      try {
         while(true) {
            int var3 = var2.getByteB();
            if(var3 == 0) {
               if(var1 != 6219) {
                  this.method1696(105, -55, (int[][])((int[][])null), -39, 71, (int[][])((int[][])null), true, (LDIndexedSprite)null, (byte)-117, false, -25);
               }

               return;
            }

            this.parseOpcode(var2, var3);
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "pb.G(" + var1 + ',' + (var2 != null?"{...}":"null") + ')');
      }
   }

   private void parseOpcode(RSByteBuffer buffer, int opcode) {
      try {

         int var4;
         int var5;
         if(1 == opcode) {
            var4 = buffer.getByteB();
            if(var4 > 0) {
               if(this.models == null || Class47.aBoolean742) {
                  this.configuration = new int[var4];
                  this.models = new int[var4];

                  for(var5 = 0; var4 > var5; ++var5) {
                     this.models[var5] = buffer.getShort();
                     this.configuration[var5] = buffer.getByteB();
                  }
               } else {
                  buffer.index += var4 * 3;
               }
            }
         } else if(opcode == 2) {
            this.name = buffer.getString();
         } else if(opcode == 5) {
            var4 = buffer.getByteB();
            if(var4 > 0) {
               if(null == this.models || Class47.aBoolean742) {
                  this.models = new int[var4];
                  this.configuration = null;

                  for(var5 = 0; var4 > var5; ++var5) {
                     this.models[var5] = buffer.getShort();
                  }
               } else {
                  buffer.index += var4 * 2;
               }
            }
         } else if (opcode == 14) {
            this.SizeX = buffer.getByteB();
         } else if (opcode == 15) {
            this.SizeY = buffer.getByteB();
         } else if (opcode == 17) {
            this.ClipType = 0;
            this.ProjectileClipped = false;
         } else if (18 == opcode) {
            this.ProjectileClipped = false;
         } else if (opcode == 19) {
            this.SecondInt = buffer.getByteB();
         } else if (opcode == 21) {
            this.aByte1505 = 1;
         } else if (opcode == 22) {
            this.aBoolean1541 = true;
         } else if (opcode == 23) {
            this.aBoolean1542 = true;
         } else if (opcode == 24) {
            this.animationId = buffer.getShort();
            if (this.animationId == 65535) {
               this.animationId = -1;
            }
         } else if (opcode == 27) {
            this.ClipType = 1;
         } else if (28 == opcode) {
            this.anInt1528 = buffer.getByteB();
         } else if (opcode == 29) {
            this.anInt1494 = buffer.getByte();
         } else if (39 == opcode) {
            this.anInt1489 = buffer.getByte() * 5;
         } else if (opcode >= 30 && opcode < 35) {
            this.options[opcode - 30] = buffer.getString();
            if (this.options[-30 + opcode].equals(-112, TextCore.HasHidden)) {
               this.options[-30 + opcode] = null;
            }
         } else if (opcode == 40) {
            var4 = buffer.getByteB();
            this.OriginalColors = new short[var4];
            this.ModifiedColors = new short[var4];

            for (var5 = 0; var5 < var4; ++var5) {
               this.OriginalColors[var5] = (short) buffer.getShort();
               this.ModifiedColors[var5] = (short) buffer.getShort();
            }
         } else if (opcode == 41) {
            var4 = buffer.getByteB();
            this.aShortArray1495 = new short[var4];
            this.aShortArray1476 = new short[var4];

            for (var5 = 0; var4 > var5; ++var5) {
               this.aShortArray1476[var5] = (short) buffer.getShort();
               this.aShortArray1495[var5] = (short) buffer.getShort();
            }
         } else if (opcode == 42) {
            var4 = buffer.getByteB();
            this.aByteArray1513 = new byte[var4];

            for (var5 = 0; var4 > var5; ++var5) {
               this.aByteArray1513[var5] = buffer.getByte();
            }
         } else if (opcode == 60) {
            this.MapIcon = buffer.getShort();
         } else if (opcode == 62) {
            this.aBoolean1536 = true;
         } else if (opcode == 64) {
            this.aBoolean1525 = false;
         } else if (opcode == 65) {
            this.anInt1479 = buffer.getShort();
         } else if (opcode == 66) {
            this.anInt1488 = buffer.getShort();
         } else if (opcode == 67) {
            this.anInt1481 = buffer.getShort();
         } else if (opcode == 69) {
            this.WalkingFlag = buffer.getByteB();
         } else if (70 == opcode) {
            this.anInt1496 = buffer.getShort((byte) 123);
         } else if (71 == opcode) {
            this.anInt1511 = buffer.getShort((byte) 94);
         } else if (72 == opcode) {
            this.SecondBool = buffer.getShort((byte) 76);
         } else if (opcode == 73) {
            this.aBoolean1483 = true;
         } else if (opcode == 74) {
            this.NotClipped = true;
         } else if (75 == opcode) {
            this.anInt1540 = buffer.getByteB();
         } else if (opcode == 77 || opcode == 92) {
            var4 = -1;
            this.ConfigFileId = buffer.getShort();
            if ('\uffff' == this.ConfigFileId) {
               this.ConfigFileId = -1;
            }

            this.ConfigId = buffer.getShort();
            if ('\uffff' == this.ConfigId) {
               this.ConfigId = -1;
            }

            if (92 == opcode) {
               var4 = buffer.getShort();
               if (var4 == '\uffff') {
                  var4 = -1;
               }
            }

            var5 = buffer.getByteB();
            this.ChildrenIds = new int[var5 - -2];

            for (int var6 = 0; var5 >= var6; ++var6) {
               this.ChildrenIds[var6] = buffer.getShort();
               if ('\uffff' == this.ChildrenIds[var6]) {
                  this.ChildrenIds[var6] = -1;
               }
            }

            this.ChildrenIds[1 + var5] = var4;
         } else if (78 == opcode) {
            this.anInt1512 = buffer.getShort();
            this.anInt1484 = buffer.getByteB();
         } else if (opcode == 79) {
            this.anInt1518 = buffer.getShort();
            this.anInt1515 = buffer.getShort();
            this.anInt1484 = buffer.getByteB();
            var4 = buffer.getByteB();
            this.anIntArray1539 = new int[var4];

            for (var5 = 0; var5 < var4; ++var5) {
               this.anIntArray1539[var5] = buffer.getShort();
            }
         } else if (81 == opcode) {
            this.aByte1505 = 2;
            this.aShort1500 = (short) (256 * buffer.getByteB());
         } else if (opcode == 82) {
            this.aBoolean1530 = true;
         } else if (opcode == 88) {
            this.aBoolean1503 = false;
         } else if (opcode == 89) {
            this.aBoolean1492 = false;
         } else if (90 == opcode) {
            this.aBoolean1502 = true;
         } else if (opcode == 91) {
            this.aBoolean1491 = true;
         } else if (opcode == 93) {
            this.aByte1505 = 3;
            this.aShort1500 = (short) buffer.getShort();
         } else if (opcode == 94) {
            this.aByte1505 = 4;
         } else if (opcode == 95) {
            this.aByte1505 = 5;
         } else if (opcode == 96) {
            this.aBoolean1507 = true;
         } else if (opcode == 97) {
            this.aBoolean1537 = true;
         } else if (opcode == 98) {
            this.aBoolean1510 = true;
         } else if (opcode == 99) {
            this.anInt1493 = buffer.getByteB();
            this.anInt1517 = buffer.getShort();
         } else if (opcode == 100) {
            this.anInt1520 = buffer.getByteB();
            this.anInt1522 = buffer.getShort();
         } else if (opcode == 101) {
            this.anInt1478 = buffer.getByteB();
         } else if (opcode == 102) {
            this.anInt1516 = buffer.getShort();
         } else if (249 == opcode) {
            var4 = buffer.getByteB();
            if (null == this.aClass130_1501) {
               var5 = Class95.method1585((byte) 83, var4);
               this.aClass130_1501 = new Class130(var5);
            }

            for (var5 = 0; var4 > var5; ++var5) {
               boolean var10 = buffer.getByteB() == 1;
               int var7 = buffer.getTriByte((byte) 122);
               Object var8;
               if (var10) {
                  var8 = new Class3_Sub29(buffer.getString());
               } else {
                  var8 = new Class3_Sub18(buffer.getInt());
               }

               this.aClass130_1501.method1779((Class3) var8, (long) var7);
            }
         }
      } catch (RuntimeException var9) {
         throw Class44.clientError(var9, "pb.K(" + (buffer != null?"{...}":"null") + ',' + opcode + ',' + -80 + ')');
      }
   }

   final boolean hasModels() {
      try {
         if(null == this.models) {
            return true;
         } else {
            boolean var2 = true;

            for(int var3 = 0; var3 < this.models.length; ++var3) {
               var2 &= Class69.aClass153_1043.method2129((byte)64, 0, '\uffff' & this.models[var3]);
            }

            return var2;
         }
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "pb.I(" + false + ')');
      }
   }

   private Class140_Sub1_Sub1 method1695(int var1, boolean var2, int var4) {
      try {
         int var6 = this.anInt1494 + 64;
         int var7 = 5 * this.anInt1489 + 768;
         Class140_Sub1_Sub1 var5;
         int var8;
         int var12;
         if(this.configuration == null) {
            if(var4 != 10) {
               return null;
            }

            if(this.models == null) {
               return null;
            }

            var8 = this.models.length;
            if(var8 == 0) {
               return null;
            }

            long var16 = 0L;

            for(int var11 = 0; var11 < var8; ++var11) {
               var16 = (long)this.models[var11] + var16 * 67783L;
            }

            if(var2) {
               var16 = ~var16;
            }

            var5 = (Class140_Sub1_Sub1)Class99.aClass93_1401.get(var16);
            if(null == var5) {
               Model_Sub1 var17 = null;

               for(var12 = 0; var12 < var8; ++var12) {
                  var17 = Model_Sub1.method2015(Class69.aClass153_1043, this.models[var12] & '\uffff');
                  if(null == var17) {
                     return null;
                  }

                  if(var8 > 1) {
                     Class164.aClass140_Sub5Array2058[var12] = var17;
                  }
               }

               if(1 < var8) {
                  var17 = new Model_Sub1(Class164.aClass140_Sub5Array2058, var8);
               }

               var5 = new Class140_Sub1_Sub1(var17, var6, var7, var2);
               Class99.aClass93_1401.put((byte)-75, var5, var16);
            }
         } else {
            var8 = -1;

            int var9;
            for(var9 = 0; this.configuration.length > var9; ++var9) {
               if(var4 == this.configuration[var9]) {
                  var8 = var9;
                  break;
               }
            }

            if(var8 == -1) {
               return null;
            }

            var9 = this.models[var8];
            if(var2) {
               var9 += 65536;
            }

            var5 = (Class140_Sub1_Sub1)Class99.aClass93_1401.get((long)var9);
            if(null == var5) {
               Model_Sub1 var10 = Model_Sub1.method2015(Class69.aClass153_1043, '\uffff' & var9);
               if(null == var10) {
                  return null;
               }

               var5 = new Class140_Sub1_Sub1(var10, var6, var7, var2);
               Class99.aClass93_1401.put((byte)-101, var5, (long)var9);
            }
         }

         boolean var14 = this.aBoolean1536;
         if(var4 == 2 && var1 > 3) {
            var14 = !var14;
         }

         boolean var15 = 128 == this.anInt1488 && this.anInt1511 == 0;
         boolean var18 = var1 == 0 && 128 == this.anInt1479 && this.anInt1481 == 128 && this.anInt1496 == 0 && this.SecondBool == 0 && !var14;
         Class140_Sub1_Sub1 var19 = var5.method1926(var18, var15, this.OriginalColors == null, var6 == var5.method1903(), var1 == 0 && !var14, true, var5.method1924() == var7, !var14, this.aShortArray1476 == null);
         if(var14) {
            var19.method1931();
         }

         if(var4 == 4 && 3 < var1) {
            var19.method1932();
            var19.method1897(45, 0, -45);
         }

         var1 &= 3;
         if(1 == var1) {
            var19.method1925();
         } else if(var1 == 2) {
            var19.method1911();
         } else if (var1 == 3) {
            var19.method1902();
         }

         if(null != this.OriginalColors) {
            for(var12 = 0; var12 < this.OriginalColors.length; ++var12) {
               var19.method1918(this.OriginalColors[var12], this.ModifiedColors[var12]);
            }
         }

         if(null != this.aShortArray1476) {
            for(var12 = 0; var12 < this.aShortArray1476.length; ++var12) {
               var19.method1916(this.aShortArray1476[var12], this.aShortArray1495[var12]);
            }
         }

         if(this.anInt1479 != 128 || this.anInt1488 != 128 || this.anInt1481 != 128) {
            var19.resize(this.anInt1479, this.anInt1488, this.anInt1481);
         }

         if(this.anInt1496 != 0 || this.anInt1511 != 0 || 0 != this.SecondBool) {
            var19.method1897(this.anInt1496, this.anInt1511, this.SecondBool);
         }

         if(var6 != var19.method1903()) {
            var19.method1914(var6);
         }

         if(var19.method1924() != var7) {
            var19.method1909(var7);
         }

         return var19;
      } catch (RuntimeException var13) {
         throw Class44.clientError(var13, "pb.L(" + var1 + ',' + var2 + ',' + true + ',' + var4 + ')');
      }
   }

   final Class136 method1696(int var1, int var2, int[][] var3, int var4, int var5, int[][] var6, boolean var7, LDIndexedSprite var8, byte var9, boolean var10, int var11) {
      try {
         if(var9 >= -5) {
            return (Class136)null;
         } else {
            long var12;
            if(HDToolKit.highDetail) {
               if(null == this.configuration) {
                  var12 = (long)((this.objectId << 10) + var1);
               } else {
                  var12 = (long)(var1 + (this.objectId << 10) - -(var4 << 3));
               }

               Class136 var16 = (Class136)Class3_Sub28_Sub7_Sub1.aClass93_4051.get(var12);
               Class140_Sub1_Sub1 var14;
               LDIndexedSprite var15;
               if(null == var16) {
                  var14 = this.method1695(var1, false, var4);
                  if(null == var14) {
                     Class100.aClass136_1413.aClass140_1777 = null;
                     Class100.aClass136_1413.aClass109_Sub1_1770 = null;
                     return Class100.aClass136_1413;
                  }

                  if(var4 == 10 && var1 > 3) {
                     var14.method1876(256);
                  }

                  if(var10) {
                     var15 = var14.method1933(var8);
                  } else {
                     var15 = null;
                  }

                  var16 = new Class136();
                  var16.aClass140_1777 = var14;
                  var16.aClass109_Sub1_1770 = var15;
                  Class3_Sub28_Sub7_Sub1.aClass93_4051.put((byte)-93, var16, var12);
               } else {
                  var14 = (Class140_Sub1_Sub1)var16.aClass140_1777;
                  var15 = var16.aClass109_Sub1_1770;
               }

               boolean var17 = this.aBoolean1541 & var7;
               Class140_Sub1_Sub1 var18 = var14.method1926(3 != this.aByte1505, this.aByte1505 == 0, true, true, true, !var17, true, true, true);
               if(this.aByte1505 != 0) {
                  var18.method1919(this.aByte1505, this.aShort1500, var14, var3, var6, var2, var5, var11);
               }

               var18.method1920(this.SecondInt == 0 && !this.aBoolean1510, true, true, this.SecondInt == 0, true, false);
               Class100.aClass136_1413.aClass140_1777 = var18;
               var18.aBoolean3809 = var17;
               Class100.aClass136_1413.aClass109_Sub1_1770 = var15;
            } else {
               if(this.configuration == null) {
                  var12 = (long)((this.objectId << 10) + var1);
               } else {
                  var12 = (long)((var4 << 3) + ((this.objectId << 10) - -var1));
               }

               boolean var20;
               if(var7 && this.aBoolean1541) {
                  var12 |= Long.MIN_VALUE;
                  var20 = true;
               } else {
                  var20 = false;
               }

               Object var22 = (GameObject)Class3_Sub28_Sub7_Sub1.aClass93_4051.get(var12);
               if(null == var22) {
                  Model_Sub1 var21 = this.method1686(var1, var4);
                  if(var21 == null) {
                     Class100.aClass136_1413.aClass140_1777 = null;
                     return Class100.aClass136_1413;
                  }

                  var21.method2010();
                  if(var4 == 10 && var1 > 3) {
                     var21.method2011();
                  }

                  if(var20) {
                     var21.aShort2879 = (short)(64 + this.anInt1494);
                     var22 = var21;
                     var21.aShort2876 = (short)(768 + 5 * this.anInt1489);
                     var21.method1997();
                  } else {
                     var22 = new Class140_Sub1_Sub2(var21, 64 - -this.anInt1494, 5 * this.anInt1489 + 768, -50, -10, -50);
                  }

                  Class3_Sub28_Sub7_Sub1.aClass93_4051.put((byte)-89, var22, var12);
               }

               if(var20) {
                  var22 = ((Model_Sub1)var22).method2004();
               }

               if(0 != this.aByte1505) {
                  if(var22 instanceof Class140_Sub1_Sub2) {
                     var22 = ((Class140_Sub1_Sub2)var22).method1941(this.aByte1505, this.aShort1500, var3, var6, var2, var5, var11, true);
                  } else if (var22 instanceof Model_Sub1) {
                     var22 = ((Model_Sub1) var22).method1999(this.aByte1505, this.aShort1500, var3, var6, var2, var5, var11);
                  }
               }

               Class100.aClass136_1413.aClass140_1777 = (GameObject)var22;
            }
            return Class100.aClass136_1413;
         }
      } catch (RuntimeException var19) {
         throw Class44.clientError(var19, "pb.A(" + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + var4 + ',' + var5 + ',' + (var6 != null?"{...}":"null") + ',' + var7 + ',' + (var8 != null?"{...}":"null") + ',' + var9 + ',' + var10 + ',' + var11 + ')');
      }
   }

   final Class136 method1697(int var1, int var2, LDIndexedSprite var3, int var4, AnimationDefinition var5, int var6, int[][] var7, boolean var8, int var9, int var10, int[][] var11, int var12, int var13, int var14) {
      try {
         if(var10 != 8308) {
            this.hasModels();
         }

         long var15;
         if(HDToolKit.highDetail) {
            if(this.configuration == null) {
               var15 = (long)(var6 + (this.objectId << 10));
            } else {
               var15 = (long)((var13 << 3) + ((this.objectId << 10) - -var6));
            }

            Class140_Sub1_Sub1 var23 = (Class140_Sub1_Sub1)Class154.aClass93_1965.get(var15);
            if(var23 == null) {
               var23 = this.method1695(var6, true, var13);
               if(null == var23) {
                  return null;
               }

               var23.method1908();
               var23.method1920(false, false, false, false, false, true);
               Class154.aClass93_1965.put((byte)-75, var23, var15);
            }

            boolean var19 = false;
            Class140_Sub1_Sub1 var22 = var23;
            if(null != var5) {
               var22 = (Class140_Sub1_Sub1)var5.method2056(var12, var9, var14, var6, var23);
               var19 = true;
            }

            if(var13 == 10 && 3 < var6) {
               if(!var19) {
                  var22 = (Class140_Sub1_Sub1)var22.method1890(true, true, true);
                  var19 = true;
               }

               var22.method1876(256);
            }

            if(var8) {
               Class100.aClass136_1413.aClass109_Sub1_1770 = var22.method1933(var3);
            } else {
               Class100.aClass136_1413.aClass109_Sub1_1770 = null;
            }

            if(this.aByte1505 != 0) {
               if(!var19) {
                  var22 = (Class140_Sub1_Sub1)var22.method1890(true, true, true);
               }

               var22.method1919(this.aByte1505, this.aShort1500, var23, var7, var11, var2, var4, var1);
            }

            Class100.aClass136_1413.aClass140_1777 = var22;
         } else {
            if(this.configuration == null) {
               var15 = (long)((this.objectId << 10) + var6);
            } else {
               var15 = (long)(var6 + (this.objectId << 10) + (var13 << 3));
            }

            Class140_Sub1_Sub2 var17 = (Class140_Sub1_Sub2)Class154.aClass93_1965.get(var15);
            if(var17 == null) {
               Model_Sub1 var18 = this.method1686(var6, var13);
               if(var18 == null) {
                  return null;
               }

               var17 = new Class140_Sub1_Sub2(var18, 64 + this.anInt1494, this.anInt1489 * 5 + 768, -50, -10, -50);
               Class154.aClass93_1965.put((byte)-94, var17, var15);
            }

            boolean var21 = false;
            if(var5 != null) {
               var21 = true;
               var17 = (Class140_Sub1_Sub2)var5.method2054(var9, var12, var17, var6, var14);
            }

            if(var13 == 10 && var6 > 3) {
               if(!var21) {
                  var21 = true;
                  var17 = (Class140_Sub1_Sub2)var17.method1890(true, true, true);
               }

               var17.method1876(256);
            }

            if(this.aByte1505 != 0) {
               if(!var21) {
                  var17 = (Class140_Sub1_Sub2)var17.method1890(true, true, true);
               }

               var17 = var17.method1941(this.aByte1505, this.aShort1500, var7, var11, var2, var4, var1, false);
            }

            Class100.aClass136_1413.aClass140_1777 = var17;
         }
         return Class100.aClass136_1413;
      } catch (RuntimeException var20) {
         throw Class44.clientError(var20, "pb.M(" + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + var4 + ',' + (var5 != null?"{...}":"null") + ',' + var6 + ',' + (var7 != null?"{...}":"null") + ',' + var8 + ',' + var9 + ',' + var10 + ',' + (var11 != null?"{...}":"null") + ',' + var12 + ',' + var13 + ',' + var14 + ')');
      }
   }

   final RSString method1698(RSString var1, int var3) {
      try {

         if(null == this.aClass130_1501) {
            return var1;
         } else {
            Class3_Sub29 var4 = (Class3_Sub29)this.aClass130_1501.method1780((long)var3, 0);
            return var4 == null?var1:var4.aClass94_2586;
         }
      } catch (RuntimeException var5) {
         throw Class44.clientError(var5, "pb.E(" + (var1 != null?"{...}":"null") + ',' + -23085 + ',' + var3 + ')');
      }
   }

   public ObjectDefinition() {
      this.name = RSString.createRSString("null");
      this.aBoolean1503 = true;
      this.anInt1493 = -1;
      this.anInt1515 = 0;
      this.anInt1516 = -1;
      this.aByte1505 = 0;
      this.aBoolean1491 = false;
      this.anInt1517 = -1;
      this.anInt1496 = 0;
      this.anInt1518 = 0;
      this.MapIcon = -1;
      this.aBoolean1510 = false;
      this.anInt1520 = -1;
      this.aShort1500 = -1;
      this.anInt1481 = 128;
      this.options = new RSString[5];
      this.anInt1479 = 128;
      this.aBoolean1492 = true;
      this.anInt1488 = 128;
      this.NotClipped = false;
      this.SecondInt = -1;
      this.aBoolean1530 = false;
      this.aBoolean1525 = true;
      this.ConfigId = -1;
      this.anInt1522 = -1;
      this.WalkingFlag = 0;
      this.ProjectileClipped = true;
      this.SecondBool = 0;
      this.anInt1478 = 0;
      this.anInt1528 = 16;
      this.aBoolean1537 = false;
      this.anInt1511 = 0;
      this.anInt1484 = 0;
      this.anInt1489 = 0;
      this.animationId = -1;
      this.aBoolean1507 = false;
      this.anInt1512 = -1;
      this.ClipType = 2;
      this.aBoolean1536 = false;
      this.ConfigFileId = -1;
      this.anInt1540 = -1;
      this.aBoolean1541 = false;
      this.aBoolean1542 = false;
   }

}
