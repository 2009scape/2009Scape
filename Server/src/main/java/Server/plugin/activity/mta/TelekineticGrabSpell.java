package plugin.activity.mta;

import core.game.content.global.action.PickupHandler;
import plugin.SpellBlocks;
import plugin.skill.magic.MagicSpell;
import plugin.skill.magic.Runes;
import core.game.interaction.SpecialGroundItems;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.equipment.SpellType;
import core.game.node.entity.impl.Projectile;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.SpellBookManager.SpellBook;
import core.game.node.entity.player.link.audio.Audio;
import core.game.node.item.GroundItem;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.plugin.Plugin;

import plugin.activity.mta.impl.TelekineticZone;

/**
 * Represents the telekenitic grab spell.
 * @author 'Vexia
 * @author Emperor
 * @version 1.0
 */
public final class TelekineticGrabSpell extends MagicSpell {

	/**
	 * Represents the animation to use.
	 */
	private static final Animation ANIMATION = new Animation(2310);

	/**
	 * Represents the graphics to use.
	 */
	private static final Graphics START_GRAPHIC = new Graphics(142, 88, 15);

	/**
	 * Represents the end graphics to use.
	 */
	private static final Graphics END_GRAPHIC = new Graphics(144);

	/**
	 * Represents the sound to use.
	 */
	private static final Audio SOUND = new Audio(3007, 10, 1);

	/**
	 * Represents the projectile id to use.
	 */
	private static final int PROJECTILE_ID = 143;

	/**
	 * Represents the start height.
	 */
	private static final int START_HEIGHT = 40;

	/**
	 * Represents the end height.
	 */
	private static final int END_HEIGHT = 0;

	/**
	 * Represents the starting delay.
	 */
	private static final int START_DELAY = 41;

	/**
	 * Represents the angle.
	 */
	private static final int ANGLE = 5;

	/**
	 * Represents the spell id.
	 */
	public static final int SPELL_ID = 65535;

	/**
	 * Constructs a new {@code TelekineticGrabSpell} {@code Object}.
	 */
	public TelekineticGrabSpell() {
		super(SpellBook.MODERN, 33, 43, ANIMATION, START_GRAPHIC, SOUND, new Item[] { new Item(Runes.AIR_RUNE.getId()), new Item(Runes.LAW_RUNE.getId(), 1) });
	}

	@Override
	public Plugin<SpellType> newInstance(SpellType arg) throws Throwable {
		SpellBook.MODERN.register(SPELL_ID, this);
		return this;
	}

	@Override
	public boolean cast(final Entity entity, final Node target) {
		final GroundItem ground = (GroundItem) target;
		if (!canCast(entity, ground)) {
			return false;
		}
		entity.lock(2);
		visualize(entity, target);
		GameWorld.Pulser.submit(getGrabPulse(entity, ground));
		return true;
	}

	@Override
	public void visualize(final Entity entity, final Node target) {
		super.visualize(entity, target);
		entity.faceLocation(target.getLocation());
		getProjectile(entity, (GroundItem) target).send();
	}

	/**
	 * Method used to get the grab pulse.
	 * @param entity the entity.
	 * @param item the item.
	 * @param delay the delay.
	 * @return the {@code Pulse}.
	 */
	public Pulse getGrabPulse(final Entity entity, final GroundItem ground) {
		return new Pulse(getDelay(ground.getLocation().getDistance(entity.getLocation())), entity) {
			@Override
			public boolean pulse() {
				Player player = entity instanceof Player ? (Player) entity : null;
				GroundItem g = GroundItemManager.get(ground.getId(), ground.getLocation(), player);
				if (g == null) {
					player.getPacketDispatch().sendMessage("Too late!");
					return true;
				}
				if (g == SpecialGroundItems.AHAB_BEER.asGroundItem()){
					player.getDialogueInterpreter().open(2692, new NPC(2692), true);
					return true;
				}
				if (player == null) {
					return true;
				}
				boolean teleZone = player.getZoneMonitor().isInZone("Telekinetic Theatre") && g.getId() == 6888;
				if (player != null) {
					if (g == null || !g.isActive()) {
						player.getPacketDispatch().sendMessage("Too late!");
						return true;
					}
					player.getAudioManager().send(3008);
					if (!teleZone) {
						player.getInventory().add(new Item(g.getId(), g.getAmount(), g.getCharge()));
					} else {
						TelekineticZone zone = TelekineticZone.getZone(player);
						zone.moveStatue();
						player.lock(getDelay());
					}
					player.getPacketDispatch().sendPositionedGraphics(END_GRAPHIC, ground.getLocation());
				}
				if (!teleZone) {
					GroundItemManager.destroy(g);
				}
				return true;
			}
		};
	}

	/**
	 * Method used to check if the spell can be casted.
	 * @param entity the entity.
	 * @param item the item to grab.
	 * @return {@code True} if so.
	 */
	public boolean canCast(final Entity entity, final GroundItem item) {
		if (entity.getLocks().isInteractionLocked() || entity.getLocks().isComponentLocked()) {
			return false;
		}
		if (entity instanceof Player) {
			final Player player = (Player) entity;
			if (!player.getInventory().hasSpaceFor(((Item) item))) {
				player.getPacketDispatch().sendMessage("You don't have enough inventory space.");
				return false;
			}
			if (!PickupHandler.canTake(player, item, 1)) {
				return false;
			}
		}
		if(SpellBlocks.isBlocked(SPELL_ID,(Node) item)){
			if(entity instanceof Player){
				entity.asPlayer().getDialogueInterpreter().sendDialogue("You can't do that.");
			}
			return false;
		}
		return super.meetsRequirements(entity, true, true);
	}

	/**
	 * Gets the projectile.
	 * @param entity the entity.
	 * @param item the item.
	 * @return the projectile.
	 */
	public Projectile getProjectile(Entity entity, GroundItem item) {
		return Projectile.create(entity.getLocation(), item.getLocation(), PROJECTILE_ID, START_HEIGHT, END_HEIGHT, START_DELAY, Projectile.getSpeed(entity, item.getLocation()), ANGLE, 11);
	}

	/**
	 * Gets the delay from the distance.
	 * @param distance the distance.
	 * @return the delay.
	 */
	public int getDelay(double distance) {
		return (int) (2 + (distance * 0.5));
	}

}
