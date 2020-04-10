package com.cc.tools;

import com.alex.loaders.npcs.NPCDefinitions;
import com.alex.store.Store;
import static com.cc.tools.ItemPack.utilizeSettings;
import com.cc.ui.NPCUI;

/**
 *
 * @author travis
 */
public class NPCPack {

    private static String currentCacheLocation;
    private static int npcID = -1;
    private static int copyNPC = -1;
    private static String npcName = "null";
    private static int npcSize = 1;
    private static int renderAnimation = -1;
    private static int spawnDirection = 0;
    private static boolean invisibleOnMap = false;
    private static String[] options = new String[5];
    private static int[] modelIds = new int[12];
    private static int combatLevel;
    private static int chatHeadIds = -1;

    public static void setSettings(NPCUI npc) {
        currentCacheLocation = npc.getCurrentCacheLocation();
        npcID = Integer.parseInt(npc.getNpcID().getText());
        copyNPC = Integer.parseInt(npc.getCopyNPCID().getText());
        npcName = npc.getNpcName().getText();
        npcSize = Integer.parseInt(npc.getNpcSize().getText());
        renderAnimation = Integer.parseInt(npc.getRenderAnimation().getText());
        spawnDirection = Integer.parseInt(npc.getSpawnDirection().getText());
        try {
            combatLevel = Integer.parseInt(npc.getCombatLevel().getText());
        } catch (Exception e) {
        }
        chatHeadIds = Integer.parseInt(npc.getChatHeadModel().getText());
        if (npc.getOpt0().getText().equalsIgnoreCase("") || npc.getOpt0().getText().equalsIgnoreCase("null")) {
            options[0] = null;
        } else {
            options[0] = npc.getOpt0().getText();
        }
        if (npc.getOpt1().getText().equalsIgnoreCase("") || npc.getOpt1().getText().equalsIgnoreCase("null")) {
            options[1] = null;
        } else {
            options[1] = npc.getOpt1().getText();
        }
        if (npc.getOpt2().getText().equalsIgnoreCase("") || npc.getOpt2().getText().equalsIgnoreCase("null")) {
            options[2] = null;
        } else {
            options[2] = npc.getOpt2().getText();
        }
        if (npc.getOpt3().getText().equalsIgnoreCase("") || npc.getOpt3().getText().equalsIgnoreCase("null")) {
            options[3] = null;
        } else {
            options[3] = npc.getOpt3().getText();
        }
        if (npc.getOpt4().getText().equalsIgnoreCase("") || npc.getOpt4().getText().equalsIgnoreCase("null")) {
            options[4] = null;
        } else {
            options[4] = npc.getOpt4().getText();
        }

        try {
            modelIds[0] = Integer.parseInt(npc.getNpcModel1().getText());
        } catch (Exception e) {
        }
        try {
            modelIds[1] = Integer.parseInt(npc.getNpcModel2().getText());
        } catch (Exception e) {
        }
        try {
            modelIds[2] = Integer.parseInt(npc.getNpcModel3().getText());
        } catch (Exception e) {
        }
        try {
            modelIds[3] = Integer.parseInt(npc.getNpcModel4().getText());
        } catch (Exception e) {
        }
        try {
            modelIds[4] = Integer.parseInt(npc.getNpcModel5().getText());
        } catch (Exception e) {
        }
        try {
            modelIds[5] = Integer.parseInt(npc.getNpcModel6().getText());
        } catch (Exception e) {
        }
        try {
            modelIds[6] = Integer.parseInt(npc.getNpcModel7().getText());
        } catch (Exception e) {
        }
        try {
            modelIds[7] = Integer.parseInt(npc.getNpcModel8().getText());
        } catch (Exception e) {
        }
        try {
            modelIds[8] = Integer.parseInt(npc.getNpcModel9().getText());
        } catch (Exception e) {
        }
        try {
            modelIds[9] = Integer.parseInt(npc.getNpcModel10().getText());
        } catch (Exception e) {
        }
        try {
            modelIds[10] = Integer.parseInt(npc.getNpcModel11().getText());
        } catch (Exception e) {
        }
        try {
            modelIds[11] = Integer.parseInt(npc.getNpcModel12().getText());
        } catch (Exception e) {
        }
    }

    public static void utilizeSettings(Store cache) {
        boolean result;
        NPCDefinitions npcDef = NPCDefinitions.getNPCDefinition(cache, copyNPC);
        npcDef.setName(npcName);
        npcDef.setSize(npcSize);
        npcDef.setCombatLevel(combatLevel);
        npcDef.setVisibleOnMap(invisibleOnMap);//Makes whole NPC invisible - Most NPCs leave FALSE
        npcDef.setRenderEmote(renderAnimation);
        npcDef.setOptions(options);
        npcDef.setRespawnDirection((byte) spawnDirection);
        npcDef.chatHeadsArray[0] = chatHeadIds;
        npcDef.modelIds = modelIds;
        result = cache.getIndexes()[18].putFile(npcID >>> 134238215, 0x7f & npcID, npcDef.encode());
    }

    public static void proceedPacking() {
        Store cache = null;
        try {
            cache = new Store(currentCacheLocation);
        } catch (Exception e) {
            log("Unable to find the Current Cache Location.");
        }
        try {
            utilizeSettings(cache);
        } catch (Exception e) {
            log("Unable to pack with current settings.");
            log("Please revise your settings and try again.");
        }
        log("Finished Packing Custom NPC Process.");
    }

    private static void log(String s) {
        System.out.println(s);
    }
}
