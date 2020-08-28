package org.runite.jagex;
/* Class152 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class Class152
{
    private final int anInt1937;
    private final int anInt1938;
    private final int anInt1939 = Class3_Sub14.method368(16);
    private final int anInt1940;
    private final int anInt1941;
    private final int[] anIntArray1942;
    private final int anInt1943;
    
    Class152() {
	anInt1938 = Class3_Sub14.method368(24);
	anInt1940 = Class3_Sub14.method368(24);
	anInt1941 = Class3_Sub14.method368(24) + 1;
	anInt1943 = Class3_Sub14.method368(6) + 1;
	anInt1937 = Class3_Sub14.method368(8);
	int[] is = new int[anInt1943];
	for (int i = 0; i < anInt1943; i++) {
	    int i_0_ = 0;
	    int i_1_ = Class3_Sub14.method368(3);
	    boolean bool = Class3_Sub14.method364() != 0;
	    if (bool)
		i_0_ = Class3_Sub14.method368(5);
	    is[i] = i_0_ << 3 | i_1_;
	}
	anIntArray1942 = new int[anInt1943 * 8];
	for (int i = 0; i < anInt1943 * 8; i++)
	    anIntArray1942[i] = ((is[i >> 3] & 1 << (i & 0x7)) != 0
				 ? Class3_Sub14.method368(8) : -1);
    }
    
    final void method2112(float[] fs, int i, boolean bool) {
	for (int i_2_ = 0; i_2_ < i; i_2_++)
	    fs[i_2_] = 0.0F;
	if (!bool) {
	    int i_3_ = (((Class71) Class3_Sub14.aClass71Array2406[anInt1937])
			.anInt1063);
	    int i_4_ = anInt1940 - anInt1938;
	    int i_5_ = i_4_ / anInt1941;
	    int[] is = new int[i_5_];
	    for (int i_6_ = 0; i_6_ < 8; i_6_++) {
		int i_7_ = 0;
		while (i_7_ < i_5_) {
		    if (i_6_ == 0) {
			int i_8_ = Class3_Sub14.aClass71Array2406
				       [anInt1937].method1290();
			for (int i_9_ = i_3_ - 1; i_9_ >= 0; i_9_--) {
			    if (i_7_ + i_9_ < i_5_)
				is[i_7_ + i_9_] = i_8_ % anInt1943;
			    i_8_ /= anInt1943;
			}
		    }
		    for (int i_10_ = 0; i_10_ < i_3_; i_10_++) {
			int i_11_ = is[i_7_];
			int i_12_ = anIntArray1942[i_11_ * 8 + i_6_];
			if (i_12_ >= 0) {
			    int i_13_ = anInt1938 + i_7_ * anInt1941;
			    Class71 class71
				= Class3_Sub14.aClass71Array2406[i_12_];
			    if (anInt1939 == 0) {
				int i_14_ = (anInt1941
					     / ((Class71) class71).anInt1063);
				for (int i_15_ = 0; i_15_ < i_14_; i_15_++) {
				    float[] fs_16_ = class71.method1288();
				    for (int i_17_ = 0;
					 i_17_ < ((Class71) class71).anInt1063;
					 i_17_++)
					fs[i_13_ + i_15_ + i_17_ * i_14_]
					    += fs_16_[i_17_];
				}
			    } else {
				int i_18_ = 0;
				while (i_18_ < anInt1941) {
				    float[] fs_19_ = class71.method1288();
				    for (int i_20_ = 0;
					 i_20_ < ((Class71) class71).anInt1063;
					 i_20_++) {
					fs[i_13_ + i_18_] += fs_19_[i_20_];
					i_18_++;
				    }
				}
			    }
			}
			if (++i_7_ >= i_5_)
			    break;
		    }
		}
	    }
	}
    }
}
