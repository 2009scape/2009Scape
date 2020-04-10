package plugin.holiday.christmas;
import java.util.List;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.component.Component;
import org.crandor.game.component.ComponentDefinition;
import org.crandor.game.component.ComponentPlugin;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.holiday.HolidayEvent;
import org.crandor.game.content.holiday.HolidayItem;
import org.crandor.game.content.holiday.HolidayType;
import org.crandor.game.content.holiday.ItemLimitation;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.Option;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.TeleportManager.TeleportType;
import org.crandor.game.node.entity.player.link.emote.Emotes;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.PulseRunner;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.repository.Repository;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles the RuneScape 2007 Christmas Event.
 * @author Vexia
 *
 */
@InitializablePlugin
public class ChristmasEvent extends HolidayEvent {

	/**
	 * The location of places where imps & snow are.
	 */
	private static final Location[] HUBS = new Location[] {Location.create(2965, 3380, 0), Location.create(3079, 3250, 0), Location.create(2659, 3657, 0), Location.create(2664, 3305, 0), Location.create(2898, 3544, 0), new Location(3159, 3490, 0)};

	/**
	 * The santa hat item.
	 */
	private static final Item SANTA_HAT = new Item(1050);

	/**
	 * The christmas cracker item.
	 */
	private static final Item CHRISTMAS_CRACKER = new Item(962);

	/**
	 * The ball of snow item.
	 */
	private static final Item BALL_OF_SNOW = new Item(11951);

	/**
	 * The snow globe item.
	 */
	private static final Item SNOWGLOBE = new Item(11949);

	/**
	 * The array of snowman hat ids.
	 */
	private static final int[] SNOWMAN_HATS = new int[] {11955, 11956, 11957, 11958, 11959};

	/**
	 * The snowman weapon ids.
	 */
	private static final int[] SNOWMAN_WEAPONS = new int[] {11952, 11953, 11954};

	/**
	 * The pelt option.
	 */
	private static final Option PELT_OPTION = new Option("Pelt", 7);

	/**
	 * Constructs the {@code ChristmasEvent} object.
	 */
	public ChristmasEvent() {
		super("2007 Christmas Event", HolidayType.CHRISTMAS, 1087, 16, 10);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		if (isActive()) {
			PluginManager.definePlugin(new ChristmasEventOptionPlugin(), new SnowmanItemHandler(), new SnowImpDialogue(), new SnowmanHatComponentPlugin(), new QueenOfSnowDialogue(), new SnowballItemPlugin(), new SnowmanNPC(), new PeltOptionHandler());
		}
		return super.newInstance(arg);
	}

	@Override
	public void load() {
		if (!ItemLimitation.isRegistered(SANTA_HAT.getId())) {
			ItemLimitation.register(SANTA_HAT.getId(), 25);
		}
		if (!ItemLimitation.isRegistered(CHRISTMAS_CRACKER.getId())) {
			ItemLimitation.register(CHRISTMAS_CRACKER.getId(), 25);
		}
		if (ItemLimitation.getAmountLeft(SANTA_HAT.getId()) > 0) {
			HolidayItem.startRandomSpawn(SANTA_HAT, 1000, Location.create(3047, 3296, 0), Location.create(3016, 3291, 0), Location.create(3013, 3260, 0), Location.create(3050, 3236, 0), Location.create(3091, 3275, 0), Location.create(3102, 3270, 1), Location.create(3210, 3221, 3), Location.create(3300, 3193, 0), Location.create(3284, 3162, 1), Location.create(2741, 3443, 0), Location.create(2695, 3462, 0), Location.create(2667, 3291, 1), Location.create(3079, 3512, 0), Location.create(3079, 3548, 0), Location.create(3018, 3602, 0), Location.create(3135, 3515, 0), Location.create(3216, 3412, 1), Location.create(2942, 3382, 0), Location.create(2973, 3338, 0));
		}
		NPC imp;
		for (Location location : HUBS) {
			imp = NPC.create(6732, location);
			imp.setWalks(true);
			imp.init();
		}
		snow();
	}

	@Override
	public boolean enter(Entity entity) {
		if (entity instanceof Player) {
			Player player = entity.asPlayer();
			player.setAttribute("last-location", player.getRenderInfo().getLastLocation());
		}
		return super.enter(entity);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e instanceof Player) {
			Player player = e.asPlayer();
			for (int i : SNOWMAN_WEAPONS) {
				if (player.getInventory().contains(i, 1)) {
					player.getInventory().remove(new Item(i, player.getInventory().getAmount(i)));
				}
				if (player.getBank().containItems(i, 1)) {
					player.getBank().remove(new Item(i, player.getBank().getAmount(i)));
				}
			}
			if (logout) {
				player.setLocation(player.getAttribute("last-location", HUBS[0]));
			}
		}
		return super.leave(e, logout);
	}
	
	@Override
	public void finalizeDeath(Entity killer, Entity victim) {
		if (killer instanceof Player && victim instanceof NPC) {
			Player player = (Player) killer;
			if (victim.getProperties().getCurrentCombatLevel() > 0) {
				int chance = (5_000 / victim.getProperties().getCurrentCombatLevel()) * 100;
				if (RandomFunction.random(chance) == 0) {
					int itemId = CHRISTMAS_CRACKER.getId();
					if (ItemLimitation.getAmountLeft(itemId) > 0) {
						ItemLimitation.decreaseAmount(itemId);
						Repository.sendNews(player.getUsername() + " found a christmas cracker. There are " + ItemLimitation.getAmountLeft(itemId) + " crackers left!");
						player.getPacketDispatch().sendMessage("<col=FF0000>You find a christmas cracker!.");
						GroundItemManager.create(new Item(itemId), victim.getLocation(), player);
					}
				}
			}
		}
	}

	/**
	 * Sends snow near the hub.
	 */
	private void snow() {
		Location loc = null;
		GameObject obj = null;
		for (Location location : HUBS) {
			for (int x = 2; x < 10; x++) {
				for (int y = 2; y < 10; y++) {
					loc = location.transform(x, y, 0);
					if (RegionManager.getObject(loc) == null && RegionManager.isTeleportPermitted(loc) && RandomFunction.random(3) == 1) {
						obj = new GameObject(28296, loc);
						ObjectBuilder.add(obj);
						RegionManager.removeClippingFlag(0, loc.getX(), loc.getY(), false, 0x100);
					}
					loc = location.transform(-x, y, 0);
					if (RegionManager.getObject(loc) == null && RegionManager.isTeleportPermitted(loc) && RandomFunction.random(3) == 1) {
						obj = new GameObject(28296, loc);
						ObjectBuilder.add(obj);
						RegionManager.removeClippingFlag(0, loc.getX(), loc.getY(), false, 0x100);
					}
					loc = location.transform(x, -y, 0);
					if (RegionManager.getObject(loc) == null && RegionManager.isTeleportPermitted(loc) && RandomFunction.random(3) == 1) {
						obj = new GameObject(28296, loc);
						ObjectBuilder.add(obj);
						RegionManager.removeClippingFlag(0, loc.getX(), loc.getY(), false, 0x100);
					}
					loc =  location.transform(-x, -y, 0);
					if (RegionManager.getObject(loc) == null && RegionManager.isTeleportPermitted(loc) && RandomFunction.random(3) == 1) {
						obj = new GameObject(28296, loc);
						ObjectBuilder.add(obj);
						RegionManager.removeClippingFlag(0, loc.getX(), loc.getY(), false, 0x100);
					}
				}
			}
		}
	}

	/**
	 * Sends a christmas teleport.
	 * @param player The player.
	 * @param location The location.
	 */
	public static void sendChristmasTeleport(Player player, Location location) {
		player.getTeleporter().send(location, TeleportType.CHRISTMAS);
	}

	@Override
	public boolean isActive() {
		return false;
	}

	@Override
	public void configure() {
		registerRegion(10583);
	}

	/**
	 * Handles interactions for the Christmas Event.
	 * @author Vexia
	 *
	 */
	public class ChristmasEventOptionPlugin extends OptionHandler {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			ObjectDefinition.forId(28296).getConfigurations().put("option:collect", this);
			ItemDefinition.forId(BALL_OF_SNOW.getId()).getConfigurations().put("option:build", this);
			for (int i = 28266; i < 28296; i++) {
				ObjectDefinition.forId(i).getConfigurations().put("option:add-to", this);
			}
			ItemDefinition.forId(SNOWGLOBE.getId()).getConfigurations().put("option:shake", this);
			return this;
		}

		@Override
		public boolean handle(final Player player, Node node, String option) {
			switch (option) {
			case "collect":
				if (!player.getInventory().hasSpaceFor(BALL_OF_SNOW)) {
					player.sendMessage("You don't have enough inventory space to collect any snow.");
					return true;
				}
				if (player.getInventory().add(BALL_OF_SNOW)) {
					player.lock(3);
					player.animate(new Animation(7529));
					player.sendMessage("You collect a ball of snow.");
				}
				break;
			case "build":
				Location loc = player.getLocation().transform(player.getDirection());
				if (RegionManager.getObject(loc) != null || !RegionManager.isTeleportPermitted(loc)) {
					player.sendMessage("You can't build a snowman here.");
					return true;
				}
				if (player.getInventory().remove(node.asItem())) {
					player.sendMessage("You start to build a snowman.");
					player.animate(Animation.create(7532));
					ObjectBuilder.add(new GameObject(28266, loc));
				}
				break;
			case "add-to":
				if (node.asObject().getId() == 28295) {
					player.sendMessage("You need a " + (player.getZoneMonitor().isInZone(getName()) ? "weapon or hat " : "hat") + " to complete this snowman.");
					return true;
				}
				if (!player.getInventory().containsItem(BALL_OF_SNOW)) {
					player.sendMessage("You need snow in order to add to this snow man.");
					return true;
				}
				if (player.getInventory().remove(BALL_OF_SNOW)) {
					if (player.getLocation().equals(node.getLocation())) {
						player.moveStep();
						player.faceLocation(node.getLocation());
					}
					player.sendMessage("You add some snow to the snowman.");
					player.lock(1);
					player.animate(Animation.create(node.asObject().getId() <= 28278 ? 7532 : 7533));
					ObjectBuilder.replace(node.asObject(), node.asObject().transform(node.asObject().getId() + 1));
					if (node.asObject().getId() + 1 >= 28295) {
						if (player.getZoneMonitor().isInZone(getName())) {
							player.sendMessages("The snowman is almsot complete! Talk to a snow imp to get a hat or weapon to", "complete it.");
						} else {
							player.sendMessage("The snowman is almsot complete! Talk to a snow imp to get a hat to complete it.");
						}
					}
				}
				break;
			case "shake":
				if (player.getInventory().freeSlots() < 1) {
					player.sendMessage("You don't have enough inventory space to do that.");
					return true;
				}
				player.lock(16);
				player.animate(Animation.create(7535));
				player.sendMessage("You shake the snow globe.");
				PulseRunner.submit(new Pulse(1, player) {
					int ticks;
					@Override
					public boolean pulse() {
						switch (++ticks) {
						case 3:
							player.getInterfaceManager().open(new Component(659));
							break;
						case 7:
							player.graphics(new Graphics(1284));
							player.sendMessage("The snow globe fills your inventory with snow.");
							player.getInventory().add(new Item(BALL_OF_SNOW.getId(), player.getInventory().freeSlots()));
							player.getInterfaceManager().close();
							return true;
						}
						return false;
					}

				});
				break;
			}
			return true;
		}

	}

	/**
	 * Handles the pelt option on entities.
	 * @author Vexia
	 *
	 */
	public class PeltOptionHandler extends OptionHandler {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			for (int i = 6747; i < 6750; i++) {
				NPCDefinition.forId(i).getConfigurations().put("option:pelt", this);
			}
			PELT_OPTION.setHandler(this);
			return this;
		}

		@Override
		public boolean handle(final Player player, final Node node, String option) {
			if (!player.getLocation().withinDistance(node.getLocation(), 6)) {
				player.sendMessage("You are too far away to throw this snowball.");
				return true;
			}
			if (player.getInventory().remove(BALL_OF_SNOW) || player.getEquipment().remove(BALL_OF_SNOW)) {
				if (!player.getEquipment().containsItem(BALL_OF_SNOW)) {
					player.getInteraction().remove(PELT_OPTION);
				}
				final Entity target = (Entity) node;
				player.lock(4);
				player.face(target);
				player.animate(Animation.create(7530));
				player.graphics(Graphics.create(1282));
				if (node instanceof NPC) {
					player.sendMessage("You throw a snowball.");
					node.asNpc().getSkills().heal(1);
					int delay = 41;
					int speed = 60;
					player.getLocation();
					int distance = (int) Location.getDistance(player.getLocation(), target.getLocation());
					int projectileSpeed = delay + speed + distance * 5;
					double hitDelay = projectileSpeed * .02857;
					PulseRunner.submit(new Pulse((int) hitDelay, target) {
						@Override
						public boolean pulse() {
							target.getImpactHandler().manualHit(player, node instanceof NPC ? 1 : 0, HitsplatType.MISS);
							return true;
						}
					});
				} else {
					player.sendMessage("You pelt " + target.asPlayer().getUsername() + " with a snowball.");
				}
				Projectile.create(player, target, 1282, 30, 10).send();
				return true;
			}
			player.sendMessage("You need a snowball in order to do that.");
			return true;
		}

		@Override
		public boolean isWalk() {
			return false;
		}

	}

	/**
	 * Handles the use of a snowman hat on a snowman.
	 * @author Vexia
	 *
	 */
	public class SnowmanItemHandler extends UseWithHandler {

		/**
		 * Constructs the {@code SnowmanItemHandler}
		 */
		public SnowmanItemHandler() {
			super(11955, 11956, 11957, 11958, 11959, 11952, 11953, 11954);
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			addHandler(28295, OBJECT_TYPE, this);
			PluginManager.definePlugin(new SnowmanDialogue());
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			final Player player = event.getPlayer();
			if (player.getInventory().remove(event.getUsedItem())) {
				boolean weapon = event.getUsedItem().getId() < 11955;
				int id = weapon ? 6747 + (11954 - event.getUsedItem().getId()) : 6742 + (4 - (11959 - event.getUsedItem().getId()));
				if (event.getUsedItem().getId() == 11953) {
					id = 6749;
				} else if (event.getUsedItem().getId() == 11952) {
					id = 6748;
				}
				final int npcId = id;
				ObjectBuilder.remove(event.getUsedWith().asObject());
				final NPC snowman = NPC.create(npcId, event.getUsedWith().getLocation());
				snowman.init();
				snowman.faceTemporary(player, 2);
				snowman.setRespawn(false);
				snowman.setWalks(true);
				snowman.setAttribute("owner", player.getDetails().getUid());
				if (!player.getEmoteManager().isUnlocked(Emotes.SNOWMAN_DANCE)) {
					player.getEmoteManager().unlock(Emotes.SNOWMAN_DANCE);
					player.sendMessage("<col=FF0000>You've unlocked the snowman dance emote!</col>");
				}
				if (!weapon && HolidayEvent.getCurrent().getStage(player) < 2) {
					HolidayEvent.getCurrent().setStage(player, 2);
				}
				if (!weapon) {
					PulseRunner.submit(new Pulse(200) {
						@Override
						public boolean pulse() {
							snowman.clear();
							return true;
						}
					});
				}
			}
			return true;
		}

		/**
		 * Handles the snowman dialogue.
		 * @author Vexia
		 *
		 */
		public class SnowmanDialogue extends DialoguePlugin {

			/**
			 * Constructs the {@code SnowmanDialogue}
			 * @param player The player.
			 */
			public SnowmanDialogue(Player player) {
				super(player);
			}

			/**
			 * Constructs the {@code SnowmanDialogue}
			 */
			public SnowmanDialogue() {
				/*
				 * empty.
				 */
			}

			@Override
			public DialoguePlugin newInstance(Player player) {
				return new SnowmanDialogue(player);
			}

			@Override
			public boolean open(Object... args) {
				npc = (NPC) args[0];
				if (npc.getAttribute("owner", 0) != player.getDetails().getUid()) {
					player.sendMessage("The snowman doesn't seem interested in talking to you.");
					return true;
				}
				npc("Happy Christmas, mortal!");
				return true;
			}

			@Override
			public boolean handle(int interfaceId, int buttonId) {
				switch (stage) {
				case 0:
					player("Merry Christmas!");
					stage = 10;
					break;
				case 10:
					npc("And a Happy New Year!");
					stage++;
					break;
				case 11:
					npc("You should keep the spirit of Christmas in your heart at", "all times, mortal. If you do this, then to have a happy", "Christmas is to have a Happy New Year.");
					stage++;
					break;
				case 12:
					end();
					break;
				}
				return true;
			}

			@Override
			public int[] getIds() {
				return new int[] {6742, 6743, 6744, 6745, 6746};
			}

		}
	}

	/**
	 * Represents the snow imp dialogue.
	 * @author Vexia
	 *
	 */
	public static class SnowImpDialogue extends DialoguePlugin {

		/**
		 * The saying of snow imps.
		 */
		private static final String[][] SAYINGS = new String[][] {
			{"Let it snow, let it snow, let it snow."},
			{"I'm dreaming of a white Keldagrim"},
			{"There's no business like snow business!"},
			{"Snowflakes! Millions of snowflakes! No two are the", "same you know!"},
			{"Isn't snow brilliant?"},
			{"Snow! Ha ha ha ha! Snow everywhere!"}
		};

		/**
		 * Constructs the {@code SnowImpDialogue}
		 * @param player The player.
		 */
		public SnowImpDialogue(Player player) {
			super(player);
		}

		/**
		 * Constructs the {@code SnowImpDialogue}
		 */
		public SnowImpDialogue() {
			/*
			 * empty.
			 */
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new SnowImpDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			switch (npc.getId()) {
			case 8536:
				npc("Go, snow warriors! Yeah!");
				stage = 100;
				break;
			default:
				npc(SAYINGS[RandomFunction.random(SAYINGS.length - 1)]);
				stage = HolidayEvent.getCurrent().getStage(player) > 1 ? 40 : 1;
				break;
			}
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 1:
				switch (HolidayEvent.getCurrent().getStage(player)) {
				case 0:
					options("Who are you?", "Why are you making it snow?", "What can I do with all this snow?");
					stage = 4;
					break;
				case 1:
					player("Could I have a snowman hat, please?");
					stage = 2;
					break;
				}
				break;
			case 2:
				for (int i : SNOWMAN_HATS) {
					if (player.hasItem(new Item(i))) {
						npc("You already have a snowman hat, mate.");
						stage = 5;
						break;
					}
				}
				end();
				player.getInterfaceManager().open(new Component(658));
				break;
			case 3:
				options("Who are you?", "Why are you making it snow?", "What can I do with all this snow?");
				stage++;
				break;
			case 4:
				switch (buttonId) {
				case 1:
					player("Who are you?");
					stage = 10;
					break;
				case 2:
					player("Why are you making it snow?");
					stage = 20;
					break;
				case 3:
					player("What can I do with all this snow?");
					stage = 30;
					break;
				}
				break;
			case 5:
				end();
				break;
			case 10:
				npc("It's a snow imp! From der Land of Snow!");
				stage++;
				break;
			case 11:
				player("Can I go to the Land of Snow?");
				stage++;
				break;
			case 12:
				if (HolidayEvent.getCurrent().getStage(player) > 1) {
					sendChristmasTeleport(player, Location.create(2643, 5611, 0));
					end();
					break;
				}
				npc("We don't just let anyone into the Land of Snow, mate.", "You need to show you like snow first. Help to build a", "snowman!");
				if (HolidayEvent.getCurrent().getStage(player) < 1) {
					HolidayEvent.getCurrent().setStage(player, 1);
				}
				stage++;
				break;
			case 13:
				options("Who are you?", "Why are you making it snow?", "Could I have a snowman hat please?");
				stage++;
				break;
			case 14:
				switch (buttonId) {
				case 1:
					player("Who are you?");
					stage = 10;
					break;
				case 2:
					player("Why are you making it snow?");
					stage = 20;
					break;
				case 3:
					player("Could I have a snowman hat please?");
					stage = 2;
					break;
				}
				break;
			case 20:
				npc("'Cause it's Christmas, innit.");
				stage = 3;
				break;
			case 30:
				npc("Whatever yer like! I don't care! There's plen'y more", "where this lot came from.");
				stage = 3;
				break;
			case 40:
				options("Who are you?", "Why are you making it snow?", "What can I do with all this snow?", "Could I have a snowman hat, please?", "Can I go to the Land of Snow?");
				stage++;
				break;
			case 41:
				switch (buttonId) {
				case 1:
					player("Who are you?");
					stage = 10;
					break;
				case 2:
					player("Why are you making it snow?");
					stage = 20;
					break;
				case 3:
					player("What can I do with all this snow?");
					stage = 30;
					break;
				case 4:
					player("Could I have a snowman hat please?");
					stage = 2;
					break;
				case 5:
					player("Can I go to the Land of Snow?");
					stage = 42;
					break;
				}
				break;
			case 42:
				end();
				sendChristmasTeleport(player, Location.create(2643, 5611, 0));
				break;
			case 100:
				options("Who are you?", "Could I have a snowman hat, please?", "Could I have a snowman weapon, please?", "I want to go back to Keldagrim.");
				stage++;
				break;
			case 101:
				switch (buttonId) {
				case 1:
					player("Who are you?");
					stage = 110;
					break;
				case 2:
					player("Could I have a snowman hat, please?");
					stage = 2;
					break;
				case 3:
					player("Could I have a snowman weapon, please?");
					stage = 130;
					break;
				case 4:
					player("I want to go back to Keldagrim.");
					stage = 140;
					break;
				}
				break;
			case 110:
				npc("It's a snow imp! From der Land of Snow!");
				stage = 100;
				break;
			case 130:
				for (int i : SNOWMAN_WEAPONS) {
					if (player.hasItem(new Item(i))) {
						npc("You already have a snowman hat, mate.");
						stage = 131;
						break;
					}
				}
				end();
				player.getPacketDispatch().sendString("Select a Snowman Hat or Weapon", 658, 13);
				player.getPacketDispatch().sendInterfaceConfig(658, 53, false);
				player.getInterfaceManager().open(new Component(658));
				break;
			case 131:
				break;
			case 140:
				npc("Very well. You may return here during Christmas-time, when", "the Land of Snow is close to Keldagrim.");
				stage++;
				break;
			case 141:
				sendChristmasTeleport(player, player.getAttribute("last-location", HUBS[0]));
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] {6732, 6733, 6734, 6735, 6736, 6737, 6738, 8536};
		}

	}

	/**
	 * Handles the snowman hat selector component.
	 * @author Vexia
	 *
	 */
	public class SnowmanHatComponentPlugin extends ComponentPlugin {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			ComponentDefinition.forId(658).setPlugin(this);
			return this;
		}

		@Override
		public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
			switch (component.getId()) {
			case 658:
				switch (opcode) {
				case 155:
					if (player.getInventory().freeSlots() < 1) {
						player.sendMessage("You don't have enough inventory space.");
						player.getInterfaceManager().close();
						return true;
					}
					boolean weapons =  button >= 32;
					for (int i : weapons ? SNOWMAN_WEAPONS : SNOWMAN_HATS) {
						if (player.hasItem(new Item(i))) {
							player.getInterfaceManager().close();
							player.sendMessage("You already have a snowman " + (weapons ? "weapon" : "hat") + ".");
							return true;
						}
					}
					if (!weapons) {
						int dif =  30 - button;
						int div = 4 - (dif / 2);
						itemId =  11955 + div;
					} else {
						//11952-11954
						itemId = 11954;
						if (button == 36) {
							itemId = 11952;
						} else if (button == 34) {
							itemId = 11953;
						} 
					}
					player.getInventory().add(new Item(itemId));
					player.getInterfaceManager().close();
					break;
				}
				break;
			}
			return true;
		}

	}

	/**
	 * Handles the dialogue for the queen of snow.
	 * @author Vexia
	 *
	 */
	public class QueenOfSnowDialogue extends DialoguePlugin {

		/**
		 * Constructs the {@code QueenOfSnowDialogue} object.
		 */
		public QueenOfSnowDialogue() {
			/*
			 * empty.
			 */
		}

		/**
		 * Constructs the {@code QueenOfSnowDialogue} object.
		 * @param player The player.
		 */
		public QueenOfSnowDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new QueenOfSnowDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			switch (HolidayEvent.getCurrent().getStage(player)) {
			case 2:
				npc("Happy Christmas, noble visitor. Welcome to the Land of", "Snow. I have a gift for you, if you would like it.");
				stage = 15;
				break;
			case 3:
				npc("Happy Christmas, noble visitor. Welcome to the Land of", "Snow.");
				stage = 100;
				break;
			}
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 15:
				options("Ooh, a present?", "I want to go back to Keldagrim.");
				stage++;
				break;
			case 16:
				switch (buttonId) {
				case 1:
					player("Ooh, a present?");
					stage = 17;
					break;
				case 2:
					player("I want to go back to Keldagrim.");
					stage = 150;
					break;
				}
				break;
			case 17:
				npc("It is a snow globe: a tiny model of your Lumbridge", "Castle enchanted with a little of this land's magical snow.");
				stage++;
				break;
			case 18:
				interpreter.sendItemMessage(SNOWGLOBE, "The Queen of Snow hands you a snow globe.");
				stage++;
				break;
			case 19:
				if (!player.getInventory().add(SNOWGLOBE)) {
					end();
					player.sendMessage("You don't have enough room in your inventory.");
					break;
				}
				if (HolidayEvent.getCurrent().getStage(player) < 3) {
					HolidayEvent.getCurrent().setStage(player, 3);
				}
				end();
				break;
			case 100:
				if (!player.hasItem(SNOWGLOBE)) {
					npc("I see that you have lost your snow globe, would", "you like another one?");
					stage = 200;
					break;
				}
				options("Happy Christmas, Your Majesty.", "Why have you sent snow to Keldagrim?", "What is the Land of Snow?", "What is the snow globe for?", "I want to go back to Keldagrim.");
				stage++;
				break;
			case 101:
				switch (buttonId) {
				case 1:
					player("Happy Christmas, Your Majesty.");
					stage = 110;
					break;
				case 2:
					player("Why have you sent snow to Keldagrim?");
					stage = 120;
					break;
				case 3:
					player("What is the Land of Snow?");
					stage = 130;
					break;
				case 4:
					player("What is the snow globe for?");
					stage = 140;
					break;
				case 5:
					player("I want to go back to Keldagrim.");
					stage = 150;
					break;
				}
				break;
			case 110:
				npc("And a Happy New Year to you.");
				stage++;
				break;
			case 111:
				end();
				break;
			case 120:
				npc("A snowy Christmas is a tradition that I have sadly neglected", "in recent years; what is Christmas without the hope", "of snow? There can be no hope of snow if snow", "never comes.");
				stage = 111;
				break;
			case 130:
				npc("The Land of Snow was created aeons ago by Guthix, as", "part of his balancing of the world. The coldness of this", "place counteracts the heat of the great deserts and volcanoes", "of others; ensuring the correct balance of hot and cold.");
				stage = 111;
				break;
			case 140:
				npc("Nothing, besides your idle amusement. It is imbued with a", "little of the snow imps' magic. When you shake it, it", "will call some snow to you from the Land of Snow.");
				stage = 111;
				break;
			case 150:
				npc("Very well. You may return here during Christmas-time,", "when the Land of Snow is close to Keldagrim. Just speak to", "a snowman or snow imp.");
				stage++;
				break;
			case 151:
				sendChristmasTeleport(player, player.getAttribute("last-location", HUBS[0]));
				end();
				break;
			case 200:
				options("Yes, please.", "No, thank you.");
				stage++;
				break;
			case 201:
				switch (buttonId) {
				case 1:
					player("Yes, please.");
					stage = 18;
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
			return new int[] {6731};
		}

	}

	/**
	 * Represents the snowman NPC.
	 * @author Vexia
	 *
	 */
	public class SnowmanNPC extends AbstractNPC {

		/**
		 * Constructs the {@code SnowmanNPC}.
		 */
		public SnowmanNPC() {
			super(-1, null);
		}

		/**
		 * Constructs the {@code SnowmanNPC}
		 * @param id The id.
		 * @param loc The location.
		 */
		public SnowmanNPC(int id, Location loc) {
			super(id, loc);
		}

		@Override
		public void handleTickActions() {	
			super.handleTickActions();
			if (!getProperties().getCombatPulse().isAttacking()) {
				final List<NPC> surronding = RegionManager.getLocalNpcs(this, 10);
				for (NPC n : surronding) {
					if (n.getProperties().getCombatPulse().isAttacking()) {
						continue;
					}
					if (n == this) {
						continue;
					}
					for (int i : getIds()) {
						if (n.getId() == i) {
							getProperties().getCombatPulse().attack(n);
							n.getProperties().getCombatPulse().attack(this);
							break;
						}
					}
				}
			}
		}

		@Override
		public void sendImpact(BattleState state) {
			if (state.getEstimatedHit() > 4) {
				state.setEstimatedHit(4);
			}
			state.setEstimatedHit(0);
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new SnowmanNPC(id, location);
		}

		@Override
		public boolean isAttackable(Entity entity, CombatStyle style) {
			if (entity instanceof Player) {
				((Player) entity).sendMessage("You can't attack a snowman.");
				return false;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] {6747, 6748, 6749};
		}

	}

	/**
	 * Handles the snow ball item plugin.
	 * @author Vexia
	 *
	 */
	public class SnowballItemPlugin implements Plugin<Object> {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			BALL_OF_SNOW.getDefinition().getConfigurations().put("equipment", this);
			return this;
		}

		@Override
		public Object fireEvent(String identifier, Object... args) {
			final Player player = (Player) args[0];
			@SuppressWarnings("unused")
			Item item = (Item) args[1];
			final Item other = args.length == 2 ? null : (Item) args[2];
			if (other != null) {
				identifier = "equip";
				item = other;
			}
			switch (identifier) {
			case "equip":
				player.getInteraction().set(PELT_OPTION);
				break;
			case "unequip":
				player.getInteraction().remove(PELT_OPTION);
				break;
			}
			return true;
		}
	}
}
