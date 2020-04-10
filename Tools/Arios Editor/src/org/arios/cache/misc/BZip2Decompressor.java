package org.arios.cache.misc;

public class BZip2Decompressor {

	private static int anIntArray257[];
	private static BZip2BlockEntry entryInstance = new BZip2BlockEntry();
	
	public static final void decompress(byte decompressedData[], byte packedData[], int containerSize, int blockSize) {
		synchronized (entryInstance) {
			entryInstance.aByteArray2224 = packedData;
			entryInstance.anInt2209 = blockSize;
			entryInstance.aByteArray2212 = decompressedData;
			entryInstance.anInt2203 = 0;
			entryInstance.anInt2206 = decompressedData.length;
			entryInstance.anInt2232 = 0;
			entryInstance.anInt2207 = 0;
			entryInstance.anInt2217 = 0;
			entryInstance.anInt2216 = 0;
			method1793(entryInstance);
			entryInstance.aByteArray2224 = null;
			entryInstance.aByteArray2212 = null;
		}
	}

	private static final void method1785(BZip2BlockEntry entry) {
		entry.anInt2215 = 0;
		for (int i = 0; i < 256; i++) {
			if (entry.aBooleanArray2213[i]) {
				entry.aByteArray2211[entry.anInt2215] = (byte) i;
				entry.anInt2215++;
			}
		}

	}

	private static final void method1786(int ai[], int ai1[], int ai2[],
			byte abyte0[], int i, int j, int k) {
		int l = 0;
		for (int i1 = i; i1 <= j; i1++) {
			for (int l2 = 0; l2 < k; l2++) {
				if (abyte0[l2] == i1) {
					ai2[l] = l2;
					l++;
				}
			}

		}

		for (int j1 = 0; j1 < 23; j1++) {
			ai1[j1] = 0;
		}

		for (int k1 = 0; k1 < k; k1++) {
			ai1[abyte0[k1] + 1]++;
		}

		for (int l1 = 1; l1 < 23; l1++) {
			ai1[l1] += ai1[l1 - 1];
		}

		for (int i2 = 0; i2 < 23; i2++) {
			ai[i2] = 0;
		}

		int i3 = 0;
		for (int j2 = i; j2 <= j; j2++) {
			i3 += ai1[j2 + 1] - ai1[j2];
			ai[j2] = i3 - 1;
			i3 <<= 1;
		}

		for (int k2 = i + 1; k2 <= j; k2++) {
			ai1[k2] = (ai[k2 - 1] + 1 << 1) - ai1[k2];
		}

	}

	private static final void method1787(BZip2BlockEntry entry) {
		byte byte4 = entry.aByte2201;
		int i = entry.anInt2222;
		int j = entry.anInt2227;
		int k = entry.anInt2221;
		int ai[] = anIntArray257;
		int l = entry.anInt2208;
		byte abyte0[] = entry.aByteArray2212;
		int i1 = entry.anInt2203;
		int j1 = entry.anInt2206;
		int k1 = j1;
		int l1 = entry.anInt2225 + 1;
		label0: do {
			if (i > 0) {
				do {
					if (j1 == 0) {
						break label0;
					}
					if (i == 1) {
						break;
					}
					abyte0[i1] = byte4;
					i--;
					i1++;
					j1--;
				} while (true);
				if (j1 == 0) {
					i = 1;
					break;
				}
				abyte0[i1] = byte4;
				i1++;
				j1--;
			}
			boolean flag = true;
			while (flag) {
				flag = false;
				if (j == l1) {
					i = 0;
					break label0;
				}
				byte4 = (byte) k;
				l = ai[l];
				byte byte0 = (byte) (l & 0xff);
				l >>= 8;
				j++;
				if (byte0 != k) {
					k = byte0;
					if (j1 == 0) {
						i = 1;
					} else {
						abyte0[i1] = byte4;
						i1++;
						j1--;
						flag = true;
						continue;
					}
					break label0;
				}
				if (j != l1) {
					continue;
				}
				if (j1 == 0) {
					i = 1;
					break label0;
				}
				abyte0[i1] = byte4;
				i1++;
				j1--;
				flag = true;
			}
			i = 2;
			l = ai[l];
			byte byte1 = (byte) (l & 0xff);
			l >>= 8;
			if (++j != l1) {
				if (byte1 != k) {
					k = byte1;
				} else {
					i = 3;
					l = ai[l];
					byte byte2 = (byte) (l & 0xff);
					l >>= 8;
					if (++j != l1) {
						if (byte2 != k) {
							k = byte2;
						} else {
							l = ai[l];
							byte byte3 = (byte) (l & 0xff);
							l >>= 8;
							j++;
							i = (byte3 & 0xff) + 4;
							l = ai[l];
							k = (byte) (l & 0xff);
							l >>= 8;
							j++;
						}
					}
				}
			}
		} while (true);
		entry.anInt2216 += k1 - j1;
		entry.aByte2201 = byte4;
		entry.anInt2222 = i;
		entry.anInt2227 = j;
		entry.anInt2221 = k;
		anIntArray257 = ai;
		entry.anInt2208 = l;
		entry.aByteArray2212 = abyte0;
		entry.anInt2203 = i1;
		entry.anInt2206 = j1;
	}

	private static final byte method1788(BZip2BlockEntry entry) {
		return (byte) method1790(1, entry);
	}

	private static final byte method1789(BZip2BlockEntry entryInstance2) {
		return (byte) method1790(8, entryInstance2);
	}

	private static final int method1790(int i, BZip2BlockEntry entry) {
		int j;
		do {
			if (entry.anInt2232 >= i) {
				int k = entry.anInt2207 >> entry.anInt2232 - i & (1 << i) - 1;
				entry.anInt2232 -= i;
				j = k;
				break;
			}
			entry.anInt2207 = entry.anInt2207 << 8
					| entry.aByteArray2224[entry.anInt2209] & 0xff;
			entry.anInt2232 += 8;
			entry.anInt2209++;
			entry.anInt2217++;
		} while (true);
		return j;
	}

	public static void clearBlockEntryInstance() {
		entryInstance = null;
	}

	private static final void method1793(BZip2BlockEntry entryInstance2) {
		// unused
		/*
		 * boolean flag = false; boolean flag1 = false; boolean flag2 = false;
		 * boolean flag3 = false; boolean flag4 = false; boolean flag5 = false;
		 * boolean flag6 = false; boolean flag7 = false; boolean flag8 = false;
		 * boolean flag9 = false; boolean flag10 = false; boolean flag11 =
		 * false; boolean flag12 = false; boolean flag13 = false; boolean flag14
		 * = false; boolean flag15 = false; boolean flag16 = false; boolean
		 * flag17 = false;
		 */
		int j8 = 0;
		int ai[] = null;
		int ai1[] = null;
		int ai2[] = null;
		entryInstance2.anInt2202 = 1;
		if (anIntArray257 == null) {
			anIntArray257 = new int[entryInstance2.anInt2202 * 0x186a0];
		}
		boolean flag18 = true;
		while (flag18) {
			byte byte0 = method1789(entryInstance2);
			if (byte0 == 23) {
				return;
			}
			byte0 = method1789(entryInstance2);
			byte0 = method1789(entryInstance2);
			byte0 = method1789(entryInstance2);
			byte0 = method1789(entryInstance2);
			byte0 = method1789(entryInstance2);
			byte0 = method1789(entryInstance2);
			byte0 = method1789(entryInstance2);
			byte0 = method1789(entryInstance2);
			byte0 = method1789(entryInstance2);
			byte0 = method1788(entryInstance2);
			entryInstance2.anInt2223 = 0;
			byte0 = method1789(entryInstance2);
			entryInstance2.anInt2223 = entryInstance2.anInt2223 << 8 | byte0 & 0xff;
			byte0 = method1789(entryInstance2);
			entryInstance2.anInt2223 = entryInstance2.anInt2223 << 8 | byte0 & 0xff;
			byte0 = method1789(entryInstance2);
			entryInstance2.anInt2223 = entryInstance2.anInt2223 << 8 | byte0 & 0xff;
			for (int j = 0; j < 16; j++) {
				byte byte1 = method1788(entryInstance2);
				if (byte1 == 1) {
					entryInstance2.aBooleanArray2205[j] = true;
				} else {
					entryInstance2.aBooleanArray2205[j] = false;
				}
			}

			for (int k = 0; k < 256; k++) {
				entryInstance2.aBooleanArray2213[k] = false;
			}

			for (int l = 0; l < 16; l++) {
				if (entryInstance2.aBooleanArray2205[l]) {
					for (int i3 = 0; i3 < 16; i3++) {
						byte byte2 = method1788(entryInstance2);
						if (byte2 == 1) {
							entryInstance2.aBooleanArray2213[l * 16 + i3] = true;
						}
					}

				}
			}

			method1785(entryInstance2);
			int i4 = entryInstance2.anInt2215 + 2;
			int j4 = method1790(3, entryInstance2);
			int k4 = method1790(15, entryInstance2);
			for (int i1 = 0; i1 < k4; i1++) {
				int j3 = 0;
				do {
					byte byte3 = method1788(entryInstance2);
					if (byte3 == 0) {
						break;
					}
					j3++;
				} while (true);
				entryInstance2.aByteArray2214[i1] = (byte) j3;
			}

			byte abyte0[] = new byte[6];
			for (byte byte16 = 0; byte16 < j4; byte16++) {
				abyte0[byte16] = byte16;
			}

			for (int j1 = 0; j1 < k4; j1++) {
				byte byte17 = entryInstance2.aByteArray2214[j1];
				byte byte15 = abyte0[byte17];
				for (; byte17 > 0; byte17--) {
					abyte0[byte17] = abyte0[byte17 - 1];
				}

				abyte0[0] = byte15;
				entryInstance2.aByteArray2219[j1] = byte15;
			}

			for (int k3 = 0; k3 < j4; k3++) {
				int k6 = method1790(5, entryInstance2);
				for (int k1 = 0; k1 < i4; k1++) {
					do {
						byte byte4 = method1788(entryInstance2);
						if (byte4 == 0) {
							break;
						}
						byte4 = method1788(entryInstance2);
						if (byte4 == 0) {
							k6++;
						} else {
							k6--;
						}
					} while (true);
					entryInstance2.aByteArrayArray2229[k3][k1] = (byte) k6;
				}

			}

			for (int l3 = 0; l3 < j4; l3++) {
				byte byte8 = 32;
				int i = 0;
				for (int l1 = 0; l1 < i4; l1++) {
					if (entryInstance2.aByteArrayArray2229[l3][l1] > i) {
						i = entryInstance2.aByteArrayArray2229[l3][l1];
					}
					if (entryInstance2.aByteArrayArray2229[l3][l1] < byte8) {
						byte8 = entryInstance2.aByteArrayArray2229[l3][l1];
					}
				}

				method1786(entryInstance2.anIntArrayArray2230[l3],
						entryInstance2.anIntArrayArray2218[l3],
						entryInstance2.anIntArrayArray2210[l3],
						entryInstance2.aByteArrayArray2229[l3], byte8, i, i4);
				entryInstance2.anIntArray2200[l3] = byte8;
			}

			int l4 = entryInstance2.anInt2215 + 1;
			int i5 = -1;
			int j5 = 0;
			for (int i2 = 0; i2 <= 255; i2++) {
				entryInstance2.anIntArray2228[i2] = 0;
			}

			int i9 = 4095;
			for (int k8 = 15; k8 >= 0; k8--) {
				for (int l8 = 15; l8 >= 0; l8--) {
					entryInstance2.aByteArray2204[i9] = (byte) (k8 * 16 + l8);
					i9--;
				}

				entryInstance2.anIntArray2226[k8] = i9 + 1;
			}

			int l5 = 0;
			if (j5 == 0) {
				i5++;
				j5 = 50;
				byte byte12 = entryInstance2.aByteArray2219[i5];
				j8 = entryInstance2.anIntArray2200[byte12];
				ai = entryInstance2.anIntArrayArray2230[byte12];
				ai2 = entryInstance2.anIntArrayArray2210[byte12];
				ai1 = entryInstance2.anIntArrayArray2218[byte12];
			}
			j5--;
			int l6 = j8;
			int k7;
			byte byte9;
			for (k7 = method1790(l6, entryInstance2); k7 > ai[l6]; k7 = k7 << 1 | byte9) {
				l6++;
				byte9 = method1788(entryInstance2);
			}

			for (int k5 = ai2[k7 - ai1[l6]]; k5 != l4;) {
				if (k5 == 0 || k5 == 1) {
					int i6 = -1;
					int j6 = 1;
					do {
						if (k5 == 0) {
							i6 += j6;
						} else if (k5 == 1) {
							i6 += 2 * j6;
						}
						j6 *= 2;
						if (j5 == 0) {
							i5++;
							j5 = 50;
							byte byte13 = entryInstance2.aByteArray2219[i5];
							j8 = entryInstance2.anIntArray2200[byte13];
							ai = entryInstance2.anIntArrayArray2230[byte13];
							ai2 = entryInstance2.anIntArrayArray2210[byte13];
							ai1 = entryInstance2.anIntArrayArray2218[byte13];
						}
						j5--;
						int i7 = j8;
						int l7;
						byte byte10;
						for (l7 = method1790(i7, entryInstance2); l7 > ai[i7]; l7 = l7 << 1
								| byte10) {
							i7++;
							byte10 = method1788(entryInstance2);
						}

						k5 = ai2[l7 - ai1[i7]];
					} while (k5 == 0 || k5 == 1);
					i6++;
					byte byte5 = entryInstance2.aByteArray2211[entryInstance2.aByteArray2204[entryInstance2.anIntArray2226[0]] & 0xff];
					entryInstance2.anIntArray2228[byte5 & 0xff] += i6;
					for (; i6 > 0; i6--) {
						anIntArray257[l5] = byte5 & 0xff;
						l5++;
					}

				} else {
					int i11 = k5 - 1;
					byte byte6;
					if (i11 < 16) {
						int i10 = entryInstance2.anIntArray2226[0];
						byte6 = entryInstance2.aByteArray2204[i10 + i11];
						for (; i11 > 3; i11 -= 4) {
							int j11 = i10 + i11;
							entryInstance2.aByteArray2204[j11] = entryInstance2.aByteArray2204[j11 - 1];
							entryInstance2.aByteArray2204[j11 - 1] = entryInstance2.aByteArray2204[j11 - 2];
							entryInstance2.aByteArray2204[j11 - 2] = entryInstance2.aByteArray2204[j11 - 3];
							entryInstance2.aByteArray2204[j11 - 3] = entryInstance2.aByteArray2204[j11 - 4];
						}

						for (; i11 > 0; i11--) {
							entryInstance2.aByteArray2204[i10 + i11] = entryInstance2.aByteArray2204[(i10 + i11) - 1];
						}

						entryInstance2.aByteArray2204[i10] = byte6;
					} else {
						int k10 = i11 / 16;
						int l10 = i11 % 16;
						int j10 = entryInstance2.anIntArray2226[k10] + l10;
						byte6 = entryInstance2.aByteArray2204[j10];
						for (; j10 > entryInstance2.anIntArray2226[k10]; j10--) {
							entryInstance2.aByteArray2204[j10] = entryInstance2.aByteArray2204[j10 - 1];
						}

						entryInstance2.anIntArray2226[k10]++;
						for (; k10 > 0; k10--) {
							entryInstance2.anIntArray2226[k10]--;
							entryInstance2.aByteArray2204[entryInstance2.anIntArray2226[k10]] = entryInstance2.aByteArray2204[(entryInstance2.anIntArray2226[k10 - 1] + 16) - 1];
						}

						entryInstance2.anIntArray2226[0]--;
						entryInstance2.aByteArray2204[entryInstance2.anIntArray2226[0]] = byte6;
						if (entryInstance2.anIntArray2226[0] == 0) {
							int l9 = 4095;
							for (int j9 = 15; j9 >= 0; j9--) {
								for (int k9 = 15; k9 >= 0; k9--) {
									entryInstance2.aByteArray2204[l9] = entryInstance2.aByteArray2204[entryInstance2.anIntArray2226[j9]
											+ k9];
									l9--;
								}

								entryInstance2.anIntArray2226[j9] = l9 + 1;
							}

						}
					}
					entryInstance2.anIntArray2228[entryInstance2.aByteArray2211[byte6 & 0xff] & 0xff]++;
					anIntArray257[l5] = entryInstance2.aByteArray2211[byte6 & 0xff] & 0xff;
					l5++;
					if (j5 == 0) {
						i5++;
						j5 = 50;
						byte byte14 = entryInstance2.aByteArray2219[i5];
						j8 = entryInstance2.anIntArray2200[byte14];
						ai = entryInstance2.anIntArrayArray2230[byte14];
						ai2 = entryInstance2.anIntArrayArray2210[byte14];
						ai1 = entryInstance2.anIntArrayArray2218[byte14];
					}
					j5--;
					int j7 = j8;
					int i8;
					byte byte11;
					for (i8 = method1790(j7, entryInstance2); i8 > ai[j7]; i8 = i8 << 1
							| byte11) {
						j7++;
						byte11 = method1788(entryInstance2);
					}

					k5 = ai2[i8 - ai1[j7]];
				}
			}

			entryInstance2.anInt2222 = 0;
			entryInstance2.aByte2201 = 0;
			entryInstance2.anIntArray2220[0] = 0;
			for (int j2 = 1; j2 <= 256; j2++) {
				entryInstance2.anIntArray2220[j2] = entryInstance2.anIntArray2228[j2 - 1];
			}

			for (int k2 = 1; k2 <= 256; k2++) {
				entryInstance2.anIntArray2220[k2] += entryInstance2.anIntArray2220[k2 - 1];
			}

			for (int l2 = 0; l2 < l5; l2++) {
				byte byte7 = (byte) (anIntArray257[l2] & 0xff);
				anIntArray257[entryInstance2.anIntArray2220[byte7 & 0xff]] |= l2 << 8;
				entryInstance2.anIntArray2220[byte7 & 0xff]++;
			}

			entryInstance2.anInt2208 = anIntArray257[entryInstance2.anInt2223] >> 8;
			entryInstance2.anInt2227 = 0;
			entryInstance2.anInt2208 = anIntArray257[entryInstance2.anInt2208];
			entryInstance2.anInt2221 = (byte) (entryInstance2.anInt2208 & 0xff);
			entryInstance2.anInt2208 >>= 8;
			entryInstance2.anInt2227++;
			entryInstance2.anInt2225 = l5;
			method1787(entryInstance2);
			if (entryInstance2.anInt2227 == entryInstance2.anInt2225 + 1 && entryInstance2.anInt2222 == 0) {
				flag18 = true;
			} else {
				flag18 = false;
			}
		}
	}

}
