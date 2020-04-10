package org.arios.cache.def.impl;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.arios.cache.def.Definition;
import org.arios.cache.misc.ByteBufferUtils;

/**
 * Represents an object's definition.
 * @author Emperor
 */
public class ObjectDefinition extends Definition {

	/**
	 * The item definitions mapping.
	 */
	private static final Map<Integer, ObjectDefinition> DEFINITIONS = new HashMap<Integer, ObjectDefinition>();

	/**
	 * The original model colors.
	 */
	private short[] originalColors;

	/**
	 * The children ids.
	 */
	public int[] childrenIds;

	/**
	 * The model ids.
	 */
	private int[] modelIds;

	/**
	 * The model configuration.
	 */
	private int[] modelConfiguration;

	/**
	 * A unknown integer.
	 */
	static int anInt3832;

	/**
	 * A unkown integer array.
	 */
	int[] anIntArray3833 = null;

	/**
	 * A unknown integer.
	 */
	private int anInt3834;

	/**
	 * A unknown integer.
	 */
	int anInt3835;

	/**
	 * A unknown integer.
	 */
	static int anInt3836;

	/**
	 * A unknown byte.
	 */
	private byte aByte3837;

	/**
	 * A unknown integer.
	 */
	int anInt3838 = -1;

	/**
	 * A unknown boolean.
	 */
	boolean aBoolean3839;

	/**
	 * A unknown integer.
	 */
	private int anInt3840;

	/**
	 * A unknown integer.
	 */
	private int anInt3841;

	/**
	 * A unknown integer.
	 */
	static int anInt3842;

	/**
	 * A unknown integer.
	 */
	static int anInt3843;

	/**
	 * A unknown integer.
	 */
	int anInt3844;

	/**
	 * A unknown boolean.
	 */
	boolean aBoolean3845;

	/**
	 * A unknown integer.
	 */
	static int anInt3846;

	/**
	 * A unknown byte.
	 */
	private byte aByte3847;

	/**
	 * A unknown byte.
	 */
	private byte aByte3849;

	/**
	 * A unknown integer.
	 */
	int anInt3850;

	/**
	 * A unknown integer.
	 */
	int anInt3851;

	/**
	 * The second boolean.
	 */
	public boolean secondBool;

	/**
	 * A unknown boolean.
	 */
	public boolean aBoolean3853;

	/**
	 * A unknown integer.
	 */
	int anInt3855;

	/**
	 * The first boolean.
	 */
	public boolean notClipped;

	/**
	 * A unknown integer.
	 */
	int anInt3857;

	/**
	 * A unknown byte array.
	 */
	private byte[] aByteArray3858;

	/**
	 * A unknown integer array.
	 */
	int[] anIntArray3859;

	/**
	 * A unknown integer.
	 */
	int anInt3860;

	/**
	 * The config file id.
	 */
	int configFileId;

	/**
	 * The modified colors.
	 */
	private short[] modifiedColors;

	/**
	 * A unknown integer.
	 */
	int anInt3865;

	/**
	 * A unknown boolean.
	 */
	boolean aBoolean3866;

	/**
	 * A unknown boolean.
	 */
	boolean aBoolean3867;

	/**
	 * The solid.
	 */
	public boolean projectileClipped;

	/**
	 * A unknown integer array.
	 */
	private int[] anIntArray3869;

	/**
	 * A unknown boolean.
	 */
	boolean aBoolean3870;

	/**
	 * The y-size.
	 */
	public int sizeY;

	/**
	 * A unknown boolean.
	 */
	boolean aBoolean3872;

	/**
	 * A unknown boolean.
	 */
	boolean membersOnly;

	/**
	 * The third integer.
	 */
	public boolean boolean1;

	/**
	 * A unknown integer.
	 */
	private int anInt3875;

	/**
	 * The add object check.
	 */
	public int addObjectCheck;

	/**
	 * A unknown integer.
	 */
	private int anInt3877;

	/**
	 * A unknown integer.
	 */
	private int anInt3878;

	/**
	 * The clipping type.
	 */
	public int clipType;

	/**
	 * A unknown integer.
	 */
	private int anInt3881;

	/**
	 * A unknown integer.
	 */
	private int anInt3882;

	/**
	 * A unknown integer.
	 */
	private int anInt3883;

	/**
	 * The loader.
	 */
	Object loader;

	/**
	 * A unknown integer.
	 */
	private int anInt3889;

	/**
	 * The x-size.
	 */
	public int sizeX;

	/**
	 * A unknown boolean.
	 */
	public boolean aBoolean3891;

	/**
	 * A unknown integer.
	 */
	int anInt3892;

	/**
	 * The second integer.
	 */
	public int secondInt;

	/**
	 * A unknown boolean.
	 */
	boolean aBoolean3894;

	/**
	 * A unknown boolean.
	 */
	boolean aBoolean3895;

	/**
	 * A unknown integer.
	 */
	int anInt3896;

	/**
	 * The configuration id.
	 */
	int configId;

	/**
	 * A unknown byte array.
	 */
	private byte[] aByteArray3899;

	/**
	 * A unknown integer.
	 */
	int anInt3900;

	/**
	 * A unknown integer.
	 */
	private int anInt3902;

	/**
	 * A unknown integer.
	 */
	int anInt3904;

	/**
	 * A unknown integer.
	 */
	int anInt3905;

	/**
	 * A unknown boolean.
	 */
	boolean aBoolean3906;

	/**
	 * A unknown integer array.
	 */
	int[] anIntArray3908;

	/**
	 * A unknown byte.
	 */
	private byte aByte3912;

	/**
	 * A unknown integer.
	 */
	int anInt3913;

	/**
	 * A unknown byte.
	 */
	private byte aByte3914;

	/**
	 * A unknown integer.
	 */
	private int anInt3915;

	/**
	 * A unknown integer array.
	 */
	private int[][] anIntArrayArray3916;

	/**
	 * A unknown integer.
	 */
	private int anInt3917;

	/**
	 * A unknown short array.
	 */
	private short[] aShortArray3919;

	/**
	 * A unknown short array.
	 */
	private short[] aShortArray3920;

	/**
	 * A unknown integer.
	 */
	int anInt3921;

	/**
	 * A unknown object.
	 */
	private Object aClass194_3922;

	/**
	 * A unknown integer.
	 */
	boolean aBoolean3923;

	/**
	 * A unknown integer.
	 */
	boolean aBoolean3924;

	/**
	 * The walking flag.
	 */
	int walkingFlag;

	/**
	 * If the object has hidden options.
	 */
	private boolean hasHiddenOptions;

	/**
	 * Construct a new {@code ObjectDefinition} {@code Object}.
	 */
	public ObjectDefinition() {
		anInt3835 = -1;
		anInt3860 = -1;
		configFileId = -1;
		aBoolean3866 = false;
		anInt3851 = -1;
		anInt3865 = 255;
		aBoolean3845 = false;
		aBoolean3867 = false;
		anInt3850 = 0;
		anInt3844 = -1;
		anInt3881 = 0;
		anInt3857 = -1;
		aBoolean3872 = true;
		anInt3882 = -1;
		anInt3834 = 0;
		options = new String[5];
		anInt3875 = 0;
		aBoolean3839 = false;
		anIntArray3869 = null;
		sizeY = 1;
		boolean1 = false;
		projectileClipped = true;
		anInt3883 = 0;
		aBoolean3895 = true;
		anInt3840 = 0;
		aBoolean3870 = false;
		anInt3889 = 0;
		aBoolean3853 = true;
		secondBool = false;
		clipType = 2;
		anInt3855 = -1;
		anInt3878 = 0;
		anInt3904 = 0;
		sizeX = 1;
		addObjectCheck = -1;
		notClipped = false;
		aBoolean3891 = false;
		anInt3905 = 0;
		name = "null";
		anInt3913 = -1;
		aBoolean3906 = false;
		membersOnly = false;
		aByte3914 = (byte) 0;
		anInt3915 = 0;
		anInt3900 = 0;
		secondInt = -1;
		aBoolean3894 = false;
		aByte3912 = (byte) 0;
		anInt3921 = 0;
		anInt3902 = 128;
		configId = -1;
		anInt3877 = 0;
		walkingFlag = 0;
		anInt3892 = 64;
		aBoolean3923 = false;
		aBoolean3924 = false;
		anInt3841 = 128;
		anInt3917 = 128;
	}

	/**
	 * Gets an object definition.
	 * @param objectId The object's id.
	 * @return The object definition.
	 */
	public static ObjectDefinition forId(int objectId) {
		return DEFINITIONS.get(objectId);
	}

	/**
	 * Parses an object's definitions.
	 * @param objectId The object id.
	 * @param buffer The buffer.
	 * @return The object definition.
	 */
	public static ObjectDefinition parseDefinition(int objectId, ByteBuffer buffer) {
		ObjectDefinition def = new ObjectDefinition();
		def.id = objectId;
		while (true) {
			int opcode = buffer.get() & 0xFF;
			if (opcode == 1 || opcode == 5) {
				int length = buffer.get() & 0xff;
				if (def.modelIds == null) {
					def.modelIds = new int[length];
					if (opcode == 1) {
						def.modelConfiguration = new int[length];
					}
					for (int i = 0; i < length; i++) {
						def.modelIds[i] = buffer.getShort() & 0xFFFF;
						if (opcode == 1) {
							def.modelConfiguration[i] = buffer.get() & 0xFF;
						}
					}
//					if (objectId == 2618) {
//						System.out.println(Arrays.toString(def.modelIds) + ", " + Arrays.toString(def.modelConfiguration));
//					}
				} else {
					buffer.position(buffer.position() + (length * (opcode == 1 ? 3 : 2)));
				}
			} else if (opcode == 2) {
				def.name = ByteBufferUtils.getString(buffer);
			} else if (opcode == 14) {
				def.sizeX = buffer.get() & 0xFF;
			} else if (opcode == 15) {
				def.sizeY = buffer.get() & 0xFF;
			} else if (opcode == 17) {
				def.projectileClipped = false;
				def.clipType = 0;
			} else if (opcode == 18) {
				def.projectileClipped = false;
			} else if (opcode == 19) {
				def.secondInt = buffer.get() & 0xFF;
			} else if (opcode == 21) {
				def.aByte3912 = (byte) 1;
			} else if (opcode == 22) {
				def.aBoolean3867 = true;
			} else if (opcode == 23) {
				def.boolean1 = true;
			} else if (opcode == 24) {
				def.addObjectCheck = buffer.getShort() & 0xFFFF;
				if (def.addObjectCheck == 65535) {
					def.addObjectCheck = -1;
				}
			} else if (opcode == 27) {
				def.clipType = 1;
			} else if (opcode == 28) {
				def.anInt3892 = ((buffer.get() & 0xFF) << 2);
			} else if (opcode == 29) {
				def.anInt3878 = buffer.get();
			} else if (opcode == 39) {
				def.anInt3840 = buffer.get() * 5;
			} else if (opcode >= 30 && opcode < 35) {
				def.options[opcode - 30] = ByteBufferUtils.getString(buffer);
				if (def.options[opcode - 30].equals("Hidden")) {
//					def.options[opcode - 30] = null;
					def.hasHiddenOptions = true;
				}
			} else if (opcode == 40) {
				int length = buffer.get() & 0xFF;
				def.originalColors = new short[length];
				def.modifiedColors = new short[length];
				for (int i = 0; i < length; i++) {
					def.originalColors[i] = buffer.getShort();
					def.modifiedColors[i] = buffer.getShort();
				}
			} else if (opcode == 41) {
				int length = buffer.get() & 0xFF;
				def.aShortArray3920 = new short[length];
				def.aShortArray3919 = new short[length];
				for (int i = 0; i < length; i++) {
					def.aShortArray3920[i] = buffer.getShort();
					def.aShortArray3919[i] = buffer.getShort();
				}
			} else if (opcode == 42) {
				int length = buffer.get() & 0xFF;
				def.aByteArray3858 = new byte[length];
				for (int i = 0; i < length; i++) {
					def.aByteArray3858[i] = buffer.get();
				}
			} else if (opcode == 60) {
				buffer.getShort();
			} else if (opcode == 62) {
				def.aBoolean3839 = true;
			} else if (opcode == 64) {
				def.aBoolean3872 = false;
			} else if (opcode == 65) {
				def.anInt3902 = buffer.getShort() & 0xFFFF;
			} else if (opcode == 66) {
				def.anInt3841 = buffer.getShort() & 0xFFFF;
			} else if (opcode == 67) {
				def.anInt3917 = buffer.getShort() & 0xFFFF;
			} else if (opcode == 68) {
				buffer.getShort();
			} else if (opcode == 69) {
				def.walkingFlag = buffer.get() & 0xFF;
			} else if (opcode == 70) {
				def.anInt3883 = buffer.getShort() << 2;
			} else if (opcode == 71) {
				def.anInt3889 = buffer.getShort() << 2;
			} else if (opcode == 72) {
				def.anInt3915 = buffer.getShort() << 2;
			} else if (opcode == 73) {
				def.secondBool = true;
			} else if (opcode == 74) {
				def.notClipped = true;
			} else if (opcode == 75) {
				def.anInt3855 = buffer.get() & 0xFF;
			} else if (opcode == 77 || opcode == 92) {
				def.configFileId = buffer.getShort() & 0xFFFF;
				if (def.configFileId == 65535) {
					def.configFileId = -1;
				}
				def.configId = buffer.getShort() & 0xFFFF;
				if (def.configId == 65535) {
					def.configId = -1;
				}
				int i_66_ = -1;
				if (opcode == 92) {
					i_66_ = buffer.getShort() & 0xFFFF;
					if (i_66_ == 65535) {
						i_66_ = -1;
					}
				}
				int i_67_ = buffer.get() & 0xFF;
				def.childrenIds = new int[i_67_ + 2];
				for (int i_68_ = 0; i_67_ >= i_68_; i_68_++) {
					def.childrenIds[i_68_] = buffer.getShort() & 0xFFFF;
					if (def.childrenIds[i_68_] == 65535) {
						def.childrenIds[i_68_] = -1;
					}
				}
				def.childrenIds[i_67_ + 1] = i_66_;
			} else if (opcode == 78) {
				def.anInt3860 = buffer.getShort() & 0xFFFF;
				def.anInt3904 = buffer.get() & 0xFF;
			} else if (opcode == 79) {
				def.anInt3900 = buffer.getShort() & 0xFFFF;
				def.anInt3905 = buffer.getShort() & 0xFFFF;
				def.anInt3904 = buffer.get() & 0xFF;
				int length = buffer.get() & 0xFF;
				def.anIntArray3859 = new int[length];
				for (int i = 0; i < length; i++) {
					def.anIntArray3859[i] = buffer.getShort() & 0xFFFF;
				}
			} else if (opcode == 81) {
				def.aByte3912 = (byte) 2;
				def.anInt3882 = 256 * buffer.get() & 0xFF;
			} else if (opcode == 82 || opcode == 88) {
				//Nothing.
			} else if (opcode == 89) {
				def.aBoolean3895 = false;
			} else if (opcode == 90) {
				def.aBoolean3870 = true;
			} else if (opcode == 91) {
				def.membersOnly = true;
			} else if (opcode == 93) {
				def.aByte3912 = (byte) 3;
				def.anInt3882 = buffer.getShort() & 0xFFFF;
			} else if (opcode == 94) {
				def.aByte3912 = (byte) 4;
			} else if (opcode == 95) {
				def.aByte3912 = (byte) 5;
			} else {
				if (opcode != 0) {
					System.err.println("Unhandled opcode: " + opcode);
				}
				break;
			}
		}
		def.configureObject();
		if (def.notClipped) {
			if (def.id == 23745 || def.id == 35343) {
				System.out.println("Not clipped - " + def.id);
			}
			def.clipType = 0;
			def.projectileClipped = false;
		}
		/*if (!Main.getGameWorld().getMainContext().isMembers() && def.membersOnly) {
			def.options = null;
		}*///dont need
		return def;
	}

	private static final int[] PIT_TRAPS = new int[] { 6632, 6633, 12602, 19227, 19260, 19261, 19262, 19263, 19264, 19265, 19266, 19267, 19268, 30082 };

	/**
	 * Configures the object definitions.
	 */
	final void configureObject() {
		if (id == 4039) {
			name = "Trapdoor";
			options[0] = "Open";
		}
		if (id == 9260) {
			options[0] = "Take-seed";
		}
		if (id  == 32836) {
			options[0] = "Walk-up";
		} else if (id == 2614) {
			options[0] = "Open";
		}
		for (int i : PIT_TRAPS) {
			if (i == id) {
				name = "Pit";
				options[0] = "Trap";
			}
		}
		if (id == 15042) {
			options[0] = "Take";
		} 
		if (id == 5492) {
			options[0] = "Open";
			options[4] = "Pick-lock";
			options[2] = "Close";
			options[3] = "Climb-down";
		}
		if (id >= 2452 && id <= 2462) {
			name = "Mysterious ruins";
			options[0] = "Enter";
		}
		if (id == 7153 || id == 7143) {
			name = "Rock";
			options[0] = "Mine";
		}
		if (id == 7145 || id == 7151) {
			name = "Boil";
			options[0] = "Burn-down";
		}
		if (id == 7152 || id == 7144) {
			name = "Tendrils";
			options[0] = "Chop";
		}
		if (id == 7148) {
			name = "Passage";
			options[0] = "go-through";
		}
		if (id == 7149 || id == 7147) {
			name = "Gap";
			options[0] = "squeeze-through";
		}
		if (id == 7146 || id == 7150) {
			name = "eyes";
			options[0] = "distract";
		}
		if (id == 2464) {
			name = "Strange stones";
			options[0] = "Search";
		}
		if (id == 1781) {
			name = "Flour bin";
			options[0] = "Empty";
		}
		if (id == 12163 || id == 12164 || id == 12165 || id == 12166) {
			name = "Canoe station";
			options[0] = "Chop-down";
		}
		if (id >= 146 && id <= 151) {
			options[0] = "Pull";
			options[1] = "Inspect";
		}
		if (id <= 145 && id >= 137) {
			options[0] = "Open";
		}
		if (id == 17431) {//silverlight quest
			name = "Rusty key";
			options[0] = "Take";
		}
		if (id == 31759) {
			name = "Drain";
			options[0] = "Search";
		}
		if (secondInt == -1) {
			secondInt = 0;
			if (modelIds != null && (modelConfiguration == null || modelConfiguration[0] == 10)) {
				secondInt = 1;
			}
			for (int i = 0; i < 5; i++) {
				if (options[i] != null) {
					secondInt = 1;
					break;
				}
			}
		}
		if (anInt3855 == -1) {
			anInt3855 = clipType == 0 ? 0 : 1;
		}
	}

	/**
	 * Get the aBoolean3839.
	 * @return the aBoolean3839
	 */
	public boolean isaBoolean3839() {
		return aBoolean3839;
	}

	/**
	 * @param aBoolean3839 the aBoolean3839 to set
	 */
	public void setaBoolean3839(boolean aBoolean3839) {
		this.aBoolean3839 = aBoolean3839;
	}

	/**
	 * Get the originalColors.
	 * @return the originalColors
	 */
	public short[] getOriginalColors() {
		return originalColors;
	}

	/**
	 * Get the childrenIds.
	 * @return the childrenIds
	 */
	public int[] getChildrenIds() {
		return childrenIds;
	}

	/**
	 * Get the anInt3832.
	 * @return the anInt3832
	 */
	public static int getAnInt3832() {
		return anInt3832;
	}

	/**
	 * Get the anIntArray3833.
	 * @return the anIntArray3833
	 */
	public int[] getAnIntArray3833() {
		return anIntArray3833;
	}

	/**
	 * Get the anInt3834.
	 * @return the anInt3834
	 */
	public int getAnInt3834() {
		return anInt3834;
	}

	/**
	 * Get the anInt3835.
	 * @return the anInt3835
	 */
	public int getAnInt3835() {
		return anInt3835;
	}

	/**
	 * Get the anInt3836.
	 * @return the anInt3836
	 */
	public static int getAnInt3836() {
		return anInt3836;
	}

	/**
	 * Get the aByte3837.
	 * @return the aByte3837
	 */
	public byte getaByte3837() {
		return aByte3837;
	}

	/**
	 * Get the anInt3838.
	 * @return the anInt3838
	 */
	public int getAnInt3838() {
		return anInt3838;
	}

	/**
	 * Get the anInt3840.
	 * @return the anInt3840
	 */
	public int getAnInt3840() {
		return anInt3840;
	}

	/**
	 * Get the anInt3841.
	 * @return the anInt3841
	 */
	public int getAnInt3841() {
		return anInt3841;
	}

	/**
	 * Get the anInt3842.
	 * @return the anInt3842
	 */
	public static int getAnInt3842() {
		return anInt3842;
	}

	/**
	 * Get the anInt3843.
	 * @return the anInt3843
	 */
	public static int getAnInt3843() {
		return anInt3843;
	}

	/**
	 * Get the anInt3844.
	 * @return the anInt3844
	 */
	public int getAnInt3844() {
		return anInt3844;
	}

	/**
	 * Get the aBoolean3845.
	 * @return the aBoolean3845
	 */
	public boolean isaBoolean3845() {
		return aBoolean3845;
	}

	/**
	 * Get the anInt3846.
	 * @return the anInt3846
	 */
	public static int getAnInt3846() {
		return anInt3846;
	}

	/**
	 * Get the aByte3847.
	 * @return the aByte3847
	 */
	public byte getaByte3847() {
		return aByte3847;
	}

	/**
	 * Get the aByte3849.
	 * @return the aByte3849
	 */
	public byte getaByte3849() {
		return aByte3849;
	}

	/**
	 * Get the anInt3850.
	 * @return the anInt3850
	 */
	public int getAnInt3850() {
		return anInt3850;
	}

	/**
	 * Get the anInt3851.
	 * @return the anInt3851
	 */
	public int getAnInt3851() {
		return anInt3851;
	}

	/**
	 * Get the secondBool.
	 * @return the secondBool
	 */
	public boolean isSecondBool() {
		return secondBool;
	}

	/**
	 * Get the aBoolean3853.
	 * @return the aBoolean3853
	 */
	public boolean isaBoolean3853() {
		return aBoolean3853;
	}

	/**
	 * Get the anInt3855.
	 * @return the anInt3855
	 */
	public int getAnInt3855() {
		return anInt3855;
	}

	/**
	 * Get the firstBool.
	 * @return the firstBool
	 */
	public boolean isFirstBool() {
		return notClipped;
	}

	/**
	 * Get the anInt3857.
	 * @return the anInt3857
	 */
	public int getAnInt3857() {
		return anInt3857;
	}

	/**
	 * Get the aByteArray3858.
	 * @return the aByteArray3858
	 */
	public byte[] getaByteArray3858() {
		return aByteArray3858;
	}

	/**
	 * Get the anIntArray3859.
	 * @return the anIntArray3859
	 */
	public int[] getAnIntArray3859() {
		return anIntArray3859;
	}

	/**
	 * Get the anInt3860.
	 * @return the anInt3860
	 */
	public int getAnInt3860() {
		return anInt3860;
	}

	/**
	 * Get the options.
	 * @return the options
	 */
	@Override
	public String[] getOptions() {
		return options;
	}

	/**
	 * Get the configFileId.
	 * @return the configFileId
	 */
	public int getConfigFileId() {
		return configFileId;
	}

	/**
	 * Get the modifiedColors.
	 * @return the modifiedColors
	 */
	public short[] getModifiedColors() {
		return modifiedColors;
	}

	/**
	 * Get the anInt3865.
	 * @return the anInt3865
	 */
	public int getAnInt3865() {
		return anInt3865;
	}

	/**
	 * Get the aBoolean3866.
	 * @return the aBoolean3866
	 */
	public boolean isaBoolean3866() {
		return aBoolean3866;
	}

	/**
	 * Get the aBoolean3867.
	 * @return the aBoolean3867
	 */
	public boolean isaBoolean3867() {
		return aBoolean3867;
	}

	/**
	 * Get the solid.
	 * @return the solid
	 */
	public boolean isProjectileClipped() {
		return projectileClipped;
	}

	/**
	 * Get the anIntArray3869.
	 * @return the anIntArray3869
	 */
	public int[] getAnIntArray3869() {
		return anIntArray3869;
	}

	/**
	 * Get the aBoolean3870.
	 * @return the aBoolean3870
	 */
	public boolean isaBoolean3870() {
		return aBoolean3870;
	}

	/**
	 * Get the sizeY.
	 * @return the sizeY
	 */
	public int getSizeY() {
		return sizeY;
	}

	/**
	 * Get the aBoolean3872.
	 * @return the aBoolean3872
	 */
	public boolean isaBoolean3872() {
		return aBoolean3872;
	}

	/**
	 * Get the membersOnly.
	 * @return the membersOnly
	 */
	public boolean isaBoolean3873() {
		return membersOnly;
	}

	/**
	 * Get the thirdInt.
	 * @return the thirdInt
	 */
	public boolean getThirdBoolean() {
		return boolean1;
	}

	/**
	 * Get the anInt3875.
	 * @return the anInt3875
	 */
	public int getAnInt3875() {
		return anInt3875;
	}

	/**
	 * Get the addObjectCheck.
	 * @return the addObjectCheck
	 */
	public int getAddObjectCheck() {
		return addObjectCheck;
	}

	/**
	 * Get the anInt3877.
	 * @return the anInt3877
	 */
	public int getAnInt3877() {
		return anInt3877;
	}

	/**
	 * Get the anInt3878.
	 * @return the anInt3878
	 */
	public int getAnInt3878() {
		return anInt3878;
	}

	/**
	 * Get the clipType.
	 * @return the clipType
	 */
	public int getClipType() {
		return clipType;
	}

	/**
	 * Get the anInt3881.
	 * @return the anInt3881
	 */
	public int getAnInt3881() {
		return anInt3881;
	}

	/**
	 * Get the anInt3882.
	 * @return the anInt3882
	 */
	public int getAnInt3882() {
		return anInt3882;
	}

	/**
	 * Get the anInt3883.
	 * @return the anInt3883
	 */
	public int getAnInt3883() {
		return anInt3883;
	}

	/**
	 * Get the loader.
	 * @return the loader
	 */
	public Object getLoader() {
		return loader;
	}

	/**
	 * Get the anInt3889.
	 * @return the anInt3889
	 */
	public int getAnInt3889() {
		return anInt3889;
	}

	/**
	 * Get the sizeX.
	 * @return the sizeX
	 */
	public int getSizeX() {
		return sizeX;
	}

	/**
	 * Get the aBoolean3891.
	 * @return the aBoolean3891
	 */
	public boolean isaBoolean3891() {
		return aBoolean3891;
	}

	/**
	 * Get the anInt3892.
	 * @return the anInt3892
	 */
	public int getAnInt3892() {
		return anInt3892;
	}

	/**
	 * Get the secondInt.
	 * @return the secondInt
	 */
	public int getSecondInt() {
		return secondInt;
	}

	/**
	 * Get the aBoolean3894.
	 * @return the aBoolean3894
	 */
	public boolean isaBoolean3894() {
		return aBoolean3894;
	}

	/**
	 * Get the aBoolean3895.
	 * @return the aBoolean3895
	 */
	public boolean isaBoolean3895() {
		return aBoolean3895;
	}

	/**
	 * Get the anInt3896.
	 * @return the anInt3896
	 */
	public int getAnInt3896() {
		return anInt3896;
	}

	/**
	 * Get the configId.
	 * @return the configId
	 */
	public int getConfigId() {
		return configId;
	}

	/**
	 * Get the aByteArray3899.
	 * @return the aByteArray3899
	 */
	public byte[] getaByteArray3899() {
		return aByteArray3899;
	}

	/**
	 * Get the anInt3900.
	 * @return the anInt3900
	 */
	public int getAnInt3900() {
		return anInt3900;
	}

	/**
	 * Get the name.
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Get the anInt3902.
	 * @return the anInt3902
	 */
	public int getAnInt3902() {
		return anInt3902;
	}

	/**
	 * Get the anInt3904.
	 * @return the anInt3904
	 */
	public int getAnInt3904() {
		return anInt3904;
	}

	/**
	 * Get the anInt3905.
	 * @return the anInt3905
	 */
	public int getAnInt3905() {
		return anInt3905;
	}

	/**
	 * Get the aBoolean3906.
	 * @return the aBoolean3906
	 */
	public boolean isaBoolean3906() {
		return aBoolean3906;
	}

	/**
	 * Get the anIntArray3908.
	 * @return the anIntArray3908
	 */
	public int[] getAnIntArray3908() {
		return anIntArray3908;
	}

	/**
	 * Get the aByte3912.
	 * @return the aByte3912
	 */
	public byte getaByte3912() {
		return aByte3912;
	}

	/**
	 * Get the anInt3913.
	 * @return the anInt3913
	 */
	public int getAnInt3913() {
		return anInt3913;
	}

	/**
	 * Get the aByte3914.
	 * @return the aByte3914
	 */
	public byte getaByte3914() {
		return aByte3914;
	}

	/**
	 * Get the anInt3915.
	 * @return the anInt3915
	 */
	public int getAnInt3915() {
		return anInt3915;
	}

	/**
	 * Get the anIntArrayArray3916.
	 * @return the anIntArrayArray3916
	 */
	public int[][] getAnIntArrayArray3916() {
		return anIntArrayArray3916;
	}

	/**
	 * Get the anInt3917.
	 * @return the anInt3917
	 */
	public int getAnInt3917() {
		return anInt3917;
	}

	/**
	 * Get the aShortArray3919.
	 * @return the aShortArray3919
	 */
	public short[] getaShortArray3919() {
		return aShortArray3919;
	}

	/**
	 * Get the aShortArray3920.
	 * @return the aShortArray3920
	 */
	public short[] getaShortArray3920() {
		return aShortArray3920;
	}

	/**
	 * Get the anInt3921.
	 * @return the anInt3921
	 */
	public int getAnInt3921() {
		return anInt3921;
	}

	/**
	 * Get the aClass194_3922.
	 * @return the aClass194_3922
	 */
	public Object getaClass194_3922() {
		return aClass194_3922;
	}

	/**
	 * Get the aBoolean3923.
	 * @return the aBoolean3923
	 */
	public boolean isaBoolean3923() {
		return aBoolean3923;
	}

	/**
	 * Get the aBoolean3924.
	 * @return the aBoolean3924
	 */
	public boolean isaBoolean3924() {
		return aBoolean3924;
	}

	/**
	 * Gets the object's model ids.
	 * @return The model ids array.
	 */
	public int[] getModelIds() {
		return modelIds;
	}

	/**
	 * If the object has a action.
	 * @param action The specified action.
	 * @return If the object has the action {@code true}.
	 */
	public boolean hasAction(String action) {
		if (options == null) {
			return false;
		}
		for (String option: options) {
			if (option == null) {
				continue;
			}
			if (option.equalsIgnoreCase(action)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Get the definitions.
	 * @return the definitions
	 */
	public static Map<Integer, ObjectDefinition> getDefinitions() {
		return DEFINITIONS;
	}

	/**
	 * Gets the hasHiddenOptions.
	 * @return The hasHiddenOptions.
	 */
	public boolean isHasHiddenOptions() {
		return hasHiddenOptions;
	}

	/**
	 * Sets the hasHiddenOptions.
	 * @param hasHiddenOptions The hasHiddenOptions to set.
	 */
	public void setHasHiddenOptions(boolean hasHiddenOptions) {
		this.hasHiddenOptions = hasHiddenOptions;
	}

	/**
	 * Gets the walking flag.
	 * @return The walking flag.
	 */
	public int getWalkingFlag() {
		return walkingFlag;
	}

}