package plugin.quest.members.merlinscrystal;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Represents the dialogue plugin used for the argein npc.
 * @author 'Vexia
 * @version 1.0
 */
public final class ArheinShopDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code ArheinShopDialogue} {@code Object}.
	 */
	public ArheinShopDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ArheinShopDialogue} {@code Object}.
	 * @param player the player.
	 */
	public ArheinShopDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ArheinShopDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (args.length > 1) {
			interpreter.sendDialogues(npc, null, "Hey buddy! Get away from my ship alright?");
			stage = 2000;
			return true;
		}
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello! Would you like to trade? I've a variety of wares", "for sale!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		Quest quest = player.getQuestRepository().getQuest("Merlin's Crystal");
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Let's trade.", "No thank you.", "Is that your ship?");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Let's trade.");
				stage = 100;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No thanks.");
				stage = 200;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Is that your ship?");
				stage = 300;
				break;
			}
			break;
		case 100:
			end();
			npc.openShop(player);
			break;
		case 300:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes, I use it to make deliveries to my customers up and", "down the coast. These crates here are all ready for my", "next trip.");
			stage = 301;
			break;
		case 301:
			interpreter.sendOptions("Select an Option", "Where do you deliver to?", "Are you rich then?", "Do you deliver to the fort just down the coast?");
			stage = 303;
			break;
		case 303:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Where do you deliver to?");
				stage = 1000;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Are you rich then?");
				stage = 2500;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Do you deliver to the fort just down the coast?");
				stage = 3000;
				break;

			}
			break;
		case 1000:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Various places up and down the coast. Mostly Karamja", "and Port Sarim.");
			stage = 1001;
			break;
		case 1001:
			interpreter.sendOptions("Select an Option", "I don't suppose I could get a lift anywhere?", "Well, good luck with your business.");
			stage = 1002;
			break;
		case 1002:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I don't suppose I could get a lift anywhere?");
				stage = 1003;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Well, good luck with your business.");
				stage = 2700;
				break;

			}
			break;
		case 1003:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Sorry pal, but I'm afraid I'm not quite ready to sail yet.");
			stage = 1004;
			break;
		case 1004:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'm waiting on a big delivery of candles wich I need to", "deliver further along the coast.");
			stage = 1005;
			break;
		case 1005:
			end();
			break;
		case 2500:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Business is going reasonably well... I wouldn't say I was the", "richest of merchants every, but I'm doing fairly well all", "things considered.");
			stage = 2501;
			break;
		case 2501:
			end();
			break;
		case 2700:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Thanks buddy!");
			stage = 2701;
			break;
		case 2701:
			end();
			break;
		case 200:
			end();
			break;
		case 2000:
			interpreter.sendDialogues(player, null, "Yeah... uh... sorry...");
			stage = 2001;
			break;
		case 2001:
			end();
			break;
		case 3000:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes, I do have orders to deliver there from time to", "time. I think I may have some bits and pieces for them", "when I leave here next actually.");
			if (quest.getStage(player) == 30 || quest.getStage(player) == 40) {
				stage = 3001;
			} else {
				stage = 2701;
			}
			break;
		case 3001:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can you drop me off on the way down please?");
			stage = 3002;
			break;
		case 3002:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I don't think Sir Mordred would like that. He wants as", "few outsiders visiting as possible. I wouldn't want to lose", "his business.");
			quest.setStage(player, 40);
			stage = 2701;
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 563 };
	}
}