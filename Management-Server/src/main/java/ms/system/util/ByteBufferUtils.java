package ms.system.util;

import java.nio.ByteBuffer;


/**
 * Holds utility methods for reading/writing a byte buffer.
 * @author Emperor
 *
 */
public final class ByteBufferUtils {

	/**
	 * Gets a string from the byte buffer.
	 * @param buffer The byte buffer.
	 * @return The string.
	 */
	public static String getString(ByteBuffer buffer) {
		StringBuilder sb = new StringBuilder();
		byte b;
		while ((b = buffer.get()) != 0) {
			sb.append((char) b);
		}
		return sb.toString();
	}

	/**
	 * Puts a string on the byte buffer.
	 * @param s The string to put.
	 * @param buffer The byte buffer.
	 */
	public static void putString(String s, ByteBuffer buffer) {
		buffer.put(s.getBytes()).put((byte) 0);
	}
	
	/**
	 * Gets a string from the byte buffer.
	 * @param s The string.
	 * @param buffer The byte buffer.
	 * @return The string.
	 */
	public static ByteBuffer putGJ2String(String s, ByteBuffer buffer) {
		byte[] packed = new byte[256];
		int length = packGJString2(0, packed, s);
		return buffer.put((byte) 0).put(packed, 0, length).put((byte) 0);
	}
	
	/**
	 * Decodes the XTEA encryption.
	 * @param keys The keys.
	 * @param start The start index.
	 * @param end The end index.
	 * @param buffer The byte buffer.
	 */
	public static void decodeXTEA(int[] keys, int start, int end, ByteBuffer buffer) {
		int l = buffer.position();
		buffer.position(start);
		int length = (end - start) / 8;
		for (int i = 0; i < length; i++) {
			int firstInt = buffer.getInt();
			int secondInt = buffer.getInt();
			int sum = 0xc6ef3720;
			int delta = 0x9e3779b9;
			for (int j = 32; j-- > 0;) {
				secondInt -= keys[(sum & 0x1c84) >>> 11] + sum ^ (firstInt >>> 5 ^ firstInt << 4) + firstInt;
				sum -= delta;
				firstInt -= (secondInt >>> 5 ^ secondInt << 4) + secondInt ^ keys[sum & 3] + sum;
			}
			buffer.position(buffer.position() - 8);
			buffer.putInt(firstInt);
			buffer.putInt(secondInt);
		}
		buffer.position(l);
	}
	
	/**
	 * Converts a String to an Integer?
	 * 
	 * @param position
	 *            The position.
	 * @param buffer
	 *            The buffer used.
	 * @param string
	 *            The String to convert.
	 * @return The Integer.
	 */
	public static int packGJString2(int position, byte[] buffer, String string) {
		int length = string.length();
		int offset = position;
		for (int i = 0; length > i; i++) {
			int character = string.charAt(i);
			if (character > 127) {
				if (character > 2047) {
					buffer[offset++] = (byte) ((character | 919275) >> 12);
					buffer[offset++] = (byte) (128 | ((character >> 6) & 63));
					buffer[offset++] = (byte) (128 | (character & 63));
				} else {
					buffer[offset++] = (byte) ((character | 12309) >> 6);
					buffer[offset++] = (byte) (128 | (character & 63));
				}
			} else
				buffer[offset++] = (byte) character;
		}
		return offset - position;
	}
	
	/**
	 * Gets a tri-byte from the buffer.
	 * @param buffer The buffer.
	 * @return The value.
	 */
	public static int getTriByte(ByteBuffer buffer) {
		return ((buffer.get() & 0xFF) << 16) + ((buffer.get() & 0xFF) << 8) + (buffer.get() & 0xFF);
	}

	/**
	 * Gets a smart from the buffer.
	 * @param buffer The buffer.
	 * @return The value.
	 */
	public static int getSmart(ByteBuffer buffer) {
		int peek = buffer.get() & 0xFF;
		if (peek <= Byte.MAX_VALUE) {
			return peek;
		}
		return ((peek << 8) | (buffer.get() & 0xFF)) - 32768;
	}

	/**
	 * Gets a smart from the buffer.
	 * @param buffer The buffer.
	 * @return The value.
	 */
	public static int getBigSmart(ByteBuffer buffer) {
		int value = 0;
		int current = getSmart(buffer);
		while (current == 32767) {
			current = getSmart(buffer);
			value += 32767;
		}
		value += current;
		return value;
	}
	
	/**
	 * Constructs a new {@code ByteBufferUtils} {@code Object}.
	 */
	private ByteBufferUtils() {
		/*
		 * empty.
		 */
	}
}