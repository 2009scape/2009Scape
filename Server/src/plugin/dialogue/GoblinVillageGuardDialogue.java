package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;

/**
 * Represents the dialogue plugin used for the goblin village guard npc.
 * @author jamix77
 */
@InitializablePlugin
public final class GoblinVillageGuardDialogue extends DialoguePlugin {

	/**
	 * 
	 * Constructs a new @{Code HengelDialoguePlugin} object.
	 */
	public GoblinVillageGuardDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * 
	 * Constructs a new @{Code GoblinVillageGuardDialogue} object.
	 * @param player
	 */
	public GoblinVillageGuardDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GoblinVillageGuardDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		player("You're a long way out from the city.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			npc("I know. We guards uaully stay by banks and shops,", "but I got sent all the way out here to keep an eye on","the brigands loitering just south of here.");
			stage = 1;
			break;
		case 1:
			player("Sounds more exciting than standing", "around guarding banks and shops.");
			stage = 2;
			break;
		case 2:
			npc("It's not too bad. At least I don't get attacked so often", "out here. Guards in the cities get killed all the time.");
			stage++;
			break;
		case 3:
			player("Honestly people these days just don't know how to behave!");
			stage++;
			break;
		case 4:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3241 };
	}
}
