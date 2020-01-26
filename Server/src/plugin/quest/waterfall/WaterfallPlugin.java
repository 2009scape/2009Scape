package plugin.quest.waterfall;

import java.util.ArrayList;
import java.util.List;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.action.ClimbActionHandler;
import org.crandor.game.content.global.action.DoorActionHandler;
import org.crandor.game.content.skill.member.agility.AgilityHandler;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.LocationLogoutTask;
import org.crandor.game.system.task.LogoutTask;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;

/**
 * Master plugin file for the Waterfall quest.
 * @author Splinter
 * @version 1.0 - Feb 28th, 2015
 */
public final class WaterfallPlugin extends OptionHandler {

	/**
	 * The rope
	 */
	public final static Item ROPE = new Item(954);

	/**
	 * The first ket
	 */
	public final static Item KEY = new Item(293);

	/**
	 * The second key
	 */
	public final static Item KEY_2 = new Item(298);

	/**
	 * The glarial's pebble
	 */
	public final static Item PEBBLE = new Item(294);

	/**
	 * The glarial's amulet
	 */
	public final static Item AMULET = new Item(295);

	/**
	 * The glarial's urn
	 */
	public final static Item URN = new Item(296);

	/**
	 * The glarial's urn
	 */
	public final static Item URN_EMPTY = new Item(297);

	/**
	 * Air rune
	 */
	public final static Item AIR_RUNE = new Item(556, 6);

	/**
	 * Water rune
	 */
	public final static Item WATER_RUNE = new Item(555, 6);

	/**
	 * Earth rune
	 */
	public final static Item EARTH_RUNE = new Item(557, 6);

	/**
	 * The swimmer swimming.
	 */
	private static final List<Player> SWIMMERS = new ArrayList<>();

	/**
	 * The ropes.
	 */
	private static final List<GameObject> ROPES = new ArrayList<>();

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		PluginManager.definePlugin(new WaterfallUseWithHandler());
		NPCDefinition.forId(305).getConfigurations().put("option:talk-to", this);
		ObjectDefinition.forId(1987).getConfigurations().put("option:board", this);
		ObjectDefinition.forId(2020).getConfigurations().put("option:climb", this);
		ObjectDefinition.forId(2022).getConfigurations().put("option:get in", this);
		ObjectDefinition.forId(10283).getConfigurations().put("option:swim", this);
		ObjectDefinition.forId(1996).getConfigurations().put("option:swim to", this);
		ObjectDefinition.forId(1989).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(1990).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(1991).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(37247).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(32711).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(33046).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(33047).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(33047).getConfigurations().put("option:close", this);
		ObjectDefinition.forId(33066).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(1999).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(1757).getConfigurations().put("option:climb-up", this);
		ObjectDefinition.forId(2002).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(1992).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(2014).getConfigurations().put("option:take treasure", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		final int id = node.getId();
		final Quest quest = player.getQuestRepository().getQuest(WaterFall.NAME);
		if (quest == null) {
			player.sendMessage("Error! Waterfall quest cannot be found.");
			return true;
		}
		switch (id) {
		case 37247:
			player.getPacketDispatch().sendMessage("The door opens...");
			player.getPulseManager().run(new Pulse(2, player) {
				@Override
				public boolean pulse() {
					if (player.getEquipment().containsOneItem(295) || player.getInventory().contains(295, 1)) {
						player.getPacketDispatch().sendMessage("You walk through the door.");
						player.teleport(new Location(2575, 9861));
					} else {
						player.getPacketDispatch().sendMessage("The cave floods and washes you away!");
						player.teleport(new Location(2527, 3413));
					}
					return true;
				}
			});
			break;
		case 1992:
			player.lock(16);
			player.getPacketDispatch().sendMessage("The grave is covered in elven script.", 1);
			player.getPacketDispatch().sendMessage("Some of the writing is in common tongue, it reads:", 3);
			player.getPacketDispatch().sendMessage("Here lies Glarial, wife of Baxtorian,", 6);
			player.getPacketDispatch().sendMessage("true friend of nature in life and death.", 9);
			player.getPacketDispatch().sendMessage("May she now rest knowing", 12);
			player.getPacketDispatch().sendMessage("only visitors with peaceful intent can enter.", 15);
			break;
		case 2014:
			if (quest.getStage(player) != 100) {
				player.getPacketDispatch().sendMessage("The cavern floods!");
				player.lock(6);
				player.getPulseManager().run(new Pulse(5, player) {
					@Override
					public boolean pulse() {
						player.teleport(new Location(2566, 9901));
						player.getPacketDispatch().sendMessage("You are washed out of the chalice room.");
						player.removeAttribute("waterfall_placed_runes");
						return true;
					}
				});
			} else {
				player.getPacketDispatch().sendMessage("You have already looted the treasure.");
			}
			break;
		case 1999:// Second key (in the actual waterfall dungeon)
			if (player.getInventory().contains(298, 1) || quest.getStage(player) < 30) {
				player.getPacketDispatch().sendMessage("You search the crate and find nothing.");
			} else if (quest.getStage(player) >= 30 && !player.getInventory().contains(298, 1)) {
				player.getPacketDispatch().sendMessage("You find a large key.");
				player.getInventory().add(new Item(298, 1));
			}
			break;
		case 2002://
			if (player.getLocation().equals(new Location(2568, 9893))) {
				player.getPacketDispatch().sendMessage("The door is locked.");
			} else if (node.getLocation().equals(new Location(2566, 9901)) || player.getLocation().equals(new Location(2568, 9894, 0))) {
				if (quest.getStage(player) >= 100 && node.getLocation().equals(new Location(2566, 9901)) && player.getLocation().equals(new Location(2566, 9901))) {
					player.teleport(new Location(2604, 9901));
				} else {
					DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
				}
			}
			if (node.getLocation().equals(new Location(2604, 9900))) {
				player.teleport(new Location(2566, 9901));
				player.removeAttribute("waterfall_placed_runes");
			}
			break;
		case 33046:
			ObjectBuilder.add(new GameObject(33047, Location.create(2530, 9844, 0), 10, 1));
			player.getPacketDispatch().sendMessage("You open the chest.");
			break;
		case 1757:
			if (node.getLocation().equals(new Location(2556, 9844))) {
				ClimbActionHandler.climb(player, new Animation(828), Location.create(2557, 3444, 0));
			} else {
				ClimbActionHandler.climbLadder(player, (GameObject) node, option);
			}
			break;
		case 33047:
			switch (option) {
			case "search":
				if (quest.getStage(player) >= 30) {
					if (!player.hasItem(new Item(295))) {
						player.getPacketDispatch().sendMessage("You search the chest and find a small amulet.");
						player.getInventory().add(new Item(295, 1));
					} else {
						player.getPacketDispatch().sendMessage("You search the chest and find nothing.");
					}
				}
				break;
			case "close":
				ObjectBuilder.add(new GameObject(33046, Location.create(2530, 9844, 0), 10, 1));
				break;
			}
			break;
		case 33066:
			if (quest.getStage(player) >= 30) {
				if (!player.getInventory().contains(296, 1)) {
					player.getPacketDispatch().sendMessage("You search the coffin and inside you find an urn full of ashes.");
					player.getInventory().add(new Item(296, 1));
				} else {
					player.getPacketDispatch().sendMessage("You search the coffin and find nothing.");
				}
			}
			break;
		case 32711:
			player.teleport(new Location(2511, 3463));
			break;
		case 2020:
			player.getDialogueInterpreter().open("waterfall_tree_dialogue", 0);
			break;
		case 305:
			player.getDialogueInterpreter().open(305, ((NPC) node));
			break;
		case 1990:
			if (player.getInventory().contains(293, 1) || quest.getStage(player) < 30) {
				player.getPacketDispatch().sendMessage("You search the crate and find nothing.");
			} else if (quest.getStage(player) >= 30 && !player.getInventory().contains(293, 1)) {
				player.getPacketDispatch().sendMessage("You find a large key.");
				player.getInventory().add(new Item(293, 1));
			}
			break;
		case 1991:
			if (player.getLocation().getY() >= 9576) {
				DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
				player.getPacketDispatch().sendMessage("You open the gate and walk through.");
			} else if (player.getInventory().contains(293, 1) && player.getLocation().getY() < 9576) {
				player.getPacketDispatch().sendMessage("The gate is locked. You need to use the key on the door to enter.");
			} else {
				player.getDialogueInterpreter().sendDialogues(306, FacialExpression.NO_EXPRESSION, "Hello? Ah yes, the door is still locked.", "If you want to get in here, you'll need to find the key", "that I hid in some crates in the eastern room.");
			}
			break;
		case 1989:
			if (player.getInventory().contains(292, 1) || quest.getStage(player) != 20) {
				player.getPacketDispatch().sendMessage("You search the bookcase and find nothing of interest.");
				break;
			} else if (!player.hasItem(new Item(292)) && quest.getStage(player) == 20) {
				player.getInventory().add(new Item(292, 1));
				player.getPacketDispatch().sendMessage("You search the bookcase and find a book named 'Book on Baxtorian'");
			}
			break;
		case 10283:
		case 1996:
			player.addExtension(LogoutTask.class, new LocationLogoutTask(10, player.getLocation()));
			player.getPacketDispatch().sendGraphic(68);
			player.lock(6);
			AgilityHandler.walk(player, -1, player.getLocation(), new Location(2512, 3471, 0), Animation.create(164), 0, null);
			player.getPacketDispatch().sendMessage("It looks like a long distance, but you swim out into the water.");
			player.getPacketDispatch().sendMessage("The current is too strong, you feel yourself being pulled under", 3);
			player.getPulseManager().run(new Pulse(6, player) {
				@Override
				public boolean pulse() {
					player.getPacketDispatch().sendMessage("You are washed downstream but feel lucky to be alive.");
					player.teleport(new Location(2527, 3413));
					return true;
				}
			});
			break;
		case 2022:// Barrel
			player.getPacketDispatch().sendMessage("You get in the barrel and start rocking.");
			player.getPacketDispatch().sendMessage("The barrel falls off the ledge.");
			player.teleport(new Location(2527, 3413));
			break;
		case 1987:// Raft
			if (quest.getStage(player) >= 10) {
				player.getPacketDispatch().sendMessage("You board the small raft", 2);
				player.lock(13);
				player.getPacketDispatch().sendMessage("and push off down stream.", 6);
				player.getPacketDispatch().sendMessage("The raft is pulled down stream by strong currents", 9);
				player.getPulseManager().run(new Pulse(12, player) {
					@Override
					public boolean pulse() {
						player.getPacketDispatch().sendMessage("You crash into a small land mound.");
						player.teleport(new Location(2512, 3481));
						return true;
					}
				});
			} else {
				player.getDialogueInterpreter().sendDialogue("You have no reason to board this raft.");
			}
			return true;
		}
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		if (n instanceof NPC) {
			NPC npc = ((NPC) n);
			if (npc.getId() == 305) {
				return Location.create(2512, 3481, 0);
			}
		}
		if (n instanceof GameObject) {
			GameObject obj = ((GameObject) n);
			if (obj.getId() == 1996 || obj.getId() == 1997) {
				return Location.create(2512, 3476, 0);
			}
		}
		return null;
	}

	@Override
	public boolean isWalk(Player player, Node node) {
		return !(node instanceof Item);
	}

	@Override
	public boolean isWalk() {
		return false;
	}

	public void handleObjects(boolean add, final Player player) {// very ugly
		// code
		if (add) {
			ROPES.add(new GameObject(1997, Location.create(2512, 3468, 0), 10, 0));
			ROPES.add(new GameObject(1998, Location.create(2512, 3469, 0), 10, 0));
			ROPES.add(new GameObject(1998, Location.create(2512, 3470, 0), 10, 0));
			ROPES.add(new GameObject(1998, Location.create(2512, 3471, 0), 10, 0));
			ROPES.add(new GameObject(1998, Location.create(2512, 3472, 0), 10, 0));
			ROPES.add(new GameObject(1998, Location.create(2512, 3473, 0), 10, 0));
			ROPES.add(new GameObject(1998, Location.create(2512, 3474, 0), 10, 0));
			ROPES.add(new GameObject(1998, Location.create(2512, 3475, 0), 10, 0));
			for (GameObject rope : ROPES) {
				ObjectBuilder.add(rope);
			}
		} else {
			for (GameObject rope : ROPES) {
				ObjectBuilder.remove(rope);
			}
			ROPES.clear();
			ObjectBuilder.add(new GameObject(1996, Location.create(2512, 3468, 0), 10, 0));

		}
	}

	/**
	 * Handles the runestone graphic.
	 * @param player the player.
	 */
	public void playRunestoneGraphics(final Player player) {
		player.getPacketDispatch().sendGlobalPositionGraphic(580, new Location(2562, 9914));
		player.getPacketDispatch().sendGlobalPositionGraphic(580, new Location(2562, 9912));
		player.getPacketDispatch().sendGlobalPositionGraphic(580, new Location(2562, 9910));
		player.getPacketDispatch().sendGlobalPositionGraphic(580, new Location(2569, 9914));
		player.getPacketDispatch().sendGlobalPositionGraphic(580, new Location(2569, 9912));
		player.getPacketDispatch().sendGlobalPositionGraphic(580, new Location(2569, 9910));
	}

	/**
	 * Handles using items on objects for the quest.
	 * @author Splinter
	 */
	public class WaterfallUseWithHandler extends UseWithHandler {

		private final int[] OBJECTS = new int[] { 1996, 1997, 2020, 1991, 1992, 2002, 2006, 2014, 2004 };

		/**
		 * Constructs a new {@Code WaterfallUseWithItemHandler} {@Code
		 *  Object}
		 */
		public WaterfallUseWithHandler() {
			super(ROPE.getId(), KEY.getId(), PEBBLE.getId(), KEY_2.getId(), AMULET.getId(), URN.getId(), AIR_RUNE.getId(), EARTH_RUNE.getId(), WATER_RUNE.getId());
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			for (int id : OBJECTS) {
				addHandler(id, OBJECT_TYPE, this);
			}
			return this;
		}

		@Override
		public Location getDestination(Player playa, Node n) {
			if (n instanceof GameObject) {
				GameObject obj = (GameObject) n;
				if (obj.getId() == 1996 || obj.getId() == 1997) {
					return Location.create(2512, 3476, 0);
				}
			}
			return null;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			final Player player = event.getPlayer();
			Item useditem = event.getUsedItem();
			final Quest quest = player.getQuestRepository().getQuest(WaterFall.NAME);
			final GameObject object = (GameObject) event.getUsedWith();

			if (useditem.getId() == ROPE.getId() && object.getId() == 1996 || object.getId() == 1997) {
				player.lock(8);
				player.animate(Animation.create(774));
				SWIMMERS.add(player);
				if (SWIMMERS.size() == 0 || ROPES.size() == 0) {
					handleObjects(true, player);
				}
				player.addExtension(LogoutTask.class, new LocationLogoutTask(13, player.getLocation()));
				player.getPulseManager().run(new Pulse(2, player) {
					@Override
					public boolean pulse() {
						player.faceLocation(new Location(2512, 3468, 0));
						AgilityHandler.walk(player, -1, player.getLocation(), new Location(2512, 3469, 0), Animation.create(273), 0, null);
						player.getPulseManager().run(new Pulse(8, player) {
							@Override
							public boolean pulse() {
								player.teleport(new Location(2513, 3468));
								player.faceLocation(new Location(2512, 3468, 0));
								player.animate(Animation.create(780));
								return true;
							}

							@Override
							public void stop() {
								super.stop();
								if (SWIMMERS.remove(player) && SWIMMERS.size() == 0) {
									handleObjects(false, player);
								}
							}
						});
						return true;
					}
				});
			}

			if (useditem.getId() == ROPE.getId() && object.getId() == 2020) {
				player.getPacketDispatch().sendMessage("You tie the rope to the tree and let yourself down on the ledge.");
				player.teleport(new Location(2511, 3463));
			}

			if (useditem.getId() == KEY.getId() && object.getId() == 1991) {
				player.getPacketDispatch().sendMessage("The key fits the gate.");
				player.getPacketDispatch().sendMessage("You open the gate and walk through.");
				DoorActionHandler.handleAutowalkDoor(player, object);
			}

			if (useditem.getId() == KEY_2.getId() && object.getId() == 2002 && !object.getLocation().equals(new Location(2566, 9901))) {
				player.getPacketDispatch().sendMessage("You open the door and walk through.");
				DoorActionHandler.handleAutowalkDoor(player, object);
			}

			if (useditem.getId() == PEBBLE.getId() && object.getId() == 1992 && ItemDefinition.canEnterEntrana(player)) {
				player.getPacketDispatch().sendMessage("You place the pebble in the gravestone's small indent.");
				player.getPacketDispatch().sendMessage("It fits perfectly.");
				player.getPacketDispatch().sendMessage("You hear a loud creak.", 4);
				player.getPacketDispatch().sendMessage("The stone slab slides back revealing a ladder down.", 7);
				player.getPacketDispatch().sendMessage("You climb down to an underground passage.", 12);
				player.getPulseManager().run(new Pulse(13, player) {
					@Override
					public boolean pulse() {
						player.getSkills().setPrayerPoints(0);
						player.teleport(new Location(2555, 9844));
						return true;
					}
				});
			} else if (!ItemDefinition.canEnterEntrana(player) && useditem.getId() == PEBBLE.getId() && object.getId() == 1992) {
				player.getPacketDispatch().sendMessage("You place the pebble in the gravestone's small indent.");
				player.getPacketDispatch().sendMessage("It fits perfectly.");
				player.getPacketDispatch().sendMessage("But nothing happens.", 4);
			}

			if (useditem.getId() == AIR_RUNE.getId() || useditem.getId() == EARTH_RUNE.getId() || useditem.getId() == WATER_RUNE.getId() && object.getId() == 2004) {
				if (player.getInventory().contains(555, 6) && player.getInventory().contains(556, 6) && player.getInventory().contains(557, 6)) {
					if (player.getAttribute("waterfall_placed_runes") != null) {
						player.getPacketDispatch().sendMessage("You have already placed the runes on the pillars.");
						return false;
					}
					playRunestoneGraphics(player);
					player.getPacketDispatch().sendMessage("You place one of each runestone on all six of the pillars.");
					player.setAttribute("waterfall_placed_runes", 1);
					player.getInventory().remove(new Item(AIR_RUNE.getId(), 6));
					player.getInventory().remove(new Item(EARTH_RUNE.getId(), 6));
					player.getInventory().remove(new Item(WATER_RUNE.getId(), 6));
				} else {
					player.getPacketDispatch().sendMessage("You do not have enough runestones to place on the pillars.");
				}
			}

			if (useditem.getId() == AMULET.getId() && object.getId() == 2006 && quest.getStage(player) != 100 && !player.getLocation().equals(new Location(2603, 9914))) {
				if (player.getAttribute("waterfall_placed_runes") == null) {
					player.getPacketDispatch().sendMessage("You place the amulet around the neck of the statue.");
					player.lock(4);
					player.getPulseManager().run(new Pulse(3, player) {
						@Override
						public boolean pulse() {
							player.getPacketDispatch().sendGraphic(74);
							player.getImpactHandler().manualHit(player, 20, HitsplatType.NORMAL);
							player.getPacketDispatch().sendMessage("Rocks fall from the ceiling and hit you in the head.");
							return true;
						}
					});
				} else if (player.getAttribute("waterfall_placed_runes") != null) {
					player.lock(7);
					player.getInventory().remove(AMULET);
					player.getPacketDispatch().sendMessage("You place the amulet around the neck of the statue.");
					player.getPacketDispatch().sendMessage("You hear a loud rumble from beneath...", 3);
					player.getPacketDispatch().sendMessage("The ground raises up before you!", 6);
					player.getPulseManager().run(new Pulse(6, player) {
						@Override
						public boolean pulse() {
							player.teleport(new Location(2603, 9914));
							return true;
						}
					});
				}
			}

			if (useditem.getId() == URN.getId() && object.getId() == 2014 && quest.getStage(player) != 100) {
				if (player.getInventory().freeSlots() < 5) {
					player.getPacketDispatch().sendMessage("You'll need 5 free inventory slots to take Glarial's treasure.");
					return false;
				} else {
					player.lock(10);
					player.getInventory().remove(URN);
					player.getInventory().add(URN_EMPTY);
					player.getPacketDispatch().sendMessage("You carefully pour the ashes into the chalice");
					player.getPacketDispatch().sendMessage("as you remove the Treasure of Baxtorian", 3);
					player.getPacketDispatch().sendMessage("The chalice remains standing.", 6);
					player.getPacketDispatch().sendMessage("Inside you find a mithril case", 6);
					player.getPacketDispatch().sendMessage("containing 40 seeds,", 6);
					player.getPacketDispatch().sendMessage("two diamonds and two gold bars.", 6);
					player.getPulseManager().run(new Pulse(8, player) {
						@Override
						public boolean pulse() {
							quest.finish(player);
							return true;
						}
					});

				}
			}
			return true;
		}
	}
}
