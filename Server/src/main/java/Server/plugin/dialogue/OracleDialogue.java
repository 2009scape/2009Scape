package plugin.dialogue;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.link.quest.Quest;
import plugin.quest.free.dragonslayer.DragonSlayer;

/**
 * Represents the oracle dialogue plugin related to dragon slayer.
 * @author 'Vexia
 */
@InitializablePlugin
public final class OracleDialogue extends DialoguePlugin {

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code OracleDialogue} {@code Object}.
	 */
	public OracleDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code OracleDialogue} {@code Object}.
	 * @param player the player.
	 */
	public OracleDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new OracleDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest(DragonSlayer.NAME);
		switch (quest.getStage(player)) {
		case 20:
			player("I seek a piece of the map to the island of Crandor.");
			stage = 0;
			break;
		default:
			player("Can you impart your wise knowledge on me, O Oracle?");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 20:
			switch (stage) {
			case 0:
				npc("The map's behind a door below,", "but entering is rather tough.", "This is that you need to know:", "You must use the following stuff.");
				stage = 1;
				break;
			case 1:
				npc("First, a drink used by a mage.", "Next, some worm string, changed to sheet.", "Then, a small crustacean cage.", "Last, a bowl that's not seen heat.");
				stage = 2;
				break;
			case 2:
				end();
				break;
			}
			break;
		default:
			switch (stage) {
			case 0:
				npc("Don't judge a book by its cover - judge it on its", "grammar and punctuation.");
				stage = 1;
				break;
			case 1:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 746 };
	}
}
