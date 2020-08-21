package plugin.quest.members.thetouristrap;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;

/**
 * The dialogue used for the captain siad npc.
 * @author 'Vexia
 * @version 1.0
 */
public final class CaptainSiadDialogue extends DialoguePlugin {

	/**
	 * The quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code CaptainSiadDialogue} {@code Object}.
	 */
	public CaptainSiadDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code CaptainSiadDialogue} {@code Object}.
	 * @param player the player.
	 */
	public CaptainSiadDialogue(final Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CaptainSiadDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest(TouristTrap.NAME);
		switch (quest.getStage(player)) {
		default:
			player.getPacketDispatch().sendMessage("The captain looks up from his work as you address him.");
			npc("What are you doing in here?");
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 52:
			switch (stage) {
			case 0:
				player("I wanted to have a chat?");
				stage++;
				break;
			case 1:
				npc("You don't belong in here, get out!");
				stage++;
				break;
			case 2:
				player("You seem to have a lot of books!");
				stage++;
				break;
			case 3:
				npc("Yes, I do. Now please get to the point?");
				stage++;
				break;
			case 4:
				player("So, you're interested in sailing?");
				stage++;
				break;
			case 5:
				interpreter.sendDialogue("The captain's interest seems to perk up.");
				stage++;
				break;
			case 6:
				npc("Well, yes actually... It's been a passion of mine for", "some years...");
				stage++;
				break;
			case 7:
				player("I could tell by the cut of your jib.");
				stage++;
				break;
			case 8:
				npc("Oh yes? Really?");
				stage++;
				break;
			case 9:
				npc("Well, I was quite a catch in my day you know!");
				stage++;
				break;
			case 10:
				interpreter.sendDialogue("The captain starts rambling on about his days as a salty sea dog. He", "looks quite distracted...");
				stage++;
				break;
			case 11:
				end();
				quest.setStage(player, 53);
				break;
			}
			break;
		default:
			switch (stage) {
			case 0:
				player("I wanted to have a chat?");
				stage++;
				break;
			case 1:
				npc("You don't belong in here, get out!");
				stage++;
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
		return new int[] { 831 };
	}

}
