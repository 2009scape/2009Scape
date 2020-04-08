package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represnets the dialogue used to handle the Leela npc.
 * @author 'Vexia
 * @date 1/14/1
 */
@InitializablePlugin
public final class LeelaDialogue extends DialoguePlugin {

	/**
	 * Represents the rope item.
	 */
	private static final Item ROPE = new Item(954);

	/**
	 * Represents the pink skirt item.
	 */
	private static final Item SKIRT = new Item(1013);

	/**
	 * Represents the yellow wig item.
	 */
	private static final Item YELLOW_WIG = new Item(2419);

	/**
	 * Represents the skin paste item.
	 */
	private static final Item PASTE = new Item(2424);

	/**
	 * Represents the bronze key item.
	 */
	private static final Item BRONZE_KEY = new Item(2418);

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Represents the count of materials you have gathered.
	 */
	private int itemCount;

	/**
	 * Constructs a new {@code LeelaDialogue} {@code Object}.
	 */
	public LeelaDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code LeelaDialogue} {@code Object}.
	 * @param player the player.
	 */
	public LeelaDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LeelaDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Prince Ali Rescue");
		switch (quest.getStage(player)) {
		case 60:
		case 100:
			interpreter.sendDialogues(npc, null, "Thank you, Al-Kharid will forever owe you for your", "help. I think that if there is every anything that needs to", "be done, you will be someone they can rely on.");
			stage = 0;
			break;
		case 50:
		case 40:
			if (!player.getInventory().containsItem(BRONZE_KEY) && !player.getBank().containsItem(BRONZE_KEY)) {
				interpreter.sendDialogues(player, null, "I'm afraid I lost that key you gave me.");
				stage = 0;
			} else {
				interpreter.sendDialogues(npc, null, "Ok now, you have all the basic equipment. What are", "your plans to stop the guard interfering?");
				stage = 10;
			}
			break;
		case 30:
			interpreter.sendDialogues(npc, null, "My father sent this key for you.", "Be careful not to lose it.");
			stage = 0;
			break;
		case 20:
			interpreter.sendDialogues(player, null, "I am here to help you free the prince.");
			stage = 0;
			break;
		default:
			interpreter.sendDialogues(player, null, "Hi!");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 60:
		case 100:
			end();
			break;
		case 50:
		case 40:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(npc, null, "Foolish man!", "A new key will cost 15 coins.");
				stage = 1;
				break;
			case 1:
				if (player.getInventory().contains(995, 15)) {
					interpreter.sendDialogues(player, null, "Here, I have 15 coins.");
					stage = 2;
				} else {
					end();
					player.getPacketDispatch().sendMessage("You don't have enough coins.");
				}
				break;
			case 2:
				Item remove = new Item(995, 15);
				if (!player.getInventory().containsItem(remove)) {
					end();
					return true;
				}
				if (player.getInventory().remove(remove)) {
					if (!player.getInventory().add(BRONZE_KEY)) {
						GroundItemManager.create(BRONZE_KEY, player);
					}
					end();
					player.getPacketDispatch().sendMessage("Leela gives you another key.");
				}
				stage = 99;
				break;
			case 99:
				end();
				break;
			case 10:
				interpreter.sendOptions("Select an Option", "I haven't spoken to him yet.", "I was going to attack him.", "I hoped to get him drunk.", "Maybe I could bribe him to leave.");
				stage = 11;
				break;
			case 11:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, null, "I haven't spoken to him yet.");
					stage = 110;
					break;
				case 2:
					interpreter.sendDialogues(player, null, "I was going to attack him.");
					stage = 120;
					break;
				case 3:
					interpreter.sendDialogues(player, null, "I hoped to get him drunk.");
					stage = 130;
					break;
				case 4:
					interpreter.sendDialogues(player, null, "Maybe I could bribe him to leave.");
					stage = 140;
					break;
				}
				break;
			case 110:
				interpreter.sendDialogues(npc, null, "Well, speaking to him may find a weakness he has. See", "if there's something that could stop him bothering us.");
				stage = 111;
				break;
			case 111:
				end();
				break;
			case 120:
				interpreter.sendDialogues(npc, null, "I don't thik you should. If you do the rest of the", "gang and Keli would attack you. The door guard", "should be removed first, to make it easy.");
				stage = 121;
				break;
			case 121:
				end();
				break;
			case 130:
				interpreter.sendDialogues(npc, null, "Well, that's possible. These guards do like a drink. I", "would think it will take at least 3 beers to do it well.", "You would probably have to do it all at the same time", "too. The effects of the local bear off quickly.");
				stage = 131;
				break;
			case 131:
				end();
				break;
			case 140:
				interpreter.sendDialogues(npc, null, "You could try. I don't think the emir will pay anything", "towards it. And we did bribe on of their guards once.");
				stage = 141;
				break;
			case 141:
				end();
				break;
			}
			break;
		case 30:
			switch (stage) {
			case 0:
				interpreter.sendDialogue("Leela gives you a copy of the key to the prince's door.");
				stage = 1;
				break;
			case 1:
				if (!player.getInventory().add(BRONZE_KEY)) {
					GroundItemManager.create(BRONZE_KEY, player);
				}
				quest.setStage(player, 40);
				interpreter.sendDialogues(npc, null, "Good, you have all the basic equipment. Next to deal", "with the guard on the door. he is talkative, try to find", "a weakness in him.");
				stage = 2;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 20:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(npc, null, "Your employment is known to me. Now, do you know", "all that we need to make the break?");
				stage = 1;
				break;
			case 1:
				interpreter.sendOptions("Select an Option", "I must make a disguise. What do you suggest?", "I need to get the key made.", "What can I do with the guards?", "I will go and get the rest of the escape equipment.");
				stage = 2;
				break;
			case 2:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, null, "I must a make a disguise. What do you suggest?");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, null, "I need to get the key made.");
					stage = 20;
					break;
				case 3:
					interpreter.sendDialogues(player, null, "What can I do with the guards?");
					stage = 30;
					break;
				case 4:
					interpreter.sendDialogues(player, null, "I will go and get the rest of the escape equipment.");
					stage = 40;
					break;
				}
				break;
			case 10:
				interpreter.sendDialogues(npc, null, "Only the lady Keli, can wander about outside the jail.", "The guards will shoot to kill if they see the prince out", "so we need a disguise good enough to fool them at a", "distance.");
				stage = 11;
				break;
			case 11:
				if (player.getInventory().containsItem(YELLOW_WIG)) {
					interpreter.sendDialogues(npc, null, "The wig you have got, well done.");
					itemCount++;
				} else {
					interpreter.sendDialogues(npc, null, "You need a wig, maybe made from wool. If you find", "someone who can work with wool ask them about it.", "There's a witch nearby may be able to help you dye it.");
				}
				stage = 12;
				break;
			case 12:
				if (player.getInventory().containsItem(SKIRT)) {
					interpreter.sendDialogues(npc, null, "You have got the skirt, good.");
					itemCount++;
				} else {
					interpreter.sendDialogues(npc, null, "You will need to get a pink skirt, same as Keli's.");
				}
				stage = 13;
				break;
			case 13:
				if (player.getInventory().containsItem(PASTE)) {
					interpreter.sendDialogues(npc, null, "You have the skin paint, well done. I thought you would", "struggle to make that.");
					itemCount++;
				} else {
					interpreter.sendDialogues(npc, null, "We still need something to colour the Prince's skin", "lighter. There's a witch close to here. She knows about", "many things. She may know some way to make the", "skin lighter.");
				}
				stage = itemCount == 3 ? 14 : 15;
				break;
			case 14:
				interpreter.sendDialogues(npc, null, "You do have everything for the disguise.");
				stage = 15;
				break;
			case 15:
				if (player.getInventory().containsItem(ROPE)) {
					interpreter.sendDialogues(npc, null, "You have the rope I see, to tie up Keli. That will be the", "most dangerous part of the plan.");
					stage = 16;
				} else {
					interpreter.sendDialogues(npc, null, "You will still need some rope to tie up Keli, of course. I", "heard that there's a good rope maker around here.");
					stage = 16;
				}
				break;
			case 16:
				end();
				break;
			case 20:
				interpreter.sendDialogues(npc, null, "Yes, that is most important. There is no way you can", "get the real key. It is on a chain around Keli's neck.", "Almost impossible to steal.");
				stage = 21;
				break;
			case 21:
				interpreter.sendDialogues(npc, null, "Get some soft clay and get her to show you the key", "somehow. Then take the print, with bronze, to my", "father.");
				stage = 22;
				break;
			case 22:
				end();
				break;
			case 30:
				interpreter.sendDialogues(npc, null, "most of the guards will be easy. The disguise will get", "past them. The only guards who will be a problem will be", "the one at the door.");
				stage = 31;
				break;
			case 31:
				end();
				break;
			case 40:
				interpreter.sendDialogues(npc, null, "Good, I shall await your return with everything.");
				stage = 41;
				break;
			case 41:
				end();
				break;
			}
			break;
		default:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(npc, null, "Please leave me alone adventurer, I am", "a very busy woman.");
				stage = 1;
				break;
			case 1:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 915 };
	}
}
