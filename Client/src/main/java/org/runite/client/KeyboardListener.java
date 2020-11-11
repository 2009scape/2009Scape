package org.runite.client;
import org.rs09.client.config.GameConfig;
import org.rs09.client.console.DeveloperConsole;
import org.rs09.client.data.ReferenceCache;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

final class KeyboardListener implements KeyListener, FocusListener {

   static int[] KEY_CODE_MAP = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, 85, 80, 84, -1, 91, -1, -1, -1, 81, 82, 86, -1, -1, -1, -1, -1, -1, -1, -1, 13, -1, -1, -1, -1, 83, 104, 105, 103, 102, 96, 98, 97, 99, -1, -1, -1, -1, -1, -1, -1, 25, 16, 17, 18, 19, 20, 21, 22, 23, 24, -1, -1, -1, -1, -1, -1, -1, 48, 68, 66, 50, 34, 51, 52, 53, 39, 54, 55, 56, 70, 69, 40, 41, 32, 35, 49, 36, 38, 67, 33, 65, 37, 64, -1, -1, -1, -1, -1, 228, 231, 227, 233, 224, 219, 225, 230, 226, 232, 89, 87, -1, 88, 229, 90, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, -1, -1, -1, 101, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 100, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
   static boolean aBoolean1905 = true;
   static int anInt1908 = 0;
   static ReferenceCache aReferenceCache_1911 = new ReferenceCache(260);
   static int loginThemeSongArchiveID;
   static int anInt1914;
   static CacheIndex aClass153_1916;
   static int anInt1918 = 0;
    static int anInt2384 = 0;
    static int[] anIntArray1978 = new int[128];

    static void adjustKeyCodeMap() {
         if(!Signlink.javaVendor.toLowerCase().contains("microsoft")) {
            if(null == Signlink.setTraversalKeysEnabled) {
               KEY_CODE_MAP[192] = 58;
               KEY_CODE_MAP[222] = 59;
            } else {
               KEY_CODE_MAP[222] = 58;
               KEY_CODE_MAP[192] = 28;
               KEY_CODE_MAP[520] = 59;
            }

            KEY_CODE_MAP[45] = 26;
            KEY_CODE_MAP[61] = 27;
            KEY_CODE_MAP[91] = 42;
            KEY_CODE_MAP[59] = 57;
            KEY_CODE_MAP[93] = 43;
            KEY_CODE_MAP[44] = 71;
            KEY_CODE_MAP[92] = 74;
            KEY_CODE_MAP[46] = 72;
            KEY_CODE_MAP[47] = 73;
         } else {
            KEY_CODE_MAP[187] = 27;
            KEY_CODE_MAP[223] = 28;
            KEY_CODE_MAP[221] = 43;
            KEY_CODE_MAP[188] = 71;
            KEY_CODE_MAP[222] = 59;
            KEY_CODE_MAP[192] = 58;
            KEY_CODE_MAP[191] = 73;
            KEY_CODE_MAP[219] = 42;
            KEY_CODE_MAP[190] = 72;
            KEY_CODE_MAP[186] = 57;
            KEY_CODE_MAP[220] = 74;
            KEY_CODE_MAP[189] = 26;
         }
   }


   public final synchronized void keyPressed(KeyEvent var1) {
      try {
//         System.out.println("Key code: " + var1.getKeyCode());
         switch (var1.getKeyCode())
         {
            case 16:
               MouseWheel.shiftDown = true;
               break;
            case 17:
               MouseWheel.ctrlDown = true;
               break;
            case 192:
               DeveloperConsole.INSTANCE.toggle();
               return;
         }
//         if (DeveloperConsole.INSTANCE.getOpen()) {
//            DeveloperConsole.INSTANCE.handleKeyDown(var1);
//            return;
//         }

         if(null != Class3_Sub13_Sub3.aClass148_3049) {
            Class3_Sub13_Sub33.anInt3398 = 0;
            int var2 = var1.getKeyCode();
//            switch(var2) {
//               case 112://f1
//                  var2 = 115;
//                  break;
//               case 113://f2
//                  var2 = 116;
//                  break;
//               case 114://f3
//                  var2 = 117;
//                  break;
//               case 115://f4
//                  var2 = 118;
//                  break;
//               case 116://f5
//                  var2 = 112;
//                  break;
//            }
            if(0 <= var2 && KEY_CODE_MAP.length > var2) {
               var2 = KEY_CODE_MAP[var2];
               // System.out.println(var2);
               if(0 != (var2 & 128)) {
                  var2 = -1;
               }
            } else {
               var2 = -1;
            }
            
            
            
            /**
             * Tab to reply
             */
			if (var1.getKeyCode() == KeyEvent.VK_TAB) {
				ClientCommands.ClientCommands(RSString.parse("::reply"));
			}

			if (var1.getKeyCode() == KeyEvent.VK_ESCAPE)
            {
               ClientCommands.ClientCommands(RSString.parse("::shutdowninterface"));
            }
			
			//causing issues when in other interfaces, such as GE interface. My quick thing impl didn't work - Jamix77
			
			/**if (var1.getKeyCode() == KeyEvent.VK_SPACE)
            {
               Class2.method78(40, false, 0);
            }**/
			
            
            /**
             * Controls arrow key mouse movement
             */
            if(anInt2384 >= 0 && var2 >= 0) {
               Unsorted.anIntArray2952[anInt2384] = var2;
               anInt2384 = 127 & anInt2384 - -1;
               if(anInt2384 == Class32.anInt1744) {
                  anInt2384 = -1;
               }
            }

            int var3;
            if(var2 >= 0) {
               var3 = 127 & 1 + Class25.anInt491;
               if(var3 != Class3_Sub28_Sub9.anInt3620) {
                  Class129.anIntArray1693[Class25.anInt491] = var2;
                  anIntArray1978[Class25.anInt491] = -1;
                  Class25.anInt491 = var3;
               }
            }

            var3 = var1.getModifiers();
            if((var3 & 10) != 0 || 85 == var2 || var2 == 10) {
               var1.consume();
            }
         }

      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "uf.keyPressed(" + (var1 != null?"{...}":"null") + ')');
      }
   }

   public final void keyTyped(KeyEvent var1) {
      if (DeveloperConsole.INSTANCE.getOpen()) {
         DeveloperConsole.INSTANCE.handleKeyPressed(var1);
         return;
      }
      if(var1.isAltDown()){
         if(var1.getKeyChar()=='n'){
            GameConfig.NPC_DEBUG_ENABLED = !GameConfig.NPC_DEBUG_ENABLED;
            Class3_Sub30_Sub1.addChatMessage(TextCore.clientDebugNotifier, 0, RSString.parse("NPC debug context " + (GameConfig.NPC_DEBUG_ENABLED ? "enabled." : "disabled.")), -1);
            return;
         }
         if(var1.getKeyChar()=='o'){
            GameConfig.OBJECT_DEBUG_ENABLED = !GameConfig.OBJECT_DEBUG_ENABLED;
            Class3_Sub30_Sub1.addChatMessage(TextCore.clientDebugNotifier, 0, RSString.parse("Object debug context " + (GameConfig.OBJECT_DEBUG_ENABLED ? "enabled." : "disabled.")), -1);
            return;
         }
         if(var1.getKeyChar()=='i'){
            GameConfig.ITEM_DEBUG_ENABLED = !GameConfig.ITEM_DEBUG_ENABLED;
            Class3_Sub30_Sub1.addChatMessage(TextCore.clientDebugNotifier, 0, RSString.parse("Item debug context " + (GameConfig.ITEM_DEBUG_ENABLED ? "enabled." : "disabled.")), -1);
            return;
         }
         return;
      }
      try {
    	 
         if(Class3_Sub13_Sub3.aClass148_3049 != null) {
            int var2 = Class79.method1386(var1);
            if(var2 >= 0) {
               int var3 = 1 + Class25.anInt491 & 127;
               if(var3 != Class3_Sub28_Sub9.anInt3620) {
                  Class129.anIntArray1693[Class25.anInt491] = -1;
                  anIntArray1978[Class25.anInt491] = var2;
                  Class25.anInt491 = var3;
               }
            }
         }

         var1.consume();
      } catch (RuntimeException var4) {
         throw ClientErrorException.clientError(var4, "uf.keyTyped(" + (var1 != null?"{...}":"null") + ')');
      }
   }

   public final synchronized void focusLost(FocusEvent var1) {
      if(Class3_Sub13_Sub3.aClass148_3049 != null) {
         anInt2384 = -1;
      }
   }

   public final synchronized void keyReleased(KeyEvent var1) {
      if (DeveloperConsole.INSTANCE.getOpen()) {
         return;
      }

      switch (var1.getKeyCode())
      {
         case 16:
            MouseWheel.shiftDown = false;
            break;
         case 17:
            MouseWheel.ctrlDown = false;
            break;
      }
      try {
         if(null != Class3_Sub13_Sub3.aClass148_3049) {
            Class3_Sub13_Sub33.anInt3398 = 0;
            int var2 = var1.getKeyCode();
            if(var2 >= 0 && var2 < KEY_CODE_MAP.length) {
               var2 = KEY_CODE_MAP[var2] & -129;
            } else {
               var2 = -1;
            }

            if(anInt2384 >= 0 && var2 >= 0) {
               Unsorted.anIntArray2952[anInt2384] = ~var2;
               anInt2384 = 127 & 1 + anInt2384;
               if(anInt2384 == Class32.anInt1744) {
                  anInt2384 = -1;
               }
            }
         }

         var1.consume();
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "uf.keyReleased(" + "{...}" + ')');
      }
   }

   public final void focusGained(FocusEvent var1) {}

}
