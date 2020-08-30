package org.runite.jagex;
import org.runite.Configurations;

import java.math.BigInteger;
import java.util.Objects;

class RSByteBuffer extends Class3 {

	byte[] buffer;
	static int[] anIntArray2591;
	int index;
	static RSString[] aClass94Array2596 = null;
	static RSString aClass94_2598 = RSString.createRSString("<br>");
	static Class3_Sub28_Sub3 aClass3_Sub28_Sub3_2600;
	static Class151_Sub1[] aClass151_Sub1Array2601 = new Class151_Sub1[29]; //TODO


	final int getShort() {
		try {
			// TODO: PROBABLY SAFE TO YEET. IT LOOKS LIKE BULLSHIT obfus code

			this.index += 2;
			return (this.buffer[-2 + this.index] << 8 & '\uff00') + (this.buffer[-1 + this.index] & 255);
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.EB(" + ')');
		}
	}

	final void putInt(int var1, int value) {
		try {
			if(var1 > -119) {
				TextCore.LoadedInterfaces = (RSString)null;
			}

			this.buffer[this.index++] = (byte)(value >> 24);
			this.buffer[this.index++] = (byte)(value >> 16);
			this.buffer[this.index++] = (byte)(value >> 8);
			this.buffer[this.index++] = (byte)value;
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.UC(" + var1 + ',' + value + ')');
		}
	}

	final void method739(int var2, long var3) {
		try {
			--var2;
			if(var2 >= 0 && var2 <= 7) {
				for(int var5 = var2 * 8; 0 <= var5; var5 -= 8) {
					this.buffer[this.index++] = (byte)((int)(var3 >> var5));
				}

			} else {
				throw new IllegalArgumentException();
			}
		} catch (RuntimeException var6) {
			throw Class44.clientError(var6, "wa.OB(" + 0 + ',' + var2 + ',' + var3 + ')');
		}
	}

	final void putLong(long var1, int var3) {
		try {
			this.buffer[this.index++] = (byte)((int)(var1 >> 56));
			this.buffer[this.index++] = (byte)((int)(var1 >> 48));
			if(var3 == -2037491440) {
				this.buffer[this.index++] = (byte)((int)(var1 >> 40));
				this.buffer[this.index++] = (byte)((int)(var1 >> 32));
				this.buffer[this.index++] = (byte)((int)(var1 >> 24));
				this.buffer[this.index++] = (byte)((int)(var1 >> 16));
				this.buffer[this.index++] = (byte)((int)(var1 >> 8));
				this.buffer[this.index++] = (byte)((int)var1);
			}
		} catch (RuntimeException var5) {
			throw Class44.clientError(var5, "wa.AC(" + var1 + ',' + var3 + ')');
		}
	}

	final int method741(byte var1) {
		try {
			byte var2 = this.buffer[this.index++];

			int var3;
			for(var3 = 0; 0 > var2; var2 = this.buffer[this.index++]) {
				var3 = (127 & var2 | var3) << 7;
			}

			return var2 | var3;
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.WA(" + var1 + ')');
		}
	}

	final void method742(int var2) {
		try {
			this.buffer[-4 + this.index + -var2] = (byte)(var2 >> 24);
			this.buffer[-var2 + this.index - 3] = (byte)(var2 >> 16);
			this.buffer[-2 + this.index + -var2] = (byte)(var2 >> 8);

			this.buffer[-var2 + this.index + -1] = (byte)var2;
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.VB(" + 83 + ',' + var2 + ')');
		}
	}

	final void putByteS(int var2) {
		try {
			this.buffer[this.index++] = (byte)(-var2 + 128);

		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.PC(" + 10213 + ',' + var2 + ')');
		}
	}

	static void method744() {
		try {

			++KeyboardListener.anInt1908;
		} catch (RuntimeException var2) {
			throw Class44.clientError(var2, "wa.HA(" + true + ')');
		}
	}

	final void putString(RSString var2) {
		try {
			this.index += var2.method1580(this.buffer, this.index, var2.length(-65));
			this.buffer[this.index++] = 0;
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.LA(" + 0 + ',' + (var2 != null?"{...}":"null") + ')');
		}
	}

	static void method746(byte var0) {
		try {
			Class67.aClass93_1013.method1524();
		} catch (RuntimeException var2) {
			throw Class44.clientError(var2, "wa.VC(" + var0 + ')');
		}
	}

	final int getShortAs() {
		try {
			this.index += 2;
			int var2 = (this.buffer[-2 + this.index] << 8 & '\uff00') - -(-128 + this.buffer[this.index + -1] & 255);

			if(var2 > 32767) {
				var2 -= 65536;
			}

			return var2;
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.NA(" + -58 + ')');
		}
	}

	final int getInt() {
		try {
			this.index += 4;
			return ((255 & this.buffer[this.index - 4]) << 24) + (16711680 & this.buffer[this.index + -3] << 16) + (((255 & this.buffer[this.index - 2]) << 8) - -(this.buffer[this.index - 1] & 255));
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.KA(" + -1 + ')');
		}
	}

	final byte method749() {
		try {
			return (byte)(-this.buffer[this.index++] + 128);
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.JB(" + false + ')');
		}
	}

	final RSString method750() {
		try {
			if(this.buffer[this.index] == 0) {
				++this.index;
				return null;
			} else {
				return this.getString();
			}
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.IB(" + (byte) 78 + ')');
		}
	}

	final int getByteA(byte var1) {
		try {
			return 255 & this.buffer[this.index++] - 128;
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.DC(" + var1 + ')');
		}
	}
	

	final void putByte(byte var1, int var2) {
		try {
			this.buffer[this.index++] = (byte)var2;
			if(var1 >= -5) {
				this.method757(-77, -126);
			}

		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.RA(" + var1 + ',' + var2 + ')');
		}
	}

	final void putBytes(byte[] var1, int var3, int var4) {
		try {
			int var5 = 0;
			if(var4 >= 37) {
				while(var3 > var5) {
					this.buffer[this.index++] = var1[var5];
					++var5;
				}

			}
		} catch (RuntimeException var6) {
			throw Class44.clientError(var6, "wa.QC(" + (var1 != null?"{...}":"null") + ',' + 0 + ',' + var3 + ',' + var4 + ')');
		}
	}

	final int getByteS() {
		try {

			return -this.buffer[this.index++] + 128 & 255;
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.PB(" + true + ')');
		}
	}

	final int getTriByte2() {
		try {
			this.index += 3;
			return ((this.buffer[this.index + -2] & 255) << 8) + ((this.buffer[-1 + this.index] & 255) << 16) + (this.buffer[-3 + this.index] & 255);
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.BA(" + (byte) -118 + ')');
		}
	}

	final long getLong(int var1) {
		try {
			if(var1 > -75) {
				return -1L;
			} else {
				long var2 = (long)this.getInt() & 4294967295L;
				long var4 = 4294967295L & (long)this.getInt();
				return var4 + (var2 << 32);
			}
		} catch (RuntimeException var6) {
			throw Class44.clientError(var6, "wa.GA(" + var1 + ')');
		}
	}

	final void method757(int var1, int var2) {
		try {
			this.buffer[this.index++] = (byte)var1;
			if(var2 < 54) {
				this.method749();
			}

			this.buffer[this.index++] = (byte)(var1 >> 8);
			this.buffer[this.index++] = (byte)(var1 >> 16);
			this.buffer[this.index++] = (byte)(var1 >> 24);
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.AB(" + var1 + ',' + var2 + ')');
		}
	}

	final int getShortA(int var1) {
		try {
			this.index += 2;
			return (this.buffer[-1 + this.index] - 128 & 255) + ('\uff00' & this.buffer[-2 + this.index] << 8);
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.LC(" + var1 + ')');
		}
	}
	

	final void putIntB(int var1, int var2) {
		try {
			this.buffer[this.index++] = (byte)(var2 >> 16);
			this.buffer[this.index++] = (byte)(var2 >> 24);
			if(var1 >= -56) {
				this.method768(82, 22);
			}

			this.buffer[this.index++] = (byte)var2;
			this.buffer[this.index++] = (byte)(var2 >> 8);
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.MC(" + var1 + ',' + var2 + ')');
		}
	}

	RSByteBuffer(int var1) {
		try {
			this.buffer = Class134.method1807(var1);
			this.index = 0;
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.<init>(" + var1 + ')');
		}
	}

	final byte getByte() {
		try {
			return this.buffer[this.index++];
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.TA(" + -1 + ")");
		}
	}

	final RSString getGJString2(int var1) {
		try {
			byte var2 = this.buffer[this.index++];
			if(var1 < 50) {
				this.buffer = (byte[])null;
			}

			if(0 == var2) {
				int var3 = this.index;

				while(Objects.requireNonNull(this.buffer)[this.index++] != 0) {
				}

				return Class3_Sub13_Sub3.method178(this.buffer, this.index - (var3 - -1), var3);
			} else {
				throw new IllegalStateException("Bad version number in gjstr2");
			}
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.DA(" + var1 + ')');
		}
	}

	final void method762(float var1, byte var2) {
		try {
			int var3 = Float.floatToRawIntBits(var1);
			this.buffer[this.index++] = (byte)var3;
			if(var2 > 63) {
				this.buffer[this.index++] = (byte)(var3 >> 8);
				this.buffer[this.index++] = (byte)(var3 >> 16);
				this.buffer[this.index++] = (byte)(var3 >> 24);
			}
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.CC(" + var1 + ',' + var2 + ')');
		}
	}

	final byte method763(byte var1) {
		try {
			return var1 < 98?95:(byte)(-this.buffer[this.index++]);
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.BC(" + var1 + ')');
		}
	}

	final void method764(int var2, byte[] var3) {
		try {

			for(int var5 = 0; var2 > var5; ++var5) {
				var3[var5] = this.buffer[this.index++];
			}

		} catch (RuntimeException var6) {
			throw Class44.clientError(var6, "wa.SB(" + 0 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + (byte) 93 + ')');
		}
	}

	final void putLEShortA(int var1) {
		try {
			this.buffer[this.index++] = (byte)(var1 + 128);
			this.buffer[this.index++] = (byte)(var1 >> 8);
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.MA(" + var1 + ')');
		}
	}

	final int getLEShort(int var1) {
		try {
			this.index += 2;
			return var1 >= -54?-17:(255 & this.buffer[this.index - 2]) + ('\uff00' & this.buffer[this.index - 1] << 8);
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.BB(" + var1 + ')');
		}
	}

	public static void method767(int var0) {
		try {
			anIntArray2591 = null;
			aClass3_Sub28_Sub3_2600 = null;
			aClass94Array2596 = null;
			TextCore.LoadedInterfaces = null;
			aClass151_Sub1Array2601 = null;
			aClass94_2598 = null;
			if(var0 != 0) {
			}

		} catch (RuntimeException var2) {
			throw Class44.clientError(var2, "wa.IC(" + var0 + ')');
		}
	}

	final void method768(int var1, int var2) {
		try {
			if(var1 != -32769) {
				this.putInt(97, -9);
			}

			if(0 <= var2 && var2 < 128) {
				this.putByte((byte)-10, var2);
			} else if(0 <= var2 && var2 < 32768) {
				this.putShort('\u8000' + var2);
			} else {
				throw new IllegalArgumentException();
			}
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.UA(" + var1 + ',' + var2 + ')');
		}
	}

	final void method769(byte var1, int var2) {
		try {
			this.buffer[-1 + -var2 + this.index] = (byte)var2;
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.MB(" + var1 + ',' + var2 + ')');
		}
	}

	final void method770(int[] var1, int var4) {
		try {
			int var5 = this.index;
			this.index = 5;
			int var6 = (-5 + var4) / 8;

			for(int var7 = 0; var6 > var7; ++var7) {
				int var10 = -957401312;
				int var8 = this.getInt();
				int var9 = this.getInt();
				int var12 = 32;

				for(int var11 = -1640531527; var12-- > 0; var8 -= (var9 >>> 5 ^ var9 << 4) + var9 ^ var1[var10 & 3] + var10) {
					var9 -= var1[(6754 & var10) >>> 11] + var10 ^ var8 + (var8 >>> 5 ^ var8 << 4);
					var10 -= var11;
				}

				this.index -= 8;
				this.putInt(-122, var8);
				this.putInt(-121, var9);
			}

			this.index = var5;
		} catch (RuntimeException var13) {
			throw Class44.clientError(var13, "wa.SC(" + (var1 != null?"{...}":"null") + ',' + 120 + ',' + 5 + ',' + var4 + ')');
		}
	}

	final void method771(int var2) {
		try {
			if((-128 & var2) != 0) {
				if((-16384 & var2) != 0) {
					if((var2 & -2097152) != 0) {
						if(0 != (-268435456 & var2)) {
							this.putByte((byte)-64, var2 >>> 28 | 128);
						}

						this.putByte((byte)-80, 128 | var2 >>> 21);
					}

					this.putByte((byte)-100, 128 | var2 >>> 14);
				}

				this.putByte((byte)-21, var2 >>> 7 | 128);
			}

			this.putByte((byte)-46, var2 & 127);
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.DB(" + 17038 + ',' + var2 + ')');
		}
	}

	final long method772(int var1) {
		try {
			--var1;
			if(0 <= var1 && var1 <= 7) {
				long var4 = 0L;

				for(int var3 = var1 * 8; var3 >= 0; var3 -= 8) {
					var4 |= ((long)this.buffer[this.index++] & 255L) << var3;
				}

				return var4;
			} else {
				throw new IllegalArgumentException();
			}
		} catch (RuntimeException var6) {
			throw Class44.clientError(var6, "wa.VA(" + var1 + ',' + 0 + ')');
		}
	}

	final int method773(byte var1) {
		try {
			if(var1 >= -120) {
				return 3;
			} else {
				int var3 = this.getSmart();

				int var2;
				for(var2 = 0; 32767 == var3; var2 += 32767) {
					var3 = this.getSmart();
				}

				var2 += var3;
				return var2;
			}
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.UB(" + var1 + ')');
		}
	}

	final void method774(int var2, byte[] var3) {
		try {
			for(int var5 = -(-var2 - -1); var5 >= 0; --var5) {
				var3[var5] = this.buffer[this.index++];
			}

		} catch (RuntimeException var6) {
			throw Class44.clientError(var6, "wa.HC(" + 2 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + 0 + ')');
		}
	}

	final void putIntA(int var1) {
		try { 
			this.buffer[this.index++] = (byte)(var1 >> 8);
			this.buffer[this.index++] = (byte)var1;
			this.buffer[this.index++] = (byte)(var1 >> 24);
			this.buffer[this.index++] = (byte)(var1 >> 16);
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.GC(" + var1 + ')');
		}
	}

	final RSString getString() {
		try {
			int var2 = this.index;

			while(0 != this.buffer[this.index++]) {
			}

			return Class3_Sub13_Sub3.method178(this.buffer, -var2 + (this.index - 1), var2);
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.FC()");
		}
	}

	static void method777(Class91[] var0, boolean var1, int var2, int var3, int var5, int var6, byte[] var7) {
		try {
			int var10;
			int var11;
			if(!var1) {
				for(int var9 = 0; 4 > var9; ++var9) {
					for(var10 = 0; var10 < 64; ++var10) {
						for(var11 = 0; var11 < 64; ++var11) {
							if(var5 - -var10 > 0 && var10 + var5 < 103 && var3 + var11 > 0 && var11 + var3 < 103) {
								var0[var9].anIntArrayArray1304[var10 + var5][var3 - -var11] = Class69.bitwiseAnd(var0[var9].anIntArrayArray1304[var10 + var5][var3 - -var11], -16777217);
							}
						}
					}
				}
			}

			RSByteBuffer var20 = new RSByteBuffer(var7);
			byte var8;
			if(var1) {
				var8 = 1;
			} else {
				var8 = 4;
			}

			int var12;
			for(var10 = 0; var8 > var10; ++var10) {
				for(var11 = 0; var11 < 64; ++var11) {
					for(var12 = 0; 64 > var12; ++var12) {
						Class167.method2267(var2, var6, var1, var20, var12 - -var3, var5 + var11, (byte)91, 0, var10);
					}
				}
			}

			int var14;
			int var15;
			int var17;
			boolean var21;
			int var24;
			for(var21 = false; var20.index < var20.buffer.length; var21 = true) {
				var11 = var20.getByteB();
				if(var11 != 129) {
					--var20.index;
					break;
				}

				for(var12 = 0; var12 < 4; ++var12) {
					byte var13 = var20.getByte();
					if(0 == var13) {
						var14 = var5;
						if(var5 >= 0) {
							if(var5 >= 104) {
								var14 = 104;
							}
						} else {
							var14 = 0;
						}

						var24 = var3;
						if(var3 < 0) {
							var24 = 0;
						} else if(var3 >= 104) {
							var24 = 104;
						}

						var15 = 64 + var5;
						var17 = var3 + 64;
						if(var17 >= 0) {
							if(var17 >= 104) {
								var17 = 104;
							}
						} else {
							var17 = 0;
						}

						if(var15 < 0) {
							var15 = 0;
						} else if(var15 >= 104) {
							var15 = 104;
						}

						while(var15 > var14) {
							while(var24 < var17) {
								Class136.aByteArrayArrayArray1774[var12][var14][var24] = 0;
								++var24;
							}

							++var14;
						}
					} else if (1 == var13) {
						for (var14 = 0; var14 < 64; var14 += 4) {
							for (var15 = 0; var15 < 64; var15 += 4) {
								byte var16 = var20.getByte();

								for (var17 = var14 + var5; 4 + var5 + var14 > var17; ++var17) {
									for (int var18 = var3 + var15; 4 + var3 + var15 > var18; ++var18) {
										if (var17 >= 0 && var17 < 104 && 0 <= var18 && var18 < 104) {
											Class136.aByteArrayArrayArray1774[var12][var17][var18] = var16;
										}
									}
								}
							}
						}
					} else if (var13 == 2 && var12 > 0) {
						var15 = var5 + 64;
						var24 = var3;
						var17 = var3 + 64;
						if (var15 < 0) {
							var15 = 0;
						} else if (104 <= var15) {
							var15 = 104;
						}

						if (var3 >= 0) {
							if (var3 >= 104) {
								var24 = 104;
							}
						} else {
							var24 = 0;
						}

						if (var17 >= 0) {
							if (var17 >= 104) {
								var17 = 104;
							}
						} else {
							var17 = 0;
						}

						var14 = var5;
						if (var5 >= 0) {
							if (104 <= var5) {
								var14 = 104;
							}
						} else {
							var14 = 0;
						}

						while (var15 > var14) {
							while (var17 > var24) {
								Class136.aByteArrayArrayArray1774[var12][var14][var24] = Class136.aByteArrayArrayArray1774[var12 + -1][var14][var24];
								++var24;
							}

							++var14;
						}
					}
				}
			}

			int var23;
			if(HDToolKit.highDetail && !var1) {
				Class86 var22 = null;

				while(var20.buffer.length > var20.index) {
					var12 = var20.getByteB();
					if(var12 == 0) {
						var22 = new Class86(var20);
					} else {
						if(var12 != 1) {
//								throw
							new IllegalStateException().printStackTrace();
							return;//
						}

						var23 = var20.getByteB();
						if(0 < var23) {
							for(var14 = 0; var23 > var14; ++var14) {
								Class43 var25 = new Class43(var20);
								if(var25.anInt705 == 31) {
									Class57 var26 = Class81.method1401(var20.getShort());
									var25.method1060((byte)-67, var26.anInt896, var26.anInt908, var26.anInt899, var26.anInt907);
								}

								var25.anInt708 += var3 << 7;
								var25.anInt703 += var5 << 7;
								var17 = var25.anInt708 >> 7;
								var24 = var25.anInt703 >> 7;
								if(var24 >= 0 && 0 <= var17 && var24 < 104 && var17 < 104) {
									var25.aBoolean696 = 0 != (Class9.aByteArrayArrayArray113[1][var24][var17] & 2);
									var25.anInt697 = Class44.anIntArrayArrayArray723[var25.anInt704][var24][var17] + -var25.anInt697;
									Class68.method1264(var25);
								}
							}
						}
					}
				}

				if(var22 == null) {
					var22 = new Class86();
				}

				for(var12 = 0; var12 < 8; ++var12) {
					for(var23 = 0; var23 < 8; ++var23) {
						var14 = var12 + (var5 >> 3);
						var15 = (var3 >> 3) + var23;
						if(0 <= var14 && var14 < 13 && var15 >= 0 && var15 < 13) {
							Class115.aClass86ArrayArray1581[var14][var15] = var22;
						}
					}
				}
			}

			if(!var21) {
				for(var11 = 0; var11 < 4; ++var11) {
					for(var12 = 0; 16 > var12; ++var12) {
						for(var23 = 0; var23 < 16; ++var23) {
							var14 = (var5 >> 2) - -var12;
							var15 = var23 + (var3 >> 2);
							if(0 <= var14 && 26 > var14 && 0 <= var15 && var15 < 26) {
								Class136.aByteArrayArrayArray1774[var11][var14][var15] = 0;
							}
						}
					}
				}
			}

		} catch (RuntimeException var19) {
			throw Class44.clientError(var19, "wa.OA(" + (var0 != null?"{...}":"null") + ',' + var1 + ',' + var2 + ',' + var3 + ',' + 4 + ',' + var5 + ',' + var6 + ',' + (var7 != null?"{...}":"null") + ')');
		}
	}

	final int getSmart() {
		try {

			int var2 = this.buffer[this.index] & 255;
			return 128 <= var2?-32768 + this.getShort():this.getByteB();
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.JA(" + true + ')');
		}
	}

	final void putTriByte(int var1) {
		try {
			this.buffer[this.index++] = (byte)(var1 >> 16);
			this.buffer[this.index++] = (byte)(var1 >> 8);

			this.buffer[this.index++] = (byte)var1;
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.FB(" + var1 + ',' + 6517 + ')');
		}
	}

	final int getIntA(int var1) {
		try {
			this.index += 4;
			if(var1 != -1) {
				this.method769((byte)7, -47);
			}

			return ((this.buffer[this.index - 2] & 255) << 24) + ((255 & this.buffer[this.index - 1]) << 16) + ('\uff00' & this.buffer[-4 + this.index] << 8) - -(this.buffer[this.index + -3] & 255);
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.NB(" + var1 + ')');
		}
	}

	final int getLEShortA(byte var1) {
		try {
			this.index += 2;
			if(var1 > -84) {
				this.method749();
			}

			return (this.buffer[-1 + this.index] << 8 & '\uff00') - -(255 & -128 + this.buffer[this.index + -2]);
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.TB(" + var1 + ')');
		}
	}

	
	final int getLEInt(int var1) {
		try {
			this.index += 4;
			if(var1 >= -42) {
				this.method763((byte)-14);
			}

			return (255 & this.buffer[-4 + this.index]) + (16711680 & this.buffer[this.index - 2] << 16) + ((255 & this.buffer[this.index + -1]) << 24) + ((this.buffer[-3 + this.index] & 255) << 8);
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.EA(" + var1 + ')');
		}
	}

	final void putShortA(int var1) {
		try {
			this.buffer[this.index++] = (byte)(var1 >> 8);
			this.buffer[this.index++] = (byte)(128 + var1);
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.LB(" + var1 + ',' + -268435456 + ')');
		}
	}

	static Class12 method784(int var0, int var1, int var2) {
		Class3_Sub2 var3 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2];
		return var3 != null && var3.aClass12_2230 != null?var3.aClass12_2230:null;
	}

	RSByteBuffer(byte[] var1) {
		try {
			this.index = 0;
			this.buffer = var1;
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.<init>(" + (var1 != null?"{...}":"null") + ')');
		}
	}

	final void putLEInt(int var1, byte var2) {
		try {
			this.buffer[this.index++] = (byte)var1;
			this.buffer[this.index++] = (byte)(var1 >> 8);
			if(var2 <= -118) {
				this.buffer[this.index++] = (byte)(var1 >> 16);
				this.buffer[this.index++] = (byte)(var1 >> 24);
			}
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.IA(" + var1 + ',' + var2 + ')');
		}
	}

	final int getByteC() {
		try {

			return 255 & -this.buffer[this.index++];
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.CB(" + true + ')');
		}
	}

	final int getShort(byte var1) {
		try {
			this.index += 2;
			int var2 = (this.buffer[-1 + this.index] & 255) + ((255 & this.buffer[this.index + -2]) << 8);
			if(var1 < 4) {
				return -83;
			} else {
				if(var2 > 32767) {
					var2 -= 65536;
				}

				return var2;
			}
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.TC(" + var1 + ')');
		}
	}
	

	
	final int method788() {
		try {
			this.index += 2;
			int var2 = ((this.buffer[this.index - 1] & 255) << 8) - -(this.buffer[-2 + this.index] - 128 & 255);

			if(32767 < var2) {
				var2 -= 65536;
			}

			return var2;
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.EC(" + -1741292848 + ')');
		}
	}

	final byte method789() {
		try {
			return (byte)(-128 + this.buffer[this.index++]);
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.CA(" + 0 + ')');
		}
	}

	final void putByteA(int var1) {
		try {
			this.buffer[this.index++] = (byte)(128 + var1);
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.KC(" + var1 + ')');
		}
	}

	final int getLEShort() {
		try {
			this.index += 2;

			int var2 = (this.buffer[-2 + this.index] & 255) + ('\uff00' & this.buffer[this.index + -1] << 8);
			if(var2 > 32767) {
				var2 -= 65536;
			}

			return var2;
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.SA(" + (byte) 10 + ')');
		}
	}
	
	
	
	

	
	static void method792() {
		try {
			int var1 = Class137.method1817();
			if(0 == var1) {
				Class158.aByteArrayArrayArray2008 = (byte[][][])null;
				Class136.method1816(0, -7);
			} else if(var1 == 1) {
				Class3_Sub5.method112((byte)0);
				Class136.method1816(512, -7);
				Class3_Sub13_Sub18.method257();
			} else {
				Class3_Sub5.method112((byte)(-4 + Class79.anInt1127 & 255));
				Class136.method1816(2, -7);
			}

		} catch (RuntimeException var2) {
			throw Class44.clientError(var2, "wa.AA(" + 9179409 + ')');
		}
	}

	final void method793(int var2) {
		try {
			int var3 = Class99.method1599(var2, this.index, this.buffer, (byte)-49);
			this.putInt(-120, var3);
        } catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.QB(" + (byte) 86 + ',' + var2 + ')');
		}
	}

	final int getTriByte(byte var1) {
		try {
			if(var1 < 76) {
				this.getLEShort(53);
			}

			this.index += 3;
			return (16711680 & this.buffer[this.index + -3] << 16) + (('\uff00' & this.buffer[-2 + this.index] << 8) - -(this.buffer[this.index + -1] & 255));
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.GB(" + var1 + ')');
		}
	}

	static void method795() {
		try {

			CS2Script.aClass93_2450.method1522((byte) 14 ^ -114, 5);
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.OC(" + (byte) 14 + ',' + 5 + ')');
		}
	}

	final void putLEShort(int var2) {
		try {

			this.buffer[this.index++] = (byte)var2;
			this.buffer[this.index++] = (byte)(var2 >> 8);
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.PA(" + -1 + ',' + var2 + ')');
		}
	}

	final int getSmart(int var1) {
		try {
			int var2 = this.buffer[this.index] & 255;
			if(var1 != -21208) {
				TextCore.LoadedInterfaces = (RSString)null;
			}

			return var2 < 128 ?-64 + this.getByteB():this.getShort() - '\uc000';
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.WB(" + var1 + ')');
		}
	}

	final int getIntB(byte var1) {
		try {
			this.index += 4;
			return ((this.buffer[-3 + this.index] & 255) << 24) - -(16711680 & this.buffer[this.index + -4] << 16) + (((this.buffer[this.index + -1] & 255) << 8) - -(255 & this.buffer[this.index + -2]));
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.NC(" + var1 + ')');
		}
	}

	static void method799(int var0, int var2, int var3, int var4) {
		try {
			if(Class140_Sub3.anInt2737 < 100) {
				Class3_Sub28_Sub14.method626();
			}

			if(HDToolKit.highDetail) {
				Class22.method935(var0, var2, var0 + var4, var3 + var2);
			} else {
				Class74.method1324(var0, var2, var0 + var4, var3 + var2);
			}

			int var6;
			int var7;
			if(Class140_Sub3.anInt2737 >= 100) {
				Class17.anInt410 = (int)((float)(var3 * 2) / Class44.aFloat727);
				Class60.anInt930 = Class3_Sub28_Sub1.anInt3536 + -((int)((float)var4 / Class44.aFloat727));
				int var15 = -((int)((float)var4 / Class44.aFloat727)) + Class3_Sub28_Sub1.anInt3536;
				var6 = Class3_Sub4.anInt2251 - (int)((float)var3 / Class44.aFloat727);
				Class60.anInt934 = Class3_Sub4.anInt2251 + -((int)((float)var3 / Class44.aFloat727));
				int var8 = Class3_Sub4.anInt2251 + (int)((float)var3 / Class44.aFloat727);
				var7 = (int)((float)var4 / Class44.aFloat727) + Class3_Sub28_Sub1.anInt3536;
				Class49.anInt817 = (int)((float)(var4 * 2) / Class44.aFloat727);

				if(HDToolKit.highDetail) {
					if(Class3_Sub13_Sub19.aClass3_Sub28_Sub16_Sub2_3221 == null || var4 != Class3_Sub13_Sub19.aClass3_Sub28_Sub16_Sub2_3221.anInt3707 || var3 != Class3_Sub13_Sub19.aClass3_Sub28_Sub16_Sub2_3221.anInt3696) {
						Class3_Sub13_Sub19.aClass3_Sub28_Sub16_Sub2_3221 = null;
						Class3_Sub13_Sub19.aClass3_Sub28_Sub16_Sub2_3221 = new Class3_Sub28_Sub16_Sub2(var4, var3);
					}

					Class74.method1319(Class3_Sub13_Sub19.aClass3_Sub28_Sub16_Sub2_3221.anIntArray4081, var4, var3);
					Node.method523(var4, 0, var7, var6, 0, var8, var3, var15);
					Class23.method938(var4, 0, var7, var8, var3, 0, var15, var6);
					Class3_Sub5.method111((byte)-54, 0, 0, var15, var4, var8, var6, var7, var3);
					Class22.method926(Class3_Sub13_Sub19.aClass3_Sub28_Sub16_Sub2_3221.anIntArray4081, var0, var2, var4, var3);
					Class74.anIntArray1100 = null;
				} else {
					Node.method523(var4 + var0, var2, var7, var6, var0, var8, var2 - -var3, var15);
					Class23.method938(var0 + var4, var0, var7, var8, var3 + var2, var2, var15, var6);
					Class3_Sub5.method111((byte)-100, var0, var2, var15, var0 - -var4, var8, var6, var7, var3 + var2);
				}

				if(0 < Class3_Sub28_Sub16.anInt3704) {
					--Class3_Sub28_Sub8.anInt3611;
					if(Class3_Sub28_Sub8.anInt3611 == 0) {
						Class3_Sub28_Sub8.anInt3611 = 20;
						--Class3_Sub28_Sub16.anInt3704;
					}
				}

				if(Class20.aBoolean438) {
					int var10 = -8 + var2 - -var3;
					int var9 = -5 + (var0 - -var4);
					Class126.aClass3_Sub28_Sub17_1669.method688(RenderAnimationDefinition.method903(new RSString[]{Class65.aClass94_985, Class72.method1298((byte)9, AnimationDefinition.anInt1862)}, (byte)-62), var9, var10, 16776960, -1);
					Runtime var11 = Runtime.getRuntime();
					int var12 = (int)((var11.totalMemory() - var11.freeMemory()) / 1024L);
					int var13 = 16776960;
					var10 -= 15;
					if(var12 > 65536) {
						var13 = 16711680;
					}

					Class126.aClass3_Sub28_Sub17_1669.method688(RenderAnimationDefinition.method903(new RSString[]{Class119.aClass94_1630, Class72.method1298((byte)9, var12), Class3_Sub13_Sub4.aClass94_3055}, (byte)-108), var9, var10, var13, -1);
				}

			} else {
				byte var5 = 20;
				var6 = var0 - -(var4 / 2);
				var7 = var3 / 2 + (var2 - 18) + -var5;
				if(HDToolKit.highDetail) {
					Class22.method934(var0, var2, var4, var3, 0);
					Class22.method927(var6 - 152, var7, 304, 34, 9179409);
					Class22.method927(var6 + -151, var7 + 1, 302, 32, 0);
					Class22.method934(-150 + var6, var7 + 2, 3 * Class140_Sub3.anInt2737, 30, 9179409);
					Class22.method934(-150 + var6 + Class140_Sub3.anInt2737 * 3, var7 - -2, 300 + -(3 * Class140_Sub3.anInt2737), 30, 0);
				} else {
					Class74.method1323(var0, var2, var4, var3, 0);
					Class74.method1311(var6 + -152, var7, 304, 34, 9179409);
					Class74.method1311(var6 + -151, 1 + var7, 302, 32, 0);
					Class74.method1323(var6 + -150, var7 + 2, Class140_Sub3.anInt2737 * 3, 30, 9179409);
					Class74.method1323(3 * Class140_Sub3.anInt2737 + -150 + var6, var7 - -2, -(Class140_Sub3.anInt2737 * 3) + 300, 30, 0);
				}

				Class168.aClass3_Sub28_Sub17_2096.method699(TextCore.LoadingGeneral, var6, var5 + var7, 16777215, -1);
			}
		} catch (RuntimeException var14) {
			throw Class44.clientError(var14, "wa.FA(" + var0 + ',' + 64 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
		}
	}

	final void encryptRSA(BigInteger var1, BigInteger var2) {
		try {
			int var4 = this.index;
			this.index = 0;
			byte[] var5 = new byte[var4];
			this.method764(var4, var5);
			BigInteger var6 = new BigInteger(var5);
			BigInteger var7;
			if (Configurations.USE_RSA)
				var7 = var6.modPow(var1, var2);
			else
				var7 = var6; 
			byte[] var8 = var7.toByteArray();
			this.index = 0;
			this.putByte((byte)-23, var8.length);
			this.putBytes(var8, var8.length, -296 + 348);
		} catch (RuntimeException var9) {
			throw Class44.clientError(var9, "wa.KB(" + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ',' + -296 + ')');
		}
	}

	final void method801(float var2) {
		try {
			int var3 = Float.floatToRawIntBits(var2);
			this.buffer[this.index++] = (byte)(var3 >> 24);
			this.buffer[this.index++] = (byte)(var3 >> 16);
			this.buffer[this.index++] = (byte)(var3 >> 8);
			this.buffer[this.index++] = (byte)var3;
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.QA(" + 881 + ',' + var2 + ')');
		}
	}

	static RSString method802(int var0) {
		try {

			return Class163_Sub2_Sub1.aClass94Array4016[var0].length(-26) > 0 ?RenderAnimationDefinition.method903(new RSString[]{Class140_Sub7.aClass94Array2935[var0], TextCore.Spacer, Class163_Sub2_Sub1.aClass94Array4016[var0]}, (byte)-86):Class140_Sub7.aClass94Array2935[var0];
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.RB(" + var0 + ',' + true + ')');
		}
	}

	final int getByteB() {
		try {
			return this.buffer[this.index++] & 255;
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "wa.RC(" + ')');
		}
	}

	final void putShort(int var2) {
		try {
			this.buffer[this.index++] = (byte)(var2 >> 8);
			this.buffer[this.index++] = (byte)var2;
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "wa.JC('" +  var2 + ')');
		}
	}

}
