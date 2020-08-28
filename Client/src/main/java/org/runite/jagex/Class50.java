package org.runite.jagex;

final class Class50 {

	static int anInt820 = 0;
	int anInt821;
	int anInt823;
	static Class3_Sub28_Sub16 aClass3_Sub28_Sub16_824;
	static long[] aLongArray826 = new long[200];
	static int anInt828 = 0;
	int anInt830;
	int anInt831;


	static void method1131(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
		try {
			if(var6 >= 0 && 0 <= var4 && var6 < 103 && 103 > var4) {
				int var9;
				if(var5 == 0) {
					Class70 var8 = Class154.method2147(var0, var6, var4);
					if(var8 != null) {
						var9 = Integer.MAX_VALUE & (int)(var8.aLong1048 >>> 32);
						if(var3 == 2) {
							var8.aClass140_1049 = new Class140_Sub3(var9, 2, 4 + var2, var0, var6, var4, var7, false, var8.aClass140_1049);
							var8.aClass140_1052 = new Class140_Sub3(var9, 2, 3 & 1 + var2, var0, var6, var4, var7, false, var8.aClass140_1052);
						} else {
							var8.aClass140_1049 = new Class140_Sub3(var9, var3, var2, var0, var6, var4, var7, false, var8.aClass140_1049);
						}
					}
				}

				if(var5 == 1) {
					Class19 var12 = Class44.method1068(var0, var6, var4);
					if(null != var12) {
						var9 = (int)(var12.aLong428 >>> 32) & Integer.MAX_VALUE;
						if(var3 == 4 || var3 == 5) {
							var12.aClass140_429 = new Class140_Sub3(var9, 4, var2, var0, var6, var4, var7, false, var12.aClass140_429);
						} else if (var3 == 6) {
                            var12.aClass140_429 = new Class140_Sub3(var9, 4, var2 - -4, var0, var6, var4, var7, false, var12.aClass140_429);
                        } else if (7 == var3) {
                            var12.aClass140_429 = new Class140_Sub3(var9, 4, (var2 - -2 & 3) - -4, var0, var6, var4, var7, false, var12.aClass140_429);
                        } else if (var3 == 8) {
                            var12.aClass140_429 = new Class140_Sub3(var9, 4, 4 + var2, var0, var6, var4, var7, false, var12.aClass140_429);
                            var12.aClass140_423 = new Class140_Sub3(var9, 4, (2 + var2 & 3) + 4, var0, var6, var4, var7, false, var12.aClass140_423);
                        }
					}
				}

				if(var5 == 2) {
					if(var3 == 11) {
						var3 = 10;
					}

					Class25 var11 = Class75.method1336(var0, var6, var4);
					if(var11 != null) {
						var11.aClass140_479 = new Class140_Sub3((int)(var11.aLong498 >>> 32) & Integer.MAX_VALUE, var3, var2, var0, var6, var4, var7, false, var11.aClass140_479);
					}
				}

				if(var5 == 3) {
					Class12 var13 = RSByteBuffer.method784(var0, var6, var4);
					if(null != var13) {
						var13.object = new Class140_Sub3(Integer.MAX_VALUE & (int)(var13.aLong328 >>> 32), 22, var2, var0, var6, var4, var7, false, var13.object);
					}
				}
			}

			if(var1 <= 104) {
				method1132(-79);
			}

		} catch (RuntimeException var10) {
			throw Class44.clientError(var10, "hd.B(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
		}
	}

	static void method1132(int var0) {
		try {
			Class128.aClass93_1683.method1523((byte)-111);
			if(var0 != 103) {
				method1132(14);
			}

		} catch (RuntimeException var2) {
			throw Class44.clientError(var2, "hd.A(" + var0 + ')');
		}
	}

	public static void method1133(byte var0) {
		try {
			aLongArray826 = null;
			aClass3_Sub28_Sub16_824 = null;
		} catch (RuntimeException var2) {
			throw Class44.clientError(var2, "hd.C(" + var0 + ')');
		}
	}

	public Class50() {}

	Class50(Class50 var1) {
		try {
			this.anInt823 = var1.anInt823;
			this.anInt831 = var1.anInt831;
			this.anInt821 = var1.anInt821;
			this.anInt830 = var1.anInt830;
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "hd.<init>(" + "null" + ')');
		}
	}

}
