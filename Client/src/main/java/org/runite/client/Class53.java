package org.runite.client;


import java.io.*;

final class Class53 {

   static int anInt865 = -1;
   static long aLong866 = 0L;
   static int anInt867;
    static int worldListOffset;


   static int method1170(int var1) {
         return var1 >>> 8;
   }

   static void method1171(int var0, int var1, int var2, int var3, int var4, RSInterface var5) {
      try {
         int var7 = var3 * var3 + var4 * var4;
         if(var7 <= 360000) {
            int var8 = Math.min(var5.width / 2, var5.height / 2);

            if(var8 * var8 >= var7) {
               Class38_Sub1.minimapIcons(var5, Class129_Sub1.aAbstractSpriteArray2690[var0], var4, var3, var1, var2);
            } else {
               var8 -= 10;
               int var9 = 2047 & Class3_Sub13_Sub8.anInt3102 + GraphicDefinition.CAMERA_DIRECTION;
               int var11 = Class51.anIntArray851[var9];
               int var10 = Class51.anIntArray840[var9];
               var10 = var10 * 256 / (256 + Class164_Sub2.anInt3020);
               var11 = var11 * 256 / (Class164_Sub2.anInt3020 + 256);
               int var12 = var4 * var10 - -(var11 * var3) >> 16;
               int var13 = -(var10 * var3) + var4 * var11 >> 16;
               double var14 = Math.atan2(var12, var13);
               int var16 = (int)(Math.sin(var14) * (double)var8);
               int var17 = (int)(Math.cos(var14) * (double)var8);
               if(HDToolKit.highDetail) {
                  ((HDSprite)Class3_Sub13_Sub39.aAbstractSpriteArray3458[var0]).method648(240, 240, (var5.width / 2 + var2 + var16) * 16, 16 * (-var17 + var5.height / 2 + var1), (int)(10430.378D * var14));
               } else {
                  ((Class3_Sub28_Sub16_Sub2)Class3_Sub13_Sub39.aAbstractSpriteArray3458[var0]).method660(-10 + var16 + var5.width / 2 + var2, -10 + var5.height / 2 + var1 + -var17, var14);
               }
            }

         }
      } catch (RuntimeException var18) {
         throw ClientErrorException.clientError(var18, "hi.D(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + (var5 != null?"{...}":"null") + ',' + false + ')');
      }
   }

   static String method1172(Throwable var1) throws IOException {
      String var2;
      if(var1 instanceof ClientErrorException) {
         ClientErrorException var3 = (ClientErrorException)var1;
         var1 = var3.aThrowable2118;
         var2 = var3.aString2117 + " | ";
      } else {
         var2 = "";
      }

      StringWriter var13 = new StringWriter();
      PrintWriter var4 = new PrintWriter(var13);
      var1.printStackTrace(var4);
      var4.close();
      String var5 = var13.toString();
      BufferedReader var6 = new BufferedReader(new StringReader(var5));
      String var7 = var6.readLine();

      while(true) {
         String var8 = var6.readLine();
         if(var8 == null) {
            var2 = var2 + "| " + var7;
            return var2;
         }

         int var9 = var8.indexOf(40);
         int var10 = var8.indexOf(41, 1 + var9);
         String var11;
         if(var9 == -1) {
            var11 = var8;
         } else {
            var11 = var8.substring(0, var9);
         }

         var11 = var11.trim();
         var11 = var11.substring(1 + var11.lastIndexOf(32));
         var11 = var11.substring(var11.lastIndexOf(9) + 1);
         var2 = var2 + var11;
         if(-1 != var9 && var10 != -1) {
            int var12 = var8.indexOf(".java:", var9);
            if(var12 >= 0) {
               var2 = var2 + var8.substring(5 + var12, var10);
            }
         }

         var2 = var2 + ' ';
      }
   }

   static RSString method1174(RSInterface var0, byte var1) {
      try {
         return Client.method44(var0).method101() != 0 ?(null != var0.aClass94_245 && var0.aClass94_245.trim(1).length() != 0?var0.aClass94_245:(ClientCommands.commandQaOpEnabled? TextCore.aClass94_2116:null)):null;
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "hi.F(" + (var0 != null?"{...}":"null") + ',' + var1 + ')');
      }
   }

}
