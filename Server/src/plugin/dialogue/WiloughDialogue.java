package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue used for wilough.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class WiloughDialogue extends DialoguePlugin {

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 100);

	/**
	 * Represents the id.
	 */
	private int id;

	/**
	 * Constructs a new {@code WiloughDialogue} {@code Object}.
	 */
	public WiloughDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code WiloughDialogue} {@code Object}.
	 * @param player the player.
	 */
	public WiloughDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new WiloughDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		if (args[0] instanceof NPC) {
			id = ((NPC) args[0]).getId();
		} else if (args[0] instanceof Integer) {
			id = (int) args[0];
		}
		final Quest quest = player.getQuestRepository().getQuest("Gertrude's Cat");
		switch (quest.getStage(player)) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello again.");
			stage = 0;
			break;
		case 10:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello there, I've been looking for you.");
			stage = 100;
			break;
		case 20:
		case 30:
		case 40:
		case 50:
			interpreter.sendDialogues(player, null, "Where did you say you saw Fluffs?");
			stage = 130;
			break;
		default:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello again.");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		final Quest quest = player.getQuestRepository().getQuest("Gertrude's Cat");
		switch (stage) {
		case 0:
			interpreter.sendDialogues(id, FacialExpression.HALF_GUILTY, "You think you're tough do you?");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Pardon?");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(id, FacialExpression.HALF_GUILTY, "I can beat anyone up!");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(781, FacialExpression.HALF_GUILTY, "He can you know!");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Really?");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogue("The boy begins to jump around with his fists up.", "You wonder what sort of desperado he'll grow up to be.");
			stage = 6;
			break;
		case 6:
			end();
			break;
		case 100:// stage 10
			interpreter.sendDialogues(id, FacialExpression.HALF_GUILTY, "I didn't mean to take it! I just forgot to pay.");
			stage = 101;
			break;
		case 101:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What? I'm trying to help your mum find Fluffs.");
			stage = 102;
			break;
		case 102:
			interpreter.sendDialogues(id == 783 ? 781 : 783, null, "I might be able to help. Fluffs followed me to our secret", "play area and I haven't seen her since.");
			stage = 103;
			break;
		case 103:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Where is this play area?");
			stage = 104;
			break;
		case 104:
			interpreter.sendDialogues(id == 783 ? 781 : 783, null, "If I told you that, it wouldn't be a secret.");
			stage = 105;
			break;
		case 105:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What will make you tell me?");
			stage = 106;
			break;
		case 106:
			interpreter.sendDialogues(id == 783 ? 781 : 783, null, "Well...now you ask, I am a bit short on cash.");
			stage = 107;
			break;
		case 107:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How much?");
			stage = 108;
			break;
		case 108:
			interpreter.sendDialogues(id == 783 ? 781 : 783, null, "10 coins.");
			stage = 109;
			break;
		case 109:
			interpreter.sendDialogues(id == 783 ? 783 : id, null, "10 coins?!");
			stage = 110;
			break;
		case 110:
			interpreter.sendDialogues(id == 783 ? 783 : id, null, "I'll handle this.");
			stage = 111;
			break;
		case 111:
			interpreter.sendDialogues(id == 783 ? 783 : id, null, "100 coins should cover it.");
			stage = 112;
			break;
		case 112:
			interpreter.sendDialogues(player, null, "100 coins! Why should I pay you?");
			stage = 113;
			break;
		case 113:
			interpreter.sendDialogues(id == 783 ? 783 : id, null, "You shouldn't, but we won't help otherwise. We never", "liked that cat anyway, so what do you say?");
			stage = 114;
			break;
		case 114:
			interpreter.sendOptions("Select an Option", "I'm not paying you a penny.", "Okay then, I'll pay.");
			stage = 115;
			break;
		case 115:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "I'm not paying you a penny.");
				stage = 116;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "Okay then, I'll pay.");
				stage = 118;
				break;
			}
			break;
		case 116:
			interpreter.sendDialogues(id == 783 ? 783 : id, null, "Okay then, I'll find another way to make money.");
			stage = 117;
			break;
		case 117:
			end();
			break;
		case 118:
			if (!player.getInventory().containsItem(COINS)) {
				end();
				return true;
			}
			if (player.getInventory().remove(COINS)) {
				interpreter.sendItemMessage(COINS, "You give the lad 100 coins.");
				quest.setStage(player, 20);
				stage = 119;
			} else {
				interpreter.sendDialogues(player, null, "Sorry, I don't seem to have enough coins.");
				stage = 117;
			}
			break;
		case 119:
			interpreter.sendDialogues(player, null, "There you go, now where did you see Fluffs?");
			stage = 120;
			break;
		case 120:
			interpreter.sendDialogues(id == 783 ? 783 : id, null, "We play at an abandoned lumber mill to the north east.", "Just beyond the Jolly Boar Inn. I saw Fluffs running", "around in there.");
			stage = 121;
			break;
		case 121:
			interpreter.sendDialogues(player, null, "Anything else?");
			stage = 122;
			break;
		case 122:
			interpreter.sendDialogues(id == 783 ? 783 : id, null, "Well, you'll have to find the broken fence to get in. I'm", "sure you can manage that.");
			stage = 123;
			break;
		case 123:
			end();
			break;
		case 130:
			interpreter.sendDialogues(id, null, "Weren't you listening? I saw the flea bag in the old", "lumber mill just north east of here. Just walk past the", "Jolly Boar Inn and you should find it.");
			stage = 131;
			break;
		case 131:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 783 };
	}
}
