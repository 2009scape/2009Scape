package org.crandor.cache.def.impl;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;

import org.crandor.cache.Cache;
import org.crandor.game.world.GameWorld;

/**
 * Holds definitions for render animations.
 * @author Jagex
 * @author Emperor
 *
 */
public class RenderAnimationDefinition {

	public int turn180Animation;
	public int anInt951 = -1;
	public int anInt952;
	public int turnCWAnimation = -1;
	public int anInt954;
	public int anInt955;
	public int anInt956;
	public int anInt957;
	public int anInt958;
	public int[] anIntArray959 = null;
	public int anInt960;
	public int anInt961 = 0;
	public int anInt962;
	public int walkAnimationId;
	public int anInt964;
	public int anInt965;
	public int anInt966;
	public int[] standAnimationIds;
	public int anInt969;
	public int[] anIntArray971;
	public int standAnimationId;
	public int anInt973;
	public int anInt974;
	public int anInt975;
	public int runAnimationId;
	public int anInt977;
	public boolean aBoolean978;
	public int[][] anIntArrayArray979;
	public int anInt980;
	public int turnCCWAnimation;
	public int anInt983;
	public int anInt985;
	public int anInt986;
	public int anInt987;
	public int anInt988;
	public int anInt989;
	public int anInt990;
	public int anInt992;
	public int anInt993;
	public int anInt994;

	/**
	 * Gets the render animation definitions for the given id.
	 * @param animId The render animation id.
	 * @return The render animation definitions.
	 */
	public static RenderAnimationDefinition forId(int animId) {
		RenderAnimationDefinition defs = new RenderAnimationDefinition();
		if (animId == -1) {
			return null;
		}
		byte[] data = Cache.getIndexes()[2].getFileData(32, animId);
		defs = new RenderAnimationDefinition();
		if (data != null) {
			defs.parse(ByteBuffer.wrap(data));
		} else {
			System.err.println("No definitions found for render animation " + animId + ", size=" + Cache.getIndexes()[2].getFilesSize(32) + "!");
		}
		return defs;
	}

	private void parse(ByteBuffer buffer) {
		for (;;) {
			int opcode = buffer.get() & 0xFF;
			if (opcode == 0) {
				break;
			}
			parseOpcode(buffer, opcode);
		}
	}

	private void parseOpcode(ByteBuffer buffer, int opcode) {
		if (opcode == 54) {
			@SuppressWarnings("unused")
			int anInt1260 = (buffer.get() & 0xFF) << 6;
			@SuppressWarnings("unused")
			int anInt1227 = (buffer.get() & 0xFF) << 6;
		} else if (opcode == 55) {
			int[] anIntArray1246 = new int[12];
			int i_14_ = buffer.get() & 0xFF;
			anIntArray1246[i_14_] = buffer.getShort() & 0xFFFF;
		} else if (opcode == 56) {
			int[][] anIntArrayArray1217 = new int[12][];
			int i_12_ = buffer.get() & 0xFF;
			anIntArrayArray1217[i_12_] = new int[3];
			for (int i_13_ = 0; i_13_ < 3; i_13_++)
				anIntArrayArray1217[i_12_][i_13_] = buffer.getShort();
		} else if ((opcode ^ 0xffffffff) != -2) {
			if ((opcode ^ 0xffffffff) != -3) {
				if (opcode != 3) {
					if ((opcode ^ 0xffffffff) != -5) {
						if (opcode == 5)
							anInt977 = buffer.getShort() & 0xFFFF;
						else if ((opcode ^ 0xffffffff) != -7) {
							if (opcode == 7)
								anInt960 = buffer.getShort() & 0xFFFF;
							else if ((opcode ^ 0xffffffff) == -9)
								anInt985 = buffer.getShort() & 0xFFFF;
							else if (opcode == 9)
								anInt957 = buffer.getShort() & 0xFFFF;
							else if (opcode == 26) {
								anInt973 = (short) (4 * buffer
										.get() & 0xFF);
								anInt975 = (short) (buffer.get() & 0xFF * 4);
							} else if ((opcode ^ 0xffffffff) == -28) {
								if (anIntArrayArray979 == null)
									anIntArrayArray979 = new int[12][];
								int i = buffer.get() & 0xFF;
								anIntArrayArray979[i] = new int[6];
								for (int i_1_ = 0; (i_1_ ^ 0xffffffff) > -7; i_1_++)
									anIntArrayArray979[i][i_1_] = buffer
											.getShort();
							} else if ((opcode ^ 0xffffffff) == -29) {
								anIntArray971 = new int[12];
								for (int i = 0; i < 12; i++) {
									anIntArray971[i] = buffer
											.get() & 0xFF;
									if (anIntArray971[i] == 255)
										anIntArray971[i] = -1;
								}
							} else if (opcode != 29) {
								if (opcode != 30) {
									if ((opcode ^ 0xffffffff) != -32) {
										if (opcode != 32) {
											if ((opcode ^ 0xffffffff) != -34) {
												if (opcode != 34) {
													if (opcode != 35) {
														if ((opcode ^ 0xffffffff) != -37) {
															if ((opcode ^ 0xffffffff) != -38) {
																if (opcode != 38) {
																	if ((opcode ^ 0xffffffff) != -40) {
																		if ((opcode ^ 0xffffffff) != -41) {
																			if ((opcode ^ 0xffffffff) == -42)
																				turnCWAnimation = buffer
																						.getShort() & 0xFFFF;
																			else if (opcode != 42) {
																				if ((opcode ^ 0xffffffff) == -44)
																					buffer.getShort();
																				else if ((opcode ^ 0xffffffff) != -45) {
																					if ((opcode ^ 0xffffffff) == -46)
																						anInt964 = buffer
																								.getShort() & 0xFFFF;
																					else if ((opcode ^ 0xffffffff) != -47) {
																						if (opcode == 47)
																							anInt966 = buffer
																									.getShort() & 0xFFFF;
																						else if (opcode == 48)
																							anInt989 = buffer
																									.getShort() & 0xFFFF;
																						else if (opcode != 49) {
																							if ((opcode ^ 0xffffffff) != -51) {
																								if (opcode != 51) {
																									if (opcode == 52) {
																										int i = buffer
																												.get() & 0xFF;
																										anIntArray959 = new int[i];
																										standAnimationIds = new int[i];
																										for (int i_2_ = 0; i_2_ < i; i_2_++) {
																											standAnimationIds[i_2_] = buffer
																													.getShort() & 0xFFFF;
																											int i_3_ = buffer
																													.get() & 0xFF;
																											anIntArray959[i_2_] = i_3_;
																											anInt994 += i_3_;
																										}
																									} else if (opcode == 53)
																										aBoolean978 = false;
																								} else
																									anInt962 = buffer
																											.getShort() & 0xFFFF;
																							} else
																								anInt990 = buffer
																										.getShort() & 0xFFFF;
																						} else
																							anInt952 = buffer
																									.getShort() & 0xFFFF;
																					} else
																						anInt983 = buffer
																								.getShort() & 0xFFFF;
																				} else
																					anInt955 = buffer
																							.getShort() & 0xFFFF;
																			} else
																				turnCCWAnimation = buffer
																						.getShort() & 0xFFFF;
																		} else
																			turn180Animation = buffer
																					.getShort() & 0xFFFF;
																	} else
																		anInt954 = buffer
																				.getShort() & 0xFFFF;
																} else
																	anInt958 = (buffer
																			.getShort() & 0xFFFF);
															} else
																anInt951 = (buffer
																		.get() & 0xFF);
														} else
															anInt965 = (buffer
																	.getShort());
													} else
														anInt969 = (buffer
																.getShort() & 0xFFFF);
												} else
													anInt993 = buffer
															.get() & 0xFF;
											} else
												anInt956 = (buffer.getShort());
										} else
											anInt961 = buffer
													.getShort() & 0xFFFF;
									} else
										anInt988 = buffer.get() & 0xFF;
								} else
									anInt980 = buffer.getShort() & 0xFFFF;
							} else
								anInt992 = buffer.get() & 0xFF;
						} else
							runAnimationId = buffer.getShort() & 0xFFFF;
					} else
						anInt986 = buffer.getShort() & 0xFFFF;
				} else
					anInt987 = buffer.getShort() & 0xFFFF;
			} else
				anInt974 = buffer.getShort() & 0xFFFF;
		} else {
			standAnimationId = buffer.getShort() & 0xFFFF;
			walkAnimationId = buffer.getShort() & 0xFFFF;
			if ((standAnimationId ^ 0xffffffff) == -65536)
				standAnimationId = -1;
			if ((walkAnimationId ^ 0xffffffff) == -65536)
				walkAnimationId = -1;
		}
	}

	public RenderAnimationDefinition() {
		anInt957 = -1;
		anInt954 = -1;
		anInt960 = -1;
		anInt958 = -1;
		anInt965 = 0;
		anInt973 = 0;
		turn180Animation = -1;
		anInt956 = 0;
		standAnimationId = -1;
		standAnimationIds = null;
		anInt952 = -1;
		anInt983 = -1;
		anInt985 = -1;
		anInt962 = -1;
		anInt966 = -1;
		anInt977 = -1;
		anInt975 = 0;
		runAnimationId = -1;
		anInt988 = 0;
		turnCCWAnimation = -1;
		anInt987 = -1;
		anInt980 = 0;
		anInt964 = -1;
		walkAnimationId = -1;
		anInt986 = -1;
		aBoolean978 = true;
		anInt992 = 0;
		anInt955 = -1;
		anInt989 = -1;
		anInt974 = -1;
		anInt969 = 0;
		anInt994 = 0;
		anInt990 = -1;
		anInt993 = 0;
	}

	public static void main(String...args) throws Throwable {
		GameWorld.prompt(false);
		RenderAnimationDefinition def = RenderAnimationDefinition.forId(1426);
		System.out.println("size: " + def.getClass().getDeclaredFields().length);
		for (Field f : def.getClass().getDeclaredFields()) {
			if (!Modifier.isStatic(f.getModifiers())) {
				if (f.getType().isArray()) {
					Object object = f.get(def);
					if (object != null) {
						int length = Array.getLength(object);
						System.out.print(f.getName() + ", [");
						for (int i = 0; i < length; i++) {
							System.out.print(Array.get(object, i) + (i < (length - 1) ? ", " : "]"));
						}
						continue;
					}
				}
				System.out.println(f.getName() + ", " + f.get(def));
			}
		}
		for (Field f : def.getClass().getSuperclass().getDeclaredFields()) {
			if (!Modifier.isStatic(f.getModifiers())) {
				if (f.getType().isArray()) {
					Object object = f.get(def);
					if (object != null) {
						int length = Array.getLength(object);
						System.out.print(f.getName() + ", [");
						for (int i = 0; i < length; i++) {
							System.out.print(Array.get(object, i) + (i < (length - 1) ? ", " : "]"));
						}
						continue;
					}
				}
				System.out.println(f.getName() + ", " + f.get(def));
			}
		}
		//Link arios editor source to this project on eclipse!
//		org.arioseditor.workspace.WorkSpace.getWorkSpace().getSettings().setCachePath(System.getProperty("user.home") + "/Dropbox/Arios V2/Source/data/cache");
//		org.arioseditor.workspace.WorkSpace.getWorkSpace().getSettings().setStorePath(System.getProperty("user.home") + "/Dropbox/Arios V2/Arios 530/data/store");
//		org.arioseditor.workspace.WorkSpace.getWorkSpace().init();
//		
//		roar:
//		for (int itemId = 0; itemId < ItemDefinition.getDefinitions().size(); itemId++) {
//			Item item = (Item) EditorType.ITEM.getTab().getNodes().get(itemId);
//			if (item == null) {
//				continue;
//			}
//			Integer standAnimation = (Integer) item.getConfigValue(ItemConfiguration.STAND_ANIM);
//			Integer walkAnimation = (Integer) item.getConfigValue(ItemConfiguration.WALK_ANIM);
//			Integer runAnimation = (Integer) item.getConfigValue(ItemConfiguration.RUN_ANIM);
//			if (standAnimation != null) {
//				for (int id = 0; id < 1431; id++) {
//					RenderAnimationDefinition def = RenderAnimationDefinition.forId(id);
//					if (def.walkAnimationId == walkAnimation && def.runAnimationId == runAnimation && def.standAnimationId == standAnimation) {
//						if (id != 1428) {
//							System.out.println("Item " + itemId + " has render animation " + id + "!");
//							item.setConfig("render_anim", (short) id);
//						}
//						continue roar;
//					}
//				}
//				item.setConfig("render_anim", (short) 1426);
//				System.out.println("Could not find render animation for item " + itemId + "!");
//			}
//		}
//		EditorType.ITEM.getTab().preSave();
//		EditorType.ITEM.getTab().save();
//		WorkSpace.getWorkSpace().save(true);
//		System.out.println("Done!");
//		System.exit(0);
	}
}
