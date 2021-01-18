package core.game.node.entity.state.impl;

import core.game.node.entity.Entity;
import core.game.node.entity.combat.ImpactHandler;
import core.game.node.entity.player.Player;
import core.game.node.entity.state.StatePulse;
import core.game.world.GameWorld;
import core.tools.RandomFunction;

import java.nio.ByteBuffer;

/**
 * Handles the diseased state.
 * @author Emperor
 */
public final class DiseaseStatePulse extends StatePulse {

	/**
	 * The current value.
	 */
	private int value;

	/**
	 * Constructs a new {@code DiseaseStatePulse} {@code Object}.
	 * @param entity The entity.
	 */
	public DiseaseStatePulse(Entity entity) {
		super(entity, 30);
	}

	@Override
	public boolean canRun(Entity entity) {
		return entity.getAttribute("disease:immunity", -1) < GameWorld.getTicks();
	}

	@Override
	public void start() {
		super.start();
		if (entity instanceof Player) {
			((Player) entity).getPacketDispatch().sendMessage("You have been diseased!");
		}
	}

	@Override
	public boolean pulse() {
		int damage = RandomFunction.random(1, 5);
		entity.getImpactHandler().getImpactQueue().add(new ImpactHandler.Impact(entity, damage, null, ImpactHandler.HitsplatType.DISEASE));
		int skill = RandomFunction.random(24);
		if (skill == 3) {
			skill--;
		}
		entity.getSkills().updateLevel(skill, -damage, 0);
		value -= 2;
		if (value < 10) {
			if (entity instanceof Player) {
				((Player) entity).getPacketDispatch().sendMessage("The disease has wore off.");
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean isSaveRequired() {
		return value > 9;
	}

	@Override
	public void save(ByteBuffer buffer) {
		buffer.put((byte) value);
	}

	@Override
	public StatePulse parse(Entity entity, ByteBuffer buffer) {
		return create(entity, buffer.get() & 0xFF, entity);
	}

	@Override
	public StatePulse create(Entity entity, Object... args) {
		DiseaseStatePulse pulse = new DiseaseStatePulse(entity);
		pulse.value = (Integer) args[0];
		return pulse;
	}

	/**
	 * Gets the value.
	 * @return The value.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * @param value The value to set.
	 */
	public void setValue(int value) {
		this.value = value;
	}
}