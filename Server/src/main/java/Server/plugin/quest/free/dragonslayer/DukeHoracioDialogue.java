package plugin.quest.free.dragonslayer;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the duke horc
 * @author 'Vexia
 * @version 1.0
 */
public final class DukeHoracioDialogue extends DialoguePlugin {

	/**
	 * Represents the air talisman item.
	 */
	private static final Item TALISMAN = new Item(1438);

	/**
	 * Constructs a new {@code DukeHoracioDialogue} {@code Object}.
	 */
	public DukeHoracioDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code DukeHoracioDialogue} {@code Object}.
	 * @param player the player.
	 */
	public DukeHoracioDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 741 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		if (player.getQuestRepository().getQuest("Dragon Slayer").getStage(player) >= 20 && !player.getInventory().containsItem(DragonSlayer.SHIELD) && !player.getBank().containsItem(DragonSlayer.SHIELD) && !player.getEquipment().containsItem(DragonSlayer.SHIELD)) {
			switch (stage) {
			case 0:
				player("I seek a shield that will protect me from dragonbreath.");
				stage = 400;
				break;
			case 400:
				npc("A knight going on a dragon quest, hmm? What", "dragon do you intend to slay?");
				stage = 401;
				break;
			case 401:
				player("Elvarg, the dragon of Crandor island!");
				stage = 402;
				break;
			case 402:
				npc("Elvarg? Are you sure?");
				stage = 403;
				break;
			case 403:
				player("Yes!");
				stage = 404;
				break;
			case 404:
				npc("Well, you're a braver man than I!");
				stage = 405;
				break;
			case 405:
				player("Why is everyone scared of this dragon?");
				stage = 406;
				break;
			case 406:
				npc("Back in my father's day, Crandor was an important", "city-state. Politically, it was important as Falador or", "Varrock and its shipes traded with every port.");
				stage = 407;
				break;
			case 407:
				npc("But, one day when I was little, all contact was lost. The", "trading ships and diplomatic envoys just stopped", "coming.");
				stage = 408;
				break;
			case 408:
				npc("I remember my father being very scared. He posted", "lookouts on the roof to warn if the dragon was", "approaching. All the city rulers worried that", "Elvarg would devastate the whole continent.");
				stage = 409;
				break;
			case 409:
				player("So, are you going to give me the shield or not?");
				stage = 410;
				break;
			case 410:
				npc("If you really think you're up to it then perhaphs you", "are the one who can kill this dragon.");
				stage = 411;
				break;
			case 411:
				if (!player.getInventory().add(DragonSlayer.SHIELD)) {
					GroundItemManager.create(DragonSlayer.SHIELD, player);
				}
				interpreter.sendItemMessage(DragonSlayer.SHIELD, "The Duke hands you a heavy orange shield.");
				stage = 412;
				break;
			}
			return true;
		}
		if (player.getQuestRepository().getQuest("Dragon Slayer").getStage(player) == 20) {
			switch (stage) {
			case 412:
				npc("Take care out there. If you kill it...");
				stage = 413;
				return true;
			case 413:
				npc("If you kill it, for Saradomin's sake make sure it's really", "dead!");
				stage = 414;
				return true;
			case 414:
				end();
				return true;
			}
		}
		final Quest quest = player.getQuestRepository().getQuest("Rune Mysteries");
		switch (stage) {
		case 0:
			if (player.getQuestRepository().getQuest("Dragon Slayer").getStage(player) == 100 && !player.getInventory().containsItem(DragonSlayer.SHIELD)) {
				interpreter.sendOptions("Select an Option", "I seek a shield that will protect me from dragonbreath.", "Have you any quests for me?", "Where can I find money?");
				stage = -5;
				return true;
			} else {
				interpreter.sendOptions("Select an Option", "Have you any quests for me?", "Where can I find money?");
			}
			stage = 1;
			break;
		case -5:
			switch (buttonId) {
			case 1:
				stage = 800;
				handleShield(buttonId);
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Have any quests for me?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I hear many of the local people earn money by", "learning a skill. Many people get by in life by becoming", "accomplished smiths, cooks, miners and woodcutters.");
				stage = 30;
				break;
			}
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Have any quests for me?");
				stage = 20;
				break;
			case 2:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I hear many of the local people earn money by learning a", "skill. Many people get by in life by becoming accomplished", "smiths, cooks, miners and woodcutters.");
				stage = 30;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You haven't seem to have slain Elvarg yet!", "Once you have slain Elvarg come back and talk to me.");
			stage = 11;
			break;
		case 20:
			if (quest.getStage(player) == 10) {
				interpreter.sendDialogues(npc, null, "The only task remotely approaching a quest is the", "delivery of the talisman I gave you to the head wizard", "of the Wizards' Tower,");
				stage = 1000;
				break;
			}
			if (quest.getStage(player) > 10) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Nope, I've got everything under control", "in the castle at the moment.");
				stage = 69;
				return true;
			}
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, it's not really a quest but I recently discovered", "this strange talisman.");
			stage = 21;
			break;
		case 69:
			end();
			break;
		case 1000:
			interpreter.sendDialogues(npc, null, "south-west of here. I suggest you deliver it to him as", "soon as possible. I have the oddest feeling that is", "important...");
			stage = 1001;
			break;
		case 1001:
			end();
			break;
		case 21:
			interpreter.sendDialogues(npc, null, "It seems to be mystical and I have never seen anything", "like it before. Would you take it to the head wizard at");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(npc, null, "the Wizards' Tower for me? It's just south-west of here", "and should not take you very long at all. I would be", "awfully grateful.");
			stage = 23;
			break;
		case 23:
			interpreter.sendOptions("Select an Option", "Sure, no problem.", "Not right now.");
			stage = 24;
			break;
		case 24:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "Sure, no problem.");
				stage = 100;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "Not right now.");
				stage = 26;
				break;
			}
			break;
		case 100:
			interpreter.sendDialogues(npc, null, "Thank you very much, stranger. I am sure the head", "wizard will reward you for such an interesting find.");
			stage = 101;
			break;
		case 101:
			interpreter.sendDialogue("The Duke hands you an " + Quest.BLUE + "air talisman</col>.");
			stage = 102;
			break;
		case 102:
			quest.start(player);
			player.getQuestRepository().syncronizeTab(player);
			if (!player.getInventory().add(TALISMAN)) {
				GroundItemManager.create(TALISMAN, player.getLocation(), player);
			}
			end();
			break;
		case 26:
			interpreter.sendDialogues(npc, null, "As you wish, stranger, although I have this strange", "feeling that it is important. Unfortunately, I cannot", "leave my castle unattended.");
			stage = 27;
			break;
		case 27:
			end();
			break;
		case 30:
			end();
			break;
		default:
			handleShield(buttonId);
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DukeHoracioDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Greetings. Welcome to my castle.");
		stage = 0;
		return true;
	}

	public void handleShield(int buttonId) {
		switch (stage) {
		case 800:
			player("I seek a shield that will protect me from dragonbreath.");
			stage = 801;
			break;
		case 801:
			npc("A knight going on a dragon quest, hmm? What", "dragon do you intend to slay?");
			stage = 802;
			break;
		case 802:
			player("Oh, no dragon in particular. I just feel like killing a", "dragon.");
			stage = 803;
			break;
		case 803:
			npc("Of course. Now you've slain Elvarg, you've earned", "the right to call the shield your own!");
			stage = 804;
			break;
		case 804:
			if (!player.getInventory().add(DragonSlayer.SHIELD)) {
				GroundItemManager.create(DragonSlayer.SHIELD, player);
			}
			interpreter.sendItemMessage(DragonSlayer.SHIELD, "The Duke hands you the shield.");
			stage = 805;
			break;
		case 805:
			end();
			break;
		}
	}
}