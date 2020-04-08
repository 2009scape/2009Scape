package plugin.consumable.food;

import org.crandor.game.content.global.consumable.ConsumableProperties;
import org.crandor.game.content.global.consumable.Consumables;
import org.crandor.game.content.global.consumable.Food;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.StringUtils;

/**
 * Represents a generic pie food.
 * @author 'Vexia, rewritten by ceik.
 * @date 23/12/2013
 */
@InitializablePlugin
public class PiePlugin extends Food {


	/**
	 * Constructs a new {@code PiePlugin.java} {@code Object}.
	 */
	public PiePlugin() {
		/**
		 * empty.
		 */
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		final PiePlugin[] PIES = new PiePlugin[] {
				new PiePlugin( 2325, new ConsumableProperties(5, 2333)), //full redberry
				new PiePlugin( 2327, new ConsumableProperties(5, 2331)), //full meat
				new PiePlugin( 2323, new ConsumableProperties(7, 2335)), //full apple
				new GardenPie( 7178,new ConsumableProperties( 0, 7180)), //full garden pie
				new FishPie(   7188,new ConsumableProperties( 0, 7190)), //full fish pie
				new AdmiralPie(7198,new ConsumableProperties( 7, 7200)), //full admiral pie
				new WildPie(   7208,new ConsumableProperties( 11,7210)), //full wild pie
				new SummerPie( 7218, new ConsumableProperties(11,7220)), //full summer pie
				new PiePlugin( 2333, 5), //half redberry
				new PiePlugin( 2331, 5), //half meat
				new PiePlugin( 2335, 7), //half apple
				new GardenPie( 7180,  0), //half garden pie
				new FishPie(   7190,  0), //half fish pie
				new AdmiralPie(7200, 7), //half admiral pie
				new WildPie(   7210, 11),//half wild pie
				new SummerPie( 7220, 11) //half summer pie
		};
		for (PiePlugin pie : PIES) {
			Consumables.add(pie);
		}
		return this;
	}

	/**
	 * Constructs a new {@code Pie.java} {@code Object}.
	 * @param itemId the item id.
	 * @param health the health.
	 */
	public PiePlugin(final int itemId, final int health) {
		super(itemId, new ConsumableProperties(health,2313));
	}
	public PiePlugin(final int itemId, ConsumableProperties properties) { super(itemId,properties);}

	@Override
	public String getEatMessage() {
		return "You eat " + (getItem().getName().toLowerCase().contains("half") ? getItem().getName().toLowerCase() : "half " + (StringUtils.isPlusN(getItem().getName().toLowerCase()) ? "an " : "a ") + getItem().getName().toLowerCase()) + ".";
	}

	/**
	 * Represents the garden pie.
	 * @author 'Vexia
	 * @date 23/12/2013
	 */
	public class GardenPie extends PiePlugin {

		/**
		 * Constructs a new {@code GardenPie} {@code Object}.
		 * @param heal the healing amount.
		 * @param itemId the new item id.
		 */
		public GardenPie(int itemId, int heal) {
			super(itemId, heal);
		}
		public GardenPie(int itemId, ConsumableProperties properties){super(itemId,properties);}

		@Override
		public void consume(final Item item, final Player player) {
			super.consume(item, player);
			player.getSkills().updateLevel(Skills.FARMING, 3, player.getSkills().getStaticLevel(Skills.FARMING) + 3);
		}

	}

	/**
	 * Represents the fish pie.
	 * @author 'Vexia
	 * @date 23/12/2013
	 */
	public class FishPie extends PiePlugin {

		/**
		 * Constructs a new {@code FishPie} {@code Object}.
		 * @param heal the healing amount.
		 * @param itemId the new item id.
		 */
		public FishPie(int itemId, int heal) {
			super(itemId,heal);
		}
		public FishPie(int itemId, ConsumableProperties properties){super(itemId,properties);}


		@Override
		public void consume(final Item item, final Player player) {
			super.consume(item, player);
			player.getSkills().updateLevel(Skills.FISHING, 3, player.getSkills().getStaticLevel(Skills.FISHING) + 3);
		}

	}

	/**
	 * Represents the admiral pie.
	 * @author 'Vexia
	 * @date 23/12/2013
	 */
	public class AdmiralPie extends PiePlugin {
		/**
		 * Constructs a new {@code AdmiralPie} {@code Object}.
		 * @param heal the healing amount.
		 * @param itemId the new item id.
		 */
		public AdmiralPie(int itemId, int health) {
			super(itemId, health);
		}
		public AdmiralPie(int itemId, ConsumableProperties properties){super(itemId,properties);}

		@Override
		public void consume(final Item item, final Player player) {
			super.consume(item, player);
			player.getSkills().updateLevel(Skills.FISHING, 5, player.getSkills().getStaticLevel(Skills.FISHING) + 5);

		}

	}

	/**
	 * Represents the wild pie.
	 * @author 'Vexia
	 * @date 23/12/2013
	 */
	public class WildPie extends PiePlugin {

		/**
		 * Constructs a new {@code WildPie} {@code Object}.
		 * @param heal the healing amount.
		 * @param itemId the new item id.
		 */
		public WildPie(int itemId, int health) {
			super(itemId, health);
		}
		public WildPie(int itemId, ConsumableProperties properties){super(itemId,properties);}


		@Override
		public void consume(final Item item, final Player player) {
			super.consume(item, player);
			player.getSkills().updateLevel(Skills.RANGE, 4, player.getSkills().getStaticLevel(Skills.RANGE) + 4);
			player.getSkills().updateLevel(Skills.SLAYER, 5, player.getSkills().getStaticLevel(Skills.SLAYER) + 5);
		}

	}

	/**
	 * Represents the summer pie.
	 * @author 'Vexia
	 * @date 23/12/2013
	 */
	public class SummerPie extends PiePlugin {

		/**
		 * Constructs a new {@code SummerPie} {@code Object}.
		 * @param heal the healing amount.
		 * @param itemId the new item id.
		 */
		public SummerPie(int itemId, int health) {
			super(itemId, health);
		}
		public SummerPie(int itemId, ConsumableProperties properties){super(itemId,properties);}

		@Override
		public void consume(final Item item, final Player player) {
			super.consume(item, player);
			player.getSettings().updateRunEnergy(-(player.getSettings().getRunEnergy() * 0.10));
			player.getSkills().updateLevel(Skills.AGILITY, 5, player.getSkills().getStaticLevel(Skills.AGILITY) + 5);
		}

	}

	/**
	 * Represents a pie property.
	 * @author 'Vexia
	 * @date 23/12/2013
	 */
	public static class PieProperty extends ConsumableProperties {

		/**
		 * Represents the pie dish item.
		 */
		private static final Item PIE_DISH = new Item(2313);

		/**
		 * Constructs a new {@code PieProperty} {@code Object}.
		 * @param healing the healing power.
		 */
		public PieProperty(int healing) {
			super(healing, PIE_DISH);
		}

		/**
		 * Constructs a new {@code PieProperty.java} {@code Object}.
		 * @param healing the healing power.
		 * @param newItem the new item.
		 */
		public PieProperty(int healing, int newItem) {
			super(healing, newItem);
		}
	}

}
