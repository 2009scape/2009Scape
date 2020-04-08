package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.map.RegionManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Represents the dialogue plugin used for the anja npc.
 * @author jamix77
 */
@InitializablePlugin
public final class AnjaDialoguePlugin extends DialoguePlugin {

	/**
	 * 
	 * Constructs a new @{Code AnjaDialoguePlugin} object.
	 */
	public AnjaDialoguePlugin() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code AjjatDialoguePlugin} {@code Object}.
	 * @param player the player.
	 */
	public AnjaDialoguePlugin(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new AnjaDialoguePlugin(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		player("Hello.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			npc("Hello sir. What are you doing in my house?");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "I'm just wandering around.", "I was hoping you'd give me some free stuff.", "I've come to kill you.");
			stage = 2;
			break;
		case 2:
			if (buttonId == 1) {
				player("I'm just wondering around.");
				stage = 3;
			} else if (buttonId == 2) {
				player("I was hoping you'd give me some free stuff.");
				stage = 10;
			} else if (buttonId == 3) {
				player("I've come to kill you.");
				stage = 13;
			} 
			break;
		case 3:
			npc("Oh dear are you lost?");
			stage++;
			break;
		case 4:
			interpreter.sendOptions("Select an Option.", "Yes, I'm lost.", "No, I know where I am.");
			stage++;
			break;
		case 5:
			switch (buttonId) {
			case 1:
				player("Yes, I'm lost.");
				stage =6;
				break;
			case 2:
				player("No I know where I am.");
				stage = 8;
				break;
			}
			break;
		case 6:
			npc("Okay, just walk north-east when you leave this house,","and soon you'll reach the big city of Falador.");
			stage++;
			break;
		case 7:
			player("Thanks a lot.");
			stage = 605;
			break;
		case 8:
			npc("Oh? Well, would you mind wandering somewhere else?", "This is my house.");
			stage++;
			break;
		case 9:
			player("Meh!");
			stage = 605;
			break;
		case 10:
			String[] dialogues = {"Do you REALLY need it","I don't have much on me...", "I don't know..."};
			npc(dialogues[RandomFunction.random(0, 2)]);
			stage++;
			break;
		case 11:
			interpreter.sendDialogues(player, FacialExpression.ASKING, "I promise I'll stop bothering you!", "Pleeease!","Pwetty pleathe wiv thugar on top!");
			stage++;
			break;
		case 12:
			npc("Oh, alright. Here you go.");
			player.getInventory().add(new Item(995,RandomFunction.random(1, 3)));
			stage = 605;
			break;
		case 13:
			npc.sendChat("Eeeek!");
			for (NPC npc1 : RegionManager.getLocalNpcs(player)) {
				if (npc1.getName().equalsIgnoreCase("Hengel")) {
					npc1.sendChat("Aaaaarrgh!");
					break;
				}
			}
			end();
			break;
		case 605:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2684 };
	}
}
