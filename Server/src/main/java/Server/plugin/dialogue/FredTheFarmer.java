package plugin.dialogue;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;

import core.plugin.InitializablePlugin;
import plugin.quest.free.SheepShearer;

/**
 * Represents the dialogue plugin used for fred the farmer npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FredTheFarmer extends DialoguePlugin {

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code FredTheFarmer} {@code Object}.
	 */
	public FredTheFarmer() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code FredTheFarmer} {@code Object}.
	 * @param player the player.
	 */
	public FredTheFarmer(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new FredTheFarmer(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Sheep Shearer");
		if (player.getQuestRepository().getQuest("Sheep Shearer").getStage(player) == 10 || player.getQuestRepository().getQuest("Sheep Shearer").getStage(player) == 90) {
			if (SheepShearer.getWoolGiven(player) == 20 && player.getQuestRepository().getQuest("Sheep Shearer").getStage(player) == 90) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "That's the last of them.");
				stage = 999;
				return true;
			}
			if (player.getInventory().contains(1737, 1) || player.getInventory().contains(1759, 1)) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "What are you doing on my land?");
				stage = 5000;
				return true;
			}
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "How are you doing getting those balls of wool?");
			stage = 4000;
			return true;
		}

		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "What are you doing on my land? You're not the one", "who keeps leaving all my gates open and letting out all", "my sheep are you?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		quest = player.getQuestRepository().getQuest("Sheep Shearer");
		switch (stage) {
		case 0:
			if (!player.getQuestRepository().isComplete("Sheep Shearer")) {
				interpreter.sendOptions("Select an Option", "I'm looking for a quest.", "I'm looking for something to kill.", "I'm lost.");
				stage = 1;
			} else {
				interpreter.sendOptions("Select an Option", "I'm looking for something to kill.", "I'm lost.");
				stage = 346;
			}
			break;
		case 346:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm looking for something to kill.");
				stage = 20;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm lost.");
				stage = 30;
				break;
			}
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm looking for a quest.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm looking for something to kill.");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm lost.");
				stage = 30;
				break;

			}
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "How can you be lost? Just follow the road east and", "south. You'll end up in Lumbridge fairly quickly.");
			stage = 31;
			break;
		case 31:
			end();
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You're after a quest, you say? Actually I could do with", "a bit of help.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "My sheep are getting mighty woolly. I'd be much", "obliged if you could shear them. And while you're at it", "spin the wool for me too.");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes, that's it. Bring me 20 balls of wool. And I'm sure", "I could sort out somesort of payment. Of course,", "there's a small matter of The Thing.");
			stage = 13;
			break;
		case 13:
			interpreter.sendOptions("Select an Option", "Yes okay. I can do that.", "That doesn't sound a very exiting quest.", "What do you mean, The Thing?");
			stage = 14;
			break;
		case 14:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes okay. I can do that.");
				stage = 670;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "That doesn't sound a very exiting quest.");
				stage = 560;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What do you mean, The Thing?");
				stage = 456;
				break;
			}
			break;
		case 670:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Good! Now one more thing, do you actually know how", "to shear a sheep?");
			stage = 678;
			break;
		case 678:
			interpreter.sendOptions("Select an Option", "Of course!", "No, I don't know actually.");
			stage = 679;
			break;
		case 679:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Of course!");
				stage = 954;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Err. No, I don't know actually.");
				stage = 750;
				break;
			}
			break;
		case 954:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Do you know how to spin wool?");
			stage = 754;
			break;
		case 750:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Just use a set of shears on a sheep to shear it.");
			stage = 751;
			break;
		case 751:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "That's all I have to do?");
			stage = 752;
			break;
		case 752:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well once you've collected some wool you'll need to spin", "it into balls.");
			stage = 753;
			break;
		case 753:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Do you know how to spin wool?");
			stage = 754;
			break;
		case 754:
			interpreter.sendOptions("Select an Option", "I don't know how to spin wool, sorry.", "I'm something of an expert actually!");
			stage = 755;
			break;
		case 755:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I don't know how to spin wool, sorry.");
				stage = 756;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm something of an expert actually!");
				stage = 850;
				break;
			}
			break;
		case 850:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well you can stop grinning and get to work then.");
			stage = 851;
			break;
		case 851:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'm not paying you by the hour!");
			stage = 852;
			break;
		case 852:
			end();
			quest.start(player);
			break;
		case 756:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Don't worry, it's quite simple!");
			stage = 757;
			break;
		case 757:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The nearest Spinning Wheel can be found on the first", "floor of Lumbridge Castle.");
			stage = 758;
			break;
		case 758:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "To get to the Lumbridge Castle just follow the road east.");
			stage = 759;
			break;
		case 759:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Thank you!");
			stage = 760;
			break;
		case 760:
			end();
			quest.start(player);
			break;
		case 671:
			end();
			break;
		case 560:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well what do you expect if you ask a farmer for a", "quest?");
			stage = 21;
			break;
		case 456:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well now, no one has ever seen The Thing. That's", "why we call it The Thing, 'cos we don't know what it is.");
			stage = 457;
			break;
		case 457:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Someone say it's a black hearted shapeshifter, hungering for", "the souls of hard working decent folk like me. Others", "say it's just a sheep.");
			stage = 21;
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "What, on my land? Leave my livestock alone you", "scoundrel!");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 4000:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How many more do I need to give you?");
			stage = 4001;
			break;
		case 4001:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You need to collect " + SheepShearer.getWoolLeft(player) + " more balls of wool.");
			stage = 4002;
			break;
		case 4002:
			if (!player.getInventory().contains(1759, 1)) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I haven't got any at the moment.");
				stage = 4003;
				break;
			}
			break;
		case 4003:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well at least you haven't been eaten.");
			stage = 21;
			break;
		case 4004:
			break;
		case 5000:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm back!");
			stage = 50001;
			break;
		case 50001:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "How are you doing getting those balls of wool?");
			stage = 50002;
			break;
		case 50002:
			if (player.getInventory().contains(1759, 1)) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I have some.");
				stage = 50010;
				break;
			}
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How many more do I need to give you?");
			stage = 50003;
			break;
		case 50003:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You need to collect " + SheepShearer.getWoolLeft(player) + " more balls of wool.");
			stage = 50004;
			break;
		case 50004:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Well I've got some wool. I've not managed to make it", "into a ball though.");
			stage = 50005;
			break;
		case 50005:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well go find a spinning wheel then. You can find one", "on the first floor of the Lumbridge Castle, just walk east on", "the road outside my house and you'll find Lumbridge.");
			stage = 50006;
			break;
		case 50006:
			end();
			break;
		case 50010:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Give 'em here then.");
			stage = 5011;
			break;
		case 5011:
			int removeAmount = SheepShearer.getWoolRemove(player);
			if (SheepShearer.addWool(player)) {
				if (SheepShearer.getWoolGiven(player) == 20) {
					player.getQuestRepository().getQuest("Sheep Shearer").setStage(player, 90);
				}
				interpreter.sendDialogue("You give fred " + removeAmount + " balls of wool");
				stage = 5012;
			}
			break;
		case 5012:
			if (SheepShearer.getWoolGiven(player) == 20) {
				player.getQuestRepository().getQuest("Sheep Shearer").setStage(player, 90);
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "That's the last of them.");
				stage = 999;
				break;// enough
			}
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "That's all I've got so far.");
			stage = 5013;
			break;
		case 5013:
			if (SheepShearer.getWoolGiven(player) == 20) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "That's the last of them.");
				stage = 999;
				break;// enough
			}
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I need " + SheepShearer.getWoolCollect(player) + " before I can pay you.");
			stage = 5014;
			break;
		case 999:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I guess I'd better pay you then.");
			stage = 1000;
			break;
		case 1000:
			end();
			Quest quest11 = player.getQuestRepository().getQuest("Sheep Shearer");
			quest11.finish(player);
			player.removeAttribute("sheep-shearer:wool");
			break;
		case 5014:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ok, I'll work on it.");
			stage = 5015;
			break;
		case 5015:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 758 };
	}
}
