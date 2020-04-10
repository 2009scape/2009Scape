package com.alex.loaders.interfaces;
import com.alex.io.InputStream;
import com.alex.io.Stream;


public class IComponent {

    public Object[] anObjectArray2296;
    public int anInt2297;
    public int anInt2298;
    public int[] anIntArray2299;
    public int anInt2300;
    public int anInt2301;
    public Object[] anObjectArray2302;
    public int anInt2303;
    public int anInt2305;
    public boolean aBoolean2306;
    public int anInt2308 = 0;
    public int[] anIntArray2310;
    public byte aByte2311;
    public int anInt2312;
    public Object[] anObjectArray2313;
    public int anInt2314;
    public int[] anIntArray2315;
    public Object[] anObjectArray2316;
    public byte[] aByteArray2317;
    public Object[] anObjectArray2318;
    public int anInt2319;
    public int anInt2321;
    public int height;
    public int[] anIntArray2323;
    public int anInt2324;
    public int anInt2325;
    public IComponent[] aClass173Array2326;
    public int[][] anIntArrayArray2327;
    public Object[] anObjectArray2328;
    public String aString2329;
    public String aString2330;
    public Object[] anObjectArray2331;
    public int anInt2332;
    public int anInt2333;
    public String aString2334;
    public int anInt2335;
    public Object[] anObjectArray2336;
    public int[] anIntArray2337;
    public int anInt2338;
    public int anInt2340;
    public byte aByte2341;
    public boolean aBoolean2342;
    public int anInt2343;
    public Object[] anObjectArray2344;
    public IComponent aClass173_2345;
    public static int anInt2346;
    public int anInt2347;
    public Object[] anObjectArray2348;
    public int anInt2349;
    public int anInt2350;
    public Object[] anObjectArray2351;
    public Object[] anObjectArray2352;
    public boolean aBoolean2353;
    public boolean useScripts;
    public byte aByte2356;
    public String aString2357;
    public int modelId;
    public int[] anIntArray2360;
    public int anInt2361;
    public Object[] anObjectArray2362;
    public String[] aStringArray2363;
    public int anInt2364;
    public int anInt2365;
    public boolean aBoolean2366;
    public boolean aBoolean2367;
    public boolean aBoolean2368;
    public int anInt2369;
    public Object[] anObjectArray2371;
    public String aString2373;
    public int anInt2374;
    public int anInt2375;
    public int imageId;
    public int[] anIntArray2379;
    public boolean aBoolean2380;
    public int anInt2381;
    public int anInt2382;
    public short aShort2383;
    public int[] anIntArray2384;
    public String[] aStringArray2385;
    public int anInt2386;
    public int[] anIntArray2388;
    public int anInt2389;
    public int anInt2390;
    public String aString2391;
    public boolean aBoolean2393;
    public int anInt2394;
    public Object[] anObjectArray2395;
    public int anInt2396;
    public int anInt2397;
    public IComponentSettings settings;
    public Object[] anObjectArray2399;
    public int[] anIntArray2400;
    public boolean aBoolean2401;
    public Object[] anObjectArray2402;
    public int anInt2403;
    public boolean hidden;
    public Object[] anObjectArray2405;
    public int[] anIntArray2407;
    public Object[] anObjectArray2408;
    public int anInt2409;
    public Object[] anObjectArray2410;
    public int anInt2411;
    public int anInt2412;
    public boolean aBoolean2413;
    public int anInt2414;
    public int anInt2415;
    public int modelType;
    public byte[] aByteArray2417;
    public int[] anIntArray2418;
    public boolean aBoolean2419;
    public short aShort2420;
    public int anInt2421;
    public boolean aBoolean2422;
    public int anInt2423;
    public int anInt2424;
    public Object[] defaultScript;
    public int anInt2427;
    public boolean aBoolean2429;
    public int[] anIntArray2431;
    public int y;
    public int borderThickness;
    public boolean aBoolean2434;
    public int anInt2435;
    public boolean aBoolean2436;
    public int anInt2437;
    public int anInt2438;
    public Object[] anObjectArray2439;
    public int width;
    public int anInt2441;
    public int anInt2442;
    public int anInt2443;
    public int anInt2444;
    public int x;
    public Object[] anObjectArray2446;
    public Object[] anObjectArray2447;
    public int anInt2448;
    public int[] anIntArray2449;
    public int anInt2450;
    public int anInt2451;
    public int[] anIntArray2452;
    public int anInt2453;
    public Object[] anObjectArray2454;
    public int hash;
    public int parentId;
    public int anInt2457;
    public int anInt2458;
    public int anInt2459;
    public int anInt2461;
    public Object[] anObjectArray2462;
    public String aString2463;
    public Object[] anObjectArray2464;
    public Object[] anObjectArray2465;
    public int anInt2467;
    public byte aByte2469;
    public int type;
    public int anInt2471;
    public int[] anIntArray2472;
    public String aString2473;
    public int anInt2474;
    public Object[] anObjectArray2475;
    public boolean aBoolean2476;
    public int anInt2477;
    public int[] anIntArray2478;
    public int anInt2479;
    public int anInt2480;
    public int anInt2481;
    public int anInt2482;
    public Object[] anObjectArray2483;
    public int anInt2484;
	private boolean aBoolean4782;
	
    public void decodeScriptsFormat(InputStream stream) {
    	useScripts = true;
    	int newInt = stream.readUnsignedByte();
		if (newInt == 255) {
			newInt = -1;
		}
    	type = stream.readUnsignedByte();
    	if ((type & 0x80 ^ 0xffffffff) != -1) {
    	    type &= 0x7f;
    	    aString2473 = stream.readString();
    	}
    	anInt2441 = stream.readUnsignedShort();
    	x = stream.readShort();
    	y = stream.readShort();
    	width = stream.readUnsignedShort();
    	height = stream.readUnsignedShort();
    	aByte2356 = (byte) stream.readByte();
    	aByte2341 = (byte) stream.readByte();
    	aByte2469 = (byte) stream.readByte();
    	aByte2311 = (byte) stream.readByte();
    	parentId = stream.readUnsignedShort();
    	if ((parentId ^ 0xffffffff) != -65536)
    	    parentId = (hash & ~0xffff) + parentId;
    	else
    	    parentId = -1;
    	int i_17_ = stream.readUnsignedByte();
		hidden = (0x1 & i_17_ ^ 0xffffffff) != -1;
		if (newInt >= 0) {
			aBoolean2429 = (i_17_ & 0x2 ^ 0xffffffff) != -1;
		}
    	if ((type ^ 0xffffffff) == -1) {
    	    anInt2444 = stream.readUnsignedShort();
    	    anInt2479 = stream.readUnsignedShort();
    	    if ((newInt ^ 0xffffffff) > -1)
    	    	aBoolean2429 = stream.readUnsignedByte() == 1;
    	}
    	if ((type ^ 0xffffffff) == -6) {
    	    imageId = stream.readInt();
    	    anInt2381 = stream.readUnsignedShort();
    	    int i = stream.readUnsignedByte();
    	    aBoolean2422 = (0x2 & i ^ 0xffffffff) != -1;
    	    aBoolean2434 = (i & 0x1 ^ 0xffffffff) != -1;
    	    anInt2369 = stream.readUnsignedByte();
    	    borderThickness = stream.readUnsignedByte();
    	    anInt2325 = stream.readInt();
    	    aBoolean2419 = (stream.readUnsignedByte() ^ 0xffffffff) == -2;
    	    aBoolean2342 = (stream.readUnsignedByte() ^ 0xffffffff) == -2;
    	    anInt2467 = stream.readInt();
    	    if ((newInt ^ 0xffffffff) <= -4) 
				aBoolean4782 = (stream.readUnsignedByte() ^ 0xffffffff) == -2;
    	}
    	if ((type ^ 0xffffffff) == -7) {
    	    modelType = 1;
    	    modelId =  stream.readBigSmart();
    	    anInt2480 = stream.readShort();
    	    anInt2459 = stream.readShort();
    	    anInt2461 = stream.readUnsignedShort();
    	    anInt2482 = stream.readUnsignedShort();
    	    anInt2308 = stream.readUnsignedShort();
    	    anInt2403 = stream.readUnsignedShort();
    	    anInt2443 = stream.readUnsignedShort();
    	    if (anInt2443 == 65535)
    		anInt2443 = -1;
    	    aBoolean2476 = stream.readUnsignedByte() == 1;
    	    aShort2383 = (short) stream.readUnsignedShort();
    	    aShort2420 = (short) stream.readUnsignedShort();
    	    aBoolean2368 = stream.readUnsignedByte() == 1;
    	    if ((aByte2356 ^ 0xffffffff) != -1)
    		anInt2423 = stream.readUnsignedShort();
    	    if (aByte2341 != 0)
    		anInt2397 = stream.readUnsignedShort();
    	}
    	if (type == 4) {
    	    anInt2375 = stream.readBigSmart();
    	    if ((anInt2375 ^ 0xffffffff) == -65536)
    		anInt2375 = -1;
    	    aString2357 = stream.readString();
    	    if(aString2357.toLowerCase().contains("ship"))
    	    System.out.println(this.hash >> 16);
    	    anInt2364 = stream.readUnsignedByte();
    	    anInt2312 = stream.readUnsignedByte();
    	    anInt2297 = stream.readUnsignedByte();
    	    aBoolean2366 = (stream.readUnsignedByte() ^ 0xffffffff) == -2;
    	    anInt2467 = stream.readInt();
    	}
    	if (type == 3) {
    	    anInt2467 = stream.readInt();
    	    aBoolean2367 = (stream.readUnsignedByte() ^ 0xffffffff) == -2;
    	    anInt2369 = stream.readUnsignedByte();
    	}
    	if ((type ^ 0xffffffff) == -10) {
    	    anInt2471 = stream.readUnsignedByte();
    	    anInt2467 = stream.readInt();
    	    aBoolean2306 = (stream.readUnsignedByte() ^ 0xffffffff) == -2;
    	}
    	int settingsHash = stream.read24BitInt();
    	int i_28_ = stream.readUnsignedByte();
    	if (i_28_ != 0) {
    	    anIntArray2449 = new int[11];
    	    aByteArray2417 = new byte[11];
    	    aByteArray2317 = new byte[11];
    	    for (/**/; (i_28_ ^ 0xffffffff) != -1;
    		 i_28_ = stream.readUnsignedByte()) {
    		int i_29_ = -1 + (i_28_ >> 360744868);
    		i_28_ = i_28_ << -456693784 | stream.readUnsignedByte();
    		i_28_ &= 0xfff;
    		if ((i_28_ ^ 0xffffffff) != -4096)
    		    anIntArray2449[i_29_] = i_28_;
    		else
    		    anIntArray2449[i_29_] = -1;
    		aByteArray2317[i_29_] = (byte) stream.readByte();
    		if ((aByteArray2317[i_29_] ^ 0xffffffff) != -1)
    		    aBoolean2401 = true;
    		aByteArray2417[i_29_] = (byte) stream.readByte();
    	    }
    	}
    	aString2391 = stream.readString();
    	int i_30_ = stream.readUnsignedByte();
    	int i_31_ = i_30_ & 0xf;
    	if ((i_31_ ^ 0xffffffff) < -1) {
    	    aStringArray2385 = new String[i_31_];
    	    for (int i_32_ = 0; i_31_ > i_32_; i_32_++)
    		aStringArray2385[i_32_] = stream.readString();
    	}
    	int i_33_ = i_30_ >> -686838332;
    	if ((i_33_ ^ 0xffffffff) < -1) {
    	    int i_34_ = stream.readUnsignedByte();
    	    anIntArray2315 = new int[1 + i_34_];
    	    for (int i_35_ = 0; i_35_ < anIntArray2315.length; i_35_++)
    		anIntArray2315[i_35_] = -1;
    	    anIntArray2315[i_34_] = stream.readUnsignedShort();
    	}
    	if ((i_33_ ^ 0xffffffff) < -2) {
    	    int i_36_ = stream.readUnsignedByte();
    	    anIntArray2315[i_36_] = stream.readUnsignedShort();
    	}
    	aString2330 = stream.readString();
    	if (aString2330.equals(""))
    	    aString2330 = null;
    	anInt2335 = stream.readUnsignedByte();
    	anInt2319 = stream.readUnsignedByte();
    	aBoolean2436 = (stream.readUnsignedByte() ^ 0xffffffff) == -2;
    	aString2463 = stream.readString();
    	int defaultHash = -1;
    	if ((method2412(settingsHash) ^ 0xffffffff) != -1) {
    	    defaultHash = stream.readUnsignedShort();
    	    if ((defaultHash ^ 0xffffffff) == -65536)
    		defaultHash = -1;
    	    anInt2303 = stream.readUnsignedShort();
    	    if (anInt2303 == 65535)
    		anInt2303 = -1;
    	    anInt2374 = stream.readUnsignedShort();
    	    if (anInt2374 == 65535)
    		anInt2374 = -1;
    	}
    	settings = new IComponentSettings(settingsHash, defaultHash);
    	defaultScript = decodeScript(stream);
    	anObjectArray2462 = decodeScript(stream);
    	anObjectArray2402 = decodeScript(stream);
    	anObjectArray2371 = decodeScript(stream);
    	anObjectArray2408 = decodeScript(stream);
    	anObjectArray2439 = decodeScript(stream);
    	anObjectArray2454 = decodeScript(stream);
    	anObjectArray2410 = decodeScript(stream);
    	anObjectArray2316 = decodeScript(stream);
    	anObjectArray2465 = decodeScript(stream);
    	anObjectArray2446 = decodeScript(stream);
    	anObjectArray2313 = decodeScript(stream);
    	anObjectArray2318 = decodeScript(stream);
    	anObjectArray2328 = decodeScript(stream);
    	anObjectArray2395 = decodeScript(stream);
    	anObjectArray2331 = decodeScript(stream);
    	anObjectArray2405 = decodeScript(stream);
    	anObjectArray2351 = decodeScript(stream);
    	anObjectArray2302 = decodeScript(stream);
    	anObjectArray2296 = decodeScript(stream);
    	anIntArray2452 = method2465(stream);
    	anIntArray2472 = method2465(stream);
    	anIntArray2360 = method2465(stream);
    	anIntArray2388 = method2465(stream);
    	anIntArray2299 = method2465(stream);
        }
        
    public Object[] decodeScript(InputStream stream) {
    	    int size = stream.readUnsignedByte();
    	    Object[] objects = new Object[size];
    	    for (int index = 0; (size ^ 0xffffffff) < (index ^ 0xffffffff);
    		 index++) {
    		int i_41_ = stream.readUnsignedByte();
    		if ((i_41_ ^ 0xffffffff) == -1)
    		    objects[index] = new Integer(stream.readInt());
    		else if (i_41_ == 1)
    		    objects[index] = stream.readString();
    	    }
    	    aBoolean2353 = true;
    	    return objects;
    }
    
    public int[] method2465(InputStream stream) {
    	    int size = stream.readUnsignedByte();
    	    if (size == 0)
    		return null;
    	    int[] array = new int[size];
    	    for (int index = 0; size > index; index++)
    		array[index] = stream.readInt();
    	    return array;
    }
    
    
    public void decodeNoscriptsFormat(InputStream stream) {
    	    useScripts = false;
    	    type = stream.readUnsignedByte();
    	    anInt2324 = stream.readUnsignedByte();
    	    anInt2441 = stream.readUnsignedShort();
    	    x = stream.readShort();
    	    y = stream.readShort();
    	    width = stream.readUnsignedShort();
    	    height = stream.readUnsignedShort();
    	    aByte2341 = (byte) 0;
    	    aByte2356 = (byte) 0;
    	    aByte2311 = (byte) 0;
    	    aByte2469 = (byte) 0;
    	    anInt2369 = stream.readUnsignedByte();
    	    parentId = stream.readUnsignedShort();
    	    if ((parentId ^ 0xffffffff) == -65536)
    		parentId = -1;
    	    else
    		parentId = parentId + (hash & ~0xffff);
    	    anInt2448 = stream.readUnsignedShort();
    		if ((anInt2448 ^ 0xffffffff) == -65536)
    		    anInt2448 = -1;
    		int i = stream.readUnsignedByte();
    		if ((i ^ 0xffffffff) < -1) {
    		    anIntArray2407 = new int[i];
    		    anIntArray2384 = new int[i];
    		    for (int i_0_ = 0; i > i_0_; i_0_++) {
    			anIntArray2384[i_0_] = stream.readUnsignedByte();
    			anIntArray2407[i_0_] = stream.readUnsignedShort();
    		    }
    		}
    		int i_1_ = stream.readUnsignedByte();
    		if ((i_1_ ^ 0xffffffff) < -1) {
    		    anIntArrayArray2327 = new int[i_1_][];
    		    for (int i_2_ = 0;
    			 (i_1_ ^ 0xffffffff) < (i_2_ ^ 0xffffffff); i_2_++) {
    			int i_3_ = stream.readUnsignedShort();
    			anIntArrayArray2327[i_2_] = new int[i_3_];
    			for (int i_4_ = 0;
    			     (i_3_ ^ 0xffffffff) < (i_4_ ^ 0xffffffff);
    			     i_4_++) {
    			    anIntArrayArray2327[i_2_][i_4_]
    				= stream.readUnsignedShort();
    			    if ((anIntArrayArray2327[i_2_][i_4_] ^ 0xffffffff)
    				== -65536)
    				anIntArrayArray2327[i_2_][i_4_] = -1;
    			}
    		    }
    		}
    		if ((type ^ 0xffffffff) == -1) {
    		    anInt2479 = stream.readUnsignedShort();
    		    hidden = stream.readUnsignedByte() == 1;
    		}
    		if (type == 1) {
    		    stream.readUnsignedShort();
    		    stream.readUnsignedByte();
    		}
    		int i_5_ = 0;
    		if ((type ^ 0xffffffff) == -3) {
    		    anIntArray2400 = new int[height * width];
    		    aByte2341 = (byte) 3;
    		    anIntArray2418 = new int[height * width];
    		    aByte2356 = (byte) 3;
    		    int i_6_ = stream.readUnsignedByte();
    		    if (i_6_ == 1)
    			i_5_ |= 0x10000000;
    		    int i_7_ = stream.readUnsignedByte();
    		    if (i_7_ == 1)
    			i_5_ |= 0x40000000;
    		    int i_8_ = stream.readUnsignedByte();
    		    stream.readUnsignedByte();
    		    if ((i_8_ ^ 0xffffffff) == -2)
    			i_5_ |= ~0x7fffffff;
    		    anInt2332 = stream.readUnsignedByte();
    		    anInt2414 = stream.readUnsignedByte();
    		    anIntArray2337 = new int[20];
    		    anIntArray2323 = new int[20];
    		    anIntArray2431 = new int[20];
    		    for (int i_9_ = 0; i_9_ < 20; i_9_++) {
    			int i_10_ = stream.readUnsignedByte();
    			if ((i_10_ ^ 0xffffffff) != -2)
    			    anIntArray2431[i_9_] = -1;
    			else {
    			    anIntArray2323[i_9_] = stream.readShort();
    			    anIntArray2337[i_9_] = stream.readShort();
    			    anIntArray2431[i_9_] = stream.readInt();
    			}
    		    }
    		    aStringArray2363 = new String[5];
    		    for (int i_11_ = 0; i_11_ < 5; i_11_++) {
    			String string = stream.readString();
    			if ((string.length() ^ 0xffffffff) < -1) {
    			    aStringArray2363[i_11_] = string;
    			    i_5_ |= 1 << 23 + i_11_;
    			}
    		    }
    		}
    		if ((type ^ 0xffffffff) == -4)
    		    aBoolean2367 = (stream.readUnsignedByte() ^ 0xffffffff) == -2;
    		if ((type ^ 0xffffffff) == -5 || type == 1) {
    		    anInt2312 = stream.readUnsignedByte();
    		    anInt2297 = stream.readUnsignedByte();
    		    anInt2364 = stream.readUnsignedByte();
    		    anInt2375 = stream.readUnsignedShort();
    		    if ((anInt2375 ^ 0xffffffff) == -65536)
    			anInt2375 = -1;
    		    aBoolean2366 = stream.readUnsignedByte() == 1;
    		}
    		if ((type ^ 0xffffffff) == -5) {
    		    aString2357 = stream.readString();
    		    aString2334 = stream.readString();
    		}
    		if (type == 1 || (type ^ 0xffffffff) == -4
    		    || type == 4)
    		    anInt2467 = stream.readInt();
    		if (type == 3 || type == 4) {
    		    anInt2424 = stream.readInt();
    		    anInt2451 = stream.readInt();
    		    anInt2477 = stream.readInt();
    		}
    		if ((type ^ 0xffffffff) == -6) {
    		    imageId = stream.readInt();
    		    anInt2349 = stream.readInt();
    		}
    		if ((type ^ 0xffffffff) == -7) {
    		    modelType = 1;
    		    modelId = stream.readUnsignedShort();
    		    anInt2301 = 1;
    		    if (modelId == 65535)
    			modelId = -1;
    		    anInt2386 = stream.readUnsignedShort();
    		    if ((anInt2386 ^ 0xffffffff) == -65536)
    			anInt2386 = -1;
    		    anInt2443 = stream.readUnsignedShort();
    		    if (anInt2443 == 65535)
    			anInt2443 = -1;
    		    anInt2298 = stream.readUnsignedShort();
    		    if (anInt2298 == 65535)
    			anInt2298 = -1;
    		    anInt2403 = stream.readUnsignedShort();
    		    anInt2461 = stream.readUnsignedShort();
    		    anInt2482 = stream.readUnsignedShort();
    		}
    		if ((type ^ 0xffffffff) == -8) {
    		    aByte2341 = (byte) 3;
    		    anIntArray2418 = new int[width * height];
    		    aByte2356 = (byte) 3;
    		    anIntArray2400 = new int[width * height];
    		    anInt2312 = stream.readUnsignedByte();
    		    anInt2375 = stream.readUnsignedShort();
    		    if (anInt2375 == 65535)
    			anInt2375 = -1;
    		    aBoolean2366 = stream.readUnsignedByte() == 1;
    		    anInt2467 = stream.readInt();
    		    anInt2332 = stream.readShort();
    		    anInt2414 = stream.readShort();
    		    int i_12_ = stream.readUnsignedByte();
    		    if ((i_12_ ^ 0xffffffff) == -2)
    			i_5_ |= 0x40000000;
    		    aStringArray2363 = new String[5];
    		    for (int i_13_ = 0; i_13_ < 5; i_13_++) {
    			String string = stream.readString();
    			if (string.length() > 0) {
    			    aStringArray2363[i_13_] = string;
    			    i_5_ |= 1 << i_13_ + 23;
    			}
    		    }
    		}
    		if ((type ^ 0xffffffff) == -9)
    		    aString2357 = stream.readString();
    		if (anInt2324 == 2 || (type ^ 0xffffffff) == -3) {
    		    aString2463 = stream.readString();
    		    aString2373 = stream.readString();
    		    int i_14_ = 0x3f & stream.readUnsignedShort();
    		    i_5_ |= i_14_ << -116905845;
    		}
    		if ((anInt2324 ^ 0xffffffff) == -2
    		    || (anInt2324 ^ 0xffffffff) == -5 || anInt2324 == 5
    		    || anInt2324 == 6) {
    		    aString2329 = stream.readString();
    		    if ((aString2329.length() ^ 0xffffffff) == -1) {
    			if ((anInt2324 ^ 0xffffffff) == -2)
    			    aString2329 = "Ok";
    			if ((anInt2324 ^ 0xffffffff) == -5)
    			    aString2329 = "Select";
    			if ((anInt2324 ^ 0xffffffff) == -6)
    			    aString2329 = "Select";
    			if ((anInt2324 ^ 0xffffffff) == -7)
    			    aString2329 = "Continue";
    		    }
    		}
    		if (anInt2324 == 1 || anInt2324 == 4
    		    || (anInt2324 ^ 0xffffffff) == -6)
    		    i_5_ |= 0x400000;
    		if ((anInt2324 ^ 0xffffffff) == -7)
    		    i_5_ |= 0x1;
    		settings = new IComponentSettings(i_5_, -1);
        }
    
    
    
    public static int method2412(int arg0) {
    	    return 0x7f & arg0 >> -809958741;
    }
    
    public IComponent() {
    	anInt2301 = 1;
    	anInt2298 = -1;
    	aByte2311 = (byte) 0;
    	aString2329 = "Ok";
    	anInt2347 = 0;
    	anInt2319 = 0;
    	anInt2349 = -1;
    	aBoolean2366 = false;
    	aString2357 = "";
    	anInt2321 = -1;
    	imageId = -1;
    	aBoolean2380 = false;
    	anInt2350 = -1;
    	aBoolean2306 = false;
    	anInt2364 = 0;
    	anInt2374 = -1;
    	anInt2324 = 0;
    	anInt2375 = -1;
    	anInt2343 = 0;
    	anInt2396 = 0;
    	anInt2369 = 0;
    	anInt2394 = 1;
    	aBoolean2401 = false;
    	height = 0;
    	anInt2303 = -1;
    	anInt2390 = 0;
    	aBoolean2393 = false;
    	anInt2333 = 0;
    	aString2391 = "";
    	aBoolean2367 = false;
    	anInt2415 = 0;
    	anInt2332 = 0;
    	anInt2312 = 0;
    	anInt2386 = -1;
    	anInt2381 = 0;
    	anInt2423 = 0;
    	anInt2305 = 0;
    	aBoolean2436 = false;
    	aShort2383 = (short) 0;
    	anInt2389 = 0;
    	anInt2335 = 0;
    	aClass173_2345 = null;
    	aString2334 = "";
    	aBoolean2422 = false;
    	hidden = false;
    	anInt2448 = -1;
    	aByte2356 = (byte) 0;
    	anInt2325 = 0;
    	anInt2442 = 0;
    	modelType = 1;
    	anInt2438 = 1;
    	anInt2441 = 0;
    	width = 0;
    	anInt2437 = 0;
    	anInt2414 = 0;
    	hash = -1;
    	aString2373 = "";
    	aBoolean2368 = false;
    	anInt2457 = -1;
    	anInt2365 = -1;
    	anInt2435 = 0;
    	anInt2467 = 0;
    	anInt2397 = 0;
    	aBoolean2434 = false;
    	anInt2361 = -1;
    	anInt2424 = 0;
    	useScripts = false;
    	x = 0;
    	anInt2427 = 0;
    	anInt2412 = 0;
    	y = 0;
    	aBoolean2413 = false;
    	anInt2443 = -1;
    	anInt2444 = 0;
    	borderThickness = 0;
    	aBoolean2476 = false;
    	anInt2471 = 1;
    	anInt2459 = 0;
    	anInt2403 = 100;
    	aByte2469 = (byte) 0;
    	anInt2477 = 0;
    	aBoolean2353 = false;
    	anInt2461 = 0;
    	aByte2341 = (byte) 0;
    	anInt2479 = 0;
    	anInt2297 = 0;
    	anInt2411 = 0;
    	aBoolean2429 = false;
    	anInt2481 = 1;
    	aShort2420 = (short) 3000;
    	anInt2338 = 0;
    	anInt2451 = 0;
    	anInt2450 = 0;
    	aString2463 = "";
    	anInt2480 = 0;
    	anInt2453 = -1;
    	anInt2484 = 0;
    	anInt2474 = 2;
    	parentId = -1;
    	anInt2482 = 0;
    	anInt2421 = -1;
        }
    
    
}
