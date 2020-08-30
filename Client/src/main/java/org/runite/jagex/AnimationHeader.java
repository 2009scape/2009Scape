package org.runite.jagex;


final class AnimationHeader {

	boolean aBoolean1382 = false;
	int anInt1383;
	Class3_Sub17 skins;
	short[] aShortArray1385;
	boolean aBoolean1386 = false;
	short[] aShortArray1387;
	short[] aShortArray1388;
	private static byte[] aByteArray1389 = new byte[500];
	private static short[] aShortArray1390 = new short[500];
	private static short[] aShortArray1391 = new short[500];
	private static short[] aShortArray1392 = new short[500];
	byte[] aByteArray1393;
	private static short[] aShortArray1394 = new short[500];
	short[] aShortArray1395;
	short[] aShortArray1396;
	private static short[] aShortArray1397 = new short[500];


	public static void method1595() {
		aShortArray1397 = null;
		aShortArray1394 = null;
		aShortArray1391 = null;
		aShortArray1390 = null;
		aShortArray1392 = null;
		aByteArray1389 = null;
	}

//	AnimationHeader(byte[] bs, Class3_Sub17 skin) {
//		this.skins = skin;
//		RSByteBuffer buffer = new RSByteBuffer(bs);
//		RSByteBuffer parent = new RSByteBuffer(bs);
//		buffer.index = 2;
//		int size = buffer.getByte((byte)-86);
//		int var6 = 0;
//		int var7 = -1;
//		parent.index = buffer.index + size;
//
//		int i;
//		for(i = 0; i < size; ++i) {
//
//			int var11 = buffer.getByte((byte)-55);
//			if(var11 > 0) {
//				int var10 = this.skins.anIntArray2466[i];
//				if (skins.anIntArray2466[i] != 0) {
//					for (int j = i - 1; j > var7; j--) {
//						if (skins.anIntArray2466[j] == 0) {
//							aShortArray1397[var7] = (short) j;
//							aShortArray1394[var7] = 0;
//							aShortArray1391[var7] = 0;
//							aShortArray1390[var7] = 0;
//							var6++;
//							break;
//						}
//					}
//					aShortArray1397[var6] = (short)i;
//					short var12 = 0;
//					if(var10 == 3) {
//						var12 = 128;
//					}
//
//					if((var11 & 1) != 0) {
//						aShortArray1394[var6] = (short)parent.getSmart(-21208);
//					} else {
//						aShortArray1394[var6] = var12;
//					}
//
//					if((var11 & 2) != 0) {
//						aShortArray1391[var6] = (short)parent.getSmart(-21208);
//					} else {
//						aShortArray1391[var6] = var12;
//					}
//
//					if((var11 & 4) != 0) {
//						aShortArray1390[var6] = (short)parent.getSmart(-21208);
//					} else {
//						aShortArray1390[var6] = var12;
//					}
//
//					aByteArray1389[var6] = (byte)(var11 >>> 3 & 3);
//					if(var10 == 2) {
//						aShortArray1394[var6] = (short)(((aShortArray1394[var6] & 255) << 3) + (aShortArray1394[var6] >> 8 & 7));
//						aShortArray1391[var6] = (short)(((aShortArray1391[var6] & 255) << 3) + (aShortArray1391[var6] >> 8 & 7));
//						aShortArray1390[var6] = (short)(((aShortArray1390[var6] & 255) << 3) + (aShortArray1390[var6] >> 8 & 7));
//					}
//					var7 = i;
//					if(var10 != 1 && var10 != 2 && var10 != 3) {
//						if(var10 == 5) {
//							this.aBoolean1386 = true;
//						} else if(var10 == 7) {
//							this.aBoolean1382 = true;
//						}
//					}
//
//					++var6;
//				}
//			}
//
//			if(parent.index != bs.length) {
//				throw new RuntimeException();
//			} else {
//				this.anInt1383 = var6;
//				this.aShortArray1385 = new short[var6];
//				this.aShortArray1388 = new short[var6];
//				this.aShortArray1396 = new short[var6];
//				this.aShortArray1395 = new short[var6];
//				this.aShortArray1387 = new short[var6];
//				this.aByteArray1393 = new byte[var6];
//
//				for(i = 0; i < var6; ++i) {
//					this.aShortArray1385[i] = aShortArray1397[i];
//					this.aShortArray1388[i] = aShortArray1394[i];
//					this.aShortArray1396[i] = aShortArray1391[i];
//					this.aShortArray1395[i] = aShortArray1390[i];
//					this.aShortArray1387[i] = aShortArray1392[i];
//					this.aByteArray1393[i] = aByteArray1389[i];
//				}
//
//			}
//		}
//	}
		   AnimationHeader(byte[] bs, Class3_Sub17 skin) {
		      this.skins = skin;
		      RSByteBuffer buffer = new RSByteBuffer(bs);
		      RSByteBuffer parent = new RSByteBuffer(bs);
		      buffer.index = 2;
		      int size = buffer.getByteB();
		      int var6 = 0;
		      int var7 = -1;
		      int var8 = -1;
		      parent.index = buffer.index + size;

		      int i;
		      for(i = 0; i < size; ++i) {
		         int var10 = this.skins.anIntArray2466[i];
		         if(var10 == 0) {
		            var7 = i;
		         }
		         int var11 = buffer.getByteB();
		         if(var11 > 0) {
		            if(var10 == 0) {
		               var8 = i;
		            }

		            aShortArray1397[var6] = (short)i;
		            short var12 = 0;
		            if(var10 == 3) {
		               var12 = 128;
		            }

		            if((var11 & 1) == 0) {
		               aShortArray1394[var6] = var12;
		            } else {
		               aShortArray1394[var6] = (short)parent.getSmart(-21208);
		            }

                     if((var11 & 2) == 0) {
                        aShortArray1391[var6] = var12;
                     } else {
                        aShortArray1391[var6] = (short)parent.getSmart(-21208);
                     }

                     if((var11 & 4) == 0) {
                        aShortArray1390[var6] = var12;
                     } else {
                        aShortArray1390[var6] = (short)parent.getSmart(-21208);
                     }

                     aByteArray1389[var6] = (byte)(var11 >>> 3 & 3);
		            if(var10 == 2) {
		               aShortArray1394[var6] = (short)(((aShortArray1394[var6] & 255) << 3) + (aShortArray1394[var6] >> 8 & 7));
		               aShortArray1391[var6] = (short)(((aShortArray1391[var6] & 255) << 3) + (aShortArray1391[var6] >> 8 & 7));
		               aShortArray1390[var6] = (short)(((aShortArray1390[var6] & 255) << 3) + (aShortArray1390[var6] >> 8 & 7));
		            }

		            aShortArray1392[var6] = -1;
		            if(var10 != 1 && var10 != 2 && var10 != 3) {
		               if(var10 == 5) {
		                  this.aBoolean1386 = true;
		               } else if(var10 == 7) {
		                  this.aBoolean1382 = true;
		               }
		            } else if(var7 > var8) {
		               aShortArray1392[var6] = (short)var7;
		               var8 = var7;
		            }

		            ++var6;
		         }
		      }

		      if(parent.index == bs.length) {
		         this.anInt1383 = var6;
		         this.aShortArray1385 = new short[var6];
		         this.aShortArray1388 = new short[var6];
		         this.aShortArray1396 = new short[var6];
		         this.aShortArray1395 = new short[var6];
		         this.aShortArray1387 = new short[var6];
		         this.aByteArray1393 = new byte[var6];

		         for(i = 0; i < var6; ++i) {
		            this.aShortArray1385[i] = aShortArray1397[i];
		            this.aShortArray1388[i] = aShortArray1394[i];
		            this.aShortArray1396[i] = aShortArray1391[i];
		            this.aShortArray1395[i] = aShortArray1390[i];
		            this.aShortArray1387[i] = aShortArray1392[i];
		            this.aByteArray1393[i] = aByteArray1389[i];
		         }

		      } else {
		         throw new RuntimeException();
		      }
           }

	}
