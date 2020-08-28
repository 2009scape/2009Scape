package org.runite.jagex;
import java.applet.Applet;
import netscape.javascript.JSObject;

final class Class42 {

   static Object method1055(String var0, Applet var2) {
      return JSObject.getWindow(var2).call(var0, (Object[])null);
   }

   static void method1056(Applet var0, Object[] var2) {
       JSObject.getWindow(var0).call("openjs", var2);
   }

   static void method1057(Applet var0, String var2) {
      JSObject.getWindow(var0).eval(var2);
   }
}
