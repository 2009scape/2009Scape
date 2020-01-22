package plugin.activity.guild;

import java.util.List;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.component.Component;
import org.crandor.game.component.ComponentDefinition;
import org.crandor.game.component.ComponentPlugin;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.Skillcape;
import org.crandor.game.content.global.action.ClimbActionHandler;
import org.crandor.game.content.global.action.DoorActionHandler;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.free.crafting.TanningProduct;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the plugin used for the ranging guild.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class RangingGuildPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(2514).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(2511).getConfigurations().put("option:climb-up", this);
		ObjectDefinition.forId(2512).getConfigurations().put("option:climb-down", this);
		new RangingGuildDoorman().init();
		new GuardDialogue().init();
		new LeatherWorkerDialogue().init();
		new ArmourSalesman().init();
		new TribalWeaponSalesman().init();
		new BowArrowSalesman().init();
		new WarningInterface().newInstance(arg);
		new TownAdvisor().init();
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final int id = node instanceof GameObject ? ((GameObject) node).getId() : 0;
		switch (option) {
		case "open":
			switch (id) {
			case 2514:
				if (player.getLocation().getY() >= 3438) {
					if (player.getSkills().getStaticLevel(Skills.RANGE) < 40) {
						player.getDialogueInterpreter().sendDialogue("You need a Ranging level of 40 to enter here.");
						return true;
					}
				}
				DoorActionHandler.handleAutowalkDoor(player, (GameObject) node, player.getLocation().getY() >= 3438 ? Location.create(2659, 3437, 0) : Location.create(2657, 3439, 0));
				break;
			}
			break;
		case "climb-up":
			switch (id) {
			case 2511:
				player.setAttribute("ladder", node);
				player.getInterfaceManager().open(new Component(564));
				break;
			}
			break;
		case "climb-down":
			switch (id) {
			case 2512:
				ClimbActionHandler.climb(player, null, Location.create(2668, 3427, 0));
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		if (n instanceof GameObject) {
			if (((GameObject) n).getDefinition().hasAction("open")) {
				if (((GameObject) n).getId() == 2514) {
					if (node.getLocation().getY() >= 3438) {
						return Location.create(2657, 3439, 0);
					} else {
						return Location.create(2659, 3437, 0);
					}
				}
				return DoorActionHandler.getDestination((Player) node, (GameObject) n);
			}
		}
		return null;
	}

	/**
	 * Represents the dialogue used for the ranging guild door man.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public class RangingGuildDoorman extends DialoguePlugin {

		/**
		 * Constructs a new {@code RangingGuildDoorman} {@code Object}.
		 */
		public RangingGuildDoorman() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code RangingGuildDoorman} {@code Object}.
		 * @param player the player.
		 */
		public RangingGuildDoorman(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new RangingGuildDoorman(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Hello there.");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Greetings. If you are an experienced archer, you may", "want to visit the guild here...");
				stage = 1;
				break;
			case 1:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 679 };
		}
	}

	/**
	 * Represents the tribal weapon salseman dialogue.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class TribalWeaponSalesman extends DialoguePlugin {

		/**
		 * Constructs a new {@code TribalWeaponSalesman} {@code Object}.
		 */
		public TribalWeaponSalesman() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code TribalWeaponSalesman} {@code Object}.
		 * @param player the player.
		 */
		public TribalWeaponSalesman(final Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new TribalWeaponSalesman(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			player("Hello there.");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				npc("Greetings, traveller. Are you interested in any throwing", "weapons?");
				stage = 1;
				break;
			case 1:
				options("Yes I am.", "Not really.");
				stage = 2;
				break;
			case 2:
				switch (buttonId) {
				case 1:
					player("Yes I am.");
					stage = 10;
					break;
				case 2:
					player("Not really.");
					stage = 20;
					break;
				}
				break;
			case 10:
				npc("That is a good thing.");
				stage = 11;
				break;
			case 11:
				end();
				npc.openShop(player);
				break;
			case 20:
				npc("No bother to me.");
				stage = 21;
				break;
			case 21:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 692 };
		}

	}

	/**
	 * Represents the guard dialogue.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class GuardDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@code GuardDialogue} {@code Object}.
		 */
		public GuardDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code GuardDialogue} {@code Object}.
		 * @param player the player.
		 */
		public GuardDialogue(final Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new GuardDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			player("Hello there.");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				npc("Greetings, traveller. Enjoy the time at the Ranging", "Guild.");
				stage = 1;
				break;
			case 1:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 678 };
		}

	}

	/**
	 * Represents the leather workers dialogue.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class LeatherWorkerDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@code LeatherWorkerDialogue} {@code Object}.
		 */
		public LeatherWorkerDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code LeatherWorkerDialogue} {@code Object}.
		 * @param player the player.
		 */
		public LeatherWorkerDialogue(final Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new LeatherWorkerDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			player("Hello.");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				npc("Can I help you?");
				stage = 1;
				break;
			case 1:
				options("What do you do here?", "No thanks.");
				stage = 2;
				break;
			case 2:
				switch (buttonId) {
				case 1:
					player("What do you do here?");
					stage = 10;
					break;
				case 2:
					player("No thanks.");
					stage = 20;
					break;
				}
				break;
			case 10:
				npc("Well, I can cure plain cowhides into pieces of leather", "ready for crafting.");
				stage = 11;
				break;
			case 11:
				npc("I work with ordinary, hard or dragonhide leather and", "also snakeskin.");
				stage = 12;
				break;
			case 12:
				end();
				TanningProduct.open(player, 680);
				break;
			case 20:
				npc("Suit yourself.");
				stage = 21;
				break;
			case 21:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 680 };
		}

	}

	/**
	 * Represents the armour salesman dialogue.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class ArmourSalesman extends DialoguePlugin {

		/**
		 * Constructs a new {@code ArmourSalesman} {@code Object}.
		 */
		public ArmourSalesman() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code ArmourSalesman} {@code Object}.
		 * @param player the player.
		 */
		public ArmourSalesman(final Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new ArmourSalesman(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			player("Good day to you.");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				npc("And to you. Can I help you?");
				stage = 1;
				break;
			case 1:
				if (Skillcape.isMaster(player, Skills.RANGE)) {
					options("What do you do here?", "I'd like to see what you sell.", "Can I buy a Skillcape of Range?", "I've seen enough, thanks.");
					stage = 100;
				} else {
					options("What do you do here?", "I'd like to see what you sell.", "I've seen enough, thanks.");
					stage = 2;
				}
				break;
			case 2:
				switch (buttonId) {
				case 1:
					player("What do you do here?");
					stage = 10;
					break;
				case 2:
					player("I'd like to see what you sell.");
					stage = 20;
					break;
				case 3:
					player("I've seen enough, thanks.");
					stage = 30;
					break;
				}
				break;
			case 10:
				npc("I am a supplier of leather armours and accessories. Ask", "and I will tell you what I know.");
				stage = 11;
				break;
			case 11:
				options("Tell me about your armours.", "Tell me about your accessories.", "I've seen enough, thanks.");
				stage = 12;
				break;
			case 12:
				switch (buttonId) {
				case 1:
					player("Tell me about your armours.");
					stage = 13;
					break;
				case 2:
					player("Tell me about your accessories.");
					stage = 15;
					break;
				case 3:
					player("I've seen enough, thanks.");
					stage = 30;
					break;
				}
				break;
			case 13:
				npc("I have normal, studded and hard types.");
				stage = 14;
				break;
			case 14:
				end();
				break;
			case 15:
				npc("Ah yes we have a new range of accessories in stock.", "Essential items for an archer like you.");
				stage = 14;
				break;
			case 20:
				npc("Indeed, cast your eyes on my wares, adventurer.");
				stage = 21;
				break;
			case 21:
				end();
				npc.openShop(player);
				break;
			case 30:
				npc("Very good, adventurer.");
				stage = 31;
				break;
			case 31:
				end();
				break;
			case 100:
				switch (buttonId) {
				case 1:
					player("What do you do here?");
					stage = 10;
					break;
				case 2:
					player("I'd like to see what you sell.");
					stage = 20;
					break;
				case 3:
					player("Can I buy a Skillcape of Range?");
					stage = 101;
					break;
				case 4:
					player("I've seen enough, thanks.");
					stage = 30;
					break;
				}
				break;
			case 101:
				npc("Certainly! Right when you give me 99000 coins.");
				stage = 102;
				break;
			case 102:
				options("Okay, here you go.", "No, thanks.");
				stage = 103;
				break;
			case 103:
				switch (buttonId) {
				case 1:
					player("Okay, here you go.");
					stage = 104;
					break;
				case 2:
					end();
					break;
				}
				break;
			case 104:
				if (Skillcape.purchase(player, Skills.RANGE)) {
					npc("There you go! Enjoy.");
				}
				stage = 105;
				break;
			case 105:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 682 };
		}

	}

	/**
	 * Represents the dialogue plugin used for the bow and arrow salesman.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class BowArrowSalesman extends DialoguePlugin {

		/**
		 * Constructs a new {@code BowArrowSalesman} {@code Object}.
		 */
		public BowArrowSalesman() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code BowArrowSalesman} {@code Object}.
		 * @param player the player.
		 */
		public BowArrowSalesman(final Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new BowArrowSalesman(player);
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
				npc("A fair day, traveller. Would you like to see my wares?");
				stage = 1;
				break;
			case 1:
				options("Yes please.", "No thanks.");
				stage = 2;
				break;
			case 2:
				switch (buttonId) {
				case 1:
					end();
					npc.openShop(player);
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
			return new int[] { 683 };
		}

	}

	/**
	 * Represents the waring interface used in the guild.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class WarningInterface extends ComponentPlugin {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			ComponentDefinition.forId(564).setPlugin(this);
			return this;
		}

		@Override
		public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
			switch (button) {
			case 17:
				ClimbActionHandler.climb(player, null, Location.create(2668, 3427, 2));
				player.getInterfaceManager().close();
				List<NPC> npcs = RegionManager.getLocalNpcs(Location.create(2668, 3427, 2));
				for (NPC n : npcs) {
					if (n.getId() == 684) {
						n.sendChat("The east tower is occupied, get them!");
					}
				}
				break;
			case 18:
				player.getInterfaceManager().close();
				break;
			}
			return true;
		}

	}

	/**
	 * Represents the town advisor.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class TownAdvisor extends DialoguePlugin {

		/**
		 * Constructs a new {@code TownAdvisor} {@code Object}.
		 * @param player the player.
		 */
		public TownAdvisor(final Player player) {
			super(player);
		}

		/**
		 * Constructs a new {@code TownAdvisor} {@code Object}.
		 */
		public TownAdvisor() {
			/**
			 * empty.
			 */
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new TownAdvisor(player);
		}

		@Override
		public boolean open(Object... args) {
			player("Hello there, what do you do here?");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				npc("Hi. We are in charge of the practice area.");
				stage = 1;
				break;
			case 1:
				player("This is a practice area?");
				stage = 2;
				break;
			case 2:
				npc("Surrounding us are four towers. Each tower contains", "trained archers of a different level. You'll notice it's", "quite a distance, so you'll need a longbow.");
				stage = 3;
				break;
			case 3:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 684 };
		}

	}
}
