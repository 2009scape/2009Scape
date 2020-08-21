package plugin.quest.free.dragonslayer;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;

/**
 * Represents the dialogue used to handle the wormbrain npc related to the
 * dragon slayer.
 * @author 'Vexia
 * @version 1.0
 */
public final class WormbrainDialogue extends DialoguePlugin {

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 10000);

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code WormbrainDialogue} {@code Object}.
	 */
	public WormbrainDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code WormbrainDialogue} {@code Object}.
	 * @param player the player.
	 */
	public WormbrainDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new WormbrainDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Dragon Slayer");
		switch (quest.getStage(player)) {
		default:
			npc("Whut you want?");
			stage = -1;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 20:
			switch (stage) {
			case -1:
				if (!player.getInventory().containsItem(DragonSlayer.WORMBRAIN_PIECE) && !player.getBank().containsItem(DragonSlayer.WORMBRAIN_PIECE)) {
					player("I believe you've got a piece of a map that I need.");
					stage = 500;
				} else {
					defaultDial(buttonId);
				}
				break;
			case 500:
				npc("So? Why should I be giving it to you? What you do", "for Wormbrain?");
				stage = 501;
				break;
			case 501:
				player("I suppose I could pay you for the map piece. Say, 500", "coins?");
				stage = 502;
				break;
			case 502:
				npc("Me not stoopid, it worth at least 10,000 coins!");
				stage = 503;
				break;
			case 503:
				options("You must be joking! Forget it.", "Alright then, 10,000 it is.");
				stage = 504;
				break;
			case 504:
				switch (buttonId) {
				case 1:
					player("You must be joking! Forget it.");
					stage = 505;
					break;
				case 2:
					player("Alrigt, then, 10,000 it is.");
					stage = 506;
					break;
				}
				break;
			case 505:
				end();
				break;
			case 506:
				if (player.getInventory().containsItem(COINS) && player.getInventory().remove(COINS)) {
					if (!player.getInventory().add(DragonSlayer.WORMBRAIN_PIECE)) {
						GroundItemManager.create(DragonSlayer.WORMBRAIN_PIECE, player);
					}
					interpreter.sendItemMessage(DragonSlayer.WORMBRAIN_PIECE.getId(), "You buy the map piece from Wormbrain.");
					stage = 507;
				} else {
					end();
					player.getPacketDispatch().sendMessage("You don't have enough coins in order to do that.");
				}
				break;
			case 507:
				npc("Fank you very much! Now me can bribe da guards,", "hehehe.");
				stage = 508;
				break;
			case 508:
				end();
				break;
			}
		default:
			defaultDial(buttonId);
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 745 };
	}

	/**
	 * Method used to handle the default dial.
	 * @param buttonId the button id.
	 */
	public void defaultDial(int buttonId) {
		switch (stage) {
		case -1:
			options("What are you in for?", "Sorry, thought this was a zoo.");
			stage = 0;
			break;
		case 0:
			switch (buttonId) {
			case 1:
				player("What are you in for?");
				stage = 10;
				break;
			case 2:
				player("Sorry, thought this was a zoo.");
				stage = 20;
				break;
			}
			break;
		case 10:
			npc("Me not sure. Me pick some stuff up and take it away.");
			stage = 11;
			break;
		case 11:
			player("Well, did the stuff belong to you?");
			stage = 12;
			break;
		case 12:
			npc("Umm...no.");
			stage = 13;
			break;
		case 13:
			player("Well, that would be why then.");
			stage = 14;
			break;
		case 14:
			npc("Oh, right.");
			stage = 15;
			break;
		case 15:
			end();
			break;
		case 20:
			end();
			break;
		}
	}
}