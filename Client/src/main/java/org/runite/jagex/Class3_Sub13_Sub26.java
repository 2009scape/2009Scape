package org.runite.jagex;

final class Class3_Sub13_Sub26 extends Class3_Sub13 {

	static int anInt3332 = 50;
	static RSString[] aClass94Array3317 = new RSString[anInt3332];
	static int[] anIntArray3318 = new int[anInt3332];
	static int[] anIntArray3319 = new int[anInt3332];
	static int rights = 0;
	static int[] anIntArray3321 = new int[]{76, 8, 137, 4, 0, 1, 38, 2, 19};
	private int anInt3322 = 4096;
	static int anInt3323 = 50;
	static int[] anIntArray3327 = new int[anInt3332];
	static int[] anIntArray3328 = new int[8];
	static int[] anIntArray3329 = new int[anInt3332];
	static int[] anIntArray3331 = new int[anInt3332];
	static byte[][] aByteArrayArray3335;
	static int[] anIntArray3336 = new int[anInt3332];
	static int[] anIntArray3337 = new int[anInt3332];


	public static void method294(byte var0) {
		try {
			anIntArray3327 = null;
			anIntArray3337 = null;
			aClass94Array3317 = null;
			anIntArray3328 = null;
			anIntArray3319 = null;
			aByteArrayArray3335 = (byte[][])null;
			anIntArray3329 = null;
			anIntArray3336 = null;
			anIntArray3321 = null;
			anIntArray3318 = null;
			anIntArray3331 = null;
		} catch (RuntimeException var2) {
			throw Class44.clientError(var2, "pg.B(" + var0 + ')');
		}
	}

	public Class3_Sub13_Sub26() {
		super(1, true);
	}

	final int[] method154(int var1, byte var2) {
		try {
			int[] var4 = this.aClass114_2382.method1709(var1);
			if(this.aClass114_2382.aBoolean1580) {
				int[] var5 = this.method152(0, var1 - 1 & Class3_Sub20.anInt2487, 32755);
				int[] var6 = this.method152(0, var1, 32755);
				int[] var7 = this.method152(0, Class3_Sub20.anInt2487 & var1 + 1, 32755);

				for(int var8 = 0; Class113.anInt1559 > var8; ++var8) {
					int var9 = (var7[var8] + -var5[var8]) * this.anInt3322;
					int var10 = this.anInt3322 * (-var6[var8 - 1 & RenderAnimationDefinition.anInt396] + var6[RenderAnimationDefinition.anInt396 & var8 - -1]);
					int var11 = var10 >> 12;
					int var12 = var9 >> 12;
					int var13 = var11 * var11 >> 12;
					int var14 = var12 * var12 >> 12;
					int var15 = (int)(Math.sqrt((double)((float)(4096 + var14 + var13) / 4096.0F)) * 4096.0D);
					int var16 = 0 != var15?16777216 / var15:0;
					var4[var8] = 4096 + -var16;
				}
			}

			return var4;
		} catch (RuntimeException var17) {
			throw Class44.clientError(var17, "pg.D(" + var1 + ',' + var2 + ')');
		}
	}

	final void method157(int var1, RSByteBuffer var2, boolean var3) {
		try {
			if(!var3) {
				method294((byte)-57);
			}

			if(var1 == 0) {
				this.anInt3322 = var2.getShort();
			}

		} catch (RuntimeException var5) {
			throw Class44.clientError(var5, "pg.A(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
		}
	}

}
