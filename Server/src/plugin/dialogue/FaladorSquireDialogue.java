package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.GameWorld;

/**
 * Represents the falador squire dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FaladorSquireDialogue extends DialoguePlugin {

	/**
	 * Represents the blurite sword item.
	 */
	private static final Item BLURITE_SWORD = new Item(667);

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code FaladorSquireDialogue} {@code Object}.
	 */
	public FaladorSquireDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code FaladorSquireDialogue} {@code Object}.
	 * @param player the player.
	 */
	public FaladorSquireDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new FaladorSquireDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("The Knight's Sword");
		switch (quest.getStage(player)) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello. I am the squire to Sir Vyvin.");
			stage = 0;
			break;
		case 10:
		case 20:
		case 30:
		case 40:
		case 50:
		case 60:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "So how are you doing getting a sword?");
			stage = 0;
			break;
		case 100:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello friend! Many thanks for all of your help! Vyvin", "never even realised it was a different sword, and I still", "have my job!");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 100:
			switch (stage) {
			case 0:
				end();
				break;
			}
			break;
		case 60:
			switch (stage) {
			case 0:
				if (!player.getInventory().containsItem(BLURITE_SWORD)) {
					interpreter.sendDialogues(player, null, "I've found a dwarf who will make the sword, I've just", "got to find the materials for it now!");
					stage = 1;
				} else {
					interpreter.sendDialogues(player, null, "I have retrieved your sword for you.");
					stage = 2;
				}
				break;
			case 1:
				end();
				break;
			case 2:
				interpreter.sendDialogues(npc, null, "Thank you, thank you, thank you! I was seriously", "worried I would have to own up to Sir Vyvin!");
				stage = 3;
				break;
			case 3:
				interpreter.sendDialogue("You give the sword to the squire.");
				stage = 4;
				break;
			case 4:
				if (player.getInventory().remove(BLURITE_SWORD)) {
					quest.finish(player);
					end();
				}
				break;
			}
			break;
		case 50:
			switch (stage) {
			case 0:
				if (player.getInventory().contains(666, 1)) {
					interpreter.sendDialogues(player, null, "I have the picture, I'll just take it to the dwarf now!");
					stage = 3;
				} else {
					interpreter.sendDialogues(player, null, "I didn't get the picture yet...");
					stage = 1;
				}
				break;
			case 1:
				interpreter.sendDialogues(npc, null, "Please try and get it quickly... I am scared Sir Vyvin", "will find out!");
				stage = 2;
				break;
			case 2:
				end();
				break;
			case 3:
				interpreter.sendDialogues(npc, null, "Please hurry!");
				stage = 2;
				break;
			}
			break;
		case 40:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(player, null, "I have found an Imcando dwarf but he needs a picture", "of the sword before he can make it.");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(npc, null, "A picture eh? Hmmm.... The only one I can think of is", "in a small portrait of Sir Vyvin's father... Sir Vyvin", "keeps it in a cupboard in is room I think.");
				stage = 2;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "Ok, I'll try to get that then.");
				stage = 3;
				break;
			case 3:
				interpreter.sendDialogues(npc, null, "Please don't let him catch you! He MUSTN'T know", "what happened!");
				stage = 4;
				break;
			case 4:
				quest.setStage(player, 50);
				end();
				break;
			}
			break;
		case 30:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(player, null, "I have found an Imcando Dwarf named Thurgo!", "I have given him Redberry pie, I hope he will", "help me now.");
				stage = 1;
				break;
			case 1:
				end();
				break;
			}
			break;
		case 20:
		case 10:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm still looking for Imcando dwarves to help me...");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Please try and find them quickly... I am scared Sir", "Vyvin will find out!");
				stage = 2;
				break;
			case 2:
				end();
				break;
			case 166:
				end();
				break;
			}
			break;
		case 0:
			switch (stage) {
			case 0:
				interpreter.sendOptions("Select an Option", "And how is life as a squire?", "Wouldn't you prefer to be a squire for me?");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "And how is life as a squire?");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Wouldn't you prefer to be a squire for me?");
					stage = 20;
					break;
				}
				break;
			case 10:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, Sir Vyvin is a good guy to work for, however,", "I'm in a spot of trouble today. I've gone and lost Sir", "Vyvin's sword!");
				stage = 11;
				break;
			case 11:
				interpreter.sendOptions("Select an Option", "Do you know where you lost it?", "I can make a new sword if you like...", "Is he angry?");
				stage = 12;
				break;
			case 12:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Do you know where you lost it?");
					stage = 100;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I can make a new sword if you like...");
					stage = 120;
					break;
				case 3:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Is he angry?");
					stage = 130;
					break;
				}
				break;
			case 20:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "No, sorry, I'm loyal to Sir Vyvin.");
				stage = 21;
				break;
			case 21:
				end();
				break;
			case 100:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well now, if I knew THAT it wouldn't be lost, now", "would it?");
				stage = 101;
				break;
			case 101:
				end();
				break;
			case 120:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Thanks for the offer. I'd be surprised if you could", "though.");
				stage = 121;
				break;
			case 121:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The thing is, this sword is a family heirloom. It has been", "passed down through Vyvin's family for five", "generations! It was originally made by the Imcando", "dwarves, who were");
				stage = 122;
				break;
			case 122:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "a particularly skilled tribe of dwarven smiths. I doubt", "anyone could make it in the style they do.");
				stage = 123;
				break;
			case 123:
				interpreter.sendOptions("Select an Option", "So would these dwarves make another one?", "Well I hope you find it soon.");
				stage = 134;
				break;
			case 134:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "So would these dwarves make another one?");
					stage = 160;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Well I hope you find it soon.");
					stage = 170;
					break;
				}
				break;
			case 160:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'm not a hundred percent sure the Imcando tribe", "exists anymore. I should think Reldo, the palace", "librarian in Varrock, will know; he has done a lot of", "research on the races of " + GameWorld.getName() + ".");
				stage = 161;
				break;
			case 161:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I don't suppose you could try and track down the", "Imcando dwarves for me? I've got so much work to", "do...");
				stage = 162;
				break;
			case 162:
				interpreter.sendOptions("Select an Option", "Ok, I'll give it a go.", "No, I've got lots of mining work to do.");
				stage = 163;
				break;
			case 163:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ok, I'll give it a go.");
					stage = 165;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, I've got lots of mining work to do.");
					stage = 164;
					break;
				}
				break;
			case 165:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Thank you very much! As I say, the best place to start", "should be with Reldo...");
				stage = 166;
				quest.setStage(player, 10);
				player.getQuestRepository().syncronizeTab(player);
				break;
			case 166:
				end();
				break;
			case 164:
				end();
				break;
			case 170:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes, me too. I'm not looking forward to telling Vyvin", "I've lost it. He's going to want it for the parade next", "week as well.");
				stage = 171;
				break;
			case 171:
				end();
				break;
			case 130:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "He doesn't know yet. I was hoping I could think of", "something to do before he does find out, But I find", "myself at a loss.");
				stage = 132;
				break;
			case 132:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 606 };
	}
}
