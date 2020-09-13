package plugin.dialogue;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.game.world.map.Location;
import plugin.quest.members.PriestInPeril;
import plugin.quest.members.lostcity.LostCity;

/**
 * Represents the dialogue plugin used for Bill Teach
 * @author Charlie
 * @version 1.0
 */
@InitializablePlugin
public final class BillTeachDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code BarfyBill} {@code Object}.
	 */
	public BillTeachDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code BarfyBill} {@code Object}.
	 * @param player the player.
	 */
	public BillTeachDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new BillTeachDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = new NPC(3155);
		player("Hello there.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			npc("Arr'! Avast ye' scallywag!", "You be lookin' to get somewhere " + (player.isMale() ? "lad?" : "lass?"));
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "Yes", "No, thank you");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				player("Yes, where can you take me?");
				stage = 1000;
				break;
			case 2:
				npc("Arr'! You be wastin' my time again..");
				stage = 7;
				break;
			}
			break;
		case 1000:
			npc("Aye, take a browse through me options.");
			stage++;
			break;
		case 1001:
			interpreter.sendOptions("Select an Option", "Slayer Tower", "Zanaris Fairy Ring", "Gnome Stronghold", "Rellekka", "More");
			stage = 2000;
			break;
		case 2000:
			switch (buttonId) {
			case 1:
				if(!player.getQuestRepository().isComplete(PriestInPeril.NAME)) {
					npc("Aye, sorry there " + pirateGender() + ", but you'll be needing to ", "help King Roald with something first.");
					stage = 7;
				} else {
					end();
					player.teleport(new Location(3429, 3526, 0));
				}
				break;
			case 2:
				if(!player.getQuestRepository().isComplete(LostCity.NAME)) {
					npc("Aye, sorry there " + pirateGender() + ", but you'll be needing to ", "discover Zanaris first.");
					stage = 7;
				} else {
					end();
					player.teleport(new Location(2412, 4433, 0));
				}
				break;
			case 3:
				end();
				player.teleport(new Location(2461, 3444, 0));
				break;
			case 4:
				end();
				player.teleport(new Location(2669, 3631, 0));
				break;
			case 5:
				interpreter.sendOptions("Select an Option", "Kalphite Lair", "Asgarnian Ice Dungeon", "Fremmenik Dungeon", "Taverley Dungeon", "More");
				stage = 3000;
				break;
			}
			break;
		case 3000:
			switch (buttonId) {
			case 1:
				end();
				player.teleport(new Location(3227, 3107, 0));
				break;
			case 2:
				end();
				player.teleport(new Location(3007, 9550, 0));
				break;
			case 3:
				end();
				player.teleport(new Location(2808, 10002, 0));
				break;
			case 4:
				end();
				player.teleport(new Location(2884, 9798, 0));
				break;
			case 5:
				interpreter.sendOptions("Select an Option", "Waterfall Dungeon", "Brimhaven Dungeon", "Ape Atoll Dungeon", "God Wars Dungeon", "More");
				stage = 4000;
				break;
			}
			break;
		case 4000:
			switch (buttonId) {
			case 1:
				end();
				player.teleport(new Location(2575, 9861, 0));
				break;
			case 2:
				end();
				player.teleport(new Location(2713, 9564, 0));
				break;
			case 3:
				end();
				player.teleport(new Location(2715, 9184, 0));
				break;
			case 4:
				end();
				player.teleport(new Location(2898, 3710, 0));
				break;
			case 5:
				interpreter.sendOptions("Select an Option", "Shilo Village", "Yanille", "Zul-Andra", "Piscatoris Fishing Colony", "More");
				stage = 5000;
				break;
			}
			break;
		case 5000:
			switch (buttonId) {
			case 1:
				end();
				player.teleport(new Location(2867, 2952, 0));
				break;
			case 2:
				end();
				player.teleport(new Location(2544, 3096, 0));
				break;
			case 3:
				end();
				player.teleport(new Location(2193, 3055, 0));
				break;
			case 4:
				end();
				player.teleport(new Location(2343, 3663, 0));
				break;
			case 5:
				interpreter.sendOptions("Select an Option", "Bandit Camp", "Miscellenia", "Mort'ton", "Feldip Hills", "Back");
				stage = 6000;
				break;
			}
			break;
		case 6000:
			switch (buttonId) {
			case 1:
				end();
				player.teleport(new Location(3176, 2987, 0));
				break;
			case 2:
				end();
				player.teleport(new Location(2581, 3845, 0));
				break;
			case 3:
				end();
				player.teleport(new Location(3488, 3296, 0));
				break;
			case 4:
				end();
				player.teleport(new Location(2525, 2915, 0));
				break;
			case 5:
				interpreter.sendOptions("Select an Option", "Slayer Tower", "Zanaris Fairy Ring", "Gnome Stronghold", "Rellekka", "More");
				stage = 2000;
				break;
			}
			break;
		case 7:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3155 };
	}

}
