package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.PulseRunner;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.update.flag.context.Graphics;

/**
 * Represents the dialogue plugin used for the gnome spirit tree.
 * @author 'Vexia
 * @author SonicForce41
 */
@InitializablePlugin
public final class GnomeSpiritTreeDialogue extends DialoguePlugin {

	/**
	 * Represents the locations to travel to.
	 */
	private static final Location[] LOCATIONS = new Location[] { Location.create(2542, 3170, 0), Location.create(2461, 3444, 0), Location.create(2556, 3259, 0), Location.create(3184, 3508, 0) };

	/**
	 * Represents the animations to use.
	 */
	private static final Animation[] ANIMATIONS = new Animation[] { new Animation(7082), new Animation(7084) };

	/**
	 * Represents the graphics to use.
	 */
	private static final Graphics[] GRAPHICS = new Graphics[] { new Graphics(1228), new Graphics(1229) };

	/**
	 * Constructs a new {@code GnomeSpiritTreeDialogue} {@code Object}.
	 */
	public GnomeSpiritTreeDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GnomeSpiritTreeDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GnomeSpiritTreeDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GnomeSpiritTreeDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		interpreter.sendDialogues(3636, null, "If you are a friend of the gnome people, you are a", "friend of mine, Do you wish to travel?");
		stage = 1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 1:
			interpreter.sendOptions("Where would you like to go?", "Tree Gnome Village", "Tree Gnome Stronghold", "Battlefield of Khazard", "Grand Exchange");
			stage = 0;
			break;
		case 0:
			Location location = null;
			stage = 1;
			switch (buttonId) {
			case 1:
				location = LOCATIONS[0];
				sendTeleport(player, location);
				break;
			case 2:
				location = LOCATIONS[1];
				sendTeleport(player, location);
				break;
			case 3:
				location = LOCATIONS[2];
				sendTeleport(player, location);
				break;
			case 4:
				location = LOCATIONS[3];
				sendTeleport(player, location);
				break;
			}
			return true;
		}
		return true;
	}

	/**
	 * Method used to send a gnome teleport.
	 * @param player the player.
	 * @param location the location.
	 */
	private void sendTeleport(final Player player, final Location location) {
		end();
		PulseRunner.submit(new Pulse(1, player) {
			int loop;

			@Override
			public boolean pulse() {
				if (loop == 0) {
					player.animate(ANIMATIONS[0]);
					player.graphics(GRAPHICS[0]);
				} else if (loop == 3) {
					if (!player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).isComplete(1, 2)) {
						player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).updateTask(player, 1, 2, true);
					}
					player.getProperties().setTeleportLocation(location);
				} else if (loop == 5) {
					player.animate(ANIMATIONS[1]);
					player.graphics(GRAPHICS[1]);
					player.face(null);
				}
				loop++;
				return loop == 8;
			}
		});
	}

	@Override
	public int[] getIds() {
		return new int[] { 1293, 1317 };
	}

}
