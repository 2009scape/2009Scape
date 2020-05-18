package plugin.zone.neitiznot;

import org.crandor.game.content.dialogue.SkillDialogueHandler;
import org.crandor.game.content.dialogue.SkillDialogueHandler.SkillDialogue;
import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.free.crafting.armour.LeatherCrafting;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.Plugin;

/**
 * Handles the crafting of yak armour.
 * @author Vexia
 */
public class YakArmourPlugin extends UseWithHandler {

	/**
	 * The body item.
	 */
	private static final Item BODY = new Item(10822);

	/**
	 * The legs item.
	 */
	private static final Item LEGS = new Item(10824);

	/**
	 * Constructs a new {@code YakArmourPlugin} {@code Object}
	 */
	public YakArmourPlugin() {
		super(1733);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(10820, ITEM_TYPE, this);
		return this;
	}

	@Override
	public boolean handle(final NodeUsageEvent event) {
		final Player player = event.getPlayer();
		SkillDialogueHandler dialogue = new SkillDialogueHandler(player, SkillDialogue.TWO_OPTION, LEGS, BODY) {

			@Override
			public void create(final int amount, int index) {
				player.getPulseManager().run(new YakArmourPulse(player, index == 1 ? BODY : LEGS, index, amount));
			}

			@Override
			public int getAll(int index) {
				return player.getInventory().getAmount(event.getUsedItem());
			}

		};
		dialogue.open();
		return true;
	}

	/**
	 * Handles the crafting of yak armour.
	 * @author Vexia
	 */
	public class YakArmourPulse extends SkillPulse<Item> {

		/**
		 * The index.
		 */
		private final int index;

		/**
		 * The ticks.
		 */
		private int ticks;

		/**
		 * The amount.
		 */
		private int amount;

		/**
		 * Constructs a new {@code YakArmourPulse} {@code Object}
		 * @param player the player.
		 * @param node the node.
		 * @param index the index.
		 */
		public YakArmourPulse(Player player, Item node, int index, int amount) {
			super(player, node);
			this.index = index;
			this.amount = amount;
		}

		@Override
		public boolean checkRequirements() {
			int level = (index == 1 ? 43 : 46);
			if (player.getSkills().getLevel(Skills.CRAFTING) < level) {
				player.getDialogueInterpreter().sendDialogue("You need a Crafting level of at least " + level + " in order to do this.");
				return false;
			}
			if (!player.getInventory().contains(LeatherCrafting.NEEDLE, 1)) {
				return false;
			}
			if (!player.getInventory().containsItem(LeatherCrafting.THREAD)) {
				player.getDialogueInterpreter().sendDialogue("You need some thread to make anything out of leather.");
				return false;
			}
			int reqAmount = index == 1 ? 1 : 2;
			if (!player.getInventory().contains(10820, reqAmount)) {
				player.getDialogueInterpreter().sendDialogue("You don't have the required amount of yak-hide in order to do this.");
				return false;
			}
			player.getInterfaceManager().close();
			return true;
		}

		@Override
		public void animate() {
			if (ticks % 5 == 0) {
				player.animate(Animation.create(1249));
			}
		}

		@Override
		public boolean reward() {
			if (++ticks % 5 != 0) {
				return false;
			}
			int reqAmount = index == 1 ? 1 : 2;
			if (player.getInventory().remove(new Item(10820, reqAmount))) {
			    player.getInventory().add(node);
				player.getSkills().addExperience(Skills.CRAFTING, 32, true);
				LeatherCrafting.decayThread(player);
				if (LeatherCrafting.isLastThread(player)) {
					LeatherCrafting.removeThread(player);
				}
				player.sendMessage("You make " + node.getName().toLowerCase() + ".");
			}
			amount--;
			return amount < 1;
		}

	}

}
