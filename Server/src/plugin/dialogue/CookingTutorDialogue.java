package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Rerpresents the dialogue plugin used for the cooking tutor.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class CookingTutorDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code CookingTutorDialogue} {@code Object}.
	 */
	public CookingTutorDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code CookingTutorDialogue} {@code Object}.
	 * @param player the player.
	 */
	public CookingTutorDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CookingTutorDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendOptions("Select an Option", "Can you teach me the basics of cooking please?", "Tell me about different food I can make.", "Goodbye.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can you teach me the basics of cooking please?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Tell me about different food I can make.");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Goodbye.");
				stage = 30;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The simplest thing to cook is raw meat or fish.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Fish can be caught, speak to the fishing tutor south of", "here in the swamp. Killing cows or chickens will yield", "raw meat to cook too.");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You can use your own fire... but it's not as effective", "and you'll burn more. To build a fire use a tinderbox", "on logs.");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Once you've found your range, click on your raw", "meat or fish in your inventory, then click on the", "cooking range. This will bring up an interface which", "you can right-click on to select the number to cook.");
			stage = 14;
			break;
		case 14:
			interpreter.sendOptions("Select an Option", "Can you teach me the basics of cooking please?", "Tell me about different food I can make.", "Goodbye.");
			stage = 0;
			break;
		case 20:
			interpreter.sendOptions("Select an Option", "Fish and Meat", "Brewing", "Vegetables", "Pie", "Go back to teaching");
			stage = 21;
			break;
		case 21:
			switch (buttonId) {
			case 1:
				interpreter.sendDoubleItemMessage(2132, 2142, "Fish and meat of most varieties can be cooked very simply on either a fire or range, experiment which one works for you.");
				stage = 20;
				break;
			case 2:
				interpreter.sendDoubleItemMessage(1911, 1905, "You can brew your own beers using the fermenting vats in either Keldagrim or Port Phasmatys. Use two buckets of water, two handfuls of barley malt, 4 hops of your choice and a pot of ale yeast, then leave to");
				stage = 220;
				break;
			case 3:
				interpreter.sendDoubleItemMessage(1942, 7058, "Baked potatoes are excellent foods and they are healthy too! You'll need to churn some butter and cheese. Look for the churn icon on the mini map. There is a churn in the farm building northwest of Lumbridge.");
				stage = 230;
				break;
			case 4:
				interpreter.sendDoubleItemMessage(1929, 1933, "Use a pot of flour with a bucket of water. You will then get an option to make bread dough, pitta bread dough, pastry dough, or pizza dough. Select pizza or pastry dough.");
				stage = 240;
				break;
			case 5:
				interpreter.sendOptions("Select an Option", "Can you teach me the basics of cooking please?", "Tell me about different food I can make.", "Goodbye.");
				stage = 0;
				break;
			}
			break;
		case 220:
			interpreter.sendDoubleItemMessage(1911, 1905, "ferment for a few days!");
			stage = 221;
			break;
		case 221:
			interpreter.sendDoubleItemMessage(1911, 1905, "Simply turn the tap and let the ale flow into the barrel next to the vat, then use empty beer glasses on the barrel.");
			stage = 20;
			break;
		case 230:
			interpreter.sendDoubleItemMessage(6697, 1985, "The nearest dairy cow is noth of the nearby water mill. Take buckets of milk with you to the churn and use the milk on the churn to make butter or cheese.");
			stage = 20;
			break;
		case 240:
			interpreter.sendDoubleItemMessage(2313, 1953, "Use the pastry dough with a pie dish then add your filling such as apple or red berries.");
			stage = 241;
			break;
		case 241:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Finally cook your pie by using the unbaked pie on a", "cooking range. Mmmm...pie.");
			stage = 242;
			break;
		case 242:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "There's pizza too! Find yourself some tomato and", "cheese, use on the Pizza dough. Cook the pizza on a", "range then add any other topping you want, such as", "anchovies.");
			stage = 20;
			break;
		case 30:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4899 };
	}
}
