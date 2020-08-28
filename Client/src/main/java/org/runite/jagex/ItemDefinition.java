package org.runite.jagex;
import java.awt.Component;
import java.io.UnsupportedEncodingException;
import java.util.Date;

final class ItemDefinition {

	private short[] aShortArray751;
	private int anInt752;
	private int anInt753 = -1;
	int anInt754;
	private int anInt755;
	int anInt756 = -1;
	int value = 1;
	int anInt758;
	static int anInt759;
	private int wornModelPositionX = 0;
	int anInt761;
	int anInt762;
	static int anInt763;
	int stackingType;
	private short[] aShortArray765;
	int[] anIntArray766;
	int anInt767;
	int anInt768;
	private int anInt769;
	RSString name;
	private int anInt771;
	private short[] aShortArray772;
	private int anInt773 = -1;
	private short[] aShortArray774;
	private int wornModelPositionY;
	private int anInt776 = -1;
	private int anInt777;
	private int wornModelPositionZ;
	boolean membersItem;
	private int anInt780;
	static int[] anIntArray781 = new int[99];
	int teamId = 0;
	RSString[] inventoryOptions;
	private int anInt784;
	private byte[] aByteArray785;
	int anInt786;
	int itemId;
	int anInt788;
	int anInt789;
	private int anInt790;
	int anInt791;
	int anInt792;
	int anInt793;
	private int anInt794;
	int anInt795;
	private int anInt796;
	private int anInt797;
	Class130 aClass130_798;
	int anInt799;
	int anInt800;
	RSString[] groundOptions;
	private int anInt802;
	private int anInt803;
	int[] anIntArray804;
	private int anInt805;
	boolean aBoolean807;
	static RSString aClass94_808;

	static RSString aClass94_809;
	private static RSString aClass94_806;
	int anInt810;
	private static RSString aClass94_811;


	final boolean method1102(boolean var1) {
		try {
			int var3 = this.anInt803;
			int var4 = this.anInt796;
			if(var1) {
				var3 = this.anInt773;
				var4 = this.anInt753;
			}

			if(false) {
				this.parseDefinitions(41, (RSByteBuffer)null);
			}

			if(var3 == -1) {
				return true;
			} else {
				boolean var5 = true;
				if(!Class3_Sub29.aClass153_2581.method2129((byte)-75, 0, var3)) {
					var5 = false;
				}

				if(var4 != -1 && !Class3_Sub29.aClass153_2581.method2129((byte)58, 0, var4)) {
					var5 = false;
				}

				return var5;
			}
		} catch (RuntimeException var6) {
			throw Class44.clientError(var6, "h.G(" + var1 + ',' + false + ')');
		}
	}

	static void method1103(CacheIndex var0, CacheIndex var1, boolean var2) {
		try {
			Class3_Sub13_Sub14.aClass153_3173 = var0;
			Class29.aClass153_557 = var1;
			if(!var2) {
			}
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "h.B(" + (var0 != null?"{...}":"null") + ',' + (var1 != null?"{...}":"null") + ',' + var2 + ')');
		}
	}

	static void method1104(byte byte0, int i, CS2Script script)
	{
		try {
			Object aobj[] = script.arguments;
			int j = ((Integer) aobj[0]).intValue();
			//System.out.println("CS opcode: " + j);
			Class3_Sub28_Sub15 class3_sub28_sub15 = Class3_Sub28_Sub8.method572(j);
			if (null == class3_sub28_sub15)
				return;
			NPC.anInt4002 = 0;
			int l = 0;
			int k = 0;
			int i1 = -1;
			int ai1[] = class3_sub28_sub15.anIntArray3690;
			int ai[] = class3_sub28_sub15.anIntArray3683;
			if (byte0 > -9)
				aClass94_811 = (RSString) null;
			byte byte1 = -1;
			Class126.anIntArray1662 = new int[class3_sub28_sub15.anInt3680];
			int k1 = 0;
			Class107.aClass94Array1454 = new RSString[class3_sub28_sub15.anInt3687];
			int l1 = 0;
			for (int i2 = 1; aobj.length > i2; i2++) {
				if (aobj[i2] instanceof Integer) {
					int k2 = ((Integer) aobj[i2]).intValue();
					if (k2 == -2147483647)
						k2 = script.anInt2447;
					if (0x80000002 == k2)
						k2 = script.anInt2441;
					if (k2 == -2147483645)
						k2 = null == script.aClass11_2449 ? -1 : script.aClass11_2449.anInt279;
					if (0x80000004 == k2)
						k2 = script.anInt2445;
					if (k2 == -2147483643)
						k2 = null == script.aClass11_2449 ? -1 : script.aClass11_2449.anInt191;
					if (k2 == -2147483642)
						k2 = null == script.aClass11_2438 ? -1 : script.aClass11_2438.anInt279;
					if (k2 == -2147483641)
						k2 = script.aClass11_2438 != null ? script.aClass11_2438.anInt191 : -1;
					if (k2 == -2147483640)
						k2 = script.anInt2444;
					if (k2 == -2147483639)
						k2 = script.anInt2443;
					Class126.anIntArray1662[k1++] = k2;
					continue;
				}
				if (!(aobj[i2] instanceof RSString))
					continue;
				RSString class94 = (RSString) aobj[i2];
				if (class94.method1528(RSInterface.aClass94_209))
					class94 = script.aClass94_2439;
				Class107.aClass94Array1454[l1++] = class94;
				//	System.out.println("Item Definition line 168 " + class94.toString());
			}

			int j2 = 0;
			label0:
			do {
				j2++;
				if (i < j2)
					throw new RuntimeException("slow");
				int j1 = ai[++i1];
				if (j1 < 100) {
					if (j1 == 0) {
						Class140_Sub7.anIntArray2929[k++] = ai1[i1];
						continue;
					}
					if (j1 == 1) {
						int l2 = ai1[i1];
						Class140_Sub7.anIntArray2929[k++] = Class163_Sub1.anIntArray2985[l2];
						continue;
					}
					if (j1 == 2) {
						int i3 = ai1[i1];
						Class86.method1428(i3, 63, Class140_Sub7.anIntArray2929[--k]);
						continue;
					}
					if (j1 == 3) {
						Class3.aClass94Array75[l++] = class3_sub28_sub15.aClass94Array3688[i1];
						continue;
					}
					if (j1 == 6) {
						i1 += ai1[i1];
						continue;
					}
					if (7 == j1) {
						k -= 2;
						if (Class140_Sub7.anIntArray2929[k] != Class140_Sub7.anIntArray2929[1 + k])
							i1 += ai1[i1];
						continue;
					}
					if (j1 == 8) {
						k -= 2;
						if (Class140_Sub7.anIntArray2929[k] == Class140_Sub7.anIntArray2929[k + 1])
							i1 += ai1[i1];
						continue;
					}
					if (9 == j1) {
						k -= 2;
						if (Class140_Sub7.anIntArray2929[1 + k] > Class140_Sub7.anIntArray2929[k])
							i1 += ai1[i1];
						continue;
					}
					if (j1 == 10) {
						k -= 2;
						if (Class140_Sub7.anIntArray2929[k - -1] < Class140_Sub7.anIntArray2929[k])
							i1 += ai1[i1];
						continue;
					}
					if (j1 == 21) {
						if (NPC.anInt4002 == 0)
							return;
						Class54 class54 = Class140_Sub4.aClass54Array2841[--NPC.anInt4002];
						class3_sub28_sub15 = class54.aClass3_Sub28_Sub15_874;
						Class126.anIntArray1662 = class54.anIntArray870;
						ai = class3_sub28_sub15.anIntArray3683;
						i1 = class54.anInt877;
						Class107.aClass94Array1454 = class54.aClass94Array873;
						ai1 = class3_sub28_sub15.anIntArray3690;
						continue;
					}
					if (j1 == 25) {
						int j3 = ai1[i1];
						Class140_Sub7.anIntArray2929[k++] = NPCDefinition.method1484(j3);
						continue;
					}
					if (j1 == 27) {
						int k3 = ai1[i1];
						Class3_Sub13_Sub29.method306(k3, Class140_Sub7.anIntArray2929[--k]);
						continue;
					}
					if (31 == j1) {
						k -= 2;
						if (Class140_Sub7.anIntArray2929[k] <= Class140_Sub7.anIntArray2929[1 + k])
							i1 += ai1[i1];
						continue;
					}
					if (j1 == 32) {
						k -= 2;
						if (Class140_Sub7.anIntArray2929[1 + k] <= Class140_Sub7.anIntArray2929[k])
							i1 += ai1[i1];
						continue;
					}
					if (33 == j1) {
						Class140_Sub7.anIntArray2929[k++] = Class126.anIntArray1662[ai1[i1]];
						continue;
					}
					if (j1 == 34) {
						Class126.anIntArray1662[ai1[i1]] = Class140_Sub7.anIntArray2929[--k];
						continue;
					}
					if (j1 == 35) {
						Class3.aClass94Array75[l++] = Class107.aClass94Array1454[ai1[i1]];
						continue;
					}
					if (j1 == 36) {
						Class107.aClass94Array1454[ai1[i1]] = Class3.aClass94Array75[--l];
						continue;
					}
					if (j1 == 37) {
						int l3 = ai1[i1];
						l -= l3;
						RSString class94_2 = Class67.method1261(l, l3, Class3.aClass94Array75);
						Class3.aClass94Array75[l++] = class94_2;
						continue;
					}
					if (38 == j1) {
						k--;
						continue;
					}
					if (j1 == 39) {
						l--;
						continue;
					}
					if (j1 == 40) {
						int i4 = ai1[i1];
						Class3_Sub28_Sub15 class3_sub28_sub15_1 = Class3_Sub28_Sub8.method572(i4);
						int ai2[] = new int[class3_sub28_sub15_1.anInt3680];
						RSString aclass94[] = new RSString[class3_sub28_sub15_1.anInt3687];
						for (int l75 = 0; class3_sub28_sub15_1.anInt3678 > l75; l75++)
							ai2[l75] = Class140_Sub7.anIntArray2929[l75 + (k - class3_sub28_sub15_1.anInt3678)];

						for (int i76 = 0; class3_sub28_sub15_1.anInt3682 > i76; i76++)
							aclass94[i76] = Class3.aClass94Array75[i76 + -class3_sub28_sub15_1.anInt3682 + l];

						k -= class3_sub28_sub15_1.anInt3678;
						l -= class3_sub28_sub15_1.anInt3682;
						Class54 class54_1 = new Class54();
						class54_1.aClass94Array873 = Class107.aClass94Array1454;
						class54_1.anIntArray870 = Class126.anIntArray1662;
						class54_1.anInt877 = i1;
						class54_1.aClass3_Sub28_Sub15_874 = class3_sub28_sub15;
						if (Class140_Sub4.aClass54Array2841.length <= NPC.anInt4002)
							throw new RuntimeException();
						class3_sub28_sub15 = class3_sub28_sub15_1;
						i1 = -1;
						Class140_Sub4.aClass54Array2841[NPC.anInt4002++] = class54_1;
						Class126.anIntArray1662 = ai2;
						ai1 = class3_sub28_sub15.anIntArray3690;
						ai = class3_sub28_sub15.anIntArray3683;
						Class107.aClass94Array1454 = aclass94;
						continue;
					}
					if (42 == j1) {
						Class140_Sub7.anIntArray2929[k++] = NPCDefinition.anIntArray1277[ai1[i1]];
						continue;
					}
					if (j1 == 43) {
						int j4 = ai1[i1];
						NPCDefinition.anIntArray1277[j4] = Class140_Sub7.anIntArray2929[--k];
						PacketParser.method825(j4);
						continue;
					}
					if (44 == j1) {
						int k4 = ai1[i1] >> 16;
						int l43 = Class140_Sub7.anIntArray2929[--k];
						int k5 = 0xffff & ai1[i1];
						if (l43 < 0 || 5000 < l43)
							throw new RuntimeException();
						WorldListCountry.anIntArray509[k4] = l43;
						byte byte2 = -1;
						if (k5 == 105)
							byte2 = 0;
						int j76 = 0;
						while (j76 < l43) {
							Canvas_Sub1.anIntArrayArray20[k4][j76] = byte2;
							j76++;
						}
						continue;
					}
					if (j1 == 45) {
						int l4 = ai1[i1];
						int l5 = Class140_Sub7.anIntArray2929[--k];
						if (0 > l5 || WorldListCountry.anIntArray509[l4] <= l5)
							throw new RuntimeException();
						Class140_Sub7.anIntArray2929[k++] = Canvas_Sub1.anIntArrayArray20[l4][l5];
						continue;
					}
					if (j1 == 46) {
						int i5 = ai1[i1];
						k -= 2;
						int i6 = Class140_Sub7.anIntArray2929[k];
						if (i6 < 0 || WorldListCountry.anIntArray509[i5] <= i6)
							throw new RuntimeException();
						Canvas_Sub1.anIntArrayArray20[i5][i6] = Class140_Sub7.anIntArray2929[1 + k];
						continue;
					}
					if (47 == j1) {
						RSString class94_1 = Class132.aClass94Array1739[ai1[i1]];
						if (null == class94_1)
							class94_1 = Class140_Sub7.aClass94_2928;
						Class3.aClass94Array75[l++] = class94_1;
						continue;
					}
					if (j1 == 48) {
						int j5 = ai1[i1];
						Class132.aClass94Array1739[j5] = Class3.aClass94Array75[--l];
						Class49.method1126(j5);
						continue;
					}
					if (j1 == 51) {
						Class130 class130 = class3_sub28_sub15.aClass130Array3685[ai1[i1]];
						Class3_Sub18 class3_sub18 = (Class3_Sub18) class130.method1780(Class140_Sub7.anIntArray2929[--k], 0);
						if (null != class3_sub18)
							i1 += class3_sub18.anInt2467;
						continue;
					}
				}
				boolean flag;
				flag = 1 == ai1[i1];
				if (j1 < 300) {
					if (j1 == 100) {
						k -= 3;
						int j6 = Class140_Sub7.anIntArray2929[k];
						int i44 = Class140_Sub7.anIntArray2929[1 + k];
						int k66 = Class140_Sub7.anIntArray2929[2 + k];
						if (i44 == 0)
							throw new RuntimeException();
						RSInterface class11_21 = Class7.getRSInterface((byte) 121, j6);
						if (null == class11_21.aClass11Array262)
							class11_21.aClass11Array262 = new RSInterface[k66 + 1];
						if (k66 >= class11_21.aClass11Array262.length) {
							RSInterface aclass11[] = new RSInterface[k66 + 1];
							for (int k81 = 0; class11_21.aClass11Array262.length > k81; k81++)
								aclass11[k81] = class11_21.aClass11Array262[k81];

							class11_21.aClass11Array262 = aclass11;
						}
						if (0 < k66 && class11_21.aClass11Array262[-1 + k66] == null)
							throw new RuntimeException("Gap at:" + (-1 + k66));
						RSInterface class11_23 = new RSInterface();
						class11_23.usingScripts = true;
						class11_23.anInt191 = k66;
						class11_23.parentId = class11_23.anInt279 = class11_21.anInt279;
						class11_23.type = i44;
						class11_21.aClass11Array262[k66] = class11_23;
						if (flag)
							Class164.aClass11_2055 = class11_23;
						else
							Class133.aClass11_1749 = class11_23;
						Class20.method909(110, class11_21);
						continue;
					}
					if (j1 == 101) {
						RSInterface class11 = flag ? Class164.aClass11_2055 : Class133.aClass11_1749;
						if (class11.anInt191 == -1)
							if (!flag)
								throw new RuntimeException("Tried to cc_delete static active-component!");
							else
								throw new RuntimeException("Tried to .cc_delete static .active-component!");
						RSInterface class11_17 = Class7.getRSInterface((byte) 111, class11.anInt279);
						class11_17.aClass11Array262[class11.anInt191] = null;
						Class20.method909(-8, class11_17);
						continue;
					}
					if (j1 == 102) {
						RSInterface class11_1 = Class7.getRSInterface((byte) 109, Class140_Sub7.anIntArray2929[--k]);
						class11_1.aClass11Array262 = null;
						Class20.method909(-50, class11_1);
						continue;
					}
					if (j1 == 200) {
						k -= 2;
						int k6 = Class140_Sub7.anIntArray2929[k];
						int j44 = Class140_Sub7.anIntArray2929[k - -1];
						RSInterface class11_19 = Class3_Sub28_Sub16.method638(k6, j44);
						if (null == class11_19 || j44 == -1) {
							Class140_Sub7.anIntArray2929[k++] = 0;
						} else {
							Class140_Sub7.anIntArray2929[k++] = 1;
							if (!flag)
								Class133.aClass11_1749 = class11_19;
							else
								Class164.aClass11_2055 = class11_19;
						}
						continue;
					}
					if (j1 != 201)
						break;
					int l6 = Class140_Sub7.anIntArray2929[--k];
					RSInterface class11_18 = Class7.getRSInterface((byte) 113, l6);
					if (null == class11_18) {
						Class140_Sub7.anIntArray2929[k++] = 0;
					} else {
						Class140_Sub7.anIntArray2929[k++] = 1;
						if (flag)
							Class164.aClass11_2055 = class11_18;
						else
							Class133.aClass11_1749 = class11_18;
					}
					continue;
				}
				if (500 <= j1) {
					if (1000 <= j1 && j1 < 1100 || 2000 <= j1 && j1 < 2100) {
						RSInterface class11_2;
						if (j1 < 2000) {
							class11_2 = flag ? Class164.aClass11_2055 : Class133.aClass11_1749;
						} else {
							class11_2 = Class7.getRSInterface((byte) 122, Class140_Sub7.anIntArray2929[--k]);
							j1 -= 1000;
						}
						if (j1 == 1000) {
							k -= 4;
							class11_2.x = Class140_Sub7.anIntArray2929[k];
							class11_2.y = Class140_Sub7.anIntArray2929[k + 1];
							int l66 = Class140_Sub7.anIntArray2929[3 + k];
							if (l66 < 0)
								l66 = 0;
							else if (l66 > 5)
								l66 = 5;
							int k44 = Class140_Sub7.anIntArray2929[k + 2];
							if (k44 >= 0) {
								if (k44 > 5)
									k44 = 5;
							} else {
								k44 = 0;
							}
							class11_2.aByte162 = (byte) l66;
							class11_2.aByte273 = (byte) k44;
							Class20.method909(109, class11_2);
							Class3_Sub13_Sub12.method225(class11_2);
							if (class11_2.anInt191 == -1)
								Class168.method2280(class11_2.anInt279);
							continue;
						}
						if (j1 == 1001) {
							k -= 4;
							class11_2.width = Class140_Sub7.anIntArray2929[k];
							class11_2.height = Class140_Sub7.anIntArray2929[1 + k];
							class11_2.anInt184 = 0;
							class11_2.anInt312 = 0;
							int l44 = Class140_Sub7.anIntArray2929[k + 2];
							int i67 = Class140_Sub7.anIntArray2929[3 + k];
							if (i67 >= 0) {
								if (i67 > 4)
									i67 = 4;
							} else {
								i67 = 0;
							}
							class11_2.aByte241 = (byte) i67;
							if (l44 < 0)
								l44 = 0;
							else if (l44 > 4)
								l44 = 4;
							class11_2.aByte304 = (byte) l44;
							Class20.method909(122, class11_2);
							Class3_Sub13_Sub12.method225(class11_2);
							if (class11_2.type == 0)
								Class151_Sub1.method2104(class11_2, false, 32);
							continue;
						}
						if (j1 == 1003) {
							boolean flag3 = Class140_Sub7.anIntArray2929[--k] == 1;
							if (flag3 == (!class11_2.hidden)) {
								class11_2.hidden = flag3;
								Class20.method909(-103, class11_2);
							}
							if (-1 == class11_2.anInt191)
								Class3_Sub28_Sub7_Sub1.method569(class11_2.anInt279);
							continue;
						}
						if (j1 == 1004) {
							k -= 2;
							class11_2.anInt216 = Class140_Sub7.anIntArray2929[k];
							class11_2.anInt160 = Class140_Sub7.anIntArray2929[k - -1];
							Class20.method909(-99, class11_2);
							Class3_Sub13_Sub12.method225(class11_2);
							if (class11_2.type == 0)
								Class151_Sub1.method2104(class11_2, false, -127);
							continue;
						}
						if (j1 != 1005)
							break;
						class11_2.aBoolean219 = Class140_Sub7.anIntArray2929[--k] == 1;
						continue;
					}
					if ((j1 < 1100 || 1200 <= j1) && (j1 < 2100 || 2200 <= j1)) {
						if ((j1 < 1200 || 1300 <= j1) && (2200 > j1 || j1 >= 2300)) {
							if (j1 >= 1300 && j1 < 1400 || j1 >= 2300 && j1 < 2400) {
								RSInterface class11_3;
								if (2000 <= j1) {
									class11_3 = Class7.getRSInterface((byte) 119, Class140_Sub7.anIntArray2929[--k]);
									j1 -= 1000;
								} else {
									class11_3 = flag ? Class164.aClass11_2055 : Class133.aClass11_1749;
								}
								if (j1 == 1300) {
									int i45 = Class140_Sub7.anIntArray2929[--k] + -1;
									if (0 > i45 || i45 > 9)
										l--;
									else
										class11_3.method857(Class3.aClass94Array75[--l], i45);
									continue;
								}
								if (j1 == 1301) {
									k -= 2;
									int j67 = Class140_Sub7.anIntArray2929[1 + k];
									int j45 = Class140_Sub7.anIntArray2929[k];
									class11_3.aClass11_302 = Class3_Sub28_Sub16.method638(j45, j67);
									continue;
								}
								if (j1 == 1302) {
									class11_3.aBoolean200 = Class140_Sub7.anIntArray2929[--k] == 1;
									continue;
								}
								if (j1 == 1303) {
									class11_3.anInt214 = Class140_Sub7.anIntArray2929[--k];
									continue;
								}
								if (j1 == 1304) {
									class11_3.anInt179 = Class140_Sub7.anIntArray2929[--k];
									continue;
								}
								if (1305 == j1) {
									class11_3.aClass94_277 = Class3.aClass94Array75[--l];
									continue;
								}
								if (j1 == 1306) {
									class11_3.aClass94_245 = Class3.aClass94Array75[--l];
									continue;
								}
								if (j1 == 1307) {
									class11_3.aClass94Array171 = null;
									continue;
								}
								if (j1 == 1308) {
									class11_3.anInt238 = Class140_Sub7.anIntArray2929[--k];
									class11_3.anInt266 = Class140_Sub7.anIntArray2929[--k];
									continue;
								}
								if (1309 != j1)
									break;
								int k45 = Class140_Sub7.anIntArray2929[--k];
								int k67 = Class140_Sub7.anIntArray2929[--k];
								if (k67 >= 1 && k67 <= 10)
									class11_3.method854(k67 + -1, k45);
								continue;
							}
							if ((j1 < 1400 || j1 >= 1500) && (2400 > j1 || j1 >= 2500)) {
								if (1600 > j1) {
									RSInterface class11_4 = flag ? Class164.aClass11_2055 : Class133.aClass11_1749;
									if (j1 == 1500) {
										Class140_Sub7.anIntArray2929[k++] = class11_4.anInt306;
										continue;
									}
									if (j1 == 1501) {
										Class140_Sub7.anIntArray2929[k++] = class11_4.anInt210;
										continue;
									}
									if (j1 == 1502) {
										Class140_Sub7.anIntArray2929[k++] = class11_4.anInt168;
										continue;
									}
									if (j1 == 1503) {
										Class140_Sub7.anIntArray2929[k++] = class11_4.anInt193;
										continue;
									}
									if (j1 == 1504) {
										Class140_Sub7.anIntArray2929[k++] = class11_4.hidden ? 1 : 0;
										continue;
									}
									if (j1 != 1505)
										break;
									Class140_Sub7.anIntArray2929[k++] = class11_4.parentId;
									continue;
								}
								if (j1 < 1700) {
									RSInterface class11_5 = flag ? Class164.aClass11_2055 : Class133.aClass11_1749;
									if (j1 == 1600) {
										Class140_Sub7.anIntArray2929[k++] = class11_5.anInt247;
										continue;
									}
									if (j1 == 1601) {
										Class140_Sub7.anIntArray2929[k++] = class11_5.anInt208;
										continue;
									}
									if (j1 == 1602) {
										Class3.aClass94Array75[l++] = class11_5.aClass94_232;
										continue;
									}
									if (j1 == 1603) {
										Class140_Sub7.anIntArray2929[k++] = class11_5.anInt240;
										continue;
									}
									if (j1 == 1604) {
										Class140_Sub7.anIntArray2929[k++] = class11_5.anInt252;
										continue;
									}
									if (j1 == 1605) {
										Class140_Sub7.anIntArray2929[k++] = class11_5.anInt164;
										continue;
									}
									if (j1 == 1606) {
										Class140_Sub7.anIntArray2929[k++] = class11_5.anInt182;
										continue;
									}
									if (1607 == j1) {
										Class140_Sub7.anIntArray2929[k++] = class11_5.anInt280;
										continue;
									}
									if (j1 == 1608) {
										Class140_Sub7.anIntArray2929[k++] = class11_5.anInt308;
										continue;
									}
									if (j1 == 1609) {
										Class140_Sub7.anIntArray2929[k++] = class11_5.anInt223;
										continue;
									}
									if (1610 == j1) {
										Class140_Sub7.anIntArray2929[k++] = class11_5.anInt258;
										continue;
									}
									if (j1 == 1611) {
										Class140_Sub7.anIntArray2929[k++] = class11_5.anInt264;
										continue;
									}
									if (j1 != 1612)
										break;
									Class140_Sub7.anIntArray2929[k++] = class11_5.spriteArchiveId;
									continue;
								}
								if (j1 >= 1800) {
									if (j1 < 1900) {
										RSInterface class11_6 = flag ? Class164.aClass11_2055 : Class133.aClass11_1749;
										if (1800 == j1) {
											Class140_Sub7.anIntArray2929[k++] = Client.method44(class11_6).method101(-95);
											continue;
										}
										if (1801 == j1) {
											int l45 = Class140_Sub7.anIntArray2929[--k];
											l45--;
											if (null == class11_6.aClass94Array171 || class11_6.aClass94Array171.length <= l45 || null == class11_6.aClass94Array171[l45])
												Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
											else
												Class3.aClass94Array75[l++] = class11_6.aClass94Array171[l45];
											continue;
										}
										if (j1 != 1802)
											break;
										if (null != class11_6.aClass94_277)
											Class3.aClass94Array75[l++] = class11_6.aClass94_277;
										else
											Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
										continue;
									}
									if (2600 > j1) {
										RSInterface class11_7 = Class7.getRSInterface((byte) 114, Class140_Sub7.anIntArray2929[--k]);
										if (j1 == 2500) {
											Class140_Sub7.anIntArray2929[k++] = class11_7.anInt306;
											continue;
										}
										if (j1 == 2501) {
											Class140_Sub7.anIntArray2929[k++] = class11_7.anInt210;
											continue;
										}
										if (2502 == j1) {
											Class140_Sub7.anIntArray2929[k++] = class11_7.anInt168;
											continue;
										}
										if (j1 == 2503) {
											Class140_Sub7.anIntArray2929[k++] = class11_7.anInt193;
											continue;
										}
										if (2504 == j1) {
											Class140_Sub7.anIntArray2929[k++] = class11_7.hidden ? 1 : 0;
											continue;
										}
										if (j1 != 2505)
											break;
										Class140_Sub7.anIntArray2929[k++] = class11_7.parentId;
										continue;
									}
									if (j1 >= 2700) {
										if (2800 <= j1) {
											if (j1 >= 2900) {
												if (j1 < 3200) {
													if (j1 == 3100) {
														RSString class94_3 = Class3.aClass94Array75[--l];
														Class3_Sub30_Sub1.addChatMessage(Class3_Sub9.aClass94_2331, 0, class94_3, -1);
														continue;
													}
													if (j1 == 3101) {
														k -= 2;
														Class3_Sub28_Sub14.method628(0, Class140_Sub7.anIntArray2929[k - -1], Class140_Sub7.anIntArray2929[k], Class102.player);
														continue;
													}
													if (j1 == 3103) {
														Class3_Sub13_Sub19.method264((byte) 87);
														continue;
													}
													if (j1 == 3104) {
														Class100.anInt1405++;
														RSString class94_4 = Class3.aClass94Array75[--l];
														int i46 = 0;
														if (class94_4.method1543(82))
															i46 = class94_4.method1552((byte) -104);
														Class3_Sub13_Sub1.outgoingBuffer.putOpcode(23);
														Class3_Sub13_Sub1.outgoingBuffer.putInt(-124, i46);
														continue;
													}
													if (j1 == 3105) {
														Class3_Sub22.anInt2500++;
														RSString class94_5 = Class3.aClass94Array75[--l];
														Class3_Sub13_Sub1.outgoingBuffer.putOpcode(244);
														Class3_Sub13_Sub1.outgoingBuffer.putLong(class94_5.toLong(-115), 0x868e5910);
														continue;
													}
													if (j1 == 3106) {
														Class7.anInt2165++;
														RSString class94_6 = Class3.aClass94Array75[--l];
														Class3_Sub13_Sub1.outgoingBuffer.putOpcode(65);
														Class3_Sub13_Sub1.outgoingBuffer.putByte((byte) -17, 1 + class94_6.length(-84));
														Class3_Sub13_Sub1.outgoingBuffer.putString(class94_6);
														continue;
													}
													if (j1 == 3107) {
														int i7 = Class140_Sub7.anIntArray2929[--k];
														RSString class94_44 = Class3.aClass94Array75[--l];
														Class166.method2258(i7, class94_44);
														continue;
													}
													if (j1 == 3108) {
														k -= 3;
														int j46 = Class140_Sub7.anIntArray2929[k - -1];
														int j7 = Class140_Sub7.anIntArray2929[k];
														int l67 = Class140_Sub7.anIntArray2929[2 + k];
														RSInterface class11_22 = Class7.getRSInterface((byte) 114, l67);
														Class3_Sub28_Sub6.a(j46, j7, 115, class11_22);
														continue;
													}
													if (j1 == 3109) {
														k -= 2;
														int k7 = Class140_Sub7.anIntArray2929[k];
														RSInterface class11_20 = flag ? Class164.aClass11_2055 : Class133.aClass11_1749;
														int k46 = Class140_Sub7.anIntArray2929[1 + k];
														Class3_Sub28_Sub6.a(k46, k7, 79, class11_20);
														continue;
													}
													if (j1 != 3110)
														break;
													Class3_Sub13_Sub16.anInt3199++;
													int l7 = Class140_Sub7.anIntArray2929[--k];
													Class3_Sub13_Sub1.outgoingBuffer.putOpcode(111);
													Class3_Sub13_Sub1.outgoingBuffer.putShort(l7);
													continue;
												}
												if (j1 < 3300) {
													if (j1 == 3200) {
														k -= 3;
														Class3_Sub13_Sub6.method199(Class140_Sub7.anIntArray2929[k - -1], Class140_Sub7.anIntArray2929[k], Class140_Sub7.anIntArray2929[k + 2]);
														continue;
													}
													if (j1 == 3201) {
														Class86.method1427(Class140_Sub7.anIntArray2929[--k]);
														continue;
													}
													if (j1 != 3202)
														break;
													k -= 2;
													Class167.method2266(Class140_Sub7.anIntArray2929[1 + k], Class140_Sub7.anIntArray2929[k]);
													continue;
												}
												if (j1 < 3400) {
													if (j1 == 3300) {
														Class140_Sub7.anIntArray2929[k++] = Class44.anInt719;
														continue;
													}
													if (j1 == 3301) {
														k -= 2;
														int i8 = Class140_Sub7.anIntArray2929[k];
														int l46 = Class140_Sub7.anIntArray2929[1 + k];
														Class140_Sub7.anIntArray2929[k++] = RSInterface.method861(i8, 89, l46);
														continue;
													}
													if (j1 == 3302) {
														k -= 2;
														int i47 = Class140_Sub7.anIntArray2929[k + 1];
														int j8 = Class140_Sub7.anIntArray2929[k];
														Class140_Sub7.anIntArray2929[k++] = Class12.method872(j8, i47);
														continue;
													}
													if (3303 == j1) {
														k -= 2;
														int j47 = Class140_Sub7.anIntArray2929[k - -1];
														int k8 = Class140_Sub7.anIntArray2929[k];
														Class140_Sub7.anIntArray2929[k++] = Class167.method2268((byte) -107, k8, j47);
														continue;
													}
													if (3304 == j1) {
														int l8 = Class140_Sub7.anIntArray2929[--k];
														Class140_Sub7.anIntArray2929[k++] = Class144.method2069(l8, -127).anInt3647;
														continue;
													}
													if (j1 == 3305) {
														int i9 = Class140_Sub7.anIntArray2929[--k];
														Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub15.anIntArray3185[i9];
														continue;
													}
													if (j1 == 3306) {
														int j9 = Class140_Sub7.anIntArray2929[--k];
														Class140_Sub7.anIntArray2929[k++] = Class3_Sub20.anIntArray2480[j9];
														continue;
													}
													if (3307 == j1) {
														int k9 = Class140_Sub7.anIntArray2929[--k];
														Class140_Sub7.anIntArray2929[k++] = Class133.anIntArray1743[k9];
														continue;
													}
													if (j1 == 3308) {
														int l9 = WorldListCountry.localPlane;
														int k47 = Class131.anInt1716 + (Class102.player.anInt2819 >> 7);
														int i68 = (Class102.player.anInt2829 >> 7) - -Class82.anInt1152;
														Class140_Sub7.anIntArray2929[k++] = (l9 << 28) - (-(k47 << 14) - i68);
														continue;
													}
													if (j1 == 3309) {
														int i10 = Class140_Sub7.anIntArray2929[--k];
														Class140_Sub7.anIntArray2929[k++] = Class3_Sub28_Sub15.method633(16383, i10 >> 14);
														continue;
													}
													if (3310 == j1) {
														int j10 = Class140_Sub7.anIntArray2929[--k];
														Class140_Sub7.anIntArray2929[k++] = j10 >> 28;
														continue;
													}
													if (j1 == 3311) {
														int k10 = Class140_Sub7.anIntArray2929[--k];
														Class140_Sub7.anIntArray2929[k++] = Class3_Sub28_Sub15.method633(k10, 16383);
														continue;
													}
													if (j1 == 3312) {
														Class140_Sub7.anIntArray2929[k++] = Class2.isMember ? 1 : 0;
														continue;
													}
													if (3313 == j1) {
														k -= 2;
														int l10 = 32768 + Class140_Sub7.anIntArray2929[k];
														int l47 = Class140_Sub7.anIntArray2929[k - -1];
														Class140_Sub7.anIntArray2929[k++] = RSInterface.method861(l10, 118, l47);
														continue;
													}
													if (3314 == j1) {
														k -= 2;
														int i11 = Class140_Sub7.anIntArray2929[k] - -32768;
														int i48 = Class140_Sub7.anIntArray2929[1 + k];
														Class140_Sub7.anIntArray2929[k++] = Class12.method872(i11, i48);
														continue;
													}
													if (3315 == j1) {
														k -= 2;
														int j11 = 32768 + Class140_Sub7.anIntArray2929[k];
														int j48 = Class140_Sub7.anIntArray2929[1 + k];
														Class140_Sub7.anIntArray2929[k++] = Class167.method2268((byte) -52, j11, j48);
														continue;
													}
													if (j1 == 3316) {
														if (Class3_Sub13_Sub26.rights < 2)
															Class140_Sub7.anIntArray2929[k++] = 0;
														else
															Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub26.rights;
														continue;
													}
													if (j1 == 3317) {
														Class140_Sub7.anIntArray2929[k++] = Class38_Sub1.anInt2617;
														continue;
													}
													if (3318 == j1) {
														Class140_Sub7.anIntArray2929[k++] = CS2Script.anInt2451;
														continue;
													}
													if (3321 == j1) {
														Class140_Sub7.anIntArray2929[k++] = Class9.anInt136;
														continue;
													}
													if (j1 == 3322) {
														Class140_Sub7.anIntArray2929[k++] = MouseListeningClass.anInt1925;
														continue;
													}
													if (3323 == j1) {
														if (Class3_Sub28_Sub19.anInt3775 >= 5 && Class3_Sub28_Sub19.anInt3775 <= 9)
															Class140_Sub7.anIntArray2929[k++] = 1;
														else
															Class140_Sub7.anIntArray2929[k++] = 0;
														continue;
													}
													if (j1 == 3324) {
														if (Class3_Sub28_Sub19.anInt3775 < 5 || Class3_Sub28_Sub19.anInt3775 > 9)
															Class140_Sub7.anIntArray2929[k++] = 0;
														else
															Class140_Sub7.anIntArray2929[k++] = Class3_Sub28_Sub19.anInt3775;
														continue;
													}
													if (3325 == j1) {
														Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub29.disableGEBoxes ? 1 : 0;
														continue;
													}
													if (3326 == j1) {
														Class140_Sub7.anIntArray2929[k++] = Class102.player.COMBAT_LEVEL;
														continue;
													}
													if (3327 == j1) {
														Class140_Sub7.anIntArray2929[k++] = Class102.player.class52.aBoolean864 ? 1 : 0;
														continue;
													}
													if (3328 == j1) {
														Class140_Sub7.anIntArray2929[k++] = !Class3_Sub15.aBoolean2433 || Class121.aBoolean1641 ? 0 : 1;
														continue;
													}
													if (3329 == j1) {
														Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub14.aBoolean3166 ? 1 : 0;
														continue;
													}
													if (j1 == 3330) {
														int k11 = Class140_Sub7.anIntArray2929[--k];
														Class140_Sub7.anIntArray2929[k++] = WorldListEntry.method1079(k11);
														continue;
													}
													if (j1 == 3331) {
														k -= 2;
														int k48 = Class140_Sub7.anIntArray2929[1 + k];
														int l11 = Class140_Sub7.anIntArray2929[k];
														Class140_Sub7.anIntArray2929[k++] = Class106.method1643(false, l11, k48);
														continue;
													}
													if (3332 == j1) {
														k -= 2;
														int i12 = Class140_Sub7.anIntArray2929[k];
														int l48 = Class140_Sub7.anIntArray2929[k + 1];
														Class140_Sub7.anIntArray2929[k++] = Class106.method1643(true, i12, l48);
														continue;
													}
													if (3333 == j1) {
														Class140_Sub7.anIntArray2929[k++] = Class7.anInt2161;
														continue;
													}
													if (3335 == j1) {
														Class140_Sub7.anIntArray2929[k++] = Class3_Sub20.language;
														continue;
													}
													if (j1 == 3336) {
														k -= 4;
														int i49 = Class140_Sub7.anIntArray2929[k - -1];
														int j12 = Class140_Sub7.anIntArray2929[k];
														j12 += i49 << 14;
														int k76 = Class140_Sub7.anIntArray2929[3 + k];
														int j68 = Class140_Sub7.anIntArray2929[2 + k];
														j12 += j68 << 28;
														j12 += k76;
														Class140_Sub7.anIntArray2929[k++] = j12;
														continue;
													}
													if (j1 != 3337)
														break;
													Class140_Sub7.anIntArray2929[k++] = Class3_Sub26.anInt2554;
													continue;
												}
												if (j1 < 3500) {
													if (j1 == 3400) {
														k -= 2;
														int k12 = Class140_Sub7.anIntArray2929[k];
														int j49 = Class140_Sub7.anIntArray2929[1 + k];
														Class3_Sub28_Sub13 class3_sub28_sub13_1 = Class3_Sub13_Sub36.method342(k12);
														if (class3_sub28_sub13_1.anInt3658 != 115) ;
														Class3.aClass94Array75[l++] = class3_sub28_sub13_1.method616(j49, (byte) 121);
														continue;
													}
													if (3408 == j1) {
														k -= 4;
														int l12 = Class140_Sub7.anIntArray2929[k];
														int k49 = Class140_Sub7.anIntArray2929[1 + k];
														int l76 = Class140_Sub7.anIntArray2929[3 + k];
														int k68 = Class140_Sub7.anIntArray2929[k - -2];
														Class3_Sub28_Sub13 class3_sub28_sub13_4 = Class3_Sub13_Sub36.method342(k68);
														if (class3_sub28_sub13_4.anInt3662 != l12 || k49 != class3_sub28_sub13_4.anInt3658)
															throw new RuntimeException("C3408-1");
														if (k49 != 115)
															Class140_Sub7.anIntArray2929[k++] = class3_sub28_sub13_4.method620(l76);
														else
															Class3.aClass94Array75[l++] = class3_sub28_sub13_4.method616(l76, (byte) -25);
														continue;
													}
													if (j1 == 3409) {
														k -= 3;
														int l49 = Class140_Sub7.anIntArray2929[k - -1];
														int l68 = Class140_Sub7.anIntArray2929[k + 2];
														int i13 = Class140_Sub7.anIntArray2929[k];
														if (l49 == -1)
															throw new RuntimeException("C3409-2");
														Class3_Sub28_Sub13 class3_sub28_sub13_3 = Class3_Sub13_Sub36.method342(l49);
														if (i13 != class3_sub28_sub13_3.anInt3658)
															throw new RuntimeException("C3409-1");
														Class140_Sub7.anIntArray2929[k++] = class3_sub28_sub13_3.method621(l68) ? 1 : 0;
														continue;
													}
													if (j1 == 3410) {
														int j13 = Class140_Sub7.anIntArray2929[--k];
														RSString class94_45 = Class3.aClass94Array75[--l];
														if (j13 == -1)
															throw new RuntimeException("C3410-2");
														Class3_Sub28_Sub13 class3_sub28_sub13_2 = Class3_Sub13_Sub36.method342(j13);
														if (class3_sub28_sub13_2.anInt3658 != 115)
															throw new RuntimeException("C3410-1");
														Class140_Sub7.anIntArray2929[k++] = class3_sub28_sub13_2.method617(class94_45) ? 1 : 0;
														continue;
													}
													if (j1 != 3411)
														break;
													int k13 = Class140_Sub7.anIntArray2929[--k];
													Class3_Sub28_Sub13 class3_sub28_sub13 = Class3_Sub13_Sub36.method342(k13);
													Class140_Sub7.anIntArray2929[k++] = class3_sub28_sub13.aClass130_3663.method1781(79);
													continue;
												}
												if (3700 > j1) {
													if (3600 == j1) {
														if (Class96.anInt1357 == 0)
															Class140_Sub7.anIntArray2929[k++] = -2;
														else if (Class96.anInt1357 != 1)
															Class140_Sub7.anIntArray2929[k++] = Class8.anInt104;
														else
															Class140_Sub7.anIntArray2929[k++] = -1;
														continue;
													}
													if (3601 == j1) {
														int l13 = Class140_Sub7.anIntArray2929[--k];
														if (Class96.anInt1357 != 2 || Class8.anInt104 <= l13)
															Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
														else
															Class3.aClass94Array75[l++] = Class70.aClass94Array1046[l13];
														continue;
													}
													if (j1 == 3602) {
														int i14 = Class140_Sub7.anIntArray2929[--k];
														if (Class96.anInt1357 != 2 || i14 >= Class8.anInt104)
															Class140_Sub7.anIntArray2929[k++] = 0;
														else
															Class140_Sub7.anIntArray2929[k++] = Class55.anIntArray882[i14];
														continue;
													}
													if (j1 == 3603) {
														int j14 = Class140_Sub7.anIntArray2929[--k];
														if (2 == Class96.anInt1357 && Class8.anInt104 > j14)
															Class140_Sub7.anIntArray2929[k++] = Class57.anIntArray904[j14];
														else
															Class140_Sub7.anIntArray2929[k++] = 0;
														continue;
													}
													if (3604 == j1) {
														int i50 = Class140_Sub7.anIntArray2929[--k];
														RSString class94_7 = Class3.aClass94Array75[--l];
														Class100.method1605(class94_7, i50);
														continue;
													}
													if (j1 == 3605) {
														RSString class94_8 = Class3.aClass94Array75[--l];
														Class163_Sub3.method2229(class94_8.toLong(-120));
														continue;
													}
													if (j1 == 3606) {
														RSString class94_9 = Class3.aClass94Array75[--l];
														Class3_Sub13_Sub27.method297(class94_9.toLong(-114), 1);
														continue;
													}
													if (j1 == 3607) {
														RSString class94_10 = Class3.aClass94Array75[--l];
														Class81.method1399(class94_10.toLong(-116));
														continue;
													}
													if (j1 == 3608) {
														RSString class94_11 = Class3.aClass94Array75[--l];
														Class3_Sub13_Sub10.method212(class94_11.toLong(-115));
														continue;
													}
													if (j1 == 3609) {
														RSString class94_12 = Class3.aClass94Array75[--l];
														if (class94_12.method1558(Class3_Sub9.aClass94_2323) || class94_12.method1558(Class3_Sub13_Sub16.aClass94_3190))
															class94_12 = class94_12.method1556(7);
														Class140_Sub7.anIntArray2929[k++] = Class54.method1176(class94_12) ? 1 : 0;
														continue;
													}
													if (j1 == 3610) {
														int k14 = Class140_Sub7.anIntArray2929[--k];
														if (Class96.anInt1357 == 2 && Class8.anInt104 > k14)
															Class3.aClass94Array75[l++] = Node.aClass94Array2566[k14];
														else
															Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
														continue;
													}
													if (j1 == 3611) {
														if (RSInterface.aClass94_251 != null)
															Class3.aClass94Array75[l++] = RSInterface.aClass94_251.method1545();
														else
															Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
														continue;
													}
													if (j1 == 3612) {
														if (null != RSInterface.aClass94_251)
															Class140_Sub7.anIntArray2929[k++] = Node.clanSize;
														else
															Class140_Sub7.anIntArray2929[k++] = 0;
														continue;
													}
													if (j1 == 3613) {
														int l14 = Class140_Sub7.anIntArray2929[--k];
														if (RSInterface.aClass94_251 == null || l14 >= Node.clanSize)
															Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
														else
															Class3.aClass94Array75[l++] = Class3_Sub28_Sub15.aClass3_Sub19Array3694[l14].aClass94_2476.method1545();
														continue;
													}
													if (j1 == 3614) {
														int i15 = Class140_Sub7.anIntArray2929[--k];
														if (RSInterface.aClass94_251 == null || i15 >= Node.clanSize)
															Class140_Sub7.anIntArray2929[k++] = 0;
														else
															Class140_Sub7.anIntArray2929[k++] = Class3_Sub28_Sub15.aClass3_Sub19Array3694[i15].anInt2478;
														continue;
													}
													if (3615 == j1) {
														int j15 = Class140_Sub7.anIntArray2929[--k];
														if (null == RSInterface.aClass94_251 || j15 >= Node.clanSize)
															Class140_Sub7.anIntArray2929[k++] = 0;
														else
															Class140_Sub7.anIntArray2929[k++] = Class3_Sub28_Sub15.aClass3_Sub19Array3694[j15].aByte2472;
														continue;
													}
													if (3616 == j1) {
														Class140_Sub7.anIntArray2929[k++] = Player.aByte3953;
														continue;
													}
													if (j1 == 3617) {
														RSString class94_13 = Class3.aClass94Array75[--l];
														Class106.method1642(class94_13);
														continue;
													}
													if (j1 == 3618) {
														Class140_Sub7.anIntArray2929[k++] = Class91.aByte1308;
														continue;
													}
													if (j1 == 3619) {
														RSString class94_14 = Class3.aClass94Array75[--l];
														Class3_Sub22.method400(class94_14.toLong(-107));
														continue;
													}
													if (j1 == 3620) {
														Class77.method1368();
														continue;
													}
													if (j1 == 3621) {
														if (Class96.anInt1357 == 0)
															Class140_Sub7.anIntArray2929[k++] = -1;
														else
															Class140_Sub7.anIntArray2929[k++] = Class3_Sub28_Sub5.anInt3591;
														continue;
													}
													if (3622 == j1) {
														int k15 = Class140_Sub7.anIntArray2929[--k];
														if (Class96.anInt1357 == 0 || Class3_Sub28_Sub5.anInt3591 <= k15)
															Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
														else
															Class3.aClass94Array75[l++] = Class41.method1052(-29664, Class114.ignores[k15]).method1545();
														continue;
													}
													if (3623 == j1) {
														RSString class94_15 = Class3.aClass94Array75[--l];
														if (class94_15.method1558(Class3_Sub9.aClass94_2323) || class94_15.method1558(Class3_Sub13_Sub16.aClass94_3190))
															class94_15 = class94_15.method1556(7);
														Class140_Sub7.anIntArray2929[k++] = Class3_Sub24_Sub3.method467(class94_15) ? 1 : 0;
														continue;
													}
													if (j1 == 3624) {
														int l15 = Class140_Sub7.anIntArray2929[--k];
														if (null != Class3_Sub28_Sub15.aClass3_Sub19Array3694 && l15 < Node.clanSize && Class3_Sub28_Sub15.aClass3_Sub19Array3694[l15].aClass94_2476.equals(-118, Class102.player.displayName))
															Class140_Sub7.anIntArray2929[k++] = 1;
														else
															Class140_Sub7.anIntArray2929[k++] = 0;
														continue;
													}
													if (j1 == 3625) {
														if (Class161.aClass94_2035 == null)
															Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
														else
															Class3.aClass94Array75[l++] = Class161.aClass94_2035.method1545();
														continue;
													}
													if (3626 == j1) {
														int i16 = Class140_Sub7.anIntArray2929[--k];
														if (RSInterface.aClass94_251 == null || i16 >= Node.clanSize)
															Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
														else
															Class3.aClass94Array75[l++] = Class3_Sub28_Sub15.aClass3_Sub19Array3694[i16].aClass94_2473;
														continue;
													}
													if (j1 == 3627) {
														int j16 = Class140_Sub7.anIntArray2929[--k];
														if (Class96.anInt1357 != 2 || 0 > j16 || Class8.anInt104 <= j16)
															Class140_Sub7.anIntArray2929[k++] = 0;
														else
															Class140_Sub7.anIntArray2929[k++] = Class3.aBooleanArray73[j16] ? 1 : 0;
														continue;
													}
													if (j1 == 3628) {
														RSString class94_16 = Class3.aClass94Array75[--l];
														if (class94_16.method1558(Class3_Sub9.aClass94_2323) || class94_16.method1558(Class3_Sub13_Sub16.aClass94_3190))
															class94_16 = class94_16.method1556(7);
														Class140_Sub7.anIntArray2929[k++] = PacketParser.method826(class94_16, -1);
														continue;
													}
													if (j1 != 3629)
														break;
													Class140_Sub7.anIntArray2929[k++] = Class3_Sub31.countryId;
													continue;
												}
												if (j1 < 4000) {
													if (j1 == 3903) {
														int k16 = Class140_Sub7.anIntArray2929[--k];
														Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub33.aClass133Array3393[k16].method1805();
														continue;
													}
													if (j1 == 3904) {
														int l16 = Class140_Sub7.anIntArray2929[--k];
														Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub33.aClass133Array3393[l16].anInt1752;
														continue;
													}
													if (j1 == 3905) {
														int i17 = Class140_Sub7.anIntArray2929[--k];
														Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub33.aClass133Array3393[i17].anInt1757;
														continue;
													}
													if (j1 == 3906) {
														int j17 = Class140_Sub7.anIntArray2929[--k];
														Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub33.aClass133Array3393[j17].anInt1747;
														continue;
													}
													if (j1 == 3907) {
														int k17 = Class140_Sub7.anIntArray2929[--k];
														Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub33.aClass133Array3393[k17].anInt1746;
														continue;
													}
													if (3908 == j1) {
														int l17 = Class140_Sub7.anIntArray2929[--k];
														Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub33.aClass133Array3393[l17].anInt1750;
														continue;
													}
													if (3910 == j1) {
														int i18 = Class140_Sub7.anIntArray2929[--k];
														int j50 = Class3_Sub13_Sub33.aClass133Array3393[i18].method1804();
														Class140_Sub7.anIntArray2929[k++] = j50 == 0 ? 1 : 0;
														continue;
													}
													if (3911 == j1) {
														int j18 = Class140_Sub7.anIntArray2929[--k];
														int k50 = Class3_Sub13_Sub33.aClass133Array3393[j18].method1804();
														Class140_Sub7.anIntArray2929[k++] = k50 != 2 ? 0 : 1;
														continue;
													}
													if (j1 == 3912) {
														int k18 = Class140_Sub7.anIntArray2929[--k];
														int l50 = Class3_Sub13_Sub33.aClass133Array3393[k18].method1804();
														Class140_Sub7.anIntArray2929[k++] = l50 == 5 ? 1 : 0;
														continue;
													}
													if (j1 != 3913)
														break;
													int l18 = Class140_Sub7.anIntArray2929[--k];
													int i51 = Class3_Sub13_Sub33.aClass133Array3393[l18].method1804();
													Class140_Sub7.anIntArray2929[k++] = 1 == i51 ? 1 : 0;
													continue;
												}
												if (j1 < 4100) {
													if (j1 == 4000) {
														k -= 2;
														int i19 = Class140_Sub7.anIntArray2929[k];
														int j51 = Class140_Sub7.anIntArray2929[k - -1];
														Class140_Sub7.anIntArray2929[k++] = j51 + i19;
														continue;
													}
													if (j1 == 4001) {
														k -= 2;
														int j19 = Class140_Sub7.anIntArray2929[k];
														int k51 = Class140_Sub7.anIntArray2929[1 + k];
														Class140_Sub7.anIntArray2929[k++] = -k51 + j19;
														continue;
													}
													if (4002 == j1) {
														k -= 2;
														int k19 = Class140_Sub7.anIntArray2929[k];
														int l51 = Class140_Sub7.anIntArray2929[1 + k];
														Class140_Sub7.anIntArray2929[k++] = l51 * k19;
														continue;
													}
													if (4003 == j1) {
														k -= 2;
														int l19 = Class140_Sub7.anIntArray2929[k];
														int i52 = Class140_Sub7.anIntArray2929[k - -1];
														Class140_Sub7.anIntArray2929[k++] = l19 / i52;
														continue;
													}
													if (j1 == 4004) {
														int i20 = Class140_Sub7.anIntArray2929[--k];
														Class140_Sub7.anIntArray2929[k++] = (int) ((double) i20 * Math.random());
														continue;
													}
													if (4005 == j1) {
														int j20 = Class140_Sub7.anIntArray2929[--k];
														Class140_Sub7.anIntArray2929[k++] = (int) (Math.random() * (double) (1 + j20));
														continue;
													}
													if (4006 == j1) {
														k -= 5;
														int k20 = Class140_Sub7.anIntArray2929[k];
														int j52 = Class140_Sub7.anIntArray2929[k - -1];
														int i77 = Class140_Sub7.anIntArray2929[k - -3];
														int i69 = Class140_Sub7.anIntArray2929[2 + k];
														int j79 = Class140_Sub7.anIntArray2929[k + 4];
														Class140_Sub7.anIntArray2929[k++] = ((-k20 + j52) * (j79 + -i69)) / (-i69 + i77) + k20;
														continue;
													}
													if (j1 == 4007) {
														k -= 2;
														long l20 = Class140_Sub7.anIntArray2929[k];
														long l69 = Class140_Sub7.anIntArray2929[k + 1];
														Class140_Sub7.anIntArray2929[k++] = (int) ((l20 * l69) / 100L + l20);
														continue;
													}
													if (j1 == 4008) {
														k -= 2;
														int i21 = Class140_Sub7.anIntArray2929[k];
														int k52 = Class140_Sub7.anIntArray2929[1 + k];
														Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub29.bitwiseOr(i21, 1 << k52);
														continue;
													}
													if (4009 == j1) {
														k -= 2;
														int j21 = Class140_Sub7.anIntArray2929[k];
														int l52 = Class140_Sub7.anIntArray2929[1 + k];
														Class140_Sub7.anIntArray2929[k++] = Class3_Sub28_Sub15.method633(-1 - (1 << l52), j21);
														continue;
													}
													if (j1 == 4010) {
														k -= 2;
														int k21 = Class140_Sub7.anIntArray2929[k];
														int i53 = Class140_Sub7.anIntArray2929[k - -1];
														Class140_Sub7.anIntArray2929[k++] = Class3_Sub28_Sub15.method633(k21, 1 << i53) != 0 ? 1 : 0;
														continue;
													}
													if (j1 == 4011) {
														k -= 2;
														int j53 = Class140_Sub7.anIntArray2929[k - -1];
														int l21 = Class140_Sub7.anIntArray2929[k];
														Class140_Sub7.anIntArray2929[k++] = l21 % j53;
														continue;
													}
													if (j1 == 4012) {
														k -= 2;
														int k53 = Class140_Sub7.anIntArray2929[k + 1];
														int i22 = Class140_Sub7.anIntArray2929[k];
														if (0 != i22)
															Class140_Sub7.anIntArray2929[k++] = (int) Math.pow(i22, k53);
														else
															Class140_Sub7.anIntArray2929[k++] = 0;
														continue;
													}
													if (j1 == 4013) {
														k -= 2;
														int l53 = Class140_Sub7.anIntArray2929[k - -1];
														int j22 = Class140_Sub7.anIntArray2929[k];
														if (j22 == 0) {
															Class140_Sub7.anIntArray2929[k++] = 0;
														} else if (l53 == 0)
															Class140_Sub7.anIntArray2929[k++] = 0x7fffffff;
														else
															Class140_Sub7.anIntArray2929[k++] = (int) Math.pow(j22, 1.0D / (double) l53);
														continue;
													}
													if (j1 == 4014) {
														k -= 2;
														int i54 = Class140_Sub7.anIntArray2929[k + 1];
														int k22 = Class140_Sub7.anIntArray2929[k];
														Class140_Sub7.anIntArray2929[k++] = Class3_Sub28_Sub15.method633(i54, k22);
														continue;
													}
													if (j1 == 4015) {
														k -= 2;
														int l22 = Class140_Sub7.anIntArray2929[k];
														int j54 = Class140_Sub7.anIntArray2929[k + 1];
														Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub29.bitwiseOr(l22, j54);
														continue;
													}
													if (j1 == 4016) {
														k -= 2;
														int i23 = Class140_Sub7.anIntArray2929[k];
														int k54 = Class140_Sub7.anIntArray2929[1 + k];
														Class140_Sub7.anIntArray2929[k++] = k54 <= i23 ? k54 : i23;
														continue;
													}
													if (j1 == 4017) {
														k -= 2;
														int l54 = Class140_Sub7.anIntArray2929[1 + k];
														int j23 = Class140_Sub7.anIntArray2929[k];
														Class140_Sub7.anIntArray2929[k++] = j23 > l54 ? j23 : l54;
														continue;
													}
													if (j1 != 4018)
														break;
													k -= 3;
													long l23 = Class140_Sub7.anIntArray2929[k];
													long l70 = Class140_Sub7.anIntArray2929[k + 1];
													long l79 = Class140_Sub7.anIntArray2929[2 + k];
													Class140_Sub7.anIntArray2929[k++] = (int) ((l23 * l79) / l70);
													continue;
												}
												if (4200 <= j1) {
													if (j1 >= 4300) {
														if (j1 < 4400) {
															if (4300 != j1)
																break;
															k -= 2;
															int k23 = Class140_Sub7.anIntArray2929[k];
															int i55 = Class140_Sub7.anIntArray2929[1 + k];
															Class3_Sub28_Sub9 class3_sub28_sub9 = Class61.method1210(i55);
															if (!class3_sub28_sub9.method585())
																Class140_Sub7.anIntArray2929[k++] = Node.method522(k23, 27112).method1475(i55, class3_sub28_sub9.anInt3614);
															else
																Class3.aClass94Array75[l++] = Node.method522(k23, 27112).method1477(i55, class3_sub28_sub9.aClass94_3619);
															continue;
														}
														if (j1 >= 4500) {
															if (j1 >= 4600) {
																if (j1 < 5100) {
																	if (j1 == 5000) {
																		Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub8.anInt3101;
																		continue;
																	}
																	if (j1 == 5001) {
																		Class123.anInt1657++;
																		k -= 3;
																		Class3_Sub13_Sub8.anInt3101 = Class140_Sub7.anIntArray2929[k];
																		Class24.anInt467 = Class140_Sub7.anIntArray2929[1 + k];
																		Class45.anInt734 = Class140_Sub7.anIntArray2929[2 + k];
																		Class3_Sub13_Sub1.outgoingBuffer.putOpcode(157);
																		Class3_Sub13_Sub1.outgoingBuffer.putByte((byte) -8, Class3_Sub13_Sub8.anInt3101);
																		Class3_Sub13_Sub1.outgoingBuffer.putByte((byte) -126, Class24.anInt467);
																		Class3_Sub13_Sub1.outgoingBuffer.putByte((byte) -82, Class45.anInt734);
																		continue;
																	}
																	if (j1 == 5002) {
																		RSString class94_17 = Class3.aClass94Array75[--l];
																		k -= 2;
																		int j55 = Class140_Sub7.anIntArray2929[k];
																		Class154.anInt1956++;
																		int j69 = Class140_Sub7.anIntArray2929[1 + k];
																		Class3_Sub13_Sub1.outgoingBuffer.putOpcode(99);
																		Class3_Sub13_Sub1.outgoingBuffer.putLong(class94_17.toLong(-110), 0x868e5910);
																		Class3_Sub13_Sub1.outgoingBuffer.putByte((byte) -33, j55 - 1);
																		Class3_Sub13_Sub1.outgoingBuffer.putByte((byte) -104, j69);
																		continue;
																	}
																	if (j1 == 5003) {
																		RSString class94_46 = null;
																		int i24 = Class140_Sub7.anIntArray2929[--k];
																		if (i24 < 100)
																			class94_46 = Class3_Sub29.aClass94Array2580[i24];
																		if (class94_46 == null)
																			class94_46 = Class3_Sub9.aClass94_2331;
																		Class3.aClass94Array75[l++] = class94_46;
																		continue;
																	}
																	if (j1 == 5004) {
																		int j24 = Class140_Sub7.anIntArray2929[--k];
																		int k55 = -1;
																		if (j24 < 100 && null != Class3_Sub29.aClass94Array2580[j24])
																			k55 = Class3_Sub13_Sub6.anIntArray3082[j24];
																		Class140_Sub7.anIntArray2929[k++] = k55;
																		continue;
																	}
																	if (j1 == 5005) {
																		Class140_Sub7.anIntArray2929[k++] = Class24.anInt467;
																		continue;
																	}
																	if (j1 == 5008) {//Used for a lot of things involving :: || More prefixes can be added by using || and listing said added way, ie ;; can be used instead of ::
																		RSString class94_18 = Class3.aClass94Array75[--l];
																		if (class94_18.method1558(Class9.aClass94_132) || class94_18.method1558(RSString.createRSString(";;")))
																			Class73.ClientCommands(class94_18);
																		else if (Class3_Sub13_Sub26.rights != 0 || (!Class3_Sub15.aBoolean2433 || Class121.aBoolean1641) && !Class3_Sub13_Sub14.aBoolean3166) {
																			RSString class94_47 = class94_18.method1534(-98);
																			Class162.anInt2037++;
																			byte byte3 = 0;
																			if (class94_47.method1558(TextCore.TextColorYellow)) {
																				byte3 = 0;
																				class94_18 = class94_18.method1556(TextCore.TextColorYellow.length(-54));
																			} else if (class94_47.method1558(TextCore.TextColorRed)) {
																				class94_18 = class94_18.method1556(TextCore.TextColorRed.length(-102));
																				byte3 = 1;
																			} else if (class94_47.method1558(TextCore.TextColorGreen)) {
																				class94_18 = class94_18.method1556(TextCore.TextColorGreen.length(-115));
																				byte3 = 2;
																			} else if (class94_47.method1558(TextCore.TextColorCyan)) {
																				byte3 = 3;
																				class94_18 = class94_18.method1556(TextCore.TextColorCyan.length(-108));
																			} else if (class94_47.method1558(TextCore.TextColorPurple)) {
																				class94_18 = class94_18.method1556(TextCore.TextColorPurple.length(-62));
																				byte3 = 4;
																			} else if (class94_47.method1558(TextCore.TextColorWhite)) {
																				class94_18 = class94_18.method1556(TextCore.TextColorWhite.length(-46));
																				byte3 = 5;
																			} else if (class94_47.method1558(TextCore.TextFlashOne)) {
																				byte3 = 6;
																				class94_18 = class94_18.method1556(TextCore.TextFlashOne.length(-63));
																			} else if (class94_47.method1558(TextCore.TextFlashTwo)) {
																				byte3 = 7;
																				class94_18 = class94_18.method1556(TextCore.TextFlashTwo.length(-83));
																			} else if (class94_47.method1558(TextCore.TextFlashThree)) {
																				class94_18 = class94_18.method1556(TextCore.TextFlashThree.length(-92));
																				byte3 = 8;
																			} else if (class94_47.method1558(TextCore.TextGlowOne)) {
																				byte3 = 9;
																				class94_18 = class94_18.method1556(TextCore.TextGlowOne.length(-34));
																			} else if (class94_47.method1558(TextCore.TextGlowTwo)) {
																				byte3 = 10;
																				class94_18 = class94_18.method1556(TextCore.TextGlowTwo.length(-126));
																			} else if (class94_47.method1558(TextCore.TextGlowThree)) {
																				class94_18 = class94_18.method1556(TextCore.TextGlowThree.length(-50));
																				byte3 = 11;
																			} else if (0 != Class3_Sub20.language)
																				if (class94_47.method1558(TextCore.TextColorYellow)) {
																					byte3 = 0;
																					class94_18 = class94_18.method1556(TextCore.TextColorYellow.length(-116));
																				} else if (class94_47.method1558(TextCore.TextColorRed)) {
																					class94_18 = class94_18.method1556(TextCore.TextColorRed.length(-80));
																					byte3 = 1;
																				} else if (class94_47.method1558(TextCore.TextColorGreen)) {
																					class94_18 = class94_18.method1556(TextCore.TextColorGreen.length(-90));
																					byte3 = 2;
																				} else if (class94_47.method1558(TextCore.TextColorCyan)) {
																					class94_18 = class94_18.method1556(TextCore.TextColorCyan.length(-34));
																					byte3 = 3;
																				} else if (class94_47.method1558(TextCore.TextColorPurple)) {
																					class94_18 = class94_18.method1556(TextCore.TextColorPurple.length(-52));
																					byte3 = 4;
																				} else if (class94_47.method1558(TextCore.TextColorWhite)) {
																					byte3 = 5;
																					class94_18 = class94_18.method1556(TextCore.TextColorWhite.length(-90));
																				} else if (class94_47.method1558(TextCore.TextFlashOne)) {
																					class94_18 = class94_18.method1556(TextCore.TextFlashOne.length(-100));
																					byte3 = 6;
																				} else if (class94_47.method1558(TextCore.TextFlashTwo)) {
																					byte3 = 7;
																					class94_18 = class94_18.method1556(TextCore.TextFlashTwo.length(-30));
																				} else if (class94_47.method1558(TextCore.TextFlashThree)) {
																					byte3 = 8;
																					class94_18 = class94_18.method1556(TextCore.TextFlashThree.length(-101));
																				} else if (class94_47.method1558(TextCore.TextGlowOne)) {
																					byte3 = 9;
																					class94_18 = class94_18.method1556(TextCore.TextGlowOne.length(-55));
																				} else if (class94_47.method1558(TextCore.TextGlowTwo)) {
																					class94_18 = class94_18.method1556(TextCore.TextGlowTwo.length(-115));
																					byte3 = 10;
																				} else if (class94_47.method1558(TextCore.TextGlowThree)) {
																					class94_18 = class94_18.method1556(TextCore.TextGlowThree.length(-84));
																					byte3 = 11;
																				}
																			byte byte4 = 0;
																			class94_47 = class94_18.method1534(-98);
																			if (class94_47.method1558(TextCore.TextWave)) {
																				class94_18 = class94_18.method1556(TextCore.TextWave.length(-105));
																				byte4 = 1;
																			} else if (class94_47.method1558(TextCore.TextWaveTwo)) {
																				byte4 = 2;
																				class94_18 = class94_18.method1556(TextCore.TextWaveTwo.length(-117));
																			} else if (class94_47.method1558(TextCore.TextShake)) {
																				class94_18 = class94_18.method1556(TextCore.TextShake.length(-37));
																				byte4 = 3;
																			} else if (class94_47.method1558(TextCore.HasScroll)) {
																				byte4 = 4;
																				class94_18 = class94_18.method1556(TextCore.HasScroll.length(-37));
																			} else if (class94_47.method1558(TextCore.TextSlide)) {
																				byte4 = 5;
																				class94_18 = class94_18.method1556(TextCore.TextSlide.length(-17));
																			} else if (0 != Class3_Sub20.language)
																				if (class94_47.method1558(TextCore.TextWave)) {
																					class94_18 = class94_18.method1556(TextCore.TextWave.length(-74));
																					byte4 = 1;
																				} else if (class94_47.method1558(TextCore.TextWaveTwo)) {
																					byte4 = 2;
																					class94_18 = class94_18.method1556(TextCore.TextWaveTwo.length(-106));
																				} else if (class94_47.method1558(TextCore.TextShake)) {
																					byte4 = 3;
																					class94_18 = class94_18.method1556(TextCore.TextShake.length(-45));
																				} else if (class94_47.method1558(TextCore.HasScroll)) {
																					byte4 = 4;
																					class94_18 = class94_18.method1556(TextCore.HasScroll.length(-92));
																				} else if (class94_47.method1558(TextCore.TextSlide)) {
																					class94_18 = class94_18.method1556(TextCore.TextSlide.length(-124));
																					byte4 = 5;
																				}
																			Class3_Sub13_Sub1.outgoingBuffer.putOpcode(237);
																			Class3_Sub13_Sub1.outgoingBuffer.putByte((byte) -11, 0);
																			int k79 = Class3_Sub13_Sub1.outgoingBuffer.index;
																			Class3_Sub13_Sub1.outgoingBuffer.putByte((byte) -34, byte3);
																			Class3_Sub13_Sub1.outgoingBuffer.putByte((byte) -117, byte4);
																			Class85.method1423(Class3_Sub13_Sub1.outgoingBuffer, class94_18);
																			Class3_Sub13_Sub1.outgoingBuffer.method769((byte) -127, -k79 + Class3_Sub13_Sub1.outgoingBuffer.index);
																		}
																		continue;
																	}
																	if (j1 == 5009) {
																		l -= 2;
																		RSString class94_48 = Class3.aClass94Array75[l + 1];
																		RSString class94_19 = Class3.aClass94Array75[l];
																		if (Class3_Sub13_Sub26.rights != 0 || (!Class3_Sub15.aBoolean2433 || Class121.aBoolean1641) && !Class3_Sub13_Sub14.aBoolean3166) {
																			Class3_Sub13_Sub1.outgoingBuffer.putOpcode(201);
																			Class15.anInt348++;
																			Class3_Sub13_Sub1.outgoingBuffer.putByte((byte) -121, 0);
																			int k69 = Class3_Sub13_Sub1.outgoingBuffer.index;
																			Class3_Sub13_Sub1.outgoingBuffer.putLong(class94_19.toLong(-128), 0x868e5910);
																			Class85.method1423(Class3_Sub13_Sub1.outgoingBuffer, class94_48);
																			Class3_Sub13_Sub1.outgoingBuffer.method769((byte) -127, Class3_Sub13_Sub1.outgoingBuffer.index - k69);
																		}
																		continue;
																	}
																	if (j1 == 5010) {
																		int k24 = Class140_Sub7.anIntArray2929[--k];
																		RSString class94_49 = null;
																		if (k24 < 100)
																			class94_49 = Class3_Sub13_Sub19.aClass94Array3226[k24];
																		if (null == class94_49)
																			class94_49 = Class3_Sub9.aClass94_2331;
																		Class3.aClass94Array75[l++] = class94_49;
																		continue;
																	}
																	if (j1 == 5011) {
																		int l24 = Class140_Sub7.anIntArray2929[--k];
																		RSString class94_50 = null;
																		if (l24 < 100)
																			class94_50 = Class163_Sub3.aClass94Array3003[l24];
																		if (class94_50 == null)
																			class94_50 = Class3_Sub9.aClass94_2331;
																		Class3.aClass94Array75[l++] = class94_50;
																		continue;
																	}
																	if (j1 == 5012) {
																		int i25 = Class140_Sub7.anIntArray2929[--k];
																		int l55 = -1;
																		if (i25 < 100)
																			l55 = GameObject.anIntArray1835[i25];
																		Class140_Sub7.anIntArray2929[k++] = l55;
																		continue;
																	}
																	if (j1 == 5015) {
																		RSString class94_20;
																		if (Class102.player == null || null == Class102.player.displayName)
																			class94_20 = Class3_Sub28_Sub14.username;
																		else
																			class94_20 = Class102.player.getName(0);
																		Class3.aClass94Array75[l++] = class94_20;
																		continue;
																	}
																	if (j1 == 5016) {
																		Class140_Sub7.anIntArray2929[k++] = Class45.anInt734;
																		continue;
																	}
																	if (j1 == 5017) {
																		Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub9.anInt3114;
																		continue;
																	}
																	if (5050 == j1) {
																		int j25 = Class140_Sub7.anIntArray2929[--k];
																		Class3.aClass94Array75[l++] = Class3_Sub13_Sub35.getQuickChatMessage(j25).quickChatMenu;
																		continue;
																	}
																	if (j1 == 5051) {
																		int k25 = Class140_Sub7.anIntArray2929[--k];
																		Class3_Sub28_Sub1 class3_sub28_sub1 = Class3_Sub13_Sub35.getQuickChatMessage(k25);
																		if (class3_sub28_sub1.anIntArray3534 != null)
																			Class140_Sub7.anIntArray2929[k++] = class3_sub28_sub1.anIntArray3534.length;
																		else
																			Class140_Sub7.anIntArray2929[k++] = 0;
																		continue;
																	}
																	if (j1 == 5052) {
																		k -= 2;
																		int l25 = Class140_Sub7.anIntArray2929[k];
																		int i56 = Class140_Sub7.anIntArray2929[k - -1];
																		Class3_Sub28_Sub1 class3_sub28_sub1_2 = Class3_Sub13_Sub35.getQuickChatMessage(l25);
																		int j77 = class3_sub28_sub1_2.anIntArray3534[i56];
																		Class140_Sub7.anIntArray2929[k++] = j77;
																		continue;
																	}
																	if (j1 == 5053) {
																		int i26 = Class140_Sub7.anIntArray2929[--k];
																		Class3_Sub28_Sub1 class3_sub28_sub1_1 = Class3_Sub13_Sub35.getQuickChatMessage(i26);
																		if (class3_sub28_sub1_1.anIntArray3540 != null)
																			Class140_Sub7.anIntArray2929[k++] = class3_sub28_sub1_1.anIntArray3540.length;
																		else
																			Class140_Sub7.anIntArray2929[k++] = 0;
																		continue;
																	}
																	if (j1 == 5054) {
																		k -= 2;
																		int j56 = Class140_Sub7.anIntArray2929[1 + k];
																		int j26 = Class140_Sub7.anIntArray2929[k];
																		Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub35.getQuickChatMessage(j26).anIntArray3540[j56];
																		continue;
																	}
																	if (j1 == 5055) {
																		int k26 = Class140_Sub7.anIntArray2929[--k];
																		Class3.aClass94Array75[l++] = Class3_Sub29.method733(0xbc614e, k26).method554();
																		continue;
																	}
																	if (j1 == 5056) {
																		int l26 = Class140_Sub7.anIntArray2929[--k];
																		Class3_Sub28_Sub4 class3_sub28_sub4 = Class3_Sub29.method733(0xbc614e, l26);
																		if (null != class3_sub28_sub4.anIntArray3567)
																			Class140_Sub7.anIntArray2929[k++] = class3_sub28_sub4.anIntArray3567.length;
																		else
																			Class140_Sub7.anIntArray2929[k++] = 0;
																		continue;
																	}
																	if (j1 == 5057) {
																		k -= 2;
																		int k56 = Class140_Sub7.anIntArray2929[1 + k];
																		int i27 = Class140_Sub7.anIntArray2929[k];
																		Class140_Sub7.anIntArray2929[k++] = Class3_Sub29.method733(0xbc614e, i27).anIntArray3567[k56];
																		continue;
																	}
																	if (j1 == 5058) {
																		Class70.aClass10_1056 = new Class10();
																		Class70.aClass10_1056.anInt149 = Class140_Sub7.anIntArray2929[--k];
																		Class70.aClass10_1056.aClass3_Sub28_Sub4_151 = Class3_Sub29.method733(0xbc614e, Class70.aClass10_1056.anInt149);
																		Class70.aClass10_1056.anIntArray153 = new int[Class70.aClass10_1056.aClass3_Sub28_Sub4_151.method552()];
																		continue;
																	}
																	if (5059 == j1) {
																		Class3_Sub28_Sub8.anInt3613++;
																		Class3_Sub13_Sub1.outgoingBuffer.putOpcode(167);
																		Class3_Sub13_Sub1.outgoingBuffer.putByte((byte) -105, 0);
																		int j27 = Class3_Sub13_Sub1.outgoingBuffer.index;
																		Class3_Sub13_Sub1.outgoingBuffer.putByte((byte) -61, 0);
																		Class3_Sub13_Sub1.outgoingBuffer.putShort(Class70.aClass10_1056.anInt149);
																		Class70.aClass10_1056.aClass3_Sub28_Sub4_151.method545(Class3_Sub13_Sub1.outgoingBuffer, Class70.aClass10_1056.anIntArray153);
																		Class3_Sub13_Sub1.outgoingBuffer.method769((byte) -126, -j27 + Class3_Sub13_Sub1.outgoingBuffer.index);
																		continue;
																	}
																	if (5060 == j1) {
																		KeyboardListener.anInt1906++;
																		RSString class94_21 = Class3.aClass94Array75[--l];
																		Class3_Sub13_Sub1.outgoingBuffer.putOpcode(178);
																		Class3_Sub13_Sub1.outgoingBuffer.putByte((byte) -108, 0);
																		int l56 = Class3_Sub13_Sub1.outgoingBuffer.index;
																		Class3_Sub13_Sub1.outgoingBuffer.putLong(class94_21.toLong(-124), 0x868e5910);
																		Class3_Sub13_Sub1.outgoingBuffer.putShort(Class70.aClass10_1056.anInt149);
																		Class70.aClass10_1056.aClass3_Sub28_Sub4_151.method545(Class3_Sub13_Sub1.outgoingBuffer, Class70.aClass10_1056.anIntArray153);
																		Class3_Sub13_Sub1.outgoingBuffer.method769((byte) 108, Class3_Sub13_Sub1.outgoingBuffer.index + -l56);
																		continue;
																	}
																	if (j1 == 5061) {
																		Class3_Sub13_Sub1.outgoingBuffer.putOpcode(167);
																		Class3_Sub28_Sub8.anInt3613++;
																		Class3_Sub13_Sub1.outgoingBuffer.putByte((byte) -62, 0);
																		int k27 = Class3_Sub13_Sub1.outgoingBuffer.index;
																		Class3_Sub13_Sub1.outgoingBuffer.putByte((byte) -88, 1);
																		Class3_Sub13_Sub1.outgoingBuffer.putShort(Class70.aClass10_1056.anInt149);
																		Class70.aClass10_1056.aClass3_Sub28_Sub4_151.method545(Class3_Sub13_Sub1.outgoingBuffer, Class70.aClass10_1056.anIntArray153);
																		Class3_Sub13_Sub1.outgoingBuffer.method769((byte) -126, -k27 + Class3_Sub13_Sub1.outgoingBuffer.index);
																		continue;
																	}
																	if (j1 == 5062) {
																		k -= 2;
																		int i57 = Class140_Sub7.anIntArray2929[1 + k];
																		int l27 = Class140_Sub7.anIntArray2929[k];
																		Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub35.getQuickChatMessage(l27).anIntArray3535[i57];
																		continue;
																	}
																	if (j1 == 5063) {
																		k -= 2;
																		int j57 = Class140_Sub7.anIntArray2929[k - -1];
																		int i28 = Class140_Sub7.anIntArray2929[k];
																		Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub35.getQuickChatMessage(i28).anIntArray3533[j57];
																		continue;
																	}
																	if (5064 == j1) {
																		k -= 2;
																		int k57 = Class140_Sub7.anIntArray2929[1 + k];
																		int j28 = Class140_Sub7.anIntArray2929[k];
																		if (k57 != -1)
																			Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub35.getQuickChatMessage(j28).method529(k57);
																		else
																			Class140_Sub7.anIntArray2929[k++] = -1;
																		continue;
																	}
																	if (j1 == 5065) {
																		k -= 2;
																		int k28 = Class140_Sub7.anIntArray2929[k];
																		int l57 = Class140_Sub7.anIntArray2929[k + 1];
																		if (l57 != -1)
																			Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub35.getQuickChatMessage(k28).method526(l57, 0);
																		else
																			Class140_Sub7.anIntArray2929[k++] = -1;
																		continue;
																	}
																	if (j1 == 5066) {
																		int l28 = Class140_Sub7.anIntArray2929[--k];
																		Class140_Sub7.anIntArray2929[k++] = Class3_Sub29.method733(0xbc614e, l28).method552();
																		continue;
																	}
																	if (j1 == 5067) {
																		k -= 2;
																		int i58 = Class140_Sub7.anIntArray2929[k + 1];
																		int i29 = Class140_Sub7.anIntArray2929[k];
																		int i70 = Class3_Sub29.method733(0xbc614e, i29).method550(49, i58);
																		Class140_Sub7.anIntArray2929[k++] = i70;
																		continue;
																	}
																	if (5068 == j1) {
																		k -= 2;
																		int j29 = Class140_Sub7.anIntArray2929[k];
																		int j58 = Class140_Sub7.anIntArray2929[1 + k];
																		Class70.aClass10_1056.anIntArray153[j29] = j58;
																		continue;
																	}
																	if (j1 == 5069) {
																		k -= 2;
																		int k29 = Class140_Sub7.anIntArray2929[k];
																		int k58 = Class140_Sub7.anIntArray2929[k + 1];
																		Class70.aClass10_1056.anIntArray153[k29] = k58;
																		continue;
																	}
																	if (j1 == 5070) {
																		k -= 3;
																		int l29 = Class140_Sub7.anIntArray2929[k];
																		int j70 = Class140_Sub7.anIntArray2929[k - -2];
																		int l58 = Class140_Sub7.anIntArray2929[k + 1];
																		Class3_Sub28_Sub4 class3_sub28_sub4_1 = Class3_Sub29.method733(0xbc614e, l29);
																		if (0 != class3_sub28_sub4_1.method550(73, l58))
																			throw new RuntimeException("bad command");
																		Class140_Sub7.anIntArray2929[k++] = class3_sub28_sub4_1.method549(j70, l58);
																		continue;
																	}
																	if (j1 == 5071) {
																		RSString class94_22 = Class3.aClass94Array75[--l];
																		boolean flag4 = 1 == Class140_Sub7.anIntArray2929[--k];
																		Class3_Sub28_Sub3.method541(flag4, class94_22);
																		Class140_Sub7.anIntArray2929[k++] = Class62.anInt952;
																		continue;
																	}
																	if (5072 == j1) {
																		if (Class99.aShortArray1398 == null || Class62.anInt952 <= Class140_Sub4.anInt2756)
																			Class140_Sub7.anIntArray2929[k++] = -1;
																		else
																			Class140_Sub7.anIntArray2929[k++] = Class3_Sub28_Sub15.method633(Class99.aShortArray1398[Class140_Sub4.anInt2756++], 65535);
																		continue;
																	}
																	if (j1 != 5073)
																		break;
																	Class140_Sub4.anInt2756 = 0;
																	continue;
																}
																if (5200 > j1) {
																	if (5100 == j1) {
																		if (!ObjectDefinition.aBooleanArray1490[86])
																			Class140_Sub7.anIntArray2929[k++] = 0;
																		else
																			Class140_Sub7.anIntArray2929[k++] = 1;
																		continue;
																	}
																	if (5101 == j1) {
																		if (ObjectDefinition.aBooleanArray1490[82])
																			Class140_Sub7.anIntArray2929[k++] = 1;
																		else
																			Class140_Sub7.anIntArray2929[k++] = 0;
																		continue;
																	}
																	if (5102 != j1)
																		break;
																	if (ObjectDefinition.aBooleanArray1490[81])
																		Class140_Sub7.anIntArray2929[k++] = 1;
																	else
																		Class140_Sub7.anIntArray2929[k++] = 0;
																	continue;
																}
																if (j1 < 5300) {
																	if (j1 == 5200) {
																		NPCDefinition.method1479(Class140_Sub7.anIntArray2929[--k]);
																		continue;
																	}
																	if (5201 == j1) {
																		Class140_Sub7.anIntArray2929[k++] = Class3_Sub28_Sub8.method571();
																		continue;
																	}
																	if (j1 == 5202) {
																		Class3_Sub24_Sub4.method503(Class140_Sub7.anIntArray2929[--k]);
																		continue;
																	}
																	if (5203 == j1) {
																		Class3.method84(Class3.aClass94Array75[--l], -801);
																		continue;
																	}
																	if (5204 == j1) {
																		Class3.aClass94Array75[l - 1] = GameShell.method27(Class3.aClass94Array75[l - 1]);
																		continue;
																	}
																	if (5205 == j1) {
																		Class3_Sub10.method138(Class3.aClass94Array75[--l]);
																		continue;
																	}
																	if (j1 == 5206) {
																		int i30 = Class140_Sub7.anIntArray2929[--k];
																		Class3_Sub28_Sub3 class3_sub28_sub3_4 = NodeList.method884(0x3fff & i30 >> 0x36628f6e, (byte) 111, 0x3fff & i30);
																		if (class3_sub28_sub3_4 != null)
																			Class3.aClass94Array75[l++] = class3_sub28_sub3_4.aClass94_3561;
																		else
																			Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
																		continue;
																	}
																	if (j1 == 5207) {
																		Class3_Sub28_Sub3 class3_sub28_sub3 = Class3_Sub15.method371(Class3.aClass94Array75[--l]);
																		if (null != class3_sub28_sub3 && class3_sub28_sub3.aClass94_3554 != null)
																			Class3.aClass94Array75[l++] = class3_sub28_sub3.aClass94_3554;
																		else
																			Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
																		continue;
																	}
																	if (5208 == j1) {
																		Class140_Sub7.anIntArray2929[k++] = Class49.anInt817;
																		Class140_Sub7.anIntArray2929[k++] = Class17.anInt410;
																		continue;
																	}
																	if (5209 == j1) {
																		Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub21.anInt3256 + Class3_Sub28_Sub1.anInt3536;
																		Class140_Sub7.anIntArray2929[k++] = Class2.anInt65 + -Class3_Sub4.anInt2251 + (-1 + Class108.anInt1460);
																		continue;
																	}
																	if (j1 == 5210) {
																		Class3_Sub28_Sub3 class3_sub28_sub3_1 = Node.method520((byte) -82);
																		if (class3_sub28_sub3_1 == null) {
																			Class140_Sub7.anIntArray2929[k++] = 0;
																			Class140_Sub7.anIntArray2929[k++] = 0;
																		} else {
																			Class140_Sub7.anIntArray2929[k++] = class3_sub28_sub3_1.anInt3558 * 64;
																			Class140_Sub7.anIntArray2929[k++] = 64 * class3_sub28_sub3_1.anInt3556;
																		}
																		continue;
																	}
																	if (j1 == 5211) {
																		Class3_Sub28_Sub3 class3_sub28_sub3_2 = Node.method520((byte) -121);
																		if (class3_sub28_sub3_2 == null) {
																			Class140_Sub7.anIntArray2929[k++] = 0;
																			Class140_Sub7.anIntArray2929[k++] = 0;
																		} else {
																			Class140_Sub7.anIntArray2929[k++] = class3_sub28_sub3_2.anInt3559 - class3_sub28_sub3_2.anInt3555;
																			Class140_Sub7.anIntArray2929[k++] = -class3_sub28_sub3_2.anInt3562 + class3_sub28_sub3_2.anInt3549;
																		}
																		continue;
																	}
																	if (j1 == 5212) {
																		int j30 = Class67.method1258((byte) -53);
																		int k70 = 0;
																		RSString class94_51;
																		if (j30 == -1) {
																			class94_51 = Class3_Sub9.aClass94_2331;
																		} else {
																			class94_51 = Class119.aClass131_1624.aClass94Array1721[j30];
																			k70 = Class119.aClass131_1624.method1791(j30, 8);
																		}
																		class94_51 = class94_51.method1560(Class140_Sub4.aClass94_2765, true, Class7.aClass94_2168);
																		Class3.aClass94Array75[l++] = class94_51;
																		Class140_Sub7.anIntArray2929[k++] = k70;
																		continue;
																	}
																	if (j1 == 5213) {
																		int i71 = 0;
																		int k30 = Class3_Sub13_Sub17.method251();
																		RSString class94_52;
																		if (k30 == -1) {
																			class94_52 = Class3_Sub9.aClass94_2331;
																		} else {
																			class94_52 = Class119.aClass131_1624.aClass94Array1721[k30];
																			i71 = Class119.aClass131_1624.method1791(k30, 8);
																		}
																		class94_52 = class94_52.method1560(Class140_Sub4.aClass94_2765, true, Class7.aClass94_2168);
																		Class3.aClass94Array75[l++] = class94_52;
																		Class140_Sub7.anIntArray2929[k++] = i71;
																		continue;
																	}
																	if (j1 == 5214) {
																		int l30 = Class140_Sub7.anIntArray2929[--k];
																		Class3_Sub28_Sub7.method565(0x3fff & l30 >> 0xa1b3276e, 0x3fff & l30);
																		continue;
																	}
																	if (j1 == 5215) {
																		int i31 = Class140_Sub7.anIntArray2929[--k];
																		RSString class94_53 = Class3.aClass94Array75[--l];
																		boolean flag10 = false;
																		NodeList class13 = AbstractIndexedSprite.method1664(0x3fff & i31 >> 0xa4e408ae, 0x3fff & i31);
																		Class3_Sub28_Sub3 class3_sub28_sub3_5 = (Class3_Sub28_Sub3) class13.method876((byte) 116);
																		do {
																			if (class3_sub28_sub3_5 == null)
																				break;
																			if (class3_sub28_sub3_5.aClass94_3561.equals(124, class94_53)) {
																				flag10 = true;
																				break;
																			}
																			class3_sub28_sub3_5 = (Class3_Sub28_Sub3) class13.method878(125);
																		} while (true);
																		if (!flag10)
																			Class140_Sub7.anIntArray2929[k++] = 0;
																		else
																			Class140_Sub7.anIntArray2929[k++] = 1;
																		continue;
																	}
																	if (j1 == 5216) {
																		int j31 = Class140_Sub7.anIntArray2929[--k];
																		Class3_Sub13_Sub36.method344(j31, 4);
																		continue;
																	}
																	if (j1 == 5217) {
																		int k31 = Class140_Sub7.anIntArray2929[--k];
																		if (!Class3_Sub10.method140(k31))
																			Class140_Sub7.anIntArray2929[k++] = 0;
																		else
																			Class140_Sub7.anIntArray2929[k++] = 1;
																		continue;
																	}
																	if (j1 == 5218) {
																		Class3_Sub28_Sub3 class3_sub28_sub3_3 = Node.method520((byte) -124);
																		if (null != class3_sub28_sub3_3)
																			Class140_Sub7.anIntArray2929[k++] = class3_sub28_sub3_3.anInt3563;
																		else
																			Class140_Sub7.anIntArray2929[k++] = -1;
																		continue;
																	}
																	if (j1 == 5219) {
																		Class21.method915(Class3.aClass94Array75[--l]);
																		continue;
																	}
																	if (j1 != 5220)
																		break;
																	Class140_Sub7.anIntArray2929[k++] = Class140_Sub3.anInt2737 != 100 ? 0 : 1;
																	continue;
																}
																if (j1 < 5400) {
																	if (j1 == 5300) {
																		k -= 2;
																		int i59 = Class140_Sub7.anIntArray2929[1 + k];
																		int l31 = Class140_Sub7.anIntArray2929[k];
																		GameObject.graphicsSettings(false, 3, l31, i59);
																		Class140_Sub7.anIntArray2929[k++] = null != Class3_Sub13_Sub10.aFrame3121 ? 1 : 0;
																		continue;
																	}
																	if (j1 == 5301) {
																		if (null != Class3_Sub13_Sub10.aFrame3121)
																			GameObject.graphicsSettings(false, Node.anInt2577, -1, -1);
																		continue;
																	}
																	if (5302 == j1) {
																		Class106 aclass106[] = Class3.method88();
																		Class140_Sub7.anIntArray2929[k++] = aclass106.length;
																		continue;
																	}
																	if (5303 == j1) {
																		int i32 = Class140_Sub7.anIntArray2929[--k];
																		Class106 aclass106_1[] = Class3.method88();
																		Class140_Sub7.anIntArray2929[k++] = aclass106_1[i32].anInt1447;
																		Class140_Sub7.anIntArray2929[k++] = aclass106_1[i32].anInt1449;
																		continue;
																	}
																	if (j1 == 5305) {
																		int j59 = Class3_Sub13_Sub5.anInt3071;
																		int j32 = Class3_Sub13.anInt2378;
																		int j71 = -1;
																		Class106 aclass106_2[] = Class3.method88();
																		int i80 = 0;
																		do {
																			if (aclass106_2.length <= i80)
																				break;
																			Class106 class106 = aclass106_2[i80];
																			if (j32 == class106.anInt1447 && class106.anInt1449 == j59) {
																				j71 = i80;
																				break;
																			}
																			i80++;
																		} while (true);
																		Class140_Sub7.anIntArray2929[k++] = j71;
																		continue;
																	}
																	if (j1 == 5306) {
																		Class140_Sub7.anIntArray2929[k++] = Class83.method1411(0);
																		continue;
																	}
																	if (j1 == 5307) {
																		int k32 = Class140_Sub7.anIntArray2929[--k];
																		if (k32 < 0 || k32 > 2)
																			k32 = 0;
																		GameObject.graphicsSettings(false, k32, -1, -1);
																		continue;
																	}
																	if (5308 == j1) {
																		Class140_Sub7.anIntArray2929[k++] = Node.anInt2577;
																		continue;
																	}
																	if (5309 != j1)
																		break;
																	int l32 = Class140_Sub7.anIntArray2929[--k];
																	if (l32 < 0 || l32 > 2)
																		l32 = 0;
																	Node.anInt2577 = l32;
																	Class119.method1730(Class38.aClass87_665);
																	continue;
																}
																if (5500 > j1) {
																	if (j1 == 5400) {
																		l -= 2;
																		RSString class94_23 = Class3.aClass94Array75[l];
																		RSString class94_54 = Class3.aClass94Array75[l - -1];
																		int k71 = Class140_Sub7.anIntArray2929[--k];
																		AnimationDefinition.anInt1853++;
																		Class3_Sub13_Sub1.outgoingBuffer.putOpcode(117);
																		Class3_Sub13_Sub1.outgoingBuffer.putByte((byte) -91, Class3_Sub13_Sub33.method326((byte) 39, class94_23) - (-Class3_Sub13_Sub33.method326((byte) 102, class94_54) + -1));
																		Class3_Sub13_Sub1.outgoingBuffer.putString(class94_23);
																		Class3_Sub13_Sub1.outgoingBuffer.putString(class94_54);
																		Class3_Sub13_Sub1.outgoingBuffer.putByte((byte) -79, k71);
																		continue;
																	}
																	if (j1 == 5401) {
																		k -= 2;
																		Class3_Sub13_Sub38.aShortArray3455[Class140_Sub7.anIntArray2929[k]] = (short) Class56.method1186(Class140_Sub7.anIntArray2929[k + 1]);
																		GameShell.method28();
																		RSByteBuffer.method746((byte) -29);
																		Class167.method2265(0);
																		WorldListEntry.method1076();
																		Class47.method1093(false);
																		continue;
																	}
																	if (j1 == 5405) {
																		k -= 2;
																		int i33 = Class140_Sub7.anIntArray2929[k];
																		int k59 = Class140_Sub7.anIntArray2929[1 + k];
																		if (i33 >= 0 && i33 < 2)
																			Class58.anIntArrayArrayArray911[i33] = new int[k59 << 0x27e6541][4];
																		continue;
																	}
																	if (j1 == 5406) {
																		k -= 7;
																		int j33 = Class140_Sub7.anIntArray2929[k];
																		int l59 = Class140_Sub7.anIntArray2929[1 + k] << 0xee994ea1;
																		int k77 = Class140_Sub7.anIntArray2929[k - -3];
																		int l71 = Class140_Sub7.anIntArray2929[2 + k];
																		int j80 = Class140_Sub7.anIntArray2929[4 + k];
																		int j82 = Class140_Sub7.anIntArray2929[6 + k];
																		int l81 = Class140_Sub7.anIntArray2929[5 + k];
																		if (j33 >= 0 && j33 < 2 && null != Class58.anIntArrayArrayArray911[j33] && l59 >= 0 && Class58.anIntArrayArrayArray911[j33].length > l59) {
																			Class58.anIntArrayArrayArray911[j33][l59] = (new int[]{
																					(Class3_Sub28_Sub15.method633(0xfffc3b9, l71) >> 0x5f43122e) * 128, k77, 128 * Class3_Sub28_Sub15.method633(l71, 16383), j82
																			});
																			Class58.anIntArrayArrayArray911[j33][l59 + 1] = (new int[]{
																					128 * (Class3_Sub28_Sub15.method633(j80, 0xfffed27) >> 0xe3e5364e), l81, 128 * Class3_Sub28_Sub15.method633(j80, 16383)
																			});
																		}
																		continue;
																	}
																	if (j1 == 5407) {
																		int k33 = Class58.anIntArrayArrayArray911[Class140_Sub7.anIntArray2929[--k]].length >> 0x2b932e01;
																		Class140_Sub7.anIntArray2929[k++] = k33;
																		continue;
																	}
																	if (j1 == 5411) {
																		if (Class3_Sub13_Sub10.aFrame3121 != null)
																			GameObject.graphicsSettings(false, Node.anInt2577, -1, -1);
																		if (null == GameShell.frame)
																			Class99.method1596(RSInterface.method856(), (byte) 126, false);
																		else
																			System.exit(0);
																		continue;
																	}
																	if (j1 == 5419) {
																		RSString class94_24 = Class3_Sub9.aClass94_2331;
																		if (null != Class136.aClass64_1778) {
																			class94_24 = Class108.method1653(Class136.aClass64_1778.anInt979);
																			if (Class136.aClass64_1778.anObject974 != null) {
																				byte abyte0[] = null;
																				try {
																					abyte0 = ((String) Class136.aClass64_1778.anObject974).getBytes("ISO-8859-1");
																				} catch (UnsupportedEncodingException e) {
																					// TODO Auto-generated catch block
																					e.printStackTrace();
																				}
																				class94_24 = Class3_Sub13_Sub3.method178(abyte0, abyte0.length, 0);
																			}
																		}
																		Class3.aClass94Array75[l++] = class94_24;
																		continue;
																	}
																	if (j1 == 5420) {
																		Class140_Sub7.anIntArray2929[k++] = Signlink.anInt1214 != 3 ? 0 : 1;
																		continue;
																	}
																	if (j1 == 5421) {
																		if (null != Class3_Sub13_Sub10.aFrame3121)
																			GameObject.graphicsSettings(false, Node.anInt2577, -1, -1);
																		boolean flag5 = 1 == Class140_Sub7.anIntArray2929[--k];
																		RSString class94_25 = Class3.aClass94Array75[--l];
																		RSString class94_64 = RenderAnimationDefinition.method903(new RSString[]{
																				RSInterface.method856(), class94_25
																		}, (byte) -71);
																		if (null == GameShell.frame && (!flag5 || Signlink.anInt1214 == 3 || !Signlink.osName.startsWith("win") || Class106.hasInternetExplorer6)) {
																			Class99.method1596(class94_64, (byte) 127, flag5);
																		} else {
																			RSString.aBoolean2154 = flag5;
																			Class3_Sub13_Sub24.aClass94_3295 = class94_64;
																			try {
																				Class15.aClass64_351 = Class38.aClass87_665.method1452(new String(class94_64.method1568(0), "ISO-8859-1"), true);
																			} catch (UnsupportedEncodingException e) {
																				// TODO Auto-generated catch block
																				e.printStackTrace();
																			}
																		}
																		continue;
																	}
																	if (5422 == j1) {
																		int i72 = Class140_Sub7.anIntArray2929[--k];
																		l -= 2;
																		RSString class94_55 = Class3.aClass94Array75[1 + l];
																		RSString class94_26 = Class3.aClass94Array75[l];
																		if (class94_26.length(-127) > 0) {
																			if (null == Class3_Sub30_Sub1.aClass94Array3802)
																				Class3_Sub30_Sub1.aClass94Array3802 = new RSString[Class3_Sub13_Sub18.anIntArray3218[Class158.anInt2014]];
																			Class3_Sub30_Sub1.aClass94Array3802[i72] = class94_26;
																		}
																		if (class94_55.length(-118) > 0) {
																			if (OutputStream_Sub1.aClass94Array45 == null)
																				OutputStream_Sub1.aClass94Array45 = new RSString[Class3_Sub13_Sub18.anIntArray3218[Class158.anInt2014]];
																			OutputStream_Sub1.aClass94Array45[i72] = class94_55;
																		}
																		continue;
																	}
																	if (j1 == 5423) {
																		Class3.aClass94Array75[--l].method1549();
																		continue;
																	}
																	if (5424 == j1) {
																		k -= 11;
																		Class3_Sub28_Sub6.anInt3600 = Class140_Sub7.anIntArray2929[k];
																		Class62.anInt963 = Class140_Sub7.anIntArray2929[k - -1];
																		MouseListeningClass.anInt1926 = Class140_Sub7.anIntArray2929[k + 2];
																		Class136.anInt1771 = Class140_Sub7.anIntArray2929[3 + k];
																		WorldListCountry.anInt502 = Class140_Sub7.anIntArray2929[4 + k];
																		Class99.anInt1400 = Class140_Sub7.anIntArray2929[5 + k];
																		Class46.anInt739 = Class140_Sub7.anIntArray2929[6 + k];
																		Class79.anInt1126 = Class140_Sub7.anIntArray2929[7 + k];
																		Class140_Sub7.anInt2937 = Class140_Sub7.anIntArray2929[8 + k];
																		Class3_Sub13_Sub28.anInt3351 = Class140_Sub7.anIntArray2929[k + 9];
																		Class154.anInt1957 = Class140_Sub7.anIntArray2929[10 + k];
																		Class140_Sub6.spritesCacheIndex.method2144(0, WorldListCountry.anInt502);
																		Class140_Sub6.spritesCacheIndex.method2144(0, Class99.anInt1400);
																		Class140_Sub6.spritesCacheIndex.method2144(0, Class46.anInt739);
																		Class140_Sub6.spritesCacheIndex.method2144(0, Class79.anInt1126);
																		Class140_Sub6.spritesCacheIndex.method2144(0, Class140_Sub7.anInt2937);
																		CacheIndex.aBoolean1951 = true;
																		continue;
																	}
																	if (j1 == 5425) {
																		Class3_Sub13.method165();
																		CacheIndex.aBoolean1951 = false;
																		continue;
																	}
																	if (j1 == 5426) {
																		Class161.anInt2027 = Class140_Sub7.anIntArray2929[--k];
																		continue;
																	}
																	if (j1 != 5427)
																		break;
																	k -= 2;
																	Class99.anInt1403 = Class140_Sub7.anIntArray2929[k];
																	Class131.anInt1719 = Class140_Sub7.anIntArray2929[k + 1];
																	continue;
																}
																if (5600 > j1) {
																	if (5500 == j1) {
																		k -= 4;
																		int l33 = Class140_Sub7.anIntArray2929[k];
																		int l77 = Class140_Sub7.anIntArray2929[k - -3];
																		int j72 = Class140_Sub7.anIntArray2929[k - -2];
																		int i60 = Class140_Sub7.anIntArray2929[k + 1];
																		Class3_Sub20.method390(false, j72, i60, l77, (byte) -128, -Class82.anInt1152 + (0x3fff & l33), ((0xffffe30 & l33) >> 0x372f8c2e) - Class131.anInt1716);
																		continue;
																	}
																	if (j1 == 5501) {
																		k -= 4;
																		int j60 = Class140_Sub7.anIntArray2929[1 + k];
																		int i34 = Class140_Sub7.anIntArray2929[k];
																		int i78 = Class140_Sub7.anIntArray2929[k - -3];
																		int k72 = Class140_Sub7.anIntArray2929[k + 2];
																		Class164_Sub1.method2238(j60, (0x3fff & i34) - Class82.anInt1152, k72, -Class131.anInt1716 + ((0xffff221 & i34) >> 0xcd90732e), i78);
																		continue;
																	}
																	if (j1 == 5502) {
																		k -= 6;
																		int j34 = Class140_Sub7.anIntArray2929[k];
																		if (j34 >= 2)
																			throw new RuntimeException();
																		NPCDefinition.anInt1252 = j34;
																		int k60 = Class140_Sub7.anIntArray2929[k - -1];
																		if (1 + k60 >= Class58.anIntArrayArrayArray911[NPCDefinition.anInt1252].length >> 0x84afc601)
																			throw new RuntimeException();
																		Class73.anInt1081 = k60;
																		Class163_Sub2_Sub1.anInt4020 = 0;
																		Class134.anInt1759 = Class140_Sub7.anIntArray2929[k + 2];
																		Class3_Sub13.anInt2383 = Class140_Sub7.anIntArray2929[k + 3];
																		int l72 = Class140_Sub7.anIntArray2929[k + 4];
																		if (2 <= l72)
																			throw new RuntimeException();
																		Class3_Sub7.anInt2293 = l72;
																		int j78 = Class140_Sub7.anIntArray2929[5 + k];
																		if (Class58.anIntArrayArrayArray911[Class3_Sub7.anInt2293].length >> 0x4d73ee21 <= 1 + j78)
																			throw new RuntimeException();
																		Class39.anInt670 = j78;
																		Class133.anInt1753 = 3;
																		continue;
																	}
																	if (j1 == 5503) {
																		Class3_Sub28_Sub5.method560();
																		continue;
																	}
																	if (5504 == j1) {
																		k -= 2;
																		Class3_Sub9.anInt2309 = Class140_Sub7.anIntArray2929[k];
																		GraphicDefinition.CAMERA_DIRECTION = Class140_Sub7.anIntArray2929[k + 1];
																		if (Class133.anInt1753 == 2) {
																			Class3_Sub13_Sub25.anInt3315 = GraphicDefinition.CAMERA_DIRECTION;
																			Class139.anInt1823 = Class3_Sub9.anInt2309;
																		}
																		Class47.method1098((byte) -74);
																		continue;
																	}
																	if (j1 == 5505) {
																		Class140_Sub7.anIntArray2929[k++] = Class3_Sub9.anInt2309;
																		continue;
																	}
																	if (5506 != j1)
																		break;
																	Class140_Sub7.anIntArray2929[k++] = GraphicDefinition.CAMERA_DIRECTION;
																	continue;
																}
																if (j1 >= 5700) {
																	if (6100 > j1) {
																		if (j1 == 6001) {
																			int k34 = Class140_Sub7.anIntArray2929[--k];
																			if (k34 < 1)
																				k34 = 1;
																			if (k34 > 4)
																				k34 = 4;
																			Class3_Sub28_Sub10.anInt3625 = k34;
																			if (!HDToolKit.highDetail || !Class106.aBoolean1441) {
																				if (Class3_Sub28_Sub10.anInt3625 == 1)
																					Class51.method1137(0.9F);
																				if (Class3_Sub28_Sub10.anInt3625 == 2)
																					Class51.method1137(0.8F);
																				if (3 == Class3_Sub28_Sub10.anInt3625)
																					Class51.method1137(0.7F);
																				if (Class3_Sub28_Sub10.anInt3625 == 4)
																					Class51.method1137(0.6F);
																			}
																			if (HDToolKit.highDetail) {
																				Class3_Sub13_Sub14.method236();
																				if (!Class106.aBoolean1441)
																					Class84.method1417(104);
																			}
																			RSByteBuffer.method746((byte) -29);
																			Class119.method1730(Class38.aClass87_665);
																			Class140_Sub2.aBoolean2705 = false;
																			continue;
																		}
																		if (j1 == 6002) {
																			Class25.method957(96, 1 == Class140_Sub7.anIntArray2929[--k]);
																			Class3_Sub10.method139(66);
																			Class84.method1417(101);
																			RSByteBuffer.method792(0x8c1111);
																			Class119.method1730(Class38.aClass87_665);
																			Class140_Sub2.aBoolean2705 = false;
																			continue;
																		}
																		if (j1 == 6003) {
																			Class3_Sub28_Sub7.aBoolean3604 = Class140_Sub7.anIntArray2929[--k] == 1;
																			RSByteBuffer.method792(0x8c1111);
																			Class119.method1730(Class38.aClass87_665);
																			Class140_Sub2.aBoolean2705 = false;
																			continue;
																		}
																		if (j1 == 6005) {
																			KeyboardListener.aBoolean1905 = Class140_Sub7.anIntArray2929[--k] == 1;
																			Class84.method1417(112);
																			Class119.method1730(Class38.aClass87_665);
																			Class140_Sub2.aBoolean2705 = false;
																			continue;
																		}
																		if (j1 == 6006) {
																			Class25.aBoolean488 = Class140_Sub7.anIntArray2929[--k] == 1;
																			((Class102) Class51.anInterface2_838).method1616(!Class25.aBoolean488);
																			Class119.method1730(Class38.aClass87_665);
																			Class140_Sub2.aBoolean2705 = false;
																			continue;
																		}
																		if (j1 == 6007) {
																			RSInterface.aBoolean236 = Class140_Sub7.anIntArray2929[--k] == 1;
																			Class119.method1730(Class38.aClass87_665);
																			Class140_Sub2.aBoolean2705 = false;
																			continue;
																		}
																		if (j1 == 6008) {
																			WorldListEntry.aBoolean2623 = Class140_Sub7.anIntArray2929[--k] == 1;
																			Class119.method1730(Class38.aClass87_665);
																			Class140_Sub2.aBoolean2705 = false;
																			continue;
																		}
																		if (j1 == 6009) {
																			Class3_Sub13_Sub22.aBoolean3275 = Class140_Sub7.anIntArray2929[--k] == 1;
																			Class119.method1730(Class38.aClass87_665);
																			Class140_Sub2.aBoolean2705 = false;
																			continue;
																		}
																		if (j1 == 6010) {
																			Class140_Sub6.aBoolean2910 = 1 == Class140_Sub7.anIntArray2929[--k];
																			Class119.method1730(Class38.aClass87_665);
																			Class140_Sub2.aBoolean2705 = false;
																			continue;
																		}
																		if (j1 == 6011) {
																			int l34 = Class140_Sub7.anIntArray2929[--k];
																			if (l34 < 0 || l34 > 2)
																				l34 = 0;
																			Class80.anInt1137 = l34;
																			Class119.method1730(Class38.aClass87_665);
																			Class140_Sub2.aBoolean2705 = false;
																			continue;
																		}
																		if (6012 == j1) {
																			if (HDToolKit.highDetail)
																				Class3_Sub28_Sub4.method551(0, 0);
																			Class106.aBoolean1441 = Class140_Sub7.anIntArray2929[--k] == 1;
																			if (HDToolKit.highDetail && Class106.aBoolean1441) {
																				Class51.method1137(0.7F);
																			} else {
																				if (Class3_Sub28_Sub10.anInt3625 == 1)
																					Class51.method1137(0.9F);
																				if (Class3_Sub28_Sub10.anInt3625 == 2)
																					Class51.method1137(0.8F);
																				if (Class3_Sub28_Sub10.anInt3625 == 3)
																					Class51.method1137(0.7F);
																				if (Class3_Sub28_Sub10.anInt3625 == 4)
																					Class51.method1137(0.6F);
																			}
																			Class84.method1417(108);
																			Class119.method1730(Class38.aClass87_665);
																			Class140_Sub2.aBoolean2705 = false;
																			continue;
																		}
																		if (j1 == 6014) {
																			Class128.aBoolean1685 = Class140_Sub7.anIntArray2929[--k] == 1;
																			if (HDToolKit.highDetail)
																				Class84.method1417(109);
																			Class119.method1730(Class38.aClass87_665);
																			Class140_Sub2.aBoolean2705 = false;
																			continue;
																		}
																		if (j1 == 6015) {
																			Class38.aBoolean661 = Class140_Sub7.anIntArray2929[--k] == 1;
																			if (HDToolKit.highDetail)
																				Class3_Sub13_Sub14.method236();
																			Class119.method1730(Class38.aClass87_665);
																			Class140_Sub2.aBoolean2705 = false;
																			continue;
																		}
																		if (6016 == j1) {
																			int i35 = Class140_Sub7.anIntArray2929[--k];
																			if (HDToolKit.highDetail)
																				Class3_Sub28_Sub5.aBoolean3593 = true;
																			if (0 > i35 || i35 > 2)
																				i35 = 0;
																			Class3_Sub28_Sub14.anInt3671 = i35;
																			continue;
																		}
																		if (j1 == 6017) {
																			Class3_Sub13_Sub15.aBoolean3184 = Class140_Sub7.anIntArray2929[--k] == 1;
																			GameShell.method34();
																			Class119.method1730(Class38.aClass87_665);
																			Class140_Sub2.aBoolean2705 = false;
																			continue;
																		}
																		if (j1 == 6018) {
																			int j35 = Class140_Sub7.anIntArray2929[--k];
																			if (j35 < 0)
																				j35 = 0;
																			if (j35 > 127)
																				j35 = 127;
																			CS2Script.anInt2453 = j35;
																			Class119.method1730(Class38.aClass87_665);
																			Class140_Sub2.aBoolean2705 = false;
																			continue;
																		}
																		if (j1 == 6019) {
																			int k35 = Class140_Sub7.anIntArray2929[--k];
																			if (k35 < 0)
																				k35 = 0;
																			if (k35 > 255)
																				k35 = 255;
																			if (Class9.anInt120 != k35) {
																				if (Class9.anInt120 == 0 && Class129.anInt1691 != -1) {
																					Class70.method1285(Class75_Sub2.aClass153_2645, Class129.anInt1691, k35);
																					Class83.aBoolean1158 = false;
																				} else if (k35 == 0) {
																					GameObject.method1870();
																					Class83.aBoolean1158 = false;
																				} else {
																					Class3_Sub29.method736(k35, 115);
																				}
																				Class9.anInt120 = k35;
																			}
																			Class119.method1730(Class38.aClass87_665);
																			Class140_Sub2.aBoolean2705 = false;
																			continue;
																		}
																		if (j1 == 6020) {
																			int l35 = Class140_Sub7.anIntArray2929[--k];
																			if (l35 < 0)
																				l35 = 0;
																			if (127 < l35)
																				l35 = 127;
																			Class14.anInt340 = l35;
																			Class119.method1730(Class38.aClass87_665);
																			Class140_Sub2.aBoolean2705 = false;
																			continue;
																		}
																		if (j1 == 6021) {
																			Class73.aBoolean1084 = Class140_Sub7.anIntArray2929[--k] == 1;
																			RSByteBuffer.method792(0x8c1111);
																			continue;
																		}
																		if (j1 == 6023) {
																			int i36 = Class140_Sub7.anIntArray2929[--k];
																			if (0 > i36)
																				i36 = 0;
																			if (i36 > 2)
																				i36 = 2;
																			boolean flag6 = false;
																			if (96 > Class3_Sub24_Sub3.anInt3492) {
																				flag6 = true;
																				i36 = 0;
																			}
																			Class127_Sub1.method1758(i36);
																			Class119.method1730(Class38.aClass87_665);
																			Class140_Sub2.aBoolean2705 = false;
																			Class140_Sub7.anIntArray2929[k++] = flag6 ? 0 : 1;
																			continue;
																		}
																		if (j1 == 6024) {
																			int j36 = Class140_Sub7.anIntArray2929[--k];
																			if (j36 < 0 || 2 < j36)
																				j36 = 0;
																			Class3_Sub28_Sub9.anInt3622 = j36;
																			Class119.method1730(Class38.aClass87_665);
																			continue;
																		}
																		if (j1 != 6028)
																			break;
																		Class163_Sub3.aBoolean3004 = Class140_Sub7.anIntArray2929[--k] != 0;
																		Class119.method1730(Class38.aClass87_665);
																		continue;
																	}
																	if (j1 < 6200) {
																		if (j1 == 6101) {
																			Class140_Sub7.anIntArray2929[k++] = Class3_Sub28_Sub10.anInt3625;
																			continue;
																		}
																		if (j1 == 6102) {
																			Class140_Sub7.anIntArray2929[k++] = NPC.method1986(109) ? 1 : 0;
																			continue;
																		}
																		if (j1 == 6103) {
																			Class140_Sub7.anIntArray2929[k++] = Class3_Sub28_Sub7.aBoolean3604 ? 1 : 0;
																			continue;
																		}
																		if (j1 == 6105) {
																			Class140_Sub7.anIntArray2929[k++] = KeyboardListener.aBoolean1905 ? 1 : 0;
																			continue;
																		}
																		if (j1 == 6106) {
																			Class140_Sub7.anIntArray2929[k++] = Class25.aBoolean488 ? 1 : 0;
																			continue;
																		}
																		if (j1 == 6107) {
																			Class140_Sub7.anIntArray2929[k++] = RSInterface.aBoolean236 ? 1 : 0;
																			continue;
																		}
																		if (j1 == 6108) {
																			Class140_Sub7.anIntArray2929[k++] = WorldListEntry.aBoolean2623 ? 1 : 0;
																			continue;
																		}
																		if (6109 == j1) {
																			Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub22.aBoolean3275 ? 1 : 0;
																			continue;
																		}
																		if (j1 == 6110) {
																			Class140_Sub7.anIntArray2929[k++] = Class140_Sub6.aBoolean2910 ? 1 : 0;
																			continue;
																		}
																		if (j1 == 6111) {
																			Class140_Sub7.anIntArray2929[k++] = Class80.anInt1137;
																			continue;
																		}
																		if (6112 == j1) {
																			Class140_Sub7.anIntArray2929[k++] = Class106.aBoolean1441 ? 1 : 0;
																			continue;
																		}
																		if (6114 == j1) {
																			Class140_Sub7.anIntArray2929[k++] = Class128.aBoolean1685 ? 1 : 0;
																			continue;
																		}
																		if (j1 == 6115) {
																			Class140_Sub7.anIntArray2929[k++] = Class38.aBoolean661 ? 1 : 0;
																			continue;
																		}
																		if (j1 == 6116) {
																			Class140_Sub7.anIntArray2929[k++] = Class3_Sub28_Sub14.anInt3671;
																			continue;
																		}
																		if (6117 == j1) {
																			Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub15.aBoolean3184 ? 1 : 0;
																			continue;
																		}
																		if (j1 == 6118) {
																			Class140_Sub7.anIntArray2929[k++] = CS2Script.anInt2453;
																			continue;
																		}
																		if (6119 == j1) {
																			Class140_Sub7.anIntArray2929[k++] = Class9.anInt120;
																			continue;
																		}
																		if (j1 == 6120) {
																			Class140_Sub7.anIntArray2929[k++] = Class14.anInt340;
																			continue;
																		}
																		if (j1 == 6121) {
																			if (HDToolKit.highDetail)
																				Class140_Sub7.anIntArray2929[k++] = HDToolKit.aBoolean1809 ? 1 : 0;
																			else
																				Class140_Sub7.anIntArray2929[k++] = 0;
																			continue;
																		}
																		if (j1 == 6123) {
																			Class140_Sub7.anIntArray2929[k++] = Class127_Sub1.method1757();
																			continue;
																		}
																		if (j1 == 6124) {
																			Class140_Sub7.anIntArray2929[k++] = Class3_Sub28_Sub9.anInt3622;
																			continue;
																		}
																		if (j1 != 6128)
																			break;
																		Class140_Sub7.anIntArray2929[k++] = Class163_Sub3.aBoolean3004 ? 1 : 0;
																		continue;
																	}
																	if (j1 >= 6300) {
																		if (j1 < 6400) {
																			if (j1 == 6300) {
																				Class140_Sub7.anIntArray2929[k++] = (int) (Class5.method830((byte) -55) / 60000L);
																				continue;
																			}
																			if (j1 == 6301) {
																				Class140_Sub7.anIntArray2929[k++] = -11745 + (int) (Class5.method830((byte) -55) / 0x5265c00L);
																				continue;
																			}
																			if (j1 == 6302) {
																				k -= 3;
																				int i73 = Class140_Sub7.anIntArray2929[k + 2];
																				int l60 = Class140_Sub7.anIntArray2929[k - -1];
																				int k36 = Class140_Sub7.anIntArray2929[k];
																				Class3_Sub28_Sub9.aCalendar3616.clear();
																				Class3_Sub28_Sub9.aCalendar3616.set(11, 12);
																				Class3_Sub28_Sub9.aCalendar3616.set(i73, l60, k36);
																				Class140_Sub7.anIntArray2929[k++] = -11745 + (int) (Class3_Sub28_Sub9.aCalendar3616.getTime().getTime() / 0x5265c00L);
																				continue;
																			}
																			if (6303 == j1) {
																				Class3_Sub28_Sub9.aCalendar3616.clear();
																				Class3_Sub28_Sub9.aCalendar3616.setTime(new Date(Class5.method830((byte) -55)));
																				Class140_Sub7.anIntArray2929[k++] = Class3_Sub28_Sub9.aCalendar3616.get(1);
																				continue;
																			}
																			if (j1 != 6304)
																				break;
																			boolean flag7 = true;
																			int l36 = Class140_Sub7.anIntArray2929[--k];
																			if (l36 >= 0) {
																				if (l36 >= 1582) {
																					if (l36 % 4 == 0) {
																						if (l36 % 100 != 0)
																							flag7 = true;
																						else if (0 != l36 % 400)
																							flag7 = false;
																					} else {
																						flag7 = false;
																					}
																				} else {
																					flag7 = l36 % 4 == 0;
																				}
																			} else {
																				flag7 = (1 + l36) % 4 == 0;
																			}
																			Class140_Sub7.anIntArray2929[k++] = flag7 ? 1 : 0;
																			continue;
																		}
																		if (j1 >= 6500) {
																			if (j1 < 6600) {
																				if (j1 == 6500) {
																					if (Class143.loadingStage != 10 || Class3_Sub13_Sub31.anInt3375 != 0 || 0 != Class3_Sub13_Sub25.loginStage || 0 != Canvas_Sub1.registryStage)
																						Class140_Sub7.anIntArray2929[k++] = 1;
																					else
																						Class140_Sub7.anIntArray2929[k++] = Class121.method1735() == -1 ? 0 : 1;
																					continue;
																				}
																				if (j1 == 6501) {
																					WorldListEntry class44_sub1 = Class140_Sub2.method1953();
																					if (class44_sub1 == null) {
																						Class140_Sub7.anIntArray2929[k++] = -1;
																						Class140_Sub7.anIntArray2929[k++] = 0;
																						Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
																						Class140_Sub7.anIntArray2929[k++] = 0;
																						Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
																						Class140_Sub7.anIntArray2929[k++] = 0;
																					} else {
																						Class140_Sub7.anIntArray2929[k++] = class44_sub1.worldId;
																						Class140_Sub7.anIntArray2929[k++] = class44_sub1.settings;
																						Class3.aClass94Array75[l++] = class44_sub1.activity;
																						WorldListCountry class26 = class44_sub1.method1078(60);
																						Class140_Sub7.anIntArray2929[k++] = class26.flagId;
																						Class3.aClass94Array75[l++] = class26.name;
																						Class140_Sub7.anIntArray2929[k++] = class44_sub1.anInt722;
																					}
																					continue;
																				}
																				if (j1 == 6502) {
																					WorldListEntry class44_sub1_1 = method1107(5422);
																					if (null == class44_sub1_1) {
																						Class140_Sub7.anIntArray2929[k++] = -1;
																						Class140_Sub7.anIntArray2929[k++] = 0;
																						Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
																						Class140_Sub7.anIntArray2929[k++] = 0;
																						Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
																						Class140_Sub7.anIntArray2929[k++] = 0;
																					} else {
																						Class140_Sub7.anIntArray2929[k++] = class44_sub1_1.worldId;
																						Class140_Sub7.anIntArray2929[k++] = class44_sub1_1.settings;
																						Class3.aClass94Array75[l++] = class44_sub1_1.activity;
																						WorldListCountry class26_1 = class44_sub1_1.method1078(70);
																						Class140_Sub7.anIntArray2929[k++] = class26_1.flagId;
																						Class3.aClass94Array75[l++] = class26_1.name;
																						Class140_Sub7.anIntArray2929[k++] = class44_sub1_1.anInt722;
																					}
																					continue;
																				}
																				if (j1 == 6503) {
																					int i37 = Class140_Sub7.anIntArray2929[--k];
																					if (Class143.loadingStage != 10 || Class3_Sub13_Sub31.anInt3375 != 0 || Class3_Sub13_Sub25.loginStage != 0 || Canvas_Sub1.registryStage != 0)
																						Class140_Sub7.anIntArray2929[k++] = 0;
																					else
																						Class140_Sub7.anIntArray2929[k++] = Class104.method1627(i37, (byte) -7) ? 1 : 0;
																					continue;
																				}
																				if (j1 == 6504) {
																					RSString.anInt2148 = Class140_Sub7.anIntArray2929[--k];
																					Class119.method1730(Class38.aClass87_665);
																					continue;
																				}
																				if (6505 == j1) {
																					Class140_Sub7.anIntArray2929[k++] = RSString.anInt2148;
																					continue;
																				}
																				if (j1 == 6506) {
																					int j37 = Class140_Sub7.anIntArray2929[--k];
																					WorldListEntry class44_sub1_2 = Class3_Sub8.getWorld(120, j37);
																					if (class44_sub1_2 == null) {
																						Class140_Sub7.anIntArray2929[k++] = -1;
																						Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
																						Class140_Sub7.anIntArray2929[k++] = 0;
																						Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
																						Class140_Sub7.anIntArray2929[k++] = 0;
																					} else {
																						Class140_Sub7.anIntArray2929[k++] = class44_sub1_2.settings;
																						Class3.aClass94Array75[l++] = class44_sub1_2.activity;
																						WorldListCountry class26_2 = class44_sub1_2.method1078(-87);
																						Class140_Sub7.anIntArray2929[k++] = class26_2.flagId;
																						Class3.aClass94Array75[l++] = class26_2.name;
																						Class140_Sub7.anIntArray2929[k++] = class44_sub1_2.anInt722;
																					}
																					continue;
																				}
																				if (j1 != 6507)
																					break;
																				k -= 4;
																				int j73 = Class140_Sub7.anIntArray2929[k + 2];
																				int k37 = Class140_Sub7.anIntArray2929[k];
																				boolean flag11 = Class140_Sub7.anIntArray2929[k - -3] == 1;
																				boolean flag8 = Class140_Sub7.anIntArray2929[1 + k] == 1;
																				Class134.method1808(j73, flag8, k37, flag11);
																				continue;
																			}
																			if (j1 >= 6700)
																				break;
																			if (6600 == j1) {
																				Class15.aBoolean346 = Class140_Sub7.anIntArray2929[--k] == 1;
																				Class119.method1730(Class38.aClass87_665);
																				continue;
																			}
																			if (j1 != 6601)
																				break;
																			Class140_Sub7.anIntArray2929[k++] = Class15.aBoolean346 ? 1 : 0;
																			continue;
																		}
																		if (6405 == j1) {
																			Class140_Sub7.anIntArray2929[k++] = Class47.method1088(false) ? 1 : 0;
																			continue;
																		}
																		if (j1 != 6406)
																			break;
																		Class140_Sub7.anIntArray2929[k++] = Class159.method2194() ? 1 : 0;
																		continue;
																	}
																	if (j1 == 6200) {
																		k -= 2;
																		Class106.aShort1444 = (short) Class140_Sub7.anIntArray2929[k];
																		if (0 >= Class106.aShort1444)
																			Class106.aShort1444 = 256;
																		Class3_Sub13_Sub3.aShort3052 = (short) Class140_Sub7.anIntArray2929[1 + k];
																		if (Class3_Sub13_Sub3.aShort3052 <= 0)
																			Class3_Sub13_Sub3.aShort3052 = 205;
																		continue;
																	}
																	if (j1 == 6201) {
																		k -= 2;
																		OutputStream_Sub1.aShort46 = (short) Class140_Sub7.anIntArray2929[k];
																		if (OutputStream_Sub1.aShort46 <= 0)
																			OutputStream_Sub1.aShort46 = 256;
																		ObjectDefinition.aShort1535 = (short) Class140_Sub7.anIntArray2929[1 + k];
																		if (ObjectDefinition.aShort1535 <= 0)
																			ObjectDefinition.aShort1535 = 320;
																		continue;
																	}
																	if (j1 == 6202) {
																		k -= 4;
																		Class3_Sub13_Sub19.aShort3241 = (short) Class140_Sub7.anIntArray2929[k];
																		if (Class3_Sub13_Sub19.aShort3241 <= 0)
																			Class3_Sub13_Sub19.aShort3241 = 1;
																		PacketParser.aShort83 = (short) Class140_Sub7.anIntArray2929[1 + k];
																		if (PacketParser.aShort83 > 0) {
																			if (Class3_Sub13_Sub19.aShort3241 > PacketParser.aShort83)
																				PacketParser.aShort83 = Class3_Sub13_Sub19.aShort3241;
																		} else {
																			PacketParser.aShort83 = 32767;
																		}
																		WorldListCountry.aShort505 = (short) Class140_Sub7.anIntArray2929[2 + k];
																		if (WorldListCountry.aShort505 <= 0)
																			WorldListCountry.aShort505 = 1;
																		Class3_Sub13_Sub23_Sub1.aShort4038 = (short) Class140_Sub7.anIntArray2929[k - -3];
																		if (Class3_Sub13_Sub23_Sub1.aShort4038 > 0) {
																			if (WorldListCountry.aShort505 > Class3_Sub13_Sub23_Sub1.aShort4038)
																				Class3_Sub13_Sub23_Sub1.aShort4038 = WorldListCountry.aShort505;
																		} else {
																			Class3_Sub13_Sub23_Sub1.aShort4038 = 32767;
																		}
																		continue;
																	}
																	if (j1 == 6203) {
																		Class65.method1239(Class168.aClass11_2091.anInt168, 81, 0, Class168.aClass11_2091.anInt193, 0, false);
																		Class140_Sub7.anIntArray2929[k++] = Class96.anInt1358;
																		Class140_Sub7.anIntArray2929[k++] = Canvas_Sub2.anInt31;
																		continue;
																	}
																	if (6204 == j1) {
																		Class140_Sub7.anIntArray2929[k++] = OutputStream_Sub1.aShort46;
																		Class140_Sub7.anIntArray2929[k++] = ObjectDefinition.aShort1535;
																		continue;
																	}
																	if (j1 != 6205)
																		break;
																	Class140_Sub7.anIntArray2929[k++] = Class106.aShort1444;
																	Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub3.aShort3052;
																	continue;
																}
																if (j1 == 5600) {
																	l -= 2;
																	RSString class94_27 = Class3.aClass94Array75[l];
																	RSString class94_56 = Class3.aClass94Array75[l + 1];
																	int k73 = Class140_Sub7.anIntArray2929[--k];
																	if (Class143.loadingStage == 10 && Class3_Sub13_Sub31.anInt3375 == 0 && Class3_Sub13_Sub25.loginStage == 0 && Canvas_Sub1.registryStage == 0 && Class43.anInt692 == 0)
																		Class131.method1793(class94_27, class94_56, k73, (byte) -38);
																	continue;
																}
																if (j1 == 5601) {
																	Class110.method1681(-1);
																	continue;
																}
																if (j1 == 5602) {
																	if (0 == Class3_Sub13_Sub25.loginStage)
																		Class158.anInt2005 = -2;
																	continue;
																}
																if (j1 == 5603) {
																	k -= 4;
																	if (Class143.loadingStage == 10 && 0 == Class3_Sub13_Sub31.anInt3375 && Class3_Sub13_Sub25.loginStage == 0 && Canvas_Sub1.registryStage == 0 && Class43.anInt692 == 0)
																		CS2Script.sendRegistryRequest(Class140_Sub7.anIntArray2929[k - -2], Class140_Sub7.anIntArray2929[k + 3], Class140_Sub7.anIntArray2929[k], Class140_Sub7.anIntArray2929[k + 1]);
																	continue;
																}
																if (j1 == 5604) {
																	l--;
																	if (Class143.loadingStage == 10 && Class3_Sub13_Sub31.anInt3375 == 0 && Class3_Sub13_Sub25.loginStage == 0 && Canvas_Sub1.registryStage == 0 && Class43.anInt692 == 0) {
																		Class40.method1041(Class3.aClass94Array75[l].toLong(-108), Class3.aClass94Array75[l]);
																		continue;
																	}
																}
																if (j1 == 5605) {
																	k -= 4;
																	l -= 2;
																	if (Class143.loadingStage == 10 && 0 == Class3_Sub13_Sub31.anInt3375 && Class3_Sub13_Sub25.loginStage == 0 && Canvas_Sub1.registryStage == 0 && Class43.anInt692 == 0)
																		Class3_Sub28_Sub6.a(Class140_Sub7.anIntArray2929[k], Class140_Sub7.anIntArray2929[k - -3], Class140_Sub7.anIntArray2929[1 + k], Class3.aClass94Array75[1 + l], Class3.aClass94Array75[l].toLong(-125), Class140_Sub7.anIntArray2929[2 + k], Class3.aClass94Array75[l]);
																	continue;
																}
																if (j1 == 5606) {
																	if (Canvas_Sub1.registryStage == 0)
																		Class130.anInt1711 = -2;
																	continue;
																}
																if (j1 == 5607) {
																	Class140_Sub7.anIntArray2929[k++] = Class158.anInt2005;
																	continue;
																}
																if (j1 == 5608) {
																	Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub34.anInt3413;
																	continue;
																}
																if (5609 == j1) {
																	Class140_Sub7.anIntArray2929[k++] = Class130.anInt1711;
																	continue;
																}
																if (j1 == 5610) {
																	for (int l37 = 0; l37 < 5; l37++)
																		Class3.aClass94Array75[l++] = Class3_Sub13_Sub33.aClass94Array3391.length <= l37 ? Class3_Sub9.aClass94_2331 : Class3_Sub13_Sub33.aClass94Array3391[l37].method1545();

																	Class3_Sub13_Sub33.aClass94Array3391 = null;
																	continue;
																}
																if (j1 != 5611)
																	break;
																Class140_Sub7.anIntArray2929[k++] = Class3_Sub26.anInt2561;
																continue;
															}
															if (4500 != j1)
																break;
															k -= 2;
															int i38 = Class140_Sub7.anIntArray2929[k];
															int i61 = Class140_Sub7.anIntArray2929[k - -1];
															Class3_Sub28_Sub9 class3_sub28_sub9_1 = Class61.method1210(i61);
															if (!class3_sub28_sub9_1.method585())
																Class140_Sub7.anIntArray2929[k++] = Class72.method1292((byte) 94, i38).method600(i61, class3_sub28_sub9_1.anInt3614);
															else
																Class3.aClass94Array75[l++] = Class72.method1292((byte) 31, i38).method604(class3_sub28_sub9_1.aClass94_3619, (byte) -44, i61);
															continue;
														}
														if (j1 != 4400)
															break;
														k -= 2;
														int j61 = Class140_Sub7.anIntArray2929[k - -1];
														int j38 = Class140_Sub7.anIntArray2929[k];
														Class3_Sub28_Sub9 class3_sub28_sub9_2 = Class61.method1210(j61);
														if (!class3_sub28_sub9_2.method585())
															Class140_Sub7.anIntArray2929[k++] = Class162.getObjectDefinition(4, j38).method1691(class3_sub28_sub9_2.anInt3614, j61, (byte) 105);
														else
															Class3.aClass94Array75[l++] = Class162.getObjectDefinition(4, j38).method1698(class3_sub28_sub9_2.aClass94_3619, j61);
														continue;
													}
													if (j1 == 4200) {
														int k38 = Class140_Sub7.anIntArray2929[--k];
														Class3.aClass94Array75[l++] = Class38.getItemDefinition(k38, (byte) 72).name;
														continue;
													}
													if (j1 == 4201) {
														k -= 2;
														int l38 = Class140_Sub7.anIntArray2929[k];
														int k61 = Class140_Sub7.anIntArray2929[k - -1];
														ItemDefinition class48_2 = Class38.getItemDefinition(l38, (byte) 77);
														if (k61 < 1 || k61 > 5 || class48_2.groundOptions[-1 + k61] == null)
															Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
														else
															Class3.aClass94Array75[l++] = class48_2.groundOptions[k61 - 1];
														continue;
													}
													if (j1 == 4202) {
														k -= 2;
														int i39 = Class140_Sub7.anIntArray2929[k];
														int l61 = Class140_Sub7.anIntArray2929[k + 1];
														ItemDefinition class48_3 = Class38.getItemDefinition(i39, (byte) 70);
														if (l61 >= 1 && l61 <= 5 && null != class48_3.inventoryOptions[l61 + -1]) {
															Class3.aClass94Array75[l++] = class48_3.inventoryOptions[-1 + l61];
														} else {
															Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
														}
														continue;
													}
													if (j1 == 4203) {
														int j39 = Class140_Sub7.anIntArray2929[--k];
														Class140_Sub7.anIntArray2929[k++] = Class38.getItemDefinition(j39, (byte) 85).value;
														continue;
													}
													if (j1 == 4204) {
														int k39 = Class140_Sub7.anIntArray2929[--k];
														Class140_Sub7.anIntArray2929[k++] = Class38.getItemDefinition(k39, (byte) 99).stackingType == 1 ? 1 : 0;
														continue;
													}
													if (4205 == j1) {
														int l39 = Class140_Sub7.anIntArray2929[--k];
														ItemDefinition class48 = Class38.getItemDefinition(l39, (byte) 96);
														if (-1 == class48.anInt791 && class48.anInt789 >= 0)
															Class140_Sub7.anIntArray2929[k++] = class48.anInt789;
														else
															Class140_Sub7.anIntArray2929[k++] = l39;
														continue;
													}
													if (j1 == 4206) {
														int i40 = Class140_Sub7.anIntArray2929[--k];
														ItemDefinition class48_1 = Class38.getItemDefinition(i40, (byte) 126);
														if (0 <= class48_1.anInt791 && class48_1.anInt789 >= 0)
															Class140_Sub7.anIntArray2929[k++] = class48_1.anInt789;
														else
															Class140_Sub7.anIntArray2929[k++] = i40;
														continue;
													}
													if (j1 == 4207) {
														int j40 = Class140_Sub7.anIntArray2929[--k];
														Class140_Sub7.anIntArray2929[k++] = Class38.getItemDefinition(j40, (byte) 121).membersItem ? 1 : 0;
														continue;
													}
													if (j1 == 4208) {
														k -= 2;
														int k40 = Class140_Sub7.anIntArray2929[k];
														int i62 = Class140_Sub7.anIntArray2929[k - -1];
														Class3_Sub28_Sub9 class3_sub28_sub9_3 = Class61.method1210(i62);
														if (class3_sub28_sub9_3.method585())
															Class3.aClass94Array75[l++] = Class38.getItemDefinition(k40, (byte) 126).method1105(107, class3_sub28_sub9_3.aClass94_3619, i62);
														else
															Class140_Sub7.anIntArray2929[k++] = Class38.getItemDefinition(k40, (byte) 79).method1115(class3_sub28_sub9_3.anInt3614, -119, i62);
														continue;
													}
													if (4210 == j1) {
														RSString class94_28 = Class3.aClass94Array75[--l];
														int j62 = Class140_Sub7.anIntArray2929[--k];
														NPCDefinition.method1480(j62 == 1, class94_28, 102);
														Class140_Sub7.anIntArray2929[k++] = Class62.anInt952;
														continue;
													}
													if (j1 == 4211) {
														if (null == Class99.aShortArray1398 || Class140_Sub4.anInt2756 >= Class62.anInt952)
															Class140_Sub7.anIntArray2929[k++] = -1;
														else
															Class140_Sub7.anIntArray2929[k++] = Class3_Sub28_Sub15.method633(Class99.aShortArray1398[Class140_Sub4.anInt2756++], 65535);
														continue;
													}
													if (4212 != j1)
														break;
													Class140_Sub4.anInt2756 = 0;
													continue;
												}
												if (4100 == j1) {
													RSString class94_29 = Class3.aClass94Array75[--l];
													int k62 = Class140_Sub7.anIntArray2929[--k];
													Class3.aClass94Array75[l++] = RenderAnimationDefinition.method903(new RSString[]{
															class94_29, Class72.method1298((byte) 9, k62)
													}, (byte) -94);
													continue;
												}
												if (j1 == 4101) {
													l -= 2;
													RSString class94_57 = Class3.aClass94Array75[l + 1];
													RSString class94_30 = Class3.aClass94Array75[l];
													Class3.aClass94Array75[l++] = RenderAnimationDefinition.method903(new RSString[]{
															class94_30, class94_57
													}, (byte) -106);
													continue;
												}
												if (4102 == j1) {
													RSString class94_31 = Class3.aClass94Array75[--l];
													int l62 = Class140_Sub7.anIntArray2929[--k];
													Class3.aClass94Array75[l++] = RenderAnimationDefinition.method903(new RSString[]{
															class94_31, Class61.method1218(true, 127, l62)
													}, (byte) -119);
													continue;
												}
												if (j1 == 4103) {
													RSString class94_32 = Class3.aClass94Array75[--l];
													Class3.aClass94Array75[l++] = class94_32.method1534(-98);
													continue;
												}
												if (4104 == j1) {
													int l40 = Class140_Sub7.anIntArray2929[--k];
													long l63 = 0xec44e2dc00L + (long) l40 * 0x5265c00L;
													Class3_Sub28_Sub9.aCalendar3616.setTime(new Date(l63));
													int k78 = Class3_Sub28_Sub9.aCalendar3616.get(5);
													int k80 = Class3_Sub28_Sub9.aCalendar3616.get(2);
													int i82 = Class3_Sub28_Sub9.aCalendar3616.get(1);
													Class3.aClass94Array75[l++] = RenderAnimationDefinition.method903(new RSString[]{
															Class72.method1298((byte) 9, k78), Class93.aClass94_1326, TextCore.MonthsOfTheYear[k80], Class93.aClass94_1326, Class72.method1298((byte) 9, i82)
													}, (byte) -122);
													continue;
												}
												if (4105 == j1) {
													l -= 2;
													RSString class94_58 = Class3.aClass94Array75[l + 1];
													RSString class94_33 = Class3.aClass94Array75[l];
													if (Class102.player.class52 == null || !Class102.player.class52.aBoolean864)
														Class3.aClass94Array75[l++] = class94_33;
													else
														Class3.aClass94Array75[l++] = class94_58;
													continue;
												}
												if (j1 == 4106) {
													int i41 = Class140_Sub7.anIntArray2929[--k];
													Class3.aClass94Array75[l++] = Class72.method1298((byte) 9, i41);
													continue;
												}
												if (j1 == 4107) {
													l -= 2;
													Class140_Sub7.anIntArray2929[k++] = Class3.aClass94Array75[l].method1546((byte) -63, Class3.aClass94Array75[l - -1]);
													continue;
												}
												if (4108 == j1) {
													RSString class94_34 = Class3.aClass94Array75[--l];
													k -= 2;
													int l73 = Class140_Sub7.anIntArray2929[k - -1];
													int i63 = Class140_Sub7.anIntArray2929[k];
													Class140_Sub7.anIntArray2929[k++] = Class86.method1430(-28922, l73).method684(class94_34, i63);
													continue;
												}
												if (j1 == 4109) {
													k -= 2;
													RSString class94_35 = Class3.aClass94Array75[--l];
													int i74 = Class140_Sub7.anIntArray2929[1 + k];
													int j63 = Class140_Sub7.anIntArray2929[k];
													Class140_Sub7.anIntArray2929[k++] = Class86.method1430(-28922, i74).method680(class94_35, j63);
													continue;
												}
												if (j1 == 4110) {
													l -= 2;
													RSString class94_36 = Class3.aClass94Array75[l];
													RSString class94_59 = Class3.aClass94Array75[l - -1];
													if (1 == Class140_Sub7.anIntArray2929[--k])
														Class3.aClass94Array75[l++] = class94_36;
													else
														Class3.aClass94Array75[l++] = class94_59;
													continue;
												}
												if (4111 == j1) {
													RSString class94_37 = Class3.aClass94Array75[--l];
													Class3.aClass94Array75[l++] = Class3_Sub28_Sub17.method686(class94_37);
													continue;
												}
												if (4112 == j1) {
													RSString class94_38 = Class3.aClass94Array75[--l];
													int k63 = Class140_Sub7.anIntArray2929[--k];
													if (k63 == -1)
														throw new RuntimeException("null char");
													Class3.aClass94Array75[l++] = class94_38.method1548(false, k63);
													continue;
												}
												if (j1 == 4113) {
													int j41 = Class140_Sub7.anIntArray2929[--k];
													Class140_Sub7.anIntArray2929[k++] = Class164_Sub2.method2248(j41) ? 1 : 0;
													continue;
												}
												if (j1 == 4114) {
													int k41 = Class140_Sub7.anIntArray2929[--k];
													Class140_Sub7.anIntArray2929[k++] = Class44.method1066(k41) ? 1 : 0;
													continue;
												}
												if (j1 == 4115) {
													int l41 = Class140_Sub7.anIntArray2929[--k];
													Class140_Sub7.anIntArray2929[k++] = Class3_Sub24_Sub4.method487(l41, (byte) -85) ? 1 : 0;
													continue;
												}
												if (4116 == j1) {
													int i42 = Class140_Sub7.anIntArray2929[--k];
													Class140_Sub7.anIntArray2929[k++] = Class3_Sub28_Sub3.method544(-49, i42) ? 1 : 0;
													continue;
												}
												if (j1 == 4117) {
													RSString class94_39 = Class3.aClass94Array75[--l];
													if (class94_39 != null)
														Class140_Sub7.anIntArray2929[k++] = class94_39.length(-96);
													else
														Class140_Sub7.anIntArray2929[k++] = 0;
													continue;
												}
												if (j1 == 4118) {
													k -= 2;
													RSString class94_40 = Class3.aClass94Array75[--l];
													int i64 = Class140_Sub7.anIntArray2929[k];
													int j74 = Class140_Sub7.anIntArray2929[1 + k];
													Class3.aClass94Array75[l++] = class94_40.method1557(j74, 0, i64);
													continue;
												}
												if (j1 == 4119) {
													RSString class94_41 = Class3.aClass94Array75[--l];
													RSString class94_60 = Class47.method1090((byte) -87, class94_41.length(-44));
													boolean flag9 = false;
													for (int l78 = 0; class94_41.length(-113) > l78; l78++) {
														int l80 = class94_41.charAt(l78, (byte) -40);
														if (l80 == 60) {
															flag9 = true;
															continue;
														}
														if (l80 == 62) {
															flag9 = false;
														} else if (!flag9)
															class94_60.method1572(l80, (byte) 125);
													}

													class94_60.method1576();
													Class3.aClass94Array75[l++] = class94_60;
													continue;
												}
												if (j1 == 4120) {
													k -= 2;
													RSString class94_42 = Class3.aClass94Array75[--l];
													int j64 = Class140_Sub7.anIntArray2929[k];
													int k74 = Class140_Sub7.anIntArray2929[1 + k];
													Class140_Sub7.anIntArray2929[k++] = class94_42.method1555(j64, k74);
													continue;
												}
												if (j1 == 4121) {
													l -= 2;
													RSString class94_43 = Class3.aClass94Array75[l];
													RSString class94_61 = Class3.aClass94Array75[1 + l];
													int l74 = Class140_Sub7.anIntArray2929[--k];
													Class140_Sub7.anIntArray2929[k++] = class94_43.method1566(class94_61, l74);
													continue;
												}
												if (j1 == 4122) {
													int j42 = Class140_Sub7.anIntArray2929[--k];
													Class140_Sub7.anIntArray2929[k++] = Class3_Sub13_Sub34.method332(2, j42);
													continue;
												}
												if (j1 == 4123) {
													int k42 = Class140_Sub7.anIntArray2929[--k];
													Class140_Sub7.anIntArray2929[k++] = RuntimeException_Sub1.method2287(k42, (byte) 59);
													continue;
												}
												if (j1 != 4124)
													break;
												boolean flag1 = Class140_Sub7.anIntArray2929[--k] != 0;
												int k64 = Class140_Sub7.anIntArray2929[--k];
												Class3.aClass94Array75[l++] = Class3_Sub23.method407(Class3_Sub20.language, flag1, 0, k64);
												continue;
											}
											RSInterface class11_8 = Class7.getRSInterface((byte) 115, Class140_Sub7.anIntArray2929[--k]);
											if (j1 == 2800) {
												Class140_Sub7.anIntArray2929[k++] = Client.method44(class11_8).method101(-94);
												continue;
											}
											if (j1 == 2801) {
												int l64 = Class140_Sub7.anIntArray2929[--k];
												l64--;
												if (class11_8.aClass94Array171 != null && class11_8.aClass94Array171.length > l64 && null != class11_8.aClass94Array171[l64])
													Class3.aClass94Array75[l++] = class11_8.aClass94Array171[l64];
												else
													Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
												continue;
											}
											if (j1 != 2802)
												break;
											if (class11_8.aClass94_277 != null)
												Class3.aClass94Array75[l++] = class11_8.aClass94_277;
											else
												Class3.aClass94Array75[l++] = Class3_Sub9.aClass94_2331;
											continue;
										}
										if (j1 == 2700) {
											RSInterface class11_9 = Class7.getRSInterface((byte) 126, Class140_Sub7.anIntArray2929[--k]);
											Class140_Sub7.anIntArray2929[k++] = class11_9.anInt192;
											continue;
										}
										if (j1 == 2701) {
											RSInterface class11_10 = Class7.getRSInterface((byte) 117, Class140_Sub7.anIntArray2929[--k]);
											if (-1 != class11_10.anInt192)
												Class140_Sub7.anIntArray2929[k++] = class11_10.anInt271;
											else
												Class140_Sub7.anIntArray2929[k++] = 0;
											continue;
										}
										if (j1 == 2702) {
											int l42 = Class140_Sub7.anIntArray2929[--k];
											Class3_Sub31 class3_sub31 = (Class3_Sub31) Class3_Sub13_Sub17.aClass130_3208.method1780(l42, 0);
											if (class3_sub31 == null)
												Class140_Sub7.anIntArray2929[k++] = 0;
											else
												Class140_Sub7.anIntArray2929[k++] = 1;
											continue;
										}
										if (j1 == 2703) {
											RSInterface class11_11 = Class7.getRSInterface((byte) 125, Class140_Sub7.anIntArray2929[--k]);
											if (null == class11_11.aClass11Array262) {
												Class140_Sub7.anIntArray2929[k++] = 0;
											} else {
												int i65 = class11_11.aClass11Array262.length;
												int i75 = 0;
												do {
													if (class11_11.aClass11Array262.length <= i75)
														break;
													if (null == class11_11.aClass11Array262[i75]) {
														i65 = i75;
														break;
													}
													i75++;
												} while (true);
												Class140_Sub7.anIntArray2929[k++] = i65;
											}
											continue;
										}
										if (j1 != 2704 && 2705 != j1)
											break;
										k -= 2;
										int i43 = Class140_Sub7.anIntArray2929[k];
										int j65 = Class140_Sub7.anIntArray2929[k + 1];
										Class3_Sub31 class3_sub31_1 = (Class3_Sub31) Class3_Sub13_Sub17.aClass130_3208.method1780(i43, 0);
										if (class3_sub31_1 == null || class3_sub31_1.anInt2602 != j65)
											Class140_Sub7.anIntArray2929[k++] = 0;
										else
											Class140_Sub7.anIntArray2929[k++] = 1;
										continue;
									}
									RSInterface class11_12 = Class7.getRSInterface((byte) 124, Class140_Sub7.anIntArray2929[--k]);
									if (2600 == j1) {
										Class140_Sub7.anIntArray2929[k++] = class11_12.anInt247;
										continue;
									}
									if (j1 == 2601) {
										Class140_Sub7.anIntArray2929[k++] = class11_12.anInt208;
										continue;
									}
									if (j1 == 2602) {
										Class3.aClass94Array75[l++] = class11_12.aClass94_232;
										continue;
									}
									if (j1 == 2603) {
										Class140_Sub7.anIntArray2929[k++] = class11_12.anInt240;
										continue;
									}
									if (j1 == 2604) {
										Class140_Sub7.anIntArray2929[k++] = class11_12.anInt252;
										continue;
									}
									if (j1 == 2605) {
										Class140_Sub7.anIntArray2929[k++] = class11_12.anInt164;
										continue;
									}
									if (j1 == 2606) {
										Class140_Sub7.anIntArray2929[k++] = class11_12.anInt182;
										continue;
									}
									if (j1 == 2607) {
										Class140_Sub7.anIntArray2929[k++] = class11_12.anInt280;
										continue;
									}
									if (2608 == j1) {
										Class140_Sub7.anIntArray2929[k++] = class11_12.anInt308;
										continue;
									}
									if (j1 == 2609) {
										Class140_Sub7.anIntArray2929[k++] = class11_12.anInt223;
										continue;
									}
									if (j1 == 2610) {
										Class140_Sub7.anIntArray2929[k++] = class11_12.anInt258;
										continue;
									}
									if (j1 == 2611) {
										Class140_Sub7.anIntArray2929[k++] = class11_12.anInt264;
										continue;
									}
									if (2612 != j1)
										break;
									Class140_Sub7.anIntArray2929[k++] = class11_12.spriteArchiveId;
									continue;
								}
								RSInterface class11_13 = flag ? Class164.aClass11_2055 : Class133.aClass11_1749;
								if (j1 == 1700) {
									Class140_Sub7.anIntArray2929[k++] = class11_13.anInt192;
									continue;
								}
								if (1701 == j1) {
									if (class11_13.anInt192 == -1)
										Class140_Sub7.anIntArray2929[k++] = 0;
									else
										Class140_Sub7.anIntArray2929[k++] = class11_13.anInt271;
									continue;
								}
								if (j1 != 1702)
									break;
								Class140_Sub7.anIntArray2929[k++] = class11_13.anInt191;
							} else {
								RSInterface class11_14;
								if (j1 < 2000) {
									class11_14 = flag ? Class164.aClass11_2055 : Class133.aClass11_1749;
								} else {
									j1 -= 1000;
									class11_14 = Class7.getRSInterface((byte) 115, Class140_Sub7.anIntArray2929[--k]);
								}
								int ai3[] = null;
								RSString class94_62 = Class3.aClass94Array75[--l];
								if (class94_62.length(-127) > 0 && class94_62.charAt(class94_62.length(-92) + -1, (byte) -96) == 89) {
									int i79 = Class140_Sub7.anIntArray2929[--k];
									if (i79 > 0) {
										ai3 = new int[i79];
										while (i79-- > 0)
											ai3[i79] = Class140_Sub7.anIntArray2929[--k];
									}
									class94_62 = class94_62.method1557(class94_62.length(-79) - 1, 0, 0);
								}
								Object aobj1[] = new Object[class94_62.length(-48) - -1];
								for (int i81 = -1 + aobj1.length; 1 <= i81; i81--)
									if (115 != class94_62.charAt(i81 + -1, (byte) -43))
										aobj1[i81] = new Integer(Class140_Sub7.anIntArray2929[--k]);
									else
										aobj1[i81] = Class3.aClass94Array75[--l];

								int j81 = Class140_Sub7.anIntArray2929[--k];
								if (j81 == -1)
									aobj1 = null;
								else
									aobj1[0] = new Integer(j81);
								class11_14.aBoolean195 = true;
								if (1400 == j1)
									class11_14.anObjectArray165 = aobj1;
								else if (j1 == 1401)
									class11_14.anObjectArray180 = aobj1;
								else if (j1 == 1402) {
									class11_14.anObjectArray239 = aobj1;
								} else if (j1 == 1403) {
									class11_14.anObjectArray248 = aobj1;
								} else if (j1 == 1404) {
									class11_14.anObjectArray281 = aobj1;
								} else if (1405 == j1)
									class11_14.anObjectArray295 = aobj1;
								else if (1406 == j1)
									class11_14.anObjectArray303 = aobj1;
								else if (1407 == j1) {
									class11_14.anIntArray286 = ai3;
									class11_14.anObjectArray282 = aobj1;
								} else if (j1 == 1408)
									class11_14.anObjectArray269 = aobj1;
								else if (j1 == 1409) {
									class11_14.anObjectArray314 = aobj1;
								} else if (1410 == j1) {
									class11_14.anObjectArray229 = aobj1;
								} else if (j1 == 1411) {
									class11_14.anObjectArray170 = aobj1;
								} else if (j1 == 1412)
									class11_14.anObjectArray276 = aobj1;
								else if (j1 == 1414) {
									class11_14.anIntArray175 = ai3;
									class11_14.anObjectArray174 = aobj1;
								} else if (1415 == j1) {
									class11_14.anIntArray274 = ai3;
									class11_14.anObjectArray158 = aobj1;
								} else if (1416 == j1)
									class11_14.anObjectArray203 = aobj1;
								else if (1417 == j1) {
									class11_14.anObjectArray183 = aobj1;
								} else if (j1 == 1418)
									class11_14.anObjectArray256 = aobj1;
								else if (j1 == 1419)
									class11_14.anObjectArray220 = aobj1;
								else if (j1 == 1420)
									class11_14.anObjectArray156 = aobj1;
								else if (j1 == 1421) {
									class11_14.anObjectArray313 = aobj1;
								} else if (1422 == j1)
									class11_14.anObjectArray315 = aobj1;
								else if (1423 == j1) {
									class11_14.anObjectArray206 = aobj1;
								} else if (j1 == 1424)
									class11_14.anObjectArray176 = aobj1;
								else if (j1 == 1425)
									class11_14.anObjectArray268 = aobj1;
								else if (j1 == 1426) {
									class11_14.anObjectArray217 = aobj1;
								} else if (1427 == j1) {
									class11_14.anObjectArray235 = aobj1;
								} else if (j1 == 1428) {
									class11_14.anObjectArray161 = aobj1;
									class11_14.anIntArray211 = ai3;
								} else if (j1 == 1429) {
									class11_14.anIntArray185 = ai3;
									class11_14.anObjectArray221 = aobj1;
								}
							}
							continue;
						}
						RSInterface class11_15;
						if (j1 < 2000) {
							class11_15 = flag ? Class164.aClass11_2055 : Class133.aClass11_1749;
						} else {
							class11_15 = Class7.getRSInterface((byte) 118, Class140_Sub7.anIntArray2929[--k]);
							j1 -= 1000;
						}
						Class20.method909(-21, class11_15);
						if (j1 == 1200 || 1205 == j1) {
							k -= 2;
							int j75 = Class140_Sub7.anIntArray2929[1 + k];
							int k65 = Class140_Sub7.anIntArray2929[k];
							if (-1 == class11_15.anInt191) {
								MouseListeningClass.method2092(class11_15.anInt279);
								Class3_Sub13_Sub19.method265(class11_15.anInt279);
								Class107.method1649(class11_15.anInt279, -101);
							}
							if (-1 == k65) {
								class11_15.itemId = -1;
								class11_15.modelType = 1;
								class11_15.anInt192 = -1;
							} else {
								class11_15.anInt192 = k65;
								class11_15.anInt271 = j75;
								ItemDefinition class48_4 = Class38.getItemDefinition(k65, (byte) 108);
								class11_15.anInt280 = class48_4.anInt768;
								class11_15.anInt258 = class48_4.anInt792;
								class11_15.anInt182 = class48_4.anInt786;
								class11_15.anInt264 = class48_4.anInt754;
								class11_15.anInt308 = class48_4.anInt799;
								class11_15.anInt164 = class48_4.anInt810;
								if (class11_15.anInt184 > 0)
									class11_15.anInt164 = (class11_15.anInt164 * 32) / class11_15.anInt184;
								else if (class11_15.width > 0)
									class11_15.anInt164 = (class11_15.anInt164 * 32) / class11_15.width;
								class11_15.aBoolean227 = 1205 != j1;
							}
							continue;
						}
						if (j1 == 1201) {
							class11_15.modelType = 2;
							class11_15.itemId = Class140_Sub7.anIntArray2929[--k];
							if (class11_15.anInt191 == -1)
								Class162.method2206(class11_15.anInt279);
							continue;
						}
						if (j1 == 1202) {
							class11_15.modelType = 3;
							class11_15.itemId = Class102.player.class52.method1163();
							if (class11_15.anInt191 == -1)
								Class162.method2206(class11_15.anInt279);
							continue;
						}
						if (1203 == j1) {
							class11_15.modelType = 6;
							class11_15.itemId = Class140_Sub7.anIntArray2929[--k];
							if (class11_15.anInt191 == -1)
								Class162.method2206(class11_15.anInt279);
							continue;
						}
						if (j1 != 1204)
							break;
						class11_15.modelType = 5;
						class11_15.itemId = Class140_Sub7.anIntArray2929[--k];
						if (class11_15.anInt191 == -1)
							Class162.method2206(class11_15.anInt279);
						continue;
					}
					RSInterface class11_16;
					if (j1 < 2000) {
						class11_16 = flag ? Class164.aClass11_2055 : Class133.aClass11_1749;
					} else {
						j1 -= 1000;
						class11_16 = Class7.getRSInterface((byte) 120, Class140_Sub7.anIntArray2929[--k]);
					}
					if (j1 == 1100) {
						k -= 2;
						class11_16.anInt247 = Class140_Sub7.anIntArray2929[k];
						if (class11_16.anInt240 + -class11_16.anInt168 < class11_16.anInt247)
							class11_16.anInt247 = class11_16.anInt240 + -class11_16.anInt168;
						if (class11_16.anInt247 < 0)
							class11_16.anInt247 = 0;
						class11_16.anInt208 = Class140_Sub7.anIntArray2929[k + 1];
						if (class11_16.anInt208 > class11_16.anInt252 + -class11_16.anInt193)
							class11_16.anInt208 = class11_16.anInt252 + -class11_16.anInt193;
						if (class11_16.anInt208 < 0)
							class11_16.anInt208 = 0;
						Class20.method909(111, class11_16);
						if (-1 == class11_16.anInt191)
							Class67.method1259(class11_16.anInt279);
						continue;
					}
					if (1101 == j1) {
						class11_16.anInt218 = Class140_Sub7.anIntArray2929[--k];
						Class20.method909(-123, class11_16);
						if (class11_16.anInt191 == -1)
							Canvas_Sub2.method56(class11_16.anInt279);
						continue;
					}
					if (j1 == 1102) {
						class11_16.aBoolean226 = Class140_Sub7.anIntArray2929[--k] == 1;
						Class20.method909(107, class11_16);
						continue;
					}
					if (1103 == j1) {
						class11_16.anInt223 = Class140_Sub7.anIntArray2929[--k];
						Class20.method909(-64, class11_16);
						continue;
					}
					if (j1 == 1104) {
						class11_16.anInt250 = Class140_Sub7.anIntArray2929[--k];
						Class20.method909(-52, class11_16);
						continue;
					}
					if (j1 == 1105) {
						class11_16.spriteArchiveId = Class140_Sub7.anIntArray2929[--k];
						Class20.method909(-122, class11_16);
						continue;
					}
					if (1106 == j1) {
						class11_16.anInt301 = Class140_Sub7.anIntArray2929[--k];
						Class20.method909(-33, class11_16);
						continue;
					}
					if (1107 == j1) {
						class11_16.aBoolean186 = Class140_Sub7.anIntArray2929[--k] == 1;
						Class20.method909(114, class11_16);
						continue;
					}
					if (j1 == 1108) {
						class11_16.modelType = 1;
						class11_16.itemId = Class140_Sub7.anIntArray2929[--k];
						Class20.method909(2, class11_16);
						if (class11_16.anInt191 == -1)
							Class162.method2206(class11_16.anInt279);
						continue;
					}
					if (j1 == 1109) {
						k -= 6;
						class11_16.anInt258 = Class140_Sub7.anIntArray2929[k];
						class11_16.anInt264 = Class140_Sub7.anIntArray2929[k + 1];
						class11_16.anInt182 = Class140_Sub7.anIntArray2929[2 + k];
						class11_16.anInt308 = Class140_Sub7.anIntArray2929[k - -3];
						class11_16.anInt280 = Class140_Sub7.anIntArray2929[k - -4];
						class11_16.anInt164 = Class140_Sub7.anIntArray2929[5 + k];
						Class20.method909(-59, class11_16);
						if (class11_16.anInt191 == -1) {
							Class3_Sub13_Sub19.method265(class11_16.anInt279);
							Class107.method1649(class11_16.anInt279, -106);
						}
						continue;
					}
					if (j1 == 1110) {
						int l65 = Class140_Sub7.anIntArray2929[--k];
						if (class11_16.animationId != l65) {
							class11_16.animationId = l65;
							class11_16.anInt283 = 0;
							class11_16.anInt267 = 0;
							class11_16.anInt260 = 1;
							Class20.method909(116, class11_16);
						}
						if (class11_16.anInt191 == -1)
							Class108.method1657(class11_16.anInt279);
						continue;
					}
					if (j1 == 1111) {
						class11_16.aBoolean181 = 1 == Class140_Sub7.anIntArray2929[--k];
						Class20.method909(118, class11_16);
						continue;
					}
					if (1112 == j1) {
						RSString class94_63 = Class3.aClass94Array75[--l];
						if (!class94_63.method1528(class11_16.aClass94_232)) {
							class11_16.aClass94_232 = class94_63;
							Class20.method909(117, class11_16);
						}
						if (class11_16.anInt191 == -1)
							Class93.method1516(class11_16.anInt279, 91);
						continue;
					}
					if (j1 == 1113) {
						class11_16.anInt270 = Class140_Sub7.anIntArray2929[--k];
						Class20.method909(111, class11_16);
						continue;
					}
					if (j1 == 1114) {
						k -= 3;
						class11_16.anInt194 = Class140_Sub7.anIntArray2929[k];
						class11_16.anInt225 = Class140_Sub7.anIntArray2929[1 + k];
						class11_16.anInt205 = Class140_Sub7.anIntArray2929[2 + k];
						Class20.method909(113, class11_16);
						continue;
					}
					if (1115 == j1) {
						class11_16.aBoolean215 = 1 == Class140_Sub7.anIntArray2929[--k];
						Class20.method909(-33, class11_16);
						continue;
					}
					if (j1 == 1116) {
						class11_16.anInt288 = Class140_Sub7.anIntArray2929[--k];
						Class20.method909(-26, class11_16);
						continue;
					}
					if (j1 == 1117) {
						class11_16.anInt287 = Class140_Sub7.anIntArray2929[--k];
						Class20.method909(117, class11_16);
						continue;
					}
					if (j1 == 1118) {
						class11_16.aBoolean178 = Class140_Sub7.anIntArray2929[--k] == 1;
						Class20.method909(123, class11_16);
						continue;
					}
					if (j1 == 1119) {
						class11_16.aBoolean199 = Class140_Sub7.anIntArray2929[--k] == 1;
						Class20.method909(-20, class11_16);
						continue;
					}
					if (j1 == 1120) {
						k -= 2;
						class11_16.anInt240 = Class140_Sub7.anIntArray2929[k];
						class11_16.anInt252 = Class140_Sub7.anIntArray2929[1 + k];
						Class20.method909(117, class11_16);
						if (class11_16.type == 0)
							Class151_Sub1.method2104(class11_16, false, -116);
						continue;
					}
					if (j1 == 1121) {
						k -= 2;
						class11_16.aShort293 = (short) Class140_Sub7.anIntArray2929[k];
						class11_16.aShort169 = (short) Class140_Sub7.anIntArray2929[k + 1];
						Class20.method909(126, class11_16);
						continue;
					}
					if (1122 == j1) {
						class11_16.aBoolean157 = Class140_Sub7.anIntArray2929[--k] == 1;
						Class20.method909(-66, class11_16);
						continue;
					}
					if (j1 != 1123)
						break;
					class11_16.anInt164 = Class140_Sub7.anIntArray2929[--k];
					Class20.method909(-126, class11_16);
					if (class11_16.anInt191 == -1)
						Class3_Sub13_Sub19.method265(class11_16.anInt279);
					continue;
				}
				if (j1 == 403) {
					k -= 2;
					int i66 = Class140_Sub7.anIntArray2929[k - -1];
					int j43 = Class140_Sub7.anIntArray2929[k];
					int k75 = 0;
					do {
						if (Class3_Sub26.anIntArray2559.length <= k75)
							break;
						if (j43 == Class3_Sub26.anIntArray2559[k75]) {
							Class102.player.class52.method1164(k75, i66);
							continue label0;
						}
						k75++;
					} while (true);
					k75 = 0;
					do {
						if (Class3_Sub13_Sub19.anIntArray3228.length <= k75)
							continue label0;
						if (Class3_Sub13_Sub19.anIntArray3228[k75] == j43) {
							Class102.player.class52.method1164(k75, i66);
							continue label0;
						}
						k75++;
					} while (true);
				}
				if (404 == j1) {
					k -= 2;
					int k43 = Class140_Sub7.anIntArray2929[k];
					int j66 = Class140_Sub7.anIntArray2929[1 + k];
					Class102.player.class52.method1162(k43, j66);
					continue;
				}
				if (j1 != 410)
					break;
				try {
					boolean flag2 = 0 != Class140_Sub7.anIntArray2929[--k];
					Class102.player.class52.method1159(flag2, true);
				} catch (Exception e) {
					System.out.println(e);
				}
			} while (true);
			throw new IllegalStateException();
		} catch (Exception e) {
			System.out.println("Exception in item defs " + e);
		}
	}


	private RSString method1105(int var1, RSString var2, int var3) {
		try {
			if(this.aClass130_798 == null) {
				return var2;
			} else {
				if(var1 < 90) {
					method1111(-111);
				}

				Class3_Sub29 var4 = (Class3_Sub29)this.aClass130_798.method1780((long)var3, 0);
				return null != var4?var4.aClass94_2586:var2;
			}
		} catch (RuntimeException var5) {
			throw Class44.clientError(var5, "h.S(" + var1 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
		}
	}

	final ItemDefinition method1106(int var1) {
		try {
			int var3 = 58 % ((-28 - 78) / 48);
			if(this.anIntArray804 != null && var1 > 1) {
				int var4 = -1;

				for(int var5 = 0; 10 > var5; ++var5) {
					if(this.anIntArray766[var5] <= var1 && this.anIntArray766[var5] != 0) {
						var4 = this.anIntArray804[var5];
					}
				}

				if(var4 != -1) {
					return Class38.getItemDefinition(var4, (byte)97);
				}
			}

			return this;
		} catch (RuntimeException var6) {
			throw Class44.clientError(var6, "h.H(" + var1 + ',' + 78 + ')');
		}
	}

	static WorldListEntry method1107(int var0) {
		try {
			if(Class3_Sub13_Sub16.aClass44_Sub1Array3201.length > Class3_Sub6.anInt2291) {
				return Class3_Sub13_Sub16.aClass44_Sub1Array3201[Class3_Sub6.anInt2291++];
			} else {
				if(var0 != 5422) {
					method1107(-66);
				}

				return null;
			}
		} catch (RuntimeException var2) {
			throw Class44.clientError(var2, "h.R(" + var0 + ')');
		}
	}

	final boolean method1108(boolean var2) {
		try {
			int var4 = this.anInt771;
			int var3 = this.anInt793;
			int var6 = 106 % (((byte) 95 - 24) / 58);
			int var5 = this.anInt769;
			if(var2) {
				var5 = this.anInt776;
				var3 = this.anInt761;
				var4 = this.anInt794;
			}

			if(var3 == -1) {
				return true;
			} else {
				boolean var7 = true;
				if(!Class3_Sub29.aClass153_2581.method2129((byte)-90, 0, var3)) {
					var7 = false;
				}

				if(var4 != -1 && !Class3_Sub29.aClass153_2581.method2129((byte)-114, 0, var4)) {
					var7 = false;
				}

				if(-1 != var5 && !Class3_Sub29.aClass153_2581.method2129((byte)83, 0, var5)) {
					var7 = false;
				}

				return var7;
			}
		} catch (RuntimeException var8) {
			throw Class44.clientError(var8, "h.C(" + (byte) 95 + ',' + var2 + ')');
		}
	}

	final void method1109(ItemDefinition var2, ItemDefinition var3) {
		try {
			this.aByteArray785 = var2.aByteArray785;
			this.wornModelPositionZ = var2.wornModelPositionZ;
			this.aClass130_798 = var2.aClass130_798;
			this.anInt769 = var2.anInt769;
			this.anInt761 = var2.anInt761;
			this.wornModelPositionY = var2.wornModelPositionY;
			this.inventoryOptions = new RSString[5];
			this.anInt755 = var3.anInt755;
			this.anInt810 = var3.anInt810;
			this.value = 0;
			this.teamId = var2.teamId;
			this.anInt773 = var2.anInt773;
			this.aShortArray774 = var2.aShortArray774;
			this.anInt768 = var3.anInt768;
			this.anInt771 = var2.anInt771;
			this.anInt799 = var3.anInt799;
			this.anInt803 = var2.anInt803;
			this.anInt796 = var2.anInt796;
			this.wornModelPositionX = var2.wornModelPositionX;
			this.anInt786 = var3.anInt786;
			this.anInt754 = var3.anInt754;
			this.anInt753 = var2.anInt753;
			this.anInt777 = var2.anInt777;
			this.aShortArray772 = var2.aShortArray772;
			this.anInt802 = var2.anInt802;
			this.anInt752 = var2.anInt752;
			this.anInt792 = var3.anInt792;
			if((byte) 69 != 69) {
				this.value = 109;
			}

			this.anInt793 = var2.anInt793;
			this.anInt794 = var2.anInt794;
			this.name = var2.name;
			this.aShortArray751 = var2.aShortArray751;
			this.aShortArray765 = var2.aShortArray765;
			this.groundOptions = var2.groundOptions;
			this.membersItem = var2.membersItem;
			this.anInt776 = var2.anInt776;
			if(null != var2.inventoryOptions) {
				for(int var4 = 0; var4 < 4; ++var4) {
					this.inventoryOptions[var4] = var2.inventoryOptions[var4];
				}
			}

			this.inventoryOptions[4] = TextCore.HasDiscard;
		} catch (RuntimeException var5) {
			throw Class44.clientError(var5, "h.J(" + (byte) 69 + ',' + (var2 != null?"{...}":"null") + ',' + (var3 != null?"{...}":"null") + ')');
		}
	}

	final Model method1110(int var1, int var2, int var3, AnimationDefinition var4, int var5, int var6) {
		try {
			if(var1 < 94) {
				this.anInt755 = -67;
			}

			if(this.anIntArray804 != null && var5 > 1) {
				int var7 = -1;

				for(int var8 = 0; var8 < 10; ++var8) {
					if(this.anIntArray766[var8] <= var5 && this.anIntArray766[var8] != 0) {
						var7 = this.anIntArray804[var8];
					}
				}

				if(var7 != -1) {
					return Class38.getItemDefinition(var7, (byte)109).method1110(113, var2, var3, var4, 1, var6);
				}
			}

			Model var11 = (Model)Class143.aClass93_1874.get((long)this.itemId);
			if(var11 == null) {
				Model_Sub1 var12 = Model_Sub1.method2015(Class3_Sub29.aClass153_2581, this.anInt755);
				if(null == var12) {
					return null;
				}

				int var9;
				if(null != this.aShortArray774) {
					for(var9 = 0; this.aShortArray774.length > var9; ++var9) {
						if(null != this.aByteArray785 && this.aByteArray785.length > var9) {
							var12.method2016(this.aShortArray774[var9], Class3_Sub13_Sub38.aShortArray3453[this.aByteArray785[var9] & 255]);
						} else {
							var12.method2016(this.aShortArray774[var9], this.aShortArray772[var9]);
						}
					}
				}

				if(this.aShortArray765 != null) {
					for(var9 = 0; var9 < this.aShortArray765.length; ++var9) {
						var12.method1998(this.aShortArray765[var9], this.aShortArray751[var9]);
					}
				}

				var11 = var12.method2008(this.anInt784 + 64, 768 + this.anInt790, -50, -10, -50);
				if(this.anInt805 != 128 || this.anInt780 != 128 || this.anInt797 != 128) {
					var11.resize(this.anInt805, this.anInt780, this.anInt797);
				}

				var11.aBoolean2699 = true;
				if(HDToolKit.highDetail) {
					((Class140_Sub1_Sub1)var11).method1920(false, false, false, false, false, true);
				}

				Class143.aClass93_1874.put((byte)-123, var11, (long)this.itemId);
			}

			if(var4 != null) {
				var11 = var4.method2055(var11, (byte)-88, var2, var3, var6);
			}

			return var11;
		} catch (RuntimeException var10) {
			throw Class44.clientError(var10, "h.E(" + var1 + ',' + var2 + ',' + var3 + ',' + (var4 != null?"{...}":"null") + ',' + var5 + ',' + var6 + ')');
		}
	}

	public static void method1111(int var0) {
		try {
			aClass94_808 = null;
			anIntArray781 = null;
			aClass94_809 = null;
			if(var0 == 3327) {
				aClass94_806 = null;
				aClass94_811 = null;
			}
		} catch (RuntimeException var2) {
			throw Class44.clientError(var2, "h.P(" + var0 + ')');
		}
	}

	final void method1112() {
		try {
			if(5401 != 5401) {
				method1103((CacheIndex)null, (CacheIndex)null, true);
			}

		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "h.O(" + 5401 + ')');
		}
	}

	final void parseDefinitions(int var1, RSByteBuffer buffer) {
		try {
			while(true) {
				int opcode = buffer.getByte((byte)-72);
				if(0 == opcode) {
					if(var1 != 1) {
						this.anInt789 = -40;
					}

					return;
				}

				this.parseOpcode(buffer, opcode);
			}
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "h.M(" + var1 + ',' + (buffer != null?"{...}":"null") + ')');
		}
	}

	private void parseOpcode(RSByteBuffer buffer, int opcode) {
		try {
			int var4 = -118 % (((byte) -72 - 48) / 61);
			if(opcode == 1) {
				this.anInt755 = buffer.getShort(1);
			} else if (opcode == 2) {
				this.name = buffer.getString();
			} else if (opcode == 4) {
				this.anInt810 = buffer.getShort(1);
			} else if (opcode == 5) {
				this.anInt786 = buffer.getShort(1);
			} else if (opcode == 6) {
				this.anInt799 = buffer.getShort(1);
			} else if (opcode == 7) {
				this.anInt792 = buffer.getShort(1);
				if (this.anInt792 > 32767) {
					this.anInt792 -= 65536;
				}
			} else if (opcode == 8) {
				this.anInt754 = buffer.getShort(1);
				if (this.anInt754 > 32767) {
					this.anInt754 -= 65536;
				}
			} else if (opcode == 11) {
				this.stackingType = 1;
			} else if (opcode == 12) {
				this.value = buffer.getInt();
			} else if (opcode == 16) {
				this.membersItem = true;
			} else if (23 == opcode) {
				this.anInt793 = buffer.getShort(1);
			} else if (opcode == 24) {
				this.anInt771 = buffer.getShort(1);
			} else if (opcode == 25) {
				this.anInt761 = buffer.getShort(1);
			} else if (opcode == 26) {
				this.anInt794 = buffer.getShort(1);
			} else if (opcode >= 30 && opcode < 35) {
				this.groundOptions[-30 + opcode] = buffer.getString();
				if (this.groundOptions[opcode + -30].equals(25, TextCore.HasHidden)) {
					this.groundOptions[-30 + opcode] = null;
				}
			} else if (35 <= opcode && 40 > opcode) {
				this.inventoryOptions[-35 + opcode] = buffer.getString();
			} else {
				int var5;
				int var6;
				if (opcode == 40) {
					var5 = buffer.getByte((byte) -64);
					this.aShortArray772 = new short[var5];
					this.aShortArray774 = new short[var5];

					for (var6 = 0; var5 > var6; ++var6) {
						this.aShortArray774[var6] = (short) buffer.getShort(1);
						this.aShortArray772[var6] = (short) buffer.getShort(1);
					}
				} else if (opcode == 41) {
					var5 = buffer.getByte((byte) -55);
					this.aShortArray751 = new short[var5];
					this.aShortArray765 = new short[var5];

					for (var6 = 0; var6 < var5; ++var6) {
						this.aShortArray765[var6] = (short) buffer.getShort(1);
						this.aShortArray751[var6] = (short) buffer.getShort(1);
					}
				} else if (42 == opcode) {
					var5 = buffer.getByte((byte) -62);
					this.aByteArray785 = new byte[var5];

					for (var6 = 0; var5 > var6; ++var6) {
						this.aByteArray785[var6] = buffer.getByte();
					}
				} else if (opcode == 65) {
					this.aBoolean807 = true;
				} else if (opcode == 78) {
					this.anInt769 = buffer.getShort(1);
				} else if (opcode == 79) {
					this.anInt776 = buffer.getShort(1);
				} else if (90 == opcode) {
					this.anInt803 = buffer.getShort(1);
				} else if (opcode == 91) {
					this.anInt773 = buffer.getShort(1);
				} else if (opcode == 92) {
					this.anInt796 = buffer.getShort(1);
				} else if (opcode == 93) {
					this.anInt753 = buffer.getShort(1);
				} else if (opcode == 95) {
					this.anInt768 = buffer.getShort(1);
				} else if (opcode == 96) {
					this.anInt800 = buffer.getByte((byte) -118);
				} else if (opcode == 97) {
					this.anInt789 = buffer.getShort(1);
				} else if (opcode == 98) {
					this.anInt791 = buffer.getShort(1);
				} else if (opcode >= 100 && opcode < 110) {
					if (null == this.anIntArray804) {
						this.anIntArray804 = new int[10];
						this.anIntArray766 = new int[10];
					}

					this.anIntArray804[-100 + opcode] = buffer.getShort(1);
					this.anIntArray766[opcode + -100] = buffer.getShort(1);
				} else if (110 == opcode) {
					this.anInt805 = buffer.getShort(1);
				} else if (opcode == 111) {
					this.anInt780 = buffer.getShort(1);
				} else if (opcode == 112) {
					this.anInt797 = buffer.getShort(1);
				} else if (opcode == 113) {
					this.anInt784 = buffer.getByte();
				} else if (opcode == 114) {
					this.anInt790 = 5 * buffer.getByte();
				} else if (opcode == 115) {
					this.teamId = buffer.getByte((byte) -125);
				} else if (opcode == 121) {
					this.anInt795 = buffer.getShort(1);
				} else if (opcode == 122) {
					this.anInt762 = buffer.getShort(1);
				} else if (125 == opcode) {
					this.wornModelPositionX = buffer.getByte();
					this.wornModelPositionZ = buffer.getByte();
					this.wornModelPositionY = buffer.getByte();
				} else if (opcode == 126) {
					this.anInt777 = buffer.getByte();
					this.anInt802 = buffer.getByte();
					this.anInt752 = buffer.getByte();
				} else if (opcode == 127) {
					this.anInt767 = buffer.getByte((byte) -34);
					this.anInt758 = buffer.getShort(1);
				} else if (opcode == 128) {
					this.anInt788 = buffer.getByte((byte) -63);
					this.anInt756 = buffer.getShort(1);
				} else if (opcode == 129) {
					buffer.getByte((byte) -51);
					buffer.getShort(1);
				} else if (opcode == 130) {
					buffer.getByte((byte) -43);
					buffer.getShort(1);
				} else if (249 == opcode) {
					var5 = buffer.getByte((byte) -44);
					if (null == this.aClass130_798) {
						var6 = Class95.method1585((byte) 97, var5);
						this.aClass130_798 = new Class130(var6);
					}

					for (var6 = 0; var6 < var5; ++var6) {
						boolean var7 = buffer.getByte((byte) -95) == 1;
						int var8 = buffer.getTriByte((byte) 122);
						Object var9;
						if (var7) {
							var9 = new Class3_Sub29(buffer.getString());
						} else {
							var9 = new Class3_Sub18(buffer.getInt());
						}

						this.aClass130_798.method1779(1, (Class3) var9, (long) var8);
					}
				}
			}

		} catch (RuntimeException var10) {
			throw Class44.clientError(var10, "h.Q(" + (byte) -72 + ',' + (buffer != null?"{...}":"null") + ',' + opcode + ')');
		}
	}

	final int method1115(int var1, int var2, int var3) {
		try {
			int var4 = -82 % ((-63 - var2) / 55);
			if(this.aClass130_798 == null) {
				return var1;
			} else {
				Class3_Sub18 var5 = (Class3_Sub18)this.aClass130_798.method1780((long)var3, 0);
				return null != var5?var5.anInt2467:var1;
			}
		} catch (RuntimeException var6) {
			throw Class44.clientError(var6, "h.I(" + var1 + ',' + var2 + ',' + var3 + ')');
		}
	}

	final Model_Sub1 method1116(boolean var1) {
		try {
			int var4 = this.anInt796;
			if((byte) -109 == -109) {
				int var3 = this.anInt803;
				if(var1) {
					var4 = this.anInt753;
					var3 = this.anInt773;
				}

				if(-1 == var3) {
					return null;
				} else {
					Model_Sub1 var5 = Model_Sub1.method2015(Class3_Sub29.aClass153_2581, var3);
					if(-1 != var4) {
						Model_Sub1 var6 = Model_Sub1.method2015(Class3_Sub29.aClass153_2581, var4);
						Model_Sub1[] var7 = new Model_Sub1[]{var5, var6};
						var5 = new Model_Sub1(var7, 2);
					}

					int var9;
					if(this.aShortArray774 != null) {
						for(var9 = 0; var9 < this.aShortArray774.length; ++var9) {
							var5.method2016(this.aShortArray774[var9], this.aShortArray772[var9]);
						}
					}

					if(this.aShortArray765 != null) {
						for(var9 = 0; var9 < this.aShortArray765.length; ++var9) {
							var5.method1998(this.aShortArray765[var9], this.aShortArray751[var9]);
						}
					}

					return var5;
				}
			} else {
				return (Model_Sub1)null;
			}
		} catch (RuntimeException var8) {
			throw Class44.clientError(var8, "h.A(" + var1 + ',' + (byte) -109 + ')');
		}
	}

	final Model_Sub1 method1117(boolean var1) {
		try {
			int var3 = this.anInt793;
			if(80 < 77) {
				this.aClass130_798 = (Class130)null;
			}

			int var4 = this.anInt771;
			int var5 = this.anInt769;
			if(var1) {
				var5 = this.anInt776;
				var3 = this.anInt761;
				var4 = this.anInt794;
			}

			if(var3 == -1) {
				return null;
			} else {
				Model_Sub1 var6 = Model_Sub1.method2015(Class3_Sub29.aClass153_2581, var3);
				if(var4 != -1) {
					Model_Sub1 var7 = Model_Sub1.method2015(Class3_Sub29.aClass153_2581, var4);
					if(-1 == var5) {
						Model_Sub1[] var8 = new Model_Sub1[]{var6, var7};
						var6 = new Model_Sub1(var8, 2);
					} else {
						Model_Sub1 var12 = Model_Sub1.method2015(Class3_Sub29.aClass153_2581, var5);
						Model_Sub1[] var9 = new Model_Sub1[]{var6, var7, var12};
						var6 = new Model_Sub1(var9, 3);
					}
				}

				//            System.out.println(var1 + " - ");
				//            this.wornModelPositionX = -1;
				//            this.wornModelPositionZ = 13;//-1;//20;
				//            this.wornModelPositionY = -1;
				if(!var1 && (this.wornModelPositionX != 0 || this.wornModelPositionZ != 0 || this.wornModelPositionY != 0)) {
					var6.method2001(this.wornModelPositionX, this.wornModelPositionZ, this.wornModelPositionY);
				}
				if(var1 && (this.anInt777 != 0 || this.anInt802 != 0 || this.anInt752 != 0)) {
					var6.method2001(this.anInt777, this.anInt802, this.anInt752);
				}

				int var11;
				if(this.aShortArray774 != null) {
					for(var11 = 0; var11 < this.aShortArray774.length; ++var11) {
						var6.method2016(this.aShortArray774[var11], this.aShortArray772[var11]);
					}
				}

				if(this.aShortArray765 != null) {
					for(var11 = 0; var11 < this.aShortArray765.length; ++var11) {
						var6.method1998(this.aShortArray765[var11], this.aShortArray751[var11]);
					}
				}

				return var6;
			}
		} catch (RuntimeException var10) {
			throw Class44.clientError(var10, "h.D(" + var1 + ',' + 80 + ')');
		}
	}

	final void method1118(ItemDefinition var1, ItemDefinition var2) {
		try {
			this.name = var1.name;
			this.anInt810 = var2.anInt810;
			if(false) {
				this.anInt780 = -70;
			}

			this.aShortArray774 = var2.aShortArray774;
			this.aShortArray772 = var2.aShortArray772;
			this.anInt786 = var2.anInt786;
			this.anInt799 = var2.anInt799;
			this.aShortArray751 = var2.aShortArray751;
			this.anInt755 = var2.anInt755;
			this.aByteArray785 = var2.aByteArray785;
			this.anInt768 = var2.anInt768;
			this.value = var1.value;
			this.stackingType = 1;
			this.anInt754 = var2.anInt754;
			this.anInt792 = var2.anInt792;
			this.aShortArray765 = var2.aShortArray765;
			this.membersItem = var1.membersItem;
		} catch (RuntimeException var5) {
			throw Class44.clientError(var5, "h.N(" + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ',' + false + ')');
		}
	}

	static void method1119(Component var0, boolean var1) {
		try {
			var0.addMouseListener(Class3_Sub28_Sub7_Sub1.aClass149_4047);
			if(var1) {
				aClass94_811 = (RSString)null;
			}

			var0.addMouseMotionListener(Class3_Sub28_Sub7_Sub1.aClass149_4047);
			var0.addFocusListener(Class3_Sub28_Sub7_Sub1.aClass149_4047);
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "h.K(" + (var0 != null?"{...}":"null") + ',' + var1 + ')');
		}
	}

	final Class140_Sub1_Sub2 method1120() {
		try {
			Model_Sub1 var2 = Model_Sub1.method2015(Class3_Sub29.aClass153_2581, this.anInt755);
			if(var2 == null) {
				return null;
			} else {
				int var3;
				if(this.aShortArray774 != null) {
					for(var3 = 0; this.aShortArray774.length > var3; ++var3) {
						if(null != this.aByteArray785 && this.aByteArray785.length > var3) {
							var2.method2016(this.aShortArray774[var3], Class3_Sub13_Sub38.aShortArray3453[this.aByteArray785[var3] & 255]);
						} else {
							var2.method2016(this.aShortArray774[var3], this.aShortArray772[var3]);
						}
					}
				}

				if(this.aShortArray765 != null) {
					for(var3 = 0; var3 < this.aShortArray765.length; ++var3) {
						var2.method1998(this.aShortArray765[var3], this.aShortArray751[var3]);
					}
				}

				Class140_Sub1_Sub2 var5 = var2.method2000(64 - -this.anInt784, 768 - -this.anInt790);
				if(18206 != 18206) {
					this.method1105(-67, (RSString)null, -37);
				}

				if(this.anInt805 != 128 || this.anInt780 != 128 || this.anInt797 != 128) {
					var5.resize(this.anInt805, this.anInt780, this.anInt797);
				}

				return var5;
			}
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "h.L(" + 18206 + ')');
		}
	}

	public ItemDefinition() {
		this.name = Class40.aClass94_672;
		this.wornModelPositionY = 0;
		this.anInt784 = 0;
		this.anInt769 = -1;
		this.anInt796 = -1;
		this.anInt791 = -1;
		this.anInt777 = 0;
		this.anInt780 = 128;
		this.anInt767 = -1;
		this.anInt758 = -1;
		this.anInt768 = 0;
		this.anInt762 = -1;
		this.anInt795 = -1;
		this.anInt761 = -1;
		this.anInt771 = -1;
		this.anInt754 = 0;
		this.anInt786 = 0;
		this.anInt799 = 0;
		this.anInt800 = 0;
		this.stackingType = 0;
		this.anInt789 = -1;
		this.anInt794 = -1;
		this.anInt788 = -1;
		this.anInt797 = 128;
		this.membersItem = false;
		this.anInt752 = 0;
		this.anInt792 = 0;
		this.anInt803 = -1;
		this.anInt802 = 0;
		this.anInt793 = -1;
		this.groundOptions = new RSString[]{null, null, TextCore.HasTake, null, null};
		this.anInt805 = 128;
		this.anInt790 = 0;
		this.wornModelPositionZ = 0;
		this.inventoryOptions = new RSString[]{null, null, null, null, TextCore.HasDrop};
		this.anInt810 = 2000;
		this.aBoolean807 = false;
	}

	static {
		int var0 = 0;

		for(int var1 = 0; var1 < 99; ++var1) {
			int var2 = 1 + var1;
			int var3 = (int)(Math.pow(2.0D, (double)var2 / 7.0D) * 300.0D + (double)var2);
			var0 += var3;
			anIntArray781[var1] = var0 / 4;
		}

		aClass94_808 =
		aClass94_811 = RSString.createRSString("green:");
		aClass94_809 = aClass94_811;
		aClass94_806 = aClass94_811;
	}

	public int getShiftClickActionIndex() {
		return (inventoryOptions != null)
				? ("Drop".equalsIgnoreCase(inventoryOptions[4].toString()) ? 4 : -1) : -1;
	}
}
