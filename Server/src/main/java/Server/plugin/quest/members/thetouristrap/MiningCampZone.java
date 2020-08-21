package plugin.quest.members.thetouristrap;

import core.game.interaction.Option;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.zone.MapZone;
import core.game.world.map.zone.ZoneBorders;
import core.game.world.map.zone.ZoneBuilder;
import core.plugin.Plugin;

/**
 * The map zone for the mining camp.
 * @author 'Vexia
 * @version 1.0
 */
public final class MiningCampZone extends MapZone implements Plugin<Object> {

	/**
	 * Constructs a new {@code MiningCampZone} {@code Object}.
	 */
	public MiningCampZone() {
		super("mining camp", true);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		return this;
	}

	@Override
	public boolean enter(Entity entity) {
		return super.enter(entity);
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (!logout && e instanceof Player) {
			if (checkAnna((Player) e)) {
				return false;
			}
		}
		return super.leave(e, logout);
	}

	@Override
	public boolean interact(Entity e, final Node node, Option option) {
		switch (option.getName()) {
		case "Equip":
		case "Wear":
			final Player player = (Player) e;
			GameWorld.Pulser.submit(new Pulse(1, player) {
				@Override
				public boolean pulse() {
					if (TouristTrap.isJailable(player)) {
						TouristTrap.jail(player, "Hey! What do you think you're doing!?");
					}
					return true;
				}
			});
			break;
		}
		return super.interact(e, node, option);
	}

	@Override
	public boolean teleport(Entity entity, final int type, Node node) {
		if (entity instanceof Player && type != -1) {
			return !checkAnna((Player) entity);
		} else {
			return super.teleport(entity, type, node);
		}
	}

	/**
	 * Checks for anna.
	 * @param p the player.
	 * @return {@code True} if removed.
	 */
	public boolean checkAnna(final Player p) {
		final Quest quest = p.getQuestRepository().getQuest(TouristTrap.NAME);
		if (p.getAttribute("ana-delay", 0) > GameWorld.getTicks()) {
			return false;
		}
		if (quest.getStage(p) > 60 && quest.getStage(p) < 95 && p.getInventory().containsItem(TouristTrap.ANNA_BARREL)) {
			p.getInventory().remove(TouristTrap.ANNA_BARREL);
			p.lock(5);
			TouristTrap.addConfig(p, (1 << 4));
			quest.setStage(p, 61);
			p.getProperties().setTeleportLocation(Location.create(3285, 3034, 0));
			p.getPacketDispatch().sendMessage("The guards spot anna and throw you in jail.");
			return true;
		}
		return false;
	}

	@Override
	public void configure() {
		register(new ZoneBorders(3274, 3014, 3305, 3041));
		register(new ZoneBorders(3260, 9408, 3331, 9472));
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

}
