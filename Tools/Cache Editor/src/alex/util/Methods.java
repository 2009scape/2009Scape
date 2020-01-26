package alex.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.GZIPOutputStream;

import alex.CacheLoader;
import alex.compressors.BZip2OutputStream;
import alex.decompressors.BZip2Decompressor;
import alex.decompressors.GZipDecompressor;
import alex.io.Stream;

public class Methods {

	public static final CRC32 CRC32 = new CRC32();
	public static final int ATTACK = 0, DEFENCE = 1, STRENGTH = 2, HITPOINTS = 3, RANGE = 4, PRAYER = 5,
	MAGIC = 6, COOKING = 7, WOODCUTTING = 8, FLETCHING = 9, FISHING = 10, FIREMAKING = 11,
	CRAFTING = 12, SMITHING = 13, MINING = 14, HERBLORE = 15, AGILITY = 16, THIEVING = 17, SLAYER = 18,
	FARMING = 19, RUNECRAFTING = 20, CONSTRUCTION = 21, HUNTER = 22, SUMMONING = 23;
	
	public static char aCharArray5916[] = { '\u20AC', '\0', '\u201A', '\u0192',
			'\u201E', '\u2026', '\u2020', '\u2021', '\u02C6', '\u2030',
			'\u0160', '\u2039', '\u0152', '\0', '\u017D', '\0', '\0', '\u2018',
			'\u2019', '\u201C', '\u201D', '\u2022', '\u2013', '\u2014',
			'\u02DC', '\u2122', '\u0161', '\u203A', '\u0153', '\0', '\u017E',
			'\u0178' };
	public final static byte ANIM_IDX_ID = 20;
	public final static byte ANIMFRAMES_IDX_ID = 0;
	static int minLength = 0;
	static int crcTable[];
	public final static short CRCTABLE_IDX_ID = 255;
	public final static byte GFX_IDX_ID = 21;
	public final static byte HUFFMAN_IDX_ID = 10;
	public final static byte INTERFACEDEF_IDX_ID = 3;
	public final static byte INTERFACESCRIPT_IDX_ID = 12;
	public final static byte ITEMDEF_IDX_ID = 19;
	public final static byte LANDSCAPEDEF_IDX_ID = 5;
	public final static byte MODELS_IDX_ID = 7;

	public final static byte MUSIC_IDX_ID = 6;

	public final static byte NPCDEF_IDX_ID = 18;

	public final static byte OBJECTDEF_IDX_ID = 16;

	public final static byte SPRITES_IDX_ID = 8;

	static {
		crcTable = new int[256];
		for (int j = 0; j < 256; j++) {
			int i = j;
			for (int k = 0; k < 8; k++) {
				if ((1 & i) != 1) {
					i >>>= 1;
				} else {
					i = 0xedb88320 ^ i >>> 1;
				}
			}

			crcTable[j] = i;
		}

	}
	
	public static final int getAmountOfItems() {
		int lastContainerId = CacheLoader.getFileSystems()[ITEMDEF_IDX_ID].getChildCount() -1;
		return (256 * lastContainerId) + CacheLoader.getFileSystems()[ITEMDEF_IDX_ID].getChildIndexCount(lastContainerId);
		//256 is the max size of each container for items(rs does that doesnt mean its limit), and then the size of last container cuz it may not be 256
	}
	public static final int getTableSize(int length) {
		length--;
		length |= length >>> -1810941663;
		length |= length >>> 2010624802;
		length |= length >>> 10996420;
		length |= length >>> 491045480;
		length |= length >>> 1388313616;
	    return 1 + length;
	}

	public static final byte[] copyBuffer(byte buffer[]) {
		int len = buffer.length;
		byte copy[] = new byte[len];
		System.arraycopy(buffer, 0, copy, 0, len);
		return copy;
	}

	public static final int getCrc(byte buffer[], int len) {
		return getCrc(buffer, 0, len);
	}

	public static final int getCrc(byte buffer[], int off, int len) {
		int l = -1;
		for (int i1 = off; len > i1; i1++) {
			l = crcTable[(buffer[i1] ^ l) & 0xff] ^ l >>> 8;
		}
		l = ~l;
		return l;
	}

	public static final int getStringBytes(String s, int strOff, int strLen,
			byte buffer[], int bufOff) {
		int l = -strOff + strLen;
		for (int i1 = 0; i1 < l; i1++) {
			char c = s.charAt(strOff + i1);
			if (c > '\0' && c < '\200' || c >= '\240' && c <= '\377') {
				buffer[i1 + bufOff] = (byte) c;
			} else if (c == '\u20AC') {
				buffer[i1 + bufOff] = -128;
			} else if (c != '\u201A') {
				if (c == '\u0192') {
					buffer[bufOff + i1] = -125;
				} else if (c != '\u201E') {
					if (c == '\u2026') {
						buffer[bufOff + i1] = -123;
					} else if (c != '\u2020') {
						if (c == '\u2021') {
							buffer[bufOff + i1] = -121;
						} else if (c != '\u02C6') {
							if (c == '\u2030') {
								buffer[i1 + bufOff] = -119;
							} else if (c == '\u0160') {
								buffer[bufOff + i1] = -118;
							} else if (c == '\u2039') {
								buffer[i1 + bufOff] = -117;
							} else if (c == '\u0152') {
								buffer[i1 + bufOff] = -116;
							} else if (c == '\u017D') {
								buffer[i1 + bufOff] = -114;
							} else if (c == '\u2018') {
								buffer[i1 + bufOff] = -111;
							} else if (c == '\u2019') {
								buffer[i1 + bufOff] = -110;
							} else if (c == '\u201C') {
								buffer[bufOff + i1] = -109;
							} else if (c == '\u201D') {
								buffer[i1 + bufOff] = -108;
							} else if (c == '\u2022') {
								buffer[i1 + bufOff] = -107;
							} else if (c != '\u2013') {
								if (c == '\u2014') {
									buffer[i1 + bufOff] = -105;
								} else if (c != '\u02DC') {
									if (c != '\u2122') {
										if (c == '\u0161') {
											buffer[i1 + bufOff] = -102;
										} else if (c == '\u203A') {
											buffer[bufOff + i1] = -101;
										} else if (c == '\u0153') {
											buffer[bufOff + i1] = -100;
										} else if (c == '\u017E') {
											buffer[bufOff + i1] = -98;
										} else if (c == '\u0178') {
											buffer[i1 + bufOff] = -97;
										} else {
											buffer[i1 + bufOff] = 63;
										}
									} else {
										buffer[bufOff + i1] = -103;
									}
								} else {
									buffer[i1 + bufOff] = -104;
								}
							} else {
								buffer[i1 + bufOff] = -106;
							}
						} else {
							buffer[i1 + bufOff] = -120;
						}
					} else {
						buffer[bufOff + i1] = -122;
					}
				} else {
					buffer[i1 + bufOff] = -124;
				}
			} else {
				buffer[bufOff + i1] = -126;
			}
		}

		return l;
	}

	public static final String getStringFromBytes(byte buffer[], int off,
			int len) {
		char ac[] = new char[len];
		int l = 0;
		for (int i1 = 0; len > i1; i1++) {
			int j1 = 0xff & buffer[off + i1];
			if (j1 != 0) {
				if (j1 >= 128 && j1 < 160) {
					char c = aCharArray5916[-128 + j1];
					if (c == 0) {
						c = '?';
					}
					j1 = c;
				}
				ac[l++] = (char) j1;
			}
		}

		return new String(ac, 0, l);
	}

	public static final int hashFile(String name) {
		int j = name.length();
		int k = 0;
		for (int l = 0; l < j; l++) {
			k = method1258(name.charAt(l)) + ((k << 5) - k);
		}

		return k;
	}

	static final byte method1258(char c) {
		byte byte0;
		if (c > 0 && c < '\200' || c >= '\240' && c <= '\377') {
			byte0 = (byte) c;
		} else if (c != '\u20AC') {
			if (c != '\u201A') {
				if (c != '\u0192') {
					if (c == '\u201E') {
						byte0 = -124;
					} else if (c != '\u2026') {
						if (c != '\u2020') {
							if (c == '\u2021') {
								byte0 = -121;
							} else if (c == '\u02C6') {
								byte0 = -120;
							} else if (c == '\u2030') {
								byte0 = -119;
							} else if (c == '\u0160') {
								byte0 = -118;
							} else if (c == '\u2039') {
								byte0 = -117;
							} else if (c == '\u0152') {
								byte0 = -116;
							} else if (c != '\u017D') {
								if (c == '\u2018') {
									byte0 = -111;
								} else if (c != '\u2019') {
									if (c != '\u201C') {
										if (c == '\u201D') {
											byte0 = -108;
										} else if (c != '\u2022') {
											if (c == '\u2013') {
												byte0 = -106;
											} else if (c == '\u2014') {
												byte0 = -105;
											} else if (c == '\u02DC') {
												byte0 = -104;
											} else if (c == '\u2122') {
												byte0 = -103;
											} else if (c != '\u0161') {
												if (c == '\u203A') {
													byte0 = -101;
												} else if (c != '\u0153') {
													if (c == '\u017E') {
														byte0 = -98;
													} else if (c != '\u0178') {
														byte0 = 63;
													} else {
														byte0 = -97;
													}
												} else {
													byte0 = -100;
												}
											} else {
												byte0 = -102;
											}
										} else {
											byte0 = -107;
										}
									} else {
										byte0 = -109;
									}
								} else {
									byte0 = -110;
								}
							} else {
								byte0 = -114;
							}
						} else {
							byte0 = -122;
						}
					} else {
						byte0 = -123;
					}
				} else {
					byte0 = -125;
				}
			} else {
				byte0 = -126;
			}
		} else {
			byte0 = -128;
		}
		return byte0;
	}

	static int method664(int i, int j) {
		return i ^ j;
	}
	
	public static final byte[] packContainer(int compression, byte[] data) {
		Stream stream = new Stream(data.length+100); //lets be sure enougth space
		if(compression == 1) //we dont have compression 1 working
			compression = 2;
		stream.putByte(compression);
		byte[] compressedData = null;
		if(compression == 0) {
			compressedData = data;
		}else if(compression == 1) {//BZip2Compressor
			ByteArrayOutputStream compressedBytes = new ByteArrayOutputStream();
			try {
				BZip2OutputStream out = new BZip2OutputStream(compressedBytes, 9);
				out.write(data);
				out.finish();
				out.close();
				compressedData = compressedBytes.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if (compression >= 2) { //GZipCompressor
			ByteArrayOutputStream compressedBytes = new ByteArrayOutputStream();
			try {
				GZIPOutputStream out = new GZIPOutputStream(compressedBytes);
				out.write(data);
				out.finish();
				out.close();
				compressedData = compressedBytes.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		stream.putInt(compressedData.length);
		if(compression >= 1)
			stream.putInt(data.length);
		for(int index = 0; index < compressedData.length; index++)
			stream.putByte(compressedData[index]);
		byte[] readyFileData = new byte[stream.offset];
		stream.offset = 0;
		stream.getBytes(readyFileData, 0, readyFileData.length);
		return readyFileData;
	}
	
	public static final byte[] unpackContainer(byte buffer[]) {
		Stream stream = new Stream(buffer);
		int compression = stream.getUByte();
		int fileSize = stream.getInt();
		if (fileSize < 0 || minLength != 0 && minLength < fileSize) {
			throw new RuntimeException();
		}
		if (compression == 0) {
			byte unpacked[] = new byte[fileSize];
			stream.getBytes(unpacked, 0, fileSize);
			return unpacked;
		}
		int decompressedSize = stream.getInt();
		if (decompressedSize < 0 || minLength != 0 && minLength < decompressedSize) {
			throw new RuntimeException();
		}
		byte decompressed[] = new byte[decompressedSize];
		if (compression != 1) {
			GZipDecompressor.decompress(stream, decompressed);
		} else {
			BZip2Decompressor.decompress(decompressed, decompressedSize, buffer, fileSize, 9);
		}
		return decompressed;
	}

}
