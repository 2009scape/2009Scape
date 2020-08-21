package plugin.quest.members.animalmagnetism;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Handles the alice npc dialogue.
 * @author Vexia
 */
public final class AliceDialogue extends DialoguePlugin {

	/**
	 * The quest.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code AliceDialogue} {@code Object}.
	 */
	public AliceDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code AliceDialogue} {@code Object}.
	 * @param player the player.
	 */
	public AliceDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new AliceDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest(AnimalMagnetism.NAME);
		switch (quest.getStage(player)) {
		default:
			options("What are you selling?", "I'm okay, thank you.");
			break;
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
			options("What are you selling?", "I'm okay, thank you.", "I'm here about a quest.");
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		default:
			switch (stage) {
			case 0:
				switch (buttonId) {
				case 1:
					end();
					npc.openShop(player);
					break;
				case 2:
					player("I'm okay, thank you.");
					stage++;
					break;
				}
				break;
			case 1:
				end();
				break;
			}
			break;
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
			switch (stage) {
			case 0:
				switch (buttonId) {
				case 1:
					end();
					npc.openShop(player);
					break;
				case 2:
					player("I'm okay, thank you.");
					stage++;
					break;
				case 3:
					player("I'm here about a quest.");
					stage = 2;
					break;
				}
				break;
			case 1:
				end();
				break;
			default:
				handleQuest(buttonId);
				break;
			}
			break;
		}
		return true;
	}

	/**
	 * Handles a quest stage.
	 * @param buttonId the buttonId.
	 */
	private void handleQuest(int buttonId) {
		switch (quest.getStage(player)) {
		case 10:
			switch (stage) {
			case 2:
				player("I am after one of your, er, unhealthier poultry. Could", "you help me?");
				stage++;
				break;
			case 3:
				npc("You need those useless, undead chickens? How odd you", "adventurers are.");
				stage++;
				break;
			case 4:
				npc("You need to talk to my husband though - not that I", "can these days.");
				stage++;
				break;
			case 5:
				player("Whyever would this be?");
				stage++;
				break;
			case 6:
				npc("Can't you see, he is dead. I can't talk to the dead.");
				stage++;
				break;
			case 7:
				end();
				break;
			}
			break;
		case 11:
			switch (stage) {
			case 2:
				player("I have a message from your husband. He wants you to", "know that he still loves you, despite his ghostly state.");
				stage++;
				break;
			case 3:
				npc("The curse of undeath was so cruel; all the men out", "here succumbed, but Lyra and I were left alive.");
				stage++;
				break;
			case 4:
				npc("Ever since that day, I've not been able to speak to him.");
				stage++;
				break;
			case 5:
				npc("Tell him I love him but I can't find our savings. I", "know he had our collection of gold and 'prize cow'", "rosettes just before the curse struck.");
				stage++;
				break;
			case 6:
				player("I'll have a word with him then; magic has its uses I", "suppose.");
				stage++;
				break;
			case 7:
				quest.setStage(player, 12);
				end();
				break;
			}
			break;
		case 12:
			switch (stage) {
			case 2:
				npc("Have you spoken to my husband yet?");
				stage++;
				break;
			case 3:
				player("I'm working on it.");
				stage++;
				break;
			case 4:
				end();
				break;
			}
			break;
		case 13:
			switch (stage) {
			case 2:
				player("Your husband say he put the cash in the bank.");
				stage++;
				break;
			case 3:
				npc("I'll need his bank pass, in that case.");
				stage++;
				break;
			case 4:
				player("Can't you just take a ghostspeak amulet? Then you", "could talk to him directly?");
				stage++;
				break;
			case 5:
				npc("I tried that once, but all those other ghosts - and even", "the undead chickens and cows - scared me so much. I", "wouldn't try it again for all the cash in Varrock bank.");
				stage++;
				break;
			case 6:
				quest.setStage(player, 14);
				end();
				break;
			}
			break;
		case 14:
			switch (stage) {
			case 2:
				npc("Have you asked him about the bank pass?");
				stage++;
				break;
			case 3:
				player("Not yet.");
				stage++;
				break;
			case 4:
				end();
				break;
			}
			break;
		case 15:
			switch (stage) {
			case 2:
				player("He says he won't trust me with the bank pass.");
				stage++;
				break;
			case 3:
				player("What if I gave some sort of altered ghostspeak amulet", "to him - surely that would work?");
				stage++;
				break;
			case 4:
				npc("You're so clever; I've overheard passing adventurers", "say that there's some witch near here who changes", "ghostspeak amulets.");
				stage++;
				break;
			case 5:
				npc("I think she lives a bit west of that mad Professor", "Frenksomething, past the Farming patch.");
				stage++;
				break;
			case 6:
				player("I'll see if I can find her. Big nose and a monstrous hat", "I assume? I wonder where the beautiful young witches", "hide...");
				stage++;
				break;
			case 7:
				npc("Mysterious indeed, but in this case she actually looks", "preety normal.");
				stage++;
				break;
			case 8:
				quest.setStage(player, 16);
				end();
				break;
			}
			break;
		case 16:
		case 17:
			switch (stage) {
			case 2:
				npc("Have you found a way for me to talk to my husband", "yet?");
				stage++;
				break;
			case 3:
				player("I've not progressed at all, I'm afraid.");
				stage++;
				break;
			case 4:
				end();
				break;
			}
			break;
		case 18:
			switch (stage) {
			case 2:
				npc("Have you handed him an enhanced amulet?");
				stage++;
				break;
			case 3:
				player("I Have obtained the amulet; I just haven't handed it", "over yet. So, it's looking good!");
				stage++;
				break;
			case 4:
				end();
				break;
			}
			break;
		}
	}

	@Override
	public int[] getIds() {
		return new int[] { 2307 };
	}

}
