package plugin.zone;

import org.crandor.game.content.global.action.ClimbActionHandler;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.agility.AgilityHandler;
import org.crandor.game.interaction.Option;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.CombatSpell;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.state.EntityState;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.zone.MapZone;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.game.world.map.zone.ZoneBuilder;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles the yanille agility dungeon.
 * @author Vexia
 */
@InitializablePlugin
public class YanilleAgilityDungeon extends MapZone implements Plugin<Object> {

	/**
	 * The herb items.
	 */
	private static final Item[] HERBS = new Item[] { new Item(205, 2), new Item(207, 3), new Item(209), new Item(211), new Item(213), new Item(219) };

	/**
	 * Constructs a new {@Code YanilleAgilityDungeon} {@Code
	 * Object}
	 */
	public YanilleAgilityDungeon() {
		super("Yanilee agility", true);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		PluginManager.definePlugin(new SalarinTwistedNPC());
		return this;
	}

	@Override
	public boolean interact(Entity entity, Node target, Option option) {
		if (entity instanceof Player) {
			Player player = entity.asPlayer();
			switch (target.getId()) {
			case 1728:
				player.teleport(new Location(2620, 9565, 0));
				return true;
			case 1729:
				player.teleport(new Location(2620, 9496, 0));
				return true;
			case 2316:
				player.teleport(Location.create(2569, 9525, 0));
				return true;
			case 2318:
			case 2317:
				if (player.getSkills().getLevel(Skills.AGILITY) < 67) {
					player.getDialogueInterpreter().sendDialogue("You need an Agility level of at least 67 in order to do this.");
					return true;
				}
				Location loc = target.getId() == 2317 ? new Location(2615, 9503, 0) : new Location(2617, 9572, 0);
				ClimbActionHandler.climb(player, target.getId() == 2317 ? ClimbActionHandler.CLIMB_UP : ClimbActionHandler.CLIMB_DOWN, loc);
				player.sendMessage("You climb " + (target.getId() == 2317 ? "up" : "down") + " the pile of rubble...");
				player.getSkills().addExperience(Skills.AGILITY, 5.5, true);
				return true;
			case 35969:
			case 2303:
				handleBalancingLedge(player, target.asObject());
				return true;
			case 377:
				if (!player.getInventory().contains(993, 1)) {
					player.sendMessage("The chest is locked.");
				} else {
					if (player.getInventory().freeSlots() == 0) {
						player.sendMessage("You don't have enough inventory space.");
						return true;
					}
					player.lock(1);
					player.getInventory().remove(new Item(993));
					player.sendMessages("You unlock the chest with your key...", "A foul gas seeps from the chest");
					player.getStateManager().register(EntityState.POISONED, true, 28, player);
					for (Item item : HERBS) {
						player.getInventory().add(item, player);
					}
					ObjectBuilder.replace(target.asObject(), target.asObject().transform(target.getId() + 1), 5);
				}
				return true;
			case 378:
				switch (option.getName().toLowerCase()) {
				case "search":
					player.sendMessage("The chest is empty.");
					break;
				case "shut":
					break;
				}
				return true;
			}
		}
		return super.interact(entity, target, option);
	}

	/**
	 * Handles the balancing ledge.
	 * @param player the player.
	 * @param object the object.
	 */
	private void handleBalancingLedge(final Player player, GameObject object) {
		if (player.getSkills().getLevel(Skills.AGILITY) < 40) {
			player.getDialogueInterpreter().sendDialogue("You need an Agility level of at least 40 in order to do this.");
			return;
		}
		final Direction dir = Direction.getLogicalDirection(player.getLocation(), object.getLocation());
		final int diff = player.getLocation().getY() == 9512 ? 0 : 1;
		Location end = object.getLocation();
		double xp = 0.0;
		if (AgilityHandler.hasFailed(player, 40, 0.01)) {
			player.lock(3);
			GameWorld.Pulser.submit(new Pulse(2, player) {
				@Override
				public boolean pulse() {
					AgilityHandler.fail(player, 1, new Location(2572, 9570, 0), Animation.create(761 - diff), RandomFunction.random(1, 3), "You lost your balance!");
					return true;
				}
			});
		} else {
			xp = 22.5;
			end = object.getLocation().transform(dir.getStepX() * 7, dir.getStepY() * 7, 0);
		}
		AgilityHandler.walk(player, -1, player.getLocation(), end, Animation.create(157 - diff), xp, null);
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public void configure() {
		register(new ZoneBorders(2544, 9481, 2631, 9587));
	}

	/**
	 * Handles the salarin twisted npc.
	 * @author Vexia
	 */
	public static class SalarinTwistedNPC extends AbstractNPC {

		/**
		 * The spell ids.
		 */
		private static final int[] SPELL_IDS = new int[] { 1, 4, 6, 8 };

		/**
		 * Constructs a new {@Code SalarinTwistedNPC} {@Code
		 * Object}
		 */
		public SalarinTwistedNPC() {
			super(-1, null);
		}

		/**
		 * Constructs a new {@Code SalarinTwistedNPC} {@Code
		 * Object}
		 * @param id the id.
		 * @param location the location.
		 */
		public SalarinTwistedNPC(int id, Location location) {
			super(id, location);
			super.setAggressive(true);
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new SalarinTwistedNPC(id, location);
		}

		@Override
		public void checkImpact(BattleState state) {
			if (state.getStyle() != CombatStyle.MAGIC) {
				state.neutralizeHits();
				return;
			}
			if (state.getSpell() == null) {
				state.neutralizeHits();
				return;
			}
			CombatSpell spell = state.getSpell();
			for (int id : SPELL_IDS) {
				if (id == spell.getSpellId()) {
					return;
				}
			}
			state.neutralizeHits();
		}

		@Override
		public int[] getIds() {
			return new int[] { 205 };
		}

	}

}
