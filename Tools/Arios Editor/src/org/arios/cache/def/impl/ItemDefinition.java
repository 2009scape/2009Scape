package org.arios.cache.def.impl;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.arios.cache.Cache;
import org.arios.cache.def.Definition;
import org.arios.cache.misc.ByteBufferUtils;
import org.arios.workspace.node.Configuration;

/**
 * Represents an item's definitions.
 * @author Jagex
 * @author Emperor
 */
public class ItemDefinition extends Definition {

	/**
	 * The item definitions mapping.
	 */
	private static final Map<Integer, ItemDefinition> DEFINITIONS = new HashMap<>();
	
	/**
	 * The interface model id.
	 */
	private int interfaceModelId;

	/**
	 * The model zoom.
	 */
	private int modelZoom;

	/**
	 * The model rotation.
	 */
	private int modelRotation1;

	/**
	 * The model rotation.
	 */
	private int modelRotation2;

	/**
	 * The model offset.
	 */
	private int modelOffset1;

	/**
	 * The model offset.
	 */
	private int modelOffset2;

	/**
	 * If item is stackable.
	 */
	private boolean stackable;

	/**
	 * The item value.
	 */
	private int value;

	/**
	 * If item is members only.
	 */
	private boolean membersOnly;

	/**
	 * The male model wear id.
	 */
	private int maleWornModelId1 = -1;

	/**
	 * The female model wear id.
	 */
	private int femaleWornModelId1;

	/**
	 * The male model wear id.
	 */
	private int maleWornModelId2 = -1;

	/**
	 * The female wear model id.
	 */
	private int femaleWornModelId2;

	/**
	 * The ground actions.
	 */
	private String[] groundActions;

	/**
	 * The original model colors.
	 */
	private short[] originalModelColors;

	/**
	 * The modified model colors.
	 */
	private short[] modifiedModelColors;

	/**
	 * The texture color 1.
	 */
	private short[] textureColour1;

	/**
	 * The texture color 2.
	 */
	private short[] textureColour2;

	/**
	 * A unknown byte array.
	 */
	private byte[] unknownArray1;

	/**
	 * A unknown integer array.
	 */
	private int[] unknownArray2;

	/**
	 * A unknown integer array.
	 */
	private int[][] unknownArray3;

	/**
	 * If item is noted.
	 */
	private boolean unnoted = true;

	/**
	 * The colour equipment.
	 */
	private int colourEquip1;

	/**
	 * The colour equipment.
	 */
	private int colourEquip2;

	/**
	 * The note item.
	 */
	private int noteId = -1;

	/**
	 * The note template id.
	 */
	private int noteTemplateId = -1;

	/**
	 * The stackable ids.
	 */
	private int[] stackIds;

	/**
	 * The stackable amounts.
	 */
	private int[] stackAmounts;

	/**
	 * The team id.
	 */
	private int teamId;

	/**
	 * The lend id.
	 */
	private int lendId = -1;

	/**
	 * The lend template id.
	 */
	private int lendTemplateId = -1;

	/**
	 * The recolour id.
	 */
	private int recolourId = -1;

	/**
	 * The recolour template id.
	 */
	private int recolourTemplateId = -1;

	/**
	 * The equip id.
	 */
	private int equipId;

	/**
	 * The item requirements
	 */
	private HashMap<Integer, Integer> itemRequirements;

	/**
	 * The clientscript data.
	 */
	private HashMap<Integer, Object> clientScriptData;

	/**
	 * Constructs a new {@code ItemDefinition} {@code Object}.
	 */
	public ItemDefinition() {
		groundActions = new String[] { null, null, "take", null, null };
		options = new String[] { null, null, null, null, "drop" };
	}
	
	/**
	 * Gets an item definition.
	 * @param itemId The item's id.
	 * @return The item definition.
	 */
	public static ItemDefinition forId(int itemId) {
		ItemDefinition def = DEFINITIONS.get(itemId);
		if (def == null) {
			byte[] data = Cache.getIndexes()[19].getFileData(itemId >>> 8, itemId & 0xFF);
			if (data == null) { 
				if (itemId != -1) {
					//System.out.println("Failed loading NPC " + id + ".");
				}
			} else {
				def = parseDefinition(itemId, ByteBuffer.wrap(data));
			}
			if (def == null) {
				def = new ItemDefinition();
			}
			DEFINITIONS.put(itemId, def);
		}
		return def;
	}

	/**
	 * Parses an item's definitions.
	 * @param itemId The item id.
	 * @param buffer The buffer.
	 * @return The item definition.
	 */
	public static ItemDefinition parseDefinition(int itemId, ByteBuffer buffer) {
		ItemDefinition def = new ItemDefinition();
		def.id = itemId;
		while (true) {
			int opcode = buffer.get() & 0xFF;
			if (opcode == 0) {
				break;
			} else if (opcode == 1) {
				def.interfaceModelId = buffer.getShort() & 0xFFFF;
			} else if (opcode == 2) {
				def.name = ByteBufferUtils.getString(buffer);
			} else if (opcode == 3) {
				def.configurations.put("examine", new Configuration<String>(7, ByteBufferUtils.getString(buffer))); //Examine info.
			} else if (opcode == 4) {
				def.modelZoom = buffer.getShort() & 0xFFFF;
			} else if (opcode == 5) {
				def.modelRotation1 = buffer.getShort() & 0xFFFF;
			} else if (opcode == 6) {
				def.modelRotation2 = buffer.getShort() & 0xFFFF;
			} else if (opcode == 7) {
				def.modelOffset1 = buffer.getShort() & 0xFFFF;
				if (def.modelOffset1 > 32767)
					def.modelOffset1 -= 65536;
			} else if (opcode == 8) {
				def.modelOffset2 = buffer.getShort() & 0xFFFF;
				if (def.modelOffset2 > 32767) {
					def.modelOffset2 -= 65536;
				}
			} else if (opcode == 10) {
				buffer.getShort();
			} else if (opcode == 11) {
				def.stackable = true;
			} else if (opcode == 12) {
				def.value = buffer.getInt();
				if (def.value == 0) {
					def.value = 1;
				}
			} else if (opcode == 16) {
				def.membersOnly = true;
			} else if (opcode == 18) {
				buffer.getShort();
			} else if (opcode == 23) {
				def.maleWornModelId1 = buffer.getShort() & 0xFFFF;
				buffer.get();
			} else if (opcode == 24) {
				def.femaleWornModelId1 = buffer.getShort() & 0xFFFF;
			} else if (opcode == 25) {
				def.maleWornModelId2 = buffer.getShort() & 0xFFFF;
				buffer.get();
			} else if (opcode == 26) {
				def.femaleWornModelId2 = buffer.getShort() & 0xFFFF;
			} else if (opcode >= 30 && opcode < 35) {
				def.groundActions[opcode - 30] = ByteBufferUtils.getString(buffer);
			} else if (opcode >= 35 && opcode < 40) {
				def.options[opcode - 35] = ByteBufferUtils.getString(buffer);
			} else if (opcode == 40) {
				int length = buffer.get() & 0xFF;
				def.originalModelColors = new short[length];
				def.modifiedModelColors = new short[length];
				for (int index = 0; index < length; index++) {
					def.originalModelColors[index] = buffer.getShort();
					def.modifiedModelColors[index] = buffer.getShort();
				}
			} else if (opcode == 41) {
				int length = buffer.get() & 0xFF;
				def.textureColour1 = new short[length];
				def.textureColour2 = new short[length];
				for (int index = 0; index < length; index++) {
					def.textureColour1[index] = buffer.getShort();
					def.textureColour2[index] = buffer.getShort();
				}
			} else if (opcode == 42) {
				int length = buffer.get() & 0xFF;
				def.unknownArray1 = new byte[length];
				for (int index = 0; index < length; index++)
					def.unknownArray1[index] = buffer.get();
			} else if (opcode == 65) {
				def.unnoted = true;
			} else if (opcode == 78) {
				def.colourEquip1 = buffer.getShort() & 0xFFFF;
			} else if (opcode == 79) {
				def.colourEquip2 = buffer.getShort() & 0xFFFF;
			} else if (opcode == 90) {
				buffer.getShort();
			} else if (opcode == 91) {
				buffer.getShort();
			} else if (opcode == 92) {
				buffer.getShort();
			} else if (opcode == 93) {
				buffer.getShort();
			} else if (opcode == 95) {
				buffer.getShort();
			} else if (opcode == 96) {
				buffer.get();
			} else if (opcode == 97) {
				def.noteId = buffer.getShort() & 0xFFFF;
			} else if (opcode == 98) {
				def.noteTemplateId = buffer.getShort() & 0xFFFF;
			} else if (opcode >= 100 && opcode < 110) {
				if (def.stackIds == null) {
					def.stackIds = new int[10];
					def.stackAmounts = new int[10];
				}
				def.stackIds[opcode - 100] = buffer.getShort() & 0xFFFF;
				def.stackAmounts[opcode - 100] = buffer.getShort() & 0xFFFF;
			} else if (opcode == 110) {
				buffer.getShort();
			} else if (opcode == 111) {
				buffer.getShort();
			} else if (opcode == 112) {
				buffer.getShort();
			} else if (opcode == 113) {
				buffer.get();
			} else if (opcode == 114) {
				buffer.get();
			} else if (opcode == 115) {
				def.teamId = buffer.get();
			} else if (opcode == 121) {
				def.lendId = buffer.getShort() & 0xFFFF;
			} else if (opcode == 122) {
				def.lendTemplateId = buffer.getShort() & 0xFFFF;
			} else if (opcode == 124) {
				if (def.unknownArray3 == null) {
					def.unknownArray3 = new int[11][];
				}
				int slot = buffer.get();
				def.unknownArray3[slot] = new int[6];
				for (int i = 0; i < 6; i++) {
					def.unknownArray3[slot][i] = buffer.getShort();
				}
			} else if (opcode == 125) {
				buffer.get();
				buffer.get();
				buffer.get();
			} else if (opcode == 126) {
				buffer.get();
				buffer.get();
				buffer.get();
			} else if (opcode == 127) {
				buffer.get();
				buffer.getShort();
			} else if (opcode == 128) {
				buffer.get();
				buffer.getShort();
			} else if (opcode == 129) {
				buffer.get();
				buffer.getShort();
			} else if (opcode == 130) {
				buffer.get();
				buffer.getShort();
			} else if (opcode == 132) {
				int length = buffer.get() & 0xFF;
				def.unknownArray2 = new int[length];
				for (int index = 0; index < length; index++) {
					def.unknownArray2[index] = buffer.getShort() & 0xFFFF;
				}
			} else if (opcode == 134) {
				buffer.get();
			} else if (opcode == 139) {
				def.recolourId = buffer.getShort() & 0xFFFF;
			} else if (opcode == 140) {
				def.recolourTemplateId = buffer.getShort() & 0xFFFF;
			} else if (opcode == 249) {
				int length = buffer.get() & 0xFF;
				if (def.clientScriptData == null) {
					def.clientScriptData = new HashMap<Integer, Object>();
				}
				for (int index = 0; index < length; index++) {
					boolean string = (buffer.get() & 0xFF) == 1;
					int key = ByteBufferUtils.getTriByte(buffer);
					Object value = string ? ByteBufferUtils.getString(buffer) : buffer.getInt();
					def.clientScriptData.put(key, value);
					System.out.println(key + " " + value);
				}
			} else {
				System.out.println("Unhandled opcode! opcode: " + opcode);
				break;
			}
		}
		return def;
	}

	/**
	 * Defines the definitions for noted, lending and recolored items.
	 */
	public static void defineTemplates() {
		int equipId = 0;
		for (int i = 0; i < DEFINITIONS.size(); i++) {
			ItemDefinition def = forId(i);
			if (def != null && (def.maleWornModelId1 >= 0 || def.maleWornModelId2 >= 0)) {
				def.equipId = equipId++;
//				if (i < 3200)
//					System.out.println("Item [id=" +  i + ", name=" + def.getName() + "] equip id: " + (equipId - 1) + " [models=" + def.maleWornModelId1 + ", " + def.maleWornModelId2 + ", " + def.femaleWornModelId1 + ", " + def.femaleWornModelId2 + ".");
			}
			if (def.noteTemplateId != -1) {
				def.transferNoteDefinition(forId(def.noteId), forId(def.noteTemplateId));
			}
			if (def.lendTemplateId != -1) {
				def.transferLendDefinition(forId(def.lendId), forId(def.lendTemplateId));
			}
			if (def.recolourTemplateId != -1) {
				def.transferRecolourDefinition(forId(def.recolourId), forId(def.recolourTemplateId));
			}
		}
	}

	/**
	 * Transfers definitions for noted items.
	 * @param reference The reference definitions.
	 * @param templateReference The template definitions.
	 */
	public void transferNoteDefinition(ItemDefinition reference, ItemDefinition templateReference) {
		membersOnly = reference.membersOnly;
		interfaceModelId = templateReference.interfaceModelId;
		originalModelColors = templateReference.originalModelColors;
		name = reference.name;
		modelOffset2 = templateReference.modelOffset2;
		textureColour1 = templateReference.textureColour1;
		value = reference.value;
		modelRotation2 = templateReference.modelRotation2;
		stackable = true;
		unnoted = false;
		modifiedModelColors = templateReference.modifiedModelColors;
		modelRotation1 = templateReference.modelRotation1;
		modelZoom = templateReference.modelZoom;
		textureColour1 = templateReference.textureColour1;
	}

	/**
	 * Transfers definitions for lending items.
	 * @param reference The reference definitions.
	 * @param templateReference The template definitions.
	 */
	public void transferLendDefinition(ItemDefinition reference, ItemDefinition templateReference) {
		femaleWornModelId1 = reference.femaleWornModelId1;
		maleWornModelId2 = reference.maleWornModelId2;
		membersOnly = reference.membersOnly;
		interfaceModelId = templateReference.interfaceModelId;
		textureColour2 = reference.textureColour2;
		groundActions = reference.groundActions;
		unknownArray1 = reference.unknownArray1;
		modelRotation1 = templateReference.modelRotation1;
		modelRotation2 = templateReference.modelRotation2;
		originalModelColors = reference.originalModelColors;
		name = reference.name;
		maleWornModelId1 = reference.maleWornModelId1;
		colourEquip1 = reference.colourEquip1;
		teamId = reference.teamId;
		modelOffset2 = templateReference.modelOffset2;
		clientScriptData = reference.clientScriptData;
		modifiedModelColors = reference.modifiedModelColors;
		colourEquip2 = reference.colourEquip2;
		modelOffset1 = templateReference.modelOffset1;
		textureColour1 = reference.textureColour1;
		value = 0;
		modelZoom = templateReference.modelZoom;
		options = new String[5];
		femaleWornModelId2 = reference.femaleWornModelId2;
		if (reference.options != null) {
			options = reference.options.clone();
		}
	}

	/**
	 * Transfers definitions for recolored items.
	 * @param reference The reference definitions.
	 * @param templateReference The template definitions.
	 */
	public void transferRecolourDefinition(ItemDefinition reference, ItemDefinition templateReference) {
		femaleWornModelId2 = reference.femaleWornModelId2;
		options = new String[5];
		modelRotation2 = templateReference.modelRotation2;
		name = reference.name;
		maleWornModelId1 = reference.maleWornModelId1;
		modelOffset2 = templateReference.modelOffset2;
		femaleWornModelId1 = reference.femaleWornModelId1;
		maleWornModelId2 = reference.maleWornModelId2;
		modelOffset1 = templateReference.modelOffset1;
		unknownArray1 = reference.unknownArray1;
		stackable = reference.stackable;
		modelRotation1 = templateReference.modelRotation1;
		textureColour1 = reference.textureColour1;
		colourEquip1 = reference.colourEquip1;
		textureColour2 = reference.textureColour2;
		modifiedModelColors = reference.modifiedModelColors;
		modelZoom = templateReference.modelZoom;
		colourEquip2 = reference.colourEquip2;
		teamId = reference.teamId;
		value = 0;
		groundActions = reference.groundActions;
		originalModelColors = reference.originalModelColors;
		membersOnly = reference.membersOnly;
		clientScriptData = reference.clientScriptData;
		interfaceModelId = templateReference.interfaceModelId;
		if (reference.options != null) {
			options = reference.options.clone();
		}
	}

	/**
	 * Gets the id.
	 * @return The id.
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * @param id The id to set.
	 */
	@Override
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the interfaceModelId.
	 * @return The interfaceModelId.
	 */
	public int getInterfaceModelId() {
		return interfaceModelId;
	}

	/**
	 * Sets the interfaceModelId.
	 * @param interfaceModelId The interfaceModelId to set.
	 */
	public void setInterfaceModelId(int interfaceModelId) {
		this.interfaceModelId = interfaceModelId;
	}

	/**
	 * Gets the name.
	 * @return The name.
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * @param name The name to set.
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the modelZoom.
	 * @return The modelZoom.
	 */
	public int getModelZoom() {
		return modelZoom;
	}

	/**
	 * Sets the modelZoom.
	 * @param modelZoom The modelZoom to set.
	 */
	public void setModelZoom(int modelZoom) {
		this.modelZoom = modelZoom;
	}

	/**
	 * Gets the modelRotation1.
	 * @return The modelRotation1.
	 */
	public int getModelRotation1() {
		return modelRotation1;
	}

	/**
	 * Sets the modelRotation1.
	 * @param modelRotation1 The modelRotation1 to set.
	 */
	public void setModelRotation1(int modelRotation1) {
		this.modelRotation1 = modelRotation1;
	}

	/**
	 * Gets the modelRotation2.
	 * @return The modelRotation2.
	 */
	public int getModelRotation2() {
		return modelRotation2;
	}

	/**
	 * Sets the modelRotation2.
	 * @param modelRotation2 The modelRotation2 to set.
	 */
	public void setModelRotation2(int modelRotation2) {
		this.modelRotation2 = modelRotation2;
	}

	/**
	 * Gets the modelOffset1.
	 * @return The modelOffset1.
	 */
	public int getModelOffset1() {
		return modelOffset1;
	}

	/**
	 * Sets the modelOffset1.
	 * @param modelOffset1 The modelOffset1 to set.
	 */
	public void setModelOffset1(int modelOffset1) {
		this.modelOffset1 = modelOffset1;
	}

	/**
	 * Gets the modelOffset2.
	 * @return The modelOffset2.
	 */
	public int getModelOffset2() {
		return modelOffset2;
	}

	/**
	 * Sets the modelOffset2.
	 * @param modelOffset2 The modelOffset2 to set.
	 */
	public void setModelOffset2(int modelOffset2) {
		this.modelOffset2 = modelOffset2;
	}

	/**
	 * Gets the stackable.
	 * @return The stackable.
	 */
	public boolean isStackable() {
		return stackable || !this.unnoted;
	}

	/**
	 * Sets the stackable.
	 * @param stackable The stackable to set.
	 */
	public void setStackable(boolean stackable) {
		this.stackable = stackable;
	}

	/**
	 * Gets the value.
	 * @return The value.
	 */
	public int getValue() {
		if (value == 0) {
			return 1;
		}
		return value;
	}
	/**
	 *@return The value.
	 */
	public int getMaxValue() {
		if ((int) (value * 1.05) <= 0) {
			return 1;
		}
		return (int) (value * 1.05);
	}
	/**
	 *@return The value.
	 */
	public int getMinValue() {
		if ((int) (value * .95) <= 0) {
			return 1;
		}
		return (int) (value * .95);
	}
	/**
	 * Sets the value.
	 * @param value The value to set.
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Gets the membersOnly.
	 * @return The membersOnly.
	 */
	public boolean isMembersOnly() {
		return membersOnly;
	}

	/**
	 * Sets the membersOnly.
	 * @param membersOnly The membersOnly to set.
	 */
	public void setMembersOnly(boolean membersOnly) {
		this.membersOnly = membersOnly;
	}

	/**
	 * Gets the maleWornModelId1.
	 * @return The maleWornModelId1.
	 */
	public int getMaleWornModelId1() {
		return maleWornModelId1;
	}

	/**
	 * Sets the maleWornModelId1.
	 * @param maleWornModelId1 The maleWornModelId1 to set.
	 */
	public void setMaleWornModelId1(int maleWornModelId1) {
		this.maleWornModelId1 = maleWornModelId1;
	}

	/**
	 * Gets the femaleWornModelId1.
	 * @return The femaleWornModelId1.
	 */
	public int getFemaleWornModelId1() {
		return femaleWornModelId1;
	}

	/**
	 * Sets the femaleWornModelId1.
	 * @param femaleWornModelId1 The femaleWornModelId1 to set.
	 */
	public void setFemaleWornModelId1(int femaleWornModelId1) {
		this.femaleWornModelId1 = femaleWornModelId1;
	}

	/**
	 * Gets the maleWornModelId2.
	 * @return The maleWornModelId2.
	 */
	public int getMaleWornModelId2() {
		return maleWornModelId2;
	}

	/**
	 * Sets the maleWornModelId2.
	 * @param maleWornModelId2 The maleWornModelId2 to set.
	 */
	public void setMaleWornModelId2(int maleWornModelId2) {
		this.maleWornModelId2 = maleWornModelId2;
	}

	/**
	 * Gets the femaleWornModelId2.
	 * @return The femaleWornModelId2.
	 */
	public int getFemaleWornModelId2() {
		return femaleWornModelId2;
	}

	/**
	 * Sets the femaleWornModelId2.
	 * @param femaleWornModelId2 The femaleWornModelId2 to set.
	 */
	public void setFemaleWornModelId2(int femaleWornModelId2) {
		this.femaleWornModelId2 = femaleWornModelId2;
	}

	/**
	 * Gets the groundOptions.
	 * @return The groundOptions.
	 */
	public String[] getGroundOptions() {
		return groundActions;
	}

	/**
	 * Sets the groundOptions.
	 * @param groundOptions The groundOptions to set.
	 */
	public void setGroundOptions(String[] groundOptions) {
		this.groundActions = groundOptions;
	}

	/**
	 * Gets the inventoryOptions.
	 * @return The inventoryOptions.
	 */
	public String[] getInventoryOptions() {
		return options;
	}

	/**
	 * Sets the inventoryOptions.
	 * @param inventoryOptions The inventoryOptions to set.
	 */
	public void setInventoryOptions(String[] inventoryOptions) {
		this.options = inventoryOptions;
	}

	/**
	 * Gets the originalModelColors.
	 * @return The originalModelColors.
	 */
	public short[] getOriginalModelColors() {
		return originalModelColors;
	}

	/**
	 * Sets the originalModelColors.
	 * @param originalModelColors The originalModelColors to set.
	 */
	public void setOriginalModelColors(short[] originalModelColors) {
		this.originalModelColors = originalModelColors;
	}

	/**
	 * Gets the modifiedModelColors.
	 * @return The modifiedModelColors.
	 */
	public short[] getModifiedModelColors() {
		return modifiedModelColors;
	}

	/**
	 * Sets the modifiedModelColors.
	 * @param modifiedModelColors The modifiedModelColors to set.
	 */
	public void setModifiedModelColors(short[] modifiedModelColors) {
		this.modifiedModelColors = modifiedModelColors;
	}

	/**
	 * Gets the textureColour1.
	 * @return The textureColour1.
	 */
	public short[] getTextureColour1() {
		return textureColour1;
	}

	/**
	 * Sets the textureColour1.
	 * @param textureColour1 The textureColour1 to set.
	 */
	public void setTextureColour1(short[] textureColour1) {
		this.textureColour1 = textureColour1;
	}

	/**
	 * Gets the textureColour2.
	 * @return The textureColour2.
	 */
	public short[] getTextureColour2() {
		return textureColour2;
	}

	/**
	 * Sets the textureColour2.
	 * @param textureColour2 The textureColour2 to set.
	 */
	public void setTextureColour2(short[] textureColour2) {
		this.textureColour2 = textureColour2;
	}

	/**
	 * Gets the unknownArray1.
	 * @return The unknownArray1.
	 */
	public byte[] getUnknownArray1() {
		return unknownArray1;
	}

	/**
	 * Sets the unknownArray1.
	 * @param unknownArray1 The unknownArray1 to set.
	 */
	public void setUnknownArray1(byte[] unknownArray1) {
		this.unknownArray1 = unknownArray1;
	}

	/**
	 * Gets the unknownArray2.
	 * @return The unknownArray2.
	 */
	public int[] getUnknownArray2() {
		return unknownArray2;
	}

	/**
	 * Sets the unknownArray2.
	 * @param unknownArray2 The unknownArray2 to set.
	 */
	public void setUnknownArray2(int[] unknownArray2) {
		this.unknownArray2 = unknownArray2;
	}

	/**
	 * Gets the unnoted.
	 * @return The unnoted.
	 */
	public boolean isUnnoted() {
		return unnoted;
	}

	/**
	 * Sets the unnoted.
	 * @param unnoted The unnoted to set.
	 */
	public void setUnnoted(boolean unnoted) {
		this.unnoted = unnoted;
	}

	/**
	 * Gets the colourEquip1.
	 * @return The colourEquip1.
	 */
	public int getColourEquip1() {
		return colourEquip1;
	}

	/**
	 * Sets the colourEquip1.
	 * @param colourEquip1 The colourEquip1 to set.
	 */
	public void setColourEquip1(int colourEquip1) {
		this.colourEquip1 = colourEquip1;
	}

	/**
	 * Gets the colourEquip2.
	 * @return The colourEquip2.
	 */
	public int getColourEquip2() {
		return colourEquip2;
	}

	/**
	 * Sets the colourEquip2.
	 * @param colourEquip2 The colourEquip2 to set.
	 */
	public void setColourEquip2(int colourEquip2) {
		this.colourEquip2 = colourEquip2;
	}

	/**
	 * Gets the noteId.
	 * @return The noteId.
	 */
	public int getNoteId() {
		return noteId;
	}

	/**
	 * Sets the noteId.
	 * @param noteId The noteId to set.
	 */
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	/**
	 * Gets the noteTemplateId.
	 * @return The noteTemplateId.
	 */
	public int getNoteTemplateId() {
		return noteTemplateId;
	}

	/**
	 * Sets the noteTemplateId.
	 * @param noteTemplateId The noteTemplateId to set.
	 */
	public void setNoteTemplateId(int noteTemplateId) {
		this.noteTemplateId = noteTemplateId;
	}

	/**
	 * Gets the stackIds.
	 * @return The stackIds.
	 */
	public int[] getStackIds() {
		return stackIds;
	}

	/**
	 * Sets the stackIds.
	 * @param stackIds The stackIds to set.
	 */
	public void setStackIds(int[] stackIds) {
		this.stackIds = stackIds;
	}

	/**
	 * Gets the stackAmounts.
	 * @return The stackAmounts.
	 */
	public int[] getStackAmounts() {
		return stackAmounts;
	}

	/**
	 * Sets the stackAmounts.
	 * @param stackAmounts The stackAmounts to set.
	 */
	public void setStackAmounts(int[] stackAmounts) {
		this.stackAmounts = stackAmounts;
	}

	/**
	 * Gets the teamId.
	 * @return The teamId.
	 */
	public int getTeamId() {
		return teamId;
	}

	/**
	 * Sets the teamId.
	 * @param teamId The teamId to set.
	 */
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	/**
	 * Gets the lendId.
	 * @return The lendId.
	 */
	public int getLendId() {
		return lendId;
	}

	/**
	 * Sets the lendId.
	 * @param lendId The lendId to set.
	 */
	public void setLendId(int lendId) {
		this.lendId = lendId;
	}

	/**
	 * Gets the lendTemplateId.
	 * @return The lendTemplateId.
	 */
	public int getLendTemplateId() {
		return lendTemplateId;
	}

	/**
	 * Sets the lendTemplateId.
	 * @param lendTemplateId The lendTemplateId to set.
	 */
	public void setLendTemplateId(int lendTemplateId) {
		this.lendTemplateId = lendTemplateId;
	}

	/**
	 * Gets the recolourId.
	 * @return The recolourId.
	 */
	public int getRecolourId() {
		return recolourId;
	}

	/**
	 * Sets the recolourId.
	 * @param recolourId The recolourId to set.
	 */
	public void setRecolourId(int recolourId) {
		this.recolourId = recolourId;
	}

	/**
	 * Gets the recolourTemplateId.
	 * @return The recolourTemplateId.
	 */
	public int getRecolourTemplateId() {
		return recolourTemplateId;
	}

	/**
	 * Sets the recolourTemplateId.
	 * @param recolourTemplateId The recolourTemplateId to set.
	 */
	public void setRecolourTemplateId(int recolourTemplateId) {
		this.recolourTemplateId = recolourTemplateId;
	}

	/**
	 * Gets the equipId.
	 * @return The equipId.
	 */
	public int getEquipId() {
		return equipId;
	}

	/**
	 * Sets the equipId.
	 * @param equipId The equipId to set.
	 */
	public void setEquipId(int equipId) {
		this.equipId = equipId;
	}

	/**
	 * Gets the clientScriptData.
	 * @return The clientScriptData.
	 */
	public HashMap<Integer, Object> getClientScriptData() {
		return clientScriptData;
	}

	/**
	 * Sets the clientScriptData.
	 * @param clientScriptData The clientScriptData to set.
	 */
	public void setClientScriptData(HashMap<Integer, Object> clientScriptData) {
		this.clientScriptData = clientScriptData;
	}

	/**
	 * If the item has the specified item.
	 * @param optionName The action.
	 * @return If the item has the specified action {@code true}.
	 */
	public boolean hasAction(String optionName) {
		if (options == null) {
			return false;
		}
		for (String action : options) {
			if (action == null) {
				continue;
			}
			if (action.equalsIgnoreCase(optionName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * If the item has the destroy action.
	 * @return If the item has the destroy action {@code true}.
	 */
	public boolean hasDestroyAction() {
		if (options == null) {
			return false;
		}
		for (String action : options) {
			if (action == null) {
				continue;
			}
			if (action.equalsIgnoreCase("Destroy")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * If the item has the wear action.
	 * @return If the item has the wear action {@code true}.
	 */
	public boolean hasWearAction() {
		if (options == null) {
			return false;
		}
		for (String action : options) {
			if (action == null) {
				continue;
			}
			if (action.equalsIgnoreCase("wield") || action.equalsIgnoreCase("wear") || action.equalsIgnoreCase("equip")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * If the item has a special bar.
	 * @return If the item has a special bar {@code true}.
	 */
	public boolean hasSpecialBar() {
		if (clientScriptData == null) {
			return false;
		}
		Object specialBar = clientScriptData.get(686);
		if (specialBar != null && specialBar instanceof Integer) {
			return (Integer) specialBar == 1;
		}
		return false;
	}

	/**
	 * Get the quest id for the item.
	 * @return The quest id.
	 */
	public int getQuestId() {
		if (clientScriptData == null) {
			return -1;
		}
		Object questId = clientScriptData.get(861);
		if (questId != null && questId instanceof Integer) {
			return (Integer) questId;
		}
		return -1;
	}

	/**
	 * Get the archive id.
	 * @return The archive id.
	 */
	public int getArchiveId() {
		return id >>> 8;
	}

	/**
	 * Get the file id.
	 * @return The file id.
	 */
	public int getFileId() {
		return 0xff & id;
	}
	
	/**
	 * Gets the definitions.
	 * @return The definitions.
	 */
	public static Map<Integer, ItemDefinition> getDefinitions() {
		return DEFINITIONS;
	}

	/**
	 * @return the itemRequirements.
	 */
	public HashMap<Integer, Integer> getItemRequirements() {
		return itemRequirements;
	}

	/**
	 * @param itemRequirements the itemRequirements to set.
	 */
	public void setItemRequirements(HashMap<Integer, Integer> itemRequirements) {
		this.itemRequirements = itemRequirements;
	}
}