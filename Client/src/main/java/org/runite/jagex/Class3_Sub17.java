package org.runite.jagex;

final class Class3_Sub17 extends Class3 {

	int anInt2454;
	int[] anIntArray2455;
	static int anInt2456;
	static float aFloat2457;
	int[][] anIntArrayArray2460;
	int anInt2462;
	boolean[] aBooleanArray2463;

	private static RSString aClass94_2465 = RSString.createRSString("");
	int[] anIntArray2466;
	static RSString aClass94_2464 = aClass94_2465;

	public static void method380(int var0) {
		try {
			aClass94_2464 = null;
			if(var0 != -29113) {
				method381(false);
			}

			aClass94_2465 = null;
		} catch (RuntimeException var2) {
			throw Class44.clientError(var2, "jm.B(" + var0 + ')');
		}
	}

	static void method381(boolean var0) {
		try {
			if(Class3_Sub28_Sub12.anInt3655 != -1) {
				Class52.method1160(-120, Class3_Sub28_Sub12.anInt3655);
			}

			for(int var1 = 0; var1 < Class3_Sub28_Sub3.anInt3557; ++var1) {
				if(Class3_Sub28_Sub14.aBooleanArray3674[var1]) {
					Class163_Sub1_Sub1.aBooleanArray4008[var1] = true;
				}

				Class130.aBooleanArray1712[var1] = Class3_Sub28_Sub14.aBooleanArray3674[var1];
				Class3_Sub28_Sub14.aBooleanArray3674[var1] = false;
			}

			Class53.anInt865 = -1;
			Class99.aClass11_1402 = null;
			Class3_Sub23.anInt2535 = Class44.anInt719;
			if(HDToolKit.highDetail) {
				OutputStream_Sub1.aBoolean47 = true;
			}

			Node.anInt2567 = -1;
			if(!var0) {
				method380(-78);
			}

			if(Class3_Sub28_Sub12.anInt3655 != -1) {
				Class3_Sub28_Sub3.anInt3557 = 0;
				Class8.method841();
			}

			if(HDToolKit.highDetail) {
				Class22.method925();
			} else {
				Class74.method1331();
			}

			Class106.anInt1446 = 0;
		} catch (RuntimeException var2) {
			throw Class44.clientError(var2, "jm.A(" + var0 + ')');
		}
	}

	Class3_Sub17(int var1, byte[] var2) {
		try {
			this.anInt2454 = var1;
			RSByteBuffer var3 = new RSByteBuffer(var2);
			this.anInt2462 = var3.getByteB();
			this.anIntArrayArray2460 = new int[this.anInt2462][];
			this.anIntArray2466 = new int[this.anInt2462];
			this.aBooleanArray2463 = new boolean[this.anInt2462];
			this.anIntArray2455 = new int[this.anInt2462];

			int var4;
			for(var4 = 0; this.anInt2462 > var4; ++var4) {
				this.anIntArray2466[var4] = var3.getByteB();
			}

			for(var4 = 0; var4 < this.anInt2462; ++var4) {
				this.aBooleanArray2463[var4] = var3.getByteB() == 1;
			}

			for(var4 = 0; var4 < this.anInt2462; ++var4) {
				this.anIntArray2455[var4] = var3.getShort();
			}

			for(var4 = 0; var4 < this.anInt2462; ++var4) {
				this.anIntArrayArray2460[var4] = new int[var3.getByteB()];
			}

			for(var4 = 0; this.anInt2462 > var4; ++var4) {
				for(int var5 = 0; this.anIntArrayArray2460[var4].length > var5; ++var5) {
					this.anIntArrayArray2460[var4][var5] = var3.getByteB();
				}
			}

		} catch (RuntimeException var6) {
			loadNewHeader(var1, var2);
//			var6.printStackTrace();
//			throw Class44.method1067(var6, "jm.<init>(" + var1 + ',' + (var2 != null?"{...}":"null") + ')');
		}
	}

	private void loadNewHeader(int var1, byte[] var2) {
		this.anInt2454 = var1;
		System.out.println("Loaded new header!");
		RSByteBuffer var3 = new RSByteBuffer(var2);
		this.anInt2462 = var3.getByteB();
		this.anIntArrayArray2460 = new int[this.anInt2462][];
		this.anIntArray2466 = new int[this.anInt2462];
		this.aBooleanArray2463 = new boolean[this.anInt2462];
		this.anIntArray2455 = new int[this.anInt2462];

		int var4;
		for(var4 = 0; this.anInt2462 > var4; ++var4) {
			this.anIntArray2466[var4] = var3.getByteB();
		}

//		for(var4 = 0; ~this.anInt2462 < ~var4; ++var4) {
//			this.aBooleanArray2463[var4] = ~var3.getByte((byte)-35) == -2;
//		}
//
//		for(var4 = 0; var4 < this.anInt2462; ++var4) {
////			this.anIntArray2455[var4] = var3.getShort(1);
//		}

		for(var4 = 0; var4 < this.anInt2462; ++var4) {
			this.anIntArrayArray2460[var4] = new int[var3.getByteB()];
		}

		for(var4 = 0; this.anInt2462 > var4; ++var4) {
			for(int var5 = 0; this.anIntArrayArray2460[var4].length > var5; ++var5) {
				this.anIntArrayArray2460[var4][var5] = var3.getByteB();
			}
		}
	}

}
