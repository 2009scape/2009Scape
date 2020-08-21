package plugin.quest.members.junglepotion;

import core.cache.def.impl.ObjectDefinition;
import plugin.dialogue.DialogueInterpreter;
import plugin.dialogue.DialoguePlugin;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.world.map.Location;
import core.plugin.Plugin;

import plugin.quest.members.junglepotion.JunglePotion.JungleObjective;

/**
 * Handles any interactions of the jungle potion quest.
 * @author Vexia
 */
public final class JunglePotionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(2584).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(2585).getConfigurations().put("option:climb", this);
		for (JungleObjective s : JungleObjective.values()) {
			ObjectDefinition.forId(s.getObjectId()).getConfigurations().put("option:search", this);
		}
		ObjectBuilder.add(new GameObject(2585, Location.create(2828, 9522, 0), 8, 0));
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final Quest quest = player.getQuestRepository().getQuest(JunglePotion.NAME);
		switch (node.getId()) {
		case 2584:
			player.getDialogueInterpreter().open("jogre_dialogue");
			return true;
		case 2585:
			player.getDialogueInterpreter().open("jogre_dialogue", true, true);
			return true;
		}
		switch (option) {
		case "search":
			search(player, quest, (GameObject) node, JungleObjective.forId(node.getId()));
			break;
		}
		return true;
	}

	/**
	 * Searches an object for a herb.
	 * @param player the player.
	 * @param quest the quest.
	 * @param object the object.
	 * @param objective the searchable.
	 */
	private void search(final Player player, Quest quest, final GameObject object, final JungleObjective objective) {
		if (quest.getStage(player) < objective.getStage()) {
			player.sendMessage("Unfortunately, you find nothing of interest.");
			return;
		}
		if (player.getInventory().freeSlots() < 1) {
			player.sendMessage("You don't have enough inventory space.");
			return;
		}
		player.sendMessage("You search the " + object.getName().toLowerCase() + "...");
		objective.search(player, object);
	}

	@Override
	public Location getDestination(Node n, Node node) {
		if (node.getId() == 2585) {
			return Location.create(2830, 9521, 0);
		}
		return null;
	}

	/**
	 * Handles the jogre cavern dialogue.
	 * @author Vexia
	 */
	public static final class JogreCavernDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@code JogreCavernDialogue} {@code Object}.
		 */
		public JogreCavernDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code JogreCavernDialogue} {@code Object}.
		 * @param player the player.
		 */
		public JogreCavernDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new JogreCavernDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			if (args.length > 1) {
				interpreter.sendDialogue("You attempt to climb the rocks back out.");
				stage = 13;
				return true;
			}
			interpreter.sendDialogue("You search the rocks... You find an entrance into some caves.");
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				options("Yes, I'll enter the cave.", "No thanks, I'll give it a miss.");
				stage++;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogue("You decide to enter the caves. You climb down several steep rock", "faces into the cavern below.");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogue("You decide to stay where you are!");
					stage = 11;
					break;
				}
				break;
			case 10:
				end();
				player.getProperties().setTeleportLocation(Location.create(2830, 9520, 0));
				break;
			case 11:
				end();
				break;
			case 13:
				end();
				player.getProperties().setTeleportLocation(Location.create(2823, 3120, 0));
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { DialogueInterpreter.getDialogueKey("jogre_dialogue") };
		}

	}
}
