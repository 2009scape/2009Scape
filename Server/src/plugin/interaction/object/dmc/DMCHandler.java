package plugin.interaction.object.dmc;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.combat.CombatSwingHandler;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.portal.Perks;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.LogoutTask;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.zone.ZoneRestriction;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.Plugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles a player's Dwarf Multi-cannon.
 * @author Emperor
 */
public final class DMCHandler {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The cannon object.
	 */
	private GameObject cannon;

	/**
	 * The amount of cannonballs loaded.
	 */
	private int cannonballs;

	/**
	 * The logout plugin.
	 */
	private Plugin<Player> logoutPlugin;

	/**
	 * The firing pulse.
	 */
	private Pulse firingPulse;

	/**
	 * The current direction.
	 */
	private DMCRevolution direction = DMCRevolution.NORTH;

	/**
	 * The decaying pulse.
	 */
	private Pulse decayPulse;

	/**
	 * Constructs a new {@code DMCHandler} {@code Object}
	 * @param player The player owning the cannon.
	 */
	public DMCHandler(final Player player) {
		this.player = player;
		this.firingPulse = new Pulse(1, player) {
			@Override
			public boolean pulse() {
				if (!cannon.isActive()) {
					return true;
				}
				return rotate();
			}

		};
		firingPulse.stop();
		this.decayPulse = new Pulse(2000, player) {
			@Override
			public boolean pulse() {
				if (!cannon.isActive()) {
					return true;
				}
				if (getDelay() == 2000) {
					setDelay(500);
					player.sendMessage("Your cannon is about to decay!");
					return false;
				}
				explode(true);
				return true;
			}

		};
		decayPulse.stop();
	}

	/**
	 * Rotates the cannon.
	 * @return {@code True} if the cannon should stop rotating.
	 */
	private boolean rotate() {
		if (cannonballs < 1) {
			player.getPacketDispatch().sendMessage("Your cannon has run out of ammo!");
			return true;
		}
		player.getPacketDispatch().sendObjectAnimation(cannon, Animation.create(direction.getAnimationId()));
		Location l = cannon.getLocation().transform(1, 1, 0);
		direction = DMCRevolution.values()[(direction.ordinal() + 1) % DMCRevolution.values().length];
		for (NPC npc : RegionManager.getLocalNpcs(l, 10)) {
			if (direction.isInSight(npc.getLocation().getX() - l.getX(), npc.getLocation().getY() - l.getY()) && npc.isAttackable(player, CombatStyle.RANGE) && CombatSwingHandler.isProjectileClipped(npc, l, false)) {
				int speed = (int) (25 + (l.getDistance(npc.getLocation()) * 10));
				Projectile.create(l, npc.getLocation(), 53, 40, 36, 20, speed, 0, 128).send();
				cannonballs--;
				int hit = 0;
				if (player.getSwingHandler(false).isAccurateImpact(player, npc, CombatStyle.RANGE, 1.2, 1.0)) {
					hit = RandomFunction.getRandom(30);
				}
				player.getSkills().addExperience(Skills.RANGE, hit * 2);
				npc.getImpactHandler().manualHit(player, hit, HitsplatType.NORMAL, (int) Math.ceil(l.getDistance(npc.getLocation()) * 0.3));
				npc.attack(player);
				break;
			}
		}
		return false;
	}

	/**
	 * Starts rotating the cannon.
	 */
	public void startFiring() {
		if (cannon == null || !cannon.isActive()) {
			player.getPacketDispatch().sendMessage("You don't have a cannon active.");
			return;
		}
		if (firingPulse.isRunning()) {
			firingPulse.stop();
			return;
		}
		if (cannonballs < 1) {
			int amount = player.getInventory().getAmount(new Item(2));
			if (amount < 1) {
				player.getPacketDispatch().sendMessage("Your cannon is out of cannonballs.");
				return;
			}
			int maxAmount = player.getDetails().getShop().hasPerk(Perks.DWARF_BEFRIENDER) ? 60 : 30;
			if (amount > maxAmount) {
				amount = maxAmount;
			}
			if (player.getInventory().remove(new Item(2, amount))) {
				cannonballs = amount;
				player.getPacketDispatch().sendMessage("You load the cannon with " + amount + " cannonballs.");
			}
		}
		firingPulse.restart();
		firingPulse.start();
		GameWorld.Pulser.submit(firingPulse);
	}

	/**
	 * Makes the cannon explode.
	 */
	public void explode(boolean decay) {
		if (!cannon.isActive()) {
			return;
		}
		player.sendMessage("Your cannon has " + (decay ? "decayed" : "been destroyed") + "!");
		for (Player p : RegionManager.getLocalPlayers(player)) {
			p.getPacketDispatch().sendPositionedGraphic(189, 0, 1, cannon.getLocation());
		}
		clear(false);
	}

	/**
	 * Constructs the cannon.
	 */
	public static void construct(final Player player) {
		final Location spawn = RegionManager.getSpawnLocation(player, new GameObject(6, player.getLocation()));
		if (spawn == null) {
			player.getPacketDispatch().sendMessage("There's not enough room for your cannon.");
			return;
		}
		if (player.getZoneMonitor().isRestricted(ZoneRestriction.CANNON)) {
			player.getPacketDispatch().sendMessage("You can't set up a cannon here.");
			return;
		}
		final DMCHandler handler = new DMCHandler(player);
		if (handler.decayPulse.isRunning()) {
			handler.decayPulse.stop();
			return;
		}
		player.setAttribute("dmc", handler);
		player.getPulseManager().clear();
		player.getWalkingQueue().reset();
		player.lock(9);
		player.faceLocation(spawn.transform(Direction.NORTH_EAST));
		GameWorld.Pulser.submit(new Pulse(2, player) {
			int count = 0;
			GameObject object = null;

			@Override
			public boolean pulse() {
				player.animate(Animation.create(827));
				if (!player.getInventory().remove(new Item(6 + (count * 2)))) {
					for (int i = count - 1; i >= 0; i--) {
						player.getInventory().add(new Item(6 + (i * 2)));
					}
					if (object != null) {
						ObjectBuilder.remove(object);
					}
					return true;
				}
				player.addExtension(LogoutTask.class, new LogoutTask() {
					int amount = count + 1;

					@Override
					public void run(Player player) {
						for (int i = 0; i < amount; i++) {
							player.getInventory().add(new Item(6 + (i * 2)));
						}
						if (object != null) {
							ObjectBuilder.remove(object);
						}
					}
				});
				switch (count) {
				case 0:
					object = ObjectBuilder.add(new GameObject(7, spawn));
					player.getPacketDispatch().sendMessage("You place the cannon base on the ground.");
					return count++ == 666;
				case 1:
					player.getPacketDispatch().sendMessage("You add the stand.");
					break;
				case 2:
					player.getPacketDispatch().sendMessage("You add the barrels.");
					break;
				case 3:
					player.getPacketDispatch().sendMessage("You add the furnace.");
					ObjectBuilder.remove(object);
					handler.configure(ObjectBuilder.add(object = object.transform(6)));
					return true;
				}
				if (!player.getDetails().getShop().hasPerk(Perks.DWARF_BEFRIENDER)) {
					handler.decayPulse.restart();
					handler.decayPulse.start();
					GameWorld.Pulser.submit(handler.decayPulse);
				}
				ObjectBuilder.remove(object);
				ObjectBuilder.add(object = object.transform(object.getId() + 1));
				return ++count == 4;
			}
		});
	}

	/**
	 * Configures the cannon.
	 * @param cannon The cannon.
	 */
	public void configure(GameObject cannon) {
		this.cannon = cannon;
		player.removeExtension(LogoutTask.class);
		player.getLogoutPlugins().add(logoutPlugin = new Plugin<Player>() {
			@Override
			public Plugin<Player> newInstance(Player arg) throws Throwable {
				clear(false);
				return this;
			}

			@Override
			public Object fireEvent(String identifier, Object... args) {
				return null;
			}
		});
	}

	/**
	 * Clears the cannon.
	 * @param pickup If the cannon is getting picked up.
	 */
	public void clear(boolean pickup) {
		if (decayPulse.isRunning()) {
			decayPulse.stop();
		}
		ObjectBuilder.remove(cannon);
		player.removeAttribute("dmc");
		player.getLogoutPlugins().remove(logoutPlugin);
		if (!pickup) {
			player.getSavedData().getActivityData().setLostCannon(true);
			return;
		}
		for (int i = 0; i < 4; i++) {
			player.getInventory().add(new Item(12 - (i * 2)));
		}
		if (cannonballs > 0) {
			player.getInventory().add(new Item(2, cannonballs));
			cannonballs = 0;
		}
	}

	/**
	 * Gets the player.
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the cannon.
	 * @return the cannon
	 */
	public GameObject getCannon() {
		return cannon;
	}

	/**
	 * Sets the cannon.
	 * @param cannon the cannon to set.
	 */
	public void setCannon(GameObject cannon) {
		this.cannon = cannon;
	}

	/**
	 * Gets the cannonballs.
	 * @return the cannonballs
	 */
	public int getCannonballs() {
		return cannonballs;
	}

	/**
	 * Sets the bacannonballs.
	 * @param cannonballs the cannonballs to set.
	 */
	public void setCannonballs(int cannonballs) {
		this.cannonballs = cannonballs;
	}

}