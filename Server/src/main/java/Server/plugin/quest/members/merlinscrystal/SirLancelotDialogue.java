package plugin.quest.members.merlinscrystal;

import core.game.node.entity.player.link.quest.Quest;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for king arthur.
 * @author 'Vexia
 * @author Splinter
 * @version 2.0
 */
public final class SirLancelotDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code KingArthurDialogue} {@code Object}.
	 */
	public SirLancelotDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code KingArthurDialogue} {@code Object}.
	 * @param player the player.
	 */
	public SirLancelotDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SirLancelotDialogue(player);
	}

	private Quest quest;
	
	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		npc("Greetings! I am Sir Lancelot, the greatest Knight in the", "land! What do you want?");
		
		quest = player.getQuestRepository().getQuest("Merlin's Crystal");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		// TODO more accurate dialogue https://runescape.wiki/w/Transcript:Merlin%27s_Crystal#Sir_Lancelot
		switch (stage) {
		case 0:
			if (quest.getStage(player) >= 20 && quest.getStage(player) < 50) {
				player("Any idea on how to get into Morgan Le Faye's", "stronghold?");
				stage = 1;
			} else {
				player("You're a little full of yourself, aren't you?");
				stage = 16;
			}
			break;
		case 1:
			npc("That stronghold is built in a strong defensive position.");
			stage = 2;
			break;
		case 2:
			npc("There are two ways in that I know of, the large heavy", "front doors, and the sea entrance, only penetrable by", "boat.");
			stage = 3;
			break;
		case 3:
			npc("They take all their deliveries by boat.");
			quest.setStage(player, 30);
			player.getQuestRepository().syncronizeTab(player);
			stage = 15;
			break;
		case 15:
			end();
			break;
		case 16:
			npc("I have every right to be proud of myself.");
			stage = 17;
			break;
		case 17:
			npc("My prowess in battle is world renowned!");
			stage = 15;
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 239 };
	}
}