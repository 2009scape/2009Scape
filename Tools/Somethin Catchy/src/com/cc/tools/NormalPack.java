/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.tools;

import com.alex.store.Index;
import com.alex.store.Store;
import com.alex.tools.clientCacheUpdater.RSXteas;
import com.alex.utils.Constants;
import com.alex.utils.Utils;
import com.cc.ui.NormalUI;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author travis
 */
public class NormalPack {

    public static boolean skeletons;
    public static boolean skins;
    private static boolean config;
    private static boolean interfaces;
    private static boolean soundEffects;
    private static boolean maps;
    private static boolean music;
    private static boolean models;
    private static boolean sprites;
    private static boolean textures;
    private static boolean huffmanEncoding;
    private static boolean interfaceScripts;
    private static boolean fonts;
    private static boolean objects;
    private static boolean clientscripts;
    private static boolean npcs;
    private static boolean items;
    private static boolean animations;
    private static boolean graphics;
    private static boolean clientvarpbits;
    private static boolean worldMap;
    private static boolean qcmessages;
    private static boolean qcmenus;
    private static boolean nativeLibs;
    private static boolean graphicShaders;
    private static boolean p11fontsimages;
    private static boolean gameTips;
    private static boolean theora;
    private static boolean vorbis;
    private static boolean mapEffects;
    private static boolean index29;
    private static boolean graphicConfigurations;
    private static boolean[] additionalOption = new boolean[3];
    private static String currentCacheLocation;
    private static String xteasLocation;
    private static String newCacheLocation;
    public static boolean[] indexSelected = new boolean[38];
    public static String[] indexName = {"Skeletons", "Skins", "Config",
        "Interfaces", "Sound Effects", "Landscapes", "Music", "Models",
        "Sprites", "Textures", "Huffman Encoding", "Music 2",
        "Interface Scripts", "Fonts", "Sound Effects 2", "Sound Effects 3",
        "Objects", "Clientscript", "NPCs", "Items", "Animations",
        "Graphics", "ClientVarpBits", "World Map", "Quick Chat Messages",
        "Quick Chat Menus", "Textures 2", "Map Effects", "Fonts 2",
        "Index 29", "Native Libraries", "Graphic Shaders",
        "P11 Fonts/Images", "Game Tips", "P11 Fonts 2/Images", "Theora",
        "Vorbis", "Graphic Configuration"};

    public static void setSettings(NormalUI normal) {
        skeletons = normal.getSkeletons().isSelected();
        skins = normal.getSkins().isSelected();
        config = normal.getConfig().isSelected();
        interfaces = normal.getInterfaces().isSelected();
        soundEffects = normal.getSoundEffects().isSelected();
        maps = normal.getMaps().isSelected();
        music = normal.getMusic().isSelected();
        models = normal.getModels().isSelected();
        sprites = normal.getSprites().isSelected();
        textures = normal.getTextures().isSelected();
        huffmanEncoding = normal.getHuffmanEncoding().isSelected();
        interfaceScripts = normal.getInterfaceScripts().isSelected();
        fonts = normal.getFonts().isSelected();
        objects = normal.getObjects().isSelected();
        clientscripts = normal.getClientscripts().isSelected();
        npcs = normal.getNpcs().isSelected();
        items = normal.getItems().isSelected();
        animations = normal.getAnimations().isSelected();
        graphics = normal.getGraphicss().isSelected();
        clientvarpbits = normal.getClientVarpBits().isSelected();
        worldMap = normal.getWorldMap().isSelected();
        qcmessages = normal.getQcMessages().isSelected();
        qcmenus = normal.getQcMenus().isSelected();
        nativeLibs = normal.getNativeLibs().isSelected();
        graphicShaders = normal.getGraphicShaders().isSelected();
        p11fontsimages = normal.getP11FontsImages().isSelected();
        gameTips = normal.getGameTips().isSelected();
        theora = normal.getTheora().isSelected();
        vorbis = normal.getVorbis().isSelected();
        mapEffects = normal.getMapEffects().isSelected();
        index29 = normal.getIndex29().isSelected();
        graphicConfigurations = normal.getGraphicConfigurations().isSelected();
        additionalOption[0] = normal.getAddOption0().isSelected();
        additionalOption[1] = normal.getAddOption1().isSelected();
        additionalOption[2] = normal.getAddOption2().isSelected();
        currentCacheLocation = normal.getCurrentCacheLocation();
        newCacheLocation = normal.getNewCacheLocation();
        xteasLocation = normal.getXteasLocation();
        convertIndexBooleans();
    }

    public static void utilizeSettings(Store cache, Store rscache) {
        boolean result;
        try {
            cache.resetIndex(7, false, false, Constants.GZIP_COMPRESSION);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NormalPack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NormalPack.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < cache.getIndexes().length; i++) {
            if (isIndexSelected(i)) {
                boolean packHere = true;
                if (i == 3 || i == 12) {
                    packHere = false;
                }
                if (i == 5 && isAdditionalOptionSelected(1)) {
                    packHere = false;
                }
                if (packHere) {
                    result = cache.getIndexes()[i].packIndex(rscache, true);
                    System.out.println("Packed " + getIndexName(i) + ": "
                            + result + " - Index " + i);
                } else {
                    System.out.println("Skipping " + getIndexName(i)
                            + " Packing here.");
                }
            }
        }

        if (isAdditionalOptionSelected(0)) {
            System.out.println("Packing old item definitions...");
            //Store cache667 = new Store("cache667/", false);
            int currentSize = 30000;// Utils.getItemDefinitionsSize(cache);
            int oldSize = Utils.getItemDefinitionsSize(cache);
            for (int i = currentSize; i < currentSize + oldSize; i++) {
                int newItemId = i;
                int oldItemId = i - currentSize;
                cache.getIndexes()[19].putFile(newItemId >>> 8,
                        0xff & newItemId, Constants.GZIP_COMPRESSION,
                        cache.getIndexes()[19].getFile(oldItemId >>> 8,
                        0xff & oldItemId), null, false, false, -1,
                        -1);
            }
            result = cache.getIndexes()[19].rewriteTable();
            System.out.println("Packed old item definitions: " + result);
        }
        if (isAdditionalOptionSelected(2)) {
            result = cache.getIndexes()[8].putFile(2173, 0,
                    Constants.GZIP_COMPRESSION,
                    rscache.getIndexes()[8].getFile(2173, 0), null, false,
                    false, -1, -1);
            System.out.println("Replaced Flag: " + result);
            result = cache.getIndexes()[8].putFile(2498, 0,
                    Constants.GZIP_COMPRESSION,
                    rscache.getIndexes()[8].getFile(2498, 0), null, false,
                    false, -1, -1);
            System.out.println("Replaced logo: " + result);
            for (int i = 4139; i <= 4146; i++) {
                result = cache.getIndexes()[8].putFile(i, 0,
                        Constants.GZIP_COMPRESSION,
                        rscache.getIndexes()[8].getFile(i, 0), null, false,
                        false, -1, -1);
            }
            System.out.println("Replaced Background: " + result);
            for (int i = 0; i < 4; i++) {
                int[] ids = new int[]{3769 + i, 3779 + i,
                    3783 + (i >= 2 ? (i - 2) : i + 2),
                    8494 + (i >= 2 ? (i - 2) : i + 2),
                    8498 + (i >= 2 ? (i - 2) : i + 2)};
                for (int id : ids) {
                    result = cache.getIndexes()[8].putFile(id, 0,
                            Constants.GZIP_COMPRESSION,
                            rscache.getIndexes()[8].getFile(id, 0), null,
                            false, false, -1, -1);
                    result = cache.getIndexes()[32].putFile(id, 0,
                            Constants.GZIP_COMPRESSION,
                            rscache.getIndexes()[32].getFile(id, 0), null,
                            false, false, -1, -1);
                    result = cache.getIndexes()[34].putFile(id, 0,
                            Constants.GZIP_COMPRESSION,
                            rscache.getIndexes()[34].getFile(id, 0), null,
                            false, false, -1, -1);
                }
            }
            System.out.println("Replaced Load Background: " + result);

            result = cache.getIndexes()[8].rewriteTable();
            result = cache.getIndexes()[32].rewriteTable();
            result = cache.getIndexes()[34].rewriteTable();
            System.out.println("Replaced all Backgrounds and Logos: "
                    + result);
        }
        if (isIndexSelected(3)) {
            System.out.println("Adding new interfaces...");
            for (int i = cache.getIndexes()[3].getLastArchiveId() + 1; i <= rscache
                    .getIndexes()[3].getLastArchiveId(); i++) {
                if (i == 548 || i == 746) {
                    continue;
                }
                if (rscache.getIndexes()[3].archiveExists(i)) {
                    cache.getIndexes()[3].putArchive(i, rscache, false,
                            false);
                }
            }
            result = cache.getIndexes()[3].rewriteTable();
            System.out.println("Packed new interfaces: " + result);
        }
        if (isIndexSelected(5) & isAdditionalOptionSelected(1)) {
            RSXteas.loadUnpackedXteas(xteasLocation);
            System.out.println("Updating Maps.");
            for (int regionId = 0; regionId < 30000; regionId++) {
                int regionX = (regionId >> 8) * 64;
                int regionY = (regionId & 0xff) * 64;
                String name = "m" + ((regionX >> 3) / 8) + "_"
                        + ((regionY >> 3) / 8);
                byte[] data = rscache.getIndexes()[5].getFile(rscache
                        .getIndexes()[5].getArchiveId(name));
                if (data != null) {
                    result = addMapFile(cache.getIndexes()[5], name, data);
                    System.out.println(name + ", " + result);
                }
                name = "um" + ((regionX >> 3) / 8) + "_"
                        + ((regionY >> 3) / 8);
                data = rscache.getIndexes()[5]
                        .getFile(rscache.getIndexes()[5].getArchiveId(name));
                if (data != null) {
                    result = addMapFile(cache.getIndexes()[5], name, data);
                    System.out.println(name + ", " + result);
                }
                int[] xteas = RSXteas.getXteas(regionId);
                name = "l" + ((regionX >> 3) / 8) + "_"
                        + ((regionY >> 3) / 8);
                data = rscache.getIndexes()[5].getFile(
                        rscache.getIndexes()[5].getArchiveId(name), 0,
                        xteas);
                if (data != null) {
                    result = addMapFile(cache.getIndexes()[5], name, data);
                    System.out.println(name + ", " + result);
                }
                name = "ul" + ((regionX >> 3) / 8) + "_"
                        + ((regionY >> 3) / 8);
                data = rscache.getIndexes()[5].getFile(
                        rscache.getIndexes()[5].getArchiveId(name), 0,
                        xteas);
                if (data != null) {
                    result = addMapFile(cache.getIndexes()[5], name, data);
                    System.out.println(name + ", " + result);
                }
                name = "n" + ((regionX >> 3) / 8) + "_"
                        + ((regionY >> 3) / 8);
                data = rscache.getIndexes()[5].getFile(
                        rscache.getIndexes()[5].getArchiveId(name), 0);
                if (data != null) {
                    result = addMapFile(cache.getIndexes()[5], name, data);
                    System.out.println(name + ", " + result);
                }
            }
            result = cache.getIndexes()[5].rewriteTable();
            System.out.println("Updated maps: " + result);
        }
    }

    public static void proceedPacking() {
        Store rscache = null;
        Store cache = null;
        try {
            rscache = new Store(newCacheLocation);
        } catch (Exception e) {
            log("Unable to find the Newer Cache Location.");
        }
        try {
            cache = new Store(currentCacheLocation);
        } catch (Exception e) {
            log ("Unable to find the Current Cache Location.");
        }
        try {
            utilizeSettings(cache, rscache);
        } catch (Exception e) {
            log("Unable to pack with current settings.");
            log("Please revise your settings and try again.");
        }
        log("Finished Packing Cache Process.");
    }

    private static void log(String s) {
        System.out.println(s);
    }

    public static boolean addMapFile(Index index, String name, byte[] data) {
        int archiveId = index.getArchiveId(name);
        if (archiveId == -1) {
            archiveId = index.getTable().getValidArchiveIds().length;
        }
        return index.putFile(archiveId, 0, Constants.GZIP_COMPRESSION, data,
                null, false, false, Utils.getNameHash(name), -1);
    }

    private static void convertIndexBooleans() {
        setIndexSelected(0, skeletons);
        setIndexSelected(1, skins);
        setIndexSelected(2, config);
        setIndexSelected(3, interfaces);
        setIndexSelected(4, soundEffects);
        setIndexSelected(5, maps);
        setIndexSelected(6, music);
        setIndexSelected(7, models);
        setIndexSelected(8, sprites);
        setIndexSelected(9, textures);
        setIndexSelected(10, huffmanEncoding);
        setIndexSelected(11, music);
        setIndexSelected(12, interfaceScripts);
        setIndexSelected(13, fonts);
        setIndexSelected(14, soundEffects);
        setIndexSelected(15, soundEffects);
        setIndexSelected(16, objects);
        setIndexSelected(17, clientscripts);
        setIndexSelected(18, npcs);
        setIndexSelected(19, items);
        setIndexSelected(20, animations);
        setIndexSelected(21, graphics);
        setIndexSelected(22, clientvarpbits);
        setIndexSelected(23, worldMap);
        setIndexSelected(24, qcmessages);
        setIndexSelected(25, qcmenus);
        setIndexSelected(26, textures);
        setIndexSelected(27, mapEffects);
        setIndexSelected(28, fonts);
        setIndexSelected(29, index29);
        setIndexSelected(30, nativeLibs);
        setIndexSelected(31, graphicShaders);
        setIndexSelected(32, p11fontsimages);
        setIndexSelected(33, gameTips);
        setIndexSelected(34, p11fontsimages);
        setIndexSelected(35, theora);
        setIndexSelected(36, vorbis);
        setIndexSelected(37, graphicConfigurations);
        setAdditionalOptionSelected(0, additionalOption[0]);
        setAdditionalOptionSelected(1, additionalOption[1]);
        setAdditionalOptionSelected(2, additionalOption[2]);
    }
    
    public static boolean isIndexSelected(int i) {
        return indexSelected[i];
    }
    
    public static void setIndexSelected(int i, boolean b) {
        indexSelected[i] = b;
    }

    public static boolean isAdditionalOptionSelected(int i) {
        return additionalOption[i];
    }
    
    public static void setAdditionalOptionSelected(int i, boolean b) {
        additionalOption[i] = b;
    }
    
    public static String getIndexName(int i) {
        return indexName[i];
    }
}
