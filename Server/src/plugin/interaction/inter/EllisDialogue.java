package plugin.interaction.inter;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.skill.free.crafting.TanningProduct;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the ellis dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class EllisDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code EllisDialogue} {@code Object}.
	 */
	public EllisDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code EllisDialogue} {@code Object}.
	 * @param player the player.
	 */
	public EllisDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new EllisDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Greetings friend I am a manufacturer of leather.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			player.getInventory().refresh();
			Item items[] = player.getInventory().toArray();
			for (int i = 0; i < items.length; i++) {
				if (items[i] == null) {
					continue;
				}
				if (TanningProduct.forItemId(items[i].getId()) != null) {
					interpreter.sendDialogues(npc, null, "I see you have brought me some hides.", "Would you like me to tan them for you?");
					stage = 100;
					return true;
				}
			}
			interpreter.sendOptions("What would you like to say?", "Can I buy some leather?", "Leather is rather weak stuff.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Can I buy some leather then?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Leather is rather weak stuff.");
				stage = 3000;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I make leather from animal hides. Bring me some cowhides", "and one gold coin per hide, and I'll tan them into soft", "leather for you.");
			stage = 2000;
			break;
		case 2000:
			end();
			break;
		case 3000:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Normal leather may be quite weak, but it's very cheap - I", "make it from cowhides for only 1 gp per hide - and it's so", "easy to craft that anyone can work with it.");
			stage = 3001;
			break;
		case 3001:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Alernatively you could try hard leather. It's not so easy", "to craft, but I only charge 3 gp per cowhide to prepare it,", "and it makes much sturdier armour.");
			stage = 3002;
			break;
		case 3002:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Thanks; I'll bear it in mind.");
			stage = 3003;
			break;
		case 3003:
			end();
			break;
		case 100:
			interpreter.sendOptions("Select an Option", "Yes please.", "No thanks.");
			stage = 101;
			break;
		case 101:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "Yes please.");
				stage = 210;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "No thanks.");
				stage = 200;
				break;
			}
			break;
		case 210:
			end();
			TanningProduct.open(player, 2824);
			break;
		case 200:
			interpreter.sendDialogues(npc, null, "Very well, sir, as you wish.");
			stage = 201;
			break;
		case 201:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2824 };
	}
}
