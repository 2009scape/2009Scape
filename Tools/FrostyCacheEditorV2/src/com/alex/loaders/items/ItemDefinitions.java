package com.alex.loaders.items;

import java.util.Arrays;
import java.util.HashMap;

import com.alex.io.InputStream;
import com.alex.io.OutputStream;
import com.alex.store.Store;
import com.alex.utils.Constants;
import com.editor.Main;

@SuppressWarnings("unused")
public class ItemDefinitions implements Cloneable {

    public int id;
    private boolean loaded;
    public int interfaceModelId;
    private String name;
    // model size information
    private int zoom2D;
    private int xAngle2D;
    private int yAngle2D;
    private int xOffset2D;
    private int yOffset2D;
    // extra information
    private int stackable;
    private int cost;
    public boolean members;
    // wearing model information
    public int maleEquip1;
    public int femaleEquip1;
    public int maleEquip2;
    public int femaleEquip2;
    public int colourEquip1;
    public int colourEquip2;
    // options
    private String[] groundOptions;
    public String[] inventoryOptions;
    // model information
    public int[] recolorSrc;
    public int[] recolorDst;
    public int[] retextureSrc;
    public int[] retextureDst;
    public byte[] recolorDstPalette;
    public int[] unknownArray2;
    // extra information, not used for newer items
    private boolean unnoted;
    public int manHead;
    public int womanHead;
    public int manHead2;
    public int womanHead2;
    public int zAngle2D;
    public int dummyItem;
    public int certLink;
    public int certTemplate;
    private int[] stackIds;
    private int[] stackAmounts;
    public int resizeX;
    public int resizeY;
    public int resizeZ;
    public int ambient;
    public int contrast;
    public int team;
    public int lentLink;
    public int lentTemplate;
    public int manWearXOffset;
    public int manWearYOffset;
    public int manWearZOffset;
    public int womanWearXOffset;
    public int womanWearYOffset;
    public int womanWearZOffset;
    public int groundCursorOp;
    public int groundCursor;
    public int cursor2op;
    public int cursor2;
    public int cursor2iop;
    public int icursor2;
    private int equipSlotId;
    public HashMap<Integer, Object> config;
    private int equipId;
    public int[] unknownArray4;
    public int[] unknownArray5;
    public byte[] unknownArray6;
    public byte[] unknownArray3;
    private boolean aBool8089;
	private int multiStackSize;
	private int anInt7901;
	private int nameColor;
	private boolean hasNameColor;
	private int anInt7900;
	private int cursor1iop;
	private int icursor1;
	private int anInt7938;
	private int bindLink;
	private int bindTemplate;
	private int shardLink;
	private int shardTemplate;
	private int shardCombineAmount;
	private String shardName;
	private boolean bound;

    public static ItemDefinitions getItemDefinition(Store cache, int itemId) {
        return getItemDefinition(cache, itemId, true);
    }

    public static ItemDefinitions getItemDefinition(Store cache, int itemId,
            boolean load) {
        return new ItemDefinitions(cache, itemId, load);
    }

    public ItemDefinitions(Store cache, int id) {
        this(cache, id, true);
    }

    public ItemDefinitions(Store cache, int id, boolean load) {
        this.id = id;
        setDefaultsVariableValues();
        setDefaultOptions();
        if (load) {
            loadItemDefinition(cache);
        }
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void write(Store store) {
        store.getIndexes()[Constants.ITEM_DEFINITIONS_INDEX].putFile(
                getArchiveId(), getFileId(), encode());
    }

    private void loadItemDefinition(Store cache) {
        byte[] data = cache.getIndexes()[Constants.ITEM_DEFINITIONS_INDEX]
                .getFile(getArchiveId(), getFileId());
        if (data == null) {
            //System.out.println("FAILED LOADING ITEM " + id);
            return;
        }
        try {
            readOpcodeValues(new InputStream(data));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        if (certTemplate != -1) {
            toNote(cache);
        }
        if (lentTemplate != -1) {
            toLend(cache);
        }
        if (bindTemplate != -1) {
			toBind(cache);
		}
		if(shardTemplate != -1) {
			toShard(cache);
		}
        loaded = true;
    }
    
    public void toBind(Store store) {
    	ItemDefinitions realItem = getItemDefinition(store, bindLink);
		recolorSrc = realItem.recolorSrc;
		colourEquip1 = realItem.colourEquip1;
		colourEquip2 = realItem.colourEquip2;
		team = realItem.team;
		cost = 0;
		members = realItem.members;
		name = realItem.name;
		inventoryOptions = new String[5];
		groundOptions = realItem.groundOptions;
		if (realItem.inventoryOptions != null) {
			for (int optionIndex = 0; optionIndex < 4; optionIndex++) {
				inventoryOptions[optionIndex] = realItem.inventoryOptions[optionIndex];
			}
		}
		inventoryOptions[4] = "Discard";
		maleEquip1 = realItem.maleEquip1;
		maleEquip2 = realItem.maleEquip2;
		femaleEquip1 = realItem.femaleEquip1;
		femaleEquip2 = realItem.femaleEquip2;
		config = realItem.config;
		equipId = realItem.equipId;
		equipSlotId = realItem.equipSlotId;
		bound = true;
	}
    
    public void toShard(Store store) {
    	ItemDefinitions realItem = getItemDefinition(store, shardLink);
		interfaceModelId = realItem.interfaceModelId;

		name = realItem.shardName;
		cost = (int) Math.floor(realItem.cost / realItem.shardCombineAmount);
		stackable = 1;
		unnoted = realItem.unnoted;
		anInt7900 = realItem.anInt7900;
		unknownArray4 = realItem.unknownArray4;
		unknownArray5 = realItem.unknownArray5;
		inventoryOptions = new String[5];
		inventoryOptions[0] = "Combine";
		inventoryOptions[4] = "Drop";
	}

    private void toNote(Store store) {
        // ItemDefinitions noteItem; //certTemplateId
        ItemDefinitions realItem = getItemDefinition(store, certLink);
        members = realItem.members;
        cost = realItem.cost;
        name = realItem.name;
        stackable = 1;
    }

    private void toLend(Store store) {
        // ItemDefinitions lendItem; //lendTemplateId
        ItemDefinitions realItem = getItemDefinition(store, lentLink);
        recolorSrc = realItem.recolorSrc;
        recolorDst = realItem.recolorDst;
        team = realItem.team;
        cost = 0;
        members = realItem.members;
        name = realItem.name;
        inventoryOptions = new String[5];
        groundOptions = realItem.groundOptions;
        if (realItem.inventoryOptions != null) {
            for (int optionIndex = 0; optionIndex < 4; optionIndex++) {
                inventoryOptions[optionIndex] = realItem.inventoryOptions[optionIndex];
            }
        }
        inventoryOptions[4] = "Discard";
        maleEquip1 = realItem.maleEquip1;
        maleEquip2 = realItem.maleEquip2;
        femaleEquip1 = realItem.femaleEquip1;
        femaleEquip2 = realItem.femaleEquip2;
        colourEquip1 = realItem.colourEquip1;
        colourEquip2 = realItem.colourEquip2;
        equipSlotId = realItem.equipSlotId;
    }

    public int getArchiveId() {
        return id >>> 8;
    }

    public int getFileId() {
        return 0xff & id;
    }

    public boolean hasSpecialBar() {
        if (config == null) {
            return false;
        }
        Object specialBar = config.get(686);
        if (specialBar != null && specialBar instanceof Integer) {
            return (Integer) specialBar == 1;
        }
        return false;
    }

    public int getRenderAnimId() {
        if (config == null) {
            return 1426;
        }
        Object animId = config.get(644);
        if (animId != null && animId instanceof Integer) {
            return (Integer) animId;
        }
        return 1426;
    }

    public void setRenderAnimId(int animId) {
        if (config == null) {
            config = new HashMap<Integer, Object>();
        }
        config.put(644, animId);
    }

    public int getQuestId() {
        if (config == null) {
            return -1;
        }
        Object questId = config.get(861);
        if (questId != null && questId instanceof Integer) {
            return (Integer) questId;
        }
        return -1;
    }

    public HashMap<Integer, Integer> getWearingSkillRequiriments() {
        if (config == null) {
            return null;
        }
        HashMap<Integer, Integer> skills = new HashMap<Integer, Integer>();
        int nextLevel = -1;
        int nextSkill = -1;
        for (int key : config.keySet()) {
            Object value = config.get(key);
            if (value instanceof String) {
                continue;
            }
            if (key == 23) {
                skills.put(4, (Integer) value);
                skills.put(11, 61);
            } else if (key >= 749 && key < 797) {
                if (key % 2 == 0) {
                    nextLevel = (Integer) value;
                } else {
                    nextSkill = (Integer) value;
                }
                if (nextLevel != -1 && nextSkill != -1) {
                    skills.put(nextSkill, nextLevel);
                    nextLevel = -1;
                    nextSkill = -1;
                }
            }

        }
        return skills;
    }

    // test :P
    public void printClientScriptData() {
        for (int key : config.keySet()) {
            Object value = config.get(key);
            System.out.println("KEY: " + key + ", VALUE: " + value);
        }
        HashMap<Integer, Integer> requiriments = getWearingSkillRequiriments();
        if (requiriments == null) {
            System.out.println("null.");
            return;
        }
        System.out.println(requiriments.keySet().size());
        for (int key : requiriments.keySet()) {
            Object value = requiriments.get(key);
            System.out.println("SKILL: " + key + ", LEVEL: " + value);
        }
    }

    private void setDefaultOptions() {
        groundOptions = new String[]{null, null, "take", null, null};
        inventoryOptions = new String[]{null, null, null, null, "drop"};
        config = new HashMap<Integer, Object>();
    }

    private void setDefaultsVariableValues() {
        name = "null";
        xOffset2D = 0;
		yOffset2D = 0;
		xAngle2D = 0;
		yAngle2D = 0;
		zAngle2D = 0;
		dummyItem = 0;
		multiStackSize = -1;
		shardCombineAmount = 0;
		anInt7938 = 0;
		anInt7900 = -1;
		groundCursorOp = -1;
		groundCursor = -1;
		cursor2op = -1;
		cursor2 = -1;
		cursor1iop = -1;
		icursor1 = -1;
		cursor2iop = -1;
		icursor2 = -1;
		anInt7901 = -1;
		equipSlotId = -1;
		name = null;
		maleEquip1 = -1;
		femaleEquip1 = -1;
		maleEquip2 = -1;
		femaleEquip2 = -1;
		zoom2D = 2000;
		lentLink = -1;
		lentTemplate = -1;
		certLink = -1;
		certTemplate = -1;
		bindLink = -1;
		bindTemplate = -1;
		resizeZ = 128;
		resizeY = 128;
		resizeX = 128;
		ambient = 0;
		contrast = 0;
		team = 0;
		shardTemplate = -1;
		shardLink = -1;
		cost = 1;
		equipId = -1;
		colourEquip1 = -1;
		colourEquip2 = -1;
		manWearXOffset = 0;
		manWearYOffset = 0;
		manWearZOffset = 0;
		womanWearXOffset = 0;
		womanWearYOffset = 0;
		womanWearZOffset = 0;
		unnoted = false;
		hasNameColor = false;
		aBool8089 = false;
    }

    public byte[] encode() {
        OutputStream stream = new OutputStream();

        stream.writeByte(1);
        stream.writeBigSmart(interfaceModelId);

        if (!name.equals("null") && certTemplate == -1) {
            stream.writeByte(2);
            stream.writeString(name);
        }

        if (zoom2D != 2000) {
            stream.writeByte(4);
            stream.writeShort(zoom2D);
        }

        if (xAngle2D != 0) {
            stream.writeByte(5);
            stream.writeShort(xAngle2D);
        }

        if (yAngle2D != 0) {
            stream.writeByte(6);
            stream.writeShort(yAngle2D);
        }

        if (xOffset2D != 0) {
            stream.writeByte(7);
            int value = xOffset2D >>= 0;
            if (value < 0) {
                value += 65536;
            }
            stream.writeShort(value);
        }

        if (yOffset2D != 0) {
            stream.writeByte(8);
            int value = yOffset2D >>= 0;
            if (value < 0) {
                value += 65536;
            }
            stream.writeShort(value);
        }

        if (stackable == 1 && certTemplate == -1) {
            stream.writeByte(11);
        }

        if (cost != 1 && lentTemplate == -1) {
            stream.writeByte(12);
            stream.writeInt(cost);
        }

        if (equipSlotId != -1) {
            stream.writeByte(13);
            stream.writeByte(equipSlotId);
        }

        if (equipId != -1) {
            stream.writeByte(14);
            stream.writeByte(equipId);
        }

        if (members && certTemplate == -1) {
            stream.writeByte(16);
        }

        if (maleEquip1 != -1) {
            stream.writeByte(23);
            stream.writeBigSmart(maleEquip1);
        }

        if (maleEquip2 != -1) {
            stream.writeByte(24);
            stream.writeBigSmart(maleEquip2);
        }

        if (femaleEquip1 != -1) {
            stream.writeByte(25);
            stream.writeBigSmart(femaleEquip1);
        }

        if (femaleEquip2 != -1) {
            stream.writeByte(26);
            stream.writeBigSmart(femaleEquip2);
        }

        for (int index = 0; index < groundOptions.length; index++) {
            if (groundOptions[index] == null
                    || (index == 2 && groundOptions[index].equals("take"))) {
                continue;
            }
            stream.writeByte(30 + index);
            stream.writeString(groundOptions[index]);
        }

        for (int index = 0; index < inventoryOptions.length; index++) {
            if (inventoryOptions[index] == null
                    || (index == 4 && inventoryOptions[index].equals("drop"))) {
                continue;
            }
            stream.writeByte(35 + index);
            stream.writeString(inventoryOptions[index]);
        }

        if (recolorSrc != null && recolorDst != null) {
            stream.writeByte(40);
            stream.writeByte(recolorSrc.length);
            for (int index = 0; index < recolorSrc.length; index++) {
                stream.writeShort(recolorSrc[index]);
                stream.writeShort(recolorDst[index]);
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
        
        if (hasNameColor) {
        	stream.writeByte(43);
        	stream.writeInt(nameColor);
        }

        if (unnoted) {
            stream.writeByte(65);
        }

        if (colourEquip1 != -1) {
            stream.writeByte(78);
            stream.writeBigSmart(colourEquip1);
        }

        if (colourEquip2 != -1) {
            stream.writeByte(79);
            stream.writeBigSmart(colourEquip2);
        }

        if (manHead != 0) {
            stream.writeByte(90);
            stream.writeBigSmart(manHead);
        }
        if (womanHead != 0) {
            stream.writeByte(91);
            stream.writeBigSmart(womanHead);
        }
        if (manHead2 != 0) {
            stream.writeByte(92);
            stream.writeBigSmart(manHead2);
        }
        if (womanHead2 != 0) {
            stream.writeByte(93);
            stream.writeBigSmart(womanHead2);
        }
        if (zAngle2D != 0) {
            stream.writeByte(95);
            stream.writeBigSmart(zAngle2D);
        }
        if (dummyItem != 0) {
            stream.writeByte(96);
            stream.writeBigSmart(dummyItem);
        }

        if (certLink != -1) {
            stream.writeByte(97);
            stream.writeShort(certLink);
        }

        if (certTemplate != -1) {
            stream.writeByte(98);
            stream.writeShort(certTemplate);
        }

        if (stackIds != null && stackAmounts != null) {
            for (int index = 0; index < stackIds.length; index++) {
                if (stackIds[index] == 0 && stackAmounts[index] == 0) {
                    continue;
                }
                stream.writeByte(100 + index);
                stream.writeShort(stackIds[index]);
                stream.writeShort(stackAmounts[index]);
            }
        }
        
        if (resizeX != 128) {
            stream.writeByte(110);
            stream.writeShort(resizeX);
        }
        if (resizeY != 128) {
            stream.writeByte(111);
            stream.writeShort(resizeY);
        }
        if (resizeZ != 128) {
            stream.writeByte(112);
            stream.writeShort(resizeZ);
        }
        if (ambient != 0) {
            stream.writeByte(113);
            stream.writeByte(ambient);
        }
        if (contrast != 0) {
            stream.writeByte(114);
            stream.writeByte(contrast);
        }

        if (team != 0) {
            stream.writeByte(115);
            stream.writeByte(team);
        }

        if (lentLink != -1) {
            stream.writeByte(121);
            stream.writeShort(lentLink);
        }

        if (lentTemplate != -1) {
            stream.writeByte(122);
            stream.writeShort(lentTemplate);
        }
        if (manWearXOffset != 0 && manWearYOffset != 0 && manWearZOffset != 0) {
            stream.writeByte(125);
            stream.writeByte(manWearXOffset);
            stream.writeByte(manWearYOffset);
            stream.writeByte(manWearZOffset);
        }
        if (womanWearXOffset != 0 && womanWearYOffset != 0 && womanWearZOffset != 0) {
            stream.writeByte(126);
            stream.writeByte(womanWearXOffset);
            stream.writeByte(womanWearYOffset);
            stream.writeByte(womanWearZOffset);
        }
        if (groundCursorOp != 0 && groundCursor != 0) {
            stream.writeByte(127);
            stream.writeByte(groundCursorOp);
            stream.writeShort(groundCursor);
        }
        if (cursor2op != 0 && cursor2 != 0) {
            stream.writeByte(128);
            stream.writeByte(cursor2op);
            stream.writeShort(cursor2);
        }
        if (cursor2op != 0 && cursor2 != 0) {
            stream.writeByte(129);
            stream.writeByte(cursor2op);
            stream.writeShort(cursor2);
        }
        if (cursor2iop != 0 && icursor2 != 0) {
            stream.writeByte(130);
            stream.writeByte(cursor2iop);
            stream.writeShort(icursor2);
        }

        if (unknownArray2 != null) {
            stream.writeByte(132);
            stream.writeByte(unknownArray2.length);
            for (int index = 0; index < unknownArray2.length; index++) {
                stream.writeShort(unknownArray2[index]);
            }
        }
        
        if (anInt7938 != 0) {
        	stream.writeByte(134);
        	stream.writeByte(anInt7938);
        }
        if (bindLink != -1) {
        	stream.writeByte(139);
        	stream.writeShort(bindLink);
        }
        if (bindTemplate != -1) {
        	stream.writeByte(140);
        	stream.writeShort(bindTemplate);
        }
        
        if (unknownArray4 != null) {
            for (int index = 0; index < unknownArray4.length; index++) {
                if (unknownArray4[index] == -1) {
                    continue;
                }
                stream.writeByte(142 + index);
                stream.writeShort(unknownArray4[index]);
            }
        }
        if (unknownArray5 != null) {
            for (int index = 0; index < unknownArray5.length; index++) {
                if (unknownArray5[index] == -1) {
                    continue;
                }
                stream.writeByte(150 + index);
                stream.writeShort(unknownArray5[index]);
            }
        }
        
        if (aBool8089) {
        	stream.writeByte(157);
        }
        if (shardLink != -1) {
        	stream.writeByte(161);
        	stream.writeShort(shardLink);
        }
        if (shardTemplate != -1) {
        	stream.writeByte(162);
        	stream.writeShort(shardTemplate);
        }
        if (shardCombineAmount != 0) {
        	stream.writeByte(163);
        	stream.writeShort(shardCombineAmount);
        }
        if (!shardName.equals("null") || shardName != null) {
        	stream.writeByte(164);
        	stream.writeString(shardName);
        }
        if (stackable == 2)
        	stream.writeByte(165);
        
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

    public final void decode(InputStream stream, int opcode) {
        if (opcode == 1) {
            interfaceModelId = stream.readBigSmart();
        } else if (opcode == 2) {
            name = stream.readString();
        } else if (opcode == 4) {
            zoom2D = stream.readUnsignedShort();
        } else if (opcode == 5) {
            xAngle2D = stream.readUnsignedShort();
        } else if (opcode == 6) {
            yAngle2D = stream.readUnsignedShort();
        } else if (opcode == 7) {
            xOffset2D = stream.readUnsignedShort();
            if (xOffset2D > 32767) {
                xOffset2D -= 65536;
            }
            xOffset2D <<= 0;
        } else if (opcode == 8) {
            yOffset2D = stream.readUnsignedShort();
            if (yOffset2D > 32767) {
                yOffset2D -= 65536;
            }
            yOffset2D <<= 0;
        } else if (opcode == 11) {
            stackable = 1;
        } else if (opcode == 12) {
            cost = stream.readInt();
        } else if (opcode == 13) {
            equipSlotId = stream.readUnsignedByte();
        } else if (opcode == 14) {
            equipId = stream.readUnsignedByte();
        } else if (opcode == 16) {
            members = true;
        } else if (opcode == 18) { // added
            multiStackSize = stream.readUnsignedShort();
        } else if (opcode == 23) {
            maleEquip1 = stream.readBigSmart();
        } else if (opcode == 24) {
            maleEquip2 = stream.readBigSmart();
        } else if (opcode == 25) {
            femaleEquip1 = stream.readBigSmart();
        } else if (opcode == 26) {
            femaleEquip2 = stream.readBigSmart();
        } else if (opcode == 27) {
            anInt7901 = stream.readUnsignedByte();
        } else if (opcode >= 30 && opcode < 35) {
            groundOptions[opcode - 30] = stream.readString();
        } else if (opcode >= 35 && opcode < 40) {
            inventoryOptions[opcode - 35] = stream.readString();
        } else if (opcode == 40) {
            int length = stream.readUnsignedByte();
            recolorSrc = new int[length];
            recolorDst = new int[length];
            for (int index = 0; index < length; index++) {
                recolorSrc[index] = stream.readUnsignedShort();
                recolorDst[index] = stream.readUnsignedShort();
            }
        } else if (opcode == 41) {
            int length = stream.readUnsignedByte();
            retextureSrc = new int[length];
            retextureDst = new int[length];
            for (int index = 0; index < length; index++) {
                retextureSrc[index] = stream.readUnsignedShort();
                retextureDst[index] = stream.readUnsignedShort();
            }
        } else if (opcode == 42) {
            int length = stream.readUnsignedByte();
            recolorDstPalette = new byte[length];
            for (int index = 0; index < length; index++) {
                recolorDstPalette[index] = (byte) stream.readByte();
            }
        } else if (opcode == 43) {
            nameColor = stream.readInt();
            hasNameColor = true;
        } else if (opcode == 44) {
            int length = stream.readUnsignedShort();
            int arraySize = 0;
            for (int modifier = 0; modifier > 0; modifier++) {
                arraySize++;
                unknownArray3 = new byte[arraySize];
                byte offset = 0;
                for (int index = 0; index < arraySize; index++) {
                    if ((length & 1 << index) > 0) {
                        unknownArray3[index] = offset;
                    } else {
                        unknownArray3[index] = -1;
                    }
                }
            }
        } else if (45 == opcode) {
            int i_97_ = (short) stream.readUnsignedShort();
            int i_98_ = 0;
            for (int i_99_ = i_97_; i_99_ > 0; i_99_ >>= 1) {
                i_98_++;
            }
            unknownArray6 = new byte[i_98_];
            byte i_100_ = 0;
            for (int i_101_ = 0; i_101_ < i_98_; i_101_++) {
                if ((i_97_ & 1 << i_101_) > 0) {
                    unknownArray6[i_101_] = i_100_;
                    i_100_++;
                } else {
                    unknownArray6[i_101_] = (byte) -1;
                }
            }
        } else if (opcode == 65) {
            unnoted = true;
        } else if (opcode == 78) {
            colourEquip1 = stream.readBigSmart();
        } else if (opcode == 79) {
            colourEquip2 = stream.readBigSmart();
        } else if (opcode == 90) {
            manHead = stream.readBigSmart();
        } else if (opcode == 91) {
            womanHead = stream.readBigSmart();
        } else if (opcode == 92) {
            manHead2 = stream.readBigSmart();
        } else if (opcode == 93) {
            womanHead2 = stream.readBigSmart();
        } else if (opcode == 94) {
            anInt7900 = stream.readUnsignedShort();
        } else if (opcode == 95) {
            zAngle2D = stream.readUnsignedShort();
        } else if (opcode == 96) {
            dummyItem = stream.readUnsignedByte();
        } else if (opcode == 97) {
            certLink = stream.readUnsignedShort();
        } else if (opcode == 98) {
            certTemplate = stream.readUnsignedShort();
        } else if (opcode >= 100 && opcode < 110) {
            if (stackIds == null) {
                stackIds = new int[10];
                stackAmounts = new int[10];
            }
            stackIds[opcode - 100] = stream.readUnsignedShort();
            stackAmounts[opcode - 100] = stream.readUnsignedShort();
        } else if (opcode == 110) {
            resizeX = stream.readUnsignedShort();
        } else if (opcode == 111) {
            resizeY = stream.readUnsignedShort();
        } else if (opcode == 112) {
            resizeZ = stream.readUnsignedShort();
        } else if (opcode == 113) {
            ambient = stream.readByte();
        } else if (opcode == 114) {
            contrast = stream.readByte();
        } else if (opcode == 115) {
            team = stream.readUnsignedByte();
        } else if (opcode == 121) {
            lentLink = stream.readUnsignedShort();
        } else if (opcode == 122) {
            lentTemplate = stream.readUnsignedShort();
        } else if (opcode == 125) {
            manWearXOffset = stream.readByte() << 2;
            manWearYOffset = stream.readByte() << 2;
            manWearZOffset = stream.readByte() << 2;
        } else if (opcode == 126) {
            womanWearXOffset = stream.readByte() << 2;
            womanWearYOffset = stream.readByte() << 2;
            womanWearZOffset = stream.readByte() << 2;
        } else if (opcode == 127) {
            groundCursorOp = stream.readUnsignedByte();
            groundCursor = stream.readUnsignedShort();
        } else if (opcode == 128) {
            cursor2op = stream.readUnsignedByte();
            cursor2 = stream.readUnsignedShort();
        } else if (opcode == 129) {
            cursor1iop = stream.readUnsignedByte();
            icursor1 = stream.readUnsignedShort();
        } else if (opcode == 130) {
            cursor2iop = stream.readUnsignedByte();
            icursor2 = stream.readUnsignedShort();
        } else if (opcode == 132) {
            int length = stream.readUnsignedByte();
            unknownArray2 = new int[length];
            for (int index = 0; index < length; index++) {
                unknownArray2[index] = stream.readUnsignedShort();
            }
        } else if (opcode == 134) {
            anInt7938 = stream.readUnsignedByte();
        } else if (opcode == 139) {
            bindLink = stream.readUnsignedShort();
        } else if (opcode == 140) {
            bindTemplate = stream.readUnsignedShort();
        } else if (opcode >= 142 && opcode < 147) {
            if (unknownArray4 == null) {
                unknownArray4 = new int[6];
                Arrays.fill(unknownArray4, -1);
            }
            unknownArray4[opcode - 142] = stream.readUnsignedShort();
        } else if (opcode >= 150 && opcode < 155) {
            if (null == unknownArray5) {
                unknownArray5 = new int[5];
                Arrays.fill(unknownArray5, -1);
            }
            unknownArray5[opcode - 150] = stream.readUnsignedShort();
        } else if (156 != opcode) {
	        if (157 == opcode)
	            aBool8089 = true;
	        else if (opcode == 161)
	        	shardLink = stream.readUnsignedShort();
	        else if (opcode == 162)
	        	shardTemplate = stream.readUnsignedShort();
	        else if (opcode == 163)
	        	shardCombineAmount = stream.readUnsignedShort();
	        else if (opcode == 164)
	        	shardName = stream.readString();
	        else if (opcode == 165)
	        	stackable = 2;
	        else if (opcode == 249) {
	            int length = stream.readUnsignedByte();
	            if (config == null) {
	                config = new HashMap<Integer, Object>(length);
	            }
	            for (int index = 0; index < length; index++) {
	                boolean stringInstance = stream.readUnsignedByte() == 1;
	                int key = stream.read24BitInt();
	                Object value = stringInstance ? stream.readString() : stream
	                        .readInt();
	                config.put(key, value);
	            }
	        } else {
	            Main.log("ItemDefinitions", "Missing Opcode " + opcode + " for item " + id);
	        }
	    }
    }

    public int getInvModelId() {
        return interfaceModelId;
    }

    public void setInvModelId(int modelId) {
        this.interfaceModelId = modelId;
    }

    public int getInvModelZoom() {
        return zoom2D;
    }

    public void setInvModelZoom(int modelZoom) {
        this.zoom2D = modelZoom;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
        recolorSrc = null;
        recolorDst = null;
    }

    public void changeModelColor(int originalModelColor, int modifiedModelColor) {
        if (recolorSrc != null) {
            for (int i = 0; i < recolorSrc.length; i++) {
                if (recolorSrc[i] == originalModelColor) {
                    recolorDst[i] = modifiedModelColor;
                    return;
                }
            }
            int[] newOriginalModelColors = Arrays.copyOf(recolorSrc,
                    recolorSrc.length + 1);
            int[] newModifiedModelColors = Arrays.copyOf(recolorDst,
                    recolorDst.length + 1);
            newOriginalModelColors[newOriginalModelColors.length - 1] = originalModelColor;
            newModifiedModelColors[newModifiedModelColors.length - 1] = modifiedModelColor;
            recolorSrc = newOriginalModelColors;
            recolorDst = newModifiedModelColors;
        } else {
            recolorSrc = new int[]{originalModelColor};
            recolorDst = new int[]{modifiedModelColor};
        }
    }

    public String[] getGroundOptions() {
        return groundOptions;
    }

    public String[] getInventoryOptions() {
        return inventoryOptions;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModelRotation1() {
        return xAngle2D;
    }

    public void setModelRotation1(int modelRotation1) {
        this.xAngle2D = modelRotation1;
    }

    public int getModelRotation2() {
        return yAngle2D;
    }

    public void setModelRotation2(int modelRotation2) {
        this.yAngle2D = modelRotation2;
    }

    public int getModelOffset1() {
        return xOffset2D;
    }

    public void setModelOffset1(int modelOffset1) {
        this.xOffset2D = modelOffset1;
    }

    public int getModelOffset2() {
        return yOffset2D;
    }

    public void setModelOffset2(int modelOffset2) {
        this.yOffset2D = modelOffset2;
    }

    public int getStackable() {
        return stackable;
    }

    public void setStackable(int stackable) {
        this.stackable = stackable;
    }

    public int getValue() {
        return cost;
    }

    public void setValue(int value) {
        this.cost = value;
    }

    public boolean isMembersOnly() {
        return members;
    }

    public void setMembersOnly(boolean membersOnly) {
        this.members = membersOnly;
    }

    public int getMaleEquipModelId1() {
        return maleEquip1;
    }

    public void setMaleEquipModelId1(int maleEquipModelId1) {
        this.maleEquip1 = maleEquipModelId1;
    }

    public int getFemaleEquipModelId1() {
        return femaleEquip1;
    }

    public void setFemaleEquipModelId1(int femaleEquipModelId1) {
        this.femaleEquip1 = femaleEquipModelId1;
    }

    public int getMaleEquipModelId2() {
        return maleEquip2;
    }

    public void setMaleEquipModelId2(int maleEquipModelId2) {
        this.maleEquip2 = maleEquipModelId2;
    }

    public int getFemaleEquipModelId2() {
        return femaleEquip2;
    }

    public void setFemaleEquipModelId2(int femaleEquipModelId2) {
        this.femaleEquip2 = femaleEquipModelId2;
    }

    public int getMaleEquipModelId3() {
        return colourEquip1;
    }

    public void setMaleEquipModelId3(int maleEquipModelId3) {
        this.colourEquip1 = maleEquipModelId3;
    }

    public int getFemaleEquipModelId3() {
        return colourEquip2;
    }

    public void setFemaleEquipModelId3(int femaleEquipModelId3) {
        this.colourEquip2 = femaleEquipModelId3;
    }

    public int[] getOriginalModelColors() {
        return recolorSrc;
    }

    public void setOriginalModelColors(int[] originalModelColors) {
        this.recolorSrc = originalModelColors;
    }

    public int[] getModifiedModelColors() {
        return recolorDst;
    }

    public void setModifiedModelColors(int[] modifiedModelColors) {
        this.recolorDst = modifiedModelColors;
    }

    public int[] getOriginalTextureColors() {
        return retextureSrc;
    }

    public void setOriginalTextureColors(int[] originalTextureColors) {
        this.retextureSrc = originalTextureColors;
    }

    public int[] getModifiedTextureColors() {
        return retextureDst;
    }

    public void setModifiedTextureColors(int[] modifiedTextureColors) {
        this.retextureDst = modifiedTextureColors;
    }

    public boolean isUnnoted() {
        return unnoted;
    }

    public void setUnnoted(boolean unnoted) {
        this.unnoted = unnoted;
    }

    public int getSwitchNoteItemId() {
        return certLink;
    }

    public void setSwitchNoteItemId(int switchNoteItemId) {
        this.certLink = switchNoteItemId;
    }

    public int getNotedItemId() {
        return certTemplate;
    }

    public void setNotedItemId(int notedItemId) {
        this.certTemplate = notedItemId;
    }

    public int[] getStackIds() {
        return stackIds;
    }

    public void setStackIds(int[] stackIds) {
        this.stackIds = stackIds;
    }

    public int[] getStackAmounts() {
        return stackAmounts;
    }

    public void setStackAmounts(int[] stackAmounts) {
        this.stackAmounts = stackAmounts;
    }

    public int getTeamId() {
        return team;
    }

    public void setTeamId(int teamId) {
        this.team = teamId;
    }

    public int getSwitchLendItemId() {
        return lentLink;
    }

    public void setSwitchLendItemId(int switchLendItemId) {
        this.lentLink = switchLendItemId;
    }

    public int getLendedItemId() {
        return lentTemplate;
    }

    public void setLendedItemId(int lendedItemId) {
        this.lentTemplate = lendedItemId;
    }

    public int getEquipSlot() {
        return equipSlotId;
    }

    public void setEquipSlot(int equipSlot) {
        this.equipSlotId = equipSlot;
    }

    public int getEquipType() {
        return equipId;
    }

    public void setEquipType(int equipType) {
        this.equipId = equipType;
    }

    public void setGroundOptions(String[] groundOptions) {
        this.groundOptions = groundOptions;
    }

    public void setInventoryOptions(String[] inventoryOptions) {
        this.inventoryOptions = inventoryOptions;
    }
}
