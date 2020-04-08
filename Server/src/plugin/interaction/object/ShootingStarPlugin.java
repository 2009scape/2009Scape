package plugin.interaction.object;

import java.util.concurrent.TimeUnit;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.free.gather.SkillingTool;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.state.impl.DoubleOrePulse;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.command.CommandPlugin;
import org.crandor.game.system.command.CommandSet;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.repository.Repository;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import org.crandor.plugin.PluginManifest;
import org.crandor.plugin.PluginType;
import org.crandor.tools.RandomFunction;

/**
 * Handles the shooting star plugin.
 * @author Vexia
 *
 */
@InitializablePlugin
public class ShootingStarPlugin extends OptionHandler {

	/**
	 * The shooting star crash sites.
	 */
	private static final Object[][] CRASH_SITES = new Object[][] {
		{"Canifis Bank", Location.create(3504, 3487, 0)},
		{"Crafting Guild", Location.create(2940, 3280, 0)},
		{"Falador East Bank", Location.create(3030, 3349, 0)},
		{"Rimmington mining site", Location.create(2975, 3237, 0)},
		{"Rimmington mine", Location.create(2975, 3240, 0)},
		{"Karamja mining site", Location.create(2737, 3223, 0)},
		{"Brimhaven mining site", Location.create(2743, 3143, 0)},
		{"South Crandor mining site", Location.create(2822, 3239, 0)},
		{"Karamja mining site", Location.create(2854, 3032, 0)},
		{"Shilo Village mining site", Location.create(2826, 2997, 0)},
		{"Relleka mining site", Location.create(2682, 3700, 0)},
		{"Ardougne mining site", Location.create(2600, 3232, 0)},
		{"Yanille Bank", Location.create(2603, 3087, 0)},
		{"Al Kharid bank", Location.create(3276, 3176, 0)},
		{"Al Kharid mining site", Location.create(3296, 3297, 0)},
		{"Duel Arena bank chest", Location.create(3342, 3267, 0)},
		{"Nardah mining site", Location.create(3320, 2872, 0)},
		{"Nardah bank", Location.create(3434, 2888, 0)},
		{"South-east Varrock mine", Location.create(3292, 3353, 0)},
		{"South-west Varrock mine", Location.create(3176, 3362, 0)},
		{"Varrock east bank", Location.create(3259, 3407, 0)},
		{"Lumbridge Swamp mine", Location.create(3227, 3150, 0)},
		{"Gnome stronghold Bank", Location.create(2460, 3432, 0)},
		{"North Edgeville mining site", Location.create(3101, 3569, 0)},
		{"Southern wilderness mine", Location.create(3025, 3591, 0)},
		{"Pirates' Hideout mine", Location.create(3059, 3940, 0)},
		{"Lava Maze mining site", Location.create(3062, 3885, 0)},
		{"Mage Arena bank", Location.create(3093, 3962, 0)}
	};

	/**
	 * Checks whether or not a discovery/first mine award has been given
	 */
	public static boolean isDiscovered = false;

	/**
	 * The star dust item id.
	 */
	private static final int STAR_DUST = 13727;

	/**
	 * The top tier that appeared.
	 */
	private static ShootingStar topTier;

	/**
	 * The shooting star currently deployed.
	 */
	private static ShootingStar star;

	/**
	 * The current star object.
	 */
	private static GameObject object;

	/**
	 * The crash site.
	 */
	private static String crashSite;

	/**
	 * The star sprite.
	 */
	private static NPC sprite;

	/**
	 * The amount of ticks passed.
	 */
	private static int ticks;

	/**
	 * The star updating pulse.
	 */

	private static final Pulse pulse = new Pulse(1) {

		@Override
		public boolean pulse() {
			if (++ticks == (GameWorld.getSettings().isDevMode() ? 200 : 7200) && star == null && object == null) {
				ShootingStar.submit();
				return false;
			}
			if (star != null && object != null && crashSite != null && topTier != null && star == topTier && (star.getTotalStardust() - star.getMinedStardust() == star.getTotalStardust()) && ticks == 5000) {
				ShootingStar.remove();
			}
			if (sprite != null && ticks == (GameWorld.getSettings().isDevMode() ? 100 : 1000)) {
				sprite.clear();
				sprite = null;
			}
			return false;
		}

	};

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (ShootingStar star : ShootingStar.values()) {
			ObjectDefinition.forId(star.getObjectId()).getConfigurations().put("option:mine", this);
			ObjectDefinition.forId(star.getObjectId()).getConfigurations().put("option:prospect", this);
		}
		ObjectDefinition.forId(13656).getConfigurations().put("option:observe", this);
		pulse.start();
		GameWorld.submit(pulse);
		PluginManager.definePlugin(new CommandPlugin() {

			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				link(CommandSet.DEVELOPER);
				return this;
			}

			@Override
			public boolean parse(Player player, String name, String[] args) {
				switch (name) {
				case "tostar":
					if (star != null && object != null) {
						player.teleport(object.getLocation().transform(1, 1, 0));
					}
					break;
				case "submit":
					ShootingStar.submit();
					break;
				case "resettime":
					player.getSavedData().getGlobalData().setStarSpriteDelay(0L);
					break;
				case "stardust":
					int dust = 8;
					System.out.println("Cosmic Runes: " +  0.76 * dust);
					System.out.println("Astral runes: " + 0.26 * dust);
					System.out.println("Gold ores: " +  0.1 * dust);
					System.out.println("GP: " + 250.1 * dust);
					break;
				case "setsprite":
					player.getSavedData().getGlobalData().setStarSpriteDelay(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1));
					break;
				case "resetstar":
					ShootingStar.remove();
					break;
				}
				return true;
			}

		});
		PluginManager.definePlugin(new StarSpriteDialogue(), new ShootingStarLogin());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		ShootingStar star;
		switch (option) {
		case "mine":
			star = ShootingStar.forId(node.getId());
			star.mine(player, node.asObject());
			break;
		case "prospect":
			star = ShootingStar.forId(node.getId());
			star.prospect(player, node.asObject());
			break;
		case "observe":
			if (ShootingStarPlugin.star != null && crashSite != null && object != null) {
				player.getDialogueInterpreter().sendDialogue("A shooting star (Level " + (ShootingStarPlugin.star.ordinal() + 1) + ")", "is currently crashed near the " + crashSite + ".");
			} else {
				int tickDiff = (GameWorld.getSettings().isDevMode() ? 200 : 7200) - ticks;
				int seconds = (int) Math.ceil((float) ((float) tickDiff / (float) 1000) * 600);
				int hours = (int) TimeUnit.SECONDS.toHours(seconds);
				int minutes = (int) TimeUnit.SECONDS.toMinutes(seconds);
				String time = "";
				if (hours > 0) {
					time += hours + " hour";
				} else {
					if (minutes > 0) {
						time += minutes + " minute(s)";
					} else {
						time += seconds + " second(s)";
					}
				}
				player.getDialogueInterpreter().sendDialogue("The next star will crash in approximately:", time);
			}
			break;
		}
		return true;
	}

	/**
	 * Gets the star dust amount for a player.
	 * @param player The player.
	 * @return The stardust amount.
	 */
	public static int getStarDust(Player player) {
		return player.getInventory().getAmount(STAR_DUST) + player.getBank().getAmount(STAR_DUST);
	}

	@PluginManifest(type = PluginType.LOGIN)
	public class ShootingStarLogin implements Plugin<Player> {

		/**
		 * Constructs the shooting star login.
		 */
		public ShootingStarLogin() {
			/*
			 * empty.
			 */
		}

		@Override
		public Plugin<Player> newInstance(Player arg) throws Throwable {
			if (arg instanceof Player) {
				if (star != null && object != null && object.isActive() && crashSite != null) {
					((Player) arg).sendMessage("<img=12><col=CC6600>News: A shooting star (Level " + (star.ordinal() + 1) + ") has just crashed near the " + crashSite + "!");
				}
				return this;
			}
			return this;
		}

		@Override
		public Object fireEvent(String identifier, Object... args) {
			return null;
		}

	}

	/**
	 * Represents a shooting star.
	 * @author Vexia
	 *
	 */
	public enum ShootingStar {
		LEVEL_1(38668, 14, 1200, 0.05),
		LEVEL_2(38667, 25, 700, 0.1),
		LEVEL_3(38666, 29, 439, 0.3),
		LEVEL_4(38665, 32, 250, 0.4),
		LEVEL_5(38664, 47, 175, 0.5),
		LEVEL_6(38663, 71, 80, 0.70),
		LEVEL_7(38662, 114, 40, 0.80),
		LEVEL_8(38661, 145, 25, 0.85),
		LEVEL_9(38660, 210, 15, 0.95);

		/**
		 * The object id.
		 */
		private final int objectId;

		/**
		 * The exp gained from one star dust.
		 */
		private final double exp;

		/**
		 * The total stardust to be mined.
		 */
		private final int totalStardust;

		/**
		 * The rate in which the star is mined.
		 */
		private final double rate;

		/**
		 * The amount of stardust currently mined. 
		 */
		private int minedStardust;	

		/**
		 * Constructs an {@code ShootingStar} object.
		 * @param objectId The object id of the star.
		 * @param exp The experience.
		 * @param totalStardust The total stardust.
		 * @param rate The rate in which the star is mined.
		 */
		private ShootingStar(int objectId, double exp, int totalStardust, double rate) {
			this.objectId = objectId;
			this.exp = exp;
			this.totalStardust = totalStardust;
			this.rate = rate;
		}

		/**
		 * Submits a shooting star into the world.
		 */
		public static void submit() {
			if (star != null) {
				star = null;
				crashSite = null;
				if (object != null) {
					ObjectBuilder.remove(object); 
				}
			}
			Object[] crashData = CRASH_SITES[RandomFunction.random(CRASH_SITES.length - 1)];
			star = RandomFunction.getRandomElement(ShootingStar.values());
			topTier = star;
			star.setMinedStardust(0);
			crashSite = (String) crashData[0];
			ObjectBuilder.add(object = new GameObject(star.getObjectId(), (Location) crashData[1]));
			Repository.sendNews("A shooting star (Level " + (star.ordinal() + 1) + ") has just crashed near the " + crashSite + "!");
			ticks = 0;
		}

		/**
		 * Removes a shooting star from the world.
		 */
		public static void remove() {
			star.setMinedStardust(0);
			if (star != null && object != null) {
				ObjectBuilder.remove(object);
				star = null;
				object = null;
				crashSite = null;
				ticks = 0;
				isDiscovered = false;
			}
		}

		/**
		 * Gets the shooting star for the object id. 
		 * @param objectId The object id of the star.
		 * @return The {@code ShootingStar} object.
		 */
		public static ShootingStar forId(int objectId) {
			for (ShootingStar star : ShootingStar.values()) {
				if (star.getObjectId() == objectId) {
					return star;
				}
			}
			return null;
		}

		/**
		 * Stars mining the shooting star.
		 * @param player The player.
		 * @param object The object.
		 */
		public void mine(Player player, GameObject object) {
			player.getPulseManager().run(new ShootingStarMiningPulse(player, object));
		}

		/**
		 * Prospects the shooting star.
		 * @param player The player.
		 */
		public void prospect(Player player, GameObject object) {
			player.getDialogueInterpreter().sendDialogue("This is a size " + (ordinal() + 1) + " star. A Mining level of at least " + getMiningLevel() + " is", "required to mine this layer. There is " + (totalStardust - minedStardust) + " stardust remaining", "until the next layer.");
		}

		/**
		 * Increments the amount of stardust mined.
		 */
		public void incrementStardust() {
			minedStardust++;
		}

		/**
		 * Sets the mined stardust.
		 * @param amount The amount.
		 */
		public void setMinedStardust(int amount) {
			this.minedStardust = amount;
		}

		/**
		 * Gets the amount of stardust mined from this level.
		 * @return The amount of stardust mined.
		 */
		public int getMinedStardust() {
			return minedStardust;
		}

		/**
		 * @return the miningLevel
		 */
		public int getMiningLevel() {
			return (ordinal() + 1) * 10;
		}

		/**
		 * @return the exp
		 */
		public double getExp() {
			return exp;
		}

		/**
		 * @return the totalStardust
		 */
		public int getTotalStardust() {
			return totalStardust;
		}

		/**
		 * @return the rate
		 */
		public double getRate() {
			return rate;
		}

		/**
		 * Gets the object id.
		 * @return The object id.
		 */
		public int getObjectId() {
			return objectId;
		}

	}

	/**
	 * Handles the mining of a shooting star.
	 * @author Vexia
	 *
	 */
	public static class ShootingStarMiningPulse extends SkillPulse<GameObject> {

		/**
		 * The amount of ticks it takes to get star dust.
		 */
		private int ticks;

		/**
		 * Constructs the {@code ShootingStarMiningPulse} object.
		 * @param player The player.
		 * @param node The node.
		 */
		public ShootingStarMiningPulse(Player player, GameObject node) {
			super(player, node);
		}

		@Override
		public void start() {
			if (star == null || object == null || !object.isActive()) {
				return;
			}
			super.start();
		}

		@Override
		public boolean checkRequirements() {
			tool = SkillingTool.getPickaxe(player);
			if (star == null || object == null || !object.isActive()) {
				return false;
			}
			//checks if the star has been discovered and if not, awards the bonus xp. Xp can be awarded regardless of mining level as per the wiki.
			if(!isDiscovered) {
				player.getSkills().addExperience(Skills.MINING, 75 * player.getSkills().getStaticLevel(Skills.MINING));
				Repository.sendNews(player.getUsername() + " is the discoverer of the crashed star near " + crashSite + "!");
				isDiscovered = true;
				if (player.getSkills().getLevel(Skills.MINING) < star.getMiningLevel()) {
					return false;
				} else {
					return true;
				}
			}
			if (player.getSkills().getLevel(Skills.MINING) < star.getMiningLevel()) {
				player.getDialogueInterpreter().sendDialogue("You need a Mining level of at least " + star.getMiningLevel() + " in order to mine this layer.");
				return false;
			}
			if (tool == null) {
				player.getPacketDispatch().sendMessage("You do not have a pickaxe to use.");
				return false;
			}
			if (player.getInventory().freeSlots() < 1 && !player.getInventory().contains(STAR_DUST, 1)) {
				player.getDialogueInterpreter().sendDialogue("Your inventory is too full to hold any more stardust.");
				return false;
			}
			return true;
		}

		@Override
		public void animate() {
			player.animate(tool.getAnimation());
		}

		@Override
		public boolean reward() {
			if (++ticks % 4 != 0) {
				return false;
			}
			if (!checkReward()) {
				return false;
			}
			if (GameWorld.getSettings().isDevMode()) {
				star.setMinedStardust(star.getTotalStardust());
			} else {
				star.incrementStardust();
			}
			
			if (ShootingStarPlugin.getStarDust(player) < 200) { 
				player.getInventory().add(new Item(STAR_DUST, 1));
			}
			player.getSkills().addExperience(Skills.MINING, star.getExp());
			if (star.getMinedStardust() >= star.getTotalStardust()) {
				Location loc = object.getLocation();
				ObjectBuilder.remove(object);
				if (star == ShootingStar.LEVEL_1) {
					if (sprite != null) {
						sprite.clear();
					}
					sprite = NPC.create(8091, loc);
					sprite.init();
					ShootingStar.remove();
					return true;
				}
				star.setMinedStardust(0);
				star = ShootingStar.values()[star.ordinal() -1];
				star.setMinedStardust(0);
				ObjectBuilder.add(object = new GameObject(star.getObjectId(), loc));
				return true;
			}
			return false;
		}

		@Override
		public void message(int type) {
			switch (type) {
			case 0:
				player.getPacketDispatch().sendMessage("You swing your pickaxe at the rock...");
				break;
			}
		}

		/**
		 * Checks if the player gets rewarded.
		 * @return {@code True} if so.
		 */
		private boolean checkReward() {
			int skill = Skills.MINING;
			int level = 1 + player.getSkills().getLevel(skill) + player.getFamiliarManager().getBoost(skill);
			double hostRatio = Math.random() * (100.0 * star.getRate());
			double clientRatio = Math.random() * ((level - star.getMiningLevel()) * (1.0 + tool.getRatio()));
			return hostRatio < clientRatio;
		}

	}

	/**
	 * Handles the star sprite dialogue.
	 * @author Vexia
	 *
	 */
	public class StarSpriteDialogue extends DialoguePlugin {

		/**
		 * The cosmic rune item id.
		 */
		private static final int COSMIC_RUNE = 564;

		/**
		 * The astral rune item id.
		 */
		private static final int ASTRAL_RUNE =  9075;

		/**
		 * The gold ore item id.
		 */
		private static final int GOLD_ORE = 445;

		/**
		 * The coins id.
		 */
		private static final int COINS = 995;

		/**
		 * The amplifier amount.
		 */
		private static final double AMPLIFIER = 1;

		/**
		 * Constructs the star sprite dialogue.
		 * @param player The player.
		 */
		public StarSpriteDialogue(Player player) {
			super(player);
		}

		/**
		 * Constructs the star sprite dialogue.
		 */
		public StarSpriteDialogue() {
			/*
			 * empty.
			 */
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new StarSpriteDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			if (player.getSavedData().getGlobalData().getStarSpriteDelay() > System.currentTimeMillis() || !player.getInventory().contains(STAR_DUST, 1)) {
				npc("Hello, strange creature.");
				stage = 0;
			} else {
				npc("Thank you for helping me out of here.");
				stage = 50;
			}
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				npc("I'm a star sprite! I Was in my star in the sky, when it", "lost control and crashed into the ground. With half my", "star sticking into the ground, I became stuck.", "Fortunately, I was mined out by the kind creatures of");
				stage++;
				break;
			case 1:
				npc("your race.");
				stage++;
				break;
			case 2:
				options("What's a star sprite?", "What are you going to do without your star?", "I thought stars were huge balls of burning gas.", "Well, I'm glad you're okay.");
				stage++;
				break;
			case 3:
				switch (buttonId) {
				case 1:
					player("What's a star sprite?");
					stage = 10;
					break;
				case 2:
					player("What are you going to do without your star?");
					stage = 20;
					break;
				case 3:
					player("I thought stars were huge balls of burning gas.");
					stage = 30;
					break;
				case 4:
					player("Well, I'm glad you're okay.");
					stage = 40;
					break;
				}
				break;
			case 10:
				npc("We're what makes the stars in the sky shine. I made", "this star shine when it was in the sky.");
				stage++;
				break;
			case 11:
				options("What are you going to do without your star?", "I thought stars were huge balls of burning gas.", "Well, I'm glad you're okay.");
				stage++;;
				break;
			case 12:
				switch (buttonId) {
				case 1:
					player("What are you going to do without your star?");
					stage = 20;
					break;
				case 2:
					player("I thought stars were huge balls of burning gas.");
					stage = 30;
					break;
				case 3:
					player("Well, I'm glad you're okay.");
					stage = 40;
					break;
				}
				break;
			case 20:
				npc("Don't worry about me. I'm sure I'll find some good", "rocks around here and get back up into the sky in no", "time.");
				stage++;
				break;
			case 21:
				options("What's a star sprite?", "I thought stars were huge balls of burning gas.", "Well, I'm glad you're okay.");
				stage++;
				break;
			case 22:
				switch (buttonId) {
				case 1:
					player("What's a star sprite?");
					stage = 10;
					break;
				case 2:
					player("I thought stars were huge balls of burning gas.");
					stage = 30;
					break;
				case 3:
					player("Well, I'm glad you're okay.");
					stage = 40;
					break;
				}
				break;
			case 30:
				npc("Most of them are, but a lot of shooting stars on this", "plane of the multiverse are rocks with star sprites in", "them.");
				stage++;
				break;
			case 31:
				options("What's a star sprite?", "What are you going to do without your star?", "Well, I'm glad you're okay.");
				stage++;
				break;
			case 32:
				switch (buttonId) {
				case 1:
					player("What's a star sprite?");
					stage = 10;
					break;
				case 2:
					player("What are you going to do without your star?");
					stage = 20;
					break;
				case 3:
					player("Well, I'm glad you're okay.");
					stage = 40;
					break;
				}
				break;
			case 40:
				npc("Thank you.");
				stage++;
				break;
			case 41:
				end();
				break;
			case 50:
				int dust = player.getInventory().getAmount(STAR_DUST) > 200 ? 200 : player.getInventory().getAmount(STAR_DUST);
				if (player.getInventory().remove(new Item(STAR_DUST, dust))) {
					int cosmicRunes = (int) (Math.ceil(0.76 * dust) * AMPLIFIER);
					int astralRunes = (int) (Math.ceil(0.26 * dust) * AMPLIFIER);
					int goldOre = (int) (Math.ceil(0.1 * dust) * AMPLIFIER);
					int coins = (int) (Math.ceil(250.0 * dust) * AMPLIFIER); // limits the amount of gp per turn-in to 90k, same as 2009 rs, and minimum from all stardust from a level 1 star works out to 50k, same as rs in 2009
					player.getInventory().add(new Item(COSMIC_RUNE, cosmicRunes), player);
					player.getInventory().add(new Item(ASTRAL_RUNE, astralRunes), player);
					player.getInventory().add(new Item(GOLD_ORE, goldOre), player);
					player.getInventory().add(new Item(COINS, coins), player);
					npc("I have rewarded you by making it so you can mine", "extra ore for the next 15 minutes. Also, have " + cosmicRunes, "cosmic runes, " + astralRunes + " astral runes, " + goldOre + " gold ore and " + coins, "coins.");
					player.setAttribute("SS Mining Bonus",System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15));
					player.getSavedData().getGlobalData().setStarSpriteDelay(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1));
					int endTick = GameWorld.getTicks() + 1500;
					player.setAttribute("SS Mining Bonus",endTick);
					DoubleOrePulse orePulse = new DoubleOrePulse(player,endTick,GameWorld.getTicks());
					orePulse.run();
				}
				stage = 52;
				break;
			case 52:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] {8091};
		}

	}

}
