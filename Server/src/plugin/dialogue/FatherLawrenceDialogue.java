package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.link.quest.Quest;

/**
 * Represents the father lawrence dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FatherLawrenceDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code FatherLawrenceDialogue} {@code Object}.
	 */
	public FatherLawrenceDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code FatherLawrenceDialogu} {@code Object}.
	 * @param player the player.
	 */
	public FatherLawrenceDialogue(Player player) {
		super(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		final Quest quest = player.getQuestRepository().getQuest("Romeo & Juliet");
		if (quest.getStage(player) < 30) {
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Oh to be a father in the times of whiskey.");
			stage = 0;
		}
		switch (quest.getStage(player)) {
		case 30:
			interpreter.sendDialogues(npc, null, "\"...and let Saradomin light the way for you... \"", "Urgh!");
			stage = 41;
			break;
		case 40:
			interpreter.sendDialogues(npc, null, "Ah, have you found the Apothecary yet? Remember,", "Cadava potion, for Juliet.");
			stage = 30;
			break;
		case 50:
			interpreter.sendDialogues(npc, null, "Did you find the Apothecary?");
			stage = 820;
			break;
		case 60:
		case 70:
			interpreter.sendDialogues(npc, null, "Did you find the Apothecary?");
			stage = 820;
			break;
		case 100:
			player("Hi again!");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		final Quest quest = player.getQuestRepository().getQuest("Romeo & Juliet");
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I sing and I drink and I wake up in gutters.");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Top of the morning to you.");
			stage = 2;
			break;
		case 2:
			end();
			break;
		case 41:
			interpreter.sendDialogues(npc, null, "Can't you see that I'm in the middle of a Sermon?!");
			stage = 42;
			break;
		case 42:
			interpreter.sendDialogues(player, null, "But Romeo sent me!");
			stage = 43;
			break;
		case 43:
			interpreter.sendDialogues(npc, null, "But I'm busy delivering a sermon to my congregation!");
			stage = 44;
			break;
		case 44:
			interpreter.sendDialogues(player, null, "Yes well it certainly seems like you have a captive", "audience!");
			stage = 45;
			break;
		case 45:
			interpreter.sendDialogues(npc, null, "Ok, ok...what do you want so I can get rid of you and", "continue with my sermon?");
			stage = 46;
			break;
		case 46:
			interpreter.sendDialogues(player, null, "Romeo sent me. He says you may be able to help.");
			stage = 47;
			break;
		case 47:
			interpreter.sendDialogues(npc, null, "Ah Romeo, yes. A fine lad, but a little bit confused.");
			stage = 48;
			break;
		case 48:
			interpreter.sendDialogues(player, null, "Yes, very confused...Anyway, Romeo wishes to be", "married to Juliet! She must be rescued from her", "father's control!");
			stage = 49;
			break;
		case 49:
			interpreter.sendDialogues(npc, null, "I agree, and I think I have an idea! A potion to make", "her appear dead...");
			stage = 50;
			break;
		case 50:
			interpreter.sendDialogues(player, null, "Dead! Sounds a bit creepy to me...but please, continue.");
			stage = 51;
			break;
		case 51:
			interpreter.sendDialogues(npc, null, "The potion will only make Juliet 'appear' dead...then", "she'll be taken to the crypt...");
			stage = 52;
			break;
		case 52:
			interpreter.sendDialogues(player, null, "Crypt! Again...very creepy! You must have some", "strange hobbies.");
			stage = 53;
			break;
		case 53:
			interpreter.sendDialogues(npc, null, "Then Romeo can collect her from the crypt! Go to the", "Apothercary, tell him I sent you and that you'll need a", "'Cadava' potion.");
			stage = 54;
			break;
		case 54:
			interpreter.sendDialogues(player, null, "Apart from the strong overtones of death, this is", "turning out to be a real love story.");
			stage = 55;
			break;
		case 55:
			quest.setStage(player, 40);
			end();
			break;
		case 30:
			end();
			break;
		case 820:
			interpreter.sendDialogues(player, null, "Yes I did. He's told me I must find some Cadava", "berries.");
			stage = 821;
			break;
		case 821:
			interpreter.sendDialogues(npc, null, "Well, good luck with that...they're quite tricky to find.");
			stage = 822;
			break;
		case 822:
			interpreter.sendDialogues(player, null, "Any clues where I can start to look?");
			stage = 823;
			break;
		case 823:
			interpreter.sendDialogues(npc, null, "I heard some kids saying they saw some the other day.", "They were visiting the mining place to the south east", "Varrock.");
			stage = 824;
			break;
		case 824:
			interpreter.sendDialogues(player, null, "Ok, that's as good a place to start looking as any.");
			stage = 825;
			break;
		case 825:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new FatherLawrenceDialogue(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 640 };
	}
}
