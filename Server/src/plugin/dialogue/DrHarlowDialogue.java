package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue used for dr harlow.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class DrHarlowDialogue extends DialoguePlugin {

	/**
	 * Represents the array of items related to dr harlow.
	 */
	private static final Item[] ITEMS = new Item[] { new Item(1549), new Item(1917, 1) };

	/**
	 * Constructs a new {@code DrHarlowDialogue} {@code Object}.
	 */
	public DrHarlowDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code DrHarlowDialogue} {@code Object}.
	 * @param player the player.
	 */
	public DrHarlowDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DrHarlowDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Buy me a drrink pleassh.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			if (player.getQuestRepository().getQuest("Vampire Slayer").getStage(player) == 10) {
				interpreter.sendOptions("Select an Option", "No, you've had enough.", "Morgan needs your help!");
				stage = 1;
			} else if (player.getQuestRepository().getQuest("Vampire Slayer").getStage(player) == 20) {
				if (player.getInventory().contains(1917, 1)) {
					interpreter.sendDialogues(player, null, "Here you go.");
					stage = 20;
				} else {
					interpreter.sendDialogues(player, null, "I'll just go and buy one.");
					stage = 2;
				}
			} else if (player.getQuestRepository().getQuest("Vampire Slayer").getStage(player) == 30) {
				if (!player.getBank().contains(1549, 1) && !player.getInventory().contains(1549, 1)) {
					if (!player.getInventory().add(ITEMS[0])) {
						GroundItem item = new GroundItem(ITEMS[0], npc.getLocation(), player);
						GroundItemManager.create(item);
					}
					interpreter.sendItemMessage(1549, "Dr Harlow hands you a stake.");
					stage = 27;
					return true;
				}
				if (player.getInventory().contains(1917, 1)) {
					interpreter.sendDialogues(player, null, "Here you go.");
					stage = 20;
				} else {
					interpreter.sendDialogues(player, null, "I'll just go and buy one.");
					stage = 2;
				}
			} else {
				interpreter.sendDialogues(player, null, "No, you've had enough.");
				stage = 2;
			}
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "No, you've had enough.");
				stage = 2;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "Morgan needs your help!");
				stage = 5;
				break;
			}
			break;
		case 5:
			interpreter.sendDialogues(npc, null, "Morgan you shhay..?");
			stage = 6;
			break;
		case 6:
			interpreter.sendDialogues(player, null, "His village is being terrorised by a vampire! He told me", "to ask you about how I can stop it.");
			stage = 7;
			break;
		case 7:
			interpreter.sendDialogues(npc, null, "Buy me a beer... then I'll teash you what you need to", "know...");
			stage = 8;
			break;
		case 8:
			interpreter.sendDialogues(player, null, "But this is your friend Morgan we're talking about!");
			stage = 9;
			break;
		case 9:
			interpreter.sendDialogues(npc, null, "Buy ush a drink anyway...");
			stage = 10;
			break;
		case 10:
			player.getQuestRepository().getQuest("Vampire Slayer").setStage(player, 20);
			end();
			break;
		case 2:
			end();
			break;
		case 20:
			if (player.getInventory().remove(ITEMS[1])) {
				interpreter.sendItemMessage(1917, "You give a beer to Dr Harlow.");
				player.getQuestRepository().getQuest("Vampire Slayer").setStage(player, 30);
				stage = 21;
			}
			break;
		case 21:
			interpreter.sendDialogues(npc, null, "Cheersh matey...");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(player, null, "So tell me how to kill vampires then.");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(npc, null, "Yesh Yesh vampires, I was very good at", "killing em once...");
			stage = 24;
			break;
		case 24:
			interpreter.sendDialogue("Dr Harlow appears to sober up slightly.");
			stage = 25;
			break;
		case 25:
			interpreter.sendDialogues(npc, null, "Well you're gonna to need a stake, otherwise he'll just", "regenerate. Yes, you must have a stake to finish it off..", "I just happen to have one with me.");
			stage = 26;
			break;
		case 26:
			if (!player.getInventory().add(ITEMS[0])) {
				GroundItem item = new GroundItem(ITEMS[0], npc.getLocation(), player);
				GroundItemManager.create(item);
			}
			interpreter.sendItemMessage(1549, "Dr Harlow hands you a stake.");
			stage = 27;
			break;
		case 27:
			interpreter.sendDialogues(npc, null, "You'll need a hammer as well, to drive it in properly,", "your everyday general store hammer will do. One last", "thing... It's wise to carry garlic with you, vampires are", "somewhat weakend if they can smell garlic. Morgan");
			stage = 28;
			break;
		case 28:
			interpreter.sendDialogues(npc, null, "alwys liked garlic, you should try his house. But", "remember, a vampire is still a dangerous foe!");
			stage = 29;
			break;
		case 29:
			interpreter.sendDialogues(player, null, "Thank you very much!");
			stage = 30;
			break;
		case 30:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 756 };
	}
}
