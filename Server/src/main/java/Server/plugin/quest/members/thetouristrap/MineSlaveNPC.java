package plugin.quest.members.thetouristrap;

import core.game.node.entity.npc.AbstractNPC;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.tools.RandomFunction;

/**
 * The mining slave npc at the desert camp.
 * @author 'Vexia
 */
public final class MineSlaveNPC extends AbstractNPC {

	/**
	 * The random force chats to say.
	 */
	private static final String[] CHATS = new String[] { "I'm sick of this place.", "What I wouldn't give for a good nights rest.", "I feel so weak I could faint.", "I didn't want to be a miner anyway.", "Ooh my back.", "I'm rich in experience, poor in wealth.", "I can' think straight, i'm so tired." };

	/**
	 * The delay until the next chat.
	 */
	private int delay;

	/**
	 * Constructs a new {@code MineSlaveNPC} {@code Object}.
	 */
	public MineSlaveNPC() {
		super(0, null);
	}

	/**
	 * Constructs a new {@code MineSlaveNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public MineSlaveNPC(int id, Location location) {
		super(id, location);
		delay = GameWorld.getTicks() + RandomFunction.random(20, 100);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new MineSlaveNPC(id, location);
	}

	@Override
	public void tick() {
		if (delay < GameWorld.getTicks()) {
			sendChat(CHATS[RandomFunction.random(CHATS.length)]);
			delay = GameWorld.getTicks() + RandomFunction.random(20, 100);
		}
		super.tick();
	}

	@Override
	public int[] getIds() {
		return new int[] { 4975, 4976, 4977, 4978 };
	}

}
