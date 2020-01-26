package org.arios.cache.def.impl;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.arios.cache.Cache;
import org.arios.cache.misc.ByteBufferUtils;

/**
 * Represents an animation's definitions.
 * @author Emperor
 */
public final class AnimationDefinition {

	public int anInt2136;
	public int anInt2137;
	public int[] anIntArray2139;
	public int anInt2140;
	public boolean aBoolean2141 = false;
	public int anInt2142;
	public int emoteItem;
	public int anInt2144 = -1;
	public int[][] handledSounds;
	public boolean[] aBooleanArray2149;
	public int[] anIntArray2151;
	public boolean aBoolean2152;
	public int[] durations;
	public int anInt2155;
	public boolean aBoolean2158;
	public boolean aBoolean2159;
	public int anInt2162;
	public int anInt2163;
	boolean newHeader;

	// added
	public int[] soundMinDelay;
	public int[] soundMaxDelay;
	public int[] anIntArray1362;
	public boolean effect2Sound;

	private static final Map<Integer, AnimationDefinition> animDefs = new HashMap<>();

	public static final AnimationDefinition forId(int emoteId) {
		try {
			AnimationDefinition defs = animDefs.get(emoteId);
			if (defs != null) {
				return defs;
			}
			byte[] data = Cache.getIndexes()[20].getFileData(emoteId >>> 7, emoteId & 0x7f);
			defs = new AnimationDefinition();
			if (data != null) {
				defs.readValueLoop(ByteBuffer.wrap(data));
			}
			defs.method2394();
			animDefs.put(emoteId, defs);
			return defs;
		} catch (Throwable t) {
			return null;
		}
	}

	private void readValueLoop(ByteBuffer buffer) {
		for (;;) {
			int opcode = buffer.get() & 0xFF;
			if (opcode == 0) {
				break;
			}
			readValues(buffer, opcode);
		}
	}

	/**
	 * Gets the duration of this animation in milliseconds.
	 * @return The duration.
	 */
	public int getDuration() {
		if (durations == null) {
			return 0;
		}
		int duration = 0;
		for (int i : durations) {
			if (i > 100) {
				continue;
			}
			duration += i * 20;
		}
		return duration;
	}

	/**
	 * Gets the duration of this animation in (600ms) ticks.
	 * @return The duration in ticks.
	 */
	public int getDurationTicks() {
		int ticks = getDuration() / 600;
		return ticks < 1 ? 1 : ticks;
	}

	private void readValues(ByteBuffer buffer, int opcode) {
		if (opcode == 1) {
			int length = buffer.getShort() & 0xFFFF;
			durations = new int[length];
			for (int i = 0; i < length; i++) {
				durations[i] = buffer.getShort() & 0xFFFF;
			}
			anIntArray2139 = new int[length];
			for (int i = 0; i < length; i++) {
				anIntArray2139[i] = buffer.getShort() & 0xFFFF;
			}
			for (int i = 0; i < length; i++) {
				anIntArray2139[i] = ((buffer.getShort() & 0xFFFF << 16) + anIntArray2139[i]);
			}
		} else if (opcode != 2) {
			if (opcode != 3) {
				if (opcode == 4)
					aBoolean2152 = true;
				else if (opcode == 5)
					anInt2142 = buffer.get() & 0xFF;
				else if (opcode != 6) {
					if (opcode == 7)
						emoteItem = buffer.getShort() & 0xFFFF;
					else if ((opcode ^ 0xffffffff) != -9) {
						if (opcode != 9) {
							if (opcode != 10) {
								if (opcode == 11)
									anInt2155 = buffer.get() & 0xFF;
								else if (opcode == 12) {
									int i = buffer.get() & 0xFF;
									anIntArray2151 = new int[i];
									for (int i_19_ = 0; ((i_19_ ^ 0xffffffff) > (i ^ 0xffffffff)); i_19_++)
										anIntArray2151[i_19_] = buffer.getShort() & 0xFFFF;
									for (int i_20_ = 0; i > i_20_; i_20_++)
										anIntArray2151[i_20_] = ((buffer.getShort() & 0xFFFF << 16) + anIntArray2151[i_20_]);
								} else if (opcode == 13) {
									// opcode 13
									int i = buffer.getShort() & 0xFFFF;
									handledSounds = new int[i][];
									for (int i_21_ = 0; i_21_ < i; i_21_++) {
										int i_22_ = buffer.get() & 0xFF;
										if ((i_22_ ^ 0xffffffff) < -1) {
											handledSounds[i_21_] = new int[i_22_];
											handledSounds[i_21_][0] = ByteBufferUtils.getTriByte(buffer);
											for (int i_23_ = 1; ((i_22_ ^ 0xffffffff) < (i_23_ ^ 0xffffffff)); i_23_++) {
												handledSounds[i_21_][i_23_] = buffer.getShort() & 0xFFFF;
											}
										}
									}
								} else if (opcode == 14) {
									aBoolean2141 = true;
								} else {
									System.out.println("Unhandled animation opcode " + opcode);
								}
							} else
								anInt2162 = buffer.get() & 0xFF;
						} else
							anInt2140 = buffer.get() & 0xFF;
					} else
						anInt2136 = buffer.get() & 0xFF;
				} else
					anInt2144 = buffer.getShort() & 0xFFFF;
			} else {
				aBooleanArray2149 = new boolean[256];
				int length = buffer.get() & 0xFF;
				for (int i = 0; i < length; i++) {
					aBooleanArray2149[buffer.get() & 0xFF] = true;
				}
			}
		} else
			anInt2163 = buffer.getShort() & 0xFFFF;
	}

	public void method2394() {
		if (anInt2140 == -1) {
			if (aBooleanArray2149 == null)
				anInt2140 = 0;
			else
				anInt2140 = 2;
		}
		if (anInt2162 == -1) {
			if (aBooleanArray2149 == null)
				anInt2162 = 0;
			else
				anInt2162 = 2;
		}
	}

	public AnimationDefinition() {
		anInt2136 = 99;
		emoteItem = -1;
		anInt2140 = -1;
		aBoolean2152 = false;
		anInt2142 = 5;
		aBoolean2159 = false;
		anInt2163 = -1;
		anInt2155 = 2;
		aBoolean2158 = false;
		anInt2162 = -1;
	}
}