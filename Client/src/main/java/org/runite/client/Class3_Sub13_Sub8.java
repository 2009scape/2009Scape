package org.runite.client;

import org.rs09.client.util.ArrayUtils;

import java.util.Objects;

final class Class3_Sub13_Sub8 extends Class3_Sub13 {

    static CacheIndex aClass153_1967;
    private boolean aBoolean3100 = true;
	static int anInt3102 = 0;
	static int anInt3103;
	private boolean aBoolean3104 = true;
	static float aFloat3105;


	final void method157(int var1, DataBuffer var2, boolean var3) {
		try {
			if(!var3) {
				method207(18, false, -19, 102L);
			}

			if(var1 == 0) {
				this.aBoolean3100 = var2.readUnsignedByte() == 1;
			} else if(var1 == 1) {
				this.aBoolean3104 = var2.readUnsignedByte() == 1;
			} else if(var1 == 2) {
				this.aBoolean2375 = var2.readUnsignedByte() == 1;
			}

		} catch (RuntimeException var5) {
			throw ClientErrorException.clientError(var5, "ej.A(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
		}
	}

	final int[][] method166(int var1, int var2) {
		try {
			if(var1 != -1) {
				method207(-98, true, 95, 79L);
			}

			int[][] var3 = this.aClass97_2376.method1594((byte)54, var2);
			if(this.aClass97_2376.aBoolean1379) {
				int[][] var4 = this.method162(!this.aBoolean3104?var2:-var2 + Class3_Sub20.anInt2487, 0, (byte)-105);
				int[] var5 = Objects.requireNonNull(var4)[0];
				int[] var7 = var4[2];
				int[] var6 = var4[1];
				int[] var9 = var3[1];
				int[] var10 = var3[2];
				int[] var8 = var3[0];
				int var11;
				if(this.aBoolean3100) {
					for(var11 = 0; Class113.anInt1559 > var11; ++var11) {
						var8[var11] = var5[RenderAnimationDefinition.anInt396 + -var11];
						var9[var11] = var6[-var11 + RenderAnimationDefinition.anInt396];
						var10[var11] = var7[RenderAnimationDefinition.anInt396 - var11];
					}
				} else {
					for(var11 = 0; var11 < Class113.anInt1559; ++var11) {
						var8[var11] = var5[var11];
						var9[var11] = var6[var11];
						var10[var11] = var7[var11];
					}
				}
			}

			return var3;
		} catch (RuntimeException var12) {
			throw ClientErrorException.clientError(var12, "ej.T(" + var1 + ',' + var2 + ')');
		}
	}

	static void method203(int var0) {
		try {
			//int var1 = 15 / ((-11 - var0) / 63);
			if(Unsorted.anInt3660 == 2) {
				if(Class3_Sub13_Sub39.anInt3460 == NPCDefinition.anInt1297 && Class38_Sub1.anInt2612 == Class168.anInt2099) {
					Unsorted.anInt3660 = 0;
					if(ClientCommands.shiftClickEnabled && ObjectDefinition.aBooleanArray1490[81] && Unsorted.menuOptionCount > 2) {
						Class3_Sub30_Sub1.method806(Unsorted.menuOptionCount + -2);
					} else {
						Class3_Sub30_Sub1.method806(Unsorted.menuOptionCount + -1);
					}
				}
			} else if(NPCDefinition.anInt1297 == Class163_Sub1.anInt2993 && Class38_Sub1.anInt2614 == Class38_Sub1.anInt2612) {
				Unsorted.anInt3660 = 0;
				if(ClientCommands.shiftClickEnabled && ObjectDefinition.aBooleanArray1490[81] && Unsorted.menuOptionCount > 2) {
					Class3_Sub30_Sub1.method806(Unsorted.menuOptionCount - 2);
				} else {
					Class3_Sub30_Sub1.method806(Unsorted.menuOptionCount - 1);
				}
			} else {
				Class168.anInt2099 = Class38_Sub1.anInt2614;
				Unsorted.anInt3660 = 2;
				Class3_Sub13_Sub39.anInt3460 = Class163_Sub1.anInt2993;
			}

		} catch (RuntimeException var2) {
			throw ClientErrorException.clientError(var2, "ej.B(" + var0 + ')');
		}
	}

	static void method204(int var0) {
		try {
			//Client Resize.
			Class3_Sub13_Sub1.outgoingBuffer.putOpcode(243);
			Class3_Sub13_Sub1.outgoingBuffer.writeByte(Class83.method1411(0));
			Class3_Sub13_Sub1.outgoingBuffer.writeShort(Class23.anInt454);
			if(var0 != -3) {
				anInt3103 = -41;
			}

			Class3_Sub13_Sub1.outgoingBuffer.writeShort(Class140_Sub7.anInt2934);
			Class3_Sub13_Sub1.outgoingBuffer.writeByte(Unsorted.anInt3671);
		} catch (RuntimeException var2) {
			throw ClientErrorException.clientError(var2, "ej.C(" + var0 + ')');
		}
	}

	static void method205(CacheIndex var0, CacheIndex var2, Interface4 var3) {
		try {
			aClass153_1967 = var0;
			Class58.anInterface4_915 = var3;
			Class3_Sub24_Sub3.aClass153_3490 = var2;
			if(Class3_Sub24_Sub3.aClass153_3490 != null) {
				Class83.anInt1156 = Class3_Sub24_Sub3.aClass153_3490.getFileAmount(1);
			}

			if(aClass153_1967 != null) {
				RenderAnimationDefinition.anInt377 = aClass153_1967.getFileAmount(1);
			}
		} catch (RuntimeException var5) {
			throw ClientErrorException.clientError(var5, "ej.E(" + (var0 != null?"{...}":"null") + ',' + 115 + ',' + (var2 != null?"{...}":"null") + ',' + (var3 != null?"{...}":"null") + ')');
		}
	}

	public Class3_Sub13_Sub8() {
		super(1, false);
	}

	final int[] method154(int var1, byte var2) {
		try {
			int[] var4 = this.aClass114_2382.method1709(var1);
			if(this.aClass114_2382.aBoolean1580) {
				int[] var5 = this.method152(0, !this.aBoolean3104?var1:Class3_Sub20.anInt2487 + -var1, 32755);
				if(this.aBoolean3100) {
					for(int var6 = 0; var6 < Class113.anInt1559; ++var6) {
						var4[var6] = var5[-var6 + RenderAnimationDefinition.anInt396];
					}
				} else {
					ArrayUtils.arraycopy(var5, 0, var4, 0, Class113.anInt1559);
				}
			}

			return var4;
		} catch (RuntimeException var7) {
			throw ClientErrorException.clientError(var7, "ej.D(" + var1 + ',' + var2 + ')');
		}
	}

	static RSString method207(int var0, boolean var1, int var2, long var3) {
		try {
			if(var0 >= 2 && var0 <= 36) {
				if(var2 <= 71) {
					aFloat3105 = 1.3008908F;
				}

				long var6 = var3 / (long)var0;

				int var5;
				for(var5 = 1; var6 != 0L; var6 /= (long)var0) {
					++var5;
				}

				int var8 = var5;
				if(0L > var3 || var1) {
					var8 = var5 + 1;
				}

				byte[] var9 = new byte[var8];
				if(var3 >= 0L) {
					if(var1) {
						var9[0] = 43;
					}
				} else {
					var9[0] = 45;
				}

				for(int var10 = 0; var10 < var5; ++var10) {
					int var11 = (int)(var3 % (long)var0);
					var3 /= (long)var0;
					if(var11 < 0) {
						var11 = -var11;
					}

					if(var11 > 9) {
						var11 += 39;
					}

					var9[-1 + -var10 + var8] = (byte)(var11 + 48);
				}

				RSString var13 = new RSString();
				var13.buffer = var9;
				var13.length = var8;
				return var13;
			} else {
				throw new IllegalArgumentException("Invalid radix:" + var0);
			}
		} catch (RuntimeException var12) {
			throw ClientErrorException.clientError(var12, "ej.F(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ')');
		}
	}

}
