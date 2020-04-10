package com.alex.loaders.npcs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.alex.io.InputStream;
import com.alex.io.OutputStream;
import com.alex.store.Store;
import com.alex.utils.Constants;

public final class NPCDefinitions {

    private static final ConcurrentHashMap<Integer, NPCDefinitions> npcDefinitions = new ConcurrentHashMap<Integer, NPCDefinitions>();
    private boolean loaded;
    private int id;
    public HashMap<Integer, Object> parameters;
    public int anInt833;
    public int anInt836;
    public int anInt837;
    public byte respawnDirection;
    public int size = 1;
    public int[][] anIntArrayArray840;
    public boolean clickable;
    public int anInt842;
    public int anInt844;
    public int[] anIntArray845;
    public int anInt846;
    public int renderEmote;
    public boolean aBoolean849 = false;
    public int anInt850;
    public byte aByte851;
    public boolean aBoolean852;
    public int degreesToTurn;
    public byte aByte854;
    public boolean aBoolean856;
    public boolean aBoolean857;
    public short[] originalModelColors;
    public int combatLevel;
    public byte[] aByteArray861;
    public short aShort862;
    public boolean isVisible;
    public int npcHeight;
    public String name;
    public short[] modifiedTextureColors;
    public byte walkMask;
    public int[] modelIds;
    public int lightModifier;
    public int anInt870;
    public int anInt871;
    public int anInt872;
    public int anInt874;
    public int anInt875;
    public int anInt876;
    public int headIcons;
    public int anInt879;
    public short[] originalTextureColors;
    public int[][] anIntArrayArray882;
    public int anInt884;
    public int[] anIntArray885;
    public int anInt888;
    public int anInt889;
    public boolean drawMinimapDot;
    public int[] chatHeadsArray;
    public short aShort894;
    public String[] options;
    public short[] modifiedModelColors;
    public int shadowModifier;
    public int npcWidth;
    public int npcId;
    public int anInt901;

    public static final NPCDefinitions getNPCDefinitions(int id, Store store) {
        NPCDefinitions def = npcDefinitions.get(id);
        if (def == null) {
            def = new NPCDefinitions(id);
            def.method694();
            byte[] data = store.getIndexes()[18].getFile(id >>> 134238215,
                    id & 0x7f);
            if (data == null) {
                // System.out.println("Failed loading NPC " + id + ".");
            } else {
                def.readValueLoop(new InputStream(data));
            }
            npcDefinitions.put(id, def);
        }
        return def;
    }

    public static NPCDefinitions getNPCDefinition(Store cache, int itemId) {
        return getNPCDefinition(cache, itemId, true);
    }

    public static NPCDefinitions getNPCDefinition(Store cache, int itemId,
            boolean load) {
        return new NPCDefinitions(cache, itemId, load);
    }

    public NPCDefinitions(Store cache, int id, boolean load) {
        this.id = id;
        setDefaultVariableValues();
        setDefaultOptions();
        if (load) {
            loadNPCDefinition(cache);
        }
    }

    private void setDefaultOptions() {
        options = new String[]{"Talk-to", null, null, null, null};
    }

    private void setDefaultVariableValues() {
        name = "null";
        combatLevel = -1;
        drawMinimapDot = true;
        renderEmote = -1;
        respawnDirection = (byte) 7;
        size = 1;
    }

    private void loadNPCDefinition(Store cache) {
        byte[] data = cache.getIndexes()[Constants.NPC_DEFINITIONS_INDEX]
                .getFile(getArchiveId(), getFileId());
        if (data == null) {
            System.out.println("FAILED LOADING NPC " + id);
            return;
        }
        try {
            readOpcodeValues(new InputStream(data));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        loaded = true;
    }

    private void readOpcodeValues(InputStream stream) {
        while (true) {
            int opcode = stream.readUnsignedByte();
            if (opcode == 0) {
                break;
            }
            readValues(stream, opcode);
        }
    }

    public int getArchiveId() {
        return id >>> 134238215;
    }

    public int getFileId() {
        return 0x7f & id;
    }

    public void method694() {
        if (modelIds == null) {
            modelIds = new int[0];
        }
    }

    private void readValueLoop(InputStream stream) {
        while (true) {
            int opcode = stream.readUnsignedByte();
            if (opcode == 0) {
                break;
            }
            readValues(stream, opcode);
        }
    }
    public boolean aBoolean3190;
    private int[] anIntArray2930;

    private void readValues(InputStream stream, int opcode) {
        if (opcode == 1) {
            int length = stream.readUnsignedByte();
            modelIds = new int[length];
            for (int index = 0; index < length; index++) {
                modelIds[index] = stream.readBigSmart();
                if ((modelIds[index] ^ 0xffffffff) == -65536) {
                    modelIds[index] = -1;
                }
            }
        } else if (opcode == 2) {
            name = stream.readString();
        } else if (opcode == 12) {
            size = stream.readUnsignedByte();
        } else if (opcode >= 30 && opcode < 36) {
            options[opcode - 30] = stream.readString();
            if (options[opcode - 30].equalsIgnoreCase("Hidden")) {
                options[opcode - 30] = null;
            }
        } else if (opcode == 40) {
            int i = stream.readUnsignedByte();
            originalModelColors = new short[i];
            modifiedModelColors = new short[i];
            for (int i_65_ = 0; (i ^ 0xffffffff) < (i_65_ ^ 0xffffffff); i_65_++) {
                modifiedModelColors[i_65_] = (short) stream.readUnsignedShort();
                originalModelColors[i_65_] = (short) stream.readUnsignedShort();
            }
        }
        if (opcode == 41) {
            int i = stream.readUnsignedByte();
            originalTextureColors = new short[i];
            modifiedTextureColors = new short[i];
            for (int i_54_ = 0; (i_54_ ^ 0xffffffff) > (i ^ 0xffffffff); i_54_++) {
                originalTextureColors[i_54_] = (short) stream.readUnsignedShort();
                modifiedTextureColors[i_54_] = (short) stream.readUnsignedShort();
            }
        } else if (opcode == 42) {
            int i = stream.readUnsignedByte();
            aByteArray861 = new byte[i];
            for (int i_55_ = 0; i > i_55_; i_55_++) {
                aByteArray861[i_55_] = (byte) stream.readByte();
            }
        } else if (opcode == 60) {
            int i = stream.readUnsignedByte();
            chatHeadsArray = new int[i];
            for (int i_64_ = 0; (i_64_ ^ 0xffffffff) > (i ^ 0xffffffff); i_64_++) {
                chatHeadsArray[i_64_] = stream.readBigSmart();
            }
        } else if (opcode == 93) {
            drawMinimapDot = false;
        } else if (opcode == 95) {
            combatLevel = stream.readUnsignedShort();
        } else if (opcode == 97) {
            npcHeight = stream.readUnsignedShort();
        } else if (opcode == 98) {
            npcWidth = stream.readUnsignedShort();
        } else if (opcode == 99) {
            isVisible = true;
        } else if (opcode == 100) {
            lightModifier = stream.readByte();
        } else if (opcode == 101) {
            shadowModifier = stream.readByte() * 5;
        } else if (opcode == 102) {
            headIcons = stream.readUnsignedShort();
        } else if (opcode == 103) {
            degreesToTurn = stream.readUnsignedShort();
        } else if (opcode == 106 || opcode == 118) {
            anInt844 = stream.readUnsignedShort();
            if (anInt844 == 65535) {
                anInt844 = -1;
            }
            anInt888 = stream.readUnsignedShort();
            if (anInt888 == 65535) {
                anInt888 = -1;
            }
            int i = -1;
            if (opcode == 118) {
                i = stream.readUnsignedShort();
                if ((i ^ 0xffffffff) == -65536) {
                    i = -1;
                }
            }
            int i_56_ = stream.readUnsignedByte();
            anIntArray845 = new int[2 + i_56_];
            for (int i_57_ = 0; i_56_ >= i_57_; i_57_++) {
                anIntArray845[i_57_] = stream.readUnsignedShort();
                if (anIntArray845[i_57_] == 65535) {
                    anIntArray845[i_57_] = -1;
                }
            }
            anIntArray845[i_56_ - -1] = i;
        } else if (opcode == 107) {
            clickable = false;
        } else if (opcode == 109) {
            aBoolean852 = false;
        } else if (opcode == 111) {
            aBoolean857 = false;
        } else if (opcode == 113) {
            aShort862 = (short) (stream.readUnsignedShort());
            aShort894 = (short) (stream.readUnsignedShort());
        } else if (opcode == 114) {
            aByte851 = (byte) (stream.readByte());
            aByte854 = (byte) (stream.readByte());
        } else if (opcode == 115) {
            stream.readUnsignedByte();
            stream.readUnsignedByte();
        } else if (opcode == 119) {
            walkMask = (byte) (stream.readByte());
        } else if (opcode == 121) {
            anIntArrayArray840 = (new int[modelIds.length][]);
            int i = (stream.readUnsignedByte());
            for (int i_62_ = 0; ((i_62_ ^ 0xffffffff) > (i ^ 0xffffffff)); i_62_++) {
                int i_63_ = (stream.readUnsignedByte());
                int[] is = (anIntArrayArray840[i_63_] = (new int[3]));
                is[0] = (stream.readByte());
                is[1] = (stream.readByte());
                is[2] = (stream.readByte());
            }
        } else if (opcode == 122) {
            anInt836 = (stream.readBigSmart());
        } else if (opcode == 123) {
            anInt846 = (stream.readUnsignedShort());
        } else if (opcode == 125) {
            respawnDirection = (byte) (stream.readByte());
        } else if (opcode == 127) {
            renderEmote = (stream.readUnsignedShort());
        } else if (opcode == 128) {
            stream.readUnsignedByte();
        } else if (opcode == 134) {
            anInt876 = (stream.readUnsignedShort());
            if (anInt876 == 65535) {
                anInt876 = -1;
            }
            anInt842 = (stream.readUnsignedShort());
            if (anInt842 == 65535) {
                anInt842 = -1;
            }
            anInt884 = (stream.readUnsignedShort());
            if ((anInt884 ^ 0xffffffff) == -65536) {
                anInt884 = -1;
            }
            anInt871 = (stream.readUnsignedShort());
            if ((anInt871 ^ 0xffffffff) == -65536) {
                anInt871 = -1;
            }
            anInt875 = (stream.readUnsignedByte());
        } else if (opcode == 135) {
            anInt833 = stream.readUnsignedByte();
            anInt874 = stream.readUnsignedShort();
        } else if (opcode == 136) {
            anInt837 = stream.readUnsignedByte();
            anInt889 = stream.readUnsignedShort();
        } else if (opcode == 137) {
            anInt872 = stream.readUnsignedShort();
        } else if (opcode == 138) {
            anInt901 = stream.readBigSmart();
        } else if (opcode == 139) {
            anInt879 = stream.readBigSmart();
        } else if (opcode == 140) {
            anInt850 = stream.readUnsignedByte();
        } else if (opcode == 141) {
            aBoolean849 = true;
        } else if (opcode == 142) {
            anInt870 = stream.readUnsignedShort();
        } else if (opcode == 143) {
            aBoolean856 = true;
        } else if (opcode >= 150 && opcode < 156) {
            options[opcode - 150] = stream.readString();
            if (options[opcode - 150].equalsIgnoreCase("Hidden")) {
                options[opcode - 150] = null;
            }
        } else if (opcode == 155) {
            int aByte821 = stream.readByte();
            int aByte824 = stream.readByte();
            int aByte843 = stream.readByte();
            int aByte855 = stream.readByte();
        } else if (opcode == 158) {
            byte aByte833 = (byte) 1;
        } else if (opcode == 159) {
            byte aByte833 = (byte) 0;
        } else if (opcode == 160) {
            int i = stream.readUnsignedByte();
            anIntArray885 = new int[i];
            for (int i_58_ = 0; i > i_58_; i_58_++) {
                anIntArray885[i_58_] = stream.readUnsignedShort();
            }
        } else if (opcode == 162) {
            aBoolean3190 = true;
        } else if (opcode == 163) {
            int anInt864 = stream.readUnsignedByte();
        } else if (opcode == 164) {
            int anInt848 = stream.readUnsignedShort();
            int anInt837 = stream.readUnsignedShort();
        } else if (opcode == 165) {
            int anInt847 = stream.readUnsignedByte();
        } else if (opcode == 168) {
            int anInt828 = stream.readUnsignedByte();
        } else if (opcode >= 170 && opcode < 176) {
            if (null == anIntArray2930) {
                anIntArray2930 = new int[6];
                Arrays.fill(anIntArray2930, -1);
            }
            int i_44_ = (short) stream.readUnsignedShort();
            if (i_44_ == 65535) {
                i_44_ = -1;
            }
            anIntArray2930[opcode - 170] = i_44_;
        } else if (opcode == 249) {
            int i = stream.readUnsignedByte();
            if (parameters == null) {
                parameters = new HashMap<Integer, Object>(i);
            }
            for (int i_60_ = 0; i > i_60_; i_60_++) {
                boolean stringInstance = stream.readUnsignedByte() == 1;
                int key = stream.read24BitInt();
                Object value;
                if (stringInstance) {
                    value = stream.readString();
                } else {
                    value = stream.readInt();
                }
                parameters.put(key, value);
            }
        }
    }

    public static final void clearNPCDefinitions() {
        npcDefinitions.clear();
    }

    public NPCDefinitions(int id) {
        this.id = id;
        anInt842 = -1;
        anInt844 = -1;
        anInt837 = -1;
        anInt846 = -1;
        degreesToTurn = 32;
        combatLevel = -1;
        anInt836 = -1;
        name = "null";
        lightModifier = 0;
        walkMask = (byte) 0;
        anInt850 = 255;
        anInt871 = -1;
        aBoolean852 = true;
        aShort862 = (short) 0;
        anInt876 = -1;
        aByte851 = (byte) -96;
        anInt875 = 0;
        anInt872 = -1;
        renderEmote = -1;
        respawnDirection = (byte) 7;
        aBoolean857 = true;
        anInt870 = -1;
        anInt874 = -1;
        anInt833 = -1;
        npcHeight = 128;
        headIcons = -1;
        aBoolean856 = false;
        anInt888 = -1;
        aByte854 = (byte) -16;
        isVisible = false;
        drawMinimapDot = true;
        anInt889 = -1;
        anInt884 = -1;
        clickable = true;
        anInt879 = -1;
        npcWidth = 128;
        aShort894 = (short) 0;
        options = new String[5];
        shadowModifier = 0;
        anInt901 = -1;
    }

    public boolean hasMarkOption() {
        for (String option : options) {
            if (option != null && option.equalsIgnoreCase("mark")) {
                return true;
            }
        }
        return false;
    }

    public boolean hasOption(String op) {
        for (String option : options) {
            if (option != null && option.equalsIgnoreCase(op)) {
                return true;
            }
        }
        return false;
    }

    public byte getRespawnDirection() {
        return respawnDirection;
    }

    public void setRespawnDirection(byte respawnDirection) {
        this.respawnDirection = respawnDirection;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getRenderEmote() {
        return renderEmote;
    }

    public void setRenderEmote(int renderEmote) {
        this.renderEmote = renderEmote;
    }

    public boolean isVisibleOnMap() {
        return drawMinimapDot;
    }

    public void setVisibleOnMap(boolean isVisibleOnMap) {
        this.drawMinimapDot = isVisibleOnMap;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getNpcId() {
        return npcId;
    }

    public void setNpcId(int npcId) {
        this.npcId = npcId;
    }

    public boolean hasAttackOption() {
        if (id == 14899) {
            return true;
        }
        for (String option : options) {
            if (option != null && option.equalsIgnoreCase("attack")) {
                return true;
            }
        }
        return false;
    }

    public byte[] encode() {
        OutputStream stream = new OutputStream();

        stream.writeByte(1);
        stream.writeByte(modelIds.length);
        for (int index = 0; index < modelIds.length; index++) {
            stream.writeBigSmart(modelIds[index]);
        }

        if (!name.equals("null")) {
            stream.writeByte(2);
            stream.writeString(name);
        }

        if (size != 1) {
            stream.writeByte(12);
            stream.writeByte(size);
        }

        for (int index = 0; index < options.length; index++) {
            if (options[index] == null || options[index] == "Hidden") {
                continue;
            }
            stream.writeByte(30 + index);
            stream.writeString(options[index]);
        }

        if (originalModelColors != null && modifiedModelColors != null) {
            stream.writeByte(40);
            stream.writeByte(originalModelColors.length);
            for (int index = 0; index < originalModelColors.length; index++) {
                stream.writeShort(originalModelColors[index]);
                stream.writeShort(modifiedModelColors[index]);
            }
        }

        if (originalTextureColors != null && modifiedTextureColors != null) {
            stream.writeByte(41);
            stream.writeByte(originalTextureColors.length);
            for (int index = 0; index < originalTextureColors.length; index++) {
                stream.writeShort(originalTextureColors[index]);
                stream.writeShort(modifiedTextureColors[index]);
            }
        }

        if (chatHeadsArray != null) {
            stream.writeByte(60);
            stream.writeByte(chatHeadsArray.length);
            for (int index = 0; index < chatHeadsArray.length; index++) {
                stream.writeBigSmart(chatHeadsArray[index]);
            }
        }

        if (!drawMinimapDot) {
            stream.writeByte(93);
        }

        if (combatLevel != 0) {
            stream.writeByte(95);
            stream.writeShort(combatLevel);
        }

        if (npcHeight != 0) {
            stream.writeByte(97);
            stream.writeShort(npcHeight);
        }

        if (npcWidth != 0) {
            stream.writeByte(98);
            stream.writeShort(npcWidth);
        }

        if (headIcons != 0) {
            stream.writeByte(102);
            stream.writeShort(headIcons);
        }

        if (respawnDirection != (byte) 7) {
            stream.writeByte(125);
            stream.writeByte(respawnDirection);
        }

        if (renderEmote != -1) {
            stream.writeByte(127);
            stream.writeShort(renderEmote);
        }

        if (parameters != null) {
            stream.writeByte(249);
            stream.writeByte(parameters.size());
            for (int key : parameters.keySet()) {
                Object value = parameters.get(key);
                stream.writeByte(value instanceof String ? 1 : 0);
                stream.write24BitInt(key);
                if (value instanceof String) {
                    stream.writeString((String) value);
                } else {
                    stream.writeInt((Integer) value);
                }
            }
        }
        // end
        stream.writeByte(0);

        byte[] data = new byte[stream.getOffset()];
        stream.setOffset(0);
        stream.getBytes(data, 0, data.length);
        return data;
    }

    public int getCombatLevel() {
        return combatLevel;
    }

    public void setCombatLevel(int combatLevel) {
        this.combatLevel = combatLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
