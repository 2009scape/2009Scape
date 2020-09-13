package plugin.dialogue;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;
import plugin.quest.free.shieldofarrav.ShieldofArrav;

/**
 * Represents the dialogue plugin used for the baraek npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class BaraekDialogue extends DialoguePlugin {

	/**
	 * Represents the fur item.
	 */
	private static final Item FUR = new Item(6814);

	/**
	 * Represents the buy coins item.
	 */
	private static final Item BUY_COINS = new Item(995, 12);

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 20);

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code BaraekDialogue} {@code Object}.
	 */
	public BaraekDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code BaraekDialogue} {@code Object}.
	 * @param player the player.
	 */
	public BaraekDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new BaraekDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest(ShieldofArrav.NAME);
		switch (quest.getStage(player)) {
		case 30:
			if (!player.getInventory().containsItem(FUR)) {
				interpreter.sendOptions("Select an Option", "Can you tell me where I can find the Phoenix Gang?", "Can you sell me some furs?", "Hello. I am in search of a quest.");
				stage = 0;
			} else {
				interpreter.sendOptions("Select an Option", "Can you tell me where I can find the Phoenix Gang?", "Can you sell me some furs?", "Hello. I am in search of a quest.", "Would you like to buy my fur?");
				stage = 40;
			}
			break;
		default:
			if (player.getInventory().containsItem(FUR)) {
				options("Can you sell me some furs?", "Hello. I am in search of a quest.", "Would you like to buy my fur?");
				stage = 40;
			} else {
				options("Can you sell me some furs?", "Hello. I am in search of a quest.");
				stage = 0;
			}
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 40:
			switch (stage) {
			case 111:
				npc("Gang. they're operating there under the name of", "VTAM Corporation. Be careful. The Phoenixes ain't", "the type to be messed about.");
				stage = 112;
				break;
			case 112:
				player("Thanks!");
				stage = 113;
				break;
			case 113:
				end();
				break;
			case 120:
				npc("Heh. If you wanna deal with the Phoenix Gand they're", "involved in much worse than a bit of briber.");
				stage = 121;
				break;
			case 121:
				end();
				break;
			case 130:
				end();
				break;
			}
		case 30:
			switch (stage) {
			case 0:
				switch (buttonId) {
				case 1:
					player("Can you tell me where I can find the Phoenix Gang?");
					stage = 10;
					break;
				case 2:
					player("Can you sell me some furs?");
					stage = 20;
					break;
				case 3:
					player("Hello! I am in search of a quest.");
					stage = 30;
					break;
				}
				break;
			case 10:
				npc("Sh sh sh, not so loud! You don't want to get me in", "trouble!");
				stage = 11;
				break;
			case 11:
				player("So DO you know where they are?");
				stage = 12;
				break;
			case 12:
				npc("I may do.");
				stage = 13;
				break;
			case 13:
				npc("But I don't to get in trouble for revealing their", "hideout.");
				stage = 14;
				break;
			case 14:
				npc("Of course, if I was, say 20 gold coins richer I may", "happen to be more inclined to take that sort of risk...");
				stage = 15;
				break;
			case 15:
				options("Okay. Have 20 gold coins.", "No, I don't like things like bribery.", "Yes. I'd like to be 20 gold coins richer too.");
				stage = 16;
				break;
			case 16:
				switch (buttonId) {
				case 1:
					player("Okay. Have 20 gold coins.");
					stage = 110;
					break;
				case 2:
					player("No, I don't like things like bribery.");
					stage = 120;
					break;
				case 3:
					player("Yes. I'd like to be 20 gold coins richer too.");
					stage = 130;
					break;
				}
				break;
			case 110:
				if (!player.getInventory().containsItem(COINS)) {
					break;
				}
				if (player.getInventory().remove(COINS)) {
					quest.setStage(player, 40);
					npc("Ok, to get to the gang hideout, enter Varrock through", "the south gate. Then, if you take the first turning east", "somewhere along there is an alleway to the south. The", "door at the end of there is an entrance to the Phoenix");
					stage = 111;
				} else {
					player("I don't have enough coins.");
					stage = 121;
				}
				break;
			case 121:
				end();
				break;
			case 30:
				npc("Sorry kiddo, I'm a fur trader not a damsel in distress.");
				stage = 31;
				break;
			case 31:
				end();
				break;
			case 40:
				switch (buttonId) {
				case 1:
					player("Can you tell me where I can find the Phoenix Gang?");
					stage = 10;
					break;
				case 2:
					stage = 80;
					handleFurBuy(buttonId);
					break;
				case 3:
					player("Hello! I am in search of a quest.");
					stage = 30;
					break;
				case 4:
					player("Can you sell me some furs?");
					stage = 20;
					break;
				}
				break;
			default:
				handleFurBuy(buttonId);
				handleFurSelling(buttonId);
				break;
			}
			break;
		default:
			switch (stage) {
			case 0:
				switch (buttonId) {
				case 1:
					player("Can you sell me some furs?");
					stage = 20;
					break;
				case 2:
					player("Hello! I am in search of a quest.");
					stage = 30;
					break;
				}
				break;
			case 30:
				npc("Sorry kiddo, I'm a fur trader not a damsel in distress.");
				stage = 31;
				break;
			case 31:
				end();
				break;
			case 40:
				switch (buttonId) {
				case 1:
					player("Can you sell me some furs?");
					stage = 20;
					break;
				case 2:
					player("Hello! I am in search of a quest.");
					stage = 30;
					break;
				case 3:
					stage = 80;
					handleFurBuy(buttonId);
					break;
				}
				break;
			default:
				handleFurBuy(buttonId);
				handleFurSelling(buttonId);
				break;
			}
			break;
		}
		return true;
	}

	/**
	 * Method used to handle the fur selling.
	 * @param buttonId the button id.
	 */
	public void handleFurSelling(int buttonId) {
		switch (stage) {
		case 20:
			npc("Yeah, sure. They're 20 gold coins each.");
			stage = 21;
			break;
		case 21:
			options("Yeah, okay, here you go.", "20 gold coins? That's an outrade!");
			stage = 22;
			break;
		case 22:
			switch (buttonId) {
			case 1:
				player("Yeah, OK, here you go.");
				stage = 25;
				break;
			case 2:
				player("20 gold coins? That's an outrage!");
				stage = 23;
				break;
			}
			break;
		case 23:
			npc("Well, I can't go any cheaper than that mate. I have a", "family to feed.");
			stage = 24;
			break;
		case 24:
			end();
			break;
		case 25:
			if (player.getInventory().remove(COINS)) {
				if (!player.getInventory().add(FUR)) {
					GroundItemManager.create(FUR, player);
				}
				interpreter.sendItemMessage(FUR.getId(), "Baraeck sells you a fur.");
				stage = 26;
			} else {
				player("Oops, I dont' seem to have enough coins.");
				stage = 24;
			}
			break;
		case 26:
			end();
			break;
		}
	}

	/**
	 * Method used to handle the buying of fur.
	 * @param buttonId the buttonId.
	 */
	public void handleFurBuy(int buttonId) {
		switch (stage) {
		case 80:
			player("Would you like to buy my fur?");
			stage = 81;
			break;
		case 81:
			npc("Let's have a look at it.");
			stage = 82;
			break;
		case 82:
			interpreter.sendItemMessage(FUR.getId(), "You hand Baraeck your fur to look at.");
			stage = 83;
			break;
		case 83:
			npc("It's not in the best condition. I guess I could give you", "12 coins for it.");
			stage = 84;
			break;
		case 84:
			options("Yeah, that'll do.", "I think I'll keep hold of it actually.");
			stage = 85;
			break;
		case 85:
			switch (buttonId) {
			case 1:
				player("Yeah, that'll do.");
				stage = 89;
				break;
			case 2:
				player("I think I'll keep hold of it actually!");
				stage = 87;
				break;
			}
			break;
		case 87:
			npc("Oh ok. Didn't want it anyway!");
			stage = 88;
			break;
		case 88:
			end();
			break;
		case 89:
			if (player.getInventory().remove(FUR)) {
				if (!player.getInventory().add(BUY_COINS)) {
					GroundItemManager.create(BUY_COINS, player);
				}
				player("Thanks!");
				stage = 90;
			}
			break;
		case 90:
			end();
			break;
		}
	}

	@Override
	public int[] getIds() {
		return new int[] { 547 };
	}
}
