package org.runite.client;


import org.rs09.client.LinkableInt;

import javax.media.opengl.GL;


final class Class31 {

    private static final LinkedList A_LINKED_LIST___581 = new LinkedList();
    private static final LinkedList A_LINKED_LIST___586 = new LinkedList();
    private static final LinkedList A_LINKED_LIST___587 = new LinkedList();
    private static final LinkedList A_LINKED_LIST___588 = new LinkedList();
    private static final int[] anIntArray589 = new int[1000];
    static int anInt580 = 0;
    static int anInt582 = 0;
    static int memory2D = 0;
    static int anInt585 = 0;
    private static long aLong583 = 0L;

    static synchronized void method985(int var0, int var1, int var2) {
        if (var2 == anInt582) {
            LinkableInt var3 = new LinkableInt(var1);
            var3.linkableKey = var0;
            A_LINKED_LIST___587.method1215(var3);
        }
    }

    static synchronized void method986(int var0, int var1) {
        if (var1 == anInt582) {
            LinkableInt var2 = new LinkableInt();
            var2.linkableKey = var0;
            A_LINKED_LIST___588.method1215(var2);
        }
    }

    static synchronized void method988() {
        ++anInt582;
        A_LINKED_LIST___581.method1211(-110);
        A_LINKED_LIST___586.method1211(-88);
        A_LINKED_LIST___587.method1211(-123);
        A_LINKED_LIST___588.method1211(-115);
        anInt585 = 0;
        memory2D = 0;
        anInt580 = 0;
    }

    static synchronized void method989(int var0, int var1, int var2) {
        if (var2 == anInt582) {
            LinkableInt var3 = new LinkableInt(var1);
            var3.linkableKey = var0;
            A_LINKED_LIST___581.method1215(var3);
        }
    }

    static synchronized void method990() {
        GL var0 = HDToolKit.gl;
        int var1 = 0;

        while (true) {
            LinkableInt var2 = (LinkableInt) A_LINKED_LIST___581.method1220();
            if (var2 == null) {
                if (var1 > 0) {
                    var0.glDeleteBuffersARB(var1, anIntArray589, 0);
                    var1 = 0;
                }

                while (true) {
                    var2 = (LinkableInt) A_LINKED_LIST___586.method1220();
                    if (var2 == null) {
                        while (true) {
                            var2 = (LinkableInt) A_LINKED_LIST___587.method1220();
                            if (var2 == null) {
                                if (var1 > 0) {
                                    var0.glDeleteTextures(var1, anIntArray589, 0);
                                }

                                while (true) {
                                    var2 = (LinkableInt) A_LINKED_LIST___588.method1220();
                                    if (var2 == null) {
                                        if (anInt585 + memory2D + anInt580 > 100663296 && TimeUtils.time() > aLong583 + 60000L) {
                                            System.gc();
                                            aLong583 = TimeUtils.time();
                                        }

                                        return;
                                    }

                                    int var3 = (int) var2.linkableKey;
                                    var0.glDeleteLists(var3, 1);
                                }
                            }

                            anIntArray589[var1++] = (int) var2.linkableKey;
                            anInt580 -= var2.value;
                            if (var1 == 1000) {
                                var0.glDeleteTextures(var1, anIntArray589, 0);
                                var1 = 0;
                            }
                        }
                    }

                    anIntArray589[var1++] = (int) var2.linkableKey;
                    memory2D -= var2.value;
                    if (var1 == 1000) {
                        var0.glDeleteTextures(var1, anIntArray589, 0);
                        var1 = 0;
                    }
                }
            }

            anIntArray589[var1++] = (int) var2.linkableKey;
            anInt585 -= var2.value;
            if (var1 == 1000) {
                var0.glDeleteBuffersARB(var1, anIntArray589, 0);
                var1 = 0;
            }
        }
    }

    static synchronized void method991(int var0, int var1, int var2) {
        if (var2 == anInt582) {
            LinkableInt var3 = new LinkableInt(var1);
            var3.linkableKey = var0;
            A_LINKED_LIST___586.method1215(var3);
        }
    }

}
