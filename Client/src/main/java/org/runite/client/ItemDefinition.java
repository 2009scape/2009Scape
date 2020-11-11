package org.runite.client;
import org.rs09.client.Linkable;
import org.rs09.client.data.HashTable;
import org.rs09.client.LinkableInt;

import java.awt.Component;
import java.util.Objects;

final class ItemDefinition {

    static short aShort505 = 1;
	static RSString[] stringsStack = new RSString[1000];
	static int[] intsStack = new int[1000];
	static int scriptHeapCounter = 0;
	// This holds all the int memory that can be accessed by the pseudo assembly by value.
	static int[] ram = new int[2500];
	// This holds even MORE memory which has five unique pages. It is slower (presumably) than regular ram
	// and can be accessed through opcodes 44-46. You can fill it with a given value
	// in a single instruction though
	static int[][] pagedRam = new int[5][5000];
	// This shows how much of the pagedRam is actually filled for any given page.
	static int[] pagedRamPageSize = new int[5];

	static RSString[] stringArguments;
	static int[] intArguments;
	// This is the TRUE stack. the one with a stack pointer and stack tracing.
	// This holds all the methods you are currently calling in your script.
	static AssembledMethodContainer[] methodStack = new AssembledMethodContainer[50];
	private short[] aShortArray751;
	private int anInt752;
	private int anInt753 = -1;
	int anInt754;
	private int anInt755;
	int anInt756 = -1;
	int value = 1;
	int anInt758;
	private int wornModelPositionX = 0;
	int anInt761;
	int anInt762;
	int stackingType;
	private short[] aShortArray765;
	int[] anIntArray766;
	int anInt767;
	int anInt768;
	private int anInt769;
	RSString name;
	private int anInt771;
	private short[] aShortArray772;
	private int anInt773 = -1;
	private short[] aShortArray774;
	private int wornModelPositionY;
	private int anInt776 = -1;
	private int anInt777;
	private int wornModelPositionZ;
	boolean membersItem;
	private int anInt780;
	static int[] anIntArray781 = new int[99];
	int teamId = 0;
	RSString[] inventoryOptions;
	private int anInt784;
	private byte[] aByteArray785;
	int anInt786;
	int itemId;
	int anInt788;
	int anInt789;
	private int anInt790;
	int anInt791;
	int anInt792;
	int anInt793;
	private int anInt794;
	int anInt795;
	private int anInt796;
	private int anInt797;
	HashTable aHashTable_798;
	int anInt799;
	int anInt800;
	RSString[] groundOptions;
	private int anInt802;
	private int anInt803;
	int[] anIntArray804;
	private int anInt805;
	boolean aBoolean807;

	int anInt810;
	private static RSString aClass94_811;

	boolean itemContextMenuDebug = true;

	static boolean method1176(RSString var0) {
	   try {
		  if(var0 == null) {
			 return false;
		  } else {
			 for(int var2 = 0; Class8.anInt104 > var2; ++var2) {
				if(var0.equalsStringIgnoreCase(Class70.aClass94Array1046[var2])) {
				   return true;
				}
			 }


			 return var0.equalsStringIgnoreCase(Class102.player.displayName);
		  }
	   } catch (RuntimeException var3) {
		  throw ClientErrorException.clientError(var3, "hj.A(" + "{...}" + ',' + (byte) -82 + ')');
	   }
	}

	static AssembledMethod getMethodByID(int methodID) {
	   try {
	   	   // Load and return method from cache
		   AssembledMethod var2 = (AssembledMethod)Class56.aClass47_885.get((long)methodID);
		   if (var2 != null) {
			  return var2;
		   }
		   // Load method from file 0 which holds all the methods. Apparently.
		   byte[] var3 = CacheIndex.interfaceScriptsIndex.getFile(methodID, 0);
		   if(var3 == null) {
			  return null;
		   } else {
			  var2 = new AssembledMethod();

			  DataBuffer var4 = new DataBuffer(var3);
			  var4.index = -2 + var4.buffer.length;
			  int var5 = var4.readUnsignedShort();
			  int var6 = -12 + var4.buffer.length + -2 - var5;
			  var4.index = var6;
			  int var7 = var4.readInt();
			  var2.numberOfIntsToCopy = var4.readUnsignedShort();
			  var2.numberOfRSStringsToCopy = var4.readUnsignedShort();
			  var2.numberOfIntArguments = var4.readUnsignedShort();
			  var2.numberOfStringArguments = var4.readUnsignedShort();
			  int var8 = var4.readUnsignedByte();
			  int var9;
			  int var10;
			  if(var8 > 0) {
				 var2.switchHashTable = new HashTable[var8];

				 for(var9 = 0; var9 < var8; ++var9) {
					var10 = var4.readUnsignedShort();
					HashTable var11 = new HashTable(Class95.method1585((byte)119, var10));
					var2.switchHashTable[var9] = var11;

					while(var10-- > 0) {
					   int var12 = var4.readInt();
					   int var13 = var4.readInt();
					   var11.put((long)var12, new LinkableInt(var13));
					}
				 }
			  }

			  var4.index = 0;
			  var4.method750();
			  var2.assemblyInstructions = new int[var7];
			  var2.stringInstructionOperands = new RSString[var7];
			  var9 = 0;

			  for(var2.instructionOperands = new int[var7]; var4.index < var6; var2.assemblyInstructions[var9++] = var10) {
				 var10 = var4.readUnsignedShort();
				 if(var10 == 3) {
					var2.stringInstructionOperands[var9] = var4.readString();
				 } else if (var10 < 100 && 21 != var10 && var10 != 38 && 39 != var10) {
					var2.instructionOperands[var9] = var4.readInt();
				 } else {
					var2.instructionOperands[var9] = var4.readUnsignedByte();
				 }
			  }
			  Class56.aClass47_885.put((long)methodID, var2);
			  return var2;
		   }
	   } catch (RuntimeException var14) {
		  throw ClientErrorException.clientError(var14, "hc.O(" + methodID + ',' + (byte) -91 + ')');
	   }
	}


	final boolean method1102(boolean var1) {
		try {
			int var3 = this.anInt803;
			int var4 = this.anInt796;
			if(var1) {
				var3 = this.anInt773;
				var4 = this.anInt753;
			}

			if(var3 == -1) {
				return true;
			} else {
				boolean var5 = true;
				if(!LinkableRSString.aClass153_2581.method2129((byte)-75, 0, var3)) {
					var5 = false;
				}

				if(var4 != -1 && !LinkableRSString.aClass153_2581.method2129((byte)58, 0, var4)) {
					var5 = false;
				}

				return var5;
			}
		} catch (RuntimeException var6) {
			throw ClientErrorException.clientError(var6, "h.G(" + var1 + ',' + false + ')');
		}
	}

	static void method1103(CacheIndex var0, CacheIndex var1) {
		try {
			NPCDefinition.aClass153_3173 = var0;
			Class29.aClass153_557 = var1;
		} catch (RuntimeException var4) {
			throw ClientErrorException.clientError(var4, "h.B(" + (var0 != null?"{...}":"null") + ',' + (var1 != null?"{...}":"null") + ',' + false + ')');
		}
	}

	public RSString method1105(RSString var2, int var3) {//private -> public to move runAssembledScript into CS2Script
		try {
			if(this.aHashTable_798 == null) {
				return var2;
			} else {

				LinkableRSString var4 = (LinkableRSString)this.aHashTable_798.get((long)var3);
				return null != var4?var4.value :var2;
			}
		} catch (RuntimeException var5) {
			throw ClientErrorException.clientError(var5, "h.S(" + 107 + ',' + (var2 != null?"{...}":"null") + ',' + var3 + ')');
		}
	}


	final ItemDefinition method1106(int var1) {
		try {
			if(this.anIntArray804 != null && var1 > 1) {
				int var4 = -1;

				for(int var5 = 0; 10 > var5; ++var5) {
					if(this.anIntArray766[var5] <= var1 && this.anIntArray766[var5] != 0) {
						var4 = this.anIntArray804[var5];
					}
				}

				if(var4 != -1) {
					return Class38.getItemDefinition(var4);
				}
			}

			return this;
		} catch (RuntimeException var6) {
			throw ClientErrorException.clientError(var6, "h.H(" + var1 + ',' + 78 + ')');
		}
	}

	static WorldListEntry method1107(int var0) {
		try {
			if(Unsorted.aClass44_Sub1Array3201.length > Class3_Sub6.anInt2291) {
				return Unsorted.aClass44_Sub1Array3201[Class3_Sub6.anInt2291++];
			} else {
				if(var0 != 5422) {
					method1107(-66);
				}

				return null;
			}
		} catch (RuntimeException var2) {
			throw ClientErrorException.clientError(var2, "h.R(" + var0 + ')');
		}
	}

	final boolean method1108(boolean var2) {
		try {
			int var4 = this.anInt771;
			int var3 = this.anInt793;
			int var5 = this.anInt769;
			if(var2) {
				var5 = this.anInt776;
				var3 = this.anInt761;
				var4 = this.anInt794;
			}

			if(var3 == -1) {
				return true;
			} else {
				boolean var7 = true;
				if(!LinkableRSString.aClass153_2581.method2129((byte)-90, 0, var3)) {
					var7 = false;
				}

				if(var4 != -1 && !LinkableRSString.aClass153_2581.method2129((byte)-114, 0, var4)) {
					var7 = false;
				}

				if(-1 != var5 && !LinkableRSString.aClass153_2581.method2129((byte)83, 0, var5)) {
					var7 = false;
				}

				return var7;
			}
		} catch (RuntimeException var8) {
			throw ClientErrorException.clientError(var8, "h.C(" + (byte) 95 + ',' + var2 + ')');
		}
	}

	final void method1109(ItemDefinition var2, ItemDefinition var3) {
		try {
			this.aByteArray785 = var2.aByteArray785;
			this.wornModelPositionZ = var2.wornModelPositionZ;
			this.aHashTable_798 = var2.aHashTable_798;
			this.anInt769 = var2.anInt769;
			this.anInt761 = var2.anInt761;
			this.wornModelPositionY = var2.wornModelPositionY;
			this.inventoryOptions = new RSString[5];
			this.anInt755 = var3.anInt755;
			this.anInt810 = var3.anInt810;
			this.value = 0;
			this.teamId = var2.teamId;
			this.anInt773 = var2.anInt773;
			this.aShortArray774 = var2.aShortArray774;
			this.anInt768 = var3.anInt768;
			this.anInt771 = var2.anInt771;
			this.anInt799 = var3.anInt799;
			this.anInt803 = var2.anInt803;
			this.anInt796 = var2.anInt796;
			this.wornModelPositionX = var2.wornModelPositionX;
			this.anInt786 = var3.anInt786;
			this.anInt754 = var3.anInt754;
			this.anInt753 = var2.anInt753;
			this.anInt777 = var2.anInt777;
			this.aShortArray772 = var2.aShortArray772;
			this.anInt802 = var2.anInt802;
			this.anInt752 = var2.anInt752;
			this.anInt792 = var3.anInt792;

			this.anInt793 = var2.anInt793;
			this.anInt794 = var2.anInt794;
			this.name = var2.name;
			this.aShortArray751 = var2.aShortArray751;
			this.aShortArray765 = var2.aShortArray765;
			this.groundOptions = var2.groundOptions;
			this.membersItem = var2.membersItem;
			this.anInt776 = var2.anInt776;
			if(null != var2.inventoryOptions) {
				System.arraycopy(var2.inventoryOptions, 0, this.inventoryOptions, 0, 4);
			}

			this.inventoryOptions[4] = TextCore.HasDiscard;
		} catch (RuntimeException var5) {
			throw ClientErrorException.clientError(var5, "h.J(" + (byte) 69 + ',' + (var2 != null?"{...}":"null") + ',' + (var3 != null?"{...}":"null") + ')');
		}
	}

	final Model method1110(int var2, int var3, AnimationDefinition var4, int var5, int var6) {
		try {
			if(this.anIntArray804 != null && var5 > 1) {
				int var7 = -1;

				for(int var8 = 0; var8 < 10; ++var8) {
					if(this.anIntArray766[var8] <= var5 && this.anIntArray766[var8] != 0) {
						var7 = this.anIntArray804[var8];
					}
				}

				if(var7 != -1) {
					return Class38.getItemDefinition(var7).method1110(var2, var3, var4, 1, var6);
				}
			}

			Model var11 = (Model)Class143.aReferenceCache_1874.get((long)this.itemId);
			if(var11 == null) {
				Model_Sub1 var12 = Model_Sub1.method2015(LinkableRSString.aClass153_2581, this.anInt755);
				if(null == var12) {
					return null;
				}

				int var9;
				if(null != this.aShortArray774) {
					for(var9 = 0; this.aShortArray774.length > var9; ++var9) {
						if(null != this.aByteArray785 && this.aByteArray785.length > var9) {
							var12.method2016(this.aShortArray774[var9], Class3_Sub13_Sub38.aShortArray3453[this.aByteArray785[var9] & 255]);
						} else {
							var12.method2016(this.aShortArray774[var9], this.aShortArray772[var9]);
						}
					}
				}

				if(this.aShortArray765 != null) {
					for(var9 = 0; var9 < this.aShortArray765.length; ++var9) {
						var12.method1998(this.aShortArray765[var9], this.aShortArray751[var9]);
					}
				}

				var11 = var12.method2008(this.anInt784 + 64, 768 + this.anInt790, -50, -10, -50);
				if(this.anInt805 != 128 || this.anInt780 != 128 || this.anInt797 != 128) {
					var11.resize(this.anInt805, this.anInt780, this.anInt797);
				}

				var11.aBoolean2699 = true;
				if(HDToolKit.highDetail) {
					((Class140_Sub1_Sub1)var11).method1920(false, false, false, false, false, true);
				}

				Class143.aReferenceCache_1874.put(var11, (long)this.itemId);
			}

			if(var4 != null) {
				var11 = var4.method2055(var11, (byte)-88, var2, var3, var6);
			}

			return var11;
		} catch (RuntimeException var10) {
			throw ClientErrorException.clientError(var10, "h.E(" + var2 + ',' + var3 + ',' + (var4 != null?"{...}":"null") + ',' + var5 + ',' + var6 + ')');
		}
	}

	final void method1112() {
		try {

		} catch (RuntimeException var3) {
			throw ClientErrorException.clientError(var3, "h.O(" + 5401 + ')');
		}
	}

	final void parseDefinitions(DataBuffer buffer) {
		try {
			while(true) {
				int opcode = buffer.readUnsignedByte();
				if(0 == opcode) {

					return;
				}

				this.parseOpcode(buffer, opcode);
			}
		} catch (RuntimeException var4) {
			throw ClientErrorException.clientError(var4, "h.M(" + 1 + ',' + (buffer != null?"{...}":"null") + ')');
		}
	}

	private void parseOpcode(DataBuffer buffer, int opcode) {
		try {
			if(opcode == 1) {
				this.anInt755 = buffer.readUnsignedShort();
			} else if (opcode == 2) {
				this.name = buffer.readString();
			} else if (opcode == 4) {
				this.anInt810 = buffer.readUnsignedShort();
			} else if (opcode == 5) {
				this.anInt786 = buffer.readUnsignedShort();
			} else if (opcode == 6) {
				this.anInt799 = buffer.readUnsignedShort();
			} else if (opcode == 7) {
				this.anInt792 = buffer.readUnsignedShort();
				if (this.anInt792 > 32767) {
					this.anInt792 -= 65536;
				}
			} else if (opcode == 8) {
				this.anInt754 = buffer.readUnsignedShort();
				if (this.anInt754 > 32767) {
					this.anInt754 -= 65536;
				}
			} else if (opcode == 11) {
				this.stackingType = 1;
			} else if (opcode == 12) {
				this.value = buffer.readInt();
			} else if (opcode == 16) {
				this.membersItem = true;
			} else if (23 == opcode) {
				this.anInt793 = buffer.readUnsignedShort();
			} else if (opcode == 24) {
				this.anInt771 = buffer.readUnsignedShort();
			} else if (opcode == 25) {
				this.anInt761 = buffer.readUnsignedShort();
			} else if (opcode == 26) {
				this.anInt794 = buffer.readUnsignedShort();
			} else if (opcode >= 30 && opcode < 35) {
				this.groundOptions[-30 + opcode] = buffer.readString();
				if (this.groundOptions[opcode + -30].equalsStringIgnoreCase(TextCore.HasHidden)) {
					this.groundOptions[-30 + opcode] = null;
				}
			} else if (35 <= opcode && 40 > opcode) {
				this.inventoryOptions[-35 + opcode] = buffer.readString();
			} else {
				int var5;
				int var6;
				if (opcode == 40) {
					var5 = buffer.readUnsignedByte();
					this.aShortArray772 = new short[var5];
					this.aShortArray774 = new short[var5];

					for (var6 = 0; var5 > var6; ++var6) {
						this.aShortArray774[var6] = (short) buffer.readUnsignedShort();
						this.aShortArray772[var6] = (short) buffer.readUnsignedShort();
					}
				} else if (opcode == 41) {
					var5 = buffer.readUnsignedByte();
					this.aShortArray751 = new short[var5];
					this.aShortArray765 = new short[var5];

					for (var6 = 0; var6 < var5; ++var6) {
						this.aShortArray765[var6] = (short) buffer.readUnsignedShort();
						this.aShortArray751[var6] = (short) buffer.readUnsignedShort();
					}
				} else if (42 == opcode) {
					var5 = buffer.readUnsignedByte();
					this.aByteArray785 = new byte[var5];

					for (var6 = 0; var5 > var6; ++var6) {
						this.aByteArray785[var6] = buffer.readSignedByte();
					}
				} else if (opcode == 65) {
					this.aBoolean807 = true;
				} else if (opcode == 78) {
					this.anInt769 = buffer.readUnsignedShort();
				} else if (opcode == 79) {
					this.anInt776 = buffer.readUnsignedShort();
				} else if (90 == opcode) {
					this.anInt803 = buffer.readUnsignedShort();
				} else if (opcode == 91) {
					this.anInt773 = buffer.readUnsignedShort();
				} else if (opcode == 92) {
					this.anInt796 = buffer.readUnsignedShort();
				} else if (opcode == 93) {
					this.anInt753 = buffer.readUnsignedShort();
				} else if (opcode == 95) {
					this.anInt768 = buffer.readUnsignedShort();
				} else if (opcode == 96) {
					this.anInt800 = buffer.readUnsignedByte();
				} else if (opcode == 97) {
					this.anInt789 = buffer.readUnsignedShort();
				} else if (opcode == 98) {
					this.anInt791 = buffer.readUnsignedShort();
				} else if (opcode >= 100 && opcode < 110) {
					if (null == this.anIntArray804) {
						this.anIntArray804 = new int[10];
						this.anIntArray766 = new int[10];
					}

					this.anIntArray804[-100 + opcode] = buffer.readUnsignedShort();
					this.anIntArray766[opcode + -100] = buffer.readUnsignedShort();
				} else if (110 == opcode) {
					this.anInt805 = buffer.readUnsignedShort();
				} else if (opcode == 111) {
					this.anInt780 = buffer.readUnsignedShort();
				} else if (opcode == 112) {
					this.anInt797 = buffer.readUnsignedShort();
				} else if (opcode == 113) {
					this.anInt784 = buffer.readSignedByte();
				} else if (opcode == 114) {
					this.anInt790 = 5 * buffer.readSignedByte();
				} else if (opcode == 115) {
					this.teamId = buffer.readUnsignedByte();
				} else if (opcode == 121) {
					this.anInt795 = buffer.readUnsignedShort();
				} else if (opcode == 122) {
					this.anInt762 = buffer.readUnsignedShort();
				} else if (125 == opcode) {
					this.wornModelPositionX = buffer.readSignedByte();
					this.wornModelPositionZ = buffer.readSignedByte();
					this.wornModelPositionY = buffer.readSignedByte();
				} else if (opcode == 126) {
					this.anInt777 = buffer.readSignedByte();
					this.anInt802 = buffer.readSignedByte();
					this.anInt752 = buffer.readSignedByte();
				} else if (opcode == 127) {
					this.anInt767 = buffer.readUnsignedByte();
					this.anInt758 = buffer.readUnsignedShort();
				} else if (opcode == 128) {
					this.anInt788 = buffer.readUnsignedByte();
					this.anInt756 = buffer.readUnsignedShort();
				} else if (opcode == 129) {
					buffer.readUnsignedByte();
					buffer.readUnsignedShort();
				} else if (opcode == 130) {
					buffer.readUnsignedByte();
					buffer.readUnsignedShort();
				} else if (249 == opcode) {
					var5 = buffer.readUnsignedByte();
					if (null == this.aHashTable_798) {
						var6 = Class95.method1585((byte) 97, var5);
						this.aHashTable_798 = new HashTable(var6);
					}

					for (var6 = 0; var6 < var5; ++var6) {
						boolean var7 = buffer.readUnsignedByte() == 1;
						int var8 = buffer.readMedium();
						Object var9;
						if (var7) {
							var9 = new LinkableRSString(buffer.readString());
						} else {
							var9 = new LinkableInt(buffer.readInt());
						}

						this.aHashTable_798.put((long) var8, (Linkable) var9);
					}
				}
			}

		} catch (RuntimeException var10) {
			throw ClientErrorException.clientError(var10, "h.Q(" + (byte) -72 + ',' + (buffer != null?"{...}":"null") + ',' + opcode + ')');
		}
	}

	final int method1115(int var1, int var2, int var3) {
		try {
			if(this.aHashTable_798 == null) {
				return var1;
			} else {
				LinkableInt var5 = (LinkableInt)this.aHashTable_798.get((long)var3);
				return null != var5?var5.value :var1;
			}
		} catch (RuntimeException var6) {
			throw ClientErrorException.clientError(var6, "h.I(" + var1 + ',' + var2 + ',' + var3 + ')');
		}
	}

	final Model_Sub1 method1116(boolean var1) {
		try {
			int var4 = this.anInt796;
			int var3 = this.anInt803;
			if(var1) {
				var4 = this.anInt753;
				var3 = this.anInt773;
			}

			if(-1 == var3) {
				return null;
			} else {
				Model_Sub1 var5 = Model_Sub1.method2015(LinkableRSString.aClass153_2581, var3);
				if(-1 != var4) {
					Model_Sub1 var6 = Model_Sub1.method2015(LinkableRSString.aClass153_2581, var4);
					Model_Sub1[] var7 = new Model_Sub1[]{var5, var6};
					var5 = new Model_Sub1(var7, 2);
				}

				int var9;
				if(this.aShortArray774 != null) {
					for(var9 = 0; var9 < this.aShortArray774.length; ++var9) {
						Objects.requireNonNull(var5).method2016(this.aShortArray774[var9], this.aShortArray772[var9]);
					}
				}

				if(this.aShortArray765 != null) {
					for(var9 = 0; var9 < this.aShortArray765.length; ++var9) {
						Objects.requireNonNull(var5).method1998(this.aShortArray765[var9], this.aShortArray751[var9]);
					}
				}

				return var5;
			}
		} catch (RuntimeException var8) {
			throw ClientErrorException.clientError(var8, "h.A(" + var1 + ',' + (byte) -109 + ')');
		}
	}

	final Model_Sub1 method1117(boolean var1) {
		try {
			int var3 = this.anInt793;

			int var4 = this.anInt771;
			int var5 = this.anInt769;
			if(var1) {
				var5 = this.anInt776;
				var3 = this.anInt761;
				var4 = this.anInt794;
			}

			if(var3 == -1) {
				return null;
			} else {
				Model_Sub1 var6 = Model_Sub1.method2015(LinkableRSString.aClass153_2581, var3);
				if(var4 != -1) {
					Model_Sub1 var7 = Model_Sub1.method2015(LinkableRSString.aClass153_2581, var4);
					if(-1 == var5) {
						Model_Sub1[] var8 = new Model_Sub1[]{var6, var7};
						var6 = new Model_Sub1(var8, 2);
					} else {
						Model_Sub1 var12 = Model_Sub1.method2015(LinkableRSString.aClass153_2581, var5);
						Model_Sub1[] var9 = new Model_Sub1[]{var6, var7, var12};
						var6 = new Model_Sub1(var9, 3);
					}
				}

				//            System.out.println(var1 + " - ");
				//            this.wornModelPositionX = -1;
				//            this.wornModelPositionZ = 13;//-1;//20;
				//            this.wornModelPositionY = -1;
				if(!var1 && (this.wornModelPositionX != 0 || this.wornModelPositionZ != 0 || this.wornModelPositionY != 0)) {
					Objects.requireNonNull(var6).method2001(this.wornModelPositionX, this.wornModelPositionZ, this.wornModelPositionY);
				}
				if(var1 && (this.anInt777 != 0 || this.anInt802 != 0 || this.anInt752 != 0)) {
					Objects.requireNonNull(var6).method2001(this.anInt777, this.anInt802, this.anInt752);
				}

				int var11;
				if(this.aShortArray774 != null) {
					for(var11 = 0; var11 < this.aShortArray774.length; ++var11) {
						Objects.requireNonNull(var6).method2016(this.aShortArray774[var11], this.aShortArray772[var11]);
					}
				}

				if(this.aShortArray765 != null) {
					for(var11 = 0; var11 < this.aShortArray765.length; ++var11) {
						Objects.requireNonNull(var6).method1998(this.aShortArray765[var11], this.aShortArray751[var11]);
					}
				}

				return var6;
			}
		} catch (RuntimeException var10) {
			throw ClientErrorException.clientError(var10, "h.D(" + var1 + ',' + 80 + ')');
		}
	}

	final void method1118(ItemDefinition var1, ItemDefinition var2) {
		try {
			this.name = var1.name;
			this.anInt810 = var2.anInt810;

			this.aShortArray774 = var2.aShortArray774;
			this.aShortArray772 = var2.aShortArray772;
			this.anInt786 = var2.anInt786;
			this.anInt799 = var2.anInt799;
			this.aShortArray751 = var2.aShortArray751;
			this.anInt755 = var2.anInt755;
			this.aByteArray785 = var2.aByteArray785;
			this.anInt768 = var2.anInt768;
			this.value = var1.value;
			this.stackingType = 1;
			this.anInt754 = var2.anInt754;
			this.anInt792 = var2.anInt792;
			this.aShortArray765 = var2.aShortArray765;
			this.membersItem = var1.membersItem;
		} catch (RuntimeException var5) {
			throw ClientErrorException.clientError(var5, "h.N(" + (var1 != null?"{...}":"null") + ',' + (var2 != null?"{...}":"null") + ',' + false + ')');
		}
	}

	static void method1119(Component var0, boolean var1) {
		try {
			var0.addMouseListener(Unsorted.aClass149_4047);
			if(var1) {
				aClass94_811 = (RSString)null;
			}

			var0.addMouseMotionListener(Unsorted.aClass149_4047);
			var0.addFocusListener(Unsorted.aClass149_4047);
		} catch (RuntimeException var3) {
			throw ClientErrorException.clientError(var3, "h.K(" + (var0 != null?"{...}":"null") + ',' + var1 + ')');
		}
	}

	final Class140_Sub1_Sub2 method1120() {
		try {
			Model_Sub1 var2 = Model_Sub1.method2015(LinkableRSString.aClass153_2581, this.anInt755);
			if(var2 == null) {
				return null;
			} else {
				int var3;
				if(this.aShortArray774 != null) {
					for(var3 = 0; this.aShortArray774.length > var3; ++var3) {
						if(null != this.aByteArray785 && this.aByteArray785.length > var3) {
							var2.method2016(this.aShortArray774[var3], Class3_Sub13_Sub38.aShortArray3453[this.aByteArray785[var3] & 255]);
						} else {
							var2.method2016(this.aShortArray774[var3], this.aShortArray772[var3]);
						}
					}
				}

				if(this.aShortArray765 != null) {
					for(var3 = 0; var3 < this.aShortArray765.length; ++var3) {
						var2.method1998(this.aShortArray765[var3], this.aShortArray751[var3]);
					}
				}

				Class140_Sub1_Sub2 var5 = var2.method2000(64 - -this.anInt784, 768 - -this.anInt790);

				if(this.anInt805 != 128 || this.anInt780 != 128 || this.anInt797 != 128) {
					var5.resize(this.anInt805, this.anInt780, this.anInt797);
				}

				return var5;
			}
		} catch (RuntimeException var4) {
			throw ClientErrorException.clientError(var4, "h.L(" + 18206 + ')');
		}
	}

	public ItemDefinition() {
		this.name = Class40.aClass94_672;
		this.wornModelPositionY = 0;
		this.anInt784 = 0;
		this.anInt769 = -1;
		this.anInt796 = -1;
		this.anInt791 = -1;
		this.anInt777 = 0;
		this.anInt780 = 128;
		this.anInt767 = -1;
		this.anInt758 = -1;
		this.anInt768 = 0;
		this.anInt762 = -1;
		this.anInt795 = -1;
		this.anInt761 = -1;
		this.anInt771 = -1;
		this.anInt754 = 0;
		this.anInt786 = 0;
		this.anInt799 = 0;
		this.anInt800 = 0;
		this.stackingType = 0;
		this.anInt789 = -1;
		this.anInt794 = -1;
		this.anInt788 = -1;
		this.anInt797 = 128;
		this.membersItem = false;
		this.anInt752 = 0;
		this.anInt792 = 0;
		this.anInt803 = -1;
		this.anInt802 = 0;
		this.anInt793 = -1;
		this.groundOptions = new RSString[]{null, null, TextCore.HasTake, null, null};
		this.anInt805 = 128;
		this.anInt790 = 0;
		this.wornModelPositionZ = 0;
		this.inventoryOptions = new RSString[]{null, null, null, null, TextCore.HasDrop};
		this.anInt810 = 2000;
		this.aBoolean807 = false;
	}

	static {
		int var0 = 0;

		for(int var1 = 0; var1 < 99; ++var1) {
			int var2 = 1 + var1;
			int var3 = (int)(Math.pow(2.0D, (double)var2 / 7.0D) * 300.0D + (double)var2);
			var0 += var3;
			int var4 = var0 / 4;
			anIntArray781[var1] = var4;
		}
		aClass94_811 = RSString.parse("green:");
	}
}
