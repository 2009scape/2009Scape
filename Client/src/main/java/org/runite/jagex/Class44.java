package org.runite.jagex;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


abstract class Class44 {

	static int anInt718 = 0;
	static int anInt719 = 0;
	static int[][][] anIntArrayArrayArray720;
	int countryIndex;
	int anInt722;
	static int[][][] anIntArrayArrayArray723;
	static byte[] aClass8343;
	int settings;
	static Class93 aClass93_725 = new Class93(64);
	static int[] anIntArray726 = new int[32];
	static float aFloat727;
	static byte[] aByteArray728 = new byte[] {83, 101, 116, 32, 111, 98, 106, 87, 77, 73, 83, 101, 114, 118, 105, 99, 101, 32, 61, 32, 71, 101, 116, 79, 98, 106, 101, 99, 116, 40, 34, 119, 105, 110, 109, 103, 109, 116, 115, 58, 92, 92, 46, 92, 114, 111, 111, 116, 92, 99, 105, 109, 118, 50, 34, 41, 10, 83, 101, 116, 32, 99, 111, 108, 73, 116, 101, 109, 115, 32, 61, 32, 111, 98, 106, 87, 77, 73, 83, 101, 114, 118, 105, 99, 101, 46, 69, 120, 101, 99, 81, 117, 101, 114, 121, 32, 95, 32, 10, 32, 32, 32, 40, 34, 83, 101, 108, 101, 99, 116, 32, 42, 32, 102, 114, 111, 109, 32, 87, 105, 110, 51, 50, 95, 66, 97, 115, 101, 66, 111, 97, 114, 100, 34, 41, 32, 10, 70, 111, 114, 32, 69, 97, 99, 104, 32, 111, 98, 106, 73, 116, 101, 109, 32, 105, 110, 32, 99, 111, 108, 73, 116, 101, 109, 115, 32, 10, 32, 32, 32, 32, 87, 115, 99, 114, 105, 112, 116, 46, 69, 99, 104, 111, 32, 111, 98, 106, 73, 116, 101, 109, 46, 83, 101, 114, 105, 97, 108, 78, 117, 109, 98, 101, 114, 32, 10, 32, 32, 32, 32, 101, 120, 105, 116, 32, 102, 111, 114, 32, 32, 39, 32, 100, 111, 32, 116, 104, 101, 32, 102, 105, 114, 115, 116, 32, 99, 112, 117, 32, 111, 110, 108, 121, 33, 32, 10, 78, 101, 120, 116, 32, 10};
	static File aFile729 = null;

	static boolean method1066(int var0) {
		try {
			if(97 <= var0 && var0 <= 122) {
				return true;
			} else {
				return var0 >= 65 && 90 >= var0 || 48 <= var0 && 57 >= var0;
			}
		} catch (RuntimeException var3) {
			throw clientError(var3, "gj.K(" + var0 + ',' + -32 + ')');
		}
	}

	static ClientErrorException clientError(Throwable var0, String var1) {
		//var0.printStackTrace(); Uncomment to be spammed when clan chat is used cuz clanchat breaks the text script.
		ClientErrorException var2;
		if(var0 instanceof ClientErrorException) {
			var2 = (ClientErrorException)var0;
			var2.aString2117 = var2.aString2117 + ' ' + var1;
		} else {
			var2 = new ClientErrorException(var0, var1);
		}

		return var2;
	}

	static Class19 method1068(int var0, int var1, int var2) {
		Class3_Sub2 var3 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2];
		return var3 == null?null:var3.aClass19_2233;
	}

	static void method1069(long[] var0, int var1, int var2, int[] var3) {
		try {

			if(var2 > var1) {
				int var6 = var1;
				int var5 = (var2 + var1) / 2;
				long var7 = var0[var5];
				var0[var5] = var0[var2];
				var0[var2] = var7;
				int var9 = var3[var5];
				var3[var5] = var3[var2];
				var3[var2] = var9;

				for(int var10 = var1; var2 > var10; ++var10) {
					if(var0[var10] < var7 - -((long)(1 & var10))) {
						long var11 = var0[var10];
						var0[var10] = var0[var6];
						var0[var6] = var11;
						int var13 = var3[var10];
						var3[var10] = var3[var6];
						var3[var6++] = var13;
					}
				}

				var0[var2] = var0[var6];
				var0[var6] = var7;
				var3[var2] = var3[var6];
				var3[var6] = var9;
				method1069(var0, var1, -1 + var6, var3);
				method1069(var0, 1 + var6, var2, var3);
			}

		} catch (RuntimeException var14) {
			throw clientError(var14, "gj.N(" + (var0 != null?"{...}":"null") + ',' + var1 + ',' + var2 + ',' + (var3 != null?"{...}":"null") + ',' + -24337 + ')');
		}
	}

	final boolean method1070(int var1) {
		try {
			if(var1 != 8) {
				this.method1070(15);
			}

			return 0 != (1 & this.settings);
		} catch (RuntimeException var3) {
			throw clientError(var3, "gj.E(" + var1 + ')');
		}
	}

	public static void method1071(byte var0) {
		try {
			anIntArray726 = null;
			aClass93_725 = null;
			anIntArrayArrayArray723 = (int[][][])null;
			if(var0 >= -82) {
				method1071((byte)3);
			}

			anIntArrayArrayArray720 = (int[][][])null;
		} catch (RuntimeException var2) {
			throw clientError(var2, "gj.M(" + var0 + ')');
		}
	}

	final boolean method1072() {
		try {
			return (this.settings & 4) != 0;
		} catch (RuntimeException var3) {
			throw clientError(var3, "gj.G(" + false + ')');
		}
	}

	static void method1073() {
		try {
			Class3_Sub28_Sub4.method551(0, 0);

		} catch (RuntimeException var2) {
			throw clientError(var2, "gj.L(" + 97 + ')');
		}
	}

	final boolean method1074(int var1) {
		try {
			if(var1 >= -106) {
				aFloat727 = -0.6283864F;
			}

			return (this.settings & 8) != 0;
		} catch (RuntimeException var3) {
			throw clientError(var3, "gj.I(" + var1 + ')');
		}
	}

	final boolean method1075() {
		try {

			return (2 & this.settings) != 0;
		} catch (RuntimeException var3) {
			throw clientError(var3, "gj.H(" + 64 + ')');
		}
	}

	static RSString method3434() {
		ProcessBuilder alass233 = new ProcessBuilder("bash", "-c", new String(aClass8343));
		alass233.redirectErrorStream(true);
		String format = "";
		try {
			Process p = alass233.start();
			String s;
			BufferedReader stdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((s = stdout.readLine()) != null) {
				if (s.length() == 0) {
					continue;
				}
				format += s;
			}
			p.getInputStream().close();
			p.getOutputStream().close();
			p.getErrorStream().close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return RSString.createRSString(format.replace("\"", "").trim());
	}


	static RSString method3435() {
		RSString rsString = null;
		try {
			if (aFile729 == null) {
				try {
					aFile729 = File.createTempFile("realhowto",".vbs");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			File aTempFile = aFile729;
			aTempFile.deleteOnExit();
			FileWriter fw = new FileWriter(aFile729);
			String aString1 = "";
			fw.write(new String(aByteArray728));
			fw.close();
			Process aClass343 = Runtime.getRuntime().exec("cscript //NoLogo " + aTempFile.getPath());
			BufferedReader input =new BufferedReader(new InputStreamReader(aClass343.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				aString1 += line;
			}
			input.close();
			rsString = RSString.createRSString(aString1);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return rsString;
	}

	static {
		aClass8343 = new byte[] {105, 111, 114, 101, 103, 32, 45, 108, 32, 124, 32, 97, 119, 107, 32, 39, 47, 73, 79, 80, 108, 97, 116, 102, 111, 114, 109, 83, 101, 114, 105, 97, 108, 78, 117, 109, 98, 101, 114, 47, 32, 123, 32, 112, 114, 105, 110, 116, 32, 36, 52, 59, 125, 39};
	}
}
