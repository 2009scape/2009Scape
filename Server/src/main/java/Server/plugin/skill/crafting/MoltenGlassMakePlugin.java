package plugin.skill.crafting;

import plugin.skill.SkillPulse;
import plugin.skill.Skills;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin used to make molten glass.
 * @author Vexia
 * @author Splinter
 * @version 1.0
 */
@InitializablePlugin
public final class MoltenGlassMakePlugin extends UseWithHandler {

	/**
	 * Constructs a new {@code MoltenGlassMakePlugin} {@code Object}.
	 */
	public MoltenGlassMakePlugin() {
		super(1783, 1781);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(4304,  OBJECT_TYPE, this);
		addHandler(6189,  OBJECT_TYPE, this);
		addHandler(9390,  OBJECT_TYPE, this);
		addHandler(11010, OBJECT_TYPE, this);
		addHandler(11666, OBJECT_TYPE, this);
		addHandler(12100, OBJECT_TYPE, this);
		addHandler(12809, OBJECT_TYPE, this);
		addHandler(18497, OBJECT_TYPE, this);
		addHandler(26814, OBJECT_TYPE, this);
		addHandler(30021, OBJECT_TYPE, this);
		addHandler(30510, OBJECT_TYPE, this);
		addHandler(36956, OBJECT_TYPE, this);
		addHandler(37651, OBJECT_TYPE, this);
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		event.getPlayer().getPulseManager().run(new MoltenGlassPulse(event.getPlayer(), event.getUsedItem()));
		return true;
	}

	/**
	 * Represents the pulse for molten glass making via a furnace
	 * @author Splinter
	 */
	public static class MoltenGlassPulse extends SkillPulse<Item> {

		/**
		 * Represents the bucket of sand item.
		 */
		private static final Item BUCKET_OF_SAND = new Item(1783);

		/**
		 * Represents the soda ash item.
		 */
		private static final Item SODA_ASH = new Item(1781);

		/**
		 * Represents the molten glass item.
		 */
		private static final Item MOLTEN_GLASS = new Item(1775);

		/**
		 * Represents the bucket item.
		 */
		private static final Item BUCKET = new Item(1925);

		/**
		 * Constructs a new {@code MoltenGlassPulse} {@code Object}.
		 * @param player the player.
		 * @param node the node.
         */
		public MoltenGlassPulse(Player player, Item node) {
			super(player, node);
		}

		@Override
		public boolean checkRequirements() {
			if (player.getInventory().contains(1783, 1) && player.getInventory().contains(1781, 1)) {
				return true;
			}
			player.sendMessage("You need at least one heap of soda ash and one bucket of sand to do this.");
			return false;
		}

		@Override
		public void animate() {
			player.animate(Animation.create(899));
		}

		@Override
		public boolean reward() {
			if (getDelay() == 1) {
				super.setDelay(3);
				return false;
			}
			if (player.getInventory().remove(BUCKET_OF_SAND, SODA_ASH)) {
				player.lock(3);
				player.getInventory().add(MOLTEN_GLASS, BUCKET);
				player.getSkills().addExperience(Skills.CRAFTING, 20, true);
				player.sendMessage("You heat the sand and soda ash in the furnace to make glass.");
			} else {
				return true;
			}
			return false;
		}

	}

}
