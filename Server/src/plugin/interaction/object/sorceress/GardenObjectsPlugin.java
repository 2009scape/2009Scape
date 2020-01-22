package plugin.interaction.object.sorceress;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.component.Component;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.LocationLogoutTask;
import org.crandor.game.system.task.LogoutTask;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.MinimapStateContext;
import org.crandor.net.packet.out.MinimapState;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Represents the plugin used for the garden object plugin.
 * @author SonicForce41
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class GardenObjectsPlugin extends OptionHandler {

	/**
	 * Represents the animation to use.
	 */
	private static final Animation ANIMATION = new Animation(827);

	/**
	 * Represents the herbs items used for the elemental garden picking.
	 */
	private static final int[] HERBS = { 199, 201, 203, 205, 207, 209, 211, 213, 215, 217, 219, 2485, 3049, 3051, 199, 201, 203, 205 };

	/**
	 * Represents the drinking animation.
	 */
	private static final Animation DRINK_ANIM = new Animation(5796);

	/**
	 * Represents the teleport anim.
	 */
	private static final Animation TELE = new Animation(714);

	/**
	 * Represents the graphics to use.
	 */
	private static final Graphics GRAPHICS = new Graphics(111, 100, 1);

	/**
	 * Represents the picking fruit anim.
	 */
	private static final Animation PICK_FRUIT = new Animation(2280);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.setOptionHandler("pick-fruit", this);
		ObjectDefinition.setOptionHandler("drink-from", this);
		ObjectDefinition.forId(21794).getConfigurations().put("option:search", this);
		for (HerbDefinition h : HerbDefinition.values()) {
			ObjectDefinition.forId(h.getId()).getConfigurations().put("option:pick", this);
		}
		new SqirkJuicePlugin().newInstance(arg);
		new OsmanDialogue().init();
		new SqirkMakingDialogue().init();
		PluginManager.definePlugin(new SorceressGardenObject());
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		GameObject object = (GameObject) node;
		if (option.equals("pick")) {
			final HerbDefinition herbDef = HerbDefinition.forId(object.getId());
			if (herbDef != null) {
				handleElementalGarden(player, object, herbDef);
				return true;
			}
		} else if (option.equals("search")) {
			if (object.getId() == 21794) {
				if (player.getInventory().freeSlots() < 1) {
					player.sendMessage("You don't have enough space in your inventory to take a beer glass.");
				} else {
					player.sendMessage("You take an empty beer glass off the shelves.");
					player.getInventory().add(new Item(1919, 1));
				}
			}
		} else if (option.equals("pick-fruit")) {
			final SeasonDefinitions def = SeasonDefinitions.forTreeId(object.getId());
			if (def != null) {
				player.lock();
				player.addExtension(LogoutTask.class, new LocationLogoutTask(99, def.getRespawn()));
				player.animate(PICK_FRUIT);
				player.getSkills().addExperience(Skills.THIEVING, def.getExp(), true);
				player.getSkills().addExperience(Skills.FARMING, def.getFarmExp(), true);
				GameWorld.submit(new Pulse(2, player) {
					int delay = 0;

					@Override
					public boolean pulse() {
						if (delay == 1) {
							player.getInventory().add(new Item(def.getFruitId()));
							player.getInterfaceManager().openOverlay(new Component(115));
							PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
						} else if (delay == 3)
							player.getProperties().setTeleportLocation(def.getRespawn());
						else if (delay == 4) {
							player.unlock();
							player.removeExtension(LogoutTask.class);
							player.getPacketDispatch().sendMessage("An elemental force enamating from the garden teleports you away.");
							PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
							player.getInterfaceManager().close();
							player.getInterfaceManager().closeOverlay();
							player.unlock();
							return true;
						}
						delay++;
						return false;
					}
				});
			}
		} else if (option.equals("drink-from")) {
			player.lock();
			GameWorld.submit(new Pulse(1, player) {
				int counter = 0;

				@Override
				public boolean pulse() {
					switch (counter++) {
					case 1:
						player.animate(DRINK_ANIM);
						break;
					case 4:
						player.graphics(GRAPHICS);
						break;
					case 5:
						player.animate(TELE);
						break;
					case 6:
						player.getInterfaceManager().openOverlay(new Component(115));
						break;
					case 7:
						PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
						break;
					case 9:
						player.getProperties().setTeleportLocation(new Location(3321, 3141, 0));
						break;
					case 11:
						player.unlock();
						player.animate(new Animation(-1));
						player.getInterfaceManager().close();
						player.getInterfaceManager().closeOverlay();
						PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
						return true;
					}
					return false;
				}
			});
		}
		return true;
	}

    /**
     * Method used to handle the elemental garden picking.
     * @param player the player.
     * @param object the object.
     * @param herbDef the herbdef.
     */
    private void handleElementalGarden(final Player player, GameObject object, final HerbDefinition herbDef) {
	player.lock();
	player.addExtension(LogoutTask.class, new LocationLogoutTask(99, herbDef.getRespawn()));
	player.animate(ANIMATION);
	player.getSkills().addExperience(Skills.FARMING, herbDef.getExp(), true);
	GameWorld.submit(new Pulse(2, player) {
	    int delay = 0;

	    @Override
	    public boolean pulse() {
		if (delay == 1) {
		    player.getInventory().add(new Item(HERBS[RandomFunction.random(0, HERBS.length - 1)], + (1)));
		    player.getInventory().add(new Item(HERBS[RandomFunction.random(0, HERBS.length - 1)], + (1)));
		    player.getPacketDispatch().sendMessage("You pick up a herb.");
			player.getInterfaceManager().openOverlay(new Component(115));
		    PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
		} else if (delay == 3)
		    player.getProperties().setTeleportLocation(Location.create(herbDef.getRespawn()));
		else if (delay == 4) {
		    player.unlock();
		    player.getPacketDispatch().sendMessage("An elemental force emnating from the garden teleports you away.");
		    PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
		    player.getInterfaceManager().close();
			player.getInterfaceManager().closeOverlay();
		    player.removeExtension(LogoutTask.class);
		    player.unlock();
		    return true;
		}
		delay++;
		return false;
	    }
	});
    }

	/**
	 * Represents the herb definitions.
	 * @author SonicForce41
	 * @version 1.0
	 */
	public enum HerbDefinition {
		WINTER(21671, 30, new Location(2907, 5470, 0)), SPRING(21668, 40, new Location(2916, 5473, 0)), AUTUMN(21670, 50, new Location(2913, 5467, 0)), SUMMER(21669, 60, new Location(2910, 5476, 0));

		/**
		 * Represents the id.
		 */
		private int id;

		/**
		 * Represents the experience.
		 */
		private double exp;

		/**
		 * Represents the respawn location.
		 */
		private Location respawn;

		/**
		 * Constructs a new {@code GardenObjectsPlugin} {@code Object}.
		 * @param id the id.
		 * @param exp the exp.
		 * @param respawn the respawn.
		 */
		HerbDefinition(int id, double exp, Location respawn) {
			this.id = id;
			this.exp = exp;
			this.respawn = respawn;
		}

		/**
		 * Gets the exp
		 * @return the Exp
		 */
		public double getExp() {
			return exp;
		}

		/**
		 * Gets the Id
		 * @return the Id
		 */
		public int getId() {
			return id;
		}

		/**
		 * Gets the respawn Location
		 * @return the Location.
		 */
		public Location getRespawn() {
			return respawn;
		}

		/**
		 * Gets the herb definition by the id.
		 * @param id the objectId
		 * @return the definition.
		 */
		public static HerbDefinition forId(int id) {
			for (HerbDefinition def : HerbDefinition.values()) {
				if (def.getId() == id) {
					return def;
				}
			}
			return null;
		}
	}

	/**
	 * Represents the season definitions.
	 * @author SonicForce41
	 * @version 1.0
	 */
	public enum SeasonDefinitions {
		WINTER(21769, 1, 30, 70.0, 10847, 10851, 5, 0, 10, 350, 21709, new Location(2907, 5470, 0)), SPRING(21767, 25, 40, 337.5, 10844, 10848, 4, 1, 20, 1350, 21753, new Location(2916, 5473, 0)), AUTUMN(21768, 45, 50, 783.3, 10846, 10850, 3, 2, 30, 2350, 21731, new Location(2913, 5467, 0)), SUMMER(21766, 65, 60, 1500, 10845, 10849, 2, 3, 40, 3000, 21687, new Location(2910, 5476, 0));

		/**
		 * The treeId
		 */
		private int treeId;

		/**
		 * The level
		 */
		private int level;

		/**
		 * The farming experience recieved from picking
		 */
		private double farmExp;

		/**
		 * The thieving experience recieved from picking
		 */
		private double treeExp;

		/**
		 * The fruitId id
		 */
		private int fruitId;

		/**
		 * The juice id
		 */
		private int juiceId;

		/**
		 * The fruit amt
		 */
		private int fruitAmt;

		/**
		 * The boost
		 */
		private int boost;

		/**
		 * The energy
		 */
		private int energy;

		/**
		 * The experience recieved from osman
		 */
		private double osmanExp;

		/**
		 * The gate id
		 */
		private int gateId;

		/**
		 * The respawn location
		 */
		private Location respawn;

		/**
		 * Constructs a new {@code GardenObjectsPlugin.java} {@code Object}.
		 * @param treeId the tree id.
		 * @param level the level.
		 * @param farmExp the farm exp.
		 * @param treeExp the tree exp.
		 * @param fruitId the fruit id.
		 * @param juiceId the juice id.
		 * @param fruitAmt the fruit amt.
		 * @param boost the boost.
		 * @param energy the energy.
		 * @param osmanExp the osman exp.
		 * @param gateId the gate id.
		 * @param respawn the respawn.
		 */
		SeasonDefinitions(int treeId, int level, double farmExp, double treeExp, int fruitId, int juiceId, int fruitAmt, int boost, int energy, double osmanExp, int gateId, Location respawn) {
			this.treeId = treeId;
			this.level = level;
			this.farmExp = farmExp;
			this.treeExp = treeExp;
			this.fruitId = fruitId;
			this.juiceId = juiceId;
			this.fruitAmt = fruitAmt;
			this.boost = boost;
			this.energy = energy;
			this.osmanExp = osmanExp;
			this.gateId = gateId;
			this.respawn = respawn;
		}

		/**
		 * Gets the theiving boost
		 * @return the boost
		 */
		public int getBoost() {
			return boost;
		}

		/**
		 * Gets the run energy restoring
		 * @return the energy
		 */
		public int getEnergy() {
			return energy;
		}

		/**
		 * Gets the exp from tree
		 * @return the treeExp
		 */
		public double getExp() {
			return treeExp;
		}

		/**
		 * Gets the farmExp
		 * @return the farmExp
		 */
		public double getFarmExp() {
			return farmExp;
		}

		/**
		 * Gets the fruid amt
		 * @return the fruitAmt
		 */
		public int getFruitAmt() {
			return fruitAmt;
		}

		/**
		 * Gets the fruit Id
		 * @return the fruitId
		 */
		public int getFruitId() {
			return fruitId;
		}

		/**
		 * Gets the gate Id
		 * @return the gateId
		 */
		public int getGateId() {
			return gateId;
		}

		/**
		 * Gets the juice Id
		 * @return the juiceId
		 */
		public int getJuiceId() {
			return juiceId;
		}

		/**
		 * Gets the level
		 * @return the Level
		 */
		public int getLevel() {
			return level;
		}

		/**
		 * Gets the experience recieved from osman
		 * @return the osmanExp
		 */
		public double getOsmanExp() {
			return osmanExp;
		}

		/**
		 * Gets the respawn location
		 * @return the respawn Location
		 */
		public Location getRespawn() {
			return respawn;
		}

		/**
		 * Gets the treeId
		 * @return the treeId
		 */
		public int getTreeId() {
			return treeId;
		}

		/**
		 * Gets the def by the fruit id.
		 * @param fruitId the fruit id.
		 * @return the definition.
		 */
		public static SeasonDefinitions forFruitId(int fruitId) {
			for (SeasonDefinitions def : SeasonDefinitions.values()) {
				if (def == null)
					continue;
				if (fruitId == def.getFruitId())
					return def;
			}
			return null;
		}

		/**
		 * Gets the def by the gate Id.
		 * @param gateId the gateId
		 * @return the def.
		 */
		public static SeasonDefinitions forGateId(int gateId) {
			for (SeasonDefinitions def : SeasonDefinitions.values()) {
				if (gateId == def.getGateId())
					return def;
			}
			return null;
		}

		/**
		 * Gets the def by the juice id.
		 * @param juiceId the juice id.
		 * @return the def.
		 */
		public static SeasonDefinitions forJuiceId(int juiceId) {
			for (SeasonDefinitions def : SeasonDefinitions.values()) {
				if (def == null)
					continue;
				if (juiceId == def.getJuiceId())
					return def;
			}
			return null;
		}

		/**
		 * Gets the season def by the tree id.
		 * @param treeId the tree id.
		 * @return the def.
		 */
		public static SeasonDefinitions forTreeId(int treeId) {
			for (SeasonDefinitions def : SeasonDefinitions.values()) {
				if (def == null)
					continue;
				if (treeId == def.getTreeId())
					return def;
			}
			return null;
		}
	}

	/**
	 * Use with Plugin for Sq'irk Juice making
	 * @author SonicForce41
	 */
	public static final class SqirkJuicePlugin extends UseWithHandler {

		/**
		 * The crushing an item with pestle and mortar animation.
		 */
		private static final Animation CRUSH_ITEM = new Animation(364);

		public SqirkJuicePlugin() {
			super(10844, 10845, 10846, 10847);
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			Item item = event.getUsedItem();
			Item with = event.getBaseItem();
			Player player = event.getPlayer();
			SeasonDefinitions def = SeasonDefinitions.forFruitId(item.getId());
			if (item == null || with == null || player == null || def == null)
				return true;
			int amt = player.getInventory().getAmount(item);
			if (!player.getInventory().containItems(1919)) {
				player.getDialogueInterpreter().open(43382, 0);
				return true;
			}
			if (amt < def.getFruitAmt()) {
				player.getDialogueInterpreter().open(43382, 1, item.getId());
				return true;
			}
			player.animate(CRUSH_ITEM);
			player.getSkills().addExperience(Skills.COOKING, 5, true);
			player.getInventory().remove(new Item(item.getId(), def.getFruitAmt()));
			player.getInventory().remove(new Item(1919));
			player.getInventory().add(new Item(def.getJuiceId()));
			player.getDialogueInterpreter().sendDialogue("You squeeze " + def.getFruitAmt() + " sq'irks into an empty glass.");
			return true;
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			addHandler(233, ITEM_TYPE, this);
			return this;
		}

	}

	/**
	 * Represents the dialougue of the osman NPC.
	 * @author 'Vexia
	 * @author SonicForce41
	 * @date 31/12/2013
	 */
	public static final class OsmanDialogue extends DialoguePlugin {

		/**
		 * Represents the key print item.
		 */
		private static final Item KEY_PRINT = new Item(2423);

		/**
		 * Represents the bronze bar item.
		 */
		private static final Item BRONZE_BAR = new Item(2349);

		/**
		 * Represents the rope item.
		 */
		private static final Item ROPE = new Item(954);

		/**
		 * Represents the pink skirt item.
		 */
		private static final Item SKIRT = new Item(1013);

		/**
		 * Represents the yellow wig item.
		 */
		private static final Item YELLOW_WIG = new Item(2419);

		/**
		 * Represents the skin paste item.
		 */
		private static final Item PASTE = new Item(2424);

		/**
		 * Represents the juices.
		 */
		private static final int[] JUICES = new int[] { 10848, 10849, 10850, 10851 };

		/**
		 * Represents the fruits.
		 */
		private static final int[] FRUITS = new int[] { 10844, 10845, 10846, 10847 };

		/**
		 * Represents the quest instance.
		 */
		private Quest quest;

		/**
		 * Represents the count of materials you have gathered.
		 */
		private int itemCount;

		/**
		 * Constructs a new {@code OsmanDialogue} {@code Object}.
		 */
		public OsmanDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code OsmanDialogue} {@code Object}.
		 * @param player the Player
		 */
		public OsmanDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new OsmanDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			quest = player.getQuestRepository().getQuest("Prince Ali Rescue");
			switch (quest.getStage(player)) {
			case 100:
				interpreter.sendDialogues(player, null, "I'd like to talk about sq'irks.");
				stage = 0;
				return true;
			case 60:
				interpreter.sendDialogues(npc, null, "The prince is safe on his way home with Leela.", "You can pick up your payment from the chancellor.");
				stage = 0;
				return true;
			case 40:
			case 50:
				interpreter.sendDialogues(npc, null, "Speak to Leela for any further instructions.");
				stage = 0;
				break;
			case 30:
				interpreter.sendDialogues(player, null, "Can you tell me what I still need to get?");
				stage = 0;
				break;
			case 20:
				if (!player.getInventory().containsItem(KEY_PRINT)) {
					interpreter.sendDialogues(player, null, "Can you tell me what I need to get?");
				} else if (!player.getInventory().containsItem(BRONZE_BAR) && player.getInventory().containsItem(KEY_PRINT)) {
					interpreter.sendDialogues(npc, null, "Good, you have the print of the key. Get a bar of", "bronze, too, and I can get the key mad.");
					stage = 70;
				} else {
					interpreter.sendDialogues(npc, null, "Well done; we can make the key now.");
					stage = 80;
				}
				return true;
			case 10:
				interpreter.sendDialogues(player, null, "The chancellor trusts me. I have come for instructions.");
				break;
			case 0:
				interpreter.sendDialogues(player, null, "I'd like to talk about sq'irks.");
				break;
			default:
				break;
			}
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (quest.getStage(player)) {
			case 100:
				switch (stage) {
				default:
					handleSqirks(buttonId);
					break;
				}
				break;
			case 60:
				end();
				break;
			case 40:
			case 50:
				end();
				break;
			case 30:
				switch (stage) {
				case 82:
					end();
					break;
				case 0:
					interpreter.sendDialogues(npc, null, "You can collect the key from Leela.");
					stage = 11;
					break;
				case 11:
					if (player.getInventory().containsItem(YELLOW_WIG)) {
						interpreter.sendDialogues(npc, null, "The wig you have got, well done.");
						itemCount++;
					} else {
						interpreter.sendDialogues(npc, null, "You need a wig, maybe made from wool. If you find", "someone who can work with wool ask them about it.", "There's a witch nearby may be able to help you dye it.");
					}
					stage = 12;
					break;
				case 12:
					if (player.getInventory().containsItem(SKIRT)) {
						interpreter.sendDialogues(npc, null, "You have got the skirt, good.");
						itemCount++;
					} else {
						interpreter.sendDialogues(npc, null, "You will need to get a pink skirt, same as Keli's.");
					}
					stage = 13;
					break;
				case 13:
					if (player.getInventory().containsItem(PASTE)) {
						interpreter.sendDialogues(npc, null, "You have the skin paint, well done. I thought you would", "struggle to make that.");
						itemCount++;
					} else {
						interpreter.sendDialogues(npc, null, "We still need something to colour the Prince's skin", "lighter. There's a witch close to here. She knows about", "many things. She may know some way to make the", "skin lighter.");
					}
					stage = itemCount == 3 ? 14 : 15;
					break;
				case 14:
					interpreter.sendDialogues(npc, null, "You do have everything for the disguise.");
					stage = 15;
					break;
				case 15:
					if (player.getInventory().containsItem(ROPE)) {
						interpreter.sendDialogues(npc, null, "You have the rope I see, to tie up Keli. That will be the", "most dangerous part of the plan.");
						stage = 16;
					} else {
						interpreter.sendDialogues(npc, null, "You will still need some rope to tie up Keli, of course. I", "heard that there's a good rope maker around here.");
						stage = 16;
					}
					break;
				case 16:
					end();
					break;
				case 20:
					interpreter.sendDialogues(npc, null, "Yes, that is most important. There is no way you can", "get the real key. It is on a chain around Keli's neck.", "Almost impossible to steal.");
					stage = 21;
					break;
				case 21:
					end();
					break;
				}
				break;
			case 0:
				switch (stage) {
				default:
					handleSqirks(buttonId);
					break;
				}
				break;
			case 20:
				switch (stage) {
				case 80:
					interpreter.sendDialogue("Osman takes the key imprint and the bronze bar.");
					stage = 81;
					break;
				case 81:
					if (player.getInventory().remove(BRONZE_BAR) && player.getInventory().remove(KEY_PRINT)) {
						interpreter.sendDialogues(npc, null, "Pick the key up from Leela.");
						quest.setStage(player, 30);
						stage = 82;
					}
					break;
				case 82:
					end();
					break;
				case 70:
					interpreter.sendDialogues(player, null, "I will get one and come back.");
					stage = 71;
					break;
				case 71:
					end();
					break;
				case 0:
					interpreter.sendDialogues(npc, null, "A print of the key in soft clay and a bronzr bar.", "Then, collect tthe key from Leela.");
					stage = 1;
					break;
				case 1:
					interpreter.sendDialogues(npc, null, "When you have the full disguise talk to", "Leela and she will help you with the rest.");
					stage = 2;
					break;
				case 2:
					end();
					break;
				case 10:
					interpreter.sendDialogues(npc, null, "The prince is guarded by some stupid guards and a", "clever woman. The woman is our only way to get the", "prince out. Only she can walk freely about the area.");
					stage = 11;
					break;
				case 11:
					interpreter.sendDialogues(npc, null, "I think you will need to tie her up. One coil of rope", "should do for that. Then, disguise the prince as her to", "get him out without suspicion.");
					stage = 12;
					break;
				case 12:
					interpreter.sendDialogues(player, null, "How good must the disguise be?");
					stage = 13;
					break;
				case 13:
					interpreter.sendDialogues(npc, null, "Only enough to fool the guards at a distance. Get a", "skirt like hers. Same colour, same style. We will only", "have a short time.");
					stage = 14;
					break;
				case 14:
					interpreter.sendDialogues(npc, null, "Get a blonde wig, too. that is up to you to make or", "find. Something to colour the skin of the prince.");
					stage = 15;
					break;
				case 15:
					interpreter.sendDialogues(npc, null, "My daughter and top spy, Leela, can help you. She has", "sent word that she has discovered where they are", "keeping the prince.");
					stage = 16;
					break;
				case 16:
					interpreter.sendDialogues(npc, null, "It's near Draynor Village. She is lurking somewhere", "near there now.");
					stage = 17;
					break;
				case 17:
					quest.setStage(player, 20);
					interpreter.sendOptions("Select an Option", "Explain the first thing again.", "What is the second thing you need?", "Okay, I better go find some things.");
					stage = 50;
					break;
				case 20:
					interpreter.sendDialogues(npc, null, "We need the key, or we need a copy made. If you can", "get some soft clay then you can copy the key...");
					stage = 21;
					break;
				case 21:
					interpreter.sendDialogues(npc, null, "...If you can convince Lady Keli to show it to you", "for a moment. She is very boastful.", "It should not be too hard.");
					stage = 22;
					break;
				case 22:
					interpreter.sendDialogues(npc, null, "Bring the imprint to me, with a bar of bronze.");
					stage = 23;
					break;
				case 23:
					quest.setStage(player, 20);
					interpreter.sendOptions("Select an Option", "What is the first thing I must do?", "What exactly is the second thing you need?", "Okay, I better go find some things.");
					stage = 24;
					break;
				case 24:
					switch (buttonId) {
					case 1:
						interpreter.sendDialogues(player, null, "What is the first thing I must do?");
						stage = 10;
						break;
					case 2:
						interpreter.sendDialogues(player, null, "What exactly is the second thing I must do?");
						stage = 20;
						break;
					case 3:
						interpreter.sendDialogues(player, null, "Okay, I better go find some things.");
						stage = 25;
						break;
					}
					break;
				case 25:
					interpreter.sendDialogues(npc, null, "May good luck travel with you. Don't forget to find", "Leela. It can't be done without her help.");
					stage = 26;
					break;
				case 26:
					end();
					break;
				case 50:
					switch (buttonId) {
					case 1:
						interpreter.sendDialogues(player, null, "Explain the first thing again.");
						stage = 10;
						break;
					case 2:
						interpreter.sendDialogues(player, null, "What is th second thing you need?");
						stage = 20;
						break;
					case 3:
						interpreter.sendDialogues(player, null, "Okay, I better go find some things.");
						stage = 25;
						break;
					}
					break;
				}
				break;
			case 10:
				switch (stage) {
				case 0:
					interpreter.sendDialogues(npc, null, "Our prince is captive by the Lady Keli. We just need", "to make the rescue. There are two things we need", "you to do.");
					stage = 1;
					break;
				case 1:
					interpreter.sendOptions("Select an Option", "What is the first thing I must do?", "What is the second thing you need?");
					stage = 3;
					break;
				case 3:
					switch (buttonId) {
					case 1:
						interpreter.sendDialogues(player, null, "What is the first thing I must do?");
						stage = 10;
						break;
					case 2:
						interpreter.sendDialogues(player, null, "What is the second thing you need?");
						stage = 20;
						break;
					}
					break;
				case 10:
					interpreter.sendDialogues(npc, null, "The prince is guarded by some stupid guards and a", "clever woman. The woman is our only way to get the", "prince out. Only she can walk freely about the area.");
					stage = 11;
					break;
				case 11:
					interpreter.sendDialogues(npc, null, "I think you will need to tie her up. One coil of rope", "should do for that. Then, disguise the prince as her to", "get him out without suspicion.");
					stage = 12;
					break;
				case 12:
					interpreter.sendDialogues(player, null, "How good must the disguise be?");
					stage = 13;
					break;
				case 13:
					interpreter.sendDialogues(npc, null, "Only enough to fool the guards at a distance. Get a", "skirt like hers. Same colour, same style. We will only", "have a short time.");
					stage = 14;
					break;
				case 14:
					interpreter.sendDialogues(npc, null, "Get a blonde wig, too. that is up to you to make or", "find. Something to colour the skin of the prince.");
					stage = 15;
					break;
				case 15:
					interpreter.sendDialogues(npc, null, "My daughter and top spy, Leela, can help you. She has", "sent word that she has discovered where they are", "keeping the prince.");
					stage = 16;
					break;
				case 16:
					interpreter.sendDialogues(npc, null, "It's near Draynor Village. She is lurking somewhere", "near there now.");
					stage = 17;
					break;
				case 17:
					quest.setStage(player, 20);
					interpreter.sendOptions("Select an Option", "Explain the first thing again.", "What is the second thing you need?", "Okay, I better go find some things.");
					stage = 50;
					break;
				case 20:
					interpreter.sendDialogues(npc, null, "We need the key, or we need a copy made. If you can", "get some soft clay then you can copy the key...");
					stage = 21;
					break;
				case 21:
					interpreter.sendDialogues(npc, null, "...If you can convince Lady Keli to show it to you", "for a moment. She is very boastful.", "It should not be too hard.");
					stage = 22;
					break;
				case 22:
					interpreter.sendDialogues(npc, null, "Bring the imprint to me, with a bar of bronze.");
					stage = 23;
					break;
				case 23:
					quest.setStage(player, 20);
					interpreter.sendOptions("Select an Option", "What is the first thing I must do?", "What exactly is the second thing you need?", "Okay, I better go find some things.");
					stage = 24;
					break;
				case 24:
					switch (buttonId) {
					case 1:
						interpreter.sendDialogues(player, null, "What is the first thing I must do?");
						stage = 10;
						break;
					case 2:
						interpreter.sendDialogues(player, null, "What exactly is the second thing I must do?");
						stage = 20;
						break;
					case 3:
						interpreter.sendDialogues(player, null, "Okay, I better go find some things.");
						stage = 25;
						break;
					}
					break;
				case 25:
					interpreter.sendDialogues(npc, null, "May good luck travel with you. Don't forget to find", "Leela. It can't be done without her help.");
					stage = 26;
					break;
				case 26:
					end();
					break;
				}
			default:
				break;
			}
			return true;
		}

		/**
		 * Method used to handle the sqirk juice dials.
		 * @param stage the stage.
		 * @param buttonId the buttonId.
		 */
		public void handleSqirks(int buttonId) {
			switch (stage) {
			case 0:
				if (!hasSqirks()) {
					interpreter.sendOptions("Select an Option", "Where do I get sq'irks?", "Why can't you get the sq'irks yourself?", "How should I squeeze the fruit?", "Is there a reward for getting these sq'irks?", "What's so good about sq'irk juice then?");
					stage = 200;
				} else {
					player(hasSqirkJuice() ? "I have some sq'riks juice for you." : "I have some sq'irks for you.");
					stage = 300;
				}
				break;
			case 300:
				if (hasSqirkJuice()) {
					double exp = getExperience();
					player.getSkills().addExperience(Skills.THIEVING, exp, true);
					interpreter.sendDialogue("Osman imparts some Thieving advice to", "you ( " + (exp) + " Thieving experience points )", "as a reward for the sq'irk juice.");
					stage = 304;
				} else {
					npc("Uh, thanks, but is there any chance that you", "could squeeze the fruit into a glass for me?");
					stage = 301;
				}
				break;
			case 301:
				interpreter.sendOptions("Select an Option", "How should I squeeze the fruit?", "Can't you do that yourself?");
				stage = 302;
				break;
			case 302:
				switch (buttonId) {
				case 1:
					player("How should I squeeze the fruit?");
					stage = 130;
					break;
				case 2:
					player("Can't you do that yourself?");
					stage = 303;
					break;
				}
				break;
			case 304:
				end();
				break;
			case 303:
				player("I only carry knives or other such devices on me", "when I'm on the job.");
				stage = 119;
				break;
			case 200:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, null, "Where do I get sq'irks?");
					stage = 110;
					break;
				case 2:
					interpreter.sendDialogues(player, null, "Why can't you get the sq'irks yourself?");
					stage = 120;
					break;
				case 3:
					interpreter.sendDialogues(player, null, "How should I squeeze the fruit?");
					stage = 130;
					break;
				case 4:
					interpreter.sendDialogues(player, null, "Is there a reward for getting these sq'irks?");
					stage = 140;
					break;
				case 5:
					interpreter.sendDialogues(player, null, "What's so good about sq'irk juice then?");
					stage = 150;
					break;
				}
				break;
			case 110:
				npc("There is a sorceress near the south-eastern edge of Al", "Kharid who grows them. Once upon a time, we", "considered each other friends.");
				stage = 111;
				break;
			case 111:
				player("What happened?");
				stage = 112;
				break;
			case 112:
				npc("We fell out, and now she won't give me any more", "fruit.");
				stage = 113;
				break;
			case 113:
				player("So all I have to do is ask her for some fruit for you?");
				stage = 114;
				break;
			case 114:
				npc("I doubt it will be that easy. She is not renowned for", "her generosity and is very secretive about her garden's", "location.");
				stage = 115;
				break;
			case 115:
				player("Oh come on, it should be easy enough to find.");
				stage = 116;
				break;
			case 116:
				npc("Her garden has remained hidden even to me - the chief", "spy of Al Kharid. I believe her garden must be hidden", "by magical means.");
				stage = 117;
				break;
			case 117:
				player("This should be an interesting task. How many sq'irks do", "you want?");
				stage = 118;
				break;
			case 118:
				npc("I'll reward you for as many as you can get your", "hands on, but could you please squeeze the fruit into a", "glass first?");
				stage = 119;
				break;
			case 119:
				interpreter.sendOptions("Select an Option", "I've another question about sq'irks.", "Thanks for the information.");
				stage = 98;
				break;
			case 98:
				switch (buttonId) {
				case 1:
					interpreter.sendOptions("Select an Option", "Where do I get sq'irks?", "Why can't you get the sq'irks yourself?", "How should I squeeze the fruit?", "Is there a reward for getting these sq'irks?", "What's so good about sq'irk juice then?");
					stage = 200;
					break;
				case 2:
					player("Thanks for the information.");
					stage = 99;
					break;
				}
				break;
			case 99:
				end();
				break;
			case 120:
				npc("I may have mentioned that I had a falling out with the", "Sorceress. Well, unsurprisingly, she refuses to give me", "any more of her garden's produce.");
				stage = 119;
				break;
			case 130:
				npc("Use a pestle and mortar to squeeze the sr'irks. Make", "sure you have an empty glass with you to collect the", "juice.");
				stage = 119;
				break;
			case 140:
				npc("Of course there is. I am a generous man. I'll teach", "you the art of Thieving for your troubles.");
				stage = 141;
				break;
			case 141:
				player("How much training will you give?");
				stage = 142;
				break;
			case 142:
				npc("That depends on the quantity and ripeness of the", "sq'irks you put into the juice.");
				stage = 143;
				break;
			case 143:
				player("That sounds fair enough.");
				stage = 119;
				break;
			case 150:
				npc("Ah it's sweet, sweet nectar for a thief or spy; it makes", "light fingers lighter, fleet feet flightier and comes in four", "different colours for those who are easily amused.");
				stage = 151;
				break;
			case 151:
				interpreter.sendDialogue("Osman starts salivating at the thought of sq'irk juice.");
				stage = 152;
				break;
			case 152:
				player("It wouldn't have addictive properties, would it?");
				stage = 153;
				break;
			case 153:
				npc("It only holds power over those with poor self-control,", "something which I have an abundance of.");
				stage = 154;
				break;
			case 154:
				player("I see.");
				stage = 119;
				break;
			}
		}

		/**
		 * Gets the experience the player can recieve.
		 * @return the experience.
		 */
		public double getExperience() {
			double total = 0;
			for (int juiceId : JUICES) {
				SeasonDefinitions def = SeasonDefinitions.forJuiceId(juiceId);
				if (def == null) {
					continue;
				}
				int amount = player.getInventory().getAmount(new Item(juiceId));
				total += (amount * def.getOsmanExp());
				player.getInventory().remove(new Item(juiceId, amount));
			}
			player.getInventory().refresh();
			return total;
		}

		/**
		 * Checks wether the <b>Player</b> has sq'irk.
		 * @return {@code True}: Player has sq'irk.
		 */
		public boolean hasSqirkFruit() {
			for (int i : FRUITS) {
				if (player.getInventory().contains(i, 1)) {
					return true;
				}
			}
			return false;
		}

		/**
		 * Checks wether the <b>Player</b> has sq'irk juice
		 * @return {@code True}: Player has sq'irk juice.
		 */
		public boolean hasSqirkJuice() {
			for (int i : JUICES) {
				if (player.getInventory().contains(i, 1)) {
					return true;
				}
			}
			return false;
		}

		/**
		 * Checks wether the <b>Player</b> has sq'irks (fruit/juice).
		 * @return {@code True}: Player has either a fruit or squeezed juice.
		 */
		public boolean hasSqirks() {
			return hasSqirkFruit() || hasSqirkJuice();
		}

		@Override
		public int[] getIds() {
			return new int[] { 924, 5282 };
		}
	}

	/**
	 * Dialogue for Sqirk making
	 * @author SonicForce41
	 */
	public class SqirkMakingDialogue extends DialoguePlugin {

		private int dialogueId;

		private SeasonDefinitions definition;

		/**
		 * Constructs a new {@code SqirkMakingDialogue.java} {@code Object}.
		 */
		public SqirkMakingDialogue() {

		}

		/**
		 * Constructs a new {@code SqirkMakingDialogue.java} {@code Object}.
		 * @param player the Player
		 */
		public SqirkMakingDialogue(Player player) {
			super(player);
		}

		@Override
		public int[] getIds() {
			return new int[] { 43382 };
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (dialogueId) {
			case 0:
				end();
				break;
			case 1:
				switch (stage) {
				case 0:
					interpreter.sendDialogue("You need " + definition.getFruitAmt() + " sq'irks of this kind to fill a glass of juice.");
					stage = 1;
					break;
				case 1:
					end();
					break;
				}
				break;
			}
			return true;
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new SqirkMakingDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			dialogueId = (int) args[0];
			switch (dialogueId) {
			case 0:
				interpreter.sendDialogues(player, FacialExpression.THINKING, "I should get an empty beer glass to", "hold the juice before I squeeze the fruit.");
				break;
			case 1:
				definition = SeasonDefinitions.forFruitId((int) args[1]);
				if (definition == null)
					end();
				interpreter.sendDialogues(player, FacialExpression.THINKING, "I think I should wait till I have", "enough fruits to make a full glass.");
				break;
			}
			stage = 0;
			return true;
		}

	}

}
