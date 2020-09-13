package plugin.dialogue;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.link.quest.Quest;
import plugin.quest.free.dragonslayer.DragonSlayer;

/**
 * Represents the cabin boy jenkins dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class CabinBoyJenkins extends DialoguePlugin {

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code CabinBoyJenkins} {@code Object}.
	 */
	public CabinBoyJenkins() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code CabinBoyJenkins} {@code Object}.
	 * @param player the player.
	 */
	public CabinBoyJenkins(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CabinBoyJenkins(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest(DragonSlayer.NAME);
		switch (quest.getStage(player)) {
		case 20:
			npc("Ahoy ! Whay d'ye think of yer ship then?");
			stage = 0;
			break;
		case 40:
		case 30:
			interpreter.sendDialogues(918, null, "Splice the mainsail!");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 40:
		case 30:
			switch (stage) {
			case 0:
				npc("Aye aye, cap'n!");
				stage = 1;
				break;
			case 1:
				end();
				break;
			}
			break;
		case 20:
			switch (stage) {
			case 0:
				player("Can you sail this ship to Crandor?");
				stage = 1;
				break;
			case 1:
				npc("Not me, sir! I'm just an 'umble cabin boy. You'll need", "a proper cap'n.");
				stage = 2;
				break;
			case 2:
				player("Where can I find a captain?");
				stage = 3;
				break;
			case 3:
				npc("The cap'ns round 'ere seem to be a mite scared of", "Crandor. I ask 'em why and they just say it was afore", "my time,");
				stage = 4;
				break;
			case 4:
				end();
				npc("but there is one cap'n I reckon might 'elp. I 'eard", "there's a retired 'un who lives in Draynor Village who's", "so desperate to sail again 'ed' take any job.");
				stage = 5;
				break;
			case 5:
				npc("I can't remember 'is name, but 'e lives in Draynor", "Village an' makes rope.");
				stage = 6;
				break;
			case 6:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 6085 };
	}
}
