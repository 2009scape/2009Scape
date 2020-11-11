package org.runite.client;
import com.jogamp.opengl.GL4bc;
import org.rs09.client.config.GameConfig;



import java.util.Objects;

final class WaterfallShader implements ShaderInterface {

   static boolean[] aBooleanArray2169 = new boolean[5];
   static CacheIndex aClass153_2172;
   static int waterfallTextureId = -1;
   private int listId;
   private final float[] aFloatArray2174 = new float[4];
   private static RSString aClass94_2175 = RSString.parse(")4a=");


   static void method1626(byte var0) {
      try {
         Class3_Sub28_Sub4.aReferenceCache_3572.clear();
         Class143.aReferenceCache_1874.clear();
         if(var0 <= -124) {
            Class67.aReferenceCache_1013.clear();
         }
      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "ob.F(" + var0 + ')');
      }
   }

   static boolean method1627(int var0, byte var1) {
      try {
         WorldListEntry var2 = Class3_Sub8.getWorld(97, var0);
         if(var2 == null) {
            return false;
         } else if (1 != Signlink.anInt1214 && Signlink.anInt1214 != 2 && Class44.anInt718 != 2) {
            RSString var9 = GameShell.aClass94_8;
            if (Class44.anInt718 != 0) {
               var9 = RSString.stringCombiner(new RSString[]{TextCore.aClass94_4007, RSString.stringAnimator(var2.worldId - -7000)});
            }

            if (var1 > -2) {
               return false;
            } else {
               RSString var4 = GameShell.aClass94_8;
               if (Class163_Sub2.aClass94_2996 != null) {
                  var4 = RSString.stringCombiner(new RSString[]{Class97.aClass94_1380, Class163_Sub2.aClass94_2996});
               }
               RSString var5 = RSString.stringCombiner(new RSString[]{RSString.parse("http:)4)4"), var2.address, var9, TextCore.aClass94_2608, RSString.stringAnimator(Class3_Sub20.language), aClass94_2175, RSString.stringAnimator(Class3_Sub26.anInt2554), var4, TextCore.aClass94_1133, !Unsorted.aBoolean3641 ? TextCore.aClass94_3013 : TextCore.aClass94_339, TextCore.aClass94_2610, !Class163_Sub2_Sub1.aBoolean4018 ? TextCore.aClass94_3013 : TextCore.aClass94_339, TextCore.aClass94_1617, Client.aBoolean3779 ? TextCore.aClass94_339 : TextCore.aClass94_3013});

               try {
                  Objects.requireNonNull(Class126.aClient1671.getAppletContext()).showDocument(var5.toURL(), "_self");
               } catch (Exception var7) {
                  return false;
               }

               return true;
            }
         } else {
            GameConfig.IP_ADDRESS = var2.address.toString();
            System.out.println(GameConfig.IP_ADDRESS);
//            GameLaunch.SETTINGS.setIp(var2.address.toString());
            var2.address.method1568();
            Class38_Sub1.accRegistryIp = GameConfig.IP_MANAGEMENT;
            CS2Script.anInt2451 = var2.worldId;
            if (Class44.anInt718 != 0) {
               Class162.anInt2036 = '\u9c40' + CS2Script.anInt2451;
               Class140_Sub6.accRegistryPort = Class162.anInt2036;
               Client.currentPort = CS2Script.anInt2451 + '\uc350';
            }

            return true;
         }
      } catch (RuntimeException var8) {
         throw ClientErrorException.clientError(var8, "ob.E(" + var0 + ',' + var1 + ')');
      }
   }

   public final int method24() {
      try {
         return 0;
      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "ob.C()");
      }
   }

   public final void method23(int var1) {
      try {
         GL4bc var2 = HDToolKit.gl;
         float var4 = (float)(1 + (var1 >> 3 & 3)) * 0.01F;
         float var3 = -0.01F * (float)(1 + (var1 & 3));
         float var5 = 0 == (var1 & 64)?4.8828125E-4F:9.765625E-4F;
         boolean var6 = (128 & var1) != 0;
         if(var6) {
            this.aFloatArray2174[0] = var5;
            this.aFloatArray2174[1] = 0.0F;
            this.aFloatArray2174[2] = 0.0F;
            this.aFloatArray2174[3] = 0.0F;
         } else {
            this.aFloatArray2174[2] = var5;
            this.aFloatArray2174[1] = 0.0F;
            this.aFloatArray2174[3] = 0.0F;
            this.aFloatArray2174[0] = 0.0F;
         }

         var2.glActiveTexture('\u84c1');
         var2.glMatrixMode(5888);
         var2.glPushMatrix();
         var2.glLoadIdentity();
         var2.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
         var2.glRotatef((float)Class140_Sub7.anInt2938 * 360.0F / 2048.0F, 1.0F, 0.0F, 0.0F);
         var2.glRotatef(360.0F * (float)Class3_Sub13_Sub8.anInt3103 / 2048.0F, 0.0F, 1.0F, 0.0F);
         var2.glTranslatef((float)(-Unsorted.anInt144), (float)(-Unsorted.anInt3695), (float)(-LinkableRSString.anInt2587));
         var2.glTexGenfv(8192, 9474, this.aFloatArray2174, 0);
         this.aFloatArray2174[3] = var3 * (float)HDToolKit.anInt1791;
         this.aFloatArray2174[0] = 0.0F;
         this.aFloatArray2174[2] = 0.0F;
         this.aFloatArray2174[1] = var5;
         var2.glTexGenfv(8193, 9474, this.aFloatArray2174, 0);
         var2.glPopMatrix();
         if(Class88.Texture3DEnabled) {
            this.aFloatArray2174[3] = (float)HDToolKit.anInt1791 * var4;
            this.aFloatArray2174[1] = 0.0F;
            this.aFloatArray2174[0] = 0.0F;
            this.aFloatArray2174[2] = 0.0F;
            var2.glTexGenfv(8194, 9473, this.aFloatArray2174, 0);
         } else {
            int var7 = (int)((float)HDToolKit.anInt1791 * var4 * 64.0F);
            var2.glBindTexture(3553, Class88.anIntArray1223[var7 % 64]);
         }

         var2.glActiveTexture('\u84c0');
      } catch (RuntimeException var8) {
         throw ClientErrorException.clientError(var8, "ob.B(" + var1 + ')');
      }
   }

   public final void method21() {
      try {
         GL4bc var1 = HDToolKit.gl;
         var1.glCallList(1 + this.listId);
      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "ob.A()");
      }
   }

   public final void method22() {
      try {
         GL4bc var1 = HDToolKit.gl;
         var1.glCallList(this.listId);
      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "ob.D()");
      }
   }

   public static void method1630(byte var0) {
      try {
         TextCore.aClass94_2171 = null;
         aClass153_2172 = null;
         aBooleanArray2169 = null;
         if(var0 > -112) {
            method1632(-116, 108, 54, -120, 44, 6);
         }
         aClass94_2175 = null;
      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "ob.J(" + var0 + ')');
      }
   }

   private void method1631() {
      try {
         GL4bc var2 = HDToolKit.gl;
         this.listId = var2.glGenLists(2);
         var2.glNewList(this.listId, 4864);//COMPILE
         var2.glActiveTexture('\u84c1');//TEXTURE1
         if(Class88.Texture3DEnabled) {
            var2.glBindTexture('\u806f', waterfallTextureId);//TEXTURE_3D
            var2.glTexGeni(8194, 9472, 9217);//R, TEXTURE_GEN_MODE, OBJECT_LINEAR
            var2.glEnable(3170);//TEXTURE_GEN_R
            var2.glEnable('\u806f');//TEXTURE_3D
         } else {
            var2.glEnable(3553);//TEXTURE_2D
         }

         var2.glTexGeni(8192, 9472, 9216);//S, TEXTURE_GEN_MODE, EYE_LINEAR
         var2.glTexGeni(8193, 9472, 9216);//T, TEXTURE_GEN_MODE, EYE_LINEAR
         var2.glEnable(3168);//TEXTURE_GEN_S
         var2.glEnable(3169);//TEXTURE_GEN_T
         var2.glActiveTexture('\u84c0');//TEXTURE1
         var2.glEndList();
         var2.glNewList(this.listId + 1, 4864);//COMPILE
         var2.glActiveTexture('\u84c1');//TEXTURE1
         if(Class88.Texture3DEnabled) {
            var2.glDisable('\u806f');//TEXTURE_3D
            var2.glDisable(3170);//TEXTURE_GEN_R
         } else {
            var2.glDisable(3553);//TEXTURE_2D
         }

         var2.glDisable(3168);//TEXTURE_GEN_S
         var2.glDisable(3169);//TEXTURE_GEN_T
         var2.glActiveTexture('\u84c0');//TEXTURE0
         var2.glEndList();
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "ob.I(" + 2 + ')');
      }
   }

   public WaterfallShader() {
      try {
         this.method1631();
      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "ob.<init>()");
      }
   }

   static void method1632(int var0, int var1, int var2, int var3, int var4, int var5) {
      try {
         if(var0 <= 66) {
            method1630((byte)-33);
         }

         for(int var6 = var3; var6 <= var1; ++var6) {
            Class3_Sub13_Sub23_Sub1.method282(Class38.anIntArrayArray663[var6], var4, 121, var2, var5);
         }

      } catch (RuntimeException var7) {
         throw ClientErrorException.clientError(var7, "ob.G(" + var0 + ',' + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
      }
   }

}
