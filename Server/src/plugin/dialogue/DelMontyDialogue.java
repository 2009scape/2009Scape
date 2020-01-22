package plugin.dialogue;

import org.crandor.game.container.impl.EquipmentContainer;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the del monty npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class DelMontyDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code DelMontyDialogue} {@code Object}.
	 */
	public DelMontyDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code DelMontyDialogue} {@code Object}.
	 * @param player the player.
	 */
	public DelMontyDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DelMontyDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		player("Hey kitty!");
		stage = 100;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			end();
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "No thanks, I'm too stressed out.", "The stocks on nokia went way down", " and I'm losing money.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Oh, well isn't it still your job to help me out?");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Like I give a flying fuck about you...", "I just need my money back.");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Listen here you stupid fucking cat... I need fucking", "EXP, and if you're not willing to teach me,", "I'll burn you like I did to the Jews.");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Fuck off kid, I DO WHATEVER I WANT AND", "I DON'T GIVE A DAMN ABOUT YOU NOR", "ANYONE ELSE.");
			stage = 6;
			break;
		case 6:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Have fun killing yourself when", "you realized your investment lost your life.");
			stage = 7;
			break;
		case 7:
			end();
			break;
		case 100:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Hiss!");
			stage = 0;
			break;
		}
		return true;
	}

	/**
	 * Method used to check if a player has a cat speak amulet.
	 * @param player the player.
	 * @return {@code True} so.
	 */
	public static boolean hasCatAmulet(Player player) {
		Item item = player.getEquipment().get(EquipmentContainer.SLOT_AMULET);
		if (item == null)
			return false;
		return item.getId() == 4677 || item.getId() == 6544;
	}

	@Override
	public int[] getIds() {
		return new int[] { 5563 };
	}

}
