package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.skill.member.construction.HouseLocation;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.GameWorld;

/**
 * Represents the estate agent dialogue.
 * @author 'Vexia
 * @version 1.0
 */
public final class EstateAgentDialogue extends DialoguePlugin {

	/**
	 * Represents the book item.
	 */
	private static final Item BOOK = new Item(8463, 1);

	/**
	 * Constructs a new {@code EstateAgentDialogue} {@code Object}.
	 */
	public EstateAgentDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code EstateAgentDialogue} {@code Object}.
	 * @param player the player.
	 */
	public EstateAgentDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new EstateAgentDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello. Welcome to the " + GameWorld.getName() + " Housing Agency! What", "can I do for you?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			if (player.getHouseManager().hasHouse()) {
				interpreter.sendOptions("Select an Option", "Can you move my house please?", "Can you redecorate my house please?", "Could I have a Construction guidebook?", "Tell me about houses", "Tell me about that skillcape you're wearing.");
				stage = 1;
			} else {
				interpreter.sendOptions("Select an Option", "How can I get a house?", "Tell me about houses.");
				stage = 2;
			}
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can you move my house please?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can you redecorate my house please?");
				stage = 30;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Could I have a Construction guidebook?");
				stage = 60;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Tell me about houses!");
				stage = 90;
				break;
			case 5:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Tell me about that skillcape you're wearing!");
				stage = 100;
				break;
			}
			break;
		case 2:
			switch (buttonId) {
			case 1:
				player("How can I get a house?");
				stage = 3;
				break;
			case 2:
				player("Tell me about houses.");
				stage = 90;
				break;
			}
			break;
		case 3:
			npc("I can sell you a starting house in Rimmington for", "1000 coins. As you increase your construction skill you", "will be able to have your house moved to other areas", "and redecorated in other styles.");
			stage = 4;
			break;
		case 4:
			npc("Do you want to buy a starter house?");
			stage = 5;
			break;
		case 5:
			options("Yes please!", "No thanks.");
			stage = 6;
			break;
		case 6:
			switch (buttonId) {
			case 1:
				player("Yes please!");
				stage = 7;
				break;
			case 2:
				player("No thanks.");
				stage = 150;
				break;
			}
			break;
		case 7:
			if (GameWorld.getSettings().isDevMode() && player.getInventory().remove(new Item(995, 1000))) {
				player.getHouseManager().create(HouseLocation.RIMMINGTON);
				npc("Thank you. Go through the Rimmington house portal", "and you will find your house ready for you to start", "building in it.");
				stage = 8;
			} else {
				npc("You don't have enough money to buy a house,", "come back when you can afford one.");
				stage = 150;
			}
			break;
		case 8:
			npc("This book will help you to start building your house.");
			player.getInventory().add(BOOK);
			stage = 150;
			break;
		case 60:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Certainly.");
			player.getInventory().add(BOOK);
			stage = 150;
			break;
		case 90:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "It all came out of the wizards' experiments. They found", "a way to fold space, so that they could pack many", "acres of land into an area only a foot across.");
			stage = 91;
			break;
		case 91:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "They created several folded-space regions across", "" + GameWorld.getName() + ". Each one contains hundreds of small plots", "where people can build houses.");
			stage = 92;
			break;
		case 92:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ah, so that's how everyone can have a house without", "them cluttering up the world!");
			stage = 93;
			break;
		case 93:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Quite. The wizards didn't want to get bogged down", "in the business side of things so they ", "hired me to sell the houses.");
			stage = 94;
			break;
		case 94:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "There are various other people across " + GameWorld.getName() + " who can", "help you furnish your house. You should start buying", "planks from the sawmill operator in Varrock.");
			stage = 150;
			break;
		case 100:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "As you may know, skillcapes are only available to masters", "in a skill. I have spent my entire life building houses and", "now I spend my time selling them! As a sign of my abilites", "I wear this Skillcape of Construction. If you ever have");
			stage = 101;
			break;
		case 101:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "enough skill to build a demonic throne, come and talk to", "me and I'll sell you a skillcape like mine.");
			stage = 150;
			break;
		case 150:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4247 };
	}
}