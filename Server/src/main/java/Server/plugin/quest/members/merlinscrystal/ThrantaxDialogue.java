package plugin.quest.members.merlinscrystal;

import plugin.dialogue.DialogueInterpreter;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Handles the thrantax dialogue.
 * @author Vexia
 * @author Splinter
 */
public class ThrantaxDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code ThrantaxDialogue} {@code Object}
	 */
	public ThrantaxDialogue() {
		/*
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ThrantaxDialogue} {@code Object}
	 * @param player the player.
	 */
	public ThrantaxDialogue(Player player) {
		super(player);
	}

	@Override
	public boolean open(Object... args) {
		interpreter.sendDialogue("Suddenly a mighty spirit appears!");
		player.lock();
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		final Quest quest = player.getQuestRepository().getQuest("Merlin's Crystal");
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Now what were those magic words again?");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "Snarthtrick Candanto Termon", "Snarthon Candtrick Termanto", "Snarthanto Cando Termtrick");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Snarthtrick...");
				stage = 100;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Snarthon...");
				stage = 201;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Snarthanto...");
				stage = 300;
				break;
			}
			break;
		case 100:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Candanto...");
			stage = 101;
			break;
		case 101:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Termon!");
			stage = 500;
			break;
		case 200:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Candtrick...");
			stage = 201;
			break;
		case 201:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Termanto!");
			stage = 203;
			break;
		case 300:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Cando...");
			stage = 301;
			break;
		case 301:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Termtrick!");
			stage = 500;
			break;
		case 203:
			interpreter.sendDialogues(238, FacialExpression.HALF_GUILTY, "GRAAAAAARGH!");
			stage = 204;
			break;
		case 204:
			interpreter.sendDialogues(238, FacialExpression.HALF_GUILTY, "Thou hast me in thine control. So that I mayst return", "from whence I came, I must grant thee a boon.");
			stage = 205;
			break;
		case 205:
			interpreter.sendDialogues(238, FacialExpression.HALF_GUILTY, "What dost thou wish of me?");
			stage = 206;
			break;
		case 206:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I wish you to free Merlin from his giant crystal!");
			stage = 207;
			break;
		case 207:
			interpreter.sendDialogues(238, FacialExpression.HALF_GUILTY, "GRAAAAAARGH!");
			stage = 208;
			break;
		case 208:
			interpreter.sendDialogues(238, FacialExpression.HALF_GUILTY, "The deed is done.");
			stage = 209;
			break;
		case 209:
			interpreter.sendDialogues(238, FacialExpression.HALF_GUILTY, "Thou mayst now shatter Merlin's Crystal with", "Excalibur,");
			stage = 210;
			break;
		case 210:
			interpreter.sendDialogues(238, FacialExpression.HALF_GUILTY, "and I can once more rest. Begone! And leave me once", "more in peace.");
			stage = 1000;
			break;
		case 303:
			break;
		case 500:
			end();
			player.getPacketDispatch().sendMessage("That was the incorrect incantation. The spirit attacks!");
			player.unlock();
			NPC npc = player.getAttribute("thrantax_npc", null);
			if (npc != null) {
				npc.attack(player);
			}
			player.removeAttribute("thrantax_npc");
			end();
			break;
		case 1000:
			player.removeAttribute("thrantax_npc");
			player.unlock();
			quest.setStage(player, 90);
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ThrantaxDialogue(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { DialogueInterpreter.getDialogueKey("thrantax_dialogue"), 238 };
	}
}