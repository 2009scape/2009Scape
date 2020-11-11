package org.runite.client;

import java.util.Objects;

final class NPC extends Class140_Sub4 {

	static boolean aBoolean3975 = false;
    static int[] npcSpawnCacheIndices;
    static NPC[] npcs = new NPC['\u8000'];
    static Class3_Sub27 aClass3_Sub27_1154;
    NPCDefinition definition;
	static AbstractSprite[] aAbstractSpriteArray3977;
	static float aFloat3979;
	static int[] anIntArray3986 = new int[32];
	static int anInt3995;
	static int[] anIntArray3997 = new int[]{19, 55, 38, 155, 255, 110, 137, 205, 76};
	static int anInt4001;

	static int method1984(int var0, int var1, int var2) {
		try {
			if(var1 == 38) {
				int var3 = 57 * var2 + var0;
				var3 ^= var3 << 13;
				int var4 = Integer.MAX_VALUE & 1376312589 + (var3 * var3 * 15731 - -789221) * var3;
				return (var4 & 133802063) >> 19;
			} else {
				return 88;
			}
		} catch (RuntimeException var5) {
			throw ClientErrorException.clientError(var5, "km.S(" + var0 + ',' + var1 + ',' + var2 + ')');
		}
	}

    static void renderNPCMasks(int var0) {
       try {
          int i;
          for(i = 0; Unsorted.maskUpdateCount > i; ++i) {
             int mask = Class21.maskUpdateIndexes[i];
             NPC npc = npcs[mask];
             int var4 = GraphicDefinition.incomingBuffer.readUnsignedByte();
             if((var4 & 8) != 0) {
                var4 += GraphicDefinition.incomingBuffer.readUnsignedByte() << 8;
             }

             int var5;
             int var6;
             //Ordinal: 0 Hit
             if((64 & var4) != 0) {
                var5 = GraphicDefinition.incomingBuffer.readUnsignedByte();
                var6 = GraphicDefinition.incomingBuffer.readUnsignedNegativeByte();
                npc.method1970(var6, Class44.anInt719, var5);
                npc.anInt2781 = 300 + Class44.anInt719;
                npc.anInt2775 = GraphicDefinition.incomingBuffer.readUnsigned128Byte();
             }

             //Ordinal: 1 Hit 2
             if((var4 & 2) != 0) {
                var5 = GraphicDefinition.incomingBuffer.readUnsignedNegativeByte();
                var6 = GraphicDefinition.incomingBuffer.readUnsigned128Byte();
                npc.method1970(var6, Class44.anInt719, var5);
             }

             //Ordinal: 2 Animation
             if((var4 & 16) != 0) {
                var5 = GraphicDefinition.incomingBuffer.readUnsignedShort();
                var6 = GraphicDefinition.incomingBuffer.readUnsignedByte();
                if('\uffff' == var5) {
                   var5 = -1;
                }

                Unsorted.method1772(var6, var5, 39, npc);
             }

             //Ordinal: 3 Face entity
             if((var4 & 4) != 0) {
                npc.anInt2772 = GraphicDefinition.incomingBuffer.readUnsignedShort128();
                if(npc.anInt2772 == 65535) {
                   npc.anInt2772 = -1;
                }
             }

             //Ordinal: 4 Graphic
             if(0 != (var4 & 128)) {
                var5 = GraphicDefinition.incomingBuffer.readUnsignedShort128();
                if(var5 == '\uffff') {
                   var5 = -1;
                }

                var6 = GraphicDefinition.incomingBuffer.readIntLE();
                boolean var7 = true;
                if(var5 != -1 && npc.anInt2842 != -1 && Client.getAnimationDefinition(GraphicDefinition.getGraphicDefinition((byte)42, var5).anInt542).anInt1857 < Client.getAnimationDefinition(GraphicDefinition.getGraphicDefinition((byte)42, npc.anInt2842).anInt542).anInt1857) {
                   var7 = false;
                }

                if(var7) {
                   npc.anInt2842 = var5;
                   npc.anInt2759 = ('\uffff' & var6) + Class44.anInt719;
                   npc.anInt2761 = 0;
                   npc.anInt2805 = 0;
                   npc.anInt2799 = var6 >> 16;
                   npc.anInt2826 = 1;
                   if(npc.anInt2759 > Class44.anInt719) {
                      npc.anInt2805 = -1;
                   }

                   if(npc.anInt2842 != -1 && Class44.anInt719 == npc.anInt2759) {
                      int var8 = GraphicDefinition.getGraphicDefinition((byte)42, npc.anInt2842).anInt542;
                      if(var8 != -1) {
                         AnimationDefinition var9 = Client.getAnimationDefinition(var8);
                         if(var9.frames != null) {
                            Unsorted.method1470(npc.anInt2829, var9, 183921384, npc.anInt2819, false, 0);
                         }
                      }
                   }
                }
             }

             //Ordinal: 5 Transform
             if((1 & var4) != 0) {
                if(npc.definition.method1474()) {
                   Class3_Sub28_Sub8.method574(npc);
                }

                npc.setDefinitions(Unsorted.method522(GraphicDefinition.incomingBuffer.readUnsignedShortLE()));
                npc.setSize(npc.definition.size, 2);
                npc.renderAnimationId = npc.definition.renderAnimationId;
                if(npc.definition.method1474()) {
                   Class70.method1286(npc.anIntArray2755[0], (ObjectDefinition)null, 0, npc, npc.anIntArray2767[0], WorldListCountry.localPlane, (Player)null);
                }
             }

             //Ordinal: 6 Force chat
             if((var4 & 32) != 0) {
                npc.textSpoken = GraphicDefinition.incomingBuffer.readString();
                npc.textCycle = 100;
             }

             //Ordinal: 7
             if((256 & var4) != 0) {
                var5 = GraphicDefinition.incomingBuffer.readUnsignedNegativeByte();
                int[] var12 = new int[var5];
                int[] var13 = new int[var5];
                int[] var14 = new int[var5];

                for(int var15 = 0; var5 > var15; ++var15) {
                   int var10 = GraphicDefinition.incomingBuffer.readUnsignedShortLE();
                   if(var10 == '\uffff') {
                      var10 = -1;
                   }

                   var12[var15] = var10;
                   var13[var15] = GraphicDefinition.incomingBuffer.readUnsigned128Byte();
                   var14[var15] = GraphicDefinition.incomingBuffer.readUnsignedShort();
                }

                Class3_Sub13_Sub22.method273(var14, npc, var13, var12);
             }

             //Ordinal: 8 Face location
             if((var4 & 512) != 0) {
                npc.anInt2786 = GraphicDefinition.incomingBuffer.readUnsignedShort128();
                npc.anInt2762 = GraphicDefinition.incomingBuffer.readUnsignedShort();
             }
          }

       } catch (RuntimeException var11) {
          throw ClientErrorException.clientError(var11, "ta.M(" + var0 + ')');
       }
    }

    protected final void finalize() {}

	final int method1871() {
		try {
			return this.anInt2820;
		} catch (RuntimeException var2) {
			throw ClientErrorException.clientError(var2, "km.MA()");
		}
	}

	static byte[] method1985(Object var1, boolean var2) {
		try {
			if(var1 == null) {
				return null;
			} else if(var1 instanceof byte[]) {
				byte[] var5 = (byte[])((byte[])var1);
				return var2?Class12.method873(var5):var5;
			} else {
				if(var1 instanceof Class144) {
					Class144 var3 = (Class144)var1;
					return var3.getBytes();
				} else {
					throw new IllegalArgumentException();
				}
			}
		} catch (RuntimeException var4) {
			throw ClientErrorException.clientError(var4, "km.Q(" + "{...}" + ',' + var2 + ')');
		}
	}

	final void animate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, long var9, int var11, Class127_Sub1 var12) {
		try {
			if(this.definition != null) {
				AnimationDefinition var13 = this.anInt2771 != -1 && this.anInt2828 == 0 ?Client.getAnimationDefinition(this.anInt2771):null;
				AnimationDefinition var14 = -1 != this.anInt2764 && (this.method1965().anInt368 != this.anInt2764 || var13 == null)?Client.getAnimationDefinition(this.anInt2764):null;
				Model var15 = this.definition.method1476(this.aClass145Array2809, this.anInt2793, (byte)-116, this.anInt2813, this.anInt2776, this.anInt2760, this.anInt2832, var14, this.anInt2802, var13);
				if(var15 != null) {
					this.anInt2820 = var15.method1871();
					NPCDefinition var16 = this.definition;
					if(null != var16.childNPCs) {
						var16 = var16.method1471((byte)-110);
					}

					Model var17;
					if(Class140_Sub6.aBoolean2910 && Objects.requireNonNull(var16).aBoolean1249) {
						var17 = Class140_Sub3.method1957(this.definition.aByte1287, this.aBoolean2810, null == var14?var13:var14, this.anInt2819, this.definition.aShort1256, this.anInt2829, this.definition.aShort1286, this.definition.size, var15, var1, null != var14?this.anInt2813:this.anInt2832, this.anInt2831, this.definition.aByte1275);
						if(HDToolKit.highDetail) {
							float var18 = HDToolKit.method1852();
							float var19 = HDToolKit.method1839();
							HDToolKit.depthBufferWritingDisabled();
							HDToolKit.method1825(var18, -150.0F + var19);
							var17.animate(0, var2, var3, var4, var5, var6, var7, var8, -1L, var11, this.aClass127_Sub1_2801);
							HDToolKit.method1830();
							HDToolKit.method1825(var18, var19);
						} else {
							var17.animate(0, var2, var3, var4, var5, var6, var7, var8, -1L, var11, this.aClass127_Sub1_2801);
						}
					}

					this.method1971(var15, (byte)-111);
					this.method1969((byte)115, var15, var1);
					var17 = null;
					if(this.anInt2842 != -1 && -1 != this.anInt2805) {
						GraphicDefinition var21 = GraphicDefinition.getGraphicDefinition((byte)42, this.anInt2842);
						var17 = var21.method966(this.anInt2826, this.anInt2805, this.anInt2761);
						if(var17 != null) {
							var17.method1897(0, -this.anInt2799, 0);
							if(var21.aBoolean536) {
								if(Class3_Sub13_Sub16.anInt3198 != 0) {
									var17.method1896(Class3_Sub13_Sub16.anInt3198);
								}

								if(Class3_Sub28_Sub9.anInt3623 != 0) {
									var17.method1886(Class3_Sub28_Sub9.anInt3623);
								}

								if(0 != Class3_Sub13_Sub9.anInt3111) {
									var17.method1897(0, Class3_Sub13_Sub9.anInt3111, 0);
								}
							}
						}
					}

					if(HDToolKit.highDetail) {
						if(this.definition.size == 1) {
							var15.aBoolean2699 = true;
						}

						var15.animate(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11, this.aClass127_Sub1_2801);
						if(var17 != null) {
							if(this.definition.size == 1) {
								var17.aBoolean2699 = true;
							}

							var17.animate(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11, this.aClass127_Sub1_2801);
						}
					} else {
						if(null != var17) {
							var15 = ((Class140_Sub1_Sub2)var15).method1943(var17);
						}

						if(this.definition.size == 1) {
							var15.aBoolean2699 = true;
						}

						var15.animate(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11, this.aClass127_Sub1_2801);
					}

				}
			}
		} catch (RuntimeException var20) {
			throw ClientErrorException.clientError(var20, "km.IA(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ',' + var6 + ',' + var7 + ',' + var8 + ',' + var9 + ',' + var11 + ',' + (var12 != null?"{...}":"null") + ')');
		}
	}

	final int getRenderAnimationId() {
		try {
			if(Class158.anInt2014 != 0 && this.definition.childNPCs != null) {
				NPCDefinition var2 = this.definition.method1471((byte)21);
				if(var2 != null && var2.renderAnimationId != -1) {
					return var2.renderAnimationId;
				}
			}

			return this.renderAnimationId;
		} catch (RuntimeException var3) {
			throw ClientErrorException.clientError(var3, "km.B(" + -1 + ')');
		}
	}

	final void method1867(int var1, int var2, int var3, int var4, int var5) {
		try {
		} catch (RuntimeException var7) {
			throw ClientErrorException.clientError(var7, "km.IB(" + var1 + ',' + var2 + ',' + var3 + ',' + var4 + ',' + var5 + ')');
		}
	}

	final boolean hasDefinitions() {
		try {

			return null != this.definition;
		} catch (RuntimeException var3) {
			throw ClientErrorException.clientError(var3, "km.L(" + (byte) 17 + ')');
		}
	}

	static boolean method1986(int var0) {
		try {
			if(var0 <= 22) {
				method1984(-48, 88, 31);
			}

			return HDToolKit.highDetail || Unsorted.aBoolean3665;
		} catch (RuntimeException var2) {
			throw ClientErrorException.clientError(var2, "km.O(" + var0 + ')');
		}
	}

	final void setDefinitions(NPCDefinition var2) {
		try {
			this.definition = var2;
        } catch (RuntimeException var4) {
			throw ClientErrorException.clientError(var4, "km.R(" + -1 + ',' + (var2 != null?"{...}":"null") + ')');
		}
	}

	static boolean method1988() {
		try {
			try {
				if(Class10.anInt154 == 2) {
					if(aClass3_Sub27_1154 == null) {
						aClass3_Sub27_1154 = Class3_Sub27.method517(Class101.aClass153_1423, Class3_Sub13_Sub39.anInt3463, Class132.anInt1741);
						if(null == aClass3_Sub27_1154) {
							return false;
						}
					}

					if(Class3_Sub28_Sub4.aClass83_3579 == null) {
						Class3_Sub28_Sub4.aClass83_3579 = new Class83(Class40.aClass153_679, Class3_Sub28_Sub20.aClass153_3786);
					}

					if(Class101.aClass3_Sub24_Sub4_1421.method470(aClass3_Sub27_1154, Class124.aClass153_1661, Class3_Sub28_Sub4.aClass83_3579)) {
						Class101.aClass3_Sub24_Sub4_1421.method471();
						Class101.aClass3_Sub24_Sub4_1421.method506(Class3_Sub13_Sub36.anInt3423);
						Class101.aClass3_Sub24_Sub4_1421.method490(Unsorted.aBoolean2311, aClass3_Sub27_1154);
						Class10.anInt154 = 0;
						aClass3_Sub27_1154 = null;
						Class3_Sub28_Sub4.aClass83_3579 = null;
						Class101.aClass153_1423 = null;
						return true;
					}
				}
			} catch (Exception var2) {
				var2.printStackTrace();
				Class101.aClass3_Sub24_Sub4_1421.method505((byte)-128);
				Class101.aClass153_1423 = null;
				aClass3_Sub27_1154 = null;
				Class10.anInt154 = 0;
				Class3_Sub28_Sub4.aClass83_3579 = null;
			}

			return false;
		} catch (RuntimeException var3) {
			throw ClientErrorException.clientError(var3, "km.P(" + false + ')');
		}
	}

}
