package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.travel.ship.Ships;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the SquireDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class SquireDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code SquireDialogue.java} {@code Object}.
	 */
	public SquireDialogue() {
		/**
		 * empty.
		 */
	}

	public SquireDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SquireDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		if (args.length == 3) {
			int type = (int) args[1];
			if (type == 0) {// lost
				interpreter.sendDialogues(3781, FacialExpression.HALF_GUILTY, "The Void Knight was killed, another of our Order has", "fallen and that Island is lost.");
				stage = 110;
			} else if (type == 1) {// won and awarded.
				String points = (String) args[2];
				interpreter.sendDialogues(3781, FacialExpression.HALF_GUILTY, "Congratulations! You managed to destroy all the portals!", "We've awarded you " + points + " Void Knight Commendation", "points. Please also accept these coins as a reward.");
				stage = 100;
			} else {// won and not awarded.
				interpreter.sendDialogues(3781, FacialExpression.HALF_GUILTY, "Congratulations! You managed to destroy all the portals!", "However, you did not succeed in reaching the required", "amount of damage delt we cannot grant you a reward.");
				stage = 101;
			}
			return true;
		}
		npc = (NPC) args[0];
		if (args.length == 2) {
			npc("Be quick, we're under attack!");
			stage = 699;
			return true;
		}
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hi, how can I help you?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			if (npc.getId() == 3781) {
				interpreter.sendOptions("Select an Option", "I'd like to go back to Port Sarim please.", "I'm fine thanks.");
				stage = 500;
				return true;
			}
			interpreter.sendOptions("Select an Option", "Who are you?", "Where does this ship go?", "I'd like to go to your outpost.", "I'm fine thanks.");
			stage = 1;
			break;
		case 500:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "I'd like to go back to Port Sarim please.");
				stage = 502;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "I'm fine thanks.");
				stage = 501;
				break;
			}
			break;
		case 501:
			end();
			break;
		case 502:
			interpreter.sendDialogues(npc, null, "Ok, but please come back soon and help us.");
			stage = 503;
			break;
		case 503:
			end();
			Ships.PEST_TO_PORT_SARIM.sail(player);
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Who are you?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Where does this ship go?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'd like to go to your outpost.");
				stage = 30;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm fine thanks.");
				stage = 40;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'm a Squire for the Void Knights.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "The who?");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The Void Knights, they are great warriors of balance", "who do Guthix's work here in Gielinor.");
			stage = 13;
			break;
		case 13:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "To the Void Knight outpost. It's a small island just off", "Karamja.");
			stage = 21;
			break;
		case 21:
			interpreter.sendOptions("Select an Option", "I'd like to go to your outpost.", "That's nice.");
			stage = 22;
			break;
		case 22:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'd like to go to your outpost.");
				stage = 23;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "That's nice.");
				stage = 200;
				break;
			}
			break;
		case 23:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Certainly, right this way.");
			stage = 24;
			break;
		case 24:
			end();
			Ships.PORT_SARIM_TO_PEST_CONTROL.sail(player);
			break;
		case 200:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Certainly, right this way.");
			stage = 24;
			break;
		case 40:
			end();
			break;
		case 699:
			options("What's going on?", "How do I repair things?", "I want to leave.", "I'd better get back to it then.");
			stage = 700;
			break;
		case 700:// ingame
			switch (buttonId) {
			case 1:
				player("What's going on?");
				stage = 710;
				break;
			case 2:
				player("How do I repair things?");
				stage = 720;
				break;
			case 3:
				player("I want to leave.");
				stage = 730;
				break;
			case 4:
				player("I'd better get back to it then.");
				stage = 740;
				break;
			}
			break;
		case 710:
			npc("This island is being invaded by outsiders and the Void", "Knight over there is using a ritual to unsummon their", "portals. We must defend the Void Knight at all costs.", "however if you get an opening you can destroy the portals.");
			stage = 711;
			break;
		case 711:
			end();
			break;
		case 720:
			npc("There are trees on the island. You'll need to chop them", "down for logs and use a hammer to repair the defences.", "Be careful tough, the trees here don't grow back very", "fast so your resources are limited!");
			stage = 721;
			break;
		case 721:
			end();
			break;
		case 730:
			end();
			break;
		case 740:
			end();
			break;
		case 100:
			String RED = "<col=8A0808>";
			String BLUE = "<col=08088A>";
			interpreter.sendDialogue(BLUE + "You now have " + RED + "" + player.getSavedData().getActivityData().getPestPoints() + "" + BLUE + " Void Knight Commendation points!</col>", "You can speak to a Void Knight to exchange your points for", "rewards.");
			stage = 101;
			break;
		case 101:
			end();
			break;
		case 110:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3781, 3790 };
	}
}
