package core.cache.def.impl;

import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import core.game.system.config.ItemConfigParser;
import core.plugin.Plugin;
import core.tools.StringUtils;
import core.cache.Cache;
import core.cache.def.Definition;
import core.cache.misc.buffer.ByteBufferUtils;
import core.game.container.Container;
import plugin.ge.GrandExchangeDatabase;
import core.game.content.global.action.DropItemHandler;
import core.game.content.global.action.EquipHandler;
import plugin.skill.Skills;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.node.item.ItemPlugin;
import core.net.packet.PacketRepository;
import core.net.packet.out.WeightUpdate;

/**
 * Represents an item's definitions.
 * @author Jagex
 * @author Emperor
 */
public class ItemDefinition extends Definition<Item> {

	/**
	 * The item definitions mapping.
	 */
	private static final Map<Integer, ItemDefinition> DEFINITIONS = new HashMap<>();

	/**
	 * The default option handlers.
	 */
	private static final Map<String, OptionHandler> OPTION_HANDLERS = new HashMap<>();

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
	private int modelRotationY;

	/**
	 * The model rotation.
	 */
	private int modelRotationX;

	/**
	 * The model offset.
	 */
	private int modelOffsetX;

	/**
	 * The model offset.
	 */
	private int modelOffsetY;

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
	private int femaleWornModelId1 = -1;

	/**
	 * The male model wear id.
	 */
	private int maleWornModelId2 = -1;

	/**
	 * The female wear model id.
	 */
	private int femaleWornModelId2 = -1;

	/**
	 * The male model wear id.
	 */
	private int maleWornModelId3 = -1;

	/**
	 * The female model wear id.
	 */
	private int femaleWornModelId3 = -1;

	/**
	 * The male model wear id.
	 */
	private int maleWornModelId4 = -1;

	/**
	 * The female wear model id.
	 */
	private int femaleWornModelId4 = -1;

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
	 * If the item is NOT noted.
	 */
	private boolean unnoted = true;

	/**
	 * The colour equipment.
	 */
	private int colourEquip1 = -1;

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
	 * The item type.
	 */
	private int itemType;

	/**
	 * Constructs a new {@code ItemDefinition} {@code Object}.
	 */
	public ItemDefinition() {
		groundActions = new String[] { null, null, "take", null, null };
		options = new String[] { null, null, null, null, "drop" };
	}

	/**
	 * Initialize the default option handlers.
	 */
	static {
		// TODO: Move this crap in a plugin.
		OptionHandler handler = new OptionHandler() {
			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				return this;
			}

			@Override
			public boolean handle(final Player player, Node node, String option) {
				return DropItemHandler.handle(player, node, option);
			}

			@Override
			public boolean isWalk() {
				return false;
			}
		};
		setOptionHandler("destroy", handler);
		setOptionHandler("dissolve", handler);
		setOptionHandler("drop", handler);
		final EquipHandler equipHandler = EquipHandler.SINGLETON;
		setOptionHandler("wear", equipHandler);
		setOptionHandler("wield", equipHandler);
		setOptionHandler("equip", equipHandler);
	}
	
	/**
	 * Parses the item definitions.
	 */
	public static void parse() {
		for (int itemId = 0; itemId < Cache.getItemDefinitionsSize(); itemId++) {
			byte[] data = Cache.getIndexes()[19].getFileData(itemId >>> 8, itemId & 0xFF);
			if (data == null) {
				ItemDefinition.getDefinitions().put(itemId, new ItemDefinition());
				continue;
			}
			ItemDefinition def = ItemDefinition.parseDefinition(itemId, ByteBuffer.wrap(data));
			if (def == null) {
				System.err.println("Could not load item definitions for id " + itemId + " - no definitions found!");
				return ;
			}
			if(itemId == 14958)
				def.setStackable(true);
			
			ItemDefinition.getDefinitions().put(itemId, def);
		}
		ItemDefinition.defineTemplates();
	}

	/**
	 * Gets an item definition.
	 * @param itemId The item's id.
	 * @return The item definition.
	 */
	public static ItemDefinition forId(int itemId) {
		ItemDefinition def = DEFINITIONS.get(itemId);
		if (def != null) {
			return def;
		}
		return new ItemDefinition();
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
				def.handlers.put("examine", ByteBufferUtils.getString(buffer)); // Examine
																						// info.
			} else if (opcode == 4) {
				def.modelZoom = buffer.getShort() & 0xFFFF;
			} else if (opcode == 5) {
				def.modelRotationY = buffer.getShort() & 0xFFFF;
			} else if (opcode == 6) {
				def.modelRotationX = buffer.getShort() & 0xFFFF;
			} else if (opcode == 7) {
				def.modelOffsetX = buffer.getShort() & 0xFFFF;
				if (def.modelOffsetX > 32767)
					def.modelOffsetX -= 65536;
			} else if (opcode == 8) {
				def.modelOffsetY = buffer.getShort() & 0xFFFF;
				if (def.modelOffsetY > 32767) {
					def.modelOffsetY -= 65536;
				}
			} else if (opcode == 10) {
				// buffer.getShort(); //10 is unused opcode.
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
				// buffer.get();
			} else if (opcode == 24) {
				def.femaleWornModelId1 = buffer.getShort() & 0xFFFF;
			} else if (opcode == 25) {
				def.maleWornModelId2 = buffer.getShort() & 0xFFFF;
				// buffer.get();
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
				def.setMaleWornModelId3(buffer.getShort());
			} else if (opcode == 91) {
				def.setFemaleWornModelId3(buffer.getShort());
			} else if (opcode == 92) {
				def.setMaleWornModelId4(buffer.getShort());
			} else if (opcode == 93) {
				def.setFemaleWornModelId4(buffer.getShort());
			} else if (opcode == 95) {
				buffer.getShort();
			} else if (opcode == 96) {
				def.itemType = buffer.get();
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
				}
			} else {
				System.out.println("Unhandled item definition opcode - opcode: " + opcode);
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
		for (int i = 0; i < Cache.getItemDefinitionsSize(); i++) {
			ItemDefinition def = forId(i);
			if (def.noteTemplateId != -1) {
				def.transferNoteDefinition(forId(def.noteId), forId(def.noteTemplateId));
			}
			if (def.lendTemplateId != -1) {
				def.transferLendDefinition(forId(def.lendId), forId(def.lendTemplateId));
			}
			if (def.recolourTemplateId != -1) {
				def.transferRecolourDefinition(forId(def.recolourId), forId(def.recolourTemplateId));
			}
			if (def != null && (def.maleWornModelId1 >= 0 || def.maleWornModelId2 >= 0)) {
				def.equipId = equipId++;
			}
		}
		forId(2513).equipId = forId(3140).equipId;
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
		modelOffsetY = templateReference.modelOffsetY;
		textureColour1 = templateReference.textureColour1;
		value = reference.value;
		modelRotationX = templateReference.modelRotationX;
		stackable = true;
		unnoted = false;
		modifiedModelColors = templateReference.modifiedModelColors;
		modelRotationY = templateReference.modelRotationY;
		modelZoom = templateReference.modelZoom;
		textureColour1 = templateReference.textureColour1;
		handlers.put(ItemConfigParser.TRADEABLE, true);
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
		modelRotationY = templateReference.modelRotationY;
		modelRotationX = templateReference.modelRotationX;
		originalModelColors = reference.originalModelColors;
		name = reference.name;
		maleWornModelId1 = reference.maleWornModelId1;
		colourEquip1 = reference.colourEquip1;
		teamId = reference.teamId;
		modelOffsetY = templateReference.modelOffsetY;
		clientScriptData = reference.clientScriptData;
		modifiedModelColors = reference.modifiedModelColors;
		colourEquip2 = reference.colourEquip2;
		modelOffsetX = templateReference.modelOffsetX;
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
		modelRotationX = templateReference.modelRotationX;
		name = reference.name;
		maleWornModelId1 = reference.maleWornModelId1;
		modelOffsetY = templateReference.modelOffsetY;
		femaleWornModelId1 = reference.femaleWornModelId1;
		maleWornModelId2 = reference.maleWornModelId2;
		modelOffsetX = templateReference.modelOffsetX;
		unknownArray1 = reference.unknownArray1;
		stackable = reference.stackable;
		modelRotationY = templateReference.modelRotationY;
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
	 * Checks if the player has the needed requirements to use this item.
	 * @param player The player.
	 * @param wield If requirements are checked for wearing the item.
	 * @param message If a message should be sent when the player does not meet
	 * a requirement.
	 * @return {@code True} if so.
	 */
	public boolean hasRequirement(Player player, boolean wield, boolean message) {
		Map<Integer, Integer> requirements = getConfiguration(ItemConfigParser.REQUIREMENTS);
		if (requirements == null) {
			return true;
		}
		for (int skill : requirements.keySet()) {
			if (wield) {
				if (skill > Skills.MAGIC && skill != Skills.SLAYER && skill != Skills.HUNTER) {
					continue;
				}
			} else if (skill <= Skills.MAGIC && skill != Skills.SLAYER && skill != Skills.HUNTER) {
				continue;
			}
			if (skill < 0 || skill >= Skills.SKILL_NAME.length) {
				continue;
			}
			int level = requirements.get(skill);
			if (player.getSkills().getStaticLevel(skill) < level) {
				if (message) {
					String name = Skills.SKILL_NAME[skill];
					player.getPacketDispatch().sendMessage("You need a" + (StringUtils.isPlusN(name) ? "n " : " ") + name + " level of " + level + " to " + (wield ? "wear " : "use ") + "this.");
				}
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the player can enter entrana.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public static boolean canEnterEntrana(Player player) {
		Container[] container = new Container[] { player.getInventory(), player.getEquipment() };
		for (Container c : container) {
			for (Item i : c.toArray()) {
				if (i == null) {
					continue;
				}
				if (!i.getDefinition().isAllowedOnEntrana()) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * The allowed names.
	 */
	private static final String[] allowedNames = new String[] { "cape", "robe", "hat", "potion" };

	/**
	 * Checks if the item is allowed on entrana.
	 * @return {@code True} if so.
	 */
	public boolean isAllowedOnEntrana() {
		if (getId() == 946) {
			return true;
		}
		if (getName().equals("Boots")) {
			return true;
		}
		if (getName().toLowerCase().startsWith("ring") || getName().toLowerCase().startsWith("amulet")) {
			return true;
		}
		if (getName().toLowerCase().contains("spotted") || getName().toLowerCase().equals("spottier")) {
			return true;
		}
		if (getName().equals("Boots of lightness")) {
			return true;
		}
		for (String name : allowedNames) {
			if (getName().toLowerCase().contains(name)) {
				return true;
			}
		}
		return getConfiguration(ItemConfigParser.BONUS) == null;
	}

	/**
	 * Gets the level requirement for this item.
	 * @param skillId The skill id.
	 * @return The level required.
	 */
	public int getRequirement(int skillId) {
		Map<Integer, Integer> requirements = getConfiguration(ItemConfigParser.REQUIREMENTS);
		if (requirements == null) {
			return 1;
		}
		Integer level = requirements.get(skillId);
		return level == null ? 1 : level;
	}
	
	/**
	 * Gets the wielding animation id (render animation id).
	 * @return The wield animation id.
	 */
	public int getRenderAnimationId() {
		return getConfiguration(ItemConfigParser.RENDER_ANIM_ID, 1426);
	}

	@Override
	public int getId() {
		return id;
	}

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
	 * Checks if the item type is for player usage.
	 * @return {@code True}.
	 */
	public boolean isPlayerType() {
		return itemType == 0;
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
		return modelRotationY;
	}

	/**
	 * Sets the modelRotation1.
	 * @param modelRotation1 The modelRotation1 to set.
	 */
	public void setModelRotation1(int modelRotation1) {
		this.modelRotationY = modelRotation1;
	}

	/**
	 * Gets the modelRotation2.
	 * @return The modelRotation2.
	 */
	public int getModelRotation2() {
		return modelRotationX;
	}

	/**
	 * Sets the modelRotation2.
	 * @param modelRotation2 The modelRotation2 to set.
	 */
	public void setModelRotation2(int modelRotation2) {
		this.modelRotationX = modelRotation2;
	}

	/**
	 * Gets the modelOffset1.
	 * @return The modelOffset1.
	 */
	public int getModelOffset1() {
		return modelOffsetX;
	}

	/**
	 * Sets the modelOffset1.
	 * @param modelOffset1 The modelOffset1 to set.
	 */
	public void setModelOffset1(int modelOffset1) {
		this.modelOffsetX = modelOffset1;
	}

	/**
	 * Gets the modelOffset2.
	 * @return The modelOffset2.
	 */
	public int getModelOffset2() {
		return modelOffsetY;
	}

	/**
	 * Sets the modelOffset2.
	 * @param modelOffset2 The modelOffset2 to set.
	 */
	public void setModelOffset2(int modelOffset2) {
		this.modelOffsetY = modelOffset2;
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
	 * @return The value.
	 */
	public int getMaxValue() {
		if ((int) (value * 1.05) <= 0) {
			return 1;
		}
		return (int) (value * 1.05);
	}

	/**
	 * @return The value.
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
	 * Gets the alchemy value.
	 * @param highAlchemy If the value is for high alchemy (instead of low).
	 * @return The alchemy value.
	 */
	public int getAlchemyValue(boolean highAlchemy) {
		if (!unnoted && noteId > -1) {
			return forId(noteId).getAlchemyValue(highAlchemy);
		}
		if (highAlchemy) {
			return getConfiguration(ItemConfigParser.HIGH_ALCHEMY, 0);
		}
		return getConfiguration(ItemConfigParser.LOW_ALCHEMY, 0);
	}

	/**
	 * Checks if the item is tradeable.
	 * @return {@code True} if so.
	 */
	public boolean isTradeable() {
		if (hasDestroyAction() && !getName().contains("impling jar")) {
			return false;
		}
		return !unnoted || getConfiguration(ItemConfigParser.TRADEABLE, false) || GrandExchangeDatabase.getDatabase().get(getId()) != null;
	}

	/**
	 * If the item has the specified item.
	 * @param optionName The reward.
	 * @return If the item has the specified reward {@code true}.
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
	 * If the item has the destroy reward.
	 * @return If the item has the destroy reward {@code true}.
	 */
	public boolean hasDestroyAction() {
		return hasAction("destroy") || hasAction("dissolve");
	}

	/**
	 * If the item has the wear reward.
	 * @return If the item has the wear reward {@code true}.
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
	 * Gets the option handler for the given option name.
	 * @param nodeId
	 * @param name The name.
	 * @return The option handler, or {@code null} if there was no default
	 * option handler.
	 */
	public static OptionHandler getOptionHandler(int nodeId, String name) {
		ItemDefinition def = forId(nodeId);
		if (def == null) {
			if (nodeId == 22937)
				System.err.println("[ItemDefinition] No definition for item id " + nodeId + "!");
			return null;
		}
		OptionHandler handler = def.getConfiguration("option:" + name);
		if (handler != null) {
			return handler;
		}
		return OPTION_HANDLERS.get(name);
	}

	/**
	 * The bonus names.
	 */
	private static final String[] BONUS_NAMES = { "Stab: ", "Slash: ", "Crush: ", "Magic: ", "Ranged: ", "Stab: ", "Slash: ", "Crush: ", "Magic: ", "Ranged: ", "Summoning: ", "Strength: ", "Prayer: " };

	/**
	 * Updates the equipment stats interface.
	 * @param player The player to update for.
	 */
	public static void statsUpdate(Player player) {
		if (!player.getAttribute("equip_stats_open", false)) {
			return;
		}
		PacketRepository.send(WeightUpdate.class, player.getPacketDispatch().getContext());
		int index = 0;
		int[] bonuses = player.getProperties().getBonuses();
		for (int i = 36; i < 50; i++) {
			if (i == 47) {
				continue;
			}
			int bonus = bonuses[index];
			String bonusValue = bonus > -1 ? ("+" + bonus) : Integer.toString(bonus);
			player.getPacketDispatch().sendString(BONUS_NAMES[index++] + bonusValue, 667, i);
		}
		player.getPacketDispatch().sendString("Attack bonus", 667, 34);
		DecimalFormat dec = new DecimalFormat("#.#"); 
		player.getPacketDispatch().sendString(dec.format(player.getSettings().getWeight())+" kg", 667, 32);
	}

	/**
	 * Checks if it has a plugin.
	 * @return {@code True} if so.
	 */
	public boolean hasPlugin() {
		return getItemPlugin() != null;
	}

	/**
	 * Gets the item plugin.
	 * @return the plugin.
	 */
	public ItemPlugin getItemPlugin() {
		return getConfiguration("wrapper", null);
	}

	/**
	 * Sets the item plugin.
	 * @param plugin the plugin.
	 */
	public void setItemPlugin(ItemPlugin plugin) {
		getHandlers().put("wrapper", plugin);
	}

	/**
	 * Sets the default option handler for an option.
	 * @param name The option name.
	 * @param handler The default option handler.
	 * @return {@code True} if there was a previous default handler mapped.
	 */
	public static boolean setOptionHandler(String name, OptionHandler handler) {
		return OPTION_HANDLERS.put(name, handler) != null;
	}

	/**
	 * @return the optionHandlers
	 */
	public static Map<String, OptionHandler> getOptionHandlers() {
		return OPTION_HANDLERS;
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

	/**
	 * Gets the itemType.
	 * @return The itemType.
	 */
	public int getItemType() {
		return itemType;
	}

	/**
	 * Sets the itemType.
	 * @param itemType The itemType to set.
	 */
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}
	
	/**
	 * Gets the femaleWornModelId3 value.
	 * @return The femaleWornModelId3.
	 */
	public int getFemaleWornModelId3() {
		return femaleWornModelId3;
	}
	/**
	 * Sets the femaleWornModelId3 value.
	 * @param femaleWornModelId3 The femaleWornModelId3 to set.
	 */
	public void setFemaleWornModelId3(int femaleWornModelId3) {
		this.femaleWornModelId3 = femaleWornModelId3;
	}
	/**
	 * Gets the femaleWornModelId4 value.
	 * @return The femaleWornModelId4.
	 */
	public int getFemaleWornModelId4() {
		return femaleWornModelId4;
	}
	/**
	 * Sets the femaleWornModelId4 value.
	 * @param femaleWornModelId4 The femaleWornModelId4 to set.
	 */
	public void setFemaleWornModelId4(int femaleWornModelId4) {
		this.femaleWornModelId4 = femaleWornModelId4;
	}
	/**
	 * Gets the maleWornModelId3 value.
	 * @return The maleWornModelId3.
	 */
	public int getMaleWornModelId3() {
		return maleWornModelId3;
	}
	/**
	 * Sets the maleWornModelId3 value.
	 * @param maleWornModelId3 The maleWornModelId3 to set.
	 */
	public void setMaleWornModelId3(int maleWornModelId3) {
		this.maleWornModelId3 = maleWornModelId3;
	}
	/**
	 * Gets the maleWornModelId4 value.
	 * @return The maleWornModelId4.
	 */
	public int getMaleWornModelId4() {
		return maleWornModelId4;
	}
	/**
	 * Sets the maleWornModelId4 value.
	 * @param maleWornModelId4 The maleWornModelId4 to set.
	 */
	public void setMaleWornModelId4(int maleWornModelId4) {
		this.maleWornModelId4 = maleWornModelId4;
	}
}