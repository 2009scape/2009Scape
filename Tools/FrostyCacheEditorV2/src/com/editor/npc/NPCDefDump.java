/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.editor.npc;

import com.alex.loaders.npcs.NPCDefinitions;
import com.alex.store.Store;
import com.alex.utils.Utils;
import com.editor.Main;
import static com.editor.npc.NPCSelection.STORE;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 *
 * @author Travis
 */
public class NPCDefDump {

    private static NPCDefinitions defs;
    private static Store STORE;

    public static void main(String args[]) {
        try {
            STORE = new Store("C:/Users/Travis/Documents/rscd/data/");
        } catch (IOException ex) {
            System.out.println("Cannot find cache location");
        }
        if (Utils.getNPCDefinitionsSize(STORE) > 30000) {
            for (int id = 0; id < Utils.getNPCDefinitionsSize(STORE) - 18433; id++) {
                defs = NPCDefinitions.getNPCDefinition(STORE, id);
                dump();
                Main.log("NPCDefDump", "Dumping NPC " + defs.id);
            }
        } else {
            for (int id = 0; id < Utils.getNPCDefinitionsSize(STORE); id++) {
                defs = NPCDefinitions.getNPCDefinition(STORE, id);
                dump();
                Main.log("NPCDefDump", "Dumping NPC " + defs.id);
            }
        }

    }

    public static void editorDump(String cache) {
        try {
            STORE = new Store(cache);
        } catch (IOException ex) {
            Main.log("NPCDefDump", "Cannot find cache location");
        }
        if (Utils.getNPCDefinitionsSize(STORE) > 30000) {
            for (int id = 0; id < Utils.getNPCDefinitionsSize(STORE) - 15615; id++) {
                defs = NPCDefinitions.getNPCDefinition(STORE, id);
                dump();
                Main.log("NPCDefDump", "Dumping NPC " + defs.id);
            }
        } else {
            for (int id = 0; id < Utils.getNPCDefinitionsSize(STORE); id++) {
                defs = NPCDefinitions.getNPCDefinition(STORE, id);
                dump();
                Main.log("NPCDefDump", "Dumping NPC " + defs.id);
            }
        }

    }

    private static void dump() {
        File f = new File(System.getProperty("user.home") + "/FrostyCacheEditor/npcs/");
        f.mkdirs();
        String lineSep = System.getProperty("line.separator");
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(System.getProperty("user.home") + "/FrostyCacheEditor/npcs/" + defs.id + ".txt"), "utf-8"));
            writer.write("name = " + defs.getName());
            writer.write(lineSep);
            writer.write("combat level = " + defs.getCombatLevel());
            writer.write(lineSep);
            writer.write("isVisibleOnMap = " + defs.drawMinimapDot);
            writer.write(lineSep);
            writer.write("height = " + defs.height);
            writer.write(lineSep);
            writer.write("width = " + defs.width);
            writer.write(lineSep);
            writer.write("walk mask = " + defs.walkMask);
            writer.write(lineSep);
            writer.write("respawn direction = " + defs.respawnDirection);
            writer.write(lineSep);
            writer.write("render emote = " + defs.renderTypeID);
            writer.write(lineSep);
            writer.write("model ids = " + getModelIds());
            writer.write(lineSep);
            writer.write("chat head model ids = " + getChatHeads());
            writer.write(lineSep);
            writer.write("options = " + getOpts());
            writer.write(lineSep);
            writer.write("model colors = " + getChangedModelColors());
            writer.write(lineSep);
            writer.write("texture colors = " + getChangedTextureColors());
            writer.write(lineSep);
            writer.write("unknown array1 = " + getUnknownArray1());
            writer.write(lineSep);
            writer.write("unknown array2 = " + getUnknownArray2());
            writer.write(lineSep);
            writer.write("unknown array3 = " + getUnknownArray3());
            writer.write(lineSep);
            writer.write("unknown array4 = " + getUnknownArray4());
            writer.write(lineSep);
            writer.write("unknown array5 = " + getUnknownArray5());
            writer.write(lineSep);
            writer.write("unknownBoolean1 = " + defs.isVisible);
            writer.write(lineSep);
            writer.write("unknwonBoolean2 = " + defs.clickable);
            writer.write(lineSep);
            writer.write("unknownBoolean3 = " + defs.aBool8492);
            writer.write(lineSep);
            writer.write("unknownBoolean4 = " + defs.aBool8460);
            writer.write(lineSep);
            writer.write("unknownBoolean5 = " + defs.aBook8472);
            writer.write(lineSep);
            writer.write("unknownBoolean6 = " + defs.aBool8459);
            writer.write(lineSep);
            writer.write("unknownBoolean7 = " + defs.unknownBoolean7);
            writer.write(lineSep);
            writer.write("unknown int1 = " + defs.ambient);
            writer.write(lineSep);
            writer.write("unknown int2 = " + defs.contrast);
            writer.write(lineSep);
            writer.write("unknown int3 = " + defs.degreesToTurn);
            writer.write(lineSep);
            writer.write("unknown int4 = " + defs.transVarBit);
            writer.write(lineSep);
            writer.write("unknown int5 = " + defs.transVar);
            writer.write(lineSep);
            writer.write("unknown int6 = " + defs.unknownInt6);
            writer.write(lineSep);
            writer.write("unknown int7 = " + defs.anInt8443);
            writer.write(lineSep);
            writer.write("unknown int8 = " + defs.anInt8466);
            writer.write(lineSep);
            writer.write("unknown int9 = " + defs.anInt8483);
            writer.write(lineSep);
            writer.write("unknown int10 = " + defs.anInt8484);
            writer.write(lineSep);
            writer.write("unknown int11 = " + defs.anInt8455);
            writer.write(lineSep);
            writer.write("unknown int12 = " + defs.anInt8457);
            writer.write(lineSep);
            writer.write("unknown int13 = " + defs.unknownInt13);
            writer.write(lineSep);
            writer.write("unknown int14 = " + defs.unknownInt14);
            writer.write(lineSep);
            writer.write("unknown int15 = " + defs.unknownInt15);
            writer.write(lineSep);
            writer.write("unknown int16 = " + defs.unknownInt16);
            writer.write(lineSep);
            writer.write("unknown int17 = " + defs.attackOpCursor);
            writer.write(lineSep);
            writer.write("unknown int18 = " + defs.anInt8465);
            writer.write(lineSep);
            writer.write("unknown int19 = " + defs.unknownInt19);
            writer.write(lineSep);
            writer.write("unknown int20 = " + defs.anInt8488);
            writer.write(lineSep);
            writer.write("unknown int21 = " + defs.mapIcon);
            writer.write(lineSep);
            writer.write("Clientscripts");
            writer.write(lineSep);
            if (defs.config != null) {
                for (int key : defs.config.keySet()) {
                    Object value = defs.config.get(key);
                    writer.write("KEY: " + key + ", VALUE: " + value);
                    writer.write(lineSep);
                }
            }
        } catch (IOException ex) {
            Main.log("NPCDefDump", "Failed to export NPC Defs to .txt");
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getClientScripts() {
        String text = "";
        String lineSep = System.getProperty("line.separator");
        if (defs.config != null) {
            for (int key : defs.config.keySet()) {
                Object value = defs.config.get(key);
                text += "KEY: " + key + ", VALUE: " + value;
                text += lineSep;
            }
        }
        return text;
    }

    public static String getModelIds() {
        String text = "";
        try {
            for (int index : defs.meshes) {
                text += index + ";";
            }
        } catch (Exception e) {
        }
        return text;
    }

    public static String getOpts() {
        String text = "";
        for (String option : defs.getOptions()) {
            text += (option == null ? "null" : option) + ";";
        }
        return text;
    }

    public static String getChangedModelColors() {
        String text = "";
        if (defs.recolorDst != null) {
            for (int i = 0; i < defs.recolorDst.length; i++) {
                text += defs.recolorDst[i] + "=" + defs.recolorSrc[i] + ";";
            }
        }
        return text;
    }

    public static String getChangedTextureColors() {
        String text = "";
        if (defs.retextureSrc != null) {
            for (int i = 0; i < defs.retextureSrc.length; i++) {
                text += defs.retextureSrc[i] + "=" + defs.retextureDst[i] + ";";
            }
        }
        return text;
    }

    public static String getChatHeads() {
        String text = "";
        try {
            for (int id : defs.interfaceModelId) {
                text += id + ";";
            }
        } catch (Exception e) {
        }
        return text;
    }

    public static String getUnknownArray1() {
        String text = "";
        try {
            for (int index : defs.recolorDstPalette) {
                text += index + ";";
            }
        } catch (Exception e) {
        }
        return text;
    }

    public static String getUnknownArray2() {
        String text = "";
        try {
            for (int index : defs.transforms) {
                text += index + ";";
            }
        } catch (Exception e) {
        }
        return text;
    }

    public static String getUnknownArray3() {
        String text = "";
        try {
            for (int index : defs.anIntArrayArray8478[0]) {
                text += index + ";";
            }
        } catch (Exception e) {
        }
        return text;
    }

    public static String getUnknownArray4() {
        String text = "";
        try {
            for (int index : defs.anIntArray8493) {
                text += index + ";";
            }
        } catch (Exception e) {
        }
        return text;
    }

    public static String getUnknownArray5() {
        String text = "";
        try {
            for (int index : defs.cursorOps) {
                text += index + ";";
            }
        } catch (Exception e) {
        }
        return text;
    }
}
