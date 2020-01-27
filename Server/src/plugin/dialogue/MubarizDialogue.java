package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the mubariz dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class MubarizDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code MubarizDialogue {@code Object}.
	 */
	public MubarizDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MubarizDialogue} {@code Object}.
	 * @param player the player.
	 */
	public MubarizDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MubarizDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hi!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Welcome to the Duel Arena!");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Thanks! I need some information.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "What would you like to know?");
			stage = 3;
			break;
		case 3:
			interpreter.sendOptions("Information", "What is this place?", "How do I challenge someone to a duel?", "What kind of options are there?", "This place looks really old, where did it come from?");
			stage = 4;
			break;
		case 4:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What is this place?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How do I challenge someone to a duel?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What kind of options are there?");
				stage = 30;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "This place looks really old, where did it come from?");
				stage = 40;
				break;

			}
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "When you go to the arena you'll go up an access ramp", "to the walkways that overlook the duel arenas. From the", "walkways you can watch the duels and challenge other", "players.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You'll know you're in the right place as you'll have a", "Duel-with option when you right-click a player.");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm there!");
			stage = 23;
			break;
		case 23:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You and your opponent can offer items as a stake. If", "you win, you recieve what your opponent staked, but if", "you lose, your opponent will get whatever items you", "staked.");
			stage = 31;
			break;
		case 31:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You can choose to use rules to spice things up a bit.", "For instance if you both agree to use the 'No Magic'", "rule then neither player can use magic to attack the", "other player. The fight will be restricted to ranging and");
			stage = 32;
			break;
		case 32:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "melee only.");
			stage = 33;
			break;
		case 33:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The rules are fairly self-evident with lots of different", "combinations for you to try out!");
			stage = 34;
			break;
		case 34:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Cool! Thanks!");
			stage = 35;
			break;
		case 35:
			end();
			break;
		case 40:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Now that the archaeologists have moved out, a group of", "warriors, headed by myself, have bought the land and", "converted it to a set of duel arenas. The best fighters", "from around the world come here to fight!");
			stage = 41;
			break;
		case 41:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I challenge you!");
			stage = 42;
			break;
		case 42:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ho! Ho! Ho!");
			stage = 43;
			break;
		case 43:
			end();
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The Duel Arena has six duel arenas where you can", "fight other players in a controlled enviornment. We", "have our own dedicated hospital where we guarantee to", "put you back together, even if you lose.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Inbetween the arenas are walkways where you can", "watch the fights and challenge other players");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Sounds great. Thanks!");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "See you in the arenas!");
			stage = 15;
			break;
		case 15:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 957 };
	}
}
