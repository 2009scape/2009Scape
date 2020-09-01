package org.runite.jagex;
import org.runite.Configurations;
import org.runite.GameLaunch;

import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public final class Client extends GameShell {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8336806252605101745L;
	static Class130 aClass130_2194 = new Class130(16);
	static int anInt2195;
	static Class3_Sub11[][] aClass3_Sub11ArrayArray2199;
	static int[] anIntArray2200;
	static int ZOOM = 600;
	static int currentPort;
	static int anInt3689 = 0;
	static int LoadingStageNumber = 10;
    static int anInt869;

    static void method631(CacheIndex var1) {
	   try {
		  Class3_Sub28_Sub5.aClass153_3580 = var1;
		  anInt869 = Class3_Sub28_Sub5.aClass153_3580.getFileAmount(4, (byte)112);
	   } catch (RuntimeException var3) {
		  throw Class44.clientError(var3, "qc.D(" + false + ',' + (var1 != null?"{...}":"null") + ')');
	   }
	}

	public static void method632() {
	   try {
		  PacketParser.aClass3_Sub19Array3694 = null;
		  Class86.aClass130_3679 = null;
		  Class3_Sub19.anIntArray3693 = null;

	   } catch (RuntimeException var2) {
		  throw Class44.clientError(var2, "qc.E(" + ')');
	   }
	}

	static void method1175(int var0) {
	   try {
		  Class3_Sub13_Sub30.anInt3362 = -1;
		  Class82.anInt1150 = -1;

		  Class3_Sub28_Sub1.anInt3536 = var0;
		  Class3_Sub5.method117();
	   } catch (RuntimeException var3) {
		  throw Class44.clientError(var3, "hj.D(" + var0 + ',' + 112 + ')');
	   }
	}

	static void method375(CacheIndex var1, CacheIndex var2) {
	   try {
		  Class10.aClass153_152 = var1;
		  Class127.aClass153_1680 = var2;
		  Class25.anInt497 = Class127.aClass153_1680.getFileAmount(3, (byte)95);
	   } catch (RuntimeException var4) {
		  throw Class44.clientError(var4, "jl.D(" + 3 + ',' + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ')');
	   }
	}


	final void method38() {
		try {
			if(Class143.loadingStage != 1000) {
				boolean var2 = NPC.method1988();
				if(var2 && Class83.aBoolean1158 && WorldListEntry.aClass155_2627 != null) {
					WorldListEntry.aClass155_2627.method2158();
				}

				if((Class143.loadingStage == 30 || Class143.loadingStage == 10) && (Class3_Sub28_Sub5.aBoolean3593 || Class53.aLong866 != 0 && Class53.aLong866 < Class5.method830((byte)-55))) {
					GameObject.graphicsSettings(Class3_Sub28_Sub5.aBoolean3593, Class83.method1411(0), Class3_Sub13.anInt2378, Class3_Sub13_Sub5.anInt3071);
				}

				int var4;
				int var5;
				if(null == Class3_Sub13_Sub10.aFrame3121) {
					Object var3;
					if(GameShell.frame == null) {
						var3 = Class38.aClass87_665.anApplet1219;
					} else {
						var3 = GameShell.frame;
					}

					var4 = ((Container)var3).getSize().width;
					var5 = ((Container)var3).getSize().height;
					if(var3 == GameShell.frame) {
						Insets var6 = GameShell.frame.getInsets();
						var4 -= var6.right + var6.left;
						var5 -= var6.top + var6.bottom;
					}

					if(var4 != Class3_Sub9.anInt2334 || Class70.anInt1047 != var5) {
						if(Signlink.osName.startsWith("mac")) {
							Class3_Sub9.anInt2334 = var4;
							Class70.anInt1047 = var5;
						} else {
							Class119.method1729();
						}

						Class53.aLong866 = Class5.method830((byte)-55) - -500L;
					}
				}

				if(Class3_Sub13_Sub10.aFrame3121 != null && !Class3_Sub13_Sub6.aBoolean3078 && (30 == Class143.loadingStage || 10 == Class143.loadingStage)) {
					GameObject.graphicsSettings(false, Node.anInt2577, -1, -1);
				}

				boolean var10 = false;
				if(Class3_Sub13_Sub10.aBoolean3116) {
					var10 = true;
					Class3_Sub13_Sub10.aBoolean3116 = false;
				}

				if(var10) {
					Class80.method1396(40 ^ -41);
				}

				if(HDToolKit.highDetail) {
					for(var4 = 0; var4 < 100; ++var4) {
						Class3_Sub28_Sub14.aBooleanArray3674[var4] = true;
					}
				}
				if(Class143.loadingStage == 0) {
					// Discord
					Discord.InitalizeDiscord();
					Class3_Sub28_Sub1.updateLoadingBar((Color)null, var10, Class3_Sub17.aClass94_2464, LoadingStageNumber);
				} else if(5 == Class143.loadingStage) {
					Class3_Sub23.method406((byte)117, false, Class168.aClass3_Sub28_Sub17_2096);
				} else if(Class143.loadingStage == 10) {
					Class3_Sub17.method381(true);
				} else if (25 != Class143.loadingStage && Class143.loadingStage != 28) {
					if (Class143.loadingStage == 30) {
						Class49.method1127(0);
					} else if (40 == Class143.loadingStage) {
						Class3_Sub13.method164((byte) -95, false, RenderAnimationDefinition.method903(new RSString[]{TextCore.ConxLost, RSByteBuffer.aClass94_2598, TextCore.AttemptingReestablish}, (byte) -67));
					}
				} else if (Class163_Sub2_Sub1.anInt4019 == 1) {
					if (Class3_Sub13_Sub24.anInt3293 > Class3_Sub29.anInt2579) {
						Class3_Sub29.anInt2579 = Class3_Sub13_Sub24.anInt3293;
					}

					var4 = 50 * (Class3_Sub29.anInt2579 + -Class3_Sub13_Sub24.anInt3293) / Class3_Sub29.anInt2579;
					Class3_Sub13.method164((byte) -71, false, RenderAnimationDefinition.method903(new RSString[]{TextCore.LoadingPleaseWait2, Class3_Sub13_Sub33.aClass94_3399, Class72.method1298((byte) 9, var4), Class10.aClass94_148}, (byte) -62));
				} else if (Class163_Sub2_Sub1.anInt4019 == 2) {
					if (Class3_Sub5.anInt2275 < Class162.anInt2038) {
						Class3_Sub5.anInt2275 = Class162.anInt2038;
					}

					var4 = (-Class162.anInt2038 + Class3_Sub5.anInt2275) * 50 / Class3_Sub5.anInt2275 + 50;
					Class3_Sub13.method164((byte) -41, false, RenderAnimationDefinition.method903(new RSString[]{TextCore.LoadingPleaseWait2, Class3_Sub13_Sub33.aClass94_3399, Class72.method1298((byte) 9, var4), Class10.aClass94_148}, (byte) -68));
				} else {
					Class3_Sub13.method164((byte) -73, false, TextCore.LoadingPleaseWait2);
				}

				if(HDToolKit.highDetail && Class143.loadingStage != 0) {
					HDToolKit.method1826();

					for(var4 = 0; Class3_Sub28_Sub3.anInt3557 > var4; ++var4) {
						Class163_Sub1_Sub1.aBooleanArray4008[var4] = false;
					}
				} else {
					Graphics var11;
					if((Class143.loadingStage == 30 || 10 == Class143.loadingStage) && anInt3689 == 0 && !var10) {
						try {
							var11 = Class3_Sub28_Sub12.aCanvas3648.getGraphics();

							for(var5 = 0; Class3_Sub28_Sub3.anInt3557 > var5; ++var5) {
								if(Class163_Sub1_Sub1.aBooleanArray4008[var5]) {
									Class164_Sub1.aClass158_3009.drawGraphics(Class3_Sub28_Sub18.anIntArray3768[var5], Class155.anIntArray1969[var5], Class140_Sub4.anIntArray2794[var5], var11, Player.anIntArray3954[var5]);
									Class163_Sub1_Sub1.aBooleanArray4008[var5] = false;
								}
							}
						} catch (Exception var8) {
							Class3_Sub28_Sub12.aCanvas3648.repaint();
						}
					} else if(0 != Class143.loadingStage) {
						try {
							var11 = Class3_Sub28_Sub12.aCanvas3648.getGraphics();
							Class164_Sub1.aClass158_3009.method2179(var11);

							for(var5 = 0; var5 < Class3_Sub28_Sub3.anInt3557; ++var5) {
								Class163_Sub1_Sub1.aBooleanArray4008[var5] = false;
							}
						} catch (Exception var7) {
							Class3_Sub28_Sub12.aCanvas3648.repaint();
						}
					}
				}

				if(Class58.aBoolean913) {
					Class75_Sub3.method1346();
				}

				if(RSString.aBoolean2146 && 10 == Class143.loadingStage && Class3_Sub28_Sub12.anInt3655 != -1) {
					RSString.aBoolean2146 = false;
					Class119.method1730(Class38.aClass87_665);
				}

			}
		} catch (RuntimeException var9) {
			throw Class44.clientError(var9, "client.K(" + 40 + ')');
		}
	}

	static RSInterface method42(RSInterface var0) {
		int var1 = method44(var0).method94();
		if(var1 == 0) {
			return null;
		} else {
			for(int var2 = 0; var2 < var1; ++var2) {
				var0 = Class7.getRSInterface((byte)127, var0.parentId);
				if(var0 == null) {
					return null;
				}
			}

			return var0;
		}
	}

	public static void method43(boolean var0) {
		try {
			if(!var0) {
				aClass3_Sub11ArrayArray2199 = (Class3_Sub11[][])((Class3_Sub11[][])null);
			}

			anIntArray2200 = null;
			aClass130_2194 = null;
			aClass3_Sub11ArrayArray2199 = (Class3_Sub11[][])null;
		} catch (RuntimeException var2) {
			throw Class44.clientError(var2, "client.O(" + var0 + ')');
		}
	}

	final void method32() {
		try {
			if(HDToolKit.highDetail) {
				HDToolKit.method1842();
			}

			if(null != Class3_Sub13_Sub10.aFrame3121) {
				Class3_Sub28_Sub10_Sub1.method593(Class3_Sub13_Sub10.aFrame3121, Class38.aClass87_665);
				Class3_Sub13_Sub10.aFrame3121 = null;
			}

			if(null != Class38.aClass87_665) {
				Class38.aClass87_665.method1442(this.getClass(), 0);
			}

			if(null != Class106.aClass67_1443) {
				Class106.aClass67_1443.aBoolean1015 = false;
			}

			Class106.aClass67_1443 = null;
			if(Class3_Sub15.aClass89_2429 != null) {
				Class3_Sub15.aClass89_2429.close(14821);
				Class3_Sub15.aClass89_2429 = null;
			}

			Class163_Sub1_Sub1.method2215(Class3_Sub28_Sub12.aCanvas3648);
			Class130.method1783(4, Class3_Sub28_Sub12.aCanvas3648);
			if(null != Class38.aClass146_668) {
				Class38.aClass146_668.method2082(Class3_Sub28_Sub12.aCanvas3648);
			}

			Class3_Sub13_Sub1.method167(0);
			MouseListeningClass.method2090();
			Class38.aClass146_668 = null;
			if(null != WorldListEntry.aClass155_2627) {
				WorldListEntry.aClass155_2627.method2163();
			}

			if(null != Class3_Sub21.aClass155_2491) {
				Class3_Sub21.aClass155_2491.method2163();
			}

			Class58.aClass66_917.method1254();
			Class3_Sub13_Sub14.aClass73_3159.method1304();

			try {
				if(Class101.aClass30_1422 != null) {
					Class101.aClass30_1422.method980();
				}

				if(Class163_Sub2.aClass30Array2998 != null) {
					for(int var2 = 0; var2 < Class163_Sub2.aClass30Array2998.length; ++var2) {
						if(null != Class163_Sub2.aClass30Array2998[var2]) {
							Class163_Sub2.aClass30Array2998[var2].method980();
						}
					}
				}

				if(null != Class114.aClass30_1572) {
					Class114.aClass30_1572.method980();
				}

				if(null != Class69.aClass30_1039) {
					Class69.aClass30_1039.method980();
				}
			} catch (IOException var3) {
			}

		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "client.F(" + (byte) 23 + ')');
		}
	}

	public final void init() {
		try {
			GameLaunch.SETTINGS.setWorld(ObjectDefinition.worldId = Integer.parseInt(Objects.requireNonNull(this.getParameter("worldid"))));
			Class44.anInt718 = Integer.parseInt(Objects.requireNonNull(this.getParameter("modewhere")));
			if(0 > Class44.anInt718 || 1 < Class44.anInt718) {
				Class44.anInt718 = 0;
			}

			Class3_Sub13_Sub13.anInt3148 = Integer.parseInt(Objects.requireNonNull(this.getParameter("modewhat")));
			if(Class3_Sub13_Sub13.anInt3148 < 0 || Class3_Sub13_Sub13.anInt3148 > 2) {
				Class3_Sub13_Sub13.anInt3148 = 0;
			}

			String var1 = this.getParameter("advertsuppressed");
			Class3_Sub28_Sub19.aBoolean3779 = var1 != null && var1.equals("1");

			try {
				Class3_Sub20.language = Integer.parseInt(Objects.requireNonNull(this.getParameter("lang")));
			} catch (Exception var10) {
				Class3_Sub20.language = 0;
			}
			Class3.method87(-31, Class3_Sub20.language);

			String var2 = this.getParameter("objecttag");
			Class163_Sub2_Sub1.aBoolean4018 = var2 != null && var2.equals("1");

			String var3 = this.getParameter("js");
			Class3_Sub28_Sub11.aBoolean3641 = null != var3 && var3.equals("1");

			String var4 = this.getParameter("game");
			if(var4 != null && var4.equals("1")) {
				Class158.anInt2014 = 1;
			} else {
				Class158.anInt2014 = 0;
			}

			try {
				Class3_Sub26.anInt2554 = Integer.parseInt(Objects.requireNonNull(this.getParameter("affid")));
			} catch (Exception var9) {
				Class3_Sub26.anInt2554 = 0;
			}

			Class163_Sub2.aClass94_2996 = Class133.aClass94_1745.method1573((byte)126, this);
			if(Class163_Sub2.aClass94_2996 == null) {
				Class163_Sub2.aClass94_2996 = Class3_Sub28_Sub14.aClass94_3672;
			}

			String var5 = this.getParameter("country");
			if(var5 != null) {
				try {
					Class3_Sub31.countryId = Integer.parseInt(var5);
				} catch (Exception var8) {
					Class3_Sub31.countryId = 0;
				}
			}

			String var6 = this.getParameter("haveie6");
			Class106.hasInternetExplorer6 = null != var6 && var6.equals("1");

			Class126.aClient1671 = this;
			this.method41(32 - -Class3_Sub13_Sub13.anInt3148);
		} catch (RuntimeException var11) {
			throw Class44.clientError(var11, "client.init()");
		}
	}

	final void method39() {
		try {
			Class119.method1729();
			Class3_Sub13_Sub14.aClass73_3159 = new Class73();
			Class58.aClass66_917 = new Class66();
			if(Class3_Sub13_Sub13.anInt3148 != 0) {
				Class3_Sub6.aByteArrayArray2287 = new byte[50][];
			}

			CS2Script.anInt2451 = ObjectDefinition.worldId;
			Class3_Sub28_Sub7.method564(Class38.aClass87_665);
			System.out.println("port = " + Class53.anInt867);
			System.out.println("Here: Config.MSIP = " + Configurations.MS_IP);
			if(Class44.anInt718 == 0) {
				ClientErrorException.worldListHost = Objects.requireNonNull(this.getCodeBase()).getHost();
				Class53.anInt867 = 43594 + ObjectDefinition.worldId; //443 is secure port
				Class3_Sub28_Sub19.anInt3773 = '\uaa4a';
			} else if (Class44.anInt718 == 1) {
				ClientErrorException.worldListHost = Objects.requireNonNull(this.getCodeBase()).getHost();
				//System.out.println("port = " + Class53.anInt867);
				Class53.anInt867 = ObjectDefinition.worldId + 50000;
				Class3_Sub28_Sub19.anInt3773 = 40000 + ObjectDefinition.worldId;
			} else if (Class44.anInt718 == 2) {
				ClientErrorException.worldListHost = "127.0.0.1";
				System.out.println("Setting worldListHost to 127.0.0.1, port = " + Class53.anInt867);
				Class53.anInt867 = ObjectDefinition.worldId + '\uc350';
				Class3_Sub28_Sub19.anInt3773 = ObjectDefinition.worldId + '\u9c40';
			}
			if(1 == Class158.anInt2014) {
				Class101.aBoolean1419 = true;
				Class92.defaultScreenColorRgb = 16777215;
				Class92.defaulFogColorRgb = 0;
				Class15.aShortArrayArray344 = Class118.aShortArrayArray1619;
				Class101.aShortArrayArray1429 = Class75_Sub1.aShortArrayArray2634;
				Class3_Sub25.aShortArray2548 = Class2.aShortArray63;
				Class91.aShortArray1311 = Class3_Sub2.aShortArray2219;
			} else {
				Class15.aShortArrayArray344 = Class3_Sub28_Sub12.aShortArrayArray3654;
				Class91.aShortArray1311 = Class3_Sub13_Sub28.aShortArray3349;
				Class101.aShortArrayArray1429 = Class20.aShortArrayArray435;
				Class3_Sub25.aShortArray2548 = Class164_Sub1.aShortArray3011;
			}

			currentPort = Class53.anInt867;
			Class162.anInt2036 = Class3_Sub28_Sub19.anInt3773;
			Class38_Sub1.accRegistryIp = Configurations.MS_IP;
			Class123.anInt1658 = Class3_Sub28_Sub19.anInt3773;
			Class3_Sub13_Sub38.aShortArray3455 = Class3_Sub13_Sub9.aShortArray3110 = Class136.aShortArray1779 = Class3_Sub13_Sub38.aShortArray3453 = new short[256];

			Class140_Sub6.accRegistryPort = Class123.anInt1658;
			if(Signlink.anInt1214 == 3 && 2 != Class44.anInt718) {
				CS2Script.anInt2451 = ObjectDefinition.worldId;
			}

			Class3_Sub22.method402();
			Class3_Sub13_Sub4.method193((byte)115, Class3_Sub28_Sub12.aCanvas3648);
			ItemDefinition.method1119(Class3_Sub28_Sub12.aCanvas3648, false);
			Class38.aClass146_668 = Class21.method916();
			if(null != Class38.aClass146_668) {
				Class38.aClass146_668.method2084(Class3_Sub28_Sub12.aCanvas3648, -97);
			}

			try {
				if(Class38.aClass87_665.aClass122_1198 != null) {
					Class101.aClass30_1422 = new Class30(Class38.aClass87_665.aClass122_1198, 5200);

					for(int var2 = 0; var2 < 29; ++var2) {
						Class163_Sub2.aClass30Array2998[var2] = new Class30(Class38.aClass87_665.aClass122Array1197[var2], 6000);
					}

					Class114.aClass30_1572 = new Class30(Class38.aClass87_665.aClass122_1204, 6000);
					Class86.aClass41_1186 = new Class41(255, Class101.aClass30_1422, Class114.aClass30_1572, 500000);
					Class69.aClass30_1039 = new Class30(Class38.aClass87_665.aClass122_1207, 24);
					Class38.aClass87_665.aClass122Array1197 = null;
					Class38.aClass87_665.aClass122_1204 = null;
					Class38.aClass87_665.aClass122_1207 = null;
					Class38.aClass87_665.aClass122_1198 = null;
				}
			} catch (IOException var3) {
				Class69.aClass30_1039 = null;
				Class101.aClass30_1422 = null;
				Class114.aClass30_1572 = null;
				Class86.aClass41_1186 = null;
			}

			Class167.aClass94_2083 = TextCore.RSLoadingPleaseWait;
			if(Class44.anInt718 != 0) {
				Class20.aBoolean438 = true;
			}
			//Class3_Sub26.aBoolean2558  = true;
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "client.B(" + 2 + ')');
		}
	}

	// This seems to invalidate a bunch of stuff.
	final void method33() {
		try {
			method43(true);
			RSString.method1541(-8635);
			Class38.method1024(21474);
			Class3_Sub28_Sub3.method542((byte)-46);
			Class131.method1792(0);
			GameShell.method26(113);
			HDToolKit.method1838();
			Class129.method1766(24241);
			Class158.method2181(false);
			Class67.method1257(25951);
			Class96.method1588((byte)106);
			RSByteBuffer.method767(0);
			IOHandler.method1463(0);
			Class66.method1242((byte)-88);
			Class73.method1306(-16222);
			Class8.method836(-114);
			Class151_Sub1.method2105(false);
			CacheIndex.method2119(100);
			Class30.method974(true);
			Class41.method1049(true);
			Class93.method1521(3101);
			NPC.method1983(-3);
			Class3_Sub30_Sub1.method808(1);
			Class91.method1491((byte)-124);
			RSInterface.method860(126);
			Class106.method1644((byte)121);
			Player.method1982((byte)121);
			Class61.method1217(0);
			Class3_Sub28_Sub16.method634((byte)108);
			Class130.method1774(103);
			Class146.method2081(0);
			Class3_Sub19.method387(103);
			Class133.method1802(25);
			Class3_Sub24_Sub4.method491((byte)85);
			Class155.method2165(0);
			Class157.method2175((byte)-110);
			Class52.method1168(8160);
			AnimationDefinition.method2057((byte)-108);
			Class140_Sub4.method1974((byte)-116);
			Class3_Sub2.method102(3353893);
			ObjectDefinition.method1687(-11);
			Class3_Sub4.method109(2);
			NPCDefinition.method1473((byte)103);
			Class3_Sub31.method821(26971);
			Class3_Sub1.method91((byte)120);
			Class3.method83((byte)30);
			NodeList.method875((byte)106);
			ISAACCipher.method1231(119);
			Class95.method1582(3);
			Class3_Sub28_Sub5.method558(-29679);
			Class145.method2071((byte)59);
			Class46.method1085(-1);
			Class132.method1800((byte)104);
			Class14.method886((byte)35);
			Class119.method1728(-14256);
			Class127.method1754(-79);
			Class17.method905(-24912);
			Class128.method1761((byte)-55);
			ClientErrorException.method2288(false);
			Class20.method908(5157);
			Class167.method2262((byte)126);
			Class3_Sub28_Sub10_Sub1.method592((byte)38);
			Class3_Sub28_Sub10_Sub2.method597((byte)108);
			Class99.method1598(-126);
			Class84.method1422((byte)24);
			Class92.method1507();
			Class40.method1042(true);
			Model_Sub1.method1990();
			Class136.method1815((byte)-45);
			Class140_Sub1_Sub1.method1915();
			ItemDefinition.method1111(3327);
			Class140_Sub1_Sub2.method1948();
			KeyboardListener.method2085(118);
			MouseListeningClass.method2088(true);
			Class23.method937(0);
			Class83.method1414(90);
			Class31.method987();
			Class15.method892(100);
			Canvas_Sub1.method53(0);
			Class162.method2205(-17413);
			AbstractIndexedSprite.method1663(33);
			Class3_Sub28_Sub17.method689();
			Class85.method1426(-25247);
			Class3_Sub22.method399(186);
			Class78.method1369();
			Class3_Sub24_Sub3.method463(-28918);
			Class3_Sub15.method372(true);
			Class38_Sub1.method1032(false);
			Class33.method999();
			Class68.method1274();
			Class43.method1059((byte)-2);
			Class151.method2093(1);
			Class62.method1223(0);
			Class3_Sub28_Sub10.method588((byte)120);
			Class143.method2063(0);
			Class74.method1333();
			PacketParser.method828(-90);
			Class49.method1130(99);
			Class3_Sub28_Sub9.method584(0);
			Node.method521(-3);
			Class47.method1096((byte)89);
			Class168.method2276(-2);
			Class139.method1858(-17124);
			Class100.method1606((byte)-48);
			Class24.method943(-9893);
			Class3_Sub28_Sub11.method605(221301966);
			Class117.method1721(true);
			Class115.method1712(69);
			RenderAnimationDefinition.method896(true);
			GraphicDefinition.method964(6);
			Class79.method1388(true);
			Class29.method973((byte)62);
			Class3_Sub28_Sub12.method613(119);
			Class3_Sub28_Sub13.method624(-1);
			Class3_Sub28_Sub4.method547(-2951);
			Class7.method833((byte)126);
			Class3_Sub28_Sub1.method528(-1667);
			Class57.method1192((byte)-86);
			Class55.method1181((byte)-118);
			Class2.method80(-27401);
			Class102.method1612(-11565);
			Class3_Sub28_Sub20.method721(20413);
			Class3_Sub28_Sub18.method711(1);
			Class51.method1155();
			Class36.method1016((byte)127);
			Class1.method71((byte)-124);
			Class101.method1608((byte)110);
			Class53.method1169(false);
			WorldListEntry.method1077(0);
			ItemDefinition.pagedRamPageSize = null;
			Class15.timeZone = null;
			Class88.method1457();
			Class137.method1818(false);
			Class107.aClass153_878 = null;
			Class10.method853(0);
			CS2Script.method376(false);
			GameObject.method1860(0);
			Class86.method1429((byte)53);
			Class3_Sub11.method147();
			Class25.method954(128);
			Class113.method1703(10967);
			Class70.method1284((byte)-87);
			Class19.method906((byte)112);
			Class12.method871((byte)115);
			Class72.method1296(1);
			Class126.method1751((byte)-58);
			Class35.method1011();
			Class3_Sub17.method380(-29113);
			Class56.method1187(30351);
			Class58.method1193(-26723);
			AnimationHeader.method1595();
			Class123.method1743(false);
			Class3_Sub28_Sub21.method726();
			Class121.method1733(-17148);
			Class141.method2045();
			Class169.method2283();
			Class77.method1365(119);
			Class110.method1682(-82);
			Class3_Sub7.method120(1000);
			Class3_Sub28_Sub6.f((int)3);
			Class3_Sub25.method510(-128);
			Class3_Sub9.method136(-3);
			Class39.method1034(8642);
			method632();
			Class116.method1715();
			Class161.method2202(-196);
			Class81.method1402((byte)73);
			Class22.method923();
			Class45.method1081((byte)81);
			Class140_Sub6.method2019(true);
			Class140_Sub2.method1954(0);
			Class107.method1646(true);
			Class140_Sub7.method2030((byte)83);
			Class140_Sub3.method1958(2);
			Class124.method1744(true);
			Class80.method1394((byte)-94);
			Class3_Sub29.method735(-22749);
			Class134.method1806(3846);
			Class3_Sub18.method382(1);
			Class3_Sub21.method396(0);
			Canvas_Sub2.method59((byte)-87);
			Class108.method1660(13123);
			Class129_Sub1.method1771(14635);
			Class158_Sub1.method2187(27316);
			Class120.method1731(12881);
			Class50.method1133((byte)81);
			Class69.method1283((byte)122);
			Class144.method2070((byte)67);
			Class105.method1641();
			Class9.method849(2);
			Class3_Sub28_Sub7.method563(3);
			Class3_Sub28_Sub7_Sub1.method570(-119);
			Class118.method1726(0);
			Class3_Sub6.method118(2);
			Class166.method2255((byte)-128);
			Class155_Sub1.method2166();
			Class103.method1623();
			Class21.method911(26);
			Class154.method2145((byte)-69);
			Class125.method1748();
			Class112.method1700();
			Class104.method1630((byte)-113);
			Class65.method1238(-112);
			Class3_Sub14.method361();
			Class59.method1204();
			Class3_Sub13_Sub4.method187(false);
			Class159.method2197(true);
			Class3_Sub13.method156(2);
			Class164.method2235(4);
			Class97.method1592((byte)102);
			Class114.method1704(65536);
			Class3_Sub10.method143(-46);
			Class82.method1409(false);
			Class44.method1071((byte)-115);
			Class164_Sub2.method2245((byte)-74);
			Class164_Sub1.method2240(128);
			Class27.method962((byte)-67);
			Class3_Sub8.method131(-109);
			Class32.method994('\u93bd');
			Class3_Sub13_Sub22.method274(-2);
			Class3_Sub13_Sub11.method217(1);
			Class3_Sub13_Sub31.method317(7759444);
			Class3_Sub13_Sub29.method309(true);
			Class3_Sub13_Sub19.method261(-125);
			Class3_Sub13_Sub24.method288((byte)110);
			Class3_Sub13_Sub2.method172(11597);
			Class3_Sub13_Sub27.method296((byte)-107);
			Class3_Sub13_Sub39.method357(false);
			Class3_Sub13_Sub8.method206(true);
			Class3_Sub13_Sub37.method348(48);
			Class3_Sub13_Sub20.method266(-1443422260);
			Class3_Sub13_Sub1.method168(-1771542303);
			Class3_Sub13_Sub30.method315(-15028);
			Class3_Sub13_Sub32.method321(-21136);
			Class3_Sub13_Sub16.method245(0);
			Class3_Sub13_Sub9.method209((byte)79);
			Class3_Sub13_Sub15.method241((byte)74);
			Class3_Sub13_Sub23_Sub1.method287(false);
			Class3_Sub13_Sub23.method277((byte)-41);
			Class3_Sub13_Sub18.method258(-97);
			Class3_Sub13_Sub13.method234(-3);
			Class3_Sub13_Sub35.method337(2);
			Class3_Sub13_Sub17.method249(-127);
			Class3_Sub13_Sub12.method227(true);
			Class3_Sub13_Sub34.method333((byte)-54);
			Class3_Sub13_Sub6.method197(1);
			Class3_Sub13_Sub7.method200((byte)122);
			Class3_Sub13_Sub25.method290(-9);
			Class3_Sub13_Sub33.method325(0);
			Class3_Sub13_Sub10.method211(1024);
			Class3_Sub13_Sub14.method238(9423);
			Class3_Sub13_Sub28.method300(103);
			Class3_Sub13_Sub3.method177((byte)119);
			Class3_Sub13_Sub26.method294((byte)30);
			Class3_Sub13_Sub36.method341((byte)85);
			Class3_Sub13_Sub21.method268((byte)-91);
			Class3_Sub13_Sub38.method351(-1);
			Class163_Sub2.method2218((byte)-83);
			Class163.method2208(30358);
			Class163_Sub2_Sub1.method2225((byte)-120);
			Class163_Sub3.method2227((byte)37);
			Class163_Sub1_Sub1.method2213((byte)104);
			Class163_Sub1.method2212(false);
			Class3_Sub28_Sub14.method627((byte)-122);
			Class3_Sub28_Sub19.method717(109);
			Class3_Sub23.method405(true);
			InputStream_Sub1.method61(-93);
			OutputStream_Sub1.method67(true);
			Class3_Sub28_Sub8.method573(-11346);
			Class3_Sub20.method391(25);
			Class3_Sub26.method511((byte)121);
			Class75.method1334((byte)-115);
			Class75_Sub1.method1343(false);
			Class75_Sub3.method1348((byte)100);
			Class75_Sub4.method1350((byte)75);
			Class75_Sub2.method1345(-71);
		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "client.C(" + 126 + ')');
		}

	}

	static Class3_Sub1 method44(RSInterface var0) {
		Class3_Sub1 var1 = (Class3_Sub1)Class124.aClass130_1659.method1780(((long)var0.anInt279 << 32) + (long)var0.anInt191, 0);
		return var1 != null?var1:var0.aClass3_Sub1_257;
	}

	static AnimationDefinition getAnimationDefinition(int var0) {
		try {

			AnimationDefinition var2 = (AnimationDefinition)Class82.aClass93_1146.get((long)var0);
			if(var2 == null) {
				byte[] var3 = AnimationDefinition.aClass153_1860.getFile(Class129.method1765(var0, -1732504441), Class67.method1262(117, var0));
				var2 = new AnimationDefinition();
				var2.animId = var0;
				if(var3 != null) {
					var2.method2053(new RSByteBuffer(var3));
				}

				var2.method2058();
				Class82.aClass93_1146.put((byte)-103, var2, (long)var0);
			}
			return var2;
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "client.D(" + var0 + ',' + (byte) -20 + ')');
		}
	}

	private void method46(int var2) {
		try {
			++Class58.aClass66_917.anInt1011;
			Class17.aClass64_413 = null;

			Class58.aClass66_917.anInt1010 = var2;
			InputStream_Sub1.js5Connection = null;
			PacketParser.anInt80 = 0;
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "client.P(" + true + ',' + var2 + ')');
		}
	}

	private void method47() {
		try {
			for(Class3_Sub23.anInt2537 = 0; Class3_Sub28_Sub10_Sub1.method591(83) && Class3_Sub23.anInt2537 < 128; ++Class3_Sub23.anInt2537) {
				Class133.inputTextCodeArray[Class3_Sub23.anInt2537] = Class3_Sub28_Sub9.anInt3624;
				Class120.anIntArray1638[Class3_Sub23.anInt2537] = Class3_Sub13_Sub27.anInt3342;
			}

			++Class106.anInt1446;
			if(-1 != Class3_Sub28_Sub12.anInt3655) {
				GraphicDefinition.method967(0, 0, 0, Class23.anInt454, Class3_Sub28_Sub12.anInt3655, 0, Class140_Sub7.anInt2934);
			}

			++Class3_Sub13_Sub17.anInt3213;
			if(HDToolKit.highDetail) {
				int var2 = 19137023;

				label191:
					for(int var3 = 0; var3 < 32768; ++var3) {
						NPC var4 = Class3_Sub13_Sub24.npcs[var3];
						if(null != var4) {
							byte var5 = var4.definition.aByte1267;
							if((var5 & 2) > 0 && var4.anInt2816 == 0 && 10.0D > Math.random() * 1000.0D) {
								int var6 = (int)Math.round(-1.0D + 2.0D * Math.random());
								int var7 = (int)Math.round(Math.random() * 2.0D - 1.0D);
								if(var6 != 0 || 0 != var7) {
									var4.aByteArray2795[0] = 1;
									var4.anIntArray2767[0] = var6 + (var4.anInt2819 >> 7);
									var4.anIntArray2755[0] = var7 + (var4.anInt2829 >> 7);
									Class86.aClass91Array1182[WorldListCountry.localPlane].method1502(var4.anInt2819 >> 7, var4.getSize(), false, 0, var4.getSize(), var4.anInt2829 >> 7);
									if(0 <= var4.anIntArray2767[0] && var4.anIntArray2767[0] <= 104 + -var4.getSize() && 0 <= var4.anIntArray2755[0] && var4.anIntArray2755[0] <= 104 - var4.getSize() && Class86.aClass91Array1182[WorldListCountry.localPlane].method1500(var4.anInt2829 >> 7, var4.anIntArray2755[0], var4.anIntArray2767[0], var4.anInt2819 >> 7)) {
										if(var4.getSize() > 1) {
											for(int var8 = var4.anIntArray2767[0]; var8 < var4.anIntArray2767[0] - -var4.getSize(); ++var8) {
												for(int var9 = var4.anIntArray2755[0]; var4.anIntArray2755[0] + var4.getSize() > var9; ++var9) {
													if((var2 & Class86.aClass91Array1182[WorldListCountry.localPlane].anIntArrayArray1304[var8][var9]) != 0) {
														continue label191;
													}
												}
											}
										}

										var4.anInt2816 = 1;
									}
								}
							}

							Class55.method1180((byte)-122, var4);
							Class17.method904(65536, var4);
							RenderAnimationDefinition.method900(var4, (byte) 1 ^ -11974);
							Class86.aClass91Array1182[WorldListCountry.localPlane].method1489(var4.anInt2819 >> 7, false, (byte)85, var4.anInt2829 >> 7, var4.getSize(), var4.getSize());
						}
					}
			}

			if(!HDToolKit.highDetail) {
				RSByteBuffer.method744();
			} else if(0 == Class3_Sub13_Sub25.loginStage && 0 == Canvas_Sub1.registryStage) {
				if(Class133.anInt1753 == 2) {
					CS2Script.method379();
				} else {
					Class3_Sub28_Sub6.d('\uffff');
				}

				if(14 > NPC.anInt3995 >> 7 || NPC.anInt3995 >> 7 >= 90 || 14 > Class77.anInt1111 >> 7 || Class77.anInt1111 >> 7 >= 90) {
					Class3_Sub13_Sub6.method195();
				}
			}

			while(true) {
				CS2Script var11 = (CS2Script)PacketParser.aClass61_82.method1220();
				RSInterface var12;
				RSInterface var13;
				if(var11 == null) {
					while(true) {
						var11 = (CS2Script)Class65.aClass61_983.method1220();
						if(null == var11) {
							while(true) {
								var11 = (CS2Script)Class110.aClass61_1471.method1220();
								if(null == var11) {
									if(Class56.aClass11_886 != null) {
										PacketParser.method829();
									}

									if(null != Class15.aClass64_351 && Class15.aClass64_351.anInt978 == 1) {
										if(null != Class15.aClass64_351.anObject974) {
											Class99.method1596(Class3_Sub13_Sub24.aClass94_3295, (byte)126, RSString.aBoolean2154);
										}

										RSString.aBoolean2154 = false;
										Class3_Sub13_Sub24.aClass94_3295 = null;
										Class15.aClass64_351 = null;
									}

									if(Class44.anInt719 % 1500 == 0) {
										Class72.method1293();
									}

									return;
								}

								var12 = var11.aClass11_2449;
								if(0 <= var12.anInt191) {
									var13 = Class7.getRSInterface((byte)118, var12.parentId);
									if(var13 == null || null == var13.aClass11Array262 || var12.anInt191 >= var13.aClass11Array262.length || var12 != var13.aClass11Array262[var12.anInt191]) {
										continue;
									}
								}

								Class43.method1065(var11);
							}
						}

						var12 = var11.aClass11_2449;
						if(var12.anInt191 >= 0) {
							var13 = Class7.getRSInterface((byte)112, var12.parentId);
							if(null == var13 || var13.aClass11Array262 == null || var13.aClass11Array262.length <= var12.anInt191 || var12 != var13.aClass11Array262[var12.anInt191]) {
								continue;
							}
						}

						Class43.method1065(var11);
					}
				}

				var12 = var11.aClass11_2449;
				if(var12.anInt191 >= 0) {
					var13 = Class7.getRSInterface((byte)126, var12.parentId);
					if(null == var13 || null == var13.aClass11Array262 || var12.anInt191 >= var13.aClass11Array262.length || var12 != var13.aClass11Array262[var12.anInt191]) {
						continue;
					}
				}

				Class43.method1065(var11);
			}
		} catch (RuntimeException var10) {
			throw Class44.clientError(var10, "client." + (byte) 1 + ')');
		}
	}

	private void method48() {
		try {
			boolean var2 = Class58.aClass66_917.method1243();
			if(!var2) {
				this.method49();
			}

		} catch (RuntimeException var3) {
			throw Class44.clientError(var3, "client.J(" + true + ')');
		}
	}

	private void method49() {
		try {

			if(Class58.aClass66_917.anInt1011 > Class163_Sub2_Sub1.anInt4026) {
				Class3_Sub13_Sub5.anInt3068 = 5 * 50 * (Class58.aClass66_917.anInt1011 + -1);
				if(Class162.anInt2036 == Class140_Sub6.accRegistryPort) {
					Class140_Sub6.accRegistryPort = currentPort;
				} else {
					Class140_Sub6.accRegistryPort = Class162.anInt2036;
				}

				if(Class3_Sub13_Sub5.anInt3068 > 3000) {
					Class3_Sub13_Sub5.anInt3068 = 3000;
				}

                if(Class58.aClass66_917.anInt1011 >= 2 && Class58.aClass66_917.anInt1010 == 6) {
                    this.method31("js5connect_outofdate");
                    Class143.loadingStage = 1000;
                    return;
                }

                if(Class58.aClass66_917.anInt1011 >= 4 && Class58.aClass66_917.anInt1010 == -1) {
                    this.method31("js5crc");
                    Class143.loadingStage = 1000;
                    return;
                }

                if(Class58.aClass66_917.anInt1011 >= 4 && (Class143.loadingStage == 0 || Class143.loadingStage == 5)) {
                    if(Class58.aClass66_917.anInt1010 == 7 || Class58.aClass66_917.anInt1010 == 9) {
                        this.method31("js5connect_full");
                    } else if (Class58.aClass66_917.anInt1010 > 0) {
						this.method31("js5connect");
					} else {
						this.method31("js5io");
					}

					Class143.loadingStage = 1000;
                    return;
                }
            }

            Class163_Sub2_Sub1.anInt4026 = Class58.aClass66_917.anInt1011;
            if(Class3_Sub13_Sub5.anInt3068 > 0) {
                --Class3_Sub13_Sub5.anInt3068;
            } else {
                try {
                    if(PacketParser.anInt80 == 0) {
                        Class17.aClass64_413 = Class38.aClass87_665.method1441((byte)8, Class38_Sub1.accRegistryIp, Class140_Sub6.accRegistryPort);
                        ++PacketParser.anInt80;
                    }

                    if(PacketParser.anInt80 == 1) {
                        if(2 == Objects.requireNonNull(Class17.aClass64_413).anInt978) {
                            this.method46(1000);
                            return;
                        }

                        if(Class17.aClass64_413.anInt978 == 1) {
                            ++PacketParser.anInt80;
                        }
                    }

                    if(2 == PacketParser.anInt80) {
                        InputStream_Sub1.js5Connection = new IOHandler((Socket) Objects.requireNonNull(Class17.aClass64_413).anObject974, Class38.aClass87_665);
                        RSByteBuffer var2 = new RSByteBuffer(9);
                        var2.putByte((byte)-69, 15); //JS5 handshake
                        var2.putInt(-31379 + 31252, Configurations.CLIENT_BUILD);
                        var2.putInt(-31379 + 31252, Configurations.SUB_BUILD);
                        InputStream_Sub1.js5Connection.sendBytes(var2.buffer, 9);
                        ++PacketParser.anInt80;
                        Class3_Sub13_Sub30.aLong3366 = Class5.method830((byte)-55);
                    }

					if(3 == PacketParser.anInt80) {
						if(Class143.loadingStage != 0 && Class143.loadingStage != 5 && 0 >= InputStream_Sub1.js5Connection.availableBytes(-31379 ^ 15655)) {
							if(Class5.method830((byte) -55) + -Class3_Sub13_Sub30.aLong3366 > 30000) {
								this.method46(1001);
								return;
							}
						} else {
							int var5 = InputStream_Sub1.js5Connection.readByte(0);
							if(var5 != 0) {
								this.method46(var5);
								return;
							}

							++PacketParser.anInt80;
						}
					}

					if(PacketParser.anInt80 == 4) {
						boolean var6 = Class143.loadingStage == 5 || Class143.loadingStage == 10 || Class143.loadingStage == 28;
						Class58.aClass66_917.method1249(!var6, InputStream_Sub1.js5Connection);
						InputStream_Sub1.js5Connection = null;
						Class17.aClass64_413 = null;
						PacketParser.anInt80 = 0;
					}
				} catch (IOException var3) {
					this.method46(1002);
				}

			}
		} catch (RuntimeException var4) {
			throw Class44.clientError(var4, "client.E(" + -31379 + ')');
		}
	}

	public static void main(String[] var0) {
		try {
			try {
				if(4 != var0.length) {
					Class3_Sub13_Sub23_Sub1.method283("argument count");
				}

				int var1 = -1;
				ObjectDefinition.worldId = Integer.parseInt(var0[0]);
				Class44.anInt718 = 2;
				if(var0[1].equals("live")) {
					Class3_Sub13_Sub13.anInt3148 = 0;
				} else if (var0[1].equals("rc")) {
					Class3_Sub13_Sub13.anInt3148 = 1;
				} else if (var0[1].equals("wip")) {
					Class3_Sub13_Sub13.anInt3148 = 2;
				} else {
					Class3_Sub13_Sub23_Sub1.method283("modewhat");
				}

				Class3_Sub28_Sub19.aBoolean3779 = false;

				try {
					byte[] var2 = var0[2].getBytes(StandardCharsets.ISO_8859_1);
					var1 = Class3_Sub13_Sub16.method243(Class3_Sub13_Sub3.method178(var2, var2.length, 0));
				} catch (Exception var3) {
				}

				if(-1 == var1) {
					if(var0[2].equals("english")) {
						Class3_Sub20.language = 0;
					} else if (var0[2].equals("german")) {
						Class3_Sub20.language = 1;
					} else {
						Class3_Sub13_Sub23_Sub1.method283("language");
					}
				} else {
					Class3_Sub20.language = var1;
				}

				Class3.method87(-78, Class3_Sub20.language);
				Class163_Sub2_Sub1.aBoolean4018 = false;
				Class3_Sub28_Sub11.aBoolean3641 = false;
				if(var0[3].equals("game0")) {
					Class158.anInt2014 = 0;
				} else if (var0[3].equals("game1")) {
					Class158.anInt2014 = 1;
				} else {
					Class3_Sub13_Sub23_Sub1.method283("game");
				}

				Class3_Sub31.countryId = 0;
				Class106.hasInternetExplorer6 = false;
				Class3_Sub26.anInt2554 = 0;
				Class163_Sub2.aClass94_2996 = Class3_Sub28_Sub14.aClass94_3672;
				Client var6 = new Client();
				Class126.aClient1671 = var6;
				var6.launch();
				GameShell.frame.setLocation(40, 40);
			} catch (Exception var4) {
				Class49.method1125((String)null, var4, (byte)119);
			}

		} catch (RuntimeException var5) {
			throw Class44.clientError(var5, "client.main(" + (var0 != null?"{...}":"null") + ')');
		}
	}

	static void handleItemSwitch(RSInterface[] interfaces, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
		for(int index = 0; index < interfaces.length; ++index) {
			RSInterface inter = interfaces[index];
			if(inter != null && inter.parentId == var1 && (!inter.usingScripts || inter.type == 0 || inter.aBoolean195 || method44(inter).anInt2205 != 0 || inter == PacketParser.aClass11_88 || inter.anInt189 == 1338) && (!inter.usingScripts || !method51(inter))) {
				int var10 = inter.anInt306 + var6;
				int var11 = inter.anInt210 + var7;
				int var12;
				int var13;
				int var14;
				int var15;
				if(inter.type == 2) {
					var12 = var2;
					var13 = var3;
					var14 = var4;
					var15 = var5;
				} else {
					int var16 = var10 + inter.anInt168;
					int var17 = var11 + inter.anInt193;
					if(inter.type == 9) {
						++var16;
						++var17;
					}

					var12 = var10 > var2?var10:var2;
					var13 = var11 > var3?var11:var3;
					var14 = var16 < var4?var16:var4;
					var15 = var17 < var5?var17:var5;
				}

				if(inter == Class56.aClass11_886) {
					Class21.aBoolean440 = true;
					Class3_Sub15.anInt2421 = var10;
					Class3_Sub2.anInt2218 = var11;
				}

				if(!inter.usingScripts || var12 < var14 && var13 < var15) {
					if(inter.type == 0) {
						if(!inter.usingScripts && method51(inter) && Class107.aClass11_1453 != inter) {
							continue;
						}

						if(inter.aBoolean219 && Class126.anInt1676 >= var12 && Class130.anInt1709 >= var13 && Class126.anInt1676 < var14 && Class130.anInt1709 < var15) {
							for(CS2Script var27 = (CS2Script)Class110.aClass61_1471.method1222(); var27 != null; var27 = (CS2Script)Class110.aClass61_1471.method1221()) {
								if(var27.aBoolean2446) {
									var27.method86(-1024);
									var27.aClass11_2449.aBoolean163 = false;
								}
							}

							if(Class75_Sub3.anInt2658 == 0) {
								Class56.aClass11_886 = null;
								PacketParser.aClass11_88 = null;
							}

							Class3_Sub19.anInt2475 = 0;
						}
					}

					if(inter.usingScripts) {
						boolean var26;
						var26 = Class126.anInt1676 >= var12 && Class130.anInt1709 >= var13 && Class126.anInt1676 < var14 && Class130.anInt1709 < var15;

						boolean var25 = false;
						if(Class3_Sub13_Sub5.anInt3069 == 1 && var26) {
							var25 = true;
						}

						boolean var18 = false;
						if(Class3_Sub28_Sub11.anInt3644 == 1 && Class163_Sub1.anInt2993 >= var12 && Class38_Sub1.anInt2614 >= var13 && Class163_Sub1.anInt2993 < var14 && Class38_Sub1.anInt2614 < var15) {
							var18 = true;
						}

						int var19;
						int var21;
						if(inter.aByteArray263 != null) {
							for(var19 = 0; var19 < inter.aByteArray263.length; ++var19) {
								if(!ObjectDefinition.aBooleanArray1490[inter.aByteArray263[var19]]) {
									if(inter.anIntArray310 != null) {
										inter.anIntArray310[var19] = 0;
									}
								} else if(inter.anIntArray310 == null || Class44.anInt719 >= inter.anIntArray310[var19]) {
									byte var20 = inter.aByteArray231[var19];
									if(var20 == 0 || ((var20 & 2) == 0 || ObjectDefinition.aBooleanArray1490[86]) && ((var20 & 1) == 0 || ObjectDefinition.aBooleanArray1490[82]) && ((var20 & 4) == 0 || ObjectDefinition.aBooleanArray1490[81])) {
										OutputStream_Sub1.method66(Class3_Sub28_Sub14.aClass94_3672, -1, var19 + 1, (byte)-29, inter.anInt279);
										var21 = inter.anIntArray299[var19];
										if(inter.anIntArray310 == null) {
											inter.anIntArray310 = new int[inter.aByteArray263.length];
										}

										if(var21 == 0) {
											inter.anIntArray310[var19] = Integer.MAX_VALUE;
										} else {
											inter.anIntArray310[var19] = Class44.anInt719 + var21;
										}
									}
								}
							}
						}

						if(var18) {
							Class3_Sub28_Sub6.a(Class38_Sub1.anInt2614 - var11, Class163_Sub1.anInt2993 - var10, 97, inter);
						}

						if(Class56.aClass11_886 != null && Class56.aClass11_886 != inter && var26 && (method44(inter).method98() || (inter.anInt279 == 49938505 && Class56.aClass11_886.anInt279 == 49938505))) {
							Class27.aClass11_526 = inter;
						}
						if(inter == PacketParser.aClass11_88) {
							Class85.aBoolean1167 = true;
							Class3_Sub13_Sub13.anInt3156 = var10;
							Class134.anInt1761 = var11;
						}

						if(inter.aBoolean195 || inter.anInt189 != 0) {
							CS2Script var30;
							if(var26 && Class29.anInt561 != 0 && inter.anObjectArray183 != null) {
								var30 = new CS2Script();
								var30.aBoolean2446 = true;
								var30.aClass11_2449 = inter;
								var30.scrollbarScrollAmount = Class29.anInt561;
								var30.arguments = inter.anObjectArray183;
								Class110.aClass61_1471.method1215(var30);
							}

							if(Class56.aClass11_886 != null || Class67.aClass11_1017 != null || Class38_Sub1.aBoolean2615 || inter.anInt189 != 1400 && Class3_Sub19.anInt2475 > 0) {
								var18 = false;
								var25 = false;
								var26 = false;
							}

							int var29;
							if(inter.anInt189 != 0) {
								if(inter.anInt189 == 1337) {
									Class168.aClass11_2091 = inter;
									Class20.method909(124, inter);
									continue;
								}

								if(inter.anInt189 == 1338) {
									if(var18) {
										Class1.anInt56 = Class163_Sub1.anInt2993 - var10;
										Class58.anInt916 = Class38_Sub1.anInt2614 - var11;
									}
									continue;
								}

								if(inter.anInt189 == 1400) {
									Class3_Sub28_Sub3.aClass11_3551 = inter;
									if(var18) {
										if(ObjectDefinition.aBooleanArray1490[82] && Class3_Sub13_Sub26.rights > 0) {
											var19 = (int)((double)(Class163_Sub1.anInt2993 - var10 - inter.anInt168 / 2) * 2.0D / (double)Class44.aFloat727);
											var29 = (int)((double)(Class38_Sub1.anInt2614 - var11 - inter.anInt193 / 2) * 2.0D / (double)Class44.aFloat727);
											var21 = Class3_Sub28_Sub1.anInt3536 + var19;
											int var32 = Class3_Sub4.anInt2251 + var29;
											int var23 = var21 + Class3_Sub13_Sub21.anInt3256;
											int var24 = Class108.anInt1460 - 1 - var32 + Class2.anInt65;
											Class30.method979(var23, var24, 0);
											Class3_Sub13_Sub19.method264((byte)126);
											continue;
										}

										Class3_Sub19.anInt2475 = 1;
										Class144.anInt1881 = Class126.anInt1676;
										Class95.anInt1336 = Class130.anInt1709;
										continue;
									}

									if(var25 && Class3_Sub19.anInt2475 > 0) {
										if(Class3_Sub19.anInt2475 == 1 && (Class144.anInt1881 != Class126.anInt1676 || Class95.anInt1336 != Class130.anInt1709)) {
											Class3_Sub28_Sub10_Sub2.anInt4073 = Class3_Sub28_Sub1.anInt3536;
											Class38.anInt660 = Class3_Sub4.anInt2251;
											Class3_Sub19.anInt2475 = 2;
										}

										if(Class3_Sub19.anInt2475 == 2) {
											method1175(Class3_Sub28_Sub10_Sub2.anInt4073 + (int)((double)(Class144.anInt1881 - Class126.anInt1676) * 2.0D / (double)NPC.aFloat3979));
											Class3_Sub13_Sub39.method354(Class38.anInt660 + (int)((double)(Class95.anInt1336 - Class130.anInt1709) * 2.0D / (double)NPC.aFloat3979));
										}
										continue;
									}

									Class3_Sub19.anInt2475 = 0;
									continue;
								}

								if(inter.anInt189 == 1401) {
									if(var25) {
										Class3_Sub13_Sub17.method253(inter.anInt168, Class130.anInt1709 - var11, Class126.anInt1676 - var10, inter.anInt193);
									}
									continue;
								}

								if(inter.anInt189 == 1402) {
									if(!HDToolKit.highDetail) {
										Class20.method909(113, inter);
									}
									continue;
								}
							}

							if(!inter.aBoolean188 && var18) {
								inter.aBoolean188 = true;
								if(inter.anObjectArray165 != null) {
									var30 = new CS2Script();
									var30.aBoolean2446 = true;
									var30.aClass11_2449 = inter;
									var30.worldSelectCursorPositionX = Class163_Sub1.anInt2993 - var10;
									var30.scrollbarScrollAmount = Class38_Sub1.anInt2614 - var11;
									var30.arguments = inter.anObjectArray165;
									Class110.aClass61_1471.method1215(var30);
								}
							}

							if(inter.aBoolean188 && var25 && inter.anObjectArray170 != null) {
								var30 = new CS2Script();
								var30.aBoolean2446 = true;
								var30.aClass11_2449 = inter;
								var30.worldSelectCursorPositionX = Class126.anInt1676 - var10;
								var30.scrollbarScrollAmount = Class130.anInt1709 - var11;
								var30.arguments = inter.anObjectArray170;
								Class110.aClass61_1471.method1215(var30);
							}

							if(inter.aBoolean188 && !var25) {
								inter.aBoolean188 = false;
								if(inter.anObjectArray239 != null) {
									var30 = new CS2Script();
									var30.aBoolean2446 = true;
									var30.aClass11_2449 = inter;
									var30.worldSelectCursorPositionX = Class126.anInt1676 - var10;
									var30.scrollbarScrollAmount = Class130.anInt1709 - var11;
									var30.arguments = inter.anObjectArray239;
									Class65.aClass61_983.method1215(var30);
								}
							}

							if(var25 && inter.anObjectArray180 != null) {
								var30 = new CS2Script();
								var30.aBoolean2446 = true;
								var30.aClass11_2449 = inter;
								var30.worldSelectCursorPositionX = Class126.anInt1676 - var10;
								var30.scrollbarScrollAmount = Class130.anInt1709 - var11;
								var30.arguments = inter.anObjectArray180;
								Class110.aClass61_1471.method1215(var30);
							}

							if(!inter.aBoolean163 && var26) {
								inter.aBoolean163 = true;
								if(inter.anObjectArray248 != null) {
									var30 = new CS2Script();
									var30.aBoolean2446 = true;
									var30.aClass11_2449 = inter;
									var30.worldSelectCursorPositionX = Class126.anInt1676 - var10;
									var30.scrollbarScrollAmount = Class130.anInt1709 - var11;
									var30.arguments = inter.anObjectArray248;
									Class110.aClass61_1471.method1215(var30);
								}
							}

							if(inter.aBoolean163 && var26 && inter.anObjectArray276 != null) {
								var30 = new CS2Script();
								var30.aBoolean2446 = true;
								var30.aClass11_2449 = inter;
								var30.worldSelectCursorPositionX = Class126.anInt1676 - var10;
								var30.scrollbarScrollAmount = Class130.anInt1709 - var11;
								var30.arguments = inter.anObjectArray276;
								Class110.aClass61_1471.method1215(var30);
							}

							if(inter.aBoolean163 && !var26) {
								inter.aBoolean163 = false;
								if(inter.anObjectArray281 != null) {
									var30 = new CS2Script();
									var30.aBoolean2446 = true;
									var30.aClass11_2449 = inter;
									var30.worldSelectCursorPositionX = Class126.anInt1676 - var10;
									var30.scrollbarScrollAmount = Class130.anInt1709 - var11;
									var30.arguments = inter.anObjectArray281;
									Class65.aClass61_983.method1215(var30);
								}
							}

							if(inter.anObjectArray269 != null) {
								var30 = new CS2Script();
								var30.aClass11_2449 = inter;
								var30.arguments = inter.anObjectArray269;
								PacketParser.aClass61_82.method1215(var30);
							}

							CS2Script var22;
							if(inter.anObjectArray161 != null && PacketParser.anInt87 > inter.anInt284) {
								if(inter.anIntArray211 != null && PacketParser.anInt87 - inter.anInt284 <= 32) {
									label531:
										for(var19 = inter.anInt284; var19 < PacketParser.anInt87; ++var19) {
											var29 = NPC.anIntArray3986[var19 & 31];

											for(var21 = 0; var21 < inter.anIntArray211.length; ++var21) {
												if(inter.anIntArray211[var21] == var29) {
													var22 = new CS2Script();
													var22.aClass11_2449 = inter;
													var22.arguments = inter.anObjectArray161;
													Class110.aClass61_1471.method1215(var22);
													break label531;
												}
											}
										}
								} else {
									var30 = new CS2Script();
									var30.aClass11_2449 = inter;
									var30.arguments = inter.anObjectArray161;
									Class110.aClass61_1471.method1215(var30);
								}

								inter.anInt284 = PacketParser.anInt87;
							}

							if(inter.anObjectArray221 != null && Class3_Sub9.anInt2317 > inter.anInt242) {
								if(inter.anIntArray185 != null && Class3_Sub9.anInt2317 - inter.anInt242 <= 32) {
									label512:
										for(var19 = inter.anInt242; var19 < Class3_Sub9.anInt2317; ++var19) {
											var29 = Class163_Sub2_Sub1.anIntArray4025[var19 & 31];

											for(var21 = 0; var21 < inter.anIntArray185.length; ++var21) {
												if(inter.anIntArray185[var21] == var29) {
													var22 = new CS2Script();
													var22.aClass11_2449 = inter;
													var22.arguments = inter.anObjectArray221;
													Class110.aClass61_1471.method1215(var22);
													break label512;
												}
											}
										}
								} else {
									var30 = new CS2Script();
									var30.aClass11_2449 = inter;
									var30.arguments = inter.anObjectArray221;
									Class110.aClass61_1471.method1215(var30);
								}

								inter.anInt242 = Class3_Sub9.anInt2317;
							}

							if(inter.anObjectArray282 != null && Class36.anInt641 > inter.anInt213) {
								if(inter.anIntArray286 != null && Class36.anInt641 - inter.anInt213 <= 32) {
									label493:
										for(var19 = inter.anInt213; var19 < Class36.anInt641; ++var19) {
											var29 = Class44.anIntArray726[var19 & 31];

											for(var21 = 0; var21 < inter.anIntArray286.length; ++var21) {
												if(inter.anIntArray286[var21] == var29) {
													var22 = new CS2Script();
													var22.aClass11_2449 = inter;
													var22.arguments = inter.anObjectArray282;
													Class110.aClass61_1471.method1215(var22);
													break label493;
												}
											}
										}
								} else {
									var30 = new CS2Script();
									var30.aClass11_2449 = inter;
									var30.arguments = inter.anObjectArray282;
									Class110.aClass61_1471.method1215(var30);
								}

								inter.anInt213 = Class36.anInt641;
							}

							if(inter.anObjectArray174 != null && Class62.anInt944 > inter.anInt255) {
								if(inter.anIntArray175 != null && Class62.anInt944 - inter.anInt255 <= 32) {
									label474:
										for(var19 = inter.anInt255; var19 < Class62.anInt944; ++var19) {
											var29 = Class3_Sub28_Sub4.anIntArray3565[var19 & 31];

											for(var21 = 0; var21 < inter.anIntArray175.length; ++var21) {
												if(inter.anIntArray175[var21] == var29) {
													var22 = new CS2Script();
													var22.aClass11_2449 = inter;
													var22.arguments = inter.anObjectArray174;
													Class110.aClass61_1471.method1215(var22);
													break label474;
												}
											}
										}
								} else {
									var30 = new CS2Script();
									var30.aClass11_2449 = inter;
									var30.arguments = inter.anObjectArray174;
									Class110.aClass61_1471.method1215(var30);
								}

								inter.anInt255 = Class62.anInt944;
							}

							if(inter.anObjectArray158 != null && Class49.anInt815 > inter.anInt311) {
								if(inter.anIntArray274 != null && Class49.anInt815 - inter.anInt311 <= 32) {
									label455:
										for(var19 = inter.anInt311; var19 < Class49.anInt815; ++var19) {
											var29 = Class3_Sub28_Sub19.anIntArray3780[var19 & 31];

											for(var21 = 0; var21 < inter.anIntArray274.length; ++var21) {
												if(inter.anIntArray274[var21] == var29) {
													var22 = new CS2Script();
													var22.aClass11_2449 = inter;
													var22.arguments = inter.anObjectArray158;
													Class110.aClass61_1471.method1215(var22);
													break label455;
												}
											}
										}
								} else {
									var30 = new CS2Script();
									var30.aClass11_2449 = inter;
									var30.arguments = inter.anObjectArray158;
									Class110.aClass61_1471.method1215(var30);
								}

								inter.anInt311 = Class49.anInt815;
							}

							if(Class24.anInt472 > inter.anInt234 && inter.anObjectArray256 != null) {
								var30 = new CS2Script();
								var30.aClass11_2449 = inter;
								var30.arguments = inter.anObjectArray256;
								Class110.aClass61_1471.method1215(var30);
							}

							if(Class110.anInt1472 > inter.anInt234 && inter.anObjectArray156 != null) {
								var30 = new CS2Script();
								var30.aClass11_2449 = inter;
								var30.arguments = inter.anObjectArray156;
								Class110.aClass61_1471.method1215(var30);
							}

							if(Class167.anInt2087 > inter.anInt234 && inter.anObjectArray313 != null) {
								var30 = new CS2Script();
								var30.aClass11_2449 = inter;
								var30.arguments = inter.anObjectArray313;
								Class110.aClass61_1471.method1215(var30);
							}

							if(Class121.anInt1642 > inter.anInt234 && inter.anObjectArray268 != null) {
								var30 = new CS2Script();
								var30.aClass11_2449 = inter;
								var30.arguments = inter.anObjectArray268;
								Class110.aClass61_1471.method1215(var30);
							}

							if(Class140_Sub6.anInt2905 > inter.anInt234 && inter.anObjectArray315 != null) {
								var30 = new CS2Script();
								var30.aClass11_2449 = inter;
								var30.arguments = inter.anObjectArray315;
								Class110.aClass61_1471.method1215(var30);
							}

							inter.anInt234 = Class3_Sub13_Sub17.anInt3213;
							if(inter.anObjectArray220 != null) {
								for(var19 = 0; var19 < Class3_Sub23.anInt2537; ++var19) {
									CS2Script var31 = new CS2Script();
									var31.aClass11_2449 = inter;
									var31.inputTextCode = Class133.inputTextCodeArray[var19];
									var31.anInt2443 = Class120.anIntArray1638[var19];
									var31.arguments = inter.anObjectArray220;
									Class110.aClass61_1471.method1215(var31);
								}
							}

							if(Class3_Sub28_Sub1.aBoolean3531 && inter.anObjectArray217 != null) {
								var30 = new CS2Script();
								var30.aClass11_2449 = inter;
								var30.arguments = inter.anObjectArray217;
								Class110.aClass61_1471.method1215(var30);
							}
						}
					}

					if(!inter.usingScripts && Class56.aClass11_886 == null && Class67.aClass11_1017 == null && !Class38_Sub1.aBoolean2615) {
						if((inter.anInt212 >= 0 || inter.anInt228 != 0) && Class126.anInt1676 >= var12 && Class130.anInt1709 >= var13 && Class126.anInt1676 < var14 && Class130.anInt1709 < var15) {
							if(inter.anInt212 >= 0) {
								Class107.aClass11_1453 = interfaces[inter.anInt212];
							} else {
								Class107.aClass11_1453 = inter;
							}
						}

						if(inter.type == 8 && Class126.anInt1676 >= var12 && Class130.anInt1709 >= var13 && Class126.anInt1676 < var14 && Class130.anInt1709 < var15) {
							Class20.aClass11_439 = inter;
						}

						if(inter.anInt252 > inter.anInt193) {
							Class137.method1819(Class130.anInt1709, inter.anInt193, inter, Class126.anInt1676, var10 + inter.anInt168, var11, inter.anInt252);
						}
					}

					if(inter.type == 0) {
						handleItemSwitch(interfaces, inter.anInt279, var12, var13, var14, var15, var10 - inter.anInt247, var11 - inter.anInt208);
						if(inter.aClass11Array262 != null) {
							handleItemSwitch(inter.aClass11Array262, inter.anInt279, var12, var13, var14, var15, var10 - inter.anInt247, var11 - inter.anInt208);
						}

						Class3_Sub31 var28 = (Class3_Sub31)Class3_Sub13_Sub17.aClass130_3208.method1780((long)inter.anInt279, 0);
						if(var28 != null) {
							GraphicDefinition.method967(var10, var13, var11, var14, var28.anInt2602, var12, var15);
						}
					}
				}
			}
		}

	}

	static boolean method51(RSInterface var0) {
		if(Class69.aBoolean1040) {
			if(method44(var0).anInt2205 != 0) {
				return false;
			}

			if(var0.type == 0) {
				return false;
			}
		}

		return var0.hidden;
	}

	private void method52(int var1) {
		try {
			if(!RSString.aBoolean2146) {
				while(Class3_Sub28_Sub10_Sub1.method591(107)) {
					if(Class3_Sub13_Sub27.anInt3342 == 115 || Class3_Sub13_Sub27.anInt3342 == 83) {
						RSString.aBoolean2146 = true;
					}
				}
			}

			if(var1 >= 46) {
				int var3;
				if(Class96.anInt1354 == 0) {
					Runtime var10 = Runtime.getRuntime();
					var3 = (int)((var10.totalMemory() - var10.freeMemory()) / 1024L);
					long var4 = Class5.method830((byte)-55);
					if(Class3_Sub13_Sub24.aLong3296 == 0) {
						Class3_Sub13_Sub24.aLong3296 = var4;
					}

					if(var3 > 16384 && 5000L > -Class3_Sub13_Sub24.aLong3296 + var4) {
						if(-Class91.aLong1310 + var4 > 1000L) {
							System.gc();
							Class91.aLong1310 = var4;
						}

						LoadingStageNumber = 5;
						Class3_Sub17.aClass94_2464 = TextCore.AllocatingMemory;
					} else {
						Class3_Sub17.aClass94_2464 = TextCore.AllocatedMemory;
						Class96.anInt1354 = 10;
						LoadingStageNumber = 5;
					}
				} else {
					int var2;
					if(Class96.anInt1354 == 10) {
						Class68.method1267();

						for(var2 = 0; var2 < 4; ++var2) {
							Class86.aClass91Array1182[var2] = new Class91();
						}

						LoadingStageNumber = 10;
						Class96.anInt1354 = 30;
						Class3_Sub17.aClass94_2464 = TextCore.CreatedWorld;
					} else if(Class96.anInt1354 == 30) {
						if(Class151.aClass8_1936 == null) {
							Class151.aClass8_1936 = new Class8(Class58.aClass66_917, Class3_Sub13_Sub14.aClass73_3159);
						}

						if(Class151.aClass8_1936.method837()) {
							Class75_Sub3.aClass153_2660 = Class8.getCacheIndex(false, true, true, 0);
							Class3_Sub28_Sub19.aClass153_3772 = Class8.getCacheIndex(false, true, true, 1);
							Class164.aClass153_2052 = Class8.getCacheIndex(true, true, false, 2);
							Class140_Sub3.aClass153_2727 = Class8.getCacheIndex(false, true, true, 3);
							Class146.aClass153_1902 = Class8.getCacheIndex(false, true, true, 4);
							Class3_Sub13_Sub6.aClass153_3077 = Class8.getCacheIndex(true, true, true, 5);
							Class75_Sub2.aClass153_2645 = Class8.getCacheIndex(true, false, true, 6);
							Class159.aClass153_2019 = Class8.getCacheIndex(false, true, true, 7);
							Class140_Sub6.spritesCacheIndex = Class8.getCacheIndex(false, true, true, 8);
							Class3_Sub13_Sub28.aClass153_3352 = Class8.getCacheIndex(false, true, true, 9);
							Class3_Sub13_Sub25.aClass153_3304 = Class8.getCacheIndex(false, true, true, 10);
							Node.aClass153_2573 = Class8.getCacheIndex(false, true, true, 11);
							Class3_Sub1.interfaceScriptsIndex = Class8.getCacheIndex(false, true, true, 12);
							CacheIndex.aClass153_1948 = Class8.getCacheIndex(false, true, true, 13);
							Class3_Sub19.aClass153_2474 = Class8.getCacheIndex(false, false, true, 14);
							NPC.aClass153_3994 = Class8.getCacheIndex(false, true, true, 15);
							Class168.aClass153_2097 = Class8.getCacheIndex(false, true, true, 16);
							NPC.aClass153_3993 = Class8.getCacheIndex(false, true, true, 17);
							Class101.aClass153_1428 = Class8.getCacheIndex(false, true, true, 18);
							Class100.aClass153_1410 = Class8.getCacheIndex(false, true, true, 19);
							Class3_Sub13_Sub36.aClass153_3429 = Class8.getCacheIndex(false, true, true, 20);
							Class70.aClass153_1058 = Class8.getCacheIndex(false, true, true, 21);
							Class3_Sub22.aClass153_2528 = Class8.getCacheIndex(false, true, true, 22);
							Class133.aClass153_1751 = Class8.getCacheIndex(true, true, true, 23);
							Class140_Sub7.aClass153_2939 = Class8.getCacheIndex(false, true, true, 24);
							Class3_Sub4.aClass153_2258 = Class8.getCacheIndex(false, true, true, 25);
							Class97.aClass153_1376 = Class8.getCacheIndex(true, true, true, 26);
							Class132.aClass153_1735 = Class8.getCacheIndex(false, true, true, 27);
							Class132.libIndex = Class8.getCacheIndex(false, true, true, 28);
							LoadingStageNumber = 15;
							Class3_Sub17.aClass94_2464 = TextCore.ConxUpdateServer;
							Class96.anInt1354 = 40;
						} else {
							Class3_Sub17.aClass94_2464 = TextCore.LoadingConnecting;
							LoadingStageNumber = 12;
						}
					} else if(Class96.anInt1354 == 40) {
						var2 = 0;

						for(var3 = 0; var3 < 29; ++var3) { //TODO
							var2 += RSByteBuffer.aClass151_Sub1Array2601[var3].method2111() * Class3_Sub13_Sub23.anIntArray3288[var3] / 100;
						}

						if(var2 < 100) { //!= 100
							if(var2 != 0) {
								Class3_Sub17.aClass94_2464 = RenderAnimationDefinition.method903(new RSString[]{TextCore.CheckingForUpdates, Class72.method1298((byte)9, var2), Class24.aClass94_468}, (byte)-93);
							}

							LoadingStageNumber = 20;
						} else {
							LoadingStageNumber = 20;
							Class3_Sub17.aClass94_2464 = TextCore.LoadedUpdateList;
							Class39.method1039(Class140_Sub6.spritesCacheIndex);
							Class97.method1593(111, Class140_Sub6.spritesCacheIndex);
							Class3_Sub13_Sub13.method233(28280, Class140_Sub6.spritesCacheIndex);
							Class96.anInt1354 = 45;
						}
					} else if (Class96.anInt1354 == 45) {
						Class140_Sub3.method1959(Class3_Sub13_Sub15.aBoolean3184);
						Class86.aClass3_Sub24_Sub4_1193 = new Class3_Sub24_Sub4();
						Class86.aClass3_Sub24_Sub4_1193.method479();
						WorldListEntry.aClass155_2627 = Class58.method1195(22050, Class38.aClass87_665, Class3_Sub28_Sub12.aCanvas3648, 0);
						WorldListEntry.aClass155_2627.method2154(-116, Class86.aClass3_Sub24_Sub4_1193);
						RenderAnimationDefinition.method897(17770, Class86.aClass3_Sub24_Sub4_1193, NPC.aClass153_3994, Class3_Sub19.aClass153_2474, Class146.aClass153_1902);
						Class3_Sub21.aClass155_2491 = Class58.method1195(2048, Class38.aClass87_665, Class3_Sub28_Sub12.aCanvas3648, 1);
						Class3_Sub26.aClass3_Sub24_Sub2_2563 = new Class3_Sub24_Sub2();
						Class3_Sub21.aClass155_2491.method2154(-128, Class3_Sub26.aClass3_Sub24_Sub2_2563);
						Class27.aClass157_524 = new Class157(22050, Class21.anInt443);
						KeyboardListener.anInt1912 = Class75_Sub2.aClass153_2645.getArchiveForName(Class1.aClass94_53);
						LoadingStageNumber = 30;
						Class96.anInt1354 = 50;
						Class3_Sub17.aClass94_2464 = TextCore.PreparedSoundEngine;
					} else if (Class96.anInt1354 == 50) {
						var2 = Class3_Sub13_Sub12.method228(Class140_Sub6.spritesCacheIndex, CacheIndex.aClass153_1948, false);
						var3 = 6;
						if (var3 <= var2) {
							Class3_Sub17.aClass94_2464 = TextCore.LoadedFonts;
							LoadingStageNumber = 35;
							Class96.anInt1354 = 60;
						} else {
							Class3_Sub17.aClass94_2464 = RenderAnimationDefinition.method903(new RSString[]{TextCore.LoadingFonts, Class72.method1298((byte) 9, 100 * var2 / var3), Class24.aClass94_468}, (byte) -59);
							LoadingStageNumber = 35;
						}
					} else if (60 == Class96.anInt1354) {
						var2 = Class3_Sub28_Sub11.method599(Class140_Sub6.spritesCacheIndex);
						var3 = 2;
						if (var3 <= var2) {
							Class3_Sub17.aClass94_2464 = TextCore.LoadedTitleScreen;
							Class96.anInt1354 = 65;
						} else {
							Class3_Sub17.aClass94_2464 = RenderAnimationDefinition.method903(new RSString[]{TextCore.LoadingTitleScreen, Class72.method1298((byte) 9, 100 * var2 / var3), Class24.aClass94_468}, (byte) -125);
						}
						LoadingStageNumber = 40;
					} else if (Class96.anInt1354 == 65) {
						Class3_Sub28_Sub9.method581(CacheIndex.aClass153_1948, Class140_Sub6.spritesCacheIndex);
						LoadingStageNumber = 45;
						Class3_Sub17.aClass94_2464 = TextCore.OpenedTitleScreen;
						Class117.method1719(5);
						Class96.anInt1354 = 70;
					} else if (Class96.anInt1354 == 70) {
						Class164.aClass153_2052.method2113((byte) 34);
						byte var7 = 0;
						var2 = var7 + Class164.aClass153_2052.method2136((byte) -124);
						Class168.aClass153_2097.method2113((byte) 120);
						var2 += Class168.aClass153_2097.method2136((byte) -128);
						Class132.libIndex.method2113((byte) 126);
						var2 += Class132.libIndex.method2136((byte) -124);
						NPC.aClass153_3993.method2113((byte) 70);
						var2 += NPC.aClass153_3993.method2136((byte) -123);
						Class101.aClass153_1428.method2113((byte) 32);
						var2 += Class101.aClass153_1428.method2136((byte) -127);
						Class100.aClass153_1410.method2113((byte) 48);
						var2 += Class100.aClass153_1410.method2136((byte) -128);
						Class3_Sub13_Sub36.aClass153_3429.method2113((byte) 43);
						var2 += Class3_Sub13_Sub36.aClass153_3429.method2136((byte) -122);
						Class70.aClass153_1058.method2113((byte) 23);
						var2 += Class70.aClass153_1058.method2136((byte) -122);
						Class3_Sub22.aClass153_2528.method2113((byte) 94);
						var2 += Class3_Sub22.aClass153_2528.method2136((byte) -128);
						Class140_Sub7.aClass153_2939.method2113((byte) 91);
						var2 += Class140_Sub7.aClass153_2939.method2136((byte) -126);
						Class3_Sub4.aClass153_2258.method2113((byte) 102);
						var2 += Class3_Sub4.aClass153_2258.method2136((byte) -128);
						Class132.aClass153_1735.method2113((byte) 126);
						var2 += Class132.aClass153_1735.method2136((byte) -124);
						if (var2 >= 1100) {
							Class132.method1799((byte) 96, Class164.aClass153_2052);
							method631(Class164.aClass153_2052);
							Class3_Sub28_Sub8.method575(Class164.aClass153_2052, -1);
							method375(Class159.aClass153_2019, Class164.aClass153_2052);
							Class108.method1661(Class168.aClass153_2097, Class159.aClass153_2019);
							ItemDefinition.method1103(Class159.aClass153_2019, Class101.aClass153_1428);
							GameObject.method1864(Class100.aClass153_1410, Class157.aClass3_Sub28_Sub17_Sub1_2000, Class159.aClass153_2019);
							Class29.method969(Class164.aClass153_2052, 59);
							Class3_Sub20.method392(Class3_Sub28_Sub19.aClass153_3772, Class3_Sub13_Sub36.aClass153_3429, Class75_Sub3.aClass153_2660);
							Class41.method1053(Class164.aClass153_2052);
							Class158.method2180(Class159.aClass153_2019, Class70.aClass153_1058);
							Class107.method1648(Class3_Sub22.aClass153_2528);
							Class3_Sub29.method731(Class164.aClass153_2052);
							Class3.method89(CacheIndex.aClass153_1948, Class140_Sub6.spritesCacheIndex, Class140_Sub3.aClass153_2727, Class159.aClass153_2019);
							Class3_Sub13_Sub17.method250(Class164.aClass153_2052);
							Class46.method1086(NPC.aClass153_3993);
							Class3_Sub13_Sub8.method205(Class3_Sub4.aClass153_2258, Class140_Sub7.aClass153_2939, new Class7());
							Class65.method1236(Class3_Sub4.aClass153_2258, Class140_Sub7.aClass153_2939);
							Class58.method1197(Class164.aClass153_2052);
							Class144.method2065(Class164.aClass153_2052, Class140_Sub6.spritesCacheIndex);
							Class107.method1645(Class164.aClass153_2052, Class140_Sub6.spritesCacheIndex);
							LoadingStageNumber = 50;
							Class3_Sub17.aClass94_2464 = TextCore.LoadedConfig;
							Class29.method968(128);
							Class96.anInt1354 = 80;
						} else {
							Class3_Sub17.aClass94_2464 = RenderAnimationDefinition.method903(new RSString[]{TextCore.LoadingConfig, Class72.method1298((byte) 9, var2 / 11), Class24.aClass94_468}, (byte) -80);
							LoadingStageNumber = 50;
						}
					} else if (Class96.anInt1354 == 80) {
						var2 = Class3_Sub4.method107(Class140_Sub6.spritesCacheIndex);
						var3 = 15;
						if (var2 < var3) {
							Class3_Sub17.aClass94_2464 = RenderAnimationDefinition.method903(new RSString[]{TextCore.LoadingSprites, Class72.method1298((byte) 9, var2 * 100 / var3), Class24.aClass94_468}, (byte) -66);
							LoadingStageNumber = 60;
						} else {
							Class14.method887(Class140_Sub6.spritesCacheIndex);
							Class96.anInt1354 = 90;
							LoadingStageNumber = 60;
							Class3_Sub17.aClass94_2464 = TextCore.LoadedSprites;
						}
					} else if (Class96.anInt1354 != 90) {
						if (Class96.anInt1354 == 100) {
							if (Class3_Sub13_Sub34.method334(Class140_Sub6.spritesCacheIndex)) {
								Class96.anInt1354 = 110;
							}
						} else if (Class96.anInt1354 == 110) {
							Class106.aClass67_1443 = new Class67();
							Class38.aClass87_665.method1451(0, 10, Class106.aClass67_1443);
							Class3_Sub17.aClass94_2464 = TextCore.LoadedInputHandler;
							LoadingStageNumber = 75;
							Class96.anInt1354 = 120;
						} else if (Class96.anInt1354 != 120) {
							if (Class96.anInt1354 == 130) {
								if (Class140_Sub3.aClass153_2727.method2113((byte) 20)) {
									if (Class3_Sub1.interfaceScriptsIndex.method2113((byte) 46)) {
										if (CacheIndex.aClass153_1948.method2113((byte) 89)) {
											if (Class133.aClass153_1751.method2127(Class95.aClass94_1342)) {
												Class75_Sub4.method1353(Class102.aClass3_Sub28_Sub16_Sub2Array2140, Class133.aClass153_1751);
												LoadingStageNumber = 95;
												Class3_Sub17.aClass94_2464 = TextCore.LoadedInterfaces;
												Class96.anInt1354 = 135;
											} else {
												Class3_Sub17.aClass94_2464 = RenderAnimationDefinition.method903(new RSString[]{TextCore.LoadingInterfaces, Class72.method1298((byte) 9, 90 - -(Class133.aClass153_1751.method2116(Class95.aClass94_1342) / 10)), Class24.aClass94_468}, (byte) -72);
												LoadingStageNumber = 85;
											}
										} else {
											Class3_Sub17.aClass94_2464 = RenderAnimationDefinition.method903(new RSString[]{TextCore.LoadingInterfaces, Class72.method1298((byte) 9, 85 - -(CacheIndex.aClass153_1948.method2136((byte) -124) / 20)), Class24.aClass94_468}, (byte) -107);
											LoadingStageNumber = 85;
										}
									} else {
										Class3_Sub17.aClass94_2464 = RenderAnimationDefinition.method903(new RSString[]{TextCore.LoadingInterfaces, Class72.method1298((byte) 9, 75 - -(Class3_Sub1.interfaceScriptsIndex.method2136((byte) -128) / 10)), Class24.aClass94_468}, (byte) -120);
										LoadingStageNumber = 85;
									}
								} else {
									Class3_Sub17.aClass94_2464 = RenderAnimationDefinition.method903(new RSString[]{TextCore.LoadingInterfaces, Class72.method1298((byte) 9, Class140_Sub3.aClass153_2727.method2136((byte) -123) * 3 / 4), Class24.aClass94_468}, (byte) -81);
									LoadingStageNumber = 85;
								}
							} else if (135 == Class96.anInt1354) {
								var2 = Class121.method1735();
								if (-1 == var2) {
									LoadingStageNumber = 95;
									Class3_Sub17.aClass94_2464 = TextCore.LoadingWLD;
								} else if (var2 == 7 || var2 == 9) {
									this.method31("worldlistfull");
									Class117.method1719(1000);
								} else if (Class30.loadedWorldList) {
									Class3_Sub17.aClass94_2464 = TextCore.LoadedWLD;
									Class96.anInt1354 = 140;
									LoadingStageNumber = 96;
								} else {
									this.method31("worldlistio_" + var2);
									Class117.method1719(1000);
								}
							} else if (Class96.anInt1354 == 140) {
								Class3_Sub22.anInt2529 = Class140_Sub3.aClass153_2727.getArchiveForName(RSString.createRSString("loginscreen"));
								Class3_Sub13_Sub6.aClass153_3077.method2115(-9, false);
								Class75_Sub2.aClass153_2645.method2115(111, true);
								Class140_Sub6.spritesCacheIndex.method2115(-76, true);
								CacheIndex.aClass153_1948.method2115(91, true);
								Class3_Sub13_Sub25.aClass153_3304.method2115(-116, true);
								Class140_Sub3.aClass153_2727.method2115(99, true);
								LoadingStageNumber = 97;
								Class3_Sub17.aClass94_2464 = TextCore.Starting3DLibrary;
								Class96.anInt1354 = 150;
								Class58.aBoolean913 = true;
							} else if (Class96.anInt1354 == 150) {
								Class88.method1454();
								if (RSString.aBoolean2146) {
									Class3_Sub28_Sub9.anInt3622 = 0;
									Class3_Sub28_Sub14.anInt3671 = 0;
									Node.anInt2577 = 0;
									Class3_Sub20.anInt2488 = 0;
								}

								RSString.aBoolean2146 = true;
								Class119.method1730(Class38.aClass87_665);
								GameObject.graphicsSettings(false, Node.anInt2577, -1, -1);
								LoadingStageNumber = 100;
								Class96.anInt1354 = 160;
								Class3_Sub17.aClass94_2464 = TextCore.Started3DLibrary;
							} else if (Class96.anInt1354 == 160) {
								Class3_Sub13_Sub11.method219(true);
							}
						} else if (Class3_Sub13_Sub25.aClass153_3304.method2125(Class3_Sub28_Sub14.aClass94_3672, TextCore.HasHuffman)) {
							Class36 var9 = new Class36(Class3_Sub13_Sub25.aClass153_3304.method2123(Class3_Sub28_Sub14.aClass94_3672, TextCore.HasHuffman));
							Class1.method69(var9, 104);
							Class3_Sub17.aClass94_2464 = TextCore.LoadedWordPack;
							Class96.anInt1354 = 130;
							LoadingStageNumber = 80;
						} else {
							Class3_Sub17.aClass94_2464 = RenderAnimationDefinition.method903(new RSString[]{TextCore.LoadingWordPack, InputStream_Sub1.aClass94_37}, (byte) -98);
							LoadingStageNumber = 80;
						}
					} else if (Class97.aClass153_1376.method2113((byte) 58)) {
						Class102 var8 = new Class102(Class3_Sub13_Sub28.aClass153_3352, Class97.aClass153_1376, Class140_Sub6.spritesCacheIndex, !Class25.aBoolean488);
						Class51.method1140(var8);
						if (Class3_Sub28_Sub10.anInt3625 == 1) {
							Class51.method1137(0.9F);
						}

						if (2 == Class3_Sub28_Sub10.anInt3625) {
							Class51.method1137(0.8F);
						}

						if (Class3_Sub28_Sub10.anInt3625 == 3) {
							Class51.method1137(0.7F);
						}

						if (Class3_Sub28_Sub10.anInt3625 == 4) {
							Class51.method1137(0.6F);
						}

						Class3_Sub17.aClass94_2464 = TextCore.LoadedTextures;
						Class96.anInt1354 = 100;
						LoadingStageNumber = 70;
					} else {
						Class3_Sub17.aClass94_2464 = RenderAnimationDefinition.method903(new RSString[]{TextCore.LoadingTextures, Class72.method1298((byte) 9, Class97.aClass153_1376.method2136((byte) -125)), Class24.aClass94_468}, (byte) -95);
						LoadingStageNumber = 70;
					}
				}
			}
		} catch (RuntimeException var6) {
			throw Class44.clientError(var6, "client.A(" + var1 + ')');
		}
	}
	
	final void method25() {
	      try {
	         if(Class143.loadingStage != 1000) {
	            ++Class44.anInt719;
	            if(Class44.anInt719 % 1000 == 1) {
	               GregorianCalendar var2 = new GregorianCalendar();
	               Class38_Sub1.anInt2618 = var2.get(Calendar.HOUR_OF_DAY) * 600 - (-(var2.get(Calendar.MINUTE) * 10) + -(var2.get(Calendar.SECOND) / 6));
	               Class3_Sub13_Sub7.aRandom3088.setSeed((long)Class38_Sub1.anInt2618);
	            }

	            this.method48();
	            if(Class151.aClass8_1936 != null) {
	               Class151.aClass8_1936.method838();
	            }

	            Class3_Sub29.method728();
	            Class58.method1194();
	            Class32.method996();
	            Class62.method1225();
	            if(HDToolKit.highDetail) {
	               Class31.method990();
	            }

	            int var4;
	            if(Class38.aClass146_668 != null) {
	               var4 = Class38.aClass146_668.method2078();
	               Class29.anInt561 = var4;
	            }

	            if(Class143.loadingStage == 0) {
	               this.method52(48);
	               Class75_Sub4.method1355();
	            } else if (Class143.loadingStage == 5) {
					this.method52(107);
					Class75_Sub4.method1355();
				} else if (Class143.loadingStage == 25 || Class143.loadingStage == 28) {
					Class40.method1046();
				}

				 if(10 == Class143.loadingStage) {
	               this.method47();
	               Class3_Sub13_Sub21.method267((byte)36);
	               Class163_Sub1_Sub1.method2216();
	               Class127.handleLogin((byte)-9);
	            } else if(Class143.loadingStage == 30) {
	               Class3_Sub13_Sub13.method235();
	            } else if(Class143.loadingStage == 40) {
	               Class127.handleLogin((byte)-9);
	               if(Class158.anInt2005 != -3) {
	                  if(Class158.anInt2005 == 15) {
	                     Class21.method912();
	                  } else if(Class158.anInt2005 != 2) {
	                     Class167.method2269((byte)46);
	                  }
	               }
	            }

	         }
	      } catch (RuntimeException var3) {
	         throw Class44.clientError(var3, "client.N(" + (byte) 107 + ')');
	      }
	   }
}
