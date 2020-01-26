package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the hetty npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class HettyDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code HettyDialogue} {@code Object}.
	 */
	public HettyDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code HettyDialogue} {@code Object}.
	 * @param player the player.
	 */
	public HettyDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HettyDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		Quest quest = player.getQuestRepository().getQuest("Witch's Potion");
		if (quest.isCompleted(player)) {
			interpreter.sendDialogues(307, FacialExpression.NO_EXPRESSION, "How's your magic coming along?");
			stage = 0;
		}
		switch (quest.getStage(player)) {
		case 0:
			interpreter.sendDialogues(307, FacialExpression.NO_EXPRESSION, "What could you want with an old woman like me?");
			stage = 11;
			break;
		case 20:
			interpreter.sendDialogues(307, FacialExpression.NO_EXPRESSION, "So have you found the things for the potion?");
			stage = 100;
			break;
		case 40:
			if (args.length == 2) {
				interpreter.sendDialogue("You drink from the cauldron, it tastes horrible! You feel yourself", "imbued with power.");
				stage = 41;
			} else {
				interpreter.sendDialogues(307, FacialExpression.NO_EXPRESSION, "Well are you going to drink the potion or not?");
				stage = 500;
			}
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		Quest quest = player.getQuestRepository().getQuest("Witch's Potion");
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I'm practicing and slowly getting better.");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(307, FacialExpression.NO_EXPRESSION, "Good, good.");
			stage = 2;
			break;
		case 2:
			end();
			break;
		case 11:
			interpreter.sendOptions("Select an Option", "I am in search of a quest.", "I've heard that you are a witch.");
			stage = 12;
			break;
		case 12:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I am in search of a quest.");
				stage = 13;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I've heard that you are a witch.");
				stage = 20;
				break;
			}
			break;
		case 13:
			interpreter.sendDialogues(307, FacialExpression.NO_EXPRESSION, "Hmmm... Maybe I can think of something for you.");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(307, FacialExpression.NO_EXPRESSION, "Would you like to become more proficient in the dark", "arts?");
			stage = 15;
			break;
		case 15:
			interpreter.sendOptions("Select an Option", "Yes help me become one with my darker side.", "No I have my prinicples and honour.");
			stage = 16;
			break;
		case 16:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Yes help me become one with my darker side.");
				stage = 30;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No I have m principles and honour.");
				stage = 17;
				break;
			}
			break;
		case 17:
			interpreter.sendDialogues(307, FacialExpression.NO_EXPRESSION, "Suit yourself, but you're missing out.");
			stage = 18;
			break;
		case 18:
			end();
			break;
		case 20:
			interpreter.sendDialogues(307, FacialExpression.NO_EXPRESSION, "Yes it does seem to be getting fairly common", "knowledge.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(307, FacialExpression.NO_EXPRESSION, "I fear I may get a visit from the witch hunter of", "Falador before long.");
			stage = 22;
			break;
		case 22:
			end();
			break;
		case 30:
			interpreter.sendDialogues(307, FacialExpression.NO_EXPRESSION, "Ok I'm going to make a potion to help bring out your", "darker self.");
			stage = 31;
			break;
		case 31:
			interpreter.sendDialogues(307, FacialExpression.NO_EXPRESSION, "You will need certain ingredients.");
			stage = 32;
			break;
		case 32:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What do i need?");
			stage = 33;
			break;
		case 33:
			interpreter.sendDialogues(307, FacialExpression.NO_EXPRESSION, "You need an eye of newt, a rat's tail, and onion... Oh", "and a piece of burnt meat.");
			stage = 34;
			break;
		case 34:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Great, I'll go get them.");
			stage = 35;
			break;
		case 35:
			quest.setStage(player, 20);
			end();
			break;
		case 100:
			// Her:Well I can't make the potion without them!
			// Remember.../You
			// need an eye of newt, a rat's tail, an onion, and a/piece of
			// burnt
			// meat. Off you go dear! Me:end();
			if (!player.getInventory().containItems(1957, 300, 2146, 221)) {
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I'm afraid I don't have all of them yet.");
				stage = 101;
			} else {
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Yes I have everything!");
				stage = 110;
			}
			break;
		case 110:
			interpreter.sendDialogues(307, FacialExpression.NO_EXPRESSION, "Excellent, can I have them then?");
			stage = 111;
			break;
		case 111:
			interpreter.sendDialogue("You pass the ingredients to Hetty and she puts them all into her", "cauldron. Hetty closes her eyes and begins to chant. The cauldron", "bubbles mysteriously.");
			stage = 112;
			break;
		case 112:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Well, is it ready?");
			stage = 113;
			break;
		case 113:
			if (player.getInventory().remove(new Item(1957), new Item(300), new Item(2146), new Item(221))) {
				quest.setStage(player, 40);
				interpreter.sendDialogues(307, FacialExpression.NO_EXPRESSION, "Ok, now drink from the cauldron.");
				stage = 114;
			}
			break;
		case 114:
			end();
			break;
		case 101:
			interpreter.sendDialogues(307, FacialExpression.NO_EXPRESSION, "Well I can't make the potion without them! Remember...", "You need an eye of newt, a rat's tail, an onion, and a", "You need an eye of newt, a rat's tail, an onion, and a", "piece of burnt meat. Off you go dear!");
			stage = 102;
			break;
		case 102:
			end();
			break;
		case 500:
			end();
			break;
		case 41:
			end();
			quest.finish(player);
			player.getQuestRepository().syncronizeTab(player);
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 307 };
	}
}
