package org.arios.cache.def.impl;

import java.awt.Graphics;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.arios.cache.Cache;
import org.arios.cache.def.Definition;
import org.arios.cache.misc.ByteBufferUtils;

/**
 * Represents an NPC's definitions.
 * @author Emperor
 *
 */
public final class NPCDefinition extends Definition {
	
	/**
	 * The definitions.
	 */
	private static final Map<Integer, NPCDefinition> DEFINITIONS = new HashMap<>();
	
	/**
	 * The size. 
	 */
	public int size = 1;
	
	/**
	 * The combat level.
	 */
	private int combatLevel;
	
	/**
	 * The head icons.
	 */
	public int headIcons;
	
	/**
	 * If the NPC can be seen on the minimap.
	 */
	public boolean isVisibleOnMap;
	
	/**
	 * The examine option value
	 */
	public String examine;
	
	/**
	 * Unidentified variables.
	 */
	public int anInt833;
	public int anInt836;
	public int anInt837;
	public boolean aBoolean841;
	public int anInt842;
	public int configFileId;
	public int[] childNPCIds;
	public int anInt846;
	public int anInt850;
	public byte aByte851;
	public boolean aBoolean852;
	public int anInt853;
	public byte aByte854;
	public boolean aBoolean856;
	public boolean aBoolean857;
	public short[] aShortArray859;
	public byte[] aByteArray861;
	public short aShort862;
	public boolean aBoolean863;
	public int anInt864;
	public short[] aShortArray866;
	public int[] anIntArray868;
	public int anInt869;
	public int anInt870;
	public int anInt871;
	public int anInt872;
	public int anInt874;
	public int anInt875;
	public int anInt876;
	public int anInt879;
	public short[] aShortArray880;
	public int anInt884;
	public int configId;
	public int anInt889;
	public int[] anIntArray892;
	public short aShort894;
	public short[] aShortArray896;
	public int anInt897;
	public int anInt899;
	public int anInt901;
	public int standAnimation;
	public int walkAnimation;
	
	/**
	 * The minimum combat distance (0 uses default distances).
	 */
	private int combatDistance;
	
	/**
	 * The combat graphics.
	 */
	private Graphics[] combatGraphics = new Graphics[3];

	/**
	 * The turning animation.
	 */
	private int turnAnimation;


	/**
	 * The turn 180� animation.
	 */
	private int turn180Animation;

	/**
	 * The turn clock-wise animation.
	 */
	private int turnCWAnimation;

	/**
	 * The turn counter clock-wise animation.
	 */
	private int turnCCWAnimation;
	
	/**
	 * Constructs a new {@code NPCDefinition} {@code Object}.
	 * @param id The NPC id.
	 */
	public NPCDefinition(int id) {
		this.id = id;
		anInt842 = -1;
		configFileId = -1;
		anInt837 = -1;
		anInt846 = -1;
		anInt853 = 32;
		standAnimation = -1;
		walkAnimation = -1;
		combatLevel = -1;
		anInt836 = -1;
		name = "null";
		anInt869 = 0;
		anInt850 = 255;
		anInt871 = -1;
		aBoolean852 = true;
		aShort862 = (short) 0;
		anInt876 = -1;
		aByte851 = (byte) -96;
		anInt875 = 0;
		anInt872 = -1;
		aBoolean857 = true;
		anInt870 = -1;
		anInt874 = -1;
		anInt833 = -1;
		anInt864 = 128;
		headIcons = -1;
		aBoolean856 = false;
		configId = -1;
		aByte854 = (byte) -16;
		aBoolean863 = false;
		isVisibleOnMap = true;
		anInt889 = -1;
		anInt884 = -1;
		aBoolean841 = true;
		anInt879 = -1;
		anInt899 = 128;
		aShort894 = (short) 0;
		options = new String[5];
		anInt897 = 0;
		anInt901 = -1;
		anIntArray868 = new int[0];
	}
	
	/**
	 * Gets the NPC definition for this id.
	 * @param id The NPC id.
	 * @return The NPC definition object.
	 */
	public static NPCDefinition forId(int id) {
		NPCDefinition def = DEFINITIONS.get(id);
		if (def == null) {
			def = new NPCDefinition(id);
			byte[] data = Cache.getIndexes()[18].getFileData(id >>> 134238215, id & 0x7f);
			if (data == null) { 
				if (id != -1) {
					//System.out.println("Failed loading NPC " + id + ".");
				}
			} else {
				def.parse(ByteBuffer.wrap(data));
			}
			DEFINITIONS.put(id, def);
		}
		return def;
	}
	
	/**
	 * Parses the data.
	 * @param buffer The data input stream.
	 */
    private void parse(ByteBuffer buffer) {
       while (true) {
            int opcode = buffer.get() & 0xFF;
            if (opcode == 0) {
                break;
            }
            parseOpcode(buffer, opcode);
        }
    }
    
    /**
     * Parses an opcode.
     * @param buffer The data input stream.
     * @param opcode The opcode to parse.
     */
	private void parseOpcode(ByteBuffer buffer, int opcode) {
		switch (opcode) {
		case 1:
			int length = buffer.get() & 0xFF;
			anIntArray868 = new int[length];
			for (int i_66_ = 0; i_66_ < length; i_66_++) {
				anIntArray868[i_66_] = buffer.getShort() & 0xFFFF;
				if ((anIntArray868[i_66_] ^ 0xffffffff) == -65536)
					anIntArray868[i_66_] = -1;
			}
			break;
		case 2:
			name = ByteBufferUtils.getString(buffer);
			break;
		case 12:
			size = buffer.get() & 0xFF;
			break;
		case 13:
			standAnimation = buffer.getShort();
			break;
		case 14:
			walkAnimation = buffer.getShort();
			break;
		case 15:
			turnAnimation = buffer.getShort();
			break;
		case 16:
			buffer.getShort(); //Another turn animation
			break;
		case 17:
			walkAnimation = buffer.getShort();
			turn180Animation = buffer.getShort();
			turnCWAnimation = buffer.getShort();
			turnCCWAnimation = buffer.getShort();
			break;
    	case 30:
    	case 31:
    	case 32:
    	case 33:
    	case 34:
            options[opcode - 30] = ByteBufferUtils.getString(buffer);
            break;
    	case 40:
			length = buffer.get() & 0xFF;
			aShortArray859 = new short[length];
			aShortArray896 = new short[length];
			for (int i_65_ = 0; (length ^ 0xffffffff) < (i_65_ ^ 0xffffffff); i_65_++) {
				aShortArray896[i_65_] = (short) (buffer.getShort() & 0xFFFF);
				aShortArray859[i_65_] = (short) (buffer.getShort() & 0xFFFF);
			}
			break;
    	case 41:
			length = buffer.get() & 0xFF;
			aShortArray880 = new short[length];
			aShortArray866 = new short[length];
			for (int i_54_ = 0; (i_54_ ^ 0xffffffff) > (length ^ 0xffffffff); i_54_++) {
				aShortArray880[i_54_] = (short) (buffer.getShort() & 0xFFFF);
				aShortArray866[i_54_] = (short) (buffer.getShort() & 0xFFFF);
			}
			break;
    	case 42:
			length = buffer.get() & 0xFF;
			aByteArray861 = new byte[length];
			for (int i_55_ = 0; length > i_55_; i_55_++) {
				aByteArray861[i_55_] = (byte) buffer.get();
			}
			break;
    	case 60:
    		length = buffer.get() & 0xFF;
			anIntArray892 = new int[length];
			for (int i_64_ = 0; (i_64_ ^ 0xffffffff) > (length ^ 0xffffffff); i_64_++) {
				anIntArray892[i_64_] = buffer.getShort() & 0xFFFF;
			}
			break;
    	case 93:
			isVisibleOnMap = false;
			break;
    	case 95:
			setCombatLevel(buffer.getShort() & 0xFFFF);
			break;
    	case 97:
			anInt864 = buffer.getShort() & 0xFFFF;
			break;
    	case 98:
			anInt899 = buffer.getShort() & 0xFFFF;
			break;
    	case 99:
			aBoolean863 = true;
			break;
    	case 100:
			anInt869 = buffer.get();
			break;
    	case 101:
			anInt897 = buffer.get() * 5;
			break;
    	case 102:
			headIcons = buffer.getShort() & 0xFFFF;
			break;
    	case 103:
			anInt853 = buffer.getShort() & 0xFFFF;
			break;
    	case 106:
    	case 118:
    		configFileId = buffer.getShort() & 0xFFFF;
			if (configFileId == 65535) {
				configFileId = -1;
			}
			configId = buffer.getShort() & 0xFFFF;
			if (configId == 65535) {
				configId = -1;
			}
			int defaultValue = -1;
			if ((opcode ^ 0xffffffff) == -119) {
				defaultValue = buffer.getShort() & 0xFFFF;
				if (defaultValue == 65535) {
					defaultValue = -1;
				}
			}
			length = buffer.get() & 0xFF;
			childNPCIds = new int[2 + length];
			for (int i = 0; length >= i; i++) {
				childNPCIds[i] = buffer.getShort() & 0xFFFF;
				if (childNPCIds[i] == 65535) {
					childNPCIds[i] = -1;
				}
			}
			childNPCIds[length + 1] = defaultValue;
			break;
    	case 107:
			aBoolean841 = false;
			break;
    	case 109:
			aBoolean852 = false;
			break;
    	case 111:
			aBoolean857 = false;
			break;
    	case 113:
			aShort862 = (short) (buffer.getShort() & 0xFFFF);
			aShort894 = (short) (buffer.getShort() & 0xFFFF);
			break;
    	case 114:
			aByte851 = (byte) (buffer.get());
			aByte854 = (byte) (buffer.get());
			break;
    	case 115:
			buffer.get();// & 0xFF;
			buffer.get();// & 0xFF;
			break;
		default:
			System.out.println("Unhandled NPC definition opcode: " + opcode);
		}
		if (id == 3074) {
			setName("Monk");
			options[0] = "Talk-to";
		}
		if (id == 2020) {
			setName("Vanstrom Klause");
		}
		if (id == 381) {
			setName("Captain Barnaby");
			options[0] = "Talk-to";
			options[2] = "Pay-fare";
		}
		if (id == 3820) {
			setName("Wise Old Man");
			options[0] = "Talk-to";
		}
		if (id == 4495) {
			setName("Grubfoot");
			options[0] = "Talk-to";
		}
		if (id == 7707) {
			setName("Drezel");
		}
	}
	
	/**
	 * Checks if this NPC has an attack option.
	 * @return {@code True} if so.
	 */
	public boolean hasAttackOption() {
		for (String option : options) {
			if (option != null && option.equalsIgnoreCase("attack")) {
				return true;
			}
		}
		return false;
	}
	
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
	 * Gets the definitions mapping.
	 * @return The mapping.
	 */
	public static final Map<Integer, NPCDefinition> getDefinitions() {
		return DEFINITIONS;
	}

	/**
	 * Method sets the value for 'examine'
	 * @param examine the examine to set.
	 */
	public final void setExamine(String examine) {
		this.examine = examine;
	}

	/**
	 * Gets the size.
	 * @return The size.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Gets the headIcons.
	 * @return The headIcons.
	 */
	public int getHeadIcons() {
		return headIcons;
	}

	/**
	 * Gets the isVisibleOnMap.
	 * @return The isVisibleOnMap.
	 */
	public boolean isVisibleOnMap() {
		return isVisibleOnMap;
	}

	/**
	 * Gets the anInt833.
	 * @return The anInt833.
	 */
	public int getAnInt833() {
		return anInt833;
	}

	/**
	 * Gets the anInt836.
	 * @return The anInt836.
	 */
	public int getAnInt836() {
		return anInt836;
	}

	/**
	 * Gets the anInt837.
	 * @return The anInt837.
	 */
	public int getAnInt837() {
		return anInt837;
	}

	/**
	 * Gets the aBoolean841.
	 * @return The aBoolean841.
	 */
	public boolean isaBoolean841() {
		return aBoolean841;
	}

	/**
	 * Gets the anInt842.
	 * @return The anInt842.
	 */
	public int getAnInt842() {
		return anInt842;
	}

	/**
	 * Gets the configFileId.
	 * @return The configFileId.
	 */
	public int getConfigFileId() {
		return configFileId;
	}

	/**
	 * Gets the childNPCIds.
	 * @return The childNPCIds.
	 */
	public int[] getChildNPCIds() {
		return childNPCIds;
	}

	/**
	 * Gets the anInt846.
	 * @return The anInt846.
	 */
	public int getAnInt846() {
		return anInt846;
	}

	/**
	 * Gets the anInt850.
	 * @return The anInt850.
	 */
	public int getAnInt850() {
		return anInt850;
	}

	/**
	 * Gets the aByte851.
	 * @return The aByte851.
	 */
	public byte getaByte851() {
		return aByte851;
	}

	/**
	 * Gets the aBoolean852.
	 * @return The aBoolean852.
	 */
	public boolean isaBoolean852() {
		return aBoolean852;
	}

	/**
	 * Gets the anInt853.
	 * @return The anInt853.
	 */
	public int getAnInt853() {
		return anInt853;
	}

	/**
	 * Gets the aByte854.
	 * @return The aByte854.
	 */
	public byte getaByte854() {
		return aByte854;
	}

	/**
	 * Gets the aBoolean856.
	 * @return The aBoolean856.
	 */
	public boolean isaBoolean856() {
		return aBoolean856;
	}

	/**
	 * Gets the aBoolean857.
	 * @return The aBoolean857.
	 */
	public boolean isaBoolean857() {
		return aBoolean857;
	}

	/**
	 * Gets the aShortArray859.
	 * @return The aShortArray859.
	 */
	public short[] getaShortArray859() {
		return aShortArray859;
	}

	/**
	 * Gets the aByteArray861.
	 * @return The aByteArray861.
	 */
	public byte[] getaByteArray861() {
		return aByteArray861;
	}

	/**
	 * Gets the aShort862.
	 * @return The aShort862.
	 */
	public short getaShort862() {
		return aShort862;
	}

	/**
	 * Gets the aBoolean863.
	 * @return The aBoolean863.
	 */
	public boolean isaBoolean863() {
		return aBoolean863;
	}

	/**
	 * Gets the anInt864.
	 * @return The anInt864.
	 */
	public int getAnInt864() {
		return anInt864;
	}

	/**
	 * Gets the aShortArray866.
	 * @return The aShortArray866.
	 */
	public short[] getaShortArray866() {
		return aShortArray866;
	}

	/**
	 * Gets the anIntArray868.
	 * @return The anIntArray868.
	 */
	public int[] getAnIntArray868() {
		return anIntArray868;
	}

	/**
	 * Gets the anInt869.
	 * @return The anInt869.
	 */
	public int getAnInt869() {
		return anInt869;
	}

	/**
	 * Gets the anInt870.
	 * @return The anInt870.
	 */
	public int getAnInt870() {
		return anInt870;
	}

	/**
	 * Gets the anInt871.
	 * @return The anInt871.
	 */
	public int getAnInt871() {
		return anInt871;
	}

	/**
	 * Gets the anInt872.
	 * @return The anInt872.
	 */
	public int getAnInt872() {
		return anInt872;
	}

	/**
	 * Gets the anInt874.
	 * @return The anInt874.
	 */
	public int getAnInt874() {
		return anInt874;
	}

	/**
	 * Gets the anInt875.
	 * @return The anInt875.
	 */
	public int getAnInt875() {
		return anInt875;
	}

	/**
	 * Gets the anInt876.
	 * @return The anInt876.
	 */
	public int getAnInt876() {
		return anInt876;
	}

	/**
	 * Gets the anInt879.
	 * @return The anInt879.
	 */
	public int getAnInt879() {
		return anInt879;
	}

	/**
	 * Gets the aShortArray880.
	 * @return The aShortArray880.
	 */
	public short[] getaShortArray880() {
		return aShortArray880;
	}

	/**
	 * Gets the anInt884.
	 * @return The anInt884.
	 */
	public int getAnInt884() {
		return anInt884;
	}

	/**
	 * Gets the configId.
	 * @return The configId.
	 */
	public int getConfigId() {
		return configId;
	}

	/**
	 * Gets the anInt889.
	 * @return The anInt889.
	 */
	public int getAnInt889() {
		return anInt889;
	}

	/**
	 * Gets the anIntArray892.
	 * @return The anIntArray892.
	 */
	public int[] getAnIntArray892() {
		return anIntArray892;
	}

	/**
	 * Gets the aShort894.
	 * @return The aShort894.
	 */
	public short getaShort894() {
		return aShort894;
	}

	/**
	 * Gets the aShortArray896.
	 * @return The aShortArray896.
	 */
	public short[] getaShortArray896() {
		return aShortArray896;
	}

	/**
	 * Gets the anInt897.
	 * @return The anInt897.
	 */
	public int getAnInt897() {
		return anInt897;
	}

	/**
	 * Gets the anInt899.
	 * @return The anInt899.
	 */
	public int getAnInt899() {
		return anInt899;
	}

	/**
	 * Gets the anInt901.
	 * @return The anInt901.
	 */
	public int getAnInt901() {
		return anInt901;
	}

	/**
	 * Gets the standAnimation.
	 * @return The standAnimation.
	 */
	public int getStandAnimation() {
		return standAnimation;
	}

	/**
	 * Gets the walkAnimation.
	 * @return The walkAnimation.
	 */
	public int getWalkAnimation() {
		return walkAnimation;
	}

	/**
	 * Gets the turnAnimation.
	 * @return The turnAnimation.
	 */
	public int getTurnAnimation() {
		return turnAnimation;
	}

	/**
	 * Gets the turn180Animation.
	 * @return The turn180Animation.
	 */
	public int getTurn180Animation() {
		return turn180Animation;
	}

	/**
	 * Gets the turnCWAnimation.
	 * @return The turnCWAnimation.
	 */
	public int getTurnCWAnimation() {
		return turnCWAnimation;
	}

	/**
	 * Gets the turnCCWAnimation.
	 * @return The turnCCWAnimation.
	 */
	public int getTurnCCWAnimation() {
		return turnCCWAnimation;
	}

	/**
	 * Gets the combatLevel.
	 * @return The combatLevel.
	 */
	public int getCombatLevel() {
		return combatLevel;
	}

	/**
	 * Sets the combatLevel.
	 * @param combatLevel The combatLevel to set.
	 */
	public void setCombatLevel(int combatLevel) {
		this.combatLevel = combatLevel;
	}

	/**
	 * Gets the combatDistance.
	 * @return The combatDistance.
	 */
	public int getCombatDistance() {
		return combatDistance;
	}

	/**
	 * Sets the combatDistance.
	 * @param combatDistance The combatDistance to set.
	 */
	public void setCombatDistance(int combatDistance) {
		this.combatDistance = combatDistance;
	}

	/**
	 * Gets the combatGraphics.
	 * @return The combatGraphics.
	 */
	public Graphics[] getCombatGraphics() {
		return combatGraphics;
	}

	/**
	 * Sets the combatGraphics.
	 * @param combatGraphics The combatGraphics to set.
	 */
	public void setCombatGraphics(Graphics[] combatGraphics) {
		this.combatGraphics = combatGraphics;
	}
	
}