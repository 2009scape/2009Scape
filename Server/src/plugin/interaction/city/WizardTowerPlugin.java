package plugin.interaction.city;

import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.component.Component;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.Skillcape;
import org.crandor.game.content.global.action.ClimbActionHandler;
import org.crandor.game.content.global.action.DoorActionHandler;
import org.crandor.game.content.global.travel.EssenceTeleport;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.CombatSpell;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.RunScript;
import org.crandor.game.node.entity.player.link.SpellBookManager.SpellBook;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.ChildPositionContext;
import org.crandor.net.packet.out.RepositionChild;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import org.crandor.tools.RandomFunction;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.StringUtils;

/**
 * Represents the plugins used related to the wizard tower.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class WizardTowerPlugin extends OptionHandler {

	/**
	 * Represents the location of the wizard tower.
	 */
	private static final Location BASEMENT = new Location(3104, 9576, 0);

	/**
	 * Represents the location of the ground floor.
	 */
	private static final Location GROUND_FLOOR = new Location(3105, 3162, 0);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(12540).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(12539).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(2147).getConfigurations().put("option:climb-down", this);
		ObjectDefinition.forId(32015).getConfigurations().put("option:climb-up", this);
		NPCDefinition.forId(300).getConfigurations().put("option:teleport", this);
		ObjectDefinition.forId(11993).getConfigurations().put("option:open", this);
		PluginManager.definePlugin(new WizardtowerWizardNPC());
		PluginManager.definePlugin(new WizardTowerDialogue());
		PluginManager.definePlugin(new WizardMisgogDialogue());
		PluginManager.definePlugin(new WizardGrayzagDialogue());
		PluginManager.definePlugin(new WizardDialogue());
		PluginManager.definePlugin(new SedridorDialogue());
		PluginManager.definePlugin(new AuburyDialoguePlugin());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (option) {
		case "teleport":
			if (!player.getQuestRepository().isComplete("Rune Mysteries")) {
				player.getPacketDispatch().sendMessage("You need to have completed the Rune Mysteries Quest to use this feature.");
				return true;
			}
			EssenceTeleport.teleport(((NPC) node), player);
			break;
		case "climb-down":
			player.getProperties().setTeleportLocation(BASEMENT);
			break;
		case "climb-up":
			if(node.getLocation().equals(new Location(3017, 10249))){
				ClimbActionHandler.climb(player, new Animation(828), new Location(3069, 3857, 0));
				return true;
			}
			if(node.getLocation().equals(new Location(3069, 10256))){
				ClimbActionHandler.climb(player, new Animation(828), new Location(3017, 3850, 0));
				return true;
			}
			if (player.getLocation().withinDistance(Location.create(3117, 9753, 0))) {
				ClimbActionHandler.climb(player, new Animation(828), new Location(3092, 3361, 0));
				return true;
			}
			if (!Location.create(3103, 9576, 0).equals(((GameObject) node).getLocation())) {
				return ObjectDefinition.getOptionHandler(2147, "climb-up").handle(player, node, option);
			}
			player.getProperties().setTeleportLocation(GROUND_FLOOR);
			break;
		case "search":
			player.getDialogueInterpreter().open(458543948);
			break;
		case "open":
			if (node.getLocation().equals(new Location(3107, 3162, 0))) {
				DoorActionHandler.handleAutowalkDoor(player, (GameObject) node, player.getLocation().getX() >= 3107 ? Location.create(3106, 3161, 0) : Location.create(3108, 3163, 0));
			} else {
				DoorActionHandler.handleDoor(player, (GameObject) node);
			}
			return true;
		}
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		if (n instanceof GameObject) {
			final GameObject object = (GameObject) n;
			if (object.getId() == 11993 && object.getLocation().equals(new Location(3107, 3162, 0))) {
				return node.getLocation().getX() >= 3107 ? Location.create(3108, 3163, 0) : Location.create(3106, 3161, 0);
			}
		}
		return null;
	}

	/**
	 * Represents a wizard at the wizard tower.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class WizardtowerWizardNPC extends AbstractNPC {

		/**
		 * The NPC ids of NPCs using this plugin.
		 */
		private static final int[] ID = { 13 };

		/**
		 * Constructs a new {@code WizardtowerWizardNPC} {@code Object}.
		 */
		public WizardtowerWizardNPC() {
			super(0, null);
		}

		/**
		 * Constructs a new {@code AlKharidWarriorPlugin} {@code Object}.
		 * @param id The NPC id.
		 * @param location The location.
		 */
		private WizardtowerWizardNPC(int id, Location location) {
			super(id, location);
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new WizardtowerWizardNPC(id, location);
		}

		@Override
		public void init() {
			super.init();
			getProperties().getCombatPulse().setStyle(CombatStyle.MAGIC);
			getProperties().setAutocastSpell((CombatSpell) SpellBook.MODERN.getSpell(8));
		}

		@Override
		public void finalizeDeath(Entity killer) {
			super.finalizeDeath(killer);
			GroundItemManager.create(new Item(526), getLocation(), (Player) killer);
		}

		@Override
		public int[] getIds() {
			return ID;
		}

	}

	/**
	 * Represents the dialogue used in the wizard tower.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class WizardTowerDialogue extends DialoguePlugin {

		/**
		 * Represents the book name to use.
		 */
		private String bookName = "";

		/**
		 * Represents the book names.
		 */
		private final String[] books = new String[] { "Living with a Wizard Husband - a Housewife's Story", "Wind Strike for Beginners", "So you think you're a Mage? Volume 28", "101 Ways to Impress your Mates with Magic", "The Life & Times of a Thingummywut by Traiborn the Wizard", "How to become the Ulitmate Wizard of the Universe", "The Dark Arts of Magical Wands" };

		/**
		 * Constructs a new {@code WizardTowerDialogue.java} {@code Object}.
		 */
		public WizardTowerDialogue() {
		}

		/**
		 * Constructs a new {@code WizardTowerDialogue.java} {@code Object}.
		 * @param player the player.
		 */
		public WizardTowerDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new WizardTowerDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			interpreter.sendDialogue("There's a large selection of books, the majority of which look fairly", "old. Some very strange names... You pick one at random :");
			bookName = books[RandomFunction.random(books.length)];
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				interpreter.sendDialogue("'" + bookName + "'");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogue("Interesting...");
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
			return new int[] { 458543948 };
		}
	}

	/**
	 * Handles the WizardMisgogDialogue dialogue.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class WizardMisgogDialogue extends DialoguePlugin {

		/**
		 * Represents the black bead item.
		 */
		private static final Item BLACK_BEAD = new Item(1474);

		/**
		 * Represents the red bead item.
		 */
		private static final Item RED_BEAD = new Item(1470);

		/**
		 * Represents the white bead item.
		 */
		private static final Item WHITE_BEAD = new Item(1476);

		/**
		 * Represents the yellow bead item.
		 */
		private static final Item YELLOW_BEAD = new Item(1472);

		/**
		 * Represents the animation.
		 */
		private static final Animation ANIMATION = new Animation(4285);

		/**
		 * Constructs a new {@code WizardMisgogDialogue} {@code Object}.
		 */
		public WizardMisgogDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code WizardMisgogDialogue} {@code Object}.
		 * @param player the player.
		 */
		public WizardMisgogDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new WizardMisgogDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			Quest quest = player.getQuestRepository().getQuest("Imp Catcher");
			switch (quest.getStage(player)) {
			case 0:
				interpreter.sendDialogues(player, null, "Give me a quest!");
				stage = 0;
				break;
			case 10:
				interpreter.sendDialogues(npc, null, "So how are you doing finding my beads?");
				stage = 0;
				break;
			case 100:
				interpreter.sendOptions("Select an Option", "Got any more quests?", "Do you know any interesting spells you could teach me?");
				stage = 0;
				break;
			}
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			final Quest quest = player.getQuestRepository().getQuest("Imp Catcher");
			switch (quest.getStage(player)) {
			case 0:
				switch (stage) {
				case 0:
					interpreter.sendDialogues(npc, null, "Give me a quest what?");
					stage = 1;
					break;
				case 1:
					interpreter.sendOptions("Select an Option", "Give me a quest please.", "Give me a quest or else!", "Just stop messing around and give me a quest!");
					stage = 2;
					break;
				case 2:
					switch (buttonId) {
					case 1:
						interpreter.sendDialogues(player, null, "Give me a quest please.");
						stage = 10;
						break;
					case 2:
						interpreter.sendDialogues(player, null, "Give me a quest or else!");
						stage = 20;
						break;
					case 3:
						interpreter.sendDialogues(player, null, "Just stop messing around and give me a quest!");
						stage = 30;
						break;
					}
					break;
				case 10:
					interpreter.sendDialogues(npc, null, "Well seeing as you asked nicely... I could do with some", "help.");
					stage = 11;
					break;
				case 11:
					interpreter.sendDialogues(npc, null, "The wizard Grayzag next door decided he didn't like", "me so he enlisted an army of hundreds of imps.");
					stage = 12;
					break;
				case 12:
					interpreter.sendDialogues(npc, null, "The imps stole all sorts of my things. Most of these", "things I don't really care about, just eggs and balls of", "string and things.");
					stage = 13;
					break;
				case 13:
					interpreter.sendDialogues(npc, null, "But they stole my four magical beads. There was a red", "one, a yellow one, a black one, and a white one.");
					stage = 14;
					break;
				case 14:
					interpreter.sendDialogues(npc, null, "These imps have now spread out all over the kingdom.", "Could you get my beads back for me?");
					stage = 15;
					break;
				case 15:
					interpreter.sendOptions("Select an Option", "I'll try", "I've better things to do than chase imps.");
					stage = 16;
					break;
				case 16:
					switch (buttonId) {
					case 1:
						interpreter.sendDialogues(player, null, "I'll try.");
						stage = 17;
						break;
					case 2:
						interpreter.sendDialogues(player, null, "I've better things to do than chase imps.");
						stage = 18;
						break;
					}
					break;
				case 17:
					interpreter.sendDialogues(npc, null, "That's great, thank you.");
					stage = 19;
					break;
				case 19:
					quest.start(player);
					player.getQuestRepository().syncronizeTab(player);
					end();
					break;
				case 18:
					end();
					break;
				case 20:
					interpreter.sendDialogues(npc, null, "Or else what? You'll attack me?");
					stage = 21;
					break;
				case 21:
					interpreter.sendDialogues(npc, null, "Hahaha!");
					stage = 22;
					break;
				case 22:
					end();
					break;
				case 30:
					interpreter.sendDialogues(npc, null, "Ah now you're assuming I have one to give.");
					stage = 31;
					break;
				case 31:
					end();
					break;
				}
				break;
			case 10:
				switch (stage) {
				case 0:
					if (player.getInventory().containItems(BLACK_BEAD.getId(), RED_BEAD.getId(), YELLOW_BEAD.getId(), WHITE_BEAD.getId())) {
						interpreter.sendDialogues(player, null, "I've got all four beads. It was hard work I can tell you.");
						stage = 5;
						break;
					}
					if (!player.getInventory().containsItem(WHITE_BEAD) && !player.getInventory().containsItem(RED_BEAD) && !player.getInventory().containsItem(YELLOW_BEAD) && !player.getInventory().containsItem(BLACK_BEAD)) {
						interpreter.sendDialogues(player, null, "I've not found any yet.");
						stage = 1;
					} else {
						interpreter.sendDialogues(player, null, "I have found some of your beads.");
						stage = 3;
					}
					break;
				case 1:
					interpreter.sendDialogues(npc, null, "Well get on with it. I've lost a white bead, a read bead, a", "black bead, and a yellow bead. Go kill some imps!");
					stage = 2;
					break;
				case 2:
					end();
					break;
				case 3:
					interpreter.sendDialogues(npc, null, "Come back when you have them all. The colour of the", "four beads that I need are red, yellow, black, and white.", "Go chase some imps!");
					stage = 4;
					break;
				case 4:
					end();
					break;
				case 5:
					interpreter.sendDialogues(npc, null, "Give them here and I'll check that they really are MY", "beads, before I give you your reward. You'll like it, it's", "an amulet of accuracy.");
					stage = 6;
					break;
				case 6:
					interpreter.sendDialogue("You give four coloured beads to Wizard Mizgog.");
					stage = 7;
					break;
				case 7:
					end();
					if (player.getInventory().remove(BLACK_BEAD, WHITE_BEAD, RED_BEAD, YELLOW_BEAD)) {
						player.lock(7);
						npc.faceLocation(Location.create(3102, 3163, 2));
						npc.animate(ANIMATION);
						GameWorld.submit(new Pulse(3, player) {
							@Override
							public boolean pulse() {
								quest.finish(player);
								player.getQuestRepository().syncronizeTab(player);
								return true;
							}

						});
					}
					break;
				}
				break;
			case 100:
				switch (stage) {
				case 0:
					switch (buttonId) {
					case 1:
						interpreter.sendDialogues(player, null, "Got any more quests?");
						stage = 10;
						break;
					case 2:
						interpreter.sendDialogues(player, null, "Do you know any interesting spells you could teach me?");
						stage = 20;
						break;
					}
					break;
				case 10:
					interpreter.sendDialogues(npc, null, "No, everything is good with the world today.");
					stage = 11;
					break;
				case 11:
					end();
					break;
				case 20:
					interpreter.sendDialogues(npc, null, "I don't think so, the type of magic I study involves", "years of meditation and research.");
					stage = 11;
					break;
				}
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 706 };
		}
	}

	/**
	 * Handles the WizardGrayzagDialogue dialogue.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class WizardGrayzagDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@code WizardGrayzagDialogue} {@code Object}.
		 */
		public WizardGrayzagDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code WizardGrayzagDialogue} {@code Object}.
		 * @param player the player.
		 */
		public WizardGrayzagDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new WizardGrayzagDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Not now, I'm trying to concentrate on a very difficult", "spell!");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 707 };
		}
	}

	/**
	 * Handles the WizardDialogue dialogue.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class WizardDialogue extends DialoguePlugin {

		/**
		 * Represents the bark item used to make split bark equipment.
		 */
		private static final Item BARK = new Item(3239);

		/**
		 * Constructs a new {@code WizardDialogue} {@code Object}.
		 */
		public WizardDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code WizardDialogue.java} {@code Object}.
		 * @param player the player.
		 */
		public WizardDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new WizardDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Hello there, can I help you?");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				interpreter.sendOptions("Select an Option", "What do you do here?", "What's that you're wearing?", "Can you make me some armour please?", "No thanks.");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What do you do here?");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What's that you're wearing?");
					stage = 20;
					break;
				case 3:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Can you make me some armour please?");
					stage = 30;
					break;
				case 4:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No thanks.");
					stage = 40;
					break;
				}
				break;
			case 30:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Certainly, what would like to me to make?");
				stage = 31;
				break;
			case 31:
				player.getInterfaceManager().openChatbox(new Component(306));
				int count = 2;
				int shift = 75;
				PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 306, 26, 10, shift));
				PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 306, 22, 88, shift));
				PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 306, 18, 288, shift));
				PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 306, 14, 188, shift + 5));
				PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 306, 10, 380, shift));
				int indexes[] = new int[] { 26, 22, 14, 18, 10 };
				for (SplitBark bark : SplitBark.values()) {
					player.getPacketDispatch().sendItemZoomOnInterface(bark.itemId, 170, 306, count);
					player.getPacketDispatch().sendString(StringUtils.formatDisplayName(bark.name()), 306, indexes[bark.ordinal()]);
					count++;
				}
				stage = 32;
				break;
			case 32:
				final SplitBark bark = SplitBark.forButton(buttonId);
				if (bark == null) {
					end();
					return true;
				}

				if (!player.getInventory().contains(BARK.getId(), bark.getAmt())) {
					String name = bark == SplitBark.HELM ? "a splitbark helm" : bark == SplitBark.BODY ? "a splitbark body" : bark == SplitBark.BOOTS ? "splitbark boots" : bark == SplitBark.GAUNTLETS ? "splitbark gauntlets" : "splitbark legs";
					interpreter.sendDialogues(npc, null, "You need " + bark.getAmt() + " pieces of bark for " + name + ".");
					return true;
				}
				final int amount = getAmt(buttonId);
				if (amount == -1) {// rscript.
					player.getDialogueInterpreter().sendInput(false, "Enter amount:");
					player.setAttribute("runscript", new RunScript() {
						@Override
						public boolean handle() {
							final int amt = (int) getValue();
							make(bark, amt);
							return true;
						}
					});
					return true;
				}
				make(bark, amount);
				break;
			case 33:
				end();
				break;
			case 20:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Split-bark armour is special armour for mages, it's much", "more resistant to physical attacks than normal robes.", "It's actually very easy for me to make, but I've been", "having trouble getting hold of the pieces.");
				stage = 14;
				break;
			case 10:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I've been studying the practice of making split-bark", "armour.");
				stage = 11;
				break;
			case 11:
				interpreter.sendOptions("Select an Option", "Split-bark armour, what's that?", "Can you make me some?");
				stage = 12;
				break;
			case 12:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Split-bark armour, what's that?");
					stage = 13;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Can you make me some?");
					stage = 9000;
					break;
				}
				break;
			case 13:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Split-bark armour is special armour for mages, it's much", "more resistant to physical attacks than normal robes.", "It's actually very easy for me to make, but I've been", "having trouble getting hold of the pieces.");
				stage = 14;
				break;
			case 14:
				interpreter.sendOptions("Select an Option", "Well good luck with that.", "Can you make me some?");
				stage = 15;
				break;
			case 15:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Well good luck with that.");
					stage = 16;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Can you make me some?");
					stage = 9000;
					break;
				}
				break;
			case 16:
				end();
				break;
			case 9000:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I need bark from a hollow tree, and some fine cloth.", "Unfortunately both these items can be found in", "Morytania, especially the cloth which is found in the", "tombs of shades.");
				stage = 9001;
				break;
			case 9001:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Of course I'd happily sell you some at a discounted", "price if you bring me those items.");
				stage = 9002;
				break;
			case 9002:
				interpreter.sendOptions("Select an Option", "Ok, guess I'll go looking then!", "Ok, how much do I need?");
				stage = 9003;
				break;
			case 9003:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Ok, guess I'll go looking then!");
					stage = 9004;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Ok, how much do I need?");
					stage = 9005;
					break;
				}
				break;
			case 9004:
				end();
				break;
			case 9005:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "1 need 1 piece of each for either gloves or boots,", "2 pieces of each for a hat,", "3 pieces of each for leggings,", "and 4 pieces of each for a top.");
				stage = 9006;
				break;
			case 9006:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I'll charge you 1,000 coins for either gloves or boots,", "6,000 coins for a hat", "32,000 coins for leggings,", "and 37,000 for a top.");
				stage = 9007;
				break;
			case 9007:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Ok, guess I'll go looking then!");
				stage = 9008;
				break;
			case 9008:
				end();
				break;
			case 40:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 1263 };
		}

		/**
		 * Method used to make split bark.
		 * @param bark the bark.
		 * @param amount the amount.
		 */
		public final void make(final SplitBark bark, int amount) {
			final int barkAmt = player.getInventory().getAmount(BARK);
			if (barkAmt < amount * bark.getAmt()) {
				interpreter.sendDialogues(npc, null, "You don't have enough bark to make that many.");
				stage = 33;
				return;
			}
			if (amount < 1) {
				end();
				return;
			}
			if (player.getInventory().freeSlots() < amount) {
				interpreter.sendDialogues(player, null, "Sorry, I don't seem to have enough inventory space.");
				stage = 33;
				return;
			}
			if (!player.getInventory().contains(995, bark.getCost() * amount)) {
				interpreter.sendDialogues(player, null, "Sorry, I don't seem to have enough coins.");
				stage = 33;
				return;
			}
			final Item money = new Item(995, bark.getCost() * amount);
			final Item barkRemove = new Item(BARK.getId(), amount * bark.getAmt());
			if (player.getInventory().remove(money) && player.getInventory().remove(barkRemove)) {
				player.getInventory().add(new Item(bark.getItemId(), amount));
				interpreter.sendDialogues(npc, null, "There you go, enjoy your new armour!");
				return;
			}
			end();
		}

		/**
		 * Method used to get the amount to make.
		 * @param buttonId the button id.
		 * @return the amt.
		 */
		public final int getAmt(final int buttonId) {
			int amount = -1;
			switch (buttonId) {
			case 10:
			case 14:
			case 18:
			case 22:
			case 26:
				amount = 1;
				break;
			case 9:
			case 13:
			case 17:
			case 21:
			case 25:
				amount = 5;
				break;
			case 8:
			case 12:
			case 16:
			case 20:
			case 24:
				amount = 10;
				break;
			case 7:
			case 11:
			case 15:
			case 19:
			case 23:
				amount = -1;
				break;
			}
			return amount;
		}

		/**
		 * Represents the split bark item info.
		 * @author 'Vexia
		 * @date 21/11/2013
		 */
		public enum SplitBark {
			HELM(3385, 6000, 2, 9), BODY(3387, 37000, 4, 13), LEGS(3389, 32000, 3, 17), GAUNTLETS(3391, 1000, 1, 21), BOOTS(3393, 1000, 1, 25);

			/**
			 * Constructs a new {@code WizardDialogue.java} {@code Object}.
			 * @param itemId the item id.
			 * @param cost the cost.
			 * @param buttonId the button id.
			 */
			SplitBark(final int itemId, final int cost, final int amt, final int buttonId) {
				this.itemId = itemId;
				this.cost = cost;
				this.amt = amt;
				this.buttonId = buttonId;
			}

			/**
			 * Represents the item to give.
			 */
			private final int itemId;

			/**
			 * Represents the cost of the item.
			 */
			private final int cost;

			/**
			 * Represents the needed amt.
			 */
			private final int amt;

			/**
			 * Represents the button id.
			 */
			private final int buttonId;

			/**
			 * Gets the itemId.
			 * @return The itemId.
			 */
			public int getItemId() {
				return itemId;
			}

			/**
			 * Gets the cost.
			 * @return The cost.
			 */
			public int getCost() {
				return cost;
			}

			/**
			 * Gets the buttonId.
			 * @return The buttonId.
			 */
			public int getButtonId() {
				return buttonId;
			}

			/**
			 * Method used to get the splitbark armour.
			 * @param button the button.
			 * @return the split bark.
			 */
			public static SplitBark forButton(final int buttonId) {
				SplitBark bark = null;
				if (buttonId >= 7 && buttonId <= 10) {
					bark = HELM;
				}
				if (buttonId >= 11 && buttonId <= 14) {
					bark = BODY;
				}
				if (buttonId >= 15 && buttonId <= 18) {
					bark = LEGS;
				}
				if (buttonId >= 19 && buttonId <= 22) {
					bark = GAUNTLETS;
				}
				if (buttonId >= 23 && buttonId <= 26) {
					bark = BOOTS;
				}
				if (buttonId == 26) {
					bark = HELM;
				}
				return bark;
			}

			/**
			 * Gets the amt.
			 * @return The amt.
			 */
			public int getAmt() {
				return amt;
			}
		}
	}

	/**
	 * Handles the SedridorDialogue dialogue.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final static class SedridorDialogue extends DialoguePlugin {

		/**
		 * Represents the talisman item.
		 */
		private static final Item TALISMAN = new Item(1438);

		/**
		 * Represents the package item.
		 */
		private static final Item PACKAGE = new Item(290);

		/**
		 * Represents the notes item.
		 */
		private static final Item NOTES = new Item(291);

		/**
		 * Represents the graphic to use.
		 */
		private static final Graphics GRAPHIC = new Graphics(6);

		/**
		 * Constructs a new {@code SedridorDialogue} {@code Object}.
		 */
		public SedridorDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code SedridorDialogue} {@code Object}.
		 * @param player the player.
		 */
		public SedridorDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new SedridorDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Welcome adventurer, to the world renowned", "Wizards' Tower. How may I help you?");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			final Quest quest = player.getQuestRepository().getQuest("Rune Mysteries");
			switch (stage) {
			case 0:
				if (quest.getStage(player) == 100) {
					interpreter.sendDialogues(player, null, "Hello there.");
					stage = 400;
					return true;
				}
				if (quest.getStage(player) == 10) {
					interpreter.sendOptions("Select an Option", "Nothing thanks, I'm just looking around.", "What are you doing down here?", "I'm looking for the head wizard.");
					stage = 1000;
					return true;
				}
				if (quest.getStage(player) == 20) {
					interpreter.sendDialogues(npc, null, "Wow! This is... incredible!");
					stage = 52;
					return true;
				}
				if (quest.getStage(player) == 30) {
					interpreter.sendDialogues(npc, null, "Ah, " + player.getUsername() + ". How goes your quest? Have you", "delivered the research notes to my friend Aubury yet?");
					stage = 800;
					return true;
				}
				if (quest.getStage(player) == 50) {
					interpreter.sendDialogues(npc, null, "Ah, " + player.getUsername() + ". How goes your quest? Have you", "delivered the research notes to my friend Aubury yet?");
					stage = 900;
					return true;
				}
				interpreter.sendOptions("Select an Option", "Nothing thanks, I'm just looking around.", "What are you doing down here?");
				stage = 1;
				break;
			case 400:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Hello again " + player.getUsername() + ". What can I do for you?");
				stage = 401;
				break;
			case 401:
				interpreter.sendOptions("Select an Option", "Nothing thanks, I'm just looking around.", "Can you teleport me to the Rune Essence?");
				stage = 402;
				break;
			case 402:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Nothing thanks, I'm just looking around.");
					stage = 403;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Can you teleport me to the Rune Essence?");
					stage = 405;
					break;
				}
				break;
			case 403:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Well, take care adventurer. You stand on the", "ruins of the old destroyed Wizards' Tower.", "Strange and powerful magicks lurk here,");
				stage = 404;
				break;
			case 404:
				end();
				break;
			case 405:
				end();
				EssenceTeleport.teleport(npc, player);
				break;
			case 900:
				interpreter.sendDialogues(player, null, "Yes, I have. He gave me some research notes", "to pass on to you.");
				stage = 901;
				break;
			case 901:
				interpreter.sendDialogues(npc, null, "May I have his notes then?");
				stage = 902;
				break;
			case 902:
				if (!player.getInventory().containsItem(NOTES)) {
					interpreter.sendDialogues(player, null, "Uh... I kind of... lost them...");
					stage = 903;
					break;
				}
				interpreter.sendDialogues(player, null, "Sure. I have them here.");
				stage = 905;
				break;
			case 905:
				interpreter.sendDialogues(npc, null, "Well, before you hand them over to me, as you", "have been nothing but truthful with me to this point,", "and I admire that in an adventurer, I will let you", "into the secret of our research.");
				stage = 906;
				break;
			case 906:
				interpreter.sendDialogues(npc, null, "Now as you may or may not know, many", "centuries ago, the wizards at this Tower", "learnt the secret of creating Rune Stones, which", "allowed us to cast Magic very easily.");
				stage = 907;
				break;
			case 907:
				interpreter.sendDialogues(npc, null, "When this Tower was burnt down the secret of", "creating runes was lost ot us for all time... except it", "wasn't. Some months ago, while searching these ruins", "for information from the old days,");
				stage = 908;
				break;
			case 908:
				interpreter.sendDialogues(npc, null, "I came upon a scroll, almost destroyed, that detailed a", "magical rock deep in the icefields of the North, closed", "off from access by anything other than magical means.");
				stage = 909;
				break;
			case 909:
				interpreter.sendDialogues(npc, null, "This rock was called the 'Rune Essence' by the", "magicians who studied its power. Apparently, by simply", "breaking a chunk from it, a Rune Stone could be", "fashioned very quickly and easily at certain");
				stage = 910;
				break;
			case 910:
				interpreter.sendDialogues(npc, null, "elemental altars that were scattered across the land", "back then. Now, this is an interesting little piece of", "history, but not much use to us as modern wizards", "without access to the Rune Essence,");
				stage = 911;
				break;
			case 911:
				interpreter.sendDialogues(npc, null, "or these elemental altars. This is where you and", "Aubury come into this stroy. A few weeks back,", "Aubury discovered in a standard delivery of runes", "to his store, a parchment detailing a");
				stage = 912;
				break;
			case 912:
				interpreter.sendDialogues(npc, null, "teleportation spell that he had never come across", "before. To his shock, when cast it took him to a", "strange rock he had never encountered before...", "yet that he felt strangely famliar...");
				stage = 913;
				break;
			case 913:
				interpreter.sendDialogues(npc, null, "As I'm sure you have now guessed, he had discovered a", "portal leading to the mythical Rune Essence. As soon as", "he told me of this spell, I saw the importance of his find,");
				stage = 914;
				break;
			case 914:
				interpreter.sendDialogues(npc, null, "for it we could but find the elemental altars spoken", "of in the ancient texts, we would once more be able", "to create runes as our ancestors had done! It would", "be the saviour of the wizards' art!");
				stage = 915;
				break;
			case 915:
				interpreter.sendDialogues(player, null, "I'm still not sure how I fit into", "this little story of yours...");
				stage = 916;
				break;
			case 916:
				interpreter.sendDialogues(npc, null, "You haven't guessed? This talisman you brought me...", "it is the key to the elemental altar of air! When", "you hold it next, it will direct you towards");
				stage = 917;
				break;
			case 917:
				interpreter.sendDialogues(npc, null, "the entrance to the long forgotten Air Altar! By", "bringing pieces of the Rune Essence to the Air Temple,", "you will be able to fashion your own Air Runes!");
				stage = 918;
				break;
			case 918:
				interpreter.sendDialogues(npc, null, "And this is not all! By finding other talismans similar", "to this one, you will eventually be able to craft every", "rune that is available on this world! Just");
				stage = 919;
				break;
			case 919:
				interpreter.sendDialogues(npc, null, "as our ancestors did! I cannot stress enough what a", "find this is! Now, due to the risks involved of letting", "this mighty power fall into the wrong hands");
				stage = 920;
				break;
			case 920:
				interpreter.sendDialogues(npc, null, "I will keep the teleport skill to the Rune Essence", "a closely guarded secret, shared only by myself", "and those Magic users around the world", "whom I trust enough to keep it.");
				stage = 921;
				break;
			case 921:
				interpreter.sendDialogues(npc, null, "This means that if any evil power should discover", "the talismans required to enter the elemental", "temples, we will be able to prevent their access", "to the Rune Essence and prevent");
				stage = 922;
				break;
			case 922:
				interpreter.sendDialogues(npc, null, "tragedy befalling this world. I know not where the", "temples are located, nor do I know where the talismans", "have been scattered to in this land, but I now");
				stage = 923;
				break;
			case 923:
				interpreter.sendDialogues(npc, null, "return your Air Talisman to you. Find the Air", "Temple, and you will be able to charge your Rune", "Essences to become Air Runes at will. Any time");
				stage = 924;
				break;
			case 924:
				interpreter.sendDialogues(npc, null, "you wish to visit the Rune Essence, speak to me", "or Aubury and we will open a portal to that", "mystical place for you to visit.");
				stage = 925;
				break;
			case 925:
				interpreter.sendDialogues(player, null, "So only you and Aubury know the teleport", "spell to the Rune Essence?");
				stage = 926;
				break;
			case 926:
				interpreter.sendDialogues(npc, null, "No... there are others... whom I will tell of your", "authorisation to visit that place. When you speak", "to them, they will know you, and grant you", "access to that place when asked.");
				stage = 927;
				break;
			case 927:
				interpreter.sendDialogues(npc, null, "Use the Air Talisman to locate the air temple,", "and use any further talismans you find to locate", "the other missing elemental temples.", "Now... my research notes please?");
				stage = 928;
				break;
			case 928:
				interpreter.sendDialogue("You hand the head wizard the research notes.", "He hands you back the Air Talisman.");
				stage = 929;
				break;
			case 929:
				if (player.getInventory().remove(NOTES)) {
					if (!player.getInventory().add(TALISMAN)) {
						GroundItemManager.create(new GroundItem(TALISMAN, player.getLocation(), player));
					}
					quest.finish(player);
					player.getQuestRepository().syncronizeTab(player);
				}
				end();
				break;
			case 903:
				interpreter.sendDialogues(npc, null, "You did? You are extremely careless aren't you? I", "suggest you go and speak to Aubury once more, with", "luck he will have made copies of his research.");
				stage = 904;
				break;
			case 904:
				end();
				break;
			case 800:
				interpreter.sendDialogues(player, null, "Not yet...");
				stage = 801;
				break;
			case 801:
				if (!player.getInventory().containsItem(PACKAGE) && !player.getBank().containsItem(PACKAGE)) {
					interpreter.sendDialogues(player, null, "...I lost the package you gave me.");
					stage = 804;
					return true;
				}
				interpreter.sendDialogues(npc, null, "Well, please do so as soon as possible. Remember: to get", "to Varrock, head due North, through Draynor Village,", "around Draynor Manor, and then head East when");
				stage = 802;
				break;
			case 802:
				interpreter.sendDialogues(npc, null, "you get to the Barbarian village. The man you seek", "is named Aubury, and he owns the rune shop there.", "It is vital he receives this package.");
				stage = 803;
				break;
			case 803:
				end();
				break;
			case 804:
				interpreter.sendDialogues(npc, null, "You WHAT?");
				stage = 805;
				break;
			case 805:
				interpreter.sendDialogues(npc, null, "Tch, that was really very careless of you. Luckily as", "head wizard I have great powers, and will be able to", "teleport it back here without too much effort.");
				stage = 806;
				break;
			case 806:
				close();
				npc.graphics(GRAPHIC);
				player.lock(3);
				GameWorld.submit(new Pulse(2) {
					@Override
					public boolean pulse() {
						interpreter.sendDialogues(npc, null, "Ok, I have retrieved it. Luckily it doesn't appear to", "have been damaged. Now please take it to Aubury, ", "and try not to lose it again.");
						stage = 807;
						return true;
					}
				});
				break;
			case 807:
				if (!player.getInventory().add(PACKAGE)) {
					GroundItemManager.create(new GroundItem(PACKAGE, player.getLocation(), player));
				}
				end();
				break;
			case 1000:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Nothing thanks, I'm just looking around.");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What are you doing down here?");
					stage = 20;
					break;
				case 3:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I'm looking for the head wizard.");
					stage = 30;
					break;
				}
				break;
			case 30:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Oh, you are, are you?", "And just why would you be doing that?");
				stage = 31;
				break;
			case 31:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "The Duke of Lumbridge sent me to find him. I have", "this weird talisman he found. He said the head wizard", "would be very interested in it.");
				stage = 32;
				break;
			case 32:// ...except I don't have it with me..."
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Did he now? HmmmMMMMMmmmmm.", "Well that IS interesting. Hand it over then adventurer,", "let me see what all the hubbub about it is.", "Just come amulet I'll wager.");
				stage = 33;
				break;
			case 33:
				interpreter.sendOptions("Select an Option", "Ok, here you are.", "No, I'll only give it to the head wizard.");
				stage = 34;
				break;
			case 34:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Ok, here you are.");
					stage = 50;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No, I'll only give it to the head wizard.");
					stage = 40;
					break;
				}
				break;
			case 50:
				if (!player.getInventory().containsItem(TALISMAN)) {
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "...except I don't have it with me...");
					stage = 99;
					break;
				}
				interpreter.sendDialogue("You hand the Talisman to the wizard.");
				stage = 51;
				break;
			case 51:
				if (player.getInventory().remove(TALISMAN)) {
					quest.setStage(player, 20);
					interpreter.sendDialogues(npc, null, "Wow! This is... incredible!");
					stage = 52;
				}
				break;
			case 52:
				interpreter.sendDialogues(npc, null, "Th-this talisman you brought me...! It is the last piece", "of the puzzle, I think! Finally! The legacy of our", "ancestors... it will return to us once more!");
				stage = 53;
				break;
			case 53:
				interpreter.sendDialogues(npc, null, "I need time to study this, " + player.getUsername() + ". Can you please", "do me this task while I study this talisman you have", "brought me? In the mighty town of Varrock, which");
				stage = 54;
				break;
			case 54:
				interpreter.sendDialogues(npc, null, "is located North East of here, there is a certain shop", "that sells magical runes. I have in this package all of the", "research I have done relating to the Rune Stones, and");
				stage = 55;
				break;
			case 55:
				interpreter.sendDialogues(npc, null, "require somebody to take them to the shopkeeper so that", "he may share my research and offer me his insights.", "Do this thing for me, and bring back what he gives you,");
				stage = 56;
				break;
			case 56:
				interpreter.sendDialogues(npc, null, "and if my suspicions are correct, I will let you into the", "knowledge of one of the greatest secrets this world has", "ever known! A secret so powerful that is destroyed the");
				stage = 57;
				break;
			case 57:
				interpreter.sendDialogues(npc, null, "original Wizards' Tower all of those centuries", "ago! My research, combined with this mysterious", "talisman... I cannot believe the answer", "the mysteries is so close now!");
				stage = 58;
				break;
			case 58:
				interpreter.sendDialogues(npc, null, "Do this thing for me " + player.getUsername() + ". Be rewarded in a", "way you can never imagine.");
				stage = 59;
				break;
			case 59:
				interpreter.sendOptions("Select an Option", "Yes, certainly.", "No, I'm busy.");
				stage = 60;
				break;
			case 60:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, null, "Yes, certainly.");
					stage = 70;
					break;
				case 2:
					interpreter.sendDialogues(player, null, "No, I'm busy.");
					stage = 61;
					break;
				}
				break;
			case 70:
				interpreter.sendDialogues(npc, null, "Take this package, and head directly North", "from here, through Draynor village, until you reach", "the Barbarian Village. Then head East from there", "until you reach Varrock.");
				stage = 71;
				break;
			case 71:
				interpreter.sendDialogues(npc, null, "Once in Varrock, take this package to the owner of the", "rune shop. His name is Aubury. You may find it", "helpful to ask one of Varrock's citizens for directions,");
				stage = 72;
				break;
			case 72:
				interpreter.sendDialogues(npc, null, "as Varrock can be a confusing place for the first time", "visitor. He will give you a special item - bring it back to", "me, and I shall show you the mystery of the runes...");
				stage = 73;
				break;
			case 73:
				interpreter.sendDialogue("The head wizard gives you a package.");
				stage = 74;
				break;
			case 74:
				quest.setStage(player, 30);
				interpreter.sendDialogues(npc, null, "Best of luck with your quest, " + player.getUsername() + ".");
				if (!player.getInventory().add(PACKAGE)) {
					GroundItemManager.create(new GroundItem(PACKAGE, player.getLocation(), player));
				}
				stage = 75;
				break;
			case 75:
				end();
				break;
			case 61:
				interpreter.sendDialogues(npc, null, "As you wish adventurer. I will continue to study this", "talisman you have brought me. Return here if you find", "yourself with some spare time to help me.");
				stage = 62;
				break;
			case 62:
				end();
				break;
			case 99:
				end();
				break;
			case 40:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "HA HA HA HA HA! I can tell you are new to this", "land, for I AM the head wizard! Hand it over and", "let me have a proper look at it, hmmm?");
				stage = 41;
				break;
			case 41:
				interpreter.sendOptions("Select an Option", "Ok, here you are.", "No, I'll only give it to the head wizard.");
				stage = 34;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Nothing thanks, I'm just looking around.");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What are you doing down here?");
					stage = 20;
					break;
				}
				break;
			case 10:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Well, take care adventurer. You stand on the", "ruins of the destroyed Wizards' Tower.", "Strange and powerful magicks lurk here.");
				stage = 11;
				break;
			case 11:
				end();
				break;
			case 20:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "That is indeed a good question. Here in the cellar", "of the Wizards' Tower you find the remains of", "the old Wizards' Tower, destroyed by fire");
				stage = 21;
				break;
			case 21:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "many years past by the treachery of the Zamorakians.", "Many mytseries were lost, which we try to find once", "more. By building this tower on the remains of the old,");
				stage = 22;
				break;
			case 22:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "we sought to show the world of our dedication to", "learning the mysteries of Magic. I am here searching", "through these fragments for knowledge from", "the artefacts from our past.");
				stage = 23;
				break;
			case 23:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "And have you found anything useful?");
				stage = 24;
				break;
			case 24:
				interpreter.sendDialogues(npc, null, "Aaah... that would be telling adventurer. Anything I", "have found I cannot speak freely of, for fear the", "treachery of the past might be repeated.");
				stage = 25;
				break;
			case 25:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Ok, well I'll leave you to it.");
				stage = 26;
				break;
			case 26:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Perhaphs I will see you later " + player.getUsername() + ".");
				stage = 27;
				break;
			case 27:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "How did you know my name???");
				stage = 28;
				break;
			case 28:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Well, I AM the head wizard here...");
				stage = 29;
				break;
			case 29:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 300 };
		}
	}

	/**
	 * Represents the dialogue plugin used for aubury.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class AuburyDialoguePlugin extends DialoguePlugin {

		/**
		 * Represents the package item.
		 */
		private static final Item PACKAGE = new Item(290);

		/**
		 * Represents the package item.
		 */
		private static final Item NOTES = new Item(291);

		/**
		 * The NPC ids that use this dialogue plugin.
		 */
		private static final int[] NPC_IDS = { 553 };

		/**
		 * Constructs a new {@code AuburyDialoguePlugin} {@code Object}.
		 */
		public AuburyDialoguePlugin() {
			/*
			 * empty.
			 */
		}

		public AuburyDialoguePlugin(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new AuburyDialoguePlugin(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			final Quest quest = player.getQuestRepository().getQuest("Rune Mysteries");
			if (quest.getStage(player) == 40) {
				interpreter.sendDialogues(npc, null, "My gratitude to you adventurer for bringing me these", "research notes. I notice that you brought the head", "wizard a speical talisman that was the key to our finally", "unlocking the puzzle.");
				stage = 900;
				return true;
			}
			if (Skillcape.isMaster(player, Skills.RUNECRAFTING)) {
				options("Can I buy a Skillcape of Runecrafting?", "Something else");
				stage = 450;
			} else {
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Do you want to buy some runes?");
			}
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			final Quest quest = player.getQuestRepository().getQuest("Rune Mysteries");
			switch (stage) {
			case 0:
				if (quest.getStage(player) == 30) {
					interpreter.sendOptions("Select an Option", "Yes please!", "Oh, it's a rune shop. No thank you, then.", "I have been sent here with a package for you.");
					stage = 800;
					return true;
				}
				if (quest.getStage(player) == 40) {
					interpreter.sendDialogues(npc, null, "My gratitude to you adventurer for bringing me these", "research notes. I notice that you brought the head", "wizard a speical talisman that was the key to our finally", "unlocking the puzzle.");
					stage = 900;
					return true;
				}
				if (quest.getStage(player) == 50) {
					interpreter.sendDialogues(npc, null, "I suggest you take those research notes of mine back", "to the head wizard at the Wizards' Tower.");
					stage = 950;
					return true;
				}
				if (!player.getQuestRepository().isComplete("Rune Mysteries")) {
					interpreter.sendOptions("Select an Option", "Yes please!", "Oh, it's a rune shop. No thank you, then.");
					stage = 100;
				} else {
					interpreter.sendOptions("Choose an option:", "Yes, please.", "No thanks.", "Can you teleport me to the rune essence?");
					stage = 1;
				}
				break;
			case 900:// quest
				interpreter.sendDialogues(npc, null, "Combined with the information I had already collated", "regarding the Rune Essence, I think we have finally", "unlocked the power to");
				stage = 901;
				break;
			case 901:
				interpreter.sendDialogues(npc, null, "...no. I am getting ahead of myself. Please take this", "summary of my research back to the head wizard at", "the Wizards' Tower. I trust his judgement on whether", "to let you in on our little secret or not.");
				stage = 902;
				break;
			case 902:
				quest.setStage(player, 50);
				if (!player.getInventory().add(NOTES)) {
					GroundItemManager.create(new GroundItem(NOTES, player.getLocation(), player));
				}
				interpreter.sendDialogue("Aubury gives you his research notes.");
				stage = 903;
				break;
			case 903:
				end();
				break;
			case 950:
				if (!player.getBank().containsItem(NOTES) && !player.getInventory().containsItem(NOTES)) {
					interpreter.sendDialogues(player, null, "I can't... I lost them...");
					stage = 955;
					return true;
				}
				interpreter.sendDialogues(player, null, "Ok then, I will do that.");
				stage = 951;
				break;
			case 951:
				interpreter.sendDialogues(npc, null, "Unless you were talking to me because you wished to", "buy some runes?");
				stage = 952;
				break;
			case 952:
				interpreter.sendOptions("Select an Option", "Yes please!", "Oh, it's a rune shop. No thank you, then.");
				stage = 100;
				break;
			case 955:
				interpreter.sendDialogues(npc, null, "Well, luckily I have duplicates. It's a good thing they", "are written in code, I would not want the wrong kind", "of person to get access to the information contained", "within.");
				stage = 956;
				break;
			case 956:
				if (!player.getInventory().add(NOTES)) {
					GroundItemManager.create(new GroundItem(NOTES, player.getLocation(), player));
				}
				interpreter.sendDialogue("Aubury gives you his research notes.");
				stage = 957;
				break;
			case 957:
				end();
				break;
			case 800:// quest
				switch (buttonId) {
				case 1:
					npc.openShop(player);
					end();
					break;
				case 2:
					interpreter.sendDialogues(player, null, "Oh, it's a rune shop. No thank you, then.");
					stage = 105;
					break;
				case 3:
					interpreter.sendDialogues(player, null, "I have been sent here with a package for you. It's from ", "the head wizard at the Wizards' Tower.");
					stage = 801;
					break;
				}
				break;
			case 801:
				interpreter.sendDialogues(player, null, "I have been sent here with a package for you. It's from ", "the head wizard at the Wizards' Tower.");
				stage = 802;
				break;
			case 802:
				interpreter.sendDialogues(npc, null, "Really? But... surely he can't  have..? Please, let me", "have it, it must be extremely important for him to have", "sent a stranger.");
				stage = 803;
				break;
			case 803:
				if (!player.getInventory().containsItem(PACKAGE)) {
					interpreter.sendDialogues(player, null, "Uh... yeah... about that... I kind of don't have it with", "me...");
					stage = 804;
					return true;
				}
				interpreter.sendDialogue("You hand Aubury the research package.");
				stage = 807;
				break;
			case 804:
				interpreter.sendDialogues(npc, null, "What kind of person tells me theyhave a delivery for", "me, but not with them? Honestly.");
				stage = 805;
				break;
			case 805:
				interpreter.sendDialogues(npc, null, "Come back when you do.");
				stage = 806;
				break;
			case 806:
				end();
				break;
			case 807:
				if (player.getInventory().remove(PACKAGE)) {
					quest.setStage(player, 40);
					interpreter.sendDialogues(npc, null, "This... this is incredible. Please, give me a few moments", "to quickly look over this, and then talk to me again.");
					stage = 808;
				}
				break;
			case 808:
				end();
				break;
			case 1:
				switch (interfaceId) {
				case 230:
					switch (buttonId) {
					case 1:
						npc.openShop(player);
						end();
						break;
					case 2:
						interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No thanks.");
						stage = 10;
						break;
					case 3:
						interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Can you teleport me to the rune essence?");
						stage = 11;
						break;
					}
				}
				break;
			case 100:
				switch (buttonId) {
				case 1:
					npc.openShop(player);
					end();
					break;
				case 2:
					interpreter.sendDialogues(player, null, "Oh, it's a rune shop. No thank you, then.");
					stage = 105;
					break;
				}
				break;
			case 105:
				end();
				break;
			case 106:
				end();
				break;
			case 10:
				end();
				break;
			case 11:
				EssenceTeleport.teleport(npc, player);
				end();
				break;
			case 450:
				switch (buttonId) {
				case 1:
					player("Can I buy a Skillcape of Runecrafting?");
					stage = 2;
					break;
				case 2:
					interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Do you want to buy some runes?");
					stage = 0;
					break;
				}
				break;
			case 2:
				npc("Certainly! Right when you give me 99000 coins.");
				stage = 3;
				break;
			case 3:
				options("Okay, here you go.", "No");
				stage = 4;
				break;
			case 4:
				switch (buttonId) {
				case 1:
					player("Okay, here you go.");
					stage = 5;
					break;
				case 2:
					end();
					break;
				}
				break;
			case 5:
				if (Skillcape.purchase(player, Skills.RUNECRAFTING)) {
					npc("There you go! Enjoy.");
				}
				stage = 6;
				break;
			case 6:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return NPC_IDS;
		}
	}

}
