package plugin.activity.mta.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.Option;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.tools.RandomFunction;

import plugin.activity.mta.MTAType;
import plugin.activity.mta.MTAZone;

/**
 * Handles the alchemist playground game.
 * @author Vexia
 */
public class AlchemistZone extends MTAZone {

	/**
	 * The alchemist zone.
	 */
	public static AlchemistZone ZONE = new AlchemistZone();

	/**
	 * The coins currency.
	 */
	public static final Item COINS = new Item(8890);

	/**
	 * The players in the zone.
	 */
	private static final List<Player> PLAYERS = new ArrayList<>();

	/**
	 * The guardian.
	 */
	private static NPC guardian;

	/**
	 * The free alchemist item convert.
	 */
	public static AlchemistItem freeConvert;

	/**
	 * The pulse.
	 */
	private static final Pulse PULSE = new Pulse(GameWorld.getSettings().isDevMode() ? 15 : 53) {
		@Override
		public boolean pulse() {
			if (PLAYERS.isEmpty()) {
				return true;
			}
			shufflePrices();
			String forceChat = "The costs are changing!";
			if (freeConvert == null && RandomFunction.random(3) < 3) {
				freeConvert = RandomFunction.getRandomElement(AlchemistItem.values());
				forceChat = "The " + freeConvert.getItem().getName().toLowerCase() + " " + (freeConvert == AlchemistItem.LEATHER_BOOTS ? "are" : "is") + " free to convert!";
			} else if (freeConvert != null) {
				freeConvert = null;
			}
			guardian.sendChat(forceChat);
			for (Player p : PLAYERS) {
				if (p == null || !p.isActive()) {
					continue;
				}
				getSession(p).shuffleObjects();
				updateInterface(p);
			}
			return false;
		}
	};

	/**
	 * Constructs a new {@code AlchemistZone} {@code Object}
	 */
	public AlchemistZone() {
		super("Alchemists' Playground", new Item[] { new Item(8890), new Item(6893), new Item(6894), new Item(6895), new Item(6896), new Item(6897) });
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e instanceof Player && PLAYERS.contains(e)) {
			PLAYERS.remove(e);
			if (logout && e.asPlayer().getInventory().containsItem(COINS)) {
				int deposit = e.asPlayer().getInventory().getAmount(COINS);
				int val = deposit / 100;
				int earn = val < 1 ? 0 : val;
				incrementPoints(e.asPlayer(), MTAType.ALCHEMISTS.ordinal(), earn);
				e.asPlayer().getInventory().remove(COINS);
			}
			e.asPlayer().removeAttribute("alchemist-session");
		}
		return super.leave(e, logout);
	}
	
	@Override
	public void update(Player player) {
		player.getPacketDispatch().sendString("" + player.getSavedData().getActivityData().getPizazzPoints(getType().ordinal()), getType().getOverlay().getId(), 3);
	}

	@Override
	public boolean enter(Entity e) {
		if (guardian == null) {
			guardian = RegionManager.getNpc(new Location(3363, 9627, 2), 3099, 20);
		}
		if (e instanceof Player && !PLAYERS.contains(e)) {
			PLAYERS.add(e.asPlayer());
			if (!PULSE.isRunning()) {
				PULSE.restart();
				PULSE.start();
				GameWorld.Pulser.submit(PULSE);
			}
			e.asPlayer().removeAttribute("alch-earn");
			setSession(e.asPlayer());
			updateInterface(e.asPlayer());
		}
		return super.enter(e);
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (e instanceof Player) {
			Player player = e.asPlayer();
			if (target.getId() == 10734) {
				deposit(player);
				return true;
			} else if (target.getId() >= 6893 && target.getId() <= 6897 && (!option.getName().equals("drop") && !option.getName().equals("take"))) {
				player.getDialogueInterpreter().sendDialogue("This item isn't yours to wield, it belongs to the arena!");
				return true;
			} else if (target.getName().equals("Cupboard")) {
				search(player, target.asObject());
				return true;
			}
		}
		return super.interact(e, target, option);
	}

	/**
	 * Searches a cupboard.
	 * @param player the player.
	 * @param object the object.
	 */
	private void search(Player player, GameObject object) {
		AlchemistSession session = getSession(player);
		AlchemistItem item = session.getItem(object.getId());
		if (object.getId() % 2 != 0) {
			player.animate(Animation.create(3032));
			ObjectBuilder.replace(object, object.transform(object.getId() + 1), 35);
		}
		if (item == null) {
			player.sendMessage("The cupboard is empty.");
			return;
		}
		if (player.getInventory().freeSlots() < 1) {
			player.sendMessage("You have no free space to hold any more items.");
			return;
		}
		player.lock(1);
		player.getInventory().add(item.getItem());
		player.sendMessage("You found: " + item.getItem().getName());
	}

	/**
	 * Deposits the coins.
	 * @param player the player.
	 */
	private void deposit(Player player) {
		if (!player.getInventory().containsItem(COINS)) {
			player.getDialogueInterpreter().sendDialogue("You don't have any coins to deposit.");
			return;
		}
		int deposit = player.getInventory().getAmount(COINS);
		if (deposit >= 12000) {
			player.teleport(new Location(3363, 3302, 0));
			player.getDialogueInterpreter().sendDialogue("You have been ejected from the arena! You were warned", "not to deposit more than 12000 coins at once.");
			return;
		}
		if (player.getInventory().remove(new Item(COINS.getId(), deposit))) {
			int val = deposit / 100;
			int earn = val < 1 ? 0 : val;
			int exp = deposit * 2;
			int taking = player.getAttribute("alch-earn", 0);
			int add = (int) (val > 0 ? (deposit / 100) * 10 : 0);
			if (add != 0) {
				taking += add;
			}
			if (earn != 0) {
				incrementPoints(player, MTAType.ALCHEMISTS.ordinal(), earn);
			}
			if (taking != 0) {
				player.setAttribute("alch-earn", taking);
			}
			player.getSkills().addExperience(Skills.MAGIC, exp, true);
			player.getDialogueInterpreter().sendDialogue("You've just deposited " + deposit + " coins, earning you " + earn + " Alchemist Pizazz", "Points and " + exp + " magic XP. So far you're taking " + taking + " coins as a", "a reward when you leave!");
		}
	}

	/**
	 * Shuffles the prices.
	 */
	public static void shufflePrices() {
		List<Integer> list = Arrays.asList(1, 5, 8, 10, 15, 20, 30);
		Collections.shuffle(list);
		for (int i = 0; i < AlchemistItem.values().length; i++) {
			AlchemistItem.values()[i].setCost(list.get(i));
		}
	}

	/**
	 * Updates the alchemy interface.
	 * @param player the player.
	 */
	public static void updateInterface(Player player) {
		for (AlchemistItem i : AlchemistItem.values()) {
			player.getPacketDispatch().sendInterfaceConfig(194, i.getChild(), freeConvert == null ? true : freeConvert == i ? false : true);
			player.getPacketDispatch().sendString(i.getCost() + "", 194, 9 + i.ordinal());
		}
	}

	@Override
	public void configure() {
		shufflePrices();
		PULSE.stop();
		register(new ZoneBorders(3341, 9618, 3393, 9654, 2));
	}

	/**
	 * Sets the session.
	 * @param player the player.
	 * @return the session.
	 */
	public static AlchemistSession setSession(Player player) {
		AlchemistSession session = new AlchemistSession(player);
		player.setAttribute("alchemist-session", session);
		return session;
	}

	/**
	 * Gets the alchemist session.
	 * @param player the player.
	 * @return the session.
	 */
	public static AlchemistSession getSession(Player player) {
		AlchemistSession session = player.getAttribute("alchemist-session", null);
		if (session == null) {
			session = setSession(player);
		}
		return session;
	}

	/**
	 * An alchemist session.
	 * @author Vexia
	 */
	public static class AlchemistSession {

		/**
		 * The player instance.
		 */
		private final Player player;

		/**
		 * The current indexer. max = 7
		 */
		private int indexer;

		/**
		 * Constructs a new {@code AlchemistSession} {@code Object}
		 * @param player the player.
		 */
		public AlchemistSession(Player player) {
			this.player = player;
			shuffleObjects();
		}

		/**
		 * Gets an alchemist item.
		 * @param id the id.
		 * @return the item.
		 */
		public AlchemistItem getItem(int id) {
			int[] ids = new int[] { 10797, 10795, 10793, 10791, 10789, 10787, 10785, 10783 };
			int index = 0;
			for (int i = 0; i < ids.length; i++) {
				if (ids[i] == id || (ids[i] + 1) == id) {
					index = i;
					break;
				}
			}
			int alchIndex = indexer + index;
			if (indexer != 0) {
				if (indexer >= 4 && index < 4) {
					if (indexer == 4 && indexer - index < 4 || indexer - index < 4) {
						return null;
					}
					if (indexer == 4) {
						return AlchemistItem.LEATHER_BOOTS;
					} else {
						if (indexer == 6 && index == 0) {
							return AlchemistItem.ADAMANT_HELM;
						} else if (indexer == 6 && index == 1) {
							return AlchemistItem.ADAMANT_KITESHIELD;
						} else if (indexer == 6 && index == 2) {
							return AlchemistItem.LEATHER_BOOTS;
						} else if (indexer == 7 && index == 0) {
							return AlchemistItem.EMERALD;
						} else if (indexer == 7 && index == 1) {
							return AlchemistItem.ADAMANT_HELM;
						} else if (indexer == 7 && index == 2) {
							return AlchemistItem.ADAMANT_KITESHIELD;
						} else if (indexer == 7 && index == 3) {
							return AlchemistItem.LEATHER_BOOTS;
						} else if (indexer == 5 && index == 0) {
							return AlchemistItem.ADAMANT_KITESHIELD;
						} else if (indexer == 5 && index == 1) {
							return AlchemistItem.LEATHER_BOOTS;
						}
					}
					return null;
				} else {
					alchIndex -= (indexer + indexer);
				}
			}
			int finalIndex = (AlchemistItem.values().length - 1) - alchIndex;
			if (finalIndex > AlchemistItem.values().length - 1 || finalIndex < 0) {
				return null;
			}
			return AlchemistItem.values()[finalIndex];
		}

		/**
		 * Shuffles the objects.
		 */
		private void shuffleObjects() {
			indexer++;
			if (indexer > 7) {
				indexer = 0;
			}
		}

		/**
		 * Gets the player.
		 * @return the player
		 */
		public Player getPlayer() {
			return player;
		}

	}

	/**
	 * An alchemist item.
	 * @author Vexia
	 */
	public enum AlchemistItem {
		LEATHER_BOOTS(new Item(6893)), ADAMANT_KITESHIELD(new Item(6894)), ADAMANT_HELM(new Item(6895)), EMERALD(new Item(6896)), RUNE_LONGSWORD(new Item(6897));

		/**
		 * The item.
		 */
		private final Item item;

		/**
		 * The cost of the item.
		 */
		private int cost;

		/**
		 * Constructs a new {@code AlchemistItem} {@code Object}
		 * @param item the item.
		 */
		private AlchemistItem(Item item) {
			this.item = item;
		}

		/**
		 * Gets the alchemist item by the id.
		 * @param id the id.
		 * @return the item.
		 */
		public static AlchemistItem forItem(int id) {
			for (AlchemistItem item : values()) {
				if (item.getItem().getId() == id) {
					return item;
				}
			}
			return null;
		}

		/**
		 * Gets the child.
		 * @return the child.
		 */
		public int getChild() {
			return 14 + ordinal();
		}

		/**
		 * Gets the item.
		 * @return the item
		 */
		public Item getItem() {
			return item;
		}

		/**
		 * Gets the cost.
		 * @return the cost
		 */
		public int getCost() {
			return cost;
		}

		/**
		 * Sets the cost.
		 * @param cost the cost to set.
		 */
		public void setCost(int cost) {
			this.cost = cost;
		}

	}
}
