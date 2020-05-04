package plugin.npc.other;

import java.util.List;

import org.crandor.game.component.Component;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.CombatSwingHandler;
import org.crandor.game.node.entity.combat.DeathTask;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.MinimapStateContext;
import org.crandor.net.packet.out.MinimapState;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the elementals npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class ElementalNPC extends AbstractNPC {
	/**
	 * Represents the definitions of the elementals.
	 * @author Sonicforce41
	 * @version 1.0
	 */
	static enum ElementalDefinition {

		/**
		 * The definition values
		 */
		ID5533(5533, new Location[] { new Location(2908, 5460, 0), new Location(2898, 5460, 0) }), ID5534(5534, new Location[] { new Location(2900, 5448, 0), new Location(2900, 5455, 0) }), ID5535(5535, new Location[] { new Location(2905, 5449, 0), new Location(2899, 5449, 0) }), ID5536(5536, new Location[] { new Location(2903, 5451, 0), new Location(2903, 5455, 0), new Location(2905, 5455, 0), new Location(2905, 5451, 0) }), ID5537(5537, new Location[] { new Location(2903, 5457, 0), new Location(2917, 5457, 0) }), ID5538(5538, new Location[] { new Location(2908, 5455, 0), new Location(2917, 5455, 0) }), ID5539(5539, new Location[] { new Location(2922, 5471, 0), new Location(2922, 5459, 0) }), ID5540(5540, new Location[] { new Location(2924, 5463, 0), new Location(2928, 5463, 0), new Location(2928, 5461, 0), new Location(2924, 5461, 0) }), ID5541(5541, new Location[] { new Location(2924, 5461, 0), new Location(2926, 5461, 0), new Location(2926, 5458, 0), new Location(2924, 5458, 0) }), ID5542(5542, new Location[] { new Location(2928, 5458, 0), new Location(2928, 5460, 0), new Location(2934, 5460, 0), new Location(2934, 5458, 0) }), ID5543(5543, new Location[] { new Location(2931, 5477, 0), new Location(2931, 5470, 0) }), ID5544(5544, new Location[] { new Location(2935, 5469, 0), new Location(2928, 5469, 0) }), ID5545(5545, new Location[] { new Location(2925, 5464, 0), new Location(2925, 5475, 0) }), ID5546(5546, new Location[] { new Location(2931, 5477, 0), new Location(2931, 5470, 0) }), ID5547(5547, new Location[] { new Location(2907, 5488, 0), new Location(2907, 5482, 0) }), ID5548(5548, new Location[] { new Location(2907, 5490, 0), new Location(2907, 5495, 0) }), ID5549(5549, new Location[] { new Location(2910, 5493, 0), new Location(2910, 5487, 0) }), ID5550(5550, new Location[] { new Location(2918, 5483, 0), new Location(2918, 5485, 0), new Location(2915, 5485, 0), new Location(2915, 5483, 0), new Location(2912, 5483, 0), new Location(2912, 5485, 0), new Location(2915, 5485, 0), new Location(2915, 5483, 0) }), ID5551(5551, new Location[] { new Location(2921, 5486, 0), new Location(2923, 5486, 0), new Location(2923, 5490, 0), new Location(2923, 5486, 0) }), ID5552(5552, new Location[] { new Location(2921, 5491, 0), new Location(2923, 5491, 0), new Location(2923, 5495, 0), new Location(2921, 5495, 0) }), ID5553(5553, new Location[] { new Location(2899, 5466, 0), new Location(2899, 5468, 0), new Location(2897, 5468, 0), new Location(2897, 5466, 0), new Location(2897, 5468, 0), new Location(2899, 5468, 0) }), ID5554(5554, new Location[] { new Location(2897, 5470, 0), new Location(2891, 5470, 0) }), ID5555(5555, new Location[] { new Location(2897, 5471, 0), new Location(2899, 5471, 0), new Location(2899, 5478, 0), new Location(2897, 5478, 0) }), ID5556(5556, new Location[] { new Location(2896, 5483, 0), new Location(2900, 5483, 0), new Location(2900, 5480, 0), new Location(2897, 5480, 0), new Location(2897, 5481, 0), new Location(2896, 5481, 0), new Location(2896, 5482, 0) }), ID5557(5557, new Location[] { new Location(2896, 5483, 0), new Location(2896, 5481, 0), new Location(2891, 5481, 0), new Location(2891, 5483, 0) }), ID5558(5558, new Location[] { new Location(2889, 5485, 0), new Location(2900, 5485, 0) });
		/**
		 * The NPC id
		 */
		private int npcId;

		/**
		 * The Locations
		 */
		private Location[] locations;

		/**
		 * Constructs a new {@code Elementals} {@code Object}.
		 * @param id the id.
		 * @param locations the locations.
		 */
		ElementalDefinition(int id, Location[] locations) {
			npcId = id;
			this.locations = locations;
		}

		/**
		 * Gets the npcId
		 * @return 'npcId'
		 */
		public int getId() {
			return npcId;
		}

		/**
		 * Gets the locations to walk
		 * @return the Locations
		 */
		public Location[] getLocation() {
			return locations;
		}

		/**
		 * Gets the elemental definition by the id.
		 * @param id the id.
		 * @return the elemental definition.
		 */
		static ElementalDefinition forId(int id) {
			for (ElementalDefinition def : ElementalDefinition.values())
				if (def != null)
					if (def.getId() == id)
						return def;
			return null;
		}

	}

	/**
	 * The Definition
	 */
	private ElementalDefinition definition;

	/**
	 * Index of current steps
	 */
	private int tilesIndex = 0;

	/**
	 * Constructs a new {@code Elementals.java} {@Code Object}.
	 */
	public ElementalNPC() {
		super(5533, null);
	}

	/**
	 * Constructs a new {@code Elementals} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public ElementalNPC(int id, Location location) {
		super(id, location);
	}

	/**
	 * Checks if the entity can teleport.
	 * @param the entity.
	 * @return {@code True} if so.
	 */
	public boolean canTeleport(Entity t) {
		if (getDirection() == Direction.EAST && (t.getLocation().getX() - getLocation().getX()) < 4 && (t.getLocation().getX() - getLocation().getX()) > -1 && (t.getLocation().getY() - getLocation().getY()) == 0)
			return true;
		if (getDirection() == Direction.WEST && (getLocation().getX() - t.getLocation().getX()) < 4 && (getLocation().getX() - t.getLocation().getX()) > -1 && (t.getLocation().getY() - getLocation().getY()) == 0)
			return true;
		if (getDirection() == Direction.NORTH && (t.getLocation().getY() - getLocation().getY()) < 4 && (t.getLocation().getY() - getLocation().getY()) > -1 && (t.getLocation().getX() - getLocation().getX()) == 0)
			return true;
		if (getDirection() == Direction.SOUTH && (getLocation().getY() - t.getLocation().getY()) < 4 && (getLocation().getY() - t.getLocation().getY()) > -1 && (t.getLocation().getX() - getLocation().getX()) == 0)
			return true;
		return false;
	}

	@Override
	public void configure() {
		super.configure();
		definition = ElementalDefinition.forId(getId());
		if (definition != null) {
			configureMovementPath(definition.getLocation());
			setWalks(true);
		}
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new ElementalNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return new int[] { 5533, 5534, 5535, 5536, 5537, 5538, 5539, 5540, 5541, 5542, 5543, 5544, 5545, 5546, 5547, 5548, 5549, 5550, 5551, 5552, 5553, 5554, 5555, 5556, 5557, 5558 };
	}

	/**
	 * Gets the respawn loc.
	 * @return the loc.
	 */
	public Location getRespawnLocation() {
		Location respawn = null;
		if (getId() >= 5533 && getId() <= 5538)
			respawn = Location.create(2913, 5467, 0);
		else if (getId() >= 5539 && getId() <= 5546)
			respawn = Location.create(2916, 5473, 0);
		else if (getId() >= 5547 && getId() <= 5552)
			respawn = Location.create(2910, 5476, 0);
		else if (getId() >= 5553 && getId() <= 5558)
			respawn = Location.create(2907, 5470, 0);
		return respawn;
	}

	/**
	 * Gets the tilesIndex.
	 * @return The tilesIndex.
	 */
	public int getTilesIndex() {
		return tilesIndex;
	}

	@Override
	public void tick() {
		super.tick();
		List<Player> players = getViewport().getCurrentPlane().getPlayers();
		for (Player player : players) {
			if (player == null || !player.isActive() || player.getLocks().isInteractionLocked() || DeathTask.isDead(player) || !canTeleport(player) || !CombatSwingHandler.isProjectileClipped(this, player, false)) {
				continue;
			}
			animate(new Animation(5803));
			sendTeleport(player);
		}
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		return super.newInstance(arg);
	}

	/**
	 * Sends a teleport to the Player
	 * @param player the Player
	 */
	public void sendTeleport(final Player player) {
		player.lock();
		GameWorld.Pulser.submit(new Pulse(1) {
			int delay = 0;

			@Override
			public boolean pulse() {
				if (delay == 0) {
					player.getPacketDispatch().sendMessage("You've been spotted by an elemental and teleported out of its garden.");
					player.graphics(new Graphics(110, 100));
					player.getInterfaceManager().openOverlay(new Component(115));
					PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
					face(player);
				} else if (delay == 6) {
					player.getProperties().setTeleportLocation(Location.create(getRespawnLocation()));
					PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
					player.getInterfaceManager().closeOverlay();
					player.getInterfaceManager().close();
					face(null);
					player.unlock();
					return true;
				}
				delay++;
				return false;
			}
		});
	}

	/**
	 * Sets the tilesIndex.
	 * @param tilesIndex The tilesIndex to set.
	 */
	public void setTilesIndex(int tilesIndex) {
		this.tilesIndex = tilesIndex;
	}
}
