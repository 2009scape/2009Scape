/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.loaders.animations;

import com.alex.io.InputStream;
import com.alex.store.Store;
import com.alex.utils.Utils;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author Travis
 */
public class AnimationDefinitions {

    public static Store cache;

    public static int id;
    public int loopCycles;
    public int anInt2137;
    public static int[] frames;
    public int anInt2140;
    public boolean aBoolean2141 = false;
    public int priority;
    public int leftHandEquip;
    public int rightHandEquip = -1;
    public int anInt2145;
    public int[][] handledSounds;
    public boolean[] aBooleanArray2149;
    public int[] anIntArray2151;
    public boolean aBoolean2152;
    public static int[] delays;
    public int anInt2155;
    public boolean aBoolean2158;
    public boolean aBoolean2159;
    public int anInt2162;
    public int loopDelay;

    // added
    public int[] soundMinDelay;
    public int[] soundMaxDelay;
    public int[] anIntArray1362;
    public boolean effect2Sound;

    public static void main(String[] args) throws IOException {
	cache = new Store("cache/");
	for (int i = 0; i < 1/*Utils.getAnimationDefinitionsSize(cache)*/; i++) {
            //getAnimationDefinitions(i).method2394();
	    System.out.println("Emote ID: " + i);
	    for (int k = 0; k < getAnimationDefinitions(i).delays.length; k++) {
		System.out.println("delays[" + k + "] = "
			+ getAnimationDefinitions(i).delays[k]);
	    }
	    for (int k = 0; k < getAnimationDefinitions(i).frames.length; k++) {
		System.out.println("frames[" + k + "] = "
			+ getAnimationDefinitions(i).frames[k]);
	    }
	    System.out.println("loopDelay = "+getAnimationDefinitions(i).loopDelay);
            System.out.println("leftHandEquip = "+getAnimationDefinitions(i).leftHandEquip);
            System.out.println("priority = "+getAnimationDefinitions(i).priority);
            System.out.println("rightHandEquip = "+getAnimationDefinitions(i).rightHandEquip);
            System.out.println("loopCycles = "+getAnimationDefinitions(i).loopCycles);
            System.out.println("anInt2140 = "+getAnimationDefinitions(i).anInt2140);
            System.out.println("anInt2162 = "+getAnimationDefinitions(i).anInt2162);
            System.out.println("anInt2155 = "+getAnimationDefinitions(i).anInt2155);
            System.out.println("anInt2145 = "+getAnimationDefinitions(i).anInt2145);
            for (int k = 0; k < getAnimationDefinitions(i).anIntArray2151.length; k++) {
		System.out.println("anIntArray2151[" + k + "] = "
			+ getAnimationDefinitions(i).anIntArray2151[k]);
	    }
            for (int k = 0; k < getAnimationDefinitions(i).aBooleanArray2149.length; k++) {
		System.out.println("aBooleanArray2149[" + k + "] = "
			+ getAnimationDefinitions(i).aBooleanArray2149[k]);
	    }
            System.out.println("aBoolean2152 = "+getAnimationDefinitions(i).aBoolean2152);
            for (int k = 0; k < getAnimationDefinitions(i).anIntArray1362.length; k++) {
		System.out.println("anIntArray1362[" + k + "] = "
			+ getAnimationDefinitions(i).anIntArray1362[k]);
	    }
	}
    }

    private static final ConcurrentHashMap<Integer, AnimationDefinitions> animDefs = new ConcurrentHashMap<Integer, AnimationDefinitions>();

    public static final AnimationDefinitions getAnimationDefinitions(int emoteId) {
	try {
	    AnimationDefinitions defs = animDefs.get(emoteId);
	    if (defs != null)
		return defs;
	    byte[] data = cache.getIndexes()[20].getFile(emoteId >>> 7,
		    emoteId & 0x7f);
	    defs = new AnimationDefinitions();
	    if (data != null)
		defs.readValueLoop(new InputStream(data));
	    defs.method2394();
	    animDefs.put(emoteId, defs);
	    id = emoteId;
	    return defs;
	} catch (Throwable t) {
	    return null;
	}
    }

    private void readValueLoop(InputStream stream) {
	for (;;) {
	    int opcode = stream.readUnsignedByte();
	    if (opcode == 0)
		break;
	    readValues(stream, opcode);
	}
    }

    public int getEmoteTime() {
	if (delays == null)
	    return 0;
	int ms = 0;
	for (int i : delays)
	    ms += i;
	return ms * 30;
    }

    public int getEmoteGameTickets() {
	return getEmoteTime() / 1000;
    }

    private void readValues(InputStream stream, int opcode) {
	if (opcode == 1) {
	    int i = stream.readUnsignedShort();
	    delays = new int[i];
	    for (int i_16_ = 0; (i ^ 0xffffffff) < (i_16_ ^ 0xffffffff); i_16_++)
		delays[i_16_] = stream.readUnsignedShort();
	    frames = new int[i];
	    for (int i_17_ = 0; (i_17_ ^ 0xffffffff) > (i ^ 0xffffffff); i_17_++)
		frames[i_17_] = stream.readUnsignedShort();
	    for (int i_18_ = 0; i_18_ < i; i_18_++)
		frames[i_18_] = ((stream.readUnsignedShort() << 16) + frames[i_18_]);
	} else if (opcode == 2) {
	    loopDelay = stream.readUnsignedShort();
	} else if (opcode == 3) {
	    aBooleanArray2149 = new boolean[256];
	    int i = stream.readUnsignedByte();
	    for (int i_24_ = 0; i_24_ < i; i_24_++)
		aBooleanArray2149[stream.readUnsignedByte()] = true;
	} else if (opcode == 4)
	    aBoolean2152 = true;
	else if (opcode == 5)
	    priority = stream.readUnsignedByte();
	else if (opcode == 6)
	    rightHandEquip = stream.readUnsignedShort();
	else if (opcode == 7)
	    leftHandEquip = stream.readUnsignedShort();
	else if (opcode == 8)
	    loopCycles = stream.readUnsignedByte();
	else if (opcode == 9)
	    anInt2140 = stream.readUnsignedByte();
	else if (opcode == 10)
	    anInt2162 = stream.readUnsignedByte();
	else if (opcode == 11)
	    anInt2155 = stream.readUnsignedByte();
	else if (opcode == 12) {
	    int i = stream.readUnsignedByte();
	    anIntArray2151 = new int[i];
	    for (int i_19_ = 0; ((i_19_ ^ 0xffffffff) > (i ^ 0xffffffff)); i_19_++)
		anIntArray2151[i_19_] = stream.readUnsignedShort();
	    for (int i_20_ = 0; i > i_20_; i_20_++)
		anIntArray2151[i_20_] = ((stream.readUnsignedShort() << 16) + anIntArray2151[i_20_]);
	} else if (opcode == 13) {
	    int i = stream.readUnsignedShort();
	    handledSounds = new int[i][];
	    for (int i_21_ = 0; i_21_ < i; i_21_++) {
		int i_22_ = stream.readUnsignedByte();
		if ((i_22_ ^ 0xffffffff) < -1) {
		    handledSounds[i_21_] = new int[i_22_];
		    handledSounds[i_21_][0] = stream.read24BitInt();
		    for (int i_23_ = 1; ((i_22_ ^ 0xffffffff) < (i_23_ ^ 0xffffffff)); i_23_++) {
			handledSounds[i_21_][i_23_] = stream
				.readUnsignedShort();
		    }
		}
	    }
	} else if (opcode == 14)
	    aBoolean2141 = true;
	else if (opcode == 15)
	    aBoolean2159 = true;
	else if (opcode == 16)
	    aBoolean2158 = true;
	else if (opcode == 17)
	    anInt2145 = stream.readUnsignedByte();
	else if (opcode == 18)
	    effect2Sound = true;
	else if (opcode == 19) {
	    if (anIntArray1362 == null) {
		anIntArray1362 = new int[handledSounds.length];
		for (int index = 0; index < handledSounds.length; index++)
		    anIntArray1362[index] = 255;
	    }
	    anIntArray1362[stream.readUnsignedByte()] = stream
		    .readUnsignedByte();
	} else if (opcode == 20) {
	    if ((soundMaxDelay == null) || (soundMinDelay == null)) {
		soundMaxDelay = (new int[handledSounds.length]);
		soundMinDelay = (new int[handledSounds.length]);
		for (int i_34_ = 0; (i_34_ < handledSounds.length); i_34_++) {
		    soundMaxDelay[i_34_] = 256;
		    soundMinDelay[i_34_] = 256;
		}
	    }
	    int index = stream.readUnsignedByte();
	    soundMaxDelay[index] = stream.readUnsignedShort();
	    soundMinDelay[index] = stream.readUnsignedShort();
	}
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

    public AnimationDefinitions() {
	loopCycles = 99;
	leftHandEquip = -1;
	anInt2140 = -1;
	aBoolean2152 = false;
	priority = 5;
	aBoolean2159 = false;
	loopDelay = -1;
	anInt2155 = 2;
	aBoolean2158 = false;
	anInt2162 = -1;
    }
}
