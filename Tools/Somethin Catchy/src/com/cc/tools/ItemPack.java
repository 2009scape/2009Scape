package com.cc.tools;

import com.alex.loaders.items.ItemDefinitions;
import com.alex.store.Store;
import static com.cc.tools.NormalPack.utilizeSettings;
import com.cc.ui.ItemUI;

/**
 *
 * @author travis
 */
public class ItemPack {
    private static String currentCacheLocation;
    private static int itemID = -1;
    private static String itemName = "null";
    private static int itemValue = -1;
    private static int equipType = -1;
    private static int notedItemID = -1;
    private static boolean membersOnly = false;
    private static int copyItemID = -1;
    private static int equipSlot = -1;
    private static int invModel = -1;
    private static int invModelZoom = 2000;
    private static int maleEquip1 = -1;
    private static int femaleEquip1 = -1;
    private static int maleEquip2 = -1;
    private static int femaleEquip2 = -1;
    private static int maleEquip3 = -1;
    private static int femaleEquip3 = -1;
    private static int teamID = 255;
    private static boolean stackable = false;
    private static int lendItemID = -1;
    private static int switchLendItemID = -1;
    private static int weaponType = -1;
    private static int renderAnimation = -1;
    private static String[] changedModelColors;
    private static String[] changedTextureColors;
    private static String[] invOptions = new String[5];
    private static String[] groundOptions = new String[5];
    private static int modelOffset1;
    private static int modelOffset2;
    private static int modelRotation1;
    private static int modelRotation2;

    public static void setSettings(ItemUI item) {
        currentCacheLocation = item.getCurrentCacheLocation();
        itemID = Integer.parseInt(item.getItemID().getText());
        itemName = item.getItemName().getText();
        itemValue = Integer.parseInt(item.getItemValue().getText());
        equipType = Integer.parseInt(item.getEquipType().getText());
        notedItemID = Integer.parseInt(item.getNotedItemID().getText());
        membersOnly = item.getMembersOnly().isSelected();
        copyItemID = Integer.parseInt(item.getCopyItemID().getText());
        equipSlot = item.getEquipSlot().getSelectedIndex();
        invModel = Integer.parseInt(item.getInvModel().getText());
        invModelZoom = Integer.parseInt(item.getInvModelZoom().getText());
        modelOffset1 = Integer.parseInt(item.getModelOffset1().getText());
        modelOffset2 = Integer.parseInt(item.getModelOffset2().getText());
        modelRotation1 = Integer.parseInt(item.getModelRotation1().getText());
        modelRotation2 = Integer.parseInt(item.getModelRotation2().getText());
        maleEquip1 = Integer.parseInt(item.getMaleEquip1().getText());
        femaleEquip1 = Integer.parseInt(item.getFemaleEquip1().getText());
        maleEquip2 = Integer.parseInt(item.getMaleEquip2().getText());
        femaleEquip2 = Integer.parseInt(item.getFemaleEquip2().getText());
        maleEquip3 = Integer.parseInt(item.getMaleEquip3().getText());
        femaleEquip3 = Integer.parseInt(item.getFemaleEquip3().getText());
        teamID = Integer.parseInt(item.getTeamID().getText());
        stackable = item.getStackable().isSelected();
        lendItemID = Integer.parseInt(item.getLendItemID().getText());
        switchLendItemID = Integer.parseInt(item.getSwitchLendItemID().getText());
        weaponType = Integer.parseInt(item.getWeaponType().getText());
        renderAnimation = Integer.parseInt(item.getRenderAnimation().getText());
        changedModelColors = item.getChangedModelColors().getText().split(",");
        changedTextureColors = item.getChangedTextureColors().getText().split(",");
        invOptions[0] = item.getInv0().getText();
        invOptions[1] = item.getInv1().getText();
        invOptions[2] = item.getInv2().getText();
        invOptions[3] = item.getInv3().getText();
        invOptions[4] = item.getInv4().getText();
        groundOptions[0] = item.getGround0().getText();
        groundOptions[1] = item.getGround1().getText();
        groundOptions[2] = item.getGround2().getText();
        groundOptions[3] = item.getGround3().getText();
        groundOptions[4] = item.getGround4().getText();
    }
    
    public static void utilizeSettings(Store cache) {
        boolean result;
        ItemDefinitions itemDef = ItemDefinitions.getItemDefinition(cache, copyItemID);
        itemDef.setName(itemName);
        itemDef.setValue(itemValue);
        itemDef.setNotedItemId(notedItemID);
        itemDef.setEquipType(equipType);
        int actualEquipSlot[] = {0, 1, 2, 3, 4, 5, 7, 9, 10, 12, 13, 14};
        itemDef.setEquipSlot(actualEquipSlot[equipSlot]);
        itemDef.setMembersOnly(membersOnly);
        itemDef.setInvModelId(invModel);
        itemDef.setInvModelZoom(invModelZoom);
        itemDef.setMaleEquipModelId2(maleEquip1);
        itemDef.setMaleEquipModelId3(maleEquip2);
        itemDef.setMaleEquipModelId1(maleEquip3);
        itemDef.setFemaleEquipModelId1(femaleEquip1);
        itemDef.setFemaleEquipModelId2(femaleEquip2);
        itemDef.setFemaleEquipModelId3(femaleEquip3);
        itemDef.teamId = teamID;
        if (stackable)
            itemDef.setStackable(1);
        else
            itemDef.setStackable(0);
        itemDef.setModelOffset1(modelOffset1);
        itemDef.setModelOffset2(modelOffset2);
        itemDef.setModelRotation1(modelRotation1);
        itemDef.setModelRotation2(modelRotation2);
        if (renderAnimation != -1)
            itemDef.clientScriptData.put(644, renderAnimation);
        if (weaponType != -1)
            itemDef.clientScriptData.put(686, weaponType);
        if (changedModelColors != null) {
            for (String t : changedModelColors) {
                String[] editedColor = t.split("=");
                itemDef.changeModelColor(Integer.valueOf(editedColor[0]),
                        Integer.valueOf(editedColor[1]));
            }
        }
        if (changedTextureColors != null) {
            for (String t : changedTextureColors) {
                String[] editedColor = t.split("=");
                itemDef.changeTextureColor(Integer.valueOf(editedColor[0]),
                        Integer.valueOf(editedColor[1]));
            }
        }
        itemDef.setGroundOptions(groundOptions);
        itemDef.setInventoryOptions(invOptions);
        
        result = cache.getIndexes()[19].putFile(itemID >>> 8, 0xff & itemID, itemDef.encode());
    }

    public static void proceedPacking() {
        Store cache = null;
        try {
            cache = new Store(currentCacheLocation);
        } catch (Exception e) {
            log ("Unable to find the Current Cache Location.");
        }
        try {
            utilizeSettings(cache);
        } catch (Exception e) {
            log("Unable to pack with current settings.");
            log("Please revise your settings and try again.");
        }
        log("Finished Packing Custom Item Process.");
    }
    
    private static void log(String s) {
        System.out.println(s);
    }
}
