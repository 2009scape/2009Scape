package org.runite.jagex;

import java.util.Objects;

final class NPC extends Class140_Sub4 {

	static boolean aBoolean3975 = false;
	NPCDefinition definition;
	static Class3_Sub28_Sub16[] aClass3_Sub28_Sub16Array3977;
	static float aFloat3979;
	static int[] anIntArray3986 = new int[32];
	static CacheIndex aClass153_3993;
	static CacheIndex aClass153_3994;
	static int anInt3995;
	static int[] anIntArray3997 = new int[]{19, 55, 38, 155, 255, 110, 137, 205, 76};
	static int anInt4001;

	public static void method1983(int var0) {
		try {
			anIntArray3986 = null;
			if(var0 == -3) {
				aClass153_3994 = null;
				anIntArray3997 = null;
				aClass3_Sub28_Sub16Array3977 = null;
				aClass153_3993 = null;
			}
		} catch (RuntimeException var2) {
			throw Class44.clientError(var2, "km.N(" + var0 + ')');
		}
	}

	static int method1984(int var0, int var1, int var2) {
		try {
			if(var1 == 38) {
				int var3 = 57 * var2 + var0;
				var3 ^= var3 << 13;
				int var4 = Integer.MAX_VALUE & 1376312589 + (var3 * var3 * 15731 - -789221) * var3;
				return (var4 & 133802063) >> 19;
			} else {
				return 88;
			}
		} catch (RuntimeException var5) {
			throw Class44.clientError(var5, "km.S(" + var0 + ',' + var1 + ',' + var2 + ')');
		}
	}

	protected final void finalize() {}

	final int method1871() {
		try {
			return this.anInt2820;
		} catch (RuntimeException var2) {
			throw Class44.clientError(var2, "km.MA()");
		}
	}

	static byte[] method1985(int var0, Object var1, boolean var2) {
		try {
			if(var1 == null) {
				return null;
			} else if(var1 instanceof byte[]) {
				byte[] var5 = (byte[])((byte[])var1);
				return var2?Class12.method873(var5):var5;
			} else {
				if(var0 > -118) {
					method1983(19);
				}

				if(var1 instanceof Class144) {
					Class144 var3 = (Class144)var1;
					return var3.method2064();
				} else {
					throw new IllegalArgumentException();
				}
			}
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "km.Q(" + var0 + ',' + "{...}" + ',' + var2 + ')');
		}
	}

	final void animate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, long var9, int var11, Class127_Sub1 var12) {
		try {
			if(this.definition != null) {
				AnimationDefinition var13 = this.anInt2771 != -1 && this.anInt2828 == 0 ?Client.getAnimationDefinition(this.anInt2771):null;
				AnimationDefinition var14 = -1 != this.anInt2764 && (this.method1965().anInt368 != this.anInt2764 || var13 == null)?Client.getAnimationDefinition(this.anInt2764):null;
				Model var15 = this.definition.method1476(this.aClass145Array2809, this.anInt2793, (byte)-116, this.anInt2813, this.anInt2776, this.anInt2760, this.anInt2832, var14, this.anInt2802, var13);
				if(var15 != null) {
					this.anInt2820 = var15.method1871();
					NPCDefinition var16 = this.definition;
					if(null != var16.childNPCs) {
						var16 = var16.method1471((byte)-110);
					}

					Model var17;
					if(Class140_Sub6.aBoolean2910 && Objects.requireNonNull(var16).aBoolean1249) {
						var17 = Class140_Sub3.method1957(this.definition.aByte1287, this.aBoolean2810, null == var14?var13:var14, this.anInt2819, this.definition.aShort1256, this.anInt2829, this.definition.aShort1286, this.definition.size, var15, var1, null != var14?this.anInt2813:this.anInt2832, this.anInt2831, this.definition.aByte1275);
						if(HDToolKit.highDetail) {
							float var18 = HDToolKit.method1852();
							float var19 = HDToolKit.method1839();
							HDToolKit.method1851();
							HDToolKit.method1825(var18, -150.0F + var19);
							var17.animate(0, var2, var3, var4, var5, var6, var7, var8, -1L, var11, this.aClass127_Sub1_2801);
							HDToolKit.method1830();
							HDToolKit.method1825(var18, var19);
						} else {
							var17.animate(0, var2, var3, var4, var5, var6, var7, var8, -1L, var11, this.aClass127_Sub1_2801);
						}
					}

					this.method1971(var15, (byte)-111);
					this.method1969((byte)115, var15, var1);
					var17 = null;
					if(this.anInt2842 != -1 && -1 != this.anInt2805) {
						GraphicDefinition var21 = RenderAnimationDefinition.getGraphicDefinition((byte)42, this.anInt2842);
						var17 = var21.method966(this.anInt2826, this.anInt2805, this.anInt2761);
						if(var17 != null) {
							var17.method1897(0, -this.anInt2799, 0);
							if(var21.aBoolean536) {
								if(Class3_Sub13_Sub16.anInt3198 != 0) {
									var17.method1896(Class3_Sub13_Sub16.anInt3198);
								}

								if(Class3_Sub28_Sub9.anInt3623 != 0) {
									var17.method1886(Class3_Sub28_Sub9.anInt3623);
								}

								if(0 != Class3_Sub13_Sub9.anInt3111) {
									var17.method1897(0, Class3_Sub13_Sub9.anInt3111, 0);
								}
							}
						}
					}

					if(HDToolKit.highDetail) {
						if(this.definition.size == 1) {
							var15.aBoolean2699 = true;
						}

						var15.animate(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11, this.aClass127_Sub1_2801);
						if(var17 != null) {
							if(this.definition.size == 1) {
								var17.aBoolean2699 = true;
							}

							var17.animate(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11, this.aClass127_Sub1_2801);
						}
					} else {
						if(null != var17) {
							var15 = ((Class140_Sub1_Sub2)var15).method1943(var17);
						}

						if(this.definition.size == 1) {
							var15.aBoolean2699 = true;
						}

						var15.animate(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11, this.aClass127_Sub1_2801);
					}

				}
			}
		} catch (RuntimeException var20) {
			throw Class44.clientError(var20, "km.IA(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + var9 + ',' + var11 + ',' + (var12 != null?"{...}":"null") + ')');
		}
	}

	final int getRenderAnimationId() {
		try {
			if(Class158.anInt2014 != 0 && this.definition.childNPCs != null) {
				NPCDefinition var2 = this.definition.method1471((byte)21);
				if(var2 != null && var2.renderAnimationId != -1) {
					return var2.renderAnimationId;
				}
			}

			return this.renderAnimationId;
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "km.B(" + -1 + ')');
		}
	}

	final void method1867(int var1, int var2, int var3, int var4, int var5) {
		try {
		} catch (RuntimeException var7) {
			throw Class44.clientError(var7, "km.IB(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
		}
	}

	final boolean hasDefinitions() {
		try {

			return null != this.definition;
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "km.L(" + (byte) 17 + ')');
		}
	}

	static boolean method1986(int var0) {
		try {
			if(var0 <= 22) {
				method1984(-48, 88, 31);
			}

			return HDToolKit.highDetail || Class3_Sub28_Sub13.aBoolean3665;
		} catch (RuntimeException var2) {
			throw Class44.clientError(var2, "km.O(" + var0 + ')');
		}
	}

	final void setDefinitions(NPCDefinition var2) {
		try {
			this.definition = var2;
        } catch (RuntimeException var4) {
			throw Class44.clientError(var4, "km.R(" + -1 + ',' + (var2 != null?"{...}":"null") + ')');
		}
	}

	static boolean method1988() {
		try {
			try {
				if(Class10.anInt154 == 2) {
					if(Class83.aClass3_Sub27_1154 == null) {
						Class83.aClass3_Sub27_1154 = Class3_Sub27.method517(Class101.aClass153_1423, Class3_Sub13_Sub39.anInt3463, Class132.anInt1741);
						if(null == Class83.aClass3_Sub27_1154) {
							return false;
						}
					}

					if(Class3_Sub28_Sub4.aClass83_3579 == null) {
						Class3_Sub28_Sub4.aClass83_3579 = new Class83(Class40.aClass153_679, Class3_Sub28_Sub20.aClass153_3786);
					}

					if(Class101.aClass3_Sub24_Sub4_1421.method470(Class83.aClass3_Sub27_1154, Class124.aClass153_1661, Class3_Sub28_Sub4.aClass83_3579)) {
						Class101.aClass3_Sub24_Sub4_1421.method471();
						Class101.aClass3_Sub24_Sub4_1421.method506(Class3_Sub13_Sub36.anInt3423);
						Class101.aClass3_Sub24_Sub4_1421.method490(Class3_Sub9.aBoolean2311, Class83.aClass3_Sub27_1154);
						Class10.anInt154 = 0;
						Class83.aClass3_Sub27_1154 = null;
						Class3_Sub28_Sub4.aClass83_3579 = null;
						Class101.aClass153_1423 = null;
						return true;
					}
				}
			} catch (Exception var2) {
				var2.printStackTrace();
				Class101.aClass3_Sub24_Sub4_1421.method505((byte)-128);
				Class101.aClass153_1423 = null;
				Class83.aClass3_Sub27_1154 = null;
				Class10.anInt154 = 0;
				Class3_Sub28_Sub4.aClass83_3579 = null;
			}

			return false;
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "km.P(" + false + ')');
		}
	}

}
