package plugin.interaction.npc;

import java.util.List;

import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.node.entity.impl.PulseManager;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Represents a burthorpe training npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public class BurthorpeTrainNPC extends AbstractNPC {

	/**
	 * The NPC ids of NPCs using this plugin.
	 */
	private static final int[] IDS = { /** dummy soldier */
	1063, /** sergant */
	1061, /** seated soldier */
	1066, 1067, 1068, /** other sergant */
	1062, /** other soldiers */
	1064, 1073, 1074, 1076, 1077 };

	/**
	 * Represents the punching animation.
	 */
	private static final Animation PUNCH = new Animation(422);

	/**
	 * Represents the kicking animation.
	 */
	private static final Animation KICK = new Animation(423);

	/**
	 * Represents the defending animation.
	 */
	private static final Animation DEFEND = new Animation(424);

	/**
	 * Represents the eating animation.
	 */
	private static final Animation EAT = new Animation(1145);

	/**
	 * Represents the messages the sergant can say.
	 */
	private static final String[] MESSAGES = new String[] { "Good work soldier!", "Push it!", "Work it!", "The dummy is the enemy. Kill it!", "Put your back into it soldier!", "You're not out for a sunday stroll soldier!", "My daughter can hit harder than that!", "I want to see you sweat!", "Keep it up soldier!" };

	/**
	 * Represents the delay.
	 */
	private long delay;

	/**
	 * Constructs a new {@code BurthorpeTrainNPC} {@code Object}.
	 */
	public BurthorpeTrainNPC() {
		super(0, null);
	}

	/**
	 * Constructs a new {@code AlKharidWarriorPlugin} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	private BurthorpeTrainNPC(int id, Location location) {
		super(id, location, id == 1061 ? true : false);
		super.setDirection(Direction.EAST);
	}

	@Override
	public void init() {
		super.init();
		if (getId() == 1063) {
			faceLocation(getLocation().transform(2, 0, 0));
		}
		if (getId() == 1066 || getId() == 1067 || getId() == 1068) {
			faceLocation(Location.create(2893, 3532, 0));
		}
		if (getId() == 1064) {
			faceLocation(getLocation().transform(0, 1, 0));
		}
		if (getId() == 1062) {
			faceLocation(Location.create(2893, 3539, 0));
		}
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new BurthorpeTrainNPC(id, location);
	}

	@Override
	public void tick() {
		if (delay < System.currentTimeMillis()) {
			action();
		}
		super.tick();
	}

	@Override
	public int getWalkRadius() {
		return getId() == 1061 ? 6 : 12;
	}

	@Override
	public int[] getIds() {
		return IDS;
	}

	/**
	 * Method used to do the reward of this npc.
	 */
	private final void action() {
		switch (getId()) {
		case 1061:// sergant
			sendChat(MESSAGES[RandomFunction.random(MESSAGES.length)]);
			delay = System.currentTimeMillis() + 9000 + RandomFunction.random(1000, 5000);
			final List<NPC> soldiers = RegionManager.getLocalNpcs(this);
			NPC soldier = null;
			while (soldier == null) {
				soldier = soldiers.get(RandomFunction.random(soldiers.size()));
				if (soldier.getId() != 1063) {
					soldier = null;
				}
			}
			final NPC sol = soldier;
			GameWorld.Pulser.submit(new Pulse(1) {
				final NPC sold = sol;
				@Override
				public boolean pulse() {
					sold.sendChat("Yes, sir!");
					return true;
				}
			});
			break;
		case 1063:// soldier.
			faceLocation(getLocation().transform(2, 0, 0));
			animate(RandomFunction.random(2) == 1 ? PUNCH : KICK);
			delay = System.currentTimeMillis() + 3000 + RandomFunction.random(1000, 3000);
			break;
		case 1066:
		case 1067:
		case 1068:// seated soldier.
			animate(EAT);
			delay = System.currentTimeMillis() + 3000 + RandomFunction.random(1000, 2000);
			break;
		case 1062:
			final int rand = RandomFunction.random(5);
			final Animation animation = rand == 1 ? PUNCH : rand == 2 ? KICK : DEFEND;
			animate(animation);
			delay = System.currentTimeMillis() + 3500;
			faceLocation(getLocation().transform(0, -1, 0));
			GameWorld.Pulser.submit(new Pulse(2) {
				@Override
				public boolean pulse() {
					final List<NPC> soldiers = RegionManager.getLocalNpcs(BurthorpeTrainNPC.this, 12);
					for (NPC n : soldiers) {
						if (n.getId() != 1064) {
							continue;
						}
						n.animate(animation);
					}
					return true;
				}
			});
			break;
		}
	}

}
