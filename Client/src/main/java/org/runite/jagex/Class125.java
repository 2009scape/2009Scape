package org.runite.jagex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.media.opengl.GL;

final class Class125 implements Interface5 {

   private int anInt2182 = -1;
   private FloatBuffer aFloatBuffer2183;
   private int anInt2184;
   private static float[] aFloatArray2185 = new float[4];
   private int anInt2186 = -1;


   public final void method21() {
      if(this.anInt2186 >= 0) {
         GL var1 = HDToolKit.gl;
         var1.glCallList(this.anInt2186 + 1);
      }
   }

   public final int method24() {
      return 0;
   }

   public static void method1748() {
      aFloatArray2185 = null;
   }

   public final void method22() {
      if(this.anInt2186 >= 0) {
         javax.media.opengl.GL var1 = HDToolKit.gl;
         var1.glCallList(this.anInt2186);
         var1.glActiveTexture('\u84c1');
         var1.glMatrixMode(5890);
         var1.glTranslatef((float)Class9.anInt144, (float) Class69.anInt3695, (float)Class3_Sub29.anInt2587);
         var1.glRotatef(-((float)Class3_Sub13_Sub8.anInt3103 * 360.0F) / 2048.0F, 0.0F, 1.0F, 0.0F);
         var1.glRotatef(-((float)Class140_Sub7.anInt2938 * 360.0F) / 2048.0F, 1.0F, 0.0F, 0.0F);
         var1.glRotatef(-180.0F, 1.0F, 0.0F, 0.0F);
         var1.glMatrixMode(5888);
         if(!Class88.aBoolean1227) {
            var1.glBindTexture(3553, Class88.anIntArray1224[(int)((float)(HDToolKit.anInt1791 * 64) * 0.0050F) % 64]);
         }

         var1.glActiveTexture('\u84c0');
         if(this.anInt2182 != HDToolKit.anInt1791) {
            int var2 = (HDToolKit.anInt1791 & 255) * 256;

            for(int var3 = 0; var3 < 64; ++var3) {
               this.aFloatBuffer2183.position(var2);
               var1.glProgramLocalParameter4fvARB('\u8620', var3, this.aFloatBuffer2183);
               var2 += 4;
            }

            if(Class88.aBoolean1227) {
               var1.glProgramLocalParameter4fARB('\u8620', 65, (float)HDToolKit.anInt1791 * 0.0050F, 0.0F, 0.0F, 1.0F);
            } else {
               var1.glProgramLocalParameter4fARB('\u8620', 65, 0.0F, 0.0F, 0.0F, 1.0F);
            }

            this.anInt2182 = HDToolKit.anInt1791;
         }

      }
   }

   private void method1749() {
      javax.media.opengl.GL var1 = HDToolKit.gl;
      this.anInt2186 = var1.glGenLists(2);
      var1.glNewList(this.anInt2186, 4864);
      var1.glActiveTexture('\u84c1');
      if(Class88.aBoolean1227) {
         var1.glBindTexture('\u806f', Class88.anInt1228);
      }

      var1.glTexEnvi(8960, '\u8571', 260);
      var1.glTexEnvi(8960, '\u8572', 7681);
      var1.glTexEnvi(8960, '\u8588', '\u8578');
      var1.glActiveTexture('\u84c0');
      var1.glBindProgramARB('\u8620', this.anInt2184);
      var1.glEnable('\u8620');
      var1.glEndList();
      var1.glNewList(this.anInt2186 + 1, 4864);
      var1.glActiveTexture('\u84c1');
      var1.glMatrixMode(5890);
      var1.glLoadIdentity();
      var1.glMatrixMode(5888);
      var1.glTexEnvi(8960, '\u8571', 8448);
      var1.glTexEnvi(8960, '\u8572', 8448);
      var1.glTexEnvi(8960, '\u8588', 5890);
      var1.glDisable(Class88.aBoolean1227?'\u806f':3553);
      var1.glActiveTexture('\u84c0');
      var1.glBindProgramARB('\u8620', 0);
      var1.glDisable('\u8620');
      var1.glDisable('\u8804');
      var1.glEndList();
   }

   private void method1750() {
      if(this.anInt2186 >= 0) {
         javax.media.opengl.GL var1 = HDToolKit.gl;
         int[] var2 = new int[1];
         var1.glBindProgramARB('\u8620', this.anInt2184);
         var1.glProgramStringARB('\u8620', '\u8875', "!!ARBvp1.0\nATTRIB  iPos         = vertex.position;\nATTRIB  iColour      = vertex.color;\nOUTPUT  oPos         = result.position;\nOUTPUT  oColour      = result.color;\nOUTPUT  oTexCoord0   = result.texcoord[0];\nOUTPUT  oTexCoord1   = result.texcoord[1];\nOUTPUT  oFogCoord    = result.fogcoord;\nPARAM   time         = program.local[65];\nPARAM   turbulence   = program.local[64];\nPARAM   lightAmbient = program.local[66]; \nPARAM   pMatrix[4]   = { state.matrix.projection };\nPARAM   mvMatrix[4]  = { state.matrix.modelview };\nPARAM   ivMatrix[4]  = { state.matrix.texture[1] };\nPARAM   fNoise[64]   = { program.local[0..63] };\nTEMP    noise, clipPos, viewPos, worldPos;\nADDRESS noiseAddr;\nDP4   viewPos.x, mvMatrix[0], iPos;\nDP4   viewPos.y, mvMatrix[1], iPos;\nDP4   viewPos.z, mvMatrix[2], iPos;\nDP4   viewPos.w, mvMatrix[3], iPos;\nDP4   worldPos.x, ivMatrix[0], viewPos;\nDP4   worldPos.y, ivMatrix[1], viewPos;\nDP4   worldPos.z, ivMatrix[2], viewPos;\nDP4   worldPos.w, ivMatrix[3], viewPos;\nADD   noise.x, worldPos.x, worldPos.z;SUB   noise.y, worldPos.z, worldPos.x;MUL   noise, noise, 0.0001220703125;\nFRC   noise, noise;\nMUL   noise, noise, 64;\nARL   noiseAddr.x, noise.x;\nMOV   noise.x, fNoise[noiseAddr.x].x;\nARL   noiseAddr.x, noise.y;\nMOV   noise.y, fNoise[noiseAddr.x].y;\nMUL   noise, noise, turbulence.x;\nMAD   oTexCoord0, worldPos.xzww, 0.0078125, noise;\nMOV   oTexCoord0.w, 1;\nMUL   oTexCoord1.xy, worldPos.xzww, 0.0009765625;\nMOV   oTexCoord1.zw, time.xxxw;\nDP4   clipPos.x, pMatrix[0], viewPos;\nDP4   clipPos.y, pMatrix[1], viewPos;\nDP4   clipPos.z, pMatrix[2], viewPos;\nDP4   clipPos.w, pMatrix[3], viewPos;\nMUL   oColour.xyz, iColour, lightAmbient;\nMOV   oColour.w, 1;\nMOV   oFogCoord.x, clipPos.z;\nMOV   oPos, clipPos; \nEND".length(), "!!ARBvp1.0\nATTRIB  iPos         = vertex.position;\nATTRIB  iColour      = vertex.color;\nOUTPUT  oPos         = result.position;\nOUTPUT  oColour      = result.color;\nOUTPUT  oTexCoord0   = result.texcoord[0];\nOUTPUT  oTexCoord1   = result.texcoord[1];\nOUTPUT  oFogCoord    = result.fogcoord;\nPARAM   time         = program.local[65];\nPARAM   turbulence   = program.local[64];\nPARAM   lightAmbient = program.local[66]; \nPARAM   pMatrix[4]   = { state.matrix.projection };\nPARAM   mvMatrix[4]  = { state.matrix.modelview };\nPARAM   ivMatrix[4]  = { state.matrix.texture[1] };\nPARAM   fNoise[64]   = { program.local[0..63] };\nTEMP    noise, clipPos, viewPos, worldPos;\nADDRESS noiseAddr;\nDP4   viewPos.x, mvMatrix[0], iPos;\nDP4   viewPos.y, mvMatrix[1], iPos;\nDP4   viewPos.z, mvMatrix[2], iPos;\nDP4   viewPos.w, mvMatrix[3], iPos;\nDP4   worldPos.x, ivMatrix[0], viewPos;\nDP4   worldPos.y, ivMatrix[1], viewPos;\nDP4   worldPos.z, ivMatrix[2], viewPos;\nDP4   worldPos.w, ivMatrix[3], viewPos;\nADD   noise.x, worldPos.x, worldPos.z;SUB   noise.y, worldPos.z, worldPos.x;MUL   noise, noise, 0.0001220703125;\nFRC   noise, noise;\nMUL   noise, noise, 64;\nARL   noiseAddr.x, noise.x;\nMOV   noise.x, fNoise[noiseAddr.x].x;\nARL   noiseAddr.x, noise.y;\nMOV   noise.y, fNoise[noiseAddr.x].y;\nMUL   noise, noise, turbulence.x;\nMAD   oTexCoord0, worldPos.xzww, 0.0078125, noise;\nMOV   oTexCoord0.w, 1;\nMUL   oTexCoord1.xy, worldPos.xzww, 0.0009765625;\nMOV   oTexCoord1.zw, time.xxxw;\nDP4   clipPos.x, pMatrix[0], viewPos;\nDP4   clipPos.y, pMatrix[1], viewPos;\nDP4   clipPos.z, pMatrix[2], viewPos;\nDP4   clipPos.w, pMatrix[3], viewPos;\nMUL   oColour.xyz, iColour, lightAmbient;\nMOV   oColour.w, 1;\nMOV   oFogCoord.x, clipPos.z;\nMOV   oPos, clipPos; \nEND");
         var1.glGetIntegerv('\u864b', var2, 0);
         if(var2[0] != -1) {
            return;
         }
      }

   }

   public final void method23(int var1) {
      if(this.anInt2186 >= 0) {
         javax.media.opengl.GL var2 = HDToolKit.gl;
         var2.glActiveTexture('\u84c1');
         if((var1 & 128) == 0) {
            var2.glEnable(Class88.aBoolean1227?'\u806f':3553);
         } else {
            var2.glDisable(Class88.aBoolean1227?'\u806f':3553);
         }

         var2.glActiveTexture('\u84c0');
         if((var1 & 64) == 0) {
            var2.glGetFloatv(2899, aFloatArray2185, 0);
            var2.glProgramLocalParameter4fvARB('\u8620', 66, aFloatArray2185, 0);
         } else {
            var2.glProgramLocalParameter4fARB('\u8620', 66, 1.0F, 1.0F, 1.0F, 1.0F);
         }

         int var3 = var1 & 3;
         if(var3 == 2) {
            var2.glProgramLocalParameter4fARB('\u8620', 64, 0.05F, 1.0F, 1.0F, 1.0F);
         } else if(var3 == 3) {
            var2.glProgramLocalParameter4fARB('\u8620', 64, 0.1F, 1.0F, 1.0F, 1.0F);
         } else {
            var2.glProgramLocalParameter4fARB('\u8620', 64, 0.025F, 1.0F, 1.0F, 1.0F);
         }

      }
   }

   public Class125() {
       if(HDToolKit.aBoolean1818 && HDToolKit.anInt1789 >= 2) {
          int[] var1 = new int[1];
          GL var2 = HDToolKit.gl;
          var2.glGenProgramsARB(1, var1, 0);
          this.anInt2184 = var1[0];
          int[][] var3 = Class15.method895(0, (byte)-73);
          int[][] var4 = Class15.method895(8, (byte)-109);
          RSByteBuffer var5 = new RSByteBuffer(262144);

          for(int var6 = 0; var6 < 256; ++var6) {
             int[] var7 = var3[var6];
             int[] var8 = var4[var6];

             for(int var9 = 0; var9 < 64; ++var9) {
                if(HDToolKit.aBoolean1790) {
                   var5.method801((float)var7[var9] / 4096.0F);
                   var5.method801((float)var8[var9] / 4096.0F);
                   var5.method801(1.0F);
                   var5.method801(1.0F);
                } else {
                   var5.method762((float)var7[var9] / 4096.0F, (byte)109);
                   var5.method762((float)var8[var9] / 4096.0F, (byte)66);
                   var5.method762(1.0F, (byte)116);
                   var5.method762(1.0F, (byte)66);
                }
             }
          }

          ByteBuffer var10 = ByteBuffer.allocateDirect(var5.index).order(ByteOrder.nativeOrder());
          var10.put(var5.buffer, 0, var5.index);
          var10.flip();
          this.aFloatBuffer2183 = var10.asFloatBuffer().asReadOnlyBuffer();
          this.method1749();
          this.method1750();
       }

   }

}
