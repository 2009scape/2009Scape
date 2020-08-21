package plugin.quest.members.waterfallquest;

import plugin.dialogue.DialogueInterpreter;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;

/**
 * Handles Golrie's Dialogue for the Waterfall quest
 * @author Splinter
 */
public class GolrieDialogue extends DialoguePlugin {

	public GolrieDialogue() {

	}

	public GolrieDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { DialogueInterpreter.getDialogueKey("golrie_dialogue"), 306 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 100:
			end();
			break;

		/* Main dialogue sequence */
		case 0:
			interpreter.sendDialogues(306, FacialExpression.OLD_NORMAL, "That's me. I've been stuck in here for weeks, those", "goblins are trying to steal my family's heirlooms. My", "grandad gave me all sorts of old junk.");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.ASKING, "Do you mind if I have a look?");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(306, FacialExpression.OLD_HAPPY, "No, of course not.");
			stage = 3;
			break;
		case 3:
			//TODO: Possibly change this to chat message
			interpreter.sendDialogue("You look amongst the junk on the floor.");
			stage = 4;
			break;
		case 4:
			//TODO: Possibly change this to chat message
			interpreter.sendDialogue("Mixed with the junk on the floor you find Glarial's pebble.");
			stage = 5;
			break;
		case 5:
			//TODO: Possibly change to HAPPY
			interpreter.sendDialogues(player, FacialExpression.ASKING, "Could I take this old pebble?");
			stage = 6;
			break;
		case 6:
			interpreter.sendDialogues(306, FacialExpression.OLD_NORMAL, "Oh that, yes have it, it's just some old elven junk I", "believe.");
			player.getInventory().add(new Item(294, 1));
			stage = 7;
			break;
		case 7:
			//TODO: Possibly change this to chat message
			interpreter.sendDialogue("You give the key to Golrie.");
			stage = 8;
			break;
		case 8:
			interpreter.sendDialogues(306, FacialExpression.OLD_HAPPY, "Thanks a lot for the key traveller. I think I'll wait in", "here until those goblins get bored and leave.");
			player.getInventory().remove(new Item(293, 1));
			stage = 9;
			break;
		case 9:
			interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "OK... Take care Golrie.");
			stage = 100;
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GolrieDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "Hello, is your name Golrie?");
		stage = 0;
		return true;
	}
}