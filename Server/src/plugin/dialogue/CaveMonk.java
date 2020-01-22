package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.map.Location;

/**
 * Represents the dialogue plugin used for a cave monk.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class CaveMonk extends DialoguePlugin {

	/**
	 * Represents the dungeon location.
	 */
	private static final Location DUNGEON = Location.create(2822, 9774, 0);

	/**
	 * The quest.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code CaveMonk} {@code Object}.
	 */
	public CaveMonk() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code CaveMonk} {@code Object}.
	 * @param player the player.
	 */
	public CaveMonk(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CaveMonk(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Lost City");
		switch (quest.getStage(player)) {
		case 0:
		case 10:
			player("Hello, what are you doing here?");
			stage = 100;
			break;
		default:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Be careful going in there! You are unarmed, and there", "is much evilness lurking down there! The evilness seems", "to block off our contact with our gods,");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 100:
			npc("None of your business.");
			stage = 101;
			break;
		case 101:
			end();
			break;
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "so our prayers seem to have less effect down there. Oh,", "also, you won't be able to come back this way - This", "ladder only goes one way!");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "The only exit from the caves below is a portal which", "leads only to the deepest wilderness!");
			stage = 2;
			break;
		case 2:
			interpreter.sendOptions("Select an Option", "I don't think I'm strong enough to enter then.", "Well that is a risk I will have to take.");
			stage = 3;
			break;
		case 3:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I don't think I'm strong enough to enter then.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Well that is a risk I will have to take.");
				stage = 20;
				break;
			}
			break;
		case 10:
			end();
			break;
		case 20:
			player.getProperties().setTeleportLocation(DUNGEON);
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 656 };
	}
}
