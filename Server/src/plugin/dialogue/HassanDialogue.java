package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue used to handle the Hassan npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class HassanDialogue extends DialoguePlugin {

	/**
	 * Represents the jug of water item.
	 */
	private static final Item JUG_OF_WATER = new Item(1937);

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code HassanDialogue} {@code Object}.
	 */
	public HassanDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code HassanDialogue} {@code Object}.
	 * @param player the player.
	 */
	public HassanDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HassanDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Prince Ali Rescue");
		switch (quest.getStage(player)) {
		case 100:
			interpreter.sendDialogues(npc, null, "You are a friend of the town of Al-Kharid. If we have", "more tasks to complete, we will ask you. Please, keep in", "contact. Good employees are not easy to find.");
			stage = 0;
			break;
		case 60:
			interpreter.sendDialogues(npc, null, "You have the eternal gratitude of the Emir for", "rescuing his son. I am authorised to pay you 700", "coins.");
			stage = 0;
			break;
		case 40:
		case 50:
		case 20:
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I understand the Spymaster has hired you. I will pay", "the reward only when the Prince is rescued.");
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Have you found the spymaster, Osman, yet? You", "cannot proceed in your task without reporting to him.");
			break;
		default:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Greetings I am Hassan, Chancellor to the Emir of Al-", "Kharid.");
			break;
		}
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 100:
			end();
			break;
		case 60:
			end();
			quest.finish(player);
			break;
		case 30:
		case 40:
		case 50:
		case 20:
			end();
			break;
		case 10:
			end();
			break;
		case 0:
			switch (stage) {
			case 0:
				interpreter.sendOptions("Select an Option", "Can I help you? You must need some help here in the desert.", "It's just too hot here. How can you stand it?", "Do you mind if I just kill your warriors?");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can I help you? You must need some help here in the", "desert.");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "It's just too hot here. How can you stand it?");
					stage = 20;
					break;
				case 3:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Do you mind if I just kill your warriors?");
					stage = 30;
					break;
				}
				break;
			case 10:
				quest.start(player);
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I need the services of someone yes. If you are", "interested, see the spymaster, Osman. I manage the", "finances here. Come to me when you need payment.");
				stage = 11;
				break;
			case 11:
				end();
				break;
			case 20:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "We manage, in our humble way. We are a wealthy", "town and we have water. It cures many thirsts.");
				stage = 21;
				break;
			case 21:
				interpreter.sendDialogue("The chancellor hands you some water.");
				stage = 22;
				break;
			case 22:
				if (!player.getInventory().add(JUG_OF_WATER)) {
					GroundItemManager.create(JUG_OF_WATER, player);
				}
				end();
				break;
			case 30:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You are welcome. They are not expensive. We have", "them here to stop the elite guard being bothered. They", "are a little harder to kill.");
				stage = 31;
				break;
			case 31:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 923 };
	}
}
