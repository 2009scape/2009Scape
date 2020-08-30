package org.runite.jagex;

import org.runite.Configurations;



final class Class3_Sub13_Sub1 extends Class3_Sub13 {

	static Class3_Sub30_Sub1 outgoingBuffer = new Class3_Sub30_Sub1();
	private int anInt3036 = 0;
	private int anInt3037 = 1;
	private int anInt3038 = 0;
	static RSString aClass2323;
	static String aString2324;
	static RSString aClass2325;
	static RSString aClass94_3039 = RSString.createRSString("0");
	static Class3_Sub28_Sub5[] aClass3_Sub28_Sub5Array3041 = new Class3_Sub28_Sub5[14];


	static void method167(int var0) {
		try {
			if(Class3_Sub13_Sub3.aClass148_3049 != null) {
				KeyboardListener var1 = Class3_Sub13_Sub3.aClass148_3049;
				synchronized(var1) {
					Class3_Sub13_Sub3.aClass148_3049 = null;
				}
			}

			if(var0 != 0) {
				method171(119, -44, -76, -104, 29, -65, 34, 18, 104);
			}

		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "ag.B(" + var0 + ')');
		}
	}

	public Class3_Sub13_Sub1() {
		super(0, true);
	}

	public static void method168(int var0) {
		try {
			if(var0 != -1771542303) {
				aClass3_Sub28_Sub5Array3041 = (Class3_Sub28_Sub5[])null;
			}

			aClass3_Sub28_Sub5Array3041 = null;
			aClass94_3039 = null;
			outgoingBuffer = null;
		} catch (RuntimeException var2) {
			throw Class44.clientError(var2, "ag.F(" + var0 + ')');
		}
	}

	final int[] method154(int var1, byte var2) {
		try {
			int[] var3 = this.aClass114_2382.method1709(var1);
			int var4;
			if(this.aClass114_2382.aBoolean1580) {
				var4 = Class163_Sub3.anIntArray2999[var1];
				int var5 = var4 - 2048 >> 1;

				for(int var6 = 0; var6 < Class113.anInt1559; ++var6) {
					int var8 = Class102.anIntArray2125[var6];
					int var9 = -2048 + var8 >> 1;
				int var7;
				if(this.anInt3038 == 0) {
					var7 = (var8 + -var4) * this.anInt3037;
				} else {
					int var10 = var9 * var9 - -(var5 * var5) >> 12;
					var7 = (int)(Math.sqrt((double)((float)var10 / 4096.0F)) * 4096.0D);
					var7 = (int)(3.141592653589793D * (double)(var7 * this.anInt3037));
				}

					var7 -= var7 & -4096;
				if(this.anInt3036 == 0) {
					var7 = Class3_Sub13_Sub17.anIntArray3212[(var7 & 4085) >> 4] + 4096 >> 1;
				} else if(this.anInt3036 == 2) {
					var7 -= 2048;
					if(var7 < 0) {
						var7 = -var7;
					}

					var7 = -var7 + 2048 << 1;
				}

				var3[var6] = var7;
				}
			}

            return var3;
		} catch (RuntimeException var11) {
			throw Class44.clientError(var11, "ag.D(" + var1 + ',' + var2 + ')');
		}
	}

	static void method229() {
		RSByteBuffer buffer = outgoingBuffer;
		buffer.putString(aClass2323);
		for (char c : aString2324.toCharArray()) {
			if (c == '-') {
				c = ':';
			}
			buffer.putByte((byte) -7, c);
		}
		buffer.putByte((byte) -66, 0);
		buffer.putString(aClass2325);
	}

	static void method169() {
		try {
			Class32.method995();

			for(int var1 = 0; 4 > var1; ++var1) {
				Class86.aClass91Array1182[var1].method1496();
			}

			System.gc();
		} catch (RuntimeException var2) {
			throw Class44.clientError(var2, "ag.O(" + 22230 + ')');
		}
	}

	static Class method170(String var1) throws ClassNotFoundException {
		try {

			return var1.equals("B")?Byte.TYPE:(!var1.equals("I")?(var1.equals("S")?Short.TYPE:(!var1.equals("J")?(var1.equals("Z")?Boolean.TYPE:(var1.equals("F")?Float.TYPE:(var1.equals("D")?Double.TYPE:(var1.equals("C")?Character.TYPE:Class.forName(Configurations.PACKAGE_JAGEX + "." + var1))))):Long.TYPE)):Integer.TYPE);
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "ag.C(" + 6092 + ',' + (var1 != null?"{...}":"null") + ')');
		}
	}

	final void method158(int var1) {
		try {
			Class8.method844((byte)-9);
			if(var1 != 16251) {
				method171(106, -38, 106, 7, -28, -61, 30, -77, -47);
			}

		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "ag.P(" + var1 + ')');
		}
	}

	static void method171(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
		try {
			if(var0 != -101) {
				method167(-46);
			}

			if(Canvas_Sub2.loadInterface(var1)) {
				Class47.method1095(var2, var8, var4, GameObject.aClass11ArrayArray1834[var1], var3, -1, var7, var6, (byte)119, var5);
			} else if (var5 == -1) {
				for (int var9 = 0; var9 < 100; ++var9) {
					Class3_Sub28_Sub14.aBooleanArray3674[var9] = true;
				}
			} else {
				Class3_Sub28_Sub14.aBooleanArray3674[var5] = true;
			}
		} catch (RuntimeException var10) {
			throw Class44.clientError(var10, "ag.E(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ')');
		}
	}

	final void method157(int var1, RSByteBuffer var2, boolean var3) {
		try {
			if(!var3) {
				this.method158(10);
			}

			if(var1 == 0) {
				this.anInt3038 = var2.getByteB();
			} else if (1 == var1) {
				this.anInt3036 = var2.getByteB();
			} else if (var1 == 3) {
				this.anInt3037 = var2.getByteB();
			}

		} catch (RuntimeException var5) {
			throw Class44.clientError(var5, "ag.A(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
		}
	}


	static void method445() {
		aClass2323 = RSString.createRSString(System.getProperty("user.name"));
		aString2324 = Class39.method132893();
		aClass2325 = Signlink.osName.startsWith("win") ? Class44.method3435() : Class44.method3434();
	}
}
