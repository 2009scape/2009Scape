package plugin.activity.wguild.cyclopes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import core.cache.def.impl.ObjectDefinition;
import core.game.container.impl.EquipmentContainer;
import plugin.dialogue.DialogueInterpreter;
import plugin.dialogue.DialoguePlugin;
import core.game.content.global.SkillcapePerks;
import core.game.content.global.action.DoorActionHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.game.world.map.path.Pathfinder;
import core.game.world.map.zone.MapZone;
import core.game.world.map.zone.ZoneBorders;
import core.game.world.map.zone.ZoneBuilder;
import core.game.world.map.zone.ZoneRestriction;
import core.plugin.Plugin;
import core.plugin.PluginManager;
import core.plugin.InitializablePlugin;
import core.tools.RandomFunction;

/**
 * The cyclopes room.
 * @author Emperor
 */
@InitializablePlugin
public final class CyclopesRoom extends MapZone implements Plugin<Object> {

	/**
	 * The defenders.
	 */
	private static final int[] DEFENDERS = { 8844, 8845, 8846, 8847, 8848, 8849, 8850};

	/**
	 * The players in the room.
	 */
	private static final List<Player> PLAYERS = new ArrayList<>();

	/**
	 * The pulse.
	 */
	private static final Pulse PULSE = new Pulse(5) {
		@Override
		public boolean pulse() {
			if (PLAYERS.isEmpty()) {
				return true;
			}
			for (Iterator<Player> it = PLAYERS.iterator(); it.hasNext();) {
				Player p = it.next();
				int current = p.getAttribute("cyclops_ticks", 0) + 5;
				if (current % 100 == 0) {
					Item tokens = new Item(8851, 10);
					if (!p.getInventory().containsItem(tokens) && !SkillcapePerks.hasSkillcapePerk(p, SkillcapePerks.ATTACK)) {
						if (!p.getLocks().isMovementLocked()) {
							p.getPulseManager().clear();
							Pathfinder.find(p.getLocation(), Location.create(2847, 3541, 2)).walk(p);
							p.lock(50);
						} else {
							GameObject object = RegionManager.getObject(2, 2847, 3541);
							if (object != null && p.getLocation().getX() == 2847 && p.getLocation().getY() == 3541) {
								DoorActionHandler.handleAutowalkDoor(p, object);
								leave(p);
								p.unlock();
								p.lock(3);
								it.remove();
							}
						}
						continue;
					}
					if (SkillcapePerks.hasSkillcapePerk(p, SkillcapePerks.ATTACK)) {
						p.sendMessage("You have not lost any tokens since you are wearing a " + p.getEquipment().get(EquipmentContainer.SLOT_CAPE).getName());
					} else {
						p.getInventory().remove(tokens);
						p.getPacketDispatch().sendMessage("10 of your tokens crumble away.");
					}
				}
				p.setAttribute("cyclops_ticks", current);
			}
			return false;
		}
	};

	/**
	 * Constructs a new {@code CyclopesRoom} {@code Object}.
	 */
	public CyclopesRoom() {
		super("wg cyclopes", true, ZoneRestriction.RANDOM_EVENTS);
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e instanceof Player && PLAYERS.contains(e)) {
			leave((Player) e);
			PLAYERS.remove(e);
			if (logout) {
				e.setLocation(Location.create(2846, 3540, 2));
			}
		}
		return super.leave(e, logout);
	}

	@Override
	public boolean death(Entity e, Entity killer) {
		if (killer instanceof Player && e instanceof NPC && (e.getId() == 4292 || e.getId() == 4291)) {
			int defenderId = getDefenderIndex((Player) killer);
			if (RandomFunction.randomize(32) == 10) {
				if (++defenderId == DEFENDERS.length) {
					defenderId--;
				}
				GroundItemManager.create(new Item(DEFENDERS[defenderId]), e.getLocation(), (Player) killer);
			}
		}
		return false;
	}

	@Override
	public void configure() {
		super.register(new ZoneBorders(2838, 3534, 2876, 3556));
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		PULSE.stop();
		PluginManager.definePlugin(new KamfreenaDial());
		PluginManager.definePlugin(new OptionHandler() {

			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				ObjectDefinition.forId(15641).getHandlers().put("option:open", this);
				ObjectDefinition.forId(15644).getHandlers().put("option:open", this);
				return this;
			}

			@Override
			public boolean handle(Player player, Node node, String option) {
				if (player.getLocation().getX() <= 2846) {
					Item tokens = new Item(8851, 100);
					if (!player.getInventory().containsItem(tokens) && !SkillcapePerks.hasSkillcapePerk(player, SkillcapePerks.ATTACK)) {
						player.getDialogueInterpreter().sendItemMessage(tokens, "You don't have enough Warrior Guild Tokens to enter", "the cyclopes enclosure yet, collect at least 100 then", "come back.");
						return true;
					}
					if (!player.getAttribute("sent_dialogue", false)) {
						player.getDialogueInterpreter().open(DialogueInterpreter.getDialogueKey("defender entry"), getDefenderIndex(player));
						return true;
					}
					player.removeAttribute("sent_dialogue");
					enter(player);
				} else {
					leave(player);
					PLAYERS.remove(player);
				}
				DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
				return true;
			}

		});
		return this;
	}

	/**
	 * Gets the current defender index.
	 * @param player The player.
	 * @return The defender index.
	 */
	private static int getDefenderIndex(Player player) {
		int index = -1;
		for (int i = DEFENDERS.length - 1; i >= 0; i--) {
			int id = DEFENDERS[i];
			if (player.getEquipment().getNew(EquipmentContainer.SLOT_SHIELD).getId() == id || player.getInventory().contains(id, 1)) {
				index = i;
				break;
			}
		}
		return index;
	}

	/**
	 * Enters the cyclopes room.
	 * @param player The player.
	 */
	private static void enter(Player player) {
		if (!PLAYERS.contains(player)) {
			PLAYERS.add(player);
		}
		if (!PULSE.isRunning()) {
			PULSE.restart();
			PULSE.start();
			GameWorld.getPulser().submit(PULSE);
		}
	}

	/**
	 * Leaves the room.
	 * @param player The player.
	 */
	private static void leave(Player player) {
		player.removeAttribute("cyclops_ticks");
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	/**
	 * The Kamfreena entrance dialogue.
	 * @author Emperor
	 */
	private static class KamfreenaDial extends DialoguePlugin {

		/**
		 * The current defender item id.
		 */
		private int defenderId;

		/**
		 * Constructs a new {@code KamfreenaDial} {@code Object}.
		 */
		public KamfreenaDial() {
			super();
		}

		/**
		 * Constructs a new {@code KamfreenaDial} {@code Object}.
		 * @param player The player.
		 */
		public KamfreenaDial(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new KamfreenaDial(player);
		}

		@Override
		public boolean open(Object... args) {
			defenderId = (Integer) args[0];
			if (defenderId == -1) {
				npc(4289, "Well, since you haven't shown me a defender to prove", "your prowess as a warrior,");
			} else {
				npc(4289, "Ahh I see that you have one of the defenders already!", "Well done.");
			}
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				player.setAttribute("sent_dialogue", true);
				if (defenderId == DEFENDERS.length - 1) {
					npc(4289, "I'll release some cyclopes which might drop the same", "rune defender for you as there isn't any higher! Have", "fun in there.");
				} else if (defenderId == -1) {
					npc(4289, "I'll release some cyclopes which might drop bronze", "defenders for you to start off with, unless you show me", "another. Have fun in there.");
				} else {
					npc(4289, "I'll release some cyclopes which might drop the next", "defender for you. Have fun in there.");
				}
				stage = 1;
				break;
			case 1:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { DialogueInterpreter.getDialogueKey("defender entry") };
		}

	}

}
