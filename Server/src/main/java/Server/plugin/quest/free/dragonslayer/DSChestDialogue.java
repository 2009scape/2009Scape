package plugin.quest.free.dragonslayer;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;
import core.game.node.item.GroundItemManager;

/**
 * Represents the dialogue plugin used for the dragon slayer chest.
 * @author 'Vexia
 * @version 1.0
 */
public final class DSChestDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code DSChestDialogue} {@code Object}.
	 */
	public DSChestDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code DSChestDialogue} {@code Object}.
	 * @param player the player.
	 */
	public DSChestDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DSChestDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		interpreter.sendDialogue("As you open the chest, you notice an inscription on the lid:");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogue("Here I rest the map to my beloved home. To whoever finds it, I beg", "of you, let it be. I was honour-bound not to destroy the map piece,", "but I have used all my magical skill to keep it from being recovered.");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogue("This map leads to the lair of the beast that destroyed my home,", "devoured my family, and burned to a cinder all that I love. But", "revenge would not benefit me now, and to disturb this beast is to risk", "brining its wrath down upon another land.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogue("I cannot stop you from taking this map piece now, but think on this:", "if you can slay the Dragon of Crandor, you are a greater hero than", "my land ever produced. There is no shame in backing out now.");
			stage = 3;
			break;
		case 3:
			interpreter.sendItemMessage(DragonSlayer.MAGIC_PIECE.getId(), "You find a map piece in the chest.");
			stage = 4;
			break;
		case 4:
			if (!player.getInventory().add(DragonSlayer.MAGIC_PIECE)) {
				GroundItemManager.create(DragonSlayer.MAGIC_PIECE, player);
			}
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3802875 };
	}
}