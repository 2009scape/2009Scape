package org.runite.jagex;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

final class IOHandler implements Runnable {

    private int anInt1230 = 0;
    private OutputStream anOutputStream1231;
    private InputStream anInputStream1232;
    private byte[] aByteArray1233;
    static int anInt1234;
    private final Socket aSocket1235;
    private int anInt1236 = 0;
    private Class64 aClass64_1237;
    static Class33 aClass33_1238;
    private final Signlink aClass87_1239;
    private boolean aBoolean1241 = false;
    static Class61 aClass61_1242 = new Class61();
    private boolean aBoolean1243 = false;
    static int anInt1244 = -1;


    public final void run() {
        try {
            while(true) {
                try {
                    label88: {
                        int var1;
                        int var2;
                        synchronized(this) {
                            if(this.anInt1230 == this.anInt1236) {
                                if(this.aBoolean1241) {
                                    break label88;
                                }

                                try {
                                    this.wait();
                                } catch (InterruptedException var9) {
                                }
                            }

                            var2 = this.anInt1236;
                            if(this.anInt1230 < this.anInt1236) {
                                var1 = -this.anInt1236 + 5000;
                            } else {
                                var1 = this.anInt1230 - this.anInt1236;
                            }
                        }

                        if(0 >= var1) {
                            continue;
                        }

                        try {
                            this.anOutputStream1231.write(this.aByteArray1233, var2, var1);
                        } catch (IOException var8) {
                            this.aBoolean1243 = true;
                        }

                        this.anInt1236 = (var1 + this.anInt1236) % 5000;

                        try {
                            if(this.anInt1236 == this.anInt1230) {
                                this.anOutputStream1231.flush();
                            }
                        } catch (IOException var7) {
                            this.aBoolean1243 = true;
                        }
                        continue;
                    }

                    try {
                        if(null != this.anInputStream1232) {
                            this.anInputStream1232.close();
                        }

                        if(null != this.anOutputStream1231) {
                            this.anOutputStream1231.close();
                        }

                        if(this.aSocket1235 != null) {
                            this.aSocket1235.close();
                        }
                    } catch (IOException var6) {
                    }

                    this.aByteArray1233 = null;
                } catch (Exception var11) {
                    Class49.method1125((String)null, var11, (byte)103);
                }

                return;
            }
        } catch (RuntimeException var12) {
            throw Class44.clientError(var12, "ma.run()");
        }
    }

    static void method1460(int var0, int var1, int var3, int var4, int var5, int var6) {
        try {
            if(Class101.anInt1425 <= var5 - var4 && Class3_Sub28_Sub18.anInt3765 >= var5 - -var4 && Class159.anInt2020 <= -var4 + var1 && Class57.anInt902 >= var4 + var1) {
                Class3_Sub13_Sub2.method175(var6, var0, var1, var3, var4, var5);
            } else {
                Class168.method2275(var3, var1, var4, var6, var0, var5);
            }

        } catch (RuntimeException var8) {
            throw Class44.clientError(var8, "ma.A(" + var0 + ',' + var1 + ',' + (byte) -113 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ')');
        }
    }

    final void readBytes(int var1, int var2, byte[] var4) throws IOException {
        try {
            if(!this.aBoolean1241) {
                while(var2 > 0) {
                    int var5 = this.anInputStream1232.read(var4, var1, var2);
                    if(0 >= var5) {
                        throw new EOFException();
                    }

                    var1 += var5;
                    var2 -= var5;
                }

            }
        } catch (RuntimeException var6) {
            throw Class44.clientError(var6, "ma.D(" + var1 + ',' + var2 + ',' + -18455 + ',' + (var4 != null?"{...}":"null") + ')');
        }
    }

    final int readByte(int var1) throws IOException {
        try {
            if(var1 != 0) {
                this.close(43);
            }

            return !this.aBoolean1241?this.anInputStream1232.read():0;
        } catch (RuntimeException var3) {
            throw Class44.clientError(var3, "ma.E(" + var1 + ')');
        }
    }

    public static void method1463(int var0) {
        try {
            if(var0 != 0) {
                aClass61_1242 = (Class61)null;
            }

            aClass61_1242 = null;
            aClass33_1238 = null;
        } catch (RuntimeException var2) {
            throw Class44.clientError(var2, "ma.I(" + var0 + ')');
        }
    }

    final void sendBytes(byte[] var3, int var4) throws IOException {
        try {
            if(!this.aBoolean1241) {
                if(this.aBoolean1243) {
                    this.aBoolean1243 = false;
                    throw new IOException();
                } else {
                    if(this.aByteArray1233 == null) {
                        this.aByteArray1233 = new byte[5000];
                    }

                    synchronized(this) {
                        for(int var6 = 0; var6 < var4; ++var6) {
                            this.aByteArray1233[this.anInt1230] = var3[var6];
                            this.anInt1230 = (this.anInt1230 + 1) % 5000;
                            if((4900 + this.anInt1236) % 5000 == this.anInt1230) {
                                throw new IOException();
                            }
                        }

                        if(null == this.aClass64_1237) {
                            this.aClass64_1237 = this.aClass87_1239.method1451(0, 3, this);
                        }

                        this.notifyAll();
                    }
                }
            }
        } catch (RuntimeException var9) {
            throw Class44.clientError(var9, "ma.K(" + false + ',' + 0 + ',' + (var3 != null?"{...}":"null") + ',' + var4 + ')');
        }
    }

    protected final void finalize() {
        try {
            this.close(14821);
        } catch (RuntimeException var2) {
            throw Class44.clientError(var2, "ma.finalize()");
        }
    }

    final int availableBytes(int var1) throws IOException {
        try {
            if(var1 != -18358) {
                this.aClass64_1237 = (Class64)null;
            }

            return this.aBoolean1241?0:this.anInputStream1232.available();
        } catch (RuntimeException var3) {
            throw Class44.clientError(var3, "ma.B(" + var1 + ')');
        }
    }

    final void method1466() {
        try {
            if(!this.aBoolean1241) {
                if(this.aBoolean1243) {
                    this.aBoolean1243 = false;
//               throw new IOException();
                    System.err.println("new IOException(roar)");
                }
            }
        } catch (RuntimeException var3) {
            throw Class44.clientError(var3, "ma.G(" + 127 + ')');
        }
    }

    final void method1467() {
        try {

            if(!this.aBoolean1241) {
                this.anInputStream1232 = new InputStream_Sub1();
                this.anOutputStream1231 = new OutputStream_Sub1();
            }
        } catch (RuntimeException var3) {
            throw Class44.clientError(var3, "ma.H(" + false + ')');
        }
    }

    final void close(int var1) {
        try {
            if(var1 != 14821) {
                this.close(6);
            }
            if(!this.aBoolean1241) {
                synchronized(this) {
                    this.aBoolean1241 = true;
                    this.notifyAll();
                }

                if(this.aClass64_1237 != null) {
                    while(this.aClass64_1237.anInt978 == 0) {
                        Class3_Sub13_Sub34.method331(1L, 64);
                    }

                    if(this.aClass64_1237.anInt978 == 1) {
                        try {
                            ((Thread)this.aClass64_1237.anObject974).join();
                        } catch (InterruptedException var4) {
                        }
                    }
                }

                this.aClass64_1237 = null;
            }
        } catch (RuntimeException var6) {
            throw Class44.clientError(var6, "ma.J(" + var1 + ')');
        }
    }

    static void method1469(int[] var0, int var1, int var2, int var3, int var4, int var5) {
        Class3_Sub2 var6 = Class75_Sub2.aClass3_Sub2ArrayArrayArray2638[var3][var4][var5];
        if(var6 != null) {
            Class126 var7 = var6.aClass126_2240;
            int var9;
            if(var7 == null) {
                Class35 var8 = var6.aClass35_2226;
                if(var8 != null) {
                    var9 = var8.anInt611;
                    int var10 = var8.anInt612;
                    int var11 = var8.anInt626;
                    int var12 = var8.anInt621;
                    int[] var13 = Class3_Sub13_Sub18.anIntArrayArray3215[var9];
                    int[] var14 = Class162.anIntArrayArray2039[var10];
                    int var15 = 0;
                    int var16;
                    if(var11 == 0) {
                        for(var16 = 0; var16 < 4; ++var16) {
                            if(var13[var14[var15++]] != 0) {
                                var0[var1] = var12;
                            }

                            if(var13[var14[var15++]] != 0) {
                                var0[var1 + 1] = var12;
                            }

                            if(var13[var14[var15++]] != 0) {
                                var0[var1 + 2] = var12;
                            }

                            if(var13[var14[var15++]] != 0) {
                                var0[var1 + 3] = var12;
                            }

                            var1 += var2;
                        }
                    } else {
                        for(var16 = 0; var16 < 4; ++var16) {
                            var0[var1] = var13[var14[var15++]] == 0?var11:var12;
                            var0[var1 + 1] = var13[var14[var15++]] == 0?var11:var12;
                            var0[var1 + 2] = var13[var14[var15++]] == 0?var11:var12;
                            var0[var1 + 3] = var13[var14[var15++]] == 0?var11:var12;
                            var1 += var2;
                        }
                    }

                }
            } else {
                int var17 = var7.anInt1673;
                if(var17 != 0) {
                    for(var9 = 0; var9 < 4; ++var9) {
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

    IOHandler(Socket var1, Signlink var2) throws IOException {
        try {
            this.aClass87_1239 = var2;
            this.aSocket1235 = var1;
            this.aSocket1235.setSoTimeout(30000);
            this.aSocket1235.setTcpNoDelay(true);
            this.anInputStream1232 = this.aSocket1235.getInputStream();
            this.anOutputStream1231 = this.aSocket1235.getOutputStream();
        } catch (RuntimeException var4) {
            throw Class44.clientError(var4, "ma.<init>(" + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ')');
        }
    }

    static void method1470(int var0, AnimationDefinition var1, int var2, int var3, boolean var4, int var5) {
        try {
            if(Class113.anInt1552 < 50) {
                if(var1.anIntArrayArray1867 != null && var1.anIntArrayArray1867.length > var5 && null != var1.anIntArrayArray1867[var5]) {
                    int var6 = var1.anIntArrayArray1867[var5][0];
                    int var7 = var6 >> 8;
                    int var10;
                    if(1 < var1.anIntArrayArray1867[var5].length) {
                        var10 = (int)((double)var1.anIntArrayArray1867[var5].length * Math.random());
                        if(0 < var10) {
                            var7 = var1.anIntArrayArray1867[var5][var10];
                        }
                    }

                    int var8 = var6 >> 5 & 7;
                    int var9 = var6 & 31;
                    if(var9 == 0) {
                        if(var4) {
                            Class3_Sub13_Sub6.method199(var8, var7, 0);
                        }

                    } else if (0 != Class14.anInt340) {
                        Class3_Sub25.anIntArray2550[Class113.anInt1552] = var7;
                        Class166.anIntArray2068[Class113.anInt1552] = var8;
                        int var11 = (-64 + var0) / 128;
                        var10 = (var3 + -64) / 128;
                        RSString.anIntArray2157[Class113.anInt1552] = 0;
                        Class102.aClass135Array2131[Class113.anInt1552] = null;
                        Class3_Sub13_Sub6.anIntArray3083[Class113.anInt1552] = var9 + (var10 << 16) + (var11 << 8);
                        if (var2 != 183921384) {
                            aClass33_1238 = (Class33) null;
                        }

                        ++Class113.anInt1552;
                    }
                }
            }
        } catch (RuntimeException var12) {
            throw Class44.clientError(var12, "ma.C(" + var0 + ',' + (var1 != null?"{...}":"null") + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
        }
    }

}
