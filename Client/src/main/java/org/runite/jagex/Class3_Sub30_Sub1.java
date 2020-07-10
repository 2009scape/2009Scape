package org.runite.jagex;

final class Class3_Sub30_Sub1 extends RSByteBuffer {

	static RSString[] aClass94Array3802;
	private ISAACCipher isaacCipher;
	static int[] anIntArray3804 = new int[256];
	static int[] anIntArray3805;
	private int anInt3806;
	static RSString aClass94_3807 = RSString.createRSString("m");


	static final void addChatMessage(RSString var0, int type, RSString message, int var3) {
		try {
			Class3_Sub28_Sub12.sendGameMessage(var3, type, message, (RSString)null, var0);
		} catch (RuntimeException var5) {
			throw Class44.method1067(var5, "i.W(" + (var0 != null?"{...}":"null") + ',' + type + ',' + (message != null?"{...}":"null") + ',' + var3 + ')');
		}
	}

	static final void method806(int var0, int var1) {
		try {
			if(-1 >= ~var1) {
				int var2 = Class117.anIntArray1613[var1];
				int var3 = Class27.anIntArray512[var1];
				int var4 = Class3_Sub13_Sub7.aShortArray3095[var1];
				if(var4 >= 2000) {
					var4 -= 2000;
				}

				long var6 = Class3_Sub13_Sub22.aLongArray3271[var1];
				int var5 = (int)Class3_Sub13_Sub22.aLongArray3271[var1];
				Player var8;
				if(31 == var4) {
					var8 = Class3_Sub13_Sub22.players[var5];
					if(null != var8) {
						++Class164.anInt2059;
						Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var8.anIntArray2767[0], 1, 0, 2, var8.anIntArray2755[0], Class102.player.anIntArray2767[0]);
						Class36.anInt638 = 2;
						Class151_Sub1.anInt2958 = 0;
						Class70.anInt1053 = Class163_Sub1.anInt2993;
						Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(71);
						Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var5);
					}
				}

				if(-47 == ~var4) {
					++Class3_Sub23.anInt2534;
					Class163_Sub2_Sub1.method2224((byte)39, var6, var3, var2);
					Class3_Sub13_Sub1.outgoingBuffer.putOpcode(247);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, Class82.anInt1152 + var3);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var2 + Class131.anInt1716);
					Class3_Sub13_Sub1.outgoingBuffer.putShort(Integer.MAX_VALUE & (int)(var6 >>> 32));
				}

				if(~var4 == -41) {
					Class3_Sub13_Sub1.outgoingBuffer.putOpcode(27);
					Class3_Sub13_Sub1.outgoingBuffer.putShort(Class110.anInt1473);
					++Class136.anInt1776;
					Class3_Sub13_Sub1.outgoingBuffer.putLEInt(var3, (byte)-122);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, var2);
					Class3_Sub13_Sub1.outgoingBuffer.putLEInt(Class3_Sub28_Sub18.anInt3764, (byte)-122);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(Class164.anInt2050);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var5);
					Class3_Sub9.anInt2330 = 0;
					Class151.aClass11_1933 = Class7.getRSInterface((byte)120, var3);
					KeyboardListener.anInt1918 = var2;
					System.out.println(Class110.anInt1473 + ", " + var3 + ", " + var2 + ", " + Class3_Sub28_Sub18.anInt3764 + ", " + Class164.anInt2050 + ", " + var5);
				}

				NPC var11;
				if(-20 == ~var4) {
					var11 = Class3_Sub13_Sub24.npcs[var5];
					if(null != var11) {
						Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var11.anIntArray2767[0], 1, 0, 2, var11.anIntArray2755[0], Class102.player.anIntArray2767[0]);
						++Class27.anInt513;
						Class70.anInt1053 = Class163_Sub1.anInt2993;
						Class36.anInt638 = 2;
						Class151_Sub1.anInt2958 = 0;
						Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(30);
						Class3_Sub13_Sub1.outgoingBuffer.putShort(var5);
					}
				}

				if(17 == var4) {
					var11 = Class3_Sub13_Sub24.npcs[var5];
					if(var11 != null) {
						Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var11.anIntArray2767[0], 1, 0, 2, var11.anIntArray2755[0], Class102.player.anIntArray2767[0]);
						Class70.anInt1053 = Class163_Sub1.anInt2993;
						Class151_Sub1.anInt2958 = 0;
						++Class3_Sub28_Sub10.anInt3627;
						Class36.anInt638 = 2;
						Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(78);
						Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, var5);
					}
				}

				if(var4 == 44) {
					var8 = Class3_Sub13_Sub22.players[var5];
					if(null != var8) {
						++Class3_Sub24_Sub4.anInt3517;
						Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var8.anIntArray2767[0], 1, 0, 2, var8.anIntArray2755[0], Class102.player.anIntArray2767[0]);
						Class70.anInt1053 = Class163_Sub1.anInt2993;
						Class36.anInt638 = 2;
						Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
						Class151_Sub1.anInt2958 = 0;
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(133);
						Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, var5);
					}
				}

				if(-59 == ~var4) {
					Class3_Sub13_Sub1.outgoingBuffer.putOpcode(135);
					Class3_Sub13_Sub1.outgoingBuffer.putShortA(var5, -268435456);
					++Class3_Sub13_Sub21.anInt3255;
					Class3_Sub13_Sub1.outgoingBuffer.putShortA(var2, -268435456);
					Class3_Sub13_Sub1.outgoingBuffer.putIntB(-68, var3);
					Class3_Sub9.anInt2330 = 0;
					Class151.aClass11_1933 = Class7.getRSInterface((byte)117, var3);
					KeyboardListener.anInt1918 = var2;
				}

				if(-43 == ~var4) {
					Class163_Sub2_Sub1.method2224((byte)39, var6, var3, var2);
					Class3_Sub13_Sub1.outgoingBuffer.putOpcode(254);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, var2 + Class131.anInt1716);
					++Class3_Sub13_Sub20.anInt3248;
					Class3_Sub13_Sub1.outgoingBuffer.putShortA((int)(var6 >>> 32) & Integer.MAX_VALUE, -268435456);
					Class3_Sub13_Sub1.outgoingBuffer.putShort(var3 - -Class82.anInt1152);
				}

				if(28 == var4) {
					Class3_Sub13_Sub19.method264((byte)122);
				}

				if(~var4 == -46) {
					var11 = Class3_Sub13_Sub24.npcs[var5];
					if(var11 != null) {
						Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var11.anIntArray2767[0], 1, 0, 2, var11.anIntArray2755[0], Class102.player.anIntArray2767[0]);
						Class70.anInt1053 = Class163_Sub1.anInt2993;
						Class36.anInt638 = 2;
						++Class3_Sub13_Sub16.anInt3205;
						Class151_Sub1.anInt2958 = 0;
						Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(239);
						Class3_Sub13_Sub1.outgoingBuffer.putLEInt(Class54.anInt872, (byte)-123);
						Class3_Sub13_Sub1.outgoingBuffer.putShortA(RSInterface.anInt278, -268435456);
						Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var5);
					}
				}

				boolean var14;
				if(18 == var4) {
					if(Class158.anInt2014 == 1) {
						Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var2, 1, 0, 2, var3, Class102.player.anIntArray2767[0]);
					} else {
						var14 = Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 0, false, 0, var0 ^ 2599, var2, 0, 0, 2, var3, Class102.player.anIntArray2767[0]);
						if(!var14) {
							Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var2, 1, 0, 2, var3, Class102.player.anIntArray2767[0]);
						}
					}

					++Class3_Sub28_Sub9.anInt3615;
					Class70.anInt1053 = Class163_Sub1.anInt2993;
					Class151_Sub1.anInt2958 = 0;
					Class36.anInt638 = 2;
					Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
					Class3_Sub13_Sub1.outgoingBuffer.putOpcode(66);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, Class131.anInt1716 + var2);
					Class3_Sub13_Sub1.outgoingBuffer.putShort(var5);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var3 - -Class82.anInt1152);
				}

				if(var4 == 1001) {
					++Class145.anInt1896;
					Class163_Sub2_Sub1.method2224((byte)39, var6, var3, var2);
					Class3_Sub13_Sub1.outgoingBuffer.putOpcode(170);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(Integer.MAX_VALUE & (int)(var6 >>> 32));
					Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var2 - -Class131.anInt1716);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var3 - -Class82.anInt1152);
				}

				if(~var4 == -1003) {
					Class36.anInt638 = 2;
					Class70.anInt1053 = Class163_Sub1.anInt2993;
					Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
					++Class130.anInt1701;
					Class151_Sub1.anInt2958 = 0;
					Class3_Sub13_Sub1.outgoingBuffer.putOpcode(92);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var5);
				}

				RSInterface var13;
				if(~var4 == -1007) {
					var13 = Class7.getRSInterface((byte)119, var3);
					if(null != var13 && ~var13.itemIds[var2] <= -100001) {
						addChatMessage(Class3_Sub28_Sub14.aClass94_3672, 0, RenderAnimationDefinition.method903(new RSString[]{Class72.method1298((byte)9, var13.itemIds[var2]), Class3_Sub28_Sub19.aClass94_3777, Class38.getItemDefinition(var5, (byte)125).name}, (byte)-84), -1);
					} else {
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(92);
						++Class130.anInt1701;
						Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var5);
					}

					Class3_Sub9.anInt2330 = 0;
					Class151.aClass11_1933 = Class7.getRSInterface((byte)123, var3);
					KeyboardListener.anInt1918 = var2;
				}

				if(var4 == 60) {
					if(-1 != ~var5) {
						if(-2 == ~var5) {
							if(0 < Class3_Sub13_Sub26.rights && ObjectDefinition.aBooleanArray1490[82] && ObjectDefinition.aBooleanArray1490[81]) {
								Class30.method979(Class131.anInt1716 + var2, Class82.anInt1152 + var3, WorldListCountry.localPlane, (byte)-4);
							} else if(Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 0, true, 0, 2, var2, 0, 0, 1, var3, Class102.player.anIntArray2767[0])) {
								Class3_Sub13_Sub1.outgoingBuffer.putByte((byte)-32, Class1.anInt56);
								Class3_Sub13_Sub1.outgoingBuffer.putByte((byte)-108, Class58.anInt916);
								Class3_Sub13_Sub1.outgoingBuffer.putShort(GraphicDefinition.CAMERA_DIRECTION);
								Class3_Sub13_Sub1.outgoingBuffer.putByte((byte)-12, 57);
								Class3_Sub13_Sub1.outgoingBuffer.putByte((byte)-118, Class3_Sub13_Sub8.anInt3102);
								Class3_Sub13_Sub1.outgoingBuffer.putByte((byte)-116, Class164_Sub2.anInt3020);
								Class3_Sub13_Sub1.outgoingBuffer.putByte((byte)-57, 89);
								Class3_Sub13_Sub1.outgoingBuffer.putShort(Class102.player.anInt2819);
								Class3_Sub13_Sub1.outgoingBuffer.putShort(Class102.player.anInt2829);
								Class3_Sub13_Sub1.outgoingBuffer.putByte((byte)-103, Class129.anInt1692);
								Class3_Sub13_Sub1.outgoingBuffer.putByte((byte)-59, 63);
							}
						}
					} else {
						Class3_Sub28_Sub10.method589(WorldListCountry.localPlane, var2, var3);
					}
				}

				if(1007 == var4) {
					Class151_Sub1.anInt2958 = 0;
					Class36.anInt638 = 2;
					Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
					Class70.anInt1053 = Class163_Sub1.anInt2993;
					var11 = Class3_Sub13_Sub24.npcs[var5];
					if(var11 != null) {
						NPCDefinition var9 = var11.definition;
						if(var9.childNPCs != null) {
							var9 = var9.method1471((byte)80);
						}

						if(null != var9) {
							++Class3_Sub25.anInt2549;
							Class3_Sub13_Sub1.outgoingBuffer.putOpcode(72);
							Class3_Sub13_Sub1.outgoingBuffer.putShort(var9.npcId);
						}
					}
				}

				if(~var4 == -48) {
					if (MouseWheel.shiftDown) {
					
							Class3_Sub13_Sub1.outgoingBuffer.putOpcode(135);
							Class3_Sub13_Sub1.outgoingBuffer.putShortA(var5, -268435456);
							++Class3_Sub13_Sub21.anInt3255;
							Class3_Sub13_Sub1.outgoingBuffer.putShortA(var2, -268435456);
							Class3_Sub13_Sub1.outgoingBuffer.putIntB(-68, var3);
							Class3_Sub9.anInt2330 = 0;
							Class151.aClass11_1933 = Class7.getRSInterface((byte)117, var3);
							KeyboardListener.anInt1918 = var2;
						
						
					} else {
					Class3_Sub13_Sub1.outgoingBuffer.putOpcode(156);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var2);
					Class3_Sub13_Sub1.outgoingBuffer.putShortA(var5, -268435456);
					Class3_Sub13_Sub1.outgoingBuffer.putLEInt(var3, (byte)-120);
					Class3_Sub9.anInt2330 = 0;
					Class151.aClass11_1933 = Class7.getRSInterface((byte)109, var3);
					KeyboardListener.anInt1918 = var2;
					}
				}

				if(-4 == ~var4) {
					Class3_Sub13_Sub1.outgoingBuffer.putOpcode(253);
					++Class29.anInt555;
					Class3_Sub13_Sub1.outgoingBuffer.putLEInt(Class54.anInt872, (byte)-126);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var2);
					Class3_Sub13_Sub1.outgoingBuffer.putLEInt(var3, (byte)-124);
					Class3_Sub13_Sub1.outgoingBuffer.putShortA(var5, -268435456);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShort(var0 ^ -2598, RSInterface.anInt278);
					Class3_Sub9.anInt2330 = 0;
					Class151.aClass11_1933 = Class7.getRSInterface((byte)120, var3);
					KeyboardListener.anInt1918 = var2;
				}

				if(~var4 == -11) {
					var8 = Class3_Sub13_Sub22.players[var5];
					if(var8 != null) {
						++IOHandler.anInt1240;
						Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var8.anIntArray2767[0], 1, 0, 2, var8.anIntArray2755[0], Class102.player.anIntArray2767[0]);
						Class36.anInt638 = 2;
						Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
						Class70.anInt1053 = Class163_Sub1.anInt2993;
						Class151_Sub1.anInt2958 = 0;
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(4);
						Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, var5);
					}
				}

				if(41 == var4 && Class3_Sub13_Sub7.aClass11_3087 == null) {
					Class2.method78(var2, false, var3);
					Class3_Sub13_Sub7.aClass11_3087 = Class3_Sub28_Sub16.method638((byte)-19, var3, var2);
					Class20.method909(var0 + -2470, Class3_Sub13_Sub7.aClass11_3087);
				}

				if(49 == var4) {
					++Class3_Sub28_Sub10.anInt3630;
					Class163_Sub2_Sub1.method2224((byte)39, var6, var3, var2);
					Class3_Sub13_Sub1.outgoingBuffer.putOpcode(84);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(Integer.MAX_VALUE & (int)(var6 >>> 32));
					Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(Class82.anInt1152 + var3);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShort(var0 + -2598, var2 - -Class131.anInt1716);
				}

				if(-24 == ~var4) {
					++Class144.anInt1886;
					Class3_Sub13_Sub1.outgoingBuffer.putOpcode(206);
					Class3_Sub13_Sub1.outgoingBuffer.putShortA(var5, -268435456);//itemId
					Class3_Sub13_Sub1.outgoingBuffer.putLEShort(var0 ^ -2598, var2);//data
					Class3_Sub13_Sub1.outgoingBuffer.putLEInt(var3, (byte)-127);//slot
					Class3_Sub9.anInt2330 = 0;
					Class151.aClass11_1933 = Class7.getRSInterface((byte)115, var3);
					KeyboardListener.anInt1918 = var2;
				}

				if(-15 == ~var4 && Class163_Sub2_Sub1.method2224((byte)39, var6, var3, var2)) {
					++Class3_Sub28_Sub10_Sub2.anInt4065;
					Class3_Sub13_Sub1.outgoingBuffer.putOpcode(134);
					Class3_Sub13_Sub1.outgoingBuffer.putShortA(Class131.anInt1716 + var2, var0 ^ -268432859);
					Class3_Sub13_Sub1.outgoingBuffer.putShort(Class164.anInt2050);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, var3 - -Class82.anInt1152);
					Class3_Sub13_Sub1.outgoingBuffer.putShort(Class110.anInt1473);
					Class3_Sub13_Sub1.outgoingBuffer.putIntB(var0 ^ -2588, Class3_Sub28_Sub18.anInt3764);
					Class3_Sub13_Sub1.outgoingBuffer.putShortA((int)(var6 >>> 32) & Integer.MAX_VALUE, -268435456);
				}

				if(var4 == 37) {
					var8 = Class3_Sub13_Sub22.players[var5];
					if(var8 != null) {
						Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var8.anIntArray2767[0], 1, 0, 2, var8.anIntArray2755[0], Class102.player.anIntArray2767[0]);
						Class151_Sub1.anInt2958 = 0;
						++Class20.anInt437;
						Class36.anInt638 = 2;
						Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
						Class70.anInt1053 = Class163_Sub1.anInt2993;
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(114);
						Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var5);
					}
				}

				if(var4 == 9 || 1003 == var4) {
					OutputStream_Sub1.method66(Class163_Sub2_Sub1.aClass94Array4016[var1], var2, var5, (byte)-19, var3);
				}

				if(-6 == ~var4) {
					if (MouseWheel.shiftDown) {
							Class3_Sub13_Sub1.outgoingBuffer.putOpcode(135);
							Class3_Sub13_Sub1.outgoingBuffer.putShortA(var5, -268435456);
							++Class3_Sub13_Sub21.anInt3255;
							Class3_Sub13_Sub1.outgoingBuffer.putShortA(var2, -268435456);
							Class3_Sub13_Sub1.outgoingBuffer.putIntB(-68, var3);
							Class3_Sub9.anInt2330 = 0;
							Class151.aClass11_1933 = Class7.getRSInterface((byte)117, var3);
							KeyboardListener.anInt1918 = var2;
						
					} else {
					Class3_Sub13_Sub1.outgoingBuffer.putOpcode(55);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, var5);
					Class3_Sub13_Sub1.outgoingBuffer.putShortA(var2, -268435456);
					++Class3_Sub13_Sub6.anInt3084;
					Class3_Sub13_Sub1.outgoingBuffer.putIntA(var3);
					Class3_Sub9.anInt2330 = 0;
					Class151.aClass11_1933 = Class7.getRSInterface((byte)116, var3);
					KeyboardListener.anInt1918 = var2;
				
					}
				}

				if(-22 == ~var4) {
					if(~Class158.anInt2014 != -2) {
						var14 = Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 0, false, 0, var0 + -2595, var2, 0, 0, 2, var3, Class102.player.anIntArray2767[0]);
						if(!var14) {
							Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, var0 + -2595, var2, 1, 0, 2, var3, Class102.player.anIntArray2767[0]);
						}
					} else {
						Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, var0 + -2595, var2, 1, 0, 2, var3, Class102.player.anIntArray2767[0]);
					}

					Class36.anInt638 = 2;
					Class70.anInt1053 = Class163_Sub1.anInt2993;
					Class151_Sub1.anInt2958 = 0;
					Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
					Class3_Sub13_Sub1.outgoingBuffer.putOpcode(228);
					++Class167.anInt2085;
					Class3_Sub13_Sub1.outgoingBuffer.putShort(var5);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShort(var0 ^ -2598, Class131.anInt1716 + var2);
					Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(Class82.anInt1152 + var3);
				}

				if(var4 == 4) {
					var11 = Class3_Sub13_Sub24.npcs[var5];
					if(var11 != null) {
						Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var11.anIntArray2767[0], 1, 0, 2, var11.anIntArray2755[0], Class102.player.anIntArray2767[0]);
						Class36.anInt638 = 2;
						Class151_Sub1.anInt2958 = 0;
						Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
						Class70.anInt1053 = Class163_Sub1.anInt2993;
						++Class3_Sub13_Sub21.anInt3259;
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(148);
						Class3_Sub13_Sub1.outgoingBuffer.putShortA(var5, var0 + -268438053);
					}
				}

				if(32 == var4) {
					var13 = Class3_Sub28_Sub16.method638((byte)-19, var3, var2);
					if(null != var13) {
						Class25.method958((byte)-126);
						Class3_Sub1 var16 = Client.method44(var13);
						Class145.method2074(var3, var2, var16.method101(-120), var16.anInt2202, var13.anInt266, -120, var13.anInt238);
						Class164_Sub1.anInt3012 = 0;
						Class3_Sub28_Sub9.aClass94_3621 = Class53.method1174(var13, (byte)-94);
						if(Class3_Sub28_Sub9.aClass94_3621 == null) {
							Class3_Sub28_Sub9.aClass94_3621 = KeyboardListener.aClass94_1915;
						}

						if(var13.usingScripts) {
							Class40.aClass94_676 = RenderAnimationDefinition.method903(new RSString[]{var13.aClass94_277, Class3_Sub26.CONTEXT_MENU_COLOR }, (byte)-83);
						} else {
							Class40.aClass94_676 = RenderAnimationDefinition.method903(new RSString[]{Class19.aClass94_431, var13.aClass94_243, Class3_Sub26.CONTEXT_MENU_COLOR }, (byte)-94);
						}
					}

				} else {
					if(29 == var4) {
						var8 = Class3_Sub13_Sub22.players[var5];
						if(null != var8) {
							++KeyboardListener.anInt1910;
							Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var8.anIntArray2767[0], 1, 0, 2, var8.anIntArray2755[0], Class102.player.anIntArray2767[0]);
							Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
							Class151_Sub1.anInt2958 = 0;
							Class36.anInt638 = 2;
							Class70.anInt1053 = Class163_Sub1.anInt2993;
							Class3_Sub13_Sub1.outgoingBuffer.putOpcode(180);
							Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var5);
						}
					}

					if(var4 == 35) {
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(161);
						Class3_Sub13_Sub1.outgoingBuffer.putLEInt(var3, (byte)-119);
						Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var5);
						Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var2);
						++Class3_Sub13_Sub39.anInt3459;
						Class3_Sub9.anInt2330 = 0;
						Class151.aClass11_1933 = Class7.getRSInterface((byte)124, var3);
						KeyboardListener.anInt1918 = var2;
					}

					if(15 == var4) {
						var8 = Class3_Sub13_Sub22.players[var5];
						if(var8 != null) {
							Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var8.anIntArray2767[0], 1, 0, 2, var8.anIntArray2755[0], Class102.player.anIntArray2767[0]);
							Class151_Sub1.anInt2958 = 0;
							Class36.anInt638 = 2;
							Class70.anInt1053 = Class163_Sub1.anInt2993;
							Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
							Class3_Sub13_Sub1.outgoingBuffer.putOpcode(195);
							++Class3_Sub28_Sub2.anInt3542;
							Class3_Sub13_Sub1.outgoingBuffer.putShortA(RSInterface.anInt278, -268435456);
							Class3_Sub13_Sub1.outgoingBuffer.putLEInt(Class54.anInt872, (byte)-124);
							Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var5);
						}
					}

					if(34 == var4) {
						if(Class158.anInt2014 != 1) {
							var14 = Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 0, false, 0, var0 ^ 2599, var2, 0, 0, 2, var3, Class102.player.anIntArray2767[0]);
							if(!var14) {
								Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var2, 1, 0, 2, var3, Class102.player.anIntArray2767[0]);
							}
						} else {
							Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var2, 1, 0, 2, var3, Class102.player.anIntArray2767[0]);
						}

						Class70.anInt1053 = Class163_Sub1.anInt2993;
						Class36.anInt638 = 2;
						++Class3_Sub13_Sub22.anInt3277;
						Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
						Class151_Sub1.anInt2958 = 0;
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(109);
						Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, var3 - -Class82.anInt1152);
						Class3_Sub13_Sub1.outgoingBuffer.putShort(var2 + Class131.anInt1716);
						Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var5);
					}

					if(-26 == ~var4) {
						++Class17.anInt411;
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(81);
						Class3_Sub13_Sub1.outgoingBuffer.putShortA(var2, -268435456);
						Class3_Sub13_Sub1.outgoingBuffer.putShort(var5);
						Class3_Sub13_Sub1.outgoingBuffer.putIntA(var3);
						Class3_Sub9.anInt2330 = 0;
						Class151.aClass11_1933 = Class7.getRSInterface((byte)126, var3);
						KeyboardListener.anInt1918 = var2;
					}

					if(~var4 == -3) {
						var11 = Class3_Sub13_Sub24.npcs[var5];
						if(var11 != null) {
							Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, var0 + -2595, var11.anIntArray2767[0], 1, 0, 2, var11.anIntArray2755[0], Class102.player.anIntArray2767[0]);
							Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
							Class36.anInt638 = 2;
							Class70.anInt1053 = Class163_Sub1.anInt2993;
							++Class3_Sub1.anInt2204;
							Class151_Sub1.anInt2958 = 0;
							Class3_Sub13_Sub1.outgoingBuffer.putOpcode(218);
							Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, var5);
						}
					}

					int var12;
					if(-52 == ~var4) {
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(10);
						++Class3_Sub28_Sub15.anInt3681;
						Class3_Sub13_Sub1.outgoingBuffer.putInt(var0 + -2725, var3);
						var13 = Class7.getRSInterface((byte)117, var3);
						if(var13.childDataBuffers != null && -6 == ~var13.childDataBuffers[0][0]) {
							var12 = var13.childDataBuffers[0][1];
							if(Class163_Sub1.anIntArray2985[var12] != var13.anIntArray307[0]) {
								Class163_Sub1.anIntArray2985[var12] = var13.anIntArray307[0];
								Class46.method1087(98, var12);
							}
						}
					}

					if(-27 == ~var4) {
						var11 = Class3_Sub13_Sub24.npcs[var5];
						if(var11 != null) {
							++Class3_Sub13_Sub37.anInt3439;
							Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var11.anIntArray2767[0], 1, 0, 2, var11.anIntArray2755[0], Class102.player.anIntArray2767[0]);
							Class36.anInt638 = 2;
							Class151_Sub1.anInt2958 = 0;
							Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
							Class70.anInt1053 = Class163_Sub1.anInt2993;
							Class3_Sub13_Sub1.outgoingBuffer.putOpcode(115);//Item on NPC
							Class3_Sub13_Sub1.outgoingBuffer.putIntB(var0 ^ -2667, Class3_Sub28_Sub18.anInt3764);
							Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, Class110.anInt1473);
							Class3_Sub13_Sub1.outgoingBuffer.putLEShort(var0 ^ -2598, var5);
							Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(Class164.anInt2050);
//							System.out.println(Class3_Sub28_Sub18.anInt3764 + ", " + Class110.anInt1473 + ", " + var5 + ", " + Class164.anInt2050);
						}
					}

					if(59 == var4) {
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(10);
						++Class3_Sub28_Sub15.anInt3681;
						Class3_Sub13_Sub1.outgoingBuffer.putInt(-121, var3);
						var13 = Class7.getRSInterface((byte)122, var3);
						if(var13.childDataBuffers != null && -6 == ~var13.childDataBuffers[0][0]) {
							var12 = var13.childDataBuffers[0][1];
							Class163_Sub1.anIntArray2985[var12] = -Class163_Sub1.anIntArray2985[var12] + 1;
							Class46.method1087(68, var12);
						}
					}

					if(~var4 == -34) {
						var14 = Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 0, false, 0, 2, var2, 0, 0, 2, var3, Class102.player.anIntArray2767[0]);
						++Class61.anInt938;
						if(!var14) {
							Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, var0 + -2595, var2, 1, 0, 2, var3, Class102.player.anIntArray2767[0]);
						}

						Class70.anInt1053 = Class163_Sub1.anInt2993;
						Class151_Sub1.anInt2958 = 0;
						Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
						Class36.anInt638 = 2;
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(101);
						Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var2 - -Class131.anInt1716);
						Class3_Sub13_Sub1.outgoingBuffer.putLEShort(var0 ^ -2598, Class110.anInt1473);
						Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, Class164.anInt2050);
						Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, var5);
						Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(Class82.anInt1152 + var3);
						Class3_Sub13_Sub1.outgoingBuffer.putIntB(-118, Class3_Sub28_Sub18.anInt3764);
					}

					if(-1005 == ~var4) {
						Class151_Sub1.anInt2958 = 0;
						Class70.anInt1053 = Class163_Sub1.anInt2993;
						Class36.anInt638 = 2;
						Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(94);
						++Class140_Sub4.anInt2770;
						Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var5);
					}

					if(11 == var4) {
						if(-1 != ~var5) {
							if(1 == var5) {
								Class3_Sub13_Sub1.outgoingBuffer.putOpcode(131);
								++Class66.anInt994;
								Class3_Sub13_Sub1.outgoingBuffer.putIntB(-57, Class54.anInt872);
								Class3_Sub13_Sub1.outgoingBuffer.putShortA(Class131.anInt1716 + var2, var0 + -268438053);
								Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(RSInterface.anInt278);
								Class3_Sub13_Sub1.outgoingBuffer.putShortA(var3 + Class82.anInt1152, -268435456);
							}
						} else {
							CS2Script.anInt2440 = 1;
							Class3_Sub28_Sub10.method589(WorldListCountry.localPlane, var2, var3);
						}
					}

					if(8 == var4) {
						var13 = Class7.getRSInterface((byte)109, var3);
						boolean var15 = true;
						if(0 < var13.anInt189) {
							var15 = Class3_Sub28_Sub19.method715(205, var13);
						}

						if(var15) {
							++Class3_Sub28_Sub15.anInt3681;
							Class3_Sub13_Sub1.outgoingBuffer.putOpcode(10);
							Class3_Sub13_Sub1.outgoingBuffer.putInt(-120, var3);
						}
					}

					if(~var4 == -2) {
						var8 = Class3_Sub13_Sub22.players[var5];
						if(var8 != null) {
							++ISAACCipher.anInt969;
							Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var8.anIntArray2767[0], 1, 0, 2, var8.anIntArray2755[0], Class102.player.anIntArray2767[0]);
							Class151_Sub1.anInt2958 = 0;
							Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
							Class36.anInt638 = 2;
							Class70.anInt1053 = Class163_Sub1.anInt2993;
							Class3_Sub13_Sub1.outgoingBuffer.putOpcode(248);
							Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var5);
							Class3_Sub13_Sub1.outgoingBuffer.putShort(Class164.anInt2050);
							Class3_Sub13_Sub1.outgoingBuffer.putShort(Class110.anInt1473);
							Class3_Sub13_Sub1.outgoingBuffer.putIntB(-110, Class3_Sub28_Sub18.anInt3764);
						}
					}

					if(var4 == 7) {
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(85);
						++Class3_Sub13_Sub12.anInt3144;
						Class3_Sub13_Sub1.outgoingBuffer.putIntA(var3);
						Class3_Sub13_Sub1.outgoingBuffer.putShort(var2);
						Class3_Sub13_Sub1.outgoingBuffer.putShortA(var5, -268435456);
						Class3_Sub9.anInt2330 = 0;
						Class151.aClass11_1933 = Class7.getRSInterface((byte)125, var3);
						KeyboardListener.anInt1918 = var2;
					}

					if(~var4 == -25) {
						if(-2 != ~Class158.anInt2014) {
							var14 = Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 0, false, 0, 2, var2, 0, 0, 2, var3, Class102.player.anIntArray2767[0]);
							if(!var14) {
								Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var2, 1, 0, 2, var3, Class102.player.anIntArray2767[0]);
							}
						} else {
							Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, var0 + -2595, var2, 1, 0, 2, var3, Class102.player.anIntArray2767[0]);
						}

						Class36.anInt638 = 2;
						++Class70.anInt1060;
						Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
						Class70.anInt1053 = Class163_Sub1.anInt2993;
						Class151_Sub1.anInt2958 = 0;
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(48);
						Class3_Sub13_Sub1.outgoingBuffer.putShortA(var2 - -Class131.anInt1716, -268435456);
						Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var5);
						Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, Class82.anInt1152 + var3);
					}

					if(var4 == 38 && Class163_Sub2_Sub1.method2224((byte)39, var6, var3, var2)) {
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(233);
						Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var3 + Class82.anInt1152);
						++Class164_Sub1.anInt3017;
						Class3_Sub13_Sub1.outgoingBuffer.putShortA(Class131.anInt1716 + var2, -268435456);
						Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(RSInterface.anInt278);
						Class3_Sub13_Sub1.outgoingBuffer.putIntA(Class54.anInt872);
						Class3_Sub13_Sub1.outgoingBuffer.putShortA((int)(var6 >>> 32) & Integer.MAX_VALUE, var0 ^ -268432859);
					}

					if(~var4 == -14) {
						Class3_Sub13_Sub1.outgoingBuffer.putOpcode(6);
						Class3_Sub13_Sub1.outgoingBuffer.putInt(var0 + -2720, var3);
						Class3_Sub13_Sub1.outgoingBuffer.putShortA(var2, -268435456);
						++Class168.anInt2088;
						Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, var5);
						Class3_Sub9.anInt2330 = 0;
						Class151.aClass11_1933 = Class7.getRSInterface((byte)115, var3);
						KeyboardListener.anInt1918 = var2;
					}

					if(57 == var4) {
						var8 = Class3_Sub13_Sub22.players[var5];
						if(null != var8) {
							Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var8.anIntArray2767[0], 1, 0, 2, var8.anIntArray2755[0], Class102.player.anIntArray2767[0]);
							Class36.anInt638 = 2;
							Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
							++Class41.anInt685;
							Class70.anInt1053 = Class163_Sub1.anInt2993;
							Class151_Sub1.anInt2958 = 0;
							Class3_Sub13_Sub1.outgoingBuffer.putOpcode(175);
							Class3_Sub13_Sub1.outgoingBuffer.putShortA(var5, -268435456);
						}
					}

					if(var4 != 22) {
						if(-51 == ~var4) {
							++AnimationDefinition.anInt1858;
							Class163_Sub2_Sub1.method2224((byte)39, var6, var3, var2);
							Class3_Sub13_Sub1.outgoingBuffer.putOpcode(194);
							Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var3 + Class82.anInt1152);
							Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, Class131.anInt1716 + var2);
							Class3_Sub13_Sub1.outgoingBuffer.putShort((int)(var6 >>> 32) & Integer.MAX_VALUE);
						}

						if(-49 == ~var4) {
							++Class126.anInt1677;
							Class3_Sub13_Sub1.outgoingBuffer.putOpcode(154);
							Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, var2);
							Class3_Sub13_Sub1.outgoingBuffer.putIntA(var3);
							Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var5);
							Class3_Sub9.anInt2330 = 0;
							Class151.aClass11_1933 = Class7.getRSInterface((byte)119, var3);
							KeyboardListener.anInt1918 = var2;
						}

						if(~var4 == -31) {
							var8 = Class3_Sub13_Sub22.players[var5];
							if(null != var8) {
								++ItemDefinition.anInt759;
								Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var8.anIntArray2767[0], 1, 0, 2, var8.anIntArray2755[0], Class102.player.anIntArray2767[0]);
								Class151_Sub1.anInt2958 = 0;
								Class70.anInt1053 = Class163_Sub1.anInt2993;
								Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
								Class36.anInt638 = 2;
								Class3_Sub13_Sub1.outgoingBuffer.putOpcode(68);
								Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var5);
							}
						}

						if(var4 == 43) {
							++Class3_Sub13_Sub37.anInt3442;
							Class3_Sub13_Sub1.outgoingBuffer.putOpcode(153);
							Class3_Sub13_Sub1.outgoingBuffer.putLEInt(var3, (byte)-124);
							Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, var2);
							Class3_Sub13_Sub1.outgoingBuffer.putLEShort(var0 + -2598, var5);
							Class3_Sub9.anInt2330 = 0;
							Class151.aClass11_1933 = Class7.getRSInterface((byte)112, var3);
							KeyboardListener.anInt1918 = var2;
						}

						if(-40 == ~var4) {
							++Class144.anInt1883;
							var14 = Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 0, false, 0, 2, var2, 0, 0, 2, var3, Class102.player.anIntArray2767[0]);
							if(!var14) {
								Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var2, 1, 0, 2, var3, Class102.player.anIntArray2767[0]);
							}

							Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
							Class70.anInt1053 = Class163_Sub1.anInt2993;
							Class36.anInt638 = 2;
							Class151_Sub1.anInt2958 = 0;
							Class3_Sub13_Sub1.outgoingBuffer.putOpcode(73);
							Class3_Sub13_Sub1.outgoingBuffer.putIntA(Class54.anInt872);
							Class3_Sub13_Sub1.outgoingBuffer.putShort(Class82.anInt1152 + var3);
							Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var5);
							Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var2 + Class131.anInt1716);
							Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, RSInterface.anInt278);
						}

						if(var0 == 2597) {
							if(~var4 == -13) {
								++PacketParser.anInt79;
								Class3_Sub13_Sub1.outgoingBuffer.putOpcode(82);
								Class3_Sub13_Sub1.outgoingBuffer.putShort(RSInterface.anInt278);
								Class3_Sub13_Sub1.outgoingBuffer.putIntA(var3);
								Class3_Sub13_Sub1.outgoingBuffer.putInt(-124, Class54.anInt872);
								Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var2);
							}

							if(var4 == 36) {
								if(~var5 != -1) {
									if(-1 > ~Class3_Sub13_Sub26.rights && ObjectDefinition.aBooleanArray1490[82] && ObjectDefinition.aBooleanArray1490[81]) {
										Class30.method979(var2 + Class131.anInt1716, Class82.anInt1152 - -var3, WorldListCountry.localPlane, (byte)-4);
									} else {
										++Class3_Sub19.anInt2479;
										Class3_Sub13_Sub1.outgoingBuffer.putOpcode(179);
										Class3_Sub13_Sub1.outgoingBuffer.putShort(var3 + Class82.anInt1152);
										Class3_Sub13_Sub1.outgoingBuffer.putShort(var2 + Class131.anInt1716);
									}
								} else {
									ObjectDefinition.anInt1521 = 1;
									Class3_Sub28_Sub10.method589(WorldListCountry.localPlane, var2, var3);
								}
							}

							if(6 == var4) {
								var8 = Class3_Sub13_Sub22.players[var5];
								if(var8 != null) {
									Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var8.anIntArray2767[0], 1, 0, 2, var8.anIntArray2755[0], Class102.player.anIntArray2767[0]);
									Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
									++Class3_Sub28_Sub11.anInt3640;
									Class151_Sub1.anInt2958 = 0;
									Class36.anInt638 = 2;
									Class70.anInt1053 = Class163_Sub1.anInt2993;
									Class3_Sub13_Sub1.outgoingBuffer.putOpcode(106);
									Class3_Sub13_Sub1.outgoingBuffer.putShort(var5);
								}
							}

							if(var4 == 20) {
								++Class75_Sub2.anInt2640;
								if(1 == Class158.anInt2014) {
									Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var2, 1, 0, 2, var3, Class102.player.anIntArray2767[0]);
								} else {
									var14 = Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 0, false, 0, 2, var2, 0, 0, 2, var3, Class102.player.anIntArray2767[0]);
									if(!var14) {
										Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var2, 1, 0, 2, var3, Class102.player.anIntArray2767[0]);
									}
								}

								Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
								Class151_Sub1.anInt2958 = 0;
								Class70.anInt1053 = Class163_Sub1.anInt2993;
								Class36.anInt638 = 2;
								Class3_Sub13_Sub1.outgoingBuffer.putOpcode(33);
								Class3_Sub13_Sub1.outgoingBuffer.putShort(var5);
								Class3_Sub13_Sub1.outgoingBuffer.putShort(Class131.anInt1716 + var2);
								Class3_Sub13_Sub1.outgoingBuffer.putLEShort(-1, Class82.anInt1152 + var3);
							}

							if(var4 == 16) {
								var11 = Class3_Sub13_Sub24.npcs[var5];
								if(null != var11) {
									++Class3_Sub28_Sub14.anInt3677;
									Class3_Sub28_Sub9.method582(Class102.player.anIntArray2755[0], 0, 1, false, 0, 2, var11.anIntArray2767[0], 1, 0, 2, var11.anIntArray2755[0], Class102.player.anIntArray2767[0]);
									Class70.anInt1053 = Class163_Sub1.anInt2993;
									Class151_Sub1.anInt2958 = 0;
									Class3_Sub28_Sub10_Sub1.anInt4062 = Class38_Sub1.anInt2614;
									Class36.anInt638 = 2;
									Class3_Sub13_Sub1.outgoingBuffer.putOpcode(3);
									Class3_Sub13_Sub1.outgoingBuffer.putLEShortA(var5);
								}
							}

							if(~Class164_Sub1.anInt3012 != -1) {
								Class164_Sub1.anInt3012 = 0;
								Class20.method909(120, Class7.getRSInterface((byte)121, Class3_Sub28_Sub18.anInt3764));
							}

							if(GameObject.aBoolean1837) {
								Class25.method958((byte)-36);
							}

							if(Class151.aClass11_1933 != null && -1 == ~Class3_Sub9.anInt2330) {
								Class20.method909(-106, Class151.aClass11_1933);
							}

						}
					} else {

						if (MouseWheel.shiftDown) {
							Class3_Sub13_Sub1.outgoingBuffer.putOpcode(135);
							Class3_Sub13_Sub1.outgoingBuffer.putShortA(var5, -268435456);
							++Class3_Sub13_Sub21.anInt3255;
							Class3_Sub13_Sub1.outgoingBuffer.putShortA(var2, -268435456);
							Class3_Sub13_Sub1.outgoingBuffer.putIntB(-68, var3);
							Class3_Sub9.anInt2330 = 0;
							Class151.aClass11_1933 = Class7.getRSInterface((byte)117, var3);
							KeyboardListener.anInt1918 = var2;

						} else {

							Class25.method958((byte)-86);
							var13 = Class7.getRSInterface((byte)123, var3);
							Class3_Sub28_Sub18.anInt3764 = var3;
							Class110.anInt1473 = var2;
							Class164_Sub1.anInt3012 = 1;
							Class164.anInt2050 = var5;
							Class20.method909(var0 ^ -2612, var13);
							RenderAnimationDefinition.aClass94_378 = RenderAnimationDefinition.method903(new RSString[]{
									Class3_Sub13_Sub2.aClass94_3042, Class38.getItemDefinition(var5, (byte)109).name, Class3_Sub26.CONTEXT_MENU_COLOR
							}, (byte)-113);

						}
					}
				}
			}
		} catch (RuntimeException var10) {
			throw Class44.method1067(var10, "i.E(" + var0 + ',' + var1 + ')');
		}
	}

	final void setBitAccess(byte var1) {
		try {
			this.anInt3806 = this.index * 8;
			int var2 = -12 % ((-32 - var1) / 54);
		} catch (RuntimeException var3) {
			throw Class44.method1067(var3, "i.R(" + var1 + ')');
		}
	}

	public static void method808(int var0) {
		try {
			aClass94_3807 = null;
			if(var0 != 1) {
				anIntArray3804 = (int[])null;
			}

			anIntArray3805 = null;
			anIntArray3804 = null;
			aClass94Array3802 = null;
		} catch (RuntimeException var2) {
			throw Class44.method1067(var2, "i.V(" + var0 + ')');
		}
	}

	Class3_Sub30_Sub1(int var1) {
		super(var1);
	}

	static final int method809(int var0, int var1, int var2, int var3, int var4) {
		try {
			if(Class3_Sub13_Sub32.aBoolean3387) {
				var0 = 1000000;
				Class3_Sub13_Sub32.aBoolean3387 = false;
			}

			Class86 var5 = Class115.aClass86ArrayArray1581[var3][var1];
			float var7 = ((float)var2 * 0.1F + 0.7F) * var5.aFloat1187;
			float var8 = var5.aFloat1190;
			int var6 = var5.anInt1177;
			int var11 = var5.anInt1184;
			int var10 = var5.anInt1175;
			if(!Class38.aBoolean661) {
				var11 = 0;
			}

			float var9 = var5.aFloat1189;
			if(var6 != Class60.anInt932 || Class3_Sub17.aFloat2457 != var7 || Class3_Sub13_Sub2.aFloat3044 != var8 || var9 != RSInterface.aFloat246 || Class96.anInt1345 != var10 || Class132.anInt1736 != var11) {
				Class3_Sub17.aFloat2457 = var7;
				Class3_Sub13_Sub36.aFloat3435 = Class3_Sub13_Sub36.aFloat3424;
				Class3_Sub13_Sub8.aFloat3105 = Class30.aFloat578;
				Class60.anInt932 = var6;
				Class155.anInt1971 = Class3_Sub28_Sub12.anInt3652;
				Class100.anInt1407 = Class41.anInt689;
				RSInterface.aFloat246 = var9;
				Class3.anInt72 = 0;
				Class3_Sub13_Sub23_Sub1.anInt4037 = CacheIndex.anInt1950;
				Class132.anInt1736 = var11;
				Class3_Sub13_Sub2.aFloat3044 = var8;
				Class96.anInt1345 = var10;
				Class110.aFloat1475 = Class12.aFloat319;
			}

			if(65536 > Class3.anInt72) {
				Class3.anInt72 += 250 * var0;
				if(-65537 >= ~Class3.anInt72) {
					Class3.anInt72 = 65536;
				}

				float var15 = (float)Class3.anInt72 / 65536.0F;
				int var13 = Class3.anInt72 >> 8;
				int var12 = -Class3.anInt72 + 65536 >> 8;
				Class3_Sub28_Sub12.anInt3652 = (-16711936 & var13 * (Class96.anInt1345 & 16711935) + (16711935 & Class155.anInt1971) * var12) + (16711680 & var12 * (Class155.anInt1971 & '\uff00') + ('\uff00' & Class96.anInt1345) * var13) >> 8;
				float var14 = (float)(65536 - Class3.anInt72) / 65536.0F;
				Class30.aFloat578 = var14 * Class3_Sub13_Sub8.aFloat3105 + var15 * Class3_Sub17.aFloat2457;
				Class3_Sub13_Sub36.aFloat3424 = Class3_Sub13_Sub36.aFloat3435 * var14 + var15 * Class3_Sub13_Sub2.aFloat3044;
				Class12.aFloat319 = var15 * RSInterface.aFloat246 + var14 * Class110.aFloat1475;
				CacheIndex.anInt1950 = (16711680 & (Class60.anInt932 & '\uff00') * var13 + var12 * (Class3_Sub13_Sub23_Sub1.anInt4037 & '\uff00')) + ((16711935 & Class3_Sub13_Sub23_Sub1.anInt4037) * var12 - -((Class60.anInt932 & 16711935) * var13) & -16711936) >> 8;
				Class41.anInt689 = var13 * Class132.anInt1736 + var12 * Class100.anInt1407 >> 8;
			}

			Class92.setLightParams(CacheIndex.anInt1950, Class30.aFloat578, Class3_Sub13_Sub36.aFloat3424, Class12.aFloat319);
			Class92.setFogValues(Class3_Sub28_Sub12.anInt3652, Class41.anInt689);
			if(var4 != 1) {
				aClass94_3807 = (RSString)null;
			}

			Class92.setLightPosition((float)Class46.anInt741, (float)Class3_Sub13_Sub22.anInt3274, (float)Class86.anInt1191);
			Class92.method1504();
			return Class3_Sub28_Sub12.anInt3652;
		} catch (RuntimeException var16) {
			throw Class44.method1067(var16, "i.F(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
		}
	}

	static final int method810(byte var0, int var1) {
		try {
			return var0 != 3?74:255 & var1;
		} catch (RuntimeException var3) {
			throw Class44.method1067(var3, "i.D(" + var0 + ',' + var1 + ')');
		}
	}

	final void method811(byte var1, int var2, byte[] var3, int var4) {
		try {
			if(var1 < 16) {
				addChatMessage((RSString)null, 126, (RSString)null, -28);
			}

			for(int var5 = 0; var5 < var4; ++var5) {
				var3[var2 + var5] = (byte)(this.buffer[this.index++] + -this.isaacCipher.nextOpcode(-9356));
			}

		} catch (RuntimeException var6) {
			throw Class44.method1067(var6, "i.S(" + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + var4 + ')');
		}
	}

	final int getBits(byte var1, int var2) {
		try {
			int var3 = this.anInt3806 >> 3;
			int var4 = 8 + -(7 & this.anInt3806);
			int var5 = 0;
			this.anInt3806 += var2;
			if(var1 != -11) {
				return -10;
			} else {
				while(~var4 > ~var2) {
					var5 += (Class140_Sub2.anIntArray2709[var4] & this.buffer[var3++]) << -var4 + var2;
					var2 -= var4;
					var4 = 8;
				}

				if(~var4 == ~var2) {
					var5 += this.buffer[var3] & Class140_Sub2.anIntArray2709[var4];
				} else {
					var5 += this.buffer[var3] >> var4 - var2 & Class140_Sub2.anIntArray2709[var2];
				}

				return var5;
			}
		} catch (RuntimeException var6) {
			throw Class44.method1067(var6, "i.C(" + var1 + ',' + var2 + ')');
		}
	}

	static final void method813(int var0) {
		try {
			Class3_Sub28_Sub4.aClass93_3572.method1523((byte)-127);
			if(var0 == 1974) {
				Class143.aClass93_1874.method1523((byte)-113);
				Class67.aClass93_1013.method1523((byte)-108);
			}
		} catch (RuntimeException var2) {
			throw Class44.method1067(var2, "i.O(" + var0 + ')');
		}
	}

	final void method814(int[] var1, boolean var2) {
		try {
			this.isaacCipher = new ISAACCipher(var1);
			if(var2) {
				this.method815(13, -97);
			}

		} catch (RuntimeException var4) {
			throw Class44.method1067(var4, "i.T(" + (var1 != null?"{...}":"null") + ',' + var2 + ')');
		}
	}

	final int method815(int var1, int var2) {
		try {
			if(var2 != 32666) {
				this.method811((byte)96, 46, (byte[])null, -50);
			}

			return var1 * 8 - this.anInt3806;
		} catch (RuntimeException var4) {
			throw Class44.method1067(var4, "i.U(" + var1 + ',' + var2 + ')');
		}
	}

	final void putOpcode(int opcode) {
		if (buffer == null || isaacCipher == null) {
			System.err.println("Buffer or cipher was null in CLass2_Sub30_Sub1 " + buffer + ", " + isaacCipher);
			return;
		}
		this.buffer[this.index++] = (byte)(opcode + this.isaacCipher.nextOpcode(-9356));
	}

	final int getOpcode(int var1) {
		try {
			return var1 != 0?102:255 & this.buffer[this.index++] - this.isaacCipher.nextOpcode(-9356);
		} catch (RuntimeException var3) {
			throw Class44.method1067(var3, "i.P(" + var1 + ')');
		}
	}

	final void method818(boolean var1) {
		try {
			this.index = (this.anInt3806 + 7) / 8;
			if(var1) {
				this.getBits((byte)-55, -75);
			}

		} catch (RuntimeException var3) {
			throw Class44.method1067(var3, "i.Q(" + var1 + ')');
		}
	}

	static final void method819(boolean var0) {
		try {
			Class3_Sub31 var1 = (Class3_Sub31)Class3_Sub13_Sub17.aClass130_3208.method1776(73);
			if(!var0) {
				for(; var1 != null; var1 = (Class3_Sub31)Class3_Sub13_Sub17.aClass130_3208.method1778(-76)) {
					int var2 = var1.anInt2602;
					if(Canvas_Sub2.loadInterface(var2, 104)) {
						boolean var3 = true;
						RSInterface[] var4 = GameObject.aClass11ArrayArray1834[var2];

						int var5;
						for(var5 = 0; ~var4.length < ~var5; ++var5) {
							if(var4[var5] != null) {
								var3 = var4[var5].usingScripts;
								break;
							}
						}

						if(!var3) {
							var5 = (int)var1.aLong71;
							RSInterface var6 = Class7.getRSInterface((byte)123, var5);
							if(null != var6) {
								Class20.method909(117, var6);
							}
						}
					}
				}

			}
		} catch (RuntimeException var7) {
			throw Class44.method1067(var7, "i.A(" + var0 + ')');
		}
	}

}
