package plugin.quest.tutorials.tutorialisland;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;

/**
 * Represents the dialogue plugin used for the brother brace npc.
 * @author Vexia
 */
@InitializablePlugin
public final class BrotherBraceDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code BrotherBraceDialogue} {@code Object}.
	 */
	public BrotherBraceDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code BrotherBraceDialogue} {@code Object}.
	 * @param player the player.
	 */
	public BrotherBraceDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new BrotherBraceDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		switch (TutorialSession.getExtension(player).getStage()) {
		case 60:
			interpreter.sendDialogues(player, null, "Good day, brother my name's " + player.getUsername() + ".");
			stage = 0;
			break;
		case 62:
			interpreter.sendDialogues(npc, null, "This is your Prayer list. Prayers can help a lot in", "combat. Click on the prayer you wish to use to activate", "it and click it again to deactivate it.");
			stage = 0;
			break;
		case 65:
			interpreter.sendDialogues(npc, null, "Good. Now you have both menus open, I'll tell you a ", "little about each. You can add people to either list by", "clicking the add button then typing their name into the", "box that appears.");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (TutorialSession.getExtension(player).getStage()) {
		case 60:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(npc, null, "Hello, " + player.getUsername() + ". I'm Brother Brace. I'm here to tell", "you all about Prayer.");
				stage = 1;
				break;
			case 1:
				end();
				TutorialStage.load(player, 61, false);
				break;
			}
			break;
		case 62:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(npc, null, "Active prayers wil drain your Prayer Points which", "you can recharge by finding an altar or other holy spot", "and praying there.");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(npc, null, "As you noticed, most enemies will drop bones when", "defeated. Burying bones by clicking them in your", "inventory will gain you Prayer experience.");
				stage = 2;
				break;
			case 2:
				interpreter.sendDialogues(npc, null, "I'm also the community officer 'round here, so it's my", "job to tell you about your friends and ignore list.");
				stage = 3;
				break;
			case 3:
				end();
				TutorialStage.load(player, 63, false);
				break;
			}
			break;
		case 65:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(npc, null, "You remove people from the lists in the same way. If", "you add someone to your ignore list they will not be", "able to talk to you or send any form of message to", "you.");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(npc, null, "Your friends list shows the online status of your", "friends. Friends in the red are offline, friends in green", "are online and on the same server and friends in yellow", "are online but on a different server.");
				stage = 2;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "Are there rules on in-game behaviour? ");
				stage = 3;
				break;
			case 3:
				interpreter.sendDialogues(npc, null, "Yes, you should read the rules of conduct on the", "website to make sure you do nothing to get yourself", "banned.");
				stage = 4;
				break;
			case 4:
				interpreter.sendDialogues(npc, null, "But in general, always try to be courteous to other", "players - remember the people in the game are real", "people with real feelings.");
				stage = 5;
				break;
			case 5:
				interpreter.sendDialogues(npc, null, "If you go 'round being abusive or causing trouble your", "character could end up being the one in trouble");
				stage = 6;
				break;
			case 6:
				interpreter.sendDialogues(player, null, "Okay thanks. I'll bear that in mind.");
				stage = 7;
				break;
			case 7:
				end();
				TutorialStage.load(player, 66, false);
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 954 };
	}
}