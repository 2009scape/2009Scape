package alex.cache.loaders;

import java.util.HashMap;

import alex.CacheLoader;
import alex.io.Stream;
import alex.util.Methods;

public class ItemDefinition {

	private static HashMap<Integer, ItemDefinition> itemsDefs = new HashMap<Integer, ItemDefinition>();

	
	public int id;
	private boolean loaded;
	
	private int interfaceModelId;
	private String name;
	
	//model size information
	private int modelZoom;
	private int modelRotation1;
	private int modelRotation2;
	private int modelOffset1;
	private int modelOffset2;
	
	//extra information
	private int stackable;
	private int value;
	private boolean membersOnly;
	
	//wearing model information
	private int maleWornModelId1;
	private int femaleWornModelId1;
	private int maleWornModelId2;
	private int femaleWornModelId2;
	
	//options
	private String[] groundOptions;
	public String[] inventoryOptions;
	
	//model information
	private short[] originalModelColors;
	private short[] modifiedModelColors;
	private short[] textureColour1;
	private short[] textureColour2;
	private byte[] unknownArray1;
	private int[] unknownArray2;
	//extra information, not used for newer items
	private boolean unnoted;
	
	private int colourEquip1;
	private int colourEquip2;
	private int unknownInt1;
	private int unknownInt2;
	private int unknownInt3;
	private int unknownInt4;
	private int unknownInt5;
	private int unknownInt6;
	private int certId;
	private int certTemplateId;
	private int[] stackIds;
	private int[] stackAmounts;
	private int unknownInt7;
	private int unknownInt8;
	private int unknownInt9;
	private int unknownInt10;
	private int unknownInt11;
	private int teamId;
	private int lendId;
	private int lendTemplateId;
	private int unknownInt12;
	private int unknownInt13;
	private int unknownInt14;
	private int unknownInt15;
	private int unknownInt16;
	private int unknownInt17;
	private int unknownInt18;
	private int unknownInt19;
	private int unknownInt20;
	private int unknownInt21;
	private int unknownInt22;
	private int unknownInt23;
	private static HashMap<Integer, Object> clientScriptData;
	
	public static ItemDefinition getItemDefinition(int itemId) {
		return getItemDefinition(itemId, true);
	}
	
	public static ItemDefinition getItemDefinition(int itemId, boolean load) {
		if (itemsDefs.containsKey(itemId))
			return itemsDefs.get(itemId);
		ItemDefinition def = new ItemDefinition(itemId, load);
		itemsDefs.put(itemId, def);
		return def;
	}

	public ItemDefinition(int id) {
		this(id, true);
	}

	public ItemDefinition(int id, boolean load) {
		this.id = id;
		setDefaultsVariableValules();
		setDefaultOptions();
		if (load) {
			loadItemDefinition();
		}
	}

	public boolean isLoaded() {
		return loaded;
	}

	public void loadItemDefinition() {
		byte[] data = CacheLoader.getFileSystems()[Methods.ITEMDEF_IDX_ID].getFile(id >>> 8, 0xff & id);
		if (data == null) {
			System.out.println("FAILED LOADING ITEM " + id);
			return;
		}
		readOpcodeValues(new Stream(data));
		printClientScriptData();
		loaded = true;
	}
	
	public boolean hasSpecialBar() {
		if(clientScriptData == null)
			return false;
		Object specialBar = clientScriptData.get(686);
		if(specialBar != null && specialBar instanceof Integer)
			return (Integer) specialBar == 1;
		return false;
	}
	public int getRenderAnimId() {
		if(clientScriptData == null)
			return 1426;
		Object animId = clientScriptData.get(644);
		if(animId != null && animId instanceof Integer)
			return (Integer) animId;
		return 1426;
	}
	
	public int getQuestId() {
		if(clientScriptData == null)
			return -1;
		Object questId = clientScriptData.get(861);
		if(questId != null && questId instanceof Integer)
			return (Integer) questId;
		return -1;
	}
	
	public HashMap<Integer, Integer> getWearingSkillRequiriments() {
		if(clientScriptData == null)
			return null;
		HashMap<Integer, Integer> skills = new HashMap<Integer, Integer>();
		int nextLevel = -1;
		int nextSkill = -1;
		for(int key : clientScriptData.keySet()) {
			Object value = clientScriptData.get(key);
			if(value instanceof String)
				continue;
			if(key == 23) {
				skills.put(Methods.RANGE, (Integer) value);
				skills.put(Methods.FIREMAKING, 61);
			}else if (key >= 749 && key < 797) {
				if(key % 2 == 0)
					nextLevel = (Integer) value;
				else
					nextSkill = (Integer) value;
				if(nextLevel != -1 && nextSkill != -1) {
					skills.put(nextSkill, nextLevel);
					nextLevel = -1;
					nextSkill = -1;
				}
			}
				
		}
		return skills;
	}
	
	//test :P
	public void printClientScriptData() {
		for(int key : clientScriptData.keySet()) {
			Object value = clientScriptData.get(key);
			System.out.println("KEY: "+key+", VALUE: "+value);
		}
		HashMap<Integer, Integer> requiriments = getWearingSkillRequiriments();
		if(requiriments == null) {
			System.out.println("null.");
			return;
		}
		System.out.println(requiriments.keySet().size());
		for(int key : requiriments.keySet()) {
			Object value = requiriments.get(key);
			System.out.println("SKILL: "+key+", LEVEL: "+value);
		}
	}
	
	
	private void setDefaultOptions() {
		groundOptions = new String[] { null, null, "take", null, null };
		inventoryOptions = new String[] { null, null, null, null, "drop" };
	}
	
	private void setDefaultsVariableValules() {
		name = "null";
		maleWornModelId1 = -1;
		maleWornModelId2 = -1;
		femaleWornModelId1 = -1;
		femaleWornModelId2 = -1;
		modelZoom = 2000;
		lendId = -1;
		lendTemplateId = -1;
		certId = -1;
		certTemplateId = -1;
		unknownInt9 = 128;
		value = 1;
		colourEquip1 = -1;
		colourEquip2 = -1;
	}
	
	public byte[] packItemDefinition() {
		Stream stream = new Stream(10000);
		
		stream.putByte(1);
		stream.putShort(interfaceModelId);
		
		if(!name.equals("null")) {
			stream.putByte(2);
			stream.putString(name);
		}
		
		if(modelZoom != 2000) {
			stream.putByte(4);
			stream.putShort(modelZoom);
		}
		
		if(modelRotation1 != 0) {
			stream.putByte(5);
			stream.putShort(modelRotation1);
		}
		
		if(modelRotation2 != 0) {
			stream.putByte(6);
			stream.putShort(modelRotation2);
		}
		
		if(modelOffset1 != 0) {
			stream.putByte(7);
			int value = modelOffset1 >>= 0;
			if (value < 0)
				value += 65536;
			stream.putShort(value);
		}
		
		if(modelOffset2 != 0) {
			stream.putByte(8);
			int value = modelOffset2 >>= 0;
			if (value < 0)
				value += 65536;
			stream.putShort(value);
		}
		
		if(stackable >= 1) {
			stream.putByte(11);
		}
		
		if(value != 1) {
			stream.putByte(12);
			stream.putInt(value);
		}
		
		if(membersOnly) {
			stream.putByte(16);
		}
		
		if(maleWornModelId1 != -1) {
			stream.putByte(23);
			stream.putShort(maleWornModelId1);
		}
		
		if(maleWornModelId2 != -1) {
			stream.putByte(24);
			stream.putShort(maleWornModelId2);
		}
		
		if(femaleWornModelId1 != -1) {
			stream.putByte(25);
			stream.putShort(femaleWornModelId1);
		}
		
		if(femaleWornModelId2 != -1) {
			stream.putByte(26);
			stream.putShort(femaleWornModelId2);
		}
		
		for(int index = 0; index < groundOptions.length; index++) {
			if(groundOptions[index] == null || (index == 2 && groundOptions[index].equals("take")))
				continue;
			stream.putByte(30+index);
			stream.putString(groundOptions[index]);
		}
		
		for(int index = 0; index < inventoryOptions.length; index++) {
			if(inventoryOptions[index] == null || (index == 4 && inventoryOptions[index].equals("drop")))
				continue;
			stream.putByte(35+index);
			stream.putString(inventoryOptions[index]);
		}
		
		if(originalModelColors != null && modifiedModelColors != null) {
			stream.putByte(40);
			stream.putByte(originalModelColors.length);
			for(int index = 0; index < originalModelColors.length; index++) {
				stream.putShort(originalModelColors[index]);
				stream.putShort(modifiedModelColors[index]);
			}
		}
		
		if(textureColour1 != null && textureColour2 != null) {
			stream.putByte(41);
			stream.putByte(textureColour1.length);
			for(int index = 0; index < textureColour1.length; index++) {
				stream.putShort(textureColour1[index]);
				stream.putShort(textureColour2[index]);
			}
		}
		
		if(unknownArray1 != null) {
			stream.putByte(42);
			stream.putByte(unknownArray1.length);
			for(int index = 0; index < unknownArray1.length; index++)
				stream.putByte(unknownArray1[index]);
		}
		if(unnoted) {
			stream.putByte(65);
		}
		
		if(colourEquip1 != -1) {
			stream.putByte(78);
			stream.putShort(colourEquip1);
		}
		
		if(colourEquip2 != -1) {
			stream.putByte(79);
			stream.putShort(colourEquip2);
		}
		
		//TODO FEW OPCODES HERE
		
		if(certId != -1) {
			stream.putByte(97);
			stream.putShort(certId);
		}
		
		if(certTemplateId != -1) {
			stream.putByte(98);
			stream.putShort(certTemplateId);
		}
		
		if(stackIds != null && stackAmounts != null) {
			for(int index = 0; index < stackIds.length; index++) {
				if(stackIds[index] == 0 && stackAmounts[index] == 0)
					continue;
				stream.putByte(100+index);
				stream.putShort(stackIds[index]);
				stream.putShort(stackAmounts[index]);
			}
		}
		
		//TODO FEW OPCODES HERE
		
		if(teamId != 0) {
			stream.putByte(115);
			stream.putByte(teamId);
		}
		
		if(lendId != -1) {
			stream.putByte(121);
			stream.putShort(lendId);
		}
		
		if(lendTemplateId != -1) {
			stream.putByte(122);
			stream.putShort(lendTemplateId);
		}
		
		
		//TODO FEW OPCODES HERE
		
		if(unknownArray2 != null) {
			stream.putByte(132);
			stream.putByte(unknownArray2.length);
			for(int index = 0; index < unknownArray2.length; index++)
				stream.putShort(unknownArray2[index]);
		}
		
		if(clientScriptData != null) {
			stream.putByte(249);
			stream.putByte(clientScriptData.size());
			for(int key : clientScriptData.keySet()) {
				Object value = clientScriptData.get(key);
				stream.putByte(value instanceof String ? 1 : 0);
				stream.putMediumInt(key);
				if(value instanceof String) {
					stream.putString((String) value);
				}else{
					stream.putInt((Integer) value);
				}
			}
		}
		
		//end
		stream.putByte(0);
		
		byte[] data = new byte[stream.offset];
		stream.offset = 0;
		stream.getBytes(data, 0, data.length);
		return data;

	}
	
	private void readValues(Stream stream, int opcode) {
		if(opcode == 1)
			interfaceModelId = stream.getUShort();
		else if (opcode == 2)
			setName(stream.getString());
		else if (opcode == 4)
			modelZoom = stream.getUShort();
		else if (opcode == 5)
			modelRotation1 = stream.getUShort();
		else if (opcode == 6)
			modelRotation2 = stream.getUShort();
		else if (opcode == 7) {
			modelOffset1 = stream.getUShort();
			if (modelOffset1 > 32767)
				modelOffset1 -= 65536;
			modelOffset1 <<= 0;
		}else if (opcode == 8) {
			modelOffset2 = stream.getUShort();
			if (modelOffset2 > 32767)
				modelOffset2 -= 65536;
			modelOffset2 <<= 0;
		}else if (opcode == 11)
			stackable = 1;
		else if (opcode == 12)
			value = stream.getInt();
		else if (opcode == 16)
			membersOnly = true;
		else if (opcode == 23)
			setMaleWornModelId1(stream.getUShort());
		else if (opcode == 24)
			femaleWornModelId1 = stream.getUShort();
		else if (opcode == 25)
			setMaleWornModelId2(stream.getUShort());
		else if (opcode == 26)
			femaleWornModelId2 = stream.getUShort();
		else if (opcode >= 30 && opcode < 35)
			groundOptions[opcode-30] = stream.getString();
		else if (opcode >= 35 && opcode < 40)
			inventoryOptions[opcode-35] = stream.getString();
		else if (opcode == 40) {
			int length = stream.getUByte();
			originalModelColors = new short[length];
			modifiedModelColors = new short[length];
			for(int index = 0; index < length; index++) {
				originalModelColors[index] = (short) stream.getUShort();
				modifiedModelColors[index] = (short) stream.getUShort();
			}
		}else if (opcode == 41) {
			int length = stream.getUByte();
			textureColour1 = new short[length];
			textureColour2 = new short[length];
			for(int index = 0; index < length; index++) {
				textureColour1[index] = (short) stream.getUShort();
				textureColour2[index] = (short) stream.getUShort();
			}
		}else if (opcode == 42) {
			int length = stream.getUByte();
			unknownArray1 = new byte[length];
			for(int index = 0; index < length; index++)
				unknownArray1[index] = stream.getByte();
		}else if (opcode == 65)
			unnoted = true;
		else if (opcode == 78)
			colourEquip1 = stream.getUShort();
		else if (opcode == 79)
			colourEquip2 = stream.getUShort();
		else if (opcode == 90)
			unknownInt1 = stream.getUShort();
		else if (opcode == 91)
			unknownInt2 = stream.getUShort();
		else if (opcode == 92)
			unknownInt3 = stream.getUShort();
		else if (opcode == 93)
			unknownInt4 = stream.getUShort();
		else if (opcode == 95)
			unknownInt5 = stream.getUShort();
		else if (opcode == 96)
			unknownInt6 = stream.getUShort();
		else if (opcode == 97)
			certId = stream.getUShort();
		else if (opcode == 98)
			certTemplateId = stream.getUShort();
		else if (opcode >= 100 && opcode < 110) {
			if (stackIds == null) {
				stackIds = new int[10];
				stackAmounts = new int[10];
			}
			stackIds[opcode-100] = stream.getUShort();
			stackAmounts[opcode-100] = stream.getUShort();
		}else if (opcode == 110)
			unknownInt7 = stream.getUShort();
		else if (opcode == 111)
			unknownInt8 = stream.getUShort();
		else if (opcode == 112)
			unknownInt9 = stream.getUShort();
		else if (opcode == 113)
			unknownInt10 = stream.getByte();
		else if (opcode == 114)
			unknownInt11 = stream.getByte() * 5;
		else if (opcode == 115)
			teamId = stream.getUByte();
		else if (opcode == 121)
			lendId = stream.getUShort();
		else if (opcode == 122)
			lendTemplateId = stream.getUShort();
		else if (opcode == 125) {
			unknownInt12 = stream.getByte() << 0;
			unknownInt13 = stream.getByte() << 0;
			unknownInt14 = stream.getByte() << 0;
		}else if (opcode == 126) {
			unknownInt15 = stream.getByte() << 0;
			unknownInt16 = stream.getByte() << 0;
			unknownInt17 = stream.getByte() << 0;
		}else if (opcode == 127) {
			unknownInt18 = stream.getUByte();
			unknownInt19 = stream.getUShort();
		}else if (opcode == 128) {
			unknownInt20 = stream.getUByte();
			unknownInt21 = stream.getUShort();
		}else if (opcode == 129) {
			unknownInt20 = stream.getUByte();
			unknownInt21 = stream.getUShort();
		}else if (opcode == 130) {
			unknownInt22 = stream.getUByte();
			unknownInt23 = stream.getUShort();
		}else if (opcode == 132) {
			int length = stream.getUByte();
			unknownArray2 = new int[length];
			for(int index = 0; index < length; index++)
				unknownArray2[index] = stream.getUShort();
		}else if (opcode == 249) {
			int length = stream.getUByte();
			if(clientScriptData == null)
				clientScriptData = new HashMap<Integer, Object>(Methods.getTableSize(length));
			for (int index = 0; index < length; index++) {
				boolean stringInstance = stream.getUByte() == 1;
				int key = stream.getMediumInt();
				Object value = stringInstance ? stream.getString() : stream.getInt();
				clientScriptData.put(key, value);
			}
		}
		else
			throw new IllegalArgumentException("MISSING OPCODE "+opcode+" FOR ITEM "+id);
	}

	private void readOpcodeValues(Stream stream) {
		while (true) {
			int opcode = stream.getUByte();
			if (opcode == 0)
				break;
			readValues(stream, opcode);
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setMaleWornModelId1(int maleWornModelId1) {
		this.maleWornModelId1 = maleWornModelId1;
	}

	public int getMaleWornModelId1() {
		return maleWornModelId1;
	}

	public void setMaleWornModelId2(int maleWornModelId2) {
		this.maleWornModelId2 = maleWornModelId2;
	}

	public int getMaleWornModelId2() {
		return maleWornModelId2;
	}
}
