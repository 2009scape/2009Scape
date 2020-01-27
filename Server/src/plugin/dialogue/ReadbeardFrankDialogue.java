package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue to handle rebeard frank.
 * @author 'Vexia
 * @date 28/12/2013
 */
@InitializablePlugin
public class ReadbeardFrankDialogue extends DialoguePlugin {

	/**
	 * Represents the karamjan rum item.
	 */
	private static final Item KARAMJAN_RUM = new Item(431);

	/**
	 * Represents the chest key item.
	 */
	private static final Item KEY = new Item(432);

	/**
	 * Represents this quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code ReadbeardFrankDialogue} {@code Object}.
	 */
	public ReadbeardFrankDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ReadbeardFrankDialogue} {@code Object}.
	 * @param player the player.
	 */
	public ReadbeardFrankDialogue(Player player) {
		super(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Pirate's Treasure");
		switch (quest.getStage(player)) {
		default:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Arr, Matey!");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 20:
			switch (stage) {
			case 0:
				if (quest.getStage(player) == 20 && !player.getInventory().containsItem(KEY) && !player.getBank().containsItem(KEY)) {
					interpreter.sendDialogues(player, null, "I seem to have lost my chest key...");
					stage = 700;
					return true;
				}
				interpreter.sendOptions("Select an Option", "Arr!", "Do you have anything for trade?");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Arr!");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Do you have anything for trade?");
					stage = 20;
					break;
				}

				break;
			case 10:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Arr!");
				stage = 11;
				break;
			case 11:
				interpreter.sendOptions("Select an Option", "Arr!", "Do you have anything for trade?");
				stage = 1;
				break;
			case 20:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Nothin' at the moment, but then again the Customs", "Agents are on the warpath right now.");
				stage = 21;
				break;
			case 21:
				end();
				break;
			case 700:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Arr, silly you. Fortunately I took the precaution to have", "another one made.");
				stage = 701;
				break;
			case 701:
				interpreter.sendItemMessage(KEY.getId(), "Frank hands you a chest key.");
				stage = 702;
				break;
			case 702:
				end();
				if (!player.getInventory().add(KEY)) {
					GroundItemManager.create(KEY, player);
				}
				break;
			case 14:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "This be Hector's key. I believe it opens his chest in his", "old room in the Blue Moon Inn in Varrock.");
				stage = 15;
				break;
			case 15:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "With any luck his treasure will be in there.");
				stage = 16;
				break;
			case 16:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ok thanks, I'll go and get it.");
				stage = 17;
				break;
			case 17:
				end();
				break;
			}
			break;
		case 10:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Have ye brought some rum for yer ol' mate Frank?");
				stage = 1;
				break;
			case 1:
				if (!player.getInventory().containsItem(KARAMJAN_RUM)) {
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, not yet.");
					stage = 2;
				} else {
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, I've got some.");
					stage = 10;
				}
				break;
			case 17:
				end();
				break;
			case 2:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Not suprising, tis no easy task to get it off Karamja.");
				stage = 3;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What do you mean?");
				stage = 4;
				break;
			case 4:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The Customs office has been clampin' down on the", "export of spirits. You seem like a resourceful young lad,", "I'm sure ye'll be able to find a way to slip the stuff past", "them.");
				stage = 5;
				break;
			case 5:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Well I'll give it another shot.");
				stage = 6;
				break;
			case 6:
				end();
				break;
			case 10:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Now a deal's a deal, I'll tell ye about the treasure. I", "used to serve under a pirate captain called One-Eyed", "Hector.");
				stage = 11;
				break;
			case 11:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hector were very successful and became very rich.", "But about a year ago we were boarded by the Customs", "and Excise Agents.");
				stage = 12;
				break;
			case 12:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hector were killed along with many of the crew, I were", "one of the few to escape and I escaped with this.");
				stage = 13;
				break;
			case 13:
				if (player.getInventory().remove(KARAMJAN_RUM) && player.getInventory().add(KEY)) {
					quest.setStage(player, 20);
					interpreter.sendItemMessage(KEY.getId(), "Frank happily takes the rum... ... and hands you a key");
					stage = 14;
				} else {
					end();
					player.getPacketDispatch().sendMessage("Not enough inventory space.");
				}
				break;
			}
			break;
		case 0:
			switch (stage) {
			case 0:
				interpreter.sendOptions("Select an Option", "I'm in search of treasure.", "Arr!", "Do you have anything for trade?");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, null, "I'm in search of treasure.");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, null, "Arr!");
					stage = 20;
					break;
				case 3:
					interpreter.sendDialogues(player, null, "Do you have anything for trade?");
					stage = 30;
					break;
				}
				break;
			case 10:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Arr, treasure you be after eh? Well I might be able to", "tell you where to find some... For a price...");
				stage = 11;
				break;
			case 11:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What sort of price?");
				stage = 12;
				break;
			case 12:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well for example if you go and get me a bottle of rum...", "Not just any rum mind...");
				stage = 13;
				break;
			case 13:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'd like some rum made on Karamja Island. There's no", "rum like Karamaja Rum!");
				stage = 14;
				break;
			case 14:
				interpreter.sendOptions("Select an Option", "Ok, I will bring you some rum", "Not right now");
				stage = 15;
				break;
			case 15:
				switch (buttonId) {
				case 1:
					quest.start(player);
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ok, I will bring you some rum.");
					stage = 17;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Not right now.");
					stage = 16;
					break;
				}
				break;
			case 17:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yer a saint, although it'll take a miracle to get it off", "Karamja.");
				stage = 18;
				break;
			case 18:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What do you mean?");
				stage = 19;
				break;
			case 19:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The Customs office has been clampin' down on the", "export of spirits. You seem like a resourceful young lad,", "I'm sure ye'll be able to find a way to slip the stuff past", "them.");
				stage = 21;
				break;
			case 21:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Well I'll give it a shot.");
				stage = 22;
				break;
			case 22:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Arr, that's the spirit!");
				stage = 23;
				break;
			case 23:
				end();
				break;
			case 16:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Fair enough. I'll still be here and thirsty whenever you", "feel like helpin' out.");
				stage = 31;
				break;
			case 20:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Arr!");
				stage = 31;
				break;
			case 30:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Nothin' at the moment, but then again the Customs", "Agents are on the warpath right now.");
				stage = 31;
				break;
			case 31:
				end();
				break;
			}
			break;
		default:
			switch (stage) {
			case 0:
				if (quest.getStage(player) == 20 && !player.getInventory().containsItem(KEY) && !player.getBank().containsItem(KEY)) {
					interpreter.sendDialogues(player, null, "I seem to have lost my chest key...");
					stage = 700;
					return true;
				}
				interpreter.sendOptions("Select an Option", "Arr!", "Do you have anything for trade?");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Arr!");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Do you have anything for trade?");
					stage = 20;
					break;
				}

				break;
			case 10:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Arr!");
				stage = 11;
				break;
			case 11:
				interpreter.sendOptions("Select an Option", "Arr!", "Do you have anything for trade?");
				stage = 1;
				break;
			case 20:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Nothin' at the moment, but then again the Customs", "Agents are on the warpath right now.");
				stage = 21;
				break;
			case 21:
				end();
				break;
			case 700:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Arr, silly you. Fortunately I took the precaution to have", "another one made.");
				stage = 701;
				break;
			case 701:
				interpreter.sendItemMessage(KEY.getId(), "Frank hands you a chest key.");
				stage = 702;
				break;
			case 702:
				end();
				if (!player.getInventory().add(KEY)) {
					GroundItemManager.create(KEY, player);
				}
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ReadbeardFrankDialogue(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 375 };
	}

}
