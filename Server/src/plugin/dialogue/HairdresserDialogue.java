package plugin.dialogue;

import org.crandor.game.component.Component;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.link.appearance.Gender;

/**
 * Represents the dialogue plugin used for the hairdresser.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class HairdresserDialogue extends DialoguePlugin {

	/**
	 * Represents if were a male.
	 */
	private boolean male = true;

	/**
	 * Constructs a new {@code HairdresserDialogue} {@code Object}.
	 */
	public HairdresserDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code HairdresserDialogue} {@code Object}.
	 * @param player the player.
	 */
	public HairdresserDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HairdresserDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		final String sex = male ? "sir" : "mam";
		if (player.getAppearance().getGender() == Gender.FEMALE) {
			male = false;
		}
		if (args.length == 2) {
			String man = male ? "men's" : "womens";
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Certainly " + sex + ". I cut " + man + " hair at the bargain rate of", "only 1000 gold coins. I'll even throw in a free recolour!");
			stage = 11;
			return true;
		}
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Good afternoon " + sex + ". In need of a haircut are we?", male ? "Perhaps a shave too?" : "");
		stage = 1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 1:
			if (male) {
				interpreter.sendOptions("Select an Option", "I'd like a haircut please.", "I'd like a shave please.", "No thank you.");
				stage = 2;
			} else {
				interpreter.sendOptions("Select an Option", "I'd like a haircut please.", "No thank you.");
				stage = 100;
			}
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I'd like a haircut please.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I'd like a shave please.");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No thank you.");
				stage = 30;
				break;
			}
			break;
		case 10:
			String sex = male ? "sir" : "mam";
			String man = male ? "men's" : "womens";
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Certainly " + sex + ". I cut " + man + " hair at the bargain rate of", "only 1000 gold coins. I'll even throw in a free recolour!");
			stage = 11;
			break;
		case 11:
			if (player.getInventory().contains(995, 1000)) {
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Please select the hairstyle and colour you would like", "from this brochure.");
				stage = 14;
			} else {
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I don't have 1000 gold coins on me...");
				stage = 12;
			}
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Well, come back when you do. I'm not running a", "charity here!");
			stage = 13;
			break;
		case 13:
			end();
			break;
		case 14:
			end();
			if (male) {
				player.getInterfaceManager().open(new Component(204));
			} else {
				player.getInterfaceManager().open(new Component(203));
			}
			break;
		case 30:
			end();
			break;
		case 100:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I'd like a haircut please.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No thank you.");
				stage = 30;
				break;
			}
			break;
		case 20:
			String sex1 = male ? "sir" : "mam";
			String man1 = male ? "men's" : "womens";
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Certainly " + sex1 + ". I cut " + man1 + " hair at the bargain rate of", "only 1000 gold coins. I'll even throw in a free recolour!");
			stage = 21;
			break;
		case 21:
			if (player.getInventory().contains(995, 1000)) {
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Please select the hairstyle and colour you would like", "from this brochure.");
				stage = 150;
			} else {
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I don't have 1000 gold coins on me...");
				stage = 12;
			}
			break;
		case 150:
			end();
			player.getInterfaceManager().open(new Component(199));
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 598 };
	}
}
