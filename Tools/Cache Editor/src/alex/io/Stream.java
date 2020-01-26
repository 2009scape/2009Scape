package alex.io;

import java.math.BigInteger;

import alex.util.Methods;

public class Stream {
	public int offset;
	public byte payload[];

	public Stream(byte abyte0[]) {
		offset = 0;
		payload = abyte0;
	}

	public Stream(int abyte0[]) {
		offset = 0;
		payload = new byte[abyte0.length];
		for(int i = 0; i < payload.length; i++)
			payload[i] = (byte) abyte0[i];
	}
	
	public Stream(int size) {
		payload = new byte[size];
		offset = 0;
	}

	final boolean compareCrcs() {
		offset -= 4;
		int i = Methods.getCrc(payload, 0, offset);
		int j = getInt();
		return j == i;
	}

	public final void decodeXTEA(int keys[], int start, int end) {
		int l = offset;
		offset = start;
		int i1 = (end - start) / 8;
		for (int j1 = 0; j1 < i1; j1++) {
			int k1 = getInt();
			int l1 = getInt();
			int sum = 0xc6ef3720;
			int delta = 0x9e3779b9;
			for (int k2 = 32; k2-- > 0;) {
				l1 -= keys[(sum & 0x1c84) >>> 11] + sum ^ (k1 >>> 5 ^ k1 << 4)
						+ k1;
				sum -= delta;
				k1 -= (l1 >>> 5 ^ l1 << 4) + l1 ^ keys[sum & 3] + sum;
			}

			offset -= 8;
			putInt(k1);
			putInt(l1);
		}

		offset = l;
	}

	public final void encodeXTEA(int keys[]) {
		int j = offset / 8;
		offset = 0;
		for (int k = 0; k < j; k++) {
			int l = getInt();
			int i1 = getInt();
			int sum = 0;
			int delta = 0x9e3779b9;
			for (int l1 = 32; l1-- > 0;) {
				l += sum + keys[3 & sum] ^ i1 + (i1 >>> 5 ^ i1 << 4);
				sum += delta;
				i1 += l + (l >>> 5 ^ l << 4) ^ keys[(0x1eec & sum) >>> 11]
						+ sum;
			}

			offset -= 8;
			putInt(l);
			putInt(i1);
		}

	}

	public final byte getByte() {
		return payload[offset++];
	}

	final byte getByteA() {
		return (byte) (payload[offset++] - 128);
	}

	public final void getBytes(byte buffer[], int off, int len) {
		for (int k = off; k < len + off; k++) {
			buffer[k] = payload[offset++];
		}

	}

	final byte getByteS() {
		return (byte) (-payload[offset++] + 128);
	}

	final void getBytesAReverse(byte buffer[], int off, int len) {
		int l = -1 + len + off;
		for (; off <= l; l--) {
			buffer[l] = (byte) (payload[offset++] - 128);
		}

	}

	final void getBytesReverse(byte buffer[], int off, int len) {
		for (int l = -1 + (off + len); l >= off; l--) {
			buffer[l] = payload[offset++];
		}

	}

	final String getCheckedString() {
		if (payload[offset] == 0) {
			offset++;
			return null;
		}
		return getString();
	}

	public final int getInt() {
		offset += 4;
		return ((0xff & payload[-3 + offset]) << 16)
				+ ((((0xff & payload[-4 + offset]) << 24) + ((payload[-2
						+ offset] & 0xff) << 8)) + (payload[-1 + offset] & 0xff));
	}

	final String getJStr() {
		byte byte0 = payload[offset++];
		if (byte0 != 0) {
			throw new IllegalStateException("Bad version number in gjstr2");
		}
		int j = offset;
		while (payload[offset++] != 0)
			;
		int k = -1 + offset - j;
		if (k == 0) {
			return "";
		} else {
			return Methods.getStringFromBytes(payload, j, k);
		}
	}

	final int getLEInt() {
		offset += 4;
		return ((0xff & payload[offset - 1]) << 24)
				+ ((0xff0000 & payload[-2 + offset] << 16)
						+ ((0xff & payload[offset - 3]) << 8) + (0xff & payload[offset - 4]));
	}

	final int getLEShort() {
		offset += 2;
		int i = (0xff & payload[-2 + offset])
				+ (0xff00 & payload[offset - 1] << 8);
		if (i > 32767) {
			i -= 0x10000;
		}
		return i;
	}

	final int getLEShortA() {
		offset += 2;
		int i = (-128 + payload[-2 + offset] & 0xff)
				+ ((0xff & payload[offset - 1]) << 8);
		if (i > 32767) {
			i -= 0x10000;
		}
		return i;
	}

	final int getLEUShort() {
		offset += 2;
		return (payload[-2 + offset] & 0xff)
				+ ((0xff & payload[-1 + offset]) << 8);
	}

	final int getLEUShortA() {
		offset += 2;
		return ((payload[offset - 1] & 0xff) << 8)
				+ (-128 + payload[-2 + offset] & 0xff);
	}

	final long getLong() {
		long l = 0xffffffffL & getInt();
		long l1 = 0xffffffffL & getInt();
		return l1 + (l << 32);
	}

	public final int getMediumInt() {
		offset += 3;
		return (0xff & payload[offset - 1])
				+ ((payload[offset - 3] << 16 & 0xff0000) + (0xff00 & payload[offset - 2] << 8));
	}

	final int getMEInt1() {
		offset += 4;
		return ((payload[offset - 4] & 0xff) << 16)
				+ (((0xff000000 & payload[offset - 3] << 24) + ((0xff & payload[offset - 1]) << 8)) + (0xff & payload[offset - 2]));
	}

	final int getMEInt2() {
		offset += 4;
		return (payload[offset - 2] << 24 & 0xff000000)
				+ (((payload[-1 + offset] & 0xff) << 16) + (payload[-4 + offset] << 8 & 0xff00))
				+ (0xff & payload[-3 + offset]);
	}

	final byte getNegByte() {
		return (byte) (-payload[offset++]);
	}

	final int getNegUByte() {
		return 0xff & -payload[offset++];
	}

	final long getShiftedLong(int i) {
		if (--i < 0 || i > 7) {
			throw new IllegalArgumentException();
		}
		int j = i * 8;
		long l = 0L;
		for (; j >= 0; j -= 8) {
			l |= (payload[offset++] & 255L) << j;
		}

		return l;
	}

	public final int getShort() {
		offset += 2;
		int i = ((payload[offset - 2] & 0xff) << 8)
				+ (0xff & payload[offset - 1]);
		if (i > 32767) {
			i -= 0x10000;
		}
		return i;
	}

	final int getShortA() {
		offset += 2;
		int j = (payload[-1 + offset] - 128 & 0xff)
				+ (0xff00 & payload[offset - 2] << 8);
		if (j > 32767) {
			j -= 0x10000;
		}
		return j;
	}

	final int getSmallSmart() {
		int i = 0xff & payload[offset];
		if (i >= 128) {
			return -49152 + getUShort();
		} else {
			return -64 + getUByte();
		}
	}

	final int getSmart() {
		int i = payload[offset] & 0xff;
		if (i >= 128) {
			return getUShort() - 32768;
		} else {
			return getUByte();
		}
	}

	final int getSmarts() {
		int i = 0;
		int j;
		for (j = getSmart(); j == 32767;) {
			j = getSmart();
			i += 32767;
		}

		i += j;
		return i;
	}

	public final String getString() {
		int j = offset;
		while (payload[offset++] != 0)
			;
		int k = -1 + (offset - j);
		if (k == 0) {
			return "";
		} else {
			return Methods.getStringFromBytes(payload, j, k);
		}
	}

	public final int getUByte() {
		return payload[offset++] & 0xff;
	}

	final int getUByteA() {
		return -128 + payload[offset++] & 0xff;
	}

	final int getUByteS() {
		return 0xff & -payload[offset++] + 128;
	}

	public final int getUShort() {
		offset += 2;
		return (payload[offset - 2] << 8 & 0xff00)
				+ (payload[offset - 1] & 0xff);
	}

	final int getUShortA() {
		offset += 2;
		return (0xff & payload[offset - 1] - 128)
				+ ((0xff & payload[offset - 2]) << 8);
	}

	final int method124() {
		byte byte1 = payload[offset++];
		int i = 0;
		for (; byte1 < 0; byte1 = payload[offset++]) {
			i = (0x7f & byte1 | i) << 7;
		}

		return i | byte1;
	}

	public final void putByte(int i) {
		payload[offset++] = (byte) i;
	}
	
	public void putString(String s) {
		System.arraycopy(s.getBytes(), 0, payload, offset, s.length());
		offset = offset + s.length();
		putByte(0);
	}

	final void putByteA(int i) {
		payload[offset++] = (byte) (i + 128);
	}

	final void putBytes(byte buffer[], int off, int len) {
		for (int k = off; off + len > k; k++) {
			payload[offset++] = buffer[k];
		}

	}

	final void putByteS(int i) {
		payload[offset++] = (byte) (128 - i);
	}

	final int putCrc(int off) {
		int k = Methods.getCrc(payload, off, offset);
		putInt(k);
		return k;
	}

	final void putFlags(int i) {
		if (~(0xffffff80 & i) != -1) {
			if (~(i & 0xffffc000) != -1) {
				if ((0xffe00000 & i) != 0) {
					if ((i & 0xf0000000) != 0) {
						putByte(i >>> 28 | 0x80);
					}
					putByte((0x10039c30 | i) >>> 21);
				}
				putByte((i | 0x203a0e) >>> 14);
			}
			putByte((0x403d | i) >>> 7);
		}
		putByte(i & 0x7f);
	}

	public final void putInt(int i) {
		payload[offset++] = (byte) (i >> 24);
		payload[offset++] = (byte) (i >> 16);
		payload[offset++] = (byte) (i >> 8);
		payload[offset++] = (byte) i;
	}

	final void putJStr(String s) {
		int j = s.indexOf('\0');
		if (j >= 0) {
			throw new IllegalArgumentException("NUL character at " + j
					+ " - cannot pjstr");
		}
		offset += Methods.getStringBytes(s, 0, s.length(), payload, offset);
		payload[offset++] = 0;
	}
	
	

	final void putLEInt(int i) {
		payload[offset++] = (byte) i;
		payload[offset++] = (byte) (i >> 8);
		payload[offset++] = (byte) (i >> 16);
		payload[offset++] = (byte) (i >> 24);
	}

	final void putLEShort(int i) {
		payload[offset++] = (byte) i;
		payload[offset++] = (byte) (i >> 8);
	}

	final void putLEShortA(int i) {
		payload[offset++] = (byte) (i + 128);
		payload[offset++] = (byte) (i >> 8);
	}

	final void putLong(long l) {
		payload[offset++] = (byte) (int) (l >> 56);
		payload[offset++] = (byte) (int) (l >> 48);
		payload[offset++] = (byte) (int) (l >> 40);
		payload[offset++] = (byte) (int) (l >> 32);
		payload[offset++] = (byte) (int) (l >> 24);
		payload[offset++] = (byte) (int) (l >> 16);
		payload[offset++] = (byte) (int) (l >> 8);
		payload[offset++] = (byte) (int) l;
	}

	public final void putMediumInt(int j) {
		payload[offset++] = (byte) (j >> 16);
		payload[offset++] = (byte) (j >> 8);
		payload[offset++] = (byte) j;
	}

	final void putMEInt1(int j) {
		payload[offset++] = (byte) (j >> 16);
		payload[offset++] = (byte) (j >> 24);
		payload[offset++] = (byte) j;
		payload[offset++] = (byte) (j >> 8);
	}

	final void putMEInt2(int i) {
		payload[offset++] = (byte) (i >> 8);
		payload[offset++] = (byte) i;
		payload[offset++] = (byte) (i >> 24);
		payload[offset++] = (byte) (i >> 16);
	}

	final void putNegByte(int i) {
		payload[offset++] = (byte) (-i);
	}

	final void putShiftedLong(int j, long l) {
		if (--j < 0 || j > 7) {
			throw new IllegalArgumentException();
		}
		for (int k = j * 8; k >= 0; k -= 8) {
			payload[offset++] = (byte) (int) (l >> k);
		}
	}

	public final void putShort(int i) {
		payload[offset++] = (byte) (i >> 8);
		payload[offset++] = (byte) i;
	}

	final void putShortA(int i) {
		payload[offset++] = (byte) (i >> 8);
		payload[offset++] = (byte) (128 + i);
	}

	final void putSizeByte(int i) {
		payload[-1 - i + offset] = (byte) i;
	}

	final void putSizeInt(int i) {
		payload[offset - (i + 4)] = (byte) (i >> 24);
		payload[-3 + (-i + offset)] = (byte) (i >> 16);
		payload[-2 + (offset - i)] = (byte) (i >> 8);
		payload[-i + (offset - 1)] = (byte) i;
	}

	final void putSizeShort(int j) {
		payload[-2 + (offset - j)] = (byte) (j >> 8);
		payload[-1 + offset - j] = (byte) j;
	}

	final void putSmart(int i) {
		if (i >= 0 && i < 128) {
			putByte(i);
			return;
		}
		if (i >= 0 && i < 32768) {
			putShort(i + 32768);
		} else {
			throw new IllegalArgumentException();
		}
	}

	final void rsaEncode(BigInteger exponent, BigInteger modulus) {
		int j = offset;
		offset = 0;
		byte abyte0[] = new byte[j];
		getBytes(abyte0, 0, j);
		BigInteger biginteger2 = new BigInteger(abyte0);
		BigInteger biginteger3 = biginteger2.modPow(exponent, modulus);
		byte abyte1[] = biginteger3.toByteArray();
		offset = 0;
		putByte(abyte1.length);
		putBytes(abyte1, 0, abyte1.length);
	}
}
