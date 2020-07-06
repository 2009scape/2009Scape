package org.runite.jagex;

final class Class45 {

	static RSString aClass94_728 = RSString.createRSString("leuchten2:");
	static int[] anIntArray729 = new int[4096];
	static float aFloat730;
	static CacheIndex aClass153_731;
	static boolean aBoolean732 = false;
	static int anInt733 = 0;
	static int anInt734 = 0;
	static int anInt735;
	static Class3_Sub28_Sub16 aClass3_Sub28_Sub16_736;


	static final void parsePlayerMask(int var0, int var1, byte var2, Player var3) {
		try {
			int var4;
			int chatIcon;
			int var7;
			//Ordinal: 0 Chat
			if(0 != (var0 & 128)) {
				var4 = GraphicDefinition.incomingBuffer.getLEShort(var2 + -47);
				chatIcon = GraphicDefinition.incomingBuffer.getByte((byte)-54);
				int var6 = GraphicDefinition.incomingBuffer.getByte((byte)-57);
				var7 = GraphicDefinition.incomingBuffer.index;
				boolean var8 = -1 != ~('\u8000' & var4);
				if(null != var3.displayName && var3.class52 != null) {
					long var9 = var3.displayName.toLong(-125);
					boolean var11 = false;
					if(chatIcon <= 1) {
						if(!var8 && (Class3_Sub15.aBoolean2433 && !Class121.aBoolean1641 || Class3_Sub13_Sub14.aBoolean3166)) {
							var11 = true;
						} else {
							for(int var12 = 0; var12 < Class3_Sub28_Sub5.anInt3591; ++var12) {
								if(Class114.ignores[var12] == var9) {
									var11 = true;
									break;
								}
							}
						}
					}

					if(!var11 && 0 == WorldListEntry.inTutorialIsland) {
						Class161.aClass3_Sub30_2030.index = 0;
						GraphicDefinition.incomingBuffer.method774(2, var6, Class161.aClass3_Sub30_2030.buffer, 0);
						Class161.aClass3_Sub30_2030.index = 0;
						int var13 = -1;
						RSString message;
						if(var8) {
							Class10 var14 = Class155.method2156(1024, Class161.aClass3_Sub30_2030);
							var4 &= 32767;
							var13 = var14.anInt149;
							message = var14.aClass3_Sub28_Sub4_151.method555(var2 + 28100, Class161.aClass3_Sub30_2030);
						} else {
							message = Class3_Sub28_Sub17.method686(Class32.method992(Class161.aClass3_Sub30_2030, 29488).method1536(78));
						}

						var3.textSpoken = message.trim(1);
						var3.textEffect = var4 & 255;
						var3.textCycle = 150;
						var3.textColor = var4 >> 8;
						if (chatIcon == 2) {
							Class3_Sub28_Sub12.sendGameMessage(var13, var8?17:1, message, (RSString)null, RenderAnimationDefinition.method903(new RSString[]{RSString.createRSString("<img=" + (chatIcon-1) + ">"), var3.getName(var2 + 79)}, (byte)-116));
						} else if (chatIcon == 1) {
							Class3_Sub28_Sub12.sendGameMessage(var13, var8?17:1, message, (RSString)null, RenderAnimationDefinition.method903(new RSString[]{Class32.aClass94_592, var3.getName(0)}, (byte)-127));
						} else {
							Class3_Sub28_Sub12.sendGameMessage(var13, var8?17:2, message, (RSString)null, var3.getName(0));
						}
					}
				}
				GraphicDefinition.incomingBuffer.index = var7 + var6;
			}

			//Ordinal: 1 Hit
			if(~(var0 & 1) != -1) {
				var4 = GraphicDefinition.incomingBuffer.getSmart(true);
				chatIcon = GraphicDefinition.incomingBuffer.getByteA((byte)-99);
				var3.method1970(chatIcon, -8, Class44.anInt719, var4);
				var3.anInt2781 = 300 + Class44.anInt719;
				var3.anInt2775 = GraphicDefinition.incomingBuffer.getByteS(true);
			}

			//Ordinal: 2 Animation
			if((var0 & 8) != 0) {
				var4 = GraphicDefinition.incomingBuffer.getShort(1);
				if(-65536 == ~var4) {
					var4 = -1;
				}

				chatIcon = GraphicDefinition.incomingBuffer.getByte((byte)-112);
				Class3_Sub28_Sub14.method628(0, chatIcon, var4, var3);
			}

			//Ordinal: 3 Appearance
			if(0 != (4 & var0)) {
				var4 = GraphicDefinition.incomingBuffer.getByteA((byte)-118);
				byte[] var16 = new byte[var4];
				RSByteBuffer var19 = new RSByteBuffer(var16);
				GraphicDefinition.incomingBuffer.method764(0, var4, var16, (byte)93);
				Class65.aClass3_Sub30Array986[var1] = var19;
				var3.parseAppearance(-15, var19);
			}

			//Ordinal: 4 Face entity
			if((2 & var0) != 0) {
				var3.anInt2772 = GraphicDefinition.incomingBuffer.getShortA(-114);
				if(-65536 == ~var3.anInt2772) {
					var3.anInt2772 = -1;
				}
			}

			//Ordinal: 5 Force movement
			if(~(1024 & var0) != -1) {
				var3.anInt2784 = GraphicDefinition.incomingBuffer.getByteC(true);
				var3.anInt2835 = GraphicDefinition.incomingBuffer.getByte((byte)-24);
				var3.anInt2823 = GraphicDefinition.incomingBuffer.getByteA((byte)-106);
				var3.anInt2798 = GraphicDefinition.incomingBuffer.getByte((byte)-37);
				var3.anInt2800 = GraphicDefinition.incomingBuffer.getLEShort(-90) + Class44.anInt719;
				var3.anInt2790 = GraphicDefinition.incomingBuffer.getLEShort(-99) - -Class44.anInt719;
				var3.anInt2840 = GraphicDefinition.incomingBuffer.getByteC(true);
				var3.anInt2816 = 1;
				var3.anInt2811 = 0;
			}

			//Ordinal: 6 Force chat
			if(~(var0 & 32) != -1) {
				var3.textSpoken = GraphicDefinition.incomingBuffer.getString();
				if(~var3.textSpoken.charAt(0, (byte)-45) == -127) {
					var3.textSpoken = var3.textSpoken.method1556(1, (byte)-74);
					Class3_Sub30_Sub1.addChatMessage(var3.getName(0), 2, var3.textSpoken, var2 ^ 78);
				} else if(var3 == Class102.player) {
					Class3_Sub30_Sub1.addChatMessage(var3.getName(0), 2, var3.textSpoken, var2 + 78);
				}

				var3.textEffect = 0;
				var3.textColor = 0;
				var3.textCycle = 150;
			}

			//Ordinal: 7 Hit 2
			if(~(var0 & 512) != -1) {
				var4 = GraphicDefinition.incomingBuffer.getSmart(true);
				chatIcon = GraphicDefinition.incomingBuffer.getByteS(true);
				var3.method1970(chatIcon, var2 + 71, Class44.anInt719, var4);
			}

			//Ordinal: 8
			if(~(2048 & var0) != -1) {
				var4 = GraphicDefinition.incomingBuffer.getByteC(true);
				int[] var18 = new int[var4];
				int[] var17 = new int[var4];
				int[] var20 = new int[var4];

				for(int var22 = 0; ~var4 < ~var22; ++var22) {
					int var23 = GraphicDefinition.incomingBuffer.getLEShort(-101);
					if('\uffff' == var23) {
						var23 = -1;
					}

					var18[var22] = var23;
					var17[var22] = GraphicDefinition.incomingBuffer.getByteA((byte)125);
					var20[var22] = GraphicDefinition.incomingBuffer.getShort(Class93.method1519(var2, -80));
				}

				Class75_Sub1.method1342(var17, var18, var3, (byte)-113, var20);
			}

			//Ordinal: 9 Graphic
			if((256 & var0) != 0) {
				var4 = GraphicDefinition.incomingBuffer.getLEShort(-128);
				if(var4 == '\uffff') {
					var4 = -1;
				}

				chatIcon = GraphicDefinition.incomingBuffer.getIntB((byte)-73);
				boolean var21 = true;
				if(~var4 != 0 && 0 != ~var3.anInt2842 && Client.getAnimationDefinition(RenderAnimationDefinition.getGraphicDefinition((byte)42, var4).anInt542, (byte)-20).anInt1857 < Client.getAnimationDefinition(RenderAnimationDefinition.getGraphicDefinition((byte)42, var3.anInt2842).anInt542, (byte)-20).anInt1857) {
					var21 = false;
				}

				if(var21) {
					var3.anInt2759 = (chatIcon & '\uffff') + Class44.anInt719;
					var3.anInt2761 = 0;
					var3.anInt2805 = 0;
					var3.anInt2842 = var4;
					if(~var3.anInt2759 < ~Class44.anInt719) {
						var3.anInt2805 = -1;
					}

					var3.anInt2799 = chatIcon >> 16;
					var3.anInt2826 = 1;
					if(~var3.anInt2842 != 0 && Class44.anInt719 == var3.anInt2759) {
						var7 = RenderAnimationDefinition.getGraphicDefinition((byte)42, var3.anInt2842).anInt542;
						if(0 != ~var7) {
							AnimationDefinition var24 = Client.getAnimationDefinition(var7, (byte)-20);
							if(null != var24 && var24.frames != null) {
								IOHandler.method1470(var3.anInt2829, var24, 183921384, var3.anInt2819, var3 == Class102.player, 0);
							}
						}
					}
				}
			}

			if(var2 == -79) {
				//Ordinal: 10 Face location
				if(~(var0 & 64) != -1) {
					var3.anInt2786 = GraphicDefinition.incomingBuffer.getShort(1);
					var3.anInt2762 = GraphicDefinition.incomingBuffer.getLEShortA((byte)-107);
				}

			}
		} catch (RuntimeException var15) {
			throw Class44.method1067(var15, "gk.A(" + var0 + ',' + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ')');
		}
	}

	public static void method1081(byte var0) {
		try {
			aClass3_Sub28_Sub16_736 = null;
			anIntArray729 = null;
			aClass94_728 = null;
			if(var0 <= 63) {
				method1082((byte[])null, -60);
			}

			aClass153_731 = null;
		} catch (RuntimeException var2) {
			throw Class44.method1067(var2, "gk.E(" + var0 + ')');
		}
	}

	static final void method1082(byte[] var0, int var1) {
		try {
			RSByteBuffer var2 = new RSByteBuffer(var0);
			var2.index = -2 + var0.length;
			Class95.anInt1338 = var2.getShort(1);
			Class3_Sub13_Sub6.anIntArray3076 = new int[Class95.anInt1338];
			Class140_Sub7.anIntArray2931 = new int[Class95.anInt1338];
			Class164.anIntArray2048 = new int[Class95.anInt1338];
			Class3_Sub13_Sub22.aBooleanArray3272 = new boolean[Class95.anInt1338];
			Class163_Sub3.aByteArrayArray3005 = new byte[Class95.anInt1338][];
			RSByteBuffer.anIntArray2591 = new int[Class95.anInt1338];
			Class163_Sub1.aByteArrayArray2987 = new byte[Class95.anInt1338][];
			var2.index = -(8 * Class95.anInt1338) + var0.length - 7;
			Class3_Sub15.anInt2426 = var2.getShort(1);
			Class133.anInt1748 = var2.getShort(1);
			int var3 = (var2.getByte((byte)-43) & 255) - -1;

			int var4;
			for(var4 = 0; ~var4 > ~Class95.anInt1338; ++var4) {
				Class164.anIntArray2048[var4] = var2.getShort(1);
			}

			if(var1 < 11) {
				method1081((byte)-52);
			}

			for(var4 = 0; ~var4 > ~Class95.anInt1338; ++var4) {
				RSByteBuffer.anIntArray2591[var4] = var2.getShort(1);
			}

			for(var4 = 0; Class95.anInt1338 > var4; ++var4) {
				Class140_Sub7.anIntArray2931[var4] = var2.getShort(1);
			}

			for(var4 = 0; ~var4 > ~Class95.anInt1338; ++var4) {
				Class3_Sub13_Sub6.anIntArray3076[var4] = var2.getShort(1);
			}

			var2.index = -(8 * Class95.anInt1338) + var0.length + -7 + 3 + -(var3 * 3);
			Class3_Sub13_Sub38.spritePalette = new int[var3];

			for(var4 = 1; ~var4 > ~var3; ++var4) {
				Class3_Sub13_Sub38.spritePalette[var4] = var2.getTriByte((byte)122);
				if(0 == Class3_Sub13_Sub38.spritePalette[var4]) {
					Class3_Sub13_Sub38.spritePalette[var4] = 1;
				}
			}

			var2.index = 0;

			for(var4 = 0; var4 < Class95.anInt1338; ++var4) {
				int var5 = Class140_Sub7.anIntArray2931[var4];
				int var6 = Class3_Sub13_Sub6.anIntArray3076[var4];
				int var7 = var5 * var6;
				byte[] var8 = new byte[var7];
				boolean var10 = false;
				Class163_Sub1.aByteArrayArray2987[var4] = var8;
				byte[] var9 = new byte[var7];
				Class163_Sub3.aByteArrayArray3005[var4] = var9;
				int var11 = var2.getByte((byte)-64);
				int var12;
				if(-1 != ~(1 & var11)) {
					int var13;
					for(var12 = 0; ~var12 > ~var5; ++var12) {
						for(var13 = 0; var13 < var6; ++var13) {
							var8[var12 + var13 * var5] = var2.getByte();
						}
					}

					if(-1 != ~(var11 & 2)) {
						for(var12 = 0; ~var12 > ~var5; ++var12) {
							for(var13 = 0; var13 < var6; ++var13) {
								byte var14 = var9[var5 * var13 + var12] = var2.getByte();
								var10 |= -1 != var14;
							}
						}
					}
				} else {
					for(var12 = 0; ~var7 < ~var12; ++var12) {
						var8[var12] = var2.getByte();
					}

					if((2 & var11) != 0) {
						for(var12 = 0; ~var12 > ~var7; ++var12) {
							byte var16 = var9[var12] = var2.getByte();
							var10 |= var16 != -1;
						}
					}
				}

				Class3_Sub13_Sub22.aBooleanArray3272[var4] = var10;
			}

		} catch (RuntimeException var15) {
			throw Class44.method1067(var15, "gk.B(" + (var0 != null?"{...}":"null") + ',' + var1 + ')');
		}
	}

	static final void method1083(byte var0) {
		try {
			Class3_Sub13_Sub9.anIntArray3107 = InputStream_Sub1.method62(true, 14585, 8, 2048, 4, 0.4F, 8, 35);
			int var1 = -5 / ((var0 - 45) / 59);
		} catch (RuntimeException var2) {
			throw Class44.method1067(var2, "gk.C(" + var0 + ')');
		}
	}

	static final void method1084(Node var0, Node var1, byte var2) {
		try {
			if(var1.aClass3_Sub28_2570 != null) {
				var1.method524((byte)-107);
			}

			var1.aClass3_Sub28_2570 = var0;
			var1.aClass3_Sub28_2578 = var0.aClass3_Sub28_2578;
			var1.aClass3_Sub28_2570.aClass3_Sub28_2578 = var1;
			if(var2 <= 101) {
				aBoolean732 = true;
			}

			var1.aClass3_Sub28_2578.aClass3_Sub28_2570 = var1;
		} catch (RuntimeException var4) {
			throw Class44.method1067(var4, "gk.D(" + (var0 != null?"{...}":"null") + ',' + (var1 != null?"{...}":"null") + ',' + var2 + ')');
		}
	}

}
