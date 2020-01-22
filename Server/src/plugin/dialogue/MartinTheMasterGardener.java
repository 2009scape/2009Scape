package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.Skillcape;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for marti the master gardener.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class MartinTheMasterGardener extends DialoguePlugin {

	/**
	 * Constructs a new {@code MartinTheMasterGardener} {@code Object}.
	 */
	public MartinTheMasterGardener() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MartinTheMasterGardener} {@code Object}.
	 * @param player the player.
	 */
	public MartinTheMasterGardener(Player player) {
		super(player);
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What is that cape that you're wearing?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I can't chat now, I have too many things to worry", "about.");
				stage = 20;
				break;
			}
			break;
		case 20:
			end();
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "This is a Skillcape of Farming, isn't it incredbile? It's a", "symbol of my ability as the finest farmer in the land!");
			stage = 11;
			break;
		case 11:
			if (player.getSkills().getStaticLevel(Skills.FARMING) == 99) {
				npc("Ah! I see you have mastered the skill of Farming,", "would you like to purchase a Farming cape for", "a fee of 99000 coins?");
				stage = 12;
			} else {
				end();
			}
			break;
		case 12:
			options("Yes, please.", "No, thanks.");
			stage = 13;
			break;
		case 13:
			switch (buttonId) {
			case 1:
				player("Yes, please.");
				stage = 14;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 14:
			if (Skillcape.purchase(player, Skills.FARMING)) {
				npc("Have fun with it.");
				stage = 15;
			}
			stage = 15;
			break;
		case 15:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MartinTheMasterGardener(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendOptions("Select an Option", "Skillcape of Farming", "Quest.");
		stage = 0;
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3299 };
	}
}
