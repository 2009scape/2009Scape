package org.runite.jagex;
import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

final class Class3_Sub28_Sub5 extends Node {

   static CacheIndex aClass153_3580;
   static Calendar aCalendar3581 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
   static int[] anIntArray3587;
   AnimationHeader[] animations;
   static int anInt3589;
   static int anInt3590 = -1;
   static int anInt3591 = 0;
   static int[] anIntArray3592 = new int[256];
   static volatile boolean aBoolean3593 = false;


   static void method556(int var0, int var1, int var2, byte var3, int var4, int var5) {
      try {
         if(var3 < -93) {
            int var8 = var2 * var2;
            int var6 = 0;
            int var7 = var1;
            int var11 = var8 << 1;
            int var9 = var1 * var1;
            int var12 = var1 << 1;
            int var10 = var9 << 1;
            int var13 = var8 * (-var12 + 1) + var10;
            int var14 = -(var11 * (-1 + var12)) + var9;
            int var16 = var9 << 2;
            int var17 = var10 * ((var6 << 1) + 3);
            int var15 = var8 << 2;
            int var18 = ((var1 << 1) - 3) * var11;
            int var19 = var16 * (1 + var6);
            int var21;
            int var22;
            if(var5 >= Class159.anInt2020 && Class57.anInt902 >= var5) {
               var21 = Class40.method1040(Class3_Sub28_Sub18.anInt3765, var2 + var4, Class101.anInt1425);
               var22 = Class40.method1040(Class3_Sub28_Sub18.anInt3765, -var2 + var4, Class101.anInt1425);
               Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var5], var22, 91, var21, var0);
            }

            for(int var20 = var15 * (-1 + var1); 0 < var7; var20 -= var15) {
               --var7;
               if(var13 < 0) {
                  while(0 > var13) {
                     ++var6;
                     var13 += var17;
                     var14 += var19;
                     var19 += var16;
                     var17 += var16;
                  }
               }

               var21 = var5 - var7;
               if(var14 < 0) {
                  var14 += var19;
                  var13 += var17;
                  var17 += var16;
                  var19 += var16;
                  ++var6;
               }

               var14 += -var18;
               var18 -= var15;
               var13 += -var20;
               var22 = var7 + var5;
               if(var22 >= Class159.anInt2020 && var21 <= Class57.anInt902) {
                  int var23 = Class40.method1040(Class3_Sub28_Sub18.anInt3765, var6 + var4, Class101.anInt1425);
                  int var24 = Class40.method1040(Class3_Sub28_Sub18.anInt3765, -var6 + var4, Class101.anInt1425);
                  if(var21 >= Class159.anInt2020) {
                     Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var21], var24, 121, var23, var0);
                  }

                  if(var22 <= Class57.anInt902) {
                     Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var22], var24, -110, var23, var0);
                  }
               }
            }

         }
      } catch (RuntimeException var25) {
         throw Class44.clientError(var25, "cl.C(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
      }
   }

   static long method557(int var0, int var1, int var2) {
      Class3_Sub2 var3 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2];
      if (var3 != null) {
         for (int var4 = 0; var4 < var3.anInt2223; ++var4) {
            Class25 var5 = var3.aClass25Array2221[var4];
            if ((var5.aLong498 >> 29 & 3L) == 2L && var5.anInt483 == var1 && var5.anInt478 == var2) {
               return var5.aLong498;
            }
         }

      }
      return 0L;
   }

   public static void method558(int var0) {
      try {
         anIntArray3592 = null;
         aClass153_3580 = null;
         anIntArray3587 = null;
         if(var0 != -29679) {
            method556(-76, 24, -17, (byte)-85, 58, -87);
         }
         aCalendar3581 = null;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "cl.B(" + var0 + ')');
      }
   }

   final boolean method559(int var2) {
      try {
         return this.animations[var2].aBoolean1386;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "cl.D(" + 1317095745 + ',' + var2 + ')');
      }
   }

   static void method560() {
      try {
         for(int var1 = 0; var1 < 5; ++var1) {
            Class104.aBooleanArray2169[var1] = false;
         }

         Class75.anInt1105 = 0;
         Class163_Sub2_Sub1.anInt4014 = 0;
         NPCDefinition.anInt1252 = -1;
         Class3_Sub7.anInt2293 = -1;
         Class133.anInt1753 = 1;
      } catch (RuntimeException var2) {
         throw Class44.clientError(var2, "cl.A(" + -21556 + ')');
      }
   }

   /*
    * public static void decodeNew(int file) {
		try {
			ByteBuffer buffer = buffers[file];

			if (buffer != null) {

				buffer.position(1);

				int skin_id = buffer.getShort() & 0xFFFF;

				if (skin_id == 0) {
					return;
				}

				Skins skin = new Skins(0, SignLink.cache.read(1, skin_id).getData());

				ByteBuffer parent = ByteBuffer.wrap(buffer.array());

				parent.position(3);//skip the first 3 bytes, like the 666 client does.

				int children = parent.get() & 0xFF;

				Animation[] headers = new Animation[children];

				ByteBuffer child = ByteBuffer.wrap(parent.array());
				child.position(parent.position() + children);


				int vertice_amt = 0;
				int unflagged_vertex = -1;
				int flagged_vertex = -1;

				for (int i = 0; i < children; i++) {

					Animation animation = headers[i] = new Animation();

					int type = skin.anIntArray646[i];

					if (type == 0) {
						unflagged_vertex = i;
					}

					int flags = child.get() & 0xFF;

					if (flags <= 0) {
						continue;
					}

					if (type == 0) {
						flagged_vertex = i;
					}

					vertex_offset_copy[vertice_amt] = i;

					int static_frame = type == 3 || type == 10 ? 128 : 0;

					x_buff_copy[vertice_amt] = (flags & 0x1) != 0 ? BufferUtils.getUnsignedSmart(child) : static_frame;
					y_buff_copy[vertice_amt] = (flags & 0x2) != 0 ? BufferUtils.getUnsignedSmart(child) : static_frame;
					z_buff_copy[vertice_amt] = (flags & 0x4) != 0 ? BufferUtils.getUnsignedSmart(child) : static_frame;

					xyz_flag_copy[vertice_amt] = (byte) (flags >>> 3 & 0x3);

					if (type == 2 || type == 9) {
						x_buff_copy[vertice_amt] = x_buff_copy[vertice_amt] << 2 & 0x3fff;
						y_buff_copy[vertice_amt] = y_buff_copy[vertice_amt] << 2 & 0x3fff;
						z_buff_copy[vertice_amt] = z_buff_copy[vertice_amt] << 2 & 0x3fff;
					}

					if (type == 1 || type == 2 || type == 3 && (unflagged_vertex > flagged_vertex)) {
						skin_vertices[vertice_amt] = unflagged_vertex;
						flagged_vertex = unflagged_vertex;
					} else {
						skin_vertices[vertice_amt] = -1;
					}

					if (type == 5) {
						animation.aBoolean688 = true;
					} else if (type == 7) {
						animation.aBoolean684 = true;
					} else if (type == 8 || type == 9 || type == 10) {
						animation.aBoolean681 = true;
					}

					vertice_amt++;
				}

				//resize arrays and copy into them.

				for (Animation animation : headers) {
					animation.vertices = vertice_amt;
					animation.xBuffer = new int[vertice_amt];
					animation.yBuffer = new int[vertice_amt];
					animation.zBuffer = new int[vertice_amt];
					animation.skinVertices = new int[vertice_amt];
					animation.xyzFlags = new int[vertice_amt];
					animation.vertexOffsets = new int[vertice_amt];

					for (int vextex = 0; vextex < vertice_amt; vextex++) {
						animation.xBuffer[vextex] = x_buff_copy[vextex];
						animation.yBuffer[vextex] = y_buff_copy[vextex];
						animation.zBuffer[vextex] = z_buff_copy[vextex];
						animation.skinVertices[vextex] = skin_vertices[vextex];
						animation.xyzFlags[vextex] = xyz_flag_copy[vextex];
						animation.vertexOffsets[vextex] = vertex_offset_copy[vextex];
					}
				}
				cache[file] = headers;
			} else {
				System.out.println("[Animation] Unrecognized skeleton file request.");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
    */
   //Animation header
   Class3_Sub28_Sub5(CacheIndex cacheIndex0, CacheIndex var2, int frame) {
      try {
         Class61 var5 = new Class61();
         int size = cacheIndex0.getFileAmount(frame, (byte)69);
         this.animations = new AnimationHeader[size];
         int[] fileIds = cacheIndex0.getFileIds(frame);
         int var8 = 0;

         while(Objects.requireNonNull(fileIds).length > var8) {
            byte[] var9 = cacheIndex0.getFile(frame, fileIds[var8]);
            int skinId = 255 & var9[1] | (var9[0] & 255) << 8;
            Class3_Sub17 var12 = (Class3_Sub17) var5.method1222();
            Class3_Sub17 skin = null;

            while(true) {
               if(var12 != null) {
                  if(var12.anInt2454 != skinId) {
                     var12 = (Class3_Sub17)var5.method1221();
                     continue;
                  }

                  skin = var12;
               }

               if(null == skin) {
                  byte[] var13;
                  var13 = var2.method2140(0, skinId);

                  skin = new Class3_Sub17(skinId, var13);
                  var5.method1215(skin);
               }

               this.animations[fileIds[var8]] = new AnimationHeader(var9, skin);
               ++var8;
               break;
            }
         }

      } catch (RuntimeException var14) {
         throw Class44.clientError(var14, "cl.<init>(" + (cacheIndex0 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ',' + frame + ',' + false + ')');
      }
   }

   final boolean method561(int var1, byte var2) {
      try {
         return var2 < 114 || this.animations[var1].aBoolean1382;
      } catch (RuntimeException var4) {
         throw Class44.clientError(var4, "cl.F(" + var1 + ',' + var2 + ')');
      }
   }

}
