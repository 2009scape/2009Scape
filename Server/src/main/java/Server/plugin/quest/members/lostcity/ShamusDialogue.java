package plugin.quest.members.lostcity;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Handles the shamus npc dialogue.
 * @author Vexia
 */
public final class ShamusDialogue extends DialoguePlugin {

	/**
	 * The quest.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code ShamusDialogue} {@code Object}.
	 */
	public ShamusDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ShamusDialogue} {@code Object}.
	 * @param player the player.
	 */
	public ShamusDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ShamusDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		quest = player.getQuestRepository().getQuest("Lost City");
		npc("Ay yer big elephant! Yer've caught me, to be sure!", "What would an elephant like yer be wanting wid ol'", "Shamus then?");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 0:
			switch (stage) {
			case 0:
				player("I'm not sure.");
				stage++;
				break;
			case 1:
				npc("Well you'll have to be catchin' me again when yer are,", "elephant!");
				stage++;
				break;
			case 2:
				disappear();
				break;
			case 3:
				end();
				break;
			}
			break;
		case 10:
			switch (stage) {
			case 0:
				player("I want to find Zanaris.");
				stage++;
				break;
			case 1:
				npc("Zanaris is it now? Well well well... Yer'll be needing to", "be going further to that funny little shed out there in the", "swamp, so you will.");
				stage++;
				break;
			case 2:
				player("...but... I thought... Zanaris was a city?");
				stage++;
				break;
			case 3:
				npc("Aye that it is!");
				stage++;
				break;
			case 4:
				player("...How does it fit in a shed then?");
				stage++;
				break;
			case 5:
				npc("Ah yer stupid elephant! The city isn't IN the shed! The", "doorway to the shed is being a portal to Zanaris, so it", "is.");
				stage++;
				break;
			case 6:
				player("So I just walk into the shed and end up in Zanaris", "then?");
				stage++;
				break;
			case 7:
				npc("Oh, was I fergetting to say? Yer need to be carrying a", "Dramenwood staff to be getting there! Otherwise Yer'll", "just be ending up in the shed.");
				stage++;
				break;
			case 8:
				player("So where would I get a staff?");
				stage++;
				break;
			case 9:
				npc("Dramenwood staffs are crafted from branches of the", "Dramen tree, so they are. I hear there's a Dramen", "tree over on the island of Entrana in a cave.");
				stage++;
				break;
			case 10:
				npc("or some such. There would be probably be a good place", "for an elephant like yer to be starting looking I reckon.");
				stage++;
				break;
			case 11:
				npc("The monks are running a ship from Port Sarim to", "Entrana, I hear too. Now leave me alone yer elephant!");
				stage++;
				break;
			case 12:
				disappear();
				break;
			case 13:
				quest.setStage(player, 20);
				end();
				break;
			}
			break;
		default:
			switch (stage) {
			case 0:
				player("I'm not sure.");
				stage++;
				break;
			case 1:
				npc("HA! Look at yer! Look at the supid elephant who tries", "to go catching a leprechaun when he don't even be", "knowing what he wants!");
				increment();
				break;
			case 2:
				disappear();
				break;
			case 3:
				end();
				break;
			}
			break;
		}
		return true;
	}

	/**
	 * Makes dhamus disappear.
	 */
	private void disappear() {
		LostCityPlugin.SHAMUS.setInvisible(true);
		interpreter.sendPlainMessage(false, "The leprechaun magically disappears.");
		stage++;
	}

	@Override
	public int[] getIds() {
		return new int[] { 654 };
	}

}
