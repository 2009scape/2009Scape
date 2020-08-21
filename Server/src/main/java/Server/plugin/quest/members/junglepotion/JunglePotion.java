package plugin.quest.members.junglepotion;

import plugin.skill.Skills;
import plugin.skill.herblore.Herbs;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.system.task.Pulse;
import core.game.world.update.flag.context.Animation;
import core.plugin.PluginManager;
import core.tools.RandomFunction;
import core.tools.StringUtils;

import core.plugin.InitializablePlugin;
import plugin.quest.members.junglepotion.JunglePotionPlugin.JogreCavernDialogue;

/**
 * The main type or the jungle potion quest.
 * @author Vexia
 * 
 */
@InitializablePlugin
public final class JunglePotion extends Quest {

	/**
	 * The name of the quest.
	 */
	public static final String NAME = "Jungle Potion";

	/**
	 * Constructs a new {@code JunglePotion} {@code Object}.
	 * @param player the player.
	 */
	public JunglePotion() {
		super(NAME, 81, 80, 1, 175, 0, 1, 12);
	}
	
	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugin(new TrufitusDialogue());
		PluginManager.definePlugin(new JogreCavernDialogue());
		PluginManager.definePlugin(new JunglePotionPlugin());
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:
			line(player, "<blue>I can start this quest by speaking to <red>Trufitus Shakaya<n><blue>who lives in the main hut in <red>Tai Bwo Wannai<n><blue>village on the island of <red>Karamja.", 11);
			break;
		case 10:
		case 20:
		case 30:
		case 40:
		case 50:
			JungleObjective objective = JungleObjective.forStage(stage);
			if (player.getInventory().containsItem(objective.getHerb().getProduct())) {
				line(player, "<str>I spoke to Trufitus, he needs to commune with the<n><str>gods, he's asked me to help him by collecting herbs.<n><n><str>I picked some fresh " + objective.getName() + " for Trufitus.<n><n><blue>I need to give the <red>" + objective.getName() + " <blue> to <red>Trufitus.", 11);
				return;
			}
			line(player, "<str>I spoke to Trufitus, he needs to commune with the<n><str>gods, he's asked me to help him by collecting herbs.<n><n><blue>I need to pick some fresh <red>" + objective.getName() + " <blue>for <red>Trufitus.", 11);
			break;
		case 60:
			line(player, "<str>I spoke to Trufitus, he needs to commune with the<n><str>gods, he's asked me to help him by collecting herbs.<n><n><str>I have given Trufitus Snakeweed, Ardrigal,<n><str>Sito Foil, Volencia moss and Rogues purse.<n><n><str>Trufitus needs to commune with the gods.<n><blue>I should speak to <red>Trufitus.", 11);
			break;
		case 100:
			line(player, "<str>Trufitus Shakaya of the Tai Bwo Wannai village needed<n><str>some jungle herbs in order to make a potion which would<n><str>help him commune with the gods. I collected five lots<n><str>of jungle herbs for him and he was able to<n><str>commune with the gods.<n><n><str>As a reward he showed me some herblore techniques.<n><n><col=FF0000>QUEST COMPLETE!</col>", 11);
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getPacketDispatch().sendString("1 Quest Point", 277, 8 + 2);
		player.getPacketDispatch().sendString("775 Herblore XP", 277, 9 + 2);
		player.getPacketDispatch().sendItemZoomOnInterface(Herbs.VOLENCIA_MOSS.getProduct().getId(), 235, 277, 3 + 2);
		player.getSkills().addExperience(Skills.HERBLORE, 775);
		player.getQuestRepository().syncronizeTab(player);
	}
	
	/**
	 * An objective during the quest.
	 * @author Vexia
	 */
	public static enum JungleObjective {
		JUNGLE_VINE(2575, Herbs.SNAKE_WEED, 10, "It grows near vines in an area to the south west where", "the ground turns soft and the water kisses your feet.") {
			@Override
			public void search(final Player player, final GameObject object) {
				final Animation animation = Animation.create(2094);
				player.animate(animation);
				player.getPulseManager().run(new Pulse(animation.getDefinition().getDurationTicks(), player, object) {
					@Override
					public boolean pulse() {
						boolean success = RandomFunction.random(3) == 1;
						if (success) {
							switchObject(object);
							findHerb(player);
							return true;
						}
						player.animate(animation);
						return false;
					}
				});
			}
		},
		PALM_TREE(2577, Herbs.ARDRIGAL, 20, "You are looking for Ardrigal. It is related to the palm", "and grows in its brothers shady profusion."), SITO_FOIL(2579, Herbs.SITO_FOIL, 30, "You are looking for Sito Foil, and it grows best where", "the ground has been blackened by the living flame."), VOLENCIA_MOSS(2581, Herbs.VOLENCIA_MOSS, 40, "You are looking for Volencia Moss. It clings to rocks", "for its existance. It is difficult to see, so you must", "search for it well."), ROGUES_PURSE(32106, Herbs.ROGUES_PUSE, 50, "It inhabits the darkness of the underground, and grows", "in the caverns to the north. A secret entrance to the", "caverns is set into the northern cliffs, be careful Bwana.") {
			@Override
			public void search(final Player player, final GameObject object) {
				final Animation animation = Animation.create(2097);
				player.animate(animation);
				player.getPulseManager().run(new Pulse(animation.getDefinition().getDurationTicks(), player, object) {
					@Override
					public boolean pulse() {
						boolean success = RandomFunction.random(4) == 1;
						if (success) {
							switchObject(object);
							findHerb(player);
							return true;
						}
						player.animate(animation, 1);
						return false;
					}
				});
			}
		};

		/**
		 * The object id.
		 */
		private final int objectId;

		/**
		 * The herb.
		 */
		private final Herbs herb;

		/**
		 * The stage required to search this.
		 */
		private final int stage;

		/**
		 * The objective clue.
		 */
		private final String[] clue;

		/**
		 * Constructs a new {@code Searchable} {@code Object}.
		 * @param objectId the object id.
		 * @param herb the herb.
		 * @param stage the stage..
		 */
		private JungleObjective(int objectId, Herbs herb, int stage, final String... clue) {
			this.objectId = objectId;
			this.herb = herb;
			this.stage = stage;
			this.clue = clue;
		}

		/**
		 * Handles the search function of the objective.
		 * @param player the player.
		 * @param object the object.
		 */
		public void search(final Player player, final GameObject object) {
			findHerb(player);
			switchObject(object);
		}

		/**
		 * Switches the objectds id.
		 * @param object the object.
		 */
		public void switchObject(GameObject object) {
			if (object.isActive()) {
				ObjectBuilder.replace(object, object.transform(object.getId() + 1), 80);
			}
		}

		/**
		 * Finds a herb.
		 * @param player the player.
		 */
		public void findHerb(final Player player) {
			player.getInventory().add(getHerb().getHerb());
			player.getDialogueInterpreter().sendItemMessage(getHerb().getHerb(), "You find a herb.");
		}

		/**
		 * Gets the jungle objective.
		 * @param stage the stage.
		 * @return the objective.
		 */
		public static JungleObjective forStage(int stage) {
			for (JungleObjective o : values()) {
				if (o.getStage() == stage) {
					return o;
				}
			}
			return null;
		}

		/**
		 * Gets a searchable by the object id.
		 * @param objectId the object id.
		 * @return the searchable.
		 */
		public static JungleObjective forId(int objectId) {
			for (JungleObjective s : values()) {
				if (s.getObjectId() == objectId) {
					return s;
				}
			}
			return null;
		}

		/**
		 * Gets the name of the herb objective.
		 * @return the name.
		 */
		public String getName() {
			return StringUtils.formatDisplayName(herb.getProduct().getName().replace("Clean", "").trim());
		}

		/**
		 * Gets the objectId.
		 * @return The objectId.
		 */
		public int getObjectId() {
			return objectId;
		}

		/**
		 * Gets the herb.
		 * @return The herb.
		 */
		public Herbs getHerb() {
			return herb;
		}

		/**
		 * Gets the stage.
		 * @return The stage.
		 */
		public int getStage() {
			return stage;
		}

		/**
		 * Gets the clue.
		 * @return The clue.
		 */
		public String[] getClue() {
			return clue;
		}
	}

}
