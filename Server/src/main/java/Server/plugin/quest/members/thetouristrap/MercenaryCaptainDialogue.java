package plugin.quest.members.thetouristrap;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.Entity;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.world.map.Location;
import core.plugin.PluginManager;

/**
 * Represents the mercenary captain dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
public final class MercenaryCaptainDialogue extends DialoguePlugin {

	/**
	 * Represents the quest.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code MercenaryCaptainDialogue} {@code Object}.
	 */
	public MercenaryCaptainDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MercenaryCaptainDialogue} {@code Object}.
	 * @param player the player.
	 */
	public MercenaryCaptainDialogue(final Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MercenaryCaptainDialogue(player);
	}

	@Override
	public void init() {
		super.init();
		PluginManager.definePlugin(new MercenaryCaptain());
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest(TouristTrap.NAME);
		switch (quest.getStage(player)) {
		case 11:
			interpreter.sendDialogue("You approach the Mercenary Captain.");
			break;
		default:
			npc("What are you doing here?");
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
		case 11:
			switch (stage) {
			case 0:
				player("Wow! A real captain!");
				stage++;
				break;
			case 1:
				npc("Be off effendi, you are not wanted around here.");
				stage++;
				break;
			case 2:
				player("I'd love to work for a tough guy like you!");
				stage++;
				break;
			case 3:
				npc("Hmmm, oh yes, what can you do?");
				stage++;
				break;
			case 4:
				player("Can't I do something for a strong Captain like you?");
				stage++;
				break;
			case 5:
				interpreter.sendDialogue("The Captain ponders a moment and then looks at you critically.");
				stage++;
				break;
			case 6:
				npc("You could bring me the head of Al Zaba Bhasim.");
				stage++;
				break;
			case 7:
				npc("He is the leader of the notorious desert bandits, they", "plague us daily. You should find them west of here.", "You should have no problem in finishing them all off.", "Do this for me and maybe I will consider helping you.");
				stage++;
				break;
			case 8:
				player("Sorry Sir, I don't think I can do that.");
				stage++;
				break;
			case 9:
				npc("Hmm, well yes, I did consider that you might not be", "right for the job. be off with you then before I turn", "my men loose on you.");
				stage++;
				break;
			case 10:
				player("It's a funny captain who can't fight his own battles!");
				stage++;
				break;
			case 11:
				interpreter.sendDialogue("The men around you fall silent and the Captain silently fumes. All", "eyes turn to the Captain...");
				stage++;
				break;
			case 12:
				npc("Very well, if you're challenging me, let's get on with it!");
				stage++;
				break;
			case 13:
				interpreter.sendDialogue("The guards gather around to watch the fight.");
				stage++;
				break;
			case 14:
				end();
				npc.getProperties().getCombatPulse().attack(player);
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 830 };
	}

	/**
	 * The mercenary captain npc.
	 * @author 'Vexia
	 */
	public static final class MercenaryCaptain extends AbstractNPC {

		/**
		 * Constructs a new {@code MercenaryCaptain} {@code Object}.
		 */
		public MercenaryCaptain() {
			super(0, null);
		}

		/**
		 * Constructs a new {@code MercenaryCaptain} {@code Object}.
		 * @param id the id..
		 * @param location the location.
		 */
		public MercenaryCaptain(int id, Location location) {
			super(id, location);
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new MercenaryCaptain(id, location);
		}

		@Override
		public void finalizeDeath(Entity killer) {
			super.finalizeDeath(killer);
			if (killer instanceof Player) {
				final Player player = (Player) killer;
				final Quest quest = player.getQuestRepository().getQuest(TouristTrap.NAME);
				switch (quest.getStage(player)) {
				case 0:
				case 10:

					break;
				default:
					if (!player.getInventory().containsItem(TouristTrap.METAL_KEY) && !player.getBank().containsItem(TouristTrap.METAL_KEY)) {
						player.getInventory().add(TouristTrap.METAL_KEY, player);
						player.getDialogueInterpreter().sendItemMessage(TouristTrap.METAL_KEY, "The mercenary captain drops a metal key on the floor.", "You quickly grab the key and add it to your inventory.");
					}
					quest.setStage(player, 20);
					break;
				}
			}
		}

		@Override
		public int[] getIds() {
			return new int[] { 830 };
		}

	}
}
