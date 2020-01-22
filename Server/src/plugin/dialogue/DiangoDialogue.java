package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used by diango.
 * @author 'Vexia
 * @version 1.1
 */
@InitializablePlugin
public final class DiangoDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code DiangoDialogue {@code Object}.
	 */
	public DiangoDialogue() {
		/*
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code DiangoDialogue} {@code Object}.
	 * @param player the player.
	 */
	public DiangoDialogue(Player player) {
		super(player);
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			options("Spinning plates?", "I'd like to check holiday items please!", "I'd like to claim purchased cosmetics.");
			stage++;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				player("Spinning plates?");
				stage = 10;
				break;
			case 2:
				player("I'd like to check holiday items please?");
				stage = 20;
				break;
			case 3:
				player("I'd like to claim purchased cosmetics.");
				stage = 30;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "That's right. There's a funny story behind them, their", "shipment was held up by thieves");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "The crate was marked 'Dragon Plates'.", "Apparently they thought it was some kind of armour,", "when really it's just a plate!");
			stage = 12;
			break;
		case 12:
			end();
			npc.openShop(player);
			break;
		case 20:
			npc("Sure thing, let me just see what you're missing.");
			stage++;
			break;
		case 21:
			openItemReturn(player);
			end();
			break;
		case 30:
			end();
			break;
		}
		return true;
	}

	/**
	 * Opens the item return.
	 * @param player the player.
	 */
	private void openItemReturn(Player player) {

	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DiangoDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Howdy there, partner! Want to see my spinning plates?", "Or did ya want a holiday item back?");
		stage = 0;
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 970 };
	}
}
