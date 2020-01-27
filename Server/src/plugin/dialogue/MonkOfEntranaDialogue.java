package plugin.dialogue;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.travel.ship.Ships;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the monk of entrana dialogue.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class MonkOfEntranaDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code MonkOfEntranaDialogue} {@code Object}.
	 */
	public MonkOfEntranaDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MonkOfEntranaDialogue} {@code Object}.
	 * @param player the player.
	 */
	public MonkOfEntranaDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MonkOfEntranaDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (npc.getId() == 2730 || npc.getId() == 658 || npc.getId() == 2731) {
			interpreter.sendDialogues(npc, null, "Do you wish to leave holy entrana?");
			stage = 500;
			return true;
		}
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Do you seek passage to holy Entrana? If so, you must", "leave your weaponry and armour behind. This is", "Saradomin's will.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "No, not right now.", "Yes, okay, I'm ready to go.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "No, not right now.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "Yes, okay, I'm ready to go.");
				stage = 20;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, null, "Very well.");
			stage = 11;
			break;
		case 11:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, null, "Very well. One moment please.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogue("The monk quickly searches you.");
			stage = 22;
			break;
		case 22:
			if (!ItemDefinition.canEnterEntrana(player)) {
				interpreter.sendDialogues(npc, null, "NO WEAPONS OR ARMOUR are permitted on holy", "Entrana AT ALL. We will not allow you to travel there", "in breach of mighty Saradomin's edict.");
				stage = 23;
				return true;
			}
			interpreter.sendDialogues(npc, null, "All is satisfactory. You may board the boat now.");
			stage = 25;
			break;
		case 23:
			interpreter.sendDialogues(npc, null, "Do not try and decieve us again. Come back when you", "have liad down your Zamorakian instruments of death.");
			stage = 24;
			break;
		case 24:
			end();
			break;
		case 25:
			end();
			Ships.PORT_SARIM_TO_ENTRANA.sail(player);
			break;
		case 500:
			interpreter.sendOptions("Select an Option", "Yes, I'm ready to go.", "Not just yet.");
			stage = 501;
			break;
		case 501:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "Yes, I'm ready to go.");
				stage = 510;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "Not just yet.");
				stage = 520;
				break;
			}
			break;
		case 510:
			interpreter.sendDialogues(npc, null, "Okay, let's board...");
			stage = 511;
			break;
		case 511:
			end();
			Ships.ENTRANA_TO_PORT_SARIM.sail(player);
			break;
		case 520:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2728, 657, 2729, 2730, 2731, 658 };
	}
}
