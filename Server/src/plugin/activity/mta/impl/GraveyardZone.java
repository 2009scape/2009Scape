package plugin.activity.mta.impl;

import java.util.ArrayList;
import java.util.List;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.Option;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.tools.RandomFunction;
import org.crandor.tools.StringUtils;

import plugin.activity.mta.MTAType;
import plugin.activity.mta.MTAZone;

/**
 * Handles the creature graveyard zone.
 * @author Vexia
 */
public class GraveyardZone extends MTAZone {

	/**
	 * The graveyard zone.
	 */
	public static final GraveyardZone ZONE = new GraveyardZone();

	/**
	 * The players in the zone.
	 */
	private static final List<Player> PLAYERS = new ArrayList<>();

	/**
	 * The runes rewarded by a chance.
	 */
	private static final Item[] RUNES = new Item[] { new Item(561), new Item(555), new Item(557) };

	/**
	 * The banana item.
	 */
	private static final Item BANANA = new Item(1963);

	/**
	 * The peach item.
	 */
	private static final Item PEACH = new Item(6883);

	/**
	 * The positions for the graphics.
	 */
	private static final Location[] GFX_POS = new Location[] { Location.create(3370, 9634, 1), Location.create(3359, 9634, 1), Location.create(3360, 9643, 1), Location.create(3363, 9643, 1), Location.create(3363, 9633, 1), Location.create(3367, 9637, 1), Location.create(3366, 9642, 1), Location.create(3380, 9647, 1), Location.create(3375, 9648, 1), Location.create(3370, 9649, 1), Location.create(3372, 9644, 1), Location.create(3379, 9646, 1), Location.create(3381, 9651, 1), Location.create(3379, 9656, 1), Location.create(3373, 9658, 1), Location.create(3353, 9648, 1), Location.create(3356, 9653, 1), Location.create(3346, 9657, 1), Location.create(3347, 9655, 1), Location.create(3354, 9634, 1), Location.create(3352, 9631, 1), Location.create(3355, 9627, 1), Location.create(3345, 9631, 1), Location.create(3345, 9627, 1), Location.create(3347, 9623, 1), Location.create(3351, 9621, 1), Location.create(3368, 9624, 1), Location.create(3368, 9631, 1), Location.create(3375, 9630, 1), Location.create(3375, 9635, 1), Location.create(3371, 9621, 1), Location.create(3377, 9621, 1), Location.create(3381, 9624, 1), Location.create(3382, 9629, 1), Location.create(3371, 9635, 1), Location.create(3365, 9634, 1), Location.create(3361, 9641, 1), Location.create(3363, 9644, 1) };

	/**
	 * The pulse.
	 */
	private static final Pulse PULSE = new Pulse(6) {
		@Override
		public boolean pulse() {
			if (PLAYERS.isEmpty()) {
				return true;
			}
			List<Location> locs = new ArrayList<>();
			for (Location l : GFX_POS) {
				if (RandomFunction.random(12) >= 8) {
					continue;
				}
				locs.add(l);
			}
			for (Player player : PLAYERS) {
				if (player == null || !player.isActive()) {
					continue;
				}
				for (Location l : locs) {
					player.getPacketDispatch().sendPositionedGraphics(Graphics.create(520), l);
				}
				if (player.getDialogueInterpreter().getDialogue() == null) {
					player.getImpactHandler().manualHit(player, 2, HitsplatType.NORMAL);
				}
			}
			locs = null;
			setDelay(RandomFunction.random(6, 12));
			return false;
		}
	};

	/**
	 * Constructs a new {@Code GraveyardZone} {@Code Object}
	 */
	public GraveyardZone() {
		super("Creature Graveyard", new Item[] { new Item(6904), new Item(6905), new Item(6906), new Item(6907), new Item(1963), new Item(6883) });
	}
	
	
	@Override
	public void update(Player player) {
		player.getPacketDispatch().sendString("" + player.getSavedData().getActivityData().getPizazzPoints(getType().ordinal()), getType().getOverlay().getId(), 12);
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e instanceof Player && PLAYERS.contains(e)) {
			PLAYERS.remove(e);
		}
		return super.leave(e, logout);
	}

	@Override
	public boolean enter(Entity e) {
		if (e instanceof Player && !PLAYERS.contains(e)) {
			PLAYERS.add(e.asPlayer());
			if (!PULSE.isRunning()) {
				PULSE.restart();
				PULSE.start();
				GameWorld.Pulser.submit(PULSE);
			}
		}
		return super.enter(e);
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (e instanceof Player) {
			Player player = e.asPlayer();
			if (target.getId() == 10735) {
				deposit(player);
				return true;
			} else if (target.getId() >= 10725 && target.getId() <= 10728) {
				BoneType.forObject(target.getId()).grab(player, target.asObject());
				return true;
			}
		}
		return super.interact(e, target, option);
	}

	@Override
	public boolean death(Entity e, Entity killer) {
		if (e instanceof Player) {
			Player p = e.asPlayer();
			int points = p.getSavedData().getActivityData().getPizazzPoints(MTAType.GRAVEYARD.ordinal());
			if (points >= 10) {
				p.getSavedData().getActivityData().decrementPizazz(MTAType.GRAVEYARD.ordinal(), 10);
			} else {
				p.getSavedData().getActivityData().decrementPizazz(MTAType.GRAVEYARD.ordinal(), points);
			}
		}
		return super.death(e, killer);
	}

	/**
	 * Deposits the fruit into the chute.
	 * @param player the player.
	 */
	private void deposit(Player player) {
		if (!player.getInventory().containsItem(BANANA) && !player.getInventory().containsItem(PEACH)) {
			player.getDialogueInterpreter().sendDialogue("You don't have any bananas or peaches to deposit.");
			return;
		}
		int totalAmount = player.getAttribute("grave-amt", 0);
		int amount = 0;
		Item[] items = new Item[] { BANANA, PEACH };
		for (Item item : items) {
			if (player.getInventory().containsItem(item)) {
				amount = player.getInventory().getAmount(item);
				totalAmount += amount;
				player.getInventory().remove(new Item(item.getId(), amount));
			}
		}
		if (totalAmount >= 16) {
			if (totalAmount > 16) {
				totalAmount = totalAmount - 16;
			} else {
				totalAmount = 0;
			}
			Item rune = RandomFunction.getRandomElement(RUNES);
			String name = rune.getName().toLowerCase();
			player.getInventory().add(rune, player);
			incrementPoints(player, MTAType.GRAVEYARD.ordinal(), 1);
			player.getSkills().addExperience(Skills.MAGIC, 50, true);
			player.getDialogueInterpreter().sendDialogue("Congratulations - you've been awarded a" + (StringUtils.isPlusN(name) ? "n" : "") + " " + name + " and extra", "magic XP.");
		}
		player.setAttribute("grave-amt", totalAmount);
	}

	@Override
	public void configure() {
		PULSE.stop();
		register(new ZoneBorders(3333, 9610, 3390, 9663, 1, true));
	}

	/**
	 * A bone type.
	 * @author Vexia
	 */
	public enum BoneType {
		FIRST(10725, new Item(6904)), SECOND(10726, new Item(6905)), THIRD(10727, new Item(6906)), FOURTH(10728, new Item(6907));

		/**
		 * The object id of the bone.
		 */
		private final int objectId;

		/**
		 * The item of the bone.
		 */
		private final Item item;

		/**
		 * Constructs a new {@Code BoneType} {@Code Object}
		 * @param objectId the object id.
		 * @param item the item.
		 */
		private BoneType(int objectId, Item item) {
			this.objectId = objectId;
			this.item = item;
		}

		/**
		 * Grabs the bone from the object.
		 * @param player the player.
		 * @param object the object.
		 */
		public void grab(Player player, GameObject object) {
			if (player.getInventory().freeSlots() < 1) {
				player.sendMessage("You have no free space!");
				return;
			}
			int life = object.getAttributes().getAttribute("life", 4);
			player.lock(1);
			player.getInventory().add(item);
			player.animate(Animation.create(827));
			life -= 1;
			if (life < 1) {
				life = 4;
				BoneType type = ordinal() + 1 > BoneType.values().length - 1 ? BoneType.FIRST : BoneType.values()[ordinal() + 1];
				ObjectBuilder.replace(object, object.transform(type.getObjectId()));
			}
			object.getAttributes().setAttribute("life", life);
		}

		/**
		 * Gets a bone type for the item.
		 * @param item the item.
		 * @return the bone type.
		 */
		public static BoneType forItem(Item item) {
			for (BoneType type : values()) {
				if (type.getItem().getId() == item.getId()) {
					return type;
				}
			}
			return null;
		}

		/**
		 * Gets the bone type by the object id.
		 * @param objectId the id.
		 * @return the type.
		 */
		public static BoneType forObject(int objectId) {
			for (BoneType type : values()) {
				if (type.getObjectId() == objectId) {
					return type;
				}
			}
			return null;
		}

		/**
		 * Gets the objectId.
		 * @return the objectId
		 */
		public int getObjectId() {
			return objectId;
		}

		/**
		 * Gets the item.
		 * @return the item
		 */
		public Item getItem() {
			return item;
		}

	}
}
