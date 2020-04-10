package com.alex.loaders.npcs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.alex.io.InputStream;
import com.alex.io.OutputStream;
import com.alex.store.Store;
import com.alex.utils.Constants;

public final class NPCDefinitions implements Cloneable {

    private static final ConcurrentHashMap<Integer, NPCDefinitions> npcDefinitions = new ConcurrentHashMap<Integer, NPCDefinitions>();
    private boolean loaded;
    public int id;
    public HashMap<Integer, Object> config;
    public int unknownInt13;
    public int unknownInt6;
    public int unknownInt15;
    public byte respawnDirection;
    public int size = 1;
    public int[][] anIntArrayArray8478;
    public boolean clickable;
    public int anInt8483;
    public int transVarBit;
    public int[] transforms;
    public int anInt8443;
    public int renderTypeID;
    public boolean aBool8460 = false;
    public int anInt8488;
    public byte aByte8477;
    public boolean aBool8492;
    public int degreesToTurn;
    public byte aByte8476;
    public boolean aBool8459;
    public boolean aBook8472;
    public int[] recolorDst;
    public int level;
    public byte[] recolorDstPalette;
    public short aShort8473;
    public boolean isVisible;
    public int height;
    public String name;
    public int[] retextureDst;
    public byte walkMask;
    public int[] meshes;
    public int ambient;
    public int mapIcon;
    public int anInt8455;
    public int attackOpCursor;
    public int unknownInt14;
    public int anInt8457;
    public int anInt8466;
    public int headIcon;
    public int unknownInt19;
    public int[] retextureSrc;
    public int[][] anIntArrayArray882;
    public int anInt8484;
    public int[] anIntArray8493;
    public int transVar;
    public int unknownInt16;
    public boolean drawMinimapDot;
    public int[] interfaceModelId;
    public short aShort8474;
    public String[] op;
    public int[] recolorSrc;
    public int contrast;
    public int width;
    public int npcId;
    public int anInt8465;

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

    public static NPCDefinitions getNPCDefinition(Store cache, int npcId) {
        return getNPCDefinition(cache, npcId, true);
    }

    public static NPCDefinitions getNPCDefinition(Store cache, int npcId,
            boolean load) {
        return new NPCDefinitions(cache, npcId, load);
    }

    public NPCDefinitions(Store cache, int id, boolean load) {
        this.id = id;
        setDefaultVariableValues();
        setDefaultOptions();
        if (load) {
            loadNPCDefinition(cache);
        }
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setDefaultOptions() {
    	op = new String[] { null, null, null, null, null, "Examine" };
    }

    private void setDefaultVariableValues() {
        name = "null";
        level = -1;
        drawMinimapDot = true;
        renderTypeID = -1;
        respawnDirection = (byte) 7;
        size = 1;
        anInt8483 = -1;
        transVarBit = -1;
        unknownInt15 = -1;
        anInt8443 = -1;
        degreesToTurn = 32;
        unknownInt6 = -1;
        ambient = 0;
        walkMask = (byte) 0;
        anInt8488 = 255;
        anInt8455 = -1;
        aBool8492 = true;
        aShort8473 = (short) 0;
        anInt8466 = -1;
        aByte8477 = (byte) -96;
        anInt8457 = 0;
        attackOpCursor = -1;
        aBook8472 = true;
        mapIcon = -1;
        unknownInt14 = -1;
        unknownInt13 = -1;
        height = 128;
        headIcon = -1;
        aBool8459 = false;
        transVar = -1;
        aByte8476 = (byte) -16;
        isVisible = false;
        unknownInt16 = -1;
        anInt8484 = -1;
        clickable = true;
        unknownInt19 = -1;
        width = 128;
        aShort8474 = (short) 0;
        contrast = 0;
        anInt8465 = -1;
    }

    private void loadNPCDefinition(Store cache) {
        byte[] data = cache.getIndexes()[Constants.NPC_DEFINITIONS_INDEX]
                .getFile(getArchiveId(), getFileId());
        if (data == null) {
            //System.out.println("FAILED LOADING NPC " + id);
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
            decode(stream, opcode);
        }
    }

    public int getArchiveId() {
        return id >>> 134238215;
    }

    public int getFileId() {
        return 0x7f & id;
    }

    public void write(Store store) {
        store.getIndexes()[Constants.NPC_DEFINITIONS_INDEX].putFile(
                getArchiveId(), getFileId(), encode());
    }

    public void method694() {
        if (meshes == null) {
            meshes = new int[0];
        }
    }

    private void readValueLoop(InputStream stream) {
        while (true) {
            int opcode = stream.readUnsignedByte();
            if (opcode == 0) {
                break;
            }
            decode(stream, opcode);
        }
    }
    public boolean unknownBoolean7;
    public int[] cursorOps;
	private int movementType;
	private byte[] aByteArray8445;
	private byte[] aByteArray8446;

    private void decode(InputStream stream, int opcode) {
        if (opcode == 1) {
            int i = stream.readUnsignedByte();
            meshes = new int[i];
            for (int i_66_ = 0; i_66_ < i; i_66_++) {
                meshes[i_66_] = stream.readBigSmart();
                if ((meshes[i_66_] ^ 0xffffffff) == -65536) {
                    meshes[i_66_] = -1;
                }
            }
        } else if (opcode == 2) {
            name = stream.readString();
        } else if (opcode == 12) {
            size = stream.readUnsignedByte();
        } else if (opcode >= 30 && opcode < 36) {
            op[opcode - 30] = stream.readString();
            if (op[opcode - 30].equalsIgnoreCase("Hidden")) {
                op[opcode - 30] = null;
            }
        } else if (opcode == 40) {
            int i = stream.readUnsignedByte();
            recolorDst = new int[i];
            recolorSrc = new int[i];
            for (int i_65_ = 0; (i ^ 0xffffffff) < (i_65_ ^ 0xffffffff); i_65_++) {
                recolorSrc[i_65_] = stream.readUnsignedShort();
                recolorDst[i_65_] = stream.readUnsignedShort();
            }
        }
        if (opcode == 41) {
            int i = stream.readUnsignedByte();
            retextureSrc = new int[i];
            retextureDst = new int[i];
            for (int i_54_ = 0; (i_54_ ^ 0xffffffff) > (i ^ 0xffffffff); i_54_++) {
                retextureSrc[i_54_] = stream.readUnsignedShort();
                retextureDst[i_54_] = stream.readUnsignedShort();
            }
        } else if (opcode == 42) {
            int i = stream.readUnsignedByte();
            recolorDstPalette = new byte[i];
            for (int i_55_ = 0; i > i_55_; i_55_++) {
                recolorDstPalette[i_55_] = (byte) stream.readByte();
            }
        } else if (44 == opcode) {
		    int i_13_ = stream.readUnsignedShort();
		    int i_14_ = 0;
		    for (int i_15_ = i_13_; i_15_ > 0; i_15_ >>= 1) {
		    	i_14_++;
		    }
		    aByteArray8445 = new byte[i_14_];
		    byte i_16_ = 0;
		    for (int i_17_ = 0; i_17_ < i_14_; i_17_++) {
				if ((i_13_ & 1 << i_17_) > 0) {
				    aByteArray8445[i_17_] = i_16_;
				    i_16_++;
				} else {
				    aByteArray8445[i_17_] = (byte) -1;
				}
		    }
		} else if (opcode == 45) {
		    int i_18_ = stream.readUnsignedShort();
		    int i_19_ = 0;
		    for (int i_20_ = i_18_; i_20_ > 0; i_20_ >>= 1) {
		    	i_19_++;
		    }
		    aByteArray8446 = new byte[i_19_];
		    byte i_21_ = 0;
		    for (int i_22_ = 0; i_22_ < i_19_; i_22_++) {
				if ((i_18_ & 1 << i_22_) > 0) {
				    aByteArray8446[i_22_] = i_21_;
				    i_21_++;
				} else {
				    aByteArray8446[i_22_] = (byte) -1;
				}
		    }
        } else if (opcode == 60) {
            int i = stream.readUnsignedByte();
            interfaceModelId = new int[i];
            for (int i_64_ = 0; (i_64_ ^ 0xffffffff) > (i ^ 0xffffffff); i_64_++) {
                interfaceModelId[i_64_] = stream.readBigSmart();
            }
        } else if (opcode == 93) {
            drawMinimapDot = false;
        } else if (opcode == 95) {
            level = stream.readUnsignedShort();
        } else if (opcode == 97) {
            height = stream.readUnsignedShort();
        } else if (opcode == 98) {
            width = stream.readUnsignedShort();
        } else if (opcode == 99) {
            isVisible = true;
        } else if (opcode == 100) {
            ambient = stream.readByte();
        } else if (opcode == 101) {
            contrast = stream.readByte();
        } else if (opcode == 102) {
            headIcon = stream.readUnsignedShort();
        } else if (opcode == 103) {
            degreesToTurn = stream.readUnsignedShort();
        } else if (opcode == 106 || opcode == 118) {
            transVarBit = stream.readUnsignedShort();
            if (transVarBit == 65535) {
                transVarBit = -1;
            }
            transVar = stream.readUnsignedShort();
            if (transVar == 65535) {
                transVar = -1;
            }
            int defaultType = -1;
            if (opcode == 118) {
                defaultType = stream.readUnsignedShort();
                if ((defaultType) == 65535) {
                    defaultType = -1;
                }
            }
            int tCount = stream.readUnsignedByte();
            transforms = new int[2 + tCount];
            for (int index = 0; tCount >= index; index++) {
                transforms[index] = stream.readUnsignedShort();
                if (transforms[index] == 65535) {
                    transforms[index] = -1;
                }
            }
            transforms[tCount + 1] = defaultType;
        } else if (opcode == 107) {
            clickable = false;
        } else if (opcode == 109) {
            aBool8492 = false;
        } else if (opcode == 111) {
            aBook8472 = false;
        } else if (opcode == 113) {
            aShort8473 = (short) (stream.readUnsignedShort());
            aShort8474 = (short) (stream.readUnsignedShort());
        } else if (opcode == 114) {
            aByte8477 = (byte) (stream.readByte());
            aByte8476 = (byte) (stream.readByte());
        } else if (opcode == 119) {
            walkMask = (byte) (stream.readByte());
        } else if (opcode == 121) {
            anIntArrayArray8478 = (new int[meshes.length][]);
            int i = (stream.readUnsignedByte());
            for (int i_62_ = 0; ((i_62_ ^ 0xffffffff) > (i ^ 0xffffffff)); i_62_++) {
                int i_63_ = (stream.readUnsignedByte());
                int[] is = (anIntArrayArray8478[i_63_] = (new int[3]));
                is[0] = (stream.readByte());
                is[1] = (stream.readByte());
                is[2] = (stream.readByte());
            }
        } else if (opcode == 122) {
            unknownInt6 = (stream.readBigSmart());
        } else if (opcode == 123) {
            anInt8443 = (stream.readUnsignedShort());
        } else if (opcode == 125) {
            respawnDirection = (byte) (stream.readByte());
        } else if (opcode == 127) {
            renderTypeID = (stream.readUnsignedShort());
        } else if (opcode == 128) {
            movementType = stream.readUnsignedByte();
        } else if (opcode == 134) {
            anInt8466 = (stream.readUnsignedShort());
            if (anInt8466 == 65535) {
                anInt8466 = -1;
            }
            anInt8483 = (stream.readUnsignedShort());
            if (anInt8483 == 65535) {
                anInt8483 = -1;
            }
            anInt8484 = (stream.readUnsignedShort());
            if ((anInt8484 ^ 0xffffffff) == -65536) {
                anInt8484 = -1;
            }
            anInt8455 = (stream.readUnsignedShort());
            if ((anInt8455 ^ 0xffffffff) == -65536) {
                anInt8455 = -1;
            }
            anInt8457 = (stream.readUnsignedByte());
        } else if (opcode == 135) {
            unknownInt13 = stream.readUnsignedByte();
            unknownInt14 = stream.readUnsignedShort();
        } else if (opcode == 136) {
            unknownInt15 = stream.readUnsignedByte();
            unknownInt16 = stream.readUnsignedShort();
        } else if (opcode == 137) {
            attackOpCursor = stream.readUnsignedShort();
        } else if (opcode == 138) {
            anInt8465 = stream.readBigSmart();
        } else if (opcode == 139) {
            unknownInt19 = stream.readBigSmart();
        } else if (opcode == 140) {
            anInt8488 = stream.readUnsignedByte();
        } else if (opcode == 141) {
            aBool8460 = true;
        } else if (opcode == 142) {
            mapIcon = stream.readUnsignedShort();
        } else if (opcode == 143) {
            aBool8459 = true;
        } else if (opcode >= 150 && opcode < 155) {
            op[opcode - 150] = stream.readString();
            if (op[opcode - 150].equalsIgnoreCase("Hidden")) {
                op[opcode - 150] = null;
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
            anIntArray8493 = new int[i];
            for (int i_58_ = 0; i > i_58_; i_58_++) {
                anIntArray8493[i_58_] = stream.readUnsignedShort();
            }
        } else if (opcode == 162) {
            unknownBoolean7 = true;
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
            if (null == cursorOps) {
                cursorOps = new int[6];
                Arrays.fill(cursorOps, -1);
            }
            int i_44_ = (short) stream.readUnsignedShort();
            if (i_44_ == 65535) {
                i_44_ = -1;
            }
            cursorOps[opcode - 170] = i_44_;
        } else if (opcode == 179) {
            stream.readUnsignedByte();
            stream.readUnsignedByte();
            stream.readUnsignedByte();
            stream.readUnsignedByte();
            stream.readUnsignedByte();
            stream.readUnsignedByte();
        } else if (opcode == 249) {
            int i = stream.readUnsignedByte();
            if (config == null) {
                config = new HashMap<Integer, Object>(i);
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
                config.put(key, value);
            }
        }
    }

    public static final void clearNPCDefinitions() {
        npcDefinitions.clear();
    }

    public NPCDefinitions(int id) {
        this.id = id;
        anInt8483 = -1;
        transVarBit = -1;
        unknownInt15 = -1;
        anInt8443 = -1;
        degreesToTurn = 32;
        level = -1;
        unknownInt6 = -1;
        name = "null";
        ambient = 0;
        walkMask = (byte) 0;
        anInt8488 = 255;
        anInt8455 = -1;
        aBool8492 = true;
        aShort8473 = (short) 0;
        anInt8466 = -1;
        aByte8477 = (byte) -96;
        anInt8457 = 0;
        attackOpCursor = -1;
        renderTypeID = -1;
        respawnDirection = (byte) 7;
        aBook8472 = true;
        mapIcon = -1;
        unknownInt14 = -1;
        unknownInt13 = -1;
        height = 128;
        headIcon = -1;
        aBool8459 = false;
        transVar = -1;
        aByte8476 = (byte) -16;
        isVisible = false;
        drawMinimapDot = true;
        unknownInt16 = -1;
        anInt8484 = -1;
        clickable = true;
        unknownInt19 = -1;
        width = 128;
        aShort8474 = (short) 0;
        op = new String[5];
        contrast = 0;
        anInt8465 = -1;
    }

    public String toString() {
        return id + " - " + name;
    }

    public boolean hasMarkOption() {
        for (String option : op) {
            if (option != null && option.equalsIgnoreCase("mark")) {
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
        return renderTypeID;
    }

    public void setRenderEmote(int renderEmote) {
        this.renderTypeID = renderEmote;
    }

    public boolean isVisibleOnMap() {
        return drawMinimapDot;
    }

    public void setVisibleOnMap(boolean isVisibleOnMap) {
        this.drawMinimapDot = isVisibleOnMap;
    }

    public String[] getOptions() {
        return op;
    }

    public void setOptions(String[] options) {
        this.op = options;
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
        for (String option : op) {
            if (option != null && option.equalsIgnoreCase("attack")) {
                return true;
            }
        }
        return false;
    }

    public byte[] encode() {
        OutputStream stream = new OutputStream();

        stream.writeByte(1);
        stream.writeByte(meshes.length);
        for (int index = 0; index < meshes.length; index++) {
            stream.writeBigSmart(meshes[index]);
        }

        if (!name.equals("null")) {
            stream.writeByte(2);
            stream.writeString(name);
        }

        if (size != 1) {
            stream.writeByte(12);
            stream.writeByte(size);
        }

        for (int index = 0; index < op.length; index++) {
            if (op[index] == null || op[index] == "Hidden") {
                continue;
            }
            stream.writeByte(30 + index);
            stream.writeString(op[index]);
        }

        if (recolorDst != null && recolorSrc != null) {
            stream.writeByte(40);
            stream.writeByte(recolorDst.length);
            for (int index = 0; index < recolorDst.length; index++) {
                stream.writeShort(recolorDst[index]);
                stream.writeShort(recolorSrc[index]);
            }
        }

        if (retextureSrc != null && retextureDst != null) {
            stream.writeByte(41);
            stream.writeByte(retextureSrc.length);
            for (int index = 0; index < retextureSrc.length; index++) {
                stream.writeShort(retextureSrc[index]);
                stream.writeShort(retextureDst[index]);
            }
        }
        
        if (recolorDstPalette != null) {
            stream.writeByte(42);
            stream.writeByte(recolorDstPalette.length);
            for (int index = 0; index < recolorDstPalette.length; index++) {
                stream.writeByte(recolorDstPalette[index]);
            }
        }

        if (interfaceModelId != null) {
            stream.writeByte(60);
            stream.writeByte(interfaceModelId.length);
            for (int index = 0; index < interfaceModelId.length; index++) {
                stream.writeBigSmart(interfaceModelId[index]);
            }
        }

        if (drawMinimapDot) {
            stream.writeByte(93);
        }

        if (level != -1) {
            stream.writeByte(95);
            stream.writeShort(level);
        }

        if (height != 0) {
            stream.writeByte(97);
            stream.writeShort(height);
        }

        if (width != 0) {
            stream.writeByte(98);
            stream.writeShort(width);
        }
        
        if (isVisible) {
            stream.writeByte(99);
        }
        
        if (ambient != 0) {
            stream.writeByte(100);
            stream.writeByte(ambient);
        }
        
        if (contrast != 0) {
            stream.writeByte(101);
            stream.writeByte(contrast / 5);
        }

        if (headIcon != -1) {
            stream.writeByte(102);
            stream.writeShort(headIcon);
        }
        
        //TODO: FEW OPCODES HERE
        
        if (walkMask != -1) {
            stream.writeByte(119);
            stream.writeByte(walkMask);
        }

        if (respawnDirection != (byte) 7) {
            stream.writeByte(125);
            stream.writeByte(respawnDirection);
        }

        if (renderTypeID != -1) {
            stream.writeByte(127);
            stream.writeShort(renderTypeID);
        }
        
        //TODO: FEW OPCODES HERE
        
        if (config != null) {
            stream.writeByte(249);
            stream.writeByte(config.size());
            for (int key : config.keySet()) {
                Object value = config.get(key);
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
        return level;
    }

    public void setCombatLevel(int combatLevel) {
        this.level = combatLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void resetTextureColors() {
        retextureSrc = null;
        retextureDst = null;
    }

    public void changeTextureColor(int originalModelColor,
            int modifiedModelColor) {
        if (retextureSrc != null) {
            for (int i = 0; i < retextureSrc.length; i++) {
                if (retextureSrc[i] == originalModelColor) {
                    retextureDst[i] = modifiedModelColor;
                    return;
                }
            }
            int[] newOriginalModelColors = Arrays.copyOf(retextureSrc,
                    retextureSrc.length + 1);
            int[] newModifiedModelColors = Arrays.copyOf(retextureDst,
                    retextureDst.length + 1);
            newOriginalModelColors[newOriginalModelColors.length - 1] = originalModelColor;
            newModifiedModelColors[newModifiedModelColors.length - 1] = modifiedModelColor;
            retextureSrc = newOriginalModelColors;
            retextureDst = newModifiedModelColors;
        } else {
            retextureSrc = new int[]{originalModelColor};
            retextureDst = new int[]{modifiedModelColor};
        }
    }

    public void resetModelColors() {
        recolorDst = null;
        recolorSrc = null;
    }

    public void changeModelColor(int originalModelColor, int modifiedModelColor) {
        if (recolorDst != null) {
            for (int i = 0; i < recolorDst.length; i++) {
                if (recolorDst[i] == originalModelColor) {
                    recolorSrc[i] = modifiedModelColor;
                    return;
                }
            }
            int[] newOriginalModelColors = Arrays.copyOf(recolorDst,
                    recolorDst.length + 1);
            int[] newModifiedModelColors = Arrays.copyOf(recolorSrc,
                    recolorSrc.length + 1);
            newOriginalModelColors[newOriginalModelColors.length - 1] = originalModelColor;
            newModifiedModelColors[newModifiedModelColors.length - 1] = modifiedModelColor;
            recolorDst = newOriginalModelColors;
            recolorSrc = newModifiedModelColors;
        } else {
            recolorDst = new int[]{originalModelColor};
            recolorSrc = new int[]{modifiedModelColor};
        }
    }
}
