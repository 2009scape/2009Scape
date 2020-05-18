package plugin.activity.gwd;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.component.Component;
import org.crandor.game.container.impl.EquipmentContainer;
import org.crandor.game.content.global.action.DoorActionHandler;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.MovementPulse;
import org.crandor.game.interaction.Option;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.equipment.RangeWeapon;
import org.crandor.game.node.entity.impl.ForceMovement;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.Rights;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.zone.MapZone;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.game.world.map.zone.ZoneBuilder;
import org.crandor.game.world.map.zone.ZoneRestriction;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.MinimapStateContext;
import org.crandor.net.packet.out.MinimapState;
import org.crandor.plugin.Plugin;
import org.crandor.tools.RandomFunction;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.StringUtils;

/**
 * Handles the god wars map zone.
 * @author Emperor
 */
@InitializablePlugin
public final class GodwarsMapzone extends MapZone implements Plugin<Object> {

	/**
	 * The Zamorak's fortress area.
	 */
	private static final ZoneBorders ZAMORAK_FORTRESS = new ZoneBorders(2880, 5317, 2944, 5362);

	static {
		ZAMORAK_FORTRESS.addException(new ZoneBorders(2880, 5317, 2904, 5338));
	}

	/**
	 * Constructs a new {@code GodwarsMapzone} {@code Object}.
	 */
	public GodwarsMapzone() {
		super("Godwars", true, ZoneRestriction.RANDOM_EVENTS, ZoneRestriction.CANNON);
	}

	@Override
	public void configure() {
		register(new ZoneBorders(2816, 5248, 2943, 5375));
	}

	@Override
	public boolean enter(Entity e) {
		if (e instanceof Player) {
			Player player = (Player) e;
			int componentId = player.getInterfaceManager().isResizable() ? 597 : 601;
			if (ZAMORAK_FORTRESS.insideBorder(player.getLocation().getX(), player.getLocation().getY())) {
				componentId = player.getInterfaceManager().isResizable() ? 598 : 599;
			}
			openOverlay(player, componentId);
			if (player.getDetails().getRights() == Rights.ADMINISTRATOR) {
				for (GodWarsFaction faction : GodWarsFaction.values()) {
					increaseKillcount(player, faction, 40);
				}
			}
		}
		return true;
	}

	/**
	 * Sets the rope setting.
	 * @param player The player.
	 * @param setting The setting.
	 */
	public void setRopeSetting(Player player, int setting) {
		int value = player.getConfigManager().get(1048) | setting;
		player.getConfigManager().set(1048, value, true);
	}

	/**
	 * Opens the overlay.
	 * @param player The player.
	 * @param componentId The component id.
	 */
	private void openOverlay(Player player, int componentId) {
		player.setAttribute("gwd:overlay", componentId);
		player.getInterfaceManager().openOverlay(new Component(componentId));
		int child = (componentId == 601 || componentId == 599) ? 6 : 7;
		for (GodWarsFaction faction : GodWarsFaction.values()) {
			int amount = player.getAttribute("gwd:" + faction.name().toLowerCase() + "kc", 0);
			player.getPacketDispatch().sendString(Integer.toString(amount), componentId, child + faction.ordinal());
		}
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (!logout && e instanceof Player) {
			for (GodWarsFaction faction : GodWarsFaction.values()) {
				e.removeAttribute("gwd:" + faction.name().toLowerCase() + "kc");
			}
			e.removeAttribute("gwd:overlay");
			e.removeAttribute("gwd:altar-recharge");
			((Player) e).getInterfaceManager().closeOverlay();
		} else if (logout) {
			e.setLocation(e.getAttribute("cross_bridge_loc", e.getLocation()));
		}
		return true;
	}

	@Override
	public boolean death(Entity e, Entity killer) {
		if (killer instanceof Player && e instanceof NPC) {
			int npcId = ((NPC) e).getId();
			int count = killer.asPlayer().getBank().getAmount(14674) + killer.asPlayer().getInventory().getAmount(14674);
			int rand = RandomFunction.random(1, 60 + (count * 10));
			if(rand == 15 && count < 3){
				Item item = new Item(14674);
				GroundItemManager.create(item, e.getLocation(), ((Player) killer));
				killer.asPlayer().sendMessage("<col=990000>A crystalline key falls to the ground as you slay your opponent.</col>");	
			}
			increaseKillcount((Player) killer, GodWarsFaction.forId(npcId), 1);
		}
		return false;
	}

	@Override
	public void locationUpdate(Entity e, Location last) {
		if (e instanceof Player) {
			Player player = (Player) e;
			Component c = player.getInterfaceManager().getOverlay();
			boolean inZamorakFortress = ZAMORAK_FORTRESS.insideBorder(player.getLocation().getX(), player.getLocation().getY());
			if ((c == null || c.getId() != 598) && inZamorakFortress) {
				openOverlay(player, 598);
			} else if ((c == null || c.getId() != 597 && c.getId() != 601) && !inZamorakFortress) {
				openOverlay(player, player.getInterfaceManager().isResizable() ? 597 : 601);
			}
		}
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (target instanceof GameObject) {
			GameObject object = (GameObject) target;
			if (object.getId() == 26439) {
				handleIceBridge((Player) e, object);
				return true;
			}
			if (object.getId() == 26384) {
				handleBigDoor((Player) e, object, true);
				return true;
			}
			if (object.getId() == 26303) {
				handlePillarGrapple((Player) e, object);
				return true;
			}
			if (object.getId() == 26293) {
				handleRopeClimb((Player) e, Location.create(2915, 3746, 0));
				return true;
			}
			if (object.getId() == 26295) {
				handleRopeClimb((Player) e, Location.create(2915, 5300, 1));
				return true;
			}
			if (object.getId() == 26296) {
				handleRopeTie((Player) e, 0x2);
				return true;
			}
			if (object.getId() == 26297) {
				if (object.getLocation().getY() == 5300) {
					handleRopeClimb((Player) e, Location.create(2912, 5300, 2));
				} else {
					handleRopeClimb((Player) e, Location.create(2920, 5276, 1));
				}
				return true;
			}
			if (object.getId() == 26299) {
				handleRopeClimb((Player) e, Location.create(2919, 5274, 0));
				return true;
			}
			if (object.getId() == 26300) {
				handleRopeTie((Player) e, 0x4);
				return true;
			}
			if (object.getId() == 26286) {
				handleAltar((Player) e, option.getName(), GodWarsFaction.ZAMORAK, Location.create(2925, 5332, 2));
				return true;
			}
			if (object.getId() == 26287) {
				handleAltar((Player) e, option.getName(), GodWarsFaction.SARADOMIN, Location.create(2908, 5265, 0));
				return true;
			}
			if (object.getId() == 26288) {
				handleAltar((Player) e, option.getName(), GodWarsFaction.ARMADYL, Location.create(2839, 5295, 2));
				return true;
			}
			if (object.getId() == 26289) {
				handleAltar((Player) e, option.getName(), GodWarsFaction.BANDOS, Location.create(2863, 5354, 2));
				return true;
			}
			if (object.getId() >= 26425 && object.getId() <= 26428) {
				return handleChamberEntrance((Player) e, object);
			}
		}
		return false;
	}

	/**
	 * Handles praying on an altar.
	 * @param player The player.
	 * @param faction The god wars faction.
	 */
	private void handleAltar(Player player, String option, GodWarsFaction faction, Location destination) {
		if (!option.equals("Pray-at")) {
			player.getProperties().setTeleportLocation(destination);
			return;
		}
		if (player.getAttribute("gwd:altar-recharge", 0L) > System.currentTimeMillis()) {
			player.getPacketDispatch().sendMessage("The gods blessed you recently - this time they ignore your prayers.");
			return;
		}
		if (player.inCombat()) {
			player.getPacketDispatch().sendMessage("You can't use the altar while in combat.");
			return;
		}
		if (player.getSkills().getPrayerPoints() >= player.getSkills().getStaticLevel(5)) {
			player.getPacketDispatch().sendMessage("You already have full prayer points.");
			return;
		}
		player.lock(2);
		int total = player.getSkills().getStaticLevel(5) + faction.getProtectionItemAmount(player);
		player.animate(new Animation(645));
		player.getSkills().decrementPrayerPoints(player.getSkills().getPrayerPoints() - total);
		player.getPacketDispatch().sendMessage("You recharge your Prayer points.");
		int time = 600_000;
		player.setAttribute("/save:gwd:altar-recharge", System.currentTimeMillis() + time);
	}

	/**
	 * Handles the rope tying.
	 * @param player The player.
	 * @param type The rock tying type.
	 */
	private void handleRopeTie(Player player, int type) {
		if (player.getSkills().getStaticLevel(Skills.AGILITY) < 70) {
			player.getPacketDispatch().sendMessage("You need an Agility level of 70 to enter here.");
			return;
		}
		if (!player.getInventory().remove(new Item(954))) {
			player.getPacketDispatch().sendMessage("You don't have a rope to tie on this rock.");
			return;
		}
		setRopeSetting(player, type);
	}

	/**
	 * Handles the climbing of a rope.
	 * @param player The player.
	 * @param object The rope object.
	 */
	private void handleRopeClimb(final Player player, final Location destination) {
		player.lock(2);
		player.animate(Animation.create(828));
		GameWorld.Pulser.submit(new Pulse(1, player) {
			@Override
			public boolean pulse() {
				player.getProperties().setTeleportLocation(destination);
				return true;
			}
		});
	}

	/**
	 * Handles the pillar grappling.
	 * @param player The player.
	 * @param object The pillar object.
	 */
	private void handlePillarGrapple(final Player player, final GameObject object) {
		if (player.getSkills().getStaticLevel(Skills.RANGE) < 70) {
			player.getPacketDispatch().sendMessage("You need a Range level of 70 to enter here.");
			return;
		}
		if (player.getEquipment().getNew(EquipmentContainer.SLOT_ARROWS).getId() != 9419) {
			player.getPacketDispatch().sendMessage("You need a mithril grapple to cross this.");
			return;
		}
		RangeWeapon weapon = RangeWeapon.get(player.getEquipment().getNew(3).getId());
		if (weapon == null || weapon.getType() != 1) {
			player.getPacketDispatch().sendMessage("You need to wield a crossbow to fire a mithril grapple.");
			return;
		}
		player.lock(4);
		if (player.getLocation().getY() < object.getLocation().getY()) {
			ForceMovement.run(player, Location.create(2872, 5269, 2), Location.create(2872, 5279, 2), Animation.create(7081), 60).setCommenceSpeed(3);
		} else {
			ForceMovement.run(player, Location.create(2872, 5279, 2), Location.create(2872, 5269, 2), Animation.create(7081), 60).setCommenceSpeed(3);
		}
		player.graphics(new Graphics(1036, 96, 30));
	}

	/**
	 * Handles the big door.
	 * @param player The player.
	 * @param object The door object.
	 */
	private void handleBigDoor(final Player player, final GameObject object, boolean checkLocation) {
		player.lock(4);
		if (checkLocation && player.getLocation().getX() > object.getLocation().getX()) {
			GameWorld.Pulser.submit(new MovementPulse(player, object.getLocation()) {
				@Override
				public boolean pulse() {
					handleBigDoor(player, object, false);
					return true;
				}
			});
			return;
		}
		if (player.getSkills().getStaticLevel(Skills.STRENGTH) < 70) {
			player.getPacketDispatch().sendMessage("You need a Strength level of 70 to enter here.");
			return;
		}
		if (!player.getInventory().contains(2347, 1)) {
			player.getPacketDispatch().sendMessage("You need a hammer to bang on the door.");
			return;
		}
		player.getPacketDispatch().sendMessage("You bang on the big door.");
		player.animate(Animation.create(7002));
		GameWorld.Pulser.submit(new Pulse(1, player) {
			@Override
			public boolean pulse() {
				object.getDefinition().getOptions()[1] = "open";
				ObjectDefinition.getOptionHandler(object.getId(), "open").handle(player, object, "open");
				return true;
			}
		});
	}

	/**
	 * Handles a chamber door opening reward.
	 * @param player The player.
	 * @param object The object.
	 * @return {@code True} if the player can't pass.
	 */
	private boolean handleChamberEntrance(Player player, GameObject object) {
		Direction dir = Direction.get((object.getRotation() + 3) % 4);
		if (dir.getStepX() != 0) {
			if (player.getLocation().getX() == object.getLocation().transform(dir.getStepX(), 0, 0).getX()) {
				player.getPacketDispatch().sendMessage("You can't leave through this door. The altar can teleport you out.");
				return true;
			}
		} else if (player.getLocation().getY() == object.getLocation().transform(0, dir.getStepY(), 0).getY()) {
			player.getPacketDispatch().sendMessage("You can't leave through this door. The altar can teleport you out.");
			return true;
		}
		int index = object.getId() - 26425;
		if (index < 2) {
			index = 1 - index;
		}
		GodWarsFaction faction = GodWarsFaction.values()[index];
		String name = faction.name().toLowerCase();
		int required = 40;
		if (player.getAttribute("gwd:" + name + "kc", 0) < required) {
			player.getPacketDispatch().sendMessage("You need " + required + " " + StringUtils.formatDisplayName(name) + " kills to enter this.");
			return true;
		}
		increaseKillcount(player, faction, -required);
		DoorActionHandler.handleAutowalkDoor(player, object);
		return true;
	}

	/**
	 * Handles the ice bridge crossing.
	 * @param player The player.
	 * @param object The object.
	 */
	private void handleIceBridge(final Player player, final GameObject object) {
		if (player.getSkills().getStaticLevel(Skills.HITPOINTS) < 70) {
			player.getPacketDispatch().sendMessage("You need 70 Hitpoints to cross this bridge.");
			return;
		}
		player.lock(7);
		GameWorld.Pulser.submit(new Pulse(1, player) {
			@Override
			public boolean pulse() {
				player.visualize(Animation.create(6988), Graphics.create(68));
				int diffY = 2;
				if (object.getLocation().getY() == 5344) {
					diffY = -2;
				}
				player.getProperties().setTeleportLocation(player.getLocation().transform(0, diffY, 0));
				player.getInterfaceManager().openOverlay(new Component(115));
				player.setAttribute("cross_bridge_loc", player.getLocation());
				GameWorld.Pulser.submit(new Pulse(1, player) {
					int counter = 0;

					@Override
					public boolean pulse() {
						switch (counter++) {
						case 4:
							if (object.getLocation().getY() == 5333) {
								player.getProperties().setTeleportLocation(Location.create(2885, 5345, 2));
							} else {
								player.getProperties().setTeleportLocation(Location.create(2885, 5332, 2));
							}
							player.setDirection(Direction.get((player.getDirection().toInteger() + 2) % 4));
							break;
						case 5:
							PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
							player.getInterfaceManager().close();
							player.removeAttribute("cross_bridge_loc");
							player.getPacketDispatch().sendMessage("Dripping, you climb out of the water.");
							if (player.getLocation().getY() > 5340) {
								player.getSkills().decrementPrayerPoints(100.0);
								player.getPacketDispatch().sendMessage("The extreme evil of this area leaves your Prayer drained.");
							}
							return true;
						}
						return false;
					}
				});
				return true;
			}
		});
	}

	/**
	 * Sets the kill count.
	 * @param p The player.
	 * @param faction The god wars faction.
	 * @param increase The amount to increase with.
	 */
	public void increaseKillcount(Player p, GodWarsFaction faction, int increase) {
		if (faction == null) {
			return;
		}
		String key = "gwd:" + faction.name().toLowerCase() + "kc";
		int amount = p.getAttribute(key, 0) + increase;
		int componentId = p.getAttribute("gwd:overlay", 601);
		int child = (componentId == 601 || componentId == 599) ? 6 : 7;
		if (amount >= 4000) {
			p.setAttribute("/save:" + key, 4000);
			p.getPacketDispatch().sendString("Max", componentId, child + faction.ordinal());
			return;
		}
		p.setAttribute("/save:" + key, amount);
		p.getPacketDispatch().sendString(Integer.toString(amount), componentId, child + faction.ordinal());
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	/**
	 * The god wars factions.
	 * @author Emperor
	 */
	static enum GodWarsFaction {
		ARMADYL(6222, 6246, 87, 11694, 11718, 11720, 11722, 12670, 12671, 14671), BANDOS(6260, 6283, 11061, 11696, 11724, 11726, 11728), SARADOMIN(6247, 6259, 1718, 2412, 2415, 2661, 2663, 2665, 2667, 3479, 3675, 3489, 3840, 4682, 6762, 8055, 10384, 10386, 10388, 10390, 10440, 10446, 10452, 10458, 10464, 10470, 11181, 11698, 11730), ZAMORAK(6203, 6221, 11716, 11700, 2414, 2417, 2653, 2655, 2657, 2659, 3478, 3674, 3841, 3842, 3852, 4683, 6764, 8056, 10368, 10370, 10372, 10374, 10444, 10450, 10456, 10460, 10468, 10474, 10776, 10786, 10790);

		/**
		 * The start NPC id.
		 */
		private final int startId;

		/**
		 * The end NPC id.
		 */
		private final int endId;

		/**
		 * The protection items.
		 */
		private final int[] protectionItems;

		/**
		 * Constructs a new {@code GodWarsFaction} {@code Object}.
		 * @param startId The start NPC id.
		 * @param endId The end NPC id.
		 * @param protectionItems The protection items for this faction.
		 */
		private GodWarsFaction(int startId, int endId, int... protectionItems) {
			this.startId = startId;
			this.endId = endId;
			this.protectionItems = protectionItems;
		}

		/**
		 * Gets the god wars faction for the given NPC id.
		 * @param npcId The NPC id.
		 * @return The faction for this NPC.
		 */
		public static GodWarsFaction forId(int npcId) {
			for (GodWarsFaction faction : values()) {
				if (npcId >= faction.getStartId() && npcId <= faction.getEndId()) {
					return faction;
				}
			}
			return null;
		}

		/**
		 * Gets the amount of items the player is wearing to protect.
		 * @param player The player.
		 * @return The amount of protection items.
		 */
		public int getProtectionItemAmount(Player player) {
			int count = 0;
			for (Item item : player.getEquipment().toArray()) {
				if (item != null) {
					for (int id : protectionItems) {
						if (item.getId() == id) {
							count++;
						}
					}
				}
			}
			return count;
		}

		/**
		 * Gets the startId.
		 * @return The startId.
		 */
		public int getStartId() {
			return startId;
		}

		/**
		 * Gets the endId.
		 * @return The endId.
		 */
		public int getEndId() {
			return endId;
		}

		/**
		 * Gets the protectionItems.
		 * @return The protectionItems.
		 */
		public int[] getProtectionItems() {
			return protectionItems;
		}
	}

}
