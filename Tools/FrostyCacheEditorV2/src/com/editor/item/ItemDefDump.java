/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.editor.item;

import com.alex.loaders.items.ItemDefinitions;
import com.alex.store.Store;
import com.alex.utils.Utils;
import com.editor.Main;
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
public class ItemDefDump {

    private static ItemDefinitions defs;
    private static Store STORE;

    public static void main(String args[]) {
        try {
            STORE = new Store("C:/Users/Travis/Documents/751 cache/");
        } catch (IOException ex) {
            System.out.println("Cannot find cache location");
        }
        if (Utils.getItemDefinitionsSize(STORE) > 30000) {
            for (int id = 0; id < Utils.getItemDefinitionsSize(STORE) - 22314; id++) {
                defs = ItemDefinitions.getItemDefinition(STORE, id);
                dump();
                Main.log("ItemDefDump", "Dumping Item "+defs.id);
            }
        } else {
            for (int id = 0; id < Utils.getItemDefinitionsSize(STORE); id++) {
                defs = ItemDefinitions.getItemDefinition(STORE, id);
                dump();
                Main.log("ItemDefDump", "Dumping Item "+defs.id);
            }
        }

    }
    
    public static void editorDump(String cache) {
        try {
            STORE = new Store(cache);
        } catch (IOException ex) {
            Main.log("ItemDefDump", "Cannot find cache location");
        }
        if (Utils.getItemDefinitionsSize(STORE) > 30000) {
            for (int id = 0; id < Utils.getItemDefinitionsSize(STORE) - 22314; id++) {
                defs = ItemDefinitions.getItemDefinition(STORE, id);
                dump();
                Main.log("ItemDefDump", "Dumping Item "+defs.id);
            }
        } else {
            for (int id = 0; id < Utils.getItemDefinitionsSize(STORE); id++) {
                defs = ItemDefinitions.getItemDefinition(STORE, id);
                dump();
                Main.log("ItemDefDump", "Dumping Item "+defs.id);
            }
        }

    }

    public static void dump() {
        File f = new File(System.getProperty("user.home") + "/FrostyCacheEditor/items/");
        f.mkdirs();
        String lineSep = System.getProperty("line.separator");
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(System.getProperty("user.home") + "/FrostyCacheEditor/items/" + defs.id + ".txt"), "utf-8"));
            writer.write("name = " + defs.getName());
            writer.write(lineSep);
            writer.write("value = " + defs.getValue());
            writer.write(lineSep);
            writer.write("team id = " + defs.getTeamId());
            writer.write(lineSep);
            writer.write("members only = " + String.valueOf(defs.isMembersOnly()));
            writer.write(lineSep);
            writer.write("equip slot = " + defs.getEquipSlot());
            writer.write(lineSep);
            writer.write("equip type = " + defs.getEquipType());
            writer.write(lineSep);
            writer.write("stack ids = " + getStackIDs());
            writer.write(lineSep);
            writer.write("stack amounts = " + getStackAmts());
            writer.write(lineSep);
            writer.write("stackable = " + defs.getStackable());
            writer.write(lineSep);
            writer.write("inv model zoom = " + defs.getInvModelZoom());
            writer.write(lineSep);
            writer.write("model rotation 1 = " + defs.getModelRotation1());
            writer.write(lineSep);
            writer.write("model rotation 2 = " + defs.getModelRotation2());
            writer.write(lineSep);
            writer.write("model offset 1 = " + defs.getModelOffset1());
            writer.write(lineSep);
            writer.write("model offset 2 = " + defs.getModelOffset2());
            writer.write(lineSep);
            writer.write("inv model id = " + defs.getInvModelId());
            writer.write(lineSep);
            writer.write("male equip model id 1 = " + defs.getMaleEquipModelId1());
            writer.write(lineSep);
            writer.write("female equip model id 1 = " + defs.getFemaleEquipModelId1());
            writer.write(lineSep);
            writer.write("male equip model id 2 = " + defs.getMaleEquipModelId2());
            writer.write(lineSep);
            writer.write("female equip model id 2 = " + defs.getFemaleEquipModelId2());
            writer.write(lineSep);
            writer.write("male equip model id 3 = " + defs.getMaleEquipModelId3());
            writer.write(lineSep);
            writer.write("female equip model id 3 = " + defs.getFemaleEquipModelId3());
            writer.write(lineSep);
            writer.write("inventory options = " + getInventoryOpts());
            writer.write(lineSep);
            writer.write("ground options = " + getGroundOpts());
            writer.write(lineSep);
            writer.write("changed model colors = " + getChangedModelColors());
            writer.write(lineSep);
            writer.write("changed texture colors = " + getChangedTextureColors());
            writer.write(lineSep);
            writer.write("switch note item id = " + defs.certLink);
            writer.write(lineSep);
            writer.write("noted item id = " + defs.certTemplate);
            writer.write(lineSep);
            writer.write("unnoted = " + String.valueOf(defs.isUnnoted()));
            writer.write(lineSep);
            writer.write("switch lend item id = " + defs.getSwitchLendItemId());
            writer.write(lineSep);
            writer.write("lended item id = " + defs.getLendedItemId());
            writer.write(lineSep);
            writer.write("unknownArray1 = " + getUnknownArray1());
            writer.write(lineSep);
            writer.write("unknownArray2 = " + getUnknownArray2());
            writer.write(lineSep);
            writer.write("unknownArray3 = " + getUnknownArray3());
            writer.write(lineSep);
            writer.write("unknownArray4 = " + getUnknownArray4());
            writer.write(lineSep);
            writer.write("unknownArray5 = " + getUnknownArray5());
            writer.write(lineSep);
            writer.write("unknownArray6 = " + getUnknownArray6());
            writer.write(lineSep);
            writer.write("unknownInt1 = " + defs.manHead);
            writer.write(lineSep);
            writer.write("unknownInt2 = " + defs.womanHead);
            writer.write(lineSep);
            writer.write("unknownInt3 = " + defs.manHead2);
            writer.write(lineSep);
            writer.write("unknownInt4 = " + defs.womanHead2);
            writer.write(lineSep);
            writer.write("unknownInt5 = " + defs.zAngle2D);
            writer.write(lineSep);
            writer.write("unknownInt6 = " + defs.dummyItem);
            writer.write(lineSep);
            writer.write("unknownInt7 = " + defs.resizeX);
            writer.write(lineSep);
            writer.write("unknownInt8 = " + defs.resizeY);
            writer.write(lineSep);
            writer.write("unknownInt9 = " + defs.resizeZ);
            writer.write(lineSep);
            writer.write("unknownInt10 = " + defs.ambient);
            writer.write(lineSep);
            writer.write("unknownInt11 = " + defs.contrast);
            writer.write(lineSep);
            writer.write("unknownInt12 = " + defs.manWearXOffset);
            writer.write(lineSep);
            writer.write("unknownInt13 = " + defs.manWearYOffset);
            writer.write(lineSep);
            writer.write("unknownInt14 = " + defs.manWearZOffset);
            writer.write(lineSep);
            writer.write("unknownInt15 = " + defs.womanWearXOffset);
            writer.write(lineSep);
            writer.write("unknownInt16 = " + defs.womanWearYOffset);
            writer.write(lineSep);
            writer.write("unknownInt17 = " + defs.womanWearZOffset);
            writer.write(lineSep);
            writer.write("unknownInt18 = " + defs.groundCursorOp);
            writer.write(lineSep);
            writer.write("unknownInt19 = " + defs.groundCursor);
            writer.write(lineSep);
            writer.write("unknownInt20 = " + defs.cursor2op);
            writer.write(lineSep);
            writer.write("unknownInt21 = " + defs.cursor2);
            writer.write(lineSep);
            writer.write("unknownInt22 = " + defs.cursor2iop);
            writer.write(lineSep);
            writer.write("unknownInt23 = " + defs.icursor2);
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
            Main.log("ItemEditor", "Failed to export Item Defs to .txt");
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getInventoryOpts() {
        String text = "";
        for (String option : defs.getInventoryOptions()) {
            text += (option == null ? "null" : option) + ";";
        }
        return text;
    }

    public static String getGroundOpts() {
        String text = "";
        for (String option : defs.getGroundOptions()) {
            text += (option == null ? "null" : option) + ";";
        }
        return text;
    }

    public static  String getChangedModelColors() {
        String text = "";
        if (defs.recolorSrc != null) {
            for (int i = 0; i < defs.recolorSrc.length; i++) {
                text += defs.recolorSrc[i] + "=" + defs.recolorDst[i] + ";";
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

    public static String getStackIDs() {
        String text = "";
        try {
            for (int index : defs.getStackIds()) {
                text += index + ";";
            }
        } catch (Exception e) {
        }
        return text;
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

    public static String getStackAmts() {
        String text = "";
        try {
            for (int index : defs.getStackAmounts()) {
                text += index + ";";
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
            for (int index : defs.unknownArray2) {
                text += index + ";";
            }
        } catch (Exception e) {
        }
        return text;
    }

    public static String getUnknownArray3() {
        String text = "";
        try {
            for (int index : defs.unknownArray3) {
                text += index + ";";
            }
        } catch (Exception e) {
        }
        return text;
    }

    public static String getUnknownArray4() {
        String text = "";
        try {
            for (int index : defs.unknownArray4) {
                text += index + ";";
            }
        } catch (Exception e) {
        }
        return text;
    }

    public static String getUnknownArray5() {
        String text = "";
        try {
            for (int index : defs.unknownArray5) {
                text += index + ";";
            }
        } catch (Exception e) {
        }
        return text;
    }

    public static String getUnknownArray6() {
        String text = "";
        try {
            for (int index : defs.unknownArray6) {
                text += index + ";";
            }
        } catch (Exception e) {
        }
        return text;
    }
}
