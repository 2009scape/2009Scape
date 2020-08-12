package plugin.holiday.easter;

import plugin.consumable.effects.HealingEffect;
import plugin.dialogue.DialoguePlugin;
import plugin.consumable.Consumables;
import plugin.consumable.Food;
import core.game.content.holiday.HolidayEvent;
import core.game.content.holiday.HolidayItem;
import core.game.content.holiday.HolidayType;
import core.game.content.holiday.ItemLimitation;
import core.game.interaction.Option;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.emote.Emotes;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.repository.Repository;
import core.game.world.update.flag.context.Animation;
import core.plugin.Plugin;
import core.plugin.PluginManager;
import core.plugin.InitializablePlugin;
import core.tools.RandomFunction;

/**
 * Represents the 2005 Easter Event of giving out chocolate eggs.
 * @author Vexia
 *
 */
@InitializablePlugin
public class BasketofEggsEvent extends HolidayEvent {

	/**
	 * The chocolate eggs item.
	 */
	private static final Item[] CHOCOLATE_EGGS = new Item[] { new Item(12646), new Item(12647), new Item(12648) };

	/**
	 * The egging option.
	 */
	private static final Option EGG_OPTION = new Option("Egg", 7);

	/**
	 * The whack option.
	 */
	private static final Option WHACK_OPTION = new Option("Whack", 7);

	/**
	 * The rubber chicken item.
	 */
	public static final Item RUBBER_CHICKEN = new Item(4566);

	/**
	 * The basket of eggs item.
	 */
	private static final Item BASKET_OF_EGGS = new Item(4565);

	/**
	 * The current egg item.
	 */
	private static Item egg;

	/**
	 * The bunny npc.
	 */
	private static NPC bunny;

	/**
	 * Constructs a new {@Code BasketofEggsEvent} {@Code Object}
	 */
	public BasketofEggsEvent() {
		super("Basket of Eggs", HolidayType.EASTER, 1086, 16, 2);
		PluginManager.definePlugins(new BasketofEggsPlugin(), new EasterBunnyDialogue(), new RubberchickenPlugin(), new ChocolateEggPlugin(), new EasterItemPlugin());
	}

	@Override
	public void load() {
		egg = new Item(1961);
		if (!ItemLimitation.isRegistered(1037)) {
			ItemLimitation.register(1037, 25);
		}
		if (!ItemLimitation.isRegistered(egg.getId())) {
			ItemLimitation.register(egg.getId(), 100);
		}
		if (ItemLimitation.getAmountLeft(egg.getId()) > 0) {
			HolidayItem.startRandomSpawn(egg, 1000, Location.create(3433, 3534, 0), Location.create(3099, 3548, 0), Location.create(3067, 10255, 0), Location.create(2913, 3746, 0), Location.create(3193, 3914, 0), Location.create(3214, 9618, 0), Location.create(3227, 3219, 2), Location.create(3300, 3312, 0), Location.create(3075, 3260, 0), Location.create(3213, 3428, 0), Location.create(3213, 3480, 0), Location.create(2710, 3460, 0), Location.create(2750, 3440, 0), Location.create(2927, 3144, 0), Location.create(2667, 3309, 0), Location.create(2600, 3104, 0), Location.create(2910, 4832, 0), Location.create(2925, 3445, 0), Location.create(2475, 3423, 1), Location.create(2817, 3441, 0), Location.create(2948, 3216, 0), Location.create(1864, 5243, 0), Location.create(2706, 3733, 0), Location.create(3186, 3446, 1), Location.create(3189, 9832, 0), Location.create(2655, 3310, 0), Location.create(3145, 9913, 0));
		}
		bunny = NPC.create(1835, new Location(3008, 3294, 0));
		bunny.setWalks(true);
		bunny.init();
	}

	@Override
	public void finalizeDeath(Entity killer, Entity victim) {
		if (killer instanceof Player && victim instanceof NPC) {
			Player player = (Player) killer;
			if (victim.getProperties().getCurrentCombatLevel() > 0) {
				int chance = (5_000 / victim.getProperties().getCurrentCombatLevel()) * 100;
				if (RandomFunction.random(chance) == 0) {
					int itemId = 1037;
					if (ItemLimitation.getAmountLeft(itemId) > 0) {
						ItemLimitation.decreaseAmount(itemId);
						Repository.sendNews(player.getUsername() + " found a pair of bunny ears!");
						player.getPacketDispatch().sendMessage("<col=FF0000>You find a set of bunny ears.");
						GroundItemManager.create(new Item(itemId), victim.getLocation(), player);
					}
				}
			}
		}
	}

	@Override
	public boolean isActive() {
		return false;
	}

	@Override
	public void configure() {

	}

	/**
	 * Gets the amount of eggs in a players inventory.
	 * @param player the player.
	 * @return the amount of eggs.
	 */
	public int getEggs(Player player) {
		int eggs = 0;
		for (Item item : CHOCOLATE_EGGS) {
			eggs += player.getInventory().getAmount(item);
		}
		return eggs;
	}

	/**
	 * Handlers the easter bunny dialogue.
	 * @author Vexia
	 *
	 */
	public class EasterBunnyDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@Code EasterBunnyDialogue} {@Code Object}
		 */
		public EasterBunnyDialogue() {
			/*
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@Code EasterBunnyDialogue} {@Code Object}
		 * @param player the player.
		 */
		public EasterBunnyDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new EasterBunnyDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			if (player.isAdmin()) {
				player.sendMessage("" + egg.getLocation());
			}
			switch (getStage(player)) {
			case 0:
				npc("Oh dear, the eggs, the eggs...", "What will I do about the eggs!? All of", "the eggs!!??");
				break;
			case 1:
				if (player.getAttribute("eggs", 0) >= 15) {
					npc("Thank you, " + player.getUsername() + "! You handed out " + player.getAttribute("eggs", 0) + " eggs! Here", "is your Easter reward.");
				} else {
					Pulse pulse = player.getAttribute("egg-pulse", null);
					if (pulse != null && pulse.isRunning() && player.hasItem(BASKET_OF_EGGS)) {
						npc("You need to hand out a total of 15 eggs in order", "for me to give you a reward.");
						stage = 4;
						return true;
					}
					npc("You didn't hand out enough eggs in order to", "receive a prize. Take this basket and try", "again.");
					stage = 2;
				}
				break;
			case 2:
				npc("Thank you for all of your help " + player.getUsername() + "!");
				break;
			}
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (getStage(player)) {
			case 0:
				switch (stage) {
				case 0:
					npc("I'm so sorry, I didn't realize you were there.", "What might I assist you with?");
					stage++;
					break;
				case 1:
					options("What about eggs..?", "What are you doing here?", "Are you in a costume?", "Nothing.");
					stage++;
					break;
				case 2:
					switch (buttonId) {
					case 1:
						player("What about eggs..?");
						stage = 10;
						break;
					case 2:
						npc("I am the easter bunny, and since it is easter", "I must begin handing out chocolate eggs to all", "of " + GameWorld.getName() + ".");
						stage = 20;
						break;
					case 3:
						player("Are you in a costume?");
						stage = 30;
						break;
					case 4:
						player("Nothing, just passing through.");
						stage = 40;
						break;
					}
					break;
				case 10:
					npc("Being the easter bunny is no simple task and", "I have found myself very overwhelmed this year.", "I have no clue how I must deliver all of these basket", "of eggs to all the people of " + GameWorld.getName() + ".");
					stage = 21;
					break;
				case 20:
					npc("I must admit that it is a very large task", "for such a bunny like myself. It takes a", "whole year to prepare and I am still behind!");
					stage++;
					break;
				case 21:
					options("Do you need any help?", "Goodluck with that.");
					stage++;
					break;
				case 22:
					if (buttonId == 1) {
						player("Do you need any help?");
						stage = 24;
					} else {
						player("Goodluck with that.");
						stage++;
					}
					break;
				case 23:
					npc("Thank you for your concern...");
					stage = 31;
					break;
				case 24:
					npc("I would appreciate your help very much " + player.getUsername() + ".", "You have two minutes to go and hand out 15", "eggs from the basket.");
					stage++;
					break;
				case 25:
					npc("Take this basket of eggs and hand out the chocolate eggs.", "When you're done come back and talk to me. I'm sure", "we can sort out some type of reward for your assistance.");
					stage++;
					break;
				case 26:
					interpreter.sendItemMessage(BASKET_OF_EGGS, "The easter bunny hands you a basket of eggs.");
					stage++;
					break;
				case 27:
					BasketofEggsEvent.this.setStage(player, 1);
					player.getInventory().add(BASKET_OF_EGGS, player);
					end();
					Pulse pulse = new Pulse(200, player) {

						@Override
						public boolean pulse() {
							player.getDialogueInterpreter().sendDialogue("Your time is up. Go and talk to the easter bunny.");
							return true;
						}

						@Override
						public void stop() {
							player.getBank().remove(BASKET_OF_EGGS);
							player.getEquipment().remove(BASKET_OF_EGGS);
							player.getInventory().remove(BASKET_OF_EGGS);
							super.stop();
						}
					};
					player.setAttribute("egg-pulse", pulse);
					GameWorld.Pulser.submit(pulse);
					break;
				case 30:
					npc("No you fool! I am an easter bunny!!");
					stage++;
					break;
				case 31:
					end();
					break;
				case 40:
					end();
					break;
				}
				break;
			case 1:
				switch (stage) {
				case 0:
					player.removeAttribute("eggs");
					interpreter.sendItemMessage(RUBBER_CHICKEN, "The easter bunny hands you over a rubber chicken", "and unlocks the 'Bunny-hop' emote.");
					stage++;
					break;
				case 1:
					player.getBank().remove(BASKET_OF_EGGS);
					player.getEquipment().remove(BASKET_OF_EGGS);
					player.getInventory().remove(BASKET_OF_EGGS);
					player.getInventory().add(RUBBER_CHICKEN, player);
					player.getEmoteManager().unlock(Emotes.BUNNY_HOP);
					BasketofEggsEvent.this.setStage(player, 2);
					end();
					break;
				case 2:
					interpreter.sendItemMessage(BASKET_OF_EGGS, "The easter bunny hands you a basket of eggs.");
					stage++;
					break;
				case 3:
					player.removeAttribute("eggs");
					player.getInventory().add(BASKET_OF_EGGS, player);
					end();
					Pulse pulse = new Pulse(200, player) {

						@Override
						public boolean pulse() {
							player.getDialogueInterpreter().sendDialogue("Your time is up. Go and talk to the easter bunny.");
							return true;
						}

						@Override
						public void stop() {
							player.getBank().remove(BASKET_OF_EGGS);
							player.getEquipment().remove(BASKET_OF_EGGS);
							player.getInventory().remove(BASKET_OF_EGGS);
							super.stop();
						}
					};
					player.setAttribute("egg-pulse", pulse);
					GameWorld.Pulser.submit(pulse);
					break;
				case 4:
					end();
					break;
				}
				break;
			case 2:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 1835 };
		}

	}

	/**
	 * Handles the interactions related to the basket of eggs.
	 * @author Vexia
	 *
	 */
	public class BasketofEggsPlugin extends OptionHandler {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			EGG_OPTION.setHandler(this);
			return this;
		}

		@Override
		public boolean handle(Player player, Node node, String option) {
			if (!(node instanceof Player)) {
				return false;
			}
			if (getStage(player) != 1) {
				player.getEquipment().remove(BASKET_OF_EGGS);
				player.getInventory().remove(BASKET_OF_EGGS);
				player.getBank().remove(BASKET_OF_EGGS);
				return true;
			}
			if (!player.getEquipment().containsItem(BASKET_OF_EGGS)) {
				player.sendMessage("You need to be wielding a basket of eggs in order to do that.");
				return true;
			}
			Pulse pulse = player.getAttribute("egg-pulse", null);
			if (pulse == null || !pulse.isRunning()) {
				player.getEquipment().remove(BASKET_OF_EGGS);
				player.getInventory().remove(BASKET_OF_EGGS);
				player.getBank().remove(BASKET_OF_EGGS);
				return true;
			}
			Player target = node.asPlayer();
			if (getEggs(target) >= 4 && !GameWorld.getSettings().isDevMode()) {
				player.sendMessage("That player already has enough chocolate eggs.");
				return true;
			}
			if (target.getInventory().freeSlots() < 1) {
				player.sendMessage("That player doesn't have enough room in their inventory.");
				return true;
			}
			target.sendMessage(player.getUsername() + " gave you an easter egg!");
			player.setAttribute("eggs", player.getAttribute("eggs", 0) + 1);
			player.sendMessage("You gave " + target.getUsername() + " an easter egg!");
			target.getInventory().add(RandomFunction.getRandomElement(CHOCOLATE_EGGS));
			return true;
		}

	}

	/**
	 * Handles the easter item plugin.
	 * @author Vexia
	 *
	 */
	public class EasterItemPlugin implements Plugin<Object> {
		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			RUBBER_CHICKEN.getDefinition().getConfigurations().put("equipment", this);
			BASKET_OF_EGGS.getDefinition().getConfigurations().put("equipment", this);
			return this;
		}

		@Override
		public Object fireEvent(String identifier, Object... args) {
			final Player player = (Player) args[0];
			Item item = (Item) args[1];
			final Item other = args.length == 2 ? null : (Item) args[2];
			if (other != null) {
				identifier = "equip";
				item = other;
			}
			switch (identifier) {
			case "equip":
				player.getInteraction().set(item.getId() == BASKET_OF_EGGS.getId() ? EGG_OPTION : WHACK_OPTION);
				break;
			case "unequip":
				player.getInteraction().remove(item.getId() == BASKET_OF_EGGS.getId() ? EGG_OPTION : WHACK_OPTION);
				break;
			}
			return true;
		}
	}

	/**
	 * Handles the interactions related to the rubber chicken.
	 * @author Vexia
	 *
	 */
	public class RubberchickenPlugin extends OptionHandler {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			RUBBER_CHICKEN.getDefinition().getConfigurations().put("option:operate", this);
			RUBBER_CHICKEN.getDefinition().getConfigurations().put("option:dance", this);
			WHACK_OPTION.setHandler(this);
			return this;
		}

		@Override
		public boolean handle(Player player, Node node, String option) {
			switch (option) {
			case "whack":
				if (!(node instanceof Player)) {
					return true;
				}
				Player target = (Player) node;
				player.lock(4);
				player.face(target);
				player.animate(Animation.create(1833));
				player.getAudioManager().send(2257, true);
				node.asPlayer().getPacketDispatch().sendMessage(player.getUsername() + " whacks you with a rubber chicken.");
				return true;
			case "dance":
			case "operate":
				if (player.getAttribute("chicken-delay", 0) > GameWorld.getTicks()) {
					return true;
				}
				player.setAttribute("chicken-delay", GameWorld.getTicks() + 8);
				player.animate(Animation.create(1835));
				return true;
			}
			return true;
		}

		// http://www.rune-server.org/runescape-development/rs-503-client-server/tutorials/461911-718-right-click-mute-option-mods.html
		// http://www.rune-server.org/runescape-development/rs2-server/snippets/513788-474-rs2-server-player-option-5-used-rubber-chicken-includes.html

	}

	/**
	 * Represents the chocolate egg plugin.
	 * @author Vexia
	 *
	 */
	public class ChocolateEggPlugin extends Food {

		/**
		 * Constructs a new {@Code ChocolateEggPlugin} {@Code Object}
		 */
		public ChocolateEggPlugin() {
			this(-1);
		}

		@Override
		public ChocolateEggPlugin newInstance(Object object) {
			for (Item item : CHOCOLATE_EGGS) {
				Consumables.add(new ChocolateEggPlugin(item.getId()));
			}
			return this;
		}

		/**
		 * Constructs a new {@code CupofTeaPlugin} {@code Object}.
		 */
		public ChocolateEggPlugin(int id) {
			super(new int[] {id}, new HealingEffect(0), new Animation(1835));
		}
	}
}
