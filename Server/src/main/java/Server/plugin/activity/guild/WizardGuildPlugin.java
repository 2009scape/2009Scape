package plugin.activity.guild;

import core.cache.def.impl.NPCDefinition;
import core.cache.def.impl.ObjectDefinition;
import plugin.dialogue.DialoguePlugin;
import core.game.content.global.Skillcape;
import core.game.content.global.action.ClimbActionHandler;
import core.game.content.global.action.DoorActionHandler;
import core.game.content.global.travel.EssenceTeleport;
import plugin.quest.free.RuneMysteries;
import plugin.skill.Skills;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the wizard guild plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class WizardGuildPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(1600).getHandlers().put("option:open", this);
		ObjectDefinition.forId(1601).getHandlers().put("option:open", this);
		NPCDefinition.forId(462).getHandlers().put("option:teleport", this);
		ObjectDefinition.forId(2154).getHandlers().put("option:open", this);
		ObjectDefinition.forId(2155).getHandlers().put("option:open", this);
		ObjectDefinition.forId(1722).getHandlers().put("option:climb-up", this);
		new WizardDistentorDialogue().init();
		new ZavisticRarveDialogue().init();
		new ProfessorImblewynDialogue().init();
		new WizardFrumsconeDialogue().init();
		new RobeStoreDialogue().init();
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final int id = node instanceof GameObject ? ((GameObject) node).getId() : ((NPC) node).getId();
		switch (option) {
		case "climb-up":
			switch (id) {
			case 1722:
				if (node.getLocation().equals(new Location(2590, 3089, 0))) {
					ClimbActionHandler.climb(player, null, Location.create(2591, 3092, 1));
				} else {
					ClimbActionHandler.climbLadder(player, (GameObject) node, option);
				}
				break;
			}
			break;
		case "open":
			switch (id) {
			case 1600:
			case 1601:
				if (player.getSkills().getStaticLevel(Skills.MAGIC) < 66) {
					player.getDialogueInterpreter().sendDialogue("You need a Magic level of at least 66 to enter.");
					return true;
				}
				DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
				break;
			case 2155:
			case 2154:
				player.getDialogueInterpreter().sendDialogues(460, null, "You can't attack the Zombies in the room, my Zombies", "are for magic target practice only and should be", "attacked from the other side of the fence.");
				break;
			}
			break;
		case "teleport":
			if (!player.getQuestRepository().isComplete(RuneMysteries.NAME)) {
				player.getPacketDispatch().sendMessage("You need to have completed the Rune Mysteries Quest to use this feature.");
				return true;
			}
			EssenceTeleport.teleport(((NPC) node), player);
			return true;
		}
		return true;
	}

	/**
	 * Represents the wizard distentor dialogue.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class WizardDistentorDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@code WizardDistentorDialogue} {@code Object}.
		 */
		public WizardDistentorDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code WizardDistentorDialogue} {@code Object}.
		 * @param player the player.
		 */
		public WizardDistentorDialogue(final Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new WizardDistentorDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			npc("Welcome to the Magicians' Guild!");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				player("Hello there.");
				stage = 1;
				break;
			case 1:
				npc("What can I do for you?");
				stage = 2;
				break;
			case 2:
				if (!player.getQuestRepository().isComplete(RuneMysteries.NAME)) {
					player("Nothing thanks, I'm just looking around.");
					stage = 4;
					return true;
				}
				options("Nothing thanks, I'm just looking around.", "Can you teleport me to Rune Essence?");
				stage = 3;
				break;
			case 3:
				switch (buttonId) {
				case 1:
					player("Nothing thanks, I'm just looking around.");
					stage = 4;
					break;
				case 2:
					player("Can you teleport me to the Rune Essence?");
					stage = 6;
					break;
				}
				break;
			case 4:
				npc("That's fine with me.");
				stage = 5;
				break;
			case 5:
				end();
				break;
			case 6:
				end();
				EssenceTeleport.teleport(npc, player);
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 462 };
		}

	}

	/**
	 * Represents the dialogue used for zavistic rarve.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class ZavisticRarveDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@code ZavisticRarveDialogue} {@code Object}.
		 */
		public ZavisticRarveDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code ZavisticRarveDialogue} {@code Object}.
		 * @param player the player.
		 */
		public ZavisticRarveDialogue(final Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new ZavisticRarveDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			npc("What are you doing...Oh, it's you...sorry...didn't", "realise... what can I do for you?");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				player("Thanks for your help with all of this.");
				stage = 1;
				break;
			case 1:
				npc("Ooohh, no thanks required. It's I who should be", "thanking you my friend...your investigative mind has", "shown how vigilant we really should be for this type of", "evil use of the magical arts.");
				stage = 2;
				break;
			case 2:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 2059 };
		}

	}

	/**
	 * Represents the wizard distentor dialogue.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class ProfessorImblewynDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@code ProfessorImblewynDialogue} {@code Object}.
		 */
		public ProfessorImblewynDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code ProfessorImblewynDialogue} {@code Object}.
		 * @param player the player.
		 */
		public ProfessorImblewynDialogue(final Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new ProfessorImblewynDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			player("I didn't realise gnomes were interested in magic.");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				npc("Gnomes are interested in everything, lad.");
				stage = 1;
				break;
			case 1:
				player("Of course.");
				stage = 2;
				break;
			case 2:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 4586 };
		}

	}

	/**
	 * Represents the dialogue plugin used for the wizard frumscone.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class WizardFrumsconeDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@code WizardFrumsconeDialogue} {@code Object}.
		 * @param player the player.
		 */
		public WizardFrumsconeDialogue(final Player player) {
			super(player);
		}

		/**
		 * Constructs a new {@code WizardFrumsconeDialogue} {@code Object}.
		 */
		public WizardFrumsconeDialogue() {
			/**
			 * empty.
			 */
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new WizardFrumsconeDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc("Do you like my magic Zombies? Feel free to kill them,", "there's plenty more where these came from!");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			end();
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 460 };
		}

	}

	/**
	 * Represents the dialogue plugin used for the robe storew owner.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class RobeStoreDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@code RobeStoreDialogue} {@code Object}.
		 * @param player the player.
		 */
		public RobeStoreDialogue(final Player player) {
			super(player);
		}

		/**
		 * Constructs a new {@code RobeStoreDialogue} {@code Object}.
		 */
		public RobeStoreDialogue() {
			/**
			 * empty.
			 */
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new RobeStoreDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			if (Skillcape.isMaster(player, Skills.MAGIC)) {
				options("Ask about Skillcape.", "Something else");
				stage = 6;
			} else {
				npc("Welcome to the Magic Guild Store. Would you like to", "buy some magic supplies?");
				stage = 0;
			}
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				options("Yes please.", "No thank you.");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					player("Yes please.");
					stage = 4;
					break;
				case 2:
					player("No thank you.");
					stage = 3;
					break;
				}
				break;
			case 3:
				end();
				break;
			case 4:
				end();
				npc.openShop(player);
				break;
			case 6:
				switch (buttonId) {
				case 1:
					player("Can I buy a Skillcape of Magic?");
					stage = 7;
					break;
				case 2:
					npc("Welcome to the Magic Guild Store. Would you like to", "buy some magic supplies?");
					stage = 0;
					break;
				}
				break;
			case 7:
				npc("Certinaly! Right when you give me 99000 coins.");
				stage = 8;
				break;
			case 8:
				options("Okay, here you go.", "No, thanks.");
				stage = 9;
				break;
			case 9:
				switch (buttonId) {
				case 1:
					player("Okay, here you go.");
					stage = 10;
					break;
				case 2:
					end();
					break;
				}
				break;
			case 10:
				if (Skillcape.purchase(player, Skills.MAGIC)) {
					npc("There you go! Enjoy.");
				}
				stage = 11;
				break;
			case 11:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 1658 };
		}

	}
}
