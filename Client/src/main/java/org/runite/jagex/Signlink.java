package org.runite.jagex;
import java.applet.Applet;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Hashtable;
import java.util.Vector;

public class Signlink implements Runnable {

   public static String javaVersion;
   public Class122[] aClass122Array1197;
   public Class122 aClass122_1198;
   public EventQueue anEventQueue1199;
   private final Thread aThread1200;
   private boolean aBoolean1201;
   public static String osName;
   private Class64 aClass64_1203 = null;
   public Class122 aClass122_1204;
   public static String osNameCS;
   private Sensor aSensor1206;
   public Class122 aClass122_1207;
   private Display aDisplay1208;
   private static String homeDirectory;
   private static final Hashtable aHashtable1211 = new Hashtable(16);
   private final String aString1212;
   private Class64 aClass64_1213 = null;
   public static int anInt1214 = 1;
   private final int anInt1215;
   public static String javaVendor;
   private Interface1 anInterface1_1217;
   public static String osArchitecture;
   public Applet anApplet1219;
   public static Method aMethod1220;
   static volatile long aLong1221 = 0L;
   public static Method aMethod1222;


   public final void method1431(int var1) {
      if(var1 != 0) {
         this.method1442((Class)null, 99);
      }

      aLong1221 = Class5.method830((byte)-55) - -5000L;
   }

   public final boolean method1432(boolean var1) {
      if(var1) {
         this.aClass122Array1197 = null;
      }

      return this.aDisplay1208 != null;
   }

   public final Class64 method1433(String var1, int var2) {
      if(var2 != 12) {
         this.aClass122_1207 = null;
      }

      return this.method1435(12, 0, var1, 0, var2 + -58);
   }

   public final void method1434(int[] var1, int var2, int var3, Component var4, Point var5, int var6) {
      if (var2 == 10000) {
         this.method1435(17, var6, new Object[]{var4, var1, var5}, var3, -57);
      }
   }

   private Class64 method1435(int var1, int var2, Object var3, int var4, int var5) {
      Class64 var6 = new Class64();
      var6.anInt980 = var2;
      var6.anInt979 = var4;
      var6.anInt975 = var1;
      var6.anObject977 = var3;
      synchronized(this) {
         if(var5 >= -2) {
            return null;
         } else {
            if(this.aClass64_1203 == null) {
               this.aClass64_1203 = this.aClass64_1213 = var6;
            } else {
               this.aClass64_1203.aClass64_976 = var6;
               this.aClass64_1203 = var6;
            }

            this.notify();
            return var6;
         }
      }
   }

   public final Class64 method1436(Frame var1, int var2) {
      if(var2 <= 78) {
         this.anApplet1219 = null;
      }

      return this.method1435(7, 0, var1, 0, -112);
   }

   private static Class122 method1438(boolean var0, String var1) {
      if(var0) {
         method1438(true, (String)null);
      }

      String[] var2 = new String[]{"c:/rscache/", "/rscache/", homeDirectory, "c:/windows/", "c:/winnt/", "c:/", "/tmp/", ""};

      for(int var3 = 0; var2.length > var3; ++var3) {
         String var4 = var2[var3];
         if(var4.length() <= 0 || (new File(var4)).exists()) {
            try {
               return new Class122(new File(var4, "jagex_" + var1 + "_preferences.dat"), "rw", 10000L);
            } catch (Exception var6) {
            }
         }
      }

      return null;
   }

   public final Class64 method1439(boolean var1, URL var2) {
      if(var1) {
         this.aClass122_1204 = null;
      }

      return this.method1435(4, 0, var2, 0, -118);
   }

   public final Class64 method1441(byte var1, String address, int port) {
      //System.out.println("var1: " + var1 + ", add: " + address + ":" + port);
      return var1 != 8?null:this.method1435(1, 0, address, port, -17);
   }

   public final void method1442(Class var1, int var2) {
      if (var2 == 0) {
         this.method1435(11, 0, var1, 0, -5);
      }
   }

   public final Class64 method1443(Class var1, Class[] var2, int var3, String var4) {
      if(var3 > -7) {
         homeDirectory = null;
      }

      return this.method1435(8, 0, new Object[]{var1, var4, var2}, 0, -75);
   }

   public final void run() {
//      byte dat2status = Update.updateExists(1, "main_file_cache.dat2");

      while(true) {
         Class64 var1;
         synchronized(this) {
            while(true) {
               if(this.aBoolean1201) {
                  return;
               }

               if(this.aClass64_1213 != null) {
                  var1 = this.aClass64_1213;
                  this.aClass64_1213 = this.aClass64_1213.aClass64_976;
                  if(this.aClass64_1213 == null) {
                     this.aClass64_1203 = null;
                  }
                  break;
               }

               try {
                  this.wait();
               } catch (InterruptedException var11) {
               }
            }
         }

         try {
            int var2 = var1.anInt975;
            if(var2 == 1) {
               if(Class5.method830((byte) -55) < aLong1221) {
                  throw new IOException();
               }
//               System.out.println("Roar " + (String)var1.anObject977 + ", port " + var1.anInt979);
               var1.anObject974 = new Socket(InetAddress.getByName((String)var1.anObject977), var1.anInt979);
            } else if(2 == var2) {
               Thread var16 = new Thread((Runnable)var1.anObject977);
               var16.setDaemon(true);
               var16.start();
               var16.setPriority(var1.anInt979);
               var1.anObject974 = var16;
            } else if (var2 == 4) {
               if (Class5.method830((byte) -55) < aLong1221) {
                  throw new IOException();
               }

               var1.anObject974 = new DataInputStream(((URL) var1.anObject977).openStream());
            } else {
               Object[] var3;
               if (var2 == 8) {
                  var3 = (Object[]) var1.anObject977;
                  if (((Class) var3[0]).getClassLoader() == null) {
                     throw new SecurityException();
                  }

                  var1.anObject974 = ((Class) var3[0]).getDeclaredMethod((String) var3[1], (Class[]) var3[2]);
               } else if (var2 == 9) {
                  var3 = (Object[]) var1.anObject977;
                  if (((Class) var3[0]).getClassLoader() == null) {
                     throw new SecurityException();
                  }

                  var1.anObject974 = ((Class) var3[0]).getDeclaredField((String) var3[1]);
               } else {
                  String var4;
                  if (var2 == 3) {
                     if (aLong1221 > Class5.method830((byte) -55)) {
                        throw new IOException();
                     }

                     var4 = (var1.anInt979 >> 24 & 255) + "." + (var1.anInt979 >> 16 & 255) + "." + (var1.anInt979 >> 8 & 255) + "." + (255 & var1.anInt979);
                     var1.anObject974 = InetAddress.getByName(var4).getHostName();
                  } else if (var2 == 5) {
                     var1.anObject974 = this.aDisplay1208.method919(true);
                  } else if (var2 == 6) {
                     Frame var5 = new Frame("Jagex Full Screen");
                     var1.anObject974 = var5;
                     var5.setResizable(false);
                     this.aDisplay1208.method918(-56, var1.anInt980 & '\uffff', var1.anInt980 >> 16, '\uffff' & var1.anInt979, var5, var1.anInt979 >>> 16);
                  } else if (var2 == 7) {
                     this.aDisplay1208.method920();
                  } else if (10 == var2) {
                     Class[] var17 = new Class[]{Class.forName("java.lang.Class"), Class.forName("java.lang.String")};
                     Runtime var6 = Runtime.getRuntime();
                     Method var7;
                     if (!osName.startsWith("mac")) {
                        var7 = Class.forName("java.lang.Runtime").getDeclaredMethod("loadLibrary0", var17);
                        var7.setAccessible(true);
                        var7.invoke(var6, new Object[]{var1.anObject977, "jawt"});
                        var7.setAccessible(false);
                     }
                     boolean var11 = osArchitecture.contains("64");
                     boolean var12 = osName.startsWith("sunos");
                     var7 = Class.forName("java.lang.Runtime").getDeclaredMethod("load0", var17);
                     var7.setAccessible(true);
                     if (osName.startsWith("linux") || var12) {
                        createLibs(var12 ? (var11 ? 7 : 6) : (var11 ? 5 : 4));
                        var7.invoke(var6, var1.anObject977, method1448(this.aString1212, this.anInt1215, true, "libgluegen-rt.so").toString());
                        Class var8 = ((Class) var1.anObject977).getClassLoader().loadClass("com.sun.opengl.impl.x11.DRIHack");
                        var8.getMethod("begin", new Class[0]).invoke((Object) null, new Object[0]);
                        var7.invoke(var6, var1.anObject977, method1448(this.aString1212, this.anInt1215, true, "libjogl.so").toString());
                        var8.getMethod("end", new Class[0]).invoke((Object) null, new Object[0]);
                        var7.invoke(var6, var1.anObject977, method1448(this.aString1212, this.anInt1215, true, "libjogl_awt.so").toString());
                     } else if (osName.startsWith("mac")) {
                        createLibs(var11 ? 2 : 3);
                        var7.invoke(var6, var1.anObject977, method1448(this.aString1212, this.anInt1215, true, "libjogl.jnilib").toString());
                        var7.invoke(var6, var1.anObject977, method1448(this.aString1212, this.anInt1215, true, "libjogl_awt.jnilib").toString());
                     } else {

                        if (!osName.startsWith("win")) {
                           throw new Exception();
                        }
                        createLibs(var11 ? 1 : 0);
                        var7.invoke(var6, new Object[]{var1.anObject977, method1448(this.aString1212, this.anInt1215, true, "jogl.dll").toString()});
                        var7.invoke(var6, new Object[]{var1.anObject977, method1448(this.aString1212, this.anInt1215, true, "jogl_awt.dll").toString()});
                     }

                     var7.setAccessible(false);
                  } else {
                     int var18;
                     if (var2 == 11) {
                        Field var20 = Class.forName("java.lang.ClassLoader").getDeclaredField("nativeLibraries");
                        var20.setAccessible(true);
                        Vector var24 = (Vector) var20.get(((Class) var1.anObject977).getClassLoader());

                        for (var18 = 0; var18 < var24.size(); ++var18) {
                           Object var26 = var24.elementAt(var18);
                           Method var9 = var26.getClass().getDeclaredMethod("finalize", new Class[0]);
                           var9.setAccessible(true);
                           var9.invoke(var26, new Object[0]);
                           var9.setAccessible(false);
                           Field var10 = var26.getClass().getDeclaredField("handle");
                           var10.setAccessible(true);
                           var10.set(var26, new Integer(0));
                           var10.setAccessible(false);
                        }

                        var20.setAccessible(false);
                     } else if (var2 == 12) {
                        var4 = (String) var1.anObject977;
                        var1.anObject974 = method1438(false, var4);
                     } else if (var2 == 14) {
                        int var22 = var1.anInt980;
                        int var23 = var1.anInt979;
                        this.aSensor1206.method1796(var23, var22);
                     } else if (15 == var2) {
                        boolean var21 = var1.anInt979 != 0;
                        Component var27 = (Component) var1.anObject977;
                        this.aSensor1206.method1797(var27, var21);
                     } else if (17 == var2) {
                        var3 = (Object[]) var1.anObject977;
                        this.aSensor1206.method1795((Point) var3[2], var1.anInt979, (Component) var3[0], var1.anInt980, (int[]) var3[1]);
                     } else {
                        if (16 != var2) {
                           throw new Exception();
                        }

                        try {
                           if (!osName.startsWith("win")) {
                              throw new Exception();
                           }

                           var4 = (String) var1.anObject977;
                           if (!var4.startsWith("http://") && !var4.startsWith("https://")) {
                              throw new Exception();
                           }

                           String var25 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789?&=,.%+-_#:/*";

                           for (var18 = 0; var18 < var4.length(); ++var18) {
                              if (var25.indexOf(var4.charAt(var18)) == -1) {
                                 throw new Exception();
                              }
                           }

                           Runtime.getRuntime().exec("cmd /c start \"j\" \"" + var4 + "\"");
                           var1.anObject974 = null;
                        } catch (Exception var12) {
                           var1.anObject974 = var12;
                        }
                     }
                  }
               }
            }

            var1.anInt978 = 1;
         } catch (ThreadDeath var13) {
            throw var13;
         } catch (Throwable var14) {
            var1.anInt978 = 2;
         }
      }
   }

   public void createLibs(int archive) throws Throwable {
	   String jogl = archive < 2 ? "jogl.dll" : archive < 4 ? "libjogl.jnilib" : "libjogl.so";
	   String joglAwt = archive < 2 ? "jogl_awt.dll" : archive < 4 ? "libjogl_awt.jnilib" : "libjogl_awt.so";
	   byte[] bs = Class132.libIndex.getFile(archive, 0);
	   if (bs == null || bs.length < 1) {
		   System.err.println("Could not create native lib " + joglAwt + ", archive=" + archive + "!");
		   return;
	   }
	   FileOutputStream fos = new FileOutputStream(method1448(this.aString1212, this.anInt1215, true, joglAwt));
	   fos.write(bs);
	   fos.flush();
	   fos.close();
	   bs = Class132.libIndex.getFile(archive, 1);
	   if (bs == null || bs.length < 1) {
		   System.err.println("Could not create native lib " + jogl + ", archive=" + archive + "!");
		   return;
	   }
	   fos = new FileOutputStream(method1448(this.aString1212, this.anInt1215, true, jogl));
	   fos.write(bs);
	   fos.flush();
	   fos.close();
	   if (archive > 3) {
		   bs = Class132.libIndex.getFile(archive, 2);
		   if (bs == null || bs.length < 1) {
			   System.err.println("Could not create native lib libgluegen-rt.so, archive=" + archive + "!");
			   return;
		   }
		   fos = new FileOutputStream(method1448(this.aString1212, this.anInt1215, true, "libgluegen-rt.so"));
		   fos.write(bs);
		   fos.flush();
		   fos.close();
	   }
   }
   
   public final Class64 method1444(int var1, Class var2) {
      if(var1 > -13) {
         this.method1435(88, -20, (Object)null, 76, -127);
      }

      return this.method1435(10, 0, var2, 0, -113);
   }

   public final void method1445(int var1) {
      synchronized(this) {
         this.aBoolean1201 = true;
         this.notifyAll();
      }

      try {
         this.aThread1200.join();
      } catch (InterruptedException var8) {
      }

      if(var1 != 0) {
         method1438(false, (String)null);
      }

      if(this.aClass122_1198 != null) {
         try {
            this.aClass122_1198.close(var1 ^ 1);
         } catch (IOException var7) {
         }
      }

      if(this.aClass122_1204 != null) {
         try {
            this.aClass122_1204.close(1);
         } catch (IOException var6) {
         }
      }

      if(this.aClass122Array1197 != null) {
         for(int var2 = 0; var2 < this.aClass122Array1197.length; ++var2) {
            if(this.aClass122Array1197[var2] != null) {
               try {
                  this.aClass122Array1197[var2].close(var1 ^ 1);
               } catch (IOException var5) {
               }
            }
         }
      }

      if(this.aClass122_1207 != null) {
         try {
            this.aClass122_1207.close(var1 + 1);
         } catch (IOException var4) {
         }
      }

   }

   public final Interface1 method1446(byte var1) {
      if(var1 < 71) {
         this.method1452((String)null, true);
      }

      //return this.anInterface1_1217;
      return null;
   }

   public final Class64 method1447(int var1, String var2, Class var3) {
      if(var1 > -39) {
         this.method1452((String)null, true);
      }

      return this.method1435(9, 0, new Object[]{var3, var2}, 0, -43);
   }

   public static File method1448(String var0, int var1, boolean var2, String var3) {
      File var4 = (File)aHashtable1211.get(var3);
      if(var4 == null) {
         if(!var2) {
            method1438(true, (String)null);
         }

         /*String[] var5 = new String[]{"c:/rscache/", "/rscache/", "c:/windows/", "c:/winnt/", "c:/", homeDirectory, "/tmp/", ""};
         String[] var6 = new String[]{".530jagex_cache_" + var1, ".530file_store_" + var1};*/
         String[] var5 = new String[]{homeDirectory, "c:/rscache/", "/rscache/", "c:/windows/", "c:/winnt/", "c:/", "/tmp/", ""};
         String[] var6 = new String[]{".runite_rs", ".530file_store_" + var1};

         for(int var7 = 0; var7 < 2; ++var7) {
            for (String s : var6) {
               for (String value : var5) {
                  String var10 = value + s + "/" + (var0 != null ? var0 + "/" : "") + var3;
                  RandomAccessFile var11 = null;

                  try {
                     File var12 = new File(var10);
                     if (var7 != 0 || var12.exists()) {
                        if (var7 != 1 || value.length() <= 0 || (new File(value)).exists()) {
                           (new File(value + s)).mkdir();
                           if (var0 != null) {
                              (new File(value + s + "/" + var0)).mkdir();
                           }
                           //								ClientLoader.getLibraryDownloader().updateDlls(var10.toString());
                           var11 = new RandomAccessFile(var12, "rw");
                           int var14 = var11.read();
                           var11.seek(0L);
                           var11.write(var14);
                           var11.seek(0L);
                           var11.close();
                           aHashtable1211.put(var3, var12);
                           return var12;
                        }
                     }
                  } catch (Exception var16) {
                     try {
                        if (var11 != null) {
                           var11.close();
                        }
                     } catch (Exception var15) {
                     }
                  }
               }
            }
         }

         throw new RuntimeException();
      } else {
         return var4;
      }
   }

   public final Class64 method1449(int var1, int var2) {
      if(var1 != 3) {
         this.aClass122_1204 = null;
      }

      return this.method1435(3, 0, (Object)null, var2, var1 + -96);
   }

   public final Class64 method1450(int var1, int var2, int var3, int var4) {
      return this.method1435(6, var1 + (var2 << 16), (Object)null, (var4 << 16) + var3, -49);
   }

   public final Class64 method1451(int var1, int var2, Runnable var3) {
      return var1 != 0?null:this.method1435(2, 0, var3, var2, -27);
   }

   public final Class64 method1452(String var1, boolean var2) {
      if(!var2) {
         this.method1436((Frame)null, 101);
      }

      return this.method1435(16, 0, var1, 0, -10);
   }

   public final Class64 method1453(byte var1) {
      if(var1 < 7) {
         this.method1443((Class)null, (Class[])null, -91, (String)null);
      }

      return this.method1435(5, 0, (Object)null, 0, -127);
   }

   public Signlink(Applet var1, int var2, String folder, int cacheIndexes) throws Exception {
      javaVersion = "1.1";
      this.aString1212 = folder;
      this.anInt1215 = var2;
      this.anApplet1219 = var1;
      javaVendor = "Unknown";

      try {
         javaVendor = System.getProperty("java.vendor");
         javaVersion = System.getProperty("java.version");
      } catch (Exception var17) {
      }

      try {
         osNameCS = System.getProperty("os.name");
      } catch (Exception var16) {
         osNameCS = "Unknown";
      }

      osName = osNameCS.toLowerCase();

      try {
         osArchitecture = System.getProperty("os.arch").toLowerCase();
      } catch (Exception var15) {
         osArchitecture = "";
      }

      try {
         homeDirectory = System.getProperty("user.home");
         if(homeDirectory != null) {
            homeDirectory = homeDirectory + "/";
         }
      } catch (Exception var13) {
      }

      if(homeDirectory == null) {
         homeDirectory = "~/";
      }

      try {
         this.anEventQueue1199 = Toolkit.getDefaultToolkit().getSystemEventQueue();
      } catch (Throwable var12) {
      }

      try {
         if(var1 == null) {
            aMethod1222 = Class.forName("java.awt.Component").getDeclaredMethod("setFocusTraversalKeysEnabled", new Class[]{Boolean.TYPE});
         } else {
            aMethod1222 = var1.getClass().getMethod("setFocusTraversalKeysEnabled", new Class[]{Boolean.TYPE});
         }
      } catch (Exception var11) {
      }

      try {
         if(var1 == null) {
            aMethod1220 = Class.forName("java.awt.Container").getDeclaredMethod("setFocusCycleRoot", new Class[]{Boolean.TYPE});
         } else {
            aMethod1220 = var1.getClass().getMethod("setFocusCycleRoot", new Class[]{Boolean.TYPE});
         }
      } catch (Exception var10) {
      }

      this.aClass122_1207 = new Class122(method1448((String)null, this.anInt1215, true, "random.dat"), "rw", 25L);
      this.aClass122_1198 = new Class122(method1448(this.aString1212, this.anInt1215, true, "main_file_cache.dat2"), "rw", 104857600L);
      this.aClass122_1204 = new Class122(method1448(this.aString1212, this.anInt1215, true, "main_file_cache.idx255"), "rw", 1048576L);
      this.aClass122Array1197 = new Class122[cacheIndexes];

      for(int var5 = 0; var5 < cacheIndexes; ++var5) {
         this.aClass122Array1197[var5] = new Class122(method1448(this.aString1212, this.anInt1215, true, "main_file_cache.idx" + var5), "rw", 1048576L);
      }

      try {
         this.aDisplay1208 = new Display();
      } catch (Throwable var9) {
    	  var9.printStackTrace();
      }

      try {
         this.aSensor1206 = new Sensor();
      } catch (Throwable var8) {
      }

      ThreadGroup var18 = Thread.currentThread().getThreadGroup();

      for(ThreadGroup var6 = var18.getParent(); var6 != null; var6 = var6.getParent()) {
         var18 = var6;
      }

      Thread[] var19 = new Thread[1000];
      var18.enumerate(var19);

      for(int var7 = 0; var7 < var19.length; ++var7) {
         if(var19[var7] != null && var19[var7].getName().startsWith("AWT")) {
            var19[var7].setPriority(1);
         }
      }

      this.aBoolean1201 = false;
      this.aThread1200 = new Thread(this);
      this.aThread1200.setPriority(10);
      this.aThread1200.setDaemon(true);
      this.aThread1200.start();
   }
}
