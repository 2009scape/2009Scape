package com.cc.tools;

import com.alex.loaders.npcs.NPCDefinitions;
import com.alex.loaders.objects.ObjectDefinitions;
import com.alex.store.Store;
import com.cc.ui.ObjectUI;

/**
 *
 * @author travis
 */
public class ObjectPack {

    public static String currentCacheLocation;
    private static int copyObject;
    private static int objectID;
    private static String[] options = new String[5];
    private static int sizeX;
    private static int sizeY;
    private static int modelIds;
    private static boolean projectileClipped;
    private static boolean clipType;
    private static String objectName;

    public static void setSettings(ObjectUI object) {
        currentCacheLocation = object.getCurrentCacheLocation();
        objectID = Integer.parseInt(object.getObjectID().getText());
        objectName = object.getObjectName().getText();
        copyObject = Integer.parseInt(object.getCopyObject().getText());
        sizeX = Integer.parseInt(object.getSizeX().getText());
        sizeY = Integer.parseInt(object.getSizeY().getText());
        options[0] = object.getOpt0().getText();
        options[1] = object.getOpt1().getText();
        options[2] = object.getOpt2().getText();
        options[3] = object.getOpt3().getText();
        options[4] = object.getOpt4().getText();
        for (int i = 0; i < 5; i++) {
            if (options[i].equalsIgnoreCase("") || options[i].equalsIgnoreCase("null"))
                options[i] = null;
        }
        projectileClipped = object.getProjectileClipped().isSelected();
        clipType = object.getClipType().isSelected();
        modelIds = Integer.parseInt(object.getModel1().getText());
        //modelIds[2][0] = Integer.parseInt(object.getModel2().getText());
    }

    public static void utilizeSettings(Store cache) {
        boolean result;
        ObjectDefinitions objectDef = ObjectDefinitions.getObjectDefinition(cache, copyObject);
        objectDef.name = objectName;
        objectDef.sizeX = sizeX;
        objectDef.sizeY = sizeY;
        objectDef.options = options;
        objectDef.projectileCliped = projectileClipped;
        if (clipType) {
            objectDef.clipType = 0;
        } else {
            objectDef.clipType = 2;
        }
        objectDef.modelIds[0][0] = modelIds;
        result = cache.getIndexes()[16].putFile(objectID >>> -1135990488, objectID & 0xff, objectDef.encode());
    }

    public static void proceedPacking() {
        Store cache = null;
        try {
            cache = new Store(currentCacheLocation);
        } catch (Exception e) {
            log("Unable to find the Current Cache Location.");
        }
        //try {
        utilizeSettings(cache);
        //} catch (Exception e) {
        //log("Unable to pack with current settings.");
        //log("Please revise your settings and try again.");
        //}
        log("Finished Packing Custom Object Process.");
    }

    private static void log(String s) {
        System.out.println(s);
    }
}
