package plugin.quest.tutorials.tutorialisland;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.world.GameWorld;
import core.plugin.InitializablePlugin;

/**
 * Represents the financial advisor plugin.
 * @author Vexia
 */
@InitializablePlugin
public final class FinancialAdvisorDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code FinancialAdvisorPlugin {@code Object}.
	 */
	public FinancialAdvisorDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code FinancialAdvisorPlugin} {@code Object}.
	 * @param player the player.
	 */
	public FinancialAdvisorDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new FinancialAdvisorDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		switch (TutorialSession.getExtension(player).getStage()) {
		case 58:
			interpreter.sendDialogues(player, null, "Hello. Who are you?");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (TutorialSession.getExtension(player).getStage()) {
		case 58:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(npc, null, "I'm the Financial Advisor. I'm here to tell people how to", "make money.");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(player, null, "Okay. How can I make money then?");
				stage = 2;
				break;
			case 2:
				interpreter.sendDialogues(npc, null, "How you can make money? Quite.");
				stage = 3;
				break;
			case 3:
				interpreter.sendDialogues(npc, null, "Well there are three basic ways of making money here:", "combat, quests, and trading. I will talk you through each", "of them very quickly.");
				stage = 4;
				break;
			case 4:
				interpreter.sendDialogues(npc, null, "Let's start with combat as it is probably still fresh in", "your mind. Many enemies, both human and monster", "will drop items when they die.");
				stage = 5;
				break;
			case 5:
				interpreter.sendDialogues(npc, null, "Now, the next way to earn money quickly is by quests", "Many people on " + GameWorld.getName() + " have things they need", "doing, which they will reward you for.");
				stage = 6;
				break;
			case 6:
				interpreter.sendDialogues(npc, null, "By getting a high level in skills such as Cooking, Mining,", "Smithing or Fishing, you can create or catch your own", "items and sell them for pure profit.");
				stage = 7;
				break;
			case 7:
				interpreter.sendDialogues(npc, null, "Well that about covers it. Come back if you'd like to go", "over this again.");
				stage = 8;
				break;
			case 8:
				end();
				TutorialStage.load(player, 59, false);
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 947 };
	}
}