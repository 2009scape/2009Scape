package org.runite.client;


import org.rs09.client.config.GameConfig;
import org.rs09.client.console.DeveloperConsole;
import org.rs09.client.filestore.resources.configs.enums.EnumDefinitionProvider;
import org.rs09.client.filestore.resources.configs.structs.StructDefinitionProvider;


import org.rs09.client.data.HashTable;
import org.rs09.client.net.Connection;

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
    static HashTable aHashTable_2194 = new HashTable(16);
    static Class3_Sub11[][] aClass3_Sub11ArrayArray2199;
    static int[] anIntArray2200;
    static int ZOOM = 600;
    static int currentPort;
    static int rectDebugInt = 0;
    static int LoadingStageNumber = 10;
    static int anInt869;
    static int loginScreenInterfaceID;
    static short[] aShortArray2219 = new short[]{(short)-4160, (short)-4163, (short)-8256, (short)-8259, (short)22461};
    static int anInt2275 = 1;
    static int anInt2317 = 0;
    static int anInt3068 = 0;
    static int[] anIntArray3288 = new int[]{4, 4, 1, 2, 6, 4, 2, 49, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    static long aLong3296 = 0L;
    static short[] aShortArray3349 = new short[]{(short)-10304, (short)9104, (short)-1, (short)-1, (short)-1};
    static short[][] aShortArrayArray2634 = new short[][]{{(short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)4, (short)24, (short)44, (short)64, (short)84, (short)104, (short)304, (short)678, (short)698, (short)550, (short)934, (short)954, (short)6448, (short)6946, (short)6966, (short)2352, (short)2726, (short)2746, (short)10544, (short)10918, (short)10938, (short)10304, (short)10550, (short)10570, (short)14640, (short)15014, (short)15034, (short)19760, (short)20134, (short)20154, (short)-29392, (short)-29018, (short)-28998, (short)31024, (short)31270, (short)31290, (short)-24272, (short)-23898, (short)-23878, (short)-19152, (short)-18778, (short)-18758, (short)-14032, (short)-13658, (short)-13638, (short)-6864, (short)-6490, (short)-6470, (short)516, (short)536, (short)6788, (short)6808, (short)11012, (short)11032, (short)14980, (short)15000, (short)21124, (short)21144, (short)-28924, (short)-28904, (short)-22012, (short)-21992, (short)-12924, (short)-12904}, {(short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)10339, (short)10574, (short)10425, (short)10398, (short)10345, (short)7512, (short)8507, (short)7378, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0}, {(short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)4, (short)24, (short)44, (short)64, (short)84, (short)104, (short)304, (short)678, (short)698, (short)550, (short)934, (short)954, (short)6448, (short)6946, (short)6966, (short)2352, (short)2726, (short)2746, (short)10544, (short)10918, (short)10938, (short)10304, (short)10550, (short)10570, (short)14640, (short)15014, (short)15034, (short)19760, (short)20134, (short)20154, (short)-29392, (short)-29018, (short)-28998, (short)31024, (short)31270, (short)31290, (short)-24272, (short)-23898, (short)-23878, (short)-19152, (short)-18778, (short)-18758, (short)-14032, (short)-13658, (short)-13638, (short)-6864, (short)-6490, (short)-6470, (short)516, (short)536, (short)6788, (short)6808, (short)11012, (short)11032, (short)14980, (short)15000, (short)21124, (short)21144, (short)-28924, (short)-28904, (short)-22012, (short)-21992, (short)-12924, (short)-12904}, {(short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)13753, (short)13737, (short)13719, (short)13883, (short)13863, (short)13974, (short)19643, (short)18601, (short)16532, (short)23993, (short)25121, (short)24980, (short)26944, (short)26921, (short)24854, (short)27191, (short)27171, (short)26130, (short)26941, (short)28696, (short)30100, (short)12477, (short)10407, (short)10388, (short)10685, (short)10665, (short)10646, (short)6711, (short)6693, (short)6674, (short)6965, (short)7073, (short)7056, (short)2361, (short)4387, (short)3346, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0}, {(short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)4, (short)24, (short)44, (short)64, (short)84, (short)104, (short)304, (short)678, (short)698, (short)550, (short)934, (short)954, (short)6448, (short)6946, (short)6966, (short)2352, (short)2726, (short)2746, (short)10544, (short)10918, (short)10938, (short)10304, (short)10550, (short)10570, (short)14640, (short)15014, (short)15034, (short)19760, (short)20134, (short)20154, (short)-29392, (short)-29018, (short)-28998, (short)31024, (short)31270, (short)31290, (short)-24272, (short)-23898, (short)-23878, (short)-19152, (short)-18778, (short)-18758, (short)-14032, (short)-13658, (short)-13638, (short)-6864, (short)-6490, (short)-6470, (short)516, (short)536, (short)6788, (short)6808, (short)11012, (short)11032, (short)14980, (short)15000, (short)21124, (short)21144, (short)-28924, (short)-28904, (short)-22012, (short)-21992, (short)-12924, (short)-12904}};
    static Class3_Sub24_Sub4 aClass3_Sub24_Sub4_1193;
    static long aLong1310 = 0L;
    static int anInt1354 = 0;
    static Class61 aClass61_1471 = new Class61();
    static Class30 aClass30_1572;
    static int[] anIntArray3780 = new int[32];
    static boolean aBoolean3779 = false;
    static int anInt3773;

    static void method631(CacheIndex var1) {
        try {
            Class3_Sub28_Sub5.aClass153_3580 = var1;
            anInt869 = Class3_Sub28_Sub5.aClass153_3580.getFileAmount(4);
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "qc.D(" + false + ',' + (var1 != null ? "{...}" : "null") + ')');
        }
    }

    static void method1175(int var0) {
        try {
            Class3_Sub13_Sub30.anInt3362 = -1;
            Class82.anInt1150 = -1;

            Class3_Sub28_Sub1.anInt3536 = var0;
            Class3_Sub5.method117();
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "hj.D(" + var0 + ',' + 112 + ')');
        }
    }

    static void method375(CacheIndex var1, CacheIndex var2) {
        try {
            Class10.aClass153_152 = var1;
            LoginHandler.aClass153_1680 = var2;
            Class25.anInt497 = LoginHandler.aClass153_1680.getFileAmount(3);
        } catch (RuntimeException var4) {
            throw ClientErrorException.clientError(var4, "jl.D(" + 3 + ',' + (var1 != null ? "{...}" : "null") + ',' + (var2 != null ? "{...}" : "null") + ')');
        }
    }


    final void method38() {
        try {
            if (Class143.loadingStage != 1000) {
                boolean var2 = NPC.method1988();
                if (var2 && Class83.aBoolean1158 && WorldListEntry.aClass155_2627 != null) {
                    WorldListEntry.aClass155_2627.method2158();
                }

                if ((Class143.loadingStage == 30 || Class143.loadingStage == 10) && (Class3_Sub28_Sub5.forceReplaceCanvasEnable || Class53.aLong866 != 0 && Class53.aLong866 < TimeUtils.time())) {
                    GameObject.graphicsSettings(Class3_Sub28_Sub5.forceReplaceCanvasEnable, Class83.method1411(0), Class3_Sub13.anInt2378, Unsorted.anInt3071);
                }

                int var4;
                int var5;
                if (null == Class3_Sub13_Sub10.aFrame3121) {
                    Object var3;
                    if (GameShell.frame == null) {
                        var3 = Class38.aClass87_665.applet;
                    } else {
                        var3 = GameShell.frame;
                    }

                    var4 = ((Container) var3).getSize().width;
                    var5 = ((Container) var3).getSize().height;
                    if (var3 == GameShell.frame) {
                        Insets var6 = GameShell.frame.getInsets();
                        var4 -= var6.right + var6.left;
                        var5 -= var6.top + var6.bottom;
                    }

                    if (var4 != Unsorted.anInt2334 || Class70.anInt1047 != var5) {
                        if (Signlink.osName.startsWith("mac")) {
                            Unsorted.anInt2334 = var4;
                            Class70.anInt1047 = var5;
                        } else {
                            Class119.method1729();
                        }

                        Class53.aLong866 = TimeUtils.time() - -500L;
                    }
                }

                if (Class3_Sub13_Sub10.aFrame3121 != null && !Class3_Sub13_Sub6.aBoolean3078 && (30 == Class143.loadingStage || 10 == Class143.loadingStage)) {
                    GameObject.graphicsSettings(false, Unsorted.anInt2577, -1, -1);
                }

                boolean var10 = false;
                if (Class3_Sub13_Sub10.aBoolean3116) {
                    var10 = true;
                    Class3_Sub13_Sub10.aBoolean3116 = false;
                }

                if (var10) {
                    Unsorted.method1396(40 ^ -41);
                }

                if (HDToolKit.highDetail) {
                    for (var4 = 0; var4 < 100; ++var4) {
                        Unsorted.aBooleanArray3674[var4] = true;
                    }
                }
                if (Class143.loadingStage == 0) {
                    // Don't load Discord on ARM
		    if (!System.getProperty("os.arch").contains("aarch64")) {
                        Discord.InitalizeDiscord();
                    }
                    Class3_Sub28_Sub1.updateLoadingBar((Color) null, var10, Class3_Sub17.aClass94_2464, LoadingStageNumber);
                } else if (5 == Class143.loadingStage) {
                    Class3_Sub23.method406((byte) 117, false, Class168.aClass3_Sub28_Sub17_2096);
                } else if (Class143.loadingStage == 10) {
                    Class3_Sub17.method381(true);
                } else if (25 != Class143.loadingStage && Class143.loadingStage != 28) {
                    if (Class143.loadingStage == 30) {
                        Class49.method1127(0);
                    } else if (40 == Class143.loadingStage) {
                        Class3_Sub13.method164((byte) -95, false, RSString.stringCombiner(new RSString[]{TextCore.ConxLost, TextCore.aClass94_2598, TextCore.AttemptingReestablish}));
                    }
                } else if (Class163_Sub2_Sub1.anInt4019 == 1) {
                    if (Class40.anInt3293 > LinkableRSString.anInt2579) {
                        LinkableRSString.anInt2579 = Class40.anInt3293;
                    }

                    var4 = 50 * (LinkableRSString.anInt2579 + -Class40.anInt3293) / LinkableRSString.anInt2579;
                    Class3_Sub13.method164((byte) -71, false, RSString.stringCombiner(new RSString[]{TextCore.LoadingPleaseWait2, TextCore.aClass94_3399, RSString.stringAnimator(var4), TextCore.aClass94_148}));
                } else if (Class163_Sub2_Sub1.anInt4019 == 2) {
                    if (anInt2275 < Class162.anInt2038) {
                        anInt2275 = Class162.anInt2038;
                    }

                    var4 = (-Class162.anInt2038 + anInt2275) * 50 / anInt2275 + 50;
                    Class3_Sub13.method164((byte) -41, false, RSString.stringCombiner(new RSString[]{TextCore.LoadingPleaseWait2, TextCore.aClass94_3399, RSString.stringAnimator(var4), TextCore.aClass94_148}));
                } else {
                    Class3_Sub13.method164((byte) -73, false, TextCore.LoadingPleaseWait2);
                }

                DeveloperConsole.INSTANCE.preDraw();
                DeveloperConsole.INSTANCE.draw();

                if (HDToolKit.highDetail && Class143.loadingStage != 0) {
                    HDToolKit.bufferSwap();

                    for (var4 = 0; Class3_Sub28_Sub3.anInt3557 > var4; ++var4) {
                        Class163_Sub1_Sub1.aBooleanArray4008[var4] = false;
                    }
                } else {
                    Graphics var11;
                    if ((Class143.loadingStage == 30 || 10 == Class143.loadingStage) && rectDebugInt == 0 && !var10) {
                        try {
                            var11 = GameShell.canvas.getGraphics();

                            for (var5 = 0; Class3_Sub28_Sub3.anInt3557 > var5; ++var5) {
                                if (Class163_Sub1_Sub1.aBooleanArray4008[var5]) {
                                    Class164_Sub1.aClass158_3009.drawGraphics(Class3_Sub28_Sub18.anIntArray3768[var5], Class155.anIntArray1969[var5], Class140_Sub4.anIntArray2794[var5], var11, Player.anIntArray3954[var5]);
                                    Class163_Sub1_Sub1.aBooleanArray4008[var5] = false;
                                }
                            }
                        } catch (Exception var8) {
                            GameShell.canvas.repaint();
                        }
                    } else if (0 != Class143.loadingStage) {
                        try {
                            var11 = GameShell.canvas.getGraphics();
                            Class164_Sub1.aClass158_3009.method2179(var11);

                            for (var5 = 0; var5 < Class3_Sub28_Sub3.anInt3557; ++var5) {
                                Class163_Sub1_Sub1.aBooleanArray4008[var5] = false;
                            }
                        } catch (Exception var7) {
                            var7.printStackTrace();
                            GameShell.canvas.repaint();
                        }
                    }
                }

                if (Class58.aBoolean913) {
                    Class75_Sub3.method1346();
                }

                if (Unsorted.aBoolean2146 && 10 == Class143.loadingStage && Class3_Sub28_Sub12.anInt3655 != -1) {
                    Unsorted.aBoolean2146 = false;
                    Class119.method1730(Class38.aClass87_665);
                }

                DeveloperConsole.INSTANCE.postDraw();

//				if (DeveloperConsole.INSTANCE.getOpen()) {
//				    DeveloperConsole.INSTANCE.draw();
////					System.out.println("Draw developer console");
//				}
            }
        } catch (RuntimeException var9) {
            throw ClientErrorException.clientError(var9, "client.K(" + 40 + ')');
        }
    }

    static RSInterface method42(RSInterface var0) {
        int var1 = method44(var0).method94();
        if (var1 == 0) {
            return null;
        } else {
            for (int var2 = 0; var2 < var1; ++var2) {
                var0 = Class7.getRSInterface(var0.parentId);
                if (var0 == null) {
                    return null;
                }
            }

            return var0;
        }
    }

    final void method32() {
        try {
            if (HDToolKit.highDetail) {
                HDToolKit.method1842();
            }

            if (null != Class3_Sub13_Sub10.aFrame3121) {
                Unsorted.method593(Class3_Sub13_Sub10.aFrame3121, Class38.aClass87_665);
                Class3_Sub13_Sub10.aFrame3121 = null;
            }

            if (null != Class38.aClass87_665) {
                Class38.aClass87_665.method1442(this.getClass(), 0);
            }

            if (null != Class3_Sub13_Sub13.aClass67_1443) {
                Class3_Sub13_Sub13.aClass67_1443.aBoolean1015 = false;
            }

            Class3_Sub13_Sub13.aClass67_1443 = null;
            if (Class3_Sub15.activeConnection != null) {
                Class3_Sub15.activeConnection.close();
                Class3_Sub15.activeConnection = null;
            }

            Class163_Sub1_Sub1.method2215(GameShell.canvas);
            Unsorted.method1783(GameShell.canvas);
            if (null != Class38.aClass146_668) {
                Class38.aClass146_668.method2082(GameShell.canvas);
            }

            Class3_Sub13_Sub1.method167(0);
            MouseListeningClass.method2090();
            Class38.aClass146_668 = null;
            if (null != WorldListEntry.aClass155_2627) {
                WorldListEntry.aClass155_2627.method2163();
            }

            if (null != Class3_Sub21.aClass155_2491) {
                Class3_Sub21.aClass155_2491.method2163();
            }

            Class58.aJs5Worker_917.close();
            Class3_Sub13_Sub14.aCacheResourceWorker_3159.stop();

            try {
                if (Class101.aClass30_1422 != null) {
                    Class101.aClass30_1422.method980();
                }

                if (Class163_Sub2.aClass30Array2998 != null) {
                    for (int var2 = 0; var2 < Class163_Sub2.aClass30Array2998.length; ++var2) {
                        if (null != Class163_Sub2.aClass30Array2998[var2]) {
                            Class163_Sub2.aClass30Array2998[var2].method980();
                        }
                    }
                }

                if (null != aClass30_1572) {
                    aClass30_1572.method980();
                }

                if (null != Unsorted.aClass30_1039) {
                    Unsorted.aClass30_1039.method980();
                }
            } catch (IOException var3) {
            }
        } catch (RuntimeException var4) {
            throw ClientErrorException.clientError(var4, "client.F(" + (byte) 23 + ')');
        }
    }

    public final void init() {
        try {
            GameConfig.WORLD = ObjectDefinition.worldId;
            Class44.anInt718 = Integer.parseInt(Objects.requireNonNull(this.getParameter("modewhere")));
            if (0 > Class44.anInt718 || 1 < Class44.anInt718) {
                Class44.anInt718 = 0;
            }

            Class3_Sub13_Sub13.anInt3148 = Integer.parseInt(Objects.requireNonNull(this.getParameter("modewhat")));
            if (Class3_Sub13_Sub13.anInt3148 < 0 || Class3_Sub13_Sub13.anInt3148 > 2) {
                Class3_Sub13_Sub13.anInt3148 = 0;
            }

            String var1 = this.getParameter("advertsuppressed");
            aBoolean3779 = var1 != null && var1.equals("1");

            try {
                Class3_Sub20.language = Integer.parseInt(Objects.requireNonNull(this.getParameter("lang")));
            } catch (Exception var10) {
                Class3_Sub20.language = 0;
            }
            Unsorted.method87(-31, Class3_Sub20.language);

            String var2 = this.getParameter("objecttag");
            Class163_Sub2_Sub1.aBoolean4018 = var2 != null && var2.equals("1");

            String var3 = this.getParameter("js");
            Unsorted.aBoolean3641 = null != var3 && var3.equals("1");

            String var4 = this.getParameter("game");
            if (var4 != null && var4.equals("1")) {
                Class158.anInt2014 = 1;
            } else {
                Class158.anInt2014 = 0;
            }

            try {
                Class3_Sub26.anInt2554 = Integer.parseInt(Objects.requireNonNull(this.getParameter("affid")));
            } catch (Exception var9) {
                Class3_Sub26.anInt2554 = 0;
            }

            Class163_Sub2.aClass94_2996 = TextCore.aClass94_1745.getParamValue(this);
            if (Class163_Sub2.aClass94_2996 == null) {
                Class163_Sub2.aClass94_2996 = TextCore.aClass94_3672;
            }

            String var5 = this.getParameter("country");
            if (var5 != null) {
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
            throw ClientErrorException.clientError(var11, "client.init()");
        }
    }

    final void method39() {
        try {
            Class119.method1729();
            Class3_Sub13_Sub14.aCacheResourceWorker_3159 = new CacheResourceWorker();
            Class58.aJs5Worker_917 = new Js5Worker();
            if (Class3_Sub13_Sub13.anInt3148 != 0) {
                Class3_Sub6.aByteArrayArray2287 = new byte[50][];
            }

            CS2Script.anInt2451 = ObjectDefinition.worldId;
            Unsorted.method564(Class38.aClass87_665);
            System.out.println("port = " + Class53.anInt867);
            System.out.println("Here: Config.MSIP = " + GameConfig.IP_MANAGEMENT);
            if (Class44.anInt718 == 0) {
                ClientErrorException.worldListHost = Objects.requireNonNull(this.getCodeBase()).getHost();
                Class53.anInt867 = GameConfig.SERVER_PORT + ObjectDefinition.worldId; //443 is secure port
                anInt3773 = '\uaa4a';
            } else if (Class44.anInt718 == 1) {
                ClientErrorException.worldListHost = Objects.requireNonNull(this.getCodeBase()).getHost();
                //System.out.println("port = " + Class53.anInt867);
                Class53.anInt867 = ObjectDefinition.worldId + 50000;
                anInt3773 = 40000 + ObjectDefinition.worldId;
            } else if (Class44.anInt718 == 2) {
                ClientErrorException.worldListHost = "127.0.0.1";
                System.out.println("Setting worldListHost to 127.0.0.1, port = " + Class53.anInt867);
                Class53.anInt867 = ObjectDefinition.worldId + '\uc350';
                anInt3773 = ObjectDefinition.worldId + '\u9c40';
            }
            if (1 == Class158.anInt2014) {
                ClientCommands.shiftClickEnabled = true;
                Class92.defaultScreenColorRgb = 16777215;
                Class92.defaultRegionAmbientRGB = 0;
                Class15.aShortArrayArray344 = Unsorted.aShortArrayArray1619;
                Class101.aShortArrayArray1429 = aShortArrayArray2634;
                Class3_Sub25.aShortArray2548 = Unsorted.aShortArray63;
                Class91.aShortArray1311 = aShortArray2219;
            } else {
                Class15.aShortArrayArray344 = Class3_Sub28_Sub12.aShortArrayArray3654;
                Class91.aShortArray1311 = aShortArray3349;
                Class101.aShortArrayArray1429 = Class20.aShortArrayArray435;
                Class3_Sub25.aShortArray2548 = Class164_Sub1.aShortArray3011;
            }

            currentPort = Class53.anInt867;
            Class162.anInt2036 = anInt3773;
            Class38_Sub1.accRegistryIp = GameConfig.IP_MANAGEMENT;
            Class123.anInt1658 = anInt3773;
            Class3_Sub13_Sub38.aShortArray3455 = Class3_Sub13_Sub9.aShortArray3110 = Class136.aShortArray1779 = Class3_Sub13_Sub38.aShortArray3453 = new short[256];

            Class140_Sub6.accRegistryPort = Class123.anInt1658;
            if (Signlink.anInt1214 == 3 && 2 != Class44.anInt718) {
                CS2Script.anInt2451 = ObjectDefinition.worldId;
            }

            KeyboardListener.adjustKeyCodeMap();
            Class3_Sub13_Sub4.method193((byte) 115, GameShell.canvas);
            ItemDefinition.method1119(GameShell.canvas, false);
            Class38.aClass146_668 = Class21.method916();
            if (null != Class38.aClass146_668) {
                Class38.aClass146_668.method2084(GameShell.canvas, -97);
            }

            try {
                if (Class38.aClass87_665.cacheDataFile != null) {
                    Class101.aClass30_1422 = new Class30(Class38.aClass87_665.cacheDataFile, 5200);

                    for (int var2 = 0; var2 < 29; ++var2) {
                        Class163_Sub2.aClass30Array2998[var2] = new Class30(Class38.aClass87_665.cacheIndicesFiles[var2], 6000);
                    }

                    aClass30_1572 = new Class30(Class38.aClass87_665.cacheChecksumFile, 6000);
                    AtmosphereParser.aClass41_1186 = new Class41(255, Class101.aClass30_1422, aClass30_1572, 500000);
                    Unsorted.aClass30_1039 = new Class30(Class38.aClass87_665.randomDatFile, 24);
                    Class38.aClass87_665.cacheIndicesFiles = null;
                    Class38.aClass87_665.cacheChecksumFile = null;
                    Class38.aClass87_665.randomDatFile = null;
                    Class38.aClass87_665.cacheDataFile = null;
                }
            } catch (IOException var3) {
                Unsorted.aClass30_1039 = null;
                Class101.aClass30_1422 = null;
                aClass30_1572 = null;
                AtmosphereParser.aClass41_1186 = null;
            }

            Class167.aClass94_2083 = TextCore.RSLoadingPleaseWait;
            if (Class44.anInt718 != 0) {
                ClientCommands.fpsOverlayEnabled = true;
            }
            //Class3_Sub26.tweeningEnabled  = true;
        } catch (RuntimeException var4) {
            throw ClientErrorException.clientError(var4, "client.B(" + 2 + ')');
        }
    }

    final void method33() {
        // TODO Do we still need this method here?
    }

    static Class3_Sub1 method44(RSInterface var0) {
        Class3_Sub1 var1 = (Class3_Sub1) Class124.aHashTable_1659.get(((long) var0.componentHash << 32) + (long) var0.anInt191);
        return var1 != null ? var1 : var0.aClass3_Sub1_257;
    }

    private void method46(int var2) {
        try {
            ++Class58.aJs5Worker_917.errors;
            Class17.aClass64_413 = null;

            Class58.aJs5Worker_917.status = var2;
            Unsorted.js5Connection = null;
            PacketParser.anInt80 = 0;
        } catch (RuntimeException var4) {
            throw ClientErrorException.clientError(var4, "client.P(" + true + ',' + var2 + ')');
        }
    }

    private void method47() {
        try {
            for (Class3_Sub23.anInt2537 = 0; Unsorted.method591(83) && Class3_Sub23.anInt2537 < 128; ++Class3_Sub23.anInt2537) {
                Class133.inputTextCodeArray[Class3_Sub23.anInt2537] = Class3_Sub28_Sub9.anInt3624;
                Class120.anIntArray1638[Class3_Sub23.anInt2537] = Class3_Sub13_Sub27.anInt3342;
            }

            ++Class106.anInt1446;
            if (-1 != Class3_Sub28_Sub12.anInt3655) {
                GraphicDefinition.method967(0, 0, 0, Class23.anInt454, Class3_Sub28_Sub12.anInt3655, 0, Class140_Sub7.anInt2934);
            }

            ++PacketParser.anInt3213;
            if (HDToolKit.highDetail) {
                int var2 = 19137023;

                label191:
                for (int var3 = 0; var3 < 32768; ++var3) {
                    NPC var4 = NPC.npcs[var3];
                    if (null != var4) {
                        byte var5 = var4.definition.aByte1267;
                        if ((var5 & 2) > 0 && var4.anInt2816 == 0 && 10.0D > Math.random() * 1000.0D) {
                            int var6 = (int) Math.round(-1.0D + 2.0D * Math.random());
                            int var7 = (int) Math.round(Math.random() * 2.0D - 1.0D);
                            if (var6 != 0 || 0 != var7) {
                                var4.aByteArray2795[0] = 1;
                                var4.anIntArray2767[0] = var6 + (var4.anInt2819 >> 7);
                                var4.anIntArray2755[0] = var7 + (var4.anInt2829 >> 7);
                                AtmosphereParser.aClass91Array1182[WorldListCountry.localPlane].method1502(var4.anInt2819 >> 7, var4.getSize(), false, 0, var4.getSize(), var4.anInt2829 >> 7);
                                if (0 <= var4.anIntArray2767[0] && var4.anIntArray2767[0] <= 104 + -var4.getSize() && 0 <= var4.anIntArray2755[0] && var4.anIntArray2755[0] <= 104 - var4.getSize() && AtmosphereParser.aClass91Array1182[WorldListCountry.localPlane].method1500(var4.anInt2829 >> 7, var4.anIntArray2755[0], var4.anIntArray2767[0], var4.anInt2819 >> 7)) {
                                    if (var4.getSize() > 1) {
                                        for (int var8 = var4.anIntArray2767[0]; var8 < var4.anIntArray2767[0] - -var4.getSize(); ++var8) {
                                            for (int var9 = var4.anIntArray2755[0]; var4.anIntArray2755[0] + var4.getSize() > var9; ++var9) {
                                                if ((var2 & AtmosphereParser.aClass91Array1182[WorldListCountry.localPlane].anIntArrayArray1304[var8][var9]) != 0) {
                                                    continue label191;
                                                }
                                            }
                                        }
                                    }

                                    var4.anInt2816 = 1;
                                }
                            }
                        }

                        Unsorted.method1180((byte) -122, var4);
                        Class17.method904(65536, var4);
                        RenderAnimationDefinition.method900(var4, (byte) 1 ^ -11974);
                        AtmosphereParser.aClass91Array1182[WorldListCountry.localPlane].method1489(var4.anInt2819 >> 7, false, (byte) 85, var4.anInt2829 >> 7, var4.getSize(), var4.getSize());
                    }
                }
            }

            if (!HDToolKit.highDetail) {
                Unsorted.method744();
            } else if (0 == LoginHandler.loginStage && 0 == Unsorted.registryStage) {
                if (Class133.anInt1753 == 2) {
                    CS2Script.method379();
                } else {
                    InterfaceWidget.d('\uffff');
                }

                if (14 > NPC.anInt3995 >> 7 || NPC.anInt3995 >> 7 >= 90 || 14 > Class77.anInt1111 >> 7 || Class77.anInt1111 >> 7 >= 90) {
                    Class3_Sub13_Sub6.method195();
                }
            }

            while (true) {
                CS2Script var11 = (CS2Script) PacketParser.aClass61_82.method1220();
                RSInterface var12;
                RSInterface var13;
                if (var11 == null) {
                    while (true) {
                        var11 = (CS2Script) Class65.aClass61_983.method1220();
                        if (null == var11) {
                            while (true) {
                                var11 = (CS2Script) aClass61_1471.method1220();
                                if (null == var11) {
                                    if (Class56.aClass11_886 != null) {
                                        PacketParser.method829();
                                    }

                                    if (null != Class15.aClass64_351 && Class15.aClass64_351.anInt978 == 1) {
                                        if (null != Class15.aClass64_351.anObject974) {
                                            Class99.method1596(Class3_Sub13_Sub24.aClass94_3295, (byte) 126, Unsorted.aBoolean2154);
                                        }

                                        Unsorted.aBoolean2154 = false;
                                        Class3_Sub13_Sub24.aClass94_3295 = null;
                                        Class15.aClass64_351 = null;
                                    }

                                    if (Class44.anInt719 % 1500 == 0) {
                                        Class72.method1293();
                                    }

                                    return;
                                }

                                var12 = var11.aClass11_2449;
                                if (0 <= var12.anInt191) {
                                    var13 = Class7.getRSInterface(var12.parentId);
                                    if (var13 == null || null == var13.aClass11Array262 || var12.anInt191 >= var13.aClass11Array262.length || var12 != var13.aClass11Array262[var12.anInt191]) {
                                        continue;
                                    }
                                }

                                Class43.method1065(var11);
                            }
                        }

                        var12 = var11.aClass11_2449;
                        if (var12.anInt191 >= 0) {
                            var13 = Class7.getRSInterface(var12.parentId);
                            if (null == var13 || var13.aClass11Array262 == null || var13.aClass11Array262.length <= var12.anInt191 || var12 != var13.aClass11Array262[var12.anInt191]) {
                                continue;
                            }
                        }

                        Class43.method1065(var11);
                    }
                }

                var12 = var11.aClass11_2449;
                if (var12.anInt191 >= 0) {
                    var13 = Class7.getRSInterface(var12.parentId);
                    if (null == var13 || null == var13.aClass11Array262 || var12.anInt191 >= var13.aClass11Array262.length || var12 != var13.aClass11Array262[var12.anInt191]) {
                        continue;
                    }
                }

                Class43.method1065(var11);
            }
        } catch (RuntimeException var10) {
            throw ClientErrorException.clientError(var10, "client." + (byte) 1 + ')');
        }
    }

    private void method48() {
        try {
            boolean var2 = Class58.aJs5Worker_917.process();
            if (!var2) {
                this.method49();
            }

        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "client.J(" + true + ')');
        }
    }

    private void method49() {
        if (Class58.aJs5Worker_917.errors > Class163_Sub2_Sub1.anInt4026) {
            anInt3068 = 5 * 50 * (Class58.aJs5Worker_917.errors + -1);

            if (Class162.anInt2036 == Class140_Sub6.accRegistryPort) {
                Class140_Sub6.accRegistryPort = currentPort;
            } else {
                Class140_Sub6.accRegistryPort = Class162.anInt2036;
            }

            if (anInt3068 > 3000) {
                anInt3068 = 3000;
            }

            if (Class58.aJs5Worker_917.errors >= 2 && Class58.aJs5Worker_917.status == 6) {
                this.method31("js5connect_outofdate");
                Class143.loadingStage = 1000;
                return;
            }

            if (Class58.aJs5Worker_917.errors >= 4 && Class58.aJs5Worker_917.status == -1) {
                this.method31("js5crc");
                Class143.loadingStage = 1000;
                return;
            }

            if (Class58.aJs5Worker_917.errors >= 4 && (Class143.loadingStage == 0 || Class143.loadingStage == 5)) {
                if (Class58.aJs5Worker_917.status == 7 || Class58.aJs5Worker_917.status == 9) {
                    this.method31("js5connect_full");
                } else if (Class58.aJs5Worker_917.status > 0) {
                    this.method31("js5connect");
                } else {
                    this.method31("js5io");
                }

                Class143.loadingStage = 1000;
                return;
            }
        }

        Class163_Sub2_Sub1.anInt4026 = Class58.aJs5Worker_917.errors;
        if (anInt3068 > 0) {
            --anInt3068;
        } else {
            try {
                if (PacketParser.anInt80 == 0) {
                    Class17.aClass64_413 = Class38.aClass87_665.method1441((byte) 8, Class38_Sub1.accRegistryIp, Class140_Sub6.accRegistryPort);
                    ++PacketParser.anInt80;
                }

                if (PacketParser.anInt80 == 1) {
                    if (2 == Objects.requireNonNull(Class17.aClass64_413).anInt978) {
                        this.method46(1000);
                        return;
                    }

                    if (Class17.aClass64_413.anInt978 == 1) {
                        ++PacketParser.anInt80;
                    }
                }

                if (2 == PacketParser.anInt80) {
                    Unsorted.js5Connection = new Connection((Socket) Objects.requireNonNull(Class17.aClass64_413).anObject974, Class38.aClass87_665);
                    DataBuffer var2 = new DataBuffer(9);
                    var2.writeByte(15); //JS5 handshake
                    var2.writeInt(GameConfig.CLIENT_BUILD);
                    var2.writeInt(GameConfig.CLIENT_VERSION);
                    Unsorted.js5Connection.sendBytes(var2.buffer, 9);
                    ++PacketParser.anInt80;
                    Class3_Sub13_Sub30.aLong3366 = TimeUtils.time();
                }

                if (3 == PacketParser.anInt80) {
                    if (Class143.loadingStage != 0 && Class143.loadingStage != 5 && 0 >= Unsorted.js5Connection.availableBytes()) {
                        if (TimeUtils.time() + -Class3_Sub13_Sub30.aLong3366 > 30000) {
                            this.method46(1001);
                            return;
                        }
                    } else {
                        int var5 = Unsorted.js5Connection.readByte();
                        if (var5 != 0) {
                            this.method46(var5);
                            return;
                        }

                        ++PacketParser.anInt80;
                    }
                }

                if (PacketParser.anInt80 == 4) {
                    boolean var6 = Class143.loadingStage == 5 || Class143.loadingStage == 10 || Class143.loadingStage == 28;
                    Class58.aJs5Worker_917.connect(!var6, Unsorted.js5Connection);
                    Unsorted.js5Connection = null;
                    Class17.aClass64_413 = null;
                    PacketParser.anInt80 = 0;
                }
            } catch (IOException var3) {
                this.method46(1002);
            }

        }
    }

    public static void main(String[] var0) {
        try {
            try {
                if (4 != var0.length) {
                    Class3_Sub13_Sub23_Sub1.method283("argument count");
                }

                int var1 = -1;
                ObjectDefinition.worldId = Integer.parseInt(var0[0]);
                Class44.anInt718 = 2;
                if (var0[1].equals("live")) {
                    Class3_Sub13_Sub13.anInt3148 = 0;
                } else if (var0[1].equals("rc")) {
                    Class3_Sub13_Sub13.anInt3148 = 1;
                } else if (var0[1].equals("wip")) {
                    Class3_Sub13_Sub13.anInt3148 = 2;
                } else {
                    Class3_Sub13_Sub23_Sub1.method283("modewhat");
                }

                aBoolean3779 = false;

                try {
                    byte[] var2 = var0[2].getBytes(StandardCharsets.ISO_8859_1);
                    var1 = Class3_Sub13_Sub16.method243(Class3_Sub13_Sub3.method178(var2, var2.length, 0));
                } catch (Exception var3) {
                }

                if (-1 == var1) {
                    if (var0[2].equals("english")) {
                        Class3_Sub20.language = 0;
                    } else if (var0[2].equals("german")) {
                        Class3_Sub20.language = 1;
                    } else {
                        Class3_Sub13_Sub23_Sub1.method283("language");
                    }
                } else {
                    Class3_Sub20.language = var1;
                }

                Unsorted.method87(-78, Class3_Sub20.language);
                Class163_Sub2_Sub1.aBoolean4018 = false;
                Unsorted.aBoolean3641 = false;
                if (var0[3].equals("game0")) {
                    Class158.anInt2014 = 0;
                } else if (var0[3].equals("game1")) {
                    Class158.anInt2014 = 1;
                } else {
                    Class3_Sub13_Sub23_Sub1.method283("game");
                }

                Class3_Sub31.countryId = 0;
                Class106.hasInternetExplorer6 = false;
                Class3_Sub26.anInt2554 = 0;
                Class163_Sub2.aClass94_2996 = TextCore.aClass94_3672;
                Client var6 = new Client();
                Class126.aClient1671 = var6;
                var6.launch();
                GameShell.frame.setLocation(40, 40);
            } catch (Exception var4) {
                Class49.method1125(null, var4, (byte) 119);
            }

        } catch (RuntimeException var5) {
            throw ClientErrorException.clientError(var5, "client.main(" + (var0 != null ? "{...}" : "null") + ')');
        }
    }

    static void handleItemSwitch(RSInterface[] interfaces, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
        for (RSInterface inter : interfaces) {
            if (inter != null && inter.parentId == var1 && (!inter.usingScripts || inter.type == 0 || inter.aBoolean195 || method44(inter).anInt2205 != 0 || inter == PacketParser.aClass11_88 || inter.anInt189 == 1338) && (!inter.usingScripts || !method51(inter))) {
                int var10 = inter.anInt306 + var6;
                int var11 = inter.anInt210 + var7;
                int var12;
                int var13;
                int var14;
                int var15;
                if (inter.type == 2) {
                    var12 = var2;
                    var13 = var3;
                    var14 = var4;
                    var15 = var5;
                } else {
                    int var16 = var10 + inter.width;
                    int var17 = var11 + inter.height;
                    if (inter.type == 9) {
                        ++var16;
                        ++var17;
                    }

                    var12 = var10 > var2 ? var10 : var2;
                    var13 = var11 > var3 ? var11 : var3;
                    var14 = var16 < var4 ? var16 : var4;
                    var15 = var17 < var5 ? var17 : var5;
                }

                if (inter == Class56.aClass11_886) {
                    Class21.aBoolean440 = true;
                    Class3_Sub15.anInt2421 = var10;
                    Class3_Sub2.anInt2218 = var11;
                }

                if (!inter.usingScripts || var12 < var14 && var13 < var15) {
                    if (inter.type == 0) {
                        if (!inter.usingScripts && method51(inter) && Class107.aClass11_1453 != inter) {
                            continue;
                        }

                        if (inter.aBoolean219 && Class126.anInt1676 >= var12 && Unsorted.anInt1709 >= var13 && Class126.anInt1676 < var14 && Unsorted.anInt1709 < var15) {
                            for (CS2Script var27 = (CS2Script) aClass61_1471.method1222(); var27 != null; var27 = (CS2Script) aClass61_1471.method1221()) {
                                if (var27.aBoolean2446) {
                                    var27.unlink();
                                    var27.aClass11_2449.aBoolean163 = false;
                                }
                            }

                            if (Class75_Sub3.anInt2658 == 0) {
                                Class56.aClass11_886 = null;
                                PacketParser.aClass11_88 = null;
                            }

                            Class3_Sub19.anInt2475 = 0;
                        }
                    }

                    if (inter.usingScripts) {
                        boolean var26;
                        var26 = Class126.anInt1676 >= var12 && Unsorted.anInt1709 >= var13 && Class126.anInt1676 < var14 && Unsorted.anInt1709 < var15;

                        boolean var25 = false;
                        if (Class3_Sub13_Sub5.anInt3069 == 1 && var26) {
                            var25 = true;
                        }

                        boolean var18 = false;
                        if (Unsorted.anInt3644 == 1 && Class163_Sub1.anInt2993 >= var12 && Class38_Sub1.anInt2614 >= var13 && Class163_Sub1.anInt2993 < var14 && Class38_Sub1.anInt2614 < var15) {
                            var18 = true;
                        }

                        int var19;
                        int var21;
                        if (inter.aByteArray263 != null) {
                            for (var19 = 0; var19 < inter.aByteArray263.length; ++var19) {
                                if (!ObjectDefinition.aBooleanArray1490[inter.aByteArray263[var19]]) {
                                    if (inter.anIntArray310 != null) {
                                        inter.anIntArray310[var19] = 0;
                                    }
                                } else if (inter.anIntArray310 == null || Class44.anInt719 >= inter.anIntArray310[var19]) {
                                    byte var20 = inter.aByteArray231[var19];
                                    if (var20 == 0 || ((var20 & 2) == 0 || ObjectDefinition.aBooleanArray1490[86]) && ((var20 & 1) == 0 || ObjectDefinition.aBooleanArray1490[82]) && ((var20 & 4) == 0 || ObjectDefinition.aBooleanArray1490[81])) {
                                        Unsorted.method66(TextCore.aClass94_3672, -1, var19 + 1, (byte) -29, inter.componentHash);
                                        var21 = inter.anIntArray299[var19];
                                        if (inter.anIntArray310 == null) {
                                            inter.anIntArray310 = new int[inter.aByteArray263.length];
                                        }

                                        if (var21 == 0) {
                                            inter.anIntArray310[var19] = Integer.MAX_VALUE;
                                        } else {
                                            inter.anIntArray310[var19] = Class44.anInt719 + var21;
                                        }
                                    }
                                }
                            }
                        }

                        if (var18) {
                            InterfaceWidget.a(Class38_Sub1.anInt2614 - var11, Class163_Sub1.anInt2993 - var10, 97, inter);
                        }

                        if (Class56.aClass11_886 != null && Class56.aClass11_886 != inter && var26 && (method44(inter).method98() || (inter.componentHash == 49938505 && Class56.aClass11_886.componentHash == 49938505))) {
                            Class27.aClass11_526 = inter;
                        }
                        if (inter == PacketParser.aClass11_88) {
                            Class85.aBoolean1167 = true;
                            Class3_Sub13_Sub13.anInt3156 = var10;
                            Class134.anInt1761 = var11;
                        }

                        if (inter.aBoolean195 || inter.anInt189 != 0) {
                            CS2Script var30;
                            if (var26 && Class29.anInt561 != 0 && inter.anObjectArray183 != null) {
                                var30 = new CS2Script();
                                var30.aBoolean2446 = true;
                                var30.aClass11_2449 = inter;
                                var30.scrollbarScrollAmount = Class29.anInt561;
                                var30.arguments = inter.anObjectArray183;
                                aClass61_1471.method1215(var30);
                            }

                            if (Class56.aClass11_886 != null || Class67.aClass11_1017 != null || Class38_Sub1.aBoolean2615 || inter.anInt189 != 1400 && Class3_Sub19.anInt2475 > 0) {
                                var18 = false;
                                var25 = false;
                                var26 = false;
                            }

                            int var29;
                            if (inter.anInt189 != 0) {
                                if (inter.anInt189 == 1337) {
                                    Class168.aClass11_2091 = inter;
                                    Class20.method909(inter);
                                    continue;
                                }

                                if (inter.anInt189 == 1338) {
                                    if (var18) {
                                        Class1.anInt56 = Class163_Sub1.anInt2993 - var10;
                                        Class58.anInt916 = Class38_Sub1.anInt2614 - var11;
                                    }
                                    continue;
                                }

                                if (inter.anInt189 == 1400) {
                                    Class3_Sub28_Sub3.aClass11_3551 = inter;
                                    if (var18) {
                                        if (ObjectDefinition.aBooleanArray1490[82] && Class3_Sub13_Sub26.rights > 0) {
                                            var19 = (int) ((double) (Class163_Sub1.anInt2993 - var10 - inter.width / 2) * 2.0D / (double) Class44.aFloat727);
                                            var29 = (int) ((double) (Class38_Sub1.anInt2614 - var11 - inter.height / 2) * 2.0D / (double) Class44.aFloat727);
                                            var21 = Class3_Sub28_Sub1.anInt3536 + var19;
                                            int var32 = Class3_Sub4.anInt2251 + var29;
                                            int var23 = var21 + Class3_Sub13_Sub21.anInt3256;
                                            int var24 = Class108.anInt1460 - 1 - var32 + Unsorted.anInt65;
                                            Class30.method979(var23, var24, 0);
                                            Class3_Sub13_Sub19.method264((byte) 126);
                                            continue;
                                        }

                                        Class3_Sub19.anInt2475 = 1;
                                        Unsorted.anInt1881 = Class126.anInt1676;
                                        Class95.anInt1336 = Unsorted.anInt1709;
                                        continue;
                                    }

                                    if (var25 && Class3_Sub19.anInt2475 > 0) {
                                        if (Class3_Sub19.anInt2475 == 1 && (Unsorted.anInt1881 != Class126.anInt1676 || Class95.anInt1336 != Unsorted.anInt1709)) {
                                            Unsorted.anInt4073 = Class3_Sub28_Sub1.anInt3536;
                                            Class38.anInt660 = Class3_Sub4.anInt2251;
                                            Class3_Sub19.anInt2475 = 2;
                                        }

                                        if (Class3_Sub19.anInt2475 == 2) {
                                            method1175(Unsorted.anInt4073 + (int) ((double) (Unsorted.anInt1881 - Class126.anInt1676) * 2.0D / (double) NPC.aFloat3979));
                                            Class3_Sub13_Sub39.method354(Class38.anInt660 + (int) ((double) (Class95.anInt1336 - Unsorted.anInt1709) * 2.0D / (double) NPC.aFloat3979));
                                        }
                                        continue;
                                    }

                                    Class3_Sub19.anInt2475 = 0;
                                    continue;
                                }

                                if (inter.anInt189 == 1401) {
                                    if (var25) {
                                        Class3_Sub13_Sub17.method253(inter.width, Unsorted.anInt1709 - var11, Class126.anInt1676 - var10, inter.height);
                                    }
                                    continue;
                                }

                                if (inter.anInt189 == 1402) {
                                    if (!HDToolKit.highDetail) {
                                        Class20.method909(inter);
                                    }
                                    continue;
                                }
                            }

                            if (!inter.aBoolean188 && var18) {
                                inter.aBoolean188 = true;
                                if (inter.anObjectArray165 != null) {
                                    var30 = new CS2Script();
                                    var30.aBoolean2446 = true;
                                    var30.aClass11_2449 = inter;
                                    var30.worldSelectCursorPositionX = Class163_Sub1.anInt2993 - var10;
                                    var30.scrollbarScrollAmount = Class38_Sub1.anInt2614 - var11;
                                    var30.arguments = inter.anObjectArray165;
                                    aClass61_1471.method1215(var30);
                                }
                            }

                            if (inter.aBoolean188 && var25 && inter.anObjectArray170 != null) {
                                var30 = new CS2Script();
                                var30.aBoolean2446 = true;
                                var30.aClass11_2449 = inter;
                                var30.worldSelectCursorPositionX = Class126.anInt1676 - var10;
                                var30.scrollbarScrollAmount = Unsorted.anInt1709 - var11;
                                var30.arguments = inter.anObjectArray170;
                                aClass61_1471.method1215(var30);
                            }

                            if (inter.aBoolean188 && !var25) {
                                inter.aBoolean188 = false;
                                if (inter.anObjectArray239 != null) {
                                    var30 = new CS2Script();
                                    var30.aBoolean2446 = true;
                                    var30.aClass11_2449 = inter;
                                    var30.worldSelectCursorPositionX = Class126.anInt1676 - var10;
                                    var30.scrollbarScrollAmount = Unsorted.anInt1709 - var11;
                                    var30.arguments = inter.anObjectArray239;
                                    Class65.aClass61_983.method1215(var30);
                                }
                            }

                            if (var25 && inter.anObjectArray180 != null) {
                                var30 = new CS2Script();
                                var30.aBoolean2446 = true;
                                var30.aClass11_2449 = inter;
                                var30.worldSelectCursorPositionX = Class126.anInt1676 - var10;
                                var30.scrollbarScrollAmount = Unsorted.anInt1709 - var11;
                                var30.arguments = inter.anObjectArray180;
                                aClass61_1471.method1215(var30);
                            }

                            if (!inter.aBoolean163 && var26) {
                                inter.aBoolean163 = true;
                                if (inter.anObjectArray248 != null) {
                                    var30 = new CS2Script();
                                    var30.aBoolean2446 = true;
                                    var30.aClass11_2449 = inter;
                                    var30.worldSelectCursorPositionX = Class126.anInt1676 - var10;
                                    var30.scrollbarScrollAmount = Unsorted.anInt1709 - var11;
                                    var30.arguments = inter.anObjectArray248;
                                    aClass61_1471.method1215(var30);
                                }
                            }

                            if (inter.aBoolean163 && var26 && inter.anObjectArray276 != null) {
                                var30 = new CS2Script();
                                var30.aBoolean2446 = true;
                                var30.aClass11_2449 = inter;
                                var30.worldSelectCursorPositionX = Class126.anInt1676 - var10;
                                var30.scrollbarScrollAmount = Unsorted.anInt1709 - var11;
                                var30.arguments = inter.anObjectArray276;
                                aClass61_1471.method1215(var30);
                            }

                            if (inter.aBoolean163 && !var26) {
                                inter.aBoolean163 = false;
                                if (inter.anObjectArray281 != null) {
                                    var30 = new CS2Script();
                                    var30.aBoolean2446 = true;
                                    var30.aClass11_2449 = inter;
                                    var30.worldSelectCursorPositionX = Class126.anInt1676 - var10;
                                    var30.scrollbarScrollAmount = Unsorted.anInt1709 - var11;
                                    var30.arguments = inter.anObjectArray281;
                                    Class65.aClass61_983.method1215(var30);
                                }
                            }

                            if (inter.anObjectArray269 != null) {
                                var30 = new CS2Script();
                                var30.aClass11_2449 = inter;
                                var30.arguments = inter.anObjectArray269;
                                PacketParser.aClass61_82.method1215(var30);
                            }

                            CS2Script var22;
                            if (inter.anObjectArray161 != null && PacketParser.anInt87 > inter.anInt284) {
                                if (inter.anIntArray211 != null && PacketParser.anInt87 - inter.anInt284 <= 32) {
                                    label531:
                                    for (var19 = inter.anInt284; var19 < PacketParser.anInt87; ++var19) {
                                        var29 = NPC.anIntArray3986[var19 & 31];

                                        for (var21 = 0; var21 < inter.anIntArray211.length; ++var21) {
                                            if (inter.anIntArray211[var21] == var29) {
                                                var22 = new CS2Script();
                                                var22.aClass11_2449 = inter;
                                                var22.arguments = inter.anObjectArray161;
                                                aClass61_1471.method1215(var22);
                                                break label531;
                                            }
                                        }
                                    }
                                } else {
                                    var30 = new CS2Script();
                                    var30.aClass11_2449 = inter;
                                    var30.arguments = inter.anObjectArray161;
                                    aClass61_1471.method1215(var30);
                                }

                                inter.anInt284 = PacketParser.anInt87;
                            }

                            if (inter.anObjectArray221 != null && anInt2317 > inter.anInt242) {
                                if (inter.anIntArray185 != null && anInt2317 - inter.anInt242 <= 32) {
                                    label512:
                                    for (var19 = inter.anInt242; var19 < anInt2317; ++var19) {
                                        var29 = Class163_Sub2_Sub1.anIntArray4025[var19 & 31];

                                        for (var21 = 0; var21 < inter.anIntArray185.length; ++var21) {
                                            if (inter.anIntArray185[var21] == var29) {
                                                var22 = new CS2Script();
                                                var22.aClass11_2449 = inter;
                                                var22.arguments = inter.anObjectArray221;
                                                aClass61_1471.method1215(var22);
                                                break label512;
                                            }
                                        }
                                    }
                                } else {
                                    var30 = new CS2Script();
                                    var30.aClass11_2449 = inter;
                                    var30.arguments = inter.anObjectArray221;
                                    aClass61_1471.method1215(var30);
                                }

                                inter.anInt242 = anInt2317;
                            }

                            if (inter.anObjectArray282 != null && Class36.anInt641 > inter.anInt213) {
                                if (inter.anIntArray286 != null && Class36.anInt641 - inter.anInt213 <= 32) {
                                    label493:
                                    for (var19 = inter.anInt213; var19 < Class36.anInt641; ++var19) {
                                        var29 = Class44.anIntArray726[var19 & 31];
                                        System.out.println("Var29 " + var29);

                                        for (var21 = 0; var21 < inter.anIntArray286.length; ++var21) {
                                            if (inter.anIntArray286[var21] == var29) {
                                                var22 = new CS2Script();
                                                var22.aClass11_2449 = inter;
                                                var22.arguments = inter.anObjectArray282;
                                                aClass61_1471.method1215(var22);
                                                break label493;
                                            }
                                        }
                                    }
                                } else {
                                    var30 = new CS2Script();
                                    var30.aClass11_2449 = inter;
                                    var30.arguments = inter.anObjectArray282;
                                    aClass61_1471.method1215(var30);
                                }

                                inter.anInt213 = Class36.anInt641;
                            }

                            if (inter.anObjectArray174 != null && Unsorted.anInt944 > inter.anInt255) {
                                if (inter.anIntArray175 != null && Unsorted.anInt944 - inter.anInt255 <= 32) {
                                    label474:
                                    for (var19 = inter.anInt255; var19 < Unsorted.anInt944; ++var19) {
                                        var29 = Class3_Sub28_Sub4.anIntArray3565[var19 & 31];

                                        for (var21 = 0; var21 < inter.anIntArray175.length; ++var21) {
                                            if (inter.anIntArray175[var21] == var29) {
                                                var22 = new CS2Script();
                                                var22.aClass11_2449 = inter;
                                                var22.arguments = inter.anObjectArray174;
                                                aClass61_1471.method1215(var22);
                                                break label474;
                                            }
                                        }
                                    }
                                } else {
                                    var30 = new CS2Script();
                                    var30.aClass11_2449 = inter;
                                    var30.arguments = inter.anObjectArray174;
                                    aClass61_1471.method1215(var30);
                                }

                                inter.anInt255 = Unsorted.anInt944;
                            }

                            if (inter.anObjectArray158 != null && Class49.anInt815 > inter.anInt311) {
                                if (inter.anIntArray274 != null && Class49.anInt815 - inter.anInt311 <= 32) {
                                    label455:
                                    for (var19 = inter.anInt311; var19 < Class49.anInt815; ++var19) {
                                        var29 = anIntArray3780[var19 & 31];

                                        for (var21 = 0; var21 < inter.anIntArray274.length; ++var21) {
                                            if (inter.anIntArray274[var21] == var29) {
                                                var22 = new CS2Script();
                                                var22.aClass11_2449 = inter;
                                                var22.arguments = inter.anObjectArray158;
                                                aClass61_1471.method1215(var22);
                                                break label455;
                                            }
                                        }
                                    }
                                } else {
                                    var30 = new CS2Script();
                                    var30.aClass11_2449 = inter;
                                    var30.arguments = inter.anObjectArray158;
                                    aClass61_1471.method1215(var30);
                                }

                                inter.anInt311 = Class49.anInt815;
                            }

                            if (Class24.anInt472 > inter.anInt234 && inter.anObjectArray256 != null) {
                                var30 = new CS2Script();
                                var30.aClass11_2449 = inter;
                                var30.arguments = inter.anObjectArray256;
                                aClass61_1471.method1215(var30);
                            }

                            if (Class110.anInt1472 > inter.anInt234 && inter.anObjectArray156 != null) {
                                var30 = new CS2Script();
                                var30.aClass11_2449 = inter;
                                var30.arguments = inter.anObjectArray156;
                                aClass61_1471.method1215(var30);
                            }

                            if (Class167.anInt2087 > inter.anInt234 && inter.anObjectArray313 != null) {
                                var30 = new CS2Script();
                                var30.aClass11_2449 = inter;
                                var30.arguments = inter.anObjectArray313;
                                aClass61_1471.method1215(var30);
                            }

                            if (Class121.anInt1642 > inter.anInt234 && inter.anObjectArray268 != null) {
                                var30 = new CS2Script();
                                var30.aClass11_2449 = inter;
                                var30.arguments = inter.anObjectArray268;
                                aClass61_1471.method1215(var30);
                            }

                            if (Class140_Sub6.anInt2905 > inter.anInt234 && inter.anObjectArray315 != null) {
                                var30 = new CS2Script();
                                var30.aClass11_2449 = inter;
                                var30.arguments = inter.anObjectArray315;
                                aClass61_1471.method1215(var30);
                            }

                            inter.anInt234 = PacketParser.anInt3213;
                            if (inter.anObjectArray220 != null) {
                                for (var19 = 0; var19 < Class3_Sub23.anInt2537; ++var19) {
                                    CS2Script var31 = new CS2Script();
                                    var31.aClass11_2449 = inter;
                                    var31.inputTextCode = Class133.inputTextCodeArray[var19];
                                    var31.anInt2443 = Class120.anIntArray1638[var19];
                                    var31.arguments = inter.anObjectArray220;
                                    aClass61_1471.method1215(var31);
                                }
                            }

                            if (Class3_Sub28_Sub1.aBoolean3531 && inter.anObjectArray217 != null) {
                                var30 = new CS2Script();
                                var30.aClass11_2449 = inter;
                                var30.arguments = inter.anObjectArray217;
                                aClass61_1471.method1215(var30);
                            }
                        }
                    }

                    if (!inter.usingScripts && Class56.aClass11_886 == null && Class67.aClass11_1017 == null && !Class38_Sub1.aBoolean2615) {
                        if ((inter.anInt212 >= 0 || inter.anInt228 != 0) && Class126.anInt1676 >= var12 && Unsorted.anInt1709 >= var13 && Class126.anInt1676 < var14 && Unsorted.anInt1709 < var15) {
                            if (inter.anInt212 >= 0) {
                                Class107.aClass11_1453 = interfaces[inter.anInt212];
                            } else {
                                Class107.aClass11_1453 = inter;
                            }
                        }

                        if (inter.type == 8 && Class126.anInt1676 >= var12 && Unsorted.anInt1709 >= var13 && Class126.anInt1676 < var14 && Unsorted.anInt1709 < var15) {
                            Class20.aClass11_439 = inter;
                        }

                        if (inter.anInt252 > inter.height) {
                            Class137.method1819(Unsorted.anInt1709, inter.height, inter, Class126.anInt1676, var10 + inter.width, var11, inter.anInt252);
                        }
                    }

                    if (inter.type == 0) {
                        handleItemSwitch(interfaces, inter.componentHash, var12, var13, var14, var15, var10 - inter.anInt247, var11 - inter.anInt208);
                        if (inter.aClass11Array262 != null) {
                            handleItemSwitch(inter.aClass11Array262, inter.componentHash, var12, var13, var14, var15, var10 - inter.anInt247, var11 - inter.anInt208);
                        }

                        Class3_Sub31 var28 = (Class3_Sub31) Class3_Sub13_Sub17.aHashTable_3208.get((long) inter.componentHash);
                        if (var28 != null) {
                            GraphicDefinition.method967(var10, var13, var11, var14, var28.anInt2602, var12, var15);
                        }
                    }
                }
            }
        }

    }

    static boolean method51(RSInterface var0) {
        if (ClientCommands.commandQaOpEnabled) {
            if (method44(var0).anInt2205 != 0) {
                return false;
            }

            if (var0.type == 0) {
                return false;
            }
        }

        return var0.hidden;
    }

    private void method52(int var1) {
        try {
            if (!Unsorted.aBoolean2146) {
                while (Unsorted.method591(107)) {
                    if (Class3_Sub13_Sub27.anInt3342 == 115 || Class3_Sub13_Sub27.anInt3342 == 83) {
                        Unsorted.aBoolean2146 = true;
                    }
                }
            }

            if (var1 >= 46) {
                int var3;
                if (anInt1354 == 0) {
                    Runtime var10 = Runtime.getRuntime();
                    var3 = (int) ((var10.totalMemory() - var10.freeMemory()) / 1024L);
                    long var4 = TimeUtils.time();
                    if (aLong3296 == 0) {
                        aLong3296 = var4;
                    }

                    if (var3 > 16384 && 5000L > -aLong3296 + var4) {
                        if (-aLong1310 + var4 > 1000L) {
                            System.gc();
                            aLong1310 = var4;
                        }

                        LoadingStageNumber = 5;
                        Class3_Sub17.aClass94_2464 = TextCore.AllocatingMemory;
                    } else {
                        Class3_Sub17.aClass94_2464 = TextCore.AllocatedMemory;
                        anInt1354 = 10;
                        LoadingStageNumber = 5;
                    }
                } else {
                    int var2;
                    if (anInt1354 == 10) {
                        Class68.method1267();

                        for (var2 = 0; var2 < 4; ++var2) {
                            AtmosphereParser.aClass91Array1182[var2] = new Class91();
                        }

                        LoadingStageNumber = 10;
                        anInt1354 = 30;
                        Class3_Sub17.aClass94_2464 = TextCore.CreatedWorld;
                    } else if (anInt1354 == 30) {
                        if (Unsorted.aClass8_1936 == null) {
                            Unsorted.aClass8_1936 = new Class8(Class58.aJs5Worker_917, Class3_Sub13_Sub14.aCacheResourceWorker_3159);
                        }

                        if (Unsorted.aClass8_1936.method837()) {
                            CacheIndex.skeletonsIndex = Class8.getCacheIndex(false, true, true, 0);
                            CacheIndex.skinsIndex = Class8.getCacheIndex(false, true, true, 1);
                            CacheIndex.configurationsIndex = Class8.getCacheIndex(true, true, false, 2);
                            CacheIndex.interfacesIndex = Class8.getCacheIndex(false, true, true, 3);
                            CacheIndex.soundFXIndex = Class8.getCacheIndex(false, true, true, 4);
                            CacheIndex.landscapesIndex = Class8.getCacheIndex(true, true, true, 5);
                            CacheIndex.musicIndex = Class8.getCacheIndex(true, false, true, 6);
                            CacheIndex.modelsIndex = Class8.getCacheIndex(false, true, true, 7);
                            CacheIndex.spritesIndex = Class8.getCacheIndex(false, true, true, 8);
                            CacheIndex.texturesIndex = Class8.getCacheIndex(false, true, true, 9);
                            CacheIndex.huffmanEncodingIndex = Class8.getCacheIndex(false, true, true, 10);
                            CacheIndex.music2Index = Class8.getCacheIndex(false, true, true, 11);
                            CacheIndex.interfaceScriptsIndex = Class8.getCacheIndex(false, true, true, 12);
                            CacheIndex.fontsIndex = Class8.getCacheIndex(false, true, true, 13);
                            CacheIndex.soundFX2Index = Class8.getCacheIndex(false, false, true, 14);
                            CacheIndex.soundFX3Index = Class8.getCacheIndex(false, true, true, 15);
                            CacheIndex.objectConfigIndex = Class8.getCacheIndex(false, true, true, 16);
                            CacheIndex.clientscriptMaskIndex = Class8.getCacheIndex(false, true, true, 17);
                            CacheIndex.npcConfigIndex = Class8.getCacheIndex(false, true, true, 18);
                            CacheIndex.itemConfigIndex = Class8.getCacheIndex(false, true, true, 19);
                            CacheIndex.animationIndex = Class8.getCacheIndex(false, true, true, 20);
                            CacheIndex.graphicFXIndex = Class8.getCacheIndex(false, true, true, 21);
                            CacheIndex.clientScriptConfigIndex = Class8.getCacheIndex(false, true, true, 22);
                            CacheIndex.worldmapIndex = Class8.getCacheIndex(true, true, true, 23);
                            CacheIndex.quickchatMessagesIndex = Class8.getCacheIndex(false, true, true, 24);
                            CacheIndex.quickchatMenusIndex = Class8.getCacheIndex(false, true, true, 25);
                            CacheIndex.materialsIndex = Class8.getCacheIndex(true, true, true, 26);
                            CacheIndex.particlesConfigIndex = Class8.getCacheIndex(false, true, true, 27);
                            CacheIndex.libIndex = Class8.getCacheIndex(false, true, true, 28);
                            LoadingStageNumber = 15;
                            Class3_Sub17.aClass94_2464 = TextCore.ConxUpdateServer;
                            anInt1354 = 40;
                        } else {
                            Class3_Sub17.aClass94_2464 = TextCore.LoadingConnecting;
                            LoadingStageNumber = 12;
                        }
                    } else if (anInt1354 == 40) {
                        var2 = 0;

                        for (var3 = 0; var3 < 29; ++var3) { //TODO
                            var2 += Unsorted.aClass151_Sub1Array2601[var3].method2111() * anIntArray3288[var3] / 100;
                        }

                        if (var2 < 100) { //!= 100
                            if (var2 != 0) {
                                Class3_Sub17.aClass94_2464 = RSString.stringCombiner(new RSString[]{TextCore.CheckingForUpdates, RSString.stringAnimator(var2), TextCore.aClass94_468});
                            }

                            LoadingStageNumber = 20;
                        } else {
                            LoadingStageNumber = 20;
                            Class3_Sub17.aClass94_2464 = TextCore.LoadedUpdateList;
                            Sprites.getSpriteFromArchive(CacheIndex.spritesIndex);
                            Class97.method1593(111, CacheIndex.spritesIndex);
                            Class3_Sub13_Sub13.method233(28280, CacheIndex.spritesIndex);
                            anInt1354 = 45;
                        }
                    } else if (anInt1354 == 45) {
                        Class140_Sub3.method1959(Class3_Sub13_Sub15.aBoolean3184);
                        aClass3_Sub24_Sub4_1193 = new Class3_Sub24_Sub4();
                        aClass3_Sub24_Sub4_1193.method479();
                        WorldListEntry.aClass155_2627 = Class58.method1195(22050, Class38.aClass87_665, GameShell.canvas, 0);
                        WorldListEntry.aClass155_2627.method2154(-116, aClass3_Sub24_Sub4_1193);
                        RenderAnimationDefinition.method897(aClass3_Sub24_Sub4_1193, CacheIndex.soundFX3Index, CacheIndex.soundFX2Index, CacheIndex.soundFXIndex);
                        Class3_Sub21.aClass155_2491 = Class58.method1195(2048, Class38.aClass87_665, GameShell.canvas, 1);
                        Class3_Sub26.aClass3_Sub24_Sub2_2563 = new Class3_Sub24_Sub2();
                        Class3_Sub21.aClass155_2491.method2154(-128, Class3_Sub26.aClass3_Sub24_Sub2_2563);
                        Class27.aClass157_524 = new Class157(22050, Class21.anInt443);
                        KeyboardListener.loginThemeSongArchiveID = CacheIndex.musicIndex.getArchiveForName(RSString.parse(GameConfig.LOGIN_THEME));
                        LoadingStageNumber = 30;
                        anInt1354 = 50;
                        Class3_Sub17.aClass94_2464 = TextCore.PreparedSoundEngine;
                    } else if (anInt1354 == 50) {
                        var2 = Sprites.method228(CacheIndex.spritesIndex, CacheIndex.fontsIndex);
                        var3 = 6;
                        if (var3 <= var2) {
                            Class3_Sub17.aClass94_2464 = TextCore.LoadedFonts;
                            LoadingStageNumber = 35;
                            anInt1354 = 60;
                        } else {
                            Class3_Sub17.aClass94_2464 = RSString.stringCombiner(new RSString[]{TextCore.LoadingFonts, RSString.stringAnimator(100 * var2 / var3), TextCore.aClass94_468});
                            LoadingStageNumber = 35;
                        }
                    } else if (60 == anInt1354) {
                        var2 = Unsorted.method599(CacheIndex.spritesIndex);
                        var3 = 2;
                        if (var3 <= var2) {
                            Class3_Sub17.aClass94_2464 = TextCore.LoadedTitleScreen;
                            anInt1354 = 65;
                        } else {
                            Class3_Sub17.aClass94_2464 = RSString.stringCombiner(new RSString[]{TextCore.LoadingTitleScreen, RSString.stringAnimator(100 * var2 / var3), TextCore.aClass94_468});
                        }
                        LoadingStageNumber = 40;
                    } else if (anInt1354 == 65) {
                        Class3_Sub28_Sub9.method581(CacheIndex.fontsIndex, CacheIndex.spritesIndex);
                        LoadingStageNumber = 45;
                        Class3_Sub17.aClass94_2464 = TextCore.OpenedTitleScreen;
                        Class117.method1719(5);
                        anInt1354 = 70;
                    } else if (anInt1354 == 70) {
                        CacheIndex.configurationsIndex.method2113();
                        byte var7 = 0;
                        var2 = var7 + CacheIndex.configurationsIndex.method2136((byte) -124);
                        CacheIndex.objectConfigIndex.method2113();
                        var2 += CacheIndex.objectConfigIndex.method2136((byte) -128);
                        CacheIndex.libIndex.method2113();
                        var2 += CacheIndex.libIndex.method2136((byte) -124);
                        CacheIndex.clientscriptMaskIndex.method2113();
                        var2 += CacheIndex.clientscriptMaskIndex.method2136((byte) -123);
                        CacheIndex.npcConfigIndex.method2113();
                        var2 += CacheIndex.npcConfigIndex.method2136((byte) -127);
                        CacheIndex.itemConfigIndex.method2113();
                        var2 += CacheIndex.itemConfigIndex.method2136((byte) -128);
                        CacheIndex.animationIndex.method2113();
                        var2 += CacheIndex.animationIndex.method2136((byte) -122);
                        CacheIndex.graphicFXIndex.method2113();
                        var2 += CacheIndex.graphicFXIndex.method2136((byte) -122);
                        CacheIndex.clientScriptConfigIndex.method2113();
                        var2 += CacheIndex.clientScriptConfigIndex.method2136((byte) -128);
                        CacheIndex.quickchatMessagesIndex.method2113();
                        var2 += CacheIndex.quickchatMessagesIndex.method2136((byte) -126);
                        CacheIndex.quickchatMenusIndex.method2113();
                        var2 += CacheIndex.quickchatMenusIndex.method2136((byte) -128);
                        CacheIndex.particlesConfigIndex.method2113();
                        var2 += CacheIndex.particlesConfigIndex.method2136((byte) -124);
                        if (var2 >= 1100) {
                            Class132.method1799((byte) 96, CacheIndex.configurationsIndex);
                            method631(CacheIndex.configurationsIndex);
                            Class3_Sub28_Sub8.method575(CacheIndex.configurationsIndex, -1);
                            method375(CacheIndex.modelsIndex, CacheIndex.configurationsIndex);
                            Class108.method1661(CacheIndex.objectConfigIndex, CacheIndex.modelsIndex);
                            ItemDefinition.method1103(CacheIndex.modelsIndex, CacheIndex.npcConfigIndex);
                            GameObject.method1864(CacheIndex.itemConfigIndex, Class157.aClass3_Sub28_Sub17_Sub1_2000, CacheIndex.modelsIndex);
                            StructDefinitionProvider.setIndex(CacheIndex.configurationsIndex);
                            Class3_Sub20.method392(CacheIndex.skinsIndex, CacheIndex.animationIndex, CacheIndex.skeletonsIndex);
                            Unsorted.method1053(CacheIndex.configurationsIndex);
                            Class158.method2180(CacheIndex.modelsIndex, CacheIndex.graphicFXIndex);
                            Class107.method1648(CacheIndex.clientScriptConfigIndex);
                            LinkableRSString.method731(CacheIndex.configurationsIndex);
                            Unsorted.method89(CacheIndex.fontsIndex, CacheIndex.spritesIndex, CacheIndex.interfacesIndex, CacheIndex.modelsIndex);
                            Class3_Sub13_Sub17.method250(CacheIndex.configurationsIndex);
                            EnumDefinitionProvider.setIndex(CacheIndex.clientscriptMaskIndex);
                            Class3_Sub13_Sub8.method205(CacheIndex.quickchatMenusIndex, CacheIndex.quickchatMessagesIndex, new Class7());
                            Class65.method1236(CacheIndex.quickchatMenusIndex, CacheIndex.quickchatMessagesIndex);
                            Class58.method1197(CacheIndex.configurationsIndex);
                            Unsorted.method2065(CacheIndex.configurationsIndex, CacheIndex.spritesIndex);
                            Class107.method1645(CacheIndex.configurationsIndex, CacheIndex.spritesIndex);
                            LoadingStageNumber = 50;
                            Class3_Sub17.aClass94_2464 = TextCore.LoadedConfig;
                            Class29.method968(128);
                            anInt1354 = 80;
                        } else {
                            Class3_Sub17.aClass94_2464 = RSString.stringCombiner(new RSString[]{TextCore.LoadingConfig, RSString.stringAnimator(var2 / 11), TextCore.aClass94_468});
                            LoadingStageNumber = 50;
                        }
                    } else if (anInt1354 == 80) {
                        var2 = Sprites.method107(CacheIndex.spritesIndex);
                        var3 = 15;
                        if (var2 < var3) {
                            Class3_Sub17.aClass94_2464 = RSString.stringCombiner(new RSString[]{TextCore.LoadingSprites, RSString.stringAnimator(var2 * 100 / var3), TextCore.aClass94_468});
                            LoadingStageNumber = 60;
                        } else {
                            Sprites.method887(CacheIndex.spritesIndex);
                            anInt1354 = 90;
                            LoadingStageNumber = 60;
                            Class3_Sub17.aClass94_2464 = TextCore.LoadedSprites;
                        }
                    } else if (anInt1354 != 90) {
                        if (anInt1354 == 100) {
                            if (Class3_Sub13_Sub34.method334(CacheIndex.spritesIndex)) {
                                anInt1354 = 110;
                            }
                        } else if (anInt1354 == 110) {
                            Class3_Sub13_Sub13.aClass67_1443 = new Class67();
                            Class38.aClass87_665.method1451(10, Class3_Sub13_Sub13.aClass67_1443);
                            Class3_Sub17.aClass94_2464 = TextCore.LoadedInputHandler;
                            LoadingStageNumber = 75;
                            anInt1354 = 120;
                        } else if (anInt1354 != 120) {
                            if (anInt1354 == 130) {
                                if (CacheIndex.interfacesIndex.method2113()) {
                                    if (CacheIndex.interfaceScriptsIndex.method2113()) {
                                        if (CacheIndex.fontsIndex.method2113()) {
                                            if (CacheIndex.worldmapIndex.method2127(TextCore.aClass94_1342)) {
                                                Class75_Sub4.method1353(Sprites.aClass3_Sub28_Sub16_Sub2Array2140, CacheIndex.worldmapIndex);
                                                LoadingStageNumber = 95;
                                                Class3_Sub17.aClass94_2464 = TextCore.LoadedInterfaces;
                                                anInt1354 = 135;
                                            } else {
                                                Class3_Sub17.aClass94_2464 = RSString.stringCombiner(new RSString[]{TextCore.LoadingInterfaces, RSString.stringAnimator(90 - -(CacheIndex.worldmapIndex.method2116(TextCore.aClass94_1342) / 10)), TextCore.aClass94_468});
                                                LoadingStageNumber = 85;
                                            }
                                        } else {
                                            Class3_Sub17.aClass94_2464 = RSString.stringCombiner(new RSString[]{TextCore.LoadingInterfaces, RSString.stringAnimator(85 - -(CacheIndex.fontsIndex.method2136((byte) -124) / 20)), TextCore.aClass94_468});
                                            LoadingStageNumber = 85;
                                        }
                                    } else {
                                        Class3_Sub17.aClass94_2464 = RSString.stringCombiner(new RSString[]{TextCore.LoadingInterfaces, RSString.stringAnimator(75 - -(CacheIndex.interfaceScriptsIndex.method2136((byte) -128) / 10)), TextCore.aClass94_468});
                                        LoadingStageNumber = 85;
                                    }
                                } else {
                                    Class3_Sub17.aClass94_2464 = RSString.stringCombiner(new RSString[]{TextCore.LoadingInterfaces, RSString.stringAnimator(CacheIndex.interfacesIndex.method2136((byte) -123) * 3 / 4), TextCore.aClass94_468});
                                    LoadingStageNumber = 85;
                                }
                            } else if (135 == anInt1354) {
                                var2 = Class121.method1735();
                                if (-1 == var2) {
                                    LoadingStageNumber = 95;
                                    Class3_Sub17.aClass94_2464 = TextCore.LoadingWLD;
                                } else if (var2 == 7 || var2 == 9) {
                                    this.method31("worldlistfull");
                                    Class117.method1719(1000);
                                } else if (Class30.loadedWorldList) {
                                    Class3_Sub17.aClass94_2464 = TextCore.LoadedWLD;
                                    anInt1354 = 140;
                                    LoadingStageNumber = 96;
                                } else {
                                    this.method31("worldlistio_" + var2);
                                    Class117.method1719(1000);
                                }
                            } else if (anInt1354 == 140) {
                                loginScreenInterfaceID = CacheIndex.interfacesIndex.getArchiveForName(RSString.parse("loginscreen"));
                                CacheIndex.landscapesIndex.method2115(-9, false);
                                CacheIndex.musicIndex.method2115(111, true);
                                CacheIndex.spritesIndex.method2115(-76, true);
                                CacheIndex.fontsIndex.method2115(91, true);
                                CacheIndex.huffmanEncodingIndex.method2115(-116, true);
                                CacheIndex.interfacesIndex.method2115(99, true);
                                LoadingStageNumber = 97;
                                Class3_Sub17.aClass94_2464 = TextCore.Starting3DLibrary;
                                anInt1354 = 150;
                                Class58.aBoolean913 = true;
                            } else if (anInt1354 == 150) {
                                Class88.method1454();
                                if (Unsorted.aBoolean2146) {
                                    Class3_Sub28_Sub9.anInt3622 = 0;
                                    Unsorted.anInt3671 = 0;
                                    Unsorted.anInt2577 = 0;
                                    Class3_Sub20.anInt2488 = 0;
                                }

                                Unsorted.aBoolean2146 = true;
                                Class119.method1730(Class38.aClass87_665);
                                GameObject.graphicsSettings(false, Unsorted.anInt2577, -1, -1);
                                LoadingStageNumber = 100;
                                anInt1354 = 160;
                                Class3_Sub17.aClass94_2464 = TextCore.Started3DLibrary;
                            } else if (anInt1354 == 160) {
                                Class3_Sub13_Sub11.method219(true);
                            }
                        } else if (CacheIndex.huffmanEncodingIndex.method2125(TextCore.aClass94_3672, TextCore.HasHuffman)) {
                            Class36 var9 = new Class36(CacheIndex.huffmanEncodingIndex.method2123(TextCore.aClass94_3672, TextCore.HasHuffman));
                            Class1.method69(var9);
                            Class3_Sub17.aClass94_2464 = TextCore.LoadedWordPack;
                            anInt1354 = 130;
                            LoadingStageNumber = 80;
                        } else {
                            Class3_Sub17.aClass94_2464 = RSString.stringCombiner(new RSString[]{TextCore.LoadingWordPack, TextCore.aClass94_37});
                            LoadingStageNumber = 80;
                        }
                    } else if (CacheIndex.materialsIndex.method2113()) {
                        Class102 var8 = new Class102(CacheIndex.texturesIndex, CacheIndex.materialsIndex, CacheIndex.spritesIndex, !Class25.aBoolean488);
                        Class51.method1140(var8);
                        if (Unsorted.anInt3625 == 1) {
                            Class51.method1137(0.9F);
                        }

                        if (2 == Unsorted.anInt3625) {
                            Class51.method1137(0.8F);
                        }

                        if (Unsorted.anInt3625 == 3) {
                            Class51.method1137(0.7F);
                        }

                        if (Unsorted.anInt3625 == 4) {
                            Class51.method1137(0.6F);
                        }

                        Class3_Sub17.aClass94_2464 = TextCore.LoadedTextures;
                        anInt1354 = 100;
                        LoadingStageNumber = 70;
                    } else {
                        Class3_Sub17.aClass94_2464 = RSString.stringCombiner(new RSString[]{TextCore.LoadingTextures, RSString.stringAnimator(CacheIndex.materialsIndex.method2136((byte) -125)), TextCore.aClass94_468});
                        LoadingStageNumber = 70;
                    }
                }
            }
        } catch (RuntimeException var6) {
            throw ClientErrorException.clientError(var6, "client.A(" + var1 + ')');
        }
    }

    final void method25() {
        try {
            if (Class143.loadingStage != 1000) {
                ++Class44.anInt719;
                if (Class44.anInt719 % 1000 == 1) {
                    GregorianCalendar var2 = new GregorianCalendar();
                    Class38_Sub1.anInt2618 = var2.get(Calendar.HOUR_OF_DAY) * 600 - (-(var2.get(Calendar.MINUTE) * 10) + -(var2.get(Calendar.SECOND) / 6));
                    Class24.aRandom3088.setSeed((long) Class38_Sub1.anInt2618);
                }

                this.method48();
                if (Unsorted.aClass8_1936 != null) {
                    Unsorted.aClass8_1936.method838();
                }

                LinkableRSString.method728();
                Class58.method1194();
                Class32.method996();
                Unsorted.method1225();
                if (HDToolKit.highDetail) {
                    Class31.method990();
                }

                int var4;
                if (Class38.aClass146_668 != null) {
                    var4 = Class38.aClass146_668.method2078();
                    Class29.anInt561 = var4;
                }

                if (Class143.loadingStage == 0) {
                    this.method52(48);
                    Class75_Sub4.method1355();
                } else if (Class143.loadingStage == 5) {
                    this.method52(107);
                    Class75_Sub4.method1355();
                } else if (Class143.loadingStage == 25 || Class143.loadingStage == 28) {
                    Class40.method1046();
                }

                if (10 == Class143.loadingStage) {
                    this.method47();
                    Class3_Sub13_Sub21.method267((byte) 36);
                    Class163_Sub1_Sub1.method2216();
                    LoginHandler.handleLogin();
                } else if (Class143.loadingStage == 30) {
                    Class3_Sub13_Sub13.method235();
                } else if (Class143.loadingStage == 40) {
                    LoginHandler.handleLogin();
                    if (Class158.anInt2005 != -3) {
                        if (Class158.anInt2005 == 15) {
                            Class21.method912();
                        } else if (Class158.anInt2005 != 2) {
                            Class167.method2269((byte) 46);
                        }
                    }
                }

            }
        } catch (RuntimeException var3) {
            throw ClientErrorException.clientError(var3, "client.N(" + (byte) 107 + ')');
        }
    }
}
