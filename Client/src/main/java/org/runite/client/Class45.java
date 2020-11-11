package org.runite.client;

import java.util.Objects;

final class Class45 {

	static int[] anIntArray729 = new int[4096];
	static float aFloat730;
	static int anInt733 = 0;
	static int anInt734 = 0;
	static AbstractSprite aAbstractSprite_736;
    static DataBuffer aClass3_Sub30_2030 = new DataBuffer(new byte[5000]);


    static void parsePlayerMask(int var0, int var1, Player var3) {
		try {
			int var4;
			int chatIcon;
			int var7;
			//Ordinal: 0 Chat
			if(0 != (var0 & 128)) {
				var4 = GraphicDefinition.incomingBuffer.readUnsignedShortLE();
				chatIcon = GraphicDefinition.incomingBuffer.readUnsignedByte();
				int var6 = GraphicDefinition.incomingBuffer.readUnsignedByte();
				var7 = GraphicDefinition.incomingBuffer.index;
				boolean var8 = ('\u8000' & var4) != 0;
				if(null != var3.displayName && var3.class52 != null) {
					long var9 = var3.displayName.toLong();
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

					if(!var11 && 0 == PacketParser.inTutorialIsland) {
						aClass3_Sub30_2030.index = 0;
						GraphicDefinition.incomingBuffer.readBytesReverse(var6, aClass3_Sub30_2030.buffer);
						aClass3_Sub30_2030.index = 0;
						int var13 = -1;
						RSString message;
						if(var8) {
							Class10 var14 = Class155.method2156(aClass3_Sub30_2030);
							var4 &= 32767;
							var13 = var14.anInt149;
							message = var14.aClass3_Sub28_Sub4_151.method555(aClass3_Sub30_2030);
						} else {
							message = Class3_Sub28_Sub17.method686(Objects.requireNonNull(Class32.method992(aClass3_Sub30_2030).properlyCapitalize()));
						}

						var3.textSpoken = message.trim(1);
						var3.textEffect = var4 & 255;
						var3.textCycle = 150;
						var3.textColor = var4 >> 8;
						if (chatIcon == 2) {
							Class3_Sub28_Sub12.sendGameMessage(var13, var8?17:1, message, (RSString)null, RSString.stringCombiner(new RSString[]{RSString.parse("<img=" + (chatIcon-1) + ">"), var3.getName()}));
						} else if (chatIcon == 1) {
							Class3_Sub28_Sub12.sendGameMessage(var13, var8?17:1, message, (RSString)null, RSString.stringCombiner(new RSString[]{TextCore.aClass94_592, var3.getName()}));
						} else {
							Class3_Sub28_Sub12.sendGameMessage(var13, var8?17:2, message, (RSString)null, var3.getName());
						}
					}
				}
				GraphicDefinition.incomingBuffer.index = var7 + var6;
			}

			//Ordinal: 1 Hit
			if((var0 & 1) != 0) {
				var4 = GraphicDefinition.incomingBuffer.getSmart();
				chatIcon = GraphicDefinition.incomingBuffer.readUnsignedByte128();
				var3.method1970(chatIcon, Class44.anInt719, var4);
				var3.anInt2781 = 300 + Class44.anInt719;
				var3.anInt2775 = GraphicDefinition.incomingBuffer.readUnsigned128Byte();
			}

			//Ordinal: 2 Animation
			if((var0 & 8) != 0) {
				var4 = GraphicDefinition.incomingBuffer.readUnsignedShort();
				if(var4 == 65535) {
					var4 = -1;
				}

				chatIcon = GraphicDefinition.incomingBuffer.readUnsignedByte();
				WorldMap.method628(chatIcon, var4, var3);
			}

			//Ordinal: 3 Appearance
			if(0 != (4 & var0)) {
				var4 = GraphicDefinition.incomingBuffer.readUnsignedByte128();
				byte[] var16 = new byte[var4];
				DataBuffer var19 = new DataBuffer(var16);
				GraphicDefinition.incomingBuffer.readBytes(var16, var4);
				Class65.aClass3_Sub30Array986[var1] = var19;
				var3.parseAppearance(-15, var19);
			}

			//Ordinal: 4 Face entity
			if((2 & var0) != 0) {
				var3.anInt2772 = GraphicDefinition.incomingBuffer.readUnsignedShort128();
				if(var3.anInt2772 == 65535) {
					var3.anInt2772 = -1;
				}
			}

			//Ordinal: 5 Force movement
			if((1024 & var0) != 0) {
				var3.anInt2784 = GraphicDefinition.incomingBuffer.readUnsignedNegativeByte();
				var3.anInt2835 = GraphicDefinition.incomingBuffer.readUnsignedByte();
				var3.anInt2823 = GraphicDefinition.incomingBuffer.readUnsignedByte128();
				var3.anInt2798 = GraphicDefinition.incomingBuffer.readUnsignedByte();
				var3.anInt2800 = GraphicDefinition.incomingBuffer.readUnsignedShortLE() + Class44.anInt719;
				var3.anInt2790 = GraphicDefinition.incomingBuffer.readUnsignedShortLE() - -Class44.anInt719;
				var3.anInt2840 = GraphicDefinition.incomingBuffer.readUnsignedNegativeByte();
				var3.anInt2816 = 1;
				var3.anInt2811 = 0;
			}

			//Ordinal: 6 Force chat
			if((var0 & 32) != 0) {
				var3.textSpoken = GraphicDefinition.incomingBuffer.readString();
				if(var3.textSpoken.charAt(0, (byte) -45) == 126) {
					var3.textSpoken = var3.textSpoken.substring(1);
					Class3_Sub30_Sub1.addChatMessage(var3.getName(), 2, var3.textSpoken, (byte) -79 ^ 78);
				} else if(var3 == Class102.player) {
					Class3_Sub30_Sub1.addChatMessage(var3.getName(), 2, var3.textSpoken, (byte) -79 + 78);
				}

				var3.textEffect = 0;
				var3.textColor = 0;
				var3.textCycle = 150;
			}

			//Ordinal: 7 Hit 2
			if((var0 & 512) != 0) {
				var4 = GraphicDefinition.incomingBuffer.getSmart();
				chatIcon = GraphicDefinition.incomingBuffer.readUnsigned128Byte();
				var3.method1970(chatIcon, Class44.anInt719, var4);
			}

			//Ordinal: 8
			if((2048 & var0) != 0) {
				var4 = GraphicDefinition.incomingBuffer.readUnsignedNegativeByte();
				int[] var18 = new int[var4];
				int[] var17 = new int[var4];
				int[] var20 = new int[var4];

				for(int var22 = 0; var22 < var4; ++var22) {
					int var23 = GraphicDefinition.incomingBuffer.readUnsignedShortLE();
					if('\uffff' == var23) {
						var23 = -1;
					}

					var18[var22] = var23;
					var17[var22] = GraphicDefinition.incomingBuffer.readUnsignedByte128();
					var20[var22] = GraphicDefinition.incomingBuffer.readUnsignedShort();
				}

				Class75_Sub1.method1342(var17, var18, var3, var20);
			}

			//Ordinal: 9 Graphic
			if((256 & var0) != 0) {
				var4 = GraphicDefinition.incomingBuffer.readUnsignedShortLE();
				if(var4 == '\uffff') {
					var4 = -1;
				}

				chatIcon = GraphicDefinition.incomingBuffer.readIntV2();
				boolean var21 = true;
				if(var4 != -1 && var3.anInt2842 != -1 && Client.getAnimationDefinition(GraphicDefinition.getGraphicDefinition((byte)42, var4).anInt542).anInt1857 < Client.getAnimationDefinition(GraphicDefinition.getGraphicDefinition((byte)42, var3.anInt2842).anInt542).anInt1857) {
					var21 = false;
				}

				if(var21) {
					var3.anInt2759 = (chatIcon & '\uffff') + Class44.anInt719;
					var3.anInt2761 = 0;
					var3.anInt2805 = 0;
					var3.anInt2842 = var4;
					if(Class44.anInt719 < var3.anInt2759) {
						var3.anInt2805 = -1;
					}

					var3.anInt2799 = chatIcon >> 16;
					var3.anInt2826 = 1;
					if(var3.anInt2842 != -1 && Class44.anInt719 == var3.anInt2759) {
						var7 = GraphicDefinition.getGraphicDefinition((byte)42, var3.anInt2842).anInt542;
						if(var7 != -1) {
							AnimationDefinition var24 = Client.getAnimationDefinition(var7);
							if(var24.frames != null) {
								Unsorted.method1470(var3.anInt2829, var24, 183921384, var3.anInt2819, var3 == Class102.player, 0);
							}
						}
					}
				}
			}

			//Ordinal: 10 Face location
			if((var0 & 64) != 0) {
				var3.anInt2786 = GraphicDefinition.incomingBuffer.readUnsignedShort();
				var3.anInt2762 = GraphicDefinition.incomingBuffer.readUnsignedShortLE128();
			}

		} catch (RuntimeException var15) {
			throw ClientErrorException.clientError(var15, "gk.A(" + var0 + ',' + var1 + ',' + (byte) -79 + ',' + (var3 != null?"{...}":"null") + ')');
		}
	}

	static void method1082(byte[] var0, int var1) {
		try {
			DataBuffer var2 = new DataBuffer(var0);
			var2.index = -2 + var0.length;
			Class95.anInt1338 = var2.readUnsignedShort();
			Unsorted.anIntArray3076 = new int[Class95.anInt1338];
			Class140_Sub7.anIntArray2931 = new int[Class95.anInt1338];
			Class164.anIntArray2048 = new int[Class95.anInt1338];
			Class3_Sub13_Sub22.aBooleanArray3272 = new boolean[Class95.anInt1338];
			Class163_Sub3.aByteArrayArray3005 = new byte[Class95.anInt1338][];
			Unsorted.anIntArray2591 = new int[Class95.anInt1338];
			Class163_Sub1.aByteArrayArray2987 = new byte[Class95.anInt1338][];
			var2.index = -(8 * Class95.anInt1338) + var0.length - 7;
			Class3_Sub15.anInt2426 = var2.readUnsignedShort();
			Class133.anInt1748 = var2.readUnsignedShort();
			int var3 = (var2.readUnsignedByte() & 255) - -1;

			int var4;
			for(var4 = 0; Class95.anInt1338 > var4; ++var4) {
				Class164.anIntArray2048[var4] = var2.readUnsignedShort();
			}

			for(var4 = 0; Class95.anInt1338 > var4; ++var4) {
				Unsorted.anIntArray2591[var4] = var2.readUnsignedShort();
			}

			for(var4 = 0; Class95.anInt1338 > var4; ++var4) {
				Class140_Sub7.anIntArray2931[var4] = var2.readUnsignedShort();
			}

			for(var4 = 0; Class95.anInt1338 > var4; ++var4) {
				Unsorted.anIntArray3076[var4] = var2.readUnsignedShort();
			}

			var2.index = -(8 * Class95.anInt1338) + var0.length + -7 + 3 + -(var3 * 3);
			Class3_Sub13_Sub38.spritePalette = new int[var3];

			for(var4 = 1; var3 > var4; ++var4) {
				Class3_Sub13_Sub38.spritePalette[var4] = var2.readMedium();
				if(0 == Class3_Sub13_Sub38.spritePalette[var4]) {
					Class3_Sub13_Sub38.spritePalette[var4] = 1;
				}
			}

			var2.index = 0;

			for(var4 = 0; var4 < Class95.anInt1338; ++var4) {
				int var5 = Class140_Sub7.anIntArray2931[var4];
				int var6 = Unsorted.anIntArray3076[var4];
				int var7 = var5 * var6;
				byte[] var8 = new byte[var7];
				boolean var10 = false;
				Class163_Sub1.aByteArrayArray2987[var4] = var8;
				byte[] var9 = new byte[var7];
				Class163_Sub3.aByteArrayArray3005[var4] = var9;
				int var11 = var2.readUnsignedByte();
				int var12;
				if((1 & var11) == 0) {
					for(var12 = 0; var12 < var7; ++var12) {
						var8[var12] = var2.readSignedByte();
					}

					if((2 & var11) != 0) {
						for(var12 = 0; var7 > var12; ++var12) {
							byte var16 = var9[var12] = var2.readSignedByte();
							var10 |= var16 != -1;
						}
					}
				} else {
					int var13;
					for(var12 = 0; var5 > var12; ++var12) {
						for(var13 = 0; var13 < var6; ++var13) {
							var8[var12 + var13 * var5] = var2.readSignedByte();
						}
					}

					if((var11 & 2) != 0) {
						for(var12 = 0; var5 > var12; ++var12) {
							for(var13 = 0; var13 < var6; ++var13) {
								byte var14 = var9[var5 * var13 + var12] = var2.readSignedByte();
								var10 |= -1 != var14;
							}
						}
					}
				}

				Class3_Sub13_Sub22.aBooleanArray3272[var4] = var10;
			}

		} catch (RuntimeException var15) {
			throw ClientErrorException.clientError(var15, "gk.B(" + (var0 != null?"{...}":"null") + ',' + var1 + ')');
		}
	}

	static void method1083(byte var0) {
		try {
			Class43.anIntArray3107 = Unsorted.method62();
		} catch (RuntimeException var2) {
			throw ClientErrorException.clientError(var2, "gk.C(" + var0 + ')');
		}
	}

}
