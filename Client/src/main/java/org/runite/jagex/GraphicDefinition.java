package org.runite.jagex;

final class GraphicDefinition {

	static int anInt529;
	private int anInt530 = 128;
	static int CAMERA_DIRECTION = 0;
	static Class3_Sub30_Sub1 incomingBuffer = new Class3_Sub30_Sub1(5000);
	private short[] aShortArray533;
	private short[] aShortArray534;
	private short[] aShortArray535;
	boolean aBoolean536 = false;
	private int anInt537 = 0;
	private int anInt538 = 0;
	int graphicId;
	private int anInt540 = 128;
	private int anInt541;
	int anInt542 = -1;
	private int anInt543 = 0;
	static int anInt544;
	private short[] aShortArray545;
	static int anInt546;
	static int anInt548 = 0;
	static volatile int anInt549 = 0;

	final void parse(RSByteBuffer var1, byte var2) {
		try {
			if(var2 != -113) {
				method967(88, -118, 75, -51, -96, -50, 45, 127);
			}

			while(true) {
				int var3 = var1.getByte((byte)-68);
				if(var3 == 0) {
					return;
				}

				this.method965(var1, var3, 128);
			}
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "eg.A(" + (var1 != null?"{...}":"null") + ',' + var2 + ')');
		}
	}

	public static void method964(int var0) {
		try {
			incomingBuffer = null;
			if(var0 != 6) {
				method964(-57);
			}
		} catch (RuntimeException var2) {
			throw Class44.clientError(var2, "eg.E(" + var0 + ')');
		}
	}

	private final void method965(RSByteBuffer var1, int var2, int var3) {
		try {
			if(var2 == 1) {
				this.anInt541 = var1.getShort(var3 + -127);
			} else if(2 == var2) {
				this.anInt542 = var1.getShort(1);
			} else if(var2 == 4) {
				this.anInt530 = var1.getShort(1);
			} else {
				if(var2 == 5) {
					this.anInt540 = var1.getShort(1);
				} else {
					if(6 == var2) {
						this.anInt543 = var1.getShort(var3 + -127);
					} else if(var2 == 7) {
						this.anInt538 = var1.getByte((byte)-47);
					} else if(var2 == 8) {
						this.anInt537 = var1.getByte((byte)-55);
					} else {
						if(var2 == 9) {
							this.aBoolean536 = true;
						} else {
							int var4;
							int var5;
							if(40 == var2) {
								var4 = var1.getByte((byte)-47);
								this.aShortArray533 = new short[var4];
								this.aShortArray545 = new short[var4];

								for(var5 = 0; var4 > var5; ++var5) {
									this.aShortArray533[var5] = (short)var1.getShort(1);
									this.aShortArray545[var5] = (short)var1.getShort(var3 + -127);
								}
							} else {
								if(41 == var2) {
									var4 = var1.getByte((byte)-32);
									this.aShortArray534 = new short[var4];
									this.aShortArray535 = new short[var4];

									for(var5 = 0; var5 < var4; ++var5) {
										this.aShortArray534[var5] = (short)var1.getShort(1);
										this.aShortArray535[var5] = (short)var1.getShort(var3 + -127);
									}
								}
							}
						}
					}
				}
			}

			if(var3 != 128) {
				this.aShortArray545 = (short[])null;
			}

		} catch (RuntimeException var6) {
			throw Class44.clientError(var6, "eg.D(" + (var1 != null?"{...}":"null") + ',' + var2 + ',' + var3 + ')');
		}
	}

	final Model method966(int var1, byte var2, int var3, int var4) {
		try {
			Model var5 = (Model)Class27.aClass93_511.get((long)this.graphicId, (byte)121);
			if(var2 == -30) {
				if(var5 == null) {
					Model_Sub1 var6 = Model_Sub1.method2015(Class3_Sub28_Sub7_Sub1.aClass153_4048, this.anInt541, 0);
					if(null == var6) {
						return null;
					}

					int var7;
					if(null != this.aShortArray533) {
						for(var7 = 0; this.aShortArray533.length > var7; ++var7) {
							var6.method2016(this.aShortArray533[var7], this.aShortArray545[var7]);
						}
					}

					if(this.aShortArray534 != null) {
						for(var7 = 0; var7 < this.aShortArray534.length; ++var7) {
							var6.method1998(this.aShortArray534[var7], this.aShortArray535[var7]);
						}
					}

					var5 = var6.method2008(64 - -this.anInt538, this.anInt537 + 850, -30, -50, -30);
					Class27.aClass93_511.put((byte)-96, var5, (long)this.graphicId);
				}

				Model var9;
				if(this.anInt542 == -1 || var3 == -1) {
					var9 = var5.method1882(true, true, true);
				} else {
					var9 = Client.getAnimationDefinition(this.anInt542, (byte)-20).method2059(var1, var4, var3, (byte)-52, var5);
				}

				if(128 != this.anInt530 || 128 != this.anInt540) {
					var9.resize(this.anInt530, this.anInt540, this.anInt530);
				}

				if(this.anInt543 != 0) {
					if(this.anInt543 == 90) {
						var9.method1885();
					}

					if(180 == this.anInt543) {
						var9.method1874();
					}

					if(270 == this.anInt543) {
						var9.method1900();
					}
				}

				return var9;
			} else {
				return (Model)null;
			}
		} catch (RuntimeException var8) {
			throw Class44.clientError(var8, "eg.C(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
		}
	}

	static final void method967(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
		try {
			if(Canvas_Sub2.loadInterface(var5, 104)) {
				if(var2 == 2) {
					Client.handleItemSwitch(GameObject.aClass11ArrayArray1834[var5], -1, var6, var1, var4, var7, var0, var3);
				}
			}
		} catch (RuntimeException var9) {
			throw Class44.clientError(var9, "eg.B(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
		}
	}

}
