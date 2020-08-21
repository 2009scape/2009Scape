package plugin.quest.members.animalmagnetism;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;

/**
 * Handles the witch dialogue.
 * @author Vexia
 */
public final class WitchDialogue extends DialoguePlugin {

	/**
	 * The iron bar items.
	 */
	private static final Item IRON_BARS = new Item(2351, 5);

	/**
	 * The quest.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code WitchDialogue} {@code Object}.
	 */
	public WitchDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code WitchDialogue} {@code Object}.
	 * @param player the player.
	 */
	public WitchDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new WitchDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		quest = player.getQuestRepository().getQuest(AnimalMagnetism.NAME);
		switch (quest.getStage(player)) {
		case 25:
			npc("Hello, hello, my poppet. What brings you to my little", "room?");
			break;
		case 26:
			if (!player.getInventory().containsItem(IRON_BARS)) {
				player("I am back.");
			} else {
				npc("Great, you'll go far! I made some nice painted metal", "toys for you, smookums.");
			}
			break;
		case 27:
			if (player.hasItem(AnimalMagnetism.SELECTED_IRON)) {
				npc("You were sent to try my patience, weren't you? Go", "away and make that magnet, then hand it to Ava.");
			} else {
				player("I have lost the selected iron...");
				stage++;
			}
			break;
		default:
			npc("Hello there, deary. Don't worry; I'm friendly.");
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 25:
			switch (stage) {
			case 0:
				player("Ava told me to ask you about making magnets.", "Something about natural fields and other stuff. Sounded", "like she needed a farmer, to be honest.");
				stage++;
				break;
			case 1:
				npc("Don't worry, deary, I can tell you just what to do and", "you won't have to worry your preety head about the", "complicated bits.");
				stage++;
				break;
			case 2:
				player("No need to patronise me quite so much, you know.");
				stage++;
				break;
			case 3:
				npc("I went to anger management classes, my lambkin; that's", "why I was treating you so kindly. It's either this way", "or talking or I'll go back to shoving children into ovens.");
				stage++;
				break;
			case 4:
				npc("Just bring me 5 iron bars, though, and you've well on", "the way to never having to talk to me again.");
				stage++;
				break;
			case 5:
				player("I'll be back.");
				stage++;
				break;
			case 6:
				quest.setStage(player, 26);
				end();
				break;
			}
			break;
		case 26:
			switch (stage) {
			case 0:
				if (!player.getInventory().containsItem(IRON_BARS)) {
					npc("Oh, but sugarpie, I need 5 iron bars, you don't have", "enough. Come back to me quickly with all 5 of them.");
					stage++;
				} else {
					player("Toys? Snookums? What ar eyou on about, you", "deranged old bar?");
					stage += 2;
				}
				break;
			case 2:
				npc("Oh, forget it, then. If you won't react to kindness, I'm", "back to luring infants into my oven. You'll have it on", "your conscience.");
				stage++;
				break;
			case 3:
				npc("Go to the iron mine just north-east of Rimmington and", "hit the bar with a plain old smithing hammer while facing", "north. Then take your new magnet to Ava. Poor girl,", "having to deal with whippersnappers like you.");
				stage++;
				break;
			case 4:
				if (player.getInventory().remove(IRON_BARS)) {
					player.getInventory().add(AnimalMagnetism.SELECTED_IRON);
					quest.setStage(player, 27);
					end();
				}
				break;
			case 1:
				end();
				break;
			}
			break;
		case 27:
			switch (stage) {
			case 0:
				end();
				break;
			case 1:
				npc("Oh, deary, deary... I had a feeling. I can", "make you another for five more iron bars.");
				stage++;
				break;
			case 2:
				if (!player.getInventory().containsItem(IRON_BARS)) {
					player("Okay, I'll go get some.");
					stage++;
					break;
				}
				if (player.getInventory().remove(IRON_BARS)) {
					player.getInventory().add(AnimalMagnetism.SELECTED_IRON);
					end();
				}
				break;
			case 3:
				end();
				break;
			}
			break;
		default:
			switch (stage) {
			case 0:
				player("You look very familiar but I can't remember chatting to", "you before.");
				stage++;
				break;
			case 1:
				npc("Well, my sisters used to live here but folk kept on", "killing them. A terrible affair, it was. I knew that no one", "would want to kill a chatty old dear like me, though.");
				stage++;
				break;
			case 2:
				player("Oh, well...");
				stage++;
				break;
			case 3:
				npc("We still have a terrible problem with local louts, though.", "Poisoning my fish, spilling my compost all over the place", "and causing a nuisance for the dear count.");
				stage++;
				break;
			case 4:
				player("I think...");
				stage++;
				break;
			case 5:
				npc("I'm sure a nice young sort like you won't be any", "trouble though. In any case, the Professor will soon", "share the secret of chickenisation with me... Not that I'd", "use it these days of course.");
				stage++;
				break;
			case 6:
				player("I have to go now.");
				stage++;
				break;
			case 7:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 5200 };
	}

}
