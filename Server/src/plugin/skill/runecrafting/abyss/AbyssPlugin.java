package plugin.skill.runecrafting.abyss;

import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.skill.free.gather.SkillingTool;
import org.crandor.game.content.skill.free.runecrafting.Altar;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.impl.Animator.Priority;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.PulseRunner;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import org.crandor.tools.RandomFunction;

/**
 * A plugin used to handle the abyss.
 * @author Vexia
 */
public final class AbyssPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (AbbysalObstacle obstacle : AbbysalObstacle.values()) {
			for (int i : obstacle.getObjects()) {
				ObjectDefinition.forId(i).getConfigurations().put("option:" + obstacle.getOption(), this);
			}
		}
		PluginManager.definePlugin(new AbyssalNPC());
		PluginManager.definePlugin(new DarkMageDialogue());
		ObjectDefinition.setOptionHandler("exit-through", this);
		PluginManager.definePlugin(new ZamorakMageDialogue());
		NPCDefinition.forId(2259).getConfigurations().put("option:teleport", this);
		NPCDefinition.forId(2262).getConfigurations().put("option:repair-pouches", this);
		return null;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (option) {
		case "teleport":
			teleport(player, (NPC) node);
			break;
		case "repair-pouches":
			player.getDialogueInterpreter().open(node.getId(), node, true);
			break;
		case "exit-through":
			Altar altar = Altar.forObject((GameObject) node);
			if (altar != null) {
				altar.enterRift(player);
			}
			break;
		case "mine":
		case "chop":
		case "squeeze-through":
		case "distract":
		case "go-through":
		case "burn-down":
			final AbbysalObstacle obstacle = AbbysalObstacle.forObject(((GameObject) node));
			obstacle.handle(player, ((GameObject) node));
			break;
		}
		return true;
	}

	/**
	 * Represents the teleporting to the abyss.
	 * @param player the player.
	 */
	public static void teleport(final Player player, final NPC npc) {
		player.lock(3);
		npc.visualize(new Animation(1979), new Graphics(4));
		npc.sendChat("Veniens! Sallakar! Rinnesset!");
		player.getSkills().decrementPrayerPoints(100);
		player.getSkullManager().checkSkull(player);
		GameWorld.Pulser.submit(new Pulse(2, player) {
			@Override
			public boolean pulse() {
				player.getProperties().setTeleportLocation(Location.create(3021, 4847, 0));
				npc.getUpdateMasks().reset();
				return true;
			}
		});
	}

	/**
	 * Represents an obstacle in an abbsyal.
	 * @author 'Vexia
	 * @date 02/11/2013
	 */
	public enum AbbysalObstacle {
		BOIL("burn-down", new Location[] { Location.create(3024, 4833, 0), Location.create(3053, 4830, 0) }, 7165) {
			@Override
			public void handle(final Player player, final GameObject object) {
				if (!player.getInventory().contains(590, 1)) {
					player.getPacketDispatch().sendMessage("You don't have a tinderbox to burn it.");
					return;
				}
				player.animate(new Animation(733));
				player.lock(3);
				GameWorld.Pulser.submit(new Pulse(1, player) {
					int count = 0;

					@Override
					public boolean pulse() {
						switch (count) {
						case 1:
							player.getPacketDispatch().sendMessage("You attempt to burn your way through..");
							break;
						case 4:
							if (RandomFunction.random(3) != 1) {
								player.getPacketDispatch().sendMessage("...and manage to burn it down and get past.");
								player.getProperties().setTeleportLocation(getLocations()[getIndex(object)]);
								return true;
							} else {
								player.getPacketDispatch().sendMessage("You fail to set it on fire.");
								return true;
							}
						}
						count++;
						return false;
					}

					@Override
					public void stop() {
						super.stop();
						player.animate(new Animation(-1, Priority.HIGH));
					}
				});
			}
		},
		MINE("mine", new Location[] { Location.create(3030, 4821, 0), Location.create(3048, 4822, 0) }, 7158, 7153) {
			@Override
			public void handle(final Player player, final GameObject object) {
				final SkillingTool tool = setTool(true, player);
				if (tool == null) {
					player.getPacketDispatch().sendMessage("You need a pickaxe in order to do that.");
					return;
				}
				player.animate(tool.getAnimation());
				player.lock(3);
				GameWorld.Pulser.submit(new Pulse(1, player) {
					int count = 0;

					@Override
					public boolean pulse() {
						switch (count) {
						case 1:
							player.getPacketDispatch().sendMessage("You attempt to mine your way through..");
							break;
						case 4:
							if (RandomFunction.random(3) != 1) {
								player.getPacketDispatch().sendMessage("...and manage to break through the rock.");
								player.getProperties().setTeleportLocation(getLocations()[getIndex(object)]);
								return true;
							} else {
								player.getPacketDispatch().sendMessage("...but fail to break-up the rock.");
								return true;
							}
						}
						count++;
						return false;
					}

					@Override
					public void stop() {
						super.stop();
						player.animate(new Animation(-1, Priority.HIGH));
					}
				});
			}
		},
		CHOP("chop", new Location[] { Location.create(3050, 4824, 0), Location.create(3028, 4824, 0) }, 7161, 7144) {
			@Override
			public void handle(final Player player, final GameObject object) {
				final SkillingTool tool = setTool(false, player);
				if (tool == null) {
					player.getPacketDispatch().sendMessage("You need an axe in order to do that.");
					return;
				}
				player.animate(tool.getAnimation());
				player.lock(3);
				GameWorld.Pulser.submit(new Pulse(1, player) {
					int count = 0;

					@Override
					public boolean pulse() {
						switch (count) {
						case 1:
							player.getPacketDispatch().sendMessage("You attempt to chop your way through...");
							break;
						case 4:
							if (RandomFunction.random(3) != 1) {
								player.getPacketDispatch().sendMessage("...and manage to chop down the tendrils.");
								player.getProperties().setTeleportLocation(getLocations()[getIndex(object)]);
								return true;
							} else {
								player.getPacketDispatch().sendMessage("You fail to cut through the tendrils.");
								return true;
							}
						}
						count++;
						return false;
					}

					@Override
					public void stop() {
						super.stop();
						player.animate(new Animation(-1, Priority.HIGH));
					}
				});
			}
		},
		SQUEEZE("squeeze-through", new Location[] { Location.create(3048, 4842, 0), Location.create(3031, 4842, 0) }, 7164, 7147) {
			@Override
			public void handle(final Player player, final GameObject object) {
				player.animate(new Animation(1331));
				player.lock(3);
				player.lock(3);
				GameWorld.Pulser.submit(new Pulse(1, player) {
					int count = 0;

					@Override
					public boolean pulse() {
						switch (count) {
						case 1:
							player.getPacketDispatch().sendMessage("You attempt to squeeze through the narrow gap...");
							break;
						case 2:
							player.getPacketDispatch().sendMessage("...and you manage to crawl through.");
							player.getProperties().setTeleportLocation(getLocations()[getIndex(object)]);
							return true;
						}
						count++;
						return false;
					}

					@Override
					public void stop() {
						super.stop();
						player.animate(new Animation(-1, Priority.HIGH));
					}
				});
			}
		},
		DISTRACT("distract", new Location[] { Location.create(3029, 4841, 0), Location.create(3051, 4838, 0) }, 7168, 7150) {
			@Override
			public void handle(final Player player, final GameObject object) {
				int[] emotes = { 855, 856, 857, 858, 859, 860, 861, 862, 863, 864, 865, 866, 2113, 2109, 2111, 2106, 2107, 2108, 0x558, 2105, 2110, 2112, 0x84F, 0x850, 1131, 1130, 1129, 1128, 1745, 3544, 3543, 2836 };
				int index = RandomFunction.random(emotes.length);
				player.animate(new Animation(emotes[index]));
				player.lock(3);
				GameWorld.Pulser.submit(new Pulse(1, player) {
					int count = 0;

					@Override
					public boolean pulse() {
						switch (count) {
						case 1:
							player.getPacketDispatch().sendMessage("You use your thieving skills to misdirect the eyes...");
							break;
						case 4:
							if (RandomFunction.random(3) != 1) {
								player.getPacketDispatch().sendMessage("...and sneak past while they're not looking.");
								player.getProperties().setTeleportLocation(getLocations()[getIndex(object)]);
							} else {
								player.getPacketDispatch().sendMessage("You fail to distract the eyes.");
								return true;
							}
							return true;
						}
						count++;
						return false;
					}

					@Override
					public void stop() {
						super.stop();
						player.animate(new Animation(-1, Priority.HIGH));
					}
				});
			}
		},
		PASSAGE("go-through", new Location[] { Location.create(3040, 4844, 0) }, 7154) {
			@Override
			public void handle(final Player player, final GameObject object) {
				player.getProperties().setTeleportLocation(getLocations()[0]);
			}
		};
		/**
		 * Constructs a new {@code RunecraftingOptionPlugin} {@code Object}.
		 * @param locations the locations.
		 * @param objects the objects.
		 */
		AbbysalObstacle(final String option, Location[] locations, int... objects) {
			this.option = option;
			this.objects = objects;
			this.locations = locations;
			this.option = option;
		}

		/**
		 * Represents the option.
		 */
		private String option;

		/**
		 * Represents the corssing location.
		 */
		private final Location[] locations;

		/**
		 * Represents the object id.
		 */
		private final int[] objects;

		/**
		 * Gets the locations.
		 * @return The locations.
		 */
		public Location[] getLocations() {
			return locations;
		}

		/**
		 * Gets the objects.
		 * @return The objects.
		 */
		public int[] getObjects() {
			return objects;
		}

		/**
		 * Method used to get the abbysal obstacle.
		 * @param object the object.
		 * @return the <code>AbbysalObstacle</code> or <code>Null</code>.
		 */
		public static AbbysalObstacle forObject(final GameObject object) {
			for (AbbysalObstacle obstacle : values()) {
				for (int i : obstacle.getObjects()) {
					if (i == object.getId()) {
						return obstacle;
					}
				}
			}
			return null;
		}

		/**
		 * Method used to get the index.
		 * @param object the object.
		 * @return the index.
		 */
		public int getIndex(final GameObject object) {
			for (int i = 0; i < objects.length; i++) {
				if (getObjects()[i] == object.getId()) {
					return i;
				}
			}
			return 0;
		}

		/**
		 * Methhod used to handle the obstacle.
		 * @param player the player.
		 * @param object the object.
		 */
		public void handle(final Player player, final GameObject object) {

		}

		/**
		 * Gets the option.
		 * @return The option.
		 */
		public String getOption() {
			return option;
		}

		/**
		 * Sets the tool used.
		 */
		private static SkillingTool setTool(final boolean mining, final Player player) {
			SkillingTool tool = null;
			if (!mining) {
				if (checkTool(player, SkillingTool.DRAGON_AXE)) {
					tool = SkillingTool.DRAGON_AXE;
				} else if (checkTool(player, SkillingTool.RUNE_AXE)) {
					tool = SkillingTool.RUNE_AXE;
				} else if (checkTool(player, SkillingTool.ADAMANT_AXE)) {
					tool = SkillingTool.ADAMANT_AXE;
				} else if (checkTool(player, SkillingTool.MITHRIL_AXE)) {
					tool = SkillingTool.MITHRIL_AXE;
				} else if (checkTool(player, SkillingTool.BLACK_AXE)) {
					tool = SkillingTool.BLACK_AXE;
				} else if (checkTool(player, SkillingTool.STEEL_AXE)) {
					tool = SkillingTool.STEEL_AXE;
				} else if (checkTool(player, SkillingTool.IRON_AXE)) {
					tool = SkillingTool.IRON_AXE;
				} else if (checkTool(player, SkillingTool.BRONZE_AXE)) {
					tool = SkillingTool.BRONZE_AXE;
				}
			} else {
				if (checkTool(player, SkillingTool.RUNE_PICKAXE)) {
					tool = SkillingTool.RUNE_PICKAXE;
				} else if (checkTool(player, SkillingTool.ADAMANT_PICKAXE)) {
					tool = SkillingTool.ADAMANT_PICKAXE;
				} else if (checkTool(player, SkillingTool.MITHRIL_PICKAXE)) {
					tool = SkillingTool.MITHRIL_PICKAXE;
				} else if (checkTool(player, SkillingTool.STEEL_PICKAXE)) {
					tool = SkillingTool.STEEL_PICKAXE;
				} else if (checkTool(player, SkillingTool.IRON_PICKAXE)) {
					tool = SkillingTool.IRON_PICKAXE;
				} else if (checkTool(player, SkillingTool.BRONZE_PICKAXE)) {
					tool = SkillingTool.BRONZE_PICKAXE;
				}
			}
			return tool;
		}

		/**
		 * Checks if the player has a tool and if he can use it.
		 * @param tool The tool.
		 * @return {@code True} if the tool is usable.
		 */
		private static boolean checkTool(final Player player, SkillingTool tool) {
			if (player.getEquipment().contains(tool.getId(), 1)) {
				return true;
			}
			return player.getInventory().contains(tool.getId(), 1);
		}
	}

}
