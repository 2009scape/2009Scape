package plugin.quest.members.thetouristrap;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;

/**
 * Represents the mercenary dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
public final class MercenaryDialogue extends DialoguePlugin {

	/**
	 * Represents the coins item to bribe.
	 */
	private static final Item COINS = new Item(995, 5);

	/**
	 * Represents the quest.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code MercenaryDialogue} {@code Object}.
	 */
	public MercenaryDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MercenaryDialogue} {@code Object}.
	 * @param player the player.
	 */
	public MercenaryDialogue(final Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MercenaryDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		quest = player.getQuestRepository().getQuest(TouristTrap.NAME);
		switch (quest.getStage(player)) {
		default:
			npc("What are you doing here?");
			break;
		case 10:
			npc("Yeah, what do you want?");
			break;
		case 100:
			npc("What are you looking at?");
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
				player("Nothing, just passing by.");
				stage++;
				break;
			case 1:
				end();
				break;
			}
			break;
		case 10:
			switch (stage) {
			case 0:
				player("I'm looking for a woman called Ana, have you seen her?");
				stage++;
				break;
			case 1:
				npc("No, now get lost!");
				stage++;
				break;
			case 2:
				player("Perhaps five gold coins will help you remember?");
				stage++;
				break;
			case 3:
				npc("Well, it certainly will help!");
				stage = 4;
				break;
			case 4:
				if (!player.getInventory().containsItem(COINS)) {
					player("Sorry, I don't seem to have enough coins.");
					stage++;
					break;
				}
				if (player.getInventory().remove(COINS)) {
					interpreter.sendItemMessage(COINS, "-- The guard takes the five gold coins. --");
					stage = 6;
				}
				break;
			case 5:
				end();
				break;
			case 6:
				npc("Now then, what did you want to know?");
				stage++;
				break;
			case 7:
				player("I'm looking for a woman called Ana, have you seen her?");
				stage++;
				break;
			case 8:
				npc("Hmm, well, we get a lot of people in here. But not", "many women through... Saw one come in last week....");
				stage++;
				break;
			case 9:
				npc("But I don't know if it's the woman you're looking for?");
				stage++;
				break;
			case 10:
				player("What are you guarding?");
				stage = 11;
				break;
			case 11:
				npc("Well, if you have to know, we're making sure that no", "prisoners get out. <col=08088A>-- The guard gives you a", "<col=08088A>disapproving look. -- </col>And to make sure that", "unauthorised people don't get in.");
				stage = 12;
				break;
			case 12:
				npc("<col=08088A>-- The guard looks around nervously. --</col> You'd better", "go soon before the Captain orders us to kill you.");
				stage++;
				break;
			case 13:
				player("Does the Captain order you to kill a lot of people?");
				stage++;
				break;
			case 14:
				npc("<col=08088A>-- The guard snorts. --</col> *Snort* Just about anyone", "who talks to him.");
				stage++;
				break;
			case 15:
				npc("Unless he has a use for you, he'll probably just order", "us to kill you. And it's such a horrible job cleaning up", "the mess afterwards.");
				stage++;
				break;
			case 16:
				player("Not to mention the senseless waste of human life.");
				stage++;
				break;
			case 17:
				npc("Huh? Them's your words, not mine.");
				stage++;
				break;
			case 18:
				player("It doesn't sound as if you respect your Captain much.");
				stage++;
				break;
			case 19:
				interpreter.sendDialogue("-- The guard looks around conspiratorially. --");
				stage++;
				break;
			case 20:
				npc("Well, to be honest. We think he's not exactly as brave", "as he makes out. But we have to follow his orders. If", "someone called him a coward, or managed to trick him", "into a one-on-one duel many of us bet that he'd be");
				stage++;
				break;
			case 21:
				npc("beaten.");
				stage++;
				break;
			case 22:
				player("And how could I trick him into a one-on-one duel?");
				stage++;
				break;
			case 23:
				npc("Like all cowards, he likes to be made to feel important.", "If anyone insults him outright, he just gets us to do his", "dirty work. However, if he thinks he's better than you,", "if you compliment him, he may feel that he can defeat");
				stage = 24;
				break;
			case 24:
				npc("you. And if he initiated a duel, all the men agreed that", "they wouldn't intervene. We think he'd be slaughtered", "in double quick time.");
				stage++;
				break;
			case 25:
				quest.setStage(player, 11);
				end();
				break;
			}
			break;
		case 100:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4989, 4990, 4991, 4992 };
	}

}
