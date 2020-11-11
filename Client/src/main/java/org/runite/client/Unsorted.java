package org.runite.client;

import com.jogamp.opengl.*;
import org.rs09.client.config.GameConfig;
import org.rs09.client.data.HashTable;
import org.rs09.client.rendering.Toolkit;
import org.rs09.client.util.ArrayUtils;

import org.rs09.client.net.Connection;
import org.rs09.client.data.ReferenceCache;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Unsorted {
    public static boolean aBoolean2146 = false;
    public static int incomingOpcode = 0;
    public static int anInt2148 = 0;
    public static boolean aBoolean2150;
    public static boolean aBoolean2154;
    public static int[] anIntArray2157 = new int[50];
    public static int[] anIntArray882 = new int[200];
    static int anInt942;
    static int anInt944 = 0;
    static int anInt952;
    static int anInt963;
    public static int anInt144;
    public static int anInt136 = 0;
    public static int anInt120 = 255;
    public static byte[][][] aByteArrayArrayArray113 = new byte[4][104][104];
    static int anInt1037;
    static int anInt1038;
    static Class30 aClass30_1039;
    static int anInt3695;
    static int anInt1042;
    static CacheIndex aClass153_1043;
    static AbstractSprite[] aAbstractSpriteArray996;
    static int maskUpdateCount = 0;
    static int anInt998 = 0;
    static int anInt999 = -1;
    static int wlPacketSize = 0;
    public static int[] anIntArray39;
    public static int anInt40 = 0;
    public static Connection js5Connection;
    public static int anInt42 = 0;
    public static RSString[] aClass94Array45;
    public static short aShort46 = 256;
    public static boolean aBoolean47 = false;
    public static int anInt48 = 2;
    public static int[] anIntArray49;
    public static int anInt1234;
    public static Class33 aClass33_1238;
    public static Class61 aClass61_1242 = new Class61();
    public static int anInt1244 = -1;
    public static RSInterface aClass11_1933;
    public static float[] aFloatArray1934 = new float[]{0.073F, 0.169F, 0.24F, 1.0F};
    public static Class8 aClass8_1936;
    public static Class3_Sub30_Sub1 aClass3_Sub30_Sub1_2942 = new Class3_Sub30_Sub1();
    public static int[] anIntArray2952 = new int[128];
    public static int anInt2958 = 0;
    public static Class61 aClass61_78 = new Class61();
    public static int anInt72 = 0;
    public static boolean[] aBooleanArray73 = new boolean[200];
    public static boolean[] aBooleanArray1703;
    public static int incomingPacketLength = 0;
    public static int anInt1705 = 0;
    public static int anInt1709 = 0;
    public static int anInt1711 = -2;
    public static boolean[] aBooleanArray1712 = new boolean[100];
    public static ReferenceCache aReferenceCache_1131 = new ReferenceCache(5);
    public static ReferenceCache aReferenceCache_1135 = new ReferenceCache(4);
    public static AbstractSprite[] aAbstractSpriteArray1136;
    public static int anInt1137 = 2;
    public static int[] anIntArray1138;
    public static int anInt3625 = 3;
    public static int anInt3631;
    public static Class25[] aClass25Array4060;
    public static int anInt4062 = 0;
    public static boolean aBoolean4063 = false;
    public static boolean aBoolean4068 = true;
    public static Class3_Sub2[][][] aClass3_Sub2ArrayArrayArray4070;
    public static int anInt4073;
    static ReferenceCache aReferenceCache_684 = new ReferenceCache(64);
    static int[] anIntArray686 = new int[2];
    static int anInt688 = 0;
    static int anInt689;
    public static CacheIndex aClass153_332;
    static RSString[] aClass94Array2566 = new RSString[200];
    static int anInt2567 = -1;
    static int clanSize;
    static int[] anIntArray2574 = new int[14];
    static int anInt2577 = 0;
    public static int[] anIntArray2591;
    public static RSString[] aClass94Array2596 = null;
    public static Class3_Sub28_Sub3 aClass3_Sub28_Sub3_2600;
    public static Class151_Sub1[] aClass151_Sub1Array2601 = new Class151_Sub1[29]; //TODO
    public static boolean aBoolean1080 = false;
    public static int anInt1081 = 0;
    public static int anInt1082;
    public static int[] anIntArray1083;
    public static boolean aBoolean1084 = false;
    public static int anInt1088 = 0;
    public static int anInt1950;
    public static boolean aBoolean1951 = false;
    public static int anInt1953;
    public static int anInt1881 = 0;
    public static int anInt1887;
    static boolean aBoolean29 = false;
    static int anInt30;
    static int anInt31 = 0;
    public static int anInt14 = 0;
    public static int anInt15 = 0;
    public static ReferenceCache aReferenceCache_21 = new ReferenceCache(64);
    public static int registryStage = 0;
    public static int anInt25 = 1;
    public static int anInt3657;
    public static int anInt3660 = 0;
    public static boolean aBoolean3665 = true;
    public static Class61 aClass61_2468 = new Class61();
    public static int[] anIntArray2469;
    public static int[] anIntArray2470 = new int[]{0, 0, 2, 0, 0, 2, 1, 1, 0};
    public static boolean aBoolean3641 = false;
    public static int anInt3642 = 0;
    public static int anInt3644 = 0;
    public static int anInt59 = 0;
    public static short[] aShortArray63 = new short[]{(short)960, (short)957, (short)-21568, (short)-21571, (short)22464};
    public static int anInt65;
    public static boolean isMember = false;
    public static int anInt67;
    public static ShaderInterface[] anShaderInterfaceArray70;
    public static boolean aBoolean742 = false;
    public static ReferenceCache aReferenceCache_743 = new ReferenceCache(20);
    public static CacheIndex quickChatMessages;
    public static byte[][][] aByteArrayArrayArray1328;
    public static int anInt3602;
    public static int anInt3603;
    public static boolean aBoolean3604 = true;
    public static int[][][] anIntArrayArrayArray3605;
    public static int anInt3606;
    public static int[] anIntArray3607 = new int[]{0, 2, 2, 2, 1, 1, 2, 2, 1, 3, 1, 1};
    public static int updateStamp;
    public static ReferenceCache aReferenceCache_4043 = new ReferenceCache(64);
    public static volatile int anInt4045 = 0;
    public static HashTable aHashTable_4046 = new HashTable(16);
    public static MouseListeningClass aClass149_4047 = new MouseListeningClass();
    public static CacheIndex aClass153_4048;
    public static int[] anIntArray4050 = new int[1000];
    public static ReferenceCache aReferenceCache_4051 = new ReferenceCache(30);
    public static short[][] aShortArrayArray1619 = new short[][]{{(short)10, (short)30, (short)50, (short)70, (short)90, (short)110, (short)310, (short)684, (short)704, (short)556, (short)940, (short)960, (short)6454, (short)6952, (short)6972, (short)2358, (short)2732, (short)2752, (short)10550, (short)10924, (short)10944, (short)10310, (short)10556, (short)10576, (short)14646, (short)15020, (short)15040, (short)19766, (short)20140, (short)20160, (short)-29386, (short)-29012, (short)-28992, (short)31030, (short)31276, (short)31296, (short)-24266, (short)-23892, (short)-23872, (short)-19146, (short)-18772, (short)-18752, (short)-14026, (short)-13652, (short)-13632, (short)-6858, (short)-6484, (short)-6464, (short)522, (short)542, (short)6794, (short)6814, (short)11018, (short)11038, (short)14986, (short)15006, (short)21130, (short)21150, (short)-28918, (short)-28898, (short)-22006, (short)-21986, (short)-12918, (short)-12898, (short)10, (short)30, (short)50, (short)70, (short)90, (short)110, (short)310, (short)684, (short)704, (short)556, (short)940, (short)960, (short)6454, (short)6952, (short)6972, (short)2358, (short)2732, (short)2752, (short)10550, (short)10924, (short)10944, (short)10310, (short)10556, (short)10576, (short)14646, (short)15020, (short)15040, (short)19766, (short)20140, (short)20160, (short)-29386, (short)-29012, (short)-28992, (short)31030, (short)31276, (short)31296, (short)-24266, (short)-23892, (short)-23872, (short)-19146, (short)-18772, (short)-18752, (short)-14026, (short)-13652, (short)-13632, (short)-6858, (short)-6484, (short)-6464, (short)522, (short)542, (short)6794, (short)6814, (short)11018, (short)11038, (short)14986, (short)15006, (short)21130, (short)21150, (short)-28918, (short)-28898, (short)-22006, (short)-21986, (short)-12918, (short)-12898, (short)10, (short)30, (short)50, (short)70, (short)90, (short)110, (short)310, (short)684, (short)704, (short)556, (short)940, (short)960, (short)6454, (short)6952, (short)6972, (short)2358, (short)2732, (short)2752, (short)10550, (short)10924, (short)10944, (short)10310, (short)10556, (short)10576, (short)14646, (short)15020, (short)15040, (short)19766, (short)20140, (short)20160, (short)-29386, (short)-29012, (short)-28992, (short)31030, (short)31276, (short)31296, (short)-24266, (short)-23892, (short)-23872, (short)-19146, (short)-18772, (short)-18752, (short)-14026, (short)-13652, (short)-13632, (short)-6858, (short)-6484, (short)-6464, (short)522, (short)542, (short)6794, (short)6814, (short)11018, (short)11038, (short)14986, (short)15006, (short)21130, (short)21150, (short)-28918, (short)-28898, (short)-22006, (short)-21986, (short)-12918, (short)-12898, (short)10, (short)30, (short)50, (short)70, (short)90, (short)110, (short)310, (short)684, (short)704, (short)556, (short)940, (short)960, (short)6454, (short)6952, (short)6972, (short)2358, (short)2732, (short)2752, (short)10550, (short)10924, (short)10944, (short)10310, (short)10556, (short)10576, (short)14646, (short)15020, (short)15040, (short)19766, (short)20140, (short)20160, (short)-29386, (short)-29012, (short)-28992, (short)31030, (short)31276, (short)31296, (short)-24266, (short)-23892, (short)-23872, (short)-19146, (short)-18772, (short)-18752, (short)-14026, (short)-13652, (short)-13632, (short)-6858, (short)-6484, (short)-6464, (short)522, (short)542, (short)6794, (short)6814, (short)11018, (short)11038, (short)14986, (short)15006, (short)21130, (short)21150, (short)-28918, (short)-28898, (short)-22006, (short)-21986, (short)-12918, (short)-12898}, {(short)10, (short)30, (short)50, (short)70, (short)90, (short)110, (short)310, (short)684, (short)704, (short)556, (short)940, (short)960, (short)6454, (short)6952, (short)6972, (short)2358, (short)2732, (short)2752, (short)10550, (short)10924, (short)10944, (short)10310, (short)10556, (short)10576, (short)14646, (short)15020, (short)15040, (short)19766, (short)20140, (short)20160, (short)-29386, (short)-29012, (short)-28992, (short)31030, (short)31276, (short)31296, (short)-24266, (short)-23892, (short)-23872, (short)-19146, (short)-18772, (short)-18752, (short)-14026, (short)-13652, (short)-13632, (short)-6858, (short)-6484, (short)-6464, (short)522, (short)542, (short)6794, (short)6814, (short)11018, (short)11038, (short)14986, (short)15006, (short)21130, (short)21150, (short)-28918, (short)-28898, (short)-22006, (short)-21986, (short)-12918, (short)-12898, (short)10, (short)30, (short)50, (short)70, (short)90, (short)110, (short)310, (short)684, (short)704, (short)556, (short)940, (short)960, (short)6454, (short)6952, (short)6972, (short)2358, (short)2732, (short)2752, (short)10550, (short)10924, (short)10944, (short)10310, (short)10556, (short)10576, (short)14646, (short)15020, (short)15040, (short)19766, (short)20140, (short)20160, (short)-29386, (short)-29012, (short)-28992, (short)31030, (short)31276, (short)31296, (short)-24266, (short)-23892, (short)-23872, (short)-19146, (short)-18772, (short)-18752, (short)-14026, (short)-13652, (short)-13632, (short)-6858, (short)-6484, (short)-6464, (short)522, (short)542, (short)6794, (short)6814, (short)11018, (short)11038, (short)14986, (short)15006, (short)21130, (short)21150, (short)-28918, (short)-28898, (short)-22006, (short)-21986, (short)-12918, (short)-12898, (short)10, (short)30, (short)50, (short)70, (short)90, (short)110, (short)310, (short)684, (short)704, (short)556, (short)940, (short)960, (short)6454, (short)6952, (short)6972, (short)2358, (short)2732, (short)2752, (short)10550, (short)10924, (short)10944, (short)10310, (short)10556, (short)10576, (short)14646, (short)15020, (short)15040, (short)19766, (short)20140, (short)20160, (short)-29386, (short)-29012, (short)-28992, (short)31030, (short)31276, (short)31296, (short)-24266, (short)-23892, (short)-23872, (short)-19146, (short)-18772, (short)-18752, (short)-14026, (short)-13652, (short)-13632, (short)-6858, (short)-6484, (short)-6464, (short)522, (short)542, (short)6794, (short)6814, (short)11018, (short)11038, (short)14986, (short)15006, (short)21130, (short)21150, (short)-28918, (short)-28898, (short)-22006, (short)-21986, (short)-12918, (short)-12898, (short)10347, (short)10582, (short)10429, (short)10407, (short)10359, (short)8414, (short)9540, (short)10456, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0}, {(short)10, (short)30, (short)50, (short)70, (short)90, (short)110, (short)310, (short)684, (short)704, (short)556, (short)940, (short)960, (short)6454, (short)6952, (short)6972, (short)2358, (short)2732, (short)2752, (short)10550, (short)10924, (short)10944, (short)10310, (short)10556, (short)10576, (short)14646, (short)15020, (short)15040, (short)19766, (short)20140, (short)20160, (short)-29386, (short)-29012, (short)-28992, (short)31030, (short)31276, (short)31296, (short)-24266, (short)-23892, (short)-23872, (short)-19146, (short)-18772, (short)-18752, (short)-14026, (short)-13652, (short)-13632, (short)-6858, (short)-6484, (short)-6464, (short)522, (short)542, (short)6794, (short)6814, (short)11018, (short)11038, (short)14986, (short)15006, (short)21130, (short)21150, (short)-28918, (short)-28898, (short)-22006, (short)-21986, (short)-12918, (short)-12898, (short)10, (short)30, (short)50, (short)70, (short)90, (short)110, (short)310, (short)684, (short)704, (short)556, (short)940, (short)960, (short)6454, (short)6952, (short)6972, (short)2358, (short)2732, (short)2752, (short)10550, (short)10924, (short)10944, (short)10310, (short)10556, (short)10576, (short)14646, (short)15020, (short)15040, (short)19766, (short)20140, (short)20160, (short)-29386, (short)-29012, (short)-28992, (short)31030, (short)31276, (short)31296, (short)-24266, (short)-23892, (short)-23872, (short)-19146, (short)-18772, (short)-18752, (short)-14026, (short)-13652, (short)-13632, (short)-6858, (short)-6484, (short)-6464, (short)522, (short)542, (short)6794, (short)6814, (short)11018, (short)11038, (short)14986, (short)15006, (short)21130, (short)21150, (short)-28918, (short)-28898, (short)-22006, (short)-21986, (short)-12918, (short)-12898, (short)10, (short)30, (short)50, (short)70, (short)90, (short)110, (short)310, (short)684, (short)704, (short)556, (short)940, (short)960, (short)6454, (short)6952, (short)6972, (short)2358, (short)2732, (short)2752, (short)10550, (short)10924, (short)10944, (short)10310, (short)10556, (short)10576, (short)14646, (short)15020, (short)15040, (short)19766, (short)20140, (short)20160, (short)-29386, (short)-29012, (short)-28992, (short)31030, (short)31276, (short)31296, (short)-24266, (short)-23892, (short)-23872, (short)-19146, (short)-18772, (short)-18752, (short)-14026, (short)-13652, (short)-13632, (short)-6858, (short)-6484, (short)-6464, (short)522, (short)542, (short)6794, (short)6814, (short)11018, (short)11038, (short)14986, (short)15006, (short)21130, (short)21150, (short)-28918, (short)-28898, (short)-22006, (short)-21986, (short)-12918, (short)-12898, (short)10, (short)30, (short)50, (short)70, (short)90, (short)110, (short)310, (short)684, (short)704, (short)556, (short)940, (short)960, (short)6454, (short)6952, (short)6972, (short)2358, (short)2732, (short)2752, (short)10550, (short)10924, (short)10944, (short)10310, (short)10556, (short)10576, (short)14646, (short)15020, (short)15040, (short)19766, (short)20140, (short)20160, (short)-29386, (short)-29012, (short)-28992, (short)31030, (short)31276, (short)31296, (short)-24266, (short)-23892, (short)-23872, (short)-19146, (short)-18772, (short)-18752, (short)-14026, (short)-13652, (short)-13632, (short)-6858, (short)-6484, (short)-6464, (short)522, (short)542, (short)6794, (short)6814, (short)11018, (short)11038, (short)14986, (short)15006, (short)21130, (short)21150, (short)-28918, (short)-28898, (short)-22006, (short)-21986, (short)-12918, (short)-12898}, {(short)4300, (short)3294, (short)3303, (short)3264, (short)4506, (short)4382, (short)4387, (short)5293, (short)7622, (short)7384, (short)8412, (short)7496, (short)86, (short)123, (short)111, (short)99, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)10, (short)30, (short)50, (short)70, (short)90, (short)110, (short)310, (short)684, (short)704, (short)556, (short)940, (short)960, (short)6454, (short)6952, (short)6972, (short)2358, (short)2732, (short)2752, (short)10550, (short)10924, (short)10944, (short)10310, (short)10556, (short)10576, (short)14646, (short)15020, (short)15040, (short)19766, (short)20140, (short)20160, (short)-29386, (short)-29012, (short)-28992, (short)31030, (short)31276, (short)31296, (short)-24266, (short)-23892, (short)-23872, (short)-19146, (short)-18772, (short)-18752, (short)-14026, (short)-13652, (short)-13632, (short)-6858, (short)-6484, (short)-6464, (short)522, (short)542, (short)6794, (short)6814, (short)11018, (short)11038, (short)14986, (short)15006, (short)21130, (short)21150, (short)-28918, (short)-28898, (short)-22006, (short)-21986, (short)-12918, (short)-12898, (short)10, (short)30, (short)50, (short)70, (short)90, (short)110, (short)310, (short)684, (short)704, (short)556, (short)940, (short)960, (short)6454, (short)6952, (short)6972, (short)2358, (short)2732, (short)2752, (short)10550, (short)10924, (short)10944, (short)10310, (short)10556, (short)10576, (short)14646, (short)15020, (short)15040, (short)19766, (short)20140, (short)20160, (short)-29386, (short)-29012, (short)-28992, (short)31030, (short)31276, (short)31296, (short)-24266, (short)-23892, (short)-23872, (short)-19146, (short)-18772, (short)-18752, (short)-14026, (short)-13652, (short)-13632, (short)-6858, (short)-6484, (short)-6464, (short)522, (short)542, (short)6794, (short)6814, (short)11018, (short)11038, (short)14986, (short)15006, (short)21130, (short)21150, (short)-28918, (short)-28898, (short)-22006, (short)-21986, (short)-12918, (short)-12898, (short)13766, (short)13745, (short)13726, (short)13890, (short)13743, (short)13852, (short)17602, (short)18605, (short)21660, (short)24000, (short)24997, (short)24088, (short)27972, (short)25903, (short)26904, (short)27193, (short)27175, (short)27156, (short)30020, (short)28975, (short)29976, (short)12482, (short)13485, (short)10392, (short)10692, (short)10669, (short)10776, (short)6717, (short)6695, (short)7830, (short)6971, (short)6951, (short)5910, (short)3389, (short)3369, (short)3356, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0}, {(short)10, (short)30, (short)50, (short)70, (short)90, (short)110, (short)310, (short)684, (short)704, (short)556, (short)940, (short)960, (short)6454, (short)6952, (short)6972, (short)2358, (short)2732, (short)2752, (short)10550, (short)10924, (short)10944, (short)10310, (short)10556, (short)10576, (short)14646, (short)15020, (short)15040, (short)19766, (short)20140, (short)20160, (short)-29386, (short)-29012, (short)-28992, (short)31030, (short)31276, (short)31296, (short)-24266, (short)-23892, (short)-23872, (short)-19146, (short)-18772, (short)-18752, (short)-14026, (short)-13652, (short)-13632, (short)-6858, (short)-6484, (short)-6464, (short)522, (short)542, (short)6794, (short)6814, (short)11018, (short)11038, (short)14986, (short)15006, (short)21130, (short)21150, (short)-28918, (short)-28898, (short)-22006, (short)-21986, (short)-12918, (short)-12898, (short)10, (short)30, (short)50, (short)70, (short)90, (short)110, (short)310, (short)684, (short)704, (short)556, (short)940, (short)960, (short)6454, (short)6952, (short)6972, (short)2358, (short)2732, (short)2752, (short)10550, (short)10924, (short)10944, (short)10310, (short)10556, (short)10576, (short)14646, (short)15020, (short)15040, (short)19766, (short)20140, (short)20160, (short)-29386, (short)-29012, (short)-28992, (short)31030, (short)31276, (short)31296, (short)-24266, (short)-23892, (short)-23872, (short)-19146, (short)-18772, (short)-18752, (short)-14026, (short)-13652, (short)-13632, (short)-6858, (short)-6484, (short)-6464, (short)522, (short)542, (short)6794, (short)6814, (short)11018, (short)11038, (short)14986, (short)15006, (short)21130, (short)21150, (short)-28918, (short)-28898, (short)-22006, (short)-21986, (short)-12918, (short)-12898, (short)10, (short)30, (short)50, (short)70, (short)90, (short)110, (short)310, (short)684, (short)704, (short)556, (short)940, (short)960, (short)6454, (short)6952, (short)6972, (short)2358, (short)2732, (short)2752, (short)10550, (short)10924, (short)10944, (short)10310, (short)10556, (short)10576, (short)14646, (short)15020, (short)15040, (short)19766, (short)20140, (short)20160, (short)-29386, (short)-29012, (short)-28992, (short)31030, (short)31276, (short)31296, (short)-24266, (short)-23892, (short)-23872, (short)-19146, (short)-18772, (short)-18752, (short)-14026, (short)-13652, (short)-13632, (short)-6858, (short)-6484, (short)-6464, (short)522, (short)542, (short)6794, (short)6814, (short)11018, (short)11038, (short)14986, (short)15006, (short)21130, (short)21150, (short)-28918, (short)-28898, (short)-22006, (short)-21986, (short)-12918, (short)-12898, (short)10, (short)30, (short)50, (short)70, (short)90, (short)110, (short)310, (short)684, (short)704, (short)556, (short)940, (short)960, (short)6454, (short)6952, (short)6972, (short)2358, (short)2732, (short)2752, (short)10550, (short)10924, (short)10944, (short)10310, (short)10556, (short)10576, (short)14646, (short)15020, (short)15040, (short)19766, (short)20140, (short)20160, (short)-29386, (short)-29012, (short)-28992, (short)31030, (short)31276, (short)31296, (short)-24266, (short)-23892, (short)-23872, (short)-19146, (short)-18772, (short)-18752, (short)-14026, (short)-13652, (short)-13632, (short)-6858, (short)-6484, (short)-6464, (short)522, (short)542, (short)6794, (short)6814, (short)11018, (short)11038, (short)14986, (short)15006, (short)21130, (short)21150, (short)-28918, (short)-28898, (short)-22006, (short)-21986, (short)-12918, (short)-12898}};
    static int anInt3671 = 0;
    static boolean[] aBooleanArray3674 = new boolean[100];
    static int anInt2281 = 0;
    static int anInt2309 = 128;
    static boolean aBoolean2311;
    static int anInt2334;
    static int anInt3071 = 0;
    static int anInt3070 = 0;
    static int[] anIntArray3076;
    static int anInt3155;
    static WorldListEntry[] aClass44_Sub1Array3201 = new WorldListEntry[0];
    static CacheIndex aClass153_3361;
    static Class52 aClass52_1112 = new Class52();
    static int anInt1165 = -1;
    static byte[][][] aByteArrayArrayArray1774;
    static int[][] anIntArrayArray2039 = new int[][]{{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, {12, 8, 4, 0, 13, 9, 5, 1, 14, 10, 6, 2, 15, 11, 7, 3}, {15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, {3, 7, 11, 15, 2, 6, 10, 14, 1, 5, 9, 13, 0, 4, 8, 12}};
    static int menuOptionCount = 0;


    static void method2086() {
        try {
            int var1 = Class102.player.anInt2819 + Class3_Sub13_Sub13.anInt3216;
            int var2 = Class102.player.anInt2829 - -anInt42;
            if (-var1 + anInt3155 < -500 || -var1 + anInt3155 > 500 || anInt942 + -var2 < -500 || -var2 + anInt942 > 500) {
                anInt3155 = var1;
                anInt942 = var2;
            }

            if (var2 != anInt942) {
                anInt942 += (-anInt942 + var2) / 16;
            }

            if (var1 != anInt3155) {
                anInt3155 += (-anInt3155 + var1) / 16;
            }

            if (Class15.aBoolean346) {
                for (int var3 = 0; var3 < Class3_Sub23.anInt2537; ++var3) {
                    int var4 = Class133.inputTextCodeArray[var3];
                    if (98 == var4) {
                        anInt2309 = -16 & anInt2309 + 47;
                    } else if (var4 == 99) {
                        anInt2309 = -16 & anInt2309 - 17;
                    } else if (var4 == 96) {
                        GraphicDefinition.CAMERA_DIRECTION = GraphicDefinition.CAMERA_DIRECTION - 65 & -128;
                    } else if (var4 == 97) {
                        GraphicDefinition.CAMERA_DIRECTION = GraphicDefinition.CAMERA_DIRECTION + 191 & -128;
                    }
                }
            } else {
                if (ObjectDefinition.aBooleanArray1490[98]) {
                    Class27.anInt517 += (-Class27.anInt517 + 12) / 2;
                } else if (!ObjectDefinition.aBooleanArray1490[99]) {
                    Class27.anInt517 /= 2;
                } else {
                    Class27.anInt517 += (-Class27.anInt517 + -12) / 2;
                }

                if (!ObjectDefinition.aBooleanArray1490[96]) {
                    if (ObjectDefinition.aBooleanArray1490[97]) {
                        anInt2281 += (-anInt2281 + 24) / 2;
                    } else {
                        anInt2281 /= 2;
                    }
                } else {
                    anInt2281 += (-anInt2281 + -24) / 2;
                }

                anInt2309 += Class27.anInt517 / 2;
                GraphicDefinition.CAMERA_DIRECTION += anInt2281 / 2;
            }

            method1098((byte) -94);
        } catch (RuntimeException var5) {
            throw ClientErrorException.clientError(var5, "uf.B(" + (byte) 68 + ')');
        }
    }

    static boolean method1529(int var0, int var1, int var2, int var3, int var4, int var6) {
        try {
            long var8 = Class157.method2174(var6, var1, var3 + var0);
            int var10;
            int var11;
            int var12;
            ObjectDefinition var13;
            int var14;
            int[] var15;
            int var16;
            if (var8 != 0) {
                var10 = 3 & (int) var8 >> 20;
                var11 = (508650 & (int) var8) >> 14;
                var12 = Integer.MAX_VALUE & (int) (var8 >>> 32);
                var13 = ObjectDefinition.getObjectDefinition(var12);
                if (var13.anInt1516 == -1) {
                    var14 = var2;
                    if (var8 > 0) {
                        var14 = var4;
                    }

                    var15 = Toolkit.JAVA_TOOLKIT.getBuffer();
                    var16 = 4 * (-(var3 * 512) + '\uce00') + var1 * 4 + 24624;
                    if (var11 == 0 || var11 == 2) {
                        if (var10 == 0) {
                            var15[var16] = var14;
                            var15[512 + var16] = var14;
                            var15[var16 - -1024] = var14;
                            var15[1536 + var16] = var14;
                        } else if (var10 == 1) {
                            var15[var16] = var14;
                            var15[1 + var16] = var14;
                            var15[var16 - -2] = var14;
                            var15[var16 - -3] = var14;
                        } else if (var10 == 2) {
                            var15[var16 - -3] = var14;
                            var15[var16 - -3 - -512] = var14;
                            var15[var16 - -3 + 1024] = var14;
                            var15[var16 + 3 + 1536] = var14;
                        } else {
                            var15[var16 + 1536] = var14;
                            var15[1536 + var16 - -1] = var14;
                            var15[var16 + 1538] = var14;
                            var15[3 + var16 + 1536] = var14;
                        }
                    }

                    if (var11 == 3) {
                        if (var10 == 0) {
                            var15[var16] = var14;
                        } else if (1 == var10) {
                            var15[var16 - -3] = var14;
                        } else if (var10 == 2) {
                            var15[var16 - -3 + 1536] = var14;
                        } else {
                            var15[var16 - -1536] = var14;
                        }
                    }

                    if (var11 == 2) {
                        if (var10 == 3) {
                            var15[var16] = var14;
                            var15[var16 - -512] = var14;
                            var15[var16 + 1024] = var14;
                            var15[1536 + var16] = var14;
                        } else if (var10 == 0) {
                            var15[var16] = var14;
                            var15[1 + var16] = var14;
                            var15[2 + var16] = var14;
                            var15[3 + var16] = var14;
                        } else if (var10 == 1) {
                            var15[var16 - -3] = var14;
                            var15[512 + 3 + var16] = var14;
                            var15[3 + (var16 - -1024)] = var14;
                            var15[1536 + var16 + 3] = var14;
                        } else {
                            var15[1536 + var16] = var14;
                            var15[var16 - -1536 + 1] = var14;
                            var15[1536 + var16 + 2] = var14;
                            var15[var16 + 1539] = var14;
                        }
                    }
                } else if (Class15.method888(var1, var13, var0, 0, var3, var10)) {
                    return false;
                }
            }

            var8 = Class3_Sub28_Sub5.method557(var6, var1, var0 + var3);
            if (var8 != 0L) {
                var10 = (int) var8 >> 20 & 3;
                var11 = ((int) var8 & 520964) >> 14;
                var12 = (int) (var8 >>> 32) & Integer.MAX_VALUE;
                var13 = ObjectDefinition.getObjectDefinition(var12);
                if (var13.anInt1516 != -1) {
                    if (Class15.method888(var1, var13, var0, 0, var3, var10)) {
                        return false;
                    }
                } else if (var11 == 9) {
                    var14 = 15658734;
                    if (var8 > 0) {
                        var14 = 15597568;
                    }

                    var16 = var1 * 4 + (24624 - -(2048 * (103 - var3)));
                    var15 = Toolkit.JAVA_TOOLKIT.getBuffer();
                    if (var10 == 0 || var10 == 2) {
                        var15[1536 + var16] = var14;
                        var15[var16 - -1025] = var14;
                        var15[var16 + 512 + 2] = var14;
                        var15[var16 - -3] = var14;
                    } else {
                        var15[var16] = var14;
                        var15[var16 - -512 - -1] = var14;
                        var15[var16 - -1024 - -2] = var14;
                        var15[1536 + var16 - -3] = var14;
                    }
                }
            }

            var8 = Class3_Sub2.method104(var6, var1, var3 + var0);
            if (var8 != 0L) {
                var10 = (int) var8 >> 20 & 3;
                var11 = (int) (var8 >>> 32) & Integer.MAX_VALUE;
                ObjectDefinition var18 = ObjectDefinition.getObjectDefinition(var11);
                return var18.anInt1516 == -1 || !Class15.method888(var1, var18, var0, 0, var3, var10);
            }

            return true;
        } catch (RuntimeException var17) {
            throw ClientErrorException.clientError(var17, "na.N(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + 0 + ',' + var6 + ',' + true + ')');
        }
    }

    static int method1535(WorldListEntry var0, WorldListEntry var1, int var2, int var3, int var4, boolean var5, boolean var6) {
        try {
            int var7 = Class161.method2201(var1, var4, var2 + -5638, var0, var6);
            if (var7 == 0) {
                if (var2 != 5730) {
                    return -76;
                } else if (var3 == -1) {
                    return 0;
                } else {
                    int var8 = Class161.method2201(var1, var3, var2 ^ 5651, var0, var5);
                    return !var5 ? var8 : -var8;
                }
            } else {
                return !var6 ? var7 : -var7;
            }
        } catch (RuntimeException var9) {
            throw ClientErrorException.clientError(var9, "na.D(" + (var0 != null ? "{...}" : "null") + ',' + (var1 != null ? "{...}" : "null") + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ')');
        }
    }

    static Class3_Sub28_Sub16_Sub2 method1537(CacheIndex var0, int var1) {
        try {
            if (Class140_Sub7.method2029((byte) -118, var0, var1)) {

                return Class117.method1722(-93);
            } else {
                return null;
            }
        } catch (RuntimeException var4) {
            throw ClientErrorException.clientError(var4, "na.GB(" + (var0 != null ? "{...}" : "null") + ',' + var1 + ',' + false + ')');
        }
    }

    static LDIndexedSprite method1539(int var2, CacheIndex var3) {
        try {
            //  System.out.println("RSString " + var2);
            return Class75_Sub4.method1351(var3, 0, var2, -30901) ? Class77.method1364() : null;
        } catch (RuntimeException var5) {
            throw ClientErrorException.clientError(var5, "na.MA(" + 0 + ',' + true + ',' + var2 + ',' + (var3 != null ? "{...}" : "null") + ')');
        }
    }

    static AbstractSprite method1570(int var0, byte var1, boolean var2, int var3, boolean var4, int var5, int var6, boolean var7) {
        try {
            ItemDefinition item = Class38.getItemDefinition(var3);
            if (var6 > 1 && item.anIntArray804 != null) {
                int var9 = -1;

                for (int var10 = 0; var10 < 10; ++var10) {
                    if (item.anIntArray766[var10] <= var6 && item.anIntArray766[var10] != 0) {
                        var9 = item.anIntArray804[var10];
                    }
                }

                if (var9 != -1) {
                    item = Class38.getItemDefinition(var9);
                }
            }

            Class140_Sub1_Sub2 var21 = item.method1120();
            if (null == var21) {
                return null;
            } else {
                Class3_Sub28_Sub16_Sub2 var22 = null;
                if (item.anInt791 == -1) {
                    if (item.anInt762 != -1) {
                        var22 = (Class3_Sub28_Sub16_Sub2) method1570(var0, (byte) -107, true, item.anInt795, false, var5, var6, false);
                        if (null == var22) {
                            return null;
                        }
                    }
                } else {
                    var22 = (Class3_Sub28_Sub16_Sub2) method1570(0, (byte) 116, true, item.anInt789, false, 1, 10, true);
                    if (null == var22) {
                        return null;
                    }
                }

                int[] var11 = Toolkit.JAVA_TOOLKIT.getBuffer();
                int var12 = Toolkit.JAVA_TOOLKIT.width;
                int var13 = Toolkit.JAVA_TOOLKIT.height;
                int[] var14 = new int[4];
                Class74.method1325(var14);
                Class3_Sub28_Sub16_Sub2 var15 = new Class3_Sub28_Sub16_Sub2(36, 32);
                Class74.setBuffer(var15.anIntArray4081, 36, 32);
                Class51.method1134();
                Class51.method1145(16, 16);
                int var16 = item.anInt810;
                Class51.aBoolean843 = false;
                if (var7) {
                    var16 = (int) ((double) var16 * 1.5D);
                } else if (var5 == 2) {
                    var16 = (int) (1.04D * (double) var16);
                }

                int var18 = Class51.anIntArray851[item.anInt786] * var16 >> 16;
                int var17 = Class51.anIntArray840[item.anInt786] * var16 >> 16;
                var21.method1893(item.anInt799, item.anInt768, item.anInt786, item.anInt792, var17 - (var21.method1871() / 2 + -item.anInt754), item.anInt754 + var18);
                if (var5 >= 1) {
                    var15.method657(1);
                    if (var5 >= 2) {
                        var15.method657(16777215);
                    }

                    Class74.setBuffer(var15.anIntArray4081, 36, 32);
                }

                if (var0 != 0) {
                    var15.method668(var0);
                }

                if (item.anInt791 != -1) {
                    Objects.requireNonNull(var22).method643(0, 0);
                } else if (-1 != item.anInt762) {
                    Class74.setBuffer(Objects.requireNonNull(var22).anIntArray4081, 36, 32);
                    var15.method643(0, 0);
                    var15 = var22;
                }

                if (var4 && (item.stackingType == 1 || var6 != 1) && var6 != -1) {
                    Class3_Sub13_Sub37.aClass3_Sub28_Sub17_Sub1_3440.method681(Class3_Sub7.itemStackColor(1000, var6), 0, 9, 16776960, 1);
                }

                Class74.setBuffer(var11, var12, var13);
                Class74.setClipping(var14);
                Class51.method1134();
                Class51.aBoolean843 = true;
                return (AbstractSprite) (HDToolKit.highDetail && !var2 ? new HDSprite(var15) : var15);
            }
        } catch (RuntimeException var20) {
            throw ClientErrorException.clientError(var20, "na.WA(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
        }
    }

    static RSString method802(int var0) {
        try {

            return Class163_Sub2_Sub1.aClass94Array4016[var0].length() > 0 ? RSString.stringCombiner(new RSString[]{Class140_Sub7.aClass94Array2935[var0], TextCore.Spacer, Class163_Sub2_Sub1.aClass94Array4016[var0]}) : Class140_Sub7.aClass94Array2935[var0];
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "wa.RB(" + var0 + ',' + true + ')');
        }
    }

    public static void method83(byte var0) {
        try {
            ItemDefinition.stringsStack = null;
            if (var0 != 30) {
                method84((RSString) null, 89);
            }

            aClass61_78 = null;
            aBooleanArray73 = null;
        } catch (RuntimeException var2) {
            throw ClientErrorException.clientError(var2, "ab.H(" + var0 + ')');
        }
    }

    static void calculateInterfacePosition(RSInterface iface, int parentWidth, int parentHeight) {
        if (iface.verticalPos == 0) {
            iface.anInt210 = iface.defY;
        } else if (iface.verticalPos == 1) {
            iface.anInt210 = (parentHeight - iface.height) / 2 + iface.defY;
        } else if (iface.verticalPos == 2) {
            iface.anInt210 = parentHeight - iface.height - iface.defY;
        } else if (iface.verticalPos == 3) {
            iface.anInt210 = iface.defY * parentHeight >> 14;
        } else if (iface.verticalPos == 4) {
            iface.anInt210 = (parentHeight * iface.defY >> 14) + (-iface.height + parentHeight) / 2;
        } else {
            iface.anInt210 = -(parentHeight * iface.defY >> 14) + -iface.height + parentHeight;
        }

        if (0 == iface.horizontalPos) {
            iface.anInt306 = iface.defX;
        } else if (iface.horizontalPos == 1) {
            iface.anInt306 = iface.defX + (parentWidth - iface.width) / 2;
        } else if (iface.horizontalPos == 2) {
            iface.anInt306 = -iface.defX + -iface.width + parentWidth;
        } else if (3 == iface.horizontalPos) {
            iface.anInt306 = iface.defX * parentWidth >> 14;
        } else if (4 == iface.horizontalPos) {
            iface.anInt306 = (iface.defX * parentWidth >> 14) + (parentWidth - iface.width) / 2;
        } else {
            iface.anInt306 = -(parentWidth * iface.defX >> 14) + parentWidth + -iface.width;
        }

        if (ClientCommands.commandQaOpEnabled && (Client.method44(iface).anInt2205 != 0 || iface.type == 0)) {
            if (iface.anInt210 < 0) {
                iface.anInt210 = 0;
            } else if (iface.height + iface.anInt210 > parentHeight) {
                iface.anInt210 = parentHeight + -iface.height;
            }

            if (0 > iface.anInt306) {
                iface.anInt306 = 0;
            } else if (parentWidth < iface.anInt306 - -iface.width) {
                iface.anInt306 = parentWidth + -iface.width;
            }
        }
    }

    static void method1225() {
        try {
            MouseListeningClass var1 = aClass149_4047;
            synchronized (var1) {

                Class3_Sub13_Sub5.anInt3069 = GraphicDefinition.anInt549;
                Class126.anInt1676 = Class3_Sub21.anInt2493;
                anInt1709 = MouseListeningClass.anInt1340;
                anInt3644 = Class140_Sub3.anInt2743;
                Class163_Sub1.anInt2993 = RenderAnimationDefinition.anInt362;
                ++anInt4045;
                Class38_Sub1.anInt2614 = Class3_Sub13_Sub32.anInt3389;
                Class75.aLong1102 = Class140_Sub6.aLong2926;
                Class140_Sub3.anInt2743 = 0;
            }
        } catch (RuntimeException var4) {
            throw ClientErrorException.clientError(var4, "ii.D(" + 18074 + ')');
        }
    }

    static void method848() {
        try {
            if (Class44.aFloat727 < NPC.aFloat3979) {
                Class44.aFloat727 = (float) ((double) Class44.aFloat727 + (double) Class44.aFloat727 / 30.0D);
                if (NPC.aFloat3979 < Class44.aFloat727) {
                    Class44.aFloat727 = NPC.aFloat3979;
                }

                Class3_Sub5.method117();
            } else if (NPC.aFloat3979 < Class44.aFloat727) {
                Class44.aFloat727 = (float) ((double) Class44.aFloat727 - (double) Class44.aFloat727 / 30.0D);
                if (NPC.aFloat3979 > Class44.aFloat727) {
                    Class44.aFloat727 = NPC.aFloat3979;
                }

                Class3_Sub5.method117();
            }

            if (Class82.anInt1150 != -1 && -1 != Class3_Sub13_Sub30.anInt3362) {
                int var1 = -Class3_Sub28_Sub1.anInt3536 + Class82.anInt1150;
                if (2 > var1 || var1 > 2) {
                    var1 >>= 4;
                }

                int var2 = -Class3_Sub4.anInt2251 + Class3_Sub13_Sub30.anInt3362;
                if (var2 < 2 || var2 > 2) {
                    var2 >>= 4;
                }

                Class3_Sub4.anInt2251 -= -var2;
                Class3_Sub28_Sub1.anInt3536 += var1;
                if (0 == var1 && 0 == var2) {
                    Class82.anInt1150 = -1;
                    Class3_Sub13_Sub30.anInt3362 = -1;
                }

                Class3_Sub5.method117();
            }

        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "bb.A(" + 4 + ')');
        }
    }

    static int bitwiseAnd(int var0, int var1) {
        try {
            return var0 & var1;
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "qc.C(" + var0 + ',' + var1 + ')');
        }
    }

    static LDIndexedSprite[] method1281() {
        try {
            LDIndexedSprite[] var1 = new LDIndexedSprite[Class95.anInt1338];

            for (int var2 = 0; Class95.anInt1338 > var2; ++var2) {
                var1[var2] = new LDIndexedSprite(Class3_Sub15.anInt2426, Class133.anInt1748, Class164.anIntArray2048[var2], anIntArray2591[var2], Class140_Sub7.anIntArray2931[var2], anIntArray3076[var2], Class163_Sub1.aByteArrayArray2987[var2], Class3_Sub13_Sub38.spritePalette);
            }

            Class39.method1035((byte) 116);
            return var1;
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "jg.A(" + 0 + ')');
        }
    }

    static void method1282(int var0, byte var1, int var2, int var3, int var4) {
        try {
            for (int var5 = 0; var5 < Class3_Sub28_Sub3.anInt3557; ++var5) {
                if (var0 < Class155.anIntArray1969[var5] - -Class3_Sub28_Sub18.anIntArray3768[var5] && var0 + var4 > Class155.anIntArray1969[var5] && var2 < Player.anIntArray3954[var5] - -Class140_Sub4.anIntArray2794[var5] && var3 + var2 > Player.anIntArray3954[var5]) {
                    Class163_Sub1_Sub1.aBooleanArray4008[var5] = true;
                }
            }

        } catch (RuntimeException var7) {
            throw ClientErrorException.clientError(var7, "jg.C(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
        }
    }

    static Class3_Sub15 method1245(CacheIndex var1, int var2) {
        try {

            byte[] var3 = var1.method2138(var2);
            return var3 != null ? new Class3_Sub15(var3) : null;
        } catch (RuntimeException var4) {
            throw ClientErrorException.clientError(var4, "jb.F(" + 117 + ',' + (var1 != null ? "{...}" : "null") + ',' + var2 + ')');
        }
    }

    static void method1250(int var0, boolean var1) {
        try {
            Class3_Sub10.aByteArrayArrayArray2339 = (byte[][][]) null;
            Class44.anIntArrayArrayArray720 = (int[][][]) null;
            if (var0 < 14) {
                method1250(10, true);
            }

            Class3_Sub28_Sub3.aClass11_3551 = null;
            RenderAnimationDefinition.aByteArrayArrayArray383 = (byte[][][]) null;
            Class83.anIntArray1161 = null;
            CS2Script.aByteArrayArrayArray2452 = (byte[][][]) null;
            if (var1 && null != aClass3_Sub28_Sub3_2600) {
                Class3_Sub20.aClass94_3220 = aClass3_Sub28_Sub3_2600.aClass94_3561;
            } else {
                Class3_Sub20.aClass94_3220 = null;
            }

            Class36.aByteArrayArrayArray640 = (byte[][][]) null;
            Class3_Sub13_Sub33.aByteArrayArrayArray3390 = (byte[][][]) null;
            Class29.anIntArrayArrayArray558 = (int[][][]) null;
            Class146.anIntArrayArrayArray1903 = (int[][][]) null;
            WorldMap.anInt2737 = 0;
            aClass3_Sub28_Sub3_2600 = null;
            Class84.aClass61_1162.method1211(-108);
            Class119.aClass131_1624 = null;
            Class3_Sub13_Sub30.anInt3362 = -1;
            Class75_Sub2.aClass33_2648 = null;
            Class91.aClass33_1305 = null;
            aClass33_1238 = null;
            Class161.aClass33_2034 = null;
            Class164_Sub2.aClass33_3019 = null;
            Class99.aClass33_1399 = null;
            Class75_Sub2.aClass33_2637 = null;
            Class119.aClass33_1626 = null;
            Class36.aAbstractSprite_637 = null;
            Class82.anInt1150 = -1;
            WorldMap.aClass3_Sub28_Sub16_Sub2_3221 = null;
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "jb.E(" + var0 + ',' + var1 + ')');
        }
    }

    public static int[] method62() {
        try {

            int[] var8 = new int[2048];
            Class3_Sub13_Sub4 var9 = new Class3_Sub13_Sub4();
            var9.anInt3060 = 8;
            var9.anInt3058 = 4;
            var9.anInt3067 = 35;
            var9.anInt3056 = 8;
            var9.anInt3062 = (int) ((float) 0.4 * 4096.0F);
            var9.aBoolean3065 = true;
            var9.method158(16251);
            Class3_Sub13_Sub3.method180(-106, 1, 2048);
            var9.method186(0, var8);
            return var8;
        } catch (RuntimeException var10) {
            throw ClientErrorException.clientError(var10, "qk.A(" + true + ',' + 14585 + ',' + 8 + ',' + 2048 + ',' + 4 + ',' + (float) 0.4 + ',' + 8 + ',' + 35 + ')');
        }
    }

    public static Class3_Sub13 method63(DataBuffer var1) {
        try {
            var1.readUnsignedByte();
            int var2 = var1.readUnsignedByte();
            Class3_Sub13 var3 = method1777(var2);
            Objects.requireNonNull(var3).anInt2381 = var1.readUnsignedByte();
            int var4 = var1.readUnsignedByte();
            for (int var5 = 0; var5 < var4; ++var5) {
                int var6 = var1.readUnsignedByte();
                var3.method157(var6, var1, true);
            }

            var3.method158(16251);
            return var3;
        } catch (RuntimeException var7) {
            throw ClientErrorException.clientError(var7, "qk.B(" + (byte) -67 + ',' + (var1 != null ? "{...}" : "null") + ')');
        }
    }

    public static int method64(int var1) {
        try {
            return var1 >>> 8;
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "qk.D(" + true + ',' + var1 + ')');
        }
    }

    public static short[] method65(short[] var1) {
        try {
            if (null == var1) {
                return null;
            } else {
                short[] var2 = new short[var1.length];
                ArrayUtils.arraycopy(var1, 0, var2, 0, var1.length);
                return var2;
            }
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "vg.A(" + 23032 + ',' + "{...}" + ')');
        }
    }

    public static void method66(RSString var0, int var1, int var2, byte var3, int var4) {
        try {
            RSInterface var5 = AbstractSprite.method638(var4, var1);
            if (null != var5) {
                if (var5.anObjectArray314 != null) {
                    CS2Script var6 = new CS2Script();
                    var6.arguments = var5.anObjectArray314;
                    var6.aClass11_2449 = var5;
                    var6.aClass94_2439 = var0;
                    var6.interfaceButtons = var2; // Set to 1 for jukebox/friends/all/game chat. set to non 1 for all other chat settings
                    Class43.method1065(var6);
                }

                boolean var8 = true;
                if (0 < var5.anInt189) {
                    var8 = Class3_Sub30_Sub1.method715(var5);
                }

                if (var8) {
                    if (Client.method44(var5).method92(var2 - 1, (byte) -108)) {
                        if (1 == var2) {
                            Class3_Sub13_Sub1.outgoingBuffer.putOpcode(155);
                            Class3_Sub13_Sub1.outgoingBuffer.writeInt(var4);
                            Class3_Sub13_Sub1.outgoingBuffer.writeShort(var1);
                        }

                        if (var3 < -7) {
                            if (var2 == 2) {
                                Class3_Sub13_Sub1.outgoingBuffer.putOpcode(196);
                                Class3_Sub13_Sub1.outgoingBuffer.writeInt(var4);
                                Class3_Sub13_Sub1.outgoingBuffer.writeShort(var1);
                            }

                            if (var2 == 3) {
                                Class3_Sub13_Sub1.outgoingBuffer.putOpcode(124);
                                Class3_Sub13_Sub1.outgoingBuffer.writeInt(var4);
                                Class3_Sub13_Sub1.outgoingBuffer.writeShort(var1);
                            }

                            if (var2 == 4) {
                                Class3_Sub13_Sub1.outgoingBuffer.putOpcode(199);
                                Class3_Sub13_Sub1.outgoingBuffer.writeInt(var4);
                                Class3_Sub13_Sub1.outgoingBuffer.writeShort(var1);
                            }

                            if (var2 == 5) {
                                Class3_Sub13_Sub1.outgoingBuffer.putOpcode(234);
                                Class3_Sub13_Sub1.outgoingBuffer.writeInt(var4);
                                Class3_Sub13_Sub1.outgoingBuffer.writeShort(var1);
                            }

                            if (6 == var2) {
                                Class3_Sub13_Sub1.outgoingBuffer.putOpcode(168);
                                Class3_Sub13_Sub1.outgoingBuffer.writeInt(var4);
                                Class3_Sub13_Sub1.outgoingBuffer.writeShort(var1);
                            }

                            if (var2 == 7) {
                                Class3_Sub13_Sub1.outgoingBuffer.putOpcode(166);
                                Class3_Sub13_Sub1.outgoingBuffer.writeInt(var4);
                                Class3_Sub13_Sub1.outgoingBuffer.writeShort(var1);
                            }

                            if (var2 == 8) {
                                Class3_Sub13_Sub1.outgoingBuffer.putOpcode(64);
                                Class3_Sub13_Sub1.outgoingBuffer.writeInt(var4);
                                Class3_Sub13_Sub1.outgoingBuffer.writeShort(var1);
                            }

                            if (var2 == 9) {
                                Class3_Sub13_Sub1.outgoingBuffer.putOpcode(53);
                                Class3_Sub13_Sub1.outgoingBuffer.writeInt(var4);
                                Class3_Sub13_Sub1.outgoingBuffer.writeShort(var1);
                            }

                            if (var2 == 10) {
                                Class3_Sub13_Sub1.outgoingBuffer.putOpcode(9);
                                Class3_Sub13_Sub1.outgoingBuffer.writeInt(var4);
                                Class3_Sub13_Sub1.outgoingBuffer.writeShort(var1);
                            }

                        }
                    }
                }
            }
        } catch (RuntimeException var7) {
            throw ClientErrorException.clientError(var7, "vg.D(" + (var0 != null ? "{...}" : "null") + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
        }
    }

    public static void method68(int var0, Class140_Sub4 var2) {
        try {
            if (var2.anInt2800 <= Class44.anInt719) {
                if (var2.anInt2790 >= Class44.anInt719) {
                    Class168.method2270(var2);
                } else {
                    method1180((byte) -22, var2);
                }
            } else {
                Class140_Sub2.method1950(var2);
            }

            if (var2.anInt2819 < 128 || var2.anInt2829 < 128 || var2.anInt2819 >= 13184 || var2.anInt2829 >= 13184) {
                var2.anInt2771 = -1;
                var2.anInt2842 = -1;
                var2.anInt2800 = 0;
                var2.anInt2790 = 0;
                var2.anInt2819 = 128 * var2.anIntArray2767[0] - -(64 * var2.getSize());
                var2.anInt2829 = var2.anIntArray2755[0] * 128 + var2.getSize() * 64;
                var2.method1973(2279 + -2395);
            }

            if (var2 == Class102.player && (var2.anInt2819 < 1536 || var2.anInt2829 < 1536 || var2.anInt2819 >= 11776 || var2.anInt2829 >= 11776)) {
                var2.anInt2842 = -1;
                var2.anInt2800 = 0;
                var2.anInt2790 = 0;
                var2.anInt2771 = -1;
                var2.anInt2819 = var2.anIntArray2767[0] * 128 + var2.getSize() * 64;
                var2.anInt2829 = 128 * var2.anIntArray2755[0] + 64 * var2.getSize();
                var2.method1973(-98);
            }

            Class17.method904(65536, var2);
            RenderAnimationDefinition.method900(var2, -11973);
        } catch (RuntimeException var4) {
            throw ClientErrorException.clientError(var4, "vg.C(" + var0 + ',' + 2279 + ',' + (var2 != null ? "{...}" : "null") + ')');
        }
    }

    public static void method1470(int var0, AnimationDefinition var1, int var2, int var3, boolean var4, int var5) {
        try {
            if (Class113.anInt1552 < 50) {
                if (var1.anIntArrayArray1867 != null && var1.anIntArrayArray1867.length > var5 && null != var1.anIntArrayArray1867[var5]) {
                    int var6 = var1.anIntArrayArray1867[var5][0];
                    int var7 = var6 >> 8;
                    int var10;
                    if (1 < var1.anIntArrayArray1867[var5].length) {
                        var10 = (int) ((double) var1.anIntArrayArray1867[var5].length * Math.random());
                        if (0 < var10) {
                            var7 = var1.anIntArrayArray1867[var5][var10];
                        }
                    }

                    int var8 = var6 >> 5 & 7;
                    int var9 = var6 & 31;
                    if (var9 == 0) {
                        if (var4) {
                            Class3_Sub13_Sub6.method199(var8, var7, 0);
                        }

                    } else if (0 != Sprites.anInt340) {
                        Class3_Sub25.anIntArray2550[Class113.anInt1552] = var7;
                        Class166.anIntArray2068[Class113.anInt1552] = var8;
                        int var11 = (-64 + var0) / 128;
                        var10 = (var3 + -64) / 128;
                        anIntArray2157[Class113.anInt1552] = 0;
                        Class102.aClass135Array2131[Class113.anInt1552] = null;
                        Class3_Sub8.anIntArray3083[Class113.anInt1552] = var9 + (var10 << 16) + (var11 << 8);
                        if (var2 != 183921384) {
                            aClass33_1238 = (Class33) null;
                        }

                        ++Class113.anInt1552;
                    }
                }
            }
        } catch (RuntimeException var12) {
            throw ClientErrorException.clientError(var12, "ma.C(" + var0 + ',' + (var1 != null ? "{...}" : "null") + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
        }
    }

    public static void method1460(int var0, int var1, int var3, int var4, int var5, int var6) {
        try {
            if (Class101.anInt1425 <= var5 - var4 && Class3_Sub28_Sub18.anInt3765 >= var5 - -var4 && Class159.anInt2020 <= -var4 + var1 && Class57.anInt902 >= var4 + var1) {
                Class3_Sub13_Sub2.method175(var6, var0, var1, var3, var4, var5);
            } else {
                Class168.method2275(var3, var1, var4, var6, var0, var5);
            }

        } catch (RuntimeException var8) {
            throw ClientErrorException.clientError(var8, "ma.A(" + var0 + ',' + var1 + ',' + (byte) -113 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ')');
        }
    }

    public static void method1469(int[] var0, int var1, int var2, int var3, int var4, int var5) {
        Class3_Sub2 var6 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var3][var4][var5];
        if (var6 != null) {
            Class126 var7 = var6.aClass126_2240;
            int var9;
            if (var7 == null) {
                Class35 var8 = var6.aClass35_2226;
                if (var8 != null) {
                    var9 = var8.anInt611;
                    int var10 = var8.anInt612;
                    int var11 = var8.anInt626;
                    int var12 = var8.anInt621;
                    int[] var13 = Class3_Sub13_Sub18.anIntArrayArray3215[var9];
                    int[] var14 = anIntArrayArray2039[var10];
                    int var15 = 0;
                    int var16;
                    if (var11 == 0) {
                        for (var16 = 0; var16 < 4; ++var16) {
                            if (var13[var14[var15++]] != 0) {
                                var0[var1] = var12;
                            }

                            if (var13[var14[var15++]] != 0) {
                                var0[var1 + 1] = var12;
                            }

                            if (var13[var14[var15++]] != 0) {
                                var0[var1 + 2] = var12;
                            }

                            if (var13[var14[var15++]] != 0) {
                                var0[var1 + 3] = var12;
                            }

                            var1 += var2;
                        }
                    } else {
                        for (var16 = 0; var16 < 4; ++var16) {
                            var0[var1] = var13[var14[var15++]] == 0 ? var11 : var12;
                            var0[var1 + 1] = var13[var14[var15++]] == 0 ? var11 : var12;
                            var0[var1 + 2] = var13[var14[var15++]] == 0 ? var11 : var12;
                            var0[var1 + 3] = var13[var14[var15++]] == 0 ? var11 : var12;
                            var1 += var2;
                        }
                    }

                }
            } else {
                int var17 = var7.anInt1673;
                if (var17 != 0) {
                    for (var9 = 0; var9 < 4; ++var9) {
                        var0[var1] = var17;
                        var0[var1 + 1] = var17;
                        var0[var1 + 2] = var17;
                        var0[var1 + 3] = var17;
                        var1 += var2;
                    }

                }
            }
        }
    }

    public static boolean method2096(int var0, int var1, int var2, long var3) {
        Class3_Sub2 var5 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2];
        if (var5 == null) {
            return false;
        } else if (var5.aClass70_2234 != null && var5.aClass70_2234.aLong1048 == var3) {
            return true;
        } else if (var5.aClass19_2233 != null && var5.aClass19_2233.aLong428 == var3) {
            return true;
        } else if (var5.aClass12_2230 != null && var5.aClass12_2230.aLong328 == var3) {
            return true;
        } else {
            for (int var6 = 0; var6 < var5.anInt2223; ++var6) {
                if (var5.aClass25Array2221[var6].aLong498 == var3) {
                    return true;
                }
            }

            return false;
        }
    }

    public static void method2099(int var1, CacheIndex var3, int var5) {
        try {
            Class101.aClass153_1423 = var3;
            Class132.anInt1741 = 0;
            Class3_Sub13_Sub39.anInt3463 = var1;
            aBoolean2311 = false;
            Class10.anInt154 = 1;
            GraphicDefinition.anInt546 = 2;

            Class3_Sub13_Sub36.anInt3423 = var5;
        } catch (RuntimeException var8) {
            throw ClientErrorException.clientError(var8, "v.Q(" + true + ',' + var1 + ',' + 0 + ',' + (var3 != null ? "{...}" : "null") + ',' + false + ',' + var5 + ',' + 2 + ')');
        }
    }

    public static void updateLocalPosition() {
        try {
            GraphicDefinition.incomingBuffer.setBitAccess((byte) 118);
            int opcode = GraphicDefinition.incomingBuffer.getBits(1);
            if (opcode != 0) {
                int type = GraphicDefinition.incomingBuffer.getBits(2);
                if (0 == type) {
                    Class21.maskUpdateIndexes[maskUpdateCount++] = 2047;
                } else {
                    int var4;
                    int var5;
                    if (type == 1) { //Walk
                        var4 = GraphicDefinition.incomingBuffer.getBits(3);
                        Class102.player.walkStep(1, (byte) -128, var4);
                        var5 = GraphicDefinition.incomingBuffer.getBits(1);
                        if (var5 == 1) {
                            Class21.maskUpdateIndexes[maskUpdateCount++] = 2047;
                        }

                    } else if (2 == type) {
                        if (GraphicDefinition.incomingBuffer.getBits(1) == 1) {
                            var4 = GraphicDefinition.incomingBuffer.getBits(3);
                            Class102.player.walkStep(2, (byte) -104, var4);
                            var5 = GraphicDefinition.incomingBuffer.getBits(3);
                            Class102.player.walkStep(2, (byte) -126, var5);
                        } else {
                            var4 = GraphicDefinition.incomingBuffer.getBits(3);
                            Class102.player.walkStep(0, (byte) -109, var4);
                        }

                        var4 = GraphicDefinition.incomingBuffer.getBits(1);
                        if (var4 == 1) {
                            Class21.maskUpdateIndexes[maskUpdateCount++] = 2047;
                        }

                    } else if (type == 3) {
                        var4 = GraphicDefinition.incomingBuffer.getBits(7);
                        var5 = GraphicDefinition.incomingBuffer.getBits(1);
                        WorldListCountry.localPlane = GraphicDefinition.incomingBuffer.getBits(2);
                        int var6 = GraphicDefinition.incomingBuffer.getBits(1);
                        if (var6 == 1) {
                            Class21.maskUpdateIndexes[maskUpdateCount++] = 2047;
                        }

                        int var7 = GraphicDefinition.incomingBuffer.getBits(7);
                        Class102.player.method1981(var7, var5 == 1, var4);
                    }
                }
            }
        } catch (RuntimeException var8) {
            throw ClientErrorException.clientError(var8, "bg.G(" + (byte) 81 + ')');
        }
    }

    public static boolean method2103(int var0, int var1) {
        try {
            return var1 >= -78 || (var0 == 198 || 230 == var0 || var0 == 156 || var0 == 140 || 223 == var0);
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "bg.P(" + var0 + ',' + var1 + ')');
        }
    }

    public static void method2104(RSInterface var0, boolean var1, int var2) {
        try {
            int var4 = var0.anInt240 != 0 ? var0.anInt240 : var0.width;
            int var5 = var0.anInt252 != 0 ? var0.anInt252 : var0.height;
            Class158.method2183(var0.componentHash, var1, var4, var5, GameObject.aClass11ArrayArray1834[var0.componentHash >> 16]);
            if (var0.aClass11Array262 != null) {
                Class158.method2183(var0.componentHash, var1, var4, var5, var0.aClass11Array262);
            }

            Class3_Sub31 var6 = (Class3_Sub31) Class3_Sub13_Sub17.aHashTable_3208.get((long) var0.componentHash);
            if (var6 != null) {
                Class75_Sub4.method1352(var5, var1, var6.anInt2602, var4);
            }

        } catch (RuntimeException var7) {
            throw ClientErrorException.clientError(var7, "bg.N(" + (var0 != null ? "{...}" : "null") + ',' + var1 + ',' + var2 + ')');
        }
    }

    public static void method84(RSString var0, int var1) {
        try {
            int var2 = Class100.method1602(var0);
            if (var2 != -1) {
                method565(Class119.aClass131_1624.aShortArray1727[var2], Class119.aClass131_1624.aShortArray1718[var2]);
            }
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "ab.N(" + (var0 != null ? "{...}" : "null") + ',' + var1 + ')');
        }
    }

    public static void method87(int var0, int var1) {
        try {
            if (var0 >= -20) {
                method83((byte) 44);
            }

            if (0 != var1) {
                if (var1 == 1) {
                    Translation.englishToGerman();
                } else {
                    if (2 != var1) {
                        throw new RuntimeException();
                    }

                    Translation.englishToFrench();
                }

            }
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "ab.K(" + var0 + ',' + var1 + ')');
        }
    }

    public static Class106[] method88() {
        try {

            if (Class56.aClass106Array890 == null) {
                Class106[] var1 = method596(Class38.aClass87_665);
                Class106[] var2 = new Class106[var1.length];
                int var3 = 0;

                label58:
                for (int var4 = 0; var4 < var1.length; ++var4) {
                    Class106 var5 = var1[var4];
                    if ((0 >= var5.anInt1450 || var5.anInt1450 >= 24) && var5.anInt1447 >= 800 && 600 <= var5.anInt1449) {
                        for (int var6 = 0; var3 > var6; ++var6) {
                            Class106 var7 = var2[var6];
                            if (var5.anInt1447 == var7.anInt1447 && var5.anInt1449 == var7.anInt1449) {
                                if (var7.anInt1450 < var5.anInt1450) {
                                    var2[var6] = var5;
                                }
                                continue label58;
                            }
                        }

                        var2[var3] = var5;
                        ++var3;
                    }
                }

                Class56.aClass106Array890 = new Class106[var3];
                ArrayUtils.arraycopy(var2, 0, Class56.aClass106Array890, 0, var3);
                int[] var9 = new int[Class56.aClass106Array890.length];

                for (int var10 = 0; Class56.aClass106Array890.length > var10; ++var10) {
                    Class106 var11 = Class56.aClass106Array890[var10];
                    var9[var10] = var11.anInt1449 * var11.anInt1447;
                }

                Class108.method1658(var9, Class56.aClass106Array890);
            }

            return Class56.aClass106Array890;
        } catch (RuntimeException var8) {
            throw ClientErrorException.clientError(var8, "ab.M(" + (byte) 28 + ')');
        }
    }

    public static void method89(CacheIndex var1, CacheIndex var2, CacheIndex var3, CacheIndex var4) {
        try {
            Class12.aClass153_323 = var2;
            Class97.aClass153_1378 = var1;
            aClass153_3361 = var3;
            Class119.aClass153_1628 = var4;

            GameObject.aClass11ArrayArray1834 = new RSInterface[aClass153_3361.method2121()][];
            aBooleanArray1703 = new boolean[aClass153_3361.method2121()];
        } catch (RuntimeException var6) {
            throw ClientErrorException.clientError(var6, "ab.J(" + true + ',' + (var1 != null ? "{...}" : "null") + ',' + (var2 != null ? "{...}" : "null") + ',' + (var3 != null ? "{...}" : "null") + ',' + (var4 != null ? "{...}" : "null") + ')');
        }
    }

    public static void method1772(int plane, int animId, int dummy, NPC var3) {
        try {
            if (var3.anInt2771 == animId && -1 != animId) {
                AnimationDefinition var4 = Client.getAnimationDefinition(animId);
                int var5 = var4.anInt1845;
                if (var5 == 1) {
                    var3.anInt2776 = 1;
                    var3.anInt2832 = 0;
                    var3.anInt2760 = 0;
                    var3.anInt2773 = 0;
                    var3.anInt2828 = plane;
                    method1470(var3.anInt2829, var4, 183921384, var3.anInt2819, false, var3.anInt2832);
                }

                if (var5 == 2) {
                    var3.anInt2773 = 0;
                }
            } else if (animId == -1 || -1 == var3.anInt2771 || Client.getAnimationDefinition(animId).anInt1857 >= Client.getAnimationDefinition(var3.anInt2771).anInt1857) {
                var3.anInt2760 = 0;
                var3.anInt2771 = animId;
                var3.anInt2776 = 1;
                var3.anInt2773 = 0;
                var3.anInt2828 = plane;
                var3.anInt2811 = var3.anInt2816;
                var3.anInt2832 = 0;
                if (var3.anInt2771 != -1) {
                    method1470(var3.anInt2829, Client.getAnimationDefinition(var3.anInt2771), dummy + 183921345, var3.anInt2819, false, var3.anInt2832);
                }
            }

            if (dummy != 39) {
                anInt1711 = 41;
            }

        } catch (RuntimeException var6) {
            throw ClientErrorException.clientError(var6, "sc.J(" + plane + ',' + animId + ',' + dummy + ',' + (var3 != null ? "{...}" : "null") + ')');
        }
    }

    public static void method1775() {
        for (int var0 = 0; var0 < anInt3070; ++var0) {
            Class25 var1 = AnimationDefinition.aClass25Array1868[var0];
            Class158.method2186(var1);
            AnimationDefinition.aClass25Array1868[var0] = null;
        }

        anInt3070 = 0;
    }

    public static Class3_Sub13 method1777(int var0) {
        try {
            if (var0 == 0) {
                return new Class3_Sub13_Sub22();
            } else if (var0 == 1) {
                return new Class3_Sub13_Sub11();
            } else if (2 == var0) {
                return new Class3_Sub13_Sub31();
            } else if (var0 == 3) {
                return new Class3_Sub13_Sub29();
            } else if (var0 == 4) {
                return new Class3_Sub13_Sub19();
            } else if (var0 == 5) {
                return new Class3_Sub13_Sub24();
            } else if (var0 == 6) {
                return new Class3_Sub13_Sub2();
            } else if (var0 == 7) {
                return new Class3_Sub13_Sub27();
            } else if (var0 == 8) {
                return new Class3_Sub13_Sub39();
            } else if (9 == var0) {
                return new Class3_Sub13_Sub8();
            } else if (10 == var0) {
                return new Class3_Sub13_Sub37();
            } else if (var0 == 11) {
                return new Class3_Sub13_Sub20();
            } else if (var0 == 12) {
                return new Class3_Sub13_Sub1();
            } else if (var0 == 13) {
                return new Class3_Sub13_Sub30();
            } else if (14 == var0) {
                return new Class3_Sub13_Sub32();
            } else if (var0 == 15) {
                return new Class3_Sub13_Sub16();
            } else if (var0 == 16) {
                return new Class3_Sub13_Sub9();
            } else if (17 == var0) {
                return new Class3_Sub13_Sub15();
            } else if (var0 == 18) {
                return new Class3_Sub13_Sub23_Sub1();
            } else if (var0 == 19) {
                return new Class3_Sub13_Sub18();
            } else if (var0 == 20) {
                return new Class3_Sub13_Sub13();
            } else if (21 == var0) {
                return new Class3_Sub13_Sub5();
            } else if (22 == var0) {
                return new Class3_Sub13_Sub35();
            } else if (var0 == 23) {
                return new Class3_Sub13_Sub17();
            } else if (24 == var0) {
                return new Class3_Sub13_Sub12();
            } else if (var0 == 25) {
                return new Class3_Sub13_Sub34();
            } else if (var0 == 26) {
                return new Class3_Sub13_Sub6();
            } else if (27 == var0) {
                return new Class3_Sub13_Sub7();
            } else if (var0 == 28) {
                return new Class3_Sub13_Sub25();
            } else if (var0 == 29) {
                return new Class3_Sub13_Sub33();
            } else if (var0 == 30) {
                return new Class3_Sub13_Sub10();
            } else if (31 == var0) {
                return new Class3_Sub13_Sub14();
            } else if (32 == var0) {
                return new Class3_Sub13_Sub28();
            } else if (33 == var0) {
                return new Class3_Sub13_Sub3();
            } else if (var0 == 34) {
                return new Class3_Sub13_Sub4();
            } else if (var0 == 35) {
                return new Class3_Sub13_Sub26();
            } else if (var0 == 36) {
                return new Class3_Sub13_Sub36();
            } else if (var0 == 37) {
                return new Class3_Sub13_Sub21();
            } else if (38 == var0) {
                return new Class3_Sub13_Sub38();
            } else if (39 == var0) {
                return new Class3_Sub13_Sub23();
            } else {

                return null;
            }
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "sc.I(" + var0 + ')');
        }
    }

    public static void method1783(Component var1) {
        try {
            var1.removeMouseListener(aClass149_4047);
            var1.removeMouseMotionListener(aClass149_4047);
            var1.removeFocusListener(aClass149_4047);
            GraphicDefinition.anInt549 = 0;
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "sc.M(" + (var1 != null ? "{...}" : "null") + ')');
        }
    }

    public static long method1395(int var0, int var1, int var2) {
        Class3_Sub2 var3 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2];
        return var3 != null && var3.aClass19_2233 != null ? var3.aClass19_2233.aLong428 : 0L;
    }

    public static void method1396(int var0) {
        try {
            int var2 = Class106.anInt1442;
            int var1 = Class84.anInt1164;
            int var4 = -Class140_Sub7.anInt2934 + (Class70.anInt1047 - var2);
            int var3 = -var1 + anInt2334 - Class23.anInt454;
            if (~var1 < var0 || var3 > 0 || var2 > 0 || var4 > 0) {
                try {
                    Object var5;
                    if (null != Class3_Sub13_Sub10.aFrame3121) {
                        var5 = Class3_Sub13_Sub10.aFrame3121;
                    } else if (GameShell.frame == null) {
                        var5 = Class38.aClass87_665.applet;
                    } else {
                        var5 = GameShell.frame;
                    }

                    int var7 = 0;
                    int var6 = 0;
                    if (GameShell.frame == var5) {
                        Insets var8 = GameShell.frame.getInsets();
                        var6 = var8.left;
                        var7 = var8.top;
                    }

                    Graphics var11 = ((Container) var5).getGraphics();
                    var11.setColor(Color.black);
                    if (var1 > 0) {
                        var11.fillRect(var6, var7, var1, Class70.anInt1047);
                    }

                    if (0 < var2) {
                        var11.fillRect(var6, var7, anInt2334, var2);
                    }

                    if (var3 > 0) {
                        var11.fillRect(-var3 + var6 + anInt2334, var7, var3, Class70.anInt1047);
                    }

                    if (var4 > 0) {
                        var11.fillRect(var6, -var4 + var7 + Class70.anInt1047, anInt2334, var4);
                    }
                } catch (Exception var9) {
                }
            }

        } catch (RuntimeException var10) {
            throw ClientErrorException.clientError(var10, "l.A(" + var0 + ')');
        }
    }

    public static void method589(int var0, int var1, int var2) {
        Class3_Sub13_Sub21.aBoolean3261 = true;
        Class91.anInt1302 = var0;
        Class49.anInt819 = var1;
        Class3_Sub13_Sub23_Sub1.anInt4039 = var2;
        Class27.anInt515 = -1;
        anInt999 = -1;
    }

    public static boolean method590(byte var0, int var1, int var2) {
        try {
            if (11 == var2) {
                var2 = 10;
            }

            if (var2 >= 5 && var2 <= 8) {
                var2 = 4;
            }

            ObjectDefinition var4 = ObjectDefinition.getObjectDefinition(var1);
            return var4.method1684(var2);
        } catch (RuntimeException var5) {
            throw ClientErrorException.clientError(var5, "il.D(" + var0 + ',' + var1 + ',' + var2 + ')');
        }
    }

    public static boolean method591(int var0) {
        try {
            KeyboardListener var1 = Class3_Sub13_Sub3.aClass148_3049;
            synchronized (var1) {
                if (Class3_Sub28_Sub9.anInt3620 == Class134.anInt1762) {
                    return false;
                } else {
                    Class3_Sub28_Sub9.anInt3624 = Class129.anIntArray1693[Class3_Sub28_Sub9.anInt3620];
                    Class3_Sub13_Sub27.anInt3342 = KeyboardListener.anIntArray1978[Class3_Sub28_Sub9.anInt3620];
                    Class3_Sub28_Sub9.anInt3620 = 1 + Class3_Sub28_Sub9.anInt3620 & 127;
                    if (var0 < 58) {
                        method591(68);
                    }

                    return true;
                }
            }
        } catch (RuntimeException var4) {
            throw ClientErrorException.clientError(var4, "c.F(" + var0 + ')');
        }
    }

    public static void method592(byte var0) {
        try {
            aClass25Array4060 = null;
            TextCore.aClass94_4057 = null;
        } catch (RuntimeException var2) {
            throw ClientErrorException.clientError(var2, "c.P(" + var0 + ')');
        }
    }

    public static void method593(Frame var0, Signlink var2) {
        try {

            while (true) {
                Class64 var3 = var2.method1436(var0, 86);

                while (var3.anInt978 == 0) {
                    TimeUtils.sleep(10L);
                }

                if (1 == var3.anInt978) {
                    var0.setVisible(false);
                    var0.dispose();
                    return;
                }

                TimeUtils.sleep(100L);
            }
        } catch (RuntimeException var4) {
            throw ClientErrorException.clientError(var4, "c.R(" + (var0 != null ? "{...}" : "null") + ',' + true + ',' + (var2 != null ? "{...}" : "null") + ')');
        }
    }

    public static void method594(int var0, int var1) {
        try {
            if (var0 < 83) {
                method592((byte) 122);
            }

            Class128.aReferenceCache_1683.sweep(var1);
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "c.Q(" + var0 + ',' + var1 + ')');
        }
    }

    public static void method595() {
        try {
            aReferenceCache_684.sweep(5);
            Class163_Sub1.aReferenceCache_2984.sweep(5);
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "c.O(" + 5 + ',' + 109 + ')');
        }
    }

    public static Class106[] method596(Signlink var1) {
        try {
            if (var1.method1432(false)) {
                Class64 var2 = var1.method1453((byte) 8);

                while (0 == var2.anInt978) {
                    TimeUtils.sleep(10L);
                }

                if (2 == var2.anInt978) {
                    return new Class106[0];
                } else {
                    int[] var3 = (int[]) ((int[]) var2.anObject974);
                    Class106[] var4 = new Class106[var3.length >> 2];

                    for (int var5 = 0; var5 < var4.length; ++var5) {
                        Class106 var6 = new Class106();
                        var4[var5] = var6;
                        var6.anInt1447 = var3[var5 << 2];
                        var6.anInt1449 = var3[(var5 << 2) + 1];
                        var6.anInt1450 = var3[(var5 << 2) - -2];
                    }

                    return var4;
                }
            } else {
                return new Class106[0];
            }
        } catch (RuntimeException var7) {
            throw ClientErrorException.clientError(var7, "pm.P(" + 10 + ',' + (var1 != null ? "{...}" : "null") + ')');
        }
    }

    public static void method598(boolean var0, int var1, boolean var2, int var3, boolean var4, int var5, int var6) {
        try {
            if (var2) {
                HDToolKit.method1842();
            }

            if (null != Class3_Sub13_Sub10.aFrame3121 && (3 != var1 || Class3_Sub13.anInt2378 != var5 || anInt3071 != var6)) {
                method593(Class3_Sub13_Sub10.aFrame3121, Class38.aClass87_665);
                Class3_Sub13_Sub10.aFrame3121 = null;
            }

            if (3 == var1 && null == Class3_Sub13_Sub10.aFrame3121) {
                Class3_Sub13_Sub10.aFrame3121 = Class99.method1597(0, var6, var5, Class38.aClass87_665);
                if (null != Class3_Sub13_Sub10.aFrame3121) {
                    anInt3071 = var6;
                    Class3_Sub13.anInt2378 = var5;
                    Class119.method1730(Class38.aClass87_665);
                }
            }

            if (var1 == 3 && Class3_Sub13_Sub10.aFrame3121 == null) {
                method598(true, anInt2577, true, var3, var4, -1, -1);
            } else {
                Object var7;
                if (null == Class3_Sub13_Sub10.aFrame3121) {
                    if (null == GameShell.frame) {
                        var7 = Class38.aClass87_665.applet;
                    } else {
                        var7 = GameShell.frame;
                    }
                } else {
                    var7 = Class3_Sub13_Sub10.aFrame3121;
                }

                anInt2334 = ((Container) var7).getSize().width;
                Class70.anInt1047 = ((Container) var7).getSize().height;
                Insets var8;
                if (GameShell.frame == var7) {
                    var8 = GameShell.frame.getInsets();
                    anInt2334 -= var8.right + var8.left;
                    Class70.anInt1047 -= var8.bottom + var8.top;
                }

                if (var1 >= 2) {
                    Class23.anInt454 = anInt2334;
                    Class140_Sub7.anInt2934 = Class70.anInt1047;
                    Class84.anInt1164 = 0;
                    Class106.anInt1442 = 0;
                } else {
                    Class106.anInt1442 = 0;
                    Class84.anInt1164 = (anInt2334 + -765) / 2;
                    Class23.anInt454 = 765;
                    Class140_Sub7.anInt2934 = 503;
                }

                if (var0) {
                    Class163_Sub1_Sub1.method2215(GameShell.canvas);
                    method1783(GameShell.canvas);
                    if (null != Class38.aClass146_668) {
                        Class38.aClass146_668.method2082(GameShell.canvas);
                    }

                    Class126.aClient1671.method30((byte) 97);
                    Class3_Sub13_Sub4.method193((byte) 97, GameShell.canvas);
                    ItemDefinition.method1119(GameShell.canvas, var4);
                    if (Class38.aClass146_668 != null) {
                        Class38.aClass146_668.method2084(GameShell.canvas, -103);
                    }
                } else {
                    if (HDToolKit.highDetail) {
                        HDToolKit.method1854(Class23.anInt454, Class140_Sub7.anInt2934);
                    }

                    GameShell.canvas.setSize(Class23.anInt454, Class140_Sub7.anInt2934);
                    if (GameShell.frame == var7) {
                        var8 = GameShell.frame.getInsets();
                        GameShell.canvas.setLocation(var8.left - -Class84.anInt1164, var8.top + Class106.anInt1442);
                    } else {
                        GameShell.canvas.setLocation(Class84.anInt1164, Class106.anInt1442);
                    }
                }

                if (0 == var1 && var3 > 0) {
                    HDToolKit.method1834(GameShell.canvas);
                }

                if (var2 && var1 > 0) {
                    GameShell.canvas.setIgnoreRepaint(true);
                    if (!GameShell.aBoolean11) {
                        Class32.method995();
                        Class164_Sub1.aClass158_3009 = null;
                        Class164_Sub1.aClass158_3009 = Class3_Sub13_Sub23_Sub1.method285(Class140_Sub7.anInt2934, Class23.anInt454, GameShell.canvas);
                        Class74.method1320();
                        if (5 == Class143.loadingStage) {
                            Class3_Sub23.method406((byte) 122, true, Class168.aClass3_Sub28_Sub17_2096);
                        } else {
                            Class3_Sub13.method164((byte) -20, false, TextCore.LoadingPleaseWait2);
                        }

                        try {
                            Graphics var11 = GameShell.canvas.getGraphics();
                            Class164_Sub1.aClass158_3009.method2179(var11);
                        } catch (Exception var9) {
                        }

                        method1396(-1);
                        if (var3 == 0) {
                            Class164_Sub1.aClass158_3009 = Class3_Sub13_Sub23_Sub1.method285(503, 765, GameShell.canvas);
                        } else {
                            Class164_Sub1.aClass158_3009 = null;
                        }

                        Class64 var13 = Class38.aClass87_665.method1444(-43, Class126.aClient1671.getClass());

                        while (var13.anInt978 == 0) {
                            TimeUtils.sleep(100L);
                        }

                        if (1 == var13.anInt978) {
                            GameShell.aBoolean11 = true;
                        }
                    }

                    if (GameShell.aBoolean11) {
                        HDToolKit.method1853(GameShell.canvas, 2 * anInt3671);
                    }
                }

                if (!HDToolKit.highDetail && 0 < var1) {
                    method598(true, 0, true, var3, false, -1, -1);
                } else {
                    if (var1 > 0 && var3 == 0) {
                        Class17.aThread409.setPriority(5);
                        Class164_Sub1.aClass158_3009 = null;
                        Class140_Sub1_Sub2.method1935();
                        ((Class102) Class51.anInterface2_838).method1619(200);
                        if (Class106.aBoolean1441) {
                            Class51.method1137(0.7F);
                        }

                        Class3_Sub13.method165();
                    } else if (0 == var1 && var3 > 0) {
                        Class17.aThread409.setPriority(1);
                        Class164_Sub1.aClass158_3009 = Class3_Sub13_Sub23_Sub1.method285(503, 765, GameShell.canvas);
                        Class140_Sub1_Sub2.method1938();
                        ((Class102) Class51.anInterface2_838).method1619(20);
                        if (Class106.aBoolean1441) {
                            if (1 == anInt3625) {
                                Class51.method1137(0.9F);
                            }

                            if (anInt3625 == 2) {
                                Class51.method1137(0.8F);
                            }

                            if (3 == anInt3625) {
                                Class51.method1137(0.7F);
                            }

                            if (anInt3625 == 4) {
                                Class51.method1137(0.6F);
                            }
                        }

                        Class3_Sub11.method144();
                        Class3_Sub13.method165();
                    }

                    aBoolean742 = !NPC.method1986(89);
                    if (var2) {
                        Class3_Sub20.method389();
                    }

                    Class3_Sub15.aBoolean2427 = var1 >= 2;

                    if (-1 != Class3_Sub28_Sub12.anInt3655) {
                        Class124.method1746(true, (byte) -107);
                    }

                    if (null != Class3_Sub15.activeConnection && (Class143.loadingStage == 30 || Class143.loadingStage == 25)) {
                        Class3_Sub13_Sub8.method204(-3);
                    }

                    for (int var12 = 0; var12 < 100; ++var12) {
                        aBooleanArray3674[var12] = true;
                    }

                    Class3_Sub13_Sub10.aBoolean3116 = true;
                }
            }
        } catch (RuntimeException var10) {
            throw ClientErrorException.clientError(var10, "pm.F(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ')');
        }
    }

    static void method1047(int var0, int var1, int var2, boolean var3, int var4, boolean var5) {
        try {
            if (var2 > var4) {
                int var7 = (var2 + var4) / 2;
                int var8 = var4;
                WorldListEntry var9 = aClass44_Sub1Array3201[var7];
                aClass44_Sub1Array3201[var7] = aClass44_Sub1Array3201[var2];
                aClass44_Sub1Array3201[var2] = var9;

                for (int var10 = var4; var10 < var2; ++var10) {
                    if (method1535(var9, aClass44_Sub1Array3201[var10], 5730, var0, var1, var3, var5) <= 0) {
                        WorldListEntry var11 = aClass44_Sub1Array3201[var10];
                        aClass44_Sub1Array3201[var10] = aClass44_Sub1Array3201[var8];
                        aClass44_Sub1Array3201[var8++] = var11;
                    }
                }

                aClass44_Sub1Array3201[var2] = aClass44_Sub1Array3201[var8];
                aClass44_Sub1Array3201[var8] = var9;
                method1047(var0, var1, -1 + var8, var3, var4, var5);
                method1047(var0, var1, var2, var3, var8 - -1, var5);
            }

        } catch (RuntimeException var12) {
            throw ClientErrorException.clientError(var12, "ge.A(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + false + ')');
        }
    }

    static void method1048(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
        try {
            if (var5 > -15) {
                anInt688 = -64;
            }

            if (var1 >= 1 && var4 >= 1 && 102 >= var1 && var4 <= 102) {
                int var8;
                if (!NPC.method1986(41) && 0 == (2 & aByteArrayArrayArray113[0][var1][var4])) {
                    var8 = var2;
                    if ((8 & aByteArrayArrayArray113[var2][var1][var4]) != 0) {
                        var8 = 0;
                    }

                    if (var8 != Class140_Sub3.anInt2745) {
                        return;
                    }
                }

                var8 = var2;
                if (var2 < 3 && (2 & aByteArrayArrayArray113[1][var1][var4]) == 2) {
                    var8 = var2 + 1;
                }

                Class20.method910(var4, var1, var2, var7, var8, AtmosphereParser.aClass91Array1182[var2]);
                if (0 <= var0) {
                    boolean var9 = KeyboardListener.aBoolean1905;
                    KeyboardListener.aBoolean1905 = true;
                    Class110.method1683(var8, false, var2, false, AtmosphereParser.aClass91Array1182[var2], var0, var6, var1, var4, var3);
                    KeyboardListener.aBoolean1905 = var9;
                }
            }

        } catch (RuntimeException var10) {
            throw ClientErrorException.clientError(var10, "ge.H(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
        }
    }

    static RSString method1052(long var1) {
        try {
            if (var1 > 0 && var1 < 6582952005840035281L) {
                if (var1 % 37L == 0) {
                    return null;
                } else {
                    int var3 = 0;

                    for (long var4 = var1; var4 != 0L; ++var3) {
                        var4 /= 37L;
                    }

                    byte[] var6 = new byte[var3];

                    while (0L != var1) {
                        long var7 = var1;
                        var1 /= 37L;
                        --var3;
                        var6[var3] = Class163_Sub1_Sub1.aByteArray4005[(int) (-(var1 * 37L) + var7)];
                    }

                    RSString var10 = new RSString();
                    var10.buffer = var6;
                    var10.length = var6.length;
                    return var10;
                }
            } else {
                return null;
            }
        } catch (RuntimeException var9) {
            throw ClientErrorException.clientError(var9, "ge.B(" + -29664 + ',' + var1 + ')');
        }
    }

    static void method1053(CacheIndex var1) {
        try {
            Class97.aClass153_1372 = var1;
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "ge.F(" + (byte) -117 + ',' + (var1 != null ? "{...}" : "null") + ')');
        }
    }

    public static Class3_Sub28_Sub17 method880(byte[] var1) {
        try {
            if (null == var1) {
                return null;
            } else {
                Object var2;
                if (HDToolKit.highDetail) {
                    var2 = new Class3_Sub28_Sub17_Sub2(var1, Class164.anIntArray2048, anIntArray2591, Class140_Sub7.anIntArray2931, anIntArray3076, Class163_Sub1.aByteArrayArray2987);
                } else {
                    var2 = new Class3_Sub28_Sub17_Sub1(var1, Class164.anIntArray2048, anIntArray2591, Class140_Sub7.anIntArray2931, anIntArray3076, Class163_Sub1.aByteArrayArray2987);
                }

                Class39.method1035((byte) 106);
                return (Class3_Sub28_Sub17) var2;
            }
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "ce.G(" + -22376 + ',' + "{...}" + ')');
        }
    }

    public static void method881(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
        try {
            if (var2 <= -65) {
                Class3_Sub4 var10 = null;

                for (Class3_Sub4 var11 = (Class3_Sub4) Class3_Sub13_Sub6.aClass61_3075.method1222(); var11 != null; var11 = (Class3_Sub4) Class3_Sub13_Sub6.aClass61_3075.method1221()) {
                    if (var11.anInt2250 == var0 && var11.anInt2264 == var4 && var11.anInt2248 == var1 && var11.anInt2263 == var7) {
                        var10 = var11;
                        break;
                    }
                }

                if (null == var10) {
                    var10 = new Class3_Sub4();
                    var10.anInt2264 = var4;
                    var10.anInt2248 = var1;
                    var10.anInt2250 = var0;
                    var10.anInt2263 = var7;
                    Class132.method1798(72, var10);
                    Class3_Sub13_Sub6.aClass61_3075.method1215(var10);
                }

                var10.anInt2262 = var8;
                var10.anInt2261 = var9;
                var10.anInt2259 = var5;
                var10.anInt2265 = var6;
                var10.anInt2256 = var3;
            }
        } catch (RuntimeException var12) {
            throw ClientErrorException.clientError(var12, "ce.J(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + var9 + ')');
        }
    }

    public static void method882() {
        try {
            Class3_Sub13_Sub36.anInt3423 = 0;
            Class132.anInt1741 = -1;
            Class10.anInt154 = 1;
            GraphicDefinition.anInt546 = 2;
            aBoolean2311 = false;
            Class101.aClass153_1423 = null;
            Class3_Sub13_Sub39.anInt3463 = -1;
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "ce.F(" + -1 + ',' + 2 + ')');
        }
    }

    public static Class3_Sub28_Sub3 method884(int var0, byte var1, int var2) {
        try {
            Class3_Sub28_Sub3 var3 = (Class3_Sub28_Sub3) Class134.aClass61_1758.method1222();

            for (; var3 != null; var3 = (Class3_Sub28_Sub3) Class134.aClass61_1758.method1221()) {
                if (var3.aBoolean3553 && var3.method537(var2, var0)) {
                    return var3;
                }
            }

            return null;
        } catch (RuntimeException var5) {
            throw ClientErrorException.clientError(var5, "ce.D(" + var0 + ',' + var1 + ',' + var2 + ')');
        }
    }

    static void method518(Player var0) {
        try {
            Class3_Sub9 var2 = (Class3_Sub9) aHashTable_4046.get(var0.displayName.toLong());

            if (var2 == null) {
                Class70.method1286(var0.anIntArray2755[0], (ObjectDefinition) null, 0, (NPC) null, var0.anIntArray2767[0], WorldListCountry.localPlane, var0);
            } else {
                var2.method134();
            }

        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "rg.UA(" + (var0 != null ? "{...}" : "null") + ',' + -110 + ')');
        }
    }

    static int method519(int var0, int var2, int var3) {
        try {
            var0 &= 3;

            return 0 != var0 ? (var0 != 1 ? (var0 == 2 ? -var3 + 7 : -var2 + 7) : var2) : var3;
        } catch (RuntimeException var5) {
            throw ClientErrorException.clientError(var5, "rg.RA(" + var0 + ',' + true + ',' + var2 + ',' + var3 + ')');
        }
    }

    static Class3_Sub28_Sub3 method520(byte var0) {
        try {
            return aClass3_Sub28_Sub3_2600;
        } catch (RuntimeException var2) {
            throw ClientErrorException.clientError(var2, "rg.OA(" + var0 + ')');
        }
    }

    static NPCDefinition method522(int var0) {
        try {
            NPCDefinition def = (NPCDefinition) aReferenceCache_4043.get((long) var0);
            if (null == def) {
                byte[] var3 = Class29.aClass153_557.getFile(Class38_Sub1.method1031(var0), method54(var0));
                def = new NPCDefinition();

                def.npcId = var0;
                if (null != var3) {
                    def.method1478(new DataBuffer(var3));
                }
                aReferenceCache_4043.put(def, (long) var0);
            }
            return def;
        } catch (RuntimeException var4) {
            throw ClientErrorException.clientError(var4, "rg.PA(" + var0 + ',' + 27112 + ')');
        }
    }

    static void method523(int var0, int var1, int var3, int var4, int var5, int var6, int var7, int var8) {
        try {
            int var9 = var3 - var8;
            int var11 = (-var5 + var0 << 16) / var9;
            int var10 = -var4 + var6;
            int var12 = (var7 + -var1 << 16) / var10;
            Class83.method1410(var1, 0, var6, var4, var3, var5, var8, var12, var11, 0, -12541);
        } catch (RuntimeException var13) {
            throw ClientErrorException.clientError(var13, "rg.SA(" + var0 + ',' + var1 + ',' + 0 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ')');
        }
    }

    public static void method744() {
        try {

            ++KeyboardListener.anInt1908;
        } catch (RuntimeException var2) {
            throw ClientErrorException.clientError(var2, "wa.HA(" + true + ')');
        }
    }

    public static void method746(byte var0) {
        try {
            Class67.aReferenceCache_1013.clear();
        } catch (RuntimeException var2) {
            throw ClientErrorException.clientError(var2, "wa.VC(" + var0 + ')');
        }
    }

    public static void method777(Class91[] var0, boolean var1, int var2, int var3, int var5, int var6, byte[] var7) {
        try {
            int var10;
            int var11;
            if (!var1) {
                for (int var9 = 0; 4 > var9; ++var9) {
                    for (var10 = 0; var10 < 64; ++var10) {
                        for (var11 = 0; var11 < 64; ++var11) {
                            if (var5 - -var10 > 0 && var10 + var5 < 103 && var3 + var11 > 0 && var11 + var3 < 103) {
                                var0[var9].anIntArrayArray1304[var10 + var5][var3 - -var11] = bitwiseAnd(var0[var9].anIntArrayArray1304[var10 + var5][var3 - -var11], -16777217);
                            }
                        }
                    }
                }
            }

            DataBuffer var20 = new DataBuffer(var7);
            byte var8;
            if (var1) {
                var8 = 1;
            } else {
                var8 = 4;
            }

            int var12;
            for (var10 = 0; var8 > var10; ++var10) {
                for (var11 = 0; var11 < 64; ++var11) {
                    for (var12 = 0; 64 > var12; ++var12) {
                        Class167.method2267(var2, var6, var1, var20, var12 - -var3, var5 + var11, (byte) 91, 0, var10);
                    }
                }
            }

            int var14;
            int var15;
            int var17;
            boolean var21;
            int var24;
            for (var21 = false; var20.index < var20.buffer.length; var21 = true) {
                var11 = var20.readUnsignedByte();
                if (var11 != 129) {
                    --var20.index;
                    break;
                }

                for (var12 = 0; var12 < 4; ++var12) {
                    byte var13 = var20.readSignedByte();
                    if (0 == var13) {
                        var14 = var5;
                        if (var5 >= 0) {
                            if (var5 >= 104) {
                                var14 = 104;
                            }
                        } else {
                            var14 = 0;
                        }

                        var24 = var3;
                        if (var3 < 0) {
                            var24 = 0;
                        } else if (var3 >= 104) {
                            var24 = 104;
                        }

                        var15 = 64 + var5;
                        var17 = var3 + 64;
                        if (var17 >= 0) {
                            if (var17 >= 104) {
                                var17 = 104;
                            }
                        } else {
                            var17 = 0;
                        }

                        if (var15 < 0) {
                            var15 = 0;
                        } else if (var15 >= 104) {
                            var15 = 104;
                        }

                        while (var15 > var14) {
                            while (var24 < var17) {
                                aByteArrayArrayArray1774[var12][var14][var24] = 0;
                                ++var24;
                            }

                            ++var14;
                        }
                    } else if (1 == var13) {
                        for (var14 = 0; var14 < 64; var14 += 4) {
                            for (var15 = 0; var15 < 64; var15 += 4) {
                                byte var16 = var20.readSignedByte();

                                for (var17 = var14 + var5; 4 + var5 + var14 > var17; ++var17) {
                                    for (int var18 = var3 + var15; 4 + var3 + var15 > var18; ++var18) {
                                        if (var17 >= 0 && var17 < 104 && 0 <= var18 && var18 < 104) {
                                            aByteArrayArrayArray1774[var12][var17][var18] = var16;
                                        }
                                    }
                                }
                            }
                        }
                    } else if (var13 == 2 && var12 > 0) {
                        var15 = var5 + 64;
                        var24 = var3;
                        var17 = var3 + 64;
                        if (var15 < 0) {
                            var15 = 0;
                        } else if (104 <= var15) {
                            var15 = 104;
                        }

                        if (var3 >= 0) {
                            if (var3 >= 104) {
                                var24 = 104;
                            }
                        } else {
                            var24 = 0;
                        }

                        if (var17 >= 0) {
                            if (var17 >= 104) {
                                var17 = 104;
                            }
                        } else {
                            var17 = 0;
                        }

                        var14 = var5;
                        if (var5 >= 0) {
                            if (104 <= var5) {
                                var14 = 104;
                            }
                        } else {
                            var14 = 0;
                        }

                        while (var15 > var14) {
                            while (var17 > var24) {
                                aByteArrayArrayArray1774[var12][var14][var24] = aByteArrayArrayArray1774[var12 + -1][var14][var24];
                                ++var24;
                            }

                            ++var14;
                        }
                    }
                }
            }

            int var23;
            if (HDToolKit.highDetail && !var1) {
                AtmosphereParser var22 = null;

                while (var20.buffer.length > var20.index) {
                    var12 = var20.readUnsignedByte();
                    if (var12 == 0) {
                        var22 = new AtmosphereParser(var20);
                    } else {
                        if (var12 != 1) {
//								throw
                            new IllegalStateException().printStackTrace();
                            return;//
                        }

                        var23 = var20.readUnsignedByte();
                        if (0 < var23) {
                            for (var14 = 0; var23 > var14; ++var14) {
                                Class43 var25 = new Class43(var20);
                                if (var25.anInt705 == 31) {
                                    Class57 var26 = Class81.method1401(var20.readUnsignedShort());
                                    var25.method1060((byte) -67, var26.anInt896, var26.anInt908, var26.anInt899, var26.anInt907);
                                }

                                var25.anInt708 += var3 << 7;
                                var25.anInt703 += var5 << 7;
                                var17 = var25.anInt708 >> 7;
                                var24 = var25.anInt703 >> 7;
                                if (var24 >= 0 && 0 <= var17 && var24 < 104 && var17 < 104) {
                                    var25.aBoolean696 = 0 != (aByteArrayArrayArray113[1][var24][var17] & 2);
                                    var25.anInt697 = Class44.anIntArrayArrayArray723[var25.anInt704][var24][var17] + -var25.anInt697;
                                    Class68.method1264(var25);
                                }
                            }
                        }
                    }
                }

                if (var22 == null) {
                    var22 = new AtmosphereParser();
                }

                for (var12 = 0; var12 < 8; ++var12) {
                    for (var23 = 0; var23 < 8; ++var23) {
                        var14 = var12 + (var5 >> 3);
                        var15 = (var3 >> 3) + var23;
                        if (0 <= var14 && var14 < 13 && var15 >= 0 && var15 < 13) {
                            AtmosphereParser.aAtmosphereParserArrayArray1581[var14][var15] = var22;
                        }
                    }
                }
            }

            if (!var21) {
                for (var11 = 0; var11 < 4; ++var11) {
                    for (var12 = 0; 16 > var12; ++var12) {
                        for (var23 = 0; var23 < 16; ++var23) {
                            var14 = (var5 >> 2) - -var12;
                            var15 = var23 + (var3 >> 2);
                            if (0 <= var14 && 26 > var14 && 0 <= var15 && var15 < 26) {
                                aByteArrayArrayArray1774[var11][var14][var15] = 0;
                            }
                        }
                    }
                }
            }

        } catch (RuntimeException var19) {
            throw ClientErrorException.clientError(var19, "wa.OA(" + (var0 != null ? "{...}" : "null") + ',' + var1 + ',' + var2 + ',' + var3 + ',' + 4 + ',' + var5 + ',' + var6 + ',' + (var7 != null ? "{...}" : "null") + ')');
        }
    }

    public static Class12 method784(int var0, int var1, int var2) {
        Class3_Sub2 var3 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var0][var1][var2];
        return var3 != null && var3.aClass12_2230 != null ? var3.aClass12_2230 : null;
    }

    public static void method792() {
        try {
            int var1 = Class137.method1817();
            if (0 == var1) {
                Class158.aByteArrayArrayArray2008 = (byte[][][]) null;
                Class136.method1816(0, -7);
            } else if (var1 == 1) {
                Class3_Sub5.method112((byte) 0);
                Class136.method1816(512, -7);
                Class3_Sub13_Sub18.method257();
            } else {
                Class3_Sub5.method112((byte) (-4 + Class79.anInt1127 & 255));
                Class136.method1816(2, -7);
            }

        } catch (RuntimeException var2) {
            throw ClientErrorException.clientError(var2, "wa.AA(" + 9179409 + ')');
        }
    }

    public static void method795() {
        try {

            CS2Script.aReferenceCache_2450.sweep(5);
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "wa.OC(" + (byte) 14 + ',' + 5 + ')');
        }
    }

    public static Class3_Sub28_Sub17 method1300(int var1, CacheIndex var3, CacheIndex var4) {
        System.out.println("Class 73 " + var1);
        return !Class75_Sub4.method1351(var3, 0, var1, -30901) ? null : method880(var4.getFile(var1, 0));
    }

    public static void method1301(int plane, int regionY, int regionX, int sceneY, boolean var4, int sceneX) {
        try {
            if (anInt3606 != regionX || Class3_Sub7.anInt2294 != regionY || plane != Class140_Sub3.anInt2745 && !NPC.method1986(45)) {
                anInt3606 = regionX;
                Class3_Sub7.anInt2294 = regionY;
                Class140_Sub3.anInt2745 = plane;
                if (NPC.method1986(105)) {
                    Class140_Sub3.anInt2745 = 0;
                }

                if (var4) {
                    Class117.method1719(28);
                } else {
                    Class117.method1719(25);
                }

                Class3_Sub13.method164((byte) -125, true, TextCore.LoadingPleaseWait2);
                int var8 = Class82.anInt1152;
                int var7 = Class131.anInt1716;
                Class82.anInt1152 = regionY * 8 - 48;
                Class131.anInt1716 = 8 * (-6 + regionX);
                Class3_Sub13_Sub21.aClass3_Sub28_Sub3_3264 = method884(8 * anInt3606, (byte) 88, 8 * Class3_Sub7.anInt2294);
                int var10 = -var8 + Class82.anInt1152;
                int var9 = Class131.anInt1716 + -var7;
                Class3_Sub13_Sub35.aClass131_3421 = null;
                int var11;
                NPC var12;
                int var13;
                if (var4) {
                    Class163.localNPCCount = 0;

                    for (var11 = 0; var11 < 32768; ++var11) {
                        var12 = NPC.npcs[var11];
                        if (null != var12) {
                            var12.anInt2819 -= 128 * var9;
                            var12.anInt2829 -= 128 * var10;
                            if (var12.anInt2819 >= 0 && var12.anInt2819 <= 13184 && var12.anInt2829 >= 0 && var12.anInt2829 <= 13184) {
                                for (var13 = 0; 10 > var13; ++var13) {
                                    var12.anIntArray2767[var13] -= var9;
                                    var12.anIntArray2755[var13] -= var10;
                                }

                                Class15.localNPCIndexes[Class163.localNPCCount++] = var11;
                            } else {
                                NPC.npcs[var11].setDefinitions((NPCDefinition) null);
                                NPC.npcs[var11] = null;
                            }
                        }
                    }
                } else {
                    for (var11 = 0; var11 < '\u8000'; ++var11) {
                        var12 = NPC.npcs[var11];
                        if (null != var12) {
                            for (var13 = 0; var13 < 10; ++var13) {
                                var12.anIntArray2767[var13] -= var9;
                                var12.anIntArray2755[var13] -= var10;
                            }

                            var12.anInt2819 -= 128 * var9;
                            var12.anInt2829 -= var10 * 128;
                        }
                    }
                }

                for (var11 = 0; var11 < 2048; ++var11) {
                    Player var23 = Class3_Sub13_Sub22.players[var11];
                    if (null != var23) {
                        for (var13 = 0; 10 > var13; ++var13) {
                            var23.anIntArray2767[var13] -= var9;
                            var23.anIntArray2755[var13] -= var10;
                        }

                        var23.anInt2819 -= 128 * var9;
                        var23.anInt2829 -= 128 * var10;
                    }
                }

                WorldListCountry.localPlane = plane;
                Class102.player.method1981(sceneX, false, sceneY);
                byte var25 = 104;
                byte var24 = 0;
                byte var14 = 0;
                byte var16 = 1;
                byte var15 = 104;
                byte var26 = 1;
                if (var10 < 0) {
                    var16 = -1;
                    var15 = -1;
                    var14 = 103;
                }

                if (var9 < 0) {
                    var26 = -1;
                    var24 = 103;
                    var25 = -1;
                }

                for (int var17 = var24; var25 != var17; var17 += var26) {
                    for (int var18 = var14; var18 != var15; var18 += var16) {
                        int var19 = var9 + var17;
                        int var20 = var18 + var10;

                        for (int var21 = 0; 4 > var21; ++var21) {
                            if (var19 >= 0 && var20 >= 0 && var19 < 104 && var20 < 104) {
                                Class3_Sub13_Sub22.aClass61ArrayArrayArray3273[var21][var17][var18] = Class3_Sub13_Sub22.aClass61ArrayArrayArray3273[var21][var19][var20];
                            } else {
                                Class3_Sub13_Sub22.aClass61ArrayArrayArray3273[var21][var17][var18] = null;
                            }
                        }
                    }
                }

                for (Class3_Sub4 var27 = (Class3_Sub4) Class3_Sub13_Sub6.aClass61_3075.method1222(); var27 != null; var27 = (Class3_Sub4) Class3_Sub13_Sub6.aClass61_3075.method1221()) {
                    var27.anInt2248 -= var10;
                    var27.anInt2264 -= var9;
                    if (0 > var27.anInt2264 || var27.anInt2248 < 0 || var27.anInt2264 >= 104 || var27.anInt2248 >= 104) {
                        var27.unlink();
                    }
                }

                if (var4) {
                    NPC.anInt3995 -= 128 * var9;
                    Class77.anInt1111 -= var10 * 128;
                    Class146.anInt1904 -= var10;
                    MouseListeningClass.anInt1923 -= var9;
                    Class157.anInt1996 -= var10;
                    anInt30 -= var9;
                } else {
                    Class133.anInt1753 = 1;
                }

                Class113.anInt1552 = 0;
                if (Class65.anInt987 != 0) {
                    Class45.anInt733 -= var10;
                    Class65.anInt987 -= var9;
                }

                if (HDToolKit.highDetail && var4 && (Math.abs(var9) > 104 || 104 < Math.abs(var10))) {
                    Class3_Sub13_Sub14.method236();
                }

                Class58.anInt909 = -1;
                Class3_Sub13_Sub15.aClass61_3177.method1211(-122);
                Class3_Sub13_Sub30.aClass61_3364.method1211(-87);
            }
        } catch (RuntimeException var22) {
            throw ClientErrorException.clientError(var22, "k.D(" + plane + ',' + regionY + ',' + regionX + ',' + sceneY + ',' + var4 + ',' + sceneX + ',' + true + ')');
        }
    }

    public static Class3_Sub28_Sub6 method1302() {
        try {
            Class3_Sub28_Sub6 var1 = (Class3_Sub28_Sub6) Class126.aClass13_1666.getFront();
            if (var1 == null) {
                do {
                    var1 = (Class3_Sub28_Sub6) Class81.aClass13_1139.getFront();
                    if (var1 == null) {
                        return null;
                    }

                    if (var1.b() > TimeUtils.time()) {
                        return null;
                    }

                    var1.unlink();
                    var1.unlinkNode();
                } while ((Long.MIN_VALUE & var1.nodeKey) == 0L);

                return var1;
            } else {
                var1.unlink();
                var1.unlinkNode();
                return var1;
            }
        } catch (RuntimeException var2) {
            throw ClientErrorException.clientError(var2, "k.J(" + (byte) -36 + ')');
        }
    }

    public static RSString method1303(RSInterface var0, RSString var1) {
        try {
            if (var1.indexOf(TextCore.aClass94_468, 102) == -1) {
                return var1;
            } else {
                while (true) {
                    int var3 = var1.indexOf(TextCore.aClass94_331, 55);
                    if (var3 == -1) {
                        while (true) {
                            var3 = var1.indexOf(TextCore.aClass94_2080, 106);
                            if (var3 == -1) {
                                while (true) {
                                    var3 = var1.indexOf(TextCore.aClass94_1301, 95);
                                    if (var3 == -1) {
                                        while (true) {
                                            var3 = var1.indexOf(TextCore.aClass94_852, 57);
                                            if (var3 == -1) {
                                                while (true) {
                                                    var3 = var1.indexOf(TextCore.aClass94_3418, 113);
                                                    if (var3 == -1) {
                                                        while (true) {
                                                            var3 = var1.indexOf(TextCore.aClass94_1051, 50);
                                                            if (var3 == -1) {
                                                                return var1;
                                                            }

                                                            RSString var4 = TextCore.aClass94_3672;
                                                            if (null != Class136.aClass64_1778) {
                                                                var4 = Class108.method1653(Class136.aClass64_1778.anInt979);

                                                                if (null != Class136.aClass64_1778.anObject974) {
                                                                    byte[] var5 = ((String) Class136.aClass64_1778.anObject974).getBytes(StandardCharsets.ISO_8859_1);
                                                                    var4 = Class3_Sub13_Sub3.method178(var5, var5.length, 0);
                                                                }
                                                            }

                                                            var1 = RSString.stringCombiner(new RSString[]{var1.substring(0, var3, 0), var4, var1.substring(4 + var3)});
                                                        }
                                                    }

                                                    var1 = RSString.stringCombiner(new RSString[]{var1.substring(0, var3, 0), Class154.method2148(Class164_Sub2.method2247((byte) -4, 4, var0)), var1.substring(var3 - -2)});
                                                }
                                            }

                                            var1 = RSString.stringCombiner(new RSString[]{var1.substring(0, var3, 0), Class154.method2148(Class164_Sub2.method2247((byte) -109, 3, var0)), var1.substring(2 + var3)});
                                        }
                                    }

                                    var1 = RSString.stringCombiner(new RSString[]{var1.substring(0, var3, 0), Class154.method2148(Class164_Sub2.method2247((byte) 111, 2, var0)), var1.substring(2 + var3)});
                                }
                            }

                            var1 = RSString.stringCombiner(new RSString[]{var1.substring(0, var3, 0), Class154.method2148(Class164_Sub2.method2247((byte) 23, 1, var0)), var1.substring(var3 + 2)});
                        }
                    }

                    var1 = RSString.stringCombiner(new RSString[]{var1.substring(0, var3, 0), Class154.method2148(Class164_Sub2.method2247((byte) 107, 0, var0)), var1.substring(2 + var3)});
                }
            }
        } catch (RuntimeException var7) {
            throw ClientErrorException.clientError(var7, "k.K(" + (var0 != null ? "{...}" : "null") + ',' + (var1 != null ? "{...}" : "null") + ',' + 0 + ')');
        }
    }

    public static void method2143(byte var0, int var1, int var2, int var3, int var4) {
        try {
            Class3_Sub28_Sub6 var5 = Class3_Sub24_Sub3.method466(8, var2);
            var5.g();
            var5.anInt3596 = var1;
            if (var0 >= -120) {
                anInt1950 = -14;
            }

            var5.anInt3598 = var4;
            var5.anInt3597 = var3;
        } catch (RuntimeException var6) {
            throw ClientErrorException.clientError(var6, "ve.U(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
        }
    }

    public static void method2065(CacheIndex var1, CacheIndex var2) {
        try {
            Class3_Sub13_Sub19.aClass153_3227 = var1;
            AnimationDefinition.aClass153_1852 = var2;
        } catch (RuntimeException var4) {
            throw ClientErrorException.clientError(var4, "u.D(" + (byte) -125 + ',' + (var1 != null ? "{...}" : "null") + ',' + (var2 != null ? "{...}" : "null") + ')');
        }
    }

    public static void method2067() {
        try {
            for (Class3_Sub28_Sub2 var1 = (Class3_Sub28_Sub2) Class3_Sub13_Sub15.aClass61_3177.method1222(); null != var1; var1 = (Class3_Sub28_Sub2) Class3_Sub13_Sub15.aClass61_3177.method1221()) {
                Class140_Sub2 var2 = var1.aClass140_Sub2_3545;
                if (WorldListCountry.localPlane == var2.anInt2717 && !var2.aBoolean2718) {
                    if (Class44.anInt719 >= var2.anInt2703) {
                        var2.method1955(Class106.anInt1446);
                        if (var2.aBoolean2718) {
                            var1.unlink();
                        } else {
                            Class20.method907(var2.anInt2717, var2.anInt2716, var2.anInt2710, var2.anInt2712, 60, var2, 0, -1L, false);
                        }
                    }
                } else {
                    var1.unlink();
                }
            }

        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "u.G(" + false + ')');
        }
    }

    // static final void method2068(NPCDefinition var0, int var1, int var2, int var3, int var4) {
    // @Splinter
    public static void drawNpcRightClickOptions(NPCDefinition var0, int var1, int var2, int var3, int var4) {
        try {
            if (menuOptionCount < 400) {
                if (var0.childNPCs != null) {
                    var0 = var0.method1471((byte) 66);
                }

                if (null != var0) {
                    if (var0.aBoolean1270) {
                        RSString var5 = var0.NPCName;
                        if (0 != var0.anInt1260) {
                            RSString var6 = Class158.anInt2014 != 1 ? TextCore.HasLevel : TextCore.HasRating;
                            var5 = RSString.stringCombiner(new RSString[]{var5, Player.combatLevelColor(var0.anInt1260, (byte) -122, Class102.player.COMBAT_LEVEL), TextCore.LEFT_PARENTHESES, var6, RSString.stringAnimator(var0.anInt1260), TextCore.RIGHT_PARENTHESES});
                        }

                        if (Class164_Sub1.anInt3012 == 1) {
                            Class3_Sub24_Sub4.method1177(Class99.anInt1403, (long) var3, (byte) -125, RSString.stringCombiner(new RSString[]{RenderAnimationDefinition.aClass94_378, ColorCore.TextColor, var5}), var1, (short) 26, TextCore.HasUse, var4);
                        } else if (GameObject.aBoolean1837) {
                            Class3_Sub28_Sub9 var12 = -1 == anInt1038 ? null : Class61.method1210(anInt1038);
                            if ((2 & Class164.anInt2051) != 0 && (var12 == null || var0.method1475(anInt1038, var12.anInt3614) != var12.anInt3614)) {
                                Class3_Sub24_Sub4.method1177(anInt1887, (long) var3, (byte) -93, RSString.stringCombiner(new RSString[]{TextCore.aClass94_676, ColorCore.TextColor, var5}), var1, (short) 45, Class3_Sub28_Sub9.aClass94_3621, var4);
                            }
                        } else {
                            RSString[] var11 = var0.options;
                            if (Class123.aBoolean1656) {
                                var11 = Class3_Sub31.optionsArrayStringConstructor(var11);
                            }

                            int var7;
                            if (var11 != null) {
                                for (var7 = 4; var7 >= 0; --var7) {
                                    if (var11[var7] != null && (Class158.anInt2014 != 0 || !var11[var7].equalsStringIgnoreCase(TextCore.HasAttack))) {
                                        byte var8 = 0;
                                        if (var7 == 0) {
                                            var8 = 17;
                                        }

                                        if (var7 == 1) {
                                            var8 = 16;
                                        }

                                        int var9 = -1;
                                        if (var7 == 2) {
                                            var8 = 4;
                                        }

                                        if (var7 == 3) {
                                            var8 = 19;
                                        }

                                        if (var0.anInt1296 == var7) {
                                            var9 = var0.anInt1253;
                                        }

                                        if (var0.anInt1289 == var7) {
                                            var9 = var0.anInt1278;
                                        }

                                        if (var7 == 4) {
                                            var8 = 2;
                                        }

                                        Class3_Sub24_Sub4.method1177(var9, (long) var3, (byte) -103, RSString.stringCombiner(new RSString[]{ColorCore.NPCRightClickColor, var5}), var1, var8, var11[var7], var4);
                                    }
                                }
                            }

                            if (0 == Class158.anInt2014 && var11 != null) {
                                for (var7 = 4; var7 >= 0; --var7) {
                                    if (null != var11[var7] && var11[var7].equalsStringIgnoreCase(TextCore.HasAttack)) {
                                        short var14 = 0;
                                        if (var0.anInt1260 > Class102.player.COMBAT_LEVEL) {
                                            var14 = 2000;
                                            //This var sets "attack" as a right click attack option for higher level npcs, let's make it a single click!
                                        }

                                        short var13 = 0;
                                        if (var7 == 0) {
                                            var13 = 17;
                                        }

                                        if (var7 == 1) {
                                            var13 = 16;
                                        }

                                        if (2 == var7) {
                                            var13 = 4;
                                        }

                                        if (3 == var7) {
                                            var13 = 19;
                                        }

                                        if (var7 == 4) {
                                            var13 = 2;
                                        }

                                        if (0 != var13) {
                                            var13 += var14;
                                        }

                                        Class3_Sub24_Sub4.method1177(var0.anInt1298, (long) var3, (byte) -128, RSString.stringCombiner(new RSString[]{ColorCore.NPCRightClickColor, var5}), var1, var13, var11[var7], var4);
                                    }
                                }
                            }

                            if (GameConfig.NPC_DEBUG_ENABLED) {
                                Class3_Sub24_Sub4.method1177(Class131.anInt1719, (long) var3, (byte) -73, RSString.stringCombiner(new RSString[]{ColorCore.NPCRightClickColor, var5}), var1, (short) 1007, RSString.parse("Examine" + "<br>" + " ID: (X" + var0.npcId + "(Y"), var4);
                            } else {
                                Class3_Sub24_Sub4.method1177(Class131.anInt1719, (long) var3, (byte) -73, RSString.stringCombiner(new RSString[]{ColorCore.NPCRightClickColor, var5}), var1, (short) 1007, TextCore.HasExamine, var4);
                            }
                        }

                    }
                }
            }
        } catch (RuntimeException var10) {
            throw ClientErrorException.clientError(var10, "u.A(" + (var0 != null ? "{...}" : "null") + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ')');
        }
    }

    public static Class3_Sub28_Sub12 method2069(int var0) {
        Class3_Sub28_Sub12 var2 = (Class3_Sub28_Sub12) Class49.aClass47_818.get(var0);
        if (null == var2) {
            byte[] var3 = Class8.aClass153_105.getFile(5, var0);
            var2 = new Class3_Sub28_Sub12();
            if (var3 != null) {
                var2.decode(new DataBuffer(var3));
            }

            Class49.aClass47_818.put(var0, var2);
        }
        return var2;
    }

    static void method56(int var0) {
        Class3_Sub28_Sub6 var2 = Class3_Sub24_Sub3.method466(6, var0);
        var2.a();
    }

    static boolean loadInterface(int archive) {
        if (!aBooleanArray1703[archive]) {
            if (aClass153_3361.method2117(archive)) {
                int fileLength = aClass153_3361.getFileAmount(archive);
                if (0 == fileLength) {
                    aBooleanArray1703[archive] = true;
                } else {
                    if (null == GameObject.aClass11ArrayArray1834[archive]) {
                        GameObject.aClass11ArrayArray1834[archive] = new RSInterface[fileLength];
                    }

                    for (int fileId = 0; fileId < fileLength; ++fileId) {
                        if (null == GameObject.aClass11ArrayArray1834[archive][fileId]) {
                            byte[] var4 = aClass153_3361.getFile(archive, fileId);
                            if (var4 != null) {
                                RSInterface iface = GameObject.aClass11ArrayArray1834[archive][fileId] = new RSInterface();
                                iface.componentHash = fileId + (archive << 16);
                                if (var4[0] == -1) {
                                    iface.decodeScriptFormat(new DataBuffer(var4));
                                } else {
                                    iface.decodeNoScripts(new DataBuffer(var4));
                                }
                            }
                        }
                    }

                    aBooleanArray1703[archive] = true;

                }
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    static void parsePlayerMasks() {
        int var1 = 0;

        while (var1 < maskUpdateCount) {
            int var2 = Class21.maskUpdateIndexes[var1];
            Player var3 = Class3_Sub13_Sub22.players[var2];
            int var4 = GraphicDefinition.incomingBuffer.readUnsignedByte();
            if ((16 & var4) != 0) {
                var4 += GraphicDefinition.incomingBuffer.readUnsignedByte() << 8;
            }

            Class45.parsePlayerMask(var4, var2, var3);
            ++var1;
        }
    }

    static void method60(int var0, int var1, int var2, Class91[] var3, int var4, byte var5, byte[] var6, int var7, int var8, int var9, boolean var10) {
        int var13;
        if (!var10) {
            for (int var12 = 0; var12 < 8; ++var12) {
                for (var13 = 0; 8 > var13; ++var13) {
                    if (0 < var1 - -var12 && var12 + var1 < 103 && var13 + var4 > 0 && var4 + var13 < 103) {
                        var3[var2].anIntArrayArray1304[var12 + var1][var13 + var4] = bitwiseAnd(var3[var2].anIntArrayArray1304[var12 + var1][var13 + var4], -16777217);
                    }
                }
            }
        }
        byte var11;
        if (var10) {
            var11 = 1;
        } else {
            var11 = 4;
        }

        DataBuffer var25 = new DataBuffer(var6);

        int var14;
        int var15;
        for (var13 = 0; var13 < var11; ++var13) {
            for (var14 = 0; var14 < 64; ++var14) {
                for (var15 = 0; var15 < 64; ++var15) {
                    if (var13 == var7 && var9 <= var14 && 8 + var9 > var14 && var8 <= var15 && var15 < 8 + var8) {
                        Class167.method2267(0, 0, var10, var25, Class3_Sub13_Sub29.method310(var0, (byte) -117, 7 & var14, 7 & var15) + var4, method519(var0, var15 & 7, var14 & 7) + var1, (byte) 63, var0, var2);
                    } else {
                        Class167.method2267(0, 0, var10, var25, -1, -1, (byte) 123, 0, 0);
                    }
                }
            }
        }

        int var17;
        int var21;
        int var20;
        int var22;
        int var29;
        while (var25.index < var25.buffer.length) {
            var14 = var25.readUnsignedByte();
            if (var14 != 129) {
                --var25.index;
                break;
            }

            for (var15 = 0; var15 < 4; ++var15) {
                byte var16 = var25.readSignedByte();
                int var18;
                if (var16 != 0) {
                    if (var16 == 1) {
                        for (var17 = 0; 64 > var17; var17 += 4) {
                            for (var18 = 0; var18 < 64; var18 += 4) {
                                byte var19 = var25.readSignedByte();
                                if (var7 >= var15) {
                                    for (var20 = var17; var17 + 4 > var20; ++var20) {
                                        for (var21 = var18; var21 < 4 + var18; ++var21) {
                                            if (var9 <= var20 && 8 + var9 > var20 && var8 <= var21) {
                                                var22 = var1 - -method519(var0, var21 & 7, var20 & 7);
                                                int var23 = Class3_Sub13_Sub29.method310(var0, (byte) -97, 7 & var20, var21 & 7) + var4;
                                                if (0 <= var22 && 104 > var22 && var23 >= 0 && var23 < 104) {
                                                    aByteArrayArrayArray1774[var2][var22][var23] = var19;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (var15 <= var7) {
                    var18 = 7 + var1;
                    var17 = var1;
                    var20 = var4 - -7;
                    if (var20 < 0) {
                        var20 = 0;
                    } else if (104 <= var20) {
                        var20 = 104;
                    }

                    if (0 <= var18) {
                        if (var18 >= 104) {
                            var18 = 104;
                        }
                    } else {
                        var18 = 0;
                    }

                    var29 = var4;
                    if (var4 < 0) {
                        var29 = 0;
                    } else if (104 <= var4) {
                        var29 = 104;
                    }

                    if (var1 < 0) {
                        var17 = 0;
                    } else if (104 <= var1) {
                        var17 = 104;
                    }

                    while (var18 > var17) {
                        while (var29 < var20) {
                            aByteArrayArrayArray1774[var2][var17][var29] = 0;
                            ++var29;
                        }

                        ++var17;
                    }
                }
            }
        }

        int var28;
        if (HDToolKit.highDetail && !var10) {
            AtmosphereParser var26 = null;

            while (var25.buffer.length > var25.index) {
                var15 = var25.readUnsignedByte();
                if (var15 == 0) {
                    var26 = new AtmosphereParser(var25);
                } else {
                    if (var15 != 1) {
                        throw new IllegalStateException();
                    }

                    var28 = var25.readUnsignedByte();
                    if (var28 > 0) {
                        for (var17 = 0; var28 > var17; ++var17) {
                            Class43 var30 = new Class43(var25);
                            if (var30.anInt705 == 31) {
                                Class57 var31 = Class81.method1401(var25.readUnsignedShort());
                                var30.method1060((byte) 123, var31.anInt896, var31.anInt908, var31.anInt899, var31.anInt907);
                            }

                            var29 = var30.anInt703 >> 7;
                            var20 = var30.anInt708 >> 7;
                            if (var30.anInt704 == var7 && var9 <= var29 && var9 - -8 > var29 && var20 >= var8 && var20 < var8 - -8) {
                                var21 = Class3_Sub26.method514(var0, var30.anInt703 & 1023, 1023 & var30.anInt708) + (var1 << 7);
                                var22 = Class3_Sub13_Sub25.method293(var30.anInt703 & 1023, var0, 1023 & var30.anInt708) + (var4 << 7);
                                var30.anInt703 = var21;
                                var30.anInt708 = var22;
                                var29 = var30.anInt703 >> 7;
                                var20 = var30.anInt708 >> 7;
                                if (var29 >= 0 && var20 >= 0 && 104 > var29 && var20 < 104) {
                                    var30.aBoolean696 = (2 & aByteArrayArrayArray113[1][var29][var20]) != 0;
                                    var30.anInt697 = Class44.anIntArrayArrayArray723[var30.anInt704][var29][var20] - var30.anInt697;
                                    Class68.method1264(var30);
                                }
                            }
                        }
                    }
                }
            }

            if (null == var26) {
                var26 = new AtmosphereParser();
            }

            AtmosphereParser.aAtmosphereParserArrayArray1581[var1 >> 3][var4 >> 3] = var26;
        }

        var14 = 7 + var1;
        var15 = var4 - -7;

        for (var28 = var1; var14 > var28; ++var28) {
            for (var17 = var4; var15 > var17; ++var17) {
                aByteArrayArrayArray1774[var2][var28][var17] = 0;
            }
        }
    }

    public static int method54(int var0) {
        try {
            return var0 & 127;
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "oe.A(" + var0 + ',' + false + ')');
        }
    }

//    public static void method55() {
//        try {
//
//            aReferenceCache_21.clearSoftReferences();
//            ObjectDefinition.aReferenceCache_1401.clearSoftReferences();
//            aReferenceCache_4051.clearSoftReferences();
//            ObjectDefinition.aReferenceCache_1965.clearSoftReferences();
//        } catch (RuntimeException var2) {
//            throw ClientErrorException.clientError(var2, "oe.B(" + 22683 + ')');
//        }
//    }

    public static LDIndexedSprite[] method619(byte var0, int var1, CacheIndex var2) {
        try {
            return Class140_Sub7.method2029((byte) -119, var2, var1) ? (var0 <= 52 ? (LDIndexedSprite[]) null : method1281()) : null;
        } catch (RuntimeException var4) {
            throw ClientErrorException.clientError(var4, "ml.A(" + var0 + ',' + var1 + ',' + (var2 != null ? "{...}" : "null") + ')');
        }
    }

    public static void method383(int var0, int var1) {
        try {
            if (var1 == Class23.anInt453 && var1 != 0) {
                ShaderInterface var2 = anShaderInterfaceArray70[var1];
                var2.method23(Class3_Sub13_Sub21.anInt3263);
            }

            if (var0 != -32584) {
                method383(60, 23);
            }

        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "ka.A(" + var0 + ',' + var1 + ')');
        }
    }

    public static int method599(CacheIndex var1) {
        try {
            int var2 = 0;
            if (var1.retrieveSpriteFile(Class154.anInt1966)) {
                ++var2;
            }

            if (var1.retrieveSpriteFile(Class79.anInt1124)) {
                ++var2;
            }

            return var2;
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "lk.F(" + -20916 + ',' + (var1 != null ? "{...}" : "null") + ')');
        }
    }

    public static AbstractSprite method602(int var0, CacheIndex var1) {
        try {
            //  System.out.println("Class3_sub28_Sub16 " + var1);
            if (Class75_Sub4.method1351(var1, 0, var0, (byte) -18 ^ 30885)) {
                return Class43.method1062((byte) -18 + 103);
            } else {
                return null;
            }
        } catch (RuntimeException var5) {
            throw ClientErrorException.clientError(var5, "lk.R(" + 0 + ',' + var0 + ',' + (var1 != null ? "{...}" : "null") + ')');
        }
    }

    /**
     * @param notifyScripts Whether if a listening CS2 script should be invoked if this interface is resized
     */
    public static void calculateInterfaceSize(RSInterface iface, int parentWidth, int parentHeight, boolean notifyScripts) {
        int oldWidth = iface.width;
        int oldHeight = iface.height;

        if (iface.horizontalResize == 0) {
            iface.width = iface.defWidth;
        } else if (iface.horizontalResize == 1) {
            iface.width = parentWidth + -iface.defWidth;
        } else if (iface.horizontalResize == 2) {
            iface.width = iface.defWidth * parentWidth >> 14;
        } else if (iface.horizontalResize == 3) {
            if (iface.type == 2) {
                iface.width = iface.defWidth * 32 - -((iface.defWidth - 1) * iface.anInt285);
            } else if (iface.type == 7) {
                iface.width = 115 * iface.defWidth + iface.anInt285 * (-1 + iface.defWidth);
            }
        }

        if (iface.verticalResize == 0) {
            iface.height = iface.defHeight;
        } else if (iface.verticalResize == 1) {
            iface.height = -iface.defHeight + parentHeight;
        } else if (iface.verticalResize == 2) {
            iface.height = parentHeight * iface.defHeight >> 14;
        } else if (iface.verticalResize == 3) {
            if (iface.type == 2) {
                iface.height = (iface.defHeight + -1) * iface.anInt290 + iface.defHeight * 32;
            } else if (iface.type == 7) {
                iface.height = iface.defHeight * 12 + (-1 + iface.defHeight) * iface.anInt290;
            }
        }

        if (iface.horizontalResize == 4) {
            iface.width = iface.anInt216 * iface.height / iface.anInt160;
        }

        if (iface.verticalResize == 4) {
            iface.height = iface.anInt160 * iface.width / iface.anInt216;
        }

        if (ClientCommands.commandQaOpEnabled && (Client.method44(iface).anInt2205 != 0 || iface.type == 0)) {
            if (iface.height < 5 && 5 > iface.width) {
                iface.height = 5;
                iface.width = 5;
            } else {
                if (iface.width <= 0) {
                    iface.width = 5;
                }

                if (0 >= iface.height) {
                    iface.height = 5;
                }
            }
        }

        if (iface.anInt189 == 1337) {
            Class168.aClass11_2091 = iface;
        }

        if (notifyScripts && null != iface.anObjectArray235 && (iface.width != oldWidth || iface.height != oldHeight)) {
            CS2Script script = new CS2Script();
            script.arguments = iface.anObjectArray235;
            script.aClass11_2449 = iface;
            Client.aClass61_1471.method1215(script);
        }
    }

    public static void method606(int var0, Class3_Sub9 var1, int var2, int var3, int var4, int var5) {
        try {
            if (var5 > 44) {
                if (var1.anInt2332 != -1 || var1.anIntArray2333 != null) {
                    int var6 = 0;
                    if (var1.anInt2321 < var0) {
                        var6 += -var1.anInt2321 + var0;
                    } else if (var1.anInt2326 > var0) {
                        var6 += var1.anInt2326 - var0;
                    }

                    if (var1.anInt2307 >= var4) {
                        if (var4 < var1.anInt2308) {
                            var6 += -var4 + var1.anInt2308;
                        }
                    } else {
                        var6 += -var1.anInt2307 + var4;
                    }

                    if (0 != var1.anInt2328 && var6 - 64 <= var1.anInt2328 && 0 != Sprites.anInt340 && var2 == var1.anInt2314) {
                        var6 -= 64;
                        if (var6 < 0) {
                            var6 = 0;
                        }

                        int var7 = (-var6 + var1.anInt2328) * Sprites.anInt340 / var1.anInt2328;
                        if (var1.aClass3_Sub24_Sub1_2312 == null) {
                            if (var1.anInt2332 >= 0) {
                                Class135 var8 = Class135.method1811(CacheIndex.soundFXIndex, var1.anInt2332, 0);
                                if (null != var8) {
                                    Class3_Sub12_Sub1 var9 = var8.method1812().method151(Class27.aClass157_524);
                                    Class3_Sub24_Sub1 var10 = Class3_Sub24_Sub1.method437(var9, var7);
                                    Objects.requireNonNull(var10).method429(-1);
                                    Class3_Sub26.aClass3_Sub24_Sub2_2563.method457(var10);
                                    var1.aClass3_Sub24_Sub1_2312 = var10;
                                }
                            }
                        } else {
                            var1.aClass3_Sub24_Sub1_2312.method419(var7);
                        }

                        if (null != var1.aClass3_Sub24_Sub1_2315) {
                            var1.aClass3_Sub24_Sub1_2315.method419(var7);
                            if (!var1.aClass3_Sub24_Sub1_2315.isLinked()) {
                                var1.aClass3_Sub24_Sub1_2315 = null;
                            }
                        } else if (var1.anIntArray2333 != null && ((var1.anInt2316 -= var3) <= 0)) {
                            int var13 = (int) ((double) var1.anIntArray2333.length * Math.random());
                            Class135 var14 = Class135.method1811(CacheIndex.soundFXIndex, var1.anIntArray2333[var13], 0);
                            if (null != var14) {
                                Class3_Sub12_Sub1 var15 = var14.method1812().method151(Class27.aClass157_524);
                                Class3_Sub24_Sub1 var11 = Class3_Sub24_Sub1.method437(var15, var7);
                                Objects.requireNonNull(var11).method429(0);
                                Class3_Sub26.aClass3_Sub24_Sub2_2563.method457(var11);
                                var1.anInt2316 = (int) ((double) (-var1.anInt2310 + var1.anInt2325) * Math.random()) + var1.anInt2310;
                                var1.aClass3_Sub24_Sub1_2315 = var11;
                            }
                        }

                    } else {
                        if (null != var1.aClass3_Sub24_Sub1_2312) {
                            Class3_Sub26.aClass3_Sub24_Sub2_2563.method461(var1.aClass3_Sub24_Sub1_2312);
                            var1.aClass3_Sub24_Sub1_2312 = null;
                        }

                        if (var1.aClass3_Sub24_Sub1_2315 != null) {
                            Class3_Sub26.aClass3_Sub24_Sub2_2563.method461(var1.aClass3_Sub24_Sub1_2315);
                            var1.aClass3_Sub24_Sub1_2315 = null;
                        }

                    }
                }
            }
        } catch (RuntimeException var12) {
            throw ClientErrorException.clientError(var12, "lk.O(" + var0 + ',' + (var1 != null ? "{...}" : "null") + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
        }
    }

    static void method1180(byte var0, Class140_Sub4 var1) {
        try {
            RenderAnimationDefinition var2 = var1.method1965();
            var1.anInt2764 = var2.anInt368;
            if (var0 >= -5) {
                anIntArray882 = (int[]) null;
            }

            if (var1.anInt2816 == 0) {
                var1.anInt2824 = 0;
            } else {
                if (var1.anInt2771 != -1 && 0 == var1.anInt2828) {
                    AnimationDefinition var3 = Client.getAnimationDefinition(var1.anInt2771);
                    if (var1.anInt2811 > 0 && var3.anInt1866 == 0) {
                        ++var1.anInt2824;
                        return;
                    }

                    if (var1.anInt2811 <= 0 && var3.anInt1850 == 0) {
                        ++var1.anInt2824;
                        return;
                    }
                }

                int var18 = var1.anInt2819;
                int var4 = var1.anInt2829;
                int var5 = var1.anIntArray2767[-1 + var1.anInt2816] * 128 - -(var1.getSize() * 64);
                int var6 = 128 * var1.anIntArray2755[-1 + var1.anInt2816] - -(var1.getSize() * 64);
                if (var5 + -var18 > 256 || -var18 + var5 < -256 || var6 - var4 > 256 || -256 > var6 - var4) {
                    var1.anInt2819 = var5;
                    var1.anInt2829 = var6;
                    return;
                }

                if (var5 <= var18) {
                    if (var18 <= var5) {
                        if (var6 <= var4) {
                            if (var6 < var4) {
                                var1.anInt2806 = 0;
                            }
                        } else {
                            var1.anInt2806 = 1024;
                        }
                    } else if (var6 > var4) {
                        var1.anInt2806 = 768;
                    } else if (var6 < var4) {
                        var1.anInt2806 = 256;
                    } else {
                        var1.anInt2806 = 512;
                    }
                } else if (var6 > var4) {
                    var1.anInt2806 = 1280;
                } else if (var4 > var6) {
                    var1.anInt2806 = 1792;
                } else {
                    var1.anInt2806 = 1536;
                }

                int var7 = 2047 & -var1.anInt2785 + var1.anInt2806;
                int var8 = var2.anInt389;
                if (1024 < var7) {
                    var7 -= 2048;
                }

                boolean var10 = true;
                byte var11 = 1;
                if (var7 >= -256 && var7 <= 256) {
                    var8 = var2.anInt382;
                } else if (var7 >= 256 && 768 > var7) {
                    var8 = var2.anInt364;
                } else if (var7 >= -768 && -256 >= var7) {
                    var8 = var2.anInt390;
                }

                int var9 = 4;
                if (var8 == -1) {
                    var8 = var2.anInt382;
                }

                var1.anInt2764 = var8;
                if (var1 instanceof NPC) {
                    var10 = ((NPC) var1).definition.aBoolean1255;
                }

                if (var10) {
                    if (var1.anInt2806 != var1.anInt2785 && var1.anInt2772 == -1 && var1.anInt2779 != 0) {
                        var9 = 2;
                    }

                    if (2 < var1.anInt2816) {
                        var9 = 6;
                    }

                    if (3 < var1.anInt2816) {
                        var9 = 8;
                    }

                    if (var1.anInt2824 > 0 && var1.anInt2816 > 1) {
                        var9 = 8;
                        --var1.anInt2824;
                    }
                } else {
                    if (1 < var1.anInt2816) {
                        var9 = 6;
                    }

                    if (var1.anInt2816 > 2) {
                        var9 = 8;
                    }

                    if (var1.anInt2824 > 0 && var1.anInt2816 > 1) {
                        --var1.anInt2824;
                        var9 = 8;
                    }
                }

                if (2 == var1.aByteArray2795[var1.anInt2816 - 1]) {
                    var9 <<= 1;
                    var11 = 2;
                } else if (var1.aByteArray2795[-1 + var1.anInt2816] == 0) {
                    var11 = 0;
                    var9 >>= 1;
                }

                if (var9 >= 8 && -1 != var2.anInt393) {
                    if (var1.anInt2764 == var2.anInt389 && -1 != var2.anInt386) {
                        var1.anInt2764 = var2.anInt386;
                    } else if (var1.anInt2764 == var2.anInt390 && var2.anInt373 != -1) {
                        var1.anInt2764 = var2.anInt373;
                    } else if (var2.anInt364 == var1.anInt2764 && var2.anInt375 != -1) {
                        var1.anInt2764 = var2.anInt375;
                    } else {
                        var1.anInt2764 = var2.anInt393;
                    }
                } else if (var2.anInt398 != -1 && var11 == 0) {
                    if (var1.anInt2764 == var2.anInt389 && var2.anInt372 != -1) {
                        var1.anInt2764 = var2.anInt372;
                    } else if (var1.anInt2764 == var2.anInt390 && -1 != var2.anInt406) {
                        var1.anInt2764 = var2.anInt406;
                    } else if (var2.anInt364 == var1.anInt2764 && -1 != var2.anInt379) {
                        var1.anInt2764 = var2.anInt379;
                    } else {
                        var1.anInt2764 = var2.anInt398;
                    }
                }

                if (var2.anInt360 != -1) {
                    var9 <<= 7;
                    if (var1.anInt2816 == 1) {
                        int var13 = (var1.anInt2819 <= var5 ? var5 - var1.anInt2819 : -var5 + var1.anInt2819) << 7;
                        int var12 = var1.anInt2758 * var1.anInt2758;
                        int var14 = (var1.anInt2829 > var6 ? -var6 + var1.anInt2829 : -var1.anInt2829 + var6) << 7;
                        int var15 = var13 > var14 ? var13 : var14;
                        int var16 = var2.anInt360 * 2 * var15;
                        if (var12 <= var16) {
                            if (var12 / 2 > var15) {
                                var1.anInt2758 -= var2.anInt360;
                                if (var1.anInt2758 < 0) {
                                    var1.anInt2758 = 0;
                                }
                            } else if (var1.anInt2758 < var9) {
                                var1.anInt2758 += var2.anInt360;
                                if (var1.anInt2758 > var9) {
                                    var1.anInt2758 = var9;
                                }
                            }
                        } else {
                            var1.anInt2758 /= 2;
                        }
                    } else if (var1.anInt2758 < var9) {
                        var1.anInt2758 += var2.anInt360;
                        if (var1.anInt2758 > var9) {
                            var1.anInt2758 = var9;
                        }
                    } else {
                        var1.anInt2758 -= var2.anInt360;
                        if (0 > var1.anInt2758) {
                            var1.anInt2758 = 0;
                        }
                    }

                    var9 = var1.anInt2758 >> 7;
                    if (var9 < 1) {
                        var9 = 1;
                    }
                }

                if (var5 > var18) {
                    var1.anInt2819 += var9;
                    if (var1.anInt2819 > var5) {
                        var1.anInt2819 = var5;
                    }
                } else if (var18 > var5) {
                    var1.anInt2819 -= var9;
                    if (var1.anInt2819 < var5) {
                        var1.anInt2819 = var5;
                    }
                }

                if (var4 >= var6) {
                    if (var6 < var4) {
                        var1.anInt2829 -= var9;
                        if (var6 > var1.anInt2829) {
                            var1.anInt2829 = var6;
                        }
                    }
                } else {
                    var1.anInt2829 += var9;
                    if (var6 < var1.anInt2829) {
                        var1.anInt2829 = var6;
                    }
                }

                if (var5 == var1.anInt2819 && var6 == var1.anInt2829) {
                    --var1.anInt2816;
                    if (0 < var1.anInt2811) {
                        --var1.anInt2811;
                    }
                }
            }

        } catch (RuntimeException var17) {
            throw ClientErrorException.clientError(var17, "ia.C(" + var0 + ',' + (var1 != null ? "{...}" : "null") + ')');
        }
    }

    static int method1184(int var0, byte var1) {
        try {
            int var3 = var0 & 63;
            int var4 = (var0 & 217) >> 6;
            if (var3 == 18) {
                if (0 == var4) {
                    return 1;
                }

                if (var4 == 1) {
                    return 2;
                }

                if (var4 == 2) {
                    return 4;
                }

                return 8;
            } else if (var3 == 19 || var3 == 21) {
                if (var4 == 0) {
                    return 16;
                }

                if (1 == var4) {
                    return 32;
                }

                if (var4 == 2) {
                    return 64;
                }

                return 128;
            }

            return 0;
        } catch (RuntimeException var5) {
            throw ClientErrorException.clientError(var5, "ia.A(" + var0 + ',' + var1 + ')');
        }
    }

    public static boolean method73(short var0) {
        try {
            if(var0 != 47 && var0 != 5 && var0 != 43 && var0 != 35 && var0 != 58 && var0 != 22 && var0 != 40 && var0 != 3) {
                if(var0 != 9 && var0 != 12 && var0 != 1006 && var0 != 1003) {

                    return var0 == 25 || var0 == 23 || 48 == var0 || var0 == 7 || var0 == 13 || (var0 == 8 || var0 == 32 || var0 == 28 || var0 == 59 || var0 == 51 || var0 == 41);
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "aa.C(" + var0 + ',' + 121 + ')');
        }
    }

    public static void method75(RSInterface[] var0, int var2) {
        try {
            for(int var3 = 0; var0.length > var3; ++var3) {
                RSInterface var4 = var0[var3];
                if(null != var4) {
                    if(var4.type == 0) {
                        if(null != var4.aClass11Array262) {
                            method75(var4.aClass11Array262, var2);
                        }

                        Class3_Sub31 var5 = (Class3_Sub31)Class3_Sub13_Sub17.aHashTable_3208.get((long)var4.componentHash);
                        if(null != var5) {
                            Class3_Sub8.method124(49, var2, var5.anInt2602);
                        }
                    }

                    CS2Script var7;
                    if(var2 == 0 && null != var4.anObjectArray206) {
                        var7 = new CS2Script();
                        var7.arguments = var4.anObjectArray206;
                        var7.aClass11_2449 = var4;
                        Class43.method1065(var7);
                    }

                    if(var2 == 1 && var4.anObjectArray176 != null) {
                        if(var4.anInt191 >= 0) {
                            RSInterface var8 = Class7.getRSInterface(var4.componentHash);
                            if(null == var8 || null == var8.aClass11Array262 || var8.aClass11Array262.length <= var4.anInt191 || var8.aClass11Array262[var4.anInt191] != var4) {
                                continue;
                            }
                        }

                        var7 = new CS2Script();
                        var7.arguments = var4.anObjectArray176;
                        var7.aClass11_2449 = var4;
                        Class43.method1065(var7);
                    }
                }
            }

        } catch (RuntimeException var6) {
            throw ClientErrorException.clientError(var6, "aa.I(" + (var0 != null?"{...}":"null") + ',' + true + ',' + var2 + ')');
        }
    }

    public static boolean method76(int var0, int var1, int var2, int var3, int var4, boolean var5, int var6, int var7, int var8, int var9, int var11) {
        try {
            int var12;
            int var13;
            for(var12 = 0; 104 > var12; ++var12) {
                for(var13 = 0; var13 < 104; ++var13) {
                    Class84.anIntArrayArray1160[var12][var13] = 0;
                    Class97.anIntArrayArray1373[var12][var13] = 99999999;
                }
            }

            Class84.anIntArrayArray1160[var11][var3] = 99;
            Class97.anIntArrayArray1373[var11][var3] = 0;
            var13 = var3;
            var12 = var11;
            byte var14 = 0;
            Class3_Sub13_Sub38.anIntArray3456[var14] = var11;
            boolean var16 = false;
            int var15 = 0;
            int var27 = var14 + 1;
            Class45.anIntArray729[var14] = var3;
            int[][] var17 = AtmosphereParser.aClass91Array1182[WorldListCountry.localPlane].anIntArrayArray1304;

            int var18;
            while(var15 != var27) {
                var12 = Class3_Sub13_Sub38.anIntArray3456[var15];
                var13 = Class45.anIntArray729[var15];
                var15 = 4095 & var15 - -1;
                if(var8 == var12 && var13 == var4) {
                    var16 = true;
                    break;
                }

                if(var1 != 0) {
                    if((5 > var1 || var1 == 10) && AtmosphereParser.aClass91Array1182[WorldListCountry.localPlane].method1488(var4, var12, var13, var8, -1 + var1, 2, var7)) {
                        var16 = true;
                        break;
                    }

                    if(var1 < 10 && AtmosphereParser.aClass91Array1182[WorldListCountry.localPlane].method1492(var4, var1 + -1, var8, var13, 2, var7, var12, 88)) {
                        var16 = true;
                        break;
                    }
                }

                if(0 != var0 && var6 != 0 && AtmosphereParser.aClass91Array1182[WorldListCountry.localPlane].method1498(var8, var13, var12, 2, var0, var2, var4, var6)) {
                    var16 = true;
                    break;
                }

                var18 = Class97.anIntArrayArray1373[var12][var13] - -1;
                if(var12 > 0 && Class84.anIntArrayArray1160[var12 + -1][var13] == 0 && 0 == (var17[-1 + var12][var13] & 19661070) && (var17[-1 + var12][var13 + 1] & 19661112) == 0) {
                    Class3_Sub13_Sub38.anIntArray3456[var27] = var12 + -1;
                    Class45.anIntArray729[var27] = var13;
                    var27 = 1 + var27 & 4095;
                    Class84.anIntArrayArray1160[var12 - 1][var13] = 2;
                    Class97.anIntArrayArray1373[-1 + var12][var13] = var18;
                }

                if(102 > var12 && Class84.anIntArrayArray1160[1 + var12][var13] == 0 && 0 == (var17[2 + var12][var13] & 19661187) && 0 == (var17[var12 + 2][1 + var13] & 19661280)) {
                    Class3_Sub13_Sub38.anIntArray3456[var27] = 1 + var12;
                    Class45.anIntArray729[var27] = var13;
                    var27 = 4095 & var27 + 1;
                    Class84.anIntArrayArray1160[var12 - -1][var13] = 8;
                    Class97.anIntArrayArray1373[var12 - -1][var13] = var18;
                }

                if(var13 > 0 && Class84.anIntArrayArray1160[var12][var13 + -1] == 0 && (19661070 & var17[var12][-1 + var13]) == 0 && (var17[var12 + 1][var13 - 1] & 19661187) == 0) {
                    Class3_Sub13_Sub38.anIntArray3456[var27] = var12;
                    Class45.anIntArray729[var27] = -1 + var13;
                    Class84.anIntArrayArray1160[var12][-1 + var13] = 1;
                    Class97.anIntArrayArray1373[var12][-1 + var13] = var18;
                    var27 = 4095 & 1 + var27;
                }

                if(var13 < 102 && Class84.anIntArrayArray1160[var12][var13 + 1] == 0 && (var17[var12][2 + var13] & 19661112) == 0 && (var17[1 + var12][var13 + 2] & 19661280) == 0) {
                    Class3_Sub13_Sub38.anIntArray3456[var27] = var12;
                    Class45.anIntArray729[var27] = var13 + 1;
                    Class84.anIntArrayArray1160[var12][1 + var13] = 4;
                    var27 = 1 + var27 & 4095;
                    Class97.anIntArrayArray1373[var12][var13 - -1] = var18;
                }

                if(var12 > 0 && var13 > 0 && Class84.anIntArrayArray1160[-1 + var12][-1 + var13] == 0 && (19661112 & var17[-1 + var12][var13]) == 0 && 0 == (var17[-1 + var12][-1 + var13] & 19661070) && 0 == (19661187 & var17[var12][-1 + var13])) {
                    Class3_Sub13_Sub38.anIntArray3456[var27] = -1 + var12;
                    Class45.anIntArray729[var27] = -1 + var13;
                    Class84.anIntArrayArray1160[-1 + var12][-1 + var13] = 3;
                    Class97.anIntArrayArray1373[-1 + var12][-1 + var13] = var18;
                    var27 = 4095 & var27 + 1;
                }

                if(var12 < 102 && 0 < var13 && Class84.anIntArrayArray1160[1 + var12][var13 + -1] == 0 && (var17[var12 - -1][var13 - 1] & 19661070) == 0 && (19661187 & var17[2 + var12][var13 + -1]) == 0 && (19661280 & var17[var12 + 2][var13]) == 0) {
                    Class3_Sub13_Sub38.anIntArray3456[var27] = var12 - -1;
                    Class45.anIntArray729[var27] = var13 - 1;
                    var27 = 4095 & 1 + var27;
                    Class84.anIntArrayArray1160[var12 - -1][-1 + var13] = 9;
                    Class97.anIntArrayArray1373[1 + var12][var13 + -1] = var18;
                }

                if(var12 > 0 && var13 < 102 && 0 == Class84.anIntArrayArray1160[var12 - 1][var13 + 1] && (var17[-1 + var12][var13 - -1] & 19661070) == 0 && 0 == (19661112 & var17[-1 + var12][var13 - -2]) && (19661280 & var17[var12][var13 + 2]) == 0) {
                    Class3_Sub13_Sub38.anIntArray3456[var27] = var12 - 1;
                    Class45.anIntArray729[var27] = 1 + var13;
                    Class84.anIntArrayArray1160[var12 - 1][1 + var13] = 6;
                    Class97.anIntArrayArray1373[-1 + var12][1 + var13] = var18;
                    var27 = 1 + var27 & 4095;
                }

                if(var12 < 102 && var13 < 102 && Class84.anIntArrayArray1160[1 + var12][1 + var13] == 0 && (19661112 & var17[var12 - -1][2 + var13]) == 0 && (19661280 & var17[var12 + 2][var13 - -2]) == 0 && (19661187 & var17[var12 - -2][var13 + 1]) == 0) {
                    Class3_Sub13_Sub38.anIntArray3456[var27] = 1 + var12;
                    Class45.anIntArray729[var27] = var13 + 1;
                    var27 = var27 - -1 & 4095;
                    Class84.anIntArrayArray1160[var12 - -1][var13 + 1] = 12;
                    Class97.anIntArrayArray1373[1 + var12][1 + var13] = var18;
                }
            }

            Class129.anInt1692 = 0;
            int var19;
            if(!var16) {
                if(!var5) {
                    return false;
                }

                var18 = 1000;
                var19 = 100;
                byte var20 = 10;

                for(int var21 = -var20 + var8; var20 + var8 >= var21; ++var21) {
                    for(int var22 = var4 + -var20; var20 + var4 >= var22; ++var22) {
                        if(var21 >= 0 && var22 >= 0 && var21 < 104 && 104 > var22 && Class97.anIntArrayArray1373[var21][var22] < 100) {
                            int var23 = 0;
                            int var24 = 0;
                            if(var8 <= var21) {
                                if(-1 + var0 + var8 < var21) {
                                    var23 = var21 + 1 + -var0 + -var8;
                                }
                            } else {
                                var23 = var8 - var21;
                            }

                            if(var22 >= var4) {
                                if(-1 + var4 + var6 < var22) {
                                    var24 = -var4 + -var6 + 1 + var22;
                                }
                            } else {
                                var24 = -var22 + var4;
                            }

                            int var25 = var23 * var23 + var24 * var24;
                            if(var25 < var18 || var25 == var18 && Class97.anIntArrayArray1373[var21][var22] < var19) {
                                var13 = var22;
                                var19 = Class97.anIntArrayArray1373[var21][var22];
                                var18 = var25;
                                var12 = var21;
                            }
                        }
                    }
                }

                if(var18 == 1000) {
                    return false;
                }

                if(var11 == var12 && var3 == var13) {
                    return false;
                }

                Class129.anInt1692 = 1;
            }

            byte var28 = 0;
            Class3_Sub13_Sub38.anIntArray3456[var28] = var12;
            var15 = var28 + 1;
            Class45.anIntArray729[var28] = var13;
            var18 = var19 = Class84.anIntArrayArray1160[var12][var13];

            for(; var12 != var11 || var13 != var3; var18 = Class84.anIntArrayArray1160[var12][var13]) {
                if(var19 != var18) {
                    Class3_Sub13_Sub38.anIntArray3456[var15] = var12;
                    Class45.anIntArray729[var15++] = var13;
                    var19 = var18;
                }

                if((var18 & 2) != 0) {
                    ++var12;
                } else if((8 & var18) != 0) {
                    --var12;
                }

                if((var18 & 1) == 0) {
                    if((var18 & 4) != 0) {
                        --var13;
                    }
                } else {
                    ++var13;
                }
            }

            if(var15 <= 0) {
                return 1 != var9;
            } else {
                Class3_Sub13_Sub27.method299(113, var15, var9);
                return true;
            }
        } catch (RuntimeException var26) {
            throw ClientErrorException.clientError(var26, "aa.A(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + var9 + ',' + 127 + ',' + var11 + ')');
        }
    }

    public static void method78(int var0, int var2) {
        try {
            Class3_Sub13_Sub1.outgoingBuffer.putOpcode(132);
            Class3_Sub13_Sub1.outgoingBuffer.writeIntV1(var2);
            Class3_Sub13_Sub1.outgoingBuffer.writeShortLE(var0);
        } catch (RuntimeException var4) {
            throw ClientErrorException.clientError(var4, "aa.E(" + var0 + ',' + false + ',' + var2 + ')');
        }
    }

    public static void method81(RSInterface var1) {
        try {

            int var2 = var1.anInt189;
            if(324 == var2) {
                if(-1 == Class3_Sub13_Sub21.anInt3260) {
                    Class3_Sub13_Sub21.anInt3260 = var1.spriteArchiveId;
                    anInt1165 = var1.anInt296;
                }

                if(aClass52_1112.aBoolean864) {
                    var1.spriteArchiveId = Class3_Sub13_Sub21.anInt3260;
                } else {
                    var1.spriteArchiveId = anInt1165;
                }

            } else if (var2 == 325) {
                if (-1 == Class3_Sub13_Sub21.anInt3260) {
                    anInt1165 = var1.anInt296;
                    Class3_Sub13_Sub21.anInt3260 = var1.spriteArchiveId;
                }

                if (aClass52_1112.aBoolean864) {
                    var1.spriteArchiveId = anInt1165;
                } else {
                    var1.spriteArchiveId = Class3_Sub13_Sub21.anInt3260;
                }

            } else if (var2 == 327) {
                var1.anInt182 = 150;
                var1.anInt308 = 2047 & (int) (Math.sin((double) Class44.anInt719 / 40.0D) * 256.0D);
                var1.modelType = 5;
                var1.itemId = -1;
            } else if (var2 == 328) {
                if (null == Class102.player.displayName) {
                    var1.itemId = 0;
                } else {
                    var1.anInt182 = 150;
                    var1.anInt308 = 2047 & (int) (256.0D * Math.sin((double) Class44.anInt719 / 40.0D));
                    var1.modelType = 5;
                    var1.itemId = 2047 + ((int) Class102.player.displayName.toLong() << 11);
                    var1.anInt260 = Class102.player.anInt2793;
                    var1.anInt267 = 0;
                    var1.animationId = Class102.player.anInt2764;
                    var1.anInt283 = Class102.player.anInt2813;
                }
            }
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "aa.H(" + (byte) -128 + ',' + (var1 != null?"{...}":"null") + ')');
        }
    }

    public static boolean method1088(boolean var0) {
        try {
            if (aBoolean3641) {
                try {
                    TextCore.aClass94_106.method1577(Class38.aClass87_665.applet);
                    return true;
                } catch (Throwable var2) {
                }
            }

            if (var0) {
                aReferenceCache_743 = (ReferenceCache) null;
            }

            return false;
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "gn.K(" + var0 + ')');
        }
    }

    public static Class3_Sub28_Sub5 method1089(CacheIndex cacheIndex0, CacheIndex var2, int frameId) {
        try {
            boolean var5 = true;
            int[] var6 = cacheIndex0.getFileIds(frameId);

            for (int var7 = 0; var7 < Objects.requireNonNull(var6).length; ++var7) {
                byte[] var8 = cacheIndex0.method2140(var6[var7], frameId);
                if (var8 == null) {
                    var5 = false;
                } else {
                    int var9 = (255 & var8[0]) << 8 | var8[1] & 255;
                    byte[] var10;
                    var10 = var2.method2140(0, var9);

                    if (null == var10) {
                        // System.out.println("Roar , " + var9);
                        var5 = false;
                    }
                }
            }
            if (var5) {
                try {
                    return new Class3_Sub28_Sub5(cacheIndex0, var2, frameId);
                } catch (Exception var11) {
                    var11.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        } catch (RuntimeException var12) {
            throw ClientErrorException.clientError(var12, "gn.J(" + (cacheIndex0 != null ? "{...}" : "null") + ',' + false + ',' + (var2 != null ? "{...}" : "null") + ',' + (byte) 118 + ',' + frameId + ')');
        }
    }

    public static RSString emptyString(int capacit) {
        RSString str = new RSString();
        str.length = 0;
        str.buffer = new byte[capacit];
        return str;
    }

    public static void method1091(boolean var0, int var1) {
        try {
            byte var2;
            byte[][] var3;
            if (HDToolKit.highDetail && var0) {
                var2 = 1;
                var3 = Class40.aByteArrayArray3669;
            } else {
                var2 = 4;
                var3 = Class164_Sub2.aByteArrayArray3027;
            }

            int var4 = var3.length;

            int var5;
            int var6;
            int var7;
            byte[] var8;
            for (var5 = 0; var5 < var4; ++var5) {
                var6 = -Class131.anInt1716 + 64 * (Class3_Sub24_Sub3.anIntArray3494[var5] >> 8);
                var7 = -Class82.anInt1152 + 64 * (255 & Class3_Sub24_Sub3.anIntArray3494[var5]);
                var8 = var3[var5];
                if (null != var8) {
                    Class58.method1194();
                    method777(AtmosphereParser.aClass91Array1182, var0, -48 + 8 * anInt3606, var7, var6, (Class3_Sub7.anInt2294 + -6) * 8, var8);
                }
            }

            var5 = 0;
            if (var1 > -66) {
                method1088(true);
            }

            for (; var5 < var4; ++var5) {
                var6 = -Class131.anInt1716 + 64 * (Class3_Sub24_Sub3.anIntArray3494[var5] >> 8);
                var7 = -Class82.anInt1152 + 64 * (255 & Class3_Sub24_Sub3.anIntArray3494[var5]);
                var8 = var3[var5];
                if (var8 == null && Class3_Sub7.anInt2294 < 800) {
                    Class58.method1194();

                    for (int var9 = 0; var9 < var2; ++var9) {
                        Class12.method870(var9, (byte) 102, var7, var6, 64, 64);
                    }
                }
            }

        } catch (RuntimeException var10) {
            throw ClientErrorException.clientError(var10, "gn.I(" + var0 + ',' + var1 + ')');
        }
    }

    public static void method1093(boolean var0) {
        try {
            if (var0) {
                method1093(true);
            }

            for (int var1 = 0; var1 < 100; ++var1) {
                aBooleanArray3674[var1] = true;
            }

        } catch (RuntimeException var2) {
            throw ClientErrorException.clientError(var2, "gn.H(" + var0 + ')');
        }
    }

    public static void method1095(int var0, int var1, int var2, RSInterface[] var3, int var4, int var5, int var6, int var7, byte var8, int var9) {
        try {
            if (HDToolKit.highDetail) {
                Class22.setClipping(var0, var6, var4, var7);
            } else {
                Class74.setClipping(var0, var6, var4, var7);
                Class51.method1134();
            }

            for (RSInterface var11 : var3) {
                if (var11 != null && (var5 == var11.parentId || var5 == -1412584499 && var11 == Class56.aClass11_886)) {
                    int var12;
                    if (var9 == -1) {
                        Class155.anIntArray1969[Class3_Sub28_Sub3.anInt3557] = var2 + var11.anInt306;
                        Player.anIntArray3954[Class3_Sub28_Sub3.anInt3557] = var11.anInt210 - -var1;
                        Class3_Sub28_Sub18.anIntArray3768[Class3_Sub28_Sub3.anInt3557] = var11.width;
                        Class140_Sub4.anIntArray2794[Class3_Sub28_Sub3.anInt3557] = var11.height;
                        var12 = Class3_Sub28_Sub3.anInt3557++;
                    } else {
                        var12 = var9;
                    }

                    var11.anInt204 = Class44.anInt719;
                    var11.anInt292 = var12;
                    if (!var11.usingScripts || !Client.method51(var11)) {
                        if (0 < var11.anInt189) {
                            method81(var11);
                        }

                        int var14 = var1 + var11.anInt210;
                        int var15 = var11.anInt223;
                        int var13 = var11.anInt306 + var2;
                        if (ClientCommands.commandQaOpEnabled && (0 != Client.method44(var11).anInt2205 || var11.type == 0) && var15 > 127) {
                            var15 = 127;
                        }

                        int var17;
                        int var16;
                        if (var11 == Class56.aClass11_886) {
                            if (var5 != -1412584499 && !var11.aBoolean200) {
                                anInt3602 = var2;
                                anInt1082 = var1;
                                Class8.aClass11Array1836 = var3;
                                continue;
                            }

                            if (NPC.aBoolean3975 && Class85.aBoolean1167) {
                                var17 = anInt1709;
                                var16 = Class126.anInt1676;
                                var17 -= Class95.anInt1336;
                                if (var17 < Class134.anInt1761) {
                                    var17 = Class134.anInt1761;
                                }

                                if (PacketParser.aClass11_88.height + Class134.anInt1761 < var17 + var11.height) {
                                    var17 = -var11.height + PacketParser.aClass11_88.height + Class134.anInt1761;
                                }

                                var14 = var17;
                                var16 -= anInt1881;
                                if (Class3_Sub13_Sub13.anInt3156 > var16) {
                                    var16 = Class3_Sub13_Sub13.anInt3156;
                                }

                                if (var11.width + var16 > PacketParser.aClass11_88.width + Class3_Sub13_Sub13.anInt3156) {
                                    var16 = -var11.width + PacketParser.aClass11_88.width + Class3_Sub13_Sub13.anInt3156;
                                }

                                var13 = var16;
                            }

                            if (!var11.aBoolean200) {
                                var15 = 128;
                            }
                        }

                        int var19;
                        int var18;
                        int var21;
                        int var20;
                        if (2 == var11.type) {
                            var19 = var7;
                            var18 = var4;
                            var17 = var6;
                            var16 = var0;
                        } else {
                            var17 = var6 < var14 ? var14 : var6;
                            var16 = var13 > var0 ? var13 : var0;
                            var20 = var11.width + var13;
                            var21 = var14 - -var11.height;
                            if (var11.type == 9) {
                                ++var21;
                                ++var20;
                            }

                            var19 = var7 <= var21 ? var7 : var21;
                            var18 = var4 <= var20 ? var4 : var20;
                        }

                        if (!var11.usingScripts || var16 < var18 && var19 > var17) {
                            int var23;
                            int var22;
                            int var25;
                            int var24;
                            int var26;
                            int var29;
                            int var28;
                            int var47;
                            if (var11.anInt189 != 0) {
                                if (var11.anInt189 == 1337 || var11.anInt189 == 1403 && HDToolKit.highDetail) {
                                    Class168.aClass11_2091 = var11;
                                    anInt2567 = var14;
                                    Class53.anInt865 = var13;
                                    Class3_Sub13_Sub36.method338(var11.height, var11.anInt189 == 1403, var13, var11.width, var14);
                                    if (HDToolKit.highDetail) {
                                        Class22.setClipping(var0, var6, var4, var7);
                                    } else {
                                        Class74.setClipping(var0, var6, var4, var7);
                                    }
                                    continue;
                                }

                                if (var11.anInt189 == 1338) {
                                    if (!var11.method855()) {
                                        continue;
                                    }

                                    Class3_Sub8.displayMinimap(var12, (byte) 59, var14, var13, var11);
                                    if (HDToolKit.highDetail) {
                                        Class22.setClipping(var0, var6, var4, var7);
                                    } else {
                                        Class74.setClipping(var0, var6, var4, var7);
                                    }

                                    if (0 != Class161.anInt2028 && 3 != Class161.anInt2028 || Class38_Sub1.aBoolean2615 || var16 > NPCDefinition.anInt1297 || var17 > Class38_Sub1.anInt2612 || var18 <= NPCDefinition.anInt1297 || Class38_Sub1.anInt2612 >= var19) {
                                        continue;
                                    }

                                    var20 = NPCDefinition.anInt1297 - var13;
                                    var21 = -var14 + Class38_Sub1.anInt2612;
                                    var22 = var11.anIntArray207[var21];
                                    if (var22 > var20 || var22 + var11.anIntArray291[var21] < var20) {
                                        continue;
                                    }

                                    var21 -= var11.height / 2;
                                    var23 = 2047 & GraphicDefinition.CAMERA_DIRECTION - -Class3_Sub13_Sub8.anInt3102;
                                    var20 -= var11.width / 2;
                                    var24 = Class51.anIntArray840[var23];
                                    var25 = Class51.anIntArray851[var23];
                                    var24 = (Class164_Sub2.anInt3020 + 256) * var24 >> 8;
                                    var25 = (Class164_Sub2.anInt3020 - -256) * var25 >> 8;
                                    var47 = -(var24 * var20) + var25 * var21 >> 11;
                                    var26 = var21 * var24 - -(var20 * var25) >> 11;
                                    var28 = Class102.player.anInt2819 + var26 >> 7;
                                    var29 = -var47 + Class102.player.anInt2829 >> 7;
                                    if (GameObject.aBoolean1837 && 0 != (Class164.anInt2051 & 64)) {
                                        RSInterface var53 = AbstractSprite.method638(Class3_Sub30_Sub1.anInt872, RSInterface.anInt278);
                                        if (null == var53) {
                                            Class25.method958((byte) -91);
                                        } else {
                                            Class3_Sub24_Sub4.method1177(anInt1887, 1L, (byte) -49, TextCore.aClass94_1724, var28, (short) 11, Class3_Sub28_Sub9.aClass94_3621, var29);
                                        }
                                        continue;
                                    }

                                    if (Class158.anInt2014 == 1) {
                                        Class3_Sub24_Sub4.method1177(-1, 1L, (byte) -41, TextCore.aClass94_3672, var28, (short) 36, TextCore.HasFaceHere, var29);
                                    }

                                    Class3_Sub24_Sub4.method1177(-1, 1L, (byte) -125, TextCore.aClass94_3672, var28, (short) 60, Class3_Sub13_Sub28.aClass94_3353, var29);
                                    continue;
                                }

                                if (var11.anInt189 == 1339) {
                                    if (var11.method855()) {
                                        Class91.drawCompass(var13, var14, var11, var12);
                                        if (HDToolKit.highDetail) {
                                            Class22.setClipping(var0, var6, var4, var7);
                                        } else {
                                            Class74.setClipping(var0, var6, var4, var7);
                                        }
                                    }
                                    continue;
                                }

                                if (var11.anInt189 == 1400) {
                                    WorldMap.drawWorldMap(var13, var14, var11.height, var11.width);
                                    aBooleanArray3674[var12] = true;
                                    Class163_Sub1_Sub1.aBooleanArray4008[var12] = true;
                                    if (HDToolKit.highDetail) {
                                        Class22.setClipping(var0, var6, var4, var7);
                                    } else {
                                        Class74.setClipping(var0, var6, var4, var7);
                                    }
                                    continue;
                                }

                                if (var11.anInt189 == 1401) {
                                    Class1.worldMapMinimap(var13, var11.height, var11.width, var14);
                                    aBooleanArray3674[var12] = true;
                                    Class163_Sub1_Sub1.aBooleanArray4008[var12] = true;
                                    if (HDToolKit.highDetail) {
                                        Class22.setClipping(var0, var6, var4, var7);
                                    } else {
                                        Class74.setClipping(var0, var6, var4, var7);
                                    }
                                    continue;
                                }

                                if (1402 == var11.anInt189) {
                                    if (!HDToolKit.highDetail) {
                                        Class129.method1768(var13, var14);
                                        aBooleanArray3674[var12] = true;
                                        Class163_Sub1_Sub1.aBooleanArray4008[var12] = true;
                                    }
                                    continue;
                                }

                                if (var11.anInt189 == 1405) {
                                    if (ClientCommands.fpsOverlayEnabled) {
                                        var20 = var11.width + var13;
                                        var21 = 15 + var14;
                                        Class126.aClass3_Sub28_Sub17_1669.method688(RSString.stringCombiner(new RSString[]{TextCore.aClass94_3196, RSString.stringAnimator(AnimationDefinition.anInt1862)}), var20, var21, 16776960, -1);//Class72.stringAnimator(AnimationDefinition.anInt1862)}), var20, var21, 16776960, -1);
                                        var21 += 15;
                                        Runtime var57 = Runtime.getRuntime();
                                        var23 = (int) ((var57.totalMemory() + -var57.freeMemory()) / 1024L);
                                        var24 = 16776960;
                                        if (var23 > 65536) {
                                            var24 = 16711680;
                                        }

                                        Class126.aClass3_Sub28_Sub17_1669.method688(RSString.stringCombiner(new RSString[]{TextCore.aClass94_4057, RSString.stringAnimator(var23), TextCore.Memoryk}), var20, var21, var24, -1);
                                        var21 += 15;
                                        if (HDToolKit.highDetail) {
                                            var24 = 16776960;
                                            var25 = (Class31.anInt580 + Class31.anInt585 + Class31.memory2D) / 1024;
                                            if (65536 < var25) {
                                                var24 = 16711680;
                                            }

                                            Class126.aClass3_Sub28_Sub17_1669.method688(RSString.stringCombiner(new RSString[]{TextCore.aClass94_1622, RSString.stringAnimator(var25), TextCore.Memoryk}), var20, var21, var24, -1);
                                            var21 += 15;
                                        }

                                        var24 = 16776960;
                                        var25 = 0;
                                        var47 = 0;
                                        var26 = 0;

                                        for (var28 = 0; var28 < 29; ++var28) { //TODO:
                                            var25 += aClass151_Sub1Array2601[var28].method2108();
                                            var26 += aClass151_Sub1Array2601[var28].method2102();
                                            var47 += aClass151_Sub1Array2601[var28].method2106();
                                        }

                                        var29 = 10000 * var26 / var25;
                                        var28 = var47 * 100 / var25;
                                        RSString var55 = RSString.stringCombiner(new RSString[]{TextCore.aClass94_436, Class3_Sub23.method407(0, true, 2, (long) var29), TextCore.aClass94_2498, RSString.stringAnimator(var28), TextCore.aClass94_148});
                                        Class3_Sub13.aClass3_Sub28_Sub17_2379.method688(var55, var20, var21, var24, -1);
                                        aBooleanArray3674[var12] = true;
                                        Class163_Sub1_Sub1.aBooleanArray4008[var12] = true;
                                        continue;
                                    }
                                    if (ClientCommands.renderInfoOverlayEnabled) {
                                        if (HDToolKit.highDetail) {
                                            int vendorColor;
                                            String vendor = HDToolKit.gl.glGetString(GL.GL_VENDOR);
                                            if (vendor.contains("NVIDIA")) {
                                                vendorColor = 7780608;
                                            } else if (vendor.contains("AMD")) {
                                                vendorColor = 15539236;
                                            } else if (vendor.contains("INTEL")) {
                                                vendorColor = 29125;
                                            } else {
                                                vendorColor = 16776960;
                                            }
                                            var20 = var11.width + var13;
                                            var21 = 15 + var14;
                                            Class126.aClass3_Sub28_Sub17_1669.method688(RSString.parse(JoglVersion.getInstance().getImplementationVendor()), var20, var21, 16776960, 7);
                                            var21 += 15;
                                            Class126.aClass3_Sub28_Sub17_1669.method688(RSString.parse(HDToolKit.gl.glGetString(GL.GL_VERSION)), var20, var21, 16776960, 7);
                                            var21 += 15;
                                            Class126.aClass3_Sub28_Sub17_1669.method688(RSString.parse(vendor), var20, var21, vendorColor, 4);
                                            var21 += 15;
                                            Class126.aClass3_Sub28_Sub17_1669.method688(RSString.parse(HDToolKit.gl.glGetString(GL.GL_RENDERER)), var20, var21, 16776960, 7);
                                            var21 += 15;
                                            Class126.aClass3_Sub28_Sub17_1669.method688(RSString.parse("Oncard Geometry: " + Class31.anInt585), var20, var21, 16776960, 7);
                                            var21 += 15;
                                            Class126.aClass3_Sub28_Sub17_1669.method688(RSString.parse("Oncard 2D: " + Class31.memory2D), var20, var21, 16776960, 7);
                                            var21 += 15;
                                            Class126.aClass3_Sub28_Sub17_1669.method688(RSString.parse("Oncard Texture: " + Class31.anInt580), var20, var21, 16776960, 7);
                                        } else {
                                            var20 = var11.width + var13;
                                            var21 = 15 + var14;
                                            Class126.aClass3_Sub28_Sub17_1669.method688(RSString.parse("Java Toolkit"), var20, var21, 16776960, 7);
                                            var21 += 15;
                                            Class126.aClass3_Sub28_Sub17_1669.method688(RSString.parse(System.getProperty("java.vendor")), var20, var21, 16776960, 7);
                                            var21 += 15;
                                            Class126.aClass3_Sub28_Sub17_1669.method688(RSString.parse(System.getProperty("java.version")), var20, var21, 16776960, 7);
                                        }
                                        continue;
                                    }
                                    continue;
                                }

                                if (var11.anInt189 == 1406) {
                                    ClientErrorException.anInt2115 = var14;
                                    AbstractSprite.aClass11_3708 = var11;
                                    Class3_Sub13_Sub23_Sub1.anInt4041 = var13;
                                    continue;
                                }
                            }

                            if (!Class38_Sub1.aBoolean2615) {
                                if (var11.type == 0 && var11.aBoolean219 && NPCDefinition.anInt1297 >= var16 && var17 <= Class38_Sub1.anInt2612 && NPCDefinition.anInt1297 < var18 && Class38_Sub1.anInt2612 < var19 && !ClientCommands.commandQaOpEnabled) {
                                    menuOptionCount = 1;
                                    Class114.anIntArray1578[0] = Class3_Sub28_Sub5.anInt3590;
                                    Class140_Sub7.aClass94Array2935[0] = TextCore.HasCancel;
                                    Class163_Sub2_Sub1.aClass94Array4016[0] = TextCore.aClass94_3672;
                                    Class3_Sub13_Sub7.aShortArray3095[0] = 1005;
                                }

                                if (var16 <= NPCDefinition.anInt1297 && Class38_Sub1.anInt2612 >= var17 && var18 > NPCDefinition.anInt1297 && Class38_Sub1.anInt2612 < var19) {
                                    Class3_Sub24_Sub4.method477(Class38_Sub1.anInt2612 + -var14, -var13 + NPCDefinition.anInt1297, var11);
                                }
                            }

                            if (var11.type == 0) {
                                if (!var11.usingScripts && Client.method51(var11) && Class107.aClass11_1453 != var11) {
                                    continue;
                                }

                                if (!var11.usingScripts) {
                                    if (var11.anInt208 > -var11.height + var11.anInt252) {
                                        var11.anInt208 = -var11.height + var11.anInt252;
                                    }

                                    if (0 > var11.anInt208) {
                                        var11.anInt208 = 0;
                                    }
                                }

                                method1095(var16, -var11.anInt208 + var14, -var11.anInt247 + var13, var3, var18, var11.componentHash, var17, var19, (byte) 87, var12);
                                if (null != var11.aClass11Array262) {
                                    method1095(var16, -var11.anInt208 + var14, -var11.anInt247 + var13, var11.aClass11Array262, var18, var11.componentHash, var17, var19, (byte) 52, var12);
                                }

                                Class3_Sub31 var36 = (Class3_Sub31) Class3_Sub13_Sub17.aHashTable_3208.get((long) var11.componentHash);
                                if (var36 != null) {
                                    if (var36.anInt2603 == 0 && !Class38_Sub1.aBoolean2615 && NPCDefinition.anInt1297 >= var16 && Class38_Sub1.anInt2612 >= var17 && NPCDefinition.anInt1297 < var18 && Class38_Sub1.anInt2612 < var19 && !ClientCommands.commandQaOpEnabled) {
                                        Class140_Sub7.aClass94Array2935[0] = TextCore.HasCancel;
                                        menuOptionCount = 1;
                                        Class114.anIntArray1578[0] = Class3_Sub28_Sub5.anInt3590;
                                        Class3_Sub13_Sub7.aShortArray3095[0] = 1005;
                                        Class163_Sub2_Sub1.aClass94Array4016[0] = TextCore.aClass94_3672;
                                    }

                                    Class3_Sub13_Sub1.method171(-101, var36.anInt2602, var16, var18, var13, var12, var19, var17, var14);
                                }

                                if (HDToolKit.highDetail) {
                                    Class22.setClipping(var0, var6, var4, var7);
                                } else {
                                    Class74.setClipping(var0, var6, var4, var7);
                                    Class51.method1134();
                                }
                            }

                            if (aBooleanArray1712[var12] || Client.rectDebugInt > 1) {
                                if (var11.type == 0 && !var11.usingScripts && var11.anInt252 > var11.height) {
                                    Class3_Sub13_Sub12.method224(var11.anInt208, var11.anInt252, var11.width + var13, var14, var11.height);
                                }

                                if (var11.type != 1) {
                                    boolean var39;
                                    boolean var46;
                                    if (var11.type == 2) {
                                        var20 = 0;

                                        for (var21 = 0; var11.defHeight > var21; ++var21) {
                                            for (var22 = 0; var11.defWidth > var22; ++var22) {
                                                var24 = var14 + var21 * (32 - -var11.anInt290);
                                                var23 = (var11.anInt285 + 32) * var22 + var13;
                                                if (var20 < 20) {
                                                    var24 += var11.anIntArray300[var20];
                                                    var23 += var11.anIntArray272[var20];
                                                }

                                                if (var11.itemAmounts[var20] <= 0) {
                                                    if (null != var11.anIntArray197 && var20 < 20) {
                                                        AbstractSprite var58 = var11.method859(var20);
                                                        if (null == var58) {
                                                            if (GameShell.aBoolean6) {
                                                                Class20.method909(var11);
                                                            }
                                                        } else {
                                                            var58.method643(var23, var24);
                                                        }
                                                    }
                                                } else {
                                                    var47 = var11.itemAmounts[var20] + -1;
                                                    if (var0 < 32 + var23 && var4 > var23 && var24 - -32 > var6 && var7 > var24 || var11 == Class67.aClass11_1017 && var20 == PacketParser.anInt86) {
                                                        AbstractSprite var54;
                                                        if (Class164_Sub1.anInt3012 == 1 && Class3_Sub30_Sub1.anInt1473 == var20 && Class3_Sub28_Sub18.anInt3764 == var11.componentHash) {
                                                            var54 = Class114.method1707(2, var47, var11.aBoolean227, var11.itemIds[var20], 0);
                                                        } else {
                                                            var54 = Class114.method1707(1, var47, var11.aBoolean227, var11.itemIds[var20], 3153952);
                                                        }

                                                        if (Class51.aBoolean837) {
                                                            aBooleanArray3674[var12] = true;
                                                        }

                                                        if (null == var54) {
                                                            Class20.method909(var11);
                                                        } else if (Class67.aClass11_1017 == var11 && var20 == PacketParser.anInt86) {
                                                            var25 = Class126.anInt1676 - Class129_Sub1.anInt2693;
                                                            var26 = -anInt40 + anInt1709;
                                                            if (var26 < 5 && var26 > -5) {
                                                                var26 = 0;
                                                            }

                                                            if (var25 < 5 && var25 > -5) {
                                                                var25 = 0;
                                                            }

                                                            if (5 > Class40.anInt677) {
                                                                var25 = 0;
                                                                var26 = 0;
                                                            }

                                                            var54.method637(var23 + var25, var24 - -var26, 128);
                                                            if (var5 != -1) {
                                                                RSInterface var51 = var3[var5 & '\uffff'];
                                                                int var31;
                                                                int var30;
                                                                if (HDToolKit.highDetail) {
                                                                    var31 = Class22.anInt451;
                                                                    var30 = Class22.anInt448;
                                                                } else {
                                                                    var30 = Toolkit.JAVA_TOOLKIT.clipTop;
                                                                    var31 = Toolkit.JAVA_TOOLKIT.clipBottom;
                                                                }

                                                                int var32;
                                                                if (var26 + var24 < var30 && var51.anInt208 > 0) {
                                                                    var32 = Class106.anInt1446 * (-var26 + var30 + -var24) / 3;
                                                                    if (Class106.anInt1446 * 10 < var32) {
                                                                        var32 = 10 * Class106.anInt1446;
                                                                    }

                                                                    if (var32 > var51.anInt208) {
                                                                        var32 = var51.anInt208;
                                                                    }

                                                                    var51.anInt208 -= var32;
                                                                    anInt40 += var32;
                                                                    Class20.method909(var51);
                                                                }

                                                                if (var31 < 32 + var26 + var24 && var51.anInt208 < -var51.height + var51.anInt252) {
                                                                    var32 = (-var31 + 32 + (var24 - -var26)) * Class106.anInt1446 / 3;
                                                                    if (var32 > Class106.anInt1446 * 10) {
                                                                        var32 = 10 * Class106.anInt1446;
                                                                    }

                                                                    if (-var51.anInt208 + var51.anInt252 + -var51.height < var32) {
                                                                        var32 = var51.anInt252 + -var51.height + -var51.anInt208;
                                                                    }

                                                                    var51.anInt208 += var32;
                                                                    anInt40 -= var32;
                                                                    Class20.method909(var51);
                                                                }
                                                            }
                                                        } else if (var11 == aClass11_1933 && var20 == KeyboardListener.anInt1918) {
                                                            var54.method637(var23, var24, 128);
                                                        } else {
                                                            var54.method643(var23, var24);
                                                        }
                                                    }
                                                }

                                                ++var20;
                                            }
                                        }
                                    } else if (3 == var11.type) {
                                        // TODO DRAWRECT
                                        if (Class3_Sub28_Sub12.method609(var11, 26)) {
                                            var20 = var11.anInt253;
                                            if (Class107.aClass11_1453 == var11 && 0 != var11.anInt222) {
                                                var20 = var11.anInt222;
                                            }
                                        } else {
                                            var20 = var11.anInt218;
                                            if (var11 == Class107.aClass11_1453 && 0 != var11.anInt228) {
                                                var20 = var11.anInt228;
                                            }
                                        }

                                        if (var15 != 0) {
                                            if (var11.aBoolean226) {
                                                Toolkit.getActiveToolkit().fillRect(var13, var14, var11.width, var11.height, var20, 256 - (var15 & 255));//Background for mage book
                                            } else if (HDToolKit.highDetail) {
                                                Class22.method928(var13, var14, var11.width, var11.height, var20, 256 + -(var15 & 255));
                                            } else {
                                                Class74.method1315(var13, var14, var11.width, var11.height, var20, -(var15 & 255) + 256);
                                            }
                                        } else if (var11.aBoolean226) {
                                            Toolkit.getActiveToolkit().method934(var13, var14, var11.width, var11.height, var20);//Fill Rectangle for skill hover
                                        } else {
                                            Toolkit.getActiveToolkit().drawRect(var13, var14, var11.width, var11.height, var20, 255);//Background border for mage book
                                        }

                                    } else {
                                        Class3_Sub28_Sub17 var34;
                                        if (var11.type == 4) {
                                            // TODO RSInterfaceRenderText
                                            var34 = var11.method868(Class3_Sub13_Sub22.aClass109Array3270);
                                            if (var34 != null) {
                                                RSString var45 = var11.text;
                                                if (Class3_Sub28_Sub12.method609(var11, 97)) {
                                                    var21 = var11.anInt253;
                                                    if (Class107.aClass11_1453 == var11 && var11.anInt222 != 0) {
                                                        var21 = var11.anInt222;
                                                    }

                                                    if (var11.aClass94_172.length() > 0) {
                                                        var45 = var11.aClass94_172;
                                                    }
                                                } else {
                                                    var21 = var11.anInt218;
                                                    if (Class107.aClass11_1453 == var11 && var11.anInt228 != 0) {
                                                        var21 = var11.anInt228;
                                                    }
                                                }

                                                if (var11.usingScripts && var11.anInt192 != -1) {
                                                    ItemDefinition var50 = Class38.getItemDefinition(var11.anInt192);
                                                    var45 = var50.name;
                                                    if (var45 == null) {
                                                        var45 = TextCore.HasNull;
                                                    }

                                                    if ((var50.stackingType == 1 || var11.anInt271 != 1) && var11.anInt271 != -1) {
                                                        var45 = RSString.stringCombiner(new RSString[]{ColorCore.ItemInterfaceColor, var45, TextCore.aClass94_2306, Class36.method1013((byte) -125, var11.anInt271)});
                                                    }
                                                }

                                                if (Class3_Sub13_Sub7.aClass11_3087 == var11) {
                                                    var21 = var11.anInt218;
                                                    var45 = TextCore.LoadingPleaseWait;
                                                }

                                                if (!var11.usingScripts) {
                                                    var45 = method1303(var11, var45);
                                                }

                                                var34.method676(var45, var13, var14, var11.width, var11.height, var21, !var11.aBoolean215 ? -1 : 0, var11.anInt194, var11.anInt225, var11.anInt205);
                                            } else if (GameShell.aBoolean6) {
                                                Class20.method909(var11);
                                            }
                                        } else if (5 == var11.type) {
                                            AbstractSprite var37;
                                            if (var11.usingScripts) {
                                                if (var11.anInt192 == -1) {
                                                    var37 = var11.method866(false);
                                                } else {
                                                    var37 = Class114.method1707(var11.anInt288, var11.anInt192, var11.aBoolean227, var11.anInt271, var11.anInt287);
                                                }

                                                if (var37 == null) {
                                                    if (GameShell.aBoolean6) {
                                                        Class20.method909(var11);
                                                    }
                                                } else {
                                                    var21 = var37.anInt3697;
                                                    var22 = var37.anInt3706;
                                                    if (var11.aBoolean186) {
                                                        var23 = (var21 + -1 + var11.width) / var21;
                                                        var24 = (var11.height - 1 - -var22) / var22;
                                                        if (HDToolKit.highDetail) {
                                                            Class22.method931(var13, var14, var11.width + var13, var11.height + var14);
                                                            var39 = Class140_Sub6.method2021((byte) -94, var37.anInt3707);
                                                            var46 = Class140_Sub6.method2021((byte) -113, var37.anInt3696);
                                                            HDSprite var27 = (HDSprite) var37;
                                                            if (var39 && var46) {
                                                                if (var15 == 0) {
                                                                    var27.method649(var13, var14, var23, var24);
                                                                } else {
                                                                    var27.method646(var13, var14, -(255 & var15) + 256, var23, var24);
                                                                }
                                                            } else if (var39) {
                                                                for (var28 = 0; var28 < var24; ++var28) {
                                                                    if (var15 == 0) {
                                                                        var27.method649(var13, var28 * var22 + var14, var23, 1);
                                                                    } else {
                                                                        var27.method646(var13, var14 + var28 * var22, 256 + -(var15 & 255), var23, 1);
                                                                    }
                                                                }
                                                            } else if (var46) {
                                                                for (var28 = 0; var28 < var23; ++var28) {
                                                                    if (var15 == 0) {
                                                                        var27.method649(var21 * var28 + var13, var14, 1, var24);
                                                                    } else {
                                                                        var27.method646(var21 * var28 + var13, var14, -(var15 & 255) + 256, 1, var24);
                                                                    }
                                                                }
                                                            } else {
                                                                for (var28 = 0; var23 > var28; ++var28) {
                                                                    for (var29 = 0; var29 < var24; ++var29) {
                                                                        if (var15 == 0) {
                                                                            var37.method643(var13 - -(var21 * var28), var22 * var29 + var14);
                                                                        } else {
                                                                            var37.method637(var28 * var21 + var13, var22 * var29 + var14, -(255 & var15) + 256);
                                                                        }
                                                                    }
                                                                }
                                                            }

                                                            Class22.setClipping(var0, var6, var4, var7);
                                                        } else {
                                                            Class74.method1326(var13, var14, var13 - -var11.width, var14 - -var11.height);

                                                            for (var25 = 0; var25 < var23; ++var25) {
                                                                for (var26 = 0; var24 > var26; ++var26) {
                                                                    if (var11.anInt301 == 0) {
                                                                        if (0 == var15) {
                                                                            var37.method643(var25 * var21 + var13, var22 * var26 + var14);
                                                                        } else {
                                                                            var37.method637(var25 * var21 + var13, var14 + var26 * var22, 256 - (255 & var15));
                                                                        }
                                                                    } else {
                                                                        var37.method640(var14 - -(var22 * var26) + var22 / 2, var11.anInt301, 4096, var25 * var21 + var13 + var21 / 2);
                                                                    }
                                                                }
                                                            }

                                                            Class74.setClipping(var0, var6, var4, var7);
                                                        }
                                                    } else {
                                                        var23 = var11.width * 4096 / var21;
                                                        if (var11.anInt301 == 0) {
                                                            if (0 != var15) {
                                                                var37.method642(var13, var14, var11.width, var11.height, -(255 & var15) + 256);
                                                            } else if (var11.width == var21 && var11.height == var22) {
                                                                var37.method643(var13, var14);
                                                            } else {
                                                                var37.method639(var13, var14, var11.width, var11.height);
                                                            }
                                                        } else {
                                                            var37.method640(var14 + var11.height / 2, var11.anInt301, var23, var13 + var11.width / 2);
                                                        }
                                                    }
                                                }
                                            } else {
                                                var37 = var11.method866(Class3_Sub28_Sub12.method609(var11, 69));
                                                if (null != var37) {
                                                    var37.method643(var13, var14);
                                                } else if (GameShell.aBoolean6) {
                                                    Class20.method909(var11);
                                                }
                                            }
                                        } else {
                                            ItemDefinition var42;
                                            if (var11.type == 6) {
                                                boolean var41 = Class3_Sub28_Sub12.method609(var11, 110);
                                                Model var38 = null;
                                                if (var41) {
                                                    var21 = var11.secondAnimationId;
                                                } else {
                                                    var21 = var11.animationId;
                                                }

                                                var23 = 0;
                                                if (var11.anInt192 != -1) {
                                                    var42 = Class38.getItemDefinition(var11.anInt192);
                                                    var42 = var42.method1106(var11.anInt271);
                                                    AnimationDefinition var52 = var21 == -1 ? null : Client.getAnimationDefinition(var21);
                                                    var38 = var42.method1110(var11.anInt260, var11.anInt267, var52, 1, var11.anInt283);
                                                    if (var38 == null) {
                                                        Class20.method909(var11);
                                                    } else {
                                                        var23 = -var38.method1871() / 2;
                                                    }
                                                } else if (5 != var11.modelType) {
                                                    if (var21 == -1) {
                                                        var38 = var11.method865(-1, (AnimationDefinition) null, -1, 126, 0, var41, Class102.player.class52);
                                                        if (null == var38 && GameShell.aBoolean6) {
                                                            Class20.method909(var11);
                                                        }
                                                    } else {
                                                        AnimationDefinition var48 = Client.getAnimationDefinition(var21);
                                                        var38 = var11.method865(var11.anInt260, var48, var11.anInt283, 127, var11.anInt267, var41, Class102.player.class52);
                                                        if (null == var38 && GameShell.aBoolean6) {
                                                            Class20.method909(var11);
                                                        }
                                                    }
                                                } else if (-1 == var11.itemId) {
                                                    var38 = aClass52_1112.method1165((Class145[]) null, -1, (AnimationDefinition) null, (AnimationDefinition) null, 0, -1, 100, 0, -1, -1);
                                                } else {
                                                    var24 = 2047 & var11.itemId;
                                                    if (Class3_Sub1.localIndex == var24) {
                                                        var24 = 2047;
                                                    }

                                                    Player var49 = Class3_Sub13_Sub22.players[var24];
                                                    AnimationDefinition var56 = var21 == -1 ? null : Client.getAnimationDefinition(var21);
                                                    if (null != var49 && (-2048 & var11.itemId) == (int) var49.displayName.toLong() << 11) {
                                                        var38 = var49.class52.method1165((Class145[]) null, -1, (AnimationDefinition) null, var56, 0, -1, -126, 0, var11.anInt283, 0);
                                                    }
                                                }

                                                if (var38 != null) {
                                                    if (var11.anInt184 > 0) {
                                                        var24 = (var11.width << 8) / var11.anInt184;
                                                    } else {
                                                        var24 = 256;
                                                    }

                                                    if (var11.anInt312 <= 0) {
                                                        var25 = 256;
                                                    } else {
                                                        var25 = (var11.height << 8) / var11.anInt312;
                                                    }

                                                    var26 = var13 - -(var11.width / 2) - -(var24 * var11.anInt259 >> 8);
                                                    var47 = var11.height / 2 + var14 + (var25 * var11.anInt230 >> 8);
                                                    if (HDToolKit.highDetail) {
                                                        if (var11.aBoolean181) {
                                                            HDToolKit.method1855(var26, var47, var11.anInt164, var11.aShort293, var24, var25);
                                                        } else {
                                                            HDToolKit.method1821(var26, var47, var24, var25);
                                                            HDToolKit.method1825(var11.aShort169, 1.5F * (float) var11.aShort293);
                                                        }

                                                        HDToolKit.method1846();
                                                        HDToolKit.method1831(true);
                                                        HDToolKit.method1827(false);
                                                        Class3_Sub13_Sub33.method324(anInt3625);
                                                        if (aBoolean47) {
                                                            Class22.resetClipping();
                                                            HDToolKit.method1841();
                                                            Class22.setClipping(var0, var6, var4, var7);
                                                            aBoolean47 = false;
                                                        }

                                                        if (var11.aBoolean309) {
                                                            HDToolKit.depthBufferWritingDisabled();
                                                        }

                                                        var28 = Class51.anIntArray840[var11.anInt182] * var11.anInt164 >> 16;
                                                        var29 = var11.anInt164 * Class51.anIntArray851[var11.anInt182] >> 16;
                                                        if (var11.usingScripts) {
                                                            var38.method1893(var11.anInt308, var11.anInt280, var11.anInt182, var11.anInt258, var11.anInt264 + var28 + var23, var11.anInt264 + var29);
                                                        } else {
                                                            var38.method1893(var11.anInt308, 0, var11.anInt182, 0, var28, var29);
                                                        }

                                                        if (var11.aBoolean309) {
                                                            HDToolKit.method1830();
                                                        }
                                                    } else {
                                                        Class51.method1145(var26, var47);
                                                        var28 = Class51.anIntArray840[var11.anInt182] * var11.anInt164 >> 16;
                                                        var29 = var11.anInt164 * Class51.anIntArray851[var11.anInt182] >> 16;
                                                        if (!var11.usingScripts) {
                                                            var38.method1893(var11.anInt308, 0, var11.anInt182, 0, var28, var29);
                                                        } else if (var11.aBoolean181) {
                                                            ((Class140_Sub1_Sub2) var38).method1946(var11.anInt308, var11.anInt280, var11.anInt182, var11.anInt258, var11.anInt264 + var23 + var28, var29 + var11.anInt264, var11.anInt164);
                                                        } else {
                                                            var38.method1893(var11.anInt308, var11.anInt280, var11.anInt182, var11.anInt258, var11.anInt264 + (var28 - -var23), var29 + var11.anInt264);
                                                        }

                                                        Class51.method1141();
                                                    }
                                                }
                                            } else {
                                                if (var11.type == 7) {
                                                    var34 = var11.method868(Class3_Sub13_Sub22.aClass109Array3270);
                                                    if (var34 == null) {
                                                        if (GameShell.aBoolean6) {
                                                            Class20.method909(var11);
                                                        }
                                                        continue;
                                                    }

                                                    var21 = 0;

                                                    for (var22 = 0; var22 < var11.defHeight; ++var22) {
                                                        for (var23 = 0; var23 < var11.defWidth; ++var23) {
                                                            if (0 < var11.itemAmounts[var21]) {
                                                                var42 = Class38.getItemDefinition(var11.itemAmounts[var21] + -1);
                                                                RSString var40;
                                                                if (1 != var42.stackingType && 1 == var11.itemIds[var21]) {
                                                                    var40 = RSString.stringCombiner(new RSString[]{
                                                                            ColorCore.ItemInterfaceColor, var42.name, TextCore.aClass94_2584
                                                                    });
                                                                } else {
                                                                    var40 = RSString.stringCombiner(new RSString[]{
                                                                            ColorCore.ItemInterfaceColor, var42.name, TextCore.aClass94_2306, Class36.method1013((byte) -100, var11.itemIds[var21])
                                                                    });
                                                                }

                                                                var26 = var13 + var23 * (var11.anInt285 + 115);
                                                                var47 = (var11.anInt290 + 12) * var22 + var14;
                                                                if (var11.anInt194 == 0) {
                                                                    var34.method681(var40, var26, var47, var11.anInt218, var11.aBoolean215 ? 0 : -1);
                                                                } else if (var11.anInt194 == 1) {
                                                                    var34.method699(var40, 57 + var26, var47, var11.anInt218, !var11.aBoolean215 ? -1 : 0);
                                                                } else {
                                                                    var34.method688(var40, -1 + var26 + 115, var47, var11.anInt218, !var11.aBoolean215 ? -1 : 0);
                                                                }
                                                            }

                                                            ++var21;
                                                        }
                                                    }
                                                }

                                                if (var11.type == 8 && Class20.aClass11_439 == var11 && Class3_Sub13_Sub26.anInt3323 == Class75.anInt1109) {
                                                    var21 = 0;
                                                    var20 = 0;
                                                    RSString var43 = var11.text;
                                                    Class3_Sub28_Sub17 var35 = Class126.aClass3_Sub28_Sub17_1669;
                                                    var43 = method1303(var11, var43);

                                                    RSString var44;
                                                    while (var43.length() > 0) {
                                                        var25 = var43.indexOf(TextCore.aClass94_2598, 62);
                                                        if (var25 == -1) {
                                                            var44 = var43;
                                                            var43 = TextCore.aClass94_3672;
                                                        } else {
                                                            var44 = var43.substring(0, var25, 0);
                                                            var43 = var43.substring(var25 + 4);
                                                        }

                                                        var26 = var35.method682(var44);
                                                        var21 += var35.anInt3727 - -1;
                                                        if (var26 > var20) {
                                                            var20 = var26;
                                                        }
                                                    }

                                                    var26 = var14 - -var11.height - -5;
                                                    var20 += 6;
                                                    var21 += 7;
                                                    if (var7 < var26 - -var21) {
                                                        var26 = -var21 + var7;
                                                    }

                                                    var25 = -var20 + -5 + var13 - -var11.width;
                                                    if (var25 < 5 + var13) {
                                                        var25 = 5 + var13;
                                                    }

                                                    if (var4 < var20 + var25) {
                                                        var25 = -var20 + var4;
                                                    }

                                                    Toolkit.getActiveToolkit().method934(var25, var26, var20, var21, 16777120);
                                                    Toolkit.getActiveToolkit().drawRect(var25, var26, var20, var21, 0, 255);

                                                    var43 = var11.text;
                                                    var47 = 2 + (var26 - -var35.anInt3727);

                                                    for (var43 = method1303(var11, var43); var43.length() > 0; var47 += var35.anInt3727 + 1) {
                                                        var28 = var43.indexOf(TextCore.aClass94_2598, 86);
                                                        if (var28 == -1) {
                                                            var44 = var43;
                                                            var43 = TextCore.aClass94_3672;
                                                        } else {
                                                            var44 = var43.substring(0, var28, 0);
                                                            var43 = var43.substring(4 + var28);
                                                        }

                                                        var35.method681(var44, 3 + var25, var47, 0, -1);
                                                    }
                                                }

                                                if (var11.type == 9) {
                                                    if (var11.aBoolean167) {
                                                        var20 = var13;
                                                        var22 = var13 - -var11.width;
                                                        var21 = var14 - -var11.height;
                                                        var23 = var14;
                                                    } else {
                                                        var20 = var13;
                                                        var21 = var14;
                                                        var23 = var14 - -var11.height;
                                                        var22 = var13 + var11.width;
                                                    }

                                                    if (var11.anInt250 == 1) {
                                                        if (HDToolKit.highDetail) {
                                                            Class22.method933(var20, var21, var22, var23, var11.anInt218);
                                                        } else {
                                                            Class74.method1328(var20, var21, var22, var23, var11.anInt218);
                                                        }
                                                    } else if (HDToolKit.highDetail) {
                                                        Class22.method929(var20, var21, var22, var23, var11.anInt218, var11.anInt250);
                                                    } else {
                                                        Class74.method1322(var20, var21, var22, var23, var11.anInt218, var11.anInt250);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (var8 <= 31) {
                quickChatMessages = (CacheIndex) null;
            }

        } catch (RuntimeException var33) {
            throw ClientErrorException.clientError(var33, "gn.B(" + var0 + ',' + var1 + ',' + var2 + ',' + (var3 != null ? "{...}" : "null") + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + var9 + ')');
        }
    }

    public static void method1098(byte var0) {
        try {
            if (anInt2309 < 128) {
                anInt2309 = 128;
            }

            if (anInt2309 > 383) {
                anInt2309 = 383;
            }

            GraphicDefinition.CAMERA_DIRECTION &= 2047;
            if (var0 >= -31) {
                quickChatMessages = (CacheIndex) null;
            }

            int var1 = anInt3155 >> 7;
            int var2 = anInt942 >> 7;
            int var3 = Class121.method1736(WorldListCountry.localPlane, 1, anInt3155, anInt942);
            int var4 = 0;
            int var5;
            if (var1 > 3 && 3 < var2 && 100 > var1 && var2 < 100) {
                for (var5 = -4 + var1; var1 - -4 >= var5; ++var5) {
                    for (int var6 = -4 + var2; var6 <= 4 + var2; ++var6) {
                        int var7 = WorldListCountry.localPlane;
                        if (3 > var7 && 2 == (2 & aByteArrayArrayArray113[1][var5][var6])) {
                            ++var7;
                        }

                        int var8 = (255 & aByteArrayArrayArray1774[var7][var5][var6]) * 8 - Class44.anIntArrayArrayArray723[var7][var5][var6] + var3;
                        if (var8 > var4) {
                            var4 = var8;
                        }
                    }
                }
            }

            var5 = 192 * var4;
            if (var5 > 98048) {
                var5 = 98048;
            }

            if (var5 < 32768) {
                var5 = '\u8000';
            }

            if (var5 <= Class75_Sub4.anInt2670) {
                if (Class75_Sub4.anInt2670 > var5) {
                    Class75_Sub4.anInt2670 += (var5 - Class75_Sub4.anInt2670) / 80;
                }
            } else {
                Class75_Sub4.anInt2670 += (-Class75_Sub4.anInt2670 + var5) / 24;
            }

        } catch (RuntimeException var9) {
            throw ClientErrorException.clientError(var9, "gn.D(" + var0 + ')');
        }
    }

    public static int method1100(int var0, int var2) {
        try {
            if (var2 == -1) {
                return 12345678;
            } else {

                var0 = var0 * (127 & var2) >> 7;
                if (2 <= var0) {
                    if (126 < var0) {
                        var0 = 126;
                    }
                } else {
                    var0 = 2;
                }

                return var0 + ('\uff80' & var2);
            }
        } catch (RuntimeException var4) {
            throw ClientErrorException.clientError(var4, "gn.C(" + var0 + ',' + true + ',' + var2 + ')');
        }
    }

    public static void method1516(int var0, int var1) {
        try {
            Class3_Sub28_Sub6 var2 = Class3_Sub24_Sub3.method466(3, var0);
            var2.a();
            if (var1 < 87) {
                TextCore.aClass94_1326 = (RSString) null;
            }

        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "n.L(" + var0 + ',' + var1 + ')');
        }
    }

    /**
     * static final void method1517(byte var0) {
     * try {
     * Class139.aFontMetrics1822 = null;
     * TextCore.Helvetica = null;
     * if(var0 != -118) {
     * method1516(64, 82);
     * }
     * <p>
     * Class129_Sub1.anImage2695 = null;
     * } catch (RuntimeException var2) {
     * throw Class44.method1067(var2, "n.J(" + var0 + ')');
     * }
     * }
     **/

    public static void method1517() {
        try {
            Class3_Sub28_Sub1.aFontMetrics1822 = null;

            //MillisTimer.anImage2695 = null;
        } catch (RuntimeException var2) {
            throw ClientErrorException.clientError(var2, "n.J(" + (byte) -118 + ')');
        }
    }

    public static int bitwiseXOR(int var0, int var1) {
        try {
            return var0 ^ var1;
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "n.E(" + var0 + ',' + var1 + ')');
        }
    }

    public static void method1525(int var1, int var2, int var3, int var4, int var5) {
        try {
            int var6 = Class40.method1040(Class57.anInt902, var3, Class159.anInt2020);
            int var7 = Class40.method1040(Class57.anInt902, var5, Class159.anInt2020);
            int var8 = Class40.method1040(Class3_Sub28_Sub18.anInt3765, var4, Class101.anInt1425);

            int var9 = Class40.method1040(Class3_Sub28_Sub18.anInt3765, var2, Class101.anInt1425);

            for (int var10 = var6; var10 <= var7; ++var10) {
                Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var10], var8, 3074 + -2974, var9, var1);
            }

        } catch (RuntimeException var11) {
            throw ClientErrorException.clientError(var11, "n.I(" + 3074 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
        }
    }

    public static Class3_Sub28_Sub16_Sub2 method562(CacheIndex var0, int var2) {
       try {
         //  System.out.println("Class 3_Sub28_Sub16_Sub2 " + var2);
          return Class75_Sub4.method1351(var0, 0, var2, -30901)? Class3_Sub28_Sub9.method578() :null;
       } catch (RuntimeException var5) {
          throw ClientErrorException.clientError(var5, "gf.O(" + (var0 != null?"{...}":"null") + ',' + 0 + ',' + var2 + ',' + (byte) 39 + ')');
       }
    }

    public static void method564(Signlink var0) {
       try {
          anInt3625 = 3;
          Class25.method957(true);
          aBoolean3604 = true;
          Class3_Sub13_Sub15.aBoolean3184 = true;
          Class128.aBoolean1685 = true;
          Class3_Sub28_Sub9.anInt3622 = 0;
          anInt3071 = 0;
          KeyboardListener.aBoolean1905 = true;
          WorldListEntry.aBoolean2623 = true;
          RSInterface.aBoolean236 = true;
          Sprites.anInt340 = 127;
          Class38.aBoolean661 = true;
          Class140_Sub6.aBoolean2910 = true;
          Class3_Sub13.anInt2378 = 0;
          anInt1137 = 2;
          Class3_Sub13_Sub22.aBoolean3275 = true;
          Class106.aBoolean1441 = true;
          anInt120 = 255;
          Class25.aBoolean488 = true;
          anInt3671 = 0;
          RandomAccessFileWrapper var2 = null;
          CS2Script.anInt2453 = 127;
          if(Class3_Sub24_Sub3.anInt3492 >= 96) {
             Class127_Sub1.method1758(2);
          } else {
             Class127_Sub1.method1758(0);
          }

          anInt2148 = 0;
          Class3_Sub20.anInt2488 = 0;
          Class15.aBoolean346 = false;
          Class163_Sub3.aBoolean3004 = true;
          aBoolean2146 = false;
          aBoolean1080 = false;
          anInt2577 = 0;

          try {
             Class64 var3 = var0.method1433("runescape", 12);

             while(0 == var3.anInt978) {
                TimeUtils.sleep(1L);
             }

             if(var3.anInt978 == 1) {
                var2 = (RandomAccessFileWrapper)var3.anObject974;
                byte[] var4 = new byte[(int)var2.getLength()];

                int var6;
                for(int var5 = 0; var4.length > var5; var5 += var6) {
                   var6 = var2.read(var4, var5, var4.length - var5, 0);
                   if(var6 == -1) {
                      throw new IOException("EOF");
                   }
                }

                Class79.method1390(new DataBuffer(var4));
             }
          } catch (Exception var8) {
          }

          try {
             if(var2 != null) {
                var2.close();
             }
          } catch (Exception var7) {
          }

       } catch (RuntimeException var9) {
          throw ClientErrorException.clientError(var9, "gf.F(" + (var0 != null?"{...}":"null") + ',' + 0 + ')');
       }
    }

    public static void method565(int var1, int var2) {
       try {
          Class82.anInt1150 = -Class3_Sub13_Sub21.anInt3256 + var1;

          int var3 = -((int)((float)Class3_Sub28_Sub3.aClass11_3551.width / Class44.aFloat727)) + Class82.anInt1150;
          int var4 = Class82.anInt1150 + (int)((float)Class3_Sub28_Sub3.aClass11_3551.width / Class44.aFloat727);
          if(var3 < 0) {
             Class82.anInt1150 = (int)((float)Class3_Sub28_Sub3.aClass11_3551.width / Class44.aFloat727);
          }

          Class3_Sub13_Sub30.anInt3362 = Class108.anInt1460 + -1 + anInt65 + -var2;
          int var6 = (int)((float)Class3_Sub28_Sub3.aClass11_3551.height / Class44.aFloat727) + Class3_Sub13_Sub30.anInt3362;
          int var5 = Class3_Sub13_Sub30.anInt3362 - (int)((float)Class3_Sub28_Sub3.aClass11_3551.height / Class44.aFloat727);
          if(Class23.anInt455 < var4) {
             Class82.anInt1150 = Class23.anInt455 + -((int)((float)Class3_Sub28_Sub3.aClass11_3551.width / Class44.aFloat727));
          }

          if(var5 < 0) {
             Class3_Sub13_Sub30.anInt3362 = (int)((float)Class3_Sub28_Sub3.aClass11_3551.height / Class44.aFloat727);
          }

          if(var6 > Class108.anInt1460) {
             Class3_Sub13_Sub30.anInt3362 = -((int)((float)Class3_Sub28_Sub3.aClass11_3551.height / Class44.aFloat727)) + Class108.anInt1460;
          }

       } catch (RuntimeException var7) {
          throw ClientErrorException.clientError(var7, "gf.E(" + (byte) 86 + ',' + var1 + ',' + var2 + ')');
       }
    }

    public static void method566(RSString var0, int var2) {
       try {
          Class3_Sub28_Sub6 var3 = Class3_Sub24_Sub3.method466(3, var2);
          var3.g();

          var3.aClass94_3599 = var0;
       } catch (RuntimeException var4) {
          throw ClientErrorException.clientError(var4, "gf.P(" + (var0 != null?"{...}":"null") + ',' + 0 + ',' + var2 + ')');
       }
    }

    public static void method569(int var1) {
       try {
          Class3_Sub28_Sub6 var2 = Class3_Sub24_Sub3.method466(7, var1);
          var2.a();
       } catch (RuntimeException var3) {
          throw ClientErrorException.clientError(var3, "he.C(" + -82 + ',' + var1 + ')');
       }
    }

    public static RSString method1723(byte var0, boolean var1, int var3) {
       try {
          int var4 = 1;

          for(int var5 = var3 / 10; 0 != var5; ++var4) {
             var5 /= 10;
          }

          int var6 = var4;
          if(var3 < 0 || var1) {
             var6 = var4 + 1;
          }

          byte[] var7 = new byte[var6];
          if(var3 >= 0) {
             if(var1) {
                var7[0] = 43;
             }
          } else {
             var7[0] = 45;
          }

          for(int var8 = 0; var4 > var8; ++var8) {
             int var9 = var3 % 10;
             if(var9 < 0) {
                var9 = -var9;
             }

             var7[var6 + -var8 + -1] = (byte)(48 + var9);
             var3 /= 10;
          }

          if(var0 >= -111) {
             method1727((byte)53);
          }

          RSString var11 = new RSString();
          var11.buffer = var7;
          var11.length = var6;
          return var11;
       } catch (RuntimeException var10) {
          throw ClientErrorException.clientError(var10, "q.A(" + var0 + ',' + var1 + ',' + 10 + ',' + var3 + ')');
       }
    }

    public static void method1724(int var0, int var1, int var2, int var3, int var4, int var5, byte var6, int var7) {
       try {
          if(var5 >= 128 && var2 >= 128 && var5 <= 13056 && var2 <= 13056) {
             int var8 = Class121.method1736(WorldListCountry.localPlane, 1, var5, var2) + -var3;
             var2 -= Class77.anInt1111;
             var8 -= Class7.anInt2162;
             var5 -= NPC.anInt3995;
             int var9 = Class51.anIntArray840[Class139.anInt1823];
             int var10 = Class51.anIntArray851[Class139.anInt1823];
             int var11 = Class51.anIntArray840[Class3_Sub13_Sub25.anInt3315];
             int var12 = Class51.anIntArray851[Class3_Sub13_Sub25.anInt3315];
             int var13 = var5 * var12 + var11 * var2 >> 16;
             var2 = var2 * var12 + -(var11 * var5) >> 16;
             var5 = var13;
             var13 = var10 * var8 - var2 * var9 >> 16;
             if(var6 <= -47) {
                var2 = var2 * var10 + var8 * var9 >> 16;
                if(50 > var2) {
                   Class82.anInt2208 = -1;
                   Class32.anInt590 = -1;
                } else if(HDToolKit.highDetail) {
                   int var14 = var1 * 512 >> 8;
                   Class32.anInt590 = var14 * var5 / var2 + var0;
                   int var15 = 512 * var7 >> 8;
                   Class82.anInt2208 = var15 * var13 / var2 + var4;
                } else {
                   Class32.anInt590 = (var5 << 9) / var2 + var0;
                   Class82.anInt2208 = (var13 << 9) / var2 + var4;
                }

             }
          } else {
             Class82.anInt2208 = -1;
             Class32.anInt590 = -1;
          }
       } catch (RuntimeException var16) {
          throw ClientErrorException.clientError(var16, "q.C(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ')');
       }
    }

    public static void method1726(int var0) {
       try {
          TextCore.aClass94_1622 = null;
          aShortArrayArray1619 = (short[][])null;
          TextCore.aClass94_1617 = null;

       } catch (RuntimeException var2) {
          throw ClientErrorException.clientError(var2, "q.B(" + var0 + ')');
       }
    }

    public static int method1727(byte var0) {
       try {
          if(var0 != 123) {
             method1726(-123);
          }

          return KeyboardListener.aReferenceCache_1911.hardCount();
       } catch (RuntimeException var2) {
          throw ClientErrorException.clientError(var2, "q.D(" + var0 + ')');
       }
    }
}
