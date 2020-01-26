package alex.cache.loaders;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.alex.io.InputStream;
import com.alex.store.Store;


import emperor.ObjectMap.GameObject;

/**
 * The {@link Definitions} of {@link GameObject}s
 * @author SonicForce41
 */
public class ObjectDefinitions {

	/**
	 * The {@link Map} of {@link ObjectDefinitions}
	 */
	private static Map<Integer, ObjectDefinitions> DEFINITIONS = new HashMap<Integer, ObjectDefinitions>();

	/**
	 * anInt3832
	 */
	static int anInt3832;

	/**
	 * anInt3836
	 */
	static int anInt3836;

	/**
	 * anInt3842
	 */
	static int anInt3842;

	/**
	 * anInt3843
	 */
	static int anInt3843;

	/**
	 * anInt3846
	 */
	static int anInt3846;

	/**
	 * Gets the {@link ObjectDefinitions} for 'objectId'
	 */
	public static ObjectDefinitions forId(final int id) {
		return DEFINITIONS.get(id);
	}

	/**
	 * Gets the {@link Map} of {@link] ObjectDefinitions}
	 * @return
	 */
	public static Map<Integer, ObjectDefinitions> getObjectDefinitions() {
		return DEFINITIONS;
	}

	/**
	 * Gets the archive Id
	 * @return
	 */
	private static int getArchiveId(final int objectID2) {
		return objectID2 >>> -1135990488;
	}

	/**
	 * aBoolean3853
	 */
	public boolean aBoolean3853;

	/**
	 * aBoolean3891
	 */
	public boolean aBoolean3891;

	/**
	 * actionCount
	 */
	public int actionCount;

	/**
	 * clippingFlag
	 */
	public boolean clippingFlag;

	/**
	 * configFileId
	 */
	public int configFileId;

	/**
	 * id
	 */
	public int id;

	/**
	 * name
	 */
	public String name;

	/**
	 * secondBool
	 */
	public boolean secondBool;

	/**
	 * secondInt
	 */
	public int secondInt;

	/**
	 * sizeX
	 */
	public int sizeX;

	/**
	 * sizeY
	 */
	public int sizeY;

	/**
	 * thirdInt
	 */
	public int thirdInt;

	/**
	 * aByte3912
	 */
	private byte aByte3912;

	/**
	 * aByteArray3858
	 */
	private byte[] aByteArray3858;

	/**
	 * anInt3881
	 */
	private int anInt3881;

	/**
	 * anIntArray3869
	 */
	private int[] anIntArray3869;

	/**
	 * anIntArrayArray3916
	 */
	@SuppressWarnings("unused")
	private int[][] anIntArrayArray3916;

	/**
	 * aShortArray3919
	 */
	private short[] aShortArray3919;

	public String[] options;
	/**
	 * aShortArray3920
	 */
	private short[] aShortArray3920;

	/**
	 * modelConfiguration
	 */
	private byte[] modelConfiguration;

	/**
	 * modifiedColors
	 */
	private short[] modifiedColors;

	/**
	 * The object Id
	 */
	private int objectId;

	/**
	 * The original colors array
	 */
	private short[] originalColors;

	/**
	 * solid
	 */
	private boolean solid;

	/**
	 * walkBitFlag
	 */
	private int walkBitFlag;

	/**
	 * aBoolean3839
	 */
	boolean aBoolean3839;

	/**
	 * aBoolean3845
	 */
	boolean aBoolean3845;

	/**
	 * aBoolean3866
	 */
	boolean aBoolean3866;

	/**
	 * aBoolean3867
	 */
	boolean aBoolean3867;

	/**
	 * aBoolean3870
	 */
	boolean aBoolean3870;

	/**
	 * aBoolean3872
	 */
	boolean aBoolean3872;

	/**
	 * aBoolean3873
	 */
	boolean aBoolean3873;

	/**
	 * aBoolean3894
	 */
	boolean aBoolean3894;

	/**
	 * aBoolean3895
	 */
	boolean aBoolean3895;

	/**
	 * aBoolean3906
	 */
	boolean aBoolean3906;

	/**
	 * aBoolean3923
	 */
	boolean aBoolean3923;

	/**
	 * aBoolean3924
	 */
	boolean aBoolean3924;

	/**
	 * anInt3835
	 */
	int anInt3835;

	/**
	 * anInt3838
	 */
	int anInt3838 = -1;

	/**
	 * anInt3844
	 */
	int anInt3844;

	/**
	 * anInt3850
	 */
	int anInt3850;

	/**
	 * anInt3851
	 */
	int anInt3851;

	/**
	 * anInt3855
	 */
	int anInt3855;

	/**
	 * anInt3857
	 */
	int anInt3857;

	/**
	 * anInt3860
	 */
	int anInt3860;

	/**
	 * anInt3865
	 */
	int anInt3865;

	/**
	 * anInt3876
	 */
	public int animationId;

	/**
	 * anInt3892
	 */
	int anInt3892;

	/**
	 * anInt3896
	 */
	int anInt3896;

	/**
	 * anInt3900
	 */
	int anInt3900;

	/**
	 * anInt3904
	 */
	int anInt3904;

	/**
	 * anInt3905
	 */
	int anInt3905;

	/**
	 * anInt3913
	 */
	int anInt3913;

	/**
	 * anInt3921
	 */
	int anInt3921;

	/**
	 * anIntArray3833
	 */
	int[] anIntArray3833 = null;

	/**
	 * anIntArray3859
	 */
	int[] anIntArray3859;

	/**
	 * anIntArray3908
	 */
	int[] anIntArray3908;

	/**
	 * The childrens id
	 */
	int[] childrenIds;

	/**
	 * configId
	 */
	int configId;

	public int[] models;

	/**
	 * Constructs a new {@code ObjectDefinitions.java} {@code Object}.
	 * @param objectId the object Id
	 */
	public ObjectDefinitions(final int objectId) {
		this.objectId = objectId;
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
		setAnInt3881(0);
		anInt3857 = -1;
		aBoolean3872 = true;
		options = new String[5];
		aBoolean3839 = false;
		anIntArray3869 = null;
		sizeX = 1;
		thirdInt = -1;
		solid = true;
		aBoolean3895 = true;
		aBoolean3870 = false;
		aBoolean3853 = true;
		secondBool = false;
		actionCount = 2;
		anInt3855 = -1;
		anInt3904 = 0;
		sizeY = 1;
		animationId = -1;
		clippingFlag = false;
		aBoolean3891 = false;
		anInt3905 = 0;
		name = "null";
		anInt3913 = -1;
		aBoolean3906 = false;
		aBoolean3873 = false;
		anInt3900 = 0;
		secondInt = -1;
		aBoolean3894 = false;
		setaByte3912((byte) 0);
		anInt3921 = 0;
		configId = -1;
		setWalkBitFlag(0);
		anInt3892 = 64;
		aBoolean3923 = false;
		aBoolean3924 = false;
	}

	/**
	 * Method returns the value of aByte3912
	 * @return the aByte3912
	 */
	public byte getaByte3912() {
		return aByte3912;
	}

	/**
	 * Method returns the value of anInt3881
	 * @return the anInt3881
	 */
	public int getAnInt3881() {
		return anInt3881;
	}

	/**
	 * Gets the object Id
	 * @return the objectId
	 */
	public int getObjectId() {
		return objectId;
	}

	/**
	 * Method returns the value of walkBitFlag
	 * @return the walkBitFlag
	 */
	public int getWalkBitFlag() {
		return walkBitFlag;
	}

	public static ObjectDefinitions initialize(int objectId, Store store) {
		byte[] is = store.getIndexes()[16].getFile(getArchiveId(objectId), objectId & 0xff);
		if (is == null) {
			return null;
		}
		ObjectDefinitions def = new ObjectDefinitions(objectId);
		def.readValueLoop(new InputStream(is));
		def.configureObject();
		if (def.clippingFlag) {
			def.solid = false;
			def.actionCount = 0;
		}
		if (def.name.contains("booth")) {
			def.clippingFlag = false;
			def.solid = true;
			def.actionCount = 2;
		}
		DEFINITIONS.put(objectId, def);
		return def;
	}

	/**
	 * Checks if the object is clipped
	 * @return
	 */
	public boolean isClippingFlag() {
		return clippingFlag;
	}

	/**
	 * Checks if the object is solid
	 * @return
	 */
	public boolean isSolid() {
		return solid;
	}

	/**
	 * Gets the size X
	 * @return
	 */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * Gets the size Y
     * @return
     */
    public int getSizeY() {
        return sizeY;
    }

    /**
     * Gets the acount count
     * @return
     */
    public int getActionCount() {
        return actionCount;
    }

    /**
     * Gets the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the walk bit
     * @return
     */
    public int getWalkBit() {
        return walkBitFlag;
    }

	/**
	 * Method sets the value for aByte3912
	 * @param aByte3912 the aByte3912 to set
	 */
	public void setaByte3912(final byte aByte3912) {
		this.aByte3912 = aByte3912;
	}

	/**
	 * Method sets the value for anInt3881
	 * @param anInt3881 the anInt3881 to set
	 */
	public void setAnInt3881(final int anInt3881) {
		this.anInt3881 = anInt3881;
	}

	/**
	 * Method sets the value for walkBitFlag
	 * @param walkBitFlag the walkBitFlag to set
	 */
	public void setWalkBitFlag(final int walkBitFlag) {
		this.walkBitFlag = walkBitFlag;
	}

	/**
	 * Reads the values in a loop
	 * @param builder
	 */
	private void readValueLoop(final InputStream builder) {
		for (;;) {
			int opcode = builder.readUnsignedByte();
			if (opcode == 0)
				break;
			readValues(builder, opcode);
		}
	}

	/**
	 * Reads the values
	 * @param builder the PacketBuilder
	 * @param opcode the opcode
	 */
	private void readValues(final InputStream builder, final int opcode) {
//		System.out.println("Reading opcode " + opcode);
		if (opcode != 1 && opcode != 5) {
			if (opcode != 2) {
				if (opcode != 14) {
					if (opcode != 15) {
						if (opcode == 17) {
							solid = false;
							actionCount = 0;
						} else if (opcode != 18) {
							if (opcode == 19)
								secondInt = builder.readUnsignedByte();
							else if (opcode == 21)
								setaByte3912((byte) 1);
							else if (opcode != 22) {
								if (opcode != 23) {
									if (opcode != 24) {
										if (opcode == 27)
											actionCount = 1;
										else if (opcode == 28)
											anInt3892 = (builder.readUnsignedByte() << 2);
										else if (opcode != 29) {
											if (opcode != 39) {
												if (opcode < 30 || opcode >= 35) {
													if (opcode == 40) {
														int i_53_ = (builder.readUnsignedByte());
														originalColors = new short[i_53_];
														modifiedColors = new short[i_53_];
														for (int i_54_ = 0; i_53_ > i_54_; i_54_++) {
															originalColors[i_54_] = (short) (builder.readUnsignedShort());
															modifiedColors[i_54_] = (short) (builder.readUnsignedShort());
														}
													} else if (opcode != 41) {
														if (opcode != 42) {
															if (opcode != 62) {
																if (opcode != 64) {
																	if (opcode == 65)
																		builder.readUnsignedShort();
																	else if (opcode != 66) {
																		if (opcode != 67) {
																			if (opcode == 69)
																				setWalkBitFlag(builder.readUnsignedByte());
																			else if (opcode != 70) {
																				if (opcode == 71)
																					builder.readShort();
																				else if (opcode != 72) {
																					if (opcode == 73)
																						secondBool = true;
																					else if (opcode == 74)
																						clippingFlag = true;
																					else if (opcode != 75) {
																						if (opcode != 77 && opcode != 92) {
																							if (opcode == 78) {
																								anInt3860 = builder.readUnsignedShort();
																								anInt3904 = builder.readUnsignedByte();
																							} else if (opcode != 79) {
																								if (opcode == 81) {
																									setaByte3912((byte) 2);
																									builder.readUnsignedByte();
																								} else if (opcode != 82) {
																									if (opcode == 88)
																										aBoolean3853 = false;
																									else if (opcode != 89) {
																										if (opcode == 90)
																											aBoolean3870 = true;
																										else if (opcode != 91) {
																											if (opcode != 93) {
																												if (opcode == 94)
																													setaByte3912((byte) 4);
																												else if (opcode != 95) {
																													if (opcode != 96) {
																														if (opcode == 97)
																															aBoolean3866 = true;
																														else if (opcode == 98)
																															aBoolean3923 = true;
																														else if (opcode == 99) {
																															anInt3857 = builder.readUnsignedByte();
																															anInt3835 = builder.readUnsignedShort();
																														} else if (opcode == 100) {
																															anInt3844 = builder.readUnsignedByte();
																															anInt3913 = builder.readUnsignedShort();
																														} else if (opcode != 101) {
																															if (opcode == 102)
																																anInt3838 = builder.readUnsignedShort();
																															else if (opcode == 103)
																																thirdInt = 0;
																															else if (opcode != 104) {
																																if (opcode == 105)
																																	aBoolean3906 = true;
																																else if (opcode == 106) {
																																	int i_55_ = builder.readUnsignedByte();
																																	anIntArray3869 = new int[i_55_];
																																	anIntArray3833 = new int[i_55_];
																																	for (int i_56_ = 0; i_56_ < i_55_; i_56_++) {
																																		anIntArray3833[i_56_] = builder.readUnsignedShort();
																																		int i_57_ = builder.readUnsignedByte();
																																		anIntArray3869[i_56_] = i_57_;
																																		setAnInt3881(getAnInt3881()
																																				+ i_57_);
																																	}
																																} else if (opcode == 107)
																																	anInt3851 = builder.readUnsignedShort();
																																else if (opcode >= 150 && opcode < 155) {
																																	options[opcode - 150] = builder.readString();
																																} else if (opcode != 160) {
																																	if (opcode == 162) {
																																		setaByte3912((byte) 3);
																																		builder.readInt();
																																	} else if (opcode == 163) {
																																		builder.readByte();
																																		builder.readByte();
																																		builder.readByte();
																																		builder.readByte();
																																	} else if (opcode != 164) {
																																		if (opcode != 165) {
																																			if (opcode != 166) {
																																				if (opcode == 167)
																																					anInt3921 = builder.readUnsignedShort();
																																				else if (opcode != 168) {
																																					if (opcode == 169) {
																																						aBoolean3845 = true;
																																					} else if (opcode == 170) {
																																						builder.readUnsignedSmart();
																																					} else if (opcode == 171) {
																																						builder.readUnsignedSmart();
																																					} else if (opcode == 173) {
																																						builder.readUnsignedShort();
																																						builder.readUnsignedShort();
																																					} else if (opcode == 177) {
																																						// something
																																						// =
																																						// true
																																					} else if (opcode == 178) {
																																						builder.readUnsignedByte();
																																					} else if (opcode == 249) {
																																						int i_58_ = builder.readUnsignedByte();
																																						for (int i_60_ = 0; i_60_ < i_58_; i_60_++) {
																																							boolean bool = builder.readUnsignedByte() == 1;
																																							builder.readByte();
																																							builder.readShort();
																																							if (!bool)
																																								builder.readInt();
																																							else
																																								builder.readString();
																																						}
																																					}
																																				} else
																																					aBoolean3894 = true;
																																			} else
																																				builder.readShort();
																																		} else
																																			builder.readShort();
																																	} else
																																		builder.readShort();
																																} else {
																																	int i_62_ = builder.readUnsignedByte();
																																	anIntArray3908 = new int[i_62_];
																																	for (int i_63_ = 0; i_62_ > i_63_; i_63_++)
																																		anIntArray3908[i_63_] = builder.readUnsignedShort();
																																}
																															} else
																																anInt3865 = builder.readUnsignedByte();
																														} else
																															anInt3850 = builder.readUnsignedByte();
																													} else
																														aBoolean3924 = true;
																												} else {
																													setaByte3912((byte) 5);
																													builder.readShort();
																												}
																											} else {
																												setaByte3912((byte) 3);
																												builder.readUnsignedShort();
																											}
																										} else
																											aBoolean3873 = true;
																									} else
																										aBoolean3895 = false;
																								} else
																									aBoolean3891 = true;
																							} else {
																								anInt3900 = builder.readUnsignedShort();
																								anInt3905 = builder.readUnsignedShort();
																								anInt3904 = builder.readUnsignedByte();
																								int i_64_ = builder.readUnsignedByte();
																								anIntArray3859 = new int[i_64_];
																								for (int i_65_ = 0; i_65_ < i_64_; i_65_++)
																									anIntArray3859[i_65_] = builder.readUnsignedShort();
																							}
																						} else {
																							configFileId = builder.readUnsignedShort();
																							if (configFileId == 65535)
																								configFileId = -1;
																							configId = builder.readUnsignedShort();
																							if (configId == 65535)
																								configId = -1;
																							int i_66_ = -1;
																							if (opcode == 92) {
																								i_66_ = builder.readUnsignedShort();
																								if (i_66_ == 65535)
																									i_66_ = -1;
																							}
																							int i_67_ = builder.readUnsignedByte();
																							childrenIds = new int[i_67_ + 2];
																							for (int i_68_ = 0; i_67_ >= i_68_; i_68_++) {
																								childrenIds[i_68_] = builder.readUnsignedShort();
																								if (childrenIds[i_68_] == 65535)
																									childrenIds[i_68_] = -1;
																							}
																							childrenIds[i_67_ + 1] = i_66_;
																						}
																					} else
																						anInt3855 = builder.readUnsignedByte();
																				} else
																					builder.readShort();
																			} else
																				builder.readShort();
																		} else
																			builder.readUnsignedShort();
																	} else
																		builder.readUnsignedShort();
																} else
																	aBoolean3872 = false;
															} else
																aBoolean3839 = true;
														} else {
															int i_69_ = builder.readUnsignedByte();
															aByteArray3858 = new byte[i_69_];
															for (int i_70_ = 0; i_70_ < i_69_; i_70_++)
																aByteArray3858[i_70_] = (byte) builder.readUnsignedByte();
														}
													} else {
														int i_71_ = builder.readUnsignedByte();
														aShortArray3920 = new short[i_71_];
														aShortArray3919 = new short[i_71_];
														for (int i_72_ = 0; i_71_ > i_72_; i_72_++) {
															aShortArray3920[i_72_] = (short) builder.readUnsignedShort();
															aShortArray3919[i_72_] = (short) builder.readUnsignedShort();
														}
													}
												} else
													options[opcode - 30] = builder.readString();
											} else
												builder.readByte();
										} else
											builder.readByte();
									} else {
										animationId = builder.readUnsignedShort();
										if (animationId == 65535)
											animationId = -1;
									}
								} else
									thirdInt = 1;
							} else
								aBoolean3867 = true;
						} else
							solid = false;
					} else
						sizeX = builder.readUnsignedByte();
				} else
					sizeY = builder.readUnsignedByte();
			} else
				name = builder.readString();
		} else {
			int length = builder.readUnsignedByte() & 0xff;
			if (opcode == 1) {
				modelConfiguration = new byte[length];
			}
			models = new int[length];
			for (int i = 0; i < length; i++) {
				models[i] = builder.readShort() & 0xFFFF;
				int config = -1;
				if (opcode == 1) {
					config = modelConfiguration[i] = (byte) (builder.readUnsignedByte() & 0xFF);
				}
//				System.out.println("Model id: " + model + ", " + config);
			}
//			boolean aBoolean1162 = false;
//			if (opcode == 5 && aBoolean1162)
//				skipBytes(builder);
//			int length = builder.readUnsignedByte();
//			anIntArrayArray3916 = new int[length][];
//			modelConfiguration = new byte[length];
//			for (int i = 0; i < length; i++) {
//				modelConfiguration[i] = (byte) builder.readByte();
//				int i_75_ = builder.readUnsignedByte();
//				anIntArrayArray3916[i] = new int[i_75_];
//				for (int i_76_ = 0; i_75_ > i_76_; i_76_++) {
//					anIntArrayArray3916[i][i_76_] = builder.readUnsignedShort();
//					if (opcode == 1)
//					System.out.println("Model id " + anIntArrayArray3916[i][i_76_]);
//				}
//			}
//			if (opcode == 5 && !aBoolean1162)
//				skipBytes(builder);
		}
	}

	/**
	 * Skips few bytes
	 * @param builder
	 */
	private void skipBytes(final InputStream builder) {
		int length = builder.readUnsignedByte();
		for (int index = 0; index < length; index++) {
			builder.skip(1);
			builder.skip(builder.readUnsignedByte() * 2);
		}
	}

	/**
	 * Checks object variables
	 */
	void configureObject() {
		if (id == 4039) {
			name = "Trapdoor";
			options[0] = "Open";
		}
		if (secondInt == -1) {
			secondInt = 0;
			if (modelConfiguration != null && modelConfiguration.length == 1 && modelConfiguration[0] == 10)
				secondInt = 1;
			for (int i = 0; i < 5; i++) {
				if (options[i] != null) {
					secondInt = 1;
					break;
				}
			}
		}
		if (anInt3855 == -1)
			anInt3855 = actionCount != 0 ? 1 : 0;
	}
	
	/**
	 * Clears the definition
	 */
	public static void clear() {
		DEFINITIONS = new TreeMap<Integer, ObjectDefinitions>();
	}

}