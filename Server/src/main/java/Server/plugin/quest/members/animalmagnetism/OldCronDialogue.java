package plugin.quest.members.animalmagnetism;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import plugin.quest.free.therestlessghost.RestlessGhost;

/**
 * Handles the dialogue used for the old crone.
 * @author Vexia
 */
public final class OldCronDialogue extends DialoguePlugin {
	
	/**
	 * The crone made amulet item.
	 */
	public static final Item CRONE_AMULET = new Item(10500);

	/**
	 * The quest used.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code OldCronDialogue} {@code Object}.
	 */
	public OldCronDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code OldCronDialogue} {@code Object}.
	 * @param player the player.
	 */
	public OldCronDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new OldCronDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Animal Magnetism");
		switch (quest.getStage(player)) {
		case 16:
		case 17:
		case 18:
			player("I'm here about the farmers east of here.");
			break;
		default:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello, old woman.");
			stage = 1;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 18:
			switch (stage) {
			case 0:
				if (!player.hasItem(CRONE_AMULET)) {
					player("Would you be able to replace the amulet you made? I", "seem to have lost it.");
					stage = 4;
					break;
				}
				npc("Yes?");
				stage++;
				break;
			case 1:
				player("Well, to tell the truth, I just came back to chat with", "you. Any news?");
				stage++;
				break;
			case 2:
				npc("Disgraceful! Deliver that amulet; a young lady's", "happiness depends upon it.");
				stage++;
				break;
			case 3:
				end();
				break;
			case 4:
				npc("Here you are; luckily, I saved some of Alice's hair in", "case you were careless. Which you were.");
				stage++;
				break;
			case 5:
				if (!player.getInventory().hasSpaceFor(RestlessGhost.AMULET)) {
					npc("You don't have enough space in your inventory.");
					stage = 3;
					break;
				}
				if (!player.getInventory().containsItem(RestlessGhost.AMULET) && !player.getEquipment().containsItem(RestlessGhost.AMULET)) {
					npc("You need to bring me a ghostspeak amulet.");
					stage = 3;
					break;
				}
				player.getInventory().add(CRONE_AMULET);
				end();
				break;
			}
			break;
		case 17:
			switch (stage) {
			case 0:
				player("I'm here to see if you are ready to do your mystical", "stuff with my ghostspeak amulet.");
				stage++;
				break;
			case 1:
				if (!player.getInventory().hasSpaceFor(RestlessGhost.AMULET)) {
					npc("I most certainly am, but you don't have enough", "space in your backpack.");
					stage++;
					break;
				}
				if (!player.getInventory().containsItem(RestlessGhost.AMULET) && !player.getEquipment().containsItem(RestlessGhost.AMULET)) {
					npc("I most certainly am, but you don't have an ghostspeak", "amulet.");
					stage++;
				} else {
					npc("I most certainly am; there you go.");
					stage += 2;
				}
				break;
			case 2:
				end();
				break;
			case 3:
				player("Wow, that was quick and painless.");
				stage++;
				break;
			case 4:
				npc("Just being a good neighbour.");
				stage++;
				break;
			case 5:
				player.getInventory().add(CRONE_AMULET);
				quest.setStage(player, 18);
				end();
				break;
			}
			break;
		case 16:
			switch (stage) {
			case 0:
				player("Alice and her husband are having trouble talking to one", "another and said you might be able to help.");
				stage++;
				break;
			case 1:
				npc("Ah, I know them; shame about those cows. Why would", "they think that I could help?");
				stage++;
				break;
			case 2:
				player("Alice seems to think you could alter a ghostspeak amulet", "in order to allow them to communicate.");
				stage++;
				break;
			case 3:
				npc("Well, the poor young lady has such family problems; I", "quite feel her pain. I'd be happy to help.");
				stage++;
				break;
			case 4:
				npc("You seem to have one of her golden hairs on your", "shoulder, so I can use that...");
				stage++;
				break;
			case 5:
				interpreter.sendDialogue("In a flash, the crone whisks away an unseen hair from your", "shoulder.");
				stage++;
				break;
			case 6:
				npc("Talk to me again with a ghostspeak amulet and some", "space in your backpack and I'll be ready to work on", "the little good deed. The way I plan is quite simple,", "really.");
				stage++;
				break;
			case 7:
				npc("I can mirror part of the unused mystical essence of the", "ghostspeak amulet, bind it with Alice's hair and thus", "create a second amulet.");
				stage++;
				break;
			case 8:
				npc("The second amulet will be useful for the purpose you", "desire, thought it won't work for any other ghost or", "human other than the farmer and his wife.");
				stage++;
				break;
			case 9:
				quest.setStage(player, 17);
				end();
				break;
			}
			break;
		default:
			switch (stage) {
			case 1:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I lived here when this was all just fields, you know.");
				stage = 2;
				break;
			case 2:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1695 };
	}
}